package org.isotopos.trollboard.api.service

import org.eclipse.egit.github.core.client.GitHubClient
import org.eclipse.egit.github.core.service.UserService
import org.isotopos.trollboard.api.Issue
import org.isotopos.trollboard.api.Project
import org.isotopos.trollboard.api.service.github.GitHubUtils
import org.eclipse.egit.github.core.RepositoryId

class GitHubIssuesService implements IssuesService {
  def gitHubOrganizationService

  List<Issue> getIssuesByOrganization(String token, String organizationId) throws Exception  {
    def issues = []
    GitHubClient client = new GitHubClient()
    client.setOAuth2Token(token)
    org.eclipse.egit.github.core.service.IssueService issueService = new org.eclipse.egit.github.core.service.IssueService(client)

    def organizations = gitHubOrganizationService.getOrganizations(token).findAll {
      it.user.username == organizationId
    }

    organizations.each {
      def projects = it.getProjects()
      projects.each {Project project ->
        RepositoryId repositoryId = new RepositoryId(it.user.username, project.name)
        def isssues = issueService.getIssues(repositoryId, [:])
        isssues.each {gitHubIssue ->
          issues << GitHubUtils.fromGitHubIssue(gitHubIssue)
        }
      }
    }

    issues
  }

  List<Issue> getIssues(String token, String projectId) throws Exception  {
    GitHubClient client = new GitHubClient()
    client.setOAuth2Token(token)

    org.eclipse.egit.github.core.service.IssueService issueService = new org.eclipse.egit.github.core.service.IssueService(client)

    UserService userService = new UserService(client)
    String userId = userService.getUser().login

    def isssues = issueService.getIssues(userId, projectId, [:])
    def issues = []
    isssues.each {
      issues << GitHubUtils.fromGitHubIssue(it)
    }

    issues
  }

  void addLabelToIssue(String token, String owner, String repoId, String issueId, String label) throws Exception {
    GitHubClient client = new GitHubClient()
    client.setOAuth2Token(token)

    org.eclipse.egit.github.core.service.IssueService issueService = new org.eclipse.egit.github.core.service.IssueService(client)
    org.eclipse.egit.github.core.service.LabelService labelService = new org.eclipse.egit.github.core.service.LabelService(client)

    //def issue = issueService.getIssue(owner,repoId,issueId)
    def issue
    try {
      issue = issueService.getIssue(owner,repoId,issueId)
    } catch(Throwable pedosEnElissue) {
      throw new RuntimeException("Can't get the issue ${issueId} from owner ${owner} and repo ${repoId}", pedosEnElissue)
    }

    //def labelsInRepo = labelService.getLabels(owner, repoId)
    def labelsInRepo = labelService.getLabels(owner, repoId)
    
    def labelsFromIssue = issue.labels

    def labelsWithNoPrice = labelsFromIssue*.name.findAll { labelName -> !labelName.contains("\$") }

    def labelToAdd = labelsInRepo*.name.find { labelName -> labelName.toUpperCase().startsWith((label+'\$').toUpperCase()) }

    def newLabels = labelsWithNoPrice + labelToAdd

    labelService.setLabels(owner, repoId,issueId, newLabels)
  }

  void addLabelToIssueUsingRepository(String token, String owner, String repoId, String issueId, String label) throws Exception {
    GitHubClient client = new GitHubClient()
    client.setOAuth2Token(token)

    org.eclipse.egit.github.core.service.IssueService issueService = new org.eclipse.egit.github.core.service.IssueService(client)
    org.eclipse.egit.github.core.service.LabelService labelService = new org.eclipse.egit.github.core.service.LabelService(client)

    UserService userService = new UserService(client)
    if (!owner) {
      owner = userService.getUser().login
    }

    RepositoryId repositoryId = new RepositoryId(owner, repoId)

    def issue
    try {
      issue = issueService.getIssue(repositoryId,issueId)
    } catch(Throwable pedosEnElissue) {
      throw new RuntimeException("Can't get the issue ${issueId} from owner ${owner} and repo ${repoId}", pedosEnElissue)
    }

    def labelsInRepo = labelService.getLabels(repositoryId)
    def labelsFromIssue = issue.labels
    def labelsWithNoPrice = labelsFromIssue*.name.findAll { labelName -> !labelName.contains("\$") }
    def labelToAdd = labelsInRepo*.name.find { labelName -> labelName.toUpperCase().startsWith((label+'\$').toUpperCase()) }
    def newLabels = labelsWithNoPrice + labelToAdd

    labelService.setLabels(repositoryId, issueId, newLabels)
  }
}
