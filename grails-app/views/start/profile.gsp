<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <meta content="trollboard" name="layout">
  <link href="/app/www/css/profile.css" rel="stylesheet" type="text/css"/>
  <script type="text/javascript" src="${g.resource(dir: 'app/www/js/vendor/bootstrap/2.1.1/js', file: 'bootstrap-collapse.min.js')}"></script>
</head>

<body>

<div class="container-fluid top-separator6">

  <div class="span3">
  <div class="mojitoPanel mojitoPanel-gray ">
    <div class="mojitoPanelHeader">
      <i class="icon-user"></i>Profile
    </div>
    <div class="mojitoPanelContent">
      <div class="well-large center">
        <img src="${profile?.avatar}" class="img-rounded" height="110%" width="110%"/>
      </div>

      <div class="well-large sidebar-nav ">
        <div class="center bottom-separator1">
          <span>${profile.name}</span>
        </div>
        <div class="bottom-separator1">
          <span><a href="${profile.resourceUri}" target="_blank">
            ${profile.username}
          </a></span>
        </div>
        <div class="bottom-separator1">
          <i class="icon-globe"></i> <span>${profile.location}</span>
        </div>
        <div class="bottom-separator1">
          <span><i class="icon-hand-right"></i> Following: ${profile.following}</span>
        </div>
        <div class="bottom-separator1">
          <span><i class="icon-hand-left"></i> Followers: ${profile.followers}</span>
        </div>
        <div class="bottom-separator1">
          <span><i class="icon-eye-open"></i> Public repositories: ${profile.publicRepos}</span>
        </div>
        <div class="bottom-separator1">
          <span><i class="icon-eye-close"></i> Private repository: ${profile.totalPrivateRepos}</span>
        </div>
        <div class="bottom-separator1">
          <span><i class="icon-eye-open"></i> Public Gists: ${profile.publicGists}</span>
        </div>
        <div class="bottom-separator1">
          <span><i class="icon-eye-close"></i> Private Gists: ${profile.privateGists}</span>
        </div>
      </div>
    </div>

  </div>
  </div>


  <div class="span8">

    <g:if test="${projects}">
    <div class="well">
    <table class="table table-bordered">
      <caption class="bottom-separator2"><h4>My projects:</h4></caption>

      <tbody>
        <g:if test="${projects}">
        <g:each in="${projects}" var="p">
          <tr>
            <td>
              <div class="bottom-separator1">
                <div class="project span7">
                  <i class="<g:if test="${p.isPrivate}">icon-folder-close</g:if>
                  <g:else>icon-folder-open</g:else>"></i> &nbsp;
                  <a href="${g.createLink(mapping: 'projectBoard', params: [project: p.name])}">${p.name}</a>
                </div>
                <g:if test="${p.language}">
                <div class="pagination-right extras span2">
                  <i class="icon-pencil"></i> ${p.language}
                </div>
                </g:if>
              </div>
              <span>&nbsp; ${p.description}</span>
            </td>
          </tr>
        </g:each>
        </g:if>
      </tbody>
    </table>
    </div>
    </g:if>


    <div class="well">
      <table class="table table-bordered">
        <caption class="bottom-separator2"><h4>My organizations:</h4></caption>

        <tbody>
        <g:if test="${organizations}">
          <g:each in="${organizations}" var="o" status="sto">
            <tr>
              <td>
                <div class="bottom-separator2">
                  <div class="organization span7">
                    <img src="${o.user.avatar}" class="img-rounded" height="50px" width="50px"/>
                    <a href="javascript: $('#organization-projects-${sto}').collapse('toggle');" >${o.user.username}</a>
                  </div>
                  <div class="pagination-right span2">
                    <button type="button" class="btn btn-link" data-toggle="collapse" data-target="#organization-projects-${sto}">
                    <i class="icon-chevron-down" ></i>
                    </button>
                  </div>
                </div>
              <g:if test="${organizations.projects}">
                <div class="collapse" id="organization-projects-${sto}">
                  <table class="table table-condensed " >
                    <tbody>
                    <g:each in="${o.projects}" var="pro" >
                      <tr><td class="none-border-left">
                        <div class="left-separator2">
                          <g:if test="${pro.isPrivate}"><i class="icon-folder-close"></i></g:if>
                          <g:else><i class="icon-folder-open"></i></g:else>
                          <a href="${g.createLink(mapping: 'organizationProjectBoard', params: [organization: o.user.username, project: pro.name])}">${pro.name}</a>
                        </div>
                        <span class="left-separator3">&nbsp; ${pro.description}</span>
                      </td></tr>
                    </g:each>
                    </tbody>
                  </table>
                </div>
              </g:if>
              </td>
            </tr>
          </g:each>
          </g:if>
        </tbody>
      </table>
    </div>

  </div>
</body>
</html>