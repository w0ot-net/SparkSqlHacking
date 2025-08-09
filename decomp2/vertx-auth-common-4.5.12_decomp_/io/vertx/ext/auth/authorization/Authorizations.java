package io.vertx.ext.auth.authorization;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.VertxGen;
import java.util.Set;

@VertxGen
public interface Authorizations {
   @Fluent
   Authorizations add(String var1, Set var2);

   @Fluent
   Authorizations add(String var1, Authorization var2);

   @Fluent
   Authorizations clear(String var1);

   @Fluent
   Authorizations clear();

   Set get(String var1);

   Set getProviderIds();
}
