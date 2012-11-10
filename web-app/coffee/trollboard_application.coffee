define [
  'chaplin'
  'cs!views/layout'
  'cs!router'
], (Chaplin, Layout, router) ->
  'use strict'

  # The application object
  class TrollboardApplication extends Chaplin.Application

    # Set your application name here so the document title is set to
    # “Controller title – Site title” (see Layout#adjustTitle)
    title: 'Trollboard....'

    initialize: ->
      super
      # Initialize core components
      @initDispatcher()
      @initLayout()
      @initMediator()

      # Application-specific scaffold
      @initControllers()
      # Register all routes and start routing
      @initRouter router
      #@initRouter routes, pushState: true, root: '/chaplinDemo/'

      # Freeze the application instance to prevent further changes
      Object.freeze? this

    # Override standard layout initializer
    # ------------------------------------
    overrideInitLayout: ->
      @layout = new Layout {@title}

    # Instantiate common controllers
    # ------------------------------
    initControllers: ->
      # These controllers are active during the whole application runtime.

    # Create aditional mediator properties
    # ------------------------------------
    initMediator: ->
      # Create a user property
      Chaplin.mediator.user = null
      # Seal the mediator
      Chaplin.mediator.seal()
