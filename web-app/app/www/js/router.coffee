define [
  'conf/config'
  'cs!controllers/trollboard/main_controller'
], (conf) ->
  'use strict'

  # The routes for the outletlication. This module returns a function.
  # `match` is match method of the Router
  (match) ->
    match '', 'trollboard/main#showWelcome'
    match '/', 'trollboard/main#showWelcome'
