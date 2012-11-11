package org.isotopos.trollboard

class Project {

  String providerId
  String projectId
  String token
  Date dateCreated
  Date lastUpdated

  static constraints = {
    providerId blank:false
    projectId blank:false
    token blank:false
  }
}
