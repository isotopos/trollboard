// The codes below are for sample purpose, please feel free to modify/test them



// ********************************
// Static wRealtimeGraph Class
// ********************************

var wRealtimeGraph =
{
	$widget: null,
	plotter: null,
	realtimeSeriesCollection: null,
	maxPoint: 30,
	Initialize: function ()
	{
		var self = wRealtimeGraph;

		// Show widgets
		Mojitoring.LoadHTML($('#wRealtimeGraph'), 'widget/statistics/realtime_graph.htm', self.InitializeCB);
	},

	InitializeCB: function ()
	{
		var self = wRealtimeGraph;

		self.$flotGraph = $("#wFlotGraph");

		// Initialize realtimeSeriesCollection
		var series1 =
		{
			color: "#a00000",
			data: self.GetEmptySeries(self.maxPoint),
			label: "Users",
			hoverable: true
		};

		var series2 =
		{
			color: "#00a000",
			data: self.GetEmptySeries(self.maxPoint),
			label: "Order items",
			hoverable: true,
			highlightColor: "#0000ff"
		};

		self.realtimeSeriesCollection = [series1, series2];

		// Setup plotter
		var options =
		{
			margin: { minBorderMargin: 0 },
			series: { shadowSize: 0, lines: { show: true }, points: { show: true} }, // drawing is faster without shadows
			yaxes: [{ position: "left", min: 0, max: 10 }, { position: "right", min: 0, max: 5}],

			xaxis: { min: -(self.maxPoint - 1), max: 0, tickFormatter: function (number, axis) { return String(number) + 's' } }
		};

		// Initialize plots and show the graph
		self.plotter = $.plot(self.$flotGraph, self.realtimeSeriesCollection, options);

		// Resize event
		$(window).on("resize", self.Resize);

		// Call Refresh  with a delay(1000)
		window.setTimeout(self.Refresh, 1000);
	},
	Refresh: function ()
	{
		var self = wRealtimeGraph;

		wDataServer.GetLatestData(self.RefreshCB);
	},
	RefreshCB: function (data)
	{
		var self = wRealtimeGraph;

		// Refresh the plotter with new data
		var series1 = self.realtimeSeriesCollection[0];
		var series2 = self.realtimeSeriesCollection[1];

		series1.data.shift(); // remove the first point of the 1st series of points
		series2.data.shift(); // remove the first point of the 2nd series of points

		series1.data.push([0, data.userCount]); // remove the first point of the 1st series of points
		series2.data.push([0, data.orderCount]); // remove the first point of the 2nd series of points

		self.ReformatSeries(series1.data);
		self.ReformatSeries(series2.data);


		// Update the flot plotter
		self.plotter.setData(self.realtimeSeriesCollection);
		self.plotter.draw();

		// Call refresh again with a delay
		window.setTimeout(self.Refresh, 1000);
	},
	Resize: function ()
	{
		var self = wRealtimeGraph;

		if (self.plotter)
		{
			self.plotter.resize();
			self.plotter.setupGrid();
			self.plotter.draw();
		}

	},
	GetEmptySeries: function (size)
	{
		var series = [];
		var x = -(size - 1);
		var point;

		for (var i = 0; i < size; i++)
		{
			point = [x, 0];
			x++;
			series.push(point);
		}

		return series;
	},
	ReformatSeries: function (series)
	{
		var point;
		var x = -(series.length - 1);
		for (var i = 0; i < series.length; i++)
		{
			point = series[i];
			point[0] = x++;
		}
	}
};

$(wRealtimeGraph.Initialize);


