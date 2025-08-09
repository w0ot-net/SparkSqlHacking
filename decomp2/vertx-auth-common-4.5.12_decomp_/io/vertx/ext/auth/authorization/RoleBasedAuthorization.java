package io.vertx.ext.auth.authorization;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.ext.auth.authorization.impl.RoleBasedAuthorizationImpl;

@VertxGen
public interface RoleBasedAuthorization extends Authorization {
   static RoleBasedAuthorization create(String role) {
      return new RoleBasedAuthorizationImpl(role);
   }

   String getRole();

   String getResource();

   @Fluent
   RoleBasedAuthorization setResource(String var1);
}
