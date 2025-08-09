package org.apache.hive.service.cli;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.security.auth.login.LoginException;
import org.apache.hadoop.hive.common.log.ProgressMonitor;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.apache.hadoop.hive.metastore.HiveMetaStoreClient;
import org.apache.hadoop.hive.metastore.IMetaStoreClient;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.ql.exec.FunctionRegistry;
import org.apache.hadoop.hive.ql.metadata.Hive;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.session.SessionState;
import org.apache.hadoop.hive.shims.Utils;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.hive.service.CompositeService;
import org.apache.hive.service.ServiceException;
import org.apache.hive.service.auth.HiveAuthFactory;
import org.apache.hive.service.cli.operation.Operation;
import org.apache.hive.service.cli.session.HiveSession;
import org.apache.hive.service.cli.session.SessionManager;
import org.apache.hive.service.rpc.thrift.TOperationHandle;
import org.apache.hive.service.rpc.thrift.TProtocolVersion;
import org.apache.hive.service.rpc.thrift.TRowSet;
import org.apache.hive.service.rpc.thrift.TTableSchema;
import org.apache.hive.service.server.HiveServer2;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.SparkLogger;
import org.apache.spark.internal.SparkLoggerFactory;
import org.apache.spark.internal.LogKeys.PRINCIPAL.;

public class CLIService extends CompositeService implements ICLIService {
   private static final SparkLogger LOG = SparkLoggerFactory.getLogger(CLIService.class);
   public static final TProtocolVersion SERVER_VERSION;
   private HiveConf hiveConf;
   private SessionManager sessionManager;
   private UserGroupInformation serviceUGI;
   private UserGroupInformation httpUGI;
   private final HiveServer2 hiveServer2;

   public CLIService(HiveServer2 hiveServer2) {
      super(CLIService.class.getSimpleName());
      this.hiveServer2 = hiveServer2;
   }

   public synchronized void init(HiveConf hiveConf) {
      this.hiveConf = hiveConf;
      this.sessionManager = new SessionManager(this.hiveServer2);
      this.addService(this.sessionManager);
      if (UserGroupInformation.isSecurityEnabled()) {
         try {
            HiveAuthFactory.loginFromKeytab(hiveConf);
            this.serviceUGI = Utils.getUGI();
         } catch (IOException e) {
            throw new ServiceException("Unable to login to kerberos with given principal/keytab", e);
         } catch (LoginException e) {
            throw new ServiceException("Unable to login to kerberos with given principal/keytab", e);
         }

         String principal = hiveConf.getVar(ConfVars.HIVE_SERVER2_SPNEGO_PRINCIPAL);
         String keyTabFile = hiveConf.getVar(ConfVars.HIVE_SERVER2_SPNEGO_KEYTAB);
         if (!principal.isEmpty() && !keyTabFile.isEmpty()) {
            try {
               this.httpUGI = HiveAuthFactory.loginFromSpnegoKeytabAndReturnUGI(hiveConf);
               LOG.info("SPNego httpUGI successfully created.");
            } catch (IOException e) {
               LOG.warn("SPNego httpUGI creation failed: ", e);
            }
         } else {
            LOG.info("SPNego httpUGI not created, spNegoPrincipal: {}, keytabFile: {}", new MDC[]{MDC.of(.MODULE$, principal), MDC.of(org.apache.spark.internal.LogKeys.KEYTAB_FILE..MODULE$, keyTabFile)});
         }
      }

      try {
         this.applyAuthorizationConfigPolicy(hiveConf);
      } catch (Exception e) {
         throw new RuntimeException("Error applying authorization policy on hive configuration: " + e.getMessage(), e);
      }

      this.setupBlockedUdfs();
      super.init(hiveConf);
   }

   private void applyAuthorizationConfigPolicy(HiveConf newHiveConf) throws HiveException, MetaException {
      SessionState ss = new SessionState(newHiveConf);
      ss.setIsHiveServerQuery(true);
      SessionState.start(ss);
      ss.applyAuthorizationPolicy();
   }

   private void setupBlockedUdfs() {
      FunctionRegistry.setupPermissionsForBuiltinUDFs(this.hiveConf.getVar(ConfVars.HIVE_SERVER2_BUILTIN_UDF_WHITELIST), this.hiveConf.getVar(ConfVars.HIVE_SERVER2_BUILTIN_UDF_BLACKLIST));
   }

   public UserGroupInformation getServiceUGI() {
      return this.serviceUGI;
   }

   public UserGroupInformation getHttpUGI() {
      return this.httpUGI;
   }

   public synchronized void start() {
      super.start();
      IMetaStoreClient metastoreClient = null;

      try {
         metastoreClient = new HiveMetaStoreClient(this.hiveConf);
         metastoreClient.getDatabases("default");
      } catch (Exception e) {
         throw new ServiceException("Unable to connect to MetaStore!", e);
      } finally {
         if (metastoreClient != null) {
            metastoreClient.close();
         }

      }

   }

