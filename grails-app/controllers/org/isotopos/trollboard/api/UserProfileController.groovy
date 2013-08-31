package org.isotopos.trollboard.api

import grails.converters.JSON

class UserProfileController {
  static allowedMethods = [
    userProfile: 'GET',
    projectIssues: 'GET',
    addDefaultLabels: 'POST',
    projects: 'GET',
    organizations: 'GET',
    projectMilestones: 'GET',
    issuesByOrganization: 'GET',

  ]
  def apiUserProfileService

  def userProfile() {
    def providerId = params.providerId
    def tokenProvider = params.providerToken

    def profile = apiUserProfileService.getUserProfile(providerId, tokenProvider)
    render((profile ?: [:]) as JSON)
  }

  def projects() {
    def providerId = params.providerId
    def tokenProvider = params.providerToken

    def projects = apiUserProfileService.getProjects(providerId, tokenProvider)
    render((projects ?: [:]) as JSON)
  }

  def organizations() {
    def providerId = params.providerId
    def tokenProvider = params.providerToken

    def projects = apiUserProfileService.getTeams(providerId, tokenProvider)
    render((projects ?: [:]) as JSON)
  }

  def projectIssues() {
    def providerId = params.providerId
    def tokenProvider = params.providerToken
    def projectId = params.projectId

    def issues = apiUserProfileService.getIssues(providerId, tokenProvider, projectId)
    render((issues ?: [:]) as JSON)
  }

  def projectMilestones() {
    def providerId = params.providerId
    def tokenProvider = params.providerToken
    def projectId = params.projectId
    def organizationId = params.organizationId

    def milestones = apiUserProfileService.getMilestones(providerId, tokenProvider, projectId, organizationId)
    render((milestones ?: [:]) as JSON)
  }

  def issuesByOrganization() {
    def providerId = params.providerId
    def tokenProvider = params.providerToken
    def organizationId = params.organizationId

    def issues = apiUserProfileService.getIssuesByOrganization(providerId, tokenProvider, organizationId)
    render((issues ?: [:]) as JSON)
  }

  def addDefaultLabels() {
    def providerId = params.providerId
    def tokenProvider = params.providerToken
    def projectId = params.projectId
    def organizationId = params.organizationId

    apiUserProfileService.createDefaultLabels(providerId, tokenProvider, organizationId, projectId)

    render([done: true] as JSON)
  }

  def labelsByProject() {
    def providerId = params.providerId
    def tokenProvider = params.providerToken
    def projectId = params.projectId
    def organizationId = params.organizationId

    def labels = []

    try {
      labels = apiUserProfileService.getLabels(providerId, tokenProvider, organizationId, projectId)
    } catch (Throwable pedos) {
      render([problemas: ''] as JSON)
      return
    }

    render((labels ?: [:]) as JSON)
  }

  def projectLanes() {
    def providerId = params.providerId
    def tokenProvider = params.providerToken
    def projectId = params.projectId
    def organizationId = params.organizationId

    def result = []

    try {
      result = apiUserProfileService.getLanes(providerId, tokenProvider, organizationId, projectId)
    } catch (Throwable pedos) {
      pedos.printStackTrace()
      pedos.cause?.printStackTrace()
      println pedos.dump()
      render([problemas: ''] as JSON)
      return
    }


    render((result ?: [:]) as JSON)
  }

  def projectLive() {
    def providerId = params.providerId
    def tokenProvider = params.providerToken
    def projectId = params.projectId
    def organizationId = params.organizationId

    try {
      apiUserProfileService.makeLiveProject(providerId, tokenProvider, organizationId, projectId)
    } catch (Throwable pedos) {
      response.status = 400
      render([done: false, message: pedos.message ?: pedos.cause?.message] as JSON)
      return
    }

    render([done: true] as JSON)
  }

  def changeIssueLane() {
    def providerId = params.providerId
    def tokenProvider = params.providerToken
    def projectId = params.projectId
    def organizationId = params.organizationId

    def issueId = params.issueId
    def laneId = params.laneId

    try {
      apiUserProfileService.changeIssueLane(providerId, tokenProvider, organizationId, projectId, issueId, laneId)
    } catch (Throwable pedos) {
      response.status = 400
      render([done: false, message: pedos.message ?: pedos.cause?.message] as JSON)
      return
    }

    render([done: true] as JSON)
  }

  def assignToMe(){
    def providerId = params.providerId
    def tokenProvider = params.providerToken
    def organizationId = params.organizationId
    def projectId = params.projectId
    def issueId = params.issueId
    apiUserProfileService.changeIssueAssigneeToMe(providerId, tokenProvider, organizationId, projectId, issueId)
    render([:] as JSON)
  }
}
