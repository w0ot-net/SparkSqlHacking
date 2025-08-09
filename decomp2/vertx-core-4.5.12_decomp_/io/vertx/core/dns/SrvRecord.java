package io.vertx.core.dns;

import io.vertx.codegen.annotations.Nullable;
import io.vertx.codegen.annotations.VertxGen;

@VertxGen
public interface SrvRecord {
   int priority();

   int weight();

   int port();

   String name();

   String protocol();

   String service();

   @Nullable String target();
}
