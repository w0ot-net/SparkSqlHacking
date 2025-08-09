package io.vertx.core.http.impl;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http2.Http2Connection;
import io.netty.handler.codec.http2.Http2Exception;
import io.netty.handler.codec.http2.Http2Flags;
import io.netty.handler.codec.http2.Http2FrameListener;
import io.netty.handler.codec.http2.Http2Headers;
import io.netty.handler.codec.http2.Http2LocalFlowController;
import io.netty.handler.codec.http2.Http2Stream;
import io.netty.handler.timeout.IdleStateEvent;
import io.vertx.codegen.annotations.Nullable;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.VertxException;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.buffer.impl.VertxByteBufAllocator;
import io.vertx.core.http.GoAway;
import io.vertx.core.http.Http2Settings;
import io.vertx.core.http.HttpClosedException;
import io.vertx.core.http.HttpConnection;
import io.vertx.core.http.StreamPriority;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.impl.VertxInternal;
import io.vertx.core.impl.future.PromiseInternal;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.core.net.impl.ConnectionBase;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

abstract class Http2ConnectionBase extends ConnectionBase implements Http2FrameListener, HttpConnection {
   private static final Logger log = LoggerFactory.getLogger(Http2ConnectionBase.class);
   protected final ChannelHandlerContext handlerContext;
   protected final VertxHttp2ConnectionHandler handler;
   protected final Http2Connection.PropertyKey streamKey;
   private boolean shutdown;
   private Handler remoteSettingsHandler;
   private final ArrayDeque updateSettingsHandlers = new ArrayDeque();
   private final ArrayDeque pongHandlers = new ArrayDeque();
   private io.netty.handler.codec.http2.Http2Settings localSettings;
   private io.netty.handler.codec.http2.Http2Settings remoteSettings;
   private Handler goAwayHandler;
   private Handler shutdownHandler;
   private Handler pingHandler;
   private GoAway goAwayStatus;
   private int windowSize;
   private long maxConcurrentStreams;

   private static ByteBuf safeBuffer(ByteBuf buf) {
      ByteBuf buffer = VertxByteBufAllocator.DEFAULT.heapBuffer(buf.readableBytes());
      buffer.writeBytes(buf);
      return buffer;
   }

   public Http2ConnectionBase(ContextInternal context, VertxHttp2ConnectionHandler handler) {
      super(context, handler.context());
      this.handler = handler;
      this.handlerContext = this.chctx;
      this.windowSize = ((Http2LocalFlowController)handler.connection().local().flowController()).windowSize(handler.connection().connectionStream());
      this.maxConcurrentStreams = 4294967295L;
      this.streamKey = handler.connection().newKey();
      this.localSettings = handler.initialSettings();
   }

   VertxInternal vertx() {
      return this.vertx;
   }

   public void handleClosed() {
      super.handleClosed();
   }

   protected void handleInterestedOpsChanged() {
   }

   protected void handleIdle(IdleStateEvent event) {
      super.handleIdle(event);
   }

   synchronized void onConnectionError(Throwable cause) {
      ArrayList<VertxHttp2Stream> streams = new ArrayList();

      try {
         this.handler.connection().forEachActiveStream((streamx) -> {
            streams.add(streamx.getProperty(this.streamKey));
            return true;
         });
      } catch (Http2Exception e) {
         log.error("Could not get the list of active streams", e);
      }

      for(VertxHttp2Stream stream : streams) {
         stream.context.dispatch((Handler)((v) -> stream.handleException(cause)));
      }

      this.handleException(cause);
   }

   VertxHttp2Stream stream(int id) {
      Http2Stream s = this.handler.connection().stream(id);
      return s == null ? null : (VertxHttp2Stream)s.getProperty(this.streamKey);
   }

   void onStreamError(int streamId, Throwable cause) {
      VertxHttp2Stream stream = this.stream(streamId);
      if (stream != null) {
         stream.onException(cause);
      }

   }

