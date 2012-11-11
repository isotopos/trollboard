package org.isotopos.trollboard.api.service

import org.eclipse.egit.github.core.RepositoryId
import org.eclipse.egit.github.core.client.GitHubClient
import org.eclipse.egit.github.core.service.LabelService
import org.eclipse.egit.github.core.service.MilestoneService
import org.eclipse.egit.github.core.service.UserService
import org.isotopos.trollboard.api.Label
import org.isotopos.trollboard.api.Lane
import org.isotopos.trollboard.api.Milestone
import org.isotopos.trollboard.api.Project
import org.isotopos.trollboard.api.service.github.GitHubUtils

class GitHubRepositoryService implements RepositoryService {
  def defaultLabels = [
    [color: '7B1E8A', name: 'Backlog$0'],
    [color: '2D2BFA', name: 'Ready$1'],
    [color: '36DA52', name: 'Coding$2'],
    [color: 'C5AAFC', name: 'Testing$3'],
    [color: 'BFE47A', name: 'Approval$4'],
    [color: '070653', name: 'Done$5'],
    [color: '00901A', name: 'Live$6'],
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

  List<Label> getLabels(String token, String user, String projectId) {
    def result = []

    GitHubClient client = new GitHubClient()
    client.setOAuth2Token(token)

    LabelService labelService = new LabelService(client)

    UserService userService = new UserService(client)
    if (!user) {
      user = userService.getUser().login
    }
    RepositoryId repositoryId = new RepositoryId(user, projectId)

    def ghLabels = labelService.getLabels(repositoryId)

    ghLabels.each {
      result << GitHubUtils.fromGitHubLabel(it)
    }

    result
  }

  List<Lane> getLanes(String token, String user, String projectId) {
    def result = []

    def labels = this.getLabels(token, user, projectId)

    labels.each {

      Integer order
      Label label = it
      String name = label.name
      if (name.contains('$')) {
        def tokens = name.tokenize('$')
        if (tokens.size() == 2) {
          if (tokens.get(1).isInteger()) {
            order = tokens.get(1).toInteger()
            name = tokens.get(0)
            result << new Lane(name: name, order: order, label: label)
          }
        }
      }
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
