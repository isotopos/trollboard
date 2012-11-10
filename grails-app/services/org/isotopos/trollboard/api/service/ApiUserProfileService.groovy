package org.isotopos.trollboard.api.service

import org.isotopos.trollboard.api.Project
import org.isotopos.trollboard.api.UserProfile

class ApiUserProfileService {
  def gitHubUserProfileService
  def gitHubRepositoryService
  def gitHubOrganizationService

  UserProfile getUserProfile(String providerId, String token) {
    if (providerId == 'github') {
      return gitHubUserProfileService.getUserProfile(token)
    }

    return null
  }

  List<Project> getProjects(String providerId, String token) {
    if (providerId == 'github') {
      return gitHubRepositoryService.getProjects(token)
    }

    null
  }

  List<Project> getTeams(String providerId, String token) {
    if (providerId == 'github') {
      return gitHubOrganizationService.getOrganizations(token)
    }

    null
  }
}
