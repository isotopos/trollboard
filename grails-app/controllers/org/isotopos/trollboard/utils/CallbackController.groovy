package org.isotopos.trollboard.utils

import org.isotopos.trollboard.Project

class CallbackController {

  def commitService

  def index() {
  	params.remove 'controller'
  	session.user = params
  	println session.user
  	redirect uri: '/app/www'
  }

  def receive(){
    println params
    def payload = params.payload
    def providerId = params.providerId
    Project project = Project.findByProviderIdAndProjectId(providerId,payload.repository.name)
    if(project){
      payload.commits.each{ commit ->
        def actions = commitService.receiveAndProcessMessage(commit.message)
        // TODO: Call the issueService to add labels to issues
      }
    }
  }
}
