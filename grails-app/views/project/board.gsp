<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head>
    <meta content="trollboard" name="layout">
    <link href="${g.resource(dir: 'css', file: 'project.css')}" rel="stylesheet" type="text/css"/>
  </head>

  <body>
    <div id="wrap">
      <div id="core" style="margin-top: 70px;">
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
                  <div style="position: relative;margin-left: 0px;" class="issue mojitoPanel mojitoPanel-dark span12 ui-draggable">
                    <span class="inlineMarker hide">marker for the inline version</span>

                    %{--<div class="mojitoPanelHeader mojitoPanelHeader-blue" id="wRealtimeGraphDD">--}%
                      %{--<i class="icon-random"></i>${issue.number}--}%
                    %{--</div>--}%

                    <div style="padding: 16px;" class="mojitoPanelContent">
                      <a href="${issue.htmlUrl}" target="_blank"><strong>#${issue.number}</strong></a> <span>${issue.title}</span>
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