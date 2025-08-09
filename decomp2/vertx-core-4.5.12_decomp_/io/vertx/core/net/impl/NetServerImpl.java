package io.vertx.core.net.impl;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.haproxy.HAProxyMessageDecoder;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.handler.traffic.GlobalTrafficShapingHandler;
import io.vertx.core.AsyncResult;
import io.vertx.core.Closeable;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.http.impl.HttpUtils;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.impl.VertxInternal;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.core.net.NetServer;
import io.vertx.core.net.NetServerOptions;
import io.vertx.core.net.NetSocket;
import io.vertx.core.net.SocketAddress;
import io.vertx.core.spi.metrics.MetricsProvider;
import io.vertx.core.spi.metrics.TCPMetrics;
import io.vertx.core.spi.metrics.VertxMetrics;
import io.vertx.core.streams.ReadStream;
import java.util.function.BiConsumer;

public class NetServerImpl extends TCPServerBase implements Closeable, MetricsProvider, NetServer {
   private static final Logger log = LoggerFactory.getLogger(NetServerImpl.class);
   private final NetSocketStream connectStream = new NetSocketStream();
   private long demand = Long.MAX_VALUE;
   private Handler handler;
   private Handler endHandler;
   private Handler exceptionHandler;

   public NetServerImpl(VertxInternal vertx, NetServerOptions options) {
      super(vertx, options);
   }

   private synchronized void pauseAccepting() {
      this.demand = 0L;
   }

   private synchronized void resumeAccepting() {
      this.demand = Long.MAX_VALUE;
   }

   private synchronized void fetchAccepting(long amount) {
      if (amount > 0L) {
         this.demand += amount;
         if (this.demand < 0L) {
            this.demand = Long.MAX_VALUE;
         }
      }

   }

   protected synchronized boolean accept() {
      boolean accept = this.demand > 0L;
      if (accept && this.demand != Long.MAX_VALUE) {
         --this.demand;
      }

      return accept;
   }

   public synchronized Handler connectHandler() {
      return this.handler;
   }

   public synchronized NetServer connectHandler(Handler handler) {
      if (this.isListening()) {
         throw new IllegalStateException("Cannot set connectHandler when server is listening");
      } else {
         this.handler = handler;
         return this;
      }
   }

   public synchronized NetServer exceptionHandler(Handler handler) {
      if (this.isListening()) {
         throw new IllegalStateException("Cannot set exceptionHandler when server is listening");
      } else {
         this.exceptionHandler = handler;
         return this;
      }
   }

   protected TCPMetrics createMetrics(SocketAddress localAddress) {
      VertxMetrics vertxMetrics = this.vertx.metricsSPI();
      return vertxMetrics != null ? vertxMetrics.createNetServerMetrics(this.options, localAddress) : null;
   }

   public Future close() {
      ContextInternal context = this.vertx.getOrCreateContext();
      Promise<Void> promise = context.promise();
      this.close(promise);
      return promise.future();
   }

   protected BiConsumer childHandler(ContextInternal context, SocketAddress socketAddress, GlobalTrafficShapingHandler trafficShapingHandler) {
      return new NetServerWorker(context, this.handler, this.exceptionHandler, trafficShapingHandler);
   }

   public synchronized Future listen(SocketAddress localAddress) {
      if (localAddress == null) {
         throw new NullPointerException("No null bind local address");
      } else if (this.handler == null) {
         throw new IllegalStateException("Set connect handler first");
      } else {
         return this.bind(localAddress).map((Object)this);
      }
   }

   public Future listen() {
      return this.listen(this.options.getPort(), this.options.getHost());
   }

   public ReadStream connectStream() {
      return this.connectStream;
   }

   public void close(Handler completionHandler) {
      ContextInternal context = this.vertx.getOrCreateContext();
      Promise<Void> promise = context.promise();
      this.close(promise);
      promise.future().onComplete(completionHandler);
   }

   public synchronized void close(Promise completion) {
      super.close(completion);
      Handler<Void> handler = this.endHandler;
      if (this.endHandler != null) {
         this.endHandler = null;
         completion.future().onComplete((ar) -> handler.handle((Object)null));
      }

   }

   public boolean isClosed() {
      return !this.isListening();
   }

   protected void initChannel(ChannelPipeline pipeline, boolean ssl) {
      if (this.options.getLogActivity()) {
         pipeline.addLast("logging", new LoggingHandler(this.options.getActivityLogDataFormat()));
      }

      if (ssl || !this.vertx.transport().supportFileRegion() || this.options.getTrafficShapingOptions() != null && this.options.getTrafficShapingOptions().getOutboundGlobalBandwidth() > 0L) {
         pipeline.addLast("chunkedWriter", new ChunkedWriteHandler());
      }

      int idleTimeout = this.options.getIdleTimeout();
      int readIdleTimeout = this.options.getReadIdleTimeout();
      int writeIdleTimeout = this.options.getWriteIdleTimeout();
      if (idleTimeout > 0 || readIdleTimeout > 0 || writeIdleTimeout > 0) {
         pipeline.addLast("idle", new IdleStateHandler((long)readIdleTimeout, (long)writeIdleTimeout, (long)idleTimeout, this.options.getIdleTimeoutUnit()));
      }

   }

