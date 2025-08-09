package org.apache.hive.jdbc;

import java.util.Map;
import org.apache.http.HttpRequest;
import org.apache.http.client.CookieStore;
import org.apache.http.protocol.HttpContext;

public class HttpTokenAuthInterceptor extends HttpRequestInterceptorBase {
   private String tokenStr;
   private static final String HIVE_DELEGATION_TOKEN_HEADER = "X-Hive-Delegation-Token";

   public HttpTokenAuthInterceptor(String tokenStr, CookieStore cookieStore, String cn, boolean isSSL, Map additionalHeaders) {
      super(cookieStore, cn, isSSL, additionalHeaders);
      this.tokenStr = tokenStr;
   }

   protected void addHttpAuthHeader(HttpRequest httpRequest, HttpContext httpContext) throws Exception {
      httpRequest.addHeader("X-Hive-Delegation-Token", this.tokenStr);
   }
}
