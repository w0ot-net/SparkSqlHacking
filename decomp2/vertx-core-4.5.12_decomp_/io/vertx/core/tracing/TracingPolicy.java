package io.vertx.core.tracing;

import io.vertx.codegen.annotations.VertxGen;

@VertxGen
public enum TracingPolicy {
   IGNORE,
   PROPAGATE,
   ALWAYS;
}
