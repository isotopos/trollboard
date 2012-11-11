package org.isotopos.trollboard.utils

import grails.converters.JSON
import javax.servlet.http.Cookie

class CallbackController {

  def grailsApplication
  def restGithubClient
  def gitHubCallbackService

  def index() {
  	def providerId = params.providerId ?: 'github'
    def profileCookie = g.cookie('trollboard-profile')
    def profile
    if (profileCookie) {
      profile = JSON.parse(profileCookie.toString())
    } else {
      def providerToken = getProviderToken(params)
      profile = ([providerId: providerId] + providerToken) as JSON
      profileCookie = new Cookie('trollboard-profile', profile.toString())
      response.addCookie(profileCookie)
    }

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
