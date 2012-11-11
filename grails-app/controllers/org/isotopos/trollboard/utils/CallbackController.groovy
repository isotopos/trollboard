package org.isotopos.trollboard.utils

import grails.converters.JSON
import org.isotopos.trollboard.Project

class CallbackController {

  def grailsApplication
  def restGithubClient
  def commitService
  def gitHubIssuesService

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
    println "\n\n\n"
    println payload
    //def providerId = params?.providerId
    def providerId = "github"
    println "REPO: " + payload.repository.name
    Project project = Project.findByProviderIdAndProjectId(providerId,payload.repository.name)
    if(project){
      payload?.commits?.each{ commit ->
        def actions = commitService.receiveAndProcessMessage(commit.message)
        println actions
        actions.each{ action ->
          println action
          action.each { k,v ->
            println "$k : $v"
            v.each { issueNumber ->
              println "Set the label $k to $issueNumber"
              gitHubIssuesService.addLabelToIssue(
                project?.token,
                payload?.repository?.owner?.name,
                payload?.repository?.name,
                issueNumber.replace("#","").trim(),k)
            }
          }
        }
      }
      render project as JSON
    }
    else 
      render [:] as JSON
  }
}
