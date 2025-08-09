package org.apache.hive.service.cli.thrift;

import java.util.List;
import java.util.Map;
import org.apache.hive.service.auth.HiveAuthFactory;
import org.apache.hive.service.cli.CLIServiceClient;
import org.apache.hive.service.cli.FetchOrientation;
import org.apache.hive.service.cli.FetchType;
import org.apache.hive.service.cli.GetInfoType;
import org.apache.hive.service.cli.GetInfoValue;
import org.apache.hive.service.cli.HiveSQLException;
import org.apache.hive.service.cli.OperationHandle;
import org.apache.hive.service.cli.OperationState;
import org.apache.hive.service.cli.OperationStatus;
import org.apache.hive.service.cli.SessionHandle;
import org.apache.hive.service.rpc.thrift.TCLIService;
import org.apache.hive.service.rpc.thrift.TCancelDelegationTokenReq;
import org.apache.hive.service.rpc.thrift.TCancelDelegationTokenResp;
import org.apache.hive.service.rpc.thrift.TCancelOperationReq;
import org.apache.hive.service.rpc.thrift.TCancelOperationResp;
import org.apache.hive.service.rpc.thrift.TCloseOperationReq;
import org.apache.hive.service.rpc.thrift.TCloseOperationResp;
import org.apache.hive.service.rpc.thrift.TCloseSessionReq;
import org.apache.hive.service.rpc.thrift.TCloseSessionResp;
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
import org.apache.hive.service.rpc.thrift.TOperationHandle;
import org.apache.hive.service.rpc.thrift.TProtocolVersion;
import org.apache.hive.service.rpc.thrift.TRenewDelegationTokenReq;
import org.apache.hive.service.rpc.thrift.TRenewDelegationTokenResp;
import org.apache.hive.service.rpc.thrift.TRowSet;
import org.apache.hive.service.rpc.thrift.TStatus;
import org.apache.hive.service.rpc.thrift.TStatusCode;
import org.apache.hive.service.rpc.thrift.TTableSchema;
import org.apache.thrift.TException;

public class ThriftCLIServiceClient extends CLIServiceClient {
   private final TCLIService.Iface cliService;

   public ThriftCLIServiceClient(TCLIService.Iface cliService) {
      this.cliService = cliService;
   }

   public void checkStatus(TStatus status) throws HiveSQLException {
      if (TStatusCode.ERROR_STATUS.equals(status.getStatusCode())) {
         throw new HiveSQLException(status);
      }
   }

   public SessionHandle openSession(String username, String password, Map configuration) throws HiveSQLException {
      try {
         TOpenSessionReq req = new TOpenSessionReq();
         req.setUsername(username);
         req.setPassword(password);
         req.setConfiguration(configuration);
         TOpenSessionResp resp = this.cliService.OpenSession(req);
         this.checkStatus(resp.getStatus());
         return new SessionHandle(resp.getSessionHandle(), resp.getServerProtocolVersion());
      } catch (HiveSQLException e) {
         throw e;
      } catch (Exception e) {
         throw new HiveSQLException(e);
      }
   }

   public SessionHandle openSessionWithImpersonation(String username, String password, Map configuration, String delegationToken) throws HiveSQLException {
      throw new HiveSQLException("open with impersonation operation is not supported in the client");
   }

   public void closeSession(SessionHandle sessionHandle) throws HiveSQLException {
      try {
         TCloseSessionReq req = new TCloseSessionReq(sessionHandle.toTSessionHandle());
         TCloseSessionResp resp = this.cliService.CloseSession(req);
         this.checkStatus(resp.getStatus());
      } catch (HiveSQLException e) {
         throw e;
      } catch (Exception e) {
         throw new HiveSQLException(e);
      }
   }

   public GetInfoValue getInfo(SessionHandle sessionHandle, GetInfoType infoType) throws HiveSQLException {
      try {
         TGetInfoReq req = new TGetInfoReq(sessionHandle.toTSessionHandle(), infoType.toTGetInfoType());
         TGetInfoResp resp = this.cliService.GetInfo(req);
         this.checkStatus(resp.getStatus());
         return new GetInfoValue(resp.getInfoValue());
      } catch (HiveSQLException e) {
         throw e;
      } catch (Exception e) {
         throw new HiveSQLException(e);
      }
   }

   public OperationHandle executeStatement(SessionHandle sessionHandle, String statement, Map confOverlay) throws HiveSQLException {
      return this.executeStatementInternal(sessionHandle, statement, confOverlay, false, 0L);
   }

