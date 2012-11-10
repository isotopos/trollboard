package org.isotopos.trollboard.api.service

import org.eclipse.egit.github.core.client.GitHubClient
import org.isotopos.trollboard.api.Project

class GitHubRepositoryService implements RepositoryService {

  List<Project> getProjects(String token) {
    GitHubClient client = new GitHubClient()
    client.setOAuth2Token(token)

    org.eclipse.egit.github.core.service.RepositoryService repositoryService = new org.eclipse.egit.github.core.service.RepositoryService(client)
    java.util.List<org.eclipse.egit.github.core.Repository> repositories = repositoryService.getRepositories(client.getUser())

    /*

   private boolean hasIssues;
 private boolean hasWiki;
 @com.google.gson.annotations.SerializedName("private")
 private boolean isPrivate;
 private java.util.Date createdAt;
 private java.util.Date pushedAt;
 private java.util.Date updatedAt;
 private int forks;
 private long id;
 private int openIssues;
 private int size;
 private int watchers;
 private org.eclipse.egit.github.core.Repository parent;
 private org.eclipse.egit.github.core.Repository source;
 private java.lang.String cloneUrl;
 private java.lang.String description;
 private java.lang.String homepage;
 private java.lang.String gitUrl;
 private java.lang.String htmlUrl;
 private java.lang.String language;
 private java.lang.String masterBranch;
 private java.lang.String mirrorUrl;
 private java.lang.String name;
 private java.lang.String sshUrl;
 private java.lang.String svnUrl;
 private java.lang.String url;
 private org.eclipse.egit.github.core.User owner;

    */

    return null
  }
}
