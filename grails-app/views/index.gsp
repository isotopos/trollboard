<!DOCTYPE html>
<html>
	<head>
		%{--<meta name="layout" content="main"/>--}%
		<title>Welcome to Trollboard</title>

    <!-- Bootstrap (responsive, with icons) full css -->
    <!-- For newer version or CDN,  please visit http://www.bootstrapcdn.com/ -->
    <link href="app/www/js/vendor/bootstrap/2.1.1/css/bootstrap-combined.min.css" rel="stylesheet">

    <!-- Guidely css -->
    <link href="app/www/js/vendor/guidely/guidely.css" rel="stylesheet" type="text/css"/>

    <!-- Mojitoring css -->
    <link href="app/www/css/monitoring.css" rel="stylesheet" type="text/css"/>

    <!-- Default Grails styles -->
    <link href="css/welcome.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
    <div class="container-narrow">
<!-- 
      <div class="masthead">
        <ul class="nav nav-pills pull-right">
          <li class="active"><a href="#">Home</a></li>
          <li><a href="#">About</a></li>
          <li><a href="#">Contact</a></li>
        </ul>
        <h3 class="muted">Trollboard</h3>
      </div>
-->

      <hr>

      <div class="jumbotron">
        <h1>Welcome to TrollBoard</h1>
        <p class="lead">Kanban like board + your favorite Social coding tool = TrollBoard</p>
        <a href="https://github.com/login/oauth/authorize?client_id=28e0b526536000c59092&state=trollboarders&scope=user,public_repo,repo,repo:status,delete_repo,notifications,gist" class="btn btn-large btn-success">
          %{--<img height="30" src="https://a248.e.akamai.net/assets.github.com/images/modules/header/logov7@4x-hover.png?1337118066" class="github-logo-4x-hover" alt="GitHub">--}%
          Sign in with GitHub
        </a>
        <a href="#" class="btn btn-large btn-success disabled">
          %{--<span class="bitbucket-logo">Sig In with Bitbucket</span>--}%
          Sig In with Bitbucket
        </a>
      </div>

      <hr>

      <div class="row-fluid marketing">
        <div class="span6">
          <h4>Subheading</h4>
          <p>Donec id elit non mi porta gravida at eget metus. Maecenas faucibus mollis interdum.</p>

          <h4>Subheading</h4>
          <p>Morbi leo risus, porta ac consectetur ac, vestibulum at eros. Cras mattis consectetur purus sit amet fermentum.</p>

          <h4>Subheading</h4>
          <p>Maecenas sed diam eget risus varius blandit sit amet non magna.</p>
        </div>

        <div class="span6">
          <h4>Subheading</h4>
          <p>Donec id elit non mi porta gravida at eget metus. Maecenas faucibus mollis interdum.</p>

          <h4>Subheading</h4>
          <p>Morbi leo risus, porta ac consectetur ac, vestibulum at eros. Cras mattis consectetur purus sit amet fermentum.</p>

          <h4>Subheading</h4>
          <p>Maecenas sed diam eget risus varius blandit sit amet non magna.</p>
        </div>
      </div>

      <hr>

      <div class="footer">
        <p>Isotopos - Grails48</p>
      </div>
    </div>
	</body>
</html>
