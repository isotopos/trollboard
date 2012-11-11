define [
  'cs!lib/simpleLog'
  'conf/config'
  'jquery'
  'chaplin'
  'cs!models/profile'
  'cs!views/base/view'
  'text!templates/trollboard/user_profile.hbs'
], (log, config, $, Chaplin, Profile, View, template) ->
  'use strict'

  mediator = Chaplin.mediator

  class UserProfileView extends View

    template: template
    template = null

    container: '#user-profile'
    id: "board-content"

    initialize: ->
      github = mediator.user.get 'github'
      @model = new Profile({access_token:github.access_token}, {load: true})

      if @model.state() isnt 'resolved'
        @model.done @render
