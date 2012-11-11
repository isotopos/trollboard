define([
], function(){

  var config = {};
  config.urlRoot = 'http://localhost/';
  config.home = 'trollboard';
  config.log = true;

  config.urlVerifySession = 'http://localhost/trollboard/session';

  config.profile = 'http://localhost/trollboard/v1/user?providerId=github&providerToken='
  config.project = 'http://localhost/trollboard/v1/user/projects?providerId=github&providerToken='

  return config;
});
