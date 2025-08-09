package org.apache.hive.service.cli.session;

import java.io.IOException;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.ql.metadata.Hive;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.shims.Utils;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.hive.service.auth.HiveAuthFactory;
import org.apache.hive.service.cli.HiveSQLException;
import org.apache.hive.service.rpc.thrift.TProtocolVersion;

public class HiveSessionImplwithUGI extends HiveSessionImpl {
   public static final String HS2TOKEN = "HiveServer2ImpersonationToken";
   private UserGroupInformation sessionUgi = null;
   private String delegationTokenStr = null;
   private HiveSession proxySession = null;

   public HiveSessionImplwithUGI(TProtocolVersion protocol, String username, String password, HiveConf hiveConf, String ipAddress, String delegationToken) throws HiveSQLException {
      super(protocol, username, password, hiveConf, ipAddress);
      this.setSessionUGI(username);
      this.setDelegationToken(delegationToken);
   }

   public void setSessionUGI(String owner) throws HiveSQLException {
      if (owner == null) {
         throw new HiveSQLException("No username provided for impersonation");
      } else {
         if (UserGroupInformation.isSecurityEnabled()) {
            try {
               this.sessionUgi = UserGroupInformation.createProxyUser(owner, UserGroupInformation.getLoginUser());
            } catch (IOException e) {
               throw new HiveSQLException("Couldn't setup proxy user", e);
            }
         } else {
            this.sessionUgi = UserGroupInformation.createRemoteUser(owner);
         }

      }
   }

   public UserGroupInformation getSessionUgi() {
      return this.sessionUgi;
   }

   public String getDelegationToken() {
      return this.delegationTokenStr;
   }

   public void close() throws HiveSQLException {
      try {
         this.acquire(true);
         this.cancelDelegationToken();
      } finally {
         try {
            super.close();
         } finally {
            try {
               FileSystem.closeAllForUGI(this.sessionUgi);
            } catch (IOException ioe) {
               throw new HiveSQLException("Could not clean up file-system handles for UGI: " + String.valueOf(this.sessionUgi), ioe);
            }
         }
      }

   }

   private void setDelegationToken(String delegationTokenStr) throws HiveSQLException {
      this.delegationTokenStr = delegationTokenStr;
      if (delegationTokenStr != null) {
         this.getHiveConf().set("hive.metastore.token.signature", "HiveServer2ImpersonationToken");

         try {
            Utils.setTokenStr(this.sessionUgi, delegationTokenStr, "HiveServer2ImpersonationToken");
         } catch (IOException e) {
            throw new HiveSQLException("Couldn't setup delegation token in the ugi", e);
         }
      }

   }

   private void cancelDelegationToken() throws HiveSQLException {
      if (this.delegationTokenStr != null) {
         try {
            Hive.getWithoutRegisterFns(this.getHiveConf()).cancelDelegationToken(this.delegationTokenStr);
         } catch (HiveException e) {
            throw new HiveSQLException("Couldn't cancel delegation token", e);
         }

         Hive.closeCurrent();
      }

   }

   protected HiveSession getSession() {
      assert this.proxySession != null;

      return this.proxySession;
   }

   public void setProxySession(HiveSession proxySession) {
      this.proxySession = proxySession;
   }

   public String getDelegationToken(HiveAuthFactory authFactory, String owner, String renewer) throws HiveSQLException {
      return authFactory.getDelegationToken(owner, renewer, this.getIpAddress());
   }

   public void cancelDelegationToken(HiveAuthFactory authFactory, String tokenStr) throws HiveSQLException {
      authFactory.cancelDelegationToken(tokenStr);
   }

   public void renewDelegationToken(HiveAuthFactory authFactory, String tokenStr) throws HiveSQLException {
      authFactory.renewDelegationToken(tokenStr);
   }
}
