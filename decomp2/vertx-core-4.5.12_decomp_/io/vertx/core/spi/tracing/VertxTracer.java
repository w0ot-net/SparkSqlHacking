package io.vertx.core.spi.tracing;

import io.vertx.core.Context;
import io.vertx.core.tracing.TracingPolicy;
import java.util.Map;
import java.util.function.BiConsumer;

public interface VertxTracer {
   VertxTracer NOOP = new VertxTracer() {
   };

   default Object receiveRequest(Context context, SpanKind kind, TracingPolicy policy, Object request, String operation, Iterable headers, TagExtractor tagExtractor) {
      return null;
   }

   default void sendResponse(Context context, Object response, Object payload, Throwable failure, TagExtractor tagExtractor) {
   }

   default Object sendRequest(Context context, SpanKind kind, TracingPolicy policy, Object request, String operation, BiConsumer headers, TagExtractor tagExtractor) {
      return null;
   }

   default void receiveResponse(Context context, Object response, Object payload, Throwable failure, TagExtractor tagExtractor) {
   }

   default void close() {
   }
}
