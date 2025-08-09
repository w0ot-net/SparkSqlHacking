package io.vertx.core.http.impl;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http2.DelegatingDecompressorFrameListener;
import io.netty.handler.codec.http2.Http2CodecUtil;
import io.netty.handler.codec.http2.Http2Connection;
import io.netty.handler.codec.http2.Http2ConnectionDecoder;
import io.netty.handler.codec.http2.Http2ConnectionEncoder;
import io.netty.handler.codec.http2.Http2ConnectionHandler;
import io.netty.handler.codec.http2.Http2DataFrame;
import io.netty.handler.codec.http2.Http2Exception;
import io.netty.handler.codec.http2.Http2Flags;
import io.netty.handler.codec.http2.Http2FrameListener;
import io.netty.handler.codec.http2.Http2Headers;
import io.netty.handler.codec.http2.Http2HeadersFrame;
import io.netty.handler.codec.http2.Http2RemoteFlowController;
import io.netty.handler.codec.http2.Http2Settings;
import io.netty.handler.codec.http2.Http2Stream;
import io.netty.handler.codec.http2.Http2StreamFrame;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.concurrent.DefaultPromise;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.FutureListener;
import io.netty.util.concurrent.Promise;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.GoAway;
import io.vertx.core.net.impl.ConnectionBase;
import java.util.function.Function;

class VertxHttp2ConnectionHandler extends Http2ConnectionHandler implements Http2FrameListener {
   private final Function connectionFactory;
   private Http2ConnectionBase connection;
   private ChannelHandlerContext chctx;
   private Promise connectFuture;
   private boolean settingsRead;
   private Handler addHandler;
   private Handler removeHandler;
   private final boolean useDecompressor;
   private final Http2Settings initialSettings;
   public boolean upgraded;
   private boolean read;

   public VertxHttp2ConnectionHandler(Function connectionFactory, boolean useDecompressor, Http2ConnectionDecoder decoder, Http2ConnectionEncoder encoder, Http2Settings initialSettings) {
      super(decoder, encoder, initialSettings);
      this.connectionFactory = connectionFactory;
      this.useDecompressor = useDecompressor;
      this.initialSettings = initialSettings;
      this.encoder().flowController().listener((s) -> {
         if (this.connection != null) {
            this.connection.onStreamWritabilityChanged(s);
         }

      });
      this.connection().addListener(new Http2Connection.Listener() {
         public void onGoAwaySent(int lastStreamId, long errorCode, ByteBuf debugData) {
            VertxHttp2ConnectionHandler.this.connection.onGoAwaySent((new GoAway()).setErrorCode(errorCode).setLastStreamId(lastStreamId).setDebugData(Buffer.buffer(debugData)));
         }

         public void onGoAwayReceived(int lastStreamId, long errorCode, ByteBuf debugData) {
            VertxHttp2ConnectionHandler.this.connection.onGoAwayReceived((new GoAway()).setErrorCode(errorCode).setLastStreamId(lastStreamId).setDebugData(Buffer.buffer(debugData)));
         }

         public void onStreamAdded(Http2Stream stream) {
         }

         public void onStreamActive(Http2Stream stream) {
         }

         public void onStreamHalfClosed(Http2Stream stream) {
         }

         public void onStreamClosed(Http2Stream stream) {
            VertxHttp2ConnectionHandler.this.connection.onStreamClosed(stream);
         }

         public void onStreamRemoved(Http2Stream stream) {
         }
      });
   }

   public Future connectFuture() {
      if (this.connectFuture == null) {
         throw new IllegalStateException();
      } else {
         return this.connectFuture;
      }
   }

   public ChannelHandlerContext context() {
      return this.chctx;
   }

   public Http2Settings initialSettings() {
      return this.initialSettings;
   }

   public VertxHttp2ConnectionHandler addHandler(Handler handler) {
      this.addHandler = handler;
      return this;
   }

   public VertxHttp2ConnectionHandler removeHandler(Handler handler) {
      this.removeHandler = handler;
      this.connection = null;
      return this;
   }

