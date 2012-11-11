<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <meta content="trollboard" name="layout">
  <link href="/trollboard/app/www/css/profile.css" rel="stylesheet" type="text/css"/>
  <link rel="stylesheet" href="http://code.jquery.com/ui/1.9.1/themes/base/jquery-ui.css" />
  <r:require module="jquery"/>

  <style type="text/css">
  body{
    padding-top: 50px;
  }
  </style>
</head>

<body>

  <div class="row-fluid">


    <div id="wServerStatus1" class="issue-wrapper wServerStatus mojitoPanel span2">
      <!-- widget header -->
      <div class="mojitoPanelHeader mojitoPanelHeader-dark">

        <span class="mojitoPanelHeaderTitle wssName">TEST</span>
                <!--
            <div class="floatRight">
                <i data-xx-value="serverStatus" class="icon-white warning"></i>
                <a class="btn btn-tiny btn-inverse" data-title="Reboot the server&lt;br &gt;&lt;/a&gt;(hardware)" data-original-title="">action</a>
                <a class="btn btn-tiny btn-inverse" data-title="Restart the web server&lt;br &gt;&lt;/a&gt;(software)" data-original-title="">action</a>
            </div>
          -->
        </div>

        <div class="mojitoPanelContent issue-wrapper">

          <ul id="ui-draggable1" class="connectedSortable">
          <!-- widget content -->
          <li>
          <div class="issue mojitoPanel mojitoPanel-dark span12" >
            <span class="inlineMarker hide">marker for the inline version</span>

            <div id="wRealtimeGraphDD" class="mojitoPanelHeader mojitoPanelHeader-dark">
              <i class="icon-random"></i>2
            </div>

            <div class="mojitoPanelContent" style="padding: 16px;">
              <a target="_blank" href="https://github.com/isotopos48test/my_examples/issues/2"><strong>#2</strong></a> <span>Do a README</span>
            </div>
          </div>
          </li>
          <!-- widget content -->
          <li>
          <div class="issue mojitoPanel mojitoPanel-dark span12" >
            <span class="inlineMarker hide">marker for the inline version</span>

            <div id="wRealtimeGraphDD" class="mojitoPanelHeader mojitoPanelHeader-dark">
              <i class="icon-random"></i>3
            </div>

            <div class="mojitoPanelContent" style="padding: 16px;">
              <a target="_blank" href="https://github.com/isotopos48test/my_examples/issues/3"><strong>#3</strong></a> <span>Read a file</span>
            </div>
          </div>
          </li>
          <!-- widget content -->
          <li>
          <div class="issue mojitoPanel mojitoPanel-dark span12" >
            <span class="inlineMarker hide">marker for the inline version</span>

            <div id="wRealtimeGraphDD" class="mojitoPanelHeader mojitoPanelHeader-dark">
              <i class="icon-random"></i>7
            </div>

            <div class="mojitoPanelContent" style="padding: 16px;">
              <a target="_blank" href="https://github.com/isotopos48test/my_examples/issues/7"><strong>#7</strong></a> <span>otro mas</span>
            </div>
          </div>
          </li>

          </ul>  <!--draggable-->

        </div>
      </div>



      <div id="wServerStatus2" class="issue-wrapper wServerStatus mojitoPanel span2">
      <!-- widget header -->
      <div class="mojitoPanelHeader mojitoPanelHeader-dark">
        
        <span class="mojitoPanelHeaderTitle wssName">TEST</span>
                <!--
            <div class="floatRight">
                <i data-xx-value="serverStatus" class="icon-white warning"></i>
                <a class="btn btn-tiny btn-inverse" data-title="Reboot the server&lt;br &gt;&lt;/a&gt;(hardware)" data-original-title="">action</a>
                <a class="btn btn-tiny btn-inverse" data-title="Restart the web server&lt;br &gt;&lt;/a&gt;(software)" data-original-title="">action</a>
            </div>
          -->
        </div>

        <div class="mojitoPanelContent issue-wrapper">
          
          <ul id="ui-draggable2" class="connectedSortable">
          <!-- widget content -->
          <li>
          <div class="issue mojitoPanel mojitoPanel-dark span12" >
            <span class="inlineMarker hide">marker for the inline version</span>

            <div id="wRealtimeGraphDD" class="mojitoPanelHeader mojitoPanelHeader-dark">
              <i class="icon-random"></i>2
            </div>

            <div class="mojitoPanelContent" style="padding: 16px;">
              <a target="_blank" href="https://github.com/isotopos48test/my_examples/issues/2"><strong>#2</strong></a> <span>Do a README</span>
            </div>
          </div>
          </li>
          <!-- widget content -->
          <li>
          <div class="issue mojitoPanel mojitoPanel-dark span12" >
            <span class="inlineMarker hide">marker for the inline version</span>

            <div id="wRealtimeGraphDD" class="mojitoPanelHeader mojitoPanelHeader-dark">
              <i class="icon-random"></i>3
            </div>

            <div class="mojitoPanelContent" style="padding: 16px;">
              <a target="_blank" href="https://github.com/isotopos48test/my_examples/issues/3"><strong>#3</strong></a> <span>Read a file</span>
            </div>
          </div>
          </li>
          <!-- widget content -->
          <li>
          <div class="issue mojitoPanel mojitoPanel-dark span12" >
            <span class="inlineMarker hide">marker for the inline version</span>

            <div id="wRealtimeGraphDD" class="mojitoPanelHeader mojitoPanelHeader-dark">
              <i class="icon-random"></i>7
            </div>

            <div class="mojitoPanelContent" style="padding: 16px;">
              <a target="_blank" href="https://github.com/isotopos48test/my_examples/issues/7"><strong>#7</strong></a> <span>otro mas</span>
            </div>
          </div>
          </li>

          </ul>  <!--draggable-->

        </div>
      </div>

    </div>
    <script src="http://code.jquery.com/ui/1.9.1/jquery-ui.js"></script>
    <script type="text/javascript">
    $(function(){
      $( "#ui-draggable1,#ui-draggable2" ).sortable({
        connectWith: ".connectedSortable"
      });
      $( "#ui-draggable1,#ui-draggable2" ).disableSelection();
    });
    </script>
  </body>
  </html>