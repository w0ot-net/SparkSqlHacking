package io.vertx.core.net.impl;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.handler.traffic.GlobalTrafficShapingHandler;
import io.vertx.core.Closeable;
import io.vertx.core.Context;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.impl.VertxInternal;
import io.vertx.core.impl.future.PromiseInternal;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.core.net.NetServerOptions;
import io.vertx.core.net.SSLOptions;
import io.vertx.core.net.SocketAddress;
import io.vertx.core.net.TrafficShapingOptions;
import io.vertx.core.spi.metrics.MetricsProvider;
import io.vertx.core.spi.metrics.TCPMetrics;
import java.net.InetSocketAddress;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;

public abstract class TCPServerBase implements Closeable, MetricsProvider {
   private static final Logger log = LoggerFactory.getLogger(NetServerImpl.class);
   protected final Context creatingContext;
   protected final VertxInternal vertx;
   protected final NetServerOptions options;
   private EventLoop eventLoop;
   private BiConsumer childHandler;
   private Handler worker;
   private volatile boolean listening;
   private ContextInternal listenContext;
   private TCPServerBase actualServer;
   private SSLHelper sslHelper;
   private volatile Future sslChannelProvider;
   private GlobalTrafficShapingHandler trafficShapingHandler;
   private ServerChannelLoadBalancer channelBalancer;
   private Future bindFuture;
   private Set servers;
   private TCPMetrics metrics;
   private volatile int actualPort;

   public TCPServerBase(VertxInternal vertx, NetServerOptions options) {
      this.vertx = vertx;
      this.options = new NetServerOptions(options);
      this.creatingContext = vertx.getContext();
   }

   public SslContextProvider sslContextProvider() {
      SslContextUpdate update = (SslContextUpdate)this.sslChannelProvider.result();
      return update != null ? update.sslChannelProvider().sslContextProvider() : null;
   }

   public int actualPort() {
      TCPServerBase server = this.actualServer;
      return server != null ? server.actualPort : this.actualPort;
   }

   protected abstract BiConsumer childHandler(ContextInternal var1, SocketAddress var2, GlobalTrafficShapingHandler var3);

   protected SSLHelper createSSLHelper() {
      return new SSLHelper(this.options, (List)null);
   }

   protected GlobalTrafficShapingHandler createTrafficShapingHandler() {
      return this.createTrafficShapingHandler(this.vertx.getEventLoopGroup(), this.options.getTrafficShapingOptions());
   }

   private GlobalTrafficShapingHandler createTrafficShapingHandler(EventLoopGroup eventLoopGroup, TrafficShapingOptions options) {
      if (options == null) {
         return null;
      } else {
         GlobalTrafficShapingHandler trafficShapingHandler;
         if (options.getMaxDelayToWait() != 0L) {
            long maxDelayToWaitInMillis = options.getMaxDelayToWaitTimeUnit().toMillis(options.getMaxDelayToWait());
            long checkIntervalForStatsInMillis = options.getCheckIntervalForStatsTimeUnit().toMillis(options.getCheckIntervalForStats());
            trafficShapingHandler = new GlobalTrafficShapingHandler(eventLoopGroup, options.getOutboundGlobalBandwidth(), options.getInboundGlobalBandwidth(), checkIntervalForStatsInMillis, maxDelayToWaitInMillis);
         } else {
            long checkIntervalForStatsInMillis = options.getCheckIntervalForStatsTimeUnit().toMillis(options.getCheckIntervalForStats());
            trafficShapingHandler = new GlobalTrafficShapingHandler(eventLoopGroup, options.getOutboundGlobalBandwidth(), options.getInboundGlobalBandwidth(), checkIntervalForStatsInMillis);
         }

         if (options.getPeakOutboundGlobalBandwidth() != 0L) {
            trafficShapingHandler.setMaxGlobalWriteSize(options.getPeakOutboundGlobalBandwidth());
         }

         return trafficShapingHandler;
      }
   }

   public int sniEntrySize() {
      return this.sslHelper.sniEntrySize();
   }

