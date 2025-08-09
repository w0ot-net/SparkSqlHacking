package io.vertx.ext.auth.authorization;

import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.ext.auth.User;

@VertxGen
public interface Authorization {
   boolean match(AuthorizationContext var1);

   @GenIgnore({"permitted-type"})
   default boolean match(User user) {
      return this.match(AuthorizationContext.create(user));
   }

   boolean verify(Authorization var1);
}
