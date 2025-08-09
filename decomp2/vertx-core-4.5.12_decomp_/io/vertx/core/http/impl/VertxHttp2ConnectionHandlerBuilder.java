package io.vertx.core.http.impl;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.compression.CompressionOptions;
import io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder;
import io.netty.handler.codec.http2.CompressorHttp2ConnectionEncoder;
import io.netty.handler.codec.http2.DefaultHttp2Connection;
import io.netty.handler.codec.http2.DefaultHttp2RemoteFlowController;
import io.netty.handler.codec.http2.Http2ConnectionDecoder;
import io.netty.handler.codec.http2.Http2ConnectionEncoder;
import io.netty.handler.codec.http2.Http2Exception;
import io.netty.handler.codec.http2.Http2Flags;
import io.netty.handler.codec.http2.Http2FrameListener;
import io.netty.handler.codec.http2.Http2FrameLogger;
import io.netty.handler.codec.http2.Http2Headers;
import io.netty.handler.codec.http2.StreamByteDistributor;
import io.netty.handler.codec.http2.UniformStreamByteDistributor;
import io.netty.handler.codec.http2.WeightedFairQueueByteDistributor;
import io.netty.handler.logging.LogLevel;
import io.vertx.core.http.Http2Settings;
import java.util.function.Function;

class VertxHttp2ConnectionHandlerBuilder extends AbstractHttp2ConnectionHandlerBuilder {
   private boolean useUniformStreamByteDistributor;
   private boolean useDecompression;
   private CompressionOptions[] compressionOptions;
   private Function connectionFactory;
   private boolean logEnabled;
   private boolean server;

   protected VertxHttp2ConnectionHandlerBuilder server(boolean isServer) {
      this.server = isServer;
      return this;
   }

   VertxHttp2ConnectionHandlerBuilder initialSettings(Http2Settings settings) {
      HttpUtils.fromVertxInitialSettings(this.server, settings, this.initialSettings());
      return this;
   }

   VertxHttp2ConnectionHandlerBuilder useCompression(CompressionOptions[] compressionOptions) {
      this.compressionOptions = compressionOptions;
      return this;
   }

   VertxHttp2ConnectionHandlerBuilder useUniformStreamByteDistributor(boolean useUniformStreamByteDistributor) {
      this.useUniformStreamByteDistributor = useUniformStreamByteDistributor;
      return this;
   }

   protected VertxHttp2ConnectionHandlerBuilder decoderEnforceMaxRstFramesPerWindow(int maxRstFramesPerWindow, int secondsPerWindow) {
      return (VertxHttp2ConnectionHandlerBuilder)super.decoderEnforceMaxRstFramesPerWindow(maxRstFramesPerWindow, secondsPerWindow);
   }

   protected VertxHttp2ConnectionHandlerBuilder gracefulShutdownTimeoutMillis(long gracefulShutdownTimeoutMillis) {
      return (VertxHttp2ConnectionHandlerBuilder)super.gracefulShutdownTimeoutMillis(gracefulShutdownTimeoutMillis);
   }

   VertxHttp2ConnectionHandlerBuilder useDecompression(boolean useDecompression) {
      this.useDecompression = useDecompression;
      return this;
   }

   VertxHttp2ConnectionHandlerBuilder connectionFactory(Function connectionFactory) {
      this.connectionFactory = connectionFactory;
      return this;
   }

   VertxHttp2ConnectionHandlerBuilder logEnabled(boolean logEnabled) {
      this.logEnabled = logEnabled;
      return this;
   }

   protected VertxHttp2ConnectionHandler build() {
      if (this.logEnabled) {
         this.frameLogger(new Http2FrameLogger(LogLevel.DEBUG));
      }

      this.configureStreamByteDistributor();
      this.frameListener(new Http2FrameListener() {
         public int onDataRead(ChannelHandlerContext ctx, int streamId, ByteBuf data, int padding, boolean endOfStream) throws Http2Exception {
            throw new UnsupportedOperationException();
         }

         public void onHeadersRead(ChannelHandlerContext ctx, int streamId, Http2Headers headers, int padding, boolean endOfStream) throws Http2Exception {
            throw new UnsupportedOperationException();
         }

         public void onHeadersRead(ChannelHandlerContext ctx, int streamId, Http2Headers headers, int streamDependency, short weight, boolean exclusive, int padding, boolean endOfStream) throws Http2Exception {
            throw new UnsupportedOperationException();
         }

         public void onPriorityRead(ChannelHandlerContext ctx, int streamId, int streamDependency, short weight, boolean exclusive) throws Http2Exception {
            throw new UnsupportedOperationException();
         }

         public void onRstStreamRead(ChannelHandlerContext ctx, int streamId, long errorCode) throws Http2Exception {
            throw new UnsupportedOperationException();
         }

         public void onSettingsAckRead(ChannelHandlerContext ctx) throws Http2Exception {
            throw new UnsupportedOperationException();
         }

         public void onSettingsRead(ChannelHandlerContext ctx, io.netty.handler.codec.http2.Http2Settings settings) throws Http2Exception {
            throw new UnsupportedOperationException();
         }

         public void onPingRead(ChannelHandlerContext channelHandlerContext, long l) throws Http2Exception {
            throw new UnsupportedOperationException();
         }

         public void onPingAckRead(ChannelHandlerContext channelHandlerContext, long l) throws Http2Exception {
            throw new UnsupportedOperationException();
         }

         public void onPushPromiseRead(ChannelHandlerContext ctx, int streamId, int promisedStreamId, Http2Headers headers, int padding) throws Http2Exception {
            throw new UnsupportedOperationException();
         }

         public void onGoAwayRead(ChannelHandlerContext ctx, int lastStreamId, long errorCode, ByteBuf debugData) throws Http2Exception {
            throw new UnsupportedOperationException();
         }

         public void onWindowUpdateRead(ChannelHandlerContext ctx, int streamId, int windowSizeIncrement) throws Http2Exception {
            throw new UnsupportedOperationException();
         }

         public void onUnknownFrame(ChannelHandlerContext ctx, byte frameType, int streamId, Http2Flags flags, ByteBuf payload) throws Http2Exception {
            throw new UnsupportedOperationException();
         }
      });
      return (VertxHttp2ConnectionHandler)super.build();
   }

   private void configureStreamByteDistributor() {
      DefaultHttp2Connection conn = new DefaultHttp2Connection(this.server, this.maxReservedStreams());
      StreamByteDistributor distributor;
      if (this.useUniformStreamByteDistributor) {
         distributor = new UniformStreamByteDistributor(conn);
      } else {
         distributor = new WeightedFairQueueByteDistributor(conn);
      }

      conn.remote().flowController(new DefaultHttp2RemoteFlowController(conn, distributor));
      this.connection(conn);
   }

   protected VertxHttp2ConnectionHandler build(Http2ConnectionDecoder decoder, Http2ConnectionEncoder encoder, io.netty.handler.codec.http2.Http2Settings initialSettings) throws Exception {
      if (this.server) {
         if (this.compressionOptions != null) {
            encoder = new CompressorHttp2ConnectionEncoder(encoder, this.compressionOptions);
         }

         VertxHttp2ConnectionHandler<C> handler = new VertxHttp2ConnectionHandler(this.connectionFactory, this.useDecompression, decoder, encoder, initialSettings);
         decoder.frameListener(handler);
         return handler;
      } else {
         VertxHttp2ConnectionHandler<C> handler = new VertxHttp2ConnectionHandler(this.connectionFactory, this.useDecompression, decoder, encoder, initialSettings);
         decoder.frameListener(handler);
         return handler;
      }
   }
}
