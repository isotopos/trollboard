var wErrorTracker =
{
	Initialize: function ()
	{
		var self = wErrorTracker;

		self.ShowErrorTracker();
	},
	ShowErrorTracker: function ()
	{
		var self = wErrorTracker;

		wDataServer.GetLatestError(5, self.ShowErrorTrackerCB);
	},
	ShowErrorTrackerCB: function (errorCollection)
	{
		var self = wErrorTracker;

		var options =
		{
			title: "Error tracker",
			columns: ["id", "type", "description"],
			actionView: "wErrorTracker.ShowView",
			icon: "icon-cog",
			headerClass: " mojitoPanelHeader-red",
			importantValue: Math.ceil(Math.random() * 42)
		};
		wTable.Show($("#wErrorTracker"), errorCollection, options);

		// Refresh every 4000ms
		window.setTimeout(self.ShowErrorTracker, 4000);
	},
	ShowView: function ()
	{
		var self = wErrorTracker;
		$('#emptyContainer').load('widget/tracker/error_view.htm', self.ShowViewCB);
	},
	ShowViewCB: function ()
	{
		var self = wErrorTracker;

		$('#errorView').modal();

	}
};

$(wErrorTracker.Initialize);