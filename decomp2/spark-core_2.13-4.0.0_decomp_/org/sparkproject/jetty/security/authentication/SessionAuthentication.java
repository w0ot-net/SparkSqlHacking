package org.sparkproject.jetty.security.authentication;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionActivationListener;
import jakarta.servlet.http.HttpSessionBindingListener;
import jakarta.servlet.http.HttpSessionEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.security.AbstractUserAuthentication;
import org.sparkproject.jetty.security.Authenticator;
import org.sparkproject.jetty.security.LoginService;
import org.sparkproject.jetty.security.SecurityHandler;
import org.sparkproject.jetty.server.UserIdentity;

public class SessionAuthentication extends AbstractUserAuthentication implements Serializable, HttpSessionActivationListener, HttpSessionBindingListener {
   private static final Logger LOG = LoggerFactory.getLogger(SessionAuthentication.class);
   private static final long serialVersionUID = -4643200685888258706L;
   public static final String __J_AUTHENTICATED = "org.sparkproject.jetty.security.UserIdentity";
   private final String _name;
   private final Object _credentials;
   private transient HttpSession _session;

   public SessionAuthentication(String method, UserIdentity userIdentity, Object credentials) {
      super(method, userIdentity);
      this._name = userIdentity.getUserPrincipal().getName();
      this._credentials = credentials;
   }

   public UserIdentity getUserIdentity() {
      if (this._userIdentity == null) {
         throw new IllegalStateException("!UserIdentity");
      } else {
         return super.getUserIdentity();
      }
   }

   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
      stream.defaultReadObject();
      SecurityHandler security = SecurityHandler.getCurrentSecurityHandler();
      if (security == null) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("!SecurityHandler");
         }

      } else {
         Authenticator authenticator = security.getAuthenticator();
         LoginService loginService;
         if (authenticator instanceof LoginAuthenticator) {
            loginService = ((LoginAuthenticator)authenticator).getLoginService();
         } else {
            loginService = security.getLoginService();
         }

         if (loginService == null) {
            if (LOG.isDebugEnabled()) {
               LOG.debug("!LoginService");
            }

         } else {
            this._userIdentity = loginService.login(this._name, this._credentials, (ServletRequest)null);
            LOG.debug("Deserialized and relogged in {}", this);
         }
      }
   }

   public String toString() {
      return String.format("%s@%x{%s,%s}", this.getClass().getSimpleName(), this.hashCode(), this._session == null ? "-" : this._session.getId(), this._userIdentity);
   }

   public void sessionWillPassivate(HttpSessionEvent se) {
   }

   public void sessionDidActivate(HttpSessionEvent se) {
      if (this._session == null) {
         this._session = se.getSession();
      }

   }
}
