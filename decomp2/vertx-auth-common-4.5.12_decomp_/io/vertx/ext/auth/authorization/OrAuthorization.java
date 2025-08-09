package io.vertx.ext.auth.authorization;

import io.vertx.codegen.annotations.VertxGen;
import io.vertx.ext.auth.authorization.impl.OrAuthorizationImpl;
import java.util.List;

@VertxGen
public interface OrAuthorization extends Authorization {
   static OrAuthorization create() {
      return new OrAuthorizationImpl();
   }

   List getAuthorizations();

   OrAuthorization addAuthorization(Authorization var1);
}
