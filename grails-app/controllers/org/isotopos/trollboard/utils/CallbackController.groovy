package org.isotopos.trollboard.utils

import org.isotopos.trollboard.Project
import grails.converters.JSON

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
    def payload = JSON?.parse(params?.payload)
    println "\n\n\n"
    println payload
    def providerId = params?.providerId
    //Project project = Project.findByProviderIdAndProjectId(providerId,payload.repository.name)
    //if(project){
      payload?.commits?.each{ commit ->
        def actions = commitService.receiveAndProcessMessage(commit.message)
        println actions
        actions.each{ action ->
          println action
          action.each { k,v ->
            println "$k : $v"
            v.each { issueNumber ->
            // Set the label k to issueNumber
            }
          }
        }
      }
    //}
    render params as JSON
  }
}
