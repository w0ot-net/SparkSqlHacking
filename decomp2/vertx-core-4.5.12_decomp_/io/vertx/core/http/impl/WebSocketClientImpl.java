package io.vertx.core.http.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.http.ClientWebSocket;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.WebSocket;
import io.vertx.core.http.WebSocketClient;
import io.vertx.core.http.WebSocketConnectOptions;
import io.vertx.core.impl.CloseFuture;
import io.vertx.core.impl.VertxInternal;

public class WebSocketClientImpl extends HttpClientBase implements WebSocketClient {
   public WebSocketClientImpl(VertxInternal vertx, HttpClientOptions options, CloseFuture closeFuture) {
      super(vertx, options, closeFuture);
   }

   public ClientWebSocket webSocket() {
      return new ClientWebSocketImpl(this);
   }

   public void connect(WebSocketConnectOptions options, Handler handler) {
      this.webSocket(options, handler);
   }

   public Future connect(WebSocketConnectOptions options) {
      return this.webSocket(options);
   }
}
