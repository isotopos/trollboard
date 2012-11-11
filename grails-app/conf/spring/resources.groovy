// Place your Spring DSL code here
beans = {
  httpClient(wslite.http.HTTPClient) {
    connectTimeout = 5000
    readTimeout = 10000
    useCaches = false
    followRedirects = false
    sslTrustAllCerts = true
  }

  restGithubClient(wslite.rest.RESTClient) {
    url = 'https://github.com/'
    httpClient = ref('httpClient')
    defaultAcceptHeader = 'application/json'
    defaultCharset = 'UTF-8'
  }
}