   public Future updateSSLOptions(SSLOptions options, boolean force) {
      TCPServerBase server = this.actualServer;
      if (server != null && server != this) {
         return server.updateSSLOptions(options, force);
      } else {
         ContextInternal ctx = this.vertx.getOrCreateContext();
         Future<SslContextUpdate> update = this.sslHelper.updateSslContext(new SSLOptions(options), force, ctx);
         this.sslChannelProvider = update;
         return update.transform((ar) -> {
            if (ar.failed()) {
               return ctx.failedFuture(ar.cause());
            } else {
               return ar.succeeded() && ((SslContextUpdate)ar.result()).error() != null ? ctx.failedFuture(((SslContextUpdate)ar.result()).error()) : ctx.succeededFuture(((SslContextUpdate)ar.result()).isUpdated());
            }
         });
      }
   }

   public void updateTrafficShapingOptions(TrafficShapingOptions options) {
      if (options == null) {
         throw new IllegalArgumentException("Invalid null value passed for traffic shaping options update");
      } else {
         TCPServerBase server = this.actualServer;
         if (server != null && server != this) {
            server.updateTrafficShapingOptions(options);
         } else {
            if (this.trafficShapingHandler == null) {
               throw new IllegalStateException("Unable to update traffic shaping options because the server was not configured to use traffic shaping during startup");
            }

            if (!options.equals(server.options.getTrafficShapingOptions())) {
               server.options.setTrafficShapingOptions(options);
               long checkIntervalForStatsInMillis = options.getCheckIntervalForStatsTimeUnit().toMillis(options.getCheckIntervalForStats());
               this.trafficShapingHandler.configure(options.getOutboundGlobalBandwidth(), options.getInboundGlobalBandwidth(), checkIntervalForStatsInMillis);
               if (options.getPeakOutboundGlobalBandwidth() != 0L) {
                  this.trafficShapingHandler.setMaxGlobalWriteSize(options.getPeakOutboundGlobalBandwidth());
               }

               if (options.getMaxDelayToWait() != 0L) {
                  long maxDelayToWaitInMillis = options.getMaxDelayToWaitTimeUnit().toMillis(options.getMaxDelayToWait());
                  this.trafficShapingHandler.setMaxWriteDelay(maxDelayToWaitInMillis);
               }
            } else {
               log.info("Not updating traffic shaping options as they have not changed");
            }
         }

      }
   }

   public Future bind(SocketAddress address) {
      ContextInternal listenContext = this.vertx.getOrCreateContext();
      return this.listen(address, listenContext).map((Object)this);
   }

