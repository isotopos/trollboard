package org.isotopos.trollboard.api.service

import org.eclipse.egit.github.core.client.GitHubClient
import org.eclipse.egit.github.core.service.UserService
import org.isotopos.trollboard.api.Issue
import org.isotopos.trollboard.api.Project
import org.isotopos.trollboard.api.Lane
import org.isotopos.trollboard.api.service.github.GitHubUtils
import org.eclipse.egit.github.core.RepositoryId

class GitHubIssuesService implements IssuesService {
  def gitHubOrganizationService
  def gitHubRepositoryService

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
    GitHubClient client = new GitHubClient()
    client.setOAuth2Token(token)

    org.eclipse.egit.github.core.service.IssueService issueService = new org.eclipse.egit.github.core.service.IssueService(client)
    org.eclipse.egit.github.core.service.LabelService labelService = new org.eclipse.egit.github.core.service.LabelService(client)

    //def issue = issueService.getIssue(owner,repoId,issueId)
    def issue = issueService.getIssue(owner,repoId,issueId)
    //def labelsInRepo = labelService.getLabels(owner, repoId)
    def labelsInRepo = labelService.getLabels(owner, repoId)    
    def labelsFromIssue = issue.labels
    def lanesFromIssue = labelsFromIssue*.name.find { labelName -> labelName.contains("\$") }
    String lane = labelsInRepo*.name.find{ labelName -> labelName.toUpperCase().startsWith(label+'\$') }
    if(lane){
      def lanePriorityFromCommit = obtenerPrioridad(lane)
      def lanePriorityFromIssue = obtenerPrioridad(lanesFromIssue)

      if( lanePriorityFromIssue > lanePriorityFromCommit){
        def labels = []
        labels << labelsFromIssue*.name.find { labelName -> !labelName.contains("\$") }
        labels << (label+'\$'+lanePriorityFromCommit)
        labelService.setLabels(owner, repoId, issueId, labels)
      }
    }
  }

  def obtenerPrioridad(lane) {
    lane.substring(lane.indexOf('\$'), lane.length()).replace('\$','')
  }
}
