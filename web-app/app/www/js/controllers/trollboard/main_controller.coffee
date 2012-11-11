define [
  'cs!lib/simpleLog'
  'conf/config'
  'jquery'
  'chaplin'
  'cs!models/user'
  'cs!models/project/projects'
  'cs!models/organization/organizations'
  'cs!views/trollboard/welcome_view'
  'cs!views/trollboard/user_profile_view'
  'cs!views/trollboard/organization/my_organizations_view'
  'cs!views/trollboard/project/my_projects_view'
], (log, config, $, Chaplin, User, Projects, Organizations, WelcomeView, UserProfileView, MyOrganizationsView, MyProjectsView) ->
  'use strict'

  mediator = Chaplin.mediator

  class MainController extends Chaplin.Controller
    title: 'trollBoad'

    showWelcome: (params) ->
      $.post config.urlVerifySession, @handlerVerifySession

    handlerVerifySession: (data) =>
      if data?.github?.code
        mediator.user = new User data
        @view = new WelcomeView()
        new UserProfileView()

        @myProjects()
        @myOrganizations()

      else
        window.location = '/' + config.home

    myProjects: (params) ->
      github = mediator.user.get 'github'
      @projectsView = new Projects({access_token:github.access_token}, {load: true})
      new MyProjectsView collection: @projectsView

    myOrganizations: (params) ->
      github = mediator.user.get 'github'
      @organizatiosView = new Organizations({access_token:github.access_token}, {load: true})
      new MyOrganizationsView collection: @organizatiosView