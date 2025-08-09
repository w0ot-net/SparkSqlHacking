package io.vertx.core;

import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.streams.ReadStream;

/** @deprecated */
@Deprecated
@VertxGen
public interface TimeoutStream extends ReadStream {
   TimeoutStream exceptionHandler(Handler var1);

   TimeoutStream handler(Handler var1);

   TimeoutStream pause();

   TimeoutStream resume();

   TimeoutStream fetch(long var1);

   TimeoutStream endHandler(Handler var1);

   void cancel();
}
