package io.vertx.core.file;

import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.Future;
import io.vertx.core.Handler;

@VertxGen
public interface AsyncFileLock {
   long position();

   long size();

   boolean isShared();

   boolean overlaps(long var1, long var3);

   boolean isValidBlocking();

   Future isValid();

   void isValid(Handler var1);

   void releaseBlocking();

   Future release();

   void release(Handler var1);
}
