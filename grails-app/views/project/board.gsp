<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head>
    <meta content="trollboard" name="layout">
    <link href="${g.resource(dir: 'css', file: 'project.css')}" rel="stylesheet" type="text/css"/>
    <link href="http://harvesthq.github.com/chosen/chosen.css"/>
    <script type="text/javascript" src="http://harvesthq.github.com/chosen/chosen/chosen.jquery.js"></script>
    <script type="text/javascript">
      $(function () {
        //$('.chzn-select').chosen();
        $('#milestone-selector').change(function () {
          var $milestonSelector = $(this);
          var milestone = $milestonSelector.val();
          if (milestone != 'null') {
            var issueByMilestoneSelector = '.milestone-' + milestone;
            $('.issue').hide();
            $(issueByMilestoneSelector).show();
          } else {
            $('.issue').show();
          }
        });
      });
    </script>
  </head>

  <body>
    <div id="wrap">
      <div id="wTopmenu">
        <!-- User button -->
        <div id="wUserButton" class="clearfix" style="top: 45px;">
          <g:select name="milestone-selector" from="${milestones}" optionKey="number" optionValue="title"
                    noSelection="['null': 'Filter by Milestone']"
                    data-placeholder="Filter by Milestone" class="chzn-select"/>
        </div>

        <ul class="dropdown-menu pull-right" id="wUserMenu">
          <li><a href="javascript:wUser.Logout();">Log in</a></li>
          <li class="divider"></li>
          <li><a href="javascript:wUser.Logout();">Log out</a></li>
        </ul>

      </div>

      <div id="core" style="margin-top: 70px;">
        <a href="#" class="btn btn-danger" id="wTakeaTour" style="top: 40px;">${name}</a>

        <div class="row-fluid">
          <g:each in="${lanes}" var="lane">
            <div class="issue-wrapper wServerStatus mojitoPanel span2 ui-droppable"
                 id="wServerStatus1">
              <!-- widget header -->
              <div class="mojitoPanelHeader mojitoPanelHeader-dark">
                %{--<i class="icon-plane"></i>--}%
                <span class="mojitoPanelHeaderTitle wssName">${lane.lane.name}</span>
                <!--
			<div class="floatRight">
				<i data-xx-value="serverStatus" class="icon-white warning"></i>
				<a class="btn btn-tiny btn-inverse" data-title="Reboot the server&lt;br &gt;&lt;/a&gt;(hardware)" data-original-title="">action</a>
				<a class="btn btn-tiny btn-inverse" data-title="Restart the web server&lt;br &gt;&lt;/a&gt;(software)" data-original-title="">action</a>
			</div>
			-->
              </div>

              <div class="mojitoPanelContent issue-wrapper ui-droppable">
                <g:each in="${lane.issues}" var="issue">
                  <!-- widget content -->
                  <g:set var="milestoneClass" value="${issue.milestone ? ' milestone-' + issue.milestone.number : ''}"/>
                  <div style="position: relative;margin-left: 0px;"
                       class="issue mojitoPanel mojitoPanel-dark span12 ui-draggable${milestoneClass}">
                    <span class="inlineMarker hide">marker for the inline version</span>

                    <div class="mojitoPanelHeader mojitoPanelHeader-dark" id="wRealtimeGraphDD">
                      <g:if test="${issue?.assignee}">
                        <div>
                          <img height="24" width="24" src="${issue.assignee.avatar}" alt="${issue.assignee.username}">
                          <span style="font-size: 12px;">${issue.assignee.username}</span>
                        </div>
                      </g:if>
                      <g:else>
                        <div>
                          <img height="24" width="24"
                               src="https://secure.gravatar.com/avatar/9b855cf7a35aad6843b9ce0c826c038a?d=https://a248.e.akamai.net/assets.github.com%2Fimages%2Fgravatars%2Fgravatar-user-420.png"
                               alt="Not assigned">
                          <span style="font-size: 12px;">Not Assigned</span>
                        </div>
                      </g:else>
                    %{--<i class="icon-random"></i>${issue.number}--}%
                    </div>

                    <div style="padding: 16px;" class="mojitoPanelContent">
                      <span>
                        <a href="${issue.htmlUrl}" target="_blank"><strong>#${issue.number}</strong>
                        </a> <strong>${issue.title}</strong>
                      </span>

                      <div style="margin-top:5px;font-size:12px;">${issue.body}</div>
                      <div style="margin-top:5px;text-align: right">
                        <img height="24" width="24" src="${issue.user.avatar}" alt="${issue.user.username}" title="${issue.user.username}"/>
                      </div>
                    </div>
                  </div>


                %{--<div style="min-height: 300px; position: relative;" class="issue mojitoPanel span12 ui-draggable"--}%
                %{--id="wRealtimeData1">--}%
                %{--<span class="inlineMarker hide">marker for the inline version</span>--}%

                %{--<div class="mojitoPanelHeader">--}%
                %{--<i class="icon-signal"></i>Today's statistics--}%
                %{--</div>--}%

                %{--<div class="mojitoPanelContent">--}%
                %{--<ul class="mojitoList">--}%
                %{--<li><i class="icon-user"></i><span id="todayUserCount">0</span><small>users</small></li>--}%
                %{--<li><i class="icon-shopping-cart"></i><span id="todayOrderCount">0</span><small>orders</small></li>--}%
                %{--<li><i class="icon-random"></i><span id="todayCashTotal">0</span><small>$ cash</small></li>--}%
                %{--</ul>--}%
                %{--</div>--}%
                %{--</div>--}%
                </g:each>
              </div>
            </div>
          </g:each>
        </div>
      </div>
    </div>
  </body>
</html>