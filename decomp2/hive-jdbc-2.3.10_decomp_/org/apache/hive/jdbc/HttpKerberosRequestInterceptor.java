package org.apache.hive.jdbc;

import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.hive.service.auth.HttpAuthUtils;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.client.CookieStore;
import org.apache.http.protocol.HttpContext;

public class HttpKerberosRequestInterceptor extends HttpRequestInterceptorBase {
   String principal;
   String host;
   String serverHttpUrl;
   boolean assumeSubject;
   private static ReentrantLock kerberosLock = new ReentrantLock(true);

   public HttpKerberosRequestInterceptor(String principal, String host, String serverHttpUrl, boolean assumeSubject, CookieStore cs, String cn, boolean isSSL, Map additionalHeaders) {
      super(cs, cn, isSSL, additionalHeaders);
      this.principal = principal;
      this.host = host;
      this.serverHttpUrl = serverHttpUrl;
      this.assumeSubject = assumeSubject;
   }

   protected void addHttpAuthHeader(HttpRequest httpRequest, HttpContext httpContext) throws Exception {
      try {
         kerberosLock.lock();
         String kerberosAuthHeader = HttpAuthUtils.getKerberosServiceTicket(this.principal, this.host, this.serverHttpUrl, this.assumeSubject);
         httpRequest.addHeader("Authorization: Negotiate ", kerberosAuthHeader);
      } catch (Exception e) {
         throw new HttpException(e.getMessage(), e);
      } finally {
         kerberosLock.unlock();
      }

   }
}
