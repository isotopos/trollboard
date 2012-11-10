package org.isotopos.trollboard.api.service.github

import org.eclipse.egit.github.core.User
import org.isotopos.trollboard.api.UserProfile

/**
 * Created with IntelliJ IDEA.
 * User: domix
 * Date: 10/11/12
 * Time: 14:53
 * To change this template use File | Settings | File Templates.
 */
class GitHubUtils {
  static UserProfile fromGitHubUserProfile(User user) {
    UserProfile userProfile = new UserProfile()
    userProfile.avatar = user.avatarUrl
    userProfile.isOrganization = user.type == User.TYPE_ORG
    userProfile.name = user.name
    userProfile.resourceUri = user.htmlUrl
    userProfile.username = user.login

    userProfile
  }
}
