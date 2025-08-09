package org.apache.hadoop.hive.metastore;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Lists;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import javax.security.auth.login.LoginException;
import org.apache.hadoop.hive.common.ObjectPair;
import org.apache.hadoop.hive.common.ValidTxnList;
import org.apache.hadoop.hive.common.auth.HiveAuthUtils;
import org.apache.hadoop.hive.common.classification.InterfaceAudience.LimitedPrivate;
import org.apache.hadoop.hive.common.classification.InterfaceAudience.Public;
import org.apache.hadoop.hive.common.classification.InterfaceStability.Unstable;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.conf.HiveConfUtil;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.apache.hadoop.hive.metastore.api.AbortTxnRequest;
import org.apache.hadoop.hive.metastore.api.AbortTxnsRequest;
import org.apache.hadoop.hive.metastore.api.AddDynamicPartitions;
import org.apache.hadoop.hive.metastore.api.AddForeignKeyRequest;
import org.apache.hadoop.hive.metastore.api.AddPartitionsRequest;
import org.apache.hadoop.hive.metastore.api.AddPartitionsResult;
import org.apache.hadoop.hive.metastore.api.AddPrimaryKeyRequest;
import org.apache.hadoop.hive.metastore.api.AggrStats;
import org.apache.hadoop.hive.metastore.api.AlreadyExistsException;
import org.apache.hadoop.hive.metastore.api.CacheFileMetadataRequest;
import org.apache.hadoop.hive.metastore.api.CacheFileMetadataResult;
import org.apache.hadoop.hive.metastore.api.CheckLockRequest;
import org.apache.hadoop.hive.metastore.api.ClearFileMetadataRequest;
import org.apache.hadoop.hive.metastore.api.ClientCapabilities;
import org.apache.hadoop.hive.metastore.api.ClientCapability;
import org.apache.hadoop.hive.metastore.api.ColumnStatistics;
import org.apache.hadoop.hive.metastore.api.CommitTxnRequest;
import org.apache.hadoop.hive.metastore.api.CompactionRequest;
import org.apache.hadoop.hive.metastore.api.CompactionResponse;
import org.apache.hadoop.hive.metastore.api.CompactionType;
import org.apache.hadoop.hive.metastore.api.ConfigValSecurityException;
import org.apache.hadoop.hive.metastore.api.CurrentNotificationEventId;
import org.apache.hadoop.hive.metastore.api.DataOperationType;
import org.apache.hadoop.hive.metastore.api.Database;
import org.apache.hadoop.hive.metastore.api.DropConstraintRequest;
import org.apache.hadoop.hive.metastore.api.DropPartitionsExpr;
import org.apache.hadoop.hive.metastore.api.DropPartitionsRequest;
import org.apache.hadoop.hive.metastore.api.EnvironmentContext;
import org.apache.hadoop.hive.metastore.api.FieldSchema;
import org.apache.hadoop.hive.metastore.api.FireEventRequest;
import org.apache.hadoop.hive.metastore.api.FireEventResponse;
import org.apache.hadoop.hive.metastore.api.ForeignKeysRequest;
import org.apache.hadoop.hive.metastore.api.Function;
import org.apache.hadoop.hive.metastore.api.GetAllFunctionsResponse;
import org.apache.hadoop.hive.metastore.api.GetFileMetadataByExprRequest;
import org.apache.hadoop.hive.metastore.api.GetFileMetadataByExprResult;
import org.apache.hadoop.hive.metastore.api.GetFileMetadataRequest;
import org.apache.hadoop.hive.metastore.api.GetFileMetadataResult;
import org.apache.hadoop.hive.metastore.api.GetOpenTxnsInfoResponse;
import org.apache.hadoop.hive.metastore.api.GetPrincipalsInRoleRequest;
import org.apache.hadoop.hive.metastore.api.GetPrincipalsInRoleResponse;
import org.apache.hadoop.hive.metastore.api.GetRoleGrantsForPrincipalRequest;
import org.apache.hadoop.hive.metastore.api.GetRoleGrantsForPrincipalResponse;
import org.apache.hadoop.hive.metastore.api.GrantRevokePrivilegeRequest;
import org.apache.hadoop.hive.metastore.api.GrantRevokePrivilegeResponse;
import org.apache.hadoop.hive.metastore.api.GrantRevokeRoleRequest;
import org.apache.hadoop.hive.metastore.api.GrantRevokeRoleResponse;
import org.apache.hadoop.hive.metastore.api.GrantRevokeType;
import org.apache.hadoop.hive.metastore.api.HeartbeatRequest;
import org.apache.hadoop.hive.metastore.api.HeartbeatTxnRangeRequest;
import org.apache.hadoop.hive.metastore.api.HeartbeatTxnRangeResponse;
import org.apache.hadoop.hive.metastore.api.HiveObjectRef;
import org.apache.hadoop.hive.metastore.api.Index;
import org.apache.hadoop.hive.metastore.api.InvalidInputException;
import org.apache.hadoop.hive.metastore.api.InvalidObjectException;
import org.apache.hadoop.hive.metastore.api.InvalidOperationException;
import org.apache.hadoop.hive.metastore.api.InvalidPartitionException;
import org.apache.hadoop.hive.metastore.api.LockRequest;
import org.apache.hadoop.hive.metastore.api.LockResponse;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.metastore.api.NoSuchLockException;
import org.apache.hadoop.hive.metastore.api.NoSuchObjectException;
import org.apache.hadoop.hive.metastore.api.NoSuchTxnException;
import org.apache.hadoop.hive.metastore.api.NotificationEvent;
import org.apache.hadoop.hive.metastore.api.NotificationEventRequest;
import org.apache.hadoop.hive.metastore.api.NotificationEventResponse;
import org.apache.hadoop.hive.metastore.api.OpenTxnRequest;
import org.apache.hadoop.hive.metastore.api.OpenTxnsResponse;
import org.apache.hadoop.hive.metastore.api.Partition;
import org.apache.hadoop.hive.metastore.api.PartitionEventType;
import org.apache.hadoop.hive.metastore.api.PartitionValuesRequest;
import org.apache.hadoop.hive.metastore.api.PartitionValuesResponse;
import org.apache.hadoop.hive.metastore.api.PartitionsByExprRequest;
import org.apache.hadoop.hive.metastore.api.PartitionsByExprResult;
import org.apache.hadoop.hive.metastore.api.PartitionsStatsRequest;
import org.apache.hadoop.hive.metastore.api.PrimaryKeysRequest;
import org.apache.hadoop.hive.metastore.api.PrincipalPrivilegeSet;
import org.apache.hadoop.hive.metastore.api.PrincipalType;
import org.apache.hadoop.hive.metastore.api.PrivilegeBag;
import org.apache.hadoop.hive.metastore.api.PutFileMetadataRequest;
import org.apache.hadoop.hive.metastore.api.RequestPartsSpec;
import org.apache.hadoop.hive.metastore.api.Role;
import org.apache.hadoop.hive.metastore.api.SQLForeignKey;
import org.apache.hadoop.hive.metastore.api.SQLPrimaryKey;
import org.apache.hadoop.hive.metastore.api.SetPartitionsStatsRequest;
import org.apache.hadoop.hive.metastore.api.ShowCompactRequest;
import org.apache.hadoop.hive.metastore.api.ShowCompactResponse;
import org.apache.hadoop.hive.metastore.api.ShowLocksRequest;
import org.apache.hadoop.hive.metastore.api.ShowLocksResponse;
import org.apache.hadoop.hive.metastore.api.Table;
import org.apache.hadoop.hive.metastore.api.TableMeta;
import org.apache.hadoop.hive.metastore.api.TableStatsRequest;
import org.apache.hadoop.hive.metastore.api.ThriftHiveMetastore;
import org.apache.hadoop.hive.metastore.api.TxnAbortedException;
import org.apache.hadoop.hive.metastore.api.TxnOpenException;
import org.apache.hadoop.hive.metastore.api.Type;
import org.apache.hadoop.hive.metastore.api.UnknownDBException;
import org.apache.hadoop.hive.metastore.api.UnknownPartitionException;
import org.apache.hadoop.hive.metastore.api.UnknownTableException;
import org.apache.hadoop.hive.metastore.api.UnlockRequest;
import org.apache.hadoop.hive.metastore.partition.spec.PartitionSpecProxy;
import org.apache.hadoop.hive.metastore.txn.TxnUtils;
import org.apache.hadoop.hive.shims.ShimLoader;
import org.apache.hadoop.hive.shims.Utils;
import org.apache.hadoop.hive.thrift.HadoopThriftAuthBridge;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.hadoop.util.StringUtils;
import org.apache.thrift.TApplicationException;
import org.apache.thrift.TConfiguration;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.apache.thrift.transport.layered.TFramedTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Public
@Unstable
public class HiveMetaStoreClient implements IMetaStoreClient {
   public static final ClientCapabilities VERSION = null;
   public static final ClientCapabilities TEST_VERSION;
   ThriftHiveMetastore.Iface client;
   private TTransport transport;
   private boolean isConnected;
   private URI[] metastoreUris;
   private final HiveMetaHookLoader hookLoader;
   protected final HiveConf conf;
   protected boolean fastpath;
   private String tokenStrForm;
   private final boolean localMetaStore;
   private final MetaStoreFilterHook filterHook;
   private final int fileMetadataBatchSize;
   private Map currentMetaVars;
   private static final AtomicInteger connCount;
   private int retries;
   private long retryDelaySeconds;
   protected static final Logger LOG;

