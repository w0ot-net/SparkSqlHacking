package io.vertx.core.http.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpConnection;
import io.vertx.core.http.HttpVersion;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.impl.NoStackTraceTimeoutException;
import io.vertx.core.net.impl.pool.ConnectResult;
import io.vertx.core.net.impl.pool.ConnectionPool;
import io.vertx.core.net.impl.pool.Lease;
import io.vertx.core.net.impl.pool.PoolConnection;
import io.vertx.core.net.impl.pool.PoolConnector;
import io.vertx.core.net.impl.pool.PoolWaiter;
import io.vertx.core.spi.metrics.ClientMetrics;
import java.util.List;
import java.util.function.BiFunction;

class SharedClientHttpStreamEndpoint extends ClientHttpEndpointBase implements PoolConnector {
   private static final BiFunction LIFO_SELECTOR = (waiter, connections) -> {
      int size = connections.size();
      PoolConnection<HttpClientConnection> selected = null;
      long last = 0L;

      for(int i = 0; i < size; ++i) {
         PoolConnection<HttpClientConnection> pooled = (PoolConnection)connections.get(i);
         if (pooled.available() > 0L) {
            HttpClientConnection conn = (HttpClientConnection)pooled.get();
            if (selected == null) {
               selected = pooled;
            } else if (conn.lastResponseReceivedTimestamp() > last) {
               selected = pooled;
            }
         }
      }

      return selected;
   };
   private final HttpClientImpl client;
   private final HttpChannelConnector connector;
   private final ConnectionPool pool;

   public SharedClientHttpStreamEndpoint(HttpClientImpl client, ClientMetrics metrics, int queueMaxSize, int http1MaxSize, int http2MaxSize, HttpChannelConnector connector, Runnable dispose) {
      super(metrics, dispose);
      ConnectionPool<HttpClientConnection> pool = ConnectionPool.pool(this, new int[]{http1MaxSize, http2MaxSize}, queueMaxSize).connectionSelector(LIFO_SELECTOR).contextProvider(client.contextProvider());
      this.client = client;
      this.connector = connector;
      this.pool = pool;
   }

   public void connect(ContextInternal context, PoolConnector.Listener listener, Handler handler) {
      this.connector.httpConnect(context, (ar) -> {
         if (ar.succeeded()) {
            this.incRefCount();
            HttpClientConnection connection = (HttpClientConnection)ar.result();
            connection.evictionHandler((v) -> {
               this.decRefCount();
               listener.onRemove();
            });
            connection.concurrencyChangeHandler(listener::onConcurrencyChange);
            long capacity = connection.concurrency();
            Handler<HttpConnection> connectionHandler = this.client.connectionHandler();
            if (connectionHandler != null) {
               context.emit(connection, connectionHandler);
            }

            int idx;
            if (connection instanceof Http1xClientConnection) {
               idx = 0;
            } else {
               idx = 1;
            }

            handler.handle(Future.succeededFuture(new ConnectResult(connection, capacity, (long)idx)));
         } else {
            handler.handle(Future.failedFuture(ar.cause()));
         }

      });
   }

   public boolean isValid(HttpClientConnection connection) {
      return connection.isValid();
   }

   void checkExpired() {
      this.pool.evict((conn) -> !conn.isValid(), (ar) -> {
         if (ar.succeeded()) {
            List<HttpClientConnection> lst = (List)ar.result();
            lst.forEach(HttpConnection::close);
         }

      });
   }

   public void requestConnection2(ContextInternal ctx, long timeout, Handler handler) {
      Request request = new Request(ctx, this.client.options().getProtocolVersion(), timeout, handler);
      request.acquire();
   }

   private class Request implements PoolWaiter.Listener, Handler {
      private final ContextInternal context;
      private final HttpVersion protocol;
      private final long timeout;
      private final Handler handler;
      private long timerID;

      Request(ContextInternal context, HttpVersion protocol, long timeout, Handler handler) {
         this.context = context;
         this.protocol = protocol;
         this.timeout = timeout;
         this.handler = handler;
         this.timerID = -1L;
      }

      public void onEnqueue(PoolWaiter waiter) {
         this.onConnect(waiter);
      }

      public void onConnect(PoolWaiter waiter) {
         if (this.timeout > 0L && this.timerID == -1L) {
            this.timerID = this.context.setTimer(this.timeout, (id) -> SharedClientHttpStreamEndpoint.this.pool.cancel(waiter, (ar) -> {
                  if (ar.succeeded() && (Boolean)ar.result()) {
                     this.handler.handle(Future.failedFuture((Throwable)(new NoStackTraceTimeoutException("The timeout of " + this.timeout + " ms has been exceeded when getting a connection to " + SharedClientHttpStreamEndpoint.this.connector.server()))));
                  }

               }));
         }

      }

      public void handle(AsyncResult ar) {
         if (this.timerID >= 0L) {
            this.context.owner().cancelTimer(this.timerID);
         }

         this.handler.handle(ar);
      }

      void acquire() {
         SharedClientHttpStreamEndpoint.this.pool.acquire(this.context, this, this.protocol == HttpVersion.HTTP_2 ? 1 : 0, this);
      }
   }
}
