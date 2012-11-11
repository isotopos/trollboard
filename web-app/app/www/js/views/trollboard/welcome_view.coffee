define [
  'cs!lib/simpleLog'
  'conf/config'
  'jquery'
  'chaplin'
  'cs!views/base/view'
  'text!templates/welcome.hbs'
], (log, config, $, Chaplin, View, template) ->
  'use strict'

  class WelcomeView extends View

    template: template
    template = null

    container: '#board-container'
    id: "board-content"
    autoRender: true

