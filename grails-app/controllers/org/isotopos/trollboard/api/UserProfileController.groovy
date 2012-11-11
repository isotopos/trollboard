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

}
