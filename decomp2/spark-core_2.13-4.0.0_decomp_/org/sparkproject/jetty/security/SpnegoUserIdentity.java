package org.sparkproject.jetty.security;

import java.security.Principal;
import javax.security.auth.Subject;
import org.sparkproject.jetty.server.UserIdentity;

public class SpnegoUserIdentity implements UserIdentity {
   private final Subject _subject;
   private final Principal _principal;
   private final UserIdentity _roleDelegate;

   public SpnegoUserIdentity(Subject subject, Principal principal, UserIdentity roleDelegate) {
      this._subject = subject;
      this._principal = principal;
      this._roleDelegate = roleDelegate;
   }

   public Subject getSubject() {
      return this._subject;
   }

   public Principal getUserPrincipal() {
      return this._principal;
   }

   public boolean isUserInRole(String role, UserIdentity.Scope scope) {
      return this._roleDelegate != null && this._roleDelegate.isUserInRole(role, scope);
   }

   public boolean isEstablished() {
      return this._roleDelegate != null;
   }
}
