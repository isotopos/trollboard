package org.isotopos.trollboard.utils

class CallbackController {

  def grailsApplication
  def restGithubClient

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
}