   void onStreamWritabilityChanged(Http2Stream s) {
      VertxHttp2Stream stream = (VertxHttp2Stream)s.getProperty(this.streamKey);
      if (stream != null) {
         stream.onWritabilityChanged();
      }

   }

   void onStreamClosed(Http2Stream s) {
      VertxHttp2Stream stream = (VertxHttp2Stream)s.getProperty(this.streamKey);
      if (stream != null) {
         boolean active = this.chctx.channel().isActive();
         if (this.goAwayStatus != null) {
            stream.onException(new HttpClosedException(this.goAwayStatus));
         } else if (!active) {
            stream.onException(HttpUtils.STREAM_CLOSED_EXCEPTION);
         }

         stream.onClose();
      }

      this.checkShutdown();
   }

   boolean onGoAwaySent(GoAway goAway) {
      synchronized(this) {
         if (this.goAwayStatus != null) {
            return false;
         }

         this.goAwayStatus = goAway;
      }

      this.checkShutdown();
      return true;
   }

   boolean onGoAwayReceived(GoAway goAway) {
      Handler<GoAway> handler;
      synchronized(this) {
         if (this.goAwayStatus != null) {
            return false;
         }

         this.goAwayStatus = goAway;
         handler = this.goAwayHandler;
      }

      if (handler != null) {
         this.context.dispatch(new GoAway(goAway), handler);
      }

      this.checkShutdown();
      return true;
   }

   public void onPriorityRead(ChannelHandlerContext ctx, int streamId, int streamDependency, short weight, boolean exclusive) {
      VertxHttp2Stream stream = this.stream(streamId);
      if (stream != null) {
         StreamPriority streamPriority = (new StreamPriority()).setDependency(streamDependency).setWeight(weight).setExclusive(exclusive);
         stream.onPriorityChange(streamPriority);
      }

   }

   public void onHeadersRead(ChannelHandlerContext ctx, int streamId, Http2Headers headers, int streamDependency, short weight, boolean exclusive, int padding, boolean endOfStream) throws Http2Exception {
      StreamPriority streamPriority = (new StreamPriority()).setDependency(streamDependency).setWeight(weight).setExclusive(exclusive);
      this.onHeadersRead(streamId, headers, streamPriority, endOfStream);
   }

   public void onHeadersRead(ChannelHandlerContext ctx, int streamId, Http2Headers headers, int padding, boolean endOfStream) throws Http2Exception {
      this.onHeadersRead(streamId, headers, (StreamPriority)null, endOfStream);
   }

   protected abstract void onHeadersRead(int var1, Http2Headers var2, StreamPriority var3, boolean var4);

   public void onSettingsAckRead(ChannelHandlerContext ctx) {
      Handler<Void> handler;
      synchronized(this) {
         handler = (Handler)this.updateSettingsHandlers.poll();
      }

      if (handler != null) {
         this.context.emit(handler);
      }

   }

   protected void concurrencyChanged(long concurrency) {
   }

   public void onSettingsRead(ChannelHandlerContext ctx, io.netty.handler.codec.http2.Http2Settings settings) {
      boolean changed;
      Handler<Http2Settings> handler;
      synchronized(this) {
         Long val = settings.maxConcurrentStreams();
         if (val != null) {
            if (this.remoteSettings != null) {
               changed = val != this.maxConcurrentStreams;
            } else {
               changed = false;
            }

            this.maxConcurrentStreams = val;
         } else {
            changed = false;
         }

         this.remoteSettings = settings;
         handler = this.remoteSettingsHandler;
      }

      if (handler != null) {
         this.context.dispatch(HttpUtils.toVertxSettings(settings), handler);
      }

      if (changed) {
         this.concurrencyChanged(this.maxConcurrentStreams);
      }

   }

   public void onPingRead(ChannelHandlerContext ctx, long data) throws Http2Exception {
      Handler<Buffer> handler = this.pingHandler;
      if (handler != null) {
         Buffer buff = Buffer.buffer().appendLong(data);
         this.context.dispatch((Handler)((v) -> handler.handle(buff)));
      }

   }