   public HiveMetaStoreClient(HiveConf conf) throws MetaException {
      this(conf, (HiveMetaHookLoader)null, true);
   }

   public HiveMetaStoreClient(HiveConf conf, HiveMetaHookLoader hookLoader) throws MetaException {
      this(conf, hookLoader, true);
   }

   public HiveMetaStoreClient(HiveConf conf, HiveMetaHookLoader hookLoader, Boolean allowEmbedded) throws MetaException {
      // $FF: Couldn't be decompiled
   }

   private MetaStoreFilterHook loadFilterHooks() throws IllegalStateException {
      Class<? extends MetaStoreFilterHook> authProviderClass = this.conf.getClass(ConfVars.METASTORE_FILTER_HOOK.varname, DefaultMetaStoreFilterHookImpl.class, MetaStoreFilterHook.class);
      String msg = "Unable to create instance of " + authProviderClass.getName() + ": ";

      try {
         Constructor<? extends MetaStoreFilterHook> constructor = authProviderClass.getConstructor(HiveConf.class);
         return (MetaStoreFilterHook)constructor.newInstance(this.conf);
      } catch (NoSuchMethodException e) {
         throw new IllegalStateException(msg + e.getMessage(), e);
      } catch (SecurityException e) {
         throw new IllegalStateException(msg + e.getMessage(), e);
      } catch (InstantiationException e) {
         throw new IllegalStateException(msg + e.getMessage(), e);
      } catch (IllegalAccessException e) {
         throw new IllegalStateException(msg + e.getMessage(), e);
      } catch (IllegalArgumentException e) {
         throw new IllegalStateException(msg + e.getMessage(), e);
      } catch (InvocationTargetException e) {
         throw new IllegalStateException(msg + e.getMessage(), e);
      }
   }

   private void promoteRandomMetaStoreURI() {
      if (this.metastoreUris.length > 1) {
         Random rng = new Random();
         int index = rng.nextInt(this.metastoreUris.length - 1) + 1;
         URI tmp = this.metastoreUris[0];
         this.metastoreUris[0] = this.metastoreUris[index];
         this.metastoreUris[index] = tmp;
      }
   }

   @VisibleForTesting
   public TTransport getTTransport() {
      return this.transport;
   }

   public boolean isLocalMetaStore() {
      return this.localMetaStore;
   }

   public boolean isCompatibleWith(HiveConf conf) {
      Map<String, String> currentMetaVarsCopy = this.currentMetaVars;
      if (currentMetaVarsCopy == null) {
         return false;
      } else {
         boolean compatible = true;

         for(HiveConf.ConfVars oneVar : HiveConf.metaVars) {
            String oldVar = (String)currentMetaVarsCopy.get(oneVar.varname);
            String newVar = conf.get(oneVar.varname, "");
            if (oldVar != null) {
               if (oneVar.isCaseSensitive()) {
                  if (oldVar.equals(newVar)) {
                     continue;
                  }
               } else if (oldVar.equalsIgnoreCase(newVar)) {
                  continue;
               }
            }

            LOG.info("Mestastore configuration " + oneVar.varname + " changed from " + oldVar + " to " + newVar);
            compatible = false;
         }

         return compatible;
      }
   }

   public void setHiveAddedJars(String addedJars) {
      HiveConf.setVar(this.conf, ConfVars.HIVEADDEDJARS, addedJars);
   }

   public void reconnect() throws MetaException {
      if (this.localMetaStore) {
         throw new MetaException("For direct MetaStore DB connections, we don't support retries at the client level.");
      } else {
         this.close();
         this.promoteRandomMetaStoreURI();
         this.open();
      }
   }

   public void alter_table(String dbname, String tbl_name, Table new_tbl) throws InvalidOperationException, MetaException, TException {
      this.alter_table_with_environmentContext(dbname, tbl_name, new_tbl, (EnvironmentContext)null);
   }

   public void alter_table(String defaultDatabaseName, String tblName, Table table, boolean cascade) throws InvalidOperationException, MetaException, TException {
      EnvironmentContext environmentContext = new EnvironmentContext();
      if (cascade) {
         environmentContext.putToProperties("CASCADE", "true");
      }

      this.alter_table_with_environmentContext(defaultDatabaseName, tblName, table, environmentContext);
   }

   public void alter_table_with_environmentContext(String dbname, String tbl_name, Table new_tbl, EnvironmentContext envContext) throws InvalidOperationException, MetaException, TException {
      this.client.alter_table_with_environment_context(dbname, tbl_name, new_tbl, envContext);
   }

   public void renamePartition(String dbname, String name, List part_vals, Partition newPart) throws InvalidOperationException, MetaException, TException {
      this.client.rename_partition(dbname, name, part_vals, newPart);
   }

   private void open() throws MetaException {
      this.isConnected = false;
      TTransportException tte = null;
      boolean useSSL = this.conf.getBoolVar(ConfVars.HIVE_METASTORE_USE_SSL);
      boolean useSasl = this.conf.getBoolVar(ConfVars.METASTORE_USE_THRIFT_SASL);
      boolean useFramedTransport = this.conf.getBoolVar(ConfVars.METASTORE_USE_THRIFT_FRAMED_TRANSPORT);
      boolean useCompactProtocol = this.conf.getBoolVar(ConfVars.METASTORE_USE_THRIFT_COMPACT_PROTOCOL);
      int clientSocketTimeout = (int)this.conf.getTimeVar(ConfVars.METASTORE_CLIENT_SOCKET_TIMEOUT, TimeUnit.MILLISECONDS);
      int connectionTimeout = (int)this.conf.getTimeVar(ConfVars.METASTORE_CLIENT_CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS);

      for(int attempt = 0; !this.isConnected && attempt < this.retries; ++attempt) {
         for(URI store : this.metastoreUris) {
            LOG.info("Trying to connect to metastore with URI " + store);

            try {
               if (useSasl) {
                  try {
                     HadoopThriftAuthBridge.Client authBridge = ShimLoader.getHadoopThriftAuthBridge().createClient();
                     String tokenSig = this.conf.getVar(ConfVars.METASTORE_TOKEN_SIGNATURE);
                     this.tokenStrForm = Utils.getTokenStrForm(tokenSig);
                     this.transport = new TSocket(new TConfiguration(), store.getHost(), store.getPort(), clientSocketTimeout, connectionTimeout);
                     if (this.tokenStrForm != null) {
                        this.transport = authBridge.createClientTransport((String)null, store.getHost(), "DIGEST", this.tokenStrForm, this.transport, MetaStoreUtils.getMetaStoreSaslProperties(this.conf));
                     } else {
                        String principalConfig = this.conf.getVar(ConfVars.METASTORE_KERBEROS_PRINCIPAL);
                        this.transport = authBridge.createClientTransport(principalConfig, store.getHost(), "KERBEROS", (String)null, this.transport, MetaStoreUtils.getMetaStoreSaslProperties(this.conf));
                     }
                  } catch (TTransportException | IOException sasle) {
                     LOG.error("Could not create client transport", sasle);
                     throw new MetaException(((Exception)sasle).toString());
                  }
               } else {
                  if (useSSL) {
                     try {
                        String trustStorePath = this.conf.getVar(ConfVars.HIVE_METASTORE_SSL_TRUSTSTORE_PATH).trim();
                        if (trustStorePath.isEmpty()) {
                           throw new IllegalArgumentException(ConfVars.HIVE_METASTORE_SSL_TRUSTSTORE_PATH.varname + " Not configured for SSL connection");
                        }

                        String trustStorePassword = ShimLoader.getHadoopShims().getPassword(this.conf, ConfVars.HIVE_METASTORE_SSL_TRUSTSTORE_PASSWORD.varname);
                        this.transport = HiveAuthUtils.getSSLSocket(store.getHost(), store.getPort(), clientSocketTimeout, connectionTimeout, trustStorePath, trustStorePassword);
                        LOG.info("Opened an SSL connection to metastore, current connections: " + connCount.incrementAndGet());
                     } catch (IOException e) {
                        throw new IllegalArgumentException(e);
                     } catch (TTransportException e) {
                        throw new MetaException(e.toString());
                     }
                  } else {
                     try {
                        this.transport = new TSocket(new TConfiguration(), store.getHost(), store.getPort(), clientSocketTimeout, connectionTimeout);
                     } catch (TTransportException e) {
                        throw new MetaException(e.toString());
                     }
                  }

                  if (useFramedTransport) {
                     try {
                        this.transport = new TFramedTransport(this.transport);
                     } catch (TTransportException e) {
                        LOG.error("Failed to create client transport", e);
                        throw new MetaException(e.toString());
                     }
                  }
               }

               TProtocol protocol;
               if (useCompactProtocol) {
                  protocol = new TCompactProtocol(this.transport);
               } else {
                  protocol = new TBinaryProtocol(this.transport);
               }

               this.client = new ThriftHiveMetastore.Client(protocol);

               try {
                  if (!this.transport.isOpen()) {
                     this.transport.open();
                     LOG.info("Opened a connection to metastore, current connections: " + connCount.incrementAndGet());
                  }

                  this.isConnected = true;
               } catch (TTransportException e) {
                  tte = e;
                  if (LOG.isDebugEnabled()) {
                     LOG.warn("Failed to connect to the MetaStore Server...", e);
                  } else {
                     LOG.warn("Failed to connect to the MetaStore Server...");
                  }
               }

               if (this.isConnected && !useSasl && this.conf.getBoolVar(ConfVars.METASTORE_EXECUTE_SET_UGI)) {
                  try {
                     UserGroupInformation ugi = Utils.getUGI();
                     this.client.set_ugi(ugi.getUserName(), Arrays.asList(ugi.getGroupNames()));
                  } catch (LoginException e) {
                     LOG.warn("Failed to do login. set_ugi() is not successful, Continuing without it.", e);
                  } catch (IOException e) {
                     LOG.warn("Failed to find ugi of client set_ugi() is not successful, Continuing without it.", e);
                  } catch (TException e) {
                     LOG.warn("set_ugi() not successful, Likely cause: new client talking to old server. Continuing without it.", e);
                  }
               }
            } catch (MetaException e) {
               LOG.error("Unable to connect to metastore with URI " + store + " in attempt " + attempt, e);
            }

            if (this.isConnected) {
               break;
            }
         }

         if (!this.isConnected && this.retryDelaySeconds > 0L) {
            try {
               LOG.info("Waiting " + this.retryDelaySeconds + " seconds before next connection attempt.");
               Thread.sleep(this.retryDelaySeconds * 1000L);
            } catch (InterruptedException var16) {
            }
         }
      }

      if (!this.isConnected) {
         throw new MetaException("Could not connect to meta store using any of the URIs provided. Most recent failure: " + StringUtils.stringifyException(tte));
      } else {
         this.snapshotActiveConf();
         LOG.info("Connected to metastore.");
      }
   }

