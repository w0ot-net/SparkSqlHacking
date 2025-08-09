package org.apache.zookeeper.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.ChannelPromise;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.ChannelGroupFuture;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.ssl.OptionalSslHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslHandler;
import io.netty.util.AttributeKey;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.DefaultEventExecutor;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.common.ClientX509Util;
import org.apache.zookeeper.common.NettyUtils;
import org.apache.zookeeper.common.X509Exception;
import org.apache.zookeeper.common.ZKConfig;
import org.apache.zookeeper.server.auth.ProviderRegistry;
import org.apache.zookeeper.server.auth.X509AuthenticationProvider;
import org.apache.zookeeper.server.quorum.QuorumPeerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NettyServerCnxnFactory extends ServerCnxnFactory {
   private static final Logger LOG = LoggerFactory.getLogger(NettyServerCnxnFactory.class);
   public static final String PORT_UNIFICATION_KEY = "zookeeper.client.portUnification";
   public static final String EARLY_DROP_SECURE_CONNECTION_HANDSHAKES = "zookeeper.netty.server.earlyDropSecureConnectionHandshakes";
   private final boolean shouldUsePortUnification;
   private static final byte TLS_HANDSHAKE_RECORD_TYPE = 22;
   private final AtomicInteger outstandingHandshake = new AtomicInteger();
   public static final String OUTSTANDING_HANDSHAKE_LIMIT = "zookeeper.netty.server.outstandingHandshake.limit";
   private int outstandingHandshakeLimit;
   private boolean handshakeThrottlingEnabled;
   private final ServerBootstrap bootstrap;
   private Channel parentChannel;
   private final ChannelGroup allChannels = new DefaultChannelGroup("zkServerCnxns", new DefaultEventExecutor());
   private final Map ipMap = new ConcurrentHashMap();
   private InetSocketAddress localAddress;
   private int maxClientCnxns = 60;
   int listenBacklog = -1;
   private final ClientX509Util x509Util = new ClientX509Util();
   public static final String NETTY_ADVANCED_FLOW_CONTROL = "zookeeper.netty.advancedFlowControl.enabled";
   private boolean advancedFlowControlEnabled = false;
   private static final AttributeKey CONNECTION_ATTRIBUTE = AttributeKey.valueOf("NettyServerCnxn");
   private static final AtomicReference TEST_ALLOCATOR = new AtomicReference((Object)null);
   public static final String CLIENT_CERT_RELOAD_KEY = "zookeeper.client.certReload";
   CnxnChannelHandler channelHandler = new CnxnChannelHandler();
   ReadIssuedTrackingHandler readIssuedTrackingHandler = new ReadIssuedTrackingHandler();
   private boolean killed;

   public void setOutstandingHandshakeLimit(int limit) {
      this.outstandingHandshakeLimit = limit;
      this.handshakeThrottlingEnabled = (this.secure || this.shouldUsePortUnification) && this.outstandingHandshakeLimit > 0;
      LOG.info("handshakeThrottlingEnabled = {}, {} = {}", new Object[]{this.handshakeThrottlingEnabled, "zookeeper.netty.server.outstandingHandshake.limit", this.outstandingHandshakeLimit});
   }

   private void updateHandshakeCountIfStarted(NettyServerCnxn cnxn) {
      if (cnxn != null && cnxn.getHandshakeState() == NettyServerCnxn.HandshakeState.STARTED) {
         cnxn.setHandshakeState(NettyServerCnxn.HandshakeState.FINISHED);
         this.outstandingHandshake.addAndGet(-1);
      }

   }

   private ServerBootstrap configureBootstrapAllocator(ServerBootstrap bootstrap) {
      ByteBufAllocator testAllocator = (ByteBufAllocator)TEST_ALLOCATOR.get();
      return testAllocator != null ? ((ServerBootstrap)bootstrap.option(ChannelOption.ALLOCATOR, testAllocator)).childOption(ChannelOption.ALLOCATOR, testAllocator) : bootstrap;
   }

   NettyServerCnxnFactory() {
      boolean useClientReload = Boolean.getBoolean("zookeeper.client.certReload");
      LOG.info("{}={}", "zookeeper.client.certReload", useClientReload);
      if (useClientReload) {
         try {
            this.x509Util.enableCertFileReloading();
         } catch (IOException e) {
            LOG.error("unable to set up client certificate reload filewatcher", e);
            useClientReload = false;
         }
      }

      boolean usePortUnification = Boolean.getBoolean("zookeeper.client.portUnification");
      LOG.info("{}={}", "zookeeper.client.portUnification", usePortUnification);
      if (usePortUnification) {
         try {
            QuorumPeerConfig.configureSSLAuth();
         } catch (QuorumPeerConfig.ConfigException e) {
            LOG.error("unable to set up SslAuthProvider, turning off client port unification", e);
            usePortUnification = false;
         }
      }

      this.shouldUsePortUnification = usePortUnification;
      this.advancedFlowControlEnabled = Boolean.getBoolean("zookeeper.netty.advancedFlowControl.enabled");
      LOG.info("{} = {}", "zookeeper.netty.advancedFlowControl.enabled", this.advancedFlowControlEnabled);
      this.setOutstandingHandshakeLimit(Integer.getInteger("zookeeper.netty.server.outstandingHandshake.limit", -1));
      EventLoopGroup bossGroup = NettyUtils.newNioOrEpollEventLoopGroup(NettyUtils.getClientReachableLocalInetAddressCount());
      EventLoopGroup workerGroup = NettyUtils.newNioOrEpollEventLoopGroup();
      ServerBootstrap bootstrap = ((ServerBootstrap)((ServerBootstrap)(new ServerBootstrap()).group(bossGroup, workerGroup).channel(NettyUtils.nioOrEpollServerSocketChannel())).option(ChannelOption.SO_REUSEADDR, true)).childOption(ChannelOption.TCP_NODELAY, true).childOption(ChannelOption.SO_LINGER, -1).childHandler(new ChannelInitializer() {
         protected void initChannel(SocketChannel ch) throws Exception {
            ChannelPipeline pipeline = ch.pipeline();
            if (NettyServerCnxnFactory.this.advancedFlowControlEnabled) {
               pipeline.addLast(new ChannelHandler[]{NettyServerCnxnFactory.this.readIssuedTrackingHandler});
            }

            if (NettyServerCnxnFactory.this.secure) {
               NettyServerCnxnFactory.this.initSSL(pipeline, false);
            } else if (NettyServerCnxnFactory.this.shouldUsePortUnification) {
               NettyServerCnxnFactory.this.initSSL(pipeline, true);
            }

            pipeline.addLast("servercnxnfactory", NettyServerCnxnFactory.this.channelHandler);
         }
      });
      this.bootstrap = this.configureBootstrapAllocator(bootstrap);
      this.bootstrap.validate();
   }

   private synchronized void initSSL(ChannelPipeline p, boolean supportPlaintext) throws X509Exception, SSLException {
      String authProviderProp = System.getProperty(this.x509Util.getSslAuthProviderProperty());
      SslContext nettySslContext;
      if (authProviderProp == null) {
         nettySslContext = this.x509Util.createNettySslContextForServer(new ZKConfig());
      } else {
         X509AuthenticationProvider authProvider = (X509AuthenticationProvider)ProviderRegistry.getProvider(System.getProperty(this.x509Util.getSslAuthProviderProperty(), "x509"));
         if (authProvider == null) {
            LOG.error("Auth provider not found: {}", authProviderProp);
            throw new X509Exception.SSLContextException("Could not create SSLContext with specified auth provider: " + authProviderProp);
         }

         nettySslContext = this.x509Util.createNettySslContextForServer(new ZKConfig(), authProvider.getKeyManager(), authProvider.getTrustManager());
      }

      if (supportPlaintext) {
         p.addLast("ssl", new DualModeSslHandler(nettySslContext));
         LOG.debug("dual mode SSL handler added for channel: {}", p.channel());
      } else {
         p.addLast("ssl", nettySslContext.newHandler(p.channel().alloc()));
         LOG.debug("SSL handler added for channel: {}", p.channel());
      }

   }

   public void closeAll(ServerCnxn.DisconnectReason reason) {
      LOG.debug("closeAll()");
      int length = this.cnxns.size();

      for(ServerCnxn cnxn : this.cnxns) {
         try {
            cnxn.close(reason);
         } catch (Exception e) {
            LOG.warn("Ignoring exception closing cnxn sessionid 0x{}", Long.toHexString(cnxn.getSessionId()), e);
         }
      }

      LOG.debug("allChannels size: {} cnxns size: {}", this.allChannels.size(), length);
   }

   public void configure(InetSocketAddress addr, int maxClientCnxns, int backlog, boolean secure) throws IOException {
      this.configureSaslLogin();
      this.initMaxCnxns();
      this.localAddress = addr;
      this.maxClientCnxns = maxClientCnxns;
      this.secure = secure;
      this.listenBacklog = backlog;
      LOG.info("configure {} secure: {} on addr {}", new Object[]{this, secure, addr});
   }

   public int getMaxClientCnxnsPerHost() {
      return this.maxClientCnxns;
   }

   public void setMaxClientCnxnsPerHost(int max) {
      this.maxClientCnxns = max;
   }

   public int getSocketListenBacklog() {
      return this.listenBacklog;
   }

   public int getLocalPort() {
      return this.localAddress.getPort();
   }

   public void join() throws InterruptedException {
      synchronized(this) {
         while(!this.killed) {
            this.wait();
         }

      }
   }

   public void shutdown() {
      synchronized(this) {
         if (this.killed) {
            LOG.info("already shutdown {}", this.localAddress);
            return;
         }
      }

      LOG.info("shutdown called {}", this.localAddress);
      this.x509Util.close();
      if (this.login != null) {
         this.login.shutdown();
      }

      EventLoopGroup bossGroup = this.bootstrap.config().group();
      EventLoopGroup workerGroup = this.bootstrap.config().childGroup();
      if (this.parentChannel != null) {
         ChannelFuture parentCloseFuture = this.parentChannel.close();
         if (bossGroup != null) {
            parentCloseFuture.addListener((future) -> bossGroup.shutdownGracefully());
         }

         this.closeAll(ServerCnxn.DisconnectReason.SERVER_SHUTDOWN);
         ChannelGroupFuture allChannelsCloseFuture = this.allChannels.close();
         if (workerGroup != null) {
            allChannelsCloseFuture.addListener((future) -> workerGroup.shutdownGracefully());
         }
      } else {
         if (bossGroup != null) {
            bossGroup.shutdownGracefully();
         }

         if (workerGroup != null) {
            workerGroup.shutdownGracefully();
         }
      }

      if (this.zkServer != null) {
         this.zkServer.shutdown();
      }

      synchronized(this) {
         this.killed = true;
         this.notifyAll();
      }
   }

   public void start() {
      if (this.listenBacklog != -1) {
         this.bootstrap.option(ChannelOption.SO_BACKLOG, this.listenBacklog);
      }

      LOG.info("binding to port {}", this.localAddress);
      this.parentChannel = this.bootstrap.bind(this.localAddress).syncUninterruptibly().channel();
      this.localAddress = (InetSocketAddress)this.parentChannel.localAddress();
      LOG.info("bound to port {}", this.getLocalPort());
   }

   public void reconfigure(InetSocketAddress addr) {
      LOG.info("binding to port {}, {}", addr, this.localAddress);
      if (addr == null || this.localAddress == null || !addr.equals(this.localAddress) && (!addr.getAddress().isAnyLocalAddress() || !this.localAddress.getAddress().isAnyLocalAddress() || addr.getPort() != this.localAddress.getPort())) {
         Channel oldChannel = this.parentChannel;

         try {
            this.parentChannel = this.bootstrap.bind(addr).syncUninterruptibly().channel();
            this.localAddress = (InetSocketAddress)this.parentChannel.localAddress();
            LOG.info("bound to port {}", this.getLocalPort());
         } catch (Exception e) {
            LOG.error("Error while reconfiguring", e);
         } finally {
            oldChannel.close();
         }

      } else {
         LOG.info("address is the same, skip rebinding");
      }
   }

   public void startup(ZooKeeperServer zks, boolean startServer) throws IOException, InterruptedException {
      this.start();
      this.setZooKeeperServer(zks);
      if (startServer) {
         zks.startdata();
         zks.startup();
      }

   }

   public Iterable getConnections() {
      return this.cnxns;
   }

   public InetSocketAddress getLocalAddress() {
      return this.localAddress;
   }

   private void addCnxn(NettyServerCnxn cnxn) {
      this.cnxns.add(cnxn);
      InetAddress addr = ((InetSocketAddress)cnxn.getChannel().remoteAddress()).getAddress();
      this.ipMap.compute(addr, (a, cnxnCount) -> {
         if (cnxnCount == null) {
            cnxnCount = new AtomicInteger();
         }

         cnxnCount.incrementAndGet();
         return cnxnCount;
      });
   }

   void removeCnxnFromIpMap(NettyServerCnxn cnxn, InetAddress remoteAddress) {
      this.ipMap.compute(remoteAddress, (addr, cnxnCount) -> {
         if (cnxnCount == null) {
            LOG.error("Unexpected remote address {} when removing cnxn {}", remoteAddress, cnxn);
            return null;
         } else {
            int newValue = cnxnCount.decrementAndGet();
            return newValue == 0 ? null : cnxnCount;
         }
      });
   }

   private int getClientCnxnCount(InetAddress addr) {
      AtomicInteger count = (AtomicInteger)this.ipMap.get(addr);
      return count == null ? 0 : count.get();
   }

   public void resetAllConnectionStats() {
      for(ServerCnxn c : this.cnxns) {
         c.resetStats();
      }

   }

   public Iterable getAllConnectionInfo(boolean brief) {
      Set<Map<String, Object>> info = new HashSet();

      for(ServerCnxn c : this.cnxns) {
         info.add(c.getConnectionInfo(brief));
      }

      return info;
   }

   static void setTestAllocator(ByteBufAllocator allocator) {
      TEST_ALLOCATOR.set(allocator);
   }

   static void clearTestAllocator() {
      TEST_ALLOCATOR.set((Object)null);
   }

   public void setAdvancedFlowControlEnabled(boolean advancedFlowControlEnabled) {
      this.advancedFlowControlEnabled = advancedFlowControlEnabled;
   }

   public void setSecure(boolean secure) {
      this.secure = secure;
   }

   public Channel getParentChannel() {
      return this.parentChannel;
   }

   public int getOutstandingHandshakeNum() {
      return this.outstandingHandshake.get();
   }

   class DualModeSslHandler extends OptionalSslHandler {
      DualModeSslHandler(SslContext sslContext) {
         super(sslContext);
      }

      protected void decode(ChannelHandlerContext context, ByteBuf in, List out) throws Exception {
         if (in.readableBytes() >= 5) {
            super.decode(context, in, out);
         } else if (in.readableBytes() > 0 && 22 != in.getByte(0)) {
            NettyServerCnxnFactory.LOG.debug("first byte {} does not match TLS handshake, failing to plaintext", in.getByte(0));
            this.handleNonSsl(context);
         }

      }

      private void handleNonSsl(ChannelHandlerContext context) {
         ChannelHandler handler = this.newNonSslHandler(context);
         if (handler != null) {
            context.pipeline().replace(this, this.newNonSslHandlerName(), handler);
         } else {
            context.pipeline().remove(this);
         }

      }

      protected SslHandler newSslHandler(ChannelHandlerContext context, SslContext sslContext) {
         NettyServerCnxn cnxn = (NettyServerCnxn)Objects.requireNonNull((NettyServerCnxn)context.channel().attr(NettyServerCnxnFactory.CONNECTION_ATTRIBUTE).get());
         NettyServerCnxnFactory.LOG.debug("creating ssl handler for session {}", cnxn.getSessionId());
         SslHandler handler = super.newSslHandler(context, sslContext);
         Future<Channel> handshakeFuture = handler.handshakeFuture();
         handshakeFuture.addListener(NettyServerCnxnFactory.this.new CertificateVerifier(handler, cnxn));
         return handler;
      }

      protected ChannelHandler newNonSslHandler(ChannelHandlerContext context) {
         NettyServerCnxn cnxn = (NettyServerCnxn)Objects.requireNonNull((NettyServerCnxn)context.channel().attr(NettyServerCnxnFactory.CONNECTION_ATTRIBUTE).get());
         NettyServerCnxnFactory.LOG.debug("creating plaintext handler for session {}", cnxn.getSessionId());
         NettyServerCnxnFactory.this.updateHandshakeCountIfStarted(cnxn);
         NettyServerCnxnFactory.this.allChannels.add(context.channel());
         NettyServerCnxnFactory.this.addCnxn(cnxn);
         return super.newNonSslHandler(context);
      }
   }

   @Sharable
   class CnxnChannelHandler extends ChannelDuplexHandler {
      private final GenericFutureListener onWriteCompletedTracer = (f) -> {
         if (NettyServerCnxnFactory.LOG.isTraceEnabled()) {
            NettyServerCnxnFactory.LOG.trace("write success: {}", f.isSuccess());
         }

      };

      public void channelActive(ChannelHandlerContext ctx) throws Exception {
         if (NettyServerCnxnFactory.LOG.isTraceEnabled()) {
            NettyServerCnxnFactory.LOG.trace("Channel active {}", ctx.channel());
         }

         Channel channel = ctx.channel();
         if (NettyServerCnxnFactory.this.limitTotalNumberOfCnxns()) {
            ServerMetrics.getMetrics().CONNECTION_REJECTED.add(1L);
            channel.close();
         } else {
            InetAddress addr = ((InetSocketAddress)channel.remoteAddress()).getAddress();
            if (NettyServerCnxnFactory.this.maxClientCnxns > 0 && NettyServerCnxnFactory.this.getClientCnxnCount(addr) >= NettyServerCnxnFactory.this.maxClientCnxns) {
               ServerMetrics.getMetrics().CONNECTION_REJECTED.add(1L);
               NettyServerCnxnFactory.LOG.warn("Too many connections from {} - max is {}", addr, NettyServerCnxnFactory.this.maxClientCnxns);
               channel.close();
            } else {
               NettyServerCnxn cnxn = new NettyServerCnxn(channel, NettyServerCnxnFactory.this.zkServer, NettyServerCnxnFactory.this);
               ctx.channel().attr(NettyServerCnxnFactory.CONNECTION_ATTRIBUTE).set(cnxn);
               if (NettyServerCnxnFactory.this.secure && !cnxn.isZKServerRunning()) {
                  boolean earlyDropSecureConnectionHandshakes = Boolean.getBoolean("zookeeper.netty.server.earlyDropSecureConnectionHandshakes");
                  if (earlyDropSecureConnectionHandshakes) {
                     NettyServerCnxnFactory.LOG.info("Zookeeper server is not running, close the connection to {} before starting the TLS handshake", cnxn.getChannel().remoteAddress());
                     ServerMetrics.getMetrics().CNXN_CLOSED_WITHOUT_ZK_SERVER_RUNNING.add(1L);
                     channel.close();
                     return;
                  }
               }

               if (NettyServerCnxnFactory.this.handshakeThrottlingEnabled) {
                  int outstandingHandshakesNum = NettyServerCnxnFactory.this.outstandingHandshake.addAndGet(1);
                  if (outstandingHandshakesNum > NettyServerCnxnFactory.this.outstandingHandshakeLimit) {
                     NettyServerCnxnFactory.this.outstandingHandshake.addAndGet(-1);
                     channel.close();
                     ServerMetrics.getMetrics().TLS_HANDSHAKE_EXCEEDED.add(1L);
                  } else {
                     cnxn.setHandshakeState(NettyServerCnxn.HandshakeState.STARTED);
                  }
               }

               if (NettyServerCnxnFactory.this.secure) {
                  SslHandler sslHandler = (SslHandler)ctx.pipeline().get(SslHandler.class);
                  Future<Channel> handshakeFuture = sslHandler.handshakeFuture();
                  handshakeFuture.addListener(NettyServerCnxnFactory.this.new CertificateVerifier(sslHandler, cnxn));
               } else if (!NettyServerCnxnFactory.this.shouldUsePortUnification) {
                  NettyServerCnxnFactory.this.allChannels.add(ctx.channel());
                  NettyServerCnxnFactory.this.addCnxn(cnxn);
               }

               if (ctx.channel().pipeline().get(SslHandler.class) == null) {
                  if (NettyServerCnxnFactory.this.zkServer != null) {
                     SocketAddress remoteAddress = cnxn.getChannel().remoteAddress();
                     if (remoteAddress != null && !((InetSocketAddress)remoteAddress).getAddress().isLoopbackAddress()) {
                        NettyServerCnxnFactory.LOG.trace("NettyChannelHandler channelActive: remote={} local={}", remoteAddress, cnxn.getChannel().localAddress());
                        NettyServerCnxnFactory.this.zkServer.serverStats().incrementNonMTLSRemoteConnCount();
                     } else {
                        NettyServerCnxnFactory.this.zkServer.serverStats().incrementNonMTLSLocalConnCount();
                     }
                  } else {
                     NettyServerCnxnFactory.LOG.trace("Opened non-TLS connection from {} but zkServer is not running", cnxn.getChannel().remoteAddress());
                  }
               }

            }
         }
      }

      public void channelInactive(ChannelHandlerContext ctx) throws Exception {
         if (NettyServerCnxnFactory.LOG.isTraceEnabled()) {
            NettyServerCnxnFactory.LOG.trace("Channel inactive {}", ctx.channel());
         }

         NettyServerCnxnFactory.this.allChannels.remove(ctx.channel());
         NettyServerCnxn cnxn = (NettyServerCnxn)ctx.channel().attr(NettyServerCnxnFactory.CONNECTION_ATTRIBUTE).getAndSet((Object)null);
         if (cnxn != null) {
            if (NettyServerCnxnFactory.LOG.isTraceEnabled()) {
               NettyServerCnxnFactory.LOG.trace("Channel inactive caused close {}", cnxn);
            }

            NettyServerCnxnFactory.this.updateHandshakeCountIfStarted(cnxn);
            cnxn.close(ServerCnxn.DisconnectReason.CHANNEL_DISCONNECTED);
         }

      }

      public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
         NettyServerCnxnFactory.LOG.warn("Exception caught", cause);
         NettyServerCnxn cnxn = (NettyServerCnxn)ctx.channel().attr(NettyServerCnxnFactory.CONNECTION_ATTRIBUTE).getAndSet((Object)null);
         if (cnxn != null) {
            NettyServerCnxnFactory.LOG.debug("Closing {}", cnxn);
            NettyServerCnxnFactory.this.updateHandshakeCountIfStarted(cnxn);
            cnxn.close(ServerCnxn.DisconnectReason.CHANNEL_CLOSED_EXCEPTION);
         }

      }

      public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
         try {
            if (evt == NettyServerCnxn.ReadEvent.ENABLE) {
               NettyServerCnxnFactory.LOG.debug("Received ReadEvent.ENABLE");
               NettyServerCnxn cnxn = (NettyServerCnxn)ctx.channel().attr(NettyServerCnxnFactory.CONNECTION_ATTRIBUTE).get();
               if (cnxn != null && cnxn.getQueuedReadableBytes() > 0) {
                  cnxn.processQueuedBuffer();
                  if (NettyServerCnxnFactory.this.advancedFlowControlEnabled && cnxn.getQueuedReadableBytes() == 0) {
                     ctx.read();
                     NettyServerCnxnFactory.LOG.debug("Issued a read after queuedBuffer drained");
                  }
               }

               if (!NettyServerCnxnFactory.this.advancedFlowControlEnabled) {
                  ctx.channel().config().setAutoRead(true);
               }
            } else if (evt == NettyServerCnxn.ReadEvent.DISABLE) {
               NettyServerCnxnFactory.LOG.debug("Received ReadEvent.DISABLE");
               ctx.channel().config().setAutoRead(false);
            }
         } finally {
            ReferenceCountUtil.release(evt);
         }

      }

      public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
         try {
            if (NettyServerCnxnFactory.LOG.isTraceEnabled()) {
               NettyServerCnxnFactory.LOG.trace("message received called {}", msg);
            }

            try {
               NettyServerCnxnFactory.LOG.debug("New message {} from {}", msg, ctx.channel());
               NettyServerCnxn cnxn = (NettyServerCnxn)ctx.channel().attr(NettyServerCnxnFactory.CONNECTION_ATTRIBUTE).get();
               if (cnxn == null) {
                  NettyServerCnxnFactory.LOG.error("channelRead() on a closed or closing NettyServerCnxn");
               } else {
                  cnxn.processMessage((ByteBuf)msg);
               }
            } catch (Exception ex) {
               NettyServerCnxnFactory.LOG.error("Unexpected exception in receive", ex);
               throw ex;
            }
         } finally {
            ReferenceCountUtil.release(msg);
         }

      }

      public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
         if (NettyServerCnxnFactory.this.advancedFlowControlEnabled) {
            NettyServerCnxn cnxn = (NettyServerCnxn)ctx.channel().attr(NettyServerCnxnFactory.CONNECTION_ATTRIBUTE).get();
            if (cnxn != null && cnxn.getQueuedReadableBytes() == 0 && cnxn.readIssuedAfterReadComplete == 0) {
               ctx.read();
               NettyServerCnxnFactory.LOG.debug("Issued a read since we do not have anything to consume after channelReadComplete");
            }
         }

         ctx.fireChannelReadComplete();
      }

      public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
         if (NettyServerCnxnFactory.LOG.isTraceEnabled()) {
            promise.addListener(this.onWriteCompletedTracer);
         }

         super.write(ctx, msg, promise);
      }
   }

   final class CertificateVerifier implements GenericFutureListener {
      private final SslHandler sslHandler;
      private final NettyServerCnxn cnxn;

      CertificateVerifier(SslHandler sslHandler, NettyServerCnxn cnxn) {
         this.sslHandler = sslHandler;
         this.cnxn = cnxn;
      }

      public void operationComplete(Future future) {
         NettyServerCnxnFactory.this.updateHandshakeCountIfStarted(this.cnxn);
         if (future.isSuccess()) {
            NettyServerCnxnFactory.LOG.debug("Successful handshake with session 0x{}", Long.toHexString(this.cnxn.getSessionId()));
            SSLEngine eng = this.sslHandler.engine();
            if (eng.getNeedClientAuth() || eng.getWantClientAuth()) {
               SSLSession session = eng.getSession();

               try {
                  this.cnxn.setClientCertificateChain(session.getPeerCertificates());
               } catch (SSLPeerUnverifiedException e) {
                  if (eng.getNeedClientAuth()) {
                     NettyServerCnxnFactory.LOG.error("Error getting peer certificates", e);
                     this.cnxn.close();
                     return;
                  }

                  Channel futureChannel = (Channel)future.getNow();
                  NettyServerCnxnFactory.this.allChannels.add((Channel)Objects.requireNonNull(futureChannel));
                  NettyServerCnxnFactory.this.addCnxn(this.cnxn);
                  return;
               } catch (Exception e) {
                  NettyServerCnxnFactory.LOG.error("Error getting peer certificates", e);
                  this.cnxn.close();
                  return;
               }

               String authProviderProp = System.getProperty(NettyServerCnxnFactory.this.x509Util.getSslAuthProviderProperty(), "x509");
               X509AuthenticationProvider authProvider = (X509AuthenticationProvider)ProviderRegistry.getProvider(authProviderProp);
               if (authProvider == null) {
                  NettyServerCnxnFactory.LOG.error("X509 Auth provider not found: {}", authProviderProp);
                  this.cnxn.close(ServerCnxn.DisconnectReason.AUTH_PROVIDER_NOT_FOUND);
                  return;
               }

               KeeperException.Code code = authProvider.handleAuthentication((ServerCnxn)this.cnxn, (byte[])null);
               if (KeeperException.Code.OK != code) {
                  NettyServerCnxnFactory.this.zkServer.serverStats().incrementAuthFailedCount();
                  NettyServerCnxnFactory.LOG.error("Authentication failed for session 0x{}", Long.toHexString(this.cnxn.getSessionId()));
                  this.cnxn.close(ServerCnxn.DisconnectReason.SASL_AUTH_FAILURE);
                  return;
               }
            }

            Channel futureChannel = (Channel)future.getNow();
            NettyServerCnxnFactory.this.allChannels.add((Channel)Objects.requireNonNull(futureChannel));
            NettyServerCnxnFactory.this.addCnxn(this.cnxn);
         } else {
            NettyServerCnxnFactory.this.zkServer.serverStats().incrementAuthFailedCount();
            NettyServerCnxnFactory.LOG.error("Unsuccessful handshake with session 0x{}", Long.toHexString(this.cnxn.getSessionId()));
            ServerMetrics.getMetrics().UNSUCCESSFUL_HANDSHAKE.add(1L);
            this.cnxn.close(ServerCnxn.DisconnectReason.FAILED_HANDSHAKE);
         }

      }
   }

   @Sharable
   static class ReadIssuedTrackingHandler extends ChannelDuplexHandler {
      public void read(ChannelHandlerContext ctx) throws Exception {
         NettyServerCnxn cnxn = (NettyServerCnxn)ctx.channel().attr(NettyServerCnxnFactory.CONNECTION_ATTRIBUTE).get();
         if (cnxn != null) {
            ++cnxn.readIssuedAfterReadComplete;
         }

         ctx.read();
      }

      public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
         NettyServerCnxn cnxn = (NettyServerCnxn)ctx.channel().attr(NettyServerCnxnFactory.CONNECTION_ATTRIBUTE).get();
         if (cnxn != null) {
            cnxn.readIssuedAfterReadComplete = 0;
         }

         ctx.fireChannelReadComplete();
      }
   }
}
