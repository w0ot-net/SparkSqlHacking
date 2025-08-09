package org.sparkproject.jetty.client;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Stream;
import org.sparkproject.jetty.client.api.Request;
import org.sparkproject.jetty.client.api.Response;
import org.sparkproject.jetty.client.api.Result;
import org.sparkproject.jetty.http.HttpField;
import org.sparkproject.jetty.http.HttpHeader;
import org.sparkproject.jetty.io.EndPoint;
import org.sparkproject.jetty.util.Callback;

public class UpgradeProtocolHandler implements ProtocolHandler {
   private final List protocols = List.of("websocket", "h2c");

   public String getName() {
      return "upgrade";
   }

   public boolean accept(Request request, Response response) {
      boolean upgraded = 101 == response.getStatus();
      boolean accepted = false;
      if (upgraded) {
         accepted = this.acceptHeaders(request, response);
      }

      return upgraded && accepted;
   }

   protected boolean acceptHeaders(Request request, Response response) {
      HttpField responseUpgrade = response.getHeaders().getField(HttpHeader.UPGRADE);
      if (responseUpgrade != null) {
         Stream var10000 = this.protocols.stream();
         Objects.requireNonNull(responseUpgrade);
         if (var10000.anyMatch(responseUpgrade::contains)) {
            return true;
         }
      }

      HttpField requestUpgrade = request.getHeaders().getField(HttpHeader.UPGRADE);
      boolean var6;
      if (requestUpgrade != null) {
         Stream var5 = this.protocols.stream();
         Objects.requireNonNull(requestUpgrade);
         if (var5.anyMatch(requestUpgrade::contains)) {
            var6 = true;
            return var6;
         }
      }

      var6 = false;
      return var6;
   }

   public Response.Listener getResponseListener() {
      return new Response.Listener.Adapter() {
         public void onComplete(Result result) {
            HttpResponse response = (HttpResponse)result.getResponse();
            HttpRequest request = (HttpRequest)response.getRequest();
            if (result.isSucceeded()) {
               try {
                  HttpConversation conversation = request.getConversation();
                  HttpUpgrader upgrader = (HttpUpgrader)conversation.getAttribute(HttpUpgrader.class.getName());
                  if (upgrader == null) {
                     throw new HttpResponseException("101 response without " + HttpUpgrader.class.getSimpleName(), response);
                  }

                  EndPoint endPoint = (EndPoint)conversation.getAttribute(EndPoint.class.getName());
                  if (endPoint == null) {
                     throw new HttpResponseException("Upgrade without " + EndPoint.class.getSimpleName(), response);
                  }

                  Callback var10003 = Callback.NOOP;
                  Objects.requireNonNull(var10003);
                  upgrader.upgrade(response, endPoint, Callback.from((Runnable)(var10003::succeeded), (Consumer)((xx) -> UpgradeProtocolHandler.this.forwardFailureComplete(request, (Throwable)null, response, xx))));
               } catch (Throwable x) {
                  UpgradeProtocolHandler.this.forwardFailureComplete(request, (Throwable)null, response, x);
               }
            } else {
               UpgradeProtocolHandler.this.forwardFailureComplete(request, result.getRequestFailure(), response, result.getResponseFailure());
            }

         }
      };
   }

   private void forwardFailureComplete(HttpRequest request, Throwable requestFailure, Response response, Throwable responseFailure) {
      HttpConversation conversation = request.getConversation();
      conversation.updateResponseListeners((Response.ResponseListener)null);
      List<Response.ResponseListener> responseListeners = conversation.getResponseListeners();
      ResponseNotifier notifier = new ResponseNotifier();
      notifier.forwardFailure(responseListeners, response, responseFailure);
      notifier.notifyComplete(responseListeners, new Result(request, requestFailure, response, responseFailure));
   }
}
