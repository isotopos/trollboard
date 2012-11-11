package org.isotopos.trollboard.api

import grails.converters.JSON

class SessionController {
	
	static allowedMethods = [index:'POST']

	def index() {
		render((session.user?:[:]) as JSON)
	}

}