package org.isotopos.trollboard

import grails.converters.JSON
import org.isotopos.trollboard.api.Issue
import org.isotopos.trollboard.api.Lane

class ProjectController {

  // def scaffold = org.isotopos.trollboard.Project
  def apiUserProfileService

  def board() {
    def organizationId = params.organization
    def projectId = params.project

    def lanes = projectLanes(projectId, organizationId)
    def issues = projectIssues(projectId)
    def model = [:]
    model.lanes = lanes.collect { Lane lane ->
      [lane: lane, issues: issues.findAll {
        Issue issue -> issue.labels*.name.contains(lane.label.name)
      }.sort { Issue issue -> issue.number }]
    }
    model.milestones = projectMilestones(projectId, organizationId)
    model
  }

  def createAsync() {
    Project project = new Project(params)
    project.save(flush: true)
    render project as JSON
  }

  private List projectLanes(def projectId, def organizationId) {
    def trollboardProfile = session.trollboardProfile
    def providerId = trollboardProfile.provider_id
    def tokenProvider = trollboardProfile.access_token

    def lanes = apiUserProfileService.getLanes(providerId, tokenProvider, organizationId, projectId)
    if (!lanes) {
      apiUserProfileService.createDefaultLabels(providerId, tokenProvider, organizationId, projectId)
      lanes = apiUserProfileService.getLanes(providerId, tokenProvider, organizationId, projectId)
    }
    lanes.sort { Lane lane -> lane.order }
  }

  private List projectIssues(def projectId) {
    def trollboardProfile = session.trollboardProfile
    def providerId = trollboardProfile.provider_id
    def tokenProvider = trollboardProfile.access_token

    apiUserProfileService.getIssues(providerId, tokenProvider, projectId)
  }

  private List projectMilestones(def projectId, def organizationId) {
    def trollboardProfile = session.trollboardProfile
    def providerId = trollboardProfile.provider_id
    def tokenProvider = trollboardProfile.access_token

    apiUserProfileService.getMilestones(providerId, tokenProvider, projectId, organizationId)
  }
}
