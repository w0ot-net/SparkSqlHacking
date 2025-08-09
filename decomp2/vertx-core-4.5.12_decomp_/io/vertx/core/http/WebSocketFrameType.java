package io.vertx.core.http;

import io.vertx.codegen.annotations.VertxGen;

@VertxGen
public enum WebSocketFrameType {
   CONTINUATION,
   TEXT,
   BINARY,
   CLOSE,
   PING,
   PONG;
}
