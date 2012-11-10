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
    "/v1/user/teams"(controller: 'userProfile', action: 'teams')
    "/v1/team/$id"(controller: 'userProfile', action: 'projects')
    "/v1/team/$id/issues"(controller: 'userProfile', action: 'projects')

    "/"(view: "/index")
    "500"(view: '/error')
  }
}
