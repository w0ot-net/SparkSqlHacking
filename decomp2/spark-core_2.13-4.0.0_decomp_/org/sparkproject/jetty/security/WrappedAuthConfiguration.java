package org.sparkproject.jetty.security;

import java.util.Set;

public class WrappedAuthConfiguration implements Authenticator.AuthConfiguration {
   private final Authenticator.AuthConfiguration _configuration;

   public WrappedAuthConfiguration(Authenticator.AuthConfiguration configuration) {
      this._configuration = configuration;
   }

   public String getAuthMethod() {
      return this._configuration.getAuthMethod();
   }

   public String getRealmName() {
      return this._configuration.getRealmName();
   }

   public String getInitParameter(String param) {
      return this._configuration.getInitParameter(param);
   }

   public Set getInitParameterNames() {
      return this._configuration.getInitParameterNames();
   }

   public LoginService getLoginService() {
      return this._configuration.getLoginService();
   }

   public IdentityService getIdentityService() {
      return this._configuration.getIdentityService();
   }

   public boolean isSessionRenewedOnAuthentication() {
      return this._configuration.isSessionRenewedOnAuthentication();
   }

   public int getSessionMaxInactiveIntervalOnAuthentication() {
      return this._configuration.getSessionMaxInactiveIntervalOnAuthentication();
   }
}