   private void snapshotActiveConf() {
      this.currentMetaVars = new HashMap(HiveConf.metaVars.length);

      for(HiveConf.ConfVars oneVar : HiveConf.metaVars) {
         this.currentMetaVars.put(oneVar.varname, this.conf.get(oneVar.varname, ""));
      }

   }

   public String getTokenStrForm() throws IOException {
      return this.tokenStrForm;
   }

   public void close() {
      this.isConnected = false;
      this.currentMetaVars = null;

      try {
         if (null != this.client) {
            this.client.shutdown();
         }
      } catch (TException e) {
         LOG.debug("Unable to shutdown metastore client. Will try closing transport directly.", e);
      }

      if (this.transport != null && this.transport.isOpen()) {
         this.transport.close();
         LOG.info("Closed a connection to metastore, current connections: " + connCount.decrementAndGet());
      }

   }

   public void setMetaConf(String key, String value) throws TException {
      this.client.setMetaConf(key, value);
   }

   public String getMetaConf(String key) throws TException {
      return this.client.getMetaConf(key);
   }

   public Partition add_partition(Partition new_part) throws InvalidObjectException, AlreadyExistsException, MetaException, TException {
      return this.add_partition(new_part, (EnvironmentContext)null);
   }

   public Partition add_partition(Partition new_part, EnvironmentContext envContext) throws InvalidObjectException, AlreadyExistsException, MetaException, TException {
      Partition p = this.client.add_partition_with_environment_context(new_part, envContext);
      return this.fastpath ? p : this.deepCopy(p);
   }

   public int add_partitions(List new_parts) throws InvalidObjectException, AlreadyExistsException, MetaException, TException {
      return this.client.add_partitions(new_parts);
   }

   public List add_partitions(List parts, boolean ifNotExists, boolean needResults) throws InvalidObjectException, AlreadyExistsException, MetaException, TException {
      if (parts.isEmpty()) {
         return needResults ? new ArrayList() : null;
      } else {
         Partition part = (Partition)parts.get(0);
         AddPartitionsRequest req = new AddPartitionsRequest(part.getDbName(), part.getTableName(), parts, ifNotExists);
         req.setNeedResult(needResults);
         AddPartitionsResult result = this.client.add_partitions_req(req);
         return needResults ? this.filterHook.filterPartitions(result.getPartitions()) : null;
      }
   }

   public int add_partitions_pspec(PartitionSpecProxy partitionSpec) throws TException {
      return this.client.add_partitions_pspec(partitionSpec.toPartitionSpec());
   }

   public Partition appendPartition(String db_name, String table_name, List part_vals) throws InvalidObjectException, AlreadyExistsException, MetaException, TException {
      return this.appendPartition(db_name, table_name, (List)part_vals, (EnvironmentContext)null);
   }

   public Partition appendPartition(String db_name, String table_name, List part_vals, EnvironmentContext envContext) throws InvalidObjectException, AlreadyExistsException, MetaException, TException {
      Partition p = this.client.append_partition_with_environment_context(db_name, table_name, part_vals, envContext);
      return this.fastpath ? p : this.deepCopy(p);
   }

   public Partition appendPartition(String dbName, String tableName, String partName) throws InvalidObjectException, AlreadyExistsException, MetaException, TException {
      return this.appendPartition(dbName, tableName, (String)partName, (EnvironmentContext)null);
   }

   public Partition appendPartition(String dbName, String tableName, String partName, EnvironmentContext envContext) throws InvalidObjectException, AlreadyExistsException, MetaException, TException {
      Partition p = this.client.append_partition_by_name_with_environment_context(dbName, tableName, partName, envContext);
      return this.fastpath ? p : this.deepCopy(p);
   }

   public Partition exchange_partition(Map partitionSpecs, String sourceDb, String sourceTable, String destDb, String destinationTableName) throws MetaException, NoSuchObjectException, InvalidObjectException, TException {
      return this.client.exchange_partition(partitionSpecs, sourceDb, sourceTable, destDb, destinationTableName);
   }

   public List exchange_partitions(Map partitionSpecs, String sourceDb, String sourceTable, String destDb, String destinationTableName) throws MetaException, NoSuchObjectException, InvalidObjectException, TException {
      return this.client.exchange_partitions(partitionSpecs, sourceDb, sourceTable, destDb, destinationTableName);
   }

   public void validatePartitionNameCharacters(List partVals) throws TException, MetaException {
      this.client.partition_name_has_valid_characters(partVals, true);
   }

   public void createDatabase(Database db) throws AlreadyExistsException, InvalidObjectException, MetaException, TException {
      this.client.create_database(db);
   }

   public void createTable(Table tbl) throws AlreadyExistsException, InvalidObjectException, MetaException, NoSuchObjectException, TException {
      this.createTable(tbl, (EnvironmentContext)null);
   }

   public void createTable(Table tbl, EnvironmentContext envContext) throws AlreadyExistsException, InvalidObjectException, MetaException, NoSuchObjectException, TException {
      HiveMetaHook hook = this.getHook(tbl);
      if (hook != null) {
         hook.preCreateTable(tbl);
      }

      boolean success = false;

      try {
         this.create_table_with_environment_context(tbl, envContext);
         if (hook != null) {
            hook.commitCreateTable(tbl);
         }

         success = true;
      } finally {
         if (!success && hook != null) {
            try {
               hook.rollbackCreateTable(tbl);
            } catch (Exception e) {
               LOG.error("Create rollback failed with", e);
            }
         }

      }

   }

   public void createTableWithConstraints(Table tbl, List primaryKeys, List foreignKeys) throws AlreadyExistsException, InvalidObjectException, MetaException, NoSuchObjectException, TException {
      HiveMetaHook hook = this.getHook(tbl);
      if (hook != null) {
         hook.preCreateTable(tbl);
      }

      boolean success = false;

      try {
         this.client.create_table_with_constraints(tbl, primaryKeys, foreignKeys);
         if (hook != null) {
            hook.commitCreateTable(tbl);
         }

         success = true;
      } finally {
         if (!success && hook != null) {
            hook.rollbackCreateTable(tbl);
         }

      }

   }

   public void dropConstraint(String dbName, String tableName, String constraintName) throws NoSuchObjectException, MetaException, TException {
      this.client.drop_constraint(new DropConstraintRequest(dbName, tableName, constraintName));
   }

   public void addPrimaryKey(List primaryKeyCols) throws NoSuchObjectException, MetaException, TException {
      this.client.add_primary_key(new AddPrimaryKeyRequest(primaryKeyCols));
   }

   public void addForeignKey(List foreignKeyCols) throws NoSuchObjectException, MetaException, TException {
      this.client.add_foreign_key(new AddForeignKeyRequest(foreignKeyCols));
   }

   public boolean createType(Type type) throws AlreadyExistsException, InvalidObjectException, MetaException, TException {
      return this.client.create_type(type);
   }

   public void dropDatabase(String name) throws NoSuchObjectException, InvalidOperationException, MetaException, TException {
      this.dropDatabase(name, true, false, false);
   }

   public void dropDatabase(String name, boolean deleteData, boolean ignoreUnknownDb) throws NoSuchObjectException, InvalidOperationException, MetaException, TException {
      this.dropDatabase(name, deleteData, ignoreUnknownDb, false);
   }

   public void dropDatabase(String name, boolean deleteData, boolean ignoreUnknownDb, boolean cascade) throws NoSuchObjectException, InvalidOperationException, MetaException, TException {
      try {
         this.getDatabase(name);
      } catch (NoSuchObjectException e) {
         if (!ignoreUnknownDb) {
            throw e;
         }

         return;
      }

      if (cascade) {
         for(String table : this.getAllTables(name)) {
            try {
               this.dropTable(name, table, deleteData, true);
            } catch (UnsupportedOperationException var9) {
            }
         }
      }

      this.client.drop_database(name, deleteData, cascade);
   }

   public boolean dropPartition(String db_name, String tbl_name, List part_vals) throws NoSuchObjectException, MetaException, TException {
      return this.dropPartition(db_name, tbl_name, (List)part_vals, true, (EnvironmentContext)null);
   }