   public OperationHandle executeStatement(SessionHandle sessionHandle, String statement, Map confOverlay, long queryTimeout) throws HiveSQLException {
      return this.executeStatementInternal(sessionHandle, statement, confOverlay, false, queryTimeout);
   }

   public OperationHandle executeStatementAsync(SessionHandle sessionHandle, String statement, Map confOverlay) throws HiveSQLException {
      return this.executeStatementInternal(sessionHandle, statement, confOverlay, true, 0L);
   }

   public OperationHandle executeStatementAsync(SessionHandle sessionHandle, String statement, Map confOverlay, long queryTimeout) throws HiveSQLException {
      return this.executeStatementInternal(sessionHandle, statement, confOverlay, true, queryTimeout);
   }

   private OperationHandle executeStatementInternal(SessionHandle sessionHandle, String statement, Map confOverlay, boolean isAsync, long queryTimeout) throws HiveSQLException {
      try {
         TExecuteStatementReq req = new TExecuteStatementReq(sessionHandle.toTSessionHandle(), statement);
         req.setConfOverlay(confOverlay);
         req.setRunAsync(isAsync);
         req.setQueryTimeout(queryTimeout);
         TExecuteStatementResp resp = this.cliService.ExecuteStatement(req);
         this.checkStatus(resp.getStatus());
         TProtocolVersion protocol = sessionHandle.getProtocolVersion();
         return new OperationHandle(resp.getOperationHandle(), protocol);
      } catch (HiveSQLException e) {
         throw e;
      } catch (Exception e) {
         throw new HiveSQLException(e);
      }
   }

   public OperationHandle getTypeInfo(SessionHandle sessionHandle) throws HiveSQLException {
      try {
         TGetTypeInfoReq req = new TGetTypeInfoReq(sessionHandle.toTSessionHandle());
         TGetTypeInfoResp resp = this.cliService.GetTypeInfo(req);
         this.checkStatus(resp.getStatus());
         TProtocolVersion protocol = sessionHandle.getProtocolVersion();
         return new OperationHandle(resp.getOperationHandle(), protocol);
      } catch (HiveSQLException e) {
         throw e;
      } catch (Exception e) {
         throw new HiveSQLException(e);
      }
   }

   public OperationHandle getCatalogs(SessionHandle sessionHandle) throws HiveSQLException {
      try {
         TGetCatalogsReq req = new TGetCatalogsReq(sessionHandle.toTSessionHandle());
         TGetCatalogsResp resp = this.cliService.GetCatalogs(req);
         this.checkStatus(resp.getStatus());
         TProtocolVersion protocol = sessionHandle.getProtocolVersion();
         return new OperationHandle(resp.getOperationHandle(), protocol);
      } catch (HiveSQLException e) {
         throw e;
      } catch (Exception e) {
         throw new HiveSQLException(e);
      }
   }

   public OperationHandle getSchemas(SessionHandle sessionHandle, String catalogName, String schemaName) throws HiveSQLException {
      try {
         TGetSchemasReq req = new TGetSchemasReq(sessionHandle.toTSessionHandle());
         req.setCatalogName(catalogName);
         req.setSchemaName(schemaName);
         TGetSchemasResp resp = this.cliService.GetSchemas(req);
         this.checkStatus(resp.getStatus());
         TProtocolVersion protocol = sessionHandle.getProtocolVersion();
         return new OperationHandle(resp.getOperationHandle(), protocol);
      } catch (HiveSQLException e) {
         throw e;
      } catch (Exception e) {
         throw new HiveSQLException(e);
      }
   }

   public OperationHandle getTables(SessionHandle sessionHandle, String catalogName, String schemaName, String tableName, List tableTypes) throws HiveSQLException {
      try {
         TGetTablesReq req = new TGetTablesReq(sessionHandle.toTSessionHandle());
         req.setTableName(tableName);
         req.setTableTypes(tableTypes);
         req.setSchemaName(schemaName);
         TGetTablesResp resp = this.cliService.GetTables(req);
         this.checkStatus(resp.getStatus());
         TProtocolVersion protocol = sessionHandle.getProtocolVersion();
         return new OperationHandle(resp.getOperationHandle(), protocol);
      } catch (HiveSQLException e) {
         throw e;
      } catch (Exception e) {
         throw new HiveSQLException(e);
      }
   }

