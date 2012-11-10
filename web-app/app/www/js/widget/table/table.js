
var wTable =
{
	Initialize: function ()
	{
		var self = wTable;
	},
	Show: function ($container, data, options)
	{
		var self = wTable;

		options = options || {}; // Prevents options is undefined

		var wTableData = { data: data, options: options };

		// jQueryMX
		if ($("#inlineTable").length > 0)
			$container.html('inlineTable', wTableData);
		else
			$container.html('widget/table/table.ejs', wTableData);

		// Setup tooltip for the rendered template
		$("[data-title]", $container).tooltip();
	}
};


$(wTable.Initialize);