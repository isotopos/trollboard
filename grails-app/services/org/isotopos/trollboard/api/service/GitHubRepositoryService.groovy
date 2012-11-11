package org.isotopos.trollboard.api.service

import org.eclipse.egit.github.core.RepositoryId
import org.eclipse.egit.github.core.client.GitHubClient
import org.eclipse.egit.github.core.service.LabelService
import org.eclipse.egit.github.core.service.UserService
import org.isotopos.trollboard.api.Project
import org.isotopos.trollboard.api.service.github.GitHubUtils
import org.isotopos.trollboard.api.Milestone
import org.eclipse.egit.github.core.service.MilestoneService

class GitHubRepositoryService implements RepositoryService {
  def defaultLabels = [
    [color: 'cccccc', name: 'todo$0'],
    [color: 'cccccc', name: 'doing$1'],
    [color: 'cccccc', name: 'test$2'],
    [color: 'cccccc', name: 'qa$3'],
    [color: 'cccccc', name: 'live$4'],
  ]

  void addDefaultLabels(String token, String user, String proyectId) {
    GitHubClient client = new GitHubClient()
    client.setOAuth2Token(token)

    LabelService labelService = new LabelService(client)

    UserService userService = new UserService(client)
    if (!user) {
      user = userService.getUser().login
    }

    RepositoryId repositoryId = new RepositoryId(user, proyectId)

    defaultLabels.each {
      org.eclipse.egit.github.core.Label label = new org.eclipse.egit.github.core.Label()
      label.color = it.color
      label.name = it.name
      label.url = "https://api.github.com/repos/${user}/${proyectId}/labels/${label.name}"

      try {
        labelService.createLabel(repositoryId, label)
      } catch (Throwable t) {
        log.error("error creating label ${label.dump()}", t)
      }
    }


  }

  List<Milestone> getMilestones(String token, String user, String projectId) {
    def result = []
    GitHubClient client = new GitHubClient()
    client.setOAuth2Token(token)

    UserService userService = new UserService(client)
    if (!user) {
      user = userService.getUser().login
    }
    RepositoryId repositoryId = new RepositoryId(user, projectId)

    MilestoneService milestoneService = new MilestoneService(client)
    def ghmilestones = milestoneService.getMilestones(repositoryId, null)

    ghmilestones.each {
      result << GitHubUtils.fromGitHubMilestone(it)
    }

    result
  }

  List<Project> getProjects(String token) {
    GitHubClient client = new GitHubClient()
    client.setOAuth2Token(token)

    org.eclipse.egit.github.core.service.RepositoryService repositoryService = new org.eclipse.egit.github.core.service.RepositoryService(client)
    java.util.List<org.eclipse.egit.github.core.Repository> repositories = repositoryService.getRepositories(client.getUser())

    def projects = []
    repositories.each {
      projects << GitHubUtils.fromGitHubRepository(it)
    }

    projects
  }
}
