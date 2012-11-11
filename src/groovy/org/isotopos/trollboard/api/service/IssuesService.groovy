package org.isotopos.trollboard.api.service

import org.isotopos.trollboard.api.Issue

/**
 * Created with IntelliJ IDEA.
 * User: domix
 * Date: 10/11/12
 * Time: 18:24
 * To change this template use File | Settings | File Templates.
 */
interface IssuesService {
  List<Issue> getIssues(String token, String projectId)
  List<Issue> getIssuesByOrganization(String token, String organizationId)
  void addLabelToIssue(String token, String owner, String repoId, String issueId, String label)
}
