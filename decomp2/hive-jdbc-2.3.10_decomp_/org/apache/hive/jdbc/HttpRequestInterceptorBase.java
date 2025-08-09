package org.apache.hive.jdbc;

import java.io.IOException;
import java.util.Map;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.client.CookieStore;
import org.apache.http.protocol.HttpContext;

public abstract class HttpRequestInterceptorBase implements HttpRequestInterceptor {
   CookieStore cookieStore;
   boolean isCookieEnabled;
   String cookieName;
   boolean isSSL;
   Map additionalHeaders;

   protected abstract void addHttpAuthHeader(HttpRequest var1, HttpContext var2) throws Exception;

   public HttpRequestInterceptorBase(CookieStore cs, String cn, boolean isSSL, Map additionalHeaders) {
      this.cookieStore = cs;
      this.isCookieEnabled = cs != null;
      this.cookieName = cn;
      this.isSSL = isSSL;
      this.additionalHeaders = additionalHeaders;
   }

   public void process(HttpRequest httpRequest, HttpContext httpContext) throws HttpException, IOException {
      try {
         if (this.isCookieEnabled) {
            httpContext.setAttribute("http.cookie-store", this.cookieStore);
         }

         if (!this.isCookieEnabled || httpContext.getAttribute("hive.server2.retryserver") == null && (this.cookieStore == null || this.cookieStore != null && Utils.needToSendCredentials(this.cookieStore, this.cookieName, this.isSSL)) || httpContext.getAttribute("hive.server2.retryserver") != null && httpContext.getAttribute("hive.server2.retryserver").equals("true")) {
            this.addHttpAuthHeader(httpRequest, httpContext);
         }

         if (this.isCookieEnabled) {
            httpContext.setAttribute("hive.server2.retryserver", "false");
         }

         if (this.additionalHeaders != null) {
            for(Map.Entry entry : this.additionalHeaders.entrySet()) {
               httpRequest.addHeader((String)entry.getKey(), (String)entry.getValue());
            }
         }

      } catch (Exception e) {
         throw new HttpException(e.getMessage(), e);
      }
   }
}
