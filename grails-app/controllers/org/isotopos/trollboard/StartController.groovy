package org.isotopos.trollboard

import grails.converters.JSON
import org.codehaus.groovy.grails.web.json.JSONElement

class StartController {

  def apiUserProfileService

  def profile() {
    def profile = flash.profile
    [profile: profile]
  }

  private JSONElement getTrollboardProfile() {
    def profileCookie = g.cookie(name: 'trollboard-profile')
    JSON.parse(profileCookie.toString())
  }
}
