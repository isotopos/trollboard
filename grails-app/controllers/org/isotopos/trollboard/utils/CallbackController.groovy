package org.isotopos.trollboard.utils

import grails.converters.JSON
import org.isotopos.trollboard.Project

class CallbackController {

  def grailsApplication
  def restGithubClient
  def gitHubCallbackService

  def index() {
  	params.remove 'controller'
    params << getProviderToken(params)
    session.user = [github: params]

  	redirect uri: '/app/www'
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
    println params
    def payload = JSON.parse(params?.payload)
    def project = gitHubCallbackService.processPayload(payload)
    render((project ?: [:]) as JSON)
  }
}
