package io.vertx.ext.auth.authorization.impl;

import io.vertx.ext.auth.authorization.Authorization;
import io.vertx.ext.auth.authorization.AuthorizationContext;
import io.vertx.ext.auth.authorization.OrAuthorization;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrAuthorizationImpl implements OrAuthorization {
   private final List authorizations = new ArrayList();

   public OrAuthorization addAuthorization(Authorization authorization) {
      this.authorizations.add(Objects.requireNonNull(authorization));
      return this;
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (!(obj instanceof OrAuthorizationImpl)) {
         return false;
      } else {
         OrAuthorizationImpl other = (OrAuthorizationImpl)obj;
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
         if (authorization.match(context)) {
            return true;
         }
      }

      return false;
   }

   public boolean verify(Authorization otherAuthorization) {
      Objects.requireNonNull(otherAuthorization);
      if (otherAuthorization instanceof OrAuthorization) {
         return this.equals(otherAuthorization);
      } else {
         return this.authorizations.size() == 1 ? ((Authorization)this.authorizations.get(0)).verify(otherAuthorization) : false;
      }
   }
}
