package org.apache.spark.network;

import com.codahale.metrics.Counter;
import io.netty.channel.Channel;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import org.apache.spark.internal.SparkLogger;
import org.apache.spark.internal.SparkLoggerFactory;
import org.apache.spark.network.client.TransportClient;
import org.apache.spark.network.client.TransportClientBootstrap;
import org.apache.spark.network.client.TransportClientFactory;
import org.apache.spark.network.client.TransportResponseHandler;
import org.apache.spark.network.protocol.MessageDecoder;
import org.apache.spark.network.protocol.MessageEncoder;
import org.apache.spark.network.protocol.SslMessageEncoder;
import org.apache.spark.network.server.ChunkFetchRequestHandler;
import org.apache.spark.network.server.RpcHandler;
import org.apache.spark.network.server.TransportChannelHandler;
import org.apache.spark.network.server.TransportRequestHandler;
import org.apache.spark.network.server.TransportServer;
import org.apache.spark.network.server.TransportServerBootstrap;
import org.apache.spark.network.ssl.SSLFactory;
import org.apache.spark.network.util.IOMode;
import org.apache.spark.network.util.NettyLogger;
import org.apache.spark.network.util.NettyUtils;
import org.apache.spark.network.util.TransportConf;

public class TransportContext implements Closeable {
   private static final SparkLogger logger = SparkLoggerFactory.getLogger(TransportContext.class);
   private static final NettyLogger nettyLogger = new NettyLogger();
   private final TransportConf conf;
   private final RpcHandler rpcHandler;
   private final boolean closeIdleConnections;
   @Nullable
   private final SSLFactory sslFactory;
   private Counter registeredConnections;
   private static final MessageToMessageEncoder ENCODER;
   private static final MessageToMessageEncoder SSL_ENCODER;
   private static final MessageDecoder DECODER;
   private final EventLoopGroup chunkFetchWorkers;

   public TransportContext(TransportConf conf, RpcHandler rpcHandler) {
      this(conf, rpcHandler, false, false);
   }

   public TransportContext(TransportConf conf, RpcHandler rpcHandler, boolean closeIdleConnections) {
      this(conf, rpcHandler, closeIdleConnections, false);
   }

   public TransportContext(TransportConf conf, RpcHandler rpcHandler, boolean closeIdleConnections, boolean isClientOnly) {
      this.registeredConnections = new Counter();
      this.conf = conf;
      this.rpcHandler = rpcHandler;
      this.closeIdleConnections = closeIdleConnections;
      this.sslFactory = this.createSslFactory();
      if (conf.getModuleName() != null && conf.getModuleName().equalsIgnoreCase("shuffle") && !isClientOnly && conf.separateChunkFetchRequest()) {
         this.chunkFetchWorkers = NettyUtils.createEventLoop(IOMode.valueOf(conf.ioMode()), conf.chunkFetchHandlerThreads(), "shuffle-chunk-fetch-handler");
      } else {
         this.chunkFetchWorkers = null;
      }

   }

   public TransportClientFactory createClientFactory(List bootstraps) {
      return new TransportClientFactory(this, bootstraps);
   }

   public TransportClientFactory createClientFactory() {
      return this.createClientFactory(new ArrayList());
   }

   public TransportServer createServer(int port, List bootstraps) {
      return new TransportServer(this, (String)null, port, this.rpcHandler, bootstraps);
   }

   public TransportServer createServer(String host, int port, List bootstraps) {
      return new TransportServer(this, host, port, this.rpcHandler, bootstraps);
   }

   public TransportServer createServer(List bootstraps) {
      return this.createServer(0, bootstraps);
   }

   public TransportServer createServer() {
      return this.createServer(0, new ArrayList());
   }

   public TransportChannelHandler initializePipeline(SocketChannel channel, boolean isClient) {
      return this.initializePipeline(channel, this.rpcHandler, isClient);
   }

   public boolean sslEncryptionEnabled() {
      return this.sslFactory != null;
   }

