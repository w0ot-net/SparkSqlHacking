package io.vertx.ext.auth.authorization;

import io.vertx.codegen.annotations.VertxGen;
import io.vertx.ext.auth.authorization.impl.NotAuthorizationImpl;

@VertxGen
public interface NotAuthorization extends Authorization {
   static NotAuthorization create(Authorization authorization) {
      return new NotAuthorizationImpl(authorization);
   }

   Authorization getAuthorization();
}
