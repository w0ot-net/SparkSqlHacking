package org.sparkproject.jetty.client;

import org.sparkproject.jetty.client.api.Request;
import org.sparkproject.jetty.client.api.Response;
import org.sparkproject.jetty.client.api.Result;
import org.sparkproject.jetty.http.HttpField;
import org.sparkproject.jetty.http.HttpHeader;

public class RedirectProtocolHandler extends Response.Listener.Adapter implements ProtocolHandler {
   public static final String NAME = "redirect";
   private final HttpRedirector redirector;

   public RedirectProtocolHandler(HttpClient client) {
      this.redirector = new HttpRedirector(client);
   }

   public String getName() {
      return "redirect";
   }

   public boolean accept(Request request, Response response) {
      return this.redirector.isRedirect(response) && request.isFollowRedirects();
   }

   public Response.Listener getResponseListener() {
      return this;
   }

   public boolean onHeader(Response response, HttpField field) {
      return field.getHeader() != HttpHeader.CONTENT_ENCODING;
   }

   public void onComplete(Result result) {
      Request request = result.getRequest();
      Response response = result.getResponse();
      if (result.isSucceeded()) {
         this.redirector.redirect(request, response, (Response.CompleteListener)null);
      } else {
         this.redirector.fail(request, response, result.getFailure());
      }

   }
}
