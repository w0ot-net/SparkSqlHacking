package org.apache.derby.impl.sql.catalog;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.SortedSet;
import org.apache.derby.catalog.DefaultInfo;
import org.apache.derby.catalog.DependableFinder;
import org.apache.derby.catalog.TypeDescriptor;
import org.apache.derby.catalog.UUID;
import org.apache.derby.catalog.types.BaseTypeIdImpl;
import org.apache.derby.catalog.types.RoutineAliasInfo;
import org.apache.derby.iapi.db.Database;
import org.apache.derby.iapi.services.cache.CacheFactory;
import org.apache.derby.iapi.services.cache.CacheManager;
import org.apache.derby.iapi.services.cache.Cacheable;
import org.apache.derby.iapi.services.cache.CacheableFactory;
import org.apache.derby.iapi.services.context.Context;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.context.ContextService;
import org.apache.derby.iapi.services.daemon.IndexStatisticsDaemon;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.services.locks.CompatibilitySpace;
import org.apache.derby.iapi.services.locks.LockFactory;
import org.apache.derby.iapi.services.locks.ShExLockable;
import org.apache.derby.iapi.services.locks.ShExQual;
import org.apache.derby.iapi.services.monitor.ModuleControl;
import org.apache.derby.iapi.services.monitor.ModuleFactory;
import org.apache.derby.iapi.services.monitor.ModuleSupportable;
import org.apache.derby.iapi.services.monitor.Monitor;
import org.apache.derby.iapi.services.property.PropertyUtil;
import org.apache.derby.iapi.services.uuid.UUIDFactory;
import org.apache.derby.iapi.sql.compile.Visitable;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.conn.LanguageConnectionFactory;
import org.apache.derby.iapi.sql.depend.DependencyManager;
import org.apache.derby.iapi.sql.dictionary.AliasDescriptor;
import org.apache.derby.iapi.sql.dictionary.BulkInsertCounter;
import org.apache.derby.iapi.sql.dictionary.CatalogRowFactory;
import org.apache.derby.iapi.sql.dictionary.ColPermsDescriptor;
import org.apache.derby.iapi.sql.dictionary.ColumnDescriptor;
import org.apache.derby.iapi.sql.dictionary.ColumnDescriptorList;
import org.apache.derby.iapi.sql.dictionary.ConglomerateDescriptor;
import org.apache.derby.iapi.sql.dictionary.ConglomerateDescriptorList;
import org.apache.derby.iapi.sql.dictionary.ConstraintDescriptor;
import org.apache.derby.iapi.sql.dictionary.ConstraintDescriptorList;
import org.apache.derby.iapi.sql.dictionary.DataDescriptorGenerator;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.DefaultDescriptor;
import org.apache.derby.iapi.sql.dictionary.DependencyDescriptor;
import org.apache.derby.iapi.sql.dictionary.FileInfoDescriptor;
import org.apache.derby.iapi.sql.dictionary.ForeignKeyConstraintDescriptor;
import org.apache.derby.iapi.sql.dictionary.IndexRowGenerator;
import org.apache.derby.iapi.sql.dictionary.KeyConstraintDescriptor;
import org.apache.derby.iapi.sql.dictionary.PasswordHasher;
import org.apache.derby.iapi.sql.dictionary.PermDescriptor;
import org.apache.derby.iapi.sql.dictionary.PermissionsDescriptor;
import org.apache.derby.iapi.sql.dictionary.ReferencedKeyConstraintDescriptor;
import org.apache.derby.iapi.sql.dictionary.RoleClosureIterator;
import org.apache.derby.iapi.sql.dictionary.RoleGrantDescriptor;
import org.apache.derby.iapi.sql.dictionary.RoutinePermsDescriptor;
import org.apache.derby.iapi.sql.dictionary.SPSDescriptor;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.sql.dictionary.SequenceDescriptor;
import org.apache.derby.iapi.sql.dictionary.StatisticsDescriptor;
import org.apache.derby.iapi.sql.dictionary.SubCheckConstraintDescriptor;
import org.apache.derby.iapi.sql.dictionary.SubConstraintDescriptor;
import org.apache.derby.iapi.sql.dictionary.SubKeyConstraintDescriptor;
import org.apache.derby.iapi.sql.dictionary.SystemColumn;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.sql.dictionary.TablePermsDescriptor;
import org.apache.derby.iapi.sql.dictionary.TriggerDescriptor;
import org.apache.derby.iapi.sql.dictionary.TriggerDescriptorList;
import org.apache.derby.iapi.sql.dictionary.TupleDescriptor;
import org.apache.derby.iapi.sql.dictionary.UserDescriptor;
import org.apache.derby.iapi.sql.dictionary.ViewDescriptor;
import org.apache.derby.iapi.sql.execute.ExecIndexRow;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.sql.execute.ExecutionFactory;
import org.apache.derby.iapi.sql.execute.ScanQualifier;
import org.apache.derby.iapi.store.access.AccessFactory;
import org.apache.derby.iapi.store.access.ColumnOrdering;
import org.apache.derby.iapi.store.access.ConglomerateController;
import org.apache.derby.iapi.store.access.FileResource;
import org.apache.derby.iapi.store.access.Qualifier;
import org.apache.derby.iapi.store.access.RowUtil;
import org.apache.derby.iapi.store.access.ScanController;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.DataValueFactory;
import org.apache.derby.iapi.types.NumberDataValue;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.iapi.types.SQLBoolean;
import org.apache.derby.iapi.types.SQLChar;
import org.apache.derby.iapi.types.SQLLongint;
import org.apache.derby.iapi.types.SQLVarchar;
import org.apache.derby.iapi.types.TypeId;
import org.apache.derby.iapi.types.UserType;
import org.apache.derby.iapi.util.IdUtil;
import org.apache.derby.impl.services.daemon.IndexStatisticsDaemonImpl;
import org.apache.derby.impl.sql.compile.ColumnReference;
import org.apache.derby.impl.sql.compile.QueryTreeNode;
import org.apache.derby.impl.sql.compile.TableName;
import org.apache.derby.impl.sql.depend.BasicDependencyManager;
import org.apache.derby.impl.sql.execute.JarUtil;
import org.apache.derby.shared.common.error.StandardException;

public final class DataDictionaryImpl implements DataDictionary, CacheableFactory, ModuleControl, ModuleSupportable {
   private static final String CFG_SYSTABLES_ID = "SystablesIdentifier";
   private static final String CFG_SYSTABLES_INDEX1_ID = "SystablesIndex1Identifier";
   private static final String CFG_SYSTABLES_INDEX2_ID = "SystablesIndex2Identifier";
   private static final String CFG_SYSCOLUMNS_ID = "SyscolumnsIdentifier";
   private static final String CFG_SYSCOLUMNS_INDEX1_ID = "SyscolumnsIndex1Identifier";
   private static final String CFG_SYSCOLUMNS_INDEX2_ID = "SyscolumnsIndex2Identifier";
   private static final String CFG_SYSCONGLOMERATES_ID = "SysconglomeratesIdentifier";
   private static final String CFG_SYSCONGLOMERATES_INDEX1_ID = "SysconglomeratesIndex1Identifier";
   private static final String CFG_SYSCONGLOMERATES_INDEX2_ID = "SysconglomeratesIndex2Identifier";
   private static final String CFG_SYSCONGLOMERATES_INDEX3_ID = "SysconglomeratesIndex3Identifier";
   private static final String CFG_SYSSCHEMAS_ID = "SysschemasIdentifier";
   private static final String CFG_SYSSCHEMAS_INDEX1_ID = "SysschemasIndex1Identifier";
   private static final String CFG_SYSSCHEMAS_INDEX2_ID = "SysschemasIndex2Identifier";
   private static final int SYSCONGLOMERATES_CORE_NUM = 0;
   private static final int SYSTABLES_CORE_NUM = 1;
   private static final int SYSCOLUMNS_CORE_NUM = 2;
   private static final int SYSSCHEMAS_CORE_NUM = 3;
   private static final int NUM_CORE = 4;
   private static final String[][] SYSFUN_FUNCTIONS = new String[][]{{"ACOS", "DOUBLE", "java.lang.StrictMath", "acos(double)", "true", "false", "DOUBLE"}, {"ASIN", "DOUBLE", "java.lang.StrictMath", "asin(double)", "true", "false", "DOUBLE"}, {"ATAN", "DOUBLE", "java.lang.StrictMath", "atan(double)", "true", "false", "DOUBLE"}, {"ATAN2", "DOUBLE", "java.lang.StrictMath", "atan2(double,double)", "true", "false", "DOUBLE", "DOUBLE"}, {"COS", "DOUBLE", "java.lang.StrictMath", "cos(double)", "true", "false", "DOUBLE"}, {"SIN", "DOUBLE", "java.lang.StrictMath", "sin(double)", "true", "false", "DOUBLE"}, {"TAN", "DOUBLE", "java.lang.StrictMath", "tan(double)", "true", "false", "DOUBLE"}, {"PI", "DOUBLE", "org.apache.derby.catalog.SystemProcedures", "PI()", "true", "false"}, {"DEGREES", "DOUBLE", "java.lang.StrictMath", "toDegrees(double)", "true", "false", "DOUBLE"}, {"RADIANS", "DOUBLE", "java.lang.StrictMath", "toRadians(double)", "true", "false", "DOUBLE"}, {"LN", "DOUBLE", "java.lang.StrictMath", "log(double)", "true", "false", "DOUBLE"}, {"LOG", "DOUBLE", "java.lang.StrictMath", "log(double)", "true", "false", "DOUBLE"}, {"LOG10", "DOUBLE", "java.lang.StrictMath", "log10(double)", "true", "false", "DOUBLE"}, {"EXP", "DOUBLE", "java.lang.StrictMath", "exp(double)", "true", "false", "DOUBLE"}, {"CEIL", "DOUBLE", "java.lang.StrictMath", "ceil(double)", "true", "false", "DOUBLE"}, {"CEILING", "DOUBLE", "java.lang.StrictMath", "ceil(double)", "true", "false", "DOUBLE"}, {"FLOOR", "DOUBLE", "java.lang.StrictMath", "floor(double)", "true", "false", "DOUBLE"}, {"SIGN", "INTEGER", "org.apache.derby.catalog.SystemProcedures", "SIGN(double)", "true", "false", "DOUBLE"}, {"RANDOM", "DOUBLE", "java.lang.StrictMath", "random()", "false", "false"}, {"RAND", "DOUBLE", "org.apache.derby.catalog.SystemProcedures", "RAND(int)", "false", "false", "INTEGER"}, {"COT", "DOUBLE", "org.apache.derby.catalog.SystemProcedures", "COT(double)", "true", "false", "DOUBLE"}, {"COSH", "DOUBLE", "java.lang.StrictMath", "cosh(double)", "true", "false", "DOUBLE"}, {"SINH", "DOUBLE", "java.lang.StrictMath", "sinh(double)", "true", "false", "DOUBLE"}, {"TANH", "DOUBLE", "java.lang.StrictMath", "tanh(double)", "true", "false", "DOUBLE"}};
   private static final int SYSFUN_DETERMINISTIC_INDEX = 4;
   private static final int SYSFUN_VARARGS_INDEX = 5;
   private static final int SYSFUN_FIRST_PARAMETER_INDEX = 6;
   private final AliasDescriptor[] sysfunDescriptors;
   private TabInfoImpl[] coreInfo;
   private SchemaDescriptor systemSchemaDesc;
   private SchemaDescriptor sysIBMSchemaDesc;
   private SchemaDescriptor declaredGlobalTemporaryTablesSchemaDesc;
   private SchemaDescriptor systemUtilSchemaDesc;
   private static final String[] nonCoreNames = new String[]{"SYSCONSTRAINTS", "SYSKEYS", "SYSDEPENDS", "SYSALIASES", "SYSVIEWS", "SYSCHECKS", "SYSFOREIGNKEYS", "SYSSTATEMENTS", "SYSFILES", "SYSTRIGGERS", "SYSSTATISTICS", "SYSDUMMY1", "SYSTABLEPERMS", "SYSCOLPERMS", "SYSROUTINEPERMS", "SYSROLES", "SYSSEQUENCES", "SYSPERMS", "SYSUSERS"};
   private static final int NUM_NONCORE;
   private static final String[] systemSchemaNames;
   private DD_Version dictionaryVersion;
   private DD_Version softwareVersion;
   private String authorizationDatabaseOwner;
   private boolean usesSqlAuthorization;
   private TabInfoImpl[] noncoreInfo;
   public DataDescriptorGenerator dataDescriptorGenerator;
   private DataValueFactory dvf;
   AccessFactory af;
   private ExecutionFactory exFactory;
   protected UUIDFactory uuidFactory;
   private IndexStatisticsDaemon indexRefresher;
   Properties startupParameters;
   int engineType;
   protected boolean booting;
   private TransactionController bootingTC;
   protected DependencyManager dmgr;
   CacheManager OIDTdCache;
   CacheManager nameTdCache;
   private CacheManager spsNameCache;
   private CacheManager sequenceGeneratorCache;
   private Hashtable spsIdHash;
   int tdCacheSize;
   int stmtCacheSize;
   private int seqgenCacheSize;
   CacheManager permissionsCache;
   int permissionsCacheSize;
   ShExLockable cacheCoordinator;
   public LockFactory lockFactory;
   volatile int cacheMode;
   volatile int ddlUsers;
   volatile int readersInDDLMode;
   private HashMap sequenceIDs;
   private boolean readOnlyUpgrade;
   private boolean indexStatsUpdateDisabled;
   private boolean indexStatsUpdateLogging;
   private String indexStatsUpdateTracing;
   private int systemSQLNameSeed;
   private static final String[] sysUtilProceduresWithPublicAccess;
   private static final String[] sysUtilFunctionsWithPublicAccess;
   private int collationTypeOfSystemSchemas;
   private int collationTypeOfUserSchemas;
   static final int DROP = 0;
   static final int EXISTS = 1;
   private String spsSet;
   private static final String[] colPrivTypeMap;
   private static final String[] colPrivTypeMapForGrant;
   private String[][] DIAG_VTI_TABLE_CLASSES;
   private String[][] DIAG_VTI_TABLE_FUNCTION_CLASSES;

   public DataDictionaryImpl() {
      this.sysfunDescriptors = new AliasDescriptor[SYSFUN_FUNCTIONS.length];
      this.cacheMode = 0;
      this.systemSQLNameSeed = 0;
      this.DIAG_VTI_TABLE_CLASSES = new String[][]{{"LOCK_TABLE", "org.apache.derby.diag.LockTable"}, {"STATEMENT_CACHE", "org.apache.derby.diag.StatementCache"}, {"TRANSACTION_TABLE", "org.apache.derby.diag.TransactionTable"}, {"ERROR_MESSAGES", "org.apache.derby.diag.ErrorMessages"}};
      this.DIAG_VTI_TABLE_FUNCTION_CLASSES = new String[][]{{"SPACE_TABLE", "org.apache.derby.diag.SpaceTable"}, {"ERROR_LOG_READER", "org.apache.derby.diag.ErrorLogReader"}, {"STATEMENT_DURATION", "org.apache.derby.diag.StatementDuration"}, {"CONTAINED_ROLES", "org.apache.derby.diag.ContainedRoles"}};
   }

   public boolean canSupport(Properties var1) {
      return Monitor.isDesiredType(var1, 2);
   }

   public void boot(boolean var1, Properties var2) throws StandardException {
      this.softwareVersion = new DD_Version(this, 280);
      this.startupParameters = var2;
      this.uuidFactory = getMonitor().getUUIDFactory();
      this.engineType = Monitor.getEngineType(var2);
      this.collationTypeOfSystemSchemas = 0;
      this.getBuiltinSystemSchemas();
      LanguageConnectionFactory var3 = (LanguageConnectionFactory)bootServiceModule(var1, this, "org.apache.derby.iapi.sql.conn.LanguageConnectionFactory", var2);
      this.dvf = var3.getDataValueFactory();
      this.exFactory = (ExecutionFactory)bootServiceModule(var1, this, "org.apache.derby.iapi.sql.execute.ExecutionFactory", var2);
      this.initializeCatalogInfo();
      this.booting = true;
      if (this.dataDescriptorGenerator == null) {
         this.dataDescriptorGenerator = new DataDescriptorGenerator(this);
      }

      if (!var1) {
         this.coreInfo[1].setHeapConglomerate(this.getBootParameter(var2, "SystablesIdentifier", true));
         this.coreInfo[1].setIndexConglomerate(0, this.getBootParameter(var2, "SystablesIndex1Identifier", true));
         this.coreInfo[1].setIndexConglomerate(1, this.getBootParameter(var2, "SystablesIndex2Identifier", true));
         this.coreInfo[2].setHeapConglomerate(this.getBootParameter(var2, "SyscolumnsIdentifier", true));
         this.coreInfo[2].setIndexConglomerate(0, this.getBootParameter(var2, "SyscolumnsIndex1Identifier", true));
         this.coreInfo[2].setIndexConglomerate(1, this.getBootParameter(var2, "SyscolumnsIndex2Identifier", false));
         this.coreInfo[0].setHeapConglomerate(this.getBootParameter(var2, "SysconglomeratesIdentifier", true));
         this.coreInfo[0].setIndexConglomerate(0, this.getBootParameter(var2, "SysconglomeratesIndex1Identifier", true));
         this.coreInfo[0].setIndexConglomerate(1, this.getBootParameter(var2, "SysconglomeratesIndex2Identifier", true));
         this.coreInfo[0].setIndexConglomerate(2, this.getBootParameter(var2, "SysconglomeratesIndex3Identifier", true));
         this.coreInfo[3].setHeapConglomerate(this.getBootParameter(var2, "SysschemasIdentifier", true));
         this.coreInfo[3].setIndexConglomerate(0, this.getBootParameter(var2, "SysschemasIndex1Identifier", true));
         this.coreInfo[3].setIndexConglomerate(1, this.getBootParameter(var2, "SysschemasIndex2Identifier", true));
      }

      String var4 = var2.getProperty("derby.language.tableDescriptorCacheSize");
      this.tdCacheSize = PropertyUtil.intPropertyValue("derby.language.tableDescriptorCacheSize", var4, 0, Integer.MAX_VALUE, 64);
      var4 = var2.getProperty("derby.language.spsCacheSize");
      this.stmtCacheSize = PropertyUtil.intPropertyValue("derby.language.spsCacheSize", var4, 0, Integer.MAX_VALUE, 32);
      var4 = var2.getProperty("derby.language.sequenceGeneratorCacheSize");
      this.seqgenCacheSize = PropertyUtil.intPropertyValue("derby.language.sequenceGeneratorCacheSize", var4, 0, Integer.MAX_VALUE, 1000);
      var4 = var2.getProperty("derby.language.permissionsCacheSize");
      this.permissionsCacheSize = PropertyUtil.intPropertyValue("derby.language.permissionsCacheSize", var4, 0, Integer.MAX_VALUE, 64);
      this.indexStatsUpdateDisabled = !PropertyUtil.getSystemBoolean("derby.storage.indexStats.auto", true);
      this.indexStatsUpdateLogging = PropertyUtil.getSystemBoolean("derby.storage.indexStats.log");
      this.indexStatsUpdateTracing = PropertyUtil.getSystemProperty("derby.storage.indexStats.trace", "off");
      CacheFactory var5 = (CacheFactory)startSystemModule("org.apache.derby.iapi.services.cache.CacheFactory");
      this.OIDTdCache = var5.newCacheManager(this, "TableDescriptorOIDCache", this.tdCacheSize, this.tdCacheSize);
      this.nameTdCache = var5.newCacheManager(this, "TableDescriptorNameCache", this.tdCacheSize, this.tdCacheSize);
      if (this.stmtCacheSize > 0) {
         this.spsNameCache = var5.newCacheManager(this, "SPSNameDescriptorCache", this.stmtCacheSize, this.stmtCacheSize);
         this.spsIdHash = new Hashtable(this.stmtCacheSize);
      }

      this.sequenceGeneratorCache = var5.newCacheManager(this, "SequenceGeneratorCache", this.seqgenCacheSize, this.seqgenCacheSize);
      this.sequenceIDs = new HashMap();
      this.cacheCoordinator = new ShExLockable();
      this.af = (AccessFactory)findServiceModule(this, "org.apache.derby.iapi.store.access.AccessFactory");
      this.lockFactory = this.af.getLockFactory();
      ContextService var6 = getContextService();
      ContextManager var7 = var6.getCurrentContextManager();
      this.bootingTC = null;

      try {
         this.bootingTC = this.af.getTransaction(var7);
         this.exFactory.newExecutionContext(var7);
         DataDescriptorGenerator var8 = this.getDataDescriptorGenerator();
         String var9;
         if (var1) {
            var9 = var2.getProperty("collation", "UCS_BASIC");
            this.bootingTC.setProperty("derby.database.collation", var9, true);
         } else {
            var9 = var2.getProperty("derby.database.collation", "UCS_BASIC");
         }

         this.collationTypeOfUserSchemas = DataTypeDescriptor.getCollationType(var9);
         this.declaredGlobalTemporaryTablesSchemaDesc = this.newDeclaredGlobalTemporaryTablesSchemaDesc("SESSION");
         boolean var10 = PropertyUtil.nativeAuthenticationEnabled(var2);
         if (var1) {
            String var21 = IdUtil.getUserNameFromURLProps(var2);
            this.authorizationDatabaseOwner = IdUtil.getUserAuthorizationId(var21);
            HashSet var22 = new HashSet();
            this.dictionaryVersion = this.softwareVersion;
            this.createDictionaryTables(var2, this.bootingTC, var8);
            this.create_SYSIBM_procedures(this.bootingTC, var22);
            this.createSystemSps(this.bootingTC);
            this.create_SYSCS_procedures(this.bootingTC, var22);
            this.grantPublicAccessToSystemRoutines(var22, this.bootingTC, this.authorizationDatabaseOwner);
            this.bootingTC.setProperty("DataDictionaryVersion", this.dictionaryVersion, true);
            this.bootingTC.setProperty("CreateDataDictionaryVersion", this.dictionaryVersion, true);
            if (PropertyUtil.getSystemBoolean("derby.database.sqlAuthorization")) {
               this.bootingTC.setProperty("derby.database.sqlAuthorization", "true", true);
            }

            if (PropertyUtil.getSystemBoolean("derby.database.sqlAuthorization") || var10) {
               this.usesSqlAuthorization = true;
            }

            this.bootingTC.setProperty("derby.authentication.builtin.algorithm", this.findDefaultBuiltinAlgorithm(), false);
         } else {
            this.loadDictionaryTables(this.bootingTC, var2);
            String var11 = PropertyUtil.getDatabaseProperty(this.bootingTC, "derby.storage.indexStats.auto");
            if (var11 != null) {
               this.indexStatsUpdateDisabled = !Boolean.valueOf(var11);
            }

            String var12 = PropertyUtil.getDatabaseProperty(this.bootingTC, "derby.storage.indexStats.log");
            if (var12 != null) {
               this.indexStatsUpdateLogging = Boolean.valueOf(var12);
            }

            String var13 = PropertyUtil.getDatabaseProperty(this.bootingTC, "derby.storage.indexStats.trace");
            if (var13 != null) {
               if (!var13.equalsIgnoreCase("off") && !var13.equalsIgnoreCase("log") && !var13.equalsIgnoreCase("stdout") && !var13.equalsIgnoreCase("both")) {
                  this.indexStatsUpdateTracing = "off";
               } else {
                  this.indexStatsUpdateTracing = var13;
               }
            }

            String var14 = PropertyUtil.getDatabaseProperty(this.bootingTC, "derby.database.sqlAuthorization");
            if (Boolean.valueOf(var2.getProperty("softUpgradeNoFeatureCheck"))) {
               if (this.dictionaryVersion.majorVersionNumber >= 140) {
                  this.usesSqlAuthorization = Boolean.valueOf(var14) || var10;
               }
            } else if (Boolean.valueOf(var14) || var10) {
               this.checkVersion(140, "sqlAuthorization");
               this.usesSqlAuthorization = true;
            }
         }

         this.bootingTC.commit();
         var7.getContext("ExecutionContext").popMe();
      } finally {
         if (this.bootingTC != null) {
            this.bootingTC.destroy();
            this.bootingTC = null;
         }

      }

      this.setDependencyManager();
      this.booting = false;
   }

   private String findDefaultBuiltinAlgorithm() {
      try {
         MessageDigest.getInstance("SHA-256");
         return "SHA-256";
      } catch (NoSuchAlgorithmException var2) {
         return "SHA-1";
      }
   }

   private CacheManager getPermissionsCache() throws StandardException {
      if (this.permissionsCache == null) {
         CacheFactory var1 = (CacheFactory)startSystemModule("org.apache.derby.iapi.services.cache.CacheFactory");
         LanguageConnectionContext var2 = getLCC();
         TransactionController var3 = var2.getTransactionExecute();
         this.permissionsCacheSize = PropertyUtil.getServiceInt(var3, "derby.language.permissionsCacheSize", 40, Integer.MAX_VALUE, this.permissionsCacheSize);
         this.permissionsCache = var1.newCacheManager(this, "PermissionsCache", this.permissionsCacheSize, this.permissionsCacheSize);
      }

      return this.permissionsCache;
   }

   protected void setDependencyManager() {
      this.dmgr = new BasicDependencyManager(this);
   }

   public DependencyManager getDependencyManager() {
      return this.dmgr;
   }

   public void stop() {
      if (this.indexRefresher != null) {
         this.indexRefresher.stop();
      }

   }

   public Cacheable newCacheable(CacheManager var1) {
      if (var1 == this.OIDTdCache) {
         return new OIDTDCacheable(this);
      } else if (var1 == this.nameTdCache) {
         return new NameTDCacheable(this);
      } else if (var1 == this.permissionsCache) {
         return new PermissionsCacheable(this);
      } else {
         return (Cacheable)(var1 == this.sequenceGeneratorCache ? new SequenceUpdater.SyssequenceUpdater(this) : new SPSNameCacheable(this));
      }
   }

   public int startReading(LanguageConnectionContext var1) throws StandardException {
      int var2 = var1.incrementBindCount();
      boolean var4 = false;

      int var3;
      do {
         if (var4) {
            try {
               this.lockFactory.zeroDurationlockObject(var1.getTransactionExecute().getLockSpace(), this.cacheCoordinator, ShExQual.SH, -1);
            } catch (StandardException var9) {
               var1.decrementBindCount();
               throw var9;
            }

            var4 = false;
         }

         synchronized(this) {
            var3 = this.getCacheMode();
            if (var2 == 1) {
               if (var3 == 0) {
                  boolean var6 = false;

                  try {
                     CompatibilitySpace var7 = var1.getTransactionExecute().getLockSpace();
                     var6 = this.lockFactory.lockObject(var7, var7.getOwner(), this.cacheCoordinator, ShExQual.SH, 0);
                  } catch (StandardException var10) {
                     var1.decrementBindCount();
                     throw var10;
                  }

                  if (!var6) {
                     var4 = true;
                  }
               } else {
                  ++this.readersInDDLMode;
               }
            }
         }
      } while(var4);

      return var3;
   }

   public void doneReading(int var1, LanguageConnectionContext var2) throws StandardException {
      int var3 = var2.decrementBindCount();
      synchronized(this) {
         if (var3 == 0) {
            if (var1 == 0) {
               if (var2.getStatementContext() != null && var2.getStatementContext().inUse()) {
                  CompatibilitySpace var5 = var2.getTransactionExecute().getLockSpace();
                  this.lockFactory.unlock(var5, var5.getOwner(), this.cacheCoordinator, ShExQual.SH);
               }
            } else {
               --this.readersInDDLMode;
               if (this.ddlUsers == 0 && this.readersInDDLMode == 0) {
                  this.clearCaches(false);
                  this.setCacheMode(0);
               }
            }
         }

      }
   }

   public void startWriting(LanguageConnectionContext var1) throws StandardException {
      boolean var2 = true;
      if (var1.getBindCount() != 0) {
         throw StandardException.newException("XCL21.S", new Object[0]);
      } else {
         if (!var1.dataDictionaryInWriteMode()) {
            for(int var3 = 0; var2; ++var3) {
               if (var3 > 4 && this.getCacheMode() == 0) {
                  this.lockFactory.zeroDurationlockObject(var1.getTransactionExecute().getLockSpace(), this.cacheCoordinator, ShExQual.EX, -2);
                  var3 = 1;
               }

               if (var3 > 0) {
                  try {
                     Thread.sleep((long)(Math.random() * (double)1131.0F % (double)20.0F));
                  } catch (InterruptedException var7) {
                     throw StandardException.interrupt(var7);
                  }
               }

               synchronized(this) {
                  if (this.getCacheMode() == 0) {
                     boolean var5 = this.lockFactory.zeroDurationlockObject(var1.getTransactionExecute().getLockSpace(), this.cacheCoordinator, ShExQual.EX, 0);
                     if (!var5) {
                        continue;
                     }

                     this.setCacheMode(1);
                     this.clearCaches(false);
                  }

                  ++this.ddlUsers;
               }

               var1.setDataDictionaryWriteMode();
               var2 = false;
            }
         }

      }
   }

   public void transactionFinished() throws StandardException {
      synchronized(this) {
         --this.ddlUsers;
         if (this.ddlUsers == 0 && this.readersInDDLMode == 0) {
            this.clearCaches();
            this.setCacheMode(0);
         }

      }
   }

   public int getCacheMode() {
      return this.cacheMode;
   }

   private void setCacheMode(int var1) {
      this.cacheMode = var1;
   }

   public DataDescriptorGenerator getDataDescriptorGenerator() {
      return this.dataDescriptorGenerator;
   }

   public String getAuthorizationDatabaseOwner() {
      return this.authorizationDatabaseOwner;
   }

   public boolean usesSqlAuthorization() {
      return this.usesSqlAuthorization;
   }

   public int getCollationTypeOfSystemSchemas() {
      return this.collationTypeOfSystemSchemas;
   }

