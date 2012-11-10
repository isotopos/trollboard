class UrlMappings {

  static mappings = {
    "/$controller/$action?/$id?" {
      constraints {
        // apply constraints here
      }
    }

    "/v1/userProfile"(controller: 'userProfile', action: 'userProfile')

    "/"(view: "/index")
    "500"(view: '/error')
  }
}
