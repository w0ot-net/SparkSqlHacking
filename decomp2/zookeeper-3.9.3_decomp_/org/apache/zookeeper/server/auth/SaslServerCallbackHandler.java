package org.apache.zookeeper.server.auth;

import java.io.IOException;
import java.util.Map;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.sasl.AuthorizeCallback;
import javax.security.sasl.RealmCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SaslServerCallbackHandler implements CallbackHandler {
   private static final Logger LOG = LoggerFactory.getLogger(SaslServerCallbackHandler.class);
   private static final String SYSPROP_SUPER_PASSWORD = "zookeeper.SASLAuthenticationProvider.superPassword";
   private static final String SYSPROP_REMOVE_HOST = "zookeeper.kerberos.removeHostFromPrincipal";
   private static final String SYSPROP_REMOVE_REALM = "zookeeper.kerberos.removeRealmFromPrincipal";
   private String userName;
   private final Map credentials;

   public SaslServerCallbackHandler(Map credentials) {
      this.credentials = credentials;
   }

   public void handle(Callback[] callbacks) throws UnsupportedCallbackException {
      for(Callback callback : callbacks) {
         if (callback instanceof NameCallback) {
            this.handleNameCallback((NameCallback)callback);
         } else if (callback instanceof PasswordCallback) {
            this.handlePasswordCallback((PasswordCallback)callback);
         } else if (callback instanceof RealmCallback) {
            this.handleRealmCallback((RealmCallback)callback);
         } else if (callback instanceof AuthorizeCallback) {
            this.handleAuthorizeCallback((AuthorizeCallback)callback);
         }
      }

   }

   private void handleNameCallback(NameCallback nc) {
      if (this.credentials.get(nc.getDefaultName()) == null) {
         LOG.warn("User '{}' not found in list of DIGEST-MD5 authenticateable users.", nc.getDefaultName());
      } else {
         nc.setName(nc.getDefaultName());
         this.userName = nc.getDefaultName();
      }
   }

   private void handlePasswordCallback(PasswordCallback pc) {
      if ("super".equals(this.userName) && System.getProperty("zookeeper.SASLAuthenticationProvider.superPassword") != null) {
         pc.setPassword(System.getProperty("zookeeper.SASLAuthenticationProvider.superPassword").toCharArray());
      } else if (this.credentials.containsKey(this.userName)) {
         pc.setPassword(((String)this.credentials.get(this.userName)).toCharArray());
      } else {
         LOG.warn("No password found for user: {}", this.userName);
      }

   }

   private void handleRealmCallback(RealmCallback rc) {
      LOG.debug("client supplied realm: {}", rc.getDefaultText());
      rc.setText(rc.getDefaultText());
   }

   private void handleAuthorizeCallback(AuthorizeCallback ac) {
      String authenticationID = ac.getAuthenticationID();
      String authorizationID = ac.getAuthorizationID();
      LOG.info("Successfully authenticated client: authenticationID={};  authorizationID={}.", authenticationID, authorizationID);
      ac.setAuthorized(true);
      KerberosName kerberosName = new KerberosName(authenticationID);

      try {
         StringBuilder userNameBuilder = new StringBuilder(kerberosName.getShortName());
         if (this.shouldAppendHost(kerberosName)) {
            userNameBuilder.append("/").append(kerberosName.getHostName());
         }

         if (this.shouldAppendRealm(kerberosName)) {
            userNameBuilder.append("@").append(kerberosName.getRealm());
         }

         LOG.info("Setting authorizedID: {}", userNameBuilder);
         ac.setAuthorizedID(userNameBuilder.toString());
      } catch (IOException e) {
         LOG.error("Failed to set name based on Kerberos authentication rules.", e);
      }

   }

   private boolean shouldAppendRealm(KerberosName kerberosName) {
      return !this.isSystemPropertyTrue("zookeeper.kerberos.removeRealmFromPrincipal") && kerberosName.getRealm() != null;
   }

   private boolean shouldAppendHost(KerberosName kerberosName) {
      return !this.isSystemPropertyTrue("zookeeper.kerberos.removeHostFromPrincipal") && kerberosName.getHostName() != null;
   }

   private boolean isSystemPropertyTrue(String propertyName) {
      return "true".equals(System.getProperty(propertyName));
   }
}
