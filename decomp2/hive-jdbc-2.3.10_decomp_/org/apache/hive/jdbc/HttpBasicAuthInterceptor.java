package org.apache.hive.jdbc;

import java.util.Map;
import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CookieStore;
import org.apache.http.impl.auth.AuthSchemeBase;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.protocol.HttpContext;

public class HttpBasicAuthInterceptor extends HttpRequestInterceptorBase {
   UsernamePasswordCredentials credentials;
   AuthSchemeBase authScheme = new BasicScheme();

   public HttpBasicAuthInterceptor(String username, String password, CookieStore cookieStore, String cn, boolean isSSL, Map additionalHeaders) {
      super(cookieStore, cn, isSSL, additionalHeaders);
      if (username != null) {
         this.credentials = new UsernamePasswordCredentials(username, password);
      }

   }

   protected void addHttpAuthHeader(HttpRequest httpRequest, HttpContext httpContext) throws Exception {
      Header basicAuthHeader = this.authScheme.authenticate(this.credentials, httpRequest, httpContext);
      httpRequest.addHeader(basicAuthHeader);
   }
}
