package org.apache.hive.service.cli.session;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.hive.common.cli.HiveFileProcessor;
import org.apache.hadoop.hive.common.cli.IHiveFileProcessor;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.conf.VariableSubstitution;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.apache.hadoop.hive.metastore.IMetaStoreClient;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.ql.exec.Utilities;
import org.apache.hadoop.hive.ql.history.HiveHistory;
import org.apache.hadoop.hive.ql.metadata.Hive;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.session.SessionState;
import org.apache.hadoop.hive.ql.session.SessionState.ResourceType;
import org.apache.hadoop.hive.serde2.thrift.ThriftFormatter;
import org.apache.hadoop.hive.shims.ShimLoader;
import org.apache.hive.common.util.HiveVersionInfo;
import org.apache.hive.service.auth.HiveAuthFactory;
import org.apache.hive.service.cli.FetchOrientation;
import org.apache.hive.service.cli.FetchType;
import org.apache.hive.service.cli.GetInfoType;
import org.apache.hive.service.cli.GetInfoValue;
import org.apache.hive.service.cli.HiveSQLException;
import org.apache.hive.service.cli.OperationHandle;
import org.apache.hive.service.cli.SessionHandle;
import org.apache.hive.service.cli.operation.ExecuteStatementOperation;
import org.apache.hive.service.cli.operation.GetCatalogsOperation;
import org.apache.hive.service.cli.operation.GetColumnsOperation;
import org.apache.hive.service.cli.operation.GetCrossReferenceOperation;
import org.apache.hive.service.cli.operation.GetFunctionsOperation;
import org.apache.hive.service.cli.operation.GetPrimaryKeysOperation;
import org.apache.hive.service.cli.operation.GetSchemasOperation;
import org.apache.hive.service.cli.operation.GetTableTypesOperation;
import org.apache.hive.service.cli.operation.GetTypeInfoOperation;
import org.apache.hive.service.cli.operation.MetadataOperation;
import org.apache.hive.service.cli.operation.Operation;
import org.apache.hive.service.cli.operation.OperationManager;
import org.apache.hive.service.rpc.thrift.TProtocolVersion;
import org.apache.hive.service.rpc.thrift.TRowSet;
import org.apache.hive.service.rpc.thrift.TTableSchema;
import org.apache.hive.service.server.ThreadWithGarbageCleanup;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.SparkLogger;
import org.apache.spark.internal.SparkLoggerFactory;
import org.apache.spark.internal.LogKeys.ERROR.;

public class HiveSessionImpl implements HiveSession {
   private final SessionHandle sessionHandle;
   private String username;
   private final String password;
   private HiveConf hiveConf;
   private SessionState sessionState;
   private String ipAddress;
   private static final String FETCH_WORK_SERDE_CLASS = "org.apache.hadoop.hive.serde2.lazy.LazySimpleSerDe";
   private static final SparkLogger LOG = SparkLoggerFactory.getLogger(HiveSessionImpl.class);
   private SessionManager sessionManager;
   private OperationManager operationManager;
   private final Set opHandleSet = new HashSet();
   private boolean isOperationLogEnabled;
   private File sessionLogDir;
   private volatile long lastAccessTime;
   private volatile long lastIdleTime;

   public HiveSessionImpl(TProtocolVersion protocol, String username, String password, HiveConf serverhiveConf, String ipAddress) {
      this.username = username;
      this.password = password;
      this.sessionHandle = new SessionHandle(protocol);
      this.hiveConf = new HiveConf(serverhiveConf);
      this.ipAddress = ipAddress;

      try {
         if (!this.hiveConf.getBoolVar(ConfVars.HIVE_SERVER2_ENABLE_DOAS) && this.hiveConf.getBoolVar(ConfVars.HIVE_SERVER2_MAP_FAIR_SCHEDULER_QUEUE)) {
            ShimLoader.getHadoopShims().refreshDefaultQueue(this.hiveConf, username);
         }
      } catch (IOException e) {
         LOG.warn("Error setting scheduler queue: ", e);
      }

      this.hiveConf.set("hive.session.id", this.sessionHandle.getHandleIdentifier().toString());
      this.hiveConf.set("list.sink.output.formatter", ThriftFormatter.class.getName());
      this.hiveConf.setInt("list.sink.output.protocol", protocol.getValue());
   }

