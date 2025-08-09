package org.sparkproject.jetty.client;

import java.net.URI;
import org.sparkproject.jetty.client.api.Request;
import org.sparkproject.jetty.client.api.Response;
import org.sparkproject.jetty.http.HttpHeader;

public class ProxyAuthenticationProtocolHandler extends AuthenticationProtocolHandler {
   public static final String NAME = "proxy-authenticate";
   private static final String ATTRIBUTE = ProxyAuthenticationProtocolHandler.class.getName() + ".attribute";

   public ProxyAuthenticationProtocolHandler(HttpClient client) {
      this(client, 16384);
   }

   public ProxyAuthenticationProtocolHandler(HttpClient client, int maxContentLength) {
      super(client, maxContentLength);
   }

   public String getName() {
      return "proxy-authenticate";
   }

   public boolean accept(Request request, Response response) {
      return response.getStatus() == 407;
   }

   protected HttpHeader getAuthenticateHeader() {
      return HttpHeader.PROXY_AUTHENTICATE;
   }

   protected HttpHeader getAuthorizationHeader() {
      return HttpHeader.PROXY_AUTHORIZATION;
   }

   protected URI getAuthenticationURI(Request request) {
      HttpDestination destination = (HttpDestination)this.getHttpClient().resolveDestination(request);
      ProxyConfiguration.Proxy proxy = destination.getProxy();
      return proxy != null ? proxy.getURI() : request.getURI();
   }

   protected String getAuthenticationAttribute() {
      return ATTRIBUTE;
   }
}
