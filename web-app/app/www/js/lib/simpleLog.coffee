define [
  'conf/config'
], (config) ->
  'use strict'
  log = {}

  log.info = ( arg ) ->
    if config.log is true
      console.info(arg)

  log.error = ( arg ) ->
    if config.log is true
      console.error(arg)

  log.debug = ( arg ) ->
    if config.log is true
      console.debug(arg)

  log.warn = ( arg ) ->
    if config.log is true
      console.warn(arg)

   log
