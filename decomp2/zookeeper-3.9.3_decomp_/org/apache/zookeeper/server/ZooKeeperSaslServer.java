package org.apache.zookeeper.server;

import javax.security.auth.Subject;
import javax.security.sasl.SaslException;
import javax.security.sasl.SaslServer;
import org.apache.zookeeper.Login;
import org.apache.zookeeper.util.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZooKeeperSaslServer {
   public static final String LOGIN_CONTEXT_NAME_KEY = "zookeeper.sasl.serverconfig";
   public static final String DEFAULT_LOGIN_CONTEXT_NAME = "Server";
   private static final Logger LOG = LoggerFactory.getLogger(ZooKeeperSaslServer.class);
   private SaslServer saslServer;

   ZooKeeperSaslServer(Login login) {
      this.saslServer = this.createSaslServer(login);
   }

   private SaslServer createSaslServer(Login login) {
      synchronized(login) {
         Subject subject = login.getSubject();
         return SecurityUtils.createSaslServer(subject, "zookeeper", "zk-sasl-md5", login.newCallbackHandler(), LOG);
      }
   }

   public byte[] evaluateResponse(byte[] response) throws SaslException {
      return this.saslServer.evaluateResponse(response);
   }

   public boolean isComplete() {
      return this.saslServer.isComplete();
   }

   public String getAuthorizationID() {
      return this.saslServer.getAuthorizationID();
   }
}
