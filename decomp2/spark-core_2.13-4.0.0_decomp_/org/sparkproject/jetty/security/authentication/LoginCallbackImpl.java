package org.sparkproject.jetty.security.authentication;

import java.security.Principal;
import javax.security.auth.Subject;
import org.sparkproject.jetty.security.IdentityService;

public class LoginCallbackImpl implements LoginCallback {
   private final Subject subject;
   private final String userName;
   private Object credential;
   private boolean success;
   private Principal userPrincipal;
   private String[] roles;

   public LoginCallbackImpl(Subject subject, String userName, Object credential) {
      this.roles = IdentityService.NO_ROLES;
      this.subject = subject;
      this.userName = userName;
      this.credential = credential;
   }

   public Subject getSubject() {
      return this.subject;
   }

   public String getUserName() {
      return this.userName;
   }

   public Object getCredential() {
      return this.credential;
   }

   public boolean isSuccess() {
      return this.success;
   }

   public void setSuccess(boolean success) {
      this.success = success;
   }

   public Principal getUserPrincipal() {
      return this.userPrincipal;
   }

   public void setUserPrincipal(Principal userPrincipal) {
      this.userPrincipal = userPrincipal;
   }

   public String[] getRoles() {
      return this.roles;
   }

   public void setRoles(String[] groups) {
      this.roles = groups;
   }

   public void clearPassword() {
      if (this.credential != null) {
         this.credential = null;
      }

   }
}
