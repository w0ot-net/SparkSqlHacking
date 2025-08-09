package org.sparkproject.jetty.security;

import jakarta.servlet.ServletRequest;
import org.sparkproject.jetty.security.authentication.LoginAuthenticator;
import org.sparkproject.jetty.server.Authentication;
import org.sparkproject.jetty.server.UserIdentity;

public class LoggedOutAuthentication implements Authentication.NonAuthenticated {
   private LoginAuthenticator _authenticator;

   public LoggedOutAuthentication(LoginAuthenticator authenticator) {
      this._authenticator = authenticator;
   }

   public Authentication login(String username, Object password, ServletRequest request) {
      if (username == null) {
         return null;
      } else {
         UserIdentity identity = this._authenticator.login(username, password, request);
         if (identity != null) {
            IdentityService identityService = this._authenticator.getLoginService().getIdentityService();
            UserAuthentication authentication = new UserAuthentication("API", identity);
            if (identityService != null) {
               identityService.associate(identity);
            }

            return authentication;
         } else {
            return null;
         }
      }
   }
}
