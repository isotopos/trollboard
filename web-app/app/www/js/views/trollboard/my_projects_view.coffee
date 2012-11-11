define [
  'cs!lib/simpleLog'
  'conf/config'
  'jquery'
  'chaplin'
  'cs!models/project'
  'cs!views/base/view'
  'text!templates/trollboard/my_projects.hbs'
], (log, config, $, Chaplin, Project, View, template) ->
  'use strict'

  mediator = Chaplin.mediator

  class UserProfileView extends View

    template: template
    template = null

    container: '#my-projects'
    id: "my-projects-content"

    initialize: ->
      github = mediator.user.get 'github'
      @model = new Project({access_token:github.access_token}, {load: true})

      if @model.state() isnt 'resolved'
        @model.done @render
