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

    def projects
    try{
      projects = apiUserProfileService.getProjects(providerId, accessToken)
    }catch (e){
      log.error("ERROR get projects", e)
    }
    def organizations
    try{
      organizations= apiUserProfileService.getTeams(providerId, accessToken)
    }catch (e){
      log.error("ERROR get organizations", e)
    }

    session.trollboardProfile.ownerId = profile.username

    [profile: profile, projects: projects, organizations: organizations]
  }
}