   public OperationHandle getTableTypes(SessionHandle sessionHandle) throws HiveSQLException {
      try {
         TGetTableTypesReq req = new TGetTableTypesReq(sessionHandle.toTSessionHandle());
         TGetTableTypesResp resp = this.cliService.GetTableTypes(req);
         this.checkStatus(resp.getStatus());
         TProtocolVersion protocol = sessionHandle.getProtocolVersion();
         return new OperationHandle(resp.getOperationHandle(), protocol);
      } catch (HiveSQLException e) {
         throw e;
      } catch (Exception e) {
         throw new HiveSQLException(e);
      }
   }

   public OperationHandle getColumns(SessionHandle sessionHandle, String catalogName, String schemaName, String tableName, String columnName) throws HiveSQLException {
      try {
         TGetColumnsReq req = new TGetColumnsReq();
         req.setSessionHandle(sessionHandle.toTSessionHandle());
         req.setCatalogName(catalogName);
         req.setSchemaName(schemaName);
         req.setTableName(tableName);
         req.setColumnName(columnName);
         TGetColumnsResp resp = this.cliService.GetColumns(req);
         this.checkStatus(resp.getStatus());
         TProtocolVersion protocol = sessionHandle.getProtocolVersion();
         return new OperationHandle(resp.getOperationHandle(), protocol);
      } catch (HiveSQLException e) {
         throw e;
      } catch (Exception e) {
         throw new HiveSQLException(e);
      }
   }

   public OperationHandle getFunctions(SessionHandle sessionHandle, String catalogName, String schemaName, String functionName) throws HiveSQLException {
      try {
         TGetFunctionsReq req = new TGetFunctionsReq(sessionHandle.toTSessionHandle(), functionName);
         req.setCatalogName(catalogName);
         req.setSchemaName(schemaName);
         TGetFunctionsResp resp = this.cliService.GetFunctions(req);
         this.checkStatus(resp.getStatus());
         TProtocolVersion protocol = sessionHandle.getProtocolVersion();
         return new OperationHandle(resp.getOperationHandle(), protocol);
      } catch (HiveSQLException e) {
         throw e;
      } catch (Exception e) {
         throw new HiveSQLException(e);
      }
   }

   public OperationStatus getOperationStatus(OperationHandle opHandle) throws HiveSQLException {
      try {
         TGetOperationStatusReq req = new TGetOperationStatusReq(opHandle.toTOperationHandle());
         TGetOperationStatusResp resp = this.cliService.GetOperationStatus(req);
         this.checkStatus(resp.getStatus());
         OperationState opState = OperationState.getOperationState(resp.getOperationState());
         HiveSQLException opException = null;
         if (opState == OperationState.ERROR) {
            opException = new HiveSQLException(resp.getErrorMessage(), resp.getSqlState(), resp.getErrorCode());
         }

         return new OperationStatus(opState, opException);
      } catch (HiveSQLException e) {
         throw e;
      } catch (Exception e) {
         throw new HiveSQLException(e);
      }
   }

   public void cancelOperation(OperationHandle opHandle) throws HiveSQLException {
      try {
         TCancelOperationReq req = new TCancelOperationReq(opHandle.toTOperationHandle());
         TCancelOperationResp resp = this.cliService.CancelOperation(req);
         this.checkStatus(resp.getStatus());
      } catch (HiveSQLException e) {
         throw e;
      } catch (Exception e) {
         throw new HiveSQLException(e);
      }
   }

   public void closeOperation(OperationHandle opHandle) throws HiveSQLException {
      try {
         TCloseOperationReq req = new TCloseOperationReq(opHandle.toTOperationHandle());
         TCloseOperationResp resp = this.cliService.CloseOperation(req);
         this.checkStatus(resp.getStatus());
      } catch (HiveSQLException e) {
         throw e;
      } catch (Exception e) {
         throw new HiveSQLException(e);
      }
   }

   public TTableSchema getResultSetMetadata(OperationHandle opHandle) throws HiveSQLException {
      try {
         TGetResultSetMetadataReq req = new TGetResultSetMetadataReq(opHandle.toTOperationHandle());
         TGetResultSetMetadataResp resp = this.cliService.GetResultSetMetadata(req);
         this.checkStatus(resp.getStatus());
         return resp.getSchema();
      } catch (HiveSQLException e) {
         throw e;
      } catch (Exception e) {
         throw new HiveSQLException(e);
      }
   }