   public boolean dropPartition(String db_name, String tbl_name, List part_vals, EnvironmentContext env_context) throws NoSuchObjectException, MetaException, TException {
      return this.dropPartition(db_name, tbl_name, part_vals, true, env_context);
   }

   public boolean dropPartition(String dbName, String tableName, String partName, boolean deleteData) throws NoSuchObjectException, MetaException, TException {
      return this.dropPartition(dbName, tableName, (String)partName, deleteData, (EnvironmentContext)null);
   }

   private static EnvironmentContext getEnvironmentContextWithIfPurgeSet() {
      Map<String, String> warehouseOptions = new HashMap();
      warehouseOptions.put("ifPurge", "TRUE");
      return new EnvironmentContext(warehouseOptions);
   }

   public boolean dropPartition(String dbName, String tableName, String partName, boolean deleteData, EnvironmentContext envContext) throws NoSuchObjectException, MetaException, TException {
      return this.client.drop_partition_by_name_with_environment_context(dbName, tableName, partName, deleteData, envContext);
   }

   public boolean dropPartition(String db_name, String tbl_name, List part_vals, boolean deleteData) throws NoSuchObjectException, MetaException, TException {
      return this.dropPartition(db_name, tbl_name, (List)part_vals, deleteData, (EnvironmentContext)null);
   }

   public boolean dropPartition(String db_name, String tbl_name, List part_vals, PartitionDropOptions options) throws TException {
      return this.dropPartition(db_name, tbl_name, part_vals, options.deleteData, options.purgeData ? getEnvironmentContextWithIfPurgeSet() : null);
   }

   public boolean dropPartition(String db_name, String tbl_name, List part_vals, boolean deleteData, EnvironmentContext envContext) throws NoSuchObjectException, MetaException, TException {
      return this.client.drop_partition_with_environment_context(db_name, tbl_name, part_vals, deleteData, envContext);
   }

   public List dropPartitions(String dbName, String tblName, List partExprs, PartitionDropOptions options) throws TException {
      RequestPartsSpec rps = new RequestPartsSpec();
      List<DropPartitionsExpr> exprs = new ArrayList(partExprs.size());

      for(ObjectPair partExpr : partExprs) {
         DropPartitionsExpr dpe = new DropPartitionsExpr();
         dpe.setExpr((byte[])partExpr.getSecond());
         dpe.setPartArchiveLevel((Integer)partExpr.getFirst());
         exprs.add(dpe);
      }

      rps.setExprs(exprs);
      DropPartitionsRequest req = new DropPartitionsRequest(dbName, tblName, rps);
      req.setDeleteData(options.deleteData);
      req.setNeedResult(options.returnResults);
      req.setIfExists(options.ifExists);
      if (options.purgeData) {
         LOG.info("Dropped partitions will be purged!");
         req.setEnvironmentContext(getEnvironmentContextWithIfPurgeSet());
      }

      return this.client.drop_partitions_req(req).getPartitions();
   }

   public List dropPartitions(String dbName, String tblName, List partExprs, boolean deleteData, boolean ifExists, boolean needResult) throws NoSuchObjectException, MetaException, TException {
      return this.dropPartitions(dbName, tblName, partExprs, PartitionDropOptions.instance().deleteData(deleteData).ifExists(ifExists).returnResults(needResult));
   }

   public List dropPartitions(String dbName, String tblName, List partExprs, boolean deleteData, boolean ifExists) throws NoSuchObjectException, MetaException, TException {
      return this.dropPartitions(dbName, tblName, partExprs, PartitionDropOptions.instance().deleteData(deleteData).ifExists(ifExists));
   }

   public void dropTable(String dbname, String name, boolean deleteData, boolean ignoreUnknownTab) throws MetaException, TException, NoSuchObjectException, UnsupportedOperationException {
      this.dropTable(dbname, name, deleteData, ignoreUnknownTab, (EnvironmentContext)null);
   }

   public void dropTable(String dbname, String name, boolean deleteData, boolean ignoreUnknownTab, boolean ifPurge) throws MetaException, TException, NoSuchObjectException, UnsupportedOperationException {
      EnvironmentContext envContext = null;
      if (ifPurge) {
         Map<String, String> warehouseOptions = null;
         warehouseOptions = new HashMap();
         warehouseOptions.put("ifPurge", "TRUE");
         envContext = new EnvironmentContext(warehouseOptions);
      }

      this.dropTable(dbname, name, deleteData, ignoreUnknownTab, envContext);
   }

   /** @deprecated */
   @Deprecated
   public void dropTable(String tableName, boolean deleteData) throws MetaException, UnknownTableException, TException, NoSuchObjectException {
      this.dropTable("default", tableName, deleteData, false, (EnvironmentContext)null);
   }

   public void dropTable(String dbname, String name) throws NoSuchObjectException, MetaException, TException {
      this.dropTable(dbname, name, true, true, (EnvironmentContext)null);
   }

   public void dropTable(String dbname, String name, boolean deleteData, boolean ignoreUnknownTab, EnvironmentContext envContext) throws MetaException, TException, NoSuchObjectException, UnsupportedOperationException {
      Table tbl;
      try {
         tbl = this.getTable(dbname, name);
      } catch (NoSuchObjectException e) {
         if (!ignoreUnknownTab) {
            throw e;
         }

         return;
      }

      if (MetaStoreUtils.isIndexTable(tbl)) {
         throw new UnsupportedOperationException("Cannot drop index tables");
      } else {
         HiveMetaHook hook = this.getHook(tbl);
         if (hook != null) {
            hook.preDropTable(tbl);
         }

         boolean success = false;

         try {
            this.drop_table_with_environment_context(dbname, name, deleteData, envContext);
            if (hook != null) {
               hook.commitDropTable(tbl, deleteData || envContext != null && "TRUE".equals(envContext.getProperties().get("ifPurge")));
            }

            success = true;
         } catch (NoSuchObjectException e) {
            if (!ignoreUnknownTab) {
               throw e;
            }
         } finally {
            if (!success && hook != null) {
               hook.rollbackDropTable(tbl);
            }

         }

      }
   }

   public boolean dropType(String type) throws NoSuchObjectException, MetaException, TException {
      return this.client.drop_type(type);
   }

   public Map getTypeAll(String name) throws MetaException, TException {
      Map<String, Type> result = null;
      Map<String, Type> fromClient = this.client.get_type_all(name);
      if (fromClient != null) {
         result = new LinkedHashMap();

         for(String key : fromClient.keySet()) {
            result.put(key, this.deepCopy((Type)fromClient.get(key)));
         }
      }

      return result;
   }

   public List getDatabases(String databasePattern) throws MetaException {
      try {
         return this.filterHook.filterDatabases(this.client.get_databases(databasePattern));
      } catch (Exception e) {
         MetaStoreUtils.logAndThrowMetaException(e);
         return null;
      }
   }

   public List getAllDatabases() throws MetaException {
      try {
         return this.filterHook.filterDatabases(this.client.get_all_databases());
      } catch (Exception e) {
         MetaStoreUtils.logAndThrowMetaException(e);
         return null;
      }
   }

   public List listPartitions(String db_name, String tbl_name, short max_parts) throws NoSuchObjectException, MetaException, TException {
      List<Partition> parts = this.client.get_partitions(db_name, tbl_name, max_parts);
      return this.fastpath ? parts : this.deepCopyPartitions(this.filterHook.filterPartitions(parts));
   }

   public PartitionSpecProxy listPartitionSpecs(String dbName, String tableName, int maxParts) throws TException {
      return PartitionSpecProxy.Factory.get(this.filterHook.filterPartitionSpecs(this.client.get_partitions_pspec(dbName, tableName, maxParts)));
   }

   public List listPartitions(String db_name, String tbl_name, List part_vals, short max_parts) throws NoSuchObjectException, MetaException, TException {
      List<Partition> parts = this.client.get_partitions_ps(db_name, tbl_name, part_vals, max_parts);
      return this.fastpath ? parts : this.deepCopyPartitions(this.filterHook.filterPartitions(parts));
   }

   public List listPartitionsWithAuthInfo(String db_name, String tbl_name, short max_parts, String user_name, List group_names) throws NoSuchObjectException, MetaException, TException {
      List<Partition> parts = this.client.get_partitions_with_auth(db_name, tbl_name, max_parts, user_name, group_names);
      return this.fastpath ? parts : this.deepCopyPartitions(this.filterHook.filterPartitions(parts));
   }

   public List listPartitionsWithAuthInfo(String db_name, String tbl_name, List part_vals, short max_parts, String user_name, List group_names) throws NoSuchObjectException, MetaException, TException {
      List<Partition> parts = this.client.get_partitions_ps_with_auth(db_name, tbl_name, part_vals, max_parts, user_name, group_names);
      return this.fastpath ? parts : this.deepCopyPartitions(this.filterHook.filterPartitions(parts));
   }

   public List listPartitionsByFilter(String db_name, String tbl_name, String filter, short max_parts) throws MetaException, NoSuchObjectException, TException {
      List<Partition> parts = this.client.get_partitions_by_filter(db_name, tbl_name, filter, max_parts);
      return this.fastpath ? parts : this.deepCopyPartitions(this.filterHook.filterPartitions(parts));
   }

