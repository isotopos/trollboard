define [
  'cs!lib/simpleLog'
  'conf/config'
  'jquery'
  'chaplin'
  'cs!models/project/projects'
  'cs!views/trollboard/project/project_view'
  'cs!views/base/collection_view'
  'text!templates/trollboard/project/my_projects.hbs'
], (log, config, $, Chaplin, Projects, ProjectView, CollectionView, template) ->
  'use strict'

  mediator = Chaplin.mediator

  class MyProjectsView extends CollectionView

    template: template
    template = null

    container: '#my-projects'
    id: "my-projects-content"
    listSelector: '#row_project'
    className: "row info"

    initialize: ->
      super

    getView: (item) ->
      new ProjectView model: item