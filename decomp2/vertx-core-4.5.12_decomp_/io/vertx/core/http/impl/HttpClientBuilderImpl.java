package io.vertx.core.http.impl;

import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientBuilder;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpClientResponse;
import io.vertx.core.http.HttpConnection;
import io.vertx.core.http.PoolOptions;
import io.vertx.core.http.RequestOptions;
import io.vertx.core.impl.CloseFuture;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.impl.VertxInternal;
import java.util.function.Function;

public class HttpClientBuilderImpl implements HttpClientBuilder {
   private final VertxInternal vertx;
   private HttpClientOptions clientOptions;
   private PoolOptions poolOptions;
   private Handler connectHandler;
   private Function redirectHandler;

   public HttpClientBuilderImpl(VertxInternal vertx) {
      this.vertx = vertx;
   }

   public HttpClientBuilder with(HttpClientOptions options) {
      this.clientOptions = options;
      return this;
   }

   public HttpClientBuilder with(PoolOptions options) {
      this.poolOptions = options;
      return this;
   }

   public HttpClientBuilder withConnectHandler(Handler handler) {
      this.connectHandler = handler;
      return this;
   }

   public HttpClientBuilder withRedirectHandler(Function handler) {
      this.redirectHandler = handler;
      return this;
   }

   private CloseFuture resolveCloseFuture() {
      ContextInternal context = this.vertx.getContext();
      return context != null ? context.closeFuture() : this.vertx.closeFuture();
   }

   public HttpClient build() {
      HttpClientOptions co = this.clientOptions != null ? this.clientOptions : new HttpClientOptions();
      PoolOptions po = this.poolOptions != null ? this.poolOptions : new PoolOptions();
      CloseFuture closeFuture = new CloseFuture();
      HttpClient client;
      if (co.isShared()) {
         client = (HttpClient)this.vertx.createSharedClient("__vertx.shared.httpClients", co.getName(), closeFuture, (cf) -> this.vertx.createHttpPoolClient(co, po, cf));
         client = new SharedHttpClient(this.vertx, closeFuture, client);
      } else {
         client = this.vertx.createHttpPoolClient(co, po, closeFuture);
      }

      Handler<HttpConnection> connectHandler = this.connectHandler;
      if (connectHandler != null) {
         client.connectionHandler(connectHandler);
      }

      Function<HttpClientResponse, Future<RequestOptions>> redirectHandler = this.redirectHandler;
      if (redirectHandler != null) {
         client.redirectHandler(redirectHandler);
      }

      this.resolveCloseFuture().add(closeFuture);
      return client;
   }
}
