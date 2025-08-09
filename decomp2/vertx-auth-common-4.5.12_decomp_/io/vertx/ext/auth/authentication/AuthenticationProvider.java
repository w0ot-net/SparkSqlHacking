package io.vertx.ext.auth.authentication;

import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.User;

@VertxGen
public interface AuthenticationProvider {
   /** @deprecated */
   @Deprecated
   void authenticate(JsonObject var1, Handler var2);

   /** @deprecated */
   @Deprecated
   default Future authenticate(JsonObject credentials) {
      Promise<User> promise = Promise.promise();
      this.authenticate((JsonObject)credentials, promise);
      return promise.future();
   }

   @GenIgnore({"permitted-type"})
   default void authenticate(Credentials credentials, Handler resultHandler) {
      this.authenticate(credentials).onComplete(resultHandler);
   }

   @GenIgnore({"permitted-type"})
   default Future authenticate(Credentials credentials) {
      return this.authenticate(credentials.toJson());
   }
}
