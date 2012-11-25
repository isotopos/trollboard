class UrlMappings {

  static mappings = {
    "/$controller/$action?/$id?" {
      constraints {
        // apply constraints here
      }
    }

    "/v1/user"(controller: 'userProfile', action: 'userProfile')
    "/v1/user/projects"(controller: 'userProfile', action: 'projects')
    "/v1/user/organizations"(controller: 'userProfile', action: 'organizations')
    "/v1/project/$projectId/issues"(controller: 'userProfile', action: 'projectIssues')
    "/v1/organization/$organizationId/issues"(controller: 'userProfile', action: 'issuesByOrganization')
    "/v1/project/$projectId/labels/"(controller: 'userProfile') {
      action = [POST: "addDefaultLabels", GET: 'labelsByProject']
    }
    "/v1/project/$projectId/milestones"(controller: 'userProfile', action: 'projectMilestones')


    "/v1/project/$projectId/lanes"(controller: 'userProfile', action: 'projectLanes')
    "/v1/project/$projectId/live"(controller: 'userProfile', action: 'projectLive')

    "/v1/project/$projectId/issue/$issueId/lane/$laneId"(controller: 'userProfile', action: 'changeIssueLane')

//    userProfile: "/user/$id"(controller: 'start', action: 'profile')
    name projectBoard: "/board/$project"(controller: 'project', action: 'board')
    name organizationProjectBoard: "/board/$organization/$project"(controller: 'project', action: 'board')
    name callbackReceivePayload: "/callback/receive"(controller: 'callback'){
      action = [GET:"receive",POST:"receive"]
    }

    "/v1/organization/$id"(controller: 'userProfile', action: 'projects')
    "/"(view: "/index")
    "500"(view: '/error')
  }
}