   public PartitionSpecProxy listPartitionSpecsByFilter(String db_name, String tbl_name, String filter, int max_parts) throws MetaException, NoSuchObjectException, TException {
      return PartitionSpecProxy.Factory.get(this.filterHook.filterPartitionSpecs(this.client.get_part_specs_by_filter(db_name, tbl_name, filter, max_parts)));
   }

   public boolean listPartitionsByExpr(String db_name, String tbl_name, byte[] expr, String default_partition_name, short max_parts, List result) throws TException {
      assert result != null;

      PartitionsByExprRequest req = new PartitionsByExprRequest(db_name, tbl_name, ByteBuffer.wrap(expr));
      if (default_partition_name != null) {
         req.setDefaultPartitionName(default_partition_name);
      }

      if (max_parts >= 0) {
         req.setMaxParts(max_parts);
      }

      PartitionsByExprResult r = null;

      try {
         r = this.client.get_partitions_by_expr(req);
      } catch (TApplicationException var10) {
         if (var10.getType() != 1 && var10.getType() != 3) {
            throw var10;
         }

         throw new IMetaStoreClient.IncompatibleMetastoreException("Metastore doesn't support listPartitionsByExpr: " + var10.getMessage());
      }

      if (this.fastpath) {
         result.addAll(r.getPartitions());
      } else {
         r.setPartitions(this.filterHook.filterPartitions(r.getPartitions()));
         this.deepCopyPartitions(r.getPartitions(), result);
      }

      return !r.isSetHasUnknownPartitions() || r.isHasUnknownPartitions();
   }

   public Database getDatabase(String name) throws NoSuchObjectException, MetaException, TException {
      Database d = this.client.get_database(name);
      return this.fastpath ? d : this.deepCopy(this.filterHook.filterDatabase(d));
   }

   public Partition getPartition(String db_name, String tbl_name, List part_vals) throws NoSuchObjectException, MetaException, TException {
      Partition p = this.client.get_partition(db_name, tbl_name, part_vals);
      return this.fastpath ? p : this.deepCopy(this.filterHook.filterPartition(p));
   }

   public List getPartitionsByNames(String db_name, String tbl_name, List part_names) throws NoSuchObjectException, MetaException, TException {
      List<Partition> parts = this.client.get_partitions_by_names(db_name, tbl_name, part_names);
      return this.fastpath ? parts : this.deepCopyPartitions(this.filterHook.filterPartitions(parts));
   }

   public PartitionValuesResponse listPartitionValues(PartitionValuesRequest request) throws MetaException, TException, NoSuchObjectException {
      return this.client.get_partition_values(request);
   }

   public Partition getPartitionWithAuthInfo(String db_name, String tbl_name, List part_vals, String user_name, List group_names) throws MetaException, UnknownTableException, NoSuchObjectException, TException {
      Partition p = this.client.get_partition_with_auth(db_name, tbl_name, part_vals, user_name, group_names);
      return this.fastpath ? p : this.deepCopy(this.filterHook.filterPartition(p));
   }

   public Table getTable(String dbname, String name) throws MetaException, TException, NoSuchObjectException {
      Table t = this.client.get_table(dbname, name);
      return this.fastpath ? t : this.deepCopy(this.filterHook.filterTable(t));
   }

   /** @deprecated */
   @Deprecated
   public Table getTable(String tableName) throws MetaException, TException, NoSuchObjectException {
      Table t = this.getTable("default", tableName);
      return this.fastpath ? t : this.filterHook.filterTable(t);
   }

   public List getTableObjectsByName(String dbName, List tableNames) throws MetaException, InvalidOperationException, UnknownDBException, TException {
      List<Table> tabs = this.client.get_table_objects_by_name(dbName, tableNames);
      return this.fastpath ? tabs : this.deepCopyTables(this.filterHook.filterTables(tabs));
   }

   public List listTableNamesByFilter(String dbName, String filter, short maxTables) throws MetaException, TException, InvalidOperationException, UnknownDBException {
      return this.filterHook.filterTableNames(dbName, this.client.get_table_names_by_filter(dbName, filter, maxTables));
   }

   public Type getType(String name) throws NoSuchObjectException, MetaException, TException {
      return this.deepCopy(this.client.get_type(name));
   }

   public List getTables(String dbname, String tablePattern) throws MetaException {
      try {
         return this.filterHook.filterTableNames(dbname, this.client.get_tables(dbname, tablePattern));
      } catch (Exception e) {
         MetaStoreUtils.logAndThrowMetaException(e);
         return null;
      }
   }

   public List getTables(String dbname, String tablePattern, TableType tableType) throws MetaException {
      try {
         return this.filterHook.filterTableNames(dbname, this.client.get_tables_by_type(dbname, tablePattern, tableType.toString()));
      } catch (Exception e) {
         MetaStoreUtils.logAndThrowMetaException(e);
         return null;
      }
   }

   public List getTableMeta(String dbPatterns, String tablePatterns, List tableTypes) throws MetaException {
      try {
         return this.filterNames(this.client.get_table_meta(dbPatterns, tablePatterns, tableTypes));
      } catch (Exception e) {
         MetaStoreUtils.logAndThrowMetaException(e);
         return null;
      }
   }

   private List filterNames(List metas) throws MetaException {
      Map<String, TableMeta> sources = new LinkedHashMap();
      Map<String, List<String>> dbTables = new LinkedHashMap();

      for(TableMeta meta : metas) {
         sources.put(meta.getDbName() + "." + meta.getTableName(), meta);
         List<String> tables = (List)dbTables.get(meta.getDbName());
         if (tables == null) {
            dbTables.put(meta.getDbName(), tables = new ArrayList());
         }

         tables.add(meta.getTableName());
      }

      List<TableMeta> filtered = new ArrayList();

      for(Map.Entry entry : dbTables.entrySet()) {
         for(String table : this.filterHook.filterTableNames((String)entry.getKey(), (List)entry.getValue())) {
            filtered.add(sources.get((String)entry.getKey() + "." + table));
         }
      }

      return filtered;
   }

   public List getAllTables(String dbname) throws MetaException {
      try {
         return this.filterHook.filterTableNames(dbname, this.client.get_all_tables(dbname));
      } catch (Exception e) {
         MetaStoreUtils.logAndThrowMetaException(e);
         return null;
      }
   }

   public boolean tableExists(String databaseName, String tableName) throws MetaException, TException, UnknownDBException {
      try {
         return this.filterHook.filterTable(this.client.get_table(databaseName, tableName)) != null;
      } catch (NoSuchObjectException var4) {
         return false;
      }
   }

   /** @deprecated */
   @Deprecated
   public boolean tableExists(String tableName) throws MetaException, TException, UnknownDBException {
      return this.tableExists("default", tableName);
   }

   public List listPartitionNames(String dbName, String tblName, short max) throws MetaException, TException {
      return this.filterHook.filterPartitionNames(dbName, tblName, this.client.get_partition_names(dbName, tblName, max));
   }

   public List listPartitionNames(String db_name, String tbl_name, List part_vals, short max_parts) throws MetaException, TException, NoSuchObjectException {
      return this.filterHook.filterPartitionNames(db_name, tbl_name, this.client.get_partition_names_ps(db_name, tbl_name, part_vals, max_parts));
   }

   public int getNumPartitionsByFilter(String db_name, String tbl_name, String filter) throws MetaException, NoSuchObjectException, TException {
      return this.client.get_num_partitions_by_filter(db_name, tbl_name, filter);
   }

   public void alter_partition(String dbName, String tblName, Partition newPart) throws InvalidOperationException, MetaException, TException {
      this.client.alter_partition(dbName, tblName, newPart);
   }

   public void alter_partition(String dbName, String tblName, Partition newPart, EnvironmentContext environmentContext) throws InvalidOperationException, MetaException, TException {
      if (environmentContext == null) {
         this.client.alter_partition(dbName, tblName, newPart);
      } else {
         this.client.alter_partition_with_environment_context(dbName, tblName, newPart, environmentContext);
      }

   }

   public void alter_partitions(String dbName, String tblName, List newParts) throws InvalidOperationException, MetaException, TException {
      this.client.alter_partitions(dbName, tblName, newParts);
   }

   public void alter_partitions(String dbName, String tblName, List newParts, EnvironmentContext environmentContext) throws InvalidOperationException, MetaException, TException {
      if (environmentContext == null) {
         this.client.alter_partitions(dbName, tblName, newParts);
      } else {
         this.client.alter_partitions_with_environment_context(dbName, tblName, newParts, environmentContext);
      }

   }

   public void alterDatabase(String dbName, Database db) throws MetaException, NoSuchObjectException, TException {
      this.client.alter_database(dbName, db);
   }

   public List getFields(String db, String tableName) throws MetaException, TException, UnknownTableException, UnknownDBException {
      List<FieldSchema> fields = this.client.get_fields(db, tableName);
      return this.fastpath ? fields : this.deepCopyFieldSchemas(fields);
   }

   public void createIndex(Index index, Table indexTable) throws AlreadyExistsException, InvalidObjectException, MetaException, NoSuchObjectException, TException {
      this.client.add_index(index, indexTable);
   }

   public void alter_index(String dbname, String base_tbl_name, String idx_name, Index new_idx) throws InvalidOperationException, MetaException, TException {
      this.client.alter_index(dbname, base_tbl_name, idx_name, new_idx);
   }

   public Index getIndex(String dbName, String tblName, String indexName) throws MetaException, UnknownTableException, NoSuchObjectException, TException {
      return this.deepCopy(this.filterHook.filterIndex(this.client.get_index_by_name(dbName, tblName, indexName)));
   }

