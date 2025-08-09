package io.vertx.core.http.impl;

import io.vertx.core.Context;
import io.vertx.core.http.HttpServerRequest;

public abstract class HttpServerRequestInternal implements HttpServerRequest {
   public abstract Context context();

   public abstract Object metric();

   public boolean isValidAuthority() {
      return this.authority() != null;
   }
}
