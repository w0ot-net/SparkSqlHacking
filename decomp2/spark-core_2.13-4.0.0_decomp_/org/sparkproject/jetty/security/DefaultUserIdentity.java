package org.sparkproject.jetty.security;

import java.security.Principal;
import javax.security.auth.Subject;
import org.sparkproject.jetty.server.UserIdentity;

public class DefaultUserIdentity implements UserIdentity {
   private final Subject _subject;
   private final Principal _userPrincipal;
   private final String[] _roles;

   public DefaultUserIdentity(Subject subject, Principal userPrincipal, String[] roles) {
      this._subject = subject;
      this._userPrincipal = userPrincipal;
      this._roles = roles;
   }

   public Subject getSubject() {
      return this._subject;
   }

   public Principal getUserPrincipal() {
      return this._userPrincipal;
   }

   public boolean isUserInRole(String role, UserIdentity.Scope scope) {
      if ("*".equals(role)) {
         return false;
      } else {
         String roleToTest = null;
         if (scope != null && scope.getRoleRefMap() != null) {
            roleToTest = (String)scope.getRoleRefMap().get(role);
         }

         if (roleToTest == null) {
            roleToTest = role;
         }

         for(String r : this._roles) {
            if (r.equals(roleToTest)) {
               return true;
            }
         }

         return false;
      }
   }

   public String toString() {
      String var10000 = DefaultUserIdentity.class.getSimpleName();
      return var10000 + "('" + String.valueOf(this._userPrincipal) + "')";
   }
}
