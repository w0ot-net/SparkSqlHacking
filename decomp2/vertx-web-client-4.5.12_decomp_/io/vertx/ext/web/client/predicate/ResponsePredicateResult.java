package io.vertx.ext.web.client.predicate;

import io.vertx.codegen.annotations.Nullable;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.impl.predicate.ResponsePredicateResultImpl;

/** @deprecated */
@Deprecated
@VertxGen
public interface ResponsePredicateResult {
   static ResponsePredicateResult success() {
      return ResponsePredicateResultImpl.SUCCESS;
   }

   static ResponsePredicateResult failure(String message) {
      return new ResponsePredicateResultImpl(false, message);
   }

   boolean succeeded();

   @Nullable String message();

   @Nullable HttpResponse response();
}
