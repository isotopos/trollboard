package org.isotopos.trollboard.utils

import grails.converters.JSON

class CallbackController {

  def grailsApplication
  def restGithubClient
  def gitHubCallbackService

  def index() {
    params.remove 'controller'
    params << getProviderToken(params)
    session.trollboardProfile = params

    redirect controller: 'start', action: 'profile'
  }

  Map getProviderToken(params) {
    def config = grailsApplication?.config

    def query = [client_id: config?.client.id,
        client_secret: config?.client.secret,
        state: params.state,
        code: params.code]

    def resp = restGithubClient.post(path: config?.github.uri.login.oauth) {
      json query
    }

    resp.json
  }

  def receive() {
    def providerId = params?.providerId ?: "github"
    def tokenProvider = params?.providerToken
    def payload = JSON.parse(params?.payload)
    def project = gitHubCallbackService.processPayload(tokenProvider, providerId, payload)
    render((project ?: [:]) as JSON)
  }
}
