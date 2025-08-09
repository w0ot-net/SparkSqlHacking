package org.apache.hadoop.hive.metastore;

import com.facebook.fb303.FacebookBase;
import com.facebook.fb303.fb_status;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimaps;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.PrivilegedExceptionAction;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Formatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Timer;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Pattern;
import javax.jdo.JDOException;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.common.FileUtils;
import org.apache.hadoop.hive.common.JvmPauseMonitor;
import org.apache.hadoop.hive.common.LogUtils;
import org.apache.hadoop.hive.common.auth.HiveAuthUtils;
import org.apache.hadoop.hive.common.classification.InterfaceAudience.LimitedPrivate;
import org.apache.hadoop.hive.common.classification.InterfaceStability.Evolving;
import org.apache.hadoop.hive.common.cli.CommonCliOptions;
import org.apache.hadoop.hive.common.metrics.common.Metrics;
import org.apache.hadoop.hive.common.metrics.common.MetricsFactory;
import org.apache.hadoop.hive.common.metrics.common.MetricsVariable;
import org.apache.hadoop.hive.conf.HiveConf;
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
import org.apache.hadoop.hive.metastore.api.ClearFileMetadataResult;
import org.apache.hadoop.hive.metastore.api.ClientCapabilities;
import org.apache.hadoop.hive.metastore.api.ClientCapability;
import org.apache.hadoop.hive.metastore.api.ColumnStatistics;
import org.apache.hadoop.hive.metastore.api.ColumnStatisticsDesc;
import org.apache.hadoop.hive.metastore.api.ColumnStatisticsObj;
import org.apache.hadoop.hive.metastore.api.CommitTxnRequest;
import org.apache.hadoop.hive.metastore.api.CompactionRequest;
import org.apache.hadoop.hive.metastore.api.CompactionResponse;
import org.apache.hadoop.hive.metastore.api.ConfigValSecurityException;
import org.apache.hadoop.hive.metastore.api.CurrentNotificationEventId;
import org.apache.hadoop.hive.metastore.api.Database;
import org.apache.hadoop.hive.metastore.api.DropConstraintRequest;
import org.apache.hadoop.hive.metastore.api.DropPartitionsExpr;
import org.apache.hadoop.hive.metastore.api.DropPartitionsRequest;
import org.apache.hadoop.hive.metastore.api.DropPartitionsResult;
import org.apache.hadoop.hive.metastore.api.EnvironmentContext;
import org.apache.hadoop.hive.metastore.api.FieldSchema;
import org.apache.hadoop.hive.metastore.api.FileMetadataExprType;
import org.apache.hadoop.hive.metastore.api.FireEventRequest;
import org.apache.hadoop.hive.metastore.api.FireEventRequestData;
import org.apache.hadoop.hive.metastore.api.FireEventResponse;
import org.apache.hadoop.hive.metastore.api.ForeignKeysRequest;
import org.apache.hadoop.hive.metastore.api.ForeignKeysResponse;
import org.apache.hadoop.hive.metastore.api.Function;
import org.apache.hadoop.hive.metastore.api.GetAllFunctionsResponse;
import org.apache.hadoop.hive.metastore.api.GetFileMetadataByExprRequest;
import org.apache.hadoop.hive.metastore.api.GetFileMetadataByExprResult;
import org.apache.hadoop.hive.metastore.api.GetFileMetadataRequest;
import org.apache.hadoop.hive.metastore.api.GetFileMetadataResult;
import org.apache.hadoop.hive.metastore.api.GetOpenTxnsInfoResponse;
import org.apache.hadoop.hive.metastore.api.GetOpenTxnsResponse;
import org.apache.hadoop.hive.metastore.api.GetPrincipalsInRoleRequest;
import org.apache.hadoop.hive.metastore.api.GetPrincipalsInRoleResponse;
import org.apache.hadoop.hive.metastore.api.GetRoleGrantsForPrincipalRequest;
import org.apache.hadoop.hive.metastore.api.GetRoleGrantsForPrincipalResponse;
import org.apache.hadoop.hive.metastore.api.GetTableRequest;
import org.apache.hadoop.hive.metastore.api.GetTableResult;
import org.apache.hadoop.hive.metastore.api.GetTablesRequest;
import org.apache.hadoop.hive.metastore.api.GetTablesResult;
import org.apache.hadoop.hive.metastore.api.GrantRevokePrivilegeRequest;
import org.apache.hadoop.hive.metastore.api.GrantRevokePrivilegeResponse;
import org.apache.hadoop.hive.metastore.api.GrantRevokeRoleRequest;
import org.apache.hadoop.hive.metastore.api.GrantRevokeRoleResponse;
import org.apache.hadoop.hive.metastore.api.HeartbeatRequest;
import org.apache.hadoop.hive.metastore.api.HeartbeatTxnRangeRequest;
import org.apache.hadoop.hive.metastore.api.HeartbeatTxnRangeResponse;
import org.apache.hadoop.hive.metastore.api.HiveObjectPrivilege;
import org.apache.hadoop.hive.metastore.api.HiveObjectRef;
import org.apache.hadoop.hive.metastore.api.HiveObjectType;
import org.apache.hadoop.hive.metastore.api.Index;
import org.apache.hadoop.hive.metastore.api.InvalidInputException;
import org.apache.hadoop.hive.metastore.api.InvalidObjectException;
import org.apache.hadoop.hive.metastore.api.InvalidOperationException;
import org.apache.hadoop.hive.metastore.api.InvalidPartitionException;
import org.apache.hadoop.hive.metastore.api.LockRequest;
import org.apache.hadoop.hive.metastore.api.LockResponse;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.metastore.api.MetadataPpdResult;
import org.apache.hadoop.hive.metastore.api.NoSuchLockException;
import org.apache.hadoop.hive.metastore.api.NoSuchObjectException;
import org.apache.hadoop.hive.metastore.api.NoSuchTxnException;
import org.apache.hadoop.hive.metastore.api.NotificationEventRequest;
import org.apache.hadoop.hive.metastore.api.NotificationEventResponse;
import org.apache.hadoop.hive.metastore.api.OpenTxnRequest;
import org.apache.hadoop.hive.metastore.api.OpenTxnsResponse;
import org.apache.hadoop.hive.metastore.api.Partition;
import org.apache.hadoop.hive.metastore.api.PartitionEventType;
import org.apache.hadoop.hive.metastore.api.PartitionListComposingSpec;
import org.apache.hadoop.hive.metastore.api.PartitionSpec;
import org.apache.hadoop.hive.metastore.api.PartitionSpecWithSharedSD;
import org.apache.hadoop.hive.metastore.api.PartitionValuesRequest;
import org.apache.hadoop.hive.metastore.api.PartitionValuesResponse;
import org.apache.hadoop.hive.metastore.api.PartitionWithoutSD;
import org.apache.hadoop.hive.metastore.api.PartitionsByExprRequest;
import org.apache.hadoop.hive.metastore.api.PartitionsByExprResult;
import org.apache.hadoop.hive.metastore.api.PartitionsStatsRequest;
import org.apache.hadoop.hive.metastore.api.PartitionsStatsResult;
import org.apache.hadoop.hive.metastore.api.PrimaryKeysRequest;
import org.apache.hadoop.hive.metastore.api.PrimaryKeysResponse;
import org.apache.hadoop.hive.metastore.api.PrincipalPrivilegeSet;
import org.apache.hadoop.hive.metastore.api.PrincipalType;
import org.apache.hadoop.hive.metastore.api.PrivilegeBag;
import org.apache.hadoop.hive.metastore.api.PrivilegeGrantInfo;
import org.apache.hadoop.hive.metastore.api.PutFileMetadataRequest;
import org.apache.hadoop.hive.metastore.api.PutFileMetadataResult;
import org.apache.hadoop.hive.metastore.api.RequestPartsSpec;
import org.apache.hadoop.hive.metastore.api.Role;
import org.apache.hadoop.hive.metastore.api.RolePrincipalGrant;
import org.apache.hadoop.hive.metastore.api.SQLForeignKey;
import org.apache.hadoop.hive.metastore.api.SQLPrimaryKey;
import org.apache.hadoop.hive.metastore.api.SetPartitionsStatsRequest;
import org.apache.hadoop.hive.metastore.api.ShowCompactRequest;
import org.apache.hadoop.hive.metastore.api.ShowCompactResponse;
import org.apache.hadoop.hive.metastore.api.ShowLocksRequest;
import org.apache.hadoop.hive.metastore.api.ShowLocksResponse;
import org.apache.hadoop.hive.metastore.api.SkewedInfo;
import org.apache.hadoop.hive.metastore.api.StorageDescriptor;
import org.apache.hadoop.hive.metastore.api.Table;
import org.apache.hadoop.hive.metastore.api.TableMeta;
import org.apache.hadoop.hive.metastore.api.TableStatsRequest;
import org.apache.hadoop.hive.metastore.api.TableStatsResult;
import org.apache.hadoop.hive.metastore.api.ThriftHiveMetastore;
import org.apache.hadoop.hive.metastore.api.TxnAbortedException;
import org.apache.hadoop.hive.metastore.api.TxnOpenException;
import org.apache.hadoop.hive.metastore.api.Type;
import org.apache.hadoop.hive.metastore.api.UnknownDBException;
import org.apache.hadoop.hive.metastore.api.UnknownPartitionException;
import org.apache.hadoop.hive.metastore.api.UnknownTableException;
import org.apache.hadoop.hive.metastore.api.UnlockRequest;
import org.apache.hadoop.hive.metastore.events.AddIndexEvent;
import org.apache.hadoop.hive.metastore.events.AddPartitionEvent;
import org.apache.hadoop.hive.metastore.events.AlterIndexEvent;
import org.apache.hadoop.hive.metastore.events.AlterPartitionEvent;
import org.apache.hadoop.hive.metastore.events.ConfigChangeEvent;
import org.apache.hadoop.hive.metastore.events.CreateDatabaseEvent;
import org.apache.hadoop.hive.metastore.events.CreateFunctionEvent;
import org.apache.hadoop.hive.metastore.events.CreateTableEvent;
import org.apache.hadoop.hive.metastore.events.DropDatabaseEvent;
import org.apache.hadoop.hive.metastore.events.DropFunctionEvent;
import org.apache.hadoop.hive.metastore.events.DropIndexEvent;
import org.apache.hadoop.hive.metastore.events.DropPartitionEvent;
import org.apache.hadoop.hive.metastore.events.DropTableEvent;
import org.apache.hadoop.hive.metastore.events.EventCleanerTask;
import org.apache.hadoop.hive.metastore.events.InsertEvent;
import org.apache.hadoop.hive.metastore.events.LoadPartitionDoneEvent;
import org.apache.hadoop.hive.metastore.events.PreAddIndexEvent;
import org.apache.hadoop.hive.metastore.events.PreAddPartitionEvent;
import org.apache.hadoop.hive.metastore.events.PreAlterIndexEvent;
import org.apache.hadoop.hive.metastore.events.PreAlterPartitionEvent;
import org.apache.hadoop.hive.metastore.events.PreAlterTableEvent;
import org.apache.hadoop.hive.metastore.events.PreAuthorizationCallEvent;
import org.apache.hadoop.hive.metastore.events.PreCreateDatabaseEvent;
import org.apache.hadoop.hive.metastore.events.PreCreateTableEvent;
import org.apache.hadoop.hive.metastore.events.PreDropDatabaseEvent;
import org.apache.hadoop.hive.metastore.events.PreDropIndexEvent;
import org.apache.hadoop.hive.metastore.events.PreDropPartitionEvent;
import org.apache.hadoop.hive.metastore.events.PreDropTableEvent;
import org.apache.hadoop.hive.metastore.events.PreEventContext;
import org.apache.hadoop.hive.metastore.events.PreLoadPartitionDoneEvent;
import org.apache.hadoop.hive.metastore.events.PreReadDatabaseEvent;
import org.apache.hadoop.hive.metastore.events.PreReadTableEvent;
import org.apache.hadoop.hive.metastore.filemeta.OrcFileMetadataHandler;
import org.apache.hadoop.hive.metastore.messaging.EventMessage;
import org.apache.hadoop.hive.metastore.partition.spec.PartitionSpecProxy;
import org.apache.hadoop.hive.metastore.txn.TxnStore;
import org.apache.hadoop.hive.metastore.txn.TxnUtils;
import org.apache.hadoop.hive.serde2.Deserializer;
import org.apache.hadoop.hive.serde2.SerDeException;
import org.apache.hadoop.hive.shims.HadoopShims;
import org.apache.hadoop.hive.shims.ShimLoader;
import org.apache.hadoop.hive.shims.Utils;
import org.apache.hadoop.hive.thrift.HadoopThriftAuthBridge;
import org.apache.hadoop.hive.thrift.HiveDelegationTokenManager;
import org.apache.hadoop.hive.thrift.TUGIContainingTransport;
import org.apache.hadoop.hive.thrift.HadoopThriftAuthBridge.Server.ServerMode;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.hadoop.util.ReflectionUtils;
import org.apache.hadoop.util.StringUtils;
import org.apache.hive.common.util.HiveStringUtils;
import org.apache.hive.common.util.ShutdownHookManager;
import org.apache.thrift.TException;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.ServerContext;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServerEventHandler;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.apache.thrift.transport.TTransportFactory;
import org.apache.thrift.transport.layered.TFramedTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HiveMetaStore extends ThriftHiveMetastore {
   public static final Logger LOG = LoggerFactory.getLogger(HiveMetaStore.class);
   private static boolean isMetaStoreRemote = false;
   @VisibleForTesting
   static boolean TEST_TIMEOUT_ENABLED = false;
   @VisibleForTesting
   static long TEST_TIMEOUT_VALUE = -1L;
   public static final ThreadLocal PARTITION_DATE_FORMAT = new ThreadLocal() {
      protected DateFormat initialValue() {
         DateFormat val = new SimpleDateFormat("yyyy-MM-dd");
         val.setLenient(false);
         return val;
      }
   };
   public static final String ADMIN = "admin";
   public static final String PUBLIC = "public";
   private static HadoopThriftAuthBridge.Server saslServer;
   private static HiveDelegationTokenManager delegationTokenManager;
   private static boolean useSasl;
   public static final String NO_FILTER_STRING = "";
   public static final int UNLIMITED_MAX_PARTITIONS = -1;
   private static int nextThreadId = 1000000;

   public static IHMSHandler newRetryingHMSHandler(IHMSHandler baseHandler, HiveConf hiveConf) throws MetaException {
      return newRetryingHMSHandler(baseHandler, hiveConf, false);
   }

   public static IHMSHandler newRetryingHMSHandler(IHMSHandler baseHandler, HiveConf hiveConf, boolean local) throws MetaException {
      return RetryingHMSHandler.getProxy(hiveConf, baseHandler, local);
   }

   public static ThriftHiveMetastore.Iface newRetryingHMSHandler(String name, HiveConf conf, boolean local) throws MetaException {
      HMSHandler baseHandler = new HMSHandler(name, conf, false);
      return RetryingHMSHandler.getProxy(conf, baseHandler, local);
   }

   public static void cancelDelegationToken(String tokenStrForm) throws IOException {
      delegationTokenManager.cancelDelegationToken(tokenStrForm);
   }

   public static String getDelegationToken(String owner, String renewer, String remoteAddr) throws IOException, InterruptedException {
      return delegationTokenManager.getDelegationToken(owner, renewer, remoteAddr);
   }

   public static boolean isMetaStoreRemote() {
      return isMetaStoreRemote;
   }

   public static long renewDelegationToken(String tokenStrForm) throws IOException {
      return delegationTokenManager.renewDelegationToken(tokenStrForm);
   }

   public static void main(String[] args) throws Throwable {
      HiveConf.setLoadMetastoreConfig(true);
      final HiveConf conf = new HiveConf(HMSHandler.class);
      HiveMetastoreCli cli = new HiveMetastoreCli(conf);
      cli.parse(args);
      final boolean isCliVerbose = cli.isVerbose();
      Properties hiveconf = cli.addHiveconfToSystemProperties();
      if (System.getProperty("log4j.configurationFile") == null) {
         try {
            LogUtils.initHiveLog4j();
         } catch (LogUtils.LogInitializationException e) {
            HiveMetaStore.HMSHandler.LOG.warn(e.getMessage());
         }
      }

      HiveStringUtils.startupShutdownMessage(HiveMetaStore.class, args, LOG);

      try {
         String msg = "Starting hive metastore on port " + cli.port;
         HiveMetaStore.HMSHandler.LOG.info(msg);
         if (cli.isVerbose()) {
            System.err.println(msg);
         }

         for(Map.Entry item : hiveconf.entrySet()) {
            conf.set((String)item.getKey(), (String)item.getValue());
         }

         ShutdownHookManager.addShutdownHook(new Runnable() {
            public void run() {
               String shutdownMsg = "Shutting down hive metastore.";
               HiveMetaStore.HMSHandler.LOG.info(shutdownMsg);
               if (isCliVerbose) {
                  System.err.println(shutdownMsg);
               }

               if (conf.getBoolVar(ConfVars.METASTORE_METRICS)) {
                  try {
                     MetricsFactory.close();
                  } catch (Exception e) {
                     HiveMetaStore.LOG.error("error in Metrics deinit: " + e.getClass().getName() + " " + e.getMessage(), e);
                  }
               }

            }
         });
         if (conf.getBoolVar(ConfVars.METASTORE_METRICS)) {
            try {
               MetricsFactory.init(conf);
            } catch (Exception e) {
               LOG.error("error in Metrics init: " + e.getClass().getName() + " " + e.getMessage(), e);
            }
         }

         Lock startLock = new ReentrantLock();
         Condition startCondition = startLock.newCondition();
         AtomicBoolean startedServing = new AtomicBoolean();
         startMetaStoreThreads(conf, startLock, startCondition, startedServing);
         startMetaStore(cli.getPort(), ShimLoader.getHadoopThriftAuthBridge(), conf, startLock, startCondition, startedServing);
      } catch (Throwable t) {
         HiveMetaStore.HMSHandler.LOG.error("Metastore Thrift Server threw an exception...", t);
         throw t;
      }
   }

   public static void startMetaStore(int port, HadoopThriftAuthBridge bridge) throws Throwable {
      startMetaStore(port, bridge, new HiveConf(HMSHandler.class), (Lock)null, (Condition)null, (AtomicBoolean)null);
   }

   public static void startMetaStore(int port, HadoopThriftAuthBridge bridge, HiveConf conf) throws Throwable {
      startMetaStore(port, bridge, conf, (Lock)null, (Condition)null, (AtomicBoolean)null);
   }

   public static void startMetaStore(int port, HadoopThriftAuthBridge bridge, HiveConf conf, Lock startLock, Condition startCondition, AtomicBoolean startedServing) throws Throwable {
      try {
         isMetaStoreRemote = true;
         long maxMessageSize = conf.getLongVar(ConfVars.METASTORESERVERMAXMESSAGESIZE);
         int minWorkerThreads = conf.getIntVar(ConfVars.METASTORESERVERMINTHREADS);
         int maxWorkerThreads = conf.getIntVar(ConfVars.METASTORESERVERMAXTHREADS);
         boolean tcpKeepAlive = conf.getBoolVar(ConfVars.METASTORE_TCP_KEEP_ALIVE);
         boolean useFramedTransport = conf.getBoolVar(ConfVars.METASTORE_USE_THRIFT_FRAMED_TRANSPORT);
         boolean useCompactProtocol = conf.getBoolVar(ConfVars.METASTORE_USE_THRIFT_COMPACT_PROTOCOL);
         boolean useSSL = conf.getBoolVar(ConfVars.HIVE_METASTORE_USE_SSL);
         useSasl = conf.getBoolVar(ConfVars.METASTORE_USE_THRIFT_SASL);
         TProtocolFactory protocolFactory;
         TProtocolFactory inputProtoFactory;
         if (useCompactProtocol) {
            protocolFactory = new TCompactProtocol.Factory();
            inputProtoFactory = new TCompactProtocol.Factory(maxMessageSize, maxMessageSize);
         } else {
            protocolFactory = new TBinaryProtocol.Factory();
            inputProtoFactory = new TBinaryProtocol.Factory(true, true, maxMessageSize, maxMessageSize);
         }

         HMSHandler baseHandler = new HMSHandler("new db based metaserver", conf, false);
         IHMSHandler handler = newRetryingHMSHandler(baseHandler, conf);
         TServerSocket serverSocket = null;
         TProcessor processor;
         TTransportFactory transFactory;
         Object var27;
         if (useSasl) {
            if (useFramedTransport) {
               throw new HiveMetaException("Framed transport is not supported with SASL enabled.");
            }

            saslServer = bridge.createServer(conf.getVar(ConfVars.METASTORE_KERBEROS_KEYTAB_FILE), conf.getVar(ConfVars.METASTORE_KERBEROS_PRINCIPAL));
            delegationTokenManager = new HiveDelegationTokenManager();
            delegationTokenManager.startDelegationTokenSecretManager(conf, baseHandler, ServerMode.METASTORE);
            saslServer.setSecretManager(delegationTokenManager.getSecretManager());
            transFactory = saslServer.createTransportFactory(MetaStoreUtils.getMetaStoreSaslProperties(conf));
            processor = saslServer.wrapProcessor(new ThriftHiveMetastore.Processor(handler));
            var27 = HiveAuthUtils.getServerSocket((String)null, port);
            LOG.info("Starting DB backed MetaStore Server in Secure Mode");
         } else {
            if (conf.getBoolVar(ConfVars.METASTORE_EXECUTE_SET_UGI)) {
               transFactory = (TTransportFactory)(useFramedTransport ? new ChainedTTransportFactory(new TFramedTransport.Factory(), new TUGIContainingTransport.Factory()) : new TUGIContainingTransport.Factory());
               processor = new TUGIBasedProcessor(handler);
               LOG.info("Starting DB backed MetaStore Server with SetUGI enabled");
            } else {
               transFactory = (TTransportFactory)(useFramedTransport ? new TFramedTransport.Factory() : new TTransportFactory());
               processor = new TSetIpAddressProcessor(handler);
               LOG.info("Starting DB backed MetaStore Server");
            }

            List<String> sslVersionBlacklist = new ArrayList();

            for(String sslVersion : conf.getVar(ConfVars.HIVE_SSL_PROTOCOL_BLACKLIST).split(",")) {
               sslVersionBlacklist.add(sslVersion);
            }

            if (!useSSL) {
               var27 = HiveAuthUtils.getServerSocket((String)null, port);
            } else {
               String keyStorePath = conf.getVar(ConfVars.HIVE_METASTORE_SSL_KEYSTORE_PATH).trim();
               if (keyStorePath.isEmpty()) {
                  throw new IllegalArgumentException(ConfVars.HIVE_METASTORE_SSL_KEYSTORE_PASSWORD.varname + " Not configured for SSL connection");
               }

               String keyStorePassword = ShimLoader.getHadoopShims().getPassword(conf, ConfVars.HIVE_METASTORE_SSL_KEYSTORE_PASSWORD.varname);
               var27 = HiveAuthUtils.getServerSSLSocket((String)null, port, keyStorePath, keyStorePassword, sslVersionBlacklist);
            }
         }

         if (tcpKeepAlive) {
            var27 = new TServerSocketKeepAlive((TServerSocket)var27);
         }

         TThreadPoolServer.Args args = ((TThreadPoolServer.Args)((TThreadPoolServer.Args)((TThreadPoolServer.Args)((TThreadPoolServer.Args)(new TThreadPoolServer.Args((TServerTransport)var27)).processor(processor)).transportFactory(transFactory)).protocolFactory(protocolFactory)).inputProtocolFactory(inputProtoFactory)).minWorkerThreads(minWorkerThreads).maxWorkerThreads(maxWorkerThreads);
         TServer tServer = new TThreadPoolServer(args);
         TServerEventHandler tServerEventHandler = new TServerEventHandler() {
            public void preServe() {
            }

            public ServerContext createContext(TProtocol tProtocol, TProtocol tProtocol1) {
               try {
                  Metrics metrics = MetricsFactory.getInstance();
                  if (metrics != null) {
                     metrics.incrementCounter("open_connections");
                  }
               } catch (Exception e) {
                  HiveMetaStore.LOG.warn("Error Reporting Metastore open connection to Metrics system", e);
               }

               return null;
            }

            public void deleteContext(ServerContext serverContext, TProtocol tProtocol, TProtocol tProtocol1) {
               try {
                  Metrics metrics = MetricsFactory.getInstance();
                  if (metrics != null) {
                     metrics.decrementCounter("open_connections");
                  }
               } catch (Exception e) {
                  HiveMetaStore.LOG.warn("Error Reporting Metastore close connection to Metrics system", e);
               }

               HiveMetaStore.cleanupRawStore();
            }

            public void processContext(ServerContext serverContext, TTransport tTransport, TTransport tTransport1) {
            }
         };
         tServer.setServerEventHandler(tServerEventHandler);
         HiveMetaStore.HMSHandler.LOG.info("Started the new metaserver on port [" + port + "]...");
         HiveMetaStore.HMSHandler.LOG.info("Options.minWorkerThreads = " + minWorkerThreads);
         HiveMetaStore.HMSHandler.LOG.info("Options.maxWorkerThreads = " + maxWorkerThreads);
         HiveMetaStore.HMSHandler.LOG.info("TCP keepalive = " + tcpKeepAlive);
         if (startLock != null) {
            signalOtherThreadsToStart(tServer, startLock, startCondition, startedServing);
         }

         tServer.serve();
      } catch (Throwable x) {
         x.printStackTrace();
         HiveMetaStore.HMSHandler.LOG.error(StringUtils.stringifyException(x));
         throw x;
      }
   }

   private static void cleanupRawStore() {
      RawStore rs = HiveMetaStore.HMSHandler.getRawStore();
      if (rs != null) {
         HiveMetaStore.HMSHandler.logInfo("Cleaning up thread local RawStore...");

         try {
            rs.shutdown();
         } finally {
            HiveMetaStore.HMSHandler.threadLocalConf.remove();
            HiveMetaStore.HMSHandler.removeRawStore();
         }

         HiveMetaStore.HMSHandler.logInfo("Done cleaning up thread local RawStore");
      }

   }

   private static void signalOtherThreadsToStart(final TServer server, final Lock startLock, final Condition startCondition, final AtomicBoolean startedServing) {
      Thread t = new Thread() {
         public void run() {
            do {
               try {
                  Thread.sleep(1000L);
               } catch (InterruptedException e) {
                  HiveMetaStore.LOG.warn("Signalling thread was interuppted: " + e.getMessage());
               }
            } while(!server.isServing());

            startLock.lock();

            try {
               startedServing.set(true);
               startCondition.signalAll();
            } finally {
               startLock.unlock();
            }

         }
      };
      t.start();
   }

   private static void startMetaStoreThreads(final HiveConf conf, final Lock startLock, final Condition startCondition, final AtomicBoolean startedServing) {
      Thread t = new Thread() {
         public void run() {
            startLock.lock();

            try {
               JvmPauseMonitor pauseMonitor = new JvmPauseMonitor(conf);
               pauseMonitor.start();
            } catch (Throwable t) {
               HiveMetaStore.LOG.warn("Could not initiate the JvmPauseMonitor thread. GCs and Pauses may not be warned upon.", t);
            }

            try {
               while(!startedServing.get()) {
                  startCondition.await();
               }

               HiveMetaStore.startCompactorInitiator(conf);
               HiveMetaStore.startCompactorWorkers(conf);
               HiveMetaStore.startCompactorCleaner(conf);
               HiveMetaStore.startHouseKeeperService(conf);
            } catch (Throwable e) {
               HiveMetaStore.LOG.error("Failure when starting the compactor, compactions may not happen, " + StringUtils.stringifyException(e));
            } finally {
               startLock.unlock();
            }

            ReplChangeManager.scheduleCMClearer(conf);
         }
      };
      t.setDaemon(true);
      t.setName("Metastore threads starter thread");
      t.start();
   }

   private static void startCompactorInitiator(HiveConf conf) throws Exception {
      if (HiveConf.getBoolVar(conf, ConfVars.HIVE_COMPACTOR_INITIATOR_ON)) {
         MetaStoreThread initiator = instantiateThread("org.apache.hadoop.hive.ql.txn.compactor.Initiator");
         initializeAndStartThread(initiator, conf);
      }

   }

   private static void startCompactorWorkers(HiveConf conf) throws Exception {
      int numWorkers = HiveConf.getIntVar(conf, ConfVars.HIVE_COMPACTOR_WORKER_THREADS);

      for(int i = 0; i < numWorkers; ++i) {
         MetaStoreThread worker = instantiateThread("org.apache.hadoop.hive.ql.txn.compactor.Worker");
         initializeAndStartThread(worker, conf);
      }

   }

   private static void startCompactorCleaner(HiveConf conf) throws Exception {
      if (HiveConf.getBoolVar(conf, ConfVars.HIVE_COMPACTOR_INITIATOR_ON)) {
         MetaStoreThread cleaner = instantiateThread("org.apache.hadoop.hive.ql.txn.compactor.Cleaner");
         initializeAndStartThread(cleaner, conf);
      }

   }

   private static MetaStoreThread instantiateThread(String classname) throws Exception {
      Class c = Class.forName(classname);
      Object o = c.newInstance();
      if (MetaStoreThread.class.isAssignableFrom(o.getClass())) {
         return (MetaStoreThread)o;
      } else {
         String s = classname + " is not an instance of MetaStoreThread.";
         LOG.error(s);
         throw new IOException(s);
      }
   }

   private static void initializeAndStartThread(MetaStoreThread thread, HiveConf conf) throws MetaException {
      LOG.info("Starting metastore thread of type " + thread.getClass().getName());
      thread.setHiveConf(conf);
      thread.setThreadId(nextThreadId++);
      thread.init(new AtomicBoolean(), new AtomicBoolean());
      thread.start();
   }

   private static void startHouseKeeperService(HiveConf conf) throws Exception {
      if (HiveConf.getBoolVar(conf, ConfVars.HIVE_COMPACTOR_INITIATOR_ON)) {
         startHouseKeeperService(conf, Class.forName("org.apache.hadoop.hive.ql.txn.AcidHouseKeeperService"));
         startHouseKeeperService(conf, Class.forName("org.apache.hadoop.hive.ql.txn.AcidCompactionHistoryService"));
         startHouseKeeperService(conf, Class.forName("org.apache.hadoop.hive.ql.txn.AcidWriteSetService"));
      }
   }

   private static void startHouseKeeperService(HiveConf conf, Class c) throws Exception {
      HouseKeeperService houseKeeper = (HouseKeeperService)c.newInstance();

      try {
         houseKeeper.start(conf);
      } catch (Exception ex) {
         LOG.error("Failed to start {}", new Object[]{houseKeeper.getClass() + ".  The system will not handle {} ", houseKeeper.getServiceDescription(), ".  Root Cause: ", ex});
      }

   }

   public static Map createHandlerMap() {
      Map<FileMetadataExprType, FileMetadataHandler> fmHandlers = new HashMap();
      FileMetadataExprType[] var1 = FileMetadataExprType.values();
      int var2 = var1.length;
      int var3 = 0;

      while(var3 < var2) {
         FileMetadataExprType v = var1[var3];
         switch (v) {
            case ORC_SARG:
               fmHandlers.put(v, new OrcFileMetadataHandler());
               ++var3;
               break;
            default:
               throw new AssertionError("Unsupported type " + v);
         }
      }

      return fmHandlers;
   }

   private static final class ChainedTTransportFactory extends TTransportFactory {
      private final TTransportFactory parentTransFactory;
      private final TTransportFactory childTransFactory;

      private ChainedTTransportFactory(TTransportFactory parentTransFactory, TTransportFactory childTransFactory) {
         this.parentTransFactory = parentTransFactory;
         this.childTransFactory = childTransFactory;
      }

      public TTransport getTransport(TTransport trans) throws TTransportException {
         return this.childTransFactory.getTransport(this.parentTransFactory.getTransport(trans));
      }
   }

   public static class HMSHandler extends FacebookBase implements IHMSHandler, ThreadLocalRawStore {
      public static final Logger LOG;
      private final HiveConf hiveConf;
      private static String currentUrl;
      private FileMetadataManager fileMetadataManager;
      private PartitionExpressionProxy expressionProxy;
      private int initDatabaseCount;
      private int initTableCount;
      private int initPartCount;
      private Warehouse wh;
      private static final ThreadLocal threadLocalMS;
      private static final ThreadLocal threadLocalTxn;
      private static final ThreadLocal threadLocalConf;
      private static ExecutorService threadPool;
      public static final String AUDIT_FORMAT = "ugi=%s\tip=%s\tcmd=%s\t";
      public static final Logger auditLog;
      private static final ThreadLocal auditFormatter;
      private static int nextSerialNum;
      private static ThreadLocal threadLocalId;
      private static ThreadLocal threadLocalIpAddress;
      private ClassLoader classLoader;
      private AlterHandler alterHandler;
      private List preListeners;
      private List listeners;
      private List transactionalListeners;
      private List endFunctionListeners;
      private List initListeners;
      private Pattern partitionValidationPattern;
      private final boolean isInTest;
      private static final Map EMPTY_MAP_FM1;
      private static final Map EMPTY_MAP_FM2;

      public static RawStore getRawStore() {
         return (RawStore)threadLocalMS.get();
      }

      public static void removeRawStore() {
         threadLocalMS.remove();
      }

      private static final void logAuditEvent(String cmd) {
         if (cmd != null) {
            UserGroupInformation ugi;
            try {
               ugi = Utils.getUGI();
            } catch (Exception ex) {
               throw new RuntimeException(ex);
            }

            Formatter fmt = (Formatter)auditFormatter.get();
            ((StringBuilder)fmt.out()).setLength(0);
            String address = getIPAddress();
            if (address == null) {
               address = "unknown-ip-addr";
            }

            auditLog.info(fmt.format("ugi=%s\tip=%s\tcmd=%s\t", ugi.getUserName(), address, cmd).toString());
         }
      }

      private static String getIPAddress() {
         if (HiveMetaStore.useSasl) {
            return HiveMetaStore.saslServer != null && HiveMetaStore.saslServer.getRemoteAddress() != null ? HiveMetaStore.saslServer.getRemoteAddress().getHostAddress() : null;
         } else {
            return getThreadLocalIpAddress();
         }
      }

      public static void setThreadLocalIpAddress(String ipAddress) {
         threadLocalIpAddress.set(ipAddress);
      }

      public static String getThreadLocalIpAddress() {
         return (String)threadLocalIpAddress.get();
      }

      public static Integer get() {
         return (Integer)threadLocalId.get();
      }

      public HMSHandler(String name) throws MetaException {
         this(name, new HiveConf(HMSHandler.class), true);
      }

      public HMSHandler(String name, HiveConf conf) throws MetaException {
         this(name, conf, true);
      }

      public HMSHandler(String name, HiveConf conf, boolean init) throws MetaException {
         super(name);
         this.classLoader = Thread.currentThread().getContextClassLoader();
         if (this.classLoader == null) {
            this.classLoader = Configuration.class.getClassLoader();
         }

         this.hiveConf = conf;
         this.isInTest = HiveConf.getBoolVar(this.hiveConf, ConfVars.HIVE_IN_TEST);
         synchronized(HMSHandler.class) {
            if (threadPool == null) {
               int numThreads = HiveConf.getIntVar(conf, ConfVars.METASTORE_FS_HANDLER_THREADS_COUNT);
               threadPool = Executors.newFixedThreadPool(numThreads, (new ThreadFactoryBuilder()).setDaemon(true).setNameFormat("HMSHandler #%d").build());
            }
         }

         if (init) {
            this.init();
         }

      }

      public HiveConf getHiveConf() {
         return this.hiveConf;
      }

      List getTransactionalListeners() {
         return this.transactionalListeners;
      }

      public List getListeners() {
         return this.listeners;
      }

      public void init() throws MetaException {
         this.initListeners = MetaStoreUtils.getMetaStoreListeners(MetaStoreInitListener.class, this.hiveConf, this.hiveConf.getVar(ConfVars.METASTORE_INIT_HOOKS));

         for(MetaStoreInitListener singleInitListener : this.initListeners) {
            MetaStoreInitContext context = new MetaStoreInitContext();
            singleInitListener.onInit(context);
         }

         String alterHandlerName = this.hiveConf.get("hive.metastore.alter.impl", HiveAlterHandler.class.getName());
         this.alterHandler = (AlterHandler)ReflectionUtils.newInstance(MetaStoreUtils.getClass(alterHandlerName), this.hiveConf);
         this.wh = new Warehouse(this.hiveConf);
         synchronized(HMSHandler.class) {
            if (currentUrl == null || !currentUrl.equals(MetaStoreInit.getConnectionURL(this.hiveConf))) {
               this.createDefaultDB();
               this.createDefaultRoles();
               this.addAdminUsers();
               currentUrl = MetaStoreInit.getConnectionURL(this.hiveConf);
            }
         }

         if (this.hiveConf.getBoolVar(ConfVars.METASTORE_METRICS)) {
            try {
               MetricsFactory.init(this.hiveConf);
            } catch (Exception e) {
               LOG.error("error in Metrics init: " + e.getClass().getName() + " " + e.getMessage(), e);
            }
         }

         Metrics metrics = MetricsFactory.getInstance();
         if (metrics != null && this.hiveConf.getBoolVar(ConfVars.METASTORE_INIT_METADATA_COUNT_ENABLED)) {
            LOG.info("Begin calculating metadata count metrics.");
            this.updateMetrics();
            LOG.info("Finished metadata count metrics: " + this.initDatabaseCount + " databases, " + this.initTableCount + " tables, " + this.initPartCount + " partitions.");
            metrics.addGauge("init_total_count_dbs", new MetricsVariable() {
               public Object getValue() {
                  return HMSHandler.this.initDatabaseCount;
               }
            });
            metrics.addGauge("init_total_count_tables", new MetricsVariable() {
               public Object getValue() {
                  return HMSHandler.this.initTableCount;
               }
            });
            metrics.addGauge("init_total_count_partitions", new MetricsVariable() {
               public Object getValue() {
                  return HMSHandler.this.initPartCount;
               }
            });
         }

         this.preListeners = MetaStoreUtils.getMetaStoreListeners(MetaStorePreEventListener.class, this.hiveConf, this.hiveConf.getVar(ConfVars.METASTORE_PRE_EVENT_LISTENERS));
         this.preListeners.add(0, new TransactionalValidationListener(this.hiveConf));
         this.listeners = MetaStoreUtils.getMetaStoreListeners(MetaStoreEventListener.class, this.hiveConf, this.hiveConf.getVar(ConfVars.METASTORE_EVENT_LISTENERS));
         this.listeners.add(new SessionPropertiesListener(this.hiveConf));
         this.listeners.add(new AcidEventListener(this.hiveConf));
         this.transactionalListeners = MetaStoreUtils.getMetaStoreListeners(MetaStoreEventListener.class, this.hiveConf, this.hiveConf.getVar(ConfVars.METASTORE_TRANSACTIONAL_EVENT_LISTENERS));
         if (metrics != null) {
            this.listeners.add(new HMSMetricsListener(this.hiveConf, metrics));
         }

         this.endFunctionListeners = MetaStoreUtils.getMetaStoreListeners(MetaStoreEndFunctionListener.class, this.hiveConf, this.hiveConf.getVar(ConfVars.METASTORE_END_FUNCTION_LISTENERS));
         String partitionValidationRegex = this.hiveConf.getVar(ConfVars.METASTORE_PARTITION_NAME_WHITELIST_PATTERN);
         if (partitionValidationRegex != null && !partitionValidationRegex.isEmpty()) {
            this.partitionValidationPattern = Pattern.compile(partitionValidationRegex);
         } else {
            this.partitionValidationPattern = null;
         }

         long cleanFreq = this.hiveConf.getTimeVar(ConfVars.METASTORE_EVENT_CLEAN_FREQ, TimeUnit.MILLISECONDS);
         if (cleanFreq > 0L) {
            Timer cleaner = new Timer("Metastore Events Cleaner Thread", true);
            cleaner.schedule(new EventCleanerTask(this), cleanFreq, cleanFreq);
         }

         this.expressionProxy = PartFilterExprUtil.createExpressionProxy(this.hiveConf);
         this.fileMetadataManager = new FileMetadataManager(this, this.hiveConf);
      }

      private static String addPrefix(String s) {
         return threadLocalId.get() + ": " + s;
      }

      public void setConf(Configuration conf) {
         threadLocalConf.set(conf);
         RawStore ms = (RawStore)threadLocalMS.get();
         if (ms != null) {
            ms.setConf(conf);
         }

      }

      public Configuration getConf() {
         Configuration conf = (Configuration)threadLocalConf.get();
         if (conf == null) {
            conf = new Configuration(this.hiveConf);
            threadLocalConf.set(conf);
         }

         return conf;
      }

      public Warehouse getWh() {
         return this.wh;
      }

      public void setMetaConf(String key, String value) throws MetaException {
         HiveConf.ConfVars confVar = HiveConf.getMetaConf(key);
         if (confVar == null) {
            throw new MetaException("Invalid configuration key " + key);
         } else {
            String validate = confVar.validate(value);
            if (validate != null) {
               throw new MetaException("Invalid configuration value " + value + " for key " + key + " by " + validate);
            } else {
               Configuration configuration = this.getConf();
               String oldValue = configuration.get(key);
               configuration.set(key, value);

               for(MetaStoreEventListener listener : this.listeners) {
                  listener.onConfigChange(new ConfigChangeEvent(this, key, oldValue, value));
               }

               if (this.transactionalListeners.size() > 0) {
                  ConfigChangeEvent cce = new ConfigChangeEvent(this, key, oldValue, value);

                  for(MetaStoreEventListener transactionalListener : this.transactionalListeners) {
                     transactionalListener.onConfigChange(cce);
                  }
               }

            }
         }
      }

      public String getMetaConf(String key) throws MetaException {
         HiveConf.ConfVars confVar = HiveConf.getMetaConf(key);
         if (confVar == null) {
            throw new MetaException("Invalid configuration key " + key);
         } else {
            return this.getConf().get(key, confVar.getDefaultValue());
         }
      }

      @LimitedPrivate({"HCATALOG"})
      @Evolving
      public RawStore getMS() throws MetaException {
         Configuration conf = this.getConf();
         return getMSForConf(conf);
      }

      public static RawStore getMSForConf(Configuration conf) throws MetaException {
         RawStore ms = (RawStore)threadLocalMS.get();
         if (ms == null) {
            ms = newRawStoreForConf(conf);
            ms.verifySchema();
            threadLocalMS.set(ms);
            ms = (RawStore)threadLocalMS.get();
         }

         return ms;
      }

      private TxnStore getTxnHandler() {
         TxnStore txn = (TxnStore)threadLocalTxn.get();
         if (txn == null) {
            txn = TxnUtils.getTxnStore(this.hiveConf);
            threadLocalTxn.set(txn);
         }

         return txn;
      }

      private static RawStore newRawStoreForConf(Configuration conf) throws MetaException {
         HiveConf hiveConf = new HiveConf(conf, HiveConf.class);
         String rawStoreClassName = hiveConf.getVar(ConfVars.METASTORE_RAW_STORE_IMPL);
         LOG.info(addPrefix("Opening raw store with implementation class:" + rawStoreClassName));
         if (hiveConf.getBoolVar(ConfVars.METASTORE_FASTPATH)) {
            LOG.info("Fastpath, skipping raw store proxy");

            try {
               RawStore rs = (RawStore)MetaStoreUtils.getClass(rawStoreClassName).newInstance();
               rs.setConf(hiveConf);
               return rs;
            } catch (Exception e) {
               LOG.error("Unable to instantiate raw store directly in fastpath mode", e);
               throw new RuntimeException(e);
            }
         } else {
            return RawStoreProxy.getProxy(hiveConf, conf, rawStoreClassName, (Integer)threadLocalId.get());
         }
      }

      private void createDefaultDB_core(RawStore ms) throws MetaException, InvalidObjectException {
         try {
            ms.getDatabase("default");
         } catch (NoSuchObjectException var4) {
            Database db = new Database("default", "Default Hive database", this.wh.getDefaultDatabasePath("default").toString(), (Map)null);
            db.setOwnerName("public");
            db.setOwnerType(PrincipalType.ROLE);
            ms.createDatabase(db);
         }

      }

      private void createDefaultDB() throws MetaException {
         try {
            this.createDefaultDB_core(this.getMS());
         } catch (JDOException e) {
            LOG.warn("Retrying creating default database after error: " + e.getMessage(), e);

            try {
               this.createDefaultDB_core(this.getMS());
            } catch (InvalidObjectException e1) {
               throw new MetaException(e1.getMessage());
            }
         } catch (InvalidObjectException e) {
            throw new MetaException(e.getMessage());
         }

      }

      private void createDefaultRoles() throws MetaException {
         try {
            this.createDefaultRoles_core();
         } catch (JDOException e) {
            LOG.warn("Retrying creating default roles after error: " + e.getMessage(), e);
            this.createDefaultRoles_core();
         }

      }

      private void createDefaultRoles_core() throws MetaException {
         RawStore ms = this.getMS();

         try {
            ms.addRole("admin", "admin");
         } catch (InvalidObjectException e) {
            LOG.debug("admin role already exists", e);
         } catch (NoSuchObjectException e) {
            LOG.warn("Unexpected exception while adding admin roles", e);
         }

         LOG.info("Added admin role in metastore");

         try {
            ms.addRole("public", "public");
         } catch (InvalidObjectException e) {
            LOG.debug("public role already exists", e);
         } catch (NoSuchObjectException e) {
            LOG.warn("Unexpected exception while adding public roles", e);
         }

         LOG.info("Added public role in metastore");
         PrivilegeBag privs = new PrivilegeBag();
         privs.addToPrivileges(new HiveObjectPrivilege(new HiveObjectRef(HiveObjectType.GLOBAL, (String)null, (String)null, (List)null, (String)null), "admin", PrincipalType.ROLE, new PrivilegeGrantInfo("All", 0, "admin", PrincipalType.ROLE, true)));

         try {
            ms.grantPrivileges(privs);
         } catch (InvalidObjectException e) {
            LOG.debug("Failed while granting global privs to admin", e);
         } catch (NoSuchObjectException e) {
            LOG.warn("Failed while granting global privs to admin", e);
         }

      }

      private void addAdminUsers() throws MetaException {
         try {
            this.addAdminUsers_core();
         } catch (JDOException e) {
            LOG.warn("Retrying adding admin users after error: " + e.getMessage(), e);
            this.addAdminUsers_core();
         }

      }

      private void addAdminUsers_core() throws MetaException {
         String userStr = HiveConf.getVar(this.hiveConf, ConfVars.USERS_IN_ADMIN_ROLE, "").trim();
         if (userStr.isEmpty()) {
            LOG.info("No user is added in admin role, since config is empty");
         } else {
            Iterator<String> users = Splitter.on(",").trimResults().omitEmptyStrings().split(userStr).iterator();
            if (!users.hasNext()) {
               LOG.info("No user is added in admin role, since config value " + userStr + " is in incorrect format. We accept comma seprated list of users.");
            } else {
               RawStore ms = this.getMS();

               Role adminRole;
               try {
                  adminRole = ms.getRole("admin");
               } catch (NoSuchObjectException e) {
                  LOG.error("Failed to retrieve just added admin role", e);
                  return;
               }

               while(users.hasNext()) {
                  String userName = (String)users.next();

                  try {
                     ms.grantRole(adminRole, userName, PrincipalType.USER, "admin", PrincipalType.ROLE, true);
                     LOG.info("Added " + userName + " to admin role");
                  } catch (NoSuchObjectException e) {
                     LOG.error("Failed to add " + userName + " in admin role", e);
                  } catch (InvalidObjectException e) {
                     LOG.debug(userName + " already in admin role", e);
                  }
               }

            }
         }
      }

      private static void logInfo(String m) {
         LOG.info(((Integer)threadLocalId.get()).toString() + ": " + m);
         logAuditEvent(m);
      }

      private String startFunction(String function, String extraLogInfo) {
         this.incrementCounter(function);
         logInfo((getThreadLocalIpAddress() == null ? "" : "source:" + getThreadLocalIpAddress() + " ") + function + extraLogInfo);
         if (MetricsFactory.getInstance() != null) {
            MetricsFactory.getInstance().startStoredScope("api_" + function);
         }

         return function;
      }

      private String startFunction(String function) {
         return this.startFunction(function, "");
      }

      private String startTableFunction(String function, String db, String tbl) {
         return this.startFunction(function, " : db=" + db + " tbl=" + tbl);
      }

      private String startMultiTableFunction(String function, String db, List tbls) {
         String tableNames = org.apache.commons.lang3.StringUtils.join(tbls, ",");
         return this.startFunction(function, " : db=" + db + " tbls=" + tableNames);
      }

      private String startPartitionFunction(String function, String db, String tbl, List partVals) {
         return this.startFunction(function, " : db=" + db + " tbl=" + tbl + "[" + org.apache.commons.lang3.StringUtils.join(partVals, ",") + "]");
      }

      private String startPartitionFunction(String function, String db, String tbl, Map partName) {
         return this.startFunction(function, " : db=" + db + " tbl=" + tbl + "partition=" + partName);
      }

      private void endFunction(String function, boolean successful, Exception e) {
         this.endFunction(function, successful, e, (String)null);
      }

      private void endFunction(String function, boolean successful, Exception e, String inputTableName) {
         this.endFunction(function, new MetaStoreEndFunctionContext(successful, e, inputTableName));
      }

      private void endFunction(String function, MetaStoreEndFunctionContext context) {
         if (MetricsFactory.getInstance() != null) {
            MetricsFactory.getInstance().endStoredScope("api_" + function);
         }

         for(MetaStoreEndFunctionListener listener : this.endFunctionListeners) {
            listener.onEndFunction(function, context);
         }

      }

      public fb_status getStatus() {
         return fb_status.ALIVE;
      }

      public void shutdown() {
         HiveMetaStore.cleanupRawStore();
      }

      public AbstractMap getCounters() {
         AbstractMap<String, Long> counters = super.getCounters();
         if (this.endFunctionListeners != null) {
            for(MetaStoreEndFunctionListener listener : this.endFunctionListeners) {
               listener.exportCounters(counters);
            }
         }

         return counters;
      }

      private void create_database_core(RawStore ms, Database db) throws AlreadyExistsException, InvalidObjectException, MetaException {
         if (!MetaStoreUtils.validateName(db.getName(), (Configuration)null)) {
            throw new InvalidObjectException(db.getName() + " is not a valid database name");
         } else {
            if (null == db.getLocationUri()) {
               db.setLocationUri(this.wh.getDefaultDatabasePath(db.getName()).toString());
            } else {
               db.setLocationUri(this.wh.getDnsPath(new Path(db.getLocationUri())).toString());
            }

            Path dbPath = new Path(db.getLocationUri());
            boolean success = false;
            boolean madeDir = false;
            Map<String, String> transactionalListenersResponses = Collections.emptyMap();

            try {
               this.firePreEvent(new PreCreateDatabaseEvent(db, this));
               if (!this.wh.isDir(dbPath)) {
                  if (!this.wh.mkdirs(dbPath, true)) {
                     throw new MetaException("Unable to create database path " + dbPath + ", failed to create database " + db.getName());
                  }

                  madeDir = true;
               }

               ms.openTransaction();
               ms.createDatabase(db);
               if (!this.transactionalListeners.isEmpty()) {
                  transactionalListenersResponses = MetaStoreListenerNotifier.notifyEvent(this.transactionalListeners, EventMessage.EventType.CREATE_DATABASE, new CreateDatabaseEvent(db, true, this));
               }

               success = ms.commitTransaction();
            } finally {
               if (!success) {
                  ms.rollbackTransaction();
                  if (madeDir) {
                     this.wh.deleteDir(dbPath, true);
                  }
               }

               if (!this.listeners.isEmpty()) {
                  MetaStoreListenerNotifier.notifyEvent(this.listeners, EventMessage.EventType.CREATE_DATABASE, new CreateDatabaseEvent(db, success, this), (EnvironmentContext)null, transactionalListenersResponses, ms);
               }

            }

         }
      }

      public void create_database(Database db) throws AlreadyExistsException, InvalidObjectException, MetaException {
         this.startFunction("create_database", ": " + db.toString());
         boolean success = false;
         Exception ex = null;

         try {
            try {
               if (null != this.get_database_core(db.getName())) {
                  throw new AlreadyExistsException("Database " + db.getName() + " already exists");
               }
            } catch (NoSuchObjectException var11) {
            }

            if (HiveMetaStore.TEST_TIMEOUT_ENABLED) {
               try {
                  Thread.sleep(HiveMetaStore.TEST_TIMEOUT_VALUE);
               } catch (InterruptedException var10) {
               }

               Deadline.checkTimeout();
            }

            this.create_database_core(this.getMS(), db);
            success = true;
         } catch (Exception e) {
            ex = e;
            if (e instanceof MetaException) {
               throw (MetaException)e;
            }

            if (e instanceof InvalidObjectException) {
               throw (InvalidObjectException)e;
            }

            if (e instanceof AlreadyExistsException) {
               throw (AlreadyExistsException)e;
            }

            throw newMetaException(e);
         } finally {
            this.endFunction("create_database", success, ex);
         }

      }

      public Database get_database(String name) throws NoSuchObjectException, MetaException {
         this.startFunction("get_database", ": " + name);
         Database db = null;
         Exception ex = null;

         try {
            db = this.get_database_core(name);
            this.firePreEvent(new PreReadDatabaseEvent(db, this));
         } catch (MetaException e) {
            ex = e;
            throw e;
         } catch (NoSuchObjectException e) {
            ex = e;
            throw e;
         } finally {
            this.endFunction("get_database", db != null, ex);
         }

         return db;
      }

      public Database get_database_core(String name) throws NoSuchObjectException, MetaException {
         Database db = null;

         try {
            db = this.getMS().getDatabase(name);
            return db;
         } catch (MetaException e) {
            throw e;
         } catch (NoSuchObjectException e) {
            throw e;
         } catch (Exception var6) {
            assert var6 instanceof RuntimeException;

            throw (RuntimeException)var6;
         }
      }

      public void alter_database(String dbName, Database db) throws NoSuchObjectException, TException, MetaException {
         this.startFunction("alter_database" + dbName);
         boolean success = false;
         Exception ex = null;

         try {
            this.getMS().alterDatabase(dbName, db);
            success = true;
         } catch (Exception e) {
            ex = e;
            this.rethrowException(e);
         } finally {
            this.endFunction("alter_database", success, ex);
         }

      }

      private void drop_database_core(RawStore ms, String name, boolean deleteData, boolean cascade) throws NoSuchObjectException, InvalidOperationException, MetaException, IOException, InvalidObjectException, InvalidInputException {
         boolean success = false;
         Database db = null;
         List<Path> tablePaths = new ArrayList();
         List<Path> partitionPaths = new ArrayList();
         Map<String, String> transactionalListenerResponses = Collections.emptyMap();

         try {
            ms.openTransaction();
            db = ms.getDatabase(name);
            this.firePreEvent(new PreDropDatabaseEvent(db, this));
            List<String> allTables = this.get_all_tables(db.getName());
            List<String> allFunctions = this.get_functions(db.getName(), "*");
            if (!cascade) {
               if (!allTables.isEmpty()) {
                  throw new InvalidOperationException("Database " + db.getName() + " is not empty. One or more tables exist.");
               }

               if (!allFunctions.isEmpty()) {
                  throw new InvalidOperationException("Database " + db.getName() + " is not empty. One or more functions exist.");
               }
            }

            Path path = (new Path(db.getLocationUri())).getParent();
            if (!this.wh.isWritable(path)) {
               throw new MetaException("Database not dropped since " + path + " is not writable by " + this.hiveConf.getUser());
            }

            Path databasePath = this.wh.getDnsPath(this.wh.getDatabasePath(db));

            for(String funcName : allFunctions) {
               this.drop_function(name, funcName);
            }

            int tableBatchSize = HiveConf.getIntVar(this.hiveConf, ConfVars.METASTORE_BATCH_RETRIEVE_MAX);
            int startIndex = 0;

            while(startIndex < allTables.size()) {
               int endIndex = Math.min(startIndex + tableBatchSize, allTables.size());
               List<Table> tables = null;

               try {
                  tables = ms.getTableObjectsByName(name, allTables.subList(startIndex, endIndex));
               } catch (UnknownDBException e) {
                  throw new MetaException(e.getMessage());
               }

               if (tables != null && !tables.isEmpty()) {
                  for(Table table : tables) {
                     Path tablePath = null;
                     if (table.getSd().getLocation() != null && !this.isExternal(table)) {
                        tablePath = this.wh.getDnsPath(new Path(table.getSd().getLocation()));
                        if (!this.wh.isWritable(tablePath.getParent())) {
                           throw new MetaException("Database metadata not deleted since table: " + table.getTableName() + " has a parent location " + tablePath.getParent() + " which is not writable by " + this.hiveConf.getUser());
                        }

                        if (!this.isSubdirectory(databasePath, tablePath)) {
                           tablePaths.add(tablePath);
                        }
                     }

                     partitionPaths = this.dropPartitionsAndGetLocations(ms, name, table.getTableName(), tablePath, table.getPartitionKeys(), deleteData && !this.isExternal(table));
                     this.drop_table(name, table.getTableName(), false);
                  }

                  startIndex = endIndex;
               }
            }

            if (ms.dropDatabase(name)) {
               if (!this.transactionalListeners.isEmpty()) {
                  transactionalListenerResponses = MetaStoreListenerNotifier.notifyEvent(this.transactionalListeners, EventMessage.EventType.DROP_DATABASE, new DropDatabaseEvent(db, true, this));
               }

               success = ms.commitTransaction();
            }
         } finally {
            if (!success) {
               ms.rollbackTransaction();
            } else if (deleteData) {
               this.deletePartitionData(partitionPaths);

               for(Path tablePath : tablePaths) {
                  this.deleteTableData(tablePath);
               }

               try {
                  this.wh.deleteDir(new Path(db.getLocationUri()), true);
               } catch (Exception e) {
                  LOG.error("Failed to delete database directory: " + db.getLocationUri() + " " + e.getMessage());
               }
            }

            if (!this.listeners.isEmpty()) {
               MetaStoreListenerNotifier.notifyEvent(this.listeners, EventMessage.EventType.DROP_DATABASE, new DropDatabaseEvent(db, success, this), (EnvironmentContext)null, transactionalListenerResponses, ms);
            }

         }

      }

      private boolean isSubdirectory(Path parent, Path other) {
         return other.toString().startsWith(parent.toString().endsWith("/") ? parent.toString() : parent.toString() + "/");
      }

      public void drop_database(String dbName, boolean deleteData, boolean cascade) throws NoSuchObjectException, InvalidOperationException, MetaException {
         this.startFunction("drop_database", ": " + dbName);
         if ("default".equalsIgnoreCase(dbName)) {
            this.endFunction("drop_database", false, (Exception)null);
            throw new MetaException("Can not drop default database");
         } else {
            boolean success = false;
            Exception ex = null;

            try {
               this.drop_database_core(this.getMS(), dbName, deleteData, cascade);
               success = true;
            } catch (IOException e) {
               ex = e;
               throw new MetaException(e.getMessage());
            } catch (Exception e) {
               ex = e;
               if (e instanceof MetaException) {
                  throw (MetaException)e;
               }

               if (e instanceof InvalidOperationException) {
                  throw (InvalidOperationException)e;
               }

               if (e instanceof NoSuchObjectException) {
                  throw (NoSuchObjectException)e;
               }

               throw newMetaException(e);
            } finally {
               this.endFunction("drop_database", success, ex);
            }

         }
      }

      public List get_databases(String pattern) throws MetaException {
         this.startFunction("get_databases", ": " + pattern);
         List<String> ret = null;
         Exception ex = null;

         try {
            ret = this.getMS().getDatabases(pattern);
         } catch (Exception e) {
            ex = e;
            if (e instanceof MetaException) {
               throw (MetaException)e;
            }

            throw newMetaException(e);
         } finally {
            this.endFunction("get_databases", ret != null, ex);
         }

         return ret;
      }

      public List get_all_databases() throws MetaException {
         this.startFunction("get_all_databases");
         List<String> ret = null;
         Exception ex = null;

         try {
            ret = this.getMS().getAllDatabases();
         } catch (Exception e) {
            ex = e;
            if (e instanceof MetaException) {
               throw (MetaException)e;
            }

            throw newMetaException(e);
         } finally {
            this.endFunction("get_all_databases", ret != null, ex);
         }

         return ret;
      }

      private void create_type_core(RawStore ms, Type type) throws AlreadyExistsException, MetaException, InvalidObjectException {
         if (!MetaStoreUtils.validateName(type.getName(), (Configuration)null)) {
            throw new InvalidObjectException("Invalid type name");
         } else {
            boolean success = false;

            try {
               ms.openTransaction();
               if (this.is_type_exists(ms, type.getName())) {
                  throw new AlreadyExistsException("Type " + type.getName() + " already exists");
               }

               ms.createType(type);
               success = ms.commitTransaction();
            } finally {
               if (!success) {
                  ms.rollbackTransaction();
               }

            }

         }
      }

      public boolean create_type(Type type) throws AlreadyExistsException, MetaException, InvalidObjectException {
         this.startFunction("create_type", ": " + type.toString());
         boolean success = false;
         Exception ex = null;

         try {
            this.create_type_core(this.getMS(), type);
            success = true;
         } catch (Exception e) {
            ex = e;
            if (e instanceof MetaException) {
               throw (MetaException)e;
            }

            if (e instanceof InvalidObjectException) {
               throw (InvalidObjectException)e;
            }

            if (e instanceof AlreadyExistsException) {
               throw (AlreadyExistsException)e;
            }

            throw newMetaException(e);
         } finally {
            this.endFunction("create_type", success, ex);
         }

         return success;
      }

      public Type get_type(String name) throws MetaException, NoSuchObjectException {
         this.startFunction("get_type", ": " + name);
         Type ret = null;
         Exception ex = null;

         try {
            ret = this.getMS().getType(name);
            if (null == ret) {
               throw new NoSuchObjectException("Type \"" + name + "\" not found.");
            }
         } catch (Exception e) {
            ex = e;
            if (e instanceof MetaException) {
               throw (MetaException)e;
            }

            if (e instanceof NoSuchObjectException) {
               throw (NoSuchObjectException)e;
            }

            throw newMetaException(e);
         } finally {
            this.endFunction("get_type", ret != null, ex);
         }

         return ret;
      }

      private boolean is_type_exists(RawStore ms, String typeName) throws MetaException {
         return ms.getType(typeName) != null;
      }

      private void drop_type_core(RawStore ms, String typeName) throws NoSuchObjectException, MetaException {
         boolean success = false;

         try {
            ms.openTransaction();
            if (!this.is_type_exists(ms, typeName)) {
               throw new NoSuchObjectException(typeName + " doesn't exist");
            }

            if (!ms.dropType(typeName)) {
               throw new MetaException("Unable to drop type " + typeName);
            }

            success = ms.commitTransaction();
         } finally {
            if (!success) {
               ms.rollbackTransaction();
            }

         }

      }

      public boolean drop_type(String name) throws MetaException, NoSuchObjectException {
         this.startFunction("drop_type", ": " + name);
         boolean success = false;
         Exception ex = null;

         try {
            success = this.getMS().dropType(name);
         } catch (Exception e) {
            ex = e;
            if (e instanceof MetaException) {
               throw (MetaException)e;
            }

            if (e instanceof NoSuchObjectException) {
               throw (NoSuchObjectException)e;
            }

            throw newMetaException(e);
         } finally {
            this.endFunction("drop_type", success, ex);
         }

         return success;
      }

      public Map get_type_all(String name) throws MetaException {
         this.startFunction("get_type_all", ": " + name);
         this.endFunction("get_type_all", false, (Exception)null);
         throw new MetaException("Not yet implemented");
      }

      private void create_table_core(RawStore ms, Table tbl, EnvironmentContext envContext, List primaryKeys, List foreignKeys) throws AlreadyExistsException, MetaException, InvalidObjectException, NoSuchObjectException {
         if (!MetaStoreUtils.validateName(tbl.getTableName(), this.hiveConf)) {
            throw new InvalidObjectException(tbl.getTableName() + " is not a valid object name");
         } else {
            String validate = MetaStoreUtils.validateTblColumns(tbl.getSd().getCols());
            if (validate != null) {
               throw new InvalidObjectException("Invalid column " + validate);
            } else {
               if (tbl.getPartitionKeys() != null) {
                  validate = MetaStoreUtils.validateTblColumns(tbl.getPartitionKeys());
                  if (validate != null) {
                     throw new InvalidObjectException("Invalid partition column " + validate);
                  }
               }

               SkewedInfo skew = tbl.getSd().getSkewedInfo();
               if (skew != null) {
                  validate = MetaStoreUtils.validateSkewedColNames(skew.getSkewedColNames());
                  if (validate != null) {
                     throw new InvalidObjectException("Invalid skew column " + validate);
                  }

                  validate = MetaStoreUtils.validateSkewedColNamesSubsetCol(skew.getSkewedColNames(), tbl.getSd().getCols());
                  if (validate != null) {
                     throw new InvalidObjectException("Invalid skew column " + validate);
                  }
               }

               Map<String, String> transactionalListenerResponses = Collections.emptyMap();
               Path tblPath = null;
               boolean success = false;
               boolean madeDir = false;

               try {
                  this.firePreEvent(new PreCreateTableEvent(tbl, this));
                  ms.openTransaction();
                  Database db = ms.getDatabase(tbl.getDbName());
                  if (db == null) {
                     throw new NoSuchObjectException("The database " + tbl.getDbName() + " does not exist");
                  }

                  if (this.is_table_exists(ms, tbl.getDbName(), tbl.getTableName())) {
                     throw new AlreadyExistsException("Table " + tbl.getTableName() + " already exists");
                  }

                  if (!TableType.VIRTUAL_VIEW.toString().equals(tbl.getTableType())) {
                     if (tbl.getSd().getLocation() != null && !tbl.getSd().getLocation().isEmpty()) {
                        if (!this.isExternal(tbl) && !MetaStoreUtils.isNonNativeTable(tbl)) {
                           LOG.warn("Location: " + tbl.getSd().getLocation() + " specified for non-external table:" + tbl.getTableName());
                        }

                        tblPath = this.wh.getDnsPath(new Path(tbl.getSd().getLocation()));
                     } else {
                        tblPath = this.wh.getDefaultTablePath(ms.getDatabase(tbl.getDbName()), tbl.getTableName());
                     }

                     tbl.getSd().setLocation(tblPath.toString());
                  }

                  if (tblPath != null && !this.wh.isDir(tblPath)) {
                     if (!this.wh.mkdirs(tblPath, true)) {
                        throw new MetaException(tblPath + " is not a directory or unable to create one");
                     }

                     madeDir = true;
                  }

                  if (HiveConf.getBoolVar(this.hiveConf, ConfVars.HIVESTATSAUTOGATHER) && !MetaStoreUtils.isView(tbl)) {
                     MetaStoreUtils.updateTableStatsFast(db, tbl, this.wh, madeDir, envContext);
                  }

                  long time = System.currentTimeMillis() / 1000L;
                  tbl.setCreateTime((int)time);
                  if (tbl.getParameters() == null || tbl.getParameters().get("transient_lastDdlTime") == null) {
                     tbl.putToParameters("transient_lastDdlTime", Long.toString(time));
                  }

                  if (primaryKeys == null && foreignKeys == null) {
                     ms.createTable(tbl);
                  } else {
                     ms.createTableWithConstraints(tbl, primaryKeys, foreignKeys);
                  }

                  if (!this.transactionalListeners.isEmpty()) {
                     transactionalListenerResponses = MetaStoreListenerNotifier.notifyEvent(this.transactionalListeners, EventMessage.EventType.CREATE_TABLE, new CreateTableEvent(tbl, true, this), envContext);
                  }

                  success = ms.commitTransaction();
               } finally {
                  if (!success) {
                     ms.rollbackTransaction();
                     if (madeDir) {
                        this.wh.deleteDir(tblPath, true);
                     }
                  }

                  if (!this.listeners.isEmpty()) {
                     MetaStoreListenerNotifier.notifyEvent(this.listeners, EventMessage.EventType.CREATE_TABLE, new CreateTableEvent(tbl, success, this), envContext, transactionalListenerResponses, ms);
                  }

               }

            }
         }
      }

      public void create_table(Table tbl) throws AlreadyExistsException, MetaException, InvalidObjectException {
         this.create_table_with_environment_context(tbl, (EnvironmentContext)null);
      }

      public void create_table_with_environment_context(Table tbl, EnvironmentContext envContext) throws AlreadyExistsException, MetaException, InvalidObjectException {
         this.startFunction("create_table", ": " + tbl.toString());
         boolean success = false;
         Exception ex = null;

         try {
            this.create_table_core(this.getMS(), tbl, envContext, (List)null, (List)null);
            success = true;
         } catch (NoSuchObjectException e) {
            ex = e;
            throw new InvalidObjectException(e.getMessage());
         } catch (Exception e) {
            ex = e;
            if (e instanceof MetaException) {
               throw (MetaException)e;
            }

            if (e instanceof InvalidObjectException) {
               throw (InvalidObjectException)e;
            }

            if (e instanceof AlreadyExistsException) {
               throw (AlreadyExistsException)e;
            }

            throw newMetaException(e);
         } finally {
            this.endFunction("create_table", success, ex, tbl.getTableName());
         }

      }

      public void create_table_with_constraints(Table tbl, List primaryKeys, List foreignKeys) throws AlreadyExistsException, MetaException, InvalidObjectException {
         this.startFunction("create_table", ": " + tbl.toString());
         boolean success = false;
         Exception ex = null;

         try {
            this.create_table_core(this.getMS(), tbl, (EnvironmentContext)null, primaryKeys, foreignKeys);
            success = true;
         } catch (NoSuchObjectException e) {
            ex = e;
            throw new InvalidObjectException(e.getMessage());
         } catch (Exception e) {
            ex = e;
            if (e instanceof MetaException) {
               throw (MetaException)e;
            }

            if (e instanceof InvalidObjectException) {
               throw (InvalidObjectException)e;
            }

            if (e instanceof AlreadyExistsException) {
               throw (AlreadyExistsException)e;
            }

            throw newMetaException(e);
         } finally {
            this.endFunction("create_table", success, ex, tbl.getTableName());
         }

      }

      public void drop_constraint(DropConstraintRequest req) throws MetaException, InvalidObjectException {
         String dbName = req.getDbname();
         String tableName = req.getTablename();
         String constraintName = req.getConstraintname();
         this.startFunction("drop_constraint", ": " + constraintName.toString());
         boolean success = false;
         Exception ex = null;

         try {
            this.getMS().dropConstraint(dbName, tableName, constraintName);
            success = true;
         } catch (NoSuchObjectException e) {
            ex = e;
            throw new InvalidObjectException(e.getMessage());
         } catch (Exception e) {
            ex = e;
            if (e instanceof MetaException) {
               throw (MetaException)e;
            }

            if (e instanceof InvalidObjectException) {
               throw (InvalidObjectException)e;
            }

            throw newMetaException(e);
         } finally {
            this.endFunction("drop_constraint", success, ex, constraintName);
         }

      }

      public void add_primary_key(AddPrimaryKeyRequest req) throws MetaException, InvalidObjectException {
         List<SQLPrimaryKey> primaryKeyCols = req.getPrimaryKeyCols();
         String constraintName = primaryKeyCols != null && primaryKeyCols.size() > 0 ? ((SQLPrimaryKey)primaryKeyCols.get(0)).getPk_name() : "null";
         this.startFunction("add_primary_key", ": " + constraintName);
         boolean success = false;
         Exception ex = null;

         try {
            this.getMS().addPrimaryKeys(primaryKeyCols);
            success = true;
         } catch (Exception e) {
            ex = e;
            if (e instanceof MetaException) {
               throw (MetaException)e;
            }

            if (e instanceof InvalidObjectException) {
               throw (InvalidObjectException)e;
            }

            throw newMetaException(e);
         } finally {
            this.endFunction("add_primary_key", success, ex, constraintName);
         }

      }

      public void add_foreign_key(AddForeignKeyRequest req) throws MetaException, InvalidObjectException {
         List<SQLForeignKey> foreignKeyCols = req.getForeignKeyCols();
         String constraintName = foreignKeyCols != null && foreignKeyCols.size() > 0 ? ((SQLForeignKey)foreignKeyCols.get(0)).getFk_name() : "null";
         this.startFunction("add_foreign_key", ": " + constraintName);
         boolean success = false;
         Exception ex = null;

         try {
            this.getMS().addForeignKeys(foreignKeyCols);
            success = true;
         } catch (Exception e) {
            ex = e;
            if (e instanceof MetaException) {
               throw (MetaException)e;
            }

            if (e instanceof InvalidObjectException) {
               throw (InvalidObjectException)e;
            }

            throw newMetaException(e);
         } finally {
            this.endFunction("add_foreign_key", success, ex, constraintName);
         }

      }

      private boolean is_table_exists(RawStore ms, String dbname, String name) throws MetaException {
         return ms.getTable(dbname, name) != null;
      }

      private boolean drop_table_core(RawStore ms, String dbname, String name, boolean deleteData, EnvironmentContext envContext, String indexName) throws NoSuchObjectException, MetaException, IOException, InvalidObjectException, InvalidInputException {
         boolean success = false;
         boolean isExternal = false;
         Path tblPath = null;
         List<Path> partPaths = null;
         Table tbl = null;
         boolean ifPurge = false;
         Map<String, String> transactionalListenerResponses = Collections.emptyMap();

         try {
            ms.openTransaction();
            tbl = this.get_table_core(dbname, name);
            if (tbl == null) {
               throw new NoSuchObjectException(name + " doesn't exist");
            }

            if (tbl.getSd() == null) {
               throw new MetaException("Table metadata is corrupted");
            }

            ifPurge = isMustPurge(envContext, tbl);
            this.firePreEvent(new PreDropTableEvent(tbl, deleteData, this));
            boolean isIndexTable = this.isIndexTable(tbl);
            if (indexName == null && isIndexTable) {
               throw new RuntimeException("The table " + name + " is an index table. Please do drop index instead.");
            }

            if (!isIndexTable) {
               try {
                  for(List<Index> indexes = ms.getIndexes(dbname, name, 32767); indexes != null && indexes.size() > 0; indexes = ms.getIndexes(dbname, name, 32767)) {
                     for(Index idx : indexes) {
                        this.drop_index_by_name(dbname, name, idx.getIndexName(), true);
                     }
                  }
               } catch (TException e) {
                  throw new MetaException(e.getMessage());
               }
            }

            isExternal = this.isExternal(tbl);
            if (tbl.getSd().getLocation() != null) {
               tblPath = new Path(tbl.getSd().getLocation());
               if (!this.wh.isWritable(tblPath.getParent())) {
                  String target = indexName == null ? "Table" : "Index table";
                  throw new MetaException(target + " metadata not deleted since " + tblPath.getParent() + " is not writable by " + this.hiveConf.getUser());
               }
            }

            this.checkTrashPurgeCombination(tblPath, dbname + "." + name, ifPurge, deleteData && !isExternal);
            partPaths = this.dropPartitionsAndGetLocations(ms, dbname, name, tblPath, tbl.getPartitionKeys(), deleteData && !isExternal);
            if (!ms.dropTable(dbname, name)) {
               String tableName = dbname + "." + name;
               throw new MetaException(indexName == null ? "Unable to drop table " + tableName : "Unable to drop index table " + tableName + " for index " + indexName);
            }

            if (!this.transactionalListeners.isEmpty()) {
               transactionalListenerResponses = MetaStoreListenerNotifier.notifyEvent(this.transactionalListeners, EventMessage.EventType.DROP_TABLE, new DropTableEvent(tbl, true, deleteData, this), envContext);
            }

            success = ms.commitTransaction();
         } finally {
            if (!success) {
               ms.rollbackTransaction();
            } else if (deleteData && !isExternal) {
               this.deletePartitionData(partPaths, ifPurge);
               this.deleteTableData(tblPath, ifPurge);
            }

            if (!this.listeners.isEmpty()) {
               MetaStoreListenerNotifier.notifyEvent(this.listeners, EventMessage.EventType.DROP_TABLE, new DropTableEvent(tbl, success, deleteData, this), envContext, transactionalListenerResponses, ms);
            }

         }

         return success;
      }

      private void checkTrashPurgeCombination(Path pathToData, String objectName, boolean ifPurge, boolean deleteData) throws MetaException {
         if (deleteData && pathToData != null && !ifPurge) {
            boolean trashEnabled = false;

            try {
               trashEnabled = 0.0F < this.hiveConf.getFloat("fs.trash.interval", -1.0F);
            } catch (NumberFormatException var9) {
            }

            if (trashEnabled) {
               try {
                  HadoopShims.HdfsEncryptionShim shim = ShimLoader.getHadoopShims().createHdfsEncryptionShim(FileSystem.get(this.hiveConf), this.hiveConf);
                  if (shim.isPathEncrypted(pathToData)) {
                     throw new MetaException("Unable to drop " + objectName + " because it is in an encryption zone and trash is enabled.  Use PURGE option to skip trash.");
                  }
               } catch (IOException ex) {
                  MetaException e = new MetaException(ex.getMessage());
                  e.initCause(ex);
                  throw e;
               }
            }

         }
      }

      private void deleteTableData(Path tablePath) {
         this.deleteTableData(tablePath, false);
      }

      private void deleteTableData(Path tablePath, boolean ifPurge) {
         if (tablePath != null) {
            try {
               this.wh.deleteDir(tablePath, true, ifPurge);
            } catch (Exception e) {
               LOG.error("Failed to delete table directory: " + tablePath + " " + e.getMessage());
            }
         }

      }

      private void deletePartitionData(List partPaths) {
         this.deletePartitionData(partPaths, false);
      }

      private void deletePartitionData(List partPaths, boolean ifPurge) {
         if (partPaths != null && !partPaths.isEmpty()) {
            for(Path partPath : partPaths) {
               try {
                  this.wh.deleteDir(partPath, true, ifPurge);
               } catch (Exception e) {
                  LOG.error("Failed to delete partition directory: " + partPath + " " + e.getMessage());
               }
            }
         }

      }

      private List dropPartitionsAndGetLocations(RawStore ms, String dbName, String tableName, Path tablePath, List partitionKeys, boolean checkLocation) throws MetaException, IOException, NoSuchObjectException, InvalidObjectException, InvalidInputException {
         int partitionBatchSize = HiveConf.getIntVar(this.hiveConf, ConfVars.METASTORE_BATCH_RETRIEVE_MAX);
         Path tableDnsPath = null;
         if (tablePath != null) {
            tableDnsPath = this.wh.getDnsPath(tablePath);
         }

         List<Path> partPaths = new ArrayList();
         Table tbl = ms.getTable(dbName, tableName);

         while(true) {
            List<Partition> partsToDelete = ms.getPartitions(dbName, tableName, partitionBatchSize);
            if (partsToDelete == null || partsToDelete.isEmpty()) {
               return partPaths;
            }

            List<String> partNames = new ArrayList();

            for(Partition part : partsToDelete) {
               if (checkLocation && part.getSd() != null && part.getSd().getLocation() != null) {
                  Path partPath = this.wh.getDnsPath(new Path(part.getSd().getLocation()));
                  if (tableDnsPath == null || partPath != null && !this.isSubdirectory(tableDnsPath, partPath)) {
                     if (!this.wh.isWritable(partPath.getParent())) {
                        throw new MetaException("Table metadata not deleted since the partition " + Warehouse.makePartName(partitionKeys, part.getValues()) + " has parent location " + partPath.getParent() + " which is not writable by " + this.hiveConf.getUser());
                     }

                     partPaths.add(partPath);
                  }
               }

               partNames.add(Warehouse.makePartName(tbl.getPartitionKeys(), part.getValues()));
            }

            for(MetaStoreEventListener listener : this.listeners) {
               if (listener instanceof HMSMetricsListener) {
                  for(Partition part : partsToDelete) {
                     listener.onDropPartition((DropPartitionEvent)null);
                  }
               }
            }

            ms.dropPartitions(dbName, tableName, partNames);
         }
      }

      public void drop_table(String dbname, String name, boolean deleteData) throws NoSuchObjectException, MetaException {
         this.drop_table_with_environment_context(dbname, name, deleteData, (EnvironmentContext)null);
      }

      public void drop_table_with_environment_context(String dbname, String name, boolean deleteData, EnvironmentContext envContext) throws NoSuchObjectException, MetaException {
         this.startTableFunction("drop_table", dbname, name);
         boolean success = false;
         Exception ex = null;

         try {
            success = this.drop_table_core(this.getMS(), dbname, name, deleteData, envContext, (String)null);
         } catch (IOException e) {
            ex = e;
            throw new MetaException(e.getMessage());
         } catch (Exception e) {
            ex = e;
            if (e instanceof MetaException) {
               throw (MetaException)e;
            }

            if (e instanceof NoSuchObjectException) {
               throw (NoSuchObjectException)e;
            }

            throw newMetaException(e);
         } finally {
            this.endFunction("drop_table", success, ex, name);
         }

      }

      private boolean isExternal(Table table) {
         return MetaStoreUtils.isExternalTable(table);
      }

      private boolean isIndexTable(Table table) {
         return MetaStoreUtils.isIndexTable(table);
      }

      /** @deprecated */
      @Deprecated
      public Table get_table(String dbname, String name) throws MetaException, NoSuchObjectException {
         return this.getTableInternal(dbname, name, (ClientCapabilities)null);
      }

      public GetTableResult get_table_req(GetTableRequest req) throws MetaException, NoSuchObjectException {
         return new GetTableResult(this.getTableInternal(req.getDbName(), req.getTblName(), req.getCapabilities()));
      }

      private Table getTableInternal(String dbname, String name, ClientCapabilities capabilities) throws MetaException, NoSuchObjectException {
         if (this.isInTest) {
            this.assertClientHasCapability(capabilities, ClientCapability.TEST_CAPABILITY, "Hive tests", "get_table_req");
         }

         Table t = null;
         this.startTableFunction("get_table", dbname, name);
         Exception ex = null;

         try {
            t = this.get_table_core(dbname, name);
            this.firePreEvent(new PreReadTableEvent(t, this));
         } catch (MetaException e) {
            ex = e;
            throw e;
         } catch (NoSuchObjectException e) {
            ex = e;
            throw e;
         } finally {
            this.endFunction("get_table", t != null, ex, name);
         }

         return t;
      }

      public List get_table_meta(String dbnames, String tblNames, List tblTypes) throws MetaException, NoSuchObjectException {
         List<TableMeta> t = null;
         this.startTableFunction("get_table_metas", dbnames, tblNames);
         Exception ex = null;

         try {
            t = this.getMS().getTableMeta(dbnames, tblNames, tblTypes);
         } catch (Exception e) {
            ex = e;
            throw newMetaException(e);
         } finally {
            this.endFunction("get_table_metas", t != null, ex);
         }

         return t;
      }

      public Table get_table_core(String dbname, String name) throws MetaException, NoSuchObjectException {
         try {
            Table t = this.getMS().getTable(dbname, name);
            if (t == null) {
               throw new NoSuchObjectException(dbname + "." + name + " table not found");
            } else {
               return t;
            }
         } catch (Exception e) {
            if (e instanceof MetaException) {
               throw (MetaException)e;
            } else if (e instanceof NoSuchObjectException) {
               throw (NoSuchObjectException)e;
            } else {
               throw newMetaException(e);
            }
         }
      }

      /** @deprecated */
      @Deprecated
      public List get_table_objects_by_name(String dbName, List tableNames) throws MetaException, InvalidOperationException, UnknownDBException {
         return this.getTableObjectsInternal(dbName, tableNames, (ClientCapabilities)null);
      }

      public GetTablesResult get_table_objects_by_name_req(GetTablesRequest req) throws TException {
         return new GetTablesResult(this.getTableObjectsInternal(req.getDbName(), req.getTblNames(), req.getCapabilities()));
      }

      private List getTableObjectsInternal(String dbName, List tableNames, ClientCapabilities capabilities) throws MetaException, InvalidOperationException, UnknownDBException {
         if (this.isInTest) {
            this.assertClientHasCapability(capabilities, ClientCapability.TEST_CAPABILITY, "Hive tests", "get_table_objects_by_name_req");
         }

         List<Table> tables = new ArrayList();
         this.startMultiTableFunction("get_multi_table", dbName, tableNames);
         Exception ex = null;
         int tableBatchSize = HiveConf.getIntVar(this.hiveConf, ConfVars.METASTORE_BATCH_RETRIEVE_MAX);

         try {
            if (dbName == null || dbName.isEmpty()) {
               throw new UnknownDBException("DB name is null or empty");
            }

            if (tableNames == null) {
               throw new InvalidOperationException(dbName + " cannot find null tables");
            }

            List<String> distinctTableNames = tableNames;
            if (tableNames.size() > tableBatchSize) {
               List<String> lowercaseTableNames = new ArrayList();

               for(String tableName : tableNames) {
                  lowercaseTableNames.add(HiveStringUtils.normalizeIdentifier(tableName));
               }

               distinctTableNames = new ArrayList(new HashSet(lowercaseTableNames));
            }

            RawStore ms = this.getMS();

            int endIndex;
            for(int startIndex = 0; startIndex < distinctTableNames.size(); startIndex = endIndex) {
               endIndex = Math.min(startIndex + tableBatchSize, distinctTableNames.size());
               tables.addAll(ms.getTableObjectsByName(dbName, distinctTableNames.subList(startIndex, endIndex)));
            }
         } catch (Exception e) {
            ex = e;
            if (e instanceof MetaException) {
               throw (MetaException)e;
            }

            if (e instanceof InvalidOperationException) {
               throw (InvalidOperationException)e;
            }

            if (e instanceof UnknownDBException) {
               throw (UnknownDBException)e;
            }

            throw newMetaException(e);
         } finally {
            this.endFunction("get_multi_table", tables != null, ex, org.apache.commons.lang3.StringUtils.join(tableNames, ","));
         }

         return tables;
      }

      private void assertClientHasCapability(ClientCapabilities client, ClientCapability value, String what, String call) throws MetaException {
         if (!this.doesClientHaveCapability(client, value)) {
            throw new MetaException("Your client does not appear to support " + what + ". To skip capability checks, please set " + ConfVars.METASTORE_CAPABILITY_CHECK.varname + " to false. This setting can be set globally, or on the client for the current metastore session. Note that this may lead to incorrect results, data loss, undefined behavior, etc. if your client is actually incompatible. You can also specify custom client capabilities via " + call + " API.");
         }
      }

      private boolean doesClientHaveCapability(ClientCapabilities client, ClientCapability value) {
         if (!HiveConf.getBoolVar(this.getConf(), ConfVars.METASTORE_CAPABILITY_CHECK)) {
            return true;
         } else {
            return client != null && client.isSetValues() && client.getValues().contains(value);
         }
      }

      public List get_table_names_by_filter(String dbName, String filter, short maxTables) throws MetaException, InvalidOperationException, UnknownDBException {
         List<String> tables = null;
         this.startFunction("get_table_names_by_filter", ": db = " + dbName + ", filter = " + filter);
         Exception ex = null;

         try {
            if (dbName == null || dbName.isEmpty()) {
               throw new UnknownDBException("DB name is null or empty");
            }

            if (filter == null) {
               throw new InvalidOperationException(filter + " cannot apply null filter");
            }

            tables = this.getMS().listTableNamesByFilter(dbName, filter, maxTables);
         } catch (Exception e) {
            ex = e;
            if (e instanceof MetaException) {
               throw (MetaException)e;
            }

            if (e instanceof InvalidOperationException) {
               throw (InvalidOperationException)e;
            }

            if (e instanceof UnknownDBException) {
               throw (UnknownDBException)e;
            }

            throw newMetaException(e);
         } finally {
            this.endFunction("get_table_names_by_filter", tables != null, ex, org.apache.commons.lang3.StringUtils.join(tables, ","));
         }

         return tables;
      }

      private Partition append_partition_common(RawStore ms, String dbName, String tableName, List part_vals, EnvironmentContext envContext) throws InvalidObjectException, AlreadyExistsException, MetaException {
         Partition part = new Partition();
         boolean success = false;
         boolean madeDir = false;
         Path partLocation = null;
         Table tbl = null;
         Map<String, String> transactionalListenerResponses = Collections.emptyMap();

         try {
            ms.openTransaction();
            part.setDbName(dbName);
            part.setTableName(tableName);
            part.setValues(part_vals);
            MetaStoreUtils.validatePartitionNameCharacters(part_vals, this.partitionValidationPattern);
            tbl = ms.getTable(part.getDbName(), part.getTableName());
            if (tbl == null) {
               throw new InvalidObjectException("Unable to add partition because table or database do not exist");
            }

            if (tbl.getSd().getLocation() == null) {
               throw new MetaException("Cannot append a partition to a view");
            }

            this.firePreEvent(new PreAddPartitionEvent(tbl, part, this));
            part.setSd(tbl.getSd().deepCopy());
            partLocation = new Path(tbl.getSd().getLocation(), Warehouse.makePartName(tbl.getPartitionKeys(), part_vals));
            part.getSd().setLocation(partLocation.toString());
            Partition old_part = null;

            try {
               old_part = ms.getPartition(part.getDbName(), part.getTableName(), part.getValues());
            } catch (NoSuchObjectException var18) {
               old_part = null;
            }

            if (old_part != null) {
               throw new AlreadyExistsException("Partition already exists:" + part);
            }

            if (!this.wh.isDir(partLocation)) {
               if (!this.wh.mkdirs(partLocation, true)) {
                  throw new MetaException(partLocation + " is not a directory or unable to create one");
               }

               madeDir = true;
            }

            long time = System.currentTimeMillis() / 1000L;
            part.setCreateTime((int)time);
            part.putToParameters("transient_lastDdlTime", Long.toString(time));
            if (HiveConf.getBoolVar(this.hiveConf, ConfVars.HIVESTATSAUTOGATHER) && !MetaStoreUtils.isView(tbl)) {
               MetaStoreUtils.updatePartitionStatsFast(part, this.wh, madeDir, envContext);
            }

            if (ms.addPartition(part)) {
               if (!this.transactionalListeners.isEmpty()) {
                  transactionalListenerResponses = MetaStoreListenerNotifier.notifyEvent(this.transactionalListeners, EventMessage.EventType.ADD_PARTITION, new AddPartitionEvent(tbl, part, true, this), envContext);
               }

               success = ms.commitTransaction();
            }
         } finally {
            if (!success) {
               ms.rollbackTransaction();
               if (madeDir) {
                  this.wh.deleteDir(partLocation, true);
               }
            }

            if (!this.listeners.isEmpty()) {
               MetaStoreListenerNotifier.notifyEvent(this.listeners, EventMessage.EventType.ADD_PARTITION, new AddPartitionEvent(tbl, part, success, this), envContext, transactionalListenerResponses, ms);
            }

         }

         return part;
      }

      private void firePreEvent(PreEventContext event) throws MetaException {
         for(MetaStorePreEventListener listener : this.preListeners) {
            try {
               listener.onEvent(event);
            } catch (NoSuchObjectException e) {
               throw new MetaException(e.getMessage());
            } catch (InvalidOperationException e) {
               throw new MetaException(e.getMessage());
            }
         }

      }

      public Partition append_partition(String dbName, String tableName, List part_vals) throws InvalidObjectException, AlreadyExistsException, MetaException {
         return this.append_partition_with_environment_context(dbName, tableName, part_vals, (EnvironmentContext)null);
      }

      public Partition append_partition_with_environment_context(String dbName, String tableName, List part_vals, EnvironmentContext envContext) throws InvalidObjectException, AlreadyExistsException, MetaException {
         this.startPartitionFunction("append_partition", dbName, tableName, part_vals);
         if (LOG.isDebugEnabled()) {
            for(String part : part_vals) {
               LOG.debug(part);
            }
         }

         Partition ret = null;
         Exception ex = null;

         try {
            ret = this.append_partition_common(this.getMS(), dbName, tableName, part_vals, envContext);
         } catch (Exception e) {
            ex = e;
            if (e instanceof MetaException) {
               throw (MetaException)e;
            }

            if (e instanceof InvalidObjectException) {
               throw (InvalidObjectException)e;
            }

            if (e instanceof AlreadyExistsException) {
               throw (AlreadyExistsException)e;
            }

            throw newMetaException(e);
         } finally {
            this.endFunction("append_partition", ret != null, ex, tableName);
         }

         return ret;
      }

      private List add_partitions_core(RawStore ms, String dbName, String tblName, List parts, boolean ifNotExists) throws MetaException, InvalidObjectException, AlreadyExistsException, TException {
         logInfo("add_partitions");
         boolean success = false;
         final Map<PartValEqWrapper, Boolean> addedPartitions = Collections.synchronizedMap(new HashMap());
         List<Partition> newParts = new ArrayList();
         List<Partition> existingParts = new ArrayList();
         Table tbl = null;
         Map<String, String> transactionalListenerResponses = Collections.emptyMap();

         try {
            ms.openTransaction();
            tbl = ms.getTable(dbName, tblName);
            if (tbl == null) {
               throw new InvalidObjectException("Unable to add partitions because database or table " + dbName + "." + tblName + " does not exist");
            }

            if (!parts.isEmpty()) {
               this.firePreEvent(new PreAddPartitionEvent(tbl, parts, this));
            }

            List<Future<Partition>> partFutures = Lists.newArrayList();
            final Table table = tbl;

            for(final Partition part : parts) {
               if (!part.getTableName().equals(tblName) || !part.getDbName().equals(dbName)) {
                  throw new MetaException("Partition does not belong to target table " + dbName + "." + tblName + ": " + part);
               }

               boolean shouldAdd = this.startAddPartition(ms, part, ifNotExists);
               if (!shouldAdd) {
                  existingParts.add(part);
                  LOG.info("Not adding partition " + part + " as it already exists");
               } else {
                  final UserGroupInformation ugi;
                  try {
                     ugi = UserGroupInformation.getCurrentUser();
                  } catch (IOException e) {
                     throw new RuntimeException(e);
                  }

                  partFutures.add(threadPool.submit(new Callable() {
                     public Partition call() throws Exception {
                        ugi.doAs(new PrivilegedExceptionAction() {
                           public Object run() throws Exception {
                              try {
                                 boolean madeDir = HMSHandler.this.createLocationForAddedPartition(table, part);
                                 if (addedPartitions.put(new PartValEqWrapper(part), madeDir) != null) {
                                    throw new MetaException("Duplicate partitions in the list: " + part);
                                 } else {
                                    HMSHandler.this.initializeAddedPartition(table, part, madeDir);
                                    return null;
                                 }
                              } catch (MetaException e) {
                                 throw new IOException(e.getMessage(), e);
                              }
                           }
                        });
                        return part;
                     }
                  }));
               }
            }

            try {
               for(Future partFuture : partFutures) {
                  Partition part = (Partition)partFuture.get();
                  if (part != null) {
                     newParts.add(part);
                  }
               }
            } catch (ExecutionException | InterruptedException e) {
               for(Future partFuture : partFutures) {
                  partFuture.cancel(true);
               }

               throw new MetaException(((Exception)e).getMessage());
            }

            if (!newParts.isEmpty()) {
               ms.addPartitions(dbName, tblName, newParts);
            } else {
               success = true;
            }

            success = false;
            if (!this.transactionalListeners.isEmpty()) {
               transactionalListenerResponses = MetaStoreListenerNotifier.notifyEvent(this.transactionalListeners, EventMessage.EventType.ADD_PARTITION, new AddPartitionEvent(tbl, newParts, true, this));
            }

            success = ms.commitTransaction();
         } finally {
            if (!success) {
               ms.rollbackTransaction();

               for(Map.Entry e : addedPartitions.entrySet()) {
                  if ((Boolean)e.getValue()) {
                     this.wh.deleteDir(new Path(((PartValEqWrapper)e.getKey()).partition.getSd().getLocation()), true);
                  }
               }

               if (!this.listeners.isEmpty()) {
                  MetaStoreListenerNotifier.notifyEvent(this.listeners, EventMessage.EventType.ADD_PARTITION, new AddPartitionEvent(tbl, parts, false, this), (EnvironmentContext)null, (Map)null, ms);
               }
            } else if (!this.listeners.isEmpty()) {
               MetaStoreListenerNotifier.notifyEvent(this.listeners, EventMessage.EventType.ADD_PARTITION, new AddPartitionEvent(tbl, newParts, true, this), (EnvironmentContext)null, transactionalListenerResponses, ms);
               if (!existingParts.isEmpty()) {
                  MetaStoreListenerNotifier.notifyEvent(this.listeners, EventMessage.EventType.ADD_PARTITION, new AddPartitionEvent(tbl, existingParts, false, this), (EnvironmentContext)null, (Map)null, ms);
               }
            }

         }

         return newParts;
      }

      public AddPartitionsResult add_partitions_req(AddPartitionsRequest request) throws InvalidObjectException, AlreadyExistsException, MetaException, TException {
         AddPartitionsResult result = new AddPartitionsResult();
         if (request.getParts().isEmpty()) {
            return result;
         } else {
            try {
               List<Partition> parts = this.add_partitions_core(this.getMS(), request.getDbName(), request.getTblName(), request.getParts(), request.isIfNotExists());
               if (request.isNeedResult()) {
                  result.setPartitions(parts);
               }

               return result;
            } catch (TException te) {
               throw te;
            } catch (Exception e) {
               throw newMetaException(e);
            }
         }
      }

      public int add_partitions(List parts) throws MetaException, InvalidObjectException, AlreadyExistsException {
         this.startFunction("add_partition");
         if (parts.size() == 0) {
            return 0;
         } else {
            Integer ret = null;
            Exception ex = null;

            try {
               ret = this.add_partitions_core(this.getMS(), ((Partition)parts.get(0)).getDbName(), ((Partition)parts.get(0)).getTableName(), parts, false).size();

               assert ret == parts.size();
            } catch (Exception e) {
               ex = e;
               if (e instanceof MetaException) {
                  throw (MetaException)e;
               }

               if (e instanceof InvalidObjectException) {
                  throw (InvalidObjectException)e;
               }

               if (e instanceof AlreadyExistsException) {
                  throw (AlreadyExistsException)e;
               }

               throw newMetaException(e);
            } finally {
               String tableName = ((Partition)parts.get(0)).getTableName();
               this.endFunction("add_partition", ret != null, ex, tableName);
            }

            return ret;
         }
      }

      public int add_partitions_pspec(List partSpecs) throws TException {
         logInfo("add_partitions_pspec");
         if (partSpecs.isEmpty()) {
            return 0;
         } else {
            String dbName = ((PartitionSpec)partSpecs.get(0)).getDbName();
            String tableName = ((PartitionSpec)partSpecs.get(0)).getTableName();
            return this.add_partitions_pspec_core(this.getMS(), dbName, tableName, partSpecs, false);
         }
      }

      private int add_partitions_pspec_core(RawStore ms, String dbName, String tblName, List partSpecs, boolean ifNotExists) throws TException {
         boolean success = false;
         final Map<PartValEqWrapperLite, Boolean> addedPartitions = Collections.synchronizedMap(new HashMap());
         PartitionSpecProxy partitionSpecProxy = PartitionSpecProxy.Factory.get(partSpecs);
         PartitionSpecProxy.PartitionIterator partitionIterator = partitionSpecProxy.getPartitionIterator();
         Table tbl = null;
         Map<String, String> transactionalListenerResponses = Collections.emptyMap();

         int var29;
         try {
            ms.openTransaction();
            tbl = ms.getTable(dbName, tblName);
            if (tbl == null) {
               throw new InvalidObjectException("Unable to add partitions because database or table " + dbName + "." + tblName + " does not exist");
            }

            this.firePreEvent(new PreAddPartitionEvent(tbl, partitionSpecProxy, this));
            List<Future<Partition>> partFutures = Lists.newArrayList();
            final Table table = tbl;

            while(partitionIterator.hasNext()) {
               final Partition part = partitionIterator.getCurrent();
               if (!part.getTableName().equals(tblName) || !part.getDbName().equals(dbName)) {
                  throw new MetaException("Partition does not belong to target table " + dbName + "." + tblName + ": " + part);
               }

               boolean shouldAdd = this.startAddPartition(ms, part, ifNotExists);
               if (!shouldAdd) {
                  LOG.info("Not adding partition " + part + " as it already exists");
               } else {
                  final UserGroupInformation ugi;
                  try {
                     ugi = UserGroupInformation.getCurrentUser();
                  } catch (IOException e) {
                     throw new RuntimeException(e);
                  }

                  partFutures.add(threadPool.submit(new Callable() {
                     public Object call() throws Exception {
                        ugi.doAs(new PrivilegedExceptionAction() {
                           public Object run() throws Exception {
                              try {
                                 boolean madeDir = HMSHandler.this.createLocationForAddedPartition(table, part);
                                 if (addedPartitions.put(new PartValEqWrapperLite(part), madeDir) != null) {
                                    throw new MetaException("Duplicate partitions in the list: " + part);
                                 } else {
                                    HMSHandler.this.initializeAddedPartition(table, part, madeDir);
                                    return null;
                                 }
                              } catch (MetaException e) {
                                 throw new IOException(e.getMessage(), e);
                              }
                           }
                        });
                        return part;
                     }
                  }));
                  partitionIterator.next();
               }
            }

            try {
               for(Future partFuture : partFutures) {
                  Partition partFuture = (Partition)partFuture.get();
               }
            } catch (ExecutionException | InterruptedException e) {
               for(Future partFuture : partFutures) {
                  partFuture.cancel(true);
               }

               throw new MetaException(((Exception)e).getMessage());
            }

            ms.addPartitions(dbName, tblName, partitionSpecProxy, ifNotExists);
            success = false;
            if (!this.transactionalListeners.isEmpty()) {
               transactionalListenerResponses = MetaStoreListenerNotifier.notifyEvent(this.transactionalListeners, EventMessage.EventType.ADD_PARTITION, new AddPartitionEvent(tbl, partitionSpecProxy, true, this));
            }

            success = ms.commitTransaction();
            var29 = addedPartitions.size();
         } finally {
            if (!success) {
               ms.rollbackTransaction();

               for(Map.Entry e : addedPartitions.entrySet()) {
                  if ((Boolean)e.getValue()) {
                     this.wh.deleteDir(new Path(((PartValEqWrapperLite)e.getKey()).location), true);
                  }
               }
            }

            if (!this.listeners.isEmpty()) {
               MetaStoreListenerNotifier.notifyEvent(this.listeners, EventMessage.EventType.ADD_PARTITION, new AddPartitionEvent(tbl, partitionSpecProxy, true, this), (EnvironmentContext)null, transactionalListenerResponses, ms);
            }

         }

         return var29;
      }

      private boolean startAddPartition(RawStore ms, Partition part, boolean ifNotExists) throws MetaException, TException {
         MetaStoreUtils.validatePartitionNameCharacters(part.getValues(), this.partitionValidationPattern);
         boolean doesExist = ms.doesPartitionExist(part.getDbName(), part.getTableName(), part.getValues());
         if (doesExist && !ifNotExists) {
            throw new AlreadyExistsException("Partition already exists: " + part);
         } else {
            return !doesExist;
         }
      }

      private boolean createLocationForAddedPartition(Table tbl, Partition part) throws MetaException {
         Path partLocation = null;
         String partLocationStr = null;
         if (part.getSd() != null) {
            partLocationStr = part.getSd().getLocation();
         }

         if (partLocationStr != null && !partLocationStr.isEmpty()) {
            if (tbl.getSd().getLocation() == null) {
               throw new MetaException("Cannot specify location for a view partition");
            }

            partLocation = this.wh.getDnsPath(new Path(partLocationStr));
         } else if (tbl.getSd().getLocation() != null) {
            partLocation = new Path(tbl.getSd().getLocation(), Warehouse.makePartName(tbl.getPartitionKeys(), part.getValues()));
         }

         boolean result = false;
         if (partLocation != null) {
            part.getSd().setLocation(partLocation.toString());
            if (!this.wh.isDir(partLocation)) {
               if (!this.wh.mkdirs(partLocation, true)) {
                  throw new MetaException(partLocation + " is not a directory or unable to create one");
               }

               result = true;
            }
         }

         return result;
      }

      private void initializeAddedPartition(Table tbl, Partition part, boolean madeDir) throws MetaException {
         this.initializeAddedPartition(tbl, (PartitionSpecProxy.PartitionIterator)(new PartitionSpecProxy.SimplePartitionWrapperIterator(part)), madeDir);
      }

      private void initializeAddedPartition(Table tbl, PartitionSpecProxy.PartitionIterator part, boolean madeDir) throws MetaException {
         if (HiveConf.getBoolVar(this.hiveConf, ConfVars.HIVESTATSAUTOGATHER) && !MetaStoreUtils.isView(tbl)) {
            MetaStoreUtils.updatePartitionStatsFast((PartitionSpecProxy.PartitionIterator)part, this.wh, madeDir, false, (EnvironmentContext)null);
         }

         long time = System.currentTimeMillis() / 1000L;
         part.setCreateTime((long)((int)time));
         if (part.getParameters() == null || part.getParameters().get("transient_lastDdlTime") == null) {
            part.putToParameters("transient_lastDdlTime", Long.toString(time));
         }

         Map<String, String> tblParams = tbl.getParameters();
         String inheritProps = this.hiveConf.getVar(ConfVars.METASTORE_PART_INHERIT_TBL_PROPS).trim();
         Set<String> inheritKeys = new HashSet(Arrays.asList(inheritProps.split(",")));
         if (inheritKeys.contains("*")) {
            inheritKeys = tblParams.keySet();
         }

         for(String key : inheritKeys) {
            String paramVal = (String)tblParams.get(key);
            if (null != paramVal) {
               part.putToParameters(key, paramVal);
            }
         }

      }

      private Partition add_partition_core(RawStore ms, Partition part, EnvironmentContext envContext) throws InvalidObjectException, AlreadyExistsException, MetaException, TException {
         boolean success = false;
         Table tbl = null;
         Map<String, String> transactionalListenerResponses = Collections.emptyMap();

         try {
            ms.openTransaction();
            tbl = ms.getTable(part.getDbName(), part.getTableName());
            if (tbl == null) {
               throw new InvalidObjectException("Unable to add partition because table or database do not exist");
            }

            this.firePreEvent(new PreAddPartitionEvent(tbl, part, this));
            boolean shouldAdd = this.startAddPartition(ms, part, false);

            assert shouldAdd;

            boolean madeDir = this.createLocationForAddedPartition(tbl, part);

            try {
               this.initializeAddedPartition(tbl, part, madeDir);
               success = ms.addPartition(part);
            } finally {
               if (!success && madeDir) {
                  this.wh.deleteDir(new Path(part.getSd().getLocation()), true);
               }

            }

            success = false;
            if (!this.transactionalListeners.isEmpty()) {
               transactionalListenerResponses = MetaStoreListenerNotifier.notifyEvent(this.transactionalListeners, EventMessage.EventType.ADD_PARTITION, new AddPartitionEvent(tbl, Arrays.asList(part), true, this), envContext);
            }

            success = ms.commitTransaction();
         } finally {
            if (!success) {
               ms.rollbackTransaction();
            }

            if (!this.listeners.isEmpty()) {
               MetaStoreListenerNotifier.notifyEvent(this.listeners, EventMessage.EventType.ADD_PARTITION, new AddPartitionEvent(tbl, Arrays.asList(part), success, this), envContext, transactionalListenerResponses, ms);
            }

         }

         return part;
      }

      public Partition add_partition(Partition part) throws InvalidObjectException, AlreadyExistsException, MetaException {
         return this.add_partition_with_environment_context(part, (EnvironmentContext)null);
      }

      public Partition add_partition_with_environment_context(Partition part, EnvironmentContext envContext) throws InvalidObjectException, AlreadyExistsException, MetaException {
         this.startTableFunction("add_partition", part.getDbName(), part.getTableName());
         Partition ret = null;
         Exception ex = null;

         try {
            ret = this.add_partition_core(this.getMS(), part, envContext);
         } catch (Exception e) {
            ex = e;
            if (e instanceof MetaException) {
               throw (MetaException)e;
            }

            if (e instanceof InvalidObjectException) {
               throw (InvalidObjectException)e;
            }

            if (e instanceof AlreadyExistsException) {
               throw (AlreadyExistsException)e;
            }

            throw newMetaException(e);
         } finally {
            this.endFunction("add_partition", ret != null, ex, part != null ? part.getTableName() : null);
         }

         return ret;
      }

      public Partition exchange_partition(Map partitionSpecs, String sourceDbName, String sourceTableName, String destDbName, String destTableName) throws MetaException, NoSuchObjectException, InvalidObjectException, InvalidInputException, TException {
         this.exchange_partitions(partitionSpecs, sourceDbName, sourceTableName, destDbName, destTableName);
         return new Partition();
      }

      public List exchange_partitions(Map partitionSpecs, String sourceDbName, String sourceTableName, String destDbName, String destTableName) throws MetaException, NoSuchObjectException, InvalidObjectException, InvalidInputException, TException {
         boolean success = false;
         boolean pathCreated = false;
         RawStore ms = this.getMS();
         ms.openTransaction();
         Table destinationTable = ms.getTable(destDbName, destTableName);
         Table sourceTable = ms.getTable(sourceDbName, sourceTableName);
         List<String> partVals = MetaStoreUtils.getPvals(sourceTable.getPartitionKeys(), partitionSpecs);
         List<String> partValsPresent = new ArrayList();
         List<FieldSchema> partitionKeysPresent = new ArrayList();
         int i = 0;

         for(FieldSchema fs : sourceTable.getPartitionKeys()) {
            String partVal = (String)partVals.get(i);
            if (partVal != null && !partVal.equals("")) {
               partValsPresent.add(partVal);
               partitionKeysPresent.add(fs);
            }

            ++i;
         }

         List<Partition> partitionsToExchange = this.get_partitions_ps(sourceDbName, sourceTableName, partVals, (short)-1);
         boolean sameColumns = MetaStoreUtils.compareFieldColumns(sourceTable.getSd().getCols(), destinationTable.getSd().getCols());
         boolean samePartitions = MetaStoreUtils.compareFieldColumns(sourceTable.getPartitionKeys(), destinationTable.getPartitionKeys());
         if (sameColumns && samePartitions) {
            Path sourcePath = new Path(sourceTable.getSd().getLocation(), Warehouse.makePartName(partitionKeysPresent, partValsPresent));
            Path destPath = new Path(destinationTable.getSd().getLocation(), Warehouse.makePartName(partitionKeysPresent, partValsPresent));
            List<Partition> destPartitions = new ArrayList();
            Map<String, String> transactionalListenerResponsesForAddPartition = Collections.emptyMap();
            List<Map<String, String>> transactionalListenerResponsesForDropPartition = Lists.newArrayListWithCapacity(partitionsToExchange.size());

            Object var45;
            try {
               for(Partition partition : partitionsToExchange) {
                  Partition destPartition = new Partition(partition);
                  destPartition.setDbName(destDbName);
                  destPartition.setTableName(destinationTable.getTableName());
                  Path destPartitionPath = new Path(destinationTable.getSd().getLocation(), Warehouse.makePartName(destinationTable.getPartitionKeys(), partition.getValues()));
                  destPartition.getSd().setLocation(destPartitionPath.toString());
                  ms.addPartition(destPartition);
                  destPartitions.add(destPartition);
                  ms.dropPartition(partition.getDbName(), sourceTable.getTableName(), partition.getValues());
               }

               Path destParentPath = destPath.getParent();
               if (!this.wh.isDir(destParentPath) && !this.wh.mkdirs(destParentPath, true)) {
                  throw new MetaException("Unable to create path " + destParentPath);
               }

               pathCreated = this.wh.renameDir(sourcePath, destPath);
               success = false;
               if (!this.transactionalListeners.isEmpty()) {
                  transactionalListenerResponsesForAddPartition = MetaStoreListenerNotifier.notifyEvent(this.transactionalListeners, EventMessage.EventType.ADD_PARTITION, new AddPartitionEvent(destinationTable, destPartitions, true, this));

                  for(Partition partition : partitionsToExchange) {
                     DropPartitionEvent dropPartitionEvent = new DropPartitionEvent(sourceTable, partition, true, true, this);
                     transactionalListenerResponsesForDropPartition.add(MetaStoreListenerNotifier.notifyEvent(this.transactionalListeners, EventMessage.EventType.DROP_PARTITION, dropPartitionEvent));
                  }
               }

               success = ms.commitTransaction();
               var45 = destPartitions;
            } finally {
               if (!success || !pathCreated) {
                  ms.rollbackTransaction();
                  if (pathCreated) {
                     this.wh.renameDir(destPath, sourcePath);
                  }
               }

               if (!this.listeners.isEmpty()) {
                  AddPartitionEvent addPartitionEvent = new AddPartitionEvent(destinationTable, destPartitions, success, this);
                  MetaStoreListenerNotifier.notifyEvent(this.listeners, EventMessage.EventType.ADD_PARTITION, addPartitionEvent, (EnvironmentContext)null, transactionalListenerResponsesForAddPartition, ms);
                  i = 0;

                  for(Partition partition : partitionsToExchange) {
                     DropPartitionEvent dropPartitionEvent = new DropPartitionEvent(sourceTable, partition, success, true, this);
                     Map<String, String> parameters = transactionalListenerResponsesForDropPartition.size() > i ? (Map)transactionalListenerResponsesForDropPartition.get(i) : null;
                     MetaStoreListenerNotifier.notifyEvent(this.listeners, EventMessage.EventType.DROP_PARTITION, dropPartitionEvent, (EnvironmentContext)null, parameters, ms);
                     ++i;
                  }
               }

            }

            return (List)var45;
         } else {
            throw new MetaException("The tables have different schemas. Their partitions cannot be exchanged.");
         }
      }

      private boolean drop_partition_common(RawStore ms, String db_name, String tbl_name, List part_vals, boolean deleteData, EnvironmentContext envContext) throws MetaException, NoSuchObjectException, IOException, InvalidObjectException, InvalidInputException {
         boolean success = false;
         Path partPath = null;
         Table tbl = null;
         Partition part = null;
         boolean isArchived = false;
         Path archiveParentDir = null;
         boolean mustPurge = false;
         boolean isExternalTbl = false;
         Map<String, String> transactionalListenerResponses = Collections.emptyMap();

         try {
            ms.openTransaction();
            part = ms.getPartition(db_name, tbl_name, part_vals);
            tbl = this.get_table_core(db_name, tbl_name);
            isExternalTbl = this.isExternal(tbl);
            this.firePreEvent(new PreDropPartitionEvent(tbl, part, deleteData, this));
            mustPurge = isMustPurge(envContext, tbl);
            if (part == null) {
               throw new NoSuchObjectException("Partition doesn't exist. " + part_vals);
            }

            isArchived = MetaStoreUtils.isArchived(part);
            if (isArchived) {
               archiveParentDir = MetaStoreUtils.getOriginalLocation(part);
               this.verifyIsWritablePath(archiveParentDir);
               this.checkTrashPurgeCombination(archiveParentDir, db_name + "." + tbl_name + "." + part_vals, mustPurge, deleteData && !isExternalTbl);
            }

            if (part.getSd() != null && part.getSd().getLocation() != null) {
               partPath = new Path(part.getSd().getLocation());
               this.verifyIsWritablePath(partPath);
               this.checkTrashPurgeCombination(partPath, db_name + "." + tbl_name + "." + part_vals, mustPurge, deleteData && !isExternalTbl);
            }

            if (!ms.dropPartition(db_name, tbl_name, part_vals)) {
               throw new MetaException("Unable to drop partition");
            }

            if (!this.transactionalListeners.isEmpty()) {
               transactionalListenerResponses = MetaStoreListenerNotifier.notifyEvent(this.transactionalListeners, EventMessage.EventType.DROP_PARTITION, new DropPartitionEvent(tbl, part, true, deleteData, this), envContext);
            }

            success = ms.commitTransaction();
         } finally {
            if (!success) {
               ms.rollbackTransaction();
            } else if (deleteData && (partPath != null || archiveParentDir != null) && !isExternalTbl) {
               if (mustPurge) {
                  LOG.info("dropPartition() will purge " + partPath + " directly, skipping trash.");
               } else {
                  LOG.info("dropPartition() will move " + partPath + " to trash-directory.");
               }

               if (isArchived) {
                  assert archiveParentDir != null;

                  this.wh.deleteDir(archiveParentDir, true, mustPurge);
               } else {
                  assert partPath != null;

                  this.wh.deleteDir(partPath, true, mustPurge);
                  this.deleteParentRecursive(partPath.getParent(), part_vals.size() - 1, mustPurge);
               }
            }

            if (!this.listeners.isEmpty()) {
               MetaStoreListenerNotifier.notifyEvent(this.listeners, EventMessage.EventType.DROP_PARTITION, new DropPartitionEvent(tbl, part, success, deleteData, this), envContext, transactionalListenerResponses, ms);
            }

         }

         return true;
      }

      private static boolean isMustPurge(EnvironmentContext envContext, Table tbl) {
         return envContext != null && Boolean.parseBoolean((String)envContext.getProperties().get("ifPurge")) || tbl.isSetParameters() && "true".equalsIgnoreCase((String)tbl.getParameters().get("auto.purge"));
      }

      private void deleteParentRecursive(Path parent, int depth, boolean mustPurge) throws IOException, MetaException {
         if (depth > 0 && parent != null && this.wh.isWritable(parent)) {
            if (this.wh.isDir(parent) && this.wh.isEmpty(parent)) {
               this.wh.deleteDir(parent, true, mustPurge);
            }

            this.deleteParentRecursive(parent.getParent(), depth - 1, mustPurge);
         }

      }

      public boolean drop_partition(String db_name, String tbl_name, List part_vals, boolean deleteData) throws NoSuchObjectException, MetaException, TException {
         return this.drop_partition_with_environment_context(db_name, tbl_name, part_vals, deleteData, (EnvironmentContext)null);
      }

      public DropPartitionsResult drop_partitions_req(DropPartitionsRequest request) throws MetaException, NoSuchObjectException, TException {
         RawStore ms = this.getMS();
         String dbName = request.getDbName();
         String tblName = request.getTblName();
         boolean ifExists = request.isSetIfExists() && request.isIfExists();
         boolean deleteData = request.isSetDeleteData() && request.isDeleteData();
         boolean ignoreProtection = request.isSetIgnoreProtection() && request.isIgnoreProtection();
         boolean needResult = !request.isSetNeedResult() || request.isNeedResult();
         List<PathAndPartValSize> dirsToDelete = new ArrayList();
         List<Path> archToDelete = new ArrayList();
         EnvironmentContext envContext = request.isSetEnvironmentContext() ? request.getEnvironmentContext() : null;
         boolean success = false;
         ms.openTransaction();
         Table tbl = null;
         List<Partition> parts = null;
         boolean mustPurge = false;
         boolean isExternalTbl = false;
         List<Map<String, String>> transactionalListenerResponses = Lists.newArrayList();
         boolean var36 = false;

         DropPartitionsResult var49;
         try {
            var36 = true;
            tbl = this.get_table_core(dbName, tblName);
            isExternalTbl = this.isExternal(tbl);
            mustPurge = isMustPurge(envContext, tbl);
            int minCount = 0;
            RequestPartsSpec spec = request.getParts();
            List partNames = null;
            if (!spec.isSetExprs()) {
               if (!spec.isSetNames()) {
                  throw new MetaException("Partition spec is not set");
               }

               partNames = spec.getNames();
               minCount = ((List)partNames).size();
               parts = ms.getPartitionsByNames(dbName, tblName, (List)partNames);
            } else {
               parts = new ArrayList(spec.getExprs().size());

               for(DropPartitionsExpr expr : spec.getExprs()) {
                  ++minCount;
                  List<Partition> result = new ArrayList();
                  boolean hasUnknown = ms.getPartitionsByExpr(dbName, tblName, expr.getExpr(), (String)null, (short)-1, result);
                  if (hasUnknown) {
                     throw new MetaException("Unexpected unknown partitions to drop");
                  }

                  if (!ignoreProtection && expr.isSetPartArchiveLevel()) {
                     for(Partition part : parts) {
                        if (MetaStoreUtils.isArchived(part) && MetaStoreUtils.getArchivingLevel(part) < expr.getPartArchiveLevel()) {
                           throw new MetaException("Cannot drop a subset of partitions  in an archive, partition " + part);
                        }
                     }
                  }

                  parts.addAll(result);
               }
            }

            if (parts.size() < minCount && !ifExists) {
               throw new NoSuchObjectException("Some partitions to drop are missing");
            }

            List<String> colNames = null;
            if (partNames == null) {
               partNames = new ArrayList(parts.size());
               colNames = new ArrayList(tbl.getPartitionKeys().size());

               for(FieldSchema col : tbl.getPartitionKeys()) {
                  colNames.add(col.getName());
               }
            }

            for(Partition part : parts) {
               this.firePreEvent(new PreDropPartitionEvent(tbl, part, deleteData, this));
               if (colNames != null) {
                  ((List)partNames).add(FileUtils.makePartName(colNames, part.getValues()));
               }

               if (MetaStoreUtils.isArchived(part)) {
                  Path archiveParentDir = MetaStoreUtils.getOriginalLocation(part);
                  this.verifyIsWritablePath(archiveParentDir);
                  this.checkTrashPurgeCombination(archiveParentDir, dbName + "." + tblName + "." + part.getValues(), mustPurge, deleteData && !isExternalTbl);
                  archToDelete.add(archiveParentDir);
               }

               if (part.getSd() != null && part.getSd().getLocation() != null) {
                  Path partPath = new Path(part.getSd().getLocation());
                  this.verifyIsWritablePath(partPath);
                  this.checkTrashPurgeCombination(partPath, dbName + "." + tblName + "." + part.getValues(), mustPurge, deleteData && !isExternalTbl);
                  dirsToDelete.add(new PathAndPartValSize(partPath, part.getValues().size()));
               }
            }

            ms.dropPartitions(dbName, tblName, (List)partNames);
            if (parts != null && !this.transactionalListeners.isEmpty()) {
               for(Partition part : parts) {
                  transactionalListenerResponses.add(MetaStoreListenerNotifier.notifyEvent(this.transactionalListeners, EventMessage.EventType.DROP_PARTITION, new DropPartitionEvent(tbl, part, true, deleteData, this), envContext));
               }
            }

            success = ms.commitTransaction();
            DropPartitionsResult result = new DropPartitionsResult();
            if (needResult) {
               result.setPartitions(parts);
            }

            var49 = result;
            var36 = false;
         } finally {
            if (var36) {
               if (!success) {
                  ms.rollbackTransaction();
               } else if (deleteData && !this.isExternal(tbl)) {
                  LOG.info(mustPurge ? "dropPartition() will purge partition-directories directly, skipping trash." : "dropPartition() will move partition-directories to trash-directory.");

                  for(Path path : archToDelete) {
                     this.wh.deleteDir(path, true, mustPurge);
                  }

                  for(PathAndPartValSize p : dirsToDelete) {
                     this.wh.deleteDir(p.path, true, mustPurge);

                     try {
                        this.deleteParentRecursive(p.path.getParent(), p.partValSize - 1, mustPurge);
                     } catch (IOException ex) {
                        LOG.warn("Error from deleteParentRecursive", ex);
                        throw new MetaException("Failed to delete parent: " + ex.getMessage());
                     }
                  }
               }

               if (parts != null) {
                  int i = 0;
                  if (parts != null && !this.listeners.isEmpty()) {
                     for(Partition part : parts) {
                        Map<String, String> parameters = !transactionalListenerResponses.isEmpty() ? (Map)transactionalListenerResponses.get(i) : null;
                        MetaStoreListenerNotifier.notifyEvent(this.listeners, EventMessage.EventType.DROP_PARTITION, new DropPartitionEvent(tbl, part, success, deleteData, this), envContext, parameters, ms);
                        ++i;
                     }
                  }
               }

            }
         }

         if (!success) {
            ms.rollbackTransaction();
         } else if (deleteData && !this.isExternal(tbl)) {
            LOG.info(mustPurge ? "dropPartition() will purge partition-directories directly, skipping trash." : "dropPartition() will move partition-directories to trash-directory.");

            for(Path path : archToDelete) {
               this.wh.deleteDir(path, true, mustPurge);
            }

            for(PathAndPartValSize p : dirsToDelete) {
               this.wh.deleteDir(p.path, true, mustPurge);

               try {
                  this.deleteParentRecursive(p.path.getParent(), p.partValSize - 1, mustPurge);
               } catch (IOException ex) {
                  LOG.warn("Error from deleteParentRecursive", ex);
                  throw new MetaException("Failed to delete parent: " + ex.getMessage());
               }
            }
         }

         if (parts != null) {
            int i = 0;
            if (parts != null && !this.listeners.isEmpty()) {
               for(Partition part : parts) {
                  Map<String, String> parameters = !transactionalListenerResponses.isEmpty() ? (Map)transactionalListenerResponses.get(i) : null;
                  MetaStoreListenerNotifier.notifyEvent(this.listeners, EventMessage.EventType.DROP_PARTITION, new DropPartitionEvent(tbl, part, success, deleteData, this), envContext, parameters, ms);
                  ++i;
               }
            }
         }

         return var49;
      }

      private void verifyIsWritablePath(Path dir) throws MetaException {
         try {
            if (!this.wh.isWritable(dir.getParent())) {
               throw new MetaException("Table partition not deleted since " + dir.getParent() + " is not writable by " + this.hiveConf.getUser());
            }
         } catch (IOException ex) {
            LOG.warn("Error from isWritable", ex);
            throw new MetaException("Table partition not deleted since " + dir.getParent() + " access cannot be checked: " + ex.getMessage());
         }
      }

      public boolean drop_partition_with_environment_context(String db_name, String tbl_name, List part_vals, boolean deleteData, EnvironmentContext envContext) throws NoSuchObjectException, MetaException, TException {
         this.startPartitionFunction("drop_partition", db_name, tbl_name, part_vals);
         LOG.info("Partition values:" + part_vals);
         boolean ret = false;
         Exception ex = null;

         try {
            ret = this.drop_partition_common(this.getMS(), db_name, tbl_name, part_vals, deleteData, envContext);
         } catch (IOException e) {
            ex = e;
            throw new MetaException(e.getMessage());
         } catch (Exception e) {
            ex = e;
            this.rethrowException(e);
         } finally {
            this.endFunction("drop_partition", ret, ex, tbl_name);
         }

         return ret;
      }

      public Partition get_partition(String db_name, String tbl_name, List part_vals) throws MetaException, NoSuchObjectException {
         this.startPartitionFunction("get_partition", db_name, tbl_name, part_vals);
         Partition ret = null;
         Exception ex = null;

         try {
            this.fireReadTablePreEvent(db_name, tbl_name);
            ret = this.getMS().getPartition(db_name, tbl_name, part_vals);
         } catch (Exception e) {
            ex = e;
            if (e instanceof MetaException) {
               throw (MetaException)e;
            }

            if (e instanceof NoSuchObjectException) {
               throw (NoSuchObjectException)e;
            }

            throw newMetaException(e);
         } finally {
            this.endFunction("get_partition", ret != null, ex, tbl_name);
         }

         return ret;
      }

      private void fireReadTablePreEvent(String dbName, String tblName) throws MetaException, NoSuchObjectException {
         if (this.preListeners.size() > 0) {
            Table t = this.getMS().getTable(dbName, tblName);
            if (t == null) {
               throw new NoSuchObjectException(dbName + "." + tblName + " table not found");
            }

            this.firePreEvent(new PreReadTableEvent(t, this));
         }

      }

      public Partition get_partition_with_auth(String db_name, String tbl_name, List part_vals, String user_name, List group_names) throws MetaException, NoSuchObjectException, TException {
         this.startPartitionFunction("get_partition_with_auth", db_name, tbl_name, part_vals);
         this.fireReadTablePreEvent(db_name, tbl_name);
         Partition ret = null;
         Exception ex = null;

         try {
            ret = this.getMS().getPartitionWithAuth(db_name, tbl_name, part_vals, user_name, group_names);
         } catch (InvalidObjectException e) {
            ex = e;
            throw new NoSuchObjectException(e.getMessage());
         } catch (Exception e) {
            ex = e;
            this.rethrowException(e);
         } finally {
            this.endFunction("get_partition_with_auth", ret != null, ex, tbl_name);
         }

         return ret;
      }

      public List get_partitions(String db_name, String tbl_name, short max_parts) throws NoSuchObjectException, MetaException {
         this.startTableFunction("get_partitions", db_name, tbl_name);
         this.fireReadTablePreEvent(db_name, tbl_name);
         List<Partition> ret = null;
         Exception ex = null;

         try {
            this.checkLimitNumberOfPartitionsByFilter(db_name, tbl_name, "", max_parts);
            ret = this.getMS().getPartitions(db_name, tbl_name, max_parts);
         } catch (Exception e) {
            ex = e;
            if (e instanceof MetaException) {
               throw (MetaException)e;
            }

            if (e instanceof NoSuchObjectException) {
               throw (NoSuchObjectException)e;
            }

            throw newMetaException(e);
         } finally {
            this.endFunction("get_partitions", ret != null, ex, tbl_name);
         }

         return ret;
      }

      public List get_partitions_with_auth(String dbName, String tblName, short maxParts, String userName, List groupNames) throws NoSuchObjectException, MetaException, TException {
         this.startTableFunction("get_partitions_with_auth", dbName, tblName);
         List<Partition> ret = null;
         Exception ex = null;

         try {
            this.checkLimitNumberOfPartitionsByFilter(dbName, tblName, "", maxParts);
            ret = this.getMS().getPartitionsWithAuth(dbName, tblName, maxParts, userName, groupNames);
         } catch (InvalidObjectException e) {
            ex = e;
            throw new NoSuchObjectException(e.getMessage());
         } catch (Exception e) {
            ex = e;
            this.rethrowException(e);
         } finally {
            this.endFunction("get_partitions_with_auth", ret != null, ex, tblName);
         }

         return ret;
      }

      private void checkLimitNumberOfPartitionsByFilter(String dbName, String tblName, String filterString, int maxParts) throws TException {
         if (this.isPartitionLimitEnabled()) {
            this.checkLimitNumberOfPartitions(tblName, this.get_num_partitions_by_filter(dbName, tblName, filterString), maxParts);
         }

      }

      private void checkLimitNumberOfPartitionsByExpr(String dbName, String tblName, byte[] filterExpr, int maxParts) throws TException {
         if (this.isPartitionLimitEnabled()) {
            this.checkLimitNumberOfPartitions(tblName, this.get_num_partitions_by_expr(dbName, tblName, filterExpr), maxParts);
         }

      }

      private boolean isPartitionLimitEnabled() {
         int partitionLimit = HiveConf.getIntVar(this.hiveConf, ConfVars.METASTORE_LIMIT_PARTITION_REQUEST);
         return partitionLimit > -1;
      }

      private void checkLimitNumberOfPartitions(String tblName, int numPartitions, int maxToFetch) throws MetaException {
         if (this.isPartitionLimitEnabled()) {
            int partitionLimit = HiveConf.getIntVar(this.hiveConf, ConfVars.METASTORE_LIMIT_PARTITION_REQUEST);
            int partitionRequest = maxToFetch < 0 ? numPartitions : maxToFetch;
            if (partitionRequest > partitionLimit) {
               String configName = ConfVars.METASTORE_LIMIT_PARTITION_REQUEST.varname;
               throw new MetaException(String.format("Number of partitions scanned (=%d) on table '%s' exceeds limit (=%d). This is controlled on the metastore server by %s.", partitionRequest, tblName, partitionLimit, configName));
            }
         }

      }

      public List get_partitions_pspec(String db_name, String tbl_name, int max_parts) throws NoSuchObjectException, MetaException {
         String dbName = db_name.toLowerCase();
         String tableName = tbl_name.toLowerCase();
         this.startTableFunction("get_partitions_pspec", dbName, tableName);
         List<PartitionSpec> partitionSpecs = null;

         List var13;
         try {
            Table table = this.get_table_core(dbName, tableName);
            List<Partition> partitions = this.get_partitions(dbName, tableName, (short)max_parts);
            if (is_partition_spec_grouping_enabled(table)) {
               partitionSpecs = this.get_partitionspecs_grouped_by_storage_descriptor(table, partitions);
            } else {
               PartitionSpec pSpec = new PartitionSpec();
               pSpec.setPartitionList(new PartitionListComposingSpec(partitions));
               pSpec.setDbName(dbName);
               pSpec.setTableName(tableName);
               pSpec.setRootPath(table.getSd().getLocation());
               partitionSpecs = Arrays.asList(pSpec);
            }

            var13 = partitionSpecs;
         } finally {
            this.endFunction("get_partitions_pspec", partitionSpecs != null && !partitionSpecs.isEmpty(), (Exception)null, tbl_name);
         }

         return var13;
      }

      private List get_partitionspecs_grouped_by_storage_descriptor(Table table, List partitions) throws NoSuchObjectException, MetaException {
         assert is_partition_spec_grouping_enabled(table);

         final String tablePath = table.getSd().getLocation();
         ImmutableListMultimap<Boolean, Partition> partitionsWithinTableDirectory = Multimaps.index(partitions, new com.google.common.base.Function() {
            public Boolean apply(Partition input) {
               return input.getSd().getLocation().startsWith(tablePath);
            }
         });
         List<PartitionSpec> partSpecs = new ArrayList();
         Map<StorageDescriptorKey, List<PartitionWithoutSD>> sdToPartList = new HashMap();
         if (partitionsWithinTableDirectory.containsKey(true)) {
            ImmutableList<Partition> partsWithinTableDir = partitionsWithinTableDirectory.get(true);

            PartitionWithoutSD partitionWithoutSD;
            StorageDescriptorKey sdKey;
            for(UnmodifiableIterator var8 = partsWithinTableDir.iterator(); var8.hasNext(); ((List)sdToPartList.get(sdKey)).add(partitionWithoutSD)) {
               Partition partition = (Partition)var8.next();
               partitionWithoutSD = new PartitionWithoutSD(partition.getValues(), partition.getCreateTime(), partition.getLastAccessTime(), partition.getSd().getLocation().substring(tablePath.length()), partition.getParameters());
               sdKey = new StorageDescriptorKey(partition.getSd());
               if (!sdToPartList.containsKey(sdKey)) {
                  sdToPartList.put(sdKey, new ArrayList());
               }
            }

            for(Map.Entry entry : sdToPartList.entrySet()) {
               partSpecs.add(this.getSharedSDPartSpec(table, (StorageDescriptorKey)entry.getKey(), (List)entry.getValue()));
            }
         }

         if (partitionsWithinTableDirectory.containsKey(false)) {
            List<Partition> partitionsOutsideTableDir = partitionsWithinTableDirectory.get(false);
            if (!partitionsOutsideTableDir.isEmpty()) {
               PartitionSpec partListSpec = new PartitionSpec();
               partListSpec.setDbName(table.getDbName());
               partListSpec.setTableName(table.getTableName());
               partListSpec.setPartitionList(new PartitionListComposingSpec(partitionsOutsideTableDir));
               partSpecs.add(partListSpec);
            }
         }

         return partSpecs;
      }

      private PartitionSpec getSharedSDPartSpec(Table table, StorageDescriptorKey sdKey, List partitions) {
         StorageDescriptor sd = new StorageDescriptor(sdKey.getSd());
         sd.setLocation(table.getSd().getLocation());
         PartitionSpecWithSharedSD sharedSDPartSpec = new PartitionSpecWithSharedSD(partitions, sd);
         PartitionSpec ret = new PartitionSpec();
         ret.setRootPath(sd.getLocation());
         ret.setSharedSDPartitionSpec(sharedSDPartSpec);
         ret.setDbName(table.getDbName());
         ret.setTableName(table.getTableName());
         return ret;
      }

      private static boolean is_partition_spec_grouping_enabled(Table table) {
         Map<String, String> parameters = table.getParameters();
         return parameters.containsKey("hive.hcatalog.partition.spec.grouping.enabled") && ((String)parameters.get("hive.hcatalog.partition.spec.grouping.enabled")).equalsIgnoreCase("true");
      }

      public List get_partition_names(String db_name, String tbl_name, short max_parts) throws MetaException, NoSuchObjectException {
         this.startTableFunction("get_partition_names", db_name, tbl_name);
         this.fireReadTablePreEvent(db_name, tbl_name);
         List<String> ret = null;
         Exception ex = null;

         try {
            ret = this.getMS().listPartitionNames(db_name, tbl_name, max_parts);
         } catch (Exception e) {
            ex = e;
            if (e instanceof MetaException) {
               throw (MetaException)e;
            }

            throw newMetaException(e);
         } finally {
            this.endFunction("get_partition_names", ret != null, ex, tbl_name);
         }

         return ret;
      }

      public PartitionValuesResponse get_partition_values(PartitionValuesRequest request) throws MetaException {
         String dbName = request.getDbName();
         String tblName = request.getTblName();
         List<FieldSchema> partCols = new ArrayList();
         partCols.add(request.getPartitionKeys().get(0));
         return this.getMS().listPartitionValues(dbName, tblName, request.getPartitionKeys(), request.isApplyDistinct(), request.getFilter(), request.isAscending(), request.getPartitionOrder(), request.getMaxParts());
      }

      public void alter_partition(String db_name, String tbl_name, Partition new_part) throws InvalidOperationException, MetaException, TException {
         this.rename_partition(db_name, tbl_name, (List)null, new_part);
      }

      public void alter_partition_with_environment_context(String dbName, String tableName, Partition newPartition, EnvironmentContext envContext) throws InvalidOperationException, MetaException, TException {
         this.rename_partition(dbName, tableName, (List)null, newPartition, envContext);
      }

      public void rename_partition(String db_name, String tbl_name, List part_vals, Partition new_part) throws InvalidOperationException, MetaException, TException {
         this.rename_partition(db_name, tbl_name, part_vals, new_part, (EnvironmentContext)null);
      }

      private void rename_partition(String db_name, String tbl_name, List part_vals, Partition new_part, EnvironmentContext envContext) throws InvalidOperationException, MetaException, TException {
         this.startTableFunction("alter_partition", db_name, tbl_name);
         if (LOG.isInfoEnabled()) {
            LOG.info("New partition values:" + new_part.getValues());
            if (part_vals != null && part_vals.size() > 0) {
               LOG.info("Old Partition values:" + part_vals);
            }
         }

         if (new_part.getSd() != null) {
            String newLocation = new_part.getSd().getLocation();
            if (org.apache.commons.lang3.StringUtils.isNotEmpty(newLocation)) {
               Path tblPath = this.wh.getDnsPath(new Path(newLocation));
               new_part.getSd().setLocation(tblPath.toString());
            }
         }

         Partition oldPart = null;
         Exception ex = null;

         try {
            this.firePreEvent(new PreAlterPartitionEvent(db_name, tbl_name, part_vals, new_part, this));
            if (part_vals != null && !part_vals.isEmpty()) {
               MetaStoreUtils.validatePartitionNameCharacters(new_part.getValues(), this.partitionValidationPattern);
            }

            oldPart = this.alterHandler.alterPartition(this.getMS(), this.wh, db_name, tbl_name, part_vals, new_part, envContext, this);
            Table table = null;
            if (!this.listeners.isEmpty()) {
               if (table == null) {
                  table = this.getMS().getTable(db_name, tbl_name);
               }

               MetaStoreListenerNotifier.notifyEvent(this.listeners, EventMessage.EventType.ALTER_PARTITION, new AlterPartitionEvent(oldPart, new_part, table, true, this), envContext);
            }
         } catch (InvalidObjectException e) {
            ex = e;
            throw new InvalidOperationException(e.getMessage());
         } catch (AlreadyExistsException e) {
            ex = e;
            throw new InvalidOperationException(e.getMessage());
         } catch (Exception e) {
            ex = e;
            if (e instanceof MetaException) {
               throw (MetaException)e;
            }

            if (e instanceof InvalidOperationException) {
               throw (InvalidOperationException)e;
            }

            if (e instanceof TException) {
               throw (TException)e;
            }

            throw newMetaException(e);
         } finally {
            this.endFunction("alter_partition", oldPart != null, ex, tbl_name);
         }

      }

      public void alter_partitions(String db_name, String tbl_name, List new_parts) throws InvalidOperationException, MetaException, TException {
         this.alter_partitions_with_environment_context(db_name, tbl_name, new_parts, (EnvironmentContext)null);
      }

      public void alter_partitions_with_environment_context(String db_name, String tbl_name, List new_parts, EnvironmentContext environmentContext) throws InvalidOperationException, MetaException, TException {
         this.startTableFunction("alter_partitions", db_name, tbl_name);
         if (LOG.isInfoEnabled()) {
            for(Partition tmpPart : new_parts) {
               LOG.info("New partition values:" + tmpPart.getValues());
            }
         }

         List<Partition> oldParts = null;
         Exception ex = null;

         try {
            for(Partition tmpPart : new_parts) {
               this.firePreEvent(new PreAlterPartitionEvent(db_name, tbl_name, (List)null, tmpPart, this));
            }

            oldParts = this.alterHandler.alterPartitions(this.getMS(), this.wh, db_name, tbl_name, new_parts, environmentContext, this);
            Iterator<Partition> olditr = oldParts.iterator();
            Table table = null;

            for(Partition tmpPart : new_parts) {
               Partition oldTmpPart = null;
               if (!olditr.hasNext()) {
                  throw new InvalidOperationException("failed to alterpartitions");
               }

               oldTmpPart = (Partition)olditr.next();
               if (table == null) {
                  table = this.getMS().getTable(db_name, tbl_name);
               }

               if (!this.listeners.isEmpty()) {
                  MetaStoreListenerNotifier.notifyEvent(this.listeners, EventMessage.EventType.ALTER_PARTITION, new AlterPartitionEvent(oldTmpPart, tmpPart, table, true, this));
               }
            }
         } catch (InvalidObjectException e) {
            ex = e;
            throw new InvalidOperationException(e.getMessage());
         } catch (AlreadyExistsException e) {
            ex = e;
            throw new InvalidOperationException(e.getMessage());
         } catch (Exception e) {
            ex = e;
            if (e instanceof MetaException) {
               throw (MetaException)e;
            }

            if (e instanceof InvalidOperationException) {
               throw (InvalidOperationException)e;
            }

            if (e instanceof TException) {
               throw (TException)e;
            }

            throw newMetaException(e);
         } finally {
            this.endFunction("alter_partition", oldParts != null, ex, tbl_name);
         }

      }

      public void alter_index(String dbname, String base_table_name, String index_name, Index newIndex) throws InvalidOperationException, MetaException {
         this.startFunction("alter_index", ": db=" + dbname + " base_tbl=" + base_table_name + " idx=" + index_name + " newidx=" + newIndex.getIndexName());
         newIndex.putToParameters("transient_lastDdlTime", Long.toString(System.currentTimeMillis() / 1000L));
         boolean success = false;
         Exception ex = null;
         Index oldIndex = null;
         RawStore ms = this.getMS();
         Map<String, String> transactionalListenerResponses = Collections.emptyMap();

         try {
            ms.openTransaction();
            oldIndex = this.get_index_by_name(dbname, base_table_name, index_name);
            this.firePreEvent(new PreAlterIndexEvent(oldIndex, newIndex, this));
            ms.alterIndex(dbname, base_table_name, index_name, newIndex);
            if (!this.transactionalListeners.isEmpty()) {
               transactionalListenerResponses = MetaStoreListenerNotifier.notifyEvent(this.transactionalListeners, EventMessage.EventType.ALTER_INDEX, new AlterIndexEvent(oldIndex, newIndex, true, this));
            }

            success = ms.commitTransaction();
         } catch (InvalidObjectException e) {
            ex = e;
            throw new InvalidOperationException(e.getMessage());
         } catch (Exception e) {
            ex = e;
            if (e instanceof MetaException) {
               throw (MetaException)e;
            }

            if (e instanceof InvalidOperationException) {
               throw (InvalidOperationException)e;
            }

            throw newMetaException(e);
         } finally {
            if (!success) {
               ms.rollbackTransaction();
            }

            this.endFunction("alter_index", success, ex, base_table_name);
            if (!this.listeners.isEmpty()) {
               MetaStoreListenerNotifier.notifyEvent(this.listeners, EventMessage.EventType.ALTER_INDEX, new AlterIndexEvent(oldIndex, newIndex, success, this), (EnvironmentContext)null, transactionalListenerResponses, ms);
            }

         }

      }

      public String getVersion() throws TException {
         this.endFunction(this.startFunction("getVersion"), true, (Exception)null);
         return "3.0";
      }

      public void alter_table(String dbname, String name, Table newTable) throws InvalidOperationException, MetaException {
         this.alter_table_core(dbname, name, newTable, (EnvironmentContext)null);
      }

      public void alter_table_with_cascade(String dbname, String name, Table newTable, boolean cascade) throws InvalidOperationException, MetaException {
         EnvironmentContext envContext = null;
         if (cascade) {
            envContext = new EnvironmentContext();
            envContext.putToProperties("CASCADE", "true");
         }

         this.alter_table_core(dbname, name, newTable, envContext);
      }

      public void alter_table_with_environment_context(String dbname, String name, Table newTable, EnvironmentContext envContext) throws InvalidOperationException, MetaException {
         this.alter_table_core(dbname, name, newTable, envContext);
      }

      private void alter_table_core(String dbname, String name, Table newTable, EnvironmentContext envContext) throws InvalidOperationException, MetaException {
         this.startFunction("alter_table", ": db=" + dbname + " tbl=" + name + " newtbl=" + newTable.getTableName());
         if (newTable.getParameters() == null || newTable.getParameters().get("transient_lastDdlTime") == null) {
            newTable.putToParameters("transient_lastDdlTime", Long.toString(System.currentTimeMillis() / 1000L));
         }

         if (newTable.getSd() != null) {
            String newLocation = newTable.getSd().getLocation();
            if (org.apache.commons.lang3.StringUtils.isNotEmpty(newLocation)) {
               Path tblPath = this.wh.getDnsPath(new Path(newLocation));
               newTable.getSd().setLocation(tblPath.toString());
            }
         }

         boolean success = false;
         Exception ex = null;

         try {
            Table oldt = this.get_table_core(dbname, name);
            this.firePreEvent(new PreAlterTableEvent(oldt, newTable, this));
            this.alterHandler.alterTable(this.getMS(), this.wh, dbname, name, newTable, envContext, this);
            success = true;
         } catch (NoSuchObjectException e) {
            ex = e;
            throw new InvalidOperationException(e.getMessage());
         } catch (Exception e) {
            ex = e;
            if (e instanceof MetaException) {
               throw (MetaException)e;
            }

            if (e instanceof InvalidOperationException) {
               throw (InvalidOperationException)e;
            }

            throw newMetaException(e);
         } finally {
            this.endFunction("alter_table", success, ex, name);
         }

      }

      public List get_tables(String dbname, String pattern) throws MetaException {
         this.startFunction("get_tables", ": db=" + dbname + " pat=" + pattern);
         List<String> ret = null;
         Exception ex = null;

         try {
            ret = this.getMS().getTables(dbname, pattern);
         } catch (Exception e) {
            ex = e;
            if (e instanceof MetaException) {
               throw (MetaException)e;
            }

            throw newMetaException(e);
         } finally {
            this.endFunction("get_tables", ret != null, ex);
         }

         return ret;
      }

      public List get_tables_by_type(String dbname, String pattern, String tableType) throws MetaException {
         this.startFunction("get_tables_by_type", ": db=" + dbname + " pat=" + pattern + ",type=" + tableType);
         List<String> ret = null;
         Exception ex = null;

         try {
            ret = this.getMS().getTables(dbname, pattern, TableType.valueOf(tableType));
         } catch (Exception e) {
            ex = e;
            if (e instanceof MetaException) {
               throw (MetaException)e;
            }

            throw newMetaException(e);
         } finally {
            this.endFunction("get_tables_by_type", ret != null, ex);
         }

         return ret;
      }

      public List get_all_tables(String dbname) throws MetaException {
         this.startFunction("get_all_tables", ": db=" + dbname);
         List<String> ret = null;
         Exception ex = null;

         try {
            ret = this.getMS().getAllTables(dbname);
         } catch (Exception e) {
            ex = e;
            if (e instanceof MetaException) {
               throw (MetaException)e;
            }

            throw newMetaException(e);
         } finally {
            this.endFunction("get_all_tables", ret != null, ex);
         }

         return ret;
      }

      public List get_fields(String db, String tableName) throws MetaException, UnknownTableException, UnknownDBException {
         return this.get_fields_with_environment_context(db, tableName, (EnvironmentContext)null);
      }

      public List get_fields_with_environment_context(String db, String tableName, EnvironmentContext envContext) throws MetaException, UnknownTableException, UnknownDBException {
         this.startFunction("get_fields_with_environment_context", ": db=" + db + "tbl=" + tableName);
         String[] names = tableName.split("\\.");
         String base_table_name = names[0];
         List<FieldSchema> ret = null;
         Exception ex = null;
         ClassLoader orgHiveLoader = null;
         Configuration curConf = this.hiveConf;

         try {
            Table tbl;
            try {
               tbl = this.get_table_core(db, base_table_name);
            } catch (NoSuchObjectException e) {
               throw new UnknownTableException(e.getMessage());
            }

            if (null != tbl.getSd().getSerdeInfo().getSerializationLib() && !this.hiveConf.getStringCollection(ConfVars.SERDESUSINGMETASTOREFORSCHEMA.varname).contains(tbl.getSd().getSerdeInfo().getSerializationLib())) {
               try {
                  if (envContext != null) {
                     String addedJars = (String)envContext.getProperties().get("hive.added.jars.path");
                     if (org.apache.commons.lang3.StringUtils.isNotBlank(addedJars)) {
                        curConf = this.getConf();
                        orgHiveLoader = curConf.getClassLoader();
                        ClassLoader loader = MetaStoreUtils.addToClassPath(orgHiveLoader, org.apache.commons.lang3.StringUtils.split(addedJars, ","));
                        curConf.setClassLoader(loader);
                     }
                  }

                  Deserializer s = MetaStoreUtils.getDeserializer(curConf, tbl, false);
                  ret = MetaStoreUtils.getFieldsFromDeserializer(tableName, s);
               } catch (SerDeException e) {
                  StringUtils.stringifyException(e);
                  throw new MetaException(e.getMessage());
               }
            } else {
               ret = tbl.getSd().getCols();
            }
         } catch (Exception e) {
            ex = e;
            if (e instanceof UnknownDBException) {
               throw (UnknownDBException)e;
            }

            if (e instanceof UnknownTableException) {
               throw (UnknownTableException)e;
            }

            if (e instanceof MetaException) {
               throw (MetaException)e;
            }

            throw newMetaException(e);
         } finally {
            if (orgHiveLoader != null) {
               curConf.setClassLoader(orgHiveLoader);
            }

            this.endFunction("get_fields_with_environment_context", ret != null, ex, tableName);
         }

         return ret;
      }

      public List get_schema(String db, String tableName) throws MetaException, UnknownTableException, UnknownDBException {
         return this.get_schema_with_environment_context(db, tableName, (EnvironmentContext)null);
      }

      public List get_schema_with_environment_context(String db, String tableName, EnvironmentContext envContext) throws MetaException, UnknownTableException, UnknownDBException {
         this.startFunction("get_schema_with_environment_context", ": db=" + db + "tbl=" + tableName);
         boolean success = false;
         Exception ex = null;

         List var10;
         try {
            String[] names = tableName.split("\\.");
            String base_table_name = names[0];

            Table tbl;
            try {
               tbl = this.get_table_core(db, base_table_name);
            } catch (NoSuchObjectException e) {
               throw new UnknownTableException(e.getMessage());
            }

            List<FieldSchema> fieldSchemas = this.get_fields_with_environment_context(db, base_table_name, envContext);
            if (tbl == null || fieldSchemas == null) {
               throw new UnknownTableException(tableName + " doesn't exist");
            }

            if (tbl.getPartitionKeys() != null) {
               fieldSchemas.addAll(tbl.getPartitionKeys());
            }

            success = true;
            var10 = fieldSchemas;
         } catch (Exception e) {
            ex = e;
            if (e instanceof UnknownDBException) {
               throw (UnknownDBException)e;
            }

            if (e instanceof UnknownTableException) {
               throw (UnknownTableException)e;
            }

            if (e instanceof MetaException) {
               throw (MetaException)e;
            }

            MetaException me = new MetaException(e.toString());
            me.initCause(e);
            throw me;
         } finally {
            this.endFunction("get_schema_with_environment_context", success, ex, tableName);
         }

         return var10;
      }

      public String getCpuProfile(int profileDurationInSec) throws TException {
         return "";
      }

      public String get_config_value(String name, String defaultValue) throws TException, ConfigValSecurityException {
         this.startFunction("get_config_value", ": name=" + name + " defaultValue=" + defaultValue);
         boolean success = false;
         Exception ex = null;

         String toReturn;
         try {
            if (name != null) {
               if (!Pattern.matches("(hive|hdfs|mapred).*", name)) {
                  throw new ConfigValSecurityException("For security reasons, the config key " + name + " cannot be accessed");
               }

               toReturn = defaultValue;

               try {
                  toReturn = this.hiveConf.get(name, defaultValue);
               } catch (RuntimeException e) {
                  LOG.error(((Integer)threadLocalId.get()).toString() + ": RuntimeException thrown in get_config_value - msg: " + e.getMessage() + " cause: " + e.getCause());
               }

               success = true;
               String te = toReturn;
               return te;
            }

            success = true;
            toReturn = defaultValue;
         } catch (Exception e) {
            ex = e;
            if (e instanceof ConfigValSecurityException) {
               throw (ConfigValSecurityException)e;
            }

            if (e instanceof TException) {
               throw (TException)e;
            }

            TException te = new TException(e.toString());
            te.initCause(e);
            throw te;
         } finally {
            this.endFunction("get_config_value", success, ex);
         }

         return toReturn;
      }

      private List getPartValsFromName(Table t, String partName) throws MetaException, InvalidObjectException {
         Preconditions.checkArgument(t != null, "Table can not be null");
         LinkedHashMap<String, String> hm = Warehouse.makeSpecFromName(partName);
         List<String> partVals = new ArrayList();

         for(FieldSchema field : t.getPartitionKeys()) {
            String key = field.getName();
            String val = (String)hm.get(key);
            if (val == null) {
               throw new InvalidObjectException("incomplete partition name - missing " + key);
            }

            partVals.add(val);
         }

         return partVals;
      }

      private List getPartValsFromName(RawStore ms, String dbName, String tblName, String partName) throws MetaException, InvalidObjectException {
         Table t = ms.getTable(dbName, tblName);
         if (t == null) {
            throw new InvalidObjectException(dbName + "." + tblName + " table not found");
         } else {
            return this.getPartValsFromName(t, partName);
         }
      }

      private Partition get_partition_by_name_core(RawStore ms, String db_name, String tbl_name, String part_name) throws MetaException, NoSuchObjectException, TException {
         this.fireReadTablePreEvent(db_name, tbl_name);
         List<String> partVals = null;

         try {
            partVals = this.getPartValsFromName(ms, db_name, tbl_name, part_name);
         } catch (InvalidObjectException e) {
            throw new NoSuchObjectException(e.getMessage());
         }

         Partition p = ms.getPartition(db_name, tbl_name, partVals);
         if (p == null) {
            throw new NoSuchObjectException(db_name + "." + tbl_name + " partition (" + part_name + ") not found");
         } else {
            return p;
         }
      }

      public Partition get_partition_by_name(String db_name, String tbl_name, String part_name) throws MetaException, NoSuchObjectException, TException {
         this.startFunction("get_partition_by_name", ": db=" + db_name + " tbl=" + tbl_name + " part=" + part_name);
         Partition ret = null;
         Exception ex = null;

         try {
            ret = this.get_partition_by_name_core(this.getMS(), db_name, tbl_name, part_name);
         } catch (Exception e) {
            ex = e;
            this.rethrowException(e);
         } finally {
            this.endFunction("get_partition_by_name", ret != null, ex, tbl_name);
         }

         return ret;
      }

      public Partition append_partition_by_name(String db_name, String tbl_name, String part_name) throws InvalidObjectException, AlreadyExistsException, MetaException, TException {
         return this.append_partition_by_name_with_environment_context(db_name, tbl_name, part_name, (EnvironmentContext)null);
      }

      public Partition append_partition_by_name_with_environment_context(String db_name, String tbl_name, String part_name, EnvironmentContext env_context) throws InvalidObjectException, AlreadyExistsException, MetaException, TException {
         this.startFunction("append_partition_by_name", ": db=" + db_name + " tbl=" + tbl_name + " part=" + part_name);
         Partition ret = null;
         Exception ex = null;

         try {
            RawStore ms = this.getMS();
            List<String> partVals = this.getPartValsFromName(ms, db_name, tbl_name, part_name);
            ret = this.append_partition_common(ms, db_name, tbl_name, partVals, env_context);
         } catch (Exception e) {
            ex = e;
            if (e instanceof InvalidObjectException) {
               throw (InvalidObjectException)e;
            }

            if (e instanceof AlreadyExistsException) {
               throw (AlreadyExistsException)e;
            }

            if (e instanceof MetaException) {
               throw (MetaException)e;
            }

            if (e instanceof TException) {
               throw (TException)e;
            }

            throw newMetaException(e);
         } finally {
            this.endFunction("append_partition_by_name", ret != null, ex, tbl_name);
         }

         return ret;
      }

      private boolean drop_partition_by_name_core(RawStore ms, String db_name, String tbl_name, String part_name, boolean deleteData, EnvironmentContext envContext) throws NoSuchObjectException, MetaException, TException, IOException, InvalidObjectException, InvalidInputException {
         List<String> partVals = null;

         try {
            partVals = this.getPartValsFromName(ms, db_name, tbl_name, part_name);
         } catch (InvalidObjectException e) {
            throw new NoSuchObjectException(e.getMessage());
         }

         return this.drop_partition_common(ms, db_name, tbl_name, partVals, deleteData, envContext);
      }

      public boolean drop_partition_by_name(String db_name, String tbl_name, String part_name, boolean deleteData) throws NoSuchObjectException, MetaException, TException {
         return this.drop_partition_by_name_with_environment_context(db_name, tbl_name, part_name, deleteData, (EnvironmentContext)null);
      }

      public boolean drop_partition_by_name_with_environment_context(String db_name, String tbl_name, String part_name, boolean deleteData, EnvironmentContext envContext) throws NoSuchObjectException, MetaException, TException {
         this.startFunction("drop_partition_by_name", ": db=" + db_name + " tbl=" + tbl_name + " part=" + part_name);
         boolean ret = false;
         Exception ex = null;

         try {
            ret = this.drop_partition_by_name_core(this.getMS(), db_name, tbl_name, part_name, deleteData, envContext);
         } catch (IOException e) {
            ex = e;
            throw new MetaException(e.getMessage());
         } catch (Exception e) {
            ex = e;
            this.rethrowException(e);
         } finally {
            this.endFunction("drop_partition_by_name", ret, ex, tbl_name);
         }

         return ret;
      }

      public List get_partitions_ps(String db_name, String tbl_name, List part_vals, short max_parts) throws MetaException, TException, NoSuchObjectException {
         this.startPartitionFunction("get_partitions_ps", db_name, tbl_name, part_vals);
         List<Partition> ret = null;
         Exception ex = null;

         try {
            ret = this.get_partitions_ps_with_auth(db_name, tbl_name, part_vals, max_parts, (String)null, (List)null);
         } catch (Exception e) {
            ex = e;
            this.rethrowException(e);
         } finally {
            this.endFunction("get_partitions_ps", ret != null, ex, tbl_name);
         }

         return ret;
      }

      public List get_partitions_ps_with_auth(String db_name, String tbl_name, List part_vals, short max_parts, String userName, List groupNames) throws MetaException, TException, NoSuchObjectException {
         this.startPartitionFunction("get_partitions_ps_with_auth", db_name, tbl_name, part_vals);
         this.fireReadTablePreEvent(db_name, tbl_name);
         List<Partition> ret = null;
         Exception ex = null;

         try {
            ret = this.getMS().listPartitionsPsWithAuth(db_name, tbl_name, part_vals, max_parts, userName, groupNames);
         } catch (InvalidObjectException e) {
            ex = e;
            throw new MetaException(e.getMessage());
         } catch (Exception e) {
            ex = e;
            this.rethrowException(e);
         } finally {
            this.endFunction("get_partitions_ps_with_auth", ret != null, ex, tbl_name);
         }

         return ret;
      }

      public List get_partition_names_ps(String db_name, String tbl_name, List part_vals, short max_parts) throws MetaException, TException, NoSuchObjectException {
         this.startPartitionFunction("get_partitions_names_ps", db_name, tbl_name, part_vals);
         this.fireReadTablePreEvent(db_name, tbl_name);
         List<String> ret = null;
         Exception ex = null;

         try {
            ret = this.getMS().listPartitionNamesPs(db_name, tbl_name, part_vals, max_parts);
         } catch (Exception e) {
            ex = e;
            this.rethrowException(e);
         } finally {
            this.endFunction("get_partitions_names_ps", ret != null, ex, tbl_name);
         }

         return ret;
      }

      public List partition_name_to_vals(String part_name) throws MetaException, TException {
         if (part_name.length() == 0) {
            return new ArrayList();
         } else {
            LinkedHashMap<String, String> map = Warehouse.makeSpecFromName(part_name);
            List<String> part_vals = new ArrayList();
            part_vals.addAll(map.values());
            return part_vals;
         }
      }

      public Map partition_name_to_spec(String part_name) throws MetaException, TException {
         return (Map)(part_name.length() == 0 ? new HashMap() : Warehouse.makeSpecFromName(part_name));
      }

      public Index add_index(Index newIndex, Table indexTable) throws InvalidObjectException, AlreadyExistsException, MetaException, TException {
         this.startFunction("add_index", ": " + newIndex.toString() + " " + indexTable.toString());
         Index ret = null;
         Exception ex = null;

         try {
            ret = this.add_index_core(this.getMS(), newIndex, indexTable);
         } catch (Exception e) {
            ex = e;
            if (e instanceof InvalidObjectException) {
               throw (InvalidObjectException)e;
            }

            if (e instanceof AlreadyExistsException) {
               throw (AlreadyExistsException)e;
            }

            if (e instanceof MetaException) {
               throw (MetaException)e;
            }

            if (e instanceof TException) {
               throw (TException)e;
            }

            throw newMetaException(e);
         } finally {
            String tableName = indexTable != null ? indexTable.getTableName() : null;
            this.endFunction("add_index", ret != null, ex, tableName);
         }

         return ret;
      }

      private Index add_index_core(RawStore ms, Index index, Table indexTable) throws InvalidObjectException, AlreadyExistsException, MetaException {
         boolean success = false;
         boolean indexTableCreated = false;
         String[] qualified = MetaStoreUtils.getQualifiedName(index.getDbName(), index.getIndexTableName());
         Map<String, String> transactionalListenerResponses = Collections.emptyMap();

         Index var13;
         try {
            ms.openTransaction();
            this.firePreEvent(new PreAddIndexEvent(index, this));
            Index old_index = null;

            try {
               old_index = this.get_index_by_name(index.getDbName(), index.getOrigTableName(), index.getIndexName());
            } catch (Exception var24) {
            }

            if (old_index != null) {
               throw new AlreadyExistsException("Index already exists:" + index);
            }

            Table origTbl = ms.getTable(index.getDbName(), index.getOrigTableName());
            if (origTbl == null) {
               throw new InvalidObjectException("Unable to add index because database or the orginal table do not exist");
            }

            long time = System.currentTimeMillis() / 1000L;
            Table indexTbl = indexTable;
            if (indexTable != null) {
               try {
                  indexTbl = ms.getTable(qualified[0], qualified[1]);
               } catch (Exception var23) {
               }

               if (indexTbl != null) {
                  throw new InvalidObjectException("Unable to add index because index table already exists");
               }

               this.create_table(indexTable);
               indexTableCreated = true;
            }

            index.setCreateTime((int)time);
            index.putToParameters("transient_lastDdlTime", Long.toString(time));
            if (ms.addIndex(index) && !this.transactionalListeners.isEmpty()) {
               transactionalListenerResponses = MetaStoreListenerNotifier.notifyEvent(this.transactionalListeners, EventMessage.EventType.CREATE_INDEX, new AddIndexEvent(index, true, this));
            }

            success = ms.commitTransaction();
            var13 = index;
         } finally {
            if (!success) {
               if (indexTableCreated) {
                  try {
                     this.drop_table(qualified[0], qualified[1], false);
                  } catch (Exception var22) {
                  }
               }

               ms.rollbackTransaction();
            }

            if (!this.listeners.isEmpty()) {
               MetaStoreListenerNotifier.notifyEvent(this.listeners, EventMessage.EventType.CREATE_INDEX, new AddIndexEvent(index, success, this), (EnvironmentContext)null, transactionalListenerResponses, ms);
            }

         }

         return var13;
      }

      public boolean drop_index_by_name(String dbName, String tblName, String indexName, boolean deleteData) throws NoSuchObjectException, MetaException, TException {
         this.startFunction("drop_index_by_name", ": db=" + dbName + " tbl=" + tblName + " index=" + indexName);
         boolean ret = false;
         Exception ex = null;

         try {
            ret = this.drop_index_by_name_core(this.getMS(), dbName, tblName, indexName, deleteData);
         } catch (IOException e) {
            ex = e;
            throw new MetaException(e.getMessage());
         } catch (Exception e) {
            ex = e;
            this.rethrowException(e);
         } finally {
            this.endFunction("drop_index_by_name", ret, ex, tblName);
         }

         return ret;
      }

      private boolean drop_index_by_name_core(RawStore ms, String dbName, String tblName, String indexName, boolean deleteData) throws NoSuchObjectException, MetaException, TException, IOException, InvalidObjectException, InvalidInputException {
         boolean success = false;
         Index index = null;
         Path tblPath = null;
         List<Path> partPaths = null;
         Map<String, String> transactionalListenerResponses = Collections.emptyMap();

         try {
            ms.openTransaction();
            index = this.get_index_by_name(dbName, tblName, indexName);
            this.firePreEvent(new PreDropIndexEvent(index, this));
            ms.dropIndex(dbName, tblName, indexName);
            String idxTblName = index.getIndexTableName();
            if (idxTblName != null) {
               String[] qualified = MetaStoreUtils.getQualifiedName(index.getDbName(), idxTblName);
               Table tbl = this.get_table_core(qualified[0], qualified[1]);
               if (tbl.getSd() == null) {
                  throw new MetaException("Table metadata is corrupted");
               }

               if (tbl.getSd().getLocation() != null) {
                  tblPath = new Path(tbl.getSd().getLocation());
                  if (!this.wh.isWritable(tblPath.getParent())) {
                     throw new MetaException("Index table metadata not deleted since " + tblPath.getParent() + " is not writable by " + this.hiveConf.getUser());
                  }
               }

               partPaths = this.dropPartitionsAndGetLocations(ms, qualified[0], qualified[1], tblPath, tbl.getPartitionKeys(), deleteData);
               if (!ms.dropTable(qualified[0], qualified[1])) {
                  throw new MetaException("Unable to drop underlying data table " + qualified[0] + "." + qualified[1] + " for index " + indexName);
               }
            }

            if (!this.transactionalListeners.isEmpty()) {
               transactionalListenerResponses = MetaStoreListenerNotifier.notifyEvent(this.transactionalListeners, EventMessage.EventType.DROP_INDEX, new DropIndexEvent(index, true, this));
            }

            success = ms.commitTransaction();
         } finally {
            if (!success) {
               ms.rollbackTransaction();
            } else if (deleteData && tblPath != null) {
               this.deletePartitionData(partPaths);
               this.deleteTableData(tblPath);
            }

            if (index != null && !this.listeners.isEmpty()) {
               MetaStoreListenerNotifier.notifyEvent(this.listeners, EventMessage.EventType.DROP_INDEX, new DropIndexEvent(index, success, this), (EnvironmentContext)null, transactionalListenerResponses, ms);
            }

         }

         return success;
      }

      public Index get_index_by_name(String dbName, String tblName, String indexName) throws MetaException, NoSuchObjectException, TException {
         this.startFunction("get_index_by_name", ": db=" + dbName + " tbl=" + tblName + " index=" + indexName);
         Index ret = null;
         Exception ex = null;

         try {
            ret = this.get_index_by_name_core(this.getMS(), dbName, tblName, indexName);
         } catch (Exception e) {
            ex = e;
            this.rethrowException(e);
         } finally {
            this.endFunction("get_index_by_name", ret != null, ex, tblName);
         }

         return ret;
      }

      private Index get_index_by_name_core(RawStore ms, String db_name, String tbl_name, String index_name) throws MetaException, NoSuchObjectException, TException {
         Index index = ms.getIndex(db_name, tbl_name, index_name);
         if (index == null) {
            throw new NoSuchObjectException(db_name + "." + tbl_name + " index=" + index_name + " not found");
         } else {
            return index;
         }
      }

      public List get_index_names(String dbName, String tblName, short maxIndexes) throws MetaException, TException {
         this.startTableFunction("get_index_names", dbName, tblName);
         List<String> ret = null;
         Exception ex = null;

         try {
            ret = this.getMS().listIndexNames(dbName, tblName, maxIndexes);
         } catch (Exception e) {
            ex = e;
            if (e instanceof MetaException) {
               throw (MetaException)e;
            }

            if (e instanceof TException) {
               throw (TException)e;
            }

            throw newMetaException(e);
         } finally {
            this.endFunction("get_index_names", ret != null, ex, tblName);
         }

         return ret;
      }

      public List get_indexes(String dbName, String tblName, short maxIndexes) throws NoSuchObjectException, MetaException, TException {
         this.startTableFunction("get_indexes", dbName, tblName);
         List<Index> ret = null;
         Exception ex = null;

         try {
            ret = this.getMS().getIndexes(dbName, tblName, maxIndexes);
         } catch (Exception e) {
            ex = e;
            this.rethrowException(e);
         } finally {
            this.endFunction("get_indexes", ret != null, ex, tblName);
         }

         return ret;
      }

      private String lowerCaseConvertPartName(String partName) throws MetaException {
         boolean isFirst = true;
         Map<String, String> partSpec = Warehouse.makeEscSpecFromName(partName);
         String convertedPartName = new String();

         for(Map.Entry entry : partSpec.entrySet()) {
            String partColName = (String)entry.getKey();
            String partColVal = (String)entry.getValue();
            if (!isFirst) {
               convertedPartName = convertedPartName + "/";
            } else {
               isFirst = false;
            }

            convertedPartName = convertedPartName + partColName.toLowerCase() + "=" + partColVal;
         }

         return convertedPartName;
      }

      public ColumnStatistics get_table_column_statistics(String dbName, String tableName, String colName) throws NoSuchObjectException, MetaException, TException, InvalidInputException, InvalidObjectException {
         dbName = dbName.toLowerCase();
         tableName = tableName.toLowerCase();
         colName = colName.toLowerCase();
         this.startFunction("get_column_statistics_by_table", ": db=" + dbName + " table=" + tableName + " column=" + colName);
         ColumnStatistics statsObj = null;

         ColumnStatistics var5;
         try {
            statsObj = this.getMS().getTableColumnStatistics(dbName, tableName, Lists.newArrayList(new String[]{colName}));

            assert statsObj == null || statsObj.getStatsObjSize() <= 1;

            var5 = statsObj;
         } finally {
            this.endFunction("get_column_statistics_by_table", statsObj != null, (Exception)null, tableName);
         }

         return var5;
      }

      public TableStatsResult get_table_statistics_req(TableStatsRequest request) throws MetaException, NoSuchObjectException, TException {
         String dbName = request.getDbName().toLowerCase();
         String tblName = request.getTblName().toLowerCase();
         this.startFunction("get_table_statistics_req", ": db=" + dbName + " table=" + tblName);
         TableStatsResult result = null;
         List<String> lowerCaseColNames = new ArrayList(request.getColNames().size());

         for(String colName : request.getColNames()) {
            lowerCaseColNames.add(colName.toLowerCase());
         }

         try {
            ColumnStatistics cs = this.getMS().getTableColumnStatistics(dbName, tblName, lowerCaseColNames);
            result = new TableStatsResult((List)(cs != null && cs.getStatsObj() != null ? cs.getStatsObj() : Lists.newArrayList()));
         } finally {
            this.endFunction("get_table_statistics_req", result == null, (Exception)null, tblName);
         }

         return result;
      }

      public ColumnStatistics get_partition_column_statistics(String dbName, String tableName, String partName, String colName) throws NoSuchObjectException, MetaException, InvalidInputException, TException, InvalidObjectException {
         dbName = dbName.toLowerCase();
         tableName = tableName.toLowerCase();
         colName = colName.toLowerCase();
         String convertedPartName = this.lowerCaseConvertPartName(partName);
         this.startFunction("get_column_statistics_by_partition", ": db=" + dbName + " table=" + tableName + " partition=" + convertedPartName + " column=" + colName);
         ColumnStatistics statsObj = null;

         Object var8;
         try {
            List<ColumnStatistics> list = this.getMS().getPartitionColumnStatistics(dbName, tableName, Lists.newArrayList(new String[]{convertedPartName}), Lists.newArrayList(new String[]{colName}));
            if (!list.isEmpty()) {
               if (list.size() != 1) {
                  throw new MetaException(list.size() + " statistics for single column and partition");
               }

               statsObj = (ColumnStatistics)list.get(0);
               return statsObj;
            }

            var8 = null;
         } finally {
            this.endFunction("get_column_statistics_by_partition", statsObj != null, (Exception)null, tableName);
         }

         return (ColumnStatistics)var8;
      }

      public PartitionsStatsResult get_partitions_statistics_req(PartitionsStatsRequest request) throws MetaException, NoSuchObjectException, TException {
         String dbName = request.getDbName().toLowerCase();
         String tblName = request.getTblName().toLowerCase();
         this.startFunction("get_partitions_statistics_req", ": db=" + dbName + " table=" + tblName);
         PartitionsStatsResult result = null;
         List<String> lowerCaseColNames = new ArrayList(request.getColNames().size());

         for(String colName : request.getColNames()) {
            lowerCaseColNames.add(colName.toLowerCase());
         }

         List<String> lowerCasePartNames = new ArrayList(request.getPartNames().size());

         for(String partName : request.getPartNames()) {
            lowerCasePartNames.add(this.lowerCaseConvertPartName(partName));
         }

         try {
            List<ColumnStatistics> stats = this.getMS().getPartitionColumnStatistics(dbName, tblName, lowerCasePartNames, lowerCaseColNames);
            Map<String, List<ColumnStatisticsObj>> map = new HashMap();

            for(ColumnStatistics stat : stats) {
               map.put(stat.getStatsDesc().getPartName(), stat.getStatsObj());
            }

            result = new PartitionsStatsResult(map);
         } finally {
            this.endFunction("get_partitions_statistics_req", result == null, (Exception)null, tblName);
         }

         return result;
      }

      public boolean update_table_column_statistics(ColumnStatistics colStats) throws NoSuchObjectException, InvalidObjectException, MetaException, TException, InvalidInputException {
         String dbName = null;
         String tableName = null;
         String colName = null;
         ColumnStatisticsDesc statsDesc = colStats.getStatsDesc();
         dbName = statsDesc.getDbName().toLowerCase();
         tableName = statsDesc.getTableName().toLowerCase();
         statsDesc.setDbName(dbName);
         statsDesc.setTableName(tableName);
         long time = System.currentTimeMillis() / 1000L;
         statsDesc.setLastAnalyzed(time);
         List<ColumnStatisticsObj> statsObjs = colStats.getStatsObj();
         this.startFunction("write_column_statistics", ":  db=" + dbName + " table=" + tableName);

         for(ColumnStatisticsObj statsObj : statsObjs) {
            colName = statsObj.getColName().toLowerCase();
            statsObj.setColName(colName);
            statsObj.setColType(statsObj.getColType().toLowerCase());
         }

         colStats.setStatsDesc(statsDesc);
         colStats.setStatsObj(statsObjs);
         boolean ret = false;

         boolean var18;
         try {
            ret = this.getMS().updateTableColumnStatistics(colStats);
            var18 = ret;
         } finally {
            this.endFunction("write_column_statistics", ret, (Exception)null, tableName);
         }

         return var18;
      }

      private boolean updatePartitonColStats(Table tbl, ColumnStatistics colStats) throws MetaException, InvalidObjectException, NoSuchObjectException, InvalidInputException {
         String dbName = null;
         String tableName = null;
         String partName = null;
         String colName = null;
         ColumnStatisticsDesc statsDesc = colStats.getStatsDesc();
         dbName = statsDesc.getDbName().toLowerCase();
         tableName = statsDesc.getTableName().toLowerCase();
         partName = this.lowerCaseConvertPartName(statsDesc.getPartName());
         statsDesc.setDbName(dbName);
         statsDesc.setTableName(tableName);
         statsDesc.setPartName(partName);
         long time = System.currentTimeMillis() / 1000L;
         statsDesc.setLastAnalyzed(time);
         List<ColumnStatisticsObj> statsObjs = colStats.getStatsObj();
         this.startFunction("write_partition_column_statistics", ":  db=" + dbName + " table=" + tableName + " part=" + partName);

         for(ColumnStatisticsObj statsObj : statsObjs) {
            colName = statsObj.getColName().toLowerCase();
            statsObj.setColName(colName);
            statsObj.setColType(statsObj.getColType().toLowerCase());
         }

         colStats.setStatsDesc(statsDesc);
         colStats.setStatsObj(statsObjs);
         boolean ret = false;

         boolean var13;
         try {
            if (tbl == null) {
               tbl = this.getTable(dbName, tableName);
            }

            List<String> partVals = this.getPartValsFromName(tbl, partName);
            ret = this.getMS().updatePartitionColumnStatistics(colStats, partVals);
            var13 = ret;
         } finally {
            this.endFunction("write_partition_column_statistics", ret, (Exception)null, tableName);
         }

         return var13;
      }

      public boolean update_partition_column_statistics(ColumnStatistics colStats) throws NoSuchObjectException, InvalidObjectException, MetaException, TException, InvalidInputException {
         return this.updatePartitonColStats((Table)null, colStats);
      }

      public boolean delete_partition_column_statistics(String dbName, String tableName, String partName, String colName) throws NoSuchObjectException, MetaException, InvalidObjectException, TException, InvalidInputException {
         dbName = dbName.toLowerCase();
         tableName = tableName.toLowerCase();
         if (colName != null) {
            colName = colName.toLowerCase();
         }

         String convertedPartName = this.lowerCaseConvertPartName(partName);
         this.startFunction("delete_column_statistics_by_partition", ": db=" + dbName + " table=" + tableName + " partition=" + convertedPartName + " column=" + colName);
         boolean ret = false;

         try {
            List<String> partVals = this.getPartValsFromName(this.getMS(), dbName, tableName, convertedPartName);
            ret = this.getMS().deletePartitionColumnStatistics(dbName, tableName, convertedPartName, partVals, colName);
         } finally {
            this.endFunction("delete_column_statistics_by_partition", ret, (Exception)null, tableName);
         }

         return ret;
      }

      public boolean delete_table_column_statistics(String dbName, String tableName, String colName) throws NoSuchObjectException, MetaException, InvalidObjectException, TException, InvalidInputException {
         dbName = dbName.toLowerCase();
         tableName = tableName.toLowerCase();
         if (colName != null) {
            colName = colName.toLowerCase();
         }

         this.startFunction("delete_column_statistics_by_table", ": db=" + dbName + " table=" + tableName + " column=" + colName);
         boolean ret = false;

         try {
            ret = this.getMS().deleteTableColumnStatistics(dbName, tableName, colName);
         } finally {
            this.endFunction("delete_column_statistics_by_table", ret, (Exception)null, tableName);
         }

         return ret;
      }

      public List get_partitions_by_filter(String dbName, String tblName, String filter, short maxParts) throws MetaException, NoSuchObjectException, TException {
         this.startTableFunction("get_partitions_by_filter", dbName, tblName);
         this.fireReadTablePreEvent(dbName, tblName);
         List<Partition> ret = null;
         Exception ex = null;

         try {
            this.checkLimitNumberOfPartitionsByFilter(dbName, tblName, filter, maxParts);
            ret = this.getMS().getPartitionsByFilter(dbName, tblName, filter, maxParts);
         } catch (Exception e) {
            ex = e;
            this.rethrowException(e);
         } finally {
            this.endFunction("get_partitions_by_filter", ret != null, ex, tblName);
         }

         return ret;
      }

      public List get_part_specs_by_filter(String dbName, String tblName, String filter, int maxParts) throws MetaException, NoSuchObjectException, TException {
         this.startTableFunction("get_partitions_by_filter_pspec", dbName, tblName);
         List<PartitionSpec> partitionSpecs = null;

         List var12;
         try {
            Table table = this.get_table_core(dbName, tblName);
            List<Partition> partitions = this.get_partitions_by_filter(dbName, tblName, filter, (short)maxParts);
            if (is_partition_spec_grouping_enabled(table)) {
               partitionSpecs = this.get_partitionspecs_grouped_by_storage_descriptor(table, partitions);
            } else {
               PartitionSpec pSpec = new PartitionSpec();
               pSpec.setPartitionList(new PartitionListComposingSpec(partitions));
               pSpec.setRootPath(table.getSd().getLocation());
               pSpec.setDbName(dbName);
               pSpec.setTableName(tblName);
               partitionSpecs = Arrays.asList(pSpec);
            }

            var12 = partitionSpecs;
         } finally {
            this.endFunction("get_partitions_by_filter_pspec", partitionSpecs != null && !partitionSpecs.isEmpty(), (Exception)null, tblName);
         }

         return var12;
      }

      public PartitionsByExprResult get_partitions_by_expr(PartitionsByExprRequest req) throws TException {
         String dbName = req.getDbName();
         String tblName = req.getTblName();
         this.startTableFunction("get_partitions_by_expr", dbName, tblName);
         this.fireReadTablePreEvent(dbName, tblName);
         PartitionsByExprResult ret = null;
         Exception ex = null;

         try {
            this.checkLimitNumberOfPartitionsByExpr(dbName, tblName, req.getExpr(), -1);
            List<Partition> partitions = new LinkedList();
            boolean hasUnknownPartitions = this.getMS().getPartitionsByExpr(dbName, tblName, req.getExpr(), req.getDefaultPartitionName(), req.getMaxParts(), partitions);
            ret = new PartitionsByExprResult(partitions, hasUnknownPartitions);
         } catch (Exception e) {
            ex = e;
            this.rethrowException(e);
         } finally {
            this.endFunction("get_partitions_by_expr", ret != null, ex, tblName);
         }

         return ret;
      }

      private void rethrowException(Exception e) throws MetaException, NoSuchObjectException, TException {
         if (e instanceof MetaException) {
            throw (MetaException)e;
         } else if (e instanceof NoSuchObjectException) {
            throw (NoSuchObjectException)e;
         } else if (e instanceof TException) {
            throw (TException)e;
         } else {
            throw newMetaException(e);
         }
      }

      public int get_num_partitions_by_filter(String dbName, String tblName, String filter) throws TException {
         this.startTableFunction("get_num_partitions_by_filter", dbName, tblName);
         int ret = -1;
         Exception ex = null;

         try {
            ret = this.getMS().getNumPartitionsByFilter(dbName, tblName, filter);
         } catch (Exception e) {
            ex = e;
            this.rethrowException(e);
         } finally {
            this.endFunction("get_num_partitions_by_filter", ret != -1, ex, tblName);
         }

         return ret;
      }

      public int get_num_partitions_by_expr(String dbName, String tblName, byte[] expr) throws TException {
         this.startTableFunction("get_num_partitions_by_expr", dbName, tblName);
         int ret = -1;
         Exception ex = null;

         try {
            ret = this.getMS().getNumPartitionsByExpr(dbName, tblName, expr);
         } catch (Exception e) {
            ex = e;
            this.rethrowException(e);
         } finally {
            this.endFunction("get_num_partitions_by_expr", ret != -1, ex, tblName);
         }

         return ret;
      }

      public List get_partitions_by_names(String dbName, String tblName, List partNames) throws MetaException, NoSuchObjectException, TException {
         this.startTableFunction("get_partitions_by_names", dbName, tblName);
         this.fireReadTablePreEvent(dbName, tblName);
         List<Partition> ret = null;
         Exception ex = null;

         try {
            ret = this.getMS().getPartitionsByNames(dbName, tblName, partNames);
         } catch (Exception e) {
            ex = e;
            this.rethrowException(e);
         } finally {
            this.endFunction("get_partitions_by_names", ret != null, ex, tblName);
         }

         return ret;
      }

      public PrincipalPrivilegeSet get_privilege_set(HiveObjectRef hiveObject, String userName, List groupNames) throws MetaException, TException {
         this.firePreEvent(new PreAuthorizationCallEvent(this));
         if (hiveObject.getObjectType() == HiveObjectType.COLUMN) {
            String partName = this.getPartName(hiveObject);
            return this.get_column_privilege_set(hiveObject.getDbName(), hiveObject.getObjectName(), partName, hiveObject.getColumnName(), userName, groupNames);
         } else if (hiveObject.getObjectType() == HiveObjectType.PARTITION) {
            String partName = this.getPartName(hiveObject);
            return this.get_partition_privilege_set(hiveObject.getDbName(), hiveObject.getObjectName(), partName, userName, groupNames);
         } else if (hiveObject.getObjectType() == HiveObjectType.DATABASE) {
            return this.get_db_privilege_set(hiveObject.getDbName(), userName, groupNames);
         } else if (hiveObject.getObjectType() == HiveObjectType.TABLE) {
            return this.get_table_privilege_set(hiveObject.getDbName(), hiveObject.getObjectName(), userName, groupNames);
         } else {
            return hiveObject.getObjectType() == HiveObjectType.GLOBAL ? this.get_user_privilege_set(userName, groupNames) : null;
         }
      }

      private String getPartName(HiveObjectRef hiveObject) throws MetaException {
         String partName = null;
         List<String> partValue = hiveObject.getPartValues();
         if (partValue != null && partValue.size() > 0) {
            try {
               Table table = this.get_table_core(hiveObject.getDbName(), hiveObject.getObjectName());
               partName = Warehouse.makePartName(table.getPartitionKeys(), partValue);
            } catch (NoSuchObjectException e) {
               throw new MetaException(e.getMessage());
            }
         }

         return partName;
      }

      private PrincipalPrivilegeSet get_column_privilege_set(String dbName, String tableName, String partName, String columnName, String userName, List groupNames) throws MetaException, TException {
         this.incrementCounter("get_column_privilege_set");
         PrincipalPrivilegeSet ret = null;

         try {
            ret = this.getMS().getColumnPrivilegeSet(dbName, tableName, partName, columnName, userName, groupNames);
            return ret;
         } catch (MetaException e) {
            throw e;
         } catch (Exception e) {
            throw new RuntimeException(e);
         }
      }

      private PrincipalPrivilegeSet get_db_privilege_set(String dbName, String userName, List groupNames) throws MetaException, TException {
         this.incrementCounter("get_db_privilege_set");
         PrincipalPrivilegeSet ret = null;

         try {
            ret = this.getMS().getDBPrivilegeSet(dbName, userName, groupNames);
            return ret;
         } catch (MetaException e) {
            throw e;
         } catch (Exception e) {
            throw new RuntimeException(e);
         }
      }

      private PrincipalPrivilegeSet get_partition_privilege_set(String dbName, String tableName, String partName, String userName, List groupNames) throws MetaException, TException {
         this.incrementCounter("get_partition_privilege_set");
         PrincipalPrivilegeSet ret = null;

         try {
            ret = this.getMS().getPartitionPrivilegeSet(dbName, tableName, partName, userName, groupNames);
            return ret;
         } catch (MetaException e) {
            throw e;
         } catch (Exception e) {
            throw new RuntimeException(e);
         }
      }

      private PrincipalPrivilegeSet get_table_privilege_set(String dbName, String tableName, String userName, List groupNames) throws MetaException, TException {
         this.incrementCounter("get_table_privilege_set");
         PrincipalPrivilegeSet ret = null;

         try {
            ret = this.getMS().getTablePrivilegeSet(dbName, tableName, userName, groupNames);
            return ret;
         } catch (MetaException e) {
            throw e;
         } catch (Exception e) {
            throw new RuntimeException(e);
         }
      }

      public boolean grant_role(String roleName, String principalName, PrincipalType principalType, String grantor, PrincipalType grantorType, boolean grantOption) throws MetaException, TException {
         this.incrementCounter("add_role_member");
         this.firePreEvent(new PreAuthorizationCallEvent(this));
         if ("public".equals(roleName)) {
            throw new MetaException("No user can be added to public. Since all users implictly belong to public role.");
         } else {
            Boolean ret = null;

            try {
               RawStore ms = this.getMS();
               Role role = ms.getRole(roleName);
               if (principalType == PrincipalType.ROLE && this.isNewRoleAParent(principalName, roleName)) {
                  throw new MetaException("Cannot grant role " + principalName + " to " + roleName + " as " + roleName + " already belongs to the role " + principalName + ". (no cycles allowed)");
               }

               ret = ms.grantRole(role, principalName, principalType, grantor, grantorType, grantOption);
            } catch (MetaException e) {
               throw e;
            } catch (Exception e) {
               throw new RuntimeException(e);
            }

            return ret;
         }
      }

      private boolean isNewRoleAParent(String newRole, String curRole) throws MetaException {
         if (newRole.equals(curRole)) {
            return true;
         } else {
            for(Role parentRole : this.getMS().listRoles(curRole, PrincipalType.ROLE)) {
               if (this.isNewRoleAParent(newRole, parentRole.getRoleName())) {
                  return true;
               }
            }

            return false;
         }
      }

      public List list_roles(String principalName, PrincipalType principalType) throws MetaException, TException {
         this.incrementCounter("list_roles");
         this.firePreEvent(new PreAuthorizationCallEvent(this));
         return this.getMS().listRoles(principalName, principalType);
      }

      public boolean create_role(Role role) throws MetaException, TException {
         this.incrementCounter("create_role");
         this.firePreEvent(new PreAuthorizationCallEvent(this));
         if ("public".equals(role.getRoleName())) {
            throw new MetaException("public role implictly exists. It can't be created.");
         } else {
            Boolean ret = null;

            try {
               ret = this.getMS().addRole(role.getRoleName(), role.getOwnerName());
            } catch (MetaException e) {
               throw e;
            } catch (Exception e) {
               throw new RuntimeException(e);
            }

            return ret;
         }
      }

      public boolean drop_role(String roleName) throws MetaException, TException {
         this.incrementCounter("drop_role");
         this.firePreEvent(new PreAuthorizationCallEvent(this));
         if (!"admin".equals(roleName) && !"public".equals(roleName)) {
            Boolean ret = null;

            try {
               ret = this.getMS().removeRole(roleName);
            } catch (MetaException e) {
               throw e;
            } catch (Exception e) {
               throw new RuntimeException(e);
            }

            return ret;
         } else {
            throw new MetaException("public,admin roles can't be dropped.");
         }
      }

      public List get_role_names() throws MetaException, TException {
         this.incrementCounter("get_role_names");
         this.firePreEvent(new PreAuthorizationCallEvent(this));
         List<String> ret = null;

         try {
            ret = this.getMS().listRoleNames();
            return ret;
         } catch (MetaException e) {
            throw e;
         } catch (Exception e) {
            throw new RuntimeException(e);
         }
      }

      public boolean grant_privileges(PrivilegeBag privileges) throws MetaException, TException {
         this.incrementCounter("grant_privileges");
         this.firePreEvent(new PreAuthorizationCallEvent(this));
         Boolean ret = null;

         try {
            ret = this.getMS().grantPrivileges(privileges);
         } catch (MetaException e) {
            throw e;
         } catch (Exception e) {
            throw new RuntimeException(e);
         }

         return ret;
      }

      public boolean revoke_role(String roleName, String userName, PrincipalType principalType) throws MetaException, TException {
         return this.revoke_role(roleName, userName, principalType, false);
      }

      private boolean revoke_role(String roleName, String userName, PrincipalType principalType, boolean grantOption) throws MetaException, TException {
         this.incrementCounter("remove_role_member");
         this.firePreEvent(new PreAuthorizationCallEvent(this));
         if ("public".equals(roleName)) {
            throw new MetaException("public role can't be revoked.");
         } else {
            Boolean ret = null;

            try {
               RawStore ms = this.getMS();
               Role mRole = ms.getRole(roleName);
               ret = ms.revokeRole(mRole, userName, principalType, grantOption);
            } catch (MetaException e) {
               throw e;
            } catch (Exception e) {
               throw new RuntimeException(e);
            }

            return ret;
         }
      }

      public GrantRevokeRoleResponse grant_revoke_role(GrantRevokeRoleRequest request) throws MetaException, TException {
         GrantRevokeRoleResponse response = new GrantRevokeRoleResponse();
         boolean grantOption = false;
         if (request.isSetGrantOption()) {
            grantOption = request.isGrantOption();
         }

         switch (request.getRequestType()) {
            case GRANT:
               boolean result = this.grant_role(request.getRoleName(), request.getPrincipalName(), request.getPrincipalType(), request.getGrantor(), request.getGrantorType(), grantOption);
               response.setSuccess(result);
               break;
            case REVOKE:
               boolean result = this.revoke_role(request.getRoleName(), request.getPrincipalName(), request.getPrincipalType(), grantOption);
               response.setSuccess(result);
               break;
            default:
               throw new MetaException("Unknown request type " + request.getRequestType());
         }

         return response;
      }

      public GrantRevokePrivilegeResponse grant_revoke_privileges(GrantRevokePrivilegeRequest request) throws MetaException, TException {
         GrantRevokePrivilegeResponse response = new GrantRevokePrivilegeResponse();
         switch (request.getRequestType()) {
            case GRANT:
               boolean result = this.grant_privileges(request.getPrivileges());
               response.setSuccess(result);
               break;
            case REVOKE:
               boolean revokeGrantOption = false;
               if (request.isSetRevokeGrantOption()) {
                  revokeGrantOption = request.isRevokeGrantOption();
               }

               boolean result = this.revoke_privileges(request.getPrivileges(), revokeGrantOption);
               response.setSuccess(result);
               break;
            default:
               throw new MetaException("Unknown request type " + request.getRequestType());
         }

         return response;
      }

      public boolean revoke_privileges(PrivilegeBag privileges) throws MetaException, TException {
         return this.revoke_privileges(privileges, false);
      }

      public boolean revoke_privileges(PrivilegeBag privileges, boolean grantOption) throws MetaException, TException {
         this.incrementCounter("revoke_privileges");
         this.firePreEvent(new PreAuthorizationCallEvent(this));
         Boolean ret = null;

         try {
            ret = this.getMS().revokePrivileges(privileges, grantOption);
         } catch (MetaException e) {
            throw e;
         } catch (Exception e) {
            throw new RuntimeException(e);
         }

         return ret;
      }

      private PrincipalPrivilegeSet get_user_privilege_set(String userName, List groupNames) throws MetaException, TException {
         this.incrementCounter("get_user_privilege_set");
         PrincipalPrivilegeSet ret = null;

         try {
            ret = this.getMS().getUserPrivilegeSet(userName, groupNames);
            return ret;
         } catch (MetaException e) {
            throw e;
         } catch (Exception e) {
            throw new RuntimeException(e);
         }
      }

      public List list_privileges(String principalName, PrincipalType principalType, HiveObjectRef hiveObject) throws MetaException, TException {
         this.firePreEvent(new PreAuthorizationCallEvent(this));
         if (hiveObject.getObjectType() == null) {
            return this.getAllPrivileges(principalName, principalType);
         } else if (hiveObject.getObjectType() == HiveObjectType.GLOBAL) {
            return this.list_global_privileges(principalName, principalType);
         } else if (hiveObject.getObjectType() == HiveObjectType.DATABASE) {
            return this.list_db_privileges(principalName, principalType, hiveObject.getDbName());
         } else if (hiveObject.getObjectType() == HiveObjectType.TABLE) {
            return this.list_table_privileges(principalName, principalType, hiveObject.getDbName(), hiveObject.getObjectName());
         } else if (hiveObject.getObjectType() == HiveObjectType.PARTITION) {
            return this.list_partition_privileges(principalName, principalType, hiveObject.getDbName(), hiveObject.getObjectName(), hiveObject.getPartValues());
         } else if (hiveObject.getObjectType() == HiveObjectType.COLUMN) {
            return hiveObject.getPartValues() != null && !hiveObject.getPartValues().isEmpty() ? this.list_partition_column_privileges(principalName, principalType, hiveObject.getDbName(), hiveObject.getObjectName(), hiveObject.getPartValues(), hiveObject.getColumnName()) : this.list_table_column_privileges(principalName, principalType, hiveObject.getDbName(), hiveObject.getObjectName(), hiveObject.getColumnName());
         } else {
            return null;
         }
      }

      private List getAllPrivileges(String principalName, PrincipalType principalType) throws TException {
         List<HiveObjectPrivilege> privs = new ArrayList();
         privs.addAll(this.list_global_privileges(principalName, principalType));
         privs.addAll(this.list_db_privileges(principalName, principalType, (String)null));
         privs.addAll(this.list_table_privileges(principalName, principalType, (String)null, (String)null));
         privs.addAll(this.list_partition_privileges(principalName, principalType, (String)null, (String)null, (List)null));
         privs.addAll(this.list_table_column_privileges(principalName, principalType, (String)null, (String)null, (String)null));
         privs.addAll(this.list_partition_column_privileges(principalName, principalType, (String)null, (String)null, (List)null, (String)null));
         return privs;
      }

      private List list_table_column_privileges(String principalName, PrincipalType principalType, String dbName, String tableName, String columnName) throws MetaException, TException {
         this.incrementCounter("list_table_column_privileges");

         try {
            if (dbName == null) {
               return this.getMS().listPrincipalTableColumnGrantsAll(principalName, principalType);
            } else if (principalName == null) {
               return this.getMS().listTableColumnGrantsAll(dbName, tableName, columnName);
            } else {
               List<HiveObjectPrivilege> result = this.getMS().listPrincipalTableColumnGrants(principalName, principalType, dbName, tableName, columnName);
               return result;
            }
         } catch (MetaException e) {
            throw e;
         } catch (Exception e) {
            throw new RuntimeException(e);
         }
      }

      private List list_partition_column_privileges(String principalName, PrincipalType principalType, String dbName, String tableName, List partValues, String columnName) throws MetaException, TException {
         this.incrementCounter("list_partition_column_privileges");

         try {
            if (dbName == null) {
               return this.getMS().listPrincipalPartitionColumnGrantsAll(principalName, principalType);
            } else {
               Table tbl = this.get_table_core(dbName, tableName);
               String partName = Warehouse.makePartName(tbl.getPartitionKeys(), partValues);
               if (principalName == null) {
                  return this.getMS().listPartitionColumnGrantsAll(dbName, tableName, partName, columnName);
               } else {
                  List<HiveObjectPrivilege> result = this.getMS().listPrincipalPartitionColumnGrants(principalName, principalType, dbName, tableName, partValues, partName, columnName);
                  return result;
               }
            }
         } catch (MetaException e) {
            throw e;
         } catch (Exception e) {
            throw new RuntimeException(e);
         }
      }

      private List list_db_privileges(String principalName, PrincipalType principalType, String dbName) throws MetaException, TException {
         this.incrementCounter("list_security_db_grant");

         try {
            if (dbName == null) {
               return this.getMS().listPrincipalDBGrantsAll(principalName, principalType);
            } else {
               return principalName == null ? this.getMS().listDBGrantsAll(dbName) : this.getMS().listPrincipalDBGrants(principalName, principalType, dbName);
            }
         } catch (MetaException e) {
            throw e;
         } catch (Exception e) {
            throw new RuntimeException(e);
         }
      }

      private List list_partition_privileges(String principalName, PrincipalType principalType, String dbName, String tableName, List partValues) throws MetaException, TException {
         this.incrementCounter("list_security_partition_grant");

         try {
            if (dbName == null) {
               return this.getMS().listPrincipalPartitionGrantsAll(principalName, principalType);
            } else {
               Table tbl = this.get_table_core(dbName, tableName);
               String partName = Warehouse.makePartName(tbl.getPartitionKeys(), partValues);
               if (principalName == null) {
                  return this.getMS().listPartitionGrantsAll(dbName, tableName, partName);
               } else {
                  List<HiveObjectPrivilege> result = this.getMS().listPrincipalPartitionGrants(principalName, principalType, dbName, tableName, partValues, partName);
                  return result;
               }
            }
         } catch (MetaException e) {
            throw e;
         } catch (Exception e) {
            throw new RuntimeException(e);
         }
      }

      private List list_table_privileges(String principalName, PrincipalType principalType, String dbName, String tableName) throws MetaException, TException {
         this.incrementCounter("list_security_table_grant");

         try {
            if (dbName == null) {
               return this.getMS().listPrincipalTableGrantsAll(principalName, principalType);
            } else if (principalName == null) {
               return this.getMS().listTableGrantsAll(dbName, tableName);
            } else {
               List<HiveObjectPrivilege> result = this.getMS().listAllTableGrants(principalName, principalType, dbName, tableName);
               return result;
            }
         } catch (MetaException e) {
            throw e;
         } catch (Exception e) {
            throw new RuntimeException(e);
         }
      }

      private List list_global_privileges(String principalName, PrincipalType principalType) throws MetaException, TException {
         this.incrementCounter("list_security_user_grant");

         try {
            if (principalName == null) {
               return this.getMS().listGlobalGrantsAll();
            } else {
               List<HiveObjectPrivilege> result = this.getMS().listPrincipalGlobalGrants(principalName, principalType);
               return result;
            }
         } catch (MetaException e) {
            throw e;
         } catch (Exception e) {
            throw new RuntimeException(e);
         }
      }

      public void cancel_delegation_token(String token_str_form) throws MetaException, TException {
         this.startFunction("cancel_delegation_token");
         boolean success = false;
         Exception ex = null;

         try {
            HiveMetaStore.cancelDelegationToken(token_str_form);
            success = true;
         } catch (IOException e) {
            ex = e;
            throw new MetaException(e.getMessage());
         } catch (Exception e) {
            ex = e;
            if (e instanceof MetaException) {
               throw (MetaException)e;
            }

            if (e instanceof TException) {
               throw (TException)e;
            }

            throw newMetaException(e);
         } finally {
            this.endFunction("cancel_delegation_token", success, ex);
         }

      }

      public long renew_delegation_token(String token_str_form) throws MetaException, TException {
         this.startFunction("renew_delegation_token");
         Long ret = null;
         Exception ex = null;

         try {
            ret = HiveMetaStore.renewDelegationToken(token_str_form);
         } catch (IOException e) {
            ex = e;
            throw new MetaException(e.getMessage());
         } catch (Exception e) {
            ex = e;
            if (e instanceof MetaException) {
               throw (MetaException)e;
            }

            if (e instanceof TException) {
               throw (TException)e;
            }

            throw newMetaException(e);
         } finally {
            this.endFunction("renew_delegation_token", ret != null, ex);
         }

         return ret;
      }

      public String get_delegation_token(String token_owner, String renewer_kerberos_principal_name) throws MetaException, TException {
         this.startFunction("get_delegation_token");
         String ret = null;
         Exception ex = null;

         try {
            ret = HiveMetaStore.getDelegationToken(token_owner, renewer_kerberos_principal_name, getIPAddress());
         } catch (IOException e) {
            ex = e;
            throw new MetaException(e.getMessage());
         } catch (InterruptedException e) {
            ex = e;
            throw new MetaException(e.getMessage());
         } catch (Exception e) {
            ex = e;
            if (e instanceof MetaException) {
               throw (MetaException)e;
            }

            if (e instanceof TException) {
               throw (TException)e;
            }

            throw newMetaException(e);
         } finally {
            this.endFunction("get_delegation_token", ret != null, ex);
         }

         return ret;
      }

      public boolean add_token(String token_identifier, String delegation_token) throws TException {
         this.startFunction("add_token", ": " + token_identifier);
         boolean ret = false;
         Exception ex = null;

         try {
            ret = this.getMS().addToken(token_identifier, delegation_token);
         } catch (Exception e) {
            ex = e;
            if (e instanceof MetaException) {
               throw (MetaException)e;
            }

            throw newMetaException(e);
         } finally {
            this.endFunction("add_token", ret, ex);
         }

         return ret;
      }

      public boolean remove_token(String token_identifier) throws TException {
         this.startFunction("remove_token", ": " + token_identifier);
         boolean ret = false;
         Exception ex = null;

         try {
            ret = this.getMS().removeToken(token_identifier);
         } catch (Exception e) {
            ex = e;
            if (e instanceof MetaException) {
               throw (MetaException)e;
            }

            throw newMetaException(e);
         } finally {
            this.endFunction("remove_token", ret, ex);
         }

         return ret;
      }

      public String get_token(String token_identifier) throws TException {
         this.startFunction("get_token for", ": " + token_identifier);
         String ret = null;
         Exception ex = null;

         try {
            ret = this.getMS().getToken(token_identifier);
         } catch (Exception e) {
            ex = e;
            if (e instanceof MetaException) {
               throw (MetaException)e;
            }

            throw newMetaException(e);
         } finally {
            this.endFunction("get_token", ret != null, ex);
         }

         return ret;
      }

      public List get_all_token_identifiers() throws TException {
         this.startFunction("get_all_token_identifiers.");
         List<String> ret = null;
         Exception ex = null;

         try {
            ret = this.getMS().getAllTokenIdentifiers();
         } catch (Exception e) {
            ex = e;
            if (e instanceof MetaException) {
               throw (MetaException)e;
            }

            throw newMetaException(e);
         } finally {
            this.endFunction("get_all_token_identifiers.", ex == null, ex);
         }

         return ret;
      }

      public int add_master_key(String key) throws MetaException, TException {
         this.startFunction("add_master_key.");
         int ret = -1;
         Exception ex = null;

         try {
            ret = this.getMS().addMasterKey(key);
         } catch (Exception e) {
            ex = e;
            if (e instanceof MetaException) {
               throw (MetaException)e;
            }

            throw newMetaException(e);
         } finally {
            this.endFunction("add_master_key.", ex == null, ex);
         }

         return ret;
      }

      public void update_master_key(int seq_number, String key) throws NoSuchObjectException, MetaException, TException {
         this.startFunction("update_master_key.");
         Exception ex = null;

         try {
            this.getMS().updateMasterKey(seq_number, key);
         } catch (Exception e) {
            ex = e;
            if (e instanceof MetaException) {
               throw (MetaException)e;
            }

            throw newMetaException(e);
         } finally {
            this.endFunction("update_master_key.", ex == null, ex);
         }

      }

      public boolean remove_master_key(int key_seq) throws TException {
         this.startFunction("remove_master_key.");
         Exception ex = null;

         boolean ret;
         try {
            ret = this.getMS().removeMasterKey(key_seq);
         } catch (Exception e) {
            ex = e;
            if (e instanceof MetaException) {
               throw (MetaException)e;
            }

            throw newMetaException(e);
         } finally {
            this.endFunction("remove_master_key.", ex == null, ex);
         }

         return ret;
      }

      public List get_master_keys() throws TException {
         this.startFunction("get_master_keys.");
         Exception ex = null;
         String[] ret = null;

         try {
            ret = this.getMS().getMasterKeys();
         } catch (Exception e) {
            ex = e;
            if (e instanceof MetaException) {
               throw (MetaException)e;
            }

            throw newMetaException(e);
         } finally {
            this.endFunction("get_master_keys.", ret != null, ex);
         }

         return Arrays.asList(ret);
      }

      public void markPartitionForEvent(String db_name, String tbl_name, Map partName, PartitionEventType evtType) throws MetaException, TException, NoSuchObjectException, UnknownDBException, UnknownTableException, InvalidPartitionException, UnknownPartitionException {
         Table tbl = null;
         Exception ex = null;
         RawStore ms = this.getMS();
         boolean success = false;

         try {
            ms.openTransaction();
            this.startPartitionFunction("markPartitionForEvent", db_name, tbl_name, partName);
            this.firePreEvent(new PreLoadPartitionDoneEvent(db_name, tbl_name, partName, this));
            tbl = ms.markPartitionForEvent(db_name, tbl_name, partName, evtType);
            if (null == tbl) {
               throw new UnknownTableException("Table: " + tbl_name + " not found.");
            }

            if (this.transactionalListeners.size() > 0) {
               LoadPartitionDoneEvent lpde = new LoadPartitionDoneEvent(true, tbl, partName, this);

               for(MetaStoreEventListener transactionalListener : this.transactionalListeners) {
                  transactionalListener.onLoadPartitionDone(lpde);
               }
            }

            success = ms.commitTransaction();

            for(MetaStoreEventListener listener : this.listeners) {
               listener.onLoadPartitionDone(new LoadPartitionDoneEvent(true, tbl, partName, this));
            }
         } catch (Exception original) {
            ex = original;
            LOG.error("Exception caught in mark partition event ", original);
            if (original instanceof NoSuchObjectException) {
               throw (NoSuchObjectException)original;
            }

            if (original instanceof UnknownTableException) {
               throw (UnknownTableException)original;
            }

            if (original instanceof UnknownDBException) {
               throw (UnknownDBException)original;
            }

            if (original instanceof UnknownPartitionException) {
               throw (UnknownPartitionException)original;
            }

            if (original instanceof InvalidPartitionException) {
               throw (InvalidPartitionException)original;
            }

            if (original instanceof MetaException) {
               throw (MetaException)original;
            }

            throw newMetaException(original);
         } finally {
            if (!success) {
               ms.rollbackTransaction();
            }

            this.endFunction("markPartitionForEvent", tbl != null, ex, tbl_name);
         }

      }

      public boolean isPartitionMarkedForEvent(String db_name, String tbl_name, Map partName, PartitionEventType evtType) throws MetaException, NoSuchObjectException, UnknownDBException, UnknownTableException, TException, UnknownPartitionException, InvalidPartitionException {
         this.startPartitionFunction("isPartitionMarkedForEvent", db_name, tbl_name, partName);
         Boolean ret = null;
         Exception ex = null;

         try {
            ret = this.getMS().isPartitionMarkedForEvent(db_name, tbl_name, partName, evtType);
         } catch (Exception original) {
            LOG.error("Exception caught for isPartitionMarkedForEvent ", original);
            ex = original;
            if (original instanceof NoSuchObjectException) {
               throw (NoSuchObjectException)original;
            }

            if (original instanceof UnknownTableException) {
               throw (UnknownTableException)original;
            }

            if (original instanceof UnknownDBException) {
               throw (UnknownDBException)original;
            }

            if (original instanceof UnknownPartitionException) {
               throw (UnknownPartitionException)original;
            }

            if (original instanceof InvalidPartitionException) {
               throw (InvalidPartitionException)original;
            }

            if (original instanceof MetaException) {
               throw (MetaException)original;
            }

            throw newMetaException(original);
         } finally {
            this.endFunction("isPartitionMarkedForEvent", ret != null, ex, tbl_name);
         }

         return ret;
      }

      public List set_ugi(String username, List groupNames) throws MetaException, TException {
         Collections.addAll(groupNames, new String[]{username});
         return groupNames;
      }

      public boolean partition_name_has_valid_characters(List part_vals, boolean throw_exception) throws TException, MetaException {
         this.startFunction("partition_name_has_valid_characters");
         boolean ret = false;
         Exception ex = null;

         try {
            if (throw_exception) {
               MetaStoreUtils.validatePartitionNameCharacters(part_vals, this.partitionValidationPattern);
               ret = true;
            } else {
               ret = MetaStoreUtils.partitionNameHasValidCharacters(part_vals, this.partitionValidationPattern);
            }
         } catch (Exception e) {
            if (e instanceof MetaException) {
               throw (MetaException)e;
            }

            throw newMetaException(e);
         }

         this.endFunction("partition_name_has_valid_characters", true, (Exception)null);
         return ret;
      }

      private static MetaException newMetaException(Exception e) {
         if (e instanceof MetaException) {
            return (MetaException)e;
         } else {
            MetaException me = new MetaException(e.toString());
            me.initCause(e);
            return me;
         }
      }

      private void validateFunctionInfo(Function func) throws InvalidObjectException, MetaException {
         if (!MetaStoreUtils.validateName(func.getFunctionName(), (Configuration)null)) {
            throw new InvalidObjectException(func.getFunctionName() + " is not a valid object name");
         } else {
            String className = func.getClassName();
            if (className == null) {
               throw new InvalidObjectException("Function class name cannot be null");
            }
         }
      }

      public void create_function(Function func) throws AlreadyExistsException, InvalidObjectException, MetaException, NoSuchObjectException, TException {
         this.validateFunctionInfo(func);
         boolean success = false;
         RawStore ms = this.getMS();
         Map<String, String> transactionalListenerResponses = Collections.emptyMap();

         try {
            ms.openTransaction();
            Database db = ms.getDatabase(func.getDbName());
            if (db == null) {
               throw new NoSuchObjectException("The database " + func.getDbName() + " does not exist");
            }

            Function existingFunc = ms.getFunction(func.getDbName(), func.getFunctionName());
            if (existingFunc != null) {
               throw new AlreadyExistsException("Function " + func.getFunctionName() + " already exists");
            }

            long time = System.currentTimeMillis() / 1000L;
            func.setCreateTime((int)time);
            ms.createFunction(func);
            if (!this.transactionalListeners.isEmpty()) {
               transactionalListenerResponses = MetaStoreListenerNotifier.notifyEvent(this.transactionalListeners, EventMessage.EventType.CREATE_FUNCTION, new CreateFunctionEvent(func, true, this));
            }

            success = ms.commitTransaction();
         } finally {
            if (!success) {
               ms.rollbackTransaction();
            }

            if (!this.listeners.isEmpty()) {
               MetaStoreListenerNotifier.notifyEvent(this.listeners, EventMessage.EventType.CREATE_FUNCTION, new CreateFunctionEvent(func, success, this), (EnvironmentContext)null, transactionalListenerResponses, ms);
            }

         }

      }

      public void drop_function(String dbName, String funcName) throws NoSuchObjectException, MetaException, InvalidObjectException, InvalidInputException {
         boolean success = false;
         Function func = null;
         RawStore ms = this.getMS();
         Map<String, String> transactionalListenerResponses = Collections.emptyMap();

         try {
            ms.openTransaction();
            func = ms.getFunction(dbName, funcName);
            if (func == null) {
               throw new NoSuchObjectException("Function " + funcName + " does not exist");
            }

            ms.dropFunction(dbName, funcName);
            if (this.transactionalListeners.size() > 0) {
               transactionalListenerResponses = MetaStoreListenerNotifier.notifyEvent(this.transactionalListeners, EventMessage.EventType.DROP_FUNCTION, new DropFunctionEvent(func, true, this));
            }

            success = ms.commitTransaction();
         } finally {
            if (!success) {
               ms.rollbackTransaction();
            }

            if (this.listeners.size() > 0) {
               MetaStoreListenerNotifier.notifyEvent(this.listeners, EventMessage.EventType.DROP_FUNCTION, new DropFunctionEvent(func, success, this), (EnvironmentContext)null, transactionalListenerResponses, ms);
            }

         }

      }

      public void alter_function(String dbName, String funcName, Function newFunc) throws InvalidOperationException, MetaException, TException {
         this.validateFunctionInfo(newFunc);
         boolean success = false;
         RawStore ms = this.getMS();

         try {
            ms.openTransaction();
            ms.alterFunction(dbName, funcName, newFunc);
            success = ms.commitTransaction();
         } finally {
            if (!success) {
               ms.rollbackTransaction();
            }

         }

      }

      public List get_functions(String dbName, String pattern) throws MetaException {
         this.startFunction("get_functions", ": db=" + dbName + " pat=" + pattern);
         RawStore ms = this.getMS();
         Exception ex = null;
         List<String> funcNames = null;

         try {
            funcNames = ms.getFunctions(dbName, pattern);
         } catch (Exception e) {
            ex = e;
            throw newMetaException(e);
         } finally {
            this.endFunction("get_functions", funcNames != null, ex);
         }

         return funcNames;
      }

      public GetAllFunctionsResponse get_all_functions() throws MetaException {
         GetAllFunctionsResponse response = new GetAllFunctionsResponse();
         this.startFunction("get_all_functions");
         RawStore ms = this.getMS();
         List<Function> allFunctions = null;
         Exception ex = null;

         try {
            allFunctions = ms.getAllFunctions();
         } catch (Exception e) {
            ex = e;
            throw newMetaException(e);
         } finally {
            this.endFunction("get_all_functions", allFunctions != null, ex);
         }

         response.setFunctions(allFunctions);
         return response;
      }

      public Function get_function(String dbName, String funcName) throws MetaException, NoSuchObjectException, TException {
         this.startFunction("get_function", ": " + dbName + "." + funcName);
         RawStore ms = this.getMS();
         Function func = null;
         Exception ex = null;

         try {
            func = ms.getFunction(dbName, funcName);
            if (func == null) {
               throw new NoSuchObjectException("Function " + dbName + "." + funcName + " does not exist");
            }
         } catch (NoSuchObjectException e) {
            ex = e;
            this.rethrowException(e);
         } catch (Exception e) {
            ex = e;
            throw newMetaException(e);
         } finally {
            this.endFunction("get_function", func != null, ex);
         }

         return func;
      }

      public GetOpenTxnsResponse get_open_txns() throws TException {
         return this.getTxnHandler().getOpenTxns();
      }

      public GetOpenTxnsInfoResponse get_open_txns_info() throws TException {
         return this.getTxnHandler().getOpenTxnsInfo();
      }

      public OpenTxnsResponse open_txns(OpenTxnRequest rqst) throws TException {
         return this.getTxnHandler().openTxns(rqst);
      }

      public void abort_txn(AbortTxnRequest rqst) throws NoSuchTxnException, TException {
         this.getTxnHandler().abortTxn(rqst);
      }

      public void abort_txns(AbortTxnsRequest rqst) throws NoSuchTxnException, TException {
         this.getTxnHandler().abortTxns(rqst);
      }

      public void commit_txn(CommitTxnRequest rqst) throws NoSuchTxnException, TxnAbortedException, TException {
         this.getTxnHandler().commitTxn(rqst);
      }

      public LockResponse lock(LockRequest rqst) throws NoSuchTxnException, TxnAbortedException, TException {
         return this.getTxnHandler().lock(rqst);
      }

      public LockResponse check_lock(CheckLockRequest rqst) throws NoSuchTxnException, TxnAbortedException, NoSuchLockException, TException {
         return this.getTxnHandler().checkLock(rqst);
      }

      public void unlock(UnlockRequest rqst) throws NoSuchLockException, TxnOpenException, TException {
         this.getTxnHandler().unlock(rqst);
      }

      public ShowLocksResponse show_locks(ShowLocksRequest rqst) throws TException {
         return this.getTxnHandler().showLocks(rqst);
      }

      public void heartbeat(HeartbeatRequest ids) throws NoSuchLockException, NoSuchTxnException, TxnAbortedException, TException {
         this.getTxnHandler().heartbeat(ids);
      }

      public HeartbeatTxnRangeResponse heartbeat_txn_range(HeartbeatTxnRangeRequest rqst) throws TException {
         return this.getTxnHandler().heartbeatTxnRange(rqst);
      }

      /** @deprecated */
      @Deprecated
      public void compact(CompactionRequest rqst) throws TException {
         this.compact2(rqst);
      }

      public CompactionResponse compact2(CompactionRequest rqst) throws TException {
         return this.getTxnHandler().compact(rqst);
      }

      public ShowCompactResponse show_compact(ShowCompactRequest rqst) throws TException {
         return this.getTxnHandler().showCompact(rqst);
      }

      public void flushCache() throws TException {
         this.getMS().flushCache();
      }

      public void add_dynamic_partitions(AddDynamicPartitions rqst) throws NoSuchTxnException, TxnAbortedException, TException {
         this.getTxnHandler().addDynamicPartitions(rqst);
      }

      public GetPrincipalsInRoleResponse get_principals_in_role(GetPrincipalsInRoleRequest request) throws MetaException, TException {
         this.incrementCounter("get_principals_in_role");
         this.firePreEvent(new PreAuthorizationCallEvent(this));
         Exception ex = null;
         GetPrincipalsInRoleResponse response = null;

         try {
            response = new GetPrincipalsInRoleResponse(this.getMS().listRoleMembers(request.getRoleName()));
         } catch (MetaException e) {
            throw e;
         } catch (Exception e) {
            ex = e;
            this.rethrowException(e);
         } finally {
            this.endFunction("get_principals_in_role", ex == null, ex);
         }

         return response;
      }

      public GetRoleGrantsForPrincipalResponse get_role_grants_for_principal(GetRoleGrantsForPrincipalRequest request) throws MetaException, TException {
         this.incrementCounter("get_role_grants_for_principal");
         this.firePreEvent(new PreAuthorizationCallEvent(this));
         Exception ex = null;
         List<RolePrincipalGrant> roleMaps = null;

         try {
            roleMaps = this.getMS().listRolesWithGrants(request.getPrincipal_name(), request.getPrincipal_type());
         } catch (MetaException e) {
            throw e;
         } catch (Exception e) {
            ex = e;
            this.rethrowException(e);
         } finally {
            this.endFunction("get_role_grants_for_principal", ex == null, ex);
         }

         return new GetRoleGrantsForPrincipalResponse(roleMaps);
      }

      private List getRolePrincipalGrants(List roles) throws MetaException {
         List<RolePrincipalGrant> rolePrinGrantList = new ArrayList();
         if (roles != null) {
            for(Role role : roles) {
               rolePrinGrantList.addAll(this.getMS().listRoleMembers(role.getRoleName()));
            }
         }

         return rolePrinGrantList;
      }

      public AggrStats get_aggr_stats_for(PartitionsStatsRequest request) throws NoSuchObjectException, MetaException, TException {
         String dbName = request.getDbName().toLowerCase();
         String tblName = request.getTblName().toLowerCase();
         this.startFunction("get_aggr_stats_for", ": db=" + request.getDbName() + " table=" + request.getTblName());
         List<String> lowerCaseColNames = new ArrayList(request.getColNames().size());

         for(String colName : request.getColNames()) {
            lowerCaseColNames.add(colName.toLowerCase());
         }

         List<String> lowerCasePartNames = new ArrayList(request.getPartNames().size());

         for(String partName : request.getPartNames()) {
            lowerCasePartNames.add(this.lowerCaseConvertPartName(partName));
         }

         AggrStats aggrStats = null;

         AggrStats var14;
         try {
            aggrStats = new AggrStats(this.getMS().get_aggr_stats_for(dbName, tblName, lowerCasePartNames, lowerCaseColNames));
            var14 = aggrStats;
         } finally {
            this.endFunction("get_aggr_stats_for", aggrStats == null, (Exception)null, request.getTblName());
         }

         return var14;
      }

      public boolean set_aggr_stats_for(SetPartitionsStatsRequest request) throws NoSuchObjectException, InvalidObjectException, MetaException, InvalidInputException, TException {
         boolean ret = true;
         List<ColumnStatistics> csNews = request.getColStats();
         if (csNews != null && !csNews.isEmpty()) {
            ColumnStatistics firstColStats = (ColumnStatistics)csNews.get(0);
            ColumnStatisticsDesc statsDesc = firstColStats.getStatsDesc();
            String dbName = statsDesc.getDbName();
            String tableName = statsDesc.getTableName();
            List<String> colNames = new ArrayList();

            for(ColumnStatisticsObj obj : firstColStats.getStatsObj()) {
               colNames.add(obj.getColName());
            }

            if (statsDesc.isIsTblLevel()) {
               if (request.getColStatsSize() != 1) {
                  throw new MetaException("Expecting only 1 ColumnStatistics for table's column stats, but find " + request.getColStatsSize());
               } else {
                  if (request.isSetNeedMerge() && request.isNeedMerge()) {
                     ColumnStatistics csOld = this.getMS().getTableColumnStatistics(dbName, tableName, colNames);
                     if (csOld != null && csOld.getStatsObjSize() != 0) {
                        MetaStoreUtils.mergeColStats(firstColStats, csOld);
                     }
                  }

                  return this.update_table_column_statistics(firstColStats);
               }
            } else {
               List<String> partitionNames = new ArrayList();

               for(ColumnStatistics csNew : csNews) {
                  partitionNames.add(csNew.getStatsDesc().getPartName());
               }

               Map<String, ColumnStatistics> map = new HashMap();
               if (request.isSetNeedMerge() && request.isNeedMerge()) {
                  List<ColumnStatistics> csOlds = this.getMS().getPartitionColumnStatistics(dbName, tableName, partitionNames, colNames);
                  if (csNews.size() != csOlds.size()) {
                     LOG.debug("Some of the partitions miss stats.");
                  }

                  for(ColumnStatistics csOld : csOlds) {
                     map.put(csOld.getStatsDesc().getPartName(), csOld);
                  }
               }

               Table t = this.getTable(dbName, tableName);

               for(int index = 0; index < csNews.size(); ++index) {
                  ColumnStatistics csNew = (ColumnStatistics)csNews.get(index);
                  ColumnStatistics csOld = (ColumnStatistics)map.get(csNew.getStatsDesc().getPartName());
                  if (csOld != null && csOld.getStatsObjSize() != 0) {
                     MetaStoreUtils.mergeColStats(csNew, csOld);
                  }

                  ret = ret && this.updatePartitonColStats(t, csNew);
               }

               return ret;
            }
         } else {
            return ret;
         }
      }

      private Table getTable(String dbName, String tableName) throws MetaException, InvalidObjectException {
         Table t = this.getMS().getTable(dbName, tableName);
         if (t == null) {
            throw new InvalidObjectException(dbName + "." + tableName + " table not found");
         } else {
            return t;
         }
      }

      public NotificationEventResponse get_next_notification(NotificationEventRequest rqst) throws TException {
         RawStore ms = this.getMS();
         return ms.getNextNotification(rqst);
      }

      public CurrentNotificationEventId get_current_notificationEventId() throws TException {
         RawStore ms = this.getMS();
         return ms.getCurrentNotificationEventId();
      }

      public FireEventResponse fire_listener_event(FireEventRequest rqst) throws TException {
         switch ((FireEventRequestData._Fields)rqst.getData().getSetField()) {
            case INSERT_DATA:
               InsertEvent event = new InsertEvent(rqst.getDbName(), rqst.getTableName(), rqst.getPartitionVals(), rqst.getData().getInsertData(), rqst.isSuccessful(), this);
               MetaStoreListenerNotifier.notifyEvent(this.transactionalListeners, EventMessage.EventType.INSERT, event);
               MetaStoreListenerNotifier.notifyEvent(this.listeners, EventMessage.EventType.INSERT, event);
               return new FireEventResponse();
            default:
               throw new TException("Event type " + ((FireEventRequestData._Fields)rqst.getData().getSetField()).toString() + " not currently supported.");
         }
      }

      public GetFileMetadataByExprResult get_file_metadata_by_expr(GetFileMetadataByExprRequest req) throws TException {
         GetFileMetadataByExprResult result = new GetFileMetadataByExprResult();
         RawStore ms = this.getMS();
         if (!ms.isFileMetadataSupported()) {
            result.setIsSupported(false);
            result.setMetadata(EMPTY_MAP_FM2);
            return result;
         } else {
            result.setIsSupported(true);
            List<Long> fileIds = req.getFileIds();
            boolean needMetadata = !req.isSetDoGetFooters() || req.isDoGetFooters();
            FileMetadataExprType type = req.isSetType() ? req.getType() : FileMetadataExprType.ORC_SARG;
            ByteBuffer[] metadatas = needMetadata ? new ByteBuffer[fileIds.size()] : null;
            ByteBuffer[] ppdResults = new ByteBuffer[fileIds.size()];
            boolean[] eliminated = new boolean[fileIds.size()];
            this.getMS().getFileMetadataByExpr(fileIds, type, req.getExpr(), metadatas, ppdResults, eliminated);

            for(int i = 0; i < fileIds.size(); ++i) {
               if (eliminated[i] || ppdResults[i] != null) {
                  MetadataPpdResult mpr = new MetadataPpdResult();
                  ByteBuffer ppdResult = eliminated[i] ? null : this.handleReadOnlyBufferForThrift(ppdResults[i]);
                  mpr.setIncludeBitset(ppdResult);
                  if (needMetadata) {
                     ByteBuffer metadata = eliminated[i] ? null : this.handleReadOnlyBufferForThrift(metadatas[i]);
                     mpr.setMetadata(metadata);
                  }

                  result.putToMetadata((Long)fileIds.get(i), mpr);
               }
            }

            if (!result.isSetMetadata()) {
               result.setMetadata(EMPTY_MAP_FM2);
            }

            return result;
         }
      }

      public GetFileMetadataResult get_file_metadata(GetFileMetadataRequest req) throws TException {
         GetFileMetadataResult result = new GetFileMetadataResult();
         RawStore ms = this.getMS();
         if (!ms.isFileMetadataSupported()) {
            result.setIsSupported(false);
            result.setMetadata(EMPTY_MAP_FM1);
            return result;
         } else {
            result.setIsSupported(true);
            List<Long> fileIds = req.getFileIds();
            ByteBuffer[] metadatas = ms.getFileMetadata(fileIds);

            assert metadatas.length == fileIds.size();

            for(int i = 0; i < metadatas.length; ++i) {
               ByteBuffer bb = metadatas[i];
               if (bb != null) {
                  bb = this.handleReadOnlyBufferForThrift(bb);
                  result.putToMetadata((Long)fileIds.get(i), bb);
               }
            }

            if (!result.isSetMetadata()) {
               result.setMetadata(EMPTY_MAP_FM1);
            }

            return result;
         }
      }

      private ByteBuffer handleReadOnlyBufferForThrift(ByteBuffer bb) {
         if (!bb.isReadOnly()) {
            return bb;
         } else {
            ByteBuffer copy = ByteBuffer.allocate(bb.capacity());
            copy.put(bb);
            copy.flip();
            return copy;
         }
      }

      public PutFileMetadataResult put_file_metadata(PutFileMetadataRequest req) throws TException {
         RawStore ms = this.getMS();
         if (ms.isFileMetadataSupported()) {
            ms.putFileMetadata(req.getFileIds(), req.getMetadata(), req.getType());
         }

         return new PutFileMetadataResult();
      }

      public ClearFileMetadataResult clear_file_metadata(ClearFileMetadataRequest req) throws TException {
         this.getMS().putFileMetadata(req.getFileIds(), (List)null, (FileMetadataExprType)null);
         return new ClearFileMetadataResult();
      }

      public CacheFileMetadataResult cache_file_metadata(CacheFileMetadataRequest req) throws TException {
         RawStore ms = this.getMS();
         if (!ms.isFileMetadataSupported()) {
            return new CacheFileMetadataResult(false);
         } else {
            String dbName = req.getDbName();
            String tblName = req.getTblName();
            String partName = req.isSetPartName() ? req.getPartName() : null;
            boolean isAllPart = req.isSetIsAllParts() && req.isIsAllParts();
            ms.openTransaction();
            boolean success = false;

            try {
               Table tbl = ms.getTable(dbName, tblName);
               if (tbl == null) {
                  throw new NoSuchObjectException(dbName + "." + tblName + " not found");
               }

               boolean isPartitioned = tbl.isSetPartitionKeys() && tbl.getPartitionKeysSize() > 0;
               String tableInputFormat = tbl.isSetSd() ? tbl.getSd().getInputFormat() : null;
               if (!isPartitioned) {
                  if (partName == null && !isAllPart) {
                     if (tbl.isSetSd() && tbl.getSd().isSetLocation()) {
                        FileMetadataExprType type = this.expressionProxy.getMetadataType(tableInputFormat);
                        if (type == null) {
                           throw new MetaException("The operation is not supported for " + tableInputFormat);
                        }

                        this.fileMetadataManager.queueCacheMetadata(tbl.getSd().getLocation(), type);
                        success = true;
                        return new CacheFileMetadataResult(true);
                     }

                     throw new MetaException("Table does not have storage location; this operation is not supported on views");
                  }

                  throw new MetaException("Table is not partitioned");
               } else {
                  List<String> partNames = null;
                  if (partName != null) {
                     partNames = Lists.newArrayList(new String[]{partName});
                  } else {
                     if (!isAllPart) {
                        throw new MetaException("Table is partitioned");
                     }

                     partNames = ms.listPartitionNames(dbName, tblName, (short)-1);
                  }

                  int batchSize = HiveConf.getIntVar(this.hiveConf, ConfVars.METASTORE_BATCH_RETRIEVE_OBJECTS_MAX);
                  int index = 0;
                  int successCount = 0;
                  int failCount = 0;
                  HashSet<String> failFormats = null;

                  while(index < partNames.size()) {
                     int currentBatchSize = Math.min(batchSize, partNames.size() - index);
                     List<String> nameBatch = partNames.subList(index, index + currentBatchSize);
                     index += currentBatchSize;

                     for(Partition part : ms.getPartitionsByNames(dbName, tblName, nameBatch)) {
                        if (!part.isSetSd() || !part.getSd().isSetLocation()) {
                           throw new MetaException("Partition does not have storage location; this operation is not supported on views");
                        }

                        String inputFormat = part.getSd().isSetInputFormat() ? part.getSd().getInputFormat() : tableInputFormat;
                        FileMetadataExprType type = this.expressionProxy.getMetadataType(inputFormat);
                        if (type == null) {
                           ++failCount;
                           if (failFormats == null) {
                              failFormats = new HashSet();
                           }

                           failFormats.add(inputFormat);
                        } else {
                           ++successCount;
                           this.fileMetadataManager.queueCacheMetadata(part.getSd().getLocation(), type);
                        }
                     }
                  }

                  success = true;
                  if (failCount > 0) {
                     String errorMsg = "The operation failed for " + failCount + " partitions and succeeded for " + successCount + " partitions; unsupported formats: ";
                     boolean isFirst = true;

                     for(String s : failFormats) {
                        if (!isFirst) {
                           errorMsg = errorMsg + ", ";
                        }

                        isFirst = false;
                        errorMsg = errorMsg + s;
                     }

                     throw new MetaException(errorMsg);
                  }
               }
            } finally {
               if (success) {
                  if (!ms.commitTransaction()) {
                     throw new MetaException("Failed to commit");
                  }
               } else {
                  ms.rollbackTransaction();
               }

            }

            return new CacheFileMetadataResult(true);
         }
      }

      @VisibleForTesting
      public void updateMetrics() throws MetaException {
         this.initTableCount = this.getMS().getTableCount();
         this.initPartCount = this.getMS().getPartitionCount();
         this.initDatabaseCount = this.getMS().getDatabaseCount();
      }

      public PrimaryKeysResponse get_primary_keys(PrimaryKeysRequest request) throws MetaException, NoSuchObjectException, TException {
         String db_name = request.getDb_name();
         String tbl_name = request.getTbl_name();
         this.startTableFunction("get_primary_keys", db_name, tbl_name);
         List<SQLPrimaryKey> ret = null;
         Exception ex = null;

         try {
            ret = this.getMS().getPrimaryKeys(db_name, tbl_name);
         } catch (Exception e) {
            ex = e;
            if (e instanceof MetaException) {
               throw (MetaException)e;
            }

            if (e instanceof NoSuchObjectException) {
               throw (NoSuchObjectException)e;
            }

            throw newMetaException(e);
         } finally {
            this.endFunction("get_primary_keys", ret != null, ex, tbl_name);
         }

         return new PrimaryKeysResponse(ret);
      }

      public ForeignKeysResponse get_foreign_keys(ForeignKeysRequest request) throws MetaException, NoSuchObjectException, TException {
         String parent_db_name = request.getParent_db_name();
         String parent_tbl_name = request.getParent_tbl_name();
         String foreign_db_name = request.getForeign_db_name();
         String foreign_tbl_name = request.getForeign_tbl_name();
         this.startFunction("get_foreign_keys", " : parentdb=" + parent_db_name + " parenttbl=" + parent_tbl_name + " foreigndb=" + foreign_db_name + " foreigntbl=" + foreign_tbl_name);
         List<SQLForeignKey> ret = null;
         Exception ex = null;

         try {
            ret = this.getMS().getForeignKeys(parent_db_name, parent_tbl_name, foreign_db_name, foreign_tbl_name);
         } catch (Exception e) {
            ex = e;
            if (e instanceof MetaException) {
               throw (MetaException)e;
            }

            if (e instanceof NoSuchObjectException) {
               throw (NoSuchObjectException)e;
            }

            throw newMetaException(e);
         } finally {
            this.endFunction("get_foreign_keys", ret != null, ex, foreign_tbl_name);
         }

         return new ForeignKeysResponse(ret);
      }

      static {
         LOG = HiveMetaStore.LOG;
         threadLocalMS = new ThreadLocal() {
            protected RawStore initialValue() {
               return null;
            }
         };
         threadLocalTxn = new ThreadLocal() {
            protected TxnStore initialValue() {
               return null;
            }
         };
         threadLocalConf = new ThreadLocal() {
            protected Configuration initialValue() {
               return null;
            }
         };
         auditLog = LoggerFactory.getLogger(HiveMetaStore.class.getName() + ".audit");
         auditFormatter = new ThreadLocal() {
            protected Formatter initialValue() {
               return new Formatter(new StringBuilder("ugi=%s\tip=%s\tcmd=%s\t".length() * 4));
            }
         };
         nextSerialNum = 0;
         threadLocalId = new ThreadLocal() {
            protected Integer initialValue() {
               return new Integer(HiveMetaStore.HMSHandler.nextSerialNum++);
            }
         };
         threadLocalIpAddress = new ThreadLocal() {
            protected String initialValue() {
               return null;
            }
         };
         EMPTY_MAP_FM1 = new HashMap(1);
         EMPTY_MAP_FM2 = new HashMap(1);
      }

      private static class PartValEqWrapper {
         Partition partition;

         public PartValEqWrapper(Partition partition) {
            this.partition = partition;
         }

         public int hashCode() {
            return this.partition.isSetValues() ? this.partition.getValues().hashCode() : 0;
         }

         public boolean equals(Object obj) {
            if (this == obj) {
               return true;
            } else if (obj != null && obj instanceof PartValEqWrapper) {
               Partition p1 = this.partition;
               Partition p2 = ((PartValEqWrapper)obj).partition;
               if (p1.isSetValues() && p2.isSetValues()) {
                  if (p1.getValues().size() != p2.getValues().size()) {
                     return false;
                  } else {
                     for(int i = 0; i < p1.getValues().size(); ++i) {
                        String v1 = (String)p1.getValues().get(i);
                        String v2 = (String)p2.getValues().get(i);
                        if ((v1 != null || v2 != null) && (v1 == null || !v1.equals(v2))) {
                           return false;
                        }
                     }

                     return true;
                  }
               } else {
                  return p1.isSetValues() == p2.isSetValues();
               }
            } else {
               return false;
            }
         }
      }

      private static class PartValEqWrapperLite {
         List values;
         String location;

         public PartValEqWrapperLite(Partition partition) {
            this.values = partition.isSetValues() ? partition.getValues() : null;
            this.location = partition.getSd().getLocation();
         }

         public int hashCode() {
            return this.values == null ? 0 : this.values.hashCode();
         }

         public boolean equals(Object obj) {
            if (this == obj) {
               return true;
            } else if (obj != null && obj instanceof PartValEqWrapperLite) {
               List<String> lhsValues = this.values;
               List<String> rhsValues = ((PartValEqWrapperLite)obj).values;
               if (lhsValues != null && rhsValues != null) {
                  if (lhsValues.size() != rhsValues.size()) {
                     return false;
                  } else {
                     for(int i = 0; i < lhsValues.size(); ++i) {
                        String lhsValue = (String)lhsValues.get(i);
                        String rhsValue = (String)rhsValues.get(i);
                        if (lhsValue == null && rhsValue != null || lhsValue != null && !lhsValue.equals(rhsValue)) {
                           return false;
                        }
                     }

                     return true;
                  }
               } else {
                  return lhsValues == rhsValues;
               }
            } else {
               return false;
            }
         }
      }

      private static class PathAndPartValSize {
         public Path path;
         public int partValSize;

         public PathAndPartValSize(Path path, int partValSize) {
            this.path = path;
            this.partValSize = partValSize;
         }
      }

      private static class StorageDescriptorKey {
         private final StorageDescriptor sd;

         StorageDescriptorKey(StorageDescriptor sd) {
            this.sd = sd;
         }

         StorageDescriptor getSd() {
            return this.sd;
         }

         private String hashCodeKey() {
            return this.sd.getInputFormat() + "\t" + this.sd.getOutputFormat() + "\t" + this.sd.getSerdeInfo().getSerializationLib() + "\t" + this.sd.getCols();
         }

         public int hashCode() {
            return this.hashCodeKey().hashCode();
         }

         public boolean equals(Object rhs) {
            if (rhs == this) {
               return true;
            } else {
               return !(rhs instanceof StorageDescriptorKey) ? false : this.hashCodeKey().equals(((StorageDescriptorKey)rhs).hashCodeKey());
            }
         }
      }
   }

   public static class HiveMetastoreCli extends CommonCliOptions {
      private int port;

      public HiveMetastoreCli(Configuration configuration) {
         super("hivemetastore", true);
         this.port = HiveConf.getIntVar(configuration, ConfVars.METASTORE_SERVER_PORT);
         Options var10000 = this.OPTIONS;
         OptionBuilder.hasArg();
         OptionBuilder.withArgName("port");
         OptionBuilder.withDescription("Hive Metastore port number, default:" + this.port);
         var10000.addOption(OptionBuilder.create('p'));
      }

      public void parse(String[] args) {
         super.parse(args);
         args = this.commandLine.getArgs();
         if (args.length > 0) {
            System.err.println("This usage has been deprecated, consider using the new command line syntax (run with -h to see usage information)");
            this.port = new Integer(args[0]);
         }

         if (this.commandLine.hasOption('p')) {
            this.port = Integer.parseInt(this.commandLine.getOptionValue('p'));
         } else {
            String metastorePort = System.getenv("METASTORE_PORT");
            if (metastorePort != null) {
               this.port = Integer.parseInt(metastorePort);
            }
         }

      }

      public int getPort() {
         return this.port;
      }
   }

   public interface ThreadLocalRawStore {
      RawStore getMS() throws MetaException;
   }
}
