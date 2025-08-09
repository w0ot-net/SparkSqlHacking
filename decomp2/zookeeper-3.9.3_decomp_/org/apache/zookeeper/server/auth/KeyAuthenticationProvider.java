package org.apache.zookeeper.server.auth;

import java.nio.charset.StandardCharsets;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.server.ZKDatabase;
import org.apache.zookeeper.server.ZooKeeperServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KeyAuthenticationProvider extends ServerAuthenticationProvider {
   private static final Logger LOG = LoggerFactory.getLogger(KeyAuthenticationProvider.class);

   public String getScheme() {
      return "key";
   }

   private byte[] getKey(ZooKeeperServer zks) {
      ZKDatabase db = zks.getZKDatabase();
      if (db != null) {
         try {
            Stat stat = new Stat();
            return db.getData("/key", stat, (Watcher)null);
         } catch (KeeperException.NoNodeException e) {
            LOG.error("getData failed", e);
         }
      }

      return null;
   }

   private boolean validate(byte[] key, byte[] auth) {
      try {
         String keyStr = new String(key, StandardCharsets.UTF_8);
         String authStr = new String(auth, StandardCharsets.UTF_8);
         int keyVal = Integer.parseInt(keyStr);
         int authVal = Integer.parseInt(authStr);
         return keyVal == 0 || authVal % keyVal == 0;
      } catch (NumberFormatException nfe) {
         LOG.error("bad formatting", nfe);
         return false;
      }
   }

   public KeeperException.Code handleAuthentication(ServerAuthenticationProvider.ServerObjs serverObjs, byte[] authData) {
      byte[] key = this.getKey(serverObjs.getZks());
      String authStr = new String(authData, StandardCharsets.UTF_8);
      String keyStr = "";
      if (key != null && !this.validate(key, authData)) {
         keyStr = new String(key, StandardCharsets.UTF_8);
         LOG.debug("KeyAuthenticationProvider handleAuthentication ({}, {}) -> FAIL.\n", keyStr, authStr);
         return KeeperException.Code.AUTHFAILED;
      } else {
         LOG.debug("KeyAuthenticationProvider handleAuthentication -> OK.\n");
         serverObjs.getCnxn().addAuthInfo(new Id(this.getScheme(), keyStr));
         return KeeperException.Code.OK;
      }
   }

   public boolean matches(ServerAuthenticationProvider.ServerObjs serverObjs, ServerAuthenticationProvider.MatchValues matchValues) {
      return matchValues.getId().equals(matchValues.getAclExpr());
   }

   public boolean isAuthenticated() {
      return true;
   }

   public boolean isValid(String id) {
      return true;
   }
}
