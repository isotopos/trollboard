define [
  'cs!lib/simpleLog'
  'jquery'
  'chaplin'
  'cs!views/base/view'
  'text!templates/welcome.hbs'
], (log, $, Chaplin, View, msg, template) ->
  'use strict'

  # Shortcut to the mediator
  mediator = Chaplin.mediator

  class WelcomeView extends View

    # Save the template string in a prototype property.
    # This is overwritten with the compiled template function.
    # In the end you might want to used precompiled templates.
    template: template
    template = null
    container: '#inner_container'
    autoRender: true

    initialize: ->
      super
      log.info "weeee!"

