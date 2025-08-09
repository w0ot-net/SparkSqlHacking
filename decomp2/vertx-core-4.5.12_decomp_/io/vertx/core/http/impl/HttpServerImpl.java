package io.vertx.core.http.impl;

import io.netty.handler.traffic.GlobalTrafficShapingHandler;
import io.vertx.core.AsyncResult;
import io.vertx.core.Closeable;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpConnection;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpVersion;
import io.vertx.core.http.ServerWebSocket;
import io.vertx.core.http.ServerWebSocketHandshake;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.impl.VertxInternal;
import io.vertx.core.impl.future.PromiseInternal;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.core.net.SocketAddress;
import io.vertx.core.net.impl.SSLHelper;
import io.vertx.core.net.impl.TCPServerBase;
import io.vertx.core.spi.metrics.MetricsProvider;
import io.vertx.core.spi.metrics.TCPMetrics;
import io.vertx.core.spi.metrics.VertxMetrics;
import io.vertx.core.streams.ReadStream;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class HttpServerImpl extends TCPServerBase implements HttpServer, Closeable, MetricsProvider {
   static final Logger log = LoggerFactory.getLogger(HttpServerImpl.class);
   private static final Handler DEFAULT_EXCEPTION_HANDLER = (t) -> log.trace("Connection failure", t);
   private static final String FLASH_POLICY_HANDLER_PROP_NAME = "vertx.flashPolicyHandler";
   private static final String DISABLE_WEBSOCKETS_PROP_NAME = "vertx.disableWebsockets";
   static final boolean USE_FLASH_POLICY_HANDLER = Boolean.getBoolean("vertx.flashPolicyHandler");
   static final boolean DISABLE_WEBSOCKETS = Boolean.getBoolean("vertx.disableWebsockets");
   final HttpServerOptions options;
   final boolean useH2UniformStreamByteDistributor;
   private final HttpStreamHandler wsStream = new HttpStreamHandler();
   private final HttpStreamHandler requestStream = new HttpStreamHandler();
   private Handler invalidRequestHandler;
   private Handler connectionHandler;
   private Handler exceptionHandler;
   private Handler webSocketHandshakeHandler;

   public HttpServerImpl(VertxInternal vertx, HttpServerOptions options) {
      super(vertx, options);
      this.options = new HttpServerOptions(options);
      this.useH2UniformStreamByteDistributor = HttpUtils.useH2UniformStreamByteDistributor();
   }

   protected TCPMetrics createMetrics(SocketAddress localAddress) {
      VertxMetrics vertxMetrics = this.vertx.metricsSPI();
      return vertxMetrics != null ? vertxMetrics.createHttpServerMetrics(this.options, localAddress) : null;
   }

   public synchronized HttpServer requestHandler(Handler handler) {
      this.requestStream.handler(handler);
      return this;
   }

   public ReadStream requestStream() {
      return this.requestStream;
   }

   public HttpServer webSocketHandler(Handler handler) {
      this.webSocketStream().handler(handler);
      return this;
   }

   public HttpServer webSocketHandshakeHandler(Handler handler) {
      this.webSocketHandshakeHandler = handler;
      return this;
   }

   public Handler requestHandler() {
      return this.requestStream.handler();
   }

   public HttpServer invalidRequestHandler(Handler handler) {
      this.invalidRequestHandler = handler;
      return this;
   }

   public synchronized HttpServer connectionHandler(Handler handler) {
      if (this.isListening()) {
         throw new IllegalStateException("Please set handler before server is listening");
      } else {
         this.connectionHandler = handler;
         return this;
      }
   }

   public synchronized HttpServer exceptionHandler(Handler handler) {
      if (this.isListening()) {
         throw new IllegalStateException("Please set handler before server is listening");
      } else {
         this.exceptionHandler = handler;
         return this;
      }
   }

   public Handler webSocketHandler() {
      return this.wsStream.handler();
   }

   public ReadStream webSocketStream() {
      return this.wsStream;
   }

   public Future listen() {
      return this.listen(this.options.getPort(), this.options.getHost());
   }

   protected BiConsumer childHandler(ContextInternal context, SocketAddress address, GlobalTrafficShapingHandler trafficShapingHandler) {
      ContextInternal connContext;
      if (context.isEventLoopContext()) {
         connContext = context;
      } else {
         connContext = this.vertx.createEventLoopContext(context.nettyEventLoop(), context.workerPool(), context.classLoader());
      }

      String host = address.isInetSocket() ? address.host() : "localhost";
      int port = address.port();
      String serverOrigin = (this.options.isSsl() ? "https" : "http") + "://" + host + ":" + port;
      HttpServerConnectionHandler hello = new HttpServerConnectionHandler(this, this.requestStream.handler, this.invalidRequestHandler, this.webSocketHandshakeHandler, this.wsStream.handler, this.connectionHandler, this.exceptionHandler == null ? DEFAULT_EXCEPTION_HANDLER : this.exceptionHandler);
      Supplier<ContextInternal> streamContextSupplier = context::duplicate;
      return new HttpServerWorker(connContext, streamContextSupplier, this, this.vertx, this.options, serverOrigin, hello, hello.exceptionHandler, trafficShapingHandler);
   }

   protected SSLHelper createSSLHelper() {
      return new SSLHelper(this.options, (List)this.options.getAlpnVersions().stream().map(HttpVersion::alpnName).collect(Collectors.toList()));
   }

   public synchronized Future listen(SocketAddress address) {
      if (this.requestStream.handler() == null && this.wsStream.handler() == null) {
         throw new IllegalStateException("Set request or WebSocket handler first");
      } else {
         return this.bind(address).map((Object)this);
      }
   }

   public Future close() {
      ContextInternal context = this.vertx.getOrCreateContext();
      PromiseInternal<Void> promise = context.promise();
      this.close((Promise)promise);
      return promise.future();
   }

   public void close(Handler done) {
      ContextInternal context = this.vertx.getOrCreateContext();
      PromiseInternal<Void> promise = context.promise();
      this.close((Promise)promise);
      if (done != null) {
         promise.future().onComplete(done);
      }

   }

   public synchronized void close(Promise completion) {
      if (this.wsStream.endHandler() != null || this.requestStream.endHandler() != null) {
         Handler<Void> wsEndHandler = this.wsStream.endHandler();
         this.wsStream.endHandler((Handler)null);
         Handler<Void> requestEndHandler = this.requestStream.endHandler();
         this.requestStream.endHandler((Handler)null);
         completion.future().onComplete((ar) -> {
            if (wsEndHandler != null) {
               wsEndHandler.handle((Object)null);
            }

            if (requestEndHandler != null) {
               requestEndHandler.handle((Object)null);
            }

         });
      }

      super.close(completion);
   }

   public boolean isClosed() {
      return !this.isListening();
   }

   boolean requestAccept() {
      return this.requestStream.accept();
   }

   boolean wsAccept() {
      return this.wsStream.accept();
   }

   class HttpStreamHandler implements ReadStream {
      private Handler handler;
      private long demand = Long.MAX_VALUE;
      private Handler endHandler;

      Handler handler() {
         synchronized(HttpServerImpl.this) {
            return this.handler;
         }
      }

      boolean accept() {
         synchronized(HttpServerImpl.this) {
            boolean accept = this.demand > 0L;
            if (accept && this.demand != Long.MAX_VALUE) {
               --this.demand;
            }

            return accept;
         }
      }

      Handler endHandler() {
         synchronized(HttpServerImpl.this) {
            return this.endHandler;
         }
      }

      public ReadStream handler(Handler handler) {
         synchronized(HttpServerImpl.this) {
            if (HttpServerImpl.this.isListening()) {
               throw new IllegalStateException("Please set handler before server is listening");
            } else {
               this.handler = handler;
               return this;
            }
         }
      }

      public ReadStream pause() {
         synchronized(HttpServerImpl.this) {
            this.demand = 0L;
            return this;
         }
      }

      public ReadStream fetch(long amount) {
         if (amount > 0L) {
            this.demand += amount;
            if (this.demand < 0L) {
               this.demand = Long.MAX_VALUE;
            }
         }

         return this;
      }

      public ReadStream resume() {
         synchronized(HttpServerImpl.this) {
            this.demand = Long.MAX_VALUE;
            return this;
         }
      }

      public ReadStream endHandler(Handler endHandler) {
         synchronized(HttpServerImpl.this) {
            this.endHandler = endHandler;
            return this;
         }
      }

      public ReadStream exceptionHandler(Handler handler) {
         return this;
      }
   }
}