   public void open(Map sessionConfMap) throws HiveSQLException {
      this.sessionState = new SessionState(this.hiveConf, this.username);
      this.sessionState.setUserIpAddress(this.ipAddress);
      this.sessionState.setIsHiveServerQuery(true);
      SessionState.setCurrentSessionState(this.sessionState);

      try {
         this.sessionState.loadAuxJars();
         this.sessionState.loadReloadableAuxJars();
      } catch (IOException e) {
         String msg = "Failed to load reloadable jar file path.";
         LOG.error("{}", e, new MDC[]{MDC.of(.MODULE$, msg)});
         throw new HiveSQLException(msg, e);
      }

      this.processGlobalInitFile();
      if (sessionConfMap != null) {
         this.configureSession(sessionConfMap);
      }

      this.lastAccessTime = System.currentTimeMillis();
      this.lastIdleTime = this.lastAccessTime;
   }

   private void processGlobalInitFile() {
      IHiveFileProcessor processor = new GlobalHivercFileProcessor();

      try {
         String hiverc = this.hiveConf.getVar(ConfVars.HIVE_SERVER2_GLOBAL_INIT_FILE_LOCATION);
         if (hiverc != null) {
            File hivercFile = new File(hiverc);
            if (hivercFile.isDirectory()) {
               hivercFile = new File(hivercFile, ".hiverc");
            }

            if (hivercFile.isFile()) {
               LOG.info("Running global init file: {}", new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.GLOBAL_INIT_FILE..MODULE$, hivercFile)});
               int rc = processor.processFile(hivercFile.getAbsolutePath());
               if (rc != 0) {
                  LOG.error("Failed on initializing global .hiverc file");
               }
            } else {
               LOG.debug("Global init file " + String.valueOf(hivercFile) + " does not exist");
            }
         }
      } catch (IOException e) {
         LOG.warn("Failed on initializing global .hiverc file", e);
      }

   }

   private void configureSession(Map sessionConfMap) throws HiveSQLException {
      SessionState.setCurrentSessionState(this.sessionState);

      for(Map.Entry entry : sessionConfMap.entrySet()) {
         String key = (String)entry.getKey();
         if (key.startsWith("set:")) {
            try {
               setVariable(key.substring(4), (String)entry.getValue());
            } catch (Exception e) {
               throw new HiveSQLException(e);
            }
         } else if (key.startsWith("use:")) {
            SessionState.get().setCurrentDatabase((String)entry.getValue());
         } else {
            this.hiveConf.verifyAndSet(key, (String)entry.getValue());
         }
      }

   }

   public static int setVariable(String varname, String varvalue) throws Exception {
      SessionState ss = SessionState.get();
      VariableSubstitution substitution = new VariableSubstitution(() -> ss.getHiveVariables());
      if (varvalue.contains("\n")) {
         ss.err.println("Warning: Value had a \\n character in it.");
      }

      varname = varname.trim();
      if (varname.startsWith("env:")) {
         ss.err.println("env:* variables can not be set.");
         return 1;
      } else {
         if (varname.startsWith("system:")) {
            String propName = varname.substring("system:".length());
            System.getProperties().setProperty(propName, substitution.substitute(ss.getConf(), varvalue));
         } else if (varname.startsWith("hiveconf:")) {
            String propName = varname.substring("hiveconf:".length());
            setConf(varname, propName, varvalue, true);
         } else if (varname.startsWith("hivevar:")) {
            String propName = varname.substring("hivevar:".length());
            ss.getHiveVariables().put(propName, substitution.substitute(ss.getConf(), varvalue));
         } else if (varname.startsWith("metaconf:")) {
            String propName = varname.substring("metaconf:".length());
            Hive hive = Hive.getWithoutRegisterFns(ss.getConf());
            hive.setMetaConf(propName, substitution.substitute(ss.getConf(), varvalue));
         } else {
            setConf(varname, varname, varvalue, true);
         }

         return 0;
      }
   }

   private static void setConf(String varname, String key, String varvalue, boolean register) throws IllegalArgumentException {
      VariableSubstitution substitution = new VariableSubstitution(() -> SessionState.get().getHiveVariables());
      HiveConf conf = SessionState.get().getConf();
      String value = substitution.substitute(conf, varvalue);
      if (conf.getBoolVar(ConfVars.HIVECONFVALIDATION)) {
         HiveConf.ConfVars confVars = HiveConf.getConfVars(key);
         if (confVars != null) {
            if (!confVars.isType(value)) {
               StringBuilder message = new StringBuilder();
               message.append("'SET ").append(varname).append('=').append(varvalue);
               message.append("' FAILED because ").append(key).append(" expects ");
               message.append(confVars.typeString()).append(" type value.");
               throw new IllegalArgumentException(message.toString());
            }

            String fail = confVars.validate(value);
            if (fail != null) {
               StringBuilder message = new StringBuilder();
               message.append("'SET ").append(varname).append('=').append(varvalue);
               message.append("' FAILED in validation : ").append(fail).append('.');
               throw new IllegalArgumentException(message.toString());
            }
         } else if (key.startsWith("hive.")) {
            throw new IllegalArgumentException("hive configuration " + key + " does not exists.");
         }
      }

      conf.verifyAndSet(key, value);
      if (register) {
         SessionState.get().getOverriddenConfigurations().put(key, value);
      }

   }

   public void setOperationLogSessionDir(File operationLogRootDir) {
      if (!operationLogRootDir.exists()) {
         LOG.warn("The operation log root directory is removed, recreating: {}", new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.PATH..MODULE$, operationLogRootDir.getAbsolutePath())});
         if (!operationLogRootDir.mkdirs()) {
            LOG.warn("Unable to create operation log root directory: {}", new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.PATH..MODULE$, operationLogRootDir.getAbsolutePath())});
         }
      }

      if (!operationLogRootDir.canWrite()) {
         LOG.warn("The operation log root directory is not writable: {}", new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.PATH..MODULE$, operationLogRootDir.getAbsolutePath())});
      }

      this.sessionLogDir = new File(operationLogRootDir, this.sessionHandle.getHandleIdentifier().toString());
      this.isOperationLogEnabled = true;
      if (!this.sessionLogDir.exists() && !this.sessionLogDir.mkdir()) {
         LOG.warn("Unable to create operation log session directory: {}", new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.PATH..MODULE$, this.sessionLogDir.getAbsolutePath())});
         this.isOperationLogEnabled = false;
      }

      if (this.isOperationLogEnabled) {
         LOG.info("Operation log session directory is created: {}", new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.PATH..MODULE$, this.sessionLogDir.getAbsolutePath())});
      }

   }

   public boolean isOperationLogEnabled() {
      return this.isOperationLogEnabled;
   }

   public File getOperationLogSessionDir() {
      return this.sessionLogDir;
   }

   public TProtocolVersion getProtocolVersion() {
      return this.sessionHandle.getProtocolVersion();
   }

   public SessionManager getSessionManager() {
      return this.sessionManager;
   }

   public void setSessionManager(SessionManager sessionManager) {
      this.sessionManager = sessionManager;
   }

   private OperationManager getOperationManager() {
      return this.operationManager;
   }

   public void setOperationManager(OperationManager operationManager) {
      this.operationManager = operationManager;
   }

   protected synchronized void acquire(boolean userAccess) {
      SessionState.setCurrentSessionState(this.sessionState);
      if (userAccess) {
         this.lastAccessTime = System.currentTimeMillis();
      }

   }

   protected synchronized void release(boolean userAccess) {
      SessionState.detachSession();
      if (ThreadWithGarbageCleanup.currentThread() instanceof ThreadWithGarbageCleanup) {
         ThreadWithGarbageCleanup currentThread = (ThreadWithGarbageCleanup)ThreadWithGarbageCleanup.currentThread();
         currentThread.cacheThreadLocalRawStore();
      }

      if (userAccess) {
         this.lastAccessTime = System.currentTimeMillis();
      }

      if (this.opHandleSet.isEmpty()) {
         this.lastIdleTime = System.currentTimeMillis();
      } else {
         this.lastIdleTime = 0L;
      }

   }

   public SessionHandle getSessionHandle() {
      return this.sessionHandle;
   }

   public String getUsername() {
      return this.username;
   }

   public String getPassword() {
      return this.password;
   }

   public HiveConf getHiveConf() {
      this.hiveConf.setVar(HiveConf.getConfVars("hive.fetch.output.serde"), "org.apache.hadoop.hive.serde2.lazy.LazySimpleSerDe");
      return this.hiveConf;
   }

   public IMetaStoreClient getMetaStoreClient() throws HiveSQLException {
      try {
         return Hive.getWithoutRegisterFns(this.getHiveConf()).getMSC();
      } catch (HiveException e) {
         throw new HiveSQLException("Failed to get metastore connection", e);
      } catch (MetaException e) {
         throw new HiveSQLException("Failed to get metastore connection", e);
      }
   }

   public GetInfoValue getInfo(GetInfoType getInfoType) throws HiveSQLException {
      this.acquire(true);

      try {
         switch (getInfoType) {
            case CLI_SERVER_NAME:
               GetInfoValue var10 = new GetInfoValue("Hive");
               return var10;
            case CLI_DBMS_NAME:
               GetInfoValue var9 = new GetInfoValue("Apache Hive");
               return var9;
            case CLI_DBMS_VER:
               GetInfoValue var8 = new GetInfoValue(HiveVersionInfo.getVersion());
               return var8;
            case CLI_MAX_COLUMN_NAME_LEN:
               GetInfoValue var7 = new GetInfoValue(128);
               return var7;
            case CLI_MAX_SCHEMA_NAME_LEN:
               GetInfoValue var6 = new GetInfoValue(128);
               return var6;
            case CLI_MAX_TABLE_NAME_LEN:
               GetInfoValue var2 = new GetInfoValue(128);
               return var2;
            case CLI_TXN_CAPABLE:
            default:
               throw new HiveSQLException("Unrecognized GetInfoType value: " + getInfoType.toString());
         }
      } finally {
         this.release(true);
      }
   }

   public OperationHandle executeStatement(String statement, Map confOverlay) throws HiveSQLException {
      return this.executeStatementInternal(statement, confOverlay, false, 0L);
   }

   public OperationHandle executeStatement(String statement, Map confOverlay, long queryTimeout) throws HiveSQLException {
      return this.executeStatementInternal(statement, confOverlay, false, queryTimeout);
   }

   public OperationHandle executeStatementAsync(String statement, Map confOverlay) throws HiveSQLException {
      return this.executeStatementInternal(statement, confOverlay, true, 0L);
   }

   public OperationHandle executeStatementAsync(String statement, Map confOverlay, long queryTimeout) throws HiveSQLException {
      return this.executeStatementInternal(statement, confOverlay, true, queryTimeout);
   }

   private OperationHandle executeStatementInternal(String statement, Map confOverlay, boolean runAsync, long queryTimeout) throws HiveSQLException {
      this.acquire(true);
      OperationManager operationManager = this.getOperationManager();
      ExecuteStatementOperation operation = operationManager.newExecuteStatementOperation(this.getSession(), statement, confOverlay, runAsync, queryTimeout);
      OperationHandle opHandle = operation.getHandle();

      OperationHandle var9;
      try {
         operation.run();
         this.opHandleSet.add(opHandle);
         var9 = opHandle;
      } catch (HiveSQLException e) {
         operationManager.closeOperation(opHandle);
         throw e;
      } finally {
         this.release(true);
      }

      return var9;
   }

   public OperationHandle getTypeInfo() throws HiveSQLException {
      this.acquire(true);
      OperationManager operationManager = this.getOperationManager();
      GetTypeInfoOperation operation = operationManager.newGetTypeInfoOperation(this.getSession());
      OperationHandle opHandle = operation.getHandle();

      OperationHandle var4;
      try {
         operation.run();
         this.opHandleSet.add(opHandle);
         var4 = opHandle;
      } catch (HiveSQLException e) {
         operationManager.closeOperation(opHandle);
         throw e;
      } finally {
         this.release(true);
      }

      return var4;
   }

   public OperationHandle getCatalogs() throws HiveSQLException {
      this.acquire(true);
      OperationManager operationManager = this.getOperationManager();
      GetCatalogsOperation operation = operationManager.newGetCatalogsOperation(this.getSession());
      OperationHandle opHandle = operation.getHandle();

      OperationHandle var4;
      try {
         operation.run();
         this.opHandleSet.add(opHandle);
         var4 = opHandle;
      } catch (HiveSQLException e) {
         operationManager.closeOperation(opHandle);
         throw e;
      } finally {
         this.release(true);
      }

      return var4;
   }

   public OperationHandle getSchemas(String catalogName, String schemaName) throws HiveSQLException {
      this.acquire(true);
      OperationManager operationManager = this.getOperationManager();
      GetSchemasOperation operation = operationManager.newGetSchemasOperation(this.getSession(), catalogName, schemaName);
      OperationHandle opHandle = operation.getHandle();

      OperationHandle var6;
      try {
         operation.run();
         this.opHandleSet.add(opHandle);
         var6 = opHandle;
      } catch (HiveSQLException e) {
         operationManager.closeOperation(opHandle);
         throw e;
      } finally {
         this.release(true);
      }

      return var6;
   }

   public OperationHandle getTables(String catalogName, String schemaName, String tableName, List tableTypes) throws HiveSQLException {
      this.acquire(true);
      OperationManager operationManager = this.getOperationManager();
      MetadataOperation operation = operationManager.newGetTablesOperation(this.getSession(), catalogName, schemaName, tableName, tableTypes);
      OperationHandle opHandle = operation.getHandle();

      OperationHandle var8;
      try {
         operation.run();
         this.opHandleSet.add(opHandle);
         var8 = opHandle;
      } catch (HiveSQLException e) {
         operationManager.closeOperation(opHandle);
         throw e;
      } finally {
         this.release(true);
      }

      return var8;
   }

   public OperationHandle getTableTypes() throws HiveSQLException {
      this.acquire(true);
      OperationManager operationManager = this.getOperationManager();
      GetTableTypesOperation operation = operationManager.newGetTableTypesOperation(this.getSession());
      OperationHandle opHandle = operation.getHandle();

      OperationHandle var4;
      try {
         operation.run();
         this.opHandleSet.add(opHandle);
         var4 = opHandle;
      } catch (HiveSQLException e) {
         operationManager.closeOperation(opHandle);
         throw e;
      } finally {
         this.release(true);
      }

      return var4;
   }

   public OperationHandle getColumns(String catalogName, String schemaName, String tableName, String columnName) throws HiveSQLException {
      this.acquire(true);
      String addedJars = Utilities.getResourceFiles(this.hiveConf, ResourceType.JAR);
      if (StringUtils.isNotBlank(addedJars)) {
         IMetaStoreClient metastoreClient = this.getSession().getMetaStoreClient();
         metastoreClient.setHiveAddedJars(addedJars);
      }

      OperationManager operationManager = this.getOperationManager();
      GetColumnsOperation operation = operationManager.newGetColumnsOperation(this.getSession(), catalogName, schemaName, tableName, columnName);
      OperationHandle opHandle = operation.getHandle();

      OperationHandle var9;
      try {
         operation.run();
         this.opHandleSet.add(opHandle);
         var9 = opHandle;
      } catch (HiveSQLException e) {
         operationManager.closeOperation(opHandle);
         throw e;
      } finally {
         this.release(true);
      }

      return var9;
   }

   public OperationHandle getFunctions(String catalogName, String schemaName, String functionName) throws HiveSQLException {
      this.acquire(true);
      OperationManager operationManager = this.getOperationManager();
      GetFunctionsOperation operation = operationManager.newGetFunctionsOperation(this.getSession(), catalogName, schemaName, functionName);
      OperationHandle opHandle = operation.getHandle();

      OperationHandle var7;
      try {
         operation.run();
         this.opHandleSet.add(opHandle);
         var7 = opHandle;
      } catch (HiveSQLException e) {
         operationManager.closeOperation(opHandle);
         throw e;
      } finally {
         this.release(true);
      }

      return var7;
   }

   public void close() throws HiveSQLException {
      try {
         this.acquire(true);

         for(OperationHandle opHandle : this.opHandleSet) {
            try {
               this.operationManager.closeOperation(opHandle);
            } catch (Exception e) {
               LOG.warn("Exception is thrown closing operation {}", e, new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.OPERATION_HANDLE..MODULE$, opHandle)});
            }
         }

         this.opHandleSet.clear();
         this.cleanupSessionLogDir();
         this.cleanupPipeoutFile();
         HiveHistory hiveHist = this.sessionState.getHiveHistory();
         if (null != hiveHist) {
            hiveHist.closeStream();
         }

         try {
            Hive.getWithoutRegisterFns(this.sessionState.getConf());
            this.sessionState.close();
         } finally {
            this.sessionState = null;
         }
      } catch (HiveException | IOException ioe) {
         throw new HiveSQLException("Failure to close", ioe);
      } finally {
         if (this.sessionState != null) {
            try {
               Hive.getWithoutRegisterFns(this.sessionState.getConf());
               this.sessionState.close();
            } catch (Throwable t) {
               LOG.warn("Error closing session", t);
            }

            this.sessionState = null;
         }

         this.release(true);
      }

   }

   private void cleanupPipeoutFile() {
      String lScratchDir = this.hiveConf.getVar(HiveConf.getConfVars("hive.exec.local.scratchdir"));
      String sessionID = this.hiveConf.getVar(HiveConf.getConfVars("hive.session.id"));
      File[] fileAry = (new File(lScratchDir)).listFiles((dir, name) -> name.startsWith(sessionID) && name.endsWith(".pipeout"));
      if (fileAry == null) {
         LOG.error("Unable to access pipeout files in {}", new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.LOCAL_SCRATCH_DIR..MODULE$, lScratchDir)});
      } else {
         for(File file : fileAry) {
            try {
               FileUtils.forceDelete(file);
            } catch (Exception e) {
               LOG.error("Failed to cleanup pipeout file: {}", e, new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.PATH..MODULE$, file)});
            }
         }
      }

   }

   private void cleanupSessionLogDir() {
      if (this.isOperationLogEnabled) {
         try {
            FileUtils.forceDelete(this.sessionLogDir);
         } catch (Exception e) {
            LOG.error("Failed to cleanup session log dir: {}", e, new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.SESSION_HANDLE..MODULE$, this.sessionHandle)});
         }
      }

   }

   public SessionState getSessionState() {
      return this.sessionState;
   }

   public String getUserName() {
      return this.username;
   }

   public void setUserName(String userName) {
      this.username = userName;
   }

   public long getLastAccessTime() {
      return this.lastAccessTime;
   }

   public void closeExpiredOperations() {
      OperationHandle[] handles = (OperationHandle[])this.opHandleSet.toArray(new OperationHandle[this.opHandleSet.size()]);
      if (handles.length > 0) {
         List<Operation> operations = this.operationManager.removeExpiredOperations(handles);
         if (!operations.isEmpty()) {
            this.closeTimedOutOperations(operations);
         }
      }

   }

   public long getNoOperationTime() {
      return this.lastIdleTime > 0L ? System.currentTimeMillis() - this.lastIdleTime : 0L;
   }

   private void closeTimedOutOperations(List operations) {
      this.acquire(false);

      try {
         for(Operation operation : operations) {
            this.opHandleSet.remove(operation.getHandle());

            try {
               operation.close();
            } catch (Exception e) {
               LOG.warn("Exception is thrown closing timed-out operation {}", e, new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.OPERATION_HANDLE..MODULE$, operation.getHandle())});
            }
         }
      } finally {
         this.release(false);
      }

   }

   public void cancelOperation(OperationHandle opHandle) throws HiveSQLException {
      this.acquire(true);

      try {
         this.sessionManager.getOperationManager().cancelOperation(opHandle);
      } finally {
         this.release(true);
      }

   }

   public void closeOperation(OperationHandle opHandle) throws HiveSQLException {
      this.acquire(true);

      try {
         this.operationManager.closeOperation(opHandle);
         this.opHandleSet.remove(opHandle);
      } finally {
         this.release(true);
      }

   }

   public TTableSchema getResultSetMetadata(OperationHandle opHandle) throws HiveSQLException {
      this.acquire(true);

      TTableSchema var2;
      try {
         var2 = this.sessionManager.getOperationManager().getOperationResultSetSchema(opHandle);
      } finally {
         this.release(true);
      }

      return var2;
   }

   public TRowSet fetchResults(OperationHandle opHandle, FetchOrientation orientation, long maxRows, FetchType fetchType) throws HiveSQLException {
      this.acquire(true);

      TRowSet var6;
      try {
         if (fetchType != FetchType.QUERY_OUTPUT) {
            var6 = this.operationManager.getOperationLogRowSet(opHandle, orientation, maxRows);
            return var6;
         }

         var6 = this.operationManager.getOperationNextRowSet(opHandle, orientation, maxRows);
      } finally {
         this.release(true);
      }

      return var6;
   }

   protected HiveSession getSession() {
      return this;
   }

   public String getIpAddress() {
      return this.ipAddress;
   }

   public void setIpAddress(String ipAddress) {
      this.ipAddress = ipAddress;
   }

   public String getDelegationToken(HiveAuthFactory authFactory, String owner, String renewer) throws HiveSQLException {
      HiveAuthFactory.verifyProxyAccess(this.getUsername(), owner, this.getIpAddress(), this.getHiveConf());
      return authFactory.getDelegationToken(owner, renewer, this.getIpAddress());
   }

   public void cancelDelegationToken(HiveAuthFactory authFactory, String tokenStr) throws HiveSQLException {
      HiveAuthFactory.verifyProxyAccess(this.getUsername(), this.getUserFromToken(authFactory, tokenStr), this.getIpAddress(), this.getHiveConf());
      authFactory.cancelDelegationToken(tokenStr);
   }

   public void renewDelegationToken(HiveAuthFactory authFactory, String tokenStr) throws HiveSQLException {
      HiveAuthFactory.verifyProxyAccess(this.getUsername(), this.getUserFromToken(authFactory, tokenStr), this.getIpAddress(), this.getHiveConf());
      authFactory.renewDelegationToken(tokenStr);
   }

   private String getUserFromToken(HiveAuthFactory authFactory, String tokenStr) throws HiveSQLException {
      return authFactory.getUserFromToken(tokenStr);
   }

   public OperationHandle getPrimaryKeys(String catalog, String schema, String table) throws HiveSQLException {
      this.acquire(true);
      OperationManager operationManager = this.getOperationManager();
      GetPrimaryKeysOperation operation = operationManager.newGetPrimaryKeysOperation(this.getSession(), catalog, schema, table);
      OperationHandle opHandle = operation.getHandle();

      OperationHandle var7;
      try {
         operation.run();
         this.opHandleSet.add(opHandle);
         var7 = opHandle;
      } catch (HiveSQLException e) {
         operationManager.closeOperation(opHandle);
         throw e;
      } finally {
         this.release(true);
      }

      return var7;
   }

   public OperationHandle getCrossReference(String primaryCatalog, String primarySchema, String primaryTable, String foreignCatalog, String foreignSchema, String foreignTable) throws HiveSQLException {
      this.acquire(true);
      OperationManager operationManager = this.getOperationManager();
      GetCrossReferenceOperation operation = operationManager.newGetCrossReferenceOperation(this.getSession(), primaryCatalog, primarySchema, primaryTable, foreignCatalog, foreignSchema, foreignTable);
      OperationHandle opHandle = operation.getHandle();

      OperationHandle var10;
      try {
         operation.run();
         this.opHandleSet.add(opHandle);
         var10 = opHandle;
      } catch (HiveSQLException e) {
         operationManager.closeOperation(opHandle);
         throw e;
      } finally {
         this.release(true);
      }

      return var10;
   }

   private class GlobalHivercFileProcessor extends HiveFileProcessor {
      protected BufferedReader loadFile(String fileName) throws IOException {
         FileInputStream initStream = null;
         BufferedReader bufferedReader = null;
         initStream = new FileInputStream(fileName);
         bufferedReader = new BufferedReader(new InputStreamReader(initStream, StandardCharsets.UTF_8));
         return bufferedReader;
      }

      protected int processCmd(String cmd) {
         int rc = 0;
         String cmd_trimmed = cmd.trim();

         try {
            HiveSessionImpl.this.executeStatementInternal(cmd_trimmed, (Map)null, false, 0L);
         } catch (HiveSQLException e) {
            rc = -1;
            HiveSessionImpl.LOG.warn("Failed to execute HQL command in global .hiverc file.", e);
         }

         return rc;
      }
   }
}
