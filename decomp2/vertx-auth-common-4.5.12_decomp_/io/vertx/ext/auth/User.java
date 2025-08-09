package io.vertx.ext.auth;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.Nullable;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.authorization.Authorization;
import io.vertx.ext.auth.authorization.Authorizations;
import io.vertx.ext.auth.authorization.RoleBasedAuthorization;
import io.vertx.ext.auth.authorization.WildcardPermissionBasedAuthorization;
import io.vertx.ext.auth.authorization.impl.AuthorizationsImpl;
import io.vertx.ext.auth.impl.UserImpl;

@VertxGen
public interface User {
   static User fromName(String username) {
      return create((new JsonObject()).put("username", username));
   }

   static User fromToken(String token) {
      return create((new JsonObject()).put("access_token", token));
   }

   static User create(JsonObject principal) {
      return create(principal, new JsonObject());
   }

   static User create(JsonObject principal, JsonObject attributes) {
      return new UserImpl(principal, attributes);
   }

   default @Nullable String subject() {
      if (this.principal().containsKey("username")) {
         return this.principal().getString("username");
      } else if (this.principal().containsKey("userHandle")) {
         return this.principal().getString("userHandle");
      } else {
         if (this.attributes().containsKey("idToken")) {
            JsonObject idToken = this.attributes().getJsonObject("idToken");
            if (idToken.containsKey("sub")) {
               return idToken.getString("sub");
            }
         }

         return (String)this.get("sub");
      }
   }

   JsonObject attributes();

   default boolean expired() {
      return this.expired(this.attributes().getInteger("leeway", 0));
   }

   default boolean expired(int leeway) {
      long now = System.currentTimeMillis() / 1000L;
      if (this.containsKey("exp") && now - (long)leeway >= this.attributes().getLong("exp", this.principal().getLong("exp", 0L))) {
         return true;
      } else {
         if (this.containsKey("iat")) {
            Long iat = this.attributes().getLong("iat", this.principal().getLong("iat", 0L));
            if (iat > now + (long)leeway) {
               return true;
            }
         }

         if (this.containsKey("nbf")) {
            Long nbf = this.attributes().getLong("nbf", this.principal().getLong("nbf", 0L));
            if (nbf > now + (long)leeway) {
               return true;
            }
         }

         return false;
      }
   }

   default @Nullable Object get(String key) {
      if (this.attributes().containsKey("rootClaim")) {
         JsonObject rootClaim;
         try {
            rootClaim = this.attributes().getJsonObject(this.attributes().getString("rootClaim"));
         } catch (ClassCastException var4) {
            rootClaim = null;
         }

         if (rootClaim != null && rootClaim.containsKey(key)) {
            return rootClaim.getValue(key);
         }
      }

      if (this.attributes().containsKey(key)) {
         return this.attributes().getValue(key);
      } else {
         return this.principal().containsKey(key) ? this.principal().getValue(key) : null;
      }
   }

   default @Nullable Object getOrDefault(String key, Object defaultValue) {
      if (this.attributes().containsKey("rootClaim")) {
         JsonObject rootClaim;
         try {
            rootClaim = this.attributes().getJsonObject(this.attributes().getString("rootClaim"));
         } catch (ClassCastException var5) {
            rootClaim = null;
         }

         if (rootClaim != null && rootClaim.containsKey(key)) {
            return rootClaim.getValue(key);
         }
      }

      if (this.attributes().containsKey(key)) {
         return this.attributes().getValue(key);
      } else {
         return this.principal().containsKey(key) ? this.principal().getValue(key) : defaultValue;
      }
   }

   default boolean containsKey(String key) {
      if (this.attributes().containsKey("rootClaim")) {
         JsonObject rootClaim;
         try {
            rootClaim = this.attributes().getJsonObject(this.attributes().getString("rootClaim"));
         } catch (ClassCastException var4) {
            rootClaim = null;
         }

         if (rootClaim != null && rootClaim.containsKey(key)) {
            return true;
         }
      }

      return this.attributes().containsKey(key) || this.principal().containsKey(key);
   }

   default Authorizations authorizations() {
      return new AuthorizationsImpl();
   }

   /** @deprecated */
   @Fluent
   @Deprecated
   User isAuthorized(Authorization var1, Handler var2);

   /** @deprecated */
   @Fluent
   @Deprecated
   default User isAuthorized(String authority, Handler resultHandler) {
      return this.isAuthorized((Authorization)(authority.startsWith("role:") ? RoleBasedAuthorization.create(authority.substring(5)) : WildcardPermissionBasedAuthorization.create(authority)), resultHandler);
   }

   /** @deprecated */
   @Deprecated
   default Future isAuthorized(Authorization authority) {
      Promise<Boolean> promise = Promise.promise();
      this.isAuthorized((Authorization)authority, promise);
      return promise.future();
   }

   /** @deprecated */
   @Deprecated
   default Future isAuthorized(String authority) {
      return this.isAuthorized((Authorization)(authority.startsWith("role:") ? RoleBasedAuthorization.create(authority.substring(5)) : WildcardPermissionBasedAuthorization.create(authority)));
   }

   /** @deprecated */
   @Fluent
   @Deprecated
   default User clearCache() {
      this.authorizations().clear();
      return this;
   }

   JsonObject principal();

   /** @deprecated */
   @Deprecated
   void setAuthProvider(AuthProvider var1);

   @Fluent
   User merge(User var1);

   default boolean hasAmr(String value) {
      return this.principal().containsKey("amr") ? this.principal().getJsonArray("amr").contains(value) : false;
   }
}
