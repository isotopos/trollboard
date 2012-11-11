<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <meta content="trollboard" name="layout">
  <link href="/trollboard/app/www/css/profile.css" rel="stylesheet" type="text/css"/>
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
        <img src="${profile?.avatar}" class="img-rounded"/>
      </div>

      <div class="well-large sidebar-nav ">
        <div class="center bottom-separator2">
          <span>${profile.name}</span>
        </div>
        <ul class="nav nav-list">
          <li class="nav-header">Github profile</li>
          <li><a href="${profile.resourceUri}" target="_blank">
            ${profile.username}
          </a></li>
        </ul>
      </div>
    </div>

  </div>
  </div>


  <div class="span7">
    <ul class="nav nav-list bottom-separator2">
      <li class="nav-header"><h5>My projects:</h5></li>
      <g:each in="${projects}" var="p">
        <li><a href="${p.website}">${p.name}</a></li>
      </g:each>
    </ul>

    <ul class="nav nav-list">
      <li class="nav-header"><h5>My organizations:</h5></li>
      <g:each in="${organizations}" var="o">
        <li><a href="${o.user.username}">${o.user.username}</a></li>
      </g:each>
    </ul>
  </div>

  </div>
</body>
</html>