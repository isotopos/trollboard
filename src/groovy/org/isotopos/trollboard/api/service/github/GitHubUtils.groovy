package org.isotopos.trollboard.api.service.github

import org.eclipse.egit.github.core.Repository
import org.eclipse.egit.github.core.User
import org.isotopos.trollboard.api.Project
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

  static Project fromGitHubRepository(Repository repository) {
    Project project = new Project()
    project.description = repository.description
    project.hasIssues = repository.hasIssues
    project.id = repository.id
    project.language = repository.language
    project.name = repository.name
    project.owner = fromGitHubUserProfile(repository.owner)
    project.website = repository.htmlUrl

    project
  }
}
