package org.isotopos.trollboard.api.service

import org.eclipse.egit.github.core.client.GitHubClient
import org.isotopos.trollboard.api.Project
import org.isotopos.trollboard.api.service.github.GitHubUtils

class GitHubRepositoryService implements RepositoryService {

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
