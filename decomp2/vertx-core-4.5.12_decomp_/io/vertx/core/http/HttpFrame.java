package io.vertx.core.http;

import io.vertx.codegen.annotations.CacheReturn;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.buffer.Buffer;

@VertxGen
public interface HttpFrame {
   @CacheReturn
   int type();

   @CacheReturn
   int flags();

   @CacheReturn
   Buffer payload();
}