   public int getCollationTypeOfUserSchemas() {
      return this.collationTypeOfUserSchemas;
   }

   public DataValueFactory getDataValueFactory() {
      return this.dvf;
   }

   public ExecutionFactory getExecutionFactory() {
      return this.exFactory;
   }

   private void getBuiltinSystemSchemas() {
      if (this.systemSchemaDesc == null) {
         this.systemSchemaDesc = this.newSystemSchemaDesc("SYS", "8000000d-00d0-fd77-3ed8-000a0a0b1900");
         this.sysIBMSchemaDesc = this.newSystemSchemaDesc("SYSIBM", "c013800d-00f8-5b53-28a9-00000019ed88");
         this.systemUtilSchemaDesc = this.newSystemSchemaDesc("SYSCS_UTIL", "c013800d-00fb-2649-07ec-000000134f30");
      }
   }

   public PasswordHasher makePasswordHasher(Dictionary var1) throws StandardException {
      boolean var2 = this.checkVersion(180, (String)null);
      boolean var3 = this.checkVersion(210, (String)null);
      if (!var2) {
         return null;
      } else {
         String var4 = (String)PropertyUtil.getPropertyFromSet(var1, "derby.authentication.builtin.algorithm");
         if (var4 == null) {
            return null;
         } else {
            byte[] var5 = null;
            int var6 = 1;
            if (var4.length() > 0 && var3) {
               var5 = this.generateRandomSalt(var1);
               var6 = this.getIntProperty(var1, "derby.authentication.builtin.iterations", 1000, 1, Integer.MAX_VALUE);
            }

            return new PasswordHasher(var4, var5, var6);
         }
      }
   }

   private byte[] generateRandomSalt(Dictionary var1) {
      int var2 = this.getIntProperty(var1, "derby.authentication.builtin.saltLength", 16, 0, Integer.MAX_VALUE);
      SecureRandom var3 = new SecureRandom();
      byte[] var4 = new byte[var2];
      var3.nextBytes(var4);
      return var4;
   }

   private int getIntProperty(Dictionary var1, String var2, int var3, int var4, int var5) {
      String var6 = (String)PropertyUtil.getPropertyFromSet(var1, var2);
      if (var6 != null) {
         try {
            int var7 = Integer.parseInt(var6);
            if (var7 >= var4 && var7 <= var5) {
               return var7;
            }
         } catch (NumberFormatException var8) {
         }
      }

      return var3;
   }

   public SchemaDescriptor getSystemSchemaDescriptor() throws StandardException {
      return this.systemSchemaDesc;
   }

   public SchemaDescriptor getSystemUtilSchemaDescriptor() throws StandardException {
      return this.systemUtilSchemaDesc;
   }

   public SchemaDescriptor getSysIBMSchemaDescriptor() throws StandardException {
      return this.sysIBMSchemaDesc;
   }

   public SchemaDescriptor getDeclaredGlobalTemporaryTablesSchemaDescriptor() throws StandardException {
      return this.declaredGlobalTemporaryTablesSchemaDesc;
   }

   public boolean isSystemSchemaName(String var1) throws StandardException {
      boolean var2 = false;
      int var3 = systemSchemaNames.length - 1;

      while(var3 >= 0 && !(var2 = systemSchemaNames[var3--].equals(var1))) {
      }

      return var2;
   }

   public SchemaDescriptor getSchemaDescriptor(String var1, TransactionController var2, boolean var3) throws StandardException {
      if (var2 == null) {
         var2 = this.getTransactionCompile();
      }

      if (this.getSystemSchemaDescriptor().getSchemaName().equals(var1)) {
         return this.getSystemSchemaDescriptor();
      } else if (this.getSysIBMSchemaDescriptor().getSchemaName().equals(var1) && this.dictionaryVersion.checkVersion(100, (String)null)) {
         return this.getSysIBMSchemaDescriptor();
      } else {
         SchemaDescriptor var4 = this.locateSchemaRow(var1, var2);
         if (var4 == null && this.getDeclaredGlobalTemporaryTablesSchemaDescriptor().getSchemaName().equals(var1)) {
            return this.getDeclaredGlobalTemporaryTablesSchemaDescriptor();
         } else if (var4 == null && var3) {
            throw StandardException.newException("42Y07", new Object[]{var1});
         } else {
            return var4;
         }
      }
   }

   private SchemaDescriptor locateSchemaRow(UUID var1, TransactionController var2) throws StandardException {
      return this.locateSchemaRowBody(var1, 4, var2);
   }

   private SchemaDescriptor locateSchemaRow(UUID var1, int var2, TransactionController var3) throws StandardException {
      return this.locateSchemaRowBody(var1, var2, var3);
   }

   private SchemaDescriptor locateSchemaRowBody(UUID var1, int var2, TransactionController var3) throws StandardException {
      TabInfoImpl var5 = this.coreInfo[3];
      SQLChar var4 = getIDValueAsCHAR(var1);
      ExecIndexRow var6 = this.exFactory.getIndexableRow(1);
      var6.setColumn(1, var4);
      return (SchemaDescriptor)this.getDescriptorViaIndex(1, var6, (ScanQualifier[][])null, var5, (TupleDescriptor)null, (List)null, SchemaDescriptor.class, false, var2, var3);
   }

   private SchemaDescriptor locateSchemaRow(String var1, TransactionController var2) throws StandardException {
      TabInfoImpl var4 = this.coreInfo[3];
      SQLVarchar var3 = new SQLVarchar(var1);
      ExecIndexRow var5 = this.exFactory.getIndexableRow(1);
      var5.setColumn(1, var3);
      return (SchemaDescriptor)this.getDescriptorViaIndex(0, var5, (ScanQualifier[][])null, var4, (TupleDescriptor)null, (List)null, SchemaDescriptor.class, false, 4, var2);
   }

   public SchemaDescriptor getSchemaDescriptor(UUID var1, TransactionController var2) throws StandardException {
      return this.getSchemaDescriptorBody(var1, 4, var2);
   }

   public SchemaDescriptor getSchemaDescriptor(UUID var1, int var2, TransactionController var3) throws StandardException {
      return this.getSchemaDescriptorBody(var1, var2, var3);
   }

   private SchemaDescriptor getSchemaDescriptorBody(UUID var1, int var2, TransactionController var3) throws StandardException {
      if (var3 == null) {
         var3 = this.getTransactionCompile();
      }

      if (var1 != null) {
         if (this.getSystemSchemaDescriptor().getUUID().equals(var1)) {
            return this.getSystemSchemaDescriptor();
         }

         if (this.getSysIBMSchemaDescriptor().getUUID().equals(var1)) {
            return this.getSysIBMSchemaDescriptor();
         }
      }

      if (!this.booting) {
         LanguageConnectionContext var4 = getLCC();
         if (var4 != null) {
            SchemaDescriptor var5 = var4.getDefaultSchema();
            if (var5 != null && (var1 == null || var1.equals(var5.getUUID()))) {
               return var5;
            }
         }
      }

      return this.locateSchemaRow(var1, var2, var3);
   }

   public boolean existsSchemaOwnedBy(String var1, TransactionController var2) throws StandardException {
      TabInfoImpl var3 = this.coreInfo[3];
      SYSSCHEMASRowFactory var4 = (SYSSCHEMASRowFactory)var3.getCatalogRowFactory();
      ConglomerateController var5 = var2.openConglomerate(var3.getHeapConglomerate(), false, 0, 6, 4);
      SQLVarchar var6 = new SQLVarchar(var1);
      ScanQualifier[][] var7 = this.exFactory.getScanQualifier(1);
      var7[0][0].setQualifier(2, var6, 2, false, false, false);
      ScanController var8 = var2.openScan(var3.getHeapConglomerate(), false, 0, 6, 4, (FormatableBitSet)null, (DataValueDescriptor[])null, 0, var7, (DataValueDescriptor[])null, 0);
      boolean var9 = false;

      try {
         ExecRow var10 = var4.makeEmptyRow();
         if (var8.fetchNext(var10.getRowArray())) {
            var9 = true;
         }
      } finally {
         if (var8 != null) {
            var8.close();
         }

         if (var5 != null) {
            var5.close();
         }

      }

      return var9;
   }

   public void addDescriptor(TupleDescriptor var1, TupleDescriptor var2, int var3, boolean var4, TransactionController var5) throws StandardException {
      TabInfoImpl var6 = var3 < 4 ? this.coreInfo[var3] : this.getNonCoreTI(var3);
      ExecRow var7 = var6.getCatalogRowFactory().makeRow(var1, var2);
      int var8 = var6.insertRow(var7, var5);
      if (!var4 && var8 != -1) {
         throw this.duplicateDescriptorException(var1, var2);
      }
   }

   private StandardException duplicateDescriptorException(TupleDescriptor var1, TupleDescriptor var2) {
      return var2 != null ? StandardException.newException("X0Y32.S", new Object[]{var1.getDescriptorType(), var1.getDescriptorName(), var2.getDescriptorType(), var2.getDescriptorName()}) : StandardException.newException("X0Y68.S", new Object[]{var1.getDescriptorType(), var1.getDescriptorName()});
   }

   public void addDescriptorArray(TupleDescriptor[] var1, TupleDescriptor var2, int var3, boolean var4, TransactionController var5) throws StandardException {
      TabInfoImpl var6 = var3 < 4 ? this.coreInfo[var3] : this.getNonCoreTI(var3);
      CatalogRowFactory var7 = var6.getCatalogRowFactory();
      ExecRow[] var8 = new ExecRow[var1.length];

      for(int var9 = 0; var9 < var1.length; ++var9) {
         ExecRow var10 = var7.makeRow(var1[var9], var2);
         var8[var9] = var10;
      }

      int var11 = var6.insertRowList(var8, var5);
      if (!var4 && var11 != -1) {
         throw this.duplicateDescriptorException(var1[var11], var2);
      }
   }

   public void dropRoleGrant(String var1, String var2, String var3, TransactionController var4) throws StandardException {
      TabInfoImpl var8 = this.getNonCoreTI(19);
      SQLVarchar var5 = new SQLVarchar(var1);
      SQLVarchar var6 = new SQLVarchar(var2);
      SQLVarchar var7 = new SQLVarchar(var3);
      ExecIndexRow var9 = this.exFactory.getIndexableRow(3);
      var9.setColumn(1, var5);
      var9.setColumn(2, var6);
      var9.setColumn(3, var7);
      var8.deleteRow(var4, var9, 0);
   }

   public void dropSchemaDescriptor(String var1, TransactionController var2) throws StandardException {
      ExecIndexRow var3 = null;
      TabInfoImpl var5 = this.coreInfo[3];
      SQLVarchar var4 = new SQLVarchar(var1);
      var3 = this.exFactory.getIndexableRow(1);
      var3.setColumn(1, var4);
      var5.deleteRow(var2, var3, 0);
   }

   public TableDescriptor getTableDescriptor(String var1, SchemaDescriptor var2, TransactionController var3) throws StandardException {
      TableDescriptor var4 = null;
      SchemaDescriptor var5 = var2 == null ? this.getSystemSchemaDescriptor() : var2;
      UUID var6 = var5.getUUID();
      if ("SYSCS_DIAG".equals(var5.getSchemaName())) {
         TableDescriptor var7 = new TableDescriptor(this, var1, var5, 5, 'R');
         if (this.getVTIClass(var7, false) != null) {
            return var7;
         }
      }

      TableKey var9 = new TableKey(var6, var1);
      if (this.getCacheMode() == 0) {
         NameTDCacheable var8 = (NameTDCacheable)this.nameTdCache.find(var9);
         if (var8 != null) {
            var4 = var8.getTableDescriptor();
            var4.setReferencedColumnMap((FormatableBitSet)null);
            this.nameTdCache.release(var8);
         }

         return var4;
      } else {
         return this.getTableDescriptorIndex1Scan(var1, var6.toString());
      }
   }

   private TableDescriptor getTableDescriptorIndex1Scan(String var1, String var2) throws StandardException {
      TabInfoImpl var6 = this.coreInfo[1];
      SQLVarchar var4 = new SQLVarchar(var1);
      SQLChar var3 = new SQLChar(var2);
      ExecIndexRow var7 = this.exFactory.getIndexableRow(2);
      var7.setColumn(1, var4);
      var7.setColumn(2, var3);
      TableDescriptor var5 = (TableDescriptor)this.getDescriptorViaIndex(0, var7, (ScanQualifier[][])null, var6, (TupleDescriptor)null, (List)null, TableDescriptor.class, false);
      return this.finishTableDescriptor(var5);
   }

   TableDescriptor getUncachedTableDescriptor(TableKey var1) throws StandardException {
      return this.getTableDescriptorIndex1Scan(var1.getTableName(), var1.getSchemaId().toString());
   }

   public TableDescriptor getTableDescriptor(UUID var1) throws StandardException {
      TableDescriptor var3 = null;
      if (this.getCacheMode() == 0) {
         OIDTDCacheable var2 = (OIDTDCacheable)this.OIDTdCache.find(var1);
         if (var2 != null) {
            var3 = var2.getTableDescriptor();
            var3.setReferencedColumnMap((FormatableBitSet)null);
            this.OIDTdCache.release(var2);
         }

         return var3;
      } else {
         return this.getTableDescriptorIndex2Scan(var1.toString());
      }
   }

   protected TableDescriptor getUncachedTableDescriptor(UUID var1) throws StandardException {
      return this.getTableDescriptorIndex2Scan(var1.toString());
   }

   private TableDescriptor getTableDescriptorIndex2Scan(String var1) throws StandardException {
      TabInfoImpl var4 = this.coreInfo[1];
      SQLChar var2 = new SQLChar(var1);
      ExecIndexRow var5 = this.exFactory.getIndexableRow(1);
      var5.setColumn(1, var2);
      TableDescriptor var3 = (TableDescriptor)this.getDescriptorViaIndex(1, var5, (ScanQualifier[][])null, var4, (TupleDescriptor)null, (List)null, TableDescriptor.class, false);
      return this.finishTableDescriptor(var3);
   }

   private TableDescriptor finishTableDescriptor(TableDescriptor var1) throws StandardException {
      if (var1 != null) {
         synchronized(var1) {
            this.getColumnDescriptorsScan(var1);
            this.getConglomerateDescriptorsScan(var1);
         }
      }

      return var1;
   }

   public boolean isSchemaEmpty(SchemaDescriptor var1) throws StandardException {
      TransactionController var3 = this.getTransactionCompile();
      SQLChar var2 = getIDValueAsCHAR(var1.getUUID());
      if (this.isSchemaReferenced(var3, this.coreInfo[1], 0, 2, var2)) {
         return false;
      } else if (this.isSchemaReferenced(var3, this.getNonCoreTI(4), 1, 2, var2)) {
         return false;
      } else if (this.isSchemaReferenced(var3, this.getNonCoreTI(11), 1, 2, var2)) {
         return false;
      } else if (this.isSchemaReferenced(var3, this.getNonCoreTI(13), 1, 2, var2)) {
         return false;
      } else if (this.isSchemaReferenced(var3, this.getNonCoreTI(7), 0, 1, var2)) {
         return false;
      } else {
         return this.dictionaryVersion.majorVersionNumber < 180 || !this.isSchemaReferenced(var3, this.getNonCoreTI(20), 1, 1, var2);
      }
   }

   protected boolean isSchemaReferenced(TransactionController var1, TabInfoImpl var2, int var3, int var4, DataValueDescriptor var5) throws StandardException {
      ConglomerateController var6 = null;
      ScanController var7 = null;
      FormatableBitSet var9 = new FormatableBitSet(var4);
      var9.set(var4 - 1);
      ScanQualifier[][] var10 = this.exFactory.getScanQualifier(1);
      var10[0][0].setQualifier(var4 - 1, var5, 2, false, false, false);

      boolean var8;
      try {
         var6 = var1.openConglomerate(var2.getHeapConglomerate(), false, 0, 6, 4);
         var7 = var1.openScan(var2.getIndexConglomerate(var3), false, 0, 6, 4, var9, (DataValueDescriptor[])null, 1, var10, (DataValueDescriptor[])null, -1);
         var8 = var7.next();
      } finally {
         if (var7 != null) {
            var7.close();
         }

         if (var6 != null) {
            var6.close();
         }

      }

      return var8;
   }

   public void dropTableDescriptor(TableDescriptor var1, SchemaDescriptor var2, TransactionController var3) throws StandardException {
      ExecIndexRow var4 = null;
      TabInfoImpl var7 = this.coreInfo[1];
      SQLVarchar var6 = new SQLVarchar(var1.getName());
      SQLChar var5 = getIDValueAsCHAR(var2.getUUID());
      var4 = this.exFactory.getIndexableRow(2);
      var4.setColumn(1, var6);
      var4.setColumn(2, var5);
      var7.deleteRow(var3, var4, 0);
   }

   public void updateLockGranularity(TableDescriptor var1, SchemaDescriptor var2, char var3, TransactionController var4) throws StandardException {
      TabInfoImpl var8 = this.coreInfo[1];
      SYSTABLESRowFactory var9 = (SYSTABLESRowFactory)var8.getCatalogRowFactory();
      SQLVarchar var7 = new SQLVarchar(var1.getName());
      SQLChar var6 = getIDValueAsCHAR(var2.getUUID());
      ExecIndexRow var10 = this.exFactory.getIndexableRow(2);
      var10.setColumn(1, var7);
      var10.setColumn(2, var6);
      ExecRow var5 = var9.makeRow(var1, var2);
      boolean[] var11 = new boolean[2];

      for(int var12 = 0; var12 < 2; ++var12) {
         var11[var12] = false;
      }

      var8.updateRow(var10, (ExecRow)var5, 0, var11, (int[])null, var4);
   }

   void upgradeCLOBGETSUBSTRING_10_6(TransactionController var1) throws StandardException {
      TabInfoImpl var2 = this.getNonCoreTI(7);
      ExecIndexRow var3 = this.exFactory.getIndexableRow(3);
      SQLVarchar var4 = new SQLVarchar("CLOBGETSUBSTRING");
      SQLChar var5 = new SQLChar(new String(new char[]{'F'}));
      var3.setColumn(1, new SQLChar("c013800d-00f8-5b53-28a9-00000019ed88"));
      var3.setColumn(2, var4);
      var3.setColumn(3, var5);
      AliasDescriptor var6 = (AliasDescriptor)this.getDescriptorViaIndex(0, var3, (ScanQualifier[][])null, var2, (TupleDescriptor)null, (List)null, AliasDescriptor.class, true, 4, var1);
      RoutineAliasInfo var7 = (RoutineAliasInfo)var6.getAliasInfo();
      TypeDescriptor var8 = DataTypeDescriptor.getCatalogType(12, 10890);
      RoutineAliasInfo var9 = new RoutineAliasInfo(var7.getMethodName(), var7.getParameterCount(), var7.getParameterNames(), var7.getParameterTypes(), var7.getParameterModes(), var7.getMaxDynamicResultSets(), var7.getParameterStyle(), var7.getSQLAllowed(), var7.isDeterministic(), var7.hasVarargs(), var7.hasDefinersRights(), var7.calledOnNullInput(), var8);
      AliasDescriptor var10 = new AliasDescriptor(this, var6.getUUID(), var6.getObjectName(), var6.getSchemaUUID(), var6.getJavaClassName(), var6.getAliasType(), var6.getNameSpace(), var6.getSystemAlias(), var9, var6.getSpecificName());
      ExecRow var11 = var2.getCatalogRowFactory().makeRow(var10, (TupleDescriptor)null);
      var2.updateRow(var3, (ExecRow)var11, 0, new boolean[]{false, false, false}, (int[])null, var1);
   }

   void upgradeSYSROUTINEPERMS_10_6(TransactionController var1) throws StandardException {
      TabInfoImpl var2 = this.getNonCoreTI(7);
      ExecIndexRow var3 = this.exFactory.getIndexableRow(3);
      SQLVarchar var4 = new SQLVarchar("SYSCS_INPLACE_COMPRESS_TABLE");
      SQLChar var5 = new SQLChar(new String(new char[]{'P'}));
      var3.setColumn(1, new SQLChar("c013800d-00fb-2649-07ec-000000134f30"));
      var3.setColumn(2, var4);
      var3.setColumn(3, var5);
      AliasDescriptor var6 = (AliasDescriptor)this.getDescriptorViaIndex(0, var3, (ScanQualifier[][])null, var2, (TupleDescriptor)null, (List)null, AliasDescriptor.class, true, 4, var1);
      UUID var7 = var6.getUUID();
      TabInfoImpl var8 = this.getNonCoreTI(18);
      ExecIndexRow var9 = this.exFactory.getIndexableRow(3);
      var9.setColumn(1, new SQLVarchar("PUBLIC"));
      var9.setColumn(2, new SQLChar(var7.toString()));
      var9.setColumn(3, new SQLVarchar((String)null));
      var8.deleteRow(var1, var9, 0);
   }

   public ColumnDescriptor getColumnDescriptorByDefaultId(UUID var1) throws StandardException {
      TabInfoImpl var3 = this.coreInfo[2];
      SQLChar var2 = getIDValueAsCHAR(var1);
      ExecIndexRow var4 = this.exFactory.getIndexableRow(1);
      var4.setColumn(1, var2);
      return (ColumnDescriptor)this.getDescriptorViaIndex(1, var4, (ScanQualifier[][])null, var3, (DefaultDescriptor)null, (List)null, ColumnDescriptor.class, false);
   }

   private void getColumnDescriptorsScan(TableDescriptor var1) throws StandardException {
      this.getColumnDescriptorsScan(var1.getUUID(), var1.getColumnDescriptorList(), var1);
   }

   private void getColumnDescriptorsScan(UUID var1, ColumnDescriptorList var2, TupleDescriptor var3) throws StandardException {
      ColumnDescriptorList var5 = new ColumnDescriptorList();
      Object var6 = null;
      TabInfoImpl var7 = this.coreInfo[2];
      SQLChar var11 = getIDValueAsCHAR(var1);
      ExecIndexRow var8 = this.exFactory.getIndexableRow(1);
      var8.setColumn(1, var11);
      this.getDescriptorViaIndex(0, var8, (ScanQualifier[][])null, var7, var3, var2, ColumnDescriptor.class, false);
      int var9 = var2.size();

      for(int var10 = 0; var10 < var9; ++var10) {
         var5.add((ColumnDescriptor)var2.get(var10));
      }

      for(int var12 = 0; var12 < var9; ++var12) {
         ColumnDescriptor var4 = var5.elementAt(var12);
         var2.set(var4.getPosition() - 1, var4);
      }

   }

   public void dropColumnDescriptor(UUID var1, String var2, TransactionController var3) throws StandardException {
      SQLChar var5 = getIDValueAsCHAR(var1);
      SQLVarchar var4 = new SQLVarchar(var2);
      ExecIndexRow var6 = this.exFactory.getIndexableRow(2);
      var6.setColumn(1, var5);
      var6.setColumn(2, var4);
      this.dropColumnDescriptorCore(var3, var6);
   }

   public void dropAllColumnDescriptors(UUID var1, TransactionController var2) throws StandardException {
      SQLChar var3 = getIDValueAsCHAR(var1);
      ExecIndexRow var4 = this.exFactory.getIndexableRow(1);
      var4.setColumn(1, var3);
      this.dropColumnDescriptorCore(var2, var4);
   }

   public void dropAllTableAndColPermDescriptors(UUID var1, TransactionController var2) throws StandardException {
      if (this.usesSqlAuthorization) {
         SQLChar var3 = getIDValueAsCHAR(var1);
         ExecIndexRow var4 = this.exFactory.getIndexableRow(1);
         var4.setColumn(1, var3);
         this.dropTablePermDescriptor(var2, var4);
         this.dropColumnPermDescriptor(var2, var4);
      }
   }

   public void updateSYSCOLPERMSforAddColumnToUserTable(UUID var1, TransactionController var2) throws StandardException {
      this.rewriteSYSCOLPERMSforAlterTable(var1, var2, (ColumnDescriptor)null);
   }

   public void updateSYSCOLPERMSforDropColumn(UUID var1, TransactionController var2, ColumnDescriptor var3) throws StandardException {
      this.rewriteSYSCOLPERMSforAlterTable(var1, var2, var3);
   }

   private void rewriteSYSCOLPERMSforAlterTable(UUID var1, TransactionController var2, ColumnDescriptor var3) throws StandardException {
      if (this.usesSqlAuthorization) {
         SQLChar var5 = getIDValueAsCHAR(var1);
         TabInfoImpl var6 = this.getNonCoreTI(17);
         SYSCOLPERMSRowFactory var7 = (SYSCOLPERMSRowFactory)var6.getCatalogRowFactory();
         ExecIndexRow var8 = this.exFactory.getIndexableRow(1);
         var8.setColumn(1, var5);
         List var4 = newSList();
         this.getDescriptorViaIndex(2, var8, (ScanQualifier[][])null, var6, (TupleDescriptor)null, var4, ColPermsDescriptor.class, false);
         boolean[] var11 = new boolean[3];
         int[] var12 = new int[]{6};

         for(ColPermsDescriptor var14 : var4) {
            this.removePermEntryInCache(var14);
            ExecIndexRow var10 = var7.buildIndexKeyRow(1, var14);
            ExecRow var9 = var6.getRow(var2, var10, 1);
            FormatableBitSet var15 = (FormatableBitSet)var9.getColumn(6).getObject();
            if (var3 == null) {
               int var18 = var15.getLength();
               var15.grow(var18 + 1);
            } else {
               FormatableBitSet var16 = new FormatableBitSet(var15);
               var16.shrink(var15.getLength() - 1);

               for(int var17 = var3.getPosition() - 1; var17 < var16.getLength(); ++var17) {
                  if (var15.isSet(var17 + 1)) {
                     var16.set(var17);
                  } else {
                     var16.clear(var17);
                  }
               }

               var15 = var16;
            }

            var9.setColumn(6, new UserType(var15));
            var6.updateRow(var10, (ExecRow)var9, 1, var11, var12, var2);
         }

      }
   }

   private void removePermEntryInCache(PermissionsDescriptor var1) throws StandardException {
      Cacheable var2 = this.getPermissionsCache().findCached(var1);
      if (var2 != null) {
         this.getPermissionsCache().remove(var2);
      }

   }

   public void dropAllRoutinePermDescriptors(UUID var1, TransactionController var2) throws StandardException {
      TabInfoImpl var3 = this.getNonCoreTI(18);
      SYSROUTINEPERMSRowFactory var4 = (SYSROUTINEPERMSRowFactory)var3.getCatalogRowFactory();
      if (this.usesSqlAuthorization) {
         SQLChar var5 = getIDValueAsCHAR(var1);
         ExecIndexRow var8 = this.exFactory.getIndexableRow(1);
         var8.setColumn(1, var5);

         ExecRow var6;
         while((var6 = var3.getRow(var2, var8, 2)) != null) {
            PermissionsDescriptor var7 = (PermissionsDescriptor)var4.buildDescriptor(var6, (TupleDescriptor)null, this);
            this.removePermEntryInCache(var7);
            ExecIndexRow var9 = var4.buildIndexKeyRow(1, var7);
            var3.deleteRow(var2, var9, 1);
         }

      }
   }

   public void dropRoleGrantsByGrantee(String var1, TransactionController var2) throws StandardException {
      TabInfoImpl var3 = this.getNonCoreTI(19);
      SYSROLESRowFactory var4 = (SYSROLESRowFactory)var3.getCatalogRowFactory();
      this.visitRoleGrants(var3, var4, 2, var1, var2, 0);
   }

   private boolean existsRoleGrantByGrantee(String var1, TransactionController var2) throws StandardException {
      TabInfoImpl var3 = this.getNonCoreTI(19);
      SYSROLESRowFactory var4 = (SYSROLESRowFactory)var3.getCatalogRowFactory();
      return this.visitRoleGrants(var3, var4, 2, var1, var2, 1);
   }

   public void dropRoleGrantsByName(String var1, TransactionController var2) throws StandardException {
      TabInfoImpl var3 = this.getNonCoreTI(19);
      SYSROLESRowFactory var4 = (SYSROLESRowFactory)var3.getCatalogRowFactory();
      this.visitRoleGrants(var3, var4, 1, var1, var2, 0);
   }

   private boolean visitRoleGrants(TabInfoImpl var1, SYSROLESRowFactory var2, int var3, String var4, TransactionController var5, int var6) throws StandardException {
      ConglomerateController var7 = var5.openConglomerate(var1.getHeapConglomerate(), false, 0, 6, 4);
      SQLVarchar var8 = new SQLVarchar(var4);
      ScanQualifier[][] var9 = this.exFactory.getScanQualifier(1);
      var9[0][0].setQualifier(var3 - 1, var8, 2, false, false, false);
      ScanController var10 = var5.openScan(var1.getIndexConglomerate(0), false, 0, 6, 4, (FormatableBitSet)null, (DataValueDescriptor[])null, 0, var9, (DataValueDescriptor[])null, 0);

      try {
         ExecRow var11 = var2.makeEmptyRow();
         ExecIndexRow var12 = getIndexRowFromHeapRow(var1.getIndexRowGenerator(0), var7.newRowLocationTemplate(), var11);

         while(var10.fetchNext(var12.getRowArray())) {
            if (var6 == 1) {
               boolean var13 = true;
               return var13;
            }

            if (var6 == 0) {
               var1.deleteRow(var5, var12, 0);
            }
         }

         return false;
      } finally {
         if (var10 != null) {
            var10.close();
         }

         if (var7 != null) {
            var7.close();
         }

      }
   }

