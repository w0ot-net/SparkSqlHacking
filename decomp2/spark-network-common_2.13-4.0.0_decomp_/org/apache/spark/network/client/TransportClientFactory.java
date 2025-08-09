package org.apache.spark.network.client;

import com.codahale.metrics.MetricSet;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.ssl.SslHandler;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import java.io.Closeable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.SparkLogger;
import org.apache.spark.internal.SparkLoggerFactory;
import org.apache.spark.internal.LogKeys.STATUS.;
import org.apache.spark.network.TransportContext;
import org.apache.spark.network.server.TransportChannelHandler;
import org.apache.spark.network.util.IOMode;
import org.apache.spark.network.util.JavaUtils;
import org.apache.spark.network.util.NettyMemoryMetrics;
import org.apache.spark.network.util.NettyUtils;
import org.apache.spark.network.util.TransportConf;
import org.sparkproject.guava.annotations.VisibleForTesting;
import org.sparkproject.guava.base.Preconditions;
import org.sparkproject.guava.base.Throwables;
import org.sparkproject.guava.collect.Lists;

public class TransportClientFactory implements Closeable {
   private static final SparkLogger logger = SparkLoggerFactory.getLogger(TransportClientFactory.class);
   private final TransportContext context;
   private final TransportConf conf;
   private final List clientBootstraps;
   private final ConcurrentHashMap connectionPool;
   private final Random rand;
   private final int numConnectionsPerPeer;
   private final Class socketChannelClass;
   private EventLoopGroup workerGroup;
   private final PooledByteBufAllocator pooledAllocator;
   private final NettyMemoryMetrics metrics;
   private final int fastFailTimeWindow;

   public TransportClientFactory(TransportContext context, List clientBootstraps) {
      this.context = (TransportContext)Preconditions.checkNotNull(context);
      this.conf = context.getConf();
      this.clientBootstraps = Lists.newArrayList((Iterable)Preconditions.checkNotNull(clientBootstraps));
      this.connectionPool = new ConcurrentHashMap();
      this.numConnectionsPerPeer = this.conf.numConnectionsPerPeer();
      this.rand = new Random();
      IOMode ioMode = IOMode.valueOf(this.conf.ioMode());
      this.socketChannelClass = NettyUtils.getClientChannelClass(ioMode);
      this.workerGroup = NettyUtils.createEventLoop(ioMode, this.conf.clientThreads(), this.conf.getModuleName() + "-client");
      if (this.conf.sharedByteBufAllocators()) {
         this.pooledAllocator = NettyUtils.getSharedPooledByteBufAllocator(this.conf.preferDirectBufsForSharedByteBufAllocators(), false);
      } else {
         this.pooledAllocator = NettyUtils.createPooledByteBufAllocator(this.conf.preferDirectBufs(), false, this.conf.clientThreads());
      }

      this.metrics = new NettyMemoryMetrics(this.pooledAllocator, this.conf.getModuleName() + "-client", this.conf);
      this.fastFailTimeWindow = (int)((double)this.conf.ioRetryWaitTimeMs() * 0.95);
   }

   public MetricSet getAllMetrics() {
      return this.metrics;
   }

