package io.vertx.core.http.impl;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpVersion;
import io.vertx.core.http.StreamPriority;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.impl.future.PromiseInternal;
import io.vertx.core.streams.WriteStream;

public interface HttpClientStream extends WriteStream {
   int id();

   Object metric();

   Object trace();

   HttpVersion version();

   HttpClientConnection connection();

   ContextInternal getContext();

   void writeHead(HttpRequestHead var1, boolean var2, ByteBuf var3, boolean var4, StreamPriority var5, boolean var6, Handler var7);

   void writeBuffer(ByteBuf var1, boolean var2, Handler var3);

   void writeFrame(int var1, int var2, ByteBuf var3);

   void continueHandler(Handler var1);

   void earlyHintsHandler(Handler var1);

   void pushHandler(Handler var1);

   void unknownFrameHandler(Handler var1);

   default Future write(Buffer data) {
      PromiseInternal<Void> promise = this.getContext().promise();
      this.writeBuffer(data.getByteBuf(), false, promise);
      return promise.future();
   }

   default void write(Buffer data, Handler handler) {
      this.writeBuffer(data.getByteBuf(), false, handler);
   }

   default Future end(Buffer data) {
      PromiseInternal<Void> promise = this.getContext().promise();
      this.writeBuffer(data.getByteBuf(), true, promise);
      return promise.future();
   }

   default void end(Buffer data, Handler handler) {
      this.writeBuffer(data.getByteBuf(), true, handler);
   }

   default Future end() {
      PromiseInternal<Void> promise = this.getContext().promise();
      this.writeBuffer(Unpooled.EMPTY_BUFFER, true, promise);
      return promise.future();
   }

   default void end(Handler handler) {
      this.writeBuffer(Unpooled.EMPTY_BUFFER, true, handler);
   }

   void headHandler(Handler var1);

   void chunkHandler(Handler var1);

   void endHandler(Handler var1);

   void priorityHandler(Handler var1);

   void closeHandler(Handler var1);

   void doSetWriteQueueMaxSize(int var1);

   boolean isNotWritable();

   void doPause();

   void doFetch(long var1);

   void reset(Throwable var1);

   StreamPriority priority();

   void updatePriority(StreamPriority var1);
}
