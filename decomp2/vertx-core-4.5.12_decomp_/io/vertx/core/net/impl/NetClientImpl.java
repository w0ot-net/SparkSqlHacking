package io.vertx.core.net.impl;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoop;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.ChannelGroupFuture;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import io.vertx.core.AsyncResult;
import io.vertx.core.Closeable;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.impl.CloseFuture;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.impl.VertxInternal;
import io.vertx.core.impl.future.PromiseInternal;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.core.net.ClientOptionsBase;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.NetClientOptions;
import io.vertx.core.net.NetSocket;
import io.vertx.core.net.ProxyOptions;
import io.vertx.core.net.SSLOptions;
import io.vertx.core.net.SocketAddress;
import io.vertx.core.spi.metrics.Metrics;
import io.vertx.core.spi.metrics.MetricsProvider;
import io.vertx.core.spi.metrics.TCPMetrics;
import java.io.FileNotFoundException;
import java.net.ConnectException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

public class NetClientImpl implements MetricsProvider, NetClient, Closeable {
   private static final Logger log = LoggerFactory.getLogger(NetClientImpl.class);
   protected final int idleTimeout;
   protected final int readIdleTimeout;
   protected final int writeIdleTimeout;
   private final TimeUnit idleTimeoutUnit;
   protected final boolean logEnabled;
   private final VertxInternal vertx;
   private final NetClientOptions options;
   private final SSLHelper sslHelper;
   private Future sslChannelProvider;
   private final ChannelGroup channelGroup;
   private final TCPMetrics metrics;
   private final CloseFuture closeFuture;
   private final Predicate proxyFilter;

   public NetClientImpl(VertxInternal vertx, TCPMetrics metrics, NetClientOptions options, CloseFuture closeFuture) {
      this.vertx = vertx;
      this.channelGroup = new DefaultChannelGroup(vertx.getAcceptorEventLoopGroup().next(), true);
      this.options = new NetClientOptions(options);
      this.sslHelper = new SSLHelper(options, options.getApplicationLayerProtocols());
      this.metrics = metrics;
      this.logEnabled = options.getLogActivity();
      this.idleTimeout = options.getIdleTimeout();
      this.readIdleTimeout = options.getReadIdleTimeout();
      this.writeIdleTimeout = options.getWriteIdleTimeout();
      this.idleTimeoutUnit = options.getIdleTimeoutUnit();
      this.closeFuture = closeFuture;
      this.proxyFilter = options.getNonProxyHosts() != null ? ProxyFilter.nonProxyHosts(options.getNonProxyHosts()) : ProxyFilter.DEFAULT_PROXY_FILTER;
   }

   protected void initChannel(ChannelPipeline pipeline) {
      if (this.logEnabled) {
         pipeline.addLast("logging", new LoggingHandler(this.options.getActivityLogDataFormat()));
      }

      if (this.options.isSsl() || !this.vertx.transport().supportFileRegion()) {
         pipeline.addLast("chunkedWriter", new ChunkedWriteHandler());
      }

      if (this.idleTimeout > 0 || this.readIdleTimeout > 0 || this.writeIdleTimeout > 0) {
         pipeline.addLast("idle", new IdleStateHandler((long)this.readIdleTimeout, (long)this.writeIdleTimeout, (long)this.idleTimeout, this.idleTimeoutUnit));
      }

   }

   public Future connect(int port, String host) {
      return this.connect(port, host, (String)null);
   }

   public Future connect(int port, String host, String serverName) {
      return this.connect(SocketAddress.inetSocketAddress(port, host), serverName);
   }

   public Future connect(SocketAddress remoteAddress) {
      return this.connect(remoteAddress, (String)null);
   }

   public Future connect(SocketAddress remoteAddress, String serverName) {
      return this.connect(this.vertx.getOrCreateContext(), remoteAddress, serverName);
   }

   public Future connect(ContextInternal context, SocketAddress remoteAddress, String serverName) {
      Promise<NetSocket> promise = context.promise();
      this.connect(remoteAddress, serverName, promise, context);
      return promise.future();
   }

