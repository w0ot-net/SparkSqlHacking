package io.vertx.ext.auth.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.ChainAuth;
import io.vertx.ext.auth.User;
import io.vertx.ext.auth.authentication.AuthenticationProvider;
import io.vertx.ext.auth.authentication.CredentialValidationException;
import io.vertx.ext.auth.authentication.Credentials;
import java.util.ArrayList;
import java.util.List;

public class ChainAuthImpl implements ChainAuth {
   private final List providers = new ArrayList();
   private final boolean all;

   public ChainAuthImpl(boolean all) {
      this.all = all;
   }

   public ChainAuth add(AuthenticationProvider other) {
      this.providers.add(other);
      return this;
   }

   public void authenticate(JsonObject credentials, Handler resultHandler) {
      this.authenticate(credentials).onComplete(resultHandler);
   }

   public Future authenticate(Credentials credentials) {
      try {
         credentials.checkValid((Object)null);
      } catch (CredentialValidationException e) {
         return Future.failedFuture(e);
      }

      return this.authenticate(credentials.toJson());
   }

   public Future authenticate(JsonObject authInfo) {
      return this.providers.size() == 0 ? Future.failedFuture("No providers in the auth chain.") : this.iterate(0, authInfo, (User)null);
   }

   private Future iterate(int idx, JsonObject authInfo, User previousUser) {
      if (idx >= this.providers.size()) {
         return !this.all ? Future.failedFuture("No more providers in the auth chain.") : Future.succeededFuture(previousUser);
      } else {
         return ((AuthenticationProvider)this.providers.get(idx)).authenticate(authInfo).compose((user) -> !this.all ? Future.succeededFuture(user) : this.iterate(idx + 1, authInfo, previousUser == null ? user : previousUser.merge(user))).recover((err) -> !this.all ? this.iterate(idx + 1, authInfo, (User)null) : Future.failedFuture(err));
      }
   }
}
