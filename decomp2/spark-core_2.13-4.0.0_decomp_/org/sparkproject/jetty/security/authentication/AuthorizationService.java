package org.sparkproject.jetty.security.authentication;

import jakarta.servlet.http.HttpServletRequest;
import org.sparkproject.jetty.security.LoginService;
import org.sparkproject.jetty.server.UserIdentity;

@FunctionalInterface
public interface AuthorizationService {
   UserIdentity getUserIdentity(HttpServletRequest var1, String var2);

   static AuthorizationService from(LoginService loginService, Object credentials) {
      return (request, name) -> loginService.login(name, credentials, request);
   }
}
