package org.isotopos.trollboard.api.service

import org.eclipse.egit.github.core.client.GitHubClient
import org.eclipse.egit.github.core.service.UserService
import org.isotopos.trollboard.api.UserProfile
import org.isotopos.trollboard.api.service.github.GitHubUserProfileUtil

class GitHubUserProfileService implements UserProfileService {

  UserProfile getUserProfile(String token) {
    GitHubClient client = new GitHubClient()
    client.setOAuth2Token(token)
    UserService userService = new UserService(client)
    GitHubUserProfileUtil.fromGitHubUserProfile(userService.getUser())
  }

}
