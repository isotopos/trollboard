package org.isotopos.trollboard

class StartController {

  def apiUserProfileService

  def profile() {
    def trollboardProfile = session.trollboardProfile
    println "Trollboard Profile: $trollboardProfile"
    def profile = apiUserProfileService.getUserProfile(trollboardProfile.provider_id, trollboardProfile.access_token)
    println "PROFILE STARTS: ${profile}"
    [profile: profile]
  }
}
