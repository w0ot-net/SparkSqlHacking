package io.vertx.core.http.impl;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.EventLoop;
import io.netty.handler.codec.http2.EmptyHttp2Headers;
import io.netty.handler.codec.http2.Http2Headers;
import io.netty.handler.codec.http2.Http2Stream;
import io.netty.util.concurrent.FutureListener;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpFrame;
import io.vertx.core.http.StreamPriority;
import io.vertx.core.http.StreamResetException;
import io.vertx.core.http.impl.headers.Http2HeadersAdaptor;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.impl.VertxInternal;
import io.vertx.core.streams.impl.InboundBuffer;

abstract class VertxHttp2Stream {
   private static final MultiMap EMPTY;
   protected final Http2ConnectionBase conn;
   protected final VertxInternal vertx;
   protected final ContextInternal context;
   protected Http2Stream stream;
   private StreamPriority priority;
   private final InboundBuffer pending;
   private boolean writable;
   private long bytesRead;
   private long bytesWritten;
   private int writeInProgress = 0;
   protected boolean isConnect;
   private long reset = -1L;

   VertxHttp2Stream(Http2ConnectionBase conn, ContextInternal context) {
      this.conn = conn;
      this.vertx = conn.vertx();
      this.context = context;
      this.pending = new InboundBuffer(context, 5L);
      this.priority = HttpUtils.DEFAULT_STREAM_PRIORITY;
      this.writable = true;
      this.isConnect = false;
      this.pending.handler((item) -> {
         if (item instanceof MultiMap) {
            this.handleEnd((MultiMap)item);
         } else {
            Buffer data = (Buffer)item;
            int len = data.length();
            conn.getContext().emit((Object)null, (v) -> {
               if (this.stream.state().remoteSideOpen()) {
                  conn.consumeCredits(this.stream, len);
               }

            });
            this.handleData(data);
         }

      });
      this.pending.exceptionHandler(context::reportException);
      this.pending.resume();
   }

   void init(Http2Stream stream) {
      synchronized(this) {
         this.stream = stream;
         this.writable = this.conn.handler.encoder().flowController().isWritable(stream);
      }

      stream.setProperty(this.conn.streamKey, this);
   }

   void onClose() {
      this.conn.flushBytesWritten();
      this.context.execute((Handler)((ex) -> this.handleClose()));
   }

   void onException(Throwable cause) {
      this.context.emit(cause, this::handleException);
   }

   void onReset(long code) {
      this.context.emit(code, this::handleReset);
   }

   void onPriorityChange(StreamPriority newPriority) {
      this.context.emit(newPriority, (priority) -> {
         if (!this.priority.equals(priority)) {
            this.priority = priority;
            this.handlePriorityChange(priority);
         }

      });
   }

   void onCustomFrame(HttpFrame frame) {
      this.context.emit(frame, this::handleCustomFrame);
   }

   void onHeaders(Http2Headers headers, StreamPriority streamPriority) {
   }

   void onData(Buffer data) {
      this.bytesRead += (long)data.length();
      this.conn.reportBytesRead((long)data.length());
      InboundBuffer var10002 = this.pending;
      this.context.execute(data, var10002::write);
   }

   void onWritabilityChanged() {
      this.context.emit((Object)null, (v) -> {
         boolean w;
         synchronized(this) {
            this.writable = !this.writable;
            w = this.writable;
         }

         this.handleWritabilityChanged(w);
      });
   }

   void onEnd() {
      this.onEnd(EMPTY);
   }

   void onEnd(MultiMap trailers) {
      this.conn.flushBytesRead();
      InboundBuffer var10002 = this.pending;
      this.context.emit(trailers, var10002::write);
   }

   public int id() {
      return this.stream.id();
   }

   long bytesWritten() {
      return this.bytesWritten;
   }

   long bytesRead() {
      return this.bytesRead;
   }

   public void doPause() {
      this.pending.pause();
   }

   public void doFetch(long amount) {
      this.pending.fetch(amount);
   }

   public synchronized boolean isNotWritable() {
      return !this.writable;
   }

   public final void writeFrame(int type, int flags, ByteBuf payload) {
      EventLoop eventLoop = this.conn.getContext().nettyEventLoop();
      if (eventLoop.inEventLoop()) {
         this.doWriteFrame(type, flags, payload);
      } else {
         eventLoop.execute(() -> this.doWriteFrame(type, flags, payload));
      }

   }

   private void doWriteFrame(int type, int flags, ByteBuf payload) {
      this.conn.handler.writeFrame(this.stream, (byte)type, (short)flags, payload);
   }

