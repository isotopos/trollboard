define([
], function(){

  var config = {};
  config.urlRoot = 'http://trollboard.rs.af.cm/';
  config.home = 'trollboard';
  config.log = true;

  config.urlVerifySession = 'http://trollboard.rs.af.cm/trollboard/session';

  config.profile = 'http://trollboard.rs.af.cm/v1/user?providerId=github&providerToken='
  config.project = 'http://trollboard.rs.af.cm/v1/user/projects?providerId=github&providerToken='
  config.organization = 'http://trollboard.rs.af.cm/v1/user/organizations?providerId=github&providerToken='

  return config;
});
