package io.vertx.core.http.impl;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.compression.CompressionOptions;
import io.netty.handler.codec.compression.StandardCompressionOptions;
import io.netty.handler.codec.haproxy.HAProxyMessageDecoder;
import io.netty.handler.codec.http.HttpContentCompressor;
import io.netty.handler.codec.http.HttpContentDecompressor;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.handler.traffic.GlobalTrafficShapingHandler;
import io.netty.util.concurrent.Promise;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.http.impl.cgbystrom.FlashPolicyHandler;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.impl.VertxInternal;
import io.vertx.core.net.impl.HAProxyMessageCompletionHandler;
import io.vertx.core.net.impl.SslChannelProvider;
import io.vertx.core.net.impl.SslHandshakeCompletionHandler;
import io.vertx.core.net.impl.VertxHandler;
import io.vertx.core.spi.metrics.HttpServerMetrics;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class HttpServerWorker implements BiConsumer {
   final ContextInternal context;
   private final Supplier streamContextSupplier;
   private final VertxInternal vertx;
   private final HttpServerImpl server;
   private final HttpServerOptions options;
   private final String serverOrigin;
   private final boolean logEnabled;
   private final boolean disableH2C;
   final Handler connectionHandler;
   private final Handler exceptionHandler;
   private final CompressionOptions[] compressionOptions;
   private final Function encodingDetector;
   private final GlobalTrafficShapingHandler trafficShapingHandler;

   public HttpServerWorker(ContextInternal context, Supplier streamContextSupplier, HttpServerImpl server, VertxInternal vertx, HttpServerOptions options, String serverOrigin, Handler connectionHandler, Handler exceptionHandler, GlobalTrafficShapingHandler trafficShapingHandler) {
      CompressionOptions[] compressionOptions = null;
      if (options.isCompressionSupported()) {
         List<CompressionOptions> compressors = options.getCompressors();
         if (compressors == null) {
            int compressionLevel = options.getCompressionLevel();
            compressionOptions = new CompressionOptions[]{StandardCompressionOptions.gzip(compressionLevel, 15, 8), StandardCompressionOptions.deflate(compressionLevel, 15, 8)};
         } else {
            compressionOptions = (CompressionOptions[])compressors.toArray(new CompressionOptions[0]);
         }
      }

      this.context = context;
      this.streamContextSupplier = streamContextSupplier;
      this.server = server;
      this.vertx = vertx;
      this.options = options;
      this.serverOrigin = serverOrigin;
      this.logEnabled = options.getLogActivity();
      this.disableH2C = !options.isHttp2ClearTextEnabled();
      this.connectionHandler = connectionHandler;
      this.exceptionHandler = exceptionHandler;
      this.compressionOptions = compressionOptions;
      this.encodingDetector = compressionOptions != null ? new EncodingDetector(compressionOptions)::determineEncoding : null;
      this.trafficShapingHandler = trafficShapingHandler;
   }

   public void accept(Channel ch, SslChannelProvider sslChannelProvider) {
      if (HAProxyMessageCompletionHandler.canUseProxyProtocol(this.options.isUseProxyProtocol())) {
         Promise<Channel> p = ch.eventLoop().newPromise();
         ch.pipeline().addLast(new ChannelHandler[]{new HAProxyMessageDecoder()});
         IdleStateHandler idle;
         if (this.options.getProxyProtocolTimeout() > 0L) {
            ch.pipeline().addLast("idle", idle = new IdleStateHandler(0L, 0L, this.options.getProxyProtocolTimeout(), this.options.getProxyProtocolTimeoutUnit()));
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

   private void configurePipeline(Channel ch, final SslChannelProvider sslChannelProvider) {
      final ChannelPipeline pipeline = ch.pipeline();
      if (this.options.isSsl()) {
         pipeline.addLast("ssl", sslChannelProvider.createServerHandler(HttpUtils.socketAddressToHostAndPort(ch.remoteAddress())));
         ChannelPromise p = ch.newPromise();
         pipeline.addLast("handshaker", new SslHandshakeCompletionHandler(p));
         p.addListener((future) -> {
            if (future.isSuccess()) {
               if (this.options.isUseAlpn()) {
                  SslHandler sslHandler = (SslHandler)pipeline.get(SslHandler.class);
                  String protocol = sslHandler.applicationProtocol();
                  if (protocol != null) {
                     switch (protocol) {
                        case "h2":
                           this.configureHttp2(ch.pipeline());
                           break;
                        case "http/1.1":
                        case "http/1.0":
                           this.configureHttp1Pipeline(ch.pipeline());
                           this.configureHttp1Handler(ch.pipeline(), sslChannelProvider);
                     }
                  } else {
                     this.configureHttp1Pipeline(ch.pipeline());
                     this.configureHttp1Handler(ch.pipeline(), sslChannelProvider);
                  }
               } else {
                  this.configureHttp1Pipeline(ch.pipeline());
                  this.configureHttp1Handler(ch.pipeline(), sslChannelProvider);
               }
            } else {
               this.handleException(future.cause());
            }

         });
      } else if (this.disableH2C) {
         this.configureHttp1Pipeline(ch.pipeline());
         this.configureHttp1Handler(ch.pipeline(), sslChannelProvider);
      } else {
         int idleTimeout = this.options.getIdleTimeout();
         int readIdleTimeout = this.options.getReadIdleTimeout();
         int writeIdleTimeout = this.options.getWriteIdleTimeout();
         final IdleStateHandler idle;
         if (idleTimeout <= 0 && readIdleTimeout <= 0 && writeIdleTimeout <= 0) {
            idle = null;
         } else {
            pipeline.addLast("idle", idle = new IdleStateHandler((long)readIdleTimeout, (long)writeIdleTimeout, (long)idleTimeout, this.options.getIdleTimeoutUnit()));
         }

         pipeline.addLast(new ChannelHandler[]{new Http1xOrH2CHandler() {
            protected void configure(ChannelHandlerContext ctx, boolean h2c) {
               if (idle != null) {
                  pipeline.remove(idle);
               }

               if (h2c) {
                  HttpServerWorker.this.configureHttp2(ctx.pipeline());
               } else {
                  HttpServerWorker.this.configureHttp1Pipeline(ctx.pipeline());
                  HttpServerWorker.this.configureHttp1OrH2CUpgradeHandler(ctx.pipeline(), sslChannelProvider);
               }

            }

            public void userEventTriggered(ChannelHandlerContext ctx, Object evt) {
               if (evt instanceof IdleStateEvent && ((IdleStateEvent)evt).state() == IdleState.ALL_IDLE) {
                  ctx.close();
               }

            }

            public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
               super.exceptionCaught(ctx, cause);
               HttpServerWorker.this.handleException(cause);
            }
         }});
      }

      if (this.trafficShapingHandler != null) {
         pipeline.addFirst("globalTrafficShaping", this.trafficShapingHandler);
      }

   }

   private void handleException(Throwable cause) {
      this.context.emit(cause, this.exceptionHandler);
   }

   private void sendServiceUnavailable(Channel ch) {
      ch.writeAndFlush(Unpooled.copiedBuffer("HTTP/1.1 503 Service Unavailable\r\nContent-Length:0\r\n\r\n", StandardCharsets.ISO_8859_1)).addListener(ChannelFutureListener.CLOSE);
   }

   private void configureHttp2(ChannelPipeline pipeline) {
      this.configureHttp2Handler(pipeline);
      this.configureHttp2Pipeline(pipeline);
   }

   private void configureHttp2Handler(ChannelPipeline pipeline) {
      VertxHttp2ConnectionHandler<Http2ServerConnection> handler = this.buildHttp2ConnectionHandler(this.context, this.connectionHandler);
      pipeline.addLast("handler", handler);
   }

   void configureHttp2Pipeline(ChannelPipeline pipeline) {
      if (!this.server.requestAccept()) {
         pipeline.channel().close();
      } else {
         int idleTimeout = this.options.getIdleTimeout();
         int readIdleTimeout = this.options.getReadIdleTimeout();
         int writeIdleTimeout = this.options.getWriteIdleTimeout();
         if (idleTimeout > 0 || readIdleTimeout > 0 || writeIdleTimeout > 0) {
            pipeline.addBefore("handler", "idle", new IdleStateHandler((long)readIdleTimeout, (long)writeIdleTimeout, (long)idleTimeout, this.options.getIdleTimeoutUnit()));
         }

      }
   }

   VertxHttp2ConnectionHandler buildHttp2ConnectionHandler(ContextInternal ctx, Handler handler_) {
      HttpServerMetrics metrics = (HttpServerMetrics)this.server.getMetrics();
      int maxRstFramesPerWindow = this.options.getHttp2RstFloodMaxRstFramePerWindow();
      int secondsPerWindow = (int)this.options.getHttp2RstFloodWindowDurationTimeUnit().toSeconds((long)this.options.getHttp2RstFloodWindowDuration());
      VertxHttp2ConnectionHandler<Http2ServerConnection> handler = (new VertxHttp2ConnectionHandlerBuilder()).server(true).useCompression(this.compressionOptions).decoderEnforceMaxRstFramesPerWindow(maxRstFramesPerWindow, secondsPerWindow).useDecompression(this.options.isDecompressionSupported()).initialSettings(this.options.getInitialSettings()).useUniformStreamByteDistributor(this.server.useH2UniformStreamByteDistributor).connectionFactory((connHandler) -> {
         Http2ServerConnection conn = new Http2ServerConnection(ctx, this.streamContextSupplier, this.serverOrigin, connHandler, this.encodingDetector, this.options, metrics);
         if (metrics != null) {
            conn.metric(metrics.connected(conn.remoteAddress(), conn.remoteName()));
         }

         return conn;
      }).logEnabled(this.logEnabled).build();
      handler.addHandler((conn) -> {
         if (this.options.getHttp2ConnectionWindowSize() > 0) {
            conn.setWindowSize(this.options.getHttp2ConnectionWindowSize());
         }

         handler_.handle(conn);
      });
      return handler;
   }

   private void configureHttp1OrH2CUpgradeHandler(ChannelPipeline pipeline, SslChannelProvider sslChannelProvider) {
      pipeline.addLast("h2c", new Http1xUpgradeToH2CHandler(this, sslChannelProvider, this.options.isCompressionSupported(), this.options.isDecompressionSupported()));
   }

   private void configureHttp1Pipeline(ChannelPipeline pipeline) {
      if (this.logEnabled) {
         pipeline.addLast("logging", new LoggingHandler(this.options.getActivityLogDataFormat()));
      }

      if (HttpServerImpl.USE_FLASH_POLICY_HANDLER) {
         pipeline.addLast("flashpolicy", new FlashPolicyHandler());
      }

      pipeline.addLast("httpDecoder", new VertxHttpRequestDecoder(this.options));
      pipeline.addLast("httpEncoder", new VertxHttpResponseEncoder());
      if (this.options.isDecompressionSupported()) {
         pipeline.addLast("inflater", new HttpContentDecompressor(false));
      }

      if (this.options.isCompressionSupported()) {
         pipeline.addLast("deflater", new HttpChunkContentCompressor(this.compressionOptions));
      }

      if (this.options.isSsl() || this.options.isCompressionSupported() || !this.vertx.transport().supportFileRegion() || this.trafficShapingHandler != null) {
         pipeline.addLast("chunkedWriter", new ChunkedWriteHandler());
      }

      int idleTimeout = this.options.getIdleTimeout();
      int readIdleTimeout = this.options.getReadIdleTimeout();
      int writeIdleTimeout = this.options.getWriteIdleTimeout();
      if (idleTimeout > 0 || readIdleTimeout > 0 || writeIdleTimeout > 0) {
         pipeline.addLast("idle", new IdleStateHandler((long)readIdleTimeout, (long)writeIdleTimeout, (long)idleTimeout, this.options.getIdleTimeoutUnit()));
      }

   }

   void configureHttp1Handler(ChannelPipeline pipeline, SslChannelProvider sslChannelProvider) {
      if (!this.server.requestAccept()) {
         this.sendServiceUnavailable(pipeline.channel());
      } else {
         HttpServerMetrics metrics = (HttpServerMetrics)this.server.getMetrics();
         VertxHandler<Http1xServerConnection> handler = VertxHandler.create((chctx) -> {
            Http1xServerConnection conn = new Http1xServerConnection(this.streamContextSupplier, sslChannelProvider, this.options, chctx, this.context, this.serverOrigin, metrics);
            return conn;
         });
         pipeline.addLast("handler", handler);
         Http1xServerConnection conn = (Http1xServerConnection)handler.getConnection();
         if (metrics != null) {
            conn.metric(metrics.connected(conn.remoteAddress(), conn.remoteName()));
         }

         this.connectionHandler.handle(conn);
      }
   }

   private static class EncodingDetector extends HttpContentCompressor {
      private EncodingDetector(CompressionOptions[] compressionOptions) {
         super(compressionOptions);
      }

      protected String determineEncoding(String acceptEncoding) {
         return super.determineEncoding(acceptEncoding);
      }
   }
}
