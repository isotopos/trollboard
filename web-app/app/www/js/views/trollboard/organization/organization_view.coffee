define [
  'cs!lib/simpleLog'
  'jquery'
  'chaplin'
  'cs!models/organization/organization'
  'cs!views/base/view'
  'text!templates/trollboard/organization/organization.hbs'
], (log, $, Chaplin, Organization, View, template) ->
  'use strict'

  mediator = Chaplin.mediator

  class OrganizationView extends View

    template: template
    template = null

    autoRender: true
    container: '#my-organizations-list'

    initialize: ->
      super
