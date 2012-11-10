package org.isotopos.trollboard.utils

class CallbackController {

  def index() {
  	params.remove 'controller'
  	session.user = params
  	println session.user
  	redirect uri: '/app/www'
  }
}
