package io.vertx.ext.auth.authorization.impl;

import io.vertx.ext.auth.authorization.AndAuthorization;
import io.vertx.ext.auth.authorization.Authorization;
import io.vertx.ext.auth.authorization.AuthorizationContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AndAuthorizationImpl implements AndAuthorization {
   private final List authorizations = new ArrayList();

   public AndAuthorization addAuthorization(Authorization authorization) {
      this.authorizations.add(Objects.requireNonNull(authorization));
      return this;
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (!(obj instanceof AndAuthorizationImpl)) {
         return false;
      } else {
         AndAuthorizationImpl other = (AndAuthorizationImpl)obj;
         return Objects.equals(this.authorizations, other.authorizations);
      }
   }

   public List getAuthorizations() {
      return this.authorizations;
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.authorizations});
   }

   public boolean match(AuthorizationContext context) {
      Objects.requireNonNull(context);

      for(Authorization authorization : this.authorizations) {
         if (!authorization.match(context)) {
            return false;
         }
      }

      return true;
   }

   public boolean verify(Authorization otherAuthorization) {
      Objects.requireNonNull(otherAuthorization);
      boolean match = false;
      if (otherAuthorization instanceof AndAuthorization) {
         for(Authorization otherAndAuthorization : ((AndAuthorization)otherAuthorization).getAuthorizations()) {
            for(Authorization authorization : this.authorizations) {
               if (authorization.verify(otherAndAuthorization)) {
                  match = true;
                  break;
               }
            }
         }

         return match;
      } else {
         for(Authorization authorization : this.authorizations) {
            if (authorization.verify(otherAuthorization)) {
               match = true;
               break;
            }
         }

         return match;
      }
   }
}
