package io.vertx.ext.auth.authorization.impl;

import io.vertx.ext.auth.User;
import io.vertx.ext.auth.authorization.Authorization;
import io.vertx.ext.auth.authorization.AuthorizationContext;
import io.vertx.ext.auth.authorization.PermissionBasedAuthorization;
import io.vertx.ext.auth.authorization.WildcardPermissionBasedAuthorization;
import java.util.Objects;

public class WildcardPermissionBasedAuthorizationImpl implements WildcardPermissionBasedAuthorization {
   private final String permission;
   private VariableAwareExpression resource;
   private final WildcardExpression wildcardPermission;

   public WildcardPermissionBasedAuthorizationImpl(String permission) {
      this.permission = (String)Objects.requireNonNull(permission);
      this.wildcardPermission = new WildcardExpression(permission);
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (!(obj instanceof WildcardPermissionBasedAuthorizationImpl)) {
         return false;
      } else {
         WildcardPermissionBasedAuthorizationImpl other = (WildcardPermissionBasedAuthorizationImpl)obj;
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

         for(String providerId : user.authorizations().getProviderIds()) {
            for(Authorization authorization : user.authorizations().get(providerId)) {
               if (authorization.verify(resolvedAuthorization)) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   public boolean verify(Authorization otherAuthorization) {
      if (otherAuthorization instanceof WildcardPermissionBasedAuthorizationImpl) {
         WildcardPermissionBasedAuthorizationImpl otherWildcardPermission = (WildcardPermissionBasedAuthorizationImpl)otherAuthorization;
         if (this.wildcardPermission.implies(otherWildcardPermission.wildcardPermission)) {
            if (this.getResource() == null) {
               return true;
            }

            return this.getResource().equals(otherWildcardPermission.getResource());
         }
      } else if (otherAuthorization instanceof PermissionBasedAuthorization) {
         PermissionBasedAuthorization otherPermission = (PermissionBasedAuthorization)otherAuthorization;
         if (this.wildcardPermission.implies(otherPermission.getPermission())) {
            if (this.getResource() == null) {
               return true;
            }

            return this.getResource().equals(otherPermission.getResource());
         }
      }

      return false;
   }

   private WildcardPermissionBasedAuthorization getResolvedAuthorization(AuthorizationContext context) {
      return (WildcardPermissionBasedAuthorization)(this.resource != null && this.resource.hasVariable() ? WildcardPermissionBasedAuthorization.create(this.permission).setResource(this.resource.resolve(context)) : this);
   }

   public String getResource() {
      return this.resource != null ? this.resource.getValue() : null;
   }

   public WildcardPermissionBasedAuthorization setResource(String resource) {
      this.resource = new VariableAwareExpression((String)Objects.requireNonNull(resource));
      return this;
   }
}