   public synchronized void stop() {
      super.stop();
   }

   /** @deprecated */
   @Deprecated
   public SessionHandle openSession(TProtocolVersion protocol, String username, String password, Map configuration) throws HiveSQLException {
      SessionHandle sessionHandle = this.sessionManager.openSession(protocol, username, password, (String)null, configuration, false, (String)null);
      LOG.debug(String.valueOf(sessionHandle) + ": openSession()");
      return sessionHandle;
   }

   /** @deprecated */
   @Deprecated
   public SessionHandle openSessionWithImpersonation(TProtocolVersion protocol, String username, String password, Map configuration, String delegationToken) throws HiveSQLException {
      SessionHandle sessionHandle = this.sessionManager.openSession(protocol, username, password, (String)null, configuration, true, delegationToken);
      LOG.debug(String.valueOf(sessionHandle) + ": openSessionWithImpersonation()");
      return sessionHandle;
   }

   public SessionHandle openSession(TProtocolVersion protocol, String username, String password, String ipAddress, Map configuration) throws HiveSQLException {
      SessionHandle sessionHandle = this.sessionManager.openSession(protocol, username, password, ipAddress, configuration, false, (String)null);
      LOG.debug(String.valueOf(sessionHandle) + ": openSession()");
      return sessionHandle;
   }

   public SessionHandle openSessionWithImpersonation(TProtocolVersion protocol, String username, String password, String ipAddress, Map configuration, String delegationToken) throws HiveSQLException {
      SessionHandle sessionHandle = this.sessionManager.openSession(protocol, username, password, ipAddress, configuration, true, delegationToken);
      LOG.debug(String.valueOf(sessionHandle) + ": openSession()");
      return sessionHandle;
   }

   public SessionHandle openSession(String username, String password, Map configuration) throws HiveSQLException {
      SessionHandle sessionHandle = this.sessionManager.openSession(SERVER_VERSION, username, password, (String)null, configuration, false, (String)null);
      LOG.debug(String.valueOf(sessionHandle) + ": openSession()");
      return sessionHandle;
   }

   public SessionHandle openSessionWithImpersonation(String username, String password, Map configuration, String delegationToken) throws HiveSQLException {
      SessionHandle sessionHandle = this.sessionManager.openSession(SERVER_VERSION, username, password, (String)null, configuration, true, delegationToken);
      LOG.debug(String.valueOf(sessionHandle) + ": openSession()");
      return sessionHandle;
   }

   public void closeSession(SessionHandle sessionHandle) throws HiveSQLException {
      this.sessionManager.closeSession(sessionHandle);
      LOG.debug(String.valueOf(sessionHandle) + ": closeSession()");
   }

   public GetInfoValue getInfo(SessionHandle sessionHandle, GetInfoType getInfoType) throws HiveSQLException {
      GetInfoValue infoValue = this.sessionManager.getSession(sessionHandle).getInfo(getInfoType);
      LOG.debug(String.valueOf(sessionHandle) + ": getInfo()");
      return infoValue;
   }

   public OperationHandle executeStatement(SessionHandle sessionHandle, String statement, Map confOverlay) throws HiveSQLException {
      HiveSession session = this.sessionManager.getSession(sessionHandle);
      session.getSessionState().updateProgressMonitor((ProgressMonitor)null);
      OperationHandle opHandle = session.executeStatement(statement, confOverlay);
      LOG.debug(String.valueOf(sessionHandle) + ": executeStatement()");
      return opHandle;
   }

   public OperationHandle executeStatement(SessionHandle sessionHandle, String statement, Map confOverlay, long queryTimeout) throws HiveSQLException {
      HiveSession session = this.sessionManager.getSession(sessionHandle);
      session.getSessionState().updateProgressMonitor((ProgressMonitor)null);
      OperationHandle opHandle = session.executeStatement(statement, confOverlay, queryTimeout);
      LOG.debug(String.valueOf(sessionHandle) + ": executeStatement()");
      return opHandle;
   }

   public OperationHandle executeStatementAsync(SessionHandle sessionHandle, String statement, Map confOverlay) throws HiveSQLException {
      HiveSession session = this.sessionManager.getSession(sessionHandle);
      session.getSessionState().updateProgressMonitor((ProgressMonitor)null);
      OperationHandle opHandle = session.executeStatementAsync(statement, confOverlay);
      LOG.debug(String.valueOf(sessionHandle) + ": executeStatementAsync()");
      return opHandle;
   }

