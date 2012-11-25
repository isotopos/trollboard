package org.isotopos.trollboard

import grails.converters.JSON
import org.isotopos.trollboard.api.Issue
import org.isotopos.trollboard.api.Lane
import org.isotopos.trollboard.api.Milestone

class ProjectController {

  // def scaffold = org.isotopos.trollboard.Project
  def apiUserProfileService

  def board() {
    def trollboardProfile = session.trollboardProfile
    if (!trollboardProfile) {
      redirect uri: '/'
      return
    }

    try {
      def organizationId = params?.organization ?: ''
      def projectId = params.project

      def project = Project.findByProjectIdAndToken(projectId, trollboardProfile.access_token)
      def lanes = projectLanes(projectId, organizationId)
      def issues = projectIssues(projectId,organizationId)
      def lanesIssues = [] as Set
      def model = [:]
      model.lanes = lanes.collect { Lane lane ->
        def laneIssues = issues.findAll { Issue issue ->
          issue.labels*.name.contains(lane.label.name)
        }.sort { Issue issue -> issue.number }
        lanesIssues.addAll(laneIssues)
        [lane: lane, issues: laneIssues]
      }
      if (model.lanes) {
        def defaultLane = model.lanes.first()
        defaultLane.issues.addAll(issues - lanesIssues)
        defaultLane.issues.sort { Issue issue -> issue.number }
      }
      model.milestones = projectMilestones(projectId, organizationId).sort { Milestone milestone -> milestone.dueOn }
      model.name = projectId
      model.project = project
      model.projectId = projectId
      model.view = 'board'
      model.ownerId = organizationId ?: session.trollboardProfile.ownerId
      model
    }catch (e) {
      flash.error =  e.message
      redirect controller: 'start', action: 'profile'
    }
  }

  def createAsync() {
    Project project = new Project(params)
    project.save(flush: true)
    render project as JSON
  }

  private List projectLanes(def projectId, def organizationId) throws Exception  {
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

  private List projectIssues(def projectId,organizationId='') throws Exception  {
    def trollboardProfile = session.trollboardProfile
    def providerId = trollboardProfile.provider_id
    def tokenProvider = trollboardProfile.access_token

    apiUserProfileService.getIssues(providerId, tokenProvider, projectId, organizationId)
  }

  private List projectMilestones(def projectId, def organizationId) throws Exception  {
    def trollboardProfile = session.trollboardProfile
    def providerId = trollboardProfile.provider_id
    def tokenProvider = trollboardProfile.access_token

    apiUserProfileService.getMilestones(providerId, tokenProvider, projectId, organizationId)
  }
}
