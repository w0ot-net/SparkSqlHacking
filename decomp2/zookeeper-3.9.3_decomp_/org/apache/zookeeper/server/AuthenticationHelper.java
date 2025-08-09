package org.apache.zookeeper.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.jute.Record;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.proto.ReplyHeader;
import org.apache.zookeeper.server.auth.ProviderRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthenticationHelper {
   private static final Logger LOG = LoggerFactory.getLogger(AuthenticationHelper.class);
   public static final String ENFORCE_AUTH_ENABLED = "zookeeper.enforce.auth.enabled";
   public static final String ENFORCE_AUTH_SCHEMES = "zookeeper.enforce.auth.schemes";
   public static final String SESSION_REQUIRE_CLIENT_SASL_AUTH = "zookeeper.sessionRequireClientSASLAuth";
   public static final String SASL_AUTH_SCHEME = "sasl";
   private boolean enforceAuthEnabled;
   private List enforceAuthSchemes = new ArrayList();
   private boolean saslAuthRequired;

   public AuthenticationHelper() {
      this.initConfigurations();
   }

   private void initConfigurations() {
      if (Boolean.parseBoolean(System.getProperty("zookeeper.sessionRequireClientSASLAuth", "false"))) {
         this.enforceAuthEnabled = true;
         this.enforceAuthSchemes.add("sasl");
      } else {
         this.enforceAuthEnabled = Boolean.parseBoolean(System.getProperty("zookeeper.enforce.auth.enabled", "false"));
         String enforceAuthSchemesProp = System.getProperty("zookeeper.enforce.auth.schemes");
         if (enforceAuthSchemesProp != null) {
            Arrays.stream(enforceAuthSchemesProp.split(",")).forEach((s) -> this.enforceAuthSchemes.add(s.trim()));
         }
      }

      LOG.info("{} = {}", "zookeeper.enforce.auth.enabled", this.enforceAuthEnabled);
      LOG.info("{} = {}", "zookeeper.enforce.auth.schemes", this.enforceAuthSchemes);
      this.validateConfiguredProperties();
      this.saslAuthRequired = this.enforceAuthEnabled && this.enforceAuthSchemes.contains("sasl");
   }

   private void validateConfiguredProperties() {
      if (this.enforceAuthEnabled) {
         if (this.enforceAuthSchemes.isEmpty()) {
            String msg = "zookeeper.enforce.auth.enabled is true zookeeper.enforce.auth.schemes must be  configured.";
            LOG.error(msg);
            throw new IllegalArgumentException(msg);
         }

         this.enforceAuthSchemes.forEach((scheme) -> {
            if (ProviderRegistry.getProvider(scheme) == null) {
               String msg = "Authentication scheme " + scheme + " is not available.";
               LOG.error(msg);
               throw new IllegalArgumentException(msg);
            }
         });
      }

   }

   private boolean isCnxnAuthenticated(ServerCnxn cnxn) {
      for(Id id : cnxn.getAuthInfo()) {
         if (this.enforceAuthSchemes.contains(id.getScheme())) {
            return true;
         }
      }

      return false;
   }

   public boolean isEnforceAuthEnabled() {
      return this.enforceAuthEnabled;
   }

   public boolean enforceAuthentication(ServerCnxn connection, int xid) throws IOException {
      if (this.isEnforceAuthEnabled() && !this.isCnxnAuthenticated(connection)) {
         LOG.error("Client authentication scheme(s) {} does not match with any of the expected authentication scheme {}, closing session.", this.getAuthSchemes(connection), this.enforceAuthSchemes);
         ReplyHeader replyHeader = new ReplyHeader(xid, 0L, KeeperException.Code.SESSIONCLOSEDREQUIRESASLAUTH.intValue());
         connection.sendResponse(replyHeader, (Record)null, "response");
         connection.sendCloseSession();
         connection.disableRecv();
         return false;
      } else {
         return true;
      }
   }

   private List getAuthSchemes(ServerCnxn connection) {
      return (List)connection.getAuthInfo().stream().map(Id::getScheme).collect(Collectors.toList());
   }

   public boolean isSaslAuthRequired() {
      return this.saslAuthRequired;
   }
}