   public List listIndexNames(String dbName, String tblName, short max) throws MetaException, TException {
      return this.filterHook.filterIndexNames(dbName, tblName, this.client.get_index_names(dbName, tblName, max));
   }

   public List listIndexes(String dbName, String tblName, short max) throws NoSuchObjectException, MetaException, TException {
      return this.filterHook.filterIndexes(this.client.get_indexes(dbName, tblName, max));
   }

   public List getPrimaryKeys(PrimaryKeysRequest req) throws MetaException, NoSuchObjectException, TException {
      return this.client.get_primary_keys(req).getPrimaryKeys();
   }

   public List getForeignKeys(ForeignKeysRequest req) throws MetaException, NoSuchObjectException, TException {
      return this.client.get_foreign_keys(req).getForeignKeys();
   }

   /** @deprecated */
   @Deprecated
   public boolean updateTableColumnStatistics(ColumnStatistics statsObj) throws NoSuchObjectException, InvalidObjectException, MetaException, TException, InvalidInputException {
      return this.client.update_table_column_statistics(statsObj);
   }

   /** @deprecated */
   @Deprecated
   public boolean updatePartitionColumnStatistics(ColumnStatistics statsObj) throws NoSuchObjectException, InvalidObjectException, MetaException, TException, InvalidInputException {
      return this.client.update_partition_column_statistics(statsObj);
   }

   public boolean setPartitionColumnStatistics(SetPartitionsStatsRequest request) throws NoSuchObjectException, InvalidObjectException, MetaException, TException, InvalidInputException {
      return this.client.set_aggr_stats_for(request);
   }

   public void flushCache() {
      try {
         this.client.flushCache();
      } catch (TException e) {
         LOG.warn("Got error flushing the cache", e);
      }

   }

   public List getTableColumnStatistics(String dbName, String tableName, List colNames) throws NoSuchObjectException, MetaException, TException, InvalidInputException, InvalidObjectException {
      return this.client.get_table_statistics_req(new TableStatsRequest(dbName, tableName, colNames)).getTableStats();
   }

   public Map getPartitionColumnStatistics(String dbName, String tableName, List partNames, List colNames) throws NoSuchObjectException, MetaException, TException {
      return this.client.get_partitions_statistics_req(new PartitionsStatsRequest(dbName, tableName, colNames, partNames)).getPartStats();
   }

   public boolean deletePartitionColumnStatistics(String dbName, String tableName, String partName, String colName) throws NoSuchObjectException, InvalidObjectException, MetaException, TException, InvalidInputException {
      return this.client.delete_partition_column_statistics(dbName, tableName, partName, colName);
   }

   public boolean deleteTableColumnStatistics(String dbName, String tableName, String colName) throws NoSuchObjectException, InvalidObjectException, MetaException, TException, InvalidInputException {
      return this.client.delete_table_column_statistics(dbName, tableName, colName);
   }

   public List getSchema(String db, String tableName) throws MetaException, TException, UnknownTableException, UnknownDBException {
      EnvironmentContext envCxt = null;
      String addedJars = this.conf.getVar(ConfVars.HIVEADDEDJARS);
      if (org.apache.commons.lang3.StringUtils.isNotBlank(addedJars)) {
         Map<String, String> props = new HashMap();
         props.put("hive.added.jars.path", addedJars);
         envCxt = new EnvironmentContext(props);
      }

      List<FieldSchema> fields = this.client.get_schema_with_environment_context(db, tableName, envCxt);
      return this.fastpath ? fields : this.deepCopyFieldSchemas(fields);
   }

   public String getConfigValue(String name, String defaultValue) throws TException, ConfigValSecurityException {
      return this.client.get_config_value(name, defaultValue);
   }

   public Partition getPartition(String db, String tableName, String partName) throws MetaException, TException, UnknownTableException, NoSuchObjectException {
      Partition p = this.client.get_partition_by_name(db, tableName, partName);
      return this.fastpath ? p : this.deepCopy(this.filterHook.filterPartition(p));
   }

   public Partition appendPartitionByName(String dbName, String tableName, String partName) throws InvalidObjectException, AlreadyExistsException, MetaException, TException {
      return this.appendPartitionByName(dbName, tableName, partName, (EnvironmentContext)null);
   }

   public Partition appendPartitionByName(String dbName, String tableName, String partName, EnvironmentContext envContext) throws InvalidObjectException, AlreadyExistsException, MetaException, TException {
      Partition p = this.client.append_partition_by_name_with_environment_context(dbName, tableName, partName, envContext);
      return this.fastpath ? p : this.deepCopy(p);
   }

   public boolean dropPartitionByName(String dbName, String tableName, String partName, boolean deleteData) throws NoSuchObjectException, MetaException, TException {
      return this.dropPartitionByName(dbName, tableName, partName, deleteData, (EnvironmentContext)null);
   }

   public boolean dropPartitionByName(String dbName, String tableName, String partName, boolean deleteData, EnvironmentContext envContext) throws NoSuchObjectException, MetaException, TException {
      return this.client.drop_partition_by_name_with_environment_context(dbName, tableName, partName, deleteData, envContext);
   }

   private HiveMetaHook getHook(Table tbl) throws MetaException {
      return this.hookLoader == null ? null : this.hookLoader.getHook(tbl);
   }

   public List partitionNameToVals(String name) throws MetaException, TException {
      return this.client.partition_name_to_vals(name);
   }

   public Map partitionNameToSpec(String name) throws MetaException, TException {
      return this.client.partition_name_to_spec(name);
   }

   private Partition deepCopy(Partition partition) {
      Partition copy = null;
      if (partition != null) {
         copy = new Partition(partition);
      }

      return copy;
   }

   private Database deepCopy(Database database) {
      Database copy = null;
      if (database != null) {
         copy = new Database(database);
      }

      return copy;
   }

   protected Table deepCopy(Table table) {
      Table copy = null;
      if (table != null) {
         copy = new Table(table);
      }

      return copy;
   }

   private Index deepCopy(Index index) {
      Index copy = null;
      if (index != null) {
         copy = new Index(index);
      }

      return copy;
   }

   private Type deepCopy(Type type) {
      Type copy = null;
      if (type != null) {
         copy = new Type(type);
      }

      return copy;
   }

   private FieldSchema deepCopy(FieldSchema schema) {
      FieldSchema copy = null;
      if (schema != null) {
         copy = new FieldSchema(schema);
      }

      return copy;
   }

   private Function deepCopy(Function func) {
      Function copy = null;
      if (func != null) {
         copy = new Function(func);
      }

      return copy;
   }

   protected PrincipalPrivilegeSet deepCopy(PrincipalPrivilegeSet pps) {
      PrincipalPrivilegeSet copy = null;
      if (pps != null) {
         copy = new PrincipalPrivilegeSet(pps);
      }

      return copy;
   }

   private List deepCopyPartitions(List partitions) {
      return this.deepCopyPartitions(partitions, (List)null);
   }

   private List deepCopyPartitions(Collection src, List dest) {
      if (src == null) {
         return dest;
      } else {
         if (dest == null) {
            dest = new ArrayList(src.size());
         }

         for(Partition part : src) {
            dest.add(this.deepCopy(part));
         }

         return dest;
      }
   }

   private List deepCopyTables(List tables) {
      List<Table> copy = null;
      if (tables != null) {
         copy = new ArrayList();

         for(Table tab : tables) {
            copy.add(this.deepCopy(tab));
         }
      }

      return copy;
   }

   protected List deepCopyFieldSchemas(List schemas) {
      List<FieldSchema> copy = null;
      if (schemas != null) {
         copy = new ArrayList();

         for(FieldSchema schema : schemas) {
            copy.add(this.deepCopy(schema));
         }
      }

      return copy;
   }

   public boolean dropIndex(String dbName, String tblName, String name, boolean deleteData) throws NoSuchObjectException, MetaException, TException {
      return this.client.drop_index_by_name(dbName, tblName, name, deleteData);
   }

   public boolean grant_role(String roleName, String userName, PrincipalType principalType, String grantor, PrincipalType grantorType, boolean grantOption) throws MetaException, TException {
      GrantRevokeRoleRequest req = new GrantRevokeRoleRequest();
      req.setRequestType(GrantRevokeType.GRANT);
      req.setRoleName(roleName);
      req.setPrincipalName(userName);
      req.setPrincipalType(principalType);
      req.setGrantor(grantor);
      req.setGrantorType(grantorType);
      req.setGrantOption(grantOption);
      GrantRevokeRoleResponse res = this.client.grant_revoke_role(req);
      if (!res.isSetSuccess()) {
         throw new MetaException("GrantRevokeResponse missing success field");
      } else {
         return res.isSuccess();
      }
   }

   public boolean create_role(Role role) throws MetaException, TException {
      return this.client.create_role(role);
   }

   public boolean drop_role(String roleName) throws MetaException, TException {
      return this.client.drop_role(roleName);
   }

   public List list_roles(String principalName, PrincipalType principalType) throws MetaException, TException {
      return this.client.list_roles(principalName, principalType);
   }

   public List listRoleNames() throws MetaException, TException {
      return this.client.get_role_names();
   }

   public GetPrincipalsInRoleResponse get_principals_in_role(GetPrincipalsInRoleRequest req) throws MetaException, TException {
      return this.client.get_principals_in_role(req);
   }

