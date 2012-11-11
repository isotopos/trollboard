package org.isotopos.trollboard

class StartController {

  def apiUserProfileService

  def profile() {
    def trollboardProfile = session.trollboardProfile
    def profile = apiUserProfileService.getUserProfile(trollboardProfile.provider_id, trollboardProfile.access_token)

    [profile: profile]
  }
}
