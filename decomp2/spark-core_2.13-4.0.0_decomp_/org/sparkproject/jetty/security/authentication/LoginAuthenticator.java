package org.sparkproject.jetty.security.authentication;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.security.Authenticator;
import org.sparkproject.jetty.security.IdentityService;
import org.sparkproject.jetty.security.LoginService;
import org.sparkproject.jetty.server.Request;
import org.sparkproject.jetty.server.Response;
import org.sparkproject.jetty.server.UserIdentity;
import org.sparkproject.jetty.server.session.Session;

public abstract class LoginAuthenticator implements Authenticator {
   private static final Logger LOG = LoggerFactory.getLogger(LoginAuthenticator.class);
   protected LoginService _loginService;
   protected IdentityService _identityService;
   private boolean _sessionRenewedOnAuthentication;
   private int _sessionMaxInactiveIntervalOnAuthentication;

   protected LoginAuthenticator() {
   }

   public void prepareRequest(ServletRequest request) {
   }

   public UserIdentity login(String username, Object password, ServletRequest servletRequest) {
      UserIdentity user = this._loginService.login(username, password, servletRequest);
      if (user != null) {
         Request request = Request.getBaseRequest(servletRequest);
         this.renewSession(request, request == null ? null : request.getResponse());
         return user;
      } else {
         return null;
      }
   }

   public void logout(ServletRequest request) {
      HttpServletRequest httpRequest = (HttpServletRequest)request;
      HttpSession session = httpRequest.getSession(false);
      if (session != null) {
         session.removeAttribute("org.sparkproject.jetty.security.sessionCreatedSecure");
      }
   }

   public void setConfiguration(Authenticator.AuthConfiguration configuration) {
      this._loginService = configuration.getLoginService();
      if (this._loginService == null) {
         String var2 = String.valueOf(this);
         throw new IllegalStateException("No LoginService for " + var2 + " in " + String.valueOf(configuration));
      } else {
         this._identityService = configuration.getIdentityService();
         if (this._identityService == null) {
            String var10002 = String.valueOf(this);
            throw new IllegalStateException("No IdentityService for " + var10002 + " in " + String.valueOf(configuration));
         } else {
            this._sessionRenewedOnAuthentication = configuration.isSessionRenewedOnAuthentication();
            this._sessionMaxInactiveIntervalOnAuthentication = configuration.getSessionMaxInactiveIntervalOnAuthentication();
         }
      }
   }

   public LoginService getLoginService() {
      return this._loginService;
   }

   protected HttpSession renewSession(HttpServletRequest request, HttpServletResponse response) {
      HttpSession session = request.getSession(false);
      if (session != null && (this._sessionRenewedOnAuthentication || this._sessionMaxInactiveIntervalOnAuthentication != 0)) {
         synchronized(session) {
            if (this._sessionMaxInactiveIntervalOnAuthentication != 0) {
               session.setMaxInactiveInterval(this._sessionMaxInactiveIntervalOnAuthentication < 0 ? -1 : this._sessionMaxInactiveIntervalOnAuthentication);
            }

            if (this._sessionRenewedOnAuthentication && session.getAttribute("org.sparkproject.jetty.security.sessionCreatedSecure") != Boolean.TRUE) {
               if (session instanceof Session) {
                  Session s = (Session)session;
                  String oldId = s.getId();
                  s.renewId(request);
                  s.setAttribute("org.sparkproject.jetty.security.sessionCreatedSecure", Boolean.TRUE);
                  if (s.isIdChanged() && response instanceof Response) {
                     ((Response)response).replaceCookie(s.getSessionHandler().getSessionCookie(s, request.getContextPath(), request.isSecure()));
                  }

                  if (LOG.isDebugEnabled()) {
                     LOG.debug("renew {}->{}", oldId, s.getId());
                  }
               } else {
                  LOG.warn("Unable to renew session {}", session);
               }

               return session;
            }
         }
      }

      return session;
   }
}
