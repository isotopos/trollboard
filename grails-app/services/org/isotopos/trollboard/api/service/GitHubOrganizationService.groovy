package org.isotopos.trollboard.api.service

import org.eclipse.egit.github.core.client.GitHubClient
import org.eclipse.egit.github.core.service.RepositoryService
import org.isotopos.trollboard.api.Organization
import org.isotopos.trollboard.api.service.github.GitHubUtils

class GitHubOrganizationService implements OrganizationService {

  List<Organization> getOrganizations(String token) {
    GitHubClient client = new GitHubClient()
    client.setOAuth2Token(token)

    org.eclipse.egit.github.core.service.OrganizationService organizationService = new org.eclipse.egit.github.core.service.OrganizationService(client)

    RepositoryService repositoryService = new RepositoryService(client)
    List<org.eclipse.egit.github.core.User> organizations = organizationService.getOrganizations()

    def orgs = []

    organizations.each {user ->
      Organization org = new Organization()
      org.user = GitHubUtils.fromGitHubUserProfile(user)

      def losRepos = []

      repositoryService.getOrgRepositories(org.user.username).each { repo ->
        losRepos << GitHubUtils.fromGitHubRepository(repo)
      }

      org.projects = losRepos

      orgs << org
    }
    return orgs
  }
}
