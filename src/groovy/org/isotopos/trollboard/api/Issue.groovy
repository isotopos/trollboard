package org.isotopos.trollboard.api

/**
 * Created with IntelliJ IDEA.
 * User: domix
 * Date: 10/11/12
 * Time: 18:12
 * To change this template use File | Settings | File Templates.
 */
class Issue {
  Long id
  Date closedAt
  Date createdAt
  Date updatedAt
  int comments
  int number
  String body
  String bodyHtml
  String bodyText
  String htmlUrl
  String state
  String title
  String url
  UserProfile assignee
  UserProfile user
  List<Label> labels
  Milestone milestone
  Integer numberOfCommits
  /*
	private ;

	private ;

	private PullRequest pullRequest;
   */
}
