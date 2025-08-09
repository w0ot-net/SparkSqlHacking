package io.vertx.ext.auth.authorization.impl;

import io.vertx.ext.auth.User;
import io.vertx.ext.auth.authorization.Authorization;
import io.vertx.ext.auth.authorization.AuthorizationContext;
import io.vertx.ext.auth.authorization.RoleBasedAuthorization;
import java.util.Objects;

public class RoleBasedAuthorizationImpl implements RoleBasedAuthorization {
   private final String role;
   private VariableAwareExpression resource;

   public RoleBasedAuthorizationImpl(String role) {
      this.role = (String)Objects.requireNonNull(role);
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (!(obj instanceof RoleBasedAuthorizationImpl)) {
         return false;
      } else {
         RoleBasedAuthorizationImpl other = (RoleBasedAuthorizationImpl)obj;
         return Objects.equals(this.resource, other.resource) && Objects.equals(this.role, other.role);
      }
   }

   public String getRole() {
      return this.role;
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.resource, this.role});
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

   private RoleBasedAuthorization getResolvedAuthorization(AuthorizationContext context) {
      return (RoleBasedAuthorization)(this.resource != null && this.resource.hasVariable() ? RoleBasedAuthorization.create(this.role).setResource(this.resource.resolve(context)) : this);
   }

   public boolean verify(Authorization otherAuthorization) {
      Objects.requireNonNull(otherAuthorization);
      if (otherAuthorization instanceof RoleBasedAuthorization) {
         RoleBasedAuthorization otherRoleBasedAuthorization = (RoleBasedAuthorization)otherAuthorization;
         if (this.role.equals(otherRoleBasedAuthorization.getRole())) {
            if (this.getResource() == null) {
               return otherRoleBasedAuthorization.getResource() == null;
            }

            return this.getResource().equals(otherRoleBasedAuthorization.getResource());
         }
      }

      return false;
   }

   public String getResource() {
      return this.resource != null ? this.resource.getValue() : null;
   }

   public RoleBasedAuthorization setResource(String resource) {
      Objects.requireNonNull(resource);
      this.resource = new VariableAwareExpression(resource);
      return this;
   }
}
