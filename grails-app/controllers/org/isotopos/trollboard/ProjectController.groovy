package org.isotopos.trollboard

import grails.converters.JSON

class ProjectController {

  def create(){
    Project project = new Project(params)
    project.save(flush:true)
    render project as JSON
  }
}
