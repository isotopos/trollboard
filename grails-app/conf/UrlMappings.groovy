class UrlMappings {

  static mappings = {
    "/$controller/$action?/$id?" {
      constraints {
        // apply constraints here
      }
    }

    "/v1/user"(controller: 'userProfile', action: 'userProfile')
    "/v1/user/projects"(controller: 'userProfile', action: 'projects')
    "/v1/project/$id/issues"(controller: 'userProfile', action: 'projectIssues')
    "/v1/user/organizations"(controller: 'userProfile', action: 'organizations')
    "/v1/organization/$id"(controller: 'userProfile', action: 'projects')
    "/v1/organization/$id/issues"(controller: 'userProfile', action: 'projects')

    "/"(view: "/index")
    "500"(view: '/error')
  }
}