   public TransportClient createClient(String remoteHost, int remotePort, boolean fastFail) throws IOException, InterruptedException {
      InetSocketAddress unresolvedAddress = InetSocketAddress.createUnresolved(remoteHost, remotePort);
      ClientPool clientPool = (ClientPool)this.connectionPool.computeIfAbsent(unresolvedAddress, (key) -> new ClientPool(this.numConnectionsPerPeer));
      int clientIndex = this.rand.nextInt(this.numConnectionsPerPeer);
      TransportClient cachedClient = clientPool.clients[clientIndex];
      if (cachedClient != null && cachedClient.isActive()) {
         TransportChannelHandler handler = (TransportChannelHandler)cachedClient.getChannel().pipeline().get(TransportChannelHandler.class);
         if (handler != null) {
            synchronized(handler) {
               handler.getResponseHandler().updateTimeOfLastRequest();
            }
         }

         if (cachedClient.isActive()) {
            logger.trace("Returning cached connection to {}: {}", cachedClient.getSocketAddress(), cachedClient);
            return cachedClient;
         }
      }

      long preResolveHost = System.nanoTime();
      InetSocketAddress resolvedAddress = new InetSocketAddress(remoteHost, remotePort);
      long hostResolveTimeMs = (System.nanoTime() - preResolveHost) / 1000000L;
      String resolvMsg = resolvedAddress.isUnresolved() ? "failed" : "succeed";
      if (hostResolveTimeMs > 2000L) {
         logger.warn("DNS resolution {} for {} took {} ms", new MDC[]{MDC.of(.MODULE$, resolvMsg), MDC.of(org.apache.spark.internal.LogKeys.HOST_PORT..MODULE$, resolvedAddress), MDC.of(org.apache.spark.internal.LogKeys.TIME..MODULE$, hostResolveTimeMs)});
      } else {
         logger.trace("DNS resolution {} for {} took {} ms", new Object[]{resolvMsg, resolvedAddress, hostResolveTimeMs});
      }

      synchronized(clientPool.locks[clientIndex]) {
         cachedClient = clientPool.clients[clientIndex];
         if (cachedClient != null) {
            if (cachedClient.isActive()) {
               logger.trace("Returning cached connection to {}: {}", resolvedAddress, cachedClient);
               return cachedClient;
            }

            logger.info("Found inactive connection to {}, creating a new one.", new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.HOST_PORT..MODULE$, resolvedAddress)});
         }

