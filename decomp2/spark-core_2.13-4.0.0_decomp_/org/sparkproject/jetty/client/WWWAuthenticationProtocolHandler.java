package org.sparkproject.jetty.client;

import java.net.URI;
import org.sparkproject.jetty.client.api.Request;
import org.sparkproject.jetty.client.api.Response;
import org.sparkproject.jetty.http.HttpHeader;

public class WWWAuthenticationProtocolHandler extends AuthenticationProtocolHandler {
   public static final String NAME = "www-authenticate";
   private static final String ATTRIBUTE = WWWAuthenticationProtocolHandler.class.getName() + ".attribute";

   public WWWAuthenticationProtocolHandler(HttpClient client) {
      this(client, 16384);
   }

   public WWWAuthenticationProtocolHandler(HttpClient client, int maxContentLength) {
      super(client, maxContentLength);
   }

   public String getName() {
      return "www-authenticate";
   }

   public boolean accept(Request request, Response response) {
      return response.getStatus() == 401;
   }

   protected HttpHeader getAuthenticateHeader() {
      return HttpHeader.WWW_AUTHENTICATE;
   }

   protected HttpHeader getAuthorizationHeader() {
      return HttpHeader.AUTHORIZATION;
   }

   protected URI getAuthenticationURI(Request request) {
      return request.getURI();
   }

   protected String getAuthenticationAttribute() {
      return ATTRIBUTE;
   }
}
