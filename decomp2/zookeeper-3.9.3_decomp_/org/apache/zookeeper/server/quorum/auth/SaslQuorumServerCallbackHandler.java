package org.apache.zookeeper.server.quorum.auth;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.AppConfigurationEntry;
import javax.security.sasl.AuthorizeCallback;
import javax.security.sasl.RealmCallback;
import org.apache.zookeeper.server.auth.DigestLoginModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SaslQuorumServerCallbackHandler implements CallbackHandler {
   private static final String USER_PREFIX = "user_";
   private static final Logger LOG = LoggerFactory.getLogger(SaslQuorumServerCallbackHandler.class);
   private String userName;
   private final boolean isDigestAuthn;
   private final Map credentials;
   private final Set authzHosts;

   public SaslQuorumServerCallbackHandler(AppConfigurationEntry[] configurationEntries, Set authzHosts) {
      Map<String, String> credentials = new HashMap();
      boolean isDigestAuthn = true;

      for(AppConfigurationEntry entry : configurationEntries) {
         if (entry.getLoginModuleName().equals(DigestLoginModule.class.getName())) {
            Map<String, ?> options = entry.getOptions();

            for(Map.Entry pair : options.entrySet()) {
               String key = (String)pair.getKey();
               if (key.startsWith("user_")) {
                  String userName = key.substring("user_".length());
                  credentials.put(userName, (String)pair.getValue());
               }
            }
         } else {
            isDigestAuthn = false;
         }
      }

      this.isDigestAuthn = isDigestAuthn;
      if (isDigestAuthn) {
         this.credentials = Collections.unmodifiableMap(credentials);
         LOG.warn("Using DIGEST-MD5 for quorum authorization");
      } else {
         this.credentials = Collections.emptyMap();
      }

      this.authzHosts = authzHosts;
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
      if (this.credentials.containsKey(this.userName)) {
         pc.setPassword(((String)this.credentials.get(this.userName)).toCharArray());
      } else {
         LOG.warn("No password found for user: {}", this.userName);
      }

   }

   private void handleRealmCallback(RealmCallback rc) {
      LOG.debug("QuorumLearner supplied realm: {}", rc.getDefaultText());
      rc.setText(rc.getDefaultText());
   }

   private void handleAuthorizeCallback(AuthorizeCallback ac) {
      String authenticationID = ac.getAuthenticationID();
      String authorizationID = ac.getAuthorizationID();
      boolean authzFlag = false;
      authzFlag = authenticationID.equals(authorizationID);
      if (!this.isDigestAuthn && authzFlag) {
         String[] components = authorizationID.split("[/@]");
         if (components.length == 3) {
            authzFlag = this.authzHosts.contains(components[1]);
         } else {
            authzFlag = false;
         }

         if (!authzFlag) {
            LOG.error("SASL authorization completed, {} is not authorized to connect", authorizationID);
         }
      }

      ac.setAuthorized(authzFlag);
      if (ac.isAuthorized()) {
         ac.setAuthorizedID(authorizationID);
         LOG.info("Successfully authenticated learner: authenticationID={};  authorizationID={}.", authenticationID, authorizationID);
      }

      LOG.debug("SASL authorization completed, authorized flag set to {}", ac.isAuthorized());
   }
}
