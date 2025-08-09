package org.sparkproject.jetty.security;

import jakarta.servlet.ServletRequest;
import org.sparkproject.jetty.server.UserIdentity;

public class EmptyLoginService implements LoginService {
   public String getName() {
      return null;
   }

   public UserIdentity login(String username, Object credentials, ServletRequest request) {
      return null;
   }

   public boolean validate(UserIdentity user) {
      return false;
   }

   public IdentityService getIdentityService() {
      return null;
   }

   public void setIdentityService(IdentityService service) {
   }

   public void logout(UserIdentity user) {
   }
}
