package org.apache.hive.jdbc;

import java.io.IOException;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;

public class XsrfHttpRequestInterceptor implements HttpRequestInterceptor {
   private static boolean injectHeader = true;

   public static void enableHeaderInjection(boolean enabled) {
      injectHeader = enabled;
   }

   public void process(HttpRequest httpRequest, HttpContext httpContext) throws HttpException, IOException {
      if (injectHeader) {
         httpRequest.addHeader("X-XSRF-HEADER", "true");
      }

   }
}