   public OperationHandle executeStatementAsync(SessionHandle sessionHandle, String statement, Map confOverlay, long queryTimeout) throws HiveSQLException {
      HiveSession session = this.sessionManager.getSession(sessionHandle);
      session.getSessionState().updateProgressMonitor((ProgressMonitor)null);
      OperationHandle opHandle = session.executeStatementAsync(statement, confOverlay, queryTimeout);
      LOG.debug(String.valueOf(sessionHandle) + ": executeStatementAsync()");
      return opHandle;
   }

   public OperationHandle getTypeInfo(SessionHandle sessionHandle) throws HiveSQLException {
      OperationHandle opHandle = this.sessionManager.getSession(sessionHandle).getTypeInfo();
      LOG.debug(String.valueOf(sessionHandle) + ": getTypeInfo()");
      return opHandle;
   }

   public OperationHandle getCatalogs(SessionHandle sessionHandle) throws HiveSQLException {
      OperationHandle opHandle = this.sessionManager.getSession(sessionHandle).getCatalogs();
      LOG.debug(String.valueOf(sessionHandle) + ": getCatalogs()");
      return opHandle;
   }

   public OperationHandle getSchemas(SessionHandle sessionHandle, String catalogName, String schemaName) throws HiveSQLException {
      OperationHandle opHandle = this.sessionManager.getSession(sessionHandle).getSchemas(catalogName, schemaName);
      LOG.debug(String.valueOf(sessionHandle) + ": getSchemas()");
      return opHandle;
   }

   public OperationHandle getTables(SessionHandle sessionHandle, String catalogName, String schemaName, String tableName, List tableTypes) throws HiveSQLException {
      OperationHandle opHandle = this.sessionManager.getSession(sessionHandle).getTables(catalogName, schemaName, tableName, tableTypes);
      LOG.debug(String.valueOf(sessionHandle) + ": getTables()");
      return opHandle;
   }

   public OperationHandle getTableTypes(SessionHandle sessionHandle) throws HiveSQLException {
      OperationHandle opHandle = this.sessionManager.getSession(sessionHandle).getTableTypes();
      LOG.debug(String.valueOf(sessionHandle) + ": getTableTypes()");
      return opHandle;
   }

   public OperationHandle getColumns(SessionHandle sessionHandle, String catalogName, String schemaName, String tableName, String columnName) throws HiveSQLException {
      OperationHandle opHandle = this.sessionManager.getSession(sessionHandle).getColumns(catalogName, schemaName, tableName, columnName);
      LOG.debug(String.valueOf(sessionHandle) + ": getColumns()");
      return opHandle;
   }

   public OperationHandle getFunctions(SessionHandle sessionHandle, String catalogName, String schemaName, String functionName) throws HiveSQLException {
      OperationHandle opHandle = this.sessionManager.getSession(sessionHandle).getFunctions(catalogName, schemaName, functionName);
      LOG.debug(String.valueOf(sessionHandle) + ": getFunctions()");
      return opHandle;
   }

   public OperationHandle getPrimaryKeys(SessionHandle sessionHandle, String catalog, String schema, String table) throws HiveSQLException {
      OperationHandle opHandle = this.sessionManager.getSession(sessionHandle).getPrimaryKeys(catalog, schema, table);
      LOG.debug(String.valueOf(sessionHandle) + ": getPrimaryKeys()");
      return opHandle;
   }

   public OperationHandle getCrossReference(SessionHandle sessionHandle, String primaryCatalog, String primarySchema, String primaryTable, String foreignCatalog, String foreignSchema, String foreignTable) throws HiveSQLException {
      OperationHandle opHandle = this.sessionManager.getSession(sessionHandle).getCrossReference(primaryCatalog, primarySchema, primaryTable, foreignCatalog, foreignSchema, foreignTable);
      LOG.debug(String.valueOf(sessionHandle) + ": getCrossReference()");
      return opHandle;
   }

