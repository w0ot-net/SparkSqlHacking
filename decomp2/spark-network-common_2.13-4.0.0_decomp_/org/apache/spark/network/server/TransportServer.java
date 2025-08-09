package org.apache.spark.network.server;

import com.codahale.metrics.Counter;
import com.codahale.metrics.MetricSet;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import java.io.Closeable;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.SystemUtils;
import org.apache.spark.internal.SparkLogger;
import org.apache.spark.internal.SparkLoggerFactory;
import org.apache.spark.network.TransportContext;
import org.apache.spark.network.util.IOMode;
import org.apache.spark.network.util.JavaUtils;
import org.apache.spark.network.util.NettyMemoryMetrics;
import org.apache.spark.network.util.NettyUtils;
import org.apache.spark.network.util.TransportConf;
import org.sparkproject.guava.base.Preconditions;
import org.sparkproject.guava.collect.Lists;

public class TransportServer implements Closeable {
   private static final SparkLogger logger = SparkLoggerFactory.getLogger(TransportServer.class);
   private final TransportContext context;
   private final TransportConf conf;
   private final RpcHandler appRpcHandler;
   private final List bootstraps;
   private ServerBootstrap bootstrap;
   private ChannelFuture channelFuture;
   private int port = -1;
   private final PooledByteBufAllocator pooledAllocator;
   private NettyMemoryMetrics metrics;

   public TransportServer(TransportContext context, String hostToBind, int portToBind, RpcHandler appRpcHandler, List bootstraps) {
      this.context = context;
      this.conf = context.getConf();
      this.appRpcHandler = appRpcHandler;
      if (this.conf.sharedByteBufAllocators()) {
         this.pooledAllocator = NettyUtils.getSharedPooledByteBufAllocator(this.conf.preferDirectBufsForSharedByteBufAllocators(), true);
      } else {
         this.pooledAllocator = NettyUtils.createPooledByteBufAllocator(this.conf.preferDirectBufs(), true, this.conf.serverThreads());
      }

      this.bootstraps = Lists.newArrayList((Iterable)Preconditions.checkNotNull(bootstraps));
      boolean shouldClose = true;

      try {
         this.init(hostToBind, portToBind);
         shouldClose = false;
      } finally {
         if (shouldClose) {
            JavaUtils.closeQuietly(this);
         }

      }

   }

   public int getPort() {
      if (this.port == -1) {
         throw new IllegalStateException("Server not initialized");
      } else {
         return this.port;
      }
   }

   private void init(String hostToBind, int portToBind) {
      IOMode ioMode = IOMode.valueOf(this.conf.ioMode());
      String var10002 = this.conf.getModuleName();
      EventLoopGroup bossGroup = NettyUtils.createEventLoop(ioMode, 1, var10002 + "-boss");
      int var10001 = this.conf.serverThreads();
      var10002 = this.conf.getModuleName();
      EventLoopGroup workerGroup = NettyUtils.createEventLoop(ioMode, var10001, var10002 + "-server");
      this.bootstrap = ((ServerBootstrap)((ServerBootstrap)((ServerBootstrap)(new ServerBootstrap()).group(bossGroup, workerGroup).channel(NettyUtils.getServerChannelClass(ioMode))).option(ChannelOption.ALLOCATOR, this.pooledAllocator)).option(ChannelOption.SO_REUSEADDR, !SystemUtils.IS_OS_WINDOWS)).childOption(ChannelOption.ALLOCATOR, this.pooledAllocator);
      this.metrics = new NettyMemoryMetrics(this.pooledAllocator, this.conf.getModuleName() + "-server", this.conf);
      if (this.conf.backLog() > 0) {
         this.bootstrap.option(ChannelOption.SO_BACKLOG, this.conf.backLog());
      }

      if (this.conf.receiveBuf() > 0) {
         this.bootstrap.childOption(ChannelOption.SO_RCVBUF, this.conf.receiveBuf());
      }

      if (this.conf.sendBuf() > 0) {
         this.bootstrap.childOption(ChannelOption.SO_SNDBUF, this.conf.sendBuf());
      }

      if (this.conf.enableTcpKeepAlive()) {
         this.bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
      }

      this.bootstrap.childHandler(new ChannelInitializer() {
         protected void initChannel(SocketChannel ch) {
            TransportServer.logger.debug("New connection accepted for remote address {}.", ch.remoteAddress());
            RpcHandler rpcHandler = TransportServer.this.appRpcHandler;

            for(TransportServerBootstrap bootstrap : TransportServer.this.bootstraps) {
               rpcHandler = bootstrap.doBootstrap(ch, rpcHandler);
            }

            TransportServer.this.context.initializePipeline(ch, rpcHandler, false);
         }
      });
      InetSocketAddress address = hostToBind == null ? new InetSocketAddress(portToBind) : new InetSocketAddress(hostToBind, portToBind);
      this.channelFuture = this.bootstrap.bind(address);
      this.channelFuture.syncUninterruptibly();
      InetSocketAddress localAddress = (InetSocketAddress)this.channelFuture.channel().localAddress();
      this.port = localAddress.getPort();
      logger.debug("Shuffle server started on {} with port {}", localAddress.getHostString(), this.port);
   }

   public MetricSet getAllMetrics() {
      return this.metrics;
   }

   public void close() {
      if (this.channelFuture != null) {
         this.channelFuture.channel().close().awaitUninterruptibly(10L, TimeUnit.SECONDS);
         this.channelFuture = null;
      }

      if (this.bootstrap != null && this.bootstrap.config().group() != null) {
         this.bootstrap.config().group().shutdownGracefully();
      }

      if (this.bootstrap != null && this.bootstrap.config().childGroup() != null) {
         this.bootstrap.config().childGroup().shutdownGracefully();
      }

      this.bootstrap = null;
   }

   public Counter getRegisteredConnections() {
      return this.context.getRegisteredConnections();
   }
}
