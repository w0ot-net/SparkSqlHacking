package io.fabric8.kubernetes.client.vertx;

import io.fabric8.kubernetes.client.http.AsyncBody;
import io.fabric8.kubernetes.client.http.StandardHttpClient;
import io.fabric8.kubernetes.client.http.StandardHttpRequest;
import io.fabric8.kubernetes.client.http.StandardWebSocketBuilder;
import io.fabric8.kubernetes.client.http.WebSocket;
import io.fabric8.kubernetes.client.http.WebSocketResponse;
import io.fabric8.kubernetes.client.http.WebSocketUpgradeResponse;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.RequestOptions;
import io.vertx.core.http.UpgradeRejectedException;
import io.vertx.core.http.WebSocketConnectOptions;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;

public class VertxHttpClient extends StandardHttpClient {
   private final Vertx vertx;
   private final HttpClient client;
   private final boolean closeVertx;

   VertxHttpClient(VertxHttpClientBuilder vertxHttpClientBuilder, AtomicBoolean closed, HttpClient client, boolean closeVertx) {
      super(vertxHttpClientBuilder, closed);
      this.vertx = vertxHttpClientBuilder.vertx;
      this.client = client;
      this.closeVertx = closeVertx;
   }

   HttpClient getClient() {
      return this.client;
   }

   public CompletableFuture buildWebSocketDirect(StandardWebSocketBuilder standardWebSocketBuilder, WebSocket.Listener listener) {
      WebSocketConnectOptions options = new WebSocketConnectOptions();
      if (standardWebSocketBuilder.getSubprotocol() != null) {
         options.setSubProtocols(Collections.singletonList(standardWebSocketBuilder.getSubprotocol()));
      }

      StandardHttpRequest request = standardWebSocketBuilder.asHttpRequest();
      if (request.getTimeout() != null) {
         options.setTimeout(request.getTimeout().toMillis());
      }

      request.headers().entrySet().forEach((e) -> ((List)e.getValue()).stream().forEach((v) -> options.addHeader((String)e.getKey(), v)));
      options.setAbsoluteURI(WebSocket.toWebSocketUri(request.uri()).toString());
      CompletableFuture<WebSocketResponse> response = new CompletableFuture();
      this.client.webSocket(options).onSuccess((ws) -> {
         VertxWebSocket ret = new VertxWebSocket(ws, listener);
         ret.init();
         response.complete(new WebSocketResponse(new WebSocketUpgradeResponse(request), ret));
      }).onFailure((t) -> {
         if (t instanceof UpgradeRejectedException) {
            UpgradeRejectedException handshake = (UpgradeRejectedException)t;
            WebSocketUpgradeResponse upgradeResponse = new WebSocketUpgradeResponse(request, handshake.getStatus(), VertxHttpRequest.toHeadersMap(handshake.getHeaders()));
            response.complete(new WebSocketResponse(upgradeResponse, handshake));
         }

         response.completeExceptionally(t);
      });
      return response;
   }

   public CompletableFuture consumeBytesDirect(StandardHttpRequest request, AsyncBody.Consumer consumer) {
      RequestOptions options = new RequestOptions();
      request.headers().forEach((k, l) -> l.forEach((v) -> options.addHeader(k, v)));
      options.setAbsoluteURI(request.uri().toString());
      options.setMethod(HttpMethod.valueOf(request.method()));
      if (request.getTimeout() != null) {
         options.setTimeout(request.getTimeout().toMillis());
      }

      Optional.ofNullable(request.getContentType()).ifPresent((s) -> options.putHeader(HttpHeaders.CONTENT_TYPE, s));
      if (request.isExpectContinue()) {
         options.putHeader(HttpHeaders.EXPECT, HttpHeaders.CONTINUE);
      }

      return (new VertxHttpRequest(this.vertx, options, request)).consumeBytes(this.client, consumer);
   }

   public void doClose() {
      try {
         this.client.close();
      } finally {
         if (this.closeVertx) {
            this.vertx.close();
         }

      }

   }
}