   public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
      super.handlerAdded(ctx);
      this.chctx = ctx;
      this.connectFuture = new DefaultPromise(ctx.executor());
      this.connection = (Http2ConnectionBase)this.connectionFactory.apply(this);
   }

   public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
      Http2Exception http2Cause = Http2CodecUtil.getEmbeddedHttp2Exception(cause);
      if (http2Cause != null) {
         super.exceptionCaught(ctx, http2Cause);
      }

      ctx.close();
   }

   public void serverUpgrade(ChannelHandlerContext ctx, Http2Settings serverUpgradeSettings) throws Exception {
      this.upgraded = true;
      this.onHttpServerUpgrade(serverUpgradeSettings);
      this.onSettingsRead(ctx, serverUpgradeSettings);
   }

   public void clientUpgrade(ChannelHandlerContext ctx) throws Exception {
      this.upgraded = true;
      this.onHttpClientUpgrade();
      this.checkFlush();
   }

   public void channelInactive(ChannelHandlerContext chctx) throws Exception {
      if (this.connection != null) {
         if (this.settingsRead) {
            if (this.removeHandler != null) {
               this.removeHandler.handle(this.connection);
            }
         } else {
            this.connectFuture.tryFailure(ConnectionBase.CLOSED_EXCEPTION);
         }

         super.channelInactive(chctx);
         this.connection.handleClosed();
      } else {
         super.channelInactive(chctx);
      }

   }

   protected void onConnectionError(ChannelHandlerContext ctx, boolean outbound, Throwable cause, Http2Exception http2Ex) {
      this.connection.onConnectionError(cause);
      if (!this.settingsRead) {
         this.connectFuture.setFailure(http2Ex);
      }

      super.onConnectionError(ctx, outbound, cause, http2Ex);
   }

   protected void onStreamError(ChannelHandlerContext ctx, boolean outbound, Throwable cause, Http2Exception.StreamException http2Ex) {
      this.connection.onStreamError(http2Ex.streamId(), http2Ex);
      super.onStreamError(ctx, outbound, cause, http2Ex);
   }

   public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
      try {
         super.userEventTriggered(ctx, evt);
      } finally {
         if (evt instanceof IdleStateEvent) {
            this.connection.handleIdle((IdleStateEvent)evt);
         }

      }

   }

   void writeHeaders(Http2Stream stream, Http2Headers headers, boolean end, int streamDependency, short weight, boolean exclusive, boolean checkFlush, FutureListener listener) {
      ChannelPromise promise = listener == null ? this.chctx.voidPromise() : this.chctx.newPromise().addListener(listener);
      this.encoder().writeHeaders(this.chctx, stream.id(), headers, streamDependency, weight, exclusive, 0, end, promise);
      if (checkFlush) {
         this.checkFlush();
      }

   }

   void writeData(Http2Stream stream, ByteBuf chunk, boolean end, FutureListener listener) {
      ChannelPromise promise = listener == null ? this.chctx.voidPromise() : this.chctx.newPromise().addListener(listener);
      this.encoder().writeData(this.chctx, stream.id(), chunk, 0, end, promise);
      Http2RemoteFlowController controller = this.encoder().flowController();
      if (!controller.isWritable(stream) || end) {
         try {
            this.encoder().flowController().writePendingBytes();
         } catch (Http2Exception e) {
            this.onError(this.chctx, true, e);
         }
      }

      this.checkFlush();
   }

   private void checkFlush() {
      if (!this.read) {
         this.chctx.channel().flush();
      }

   }

   ChannelFuture writePing(long data) {
      ChannelPromise promise = this.chctx.newPromise();
      EventExecutor executor = this.chctx.executor();
      if (executor.inEventLoop()) {
         this._writePing(data, promise);
      } else {
         executor.execute(() -> this._writePing(data, promise));
      }

      return promise;
   }

   private void _writePing(long data, ChannelPromise promise) {
      this.encoder().writePing(this.chctx, false, data, promise);
      this.checkFlush();
   }

   void consume(Http2Stream stream, int numBytes) {
      try {
         boolean windowUpdateSent = this.decoder().flowController().consumeBytes(stream, numBytes);
         if (windowUpdateSent) {
            this.checkFlush();
         }
      } catch (Http2Exception e) {
         this.onError(this.chctx, true, e);
      }

   }

   void writeFrame(Http2Stream stream, byte type, short flags, ByteBuf payload) {
      this.encoder().writeFrame(this.chctx, type, stream.id(), new Http2Flags(flags), payload, this.chctx.newPromise());
      this.checkFlush();
   }

   void writeReset(int streamId, long code) {
      this.encoder().writeRstStream(this.chctx, streamId, code, this.chctx.newPromise());
      this.checkFlush();
   }

   void writeGoAway(long errorCode, int lastStreamId, ByteBuf debugData) {
      EventExecutor executor = this.chctx.executor();
      if (executor.inEventLoop()) {
         this._writeGoAway(errorCode, lastStreamId, debugData);
      } else {
         executor.execute(() -> this._writeGoAway(errorCode, lastStreamId, debugData));
      }

   }

   private void _writeGoAway(long errorCode, int lastStreamId, ByteBuf debugData) {
      this.encoder().writeGoAway(this.chctx, lastStreamId, errorCode, debugData, this.chctx.newPromise());
      this.checkFlush();
   }

   ChannelFuture writeSettings(Http2Settings settingsUpdate) {
      ChannelPromise promise = this.chctx.newPromise();
      EventExecutor executor = this.chctx.executor();
      if (executor.inEventLoop()) {
         this._writeSettings(settingsUpdate, promise);
      } else {
         executor.execute(() -> this._writeSettings(settingsUpdate, promise));
      }

      return promise;
   }

   private void _writeSettings(Http2Settings settingsUpdate, ChannelPromise promise) {
      this.encoder().writeSettings(this.chctx, settingsUpdate, promise);
      this.checkFlush();
   }

   Future writePushPromise(int streamId, Http2Headers headers) {
      int promisedStreamId = this.connection().local().incrementAndGetNextStreamId();
      DefaultPromise<Integer> future = new DefaultPromise(this.chctx.executor());
      ChannelPromise promise = this.chctx.newPromise();
      promise.addListener((fut) -> {
         if (fut.isSuccess()) {
            future.setSuccess(promisedStreamId);
         } else {
            future.setFailure(fut.cause());
         }

      });
      this._writePushPromise(streamId, promisedStreamId, headers, promise);
      this.checkFlush();
      return future;
   }

   int maxConcurrentStreams() {
      return this.connection().local().maxActiveStreams();
   }

   private void _writePushPromise(int streamId, int promisedStreamId, Http2Headers headers, ChannelPromise promise) {
      this.encoder().writePushPromise(this.chctx, streamId, promisedStreamId, headers, 0, promise);
   }

   public int onDataRead(ChannelHandlerContext ctx, int streamId, ByteBuf data, int padding, boolean endOfStream) throws Http2Exception {
      throw new UnsupportedOperationException();
   }

   public void onHeadersRead(ChannelHandlerContext ctx, int streamId, Http2Headers headers, int padding, boolean endOfStream) throws Http2Exception {
      throw new UnsupportedOperationException();
   }

   public void onHeadersRead(ChannelHandlerContext ctx, int streamId, Http2Headers headers, int streamDependency, short weight, boolean exclusive, int padding, boolean endOfStream) throws Http2Exception {
      this.connection.onHeadersRead(ctx, streamId, headers, streamDependency, weight, exclusive, padding, endOfStream);
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

   public void onSettingsRead(ChannelHandlerContext ctx, Http2Settings settings) throws Http2Exception {
      if (this.useDecompressor) {
         this.decoder().frameListener(new DelegatingDecompressorFrameListener(this.decoder().connection(), this.connection));
      } else {
         this.decoder().frameListener(this.connection);
      }

      this.connection.onSettingsRead(ctx, settings);
      this.settingsRead = true;
      if (this.addHandler != null) {
         this.addHandler.handle(this.connection);
      }

      this.connectFuture.setSuccess(this.connection);
   }

   public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
      this.read = true;
      if (msg instanceof ByteBuf) {
         super.channelRead(ctx, msg);
      } else if (msg instanceof Http2StreamFrame) {
         if (msg instanceof Http2HeadersFrame) {
            Http2HeadersFrame frame = (Http2HeadersFrame)msg;
            this.connection.onHeadersRead(ctx, 1, frame.headers(), frame.padding(), frame.isEndStream());
         } else if (msg instanceof Http2DataFrame) {
            Http2DataFrame frame = (Http2DataFrame)msg;
            this.connection.onDataRead(ctx, 1, frame.content(), frame.padding(), frame.isEndStream());
         }
      } else {
         super.channelRead(ctx, msg);
      }

   }

   public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
      this.read = false;
      super.channelReadComplete(ctx);
   }

   public void onPingRead(ChannelHandlerContext ctx, long data) throws Http2Exception {
      throw new UnsupportedOperationException();
   }

   public void onPingAckRead(ChannelHandlerContext ctx, long data) throws Http2Exception {
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

   private void _writePriority(Http2Stream stream, int streamDependency, short weight, boolean exclusive) {
      this.encoder().writePriority(this.chctx, stream.id(), streamDependency, weight, exclusive, this.chctx.newPromise());
   }

   void writePriority(Http2Stream stream, int streamDependency, short weight, boolean exclusive) {
      EventExecutor executor = this.chctx.executor();
      if (executor.inEventLoop()) {
         this._writePriority(stream, streamDependency, weight, exclusive);
      } else {
         executor.execute(() -> this._writePriority(stream, streamDependency, weight, exclusive));
      }

   }
}
