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
    <link href="app/www/js/vendor/bootstrap/2.1.1/css/bootstrap-combined.min.css" rel="stylesheet">

    <!-- Guidely css -->
    <link href="app/www/js/vendor/guidely/guidely.css" rel="stylesheet" type="text/css"/>

    <!-- Mojitoring css -->
    <link href="app/www/css/monitoring.css" rel="stylesheet" type="text/css"/>
    <g:layoutHead/>
    <r:layoutResources/>
  </head>

  <body>
    <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
          <button data-target=".nav-collapse" data-toggle="collapse" class="btn btn-navbar" type="button">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a href="./index.html" class="brand">Bootstrap</a>
          <div class="nav-collapse collapse">
            <ul class="nav">
              <li class="active">
                <a href="./index.html">Home</a>
              </li>
              <li class="">
                <a href="./getting-started.html">Get started</a>
              </li>
              <li class="">
                <a href="./scaffolding.html">Scaffolding</a>
              </li>
              <li class="">
                <a href="./base-css.html">Base CSS</a>
              </li>
              <li class="">
                <a href="./components.html">Components</a>
              </li>
              <li class="">
                <a href="./javascript.html">JavaScript</a>
              </li>
              <li class="">
                <a href="./customize.html">Customize</a>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>

    <div class="container">
      <g:layoutBody/>
    </div>

    <div class="footer" role="contentinfo"></div>
    <g:javascript library="application"/>
    <r:layoutResources/>
  </body>
</html>