   public GetRoleGrantsForPrincipalResponse get_role_grants_for_principal(GetRoleGrantsForPrincipalRequest getRolePrincReq) throws MetaException, TException {
      return this.client.get_role_grants_for_principal(getRolePrincReq);
   }

   public boolean grant_privileges(PrivilegeBag privileges) throws MetaException, TException {
      GrantRevokePrivilegeRequest req = new GrantRevokePrivilegeRequest();
      req.setRequestType(GrantRevokeType.GRANT);
      req.setPrivileges(privileges);
      GrantRevokePrivilegeResponse res = this.client.grant_revoke_privileges(req);
      if (!res.isSetSuccess()) {
         throw new MetaException("GrantRevokePrivilegeResponse missing success field");
      } else {
         return res.isSuccess();
      }
   }

   public boolean revoke_role(String roleName, String userName, PrincipalType principalType, boolean grantOption) throws MetaException, TException {
      GrantRevokeRoleRequest req = new GrantRevokeRoleRequest();
      req.setRequestType(GrantRevokeType.REVOKE);
      req.setRoleName(roleName);
      req.setPrincipalName(userName);
      req.setPrincipalType(principalType);
      req.setGrantOption(grantOption);
      GrantRevokeRoleResponse res = this.client.grant_revoke_role(req);
      if (!res.isSetSuccess()) {
         throw new MetaException("GrantRevokeResponse missing success field");
      } else {
         return res.isSuccess();
      }
   }

   public boolean revoke_privileges(PrivilegeBag privileges, boolean grantOption) throws MetaException, TException {
      GrantRevokePrivilegeRequest req = new GrantRevokePrivilegeRequest();
      req.setRequestType(GrantRevokeType.REVOKE);
      req.setPrivileges(privileges);
      req.setRevokeGrantOption(grantOption);
      GrantRevokePrivilegeResponse res = this.client.grant_revoke_privileges(req);
      if (!res.isSetSuccess()) {
         throw new MetaException("GrantRevokePrivilegeResponse missing success field");
      } else {
         return res.isSuccess();
      }
   }

   public PrincipalPrivilegeSet get_privilege_set(HiveObjectRef hiveObject, String userName, List groupNames) throws MetaException, TException {
      return this.client.get_privilege_set(hiveObject, userName, groupNames);
   }

   public List list_privileges(String principalName, PrincipalType principalType, HiveObjectRef hiveObject) throws MetaException, TException {
      return this.client.list_privileges(principalName, principalType, hiveObject);
   }

   public String getDelegationToken(String renewerKerberosPrincipalName) throws MetaException, TException, IOException {
      String owner = this.conf.getUser();
      return this.getDelegationToken(owner, renewerKerberosPrincipalName);
   }

   public String getDelegationToken(String owner, String renewerKerberosPrincipalName) throws MetaException, TException {
      return this.localMetaStore ? null : this.client.get_delegation_token(owner, renewerKerberosPrincipalName);
   }

   public long renewDelegationToken(String tokenStrForm) throws MetaException, TException {
      return this.localMetaStore ? 0L : this.client.renew_delegation_token(tokenStrForm);
   }

   public void cancelDelegationToken(String tokenStrForm) throws MetaException, TException {
      if (!this.localMetaStore) {
         this.client.cancel_delegation_token(tokenStrForm);
      }
   }

   public boolean addToken(String tokenIdentifier, String delegationToken) throws TException {
      return this.client.add_token(tokenIdentifier, delegationToken);
   }

   public boolean removeToken(String tokenIdentifier) throws TException {
      return this.client.remove_token(tokenIdentifier);
   }

   public String getToken(String tokenIdentifier) throws TException {
      return this.client.get_token(tokenIdentifier);
   }

   public List getAllTokenIdentifiers() throws TException {
      return this.client.get_all_token_identifiers();
   }

   public int addMasterKey(String key) throws MetaException, TException {
      return this.client.add_master_key(key);
   }

   public void updateMasterKey(Integer seqNo, String key) throws NoSuchObjectException, MetaException, TException {
      this.client.update_master_key(seqNo, key);
   }

   public boolean removeMasterKey(Integer keySeq) throws TException {
      return this.client.remove_master_key(keySeq);
   }

   public String[] getMasterKeys() throws TException {
      List<String> keyList = this.client.get_master_keys();
      return (String[])keyList.toArray(new String[keyList.size()]);
   }

   public ValidTxnList getValidTxns() throws TException {
      return TxnUtils.createValidReadTxnList(this.client.get_open_txns(), 0L);
   }

   public ValidTxnList getValidTxns(long currentTxn) throws TException {
      return TxnUtils.createValidReadTxnList(this.client.get_open_txns(), currentTxn);
   }

   public long openTxn(String user) throws TException {
      OpenTxnsResponse txns = this.openTxns(user, 1);
      return (Long)txns.getTxn_ids().get(0);
   }

   public OpenTxnsResponse openTxns(String user, int numTxns) throws TException {
      String hostname = null;

      try {
         hostname = InetAddress.getLocalHost().getHostName();
      } catch (UnknownHostException e) {
         LOG.error("Unable to resolve my host name " + e.getMessage());
         throw new RuntimeException(e);
      }

      return this.client.open_txns(new OpenTxnRequest(numTxns, user, hostname));
   }

   public void rollbackTxn(long txnid) throws NoSuchTxnException, TException {
      this.client.abort_txn(new AbortTxnRequest(txnid));
   }

   public void commitTxn(long txnid) throws NoSuchTxnException, TxnAbortedException, TException {
      this.client.commit_txn(new CommitTxnRequest(txnid));
   }

   public GetOpenTxnsInfoResponse showTxns() throws TException {
      return this.client.get_open_txns_info();
   }

   public void abortTxns(List txnids) throws NoSuchTxnException, TException {
      this.client.abort_txns(new AbortTxnsRequest(txnids));
   }

   public LockResponse lock(LockRequest request) throws NoSuchTxnException, TxnAbortedException, TException {
      return this.client.lock(request);
   }

   public LockResponse checkLock(long lockid) throws NoSuchTxnException, TxnAbortedException, NoSuchLockException, TException {
      return this.client.check_lock(new CheckLockRequest(lockid));
   }

   public void unlock(long lockid) throws NoSuchLockException, TxnOpenException, TException {
      this.client.unlock(new UnlockRequest(lockid));
   }

   /** @deprecated */
   @Deprecated
   public ShowLocksResponse showLocks() throws TException {
      return this.client.show_locks(new ShowLocksRequest());
   }

   public ShowLocksResponse showLocks(ShowLocksRequest request) throws TException {
      return this.client.show_locks(request);
   }

   public void heartbeat(long txnid, long lockid) throws NoSuchLockException, NoSuchTxnException, TxnAbortedException, TException {
      HeartbeatRequest hb = new HeartbeatRequest();
      hb.setLockid(lockid);
      hb.setTxnid(txnid);
      this.client.heartbeat(hb);
   }

   public HeartbeatTxnRangeResponse heartbeatTxnRange(long min, long max) throws NoSuchTxnException, TxnAbortedException, TException {
      HeartbeatTxnRangeRequest rqst = new HeartbeatTxnRangeRequest(min, max);
      return this.client.heartbeat_txn_range(rqst);
   }

   /** @deprecated */
   @Deprecated
   public void compact(String dbname, String tableName, String partitionName, CompactionType type) throws TException {
      CompactionRequest cr = new CompactionRequest();
      if (dbname == null) {
         cr.setDbname("default");
      } else {
         cr.setDbname(dbname);
      }

      cr.setTablename(tableName);
      if (partitionName != null) {
         cr.setPartitionname(partitionName);
      }

      cr.setType(type);
      this.client.compact(cr);
   }

   /** @deprecated */
   @Deprecated
   public void compact(String dbname, String tableName, String partitionName, CompactionType type, Map tblproperties) throws TException {
      this.compact2(dbname, tableName, partitionName, type, tblproperties);
   }

   public CompactionResponse compact2(String dbname, String tableName, String partitionName, CompactionType type, Map tblproperties) throws TException {
      CompactionRequest cr = new CompactionRequest();
      if (dbname == null) {
         cr.setDbname("default");
      } else {
         cr.setDbname(dbname);
      }

      cr.setTablename(tableName);
      if (partitionName != null) {
         cr.setPartitionname(partitionName);
      }

      cr.setType(type);
      cr.setProperties(tblproperties);
      return this.client.compact2(cr);
   }

   public ShowCompactResponse showCompactions() throws TException {
      return this.client.show_compact(new ShowCompactRequest());
   }

   /** @deprecated */
   @Deprecated
   public void addDynamicPartitions(long txnId, String dbName, String tableName, List partNames) throws TException {
      this.client.add_dynamic_partitions(new AddDynamicPartitions(txnId, dbName, tableName, partNames));
   }

   public void addDynamicPartitions(long txnId, String dbName, String tableName, List partNames, DataOperationType operationType) throws TException {
      AddDynamicPartitions adp = new AddDynamicPartitions(txnId, dbName, tableName, partNames);
      adp.setOperationType(operationType);
      this.client.add_dynamic_partitions(adp);
   }

   public void insertTable(Table table, boolean overwrite) throws MetaException {
      boolean failed = true;
      HiveMetaHook hook = this.getHook(table);
      if (hook != null && hook instanceof DefaultHiveMetaHook) {
         DefaultHiveMetaHook hiveMetaHook = (DefaultHiveMetaHook)hook;

         try {
            hiveMetaHook.commitInsertTable(table, overwrite);
            failed = false;
         } finally {
            if (failed) {
               hiveMetaHook.rollbackInsertTable(table, overwrite);
            }

         }

      }
   }

