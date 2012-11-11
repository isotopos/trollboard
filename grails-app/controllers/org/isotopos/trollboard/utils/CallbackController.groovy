package org.isotopos.trollboard.utils

class CallbackController {

  def commitService

  def index() {
  	params.remove 'controller'
  	session.user = params
    def payload = params.payload
    def providerId = params.providerId
    Project project = Project.findByProviderIdAndProjectId(providerId,payload.repository.name)
    if(project){
      // TODO: Process commit
      payload.commits.each{ commit ->
        def actions = commitService.receiveAndProcessMessage(commit.message)
      }
    }
  	println session.user
  	redirect uri: '/app/www'
  }
}
