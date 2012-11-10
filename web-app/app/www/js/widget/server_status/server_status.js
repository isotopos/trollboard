/*

	Server Status usage example

*/

function ServerStatus($widget)
{
	this.$widget = $widget;
};

ServerStatus.prototype.Render = function (serverStatus)
{
	// jQuery MX
	if ($("#inlineServerStatus").length > 0)
		this.$widget.html('inlineServerStatus', serverStatus);
	else
		this.$widget.html('widget/server_status/server_status.ejs', serverStatus);

	// Setup tooltip for the rendered template
	$("[data-title]", this.$widget).tooltip();
};

ServerStatus.prototype.Refresh = function (serverStatus)
{
	// Update server status
	var $dom = $('[data-xx-value="serverStatus"]', this.$widget);
	$dom.removeClass("ok warning danger");
	$dom.addClass(serverStatus.serverStatus);

	// Update cpu charge
	$('[data-xx-value="cpu"]', this.$widget).html(serverStatus.cpu);

	// Update application status: todo :)
};

var wServerStatusManager =
{
	serverStatusCollection: null,
	Inititialize: function ()
	{
		var self = wServerStatusManager;

		// Call server data (wDataServer is a local library which simulates Ajax-Server call)
		wDataServer.GetServerStatusCollection(self.InitializeCB);

		// Show Database Server widget
		Mojitoring.LoadHTML($('#wDatabaseStatus'), 'widget/server_status/database_status.htm');
	},
	InitializeCB: function (data)
	{
		// Important: for our example, data should contain 2 serverStatus

		var self = wServerStatusManager;

		self.serverStatusCollection = [];

		// Create 2 ServerStatus widgets
		self.serverStatusCollection[0] = new ServerStatus($("#wServerStatus1"));
		self.serverStatusCollection[1] = new ServerStatus($("#wServerStatus2"));

		// Render the ServerStatus widgets
		self.serverStatusCollection[0].Render(data[0]);
		self.serverStatusCollection[1].Render(data[1]);

		// Refresh the ServerStatus widgets
		self.Refresh();
	},
	Refresh: function ()
	{
		var self = wServerStatusManager;

		wDataServer.GetServerStatusCollection(self.RefreshCB);
	},
	RefreshCB: function (data)
	{
		var self = wServerStatusManager;

		self.serverStatusCollection[0].Refresh(data[0]);
		self.serverStatusCollection[1].Refresh(data[1]);
		
		// Refresh data every 2000ms
		window.setTimeout(self.Refresh, 2000);
	}
};


$(wServerStatusManager.Inititialize);