   public void onPingAckRead(ChannelHandlerContext ctx, long data) {
      Promise<Buffer> handler = (Promise)this.pongHandlers.poll();
      if (handler != null) {
         Buffer buff = Buffer.buffer().appendLong(data);
         handler.complete(buff);
      }

   }

   public void onPushPromiseRead(ChannelHandlerContext ctx, int streamId, int promisedStreamId, Http2Headers headers, int padding) throws Http2Exception {
   }

   public void onGoAwayRead(ChannelHandlerContext ctx, int lastStreamId, long errorCode, ByteBuf debugData) {
   }

   public void onWindowUpdateRead(ChannelHandlerContext ctx, int streamId, int windowSizeIncrement) {
   }

   public void onUnknownFrame(ChannelHandlerContext ctx, byte frameType, int streamId, Http2Flags flags, ByteBuf payload) {
      VertxHttp2Stream stream = this.stream(streamId);
      if (stream != null) {
         Buffer buff = Buffer.buffer(safeBuffer(payload));
         stream.onCustomFrame(new HttpFrameImpl(frameType, flags.value(), buff));
      }

   }

   public void onRstStreamRead(ChannelHandlerContext ctx, int streamId, long errorCode) {
      VertxHttp2Stream stream = this.stream(streamId);
      if (stream != null) {
         stream.onReset(errorCode);
      }

   }

   public int onDataRead(ChannelHandlerContext ctx, int streamId, ByteBuf data, int padding, boolean endOfStream) {
      VertxHttp2Stream stream = this.stream(streamId);
      if (stream != null) {
         data = safeBuffer(data);
         Buffer buff = Buffer.buffer(data);
         stream.onData(buff);
         if (endOfStream) {
            stream.onEnd();
         }
      }

      return padding;
   }

   public int getWindowSize() {
      return this.windowSize;
   }

   public HttpConnection setWindowSize(int windowSize) {
      try {
         Http2Stream stream = this.handler.encoder().connection().connectionStream();
         int delta = windowSize - this.windowSize;
         this.handler.decoder().flowController().incrementWindowSize(stream, delta);
         this.windowSize = windowSize;
         return this;
      } catch (Http2Exception e) {
         throw new VertxException(e);
      }
   }

   public HttpConnection goAway(long errorCode, int lastStreamId, Buffer debugData) {
      if (errorCode < 0L) {
         throw new IllegalArgumentException();
      } else {
         if (lastStreamId < 0) {
            lastStreamId = this.handler.connection().remote().lastStreamCreated();
         }

         this.handler.writeGoAway(errorCode, lastStreamId, debugData != null ? debugData.getByteBuf() : Unpooled.EMPTY_BUFFER);
         return this;
      }
   }

   public synchronized HttpConnection goAwayHandler(Handler handler) {
      this.goAwayHandler = handler;
      return this;
   }

   public synchronized HttpConnection shutdownHandler(Handler handler) {
      this.shutdownHandler = handler;
      return this;
   }

   public Future shutdown(long timeout, TimeUnit unit) {
      PromiseInternal<Void> promise = this.vertx.promise();
      this.shutdown(unit.toMillis(timeout), promise);
      return promise.future();
   }

   private void shutdown(long timeout, PromiseInternal promise) {
      if (timeout < 0L) {
         promise.fail("Invalid timeout value " + timeout);
      } else {
         this.handler.gracefulShutdownTimeoutMillis(timeout);
         ChannelFuture fut = this.channel().close();
         fut.addListener(promise);
      }
   }

   public Http2ConnectionBase closeHandler(Handler handler) {
      return (Http2ConnectionBase)super.closeHandler(handler);
   }

