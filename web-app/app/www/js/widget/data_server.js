/**
 * Mojitoring
 * steven@w33kn.com
**/

/*
	Data Server library simulate server callbacks
	It provides data (JSON format) as if it were from server.
	If you are familiar with Ajax, the code would be very simple to read

	You can easily adapt the design with your favorite server side language (php, node.js, java, asp.net, ruby on rail...)

	In a real case, you should provide data from a database server.
*/

// ServerStatus collection data
var serverStatusCollection =
[
	{
		serverName: "Server 1",
		serverStatus: "ok",
		lastReboot: new Date(),
		cpu: 5,
		applicationCollection:
		[
			{ applicationName: "Website", applicationStatus: "ok" },
			{ applicationName: "Webservice 1", applicationStatus: "ok" },
			{ applicationName: "Webservice 2", applicationStatus: "ok" }
		]
	},
	{
		serverName: "Server 2",
		serverStatus: "ok",
		lastReboot: new Date(),
		cpu: 5,
		applicationCollection:
		[
			{ applicationName: "Website", applicationStatus: "ok" },
			{ applicationName: "Webservice 1", applicationStatus: "warning" },
			{ applicationName: "Webservice 2", applicationStatus: "danger" }
		]
	}
];


	var wDataServer =
{
	data: null,
	Initialize: function ()
	{
		var self = wDataServer;

		self.data = {};
	},
	GetLatestData: function (cb)
	{
		if (!cb)
		{
			alert("wDataServer.GetLatestData error: cb (Callback method) missing");
			return;
		}

		// Here you should retrieve data from the server: ajax + cb (callback)
		// But for the demo purpose, we emulate the server data and do the callback
		var self = wDataServer;

		self.data.userCount = self.GetRandomInt(1, 10); // data for the 1st series: value between 0 to 50
		self.data.orderCount = self.GetRandomInt(0, 5); // data for the 2nd series: value between 0 to 100
		self.data.cashTotal = self.GetRandomInt(0, 100); // data for the 2nd series: value between 0 to 100

		// Callback as if it was an ajax callback
		cb(self.data);
	},
	GetLatestError: function (count, cb)
	{
		if (!cb)
		{
			alert("wDataServer.GetLatestError error: cb (Callback method) missing");
			return;
		}

		var self = wDataServer;


		// We simulate errors
		var typeCollection = ["login error", "error 404", "error 500", "unkown"];

		var errorCollection = [];
		var error, typeIndex, description, cssClass;
		for (var i = 0; i < count; i++)
		{
			cssClass= null;
			
			typeIndex = Math.floor(Math.random() * typeCollection.length);
			switch (typeIndex)
			{
				case 0:
					description = "login = " + self.GetRandomString(6, 16);
					break;
				case 1:
					description = "page = " + self.GetRandomString(6, 8) + ".htm";
					cssClass = "info";
					break;
				case 2:
					description = "page = " + self.GetRandomString(6, 8) + ".htm, error at line " + String(self.GetRandomInt(4, 1000));
					cssClass = "error";
					break;
				default:
					description = "unexpected error at page " +self.GetRandomString(6, 8) + ".htm";
					cssClass = "warning";
					break;
			}

			error =
			{
				id: "#" + String(Math.ceil(Math.random() * 10000)),
				type: typeCollection[typeIndex],
				description: description
			}

			if (cssClass)
				error.cssClass = cssClass;
			errorCollection.push(error);
		}

		// Callback as if it was an ajax callback
		cb(errorCollection);
	},
	GetUserCollection: function (count, cb)
	{
		if (!cb)
		{
			alert("wDataServer.GetUserCollection error: cb (Callback method) missing");
			return;
		}

		var self = wDataServer;

		var userCollection = [];
		var user;

		// Create random data
		var firstname, lastname, company;
		for (var i = 0; i < count; i++)
		{
			firstname = self.GetRandomString(3, 9);
			lastname = self.GetRandomString(3, 8);
			company = self.GetRandomString(3, 9);
			user =
			{
				id: i + 1,
				lastname: lastname.toUpperCase(),
				firstname: firstname, //[0].toUpperCase() + firstname.substr(1),
				company: company,
				email: firstname + "." + lastname + "@" + company + ".com"
			};

			userCollection.push(user);
		}

		// Callback as if it was an ajax callback
		cb(userCollection.reverse());
	},
	GetServerStatusCollection: function (cb)
	{
		if (!cb)
		{
			alert("wDataServer.GetServerStatusCollection error: cb (Callback method) missing");
		}

		var serverStatus;

		// We simulate the changes of each serverStatus of the collection
		for (var i = 0; i < serverStatusCollection.length; i++)
		{
			serverStatus = serverStatusCollection[i];

			// Simulate cpu charge
			var a = Math.round(Math.random() * 100);
			serverStatus.cpu = Math.round((a * a) / 100);

			// Simulate server status
			a = Math.round(Math.random() * 100);

			serverStatus.serverStatus = "ok";

			if (a > 50)
				serverStatus.serverStatus = "warning";

			if (a > 80)
				serverStatus.serverStatus = "danger";
		}

		// Callback as if it was an ajax callback
		cb(serverStatusCollection);
	},
	GetRandomInt: function (yMin, yMax, prevValue)
	{
		var amplitude = yMax - yMin;

		var yRandom = prevValue || yMin + (amplitude >> 1); // faster than min + Math.floor(amplitude / 2)

		yRandom += Math.floor(Math.random() * (amplitude >> 1) - (amplitude >> 2));
		if (yRandom < yMin)
			yRandom = yMin;

		if (yRandom > yMax)
			yRandom = yMax;

		return yRandom;
	},
	GetRandomString: function (minLength, maxLength)
	{
		var alpha = "abcdefghijklmnopqrstuvwxyz.eeeeeeeeeeeeeeeeeeaaaaaaaaaaaaaaiiiiiiiiiooooooooooooouuuuuuuuuu";
		var alphaLen = alpha.length;
		var len = minLength + Math.round(Math.random() * (maxLength - minLength));

		var value = "";
		for (var i = 0; i < len; i++)
		{
			value += alpha[Math.floor(Math.random() * alphaLen)];
		}
		return value;
	}
};

$(wDataServer.Initialize);