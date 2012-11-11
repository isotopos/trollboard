package org.isotopos.trollboard.api.service

import org.eclipse.egit.github.core.client.GitHubClient
import org.eclipse.egit.github.core.service.UserService
import org.isotopos.trollboard.api.Issue
import org.isotopos.trollboard.api.Project
import org.isotopos.trollboard.api.service.github.GitHubUtils
import org.eclipse.egit.github.core.RepositoryId

class GitHubIssuesService implements IssuesService {
  def gitHubOrganizationService

  List<Issue> getIssuesByOrganization(String token, String organizationId) {
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

  List<Issue> getIssues(String token, String projectId) {
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

  void addLabelToIssue(String token, String owner, String repoId, String issueId, String label){
    println token
    println owner
    println repoId
    println issueId
    println labels
    GitHubClient client = new GitHubClient()
    client.setOAuth2Token(token)

    org.eclipse.egit.github.core.service.IssueService issueService = new org.eclipse.egit.github.core.service.IssueService(client)
    org.eclipse.egit.github.core.service.LabelService labelService = new org.eclipse.egit.github.core.service.LabelService(client)

    def issue = issueService.getIssue(owner,repoId,issueId)
    println issue.dump()

    def labelsInRepo = labelService.getLabels(owner, repoId)
    println labelsInRepo
    
    def labelsFromIssue = issue.labels
    println labelsFromIssue

    def labelsWithNoPrice = labelsFromIssue*.name.findAll { labelName -> !labelName.contains("\$") }
    println labelsWithNoPrice

    def labelToAdd = labelsInRepo*.name.find { labelName -> labelName.toUpperCase().startsWith(label+'\$') }

    def newLabels = labelsWithNoPrice + labelToAdd
    println newLabels

    
  }
}