   protected final boolean checkReset(Handler handler) {
      long reset;
      synchronized(this) {
         reset = this.reset;
         if (reset == -1L) {
            return false;
         }
      }

      handler.handle(this.context.failedFuture((Throwable)(new StreamResetException(reset))));
      return true;
   }

   final void writeHeaders(Http2Headers headers, boolean end, boolean checkFlush, Handler handler) {
      if (!this.checkReset(handler)) {
         EventLoop eventLoop = this.conn.getContext().nettyEventLoop();
         synchronized(this) {
            if (this.shouldQueue(eventLoop)) {
               this.queueForWrite(eventLoop, () -> this.doWriteHeaders(headers, end, checkFlush, handler));
               return;
            }
         }

         this.doWriteHeaders(headers, end, checkFlush, handler);
      }
   }

   void doWriteHeaders(Http2Headers headers, boolean end, boolean checkFlush, Handler handler) {
      FutureListener<Void> promise = handler == null ? null : this.context.promise(handler);
      if (end) {
         this.endWritten();
      }

      this.conn.handler.writeHeaders(this.stream, headers, end, this.priority.getDependency(), this.priority.getWeight(), this.priority.isExclusive(), checkFlush, promise);
   }

   protected void endWritten() {
   }

   private void writePriorityFrame(StreamPriority priority) {
      this.conn.handler.writePriority(this.stream, priority.getDependency(), priority.getWeight(), priority.isExclusive());
   }

   final void writeData(ByteBuf chunk, boolean end, Handler handler) {
      if (!this.checkReset(handler)) {
         ContextInternal ctx = this.conn.getContext();
         EventLoop eventLoop = ctx.nettyEventLoop();
         synchronized(this) {
            if (this.shouldQueue(eventLoop)) {
               this.queueForWrite(eventLoop, () -> this.doWriteData(chunk, end, handler));
               return;
            }
         }

         this.doWriteData(chunk, end, handler);
      }
   }

   protected boolean shouldQueue(EventLoop eventLoop) {
      return !eventLoop.inEventLoop() || this.writeInProgress > 0;
   }

   protected void queueForWrite(EventLoop eventLoop, Runnable action) {
      ++this.writeInProgress;
      eventLoop.execute(() -> {
         synchronized(this) {
            --this.writeInProgress;
         }

         action.run();
      });
   }

   void doWriteData(ByteBuf buf, boolean end, Handler handler) {
      ByteBuf chunk;
      if (buf == null && end) {
         chunk = Unpooled.EMPTY_BUFFER;
      } else {
         chunk = buf;
      }

      int numOfBytes = chunk.readableBytes();
      this.bytesWritten += (long)numOfBytes;
      this.conn.reportBytesWritten((long)numOfBytes);
      FutureListener<Void> promise = handler == null ? null : this.context.promise(handler);
      if (end) {
         this.endWritten();
      }

      this.conn.handler.writeData(this.stream, chunk, end, promise);
   }

   final void writeReset(long code) {
      EventLoop eventLoop = this.conn.getContext().nettyEventLoop();
      if (eventLoop.inEventLoop()) {
         this.doWriteReset(code);
      } else {
         eventLoop.execute(() -> this.doWriteReset(code));
      }

   }

   protected void doWriteReset(long code) {
      int streamId;
      synchronized(this) {
         streamId = this.stream != null ? this.stream.id() : -1;
         this.reset = code;
      }

      if (streamId != -1) {
         this.conn.handler.writeReset(streamId, code);
      } else {
         this.handleReset(code);
      }

   }

   void handleWritabilityChanged(boolean writable) {
   }

   void handleData(Buffer buf) {
   }

   void handleCustomFrame(HttpFrame frame) {
   }

   void handleEnd(MultiMap trailers) {
   }

   void handleReset(long errorCode) {
   }

   void handleException(Throwable cause) {
   }

   void handleClose() {
   }

   synchronized void priority(StreamPriority streamPriority) {
      this.priority = streamPriority;
   }

   synchronized StreamPriority priority() {
      return this.priority;
   }

   synchronized void updatePriority(StreamPriority priority) {
      if (!this.priority.equals(priority)) {
         this.priority = priority;
         if (this.stream != null) {
            this.writePriorityFrame(priority);
         }
      }

   }

   void handlePriorityChange(StreamPriority newPriority) {
   }

   static {
      EMPTY = new Http2HeadersAdaptor(EmptyHttp2Headers.INSTANCE);
   }
}
