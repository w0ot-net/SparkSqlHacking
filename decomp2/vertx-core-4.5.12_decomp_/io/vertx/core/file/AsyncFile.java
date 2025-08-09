package io.vertx.core.file;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.Nullable;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.streams.ReadStream;
import io.vertx.core.streams.WriteStream;

@VertxGen
public interface AsyncFile extends ReadStream, WriteStream {
   AsyncFile handler(Handler var1);

   AsyncFile pause();

   AsyncFile resume();

   AsyncFile endHandler(Handler var1);

   AsyncFile setWriteQueueMaxSize(int var1);

   AsyncFile drainHandler(Handler var1);

   AsyncFile exceptionHandler(Handler var1);

   AsyncFile fetch(long var1);

   Future close();

   void close(Handler var1);

   void write(Buffer var1, long var2, Handler var4);

   Future write(Buffer var1, long var2);

   @Fluent
   AsyncFile read(Buffer var1, int var2, long var3, int var5, Handler var6);

   Future read(Buffer var1, int var2, long var3, int var5);

   Future flush();

   @Fluent
   AsyncFile flush(Handler var1);

   @Fluent
   AsyncFile setReadPos(long var1);

   @Fluent
   AsyncFile setReadLength(long var1);

   long getReadLength();

   @Fluent
   AsyncFile setWritePos(long var1);

   long getWritePos();

   @Fluent
   AsyncFile setReadBufferSize(int var1);

   long sizeBlocking();

   default void size(Handler handler) {
      Future<Long> future = this.size();
      if (handler != null) {
         future.onComplete(handler);
      }

   }

   Future size();

   @Nullable AsyncFileLock tryLock();

   @Nullable AsyncFileLock tryLock(long var1, long var3, boolean var5);

   Future lock();

   void lock(Handler var1);

   Future lock(long var1, long var3, boolean var5);

   void lock(long var1, long var3, boolean var5, Handler var6);
}
