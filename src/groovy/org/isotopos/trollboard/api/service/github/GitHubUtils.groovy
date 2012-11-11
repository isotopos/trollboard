package org.isotopos.trollboard.api.service.github

import org.eclipse.egit.github.core.Repository
import org.eclipse.egit.github.core.User
import org.isotopos.trollboard.api.*

/**
 * Created with IntelliJ IDEA.
 * User: domix
 * Date: 10/11/12
 * Time: 14:53
 * To change this template use File | Settings | File Templates.
 */
class GitHubUtils {
  static UserProfile fromGitHubUserProfile(User user) {
    if (user) {
      UserProfile userProfile = new UserProfile()
      userProfile.avatar = user.avatarUrl
      userProfile.isOrganization = user.type == User.TYPE_ORG
      userProfile.name = user.name
      userProfile.resourceUri = user.htmlUrl
      userProfile.username = user.login

      userProfile
    } else {
      null
    }

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

  static Label fromGitHubLabel(org.eclipse.egit.github.core.Label gitHubLabel) {
    def label = new Label()

    label.color = gitHubLabel.color
    label.name = gitHubLabel.name
    label.url = gitHubLabel.url

    label
  }

  static List<Label> fromGitHubLabels(List<org.eclipse.egit.github.core.Label> gitHubLabels) {
    def labels = []
    gitHubLabels.each {
      labels << fromGitHubLabel(it)
    }

    labels
  }

  static Issue fromGitHubIssue(org.eclipse.egit.github.core.Issue gitHubIssue) {
    def issue = new Issue()
    issue.body = gitHubIssue.body
    issue.assignee = fromGitHubUserProfile(gitHubIssue.assignee)
    issue.bodyHtml = gitHubIssue.bodyHtml
    issue.bodyText = gitHubIssue.bodyText
    issue.closedAt = gitHubIssue.closedAt
    issue.comments = gitHubIssue.comments
    issue.createdAt = gitHubIssue.createdAt
    issue.htmlUrl = gitHubIssue.htmlUrl
    issue.id = gitHubIssue.id
    issue.number = gitHubIssue.number
    issue.state = gitHubIssue.state
    issue.title = gitHubIssue.title
    issue.updatedAt = gitHubIssue.updatedAt
    issue.url = gitHubIssue.url
    issue.user = fromGitHubUserProfile(gitHubIssue.user)
    issue.labels = fromGitHubLabels(gitHubIssue.labels)

    issue.milestone = fromGitHubMilestone(gitHubIssue.milestone)

    issue
  }

  static Milestone fromGitHubMilestone(org.eclipse.egit.github.core.Milestone gitHubMilestone) {
    if (gitHubMilestone) {
      def milestone = new Milestone()
      milestone.closedIssues = gitHubMilestone.closedIssues
      milestone.createdAt = gitHubMilestone.createdAt
      milestone.creator = fromGitHubUserProfile(gitHubMilestone.creator)
      milestone.description = gitHubMilestone.description
      milestone.dueOn = gitHubMilestone.dueOn
      milestone.number = gitHubMilestone.number
      milestone.openIssues = gitHubMilestone.openIssues
      milestone.state = gitHubMilestone.state
      milestone.title = gitHubMilestone.title
      milestone.url = gitHubMilestone.url

      return milestone
    } else {
      return null
    }


  }
}
