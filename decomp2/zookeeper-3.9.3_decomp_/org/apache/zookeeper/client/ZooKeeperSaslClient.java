package org.apache.zookeeper.client;

import java.io.IOException;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;
import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.AppConfigurationEntry;
import javax.security.auth.login.Configuration;
import javax.security.auth.login.LoginException;
import javax.security.sasl.SaslClient;
import javax.security.sasl.SaslException;
import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.ClientCnxn;
import org.apache.zookeeper.Login;
import org.apache.zookeeper.SaslClientCallbackHandler;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.proto.GetSASLRequest;
import org.apache.zookeeper.proto.SetSASLResponse;
import org.apache.zookeeper.util.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZooKeeperSaslClient {
   /** @deprecated */
   @Deprecated
   public static final String LOGIN_CONTEXT_NAME_KEY = "zookeeper.sasl.clientconfig";
   /** @deprecated */
   @Deprecated
   public static final String ENABLE_CLIENT_SASL_KEY = "zookeeper.sasl.client";
   /** @deprecated */
   @Deprecated
   public static final String ENABLE_CLIENT_SASL_DEFAULT = "true";
   private static final Logger LOG = LoggerFactory.getLogger(ZooKeeperSaslClient.class);
   private Login login = null;
   private SaslClient saslClient;
   private boolean isSASLConfigured = true;
   private final ZKClientConfig clientConfig;
   private byte[] saslToken = new byte[0];
   private SaslState saslState;
   private boolean gotLastPacket;
   private final String configStatus;

   /** @deprecated */
   @Deprecated
   public static boolean isEnabled() {
      return Boolean.parseBoolean(System.getProperty("zookeeper.sasl.client", "true"));
   }

   public SaslState getSaslState() {
      return this.saslState;
   }

   public String getLoginContext() {
      return this.login != null ? this.login.getLoginContextName() : null;
   }

   public ZooKeeperSaslClient(String serverPrincipal, ZKClientConfig clientConfig, AtomicReference loginRef) throws LoginException {
      this.saslState = ZooKeeperSaslClient.SaslState.INITIAL;
      this.gotLastPacket = false;
      String clientSection = clientConfig.getProperty("zookeeper.sasl.clientconfig", "Client");
      this.clientConfig = clientConfig;
      AppConfigurationEntry[] entries = null;
      RuntimeException runtimeException = null;

      try {
         entries = Configuration.getConfiguration().getAppConfigurationEntry(clientSection);
      } catch (SecurityException e) {
         runtimeException = e;
      } catch (IllegalArgumentException e) {
         runtimeException = e;
      }

      if (entries != null) {
         this.configStatus = "Will attempt to SASL-authenticate using Login Context section '" + clientSection + "'";
         this.saslClient = this.createSaslClient(serverPrincipal, clientSection, loginRef);
         this.login = (Login)loginRef.get();
      } else {
         this.saslState = ZooKeeperSaslClient.SaslState.FAILED;
         String explicitClientSection = clientConfig.getProperty("zookeeper.sasl.clientconfig");
         if (explicitClientSection != null) {
            if (runtimeException != null) {
               throw new LoginException("Zookeeper client cannot authenticate using the " + explicitClientSection + " section of the supplied JAAS configuration: '" + clientConfig.getJaasConfKey() + "' because of a RuntimeException: " + runtimeException);
            }

            throw new LoginException("Client cannot SASL-authenticate because the specified JAAS configuration section '" + explicitClientSection + "' could not be found.");
         }

         String msg = "Will not attempt to authenticate using SASL ";
         if (runtimeException != null) {
            msg = msg + "(" + runtimeException + ")";
         } else {
            msg = msg + "(unknown error)";
         }

         this.configStatus = msg;
         this.isSASLConfigured = false;
         if (clientConfig.getJaasConfKey() != null) {
            if (runtimeException != null) {
               throw new LoginException("Zookeeper client cannot authenticate using the '" + clientConfig.getProperty("zookeeper.sasl.clientconfig", "Client") + "' section of the supplied JAAS configuration: '" + clientConfig.getJaasConfKey() + "' because of a RuntimeException: " + runtimeException);
            }

            throw new LoginException("No JAAS configuration section named '" + clientConfig.getProperty("zookeeper.sasl.clientconfig", "Client") + "' was found in specified JAAS configuration file: '" + clientConfig.getJaasConfKey() + "'.");
         }
      }

   }

   public String getConfigStatus() {
      return this.configStatus;
   }

   public boolean isComplete() {
      return this.saslState == ZooKeeperSaslClient.SaslState.COMPLETE;
   }

   public boolean isFailed() {
      return this.saslState == ZooKeeperSaslClient.SaslState.FAILED;
   }

   private SaslClient createSaslClient(String servicePrincipal, String loginContext, AtomicReference loginRef) throws LoginException {
      try {
         if (loginRef.get() == null) {
            LOG.debug("JAAS loginContext is: {}", loginContext);
            Supplier<CallbackHandler> callbackHandlerSupplier = () -> new SaslClientCallbackHandler((String)null, "Client");
            Login l = new Login(loginContext, callbackHandlerSupplier, this.clientConfig);
            if (loginRef.compareAndSet((Object)null, l)) {
               l.startThreadIfNeeded();
            }
         }

         return SecurityUtils.createSaslClient(((Login)loginRef.get()).getSubject(), servicePrincipal, "zookeeper", "zk-sasl-md5", LOG, "Client");
      } catch (LoginException e) {
         throw e;
      } catch (Exception e) {
         LOG.error("Exception while trying to create SASL client.", e);
         return null;
      }
   }

   public void respondToServer(byte[] serverToken, ClientCnxn cnxn) {
      if (this.saslClient == null) {
         LOG.error("saslClient is unexpectedly null. Cannot respond to server's SASL message; ignoring.");
      } else {
         if (!this.saslClient.isComplete()) {
            try {
               this.saslToken = this.createSaslToken(serverToken);
               if (this.saslToken != null) {
                  this.sendSaslPacket(this.saslToken, cnxn);
               }
            } catch (SaslException e) {
               LOG.error("SASL authentication failed using login context '{}'.", this.getLoginContext(), e);
               this.saslState = ZooKeeperSaslClient.SaslState.FAILED;
               this.gotLastPacket = true;
            }
         }

         if (this.saslClient.isComplete()) {
            if (serverToken == null && this.saslClient.getMechanismName().equals("GSSAPI")) {
               this.gotLastPacket = true;
            }

            if (!this.saslClient.getMechanismName().equals("GSSAPI")) {
               this.gotLastPacket = true;
            }

            cnxn.saslCompleted();
         }

      }
   }

   private byte[] createSaslToken() throws SaslException {
      this.saslState = ZooKeeperSaslClient.SaslState.INTERMEDIATE;
      return this.createSaslToken(this.saslToken);
   }

   private byte[] createSaslToken(final byte[] saslToken) throws SaslException {
      if (saslToken == null) {
         this.saslState = ZooKeeperSaslClient.SaslState.FAILED;
         throw new SaslException("Error in authenticating with a Zookeeper Quorum member: the quorum member's saslToken is null.");
      } else {
         Subject subject = this.login.getSubject();
         if (subject != null) {
            synchronized(this.login) {
               byte[] var10000;
               try {
                  byte[] retval = (byte[])Subject.doAs(subject, new PrivilegedExceptionAction() {
                     public byte[] run() throws SaslException {
                        ZooKeeperSaslClient.LOG.debug("saslClient.evaluateChallenge(len={})", saslToken.length);
                        return ZooKeeperSaslClient.this.saslClient.evaluateChallenge(saslToken);
                     }
                  });
                  var10000 = retval;
               } catch (PrivilegedActionException e) {
                  String error = "An error: (" + e + ") occurred when evaluating Zookeeper Quorum Member's  received SASL token.";
                  String UNKNOWN_SERVER_ERROR_TEXT = "(Mechanism level: Server not found in Kerberos database (7) - UNKNOWN_SERVER)";
                  if (e.toString().contains("(Mechanism level: Server not found in Kerberos database (7) - UNKNOWN_SERVER)")) {
                     error = error + " This may be caused by Java's being unable to resolve the Zookeeper Quorum Member's hostname correctly. You may want to try to adding '-Dsun.net.spi.nameservice.provider.1=dns,sun' to your client's JVMFLAGS environment.";
                  }

                  error = error + " Zookeeper Client will go to AUTH_FAILED state.";
                  LOG.error(error);
                  this.saslState = ZooKeeperSaslClient.SaslState.FAILED;
                  throw new SaslException(error, e);
               }

               return var10000;
            }
         } else {
            throw new SaslException("Cannot make SASL token without subject defined. For diagnosis, please look for WARNs and ERRORs in your log related to the Login class.");
         }
      }
   }

   private void sendSaslPacket(byte[] saslToken, ClientCnxn cnxn) throws SaslException {
      LOG.debug("ClientCnxn:sendSaslPacket:length={}", saslToken.length);
      GetSASLRequest request = new GetSASLRequest();
      request.setToken(saslToken);
      SetSASLResponse response = new SetSASLResponse();
      ServerSaslResponseCallback cb = new ServerSaslResponseCallback();

      try {
         cnxn.sendPacket(request, response, cb, 102);
      } catch (IOException e) {
         throw new SaslException("Failed to send SASL packet to server.", e);
      }
   }

   private void sendSaslPacket(ClientCnxn cnxn) throws SaslException {
      LOG.debug("ClientCnxn:sendSaslPacket:length={}", this.saslToken.length);
      GetSASLRequest request = new GetSASLRequest();
      request.setToken(this.createSaslToken());
      SetSASLResponse response = new SetSASLResponse();
      ServerSaslResponseCallback cb = new ServerSaslResponseCallback();

      try {
         cnxn.sendPacket(request, response, cb, 102);
      } catch (IOException e) {
         throw new SaslException("Failed to send SASL packet to server due to IOException:", e);
      }
   }

   public Watcher.Event.KeeperState getKeeperState() {
      if (this.saslClient != null) {
         if (this.saslState == ZooKeeperSaslClient.SaslState.FAILED) {
            return Watcher.Event.KeeperState.AuthFailed;
         }

         if (this.saslClient.isComplete() && this.saslState == ZooKeeperSaslClient.SaslState.INTERMEDIATE) {
            this.saslState = ZooKeeperSaslClient.SaslState.COMPLETE;
            return Watcher.Event.KeeperState.SaslAuthenticated;
         }
      }

      return null;
   }

   public void initialize(ClientCnxn cnxn) throws SaslException {
      if (this.saslClient == null) {
         this.saslState = ZooKeeperSaslClient.SaslState.FAILED;
         throw new SaslException("saslClient failed to initialize properly: it's null.");
      } else {
         if (this.saslState == ZooKeeperSaslClient.SaslState.INITIAL) {
            if (this.saslClient.hasInitialResponse()) {
               this.sendSaslPacket(cnxn);
            } else {
               byte[] emptyToken = new byte[0];
               this.sendSaslPacket(emptyToken, cnxn);
            }

            this.saslState = ZooKeeperSaslClient.SaslState.INTERMEDIATE;
         }

      }
   }

   public boolean clientTunneledAuthenticationInProgress() {
      if (!this.isSASLConfigured) {
         return false;
      } else {
         try {
            if (this.clientConfig.getJaasConfKey() != null || Configuration.getConfiguration() != null && Configuration.getConfiguration().getAppConfigurationEntry(this.clientConfig.getProperty("zookeeper.sasl.clientconfig", "Client")) != null) {
               if (!this.isComplete() && !this.isFailed()) {
                  return true;
               }

               if (!this.gotLastPacket) {
                  return true;
               }
            }

            return false;
         } catch (SecurityException e) {
            LOG.debug("Could not retrieve login configuration", e);
            return false;
         }
      }
   }

   public static enum SaslState {
      INITIAL,
      INTERMEDIATE,
      COMPLETE,
      FAILED;
   }

   public static class ServerSaslResponseCallback implements AsyncCallback.DataCallback {
      public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
         ZooKeeperSaslClient client = ((ClientCnxn)ctx).getZooKeeperSaslClient();
         if (client == null) {
            ZooKeeperSaslClient.LOG.warn("sasl client was unexpectedly null: cannot respond to Zookeeper server.");
         } else {
            byte[] usedata = data;
            if (data != null) {
               ZooKeeperSaslClient.LOG.debug("ServerSaslResponseCallback(): saslToken server response: (length={})", data.length);
            } else {
               usedata = new byte[0];
               ZooKeeperSaslClient.LOG.debug("ServerSaslResponseCallback(): using empty data[] as server response (length={})", usedata.length);
            }

            client.respondToServer(usedata, (ClientCnxn)ctx);
         }
      }
   }
}
