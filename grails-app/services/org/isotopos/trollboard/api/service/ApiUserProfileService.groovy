package org.isotopos.trollboard.api.service

import org.isotopos.trollboard.api.UserProfile

class ApiUserProfileService  {
  def gitHubUserProfileService

  UserProfile getUserProfile(String providerId, String token) {
    if(providerId == 'github') {
      return gitHubUserProfileService.getUserProfile(token)
    }

    return null
  }
}
