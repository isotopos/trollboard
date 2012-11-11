define [
  'chaplin',
  'cs!models/base/collection'
  'cs!models/project/project'
  'conf/config'
], (Chaplin, Collection, Project, config) ->
  'use strict'

  class Projects extends Collection

    model: Project

    initialize: (attributes, options)->
      super
      @url = config.project + attributes.access_token
      @fetch()


