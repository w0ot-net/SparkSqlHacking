package io.vertx.ext.auth.authorization.impl;

import io.vertx.ext.auth.User;
import io.vertx.ext.auth.authorization.Authorization;
import io.vertx.ext.auth.authorization.AuthorizationContext;
import io.vertx.ext.auth.authorization.Authorizations;
import io.vertx.ext.auth.authorization.PermissionBasedAuthorization;
import io.vertx.ext.auth.authorization.WildcardPermissionBasedAuthorization;
import java.util.Objects;

public class PermissionBasedAuthorizationImpl implements PermissionBasedAuthorization {
   private final String permission;
   private VariableAwareExpression resource;

   public PermissionBasedAuthorizationImpl(String permission) {
      this.permission = (String)Objects.requireNonNull(permission);
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (!(obj instanceof PermissionBasedAuthorizationImpl)) {
         return false;
      } else {
         PermissionBasedAuthorizationImpl other = (PermissionBasedAuthorizationImpl)obj;
         return Objects.equals(this.permission, other.permission) && Objects.equals(this.resource, other.resource);
      }
   }

   public String getPermission() {
      return this.permission;
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.permission, this.resource});
   }

   public boolean match(AuthorizationContext context) {
      Objects.requireNonNull(context);
      User user = context.user();
      if (user != null) {
         Authorization resolvedAuthorization = this.getResolvedAuthorization(context);
         Authorizations authorizations = user.authorizations();

         for(String providerId : authorizations.getProviderIds()) {
            for(Authorization authorization : authorizations.get(providerId)) {
               if (authorization.verify(resolvedAuthorization)) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   private PermissionBasedAuthorization getResolvedAuthorization(AuthorizationContext context) {
      return (PermissionBasedAuthorization)(this.resource != null && this.resource.hasVariable() ? PermissionBasedAuthorization.create(this.permission).setResource(this.resource.resolve(context)) : this);
   }

   public boolean verify(Authorization otherAuthorization) {
      Objects.requireNonNull(otherAuthorization);
      if (otherAuthorization instanceof PermissionBasedAuthorization) {
         PermissionBasedAuthorization otherPermissionBasedAuthorization = (PermissionBasedAuthorization)otherAuthorization;
         if (this.permission.equals(otherPermissionBasedAuthorization.getPermission())) {
            if (this.getResource() == null) {
               return otherPermissionBasedAuthorization.getResource() == null;
            }

            return this.getResource().equals(otherPermissionBasedAuthorization.getResource());
         }
      } else if (otherAuthorization instanceof WildcardPermissionBasedAuthorization) {
         WildcardPermissionBasedAuthorization otherWildcardPermissionBasedAuthorization = (WildcardPermissionBasedAuthorization)otherAuthorization;
         if (this.permission.equals(otherWildcardPermissionBasedAuthorization.getPermission())) {
            if (this.getResource() == null) {
               return otherWildcardPermissionBasedAuthorization.getResource() == null;
            }

            return this.getResource().equals(otherWildcardPermissionBasedAuthorization.getResource());
         }
      }

      return false;
   }

   public String getResource() {
      return this.resource != null ? this.resource.getValue() : null;
   }

   public PermissionBasedAuthorization setResource(String resource) {
      Objects.requireNonNull(resource);
      this.resource = new VariableAwareExpression(resource);
      return this;
   }
}
