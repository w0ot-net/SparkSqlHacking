package org.sparkproject.jetty.security.authentication;

import java.security.Principal;
import javax.security.auth.Subject;

public interface LoginCallback {
   Subject getSubject();

   String getUserName();

   Object getCredential();

   boolean isSuccess();

   void setSuccess(boolean var1);

   Principal getUserPrincipal();

   void setUserPrincipal(Principal var1);

   String[] getRoles();

   void setRoles(String[] var1);

   void clearPassword();
}
