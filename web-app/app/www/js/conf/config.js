define([
], function(){

  var config = {};
  config.urlRoot = 'http://localhost/';
  config.home = 'trollboard';
  config.log = true;

  config.urlVerifySession = 'http://localhost/trollboard/session';

  config.githubProfile = 'http://localhost/trollboard/v1/user?providerId=github&providerToken='

  return config;
});
