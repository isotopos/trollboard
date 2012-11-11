var wUser =
{
	Initialize: function ()
	{
		var self = wUser;

		// Show wUserFeature widget
		Mojitoring.LoadHTML($('#wUserFeature'), 'widget/user/user_feature.htm');

		// Show wUserTracker
		self.ShowUserTracker();
	},
	Login: function ()
	{
		var self = wUser;
		$("#homeTab").tab('show');
	},
	Logout: function ()
	{
		var self = wUser;
		self.ShowLoginWidget();
	},
	ShowLoginWidget: function ()
	{
		Mojitoring.LoadHTML($('#loginPane'), 'widget/user/login.html');
		$("#loginTab").tab('show');
	},
	ShowUserList: function ()
	{
		var self = wUser;

		wDataServer.GetUserCollection(100, self.ShowUserListCB);
	},
	ShowUserListCB: function (userCollection)
	{
		var self = wUser;

		var options =
		{
			title: "User list",
			caption: "Note: this is a use case example, please implements your features at your will.",
			columns: ["id", "lastname", "firstname", "company", "email"],
			columnsClass: [null, null, null, "hidable", "hidable"],
			searchBar: true,
			actionView: "wUser.ShowView",
			actionEdit: "wUser.ShowEdit",
			actionDelete: true,
			icon: "icon-user",
			headerClass: " mojitoPanelHeader-blue"
		};

		wTable.Show($("#userListPane"), userCollection, options);
	},
	ShowUserTracker: function ()
	{
		var self = wUser;

		wDataServer.GetUserCollection(5, self.ShowUserTrackerCB);
	},
	ShowUserTrackerCB: function (userCollection)
	{
		var self = wUser;

		var options =
		{
			title: "Online active users",
			columns: ["id", "lastname", "firstname", "email"],
			columnsClass: [null, null, null, "hidable"],
			actionView: "wUser.ShowView",
			icon: "icon-user",
			headerClass: " mojitoPanelHeader-blue"
		};
		wTable.Show($("#wUserTracker"), userCollection, options);
	},
	ShowEdit: function ()
	{
		var self = wUser;
		$('#emptyContainer').load('widget/user/user_edit.htm', self.ShowEditCB);
	},
	ShowEditCB: function ()
	{
		var self = wUser;

		$('#userEdit').modal();
	},
	ShowView: function ()
	{
		var self = wUser;
		$('#emptyContainer').load('widget/user/user_view.htm', self.ShowViewCB);
	},
	ShowViewCB: function ()
	{
		var self = wUser;

		$('#userView').modal();
	}
};

$(wUser.Initialize);