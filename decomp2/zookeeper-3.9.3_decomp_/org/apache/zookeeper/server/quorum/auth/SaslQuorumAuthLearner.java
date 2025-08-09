package org.apache.zookeeper.server.quorum.auth;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.function.Supplier;
import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.AppConfigurationEntry;
import javax.security.auth.login.Configuration;
import javax.security.auth.login.LoginException;
import javax.security.sasl.SaslClient;
import javax.security.sasl.SaslException;
import org.apache.jute.BinaryInputArchive;
import org.apache.jute.BinaryOutputArchive;
import org.apache.zookeeper.Login;
import org.apache.zookeeper.SaslClientCallbackHandler;
import org.apache.zookeeper.common.ZKConfig;
import org.apache.zookeeper.server.quorum.QuorumAuthPacket;
import org.apache.zookeeper.util.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SaslQuorumAuthLearner implements QuorumAuthLearner {
   private static final Logger LOG = LoggerFactory.getLogger(SaslQuorumAuthLearner.class);
   private final Login learnerLogin;
   private final boolean quorumRequireSasl;
   private final String quorumServicePrincipal;

   public SaslQuorumAuthLearner(boolean quorumRequireSasl, String quorumServicePrincipal, String loginContext) throws SaslException {
      this.quorumRequireSasl = quorumRequireSasl;
      this.quorumServicePrincipal = quorumServicePrincipal;

      try {
         AppConfigurationEntry[] entries = Configuration.getConfiguration().getAppConfigurationEntry(loginContext);
         if (entries != null && entries.length != 0) {
            Supplier<CallbackHandler> callbackSupplier = () -> new SaslClientCallbackHandler((String)null, "QuorumLearner");
            this.learnerLogin = new Login(loginContext, callbackSupplier, new ZKConfig());
            this.learnerLogin.startThreadIfNeeded();
         } else {
            throw new LoginException(String.format("SASL-authentication failed because the specified JAAS configuration section '%s' could not be found.", loginContext));
         }
      } catch (LoginException e) {
         throw new SaslException("Failed to initialize authentication mechanism using SASL", e);
      }
   }

   public void authenticate(Socket sock, String hostName) throws IOException {
      if (!this.quorumRequireSasl) {
         LOG.info("Skipping SASL authentication as {}={}", "quorum.auth.learnerRequireSasl", this.quorumRequireSasl);
      } else {
         SaslClient sc = null;
         String principalConfig = SecurityUtils.getServerPrincipal(this.quorumServicePrincipal, hostName);

         try {
            DataOutputStream dout = new DataOutputStream(sock.getOutputStream());
            DataInputStream din = new DataInputStream(sock.getInputStream());
            byte[] responseToken = new byte[0];
            sc = SecurityUtils.createSaslClient(this.learnerLogin.getSubject(), principalConfig, "zookeeper-quorum", "zk-quorum-sasl-md5", LOG, "QuorumLearner");
            if (sc.hasInitialResponse()) {
               responseToken = this.createSaslToken(new byte[0], sc, this.learnerLogin);
            }

            this.send(dout, responseToken);
            QuorumAuthPacket authPacket = this.receive(din);
            QuorumAuth.Status qpStatus = QuorumAuth.Status.getStatus(authPacket.getStatus());

            while(!sc.isComplete()) {
               switch (qpStatus) {
                  case SUCCESS:
                     responseToken = this.createSaslToken(authPacket.getToken(), sc, this.learnerLogin);
                     if (responseToken != null) {
                        throw new SaslException("Protocol error: attempting to send response after completion");
                     }
                     break;
                  case IN_PROGRESS:
                     responseToken = this.createSaslToken(authPacket.getToken(), sc, this.learnerLogin);
                     this.send(dout, responseToken);
                     authPacket = this.receive(din);
                     qpStatus = QuorumAuth.Status.getStatus(authPacket.getStatus());
                     break;
                  case ERROR:
                     throw new SaslException("Authentication failed against server addr: " + sock.getRemoteSocketAddress());
                  default:
                     LOG.warn("Unknown status:{}!", qpStatus);
                     throw new SaslException("Authentication failed against server addr: " + sock.getRemoteSocketAddress());
               }
            }

            this.checkAuthStatus(sock, qpStatus);
         } finally {
            if (sc != null) {
               try {
                  sc.dispose();
               } catch (SaslException e) {
                  LOG.error("SaslClient dispose() failed", e);
               }
            }

         }

      }
   }

   private void checkAuthStatus(Socket sock, QuorumAuth.Status qpStatus) throws SaslException {
      if (qpStatus == QuorumAuth.Status.SUCCESS) {
         LOG.info("Successfully completed the authentication using SASL. server addr: {}, status: {}", sock.getRemoteSocketAddress(), qpStatus);
      } else {
         throw new SaslException("Authentication failed against server addr: " + sock.getRemoteSocketAddress() + ", qpStatus: " + qpStatus);
      }
   }

   private QuorumAuthPacket receive(DataInputStream din) throws IOException {
      QuorumAuthPacket authPacket = new QuorumAuthPacket();
      BinaryInputArchive bia = BinaryInputArchive.getArchive(din);
      authPacket.deserialize(bia, "qpconnect");
      return authPacket;
   }

   private void send(DataOutputStream dout, byte[] response) throws IOException {
      BufferedOutputStream bufferedOutput = new BufferedOutputStream(dout);
      BinaryOutputArchive boa = BinaryOutputArchive.getArchive(bufferedOutput);
      QuorumAuthPacket authPacket = QuorumAuth.createPacket(QuorumAuth.Status.IN_PROGRESS, response);
      boa.writeRecord(authPacket, "qpconnect");
      bufferedOutput.flush();
   }

   private byte[] createSaslToken(final byte[] saslToken, final SaslClient saslClient, Login login) throws SaslException {
      if (saslToken == null) {
         throw new SaslException("Error in authenticating with a Zookeeper Quorum member: the quorum member's saslToken is null.");
      } else if (login.getSubject() != null) {
         synchronized(login) {
            byte[] var10000;
            try {
               byte[] retval = (byte[])Subject.doAs(login.getSubject(), new PrivilegedExceptionAction() {
                  public byte[] run() throws SaslException {
                     SaslQuorumAuthLearner.LOG.debug("saslClient.evaluateChallenge(len={})", saslToken.length);
                     return saslClient.evaluateChallenge(saslToken);
                  }
               });
               var10000 = retval;
            } catch (PrivilegedActionException e) {
               String error = "An error: (" + e + ") occurred when evaluating Zookeeper Quorum Member's received SASL token.";
               String UNKNOWN_SERVER_ERROR_TEXT = "(Mechanism level: Server not found in Kerberos database (7) - UNKNOWN_SERVER)";
               if (e.toString().indexOf("(Mechanism level: Server not found in Kerberos database (7) - UNKNOWN_SERVER)") > -1) {
                  error = error + " This may be caused by Java's being unable to resolve the Zookeeper Quorum Member's hostname correctly. You may want to try to adding '-Dsun.net.spi.nameservice.provider.1=dns,sun' to your server's JVMFLAGS environment.";
               }

               LOG.error(error);
               throw new SaslException(error, e);
            }

            return var10000;
         }
      } else {
         throw new SaslException("Cannot make SASL token without subject defined. For diagnosis, please look for WARNs and ERRORs in your log related to the Login class.");
      }
   }
}
