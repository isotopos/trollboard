package org.isotopos.trollboard.api.service

import org.eclipse.egit.github.core.User
import org.eclipse.egit.github.core.client.GitHubClient
import org.eclipse.egit.github.core.service.UserService
import org.isotopos.trollboard.api.UserProfile

class GitHubUserProfileService implements UserProfileService {

  UserProfile getUserProfile(String token) {
    GitHubClient client = new GitHubClient()
    client.setOAuth2Token(token)
    UserService userService = new UserService(client)
    User user = userService.getUser()

    UserProfile userProfile = new UserProfile()
    userProfile.avatar = user.avatarUrl
    userProfile.isOrganization = user.type == User.TYPE_ORG
    userProfile.name = user.name
    userProfile.resourceUri = user.htmlUrl
    userProfile.username = user.login

    userProfile
  }

}
