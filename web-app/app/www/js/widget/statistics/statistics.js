var wStatistics =
{
	todayData: null,
	totalData: null,
	Initialize: function ()
	{
		var self = wStatistics;

		// Show widgets
		Mojitoring.LoadHTML($('#wRealtimeData1'),'widget/statistics/today_stat.htm');
		Mojitoring.LoadHTML($('#wRealtimeData2'), 'widget/statistics/total_stat.htm');
		Mojitoring.LoadHTML($('#wAnalytics'), 'widget/statistics/analytics.htm');

		// Initialize data (for simulation)
		self.todayData = {};
		self.todayData.userCount = Math.floor(Math.random() * 100);
		self.todayData.orderCount = Math.floor(Math.random() * 200);
		self.todayData.cashTotal = Math.floor(Math.random() * 10000);

		self.totalData = {};
		self.totalData.userCount = 1000 + Math.floor(Math.random() * 1000);
		self.totalData.orderCount = 2000 + Math.floor(Math.random() * 2000);
		self.totalData.cashTotal = 100000 + Math.floor(Math.random() * 400000);

		// Display data
		self.Display();

		// Call refresh with a delay(2s)
		window.setTimeout(self.Refresh, 2000);
	},
	Display: function ()
	{
		var self = wStatistics;

		$("#todayUserCount").html(self.todayData.userCount);
		$("#todayOrderCount").html(self.todayData.orderCount);
		$("#todayCashTotal").html(self.todayData.cashTotal);

		$("#totalUserCount").html(self.totalData.userCount);
		$("#totalOrderCount").html(self.totalData.orderCount);
		$("#totalCashTotal").html(self.totalData.cashTotal);

	},
	Refresh: function (data)
	{
		var self = wStatistics;

		wDataServer.GetLatestData(self.RefreshCB);
	},
	RefreshCB: function (data)
	{
		var self = wStatistics;

		// Update data
		self.todayData.userCount += data.userCount;
		self.todayData.orderCount += data.orderCount;

		self.totalData.userCount += data.userCount;
		self.totalData.orderCount += data.orderCount;

		var cash = Math.floor(data.orderCount * (Math.random() * 10 + 1));
		self.todayData.cashTotal += cash;
		self.totalData.cashTotal += cash;

		// Display
		self.Display();

		// Call refresh again with a delay(2s)
		window.setTimeout(self.Refresh, 2000);
	}
};

$(wStatistics.Initialize);