package org.apache.hive.service.cli.thrift;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.security.auth.login.LoginException;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.apache.hadoop.util.StringUtils;
import org.apache.hive.service.AbstractService;
import org.apache.hive.service.ServiceException;
import org.apache.hive.service.ServiceUtils;
import org.apache.hive.service.auth.HiveAuthFactory;
import org.apache.hive.service.auth.TSetIpAddressProcessor;
import org.apache.hive.service.cli.CLIService;
import org.apache.hive.service.cli.FetchOrientation;
import org.apache.hive.service.cli.FetchType;
import org.apache.hive.service.cli.GetInfoType;
import org.apache.hive.service.cli.GetInfoValue;
import org.apache.hive.service.cli.HiveSQLException;
import org.apache.hive.service.cli.OperationHandle;
import org.apache.hive.service.cli.OperationStatus;
import org.apache.hive.service.cli.SessionHandle;
import org.apache.hive.service.cli.session.SessionManager;
import org.apache.hive.service.rpc.thrift.TCLIService;
import org.apache.hive.service.rpc.thrift.TCancelDelegationTokenReq;
import org.apache.hive.service.rpc.thrift.TCancelDelegationTokenResp;
import org.apache.hive.service.rpc.thrift.TCancelOperationReq;
import org.apache.hive.service.rpc.thrift.TCancelOperationResp;
import org.apache.hive.service.rpc.thrift.TCloseOperationReq;
import org.apache.hive.service.rpc.thrift.TCloseOperationResp;
import org.apache.hive.service.rpc.thrift.TCloseSessionReq;
import org.apache.hive.service.rpc.thrift.TCloseSessionResp;
import org.apache.hive.service.rpc.thrift.TDownloadDataReq;
import org.apache.hive.service.rpc.thrift.TDownloadDataResp;
import org.apache.hive.service.rpc.thrift.TExecuteStatementReq;
import org.apache.hive.service.rpc.thrift.TExecuteStatementResp;
import org.apache.hive.service.rpc.thrift.TFetchResultsReq;
import org.apache.hive.service.rpc.thrift.TFetchResultsResp;
import org.apache.hive.service.rpc.thrift.TGetCatalogsReq;
import org.apache.hive.service.rpc.thrift.TGetCatalogsResp;
import org.apache.hive.service.rpc.thrift.TGetColumnsReq;
import org.apache.hive.service.rpc.thrift.TGetColumnsResp;
import org.apache.hive.service.rpc.thrift.TGetCrossReferenceReq;
import org.apache.hive.service.rpc.thrift.TGetCrossReferenceResp;
import org.apache.hive.service.rpc.thrift.TGetDelegationTokenReq;
import org.apache.hive.service.rpc.thrift.TGetDelegationTokenResp;
import org.apache.hive.service.rpc.thrift.TGetFunctionsReq;
import org.apache.hive.service.rpc.thrift.TGetFunctionsResp;
import org.apache.hive.service.rpc.thrift.TGetInfoReq;
import org.apache.hive.service.rpc.thrift.TGetInfoResp;
import org.apache.hive.service.rpc.thrift.TGetOperationStatusReq;
import org.apache.hive.service.rpc.thrift.TGetOperationStatusResp;
import org.apache.hive.service.rpc.thrift.TGetPrimaryKeysReq;
import org.apache.hive.service.rpc.thrift.TGetPrimaryKeysResp;
import org.apache.hive.service.rpc.thrift.TGetQueryIdReq;
import org.apache.hive.service.rpc.thrift.TGetQueryIdResp;
import org.apache.hive.service.rpc.thrift.TGetResultSetMetadataReq;
import org.apache.hive.service.rpc.thrift.TGetResultSetMetadataResp;
import org.apache.hive.service.rpc.thrift.TGetSchemasReq;
import org.apache.hive.service.rpc.thrift.TGetSchemasResp;
import org.apache.hive.service.rpc.thrift.TGetTableTypesReq;
import org.apache.hive.service.rpc.thrift.TGetTableTypesResp;
import org.apache.hive.service.rpc.thrift.TGetTablesReq;
import org.apache.hive.service.rpc.thrift.TGetTablesResp;
import org.apache.hive.service.rpc.thrift.TGetTypeInfoReq;
import org.apache.hive.service.rpc.thrift.TGetTypeInfoResp;
import org.apache.hive.service.rpc.thrift.TOpenSessionReq;
import org.apache.hive.service.rpc.thrift.TOpenSessionResp;
import org.apache.hive.service.rpc.thrift.TProtocolVersion;
import org.apache.hive.service.rpc.thrift.TRenewDelegationTokenReq;
import org.apache.hive.service.rpc.thrift.TRenewDelegationTokenResp;
import org.apache.hive.service.rpc.thrift.TRowSet;
import org.apache.hive.service.rpc.thrift.TSetClientInfoReq;
import org.apache.hive.service.rpc.thrift.TSetClientInfoResp;
import org.apache.hive.service.rpc.thrift.TStatus;
import org.apache.hive.service.rpc.thrift.TStatusCode;
import org.apache.hive.service.rpc.thrift.TTableSchema;
import org.apache.hive.service.rpc.thrift.TUploadDataReq;
import org.apache.hive.service.rpc.thrift.TUploadDataResp;
import org.apache.hive.service.server.HiveServer2;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.SparkLogger;
import org.apache.spark.internal.SparkLoggerFactory;
import org.apache.spark.internal.LogKeys.PROTOCOL_VERSION.;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.server.ServerContext;
import org.apache.thrift.server.TServerEventHandler;
import org.apache.thrift.transport.TTransport;

