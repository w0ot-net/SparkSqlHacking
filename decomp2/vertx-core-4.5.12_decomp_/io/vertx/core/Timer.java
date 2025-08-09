package io.vertx.core;

import io.vertx.codegen.annotations.VertxGen;

@VertxGen
public interface Timer extends Future {
   boolean cancel();
}
