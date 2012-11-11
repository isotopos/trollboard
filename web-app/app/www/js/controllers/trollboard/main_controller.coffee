define [
  'cs!lib/simpleLog'
  'conf/config'
  'jquery'
  'chaplin'
  'cs!models/user'
  'cs!views/trollboard/welcome_view'
  'conf/config'
], (log, config, $, Chaplin, User, WelcomeView, conf) ->
  'use strict'

  mediator = Chaplin.mediator

  class MainController extends Chaplin.Controller
    title: 'trollBoad'

    showWelcome: (params) ->
      $.post conf.urlVerifySession, @handlerVerifySession
    
    handlerVerifySession: (data) ->
      console.log 'data', data
      if data.code 
        mediator.user = new User data
        @view = new WelcomeView()
      else
        window.location = config.home