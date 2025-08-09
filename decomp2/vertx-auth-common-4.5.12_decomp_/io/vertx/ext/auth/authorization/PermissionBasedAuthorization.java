package io.vertx.ext.auth.authorization;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.ext.auth.authorization.impl.PermissionBasedAuthorizationImpl;

@VertxGen
public interface PermissionBasedAuthorization extends Authorization {
   static PermissionBasedAuthorization create(String permission) {
      return new PermissionBasedAuthorizationImpl(permission);
   }

   String getPermission();

   String getResource();

   @Fluent
   PermissionBasedAuthorization setResource(String var1);
}
