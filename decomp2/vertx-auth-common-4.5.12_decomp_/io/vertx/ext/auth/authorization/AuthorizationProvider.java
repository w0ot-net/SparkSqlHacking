package io.vertx.ext.auth.authorization;

import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.ext.auth.User;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@VertxGen
public interface AuthorizationProvider {
   static AuthorizationProvider create(final String id, Set authorizations) {
      final Set<Authorization> _authorizations = new HashSet((Collection)Objects.requireNonNull(authorizations));
      return new AuthorizationProvider() {
         public String getId() {
            return id;
         }

         public void getAuthorizations(User user, Handler handler) {
            this.getAuthorizations(user).onComplete(handler);
         }

         public Future getAuthorizations(User user) {
            user.authorizations().add(this.getId(), _authorizations);
            return Future.succeededFuture();
         }
      };
   }

   String getId();

   void getAuthorizations(User var1, Handler var2);

   default Future getAuthorizations(User user) {
      Promise<Void> promise = Promise.promise();
      this.getAuthorizations(user, promise);
      return promise.future();
   }
}