   public NetClient connect(int port, String host, Handler connectHandler) {
      return this.connect(port, host, (String)null, connectHandler);
   }

   public NetClient connect(int port, String host, String serverName, Handler connectHandler) {
      return this.connect(SocketAddress.inetSocketAddress(port, host), serverName, connectHandler);
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

   public void close(Promise completion) {
      ChannelGroupFuture fut = this.channelGroup.close();
      if (this.metrics != null) {
         PromiseInternal<Void> p = (PromiseInternal)Promise.promise();
         fut.addListener(p);
         p.future().compose((v) -> {
            this.metrics.close();
            return Future.succeededFuture();
         }).onComplete(completion);
      } else {
         fut.addListener((PromiseInternal)completion);
      }

   }

   public boolean isMetricsEnabled() {
      return this.metrics != null;
   }

   public Metrics getMetrics() {
      return this.metrics;
   }

   public Future updateSSLOptions(SSLOptions options, boolean force) {
      ContextInternal ctx = this.vertx.getOrCreateContext();
      Future<SslContextUpdate> fut;
      synchronized(this) {
         fut = this.sslHelper.updateSslContext(new SSLOptions(options), force, ctx);
         this.sslChannelProvider = fut;
      }

      return fut.transform((ar) -> {
         if (ar.failed()) {
            return ctx.failedFuture(ar.cause());
         } else {
            return ar.succeeded() && ((SslContextUpdate)ar.result()).error() != null ? ctx.failedFuture(((SslContextUpdate)ar.result()).error()) : ctx.succeededFuture(((SslContextUpdate)ar.result()).isUpdated());
         }
      });
   }

   public NetClient connect(SocketAddress remoteAddress, String serverName, Handler connectHandler) {
      Objects.requireNonNull(connectHandler, "No null connectHandler accepted");
      ContextInternal ctx = this.vertx.getOrCreateContext();
      Promise<NetSocket> promise = ctx.promise();
      promise.future().onComplete(connectHandler);
      this.connect(remoteAddress, serverName, promise, ctx);
      return this;
   }

   public NetClient connect(SocketAddress remoteAddress, Handler connectHandler) {
      return this.connect((SocketAddress)remoteAddress, (String)null, (Handler)connectHandler);
   }

   private void connect(SocketAddress remoteAddress, String serverName, Promise connectHandler, ContextInternal ctx) {
      if (this.closeFuture.isClosed()) {
         throw new IllegalStateException("Client is closed");
      } else {
         SocketAddress peerAddress = remoteAddress;
         String peerHost = remoteAddress.host();
         if (peerHost != null && peerHost.endsWith(".")) {
            peerAddress = SocketAddress.inetSocketAddress(remoteAddress.port(), peerHost.substring(0, peerHost.length() - 1));
         }

         ProxyOptions proxyOptions = this.options.getProxyOptions();
         if (this.proxyFilter != null && !this.proxyFilter.test(remoteAddress)) {
            proxyOptions = null;
         }

         this.connectInternal(proxyOptions, remoteAddress, peerAddress, serverName, this.options.isSsl(), this.options.isUseAlpn(), this.options.isRegisterWriteHandler(), connectHandler, ctx, this.options.getReconnectAttempts());
      }
   }

   public void connectInternal(ProxyOptions proxyOptions, SocketAddress remoteAddress, SocketAddress peerAddress, String serverName, boolean ssl, boolean useAlpn, boolean registerWriteHandlers, Promise connectHandler, ContextInternal context, int remainingAttempts) {
      if (this.closeFuture.isClosed()) {
         connectHandler.fail((Throwable)(new IllegalStateException("Client is closed")));
      } else {
         if (ssl && this.options.getHostnameVerificationAlgorithm() == null) {
            connectHandler.fail("Missing hostname verification algorithm: you must set TCP client options host name verification algorithm");
            return;
         }

         Future<SslContextUpdate> fut;
         synchronized(this) {
            fut = this.sslChannelProvider;
            if (fut == null) {
               fut = this.sslHelper.updateSslContext(this.options.getSslOptions(), true, context);
               this.sslChannelProvider = fut;
            }
         }

         fut.onComplete((ar) -> {
            if (ar.succeeded()) {
               this.connectInternal2(proxyOptions, remoteAddress, peerAddress, ((SslContextUpdate)ar.result()).sslChannelProvider(), serverName, ssl, useAlpn, registerWriteHandlers, connectHandler, context, remainingAttempts);
            } else {
               connectHandler.fail(ar.cause());
            }

         });
      }

   }

   private void connectInternal2(ProxyOptions proxyOptions, SocketAddress remoteAddress, SocketAddress peerAddress, SslChannelProvider sslChannelProvider, String serverName, boolean ssl, boolean useAlpn, boolean registerWriteHandlers, Promise connectHandler, ContextInternal context, int remainingAttempts) {
      EventLoop eventLoop = context.nettyEventLoop();
      if (eventLoop.inEventLoop()) {
         Objects.requireNonNull(connectHandler, "No null connectHandler accepted");
         Bootstrap bootstrap = new Bootstrap();
         bootstrap.group(eventLoop);
         bootstrap.option(ChannelOption.ALLOCATOR, this.sslHelper.clientByteBufAllocator(sslChannelProvider.sslContextProvider()));
         this.vertx.transport().configure((ClientOptionsBase)this.options, remoteAddress.isDomainSocket(), (Bootstrap)bootstrap);
         ChannelProvider channelProvider = (new ChannelProvider(bootstrap, sslChannelProvider, context)).proxyOptions(proxyOptions);
         channelProvider.handler((ch) -> this.connected(context, ch, connectHandler, remoteAddress, sslChannelProvider, channelProvider.applicationProtocol(), registerWriteHandlers));
         io.netty.util.concurrent.Future<Channel> fut = channelProvider.connect(remoteAddress, peerAddress, serverName, ssl, useAlpn);
         fut.addListener((future) -> {
            if (!future.isSuccess()) {
               Throwable cause = future.cause();
               boolean connectError = cause instanceof ConnectException || cause instanceof FileNotFoundException;
               if (!connectError || remainingAttempts <= 0 && remainingAttempts != -1) {
                  this.failed(context, (Channel)null, cause, connectHandler);
               } else {
                  context.emit((v) -> {
                     log.debug("Failed to create connection. Will retry in " + this.options.getReconnectInterval() + " milliseconds");
                     this.vertx.setTimer(this.options.getReconnectInterval(), (tid) -> this.connectInternal(proxyOptions, remoteAddress, peerAddress, serverName, ssl, useAlpn, registerWriteHandlers, connectHandler, context, remainingAttempts == -1 ? remainingAttempts : remainingAttempts - 1));
                  });
               }
            }

         });
      } else {
         eventLoop.execute(() -> this.connectInternal2(proxyOptions, remoteAddress, peerAddress, sslChannelProvider, serverName, ssl, useAlpn, registerWriteHandlers, connectHandler, context, remainingAttempts));
      }

   }

   private void connected(ContextInternal context, Channel ch, Promise connectHandler, SocketAddress remoteAddress, SslChannelProvider sslChannelProvider, String applicationLayerProtocol, boolean registerWriteHandlers) {
      this.channelGroup.add(ch);
      this.initChannel(ch.pipeline());
      VertxHandler<NetSocketImpl> handler = VertxHandler.create((ctx) -> new NetSocketImpl(context, ctx, remoteAddress, sslChannelProvider, this.metrics, this.options.getHostnameVerificationAlgorithm(), applicationLayerProtocol, registerWriteHandlers));
      handler.removeHandler(NetSocketImpl::unregisterEventBusHandler);
      handler.addHandler((sock) -> {
         if (this.metrics != null) {
            sock.metric(this.metrics.connected(sock.remoteAddress(), sock.remoteName()));
         }

         sock.registerEventBusHandler();
         connectHandler.complete(sock);
      });
      ch.pipeline().addLast("handler", handler);
   }

   private void failed(ContextInternal context, Channel ch, Throwable th, Promise connectHandler) {
      if (ch != null) {
         ch.close();
      }

      context.emit(th, connectHandler::tryFail);
   }
}