   public OperationStatus getOperationStatus(OperationHandle opHandle) throws HiveSQLException {
      Operation operation = this.sessionManager.getOperationManager().getOperation(opHandle);
      if (operation.shouldRunAsync()) {
         HiveConf conf = operation.getParentSession().getHiveConf();
         long timeout = HiveConf.getTimeVar(conf, ConfVars.HIVE_SERVER2_LONG_POLLING_TIMEOUT, TimeUnit.MILLISECONDS);

         try {
            operation.getBackgroundHandle().get(timeout, TimeUnit.MILLISECONDS);
         } catch (TimeoutException var7) {
            LOG.trace(String.valueOf(opHandle) + ": Long polling timed out");
         } catch (CancellationException e) {
            LOG.trace(String.valueOf(opHandle) + ": The background operation was cancelled", e);
         } catch (ExecutionException e) {
            LOG.warn("{}: The background operation was aborted", e, new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.OPERATION_HANDLE..MODULE$, opHandle)});
         } catch (InterruptedException var10) {
         }
      }

      OperationStatus opStatus = operation.getStatus();
      LOG.debug(String.valueOf(opHandle) + ": getOperationStatus()");
      return opStatus;
   }

   public HiveConf getSessionConf(SessionHandle sessionHandle) throws HiveSQLException {
      return this.sessionManager.getSession(sessionHandle).getHiveConf();
   }

   public void cancelOperation(OperationHandle opHandle) throws HiveSQLException {
      this.sessionManager.getOperationManager().getOperation(opHandle).getParentSession().cancelOperation(opHandle);
      LOG.debug(String.valueOf(opHandle) + ": cancelOperation()");
   }

   public void closeOperation(OperationHandle opHandle) throws HiveSQLException {
      this.sessionManager.getOperationManager().getOperation(opHandle).getParentSession().closeOperation(opHandle);
      LOG.debug(String.valueOf(opHandle) + ": closeOperation");
   }

   public TTableSchema getResultSetMetadata(OperationHandle opHandle) throws HiveSQLException {
      TTableSchema tableSchema = this.sessionManager.getOperationManager().getOperation(opHandle).getParentSession().getResultSetMetadata(opHandle);
      LOG.debug(String.valueOf(opHandle) + ": getResultSetMetadata()");
      return tableSchema;
   }

   public TRowSet fetchResults(OperationHandle opHandle) throws HiveSQLException {
      return this.fetchResults(opHandle, Operation.DEFAULT_FETCH_ORIENTATION, 100L, FetchType.QUERY_OUTPUT);
   }

   public TRowSet fetchResults(OperationHandle opHandle, FetchOrientation orientation, long maxRows, FetchType fetchType) throws HiveSQLException {
      TRowSet rowSet = this.sessionManager.getOperationManager().getOperation(opHandle).getParentSession().fetchResults(opHandle, orientation, maxRows, fetchType);
      LOG.debug(String.valueOf(opHandle) + ": fetchResults()");
      return rowSet;
   }

   public synchronized String getDelegationTokenFromMetaStore(String owner) throws HiveSQLException, UnsupportedOperationException, LoginException, IOException {
      if (this.hiveConf.getBoolVar(ConfVars.METASTORE_USE_THRIFT_SASL) && this.hiveConf.getBoolVar(ConfVars.HIVE_SERVER2_ENABLE_DOAS)) {
         try {
            Hive.closeCurrent();
            return Hive.getWithoutRegisterFns(this.hiveConf).getDelegationToken(owner, owner);
         } catch (HiveException e) {
            if (e.getCause() instanceof UnsupportedOperationException) {
               throw (UnsupportedOperationException)e.getCause();
            } else {
               throw new HiveSQLException("Error connect metastore to setup impersonation", e);
            }
         }
      } else {
         throw new UnsupportedOperationException("delegation token is can only be obtained for a secure remote metastore");
      }
   }

   public String getDelegationToken(SessionHandle sessionHandle, HiveAuthFactory authFactory, String owner, String renewer) throws HiveSQLException {
      String delegationToken = this.sessionManager.getSession(sessionHandle).getDelegationToken(authFactory, owner, renewer);
      LOG.info("{}: getDelegationToken()", new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.SESSION_HANDLE..MODULE$, sessionHandle)});
      return delegationToken;
   }

   public void cancelDelegationToken(SessionHandle sessionHandle, HiveAuthFactory authFactory, String tokenStr) throws HiveSQLException {
      this.sessionManager.getSession(sessionHandle).cancelDelegationToken(authFactory, tokenStr);
      LOG.info("{}: cancelDelegationToken()", new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.SESSION_HANDLE..MODULE$, sessionHandle)});
   }

   public void renewDelegationToken(SessionHandle sessionHandle, HiveAuthFactory authFactory, String tokenStr) throws HiveSQLException {
      this.sessionManager.getSession(sessionHandle).renewDelegationToken(authFactory, tokenStr);
      LOG.info("{}: renewDelegationToken()", new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.SESSION_HANDLE..MODULE$, sessionHandle)});
   }

   public String getQueryId(TOperationHandle opHandle) throws HiveSQLException {
      Operation operation = this.sessionManager.getOperationManager().getOperation(new OperationHandle(opHandle));
      String queryId = operation.getParentSession().getHiveConf().getVar(HiveConf.getConfVars("hive.query.id"));
      SparkLogger var10000 = LOG;
      String var10001 = String.valueOf(opHandle);
      var10000.debug(var10001 + ": getQueryId() " + queryId);
      return queryId;
   }

   public SessionManager getSessionManager() {
      return this.sessionManager;
   }

   static {
      TProtocolVersion[] protocols = TProtocolVersion.values();
      SERVER_VERSION = protocols[protocols.length - 1];
   }
}