   HashMap getRoleGrantGraph(TransactionController var1, boolean var2) throws StandardException {
      HashMap var3 = new HashMap();
      TabInfoImpl var4 = this.getNonCoreTI(19);
      SYSROLESRowFactory var5 = (SYSROLESRowFactory)var4.getCatalogRowFactory();
      SQLVarchar var6 = new SQLVarchar("N");
      ScanQualifier[][] var7 = this.exFactory.getScanQualifier(1);
      var7[0][0].setQualifier(5, var6, 2, false, false, false);
      ScanController var8 = var1.openScan(var4.getHeapConglomerate(), false, 0, 6, 4, (FormatableBitSet)null, (DataValueDescriptor[])null, 0, var7, (DataValueDescriptor[])null, 0);
      ExecRow var9 = var5.makeEmptyRow();

      while(var8.fetchNext(var9.getRowArray())) {
         RoleGrantDescriptor var10 = (RoleGrantDescriptor)var5.buildDescriptor(var9, (TupleDescriptor)null, this);
         RoleGrantDescriptor var11 = this.getRoleDefinitionDescriptor(var10.getGrantee());
         if (var11 != null) {
            String var12;
            if (var2) {
               var12 = var11.getRoleName();
            } else {
               var12 = var10.getRoleName();
            }

            Object var13 = (List)var3.get(var12);
            if (var13 == null) {
               var13 = new LinkedList();
            }

            ((List)var13).add(var10);
            var3.put(var12, var13);
         }
      }

      var8.close();
      return var3;
   }

   public RoleClosureIterator createRoleClosureIterator(TransactionController var1, String var2, boolean var3) throws StandardException {
      return new RoleClosureIteratorImpl(var2, var3, this, var1);
   }

   public void dropAllPermsByGrantee(String var1, TransactionController var2) throws StandardException {
      this.dropPermsByGrantee(var1, var2, 16, 0, 1);
      this.dropPermsByGrantee(var1, var2, 17, 0, 1);
      this.dropPermsByGrantee(var1, var2, 18, 0, 1);
   }

   private void dropPermsByGrantee(String var1, TransactionController var2, int var3, int var4, int var5) throws StandardException {
      this.visitPermsByGrantee(var1, var2, var3, var4, var5, 0);
   }

   private boolean existsPermByGrantee(String var1, TransactionController var2, int var3, int var4, int var5) throws StandardException {
      return this.visitPermsByGrantee(var1, var2, var3, var4, var5, 1);
   }

   private boolean visitPermsByGrantee(String var1, TransactionController var2, int var3, int var4, int var5, int var6) throws StandardException {
      TabInfoImpl var7 = this.getNonCoreTI(var3);
      PermissionsCatalogRowFactory var8 = (PermissionsCatalogRowFactory)var7.getCatalogRowFactory();
      ConglomerateController var9 = var2.openConglomerate(var7.getHeapConglomerate(), false, 0, 6, 4);
      SQLVarchar var10 = new SQLVarchar(var1);
      ScanQualifier[][] var11 = this.exFactory.getScanQualifier(1);
      var11[0][0].setQualifier(var5 - 1, var10, 2, false, false, false);
      ScanController var12 = var2.openScan(var7.getIndexConglomerate(var4), false, 0, 6, 4, (FormatableBitSet)null, (DataValueDescriptor[])null, 0, var11, (DataValueDescriptor[])null, 0);

      try {
         ExecRow var13 = var8.makeEmptyRow();
         ExecIndexRow var14 = getIndexRowFromHeapRow(var7.getIndexRowGenerator(var4), var9.newRowLocationTemplate(), var13);

         while(var12.fetchNext(var14.getRowArray())) {
            RowLocation var15 = (RowLocation)var14.getColumn(var14.nColumns());
            var9.fetch(var15, var13.getRowArray(), (FormatableBitSet)null);
            if (var6 == 1) {
               boolean var21 = true;
               return var21;
            }

            if (var6 == 0) {
               PermissionsDescriptor var17 = (PermissionsDescriptor)var8.buildDescriptor(var13, (TupleDescriptor)null, this);
               this.removePermEntryInCache(var17);
               var7.deleteRow(var2, var14, var4);
            }
         }

         return false;
      } finally {
         if (var12 != null) {
            var12.close();
         }

         if (var9 != null) {
            var9.close();
         }

      }
   }

   private void dropColumnDescriptorCore(TransactionController var1, ExecIndexRow var2) throws StandardException {
      TabInfoImpl var3 = this.coreInfo[2];
      var3.deleteRow(var1, var2, 0);
   }

   private void dropTablePermDescriptor(TransactionController var1, ExecIndexRow var2) throws StandardException {
      TabInfoImpl var5 = this.getNonCoreTI(16);
      SYSTABLEPERMSRowFactory var6 = (SYSTABLEPERMSRowFactory)var5.getCatalogRowFactory();

      ExecRow var3;
      while((var3 = var5.getRow(var1, var2, 2)) != null) {
         PermissionsDescriptor var4 = (PermissionsDescriptor)var6.buildDescriptor(var3, (TupleDescriptor)null, this);
         this.removePermEntryInCache(var4);
         ExecIndexRow var7 = var6.buildIndexKeyRow(1, var4);
         var5.deleteRow(var1, var7, 1);
      }

   }

   private void dropColumnPermDescriptor(TransactionController var1, ExecIndexRow var2) throws StandardException {
      TabInfoImpl var5 = this.getNonCoreTI(17);
      SYSCOLPERMSRowFactory var6 = (SYSCOLPERMSRowFactory)var5.getCatalogRowFactory();

      ExecRow var3;
      while((var3 = var5.getRow(var1, var2, 2)) != null) {
         PermissionsDescriptor var4 = (PermissionsDescriptor)var6.buildDescriptor(var3, (TupleDescriptor)null, this);
         this.removePermEntryInCache(var4);
         ExecIndexRow var7 = var6.buildIndexKeyRow(1, var4);
         var5.deleteRow(var1, var7, 1);
      }

   }

   private void updateColumnDescriptor(ColumnDescriptor var1, UUID var2, String var3, int[] var4, TransactionController var5) throws StandardException {
      ExecIndexRow var6 = null;
      TabInfoImpl var10 = this.coreInfo[2];
      SYSCOLUMNSRowFactory var11 = (SYSCOLUMNSRowFactory)var10.getCatalogRowFactory();
      SQLChar var8 = getIDValueAsCHAR(var2);
      SQLVarchar var9 = new SQLVarchar(var3);
      var6 = this.exFactory.getIndexableRow(2);
      var6.setColumn(1, var8);
      var6.setColumn(2, var9);
      ExecRow var7 = var11.makeRow(var1, (TupleDescriptor)null);
      boolean[] var12 = new boolean[var11.getNumIndexes()];
      if (var4 == null) {
         var12[0] = true;
         var12[1] = true;
      } else {
         for(int var13 = 0; var13 < var4.length; ++var13) {
            if (var4[var13] == 2 || var4[var13] == 1) {
               var12[0] = true;
               break;
            }

            if (var4[var13] == 6) {
               var12[1] = true;
               break;
            }
         }
      }

      var10.updateRow(var6, (ExecRow)var7, 0, var12, var4, var5);
   }

   public ViewDescriptor getViewDescriptor(UUID var1) throws StandardException {
      return this.getViewDescriptor(this.getTableDescriptor(var1));
   }

   public ViewDescriptor getViewDescriptor(TableDescriptor var1) throws StandardException {
      TableDescriptor var2 = var1;
      if (var1.getViewDescriptor() != null) {
         return var1.getViewDescriptor();
      } else {
         synchronized(var1) {
            if (var2.getViewDescriptor() != null) {
               return var2.getViewDescriptor();
            }

            var2.setViewDescriptor(this.getViewDescriptorScan(var2));
         }

         return var1.getViewDescriptor();
      }
   }

   private ViewDescriptor getViewDescriptorScan(TableDescriptor var1) throws StandardException {
      TabInfoImpl var4 = this.getNonCoreTI(8);
      UUID var5 = var1.getUUID();
      SQLChar var3 = getIDValueAsCHAR(var5);
      ExecIndexRow var6 = this.exFactory.getIndexableRow(1);
      var6.setColumn(1, var3);
      ViewDescriptor var2 = (ViewDescriptor)this.getDescriptorViaIndex(0, var6, (ScanQualifier[][])null, var4, (TupleDescriptor)null, (List)null, ViewDescriptor.class, false);
      if (var2 != null) {
         var2.setViewName(var1.getName());
      }

      return var2;
   }

   public void dropViewDescriptor(ViewDescriptor var1, TransactionController var2) throws StandardException {
      TabInfoImpl var4 = this.getNonCoreTI(8);
      SQLChar var3 = getIDValueAsCHAR(var1.getUUID());
      ExecIndexRow var5 = this.exFactory.getIndexableRow(1);
      var5.setColumn(1, var3);
      var4.deleteRow(var2, var5, 0);
   }

   private FileInfoDescriptor getFileInfoDescriptorIndex2Scan(UUID var1) throws StandardException {
      TabInfoImpl var3 = this.getNonCoreTI(12);
      SQLChar var2 = getIDValueAsCHAR(var1);
      ExecIndexRow var4 = this.exFactory.getIndexableRow(1);
      var4.setColumn(1, var2);
      return (FileInfoDescriptor)this.getDescriptorViaIndex(1, var4, (ScanQualifier[][])null, var3, (TupleDescriptor)null, (List)null, FileInfoDescriptor.class, false);
   }

   public FileInfoDescriptor getFileInfoDescriptor(UUID var1) throws StandardException {
      return this.getFileInfoDescriptorIndex2Scan(var1);
   }

   private FileInfoDescriptor getFileInfoDescriptorIndex1Scan(UUID var1, String var2) throws StandardException {
      TabInfoImpl var5 = this.getNonCoreTI(12);
      SQLVarchar var4 = new SQLVarchar(var2);
      SQLChar var3 = getIDValueAsCHAR(var1);
      ExecIndexRow var6 = this.exFactory.getIndexableRow(2);
      var6.setColumn(1, var4);
      var6.setColumn(2, var3);
      FileInfoDescriptor var7 = (FileInfoDescriptor)this.getDescriptorViaIndex(0, var6, (ScanQualifier[][])null, var5, (TupleDescriptor)null, (List)null, FileInfoDescriptor.class, false);
      return var7;
   }

   public FileInfoDescriptor getFileInfoDescriptor(SchemaDescriptor var1, String var2) throws StandardException {
      return this.getFileInfoDescriptorIndex1Scan(var1.getUUID(), var2);
   }

   public void dropFileInfoDescriptor(FileInfoDescriptor var1) throws StandardException {
      ExecIndexRow var2 = null;
      TabInfoImpl var4 = this.getNonCoreTI(12);
      TransactionController var5 = this.getTransactionExecute();
      SQLChar var3 = getIDValueAsCHAR(var1.getUUID());
      var2 = this.exFactory.getIndexableRow(1);
      var2.setColumn(1, var3);
      var4.deleteRow(var5, var2, 1);
   }

   public SPSDescriptor getSPSDescriptor(UUID var1) throws StandardException {
      this.getNonCoreTI(11);
      SPSDescriptor var2;
      if (this.spsNameCache != null && this.getCacheMode() == 0) {
         var2 = (SPSDescriptor)this.spsIdHash.get(var1);
         if (var2 != null) {
            return var2;
         }

         var2 = this.getSPSDescriptorIndex2Scan(var1.toString());
         TableKey var3 = new TableKey(var2.getSchemaDescriptor().getUUID(), var2.getName());

         try {
            SPSNameCacheable var4 = (SPSNameCacheable)this.spsNameCache.create(var3, var2);
            this.spsNameCache.release(var4);
         } catch (StandardException var5) {
            if ("XBCA0.S".equals(var5.getMessageId())) {
               return var2;
            }

            throw var5;
         }
      } else {
         var2 = this.getSPSDescriptorIndex2Scan(var1.toString());
      }

      return var2;
   }

   void spsCacheEntryAdded(SPSDescriptor var1) {
      this.spsIdHash.put(var1.getUUID(), var1);
   }

   void spsCacheEntryRemoved(SPSDescriptor var1) {
      this.spsIdHash.remove(var1.getUUID());
   }

   SPSDescriptor getUncachedSPSDescriptor(TableKey var1) throws StandardException {
      return this.getSPSDescriptorIndex1Scan(var1.getTableName(), var1.getSchemaId().toString());
   }

   protected SPSDescriptor getUncachedSPSDescriptor(UUID var1) throws StandardException {
      return this.getSPSDescriptorIndex2Scan(var1.toString());
   }

   private SPSDescriptor getSPSDescriptorIndex2Scan(String var1) throws StandardException {
      TabInfoImpl var3 = this.getNonCoreTI(11);
      SQLChar var2 = new SQLChar(var1);
      ExecIndexRow var4 = this.exFactory.getIndexableRow(1);
      var4.setColumn(1, var2);
      SPSDescriptor var5 = (SPSDescriptor)this.getDescriptorViaIndex(0, var4, (ScanQualifier[][])null, var3, (TupleDescriptor)null, (List)null, SPSDescriptor.class, false);
      return var5;
   }

   public SPSDescriptor getSPSDescriptor(String var1, SchemaDescriptor var2) throws StandardException {
      SPSDescriptor var3 = null;
      UUID var5 = var2.getUUID();
      if (this.spsNameCache != null && this.getCacheMode() == 0) {
         TableKey var4 = new TableKey(var5, var1);
         SPSNameCacheable var6 = (SPSNameCacheable)this.spsNameCache.find(var4);
         if (var6 != null) {
            var3 = var6.getSPSDescriptor();
            this.spsNameCache.release(var6);
         }

         return var3;
      } else {
         return this.getSPSDescriptorIndex1Scan(var1, var5.toString());
      }
   }

   private SPSDescriptor getSPSDescriptorIndex1Scan(String var1, String var2) throws StandardException {
      TabInfoImpl var5 = this.getNonCoreTI(11);
      SQLVarchar var4 = new SQLVarchar(var1);
      SQLChar var3 = new SQLChar(var2);
      ExecIndexRow var6 = this.exFactory.getIndexableRow(2);
      var6.setColumn(1, var4);
      var6.setColumn(2, var3);
      SPSDescriptor var7 = (SPSDescriptor)this.getDescriptorViaIndex(1, var6, (ScanQualifier[][])null, var5, (TupleDescriptor)null, (List)null, SPSDescriptor.class, false);
      if (var7 != null) {
         ArrayList var8 = new ArrayList();
         var7.setParams(this.getSPSParams(var7, var8));
         Object[] var9 = var8.toArray();
         var7.setParameterDefaults(var9);
      }

      return var7;
   }

   public void addSPSDescriptor(SPSDescriptor var1, TransactionController var2) throws StandardException {
      TabInfoImpl var4 = this.getNonCoreTI(11);
      SYSSTATEMENTSRowFactory var5 = (SYSSTATEMENTSRowFactory)var4.getCatalogRowFactory();
      int var6;
      synchronized(var1) {
         boolean var8 = var1.initiallyCompilable();
         ExecRow var3 = var5.makeSYSSTATEMENTSrow(var8, var1);
         var6 = var4.insertRow(var3, var2);
      }

      if (var6 != -1) {
         throw StandardException.newException("X0Y32.S", new Object[]{var1.getDescriptorType(), var1.getDescriptorName(), var1.getSchemaDescriptor().getDescriptorType(), var1.getSchemaDescriptor().getSchemaName()});
      } else {
         this.addSPSParams(var1, var2);
      }
   }

   private void addSPSParams(SPSDescriptor var1, TransactionController var2) throws StandardException {
      UUID var3 = var1.getUUID();
      DataTypeDescriptor[] var4 = var1.getParams();
      Object[] var5 = var1.getParameterDefaults();
      if (var4 != null) {
         int var6 = var4.length;

         for(int var7 = 0; var7 < var6; ++var7) {
            int var8 = var7 + 1;
            ColumnDescriptor var9 = new ColumnDescriptor("PARAM" + var8, var8, var4[var7], var5 != null && var7 < var5.length ? (DataValueDescriptor)var5[var7] : (DataValueDescriptor)null, (DefaultInfo)null, var3, (UUID)null, 0L, 0L, 0L, false);
            this.addDescriptor(var9, (TupleDescriptor)null, 2, false, var2);
         }

      }
   }

   public DataTypeDescriptor[] getSPSParams(SPSDescriptor var1, List var2) throws StandardException {
      ColumnDescriptorList var3 = new ColumnDescriptorList();
      this.getColumnDescriptorsScan(var1.getUUID(), var3, var1);
      int var4 = var3.size();
      DataTypeDescriptor[] var5 = new DataTypeDescriptor[var4];

      for(int var6 = 0; var6 < var4; ++var6) {
         ColumnDescriptor var7 = var3.elementAt(var6);
         var5[var6] = var7.getType();
         if (var2 != null) {
            var2.add(var7.getDefaultValue());
         }
      }

      return var5;
   }

   public void updateSPS(SPSDescriptor var1, TransactionController var2, boolean var3) throws StandardException {
      ExecIndexRow var4 = null;
      TabInfoImpl var7 = this.getNonCoreTI(11);
      SYSSTATEMENTSRowFactory var8 = (SYSSTATEMENTSRowFactory)var7.getCatalogRowFactory();
      int[] var9;
      if (var3) {
         var9 = new int[]{5, 6, 7, 9, 10};
      } else {
         var9 = new int[]{5, 10};
      }

      SQLChar var6 = getIDValueAsCHAR(var1.getUUID());
      var4 = this.exFactory.getIndexableRow(1);
      var4.setColumn(1, var6);
      ExecRow var5 = var8.makeSYSSTATEMENTSrow(false, var1);
      boolean[] var10 = new boolean[2];
      var7.updateRow(var4, (ExecRow)var5, 0, var10, var9, var2);
      if (var3) {
         DataTypeDescriptor[] var11 = var1.getParams();
         if (var11 != null) {
            this.dropAllColumnDescriptors(var1.getUUID(), var2);
            this.addSPSParams(var1, var2);
         }
      }
   }

   public void invalidateAllSPSPlans() throws StandardException {
      LanguageConnectionContext var1 = (LanguageConnectionContext)getContext("LanguageConnectionContext");
      this.invalidateAllSPSPlans(var1);
   }

   public void invalidateAllSPSPlans(LanguageConnectionContext var1) throws StandardException {
      this.startWriting(var1);

      for(SPSDescriptor var3 : this.getAllSPSDescriptors()) {
         var3.makeInvalid(14, var1);
      }

   }

   void clearSPSPlans() throws StandardException {
      TabInfoImpl var1 = this.getNonCoreTI(11);
      this.faultInTabInfo(var1);
      TransactionController var2 = this.getTransactionExecute();
      FormatableBitSet var3 = new FormatableBitSet(11);
      FormatableBitSet var4 = new FormatableBitSet(11);
      var4.set(4);
      var4.set(9);
      DataValueDescriptor[] var5 = new DataValueDescriptor[11];
      var5[4] = new SQLBoolean(false);
      var5[9] = new UserType((Object)null);
      ScanController var6 = var2.openScan(var1.getHeapConglomerate(), false, 4, 7, 4, var3, (DataValueDescriptor[])null, 0, (Qualifier[][])null, (DataValueDescriptor[])null, 0);

      while(var6.fetchNext((DataValueDescriptor[])null)) {
         var6.replace(var5, var4);
      }

      var6.close();
   }

   public void dropSPSDescriptor(SPSDescriptor var1, TransactionController var2) throws StandardException {
      this.dropSPSDescriptor(var1.getUUID(), var2);
   }

   public void dropSPSDescriptor(UUID var1, TransactionController var2) throws StandardException {
      TabInfoImpl var4 = this.getNonCoreTI(11);
      SQLChar var3 = getIDValueAsCHAR(var1);
      ExecIndexRow var5 = this.exFactory.getIndexableRow(1);
      var5.setColumn(1, var3);
      var4.deleteRow(var2, var5, 0);
      this.dropAllColumnDescriptors(var1, var2);
   }

   public List getAllSPSDescriptors() throws StandardException {
      TabInfoImpl var1 = this.getNonCoreTI(11);
      List var2 = newSList();
      FormatableBitSet var3 = new FormatableBitSet(var1.getCatalogRowFactory().getHeapColumnCount());

      for(int var4 = 0; var4 < var3.size(); ++var4) {
         if (var4 + 1 == 10) {
            var3.clear(var4);
         } else {
            var3.set(var4);
         }
      }

      this.getDescriptorViaHeap(var3, (ScanQualifier[][])null, var1, (TupleDescriptor)null, var2, SPSDescriptor.class);
      return var2;
   }

   private ConstraintDescriptorList getAllConstraintDescriptors() throws StandardException {
      TabInfoImpl var1 = this.getNonCoreTI(4);
      ConstraintDescriptorList var2 = new ConstraintDescriptorList();
      this.getConstraintDescriptorViaHeap((ScanQualifier[][])null, var1, (TupleDescriptor)null, var2);
      return var2;
   }

   public int[] examineTriggerNodeAndCols(Visitable var1, String var2, String var3, String var4, int[] var5, int[] var6, int var7, TableDescriptor var8, int var9, boolean var10, List var11) throws StandardException {
      boolean var12 = this.checkVersion(210, (String)null);
      new StringBuilder();
      boolean var14 = false;
      int var15 = var8.getNumberOfColumns();
      int[] var16 = new int[var15];
      int[] var17 = new int[var15];
      Arrays.fill(var17, -1);
      if (var5 == null) {
         for(int var18 = 0; var18 < var15; ++var18) {
            var16[var18] = var18 + 1;
         }
      } else {
         Arrays.fill(var16, -1);

         for(int var26 = 0; var26 < var5.length; ++var26) {
            var16[var5[var26] - 1] = var5[var26];
         }
      }

      if (var6 != null) {
         for(int var27 = 0; var27 < var6.length; ++var27) {
            if (var6[var27] > 0) {
               var16[var6[var27] - 1] = var6[var27];
            }
         }
      }

      SortedSet var28 = getTransitionVariables(var1, var2, var3);
      if (var10) {
         for(ColumnReference var20 : var28) {
            TableName var21 = var20.getQualifiedTableName();
            this.checkInvalidTriggerReference(var21.getTableName(), var2, var3, var9);
            String var22 = var20.getColumnName();
            ColumnDescriptor var23;
            if ((var23 = var8.getColumnDescriptor(var22)) == null) {
               throw StandardException.newException("42X04", new Object[]{var21 + "." + var22});
            }

            if (var12) {
               int var24 = var23.getPosition();
               var16[var24 - 1] = var24;
               var17[var24 - 1] = var24;
               var6[var24 - 1] = var24;
            }
         }
      } else if (var5 != null && var6 != null) {
         for(int var29 = 0; var29 < var6.length; ++var29) {
            var16[var6[var29] - 1] = var6[var29];
         }
      }

      Arrays.sort(var16);
      var16 = this.justTheRequiredColumns(var16, var8);
      return var16;
   }

   public String getTriggerActionString(Visitable var1, String var2, String var3, String var4, int[] var5, int[] var6, int var7, TableDescriptor var8, int var9, boolean var10, List var11, int[] var12) throws StandardException {
      boolean var13 = this.checkVersion(210, (String)null);
      StringBuilder var14 = new StringBuilder();
      int var15 = 0;
      int var16 = var8.getNumberOfColumns();
      int[] var17 = new int[var16];
      SortedSet var18 = getTransitionVariables(var1, var2, var3);
      var17 = var12;

      for(ColumnReference var20 : var18) {
         TableName var21 = var20.getQualifiedTableName();
         int var22 = var21.getBeginOffset() - var7;
         String var23 = var20.getColumnName();
         var14.append(var4, var15, var22);
         int var24 = -1;
         ColumnDescriptor var25 = var8.getColumnDescriptor(var23);
         if (var25 == null) {
            throw StandardException.newException("42X04", new Object[]{var21 + "." + var23});
         }

         int var26 = var25.getPosition();
         if (var13 && var17 != null) {
            for(int var27 = 0; var27 < var17.length; ++var27) {
               if (var17[var27] == var26) {
                  var24 = var27 + 1;
               }
            }
         } else {
            var24 = var26;
         }

         int var29 = var14.length();
         var14.append(this.genColumnReferenceSQL(var8, var23, var21.getTableName(), var21.getTableName().equals(var2), var24));
         var15 = var20.getEndOffset() + 1 - var7;
         if (var11 != null) {
            var11.add(new int[]{var22, var15, var29, var14.length()});
         }
      }

      var14.append(var4, var15, var4.length());
      return var14.toString();
   }

   private static SortedSet getTransitionVariables(Visitable var0, String var1, String var2) throws StandardException {
      SortedSet var3 = ((QueryTreeNode)var0).getOffsetOrderedNodes(ColumnReference.class);
      Iterator var4 = var3.iterator();

      while(var4.hasNext()) {
         TableName var5 = ((ColumnReference)var4.next()).getQualifiedTableName();
         if (!isTransitionVariable(var5, var1, var2)) {
            var4.remove();
         }
      }

      return var3;
   }

   private static boolean isTransitionVariable(TableName var0, String var1, String var2) {
      if (var0 != null) {
         if (var0.hasSchema()) {
            return false;
         }

         String var3 = var0.getTableName();
         if (var3 != null) {
            return var3.equals(var1) || var3.equals(var2);
         }
      }

      return false;
   }

   private int[] justTheRequiredColumns(int[] var1, TableDescriptor var2) {
      int var3 = 0;
      int var4 = var2.getNumberOfColumns();

      for(int var5 = 0; var5 < var4; ++var5) {
         if (var1[var5] != -1) {
            ++var3;
         }
      }

      if (var3 > 0) {
         int[] var8 = new int[var3];
         int var6 = 0;

         for(int var7 = 0; var7 < var4; ++var7) {
            if (var1[var7] != -1) {
               var8[var6++] = var1[var7];
            }
         }

         return var8;
      } else {
         return null;
      }
   }

   private void checkInvalidTriggerReference(String var1, String var2, String var3, int var4) throws StandardException {
      if (var1.equals(var2) && (var4 & 4) == 4) {
         throw StandardException.newException("42Y92", new Object[]{"INSERT", "new"});
      } else if (var1.equals(var3) && (var4 & 2) == 2) {
         throw StandardException.newException("42Y92", new Object[]{"DELETE", "old"});
      }
   }

   private String genColumnReferenceSQL(TableDescriptor var1, String var2, String var3, boolean var4, int var5) throws StandardException {
      ColumnDescriptor var6 = null;
      if ((var6 = var1.getColumnDescriptor(var2)) == null) {
         throw StandardException.newException("42X04", new Object[]{var3 + "." + var2});
      } else {
         DataTypeDescriptor var7 = var6.getType();
         TypeId var8 = var7.getTypeId();
         if (!var8.isXMLTypeId()) {
            StringBuffer var11 = new StringBuffer();
            var11.append("CAST (org.apache.derby.iapi.db.Factory::getTriggerExecutionContext().");
            var11.append(var4 ? "getOldRow()" : "getNewRow()");
            var11.append(".getObject(");
            var11.append(var5);
            var11.append(") AS ");
            var11.append(var8.userType() ? var8.getSQLTypeName() : var7.getSQLstring());
            var11.append(") ");
            return var11.toString();
         } else {
            StringBuffer var9 = new StringBuffer();
            var9.append("XMLPARSE(DOCUMENT CAST( ");
            var9.append("org.apache.derby.iapi.db.Factory::getTriggerExecutionContext().");
            var9.append(var4 ? "getOldRow()" : "getNewRow()");
            var9.append(".getString(");
            var9.append(var5);
            var9.append(") AS CLOB) PRESERVE WHITESPACE ) ");
            return var9.toString();
         }
      }
   }

   public TriggerDescriptor getTriggerDescriptor(UUID var1) throws StandardException {
      TabInfoImpl var2 = this.getNonCoreTI(13);
      SQLChar var3 = getIDValueAsCHAR(var1);
      ExecIndexRow var4 = this.exFactory.getIndexableRow(1);
      var4.setColumn(1, var3);
      return (TriggerDescriptor)this.getDescriptorViaIndex(0, var4, (ScanQualifier[][])null, var2, (TupleDescriptor)null, (List)null, TriggerDescriptor.class, false);
   }

   public TriggerDescriptor getTriggerDescriptor(String var1, SchemaDescriptor var2) throws StandardException {
      TabInfoImpl var5 = this.getNonCoreTI(13);
      SQLVarchar var4 = new SQLVarchar(var1);
      SQLChar var3 = getIDValueAsCHAR(var2.getUUID());
      ExecIndexRow var6 = this.exFactory.getIndexableRow(2);
      var6.setColumn(1, var4);
      var6.setColumn(2, var3);
      return (TriggerDescriptor)this.getDescriptorViaIndex(1, var6, (ScanQualifier[][])null, var5, (TupleDescriptor)null, (List)null, TriggerDescriptor.class, false);
   }

   public TriggerDescriptorList getTriggerDescriptors(TableDescriptor var1) throws StandardException {
      TriggerDescriptorList var2 = var1.getTriggerDescriptorList();
      synchronized(var2) {
         if (!var2.getScanned()) {
            this.getTriggerDescriptorsScan(var1, false);
         }

         return var2;
      }
   }

   private void getTriggerDescriptorsScan(TableDescriptor var1, boolean var2) throws StandardException {
      TriggerDescriptorList var3 = var1.getTriggerDescriptorList();
      Object var4 = null;
      TabInfoImpl var5 = this.getNonCoreTI(13);
      SQLChar var7 = getIDValueAsCHAR(var1.getUUID());
      ExecIndexRow var6 = this.exFactory.getIndexableRow(1);
      var6.setColumn(1, var7);
      this.getDescriptorViaIndex(2, var6, (ScanQualifier[][])null, var5, (TupleDescriptor)null, var3, TriggerDescriptor.class, var2);
      var3.setScanned(true);
   }

   public void dropTriggerDescriptor(TriggerDescriptor var1, TransactionController var2) throws StandardException {
      TabInfoImpl var4 = this.getNonCoreTI(13);
      SQLChar var3 = getIDValueAsCHAR(var1.getUUID());
      ExecIndexRow var5 = this.exFactory.getIndexableRow(1);
      var5.setColumn(1, var3);
      var4.deleteRow(var2, var5, 0);
   }

