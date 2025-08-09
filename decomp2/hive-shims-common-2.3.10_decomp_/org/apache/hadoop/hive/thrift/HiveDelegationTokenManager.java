package org.apache.hadoop.hive.thrift;

import java.io.IOException;
import java.security.PrivilegedExceptionAction;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.shims.Utils;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.hadoop.security.authorize.ProxyUsers;
import org.apache.hadoop.util.ReflectionUtils;

public class HiveDelegationTokenManager {
   public static final String DELEGATION_TOKEN_GC_INTERVAL = "hive.cluster.delegation.token.gc-interval";
   private static final long DELEGATION_TOKEN_GC_INTERVAL_DEFAULT = 3600000L;
   public static final String DELEGATION_KEY_UPDATE_INTERVAL_KEY = "hive.cluster.delegation.key.update-interval";
   public static final long DELEGATION_KEY_UPDATE_INTERVAL_DEFAULT = 86400000L;
   public static final String DELEGATION_TOKEN_RENEW_INTERVAL_KEY = "hive.cluster.delegation.token.renew-interval";
   public static final long DELEGATION_TOKEN_RENEW_INTERVAL_DEFAULT = 86400000L;
   public static final String DELEGATION_TOKEN_MAX_LIFETIME_KEY = "hive.cluster.delegation.token.max-lifetime";
   public static final long DELEGATION_TOKEN_MAX_LIFETIME_DEFAULT = 604800000L;
   public static final String DELEGATION_TOKEN_STORE_CLS = "hive.cluster.delegation.token.store.class";
   public static final String DELEGATION_TOKEN_STORE_ZK_CONNECT_STR = "hive.cluster.delegation.token.store.zookeeper.connectString";
   public static final String DELEGATION_TOKEN_STORE_ZK_CONNECT_STR_ALTERNATE = "hive.zookeeper.quorum";
   public static final String DELEGATION_TOKEN_STORE_ZK_CONNECT_TIMEOUTMILLIS = "hive.cluster.delegation.token.store.zookeeper.connectTimeoutMillis";
   public static final String DELEGATION_TOKEN_STORE_ZK_ZNODE = "hive.cluster.delegation.token.store.zookeeper.znode";
   public static final String DELEGATION_TOKEN_STORE_ZK_ACL = "hive.cluster.delegation.token.store.zookeeper.acl";
   public static final String DELEGATION_TOKEN_STORE_ZK_ZNODE_DEFAULT = "/hivedelegation";
   protected DelegationTokenSecretManager secretManager;

   public DelegationTokenSecretManager getSecretManager() {
      return this.secretManager;
   }

   public void startDelegationTokenSecretManager(Configuration conf, Object hms, HadoopThriftAuthBridge.Server.ServerMode smode) throws IOException {
      long secretKeyInterval = conf.getLong("hive.cluster.delegation.key.update-interval", 86400000L);
      long tokenMaxLifetime = conf.getLong("hive.cluster.delegation.token.max-lifetime", 604800000L);
      long tokenRenewInterval = conf.getLong("hive.cluster.delegation.token.renew-interval", 86400000L);
      long tokenGcInterval = conf.getLong("hive.cluster.delegation.token.gc-interval", 3600000L);
      DelegationTokenStore dts = this.getTokenStore(conf);
      dts.setConf(conf);
      dts.init(hms, smode);
      this.secretManager = new TokenStoreDelegationTokenSecretManager(secretKeyInterval, tokenMaxLifetime, tokenRenewInterval, tokenGcInterval, dts);
      this.secretManager.startThreads();
   }

   public String getDelegationToken(String owner, final String renewer, String remoteAddr) throws IOException, InterruptedException {
      UserGroupInformation currUser = UserGroupInformation.getCurrentUser();
      UserGroupInformation ownerUgi = UserGroupInformation.createRemoteUser(owner);
      if (!ownerUgi.getShortUserName().equals(currUser.getShortUserName())) {
         ownerUgi = UserGroupInformation.createProxyUser(owner, UserGroupInformation.getCurrentUser());
         ProxyUsers.authorize(ownerUgi, remoteAddr, (Configuration)null);
      }

      return (String)ownerUgi.doAs(new PrivilegedExceptionAction() {
         public String run() throws IOException {
            return HiveDelegationTokenManager.this.secretManager.getDelegationToken(renewer);
         }
      });
   }

   public String getDelegationTokenWithService(String owner, String renewer, String service, String remoteAddr) throws IOException, InterruptedException {
      String token = this.getDelegationToken(owner, renewer, remoteAddr);
      return Utils.addServiceToToken(token, service);
   }

   public long renewDelegationToken(String tokenStrForm) throws IOException {
      return this.secretManager.renewDelegationToken(tokenStrForm);
   }

   public String getUserFromToken(String tokenStr) throws IOException {
      return this.secretManager.getUserFromToken(tokenStr);
   }

   public void cancelDelegationToken(String tokenStrForm) throws IOException {
      this.secretManager.cancelDelegationToken(tokenStrForm);
   }

   public String verifyDelegationToken(String tokenStrForm) throws IOException {
      return this.secretManager.verifyDelegationToken(tokenStrForm);
   }

   private DelegationTokenStore getTokenStore(Configuration conf) throws IOException {
      String tokenStoreClassName = conf.get("hive.cluster.delegation.token.store.class", "");
      if (StringUtils.isBlank(tokenStoreClassName)) {
         return new MemoryTokenStore();
      } else {
         try {
            Class<? extends DelegationTokenStore> storeClass = Class.forName(tokenStoreClassName).asSubclass(DelegationTokenStore.class);
            return (DelegationTokenStore)ReflectionUtils.newInstance(storeClass, conf);
         } catch (ClassNotFoundException e) {
            throw new IOException("Error initializing delegation token store: " + tokenStoreClassName, e);
         }
      }
   }
}
