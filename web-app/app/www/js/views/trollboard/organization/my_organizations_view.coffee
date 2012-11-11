define [
  'cs!lib/simpleLog'
  'conf/config'
  'jquery'
  'chaplin'
  'cs!views/trollboard/organization/organization_view'
  'cs!views/base/collection_view'
  'text!templates/trollboard/organization/my_organizations.hbs'
], (log, config, $, Chaplin, OrganizationView, CollectionView, template) ->
  'use strict'

  mediator = Chaplin.mediator

  class MyOrganizationsView extends CollectionView

    template: template
    template = null

    container: '#my-organizations'
    id: "my-organizations-content"
    listSelector: '#row_organization'
    className: "row info"

    getView: (item) ->
      new OrganizationView model: item