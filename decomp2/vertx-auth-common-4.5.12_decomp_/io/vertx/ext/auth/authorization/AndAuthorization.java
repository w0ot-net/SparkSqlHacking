package io.vertx.ext.auth.authorization;

import io.vertx.codegen.annotations.VertxGen;
import io.vertx.ext.auth.authorization.impl.AndAuthorizationImpl;
import java.util.List;

@VertxGen
public interface AndAuthorization extends Authorization {
   static AndAuthorization create() {
      return new AndAuthorizationImpl();
   }

   List getAuthorizations();

   AndAuthorization addAuthorization(Authorization var1);
}