   public void updateTriggerDescriptor(TriggerDescriptor var1, UUID var2, int[] var3, TransactionController var4) throws StandardException {
      ExecIndexRow var5 = null;
      TabInfoImpl var8 = this.getNonCoreTI(13);
      SYSTRIGGERSRowFactory var9 = (SYSTRIGGERSRowFactory)var8.getCatalogRowFactory();
      SQLChar var7 = getIDValueAsCHAR(var2);
      var5 = this.exFactory.getIndexableRow(1);
      var5.setColumn(1, var7);
      ExecRow var6 = var9.makeRow(var1, (TupleDescriptor)null);
      boolean[] var10 = new boolean[3];
      if (var3 == null) {
         var10[0] = true;
         var10[1] = true;
         var10[2] = true;
      } else {
         for(int var11 = 0; var11 < var3.length; ++var11) {
            switch (var3[var11]) {
               case 1:
                  var10[0] = true;
                  break;
               case 2:
               case 3:
                  var10[1] = true;
               case 4:
               case 5:
               case 6:
               case 7:
               case 8:
               default:
                  break;
               case 9:
                  var10[2] = true;
            }
         }
      }

      var8.updateRow(var5, (ExecRow)var6, 0, var10, var3, var4);
   }

   public ConstraintDescriptor getConstraintDescriptor(UUID var1) throws StandardException {
      TabInfoImpl var3 = this.getNonCoreTI(4);
      SQLChar var2 = getIDValueAsCHAR(var1);
      ExecIndexRow var4 = this.exFactory.getIndexableRow(1);
      var4.setColumn(1, var2);
      return this.getConstraintDescriptorViaIndex(0, var4, var3, (TableDescriptor)null, (ConstraintDescriptorList)null, false);
   }

   public ConstraintDescriptor getConstraintDescriptor(String var1, UUID var2) throws StandardException {
      TabInfoImpl var5 = this.getNonCoreTI(4);
      SQLVarchar var4 = new SQLVarchar(var1);
      SQLChar var3 = getIDValueAsCHAR(var2);
      ExecIndexRow var6 = this.exFactory.getIndexableRow(2);
      var6.setColumn(1, var4);
      var6.setColumn(2, var3);
      return this.getConstraintDescriptorViaIndex(1, var6, var5, (TableDescriptor)null, (ConstraintDescriptorList)null, false);
   }

   public List getStatisticsDescriptors(TableDescriptor var1) throws StandardException {
      TabInfoImpl var2 = this.getNonCoreTI(14);
      List var3 = newSList();
      SQLChar var4 = getIDValueAsCHAR(var1.getUUID());
      ExecIndexRow var5 = this.exFactory.getIndexableRow(1);
      var5.setColumn(1, var4);
      this.getDescriptorViaIndex(0, var5, (ScanQualifier[][])null, var2, (TupleDescriptor)null, var3, StatisticsDescriptor.class, false, 1, this.getTransactionCompile());
      return var3;
   }

   public ConstraintDescriptorList getConstraintDescriptors(TableDescriptor var1) throws StandardException {
      if (var1 == null) {
         return this.getAllConstraintDescriptors();
      } else {
         ConstraintDescriptorList var2 = var1.getConstraintDescriptorList();
         synchronized(var2) {
            if (!var2.getScanned()) {
               this.getConstraintDescriptorsScan(var1, false);
            }

            return var2;
         }
      }
   }

   public ConstraintDescriptorList getActiveConstraintDescriptors(ConstraintDescriptorList var1) throws StandardException {
      return var1;
   }

   public boolean activeConstraint(ConstraintDescriptor var1) throws StandardException {
      return true;
   }

   public ConstraintDescriptor getConstraintDescriptor(TableDescriptor var1, UUID var2) throws StandardException {
      return this.getConstraintDescriptors(var1).getConstraintDescriptor(var2);
   }

   public ConstraintDescriptor getConstraintDescriptorById(TableDescriptor var1, UUID var2) throws StandardException {
      return this.getConstraintDescriptors(var1).getConstraintDescriptorById(var2);
   }

   public ConstraintDescriptor getConstraintDescriptorByName(TableDescriptor var1, SchemaDescriptor var2, String var3, boolean var4) throws StandardException {
      if (var4) {
         var1.emptyConstraintDescriptorList();
         this.getConstraintDescriptorsScan(var1, true);
      }

      return this.getConstraintDescriptors(var1).getConstraintDescriptorByName(var2, var3);
   }

   private void getConstraintDescriptorsScan(TableDescriptor var1, boolean var2) throws StandardException {
      ConstraintDescriptorList var3 = var1.getConstraintDescriptorList();
      Object var4 = null;
      TabInfoImpl var5 = this.getNonCoreTI(4);
      SQLChar var7 = getIDValueAsCHAR(var1.getUUID());
      ExecIndexRow var6 = this.exFactory.getIndexableRow(1);
      var6.setColumn(1, var7);
      this.getConstraintDescriptorViaIndex(2, var6, var5, var1, var3, var2);
      var3.setScanned(true);
   }

   protected ConstraintDescriptor getConstraintDescriptorViaIndex(int var1, ExecIndexRow var2, TabInfoImpl var3, TableDescriptor var4, ConstraintDescriptorList var5, boolean var6) throws StandardException {
      SYSCONSTRAINTSRowFactory var7 = (SYSCONSTRAINTSRowFactory)var3.getCatalogRowFactory();
      ConstraintDescriptor var9 = null;
      TransactionController var14 = this.getTransactionCompile();
      ExecRow var11 = var7.makeEmptyRow();
      ConglomerateController var8 = var14.openConglomerate(var3.getHeapConglomerate(), false, 0, 6, 4);
      ScanController var13 = var14.openScan(var3.getIndexConglomerate(var1), false, var6 ? 4 : 0, 6, 4, (FormatableBitSet)null, var2.getRowArray(), 1, (Qualifier[][])null, var2.getRowArray(), -1);

      while(var13.next()) {
         Object var15 = null;
         ExecIndexRow var10 = getIndexRowFromHeapRow(var3.getIndexRowGenerator(var1), var8.newRowLocationTemplate(), var11);
         var13.fetch(var10.getRowArray());
         RowLocation var12 = (RowLocation)var10.getColumn(var10.nColumns());
         var8.fetch(var12, var11.getRowArray(), (FormatableBitSet)null);
         switch (var7.getConstraintType(var11)) {
            case 2:
            case 3:
            case 6:
               var15 = this.getSubKeyConstraint(var7.getConstraintId(var11), var7.getConstraintType(var11));
               break;
            case 4:
               var15 = this.getSubCheckConstraint(var7.getConstraintId(var11));
            case 5:
         }

         ((SubConstraintDescriptor)var15).setTableDescriptor(var4);
         var9 = (ConstraintDescriptor)var7.buildDescriptor(var11, (TupleDescriptor)var15, this);
         if (var5 == null) {
            break;
         }

         var5.add(var9);
      }

      var13.close();
      var8.close();
      return var9;
   }

   protected TupleDescriptor getConstraintDescriptorViaHeap(ScanQualifier[][] var1, TabInfoImpl var2, TupleDescriptor var3, ConstraintDescriptorList var4) throws StandardException {
      SYSCONSTRAINTSRowFactory var5 = (SYSCONSTRAINTSRowFactory)var2.getCatalogRowFactory();
      ConstraintDescriptor var9 = null;
      TransactionController var8 = this.getTransactionCompile();
      ExecRow var6 = var5.makeEmptyRow();
      ScanController var7 = var8.openScan(var2.getHeapConglomerate(), false, 0, 7, 4, (FormatableBitSet)null, (DataValueDescriptor[])null, 0, var1, (DataValueDescriptor[])null, 0);

      try {
         while(var7.fetchNext(var6.getRowArray())) {
            Object var10 = null;
            switch (var5.getConstraintType(var6)) {
               case 2:
               case 3:
               case 6:
                  var10 = this.getSubKeyConstraint(var5.getConstraintId(var6), var5.getConstraintType(var6));
                  break;
               case 4:
                  var10 = this.getSubCheckConstraint(var5.getConstraintId(var6));
               case 5:
            }

            var9 = (ConstraintDescriptor)var5.buildDescriptor(var6, (TupleDescriptor)var10, this);
            if (var4 == null) {
               break;
            }

            var4.add(var9);
         }
      } finally {
         var7.close();
      }

      return var9;
   }

   public TableDescriptor getConstraintTableDescriptor(UUID var1) throws StandardException {
      List var2 = this.getConstraints(var1, 0, 2);
      return var2.size() == 0 ? null : this.getTableDescriptor((UUID)var2.get(0));
   }

   public ConstraintDescriptorList getForeignKeys(UUID var1) throws StandardException {
      TabInfoImpl var2 = this.getNonCoreTI(10);
      List var3 = newSList();
      SQLChar var4 = getIDValueAsCHAR(var1);
      ExecIndexRow var5 = this.exFactory.getIndexableRow(1);
      var5.setColumn(1, var4);
      this.getDescriptorViaIndex(1, var5, (ScanQualifier[][])null, var2, (TupleDescriptor)null, var3, SubKeyConstraintDescriptor.class, false);
      ConstraintDescriptorList var7 = new ConstraintDescriptorList();

      for(SubKeyConstraintDescriptor var9 : var3) {
         TableDescriptor var6 = this.getConstraintTableDescriptor(var9.getUUID());
         var7.add(this.getConstraintDescriptors(var6).getConstraintDescriptorById(var9.getUUID()));
      }

      return var7;
   }

   public List getConstraints(UUID var1, int var2, int var3) throws StandardException {
      ConglomerateController var7 = null;
      ScanController var8 = null;
      TabInfoImpl var10 = this.getNonCoreTI(4);
      SYSCONSTRAINTSRowFactory var11 = (SYSCONSTRAINTSRowFactory)var10.getCatalogRowFactory();
      Object var12 = null;
      ArrayList var13 = new ArrayList();

      try {
         SQLChar var14 = getIDValueAsCHAR(var1);
         ExecIndexRow var15 = this.exFactory.getIndexableRow(1);
         var15.setColumn(1, var14);
         TransactionController var9 = this.getTransactionCompile();
         ExecRow var5 = var11.makeEmptyRow();
         var7 = var9.openConglomerate(var10.getHeapConglomerate(), false, 0, 6, 4);
         ExecIndexRow var4 = getIndexRowFromHeapRow(var10.getIndexRowGenerator(var2), var7.newRowLocationTemplate(), var5);
         DataValueDescriptor[] var16 = new DataValueDescriptor[7];
         FormatableBitSet var17 = new FormatableBitSet(7);
         var17.set(var3 - 1);
         var16[var3 - 1] = new SQLChar();
         var8 = var9.openScan(var10.getIndexConglomerate(var2), false, 0, 6, 4, (FormatableBitSet)null, var15.getRowArray(), 1, (Qualifier[][])null, var15.getRowArray(), -1);

         while(var8.fetchNext(var4.getRowArray())) {
            RowLocation var6 = (RowLocation)var4.getColumn(var4.nColumns());
            var7.fetch(var6, var16, var17);
            var13.add(this.uuidFactory.recreateUUID((String)var16[var3 - 1].getObject()));
         }
      } finally {
         if (var7 != null) {
            var7.close();
         }

         if (var8 != null) {
            var8.close();
         }

      }

      return var13;
   }

   public void addConstraintDescriptor(ConstraintDescriptor var1, TransactionController var2) throws StandardException {
      int var3 = var1.getConstraintType();
      this.addDescriptor(var1, var1.getSchemaDescriptor(), 4, false, var2);
      switch (var3) {
         case 2:
         case 3:
         case 6:
            this.addSubKeyConstraint((KeyConstraintDescriptor)var1, var2);
            break;
         case 4:
            this.addDescriptor(var1, (TupleDescriptor)null, 9, true, var2);
         case 5:
      }

   }

   public void updateConstraintDescriptor(ConstraintDescriptor var1, UUID var2, int[] var3, TransactionController var4) throws StandardException {
      ExecIndexRow var5 = null;
      TabInfoImpl var8 = this.getNonCoreTI(4);
      SYSCONSTRAINTSRowFactory var9 = (SYSCONSTRAINTSRowFactory)var8.getCatalogRowFactory();
      SQLChar var7 = getIDValueAsCHAR(var2);
      var5 = this.exFactory.getIndexableRow(1);
      var5.setColumn(1, var7);
      ExecRow var6 = var9.makeRow(var1, (TupleDescriptor)null);
      boolean[] var10 = new boolean[3];
      if (var3 == null) {
         var10[0] = true;
         var10[1] = true;
         var10[2] = true;
      } else {
         for(int var11 = 0; var11 < var3.length; ++var11) {
            switch (var3[var11]) {
               case 1:
                  var10[0] = true;
                  break;
               case 2:
                  var10[2] = true;
                  break;
               case 3:
               case 5:
                  var10[1] = true;
               case 4:
            }
         }
      }

      var8.updateRow(var5, (ExecRow)var6, 0, var10, var3, var4);
   }

   public void dropConstraintDescriptor(ConstraintDescriptor var1, TransactionController var2) throws StandardException {
      ExecIndexRow var3 = null;
      TabInfoImpl var6 = this.getNonCoreTI(4);
      switch (var1.getConstraintType()) {
         case 2:
         case 3:
         case 6:
            this.dropSubKeyConstraint(var1, var2);
            break;
         case 4:
            this.dropSubCheckConstraint(var1.getUUID(), var2);
         case 5:
      }

      SQLVarchar var5 = new SQLVarchar(var1.getConstraintName());
      SQLChar var4 = getIDValueAsCHAR(var1.getSchemaDescriptor().getUUID());
      var3 = this.exFactory.getIndexableRow(2);
      var3.setColumn(1, var5);
      var3.setColumn(2, var4);
      var6.deleteRow(var2, var3, 1);
   }

   public void dropAllConstraintDescriptors(TableDescriptor var1, TransactionController var2) throws StandardException {
      for(ConstraintDescriptor var5 : this.getConstraintDescriptors(var1)) {
         this.dropConstraintDescriptor(var5, var2);
      }

      var1.setConstraintDescriptorList((ConstraintDescriptorList)null);
   }

   public SubKeyConstraintDescriptor getSubKeyConstraint(UUID var1, int var2) throws StandardException {
      Object var3 = null;
      byte var5;
      byte var6;
      if (var2 == 6) {
         var6 = 10;
         var5 = 0;
      } else {
         var6 = 5;
         var5 = 0;
      }

      TabInfoImpl var4 = this.getNonCoreTI(var6);
      SQLChar var8 = getIDValueAsCHAR(var1);
      ExecIndexRow var7 = this.exFactory.getIndexableRow(1);
      var7.setColumn(1, var8);
      return (SubKeyConstraintDescriptor)this.getDescriptorViaIndex(var5, var7, (ScanQualifier[][])null, var4, (TupleDescriptor)null, (List)null, SubKeyConstraintDescriptor.class, false);
   }

   private void addSubKeyConstraint(KeyConstraintDescriptor var1, TransactionController var2) throws StandardException {
      ExecRow var3;
      TabInfoImpl var4;
      if (var1.getConstraintType() == 6) {
         ForeignKeyConstraintDescriptor var5 = (ForeignKeyConstraintDescriptor)var1;
         var4 = this.getNonCoreTI(10);
         SYSFOREIGNKEYSRowFactory var6 = (SYSFOREIGNKEYSRowFactory)var4.getCatalogRowFactory();
         var3 = var6.makeRow(var5, (TupleDescriptor)null);
         ReferencedKeyConstraintDescriptor var7 = var5.getReferencedConstraint();
         var7.incrementReferenceCount();
         int[] var8 = new int[]{7};
         this.updateConstraintDescriptor(var7, var7.getUUID(), var8, var2);
      } else {
         var4 = this.getNonCoreTI(5);
         SYSKEYSRowFactory var9 = (SYSKEYSRowFactory)var4.getCatalogRowFactory();
         var3 = var9.makeRow(var1, (TupleDescriptor)null);
      }

      var4.insertRow(var3, var2);
   }

   private void dropSubKeyConstraint(ConstraintDescriptor var1, TransactionController var2) throws StandardException {
      ExecIndexRow var3 = null;
      byte var6;
      byte var7;
      if (var1.getConstraintType() == 6) {
         var6 = 10;
         var7 = 0;
         if (var1.getConstraintType() == 6) {
            ReferencedKeyConstraintDescriptor var8 = (ReferencedKeyConstraintDescriptor)this.getConstraintDescriptor(((ForeignKeyConstraintDescriptor)var1).getReferencedConstraintId());
            if (var8 != null) {
               var8.decrementReferenceCount();
               int[] var9 = new int[]{7};
               this.updateConstraintDescriptor(var8, var8.getUUID(), var9, var2);
            }
         }
      } else {
         var6 = 5;
         var7 = 0;
      }

      TabInfoImpl var5 = this.getNonCoreTI(var6);
      SQLChar var4 = getIDValueAsCHAR(var1.getUUID());
      var3 = this.exFactory.getIndexableRow(1);
      var3.setColumn(1, var4);
      var5.deleteRow(var2, var3, var7);
   }

   private SubCheckConstraintDescriptor getSubCheckConstraint(UUID var1) throws StandardException {
      Object var2 = null;
      TabInfoImpl var3 = this.getNonCoreTI(9);
      SQLChar var5 = getIDValueAsCHAR(var1);
      ExecIndexRow var4 = this.exFactory.getIndexableRow(1);
      var4.setColumn(1, var5);
      return (SubCheckConstraintDescriptor)this.getDescriptorViaIndex(0, var4, (ScanQualifier[][])null, var3, (TupleDescriptor)null, (List)null, SubCheckConstraintDescriptor.class, false);
   }

   private void dropSubCheckConstraint(UUID var1, TransactionController var2) throws StandardException {
      ExecIndexRow var3 = null;
      TabInfoImpl var5 = this.getNonCoreTI(9);
      SQLChar var4 = getIDValueAsCHAR(var1);
      var3 = this.exFactory.getIndexableRow(1);
      var3.setColumn(1, var4);
      var5.deleteRow(var2, var3, 0);
   }

   public Hashtable hashAllConglomerateDescriptorsByNumber(TransactionController var1) throws StandardException {
      Hashtable var2 = new Hashtable();
      Object var3 = null;
      TabInfoImpl var6 = this.coreInfo[0];
      SYSCONGLOMERATESRowFactory var7 = (SYSCONGLOMERATESRowFactory)var6.getCatalogRowFactory();
      ExecRow var5 = var7.makeEmptyRow();
      ScanController var4 = var1.openScan(var6.getHeapConglomerate(), false, 0, 6, 1, (FormatableBitSet)null, (DataValueDescriptor[])null, 1, (ScanQualifier[][])null, (DataValueDescriptor[])null, -1);

      while(var4.fetchNext(var5.getRowArray())) {
         ConglomerateDescriptor var9 = (ConglomerateDescriptor)var7.buildDescriptor(var5, (TupleDescriptor)null, this);
         Long var8 = var9.getConglomerateNumber();
         var2.put(var8, var9);
      }

      var4.close();
      return var2;
   }

   public Hashtable hashAllTableDescriptorsByTableId(TransactionController var1) throws StandardException {
      Hashtable var2 = new Hashtable();
      TabInfoImpl var5 = this.coreInfo[1];
      SYSTABLESRowFactory var6 = (SYSTABLESRowFactory)var5.getCatalogRowFactory();
      ExecRow var4 = var6.makeEmptyRow();
      ScanController var3 = var1.openScan(var5.getHeapConglomerate(), false, 0, 6, 1, (FormatableBitSet)null, (DataValueDescriptor[])null, 1, (ScanQualifier[][])null, (DataValueDescriptor[])null, -1);

      while(var3.fetchNext(var4.getRowArray())) {
         TableDescriptor var7 = (TableDescriptor)var6.buildDescriptor(var4, (TupleDescriptor)null, this, 1);
         var2.put(var7.getUUID(), var7);
      }

      var3.close();
      return var2;
   }

   public ConglomerateDescriptor getConglomerateDescriptor(UUID var1) throws StandardException {
      ConglomerateDescriptor[] var2 = this.getConglomerateDescriptors(var1);
      return var2.length == 0 ? null : var2[0];
   }

   public ConglomerateDescriptor[] getConglomerateDescriptors(UUID var1) throws StandardException {
      TabInfoImpl var3 = this.coreInfo[0];
      List var4 = newSList();
      if (var1 != null) {
         SQLChar var2 = getIDValueAsCHAR(var1);
         ExecIndexRow var5 = this.exFactory.getIndexableRow(1);
         var5.setColumn(1, var2);
         this.getDescriptorViaIndex(0, var5, (ScanQualifier[][])null, var3, (TupleDescriptor)null, var4, ConglomerateDescriptor.class, false);
      } else {
         this.getDescriptorViaHeap((FormatableBitSet)null, (ScanQualifier[][])null, var3, (TupleDescriptor)null, var4, ConglomerateDescriptor.class);
      }

      return (ConglomerateDescriptor[])var4.toArray(new ConglomerateDescriptor[var4.size()]);
   }

   public ConglomerateDescriptor getConglomerateDescriptor(long var1) throws StandardException {
      ConglomerateDescriptor[] var3 = this.getConglomerateDescriptors(var1);
      return var3.length == 0 ? null : var3[0];
   }

   public ConglomerateDescriptor[] getConglomerateDescriptors(long var1) throws StandardException {
      Object var3 = null;
      TabInfoImpl var4 = this.coreInfo[0];
      SYSCONGLOMERATESRowFactory var5 = (SYSCONGLOMERATESRowFactory)var4.getCatalogRowFactory();
      SQLLongint var8 = new SQLLongint(var1);
      ScanQualifier[][] var6 = this.exFactory.getScanQualifier(1);
      var6[0][0].setQualifier(3 - 1, var8, 2, false, false, false);
      ConglomerateDescriptorList var7 = new ConglomerateDescriptorList();
      this.getDescriptorViaHeap((FormatableBitSet)null, var6, var4, (TupleDescriptor)null, var7, ConglomerateDescriptor.class);
      return (ConglomerateDescriptor[])var7.toArray(new ConglomerateDescriptor[var7.size()]);
   }

   private void getConglomerateDescriptorsScan(TableDescriptor var1) throws StandardException {
      ConglomerateDescriptorList var2 = var1.getConglomerateDescriptorList();
      ExecIndexRow var3 = null;
      TabInfoImpl var5 = this.coreInfo[0];
      SQLChar var4 = getIDValueAsCHAR(var1.getUUID());
      var3 = this.exFactory.getIndexableRow(1);
      var3.setColumn(1, var4);
      this.getDescriptorViaIndex(2, var3, (ScanQualifier[][])null, var5, (TupleDescriptor)null, var2, ConglomerateDescriptor.class, false);
   }

   public ConglomerateDescriptor getConglomerateDescriptor(String var1, SchemaDescriptor var2, boolean var3) throws StandardException {
      ExecIndexRow var4 = null;
      Object var6 = null;
      TabInfoImpl var7 = this.coreInfo[0];
      SQLVarchar var5 = new SQLVarchar(var1);
      SQLChar var9 = getIDValueAsCHAR(var2.getUUID());
      var4 = this.exFactory.getIndexableRow(2);
      var4.setColumn(1, var5);
      var4.setColumn(2, var9);
      return (ConglomerateDescriptor)this.getDescriptorViaIndex(1, var4, (ScanQualifier[][])null, var7, (TupleDescriptor)null, (List)null, ConglomerateDescriptor.class, var3);
   }

   public void dropConglomerateDescriptor(ConglomerateDescriptor var1, TransactionController var2) throws StandardException {
      ExecIndexRow var3 = null;
      Object var5 = null;
      TabInfoImpl var6 = this.coreInfo[0];
      SQLVarchar var4 = new SQLVarchar(var1.getConglomerateName());
      SQLChar var8 = getIDValueAsCHAR(var1.getSchemaID());
      var3 = this.exFactory.getIndexableRow(2);
      var3.setColumn(1, var4);
      var3.setColumn(2, var8);
      var6.deleteRow(var2, var3, 1);
   }

   public void dropAllConglomerateDescriptors(TableDescriptor var1, TransactionController var2) throws StandardException {
      ExecIndexRow var3 = null;
      TabInfoImpl var5 = this.coreInfo[0];
      SQLChar var4 = getIDValueAsCHAR(var1.getUUID());
      var3 = this.exFactory.getIndexableRow(1);
      var3.setColumn(1, var4);
      var5.deleteRow(var2, var3, 2);
   }

   public void updateConglomerateDescriptor(ConglomerateDescriptor var1, long var2, TransactionController var4) throws StandardException {
      ConglomerateDescriptor[] var5 = new ConglomerateDescriptor[]{var1};
      this.updateConglomerateDescriptor(var5, var2, var4);
   }

   public void updateSystemSchemaAuthorization(String var1, TransactionController var2) throws StandardException {
      this.updateSchemaAuth("SYS", var1, var2);
      this.updateSchemaAuth("SYSIBM", var1, var2);
      this.updateSchemaAuth("SYSCAT", var1, var2);
      this.updateSchemaAuth("SYSFUN", var1, var2);
      this.updateSchemaAuth("SYSPROC", var1, var2);
      this.updateSchemaAuth("SYSSTAT", var1, var2);
      this.updateSchemaAuth("NULLID", var1, var2);
      this.updateSchemaAuth("SQLJ", var1, var2);
      this.updateSchemaAuth("SYSCS_DIAG", var1, var2);
      this.updateSchemaAuth("SYSCS_UTIL", var1, var2);
      this.resetDatabaseOwner(var2);
   }

   public void updateSchemaAuth(String var1, String var2, TransactionController var3) throws StandardException {
      TabInfoImpl var6 = this.coreInfo[3];
      SQLVarchar var5 = new SQLVarchar(var1);
      ExecIndexRow var4 = this.exFactory.getIndexableRow(1);
      var4.setColumn(1, var5);
      SYSSCHEMASRowFactory var7 = (SYSSCHEMASRowFactory)var6.getCatalogRowFactory();
      ExecRow var8 = var7.makeEmptyRow();
      var8.setColumn(3, new SQLVarchar(var2));
      boolean[] var9 = new boolean[]{false, false};
      int[] var10 = new int[]{3};
      var6.updateRow(var4, (ExecRow)var8, 0, var9, var10, var3);
   }

   public void updateConglomerateDescriptor(ConglomerateDescriptor[] var1, long var2, TransactionController var4) throws StandardException {
      ExecIndexRow var5 = null;
      TabInfoImpl var8 = this.coreInfo[0];
      SYSCONGLOMERATESRowFactory var9 = (SYSCONGLOMERATESRowFactory)var8.getCatalogRowFactory();
      boolean[] var10 = new boolean[]{false, false, false};

      for(int var11 = 0; var11 < var1.length; ++var11) {
         SQLChar var7 = getIDValueAsCHAR(var1[var11].getUUID());
         var5 = this.exFactory.getIndexableRow(1);
         var5.setColumn(1, var7);
         var1[var11].setConglomerateNumber(var2);
         ExecRow var6 = var9.makeRow(var1[var11], (TupleDescriptor)null);
         var8.updateRow(var5, (ExecRow)var6, 0, var10, (int[])null, var4);
      }

   }

   public List getDependentsDescriptorList(String var1) throws StandardException {
      List var2 = newSList();
      TabInfoImpl var4 = this.getNonCoreTI(6);
      SQLChar var3 = new SQLChar(var1);
      ExecIndexRow var5 = this.exFactory.getIndexableRow(1);
      var5.setColumn(1, var3);
      this.getDescriptorViaIndex(0, var5, (ScanQualifier[][])null, var4, (TupleDescriptor)null, var2, DependencyDescriptor.class, false);
      return var2;
   }

   public List getProvidersDescriptorList(String var1) throws StandardException {
      List var2 = newSList();
      TabInfoImpl var4 = this.getNonCoreTI(6);
      SQLChar var3 = new SQLChar(var1);
      ExecIndexRow var5 = this.exFactory.getIndexableRow(1);
      var5.setColumn(1, var3);
      this.getDescriptorViaIndex(1, var5, (ScanQualifier[][])null, var4, (TupleDescriptor)null, var2, DependencyDescriptor.class, false);
      return var2;
   }

   public List getAllDependencyDescriptorsList() throws StandardException {
      List var5 = newSList();
      TabInfoImpl var6 = this.getNonCoreTI(6);
      SYSDEPENDSRowFactory var7 = (SYSDEPENDSRowFactory)var6.getCatalogRowFactory();
      TransactionController var2 = this.getTransactionCompile();
      ExecRow var3 = var7.makeEmptyRow();
      ScanController var1 = var2.openScan(var6.getHeapConglomerate(), false, 0, 7, 4, (FormatableBitSet)null, (DataValueDescriptor[])null, 1, (Qualifier[][])null, (DataValueDescriptor[])null, -1);

      while(var1.fetchNext(var3.getRowArray())) {
         DependencyDescriptor var8 = (DependencyDescriptor)var7.buildDescriptor(var3, (TupleDescriptor)null, this);
         var5.add(var8);
      }

      var1.close();
      return var5;
   }

   public void dropStoredDependency(DependencyDescriptor var1, TransactionController var2) throws StandardException {
      ExecIndexRow var3 = null;
      UUID var4 = var1.getUUID();
      UUID var5 = var1.getProviderID();
      SQLChar var6 = getIDValueAsCHAR(var4);
      TabInfoImpl var7 = this.getNonCoreTI(6);
      var3 = this.exFactory.getIndexableRow(1);
      var3.setColumn(1, var6);
      DropDependencyFilter var8 = new DropDependencyFilter(var5);
      var7.deleteRows(var2, var3, 1, (Qualifier[][])null, var8, var3, -1, 0);
   }

   public void dropDependentsStoredDependencies(UUID var1, TransactionController var2) throws StandardException {
      this.dropDependentsStoredDependencies(var1, var2, true);
   }

   public void dropDependentsStoredDependencies(UUID var1, TransactionController var2, boolean var3) throws StandardException {
      ExecIndexRow var4 = null;
      TabInfoImpl var6 = this.getNonCoreTI(6);
      SQLChar var5 = getIDValueAsCHAR(var1);
      var4 = this.exFactory.getIndexableRow(1);
      var4.setColumn(1, var5);
      var6.deleteRow(var2, var4, 0, var3);
   }

