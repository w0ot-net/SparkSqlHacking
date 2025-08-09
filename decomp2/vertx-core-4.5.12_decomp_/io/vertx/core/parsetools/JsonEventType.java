package io.vertx.core.parsetools;

import io.vertx.codegen.annotations.VertxGen;

@VertxGen
public enum JsonEventType {
   START_OBJECT,
   END_OBJECT,
   START_ARRAY,
   END_ARRAY,
   VALUE;
}
