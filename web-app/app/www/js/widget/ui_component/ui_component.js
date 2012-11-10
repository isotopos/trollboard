var wUIComponent =
{
	Initialize: function()
	{
		var self = wUIComponent;
	},
	Show: function()
	{
		Mojitoring.LoadHTML($("#uiPane"), "widget/ui_component/ui_component.htm");
	}
};

$(wUIComponent.Initialize);