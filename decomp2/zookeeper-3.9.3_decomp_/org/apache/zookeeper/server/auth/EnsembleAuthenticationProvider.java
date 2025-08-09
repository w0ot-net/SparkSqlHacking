package org.apache.zookeeper.server.auth;

import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.server.ServerCnxn;
import org.apache.zookeeper.server.ServerMetrics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnsembleAuthenticationProvider implements AuthenticationProvider {
   private static final Logger LOG = LoggerFactory.getLogger(EnsembleAuthenticationProvider.class);
   public static final String ENSEMBLE_PROPERTY = "zookeeper.ensembleAuthName";
   private static final int MIN_LOGGING_INTERVAL_MS = 1000;
   private Set ensembleNames;
   private long lastFailureLogged;

   public EnsembleAuthenticationProvider() {
      String namesCSV = System.getProperty("zookeeper.ensembleAuthName");
      if (namesCSV != null) {
         LOG.info("Set expected ensemble names to {}", namesCSV);
         this.setEnsembleNames(namesCSV);
      }

   }

   public void setEnsembleNames(String namesCSV) {
      this.ensembleNames = new HashSet();

      for(String name : namesCSV.split(",")) {
         this.ensembleNames.add(name.trim());
      }

   }

   public String getScheme() {
      return "ensemble";
   }

   public KeeperException.Code handleAuthentication(ServerCnxn cnxn, byte[] authData) {
      if (authData != null && authData.length != 0) {
         String receivedEnsembleName = new String(authData, StandardCharsets.UTF_8);
         if (this.ensembleNames == null) {
            ServerMetrics.getMetrics().ENSEMBLE_AUTH_SKIP.add(1L);
            return KeeperException.Code.OK;
         } else if (this.ensembleNames.contains(receivedEnsembleName)) {
            ServerMetrics.getMetrics().ENSEMBLE_AUTH_SUCCESS.add(1L);
            return KeeperException.Code.OK;
         } else {
            long currentTime = System.currentTimeMillis();
            if (this.lastFailureLogged + 1000L < currentTime) {
               String id = cnxn.getRemoteSocketAddress().getAddress().getHostAddress();
               LOG.warn("Unexpected ensemble name: ensemble name: {} client ip: {}", receivedEnsembleName, id);
               this.lastFailureLogged = currentTime;
            }

            ServerMetrics.getMetrics().ENSEMBLE_AUTH_FAIL.add(1L);
            cnxn.close(ServerCnxn.DisconnectReason.FAILED_HANDSHAKE);
            return KeeperException.Code.BADARGUMENTS;
         }
      } else {
         ServerMetrics.getMetrics().ENSEMBLE_AUTH_SKIP.add(1L);
         return KeeperException.Code.OK;
      }
   }

   public boolean matches(String id, String aclExpr) {
      return false;
   }

   public boolean isAuthenticated() {
      return false;
   }

   public boolean isValid(String id) {
      return false;
   }
}