   public TransportChannelHandler initializePipeline(SocketChannel channel, RpcHandler channelRpcHandler, boolean isClient) {
      try {
         TransportChannelHandler channelHandler = this.createChannelHandler(channel, channelRpcHandler);
         ChannelPipeline pipeline = channel.pipeline();
         if (nettyLogger.getLoggingHandler() != null) {
            pipeline.addLast("loggingHandler", nettyLogger.getLoggingHandler());
         }

         if (this.sslEncryptionEnabled()) {
            SslHandler sslHandler;
            try {
               sslHandler = new SslHandler(this.sslFactory.createSSLEngine(isClient, channel.alloc()));
            } catch (Exception e) {
               throw new IllegalStateException("Error creating Netty SslHandler", e);
            }

            pipeline.addFirst("NettySslEncryptionHandler", sslHandler);
            pipeline.addLast("chunkedWriter", new ChunkedWriteHandler());
         }

         pipeline.addLast("encoder", this.sslEncryptionEnabled() ? SSL_ENCODER : ENCODER).addLast("frameDecoder", NettyUtils.createFrameDecoder()).addLast("decoder", this.getDecoder()).addLast("idleStateHandler", new IdleStateHandler(0, 0, this.conf.connectionTimeoutMs() / 1000)).addLast("handler", channelHandler);
         if (this.chunkFetchWorkers != null) {
            ChunkFetchRequestHandler chunkFetchHandler = new ChunkFetchRequestHandler(channelHandler.getClient(), this.rpcHandler.getStreamManager(), this.conf.maxChunksBeingTransferred(), true);
            pipeline.addLast(this.chunkFetchWorkers, "chunkFetchHandler", chunkFetchHandler);
         }

         return channelHandler;
      } catch (RuntimeException e) {
         logger.error("Error while initializing Netty pipeline", e);
         throw e;
      }
   }

   protected MessageToMessageDecoder getDecoder() {
      return DECODER;
   }

   private SSLFactory createSslFactory() {
      if (this.conf.sslRpcEnabled()) {
         if (this.conf.sslRpcEnabledAndKeysAreValid()) {
            return (new SSLFactory.Builder()).openSslEnabled(this.conf.sslRpcOpenSslEnabled()).requestedProtocol(this.conf.sslRpcProtocol()).requestedCiphers(this.conf.sslRpcRequestedCiphers()).keyStore(this.conf.sslRpcKeyStore(), this.conf.sslRpcKeyStorePassword()).privateKey(this.conf.sslRpcPrivateKey()).privateKeyPassword(this.conf.sslRpcPrivateKeyPassword()).keyPassword(this.conf.sslRpcKeyPassword()).certChain(this.conf.sslRpcCertChain()).trustStore(this.conf.sslRpcTrustStore(), this.conf.sslRpcTrustStorePassword(), this.conf.sslRpcTrustStoreReloadingEnabled(), this.conf.sslRpctrustStoreReloadIntervalMs()).build();
         } else {
            logger.error("RPC SSL encryption enabled but keys not found!Please ensure the configured keys are present.");
            throw new IllegalArgumentException("RPC SSL encryption enabled but keys not found!");
         }
      } else {
         return null;
      }
   }

   private TransportChannelHandler createChannelHandler(Channel channel, RpcHandler rpcHandler) {
      TransportResponseHandler responseHandler = new TransportResponseHandler(channel);
      TransportClient client = new TransportClient(channel, responseHandler);
      boolean separateChunkFetchRequest = this.conf.separateChunkFetchRequest();
      ChunkFetchRequestHandler chunkFetchRequestHandler = null;
      if (!separateChunkFetchRequest) {
         chunkFetchRequestHandler = new ChunkFetchRequestHandler(client, rpcHandler.getStreamManager(), this.conf.maxChunksBeingTransferred(), false);
      }

      TransportRequestHandler requestHandler = new TransportRequestHandler(channel, client, rpcHandler, this.conf.maxChunksBeingTransferred(), chunkFetchRequestHandler);
      return new TransportChannelHandler(client, responseHandler, requestHandler, (long)this.conf.connectionTimeoutMs(), separateChunkFetchRequest, this.closeIdleConnections, this);
   }

   public TransportConf getConf() {
      return this.conf;
   }

   public Counter getRegisteredConnections() {
      return this.registeredConnections;
   }

   public void close() {
      if (this.chunkFetchWorkers != null) {
         this.chunkFetchWorkers.shutdownGracefully();
      }

      if (this.sslFactory != null) {
         this.sslFactory.destroy();
      }

   }

   static {
      ENCODER = MessageEncoder.INSTANCE;
      SSL_ENCODER = SslMessageEncoder.INSTANCE;
      DECODER = MessageDecoder.INSTANCE;
   }
}