   public TRowSet fetchResults(OperationHandle opHandle, FetchOrientation orientation, long maxRows, FetchType fetchType) throws HiveSQLException {
      try {
         TFetchResultsReq req = new TFetchResultsReq();
         req.setOperationHandle(opHandle.toTOperationHandle());
         req.setOrientation(orientation.toTFetchOrientation());
         req.setMaxRows(maxRows);
         req.setFetchType(fetchType.toTFetchType());
         TFetchResultsResp resp = this.cliService.FetchResults(req);
         this.checkStatus(resp.getStatus());
         return resp.getResults();
      } catch (HiveSQLException e) {
         throw e;
      } catch (Exception e) {
         throw new HiveSQLException(e);
      }
   }

   public TRowSet fetchResults(OperationHandle opHandle) throws HiveSQLException {
      return this.fetchResults(opHandle, FetchOrientation.FETCH_NEXT, 10000L, FetchType.QUERY_OUTPUT);
   }

   public String getDelegationToken(SessionHandle sessionHandle, HiveAuthFactory authFactory, String owner, String renewer) throws HiveSQLException {
      TGetDelegationTokenReq req = new TGetDelegationTokenReq(sessionHandle.toTSessionHandle(), owner, renewer);

      try {
         TGetDelegationTokenResp tokenResp = this.cliService.GetDelegationToken(req);
         this.checkStatus(tokenResp.getStatus());
         return tokenResp.getDelegationToken();
      } catch (Exception e) {
         throw new HiveSQLException(e);
      }
   }

   public void cancelDelegationToken(SessionHandle sessionHandle, HiveAuthFactory authFactory, String tokenStr) throws HiveSQLException {
      TCancelDelegationTokenReq cancelReq = new TCancelDelegationTokenReq(sessionHandle.toTSessionHandle(), tokenStr);

      try {
         TCancelDelegationTokenResp cancelResp = this.cliService.CancelDelegationToken(cancelReq);
         this.checkStatus(cancelResp.getStatus());
      } catch (TException e) {
         throw new HiveSQLException(e);
      }
   }

   public void renewDelegationToken(SessionHandle sessionHandle, HiveAuthFactory authFactory, String tokenStr) throws HiveSQLException {
      TRenewDelegationTokenReq cancelReq = new TRenewDelegationTokenReq(sessionHandle.toTSessionHandle(), tokenStr);

      try {
         TRenewDelegationTokenResp renewResp = this.cliService.RenewDelegationToken(cancelReq);
         this.checkStatus(renewResp.getStatus());
      } catch (Exception e) {
         throw new HiveSQLException(e);
      }
   }

   public OperationHandle getPrimaryKeys(SessionHandle sessionHandle, String catalog, String schema, String table) throws HiveSQLException {
      try {
         TGetPrimaryKeysReq req = new TGetPrimaryKeysReq(sessionHandle.toTSessionHandle());
         req.setCatalogName(catalog);
         req.setSchemaName(schema);
         req.setTableName(table);
         TGetPrimaryKeysResp resp = this.cliService.GetPrimaryKeys(req);
         this.checkStatus(resp.getStatus());
         TProtocolVersion protocol = sessionHandle.getProtocolVersion();
         return new OperationHandle(resp.getOperationHandle(), protocol);
      } catch (HiveSQLException e) {
         throw e;
      } catch (Exception e) {
         throw new HiveSQLException(e);
      }
   }

   public OperationHandle getCrossReference(SessionHandle sessionHandle, String primaryCatalog, String primarySchema, String primaryTable, String foreignCatalog, String foreignSchema, String foreignTable) throws HiveSQLException {
      try {
         TGetCrossReferenceReq req = new TGetCrossReferenceReq(sessionHandle.toTSessionHandle());
         req.setParentCatalogName(primaryCatalog);
         req.setParentSchemaName(primarySchema);
         req.setParentTableName(primaryTable);
         req.setForeignCatalogName(foreignCatalog);
         req.setForeignSchemaName(foreignSchema);
         req.setForeignTableName(foreignTable);
         TGetCrossReferenceResp resp = this.cliService.GetCrossReference(req);
         this.checkStatus(resp.getStatus());
         TProtocolVersion protocol = sessionHandle.getProtocolVersion();
         return new OperationHandle(resp.getOperationHandle(), protocol);
      } catch (HiveSQLException e) {
         throw e;
      } catch (Exception e) {
         throw new HiveSQLException(e);
      }
   }

   public String getQueryId(TOperationHandle operationHandle) throws HiveSQLException {
      try {
         return this.cliService.GetQueryId(new TGetQueryIdReq(operationHandle)).getQueryId();
      } catch (TException e) {
         throw new HiveSQLException(e);
      }
   }
}
