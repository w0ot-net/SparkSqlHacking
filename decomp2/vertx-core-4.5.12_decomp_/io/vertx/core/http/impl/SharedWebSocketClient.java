package io.vertx.core.http.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.http.ClientWebSocket;
import io.vertx.core.http.WebSocket;
import io.vertx.core.http.WebSocketClient;
import io.vertx.core.http.WebSocketConnectOptions;
import io.vertx.core.impl.CloseFuture;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.impl.VertxInternal;
import io.vertx.core.impl.future.PromiseInternal;
import io.vertx.core.net.SSLOptions;

public class SharedWebSocketClient implements WebSocketClient {
   public static final String SHARED_MAP_NAME = "__vertx.shared.webSocketClients";
   private final VertxInternal vertx;
   private final CloseFuture closeFuture;
   private final WebSocketClient delegate;

   public SharedWebSocketClient(VertxInternal vertx, CloseFuture closeFuture, WebSocketClient delegate) {
      this.vertx = vertx;
      this.closeFuture = closeFuture;
      this.delegate = delegate;
   }

   public void close(Handler handler) {
      ContextInternal closingCtx = this.vertx.getOrCreateContext();
      this.closeFuture.close(handler != null ? closingCtx.promise(handler) : null);
   }

   public Future close() {
      ContextInternal closingCtx = this.vertx.getOrCreateContext();
      PromiseInternal<Void> promise = closingCtx.promise();
      this.closeFuture.close(promise);
      return promise.future();
   }

   public ClientWebSocket webSocket() {
      return this.delegate.webSocket();
   }

   public void connect(WebSocketConnectOptions options, Handler handler) {
      this.delegate.connect(options, handler);
   }

   public Future connect(WebSocketConnectOptions options) {
      return this.delegate.connect(options);
   }

   public Future updateSSLOptions(SSLOptions options, boolean force) {
      return this.delegate.updateSSLOptions(options, force);
   }

   public boolean isMetricsEnabled() {
      return this.delegate.isMetricsEnabled();
   }
}
