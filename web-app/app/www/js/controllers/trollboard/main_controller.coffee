define [
  'cs!lib/simpleLog'
  'jquery'
  'chaplin'
  'cs!views/trollboard/welcome_view'
  'conf/config'
], (log, $, Chaplin, WelcomeView, conf) ->
  'use strict'

  mediator = Chaplin.mediator

  class MainController extends Chaplin.Controller
    title: 'trollBoad'
    initialize:->

    showWelcome: (params) ->
      log.info ['mainStore#showWelcome', JSON.stringify(params)]
      @view = new WelcomeView()