   public UUIDFactory getUUIDFactory() {
      return this.uuidFactory;
   }

   public AliasDescriptor getAliasDescriptorForUDT(TransactionController var1, DataTypeDescriptor var2) throws StandardException {
      if (var1 == null) {
         var1 = this.getTransactionCompile();
      }

      if (var2 == null) {
         return null;
      } else {
         BaseTypeIdImpl var3 = var2.getTypeId().getBaseTypeId();
         if (!var3.isAnsiUDT()) {
            return null;
         } else {
            SchemaDescriptor var4 = this.getSchemaDescriptor(var3.getSchemaName(), var1, true);
            AliasDescriptor var5 = this.getAliasDescriptor(var4.getUUID().toString(), var3.getUnqualifiedName(), 'A');
            return var5;
         }
      }
   }

   public AliasDescriptor getAliasDescriptor(UUID var1) throws StandardException {
      TabInfoImpl var3 = this.getNonCoreTI(7);
      SQLChar var2 = getIDValueAsCHAR(var1);
      ExecIndexRow var4 = this.exFactory.getIndexableRow(1);
      var4.setColumn(1, var2);
      return (AliasDescriptor)this.getDescriptorViaIndex(1, var4, (ScanQualifier[][])null, var3, (TupleDescriptor)null, (List)null, AliasDescriptor.class, false);
   }

   public AliasDescriptor getAliasDescriptor(String var1, String var2, char var3) throws StandardException {
      TabInfoImpl var6 = this.getNonCoreTI(7);
      SQLVarchar var4 = new SQLVarchar(var2);
      char[] var7 = new char[]{var3};
      SQLChar var5 = new SQLChar(new String(var7));
      ExecIndexRow var8 = this.exFactory.getIndexableRow(3);
      var8.setColumn(1, new SQLChar(var1));
      var8.setColumn(2, var4);
      var8.setColumn(3, var5);
      return (AliasDescriptor)this.getDescriptorViaIndex(0, var8, (ScanQualifier[][])null, var6, (TupleDescriptor)null, (List)null, AliasDescriptor.class, false);
   }

   public List getRoutineList(String var1, String var2, char var3) throws StandardException {
      ArrayList var4 = new ArrayList(1);
      if (var1.equals("c013800d-00fb-2642-07ec-000000134f30") && var3 == 'F') {
         for(int var17 = 0; var17 < SYSFUN_FUNCTIONS.length; ++var17) {
            String[] var6 = SYSFUN_FUNCTIONS[var17];
            String var7 = var6[0];
            if (var7.equals(var2)) {
               AliasDescriptor var8 = this.sysfunDescriptors[var17];
               if (var8 == null) {
                  TypeDescriptor var9 = DataTypeDescriptor.getBuiltInDataTypeDescriptor(var6[1]).getCatalogType();
                  boolean var10 = Boolean.valueOf(var6[4]);
                  boolean var11 = Boolean.valueOf(var6[5]);
                  int var12 = var6.length - 6;
                  TypeDescriptor[] var13 = new TypeDescriptor[var12];
                  String[] var14 = new String[var12];
                  int[] var15 = new int[var12];

                  for(int var16 = 0; var16 < var12; ++var16) {
                     var13[var16] = DataTypeDescriptor.getBuiltInDataTypeDescriptor(var6[6 + var16]).getCatalogType();
                     var14[var16] = "P" + (var16 + 1);
                     var15[var16] = 1;
                  }

                  RoutineAliasInfo var18 = new RoutineAliasInfo(var6[3], var12, var14, var13, var15, 0, (short)0, (short)3, var10, var11, false, false, var9);
                  var8 = new AliasDescriptor(this, this.uuidFactory.createUUID(), var7, this.uuidFactory.recreateUUID(var1), var6[2], 'F', 'F', true, var18, (String)null);
                  this.sysfunDescriptors[var17] = var8;
               }

               var4.add(var8);
            }
         }

         return var4;
      } else {
         AliasDescriptor var5 = this.getAliasDescriptor(var1, var2, var3);
         if (var5 != null) {
            var4.add(var5);
         }

         return var4;
      }
   }

   public void dropAliasDescriptor(AliasDescriptor var1, TransactionController var2) throws StandardException {
      TabInfoImpl var3 = this.getNonCoreTI(7);
      char[] var4 = new char[]{var1.getNameSpace()};
      ExecIndexRow var5 = this.exFactory.getIndexableRow(3);
      var5.setColumn(1, getIDValueAsCHAR(var1.getSchemaUUID()));
      var5.setColumn(2, new SQLVarchar(var1.getDescriptorName()));
      var5.setColumn(3, new SQLChar(new String(var4)));
      var3.deleteRow(var2, var5, 0);
   }

   public void updateUser(UserDescriptor var1, TransactionController var2) throws StandardException {
      TabInfoImpl var4 = this.getNonCoreTI(22);
      ExecIndexRow var3 = this.exFactory.getIndexableRow(1);
      var3.setColumn(1, new SQLVarchar(var1.getUserName()));
      ExecRow var5 = var4.getCatalogRowFactory().makeRow(var1, (TupleDescriptor)null);
      boolean[] var6 = new boolean[]{false};
      int[] var7 = new int[]{2, 3, 4};
      var4.updateRow(var3, (ExecRow)var5, 0, var6, var7, var2);
   }

   public UserDescriptor getUser(String var1) throws StandardException {
      this.dictionaryVersion.checkVersion(210, "NATIVE AUTHENTICATION");
      TabInfoImpl var3 = this.getNonCoreTI(22);
      ExecIndexRow var2 = this.exFactory.getIndexableRow(1);
      var2.setColumn(1, new SQLVarchar(var1));
      return (UserDescriptor)this.getDescriptorViaIndex(0, var2, (ScanQualifier[][])null, var3, (TupleDescriptor)null, (List)null, UserDescriptor.class, false);
   }

   public void dropUser(String var1, TransactionController var2) throws StandardException {
      TabInfoImpl var3 = this.getNonCoreTI(22);
      ExecIndexRow var4 = this.exFactory.getIndexableRow(1);
      var4.setColumn(1, new SQLVarchar(var1));
      var3.deleteRow(var2, var4, 0);
   }

   private void loadDictionaryTables(TransactionController var1, Properties var2) throws StandardException {
      this.loadCatalogs(this.coreInfo);
      this.dictionaryVersion = (DD_Version)var1.getProperty("DataDictionaryVersion");
      boolean var3 = PropertyUtil.nativeAuthenticationEnabled(var2);
      if (var3) {
         this.dictionaryVersion.checkVersion(210, "NATIVE AUTHENTICATION");
      }

      this.resetDatabaseOwner(var1);
      this.softwareVersion.upgradeIfNeeded(this.dictionaryVersion, var1, var2);
   }

   public void resetDatabaseOwner(TransactionController var1) throws StandardException {
      SchemaDescriptor var2 = this.locateSchemaRow("SYSIBM", var1);
      this.authorizationDatabaseOwner = var2.getAuthorizationId();
      this.systemSchemaDesc.setAuthorizationId(this.authorizationDatabaseOwner);
      this.sysIBMSchemaDesc.setAuthorizationId(this.authorizationDatabaseOwner);
      this.systemUtilSchemaDesc.setAuthorizationId(this.authorizationDatabaseOwner);
   }

   private void loadCatalogs(TabInfoImpl[] var1) throws StandardException {
      for(TabInfoImpl var5 : var1) {
         int var3 = var5.getNumberOfIndexes();

         for(int var4 = 0; var4 < var3; ++var4) {
            this.initSystemIndexVariables(var5, var4);
         }
      }

   }

   protected void createDictionaryTables(Properties var1, TransactionController var2, DataDescriptorGenerator var3) throws StandardException {
      this.systemSchemaDesc = this.newSystemSchemaDesc("SYS", "8000000d-00d0-fd77-3ed8-000a0a0b1900");

      for(int var4 = 0; var4 < 4; ++var4) {
         TabInfoImpl var5 = this.coreInfo[var4];
         Properties var6 = var5.getCreateHeapProperties();
         var5.setHeapConglomerate(this.createConglomerate(var5.getTableName(), var2, var5.getCatalogRowFactory().makeEmptyRow(), var6));
         if (this.coreInfo[var4].getNumberOfIndexes() > 0) {
            this.bootStrapSystemIndexes(this.systemSchemaDesc, var2, var3, var5);
         }
      }

      for(int var8 = 0; var8 < 4; ++var8) {
         TabInfoImpl var11 = this.coreInfo[var8];
         this.addSystemTableToDictionary(var11, this.systemSchemaDesc, var2, var3);
      }

      var1.put("SystablesIdentifier", Long.toString(this.coreInfo[1].getHeapConglomerate()));
      TabInfoImpl var10002 = this.coreInfo[1];
      SYSTABLESRowFactory var10003 = (SYSTABLESRowFactory)this.coreInfo[1].getCatalogRowFactory();
      var1.put("SystablesIndex1Identifier", Long.toString(var10002.getIndexConglomerate(0)));
      var10002 = this.coreInfo[1];
      var10003 = (SYSTABLESRowFactory)this.coreInfo[1].getCatalogRowFactory();
      var1.put("SystablesIndex2Identifier", Long.toString(var10002.getIndexConglomerate(1)));
      var1.put("SyscolumnsIdentifier", Long.toString(this.coreInfo[2].getHeapConglomerate()));
      var10002 = this.coreInfo[2];
      SYSCOLUMNSRowFactory var23 = (SYSCOLUMNSRowFactory)this.coreInfo[2].getCatalogRowFactory();
      var1.put("SyscolumnsIndex1Identifier", Long.toString(var10002.getIndexConglomerate(0)));
      var10002 = this.coreInfo[2];
      var23 = (SYSCOLUMNSRowFactory)this.coreInfo[2].getCatalogRowFactory();
      var1.put("SyscolumnsIndex2Identifier", Long.toString(var10002.getIndexConglomerate(1)));
      var1.put("SysconglomeratesIdentifier", Long.toString(this.coreInfo[0].getHeapConglomerate()));
      var10002 = this.coreInfo[0];
      SYSCONGLOMERATESRowFactory var25 = (SYSCONGLOMERATESRowFactory)this.coreInfo[0].getCatalogRowFactory();
      var1.put("SysconglomeratesIndex1Identifier", Long.toString(var10002.getIndexConglomerate(0)));
      var10002 = this.coreInfo[0];
      var25 = (SYSCONGLOMERATESRowFactory)this.coreInfo[0].getCatalogRowFactory();
      var1.put("SysconglomeratesIndex2Identifier", Long.toString(var10002.getIndexConglomerate(1)));
      var10002 = this.coreInfo[0];
      var25 = (SYSCONGLOMERATESRowFactory)this.coreInfo[0].getCatalogRowFactory();
      var1.put("SysconglomeratesIndex3Identifier", Long.toString(var10002.getIndexConglomerate(2)));
      var1.put("SysschemasIdentifier", Long.toString(this.coreInfo[3].getHeapConglomerate()));
      var10002 = this.coreInfo[3];
      SYSSCHEMASRowFactory var28 = (SYSSCHEMASRowFactory)this.coreInfo[3].getCatalogRowFactory();
      var1.put("SysschemasIndex1Identifier", Long.toString(var10002.getIndexConglomerate(0)));
      var10002 = this.coreInfo[3];
      var28 = (SYSSCHEMASRowFactory)this.coreInfo[3].getCatalogRowFactory();
      var1.put("SysschemasIndex2Identifier", Long.toString(var10002.getIndexConglomerate(1)));
      this.sysIBMSchemaDesc = this.addSystemSchema("SYSIBM", "c013800d-00f8-5b53-28a9-00000019ed88", var2);

      for(int var9 = 0; var9 < NUM_NONCORE; ++var9) {
         int var12 = var9 + 4;
         boolean var13 = var12 == 15;
         TabInfoImpl var7 = this.getNonCoreTIByNumber(var12);
         this.makeCatalog(var7, var13 ? this.sysIBMSchemaDesc : this.systemSchemaDesc, var2);
         if (var13) {
            this.populateSYSDUMMY1(var2);
         }

         this.clearNoncoreTable(var9);
      }

      this.addDescriptor(this.systemSchemaDesc, (TupleDescriptor)null, 3, false, var2);
      this.addSystemSchema("SYSCAT", "c013800d-00fb-2641-07ec-000000134f30", var2);
      this.addSystemSchema("SYSFUN", "c013800d-00fb-2642-07ec-000000134f30", var2);
      this.addSystemSchema("SYSPROC", "c013800d-00fb-2643-07ec-000000134f30", var2);
      this.addSystemSchema("SYSSTAT", "c013800d-00fb-2644-07ec-000000134f30", var2);
      this.addSystemSchema("NULLID", "c013800d-00fb-2647-07ec-000000134f30", var2);
      this.addSystemSchema("SQLJ", "c013800d-00fb-2648-07ec-000000134f30", var2);
      this.addSystemSchema("SYSCS_DIAG", "c013800d-00fb-2646-07ec-000000134f30", var2);
      this.addSystemSchema("SYSCS_UTIL", "c013800d-00fb-2649-07ec-000000134f30", var2);
      SchemaDescriptor var10 = new SchemaDescriptor(this, "APP", "APP", this.uuidFactory.recreateUUID("80000000-00d2-b38f-4cda-000a0a412c00"), false);
      this.addDescriptor(var10, (TupleDescriptor)null, 3, false, var2);
   }

   private SchemaDescriptor addSystemSchema(String var1, String var2, TransactionController var3) throws StandardException {
      SchemaDescriptor var4 = new SchemaDescriptor(this, var1, this.authorizationDatabaseOwner, this.uuidFactory.recreateUUID(var2), true);
      this.addDescriptor(var4, (TupleDescriptor)null, 3, false, var3);
      return var4;
   }

   protected void upgradeMakeCatalog(TransactionController var1, int var2) throws StandardException {
      TabInfoImpl var3;
      if (var2 >= 4) {
         var3 = this.getNonCoreTIByNumber(var2);
      } else {
         var3 = this.coreInfo[var2];
      }

      this.makeCatalog(var3, var2 == 15 ? this.getSysIBMSchemaDescriptor() : this.getSystemSchemaDescriptor(), var1);
   }

   protected void upgradeJarStorage(TransactionController var1) throws StandardException {
      TabInfoImpl var2 = this.getNonCoreTI(12);
      SYSFILESRowFactory var3 = (SYSFILESRowFactory)var2.getCatalogRowFactory();
      ExecRow var4 = var3.makeEmptyRow();
      ScanController var5 = var1.openScan(var2.getHeapConglomerate(), false, 0, 7, 4, (FormatableBitSet)null, (DataValueDescriptor[])null, 0, (Qualifier[][])null, (DataValueDescriptor[])null, 0);
      HashMap var6 = new HashMap();

      try {
         while(var5.fetchNext(var4.getRowArray())) {
            FileInfoDescriptor var7 = (FileInfoDescriptor)var3.buildDescriptor(var4, (TupleDescriptor)null, this);
            var6.put(var7.getSchemaDescriptor().getSchemaName(), (Object)null);
            JarUtil.upgradeJar(var1, var7);
         }
      } finally {
         var5.close();
      }

      Iterator var11 = var6.keySet().iterator();
      FileResource var8 = var1.getFileHandler();

      while(var11.hasNext()) {
         char var10001 = File.separatorChar;
         var8.removeJarDir("jar" + var10001 + (String)var11.next());
      }

   }

   private void makeCatalog(TabInfoImpl var1, SchemaDescriptor var2, TransactionController var3) throws StandardException {
      DataDescriptorGenerator var4 = this.getDataDescriptorGenerator();
      Properties var5 = var1.getCreateHeapProperties();
      var1.setHeapConglomerate(this.createConglomerate(var1.getTableName(), var3, var1.getCatalogRowFactory().makeEmptyRowForCurrentVersion(), var5));
      if (var1.getNumberOfIndexes() > 0) {
         this.bootStrapSystemIndexes(var2, var3, var4, var1);
      }

      this.addSystemTableToDictionary(var1, var2, var3, var4);
   }

   public void upgradeFixSystemColumnDefinition(CatalogRowFactory var1, int var2, TransactionController var3) throws StandardException {
      SystemColumn[] var5 = var1.buildColumnList();
      SchemaDescriptor var6 = this.getSystemSchemaDescriptor();
      TableDescriptor var7 = this.getTableDescriptor(var1.getCatalogName(), var6, var3);
      SystemColumn var4 = var5[var2 - 1];
      ColumnDescriptor var8 = this.makeColumnDescriptor(var4, var2, var7);
      String var9 = var8.getColumnName();
      int[] var10 = new int[]{4};
      this.updateColumnDescriptor(var8, var7.getUUID(), var9, var10, var3);
   }

   public void upgrade_addColumns(CatalogRowFactory var1, int[] var2, TransactionController var3) throws StandardException {
      SystemColumn[] var6 = var1.buildColumnList();
      ExecRow var7 = var1.makeEmptyRowForCurrentVersion();
      int var8 = var2.length;
      SchemaDescriptor var9 = this.getSystemSchemaDescriptor();
      TableDescriptor var10;
      long var11;
      if (var1 instanceof SYSTABLESRowFactory) {
         var10 = this.dataDescriptorGenerator.newTableDescriptor("SYSTABLES", var9, 0, 'R');
         var10.setUUID(this.getUUIDForCoreTable("SYSTABLES", var9.getUUID().toString(), var3));
         var11 = this.coreInfo[1].getHeapConglomerate();
      } else if (var1 instanceof SYSCOLUMNSRowFactory) {
         var10 = this.dataDescriptorGenerator.newTableDescriptor("SYSCOLUMNS", var9, 0, 'R');
         var10.setUUID(this.getUUIDForCoreTable("SYSCOLUMNS", var9.getUUID().toString(), var3));
         var11 = this.coreInfo[2].getHeapConglomerate();
      } else {
         var10 = this.getTableDescriptor(var1.getCatalogName(), var9, var3);
         var11 = var10.getHeapConglomerateId();
      }

      this.widenConglomerate(var7, var2, var11, var3);
      ColumnDescriptor[] var13 = new ColumnDescriptor[var8];

      for(int var14 = 0; var14 < var8; ++var14) {
         int var4 = var2[var14];
         SystemColumn var5 = var6[var4 - 1];
         var13[var14] = this.makeColumnDescriptor(var5, var4, var10);
      }

      this.addDescriptorArray(var13, var10, 2, false, var3);
   }

   void upgrade_SYSCOLUMNS_AUTOINCCYCLE(TransactionController var1) throws StandardException {
      TabInfoImpl var2 = this.coreInfo[2];
      this.upgrade_addColumns(var2.getCatalogRowFactory(), new int[]{10}, var1);
   }

   public void upgrade_addInvisibleColumns(CatalogRowFactory var1, int[] var2, TransactionController var3) throws StandardException {
      ExecRow var4 = var1.makeEmptyRowForCurrentVersion();
      SchemaDescriptor var5 = this.getSystemSchemaDescriptor();
      long var6 = this.getTableDescriptor(var1.getCatalogName(), var5, var3).getHeapConglomerateId();
      this.widenConglomerate(var4, var2, var6, var3);
   }

   private void widenConglomerate(ExecRow var1, int[] var2, long var3, TransactionController var5) throws StandardException {
      for(int var8 : var2) {
         int var9 = var8 - 1;
         var5.addColumnToConglomerate(var3, var9, var1.getColumn(var8), 0);
      }

   }

   private UUID getUUIDForCoreTable(String var1, String var2, TransactionController var3) throws StandardException {
      TabInfoImpl var9 = this.coreInfo[1];
      SYSTABLESRowFactory var10 = (SYSTABLESRowFactory)var9.getCatalogRowFactory();
      ExecRow var5 = this.exFactory.getValueRow(1);
      SQLVarchar var7 = new SQLVarchar(var1);
      SQLChar var6 = new SQLChar(var2);
      ExecIndexRow var11 = this.exFactory.getIndexableRow(2);
      var11.setColumn(1, var7);
      var11.setColumn(2, var6);
      ConglomerateController var4 = var3.openConglomerate(var9.getHeapConglomerate(), false, 0, 6, 4);
      ExecIndexRow var12 = var10.buildEmptyIndexRow(0, var4.newRowLocationTemplate());
      ScanController var8 = var3.openScan(var9.getIndexConglomerate(0), false, 0, 6, 4, (FormatableBitSet)null, var11.getRowArray(), 1, (ScanQualifier[][])null, var11.getRowArray(), -1);
      if (var8.fetchNext(var12.getRowArray())) {
         RowLocation var13 = (RowLocation)var12.getColumn(var12.nColumns());
         var5.setColumn(1, new SQLChar());
         FormatableBitSet var14 = new FormatableBitSet(1);
         var14.set(0);
         var4.fetch(var13, var5.getRowArray(), (FormatableBitSet)null);
      }

      var8.close();
      var4.close();
      return this.uuidFactory.recreateUUID(var5.getColumn(1).toString());
   }

   void upgrade_initSystemTableCols(TransactionController var1, boolean var2, int var3, FormatableBitSet var4, DataValueDescriptor[] var5) throws StandardException {
      TabInfoImpl var6 = var2 ? this.coreInfo[var3] : this.getNonCoreTIByNumber(var3);
      if (!var2) {
         this.faultInTabInfo(var6);
      }

      ScanController var7 = var1.openScan(var6.getHeapConglomerate(), false, 4, 7, 4, RowUtil.EMPTY_ROW_BITSET, (DataValueDescriptor[])null, 0, (Qualifier[][])null, (DataValueDescriptor[])null, 0);

      while(var7.next()) {
         var7.replace(var5, var4);
      }

      var7.close();
   }

   private void bootStrapSystemIndexes(SchemaDescriptor var1, TransactionController var2, DataDescriptorGenerator var3, TabInfoImpl var4) throws StandardException {
      ConglomerateDescriptor[] var5 = new ConglomerateDescriptor[var4.getNumberOfIndexes()];

      for(int var6 = 0; var6 < var4.getNumberOfIndexes(); ++var6) {
         var5[var6] = this.bootstrapOneIndex(var1, var2, var3, var4, var6, var4.getHeapConglomerate());
      }

      for(int var7 = 0; var7 < var4.getNumberOfIndexes(); ++var7) {
         this.addDescriptor(var5[var7], var1, 0, false, var2);
      }

   }

   public RowLocation[] computeAutoincRowLocations(TransactionController var1, TableDescriptor var2) throws StandardException {
      if (!var2.tableHasAutoincrement()) {
         return null;
      } else {
         int var3 = var2.getNumberOfColumns();
         RowLocation[] var4 = new RowLocation[var3];

         for(int var5 = 0; var5 < var3; ++var5) {
            ColumnDescriptor var6 = var2.getColumnDescriptor(var5 + 1);
            if (var6.isAutoincrement()) {
               var4[var5] = this.computeRowLocation(var1, var2, var6.getColumnName());
            }
         }

         return var4;
      }
   }

   public NumberDataValue getSetAutoincrementValue(RowLocation var1, TransactionController var2, boolean var3, NumberDataValue var4, boolean var5) throws StandardException {
      byte var6 = 7;
      TabInfoImpl var7 = this.coreInfo[2];
      ConglomerateController var8 = null;
      SYSCOLUMNSRowFactory var9 = (SYSCOLUMNSRowFactory)var7.getCatalogRowFactory();
      ExecRow var10 = var9.makeEmptyRow();
      FormatableBitSet var11 = new FormatableBitSet(10);
      var11.set(var6 - 1);
      var11.set(var6);
      var11.set(var6 + 1);

      NumberDataValue var21;
      try {
         var8 = var2.openConglomerate(var7.getHeapConglomerate(), false, 4 | (var5 ? 0 : 128), 6, 4);
         var8.fetch(var1, var10.getRowArray(), var11, var5);
         NumberDataValue var13 = (NumberDataValue)var10.getColumn(var6);
         long var14 = var13.getLong();
         if (var3) {
            var21 = (NumberDataValue)var10.getColumn(var6 + 2);
            var13 = var13.plus(var13, var21, var13);
            var10.setColumn(var6, var13);
            FormatableBitSet var17 = new FormatableBitSet(10);
            var17.set(var6 - 1);
            var8.replace(var1, var10.getRowArray(), var17);
         }

         if (var4 == null) {
            var13.setValue(var14);
            var21 = var13;
            return var21;
         }

         var4.setValue(var14);
         var21 = var4;
      } finally {
         if (var8 != null) {
            var8.close();
         }

      }

      return var21;
   }

   private ConglomerateDescriptor bootstrapOneIndex(SchemaDescriptor var1, TransactionController var2, DataDescriptorGenerator var3, TabInfoImpl var4, int var5, long var6) throws StandardException {
      CatalogRowFactory var16 = var4.getCatalogRowFactory();
      this.initSystemIndexVariables(var4, var5);
      IndexRowGenerator var17 = var4.getIndexRowGenerator(var5);
      int var12 = var4.getIndexColumnCount(var5);
      boolean var8 = var4.isIndexUnique(var5);
      ExecIndexRow var11 = var17.getIndexRowTemplate();
      ExecRow var10 = var16.makeEmptyRowForCurrentVersion();
      ConglomerateController var9 = var2.openConglomerate(var6, false, 0, 6, 4);
      RowLocation var15 = var9.newRowLocationTemplate();
      var9.close();
      var17.getIndexRow(var10, var15, var11, (FormatableBitSet)null);
      Properties var19 = var4.getCreateIndexProperties(var5);
      var19.put("baseConglomerateId", Long.toString(var6));
      var19.put("nUniqueColumns", Integer.toString(var8 ? var12 : var12 + 1));
      var19.put("rowLocationColumn", Integer.toString(var12));
      var19.put("nKeyFields", Integer.toString(var12 + 1));
      long var13 = var2.createConglomerate("BTREE", var11.getRowArray(), (ColumnOrdering[])null, (int[])null, var19, 0);
      ConglomerateDescriptor var18 = var3.newConglomerateDescriptor(var13, var16.getIndexName(var5), true, var17, false, var16.getCanonicalIndexUUID(var5), var16.getCanonicalTableUUID(), var1.getUUID());
      var4.setIndexConglomerate(var18);
      return var18;
   }

   private void initSystemIndexVariables(TabInfoImpl var1, int var2) throws StandardException {
      int var3 = var1.getIndexColumnCount(var2);
      int[] var4 = new int[var3];

      for(int var5 = 0; var5 < var3; ++var5) {
         var4[var5] = var1.getBaseColumnPosition(var2, var5);
      }

      boolean[] var7 = new boolean[var4.length];

      for(int var6 = 0; var6 < var4.length; ++var6) {
         var7[var6] = true;
      }

      Object var8 = null;
      IndexRowGenerator var9 = new IndexRowGenerator("BTREE", var1.isIndexUnique(var2), false, false, false, var4, var7, var4.length);
      var1.setIndexRowGenerator(var2, var9);
   }

   protected void populateSYSDUMMY1(TransactionController var1) throws StandardException {
      TabInfoImpl var2 = this.getNonCoreTI(15);
      ExecRow var3 = var2.getCatalogRowFactory().makeRow((TupleDescriptor)null, (TupleDescriptor)null);
      var2.insertRow(var3, var1);
   }

   public void clearCaches() throws StandardException {
      this.clearCaches(true);
   }

   public void clearCaches(boolean var1) throws StandardException {
      this.nameTdCache.cleanAll();
      this.nameTdCache.ageOut();
      this.OIDTdCache.cleanAll();
      this.OIDTdCache.ageOut();
      if (var1) {
         this.clearSequenceCaches();
      }

      if (this.spsNameCache != null) {
         this.spsNameCache.cleanAll();
         this.spsNameCache.ageOut();
         this.spsIdHash.clear();
      }

   }

   public void clearSequenceCaches() throws StandardException {
      this.sequenceGeneratorCache.cleanAll();
      this.sequenceGeneratorCache.ageOut();
   }

   private void addSystemTableToDictionary(TabInfoImpl var1, SchemaDescriptor var2, TransactionController var3, DataDescriptorGenerator var4) throws StandardException {
      CatalogRowFactory var5 = var1.getCatalogRowFactory();
      String var6 = var1.getTableName();
      long var7 = var1.getHeapConglomerate();
      SystemColumn[] var9 = var5.buildColumnList();
      UUID var10 = var5.getCanonicalHeapUUID();
      String var11 = var5.getCanonicalHeapName();
      int var14 = var9.length;
      TableDescriptor var12 = var4.newTableDescriptor(var6, var2, 1, 'R');
      var12.setUUID(var5.getCanonicalTableUUID());
      this.addDescriptor(var12, var2, 1, false, var3);
      UUID var13 = var12.getUUID();
      ConglomerateDescriptor var16 = var4.newConglomerateDescriptor(var7, var11, false, (IndexRowGenerator)null, false, var10, var13, var2.getUUID());
      this.addDescriptor(var16, var2, 0, false, var3);
      ColumnDescriptor[] var17 = new ColumnDescriptor[var14];

      for(int var18 = 0; var18 < var14; ++var18) {
         SystemColumn var15 = var9[var18];
         var17[var18] = this.makeColumnDescriptor(var15, var18 + 1, var12);
      }

      this.addDescriptorArray(var17, var12, 2, false, var3);
      ColumnDescriptorList var20 = var12.getColumnDescriptorList();

      for(int var19 = 0; var19 < var14; ++var19) {
         var20.add(var17[var19]);
      }

   }

   private ColumnDescriptor makeColumnDescriptor(SystemColumn var1, int var2, TableDescriptor var3) throws StandardException {
      return new ColumnDescriptor(var1.getName(), var2, var1.getType(), (DataValueDescriptor)null, (DefaultInfo)null, var3, (UUID)null, 0L, 0L, false);
   }

