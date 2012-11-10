var cpTemplates, dataBindig, fs, handlebars, sys, _;

sys = require("sys");
fs = require("fs");
handlebars = require("handlebars");
_ = require("underscore");

desc("This is the default task and doesnt do nada.");
task("default", function() {
  return console.log("jake -T  to see a list of avalible tasks");
});

desc("task for development it only runs switch-config[dev]");
task("dev", function() {
  var t;
  t = jake.Task["switch-config"];
  t.invoke.apply(t, ["dev"]);
  return console.log("web-app builded for development enviroment.");
});


desc("task for prod it only runs switch-config[prod]");
task("prod", function() {
  var t;
  t = jake.Task["switch-config"];
  t.invoke.apply(t, ["prod"]);
  return console.log("web-app builded for qa enviroment.");
});


desc("task to deploy prod");
task("deploy-prod", function() {
  var cmds;
  //cmds = ["jake deploy-field",
  cmds = [
  "rm -rf www/target",
  "jake prod",
  "jake opt"]
  console.log("going to execute this");
  console.log(cmds);
  return jake.exec(cmds, (function() {
    console.log("app ready");
    return complete();
  }), {
    stdout: true
  });
});

desc("RequireJS has an optimization tool it runs [r.js -o build.js]");
task("opt", function() {
  var cmds;
  jake.rmRf("target");
  console.log("Optimizing.... [r.js -o app.build.js]");
  cmds = ["node node_modules/requirejs/bin/r.js -o build.js"];
  return jake.exec(cmds, (function() {
    console.log("Optimize ready");
    return complete();
  }), {
    stdout: true
  });
});

task("switch-config", function() {
  var env, envJson, execFile, fileContents, file_list, schema;
  jake.rmRf(".tmp/");
  env = arguments[0];
  console.log("switching config ... to " + env);
  fileContents = fs.readFileSync("config.js", "utf8");
  schema = JSON.parse(fileContents);
  envJson = _.find(schema.enviroments, function(envc) {
    return envc.name === env;
  });
  console.log(envJson);
  file_list = "";
  execFile = require("child_process").execFile;
  return execFile("find", ["resources/", "-name", "*.hbs"], function(err, stdout, stderr) {
    file_list = "" + stdout.split("\n");
    return _.each(file_list.split(","), function(file) {
      if (file !== "") {
        console.log("processing ---> " + file);
        dataBindig(file, envJson.config);
        return cpTemplates();
      }
    });
  });
});

dataBindig = function(tmplFile, json) {
  var dir, encoding, fileTest, newDir, newName, tmpl;
  fileTest = fs.readFileSync(tmplFile, "utf8");
  tmpl = handlebars.compile(fileTest);
  newDir = ".tmp/";
  dir = tmplFile.split("/");
  newName = dir[dir.length - 1];
  dir = tmplFile.replace(newName, "");
  newDir = newDir + dir;
  newName = newName.replace(".hbs", "");
  jake.mkdirP(newDir);
  return fs.writeFileSync(newDir + newName, tmpl(json), encoding = "utf8", function(err) {
    if (err) {
      return console.log(err);
    } else {
      return console.log("the file" + tmplFile + " was processed to -> " + newDir + newName);
    }
  });
};

cpTemplates = function() {
  var cmds;
  cmds = ["cp -rf .tmp/resources/* www"];
  return jake.exec(cmds, (function() {
    console.log("resources populated and moved to -> /www");
    return complete();
  }), {
    stdout: true
  });
};