   @LimitedPrivate({"HCatalog"})
   public NotificationEventResponse getNextNotification(long lastEventId, int maxEvents, IMetaStoreClient.NotificationFilter filter) throws TException {
      NotificationEventRequest rqst = new NotificationEventRequest(lastEventId);
      rqst.setMaxEvents(maxEvents);
      NotificationEventResponse rsp = this.client.get_next_notification(rqst);
      LOG.debug("Got back " + rsp.getEventsSize() + " events");
      if (filter == null) {
         return rsp;
      } else {
         NotificationEventResponse filtered = new NotificationEventResponse();
         if (rsp != null && rsp.getEvents() != null) {
            for(NotificationEvent e : rsp.getEvents()) {
               if (filter.accept(e)) {
                  filtered.addToEvents(e);
               }
            }
         }

         return filtered;
      }
   }

   @LimitedPrivate({"HCatalog"})
   public CurrentNotificationEventId getCurrentNotificationEventId() throws TException {
      return this.client.get_current_notificationEventId();
   }

   @LimitedPrivate({"Apache Hive, HCatalog"})
   public FireEventResponse fireListenerEvent(FireEventRequest rqst) throws TException {
      return this.client.fire_listener_event(rqst);
   }

   public static IMetaStoreClient newSynchronizedClient(IMetaStoreClient client) {
      return (IMetaStoreClient)Proxy.newProxyInstance(HiveMetaStoreClient.class.getClassLoader(), new Class[]{IMetaStoreClient.class}, new SynchronizedHandler(client));
   }

   public void markPartitionForEvent(String db_name, String tbl_name, Map partKVs, PartitionEventType eventType) throws MetaException, TException, NoSuchObjectException, UnknownDBException, UnknownTableException, InvalidPartitionException, UnknownPartitionException {
      assert db_name != null;

      assert tbl_name != null;

      assert partKVs != null;

      this.client.markPartitionForEvent(db_name, tbl_name, partKVs, eventType);
   }

   public boolean isPartitionMarkedForEvent(String db_name, String tbl_name, Map partKVs, PartitionEventType eventType) throws MetaException, NoSuchObjectException, UnknownTableException, UnknownDBException, TException, InvalidPartitionException, UnknownPartitionException {
      assert db_name != null;

      assert tbl_name != null;

      assert partKVs != null;

      return this.client.isPartitionMarkedForEvent(db_name, tbl_name, partKVs, eventType);
   }

   public void createFunction(Function func) throws InvalidObjectException, MetaException, TException {
      this.client.create_function(func);
   }

   public void alterFunction(String dbName, String funcName, Function newFunction) throws InvalidObjectException, MetaException, TException {
      this.client.alter_function(dbName, funcName, newFunction);
   }

   public void dropFunction(String dbName, String funcName) throws MetaException, NoSuchObjectException, InvalidObjectException, InvalidInputException, TException {
      this.client.drop_function(dbName, funcName);
   }

   public Function getFunction(String dbName, String funcName) throws MetaException, TException {
      Function f = this.client.get_function(dbName, funcName);
      return this.fastpath ? f : this.deepCopy(f);
   }

   public List getFunctions(String dbName, String pattern) throws MetaException, TException {
      return this.client.get_functions(dbName, pattern);
   }

   public GetAllFunctionsResponse getAllFunctions() throws MetaException, TException {
      return this.client.get_all_functions();
   }

   protected void create_table_with_environment_context(Table tbl, EnvironmentContext envContext) throws AlreadyExistsException, InvalidObjectException, MetaException, NoSuchObjectException, TException {
      this.client.create_table_with_environment_context(tbl, envContext);
   }

   protected void drop_table_with_environment_context(String dbname, String name, boolean deleteData, EnvironmentContext envContext) throws MetaException, TException, NoSuchObjectException, UnsupportedOperationException {
      this.client.drop_table_with_environment_context(dbname, name, deleteData, envContext);
   }

   public AggrStats getAggrColStatsFor(String dbName, String tblName, List colNames, List partNames) throws NoSuchObjectException, MetaException, TException {
      if (!colNames.isEmpty() && !partNames.isEmpty()) {
         PartitionsStatsRequest req = new PartitionsStatsRequest(dbName, tblName, colNames, partNames);
         return this.client.get_aggr_stats_for(req);
      } else {
         LOG.debug("Columns is empty or partNames is empty : Short-circuiting stats eval on client side.");
         return new AggrStats(new ArrayList(), 0L);
      }
   }

   public Iterable getFileMetadata(final List fileIds) throws TException {
      return new MetastoreMapIterable() {
         private int listIndex = 0;

         protected Map fetchNextBatch() throws TException {
            if (this.listIndex == fileIds.size()) {
               return null;
            } else {
               int endIndex = Math.min(this.listIndex + HiveMetaStoreClient.this.fileMetadataBatchSize, fileIds.size());
               List<Long> subList = fileIds.subList(this.listIndex, endIndex);
               GetFileMetadataResult resp = HiveMetaStoreClient.this.sendGetFileMetadataReq(subList);
               if (!resp.isIsSupported()) {
                  return null;
               } else {
                  this.listIndex = endIndex;
                  return resp.getMetadata();
               }
            }
         }
      };
   }

   private GetFileMetadataResult sendGetFileMetadataReq(List fileIds) throws TException {
      return this.client.get_file_metadata(new GetFileMetadataRequest(fileIds));
   }

   public Iterable getFileMetadataBySarg(final List fileIds, final ByteBuffer sarg, final boolean doGetFooters) throws TException {
      return new MetastoreMapIterable() {
         private int listIndex = 0;

         protected Map fetchNextBatch() throws TException {
            if (this.listIndex == fileIds.size()) {
               return null;
            } else {
               int endIndex = Math.min(this.listIndex + HiveMetaStoreClient.this.fileMetadataBatchSize, fileIds.size());
               List<Long> subList = fileIds.subList(this.listIndex, endIndex);
               GetFileMetadataByExprResult resp = HiveMetaStoreClient.this.sendGetFileMetadataBySargReq(sarg, subList, doGetFooters);
               if (!resp.isIsSupported()) {
                  return null;
               } else {
                  this.listIndex = endIndex;
                  return resp.getMetadata();
               }
            }
         }
      };
   }

   private GetFileMetadataByExprResult sendGetFileMetadataBySargReq(ByteBuffer sarg, List fileIds, boolean doGetFooters) throws TException {
      GetFileMetadataByExprRequest req = new GetFileMetadataByExprRequest(fileIds, sarg);
      req.setDoGetFooters(doGetFooters);
      return this.client.get_file_metadata_by_expr(req);
   }

   public void clearFileMetadata(List fileIds) throws TException {
      ClearFileMetadataRequest req = new ClearFileMetadataRequest();
      req.setFileIds(fileIds);
      this.client.clear_file_metadata(req);
   }

   public void putFileMetadata(List fileIds, List metadata) throws TException {
      PutFileMetadataRequest req = new PutFileMetadataRequest();
      req.setFileIds(fileIds);
      req.setMetadata(metadata);
      this.client.put_file_metadata(req);
   }

   public boolean isSameConfObj(HiveConf c) {
      return this.conf == c;
   }

   public boolean cacheFileMetadata(String dbName, String tableName, String partName, boolean allParts) throws TException {
      CacheFileMetadataRequest req = new CacheFileMetadataRequest();
      req.setDbName(dbName);
      req.setTblName(tableName);
      if (partName != null) {
         req.setPartName(partName);
      } else {
         req.setIsAllParts(allParts);
      }

      CacheFileMetadataResult result = this.client.cache_file_metadata(req);
      return result.isIsSupported();
   }

   static {
      TEST_VERSION = new ClientCapabilities(Lists.newArrayList(new ClientCapability[]{ClientCapability.TEST_CAPABILITY}));
      connCount = new AtomicInteger(0);
      LOG = LoggerFactory.getLogger("hive.metastore");
   }

   private static class SynchronizedHandler implements InvocationHandler {
      private final IMetaStoreClient client;

      SynchronizedHandler(IMetaStoreClient client) {
         this.client = client;
      }

      public synchronized Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
         try {
            return method.invoke(this.client, args);
         } catch (InvocationTargetException e) {
            throw e.getTargetException();
         }
      }
   }

   public abstract static class MetastoreMapIterable implements Iterable, Iterator {
      private Iterator currentIter;

      protected abstract Map fetchNextBatch() throws TException;

      public Iterator iterator() {
         return this;
      }

      public boolean hasNext() {
         this.ensureCurrentBatch();
         return this.currentIter != null;
      }

      private void ensureCurrentBatch() {
         if (this.currentIter == null || !this.currentIter.hasNext()) {
            this.currentIter = null;

            Map<K, V> currentBatch;
            do {
               try {
                  currentBatch = this.fetchNextBatch();
               } catch (TException ex) {
                  throw new RuntimeException(ex);
               }

               if (currentBatch == null) {
                  return;
               }
            } while(currentBatch.isEmpty());

            this.currentIter = currentBatch.entrySet().iterator();
         }
      }

      public Map.Entry next() {
         this.ensureCurrentBatch();
         if (this.currentIter == null) {
            throw new NoSuchElementException();
         } else {
            return (Map.Entry)this.currentIter.next();
         }
      }

      public void remove() {
         throw new UnsupportedOperationException();
      }
   }
}