         if (fastFail && System.currentTimeMillis() - clientPool.lastConnectionFailed < (long)this.fastFailTimeWindow) {
            throw new IOException(String.format("Connecting to %s failed in the last %s ms, fail this connection directly", resolvedAddress, this.fastFailTimeWindow));
         } else {
            try {
               clientPool.clients[clientIndex] = this.createClient(resolvedAddress);
               clientPool.lastConnectionFailed = 0L;
            } catch (IOException e) {
               clientPool.lastConnectionFailed = System.currentTimeMillis();
               throw e;
            }

            return clientPool.clients[clientIndex];
         }
      }
   }

   public TransportClient createClient(String remoteHost, int remotePort) throws IOException, InterruptedException {
      return this.createClient(remoteHost, remotePort, false);
   }

   public TransportClient createUnmanagedClient(String remoteHost, int remotePort) throws IOException, InterruptedException {
      InetSocketAddress address = new InetSocketAddress(remoteHost, remotePort);
      return this.createClient(address);
   }

   @VisibleForTesting
   TransportClient createClient(final InetSocketAddress address) throws IOException, InterruptedException {
      logger.debug("Creating new connection to {}", address);
      Bootstrap bootstrap = new Bootstrap();
      int connCreateTimeout = this.conf.connectionCreationTimeoutMs();
      ((Bootstrap)((Bootstrap)((Bootstrap)((Bootstrap)((Bootstrap)bootstrap.group(this.workerGroup)).channel(this.socketChannelClass)).option(ChannelOption.TCP_NODELAY, true)).option(ChannelOption.SO_KEEPALIVE, true)).option(ChannelOption.CONNECT_TIMEOUT_MILLIS, connCreateTimeout)).option(ChannelOption.ALLOCATOR, this.pooledAllocator);
      if (this.conf.receiveBuf() > 0) {
         bootstrap.option(ChannelOption.SO_RCVBUF, this.conf.receiveBuf());
      }

      if (this.conf.sendBuf() > 0) {
         bootstrap.option(ChannelOption.SO_SNDBUF, this.conf.sendBuf());
      }

      final AtomicReference<TransportClient> clientRef = new AtomicReference();
      final AtomicReference<Channel> channelRef = new AtomicReference();
      bootstrap.handler(new ChannelInitializer() {
         public void initChannel(SocketChannel ch) {
            TransportChannelHandler clientHandler = TransportClientFactory.this.context.initializePipeline(ch, true);
            clientRef.set(clientHandler.getClient());
            channelRef.set(ch);
         }
      });
      long preConnect = System.nanoTime();
      final ChannelFuture cf = bootstrap.connect(address);
      if (connCreateTimeout <= 0) {
         cf.await();

         assert cf.isDone();

         if (cf.isCancelled()) {
            throw new IOException(String.format("Connecting to %s cancelled", address));
         }

         if (!cf.isSuccess()) {
            throw new IOException(String.format("Failed to connect to %s", address), cf.cause());
         }
      } else {
         if (!cf.await((long)connCreateTimeout)) {
            throw new IOException(String.format("Connecting to %s timed out (%s ms)", address, connCreateTimeout));
         }

         if (cf.cause() != null) {
            throw new IOException(String.format("Failed to connect to %s", address), cf.cause());
         }
      }

      if (this.context.sslEncryptionEnabled()) {
         SslHandler sslHandler = (SslHandler)cf.channel().pipeline().get(SslHandler.class);
         Future<Channel> future = sslHandler.handshakeFuture().addListener(new GenericFutureListener() {
            public void operationComplete(Future handshakeFuture) {
               if (handshakeFuture.isSuccess()) {
                  TransportClientFactory.logger.debug("{} successfully completed TLS handshake to ", address);
               } else {
                  TransportClientFactory.logger.info("failed to complete TLS handshake to {}", handshakeFuture.cause(), new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.HOST_PORT..MODULE$, address)});
                  cf.channel().close();
               }

            }
         });
         if (!future.await((long)this.conf.connectionTimeoutMs())) {
            cf.channel().close();
            throw new IOException(String.format("Failed to connect to %s within connection timeout", address));
         }
      }

      TransportClient client = (TransportClient)clientRef.get();
      Channel channel = (Channel)channelRef.get();

      assert client != null : "Channel future completed successfully with null client";

      long preBootstrap = System.nanoTime();
      logger.debug("Connection to {} successful, running bootstraps...", address);

      try {
         for(TransportClientBootstrap clientBootstrap : this.clientBootstraps) {
            clientBootstrap.doBootstrap(client, channel);
         }
      } catch (Exception e) {
         long bootstrapTimeMs = (System.nanoTime() - preBootstrap) / 1000000L;
         logger.error("Exception while bootstrapping client after {} ms", e, new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.BOOTSTRAP_TIME..MODULE$, bootstrapTimeMs)});
         client.close();
         Throwables.throwIfUnchecked(e);
         throw new RuntimeException(e);
      }

      long postBootstrap = System.nanoTime();
      logger.info("Successfully created connection to {} after {} ms ({} ms spent in bootstraps)", new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.HOST_PORT..MODULE$, address), MDC.of(org.apache.spark.internal.LogKeys.ELAPSED_TIME..MODULE$, (postBootstrap - preConnect) / 1000000L), MDC.of(org.apache.spark.internal.LogKeys.BOOTSTRAP_TIME..MODULE$, (postBootstrap - preBootstrap) / 1000000L)});
      return client;
   }

   public void close() {
      for(ClientPool clientPool : this.connectionPool.values()) {
         for(int i = 0; i < clientPool.clients.length; ++i) {
            TransportClient client = clientPool.clients[i];
            if (client != null) {
               clientPool.clients[i] = null;
               JavaUtils.closeQuietly(client);
            }
         }
      }

      this.connectionPool.clear();
      if (this.workerGroup != null && !this.workerGroup.isShuttingDown()) {
         this.workerGroup.shutdownGracefully();
      }

   }

   private static class ClientPool {
      TransportClient[] clients;
      Object[] locks;
      volatile long lastConnectionFailed;

      ClientPool(int size) {
         this.clients = new TransportClient[size];
         this.locks = new Object[size];

         for(int i = 0; i < size; ++i) {
            this.locks[i] = new Object();
         }

         this.lastConnectionFailed = 0L;
      }
   }
}
