package io.vertx.ext.web.client.impl.predicate;

import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.predicate.ResponsePredicateResult;

public class ResponsePredicateResultImpl implements ResponsePredicateResult {
   public static final ResponsePredicateResultImpl SUCCESS = new ResponsePredicateResultImpl(true, (String)null);
   private final boolean passed;
   private final String message;
   private HttpResponse httpResponse;

   public ResponsePredicateResultImpl(boolean passed, String message) {
      this.passed = passed;
      this.message = message;
   }

   public boolean succeeded() {
      return this.passed;
   }

   public String message() {
      return this.message;
   }

   public HttpResponse response() {
      return this.httpResponse;
   }

   public ResponsePredicateResultImpl setHttpResponse(HttpResponse httpResponse) {
      this.httpResponse = httpResponse;
      return this;
   }
}