public abstract class ThriftCLIService extends AbstractService implements TCLIService.Iface, Runnable {
   public static final SparkLogger LOG = SparkLoggerFactory.getLogger(ThriftCLIService.class);
   protected CLIService cliService;
   private static final TStatus OK_STATUS;
   protected static HiveAuthFactory hiveAuthFactory;
   protected int portNum;
   protected InetAddress serverIPAddress;
   protected String hiveHost;
   private Thread serverThread = null;
   private boolean isStarted = false;
   protected boolean isEmbedded = false;
   protected HiveConf hiveConf;
   protected int minWorkerThreads;
   protected int maxWorkerThreads;
   protected long workerKeepAliveTime;
   protected TServerEventHandler serverEventHandler;
   protected ThreadLocal currentServerContext;

   public ThriftCLIService(CLIService service, String serviceName) {
      super(serviceName);
      this.cliService = service;
      this.currentServerContext = new ThreadLocal();
      this.serverEventHandler = new TServerEventHandler() {
         public ServerContext createContext(TProtocol input, TProtocol output) {
            return new ThriftCLIServerContext();
         }

         public void deleteContext(ServerContext serverContext, TProtocol input, TProtocol output) {
            ThriftCLIServerContext context = (ThriftCLIServerContext)serverContext;
            SessionHandle sessionHandle = context.getSessionHandle();
            if (sessionHandle != null) {
               ThriftCLIService.LOG.info("Session disconnected without closing properly, close it now");

               try {
                  ThriftCLIService.this.cliService.closeSession(sessionHandle);
               } catch (HiveSQLException e) {
                  ThriftCLIService.LOG.warn("Failed to close session: ", e);
               }
            }

         }

         public void preServe() {
         }

         public void processContext(ServerContext serverContext, TTransport input, TTransport output) {
            ThriftCLIService.this.currentServerContext.set(serverContext);
         }
      };
   }

   public synchronized void init(HiveConf hiveConf) {
      this.hiveConf = hiveConf;
      this.hiveHost = System.getenv("HIVE_SERVER2_THRIFT_BIND_HOST");
      if (this.hiveHost == null) {
         this.hiveHost = hiveConf.getVar(ConfVars.HIVE_SERVER2_THRIFT_BIND_HOST);
      }

      try {
         if (this.hiveHost != null && !this.hiveHost.isEmpty()) {
            this.serverIPAddress = InetAddress.getByName(this.hiveHost);
         } else {
            this.serverIPAddress = InetAddress.getLocalHost();
         }
      } catch (UnknownHostException e) {
         throw new ServiceException(e);
      }

      if (HiveServer2.isHTTPTransportMode(hiveConf)) {
         this.workerKeepAliveTime = hiveConf.getTimeVar(ConfVars.HIVE_SERVER2_THRIFT_HTTP_WORKER_KEEPALIVE_TIME, TimeUnit.SECONDS);
         String portString = System.getenv("HIVE_SERVER2_THRIFT_HTTP_PORT");
         if (portString != null) {
            this.portNum = Integer.valueOf(portString);
         } else {
            this.portNum = hiveConf.getIntVar(ConfVars.HIVE_SERVER2_THRIFT_HTTP_PORT);
         }
      } else {
         this.workerKeepAliveTime = hiveConf.getTimeVar(ConfVars.HIVE_SERVER2_THRIFT_WORKER_KEEPALIVE_TIME, TimeUnit.SECONDS);
         String portString = System.getenv("HIVE_SERVER2_THRIFT_PORT");
         if (portString != null) {
            this.portNum = Integer.valueOf(portString);
         } else {
            this.portNum = hiveConf.getIntVar(ConfVars.HIVE_SERVER2_THRIFT_PORT);
         }
      }

      this.minWorkerThreads = hiveConf.getIntVar(ConfVars.HIVE_SERVER2_THRIFT_MIN_WORKER_THREADS);
      this.maxWorkerThreads = hiveConf.getIntVar(ConfVars.HIVE_SERVER2_THRIFT_MAX_WORKER_THREADS);
      super.init(hiveConf);
   }

