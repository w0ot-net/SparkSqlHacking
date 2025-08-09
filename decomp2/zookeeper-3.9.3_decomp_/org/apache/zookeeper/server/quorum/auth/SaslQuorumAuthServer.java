package org.apache.zookeeper.server.quorum.auth;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Set;
import java.util.function.Supplier;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.AppConfigurationEntry;
import javax.security.auth.login.Configuration;
import javax.security.auth.login.LoginException;
import javax.security.sasl.SaslException;
import javax.security.sasl.SaslServer;
import org.apache.jute.BinaryInputArchive;
import org.apache.jute.BinaryOutputArchive;
import org.apache.zookeeper.Login;
import org.apache.zookeeper.common.ZKConfig;
import org.apache.zookeeper.server.quorum.QuorumAuthPacket;
import org.apache.zookeeper.util.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SaslQuorumAuthServer implements QuorumAuthServer {
   private static final Logger LOG = LoggerFactory.getLogger(SaslQuorumAuthServer.class);
   private static final int MAX_RETRIES = 5;
   private final Login serverLogin;
   private final boolean quorumRequireSasl;

   public SaslQuorumAuthServer(boolean quorumRequireSasl, String loginContext, Set authzHosts) throws SaslException {
      this.quorumRequireSasl = quorumRequireSasl;

      try {
         AppConfigurationEntry[] entries = Configuration.getConfiguration().getAppConfigurationEntry(loginContext);
         if (entries != null && entries.length != 0) {
            Supplier<CallbackHandler> callbackSupplier = () -> new SaslQuorumServerCallbackHandler(entries, authzHosts);
            this.serverLogin = new Login(loginContext, callbackSupplier, new ZKConfig());
            this.serverLogin.startThreadIfNeeded();
         } else {
            throw new LoginException(String.format("SASL-authentication failed because the specified JAAS configuration section '%s' could not be found.", loginContext));
         }
      } catch (Throwable e) {
         throw new SaslException("Failed to initialize authentication mechanism using SASL", e);
      }
   }

   public void authenticate(Socket sock, DataInputStream din) throws SaslException {
      DataOutputStream dout = null;
      SaslServer ss = null;

      try {
         if (QuorumAuth.nextPacketIsAuth(din)) {
            byte[] token = this.receive(din);
            int tries = 0;
            dout = new DataOutputStream(sock.getOutputStream());
            byte[] challenge = null;
            ss = SecurityUtils.createSaslServer(this.serverLogin.getSubject(), "zookeeper-quorum", "zk-quorum-sasl-md5", this.serverLogin.newCallbackHandler(), LOG);

            while(!ss.isComplete()) {
               challenge = ss.evaluateResponse(token);
               if (!ss.isComplete()) {
                  ++tries;
                  if (tries > 5) {
                     this.send(dout, challenge, QuorumAuth.Status.ERROR);
                     LOG.warn("Failed to authenticate using SASL, server addr: {}, retries={} exceeded.", sock.getRemoteSocketAddress(), tries);
                     break;
                  }

                  this.send(dout, challenge, QuorumAuth.Status.IN_PROGRESS);
                  token = this.receive(din);
               }
            }

            if (ss.isComplete()) {
               this.send(dout, challenge, QuorumAuth.Status.SUCCESS);
               LOG.info("Successfully completed the authentication using SASL. learner addr: {}", sock.getRemoteSocketAddress());
            }

            return;
         }

         if (this.quorumRequireSasl) {
            throw new SaslException("Learner not trying to authenticate and authentication is required");
         }
      } catch (Exception e) {
         try {
            if (dout != null) {
               this.send(dout, new byte[0], QuorumAuth.Status.ERROR);
            }
         } catch (IOException ioe) {
            LOG.warn("Exception while sending failed status", ioe);
         }

         if (this.quorumRequireSasl) {
            LOG.error("Failed to authenticate using SASL", e);
            throw new SaslException("Failed to authenticate using SASL: " + e.getMessage());
         }

         LOG.warn("Failed to authenticate using SASL", e);
         LOG.warn("Maintaining learner connection despite SASL authentication failure. server addr: {}, {}: {}", new Object[]{sock.getRemoteSocketAddress(), "quorum.auth.serverRequireSasl", this.quorumRequireSasl});
         return;
      } finally {
         if (ss != null) {
            try {
               ss.dispose();
            } catch (SaslException e) {
               LOG.error("SaslServer dispose() failed", e);
            }
         }

      }

   }

   private byte[] receive(DataInputStream din) throws IOException {
      QuorumAuthPacket authPacket = new QuorumAuthPacket();
      BinaryInputArchive bia = BinaryInputArchive.getArchive(din);
      authPacket.deserialize(bia, "qpconnect");
      return authPacket.getToken();
   }

   private void send(DataOutputStream dout, byte[] challenge, QuorumAuth.Status s) throws IOException {
      BufferedOutputStream bufferedOutput = new BufferedOutputStream(dout);
      BinaryOutputArchive boa = BinaryOutputArchive.getArchive(bufferedOutput);
      QuorumAuthPacket authPacket;
      if (challenge == null && s != QuorumAuth.Status.SUCCESS) {
         authPacket = QuorumAuth.createPacket(QuorumAuth.Status.IN_PROGRESS, (byte[])null);
      } else {
         authPacket = QuorumAuth.createPacket(s, challenge);
      }

      boa.writeRecord(authPacket, "qpconnect");
      bufferedOutput.flush();
   }
}
