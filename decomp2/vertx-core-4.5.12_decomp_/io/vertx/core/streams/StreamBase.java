package io.vertx.core.streams;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.Nullable;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.Handler;

@VertxGen(
   concrete = false
)
public interface StreamBase {
   @Fluent
   StreamBase exceptionHandler(@Nullable Handler var1);
}
