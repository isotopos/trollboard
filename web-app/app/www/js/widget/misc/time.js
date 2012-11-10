var wTime =
{
	$widget: null,
	$wTimeHour: null,
	$wTimeMinute: null,
	$wTimeSecond: null,
	Initialize: function ()
	{
		var self = wTime;

		Mojitoring.LoadHTML($("#wMisc"), "widget/misc/time.htm", self.InitializeCB);
	},
	InitializeCB: function()
	{
		var self = wTime;

		self.$widget = $("#wTime");
		self.$wTimeHour = $("#wTimeHour", self.$widget);
		self.$wTimeMinute = $("#wTimeMinute", self.$widget);
		self.$wTimeSecond = $("#wTimeSecond", self.$widget);

		// Setup tooltip for the rendered template
		$("[data-title]", self.$widget).tooltip();

		// Refresh every 1000ms
		window.setInterval(self.Refresh, 1000);
	},
	Refresh: function ()
	{
		var self = wTime;

		var now = new Date();

		self.$wTimeHour.html(self.ZeroPad(now.getHours()));
		self.$wTimeMinute.html(self.ZeroPad(now.getMinutes()));
		self.$wTimeSecond.html(self.ZeroPad(now.getSeconds()));
	},
	ZeroPad: function (n)
	{
		return (+n < 10 ? '0' : '') + n
	}

};

$(wTime.Initialize);