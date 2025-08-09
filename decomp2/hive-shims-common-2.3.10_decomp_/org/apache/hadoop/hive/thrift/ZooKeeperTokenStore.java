package org.apache.hadoop.hive.thrift;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.ACLBackgroundPathAndBytesable;
import org.apache.curator.framework.api.ACLProvider;
import org.apache.curator.framework.api.BackgroundPathAndBytesable;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.shims.Utils;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.hadoop.security.token.delegation.AbstractDelegationTokenSecretManager;
import org.apache.hadoop.security.token.delegation.HiveDelegationTokenSupport;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZooKeeperTokenStore implements DelegationTokenStore {
   private static final Logger LOGGER = LoggerFactory.getLogger(ZooKeeperTokenStore.class.getName());
   protected static final String ZK_SEQ_FORMAT = "%010d";
   private static final String NODE_KEYS = "/keys";
   private static final String NODE_TOKENS = "/tokens";
   private String rootNode = "";
   private volatile CuratorFramework zkSession;
   private String zkConnectString;
   private int connectTimeoutMillis;
   private List newNodeAcl;
   private final ACLProvider aclDefaultProvider;
   private HadoopThriftAuthBridge.Server.ServerMode serverMode;
   private final String WHEN_ZK_DSTORE_MSG;
   private Configuration conf;

   protected ZooKeeperTokenStore() {
      this.newNodeAcl = Arrays.asList(new ACL(31, Ids.AUTH_IDS));
      this.aclDefaultProvider = new ACLProvider() {
         public List getDefaultAcl() {
            return ZooKeeperTokenStore.this.newNodeAcl;
         }

         public List getAclForPath(String path) {
            return this.getDefaultAcl();
         }
      };
      this.WHEN_ZK_DSTORE_MSG = "when zookeeper based delegation token storage is enabled(hive.cluster.delegation.token.store.class=" + ZooKeeperTokenStore.class.getName() + ")";
   }

   private CuratorFramework getSession() {
      if (this.zkSession == null || this.zkSession.getState() == CuratorFrameworkState.STOPPED) {
         synchronized(this) {
            if (this.zkSession == null || this.zkSession.getState() == CuratorFrameworkState.STOPPED) {
               this.zkSession = CuratorFrameworkFactory.builder().connectString(this.zkConnectString).connectionTimeoutMs(this.connectTimeoutMillis).aclProvider(this.aclDefaultProvider).retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();
               this.zkSession.start();
            }
         }
      }

      return this.zkSession;
   }

   private void setupJAASConfig(Configuration conf) throws IOException {
      if (!UserGroupInformation.getLoginUser().isFromKeytab()) {
         LOGGER.warn("Login is not from keytab");
      } else {
         String principal;
         String keytab;
         switch (this.serverMode) {
            case METASTORE:
               principal = this.getNonEmptyConfVar(conf, "hive.metastore.kerberos.principal");
               keytab = this.getNonEmptyConfVar(conf, "hive.metastore.kerberos.keytab.file");
               break;
            case HIVESERVER2:
               principal = this.getNonEmptyConfVar(conf, "hive.server2.authentication.kerberos.principal");
               keytab = this.getNonEmptyConfVar(conf, "hive.server2.authentication.kerberos.keytab");
               break;
            default:
               throw new AssertionError("Unexpected server mode " + this.serverMode);
         }

         Utils.setZookeeperClientKerberosJaasConfig(principal, keytab);
      }
   }

   private String getNonEmptyConfVar(Configuration conf, String param) throws IOException {
      String val = conf.get(param);
      if (val != null && !val.trim().isEmpty()) {
         return val;
      } else {
         throw new IOException("Configuration parameter " + param + " should be set, " + this.WHEN_ZK_DSTORE_MSG);
      }
   }

   public void ensurePath(String path, List acl) throws DelegationTokenStore.TokenStoreException {
      try {
         CuratorFramework zk = this.getSession();
         String node = (String)((BackgroundPathAndBytesable)((ACLBackgroundPathAndBytesable)zk.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT)).withACL(acl)).forPath(path);
         LOGGER.info("Created path: {} ", node);
      } catch (KeeperException.NodeExistsException var5) {
      } catch (Exception e) {
         throw new DelegationTokenStore.TokenStoreException("Error creating path " + path, e);
      }

   }

   public static int getPermFromString(String permString) {
      int perm = 0;

      for(int i = 0; i < permString.length(); ++i) {
         switch (permString.charAt(i)) {
            case 'a':
               perm |= 16;
               break;
            case 'c':
               perm |= 4;
               break;
            case 'd':
               perm |= 8;
               break;
            case 'r':
               perm |= 1;
               break;
            case 'w':
               perm |= 2;
               break;
            default:
               LOGGER.error("Unknown perm type: " + permString.charAt(i));
         }
      }

      return perm;
   }

   public static List parseACLs(String aclString) {
      String[] aclComps = StringUtils.splitByWholeSeparator(aclString, ",");
      List<ACL> acl = new ArrayList(aclComps.length);

      for(String a : aclComps) {
         if (!StringUtils.isBlank(a)) {
            a = a.trim();
            int firstColon = a.indexOf(58);
            int lastColon = a.lastIndexOf(58);
            if (firstColon != -1 && lastColon != -1 && firstColon != lastColon) {
               ACL newAcl = new ACL();
               newAcl.setId(new Id(a.substring(0, firstColon), a.substring(firstColon + 1, lastColon)));
               newAcl.setPerms(getPermFromString(a.substring(lastColon + 1)));
               acl.add(newAcl);
            } else {
               LOGGER.error(a + " does not have the form scheme:id:perm");
            }
         }
      }

      return acl;
   }

   private void initClientAndPaths() {
      if (this.zkSession != null) {
         this.zkSession.close();
      }

      try {
         this.ensurePath(this.rootNode + "/keys", this.newNodeAcl);
         this.ensurePath(this.rootNode + "/tokens", this.newNodeAcl);
      } catch (DelegationTokenStore.TokenStoreException e) {
         throw e;
      }
   }

   public void setConf(Configuration conf) {
      if (conf == null) {
         throw new IllegalArgumentException("conf is null");
      } else {
         this.conf = conf;
      }
   }

   public Configuration getConf() {
      return null;
   }

   private Map getAllKeys() throws KeeperException, InterruptedException {
      String masterKeyNode = this.rootNode + "/keys";
      List<String> nodes = this.zkGetChildren(masterKeyNode);
      Map<Integer, byte[]> result = new HashMap();

      for(String node : nodes) {
         String nodePath = masterKeyNode + "/" + node;
         byte[] data = this.zkGetData(nodePath);
         if (data != null) {
            result.put(this.getSeq(node), data);
         }
      }

      return result;
   }

   private List zkGetChildren(String path) {
      CuratorFramework zk = this.getSession();

      try {
         return (List)zk.getChildren().forPath(path);
      } catch (Exception e) {
         throw new DelegationTokenStore.TokenStoreException("Error getting children for " + path, e);
      }
   }

   private byte[] zkGetData(String nodePath) {
      CuratorFramework zk = this.getSession();

      try {
         return (byte[])zk.getData().forPath(nodePath);
      } catch (KeeperException.NoNodeException var4) {
         return null;
      } catch (Exception e) {
         throw new DelegationTokenStore.TokenStoreException("Error reading " + nodePath, e);
      }
   }

   private int getSeq(String path) {
      String[] pathComps = path.split("/");
      return Integer.parseInt(pathComps[pathComps.length - 1]);
   }

   public int addMasterKey(String s) {
      String keysPath = this.rootNode + "/keys" + "/";
      CuratorFramework zk = this.getSession();

      String newNode;
      try {
         newNode = (String)((BackgroundPathAndBytesable)((ACLBackgroundPathAndBytesable)zk.create().withMode(CreateMode.PERSISTENT_SEQUENTIAL)).withACL(this.newNodeAcl)).forPath(keysPath, s.getBytes());
      } catch (Exception e) {
         throw new DelegationTokenStore.TokenStoreException("Error creating new node with path " + keysPath, e);
      }

      LOGGER.info("Added key {}", newNode);
      return this.getSeq(newNode);
   }

   public void updateMasterKey(int keySeq, String s) {
      CuratorFramework zk = this.getSession();
      String keyPath = this.rootNode + "/keys" + "/" + String.format("%010d", keySeq);

      try {
         zk.setData().forPath(keyPath, s.getBytes());
      } catch (Exception e) {
         throw new DelegationTokenStore.TokenStoreException("Error setting data in " + keyPath, e);
      }
   }

   public boolean removeMasterKey(int keySeq) {
      String keyPath = this.rootNode + "/keys" + "/" + String.format("%010d", keySeq);
      this.zkDelete(keyPath);
      return true;
   }

   private void zkDelete(String path) {
      CuratorFramework zk = this.getSession();

      try {
         zk.delete().forPath(path);
      } catch (KeeperException.NoNodeException var4) {
      } catch (Exception e) {
         throw new DelegationTokenStore.TokenStoreException("Error deleting " + path, e);
      }

   }

   public String[] getMasterKeys() {
      try {
         Map<Integer, byte[]> allKeys = this.getAllKeys();
         String[] result = new String[allKeys.size()];
         int resultIdx = 0;

         for(byte[] keyBytes : allKeys.values()) {
            result[resultIdx++] = new String(keyBytes);
         }

         return result;
      } catch (KeeperException ex) {
         throw new DelegationTokenStore.TokenStoreException(ex);
      } catch (InterruptedException ex) {
         throw new DelegationTokenStore.TokenStoreException(ex);
      }
   }

   private String getTokenPath(DelegationTokenIdentifier tokenIdentifier) {
      try {
         return this.rootNode + "/tokens" + "/" + TokenStoreDelegationTokenSecretManager.encodeWritable(tokenIdentifier);
      } catch (IOException ex) {
         throw new DelegationTokenStore.TokenStoreException("Failed to encode token identifier", ex);
      }
   }

   public boolean addToken(DelegationTokenIdentifier tokenIdentifier, AbstractDelegationTokenSecretManager.DelegationTokenInformation token) {
      byte[] tokenBytes = HiveDelegationTokenSupport.encodeDelegationTokenInformation(token);
      String tokenPath = this.getTokenPath(tokenIdentifier);
      CuratorFramework zk = this.getSession();

      String newNode;
      try {
         newNode = (String)((BackgroundPathAndBytesable)((ACLBackgroundPathAndBytesable)zk.create().withMode(CreateMode.PERSISTENT)).withACL(this.newNodeAcl)).forPath(tokenPath, tokenBytes);
      } catch (Exception e) {
         throw new DelegationTokenStore.TokenStoreException("Error creating new node with path " + tokenPath, e);
      }

      LOGGER.info("Added token: {}", newNode);
      return true;
   }

   public boolean removeToken(DelegationTokenIdentifier tokenIdentifier) {
      String tokenPath = this.getTokenPath(tokenIdentifier);
      this.zkDelete(tokenPath);
      return true;
   }

   public AbstractDelegationTokenSecretManager.DelegationTokenInformation getToken(DelegationTokenIdentifier tokenIdentifier) {
      byte[] tokenBytes = this.zkGetData(this.getTokenPath(tokenIdentifier));
      if (tokenBytes == null) {
         return null;
      } else {
         try {
            return HiveDelegationTokenSupport.decodeDelegationTokenInformation(tokenBytes);
         } catch (Exception ex) {
            throw new DelegationTokenStore.TokenStoreException("Failed to decode token", ex);
         }
      }
   }

   public List getAllDelegationTokenIdentifiers() {
      String containerNode = this.rootNode + "/tokens";
      List<String> nodes = this.zkGetChildren(containerNode);
      List<DelegationTokenIdentifier> result = new ArrayList(nodes.size());

      for(String node : nodes) {
         DelegationTokenIdentifier id = new DelegationTokenIdentifier();

         try {
            TokenStoreDelegationTokenSecretManager.decodeWritable(id, node);
            result.add(id);
         } catch (Exception var8) {
            LOGGER.warn("Failed to decode token '{}'", node);
         }
      }

      return result;
   }

   public void close() throws IOException {
      if (this.zkSession != null) {
         this.zkSession.close();
      }

   }

   public void init(Object hmsHandler, HadoopThriftAuthBridge.Server.ServerMode smode) {
      this.serverMode = smode;
      this.zkConnectString = this.conf.get("hive.cluster.delegation.token.store.zookeeper.connectString", (String)null);
      if (this.zkConnectString == null || this.zkConnectString.trim().isEmpty()) {
         this.zkConnectString = this.conf.get("hive.zookeeper.quorum", (String)null);
         if (this.zkConnectString == null || this.zkConnectString.trim().isEmpty()) {
            throw new IllegalArgumentException("Zookeeper connect string has to be specifed through either hive.cluster.delegation.token.store.zookeeper.connectString or hive.zookeeper.quorum" + this.WHEN_ZK_DSTORE_MSG);
         }
      }

      this.connectTimeoutMillis = this.conf.getInt("hive.cluster.delegation.token.store.zookeeper.connectTimeoutMillis", CuratorFrameworkFactory.builder().getConnectionTimeoutMs());
      String aclStr = this.conf.get("hive.cluster.delegation.token.store.zookeeper.acl", (String)null);
      if (StringUtils.isNotBlank(aclStr)) {
         this.newNodeAcl = parseACLs(aclStr);
      }

      this.rootNode = this.conf.get("hive.cluster.delegation.token.store.zookeeper.znode", "/hivedelegation") + this.serverMode;

      try {
         this.setupJAASConfig(this.conf);
      } catch (IOException e) {
         throw new DelegationTokenStore.TokenStoreException("Error setting up JAAS configuration for zookeeper client " + e.getMessage(), e);
      }

      this.initClientAndPaths();
   }
}
