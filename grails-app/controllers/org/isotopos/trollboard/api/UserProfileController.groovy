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

}