   private class NetServerWorker implements BiConsumer {
      private final ContextInternal context;
      private final Handler connectionHandler;
      private final Handler exceptionHandler;
      private final GlobalTrafficShapingHandler trafficShapingHandler;

      NetServerWorker(ContextInternal context, Handler connectionHandler, Handler exceptionHandler, GlobalTrafficShapingHandler trafficShapingHandler) {
         this.context = context;
         this.connectionHandler = connectionHandler;
         this.exceptionHandler = exceptionHandler;
         this.trafficShapingHandler = trafficShapingHandler;
      }

      public void accept(Channel ch, SslChannelProvider sslChannelProvider) {
         if (!NetServerImpl.this.accept()) {
            ch.close();
         } else {
            if (HAProxyMessageCompletionHandler.canUseProxyProtocol(NetServerImpl.this.options.isUseProxyProtocol())) {
               io.netty.util.concurrent.Promise<Channel> p = ch.eventLoop().newPromise();
               ch.pipeline().addLast(new ChannelHandler[]{new HAProxyMessageDecoder()});
               IdleStateHandler idle;
               if (NetServerImpl.this.options.getProxyProtocolTimeout() > 0L) {
                  ch.pipeline().addLast("idle", idle = new IdleStateHandler(0L, 0L, NetServerImpl.this.options.getProxyProtocolTimeout(), NetServerImpl.this.options.getProxyProtocolTimeoutUnit()));
               } else {
                  idle = null;
               }

               ch.pipeline().addLast(new ChannelHandler[]{new HAProxyMessageCompletionHandler(p)});
               p.addListener((future) -> {
                  if (future.isSuccess()) {
                     if (idle != null) {
                        ch.pipeline().remove(idle);
                     }

                     this.configurePipeline((Channel)future.getNow(), sslChannelProvider);
                  } else {
                     this.handleException(future.cause());
                  }

               });
            } else {
               this.configurePipeline(ch, sslChannelProvider);
            }

         }
      }

      private void configurePipeline(Channel ch, SslChannelProvider sslChannelProvider) {
         if (NetServerImpl.this.options.isSsl()) {
            ch.pipeline().addLast("ssl", sslChannelProvider.createServerHandler(HttpUtils.socketAddressToHostAndPort(ch.remoteAddress())));
            ChannelPromise p = ch.newPromise();
            ch.pipeline().addLast("handshaker", new SslHandshakeCompletionHandler(p));
            p.addListener((future) -> {
               if (future.isSuccess()) {
                  this.connected(ch, sslChannelProvider);
               } else {
                  this.handleException(future.cause());
               }

            });
         } else {
            this.connected(ch, sslChannelProvider);
         }

         if (this.trafficShapingHandler != null) {
            ch.pipeline().addFirst("globalTrafficShaping", this.trafficShapingHandler);
         }

      }

      private void handleException(Throwable cause) {
         if (this.exceptionHandler != null) {
            this.context.emit((v) -> this.exceptionHandler.handle(cause));
         }

      }

      private void connected(Channel ch, SslChannelProvider sslChannelProvider) {
         NetServerImpl.this.initChannel(ch.pipeline(), NetServerImpl.this.options.isSsl());
         TCPMetrics<?> metrics = NetServerImpl.this.getMetrics();
         VertxHandler<NetSocketImpl> handler = VertxHandler.create((ctx) -> new NetSocketImpl(this.context, ctx, sslChannelProvider, metrics, NetServerImpl.this.options.isRegisterWriteHandler()));
         handler.removeHandler(NetSocketImpl::unregisterEventBusHandler);
         handler.addHandler((conn) -> {
            if (metrics != null) {
               conn.metric(metrics.connected(conn.remoteAddress(), conn.remoteName()));
            }

            conn.registerEventBusHandler();
            Handler var10002 = this.connectionHandler;
            this.context.emit(conn, var10002::handle);
         });
         ch.pipeline().addLast("handler", handler);
      }
   }

   private class NetSocketStream implements ReadStream {
      private NetSocketStream() {
      }

      public NetSocketStream handler(Handler handler) {
         NetServerImpl.this.connectHandler(handler);
         return this;
      }

      public NetSocketStream pause() {
         NetServerImpl.this.pauseAccepting();
         return this;
      }

      public NetSocketStream resume() {
         NetServerImpl.this.resumeAccepting();
         return this;
      }

      public ReadStream fetch(long amount) {
         NetServerImpl.this.fetchAccepting(amount);
         return this;
      }

      public NetSocketStream endHandler(Handler handler) {
         synchronized(NetServerImpl.this) {
            NetServerImpl.this.endHandler = handler;
            return this;
         }
      }

      public NetSocketStream exceptionHandler(Handler handler) {
         return this;
      }
   }
}
