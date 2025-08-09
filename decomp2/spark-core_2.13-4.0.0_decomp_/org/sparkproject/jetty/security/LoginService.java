package org.sparkproject.jetty.security;

import jakarta.servlet.ServletRequest;
import org.sparkproject.jetty.server.UserIdentity;

public interface LoginService {
   String getName();

   UserIdentity login(String var1, Object var2, ServletRequest var3);

   boolean validate(UserIdentity var1);

   IdentityService getIdentityService();

   void setIdentityService(IdentityService var1);

   void logout(UserIdentity var1);
}
