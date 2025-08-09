package io.vertx.ext.auth.authorization.impl;

import io.vertx.ext.auth.authorization.Authorization;
import io.vertx.ext.auth.authorization.AuthorizationContext;
import io.vertx.ext.auth.authorization.NotAuthorization;
import java.util.Objects;

public class NotAuthorizationImpl implements NotAuthorization {
   private Authorization authorization;

   public NotAuthorizationImpl() {
   }

   public NotAuthorizationImpl(Authorization authorization) {
      this.authorization = (Authorization)Objects.requireNonNull(authorization);
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (!(obj instanceof NotAuthorizationImpl)) {
         return false;
      } else {
         NotAuthorizationImpl other = (NotAuthorizationImpl)obj;
         return Objects.equals(this.authorization, other.authorization);
      }
   }

   public Authorization getAuthorization() {
      return this.authorization;
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.authorization});
   }

   public boolean match(AuthorizationContext context) {
      Objects.requireNonNull(context);
      return !this.authorization.match(context);
   }

   public boolean verify(Authorization authorization) {
      return this.equals(authorization);
   }
}