   public synchronized void start() {
      super.start();
      if (!this.isStarted && !this.isEmbedded) {
         this.initializeServer();
         this.serverThread = new Thread(this);
         this.serverThread.setName(this.getName());
         this.serverThread.start();
         this.isStarted = true;
      }

   }

   protected abstract void stopServer();

   public synchronized void stop() {
      if (this.isStarted && !this.isEmbedded) {
         if (this.serverThread != null) {
            this.serverThread.interrupt();
            this.serverThread = null;
         }

         this.stopServer();
         this.isStarted = false;
      }

      super.stop();
   }

   public int getPortNumber() {
      return this.portNum;
   }

   public InetAddress getServerIPAddress() {
      return this.serverIPAddress;
   }

   public TGetDelegationTokenResp GetDelegationToken(TGetDelegationTokenReq req) throws TException {
      TGetDelegationTokenResp resp = new TGetDelegationTokenResp();
      resp.setStatus(this.notSupportTokenErrorStatus());
      return resp;
   }

   public TCancelDelegationTokenResp CancelDelegationToken(TCancelDelegationTokenReq req) throws TException {
      TCancelDelegationTokenResp resp = new TCancelDelegationTokenResp();
      resp.setStatus(this.notSupportTokenErrorStatus());
      return resp;
   }

   public TRenewDelegationTokenResp RenewDelegationToken(TRenewDelegationTokenReq req) throws TException {
      TRenewDelegationTokenResp resp = new TRenewDelegationTokenResp();
      resp.setStatus(this.notSupportTokenErrorStatus());
      return resp;
   }

   private TStatus notSupportTokenErrorStatus() {
      TStatus errorStatus = new TStatus(TStatusCode.ERROR_STATUS);
      errorStatus.setErrorMessage("Delegation token is not supported");
      return errorStatus;
   }

   public TOpenSessionResp OpenSession(TOpenSessionReq req) throws TException {
      LOG.info("Client protocol version: {}", new MDC[]{MDC.of(.MODULE$, req.getClient_protocol())});
      TOpenSessionResp resp = new TOpenSessionResp();

      try {
         SessionHandle sessionHandle = this.getSessionHandle(req, resp);
         resp.setSessionHandle(sessionHandle.toTSessionHandle());
         resp.setConfiguration(new HashMap());
         resp.setStatus(OK_STATUS);
         ThriftCLIServerContext context = (ThriftCLIServerContext)this.currentServerContext.get();
         if (context != null) {
            context.setSessionHandle(sessionHandle);
         }
      } catch (Exception e) {
         LOG.warn("Error opening session: ", e);
         resp.setStatus(HiveSQLException.toTStatus(e));
      }

      return resp;
   }

