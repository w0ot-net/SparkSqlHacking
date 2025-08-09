package io.vertx.ext.auth;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.ext.auth.authentication.AuthenticationProvider;
import io.vertx.ext.auth.impl.ChainAuthImpl;

@VertxGen
public interface ChainAuth extends AuthenticationProvider {
   /** @deprecated */
   @Deprecated
   static ChainAuth create() {
      return any();
   }

   static ChainAuth all() {
      return new ChainAuthImpl(true);
   }

   static ChainAuth any() {
      return new ChainAuthImpl(false);
   }

   @Fluent
   ChainAuth add(AuthenticationProvider var1);
}
