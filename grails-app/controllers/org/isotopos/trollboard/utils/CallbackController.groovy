package org.isotopos.trollboard.utils

import wslite.rest.ContentType
import wslite.rest.RESTClient

class CallbackController {

  def restClient

  def index() {
  	params.remove 'controller'
  	session.user = params
  	println session.user

    try{
      getProviderToken(params)
    } catch (e){
      println 'ERROR message:' + e.message
      e.printStackTrace()
    }
  	redirect uri: '/app/www'
  }

  def getProviderToken(params) {

    def query = [client_id: '28e0b526536000c59092',
        state: 'trollboarders',
        client_secret: '9f094c887723847f42f6816d11d355d5ecfa7b6f',
        code: params.code]

    println 'Params for github login: ' + query
    def client = new RESTClient('https://github.com/')
    client.defaultCharset = "UTF-8"
    def rest = client.post(path: '/login/oauth/access_token'){
        type ContentType.JSON
        json query
    }

    println 'rest.statusMessage:' +rest.statusMessage
    println 'rest.access_token:' +rest.response.data
//    println 'rest.token_type:' +rest.response.token_type
    println 'response restClient: ' + rest.dump()
  }
}
