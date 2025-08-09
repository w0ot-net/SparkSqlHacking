package io.vertx.ext.auth.authorization;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.ext.auth.authorization.impl.WildcardPermissionBasedAuthorizationImpl;

@VertxGen
public interface WildcardPermissionBasedAuthorization extends Authorization {
   static WildcardPermissionBasedAuthorization create(String permission) {
      return new WildcardPermissionBasedAuthorizationImpl(permission);
   }

   String getPermission();

   String getResource();

   @Fluent
   WildcardPermissionBasedAuthorization setResource(String var1);
}
