package org.sparkproject.jetty.security.authentication;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;
import java.time.Duration;
import java.time.Instant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.http.HttpHeader;
import org.sparkproject.jetty.http.HttpMethod;
import org.sparkproject.jetty.security.ServerAuthException;
import org.sparkproject.jetty.security.SpnegoUserIdentity;
import org.sparkproject.jetty.security.SpnegoUserPrincipal;
import org.sparkproject.jetty.security.UserAuthentication;
import org.sparkproject.jetty.server.Authentication;
import org.sparkproject.jetty.server.Request;
import org.sparkproject.jetty.server.UserIdentity;

public class ConfigurableSpnegoAuthenticator extends LoginAuthenticator {
   private static final Logger LOG = LoggerFactory.getLogger(ConfigurableSpnegoAuthenticator.class);
   private final String _authMethod;
   private Duration _authenticationDuration;

   public ConfigurableSpnegoAuthenticator() {
      this("SPNEGO");
   }

   public ConfigurableSpnegoAuthenticator(String authMethod) {
      this._authenticationDuration = Duration.ofNanos(-1L);
      this._authMethod = authMethod;
   }

   public String getAuthMethod() {
      return this._authMethod;
   }

   public Duration getAuthenticationDuration() {
      return this._authenticationDuration;
   }

   public void setAuthenticationDuration(Duration authenticationDuration) {
      this._authenticationDuration = authenticationDuration;
   }

   public UserIdentity login(String username, Object password, ServletRequest servletRequest) {
      SpnegoUserIdentity user = (SpnegoUserIdentity)this._loginService.login(username, password, servletRequest);
      if (user != null && user.isEstablished()) {
         Request request = Request.getBaseRequest(servletRequest);
         this.renewSession(request, request == null ? null : request.getResponse());
      }

      return user;
   }

   public Authentication validateRequest(ServletRequest req, ServletResponse res, boolean mandatory) throws ServerAuthException {
      if (!mandatory) {
         return new DeferredAuthentication(this);
      } else {
         HttpServletRequest request = (HttpServletRequest)req;
         HttpServletResponse response = (HttpServletResponse)res;
         String header = request.getHeader(HttpHeader.AUTHORIZATION.asString());
         String spnegoToken = this.getSpnegoToken(header);
         HttpSession httpSession = request.getSession(false);
         if (header != null && spnegoToken != null) {
            SpnegoUserIdentity identity = (SpnegoUserIdentity)this.login((String)null, spnegoToken, request);
            if (identity.isEstablished()) {
               if (!DeferredAuthentication.isDeferred(response)) {
                  if (LOG.isDebugEnabled()) {
                     LOG.debug("Sending final token");
                  }

                  SpnegoUserPrincipal principal = (SpnegoUserPrincipal)identity.getUserPrincipal();
                  this.setSpnegoToken(response, principal.getEncodedToken());
               }

               Duration authnDuration = this.getAuthenticationDuration();
               if (!authnDuration.isNegative()) {
                  if (httpSession == null) {
                     httpSession = request.getSession(true);
                  }

                  httpSession.setAttribute(ConfigurableSpnegoAuthenticator.UserIdentityHolder.ATTRIBUTE, new UserIdentityHolder(identity));
               }

               return new UserAuthentication(this.getAuthMethod(), identity);
            } else if (DeferredAuthentication.isDeferred(response)) {
               return Authentication.UNAUTHENTICATED;
            } else {
               if (LOG.isDebugEnabled()) {
                  LOG.debug("Sending intermediate challenge");
               }

               SpnegoUserPrincipal principal = (SpnegoUserPrincipal)identity.getUserPrincipal();
               this.sendChallenge(response, principal.getEncodedToken());
               return Authentication.SEND_CONTINUE;
            }
         } else {
            if (httpSession != null) {
               UserIdentityHolder holder = (UserIdentityHolder)httpSession.getAttribute(ConfigurableSpnegoAuthenticator.UserIdentityHolder.ATTRIBUTE);
               if (holder != null) {
                  UserIdentity identity = holder._userIdentity;
                  if (identity != null) {
                     Duration authnDuration = this.getAuthenticationDuration();
                     if (!authnDuration.isNegative()) {
                        boolean expired = !authnDuration.isZero() && Instant.now().isAfter(holder._validFrom.plus(authnDuration));
                        if (!expired || !HttpMethod.GET.is(request.getMethod())) {
                           return new UserAuthentication(this.getAuthMethod(), identity);
                        }
                     }
                  }
               }
            }

            if (DeferredAuthentication.isDeferred(response)) {
               return Authentication.UNAUTHENTICATED;
            } else {
               if (LOG.isDebugEnabled()) {
                  LOG.debug("Sending initial challenge");
               }

               this.sendChallenge(response, (String)null);
               return Authentication.SEND_CONTINUE;
            }
         }
      }
   }

   private void sendChallenge(HttpServletResponse response, String token) throws ServerAuthException {
      try {
         this.setSpnegoToken(response, token);
         response.sendError(401);
      } catch (IOException x) {
         throw new ServerAuthException(x);
      }
   }

   private void setSpnegoToken(HttpServletResponse response, String token) {
      String value = HttpHeader.NEGOTIATE.asString();
      if (token != null) {
         value = value + " " + token;
      }

      response.setHeader(HttpHeader.WWW_AUTHENTICATE.asString(), value);
   }

   private String getSpnegoToken(String header) {
      if (header == null) {
         return null;
      } else {
         String scheme = HttpHeader.NEGOTIATE.asString() + " ";
         return header.regionMatches(true, 0, scheme, 0, scheme.length()) ? header.substring(scheme.length()).trim() : null;
      }
   }

   public boolean secureResponse(ServletRequest request, ServletResponse response, boolean mandatory, Authentication.User validatedUser) {
      return true;
   }

   private static class UserIdentityHolder implements Serializable {
      private static final String ATTRIBUTE = UserIdentityHolder.class.getName();
      private final transient Instant _validFrom = Instant.now();
      private final transient UserIdentity _userIdentity;

      private UserIdentityHolder(UserIdentity userIdentity) {
         this._userIdentity = userIdentity;
      }
   }
}
