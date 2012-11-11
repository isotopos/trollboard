define [
  'cs!lib/simpleLog'
  'jquery'
  'chaplin'
  'cs!models/profile'
  'cs!views/base/view'
  'text!templates/trollboard/project/project.hbs'
], (log, $, Chaplin, Profile, View, template) ->
  'use strict'

  mediator = Chaplin.mediator

  class ProjectView extends View

    template: template
    template = null

    autoRender: true
    container: '#my-projects-list'

    initialize: ->
      super
