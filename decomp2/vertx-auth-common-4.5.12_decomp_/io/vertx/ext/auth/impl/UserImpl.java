package io.vertx.ext.auth.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.shareddata.ClusterSerializable;
import io.vertx.ext.auth.AuthProvider;
import io.vertx.ext.auth.User;
import io.vertx.ext.auth.authorization.Authorization;
import io.vertx.ext.auth.authorization.AuthorizationContext;
import io.vertx.ext.auth.authorization.Authorizations;
import io.vertx.ext.auth.authorization.impl.AuthorizationContextImpl;
import io.vertx.ext.auth.authorization.impl.AuthorizationsImpl;
import java.util.Collections;
import java.util.Objects;

public class UserImpl implements User, ClusterSerializable {
   private Authorizations authorizations;
   private JsonObject attributes;
   private JsonObject principal;

   public UserImpl() {
   }

   public UserImpl(JsonObject principal, JsonObject attributes) {
      this.principal = (JsonObject)Objects.requireNonNull(principal);
      this.attributes = attributes;
      this.authorizations = new AuthorizationsImpl();
   }

   public Authorizations authorizations() {
      return this.authorizations;
   }

   public JsonObject attributes() {
      return this.attributes;
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj == null) {
         return false;
      } else if (this.getClass() != obj.getClass()) {
         return false;
      } else {
         UserImpl other = (UserImpl)obj;
         return Objects.equals(this.authorizations, other.authorizations) && Objects.equals(this.principal, other.principal) && Objects.equals(this.attributes, other.attributes);
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.authorizations, this.principal, this.attributes});
   }

   public User isAuthorized(Authorization authorization, Handler resultHandler) {
      Objects.requireNonNull(authorization);
      Objects.requireNonNull(resultHandler);
      AuthorizationContext context = new AuthorizationContextImpl(this);
      resultHandler.handle(Future.succeededFuture(authorization.match(context)));
      return this;
   }

   public JsonObject principal() {
      return this.principal;
   }

   public void setAuthProvider(AuthProvider authProvider) {
   }

   public User merge(User other) {
      if (other == null) {
         return this;
      } else {
         JsonArray amr = this.principal().getJsonArray("amr");
         JsonArray otherAmr = other.principal().getJsonArray("amr");
         this.principal().mergeIn(other.principal());
         if (amr == null) {
            if (otherAmr != null) {
               amr = otherAmr.copy();
            }
         } else if (otherAmr != null) {
            amr = amr.copy();

            for(Object el : otherAmr) {
               if (!amr.contains(el)) {
                  amr.add(el);
               }
            }
         }

         if (amr == null) {
            this.principal.put("amr", Collections.singletonList("mfa"));
         } else {
            amr = amr.copy();
            if (!amr.contains("mfa")) {
               amr.add("mfa");
            }

            this.principal.put("amr", amr);
         }

         JsonObject attrs = this.attributes();
         JsonObject otherAttrs = other.attributes();
         if (attrs == null) {
            if (otherAttrs != null) {
               this.attributes = otherAttrs.copy();
            }
         } else if (otherAttrs != null) {
            for(String key : otherAttrs.fieldNames()) {
               Object lhsValue = attrs.getValue(key);
               Object rhsValue = otherAttrs.getValue(key);
               if (lhsValue == null) {
                  attrs.put(key, rhsValue instanceof JsonArray ? (new JsonArray()).add(rhsValue) : rhsValue);
               } else if (lhsValue instanceof JsonArray) {
                  if (rhsValue instanceof JsonArray) {
                     ((JsonArray)lhsValue).addAll((JsonArray)rhsValue);
                  } else {
                     ((JsonArray)lhsValue).add(rhsValue);
                  }
               } else if (rhsValue instanceof JsonArray) {
                  attrs.put(key, (new JsonArray()).add(lhsValue).addAll((JsonArray)rhsValue));
               } else {
                  attrs.put(key, (new JsonArray()).add(lhsValue).add(rhsValue));
               }
            }
         }

         return this;
      }
   }

   public void writeToBuffer(Buffer buffer) {
      UserConverter.encode(this).writeToBuffer(buffer);
   }

   public int readFromBuffer(int pos, Buffer buffer) {
      JsonObject jsonObject = new JsonObject();
      int read = jsonObject.readFromBuffer(pos, buffer);
      User readUser = UserConverter.decode(jsonObject);
      this.principal = readUser.principal();
      this.authorizations = readUser.authorizations();
      this.attributes = readUser.attributes();
      return read;
   }
}
