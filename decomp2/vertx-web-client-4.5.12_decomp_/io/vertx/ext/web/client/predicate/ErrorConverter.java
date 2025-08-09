package io.vertx.ext.web.client.predicate;

import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.impl.NoStackTraceThrowable;
import io.vertx.ext.web.client.impl.predicate.ErrorConverterImpl;
import java.util.function.Function;

/** @deprecated */
@Deprecated
@FunctionalInterface
@VertxGen
public interface ErrorConverter {
   ErrorConverter DEFAULT_CONVERTER = (result) -> {
      String message = result.message();
      return message == null ? null : new NoStackTraceThrowable(message);
   };

   static ErrorConverter create(Function converter) {
      return converter::apply;
   }

   static ErrorConverter createFullBody(Function converter) {
      return new ErrorConverterImpl(converter, true);
   }

   Throwable apply(ResponsePredicateResult var1);

   default boolean requiresBody() {
      return false;
   }
}
