package org.isotopos.trollboard.api

import grails.converters.JSON

class UserProfileController {
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

  def issuesByOrganization() {
    def providerId = params.providerId
    def tokenProvider = params.providerToken
    def organizationId = params.organizationId

    def issues = apiUserProfileService.getIssuesByOrganization(providerId, tokenProvider, organizationId)
    render((issues ?: [:]) as JSON)
  }

}
