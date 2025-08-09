package org.apache.zookeeper.server.auth;

import java.util.List;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.server.ServerCnxn;
import org.apache.zookeeper.server.ZooKeeperServer;

public abstract class ServerAuthenticationProvider implements AuthenticationProvider {
   public abstract KeeperException.Code handleAuthentication(ServerObjs var1, byte[] var2);

   public abstract boolean matches(ServerObjs var1, MatchValues var2);

   public final KeeperException.Code handleAuthentication(ServerCnxn cnxn, byte[] authData) {
      throw new UnsupportedOperationException();
   }

   public final boolean matches(String id, String aclExpr) {
      throw new UnsupportedOperationException();
   }

   public static class ServerObjs {
      private final ZooKeeperServer zks;
      private final ServerCnxn cnxn;

      public ServerObjs(ZooKeeperServer zks, ServerCnxn cnxn) {
         this.zks = zks;
         this.cnxn = cnxn;
      }

      public ZooKeeperServer getZks() {
         return this.zks;
      }

      public ServerCnxn getCnxn() {
         return this.cnxn;
      }
   }

   public static class MatchValues {
      private final String path;
      private final String id;
      private final String aclExpr;
      private final int perm;
      private final List setAcls;

      public MatchValues(String path, String id, String aclExpr, int perm, List setAcls) {
         this.path = path;
         this.id = id;
         this.aclExpr = aclExpr;
         this.perm = perm;
         this.setAcls = setAcls;
      }

      public String getPath() {
         return this.path;
      }

      public String getId() {
         return this.id;
      }

      public String getAclExpr() {
         return this.aclExpr;
      }

      public int getPerm() {
         return this.perm;
      }

      public List getSetAcls() {
         return this.setAcls;
      }
   }
}
