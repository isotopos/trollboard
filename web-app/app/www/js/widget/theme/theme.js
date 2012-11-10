/**
 * Mojitoring
 * steven@w33kn.com
**/


/* Theme */

var wTheme =
{
	id: "wTheme",
	currentTheme: "irongrip",
	Initialize: function ()
	{
		var self = wTheme;
		$(document.body).addClass(self.currentTheme);
	},
	Change: function (theme)
	{
		var self = wTheme;
		if (self.currentTheme === theme)
			retrun;

		$(document.body).removeClass(self.currentTheme);
		$(document.body).addClass(theme);
		self.currentTheme = theme;
	},
	Hide: function ()
	{
		var self = wTheme;

		// Activate home tab
		$("#homeTab").tab('show');
	},
	Show: function ()
	{
		var self = wTheme;

		Mojitoring.LoadHTML($('#themePane'), 'widget/theme/theme.html', self.ShowCB);
	},
	ShowCB: function()
	{
		var $widget = $("#wTheme");
		$("[data-title]", $widget).tooltip();
	}
};

$(wTheme.Initialize);