   public Future close() {
      PromiseInternal<Void> promise = this.context.promise();
      ChannelPromise pr = this.chctx.newPromise();
      ChannelPromise channelPromise = pr.addListener(promise);
      this.handlerContext.writeAndFlush(Unpooled.EMPTY_BUFFER, pr);
      channelPromise.addListener((ChannelFutureListener)(future) -> this.shutdown(0L));
      return promise.future();
   }

   public synchronized HttpConnection remoteSettingsHandler(Handler handler) {
      this.remoteSettingsHandler = handler;
      return this;
   }

   public synchronized Http2Settings remoteSettings() {
      return HttpUtils.toVertxSettings(this.remoteSettings);
   }

   public synchronized Http2Settings settings() {
      return HttpUtils.toVertxSettings(this.localSettings);
   }

   public Future updateSettings(Http2Settings settings) {
      Promise<Void> promise = this.context.promise();
      io.netty.handler.codec.http2.Http2Settings settingsUpdate = HttpUtils.fromVertxSettings(settings);
      this.updateSettings((io.netty.handler.codec.http2.Http2Settings)settingsUpdate, promise);
      return promise.future();
   }

   public HttpConnection updateSettings(Http2Settings settings, @Nullable Handler completionHandler) {
      this.updateSettings(settings).onComplete(completionHandler);
      return this;
   }

   protected void updateSettings(io.netty.handler.codec.http2.Http2Settings settingsUpdate, Handler completionHandler) {
      io.netty.handler.codec.http2.Http2Settings current = this.handler.decoder().localSettings();

      for(Map.Entry entry : current.entrySet()) {
         Character key = (Character)entry.getKey();
         if (Objects.equals(settingsUpdate.get(key), entry.getValue())) {
            settingsUpdate.remove(key);
         }
      }

      Handler<Void> pending = (v) -> {
         synchronized(this) {
            this.localSettings.putAll(settingsUpdate);
         }

         if (completionHandler != null) {
            completionHandler.handle(Future.succeededFuture());
         }

      };
      this.updateSettingsHandlers.add(pending);
      this.handler.writeSettings(settingsUpdate).addListener((fut) -> {
         if (!fut.isSuccess()) {
            synchronized(this) {
               this.updateSettingsHandlers.remove(pending);
            }

            if (completionHandler != null) {
               completionHandler.handle(Future.failedFuture(fut.cause()));
            }
         }

      });
   }

   public Future ping(Buffer data) {
      if (data.length() != 8) {
         throw new IllegalArgumentException("Ping data must be exactly 8 bytes");
      } else {
         Promise<Buffer> promise = this.context.promise();
         this.handler.writePing(data.getLong(0)).addListener((fut) -> {
            if (fut.isSuccess()) {
               synchronized(this) {
                  this.pongHandlers.add(promise);
               }
            } else {
               promise.fail(fut.cause());
            }

         });
         return promise.future();
      }
   }

   public HttpConnection ping(Buffer data, Handler pongHandler) {
      Future<Buffer> fut = this.ping(data);
      if (pongHandler != null) {
         fut.onComplete(pongHandler);
      }

      return this;
   }

   public synchronized HttpConnection pingHandler(Handler handler) {
      this.pingHandler = handler;
      return this;
   }

   public Http2ConnectionBase exceptionHandler(Handler handler) {
      return (Http2ConnectionBase)super.exceptionHandler(handler);
   }

   void consumeCredits(Http2Stream stream, int numBytes) {
      this.handler.consume(stream, numBytes);
   }

   private void checkShutdown() {
      Handler<Void> shutdownHandler;
      synchronized(this) {
         if (this.shutdown) {
            return;
         }

         Http2Connection conn = this.handler.connection();
         if (!conn.goAwayReceived() && !conn.goAwaySent() || conn.numActiveStreams() > 0) {
            return;
         }

         this.shutdown = true;
         shutdownHandler = this.shutdownHandler;
      }

      this.doShutdown(shutdownHandler);
   }

   protected void doShutdown(Handler shutdownHandler) {
      if (shutdownHandler != null) {
         this.context.dispatch(shutdownHandler);
      }

   }
}
