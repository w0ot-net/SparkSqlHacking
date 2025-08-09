package io.vertx.core.http.impl;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.http.ServerWebSocket;
import io.vertx.core.http.WebSocketFrame;
import io.vertx.core.http.impl.ws.WebSocketFrameImpl;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.net.HostAndPort;
import io.vertx.core.spi.metrics.HttpServerMetrics;
import io.vertx.core.spi.metrics.Metrics;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class ServerWebSocketImpl extends WebSocketImplBase implements ServerWebSocket {
   private static final AtomicReferenceFieldUpdater STATUS_UPDATER = AtomicReferenceFieldUpdater.newUpdater(ServerWebSocketImpl.class, Integer.class, "status");
   private final Http1xServerConnection conn;
   private final long closingTimeoutMS;
   private final String scheme;
   private final String host;
   private final HostAndPort authority;
   private final String uri;
   private final String path;
   private final String query;
   private final WebSocketServerHandshaker handshaker;
   private Http1xServerRequest request;
   private volatile Integer status;
   private Promise handshakePromise;

   ServerWebSocketImpl(ContextInternal context, Http1xServerConnection conn, boolean supportsContinuation, long closingTimeout, Http1xServerRequest request, WebSocketServerHandshaker handshaker, int maxWebSocketFrameSize, int maxWebSocketMessageSize, boolean registerWebSocketWriteHandlers) {
      super(context, conn, supportsContinuation, maxWebSocketFrameSize, maxWebSocketMessageSize, registerWebSocketWriteHandlers);
      this.conn = conn;
      this.closingTimeoutMS = closingTimeout >= 0L ? closingTimeout * 1000L : -1L;
      this.scheme = request.scheme();
      this.host = request.host();
      this.authority = request.authority();
      this.uri = request.uri();
      this.path = request.path();
      this.query = request.query();
      this.request = request;
      this.handshaker = handshaker;
      this.headers(request.headers());
   }

   public String scheme() {
      return this.scheme;
   }

   public String host() {
      return this.host;
   }

   public HostAndPort authority() {
      return this.authority;
   }

   public String uri() {
      return this.uri;
   }

   public String path() {
      return this.path;
   }

   public String query() {
      return this.query;
   }

   public void accept() {
      if (this.tryHandshake(101) != Boolean.TRUE) {
         throw new IllegalStateException("WebSocket already rejected");
      }
   }

   public void reject() {
      this.reject(502);
   }

   public void reject(int sc) {
      if (sc == 101) {
         throw new IllegalArgumentException("Invalid WebSocket rejection status code: 101");
      } else if (this.tryHandshake(sc) != Boolean.TRUE) {
         throw new IllegalStateException("Cannot reject WebSocket, it has already been written to");
      }
   }

   public Future close(short statusCode, String reason) {
      synchronized(this.conn) {
         if (this.status == null) {
            if (this.handshakePromise == null) {
               this.tryHandshake(101);
            } else {
               this.handshakePromise.tryComplete(101);
            }
         }
      }

      Future<Void> fut = super.close(statusCode, reason);
      fut.onComplete((v) -> {
         if (this.closingTimeoutMS == 0L) {
            this.closeConnection();
         } else if (this.closingTimeoutMS > 0L) {
            this.initiateConnectionCloseTimeout(this.closingTimeoutMS);
         }

      });
      return fut;
   }

   public Future writeFrame(WebSocketFrame frame) {
      synchronized(this.conn) {
         Boolean check = this.tryHandshake(101);
         if (check == null) {
            throw new IllegalStateException("Cannot write to WebSocket, it is pending accept or reject");
         } else if (!check) {
            throw new IllegalStateException("Cannot write to WebSocket, it has been rejected");
         } else {
            return super.unsafeWriteFrame((WebSocketFrameImpl)frame);
         }
      }
   }

   private void handleHandshake(int sc) {
      synchronized(this.conn) {
         if (this.status == null) {
            if (sc == 101) {
               this.doHandshake();
            } else {
               STATUS_UPDATER.lazySet(this, sc);
               HttpUtils.sendError(this.conn.channel(), HttpResponseStatus.valueOf(sc));
            }
         }

      }
   }

   private void doHandshake() {
      Channel channel = this.conn.channel();
      Http1xServerResponse response = this.request.response();

      try {
         this.handshaker.handshake(channel, this.request.nettyRequest(), (HttpHeaders)response.headers(), channel.newPromise());
      } catch (Exception e) {
         response.setStatusCode(HttpResponseStatus.BAD_REQUEST.code()).end();
         throw e;
      } finally {
         this.request = null;
      }

      response.completeHandshake();
      STATUS_UPDATER.lazySet(this, HttpResponseStatus.SWITCHING_PROTOCOLS.code());
      this.subProtocol(this.handshaker.selectedSubprotocol());
      ChannelPipeline pipeline = channel.pipeline();
      ChannelHandler handler = pipeline.get(HttpChunkContentCompressor.class);
      if (handler != null) {
         pipeline.remove(handler);
      }

      this.registerHandler(this.conn.getContext().owner().eventBus());
   }

   Boolean tryHandshake(int sc) {
      Integer status = this.status;
      if (status != null) {
         return status == sc;
      } else {
         synchronized(this.conn) {
            assert status == null;

            status = this.status;
            if (status != null) {
               return status == sc;
            } else {
               if (this.handshakePromise == null) {
                  this.setHandshake(Future.succeededFuture(sc));
                  status = this.status;
               }

               return status == null ? null : status == sc;
            }
         }
      }
   }

   public void setHandshake(Future future, Handler handler) {
      Future<Integer> fut = this.setHandshake(future);
      fut.onComplete(handler);
   }

   public Future setHandshake(Future future) {
      if (future == null) {
         throw new NullPointerException();
      } else {
         Promise<Integer> p1 = Promise.promise();
         Promise<Integer> p2 = Promise.promise();
         synchronized(this.conn) {
            if (this.handshakePromise != null) {
               throw new IllegalStateException();
            }

            this.handshakePromise = p1;
         }

         future.onComplete(p1);
         p1.future().onComplete((ar) -> {
            if (ar.succeeded()) {
               this.handleHandshake((Integer)ar.result());
            } else {
               this.handleHandshake(500);
            }

            p2.handle(ar);
         });
         return p2.future();
      }
   }

   protected void handleCloseConnection() {
      this.closeConnection();
   }

   protected void handleClose(boolean graceful) {
      HttpServerMetrics metrics = this.conn.metrics;
      if (Metrics.METRICS_ENABLED && metrics != null) {
         metrics.disconnected(this.getMetric());
         this.setMetric((Object)null);
      }

      super.handleClose(graceful);
   }
}
