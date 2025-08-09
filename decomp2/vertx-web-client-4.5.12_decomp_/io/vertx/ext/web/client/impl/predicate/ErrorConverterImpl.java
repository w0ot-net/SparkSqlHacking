package io.vertx.ext.web.client.impl.predicate;

import io.vertx.ext.web.client.predicate.ErrorConverter;
import io.vertx.ext.web.client.predicate.ResponsePredicateResult;
import java.util.function.Function;

public class ErrorConverterImpl implements ErrorConverter {
   private final Function converter;
   private final boolean needsBody;

   public ErrorConverterImpl(Function converter, boolean needsBody) {
      this.converter = converter;
      this.needsBody = needsBody;
   }

   public boolean requiresBody() {
      return this.needsBody;
   }

   public Throwable apply(ResponsePredicateResult result) {
      return (Throwable)this.converter.apply(result);
   }
}
