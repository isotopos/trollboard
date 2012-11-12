package org.isotopos.trollboard.api.service

import org.isotopos.trollboard.Project

class GitHubCallbackService implements CallbackService {

  def gitHubIssuesService
  def commitService

  Project processPayload(payload) {
    Project project = Project.findByProjectId(payload.repository.name)
    if(project){
      payload?.commits?.each{ commit ->
        def actions = commitService.receiveAndProcessMessage(commit.message)
        actions.each{ action ->
          action.each { k,v ->
            v.each { issueNumber ->
              gitHubIssuesService.addLabelToIssue(
                project?.token ?: tokenProvider,
                payload?.repository?.owner?.name,
                payload?.repository?.name,
                issueNumber.replace("#","").trim(),k)
            }
          }
        }
      }
    }
    project
  }
}
