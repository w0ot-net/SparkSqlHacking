package org.sparkproject.jetty.client;

import java.util.List;
import org.sparkproject.jetty.client.api.Request;
import org.sparkproject.jetty.client.api.Response;
import org.sparkproject.jetty.client.api.Result;
import org.sparkproject.jetty.client.util.BufferingResponseListener;
import org.sparkproject.jetty.http.HttpHeader;
import org.sparkproject.jetty.http.HttpHeaderValue;

public class ContinueProtocolHandler implements ProtocolHandler {
   public static final String NAME = "continue";
   private static final String ATTRIBUTE = ContinueProtocolHandler.class.getName() + ".100continue";
   private final ResponseNotifier notifier = new ResponseNotifier();

   public String getName() {
      return "continue";
   }

   public boolean accept(Request request, Response response) {
      boolean is100 = response.getStatus() == 100;
      boolean expect100 = request.getHeaders().contains(HttpHeader.EXPECT, HttpHeaderValue.CONTINUE.asString());
      boolean handled100 = request.getAttributes().containsKey(ATTRIBUTE);
      return (is100 || expect100) && !handled100;
   }

   public Response.Listener getResponseListener() {
      return new ContinueListener();
   }

   protected void onContinue(Request request) {
   }

   protected class ContinueListener extends BufferingResponseListener {
      public void onSuccess(Response response) {
         Request request = response.getRequest();
         HttpConversation conversation = ((HttpRequest)request).getConversation();
         request.attribute(ContinueProtocolHandler.ATTRIBUTE, Boolean.TRUE);
         conversation.updateResponseListeners((Response.ResponseListener)null);
         HttpExchange exchange = (HttpExchange)conversation.getExchanges().peekLast();
         if (response.getStatus() == 100) {
            exchange.resetResponse();
            exchange.proceed((Throwable)null);
            ContinueProtocolHandler.this.onContinue(request);
         } else {
            List<Response.ResponseListener> listeners = exchange.getResponseListeners();
            HttpContentResponse contentResponse = new HttpContentResponse(response, this.getContent(), this.getMediaType(), this.getEncoding());
            ContinueProtocolHandler.this.notifier.forwardSuccess(listeners, contentResponse);
            exchange.proceed(new HttpRequestException("Expectation failed", request));
         }

      }

      public void onFailure(Response response, Throwable failure) {
         HttpConversation conversation = ((HttpRequest)response.getRequest()).getConversation();
         conversation.setAttribute(ContinueProtocolHandler.ATTRIBUTE, Boolean.TRUE);
         conversation.updateResponseListeners((Response.ResponseListener)null);
         HttpExchange exchange = (HttpExchange)conversation.getExchanges().peekLast();

         assert exchange.getResponse() == response;

         List<Response.ResponseListener> listeners = exchange.getResponseListeners();
         HttpContentResponse contentResponse = new HttpContentResponse(response, this.getContent(), this.getMediaType(), this.getEncoding());
         ContinueProtocolHandler.this.notifier.forwardFailureComplete(listeners, exchange.getRequest(), exchange.getRequestFailure(), contentResponse, failure);
      }

      public void onComplete(Result result) {
      }
   }
}
