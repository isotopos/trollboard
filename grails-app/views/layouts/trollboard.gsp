<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title><g:layoutTitle default="Trollboard"/></title>
    <!-- Bootstrap (responsive, with icons) full css -->
    <!-- For newer version or CDN,  please visit http://www.bootstrapcdn.com/ -->
    <link href="${g.resource(dir: 'app/www/js/vendor/bootstrap/2.1.1/css', file: 'bootstrap-combined.min.css')}"
          rel="stylesheet">

    <!-- Guidely css -->
    <link href="${g.resource(dir: 'app/www/js/vendor/guidely', file: 'guidely.css')}" rel="stylesheet" type="text/css"/>

    <!-- Mojitoring css -->
    <link href="${g.resource(dir: 'app/www/css', file: 'monitoring.css')}" rel="stylesheet" type="text/css"/>

    <script type="text/javascript" src="${g.resource(dir: 'app/www/js/vendor', file: 'jquery-1.8.2.min.js')}"></script>
    <script type="text/javascript"
            src="${g.resource(dir: 'app/www/js/vendor/bootstrap/2.1.1/js', file: 'bootstrap.min.js')}"></script>
    <g:layoutHead/>
    <r:layoutResources/>
  </head>

  <body class="irongrip">
    <div>
      <div class="navbar navbar-inverse navbar-fixed-top">
        <div class="navbar-inner">
          <div class="container">
            <a data-target=".nav-collapse" data-toggle="collapse" class="btn btn-navbar">
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </a>
            <a href="http://trollboard.rs.af.cm/" class="brand">Trollboard</a>

            <div class="nav-collapse collapse">
              <ul class="nav">
                <li class="active"><a href="#">Home</a></li>
                <li><a href="http://trollboard.rs.af.cm/#about">About</a></li>
                <li><a href="http://trollboard.rs.af.cm/#contact">Contact</a></li>
              </ul>
            </div>
          <!--/.nav-collapse -->
            <g:if test="${view == 'board' && !project}">
              <div id="commit-integration-btn" class="btn-group pull-right">
                <a href="#" class="btn btn-info">
                  <i class="icon-play"></i> Commit Integration
                </a>
              </div>
            </g:if>
          </div>
        </div>
      </div>

      %{--<div class="container">--}%
      <g:layoutBody/>
      %{--</div>--}%

      <div class="footer" role="contentinfo"></div>
      <script type="text/javascript">
        var TrollBoard = { appCtx: '${request.contextPath}',
          projectId: '${params.project}',
          providerToken: '${session.trollboardProfile?.access_token}',
          ownerId: '${session.trollboardProfile.ownerId}'
        };
      </script>
      <g:javascript library="application"/>
      <r:layoutResources/>
  </body>
</html>
