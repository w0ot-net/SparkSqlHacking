package io.vertx.ext.auth.authorization;

import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.MultiMap;
import io.vertx.ext.auth.User;
import io.vertx.ext.auth.authorization.impl.AuthorizationContextImpl;

@VertxGen
public interface AuthorizationContext {
   static AuthorizationContext create(User user) {
      return new AuthorizationContextImpl(user);
   }

   User user();

   MultiMap variables();
}
