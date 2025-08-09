package io.vertx.ext.web.client.impl.predicate;

import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.predicate.ErrorConverter;
import io.vertx.ext.web.client.predicate.ResponsePredicate;
import io.vertx.ext.web.client.predicate.ResponsePredicateResult;
import java.util.function.Function;

public class ResponsePredicateImpl implements ResponsePredicate {
   private final Function predicate;
   private final ErrorConverter errorConverter;

   public ResponsePredicateImpl(Function predicate, ErrorConverter errorConverter) {
      this.predicate = predicate;
      this.errorConverter = errorConverter;
   }

   public ResponsePredicateResult apply(HttpResponse response) {
      return (ResponsePredicateResult)this.predicate.apply(response);
   }

   public ErrorConverter errorConverter() {
      return this.errorConverter;
   }
}