   private long createConglomerate(String var1, TransactionController var2, ExecRow var3, Properties var4) throws StandardException {
      long var5 = var2.createConglomerate("heap", var3.getRowArray(), (ColumnOrdering[])null, (int[])null, var4, 0);
      return var5;
   }

   private static SQLChar getIDValueAsCHAR(UUID var0) {
      String var1 = var0.toString();
      return new SQLChar(var1);
   }

   public void initializeCatalogInfo() throws StandardException {
      this.initializeCoreInfo();
      this.initializeNoncoreInfo();
   }

   private void initializeCoreInfo() throws StandardException {
      TabInfoImpl[] var1 = this.coreInfo = new TabInfoImpl[4];
      UUIDFactory var2 = this.uuidFactory;
      var1[1] = new TabInfoImpl(new SYSTABLESRowFactory(var2, this.exFactory, this.dvf));
      var1[2] = new TabInfoImpl(new SYSCOLUMNSRowFactory(this, var2, this.exFactory, this.dvf));
      var1[0] = new TabInfoImpl(new SYSCONGLOMERATESRowFactory(var2, this.exFactory, this.dvf));
      var1[3] = new TabInfoImpl(new SYSSCHEMASRowFactory(var2, this.exFactory, this.dvf));
   }

   private void initializeNoncoreInfo() {
      this.noncoreInfo = new TabInfoImpl[NUM_NONCORE];
   }

   public TransactionController getTransactionCompile() throws StandardException {
      if (this.bootingTC != null) {
         return this.bootingTC;
      } else {
         LanguageConnectionContext var1 = getLCC();
         return var1.getTransactionCompile();
      }
   }

   public TransactionController getTransactionExecute() throws StandardException {
      if (this.bootingTC != null) {
         return this.bootingTC;
      } else {
         LanguageConnectionContext var1 = getLCC();
         return var1.getTransactionExecute();
      }
   }

   private TupleDescriptor getDescriptorViaIndex(int var1, ExecIndexRow var2, ScanQualifier[][] var3, TabInfoImpl var4, TupleDescriptor var5, List var6, Class var7, boolean var8) throws StandardException {
      TransactionController var9 = this.getTransactionCompile();
      return this.getDescriptorViaIndexMinion(var1, var2, var3, var4, var5, var6, var7, var8, 4, var9);
   }

   private TupleDescriptor getDescriptorViaIndex(int var1, ExecIndexRow var2, ScanQualifier[][] var3, TabInfoImpl var4, TupleDescriptor var5, List var6, Class var7, boolean var8, int var9, TransactionController var10) throws StandardException {
      if (var10 == null) {
         var10 = this.getTransactionCompile();
      }

      return this.getDescriptorViaIndexMinion(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10);
   }

   private TupleDescriptor getDescriptorViaIndexMinion(int var1, ExecIndexRow var2, ScanQualifier[][] var3, TabInfoImpl var4, TupleDescriptor var5, List var6, Class var7, boolean var8, int var9, TransactionController var10) throws StandardException {
      CatalogRowFactory var11 = var4.getCatalogRowFactory();
      TupleDescriptor var17 = null;
      ExecRow var14 = var11.makeEmptyRow();
      ConglomerateController var12 = var10.openConglomerate(var4.getHeapConglomerate(), false, 0, 6, var9);
      ScanController var16 = var10.openScan(var4.getIndexConglomerate(var1), false, var8 ? 4 : 0, 6, var9, (FormatableBitSet)null, var2.getRowArray(), 1, var3, var2.getRowArray(), -1);

      while(true) {
         ExecIndexRow var13 = getIndexRowFromHeapRow(var4.getIndexRowGenerator(var1), var12.newRowLocationTemplate(), var14);
         if (!var16.fetchNext(var13.getRowArray())) {
            break;
         }

         RowLocation var15 = (RowLocation)var13.getColumn(var13.nColumns());
         boolean var18 = false;

         try {
            var18 = var12.fetch(var15, var14.getRowArray(), (FormatableBitSet)null);
         } catch (RuntimeException var20) {
            throw var20;
         } catch (StandardException var21) {
            throw var21;
         }

         if (!var18 && var9 == 1) {
            var17 = null;
         } else {
            var17 = (TupleDescriptor)var7.cast(var11.buildDescriptor(var14, var5, this));
         }

         if (var6 == null) {
            break;
         }

         if (var17 != null) {
            var6.add(var17);
         }
      }

      var16.close();
      var12.close();
      return var17;
   }

   private void debugGenerateInfo(StringBuffer var1, TransactionController var2, ConglomerateController var3, TabInfoImpl var4, int var5) {
   }

   protected TupleDescriptor getDescriptorViaHeap(FormatableBitSet var1, ScanQualifier[][] var2, TabInfoImpl var3, TupleDescriptor var4, List var5, Class var6) throws StandardException {
      CatalogRowFactory var7 = var3.getCatalogRowFactory();
      TupleDescriptor var11 = null;
      TransactionController var10 = this.getTransactionCompile();
      ExecRow var8 = var7.makeEmptyRow();
      ScanController var9 = var10.openScan(var3.getHeapConglomerate(), false, 0, 7, 4, var1, (DataValueDescriptor[])null, 0, var2, (DataValueDescriptor[])null, 0);

      while(var9.fetchNext(var8.getRowArray())) {
         var11 = (TupleDescriptor)var6.cast(var7.buildDescriptor(var8, var4, this));
         if (var5 == null) {
            break;
         }

         var5.add(var11);
      }

      var9.close();
      return var11;
   }

   private TabInfoImpl getNonCoreTI(int var1) throws StandardException {
      TabInfoImpl var2 = this.getNonCoreTIByNumber(var1);
      this.faultInTabInfo(var2);
      return var2;
   }

   protected TabInfoImpl getNonCoreTIByNumber(int var1) throws StandardException {
      int var2 = var1 - 4;
      TabInfoImpl var3 = this.noncoreInfo[var2];
      if (var3 == null) {
         UUIDFactory var4 = this.uuidFactory;
         switch (var1) {
            case 4 -> var3 = new TabInfoImpl(new SYSCONSTRAINTSRowFactory(var4, this.exFactory, this.dvf));
            case 5 -> var3 = new TabInfoImpl(new SYSKEYSRowFactory(var4, this.exFactory, this.dvf));
            case 6 -> var3 = new TabInfoImpl(new SYSDEPENDSRowFactory(var4, this.exFactory, this.dvf));
            case 7 -> var3 = new TabInfoImpl(new SYSALIASESRowFactory(var4, this.exFactory, this.dvf));
            case 8 -> var3 = new TabInfoImpl(new SYSVIEWSRowFactory(var4, this.exFactory, this.dvf));
            case 9 -> var3 = new TabInfoImpl(new SYSCHECKSRowFactory(var4, this.exFactory, this.dvf));
            case 10 -> var3 = new TabInfoImpl(new SYSFOREIGNKEYSRowFactory(var4, this.exFactory, this.dvf));
            case 11 -> var3 = new TabInfoImpl(new SYSSTATEMENTSRowFactory(var4, this.exFactory, this.dvf));
            case 12 -> var3 = new TabInfoImpl(new SYSFILESRowFactory(var4, this.exFactory, this.dvf));
            case 13 -> var3 = new TabInfoImpl(new SYSTRIGGERSRowFactory(this, var4, this.exFactory, this.dvf));
            case 14 -> var3 = new TabInfoImpl(new SYSSTATISTICSRowFactory(var4, this.exFactory, this.dvf));
            case 15 -> var3 = new TabInfoImpl(new SYSDUMMY1RowFactory(var4, this.exFactory, this.dvf));
            case 16 -> var3 = new TabInfoImpl(new SYSTABLEPERMSRowFactory(var4, this.exFactory, this.dvf));
            case 17 -> var3 = new TabInfoImpl(new SYSCOLPERMSRowFactory(var4, this.exFactory, this.dvf));
            case 18 -> var3 = new TabInfoImpl(new SYSROUTINEPERMSRowFactory(var4, this.exFactory, this.dvf));
            case 19 -> var3 = new TabInfoImpl(new SYSROLESRowFactory(var4, this.exFactory, this.dvf));
            case 20 -> var3 = new TabInfoImpl(new SYSSEQUENCESRowFactory(var4, this.exFactory, this.dvf));
            case 21 -> var3 = new TabInfoImpl(new SYSPERMSRowFactory(var4, this.exFactory, this.dvf));
            case 22 -> var3 = new TabInfoImpl(new SYSUSERSRowFactory(var4, this.exFactory, this.dvf));
         }

         this.initSystemIndexVariables(var3);
         this.noncoreInfo[var2] = var3;
      }

      return var3;
   }

   protected void initSystemIndexVariables(TabInfoImpl var1) throws StandardException {
      int var2 = var1.getNumberOfIndexes();

      for(int var3 = 0; var3 < var2; ++var3) {
         this.initSystemIndexVariables(var1, var3);
      }

   }

   private void clearNoncoreTable(int var1) {
      this.noncoreInfo[var1] = null;
   }

   private void faultInTabInfo(TabInfoImpl var1) throws StandardException {
      if (!var1.isComplete()) {
         synchronized(var1) {
            if (!var1.isComplete()) {
               TableDescriptor var4 = this.getTableDescriptor(var1.getTableName(), this.getSystemSchemaDescriptor(), (TransactionController)null);
               if (var4 != null) {
                  Object var5 = null;
                  ConglomerateDescriptor[] var6 = var4.getConglomerateDescriptors();

                  for(int var7 = 0; var7 < var6.length; ++var7) {
                     ConglomerateDescriptor var12 = var6[var7];
                     if (!var12.isIndex()) {
                        var1.setHeapConglomerate(var12.getConglomerateNumber());
                        break;
                     }
                  }

                  int var2 = var1.getCatalogRowFactory().getNumIndexes();
                  if (var2 != 0) {
                     Object var13 = null;
                     int var8 = 0;

                     for(int var9 = 0; var9 < var6.length; ++var9) {
                        ConglomerateDescriptor var14 = var6[var9];
                        if (var14.isIndex()) {
                           var1.setIndexConglomerate(var14);
                           ++var8;
                        }
                     }

                  }
               }
            }
         }
      }
   }

   public static ExecIndexRow getIndexRowFromHeapRow(IndexRowGenerator var0, RowLocation var1, ExecRow var2) throws StandardException {
      ExecIndexRow var3 = var0.getIndexRowTemplate();
      var0.getIndexRow(var2, var1, var3, (FormatableBitSet)null);
      return var3;
   }

   public int getEngineType() {
      return this.engineType;
   }

   public long getSYSCOLUMNSHeapConglomerateNumber() {
      return this.coreInfo[2].getHeapConglomerate();
   }

   void addSYSCOLUMNSIndex2Property(TransactionController var1, long var2) {
      this.startupParameters.put("SyscolumnsIndex2Identifier", Long.toString(var2));
   }

   private long getBootParameter(Properties var1, String var2, boolean var3) throws StandardException {
      String var4 = var1.getProperty(var2);
      if (var4 == null) {
         if (!var3) {
            return -1L;
         } else {
            throw StandardException.newException("XCY03.S", new Object[]{var2});
         }
      } else {
         try {
            return Long.parseLong(var4);
         } catch (NumberFormatException var6) {
            throw StandardException.newException("XCY00.S", new Object[]{var2, var4});
         }
      }
   }

   public String getSystemSQLName() {
      int var1 = this.systemSQLNameSeed++;
      if (var1 < 0) {
         var1 = 0;
         this.systemSQLNameSeed = var1;
      }

      String var2 = String.format("%010d", var1);
      return "SQL" + var2 + "-" + this.uuidFactory.createUUID().toString();
   }

   private static String twoDigits(int var0) {
      String var1;
      if (var0 < 10) {
         var1 = "0" + var0;
      } else {
         int var2 = Integer.toString(var0).length();
         var1 = Integer.toString(var0).substring(var2 - 2);
      }

      return var1;
   }

   public void setAutoincrementValue(TransactionController var1, UUID var2, String var3, long var4, boolean var6) throws StandardException {
      TabInfoImpl var7 = this.coreInfo[2];
      ExecIndexRow var8 = null;
      var8 = this.exFactory.getIndexableRow(2);
      var8.setColumn(1, getIDValueAsCHAR(var2));
      var8.setColumn(2, new SQLChar(var3));
      SYSCOLUMNSRowFactory var9 = (SYSCOLUMNSRowFactory)var7.getCatalogRowFactory();
      ExecRow var10 = var9.makeEmptyRow();
      boolean[] var11 = new boolean[2];

      for(int var12 = 0; var12 < 2; ++var12) {
         var11[var12] = false;
      }

      int[] var16 = new int[]{7};
      if (var6) {
         ExecRow var13 = var7.getRow(var1, var8, 0);
         NumberDataValue var14 = (NumberDataValue)var13.getColumn(9);
         var4 += var14.getLong();
      }

      var10.setColumn(7, new SQLLongint(var4));
      var7.updateRow(var8, (ExecRow)var10, 0, var11, var16, var1);
   }

   private RowLocation computeRowLocation(TransactionController var1, TableDescriptor var2, String var3) throws StandardException {
      TabInfoImpl var4 = this.coreInfo[2];
      ExecIndexRow var5 = null;
      UUID var6 = var2.getUUID();
      var5 = this.exFactory.getIndexableRow(2);
      var5.setColumn(1, getIDValueAsCHAR(var6));
      var5.setColumn(2, new SQLChar(var3));
      return var4.getRowLocation(var1, var5, 0);
   }

   public void computeSequenceRowLocation(TransactionController var1, String var2, RowLocation[] var3, SequenceDescriptor[] var4) throws StandardException {
      TabInfoImpl var5 = this.getNonCoreTI(20);
      ExecIndexRow var6 = null;
      var6 = this.exFactory.getIndexableRow(1);
      var6.setColumn(1, new SQLChar(var2));
      var3[0] = var5.getRowLocation(var1, var6, 0);
      var4[0] = (SequenceDescriptor)this.getDescriptorViaIndex(0, var6, (ScanQualifier[][])null, var5, (TupleDescriptor)null, (List)null, SequenceDescriptor.class, false, 4, var1);
   }

   public boolean updateCurrentSequenceValue(TransactionController var1, RowLocation var2, boolean var3, Long var4, Long var5) throws StandardException {
      byte var6 = 5;
      FormatableBitSet var7 = new FormatableBitSet(10);
      TabInfoImpl var8 = this.getNonCoreTI(20);
      ConglomerateController var9 = null;
      SYSSEQUENCESRowFactory var10 = (SYSSEQUENCESRowFactory)var8.getCatalogRowFactory();
      ExecRow var11 = var10.makeEmptyRow();
      var7.set(var6 - 1);

      boolean var13;
      try {
         var9 = var1.openConglomerate(var8.getHeapConglomerate(), false, 4 | (var3 ? 0 : 128), 6, 4);
         boolean var12 = var9.fetch(var2, var11.getRowArray(), var7, var3);
         if (var12) {
            NumberDataValue var20 = (NumberDataValue)var11.getColumn(var6);
            SQLLongint var14;
            if (var4 == null) {
               var14 = new SQLLongint();
            } else {
               var14 = new SQLLongint(var4);
            }

            if (var4 != null && var14.compare(var20) != 0) {
               boolean var21 = false;
               return var21;
            }

            SQLLongint var15;
            if (var5 == null) {
               var15 = new SQLLongint();
            } else {
               var15 = new SQLLongint(var5);
            }

            var11.setColumn(var6, var15);
            var9.replace(var2, var11.getRowArray(), var7);
            boolean var16 = true;
            return var16;
         }

         var13 = false;
      } finally {
         if (var9 != null) {
            var9.close();
         }

      }

      return var13;
   }

   public void getCurrentValueAndAdvance(String var1, NumberDataValue var2) throws StandardException {
      SequenceUpdater var3 = null;

      try {
         var3 = (SequenceUpdater)this.sequenceGeneratorCache.find(var1);
         var3.getCurrentValueAndAdvance(var2);
      } finally {
         if (var3 != null) {
            this.sequenceGeneratorCache.release(var3);
         }

      }

   }

   public Long peekAtIdentity(String var1, String var2) throws StandardException {
      LanguageConnectionContext var3 = getLCC();
      TransactionController var4 = var3.getTransactionExecute();
      SchemaDescriptor var5 = this.getSchemaDescriptor(var1, var4, true);
      TableDescriptor var6 = this.getTableDescriptor(var2, var5, var4);
      if (var6 == null) {
         throw StandardException.newException("X0X81.S", new Object[]{"TABLE", var1 + "." + var2});
      } else {
         return this.peekAtSequence("SYS", TableDescriptor.makeSequenceName(var6.getUUID()));
      }
   }

   public Long peekAtSequence(String var1, String var2) throws StandardException {
      String var3 = this.getSequenceID(var1, var2);
      if (var3 == null) {
         throw StandardException.newException("X0X81.S", new Object[]{"SEQUENCE", var1 + "." + var2});
      } else {
         SequenceUpdater var4 = null;

         Long var5;
         try {
            var4 = (SequenceUpdater)this.sequenceGeneratorCache.find(var3);
            var5 = var4.peekAtCurrentValue();
         } finally {
            if (var4 != null) {
               this.sequenceGeneratorCache.release(var4);
            }

         }

         return var5;
      }
   }

   public BulkInsertCounter getBulkInsertCounter(String var1, boolean var2) throws StandardException {
      SequenceUpdater var3 = null;

      SequenceUpdater.BulkInsertUpdater var4;
      try {
         var3 = (SequenceUpdater)this.sequenceGeneratorCache.find(var1);
         var4 = var3.getBulkInsertUpdater(var2);
      } finally {
         if (var3 != null) {
            this.sequenceGeneratorCache.release(var3);
         }

      }

      return var4;
   }

   public void flushBulkInsertCounter(String var1, BulkInsertCounter var2) throws StandardException {
      SequenceUpdater var3 = null;

      try {
         var3 = (SequenceUpdater)this.sequenceGeneratorCache.find(var1);
         var3.reset(var2.peekAtCurrentValue());
      } finally {
         if (var3 != null) {
            this.sequenceGeneratorCache.release(var3);
         }

      }

   }

   public RowLocation getRowLocationTemplate(LanguageConnectionContext var1, TableDescriptor var2) throws StandardException {
      ConglomerateController var4 = null;
      TransactionController var5 = var1.getTransactionCompile();
      long var6 = var2.getHeapConglomerateId();
      var4 = var5.openConglomerate(var6, false, 0, 6, 2);

      RowLocation var3;
      try {
         var3 = var4.newRowLocationTemplate();
      } finally {
         var4.close();
      }

      return var3;
   }

   public void addTableDescriptorToOtherCache(TableDescriptor var1, Cacheable var2) throws StandardException {
      CacheManager var3 = var2 instanceof OIDTDCacheable ? this.nameTdCache : this.OIDTdCache;
      TDCacheable var5 = null;
      Object var4;
      if (var3 == this.nameTdCache) {
         var4 = new TableKey(var1.getSchemaDescriptor().getUUID(), var1.getName());
      } else {
         var4 = var1.getUUID();
      }

      try {
         var5 = (TDCacheable)var3.create(var4, var1);
      } catch (StandardException var10) {
         if (!var10.getMessageId().equals("XBCA0.S")) {
            throw var10;
         }
      } finally {
         if (var5 != null) {
            var3.release(var5);
         }

      }

   }

   public void dropStatisticsDescriptors(UUID var1, UUID var2, TransactionController var3) throws StandardException {
      TabInfoImpl var4 = this.getNonCoreTI(14);
      SQLChar var5 = getIDValueAsCHAR(var1);
      ExecIndexRow var7;
      if (var2 != null) {
         var7 = this.exFactory.getIndexableRow(2);
         SQLChar var6 = getIDValueAsCHAR(var2);
         var7.setColumn(2, var6);
      } else {
         var7 = this.exFactory.getIndexableRow(1);
      }

      var7.setColumn(1, var5);
      var4.deleteRow(var3, var7, 0);
   }

   private static LanguageConnectionContext getLCC() {
      return (LanguageConnectionContext)getContextOrNull("LanguageConnectionContext");
   }

   private SchemaDescriptor newSystemSchemaDesc(String var1, String var2) {
      return new SchemaDescriptor(this, var1, this.authorizationDatabaseOwner, this.uuidFactory.recreateUUID(var2), true);
   }

   private SchemaDescriptor newDeclaredGlobalTemporaryTablesSchemaDesc(String var1) {
      return new SchemaDescriptor(this, var1, this.authorizationDatabaseOwner, (UUID)null, false);
   }

   public boolean checkVersion(int var1, String var2) throws StandardException {
      if (var1 == -1) {
         var1 = this.softwareVersion.majorVersionNumber;
      }

      return this.dictionaryVersion.checkVersion(var1, var2);
   }

   public boolean isReadOnlyUpgrade() {
      return this.readOnlyUpgrade;
   }

   void setReadOnlyUpgrade() {
      this.readOnlyUpgrade = true;
   }

   void createSystemSps(TransactionController var1) throws StandardException {
      this.createSPSSet(var1, false, this.getSystemSchemaDescriptor().getUUID());
      this.createSPSSet(var1, true, this.getSysIBMSchemaDescriptor().getUUID());
   }

   protected void createSPSSet(TransactionController var1, boolean var2, UUID var3) throws StandardException {
      Properties var4 = this.getQueryDescriptions(var2);
      Enumeration var5 = var4.keys();
      boolean var6 = true;

      while(var5.hasMoreElements()) {
         String var7 = (String)var5.nextElement();
         String var8 = var4.getProperty(var7);
         SPSDescriptor var9 = new SPSDescriptor(this, var7, this.getUUIDFactory().createUUID(), var3, var3, 'S', !var6, var8, !var6);
         this.addSPSDescriptor(var9, var1);
      }

   }

   private final UUID createSystemProcedureOrFunction(String var1, UUID var2, String[] var3, TypeDescriptor[] var4, int var5, int var6, short var7, boolean var8, boolean var9, TypeDescriptor var10, HashSet var11, TransactionController var12, String var13) throws StandardException {
      int var14 = 0;
      if (var3 != null) {
         var14 = var3.length;
      }

      int[] var15 = null;
      if (var14 != 0) {
         var15 = new int[var14];
         int var16 = var14 - var5;

         for(int var17 = 0; var17 < var16; ++var17) {
            var15[var17] = 1;
         }

         for(int var20 = 0; var20 < var5; ++var20) {
            var15[var16 + var20] = 4;
         }
      }

      RoutineAliasInfo var19 = new RoutineAliasInfo(var1, var14, var3, var4, var15, var6, (short)0, var7, var8, var9, false, true, var10);
      UUID var21 = this.getUUIDFactory().createUUID();
      AliasDescriptor var18 = new AliasDescriptor(this, var21, var1, var2, var13, (char)(var10 == null ? 'P' : 'F'), (char)(var10 == null ? 'P' : 'F'), false, var19, (String)null);
      this.addDescriptor(var18, (TupleDescriptor)null, 7, false, var12);
      var11.add(var1);
      return var21;
   }

   private final UUID createSystemProcedureOrFunction(String var1, UUID var2, String[] var3, TypeDescriptor[] var4, int var5, int var6, short var7, boolean var8, boolean var9, TypeDescriptor var10, HashSet var11, TransactionController var12) throws StandardException {
      UUID var13 = this.createSystemProcedureOrFunction(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, "org.apache.derby.catalog.SystemProcedures");
      return var13;
   }

   private final void create_SYSCS_procedures(TransactionController var1, HashSet var2) throws StandardException {
      TypeDescriptor var3 = DataTypeDescriptor.getCatalogType(12, 32672);
      UUID var4 = this.getSystemUtilSchemaDescriptor().getUUID();
      String[] var5 = new String[]{"KEY", "VALUE"};
      TypeDescriptor[] var6 = new TypeDescriptor[]{CATALOG_TYPE_SYSTEM_IDENTIFIER, DataTypeDescriptor.getCatalogType(12, 32672)};
      this.createSystemProcedureOrFunction("SYSCS_SET_DATABASE_PROPERTY", var4, var5, var6, 0, 0, (short)0, false, false, (TypeDescriptor)null, var2, var1);
      var5 = new String[]{"SCHEMANAME", "TABLENAME", "SEQUENTIAL"};
      var6 = new TypeDescriptor[]{CATALOG_TYPE_SYSTEM_IDENTIFIER, CATALOG_TYPE_SYSTEM_IDENTIFIER, TypeDescriptor.SMALLINT};
      this.createSystemProcedureOrFunction("SYSCS_COMPRESS_TABLE", var4, var5, var6, 0, 0, (short)0, false, false, (TypeDescriptor)null, var2, var1);
      this.createSystemProcedureOrFunction("SYSCS_CHECKPOINT_DATABASE", var4, (String[])null, (TypeDescriptor[])null, 0, 0, (short)2, false, false, (TypeDescriptor)null, var2, var1);
      this.createSystemProcedureOrFunction("SYSCS_FREEZE_DATABASE", var4, (String[])null, (TypeDescriptor[])null, 0, 0, (short)2, false, false, (TypeDescriptor)null, var2, var1);
      this.createSystemProcedureOrFunction("SYSCS_UNFREEZE_DATABASE", var4, (String[])null, (TypeDescriptor[])null, 0, 0, (short)2, false, false, (TypeDescriptor)null, var2, var1);
      var5 = new String[]{"BACKUPDIR"};
      var6 = new TypeDescriptor[]{DataTypeDescriptor.getCatalogType(12, 32672)};
      this.createSystemProcedureOrFunction("SYSCS_BACKUP_DATABASE", var4, var5, var6, 0, 0, (short)0, false, false, (TypeDescriptor)null, var2, var1);
      var5 = new String[]{"BACKUPDIR", "DELETE_ARCHIVED_LOG_FILES"};
      var6 = new TypeDescriptor[]{DataTypeDescriptor.getCatalogType(12, 32672), TypeDescriptor.SMALLINT};
      this.createSystemProcedureOrFunction("SYSCS_BACKUP_DATABASE_AND_ENABLE_LOG_ARCHIVE_MODE", var4, var5, var6, 0, 0, (short)0, false, false, (TypeDescriptor)null, var2, var1);
      var5 = new String[]{"DELETE_ARCHIVED_LOG_FILES"};
      var6 = new TypeDescriptor[]{TypeDescriptor.SMALLINT};
      this.createSystemProcedureOrFunction("SYSCS_DISABLE_LOG_ARCHIVE_MODE", var4, var5, var6, 0, 0, (short)0, false, false, (TypeDescriptor)null, var2, var1);
      var5 = new String[]{"ENABLE"};
      var6 = new TypeDescriptor[]{TypeDescriptor.SMALLINT};
      this.createSystemProcedureOrFunction("SYSCS_SET_RUNTIMESTATISTICS", var4, var5, var6, 0, 0, (short)2, false, false, (TypeDescriptor)null, var2, var1);
      var5 = new String[]{"ENABLE"};
      var6 = new TypeDescriptor[]{TypeDescriptor.SMALLINT};
      this.createSystemProcedureOrFunction("SYSCS_SET_STATISTICS_TIMING", var4, var5, var6, 0, 0, (short)2, false, false, (TypeDescriptor)null, var2, var1);
      var5 = new String[]{"KEY"};
      var6 = new TypeDescriptor[]{CATALOG_TYPE_SYSTEM_IDENTIFIER};
      this.createSystemProcedureOrFunction("SYSCS_GET_DATABASE_PROPERTY", var4, var5, var6, 0, 0, (short)1, false, false, DataTypeDescriptor.getCatalogType(12, 32672), var2, var1);
      var5 = new String[]{"SCHEMANAME", "TABLENAME"};
      var6 = new TypeDescriptor[]{CATALOG_TYPE_SYSTEM_IDENTIFIER, CATALOG_TYPE_SYSTEM_IDENTIFIER};
      this.createSystemProcedureOrFunction("SYSCS_CHECK_TABLE", var4, var5, var6, 0, 0, (short)1, false, false, TypeDescriptor.INTEGER, var2, var1);
      this.createSystemProcedureOrFunction("SYSCS_GET_RUNTIMESTATISTICS", var4, (String[])null, (TypeDescriptor[])null, 0, 0, (short)2, false, false, DataTypeDescriptor.getCatalogType(12, 32672), var2, var1);
      UUID var16 = this.getSchemaDescriptor("SQLJ", var1, true).getUUID();
      String[] var25 = new String[]{"URL", "JAR", "DEPLOY"};
      TypeDescriptor[] var7 = new TypeDescriptor[]{DataTypeDescriptor.getCatalogType(12, 256), CATALOG_TYPE_SYSTEM_IDENTIFIER, TypeDescriptor.INTEGER};
      this.createSystemProcedureOrFunction("INSTALL_JAR", var16, var25, var7, 0, 0, (short)0, false, false, (TypeDescriptor)null, var2, var1);
      var25 = new String[]{"URL", "JAR"};
      var7 = new TypeDescriptor[]{DataTypeDescriptor.getCatalogType(12, 256), CATALOG_TYPE_SYSTEM_IDENTIFIER};
      this.createSystemProcedureOrFunction("REPLACE_JAR", var16, var25, var7, 0, 0, (short)0, false, false, (TypeDescriptor)null, var2, var1);
      var25 = new String[]{"JAR", "UNDEPLOY"};
      var7 = new TypeDescriptor[]{CATALOG_TYPE_SYSTEM_IDENTIFIER, TypeDescriptor.INTEGER};
      this.createSystemProcedureOrFunction("REMOVE_JAR", var16, var25, var7, 0, 0, (short)0, false, false, (TypeDescriptor)null, var2, var1);
      var25 = new String[]{"schemaName", "tableName", "fileName", " columnDelimiter", "characterDelimiter", "codeset"};
      var7 = new TypeDescriptor[]{CATALOG_TYPE_SYSTEM_IDENTIFIER, CATALOG_TYPE_SYSTEM_IDENTIFIER, var3, DataTypeDescriptor.getCatalogType(1, 1), DataTypeDescriptor.getCatalogType(1, 1), CATALOG_TYPE_SYSTEM_IDENTIFIER};
      this.createSystemProcedureOrFunction("SYSCS_EXPORT_TABLE", var4, var25, var7, 0, 0, (short)1, false, false, (TypeDescriptor)null, var2, var1);
      var25 = new String[]{"selectStatement", "fileName", " columnDelimiter", "characterDelimiter", "codeset"};
      var7 = new TypeDescriptor[]{var3, var3, DataTypeDescriptor.getCatalogType(1, 1), DataTypeDescriptor.getCatalogType(1, 1), CATALOG_TYPE_SYSTEM_IDENTIFIER};
      this.createSystemProcedureOrFunction("SYSCS_EXPORT_QUERY", var4, var25, var7, 0, 0, (short)1, false, false, (TypeDescriptor)null, var2, var1);
      var25 = new String[]{"schemaName", "tableName", "fileName", " columnDelimiter", "characterDelimiter", "codeset", "replace"};
      var7 = new TypeDescriptor[]{CATALOG_TYPE_SYSTEM_IDENTIFIER, CATALOG_TYPE_SYSTEM_IDENTIFIER, var3, DataTypeDescriptor.getCatalogType(1, 1), DataTypeDescriptor.getCatalogType(1, 1), CATALOG_TYPE_SYSTEM_IDENTIFIER, TypeDescriptor.SMALLINT};
      this.createSystemProcedureOrFunction("SYSCS_IMPORT_TABLE", var4, var25, var7, 0, 0, (short)0, false, false, (TypeDescriptor)null, var2, var1);
      var25 = new String[]{"schemaName", "tableName", "insertColumnList", "columnIndexes", "fileName", " columnDelimiter", "characterDelimiter", "codeset", "replace"};
      var7 = new TypeDescriptor[]{CATALOG_TYPE_SYSTEM_IDENTIFIER, CATALOG_TYPE_SYSTEM_IDENTIFIER, var3, var3, var3, DataTypeDescriptor.getCatalogType(1, 1), DataTypeDescriptor.getCatalogType(1, 1), CATALOG_TYPE_SYSTEM_IDENTIFIER, TypeDescriptor.SMALLINT};
      this.createSystemProcedureOrFunction("SYSCS_IMPORT_DATA", var4, var25, var7, 0, 0, (short)0, false, false, (TypeDescriptor)null, var2, var1);
      var25 = new String[]{"schemaName", "tableName", "vtiName", "vtiArg"};
      var7 = new TypeDescriptor[]{CATALOG_TYPE_SYSTEM_IDENTIFIER, CATALOG_TYPE_SYSTEM_IDENTIFIER, var3, var3};
      this.createSystemProcedureOrFunction("SYSCS_BULK_INSERT", var4, var25, var7, 0, 0, (short)0, false, false, (TypeDescriptor)null, var2, var1);
      this.create_10_1_system_procedures(var1, var2, var4);
      this.create_10_2_system_procedures(var1, var2, var4);
      this.create_10_3_system_procedures(var1, var2);
      this.create_10_5_system_procedures(var1, var2);
      this.create_10_6_system_procedures(var1, var2);
      this.create_10_9_system_procedures(var1, var2);
      this.create_10_10_system_procedures(var1, var2);
      this.create_10_11_system_procedures(var1, var2);
      this.create_10_12_system_procedures(var1, var2);
      this.create_10_13_system_procedures(var1, var2);
   }

