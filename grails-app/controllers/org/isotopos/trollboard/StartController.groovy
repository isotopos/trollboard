package org.isotopos.trollboard

import grails.converters.JSON
import org.codehaus.groovy.grails.web.json.JSONElement

class StartController {

  def apiUserProfileService

  def profile() {
    def profile = getTrollboardProfile()
    apiUserProfileService.getUserProfile()
    [profile: profile]
  }

  private JSONElement getTrollboardProfile() {
    def profileCookie = g.cookie('trollboard-profile')
    JSON.parse(profileCookie.toString())
  }
}
