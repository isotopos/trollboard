package org.isotopos.trollboard

class StartController {

  def apiUserProfileService

  def profile() {
    def trollboardProfile = session.trollboardProfile

    if (!trollboardProfile) {
      redirect uri: '/'
      return
    }

    def providerId = trollboardProfile.provider_id
    def accessToken = trollboardProfile.access_token

    def profile = apiUserProfileService.getUserProfile(providerId, accessToken)
    def projects = apiUserProfileService.getProjects(providerId, accessToken)
    def organizations = apiUserProfileService.getTeams(providerId, accessToken)

    session.trollboardProfile.ownerId = profile.username

    [profile: profile, projects: projects, organizations: organizations]
  }
}