   private synchronized Future listen(SocketAddress localAddress, ContextInternal context) {
      if (this.listening) {
         throw new IllegalStateException("Listen already called");
      } else {
         this.listenContext = context;
         this.listening = true;
         this.eventLoop = context.nettyEventLoop();
         Map<ServerID, TCPServerBase> sharedNetServers = this.vertx.sharedTCPServers(this.getClass());
         synchronized(sharedNetServers) {
            this.actualPort = localAddress.port();
            String hostOrPath = localAddress.isInetSocket() ? localAddress.host() : localAddress.path();
            SocketAddress bindAddress;
            TCPServerBase main;
            boolean shared;
            ServerID id;
            if (this.actualPort <= 0 && !localAddress.isDomainSocket()) {
               if (this.actualPort < 0) {
                  id = new ServerID(this.actualPort, hostOrPath + "/" + -this.actualPort);
                  main = (TCPServerBase)sharedNetServers.get(id);
                  shared = true;
                  bindAddress = SocketAddress.inetSocketAddress(0, localAddress.host());
               } else {
                  id = new ServerID(this.actualPort, hostOrPath);
                  main = null;
                  shared = false;
                  bindAddress = localAddress;
               }
            } else {
               id = new ServerID(this.actualPort, hostOrPath);
               main = (TCPServerBase)sharedNetServers.get(id);
               shared = true;
               bindAddress = localAddress;
            }

            PromiseInternal<Channel> promise = this.listenContext.promise();
            if (main == null) {
               this.actualServer = this;
               this.bindFuture = promise;
               this.sslHelper = this.createSSLHelper();
               this.trafficShapingHandler = this.createTrafficShapingHandler();
               this.childHandler = this.childHandler(this.listenContext, localAddress, this.trafficShapingHandler);
               this.worker = (ch) -> this.childHandler.accept(ch, ((SslContextUpdate)this.sslChannelProvider.result()).sslChannelProvider());
               this.servers = new HashSet();
               this.servers.add(this);
               this.channelBalancer = new ServerChannelLoadBalancer(this.vertx.getAcceptorEventLoopGroup().next());
               if (shared) {
                  sharedNetServers.put(id, this);
               }

               this.listenContext.addCloseHook(this);
               this.sslChannelProvider = this.sslHelper.updateSslContext(this.options.getSslOptions(), true, this.listenContext).onComplete((ar) -> {
                  if (ar.succeeded()) {
                     this.channelBalancer.addWorker(this.eventLoop, this.worker);
                     ServerBootstrap bootstrap = new ServerBootstrap();
                     bootstrap.group(this.vertx.getAcceptorEventLoopGroup(), this.channelBalancer.workers());
                     bootstrap.childOption(ChannelOption.ALLOCATOR, this.sslHelper.serverByteBufAllocator(((SslContextUpdate)ar.result()).sslChannelProvider().sslContextProvider()));
                     bootstrap.childHandler(this.channelBalancer);
                     this.applyConnectionOptions(localAddress.isDomainSocket(), bootstrap);
                     io.netty.util.concurrent.Future<Channel> bindFuture = AsyncResolveConnectHelper.doBind(this.vertx, bindAddress, bootstrap);
                     bindFuture.addListener((res) -> {
                        if (res.isSuccess()) {
                           Channel ch = (Channel)res.getNow();
                           log.trace("Net server listening on " + hostOrPath + ":" + ch.localAddress());
                           if (shared) {
                              ch.closeFuture().addListener((ChannelFutureListener)(channelFuture) -> {
                                 synchronized(sharedNetServers) {
                                    sharedNetServers.remove(id);
                                 }
                              });
                           }

                           if (bindAddress.isInetSocket()) {
                              this.actualPort = ((InetSocketAddress)ch.localAddress()).getPort();
                           }

                           this.metrics = this.createMetrics(localAddress);
                           promise.complete(ch);
                        } else {
                           promise.fail(res.cause());
                        }

                     });
                  } else {
                     promise.fail(ar.cause());
                  }

               });
               this.bindFuture.onFailure((err) -> {
                  if (shared) {
                     synchronized(sharedNetServers) {
                        sharedNetServers.remove(id);
                     }
                  }

                  this.listening = false;
               });
               return this.bindFuture;
            } else {
               this.actualServer = main;
               this.metrics = this.actualServer.metrics;
               this.trafficShapingHandler = this.actualServer.trafficShapingHandler;
               this.childHandler = this.childHandler(this.listenContext, localAddress, this.actualServer.trafficShapingHandler);
               this.worker = (ch) -> this.childHandler.accept(ch, ((SslContextUpdate)this.actualServer.sslChannelProvider.result()).sslChannelProvider());
               this.actualServer.servers.add(this);
               this.actualServer.channelBalancer.addWorker(this.eventLoop, this.worker);
               this.listenContext.addCloseHook(this);
               main.bindFuture.onComplete(promise);
               return promise.future();
            }
         }
      }
   }

   public boolean isListening() {
      return this.listening;
   }

   protected TCPMetrics createMetrics(SocketAddress localAddress) {
      return null;
   }

   private void applyConnectionOptions(boolean domainSocket, ServerBootstrap bootstrap) {
      this.vertx.transport().configure(this.options, domainSocket, bootstrap);
   }

   public boolean isMetricsEnabled() {
      return this.metrics != null;
   }

   public synchronized TCPMetrics getMetrics() {
      return this.actualServer != null ? this.actualServer.metrics : null;
   }

   public synchronized void close(Promise completion) {
      if (!this.listening) {
         completion.complete();
      } else {
         this.listening = false;
         this.listenContext.removeCloseHook(this);
         Map<ServerID, TCPServerBase> servers = this.vertx.sharedTCPServers(this.getClass());
         synchronized(servers) {
            ServerChannelLoadBalancer balancer = this.actualServer.channelBalancer;
            balancer.removeWorker(this.eventLoop, this.worker);
            if (balancer.hasHandlers()) {
               completion.complete();
            } else {
               this.actualServer.actualClose(completion);
            }

         }
      }
   }

   private void actualClose(Promise done) {
      this.channelBalancer.close();
      this.bindFuture.onComplete((ar) -> {
         if (ar.succeeded()) {
            Channel channel = (Channel)ar.result();
            ChannelFuture a = channel.close();
            if (this.metrics != null) {
               a.addListener((cg) -> this.metrics.close());
            }

            a.addListener((PromiseInternal)done);
         } else {
            done.complete();
         }

      });
   }

   public abstract Future close();
}
