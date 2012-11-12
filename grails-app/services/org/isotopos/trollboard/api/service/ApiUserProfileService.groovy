package org.isotopos.trollboard.api.service

import org.isotopos.trollboard.api.*

class ApiUserProfileService {
  def gitHubUserProfileService
  def gitHubRepositoryService
  def gitHubOrganizationService
  def gitHubIssuesService

  UserProfile getUserProfile(String providerId, String token) throws Exception  {
    if (providerId == 'github') {
      return gitHubUserProfileService.getUserProfile(token)
    }

    return null
  }

  List<Project> getProjects(String providerId, String token) throws Exception  {
    if (providerId == 'github') {
      return gitHubRepositoryService.getProjects(token)
    }

    null
  }

  List<Project> getTeams(String providerId, String token) throws Exception  {
    if (providerId == 'github') {
      return gitHubOrganizationService.getOrganizations(token)
    }

    null
  }



  List<Issue> getIssues(String providerId, String token, String project) throws Exception  {
    if (providerId == 'github') {
      return gitHubIssuesService.getIssues(token, project)
    }

    null
  }

  List<Milestone> getMilestones(String providerId, String token, String project, String organizationId) throws Exception  {
    if (providerId == 'github') {
      return gitHubRepositoryService.getMilestones(token, organizationId, project)
    }

    null
  }

  List<Issue> getIssuesByOrganization(String providerId, String token, String organizationId) throws Exception  {
    if (providerId == 'github') {
      return gitHubIssuesService.getIssuesByOrganization(token, organizationId)
    }

    null
  }

  void createDefaultLabels(String providerId, String tokenProvider, String organizationId, String projectId) throws Exception {
    if (providerId == 'github') {
      gitHubRepositoryService.addDefaultLabels(tokenProvider, organizationId, projectId)
    }
  }

  List<Label> getLabels(String providerId, String tokenProvider, String organizationId, String projectId) throws Exception  {
    if (providerId == 'github') {
      return gitHubRepositoryService.getLabels(tokenProvider, organizationId, projectId)
    }

    null
  }

  List<Lane> getLanes(String providerId, String tokenProvider, String organizationId, String projectId) throws Exception {
    if (providerId == 'github') {
      return gitHubRepositoryService.getLanes(tokenProvider, organizationId, projectId)
    }

    null
  }

  void makeLiveProject(String providerId, String tokenProvider, String organizationId, String projectId) throws Exception  {
    if (providerId == 'github') {
      gitHubRepositoryService.createHook(tokenProvider, organizationId, projectId)
    }
  }

  void changeIssueLane(String providerId, String tokenProvider, String organizationId, String projectId, String issueId, String laneId) throws Exception  {
    if (providerId == 'github') {
      gitHubIssuesService.addLabelToIssue(tokenProvider, organizationId, projectId, issueId, laneId)
    }
  }
}
