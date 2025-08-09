package org.apache.hadoop.hive.metastore;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.URI;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Pattern;
import javax.jdo.JDOCanRetryException;
import javax.jdo.JDODataStoreException;
import javax.jdo.JDOException;
import javax.jdo.JDOHelper;
import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;
import javax.jdo.datastore.DataStoreCache;
import javax.jdo.identity.IntIdentity;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.conf.Configurable;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.common.FileUtils;
import org.apache.hadoop.hive.common.ObjectPair;
import org.apache.hadoop.hive.common.StatsSetupConst;
import org.apache.hadoop.hive.common.classification.InterfaceAudience.LimitedPrivate;
import org.apache.hadoop.hive.common.classification.InterfaceStability.Evolving;
import org.apache.hadoop.hive.common.metrics.common.Metrics;
import org.apache.hadoop.hive.common.metrics.common.MetricsFactory;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.apache.hadoop.hive.metastore.api.AggrStats;
import org.apache.hadoop.hive.metastore.api.ColumnStatistics;
import org.apache.hadoop.hive.metastore.api.ColumnStatisticsDesc;
import org.apache.hadoop.hive.metastore.api.ColumnStatisticsObj;
import org.apache.hadoop.hive.metastore.api.CurrentNotificationEventId;
import org.apache.hadoop.hive.metastore.api.Database;
import org.apache.hadoop.hive.metastore.api.FieldSchema;
import org.apache.hadoop.hive.metastore.api.FileMetadataExprType;
import org.apache.hadoop.hive.metastore.api.Function;
import org.apache.hadoop.hive.metastore.api.FunctionType;
import org.apache.hadoop.hive.metastore.api.HiveObjectPrivilege;
import org.apache.hadoop.hive.metastore.api.HiveObjectRef;
import org.apache.hadoop.hive.metastore.api.HiveObjectType;
import org.apache.hadoop.hive.metastore.api.Index;
import org.apache.hadoop.hive.metastore.api.InvalidInputException;
import org.apache.hadoop.hive.metastore.api.InvalidObjectException;
import org.apache.hadoop.hive.metastore.api.InvalidPartitionException;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.metastore.api.NoSuchObjectException;
import org.apache.hadoop.hive.metastore.api.NotificationEvent;
import org.apache.hadoop.hive.metastore.api.NotificationEventRequest;
import org.apache.hadoop.hive.metastore.api.NotificationEventResponse;
import org.apache.hadoop.hive.metastore.api.Order;
import org.apache.hadoop.hive.metastore.api.Partition;
import org.apache.hadoop.hive.metastore.api.PartitionEventType;
import org.apache.hadoop.hive.metastore.api.PartitionValuesResponse;
import org.apache.hadoop.hive.metastore.api.PartitionValuesRow;
import org.apache.hadoop.hive.metastore.api.PrincipalPrivilegeSet;
import org.apache.hadoop.hive.metastore.api.PrincipalType;
import org.apache.hadoop.hive.metastore.api.PrivilegeBag;
import org.apache.hadoop.hive.metastore.api.PrivilegeGrantInfo;
import org.apache.hadoop.hive.metastore.api.ResourceType;
import org.apache.hadoop.hive.metastore.api.ResourceUri;
import org.apache.hadoop.hive.metastore.api.Role;
import org.apache.hadoop.hive.metastore.api.RolePrincipalGrant;
import org.apache.hadoop.hive.metastore.api.SQLForeignKey;
import org.apache.hadoop.hive.metastore.api.SQLPrimaryKey;
import org.apache.hadoop.hive.metastore.api.SerDeInfo;
import org.apache.hadoop.hive.metastore.api.SkewedInfo;
import org.apache.hadoop.hive.metastore.api.StorageDescriptor;
import org.apache.hadoop.hive.metastore.api.Table;
import org.apache.hadoop.hive.metastore.api.TableMeta;
import org.apache.hadoop.hive.metastore.api.Type;
import org.apache.hadoop.hive.metastore.api.UnknownDBException;
import org.apache.hadoop.hive.metastore.api.UnknownPartitionException;
import org.apache.hadoop.hive.metastore.api.UnknownTableException;
import org.apache.hadoop.hive.metastore.model.MColumnDescriptor;
import org.apache.hadoop.hive.metastore.model.MConstraint;
import org.apache.hadoop.hive.metastore.model.MDBPrivilege;
import org.apache.hadoop.hive.metastore.model.MDatabase;
import org.apache.hadoop.hive.metastore.model.MDelegationToken;
import org.apache.hadoop.hive.metastore.model.MFieldSchema;
import org.apache.hadoop.hive.metastore.model.MFunction;
import org.apache.hadoop.hive.metastore.model.MGlobalPrivilege;
import org.apache.hadoop.hive.metastore.model.MIndex;
import org.apache.hadoop.hive.metastore.model.MMasterKey;
import org.apache.hadoop.hive.metastore.model.MNotificationLog;
import org.apache.hadoop.hive.metastore.model.MNotificationNextId;
import org.apache.hadoop.hive.metastore.model.MOrder;
import org.apache.hadoop.hive.metastore.model.MPartition;
import org.apache.hadoop.hive.metastore.model.MPartitionColumnPrivilege;
import org.apache.hadoop.hive.metastore.model.MPartitionColumnStatistics;
import org.apache.hadoop.hive.metastore.model.MPartitionEvent;
import org.apache.hadoop.hive.metastore.model.MPartitionPrivilege;
import org.apache.hadoop.hive.metastore.model.MResourceUri;
import org.apache.hadoop.hive.metastore.model.MRole;
import org.apache.hadoop.hive.metastore.model.MRoleMap;
import org.apache.hadoop.hive.metastore.model.MSerDeInfo;
import org.apache.hadoop.hive.metastore.model.MStorageDescriptor;
import org.apache.hadoop.hive.metastore.model.MStringList;
import org.apache.hadoop.hive.metastore.model.MTable;
import org.apache.hadoop.hive.metastore.model.MTableColumnPrivilege;
import org.apache.hadoop.hive.metastore.model.MTableColumnStatistics;
import org.apache.hadoop.hive.metastore.model.MTablePrivilege;
import org.apache.hadoop.hive.metastore.model.MType;
import org.apache.hadoop.hive.metastore.model.MVersionTable;
import org.apache.hadoop.hive.metastore.parser.ExpressionTree;
import org.apache.hadoop.hive.metastore.partition.spec.PartitionSpecProxy;
import org.apache.hadoop.hive.serde2.typeinfo.PrimitiveTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoFactory;
import org.apache.hadoop.hive.shims.ShimLoader;
import org.apache.hive.common.util.HiveStringUtils;
import org.apache.thrift.TException;
import org.datanucleus.AbstractNucleusContext;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ClassLoaderResolverImpl;
import org.datanucleus.ExecutionContext;
import org.datanucleus.ExecutionContextThreadedImpl;
import org.datanucleus.NucleusContext;
import org.datanucleus.PersistenceNucleusContextImpl;
import org.datanucleus.api.jdo.JDOPersistenceManager;
import org.datanucleus.api.jdo.JDOPersistenceManagerFactory;
import org.datanucleus.plugin.NonManagedPluginRegistry;
import org.datanucleus.plugin.PluginManager;
import org.datanucleus.plugin.PluginRegistry;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.exceptions.MissingTableException;
import org.datanucleus.store.rdbms.scostore.BaseContainerStore;
import org.datanucleus.store.scostore.Store;
import org.datanucleus.store.types.TypeManagerImpl;
import org.datanucleus.util.WeakValueMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ObjectStore implements RawStore, Configurable {
   private static Properties prop = null;
   private static PersistenceManagerFactory pmf = null;
   private static Lock pmfPropLock = new ReentrantLock();
   private static final AtomicBoolean isSchemaVerified = new AtomicBoolean(false);
   private static final Logger LOG = LoggerFactory.getLogger(ObjectStore.class.getName());
   private static final Map PINCLASSMAP;
   private static final String HOSTNAME;
   private static final String USER;
   private static final String JDO_PARAM = ":param";
   private boolean isInitialized = false;
   private PersistenceManager pm = null;
   private MetaStoreDirectSql directSql = null;
   private PartitionExpressionProxy expressionProxy = null;
   private Configuration hiveConf;
   private volatile int openTrasactionCalls = 0;
   private Transaction currentTransaction = null;
   private TXN_STATUS transactionStatus;
   private Pattern partitionValidationPattern;
   private ClassLoader classLoader;
   private static final Set retriableExceptionClasses;
   private static final int stackLimit = 5;

   public ObjectStore() {
      this.transactionStatus = ObjectStore.TXN_STATUS.NO_STATE;
      this.classLoader = Thread.currentThread().getContextClassLoader();
      if (this.classLoader == null) {
         this.classLoader = ObjectStore.class.getClassLoader();
      }

   }

   public Configuration getConf() {
      return this.hiveConf;
   }

   public void setConf(Configuration conf) {
      pmfPropLock.lock();

      try {
         this.isInitialized = false;
         this.hiveConf = conf;
         configureSSL(conf);
         Properties propsFromConf = getDataSourceProps(conf);
         boolean propsChanged = !propsFromConf.equals(prop);
         if (propsChanged) {
            if (pmf != null) {
               clearOutPmfClassLoaderCache(pmf);
            }

            pmf = null;
            prop = null;
         }

         assert !this.isActiveTransaction();

         this.shutdown();
         this.pm = null;
         this.directSql = null;
         this.expressionProxy = null;
         this.openTrasactionCalls = 0;
         this.currentTransaction = null;
         this.transactionStatus = ObjectStore.TXN_STATUS.NO_STATE;
         this.initialize(propsFromConf);
         String partitionValidationRegex = this.hiveConf.get(ConfVars.METASTORE_PARTITION_NAME_WHITELIST_PATTERN.name());
         if (partitionValidationRegex != null && !partitionValidationRegex.isEmpty()) {
            this.partitionValidationPattern = Pattern.compile(partitionValidationRegex);
         } else {
            this.partitionValidationPattern = null;
         }

         if (!this.isInitialized) {
            throw new RuntimeException("Unable to create persistence manager. Check dss.log for details");
         }

         LOG.info("Initialized ObjectStore");
      } finally {
         pmfPropLock.unlock();
      }

   }

   private void initialize(Properties dsProps) {
      int retryLimit = HiveConf.getIntVar(this.hiveConf, ConfVars.HMSHANDLERATTEMPTS);
      long retryInterval = HiveConf.getTimeVar(this.hiveConf, ConfVars.HMSHANDLERINTERVAL, TimeUnit.MILLISECONDS);
      int numTries = retryLimit;

      while(true) {
         if (numTries > 0) {
            try {
               this.initializeHelper(dsProps);
               return;
            } catch (Exception var10) {
               --numTries;
               boolean retriable = this.isRetriableException(var10);
               if (numTries > 0 && retriable) {
                  LOG.info("Retriable exception while instantiating ObjectStore, retrying. " + numTries + " tries left", var10);

                  try {
                     Thread.sleep(retryInterval);
                  } catch (InterruptedException ie) {
                     LOG.debug("Interrupted while sleeping before retrying.", ie);
                     Thread.currentThread().interrupt();
                  }
                  continue;
               }

               if (retriable) {
                  LOG.warn("Exception retry limit reached, not retrying any longer.", var10);
               } else {
                  LOG.debug("Non-retriable exception during ObjectStore initialize.", var10);
               }

               throw var10;
            }
         }

         return;
      }
   }

   private boolean isRetriableException(Throwable e) {
      if (e == null) {
         return false;
      } else if (retriableExceptionClasses.contains(e.getClass())) {
         return true;
      } else {
         for(Class c : retriableExceptionClasses) {
            if (c.isInstance(e)) {
               return true;
            }
         }

         if (e.getCause() == null) {
            return false;
         } else {
            return this.isRetriableException(e.getCause());
         }
      }
   }

   private void initializeHelper(Properties dsProps) {
      LOG.info("ObjectStore, initialize called");
      prop = dsProps;
      this.pm = this.getPersistenceManager();
      this.isInitialized = this.pm != null;
      if (this.isInitialized) {
         this.expressionProxy = createExpressionProxy(this.hiveConf);
         if (HiveConf.getBoolVar(this.getConf(), ConfVars.METASTORE_TRY_DIRECT_SQL)) {
            this.directSql = new MetaStoreDirectSql(this.pm, this.hiveConf);
         }
      }

      LOG.debug("RawStore: " + this + ", with PersistenceManager: " + this.pm + " created in the thread with id: " + Thread.currentThread().getId());
   }

   private static PartitionExpressionProxy createExpressionProxy(Configuration conf) {
      String className = HiveConf.getVar(conf, ConfVars.METASTORE_EXPRESSION_PROXY_CLASS);

      try {
         Class<? extends PartitionExpressionProxy> clazz = MetaStoreUtils.getClass(className);
         return (PartitionExpressionProxy)MetaStoreUtils.newInstance(clazz, new Class[0], new Object[0]);
      } catch (MetaException e) {
         LOG.error("Error loading PartitionExpressionProxy", e);
         throw new RuntimeException("Error loading PartitionExpressionProxy: " + e.getMessage());
      }
   }

   private static void configureSSL(Configuration conf) {
      String sslPropString = conf.get(ConfVars.METASTORE_DBACCESS_SSL_PROPS.varname);
      if (StringUtils.isNotEmpty(sslPropString)) {
         LOG.info("Metastore setting SSL properties of the connection to backed DB");

         for(String sslProp : sslPropString.split(",")) {
            String[] pair = sslProp.trim().split("=");
            if (pair != null && pair.length == 2) {
               System.setProperty(pair[0].trim(), pair[1].trim());
            } else {
               LOG.warn("Invalid metastore property value for " + ConfVars.METASTORE_DBACCESS_SSL_PROPS);
            }
         }
      }

   }

   private static Properties getDataSourceProps(Configuration conf) {
      Properties prop = new Properties();
      correctAutoStartMechanism(conf);

      for(Map.Entry e : conf) {
         if (((String)e.getKey()).contains("datanucleus") || ((String)e.getKey()).contains("jdo")) {
            Object prevVal = prop.setProperty((String)e.getKey(), conf.get((String)e.getKey()));
            if (LOG.isDebugEnabled() && !((String)e.getKey()).equals(ConfVars.METASTOREPWD.varname)) {
               LOG.debug("Overriding " + (String)e.getKey() + " value " + prevVal + " from  jpox.properties with " + (String)e.getValue());
            }
         }
      }

      try {
         String passwd = ShimLoader.getHadoopShims().getPassword(conf, ConfVars.METASTOREPWD.varname);
         if (passwd != null && !passwd.isEmpty()) {
            prop.setProperty(ConfVars.METASTOREPWD.varname, passwd);
         }
      } catch (IOException err) {
         throw new RuntimeException("Error getting metastore password: " + err.getMessage(), err);
      }

      if (LOG.isDebugEnabled()) {
         for(Map.Entry e : prop.entrySet()) {
            if (!e.getKey().equals(ConfVars.METASTOREPWD.varname)) {
               LOG.debug(e.getKey() + " = " + e.getValue());
            }
         }
      }

      return prop;
   }

   private static void correctAutoStartMechanism(Configuration conf) {
      String autoStartKey = "datanucleus.autoStartMechanismMode";
      String autoStartIgnore = "ignored";
      String currentAutoStartVal = conf.get("datanucleus.autoStartMechanismMode");
      if (currentAutoStartVal != null && !currentAutoStartVal.equalsIgnoreCase("ignored")) {
         LOG.warn("datanucleus.autoStartMechanismMode is set to unsupported value " + conf.get("datanucleus.autoStartMechanismMode") + " . Setting it to value " + "ignored");
      }

      conf.set("datanucleus.autoStartMechanismMode", "ignored");
   }

   private static synchronized PersistenceManagerFactory getPMF() {
      if (pmf == null) {
         pmf = JDOHelper.getPersistenceManagerFactory(prop);
         DataStoreCache dsc = pmf.getDataStoreCache();
         if (dsc != null) {
            HiveConf conf = new HiveConf(ObjectStore.class);
            String objTypes = HiveConf.getVar(conf, ConfVars.METASTORE_CACHE_PINOBJTYPES);
            LOG.info("Setting MetaStore object pin classes with hive.metastore.cache.pinobjtypes=\"" + objTypes + "\"");
            if (objTypes != null && objTypes.length() > 0) {
               objTypes = objTypes.toLowerCase();
               String[] typeTokens = objTypes.split(",");

               for(String type : typeTokens) {
                  type = type.trim();
                  if (PINCLASSMAP.containsKey(type)) {
                     dsc.pinAll(true, (Class)PINCLASSMAP.get(type));
                  } else {
                     LOG.warn(type + " is not one of the pinnable object types: " + StringUtils.join(PINCLASSMAP.keySet(), " "));
                  }
               }
            }
         } else {
            LOG.warn("PersistenceManagerFactory returned null DataStoreCache object. Unable to initialize object pin types defined by hive.metastore.cache.pinobjtypes");
         }
      }

      return pmf;
   }

   @LimitedPrivate({"HCATALOG"})
   @Evolving
   public PersistenceManager getPersistenceManager() {
      return getPMF().getPersistenceManager();
   }

   public void shutdown() {
      if (this.pm != null) {
         LOG.debug("RawStore: " + this + ", with PersistenceManager: " + this.pm + " will be shutdown");
         this.pm.close();
      }

   }

   public boolean openTransaction() {
      return this.openTransaction((String)null);
   }

   public boolean openTransaction(String isolationLevel) {
      ++this.openTrasactionCalls;
      if (this.openTrasactionCalls == 1) {
         this.currentTransaction = this.pm.currentTransaction();
         if (isolationLevel != null) {
            this.currentTransaction.setIsolationLevel(isolationLevel);
         }

         this.currentTransaction.begin();
         this.transactionStatus = ObjectStore.TXN_STATUS.OPEN;
      } else {
         if (this.currentTransaction == null || !this.currentTransaction.isActive()) {
            throw new RuntimeException("openTransaction called in an interior transaction scope, but currentTransaction is not active.");
         }

         if (isolationLevel != null && !isolationLevel.equals(this.currentTransaction.getIsolationLevel())) {
            throw new RuntimeException("Can not set isolation level on an open transaction");
         }
      }

      boolean result = this.currentTransaction.isActive();
      this.debugLog("Open transaction: count = " + this.openTrasactionCalls + ", isActive = " + result + ", isolationLevel = " + this.currentTransaction.getIsolationLevel());
      return result;
   }

   public long updateParameterWithExpectedValue(final Table table, final String key, final String expectedValue, final String newValue) throws MetaException, NoSuchObjectException {
      return (Long)(new GetHelper(table.getDbName(), table.getTableName(), true, false) {
         protected String describeResult() {
            return "Affected rows";
         }

         protected Long getSqlResult(GetHelper ctx) throws MetaException {
            return ObjectStore.this.directSql.updateTableParam(table, key, expectedValue, newValue);
         }

         protected Long getJdoResult(GetHelper ctx) throws MetaException, NoSuchObjectException {
            throw new UnsupportedOperationException("Cannot update parameter with JDO, make sure direct SQL is enabled");
         }
      }).run(false);
   }

   public boolean commitTransaction() {
      if (ObjectStore.TXN_STATUS.ROLLBACK == this.transactionStatus) {
         this.debugLog("Commit transaction: rollback");
         return false;
      } else if (this.openTrasactionCalls <= 0) {
         RuntimeException e = new RuntimeException("commitTransaction was called but openTransactionCalls = " + this.openTrasactionCalls + ". This probably indicates that there are unbalanced calls to openTransaction/commitTransaction");
         LOG.error("Unbalanced calls to open/commit Transaction", e);
         throw e;
      } else if (!this.currentTransaction.isActive()) {
         RuntimeException e = new RuntimeException("commitTransaction was called but openTransactionCalls = " + this.openTrasactionCalls + ". This probably indicates that there are unbalanced calls to openTransaction/commitTransaction");
         LOG.error("Unbalanced calls to open/commit Transaction", e);
         throw e;
      } else {
         --this.openTrasactionCalls;
         this.debugLog("Commit transaction: count = " + this.openTrasactionCalls + ", isactive " + this.currentTransaction.isActive());
         if (this.openTrasactionCalls == 0 && this.currentTransaction.isActive()) {
            this.transactionStatus = ObjectStore.TXN_STATUS.COMMITED;
            this.currentTransaction.commit();
         }

         return true;
      }
   }

   public boolean isActiveTransaction() {
      return this.currentTransaction == null ? false : this.currentTransaction.isActive();
   }

   public void rollbackTransaction() {
      if (this.openTrasactionCalls < 1) {
         this.debugLog("rolling back transaction: no open transactions: " + this.openTrasactionCalls);
      } else {
         this.debugLog("Rollback transaction, isActive: " + this.currentTransaction.isActive());

         try {
            if (this.currentTransaction.isActive() && this.transactionStatus != ObjectStore.TXN_STATUS.ROLLBACK) {
               this.currentTransaction.rollback();
            }
         } finally {
            this.openTrasactionCalls = 0;
            this.transactionStatus = ObjectStore.TXN_STATUS.ROLLBACK;
            this.pm.evictAll();
         }

      }
   }

   public void createDatabase(Database db) throws InvalidObjectException, MetaException {
      boolean commited = false;
      MDatabase mdb = new MDatabase();
      mdb.setName(db.getName().toLowerCase());
      mdb.setLocationUri(db.getLocationUri());
      mdb.setDescription(db.getDescription());
      mdb.setParameters(db.getParameters());
      mdb.setOwnerName(db.getOwnerName());
      PrincipalType ownerType = db.getOwnerType();
      mdb.setOwnerType(null == ownerType ? PrincipalType.USER.name() : ownerType.name());

      try {
         this.openTransaction();
         this.pm.makePersistent(mdb);
         commited = this.commitTransaction();
      } finally {
         if (!commited) {
            this.rollbackTransaction();
         }

      }

   }

   private MDatabase getMDatabase(String name) throws NoSuchObjectException {
      MDatabase mdb = null;
      boolean commited = false;
      Query query = null;

      try {
         this.openTransaction();
         name = HiveStringUtils.normalizeIdentifier(name);
         query = this.pm.newQuery(MDatabase.class, "name == dbname");
         query.declareParameters("java.lang.String dbname");
         query.setUnique(true);
         mdb = (MDatabase)query.execute(name);
         this.pm.retrieve(mdb);
         commited = this.commitTransaction();
      } finally {
         this.rollbackAndCleanup(commited, query);
      }

      if (mdb == null) {
         throw new NoSuchObjectException("There is no database named " + name);
      } else {
         return mdb;
      }
   }

   public Database getDatabase(String name) throws NoSuchObjectException {
      MetaException ex = null;
      Database db = null;

      try {
         db = this.getDatabaseInternal(name);
      } catch (MetaException e) {
         ex = e;
      }

      if (db == null) {
         LOG.warn("Failed to get database " + name + ", returning NoSuchObjectException", ex);
         throw new NoSuchObjectException(name + (ex == null ? "" : ": " + ex.getMessage()));
      } else {
         return db;
      }
   }

   public Database getDatabaseInternal(String name) throws MetaException, NoSuchObjectException {
      return (Database)(new GetDbHelper(name, (String)null, true, true) {
         protected Database getSqlResult(GetHelper ctx) throws MetaException {
            return ObjectStore.this.directSql.getDatabase(this.dbName);
         }

         protected Database getJdoResult(GetHelper ctx) throws MetaException, NoSuchObjectException {
            return ObjectStore.this.getJDODatabase(this.dbName);
         }
      }).run(false);
   }

   public Database getJDODatabase(String name) throws NoSuchObjectException {
      MDatabase mdb = null;
      boolean commited = false;

      try {
         this.openTransaction();
         mdb = this.getMDatabase(name);
         commited = this.commitTransaction();
      } finally {
         if (!commited) {
            this.rollbackTransaction();
         }

      }

      Database db = new Database();
      db.setName(mdb.getName());
      db.setDescription(mdb.getDescription());
      db.setLocationUri(mdb.getLocationUri());
      db.setParameters(this.convertMap(mdb.getParameters()));
      db.setOwnerName(mdb.getOwnerName());
      String type = mdb.getOwnerType();
      db.setOwnerType(null != type && !type.trim().isEmpty() ? PrincipalType.valueOf(type) : null);
      return db;
   }

   public boolean alterDatabase(String dbName, Database db) throws MetaException, NoSuchObjectException {
      MDatabase mdb = null;
      boolean committed = false;

      try {
         mdb = this.getMDatabase(dbName);
         mdb.setParameters(db.getParameters());
         mdb.setOwnerName(db.getOwnerName());
         if (db.getOwnerType() != null) {
            mdb.setOwnerType(db.getOwnerType().name());
         }

         this.openTransaction();
         this.pm.makePersistent(mdb);
         committed = this.commitTransaction();
      } finally {
         if (!committed) {
            this.rollbackTransaction();
            return false;
         }

      }

      return true;
   }

   public boolean dropDatabase(String dbname) throws NoSuchObjectException, MetaException {
      boolean success = false;
      LOG.info("Dropping database " + dbname + " along with all tables");
      dbname = HiveStringUtils.normalizeIdentifier(dbname);
      QueryWrapper queryWrapper = new QueryWrapper();

      try {
         this.openTransaction();
         MDatabase db = this.getMDatabase(dbname);
         this.pm.retrieve(db);
         if (db != null) {
            List<MDBPrivilege> dbGrants = this.listDatabaseGrants(dbname, queryWrapper);
            if (dbGrants != null && dbGrants.size() > 0) {
               this.pm.deletePersistentAll(dbGrants);
            }

            this.pm.deletePersistent(db);
         }

         success = this.commitTransaction();
      } finally {
         this.rollbackAndCleanup(success, queryWrapper);
      }

      return success;
   }

   public List getDatabases(String pattern) throws MetaException {
      if (pattern != null && !pattern.equals("*")) {
         boolean commited = false;
         List<String> databases = null;
         Query query = null;

         try {
            this.openTransaction();
            String[] subpatterns = pattern.trim().split("\\|");
            StringBuilder filterBuilder = new StringBuilder();
            List<String> parameterVals = new ArrayList(subpatterns.length);
            this.appendPatternCondition(filterBuilder, "name", subpatterns, parameterVals);
            query = this.pm.newQuery(MDatabase.class, filterBuilder.toString());
            query.setResult("name");
            query.setOrdering("name ascending");
            Collection names = (Collection)query.executeWithArray(parameterVals.toArray(new String[parameterVals.size()]));
            databases = new ArrayList();
            Iterator i = names.iterator();

            while(i.hasNext()) {
               databases.add((String)i.next());
            }

            commited = this.commitTransaction();
         } finally {
            this.rollbackAndCleanup(commited, query);
         }

         return databases;
      } else {
         return this.getAllDatabases();
      }
   }

   public List getAllDatabases() throws MetaException {
      boolean commited = false;
      List<String> databases = null;
      String queryStr = "select name from org.apache.hadoop.hive.metastore.model.MDatabase";
      Query query = null;
      this.openTransaction();

      try {
         query = this.pm.newQuery(queryStr);
         query.setResult("name");
         databases = new ArrayList((Collection)query.execute());
         commited = this.commitTransaction();
      } finally {
         this.rollbackAndCleanup(commited, query);
      }

      Collections.sort(databases);
      return databases;
   }

   private MType getMType(Type type) {
      List<MFieldSchema> fields = new ArrayList();
      if (type.getFields() != null) {
         for(FieldSchema field : type.getFields()) {
            fields.add(new MFieldSchema(field.getName(), field.getType(), field.getComment()));
         }
      }

      return new MType(type.getName(), type.getType1(), type.getType2(), fields);
   }

   private Type getType(MType mtype) {
      List<FieldSchema> fields = new ArrayList();
      if (mtype.getFields() != null) {
         for(MFieldSchema field : mtype.getFields()) {
            fields.add(new FieldSchema(field.getName(), field.getType(), field.getComment()));
         }
      }

      Type ret = new Type();
      ret.setName(mtype.getName());
      ret.setType1(mtype.getType1());
      ret.setType2(mtype.getType2());
      ret.setFields(fields);
      return ret;
   }

   public boolean createType(Type type) {
      boolean success = false;
      MType mtype = this.getMType(type);
      boolean commited = false;

      try {
         this.openTransaction();
         this.pm.makePersistent(mtype);
         commited = this.commitTransaction();
         success = true;
      } finally {
         if (!commited) {
            this.rollbackTransaction();
         }

      }

      return success;
   }

   public Type getType(String typeName) {
      Type type = null;
      boolean commited = false;
      Query query = null;

      try {
         this.openTransaction();
         query = this.pm.newQuery(MType.class, "name == typeName");
         query.declareParameters("java.lang.String typeName");
         query.setUnique(true);
         MType mtype = (MType)query.execute(typeName.trim());
         this.pm.retrieve(type);
         if (mtype != null) {
            type = this.getType(mtype);
         }

         commited = this.commitTransaction();
      } finally {
         this.rollbackAndCleanup(commited, query);
      }

      return type;
   }

   public boolean dropType(String typeName) {
      boolean success = false;
      Query query = null;

      try {
         this.openTransaction();
         query = this.pm.newQuery(MType.class, "name == typeName");
         query.declareParameters("java.lang.String typeName");
         query.setUnique(true);
         MType type = (MType)query.execute(typeName.trim());
         this.pm.retrieve(type);
         if (type != null) {
            this.pm.deletePersistent(type);
         }

         success = this.commitTransaction();
      } catch (JDOObjectNotFoundException e) {
         success = this.commitTransaction();
         LOG.debug("type not found " + typeName, e);
      } finally {
         this.rollbackAndCleanup(success, query);
      }

      return success;
   }

   public void createTableWithConstraints(Table tbl, List primaryKeys, List foreignKeys) throws InvalidObjectException, MetaException {
      boolean success = false;

      try {
         this.openTransaction();
         this.createTable(tbl);
         this.addPrimaryKeys(primaryKeys, false);
         this.addForeignKeys(foreignKeys, false);
         success = this.commitTransaction();
      } finally {
         if (!success) {
            this.rollbackTransaction();
         }

      }

   }

   public void createTable(Table tbl) throws InvalidObjectException, MetaException {
      boolean commited = false;

      try {
         this.openTransaction();
         MTable mtbl = this.convertToMTable(tbl);
         this.pm.makePersistent(mtbl);
         PrincipalPrivilegeSet principalPrivs = tbl.getPrivileges();
         List<Object> toPersistPrivObjs = new ArrayList();
         if (principalPrivs != null) {
            int now = (int)(System.currentTimeMillis() / 1000L);
            Map<String, List<PrivilegeGrantInfo>> userPrivs = principalPrivs.getUserPrivileges();
            this.putPersistentPrivObjects(mtbl, toPersistPrivObjs, now, userPrivs, PrincipalType.USER);
            Map<String, List<PrivilegeGrantInfo>> groupPrivs = principalPrivs.getGroupPrivileges();
            this.putPersistentPrivObjects(mtbl, toPersistPrivObjs, now, groupPrivs, PrincipalType.GROUP);
            Map<String, List<PrivilegeGrantInfo>> rolePrivs = principalPrivs.getRolePrivileges();
            this.putPersistentPrivObjects(mtbl, toPersistPrivObjs, now, rolePrivs, PrincipalType.ROLE);
         }

         this.pm.makePersistentAll(toPersistPrivObjs);
         commited = this.commitTransaction();
      } finally {
         if (!commited) {
            this.rollbackTransaction();
         }

      }

   }

   private void putPersistentPrivObjects(MTable mtbl, List toPersistPrivObjs, int now, Map privMap, PrincipalType type) {
      if (privMap != null) {
         for(Map.Entry entry : privMap.entrySet()) {
            String principalName = (String)entry.getKey();
            List<PrivilegeGrantInfo> privs = (List)entry.getValue();

            for(int i = 0; i < privs.size(); ++i) {
               PrivilegeGrantInfo priv = (PrivilegeGrantInfo)privs.get(i);
               if (priv != null) {
                  MTablePrivilege mTblSec = new MTablePrivilege(principalName, type.toString(), mtbl, priv.getPrivilege(), now, priv.getGrantor(), priv.getGrantorType().toString(), priv.isGrantOption());
                  toPersistPrivObjs.add(mTblSec);
               }
            }
         }
      }

   }

   public boolean dropTable(String dbName, String tableName) throws MetaException, NoSuchObjectException, InvalidObjectException, InvalidInputException {
      boolean success = false;

      try {
         this.openTransaction();
         MTable tbl = this.getMTable(dbName, tableName);
         this.pm.retrieve(tbl);
         if (tbl != null) {
            List<MTablePrivilege> tabGrants = this.listAllTableGrants(dbName, tableName);
            if (tabGrants != null && tabGrants.size() > 0) {
               this.pm.deletePersistentAll(tabGrants);
            }

            List<MTableColumnPrivilege> tblColGrants = this.listTableAllColumnGrants(dbName, tableName);
            if (tblColGrants != null && tblColGrants.size() > 0) {
               this.pm.deletePersistentAll(tblColGrants);
            }

            List<MPartitionPrivilege> partGrants = this.listTableAllPartitionGrants(dbName, tableName);
            if (partGrants != null && partGrants.size() > 0) {
               this.pm.deletePersistentAll(partGrants);
            }

            List<MPartitionColumnPrivilege> partColGrants = this.listTableAllPartitionColumnGrants(dbName, tableName);
            if (partColGrants != null && partColGrants.size() > 0) {
               this.pm.deletePersistentAll(partColGrants);
            }

            try {
               this.deleteTableColumnStatistics(dbName, tableName, (String)null);
            } catch (NoSuchObjectException var13) {
               LOG.info("Found no table level column statistics associated with db " + dbName + " table " + tableName + " record to delete");
            }

            List<MConstraint> tabConstraints = this.listAllTableConstraintsWithOptionalConstraintName(dbName, tableName, (String)null);
            if (tabConstraints != null && tabConstraints.size() > 0) {
               this.pm.deletePersistentAll(tabConstraints);
            }

            this.preDropStorageDescriptor(tbl.getSd());
            this.pm.deletePersistentAll(new Object[]{tbl});
         }

         success = this.commitTransaction();
      } finally {
         if (!success) {
            this.rollbackTransaction();
         }

      }

      return success;
   }

   private List listAllTableConstraintsWithOptionalConstraintName(String dbName, String tableName, String constraintname) {
      List<MConstraint> mConstraints = null;
      List<String> constraintNames = new ArrayList();
      Query query = null;

      try {
         query = this.pm.newQuery("select constraintName from org.apache.hadoop.hive.metastore.model.MConstraint  where ((parentTable.tableName == ptblname && parentTable.database.name == pdbname) || (childTable != null && childTable.tableName == ctblname && childTable.database.name == cdbname)) " + (constraintname != null ? " && constraintName == constraintname" : ""));
         query.declareParameters("java.lang.String ptblname, java.lang.String pdbname,java.lang.String ctblname, java.lang.String cdbname" + (constraintname != null ? ", java.lang.String constraintname" : ""));

         for(String currName : constraintname != null ? (Collection)query.executeWithArray(new Object[]{tableName, dbName, tableName, dbName, constraintname}) : (Collection)query.executeWithArray(new Object[]{tableName, dbName, tableName, dbName})) {
            constraintNames.add(currName);
         }

         query = this.pm.newQuery(MConstraint.class);
         query.setFilter("param.contains(constraintName)");
         query.declareParameters("java.util.Collection param");
         Collection<?> constraints = (Collection)query.execute(constraintNames);
         mConstraints = new ArrayList();

         for(MConstraint currConstraint : constraints) {
            mConstraints.add(currConstraint);
         }
      } finally {
         if (query != null) {
            query.closeAll();
         }

      }

      return mConstraints;
   }

   public Table getTable(String dbName, String tableName) throws MetaException {
      boolean commited = false;
      Table tbl = null;

      try {
         this.openTransaction();
         tbl = this.convertToTable(this.getMTable(dbName, tableName));
         commited = this.commitTransaction();
      } finally {
         if (!commited) {
            this.rollbackTransaction();
         }

      }

      return tbl;
   }

   public List getTables(String dbName, String pattern) throws MetaException {
      return this.getTables(dbName, pattern, (TableType)null);
   }

   public List getTables(String dbName, String pattern, TableType tableType) throws MetaException {
      boolean commited = false;
      Query query = null;
      List<String> tbls = null;

      try {
         this.openTransaction();
         dbName = HiveStringUtils.normalizeIdentifier(dbName);
         List<String> parameterVals = new ArrayList();
         StringBuilder filterBuilder = new StringBuilder();
         this.appendSimpleCondition(filterBuilder, "database.name", new String[]{dbName}, parameterVals);
         if (pattern != null) {
            this.appendPatternCondition(filterBuilder, "tableName", pattern, parameterVals);
         }

         if (tableType != null) {
            this.appendPatternCondition(filterBuilder, "tableType", new String[]{tableType.toString()}, parameterVals);
         }

         query = this.pm.newQuery(MTable.class, filterBuilder.toString());
         query.setResult("tableName");
         query.setOrdering("tableName ascending");
         Collection names = (Collection)query.executeWithArray(parameterVals.toArray(new String[parameterVals.size()]));
         tbls = new ArrayList();
         Iterator i = names.iterator();

         while(i.hasNext()) {
            tbls.add((String)i.next());
         }

         commited = this.commitTransaction();
      } finally {
         this.rollbackAndCleanup(commited, query);
      }

      return tbls;
   }

   public int getDatabaseCount() throws MetaException {
      return this.getObjectCount("name", MDatabase.class.getName());
   }

   public int getPartitionCount() throws MetaException {
      return this.getObjectCount("partitionName", MPartition.class.getName());
   }

   public int getTableCount() throws MetaException {
      return this.getObjectCount("tableName", MTable.class.getName());
   }

   private int getObjectCount(String fieldName, String objName) {
      Long result = 0L;
      boolean commited = false;
      Query query = null;

      try {
         this.openTransaction();
         String queryStr = "select count(" + fieldName + ") from " + objName;
         query = this.pm.newQuery(queryStr);
         result = (Long)query.execute();
         commited = this.commitTransaction();
      } finally {
         this.rollbackAndCleanup(commited, query);
      }

      return result.intValue();
   }

   public List getTableMeta(String dbNames, String tableNames, List tableTypes) throws MetaException {
      boolean commited = false;
      Query query = null;
      List<TableMeta> metas = new ArrayList();

      try {
         this.openTransaction();
         StringBuilder filterBuilder = new StringBuilder();
         List<String> parameterVals = new ArrayList();
         if (dbNames != null && !dbNames.equals("*")) {
            this.appendPatternCondition(filterBuilder, "database.name", dbNames, parameterVals);
         }

         if (tableNames != null && !tableNames.equals("*")) {
            this.appendPatternCondition(filterBuilder, "tableName", tableNames, parameterVals);
         }

         if (tableTypes != null && !tableTypes.isEmpty()) {
            this.appendSimpleCondition(filterBuilder, "tableType", (String[])tableTypes.toArray(new String[0]), parameterVals);
         }

         query = this.pm.newQuery(MTable.class, filterBuilder.toString());

         for(MTable table : (Collection)query.executeWithArray(parameterVals.toArray(new String[parameterVals.size()]))) {
            TableMeta metaData = new TableMeta(table.getDatabase().getName(), table.getTableName(), table.getTableType());
            metaData.setComments((String)table.getParameters().get("comment"));
            metas.add(metaData);
         }

         commited = this.commitTransaction();
      } finally {
         this.rollbackAndCleanup(commited, query);
      }

      return metas;
   }

   private StringBuilder appendPatternCondition(StringBuilder filterBuilder, String fieldName, String[] elements, List parameterVals) {
      return this.appendCondition(filterBuilder, fieldName, elements, true, parameterVals);
   }

   private StringBuilder appendPatternCondition(StringBuilder builder, String fieldName, String elements, List parameters) {
      elements = HiveStringUtils.normalizeIdentifier(elements);
      return this.appendCondition(builder, fieldName, elements.split("\\|"), true, parameters);
   }

   private StringBuilder appendSimpleCondition(StringBuilder builder, String fieldName, String[] elements, List parameters) {
      return this.appendCondition(builder, fieldName, elements, false, parameters);
   }

   private StringBuilder appendCondition(StringBuilder builder, String fieldName, String[] elements, boolean pattern, List parameters) {
      if (builder.length() > 0) {
         builder.append(" && ");
      }

      builder.append(" (");
      int length = builder.length();

      for(String element : elements) {
         if (pattern) {
            element = "(?i)" + element.replaceAll("\\*", ".*");
         }

         parameters.add(element);
         if (builder.length() > length) {
            builder.append(" || ");
         }

         builder.append(fieldName);
         if (pattern) {
            builder.append(".matches(").append(":param").append(parameters.size()).append(")");
         } else {
            builder.append(" == ").append(":param").append(parameters.size());
         }
      }

      builder.append(" )");
      return builder;
   }

   public List getAllTables(String dbName) throws MetaException {
      return this.getTables(dbName, ".*");
   }

   private AttachedMTableInfo getMTable(String db, String table, boolean retrieveCD) {
      AttachedMTableInfo nmtbl = new AttachedMTableInfo();
      MTable mtbl = null;
      boolean commited = false;
      Query query = null;

      try {
         this.openTransaction();
         db = HiveStringUtils.normalizeIdentifier(db);
         table = HiveStringUtils.normalizeIdentifier(table);
         query = this.pm.newQuery(MTable.class, "tableName == table && database.name == db");
         query.declareParameters("java.lang.String table, java.lang.String db");
         query.setUnique(true);
         mtbl = (MTable)query.execute(table, db);
         this.pm.retrieve(mtbl);
         if (mtbl != null && retrieveCD) {
            this.pm.retrieve(mtbl.getSd());
            this.pm.retrieveAll(new Object[]{mtbl.getSd().getCD()});
            nmtbl.mcd = mtbl.getSd().getCD();
         }

         commited = this.commitTransaction();
      } finally {
         this.rollbackAndCleanup(commited, query);
      }

      nmtbl.mtbl = mtbl;
      return nmtbl;
   }

   private MTable getMTable(String db, String table) {
      AttachedMTableInfo nmtbl = this.getMTable(db, table, false);
      return nmtbl.mtbl;
   }

   public List getTableObjectsByName(String db, List tbl_names) throws MetaException, UnknownDBException {
      List<Table> tables = new ArrayList();
      boolean committed = false;
      Query dbExistsQuery = null;
      Query query = null;

      try {
         this.openTransaction();
         db = HiveStringUtils.normalizeIdentifier(db);
         dbExistsQuery = this.pm.newQuery(MDatabase.class, "name == db");
         dbExistsQuery.declareParameters("java.lang.String db");
         dbExistsQuery.setUnique(true);
         dbExistsQuery.setResult("name");
         String dbNameIfExists = (String)dbExistsQuery.execute(db);
         if (dbNameIfExists == null || dbNameIfExists.isEmpty()) {
            throw new UnknownDBException("Could not find database " + db);
         }

         List<String> lowered_tbl_names = new ArrayList();

         for(String t : tbl_names) {
            lowered_tbl_names.add(HiveStringUtils.normalizeIdentifier(t));
         }

         query = this.pm.newQuery(MTable.class);
         query.setFilter("database.name == db && tbl_names.contains(tableName)");
         query.declareParameters("java.lang.String db, java.util.Collection tbl_names");
         Collection mtables = (Collection)query.execute(db, lowered_tbl_names);
         Iterator iter = mtables.iterator();

         while(iter.hasNext()) {
            tables.add(this.convertToTable((MTable)iter.next()));
         }

         committed = this.commitTransaction();
      } finally {
         this.rollbackAndCleanup(committed, query);
         if (dbExistsQuery != null) {
            dbExistsQuery.closeAll();
         }

      }

      return tables;
   }

   private List convertList(List dnList) {
      return dnList == null ? null : Lists.newArrayList(dnList);
   }

   private Map convertMap(Map dnMap) {
      return MetaStoreUtils.trimMapNulls(dnMap, HiveConf.getBoolVar(this.getConf(), ConfVars.METASTORE_ORM_RETRIEVE_MAPNULLS_AS_EMPTY_STRINGS));
   }

   private Table convertToTable(MTable mtbl) throws MetaException {
      if (mtbl == null) {
         return null;
      } else {
         String tableType = mtbl.getTableType();
         if (tableType == null) {
            if (mtbl.getViewOriginalText() != null) {
               tableType = TableType.VIRTUAL_VIEW.toString();
            } else if ("TRUE".equals(mtbl.getParameters().get("EXTERNAL"))) {
               tableType = TableType.EXTERNAL_TABLE.toString();
            } else {
               tableType = TableType.MANAGED_TABLE.toString();
            }
         }

         Table table = new Table(mtbl.getTableName(), mtbl.getDatabase().getName(), mtbl.getOwner(), mtbl.getCreateTime(), mtbl.getLastAccessTime(), mtbl.getRetention(), this.convertToStorageDescriptor(mtbl.getSd()), this.convertToFieldSchemas(mtbl.getPartitionKeys()), this.convertMap(mtbl.getParameters()), mtbl.getViewOriginalText(), mtbl.getViewExpandedText(), tableType);
         table.setRewriteEnabled(mtbl.isRewriteEnabled());
         return table;
      }
   }

   private MTable convertToMTable(Table tbl) throws InvalidObjectException, MetaException {
      if (tbl == null) {
         return null;
      } else {
         MDatabase mdb = null;

         try {
            mdb = this.getMDatabase(tbl.getDbName());
         } catch (NoSuchObjectException e) {
            LOG.error(org.apache.hadoop.util.StringUtils.stringifyException(e));
            throw new InvalidObjectException("Database " + tbl.getDbName() + " doesn't exist.");
         }

         String tableType = tbl.getTableType();
         boolean isExternal = "TRUE".equals(tbl.getParameters().get("EXTERNAL"));
         if (TableType.MANAGED_TABLE.toString().equals(tableType) && isExternal) {
            tableType = TableType.EXTERNAL_TABLE.toString();
         }

         if (TableType.EXTERNAL_TABLE.toString().equals(tableType) && !isExternal) {
            tableType = TableType.MANAGED_TABLE.toString();
         }

         return new MTable(HiveStringUtils.normalizeIdentifier(tbl.getTableName()), mdb, this.convertToMStorageDescriptor(tbl.getSd()), tbl.getOwner(), tbl.getCreateTime(), tbl.getLastAccessTime(), tbl.getRetention(), this.convertToMFieldSchemas(tbl.getPartitionKeys()), tbl.getParameters(), tbl.getViewOriginalText(), tbl.getViewExpandedText(), tbl.isRewriteEnabled(), tableType);
      }
   }

   private List convertToMFieldSchemas(List keys) {
      List<MFieldSchema> mkeys = null;
      if (keys != null) {
         mkeys = new ArrayList(keys.size());

         for(FieldSchema part : keys) {
            mkeys.add(new MFieldSchema(part.getName().toLowerCase(), part.getType(), part.getComment()));
         }
      }

      return mkeys;
   }

   private List convertToFieldSchemas(List mkeys) {
      List<FieldSchema> keys = null;
      if (mkeys != null) {
         keys = new ArrayList(mkeys.size());

         for(MFieldSchema part : mkeys) {
            keys.add(new FieldSchema(part.getName(), part.getType(), part.getComment()));
         }
      }

      return keys;
   }

   private List convertToMOrders(List keys) {
      List<MOrder> mkeys = null;
      if (keys != null) {
         mkeys = new ArrayList(keys.size());

         for(Order part : keys) {
            mkeys.add(new MOrder(HiveStringUtils.normalizeIdentifier(part.getCol()), part.getOrder()));
         }
      }

      return mkeys;
   }

   private List convertToOrders(List mkeys) {
      List<Order> keys = null;
      if (mkeys != null) {
         keys = new ArrayList(mkeys.size());

         for(MOrder part : mkeys) {
            keys.add(new Order(part.getCol(), part.getOrder()));
         }
      }

      return keys;
   }

   private SerDeInfo convertToSerDeInfo(MSerDeInfo ms) throws MetaException {
      if (ms == null) {
         throw new MetaException("Invalid SerDeInfo object");
      } else {
         return new SerDeInfo(ms.getName(), ms.getSerializationLib(), this.convertMap(ms.getParameters()));
      }
   }

   private MSerDeInfo convertToMSerDeInfo(SerDeInfo ms) throws MetaException {
      if (ms == null) {
         throw new MetaException("Invalid SerDeInfo object");
      } else {
         return new MSerDeInfo(ms.getName(), ms.getSerializationLib(), ms.getParameters());
      }
   }

   private MColumnDescriptor createNewMColumnDescriptor(List cols) {
      return cols == null ? null : new MColumnDescriptor(cols);
   }

   private StorageDescriptor convertToStorageDescriptor(MStorageDescriptor msd, boolean noFS) throws MetaException {
      if (msd == null) {
         return null;
      } else {
         List<MFieldSchema> mFieldSchemas = msd.getCD() == null ? null : msd.getCD().getCols();
         StorageDescriptor sd = new StorageDescriptor(noFS ? null : this.convertToFieldSchemas(mFieldSchemas), msd.getLocation(), msd.getInputFormat(), msd.getOutputFormat(), msd.isCompressed(), msd.getNumBuckets(), this.convertToSerDeInfo(msd.getSerDeInfo()), this.convertList(msd.getBucketCols()), this.convertToOrders(msd.getSortCols()), this.convertMap(msd.getParameters()));
         SkewedInfo skewedInfo = new SkewedInfo(this.convertList(msd.getSkewedColNames()), this.convertToSkewedValues(msd.getSkewedColValues()), this.covertToSkewedMap(msd.getSkewedColValueLocationMaps()));
         sd.setSkewedInfo(skewedInfo);
         sd.setStoredAsSubDirectories(msd.isStoredAsSubDirectories());
         return sd;
      }
   }

   private StorageDescriptor convertToStorageDescriptor(MStorageDescriptor msd) throws MetaException {
      return this.convertToStorageDescriptor(msd, false);
   }

   private List convertToSkewedValues(List mLists) {
      List<List<String>> lists = null;
      if (mLists != null) {
         lists = new ArrayList(mLists.size());

         for(MStringList element : mLists) {
            lists.add(new ArrayList(element.getInternalList()));
         }
      }

      return lists;
   }

   private List convertToMStringLists(List mLists) {
      List<MStringList> lists = null;
      if (null != mLists) {
         lists = new ArrayList();

         for(List mList : mLists) {
            lists.add(new MStringList(mList));
         }
      }

      return lists;
   }

   private Map covertToSkewedMap(Map mMap) {
      Map<List<String>, String> map = null;
      if (mMap != null) {
         map = new HashMap(mMap.size());

         for(MStringList key : mMap.keySet()) {
            map.put(new ArrayList(key.getInternalList()), mMap.get(key));
         }
      }

      return map;
   }

   private Map covertToMapMStringList(Map mMap) {
      Map<MStringList, String> map = null;
      if (mMap != null) {
         map = new HashMap(mMap.size());

         for(List key : mMap.keySet()) {
            map.put(new MStringList(key), mMap.get(key));
         }
      }

      return map;
   }

   private MStorageDescriptor convertToMStorageDescriptor(StorageDescriptor sd) throws MetaException {
      if (sd == null) {
         return null;
      } else {
         MColumnDescriptor mcd = this.createNewMColumnDescriptor(this.convertToMFieldSchemas(sd.getCols()));
         return this.convertToMStorageDescriptor(sd, mcd);
      }
   }

   private MStorageDescriptor convertToMStorageDescriptor(StorageDescriptor sd, MColumnDescriptor mcd) throws MetaException {
      return sd == null ? null : new MStorageDescriptor(mcd, sd.getLocation(), sd.getInputFormat(), sd.getOutputFormat(), sd.isCompressed(), sd.getNumBuckets(), this.convertToMSerDeInfo(sd.getSerdeInfo()), sd.getBucketCols(), this.convertToMOrders(sd.getSortCols()), sd.getParameters(), null == sd.getSkewedInfo() ? null : sd.getSkewedInfo().getSkewedColNames(), this.convertToMStringLists(null == sd.getSkewedInfo() ? null : sd.getSkewedInfo().getSkewedColValues()), this.covertToMapMStringList(null == sd.getSkewedInfo() ? null : sd.getSkewedInfo().getSkewedColValueLocationMaps()), sd.isStoredAsSubDirectories());
   }

   public boolean addPartitions(String dbName, String tblName, List parts) throws InvalidObjectException, MetaException {
      boolean success = false;
      this.openTransaction();

      try {
         List<MTablePrivilege> tabGrants = null;
         List<MTableColumnPrivilege> tabColumnGrants = null;
         MTable table = this.getMTable(dbName, tblName);
         if ("TRUE".equalsIgnoreCase((String)table.getParameters().get("PARTITION_LEVEL_PRIVILEGE"))) {
            tabGrants = this.listAllTableGrants(dbName, tblName);
            tabColumnGrants = this.listTableAllColumnGrants(dbName, tblName);
         }

         List<Object> toPersist = new ArrayList();

         for(Partition part : parts) {
            if (!part.getTableName().equals(tblName) || !part.getDbName().equals(dbName)) {
               throw new MetaException("Partition does not belong to target table " + dbName + "." + tblName + ": " + part);
            }

            MPartition mpart = this.convertToMPart(part, true);
            toPersist.add(mpart);
            int now = (int)(System.currentTimeMillis() / 1000L);
            if (tabGrants != null) {
               for(MTablePrivilege tab : tabGrants) {
                  toPersist.add(new MPartitionPrivilege(tab.getPrincipalName(), tab.getPrincipalType(), mpart, tab.getPrivilege(), now, tab.getGrantor(), tab.getGrantorType(), tab.getGrantOption()));
               }
            }

            if (tabColumnGrants != null) {
               for(MTableColumnPrivilege col : tabColumnGrants) {
                  toPersist.add(new MPartitionColumnPrivilege(col.getPrincipalName(), col.getPrincipalType(), mpart, col.getColumnName(), col.getPrivilege(), now, col.getGrantor(), col.getGrantorType(), col.getGrantOption()));
               }
            }
         }

         if (toPersist.size() > 0) {
            this.pm.makePersistentAll(toPersist);
         }

         success = this.commitTransaction();
      } finally {
         if (!success) {
            this.rollbackTransaction();
         }

      }

      return success;
   }

   private boolean isValidPartition(Partition part, boolean ifNotExists) throws MetaException {
      MetaStoreUtils.validatePartitionNameCharacters(part.getValues(), this.partitionValidationPattern);
      boolean doesExist = this.doesPartitionExist(part.getDbName(), part.getTableName(), part.getValues());
      if (doesExist && !ifNotExists) {
         throw new MetaException("Partition already exists: " + part);
      } else {
         return !doesExist;
      }
   }

   public boolean addPartitions(String dbName, String tblName, PartitionSpecProxy partitionSpec, boolean ifNotExists) throws InvalidObjectException, MetaException {
      boolean success = false;
      this.openTransaction();

      try {
         List<MTablePrivilege> tabGrants = null;
         List<MTableColumnPrivilege> tabColumnGrants = null;
         MTable table = this.getMTable(dbName, tblName);
         if ("TRUE".equalsIgnoreCase((String)table.getParameters().get("PARTITION_LEVEL_PRIVILEGE"))) {
            tabGrants = this.listAllTableGrants(dbName, tblName);
            tabColumnGrants = this.listTableAllColumnGrants(dbName, tblName);
         }

         if (!partitionSpec.getTableName().equals(tblName) || !partitionSpec.getDbName().equals(dbName)) {
            throw new MetaException("Partition does not belong to target table " + dbName + "." + tblName + ": " + partitionSpec);
         }

         PartitionSpecProxy.PartitionIterator iterator = partitionSpec.getPartitionIterator();
         int now = (int)(System.currentTimeMillis() / 1000L);

         while(iterator.hasNext()) {
            Partition part = (Partition)iterator.next();
            if (this.isValidPartition(part, ifNotExists)) {
               MPartition mpart = this.convertToMPart(part, true);
               this.pm.makePersistent(mpart);
               if (tabGrants != null) {
                  for(MTablePrivilege tab : tabGrants) {
                     this.pm.makePersistent(new MPartitionPrivilege(tab.getPrincipalName(), tab.getPrincipalType(), mpart, tab.getPrivilege(), now, tab.getGrantor(), tab.getGrantorType(), tab.getGrantOption()));
                  }
               }

               if (tabColumnGrants != null) {
                  for(MTableColumnPrivilege col : tabColumnGrants) {
                     this.pm.makePersistent(new MPartitionColumnPrivilege(col.getPrincipalName(), col.getPrincipalType(), mpart, col.getColumnName(), col.getPrivilege(), now, col.getGrantor(), col.getGrantorType(), col.getGrantOption()));
                  }
               }
            }
         }

         success = this.commitTransaction();
      } finally {
         if (!success) {
            this.rollbackTransaction();
         }

      }

      return success;
   }

   public boolean addPartition(Partition part) throws InvalidObjectException, MetaException {
      boolean success = false;
      boolean commited = false;

      try {
         MTable table = this.getMTable(part.getDbName(), part.getTableName());
         List<MTablePrivilege> tabGrants = null;
         List<MTableColumnPrivilege> tabColumnGrants = null;
         if ("TRUE".equalsIgnoreCase((String)table.getParameters().get("PARTITION_LEVEL_PRIVILEGE"))) {
            tabGrants = this.listAllTableGrants(part.getDbName(), part.getTableName());
            tabColumnGrants = this.listTableAllColumnGrants(part.getDbName(), part.getTableName());
         }

         this.openTransaction();
         MPartition mpart = this.convertToMPart(part, true);
         this.pm.makePersistent(mpart);
         int now = (int)(System.currentTimeMillis() / 1000L);
         List<Object> toPersist = new ArrayList();
         if (tabGrants != null) {
            for(MTablePrivilege tab : tabGrants) {
               MPartitionPrivilege partGrant = new MPartitionPrivilege(tab.getPrincipalName(), tab.getPrincipalType(), mpart, tab.getPrivilege(), now, tab.getGrantor(), tab.getGrantorType(), tab.getGrantOption());
               toPersist.add(partGrant);
            }
         }

         if (tabColumnGrants != null) {
            for(MTableColumnPrivilege col : tabColumnGrants) {
               MPartitionColumnPrivilege partColumn = new MPartitionColumnPrivilege(col.getPrincipalName(), col.getPrincipalType(), mpart, col.getColumnName(), col.getPrivilege(), now, col.getGrantor(), col.getGrantorType(), col.getGrantOption());
               toPersist.add(partColumn);
            }

            if (toPersist.size() > 0) {
               this.pm.makePersistentAll(toPersist);
            }
         }

         commited = this.commitTransaction();
         success = true;
      } finally {
         if (!commited) {
            this.rollbackTransaction();
         }

      }

      return success;
   }

   public Partition getPartition(String dbName, String tableName, List part_vals) throws NoSuchObjectException, MetaException {
      this.openTransaction();
      Partition part = this.convertToPart(this.getMPartition(dbName, tableName, part_vals));
      this.commitTransaction();
      if (part == null) {
         throw new NoSuchObjectException("partition values=" + part_vals.toString());
      } else {
         part.setValues(part_vals);
         return part;
      }
   }

   private MPartition getMPartition(String dbName, String tableName, List part_vals) throws MetaException {
      List<MPartition> mparts = null;
      MPartition ret = null;
      boolean commited = false;
      Query query = null;

      Object var9;
      try {
         this.openTransaction();
         dbName = HiveStringUtils.normalizeIdentifier(dbName);
         tableName = HiveStringUtils.normalizeIdentifier(tableName);
         MTable mtbl = this.getMTable(dbName, tableName);
         if (mtbl != null) {
            String name = Warehouse.makePartName(this.convertToFieldSchemas(mtbl.getPartitionKeys()), part_vals);
            query = this.pm.newQuery(MPartition.class, "table.tableName == t1 && table.database.name == t2 && partitionName == t3");
            query.declareParameters("java.lang.String t1, java.lang.String t2, java.lang.String t3");
            mparts = (List)query.execute(tableName, dbName, name);
            this.pm.retrieveAll(mparts);
            commited = this.commitTransaction();
            if (mparts != null && mparts.size() > 0) {
               if (mparts.size() > 1) {
                  throw new MetaException("Expecting only one partition but more than one partitions are found.");
               }

               MPartition mpart = (MPartition)mparts.get(0);
               if (!name.equals(mpart.getPartitionName())) {
                  throw new MetaException("Expecting a partition with name " + name + ", but metastore is returning a partition with name " + mpart.getPartitionName() + ".");
               }

               ret = mpart;
            }

            return ret;
         }

         commited = this.commitTransaction();
         var9 = null;
      } finally {
         this.rollbackAndCleanup(commited, query);
      }

      return (MPartition)var9;
   }

   private MPartition convertToMPart(Partition part, boolean useTableCD) throws InvalidObjectException, MetaException {
      if (part == null) {
         return null;
      } else {
         MTable mt = this.getMTable(part.getDbName(), part.getTableName());
         if (mt == null) {
            throw new InvalidObjectException("Partition doesn't have a valid table or database name");
         } else {
            MStorageDescriptor msd;
            if (useTableCD && mt.getSd() != null && mt.getSd().getCD() != null && mt.getSd().getCD().getCols() != null && part.getSd() != null && this.convertToFieldSchemas(mt.getSd().getCD().getCols()).equals(part.getSd().getCols())) {
               msd = this.convertToMStorageDescriptor(part.getSd(), mt.getSd().getCD());
            } else {
               msd = this.convertToMStorageDescriptor(part.getSd());
            }

            return new MPartition(Warehouse.makePartName(this.convertToFieldSchemas(mt.getPartitionKeys()), part.getValues()), mt, part.getValues(), part.getCreateTime(), part.getLastAccessTime(), msd, part.getParameters());
         }
      }
   }

   private Partition convertToPart(MPartition mpart) throws MetaException {
      return mpart == null ? null : new Partition(this.convertList(mpart.getValues()), mpart.getTable().getDatabase().getName(), mpart.getTable().getTableName(), mpart.getCreateTime(), mpart.getLastAccessTime(), this.convertToStorageDescriptor(mpart.getSd()), this.convertMap(mpart.getParameters()));
   }

   private Partition convertToPart(String dbName, String tblName, MPartition mpart) throws MetaException {
      return mpart == null ? null : new Partition(this.convertList(mpart.getValues()), dbName, tblName, mpart.getCreateTime(), mpart.getLastAccessTime(), this.convertToStorageDescriptor(mpart.getSd(), false), this.convertMap(mpart.getParameters()));
   }

   public boolean dropPartition(String dbName, String tableName, List part_vals) throws MetaException, NoSuchObjectException, InvalidObjectException, InvalidInputException {
      boolean success = false;

      try {
         this.openTransaction();
         MPartition part = this.getMPartition(dbName, tableName, part_vals);
         this.dropPartitionCommon(part);
         success = this.commitTransaction();
      } finally {
         if (!success) {
            this.rollbackTransaction();
         }

      }

      return success;
   }

   public void dropPartitions(String dbName, String tblName, List partNames) throws MetaException, NoSuchObjectException {
      if (!partNames.isEmpty()) {
         boolean success = false;
         this.openTransaction();

         try {
            this.dropPartitionGrantsNoTxn(dbName, tblName, partNames);
            this.dropPartitionAllColumnGrantsNoTxn(dbName, tblName, partNames);
            this.dropPartitionColumnStatisticsNoTxn(dbName, tblName, partNames);

            for(MColumnDescriptor mcd : this.detachCdsFromSdsNoTxn(dbName, tblName, partNames)) {
               this.removeUnusedColumnDescriptor(mcd);
            }

            this.dropPartitionsNoTxn(dbName, tblName, partNames);
            if (!(success = this.commitTransaction())) {
               throw new MetaException("Failed to drop partitions");
            }
         } finally {
            if (!success) {
               this.rollbackTransaction();
            }

         }

      }
   }

   private boolean dropPartitionCommon(MPartition part) throws NoSuchObjectException, MetaException, InvalidObjectException, InvalidInputException {
      boolean success = false;

      try {
         this.openTransaction();
         if (part != null) {
            List<MFieldSchema> schemas = part.getTable().getPartitionKeys();
            List<String> colNames = new ArrayList();

            for(MFieldSchema col : schemas) {
               colNames.add(col.getName());
            }

            String partName = FileUtils.makePartName(colNames, part.getValues());
            List<MPartitionPrivilege> partGrants = this.listPartitionGrants(part.getTable().getDatabase().getName(), part.getTable().getTableName(), Lists.newArrayList(new String[]{partName}));
            if (partGrants != null && partGrants.size() > 0) {
               this.pm.deletePersistentAll(partGrants);
            }

            List<MPartitionColumnPrivilege> partColumnGrants = this.listPartitionAllColumnGrants(part.getTable().getDatabase().getName(), part.getTable().getTableName(), Lists.newArrayList(new String[]{partName}));
            if (partColumnGrants != null && partColumnGrants.size() > 0) {
               this.pm.deletePersistentAll(partColumnGrants);
            }

            String dbName = part.getTable().getDatabase().getName();
            String tableName = part.getTable().getTableName();

            try {
               this.deletePartitionColumnStatistics(dbName, tableName, partName, part.getValues(), (String)null);
            } catch (NoSuchObjectException var14) {
               LOG.info("No column statistics records found to delete");
            }

            this.preDropStorageDescriptor(part.getSd());
            this.pm.deletePersistent(part);
         }

         success = this.commitTransaction();
      } finally {
         if (!success) {
            this.rollbackTransaction();
         }

      }

      return success;
   }

   public List getPartitions(String dbName, String tableName, int maxParts) throws MetaException, NoSuchObjectException {
      return this.getPartitionsInternal(dbName, tableName, maxParts, true, true);
   }

   protected List getPartitionsInternal(String dbName, String tblName, final int maxParts, boolean allowSql, boolean allowJdo) throws MetaException, NoSuchObjectException {
      return (List)(new GetListHelper(dbName, tblName, allowSql, allowJdo) {
         protected List getSqlResult(GetHelper ctx) throws MetaException {
            Integer max = maxParts < 0 ? null : maxParts;
            return ObjectStore.this.directSql.getPartitions(this.dbName, this.tblName, max);
         }

         protected List getJdoResult(GetHelper ctx) throws MetaException {
            QueryWrapper queryWrapper = new QueryWrapper();

            List var3;
            try {
               var3 = ObjectStore.this.convertToParts(ObjectStore.this.listMPartitions(this.dbName, this.tblName, maxParts, queryWrapper));
            } finally {
               queryWrapper.close();
            }

            return var3;
         }
      }).run(false);
   }

   public List getPartitionsWithAuth(String dbName, String tblName, short max, String userName, List groupNames) throws MetaException, InvalidObjectException {
      boolean success = false;
      QueryWrapper queryWrapper = new QueryWrapper();

      Object var19;
      try {
         this.openTransaction();
         List<MPartition> mparts = this.listMPartitions(dbName, tblName, max, queryWrapper);
         List<Partition> parts = new ArrayList(mparts.size());
         if (mparts != null && mparts.size() > 0) {
            for(MPartition mpart : mparts) {
               MTable mtbl = mpart.getTable();
               Partition part = this.convertToPart(mpart);
               parts.add(part);
               if ("TRUE".equalsIgnoreCase((String)mtbl.getParameters().get("PARTITION_LEVEL_PRIVILEGE"))) {
                  String partName = Warehouse.makePartName(this.convertToFieldSchemas(mtbl.getPartitionKeys()), part.getValues());
                  PrincipalPrivilegeSet partAuth = this.getPartitionPrivilegeSet(dbName, tblName, partName, userName, groupNames);
                  part.setPrivileges(partAuth);
               }
            }
         }

         success = this.commitTransaction();
         var19 = parts;
      } finally {
         this.rollbackAndCleanup(success, queryWrapper);
      }

      return (List)var19;
   }

   public Partition getPartitionWithAuth(String dbName, String tblName, List partVals, String user_name, List group_names) throws NoSuchObjectException, MetaException, InvalidObjectException {
      boolean success = false;

      Partition var16;
      try {
         this.openTransaction();
         MPartition mpart = this.getMPartition(dbName, tblName, partVals);
         if (mpart == null) {
            this.commitTransaction();
            throw new NoSuchObjectException("partition values=" + partVals.toString());
         }

         Partition part = null;
         MTable mtbl = mpart.getTable();
         part = this.convertToPart(mpart);
         if ("TRUE".equalsIgnoreCase((String)mtbl.getParameters().get("PARTITION_LEVEL_PRIVILEGE"))) {
            String partName = Warehouse.makePartName(this.convertToFieldSchemas(mtbl.getPartitionKeys()), partVals);
            PrincipalPrivilegeSet partAuth = this.getPartitionPrivilegeSet(dbName, tblName, partName, user_name, group_names);
            part.setPrivileges(partAuth);
         }

         success = this.commitTransaction();
         var16 = part;
      } finally {
         if (!success) {
            this.rollbackTransaction();
         }

      }

      return var16;
   }

   private List convertToParts(List mparts) throws MetaException {
      return this.convertToParts(mparts, (List)null);
   }

   private List convertToParts(List src, List dest) throws MetaException {
      if (src == null) {
         return dest;
      } else {
         if (dest == null) {
            dest = new ArrayList(src.size());
         }

         for(MPartition mp : src) {
            dest.add(this.convertToPart(mp));
            Deadline.checkTimeout();
         }

         return dest;
      }
   }

   private List convertToParts(String dbName, String tblName, List mparts) throws MetaException {
      List<Partition> parts = new ArrayList(mparts.size());

      for(MPartition mp : mparts) {
         parts.add(this.convertToPart(dbName, tblName, mp));
         Deadline.checkTimeout();
      }

      return parts;
   }

   public List listPartitionNames(String dbName, String tableName, short max) throws MetaException {
      List<String> pns = null;
      boolean success = false;

      try {
         this.openTransaction();
         LOG.debug("Executing getPartitionNames");
         pns = this.getPartitionNamesNoTxn(dbName, tableName, max);
         success = this.commitTransaction();
      } finally {
         if (!success) {
            this.rollbackTransaction();
         }

      }

      return pns;
   }

   private String extractPartitionKey(FieldSchema key, List pkeys) {
      StringBuilder buffer = new StringBuilder(256);

      assert pkeys.size() >= 1;

      String partKey = "/" + key.getName() + "=";
      if (pkeys.size() == 1 && ((FieldSchema)pkeys.get(0)).getName().matches(key.getName())) {
         buffer.append("partitionName.substring(partitionName.indexOf(\"").append(key.getName()).append("=\") + ").append(key.getName().length() + 1).append(")");
      } else if (((FieldSchema)pkeys.get(0)).getName().matches(key.getName())) {
         buffer.append("partitionName.substring(partitionName.indexOf(\"").append(key.getName()).append("=\") + ").append(key.getName().length() + 1).append(", ").append("partitionName.indexOf(\"/\")").append(")");
      } else if (((FieldSchema)pkeys.get(pkeys.size() - 1)).getName().matches(key.getName())) {
         buffer.append("partitionName.substring(partitionName.indexOf(\"").append(partKey).append("\") + ").append(partKey.length()).append(")");
      } else {
         buffer.append("partitionName.substring(partitionName.indexOf(\"").append(partKey).append("\") + ").append(partKey.length()).append(", ").append("partitionName.indexOf(\"/\", partitionName.indexOf(\"").append(partKey).append("\") + 1))");
      }

      LOG.info("Query for Key:" + key.getName() + " is :" + buffer);
      return buffer.toString();
   }

   public PartitionValuesResponse listPartitionValues(String dbName, String tableName, List cols, boolean applyDistinct, String filter, boolean ascending, List order, long maxParts) throws MetaException {
      dbName = dbName.toLowerCase().trim();
      tableName = tableName.toLowerCase().trim();

      try {
         if (filter != null && !filter.isEmpty()) {
            PartitionValuesResponse response = this.extractPartitionNamesByFilter(dbName, tableName, filter, cols, ascending, applyDistinct, maxParts);
            if (response != null && response.getPartitionValues() != null) {
               LOG.info("Number of records fetched with filter: " + response.getPartitionValues().size());
            }

            return response;
         } else {
            PartitionValuesResponse response = this.getDistinctValuesForPartitionsNoTxn(dbName, tableName, cols, applyDistinct, ascending, maxParts);
            LOG.info("Number of records fetched: " + response.getPartitionValues().size());
            return response;
         }
      } catch (Exception t) {
         LOG.error("Exception in ORM", t);
         throw new MetaException("Error retrieving partition values: " + t);
      } finally {
         ;
      }
   }

   private PartitionValuesResponse extractPartitionNamesByFilter(String dbName, String tableName, String filter, List cols, boolean ascending, boolean applyDistinct, long maxParts) throws MetaException, NoSuchObjectException {
      LOG.info("Database: " + dbName + " Table:" + tableName + " filter\"" + filter + "\" cols:" + cols);
      new ArrayList();
      List<String> partitionNames = null;
      List<Partition> partitions = null;
      Table tbl = this.getTable(dbName, tableName);

      try {
         partitionNames = this.getPartitionNamesByFilter(dbName, tableName, filter, ascending, maxParts);
      } catch (MetaException var20) {
         LOG.warn("Querying by partition names failed, trying out with partition objects, filter:" + filter);
      }

      if (partitionNames == null) {
         partitions = this.getPartitionsByFilter(dbName, tableName, filter, (short)((int)maxParts));
      }

      if (partitions != null) {
         partitionNames = new ArrayList(partitions.size());

         for(Partition partition : partitions) {
            if (tbl.getPartitionKeys() != null && partition.getValues() != null) {
               partitionNames.add(Warehouse.makePartName(tbl.getPartitionKeys(), partition.getValues()));
            }
         }
      }

      if (partitionNames == null && partitions == null) {
         throw new MetaException("Cannot obtain list of partitions by filter:\"" + filter + "\" for " + dbName + ":" + tableName);
      } else {
         if (!ascending) {
            Collections.sort(partitionNames, Collections.reverseOrder());
         }

         PartitionValuesResponse response = new PartitionValuesResponse();
         response.setPartitionValues(new ArrayList(partitionNames.size()));
         LOG.info("Converting responses to Partition values for items:" + partitionNames.size());

         for(String partName : partitionNames) {
            ArrayList<String> vals = new ArrayList(tbl.getPartitionKeys().size());

            for(FieldSchema key : tbl.getPartitionKeys()) {
               vals.add((Object)null);
            }

            PartitionValuesRow row = new PartitionValuesRow();
            Warehouse.makeValsFromName(partName, vals);

            for(String value : vals) {
               row.addToRow(value);
            }

            response.addToPartitionValues(row);
         }

         return response;
      }
   }

   private List getPartitionNamesByFilter(String dbName, String tableName, String filter, boolean ascending, long maxParts) throws MetaException {
      boolean success = false;
      List<String> partNames = new ArrayList();

      Map<String, Object> params;
      try {
         this.openTransaction();
         LOG.debug("Executing getPartitionNamesByFilter");
         dbName = dbName.toLowerCase();
         tableName = tableName.toLowerCase();
         MTable mtable = this.getMTable(dbName, tableName);
         if (mtable != null) {
            params = new HashMap();
            String queryFilterString = this.makeQueryFilterString(dbName, mtable, filter, params);
            Query query = this.pm.newQuery("select partitionName from org.apache.hadoop.hive.metastore.model.MPartition where " + queryFilterString);
            if (maxParts >= 0L) {
               query.setRange(0L, maxParts);
            }

            LOG.debug("Filter specified is " + filter + ", JDOQL filter is " + queryFilterString);
            LOG.debug("Parms is " + params);
            String parameterDeclaration = this.makeParameterDeclarationStringObj(params);
            query.declareParameters(parameterDeclaration);
            if (ascending) {
               query.setOrdering("partitionName ascending");
            } else {
               query.setOrdering("partitionName descending");
            }

            query.setResult("partitionName");
            Collection names = (Collection)query.executeWithMap(params);
            List var21 = new ArrayList();
            Iterator i = names.iterator();

            while(i.hasNext()) {
               var21.add((String)i.next());
            }

            LOG.debug("Done executing query for getPartitionNamesByFilter");
            success = this.commitTransaction();
            LOG.debug("Done retrieving all objects for getPartitionNamesByFilter, size:" + var21.size());
            query.closeAll();
            return var21;
         }

         params = partNames;
      } finally {
         if (!success) {
            this.rollbackTransaction();
         }

      }

      return params;
   }

   private PartitionValuesResponse getDistinctValuesForPartitionsNoTxn(String dbName, String tableName, List cols, boolean applyDistinct, boolean ascending, long maxParts) throws MetaException {
      PartitionValuesResponse var26;
      try {
         this.openTransaction();
         Query q = this.pm.newQuery("select partitionName from org.apache.hadoop.hive.metastore.model.MPartition where table.database.name == t1 && table.tableName == t2 ");
         q.declareParameters("java.lang.String t1, java.lang.String t2");
         if (maxParts > 0L) {
            q.setRange(0L, maxParts);
         }

         StringBuilder partValuesSelect = new StringBuilder(256);
         if (applyDistinct) {
            partValuesSelect.append("DISTINCT ");
         }

         List<FieldSchema> partitionKeys = this.getTable(dbName, tableName).getPartitionKeys();

         for(FieldSchema key : cols) {
            partValuesSelect.append(this.extractPartitionKey(key, partitionKeys)).append(", ");
         }

         partValuesSelect.setLength(partValuesSelect.length() - 2);
         LOG.info("Columns to be selected from Partitions: " + partValuesSelect);
         q.setResult(partValuesSelect.toString());
         PartitionValuesResponse response = new PartitionValuesResponse();
         response.setPartitionValues(new ArrayList());
         if (cols.size() <= 1) {
            for(Object row : (List)q.execute(dbName, tableName)) {
               PartitionValuesRow rowResponse = new PartitionValuesRow();
               rowResponse.addToRow((String)row);
               response.addToPartitionValues(rowResponse);
            }
         } else {
            for(Object[] row : (List)q.execute(dbName, tableName)) {
               PartitionValuesRow rowResponse = new PartitionValuesRow();

               for(Object columnValue : row) {
                  rowResponse.addToRow((String)columnValue);
               }

               response.addToPartitionValues(rowResponse);
            }
         }

         q.closeAll();
         var26 = response;
      } finally {
         this.commitTransaction();
      }

      return var26;
   }

   private List getPartitionNamesNoTxn(String dbName, String tableName, short max) {
      List<String> pns = new ArrayList();
      dbName = HiveStringUtils.normalizeIdentifier(dbName);
      tableName = HiveStringUtils.normalizeIdentifier(tableName);
      Query query = this.pm.newQuery("select partitionName from org.apache.hadoop.hive.metastore.model.MPartition where table.database.name == t1 && table.tableName == t2 order by partitionName asc");
      query.declareParameters("java.lang.String t1, java.lang.String t2");
      query.setResult("partitionName");
      if (max > 0) {
         query.setRange(0L, (long)max);
      }

      Collection names = (Collection)query.execute(dbName, tableName);
      Iterator i = names.iterator();

      while(i.hasNext()) {
         pns.add((String)i.next());
      }

      if (query != null) {
         query.closeAll();
      }

      return pns;
   }

   private Collection getPartitionPsQueryResults(String dbName, String tableName, List part_vals, short max_parts, String resultsCol, QueryWrapper queryWrapper) throws MetaException, NoSuchObjectException {
      dbName = HiveStringUtils.normalizeIdentifier(dbName);
      tableName = HiveStringUtils.normalizeIdentifier(tableName);
      Table table = this.getTable(dbName, tableName);
      if (table == null) {
         throw new NoSuchObjectException(dbName + "." + tableName + " table not found");
      } else {
         List<FieldSchema> partCols = table.getPartitionKeys();
         int numPartKeys = partCols.size();
         if (part_vals.size() > numPartKeys) {
            throw new MetaException("Incorrect number of partition values. numPartKeys=" + numPartKeys + ", part_val=" + part_vals.size());
         } else {
            partCols = partCols.subList(0, part_vals.size());
            String partNameMatcher = Warehouse.makePartName(partCols, part_vals, ".*");
            if (part_vals.size() < numPartKeys) {
               partNameMatcher = partNameMatcher + ".*";
            }

            Query query = queryWrapper.query = this.pm.newQuery(MPartition.class);
            StringBuilder queryFilter = new StringBuilder("table.database.name == dbName");
            queryFilter.append(" && table.tableName == tableName");
            queryFilter.append(" && partitionName.matches(partialRegex)");
            query.setFilter(queryFilter.toString());
            query.declareParameters("java.lang.String dbName, java.lang.String tableName, java.lang.String partialRegex");
            if (max_parts >= 0) {
               query.setRange(0L, (long)max_parts);
            }

            if (resultsCol != null && !resultsCol.isEmpty()) {
               query.setResult(resultsCol);
            }

            return (Collection)query.execute(dbName, tableName, partNameMatcher);
         }
      }
   }

   private boolean canTryDirectSQL(List partVals) {
      if (partVals.isEmpty()) {
         return false;
      } else {
         for(String val : partVals) {
            if (val != null && !val.isEmpty()) {
               return false;
            }
         }

         return true;
      }
   }

   public List listPartitionsPsWithAuth(String db_name, String tbl_name, List part_vals, short max_parts, String userName, List groupNames) throws MetaException, InvalidObjectException, NoSuchObjectException {
      List<Partition> partitions = new ArrayList();
      boolean success = false;
      QueryWrapper queryWrapper = new QueryWrapper();

      List var12;
      try {
         this.openTransaction();
         MTable mtbl = this.getMTable(db_name, tbl_name);
         if (mtbl == null) {
            throw new NoSuchObjectException(db_name + "." + tbl_name + " table not found");
         }

         boolean getauth = null != userName && null != groupNames && "TRUE".equalsIgnoreCase((String)mtbl.getParameters().get("PARTITION_LEVEL_PRIVILEGE"));
         if (getauth || !this.canTryDirectSQL(part_vals)) {
            LOG.debug("executing listPartitionNamesPsWithAuth");

            for(Object o : this.getPartitionPsQueryResults(db_name, tbl_name, part_vals, max_parts, (String)null, queryWrapper)) {
               Partition part = this.convertToPart((MPartition)o);
               if (getauth) {
                  String partName = Warehouse.makePartName(this.convertToFieldSchemas(mtbl.getPartitionKeys()), part.getValues());
                  PrincipalPrivilegeSet partAuth = this.getPartitionPrivilegeSet(db_name, tbl_name, partName, userName, groupNames);
                  part.setPrivileges(partAuth);
               }

               partitions.add(part);
            }

            success = this.commitTransaction();
            return partitions;
         }

         LOG.debug("Redirecting to directSQL enabled API: db: {} tbl: {} partVals: {}", new Object[]{db_name, tbl_name, Joiner.on(',').join(part_vals)});
         var12 = this.getPartitions(db_name, tbl_name, -1);
      } catch (NoSuchObjectException | MetaException | InvalidObjectException e) {
         throw e;
      } catch (Exception e) {
         throw new MetaException(e.getMessage());
      } finally {
         this.rollbackAndCleanup(success, queryWrapper);
      }

      return var12;
   }

   public List listPartitionNamesPs(String dbName, String tableName, List part_vals, short max_parts) throws MetaException, NoSuchObjectException {
      List<String> partitionNames = new ArrayList();
      boolean success = false;
      QueryWrapper queryWrapper = new QueryWrapper();

      try {
         this.openTransaction();
         LOG.debug("Executing listPartitionNamesPs");

         for(Object o : this.getPartitionPsQueryResults(dbName, tableName, part_vals, max_parts, "partitionName", queryWrapper)) {
            partitionNames.add((String)o);
         }

         success = this.commitTransaction();
      } finally {
         this.rollbackAndCleanup(success, queryWrapper);
      }

      return partitionNames;
   }

   private List listMPartitions(String dbName, String tableName, int max, QueryWrapper queryWrapper) {
      boolean success = false;
      List<MPartition> mparts = null;

      try {
         this.openTransaction();
         LOG.debug("Executing listMPartitions");
         dbName = HiveStringUtils.normalizeIdentifier(dbName);
         tableName = HiveStringUtils.normalizeIdentifier(tableName);
         Query query = queryWrapper.query = this.pm.newQuery(MPartition.class, "table.tableName == t1 && table.database.name == t2");
         query.declareParameters("java.lang.String t1, java.lang.String t2");
         query.setOrdering("partitionName ascending");
         if (max > 0) {
            query.setRange(0L, (long)max);
         }

         mparts = (List)query.execute(tableName, dbName);
         LOG.debug("Done executing query for listMPartitions");
         this.pm.retrieveAll(mparts);
         success = this.commitTransaction();
         LOG.debug("Done retrieving all objects for listMPartitions " + mparts);
      } finally {
         if (!success) {
            this.rollbackTransaction();
         }

      }

      return mparts;
   }

   public List getPartitionsByNames(String dbName, String tblName, List partNames) throws MetaException, NoSuchObjectException {
      return this.getPartitionsByNamesInternal(dbName, tblName, partNames, true, true);
   }

   protected List getPartitionsByNamesInternal(String dbName, String tblName, final List partNames, boolean allowSql, boolean allowJdo) throws MetaException, NoSuchObjectException {
      return (List)(new GetListHelper(dbName, tblName, allowSql, allowJdo) {
         protected List getSqlResult(GetHelper ctx) throws MetaException {
            return ObjectStore.this.directSql.getPartitionsViaSqlFilter(this.dbName, this.tblName, partNames);
         }

         protected List getJdoResult(GetHelper ctx) throws MetaException, NoSuchObjectException {
            return ObjectStore.this.getPartitionsViaOrmFilter(this.dbName, this.tblName, partNames);
         }
      }).run(false);
   }

   public boolean getPartitionsByExpr(String dbName, String tblName, byte[] expr, String defaultPartitionName, short maxParts, List result) throws TException {
      return this.getPartitionsByExprInternal(dbName, tblName, expr, defaultPartitionName, maxParts, result, true, true);
   }

   protected boolean getPartitionsByExprInternal(String dbName, String tblName, final byte[] expr, final String defaultPartitionName, final short maxParts, List result, boolean allowSql, boolean allowJdo) throws TException {
      assert result != null;

      final ExpressionTree exprTree = PartFilterExprUtil.makeExpressionTree(this.expressionProxy, expr);
      final AtomicBoolean hasUnknownPartitions = new AtomicBoolean(false);
      result.addAll((Collection)(new GetListHelper(dbName, tblName, allowSql, allowJdo) {
         protected List getSqlResult(GetHelper ctx) throws MetaException {
            List<Partition> result = null;
            if (exprTree != null) {
               MetaStoreDirectSql.SqlFilterForPushdown filter = new MetaStoreDirectSql.SqlFilterForPushdown();
               if (ObjectStore.this.directSql.generateSqlFilterForPushdown(ctx.getTable(), exprTree, filter)) {
                  return ObjectStore.this.directSql.getPartitionsViaSqlFilter(filter, (Integer)null);
               }
            }

            List<String> partNames = new LinkedList();
            hasUnknownPartitions.set(ObjectStore.this.getPartitionNamesPrunedByExprNoTxn(ctx.getTable(), expr, defaultPartitionName, maxParts, partNames));
            return ObjectStore.this.directSql.getPartitionsViaSqlFilter(this.dbName, this.tblName, partNames);
         }

         protected List getJdoResult(GetHelper ctx) throws MetaException, NoSuchObjectException {
            List<Partition> result = null;
            if (exprTree != null) {
               result = ObjectStore.this.getPartitionsViaOrmFilter(ctx.getTable(), exprTree, maxParts, false);
            }

            if (result == null) {
               List<String> partNames = new ArrayList();
               hasUnknownPartitions.set(ObjectStore.this.getPartitionNamesPrunedByExprNoTxn(ctx.getTable(), expr, defaultPartitionName, maxParts, partNames));
               result = ObjectStore.this.getPartitionsViaOrmFilter(this.dbName, this.tblName, partNames);
            }

            return result;
         }
      }).run(true));
      return hasUnknownPartitions.get();
   }

   private boolean getPartitionNamesPrunedByExprNoTxn(Table table, byte[] expr, String defaultPartName, short maxParts, List result) throws MetaException {
      result.addAll(this.getPartitionNamesNoTxn(table.getDbName(), table.getTableName(), maxParts));
      List<String> columnNames = new ArrayList();
      List<PrimitiveTypeInfo> typeInfos = new ArrayList();

      for(FieldSchema fs : table.getPartitionKeys()) {
         columnNames.add(fs.getName());
         typeInfos.add(TypeInfoFactory.getPrimitiveTypeInfo(fs.getType()));
      }

      if (defaultPartName == null || defaultPartName.isEmpty()) {
         defaultPartName = HiveConf.getVar(this.getConf(), ConfVars.DEFAULTPARTITIONNAME);
      }

      return this.expressionProxy.filterPartitionsByExpr(columnNames, typeInfos, expr, defaultPartName, result);
   }

   private List getPartitionsViaOrmFilter(Table table, ExpressionTree tree, short maxParts, boolean isValidatedFilter) throws MetaException {
      Map<String, Object> params = new HashMap();
      String jdoFilter = this.makeQueryFilterString(table.getDbName(), table, tree, params, isValidatedFilter);
      if (jdoFilter == null) {
         assert !isValidatedFilter;

         return null;
      } else {
         Query query = this.pm.newQuery(MPartition.class, jdoFilter);
         if (maxParts >= 0) {
            query.setRange(0L, (long)maxParts);
         }

         String parameterDeclaration = this.makeParameterDeclarationStringObj(params);
         query.declareParameters(parameterDeclaration);
         query.setOrdering("partitionName ascending");
         List<MPartition> mparts = (List)query.executeWithMap(params);
         LOG.debug("Done executing query for getPartitionsViaOrmFilter");
         this.pm.retrieveAll(mparts);
         LOG.debug("Done retrieving all objects for getPartitionsViaOrmFilter");
         List<Partition> results = this.convertToParts(mparts);
         query.closeAll();
         return results;
      }
   }

   private Integer getNumPartitionsViaOrmFilter(Table table, ExpressionTree tree, boolean isValidatedFilter) throws MetaException {
      Map<String, Object> params = new HashMap();
      String jdoFilter = this.makeQueryFilterString(table.getDbName(), table, tree, params, isValidatedFilter);
      if (jdoFilter == null) {
         assert !isValidatedFilter;

         return null;
      } else {
         Query query = this.pm.newQuery("select count(partitionName) from org.apache.hadoop.hive.metastore.model.MPartition");
         query.setFilter(jdoFilter);
         String parameterDeclaration = this.makeParameterDeclarationStringObj(params);
         query.declareParameters(parameterDeclaration);
         Long result = (Long)query.executeWithMap(params);
         query.closeAll();
         return result.intValue();
      }
   }

   private List getPartitionsViaOrmFilter(String dbName, String tblName, List partNames) throws MetaException {
      if (partNames.isEmpty()) {
         return new ArrayList();
      } else {
         ObjectPair<Query, Map<String, String>> queryWithParams = this.getPartQueryWithParams(dbName, tblName, partNames);
         Query query = (Query)queryWithParams.getFirst();
         query.setResultClass(MPartition.class);
         query.setClass(MPartition.class);
         query.setOrdering("partitionName ascending");
         List<MPartition> mparts = (List)query.executeWithMap((Map)queryWithParams.getSecond());
         List<Partition> partitions = this.convertToParts(dbName, tblName, mparts);
         if (query != null) {
            query.closeAll();
         }

         return partitions;
      }
   }

   private void dropPartitionsNoTxn(String dbName, String tblName, List partNames) {
      ObjectPair<Query, Map<String, String>> queryWithParams = this.getPartQueryWithParams(dbName, tblName, partNames);
      Query query = (Query)queryWithParams.getFirst();
      query.setClass(MPartition.class);
      long deleted = query.deletePersistentAll((Map)queryWithParams.getSecond());
      LOG.debug("Deleted " + deleted + " partition from store");
      query.closeAll();
   }

   private HashSet detachCdsFromSdsNoTxn(String dbName, String tblName, List partNames) {
      ObjectPair<Query, Map<String, String>> queryWithParams = this.getPartQueryWithParams(dbName, tblName, partNames);
      Query query = (Query)queryWithParams.getFirst();
      query.setClass(MPartition.class);
      query.setResult("sd");
      List<MStorageDescriptor> sds = (List)query.executeWithMap((Map)queryWithParams.getSecond());
      HashSet<MColumnDescriptor> candidateCds = new HashSet();

      for(MStorageDescriptor sd : sds) {
         if (sd != null && sd.getCD() != null) {
            candidateCds.add(sd.getCD());
            sd.setCD((MColumnDescriptor)null);
         }
      }

      if (query != null) {
         query.closeAll();
      }

      return candidateCds;
   }

   private ObjectPair getPartQueryWithParams(String dbName, String tblName, List partNames) {
      StringBuilder sb = new StringBuilder("table.tableName == t1 && table.database.name == t2 && (");
      int n = 0;
      Map<String, String> params = new HashMap();
      Iterator<String> itr = partNames.iterator();

      while(itr.hasNext()) {
         String pn = "p" + n;
         ++n;
         String part = (String)itr.next();
         params.put(pn, part);
         sb.append("partitionName == ").append(pn);
         sb.append(" || ");
      }

      sb.setLength(sb.length() - 4);
      sb.append(')');
      Query query = this.pm.newQuery();
      query.setFilter(sb.toString());
      LOG.debug(" JDOQL filter is " + sb.toString());
      params.put("t1", HiveStringUtils.normalizeIdentifier(tblName));
      params.put("t2", HiveStringUtils.normalizeIdentifier(dbName));
      query.declareParameters(this.makeParameterDeclarationString(params));
      return new ObjectPair(query, params);
   }

   public List getPartitionsByFilter(String dbName, String tblName, String filter, short maxParts) throws MetaException, NoSuchObjectException {
      return this.getPartitionsByFilterInternal(dbName, tblName, filter, maxParts, true, true);
   }

   public int getNumPartitionsByFilter(String dbName, String tblName, String filter) throws MetaException, NoSuchObjectException {
      final ExpressionTree exprTree = filter != null && !filter.isEmpty() ? PartFilterExprUtil.getFilterParser(filter).tree : ExpressionTree.EMPTY_TREE;
      return (Integer)(new GetHelper(dbName, tblName, true, true) {
         private MetaStoreDirectSql.SqlFilterForPushdown filter = new MetaStoreDirectSql.SqlFilterForPushdown();

         protected String describeResult() {
            return "Partition count";
         }

         protected boolean canUseDirectSql(GetHelper ctx) throws MetaException {
            return ObjectStore.this.directSql.generateSqlFilterForPushdown(ctx.getTable(), exprTree, this.filter);
         }

         protected Integer getSqlResult(GetHelper ctx) throws MetaException {
            return ObjectStore.this.directSql.getNumPartitionsViaSqlFilter(this.filter);
         }

         protected Integer getJdoResult(GetHelper ctx) throws MetaException, NoSuchObjectException {
            return ObjectStore.this.getNumPartitionsViaOrmFilter(ctx.getTable(), exprTree, true);
         }
      }).run(true);
   }

   public int getNumPartitionsByExpr(String dbName, String tblName, final byte[] expr) throws MetaException, NoSuchObjectException {
      final ExpressionTree exprTree = PartFilterExprUtil.makeExpressionTree(this.expressionProxy, expr);
      return (Integer)(new GetHelper(dbName, tblName, true, true) {
         private MetaStoreDirectSql.SqlFilterForPushdown filter = new MetaStoreDirectSql.SqlFilterForPushdown();

         protected String describeResult() {
            return "Partition count";
         }

         protected boolean canUseDirectSql(GetHelper ctx) throws MetaException {
            return ObjectStore.this.directSql.generateSqlFilterForPushdown(ctx.getTable(), exprTree, this.filter);
         }

         protected Integer getSqlResult(GetHelper ctx) throws MetaException {
            return ObjectStore.this.directSql.getNumPartitionsViaSqlFilter(this.filter);
         }

         protected Integer getJdoResult(GetHelper ctx) throws MetaException, NoSuchObjectException {
            Integer numPartitions = null;
            if (exprTree != null) {
               try {
                  numPartitions = ObjectStore.this.getNumPartitionsViaOrmFilter(ctx.getTable(), exprTree, true);
               } catch (MetaException var4) {
                  numPartitions = null;
               }
            }

            if (numPartitions == null) {
               List<String> filteredPartNames = new ArrayList();
               ObjectStore.this.getPartitionNamesPrunedByExprNoTxn(ctx.getTable(), expr, "", (short)-1, filteredPartNames);
               numPartitions = filteredPartNames.size();
            }

            return numPartitions;
         }
      }).run(true);
   }

   protected List getPartitionsByFilterInternal(String dbName, String tblName, String filter, final short maxParts, boolean allowSql, boolean allowJdo) throws MetaException, NoSuchObjectException {
      final ExpressionTree tree = filter != null && !filter.isEmpty() ? PartFilterExprUtil.getFilterParser(filter).tree : ExpressionTree.EMPTY_TREE;
      return (List)(new GetListHelper(dbName, tblName, allowSql, allowJdo) {
         private MetaStoreDirectSql.SqlFilterForPushdown filter = new MetaStoreDirectSql.SqlFilterForPushdown();

         protected boolean canUseDirectSql(GetHelper ctx) throws MetaException {
            return ObjectStore.this.directSql.generateSqlFilterForPushdown(ctx.getTable(), tree, this.filter);
         }

         protected List getSqlResult(GetHelper ctx) throws MetaException {
            return ObjectStore.this.directSql.getPartitionsViaSqlFilter(this.filter, maxParts < 0 ? null : Integer.valueOf(maxParts));
         }

         protected List getJdoResult(GetHelper ctx) throws MetaException, NoSuchObjectException {
            return ObjectStore.this.getPartitionsViaOrmFilter(ctx.getTable(), tree, maxParts, true);
         }
      }).run(true);
   }

   private MTable ensureGetMTable(String dbName, String tblName) throws NoSuchObjectException, MetaException {
      MTable mtable = this.getMTable(dbName, tblName);
      if (mtable == null) {
         throw new NoSuchObjectException("Specified database/table does not exist : " + dbName + "." + tblName);
      } else {
         return mtable;
      }
   }

   private Table ensureGetTable(String dbName, String tblName) throws NoSuchObjectException, MetaException {
      return this.convertToTable(this.ensureGetMTable(dbName, tblName));
   }

   private String makeQueryFilterString(String dbName, MTable mtable, String filter, Map params) throws MetaException {
      ExpressionTree tree = filter != null && !filter.isEmpty() ? PartFilterExprUtil.getFilterParser(filter).tree : ExpressionTree.EMPTY_TREE;
      return this.makeQueryFilterString(dbName, this.convertToTable(mtable), tree, params, true);
   }

   private String makeQueryFilterString(String dbName, Table table, ExpressionTree tree, Map params, boolean isValidatedFilter) throws MetaException {
      assert tree != null;

      ExpressionTree.FilterBuilder queryBuilder = new ExpressionTree.FilterBuilder(isValidatedFilter);
      if (table != null) {
         queryBuilder.append("table.tableName == t1 && table.database.name == t2");
         params.put("t1", table.getTableName());
         params.put("t2", table.getDbName());
      } else {
         queryBuilder.append("database.name == dbName");
         params.put("dbName", dbName);
      }

      tree.generateJDOFilterFragment(this.getConf(), table, params, queryBuilder);
      if (queryBuilder.hasError()) {
         assert !isValidatedFilter;

         LOG.info("JDO filter pushdown cannot be used: " + queryBuilder.getErrorMessage());
         return null;
      } else {
         String jdoFilter = queryBuilder.getFilter();
         LOG.debug("jdoFilter = " + jdoFilter);
         return jdoFilter;
      }
   }

   private String makeParameterDeclarationString(Map params) {
      StringBuilder paramDecl = new StringBuilder();

      for(String key : params.keySet()) {
         paramDecl.append(", java.lang.String " + key);
      }

      return paramDecl.toString();
   }

   private String makeParameterDeclarationStringObj(Map params) {
      StringBuilder paramDecl = new StringBuilder();

      for(Map.Entry entry : params.entrySet()) {
         paramDecl.append(", ");
         paramDecl.append(entry.getValue().getClass().getName());
         paramDecl.append(" ");
         paramDecl.append((String)entry.getKey());
      }

      return paramDecl.toString();
   }

   public List listTableNamesByFilter(String dbName, String filter, short maxTables) throws MetaException {
      boolean success = false;
      Query query = null;
      new ArrayList();

      ArrayList tableNames;
      try {
         this.openTransaction();
         LOG.debug("Executing listTableNamesByFilter");
         dbName = HiveStringUtils.normalizeIdentifier(dbName);
         Map<String, Object> params = new HashMap();
         String queryFilterString = this.makeQueryFilterString(dbName, (MTable)null, filter, params);
         query = this.pm.newQuery(MTable.class);
         query.declareImports("import java.lang.String");
         query.setResult("tableName");
         query.setResultClass(String.class);
         if (maxTables >= 0) {
            query.setRange(0L, (long)maxTables);
         }

         LOG.debug("filter specified is " + filter + ", JDOQL filter is " + queryFilterString);

         for(Map.Entry entry : params.entrySet()) {
            LOG.debug("key: " + (String)entry.getKey() + " value: " + entry.getValue() + " class: " + entry.getValue().getClass().getName());
         }

         String parameterDeclaration = this.makeParameterDeclarationStringObj(params);
         query.declareParameters(parameterDeclaration);
         query.setFilter(queryFilterString);
         Collection names = (Collection)query.executeWithMap(params);
         Set<String> tableNamesSet = new HashSet();
         Iterator i = names.iterator();

         while(i.hasNext()) {
            tableNamesSet.add((String)i.next());
         }

         tableNames = new ArrayList(tableNamesSet);
         LOG.debug("Done executing query for listTableNamesByFilter");
         success = this.commitTransaction();
         LOG.debug("Done retrieving all objects for listTableNamesByFilter");
      } finally {
         this.rollbackAndCleanup(success, query);
      }

      return tableNames;
   }

   public List listPartitionNamesByFilter(String dbName, String tableName, String filter, short maxParts) throws MetaException {
      boolean success = false;
      Query query = null;
      List<String> partNames = new ArrayList();

      Map<String, Object> params;
      try {
         this.openTransaction();
         LOG.debug("Executing listMPartitionNamesByFilter");
         dbName = HiveStringUtils.normalizeIdentifier(dbName);
         tableName = HiveStringUtils.normalizeIdentifier(tableName);
         MTable mtable = this.getMTable(dbName, tableName);
         if (mtable != null) {
            params = new HashMap();
            String queryFilterString = this.makeQueryFilterString(dbName, mtable, filter, params);
            query = this.pm.newQuery("select partitionName from org.apache.hadoop.hive.metastore.model.MPartition where " + queryFilterString);
            if (maxParts >= 0) {
               query.setRange(0L, (long)maxParts);
            }

            LOG.debug("Filter specified is " + filter + ", JDOQL filter is " + queryFilterString);
            LOG.debug("Parms is " + params);
            String parameterDeclaration = this.makeParameterDeclarationStringObj(params);
            query.declareParameters(parameterDeclaration);
            query.setOrdering("partitionName ascending");
            query.setResult("partitionName");
            Collection names = (Collection)query.executeWithMap(params);
            List var19 = new ArrayList();
            Iterator i = names.iterator();

            while(i.hasNext()) {
               var19.add((String)i.next());
            }

            LOG.debug("Done executing query for listMPartitionNamesByFilter");
            success = this.commitTransaction();
            LOG.debug("Done retrieving all objects for listMPartitionNamesByFilter");
            return var19;
         }

         params = partNames;
      } finally {
         this.rollbackAndCleanup(success, query);
      }

      return params;
   }

   public void alterTable(String dbname, String name, Table newTable) throws InvalidObjectException, MetaException {
      boolean success = false;

      try {
         this.openTransaction();
         name = HiveStringUtils.normalizeIdentifier(name);
         dbname = HiveStringUtils.normalizeIdentifier(dbname);
         MTable newt = this.convertToMTable(newTable);
         if (newt == null) {
            throw new InvalidObjectException("new table is invalid");
         }

         MTable oldt = this.getMTable(dbname, name);
         if (oldt == null) {
            throw new MetaException("table " + dbname + "." + name + " doesn't exist");
         }

         oldt.setDatabase(newt.getDatabase());
         oldt.setTableName(HiveStringUtils.normalizeIdentifier(newt.getTableName()));
         oldt.setParameters(newt.getParameters());
         oldt.setOwner(newt.getOwner());
         this.copyMSD(newt.getSd(), oldt.getSd());
         oldt.setRetention(newt.getRetention());
         oldt.setPartitionKeys(newt.getPartitionKeys());
         oldt.setTableType(newt.getTableType());
         oldt.setLastAccessTime(newt.getLastAccessTime());
         oldt.setViewOriginalText(newt.getViewOriginalText());
         oldt.setViewExpandedText(newt.getViewExpandedText());
         oldt.setRewriteEnabled(newt.isRewriteEnabled());
         success = this.commitTransaction();
      } finally {
         if (!success) {
            this.rollbackTransaction();
         }

      }

   }

   public void alterIndex(String dbname, String baseTblName, String name, Index newIndex) throws InvalidObjectException, MetaException {
      boolean success = false;

      try {
         this.openTransaction();
         name = HiveStringUtils.normalizeIdentifier(name);
         baseTblName = HiveStringUtils.normalizeIdentifier(baseTblName);
         dbname = HiveStringUtils.normalizeIdentifier(dbname);
         MIndex newi = this.convertToMIndex(newIndex);
         if (newi == null) {
            throw new InvalidObjectException("new index is invalid");
         }

         MIndex oldi = this.getMIndex(dbname, baseTblName, name);
         if (oldi == null) {
            throw new MetaException("index " + name + " doesn't exist");
         }

         oldi.setParameters(newi.getParameters());
         success = this.commitTransaction();
      } finally {
         if (!success) {
            this.rollbackTransaction();
         }

      }

   }

   private void alterPartitionNoTxn(String dbname, String name, List part_vals, Partition newPart) throws InvalidObjectException, MetaException {
      name = HiveStringUtils.normalizeIdentifier(name);
      dbname = HiveStringUtils.normalizeIdentifier(dbname);
      MPartition oldp = this.getMPartition(dbname, name, part_vals);
      MPartition newp = this.convertToMPart(newPart, false);
      if (oldp != null && newp != null) {
         oldp.setValues(newp.getValues());
         oldp.setPartitionName(newp.getPartitionName());
         oldp.setParameters(newPart.getParameters());
         if (!TableType.VIRTUAL_VIEW.name().equals(oldp.getTable().getTableType())) {
            this.copyMSD(newp.getSd(), oldp.getSd());
         }

         if (newp.getCreateTime() != oldp.getCreateTime()) {
            oldp.setCreateTime(newp.getCreateTime());
         }

         if (newp.getLastAccessTime() != oldp.getLastAccessTime()) {
            oldp.setLastAccessTime(newp.getLastAccessTime());
         }

      } else {
         throw new InvalidObjectException("partition does not exist.");
      }
   }

   public void alterPartition(String dbname, String name, List part_vals, Partition newPart) throws InvalidObjectException, MetaException {
      boolean success = false;
      Exception e = null;

      try {
         this.openTransaction();
         this.alterPartitionNoTxn(dbname, name, part_vals, newPart);
         success = this.commitTransaction();
      } catch (Exception exception) {
         e = exception;
      } finally {
         if (!success) {
            this.rollbackTransaction();
            MetaException metaException = new MetaException("The transaction for alter partition did not commit successfully.");
            if (e != null) {
               metaException.initCause(e);
            }

            throw metaException;
         }

      }

   }

   public void alterPartitions(String dbname, String name, List part_vals, List newParts) throws InvalidObjectException, MetaException {
      boolean success = false;
      Exception e = null;

      try {
         this.openTransaction();
         Iterator<List<String>> part_val_itr = part_vals.iterator();

         for(Partition tmpPart : newParts) {
            List<String> tmpPartVals = (List)part_val_itr.next();
            this.alterPartitionNoTxn(dbname, name, tmpPartVals, tmpPart);
         }

         success = this.commitTransaction();
      } catch (Exception exception) {
         e = exception;
      } finally {
         if (!success) {
            this.rollbackTransaction();
            MetaException metaException = new MetaException("The transaction for alter partition did not commit successfully.");
            if (e != null) {
               metaException.initCause(e);
            }

            throw metaException;
         }

      }

   }

   private void copyMSD(MStorageDescriptor newSd, MStorageDescriptor oldSd) {
      oldSd.setLocation(newSd.getLocation());
      MColumnDescriptor oldCD = oldSd.getCD();
      if (oldSd == null || oldSd.getCD() == null || oldSd.getCD().getCols() == null || newSd == null || newSd.getCD() == null || newSd.getCD().getCols() == null || !this.convertToFieldSchemas(newSd.getCD().getCols()).equals(this.convertToFieldSchemas(oldSd.getCD().getCols()))) {
         oldSd.setCD(newSd.getCD());
      }

      this.removeUnusedColumnDescriptor(oldCD);
      oldSd.setBucketCols(newSd.getBucketCols());
      oldSd.setCompressed(newSd.isCompressed());
      oldSd.setInputFormat(newSd.getInputFormat());
      oldSd.setOutputFormat(newSd.getOutputFormat());
      oldSd.setNumBuckets(newSd.getNumBuckets());
      oldSd.getSerDeInfo().setName(newSd.getSerDeInfo().getName());
      oldSd.getSerDeInfo().setSerializationLib(newSd.getSerDeInfo().getSerializationLib());
      oldSd.getSerDeInfo().setParameters(newSd.getSerDeInfo().getParameters());
      oldSd.setSkewedColNames(newSd.getSkewedColNames());
      oldSd.setSkewedColValues(newSd.getSkewedColValues());
      oldSd.setSkewedColValueLocationMaps(newSd.getSkewedColValueLocationMaps());
      oldSd.setSortCols(newSd.getSortCols());
      oldSd.setParameters(newSd.getParameters());
      oldSd.setStoredAsSubDirectories(newSd.isStoredAsSubDirectories());
   }

   private void removeUnusedColumnDescriptor(MColumnDescriptor oldCD) {
      if (oldCD != null) {
         boolean success = false;
         QueryWrapper queryWrapper = new QueryWrapper();

         try {
            this.openTransaction();
            LOG.debug("execute removeUnusedColumnDescriptor");
            List<MStorageDescriptor> referencedSDs = this.listStorageDescriptorsWithCD(oldCD, 1L, queryWrapper);
            if (referencedSDs != null && referencedSDs.isEmpty()) {
               this.pm.retrieve(oldCD);
               this.pm.deletePersistent(oldCD);
            }

            success = this.commitTransaction();
            LOG.debug("successfully deleted a CD in removeUnusedColumnDescriptor");
         } finally {
            this.rollbackAndCleanup(success, queryWrapper);
         }

      }
   }

   private void preDropStorageDescriptor(MStorageDescriptor msd) {
      if (msd != null && msd.getCD() != null) {
         MColumnDescriptor mcd = msd.getCD();
         msd.setCD((MColumnDescriptor)null);
         this.removeUnusedColumnDescriptor(mcd);
      }
   }

   private List listStorageDescriptorsWithCD(MColumnDescriptor oldCD, long maxSDs, QueryWrapper queryWrapper) {
      boolean success = false;
      List<MStorageDescriptor> sds = null;

      try {
         this.openTransaction();
         LOG.debug("Executing listStorageDescriptorsWithCD");
         Query query = queryWrapper.query = this.pm.newQuery(MStorageDescriptor.class, "this.cd == inCD");
         query.declareParameters("MColumnDescriptor inCD");
         if (maxSDs >= 0L) {
            query.setRange(0L, maxSDs);
         }

         sds = (List)query.execute(oldCD);
         LOG.debug("Done executing query for listStorageDescriptorsWithCD");
         this.pm.retrieveAll(sds);
         success = this.commitTransaction();
         LOG.debug("Done retrieving all objects for listStorageDescriptorsWithCD");
      } finally {
         if (!success) {
            this.rollbackTransaction();
         }

      }

      return sds;
   }

   private int getColumnIndexFromTableColumns(List cols, String col) {
      if (cols == null) {
         return -1;
      } else {
         for(int i = 0; i < cols.size(); ++i) {
            MFieldSchema mfs = (MFieldSchema)cols.get(i);
            if (mfs.getName().equalsIgnoreCase(col)) {
               return i;
            }
         }

         return -1;
      }
   }

   private boolean constraintNameAlreadyExists(String name) {
      boolean commited = false;
      Query constraintExistsQuery = null;
      String constraintNameIfExists = null;

      try {
         this.openTransaction();
         name = HiveStringUtils.normalizeIdentifier(name);
         constraintExistsQuery = this.pm.newQuery(MConstraint.class, "constraintName == name");
         constraintExistsQuery.declareParameters("java.lang.String name");
         constraintExistsQuery.setUnique(true);
         constraintExistsQuery.setResult("name");
         constraintNameIfExists = (String)constraintExistsQuery.execute(name);
         commited = this.commitTransaction();
      } finally {
         this.rollbackAndCleanup(commited, constraintExistsQuery);
      }

      return constraintNameIfExists != null && !constraintNameIfExists.isEmpty();
   }

   private String generateConstraintName(String... parameters) throws MetaException {
      int hashcode = ArrayUtils.toString(parameters).hashCode();
      int counter = 0;
      int MAX_RETRIES = 10;

      while(counter < 10) {
         String currName = (parameters.length == 0 ? "constraint_" : parameters[parameters.length - 1]) + "_" + hashcode + "_" + System.currentTimeMillis() + "_" + counter++;
         if (!this.constraintNameAlreadyExists(currName)) {
            return currName;
         }
      }

      throw new MetaException("Error while trying to generate the constraint name for " + ArrayUtils.toString(parameters));
   }

   public void addForeignKeys(List fks) throws InvalidObjectException, MetaException {
      this.addForeignKeys(fks, true);
   }

   private void addForeignKeys(List fks, boolean retrieveCD) throws InvalidObjectException, MetaException {
      List<MConstraint> mpkfks = new ArrayList();
      String currentConstraintName = null;

      for(int i = 0; i < fks.size(); ++i) {
         AttachedMTableInfo nParentTable = this.getMTable(((SQLForeignKey)fks.get(i)).getPktable_db(), ((SQLForeignKey)fks.get(i)).getPktable_name(), retrieveCD);
         MTable parentTable = nParentTable.mtbl;
         if (parentTable == null) {
            throw new InvalidObjectException("Parent table not found: " + ((SQLForeignKey)fks.get(i)).getPktable_name());
         }

         AttachedMTableInfo nChildTable = this.getMTable(((SQLForeignKey)fks.get(i)).getFktable_db(), ((SQLForeignKey)fks.get(i)).getFktable_name(), retrieveCD);
         MTable childTable = nChildTable.mtbl;
         if (childTable == null) {
            throw new InvalidObjectException("Child table not found: " + ((SQLForeignKey)fks.get(i)).getFktable_name());
         }

         MColumnDescriptor parentCD = retrieveCD ? nParentTable.mcd : parentTable.getSd().getCD();
         List<MFieldSchema> parentCols = parentCD == null ? null : parentCD.getCols();
         int parentIntegerIndex = this.getColumnIndexFromTableColumns(parentCols, ((SQLForeignKey)fks.get(i)).getPkcolumn_name());
         if (parentIntegerIndex == -1) {
            throw new InvalidObjectException("Parent column not found: " + ((SQLForeignKey)fks.get(i)).getPkcolumn_name());
         }

         MColumnDescriptor childCD = retrieveCD ? nChildTable.mcd : childTable.getSd().getCD();
         List<MFieldSchema> childCols = childCD.getCols();
         int childIntegerIndex = this.getColumnIndexFromTableColumns(childCols, ((SQLForeignKey)fks.get(i)).getFkcolumn_name());
         if (childIntegerIndex == -1) {
            throw new InvalidObjectException("Child column not found: " + ((SQLForeignKey)fks.get(i)).getFkcolumn_name());
         }

         if (((SQLForeignKey)fks.get(i)).getFk_name() == null) {
            if (((SQLForeignKey)fks.get(i)).getKey_seq() == 1) {
               currentConstraintName = this.generateConstraintName(((SQLForeignKey)fks.get(i)).getFktable_db(), ((SQLForeignKey)fks.get(i)).getFktable_name(), ((SQLForeignKey)fks.get(i)).getPktable_db(), ((SQLForeignKey)fks.get(i)).getPktable_name(), ((SQLForeignKey)fks.get(i)).getPkcolumn_name(), ((SQLForeignKey)fks.get(i)).getFkcolumn_name(), "fk");
            }
         } else {
            currentConstraintName = ((SQLForeignKey)fks.get(i)).getFk_name();
         }

         Integer updateRule = ((SQLForeignKey)fks.get(i)).getUpdate_rule();
         Integer deleteRule = ((SQLForeignKey)fks.get(i)).getDelete_rule();
         int enableValidateRely = (((SQLForeignKey)fks.get(i)).isEnable_cstr() ? 4 : 0) + (((SQLForeignKey)fks.get(i)).isValidate_cstr() ? 2 : 0) + (((SQLForeignKey)fks.get(i)).isRely_cstr() ? 1 : 0);
         MConstraint mpkfk = new MConstraint(currentConstraintName, 1, ((SQLForeignKey)fks.get(i)).getKey_seq(), deleteRule, updateRule, enableValidateRely, parentTable, childTable, parentCD, childCD, childIntegerIndex, parentIntegerIndex);
         mpkfks.add(mpkfk);
      }

      this.pm.makePersistentAll(mpkfks);
   }

   public void addPrimaryKeys(List pks) throws InvalidObjectException, MetaException {
      this.addPrimaryKeys(pks, true);
   }

   private void addPrimaryKeys(List pks, boolean retrieveCD) throws InvalidObjectException, MetaException {
      List<MConstraint> mpks = new ArrayList();
      String constraintName = null;

      for(int i = 0; i < pks.size(); ++i) {
         AttachedMTableInfo nParentTable = this.getMTable(((SQLPrimaryKey)pks.get(i)).getTable_db(), ((SQLPrimaryKey)pks.get(i)).getTable_name(), retrieveCD);
         MTable parentTable = nParentTable.mtbl;
         if (parentTable == null) {
            throw new InvalidObjectException("Parent table not found: " + ((SQLPrimaryKey)pks.get(i)).getTable_name());
         }

         MColumnDescriptor parentCD = retrieveCD ? nParentTable.mcd : parentTable.getSd().getCD();
         int parentIntegerIndex = this.getColumnIndexFromTableColumns(parentCD == null ? null : parentCD.getCols(), ((SQLPrimaryKey)pks.get(i)).getColumn_name());
         if (parentIntegerIndex == -1) {
            throw new InvalidObjectException("Parent column not found: " + ((SQLPrimaryKey)pks.get(i)).getColumn_name());
         }

         if (this.getPrimaryKeyConstraintName(parentTable.getDatabase().getName(), parentTable.getTableName()) != null) {
            throw new MetaException(" Primary key already exists for: " + parentTable.getDatabase().getName() + "." + ((SQLPrimaryKey)pks.get(i)).getTable_name());
         }

         if (((SQLPrimaryKey)pks.get(i)).getPk_name() == null) {
            if (((SQLPrimaryKey)pks.get(i)).getKey_seq() == 1) {
               constraintName = this.generateConstraintName(((SQLPrimaryKey)pks.get(i)).getTable_db(), ((SQLPrimaryKey)pks.get(i)).getTable_name(), ((SQLPrimaryKey)pks.get(i)).getColumn_name(), "pk");
            }
         } else {
            constraintName = ((SQLPrimaryKey)pks.get(i)).getPk_name();
         }

         int enableValidateRely = (((SQLPrimaryKey)pks.get(i)).isEnable_cstr() ? 4 : 0) + (((SQLPrimaryKey)pks.get(i)).isValidate_cstr() ? 2 : 0) + (((SQLPrimaryKey)pks.get(i)).isRely_cstr() ? 1 : 0);
         MConstraint mpk = new MConstraint(constraintName, 0, ((SQLPrimaryKey)pks.get(i)).getKey_seq(), (Integer)null, (Integer)null, enableValidateRely, parentTable, (MTable)null, parentCD, (MColumnDescriptor)null, (Integer)null, parentIntegerIndex);
         mpks.add(mpk);
      }

      this.pm.makePersistentAll(mpks);
   }

   public boolean addIndex(Index index) throws InvalidObjectException, MetaException {
      boolean commited = false;

      boolean var4;
      try {
         this.openTransaction();
         MIndex idx = this.convertToMIndex(index);
         this.pm.makePersistent(idx);
         commited = this.commitTransaction();
         var4 = true;
      } finally {
         if (!commited) {
            this.rollbackTransaction();
            return false;
         }

      }

      return var4;
   }

   private MIndex convertToMIndex(Index index) throws InvalidObjectException, MetaException {
      StorageDescriptor sd = index.getSd();
      if (sd == null) {
         throw new InvalidObjectException("Storage descriptor is not defined for index.");
      } else {
         MStorageDescriptor msd = this.convertToMStorageDescriptor(sd);
         MTable origTable = this.getMTable(index.getDbName(), index.getOrigTableName());
         if (origTable == null) {
            throw new InvalidObjectException("Original table does not exist for the given index.");
         } else {
            String[] qualified = MetaStoreUtils.getQualifiedName(index.getDbName(), index.getIndexTableName());
            MTable indexTable = this.getMTable(qualified[0], qualified[1]);
            if (indexTable == null) {
               throw new InvalidObjectException("Underlying index table does not exist for the given index.");
            } else {
               return new MIndex(HiveStringUtils.normalizeIdentifier(index.getIndexName()), origTable, index.getCreateTime(), index.getLastAccessTime(), index.getParameters(), indexTable, msd, index.getIndexHandlerClass(), index.isDeferredRebuild());
            }
         }
      }
   }

   public boolean dropIndex(String dbName, String origTableName, String indexName) throws MetaException {
      boolean success = false;

      try {
         this.openTransaction();
         MIndex index = this.getMIndex(dbName, origTableName, indexName);
         if (index != null) {
            this.pm.deletePersistent(index);
         }

         success = this.commitTransaction();
      } finally {
         if (!success) {
            this.rollbackTransaction();
         }

      }

      return success;
   }

   private MIndex getMIndex(String dbName, String originalTblName, String indexName) throws MetaException {
      MIndex midx = null;
      boolean commited = false;
      Query query = null;

      Object var8;
      try {
         this.openTransaction();
         dbName = HiveStringUtils.normalizeIdentifier(dbName);
         originalTblName = HiveStringUtils.normalizeIdentifier(originalTblName);
         MTable mtbl = this.getMTable(dbName, originalTblName);
         if (mtbl != null) {
            query = this.pm.newQuery(MIndex.class, "origTable.tableName == t1 && origTable.database.name == t2 && indexName == t3");
            query.declareParameters("java.lang.String t1, java.lang.String t2, java.lang.String t3");
            query.setUnique(true);
            midx = (MIndex)query.execute(originalTblName, dbName, HiveStringUtils.normalizeIdentifier(indexName));
            this.pm.retrieve(midx);
            commited = this.commitTransaction();
            return midx;
         }

         commited = this.commitTransaction();
         var8 = null;
      } finally {
         this.rollbackAndCleanup(commited, query);
      }

      return (MIndex)var8;
   }

   public Index getIndex(String dbName, String origTableName, String indexName) throws MetaException {
      this.openTransaction();
      MIndex mIndex = this.getMIndex(dbName, origTableName, indexName);
      Index ret = this.convertToIndex(mIndex);
      this.commitTransaction();
      return ret;
   }

   private Index convertToIndex(MIndex mIndex) throws MetaException {
      if (mIndex == null) {
         return null;
      } else {
         MTable origTable = mIndex.getOrigTable();
         MTable indexTable = mIndex.getIndexTable();
         return new Index(mIndex.getIndexName(), mIndex.getIndexHandlerClass(), origTable.getDatabase().getName(), origTable.getTableName(), mIndex.getCreateTime(), mIndex.getLastAccessTime(), indexTable.getTableName(), this.convertToStorageDescriptor(mIndex.getSd()), mIndex.getParameters(), mIndex.getDeferredRebuild());
      }
   }

   public List getIndexes(String dbName, String origTableName, int max) throws MetaException {
      boolean success = false;
      Query query = null;

      Object var15;
      try {
         LOG.debug("Executing getIndexes");
         this.openTransaction();
         dbName = HiveStringUtils.normalizeIdentifier(dbName);
         origTableName = HiveStringUtils.normalizeIdentifier(origTableName);
         query = this.pm.newQuery(MIndex.class, "origTable.tableName == t1 && origTable.database.name == t2");
         query.declareParameters("java.lang.String t1, java.lang.String t2");
         List<MIndex> mIndexes = (List)query.execute(origTableName, dbName);
         this.pm.retrieveAll(mIndexes);
         List<Index> indexes = new ArrayList(mIndexes.size());

         for(MIndex mIdx : mIndexes) {
            indexes.add(this.convertToIndex(mIdx));
         }

         success = this.commitTransaction();
         LOG.debug("Done retrieving all objects for getIndexes");
         var15 = indexes;
      } finally {
         this.rollbackAndCleanup(success, query);
      }

      return (List)var15;
   }

   public List listIndexNames(String dbName, String origTableName, short max) throws MetaException {
      List<String> pns = new ArrayList();
      boolean success = false;
      Query query = null;

      try {
         this.openTransaction();
         LOG.debug("Executing listIndexNames");
         dbName = HiveStringUtils.normalizeIdentifier(dbName);
         origTableName = HiveStringUtils.normalizeIdentifier(origTableName);
         query = this.pm.newQuery("select indexName from org.apache.hadoop.hive.metastore.model.MIndex where origTable.database.name == t1 && origTable.tableName == t2 order by indexName asc");
         query.declareParameters("java.lang.String t1, java.lang.String t2");
         query.setResult("indexName");
         Collection names = (Collection)query.execute(dbName, origTableName);
         Iterator i = names.iterator();

         while(i.hasNext()) {
            pns.add((String)i.next());
         }

         success = this.commitTransaction();
      } finally {
         this.rollbackAndCleanup(success, query);
      }

      return pns;
   }

   public boolean addRole(String roleName, String ownerName) throws InvalidObjectException, MetaException, NoSuchObjectException {
      boolean success = false;
      boolean commited = false;

      try {
         this.openTransaction();
         MRole nameCheck = this.getMRole(roleName);
         if (nameCheck != null) {
            throw new InvalidObjectException("Role " + roleName + " already exists.");
         }

         int now = (int)(System.currentTimeMillis() / 1000L);
         MRole mRole = new MRole(roleName, now, ownerName);
         this.pm.makePersistent(mRole);
         commited = this.commitTransaction();
         success = true;
      } finally {
         if (!commited) {
            this.rollbackTransaction();
         }

      }

      return success;
   }

   public boolean grantRole(Role role, String userName, PrincipalType principalType, String grantor, PrincipalType grantorType, boolean grantOption) throws MetaException, NoSuchObjectException, InvalidObjectException {
      boolean success = false;
      boolean commited = false;

      try {
         this.openTransaction();
         MRoleMap roleMap = null;

         try {
            roleMap = this.getMSecurityUserRoleMap(userName, principalType, role.getRoleName());
         } catch (Exception var17) {
         }

         if (roleMap != null) {
            throw new InvalidObjectException("Principal " + userName + " already has the role " + role.getRoleName());
         }

         if (principalType == PrincipalType.ROLE) {
            this.validateRole(userName);
         }

         MRole mRole = this.getMRole(role.getRoleName());
         long now = System.currentTimeMillis() / 1000L;
         MRoleMap roleMember = new MRoleMap(userName, principalType.toString(), mRole, (int)now, grantor, grantorType.toString(), grantOption);
         this.pm.makePersistent(roleMember);
         commited = this.commitTransaction();
         success = true;
      } finally {
         if (!commited) {
            this.rollbackTransaction();
         }

      }

      return success;
   }

   private void validateRole(String roleName) throws NoSuchObjectException {
      MRole granteeRole = this.getMRole(roleName);
      if (granteeRole == null) {
         throw new NoSuchObjectException("Role " + roleName + " does not exist");
      }
   }

   public boolean revokeRole(Role role, String userName, PrincipalType principalType, boolean grantOption) throws MetaException, NoSuchObjectException {
      boolean success = false;

      try {
         this.openTransaction();
         MRoleMap roleMember = this.getMSecurityUserRoleMap(userName, principalType, role.getRoleName());
         if (grantOption) {
            if (!roleMember.getGrantOption()) {
               throw new MetaException("User " + userName + " does not have grant option with role " + role.getRoleName());
            }

            roleMember.setGrantOption(false);
         } else {
            this.pm.deletePersistent(roleMember);
         }

         success = this.commitTransaction();
      } finally {
         if (!success) {
            this.rollbackTransaction();
         }

      }

      return success;
   }

   private MRoleMap getMSecurityUserRoleMap(String userName, PrincipalType principalType, String roleName) {
      MRoleMap mRoleMember = null;
      boolean commited = false;
      Query query = null;

      try {
         this.openTransaction();
         query = this.pm.newQuery(MRoleMap.class, "principalName == t1 && principalType == t2 && role.roleName == t3");
         query.declareParameters("java.lang.String t1, java.lang.String t2, java.lang.String t3");
         query.setUnique(true);
         mRoleMember = (MRoleMap)query.executeWithArray(new Object[]{userName, principalType.toString(), roleName});
         this.pm.retrieve(mRoleMember);
         commited = this.commitTransaction();
      } finally {
         this.rollbackAndCleanup(commited, query);
      }

      return mRoleMember;
   }

   public boolean removeRole(String roleName) throws MetaException, NoSuchObjectException {
      boolean success = false;
      QueryWrapper queryWrapper = new QueryWrapper();

      try {
         this.openTransaction();
         MRole mRol = this.getMRole(roleName);
         this.pm.retrieve(mRol);
         if (mRol != null) {
            List<MRoleMap> roleMap = this.listMRoleMembers(mRol.getRoleName());
            if (roleMap.size() > 0) {
               this.pm.deletePersistentAll(roleMap);
            }

            List<MRoleMap> roleMember = this.listMSecurityPrincipalMembershipRole(mRol.getRoleName(), PrincipalType.ROLE, queryWrapper);
            if (roleMember.size() > 0) {
               this.pm.deletePersistentAll(roleMember);
            }

            queryWrapper.close();
            List<MGlobalPrivilege> userGrants = this.listPrincipalMGlobalGrants(mRol.getRoleName(), PrincipalType.ROLE);
            if (userGrants.size() > 0) {
               this.pm.deletePersistentAll(userGrants);
            }

            List<MDBPrivilege> dbGrants = this.listPrincipalAllDBGrant(mRol.getRoleName(), PrincipalType.ROLE, queryWrapper);
            if (dbGrants.size() > 0) {
               this.pm.deletePersistentAll(dbGrants);
            }

            queryWrapper.close();
            List<MTablePrivilege> tabPartGrants = this.listPrincipalAllTableGrants(mRol.getRoleName(), PrincipalType.ROLE, queryWrapper);
            if (tabPartGrants.size() > 0) {
               this.pm.deletePersistentAll(tabPartGrants);
            }

            queryWrapper.close();
            List<MPartitionPrivilege> partGrants = this.listPrincipalAllPartitionGrants(mRol.getRoleName(), PrincipalType.ROLE, queryWrapper);
            if (partGrants.size() > 0) {
               this.pm.deletePersistentAll(partGrants);
            }

            queryWrapper.close();
            List<MTableColumnPrivilege> tblColumnGrants = this.listPrincipalAllTableColumnGrants(mRol.getRoleName(), PrincipalType.ROLE, queryWrapper);
            if (tblColumnGrants.size() > 0) {
               this.pm.deletePersistentAll(tblColumnGrants);
            }

            queryWrapper.close();
            List<MPartitionColumnPrivilege> partColumnGrants = this.listPrincipalAllPartitionColumnGrants(mRol.getRoleName(), PrincipalType.ROLE, queryWrapper);
            if (partColumnGrants.size() > 0) {
               this.pm.deletePersistentAll(partColumnGrants);
            }

            queryWrapper.close();
            this.pm.deletePersistent(mRol);
         }

         success = this.commitTransaction();
      } finally {
         this.rollbackAndCleanup(success, queryWrapper);
      }

      return success;
   }

   private Set listAllRolesInHierarchy(String userName, List groupNames) {
      List<MRoleMap> ret = new ArrayList();
      if (userName != null) {
         ret.addAll(this.listMRoles(userName, PrincipalType.USER));
      }

      if (groupNames != null) {
         for(String groupName : groupNames) {
            ret.addAll(this.listMRoles(groupName, PrincipalType.GROUP));
         }
      }

      Set<String> roleNames = new HashSet();
      this.getAllRoleAncestors(roleNames, ret);
      return roleNames;
   }

   private void getAllRoleAncestors(Set processedRoleNames, List parentRoles) {
      for(MRoleMap parentRole : parentRoles) {
         String parentRoleName = parentRole.getRole().getRoleName();
         if (!processedRoleNames.contains(parentRoleName)) {
            List<MRoleMap> nextParentRoles = this.listMRoles(parentRoleName, PrincipalType.ROLE);
            processedRoleNames.add(parentRoleName);
            this.getAllRoleAncestors(processedRoleNames, nextParentRoles);
         }
      }

   }

   public List listMRoles(String principalName, PrincipalType principalType) {
      boolean success = false;
      Query query = null;
      List<MRoleMap> mRoleMember = new ArrayList();

      try {
         LOG.debug("Executing listRoles");
         this.openTransaction();
         query = this.pm.newQuery(MRoleMap.class, "principalName == t1 && principalType == t2");
         query.declareParameters("java.lang.String t1, java.lang.String t2");
         query.setUnique(false);
         List<MRoleMap> mRoles = (List)query.executeWithArray(new Object[]{principalName, principalType.toString()});
         this.pm.retrieveAll(mRoles);
         success = this.commitTransaction();
         mRoleMember.addAll(mRoles);
         LOG.debug("Done retrieving all objects for listRoles");
      } finally {
         this.rollbackAndCleanup(success, query);
      }

      if (principalType == PrincipalType.USER) {
         MRole publicRole = new MRole("public", 0, "public");
         mRoleMember.add(new MRoleMap(principalName, principalType.toString(), publicRole, 0, (String)null, (String)null, false));
      }

      return mRoleMember;
   }

   public List listRoles(String principalName, PrincipalType principalType) {
      List<Role> result = new ArrayList();
      List<MRoleMap> roleMaps = this.listMRoles(principalName, principalType);
      if (roleMaps != null) {
         for(MRoleMap roleMap : roleMaps) {
            MRole mrole = roleMap.getRole();
            Role role = new Role(mrole.getRoleName(), mrole.getCreateTime(), mrole.getOwnerName());
            result.add(role);
         }
      }

      return result;
   }

   public List listRolesWithGrants(String principalName, PrincipalType principalType) {
      List<RolePrincipalGrant> result = new ArrayList();
      List<MRoleMap> roleMaps = this.listMRoles(principalName, principalType);
      if (roleMaps != null) {
         for(MRoleMap roleMap : roleMaps) {
            RolePrincipalGrant rolePrinGrant = new RolePrincipalGrant(roleMap.getRole().getRoleName(), roleMap.getPrincipalName(), PrincipalType.valueOf(roleMap.getPrincipalType()), roleMap.getGrantOption(), roleMap.getAddTime(), roleMap.getGrantor(), roleMap.getGrantorType() == null ? null : PrincipalType.valueOf(roleMap.getGrantorType()));
            result.add(rolePrinGrant);
         }
      }

      return result;
   }

   private List listMSecurityPrincipalMembershipRole(String roleName, PrincipalType principalType, QueryWrapper queryWrapper) {
      boolean success = false;
      List<MRoleMap> mRoleMemebership = null;

      try {
         LOG.debug("Executing listMSecurityPrincipalMembershipRole");
         this.openTransaction();
         Query query = queryWrapper.query = this.pm.newQuery(MRoleMap.class, "principalName == t1 && principalType == t2");
         query.declareParameters("java.lang.String t1, java.lang.String t2");
         mRoleMemebership = (List)query.execute(roleName, principalType.toString());
         this.pm.retrieveAll(mRoleMemebership);
         success = this.commitTransaction();
         LOG.debug("Done retrieving all objects for listMSecurityPrincipalMembershipRole");
      } finally {
         if (!success) {
            this.rollbackTransaction();
         }

      }

      return mRoleMemebership;
   }

   public Role getRole(String roleName) throws NoSuchObjectException {
      MRole mRole = this.getMRole(roleName);
      if (mRole == null) {
         throw new NoSuchObjectException(roleName + " role can not be found.");
      } else {
         Role ret = new Role(mRole.getRoleName(), mRole.getCreateTime(), mRole.getOwnerName());
         return ret;
      }
   }

   private MRole getMRole(String roleName) {
      MRole mrole = null;
      boolean commited = false;
      Query query = null;

      try {
         this.openTransaction();
         query = this.pm.newQuery(MRole.class, "roleName == t1");
         query.declareParameters("java.lang.String t1");
         query.setUnique(true);
         mrole = (MRole)query.execute(roleName);
         this.pm.retrieve(mrole);
         commited = this.commitTransaction();
      } finally {
         this.rollbackAndCleanup(commited, query);
      }

      return mrole;
   }

   public List listRoleNames() {
      boolean success = false;
      Query query = null;

      Object var9;
      try {
         this.openTransaction();
         LOG.debug("Executing listAllRoleNames");
         query = this.pm.newQuery("select roleName from org.apache.hadoop.hive.metastore.model.MRole");
         query.setResult("roleName");
         Collection names = (Collection)query.execute();
         List<String> roleNames = new ArrayList();
         Iterator i = names.iterator();

         while(i.hasNext()) {
            roleNames.add((String)i.next());
         }

         success = this.commitTransaction();
         var9 = roleNames;
      } finally {
         this.rollbackAndCleanup(success, query);
      }

      return (List)var9;
   }

   public PrincipalPrivilegeSet getUserPrivilegeSet(String userName, List groupNames) throws InvalidObjectException, MetaException {
      boolean commited = false;
      PrincipalPrivilegeSet ret = new PrincipalPrivilegeSet();

      try {
         this.openTransaction();
         if (userName != null) {
            List<MGlobalPrivilege> user = this.listPrincipalMGlobalGrants(userName, PrincipalType.USER);
            if (user.size() > 0) {
               Map<String, List<PrivilegeGrantInfo>> userPriv = new HashMap();
               List<PrivilegeGrantInfo> grantInfos = new ArrayList(user.size());

               for(int i = 0; i < user.size(); ++i) {
                  MGlobalPrivilege item = (MGlobalPrivilege)user.get(i);
                  grantInfos.add(new PrivilegeGrantInfo(item.getPrivilege(), item.getCreateTime(), item.getGrantor(), this.getPrincipalTypeFromStr(item.getGrantorType()), item.getGrantOption()));
               }

               userPriv.put(userName, grantInfos);
               ret.setUserPrivileges(userPriv);
            }
         }

         if (groupNames != null && groupNames.size() > 0) {
            Map<String, List<PrivilegeGrantInfo>> groupPriv = new HashMap();

            for(String groupName : groupNames) {
               List<MGlobalPrivilege> group = this.listPrincipalMGlobalGrants(groupName, PrincipalType.GROUP);
               if (group.size() > 0) {
                  List<PrivilegeGrantInfo> grantInfos = new ArrayList(group.size());

                  for(int i = 0; i < group.size(); ++i) {
                     MGlobalPrivilege item = (MGlobalPrivilege)group.get(i);
                     grantInfos.add(new PrivilegeGrantInfo(item.getPrivilege(), item.getCreateTime(), item.getGrantor(), this.getPrincipalTypeFromStr(item.getGrantorType()), item.getGrantOption()));
                  }

                  groupPriv.put(groupName, grantInfos);
               }
            }

            ret.setGroupPrivileges(groupPriv);
         }

         commited = this.commitTransaction();
      } finally {
         if (!commited) {
            this.rollbackTransaction();
         }

      }

      return ret;
   }

   public List getDBPrivilege(String dbName, String principalName, PrincipalType principalType) throws InvalidObjectException, MetaException {
      dbName = HiveStringUtils.normalizeIdentifier(dbName);
      if (principalName != null) {
         List<MDBPrivilege> userNameDbPriv = this.listPrincipalMDBGrants(principalName, principalType, dbName);
         if (userNameDbPriv != null && userNameDbPriv.size() > 0) {
            List<PrivilegeGrantInfo> grantInfos = new ArrayList(userNameDbPriv.size());

            for(int i = 0; i < userNameDbPriv.size(); ++i) {
               MDBPrivilege item = (MDBPrivilege)userNameDbPriv.get(i);
               grantInfos.add(new PrivilegeGrantInfo(item.getPrivilege(), item.getCreateTime(), item.getGrantor(), this.getPrincipalTypeFromStr(item.getGrantorType()), item.getGrantOption()));
            }

            return grantInfos;
         }
      }

      return new ArrayList(0);
   }

   public PrincipalPrivilegeSet getDBPrivilegeSet(String dbName, String userName, List groupNames) throws InvalidObjectException, MetaException {
      boolean commited = false;
      dbName = HiveStringUtils.normalizeIdentifier(dbName);
      PrincipalPrivilegeSet ret = new PrincipalPrivilegeSet();

      try {
         this.openTransaction();
         if (userName != null) {
            Map<String, List<PrivilegeGrantInfo>> dbUserPriv = new HashMap();
            dbUserPriv.put(userName, this.getDBPrivilege(dbName, userName, PrincipalType.USER));
            ret.setUserPrivileges(dbUserPriv);
         }

         if (groupNames != null && groupNames.size() > 0) {
            Map<String, List<PrivilegeGrantInfo>> dbGroupPriv = new HashMap();

            for(String groupName : groupNames) {
               dbGroupPriv.put(groupName, this.getDBPrivilege(dbName, groupName, PrincipalType.GROUP));
            }

            ret.setGroupPrivileges(dbGroupPriv);
         }

         Set<String> roleNames = this.listAllRolesInHierarchy(userName, groupNames);
         if (roleNames != null && roleNames.size() > 0) {
            Map<String, List<PrivilegeGrantInfo>> dbRolePriv = new HashMap();

            for(String roleName : roleNames) {
               dbRolePriv.put(roleName, this.getDBPrivilege(dbName, roleName, PrincipalType.ROLE));
            }

            ret.setRolePrivileges(dbRolePriv);
         }

         commited = this.commitTransaction();
      } finally {
         if (!commited) {
            this.rollbackTransaction();
         }

      }

      return ret;
   }

   public PrincipalPrivilegeSet getPartitionPrivilegeSet(String dbName, String tableName, String partition, String userName, List groupNames) throws InvalidObjectException, MetaException {
      boolean commited = false;
      PrincipalPrivilegeSet ret = new PrincipalPrivilegeSet();
      tableName = HiveStringUtils.normalizeIdentifier(tableName);
      dbName = HiveStringUtils.normalizeIdentifier(dbName);

      try {
         this.openTransaction();
         if (userName != null) {
            Map<String, List<PrivilegeGrantInfo>> partUserPriv = new HashMap();
            partUserPriv.put(userName, this.getPartitionPrivilege(dbName, tableName, partition, userName, PrincipalType.USER));
            ret.setUserPrivileges(partUserPriv);
         }

         if (groupNames != null && groupNames.size() > 0) {
            Map<String, List<PrivilegeGrantInfo>> partGroupPriv = new HashMap();

            for(String groupName : groupNames) {
               partGroupPriv.put(groupName, this.getPartitionPrivilege(dbName, tableName, partition, groupName, PrincipalType.GROUP));
            }

            ret.setGroupPrivileges(partGroupPriv);
         }

         Set<String> roleNames = this.listAllRolesInHierarchy(userName, groupNames);
         if (roleNames != null && roleNames.size() > 0) {
            Map<String, List<PrivilegeGrantInfo>> partRolePriv = new HashMap();

            for(String roleName : roleNames) {
               partRolePriv.put(roleName, this.getPartitionPrivilege(dbName, tableName, partition, roleName, PrincipalType.ROLE));
            }

            ret.setRolePrivileges(partRolePriv);
         }

         commited = this.commitTransaction();
      } finally {
         if (!commited) {
            this.rollbackTransaction();
         }

      }

      return ret;
   }

   public PrincipalPrivilegeSet getTablePrivilegeSet(String dbName, String tableName, String userName, List groupNames) throws InvalidObjectException, MetaException {
      boolean commited = false;
      PrincipalPrivilegeSet ret = new PrincipalPrivilegeSet();
      tableName = HiveStringUtils.normalizeIdentifier(tableName);
      dbName = HiveStringUtils.normalizeIdentifier(dbName);

      try {
         this.openTransaction();
         if (userName != null) {
            Map<String, List<PrivilegeGrantInfo>> tableUserPriv = new HashMap();
            tableUserPriv.put(userName, this.getTablePrivilege(dbName, tableName, userName, PrincipalType.USER));
            ret.setUserPrivileges(tableUserPriv);
         }

         if (groupNames != null && groupNames.size() > 0) {
            Map<String, List<PrivilegeGrantInfo>> tableGroupPriv = new HashMap();

            for(String groupName : groupNames) {
               tableGroupPriv.put(groupName, this.getTablePrivilege(dbName, tableName, groupName, PrincipalType.GROUP));
            }

            ret.setGroupPrivileges(tableGroupPriv);
         }

         Set<String> roleNames = this.listAllRolesInHierarchy(userName, groupNames);
         if (roleNames != null && roleNames.size() > 0) {
            Map<String, List<PrivilegeGrantInfo>> tableRolePriv = new HashMap();

            for(String roleName : roleNames) {
               tableRolePriv.put(roleName, this.getTablePrivilege(dbName, tableName, roleName, PrincipalType.ROLE));
            }

            ret.setRolePrivileges(tableRolePriv);
         }

         commited = this.commitTransaction();
      } finally {
         if (!commited) {
            this.rollbackTransaction();
         }

      }

      return ret;
   }

   public PrincipalPrivilegeSet getColumnPrivilegeSet(String dbName, String tableName, String partitionName, String columnName, String userName, List groupNames) throws InvalidObjectException, MetaException {
      tableName = HiveStringUtils.normalizeIdentifier(tableName);
      dbName = HiveStringUtils.normalizeIdentifier(dbName);
      columnName = HiveStringUtils.normalizeIdentifier(columnName);
      boolean commited = false;
      PrincipalPrivilegeSet ret = new PrincipalPrivilegeSet();

      try {
         this.openTransaction();
         if (userName != null) {
            Map<String, List<PrivilegeGrantInfo>> columnUserPriv = new HashMap();
            columnUserPriv.put(userName, this.getColumnPrivilege(dbName, tableName, columnName, partitionName, userName, PrincipalType.USER));
            ret.setUserPrivileges(columnUserPriv);
         }

         if (groupNames != null && groupNames.size() > 0) {
            Map<String, List<PrivilegeGrantInfo>> columnGroupPriv = new HashMap();

            for(String groupName : groupNames) {
               columnGroupPriv.put(groupName, this.getColumnPrivilege(dbName, tableName, columnName, partitionName, groupName, PrincipalType.GROUP));
            }

            ret.setGroupPrivileges(columnGroupPriv);
         }

         Set<String> roleNames = this.listAllRolesInHierarchy(userName, groupNames);
         if (roleNames != null && roleNames.size() > 0) {
            Map<String, List<PrivilegeGrantInfo>> columnRolePriv = new HashMap();

            for(String roleName : roleNames) {
               columnRolePriv.put(roleName, this.getColumnPrivilege(dbName, tableName, columnName, partitionName, roleName, PrincipalType.ROLE));
            }

            ret.setRolePrivileges(columnRolePriv);
         }

         commited = this.commitTransaction();
      } finally {
         if (!commited) {
            this.rollbackTransaction();
         }

      }

      return ret;
   }

   private List getPartitionPrivilege(String dbName, String tableName, String partName, String principalName, PrincipalType principalType) {
      tableName = HiveStringUtils.normalizeIdentifier(tableName);
      dbName = HiveStringUtils.normalizeIdentifier(dbName);
      if (principalName != null) {
         List<MPartitionPrivilege> userNameTabPartPriv = this.listPrincipalMPartitionGrants(principalName, principalType, dbName, tableName, partName);
         if (userNameTabPartPriv != null && userNameTabPartPriv.size() > 0) {
            List<PrivilegeGrantInfo> grantInfos = new ArrayList(userNameTabPartPriv.size());

            for(int i = 0; i < userNameTabPartPriv.size(); ++i) {
               MPartitionPrivilege item = (MPartitionPrivilege)userNameTabPartPriv.get(i);
               grantInfos.add(new PrivilegeGrantInfo(item.getPrivilege(), item.getCreateTime(), item.getGrantor(), this.getPrincipalTypeFromStr(item.getGrantorType()), item.getGrantOption()));
            }

            return grantInfos;
         }
      }

      return new ArrayList(0);
   }

   private PrincipalType getPrincipalTypeFromStr(String str) {
      return str == null ? null : PrincipalType.valueOf(str);
   }

   private List getTablePrivilege(String dbName, String tableName, String principalName, PrincipalType principalType) {
      tableName = HiveStringUtils.normalizeIdentifier(tableName);
      dbName = HiveStringUtils.normalizeIdentifier(dbName);
      if (principalName != null) {
         List<MTablePrivilege> userNameTabPartPriv = this.listAllMTableGrants(principalName, principalType, dbName, tableName);
         if (userNameTabPartPriv != null && userNameTabPartPriv.size() > 0) {
            List<PrivilegeGrantInfo> grantInfos = new ArrayList(userNameTabPartPriv.size());

            for(int i = 0; i < userNameTabPartPriv.size(); ++i) {
               MTablePrivilege item = (MTablePrivilege)userNameTabPartPriv.get(i);
               grantInfos.add(new PrivilegeGrantInfo(item.getPrivilege(), item.getCreateTime(), item.getGrantor(), this.getPrincipalTypeFromStr(item.getGrantorType()), item.getGrantOption()));
            }

            return grantInfos;
         }
      }

      return new ArrayList(0);
   }

   private List getColumnPrivilege(String dbName, String tableName, String columnName, String partitionName, String principalName, PrincipalType principalType) {
      tableName = HiveStringUtils.normalizeIdentifier(tableName);
      dbName = HiveStringUtils.normalizeIdentifier(dbName);
      columnName = HiveStringUtils.normalizeIdentifier(columnName);
      if (partitionName == null) {
         List<MTableColumnPrivilege> userNameColumnPriv = this.listPrincipalMTableColumnGrants(principalName, principalType, dbName, tableName, columnName);
         if (userNameColumnPriv != null && userNameColumnPriv.size() > 0) {
            List<PrivilegeGrantInfo> grantInfos = new ArrayList(userNameColumnPriv.size());

            for(int i = 0; i < userNameColumnPriv.size(); ++i) {
               MTableColumnPrivilege item = (MTableColumnPrivilege)userNameColumnPriv.get(i);
               grantInfos.add(new PrivilegeGrantInfo(item.getPrivilege(), item.getCreateTime(), item.getGrantor(), this.getPrincipalTypeFromStr(item.getGrantorType()), item.getGrantOption()));
            }

            return grantInfos;
         }
      } else {
         List<MPartitionColumnPrivilege> userNameColumnPriv = this.listPrincipalMPartitionColumnGrants(principalName, principalType, dbName, tableName, partitionName, columnName);
         if (userNameColumnPriv != null && userNameColumnPriv.size() > 0) {
            List<PrivilegeGrantInfo> grantInfos = new ArrayList(userNameColumnPriv.size());

            for(int i = 0; i < userNameColumnPriv.size(); ++i) {
               MPartitionColumnPrivilege item = (MPartitionColumnPrivilege)userNameColumnPriv.get(i);
               grantInfos.add(new PrivilegeGrantInfo(item.getPrivilege(), item.getCreateTime(), item.getGrantor(), this.getPrincipalTypeFromStr(item.getGrantorType()), item.getGrantOption()));
            }

            return grantInfos;
         }
      }

      return new ArrayList(0);
   }

   public boolean grantPrivileges(PrivilegeBag privileges) throws InvalidObjectException, MetaException, NoSuchObjectException {
      boolean committed = false;
      int now = (int)(System.currentTimeMillis() / 1000L);

      try {
         this.openTransaction();
         List<Object> persistentObjs = new ArrayList();
         List<HiveObjectPrivilege> privilegeList = privileges.getPrivileges();
         if (privilegeList != null && privilegeList.size() > 0) {
            Iterator<HiveObjectPrivilege> privIter = privilegeList.iterator();
            Set<String> privSet = new HashSet();

            while(privIter.hasNext()) {
               HiveObjectPrivilege privDef = (HiveObjectPrivilege)privIter.next();
               HiveObjectRef hiveObject = privDef.getHiveObject();
               String privilegeStr = privDef.getGrantInfo().getPrivilege();
               String[] privs = privilegeStr.split(",");
               String userName = privDef.getPrincipalName();
               PrincipalType principalType = privDef.getPrincipalType();
               String grantor = privDef.getGrantInfo().getGrantor();
               String grantorType = privDef.getGrantInfo().getGrantorType().toString();
               boolean grantOption = privDef.getGrantInfo().isGrantOption();
               privSet.clear();
               if (principalType == PrincipalType.ROLE) {
                  this.validateRole(userName);
               }

               if (hiveObject.getObjectType() == HiveObjectType.GLOBAL) {
                  List<MGlobalPrivilege> globalPrivs = this.listPrincipalMGlobalGrants(userName, principalType);
                  if (globalPrivs != null) {
                     for(MGlobalPrivilege priv : globalPrivs) {
                        if (priv.getGrantor().equalsIgnoreCase(grantor)) {
                           privSet.add(priv.getPrivilege());
                        }
                     }
                  }

                  for(String privilege : privs) {
                     if (privSet.contains(privilege)) {
                        throw new InvalidObjectException(privilege + " is already granted by " + grantor);
                     }

                     MGlobalPrivilege mGlobalPrivs = new MGlobalPrivilege(userName, principalType.toString(), privilege, now, grantor, grantorType, grantOption);
                     persistentObjs.add(mGlobalPrivs);
                  }
               } else if (hiveObject.getObjectType() == HiveObjectType.DATABASE) {
                  MDatabase dbObj = this.getMDatabase(hiveObject.getDbName());
                  if (dbObj != null) {
                     List<MDBPrivilege> dbPrivs = this.listPrincipalMDBGrants(userName, principalType, hiveObject.getDbName());
                     if (dbPrivs != null) {
                        for(MDBPrivilege priv : dbPrivs) {
                           if (priv.getGrantor().equalsIgnoreCase(grantor)) {
                              privSet.add(priv.getPrivilege());
                           }
                        }
                     }

                     for(String privilege : privs) {
                        if (privSet.contains(privilege)) {
                           throw new InvalidObjectException(privilege + " is already granted on database " + hiveObject.getDbName() + " by " + grantor);
                        }

                        MDBPrivilege mDb = new MDBPrivilege(userName, principalType.toString(), dbObj, privilege, now, grantor, grantorType, grantOption);
                        persistentObjs.add(mDb);
                     }
                  }
               } else if (hiveObject.getObjectType() == HiveObjectType.TABLE) {
                  MTable tblObj = this.getMTable(hiveObject.getDbName(), hiveObject.getObjectName());
                  if (tblObj != null) {
                     List<MTablePrivilege> tablePrivs = this.listAllMTableGrants(userName, principalType, hiveObject.getDbName(), hiveObject.getObjectName());
                     if (tablePrivs != null) {
                        for(MTablePrivilege priv : tablePrivs) {
                           if (priv.getGrantor() != null && priv.getGrantor().equalsIgnoreCase(grantor)) {
                              privSet.add(priv.getPrivilege());
                           }
                        }
                     }

                     for(String privilege : privs) {
                        if (privSet.contains(privilege)) {
                           throw new InvalidObjectException(privilege + " is already granted on table [" + hiveObject.getDbName() + "," + hiveObject.getObjectName() + "] by " + grantor);
                        }

                        MTablePrivilege mTab = new MTablePrivilege(userName, principalType.toString(), tblObj, privilege, now, grantor, grantorType, grantOption);
                        persistentObjs.add(mTab);
                     }
                  }
               } else if (hiveObject.getObjectType() == HiveObjectType.PARTITION) {
                  MPartition partObj = this.getMPartition(hiveObject.getDbName(), hiveObject.getObjectName(), hiveObject.getPartValues());
                  String partName = null;
                  if (partObj != null) {
                     partName = partObj.getPartitionName();
                     List<MPartitionPrivilege> partPrivs = this.listPrincipalMPartitionGrants(userName, principalType, hiveObject.getDbName(), hiveObject.getObjectName(), partObj.getPartitionName());
                     if (partPrivs != null) {
                        for(MPartitionPrivilege priv : partPrivs) {
                           if (priv.getGrantor().equalsIgnoreCase(grantor)) {
                              privSet.add(priv.getPrivilege());
                           }
                        }
                     }

                     for(String privilege : privs) {
                        if (privSet.contains(privilege)) {
                           throw new InvalidObjectException(privilege + " is already granted on partition [" + hiveObject.getDbName() + "," + hiveObject.getObjectName() + "," + partName + "] by " + grantor);
                        }

                        MPartitionPrivilege mTab = new MPartitionPrivilege(userName, principalType.toString(), partObj, privilege, now, grantor, grantorType, grantOption);
                        persistentObjs.add(mTab);
                     }
                  }
               } else if (hiveObject.getObjectType() == HiveObjectType.COLUMN) {
                  MTable tblObj = this.getMTable(hiveObject.getDbName(), hiveObject.getObjectName());
                  if (tblObj != null) {
                     if (hiveObject.getPartValues() != null) {
                        MPartition partObj = null;
                        List<MPartitionColumnPrivilege> colPrivs = null;
                        partObj = this.getMPartition(hiveObject.getDbName(), hiveObject.getObjectName(), hiveObject.getPartValues());
                        if (partObj != null) {
                           colPrivs = this.listPrincipalMPartitionColumnGrants(userName, principalType, hiveObject.getDbName(), hiveObject.getObjectName(), partObj.getPartitionName(), hiveObject.getColumnName());
                           if (colPrivs != null) {
                              for(MPartitionColumnPrivilege priv : colPrivs) {
                                 if (priv.getGrantor().equalsIgnoreCase(grantor)) {
                                    privSet.add(priv.getPrivilege());
                                 }
                              }
                           }

                           for(String privilege : privs) {
                              if (privSet.contains(privilege)) {
                                 throw new InvalidObjectException(privilege + " is already granted on column " + hiveObject.getColumnName() + " [" + hiveObject.getDbName() + "," + hiveObject.getObjectName() + "," + partObj.getPartitionName() + "] by " + grantor);
                              }

                              MPartitionColumnPrivilege mCol = new MPartitionColumnPrivilege(userName, principalType.toString(), partObj, hiveObject.getColumnName(), privilege, now, grantor, grantorType, grantOption);
                              persistentObjs.add(mCol);
                           }
                        }
                     } else {
                        List<MTableColumnPrivilege> colPrivs = null;
                        colPrivs = this.listPrincipalMTableColumnGrants(userName, principalType, hiveObject.getDbName(), hiveObject.getObjectName(), hiveObject.getColumnName());
                        if (colPrivs != null) {
                           for(MTableColumnPrivilege priv : colPrivs) {
                              if (priv.getGrantor().equalsIgnoreCase(grantor)) {
                                 privSet.add(priv.getPrivilege());
                              }
                           }
                        }

                        for(String privilege : privs) {
                           if (privSet.contains(privilege)) {
                              throw new InvalidObjectException(privilege + " is already granted on column " + hiveObject.getColumnName() + " [" + hiveObject.getDbName() + "," + hiveObject.getObjectName() + "] by " + grantor);
                           }

                           MTableColumnPrivilege mCol = new MTableColumnPrivilege(userName, principalType.toString(), tblObj, hiveObject.getColumnName(), privilege, now, grantor, grantorType, grantOption);
                           persistentObjs.add(mCol);
                        }
                     }
                  }
               }
            }
         }

         if (persistentObjs.size() > 0) {
            this.pm.makePersistentAll(persistentObjs);
         }

         committed = this.commitTransaction();
      } finally {
         if (!committed) {
            this.rollbackTransaction();
         }

      }

      return committed;
   }

   public boolean revokePrivileges(PrivilegeBag privileges, boolean grantOption) throws InvalidObjectException, MetaException, NoSuchObjectException {
      boolean committed = false;

      try {
         this.openTransaction();
         List<Object> persistentObjs = new ArrayList();
         List<HiveObjectPrivilege> privilegeList = privileges.getPrivileges();
         if (privilegeList != null && privilegeList.size() > 0) {
            for(HiveObjectPrivilege privDef : privilegeList) {
               HiveObjectRef hiveObject = privDef.getHiveObject();
               String privilegeStr = privDef.getGrantInfo().getPrivilege();
               if (privilegeStr != null && !privilegeStr.trim().equals("")) {
                  String[] privs = privilegeStr.split(",");
                  String userName = privDef.getPrincipalName();
                  PrincipalType principalType = privDef.getPrincipalType();
                  if (hiveObject.getObjectType() == HiveObjectType.GLOBAL) {
                     List<MGlobalPrivilege> mSecUser = this.listPrincipalMGlobalGrants(userName, principalType);
                     boolean found = false;
                     if (mSecUser != null) {
                        for(String privilege : privs) {
                           for(MGlobalPrivilege userGrant : mSecUser) {
                              String userGrantPrivs = userGrant.getPrivilege();
                              if (privilege.equals(userGrantPrivs)) {
                                 found = true;
                                 if (grantOption) {
                                    if (!userGrant.getGrantOption()) {
                                       throw new MetaException("User " + userName + " does not have grant option with privilege " + privilege);
                                    }

                                    userGrant.setGrantOption(false);
                                 }

                                 persistentObjs.add(userGrant);
                                 break;
                              }
                           }

                           if (!found) {
                              throw new InvalidObjectException("No user grant found for privileges " + privilege);
                           }
                        }
                     }
                  } else if (hiveObject.getObjectType() == HiveObjectType.DATABASE) {
                     MDatabase dbObj = this.getMDatabase(hiveObject.getDbName());
                     if (dbObj != null) {
                        String db = hiveObject.getDbName();
                        boolean found = false;
                        List<MDBPrivilege> dbGrants = this.listPrincipalMDBGrants(userName, principalType, db);

                        for(String privilege : privs) {
                           for(MDBPrivilege dbGrant : dbGrants) {
                              String dbGrantPriv = dbGrant.getPrivilege();
                              if (privilege.equals(dbGrantPriv)) {
                                 found = true;
                                 if (grantOption) {
                                    if (!dbGrant.getGrantOption()) {
                                       throw new MetaException("User " + userName + " does not have grant option with privilege " + privilege);
                                    }

                                    dbGrant.setGrantOption(false);
                                 }

                                 persistentObjs.add(dbGrant);
                                 break;
                              }
                           }

                           if (!found) {
                              throw new InvalidObjectException("No database grant found for privileges " + privilege + " on database " + db);
                           }
                        }
                     }
                  } else if (hiveObject.getObjectType() == HiveObjectType.TABLE) {
                     boolean found = false;
                     List<MTablePrivilege> tableGrants = this.listAllMTableGrants(userName, principalType, hiveObject.getDbName(), hiveObject.getObjectName());

                     for(String privilege : privs) {
                        for(MTablePrivilege tabGrant : tableGrants) {
                           String tableGrantPriv = tabGrant.getPrivilege();
                           if (privilege.equalsIgnoreCase(tableGrantPriv)) {
                              found = true;
                              if (grantOption) {
                                 if (!tabGrant.getGrantOption()) {
                                    throw new MetaException("User " + userName + " does not have grant option with privilege " + privilege);
                                 }

                                 tabGrant.setGrantOption(false);
                              }

                              persistentObjs.add(tabGrant);
                              break;
                           }
                        }

                        if (!found) {
                           throw new InvalidObjectException("No grant (" + privilege + ") found  on table " + hiveObject.getObjectName() + ", database is " + hiveObject.getDbName());
                        }
                     }
                  } else if (hiveObject.getObjectType() == HiveObjectType.PARTITION) {
                     boolean found = false;
                     Table tabObj = this.getTable(hiveObject.getDbName(), hiveObject.getObjectName());
                     String partName = null;
                     if (hiveObject.getPartValues() != null) {
                        partName = Warehouse.makePartName(tabObj.getPartitionKeys(), hiveObject.getPartValues());
                     }

                     List<MPartitionPrivilege> partitionGrants = this.listPrincipalMPartitionGrants(userName, principalType, hiveObject.getDbName(), hiveObject.getObjectName(), partName);

                     for(String privilege : privs) {
                        for(MPartitionPrivilege partGrant : partitionGrants) {
                           String partPriv = partGrant.getPrivilege();
                           if (partPriv.equalsIgnoreCase(privilege)) {
                              found = true;
                              if (grantOption) {
                                 if (!partGrant.getGrantOption()) {
                                    throw new MetaException("User " + userName + " does not have grant option with privilege " + privilege);
                                 }

                                 partGrant.setGrantOption(false);
                              }

                              persistentObjs.add(partGrant);
                              break;
                           }
                        }

                        if (!found) {
                           throw new InvalidObjectException("No grant (" + privilege + ") found  on table " + tabObj.getTableName() + ", partition is " + partName + ", database is " + tabObj.getDbName());
                        }
                     }
                  } else if (hiveObject.getObjectType() == HiveObjectType.COLUMN) {
                     Table tabObj = this.getTable(hiveObject.getDbName(), hiveObject.getObjectName());
                     String partName = null;
                     if (hiveObject.getPartValues() != null) {
                        partName = Warehouse.makePartName(tabObj.getPartitionKeys(), hiveObject.getPartValues());
                     }

                     if (partName != null) {
                        List<MPartitionColumnPrivilege> mSecCol = this.listPrincipalMPartitionColumnGrants(userName, principalType, hiveObject.getDbName(), hiveObject.getObjectName(), partName, hiveObject.getColumnName());
                        boolean found = false;
                        if (mSecCol != null) {
                           for(String privilege : privs) {
                              for(MPartitionColumnPrivilege col : mSecCol) {
                                 String colPriv = col.getPrivilege();
                                 if (colPriv.equalsIgnoreCase(privilege)) {
                                    found = true;
                                    if (grantOption) {
                                       if (!col.getGrantOption()) {
                                          throw new MetaException("User " + userName + " does not have grant option with privilege " + privilege);
                                       }

                                       col.setGrantOption(false);
                                    }

                                    persistentObjs.add(col);
                                    break;
                                 }
                              }

                              if (!found) {
                                 throw new InvalidObjectException("No grant (" + privilege + ") found  on table " + tabObj.getTableName() + ", partition is " + partName + ", column name = " + hiveObject.getColumnName() + ", database is " + tabObj.getDbName());
                              }
                           }
                        }
                     } else {
                        List<MTableColumnPrivilege> mSecCol = this.listPrincipalMTableColumnGrants(userName, principalType, hiveObject.getDbName(), hiveObject.getObjectName(), hiveObject.getColumnName());
                        boolean found = false;
                        if (mSecCol != null) {
                           for(String privilege : privs) {
                              for(MTableColumnPrivilege col : mSecCol) {
                                 String colPriv = col.getPrivilege();
                                 if (colPriv.equalsIgnoreCase(privilege)) {
                                    found = true;
                                    if (grantOption) {
                                       if (!col.getGrantOption()) {
                                          throw new MetaException("User " + userName + " does not have grant option with privilege " + privilege);
                                       }

                                       col.setGrantOption(false);
                                    }

                                    persistentObjs.add(col);
                                    break;
                                 }
                              }

                              if (!found) {
                                 throw new InvalidObjectException("No grant (" + privilege + ") found  on table " + tabObj.getTableName() + ", column name = " + hiveObject.getColumnName() + ", database is " + tabObj.getDbName());
                              }
                           }
                        }
                     }
                  }
               }
            }
         }

         if (persistentObjs.size() > 0 && !grantOption) {
            this.pm.deletePersistentAll(persistentObjs);
         }

         committed = this.commitTransaction();
      } finally {
         if (!committed) {
            this.rollbackTransaction();
         }

      }

      return committed;
   }

   public List listMRoleMembers(String roleName) {
      boolean success = false;
      Query query = null;
      List<MRoleMap> mRoleMemeberList = new ArrayList();

      try {
         LOG.debug("Executing listRoleMembers");
         this.openTransaction();
         query = this.pm.newQuery(MRoleMap.class, "role.roleName == t1");
         query.declareParameters("java.lang.String t1");
         query.setUnique(false);
         List<MRoleMap> mRoles = (List)query.execute(roleName);
         this.pm.retrieveAll(mRoles);
         success = this.commitTransaction();
         mRoleMemeberList.addAll(mRoles);
         LOG.debug("Done retrieving all objects for listRoleMembers");
      } finally {
         this.rollbackAndCleanup(success, query);
      }

      return mRoleMemeberList;
   }

   public List listRoleMembers(String roleName) {
      List<MRoleMap> roleMaps = this.listMRoleMembers(roleName);
      List<RolePrincipalGrant> rolePrinGrantList = new ArrayList();
      if (roleMaps != null) {
         for(MRoleMap roleMap : roleMaps) {
            RolePrincipalGrant rolePrinGrant = new RolePrincipalGrant(roleMap.getRole().getRoleName(), roleMap.getPrincipalName(), PrincipalType.valueOf(roleMap.getPrincipalType()), roleMap.getGrantOption(), roleMap.getAddTime(), roleMap.getGrantor(), roleMap.getGrantorType() == null ? null : PrincipalType.valueOf(roleMap.getGrantorType()));
            rolePrinGrantList.add(rolePrinGrant);
         }
      }

      return rolePrinGrantList;
   }

   public List listPrincipalMGlobalGrants(String principalName, PrincipalType principalType) {
      boolean commited = false;
      Query query = null;
      List<MGlobalPrivilege> userNameDbPriv = new ArrayList();

      try {
         List<MGlobalPrivilege> mPrivs = null;
         this.openTransaction();
         if (principalName != null) {
            query = this.pm.newQuery(MGlobalPrivilege.class, "principalName == t1 && principalType == t2 ");
            query.declareParameters("java.lang.String t1, java.lang.String t2");
            mPrivs = (List)query.executeWithArray(new Object[]{principalName, principalType.toString()});
            this.pm.retrieveAll(mPrivs);
         }

         commited = this.commitTransaction();
         if (mPrivs != null) {
            userNameDbPriv.addAll(mPrivs);
         }
      } finally {
         this.rollbackAndCleanup(commited, query);
      }

      return userNameDbPriv;
   }

   public List listPrincipalGlobalGrants(String principalName, PrincipalType principalType) {
      List<MGlobalPrivilege> mUsers = this.listPrincipalMGlobalGrants(principalName, principalType);
      if (mUsers.isEmpty()) {
         return Collections.emptyList();
      } else {
         List<HiveObjectPrivilege> result = new ArrayList();

         for(int i = 0; i < mUsers.size(); ++i) {
            MGlobalPrivilege sUsr = (MGlobalPrivilege)mUsers.get(i);
            HiveObjectRef objectRef = new HiveObjectRef(HiveObjectType.GLOBAL, (String)null, (String)null, (List)null, (String)null);
            HiveObjectPrivilege secUser = new HiveObjectPrivilege(objectRef, sUsr.getPrincipalName(), principalType, new PrivilegeGrantInfo(sUsr.getPrivilege(), sUsr.getCreateTime(), sUsr.getGrantor(), PrincipalType.valueOf(sUsr.getGrantorType()), sUsr.getGrantOption()));
            result.add(secUser);
         }

         return result;
      }
   }

   public List listGlobalGrantsAll() {
      boolean commited = false;
      Query query = null;

      List var4;
      try {
         this.openTransaction();
         query = this.pm.newQuery(MGlobalPrivilege.class);
         List<MGlobalPrivilege> userNameDbPriv = (List)query.execute();
         this.pm.retrieveAll(userNameDbPriv);
         commited = this.commitTransaction();
         var4 = this.convertGlobal(userNameDbPriv);
      } finally {
         this.rollbackAndCleanup(commited, query);
      }

      return var4;
   }

   private List convertGlobal(List privs) {
      List<HiveObjectPrivilege> result = new ArrayList();

      for(MGlobalPrivilege priv : privs) {
         String pname = priv.getPrincipalName();
         PrincipalType ptype = PrincipalType.valueOf(priv.getPrincipalType());
         HiveObjectRef objectRef = new HiveObjectRef(HiveObjectType.GLOBAL, (String)null, (String)null, (List)null, (String)null);
         PrivilegeGrantInfo grantor = new PrivilegeGrantInfo(priv.getPrivilege(), priv.getCreateTime(), priv.getGrantor(), PrincipalType.valueOf(priv.getGrantorType()), priv.getGrantOption());
         result.add(new HiveObjectPrivilege(objectRef, pname, ptype, grantor));
      }

      return result;
   }

   public List listPrincipalMDBGrants(String principalName, PrincipalType principalType, String dbName) {
      boolean success = false;
      Query query = null;
      List<MDBPrivilege> mSecurityDBList = new ArrayList();
      dbName = HiveStringUtils.normalizeIdentifier(dbName);

      try {
         LOG.debug("Executing listPrincipalDBGrants");
         this.openTransaction();
         query = this.pm.newQuery(MDBPrivilege.class, "principalName == t1 && principalType == t2 && database.name == t3");
         query.declareParameters("java.lang.String t1, java.lang.String t2, java.lang.String t3");
         List<MDBPrivilege> mPrivs = (List)query.executeWithArray(new Object[]{principalName, principalType.toString(), dbName});
         this.pm.retrieveAll(mPrivs);
         success = this.commitTransaction();
         mSecurityDBList.addAll(mPrivs);
         LOG.debug("Done retrieving all objects for listPrincipalDBGrants");
      } finally {
         this.rollbackAndCleanup(success, query);
      }

      return mSecurityDBList;
   }

   public List listPrincipalDBGrants(String principalName, PrincipalType principalType, String dbName) {
      List<MDBPrivilege> mDbs = this.listPrincipalMDBGrants(principalName, principalType, dbName);
      if (mDbs.isEmpty()) {
         return Collections.emptyList();
      } else {
         List<HiveObjectPrivilege> result = new ArrayList();

         for(int i = 0; i < mDbs.size(); ++i) {
            MDBPrivilege sDB = (MDBPrivilege)mDbs.get(i);
            HiveObjectRef objectRef = new HiveObjectRef(HiveObjectType.DATABASE, dbName, (String)null, (List)null, (String)null);
            HiveObjectPrivilege secObj = new HiveObjectPrivilege(objectRef, sDB.getPrincipalName(), principalType, new PrivilegeGrantInfo(sDB.getPrivilege(), sDB.getCreateTime(), sDB.getGrantor(), PrincipalType.valueOf(sDB.getGrantorType()), sDB.getGrantOption()));
            result.add(secObj);
         }

         return result;
      }
   }

   public List listPrincipalDBGrantsAll(String principalName, PrincipalType principalType) {
      QueryWrapper queryWrapper = new QueryWrapper();

      List var4;
      try {
         var4 = this.convertDB(this.listPrincipalAllDBGrant(principalName, principalType, queryWrapper));
      } finally {
         queryWrapper.close();
      }

      return var4;
   }

   public List listDBGrantsAll(String dbName) {
      QueryWrapper queryWrapper = new QueryWrapper();

      List var3;
      try {
         var3 = this.convertDB(this.listDatabaseGrants(dbName, queryWrapper));
      } finally {
         queryWrapper.close();
      }

      return var3;
   }

   private List convertDB(List privs) {
      List<HiveObjectPrivilege> result = new ArrayList();

      for(MDBPrivilege priv : privs) {
         String pname = priv.getPrincipalName();
         PrincipalType ptype = PrincipalType.valueOf(priv.getPrincipalType());
         String database = priv.getDatabase().getName();
         HiveObjectRef objectRef = new HiveObjectRef(HiveObjectType.DATABASE, database, (String)null, (List)null, (String)null);
         PrivilegeGrantInfo grantor = new PrivilegeGrantInfo(priv.getPrivilege(), priv.getCreateTime(), priv.getGrantor(), PrincipalType.valueOf(priv.getGrantorType()), priv.getGrantOption());
         result.add(new HiveObjectPrivilege(objectRef, pname, ptype, grantor));
      }

      return result;
   }

   private List listPrincipalAllDBGrant(String principalName, PrincipalType principalType, QueryWrapper queryWrapper) {
      boolean success = false;
      Query query = null;
      List<MDBPrivilege> mSecurityDBList = null;

      try {
         LOG.debug("Executing listPrincipalAllDBGrant");
         this.openTransaction();
         if (principalName != null && principalType != null) {
            query = queryWrapper.query = this.pm.newQuery(MDBPrivilege.class, "principalName == t1 && principalType == t2");
            query.declareParameters("java.lang.String t1, java.lang.String t2");
            mSecurityDBList = (List)query.execute(principalName, principalType.toString());
         } else {
            query = queryWrapper.query = this.pm.newQuery(MDBPrivilege.class);
            mSecurityDBList = (List)query.execute();
         }

         this.pm.retrieveAll(mSecurityDBList);
         success = this.commitTransaction();
         LOG.debug("Done retrieving all objects for listPrincipalAllDBGrant");
      } finally {
         if (!success) {
            this.rollbackTransaction();
         }

      }

      return mSecurityDBList;
   }

   public List listAllTableGrants(String dbName, String tableName) {
      boolean success = false;
      Query query = null;
      tableName = HiveStringUtils.normalizeIdentifier(tableName);
      dbName = HiveStringUtils.normalizeIdentifier(dbName);
      List<MTablePrivilege> mSecurityTabList = new ArrayList();
      tableName = HiveStringUtils.normalizeIdentifier(tableName);
      dbName = HiveStringUtils.normalizeIdentifier(dbName);

      try {
         LOG.debug("Executing listAllTableGrants");
         this.openTransaction();
         String queryStr = "table.tableName == t1 && table.database.name == t2";
         query = this.pm.newQuery(MTablePrivilege.class, queryStr);
         query.declareParameters("java.lang.String t1, java.lang.String t2");
         List<MTablePrivilege> mPrivs = (List)query.executeWithArray(new Object[]{tableName, dbName});
         LOG.debug("Done executing query for listAllTableGrants");
         this.pm.retrieveAll(mPrivs);
         success = this.commitTransaction();
         mSecurityTabList.addAll(mPrivs);
         LOG.debug("Done retrieving all objects for listAllTableGrants");
      } finally {
         this.rollbackAndCleanup(success, query);
      }

      return mSecurityTabList;
   }

   public List listTableAllPartitionGrants(String dbName, String tableName) {
      tableName = HiveStringUtils.normalizeIdentifier(tableName);
      dbName = HiveStringUtils.normalizeIdentifier(dbName);
      boolean success = false;
      Query query = null;
      List<MPartitionPrivilege> mSecurityTabPartList = new ArrayList();

      try {
         LOG.debug("Executing listTableAllPartitionGrants");
         this.openTransaction();
         String queryStr = "partition.table.tableName == t1 && partition.table.database.name == t2";
         query = this.pm.newQuery(MPartitionPrivilege.class, queryStr);
         query.declareParameters("java.lang.String t1, java.lang.String t2");
         List<MPartitionPrivilege> mPrivs = (List)query.executeWithArray(new Object[]{tableName, dbName});
         this.pm.retrieveAll(mPrivs);
         success = this.commitTransaction();
         mSecurityTabPartList.addAll(mPrivs);
         LOG.debug("Done retrieving all objects for listTableAllPartitionGrants");
      } finally {
         this.rollbackAndCleanup(success, query);
      }

      return mSecurityTabPartList;
   }

   public List listTableAllColumnGrants(String dbName, String tableName) {
      boolean success = false;
      Query query = null;
      List<MTableColumnPrivilege> mTblColPrivilegeList = new ArrayList();
      tableName = HiveStringUtils.normalizeIdentifier(tableName);
      dbName = HiveStringUtils.normalizeIdentifier(dbName);

      try {
         LOG.debug("Executing listTableAllColumnGrants");
         this.openTransaction();
         String queryStr = "table.tableName == t1 && table.database.name == t2";
         query = this.pm.newQuery(MTableColumnPrivilege.class, queryStr);
         query.declareParameters("java.lang.String t1, java.lang.String t2");
         List<MTableColumnPrivilege> mPrivs = (List)query.executeWithArray(new Object[]{tableName, dbName});
         this.pm.retrieveAll(mPrivs);
         success = this.commitTransaction();
         mTblColPrivilegeList.addAll(mPrivs);
         LOG.debug("Done retrieving all objects for listTableAllColumnGrants");
      } finally {
         this.rollbackAndCleanup(success, query);
      }

      return mTblColPrivilegeList;
   }

   public List listTableAllPartitionColumnGrants(String dbName, String tableName) {
      boolean success = false;
      Query query = null;
      tableName = HiveStringUtils.normalizeIdentifier(tableName);
      dbName = HiveStringUtils.normalizeIdentifier(dbName);
      List<MPartitionColumnPrivilege> mSecurityColList = new ArrayList();

      try {
         LOG.debug("Executing listTableAllPartitionColumnGrants");
         this.openTransaction();
         String queryStr = "partition.table.tableName == t1 && partition.table.database.name == t2";
         query = this.pm.newQuery(MPartitionColumnPrivilege.class, queryStr);
         query.declareParameters("java.lang.String t1, java.lang.String t2");
         List<MPartitionColumnPrivilege> mPrivs = (List)query.executeWithArray(new Object[]{tableName, dbName});
         this.pm.retrieveAll(mPrivs);
         success = this.commitTransaction();
         mSecurityColList.addAll(mPrivs);
         LOG.debug("Done retrieving all objects for listTableAllPartitionColumnGrants");
      } finally {
         this.rollbackAndCleanup(success, query);
      }

      return mSecurityColList;
   }

   public List listPartitionAllColumnGrants(String dbName, String tableName, List partNames) {
      boolean success = false;
      tableName = HiveStringUtils.normalizeIdentifier(tableName);
      dbName = HiveStringUtils.normalizeIdentifier(dbName);
      List<MPartitionColumnPrivilege> mSecurityColList = null;

      try {
         this.openTransaction();
         LOG.debug("Executing listPartitionAllColumnGrants");
         mSecurityColList = this.queryByPartitionNames(dbName, tableName, partNames, MPartitionColumnPrivilege.class, "partition.table.tableName", "partition.table.database.name", "partition.partitionName");
         LOG.debug("Done executing query for listPartitionAllColumnGrants");
         this.pm.retrieveAll(mSecurityColList);
         success = this.commitTransaction();
         LOG.debug("Done retrieving all objects for listPartitionAllColumnGrants");
      } finally {
         if (!success) {
            this.rollbackTransaction();
         }

      }

      return mSecurityColList;
   }

   public void dropPartitionAllColumnGrantsNoTxn(String dbName, String tableName, List partNames) {
      ObjectPair<Query, Object[]> queryWithParams = this.makeQueryByPartitionNames(dbName, tableName, partNames, MPartitionColumnPrivilege.class, "partition.table.tableName", "partition.table.database.name", "partition.partitionName");
      ((Query)queryWithParams.getFirst()).deletePersistentAll(queryWithParams.getSecond());
   }

   private List listDatabaseGrants(String dbName, QueryWrapper queryWrapper) {
      dbName = HiveStringUtils.normalizeIdentifier(dbName);
      boolean success = false;

      List var6;
      try {
         LOG.debug("Executing listDatabaseGrants");
         this.openTransaction();
         Query query = queryWrapper.query = this.pm.newQuery(MDBPrivilege.class, "database.name == t1");
         query.declareParameters("java.lang.String t1");
         List<MDBPrivilege> mSecurityDBList = (List)query.executeWithArray(new Object[]{dbName});
         this.pm.retrieveAll(mSecurityDBList);
         success = this.commitTransaction();
         LOG.debug("Done retrieving all objects for listDatabaseGrants");
         var6 = mSecurityDBList;
      } finally {
         if (!success) {
            this.rollbackTransaction();
         }

      }

      return var6;
   }

   private List listPartitionGrants(String dbName, String tableName, List partNames) {
      tableName = HiveStringUtils.normalizeIdentifier(tableName);
      dbName = HiveStringUtils.normalizeIdentifier(dbName);
      boolean success = false;
      List<MPartitionPrivilege> mSecurityTabPartList = null;

      try {
         this.openTransaction();
         LOG.debug("Executing listPartitionGrants");
         mSecurityTabPartList = this.queryByPartitionNames(dbName, tableName, partNames, MPartitionPrivilege.class, "partition.table.tableName", "partition.table.database.name", "partition.partitionName");
         LOG.debug("Done executing query for listPartitionGrants");
         this.pm.retrieveAll(mSecurityTabPartList);
         success = this.commitTransaction();
         LOG.debug("Done retrieving all objects for listPartitionGrants");
      } finally {
         if (!success) {
            this.rollbackTransaction();
         }

      }

      return mSecurityTabPartList;
   }

   private void dropPartitionGrantsNoTxn(String dbName, String tableName, List partNames) {
      ObjectPair<Query, Object[]> queryWithParams = this.makeQueryByPartitionNames(dbName, tableName, partNames, MPartitionPrivilege.class, "partition.table.tableName", "partition.table.database.name", "partition.partitionName");
      ((Query)queryWithParams.getFirst()).deletePersistentAll(queryWithParams.getSecond());
   }

   private List queryByPartitionNames(String dbName, String tableName, List partNames, Class clazz, String tbCol, String dbCol, String partCol) {
      ObjectPair<Query, Object[]> queryAndParams = this.makeQueryByPartitionNames(dbName, tableName, partNames, clazz, tbCol, dbCol, partCol);
      return (List)((Query)queryAndParams.getFirst()).executeWithArray(queryAndParams.getSecond());
   }

   private ObjectPair makeQueryByPartitionNames(String dbName, String tableName, List partNames, Class clazz, String tbCol, String dbCol, String partCol) {
      String queryStr = tbCol + " == t1 && " + dbCol + " == t2";
      String paramStr = "java.lang.String t1, java.lang.String t2";
      Object[] params = new Object[2 + partNames.size()];
      params[0] = HiveStringUtils.normalizeIdentifier(tableName);
      params[1] = HiveStringUtils.normalizeIdentifier(dbName);
      int index = 0;

      for(String partName : partNames) {
         params[index + 2] = partName;
         queryStr = queryStr + (index == 0 ? " && (" : " || ") + partCol + " == p" + index;
         paramStr = paramStr + ", java.lang.String p" + index;
         ++index;
      }

      queryStr = queryStr + ")";
      Query query = this.pm.newQuery(clazz, queryStr);
      query.declareParameters(paramStr);
      return new ObjectPair(query, params);
   }

   public List listAllMTableGrants(String principalName, PrincipalType principalType, String dbName, String tableName) {
      tableName = HiveStringUtils.normalizeIdentifier(tableName);
      dbName = HiveStringUtils.normalizeIdentifier(dbName);
      boolean success = false;
      Query query = null;
      List<MTablePrivilege> mSecurityTabPartList = new ArrayList();

      try {
         this.openTransaction();
         LOG.debug("Executing listAllTableGrants");
         query = this.pm.newQuery(MTablePrivilege.class, "principalName == t1 && principalType == t2 && table.tableName == t3 && table.database.name == t4");
         query.declareParameters("java.lang.String t1, java.lang.String t2, java.lang.String t3, java.lang.String t4");
         List<MTablePrivilege> mPrivs = (List)query.executeWithArray(new Object[]{principalName, principalType.toString(), tableName, dbName});
         this.pm.retrieveAll(mPrivs);
         success = this.commitTransaction();
         mSecurityTabPartList.addAll(mPrivs);
         LOG.debug("Done retrieving all objects for listAllTableGrants");
      } finally {
         this.rollbackAndCleanup(success, query);
      }

      return mSecurityTabPartList;
   }

   public List listAllTableGrants(String principalName, PrincipalType principalType, String dbName, String tableName) {
      List<MTablePrivilege> mTbls = this.listAllMTableGrants(principalName, principalType, dbName, tableName);
      if (mTbls.isEmpty()) {
         return Collections.emptyList();
      } else {
         List<HiveObjectPrivilege> result = new ArrayList();

         for(int i = 0; i < mTbls.size(); ++i) {
            MTablePrivilege sTbl = (MTablePrivilege)mTbls.get(i);
            HiveObjectRef objectRef = new HiveObjectRef(HiveObjectType.TABLE, dbName, tableName, (List)null, (String)null);
            HiveObjectPrivilege secObj = new HiveObjectPrivilege(objectRef, sTbl.getPrincipalName(), principalType, new PrivilegeGrantInfo(sTbl.getPrivilege(), sTbl.getCreateTime(), sTbl.getGrantor(), PrincipalType.valueOf(sTbl.getGrantorType()), sTbl.getGrantOption()));
            result.add(secObj);
         }

         return result;
      }
   }

   public List listPrincipalMPartitionGrants(String principalName, PrincipalType principalType, String dbName, String tableName, String partName) {
      boolean success = false;
      Query query = null;
      tableName = HiveStringUtils.normalizeIdentifier(tableName);
      dbName = HiveStringUtils.normalizeIdentifier(dbName);
      List<MPartitionPrivilege> mSecurityTabPartList = new ArrayList();

      try {
         LOG.debug("Executing listPrincipalPartitionGrants");
         this.openTransaction();
         query = this.pm.newQuery(MPartitionPrivilege.class, "principalName == t1 && principalType == t2 && partition.table.tableName == t3 && partition.table.database.name == t4 && partition.partitionName == t5");
         query.declareParameters("java.lang.String t1, java.lang.String t2, java.lang.String t3, java.lang.String t4, java.lang.String t5");
         List<MPartitionPrivilege> mPrivs = (List)query.executeWithArray(new Object[]{principalName, principalType.toString(), tableName, dbName, partName});
         this.pm.retrieveAll(mPrivs);
         success = this.commitTransaction();
         mSecurityTabPartList.addAll(mPrivs);
         LOG.debug("Done retrieving all objects for listPrincipalPartitionGrants");
      } finally {
         this.rollbackAndCleanup(success, query);
      }

      return mSecurityTabPartList;
   }

   public List listPrincipalPartitionGrants(String principalName, PrincipalType principalType, String dbName, String tableName, List partValues, String partName) {
      List<MPartitionPrivilege> mParts = this.listPrincipalMPartitionGrants(principalName, principalType, dbName, tableName, partName);
      if (mParts.isEmpty()) {
         return Collections.emptyList();
      } else {
         List<HiveObjectPrivilege> result = new ArrayList();

         for(int i = 0; i < mParts.size(); ++i) {
            MPartitionPrivilege sPart = (MPartitionPrivilege)mParts.get(i);
            HiveObjectRef objectRef = new HiveObjectRef(HiveObjectType.PARTITION, dbName, tableName, partValues, (String)null);
            HiveObjectPrivilege secObj = new HiveObjectPrivilege(objectRef, sPart.getPrincipalName(), principalType, new PrivilegeGrantInfo(sPart.getPrivilege(), sPart.getCreateTime(), sPart.getGrantor(), PrincipalType.valueOf(sPart.getGrantorType()), sPart.getGrantOption()));
            result.add(secObj);
         }

         return result;
      }
   }

   public List listPrincipalMTableColumnGrants(String principalName, PrincipalType principalType, String dbName, String tableName, String columnName) {
      boolean success = false;
      Query query = null;
      tableName = HiveStringUtils.normalizeIdentifier(tableName);
      dbName = HiveStringUtils.normalizeIdentifier(dbName);
      columnName = HiveStringUtils.normalizeIdentifier(columnName);
      List<MTableColumnPrivilege> mSecurityColList = new ArrayList();

      try {
         LOG.debug("Executing listPrincipalTableColumnGrants");
         this.openTransaction();
         String queryStr = "principalName == t1 && principalType == t2 && table.tableName == t3 && table.database.name == t4 &&  columnName == t5 ";
         query = this.pm.newQuery(MTableColumnPrivilege.class, queryStr);
         query.declareParameters("java.lang.String t1, java.lang.String t2, java.lang.String t3, java.lang.String t4, java.lang.String t5");
         List<MTableColumnPrivilege> mPrivs = (List)query.executeWithArray(new Object[]{principalName, principalType.toString(), tableName, dbName, columnName});
         this.pm.retrieveAll(mPrivs);
         success = this.commitTransaction();
         mSecurityColList.addAll(mPrivs);
         LOG.debug("Done retrieving all objects for listPrincipalTableColumnGrants");
      } finally {
         this.rollbackAndCleanup(success, query);
      }

      return mSecurityColList;
   }

   public List listPrincipalTableColumnGrants(String principalName, PrincipalType principalType, String dbName, String tableName, String columnName) {
      List<MTableColumnPrivilege> mTableCols = this.listPrincipalMTableColumnGrants(principalName, principalType, dbName, tableName, columnName);
      if (mTableCols.isEmpty()) {
         return Collections.emptyList();
      } else {
         List<HiveObjectPrivilege> result = new ArrayList();

         for(int i = 0; i < mTableCols.size(); ++i) {
            MTableColumnPrivilege sCol = (MTableColumnPrivilege)mTableCols.get(i);
            HiveObjectRef objectRef = new HiveObjectRef(HiveObjectType.COLUMN, dbName, tableName, (List)null, sCol.getColumnName());
            HiveObjectPrivilege secObj = new HiveObjectPrivilege(objectRef, sCol.getPrincipalName(), principalType, new PrivilegeGrantInfo(sCol.getPrivilege(), sCol.getCreateTime(), sCol.getGrantor(), PrincipalType.valueOf(sCol.getGrantorType()), sCol.getGrantOption()));
            result.add(secObj);
         }

         return result;
      }
   }

   public List listPrincipalMPartitionColumnGrants(String principalName, PrincipalType principalType, String dbName, String tableName, String partitionName, String columnName) {
      boolean success = false;
      Query query = null;
      tableName = HiveStringUtils.normalizeIdentifier(tableName);
      dbName = HiveStringUtils.normalizeIdentifier(dbName);
      columnName = HiveStringUtils.normalizeIdentifier(columnName);
      List<MPartitionColumnPrivilege> mSecurityColList = new ArrayList();

      try {
         LOG.debug("Executing listPrincipalPartitionColumnGrants");
         this.openTransaction();
         query = this.pm.newQuery(MPartitionColumnPrivilege.class, "principalName == t1 && principalType == t2 && partition.table.tableName == t3 && partition.table.database.name == t4 && partition.partitionName == t5 && columnName == t6");
         query.declareParameters("java.lang.String t1, java.lang.String t2, java.lang.String t3, java.lang.String t4, java.lang.String t5, java.lang.String t6");
         List<MPartitionColumnPrivilege> mPrivs = (List)query.executeWithArray(new Object[]{principalName, principalType.toString(), tableName, dbName, partitionName, columnName});
         this.pm.retrieveAll(mPrivs);
         success = this.commitTransaction();
         mSecurityColList.addAll(mPrivs);
         LOG.debug("Done retrieving all objects for listPrincipalPartitionColumnGrants");
      } finally {
         this.rollbackAndCleanup(success, query);
      }

      return mSecurityColList;
   }

   public List listPrincipalPartitionColumnGrants(String principalName, PrincipalType principalType, String dbName, String tableName, List partValues, String partitionName, String columnName) {
      List<MPartitionColumnPrivilege> mPartitionCols = this.listPrincipalMPartitionColumnGrants(principalName, principalType, dbName, tableName, partitionName, columnName);
      if (mPartitionCols.isEmpty()) {
         return Collections.emptyList();
      } else {
         List<HiveObjectPrivilege> result = new ArrayList();

         for(int i = 0; i < mPartitionCols.size(); ++i) {
            MPartitionColumnPrivilege sCol = (MPartitionColumnPrivilege)mPartitionCols.get(i);
            HiveObjectRef objectRef = new HiveObjectRef(HiveObjectType.COLUMN, dbName, tableName, partValues, sCol.getColumnName());
            HiveObjectPrivilege secObj = new HiveObjectPrivilege(objectRef, sCol.getPrincipalName(), principalType, new PrivilegeGrantInfo(sCol.getPrivilege(), sCol.getCreateTime(), sCol.getGrantor(), PrincipalType.valueOf(sCol.getGrantorType()), sCol.getGrantOption()));
            result.add(secObj);
         }

         return result;
      }
   }

   public List listPrincipalPartitionColumnGrantsAll(String principalName, PrincipalType principalType) {
      boolean success = false;
      Query query = null;

      List var7;
      try {
         this.openTransaction();
         LOG.debug("Executing listPrincipalPartitionColumnGrantsAll");
         List<MPartitionColumnPrivilege> mSecurityTabPartList;
         if (principalName != null && principalType != null) {
            query = this.pm.newQuery(MPartitionColumnPrivilege.class, "principalName == t1 && principalType == t2");
            query.declareParameters("java.lang.String t1, java.lang.String t2");
            mSecurityTabPartList = (List)query.executeWithArray(new Object[]{principalName, principalType.toString()});
         } else {
            query = this.pm.newQuery(MPartitionColumnPrivilege.class);
            mSecurityTabPartList = (List)query.execute();
         }

         LOG.debug("Done executing query for listPrincipalPartitionColumnGrantsAll");
         this.pm.retrieveAll(mSecurityTabPartList);
         List<HiveObjectPrivilege> result = this.convertPartCols(mSecurityTabPartList);
         success = this.commitTransaction();
         LOG.debug("Done retrieving all objects for listPrincipalPartitionColumnGrantsAll");
         var7 = result;
      } finally {
         this.rollbackAndCleanup(success, query);
      }

      return var7;
   }

   public List listPartitionColumnGrantsAll(String dbName, String tableName, String partitionName, String columnName) {
      boolean success = false;
      Query query = null;

      List var9;
      try {
         this.openTransaction();
         LOG.debug("Executing listPartitionColumnGrantsAll");
         query = this.pm.newQuery(MPartitionColumnPrivilege.class, "partition.table.tableName == t3 && partition.table.database.name == t4 && partition.partitionName == t5 && columnName == t6");
         query.declareParameters("java.lang.String t3, java.lang.String t4, java.lang.String t5, java.lang.String t6");
         List<MPartitionColumnPrivilege> mSecurityTabPartList = (List)query.executeWithArray(new Object[]{tableName, dbName, partitionName, columnName});
         LOG.debug("Done executing query for listPartitionColumnGrantsAll");
         this.pm.retrieveAll(mSecurityTabPartList);
         List<HiveObjectPrivilege> result = this.convertPartCols(mSecurityTabPartList);
         success = this.commitTransaction();
         LOG.debug("Done retrieving all objects for listPartitionColumnGrantsAll");
         var9 = result;
      } finally {
         this.rollbackAndCleanup(success, query);
      }

      return var9;
   }

   private List convertPartCols(List privs) {
      List<HiveObjectPrivilege> result = new ArrayList();

      for(MPartitionColumnPrivilege priv : privs) {
         String pname = priv.getPrincipalName();
         PrincipalType ptype = PrincipalType.valueOf(priv.getPrincipalType());
         MPartition mpartition = priv.getPartition();
         MTable mtable = mpartition.getTable();
         MDatabase mdatabase = mtable.getDatabase();
         HiveObjectRef objectRef = new HiveObjectRef(HiveObjectType.COLUMN, mdatabase.getName(), mtable.getTableName(), mpartition.getValues(), priv.getColumnName());
         PrivilegeGrantInfo grantor = new PrivilegeGrantInfo(priv.getPrivilege(), priv.getCreateTime(), priv.getGrantor(), PrincipalType.valueOf(priv.getGrantorType()), priv.getGrantOption());
         result.add(new HiveObjectPrivilege(objectRef, pname, ptype, grantor));
      }

      return result;
   }

   private List listPrincipalAllTableGrants(String principalName, PrincipalType principalType, QueryWrapper queryWrapper) {
      boolean success = false;
      List<MTablePrivilege> mSecurityTabPartList = null;

      try {
         LOG.debug("Executing listPrincipalAllTableGrants");
         this.openTransaction();
         Query query = queryWrapper.query = this.pm.newQuery(MTablePrivilege.class, "principalName == t1 && principalType == t2");
         query.declareParameters("java.lang.String t1, java.lang.String t2");
         mSecurityTabPartList = (List)query.execute(principalName, principalType.toString());
         this.pm.retrieveAll(mSecurityTabPartList);
         success = this.commitTransaction();
         LOG.debug("Done retrieving all objects for listPrincipalAllTableGrants");
      } finally {
         if (!success) {
            this.rollbackTransaction();
         }

      }

      return mSecurityTabPartList;
   }

   public List listPrincipalTableGrantsAll(String principalName, PrincipalType principalType) {
      boolean success = false;
      Query query = null;

      List var7;
      try {
         this.openTransaction();
         LOG.debug("Executing listPrincipalAllTableGrants");
         List<MTablePrivilege> mSecurityTabPartList;
         if (principalName != null && principalType != null) {
            query = this.pm.newQuery(MTablePrivilege.class, "principalName == t1 && principalType == t2");
            query.declareParameters("java.lang.String t1, java.lang.String t2");
            mSecurityTabPartList = (List)query.execute(principalName, principalType.toString());
         } else {
            query = this.pm.newQuery(MTablePrivilege.class);
            mSecurityTabPartList = (List)query.execute();
         }

         LOG.debug("Done executing query for listPrincipalAllTableGrants");
         this.pm.retrieveAll(mSecurityTabPartList);
         List<HiveObjectPrivilege> result = this.convertTable(mSecurityTabPartList);
         success = this.commitTransaction();
         LOG.debug("Done retrieving all objects for listPrincipalAllTableGrants");
         var7 = result;
      } finally {
         this.rollbackAndCleanup(success, query);
      }

      return var7;
   }

   public List listTableGrantsAll(String dbName, String tableName) {
      boolean success = false;
      Query query = null;

      List var7;
      try {
         this.openTransaction();
         LOG.debug("Executing listTableGrantsAll");
         query = this.pm.newQuery(MTablePrivilege.class, "table.tableName == t1 && table.database.name == t2");
         query.declareParameters("java.lang.String t1, java.lang.String t2");
         List<MTablePrivilege> mSecurityTabPartList = (List)query.executeWithArray(new Object[]{tableName, dbName});
         LOG.debug("Done executing query for listTableGrantsAll");
         this.pm.retrieveAll(mSecurityTabPartList);
         List<HiveObjectPrivilege> result = this.convertTable(mSecurityTabPartList);
         success = this.commitTransaction();
         LOG.debug("Done retrieving all objects for listPrincipalAllTableGrants");
         var7 = result;
      } finally {
         this.rollbackAndCleanup(success, query);
      }

      return var7;
   }

   private List convertTable(List privs) {
      List<HiveObjectPrivilege> result = new ArrayList();

      for(MTablePrivilege priv : privs) {
         String pname = priv.getPrincipalName();
         PrincipalType ptype = PrincipalType.valueOf(priv.getPrincipalType());
         String table = priv.getTable().getTableName();
         String database = priv.getTable().getDatabase().getName();
         HiveObjectRef objectRef = new HiveObjectRef(HiveObjectType.TABLE, database, table, (List)null, (String)null);
         PrivilegeGrantInfo grantor = new PrivilegeGrantInfo(priv.getPrivilege(), priv.getCreateTime(), priv.getGrantor(), PrincipalType.valueOf(priv.getGrantorType()), priv.getGrantOption());
         result.add(new HiveObjectPrivilege(objectRef, pname, ptype, grantor));
      }

      return result;
   }

   private List listPrincipalAllPartitionGrants(String principalName, PrincipalType principalType, QueryWrapper queryWrapper) {
      boolean success = false;
      List<MPartitionPrivilege> mSecurityTabPartList = null;

      try {
         this.openTransaction();
         LOG.debug("Executing listPrincipalAllPartitionGrants");
         Query query = queryWrapper.query = this.pm.newQuery(MPartitionPrivilege.class, "principalName == t1 && principalType == t2");
         query.declareParameters("java.lang.String t1, java.lang.String t2");
         mSecurityTabPartList = (List)query.execute(principalName, principalType.toString());
         this.pm.retrieveAll(mSecurityTabPartList);
         success = this.commitTransaction();
         LOG.debug("Done retrieving all objects for listPrincipalAllPartitionGrants");
      } finally {
         if (!success) {
            this.rollbackTransaction();
         }

      }

      return mSecurityTabPartList;
   }

   public List listPrincipalPartitionGrantsAll(String principalName, PrincipalType principalType) {
      boolean success = false;
      Query query = null;

      List var7;
      try {
         this.openTransaction();
         LOG.debug("Executing listPrincipalPartitionGrantsAll");
         List<MPartitionPrivilege> mSecurityTabPartList;
         if (principalName != null && principalType != null) {
            query = this.pm.newQuery(MPartitionPrivilege.class, "principalName == t1 && principalType == t2");
            query.declareParameters("java.lang.String t1, java.lang.String t2");
            mSecurityTabPartList = (List)query.execute(principalName, principalType.toString());
         } else {
            query = this.pm.newQuery(MPartitionPrivilege.class);
            mSecurityTabPartList = (List)query.execute();
         }

         LOG.debug("Done executing query for listPrincipalPartitionGrantsAll");
         this.pm.retrieveAll(mSecurityTabPartList);
         List<HiveObjectPrivilege> result = this.convertPartition(mSecurityTabPartList);
         success = this.commitTransaction();
         LOG.debug("Done retrieving all objects for listPrincipalPartitionGrantsAll");
         var7 = result;
      } finally {
         this.rollbackAndCleanup(success, query);
      }

      return var7;
   }

   public List listPartitionGrantsAll(String dbName, String tableName, String partitionName) {
      boolean success = false;
      Query query = null;

      List var8;
      try {
         this.openTransaction();
         LOG.debug("Executing listPrincipalPartitionGrantsAll");
         query = this.pm.newQuery(MPartitionPrivilege.class, "partition.table.tableName == t3 && partition.table.database.name == t4 && partition.partitionName == t5");
         query.declareParameters("java.lang.String t3, java.lang.String t4, java.lang.String t5");
         List<MPartitionPrivilege> mSecurityTabPartList = (List)query.executeWithArray(new Object[]{tableName, dbName, partitionName});
         LOG.debug("Done executing query for listPrincipalPartitionGrantsAll");
         this.pm.retrieveAll(mSecurityTabPartList);
         List<HiveObjectPrivilege> result = this.convertPartition(mSecurityTabPartList);
         success = this.commitTransaction();
         LOG.debug("Done retrieving all objects for listPrincipalPartitionGrantsAll");
         var8 = result;
      } finally {
         this.rollbackAndCleanup(success, query);
      }

      return var8;
   }

   private List convertPartition(List privs) {
      List<HiveObjectPrivilege> result = new ArrayList();

      for(MPartitionPrivilege priv : privs) {
         String pname = priv.getPrincipalName();
         PrincipalType ptype = PrincipalType.valueOf(priv.getPrincipalType());
         MPartition mpartition = priv.getPartition();
         MTable mtable = mpartition.getTable();
         MDatabase mdatabase = mtable.getDatabase();
         HiveObjectRef objectRef = new HiveObjectRef(HiveObjectType.PARTITION, mdatabase.getName(), mtable.getTableName(), mpartition.getValues(), (String)null);
         PrivilegeGrantInfo grantor = new PrivilegeGrantInfo(priv.getPrivilege(), priv.getCreateTime(), priv.getGrantor(), PrincipalType.valueOf(priv.getGrantorType()), priv.getGrantOption());
         result.add(new HiveObjectPrivilege(objectRef, pname, ptype, grantor));
      }

      return result;
   }

   private List listPrincipalAllTableColumnGrants(String principalName, PrincipalType principalType, QueryWrapper queryWrapper) {
      boolean success = false;
      List<MTableColumnPrivilege> mSecurityColumnList = null;

      try {
         LOG.debug("Executing listPrincipalAllTableColumnGrants");
         this.openTransaction();
         Query query = queryWrapper.query = this.pm.newQuery(MTableColumnPrivilege.class, "principalName == t1 && principalType == t2");
         query.declareParameters("java.lang.String t1, java.lang.String t2");
         mSecurityColumnList = (List)query.execute(principalName, principalType.toString());
         this.pm.retrieveAll(mSecurityColumnList);
         success = this.commitTransaction();
         LOG.debug("Done retrieving all objects for listPrincipalAllTableColumnGrants");
      } finally {
         if (!success) {
            this.rollbackTransaction();
         }

      }

      return mSecurityColumnList;
   }

   public List listPrincipalTableColumnGrantsAll(String principalName, PrincipalType principalType) {
      boolean success = false;
      Query query = null;

      List var7;
      try {
         this.openTransaction();
         LOG.debug("Executing listPrincipalTableColumnGrantsAll");
         List<MTableColumnPrivilege> mSecurityTabPartList;
         if (principalName != null && principalType != null) {
            query = this.pm.newQuery(MTableColumnPrivilege.class, "principalName == t1 && principalType == t2");
            query.declareParameters("java.lang.String t1, java.lang.String t2");
            mSecurityTabPartList = (List)query.execute(principalName, principalType.toString());
         } else {
            query = this.pm.newQuery(MTableColumnPrivilege.class);
            mSecurityTabPartList = (List)query.execute();
         }

         LOG.debug("Done executing query for listPrincipalTableColumnGrantsAll");
         this.pm.retrieveAll(mSecurityTabPartList);
         List<HiveObjectPrivilege> result = this.convertTableCols(mSecurityTabPartList);
         success = this.commitTransaction();
         LOG.debug("Done retrieving all objects for listPrincipalTableColumnGrantsAll");
         var7 = result;
      } finally {
         this.rollbackAndCleanup(success, query);
      }

      return var7;
   }

   public List listTableColumnGrantsAll(String dbName, String tableName, String columnName) {
      boolean success = false;
      Query query = null;

      List var8;
      try {
         this.openTransaction();
         LOG.debug("Executing listPrincipalTableColumnGrantsAll");
         query = this.pm.newQuery(MTableColumnPrivilege.class, "table.tableName == t3 && table.database.name == t4 &&  columnName == t5");
         query.declareParameters("java.lang.String t3, java.lang.String t4, java.lang.String t5");
         List<MTableColumnPrivilege> mSecurityTabPartList = (List)query.executeWithArray(new Object[]{tableName, dbName, columnName});
         LOG.debug("Done executing query for listPrincipalTableColumnGrantsAll");
         this.pm.retrieveAll(mSecurityTabPartList);
         List<HiveObjectPrivilege> result = this.convertTableCols(mSecurityTabPartList);
         success = this.commitTransaction();
         LOG.debug("Done retrieving all objects for listPrincipalTableColumnGrantsAll");
         var8 = result;
      } finally {
         this.rollbackAndCleanup(success, query);
      }

      return var8;
   }

   private List convertTableCols(List privs) {
      List<HiveObjectPrivilege> result = new ArrayList();

      for(MTableColumnPrivilege priv : privs) {
         String pname = priv.getPrincipalName();
         PrincipalType ptype = PrincipalType.valueOf(priv.getPrincipalType());
         MTable mtable = priv.getTable();
         MDatabase mdatabase = mtable.getDatabase();
         HiveObjectRef objectRef = new HiveObjectRef(HiveObjectType.COLUMN, mdatabase.getName(), mtable.getTableName(), (List)null, priv.getColumnName());
         PrivilegeGrantInfo grantor = new PrivilegeGrantInfo(priv.getPrivilege(), priv.getCreateTime(), priv.getGrantor(), PrincipalType.valueOf(priv.getGrantorType()), priv.getGrantOption());
         result.add(new HiveObjectPrivilege(objectRef, pname, ptype, grantor));
      }

      return result;
   }

   private List listPrincipalAllPartitionColumnGrants(String principalName, PrincipalType principalType, QueryWrapper queryWrapper) {
      boolean success = false;
      List<MPartitionColumnPrivilege> mSecurityColumnList = null;

      try {
         LOG.debug("Executing listPrincipalAllTableColumnGrants");
         this.openTransaction();
         Query query = queryWrapper.query = this.pm.newQuery(MPartitionColumnPrivilege.class, "principalName == t1 && principalType == t2");
         query.declareParameters("java.lang.String t1, java.lang.String t2");
         mSecurityColumnList = (List)query.execute(principalName, principalType.toString());
         this.pm.retrieveAll(mSecurityColumnList);
         success = this.commitTransaction();
         LOG.debug("Done retrieving all objects for listPrincipalAllTableColumnGrants");
      } finally {
         if (!success) {
            this.rollbackTransaction();
         }

      }

      return mSecurityColumnList;
   }

   public boolean isPartitionMarkedForEvent(String dbName, String tblName, Map partName, PartitionEventType evtType) throws UnknownTableException, MetaException, InvalidPartitionException, UnknownPartitionException {
      boolean success = false;
      Query query = null;

      boolean var9;
      try {
         LOG.debug("Begin Executing isPartitionMarkedForEvent");
         this.openTransaction();
         query = this.pm.newQuery(MPartitionEvent.class, "dbName == t1 && tblName == t2 && partName == t3 && eventType == t4");
         query.declareParameters("java.lang.String t1, java.lang.String t2, java.lang.String t3, int t4");
         Table tbl = this.getTable(dbName, tblName);
         if (null == tbl) {
            throw new UnknownTableException("Table: " + tblName + " is not found.");
         }

         Collection<MPartitionEvent> partEvents = (Collection)query.executeWithArray(new Object[]{dbName, tblName, this.getPartitionStr(tbl, partName), evtType.getValue()});
         this.pm.retrieveAll(partEvents);
         success = this.commitTransaction();
         LOG.debug("Done executing isPartitionMarkedForEvent");
         var9 = partEvents != null && !partEvents.isEmpty();
      } finally {
         this.rollbackAndCleanup(success, query);
      }

      return var9;
   }

   public Table markPartitionForEvent(String dbName, String tblName, Map partName, PartitionEventType evtType) throws MetaException, UnknownTableException, InvalidPartitionException, UnknownPartitionException {
      LOG.debug("Begin executing markPartitionForEvent");
      boolean success = false;
      Table tbl = null;

      try {
         this.openTransaction();
         tbl = this.getTable(dbName, tblName);
         if (null == tbl) {
            throw new UnknownTableException("Table: " + tblName + " is not found.");
         }

         this.pm.makePersistent(new MPartitionEvent(dbName, tblName, this.getPartitionStr(tbl, partName), evtType.getValue()));
         success = this.commitTransaction();
         LOG.debug("Done executing markPartitionForEvent");
      } finally {
         if (!success) {
            this.rollbackTransaction();
         }

      }

      return tbl;
   }

   private String getPartitionStr(Table tbl, Map partName) throws InvalidPartitionException {
      if (tbl.getPartitionKeysSize() != partName.size()) {
         throw new InvalidPartitionException("Number of partition columns in table: " + tbl.getPartitionKeysSize() + " doesn't match with number of supplied partition values: " + partName.size());
      } else {
         List<String> storedVals = new ArrayList(tbl.getPartitionKeysSize());

         for(FieldSchema partKey : tbl.getPartitionKeys()) {
            String partVal = (String)partName.get(partKey.getName());
            if (null == partVal) {
               throw new InvalidPartitionException("No value found for partition column: " + partKey.getName());
            }

            storedVals.add(partVal);
         }

         return StringUtils.join(storedVals, ',');
      }
   }

   public Collection executeJDOQLSelect(String queryStr, QueryWrapper queryWrapper) {
      boolean committed = false;
      Collection<?> result = null;

      Collection var6;
      try {
         this.openTransaction();
         Query query = queryWrapper.query = this.pm.newQuery(queryStr);
         result = (Collection)query.execute();
         committed = this.commitTransaction();
         if (!committed) {
            var6 = null;
            return var6;
         }

         var6 = result;
      } finally {
         if (!committed) {
            this.rollbackTransaction();
         }

      }

      return var6;
   }

   public long executeJDOQLUpdate(String queryStr) {
      boolean committed = false;
      long numUpdated = 0L;
      Query query = null;

      long var6;
      try {
         this.openTransaction();
         query = this.pm.newQuery(queryStr);
         numUpdated = (Long)query.execute();
         committed = this.commitTransaction();
         if (!committed) {
            var6 = -1L;
            return var6;
         }

         var6 = numUpdated;
      } finally {
         this.rollbackAndCleanup(committed, query);
      }

      return var6;
   }

   public Set listFSRoots() {
      boolean committed = false;
      Query query = null;
      Set<String> fsRoots = new HashSet();

      Object var10;
      try {
         this.openTransaction();
         query = this.pm.newQuery(MDatabase.class);
         List<MDatabase> mDBs = (List)query.execute();
         this.pm.retrieveAll(mDBs);

         for(MDatabase mDB : mDBs) {
            fsRoots.add(mDB.getLocationUri());
         }

         committed = this.commitTransaction();
         if (!committed) {
            var10 = null;
            return (Set)var10;
         }

         var10 = fsRoots;
      } finally {
         this.rollbackAndCleanup(committed, query);
      }

      return (Set)var10;
   }

   private boolean shouldUpdateURI(URI onDiskUri, URI inputUri) {
      String onDiskHost = onDiskUri.getHost();
      String inputHost = inputUri.getHost();
      int onDiskPort = onDiskUri.getPort();
      int inputPort = inputUri.getPort();
      String onDiskScheme = onDiskUri.getScheme();
      String inputScheme = inputUri.getScheme();
      if (inputPort != -1 && inputPort != onDiskPort) {
         return false;
      } else {
         if (inputScheme != null) {
            if (onDiskScheme == null) {
               return false;
            }

            if (!inputScheme.equalsIgnoreCase(onDiskScheme)) {
               return false;
            }
         }

         if (onDiskHost != null) {
            return inputHost.equalsIgnoreCase(onDiskHost);
         } else {
            return false;
         }
      }
   }

   public UpdateMDatabaseURIRetVal updateMDatabaseURI(URI oldLoc, URI newLoc, boolean dryRun) {
      boolean committed = false;
      Query query = null;
      Map<String, String> updateLocations = new HashMap();
      List<String> badRecords = new ArrayList();
      UpdateMDatabaseURIRetVal retVal = null;

      UpdateMDatabaseURIRetVal var20;
      try {
         this.openTransaction();
         query = this.pm.newQuery(MDatabase.class);
         List<MDatabase> mDBs = (List)query.execute();
         this.pm.retrieveAll(mDBs);

         for(MDatabase mDB : mDBs) {
            URI locationURI = null;
            String location = mDB.getLocationUri();

            try {
               locationURI = (new Path(location)).toUri();
            } catch (IllegalArgumentException var18) {
               badRecords.add(location);
            }

            if (locationURI == null) {
               badRecords.add(location);
            } else if (this.shouldUpdateURI(locationURI, oldLoc)) {
               String dbLoc = mDB.getLocationUri().replaceAll(oldLoc.toString(), newLoc.toString());
               updateLocations.put(locationURI.toString(), dbLoc);
               if (!dryRun) {
                  mDB.setLocationUri(dbLoc);
               }
            }
         }

         committed = this.commitTransaction();
         if (committed) {
            retVal = new UpdateMDatabaseURIRetVal(badRecords, updateLocations);
         }

         var20 = retVal;
      } finally {
         this.rollbackAndCleanup(committed, query);
      }

      return var20;
   }

   private void updatePropURIHelper(URI oldLoc, URI newLoc, String tblPropKey, boolean isDryRun, List badRecords, Map updateLocations, Map parameters) {
      URI tablePropLocationURI = null;
      if (parameters.containsKey(tblPropKey)) {
         String tablePropLocation = (String)parameters.get(tblPropKey);

         try {
            tablePropLocationURI = (new Path(tablePropLocation)).toUri();
         } catch (IllegalArgumentException var11) {
            badRecords.add(tablePropLocation);
         }

         if (tablePropLocationURI == null) {
            badRecords.add(tablePropLocation);
         } else if (this.shouldUpdateURI(tablePropLocationURI, oldLoc)) {
            String tblPropLoc = ((String)parameters.get(tblPropKey)).replaceAll(oldLoc.toString(), newLoc.toString());
            updateLocations.put(tablePropLocationURI.toString(), tblPropLoc);
            if (!isDryRun) {
               parameters.put(tblPropKey, tblPropLoc);
            }
         }
      }

   }

   public UpdatePropURIRetVal updateTblPropURI(URI oldLoc, URI newLoc, String tblPropKey, boolean isDryRun) {
      boolean committed = false;
      Query query = null;
      Map<String, String> updateLocations = new HashMap();
      List<String> badRecords = new ArrayList();
      UpdatePropURIRetVal retVal = null;

      UpdatePropURIRetVal var16;
      try {
         this.openTransaction();
         query = this.pm.newQuery(MTable.class);
         List<MTable> mTbls = (List)query.execute();
         this.pm.retrieveAll(mTbls);

         for(MTable mTbl : mTbls) {
            this.updatePropURIHelper(oldLoc, newLoc, tblPropKey, isDryRun, badRecords, updateLocations, mTbl.getParameters());
         }

         committed = this.commitTransaction();
         if (committed) {
            retVal = new UpdatePropURIRetVal(badRecords, updateLocations);
         }

         var16 = retVal;
      } finally {
         this.rollbackAndCleanup(committed, query);
      }

      return var16;
   }

   /** @deprecated */
   @Deprecated
   public UpdatePropURIRetVal updateMStorageDescriptorTblPropURI(URI oldLoc, URI newLoc, String tblPropKey, boolean isDryRun) {
      boolean committed = false;
      Query query = null;
      Map<String, String> updateLocations = new HashMap();
      List<String> badRecords = new ArrayList();
      UpdatePropURIRetVal retVal = null;

      UpdatePropURIRetVal var16;
      try {
         this.openTransaction();
         query = this.pm.newQuery(MStorageDescriptor.class);
         List<MStorageDescriptor> mSDSs = (List)query.execute();
         this.pm.retrieveAll(mSDSs);

         for(MStorageDescriptor mSDS : mSDSs) {
            this.updatePropURIHelper(oldLoc, newLoc, tblPropKey, isDryRun, badRecords, updateLocations, mSDS.getParameters());
         }

         committed = this.commitTransaction();
         if (committed) {
            retVal = new UpdatePropURIRetVal(badRecords, updateLocations);
         }

         var16 = retVal;
      } finally {
         this.rollbackAndCleanup(committed, query);
      }

      return var16;
   }

   public UpdateMStorageDescriptorTblURIRetVal updateMStorageDescriptorTblURI(URI oldLoc, URI newLoc, boolean isDryRun) {
      boolean committed = false;
      Query query = null;
      Map<String, String> updateLocations = new HashMap();
      List<String> badRecords = new ArrayList();
      int numNullRecords = 0;
      UpdateMStorageDescriptorTblURIRetVal retVal = null;

      UpdateMStorageDescriptorTblURIRetVal var21;
      try {
         this.openTransaction();
         query = this.pm.newQuery(MStorageDescriptor.class);
         List<MStorageDescriptor> mSDSs = (List)query.execute();
         this.pm.retrieveAll(mSDSs);

         for(MStorageDescriptor mSDS : mSDSs) {
            URI locationURI = null;
            String location = mSDS.getLocation();
            if (location == null) {
               ++numNullRecords;
            } else {
               try {
                  locationURI = (new Path(location)).toUri();
               } catch (IllegalArgumentException var19) {
                  badRecords.add(location);
               }

               if (locationURI == null) {
                  badRecords.add(location);
               } else if (this.shouldUpdateURI(locationURI, oldLoc)) {
                  String tblLoc = mSDS.getLocation().replaceAll(oldLoc.toString(), newLoc.toString());
                  updateLocations.put(locationURI.toString(), tblLoc);
                  if (!isDryRun) {
                     mSDS.setLocation(tblLoc);
                  }
               }
            }
         }

         committed = this.commitTransaction();
         if (committed) {
            retVal = new UpdateMStorageDescriptorTblURIRetVal(badRecords, updateLocations, numNullRecords);
         }

         var21 = retVal;
      } finally {
         this.rollbackAndCleanup(committed, query);
      }

      return var21;
   }

   public UpdateSerdeURIRetVal updateSerdeURI(URI oldLoc, URI newLoc, String serdeProp, boolean isDryRun) {
      boolean committed = false;
      Query query = null;
      Map<String, String> updateLocations = new HashMap();
      List<String> badRecords = new ArrayList();
      UpdateSerdeURIRetVal retVal = null;

      UpdateSerdeURIRetVal var21;
      try {
         this.openTransaction();
         query = this.pm.newQuery(MSerDeInfo.class);
         List<MSerDeInfo> mSerdes = (List)query.execute();
         this.pm.retrieveAll(mSerdes);

         for(MSerDeInfo mSerde : mSerdes) {
            if (mSerde.getParameters().containsKey(serdeProp)) {
               String schemaLoc = (String)mSerde.getParameters().get(serdeProp);
               URI schemaLocURI = null;

               try {
                  schemaLocURI = (new Path(schemaLoc)).toUri();
               } catch (IllegalArgumentException var19) {
                  badRecords.add(schemaLoc);
               }

               if (schemaLocURI == null) {
                  badRecords.add(schemaLoc);
               } else if (this.shouldUpdateURI(schemaLocURI, oldLoc)) {
                  String newSchemaLoc = schemaLoc.replaceAll(oldLoc.toString(), newLoc.toString());
                  updateLocations.put(schemaLocURI.toString(), newSchemaLoc);
                  if (!isDryRun) {
                     mSerde.getParameters().put(serdeProp, newSchemaLoc);
                  }
               }
            }
         }

         committed = this.commitTransaction();
         if (committed) {
            retVal = new UpdateSerdeURIRetVal(badRecords, updateLocations);
         }

         var21 = retVal;
      } finally {
         this.rollbackAndCleanup(committed, query);
      }

      return var21;
   }

   private void writeMTableColumnStatistics(Table table, MTableColumnStatistics mStatsObj, MTableColumnStatistics oldStats) throws NoSuchObjectException, MetaException, InvalidObjectException, InvalidInputException {
      String dbName = mStatsObj.getDbName();
      String tableName = mStatsObj.getTableName();
      String colName = mStatsObj.getColName();
      QueryWrapper queryWrapper = new QueryWrapper();

      try {
         LOG.info("Updating table level column statistics for db=" + dbName + " tableName=" + tableName + " colName=" + colName);
         this.validateTableCols(table, Lists.newArrayList(new String[]{colName}));
         if (oldStats != null) {
            StatObjectConverter.setFieldsIntoOldStats(mStatsObj, oldStats);
         } else {
            this.pm.makePersistent(mStatsObj);
         }
      } finally {
         queryWrapper.close();
      }

   }

   private void writeMPartitionColumnStatistics(Table table, Partition partition, MPartitionColumnStatistics mStatsObj, MPartitionColumnStatistics oldStats) throws NoSuchObjectException, MetaException, InvalidObjectException, InvalidInputException {
      String dbName = mStatsObj.getDbName();
      String tableName = mStatsObj.getTableName();
      String partName = mStatsObj.getPartitionName();
      String colName = mStatsObj.getColName();
      LOG.info("Updating partition level column statistics for db=" + dbName + " tableName=" + tableName + " partName=" + partName + " colName=" + colName);
      boolean foundCol = false;

      for(FieldSchema col : partition.getSd().getCols()) {
         if (col.getName().equals(mStatsObj.getColName())) {
            foundCol = true;
            break;
         }
      }

      if (!foundCol) {
         LOG.warn("Column " + colName + " for which stats gathering is requested doesn't exist.");
      }

      QueryWrapper queryWrapper = new QueryWrapper();

      try {
         if (oldStats != null) {
            StatObjectConverter.setFieldsIntoOldStats(mStatsObj, oldStats);
         } else {
            this.pm.makePersistent(mStatsObj);
         }
      } finally {
         queryWrapper.close();
      }

   }

   private Map getPartitionColStats(Table table, List colNames) throws NoSuchObjectException, MetaException {
      Map<String, MTableColumnStatistics> statsMap = Maps.newHashMap();
      QueryWrapper queryWrapper = new QueryWrapper();

      try {
         for(MTableColumnStatistics cStat : this.getMTableColumnStatistics(table, colNames, queryWrapper)) {
            statsMap.put(cStat.getColName(), cStat);
         }
      } finally {
         queryWrapper.close();
      }

      return statsMap;
   }

   public boolean updateTableColumnStatistics(ColumnStatistics colStats) throws NoSuchObjectException, MetaException, InvalidObjectException, InvalidInputException {
      boolean committed = false;
      this.openTransaction();

      boolean var12;
      try {
         List<ColumnStatisticsObj> statsObjs = colStats.getStatsObj();
         ColumnStatisticsDesc statsDesc = colStats.getStatsDesc();
         Table table = this.ensureGetTable(statsDesc.getDbName(), statsDesc.getTableName());
         List<String> colNames = new ArrayList();

         for(ColumnStatisticsObj statsObj : statsObjs) {
            colNames.add(statsObj.getColName());
         }

         Map<String, MTableColumnStatistics> oldStats = this.getPartitionColStats(table, colNames);

         for(ColumnStatisticsObj statsObj : statsObjs) {
            MTableColumnStatistics mStatsObj = StatObjectConverter.convertToMTableColumnStatistics(this.ensureGetMTable(statsDesc.getDbName(), statsDesc.getTableName()), statsDesc, statsObj);
            this.writeMTableColumnStatistics(table, mStatsObj, (MTableColumnStatistics)oldStats.get(statsObj.getColName()));
            colNames.add(statsObj.getColName());
         }

         String dbname = table.getDbName();
         String name = table.getTableName();
         MTable oldt = this.getMTable(dbname, name);
         Map<String, String> parameters = table.getParameters();
         StatsSetupConst.setColumnStatsState(parameters, colNames);
         oldt.setParameters(parameters);
         committed = this.commitTransaction();
         var12 = committed;
      } finally {
         if (!committed) {
            this.rollbackTransaction();
         }

      }

      return var12;
   }

   private Map getPartitionColStats(Table table, String partitionName, List colNames) throws NoSuchObjectException, MetaException {
      Map<String, MPartitionColumnStatistics> statsMap = Maps.newHashMap();
      QueryWrapper queryWrapper = new QueryWrapper();

      try {
         for(MPartitionColumnStatistics cStat : this.getMPartitionColumnStatistics(table, Lists.newArrayList(new String[]{partitionName}), colNames, queryWrapper)) {
            statsMap.put(cStat.getColName(), cStat);
         }
      } finally {
         queryWrapper.close();
      }

      return statsMap;
   }

   public boolean updatePartitionColumnStatistics(ColumnStatistics colStats, List partVals) throws NoSuchObjectException, MetaException, InvalidObjectException, InvalidInputException {
      boolean committed = false;

      boolean var20;
      try {
         this.openTransaction();
         List<ColumnStatisticsObj> statsObjs = colStats.getStatsObj();
         ColumnStatisticsDesc statsDesc = colStats.getStatsDesc();
         Table table = this.ensureGetTable(statsDesc.getDbName(), statsDesc.getTableName());
         Partition partition = this.convertToPart(this.getMPartition(statsDesc.getDbName(), statsDesc.getTableName(), partVals));
         List<String> colNames = new ArrayList();

         for(ColumnStatisticsObj statsObj : statsObjs) {
            colNames.add(statsObj.getColName());
         }

         Map<String, MPartitionColumnStatistics> oldStats = this.getPartitionColStats(table, statsDesc.getPartName(), colNames);
         MPartition mPartition = this.getMPartition(statsDesc.getDbName(), statsDesc.getTableName(), partVals);
         if (partition == null) {
            throw new NoSuchObjectException("Partition for which stats is gathered doesn't exist.");
         }

         for(ColumnStatisticsObj statsObj : statsObjs) {
            MPartitionColumnStatistics mStatsObj = StatObjectConverter.convertToMPartitionColumnStatistics(mPartition, statsDesc, statsObj);
            this.writeMPartitionColumnStatistics(table, partition, mStatsObj, (MPartitionColumnStatistics)oldStats.get(statsObj.getColName()));
         }

         Map<String, String> parameters = mPartition.getParameters();
         StatsSetupConst.setColumnStatsState(parameters, colNames);
         mPartition.setParameters(parameters);
         committed = this.commitTransaction();
         var20 = committed;
      } finally {
         if (!committed) {
            this.rollbackTransaction();
         }

      }

      return var20;
   }

   private List getMTableColumnStatistics(Table table, List colNames, QueryWrapper queryWrapper) throws MetaException {
      if (colNames != null && !colNames.isEmpty()) {
         boolean committed = false;

         List var18;
         try {
            this.openTransaction();
            List<MTableColumnStatistics> result = null;
            this.validateTableCols(table, colNames);
            Query query = queryWrapper.query = this.pm.newQuery(MTableColumnStatistics.class);
            String filter = "tableName == t1 && dbName == t2 && (";
            String paramStr = "java.lang.String t1, java.lang.String t2";
            Object[] params = new Object[colNames.size() + 2];
            params[0] = table.getTableName();
            params[1] = table.getDbName();

            for(int i = 0; i < colNames.size(); ++i) {
               filter = filter + (i == 0 ? "" : " || ") + "colName == c" + i;
               paramStr = paramStr + ", java.lang.String c" + i;
               params[i + 2] = colNames.get(i);
            }

            filter = filter + ")";
            query.setFilter(filter);
            query.declareParameters(paramStr);
            result = (List)query.executeWithArray(params);
            this.pm.retrieveAll(result);
            if (result.size() > colNames.size()) {
               throw new MetaException("Unexpected " + result.size() + " statistics for " + colNames.size() + " columns");
            }

            committed = this.commitTransaction();
            var18 = result;
         } catch (Exception ex) {
            LOG.error("Error retrieving statistics via jdo", ex);
            if (ex instanceof MetaException) {
               throw (MetaException)ex;
            }

            throw new MetaException(ex.getMessage());
         } finally {
            if (!committed) {
               this.rollbackTransaction();
            }

         }

         return var18;
      } else {
         return null;
      }
   }

   @VisibleForTesting
   public void validateTableCols(Table table, List colNames) throws MetaException {
      List<FieldSchema> colList = table.getSd().getCols();

      for(String colName : colNames) {
         boolean foundCol = false;

         for(FieldSchema mCol : colList) {
            if (mCol.getName().equals(colName)) {
               foundCol = true;
               break;
            }
         }

         if (!foundCol) {
            throw new MetaException("Column " + colName + " doesn't exist in table " + table.getTableName() + " in database " + table.getDbName());
         }
      }

   }

   public ColumnStatistics getTableColumnStatistics(String dbName, String tableName, List colNames) throws MetaException, NoSuchObjectException {
      return this.getTableColumnStatisticsInternal(dbName, tableName, colNames, true, true);
   }

   protected ColumnStatistics getTableColumnStatisticsInternal(String dbName, String tableName, final List colNames, boolean allowSql, boolean allowJdo) throws MetaException, NoSuchObjectException {
      return (ColumnStatistics)(new GetStatHelper(HiveStringUtils.normalizeIdentifier(dbName), HiveStringUtils.normalizeIdentifier(tableName), allowSql, allowJdo) {
         protected ColumnStatistics getSqlResult(GetHelper ctx) throws MetaException {
            return ObjectStore.this.directSql.getTableStats(this.dbName, this.tblName, colNames);
         }

         protected ColumnStatistics getJdoResult(GetHelper ctx) throws MetaException {
            QueryWrapper queryWrapper = new QueryWrapper();

            Object var4;
            try {
               List<MTableColumnStatistics> mStats = ObjectStore.this.getMTableColumnStatistics(this.getTable(), colNames, queryWrapper);
               if (!mStats.isEmpty()) {
                  ColumnStatisticsDesc desc = StatObjectConverter.getTableColumnStatisticsDesc((MTableColumnStatistics)mStats.get(0));
                  List<ColumnStatisticsObj> statObjs = new ArrayList(mStats.size());

                  for(MTableColumnStatistics mStat : mStats) {
                     if (desc.getLastAnalyzed() > mStat.getLastAnalyzed()) {
                        desc.setLastAnalyzed(mStat.getLastAnalyzed());
                     }

                     statObjs.add(StatObjectConverter.getTableColumnStatisticsObj(mStat));
                     Deadline.checkTimeout();
                  }

                  ColumnStatistics var12 = new ColumnStatistics(desc, statObjs);
                  return var12;
               }

               var4 = null;
            } finally {
               queryWrapper.close();
            }

            return (ColumnStatistics)var4;
         }
      }).run(true);
   }

   public List getPartitionColumnStatistics(String dbName, String tableName, List partNames, List colNames) throws MetaException, NoSuchObjectException {
      return this.getPartitionColumnStatisticsInternal(dbName, tableName, partNames, colNames, true, true);
   }

   protected List getPartitionColumnStatisticsInternal(String dbName, String tableName, final List partNames, final List colNames, boolean allowSql, boolean allowJdo) throws MetaException, NoSuchObjectException {
      return (List)(new GetListHelper(dbName, tableName, allowSql, allowJdo) {
         protected List getSqlResult(GetHelper ctx) throws MetaException {
            return ObjectStore.this.directSql.getPartitionStats(this.dbName, this.tblName, partNames, colNames);
         }

         protected List getJdoResult(GetHelper ctx) throws MetaException, NoSuchObjectException {
            QueryWrapper queryWrapper = new QueryWrapper();

            Object var15;
            try {
               List<MPartitionColumnStatistics> mStats = ObjectStore.this.getMPartitionColumnStatistics(this.getTable(), partNames, colNames, queryWrapper);
               List<ColumnStatistics> result = new ArrayList(Math.min(mStats.size(), partNames.size()));
               String lastPartName = null;
               List<ColumnStatisticsObj> curList = null;
               ColumnStatisticsDesc csd = null;

               for(int i = 0; i <= mStats.size(); ++i) {
                  boolean isLast = i == mStats.size();
                  MPartitionColumnStatistics mStatsObj = isLast ? null : (MPartitionColumnStatistics)mStats.get(i);
                  String partName = isLast ? null : mStatsObj.getPartitionName();
                  if (isLast || !partName.equals(lastPartName)) {
                     if (i != 0) {
                        result.add(new ColumnStatistics(csd, curList));
                     }

                     if (isLast) {
                        continue;
                     }

                     csd = StatObjectConverter.getPartitionColumnStatisticsDesc(mStatsObj);
                     curList = new ArrayList(colNames.size());
                  }

                  curList.add(StatObjectConverter.getPartitionColumnStatisticsObj(mStatsObj));
                  lastPartName = partName;
                  Deadline.checkTimeout();
               }

               var15 = result;
            } finally {
               queryWrapper.close();
            }

            return (List)var15;
         }
      }).run(true);
   }

   public AggrStats get_aggr_stats_for(String dbName, String tblName, final List partNames, final List colNames) throws MetaException, NoSuchObjectException {
      final boolean useDensityFunctionForNDVEstimation = HiveConf.getBoolVar(this.getConf(), ConfVars.HIVE_METASTORE_STATS_NDV_DENSITY_FUNCTION);
      final double ndvTuner = (double)HiveConf.getFloatVar(this.getConf(), ConfVars.HIVE_METASTORE_STATS_NDV_TUNER);
      return (AggrStats)(new GetHelper(dbName, tblName, true, false) {
         protected AggrStats getSqlResult(GetHelper ctx) throws MetaException {
            return ObjectStore.this.directSql.aggrColStatsForPartitions(this.dbName, this.tblName, partNames, colNames, useDensityFunctionForNDVEstimation, ndvTuner);
         }

         protected AggrStats getJdoResult(GetHelper ctx) throws MetaException, NoSuchObjectException {
            throw new MetaException("Jdo path is not implemented for stats aggr.");
         }

         protected String describeResult() {
            return null;
         }
      }).run(true);
   }

   public void flushCache() {
   }

   private List getMPartitionColumnStatistics(Table table, List partNames, List colNames, QueryWrapper queryWrapper) throws NoSuchObjectException, MetaException {
      boolean committed = false;

      List var27;
      try {
         this.openTransaction();
         this.validateTableCols(table, colNames);
         Query query = queryWrapper.query = this.pm.newQuery(MPartitionColumnStatistics.class);
         String paramStr = "java.lang.String t1, java.lang.String t2";
         String filter = "tableName == t1 && dbName == t2 && (";
         Object[] params = new Object[colNames.size() + partNames.size() + 2];
         int i = 0;
         params[i++] = table.getTableName();
         params[i++] = table.getDbName();
         int firstI = i;

         for(String s : partNames) {
            filter = filter + (i == firstI ? "" : " || ") + "partitionName == p" + i;
            paramStr = paramStr + ", java.lang.String p" + i;
            params[i++] = s;
         }

         filter = filter + ") && (";
         firstI = i;

         for(String s : colNames) {
            filter = filter + (i == firstI ? "" : " || ") + "colName == c" + i;
            paramStr = paramStr + ", java.lang.String c" + i;
            params[i++] = s;
         }

         filter = filter + ")";
         query.setFilter(filter);
         query.declareParameters(paramStr);
         query.setOrdering("partitionName ascending");
         List<MPartitionColumnStatistics> result = (List)query.executeWithArray(params);
         this.pm.retrieveAll(result);
         committed = this.commitTransaction();
         var27 = result;
      } catch (Exception ex) {
         LOG.error("Error retrieving statistics via jdo", ex);
         if (ex instanceof MetaException) {
            throw (MetaException)ex;
         }

         throw new MetaException(ex.getMessage());
      } finally {
         if (!committed) {
            this.rollbackTransaction();
            return Lists.newArrayList();
         }

      }

      return var27;
   }

   private void dropPartitionColumnStatisticsNoTxn(String dbName, String tableName, List partNames) throws MetaException {
      ObjectPair<Query, Object[]> queryWithParams = this.makeQueryByPartitionNames(dbName, tableName, partNames, MPartitionColumnStatistics.class, "tableName", "dbName", "partition.partitionName");
      ((Query)queryWithParams.getFirst()).deletePersistentAll(queryWithParams.getSecond());
   }

   public boolean deletePartitionColumnStatistics(String dbName, String tableName, String partName, List partVals, String colName) throws NoSuchObjectException, MetaException, InvalidObjectException, InvalidInputException {
      boolean ret = false;
      Query query = null;
      if (dbName == null) {
         dbName = "default";
      }

      if (tableName == null) {
         throw new InvalidInputException("Table name is null.");
      } else {
         try {
            this.openTransaction();
            MTable mTable = this.getMTable(dbName, tableName);
            if (mTable == null) {
               throw new NoSuchObjectException("Table " + tableName + "  for which stats deletion is requested doesn't exist");
            }

            MPartition mPartition = this.getMPartition(dbName, tableName, partVals);
            if (mPartition == null) {
               throw new NoSuchObjectException("Partition " + partName + " for which stats deletion is requested doesn't exist");
            }

            query = this.pm.newQuery(MPartitionColumnStatistics.class);
            String filter;
            String parameters;
            if (colName != null) {
               filter = "partition.partitionName == t1 && dbName == t2 && tableName == t3 && colName == t4";
               parameters = "java.lang.String t1, java.lang.String t2, java.lang.String t3, java.lang.String t4";
            } else {
               filter = "partition.partitionName == t1 && dbName == t2 && tableName == t3";
               parameters = "java.lang.String t1, java.lang.String t2, java.lang.String t3";
            }

            query.setFilter(filter);
            query.declareParameters(parameters);
            if (colName != null) {
               query.setUnique(true);
               MPartitionColumnStatistics mStatsObj = (MPartitionColumnStatistics)query.executeWithArray(new Object[]{partName.trim(), HiveStringUtils.normalizeIdentifier(dbName), HiveStringUtils.normalizeIdentifier(tableName), HiveStringUtils.normalizeIdentifier(colName)});
               this.pm.retrieve(mStatsObj);
               if (mStatsObj == null) {
                  throw new NoSuchObjectException("Column stats doesn't exist for db=" + dbName + " table=" + tableName + " partition=" + partName + " col=" + colName);
               }

               this.pm.deletePersistent(mStatsObj);
            } else {
               List<MPartitionColumnStatistics> mStatsObjColl = (List)query.execute(partName.trim(), HiveStringUtils.normalizeIdentifier(dbName), HiveStringUtils.normalizeIdentifier(tableName));
               this.pm.retrieveAll(mStatsObjColl);
               if (mStatsObjColl == null) {
                  throw new NoSuchObjectException("Column stats doesn't exist for db=" + dbName + " table=" + tableName + " partition" + partName);
               }

               this.pm.deletePersistentAll(mStatsObjColl);
            }

            ret = this.commitTransaction();
         } catch (NoSuchObjectException e) {
            this.rollbackTransaction();
            throw e;
         } finally {
            this.rollbackAndCleanup(ret, query);
         }

         return ret;
      }
   }

   public boolean deleteTableColumnStatistics(String dbName, String tableName, String colName) throws NoSuchObjectException, MetaException, InvalidObjectException, InvalidInputException {
      boolean ret = false;
      Query query = null;
      if (dbName == null) {
         dbName = "default";
      }

      if (tableName == null) {
         throw new InvalidInputException("Table name is null.");
      } else {
         try {
            this.openTransaction();
            MTable mTable = this.getMTable(dbName, tableName);
            if (mTable == null) {
               throw new NoSuchObjectException("Table " + tableName + "  for which stats deletion is requested doesn't exist");
            }

            query = this.pm.newQuery(MTableColumnStatistics.class);
            String filter;
            String parameters;
            if (colName != null) {
               filter = "table.tableName == t1 && dbName == t2 && colName == t3";
               parameters = "java.lang.String t1, java.lang.String t2, java.lang.String t3";
            } else {
               filter = "table.tableName == t1 && dbName == t2";
               parameters = "java.lang.String t1, java.lang.String t2";
            }

            query.setFilter(filter);
            query.declareParameters(parameters);
            if (colName != null) {
               query.setUnique(true);
               MTableColumnStatistics mStatsObj = (MTableColumnStatistics)query.execute(HiveStringUtils.normalizeIdentifier(tableName), HiveStringUtils.normalizeIdentifier(dbName), HiveStringUtils.normalizeIdentifier(colName));
               this.pm.retrieve(mStatsObj);
               if (mStatsObj == null) {
                  throw new NoSuchObjectException("Column stats doesn't exist for db=" + dbName + " table=" + tableName + " col=" + colName);
               }

               this.pm.deletePersistent(mStatsObj);
            } else {
               List<MTableColumnStatistics> mStatsObjColl = (List)query.execute(HiveStringUtils.normalizeIdentifier(tableName), HiveStringUtils.normalizeIdentifier(dbName));
               this.pm.retrieveAll(mStatsObjColl);
               if (mStatsObjColl == null) {
                  throw new NoSuchObjectException("Column stats doesn't exist for db=" + dbName + " table=" + tableName);
               }

               this.pm.deletePersistentAll(mStatsObjColl);
            }

            ret = this.commitTransaction();
         } catch (NoSuchObjectException e) {
            this.rollbackTransaction();
            throw e;
         } finally {
            this.rollbackAndCleanup(ret, query);
         }

         return ret;
      }
   }

   public long cleanupEvents() {
      boolean commited = false;
      Query query = null;
      LOG.debug("Begin executing cleanupEvents");
      Long expiryTime = HiveConf.getTimeVar(this.getConf(), ConfVars.METASTORE_EVENT_EXPIRY_DURATION, TimeUnit.MILLISECONDS);
      Long curTime = System.currentTimeMillis();

      long delCnt;
      try {
         this.openTransaction();
         query = this.pm.newQuery(MPartitionEvent.class, "curTime - eventTime > expiryTime");
         query.declareParameters("java.lang.Long curTime, java.lang.Long expiryTime");
         delCnt = query.deletePersistentAll(new Object[]{curTime, expiryTime});
         commited = this.commitTransaction();
      } finally {
         this.rollbackAndCleanup(commited, query);
         LOG.debug("Done executing cleanupEvents");
      }

      return delCnt;
   }

   private MDelegationToken getTokenFrom(String tokenId) {
      Query query = this.pm.newQuery(MDelegationToken.class, "tokenIdentifier == tokenId");
      query.declareParameters("java.lang.String tokenId");
      query.setUnique(true);
      MDelegationToken delegationToken = (MDelegationToken)query.execute(tokenId);
      if (query != null) {
         query.closeAll();
      }

      return delegationToken;
   }

   public boolean addToken(String tokenId, String delegationToken) {
      LOG.debug("Begin executing addToken");
      boolean committed = false;

      MDelegationToken token;
      try {
         this.openTransaction();
         token = this.getTokenFrom(tokenId);
         if (token == null) {
            this.pm.makePersistent(new MDelegationToken(tokenId, delegationToken));
         }

         committed = this.commitTransaction();
      } finally {
         if (!committed) {
            this.rollbackTransaction();
         }

      }

      LOG.debug("Done executing addToken with status : " + committed);
      return committed && token == null;
   }

   public boolean removeToken(String tokenId) {
      LOG.debug("Begin executing removeToken");
      boolean committed = false;

      MDelegationToken token;
      try {
         this.openTransaction();
         token = this.getTokenFrom(tokenId);
         if (null != token) {
            this.pm.deletePersistent(token);
         }

         committed = this.commitTransaction();
      } finally {
         if (!committed) {
            this.rollbackTransaction();
         }

      }

      LOG.debug("Done executing removeToken with status : " + committed);
      return committed && token != null;
   }

   public String getToken(String tokenId) {
      LOG.debug("Begin executing getToken");
      boolean committed = false;

      MDelegationToken token;
      try {
         this.openTransaction();
         token = this.getTokenFrom(tokenId);
         if (null != token) {
            this.pm.retrieve(token);
         }

         committed = this.commitTransaction();
      } finally {
         if (!committed) {
            this.rollbackTransaction();
         }

      }

      LOG.debug("Done executing getToken with status : " + committed);
      return null == token ? null : token.getTokenStr();
   }

   public List getAllTokenIdentifiers() {
      LOG.debug("Begin executing getAllTokenIdentifiers");
      boolean committed = false;
      Query query = null;
      List<String> tokenIdents = new ArrayList();

      Object var10;
      try {
         this.openTransaction();
         query = this.pm.newQuery(MDelegationToken.class);
         List<MDelegationToken> tokens = (List)query.execute();
         this.pm.retrieveAll(tokens);
         committed = this.commitTransaction();

         for(MDelegationToken token : tokens) {
            tokenIdents.add(token.getTokenIdentifier());
         }

         var10 = tokenIdents;
      } finally {
         LOG.debug("Done executing getAllTokenIdentifers with status : " + committed);
         this.rollbackAndCleanup(committed, query);
      }

      return (List)var10;
   }

   public int addMasterKey(String key) throws MetaException {
      LOG.debug("Begin executing addMasterKey");
      boolean committed = false;
      MMasterKey masterKey = new MMasterKey(key);

      try {
         this.openTransaction();
         this.pm.makePersistent(masterKey);
         committed = this.commitTransaction();
      } finally {
         if (!committed) {
            this.rollbackTransaction();
         }

      }

      LOG.debug("Done executing addMasterKey with status : " + committed);
      if (committed) {
         return ((IntIdentity)this.pm.getObjectId(masterKey)).getKey();
      } else {
         throw new MetaException("Failed to add master key.");
      }
   }

   public void updateMasterKey(Integer id, String key) throws NoSuchObjectException, MetaException {
      LOG.debug("Begin executing updateMasterKey");
      boolean committed = false;
      Query query = null;

      MMasterKey masterKey;
      try {
         this.openTransaction();
         query = this.pm.newQuery(MMasterKey.class, "keyId == id");
         query.declareParameters("java.lang.Integer id");
         query.setUnique(true);
         masterKey = (MMasterKey)query.execute(id);
         if (null != masterKey) {
            masterKey.setMasterKey(key);
         }

         committed = this.commitTransaction();
      } finally {
         this.rollbackAndCleanup(committed, query);
      }

      LOG.debug("Done executing updateMasterKey with status : " + committed);
      if (null == masterKey) {
         throw new NoSuchObjectException("No key found with keyId: " + id);
      } else if (!committed) {
         throw new MetaException("Though key is found, failed to update it. " + id);
      }
   }

   public boolean removeMasterKey(Integer id) {
      LOG.debug("Begin executing removeMasterKey");
      boolean success = false;
      Query query = null;

      MMasterKey masterKey;
      try {
         this.openTransaction();
         query = this.pm.newQuery(MMasterKey.class, "keyId == id");
         query.declareParameters("java.lang.Integer id");
         query.setUnique(true);
         masterKey = (MMasterKey)query.execute(id);
         if (null != masterKey) {
            this.pm.deletePersistent(masterKey);
         }

         success = this.commitTransaction();
      } finally {
         this.rollbackAndCleanup(success, query);
      }

      LOG.debug("Done executing removeMasterKey with status : " + success);
      return null != masterKey && success;
   }

   public String[] getMasterKeys() {
      LOG.debug("Begin executing getMasterKeys");
      boolean committed = false;
      Query query = null;

      String[] var9;
      try {
         this.openTransaction();
         query = this.pm.newQuery(MMasterKey.class);
         List<MMasterKey> keys = (List)query.execute();
         this.pm.retrieveAll(keys);
         committed = this.commitTransaction();
         String[] masterKeys = new String[keys.size()];

         for(int i = 0; i < keys.size(); ++i) {
            masterKeys[i] = ((MMasterKey)keys.get(i)).getMasterKey();
         }

         var9 = masterKeys;
      } finally {
         LOG.debug("Done executing getMasterKeys with status : " + committed);
         this.rollbackAndCleanup(committed, query);
      }

      return var9;
   }

   public void verifySchema() throws MetaException {
      if (!isSchemaVerified.get()) {
         this.checkSchema();
      }
   }

   public static void setSchemaVerified(boolean val) {
      isSchemaVerified.set(val);
   }

   private synchronized void checkSchema() throws MetaException {
      if (!isSchemaVerified.get()) {
         boolean strictValidation = HiveConf.getBoolVar(this.getConf(), ConfVars.METASTORE_SCHEMA_VERIFICATION);
         String dbSchemaVer = this.getMetaStoreSchemaVersion();
         String hiveSchemaVer = MetaStoreSchemaInfo.getHiveSchemaVersion();
         if (dbSchemaVer == null) {
            if (strictValidation) {
               throw new MetaException("Version information not found in metastore. ");
            }

            LOG.warn("Version information not found in metastore. " + ConfVars.METASTORE_SCHEMA_VERIFICATION.toString() + " is not enabled so recording the schema version " + hiveSchemaVer);
            this.setMetaStoreSchemaVersion(hiveSchemaVer, "Set by MetaStore " + USER + "@" + HOSTNAME);
         } else if (MetaStoreSchemaInfo.isVersionCompatible(hiveSchemaVer, dbSchemaVer)) {
            LOG.debug("Found expected HMS version of " + dbSchemaVer);
         } else {
            if (strictValidation) {
               throw new MetaException("Hive Schema version " + hiveSchemaVer + " does not match metastore's schema version " + dbSchemaVer + " Metastore is not upgraded or corrupt");
            }

            LOG.error("Version information found in metastore differs " + dbSchemaVer + " from expected schema version " + hiveSchemaVer + ". Schema verififcation is disabled " + ConfVars.METASTORE_SCHEMA_VERIFICATION);
            this.setMetaStoreSchemaVersion(hiveSchemaVer, "Set by MetaStore " + USER + "@" + HOSTNAME);
         }

         isSchemaVerified.set(true);
      }
   }

   public String getMetaStoreSchemaVersion() throws MetaException {
      MVersionTable mSchemaVer;
      try {
         mSchemaVer = this.getMSchemaVersion();
      } catch (NoSuchObjectException var3) {
         return null;
      }

      return mSchemaVer.getSchemaVersion();
   }

   private MVersionTable getMSchemaVersion() throws NoSuchObjectException, MetaException {
      boolean committed = false;
      Query query = null;
      new ArrayList();

      MVersionTable e;
      try {
         this.openTransaction();
         query = this.pm.newQuery(MVersionTable.class);

         List mVerTables;
         try {
            mVerTables = (List)query.execute();
            this.pm.retrieveAll(mVerTables);
         } catch (JDODataStoreException e) {
            if (e.getCause() instanceof MissingTableException) {
               throw new MetaException("Version table not found. The metastore is not upgraded to " + MetaStoreSchemaInfo.getHiveSchemaVersion());
            }

            throw e;
         }

         committed = this.commitTransaction();
         if (mVerTables.isEmpty()) {
            throw new NoSuchObjectException("No matching version found");
         }

         if (mVerTables.size() > 1) {
            String msg = "Metastore contains multiple versions (" + mVerTables.size() + ") ";

            for(MVersionTable version : mVerTables) {
               msg = msg + "[ version = " + version.getSchemaVersion() + ", comment = " + version.getVersionComment() + " ] ";
            }

            throw new MetaException(msg.trim());
         }

         e = (MVersionTable)mVerTables.get(0);
      } finally {
         this.rollbackAndCleanup(committed, query);
      }

      return e;
   }

   public void setMetaStoreSchemaVersion(String schemaVersion, String comment) throws MetaException {
      boolean commited = false;
      boolean recordVersion = HiveConf.getBoolVar(this.getConf(), ConfVars.METASTORE_SCHEMA_VERIFICATION_RECORD_VERSION);
      if (!recordVersion) {
         LOG.warn("setMetaStoreSchemaVersion called but recording version is disabled: version = " + schemaVersion + ", comment = " + comment);
      } else {
         LOG.warn("Setting metastore schema version in db to " + schemaVersion);

         MVersionTable mSchemaVer;
         try {
            mSchemaVer = this.getMSchemaVersion();
         } catch (NoSuchObjectException var10) {
            mSchemaVer = new MVersionTable();
         }

         mSchemaVer.setSchemaVersion(schemaVersion);
         mSchemaVer.setVersionComment(comment);

         try {
            this.openTransaction();
            this.pm.makePersistent(mSchemaVer);
            commited = this.commitTransaction();
         } finally {
            if (!commited) {
               this.rollbackTransaction();
            }

         }

      }
   }

   public boolean doesPartitionExist(String dbName, String tableName, List partVals) throws MetaException {
      try {
         return this.getPartition(dbName, tableName, partVals) != null;
      } catch (NoSuchObjectException var5) {
         return false;
      }
   }

   private void debugLog(String message) {
      if (LOG.isDebugEnabled()) {
         LOG.debug(message + this.getCallStack());
      }

   }

   private String getCallStack() {
      StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
      int thislimit = Math.min(5, stackTrace.length);
      StringBuilder sb = new StringBuilder();
      sb.append(" at:");

      for(int i = 4; i < thislimit; ++i) {
         sb.append("\n\t");
         sb.append(stackTrace[i].toString());
      }

      return sb.toString();
   }

   private Function convertToFunction(MFunction mfunc) {
      if (mfunc == null) {
         return null;
      } else {
         Function func = new Function(mfunc.getFunctionName(), mfunc.getDatabase().getName(), mfunc.getClassName(), mfunc.getOwnerName(), PrincipalType.valueOf(mfunc.getOwnerType()), mfunc.getCreateTime(), FunctionType.findByValue(mfunc.getFunctionType()), this.convertToResourceUriList(mfunc.getResourceUris()));
         return func;
      }
   }

   private List convertToFunctions(List mfuncs) {
      if (mfuncs == null) {
         return null;
      } else {
         List<Function> functions = new ArrayList();

         for(MFunction mfunc : mfuncs) {
            functions.add(this.convertToFunction(mfunc));
         }

         return functions;
      }
   }

   private MFunction convertToMFunction(Function func) throws InvalidObjectException {
      if (func == null) {
         return null;
      } else {
         MDatabase mdb = null;

         try {
            mdb = this.getMDatabase(func.getDbName());
         } catch (NoSuchObjectException e) {
            LOG.error(org.apache.hadoop.util.StringUtils.stringifyException(e));
            throw new InvalidObjectException("Database " + func.getDbName() + " doesn't exist.");
         }

         MFunction mfunc = new MFunction(func.getFunctionName(), mdb, func.getClassName(), func.getOwnerName(), func.getOwnerType().name(), func.getCreateTime(), func.getFunctionType().getValue(), this.convertToMResourceUriList(func.getResourceUris()));
         return mfunc;
      }
   }

   private List convertToResourceUriList(List mresourceUriList) {
      List<ResourceUri> resourceUriList = null;
      if (mresourceUriList != null) {
         resourceUriList = new ArrayList(mresourceUriList.size());

         for(MResourceUri mres : mresourceUriList) {
            resourceUriList.add(new ResourceUri(ResourceType.findByValue(mres.getResourceType()), mres.getUri()));
         }
      }

      return resourceUriList;
   }

   private List convertToMResourceUriList(List resourceUriList) {
      List<MResourceUri> mresourceUriList = null;
      if (resourceUriList != null) {
         mresourceUriList = new ArrayList(resourceUriList.size());

         for(ResourceUri res : resourceUriList) {
            mresourceUriList.add(new MResourceUri(res.getResourceType().getValue(), res.getUri()));
         }
      }

      return mresourceUriList;
   }

   public void createFunction(Function func) throws InvalidObjectException, MetaException {
      boolean committed = false;

      try {
         this.openTransaction();
         MFunction mfunc = this.convertToMFunction(func);
         this.pm.makePersistent(mfunc);
         committed = this.commitTransaction();
      } finally {
         if (!committed) {
            this.rollbackTransaction();
         }

      }

   }

   public void alterFunction(String dbName, String funcName, Function newFunction) throws InvalidObjectException, MetaException {
      boolean success = false;

      try {
         this.openTransaction();
         funcName = HiveStringUtils.normalizeIdentifier(funcName);
         dbName = HiveStringUtils.normalizeIdentifier(dbName);
         MFunction newf = this.convertToMFunction(newFunction);
         if (newf == null) {
            throw new InvalidObjectException("new function is invalid");
         }

         MFunction oldf = this.getMFunction(dbName, funcName);
         if (oldf == null) {
            throw new MetaException("function " + funcName + " doesn't exist");
         }

         oldf.setFunctionName(HiveStringUtils.normalizeIdentifier(newf.getFunctionName()));
         oldf.setDatabase(newf.getDatabase());
         oldf.setOwnerName(newf.getOwnerName());
         oldf.setOwnerType(newf.getOwnerType());
         oldf.setClassName(newf.getClassName());
         oldf.setFunctionType(newf.getFunctionType());
         success = this.commitTransaction();
      } finally {
         if (!success) {
            this.rollbackTransaction();
         }

      }

   }

   public void dropFunction(String dbName, String funcName) throws MetaException, NoSuchObjectException, InvalidObjectException, InvalidInputException {
      boolean success = false;

      try {
         this.openTransaction();
         MFunction mfunc = this.getMFunction(dbName, funcName);
         this.pm.retrieve(mfunc);
         if (mfunc != null) {
            this.pm.deletePersistentAll(new Object[]{mfunc});
         }

         success = this.commitTransaction();
      } finally {
         if (!success) {
            this.rollbackTransaction();
         }

      }

   }

   private MFunction getMFunction(String db, String function) {
      MFunction mfunc = null;
      boolean commited = false;
      Query query = null;

      try {
         this.openTransaction();
         db = HiveStringUtils.normalizeIdentifier(db);
         function = HiveStringUtils.normalizeIdentifier(function);
         query = this.pm.newQuery(MFunction.class, "functionName == function && database.name == db");
         query.declareParameters("java.lang.String function, java.lang.String db");
         query.setUnique(true);
         mfunc = (MFunction)query.execute(function, db);
         this.pm.retrieve(mfunc);
         commited = this.commitTransaction();
      } finally {
         this.rollbackAndCleanup(commited, query);
      }

      return mfunc;
   }

   public Function getFunction(String dbName, String funcName) throws MetaException {
      boolean commited = false;
      Function func = null;

      try {
         this.openTransaction();
         func = this.convertToFunction(this.getMFunction(dbName, funcName));
         commited = this.commitTransaction();
      } finally {
         if (!commited) {
            this.rollbackTransaction();
         }

      }

      return func;
   }

   public List getAllFunctions() throws MetaException {
      boolean commited = false;

      List var4;
      try {
         this.openTransaction();
         Query query = this.pm.newQuery(MFunction.class);
         List<MFunction> allFunctions = (List)query.execute();
         this.pm.retrieveAll(allFunctions);
         commited = this.commitTransaction();
         var4 = this.convertToFunctions(allFunctions);
      } finally {
         if (!commited) {
            this.rollbackTransaction();
         }

      }

      return var4;
   }

   public List getFunctions(String dbName, String pattern) throws MetaException {
      boolean commited = false;
      Query query = null;
      List<String> funcs = null;

      try {
         this.openTransaction();
         dbName = HiveStringUtils.normalizeIdentifier(dbName);
         List<String> parameterVals = new ArrayList();
         StringBuilder filterBuilder = new StringBuilder();
         this.appendSimpleCondition(filterBuilder, "database.name", new String[]{dbName}, parameterVals);
         if (pattern != null) {
            this.appendPatternCondition(filterBuilder, "functionName", pattern, parameterVals);
         }

         query = this.pm.newQuery(MFunction.class, filterBuilder.toString());
         query.setResult("functionName");
         query.setOrdering("functionName ascending");
         Collection names = (Collection)query.executeWithArray(parameterVals.toArray(new String[parameterVals.size()]));
         funcs = new ArrayList();
         Iterator i = names.iterator();

         while(i.hasNext()) {
            funcs.add((String)i.next());
         }

         commited = this.commitTransaction();
      } finally {
         this.rollbackAndCleanup(commited, query);
      }

      return funcs;
   }

   public NotificationEventResponse getNextNotification(NotificationEventRequest rqst) {
      boolean commited = false;
      Query query = null;
      NotificationEventResponse result = new NotificationEventResponse();
      result.setEvents(new ArrayList());

      NotificationEventResponse var8;
      try {
         this.openTransaction();
         long lastEvent = rqst.getLastEvent();
         query = this.pm.newQuery(MNotificationLog.class, "eventId > lastEvent");
         query.declareParameters("java.lang.Long lastEvent");
         query.setOrdering("eventId ascending");
         Collection<MNotificationLog> events = (Collection)query.execute(lastEvent);
         commited = this.commitTransaction();
         if (events != null) {
            Iterator<MNotificationLog> i = events.iterator();
            int maxEvents = rqst.getMaxEvents() > 0 ? rqst.getMaxEvents() : Integer.MAX_VALUE;
            int numEvents = 0;

            while(i.hasNext() && numEvents++ < maxEvents) {
               result.addToEvents(this.translateDbToThrift((MNotificationLog)i.next()));
            }

            NotificationEventResponse var11 = result;
            return var11;
         }

         var8 = result;
      } finally {
         if (!commited) {
            this.rollbackAndCleanup(commited, query);
            return null;
         }

      }

      return var8;
   }

   public void addNotificationEvent(NotificationEvent entry) {
      boolean commited = false;
      Query query = null;

      try {
         this.openTransaction();
         query = this.pm.newQuery(MNotificationNextId.class);
         Collection<MNotificationNextId> ids = (Collection)query.execute();
         MNotificationNextId id = null;
         boolean needToPersistId;
         if (ids != null && ids.size() != 0) {
            id = (MNotificationNextId)ids.iterator().next();
            needToPersistId = false;
         } else {
            id = new MNotificationNextId(1L);
            needToPersistId = true;
         }

         entry.setEventId(id.getNextEventId());
         id.incrementEventId();
         if (needToPersistId) {
            this.pm.makePersistent(id);
         }

         this.pm.makePersistent(this.translateThriftToDb(entry));
         commited = this.commitTransaction();
      } finally {
         this.rollbackAndCleanup(commited, query);
      }

   }

   public void cleanNotificationEvents(int olderThan) {
      boolean commited = false;
      Query query = null;

      try {
         this.openTransaction();
         long tmp = System.currentTimeMillis() / 1000L - (long)olderThan;
         int tooOld = tmp > 2147483647L ? 0 : (int)tmp;
         query = this.pm.newQuery(MNotificationLog.class, "eventTime < tooOld");
         query.declareParameters("java.lang.Integer tooOld");
         Collection<MNotificationLog> toBeRemoved = (Collection)query.execute(tooOld);
         if (toBeRemoved != null && toBeRemoved.size() > 0) {
            this.pm.deletePersistentAll(toBeRemoved);
         }

         commited = this.commitTransaction();
      } finally {
         this.rollbackAndCleanup(commited, query);
      }

   }

   public CurrentNotificationEventId getCurrentNotificationEventId() {
      boolean commited = false;
      Query query = null;

      CurrentNotificationEventId var6;
      try {
         this.openTransaction();
         query = this.pm.newQuery(MNotificationNextId.class);
         Collection<MNotificationNextId> ids = (Collection)query.execute();
         long id = 0L;
         if (ids != null && ids.size() > 0) {
            id = ((MNotificationNextId)ids.iterator().next()).getNextEventId() - 1L;
         }

         commited = this.commitTransaction();
         var6 = new CurrentNotificationEventId(id);
      } finally {
         this.rollbackAndCleanup(commited, query);
      }

      return var6;
   }

   private MNotificationLog translateThriftToDb(NotificationEvent entry) {
      MNotificationLog dbEntry = new MNotificationLog();
      dbEntry.setEventId(entry.getEventId());
      dbEntry.setEventTime(entry.getEventTime());
      dbEntry.setEventType(entry.getEventType());
      dbEntry.setDbName(entry.getDbName());
      dbEntry.setTableName(entry.getTableName());
      dbEntry.setMessage(entry.getMessage());
      dbEntry.setMessageFormat(entry.getMessageFormat());
      return dbEntry;
   }

   private NotificationEvent translateDbToThrift(MNotificationLog dbEvent) {
      NotificationEvent event = new NotificationEvent();
      event.setEventId(dbEvent.getEventId());
      event.setEventTime(dbEvent.getEventTime());
      event.setEventType(dbEvent.getEventType());
      event.setDbName(dbEvent.getDbName());
      event.setTableName(dbEvent.getTableName());
      event.setMessage(dbEvent.getMessage());
      event.setMessageFormat(dbEvent.getMessageFormat());
      return event;
   }

   public boolean isFileMetadataSupported() {
      return false;
   }

   public ByteBuffer[] getFileMetadata(List fileIds) {
      throw new UnsupportedOperationException();
   }

   public void putFileMetadata(List fileIds, List metadata, FileMetadataExprType type) {
      throw new UnsupportedOperationException();
   }

   public void getFileMetadataByExpr(List fileIds, FileMetadataExprType type, byte[] expr, ByteBuffer[] metadatas, ByteBuffer[] stripeBitsets, boolean[] eliminated) {
      throw new UnsupportedOperationException();
   }

   public FileMetadataHandler getFileMetadataHandler(FileMetadataExprType type) {
      throw new UnsupportedOperationException();
   }

   public static void unCacheDataNucleusClassLoaders() {
      PersistenceManagerFactory pmf = getPMF();
      clearOutPmfClassLoaderCache(pmf);
   }

   private static void clearOutPmfClassLoaderCache(PersistenceManagerFactory pmf) {
      if (pmf != null && pmf instanceof JDOPersistenceManagerFactory) {
         JDOPersistenceManagerFactory jdoPmf = (JDOPersistenceManagerFactory)pmf;
         NucleusContext nc = jdoPmf.getNucleusContext();

         try {
            Field pmCache = pmf.getClass().getDeclaredField("pmCache");
            pmCache.setAccessible(true);

            for(JDOPersistenceManager pm : (Set)pmCache.get(pmf)) {
               ExecutionContext ec = pm.getExecutionContext();
               if (ec instanceof ExecutionContextThreadedImpl) {
                  ClassLoaderResolver clr = ((ExecutionContextThreadedImpl)ec).getClassLoaderResolver();
                  clearClr(clr);
               }
            }

            PluginManager pluginManager = jdoPmf.getNucleusContext().getPluginManager();
            Field registryField = pluginManager.getClass().getDeclaredField("registry");
            registryField.setAccessible(true);
            PluginRegistry registry = (PluginRegistry)registryField.get(pluginManager);
            if (registry instanceof NonManagedPluginRegistry) {
               NonManagedPluginRegistry nRegistry = (NonManagedPluginRegistry)registry;
               Field clrField = nRegistry.getClass().getDeclaredField("clr");
               clrField.setAccessible(true);
               ClassLoaderResolver clr = (ClassLoaderResolver)clrField.get(nRegistry);
               clearClr(clr);
            }

            if (nc instanceof PersistenceNucleusContextImpl) {
               PersistenceNucleusContextImpl pnc = (PersistenceNucleusContextImpl)nc;
               TypeManagerImpl tm = (TypeManagerImpl)pnc.getTypeManager();
               Field clrField = tm.getClass().getDeclaredField("clr");
               clrField.setAccessible(true);
               ClassLoaderResolver clr = (ClassLoaderResolver)clrField.get(tm);
               clearClr(clr);
               Field storeMgrField = pnc.getClass().getDeclaredField("storeMgr");
               storeMgrField.setAccessible(true);
               RDBMSStoreManager storeMgr = (RDBMSStoreManager)storeMgrField.get(pnc);
               Field backingStoreField = storeMgr.getClass().getDeclaredField("backingStoreByMemberName");
               backingStoreField.setAccessible(true);
               Map<String, Store> backingStoreByMemberName = (Map)backingStoreField.get(storeMgr);

               for(Store store : backingStoreByMemberName.values()) {
                  BaseContainerStore baseStore = (BaseContainerStore)store;
                  clrField = BaseContainerStore.class.getDeclaredField("clr");
                  clrField.setAccessible(true);
                  clr = (ClassLoaderResolver)clrField.get(baseStore);
                  clearClr(clr);
               }
            }

            Field classLoaderResolverMap = AbstractNucleusContext.class.getDeclaredField("classLoaderResolverMap");
            classLoaderResolverMap.setAccessible(true);
            Map<String, ClassLoaderResolver> loaderMap = (Map)classLoaderResolverMap.get(nc);

            for(ClassLoaderResolver clr : loaderMap.values()) {
               clearClr(clr);
            }

            classLoaderResolverMap.set(nc, new HashMap());
            LOG.debug("Removed cached classloaders from DataNucleus NucleusContext");
         } catch (Exception e) {
            LOG.warn("Failed to remove cached classloaders from DataNucleus NucleusContext ", e);
         }

      }
   }

   private static void clearClr(ClassLoaderResolver clr) throws Exception {
      if (clr != null && clr instanceof ClassLoaderResolverImpl) {
         ClassLoaderResolverImpl clri = (ClassLoaderResolverImpl)clr;
         long resourcesCleared = clearFieldMap(clri, "resources");
         long loadedClassesCleared = clearFieldMap(clri, "loadedClasses");
         long unloadedClassesCleared = clearFieldMap(clri, "unloadedClasses");
         LOG.debug("Cleared ClassLoaderResolverImpl: " + resourcesCleared + "," + loadedClassesCleared + "," + unloadedClassesCleared);
      }

   }

   private static long clearFieldMap(ClassLoaderResolverImpl clri, String mapFieldName) throws Exception {
      Field mapField = ClassLoaderResolverImpl.class.getDeclaredField(mapFieldName);
      mapField.setAccessible(true);
      Map<String, Class> map = (Map)mapField.get(clri);
      long sz = (long)map.size();
      mapField.set(clri, Collections.synchronizedMap(new WeakValueMap()));
      return sz;
   }

   public List getPrimaryKeys(String db_name, String tbl_name) throws MetaException {
      try {
         return this.getPrimaryKeysInternal(db_name, tbl_name, true, true);
      } catch (NoSuchObjectException e) {
         throw new MetaException(e.getMessage());
      }
   }

   protected List getPrimaryKeysInternal(final String db_name, final String tbl_name, boolean allowSql, boolean allowJdo) throws MetaException, NoSuchObjectException {
      return (List)(new GetListHelper(db_name, tbl_name, allowSql, allowJdo) {
         protected List getSqlResult(GetHelper ctx) throws MetaException {
            return ObjectStore.this.directSql.getPrimaryKeys(db_name, tbl_name);
         }

         protected List getJdoResult(GetHelper ctx) throws MetaException, NoSuchObjectException {
            return ObjectStore.this.getPrimaryKeysViaJdo(db_name, tbl_name);
         }
      }).run(false);
   }

   private List getPrimaryKeysViaJdo(String db_name, String tbl_name) throws MetaException {
      boolean commited = false;
      List<SQLPrimaryKey> primaryKeys = null;
      Query query = null;

      try {
         this.openTransaction();
         query = this.pm.newQuery(MConstraint.class, "parentTable.tableName == tbl_name && parentTable.database.name == db_name && constraintType == MConstraint.PRIMARY_KEY_CONSTRAINT");
         query.declareParameters("java.lang.String tbl_name, java.lang.String db_name");
         Collection<?> constraints = (Collection)query.execute(tbl_name, db_name);
         this.pm.retrieveAll(constraints);
         primaryKeys = new ArrayList();

         for(MConstraint currPK : constraints) {
            int enableValidateRely = currPK.getEnableValidateRely();
            boolean enable = (enableValidateRely & 4) != 0;
            boolean validate = (enableValidateRely & 2) != 0;
            boolean rely = (enableValidateRely & 1) != 0;
            primaryKeys.add(new SQLPrimaryKey(db_name, tbl_name, ((MFieldSchema)currPK.getParentColumn().getCols().get(currPK.getParentIntegerIndex())).getName(), currPK.getPosition(), currPK.getConstraintName(), enable, validate, rely));
         }

         commited = this.commitTransaction();
      } finally {
         this.rollbackAndCleanup(commited, query);
      }

      return primaryKeys;
   }

   private String getPrimaryKeyConstraintName(String db_name, String tbl_name) throws MetaException {
      boolean commited = false;
      String ret = null;
      Query query = null;

      try {
         this.openTransaction();
         query = this.pm.newQuery(MConstraint.class, "parentTable.tableName == tbl_name && parentTable.database.name == db_name && constraintType == MConstraint.PRIMARY_KEY_CONSTRAINT");
         query.declareParameters("java.lang.String tbl_name, java.lang.String db_name");
         Collection<?> constraints = (Collection)query.execute(tbl_name, db_name);
         this.pm.retrieveAll(constraints);
         Iterator<?> i = constraints.iterator();
         if (i.hasNext()) {
            MConstraint currPK = (MConstraint)i.next();
            ret = currPK.getConstraintName();
         }

         commited = this.commitTransaction();
      } finally {
         this.rollbackAndCleanup(commited, query);
      }

      return ret;
   }

   public List getForeignKeys(String parent_db_name, String parent_tbl_name, String foreign_db_name, String foreign_tbl_name) throws MetaException {
      try {
         return this.getForeignKeysInternal(parent_db_name, parent_tbl_name, foreign_db_name, foreign_tbl_name, true, true);
      } catch (NoSuchObjectException e) {
         throw new MetaException(e.getMessage());
      }
   }

   protected List getForeignKeysInternal(final String parent_db_name, final String parent_tbl_name, final String foreign_db_name, final String foreign_tbl_name, boolean allowSql, boolean allowJdo) throws MetaException, NoSuchObjectException {
      return (List)(new GetListHelper(foreign_db_name, foreign_tbl_name, allowSql, allowJdo) {
         protected List getSqlResult(GetHelper ctx) throws MetaException {
            return ObjectStore.this.directSql.getForeignKeys(parent_db_name, parent_tbl_name, foreign_db_name, foreign_tbl_name);
         }

         protected List getJdoResult(GetHelper ctx) throws MetaException, NoSuchObjectException {
            return ObjectStore.this.getForeignKeysViaJdo(parent_db_name, parent_tbl_name, foreign_db_name, foreign_tbl_name);
         }
      }).run(false);
   }

   private List getForeignKeysViaJdo(String parent_db_name, String parent_tbl_name, String foreign_db_name, String foreign_tbl_name) throws MetaException {
      boolean commited = false;
      List<SQLForeignKey> foreignKeys = null;
      Collection<?> constraints = null;
      Query query = null;
      Map<String, String> tblToConstraint = new HashMap();

      try {
         this.openTransaction();
         String queryText = (parent_tbl_name != null ? "parentTable.tableName == parent_tbl_name && " : "") + (parent_db_name != null ? " parentTable.database.name == parent_db_name && " : "") + (foreign_tbl_name != null ? " childTable.tableName == foreign_tbl_name && " : "") + (foreign_db_name != null ? " childTable.database.name == foreign_db_name && " : "") + " constraintType == MConstraint.FOREIGN_KEY_CONSTRAINT";
         queryText = queryText.trim();
         query = this.pm.newQuery(MConstraint.class, queryText);
         String paramText = (parent_tbl_name == null ? "" : "java.lang.String parent_tbl_name,") + (parent_db_name == null ? "" : " java.lang.String parent_db_name, ") + (foreign_tbl_name == null ? "" : "java.lang.String foreign_tbl_name,") + (foreign_db_name == null ? "" : " java.lang.String foreign_db_name");
         paramText = paramText.trim();
         if (paramText.endsWith(",")) {
            paramText = paramText.substring(0, paramText.length() - 1);
         }

         query.declareParameters(paramText);
         List<String> params = new ArrayList();
         if (parent_tbl_name != null) {
            params.add(parent_tbl_name);
         }

         if (parent_db_name != null) {
            params.add(parent_db_name);
         }

         if (foreign_tbl_name != null) {
            params.add(foreign_tbl_name);
         }

         if (foreign_db_name != null) {
            params.add(foreign_db_name);
         }

         if (params.size() == 0) {
            constraints = (Collection)query.execute();
         } else if (params.size() == 1) {
            constraints = (Collection)query.execute(params.get(0));
         } else if (params.size() == 2) {
            constraints = (Collection)query.execute(params.get(0), params.get(1));
         } else if (params.size() == 3) {
            constraints = (Collection)query.execute(params.get(0), params.get(1), params.get(2));
         } else {
            constraints = (Collection)query.executeWithArray(new Object[]{params.get(0), params.get(1), params.get(2), params.get(3)});
         }

         this.pm.retrieveAll(constraints);
         foreignKeys = new ArrayList();

         for(MConstraint currPKFK : constraints) {
            int enableValidateRely = currPKFK.getEnableValidateRely();
            boolean enable = (enableValidateRely & 4) != 0;
            boolean validate = (enableValidateRely & 2) != 0;
            boolean rely = (enableValidateRely & 1) != 0;
            String consolidatedtblName = currPKFK.getParentTable().getDatabase().getName() + "." + currPKFK.getParentTable().getTableName();
            String pkName;
            if (tblToConstraint.containsKey(consolidatedtblName)) {
               pkName = (String)tblToConstraint.get(consolidatedtblName);
            } else {
               pkName = this.getPrimaryKeyConstraintName(currPKFK.getParentTable().getDatabase().getName(), currPKFK.getParentTable().getDatabase().getName());
               tblToConstraint.put(consolidatedtblName, pkName);
            }

            foreignKeys.add(new SQLForeignKey(currPKFK.getParentTable().getDatabase().getName(), currPKFK.getParentTable().getDatabase().getName(), ((MFieldSchema)currPKFK.getParentColumn().getCols().get(currPKFK.getParentIntegerIndex())).getName(), currPKFK.getChildTable().getDatabase().getName(), currPKFK.getChildTable().getTableName(), ((MFieldSchema)currPKFK.getChildColumn().getCols().get(currPKFK.getChildIntegerIndex())).getName(), currPKFK.getPosition(), currPKFK.getUpdateRule(), currPKFK.getDeleteRule(), currPKFK.getConstraintName(), pkName, enable, validate, rely));
         }

         commited = this.commitTransaction();
      } finally {
         this.rollbackAndCleanup(commited, query);
      }

      return foreignKeys;
   }

   public void dropConstraint(String dbName, String tableName, String constraintName) throws NoSuchObjectException {
      boolean success = false;

      try {
         this.openTransaction();
         List<MConstraint> tabConstraints = this.listAllTableConstraintsWithOptionalConstraintName(dbName, tableName, constraintName);
         if (tabConstraints == null || tabConstraints.size() <= 0) {
            throw new NoSuchObjectException("The constraint: " + constraintName + " does not exist for the associated table: " + dbName + "." + tableName);
         }

         this.pm.deletePersistentAll(tabConstraints);
         success = this.commitTransaction();
      } finally {
         if (!success) {
            this.rollbackTransaction();
         }

      }

   }

   @VisibleForTesting
   void rollbackAndCleanup(boolean success, Query query) {
      try {
         if (!success) {
            this.rollbackTransaction();
         }
      } finally {
         if (query != null) {
            query.closeAll();
         }

      }

   }

   @VisibleForTesting
   void rollbackAndCleanup(boolean success, QueryWrapper queryWrapper) {
      try {
         if (!success) {
            this.rollbackTransaction();
         }
      } finally {
         if (queryWrapper != null) {
            queryWrapper.close();
         }

      }

   }

   static {
      Map<String, Class> map = new HashMap();
      map.put("table", MTable.class);
      map.put("storagedescriptor", MStorageDescriptor.class);
      map.put("serdeinfo", MSerDeInfo.class);
      map.put("partition", MPartition.class);
      map.put("database", MDatabase.class);
      map.put("type", MType.class);
      map.put("fieldschema", MFieldSchema.class);
      map.put("order", MOrder.class);
      PINCLASSMAP = Collections.unmodifiableMap(map);
      String hostname = "UNKNOWN";

      try {
         InetAddress clientAddr = InetAddress.getLocalHost();
         hostname = clientAddr.getHostAddress();
      } catch (IOException var3) {
      }

      HOSTNAME = hostname;
      String user = System.getenv("USER");
      if (user == null) {
         USER = "UNKNOWN";
      } else {
         USER = user;
      }

      retriableExceptionClasses = new HashSet(Arrays.asList(JDOCanRetryException.class));
   }

   private static enum TXN_STATUS {
      NO_STATE,
      OPEN,
      COMMITED,
      ROLLBACK;
   }

   public static class QueryWrapper implements AutoCloseable {
      public Query query;

      public void close() {
         if (this.query != null) {
            this.query.closeAll();
            this.query = null;
         }

      }
   }

   class AttachedMTableInfo {
      MTable mtbl;
      MColumnDescriptor mcd;

      public AttachedMTableInfo() {
      }

      public AttachedMTableInfo(MTable mtbl, MColumnDescriptor mcd) {
         this.mtbl = mtbl;
         this.mcd = mcd;
      }
   }

   @VisibleForTesting
   public abstract class GetHelper {
      private final boolean isInTxn;
      private final boolean doTrace;
      private final boolean allowJdo;
      private boolean doUseDirectSql;
      private long start;
      private Table table;
      protected final String dbName;
      protected final String tblName;
      private boolean success = false;
      protected Object results = null;

      public GetHelper(String dbName, String tblName, boolean allowSql, boolean allowJdo) throws MetaException {
         assert allowSql || allowJdo;

         this.allowJdo = allowJdo;
         this.dbName = HiveStringUtils.normalizeIdentifier(dbName);
         if (tblName != null) {
            this.tblName = HiveStringUtils.normalizeIdentifier(tblName);
         } else {
            this.tblName = null;
            this.table = null;
         }

         this.doTrace = ObjectStore.LOG.isDebugEnabled();
         this.isInTxn = ObjectStore.this.isActiveTransaction();
         boolean isConfigEnabled = HiveConf.getBoolVar(ObjectStore.this.getConf(), ConfVars.METASTORE_TRY_DIRECT_SQL) && (HiveConf.getBoolVar(ObjectStore.this.getConf(), ConfVars.METASTORE_TRY_DIRECT_SQL_DDL) || !this.isInTxn);
         if (isConfigEnabled && ObjectStore.this.directSql == null) {
            ObjectStore.this.directSql = new MetaStoreDirectSql(ObjectStore.this.pm, ObjectStore.this.getConf());
         }

         if (!allowJdo && isConfigEnabled && !ObjectStore.this.directSql.isCompatibleDatastore()) {
            throw new MetaException("SQL is not operational");
         } else {
            this.doUseDirectSql = allowSql && isConfigEnabled && ObjectStore.this.directSql.isCompatibleDatastore();
         }
      }

      protected boolean canUseDirectSql(GetHelper ctx) throws MetaException {
         return true;
      }

      protected abstract String describeResult();

      protected abstract Object getSqlResult(GetHelper var1) throws MetaException;

      protected abstract Object getJdoResult(GetHelper var1) throws MetaException, NoSuchObjectException;

      public Object run(boolean initTable) throws MetaException, NoSuchObjectException {
         Object ex;
         try {
            this.start(initTable);
            if (this.doUseDirectSql) {
               try {
                  ObjectStore.this.directSql.prepareTxn();
                  this.results = this.getSqlResult(this);
               } catch (Exception ex) {
                  this.handleDirectSqlError(ex);
               }
            }

            if (!this.doUseDirectSql) {
               this.results = this.getJdoResult(this);
            }

            ex = this.commit();
         } catch (NoSuchObjectException ex) {
            throw ex;
         } catch (MetaException ex) {
            throw ex;
         } catch (Exception ex) {
            ObjectStore.LOG.error("", ex);
            throw new MetaException(ex.getMessage());
         } finally {
            this.close();
         }

         return ex;
      }

      private void start(boolean initTable) throws MetaException, NoSuchObjectException {
         this.start = this.doTrace ? System.nanoTime() : 0L;
         ObjectStore.this.openTransaction();
         if (initTable && this.tblName != null) {
            this.table = ObjectStore.this.ensureGetTable(this.dbName, this.tblName);
         }

         this.doUseDirectSql = this.doUseDirectSql && this.canUseDirectSql(this);
      }

      private void handleDirectSqlError(Exception ex) throws MetaException, NoSuchObjectException {
         String message = null;

         try {
            message = this.generateShorterMessage(ex);
         } catch (Throwable t) {
            message = ex.toString() + "; error building a better message: " + t.getMessage();
         }

         ObjectStore.LOG.warn(message);
         if (ObjectStore.LOG.isDebugEnabled()) {
            ObjectStore.LOG.debug("Full DirectSQL callstack for debugging (note: this is not an error)", ex);
         }

         if (!this.allowJdo) {
            if (ex instanceof MetaException) {
               throw (MetaException)ex;
            } else {
               throw new MetaException(ex.getMessage());
            }
         } else {
            if (!this.isInTxn) {
               JDOException rollbackEx = null;

               try {
                  ObjectStore.this.rollbackTransaction();
               } catch (JDOException jex) {
                  rollbackEx = jex;
               }

               if (rollbackEx != null) {
                  if (ObjectStore.this.currentTransaction != null && ObjectStore.this.currentTransaction.isActive()) {
                     throw rollbackEx;
                  }

                  ObjectStore.LOG.info("Ignoring exception, rollback succeeded: " + rollbackEx.getMessage());
               }

               this.start = this.doTrace ? System.nanoTime() : 0L;
               ObjectStore.this.openTransaction();
               if (this.table != null) {
                  this.table = ObjectStore.this.ensureGetTable(this.dbName, this.tblName);
               }
            } else {
               this.start = this.doTrace ? System.nanoTime() : 0L;
            }

            Metrics metrics = MetricsFactory.getInstance();
            if (metrics != null) {
               try {
                  metrics.incrementCounter("directsql_errors");
               } catch (Exception e) {
                  ObjectStore.LOG.warn("Error reporting Direct SQL errors to metrics system", e);
               }
            }

            this.doUseDirectSql = false;
         }
      }

      private String generateShorterMessage(Exception ex) {
         StringBuilder message = new StringBuilder("Falling back to ORM path due to direct SQL failure (this is not an error): ");
         Throwable t = ex;
         StackTraceElement[] prevStack = null;

         while(t != null) {
            message.append(t.getMessage());
            StackTraceElement[] stack = t.getStackTrace();
            int uniqueFrames = stack.length - 1;
            if (prevStack != null) {
               for(int n = prevStack.length - 1; uniqueFrames >= 0 && n >= 0 && stack[uniqueFrames].equals(prevStack[n]); --n) {
                  --uniqueFrames;
               }
            }

            for(int i = 0; i <= uniqueFrames; ++i) {
               StackTraceElement ste = stack[i];
               message.append(" at ").append(ste);
               if (ste.getMethodName() != null && ste.getMethodName().contains("getSqlResult") && (ste.getFileName() == null || ste.getFileName().contains("ObjectStore"))) {
                  break;
               }
            }

            prevStack = stack;
            t = t.getCause();
            if (t != null) {
               message.append(";\n Caused by: ");
            }
         }

         return message.toString();
      }

      private Object commit() {
         this.success = ObjectStore.this.commitTransaction();
         if (this.doTrace) {
            ObjectStore.LOG.debug(this.describeResult() + " retrieved using " + (this.doUseDirectSql ? "SQL" : "ORM") + " in " + (double)(System.nanoTime() - this.start) / (double)1000000.0F + "ms");
         }

         return this.results;
      }

      private void close() {
         if (!this.success) {
            ObjectStore.this.rollbackTransaction();
         }

      }

      public Table getTable() {
         return this.table;
      }
   }

   private abstract class GetListHelper extends GetHelper {
      public GetListHelper(String dbName, String tblName, boolean allowSql, boolean allowJdo) throws MetaException {
         super(dbName, tblName, allowSql, allowJdo);
      }

      protected String describeResult() {
         return ((List)this.results).size() + " entries";
      }
   }

   @VisibleForTesting
   public abstract class GetDbHelper extends GetHelper {
      public GetDbHelper(String dbName, String tblName, boolean allowSql, boolean allowJdo) throws MetaException {
         super(dbName, (String)null, allowSql, allowJdo);
      }

      protected String describeResult() {
         return "db details for db " + this.dbName;
      }
   }

   private abstract class GetStatHelper extends GetHelper {
      public GetStatHelper(String dbName, String tblName, boolean allowSql, boolean allowJdo) throws MetaException {
         super(dbName, tblName, allowSql, allowJdo);
      }

      protected String describeResult() {
         return "statistics for " + (this.results == null ? 0 : ((ColumnStatistics)this.results).getStatsObjSize()) + " columns";
      }
   }

   public class UpdateMDatabaseURIRetVal {
      private List badRecords;
      private Map updateLocations;

      UpdateMDatabaseURIRetVal(List badRecords, Map updateLocations) {
         this.badRecords = badRecords;
         this.updateLocations = updateLocations;
      }

      public List getBadRecords() {
         return this.badRecords;
      }

      public void setBadRecords(List badRecords) {
         this.badRecords = badRecords;
      }

      public Map getUpdateLocations() {
         return this.updateLocations;
      }

      public void setUpdateLocations(Map updateLocations) {
         this.updateLocations = updateLocations;
      }
   }

   public class UpdatePropURIRetVal {
      private List badRecords;
      private Map updateLocations;

      UpdatePropURIRetVal(List badRecords, Map updateLocations) {
         this.badRecords = badRecords;
         this.updateLocations = updateLocations;
      }

      public List getBadRecords() {
         return this.badRecords;
      }

      public void setBadRecords(List badRecords) {
         this.badRecords = badRecords;
      }

      public Map getUpdateLocations() {
         return this.updateLocations;
      }

      public void setUpdateLocations(Map updateLocations) {
         this.updateLocations = updateLocations;
      }
   }

   public class UpdateMStorageDescriptorTblURIRetVal {
      private List badRecords;
      private Map updateLocations;
      private int numNullRecords;

      UpdateMStorageDescriptorTblURIRetVal(List badRecords, Map updateLocations, int numNullRecords) {
         this.badRecords = badRecords;
         this.updateLocations = updateLocations;
         this.numNullRecords = numNullRecords;
      }

      public List getBadRecords() {
         return this.badRecords;
      }

      public void setBadRecords(List badRecords) {
         this.badRecords = badRecords;
      }

      public Map getUpdateLocations() {
         return this.updateLocations;
      }

      public void setUpdateLocations(Map updateLocations) {
         this.updateLocations = updateLocations;
      }

      public int getNumNullRecords() {
         return this.numNullRecords;
      }

      public void setNumNullRecords(int numNullRecords) {
         this.numNullRecords = numNullRecords;
      }
   }

   public class UpdateSerdeURIRetVal {
      private List badRecords;
      private Map updateLocations;

      UpdateSerdeURIRetVal(List badRecords, Map updateLocations) {
         this.badRecords = badRecords;
         this.updateLocations = updateLocations;
      }

      public List getBadRecords() {
         return this.badRecords;
      }

      public void setBadRecords(List badRecords) {
         this.badRecords = badRecords;
      }

      public Map getUpdateLocations() {
         return this.updateLocations;
      }

      public void setUpdateLocations(Map updateLocations) {
         this.updateLocations = updateLocations;
      }
   }
}