   public TSetClientInfoResp SetClientInfo(TSetClientInfoReq req) throws TException {
      if (req.isSetConfiguration()) {
         StringBuilder sb = null;

         for(Map.Entry e : req.getConfiguration().entrySet()) {
            if (sb == null) {
               SessionHandle sh = new SessionHandle(req.getSessionHandle());
               sb = (new StringBuilder("Client information for ")).append(sh).append(": ");
            } else {
               sb.append(", ");
            }

            sb.append((String)e.getKey()).append(" = ").append((String)e.getValue());
         }

         if (sb != null) {
            LOG.info("{}", new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.SET_CLIENT_INFO_REQUEST..MODULE$, sb)});
         }
      }

      return new TSetClientInfoResp(OK_STATUS);
   }

   private String getIpAddress() {
      String clientIpAddress;
      if (this.cliService.getHiveConf().getVar(ConfVars.HIVE_SERVER2_TRANSPORT_MODE).equalsIgnoreCase("http")) {
         clientIpAddress = SessionManager.getIpAddress();
      } else if (this.isKerberosAuthMode()) {
         clientIpAddress = hiveAuthFactory.getIpAddress();
      } else {
         clientIpAddress = TSetIpAddressProcessor.getUserIpAddress();
      }

      LOG.debug("Client's IP Address: " + clientIpAddress);
      return clientIpAddress;
   }

   private String getUserName(TOpenSessionReq req) throws HiveSQLException {
      String userName = null;
      if (this.isKerberosAuthMode()) {
         userName = hiveAuthFactory.getRemoteUser();
      }

      if (userName == null) {
         userName = TSetIpAddressProcessor.getUserName();
      }

      if (this.cliService.getHiveConf().getVar(ConfVars.HIVE_SERVER2_TRANSPORT_MODE).equalsIgnoreCase("http")) {
         userName = SessionManager.getUserName();
      }

      if (userName == null) {
         userName = req.getUsername();
      }

      userName = this.getShortName(userName);
      String effectiveClientUser = this.getProxyUser(userName, req.getConfiguration(), this.getIpAddress());
      LOG.debug("Client's username: " + effectiveClientUser);
      return effectiveClientUser;
   }

   private String getShortName(String userName) {
      String ret = null;
      if (userName != null) {
         int indexOfDomainMatch = ServiceUtils.indexOfDomainMatch(userName);
         ret = indexOfDomainMatch <= 0 ? userName : userName.substring(0, indexOfDomainMatch);
      }

      return ret;
   }

   SessionHandle getSessionHandle(TOpenSessionReq req, TOpenSessionResp res) throws HiveSQLException, LoginException, IOException {
      String userName = this.getUserName(req);
      String ipAddress = this.getIpAddress();
      TProtocolVersion protocol = this.getMinVersion(CLIService.SERVER_VERSION, req.getClient_protocol());
      res.setServerProtocolVersion(protocol);
      SessionHandle sessionHandle;
      if (this.cliService.getHiveConf().getBoolVar(ConfVars.HIVE_SERVER2_ENABLE_DOAS) && userName != null) {
         String delegationTokenStr = this.getDelegationToken(userName);
         sessionHandle = this.cliService.openSessionWithImpersonation(protocol, userName, req.getPassword(), ipAddress, req.getConfiguration(), delegationTokenStr);
      } else {
         sessionHandle = this.cliService.openSession(protocol, userName, req.getPassword(), ipAddress, req.getConfiguration());
      }

      return sessionHandle;
   }

   private String getDelegationToken(String userName) throws HiveSQLException, LoginException, IOException {
      try {
         return this.cliService.getDelegationTokenFromMetaStore(userName);
      } catch (UnsupportedOperationException var3) {
         return null;
      }
   }

   private TProtocolVersion getMinVersion(TProtocolVersion... versions) {
      TProtocolVersion[] values = TProtocolVersion.values();
      int current = values[values.length - 1].getValue();

      for(TProtocolVersion version : versions) {
         if (current > version.getValue()) {
            current = version.getValue();
         }
      }

      for(TProtocolVersion version : values) {
         if (version.getValue() == current) {
            return version;
         }
      }

      throw new IllegalArgumentException("never");
   }

   public TCloseSessionResp CloseSession(TCloseSessionReq req) throws TException {
      TCloseSessionResp resp = new TCloseSessionResp();

      try {
         SessionHandle sessionHandle = new SessionHandle(req.getSessionHandle());
         this.cliService.closeSession(sessionHandle);
         resp.setStatus(OK_STATUS);
         ThriftCLIServerContext context = (ThriftCLIServerContext)this.currentServerContext.get();
         if (context != null) {
            context.setSessionHandle((SessionHandle)null);
         }
      } catch (Exception e) {
         LOG.warn("Error closing session: ", e);
         resp.setStatus(HiveSQLException.toTStatus(e));
      }

      return resp;
   }

   public TGetInfoResp GetInfo(TGetInfoReq req) throws TException {
      TGetInfoResp resp = new TGetInfoResp();

      try {
         GetInfoValue getInfoValue = this.cliService.getInfo(new SessionHandle(req.getSessionHandle()), GetInfoType.getGetInfoType(req.getInfoType()));
         resp.setInfoValue(getInfoValue.toTGetInfoValue());
         resp.setStatus(OK_STATUS);
      } catch (Exception e) {
         LOG.warn("Error getting info: ", e);
         resp.setStatus(HiveSQLException.toTStatus(e));
      }

      return resp;
   }

   public TExecuteStatementResp ExecuteStatement(TExecuteStatementReq req) throws TException {
      TExecuteStatementResp resp = new TExecuteStatementResp();

      try {
         SessionHandle sessionHandle = new SessionHandle(req.getSessionHandle());
         String statement = req.getStatement();
         Map<String, String> confOverlay = req.getConfOverlay();
         Boolean runAsync = req.isRunAsync();
         long queryTimeout = req.getQueryTimeout();
         OperationHandle operationHandle = runAsync ? this.cliService.executeStatementAsync(sessionHandle, statement, confOverlay, queryTimeout) : this.cliService.executeStatement(sessionHandle, statement, confOverlay, queryTimeout);
         resp.setOperationHandle(operationHandle.toTOperationHandle());
         resp.setStatus(OK_STATUS);
      } catch (Exception e) {
         LOG.warn("Error executing statement: ", e);
         resp.setStatus(HiveSQLException.toTStatus(e));
      }

      return resp;
   }

   public TGetTypeInfoResp GetTypeInfo(TGetTypeInfoReq req) throws TException {
      TGetTypeInfoResp resp = new TGetTypeInfoResp();

      try {
         OperationHandle operationHandle = this.cliService.getTypeInfo(new SessionHandle(req.getSessionHandle()));
         resp.setOperationHandle(operationHandle.toTOperationHandle());
         resp.setStatus(OK_STATUS);
      } catch (Exception e) {
         LOG.warn("Error getting type info: ", e);
         resp.setStatus(HiveSQLException.toTStatus(e));
      }

      return resp;
   }

   public TGetCatalogsResp GetCatalogs(TGetCatalogsReq req) throws TException {
      TGetCatalogsResp resp = new TGetCatalogsResp();

      try {
         OperationHandle opHandle = this.cliService.getCatalogs(new SessionHandle(req.getSessionHandle()));
         resp.setOperationHandle(opHandle.toTOperationHandle());
         resp.setStatus(OK_STATUS);
      } catch (Exception e) {
         LOG.warn("Error getting catalogs: ", e);
         resp.setStatus(HiveSQLException.toTStatus(e));
      }

      return resp;
   }

   public TGetSchemasResp GetSchemas(TGetSchemasReq req) throws TException {
      TGetSchemasResp resp = new TGetSchemasResp();

      try {
         OperationHandle opHandle = this.cliService.getSchemas(new SessionHandle(req.getSessionHandle()), req.getCatalogName(), req.getSchemaName());
         resp.setOperationHandle(opHandle.toTOperationHandle());
         resp.setStatus(OK_STATUS);
      } catch (Exception e) {
         LOG.warn("Error getting schemas: ", e);
         resp.setStatus(HiveSQLException.toTStatus(e));
      }

      return resp;
   }

   public TGetTablesResp GetTables(TGetTablesReq req) throws TException {
      TGetTablesResp resp = new TGetTablesResp();

      try {
         OperationHandle opHandle = this.cliService.getTables(new SessionHandle(req.getSessionHandle()), req.getCatalogName(), req.getSchemaName(), req.getTableName(), req.getTableTypes());
         resp.setOperationHandle(opHandle.toTOperationHandle());
         resp.setStatus(OK_STATUS);
      } catch (Exception e) {
         LOG.warn("Error getting tables: ", e);
         resp.setStatus(HiveSQLException.toTStatus(e));
      }

      return resp;
   }

   public TGetTableTypesResp GetTableTypes(TGetTableTypesReq req) throws TException {
      TGetTableTypesResp resp = new TGetTableTypesResp();

      try {
         OperationHandle opHandle = this.cliService.getTableTypes(new SessionHandle(req.getSessionHandle()));
         resp.setOperationHandle(opHandle.toTOperationHandle());
         resp.setStatus(OK_STATUS);
      } catch (Exception e) {
         LOG.warn("Error getting table types: ", e);
         resp.setStatus(HiveSQLException.toTStatus(e));
      }

      return resp;
   }

   public TGetColumnsResp GetColumns(TGetColumnsReq req) throws TException {
      TGetColumnsResp resp = new TGetColumnsResp();

      try {
         OperationHandle opHandle = this.cliService.getColumns(new SessionHandle(req.getSessionHandle()), req.getCatalogName(), req.getSchemaName(), req.getTableName(), req.getColumnName());
         resp.setOperationHandle(opHandle.toTOperationHandle());
         resp.setStatus(OK_STATUS);
      } catch (Exception e) {
         LOG.warn("Error getting columns: ", e);
         resp.setStatus(HiveSQLException.toTStatus(e));
      }

      return resp;
   }

   public TGetFunctionsResp GetFunctions(TGetFunctionsReq req) throws TException {
      TGetFunctionsResp resp = new TGetFunctionsResp();

      try {
         OperationHandle opHandle = this.cliService.getFunctions(new SessionHandle(req.getSessionHandle()), req.getCatalogName(), req.getSchemaName(), req.getFunctionName());
         resp.setOperationHandle(opHandle.toTOperationHandle());
         resp.setStatus(OK_STATUS);
      } catch (Exception e) {
         LOG.warn("Error getting functions: ", e);
         resp.setStatus(HiveSQLException.toTStatus(e));
      }

      return resp;
   }

   public TGetOperationStatusResp GetOperationStatus(TGetOperationStatusReq req) throws TException {
      TGetOperationStatusResp resp = new TGetOperationStatusResp();

      try {
         OperationStatus operationStatus = this.cliService.getOperationStatus(new OperationHandle(req.getOperationHandle()));
         resp.setOperationState(operationStatus.getState().toTOperationState());
         HiveSQLException opException = operationStatus.getOperationException();
         if (opException != null) {
            resp.setSqlState(opException.getSQLState());
            resp.setErrorCode(opException.getErrorCode());
            resp.setErrorMessage(StringUtils.stringifyException(opException));
         }

         resp.setStatus(OK_STATUS);
      } catch (Exception e) {
         LOG.warn("Error getting operation status: ", e);
         resp.setStatus(HiveSQLException.toTStatus(e));
      }

      return resp;
   }

   public TCancelOperationResp CancelOperation(TCancelOperationReq req) throws TException {
      TCancelOperationResp resp = new TCancelOperationResp();

      try {
         this.cliService.cancelOperation(new OperationHandle(req.getOperationHandle()));
         resp.setStatus(OK_STATUS);
      } catch (Exception e) {
         LOG.warn("Error cancelling operation: ", e);
         resp.setStatus(HiveSQLException.toTStatus(e));
      }

      return resp;
   }

   public TCloseOperationResp CloseOperation(TCloseOperationReq req) throws TException {
      TCloseOperationResp resp = new TCloseOperationResp();

      try {
         this.cliService.closeOperation(new OperationHandle(req.getOperationHandle()));
         resp.setStatus(OK_STATUS);
      } catch (Exception e) {
         LOG.warn("Error closing operation: ", e);
         resp.setStatus(HiveSQLException.toTStatus(e));
      }

      return resp;
   }

   public TGetResultSetMetadataResp GetResultSetMetadata(TGetResultSetMetadataReq req) throws TException {
      TGetResultSetMetadataResp resp = new TGetResultSetMetadataResp();

      try {
         TTableSchema schema = this.cliService.getResultSetMetadata(new OperationHandle(req.getOperationHandle()));
         resp.setSchema(schema);
         resp.setStatus(OK_STATUS);
      } catch (Exception e) {
         LOG.warn("Error getting result set metadata: ", e);
         resp.setStatus(HiveSQLException.toTStatus(e));
      }

      return resp;
   }

   public TFetchResultsResp FetchResults(TFetchResultsReq req) throws TException {
      TFetchResultsResp resp = new TFetchResultsResp();

      try {
         TRowSet rowSet = this.cliService.fetchResults(new OperationHandle(req.getOperationHandle()), FetchOrientation.getFetchOrientation(req.getOrientation()), req.getMaxRows(), FetchType.getFetchType(req.getFetchType()));
         resp.setResults(rowSet);
         resp.setHasMoreRows(false);
         resp.setStatus(OK_STATUS);
      } catch (Exception e) {
         LOG.warn("Error fetching results: ", e);
         resp.setStatus(HiveSQLException.toTStatus(e));
      }

      return resp;
   }

   public TGetPrimaryKeysResp GetPrimaryKeys(TGetPrimaryKeysReq req) throws TException {
      TGetPrimaryKeysResp resp = new TGetPrimaryKeysResp();

      try {
         OperationHandle opHandle = this.cliService.getPrimaryKeys(new SessionHandle(req.getSessionHandle()), req.getCatalogName(), req.getSchemaName(), req.getTableName());
         resp.setOperationHandle(opHandle.toTOperationHandle());
         resp.setStatus(OK_STATUS);
      } catch (Exception e) {
         LOG.warn("Error getting primary keys: ", e);
         resp.setStatus(HiveSQLException.toTStatus(e));
      }

      return resp;
   }

   public TGetCrossReferenceResp GetCrossReference(TGetCrossReferenceReq req) throws TException {
      TGetCrossReferenceResp resp = new TGetCrossReferenceResp();

      try {
         OperationHandle opHandle = this.cliService.getCrossReference(new SessionHandle(req.getSessionHandle()), req.getParentCatalogName(), req.getParentSchemaName(), req.getParentTableName(), req.getForeignCatalogName(), req.getForeignSchemaName(), req.getForeignTableName());
         resp.setOperationHandle(opHandle.toTOperationHandle());
         resp.setStatus(OK_STATUS);
      } catch (Exception e) {
         LOG.warn("Error getting cross reference: ", e);
         resp.setStatus(HiveSQLException.toTStatus(e));
      }

      return resp;
   }

   protected abstract void initializeServer();

   public TGetQueryIdResp GetQueryId(TGetQueryIdReq req) throws TException {
      try {
         return new TGetQueryIdResp(this.cliService.getQueryId(req.getOperationHandle()));
      } catch (HiveSQLException e) {
         throw new TException(e);
      }
   }

   public TUploadDataResp UploadData(TUploadDataReq req) throws TException {
      throw new UnsupportedOperationException("Method UploadData has not been implemented.");
   }

   public TDownloadDataResp DownloadData(TDownloadDataReq req) throws TException {
      throw new UnsupportedOperationException("Method DownloadData has not been implemented.");
   }

   public abstract void run();

   private String getProxyUser(String realUser, Map sessionConf, String ipAddress) throws HiveSQLException {
      String proxyUser = null;
      if (this.cliService.getHiveConf().getVar(ConfVars.HIVE_SERVER2_TRANSPORT_MODE).equalsIgnoreCase("http")) {
         proxyUser = SessionManager.getProxyUserName();
         LOG.debug("Proxy user from query string: " + proxyUser);
      }

      if (proxyUser == null && sessionConf != null && sessionConf.containsKey("hive.server2.proxy.user")) {
         String proxyUserFromThriftBody = (String)sessionConf.get("hive.server2.proxy.user");
         LOG.debug("Proxy user from thrift body: " + proxyUserFromThriftBody);
         proxyUser = proxyUserFromThriftBody;
      }

      if (proxyUser == null) {
         return realUser;
      } else if (!this.hiveConf.getBoolVar(ConfVars.HIVE_SERVER2_ALLOW_USER_SUBSTITUTION)) {
         throw new HiveSQLException("Proxy user substitution is not allowed");
      } else if (HiveAuthFactory.AuthTypes.NONE.toString().equalsIgnoreCase(this.hiveConf.getVar(ConfVars.HIVE_SERVER2_AUTHENTICATION))) {
         return proxyUser;
      } else {
         HiveAuthFactory.verifyProxyAccess(realUser, proxyUser, ipAddress, this.hiveConf);
         LOG.debug("Verified proxy user: " + proxyUser);
         return proxyUser;
      }
   }

   private boolean isKerberosAuthMode() {
      return this.cliService.getHiveConf().getVar(ConfVars.HIVE_SERVER2_AUTHENTICATION).equalsIgnoreCase(HiveAuthFactory.AuthTypes.KERBEROS.toString());
   }

   static {
      OK_STATUS = new TStatus(TStatusCode.SUCCESS_STATUS);
   }

   static class ThriftCLIServerContext implements ServerContext {
      private SessionHandle sessionHandle = null;

      public void setSessionHandle(SessionHandle sessionHandle) {
         this.sessionHandle = sessionHandle;
      }

      public SessionHandle getSessionHandle() {
         return this.sessionHandle;
      }

      public Object unwrap(Class aClass) {
         return null;
      }

      public boolean isWrapperFor(Class aClass) {
         return false;
      }
   }
}