   protected final void create_SYSIBM_procedures(TransactionController var1, HashSet var2) throws StandardException {
      UUID var3 = this.getSysIBMSchemaDescriptor().getUUID();
      String[] var4 = new String[]{"SQLCODE", "SQLERRML", "SQLERRMC", "SQLERRP", "SQLERRD0", "SQLERRD1", "SQLERRD2", "SQLERRD3", "SQLERRD4", "SQLERRD5", "SQLWARN", "SQLSTATE", "FILE", "LOCALE", "MESSAGE", "RETURNCODE"};
      TypeDescriptor[] var5 = new TypeDescriptor[]{TypeDescriptor.INTEGER, TypeDescriptor.SMALLINT, DataTypeDescriptor.getCatalogType(12, 2400), DataTypeDescriptor.getCatalogType(1, 8), TypeDescriptor.INTEGER, TypeDescriptor.INTEGER, TypeDescriptor.INTEGER, TypeDescriptor.INTEGER, TypeDescriptor.INTEGER, TypeDescriptor.INTEGER, DataTypeDescriptor.getCatalogType(1, 11), DataTypeDescriptor.getCatalogType(1, 5), DataTypeDescriptor.getCatalogType(12, 50), DataTypeDescriptor.getCatalogType(1, 5), DataTypeDescriptor.getCatalogType(12, 2400), TypeDescriptor.INTEGER};
      this.createSystemProcedureOrFunction("SQLCAMESSAGE", var3, var4, var5, 2, 0, (short)1, false, false, (TypeDescriptor)null, var2, var1);
      var4 = new String[]{"CATALOGNAME", "SCHEMANAME", "PROCNAME", "OPTIONS"};
      var5 = new TypeDescriptor[]{CATALOG_TYPE_SYSTEM_IDENTIFIER, CATALOG_TYPE_SYSTEM_IDENTIFIER, CATALOG_TYPE_SYSTEM_IDENTIFIER, DataTypeDescriptor.getCatalogType(12, 4000)};
      this.createSystemProcedureOrFunction("SQLPROCEDURES", var3, var4, var5, 0, 1, (short)1, false, false, (TypeDescriptor)null, var2, var1);
      var4 = new String[]{"CATALOGNAME", "SCHEMANAME", "TABLENAME", "OPTIONS"};
      var5 = new TypeDescriptor[]{CATALOG_TYPE_SYSTEM_IDENTIFIER, CATALOG_TYPE_SYSTEM_IDENTIFIER, CATALOG_TYPE_SYSTEM_IDENTIFIER, DataTypeDescriptor.getCatalogType(12, 4000)};
      this.createSystemProcedureOrFunction("SQLTABLEPRIVILEGES", var3, var4, var5, 0, 1, (short)1, false, false, (TypeDescriptor)null, var2, var1);
      var4 = new String[]{"CATALOGNAME", "SCHEMANAME", "TABLENAME", "OPTIONS"};
      var5 = new TypeDescriptor[]{CATALOG_TYPE_SYSTEM_IDENTIFIER, CATALOG_TYPE_SYSTEM_IDENTIFIER, CATALOG_TYPE_SYSTEM_IDENTIFIER, DataTypeDescriptor.getCatalogType(12, 4000)};
      this.createSystemProcedureOrFunction("SQLPRIMARYKEYS", var3, var4, var5, 0, 1, (short)1, false, false, (TypeDescriptor)null, var2, var1);
      var4 = new String[]{"CATALOGNAME", "SCHEMANAME", "TABLENAME", "TABLETYPE", "OPTIONS"};
      var5 = new TypeDescriptor[]{CATALOG_TYPE_SYSTEM_IDENTIFIER, CATALOG_TYPE_SYSTEM_IDENTIFIER, CATALOG_TYPE_SYSTEM_IDENTIFIER, DataTypeDescriptor.getCatalogType(12, 4000), DataTypeDescriptor.getCatalogType(12, 4000)};
      this.createSystemProcedureOrFunction("SQLTABLES", var3, var4, var5, 0, 1, (short)1, false, false, (TypeDescriptor)null, var2, var1);
      var4 = new String[]{"CATALOGNAME", "SCHEMANAME", "PROCNAME", "PARAMNAME", "OPTIONS"};
      var5 = new TypeDescriptor[]{CATALOG_TYPE_SYSTEM_IDENTIFIER, CATALOG_TYPE_SYSTEM_IDENTIFIER, CATALOG_TYPE_SYSTEM_IDENTIFIER, CATALOG_TYPE_SYSTEM_IDENTIFIER, DataTypeDescriptor.getCatalogType(12, 4000)};
      this.createSystemProcedureOrFunction("SQLPROCEDURECOLS", var3, var4, var5, 0, 1, (short)1, false, false, (TypeDescriptor)null, var2, var1);
      var4 = new String[]{"CATALOGNAME", "SCHEMANAME", "TABLENAME", "COLUMNNAME", "OPTIONS"};
      var5 = new TypeDescriptor[]{CATALOG_TYPE_SYSTEM_IDENTIFIER, CATALOG_TYPE_SYSTEM_IDENTIFIER, CATALOG_TYPE_SYSTEM_IDENTIFIER, CATALOG_TYPE_SYSTEM_IDENTIFIER, DataTypeDescriptor.getCatalogType(12, 4000)};
      this.createSystemProcedureOrFunction("SQLCOLUMNS", var3, var4, var5, 0, 1, (short)1, false, false, (TypeDescriptor)null, var2, var1);
      var4 = new String[]{"CATALOGNAME", "SCHEMANAME", "TABLENAME", "COLUMNNAME", "OPTIONS"};
      var5 = new TypeDescriptor[]{CATALOG_TYPE_SYSTEM_IDENTIFIER, CATALOG_TYPE_SYSTEM_IDENTIFIER, CATALOG_TYPE_SYSTEM_IDENTIFIER, CATALOG_TYPE_SYSTEM_IDENTIFIER, DataTypeDescriptor.getCatalogType(12, 4000)};
      this.createSystemProcedureOrFunction("SQLCOLPRIVILEGES", var3, var4, var5, 0, 1, (short)1, false, false, (TypeDescriptor)null, var2, var1);
      var4 = new String[]{"CATALOGNAME", "SCHEMAPATTERN", "TYPENAMEPATTERN", "UDTTYPES", "OPTIONS"};
      var5 = new TypeDescriptor[]{CATALOG_TYPE_SYSTEM_IDENTIFIER, CATALOG_TYPE_SYSTEM_IDENTIFIER, CATALOG_TYPE_SYSTEM_IDENTIFIER, CATALOG_TYPE_SYSTEM_IDENTIFIER, DataTypeDescriptor.getCatalogType(12, 4000)};
      this.createSystemProcedureOrFunction("SQLUDTS", var3, var4, var5, 0, 1, (short)1, false, false, (TypeDescriptor)null, var2, var1);
      var4 = new String[]{"PKCATALOGNAME", "PKSCHEMANAME", "PKTABLENAME", "FKCATALOGNAME", "FKSCHEMANAME", "FKTABLENAME", "OPTIONS"};
      var5 = new TypeDescriptor[]{CATALOG_TYPE_SYSTEM_IDENTIFIER, CATALOG_TYPE_SYSTEM_IDENTIFIER, CATALOG_TYPE_SYSTEM_IDENTIFIER, CATALOG_TYPE_SYSTEM_IDENTIFIER, CATALOG_TYPE_SYSTEM_IDENTIFIER, CATALOG_TYPE_SYSTEM_IDENTIFIER, DataTypeDescriptor.getCatalogType(12, 4000)};
      this.createSystemProcedureOrFunction("SQLFOREIGNKEYS", var3, var4, var5, 0, 1, (short)1, false, false, (TypeDescriptor)null, var2, var1);
      var4 = new String[]{"COLTYPE", "CATALOGNAME", "SCHEMANAME", "TABLENAME", "SCOPE", "NULLABLE", "OPTIONS"};
      var5 = new TypeDescriptor[]{TypeDescriptor.SMALLINT, CATALOG_TYPE_SYSTEM_IDENTIFIER, CATALOG_TYPE_SYSTEM_IDENTIFIER, CATALOG_TYPE_SYSTEM_IDENTIFIER, TypeDescriptor.SMALLINT, TypeDescriptor.SMALLINT, DataTypeDescriptor.getCatalogType(12, 4000)};
      this.createSystemProcedureOrFunction("SQLSPECIALCOLUMNS", var3, var4, var5, 0, 1, (short)1, false, false, (TypeDescriptor)null, var2, var1);
      var4 = new String[]{"DATATYPE", "OPTIONS"};
      var5 = new TypeDescriptor[]{TypeDescriptor.SMALLINT, DataTypeDescriptor.getCatalogType(12, 4000)};
      this.createSystemProcedureOrFunction("SQLGETTYPEINFO", var3, var4, var5, 0, 1, (short)1, false, false, (TypeDescriptor)null, var2, var1);
      var4 = new String[]{"CATALOGNAME", "SCHEMANAME", "TABLENAME", "UNIQUE", "RESERVED", "OPTIONS"};
      var5 = new TypeDescriptor[]{CATALOG_TYPE_SYSTEM_IDENTIFIER, CATALOG_TYPE_SYSTEM_IDENTIFIER, CATALOG_TYPE_SYSTEM_IDENTIFIER, TypeDescriptor.SMALLINT, TypeDescriptor.SMALLINT, DataTypeDescriptor.getCatalogType(12, 4000)};
      this.createSystemProcedureOrFunction("SQLSTATISTICS", var3, var4, var5, 0, 1, (short)1, false, false, (TypeDescriptor)null, var2, var1);
      this.createSystemProcedureOrFunction("METADATA", var3, (String[])null, (TypeDescriptor[])null, 0, 1, (short)1, false, false, (TypeDescriptor)null, var2, var1);
   }

   public void grantPublicAccessToSystemRoutines(HashSet var1, TransactionController var2, String var3) throws StandardException {
      String var4 = this.getSystemUtilSchemaDescriptor().getUUID().toString();

      for(int var5 = 0; var5 < sysUtilProceduresWithPublicAccess.length; ++var5) {
         String var6 = sysUtilProceduresWithPublicAccess[var5];
         if (var1.contains(var6)) {
            this.grantPublicAccessToSystemRoutine(var4, var6, 'P', var2, var3);
         }
      }

      for(int var7 = 0; var7 < sysUtilFunctionsWithPublicAccess.length; ++var7) {
         String var8 = sysUtilFunctionsWithPublicAccess[var7];
         if (var1.contains(var8)) {
            this.grantPublicAccessToSystemRoutine(var4, var8, 'F', var2, var3);
         }
      }

   }

   private void grantPublicAccessToSystemRoutine(String var1, String var2, char var3, TransactionController var4, String var5) throws StandardException {
      AliasDescriptor var6 = this.getAliasDescriptor(var1, var2, var3);
      if (var6 != null) {
         UUID var7 = var6.getUUID();
         this.createRoutinePermPublicDescriptor(var7, var4, var5);
      }
   }

   void createRoutinePermPublicDescriptor(UUID var1, TransactionController var2, String var3) throws StandardException {
      RoutinePermsDescriptor var4 = new RoutinePermsDescriptor(this, "PUBLIC", var3, var1);
      this.addDescriptor(var4, (TupleDescriptor)null, 18, false, var2);
   }

   void create_10_1_system_procedures(TransactionController var1, HashSet var2, UUID var3) throws StandardException {
      String[] var4 = new String[]{"SCHEMANAME", "TABLENAME", "PURGE_ROWS", "DEFRAGMENT_ROWS", "TRUNCATE_END"};
      TypeDescriptor[] var5 = new TypeDescriptor[]{CATALOG_TYPE_SYSTEM_IDENTIFIER, CATALOG_TYPE_SYSTEM_IDENTIFIER, TypeDescriptor.SMALLINT, TypeDescriptor.SMALLINT, TypeDescriptor.SMALLINT};
      this.createSystemProcedureOrFunction("SYSCS_INPLACE_COMPRESS_TABLE", var3, var4, var5, 0, 0, (short)0, false, false, (TypeDescriptor)null, var2, var1);
   }

   void create_10_2_system_procedures(TransactionController var1, HashSet var2, UUID var3) throws StandardException {
      String[] var4 = new String[]{"BACKUPDIR"};
      TypeDescriptor[] var5 = new TypeDescriptor[]{DataTypeDescriptor.getCatalogType(12, 32672)};
      this.createSystemProcedureOrFunction("SYSCS_BACKUP_DATABASE_NOWAIT", var3, var4, var5, 0, 0, (short)0, false, false, (TypeDescriptor)null, var2, var1);
      var4 = new String[]{"BACKUPDIR", "DELETE_ARCHIVED_LOG_FILES"};
      var5 = new TypeDescriptor[]{DataTypeDescriptor.getCatalogType(12, 32672), TypeDescriptor.SMALLINT};
      this.createSystemProcedureOrFunction("SYSCS_BACKUP_DATABASE_AND_ENABLE_LOG_ARCHIVE_MODE_NOWAIT", var3, var4, var5, 0, 0, (short)0, false, false, (TypeDescriptor)null, var2, var1);
      var4 = new String[]{"CATALOGNAME", "SCHEMANAME", "FUNCNAME", "OPTIONS"};
      var5 = new TypeDescriptor[]{CATALOG_TYPE_SYSTEM_IDENTIFIER, CATALOG_TYPE_SYSTEM_IDENTIFIER, CATALOG_TYPE_SYSTEM_IDENTIFIER, DataTypeDescriptor.getCatalogType(12, 4000)};
      this.createSystemProcedureOrFunction("SQLFUNCTIONS", this.getSysIBMSchemaDescriptor().getUUID(), var4, var5, 0, 1, (short)1, false, false, (TypeDescriptor)null, var2, var1);
      var4 = new String[]{"CATALOGNAME", "SCHEMANAME", "FUNCNAME", "PARAMNAME", "OPTIONS"};
      var5 = new TypeDescriptor[]{CATALOG_TYPE_SYSTEM_IDENTIFIER, CATALOG_TYPE_SYSTEM_IDENTIFIER, CATALOG_TYPE_SYSTEM_IDENTIFIER, CATALOG_TYPE_SYSTEM_IDENTIFIER, DataTypeDescriptor.getCatalogType(12, 4000)};
      this.createSystemProcedureOrFunction("SQLFUNCTIONPARAMS", this.getSysIBMSchemaDescriptor().getUUID(), var4, var5, 0, 1, (short)1, false, false, (TypeDescriptor)null, var2, var1);
   }

   private void create_10_3_system_procedures_SYSIBM(TransactionController var1, HashSet var2) throws StandardException {
      UUID var3 = this.getSysIBMSchemaDescriptor().getUUID();
      Object var4 = null;
      Object var5 = null;
      this.createSystemProcedureOrFunction("CLOBCREATELOCATOR", var3, (String[])var4, (TypeDescriptor[])var5, 0, 0, (short)2, false, false, TypeDescriptor.INTEGER, var2, var1, "org.apache.derby.impl.jdbc.LOBStoredProcedure");
      String[] var6 = new String[]{"LOCATOR"};
      TypeDescriptor[] var21 = new TypeDescriptor[]{TypeDescriptor.INTEGER};
      this.createSystemProcedureOrFunction("CLOBRELEASELOCATOR", var3, var6, var21, 0, 0, (short)2, false, false, (TypeDescriptor)null, var2, var1, "org.apache.derby.impl.jdbc.LOBStoredProcedure");
      var6 = new String[]{"LOCATOR", "SEARCHSTR", "POS"};
      var21 = new TypeDescriptor[]{TypeDescriptor.INTEGER, DataTypeDescriptor.getCatalogType(12), DataTypeDescriptor.getCatalogType(-5)};
      this.createSystemProcedureOrFunction("CLOBGETPOSITIONFROMSTRING", var3, var6, var21, 0, 0, (short)2, false, false, DataTypeDescriptor.getCatalogType(-5), var2, var1, "org.apache.derby.impl.jdbc.LOBStoredProcedure");
      var6 = new String[]{"LOCATOR", "SEARCHLOCATOR", "POS"};
      var21 = new TypeDescriptor[]{TypeDescriptor.INTEGER, TypeDescriptor.INTEGER, DataTypeDescriptor.getCatalogType(-5)};
      this.createSystemProcedureOrFunction("CLOBGETPOSITIONFROMLOCATOR", var3, var6, var21, 0, 0, (short)2, false, false, DataTypeDescriptor.getCatalogType(-5), var2, var1, "org.apache.derby.impl.jdbc.LOBStoredProcedure");
      var6 = new String[]{"LOCATOR"};
      var21 = new TypeDescriptor[]{TypeDescriptor.INTEGER};
      this.createSystemProcedureOrFunction("CLOBGETLENGTH", var3, var6, var21, 0, 0, (short)2, false, false, DataTypeDescriptor.getCatalogType(-5), var2, var1, "org.apache.derby.impl.jdbc.LOBStoredProcedure");
      var6 = new String[]{"LOCATOR", "POS", "LEN"};
      var21 = new TypeDescriptor[]{TypeDescriptor.INTEGER, DataTypeDescriptor.getCatalogType(-5), TypeDescriptor.INTEGER};
      this.createSystemProcedureOrFunction("CLOBGETSUBSTRING", var3, var6, var21, 0, 0, (short)2, false, false, DataTypeDescriptor.getCatalogType(12, 10890), var2, var1, "org.apache.derby.impl.jdbc.LOBStoredProcedure");
      var6 = new String[]{"LOCATOR", "POS", "LEN", "REPLACESTR"};
      var21 = new TypeDescriptor[]{TypeDescriptor.INTEGER, DataTypeDescriptor.getCatalogType(-5), TypeDescriptor.INTEGER, DataTypeDescriptor.getCatalogType(12)};
      this.createSystemProcedureOrFunction("CLOBSETSTRING", var3, var6, var21, 0, 0, (short)2, false, false, (TypeDescriptor)null, var2, var1, "org.apache.derby.impl.jdbc.LOBStoredProcedure");
      var6 = new String[]{"LOCATOR", "LEN"};
      var21 = new TypeDescriptor[]{TypeDescriptor.INTEGER, DataTypeDescriptor.getCatalogType(-5)};
      this.createSystemProcedureOrFunction("CLOBTRUNCATE", var3, var6, var21, 0, 0, (short)2, false, false, (TypeDescriptor)null, var2, var1, "org.apache.derby.impl.jdbc.LOBStoredProcedure");
      Object var13 = null;
      var21 = null;
      this.createSystemProcedureOrFunction("BLOBCREATELOCATOR", var3, (String[])var13, var21, 0, 0, (short)2, false, false, TypeDescriptor.INTEGER, var2, var1, "org.apache.derby.impl.jdbc.LOBStoredProcedure");
      String[] var14 = new String[]{"LOCATOR"};
      var21 = new TypeDescriptor[]{TypeDescriptor.INTEGER};
      this.createSystemProcedureOrFunction("BLOBRELEASELOCATOR", var3, var14, var21, 0, 0, (short)2, false, false, (TypeDescriptor)null, var2, var1, "org.apache.derby.impl.jdbc.LOBStoredProcedure");
      var14 = new String[]{"LOCATOR", "SEARCHBYTES", "POS"};
      var21 = new TypeDescriptor[]{TypeDescriptor.INTEGER, DataTypeDescriptor.getCatalogType(-3), DataTypeDescriptor.getCatalogType(-5)};
      this.createSystemProcedureOrFunction("BLOBGETPOSITIONFROMBYTES", var3, var14, var21, 0, 0, (short)2, false, false, DataTypeDescriptor.getCatalogType(-5), var2, var1, "org.apache.derby.impl.jdbc.LOBStoredProcedure");
      var14 = new String[]{"LOCATOR", "SEARCHLOCATOR", "POS"};
      var21 = new TypeDescriptor[]{TypeDescriptor.INTEGER, TypeDescriptor.INTEGER, DataTypeDescriptor.getCatalogType(-5)};
      this.createSystemProcedureOrFunction("BLOBGETPOSITIONFROMLOCATOR", var3, var14, var21, 0, 0, (short)2, false, false, DataTypeDescriptor.getCatalogType(-5), var2, var1, "org.apache.derby.impl.jdbc.LOBStoredProcedure");
      var14 = new String[]{"LOCATOR"};
      var21 = new TypeDescriptor[]{TypeDescriptor.INTEGER};
      this.createSystemProcedureOrFunction("BLOBGETLENGTH", var3, var14, var21, 0, 0, (short)2, false, false, DataTypeDescriptor.getCatalogType(-5), var2, var1, "org.apache.derby.impl.jdbc.LOBStoredProcedure");
      var14 = new String[]{"LOCATOR", "POS", "LEN"};
      var21 = new TypeDescriptor[]{TypeDescriptor.INTEGER, DataTypeDescriptor.getCatalogType(-5), TypeDescriptor.INTEGER};
      this.createSystemProcedureOrFunction("BLOBGETBYTES", var3, var14, var21, 0, 0, (short)2, false, false, DataTypeDescriptor.getCatalogType(-3, 32672), var2, var1, "org.apache.derby.impl.jdbc.LOBStoredProcedure");
      var14 = new String[]{"LOCATOR", "POS", "LEN", "REPLACEBYTES"};
      var21 = new TypeDescriptor[]{TypeDescriptor.INTEGER, DataTypeDescriptor.getCatalogType(-5), TypeDescriptor.INTEGER, DataTypeDescriptor.getCatalogType(-3)};
      this.createSystemProcedureOrFunction("BLOBSETBYTES", var3, var14, var21, 0, 0, (short)2, false, false, (TypeDescriptor)null, var2, var1, "org.apache.derby.impl.jdbc.LOBStoredProcedure");
      var14 = new String[]{"LOCATOR", "LEN"};
      var21 = new TypeDescriptor[]{TypeDescriptor.INTEGER, DataTypeDescriptor.getCatalogType(-5)};
      this.createSystemProcedureOrFunction("BLOBTRUNCATE", var3, var14, var21, 0, 0, (short)2, false, false, (TypeDescriptor)null, var2, var1, "org.apache.derby.impl.jdbc.LOBStoredProcedure");
   }

   void create_10_5_system_procedures(TransactionController var1, HashSet var2) throws StandardException {
      UUID var3 = this.getSystemUtilSchemaDescriptor().getUUID();
      String[] var4 = new String[]{"SCHEMANAME", "TABLENAME", "INDEXNAME"};
      TypeDescriptor[] var5 = new TypeDescriptor[]{CATALOG_TYPE_SYSTEM_IDENTIFIER, CATALOG_TYPE_SYSTEM_IDENTIFIER, CATALOG_TYPE_SYSTEM_IDENTIFIER};
      this.createSystemProcedureOrFunction("SYSCS_UPDATE_STATISTICS", var3, var4, var5, 0, 0, (short)0, false, false, (TypeDescriptor)null, var2, var1);
   }

   void create_10_6_system_procedures(TransactionController var1, HashSet var2) throws StandardException {
      UUID var3 = this.getSystemUtilSchemaDescriptor().getUUID();
      String[] var4 = new String[]{"ENABLE"};
      TypeDescriptor[] var5 = new TypeDescriptor[]{TypeDescriptor.INTEGER};
      this.createSystemProcedureOrFunction("SYSCS_SET_XPLAIN_MODE", var3, var4, var5, 0, 0, (short)2, false, false, (TypeDescriptor)null, var2, var1);
      this.createSystemProcedureOrFunction("SYSCS_GET_XPLAIN_MODE", var3, (String[])null, (TypeDescriptor[])null, 0, 0, (short)1, false, false, TypeDescriptor.INTEGER, var2, var1);
      var4 = new String[]{"SCHEMANAME"};
      var5 = new TypeDescriptor[]{CATALOG_TYPE_SYSTEM_IDENTIFIER};
      this.createSystemProcedureOrFunction("SYSCS_SET_XPLAIN_SCHEMA", var3, var4, var5, 0, 0, (short)0, false, false, (TypeDescriptor)null, var2, var1);
      this.createSystemProcedureOrFunction("SYSCS_GET_XPLAIN_SCHEMA", var3, (String[])null, (TypeDescriptor[])null, 0, 0, (short)1, false, false, CATALOG_TYPE_SYSTEM_IDENTIFIER, var2, var1);
   }

   void create_10_3_system_procedures(TransactionController var1, HashSet var2) throws StandardException {
      this.create_10_3_system_procedures_SYSCS_UTIL(var1, var2);
      this.create_10_3_system_procedures_SYSIBM(var1, var2);
   }

