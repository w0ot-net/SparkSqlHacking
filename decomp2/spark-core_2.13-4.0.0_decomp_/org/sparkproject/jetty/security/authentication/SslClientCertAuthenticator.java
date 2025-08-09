package org.sparkproject.jetty.security.authentication;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.security.cert.X509Certificate;
import java.util.Base64;
import java.util.Objects;
import org.sparkproject.jetty.security.ServerAuthException;
import org.sparkproject.jetty.security.UserAuthentication;
import org.sparkproject.jetty.server.Authentication;
import org.sparkproject.jetty.server.UserIdentity;
import org.sparkproject.jetty.util.ssl.SslContextFactory;

public class SslClientCertAuthenticator extends LoginAuthenticator {
   private final SslContextFactory sslContextFactory;
   private boolean validateCerts = true;

   public SslClientCertAuthenticator(SslContextFactory sslContextFactory) {
      this.sslContextFactory = (SslContextFactory)Objects.requireNonNull(sslContextFactory);
   }

   public String getAuthMethod() {
      return "CLIENT_CERT";
   }

   public Authentication validateRequest(ServletRequest req, ServletResponse res, boolean mandatory) throws ServerAuthException {
      if (!mandatory) {
         return new DeferredAuthentication(this);
      } else {
         HttpServletRequest request = (HttpServletRequest)req;
         HttpServletResponse response = (HttpServletResponse)res;
         X509Certificate[] certs = (X509Certificate[])request.getAttribute("jakarta.servlet.request.X509Certificate");

         try {
            if (certs != null && certs.length > 0) {
               if (this.validateCerts) {
                  this.sslContextFactory.validateCerts(certs);
               }

               for(X509Certificate cert : certs) {
                  if (cert != null) {
                     Principal principal = cert.getSubjectDN();
                     if (principal == null) {
                        principal = cert.getIssuerDN();
                     }

                     String username = principal == null ? "clientcert" : principal.getName();
                     UserIdentity user = this.login(username, "", req);
                     if (user != null) {
                        return new UserAuthentication(this.getAuthMethod(), user);
                     }

                     user = this.login(username, (Object)null, req);
                     if (user != null) {
                        return new UserAuthentication(this.getAuthMethod(), user);
                     }

                     char[] credential = Base64.getEncoder().encodeToString(cert.getSignature()).toCharArray();
                     user = this.login(username, credential, req);
                     if (user != null) {
                        return new UserAuthentication(this.getAuthMethod(), user);
                     }
                  }
               }
            }

            if (!DeferredAuthentication.isDeferred(response)) {
               response.sendError(403);
               return Authentication.SEND_FAILURE;
            } else {
               return Authentication.UNAUTHENTICATED;
            }
         } catch (Exception e) {
            throw new ServerAuthException(e.getMessage());
         }
      }
   }

   public boolean secureResponse(ServletRequest req, ServletResponse res, boolean mandatory, Authentication.User validatedUser) throws ServerAuthException {
      return true;
   }

   public boolean isValidateCerts() {
      return this.validateCerts;
   }

   public void setValidateCerts(boolean validateCerts) {
      this.validateCerts = validateCerts;
   }
}
