define [
  'chaplin',
  'cs!models/base/collection'
  'cs!models/organization/organization'
  'conf/config'
], (Chaplin, Collection, Organization, config) ->
  'use strict'

  class Organizations extends Collection

    model: Organization

    initialize: (attributes, options)->
      super
      @url = config.organization + attributes.access_token
      @fetch()