   void create_10_3_system_procedures_SYSCS_UTIL(TransactionController var1, HashSet var2) throws StandardException {
      UUID var3 = this.getSystemUtilSchemaDescriptor().getUUID();
      String[] var4 = new String[]{"schemaName", "tableName", "fileName", " columnDelimiter", "characterDelimiter", "codeset", "lobsFileName"};
      TypeDescriptor[] var5 = new TypeDescriptor[]{CATALOG_TYPE_SYSTEM_IDENTIFIER, CATALOG_TYPE_SYSTEM_IDENTIFIER, DataTypeDescriptor.getCatalogType(12, 32672), DataTypeDescriptor.getCatalogType(1, 1), DataTypeDescriptor.getCatalogType(1, 1), CATALOG_TYPE_SYSTEM_IDENTIFIER, DataTypeDescriptor.getCatalogType(12, 32672)};
      this.createSystemProcedureOrFunction("SYSCS_EXPORT_TABLE_LOBS_TO_EXTFILE", var3, var4, var5, 0, 0, (short)1, false, false, (TypeDescriptor)null, var2, var1);
      var4 = new String[]{"selectStatement", "fileName", " columnDelimiter", "characterDelimiter", "codeset", "lobsFileName"};
      var5 = new TypeDescriptor[]{DataTypeDescriptor.getCatalogType(12, 32672), DataTypeDescriptor.getCatalogType(12, 32672), DataTypeDescriptor.getCatalogType(1, 1), DataTypeDescriptor.getCatalogType(1, 1), CATALOG_TYPE_SYSTEM_IDENTIFIER, DataTypeDescriptor.getCatalogType(12, 32672)};
      this.createSystemProcedureOrFunction("SYSCS_EXPORT_QUERY_LOBS_TO_EXTFILE", var3, var4, var5, 0, 0, (short)1, false, false, (TypeDescriptor)null, var2, var1);
      var4 = new String[]{"schemaName", "tableName", "fileName", " columnDelimiter", "characterDelimiter", "codeset", "replace"};
      var5 = new TypeDescriptor[]{CATALOG_TYPE_SYSTEM_IDENTIFIER, CATALOG_TYPE_SYSTEM_IDENTIFIER, DataTypeDescriptor.getCatalogType(12, 32672), DataTypeDescriptor.getCatalogType(1, 1), DataTypeDescriptor.getCatalogType(1, 1), CATALOG_TYPE_SYSTEM_IDENTIFIER, TypeDescriptor.SMALLINT};
      this.createSystemProcedureOrFunction("SYSCS_IMPORT_TABLE_LOBS_FROM_EXTFILE", var3, var4, var5, 0, 0, (short)0, false, false, (TypeDescriptor)null, var2, var1);
      var4 = new String[]{"schemaName", "tableName", "insertColumnList", "columnIndexes", "fileName", " columnDelimiter", "characterDelimiter", "codeset", "replace"};
      var5 = new TypeDescriptor[]{CATALOG_TYPE_SYSTEM_IDENTIFIER, CATALOG_TYPE_SYSTEM_IDENTIFIER, DataTypeDescriptor.getCatalogType(12, 32672), DataTypeDescriptor.getCatalogType(12, 32672), DataTypeDescriptor.getCatalogType(12, 32672), DataTypeDescriptor.getCatalogType(1, 1), DataTypeDescriptor.getCatalogType(1, 1), CATALOG_TYPE_SYSTEM_IDENTIFIER, TypeDescriptor.SMALLINT};
      this.createSystemProcedureOrFunction("SYSCS_IMPORT_DATA_LOBS_FROM_EXTFILE", var3, var4, var5, 0, 0, (short)0, false, false, (TypeDescriptor)null, var2, var1);
      this.createSystemProcedureOrFunction("SYSCS_RELOAD_SECURITY_POLICY", var3, (String[])null, (TypeDescriptor[])null, 0, 0, (short)3, false, false, (TypeDescriptor)null, var2, var1);
      TypeDescriptor[] var9 = new TypeDescriptor[]{CATALOG_TYPE_SYSTEM_IDENTIFIER, CATALOG_TYPE_SYSTEM_IDENTIFIER};
      this.createSystemProcedureOrFunction("SYSCS_SET_USER_ACCESS", var3, new String[]{"USERNAME", "CONNECTIONPERMISSION"}, var9, 0, 0, (short)0, false, false, (TypeDescriptor)null, var2, var1);
      var9 = new TypeDescriptor[]{CATALOG_TYPE_SYSTEM_IDENTIFIER};
      this.createSystemProcedureOrFunction("SYSCS_GET_USER_ACCESS", var3, new String[]{"USERNAME"}, var9, 0, 0, (short)1, false, false, CATALOG_TYPE_SYSTEM_IDENTIFIER, var2, var1);
      this.createSystemProcedureOrFunction("SYSCS_EMPTY_STATEMENT_CACHE", var3, (String[])null, (TypeDescriptor[])null, 0, 0, (short)3, false, false, (TypeDescriptor)null, var2, var1);
   }

   void create_10_9_system_procedures(TransactionController var1, HashSet var2) throws StandardException {
      UUID var3 = this.getSystemUtilSchemaDescriptor().getUUID();
      String[] var4 = new String[]{"userName", "password"};
      TypeDescriptor[] var5 = new TypeDescriptor[]{CATALOG_TYPE_SYSTEM_IDENTIFIER, DataTypeDescriptor.getCatalogType(12, 32672)};
      this.createSystemProcedureOrFunction("SYSCS_CREATE_USER", var3, var4, var5, 0, 0, (short)0, false, false, (TypeDescriptor)null, var2, var1);
      var4 = new String[]{"userName", "password"};
      var5 = new TypeDescriptor[]{CATALOG_TYPE_SYSTEM_IDENTIFIER, DataTypeDescriptor.getCatalogType(12, 32672)};
      this.createSystemProcedureOrFunction("SYSCS_RESET_PASSWORD", var3, var4, var5, 0, 0, (short)0, false, false, (TypeDescriptor)null, var2, var1);
      var4 = new String[]{"password"};
      var5 = new TypeDescriptor[]{DataTypeDescriptor.getCatalogType(12, 32672)};
      this.createSystemProcedureOrFunction("SYSCS_MODIFY_PASSWORD", var3, var4, var5, 0, 0, (short)0, false, false, (TypeDescriptor)null, var2, var1);
      var4 = new String[]{"userName"};
      var5 = new TypeDescriptor[]{CATALOG_TYPE_SYSTEM_IDENTIFIER};
      this.createSystemProcedureOrFunction("SYSCS_DROP_USER", var3, var4, var5, 0, 0, (short)0, false, false, (TypeDescriptor)null, var2, var1);
      var4 = new String[]{"schemaName", "sequenceName"};
      var5 = new TypeDescriptor[]{CATALOG_TYPE_SYSTEM_IDENTIFIER, CATALOG_TYPE_SYSTEM_IDENTIFIER};
      this.createSystemProcedureOrFunction("SYSCS_PEEK_AT_SEQUENCE", var3, var4, var5, 0, 0, (short)1, false, false, DataTypeDescriptor.getCatalogType(-5), var2, var1);
      var4 = new String[]{"SCHEMANAME", "TABLENAME", "INDEXNAME"};
      var5 = new TypeDescriptor[]{CATALOG_TYPE_SYSTEM_IDENTIFIER, CATALOG_TYPE_SYSTEM_IDENTIFIER, CATALOG_TYPE_SYSTEM_IDENTIFIER};
      this.createSystemProcedureOrFunction("SYSCS_DROP_STATISTICS", var3, var4, var5, 0, 0, (short)0, false, false, (TypeDescriptor)null, var2, var1);
   }

   void create_10_10_system_procedures(TransactionController var1, HashSet var2) throws StandardException {
      UUID var3 = this.getSystemUtilSchemaDescriptor().getUUID();
      TypeDescriptor var4 = DataTypeDescriptor.getCatalogType(12, 32672);
      this.createSystemProcedureOrFunction("SYSCS_INVALIDATE_STORED_STATEMENTS", var3, (String[])null, (TypeDescriptor[])null, 0, 0, (short)3, false, false, (TypeDescriptor)null, var2, var1);
      String[] var5 = new String[]{"toolName", "register", "optionalArgs"};
      TypeDescriptor[] var6 = new TypeDescriptor[]{var4, DataTypeDescriptor.getCatalogType(16), var4};
      this.createSystemProcedureOrFunction("SYSCS_REGISTER_TOOL", var3, var5, var6, 0, 0, (short)0, false, true, (TypeDescriptor)null, var2, var1, "org.apache.derby.catalog.Java5SystemProcedures");
   }

   void create_10_11_system_procedures(TransactionController var1, HashSet var2) throws StandardException {
      UUID var3 = this.getSystemUtilSchemaDescriptor().getUUID();
      String[] var4 = new String[]{"schemaName", "tableName"};
      TypeDescriptor[] var5 = new TypeDescriptor[]{CATALOG_TYPE_SYSTEM_IDENTIFIER, CATALOG_TYPE_SYSTEM_IDENTIFIER};
      this.createSystemProcedureOrFunction("SYSCS_PEEK_AT_IDENTITY", var3, var4, var5, 0, 0, (short)1, false, false, DataTypeDescriptor.getCatalogType(-5), var2, var1);
   }

   void create_10_12_system_procedures(TransactionController var1, HashSet var2) throws StandardException {
      UUID var3 = this.getSystemUtilSchemaDescriptor().getUUID();
      this.createSystemProcedureOrFunction("SYSCS_GET_DATABASE_NAME", var3, (String[])null, (TypeDescriptor[])null, 0, 0, (short)1, false, false, DataTypeDescriptor.getCatalogType(12), var2, var1);
   }

   void create_10_13_system_procedures(TransactionController var1, HashSet var2) throws StandardException {
      UUID var3 = this.getSystemUtilSchemaDescriptor().getUUID();
      TypeDescriptor var4 = DataTypeDescriptor.getCatalogType(12, 32672);
      String[] var5 = new String[]{"schemaName", "tableName", "fileName", " columnDelimiter", "characterDelimiter", "codeset", "replace", "skip"};
      TypeDescriptor[] var6 = new TypeDescriptor[]{CATALOG_TYPE_SYSTEM_IDENTIFIER, CATALOG_TYPE_SYSTEM_IDENTIFIER, var4, DataTypeDescriptor.getCatalogType(1, 1), DataTypeDescriptor.getCatalogType(1, 1), CATALOG_TYPE_SYSTEM_IDENTIFIER, TypeDescriptor.SMALLINT, TypeDescriptor.SMALLINT};
      this.createSystemProcedureOrFunction("SYSCS_IMPORT_TABLE_BULK", var3, var5, var6, 0, 0, (short)0, false, false, (TypeDescriptor)null, var2, var1);
      var5 = new String[]{"schemaName", "tableName", "insertColumnList", "columnIndexes", "fileName", " columnDelimiter", "characterDelimiter", "codeset", "replace", "skip"};
      var6 = new TypeDescriptor[]{CATALOG_TYPE_SYSTEM_IDENTIFIER, CATALOG_TYPE_SYSTEM_IDENTIFIER, var4, var4, var4, DataTypeDescriptor.getCatalogType(1, 1), DataTypeDescriptor.getCatalogType(1, 1), CATALOG_TYPE_SYSTEM_IDENTIFIER, TypeDescriptor.SMALLINT, TypeDescriptor.SMALLINT};
      this.createSystemProcedureOrFunction("SYSCS_IMPORT_DATA_BULK", var3, var5, var6, 0, 0, (short)0, false, false, (TypeDescriptor)null, var2, var1);
   }

   private final synchronized Properties getQueryDescriptions(boolean var1) {
      this.spsSet = var1 ? "metadata_net.properties" : "/org/apache/derby/impl/jdbc/metadata.properties";
      return this.run();
   }

   public final Properties run() {
      Properties var1 = new Properties();

      try {
         InputStream var2 = this.getClass().getResourceAsStream(this.spsSet);
         var1.load(var2);
         var2.close();
      } catch (IOException var3) {
      }

      return var1;
   }

   private static List newSList() {
      return Collections.synchronizedList(new LinkedList());
   }

   public TablePermsDescriptor getTablePermissions(UUID var1, String var2) throws StandardException {
      TablePermsDescriptor var3 = new TablePermsDescriptor(this, var2, (String)null, var1);
      return (TablePermsDescriptor)this.getPermissions(var3);
   }

   public TablePermsDescriptor getTablePermissions(UUID var1) throws StandardException {
      TablePermsDescriptor var2 = new TablePermsDescriptor(this, var1);
      return this.getUncachedTablePermsDescriptor(var2);
   }

   private Object getPermissions(PermissionsDescriptor var1) throws StandardException {
      Cacheable var2 = this.getPermissionsCache().find(var1);
      if (var2 == null) {
         return null;
      } else {
         Object var3 = var2.getIdentity();
         this.getPermissionsCache().release(var2);
         return var3;
      }
   }

   public ColPermsDescriptor getColumnPermissions(UUID var1) throws StandardException {
      ColPermsDescriptor var2 = new ColPermsDescriptor(this, var1);
      return this.getUncachedColPermsDescriptor(var2);
   }

   public ColPermsDescriptor getColumnPermissions(UUID var1, int var2, boolean var3, String var4) throws StandardException {
      String var5 = var3 ? colPrivTypeMapForGrant[var2] : colPrivTypeMap[var2];
      ColPermsDescriptor var6 = new ColPermsDescriptor(this, var4, (String)null, var1, var5);
      return (ColPermsDescriptor)this.getPermissions(var6);
   }

   public ColPermsDescriptor getColumnPermissions(UUID var1, String var2, boolean var3, String var4) throws StandardException {
      ColPermsDescriptor var5 = new ColPermsDescriptor(this, var4, (String)null, var1, var2);
      return (ColPermsDescriptor)this.getPermissions(var5);
   }

   public RoutinePermsDescriptor getRoutinePermissions(UUID var1, String var2) throws StandardException {
      RoutinePermsDescriptor var3 = new RoutinePermsDescriptor(this, var2, (String)null, var1);
      return (RoutinePermsDescriptor)this.getPermissions(var3);
   }

   public RoutinePermsDescriptor getRoutinePermissions(UUID var1) throws StandardException {
      RoutinePermsDescriptor var2 = new RoutinePermsDescriptor(this, var1);
      return this.getUncachedRoutinePermsDescriptor(var2);
   }

   public boolean addRemovePermissionsDescriptor(boolean var1, PermissionsDescriptor var2, String var3, TransactionController var4) throws StandardException {
      int var5 = var2.getCatalogNumber();
      var2.setUUID((UUID)null);
      var2.setGrantee(var3);
      TabInfoImpl var6 = this.getNonCoreTI(var5);
      PermissionsCatalogRowFactory var7 = (PermissionsCatalogRowFactory)var6.getCatalogRowFactory();
      int var8 = var7.getPrimaryKeyIndexNumber();
      ConglomerateController var9 = var4.openConglomerate(var6.getHeapConglomerate(), false, 0, 6, 4);
      Object var10 = null;

      try {
         RowLocation var21 = var9.newRowLocationTemplate();
      } finally {
         var9.close();
         Object var20 = null;
      }

      ExecIndexRow var11 = var7.buildIndexKeyRow(var8, var2);
      ExecRow var12 = var6.getRow(var4, var11, var8);
      if (var12 == null) {
         if (!var1) {
            return false;
         }

         ExecRow var13 = var6.getCatalogRowFactory().makeRow(var2, (TupleDescriptor)null);
         var6.insertRow(var13, var4);
      } else {
         boolean[] var22 = new boolean[var12.nColumns()];
         boolean[] var14 = new boolean[var7.getNumIndexes()];
         int var15 = 0;
         if (var1) {
            var15 = var7.orPermissions(var12, var2, var22);
         } else {
            var15 = var7.removePermissions(var12, var2, var22);
         }

         if (var15 == 0) {
            return false;
         }

         if (!var1) {
            var7.setUUIDOfThePassedDescriptor(var12, var2);
         }

         if (var15 < 0) {
            var6.deleteRow(var4, var11, var8);
         } else if (var15 > 0) {
            int[] var16 = new int[var15];
            var15 = 0;

            for(int var17 = 0; var17 < var22.length; ++var17) {
               if (var22[var17]) {
                  var16[var15++] = var17 + 1;
               }
            }

            var6.updateRow(var11, var12, var8, var14, var16, var4);
         }
      }

      this.removePermEntryInCache(var2);
      return !var1;
   }

   TablePermsDescriptor getUncachedTablePermsDescriptor(TablePermsDescriptor var1) throws StandardException {
      return var1.getObjectID() == null ? (TablePermsDescriptor)this.getUncachedPermissionsDescriptor(16, 0, var1, TablePermsDescriptor.class) : (TablePermsDescriptor)this.getUncachedPermissionsDescriptor(16, 1, var1, TablePermsDescriptor.class);
   }

   ColPermsDescriptor getUncachedColPermsDescriptor(ColPermsDescriptor var1) throws StandardException {
      return var1.getObjectID() == null ? (ColPermsDescriptor)this.getUncachedPermissionsDescriptor(17, 0, var1, ColPermsDescriptor.class) : (ColPermsDescriptor)this.getUncachedPermissionsDescriptor(17, 1, var1, ColPermsDescriptor.class);
   }

   private PermissionsDescriptor getUncachedPermissionsDescriptor(int var1, int var2, PermissionsDescriptor var3, Class var4) throws StandardException {
      TabInfoImpl var5 = this.getNonCoreTI(var1);
      PermissionsCatalogRowFactory var6 = (PermissionsCatalogRowFactory)var5.getCatalogRowFactory();
      ExecIndexRow var7 = var6.buildIndexKeyRow(var2, var3);
      return (PermissionsDescriptor)this.getDescriptorViaIndex(var2, var7, (ScanQualifier[][])null, var5, (TupleDescriptor)null, (List)null, var4, false);
   }

   RoutinePermsDescriptor getUncachedRoutinePermsDescriptor(RoutinePermsDescriptor var1) throws StandardException {
      return var1.getObjectID() == null ? (RoutinePermsDescriptor)this.getUncachedPermissionsDescriptor(18, 0, var1, RoutinePermsDescriptor.class) : (RoutinePermsDescriptor)this.getUncachedPermissionsDescriptor(18, 1, var1, RoutinePermsDescriptor.class);
   }

   public String getVTIClass(TableDescriptor var1, boolean var2) throws StandardException {
      if ("SYSCS_DIAG".equals(var1.getSchemaName())) {
         return this.getBuiltinVTIClass(var1, var2);
      } else {
         String var3 = var1.getSchemaName();
         String var4 = var1.getDescriptorName();
         SchemaDescriptor var5 = this.getSchemaDescriptor(var1.getSchemaName(), (TransactionController)null, true);
         if (var5 != null) {
            AliasDescriptor var6 = this.getAliasDescriptor(var5.getUUID().toString(), var4, 'F');
            if (var6 != null && var6.isTableFunction()) {
               return var6.getJavaClassName();
            } else {
               throw StandardException.newException("42ZB4", new Object[]{var3, var4});
            }
         } else {
            return null;
         }
      }
   }

   public String getBuiltinVTIClass(TableDescriptor var1, boolean var2) throws StandardException {
      if ("SYSCS_DIAG".equals(var1.getSchemaName())) {
         String[][] var3 = var2 ? this.DIAG_VTI_TABLE_FUNCTION_CLASSES : this.DIAG_VTI_TABLE_CLASSES;

         for(int var4 = 0; var4 < var3.length; ++var4) {
            String[] var5 = var3[var4];
            if (var5[0].equals(var1.getDescriptorName())) {
               return var5[1];
            }
         }
      }

      return null;
   }

   public RoleGrantDescriptor getRoleGrantDescriptor(UUID var1) throws StandardException {
      TabInfoImpl var3 = this.getNonCoreTI(19);
      SQLChar var2 = getIDValueAsCHAR(var1);
      ExecIndexRow var4 = this.exFactory.getIndexableRow(1);
      var4.setColumn(1, var2);
      return (RoleGrantDescriptor)this.getDescriptorViaIndex(2, var4, (ScanQualifier[][])null, var3, (TupleDescriptor)null, (List)null, RoleGrantDescriptor.class, false);
   }

   public RoleGrantDescriptor getRoleDefinitionDescriptor(String var1) throws StandardException {
      TabInfoImpl var4 = this.getNonCoreTI(19);
      SQLVarchar var2 = new SQLVarchar(var1);
      SQLVarchar var3 = new SQLVarchar("Y");
      ExecIndexRow var5 = this.exFactory.getIndexableRow(2);
      var5.setColumn(1, var2);
      var5.setColumn(2, var3);
      return (RoleGrantDescriptor)this.getDescriptorViaIndex(1, var5, (ScanQualifier[][])null, var4, (TupleDescriptor)null, (List)null, RoleGrantDescriptor.class, false);
   }

   public RoleGrantDescriptor getRoleGrantDescriptor(String var1, String var2, String var3) throws StandardException {
      TabInfoImpl var7 = this.getNonCoreTI(19);
      SQLVarchar var4 = new SQLVarchar(var1);
      SQLVarchar var5 = new SQLVarchar(var2);
      SQLVarchar var6 = new SQLVarchar(var3);
      ExecIndexRow var8 = this.exFactory.getIndexableRow(3);
      var8.setColumn(1, var4);
      var8.setColumn(2, var5);
      var8.setColumn(3, var6);
      return (RoleGrantDescriptor)this.getDescriptorViaIndex(0, var8, (ScanQualifier[][])null, var7, (TupleDescriptor)null, (List)null, RoleGrantDescriptor.class, false);
   }

   public boolean existsGrantToAuthid(String var1, TransactionController var2) throws StandardException {
      return this.existsPermByGrantee(var1, var2, 16, 0, 1) || this.existsPermByGrantee(var1, var2, 17, 0, 1) || this.existsPermByGrantee(var1, var2, 18, 0, 1) || this.existsRoleGrantByGrantee(var1, var2);
   }

   private void dropJDBCMetadataSPSes(TransactionController var1) throws StandardException {
      for(SPSDescriptor var3 : this.getAllSPSDescriptors()) {
         SchemaDescriptor var4 = var3.getSchemaDescriptor();
         if (var4.isSystemSchema()) {
            this.dropSPSDescriptor(var3, var1);
            this.dropDependentsStoredDependencies(var3.getUUID(), var1);
         }
      }

   }

   public void updateMetadataSPSes(TransactionController var1) throws StandardException {
      this.dropJDBCMetadataSPSes(var1);
      this.createSystemSps(var1);
   }

   public void dropSequenceDescriptor(SequenceDescriptor var1, TransactionController var2) throws StandardException {
      TabInfoImpl var4 = this.getNonCoreTI(20);
      SQLChar var3 = getIDValueAsCHAR(var1.getUUID());
      ExecIndexRow var5 = this.exFactory.getIndexableRow(1);
      var5.setColumn(1, var3);
      var4.deleteRow(var2, var5, 0);
      this.dropSequenceID(var1);
   }

   public SequenceDescriptor getSequenceDescriptor(UUID var1) throws StandardException {
      TabInfoImpl var3 = this.getNonCoreTI(20);
      SQLChar var2 = getIDValueAsCHAR(var1);
      ExecIndexRow var4 = this.exFactory.getIndexableRow(1);
      var4.setColumn(1, var2);
      SequenceDescriptor var5 = (SequenceDescriptor)this.getDescriptorViaIndex(0, var4, (ScanQualifier[][])null, var3, (TupleDescriptor)null, (List)null, SequenceDescriptor.class, false);
      this.putSequenceID(var5);
      return var5;
   }

   public SequenceDescriptor getSequenceDescriptor(SchemaDescriptor var1, String var2) throws StandardException {
      TabInfoImpl var5 = this.getNonCoreTI(20);
      SQLVarchar var4 = new SQLVarchar(var2);
      SQLChar var3 = getIDValueAsCHAR(var1.getUUID());
      ExecIndexRow var6 = this.exFactory.getIndexableRow(2);
      var6.setColumn(1, var3);
      var6.setColumn(2, var4);
      SequenceDescriptor var7 = (SequenceDescriptor)this.getDescriptorViaIndex(1, var6, (ScanQualifier[][])null, var5, (TupleDescriptor)null, (List)null, SequenceDescriptor.class, false);
      this.putSequenceID(var7);
      return var7;
   }

   private void putSequenceID(SequenceDescriptor var1) throws StandardException {
      if (var1 != null) {
         SchemaDescriptor var2 = var1.getSchemaDescriptor();
         String var3 = var2.getSchemaName();
         String var4 = var1.getSequenceName();
         String var5 = var1.getUUID().toString();
         HashMap var6 = (HashMap)this.sequenceIDs.get(var3);
         if (var6 == null) {
            var6 = new HashMap();
            this.sequenceIDs.put(var3, var6);
         }

         if (var6.get(var4) == null) {
            var6.put(var4, var5);
         }

      }
   }

   private void dropSequenceID(SequenceDescriptor var1) throws StandardException {
      if (var1 != null) {
         SchemaDescriptor var2 = var1.getSchemaDescriptor();
         String var3 = var2.getSchemaName();
         String var4 = var1.getSequenceName();
         HashMap var5 = (HashMap)this.sequenceIDs.get(var3);
         if (var5 != null) {
            if (var5.get(var4) != null) {
               var5.remove(var4);
            }
         }
      }
   }

   private String getSequenceID(String var1, String var2) throws StandardException {
      HashMap var3 = (HashMap)this.sequenceIDs.get(var1);
      if (var3 != null) {
         String var4 = (String)var3.get(var2);
         if (var4 != null) {
            return var4;
         }
      }

      SequenceDescriptor var5 = this.getSequenceDescriptor(this.getSchemaDescriptor(var1, this.getTransactionCompile(), true), var2);
      return var5 == null ? null : var5.getUUID().toString();
   }

   PermDescriptor getUncachedGenericPermDescriptor(PermDescriptor var1) throws StandardException {
      return var1.getObjectID() == null ? (PermDescriptor)this.getUncachedPermissionsDescriptor(21, 2, var1, PermDescriptor.class) : (PermDescriptor)this.getUncachedPermissionsDescriptor(21, 0, var1, PermDescriptor.class);
   }

   public PermDescriptor getGenericPermissions(UUID var1, String var2, String var3, String var4) throws StandardException {
      PermDescriptor var5 = new PermDescriptor(this, (UUID)null, var2, var1, var3, (String)null, var4, false);
      return (PermDescriptor)this.getPermissions(var5);
   }

   public PermDescriptor getGenericPermissions(UUID var1) throws StandardException {
      PermDescriptor var2 = new PermDescriptor(this, var1);
      return this.getUncachedGenericPermDescriptor(var2);
   }

   public void dropAllPermDescriptors(UUID var1, TransactionController var2) throws StandardException {
      TabInfoImpl var3 = this.getNonCoreTI(21);
      SYSPERMSRowFactory var4 = (SYSPERMSRowFactory)var3.getCatalogRowFactory();
      if (this.usesSqlAuthorization) {
         SQLChar var5 = getIDValueAsCHAR(var1);
         ExecIndexRow var8 = this.exFactory.getIndexableRow(1);
         var8.setColumn(1, var5);

         ExecRow var6;
         while((var6 = var3.getRow(var2, var8, 1)) != null) {
            PermissionsDescriptor var7 = (PermissionsDescriptor)var4.buildDescriptor(var6, (TupleDescriptor)null, this);
            this.removePermEntryInCache(var7);
            ExecIndexRow var9 = var4.buildIndexKeyRow(0, var7);
            var3.deleteRow(var2, var9, 0);
         }

      }
   }

   public IndexStatisticsDaemon getIndexStatsRefresher(boolean var1) {
      return this.indexStatsUpdateDisabled && var1 ? null : this.indexRefresher;
   }

   public void disableIndexStatsRefresher() {
      if (!this.indexStatsUpdateDisabled) {
         this.indexStatsUpdateDisabled = true;
         this.indexRefresher.stop();
      }

   }

   public boolean doCreateIndexStatsRefresher() {
      return this.indexRefresher == null;
   }

   public void createIndexStatsRefresher(Database var1, String var2) {
      if (this.af.isReadOnly()) {
         this.indexStatsUpdateDisabled = true;
      } else {
         this.indexRefresher = new IndexStatisticsDaemonImpl(Monitor.getStream(), this.indexStatsUpdateLogging, this.indexStatsUpdateTracing, var1, this.authorizationDatabaseOwner, var2);
      }
   }

   public DependableFinder getDependableFinder(int var1) {
      return new DDdependableFinder(var1);
   }

   public DependableFinder getColumnDependableFinder(int var1, byte[] var2) {
      return new DDColumnDependableFinder(var1, var2);
   }

   void createIdentitySequences(TransactionController var1) throws StandardException {
      Hashtable var2 = this.hashAllTableDescriptorsByTableId(var1);

      for(UUID var4 : var2.keySet()) {
         TableDescriptor var5 = this.getTableDescriptor(var4);

         for(ColumnDescriptor var8 : var5.getColumnDescriptorList()) {
            if (var8.isAutoincrement()) {
               this.createIdentitySequence(var5, var8, var1);
            }
         }
      }

   }

   private void createIdentitySequence(TableDescriptor var1, ColumnDescriptor var2, TransactionController var3) throws StandardException {
      DataTypeDescriptor var4 = var2.getType();
      long[] var5 = var4.getNumericBounds();
      long var6 = var2.getAutoincValue();
      long var8 = var2.getAutoincStart();
      long var10 = var5[0];
      long var12 = var5[1];
      long var14 = var2.getAutoincInc();
      SchemaDescriptor var16 = this.getSystemSchemaDescriptor();
      SequenceDescriptor var17 = this.getDataDescriptorGenerator().newSequenceDescriptor(var16, this.getUUIDFactory().createUUID(), TableDescriptor.makeSequenceName(var1.getUUID()), var4, var6, var8, var10, var12, var14, false);
      this.addDescriptor(var17, (TupleDescriptor)null, 20, false, var3);
   }

   private static ContextService getContextService() {
      return ContextService.getFactory();
   }

   private static Context getContextOrNull(String var0) {
      return ContextService.getContextOrNull(var0);
   }

   private static Context getContext(String var0) {
      return ContextService.getContext(var0);
   }

   static ModuleFactory getMonitor() {
      return Monitor.getMonitor();
   }

   private static Object startSystemModule(String var0) throws StandardException {
      return Monitor.startSystemModule(var0);
   }

   private static Object bootServiceModule(boolean var0, Object var1, String var2, Properties var3) throws StandardException {
      return Monitor.bootServiceModule(var0, var1, var2, var3);
   }

   private static Object findServiceModule(Object var0, String var1) throws StandardException {
      return Monitor.findServiceModule(var0, var1);
   }

   static {
      NUM_NONCORE = nonCoreNames.length;
      systemSchemaNames = new String[]{"SYSCAT", "SYSFUN", "SYSPROC", "SYSSTAT", "NULLID", "SYSCS_DIAG", "SYSCS_UTIL", "SYSIBM", "SQLJ", "SYS"};
      sysUtilProceduresWithPublicAccess = new String[]{"SYSCS_SET_RUNTIMESTATISTICS", "SYSCS_SET_STATISTICS_TIMING", "SYSCS_INPLACE_COMPRESS_TABLE", "SYSCS_COMPRESS_TABLE", "SYSCS_UPDATE_STATISTICS", "SYSCS_MODIFY_PASSWORD", "SYSCS_DROP_STATISTICS"};
      sysUtilFunctionsWithPublicAccess = new String[]{"SYSCS_GET_RUNTIMESTATISTICS", "SYSCS_PEEK_AT_SEQUENCE", "SYSCS_PEEK_AT_IDENTITY", "SYSCS_GET_DATABASE_NAME"};
      colPrivTypeMap = new String[9];
      colPrivTypeMapForGrant = new String[9];
      colPrivTypeMap[8] = "s";
      colPrivTypeMapForGrant[8] = "S";
      colPrivTypeMap[0] = "s";
      colPrivTypeMapForGrant[0] = "S";
      colPrivTypeMap[1] = "u";
      colPrivTypeMapForGrant[1] = "U";
      colPrivTypeMap[2] = "r";
      colPrivTypeMapForGrant[2] = "R";
   }
}
