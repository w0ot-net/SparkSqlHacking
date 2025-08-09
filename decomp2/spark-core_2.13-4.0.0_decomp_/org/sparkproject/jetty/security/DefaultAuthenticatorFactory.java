package org.sparkproject.jetty.security;

import jakarta.servlet.ServletContext;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.security.authentication.BasicAuthenticator;
import org.sparkproject.jetty.security.authentication.ClientCertAuthenticator;
import org.sparkproject.jetty.security.authentication.ConfigurableSpnegoAuthenticator;
import org.sparkproject.jetty.security.authentication.DigestAuthenticator;
import org.sparkproject.jetty.security.authentication.FormAuthenticator;
import org.sparkproject.jetty.security.authentication.SslClientCertAuthenticator;
import org.sparkproject.jetty.server.Server;
import org.sparkproject.jetty.util.ssl.SslContextFactory;

public class DefaultAuthenticatorFactory implements Authenticator.Factory {
   private static final Logger LOG = LoggerFactory.getLogger(DefaultAuthenticatorFactory.class);
   LoginService _loginService;

   public Authenticator getAuthenticator(Server server, ServletContext context, Authenticator.AuthConfiguration configuration, IdentityService identityService, LoginService loginService) {
      String auth = configuration.getAuthMethod();
      Authenticator authenticator = null;
      if ("BASIC".equalsIgnoreCase(auth)) {
         authenticator = new BasicAuthenticator();
      } else if ("DIGEST".equalsIgnoreCase(auth)) {
         authenticator = new DigestAuthenticator();
      } else if ("FORM".equalsIgnoreCase(auth)) {
         authenticator = new FormAuthenticator();
      } else if ("SPNEGO".equalsIgnoreCase(auth)) {
         authenticator = new ConfigurableSpnegoAuthenticator();
      } else if ("NEGOTIATE".equalsIgnoreCase(auth)) {
         authenticator = new ConfigurableSpnegoAuthenticator("NEGOTIATE");
      }

      if ("CLIENT_CERT".equalsIgnoreCase(auth) || "CLIENT-CERT".equalsIgnoreCase(auth)) {
         Collection<SslContextFactory> sslContextFactories = server.getBeans(SslContextFactory.class);
         if (sslContextFactories.size() != 1) {
            if (sslContextFactories.size() > 1) {
               LOG.info("Multiple SslContextFactory instances discovered. Directly configure a SslClientCertAuthenticator to use one.");
            } else {
               LOG.debug("No SslContextFactory instances discovered. Directly configure a SslClientCertAuthenticator to use one.");
            }

            authenticator = new ClientCertAuthenticator();
         } else {
            authenticator = new SslClientCertAuthenticator((SslContextFactory)sslContextFactories.iterator().next());
         }
      }

      return authenticator;
   }

   public LoginService getLoginService() {
      return this._loginService;
   }

   public void setLoginService(LoginService loginService) {
      this._loginService = loginService;
   }
}
