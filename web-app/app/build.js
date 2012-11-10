({
  appDir:"www",
  baseUrl:"js",
  dir:"target/www",
  stubModules: ['cs'],
  modules:[
    //{
      //name:"common",
      //include: ['jquery',
                //'underscore',
                //'backbone',
                //'handlebars',
                //'text',
                //'async',
                ////'templates',
                //'jqueryform',
                //'jquery.prettyPhoto',
                //'modernizr',
                //'jquery.cookie',
                //'jquery.validate',
                //'jquery.validate.es',
                //'json2',
                //'jquery.ui',
                //'jquery.ui.selectmenu',
                //'bootstrap',
                //'jquery.colorbox'],
      ////exclude: ['coffee-script']

    //},
    {
      name:"store-app",
      exclude: [ 'coffee-script']
    }
  ],
  paths:{
    jquery:'vendor/jquery-1.7.2',
    underscore:'vendor/underscore',
    backbone:'vendor/backbone',
    handlebars:"vendor/handlebars-1.0.0.beta.6",
    chaplin:'vendor/chaplin-1.0.0-pre-91490ab',
    text:'vendor/require-text-1.06',
    cs:'vendor/require-cs-0.4.2',
    "coffee-script":'vendor/coffee-script-1.3.3',
    async: 'vendor/async',
    templates:'../templates',
    jqueryform: 'vendor/jquery.form',
    'jquery.prettyPhoto': 'vendor/jquery.prettyPhoto',
    modernizr: 'vendor/modernizr',
    'jquery.cookie': 'vendor/jquery.cookie',
    'jquery.validate': 'vendor/jquery.validate',
    'jquery.validate.es': 'vendor/localization/messages_es',
    'json2': 'vendor/json2',
    'jquery.ui': 'vendor/jquery-ui-1.8.21.min',  //remove if new components are added
    'jquery.ui.selectmenu' : 'vendor/jquery.ui.selectmenu',
    'bootstrap' : 'vendor/bootstrap',
    'jquery.serializeJSON': 'vendor/jquery.serializeJSON',
    'moment': 'vendor/moment-1.7.2',
    'moment.es': 'vendor/localization/es'
  },
  optimezeCss: 'standard'
  //optimize: 'none'

})
