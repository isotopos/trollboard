package org.isotopos.trollboard.utils

import grails.converters.JSON
import javax.servlet.http.Cookie

class CallbackController {

  def grailsApplication
  def restGithubClient
  def gitHubCallbackService
  def apiUserProfileService

  def index() {
  	def providerId = params.providerId ?: 'github'
    def profileCookie = g.cookie(name: 'trollboard-profile')
    def trollboardProfile
    if (profileCookie) {
      trollboardProfile = JSON.parse(profileCookie.toString())
    } else {
      def providerToken = getProviderToken(params)
      trollboardProfile = ([provider_id: providerId] + providerToken) as JSON
      profileCookie = new Cookie('trollboard-profile', trollboardProfile.toString())
      response.addCookie(profileCookie)
    }

    def profile = apiUserProfileService.getUserProfile(trollboardProfile.provider_id, trollboardProfile.access_token)
    flash.profile = profile
  	redirect controller: 'start', id: profile.username
  }

  Map getProviderToken(params) {
    def config = grailsApplication?.config

    def query = [client_id: config?.client.id,
        client_secret: config?.client.secret,
        state: params.state,
        code: params.code]

    def resp = restGithubClient.post(path: config?.github.uri.login.oauth){
      json query
    }

    resp.json
  }

  def receive(){
    def providerId = params?.providerId ?: "github"
    def tokenProvider = params?.providerToken
    def payload = JSON.parse(params?.payload)
    def project = gitHubCallbackService.processPayload(tokenProvider,providerId,payload)
    render((project ?: [:]) as JSON)
  }
}
