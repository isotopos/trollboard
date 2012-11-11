package org.isotopos.trollboard.api

/**
 * Created with IntelliJ IDEA.
 * User: domix
 * Date: 10/11/12
 * Time: 20:14
 * To change this template use File | Settings | File Templates.
 */
class Milestone {
  Date createdAt

  Date dueOn

  int closedIssues

  int number

  int openIssues

  String description

  String state

  String title

  String url

  UserProfile creator
}
