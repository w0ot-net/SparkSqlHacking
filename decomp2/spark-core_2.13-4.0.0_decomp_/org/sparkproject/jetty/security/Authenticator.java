package org.sparkproject.jetty.security;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import java.util.Set;
import org.sparkproject.jetty.server.Authentication;
import org.sparkproject.jetty.server.Server;

public interface Authenticator {
   void setConfiguration(AuthConfiguration var1);

   String getAuthMethod();

   void prepareRequest(ServletRequest var1);

   Authentication validateRequest(ServletRequest var1, ServletResponse var2, boolean var3) throws ServerAuthException;

   boolean secureResponse(ServletRequest var1, ServletResponse var2, boolean var3, Authentication.User var4) throws ServerAuthException;

   public interface AuthConfiguration {
      String getAuthMethod();

      String getRealmName();

      String getInitParameter(String var1);

      Set getInitParameterNames();

      LoginService getLoginService();

      IdentityService getIdentityService();

      boolean isSessionRenewedOnAuthentication();

      int getSessionMaxInactiveIntervalOnAuthentication();
   }

   public interface Factory {
      Authenticator getAuthenticator(Server var1, ServletContext var2, AuthConfiguration var3, IdentityService var4, LoginService var5);
   }
}
