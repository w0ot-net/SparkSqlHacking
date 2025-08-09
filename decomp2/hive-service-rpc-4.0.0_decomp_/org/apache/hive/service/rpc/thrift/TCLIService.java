package org.apache.hive.service.rpc.thrift;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import org.apache.hadoop.hive.common.classification.InterfaceAudience.Public;
import org.apache.hadoop.hive.common.classification.InterfaceStability.Stable;
import org.apache.thrift.AsyncProcessFunction;
import org.apache.thrift.ProcessFunction;
import org.apache.thrift.TApplicationException;
import org.apache.thrift.TBase;
import org.apache.thrift.TBaseAsyncProcessor;
import org.apache.thrift.TBaseHelper;
import org.apache.thrift.TBaseProcessor;
import org.apache.thrift.TException;
import org.apache.thrift.TFieldIdEnum;
import org.apache.thrift.TProcessor;
import org.apache.thrift.TSerializable;
import org.apache.thrift.TServiceClient;
import org.apache.thrift.TServiceClientFactory;
import org.apache.thrift.annotation.Nullable;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.async.TAsyncClient;
import org.apache.thrift.async.TAsyncClientFactory;
import org.apache.thrift.async.TAsyncClientManager;
import org.apache.thrift.async.TAsyncMethodCall;
import org.apache.thrift.async.TAsyncMethodCall.State;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.StructMetaData;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TMessage;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;
import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.server.AbstractNonblockingServer;
import org.apache.thrift.transport.TIOStreamTransport;
import org.apache.thrift.transport.TMemoryInputTransport;
import org.apache.thrift.transport.TNonblockingTransport;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Public
@Stable
public class TCLIService {
   @Public
   @Stable
   public static class Client extends TServiceClient implements Iface {
      public Client(TProtocol prot) {
         super(prot, prot);
      }

      public Client(TProtocol iprot, TProtocol oprot) {
         super(iprot, oprot);
      }

      public TOpenSessionResp OpenSession(TOpenSessionReq req) throws TException {
         this.send_OpenSession(req);
         return this.recv_OpenSession();
      }

      public void send_OpenSession(TOpenSessionReq req) throws TException {
         OpenSession_args args = new OpenSession_args();
         args.setReq(req);
         this.sendBase("OpenSession", args);
      }

      public TOpenSessionResp recv_OpenSession() throws TException {
         OpenSession_result result = new OpenSession_result();
         this.receiveBase(result, "OpenSession");
         if (result.isSetSuccess()) {
            return result.success;
         } else {
            throw new TApplicationException(5, "OpenSession failed: unknown result");
         }
      }

      public TCloseSessionResp CloseSession(TCloseSessionReq req) throws TException {
         this.send_CloseSession(req);
         return this.recv_CloseSession();
      }

      public void send_CloseSession(TCloseSessionReq req) throws TException {
         CloseSession_args args = new CloseSession_args();
         args.setReq(req);
         this.sendBase("CloseSession", args);
      }

      public TCloseSessionResp recv_CloseSession() throws TException {
         CloseSession_result result = new CloseSession_result();
         this.receiveBase(result, "CloseSession");
         if (result.isSetSuccess()) {
            return result.success;
         } else {
            throw new TApplicationException(5, "CloseSession failed: unknown result");
         }
      }

      public TGetInfoResp GetInfo(TGetInfoReq req) throws TException {
         this.send_GetInfo(req);
         return this.recv_GetInfo();
      }

      public void send_GetInfo(TGetInfoReq req) throws TException {
         GetInfo_args args = new GetInfo_args();
         args.setReq(req);
         this.sendBase("GetInfo", args);
      }

      public TGetInfoResp recv_GetInfo() throws TException {
         GetInfo_result result = new GetInfo_result();
         this.receiveBase(result, "GetInfo");
         if (result.isSetSuccess()) {
            return result.success;
         } else {
            throw new TApplicationException(5, "GetInfo failed: unknown result");
         }
      }

      public TExecuteStatementResp ExecuteStatement(TExecuteStatementReq req) throws TException {
         this.send_ExecuteStatement(req);
         return this.recv_ExecuteStatement();
      }

      public void send_ExecuteStatement(TExecuteStatementReq req) throws TException {
         ExecuteStatement_args args = new ExecuteStatement_args();
         args.setReq(req);
         this.sendBase("ExecuteStatement", args);
      }

      public TExecuteStatementResp recv_ExecuteStatement() throws TException {
         ExecuteStatement_result result = new ExecuteStatement_result();
         this.receiveBase(result, "ExecuteStatement");
         if (result.isSetSuccess()) {
            return result.success;
         } else {
            throw new TApplicationException(5, "ExecuteStatement failed: unknown result");
         }
      }

      public TGetTypeInfoResp GetTypeInfo(TGetTypeInfoReq req) throws TException {
         this.send_GetTypeInfo(req);
         return this.recv_GetTypeInfo();
      }

      public void send_GetTypeInfo(TGetTypeInfoReq req) throws TException {
         GetTypeInfo_args args = new GetTypeInfo_args();
         args.setReq(req);
         this.sendBase("GetTypeInfo", args);
      }

      public TGetTypeInfoResp recv_GetTypeInfo() throws TException {
         GetTypeInfo_result result = new GetTypeInfo_result();
         this.receiveBase(result, "GetTypeInfo");
         if (result.isSetSuccess()) {
            return result.success;
         } else {
            throw new TApplicationException(5, "GetTypeInfo failed: unknown result");
         }
      }

      public TGetCatalogsResp GetCatalogs(TGetCatalogsReq req) throws TException {
         this.send_GetCatalogs(req);
         return this.recv_GetCatalogs();
      }

      public void send_GetCatalogs(TGetCatalogsReq req) throws TException {
         GetCatalogs_args args = new GetCatalogs_args();
         args.setReq(req);
         this.sendBase("GetCatalogs", args);
      }

      public TGetCatalogsResp recv_GetCatalogs() throws TException {
         GetCatalogs_result result = new GetCatalogs_result();
         this.receiveBase(result, "GetCatalogs");
         if (result.isSetSuccess()) {
            return result.success;
         } else {
            throw new TApplicationException(5, "GetCatalogs failed: unknown result");
         }
      }

      public TGetSchemasResp GetSchemas(TGetSchemasReq req) throws TException {
         this.send_GetSchemas(req);
         return this.recv_GetSchemas();
      }

      public void send_GetSchemas(TGetSchemasReq req) throws TException {
         GetSchemas_args args = new GetSchemas_args();
         args.setReq(req);
         this.sendBase("GetSchemas", args);
      }

      public TGetSchemasResp recv_GetSchemas() throws TException {
         GetSchemas_result result = new GetSchemas_result();
         this.receiveBase(result, "GetSchemas");
         if (result.isSetSuccess()) {
            return result.success;
         } else {
            throw new TApplicationException(5, "GetSchemas failed: unknown result");
         }
      }

      public TGetTablesResp GetTables(TGetTablesReq req) throws TException {
         this.send_GetTables(req);
         return this.recv_GetTables();
      }

      public void send_GetTables(TGetTablesReq req) throws TException {
         GetTables_args args = new GetTables_args();
         args.setReq(req);
         this.sendBase("GetTables", args);
      }

      public TGetTablesResp recv_GetTables() throws TException {
         GetTables_result result = new GetTables_result();
         this.receiveBase(result, "GetTables");
         if (result.isSetSuccess()) {
            return result.success;
         } else {
            throw new TApplicationException(5, "GetTables failed: unknown result");
         }
      }

      public TGetTableTypesResp GetTableTypes(TGetTableTypesReq req) throws TException {
         this.send_GetTableTypes(req);
         return this.recv_GetTableTypes();
      }

      public void send_GetTableTypes(TGetTableTypesReq req) throws TException {
         GetTableTypes_args args = new GetTableTypes_args();
         args.setReq(req);
         this.sendBase("GetTableTypes", args);
      }

      public TGetTableTypesResp recv_GetTableTypes() throws TException {
         GetTableTypes_result result = new GetTableTypes_result();
         this.receiveBase(result, "GetTableTypes");
         if (result.isSetSuccess()) {
            return result.success;
         } else {
            throw new TApplicationException(5, "GetTableTypes failed: unknown result");
         }
      }

      public TGetColumnsResp GetColumns(TGetColumnsReq req) throws TException {
         this.send_GetColumns(req);
         return this.recv_GetColumns();
      }

      public void send_GetColumns(TGetColumnsReq req) throws TException {
         GetColumns_args args = new GetColumns_args();
         args.setReq(req);
         this.sendBase("GetColumns", args);
      }

      public TGetColumnsResp recv_GetColumns() throws TException {
         GetColumns_result result = new GetColumns_result();
         this.receiveBase(result, "GetColumns");
         if (result.isSetSuccess()) {
            return result.success;
         } else {
            throw new TApplicationException(5, "GetColumns failed: unknown result");
         }
      }

      public TGetFunctionsResp GetFunctions(TGetFunctionsReq req) throws TException {
         this.send_GetFunctions(req);
         return this.recv_GetFunctions();
      }

      public void send_GetFunctions(TGetFunctionsReq req) throws TException {
         GetFunctions_args args = new GetFunctions_args();
         args.setReq(req);
         this.sendBase("GetFunctions", args);
      }

      public TGetFunctionsResp recv_GetFunctions() throws TException {
         GetFunctions_result result = new GetFunctions_result();
         this.receiveBase(result, "GetFunctions");
         if (result.isSetSuccess()) {
            return result.success;
         } else {
            throw new TApplicationException(5, "GetFunctions failed: unknown result");
         }
      }

      public TGetPrimaryKeysResp GetPrimaryKeys(TGetPrimaryKeysReq req) throws TException {
         this.send_GetPrimaryKeys(req);
         return this.recv_GetPrimaryKeys();
      }

      public void send_GetPrimaryKeys(TGetPrimaryKeysReq req) throws TException {
         GetPrimaryKeys_args args = new GetPrimaryKeys_args();
         args.setReq(req);
         this.sendBase("GetPrimaryKeys", args);
      }

      public TGetPrimaryKeysResp recv_GetPrimaryKeys() throws TException {
         GetPrimaryKeys_result result = new GetPrimaryKeys_result();
         this.receiveBase(result, "GetPrimaryKeys");
         if (result.isSetSuccess()) {
            return result.success;
         } else {
            throw new TApplicationException(5, "GetPrimaryKeys failed: unknown result");
         }
      }

      public TGetCrossReferenceResp GetCrossReference(TGetCrossReferenceReq req) throws TException {
         this.send_GetCrossReference(req);
         return this.recv_GetCrossReference();
      }

      public void send_GetCrossReference(TGetCrossReferenceReq req) throws TException {
         GetCrossReference_args args = new GetCrossReference_args();
         args.setReq(req);
         this.sendBase("GetCrossReference", args);
      }

      public TGetCrossReferenceResp recv_GetCrossReference() throws TException {
         GetCrossReference_result result = new GetCrossReference_result();
         this.receiveBase(result, "GetCrossReference");
         if (result.isSetSuccess()) {
            return result.success;
         } else {
            throw new TApplicationException(5, "GetCrossReference failed: unknown result");
         }
      }

      public TGetOperationStatusResp GetOperationStatus(TGetOperationStatusReq req) throws TException {
         this.send_GetOperationStatus(req);
         return this.recv_GetOperationStatus();
      }

      public void send_GetOperationStatus(TGetOperationStatusReq req) throws TException {
         GetOperationStatus_args args = new GetOperationStatus_args();
         args.setReq(req);
         this.sendBase("GetOperationStatus", args);
      }

      public TGetOperationStatusResp recv_GetOperationStatus() throws TException {
         GetOperationStatus_result result = new GetOperationStatus_result();
         this.receiveBase(result, "GetOperationStatus");
         if (result.isSetSuccess()) {
            return result.success;
         } else {
            throw new TApplicationException(5, "GetOperationStatus failed: unknown result");
         }
      }

      public TCancelOperationResp CancelOperation(TCancelOperationReq req) throws TException {
         this.send_CancelOperation(req);
         return this.recv_CancelOperation();
      }

      public void send_CancelOperation(TCancelOperationReq req) throws TException {
         CancelOperation_args args = new CancelOperation_args();
         args.setReq(req);
         this.sendBase("CancelOperation", args);
      }

      public TCancelOperationResp recv_CancelOperation() throws TException {
         CancelOperation_result result = new CancelOperation_result();
         this.receiveBase(result, "CancelOperation");
         if (result.isSetSuccess()) {
            return result.success;
         } else {
            throw new TApplicationException(5, "CancelOperation failed: unknown result");
         }
      }

      public TCloseOperationResp CloseOperation(TCloseOperationReq req) throws TException {
         this.send_CloseOperation(req);
         return this.recv_CloseOperation();
      }

      public void send_CloseOperation(TCloseOperationReq req) throws TException {
         CloseOperation_args args = new CloseOperation_args();
         args.setReq(req);
         this.sendBase("CloseOperation", args);
      }

      public TCloseOperationResp recv_CloseOperation() throws TException {
         CloseOperation_result result = new CloseOperation_result();
         this.receiveBase(result, "CloseOperation");
         if (result.isSetSuccess()) {
            return result.success;
         } else {
            throw new TApplicationException(5, "CloseOperation failed: unknown result");
         }
      }

      public TGetResultSetMetadataResp GetResultSetMetadata(TGetResultSetMetadataReq req) throws TException {
         this.send_GetResultSetMetadata(req);
         return this.recv_GetResultSetMetadata();
      }

      public void send_GetResultSetMetadata(TGetResultSetMetadataReq req) throws TException {
         GetResultSetMetadata_args args = new GetResultSetMetadata_args();
         args.setReq(req);
         this.sendBase("GetResultSetMetadata", args);
      }

      public TGetResultSetMetadataResp recv_GetResultSetMetadata() throws TException {
         GetResultSetMetadata_result result = new GetResultSetMetadata_result();
         this.receiveBase(result, "GetResultSetMetadata");
         if (result.isSetSuccess()) {
            return result.success;
         } else {
            throw new TApplicationException(5, "GetResultSetMetadata failed: unknown result");
         }
      }

      public TFetchResultsResp FetchResults(TFetchResultsReq req) throws TException {
         this.send_FetchResults(req);
         return this.recv_FetchResults();
      }

      public void send_FetchResults(TFetchResultsReq req) throws TException {
         FetchResults_args args = new FetchResults_args();
         args.setReq(req);
         this.sendBase("FetchResults", args);
      }

      public TFetchResultsResp recv_FetchResults() throws TException {
         FetchResults_result result = new FetchResults_result();
         this.receiveBase(result, "FetchResults");
         if (result.isSetSuccess()) {
            return result.success;
         } else {
            throw new TApplicationException(5, "FetchResults failed: unknown result");
         }
      }

      public TGetDelegationTokenResp GetDelegationToken(TGetDelegationTokenReq req) throws TException {
         this.send_GetDelegationToken(req);
         return this.recv_GetDelegationToken();
      }

      public void send_GetDelegationToken(TGetDelegationTokenReq req) throws TException {
         GetDelegationToken_args args = new GetDelegationToken_args();
         args.setReq(req);
         this.sendBase("GetDelegationToken", args);
      }

      public TGetDelegationTokenResp recv_GetDelegationToken() throws TException {
         GetDelegationToken_result result = new GetDelegationToken_result();
         this.receiveBase(result, "GetDelegationToken");
         if (result.isSetSuccess()) {
            return result.success;
         } else {
            throw new TApplicationException(5, "GetDelegationToken failed: unknown result");
         }
      }

      public TCancelDelegationTokenResp CancelDelegationToken(TCancelDelegationTokenReq req) throws TException {
         this.send_CancelDelegationToken(req);
         return this.recv_CancelDelegationToken();
      }

      public void send_CancelDelegationToken(TCancelDelegationTokenReq req) throws TException {
         CancelDelegationToken_args args = new CancelDelegationToken_args();
         args.setReq(req);
         this.sendBase("CancelDelegationToken", args);
      }

      public TCancelDelegationTokenResp recv_CancelDelegationToken() throws TException {
         CancelDelegationToken_result result = new CancelDelegationToken_result();
         this.receiveBase(result, "CancelDelegationToken");
         if (result.isSetSuccess()) {
            return result.success;
         } else {
            throw new TApplicationException(5, "CancelDelegationToken failed: unknown result");
         }
      }

      public TRenewDelegationTokenResp RenewDelegationToken(TRenewDelegationTokenReq req) throws TException {
         this.send_RenewDelegationToken(req);
         return this.recv_RenewDelegationToken();
      }

      public void send_RenewDelegationToken(TRenewDelegationTokenReq req) throws TException {
         RenewDelegationToken_args args = new RenewDelegationToken_args();
         args.setReq(req);
         this.sendBase("RenewDelegationToken", args);
      }

      public TRenewDelegationTokenResp recv_RenewDelegationToken() throws TException {
         RenewDelegationToken_result result = new RenewDelegationToken_result();
         this.receiveBase(result, "RenewDelegationToken");
         if (result.isSetSuccess()) {
            return result.success;
         } else {
            throw new TApplicationException(5, "RenewDelegationToken failed: unknown result");
         }
      }

      public TGetQueryIdResp GetQueryId(TGetQueryIdReq req) throws TException {
         this.send_GetQueryId(req);
         return this.recv_GetQueryId();
      }

      public void send_GetQueryId(TGetQueryIdReq req) throws TException {
         GetQueryId_args args = new GetQueryId_args();
         args.setReq(req);
         this.sendBase("GetQueryId", args);
      }

      public TGetQueryIdResp recv_GetQueryId() throws TException {
         GetQueryId_result result = new GetQueryId_result();
         this.receiveBase(result, "GetQueryId");
         if (result.isSetSuccess()) {
            return result.success;
         } else {
            throw new TApplicationException(5, "GetQueryId failed: unknown result");
         }
      }

      public TSetClientInfoResp SetClientInfo(TSetClientInfoReq req) throws TException {
         this.send_SetClientInfo(req);
         return this.recv_SetClientInfo();
      }

      public void send_SetClientInfo(TSetClientInfoReq req) throws TException {
         SetClientInfo_args args = new SetClientInfo_args();
         args.setReq(req);
         this.sendBase("SetClientInfo", args);
      }

      public TSetClientInfoResp recv_SetClientInfo() throws TException {
         SetClientInfo_result result = new SetClientInfo_result();
         this.receiveBase(result, "SetClientInfo");
         if (result.isSetSuccess()) {
            return result.success;
         } else {
            throw new TApplicationException(5, "SetClientInfo failed: unknown result");
         }
      }

      public TUploadDataResp UploadData(TUploadDataReq req) throws TException {
         this.send_UploadData(req);
         return this.recv_UploadData();
      }

      public void send_UploadData(TUploadDataReq req) throws TException {
         UploadData_args args = new UploadData_args();
         args.setReq(req);
         this.sendBase("UploadData", args);
      }

      public TUploadDataResp recv_UploadData() throws TException {
         UploadData_result result = new UploadData_result();
         this.receiveBase(result, "UploadData");
         if (result.isSetSuccess()) {
            return result.success;
         } else {
            throw new TApplicationException(5, "UploadData failed: unknown result");
         }
      }

      public TDownloadDataResp DownloadData(TDownloadDataReq req) throws TException {
         this.send_DownloadData(req);
         return this.recv_DownloadData();
      }

      public void send_DownloadData(TDownloadDataReq req) throws TException {
         DownloadData_args args = new DownloadData_args();
         args.setReq(req);
         this.sendBase("DownloadData", args);
      }

      public TDownloadDataResp recv_DownloadData() throws TException {
         DownloadData_result result = new DownloadData_result();
         this.receiveBase(result, "DownloadData");
         if (result.isSetSuccess()) {
            return result.success;
         } else {
            throw new TApplicationException(5, "DownloadData failed: unknown result");
         }
      }

      @Public
      @Stable
      public static class Factory implements TServiceClientFactory {
         public Client getClient(TProtocol prot) {
            return new Client(prot);
         }

         public Client getClient(TProtocol iprot, TProtocol oprot) {
            return new Client(iprot, oprot);
         }
      }
   }

   @Public
   @Stable
   public static class AsyncClient extends TAsyncClient implements AsyncIface {
      public AsyncClient(TProtocolFactory protocolFactory, TAsyncClientManager clientManager, TNonblockingTransport transport) {
         super(protocolFactory, clientManager, transport);
      }

      public void OpenSession(TOpenSessionReq req, AsyncMethodCallback resultHandler) throws TException {
         this.checkReady();
         OpenSession_call method_call = new OpenSession_call(req, resultHandler, this, this.___protocolFactory, this.___transport);
         this.___currentMethod = method_call;
         this.___manager.call(method_call);
      }

      public void CloseSession(TCloseSessionReq req, AsyncMethodCallback resultHandler) throws TException {
         this.checkReady();
         CloseSession_call method_call = new CloseSession_call(req, resultHandler, this, this.___protocolFactory, this.___transport);
         this.___currentMethod = method_call;
         this.___manager.call(method_call);
      }

      public void GetInfo(TGetInfoReq req, AsyncMethodCallback resultHandler) throws TException {
         this.checkReady();
         GetInfo_call method_call = new GetInfo_call(req, resultHandler, this, this.___protocolFactory, this.___transport);
         this.___currentMethod = method_call;
         this.___manager.call(method_call);
      }

      public void ExecuteStatement(TExecuteStatementReq req, AsyncMethodCallback resultHandler) throws TException {
         this.checkReady();
         ExecuteStatement_call method_call = new ExecuteStatement_call(req, resultHandler, this, this.___protocolFactory, this.___transport);
         this.___currentMethod = method_call;
         this.___manager.call(method_call);
      }

      public void GetTypeInfo(TGetTypeInfoReq req, AsyncMethodCallback resultHandler) throws TException {
         this.checkReady();
         GetTypeInfo_call method_call = new GetTypeInfo_call(req, resultHandler, this, this.___protocolFactory, this.___transport);
         this.___currentMethod = method_call;
         this.___manager.call(method_call);
      }

      public void GetCatalogs(TGetCatalogsReq req, AsyncMethodCallback resultHandler) throws TException {
         this.checkReady();
         GetCatalogs_call method_call = new GetCatalogs_call(req, resultHandler, this, this.___protocolFactory, this.___transport);
         this.___currentMethod = method_call;
         this.___manager.call(method_call);
      }

      public void GetSchemas(TGetSchemasReq req, AsyncMethodCallback resultHandler) throws TException {
         this.checkReady();
         GetSchemas_call method_call = new GetSchemas_call(req, resultHandler, this, this.___protocolFactory, this.___transport);
         this.___currentMethod = method_call;
         this.___manager.call(method_call);
      }

      public void GetTables(TGetTablesReq req, AsyncMethodCallback resultHandler) throws TException {
         this.checkReady();
         GetTables_call method_call = new GetTables_call(req, resultHandler, this, this.___protocolFactory, this.___transport);
         this.___currentMethod = method_call;
         this.___manager.call(method_call);
      }

      public void GetTableTypes(TGetTableTypesReq req, AsyncMethodCallback resultHandler) throws TException {
         this.checkReady();
         GetTableTypes_call method_call = new GetTableTypes_call(req, resultHandler, this, this.___protocolFactory, this.___transport);
         this.___currentMethod = method_call;
         this.___manager.call(method_call);
      }

      public void GetColumns(TGetColumnsReq req, AsyncMethodCallback resultHandler) throws TException {
         this.checkReady();
         GetColumns_call method_call = new GetColumns_call(req, resultHandler, this, this.___protocolFactory, this.___transport);
         this.___currentMethod = method_call;
         this.___manager.call(method_call);
      }

      public void GetFunctions(TGetFunctionsReq req, AsyncMethodCallback resultHandler) throws TException {
         this.checkReady();
         GetFunctions_call method_call = new GetFunctions_call(req, resultHandler, this, this.___protocolFactory, this.___transport);
         this.___currentMethod = method_call;
         this.___manager.call(method_call);
      }

      public void GetPrimaryKeys(TGetPrimaryKeysReq req, AsyncMethodCallback resultHandler) throws TException {
         this.checkReady();
         GetPrimaryKeys_call method_call = new GetPrimaryKeys_call(req, resultHandler, this, this.___protocolFactory, this.___transport);
         this.___currentMethod = method_call;
         this.___manager.call(method_call);
      }

      public void GetCrossReference(TGetCrossReferenceReq req, AsyncMethodCallback resultHandler) throws TException {
         this.checkReady();
         GetCrossReference_call method_call = new GetCrossReference_call(req, resultHandler, this, this.___protocolFactory, this.___transport);
         this.___currentMethod = method_call;
         this.___manager.call(method_call);
      }

      public void GetOperationStatus(TGetOperationStatusReq req, AsyncMethodCallback resultHandler) throws TException {
         this.checkReady();
         GetOperationStatus_call method_call = new GetOperationStatus_call(req, resultHandler, this, this.___protocolFactory, this.___transport);
         this.___currentMethod = method_call;
         this.___manager.call(method_call);
      }

      public void CancelOperation(TCancelOperationReq req, AsyncMethodCallback resultHandler) throws TException {
         this.checkReady();
         CancelOperation_call method_call = new CancelOperation_call(req, resultHandler, this, this.___protocolFactory, this.___transport);
         this.___currentMethod = method_call;
         this.___manager.call(method_call);
      }

      public void CloseOperation(TCloseOperationReq req, AsyncMethodCallback resultHandler) throws TException {
         this.checkReady();
         CloseOperation_call method_call = new CloseOperation_call(req, resultHandler, this, this.___protocolFactory, this.___transport);
         this.___currentMethod = method_call;
         this.___manager.call(method_call);
      }

      public void GetResultSetMetadata(TGetResultSetMetadataReq req, AsyncMethodCallback resultHandler) throws TException {
         this.checkReady();
         GetResultSetMetadata_call method_call = new GetResultSetMetadata_call(req, resultHandler, this, this.___protocolFactory, this.___transport);
         this.___currentMethod = method_call;
         this.___manager.call(method_call);
      }

      public void FetchResults(TFetchResultsReq req, AsyncMethodCallback resultHandler) throws TException {
         this.checkReady();
         FetchResults_call method_call = new FetchResults_call(req, resultHandler, this, this.___protocolFactory, this.___transport);
         this.___currentMethod = method_call;
         this.___manager.call(method_call);
      }

      public void GetDelegationToken(TGetDelegationTokenReq req, AsyncMethodCallback resultHandler) throws TException {
         this.checkReady();
         GetDelegationToken_call method_call = new GetDelegationToken_call(req, resultHandler, this, this.___protocolFactory, this.___transport);
         this.___currentMethod = method_call;
         this.___manager.call(method_call);
      }

      public void CancelDelegationToken(TCancelDelegationTokenReq req, AsyncMethodCallback resultHandler) throws TException {
         this.checkReady();
         CancelDelegationToken_call method_call = new CancelDelegationToken_call(req, resultHandler, this, this.___protocolFactory, this.___transport);
         this.___currentMethod = method_call;
         this.___manager.call(method_call);
      }

      public void RenewDelegationToken(TRenewDelegationTokenReq req, AsyncMethodCallback resultHandler) throws TException {
         this.checkReady();
         RenewDelegationToken_call method_call = new RenewDelegationToken_call(req, resultHandler, this, this.___protocolFactory, this.___transport);
         this.___currentMethod = method_call;
         this.___manager.call(method_call);
      }

      public void GetQueryId(TGetQueryIdReq req, AsyncMethodCallback resultHandler) throws TException {
         this.checkReady();
         GetQueryId_call method_call = new GetQueryId_call(req, resultHandler, this, this.___protocolFactory, this.___transport);
         this.___currentMethod = method_call;
         this.___manager.call(method_call);
      }

      public void SetClientInfo(TSetClientInfoReq req, AsyncMethodCallback resultHandler) throws TException {
         this.checkReady();
         SetClientInfo_call method_call = new SetClientInfo_call(req, resultHandler, this, this.___protocolFactory, this.___transport);
         this.___currentMethod = method_call;
         this.___manager.call(method_call);
      }

      public void UploadData(TUploadDataReq req, AsyncMethodCallback resultHandler) throws TException {
         this.checkReady();
         UploadData_call method_call = new UploadData_call(req, resultHandler, this, this.___protocolFactory, this.___transport);
         this.___currentMethod = method_call;
         this.___manager.call(method_call);
      }

      public void DownloadData(TDownloadDataReq req, AsyncMethodCallback resultHandler) throws TException {
         this.checkReady();
         DownloadData_call method_call = new DownloadData_call(req, resultHandler, this, this.___protocolFactory, this.___transport);
         this.___currentMethod = method_call;
         this.___manager.call(method_call);
      }

      @Public
      @Stable
      public static class Factory implements TAsyncClientFactory {
         private TAsyncClientManager clientManager;
         private TProtocolFactory protocolFactory;

         public Factory(TAsyncClientManager clientManager, TProtocolFactory protocolFactory) {
            this.clientManager = clientManager;
            this.protocolFactory = protocolFactory;
         }

         public AsyncClient getAsyncClient(TNonblockingTransport transport) {
            return new AsyncClient(this.protocolFactory, this.clientManager, transport);
         }
      }

      @Public
      @Stable
      public static class OpenSession_call extends TAsyncMethodCall {
         private TOpenSessionReq req;

         public OpenSession_call(TOpenSessionReq req, AsyncMethodCallback resultHandler, TAsyncClient client, TProtocolFactory protocolFactory, TNonblockingTransport transport) throws TException {
            super(client, protocolFactory, transport, resultHandler, false);
            this.req = req;
         }

         public void write_args(TProtocol prot) throws TException {
            prot.writeMessageBegin(new TMessage("OpenSession", (byte)1, 0));
            OpenSession_args args = new OpenSession_args();
            args.setReq(this.req);
            args.write(prot);
            prot.writeMessageEnd();
         }

         public TOpenSessionResp getResult() throws TException {
            if (this.getState() != State.RESPONSE_READ) {
               throw new IllegalStateException("Method call not finished!");
            } else {
               TMemoryInputTransport memoryTransport = new TMemoryInputTransport(this.getFrameBuffer().array());
               TProtocol prot = this.client.getProtocolFactory().getProtocol(memoryTransport);
               return (new Client(prot)).recv_OpenSession();
            }
         }
      }

      @Public
      @Stable
      public static class CloseSession_call extends TAsyncMethodCall {
         private TCloseSessionReq req;

         public CloseSession_call(TCloseSessionReq req, AsyncMethodCallback resultHandler, TAsyncClient client, TProtocolFactory protocolFactory, TNonblockingTransport transport) throws TException {
            super(client, protocolFactory, transport, resultHandler, false);
            this.req = req;
         }

         public void write_args(TProtocol prot) throws TException {
            prot.writeMessageBegin(new TMessage("CloseSession", (byte)1, 0));
            CloseSession_args args = new CloseSession_args();
            args.setReq(this.req);
            args.write(prot);
            prot.writeMessageEnd();
         }

         public TCloseSessionResp getResult() throws TException {
            if (this.getState() != State.RESPONSE_READ) {
               throw new IllegalStateException("Method call not finished!");
            } else {
               TMemoryInputTransport memoryTransport = new TMemoryInputTransport(this.getFrameBuffer().array());
               TProtocol prot = this.client.getProtocolFactory().getProtocol(memoryTransport);
               return (new Client(prot)).recv_CloseSession();
            }
         }
      }

      @Public
      @Stable
      public static class GetInfo_call extends TAsyncMethodCall {
         private TGetInfoReq req;

         public GetInfo_call(TGetInfoReq req, AsyncMethodCallback resultHandler, TAsyncClient client, TProtocolFactory protocolFactory, TNonblockingTransport transport) throws TException {
            super(client, protocolFactory, transport, resultHandler, false);
            this.req = req;
         }

         public void write_args(TProtocol prot) throws TException {
            prot.writeMessageBegin(new TMessage("GetInfo", (byte)1, 0));
            GetInfo_args args = new GetInfo_args();
            args.setReq(this.req);
            args.write(prot);
            prot.writeMessageEnd();
         }

         public TGetInfoResp getResult() throws TException {
            if (this.getState() != State.RESPONSE_READ) {
               throw new IllegalStateException("Method call not finished!");
            } else {
               TMemoryInputTransport memoryTransport = new TMemoryInputTransport(this.getFrameBuffer().array());
               TProtocol prot = this.client.getProtocolFactory().getProtocol(memoryTransport);
               return (new Client(prot)).recv_GetInfo();
            }
         }
      }

      @Public
      @Stable
      public static class ExecuteStatement_call extends TAsyncMethodCall {
         private TExecuteStatementReq req;

         public ExecuteStatement_call(TExecuteStatementReq req, AsyncMethodCallback resultHandler, TAsyncClient client, TProtocolFactory protocolFactory, TNonblockingTransport transport) throws TException {
            super(client, protocolFactory, transport, resultHandler, false);
            this.req = req;
         }

         public void write_args(TProtocol prot) throws TException {
            prot.writeMessageBegin(new TMessage("ExecuteStatement", (byte)1, 0));
            ExecuteStatement_args args = new ExecuteStatement_args();
            args.setReq(this.req);
            args.write(prot);
            prot.writeMessageEnd();
         }

         public TExecuteStatementResp getResult() throws TException {
            if (this.getState() != State.RESPONSE_READ) {
               throw new IllegalStateException("Method call not finished!");
            } else {
               TMemoryInputTransport memoryTransport = new TMemoryInputTransport(this.getFrameBuffer().array());
               TProtocol prot = this.client.getProtocolFactory().getProtocol(memoryTransport);
               return (new Client(prot)).recv_ExecuteStatement();
            }
         }
      }

      @Public
      @Stable
      public static class GetTypeInfo_call extends TAsyncMethodCall {
         private TGetTypeInfoReq req;

         public GetTypeInfo_call(TGetTypeInfoReq req, AsyncMethodCallback resultHandler, TAsyncClient client, TProtocolFactory protocolFactory, TNonblockingTransport transport) throws TException {
            super(client, protocolFactory, transport, resultHandler, false);
            this.req = req;
         }

         public void write_args(TProtocol prot) throws TException {
            prot.writeMessageBegin(new TMessage("GetTypeInfo", (byte)1, 0));
            GetTypeInfo_args args = new GetTypeInfo_args();
            args.setReq(this.req);
            args.write(prot);
            prot.writeMessageEnd();
         }

         public TGetTypeInfoResp getResult() throws TException {
            if (this.getState() != State.RESPONSE_READ) {
               throw new IllegalStateException("Method call not finished!");
            } else {
               TMemoryInputTransport memoryTransport = new TMemoryInputTransport(this.getFrameBuffer().array());
               TProtocol prot = this.client.getProtocolFactory().getProtocol(memoryTransport);
               return (new Client(prot)).recv_GetTypeInfo();
            }
         }
      }

      @Public
      @Stable
      public static class GetCatalogs_call extends TAsyncMethodCall {
         private TGetCatalogsReq req;

         public GetCatalogs_call(TGetCatalogsReq req, AsyncMethodCallback resultHandler, TAsyncClient client, TProtocolFactory protocolFactory, TNonblockingTransport transport) throws TException {
            super(client, protocolFactory, transport, resultHandler, false);
            this.req = req;
         }

         public void write_args(TProtocol prot) throws TException {
            prot.writeMessageBegin(new TMessage("GetCatalogs", (byte)1, 0));
            GetCatalogs_args args = new GetCatalogs_args();
            args.setReq(this.req);
            args.write(prot);
            prot.writeMessageEnd();
         }

         public TGetCatalogsResp getResult() throws TException {
            if (this.getState() != State.RESPONSE_READ) {
               throw new IllegalStateException("Method call not finished!");
            } else {
               TMemoryInputTransport memoryTransport = new TMemoryInputTransport(this.getFrameBuffer().array());
               TProtocol prot = this.client.getProtocolFactory().getProtocol(memoryTransport);
               return (new Client(prot)).recv_GetCatalogs();
            }
         }
      }

      @Public
      @Stable
      public static class GetSchemas_call extends TAsyncMethodCall {
         private TGetSchemasReq req;

         public GetSchemas_call(TGetSchemasReq req, AsyncMethodCallback resultHandler, TAsyncClient client, TProtocolFactory protocolFactory, TNonblockingTransport transport) throws TException {
            super(client, protocolFactory, transport, resultHandler, false);
            this.req = req;
         }

         public void write_args(TProtocol prot) throws TException {
            prot.writeMessageBegin(new TMessage("GetSchemas", (byte)1, 0));
            GetSchemas_args args = new GetSchemas_args();
            args.setReq(this.req);
            args.write(prot);
            prot.writeMessageEnd();
         }

         public TGetSchemasResp getResult() throws TException {
            if (this.getState() != State.RESPONSE_READ) {
               throw new IllegalStateException("Method call not finished!");
            } else {
               TMemoryInputTransport memoryTransport = new TMemoryInputTransport(this.getFrameBuffer().array());
               TProtocol prot = this.client.getProtocolFactory().getProtocol(memoryTransport);
               return (new Client(prot)).recv_GetSchemas();
            }
         }
      }

      @Public
      @Stable
      public static class GetTables_call extends TAsyncMethodCall {
         private TGetTablesReq req;

         public GetTables_call(TGetTablesReq req, AsyncMethodCallback resultHandler, TAsyncClient client, TProtocolFactory protocolFactory, TNonblockingTransport transport) throws TException {
            super(client, protocolFactory, transport, resultHandler, false);
            this.req = req;
         }

         public void write_args(TProtocol prot) throws TException {
            prot.writeMessageBegin(new TMessage("GetTables", (byte)1, 0));
            GetTables_args args = new GetTables_args();
            args.setReq(this.req);
            args.write(prot);
            prot.writeMessageEnd();
         }

         public TGetTablesResp getResult() throws TException {
            if (this.getState() != State.RESPONSE_READ) {
               throw new IllegalStateException("Method call not finished!");
            } else {
               TMemoryInputTransport memoryTransport = new TMemoryInputTransport(this.getFrameBuffer().array());
               TProtocol prot = this.client.getProtocolFactory().getProtocol(memoryTransport);
               return (new Client(prot)).recv_GetTables();
            }
         }
      }

      @Public
      @Stable
      public static class GetTableTypes_call extends TAsyncMethodCall {
         private TGetTableTypesReq req;

         public GetTableTypes_call(TGetTableTypesReq req, AsyncMethodCallback resultHandler, TAsyncClient client, TProtocolFactory protocolFactory, TNonblockingTransport transport) throws TException {
            super(client, protocolFactory, transport, resultHandler, false);
            this.req = req;
         }

         public void write_args(TProtocol prot) throws TException {
            prot.writeMessageBegin(new TMessage("GetTableTypes", (byte)1, 0));
            GetTableTypes_args args = new GetTableTypes_args();
            args.setReq(this.req);
            args.write(prot);
            prot.writeMessageEnd();
         }

         public TGetTableTypesResp getResult() throws TException {
            if (this.getState() != State.RESPONSE_READ) {
               throw new IllegalStateException("Method call not finished!");
            } else {
               TMemoryInputTransport memoryTransport = new TMemoryInputTransport(this.getFrameBuffer().array());
               TProtocol prot = this.client.getProtocolFactory().getProtocol(memoryTransport);
               return (new Client(prot)).recv_GetTableTypes();
            }
         }
      }

      @Public
      @Stable
      public static class GetColumns_call extends TAsyncMethodCall {
         private TGetColumnsReq req;

         public GetColumns_call(TGetColumnsReq req, AsyncMethodCallback resultHandler, TAsyncClient client, TProtocolFactory protocolFactory, TNonblockingTransport transport) throws TException {
            super(client, protocolFactory, transport, resultHandler, false);
            this.req = req;
         }

         public void write_args(TProtocol prot) throws TException {
            prot.writeMessageBegin(new TMessage("GetColumns", (byte)1, 0));
            GetColumns_args args = new GetColumns_args();
            args.setReq(this.req);
            args.write(prot);
            prot.writeMessageEnd();
         }

         public TGetColumnsResp getResult() throws TException {
            if (this.getState() != State.RESPONSE_READ) {
               throw new IllegalStateException("Method call not finished!");
            } else {
               TMemoryInputTransport memoryTransport = new TMemoryInputTransport(this.getFrameBuffer().array());
               TProtocol prot = this.client.getProtocolFactory().getProtocol(memoryTransport);
               return (new Client(prot)).recv_GetColumns();
            }
         }
      }

      @Public
      @Stable
      public static class GetFunctions_call extends TAsyncMethodCall {
         private TGetFunctionsReq req;

         public GetFunctions_call(TGetFunctionsReq req, AsyncMethodCallback resultHandler, TAsyncClient client, TProtocolFactory protocolFactory, TNonblockingTransport transport) throws TException {
            super(client, protocolFactory, transport, resultHandler, false);
            this.req = req;
         }

         public void write_args(TProtocol prot) throws TException {
            prot.writeMessageBegin(new TMessage("GetFunctions", (byte)1, 0));
            GetFunctions_args args = new GetFunctions_args();
            args.setReq(this.req);
            args.write(prot);
            prot.writeMessageEnd();
         }

         public TGetFunctionsResp getResult() throws TException {
            if (this.getState() != State.RESPONSE_READ) {
               throw new IllegalStateException("Method call not finished!");
            } else {
               TMemoryInputTransport memoryTransport = new TMemoryInputTransport(this.getFrameBuffer().array());
               TProtocol prot = this.client.getProtocolFactory().getProtocol(memoryTransport);
               return (new Client(prot)).recv_GetFunctions();
            }
         }
      }

      @Public
      @Stable
      public static class GetPrimaryKeys_call extends TAsyncMethodCall {
         private TGetPrimaryKeysReq req;

         public GetPrimaryKeys_call(TGetPrimaryKeysReq req, AsyncMethodCallback resultHandler, TAsyncClient client, TProtocolFactory protocolFactory, TNonblockingTransport transport) throws TException {
            super(client, protocolFactory, transport, resultHandler, false);
            this.req = req;
         }

         public void write_args(TProtocol prot) throws TException {
            prot.writeMessageBegin(new TMessage("GetPrimaryKeys", (byte)1, 0));
            GetPrimaryKeys_args args = new GetPrimaryKeys_args();
            args.setReq(this.req);
            args.write(prot);
            prot.writeMessageEnd();
         }

         public TGetPrimaryKeysResp getResult() throws TException {
            if (this.getState() != State.RESPONSE_READ) {
               throw new IllegalStateException("Method call not finished!");
            } else {
               TMemoryInputTransport memoryTransport = new TMemoryInputTransport(this.getFrameBuffer().array());
               TProtocol prot = this.client.getProtocolFactory().getProtocol(memoryTransport);
               return (new Client(prot)).recv_GetPrimaryKeys();
            }
         }
      }

      @Public
      @Stable
      public static class GetCrossReference_call extends TAsyncMethodCall {
         private TGetCrossReferenceReq req;

         public GetCrossReference_call(TGetCrossReferenceReq req, AsyncMethodCallback resultHandler, TAsyncClient client, TProtocolFactory protocolFactory, TNonblockingTransport transport) throws TException {
            super(client, protocolFactory, transport, resultHandler, false);
            this.req = req;
         }

         public void write_args(TProtocol prot) throws TException {
            prot.writeMessageBegin(new TMessage("GetCrossReference", (byte)1, 0));
            GetCrossReference_args args = new GetCrossReference_args();
            args.setReq(this.req);
            args.write(prot);
            prot.writeMessageEnd();
         }

         public TGetCrossReferenceResp getResult() throws TException {
            if (this.getState() != State.RESPONSE_READ) {
               throw new IllegalStateException("Method call not finished!");
            } else {
               TMemoryInputTransport memoryTransport = new TMemoryInputTransport(this.getFrameBuffer().array());
               TProtocol prot = this.client.getProtocolFactory().getProtocol(memoryTransport);
               return (new Client(prot)).recv_GetCrossReference();
            }
         }
      }

      @Public
      @Stable
      public static class GetOperationStatus_call extends TAsyncMethodCall {
         private TGetOperationStatusReq req;

         public GetOperationStatus_call(TGetOperationStatusReq req, AsyncMethodCallback resultHandler, TAsyncClient client, TProtocolFactory protocolFactory, TNonblockingTransport transport) throws TException {
            super(client, protocolFactory, transport, resultHandler, false);
            this.req = req;
         }

         public void write_args(TProtocol prot) throws TException {
            prot.writeMessageBegin(new TMessage("GetOperationStatus", (byte)1, 0));
            GetOperationStatus_args args = new GetOperationStatus_args();
            args.setReq(this.req);
            args.write(prot);
            prot.writeMessageEnd();
         }

         public TGetOperationStatusResp getResult() throws TException {
            if (this.getState() != State.RESPONSE_READ) {
               throw new IllegalStateException("Method call not finished!");
            } else {
               TMemoryInputTransport memoryTransport = new TMemoryInputTransport(this.getFrameBuffer().array());
               TProtocol prot = this.client.getProtocolFactory().getProtocol(memoryTransport);
               return (new Client(prot)).recv_GetOperationStatus();
            }
         }
      }

      @Public
      @Stable
      public static class CancelOperation_call extends TAsyncMethodCall {
         private TCancelOperationReq req;

         public CancelOperation_call(TCancelOperationReq req, AsyncMethodCallback resultHandler, TAsyncClient client, TProtocolFactory protocolFactory, TNonblockingTransport transport) throws TException {
            super(client, protocolFactory, transport, resultHandler, false);
            this.req = req;
         }

         public void write_args(TProtocol prot) throws TException {
            prot.writeMessageBegin(new TMessage("CancelOperation", (byte)1, 0));
            CancelOperation_args args = new CancelOperation_args();
            args.setReq(this.req);
            args.write(prot);
            prot.writeMessageEnd();
         }

         public TCancelOperationResp getResult() throws TException {
            if (this.getState() != State.RESPONSE_READ) {
               throw new IllegalStateException("Method call not finished!");
            } else {
               TMemoryInputTransport memoryTransport = new TMemoryInputTransport(this.getFrameBuffer().array());
               TProtocol prot = this.client.getProtocolFactory().getProtocol(memoryTransport);
               return (new Client(prot)).recv_CancelOperation();
            }
         }
      }

      @Public
      @Stable
      public static class CloseOperation_call extends TAsyncMethodCall {
         private TCloseOperationReq req;

         public CloseOperation_call(TCloseOperationReq req, AsyncMethodCallback resultHandler, TAsyncClient client, TProtocolFactory protocolFactory, TNonblockingTransport transport) throws TException {
            super(client, protocolFactory, transport, resultHandler, false);
            this.req = req;
         }

         public void write_args(TProtocol prot) throws TException {
            prot.writeMessageBegin(new TMessage("CloseOperation", (byte)1, 0));
            CloseOperation_args args = new CloseOperation_args();
            args.setReq(this.req);
            args.write(prot);
            prot.writeMessageEnd();
         }

         public TCloseOperationResp getResult() throws TException {
            if (this.getState() != State.RESPONSE_READ) {
               throw new IllegalStateException("Method call not finished!");
            } else {
               TMemoryInputTransport memoryTransport = new TMemoryInputTransport(this.getFrameBuffer().array());
               TProtocol prot = this.client.getProtocolFactory().getProtocol(memoryTransport);
               return (new Client(prot)).recv_CloseOperation();
            }
         }
      }

      @Public
      @Stable
      public static class GetResultSetMetadata_call extends TAsyncMethodCall {
         private TGetResultSetMetadataReq req;

         public GetResultSetMetadata_call(TGetResultSetMetadataReq req, AsyncMethodCallback resultHandler, TAsyncClient client, TProtocolFactory protocolFactory, TNonblockingTransport transport) throws TException {
            super(client, protocolFactory, transport, resultHandler, false);
            this.req = req;
         }

         public void write_args(TProtocol prot) throws TException {
            prot.writeMessageBegin(new TMessage("GetResultSetMetadata", (byte)1, 0));
            GetResultSetMetadata_args args = new GetResultSetMetadata_args();
            args.setReq(this.req);
            args.write(prot);
            prot.writeMessageEnd();
         }

         public TGetResultSetMetadataResp getResult() throws TException {
            if (this.getState() != State.RESPONSE_READ) {
               throw new IllegalStateException("Method call not finished!");
            } else {
               TMemoryInputTransport memoryTransport = new TMemoryInputTransport(this.getFrameBuffer().array());
               TProtocol prot = this.client.getProtocolFactory().getProtocol(memoryTransport);
               return (new Client(prot)).recv_GetResultSetMetadata();
            }
         }
      }

      @Public
      @Stable
      public static class FetchResults_call extends TAsyncMethodCall {
         private TFetchResultsReq req;

         public FetchResults_call(TFetchResultsReq req, AsyncMethodCallback resultHandler, TAsyncClient client, TProtocolFactory protocolFactory, TNonblockingTransport transport) throws TException {
            super(client, protocolFactory, transport, resultHandler, false);
            this.req = req;
         }

         public void write_args(TProtocol prot) throws TException {
            prot.writeMessageBegin(new TMessage("FetchResults", (byte)1, 0));
            FetchResults_args args = new FetchResults_args();
            args.setReq(this.req);
            args.write(prot);
            prot.writeMessageEnd();
         }

         public TFetchResultsResp getResult() throws TException {
            if (this.getState() != State.RESPONSE_READ) {
               throw new IllegalStateException("Method call not finished!");
            } else {
               TMemoryInputTransport memoryTransport = new TMemoryInputTransport(this.getFrameBuffer().array());
               TProtocol prot = this.client.getProtocolFactory().getProtocol(memoryTransport);
               return (new Client(prot)).recv_FetchResults();
            }
         }
      }

      @Public
      @Stable
      public static class GetDelegationToken_call extends TAsyncMethodCall {
         private TGetDelegationTokenReq req;

         public GetDelegationToken_call(TGetDelegationTokenReq req, AsyncMethodCallback resultHandler, TAsyncClient client, TProtocolFactory protocolFactory, TNonblockingTransport transport) throws TException {
            super(client, protocolFactory, transport, resultHandler, false);
            this.req = req;
         }

         public void write_args(TProtocol prot) throws TException {
            prot.writeMessageBegin(new TMessage("GetDelegationToken", (byte)1, 0));
            GetDelegationToken_args args = new GetDelegationToken_args();
            args.setReq(this.req);
            args.write(prot);
            prot.writeMessageEnd();
         }

         public TGetDelegationTokenResp getResult() throws TException {
            if (this.getState() != State.RESPONSE_READ) {
               throw new IllegalStateException("Method call not finished!");
            } else {
               TMemoryInputTransport memoryTransport = new TMemoryInputTransport(this.getFrameBuffer().array());
               TProtocol prot = this.client.getProtocolFactory().getProtocol(memoryTransport);
               return (new Client(prot)).recv_GetDelegationToken();
            }
         }
      }

      @Public
      @Stable
      public static class CancelDelegationToken_call extends TAsyncMethodCall {
         private TCancelDelegationTokenReq req;

         public CancelDelegationToken_call(TCancelDelegationTokenReq req, AsyncMethodCallback resultHandler, TAsyncClient client, TProtocolFactory protocolFactory, TNonblockingTransport transport) throws TException {
            super(client, protocolFactory, transport, resultHandler, false);
            this.req = req;
         }

         public void write_args(TProtocol prot) throws TException {
            prot.writeMessageBegin(new TMessage("CancelDelegationToken", (byte)1, 0));
            CancelDelegationToken_args args = new CancelDelegationToken_args();
            args.setReq(this.req);
            args.write(prot);
            prot.writeMessageEnd();
         }

         public TCancelDelegationTokenResp getResult() throws TException {
            if (this.getState() != State.RESPONSE_READ) {
               throw new IllegalStateException("Method call not finished!");
            } else {
               TMemoryInputTransport memoryTransport = new TMemoryInputTransport(this.getFrameBuffer().array());
               TProtocol prot = this.client.getProtocolFactory().getProtocol(memoryTransport);
               return (new Client(prot)).recv_CancelDelegationToken();
            }
         }
      }

      @Public
      @Stable
      public static class RenewDelegationToken_call extends TAsyncMethodCall {
         private TRenewDelegationTokenReq req;

         public RenewDelegationToken_call(TRenewDelegationTokenReq req, AsyncMethodCallback resultHandler, TAsyncClient client, TProtocolFactory protocolFactory, TNonblockingTransport transport) throws TException {
            super(client, protocolFactory, transport, resultHandler, false);
            this.req = req;
         }

         public void write_args(TProtocol prot) throws TException {
            prot.writeMessageBegin(new TMessage("RenewDelegationToken", (byte)1, 0));
            RenewDelegationToken_args args = new RenewDelegationToken_args();
            args.setReq(this.req);
            args.write(prot);
            prot.writeMessageEnd();
         }

         public TRenewDelegationTokenResp getResult() throws TException {
            if (this.getState() != State.RESPONSE_READ) {
               throw new IllegalStateException("Method call not finished!");
            } else {
               TMemoryInputTransport memoryTransport = new TMemoryInputTransport(this.getFrameBuffer().array());
               TProtocol prot = this.client.getProtocolFactory().getProtocol(memoryTransport);
               return (new Client(prot)).recv_RenewDelegationToken();
            }
         }
      }

      @Public
      @Stable
      public static class GetQueryId_call extends TAsyncMethodCall {
         private TGetQueryIdReq req;

         public GetQueryId_call(TGetQueryIdReq req, AsyncMethodCallback resultHandler, TAsyncClient client, TProtocolFactory protocolFactory, TNonblockingTransport transport) throws TException {
            super(client, protocolFactory, transport, resultHandler, false);
            this.req = req;
         }

         public void write_args(TProtocol prot) throws TException {
            prot.writeMessageBegin(new TMessage("GetQueryId", (byte)1, 0));
            GetQueryId_args args = new GetQueryId_args();
            args.setReq(this.req);
            args.write(prot);
            prot.writeMessageEnd();
         }

         public TGetQueryIdResp getResult() throws TException {
            if (this.getState() != State.RESPONSE_READ) {
               throw new IllegalStateException("Method call not finished!");
            } else {
               TMemoryInputTransport memoryTransport = new TMemoryInputTransport(this.getFrameBuffer().array());
               TProtocol prot = this.client.getProtocolFactory().getProtocol(memoryTransport);
               return (new Client(prot)).recv_GetQueryId();
            }
         }
      }

      @Public
      @Stable
      public static class SetClientInfo_call extends TAsyncMethodCall {
         private TSetClientInfoReq req;

         public SetClientInfo_call(TSetClientInfoReq req, AsyncMethodCallback resultHandler, TAsyncClient client, TProtocolFactory protocolFactory, TNonblockingTransport transport) throws TException {
            super(client, protocolFactory, transport, resultHandler, false);
            this.req = req;
         }

         public void write_args(TProtocol prot) throws TException {
            prot.writeMessageBegin(new TMessage("SetClientInfo", (byte)1, 0));
            SetClientInfo_args args = new SetClientInfo_args();
            args.setReq(this.req);
            args.write(prot);
            prot.writeMessageEnd();
         }

         public TSetClientInfoResp getResult() throws TException {
            if (this.getState() != State.RESPONSE_READ) {
               throw new IllegalStateException("Method call not finished!");
            } else {
               TMemoryInputTransport memoryTransport = new TMemoryInputTransport(this.getFrameBuffer().array());
               TProtocol prot = this.client.getProtocolFactory().getProtocol(memoryTransport);
               return (new Client(prot)).recv_SetClientInfo();
            }
         }
      }

      @Public
      @Stable
      public static class UploadData_call extends TAsyncMethodCall {
         private TUploadDataReq req;

         public UploadData_call(TUploadDataReq req, AsyncMethodCallback resultHandler, TAsyncClient client, TProtocolFactory protocolFactory, TNonblockingTransport transport) throws TException {
            super(client, protocolFactory, transport, resultHandler, false);
            this.req = req;
         }

         public void write_args(TProtocol prot) throws TException {
            prot.writeMessageBegin(new TMessage("UploadData", (byte)1, 0));
            UploadData_args args = new UploadData_args();
            args.setReq(this.req);
            args.write(prot);
            prot.writeMessageEnd();
         }

         public TUploadDataResp getResult() throws TException {
            if (this.getState() != State.RESPONSE_READ) {
               throw new IllegalStateException("Method call not finished!");
            } else {
               TMemoryInputTransport memoryTransport = new TMemoryInputTransport(this.getFrameBuffer().array());
               TProtocol prot = this.client.getProtocolFactory().getProtocol(memoryTransport);
               return (new Client(prot)).recv_UploadData();
            }
         }
      }

      @Public
      @Stable
      public static class DownloadData_call extends TAsyncMethodCall {
         private TDownloadDataReq req;

         public DownloadData_call(TDownloadDataReq req, AsyncMethodCallback resultHandler, TAsyncClient client, TProtocolFactory protocolFactory, TNonblockingTransport transport) throws TException {
            super(client, protocolFactory, transport, resultHandler, false);
            this.req = req;
         }

         public void write_args(TProtocol prot) throws TException {
            prot.writeMessageBegin(new TMessage("DownloadData", (byte)1, 0));
            DownloadData_args args = new DownloadData_args();
            args.setReq(this.req);
            args.write(prot);
            prot.writeMessageEnd();
         }

         public TDownloadDataResp getResult() throws TException {
            if (this.getState() != State.RESPONSE_READ) {
               throw new IllegalStateException("Method call not finished!");
            } else {
               TMemoryInputTransport memoryTransport = new TMemoryInputTransport(this.getFrameBuffer().array());
               TProtocol prot = this.client.getProtocolFactory().getProtocol(memoryTransport);
               return (new Client(prot)).recv_DownloadData();
            }
         }
      }
   }

   @Public
   @Stable
   public static class Processor extends TBaseProcessor implements TProcessor {
      private static final Logger _LOGGER = LoggerFactory.getLogger(Processor.class.getName());

      public Processor(Iface iface) {
         super(iface, getProcessMap(new HashMap()));
      }

      protected Processor(Iface iface, Map processMap) {
         super(iface, getProcessMap(processMap));
      }

      private static Map getProcessMap(Map processMap) {
         processMap.put("OpenSession", new OpenSession());
         processMap.put("CloseSession", new CloseSession());
         processMap.put("GetInfo", new GetInfo());
         processMap.put("ExecuteStatement", new ExecuteStatement());
         processMap.put("GetTypeInfo", new GetTypeInfo());
         processMap.put("GetCatalogs", new GetCatalogs());
         processMap.put("GetSchemas", new GetSchemas());
         processMap.put("GetTables", new GetTables());
         processMap.put("GetTableTypes", new GetTableTypes());
         processMap.put("GetColumns", new GetColumns());
         processMap.put("GetFunctions", new GetFunctions());
         processMap.put("GetPrimaryKeys", new GetPrimaryKeys());
         processMap.put("GetCrossReference", new GetCrossReference());
         processMap.put("GetOperationStatus", new GetOperationStatus());
         processMap.put("CancelOperation", new CancelOperation());
         processMap.put("CloseOperation", new CloseOperation());
         processMap.put("GetResultSetMetadata", new GetResultSetMetadata());
         processMap.put("FetchResults", new FetchResults());
         processMap.put("GetDelegationToken", new GetDelegationToken());
         processMap.put("CancelDelegationToken", new CancelDelegationToken());
         processMap.put("RenewDelegationToken", new RenewDelegationToken());
         processMap.put("GetQueryId", new GetQueryId());
         processMap.put("SetClientInfo", new SetClientInfo());
         processMap.put("UploadData", new UploadData());
         processMap.put("DownloadData", new DownloadData());
         return processMap;
      }

      @Public
      @Stable
      public static class OpenSession extends ProcessFunction {
         public OpenSession() {
            super("OpenSession");
         }

         public OpenSession_args getEmptyArgsInstance() {
            return new OpenSession_args();
         }

         protected boolean isOneway() {
            return false;
         }

         protected boolean rethrowUnhandledExceptions() {
            return false;
         }

         public OpenSession_result getResult(Iface iface, OpenSession_args args) throws TException {
            OpenSession_result result = new OpenSession_result();
            result.success = iface.OpenSession(args.req);
            return result;
         }
      }

      @Public
      @Stable
      public static class CloseSession extends ProcessFunction {
         public CloseSession() {
            super("CloseSession");
         }

         public CloseSession_args getEmptyArgsInstance() {
            return new CloseSession_args();
         }

         protected boolean isOneway() {
            return false;
         }

         protected boolean rethrowUnhandledExceptions() {
            return false;
         }

         public CloseSession_result getResult(Iface iface, CloseSession_args args) throws TException {
            CloseSession_result result = new CloseSession_result();
            result.success = iface.CloseSession(args.req);
            return result;
         }
      }

      @Public
      @Stable
      public static class GetInfo extends ProcessFunction {
         public GetInfo() {
            super("GetInfo");
         }

         public GetInfo_args getEmptyArgsInstance() {
            return new GetInfo_args();
         }

         protected boolean isOneway() {
            return false;
         }

         protected boolean rethrowUnhandledExceptions() {
            return false;
         }

         public GetInfo_result getResult(Iface iface, GetInfo_args args) throws TException {
            GetInfo_result result = new GetInfo_result();
            result.success = iface.GetInfo(args.req);
            return result;
         }
      }

      @Public
      @Stable
      public static class ExecuteStatement extends ProcessFunction {
         public ExecuteStatement() {
            super("ExecuteStatement");
         }

         public ExecuteStatement_args getEmptyArgsInstance() {
            return new ExecuteStatement_args();
         }

         protected boolean isOneway() {
            return false;
         }

         protected boolean rethrowUnhandledExceptions() {
            return false;
         }

         public ExecuteStatement_result getResult(Iface iface, ExecuteStatement_args args) throws TException {
            ExecuteStatement_result result = new ExecuteStatement_result();
            result.success = iface.ExecuteStatement(args.req);
            return result;
         }
      }

      @Public
      @Stable
      public static class GetTypeInfo extends ProcessFunction {
         public GetTypeInfo() {
            super("GetTypeInfo");
         }

         public GetTypeInfo_args getEmptyArgsInstance() {
            return new GetTypeInfo_args();
         }

         protected boolean isOneway() {
            return false;
         }

         protected boolean rethrowUnhandledExceptions() {
            return false;
         }

         public GetTypeInfo_result getResult(Iface iface, GetTypeInfo_args args) throws TException {
            GetTypeInfo_result result = new GetTypeInfo_result();
            result.success = iface.GetTypeInfo(args.req);
            return result;
         }
      }

      @Public
      @Stable
      public static class GetCatalogs extends ProcessFunction {
         public GetCatalogs() {
            super("GetCatalogs");
         }

         public GetCatalogs_args getEmptyArgsInstance() {
            return new GetCatalogs_args();
         }

         protected boolean isOneway() {
            return false;
         }

         protected boolean rethrowUnhandledExceptions() {
            return false;
         }

         public GetCatalogs_result getResult(Iface iface, GetCatalogs_args args) throws TException {
            GetCatalogs_result result = new GetCatalogs_result();
            result.success = iface.GetCatalogs(args.req);
            return result;
         }
      }

      @Public
      @Stable
      public static class GetSchemas extends ProcessFunction {
         public GetSchemas() {
            super("GetSchemas");
         }

         public GetSchemas_args getEmptyArgsInstance() {
            return new GetSchemas_args();
         }

         protected boolean isOneway() {
            return false;
         }

         protected boolean rethrowUnhandledExceptions() {
            return false;
         }

         public GetSchemas_result getResult(Iface iface, GetSchemas_args args) throws TException {
            GetSchemas_result result = new GetSchemas_result();
            result.success = iface.GetSchemas(args.req);
            return result;
         }
      }

      @Public
      @Stable
      public static class GetTables extends ProcessFunction {
         public GetTables() {
            super("GetTables");
         }

         public GetTables_args getEmptyArgsInstance() {
            return new GetTables_args();
         }

         protected boolean isOneway() {
            return false;
         }

         protected boolean rethrowUnhandledExceptions() {
            return false;
         }

         public GetTables_result getResult(Iface iface, GetTables_args args) throws TException {
            GetTables_result result = new GetTables_result();
            result.success = iface.GetTables(args.req);
            return result;
         }
      }

      @Public
      @Stable
      public static class GetTableTypes extends ProcessFunction {
         public GetTableTypes() {
            super("GetTableTypes");
         }

         public GetTableTypes_args getEmptyArgsInstance() {
            return new GetTableTypes_args();
         }

         protected boolean isOneway() {
            return false;
         }

         protected boolean rethrowUnhandledExceptions() {
            return false;
         }

         public GetTableTypes_result getResult(Iface iface, GetTableTypes_args args) throws TException {
            GetTableTypes_result result = new GetTableTypes_result();
            result.success = iface.GetTableTypes(args.req);
            return result;
         }
      }

      @Public
      @Stable
      public static class GetColumns extends ProcessFunction {
         public GetColumns() {
            super("GetColumns");
         }

         public GetColumns_args getEmptyArgsInstance() {
            return new GetColumns_args();
         }

         protected boolean isOneway() {
            return false;
         }

         protected boolean rethrowUnhandledExceptions() {
            return false;
         }

         public GetColumns_result getResult(Iface iface, GetColumns_args args) throws TException {
            GetColumns_result result = new GetColumns_result();
            result.success = iface.GetColumns(args.req);
            return result;
         }
      }

      @Public
      @Stable
      public static class GetFunctions extends ProcessFunction {
         public GetFunctions() {
            super("GetFunctions");
         }

         public GetFunctions_args getEmptyArgsInstance() {
            return new GetFunctions_args();
         }

         protected boolean isOneway() {
            return false;
         }

         protected boolean rethrowUnhandledExceptions() {
            return false;
         }

         public GetFunctions_result getResult(Iface iface, GetFunctions_args args) throws TException {
            GetFunctions_result result = new GetFunctions_result();
            result.success = iface.GetFunctions(args.req);
            return result;
         }
      }

      @Public
      @Stable
      public static class GetPrimaryKeys extends ProcessFunction {
         public GetPrimaryKeys() {
            super("GetPrimaryKeys");
         }

         public GetPrimaryKeys_args getEmptyArgsInstance() {
            return new GetPrimaryKeys_args();
         }

         protected boolean isOneway() {
            return false;
         }

         protected boolean rethrowUnhandledExceptions() {
            return false;
         }

         public GetPrimaryKeys_result getResult(Iface iface, GetPrimaryKeys_args args) throws TException {
            GetPrimaryKeys_result result = new GetPrimaryKeys_result();
            result.success = iface.GetPrimaryKeys(args.req);
            return result;
         }
      }

      @Public
      @Stable
      public static class GetCrossReference extends ProcessFunction {
         public GetCrossReference() {
            super("GetCrossReference");
         }

         public GetCrossReference_args getEmptyArgsInstance() {
            return new GetCrossReference_args();
         }

         protected boolean isOneway() {
            return false;
         }

         protected boolean rethrowUnhandledExceptions() {
            return false;
         }

         public GetCrossReference_result getResult(Iface iface, GetCrossReference_args args) throws TException {
            GetCrossReference_result result = new GetCrossReference_result();
            result.success = iface.GetCrossReference(args.req);
            return result;
         }
      }

      @Public
      @Stable
      public static class GetOperationStatus extends ProcessFunction {
         public GetOperationStatus() {
            super("GetOperationStatus");
         }

         public GetOperationStatus_args getEmptyArgsInstance() {
            return new GetOperationStatus_args();
         }

         protected boolean isOneway() {
            return false;
         }

         protected boolean rethrowUnhandledExceptions() {
            return false;
         }

         public GetOperationStatus_result getResult(Iface iface, GetOperationStatus_args args) throws TException {
            GetOperationStatus_result result = new GetOperationStatus_result();
            result.success = iface.GetOperationStatus(args.req);
            return result;
         }
      }

      @Public
      @Stable
      public static class CancelOperation extends ProcessFunction {
         public CancelOperation() {
            super("CancelOperation");
         }

         public CancelOperation_args getEmptyArgsInstance() {
            return new CancelOperation_args();
         }

         protected boolean isOneway() {
            return false;
         }

         protected boolean rethrowUnhandledExceptions() {
            return false;
         }

         public CancelOperation_result getResult(Iface iface, CancelOperation_args args) throws TException {
            CancelOperation_result result = new CancelOperation_result();
            result.success = iface.CancelOperation(args.req);
            return result;
         }
      }

      @Public
      @Stable
      public static class CloseOperation extends ProcessFunction {
         public CloseOperation() {
            super("CloseOperation");
         }

         public CloseOperation_args getEmptyArgsInstance() {
            return new CloseOperation_args();
         }

         protected boolean isOneway() {
            return false;
         }

         protected boolean rethrowUnhandledExceptions() {
            return false;
         }

         public CloseOperation_result getResult(Iface iface, CloseOperation_args args) throws TException {
            CloseOperation_result result = new CloseOperation_result();
            result.success = iface.CloseOperation(args.req);
            return result;
         }
      }

      @Public
      @Stable
      public static class GetResultSetMetadata extends ProcessFunction {
         public GetResultSetMetadata() {
            super("GetResultSetMetadata");
         }

         public GetResultSetMetadata_args getEmptyArgsInstance() {
            return new GetResultSetMetadata_args();
         }

         protected boolean isOneway() {
            return false;
         }

         protected boolean rethrowUnhandledExceptions() {
            return false;
         }

         public GetResultSetMetadata_result getResult(Iface iface, GetResultSetMetadata_args args) throws TException {
            GetResultSetMetadata_result result = new GetResultSetMetadata_result();
            result.success = iface.GetResultSetMetadata(args.req);
            return result;
         }
      }

      @Public
      @Stable
      public static class FetchResults extends ProcessFunction {
         public FetchResults() {
            super("FetchResults");
         }

         public FetchResults_args getEmptyArgsInstance() {
            return new FetchResults_args();
         }

         protected boolean isOneway() {
            return false;
         }

         protected boolean rethrowUnhandledExceptions() {
            return false;
         }

         public FetchResults_result getResult(Iface iface, FetchResults_args args) throws TException {
            FetchResults_result result = new FetchResults_result();
            result.success = iface.FetchResults(args.req);
            return result;
         }
      }

      @Public
      @Stable
      public static class GetDelegationToken extends ProcessFunction {
         public GetDelegationToken() {
            super("GetDelegationToken");
         }

         public GetDelegationToken_args getEmptyArgsInstance() {
            return new GetDelegationToken_args();
         }

         protected boolean isOneway() {
            return false;
         }

         protected boolean rethrowUnhandledExceptions() {
            return false;
         }

         public GetDelegationToken_result getResult(Iface iface, GetDelegationToken_args args) throws TException {
            GetDelegationToken_result result = new GetDelegationToken_result();
            result.success = iface.GetDelegationToken(args.req);
            return result;
         }
      }

      @Public
      @Stable
      public static class CancelDelegationToken extends ProcessFunction {
         public CancelDelegationToken() {
            super("CancelDelegationToken");
         }

         public CancelDelegationToken_args getEmptyArgsInstance() {
            return new CancelDelegationToken_args();
         }

         protected boolean isOneway() {
            return false;
         }

         protected boolean rethrowUnhandledExceptions() {
            return false;
         }

         public CancelDelegationToken_result getResult(Iface iface, CancelDelegationToken_args args) throws TException {
            CancelDelegationToken_result result = new CancelDelegationToken_result();
            result.success = iface.CancelDelegationToken(args.req);
            return result;
         }
      }

      @Public
      @Stable
      public static class RenewDelegationToken extends ProcessFunction {
         public RenewDelegationToken() {
            super("RenewDelegationToken");
         }

         public RenewDelegationToken_args getEmptyArgsInstance() {
            return new RenewDelegationToken_args();
         }

         protected boolean isOneway() {
            return false;
         }

         protected boolean rethrowUnhandledExceptions() {
            return false;
         }

         public RenewDelegationToken_result getResult(Iface iface, RenewDelegationToken_args args) throws TException {
            RenewDelegationToken_result result = new RenewDelegationToken_result();
            result.success = iface.RenewDelegationToken(args.req);
            return result;
         }
      }

      @Public
      @Stable
      public static class GetQueryId extends ProcessFunction {
         public GetQueryId() {
            super("GetQueryId");
         }

         public GetQueryId_args getEmptyArgsInstance() {
            return new GetQueryId_args();
         }

         protected boolean isOneway() {
            return false;
         }

         protected boolean rethrowUnhandledExceptions() {
            return false;
         }

         public GetQueryId_result getResult(Iface iface, GetQueryId_args args) throws TException {
            GetQueryId_result result = new GetQueryId_result();
            result.success = iface.GetQueryId(args.req);
            return result;
         }
      }

      @Public
      @Stable
      public static class SetClientInfo extends ProcessFunction {
         public SetClientInfo() {
            super("SetClientInfo");
         }

         public SetClientInfo_args getEmptyArgsInstance() {
            return new SetClientInfo_args();
         }

         protected boolean isOneway() {
            return false;
         }

         protected boolean rethrowUnhandledExceptions() {
            return false;
         }

         public SetClientInfo_result getResult(Iface iface, SetClientInfo_args args) throws TException {
            SetClientInfo_result result = new SetClientInfo_result();
            result.success = iface.SetClientInfo(args.req);
            return result;
         }
      }

      @Public
      @Stable
      public static class UploadData extends ProcessFunction {
         public UploadData() {
            super("UploadData");
         }

         public UploadData_args getEmptyArgsInstance() {
            return new UploadData_args();
         }

         protected boolean isOneway() {
            return false;
         }

         protected boolean rethrowUnhandledExceptions() {
            return false;
         }

         public UploadData_result getResult(Iface iface, UploadData_args args) throws TException {
            UploadData_result result = new UploadData_result();
            result.success = iface.UploadData(args.req);
            return result;
         }
      }

      @Public
      @Stable
      public static class DownloadData extends ProcessFunction {
         public DownloadData() {
            super("DownloadData");
         }

         public DownloadData_args getEmptyArgsInstance() {
            return new DownloadData_args();
         }

         protected boolean isOneway() {
            return false;
         }

         protected boolean rethrowUnhandledExceptions() {
            return false;
         }

         public DownloadData_result getResult(Iface iface, DownloadData_args args) throws TException {
            DownloadData_result result = new DownloadData_result();
            result.success = iface.DownloadData(args.req);
            return result;
         }
      }
   }

   @Public
   @Stable
   public static class AsyncProcessor extends TBaseAsyncProcessor {
      private static final Logger _LOGGER = LoggerFactory.getLogger(AsyncProcessor.class.getName());

      public AsyncProcessor(AsyncIface iface) {
         super(iface, getProcessMap(new HashMap()));
      }

      protected AsyncProcessor(AsyncIface iface, Map processMap) {
         super(iface, getProcessMap(processMap));
      }

      private static Map getProcessMap(Map processMap) {
         processMap.put("OpenSession", new OpenSession());
         processMap.put("CloseSession", new CloseSession());
         processMap.put("GetInfo", new GetInfo());
         processMap.put("ExecuteStatement", new ExecuteStatement());
         processMap.put("GetTypeInfo", new GetTypeInfo());
         processMap.put("GetCatalogs", new GetCatalogs());
         processMap.put("GetSchemas", new GetSchemas());
         processMap.put("GetTables", new GetTables());
         processMap.put("GetTableTypes", new GetTableTypes());
         processMap.put("GetColumns", new GetColumns());
         processMap.put("GetFunctions", new GetFunctions());
         processMap.put("GetPrimaryKeys", new GetPrimaryKeys());
         processMap.put("GetCrossReference", new GetCrossReference());
         processMap.put("GetOperationStatus", new GetOperationStatus());
         processMap.put("CancelOperation", new CancelOperation());
         processMap.put("CloseOperation", new CloseOperation());
         processMap.put("GetResultSetMetadata", new GetResultSetMetadata());
         processMap.put("FetchResults", new FetchResults());
         processMap.put("GetDelegationToken", new GetDelegationToken());
         processMap.put("CancelDelegationToken", new CancelDelegationToken());
         processMap.put("RenewDelegationToken", new RenewDelegationToken());
         processMap.put("GetQueryId", new GetQueryId());
         processMap.put("SetClientInfo", new SetClientInfo());
         processMap.put("UploadData", new UploadData());
         processMap.put("DownloadData", new DownloadData());
         return processMap;
      }

      @Public
      @Stable
      public static class OpenSession extends AsyncProcessFunction {
         public OpenSession() {
            super("OpenSession");
         }

         public OpenSession_args getEmptyArgsInstance() {
            return new OpenSession_args();
         }

         public AsyncMethodCallback getResultHandler(final AbstractNonblockingServer.AsyncFrameBuffer fb, final int seqid) {
            return new AsyncMethodCallback() {
               public void onComplete(TOpenSessionResp o) {
                  OpenSession_result result = new OpenSession_result();
                  result.success = o;

                  try {
                     ((AsyncProcessFunction)OpenSession.this).sendResponse(fb, result, (byte)2, seqid);
                  } catch (TTransportException e) {
                     TCLIService.AsyncProcessor._LOGGER.error("TTransportException writing to internal frame buffer", e);
                     fb.close();
                  } catch (Exception e) {
                     TCLIService.AsyncProcessor._LOGGER.error("Exception writing to internal frame buffer", e);
                     this.onError(e);
                  }

               }

               public void onError(Exception e) {
                  byte msgType = 2;
                  new OpenSession_result();
                  if (e instanceof TTransportException) {
                     TCLIService.AsyncProcessor._LOGGER.error("TTransportException inside handler", e);
                     fb.close();
                  } else {
                     TSerializable msg;
                     if (e instanceof TApplicationException) {
                        TCLIService.AsyncProcessor._LOGGER.error("TApplicationException inside handler", e);
                        msgType = 3;
                        msg = (TApplicationException)e;
                     } else {
                        TCLIService.AsyncProcessor._LOGGER.error("Exception inside handler", e);
                        msgType = 3;
                        msg = new TApplicationException(6, e.getMessage());
                     }

                     try {
                        ((AsyncProcessFunction)OpenSession.this).sendResponse(fb, msg, msgType, seqid);
                     } catch (Exception ex) {
                        TCLIService.AsyncProcessor._LOGGER.error("Exception writing to internal frame buffer", ex);
                        fb.close();
                     }

                  }
               }
            };
         }

         protected boolean isOneway() {
            return false;
         }

         public void start(AsyncIface iface, OpenSession_args args, AsyncMethodCallback resultHandler) throws TException {
            iface.OpenSession(args.req, resultHandler);
         }
      }

      @Public
      @Stable
      public static class CloseSession extends AsyncProcessFunction {
         public CloseSession() {
            super("CloseSession");
         }

         public CloseSession_args getEmptyArgsInstance() {
            return new CloseSession_args();
         }

         public AsyncMethodCallback getResultHandler(final AbstractNonblockingServer.AsyncFrameBuffer fb, final int seqid) {
            return new AsyncMethodCallback() {
               public void onComplete(TCloseSessionResp o) {
                  CloseSession_result result = new CloseSession_result();
                  result.success = o;

                  try {
                     ((AsyncProcessFunction)CloseSession.this).sendResponse(fb, result, (byte)2, seqid);
                  } catch (TTransportException e) {
                     TCLIService.AsyncProcessor._LOGGER.error("TTransportException writing to internal frame buffer", e);
                     fb.close();
                  } catch (Exception e) {
                     TCLIService.AsyncProcessor._LOGGER.error("Exception writing to internal frame buffer", e);
                     this.onError(e);
                  }

               }

               public void onError(Exception e) {
                  byte msgType = 2;
                  new CloseSession_result();
                  if (e instanceof TTransportException) {
                     TCLIService.AsyncProcessor._LOGGER.error("TTransportException inside handler", e);
                     fb.close();
                  } else {
                     TSerializable msg;
                     if (e instanceof TApplicationException) {
                        TCLIService.AsyncProcessor._LOGGER.error("TApplicationException inside handler", e);
                        msgType = 3;
                        msg = (TApplicationException)e;
                     } else {
                        TCLIService.AsyncProcessor._LOGGER.error("Exception inside handler", e);
                        msgType = 3;
                        msg = new TApplicationException(6, e.getMessage());
                     }

                     try {
                        ((AsyncProcessFunction)CloseSession.this).sendResponse(fb, msg, msgType, seqid);
                     } catch (Exception ex) {
                        TCLIService.AsyncProcessor._LOGGER.error("Exception writing to internal frame buffer", ex);
                        fb.close();
                     }

                  }
               }
            };
         }

         protected boolean isOneway() {
            return false;
         }

         public void start(AsyncIface iface, CloseSession_args args, AsyncMethodCallback resultHandler) throws TException {
            iface.CloseSession(args.req, resultHandler);
         }
      }

      @Public
      @Stable
      public static class GetInfo extends AsyncProcessFunction {
         public GetInfo() {
            super("GetInfo");
         }

         public GetInfo_args getEmptyArgsInstance() {
            return new GetInfo_args();
         }

         public AsyncMethodCallback getResultHandler(final AbstractNonblockingServer.AsyncFrameBuffer fb, final int seqid) {
            return new AsyncMethodCallback() {
               public void onComplete(TGetInfoResp o) {
                  GetInfo_result result = new GetInfo_result();
                  result.success = o;

                  try {
                     ((AsyncProcessFunction)GetInfo.this).sendResponse(fb, result, (byte)2, seqid);
                  } catch (TTransportException e) {
                     TCLIService.AsyncProcessor._LOGGER.error("TTransportException writing to internal frame buffer", e);
                     fb.close();
                  } catch (Exception e) {
                     TCLIService.AsyncProcessor._LOGGER.error("Exception writing to internal frame buffer", e);
                     this.onError(e);
                  }

               }

               public void onError(Exception e) {
                  byte msgType = 2;
                  new GetInfo_result();
                  if (e instanceof TTransportException) {
                     TCLIService.AsyncProcessor._LOGGER.error("TTransportException inside handler", e);
                     fb.close();
                  } else {
                     TSerializable msg;
                     if (e instanceof TApplicationException) {
                        TCLIService.AsyncProcessor._LOGGER.error("TApplicationException inside handler", e);
                        msgType = 3;
                        msg = (TApplicationException)e;
                     } else {
                        TCLIService.AsyncProcessor._LOGGER.error("Exception inside handler", e);
                        msgType = 3;
                        msg = new TApplicationException(6, e.getMessage());
                     }

                     try {
                        ((AsyncProcessFunction)GetInfo.this).sendResponse(fb, msg, msgType, seqid);
                     } catch (Exception ex) {
                        TCLIService.AsyncProcessor._LOGGER.error("Exception writing to internal frame buffer", ex);
                        fb.close();
                     }

                  }
               }
            };
         }

         protected boolean isOneway() {
            return false;
         }

         public void start(AsyncIface iface, GetInfo_args args, AsyncMethodCallback resultHandler) throws TException {
            iface.GetInfo(args.req, resultHandler);
         }
      }

      @Public
      @Stable
      public static class ExecuteStatement extends AsyncProcessFunction {
         public ExecuteStatement() {
            super("ExecuteStatement");
         }

         public ExecuteStatement_args getEmptyArgsInstance() {
            return new ExecuteStatement_args();
         }

         public AsyncMethodCallback getResultHandler(final AbstractNonblockingServer.AsyncFrameBuffer fb, final int seqid) {
            return new AsyncMethodCallback() {
               public void onComplete(TExecuteStatementResp o) {
                  ExecuteStatement_result result = new ExecuteStatement_result();
                  result.success = o;

                  try {
                     ((AsyncProcessFunction)ExecuteStatement.this).sendResponse(fb, result, (byte)2, seqid);
                  } catch (TTransportException e) {
                     TCLIService.AsyncProcessor._LOGGER.error("TTransportException writing to internal frame buffer", e);
                     fb.close();
                  } catch (Exception e) {
                     TCLIService.AsyncProcessor._LOGGER.error("Exception writing to internal frame buffer", e);
                     this.onError(e);
                  }

               }

               public void onError(Exception e) {
                  byte msgType = 2;
                  new ExecuteStatement_result();
                  if (e instanceof TTransportException) {
                     TCLIService.AsyncProcessor._LOGGER.error("TTransportException inside handler", e);
                     fb.close();
                  } else {
                     TSerializable msg;
                     if (e instanceof TApplicationException) {
                        TCLIService.AsyncProcessor._LOGGER.error("TApplicationException inside handler", e);
                        msgType = 3;
                        msg = (TApplicationException)e;
                     } else {
                        TCLIService.AsyncProcessor._LOGGER.error("Exception inside handler", e);
                        msgType = 3;
                        msg = new TApplicationException(6, e.getMessage());
                     }

                     try {
                        ((AsyncProcessFunction)ExecuteStatement.this).sendResponse(fb, msg, msgType, seqid);
                     } catch (Exception ex) {
                        TCLIService.AsyncProcessor._LOGGER.error("Exception writing to internal frame buffer", ex);
                        fb.close();
                     }

                  }
               }
            };
         }

         protected boolean isOneway() {
            return false;
         }

         public void start(AsyncIface iface, ExecuteStatement_args args, AsyncMethodCallback resultHandler) throws TException {
            iface.ExecuteStatement(args.req, resultHandler);
         }
      }

      @Public
      @Stable
      public static class GetTypeInfo extends AsyncProcessFunction {
         public GetTypeInfo() {
            super("GetTypeInfo");
         }

         public GetTypeInfo_args getEmptyArgsInstance() {
            return new GetTypeInfo_args();
         }

         public AsyncMethodCallback getResultHandler(final AbstractNonblockingServer.AsyncFrameBuffer fb, final int seqid) {
            return new AsyncMethodCallback() {
               public void onComplete(TGetTypeInfoResp o) {
                  GetTypeInfo_result result = new GetTypeInfo_result();
                  result.success = o;

                  try {
                     ((AsyncProcessFunction)GetTypeInfo.this).sendResponse(fb, result, (byte)2, seqid);
                  } catch (TTransportException e) {
                     TCLIService.AsyncProcessor._LOGGER.error("TTransportException writing to internal frame buffer", e);
                     fb.close();
                  } catch (Exception e) {
                     TCLIService.AsyncProcessor._LOGGER.error("Exception writing to internal frame buffer", e);
                     this.onError(e);
                  }

               }

               public void onError(Exception e) {
                  byte msgType = 2;
                  new GetTypeInfo_result();
                  if (e instanceof TTransportException) {
                     TCLIService.AsyncProcessor._LOGGER.error("TTransportException inside handler", e);
                     fb.close();
                  } else {
                     TSerializable msg;
                     if (e instanceof TApplicationException) {
                        TCLIService.AsyncProcessor._LOGGER.error("TApplicationException inside handler", e);
                        msgType = 3;
                        msg = (TApplicationException)e;
                     } else {
                        TCLIService.AsyncProcessor._LOGGER.error("Exception inside handler", e);
                        msgType = 3;
                        msg = new TApplicationException(6, e.getMessage());
                     }

                     try {
                        ((AsyncProcessFunction)GetTypeInfo.this).sendResponse(fb, msg, msgType, seqid);
                     } catch (Exception ex) {
                        TCLIService.AsyncProcessor._LOGGER.error("Exception writing to internal frame buffer", ex);
                        fb.close();
                     }

                  }
               }
            };
         }

         protected boolean isOneway() {
            return false;
         }

         public void start(AsyncIface iface, GetTypeInfo_args args, AsyncMethodCallback resultHandler) throws TException {
            iface.GetTypeInfo(args.req, resultHandler);
         }
      }

      @Public
      @Stable
      public static class GetCatalogs extends AsyncProcessFunction {
         public GetCatalogs() {
            super("GetCatalogs");
         }

         public GetCatalogs_args getEmptyArgsInstance() {
            return new GetCatalogs_args();
         }

         public AsyncMethodCallback getResultHandler(final AbstractNonblockingServer.AsyncFrameBuffer fb, final int seqid) {
            return new AsyncMethodCallback() {
               public void onComplete(TGetCatalogsResp o) {
                  GetCatalogs_result result = new GetCatalogs_result();
                  result.success = o;

                  try {
                     ((AsyncProcessFunction)GetCatalogs.this).sendResponse(fb, result, (byte)2, seqid);
                  } catch (TTransportException e) {
                     TCLIService.AsyncProcessor._LOGGER.error("TTransportException writing to internal frame buffer", e);
                     fb.close();
                  } catch (Exception e) {
                     TCLIService.AsyncProcessor._LOGGER.error("Exception writing to internal frame buffer", e);
                     this.onError(e);
                  }

               }

               public void onError(Exception e) {
                  byte msgType = 2;
                  new GetCatalogs_result();
                  if (e instanceof TTransportException) {
                     TCLIService.AsyncProcessor._LOGGER.error("TTransportException inside handler", e);
                     fb.close();
                  } else {
                     TSerializable msg;
                     if (e instanceof TApplicationException) {
                        TCLIService.AsyncProcessor._LOGGER.error("TApplicationException inside handler", e);
                        msgType = 3;
                        msg = (TApplicationException)e;
                     } else {
                        TCLIService.AsyncProcessor._LOGGER.error("Exception inside handler", e);
                        msgType = 3;
                        msg = new TApplicationException(6, e.getMessage());
                     }

                     try {
                        ((AsyncProcessFunction)GetCatalogs.this).sendResponse(fb, msg, msgType, seqid);
                     } catch (Exception ex) {
                        TCLIService.AsyncProcessor._LOGGER.error("Exception writing to internal frame buffer", ex);
                        fb.close();
                     }

                  }
               }
            };
         }

         protected boolean isOneway() {
            return false;
         }

         public void start(AsyncIface iface, GetCatalogs_args args, AsyncMethodCallback resultHandler) throws TException {
            iface.GetCatalogs(args.req, resultHandler);
         }
      }

      @Public
      @Stable
      public static class GetSchemas extends AsyncProcessFunction {
         public GetSchemas() {
            super("GetSchemas");
         }

         public GetSchemas_args getEmptyArgsInstance() {
            return new GetSchemas_args();
         }

         public AsyncMethodCallback getResultHandler(final AbstractNonblockingServer.AsyncFrameBuffer fb, final int seqid) {
            return new AsyncMethodCallback() {
               public void onComplete(TGetSchemasResp o) {
                  GetSchemas_result result = new GetSchemas_result();
                  result.success = o;

                  try {
                     ((AsyncProcessFunction)GetSchemas.this).sendResponse(fb, result, (byte)2, seqid);
                  } catch (TTransportException e) {
                     TCLIService.AsyncProcessor._LOGGER.error("TTransportException writing to internal frame buffer", e);
                     fb.close();
                  } catch (Exception e) {
                     TCLIService.AsyncProcessor._LOGGER.error("Exception writing to internal frame buffer", e);
                     this.onError(e);
                  }

               }

               public void onError(Exception e) {
                  byte msgType = 2;
                  new GetSchemas_result();
                  if (e instanceof TTransportException) {
                     TCLIService.AsyncProcessor._LOGGER.error("TTransportException inside handler", e);
                     fb.close();
                  } else {
                     TSerializable msg;
                     if (e instanceof TApplicationException) {
                        TCLIService.AsyncProcessor._LOGGER.error("TApplicationException inside handler", e);
                        msgType = 3;
                        msg = (TApplicationException)e;
                     } else {
                        TCLIService.AsyncProcessor._LOGGER.error("Exception inside handler", e);
                        msgType = 3;
                        msg = new TApplicationException(6, e.getMessage());
                     }

                     try {
                        ((AsyncProcessFunction)GetSchemas.this).sendResponse(fb, msg, msgType, seqid);
                     } catch (Exception ex) {
                        TCLIService.AsyncProcessor._LOGGER.error("Exception writing to internal frame buffer", ex);
                        fb.close();
                     }

                  }
               }
            };
         }

         protected boolean isOneway() {
            return false;
         }

         public void start(AsyncIface iface, GetSchemas_args args, AsyncMethodCallback resultHandler) throws TException {
            iface.GetSchemas(args.req, resultHandler);
         }
      }

      @Public
      @Stable
      public static class GetTables extends AsyncProcessFunction {
         public GetTables() {
            super("GetTables");
         }

         public GetTables_args getEmptyArgsInstance() {
            return new GetTables_args();
         }

         public AsyncMethodCallback getResultHandler(final AbstractNonblockingServer.AsyncFrameBuffer fb, final int seqid) {
            return new AsyncMethodCallback() {
               public void onComplete(TGetTablesResp o) {
                  GetTables_result result = new GetTables_result();
                  result.success = o;

                  try {
                     ((AsyncProcessFunction)GetTables.this).sendResponse(fb, result, (byte)2, seqid);
                  } catch (TTransportException e) {
                     TCLIService.AsyncProcessor._LOGGER.error("TTransportException writing to internal frame buffer", e);
                     fb.close();
                  } catch (Exception e) {
                     TCLIService.AsyncProcessor._LOGGER.error("Exception writing to internal frame buffer", e);
                     this.onError(e);
                  }

               }

               public void onError(Exception e) {
                  byte msgType = 2;
                  new GetTables_result();
                  if (e instanceof TTransportException) {
                     TCLIService.AsyncProcessor._LOGGER.error("TTransportException inside handler", e);
                     fb.close();
                  } else {
                     TSerializable msg;
                     if (e instanceof TApplicationException) {
                        TCLIService.AsyncProcessor._LOGGER.error("TApplicationException inside handler", e);
                        msgType = 3;
                        msg = (TApplicationException)e;
                     } else {
                        TCLIService.AsyncProcessor._LOGGER.error("Exception inside handler", e);
                        msgType = 3;
                        msg = new TApplicationException(6, e.getMessage());
                     }

                     try {
                        ((AsyncProcessFunction)GetTables.this).sendResponse(fb, msg, msgType, seqid);
                     } catch (Exception ex) {
                        TCLIService.AsyncProcessor._LOGGER.error("Exception writing to internal frame buffer", ex);
                        fb.close();
                     }

                  }
               }
            };
         }

         protected boolean isOneway() {
            return false;
         }

         public void start(AsyncIface iface, GetTables_args args, AsyncMethodCallback resultHandler) throws TException {
            iface.GetTables(args.req, resultHandler);
         }
      }

      @Public
      @Stable
      public static class GetTableTypes extends AsyncProcessFunction {
         public GetTableTypes() {
            super("GetTableTypes");
         }

         public GetTableTypes_args getEmptyArgsInstance() {
            return new GetTableTypes_args();
         }

         public AsyncMethodCallback getResultHandler(final AbstractNonblockingServer.AsyncFrameBuffer fb, final int seqid) {
            return new AsyncMethodCallback() {
               public void onComplete(TGetTableTypesResp o) {
                  GetTableTypes_result result = new GetTableTypes_result();
                  result.success = o;

                  try {
                     ((AsyncProcessFunction)GetTableTypes.this).sendResponse(fb, result, (byte)2, seqid);
                  } catch (TTransportException e) {
                     TCLIService.AsyncProcessor._LOGGER.error("TTransportException writing to internal frame buffer", e);
                     fb.close();
                  } catch (Exception e) {
                     TCLIService.AsyncProcessor._LOGGER.error("Exception writing to internal frame buffer", e);
                     this.onError(e);
                  }

               }

               public void onError(Exception e) {
                  byte msgType = 2;
                  new GetTableTypes_result();
                  if (e instanceof TTransportException) {
                     TCLIService.AsyncProcessor._LOGGER.error("TTransportException inside handler", e);
                     fb.close();
                  } else {
                     TSerializable msg;
                     if (e instanceof TApplicationException) {
                        TCLIService.AsyncProcessor._LOGGER.error("TApplicationException inside handler", e);
                        msgType = 3;
                        msg = (TApplicationException)e;
                     } else {
                        TCLIService.AsyncProcessor._LOGGER.error("Exception inside handler", e);
                        msgType = 3;
                        msg = new TApplicationException(6, e.getMessage());
                     }

                     try {
                        ((AsyncProcessFunction)GetTableTypes.this).sendResponse(fb, msg, msgType, seqid);
                     } catch (Exception ex) {
                        TCLIService.AsyncProcessor._LOGGER.error("Exception writing to internal frame buffer", ex);
                        fb.close();
                     }

                  }
               }
            };
         }

         protected boolean isOneway() {
            return false;
         }

         public void start(AsyncIface iface, GetTableTypes_args args, AsyncMethodCallback resultHandler) throws TException {
            iface.GetTableTypes(args.req, resultHandler);
         }
      }

      @Public
      @Stable
      public static class GetColumns extends AsyncProcessFunction {
         public GetColumns() {
            super("GetColumns");
         }

         public GetColumns_args getEmptyArgsInstance() {
            return new GetColumns_args();
         }

         public AsyncMethodCallback getResultHandler(final AbstractNonblockingServer.AsyncFrameBuffer fb, final int seqid) {
            return new AsyncMethodCallback() {
               public void onComplete(TGetColumnsResp o) {
                  GetColumns_result result = new GetColumns_result();
                  result.success = o;

                  try {
                     ((AsyncProcessFunction)GetColumns.this).sendResponse(fb, result, (byte)2, seqid);
                  } catch (TTransportException e) {
                     TCLIService.AsyncProcessor._LOGGER.error("TTransportException writing to internal frame buffer", e);
                     fb.close();
                  } catch (Exception e) {
                     TCLIService.AsyncProcessor._LOGGER.error("Exception writing to internal frame buffer", e);
                     this.onError(e);
                  }

               }

               public void onError(Exception e) {
                  byte msgType = 2;
                  new GetColumns_result();
                  if (e instanceof TTransportException) {
                     TCLIService.AsyncProcessor._LOGGER.error("TTransportException inside handler", e);
                     fb.close();
                  } else {
                     TSerializable msg;
                     if (e instanceof TApplicationException) {
                        TCLIService.AsyncProcessor._LOGGER.error("TApplicationException inside handler", e);
                        msgType = 3;
                        msg = (TApplicationException)e;
                     } else {
                        TCLIService.AsyncProcessor._LOGGER.error("Exception inside handler", e);
                        msgType = 3;
                        msg = new TApplicationException(6, e.getMessage());
                     }

                     try {
                        ((AsyncProcessFunction)GetColumns.this).sendResponse(fb, msg, msgType, seqid);
                     } catch (Exception ex) {
                        TCLIService.AsyncProcessor._LOGGER.error("Exception writing to internal frame buffer", ex);
                        fb.close();
                     }

                  }
               }
            };
         }

         protected boolean isOneway() {
            return false;
         }

         public void start(AsyncIface iface, GetColumns_args args, AsyncMethodCallback resultHandler) throws TException {
            iface.GetColumns(args.req, resultHandler);
         }
      }

      @Public
      @Stable
      public static class GetFunctions extends AsyncProcessFunction {
         public GetFunctions() {
            super("GetFunctions");
         }

         public GetFunctions_args getEmptyArgsInstance() {
            return new GetFunctions_args();
         }

         public AsyncMethodCallback getResultHandler(final AbstractNonblockingServer.AsyncFrameBuffer fb, final int seqid) {
            return new AsyncMethodCallback() {
               public void onComplete(TGetFunctionsResp o) {
                  GetFunctions_result result = new GetFunctions_result();
                  result.success = o;

                  try {
                     ((AsyncProcessFunction)GetFunctions.this).sendResponse(fb, result, (byte)2, seqid);
                  } catch (TTransportException e) {
                     TCLIService.AsyncProcessor._LOGGER.error("TTransportException writing to internal frame buffer", e);
                     fb.close();
                  } catch (Exception e) {
                     TCLIService.AsyncProcessor._LOGGER.error("Exception writing to internal frame buffer", e);
                     this.onError(e);
                  }

               }

               public void onError(Exception e) {
                  byte msgType = 2;
                  new GetFunctions_result();
                  if (e instanceof TTransportException) {
                     TCLIService.AsyncProcessor._LOGGER.error("TTransportException inside handler", e);
                     fb.close();
                  } else {
                     TSerializable msg;
                     if (e instanceof TApplicationException) {
                        TCLIService.AsyncProcessor._LOGGER.error("TApplicationException inside handler", e);
                        msgType = 3;
                        msg = (TApplicationException)e;
                     } else {
                        TCLIService.AsyncProcessor._LOGGER.error("Exception inside handler", e);
                        msgType = 3;
                        msg = new TApplicationException(6, e.getMessage());
                     }

                     try {
                        ((AsyncProcessFunction)GetFunctions.this).sendResponse(fb, msg, msgType, seqid);
                     } catch (Exception ex) {
                        TCLIService.AsyncProcessor._LOGGER.error("Exception writing to internal frame buffer", ex);
                        fb.close();
                     }

                  }
               }
            };
         }

         protected boolean isOneway() {
            return false;
         }

         public void start(AsyncIface iface, GetFunctions_args args, AsyncMethodCallback resultHandler) throws TException {
            iface.GetFunctions(args.req, resultHandler);
         }
      }

      @Public
      @Stable
      public static class GetPrimaryKeys extends AsyncProcessFunction {
         public GetPrimaryKeys() {
            super("GetPrimaryKeys");
         }

         public GetPrimaryKeys_args getEmptyArgsInstance() {
            return new GetPrimaryKeys_args();
         }

         public AsyncMethodCallback getResultHandler(final AbstractNonblockingServer.AsyncFrameBuffer fb, final int seqid) {
            return new AsyncMethodCallback() {
               public void onComplete(TGetPrimaryKeysResp o) {
                  GetPrimaryKeys_result result = new GetPrimaryKeys_result();
                  result.success = o;

                  try {
                     ((AsyncProcessFunction)GetPrimaryKeys.this).sendResponse(fb, result, (byte)2, seqid);
                  } catch (TTransportException e) {
                     TCLIService.AsyncProcessor._LOGGER.error("TTransportException writing to internal frame buffer", e);
                     fb.close();
                  } catch (Exception e) {
                     TCLIService.AsyncProcessor._LOGGER.error("Exception writing to internal frame buffer", e);
                     this.onError(e);
                  }

               }

               public void onError(Exception e) {
                  byte msgType = 2;
                  new GetPrimaryKeys_result();
                  if (e instanceof TTransportException) {
                     TCLIService.AsyncProcessor._LOGGER.error("TTransportException inside handler", e);
                     fb.close();
                  } else {
                     TSerializable msg;
                     if (e instanceof TApplicationException) {
                        TCLIService.AsyncProcessor._LOGGER.error("TApplicationException inside handler", e);
                        msgType = 3;
                        msg = (TApplicationException)e;
                     } else {
                        TCLIService.AsyncProcessor._LOGGER.error("Exception inside handler", e);
                        msgType = 3;
                        msg = new TApplicationException(6, e.getMessage());
                     }

                     try {
                        ((AsyncProcessFunction)GetPrimaryKeys.this).sendResponse(fb, msg, msgType, seqid);
                     } catch (Exception ex) {
                        TCLIService.AsyncProcessor._LOGGER.error("Exception writing to internal frame buffer", ex);
                        fb.close();
                     }

                  }
               }
            };
         }

         protected boolean isOneway() {
            return false;
         }

         public void start(AsyncIface iface, GetPrimaryKeys_args args, AsyncMethodCallback resultHandler) throws TException {
            iface.GetPrimaryKeys(args.req, resultHandler);
         }
      }

      @Public
      @Stable
      public static class GetCrossReference extends AsyncProcessFunction {
         public GetCrossReference() {
            super("GetCrossReference");
         }

         public GetCrossReference_args getEmptyArgsInstance() {
            return new GetCrossReference_args();
         }

         public AsyncMethodCallback getResultHandler(final AbstractNonblockingServer.AsyncFrameBuffer fb, final int seqid) {
            return new AsyncMethodCallback() {
               public void onComplete(TGetCrossReferenceResp o) {
                  GetCrossReference_result result = new GetCrossReference_result();
                  result.success = o;

                  try {
                     ((AsyncProcessFunction)GetCrossReference.this).sendResponse(fb, result, (byte)2, seqid);
                  } catch (TTransportException e) {
                     TCLIService.AsyncProcessor._LOGGER.error("TTransportException writing to internal frame buffer", e);
                     fb.close();
                  } catch (Exception e) {
                     TCLIService.AsyncProcessor._LOGGER.error("Exception writing to internal frame buffer", e);
                     this.onError(e);
                  }

               }

               public void onError(Exception e) {
                  byte msgType = 2;
                  new GetCrossReference_result();
                  if (e instanceof TTransportException) {
                     TCLIService.AsyncProcessor._LOGGER.error("TTransportException inside handler", e);
                     fb.close();
                  } else {
                     TSerializable msg;
                     if (e instanceof TApplicationException) {
                        TCLIService.AsyncProcessor._LOGGER.error("TApplicationException inside handler", e);
                        msgType = 3;
                        msg = (TApplicationException)e;
                     } else {
                        TCLIService.AsyncProcessor._LOGGER.error("Exception inside handler", e);
                        msgType = 3;
                        msg = new TApplicationException(6, e.getMessage());
                     }

                     try {
                        ((AsyncProcessFunction)GetCrossReference.this).sendResponse(fb, msg, msgType, seqid);
                     } catch (Exception ex) {
                        TCLIService.AsyncProcessor._LOGGER.error("Exception writing to internal frame buffer", ex);
                        fb.close();
                     }

                  }
               }
            };
         }

         protected boolean isOneway() {
            return false;
         }

         public void start(AsyncIface iface, GetCrossReference_args args, AsyncMethodCallback resultHandler) throws TException {
            iface.GetCrossReference(args.req, resultHandler);
         }
      }

      @Public
      @Stable
      public static class GetOperationStatus extends AsyncProcessFunction {
         public GetOperationStatus() {
            super("GetOperationStatus");
         }

         public GetOperationStatus_args getEmptyArgsInstance() {
            return new GetOperationStatus_args();
         }

         public AsyncMethodCallback getResultHandler(final AbstractNonblockingServer.AsyncFrameBuffer fb, final int seqid) {
            return new AsyncMethodCallback() {
               public void onComplete(TGetOperationStatusResp o) {
                  GetOperationStatus_result result = new GetOperationStatus_result();
                  result.success = o;

                  try {
                     ((AsyncProcessFunction)GetOperationStatus.this).sendResponse(fb, result, (byte)2, seqid);
                  } catch (TTransportException e) {
                     TCLIService.AsyncProcessor._LOGGER.error("TTransportException writing to internal frame buffer", e);
                     fb.close();
                  } catch (Exception e) {
                     TCLIService.AsyncProcessor._LOGGER.error("Exception writing to internal frame buffer", e);
                     this.onError(e);
                  }

               }

               public void onError(Exception e) {
                  byte msgType = 2;
                  new GetOperationStatus_result();
                  if (e instanceof TTransportException) {
                     TCLIService.AsyncProcessor._LOGGER.error("TTransportException inside handler", e);
                     fb.close();
                  } else {
                     TSerializable msg;
                     if (e instanceof TApplicationException) {
                        TCLIService.AsyncProcessor._LOGGER.error("TApplicationException inside handler", e);
                        msgType = 3;
                        msg = (TApplicationException)e;
                     } else {
                        TCLIService.AsyncProcessor._LOGGER.error("Exception inside handler", e);
                        msgType = 3;
                        msg = new TApplicationException(6, e.getMessage());
                     }

                     try {
                        ((AsyncProcessFunction)GetOperationStatus.this).sendResponse(fb, msg, msgType, seqid);
                     } catch (Exception ex) {
                        TCLIService.AsyncProcessor._LOGGER.error("Exception writing to internal frame buffer", ex);
                        fb.close();
                     }

                  }
               }
            };
         }

         protected boolean isOneway() {
            return false;
         }

         public void start(AsyncIface iface, GetOperationStatus_args args, AsyncMethodCallback resultHandler) throws TException {
            iface.GetOperationStatus(args.req, resultHandler);
         }
      }

      @Public
      @Stable
      public static class CancelOperation extends AsyncProcessFunction {
         public CancelOperation() {
            super("CancelOperation");
         }

         public CancelOperation_args getEmptyArgsInstance() {
            return new CancelOperation_args();
         }

         public AsyncMethodCallback getResultHandler(final AbstractNonblockingServer.AsyncFrameBuffer fb, final int seqid) {
            return new AsyncMethodCallback() {
               public void onComplete(TCancelOperationResp o) {
                  CancelOperation_result result = new CancelOperation_result();
                  result.success = o;

                  try {
                     ((AsyncProcessFunction)CancelOperation.this).sendResponse(fb, result, (byte)2, seqid);
                  } catch (TTransportException e) {
                     TCLIService.AsyncProcessor._LOGGER.error("TTransportException writing to internal frame buffer", e);
                     fb.close();
                  } catch (Exception e) {
                     TCLIService.AsyncProcessor._LOGGER.error("Exception writing to internal frame buffer", e);
                     this.onError(e);
                  }

               }

               public void onError(Exception e) {
                  byte msgType = 2;
                  new CancelOperation_result();
                  if (e instanceof TTransportException) {
                     TCLIService.AsyncProcessor._LOGGER.error("TTransportException inside handler", e);
                     fb.close();
                  } else {
                     TSerializable msg;
                     if (e instanceof TApplicationException) {
                        TCLIService.AsyncProcessor._LOGGER.error("TApplicationException inside handler", e);
                        msgType = 3;
                        msg = (TApplicationException)e;
                     } else {
                        TCLIService.AsyncProcessor._LOGGER.error("Exception inside handler", e);
                        msgType = 3;
                        msg = new TApplicationException(6, e.getMessage());
                     }

                     try {
                        ((AsyncProcessFunction)CancelOperation.this).sendResponse(fb, msg, msgType, seqid);
                     } catch (Exception ex) {
                        TCLIService.AsyncProcessor._LOGGER.error("Exception writing to internal frame buffer", ex);
                        fb.close();
                     }

                  }
               }
            };
         }

         protected boolean isOneway() {
            return false;
         }

         public void start(AsyncIface iface, CancelOperation_args args, AsyncMethodCallback resultHandler) throws TException {
            iface.CancelOperation(args.req, resultHandler);
         }
      }

      @Public
      @Stable
      public static class CloseOperation extends AsyncProcessFunction {
         public CloseOperation() {
            super("CloseOperation");
         }

         public CloseOperation_args getEmptyArgsInstance() {
            return new CloseOperation_args();
         }

         public AsyncMethodCallback getResultHandler(final AbstractNonblockingServer.AsyncFrameBuffer fb, final int seqid) {
            return new AsyncMethodCallback() {
               public void onComplete(TCloseOperationResp o) {
                  CloseOperation_result result = new CloseOperation_result();
                  result.success = o;

                  try {
                     ((AsyncProcessFunction)CloseOperation.this).sendResponse(fb, result, (byte)2, seqid);
                  } catch (TTransportException e) {
                     TCLIService.AsyncProcessor._LOGGER.error("TTransportException writing to internal frame buffer", e);
                     fb.close();
                  } catch (Exception e) {
                     TCLIService.AsyncProcessor._LOGGER.error("Exception writing to internal frame buffer", e);
                     this.onError(e);
                  }

               }

               public void onError(Exception e) {
                  byte msgType = 2;
                  new CloseOperation_result();
                  if (e instanceof TTransportException) {
                     TCLIService.AsyncProcessor._LOGGER.error("TTransportException inside handler", e);
                     fb.close();
                  } else {
                     TSerializable msg;
                     if (e instanceof TApplicationException) {
                        TCLIService.AsyncProcessor._LOGGER.error("TApplicationException inside handler", e);
                        msgType = 3;
                        msg = (TApplicationException)e;
                     } else {
                        TCLIService.AsyncProcessor._LOGGER.error("Exception inside handler", e);
                        msgType = 3;
                        msg = new TApplicationException(6, e.getMessage());
                     }

                     try {
                        ((AsyncProcessFunction)CloseOperation.this).sendResponse(fb, msg, msgType, seqid);
                     } catch (Exception ex) {
                        TCLIService.AsyncProcessor._LOGGER.error("Exception writing to internal frame buffer", ex);
                        fb.close();
                     }

                  }
               }
            };
         }

         protected boolean isOneway() {
            return false;
         }

         public void start(AsyncIface iface, CloseOperation_args args, AsyncMethodCallback resultHandler) throws TException {
            iface.CloseOperation(args.req, resultHandler);
         }
      }

      @Public
      @Stable
      public static class GetResultSetMetadata extends AsyncProcessFunction {
         public GetResultSetMetadata() {
            super("GetResultSetMetadata");
         }

         public GetResultSetMetadata_args getEmptyArgsInstance() {
            return new GetResultSetMetadata_args();
         }

         public AsyncMethodCallback getResultHandler(final AbstractNonblockingServer.AsyncFrameBuffer fb, final int seqid) {
            return new AsyncMethodCallback() {
               public void onComplete(TGetResultSetMetadataResp o) {
                  GetResultSetMetadata_result result = new GetResultSetMetadata_result();
                  result.success = o;

                  try {
                     ((AsyncProcessFunction)GetResultSetMetadata.this).sendResponse(fb, result, (byte)2, seqid);
                  } catch (TTransportException e) {
                     TCLIService.AsyncProcessor._LOGGER.error("TTransportException writing to internal frame buffer", e);
                     fb.close();
                  } catch (Exception e) {
                     TCLIService.AsyncProcessor._LOGGER.error("Exception writing to internal frame buffer", e);
                     this.onError(e);
                  }

               }

               public void onError(Exception e) {
                  byte msgType = 2;
                  new GetResultSetMetadata_result();
                  if (e instanceof TTransportException) {
                     TCLIService.AsyncProcessor._LOGGER.error("TTransportException inside handler", e);
                     fb.close();
                  } else {
                     TSerializable msg;
                     if (e instanceof TApplicationException) {
                        TCLIService.AsyncProcessor._LOGGER.error("TApplicationException inside handler", e);
                        msgType = 3;
                        msg = (TApplicationException)e;
                     } else {
                        TCLIService.AsyncProcessor._LOGGER.error("Exception inside handler", e);
                        msgType = 3;
                        msg = new TApplicationException(6, e.getMessage());
                     }

                     try {
                        ((AsyncProcessFunction)GetResultSetMetadata.this).sendResponse(fb, msg, msgType, seqid);
                     } catch (Exception ex) {
                        TCLIService.AsyncProcessor._LOGGER.error("Exception writing to internal frame buffer", ex);
                        fb.close();
                     }

                  }
               }
            };
         }

         protected boolean isOneway() {
            return false;
         }

         public void start(AsyncIface iface, GetResultSetMetadata_args args, AsyncMethodCallback resultHandler) throws TException {
            iface.GetResultSetMetadata(args.req, resultHandler);
         }
      }

      @Public
      @Stable
      public static class FetchResults extends AsyncProcessFunction {
         public FetchResults() {
            super("FetchResults");
         }

         public FetchResults_args getEmptyArgsInstance() {
            return new FetchResults_args();
         }

         public AsyncMethodCallback getResultHandler(final AbstractNonblockingServer.AsyncFrameBuffer fb, final int seqid) {
            return new AsyncMethodCallback() {
               public void onComplete(TFetchResultsResp o) {
                  FetchResults_result result = new FetchResults_result();
                  result.success = o;

                  try {
                     ((AsyncProcessFunction)FetchResults.this).sendResponse(fb, result, (byte)2, seqid);
                  } catch (TTransportException e) {
                     TCLIService.AsyncProcessor._LOGGER.error("TTransportException writing to internal frame buffer", e);
                     fb.close();
                  } catch (Exception e) {
                     TCLIService.AsyncProcessor._LOGGER.error("Exception writing to internal frame buffer", e);
                     this.onError(e);
                  }

               }

               public void onError(Exception e) {
                  byte msgType = 2;
                  new FetchResults_result();
                  if (e instanceof TTransportException) {
                     TCLIService.AsyncProcessor._LOGGER.error("TTransportException inside handler", e);
                     fb.close();
                  } else {
                     TSerializable msg;
                     if (e instanceof TApplicationException) {
                        TCLIService.AsyncProcessor._LOGGER.error("TApplicationException inside handler", e);
                        msgType = 3;
                        msg = (TApplicationException)e;
                     } else {
                        TCLIService.AsyncProcessor._LOGGER.error("Exception inside handler", e);
                        msgType = 3;
                        msg = new TApplicationException(6, e.getMessage());
                     }

                     try {
                        ((AsyncProcessFunction)FetchResults.this).sendResponse(fb, msg, msgType, seqid);
                     } catch (Exception ex) {
                        TCLIService.AsyncProcessor._LOGGER.error("Exception writing to internal frame buffer", ex);
                        fb.close();
                     }

                  }
               }
            };
         }

         protected boolean isOneway() {
            return false;
         }

         public void start(AsyncIface iface, FetchResults_args args, AsyncMethodCallback resultHandler) throws TException {
            iface.FetchResults(args.req, resultHandler);
         }
      }

      @Public
      @Stable
      public static class GetDelegationToken extends AsyncProcessFunction {
         public GetDelegationToken() {
            super("GetDelegationToken");
         }

         public GetDelegationToken_args getEmptyArgsInstance() {
            return new GetDelegationToken_args();
         }

         public AsyncMethodCallback getResultHandler(final AbstractNonblockingServer.AsyncFrameBuffer fb, final int seqid) {
            return new AsyncMethodCallback() {
               public void onComplete(TGetDelegationTokenResp o) {
                  GetDelegationToken_result result = new GetDelegationToken_result();
                  result.success = o;

                  try {
                     ((AsyncProcessFunction)GetDelegationToken.this).sendResponse(fb, result, (byte)2, seqid);
                  } catch (TTransportException e) {
                     TCLIService.AsyncProcessor._LOGGER.error("TTransportException writing to internal frame buffer", e);
                     fb.close();
                  } catch (Exception e) {
                     TCLIService.AsyncProcessor._LOGGER.error("Exception writing to internal frame buffer", e);
                     this.onError(e);
                  }

               }

               public void onError(Exception e) {
                  byte msgType = 2;
                  new GetDelegationToken_result();
                  if (e instanceof TTransportException) {
                     TCLIService.AsyncProcessor._LOGGER.error("TTransportException inside handler", e);
                     fb.close();
                  } else {
                     TSerializable msg;
                     if (e instanceof TApplicationException) {
                        TCLIService.AsyncProcessor._LOGGER.error("TApplicationException inside handler", e);
                        msgType = 3;
                        msg = (TApplicationException)e;
                     } else {
                        TCLIService.AsyncProcessor._LOGGER.error("Exception inside handler", e);
                        msgType = 3;
                        msg = new TApplicationException(6, e.getMessage());
                     }

                     try {
                        ((AsyncProcessFunction)GetDelegationToken.this).sendResponse(fb, msg, msgType, seqid);
                     } catch (Exception ex) {
                        TCLIService.AsyncProcessor._LOGGER.error("Exception writing to internal frame buffer", ex);
                        fb.close();
                     }

                  }
               }
            };
         }

         protected boolean isOneway() {
            return false;
         }

         public void start(AsyncIface iface, GetDelegationToken_args args, AsyncMethodCallback resultHandler) throws TException {
            iface.GetDelegationToken(args.req, resultHandler);
         }
      }

      @Public
      @Stable
      public static class CancelDelegationToken extends AsyncProcessFunction {
         public CancelDelegationToken() {
            super("CancelDelegationToken");
         }

         public CancelDelegationToken_args getEmptyArgsInstance() {
            return new CancelDelegationToken_args();
         }

         public AsyncMethodCallback getResultHandler(final AbstractNonblockingServer.AsyncFrameBuffer fb, final int seqid) {
            return new AsyncMethodCallback() {
               public void onComplete(TCancelDelegationTokenResp o) {
                  CancelDelegationToken_result result = new CancelDelegationToken_result();
                  result.success = o;

                  try {
                     ((AsyncProcessFunction)CancelDelegationToken.this).sendResponse(fb, result, (byte)2, seqid);
                  } catch (TTransportException e) {
                     TCLIService.AsyncProcessor._LOGGER.error("TTransportException writing to internal frame buffer", e);
                     fb.close();
                  } catch (Exception e) {
                     TCLIService.AsyncProcessor._LOGGER.error("Exception writing to internal frame buffer", e);
                     this.onError(e);
                  }

               }

               public void onError(Exception e) {
                  byte msgType = 2;
                  new CancelDelegationToken_result();
                  if (e instanceof TTransportException) {
                     TCLIService.AsyncProcessor._LOGGER.error("TTransportException inside handler", e);
                     fb.close();
                  } else {
                     TSerializable msg;
                     if (e instanceof TApplicationException) {
                        TCLIService.AsyncProcessor._LOGGER.error("TApplicationException inside handler", e);
                        msgType = 3;
                        msg = (TApplicationException)e;
                     } else {
                        TCLIService.AsyncProcessor._LOGGER.error("Exception inside handler", e);
                        msgType = 3;
                        msg = new TApplicationException(6, e.getMessage());
                     }

                     try {
                        ((AsyncProcessFunction)CancelDelegationToken.this).sendResponse(fb, msg, msgType, seqid);
                     } catch (Exception ex) {
                        TCLIService.AsyncProcessor._LOGGER.error("Exception writing to internal frame buffer", ex);
                        fb.close();
                     }

                  }
               }
            };
         }

         protected boolean isOneway() {
            return false;
         }

         public void start(AsyncIface iface, CancelDelegationToken_args args, AsyncMethodCallback resultHandler) throws TException {
            iface.CancelDelegationToken(args.req, resultHandler);
         }
      }

      @Public
      @Stable
      public static class RenewDelegationToken extends AsyncProcessFunction {
         public RenewDelegationToken() {
            super("RenewDelegationToken");
         }

         public RenewDelegationToken_args getEmptyArgsInstance() {
            return new RenewDelegationToken_args();
         }

         public AsyncMethodCallback getResultHandler(final AbstractNonblockingServer.AsyncFrameBuffer fb, final int seqid) {
            return new AsyncMethodCallback() {
               public void onComplete(TRenewDelegationTokenResp o) {
                  RenewDelegationToken_result result = new RenewDelegationToken_result();
                  result.success = o;

                  try {
                     ((AsyncProcessFunction)RenewDelegationToken.this).sendResponse(fb, result, (byte)2, seqid);
                  } catch (TTransportException e) {
                     TCLIService.AsyncProcessor._LOGGER.error("TTransportException writing to internal frame buffer", e);
                     fb.close();
                  } catch (Exception e) {
                     TCLIService.AsyncProcessor._LOGGER.error("Exception writing to internal frame buffer", e);
                     this.onError(e);
                  }

               }

               public void onError(Exception e) {
                  byte msgType = 2;
                  new RenewDelegationToken_result();
                  if (e instanceof TTransportException) {
                     TCLIService.AsyncProcessor._LOGGER.error("TTransportException inside handler", e);
                     fb.close();
                  } else {
                     TSerializable msg;
                     if (e instanceof TApplicationException) {
                        TCLIService.AsyncProcessor._LOGGER.error("TApplicationException inside handler", e);
                        msgType = 3;
                        msg = (TApplicationException)e;
                     } else {
                        TCLIService.AsyncProcessor._LOGGER.error("Exception inside handler", e);
                        msgType = 3;
                        msg = new TApplicationException(6, e.getMessage());
                     }

                     try {
                        ((AsyncProcessFunction)RenewDelegationToken.this).sendResponse(fb, msg, msgType, seqid);
                     } catch (Exception ex) {
                        TCLIService.AsyncProcessor._LOGGER.error("Exception writing to internal frame buffer", ex);
                        fb.close();
                     }

                  }
               }
            };
         }

         protected boolean isOneway() {
            return false;
         }

         public void start(AsyncIface iface, RenewDelegationToken_args args, AsyncMethodCallback resultHandler) throws TException {
            iface.RenewDelegationToken(args.req, resultHandler);
         }
      }

      @Public
      @Stable
      public static class GetQueryId extends AsyncProcessFunction {
         public GetQueryId() {
            super("GetQueryId");
         }

         public GetQueryId_args getEmptyArgsInstance() {
            return new GetQueryId_args();
         }

         public AsyncMethodCallback getResultHandler(final AbstractNonblockingServer.AsyncFrameBuffer fb, final int seqid) {
            return new AsyncMethodCallback() {
               public void onComplete(TGetQueryIdResp o) {
                  GetQueryId_result result = new GetQueryId_result();
                  result.success = o;

                  try {
                     ((AsyncProcessFunction)GetQueryId.this).sendResponse(fb, result, (byte)2, seqid);
                  } catch (TTransportException e) {
                     TCLIService.AsyncProcessor._LOGGER.error("TTransportException writing to internal frame buffer", e);
                     fb.close();
                  } catch (Exception e) {
                     TCLIService.AsyncProcessor._LOGGER.error("Exception writing to internal frame buffer", e);
                     this.onError(e);
                  }

               }

               public void onError(Exception e) {
                  byte msgType = 2;
                  new GetQueryId_result();
                  if (e instanceof TTransportException) {
                     TCLIService.AsyncProcessor._LOGGER.error("TTransportException inside handler", e);
                     fb.close();
                  } else {
                     TSerializable msg;
                     if (e instanceof TApplicationException) {
                        TCLIService.AsyncProcessor._LOGGER.error("TApplicationException inside handler", e);
                        msgType = 3;
                        msg = (TApplicationException)e;
                     } else {
                        TCLIService.AsyncProcessor._LOGGER.error("Exception inside handler", e);
                        msgType = 3;
                        msg = new TApplicationException(6, e.getMessage());
                     }

                     try {
                        ((AsyncProcessFunction)GetQueryId.this).sendResponse(fb, msg, msgType, seqid);
                     } catch (Exception ex) {
                        TCLIService.AsyncProcessor._LOGGER.error("Exception writing to internal frame buffer", ex);
                        fb.close();
                     }

                  }
               }
            };
         }

         protected boolean isOneway() {
            return false;
         }

         public void start(AsyncIface iface, GetQueryId_args args, AsyncMethodCallback resultHandler) throws TException {
            iface.GetQueryId(args.req, resultHandler);
         }
      }

      @Public
      @Stable
      public static class SetClientInfo extends AsyncProcessFunction {
         public SetClientInfo() {
            super("SetClientInfo");
         }

         public SetClientInfo_args getEmptyArgsInstance() {
            return new SetClientInfo_args();
         }

         public AsyncMethodCallback getResultHandler(final AbstractNonblockingServer.AsyncFrameBuffer fb, final int seqid) {
            return new AsyncMethodCallback() {
               public void onComplete(TSetClientInfoResp o) {
                  SetClientInfo_result result = new SetClientInfo_result();
                  result.success = o;

                  try {
                     ((AsyncProcessFunction)SetClientInfo.this).sendResponse(fb, result, (byte)2, seqid);
                  } catch (TTransportException e) {
                     TCLIService.AsyncProcessor._LOGGER.error("TTransportException writing to internal frame buffer", e);
                     fb.close();
                  } catch (Exception e) {
                     TCLIService.AsyncProcessor._LOGGER.error("Exception writing to internal frame buffer", e);
                     this.onError(e);
                  }

               }

               public void onError(Exception e) {
                  byte msgType = 2;
                  new SetClientInfo_result();
                  if (e instanceof TTransportException) {
                     TCLIService.AsyncProcessor._LOGGER.error("TTransportException inside handler", e);
                     fb.close();
                  } else {
                     TSerializable msg;
                     if (e instanceof TApplicationException) {
                        TCLIService.AsyncProcessor._LOGGER.error("TApplicationException inside handler", e);
                        msgType = 3;
                        msg = (TApplicationException)e;
                     } else {
                        TCLIService.AsyncProcessor._LOGGER.error("Exception inside handler", e);
                        msgType = 3;
                        msg = new TApplicationException(6, e.getMessage());
                     }

                     try {
                        ((AsyncProcessFunction)SetClientInfo.this).sendResponse(fb, msg, msgType, seqid);
                     } catch (Exception ex) {
                        TCLIService.AsyncProcessor._LOGGER.error("Exception writing to internal frame buffer", ex);
                        fb.close();
                     }

                  }
               }
            };
         }

         protected boolean isOneway() {
            return false;
         }

         public void start(AsyncIface iface, SetClientInfo_args args, AsyncMethodCallback resultHandler) throws TException {
            iface.SetClientInfo(args.req, resultHandler);
         }
      }

      @Public
      @Stable
      public static class UploadData extends AsyncProcessFunction {
         public UploadData() {
            super("UploadData");
         }

         public UploadData_args getEmptyArgsInstance() {
            return new UploadData_args();
         }

         public AsyncMethodCallback getResultHandler(final AbstractNonblockingServer.AsyncFrameBuffer fb, final int seqid) {
            return new AsyncMethodCallback() {
               public void onComplete(TUploadDataResp o) {
                  UploadData_result result = new UploadData_result();
                  result.success = o;

                  try {
                     ((AsyncProcessFunction)UploadData.this).sendResponse(fb, result, (byte)2, seqid);
                  } catch (TTransportException e) {
                     TCLIService.AsyncProcessor._LOGGER.error("TTransportException writing to internal frame buffer", e);
                     fb.close();
                  } catch (Exception e) {
                     TCLIService.AsyncProcessor._LOGGER.error("Exception writing to internal frame buffer", e);
                     this.onError(e);
                  }

               }

               public void onError(Exception e) {
                  byte msgType = 2;
                  new UploadData_result();
                  if (e instanceof TTransportException) {
                     TCLIService.AsyncProcessor._LOGGER.error("TTransportException inside handler", e);
                     fb.close();
                  } else {
                     TSerializable msg;
                     if (e instanceof TApplicationException) {
                        TCLIService.AsyncProcessor._LOGGER.error("TApplicationException inside handler", e);
                        msgType = 3;
                        msg = (TApplicationException)e;
                     } else {
                        TCLIService.AsyncProcessor._LOGGER.error("Exception inside handler", e);
                        msgType = 3;
                        msg = new TApplicationException(6, e.getMessage());
                     }

                     try {
                        ((AsyncProcessFunction)UploadData.this).sendResponse(fb, msg, msgType, seqid);
                     } catch (Exception ex) {
                        TCLIService.AsyncProcessor._LOGGER.error("Exception writing to internal frame buffer", ex);
                        fb.close();
                     }

                  }
               }
            };
         }

         protected boolean isOneway() {
            return false;
         }

         public void start(AsyncIface iface, UploadData_args args, AsyncMethodCallback resultHandler) throws TException {
            iface.UploadData(args.req, resultHandler);
         }
      }

      @Public
      @Stable
      public static class DownloadData extends AsyncProcessFunction {
         public DownloadData() {
            super("DownloadData");
         }

         public DownloadData_args getEmptyArgsInstance() {
            return new DownloadData_args();
         }

         public AsyncMethodCallback getResultHandler(final AbstractNonblockingServer.AsyncFrameBuffer fb, final int seqid) {
            return new AsyncMethodCallback() {
               public void onComplete(TDownloadDataResp o) {
                  DownloadData_result result = new DownloadData_result();
                  result.success = o;

                  try {
                     ((AsyncProcessFunction)DownloadData.this).sendResponse(fb, result, (byte)2, seqid);
                  } catch (TTransportException e) {
                     TCLIService.AsyncProcessor._LOGGER.error("TTransportException writing to internal frame buffer", e);
                     fb.close();
                  } catch (Exception e) {
                     TCLIService.AsyncProcessor._LOGGER.error("Exception writing to internal frame buffer", e);
                     this.onError(e);
                  }

               }

               public void onError(Exception e) {
                  byte msgType = 2;
                  new DownloadData_result();
                  if (e instanceof TTransportException) {
                     TCLIService.AsyncProcessor._LOGGER.error("TTransportException inside handler", e);
                     fb.close();
                  } else {
                     TSerializable msg;
                     if (e instanceof TApplicationException) {
                        TCLIService.AsyncProcessor._LOGGER.error("TApplicationException inside handler", e);
                        msgType = 3;
                        msg = (TApplicationException)e;
                     } else {
                        TCLIService.AsyncProcessor._LOGGER.error("Exception inside handler", e);
                        msgType = 3;
                        msg = new TApplicationException(6, e.getMessage());
                     }

                     try {
                        ((AsyncProcessFunction)DownloadData.this).sendResponse(fb, msg, msgType, seqid);
                     } catch (Exception ex) {
                        TCLIService.AsyncProcessor._LOGGER.error("Exception writing to internal frame buffer", ex);
                        fb.close();
                     }

                  }
               }
            };
         }

         protected boolean isOneway() {
            return false;
         }

         public void start(AsyncIface iface, DownloadData_args args, AsyncMethodCallback resultHandler) throws TException {
            iface.DownloadData(args.req, resultHandler);
         }
      }
   }

   @Public
   @Stable
   public static class OpenSession_args implements TBase, Serializable, Cloneable, Comparable {
      private static final TStruct STRUCT_DESC = new TStruct("OpenSession_args");
      private static final TField REQ_FIELD_DESC = new TField("req", (byte)12, (short)1);
      private static final SchemeFactory STANDARD_SCHEME_FACTORY = new OpenSession_argsStandardSchemeFactory();
      private static final SchemeFactory TUPLE_SCHEME_FACTORY = new OpenSession_argsTupleSchemeFactory();
      @Nullable
      private TOpenSessionReq req;
      public static final Map metaDataMap;

      public OpenSession_args() {
      }

      public OpenSession_args(TOpenSessionReq req) {
         this();
         this.req = req;
      }

      public OpenSession_args(OpenSession_args other) {
         if (other.isSetReq()) {
            this.req = new TOpenSessionReq(other.req);
         }

      }

      public OpenSession_args deepCopy() {
         return new OpenSession_args(this);
      }

      public void clear() {
         this.req = null;
      }

      @Nullable
      public TOpenSessionReq getReq() {
         return this.req;
      }

      public void setReq(@Nullable TOpenSessionReq req) {
         this.req = req;
      }

      public void unsetReq() {
         this.req = null;
      }

      public boolean isSetReq() {
         return this.req != null;
      }

      public void setReqIsSet(boolean value) {
         if (!value) {
            this.req = null;
         }

      }

      public void setFieldValue(_Fields field, @Nullable Object value) {
         switch (field) {
            case REQ:
               if (value == null) {
                  this.unsetReq();
               } else {
                  this.setReq((TOpenSessionReq)value);
               }
            default:
         }
      }

      @Nullable
      public Object getFieldValue(_Fields field) {
         switch (field) {
            case REQ:
               return this.getReq();
            default:
               throw new IllegalStateException();
         }
      }

      public boolean isSet(_Fields field) {
         if (field == null) {
            throw new IllegalArgumentException();
         } else {
            switch (field) {
               case REQ:
                  return this.isSetReq();
               default:
                  throw new IllegalStateException();
            }
         }
      }

      public boolean equals(Object that) {
         return that instanceof OpenSession_args ? this.equals((OpenSession_args)that) : false;
      }

      public boolean equals(OpenSession_args that) {
         if (that == null) {
            return false;
         } else if (this == that) {
            return true;
         } else {
            boolean this_present_req = this.isSetReq();
            boolean that_present_req = that.isSetReq();
            if (this_present_req || that_present_req) {
               if (!this_present_req || !that_present_req) {
                  return false;
               }

               if (!this.req.equals(that.req)) {
                  return false;
               }
            }

            return true;
         }
      }

      public int hashCode() {
         int hashCode = 1;
         hashCode = hashCode * 8191 + (this.isSetReq() ? 131071 : 524287);
         if (this.isSetReq()) {
            hashCode = hashCode * 8191 + this.req.hashCode();
         }

         return hashCode;
      }

      public int compareTo(OpenSession_args other) {
         if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
         } else {
            int lastComparison = 0;
            lastComparison = Boolean.compare(this.isSetReq(), other.isSetReq());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetReq()) {
                  lastComparison = TBaseHelper.compareTo(this.req, other.req);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               return 0;
            }
         }
      }

      @Nullable
      public _Fields fieldForId(int fieldId) {
         return TCLIService.OpenSession_args._Fields.findByThriftId(fieldId);
      }

      public void read(TProtocol iprot) throws TException {
         scheme(iprot).read(iprot, this);
      }

      public void write(TProtocol oprot) throws TException {
         scheme(oprot).write(oprot, this);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("OpenSession_args(");
         boolean first = true;
         sb.append("req:");
         if (this.req == null) {
            sb.append("null");
         } else {
            sb.append(this.req);
         }

         first = false;
         sb.append(")");
         return sb.toString();
      }

      public void validate() throws TException {
         if (this.req != null) {
            this.req.validate();
         }

      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         try {
            this.write(new TCompactProtocol(new TIOStreamTransport(out)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         try {
            this.read(new TCompactProtocol(new TIOStreamTransport(in)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private static IScheme scheme(TProtocol proto) {
         return (StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
      }

      static {
         Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
         tmpMap.put(TCLIService.OpenSession_args._Fields.REQ, new FieldMetaData("req", (byte)3, new StructMetaData((byte)12, TOpenSessionReq.class)));
         metaDataMap = Collections.unmodifiableMap(tmpMap);
         FieldMetaData.addStructMetaDataMap(OpenSession_args.class, metaDataMap);
      }

      public static enum _Fields implements TFieldIdEnum {
         REQ((short)1, "req");

         private static final Map byName = new HashMap();
         private final short _thriftId;
         private final String _fieldName;

         @Nullable
         public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
               case 1:
                  return REQ;
               default:
                  return null;
            }
         }

         public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) {
               throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            } else {
               return fields;
            }
         }

         @Nullable
         public static _Fields findByName(String name) {
            return (_Fields)byName.get(name);
         }

         private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
         }

         public short getThriftFieldId() {
            return this._thriftId;
         }

         public String getFieldName() {
            return this._fieldName;
         }

         static {
            for(_Fields field : EnumSet.allOf(_Fields.class)) {
               byName.put(field.getFieldName(), field);
            }

         }
      }

      private static class OpenSession_argsStandardSchemeFactory implements SchemeFactory {
         private OpenSession_argsStandardSchemeFactory() {
         }

         public OpenSession_argsStandardScheme getScheme() {
            return new OpenSession_argsStandardScheme();
         }
      }

      private static class OpenSession_argsStandardScheme extends StandardScheme {
         private OpenSession_argsStandardScheme() {
         }

         public void read(TProtocol iprot, OpenSession_args struct) throws TException {
            iprot.readStructBegin();

            while(true) {
               TField schemeField = iprot.readFieldBegin();
               if (schemeField.type == 0) {
                  iprot.readStructEnd();
                  struct.validate();
                  return;
               }

               switch (schemeField.id) {
                  case 1:
                     if (schemeField.type == 12) {
                        struct.req = new TOpenSessionReq();
                        struct.req.read(iprot);
                        struct.setReqIsSet(true);
                     } else {
                        TProtocolUtil.skip(iprot, schemeField.type);
                     }
                     break;
                  default:
                     TProtocolUtil.skip(iprot, schemeField.type);
               }

               iprot.readFieldEnd();
            }
         }

         public void write(TProtocol oprot, OpenSession_args struct) throws TException {
            struct.validate();
            oprot.writeStructBegin(TCLIService.OpenSession_args.STRUCT_DESC);
            if (struct.req != null) {
               oprot.writeFieldBegin(TCLIService.OpenSession_args.REQ_FIELD_DESC);
               struct.req.write(oprot);
               oprot.writeFieldEnd();
            }

            oprot.writeFieldStop();
            oprot.writeStructEnd();
         }
      }

      private static class OpenSession_argsTupleSchemeFactory implements SchemeFactory {
         private OpenSession_argsTupleSchemeFactory() {
         }

         public OpenSession_argsTupleScheme getScheme() {
            return new OpenSession_argsTupleScheme();
         }
      }

      private static class OpenSession_argsTupleScheme extends TupleScheme {
         private OpenSession_argsTupleScheme() {
         }

         public void write(TProtocol prot, OpenSession_args struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol)prot;
            BitSet optionals = new BitSet();
            if (struct.isSetReq()) {
               optionals.set(0);
            }

            oprot.writeBitSet(optionals, 1);
            if (struct.isSetReq()) {
               struct.req.write(oprot);
            }

         }

         public void read(TProtocol prot, OpenSession_args struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol)prot;
            BitSet incoming = iprot.readBitSet(1);
            if (incoming.get(0)) {
               struct.req = new TOpenSessionReq();
               struct.req.read(iprot);
               struct.setReqIsSet(true);
            }

         }
      }
   }

   @Public
   @Stable
   public static class OpenSession_result implements TBase, Serializable, Cloneable, Comparable {
      private static final TStruct STRUCT_DESC = new TStruct("OpenSession_result");
      private static final TField SUCCESS_FIELD_DESC = new TField("success", (byte)12, (short)0);
      private static final SchemeFactory STANDARD_SCHEME_FACTORY = new OpenSession_resultStandardSchemeFactory();
      private static final SchemeFactory TUPLE_SCHEME_FACTORY = new OpenSession_resultTupleSchemeFactory();
      @Nullable
      private TOpenSessionResp success;
      public static final Map metaDataMap;

      public OpenSession_result() {
      }

      public OpenSession_result(TOpenSessionResp success) {
         this();
         this.success = success;
      }

      public OpenSession_result(OpenSession_result other) {
         if (other.isSetSuccess()) {
            this.success = new TOpenSessionResp(other.success);
         }

      }

      public OpenSession_result deepCopy() {
         return new OpenSession_result(this);
      }

      public void clear() {
         this.success = null;
      }

      @Nullable
      public TOpenSessionResp getSuccess() {
         return this.success;
      }

      public void setSuccess(@Nullable TOpenSessionResp success) {
         this.success = success;
      }

      public void unsetSuccess() {
         this.success = null;
      }

      public boolean isSetSuccess() {
         return this.success != null;
      }

      public void setSuccessIsSet(boolean value) {
         if (!value) {
            this.success = null;
         }

      }

      public void setFieldValue(_Fields field, @Nullable Object value) {
         switch (field) {
            case SUCCESS:
               if (value == null) {
                  this.unsetSuccess();
               } else {
                  this.setSuccess((TOpenSessionResp)value);
               }
            default:
         }
      }

      @Nullable
      public Object getFieldValue(_Fields field) {
         switch (field) {
            case SUCCESS:
               return this.getSuccess();
            default:
               throw new IllegalStateException();
         }
      }

      public boolean isSet(_Fields field) {
         if (field == null) {
            throw new IllegalArgumentException();
         } else {
            switch (field) {
               case SUCCESS:
                  return this.isSetSuccess();
               default:
                  throw new IllegalStateException();
            }
         }
      }

      public boolean equals(Object that) {
         return that instanceof OpenSession_result ? this.equals((OpenSession_result)that) : false;
      }

      public boolean equals(OpenSession_result that) {
         if (that == null) {
            return false;
         } else if (this == that) {
            return true;
         } else {
            boolean this_present_success = this.isSetSuccess();
            boolean that_present_success = that.isSetSuccess();
            if (this_present_success || that_present_success) {
               if (!this_present_success || !that_present_success) {
                  return false;
               }

               if (!this.success.equals(that.success)) {
                  return false;
               }
            }

            return true;
         }
      }

      public int hashCode() {
         int hashCode = 1;
         hashCode = hashCode * 8191 + (this.isSetSuccess() ? 131071 : 524287);
         if (this.isSetSuccess()) {
            hashCode = hashCode * 8191 + this.success.hashCode();
         }

         return hashCode;
      }

      public int compareTo(OpenSession_result other) {
         if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
         } else {
            int lastComparison = 0;
            lastComparison = Boolean.compare(this.isSetSuccess(), other.isSetSuccess());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetSuccess()) {
                  lastComparison = TBaseHelper.compareTo(this.success, other.success);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               return 0;
            }
         }
      }

      @Nullable
      public _Fields fieldForId(int fieldId) {
         return TCLIService.OpenSession_result._Fields.findByThriftId(fieldId);
      }

      public void read(TProtocol iprot) throws TException {
         scheme(iprot).read(iprot, this);
      }

      public void write(TProtocol oprot) throws TException {
         scheme(oprot).write(oprot, this);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("OpenSession_result(");
         boolean first = true;
         sb.append("success:");
         if (this.success == null) {
            sb.append("null");
         } else {
            sb.append(this.success);
         }

         first = false;
         sb.append(")");
         return sb.toString();
      }

      public void validate() throws TException {
         if (this.success != null) {
            this.success.validate();
         }

      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         try {
            this.write(new TCompactProtocol(new TIOStreamTransport(out)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         try {
            this.read(new TCompactProtocol(new TIOStreamTransport(in)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private static IScheme scheme(TProtocol proto) {
         return (StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
      }

      static {
         Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
         tmpMap.put(TCLIService.OpenSession_result._Fields.SUCCESS, new FieldMetaData("success", (byte)3, new StructMetaData((byte)12, TOpenSessionResp.class)));
         metaDataMap = Collections.unmodifiableMap(tmpMap);
         FieldMetaData.addStructMetaDataMap(OpenSession_result.class, metaDataMap);
      }

      public static enum _Fields implements TFieldIdEnum {
         SUCCESS((short)0, "success");

         private static final Map byName = new HashMap();
         private final short _thriftId;
         private final String _fieldName;

         @Nullable
         public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
               case 0:
                  return SUCCESS;
               default:
                  return null;
            }
         }

         public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) {
               throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            } else {
               return fields;
            }
         }

         @Nullable
         public static _Fields findByName(String name) {
            return (_Fields)byName.get(name);
         }

         private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
         }

         public short getThriftFieldId() {
            return this._thriftId;
         }

         public String getFieldName() {
            return this._fieldName;
         }

         static {
            for(_Fields field : EnumSet.allOf(_Fields.class)) {
               byName.put(field.getFieldName(), field);
            }

         }
      }

      private static class OpenSession_resultStandardSchemeFactory implements SchemeFactory {
         private OpenSession_resultStandardSchemeFactory() {
         }

         public OpenSession_resultStandardScheme getScheme() {
            return new OpenSession_resultStandardScheme();
         }
      }

      private static class OpenSession_resultStandardScheme extends StandardScheme {
         private OpenSession_resultStandardScheme() {
         }

         public void read(TProtocol iprot, OpenSession_result struct) throws TException {
            iprot.readStructBegin();

            while(true) {
               TField schemeField = iprot.readFieldBegin();
               if (schemeField.type == 0) {
                  iprot.readStructEnd();
                  struct.validate();
                  return;
               }

               switch (schemeField.id) {
                  case 0:
                     if (schemeField.type == 12) {
                        struct.success = new TOpenSessionResp();
                        struct.success.read(iprot);
                        struct.setSuccessIsSet(true);
                     } else {
                        TProtocolUtil.skip(iprot, schemeField.type);
                     }
                     break;
                  default:
                     TProtocolUtil.skip(iprot, schemeField.type);
               }

               iprot.readFieldEnd();
            }
         }

         public void write(TProtocol oprot, OpenSession_result struct) throws TException {
            struct.validate();
            oprot.writeStructBegin(TCLIService.OpenSession_result.STRUCT_DESC);
            if (struct.success != null) {
               oprot.writeFieldBegin(TCLIService.OpenSession_result.SUCCESS_FIELD_DESC);
               struct.success.write(oprot);
               oprot.writeFieldEnd();
            }

            oprot.writeFieldStop();
            oprot.writeStructEnd();
         }
      }

      private static class OpenSession_resultTupleSchemeFactory implements SchemeFactory {
         private OpenSession_resultTupleSchemeFactory() {
         }

         public OpenSession_resultTupleScheme getScheme() {
            return new OpenSession_resultTupleScheme();
         }
      }

      private static class OpenSession_resultTupleScheme extends TupleScheme {
         private OpenSession_resultTupleScheme() {
         }

         public void write(TProtocol prot, OpenSession_result struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol)prot;
            BitSet optionals = new BitSet();
            if (struct.isSetSuccess()) {
               optionals.set(0);
            }

            oprot.writeBitSet(optionals, 1);
            if (struct.isSetSuccess()) {
               struct.success.write(oprot);
            }

         }

         public void read(TProtocol prot, OpenSession_result struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol)prot;
            BitSet incoming = iprot.readBitSet(1);
            if (incoming.get(0)) {
               struct.success = new TOpenSessionResp();
               struct.success.read(iprot);
               struct.setSuccessIsSet(true);
            }

         }
      }
   }

   @Public
   @Stable
   public static class CloseSession_args implements TBase, Serializable, Cloneable, Comparable {
      private static final TStruct STRUCT_DESC = new TStruct("CloseSession_args");
      private static final TField REQ_FIELD_DESC = new TField("req", (byte)12, (short)1);
      private static final SchemeFactory STANDARD_SCHEME_FACTORY = new CloseSession_argsStandardSchemeFactory();
      private static final SchemeFactory TUPLE_SCHEME_FACTORY = new CloseSession_argsTupleSchemeFactory();
      @Nullable
      private TCloseSessionReq req;
      public static final Map metaDataMap;

      public CloseSession_args() {
      }

      public CloseSession_args(TCloseSessionReq req) {
         this();
         this.req = req;
      }

      public CloseSession_args(CloseSession_args other) {
         if (other.isSetReq()) {
            this.req = new TCloseSessionReq(other.req);
         }

      }

      public CloseSession_args deepCopy() {
         return new CloseSession_args(this);
      }

      public void clear() {
         this.req = null;
      }

      @Nullable
      public TCloseSessionReq getReq() {
         return this.req;
      }

      public void setReq(@Nullable TCloseSessionReq req) {
         this.req = req;
      }

      public void unsetReq() {
         this.req = null;
      }

      public boolean isSetReq() {
         return this.req != null;
      }

      public void setReqIsSet(boolean value) {
         if (!value) {
            this.req = null;
         }

      }

      public void setFieldValue(_Fields field, @Nullable Object value) {
         switch (field) {
            case REQ:
               if (value == null) {
                  this.unsetReq();
               } else {
                  this.setReq((TCloseSessionReq)value);
               }
            default:
         }
      }

      @Nullable
      public Object getFieldValue(_Fields field) {
         switch (field) {
            case REQ:
               return this.getReq();
            default:
               throw new IllegalStateException();
         }
      }

      public boolean isSet(_Fields field) {
         if (field == null) {
            throw new IllegalArgumentException();
         } else {
            switch (field) {
               case REQ:
                  return this.isSetReq();
               default:
                  throw new IllegalStateException();
            }
         }
      }

      public boolean equals(Object that) {
         return that instanceof CloseSession_args ? this.equals((CloseSession_args)that) : false;
      }

      public boolean equals(CloseSession_args that) {
         if (that == null) {
            return false;
         } else if (this == that) {
            return true;
         } else {
            boolean this_present_req = this.isSetReq();
            boolean that_present_req = that.isSetReq();
            if (this_present_req || that_present_req) {
               if (!this_present_req || !that_present_req) {
                  return false;
               }

               if (!this.req.equals(that.req)) {
                  return false;
               }
            }

            return true;
         }
      }

      public int hashCode() {
         int hashCode = 1;
         hashCode = hashCode * 8191 + (this.isSetReq() ? 131071 : 524287);
         if (this.isSetReq()) {
            hashCode = hashCode * 8191 + this.req.hashCode();
         }

         return hashCode;
      }

      public int compareTo(CloseSession_args other) {
         if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
         } else {
            int lastComparison = 0;
            lastComparison = Boolean.compare(this.isSetReq(), other.isSetReq());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetReq()) {
                  lastComparison = TBaseHelper.compareTo(this.req, other.req);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               return 0;
            }
         }
      }

      @Nullable
      public _Fields fieldForId(int fieldId) {
         return TCLIService.CloseSession_args._Fields.findByThriftId(fieldId);
      }

      public void read(TProtocol iprot) throws TException {
         scheme(iprot).read(iprot, this);
      }

      public void write(TProtocol oprot) throws TException {
         scheme(oprot).write(oprot, this);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("CloseSession_args(");
         boolean first = true;
         sb.append("req:");
         if (this.req == null) {
            sb.append("null");
         } else {
            sb.append(this.req);
         }

         first = false;
         sb.append(")");
         return sb.toString();
      }

      public void validate() throws TException {
         if (this.req != null) {
            this.req.validate();
         }

      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         try {
            this.write(new TCompactProtocol(new TIOStreamTransport(out)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         try {
            this.read(new TCompactProtocol(new TIOStreamTransport(in)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private static IScheme scheme(TProtocol proto) {
         return (StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
      }

      static {
         Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
         tmpMap.put(TCLIService.CloseSession_args._Fields.REQ, new FieldMetaData("req", (byte)3, new StructMetaData((byte)12, TCloseSessionReq.class)));
         metaDataMap = Collections.unmodifiableMap(tmpMap);
         FieldMetaData.addStructMetaDataMap(CloseSession_args.class, metaDataMap);
      }

      public static enum _Fields implements TFieldIdEnum {
         REQ((short)1, "req");

         private static final Map byName = new HashMap();
         private final short _thriftId;
         private final String _fieldName;

         @Nullable
         public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
               case 1:
                  return REQ;
               default:
                  return null;
            }
         }

         public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) {
               throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            } else {
               return fields;
            }
         }

         @Nullable
         public static _Fields findByName(String name) {
            return (_Fields)byName.get(name);
         }

         private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
         }

         public short getThriftFieldId() {
            return this._thriftId;
         }

         public String getFieldName() {
            return this._fieldName;
         }

         static {
            for(_Fields field : EnumSet.allOf(_Fields.class)) {
               byName.put(field.getFieldName(), field);
            }

         }
      }

      private static class CloseSession_argsStandardSchemeFactory implements SchemeFactory {
         private CloseSession_argsStandardSchemeFactory() {
         }

         public CloseSession_argsStandardScheme getScheme() {
            return new CloseSession_argsStandardScheme();
         }
      }

      private static class CloseSession_argsStandardScheme extends StandardScheme {
         private CloseSession_argsStandardScheme() {
         }

         public void read(TProtocol iprot, CloseSession_args struct) throws TException {
            iprot.readStructBegin();

            while(true) {
               TField schemeField = iprot.readFieldBegin();
               if (schemeField.type == 0) {
                  iprot.readStructEnd();
                  struct.validate();
                  return;
               }

               switch (schemeField.id) {
                  case 1:
                     if (schemeField.type == 12) {
                        struct.req = new TCloseSessionReq();
                        struct.req.read(iprot);
                        struct.setReqIsSet(true);
                     } else {
                        TProtocolUtil.skip(iprot, schemeField.type);
                     }
                     break;
                  default:
                     TProtocolUtil.skip(iprot, schemeField.type);
               }

               iprot.readFieldEnd();
            }
         }

         public void write(TProtocol oprot, CloseSession_args struct) throws TException {
            struct.validate();
            oprot.writeStructBegin(TCLIService.CloseSession_args.STRUCT_DESC);
            if (struct.req != null) {
               oprot.writeFieldBegin(TCLIService.CloseSession_args.REQ_FIELD_DESC);
               struct.req.write(oprot);
               oprot.writeFieldEnd();
            }

            oprot.writeFieldStop();
            oprot.writeStructEnd();
         }
      }

      private static class CloseSession_argsTupleSchemeFactory implements SchemeFactory {
         private CloseSession_argsTupleSchemeFactory() {
         }

         public CloseSession_argsTupleScheme getScheme() {
            return new CloseSession_argsTupleScheme();
         }
      }

      private static class CloseSession_argsTupleScheme extends TupleScheme {
         private CloseSession_argsTupleScheme() {
         }

         public void write(TProtocol prot, CloseSession_args struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol)prot;
            BitSet optionals = new BitSet();
            if (struct.isSetReq()) {
               optionals.set(0);
            }

            oprot.writeBitSet(optionals, 1);
            if (struct.isSetReq()) {
               struct.req.write(oprot);
            }

         }

         public void read(TProtocol prot, CloseSession_args struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol)prot;
            BitSet incoming = iprot.readBitSet(1);
            if (incoming.get(0)) {
               struct.req = new TCloseSessionReq();
               struct.req.read(iprot);
               struct.setReqIsSet(true);
            }

         }
      }
   }

   @Public
   @Stable
   public static class CloseSession_result implements TBase, Serializable, Cloneable, Comparable {
      private static final TStruct STRUCT_DESC = new TStruct("CloseSession_result");
      private static final TField SUCCESS_FIELD_DESC = new TField("success", (byte)12, (short)0);
      private static final SchemeFactory STANDARD_SCHEME_FACTORY = new CloseSession_resultStandardSchemeFactory();
      private static final SchemeFactory TUPLE_SCHEME_FACTORY = new CloseSession_resultTupleSchemeFactory();
      @Nullable
      private TCloseSessionResp success;
      public static final Map metaDataMap;

      public CloseSession_result() {
      }

      public CloseSession_result(TCloseSessionResp success) {
         this();
         this.success = success;
      }

      public CloseSession_result(CloseSession_result other) {
         if (other.isSetSuccess()) {
            this.success = new TCloseSessionResp(other.success);
         }

      }

      public CloseSession_result deepCopy() {
         return new CloseSession_result(this);
      }

      public void clear() {
         this.success = null;
      }

      @Nullable
      public TCloseSessionResp getSuccess() {
         return this.success;
      }

      public void setSuccess(@Nullable TCloseSessionResp success) {
         this.success = success;
      }

      public void unsetSuccess() {
         this.success = null;
      }

      public boolean isSetSuccess() {
         return this.success != null;
      }

      public void setSuccessIsSet(boolean value) {
         if (!value) {
            this.success = null;
         }

      }

      public void setFieldValue(_Fields field, @Nullable Object value) {
         switch (field) {
            case SUCCESS:
               if (value == null) {
                  this.unsetSuccess();
               } else {
                  this.setSuccess((TCloseSessionResp)value);
               }
            default:
         }
      }

      @Nullable
      public Object getFieldValue(_Fields field) {
         switch (field) {
            case SUCCESS:
               return this.getSuccess();
            default:
               throw new IllegalStateException();
         }
      }

      public boolean isSet(_Fields field) {
         if (field == null) {
            throw new IllegalArgumentException();
         } else {
            switch (field) {
               case SUCCESS:
                  return this.isSetSuccess();
               default:
                  throw new IllegalStateException();
            }
         }
      }

      public boolean equals(Object that) {
         return that instanceof CloseSession_result ? this.equals((CloseSession_result)that) : false;
      }

      public boolean equals(CloseSession_result that) {
         if (that == null) {
            return false;
         } else if (this == that) {
            return true;
         } else {
            boolean this_present_success = this.isSetSuccess();
            boolean that_present_success = that.isSetSuccess();
            if (this_present_success || that_present_success) {
               if (!this_present_success || !that_present_success) {
                  return false;
               }

               if (!this.success.equals(that.success)) {
                  return false;
               }
            }

            return true;
         }
      }

      public int hashCode() {
         int hashCode = 1;
         hashCode = hashCode * 8191 + (this.isSetSuccess() ? 131071 : 524287);
         if (this.isSetSuccess()) {
            hashCode = hashCode * 8191 + this.success.hashCode();
         }

         return hashCode;
      }

      public int compareTo(CloseSession_result other) {
         if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
         } else {
            int lastComparison = 0;
            lastComparison = Boolean.compare(this.isSetSuccess(), other.isSetSuccess());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetSuccess()) {
                  lastComparison = TBaseHelper.compareTo(this.success, other.success);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               return 0;
            }
         }
      }

      @Nullable
      public _Fields fieldForId(int fieldId) {
         return TCLIService.CloseSession_result._Fields.findByThriftId(fieldId);
      }

      public void read(TProtocol iprot) throws TException {
         scheme(iprot).read(iprot, this);
      }

      public void write(TProtocol oprot) throws TException {
         scheme(oprot).write(oprot, this);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("CloseSession_result(");
         boolean first = true;
         sb.append("success:");
         if (this.success == null) {
            sb.append("null");
         } else {
            sb.append(this.success);
         }

         first = false;
         sb.append(")");
         return sb.toString();
      }

      public void validate() throws TException {
         if (this.success != null) {
            this.success.validate();
         }

      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         try {
            this.write(new TCompactProtocol(new TIOStreamTransport(out)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         try {
            this.read(new TCompactProtocol(new TIOStreamTransport(in)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private static IScheme scheme(TProtocol proto) {
         return (StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
      }

      static {
         Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
         tmpMap.put(TCLIService.CloseSession_result._Fields.SUCCESS, new FieldMetaData("success", (byte)3, new StructMetaData((byte)12, TCloseSessionResp.class)));
         metaDataMap = Collections.unmodifiableMap(tmpMap);
         FieldMetaData.addStructMetaDataMap(CloseSession_result.class, metaDataMap);
      }

      public static enum _Fields implements TFieldIdEnum {
         SUCCESS((short)0, "success");

         private static final Map byName = new HashMap();
         private final short _thriftId;
         private final String _fieldName;

         @Nullable
         public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
               case 0:
                  return SUCCESS;
               default:
                  return null;
            }
         }

         public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) {
               throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            } else {
               return fields;
            }
         }

         @Nullable
         public static _Fields findByName(String name) {
            return (_Fields)byName.get(name);
         }

         private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
         }

         public short getThriftFieldId() {
            return this._thriftId;
         }

         public String getFieldName() {
            return this._fieldName;
         }

         static {
            for(_Fields field : EnumSet.allOf(_Fields.class)) {
               byName.put(field.getFieldName(), field);
            }

         }
      }

      private static class CloseSession_resultStandardSchemeFactory implements SchemeFactory {
         private CloseSession_resultStandardSchemeFactory() {
         }

         public CloseSession_resultStandardScheme getScheme() {
            return new CloseSession_resultStandardScheme();
         }
      }

      private static class CloseSession_resultStandardScheme extends StandardScheme {
         private CloseSession_resultStandardScheme() {
         }

         public void read(TProtocol iprot, CloseSession_result struct) throws TException {
            iprot.readStructBegin();

            while(true) {
               TField schemeField = iprot.readFieldBegin();
               if (schemeField.type == 0) {
                  iprot.readStructEnd();
                  struct.validate();
                  return;
               }

               switch (schemeField.id) {
                  case 0:
                     if (schemeField.type == 12) {
                        struct.success = new TCloseSessionResp();
                        struct.success.read(iprot);
                        struct.setSuccessIsSet(true);
                     } else {
                        TProtocolUtil.skip(iprot, schemeField.type);
                     }
                     break;
                  default:
                     TProtocolUtil.skip(iprot, schemeField.type);
               }

               iprot.readFieldEnd();
            }
         }

         public void write(TProtocol oprot, CloseSession_result struct) throws TException {
            struct.validate();
            oprot.writeStructBegin(TCLIService.CloseSession_result.STRUCT_DESC);
            if (struct.success != null) {
               oprot.writeFieldBegin(TCLIService.CloseSession_result.SUCCESS_FIELD_DESC);
               struct.success.write(oprot);
               oprot.writeFieldEnd();
            }

            oprot.writeFieldStop();
            oprot.writeStructEnd();
         }
      }

      private static class CloseSession_resultTupleSchemeFactory implements SchemeFactory {
         private CloseSession_resultTupleSchemeFactory() {
         }

         public CloseSession_resultTupleScheme getScheme() {
            return new CloseSession_resultTupleScheme();
         }
      }

      private static class CloseSession_resultTupleScheme extends TupleScheme {
         private CloseSession_resultTupleScheme() {
         }

         public void write(TProtocol prot, CloseSession_result struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol)prot;
            BitSet optionals = new BitSet();
            if (struct.isSetSuccess()) {
               optionals.set(0);
            }

            oprot.writeBitSet(optionals, 1);
            if (struct.isSetSuccess()) {
               struct.success.write(oprot);
            }

         }

         public void read(TProtocol prot, CloseSession_result struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol)prot;
            BitSet incoming = iprot.readBitSet(1);
            if (incoming.get(0)) {
               struct.success = new TCloseSessionResp();
               struct.success.read(iprot);
               struct.setSuccessIsSet(true);
            }

         }
      }
   }

   @Public
   @Stable
   public static class GetInfo_args implements TBase, Serializable, Cloneable, Comparable {
      private static final TStruct STRUCT_DESC = new TStruct("GetInfo_args");
      private static final TField REQ_FIELD_DESC = new TField("req", (byte)12, (short)1);
      private static final SchemeFactory STANDARD_SCHEME_FACTORY = new GetInfo_argsStandardSchemeFactory();
      private static final SchemeFactory TUPLE_SCHEME_FACTORY = new GetInfo_argsTupleSchemeFactory();
      @Nullable
      private TGetInfoReq req;
      public static final Map metaDataMap;

      public GetInfo_args() {
      }

      public GetInfo_args(TGetInfoReq req) {
         this();
         this.req = req;
      }

      public GetInfo_args(GetInfo_args other) {
         if (other.isSetReq()) {
            this.req = new TGetInfoReq(other.req);
         }

      }

      public GetInfo_args deepCopy() {
         return new GetInfo_args(this);
      }

      public void clear() {
         this.req = null;
      }

      @Nullable
      public TGetInfoReq getReq() {
         return this.req;
      }

      public void setReq(@Nullable TGetInfoReq req) {
         this.req = req;
      }

      public void unsetReq() {
         this.req = null;
      }

      public boolean isSetReq() {
         return this.req != null;
      }

      public void setReqIsSet(boolean value) {
         if (!value) {
            this.req = null;
         }

      }

      public void setFieldValue(_Fields field, @Nullable Object value) {
         switch (field) {
            case REQ:
               if (value == null) {
                  this.unsetReq();
               } else {
                  this.setReq((TGetInfoReq)value);
               }
            default:
         }
      }

      @Nullable
      public Object getFieldValue(_Fields field) {
         switch (field) {
            case REQ:
               return this.getReq();
            default:
               throw new IllegalStateException();
         }
      }

      public boolean isSet(_Fields field) {
         if (field == null) {
            throw new IllegalArgumentException();
         } else {
            switch (field) {
               case REQ:
                  return this.isSetReq();
               default:
                  throw new IllegalStateException();
            }
         }
      }

      public boolean equals(Object that) {
         return that instanceof GetInfo_args ? this.equals((GetInfo_args)that) : false;
      }

      public boolean equals(GetInfo_args that) {
         if (that == null) {
            return false;
         } else if (this == that) {
            return true;
         } else {
            boolean this_present_req = this.isSetReq();
            boolean that_present_req = that.isSetReq();
            if (this_present_req || that_present_req) {
               if (!this_present_req || !that_present_req) {
                  return false;
               }

               if (!this.req.equals(that.req)) {
                  return false;
               }
            }

            return true;
         }
      }

      public int hashCode() {
         int hashCode = 1;
         hashCode = hashCode * 8191 + (this.isSetReq() ? 131071 : 524287);
         if (this.isSetReq()) {
            hashCode = hashCode * 8191 + this.req.hashCode();
         }

         return hashCode;
      }

      public int compareTo(GetInfo_args other) {
         if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
         } else {
            int lastComparison = 0;
            lastComparison = Boolean.compare(this.isSetReq(), other.isSetReq());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetReq()) {
                  lastComparison = TBaseHelper.compareTo(this.req, other.req);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               return 0;
            }
         }
      }

      @Nullable
      public _Fields fieldForId(int fieldId) {
         return TCLIService.GetInfo_args._Fields.findByThriftId(fieldId);
      }

      public void read(TProtocol iprot) throws TException {
         scheme(iprot).read(iprot, this);
      }

      public void write(TProtocol oprot) throws TException {
         scheme(oprot).write(oprot, this);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("GetInfo_args(");
         boolean first = true;
         sb.append("req:");
         if (this.req == null) {
            sb.append("null");
         } else {
            sb.append(this.req);
         }

         first = false;
         sb.append(")");
         return sb.toString();
      }

      public void validate() throws TException {
         if (this.req != null) {
            this.req.validate();
         }

      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         try {
            this.write(new TCompactProtocol(new TIOStreamTransport(out)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         try {
            this.read(new TCompactProtocol(new TIOStreamTransport(in)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private static IScheme scheme(TProtocol proto) {
         return (StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
      }

      static {
         Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
         tmpMap.put(TCLIService.GetInfo_args._Fields.REQ, new FieldMetaData("req", (byte)3, new StructMetaData((byte)12, TGetInfoReq.class)));
         metaDataMap = Collections.unmodifiableMap(tmpMap);
         FieldMetaData.addStructMetaDataMap(GetInfo_args.class, metaDataMap);
      }

      public static enum _Fields implements TFieldIdEnum {
         REQ((short)1, "req");

         private static final Map byName = new HashMap();
         private final short _thriftId;
         private final String _fieldName;

         @Nullable
         public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
               case 1:
                  return REQ;
               default:
                  return null;
            }
         }

         public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) {
               throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            } else {
               return fields;
            }
         }

         @Nullable
         public static _Fields findByName(String name) {
            return (_Fields)byName.get(name);
         }

         private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
         }

         public short getThriftFieldId() {
            return this._thriftId;
         }

         public String getFieldName() {
            return this._fieldName;
         }

         static {
            for(_Fields field : EnumSet.allOf(_Fields.class)) {
               byName.put(field.getFieldName(), field);
            }

         }
      }

      private static class GetInfo_argsStandardSchemeFactory implements SchemeFactory {
         private GetInfo_argsStandardSchemeFactory() {
         }

         public GetInfo_argsStandardScheme getScheme() {
            return new GetInfo_argsStandardScheme();
         }
      }

      private static class GetInfo_argsStandardScheme extends StandardScheme {
         private GetInfo_argsStandardScheme() {
         }

         public void read(TProtocol iprot, GetInfo_args struct) throws TException {
            iprot.readStructBegin();

            while(true) {
               TField schemeField = iprot.readFieldBegin();
               if (schemeField.type == 0) {
                  iprot.readStructEnd();
                  struct.validate();
                  return;
               }

               switch (schemeField.id) {
                  case 1:
                     if (schemeField.type == 12) {
                        struct.req = new TGetInfoReq();
                        struct.req.read(iprot);
                        struct.setReqIsSet(true);
                     } else {
                        TProtocolUtil.skip(iprot, schemeField.type);
                     }
                     break;
                  default:
                     TProtocolUtil.skip(iprot, schemeField.type);
               }

               iprot.readFieldEnd();
            }
         }

         public void write(TProtocol oprot, GetInfo_args struct) throws TException {
            struct.validate();
            oprot.writeStructBegin(TCLIService.GetInfo_args.STRUCT_DESC);
            if (struct.req != null) {
               oprot.writeFieldBegin(TCLIService.GetInfo_args.REQ_FIELD_DESC);
               struct.req.write(oprot);
               oprot.writeFieldEnd();
            }

            oprot.writeFieldStop();
            oprot.writeStructEnd();
         }
      }

      private static class GetInfo_argsTupleSchemeFactory implements SchemeFactory {
         private GetInfo_argsTupleSchemeFactory() {
         }

         public GetInfo_argsTupleScheme getScheme() {
            return new GetInfo_argsTupleScheme();
         }
      }

      private static class GetInfo_argsTupleScheme extends TupleScheme {
         private GetInfo_argsTupleScheme() {
         }

         public void write(TProtocol prot, GetInfo_args struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol)prot;
            BitSet optionals = new BitSet();
            if (struct.isSetReq()) {
               optionals.set(0);
            }

            oprot.writeBitSet(optionals, 1);
            if (struct.isSetReq()) {
               struct.req.write(oprot);
            }

         }

         public void read(TProtocol prot, GetInfo_args struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol)prot;
            BitSet incoming = iprot.readBitSet(1);
            if (incoming.get(0)) {
               struct.req = new TGetInfoReq();
               struct.req.read(iprot);
               struct.setReqIsSet(true);
            }

         }
      }
   }

   @Public
   @Stable
   public static class GetInfo_result implements TBase, Serializable, Cloneable, Comparable {
      private static final TStruct STRUCT_DESC = new TStruct("GetInfo_result");
      private static final TField SUCCESS_FIELD_DESC = new TField("success", (byte)12, (short)0);
      private static final SchemeFactory STANDARD_SCHEME_FACTORY = new GetInfo_resultStandardSchemeFactory();
      private static final SchemeFactory TUPLE_SCHEME_FACTORY = new GetInfo_resultTupleSchemeFactory();
      @Nullable
      private TGetInfoResp success;
      public static final Map metaDataMap;

      public GetInfo_result() {
      }

      public GetInfo_result(TGetInfoResp success) {
         this();
         this.success = success;
      }

      public GetInfo_result(GetInfo_result other) {
         if (other.isSetSuccess()) {
            this.success = new TGetInfoResp(other.success);
         }

      }

      public GetInfo_result deepCopy() {
         return new GetInfo_result(this);
      }

      public void clear() {
         this.success = null;
      }

      @Nullable
      public TGetInfoResp getSuccess() {
         return this.success;
      }

      public void setSuccess(@Nullable TGetInfoResp success) {
         this.success = success;
      }

      public void unsetSuccess() {
         this.success = null;
      }

      public boolean isSetSuccess() {
         return this.success != null;
      }

      public void setSuccessIsSet(boolean value) {
         if (!value) {
            this.success = null;
         }

      }

      public void setFieldValue(_Fields field, @Nullable Object value) {
         switch (field) {
            case SUCCESS:
               if (value == null) {
                  this.unsetSuccess();
               } else {
                  this.setSuccess((TGetInfoResp)value);
               }
            default:
         }
      }

      @Nullable
      public Object getFieldValue(_Fields field) {
         switch (field) {
            case SUCCESS:
               return this.getSuccess();
            default:
               throw new IllegalStateException();
         }
      }

      public boolean isSet(_Fields field) {
         if (field == null) {
            throw new IllegalArgumentException();
         } else {
            switch (field) {
               case SUCCESS:
                  return this.isSetSuccess();
               default:
                  throw new IllegalStateException();
            }
         }
      }

      public boolean equals(Object that) {
         return that instanceof GetInfo_result ? this.equals((GetInfo_result)that) : false;
      }

      public boolean equals(GetInfo_result that) {
         if (that == null) {
            return false;
         } else if (this == that) {
            return true;
         } else {
            boolean this_present_success = this.isSetSuccess();
            boolean that_present_success = that.isSetSuccess();
            if (this_present_success || that_present_success) {
               if (!this_present_success || !that_present_success) {
                  return false;
               }

               if (!this.success.equals(that.success)) {
                  return false;
               }
            }

            return true;
         }
      }

      public int hashCode() {
         int hashCode = 1;
         hashCode = hashCode * 8191 + (this.isSetSuccess() ? 131071 : 524287);
         if (this.isSetSuccess()) {
            hashCode = hashCode * 8191 + this.success.hashCode();
         }

         return hashCode;
      }

      public int compareTo(GetInfo_result other) {
         if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
         } else {
            int lastComparison = 0;
            lastComparison = Boolean.compare(this.isSetSuccess(), other.isSetSuccess());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetSuccess()) {
                  lastComparison = TBaseHelper.compareTo(this.success, other.success);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               return 0;
            }
         }
      }

      @Nullable
      public _Fields fieldForId(int fieldId) {
         return TCLIService.GetInfo_result._Fields.findByThriftId(fieldId);
      }

      public void read(TProtocol iprot) throws TException {
         scheme(iprot).read(iprot, this);
      }

      public void write(TProtocol oprot) throws TException {
         scheme(oprot).write(oprot, this);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("GetInfo_result(");
         boolean first = true;
         sb.append("success:");
         if (this.success == null) {
            sb.append("null");
         } else {
            sb.append(this.success);
         }

         first = false;
         sb.append(")");
         return sb.toString();
      }

      public void validate() throws TException {
         if (this.success != null) {
            this.success.validate();
         }

      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         try {
            this.write(new TCompactProtocol(new TIOStreamTransport(out)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         try {
            this.read(new TCompactProtocol(new TIOStreamTransport(in)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private static IScheme scheme(TProtocol proto) {
         return (StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
      }

      static {
         Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
         tmpMap.put(TCLIService.GetInfo_result._Fields.SUCCESS, new FieldMetaData("success", (byte)3, new StructMetaData((byte)12, TGetInfoResp.class)));
         metaDataMap = Collections.unmodifiableMap(tmpMap);
         FieldMetaData.addStructMetaDataMap(GetInfo_result.class, metaDataMap);
      }

      public static enum _Fields implements TFieldIdEnum {
         SUCCESS((short)0, "success");

         private static final Map byName = new HashMap();
         private final short _thriftId;
         private final String _fieldName;

         @Nullable
         public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
               case 0:
                  return SUCCESS;
               default:
                  return null;
            }
         }

         public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) {
               throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            } else {
               return fields;
            }
         }

         @Nullable
         public static _Fields findByName(String name) {
            return (_Fields)byName.get(name);
         }

         private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
         }

         public short getThriftFieldId() {
            return this._thriftId;
         }

         public String getFieldName() {
            return this._fieldName;
         }

         static {
            for(_Fields field : EnumSet.allOf(_Fields.class)) {
               byName.put(field.getFieldName(), field);
            }

         }
      }

      private static class GetInfo_resultStandardSchemeFactory implements SchemeFactory {
         private GetInfo_resultStandardSchemeFactory() {
         }

         public GetInfo_resultStandardScheme getScheme() {
            return new GetInfo_resultStandardScheme();
         }
      }

      private static class GetInfo_resultStandardScheme extends StandardScheme {
         private GetInfo_resultStandardScheme() {
         }

         public void read(TProtocol iprot, GetInfo_result struct) throws TException {
            iprot.readStructBegin();

            while(true) {
               TField schemeField = iprot.readFieldBegin();
               if (schemeField.type == 0) {
                  iprot.readStructEnd();
                  struct.validate();
                  return;
               }

               switch (schemeField.id) {
                  case 0:
                     if (schemeField.type == 12) {
                        struct.success = new TGetInfoResp();
                        struct.success.read(iprot);
                        struct.setSuccessIsSet(true);
                     } else {
                        TProtocolUtil.skip(iprot, schemeField.type);
                     }
                     break;
                  default:
                     TProtocolUtil.skip(iprot, schemeField.type);
               }

               iprot.readFieldEnd();
            }
         }

         public void write(TProtocol oprot, GetInfo_result struct) throws TException {
            struct.validate();
            oprot.writeStructBegin(TCLIService.GetInfo_result.STRUCT_DESC);
            if (struct.success != null) {
               oprot.writeFieldBegin(TCLIService.GetInfo_result.SUCCESS_FIELD_DESC);
               struct.success.write(oprot);
               oprot.writeFieldEnd();
            }

            oprot.writeFieldStop();
            oprot.writeStructEnd();
         }
      }

      private static class GetInfo_resultTupleSchemeFactory implements SchemeFactory {
         private GetInfo_resultTupleSchemeFactory() {
         }

         public GetInfo_resultTupleScheme getScheme() {
            return new GetInfo_resultTupleScheme();
         }
      }

      private static class GetInfo_resultTupleScheme extends TupleScheme {
         private GetInfo_resultTupleScheme() {
         }

         public void write(TProtocol prot, GetInfo_result struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol)prot;
            BitSet optionals = new BitSet();
            if (struct.isSetSuccess()) {
               optionals.set(0);
            }

            oprot.writeBitSet(optionals, 1);
            if (struct.isSetSuccess()) {
               struct.success.write(oprot);
            }

         }

         public void read(TProtocol prot, GetInfo_result struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol)prot;
            BitSet incoming = iprot.readBitSet(1);
            if (incoming.get(0)) {
               struct.success = new TGetInfoResp();
               struct.success.read(iprot);
               struct.setSuccessIsSet(true);
            }

         }
      }
   }

   @Public
   @Stable
   public static class ExecuteStatement_args implements TBase, Serializable, Cloneable, Comparable {
      private static final TStruct STRUCT_DESC = new TStruct("ExecuteStatement_args");
      private static final TField REQ_FIELD_DESC = new TField("req", (byte)12, (short)1);
      private static final SchemeFactory STANDARD_SCHEME_FACTORY = new ExecuteStatement_argsStandardSchemeFactory();
      private static final SchemeFactory TUPLE_SCHEME_FACTORY = new ExecuteStatement_argsTupleSchemeFactory();
      @Nullable
      private TExecuteStatementReq req;
      public static final Map metaDataMap;

      public ExecuteStatement_args() {
      }

      public ExecuteStatement_args(TExecuteStatementReq req) {
         this();
         this.req = req;
      }

      public ExecuteStatement_args(ExecuteStatement_args other) {
         if (other.isSetReq()) {
            this.req = new TExecuteStatementReq(other.req);
         }

      }

      public ExecuteStatement_args deepCopy() {
         return new ExecuteStatement_args(this);
      }

      public void clear() {
         this.req = null;
      }

      @Nullable
      public TExecuteStatementReq getReq() {
         return this.req;
      }

      public void setReq(@Nullable TExecuteStatementReq req) {
         this.req = req;
      }

      public void unsetReq() {
         this.req = null;
      }

      public boolean isSetReq() {
         return this.req != null;
      }

      public void setReqIsSet(boolean value) {
         if (!value) {
            this.req = null;
         }

      }

      public void setFieldValue(_Fields field, @Nullable Object value) {
         switch (field) {
            case REQ:
               if (value == null) {
                  this.unsetReq();
               } else {
                  this.setReq((TExecuteStatementReq)value);
               }
            default:
         }
      }

      @Nullable
      public Object getFieldValue(_Fields field) {
         switch (field) {
            case REQ:
               return this.getReq();
            default:
               throw new IllegalStateException();
         }
      }

      public boolean isSet(_Fields field) {
         if (field == null) {
            throw new IllegalArgumentException();
         } else {
            switch (field) {
               case REQ:
                  return this.isSetReq();
               default:
                  throw new IllegalStateException();
            }
         }
      }

      public boolean equals(Object that) {
         return that instanceof ExecuteStatement_args ? this.equals((ExecuteStatement_args)that) : false;
      }

      public boolean equals(ExecuteStatement_args that) {
         if (that == null) {
            return false;
         } else if (this == that) {
            return true;
         } else {
            boolean this_present_req = this.isSetReq();
            boolean that_present_req = that.isSetReq();
            if (this_present_req || that_present_req) {
               if (!this_present_req || !that_present_req) {
                  return false;
               }

               if (!this.req.equals(that.req)) {
                  return false;
               }
            }

            return true;
         }
      }

      public int hashCode() {
         int hashCode = 1;
         hashCode = hashCode * 8191 + (this.isSetReq() ? 131071 : 524287);
         if (this.isSetReq()) {
            hashCode = hashCode * 8191 + this.req.hashCode();
         }

         return hashCode;
      }

      public int compareTo(ExecuteStatement_args other) {
         if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
         } else {
            int lastComparison = 0;
            lastComparison = Boolean.compare(this.isSetReq(), other.isSetReq());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetReq()) {
                  lastComparison = TBaseHelper.compareTo(this.req, other.req);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               return 0;
            }
         }
      }

      @Nullable
      public _Fields fieldForId(int fieldId) {
         return TCLIService.ExecuteStatement_args._Fields.findByThriftId(fieldId);
      }

      public void read(TProtocol iprot) throws TException {
         scheme(iprot).read(iprot, this);
      }

      public void write(TProtocol oprot) throws TException {
         scheme(oprot).write(oprot, this);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("ExecuteStatement_args(");
         boolean first = true;
         sb.append("req:");
         if (this.req == null) {
            sb.append("null");
         } else {
            sb.append(this.req);
         }

         first = false;
         sb.append(")");
         return sb.toString();
      }

      public void validate() throws TException {
         if (this.req != null) {
            this.req.validate();
         }

      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         try {
            this.write(new TCompactProtocol(new TIOStreamTransport(out)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         try {
            this.read(new TCompactProtocol(new TIOStreamTransport(in)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private static IScheme scheme(TProtocol proto) {
         return (StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
      }

      static {
         Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
         tmpMap.put(TCLIService.ExecuteStatement_args._Fields.REQ, new FieldMetaData("req", (byte)3, new StructMetaData((byte)12, TExecuteStatementReq.class)));
         metaDataMap = Collections.unmodifiableMap(tmpMap);
         FieldMetaData.addStructMetaDataMap(ExecuteStatement_args.class, metaDataMap);
      }

      public static enum _Fields implements TFieldIdEnum {
         REQ((short)1, "req");

         private static final Map byName = new HashMap();
         private final short _thriftId;
         private final String _fieldName;

         @Nullable
         public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
               case 1:
                  return REQ;
               default:
                  return null;
            }
         }

         public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) {
               throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            } else {
               return fields;
            }
         }

         @Nullable
         public static _Fields findByName(String name) {
            return (_Fields)byName.get(name);
         }

         private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
         }

         public short getThriftFieldId() {
            return this._thriftId;
         }

         public String getFieldName() {
            return this._fieldName;
         }

         static {
            for(_Fields field : EnumSet.allOf(_Fields.class)) {
               byName.put(field.getFieldName(), field);
            }

         }
      }

      private static class ExecuteStatement_argsStandardSchemeFactory implements SchemeFactory {
         private ExecuteStatement_argsStandardSchemeFactory() {
         }

         public ExecuteStatement_argsStandardScheme getScheme() {
            return new ExecuteStatement_argsStandardScheme();
         }
      }

      private static class ExecuteStatement_argsStandardScheme extends StandardScheme {
         private ExecuteStatement_argsStandardScheme() {
         }

         public void read(TProtocol iprot, ExecuteStatement_args struct) throws TException {
            iprot.readStructBegin();

            while(true) {
               TField schemeField = iprot.readFieldBegin();
               if (schemeField.type == 0) {
                  iprot.readStructEnd();
                  struct.validate();
                  return;
               }

               switch (schemeField.id) {
                  case 1:
                     if (schemeField.type == 12) {
                        struct.req = new TExecuteStatementReq();
                        struct.req.read(iprot);
                        struct.setReqIsSet(true);
                     } else {
                        TProtocolUtil.skip(iprot, schemeField.type);
                     }
                     break;
                  default:
                     TProtocolUtil.skip(iprot, schemeField.type);
               }

               iprot.readFieldEnd();
            }
         }

         public void write(TProtocol oprot, ExecuteStatement_args struct) throws TException {
            struct.validate();
            oprot.writeStructBegin(TCLIService.ExecuteStatement_args.STRUCT_DESC);
            if (struct.req != null) {
               oprot.writeFieldBegin(TCLIService.ExecuteStatement_args.REQ_FIELD_DESC);
               struct.req.write(oprot);
               oprot.writeFieldEnd();
            }

            oprot.writeFieldStop();
            oprot.writeStructEnd();
         }
      }

      private static class ExecuteStatement_argsTupleSchemeFactory implements SchemeFactory {
         private ExecuteStatement_argsTupleSchemeFactory() {
         }

         public ExecuteStatement_argsTupleScheme getScheme() {
            return new ExecuteStatement_argsTupleScheme();
         }
      }

      private static class ExecuteStatement_argsTupleScheme extends TupleScheme {
         private ExecuteStatement_argsTupleScheme() {
         }

         public void write(TProtocol prot, ExecuteStatement_args struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol)prot;
            BitSet optionals = new BitSet();
            if (struct.isSetReq()) {
               optionals.set(0);
            }

            oprot.writeBitSet(optionals, 1);
            if (struct.isSetReq()) {
               struct.req.write(oprot);
            }

         }

         public void read(TProtocol prot, ExecuteStatement_args struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol)prot;
            BitSet incoming = iprot.readBitSet(1);
            if (incoming.get(0)) {
               struct.req = new TExecuteStatementReq();
               struct.req.read(iprot);
               struct.setReqIsSet(true);
            }

         }
      }
   }

   @Public
   @Stable
   public static class ExecuteStatement_result implements TBase, Serializable, Cloneable, Comparable {
      private static final TStruct STRUCT_DESC = new TStruct("ExecuteStatement_result");
      private static final TField SUCCESS_FIELD_DESC = new TField("success", (byte)12, (short)0);
      private static final SchemeFactory STANDARD_SCHEME_FACTORY = new ExecuteStatement_resultStandardSchemeFactory();
      private static final SchemeFactory TUPLE_SCHEME_FACTORY = new ExecuteStatement_resultTupleSchemeFactory();
      @Nullable
      private TExecuteStatementResp success;
      public static final Map metaDataMap;

      public ExecuteStatement_result() {
      }

      public ExecuteStatement_result(TExecuteStatementResp success) {
         this();
         this.success = success;
      }

      public ExecuteStatement_result(ExecuteStatement_result other) {
         if (other.isSetSuccess()) {
            this.success = new TExecuteStatementResp(other.success);
         }

      }

      public ExecuteStatement_result deepCopy() {
         return new ExecuteStatement_result(this);
      }

      public void clear() {
         this.success = null;
      }

      @Nullable
      public TExecuteStatementResp getSuccess() {
         return this.success;
      }

      public void setSuccess(@Nullable TExecuteStatementResp success) {
         this.success = success;
      }

      public void unsetSuccess() {
         this.success = null;
      }

      public boolean isSetSuccess() {
         return this.success != null;
      }

      public void setSuccessIsSet(boolean value) {
         if (!value) {
            this.success = null;
         }

      }

      public void setFieldValue(_Fields field, @Nullable Object value) {
         switch (field) {
            case SUCCESS:
               if (value == null) {
                  this.unsetSuccess();
               } else {
                  this.setSuccess((TExecuteStatementResp)value);
               }
            default:
         }
      }

      @Nullable
      public Object getFieldValue(_Fields field) {
         switch (field) {
            case SUCCESS:
               return this.getSuccess();
            default:
               throw new IllegalStateException();
         }
      }

      public boolean isSet(_Fields field) {
         if (field == null) {
            throw new IllegalArgumentException();
         } else {
            switch (field) {
               case SUCCESS:
                  return this.isSetSuccess();
               default:
                  throw new IllegalStateException();
            }
         }
      }

      public boolean equals(Object that) {
         return that instanceof ExecuteStatement_result ? this.equals((ExecuteStatement_result)that) : false;
      }

      public boolean equals(ExecuteStatement_result that) {
         if (that == null) {
            return false;
         } else if (this == that) {
            return true;
         } else {
            boolean this_present_success = this.isSetSuccess();
            boolean that_present_success = that.isSetSuccess();
            if (this_present_success || that_present_success) {
               if (!this_present_success || !that_present_success) {
                  return false;
               }

               if (!this.success.equals(that.success)) {
                  return false;
               }
            }

            return true;
         }
      }

      public int hashCode() {
         int hashCode = 1;
         hashCode = hashCode * 8191 + (this.isSetSuccess() ? 131071 : 524287);
         if (this.isSetSuccess()) {
            hashCode = hashCode * 8191 + this.success.hashCode();
         }

         return hashCode;
      }

      public int compareTo(ExecuteStatement_result other) {
         if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
         } else {
            int lastComparison = 0;
            lastComparison = Boolean.compare(this.isSetSuccess(), other.isSetSuccess());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetSuccess()) {
                  lastComparison = TBaseHelper.compareTo(this.success, other.success);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               return 0;
            }
         }
      }

      @Nullable
      public _Fields fieldForId(int fieldId) {
         return TCLIService.ExecuteStatement_result._Fields.findByThriftId(fieldId);
      }

      public void read(TProtocol iprot) throws TException {
         scheme(iprot).read(iprot, this);
      }

      public void write(TProtocol oprot) throws TException {
         scheme(oprot).write(oprot, this);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("ExecuteStatement_result(");
         boolean first = true;
         sb.append("success:");
         if (this.success == null) {
            sb.append("null");
         } else {
            sb.append(this.success);
         }

         first = false;
         sb.append(")");
         return sb.toString();
      }

      public void validate() throws TException {
         if (this.success != null) {
            this.success.validate();
         }

      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         try {
            this.write(new TCompactProtocol(new TIOStreamTransport(out)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         try {
            this.read(new TCompactProtocol(new TIOStreamTransport(in)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private static IScheme scheme(TProtocol proto) {
         return (StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
      }

      static {
         Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
         tmpMap.put(TCLIService.ExecuteStatement_result._Fields.SUCCESS, new FieldMetaData("success", (byte)3, new StructMetaData((byte)12, TExecuteStatementResp.class)));
         metaDataMap = Collections.unmodifiableMap(tmpMap);
         FieldMetaData.addStructMetaDataMap(ExecuteStatement_result.class, metaDataMap);
      }

      public static enum _Fields implements TFieldIdEnum {
         SUCCESS((short)0, "success");

         private static final Map byName = new HashMap();
         private final short _thriftId;
         private final String _fieldName;

         @Nullable
         public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
               case 0:
                  return SUCCESS;
               default:
                  return null;
            }
         }

         public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) {
               throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            } else {
               return fields;
            }
         }

         @Nullable
         public static _Fields findByName(String name) {
            return (_Fields)byName.get(name);
         }

         private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
         }

         public short getThriftFieldId() {
            return this._thriftId;
         }

         public String getFieldName() {
            return this._fieldName;
         }

         static {
            for(_Fields field : EnumSet.allOf(_Fields.class)) {
               byName.put(field.getFieldName(), field);
            }

         }
      }

      private static class ExecuteStatement_resultStandardSchemeFactory implements SchemeFactory {
         private ExecuteStatement_resultStandardSchemeFactory() {
         }

         public ExecuteStatement_resultStandardScheme getScheme() {
            return new ExecuteStatement_resultStandardScheme();
         }
      }

      private static class ExecuteStatement_resultStandardScheme extends StandardScheme {
         private ExecuteStatement_resultStandardScheme() {
         }

         public void read(TProtocol iprot, ExecuteStatement_result struct) throws TException {
            iprot.readStructBegin();

            while(true) {
               TField schemeField = iprot.readFieldBegin();
               if (schemeField.type == 0) {
                  iprot.readStructEnd();
                  struct.validate();
                  return;
               }

               switch (schemeField.id) {
                  case 0:
                     if (schemeField.type == 12) {
                        struct.success = new TExecuteStatementResp();
                        struct.success.read(iprot);
                        struct.setSuccessIsSet(true);
                     } else {
                        TProtocolUtil.skip(iprot, schemeField.type);
                     }
                     break;
                  default:
                     TProtocolUtil.skip(iprot, schemeField.type);
               }

               iprot.readFieldEnd();
            }
         }

         public void write(TProtocol oprot, ExecuteStatement_result struct) throws TException {
            struct.validate();
            oprot.writeStructBegin(TCLIService.ExecuteStatement_result.STRUCT_DESC);
            if (struct.success != null) {
               oprot.writeFieldBegin(TCLIService.ExecuteStatement_result.SUCCESS_FIELD_DESC);
               struct.success.write(oprot);
               oprot.writeFieldEnd();
            }

            oprot.writeFieldStop();
            oprot.writeStructEnd();
         }
      }

      private static class ExecuteStatement_resultTupleSchemeFactory implements SchemeFactory {
         private ExecuteStatement_resultTupleSchemeFactory() {
         }

         public ExecuteStatement_resultTupleScheme getScheme() {
            return new ExecuteStatement_resultTupleScheme();
         }
      }

      private static class ExecuteStatement_resultTupleScheme extends TupleScheme {
         private ExecuteStatement_resultTupleScheme() {
         }

         public void write(TProtocol prot, ExecuteStatement_result struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol)prot;
            BitSet optionals = new BitSet();
            if (struct.isSetSuccess()) {
               optionals.set(0);
            }

            oprot.writeBitSet(optionals, 1);
            if (struct.isSetSuccess()) {
               struct.success.write(oprot);
            }

         }

         public void read(TProtocol prot, ExecuteStatement_result struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol)prot;
            BitSet incoming = iprot.readBitSet(1);
            if (incoming.get(0)) {
               struct.success = new TExecuteStatementResp();
               struct.success.read(iprot);
               struct.setSuccessIsSet(true);
            }

         }
      }
   }

   @Public
   @Stable
   public static class GetTypeInfo_args implements TBase, Serializable, Cloneable, Comparable {
      private static final TStruct STRUCT_DESC = new TStruct("GetTypeInfo_args");
      private static final TField REQ_FIELD_DESC = new TField("req", (byte)12, (short)1);
      private static final SchemeFactory STANDARD_SCHEME_FACTORY = new GetTypeInfo_argsStandardSchemeFactory();
      private static final SchemeFactory TUPLE_SCHEME_FACTORY = new GetTypeInfo_argsTupleSchemeFactory();
      @Nullable
      private TGetTypeInfoReq req;
      public static final Map metaDataMap;

      public GetTypeInfo_args() {
      }

      public GetTypeInfo_args(TGetTypeInfoReq req) {
         this();
         this.req = req;
      }

      public GetTypeInfo_args(GetTypeInfo_args other) {
         if (other.isSetReq()) {
            this.req = new TGetTypeInfoReq(other.req);
         }

      }

      public GetTypeInfo_args deepCopy() {
         return new GetTypeInfo_args(this);
      }

      public void clear() {
         this.req = null;
      }

      @Nullable
      public TGetTypeInfoReq getReq() {
         return this.req;
      }

      public void setReq(@Nullable TGetTypeInfoReq req) {
         this.req = req;
      }

      public void unsetReq() {
         this.req = null;
      }

      public boolean isSetReq() {
         return this.req != null;
      }

      public void setReqIsSet(boolean value) {
         if (!value) {
            this.req = null;
         }

      }

      public void setFieldValue(_Fields field, @Nullable Object value) {
         switch (field) {
            case REQ:
               if (value == null) {
                  this.unsetReq();
               } else {
                  this.setReq((TGetTypeInfoReq)value);
               }
            default:
         }
      }

      @Nullable
      public Object getFieldValue(_Fields field) {
         switch (field) {
            case REQ:
               return this.getReq();
            default:
               throw new IllegalStateException();
         }
      }

      public boolean isSet(_Fields field) {
         if (field == null) {
            throw new IllegalArgumentException();
         } else {
            switch (field) {
               case REQ:
                  return this.isSetReq();
               default:
                  throw new IllegalStateException();
            }
         }
      }

      public boolean equals(Object that) {
         return that instanceof GetTypeInfo_args ? this.equals((GetTypeInfo_args)that) : false;
      }

      public boolean equals(GetTypeInfo_args that) {
         if (that == null) {
            return false;
         } else if (this == that) {
            return true;
         } else {
            boolean this_present_req = this.isSetReq();
            boolean that_present_req = that.isSetReq();
            if (this_present_req || that_present_req) {
               if (!this_present_req || !that_present_req) {
                  return false;
               }

               if (!this.req.equals(that.req)) {
                  return false;
               }
            }

            return true;
         }
      }

      public int hashCode() {
         int hashCode = 1;
         hashCode = hashCode * 8191 + (this.isSetReq() ? 131071 : 524287);
         if (this.isSetReq()) {
            hashCode = hashCode * 8191 + this.req.hashCode();
         }

         return hashCode;
      }

      public int compareTo(GetTypeInfo_args other) {
         if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
         } else {
            int lastComparison = 0;
            lastComparison = Boolean.compare(this.isSetReq(), other.isSetReq());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetReq()) {
                  lastComparison = TBaseHelper.compareTo(this.req, other.req);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               return 0;
            }
         }
      }

      @Nullable
      public _Fields fieldForId(int fieldId) {
         return TCLIService.GetTypeInfo_args._Fields.findByThriftId(fieldId);
      }

      public void read(TProtocol iprot) throws TException {
         scheme(iprot).read(iprot, this);
      }

      public void write(TProtocol oprot) throws TException {
         scheme(oprot).write(oprot, this);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("GetTypeInfo_args(");
         boolean first = true;
         sb.append("req:");
         if (this.req == null) {
            sb.append("null");
         } else {
            sb.append(this.req);
         }

         first = false;
         sb.append(")");
         return sb.toString();
      }

      public void validate() throws TException {
         if (this.req != null) {
            this.req.validate();
         }

      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         try {
            this.write(new TCompactProtocol(new TIOStreamTransport(out)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         try {
            this.read(new TCompactProtocol(new TIOStreamTransport(in)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private static IScheme scheme(TProtocol proto) {
         return (StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
      }

      static {
         Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
         tmpMap.put(TCLIService.GetTypeInfo_args._Fields.REQ, new FieldMetaData("req", (byte)3, new StructMetaData((byte)12, TGetTypeInfoReq.class)));
         metaDataMap = Collections.unmodifiableMap(tmpMap);
         FieldMetaData.addStructMetaDataMap(GetTypeInfo_args.class, metaDataMap);
      }

      public static enum _Fields implements TFieldIdEnum {
         REQ((short)1, "req");

         private static final Map byName = new HashMap();
         private final short _thriftId;
         private final String _fieldName;

         @Nullable
         public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
               case 1:
                  return REQ;
               default:
                  return null;
            }
         }

         public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) {
               throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            } else {
               return fields;
            }
         }

         @Nullable
         public static _Fields findByName(String name) {
            return (_Fields)byName.get(name);
         }

         private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
         }

         public short getThriftFieldId() {
            return this._thriftId;
         }

         public String getFieldName() {
            return this._fieldName;
         }

         static {
            for(_Fields field : EnumSet.allOf(_Fields.class)) {
               byName.put(field.getFieldName(), field);
            }

         }
      }

      private static class GetTypeInfo_argsStandardSchemeFactory implements SchemeFactory {
         private GetTypeInfo_argsStandardSchemeFactory() {
         }

         public GetTypeInfo_argsStandardScheme getScheme() {
            return new GetTypeInfo_argsStandardScheme();
         }
      }

      private static class GetTypeInfo_argsStandardScheme extends StandardScheme {
         private GetTypeInfo_argsStandardScheme() {
         }

         public void read(TProtocol iprot, GetTypeInfo_args struct) throws TException {
            iprot.readStructBegin();

            while(true) {
               TField schemeField = iprot.readFieldBegin();
               if (schemeField.type == 0) {
                  iprot.readStructEnd();
                  struct.validate();
                  return;
               }

               switch (schemeField.id) {
                  case 1:
                     if (schemeField.type == 12) {
                        struct.req = new TGetTypeInfoReq();
                        struct.req.read(iprot);
                        struct.setReqIsSet(true);
                     } else {
                        TProtocolUtil.skip(iprot, schemeField.type);
                     }
                     break;
                  default:
                     TProtocolUtil.skip(iprot, schemeField.type);
               }

               iprot.readFieldEnd();
            }
         }

         public void write(TProtocol oprot, GetTypeInfo_args struct) throws TException {
            struct.validate();
            oprot.writeStructBegin(TCLIService.GetTypeInfo_args.STRUCT_DESC);
            if (struct.req != null) {
               oprot.writeFieldBegin(TCLIService.GetTypeInfo_args.REQ_FIELD_DESC);
               struct.req.write(oprot);
               oprot.writeFieldEnd();
            }

            oprot.writeFieldStop();
            oprot.writeStructEnd();
         }
      }

      private static class GetTypeInfo_argsTupleSchemeFactory implements SchemeFactory {
         private GetTypeInfo_argsTupleSchemeFactory() {
         }

         public GetTypeInfo_argsTupleScheme getScheme() {
            return new GetTypeInfo_argsTupleScheme();
         }
      }

      private static class GetTypeInfo_argsTupleScheme extends TupleScheme {
         private GetTypeInfo_argsTupleScheme() {
         }

         public void write(TProtocol prot, GetTypeInfo_args struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol)prot;
            BitSet optionals = new BitSet();
            if (struct.isSetReq()) {
               optionals.set(0);
            }

            oprot.writeBitSet(optionals, 1);
            if (struct.isSetReq()) {
               struct.req.write(oprot);
            }

         }

         public void read(TProtocol prot, GetTypeInfo_args struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol)prot;
            BitSet incoming = iprot.readBitSet(1);
            if (incoming.get(0)) {
               struct.req = new TGetTypeInfoReq();
               struct.req.read(iprot);
               struct.setReqIsSet(true);
            }

         }
      }
   }

   @Public
   @Stable
   public static class GetTypeInfo_result implements TBase, Serializable, Cloneable, Comparable {
      private static final TStruct STRUCT_DESC = new TStruct("GetTypeInfo_result");
      private static final TField SUCCESS_FIELD_DESC = new TField("success", (byte)12, (short)0);
      private static final SchemeFactory STANDARD_SCHEME_FACTORY = new GetTypeInfo_resultStandardSchemeFactory();
      private static final SchemeFactory TUPLE_SCHEME_FACTORY = new GetTypeInfo_resultTupleSchemeFactory();
      @Nullable
      private TGetTypeInfoResp success;
      public static final Map metaDataMap;

      public GetTypeInfo_result() {
      }

      public GetTypeInfo_result(TGetTypeInfoResp success) {
         this();
         this.success = success;
      }

      public GetTypeInfo_result(GetTypeInfo_result other) {
         if (other.isSetSuccess()) {
            this.success = new TGetTypeInfoResp(other.success);
         }

      }

      public GetTypeInfo_result deepCopy() {
         return new GetTypeInfo_result(this);
      }

      public void clear() {
         this.success = null;
      }

      @Nullable
      public TGetTypeInfoResp getSuccess() {
         return this.success;
      }

      public void setSuccess(@Nullable TGetTypeInfoResp success) {
         this.success = success;
      }

      public void unsetSuccess() {
         this.success = null;
      }

      public boolean isSetSuccess() {
         return this.success != null;
      }

      public void setSuccessIsSet(boolean value) {
         if (!value) {
            this.success = null;
         }

      }

      public void setFieldValue(_Fields field, @Nullable Object value) {
         switch (field) {
            case SUCCESS:
               if (value == null) {
                  this.unsetSuccess();
               } else {
                  this.setSuccess((TGetTypeInfoResp)value);
               }
            default:
         }
      }

      @Nullable
      public Object getFieldValue(_Fields field) {
         switch (field) {
            case SUCCESS:
               return this.getSuccess();
            default:
               throw new IllegalStateException();
         }
      }

      public boolean isSet(_Fields field) {
         if (field == null) {
            throw new IllegalArgumentException();
         } else {
            switch (field) {
               case SUCCESS:
                  return this.isSetSuccess();
               default:
                  throw new IllegalStateException();
            }
         }
      }

      public boolean equals(Object that) {
         return that instanceof GetTypeInfo_result ? this.equals((GetTypeInfo_result)that) : false;
      }

      public boolean equals(GetTypeInfo_result that) {
         if (that == null) {
            return false;
         } else if (this == that) {
            return true;
         } else {
            boolean this_present_success = this.isSetSuccess();
            boolean that_present_success = that.isSetSuccess();
            if (this_present_success || that_present_success) {
               if (!this_present_success || !that_present_success) {
                  return false;
               }

               if (!this.success.equals(that.success)) {
                  return false;
               }
            }

            return true;
         }
      }

      public int hashCode() {
         int hashCode = 1;
         hashCode = hashCode * 8191 + (this.isSetSuccess() ? 131071 : 524287);
         if (this.isSetSuccess()) {
            hashCode = hashCode * 8191 + this.success.hashCode();
         }

         return hashCode;
      }

      public int compareTo(GetTypeInfo_result other) {
         if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
         } else {
            int lastComparison = 0;
            lastComparison = Boolean.compare(this.isSetSuccess(), other.isSetSuccess());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetSuccess()) {
                  lastComparison = TBaseHelper.compareTo(this.success, other.success);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               return 0;
            }
         }
      }

      @Nullable
      public _Fields fieldForId(int fieldId) {
         return TCLIService.GetTypeInfo_result._Fields.findByThriftId(fieldId);
      }

      public void read(TProtocol iprot) throws TException {
         scheme(iprot).read(iprot, this);
      }

      public void write(TProtocol oprot) throws TException {
         scheme(oprot).write(oprot, this);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("GetTypeInfo_result(");
         boolean first = true;
         sb.append("success:");
         if (this.success == null) {
            sb.append("null");
         } else {
            sb.append(this.success);
         }

         first = false;
         sb.append(")");
         return sb.toString();
      }

      public void validate() throws TException {
         if (this.success != null) {
            this.success.validate();
         }

      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         try {
            this.write(new TCompactProtocol(new TIOStreamTransport(out)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         try {
            this.read(new TCompactProtocol(new TIOStreamTransport(in)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private static IScheme scheme(TProtocol proto) {
         return (StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
      }

      static {
         Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
         tmpMap.put(TCLIService.GetTypeInfo_result._Fields.SUCCESS, new FieldMetaData("success", (byte)3, new StructMetaData((byte)12, TGetTypeInfoResp.class)));
         metaDataMap = Collections.unmodifiableMap(tmpMap);
         FieldMetaData.addStructMetaDataMap(GetTypeInfo_result.class, metaDataMap);
      }

      public static enum _Fields implements TFieldIdEnum {
         SUCCESS((short)0, "success");

         private static final Map byName = new HashMap();
         private final short _thriftId;
         private final String _fieldName;

         @Nullable
         public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
               case 0:
                  return SUCCESS;
               default:
                  return null;
            }
         }

         public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) {
               throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            } else {
               return fields;
            }
         }

         @Nullable
         public static _Fields findByName(String name) {
            return (_Fields)byName.get(name);
         }

         private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
         }

         public short getThriftFieldId() {
            return this._thriftId;
         }

         public String getFieldName() {
            return this._fieldName;
         }

         static {
            for(_Fields field : EnumSet.allOf(_Fields.class)) {
               byName.put(field.getFieldName(), field);
            }

         }
      }

      private static class GetTypeInfo_resultStandardSchemeFactory implements SchemeFactory {
         private GetTypeInfo_resultStandardSchemeFactory() {
         }

         public GetTypeInfo_resultStandardScheme getScheme() {
            return new GetTypeInfo_resultStandardScheme();
         }
      }

      private static class GetTypeInfo_resultStandardScheme extends StandardScheme {
         private GetTypeInfo_resultStandardScheme() {
         }

         public void read(TProtocol iprot, GetTypeInfo_result struct) throws TException {
            iprot.readStructBegin();

            while(true) {
               TField schemeField = iprot.readFieldBegin();
               if (schemeField.type == 0) {
                  iprot.readStructEnd();
                  struct.validate();
                  return;
               }

               switch (schemeField.id) {
                  case 0:
                     if (schemeField.type == 12) {
                        struct.success = new TGetTypeInfoResp();
                        struct.success.read(iprot);
                        struct.setSuccessIsSet(true);
                     } else {
                        TProtocolUtil.skip(iprot, schemeField.type);
                     }
                     break;
                  default:
                     TProtocolUtil.skip(iprot, schemeField.type);
               }

               iprot.readFieldEnd();
            }
         }

         public void write(TProtocol oprot, GetTypeInfo_result struct) throws TException {
            struct.validate();
            oprot.writeStructBegin(TCLIService.GetTypeInfo_result.STRUCT_DESC);
            if (struct.success != null) {
               oprot.writeFieldBegin(TCLIService.GetTypeInfo_result.SUCCESS_FIELD_DESC);
               struct.success.write(oprot);
               oprot.writeFieldEnd();
            }

            oprot.writeFieldStop();
            oprot.writeStructEnd();
         }
      }

      private static class GetTypeInfo_resultTupleSchemeFactory implements SchemeFactory {
         private GetTypeInfo_resultTupleSchemeFactory() {
         }

         public GetTypeInfo_resultTupleScheme getScheme() {
            return new GetTypeInfo_resultTupleScheme();
         }
      }

      private static class GetTypeInfo_resultTupleScheme extends TupleScheme {
         private GetTypeInfo_resultTupleScheme() {
         }

         public void write(TProtocol prot, GetTypeInfo_result struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol)prot;
            BitSet optionals = new BitSet();
            if (struct.isSetSuccess()) {
               optionals.set(0);
            }

            oprot.writeBitSet(optionals, 1);
            if (struct.isSetSuccess()) {
               struct.success.write(oprot);
            }

         }

         public void read(TProtocol prot, GetTypeInfo_result struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol)prot;
            BitSet incoming = iprot.readBitSet(1);
            if (incoming.get(0)) {
               struct.success = new TGetTypeInfoResp();
               struct.success.read(iprot);
               struct.setSuccessIsSet(true);
            }

         }
      }
   }

   @Public
   @Stable
   public static class GetCatalogs_args implements TBase, Serializable, Cloneable, Comparable {
      private static final TStruct STRUCT_DESC = new TStruct("GetCatalogs_args");
      private static final TField REQ_FIELD_DESC = new TField("req", (byte)12, (short)1);
      private static final SchemeFactory STANDARD_SCHEME_FACTORY = new GetCatalogs_argsStandardSchemeFactory();
      private static final SchemeFactory TUPLE_SCHEME_FACTORY = new GetCatalogs_argsTupleSchemeFactory();
      @Nullable
      private TGetCatalogsReq req;
      public static final Map metaDataMap;

      public GetCatalogs_args() {
      }

      public GetCatalogs_args(TGetCatalogsReq req) {
         this();
         this.req = req;
      }

      public GetCatalogs_args(GetCatalogs_args other) {
         if (other.isSetReq()) {
            this.req = new TGetCatalogsReq(other.req);
         }

      }

      public GetCatalogs_args deepCopy() {
         return new GetCatalogs_args(this);
      }

      public void clear() {
         this.req = null;
      }

      @Nullable
      public TGetCatalogsReq getReq() {
         return this.req;
      }

      public void setReq(@Nullable TGetCatalogsReq req) {
         this.req = req;
      }

      public void unsetReq() {
         this.req = null;
      }

      public boolean isSetReq() {
         return this.req != null;
      }

      public void setReqIsSet(boolean value) {
         if (!value) {
            this.req = null;
         }

      }

      public void setFieldValue(_Fields field, @Nullable Object value) {
         switch (field) {
            case REQ:
               if (value == null) {
                  this.unsetReq();
               } else {
                  this.setReq((TGetCatalogsReq)value);
               }
            default:
         }
      }

      @Nullable
      public Object getFieldValue(_Fields field) {
         switch (field) {
            case REQ:
               return this.getReq();
            default:
               throw new IllegalStateException();
         }
      }

      public boolean isSet(_Fields field) {
         if (field == null) {
            throw new IllegalArgumentException();
         } else {
            switch (field) {
               case REQ:
                  return this.isSetReq();
               default:
                  throw new IllegalStateException();
            }
         }
      }

      public boolean equals(Object that) {
         return that instanceof GetCatalogs_args ? this.equals((GetCatalogs_args)that) : false;
      }

      public boolean equals(GetCatalogs_args that) {
         if (that == null) {
            return false;
         } else if (this == that) {
            return true;
         } else {
            boolean this_present_req = this.isSetReq();
            boolean that_present_req = that.isSetReq();
            if (this_present_req || that_present_req) {
               if (!this_present_req || !that_present_req) {
                  return false;
               }

               if (!this.req.equals(that.req)) {
                  return false;
               }
            }

            return true;
         }
      }

      public int hashCode() {
         int hashCode = 1;
         hashCode = hashCode * 8191 + (this.isSetReq() ? 131071 : 524287);
         if (this.isSetReq()) {
            hashCode = hashCode * 8191 + this.req.hashCode();
         }

         return hashCode;
      }

      public int compareTo(GetCatalogs_args other) {
         if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
         } else {
            int lastComparison = 0;
            lastComparison = Boolean.compare(this.isSetReq(), other.isSetReq());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetReq()) {
                  lastComparison = TBaseHelper.compareTo(this.req, other.req);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               return 0;
            }
         }
      }

      @Nullable
      public _Fields fieldForId(int fieldId) {
         return TCLIService.GetCatalogs_args._Fields.findByThriftId(fieldId);
      }

      public void read(TProtocol iprot) throws TException {
         scheme(iprot).read(iprot, this);
      }

      public void write(TProtocol oprot) throws TException {
         scheme(oprot).write(oprot, this);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("GetCatalogs_args(");
         boolean first = true;
         sb.append("req:");
         if (this.req == null) {
            sb.append("null");
         } else {
            sb.append(this.req);
         }

         first = false;
         sb.append(")");
         return sb.toString();
      }

      public void validate() throws TException {
         if (this.req != null) {
            this.req.validate();
         }

      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         try {
            this.write(new TCompactProtocol(new TIOStreamTransport(out)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         try {
            this.read(new TCompactProtocol(new TIOStreamTransport(in)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private static IScheme scheme(TProtocol proto) {
         return (StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
      }

      static {
         Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
         tmpMap.put(TCLIService.GetCatalogs_args._Fields.REQ, new FieldMetaData("req", (byte)3, new StructMetaData((byte)12, TGetCatalogsReq.class)));
         metaDataMap = Collections.unmodifiableMap(tmpMap);
         FieldMetaData.addStructMetaDataMap(GetCatalogs_args.class, metaDataMap);
      }

      public static enum _Fields implements TFieldIdEnum {
         REQ((short)1, "req");

         private static final Map byName = new HashMap();
         private final short _thriftId;
         private final String _fieldName;

         @Nullable
         public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
               case 1:
                  return REQ;
               default:
                  return null;
            }
         }

         public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) {
               throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            } else {
               return fields;
            }
         }

         @Nullable
         public static _Fields findByName(String name) {
            return (_Fields)byName.get(name);
         }

         private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
         }

         public short getThriftFieldId() {
            return this._thriftId;
         }

         public String getFieldName() {
            return this._fieldName;
         }

         static {
            for(_Fields field : EnumSet.allOf(_Fields.class)) {
               byName.put(field.getFieldName(), field);
            }

         }
      }

      private static class GetCatalogs_argsStandardSchemeFactory implements SchemeFactory {
         private GetCatalogs_argsStandardSchemeFactory() {
         }

         public GetCatalogs_argsStandardScheme getScheme() {
            return new GetCatalogs_argsStandardScheme();
         }
      }

      private static class GetCatalogs_argsStandardScheme extends StandardScheme {
         private GetCatalogs_argsStandardScheme() {
         }

         public void read(TProtocol iprot, GetCatalogs_args struct) throws TException {
            iprot.readStructBegin();

            while(true) {
               TField schemeField = iprot.readFieldBegin();
               if (schemeField.type == 0) {
                  iprot.readStructEnd();
                  struct.validate();
                  return;
               }

               switch (schemeField.id) {
                  case 1:
                     if (schemeField.type == 12) {
                        struct.req = new TGetCatalogsReq();
                        struct.req.read(iprot);
                        struct.setReqIsSet(true);
                     } else {
                        TProtocolUtil.skip(iprot, schemeField.type);
                     }
                     break;
                  default:
                     TProtocolUtil.skip(iprot, schemeField.type);
               }

               iprot.readFieldEnd();
            }
         }

         public void write(TProtocol oprot, GetCatalogs_args struct) throws TException {
            struct.validate();
            oprot.writeStructBegin(TCLIService.GetCatalogs_args.STRUCT_DESC);
            if (struct.req != null) {
               oprot.writeFieldBegin(TCLIService.GetCatalogs_args.REQ_FIELD_DESC);
               struct.req.write(oprot);
               oprot.writeFieldEnd();
            }

            oprot.writeFieldStop();
            oprot.writeStructEnd();
         }
      }

      private static class GetCatalogs_argsTupleSchemeFactory implements SchemeFactory {
         private GetCatalogs_argsTupleSchemeFactory() {
         }

         public GetCatalogs_argsTupleScheme getScheme() {
            return new GetCatalogs_argsTupleScheme();
         }
      }

      private static class GetCatalogs_argsTupleScheme extends TupleScheme {
         private GetCatalogs_argsTupleScheme() {
         }

         public void write(TProtocol prot, GetCatalogs_args struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol)prot;
            BitSet optionals = new BitSet();
            if (struct.isSetReq()) {
               optionals.set(0);
            }

            oprot.writeBitSet(optionals, 1);
            if (struct.isSetReq()) {
               struct.req.write(oprot);
            }

         }

         public void read(TProtocol prot, GetCatalogs_args struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol)prot;
            BitSet incoming = iprot.readBitSet(1);
            if (incoming.get(0)) {
               struct.req = new TGetCatalogsReq();
               struct.req.read(iprot);
               struct.setReqIsSet(true);
            }

         }
      }
   }

   @Public
   @Stable
   public static class GetCatalogs_result implements TBase, Serializable, Cloneable, Comparable {
      private static final TStruct STRUCT_DESC = new TStruct("GetCatalogs_result");
      private static final TField SUCCESS_FIELD_DESC = new TField("success", (byte)12, (short)0);
      private static final SchemeFactory STANDARD_SCHEME_FACTORY = new GetCatalogs_resultStandardSchemeFactory();
      private static final SchemeFactory TUPLE_SCHEME_FACTORY = new GetCatalogs_resultTupleSchemeFactory();
      @Nullable
      private TGetCatalogsResp success;
      public static final Map metaDataMap;

      public GetCatalogs_result() {
      }

      public GetCatalogs_result(TGetCatalogsResp success) {
         this();
         this.success = success;
      }

      public GetCatalogs_result(GetCatalogs_result other) {
         if (other.isSetSuccess()) {
            this.success = new TGetCatalogsResp(other.success);
         }

      }

      public GetCatalogs_result deepCopy() {
         return new GetCatalogs_result(this);
      }

      public void clear() {
         this.success = null;
      }

      @Nullable
      public TGetCatalogsResp getSuccess() {
         return this.success;
      }

      public void setSuccess(@Nullable TGetCatalogsResp success) {
         this.success = success;
      }

      public void unsetSuccess() {
         this.success = null;
      }

      public boolean isSetSuccess() {
         return this.success != null;
      }

      public void setSuccessIsSet(boolean value) {
         if (!value) {
            this.success = null;
         }

      }

      public void setFieldValue(_Fields field, @Nullable Object value) {
         switch (field) {
            case SUCCESS:
               if (value == null) {
                  this.unsetSuccess();
               } else {
                  this.setSuccess((TGetCatalogsResp)value);
               }
            default:
         }
      }

      @Nullable
      public Object getFieldValue(_Fields field) {
         switch (field) {
            case SUCCESS:
               return this.getSuccess();
            default:
               throw new IllegalStateException();
         }
      }

      public boolean isSet(_Fields field) {
         if (field == null) {
            throw new IllegalArgumentException();
         } else {
            switch (field) {
               case SUCCESS:
                  return this.isSetSuccess();
               default:
                  throw new IllegalStateException();
            }
         }
      }

      public boolean equals(Object that) {
         return that instanceof GetCatalogs_result ? this.equals((GetCatalogs_result)that) : false;
      }

      public boolean equals(GetCatalogs_result that) {
         if (that == null) {
            return false;
         } else if (this == that) {
            return true;
         } else {
            boolean this_present_success = this.isSetSuccess();
            boolean that_present_success = that.isSetSuccess();
            if (this_present_success || that_present_success) {
               if (!this_present_success || !that_present_success) {
                  return false;
               }

               if (!this.success.equals(that.success)) {
                  return false;
               }
            }

            return true;
         }
      }

      public int hashCode() {
         int hashCode = 1;
         hashCode = hashCode * 8191 + (this.isSetSuccess() ? 131071 : 524287);
         if (this.isSetSuccess()) {
            hashCode = hashCode * 8191 + this.success.hashCode();
         }

         return hashCode;
      }

      public int compareTo(GetCatalogs_result other) {
         if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
         } else {
            int lastComparison = 0;
            lastComparison = Boolean.compare(this.isSetSuccess(), other.isSetSuccess());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetSuccess()) {
                  lastComparison = TBaseHelper.compareTo(this.success, other.success);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               return 0;
            }
         }
      }

      @Nullable
      public _Fields fieldForId(int fieldId) {
         return TCLIService.GetCatalogs_result._Fields.findByThriftId(fieldId);
      }

      public void read(TProtocol iprot) throws TException {
         scheme(iprot).read(iprot, this);
      }

      public void write(TProtocol oprot) throws TException {
         scheme(oprot).write(oprot, this);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("GetCatalogs_result(");
         boolean first = true;
         sb.append("success:");
         if (this.success == null) {
            sb.append("null");
         } else {
            sb.append(this.success);
         }

         first = false;
         sb.append(")");
         return sb.toString();
      }

      public void validate() throws TException {
         if (this.success != null) {
            this.success.validate();
         }

      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         try {
            this.write(new TCompactProtocol(new TIOStreamTransport(out)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         try {
            this.read(new TCompactProtocol(new TIOStreamTransport(in)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private static IScheme scheme(TProtocol proto) {
         return (StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
      }

      static {
         Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
         tmpMap.put(TCLIService.GetCatalogs_result._Fields.SUCCESS, new FieldMetaData("success", (byte)3, new StructMetaData((byte)12, TGetCatalogsResp.class)));
         metaDataMap = Collections.unmodifiableMap(tmpMap);
         FieldMetaData.addStructMetaDataMap(GetCatalogs_result.class, metaDataMap);
      }

      public static enum _Fields implements TFieldIdEnum {
         SUCCESS((short)0, "success");

         private static final Map byName = new HashMap();
         private final short _thriftId;
         private final String _fieldName;

         @Nullable
         public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
               case 0:
                  return SUCCESS;
               default:
                  return null;
            }
         }

         public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) {
               throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            } else {
               return fields;
            }
         }

         @Nullable
         public static _Fields findByName(String name) {
            return (_Fields)byName.get(name);
         }

         private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
         }

         public short getThriftFieldId() {
            return this._thriftId;
         }

         public String getFieldName() {
            return this._fieldName;
         }

         static {
            for(_Fields field : EnumSet.allOf(_Fields.class)) {
               byName.put(field.getFieldName(), field);
            }

         }
      }

      private static class GetCatalogs_resultStandardSchemeFactory implements SchemeFactory {
         private GetCatalogs_resultStandardSchemeFactory() {
         }

         public GetCatalogs_resultStandardScheme getScheme() {
            return new GetCatalogs_resultStandardScheme();
         }
      }

      private static class GetCatalogs_resultStandardScheme extends StandardScheme {
         private GetCatalogs_resultStandardScheme() {
         }

         public void read(TProtocol iprot, GetCatalogs_result struct) throws TException {
            iprot.readStructBegin();

            while(true) {
               TField schemeField = iprot.readFieldBegin();
               if (schemeField.type == 0) {
                  iprot.readStructEnd();
                  struct.validate();
                  return;
               }

               switch (schemeField.id) {
                  case 0:
                     if (schemeField.type == 12) {
                        struct.success = new TGetCatalogsResp();
                        struct.success.read(iprot);
                        struct.setSuccessIsSet(true);
                     } else {
                        TProtocolUtil.skip(iprot, schemeField.type);
                     }
                     break;
                  default:
                     TProtocolUtil.skip(iprot, schemeField.type);
               }

               iprot.readFieldEnd();
            }
         }

         public void write(TProtocol oprot, GetCatalogs_result struct) throws TException {
            struct.validate();
            oprot.writeStructBegin(TCLIService.GetCatalogs_result.STRUCT_DESC);
            if (struct.success != null) {
               oprot.writeFieldBegin(TCLIService.GetCatalogs_result.SUCCESS_FIELD_DESC);
               struct.success.write(oprot);
               oprot.writeFieldEnd();
            }

            oprot.writeFieldStop();
            oprot.writeStructEnd();
         }
      }

      private static class GetCatalogs_resultTupleSchemeFactory implements SchemeFactory {
         private GetCatalogs_resultTupleSchemeFactory() {
         }

         public GetCatalogs_resultTupleScheme getScheme() {
            return new GetCatalogs_resultTupleScheme();
         }
      }

      private static class GetCatalogs_resultTupleScheme extends TupleScheme {
         private GetCatalogs_resultTupleScheme() {
         }

         public void write(TProtocol prot, GetCatalogs_result struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol)prot;
            BitSet optionals = new BitSet();
            if (struct.isSetSuccess()) {
               optionals.set(0);
            }

            oprot.writeBitSet(optionals, 1);
            if (struct.isSetSuccess()) {
               struct.success.write(oprot);
            }

         }

         public void read(TProtocol prot, GetCatalogs_result struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol)prot;
            BitSet incoming = iprot.readBitSet(1);
            if (incoming.get(0)) {
               struct.success = new TGetCatalogsResp();
               struct.success.read(iprot);
               struct.setSuccessIsSet(true);
            }

         }
      }
   }

   @Public
   @Stable
   public static class GetSchemas_args implements TBase, Serializable, Cloneable, Comparable {
      private static final TStruct STRUCT_DESC = new TStruct("GetSchemas_args");
      private static final TField REQ_FIELD_DESC = new TField("req", (byte)12, (short)1);
      private static final SchemeFactory STANDARD_SCHEME_FACTORY = new GetSchemas_argsStandardSchemeFactory();
      private static final SchemeFactory TUPLE_SCHEME_FACTORY = new GetSchemas_argsTupleSchemeFactory();
      @Nullable
      private TGetSchemasReq req;
      public static final Map metaDataMap;

      public GetSchemas_args() {
      }

      public GetSchemas_args(TGetSchemasReq req) {
         this();
         this.req = req;
      }

      public GetSchemas_args(GetSchemas_args other) {
         if (other.isSetReq()) {
            this.req = new TGetSchemasReq(other.req);
         }

      }

      public GetSchemas_args deepCopy() {
         return new GetSchemas_args(this);
      }

      public void clear() {
         this.req = null;
      }

      @Nullable
      public TGetSchemasReq getReq() {
         return this.req;
      }

      public void setReq(@Nullable TGetSchemasReq req) {
         this.req = req;
      }

      public void unsetReq() {
         this.req = null;
      }

      public boolean isSetReq() {
         return this.req != null;
      }

      public void setReqIsSet(boolean value) {
         if (!value) {
            this.req = null;
         }

      }

      public void setFieldValue(_Fields field, @Nullable Object value) {
         switch (field) {
            case REQ:
               if (value == null) {
                  this.unsetReq();
               } else {
                  this.setReq((TGetSchemasReq)value);
               }
            default:
         }
      }

      @Nullable
      public Object getFieldValue(_Fields field) {
         switch (field) {
            case REQ:
               return this.getReq();
            default:
               throw new IllegalStateException();
         }
      }

      public boolean isSet(_Fields field) {
         if (field == null) {
            throw new IllegalArgumentException();
         } else {
            switch (field) {
               case REQ:
                  return this.isSetReq();
               default:
                  throw new IllegalStateException();
            }
         }
      }

      public boolean equals(Object that) {
         return that instanceof GetSchemas_args ? this.equals((GetSchemas_args)that) : false;
      }

      public boolean equals(GetSchemas_args that) {
         if (that == null) {
            return false;
         } else if (this == that) {
            return true;
         } else {
            boolean this_present_req = this.isSetReq();
            boolean that_present_req = that.isSetReq();
            if (this_present_req || that_present_req) {
               if (!this_present_req || !that_present_req) {
                  return false;
               }

               if (!this.req.equals(that.req)) {
                  return false;
               }
            }

            return true;
         }
      }

      public int hashCode() {
         int hashCode = 1;
         hashCode = hashCode * 8191 + (this.isSetReq() ? 131071 : 524287);
         if (this.isSetReq()) {
            hashCode = hashCode * 8191 + this.req.hashCode();
         }

         return hashCode;
      }

      public int compareTo(GetSchemas_args other) {
         if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
         } else {
            int lastComparison = 0;
            lastComparison = Boolean.compare(this.isSetReq(), other.isSetReq());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetReq()) {
                  lastComparison = TBaseHelper.compareTo(this.req, other.req);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               return 0;
            }
         }
      }

      @Nullable
      public _Fields fieldForId(int fieldId) {
         return TCLIService.GetSchemas_args._Fields.findByThriftId(fieldId);
      }

      public void read(TProtocol iprot) throws TException {
         scheme(iprot).read(iprot, this);
      }

      public void write(TProtocol oprot) throws TException {
         scheme(oprot).write(oprot, this);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("GetSchemas_args(");
         boolean first = true;
         sb.append("req:");
         if (this.req == null) {
            sb.append("null");
         } else {
            sb.append(this.req);
         }

         first = false;
         sb.append(")");
         return sb.toString();
      }

      public void validate() throws TException {
         if (this.req != null) {
            this.req.validate();
         }

      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         try {
            this.write(new TCompactProtocol(new TIOStreamTransport(out)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         try {
            this.read(new TCompactProtocol(new TIOStreamTransport(in)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private static IScheme scheme(TProtocol proto) {
         return (StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
      }

      static {
         Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
         tmpMap.put(TCLIService.GetSchemas_args._Fields.REQ, new FieldMetaData("req", (byte)3, new StructMetaData((byte)12, TGetSchemasReq.class)));
         metaDataMap = Collections.unmodifiableMap(tmpMap);
         FieldMetaData.addStructMetaDataMap(GetSchemas_args.class, metaDataMap);
      }

      public static enum _Fields implements TFieldIdEnum {
         REQ((short)1, "req");

         private static final Map byName = new HashMap();
         private final short _thriftId;
         private final String _fieldName;

         @Nullable
         public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
               case 1:
                  return REQ;
               default:
                  return null;
            }
         }

         public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) {
               throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            } else {
               return fields;
            }
         }

         @Nullable
         public static _Fields findByName(String name) {
            return (_Fields)byName.get(name);
         }

         private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
         }

         public short getThriftFieldId() {
            return this._thriftId;
         }

         public String getFieldName() {
            return this._fieldName;
         }

         static {
            for(_Fields field : EnumSet.allOf(_Fields.class)) {
               byName.put(field.getFieldName(), field);
            }

         }
      }

      private static class GetSchemas_argsStandardSchemeFactory implements SchemeFactory {
         private GetSchemas_argsStandardSchemeFactory() {
         }

         public GetSchemas_argsStandardScheme getScheme() {
            return new GetSchemas_argsStandardScheme();
         }
      }

      private static class GetSchemas_argsStandardScheme extends StandardScheme {
         private GetSchemas_argsStandardScheme() {
         }

         public void read(TProtocol iprot, GetSchemas_args struct) throws TException {
            iprot.readStructBegin();

            while(true) {
               TField schemeField = iprot.readFieldBegin();
               if (schemeField.type == 0) {
                  iprot.readStructEnd();
                  struct.validate();
                  return;
               }

               switch (schemeField.id) {
                  case 1:
                     if (schemeField.type == 12) {
                        struct.req = new TGetSchemasReq();
                        struct.req.read(iprot);
                        struct.setReqIsSet(true);
                     } else {
                        TProtocolUtil.skip(iprot, schemeField.type);
                     }
                     break;
                  default:
                     TProtocolUtil.skip(iprot, schemeField.type);
               }

               iprot.readFieldEnd();
            }
         }

         public void write(TProtocol oprot, GetSchemas_args struct) throws TException {
            struct.validate();
            oprot.writeStructBegin(TCLIService.GetSchemas_args.STRUCT_DESC);
            if (struct.req != null) {
               oprot.writeFieldBegin(TCLIService.GetSchemas_args.REQ_FIELD_DESC);
               struct.req.write(oprot);
               oprot.writeFieldEnd();
            }

            oprot.writeFieldStop();
            oprot.writeStructEnd();
         }
      }

      private static class GetSchemas_argsTupleSchemeFactory implements SchemeFactory {
         private GetSchemas_argsTupleSchemeFactory() {
         }

         public GetSchemas_argsTupleScheme getScheme() {
            return new GetSchemas_argsTupleScheme();
         }
      }

      private static class GetSchemas_argsTupleScheme extends TupleScheme {
         private GetSchemas_argsTupleScheme() {
         }

         public void write(TProtocol prot, GetSchemas_args struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol)prot;
            BitSet optionals = new BitSet();
            if (struct.isSetReq()) {
               optionals.set(0);
            }

            oprot.writeBitSet(optionals, 1);
            if (struct.isSetReq()) {
               struct.req.write(oprot);
            }

         }

         public void read(TProtocol prot, GetSchemas_args struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol)prot;
            BitSet incoming = iprot.readBitSet(1);
            if (incoming.get(0)) {
               struct.req = new TGetSchemasReq();
               struct.req.read(iprot);
               struct.setReqIsSet(true);
            }

         }
      }
   }

   @Public
   @Stable
   public static class GetSchemas_result implements TBase, Serializable, Cloneable, Comparable {
      private static final TStruct STRUCT_DESC = new TStruct("GetSchemas_result");
      private static final TField SUCCESS_FIELD_DESC = new TField("success", (byte)12, (short)0);
      private static final SchemeFactory STANDARD_SCHEME_FACTORY = new GetSchemas_resultStandardSchemeFactory();
      private static final SchemeFactory TUPLE_SCHEME_FACTORY = new GetSchemas_resultTupleSchemeFactory();
      @Nullable
      private TGetSchemasResp success;
      public static final Map metaDataMap;

      public GetSchemas_result() {
      }

      public GetSchemas_result(TGetSchemasResp success) {
         this();
         this.success = success;
      }

      public GetSchemas_result(GetSchemas_result other) {
         if (other.isSetSuccess()) {
            this.success = new TGetSchemasResp(other.success);
         }

      }

      public GetSchemas_result deepCopy() {
         return new GetSchemas_result(this);
      }

      public void clear() {
         this.success = null;
      }

      @Nullable
      public TGetSchemasResp getSuccess() {
         return this.success;
      }

      public void setSuccess(@Nullable TGetSchemasResp success) {
         this.success = success;
      }

      public void unsetSuccess() {
         this.success = null;
      }

      public boolean isSetSuccess() {
         return this.success != null;
      }

      public void setSuccessIsSet(boolean value) {
         if (!value) {
            this.success = null;
         }

      }

      public void setFieldValue(_Fields field, @Nullable Object value) {
         switch (field) {
            case SUCCESS:
               if (value == null) {
                  this.unsetSuccess();
               } else {
                  this.setSuccess((TGetSchemasResp)value);
               }
            default:
         }
      }

      @Nullable
      public Object getFieldValue(_Fields field) {
         switch (field) {
            case SUCCESS:
               return this.getSuccess();
            default:
               throw new IllegalStateException();
         }
      }

      public boolean isSet(_Fields field) {
         if (field == null) {
            throw new IllegalArgumentException();
         } else {
            switch (field) {
               case SUCCESS:
                  return this.isSetSuccess();
               default:
                  throw new IllegalStateException();
            }
         }
      }

      public boolean equals(Object that) {
         return that instanceof GetSchemas_result ? this.equals((GetSchemas_result)that) : false;
      }

      public boolean equals(GetSchemas_result that) {
         if (that == null) {
            return false;
         } else if (this == that) {
            return true;
         } else {
            boolean this_present_success = this.isSetSuccess();
            boolean that_present_success = that.isSetSuccess();
            if (this_present_success || that_present_success) {
               if (!this_present_success || !that_present_success) {
                  return false;
               }

               if (!this.success.equals(that.success)) {
                  return false;
               }
            }

            return true;
         }
      }

      public int hashCode() {
         int hashCode = 1;
         hashCode = hashCode * 8191 + (this.isSetSuccess() ? 131071 : 524287);
         if (this.isSetSuccess()) {
            hashCode = hashCode * 8191 + this.success.hashCode();
         }

         return hashCode;
      }

      public int compareTo(GetSchemas_result other) {
         if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
         } else {
            int lastComparison = 0;
            lastComparison = Boolean.compare(this.isSetSuccess(), other.isSetSuccess());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetSuccess()) {
                  lastComparison = TBaseHelper.compareTo(this.success, other.success);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               return 0;
            }
         }
      }

      @Nullable
      public _Fields fieldForId(int fieldId) {
         return TCLIService.GetSchemas_result._Fields.findByThriftId(fieldId);
      }

      public void read(TProtocol iprot) throws TException {
         scheme(iprot).read(iprot, this);
      }

      public void write(TProtocol oprot) throws TException {
         scheme(oprot).write(oprot, this);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("GetSchemas_result(");
         boolean first = true;
         sb.append("success:");
         if (this.success == null) {
            sb.append("null");
         } else {
            sb.append(this.success);
         }

         first = false;
         sb.append(")");
         return sb.toString();
      }

      public void validate() throws TException {
         if (this.success != null) {
            this.success.validate();
         }

      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         try {
            this.write(new TCompactProtocol(new TIOStreamTransport(out)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         try {
            this.read(new TCompactProtocol(new TIOStreamTransport(in)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private static IScheme scheme(TProtocol proto) {
         return (StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
      }

      static {
         Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
         tmpMap.put(TCLIService.GetSchemas_result._Fields.SUCCESS, new FieldMetaData("success", (byte)3, new StructMetaData((byte)12, TGetSchemasResp.class)));
         metaDataMap = Collections.unmodifiableMap(tmpMap);
         FieldMetaData.addStructMetaDataMap(GetSchemas_result.class, metaDataMap);
      }

      public static enum _Fields implements TFieldIdEnum {
         SUCCESS((short)0, "success");

         private static final Map byName = new HashMap();
         private final short _thriftId;
         private final String _fieldName;

         @Nullable
         public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
               case 0:
                  return SUCCESS;
               default:
                  return null;
            }
         }

         public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) {
               throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            } else {
               return fields;
            }
         }

         @Nullable
         public static _Fields findByName(String name) {
            return (_Fields)byName.get(name);
         }

         private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
         }

         public short getThriftFieldId() {
            return this._thriftId;
         }

         public String getFieldName() {
            return this._fieldName;
         }

         static {
            for(_Fields field : EnumSet.allOf(_Fields.class)) {
               byName.put(field.getFieldName(), field);
            }

         }
      }

      private static class GetSchemas_resultStandardSchemeFactory implements SchemeFactory {
         private GetSchemas_resultStandardSchemeFactory() {
         }

         public GetSchemas_resultStandardScheme getScheme() {
            return new GetSchemas_resultStandardScheme();
         }
      }

      private static class GetSchemas_resultStandardScheme extends StandardScheme {
         private GetSchemas_resultStandardScheme() {
         }

         public void read(TProtocol iprot, GetSchemas_result struct) throws TException {
            iprot.readStructBegin();

            while(true) {
               TField schemeField = iprot.readFieldBegin();
               if (schemeField.type == 0) {
                  iprot.readStructEnd();
                  struct.validate();
                  return;
               }

               switch (schemeField.id) {
                  case 0:
                     if (schemeField.type == 12) {
                        struct.success = new TGetSchemasResp();
                        struct.success.read(iprot);
                        struct.setSuccessIsSet(true);
                     } else {
                        TProtocolUtil.skip(iprot, schemeField.type);
                     }
                     break;
                  default:
                     TProtocolUtil.skip(iprot, schemeField.type);
               }

               iprot.readFieldEnd();
            }
         }

         public void write(TProtocol oprot, GetSchemas_result struct) throws TException {
            struct.validate();
            oprot.writeStructBegin(TCLIService.GetSchemas_result.STRUCT_DESC);
            if (struct.success != null) {
               oprot.writeFieldBegin(TCLIService.GetSchemas_result.SUCCESS_FIELD_DESC);
               struct.success.write(oprot);
               oprot.writeFieldEnd();
            }

            oprot.writeFieldStop();
            oprot.writeStructEnd();
         }
      }

      private static class GetSchemas_resultTupleSchemeFactory implements SchemeFactory {
         private GetSchemas_resultTupleSchemeFactory() {
         }

         public GetSchemas_resultTupleScheme getScheme() {
            return new GetSchemas_resultTupleScheme();
         }
      }

      private static class GetSchemas_resultTupleScheme extends TupleScheme {
         private GetSchemas_resultTupleScheme() {
         }

         public void write(TProtocol prot, GetSchemas_result struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol)prot;
            BitSet optionals = new BitSet();
            if (struct.isSetSuccess()) {
               optionals.set(0);
            }

            oprot.writeBitSet(optionals, 1);
            if (struct.isSetSuccess()) {
               struct.success.write(oprot);
            }

         }

         public void read(TProtocol prot, GetSchemas_result struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol)prot;
            BitSet incoming = iprot.readBitSet(1);
            if (incoming.get(0)) {
               struct.success = new TGetSchemasResp();
               struct.success.read(iprot);
               struct.setSuccessIsSet(true);
            }

         }
      }
   }

   @Public
   @Stable
   public static class GetTables_args implements TBase, Serializable, Cloneable, Comparable {
      private static final TStruct STRUCT_DESC = new TStruct("GetTables_args");
      private static final TField REQ_FIELD_DESC = new TField("req", (byte)12, (short)1);
      private static final SchemeFactory STANDARD_SCHEME_FACTORY = new GetTables_argsStandardSchemeFactory();
      private static final SchemeFactory TUPLE_SCHEME_FACTORY = new GetTables_argsTupleSchemeFactory();
      @Nullable
      private TGetTablesReq req;
      public static final Map metaDataMap;

      public GetTables_args() {
      }

      public GetTables_args(TGetTablesReq req) {
         this();
         this.req = req;
      }

      public GetTables_args(GetTables_args other) {
         if (other.isSetReq()) {
            this.req = new TGetTablesReq(other.req);
         }

      }

      public GetTables_args deepCopy() {
         return new GetTables_args(this);
      }

      public void clear() {
         this.req = null;
      }

      @Nullable
      public TGetTablesReq getReq() {
         return this.req;
      }

      public void setReq(@Nullable TGetTablesReq req) {
         this.req = req;
      }

      public void unsetReq() {
         this.req = null;
      }

      public boolean isSetReq() {
         return this.req != null;
      }

      public void setReqIsSet(boolean value) {
         if (!value) {
            this.req = null;
         }

      }

      public void setFieldValue(_Fields field, @Nullable Object value) {
         switch (field) {
            case REQ:
               if (value == null) {
                  this.unsetReq();
               } else {
                  this.setReq((TGetTablesReq)value);
               }
            default:
         }
      }

      @Nullable
      public Object getFieldValue(_Fields field) {
         switch (field) {
            case REQ:
               return this.getReq();
            default:
               throw new IllegalStateException();
         }
      }

      public boolean isSet(_Fields field) {
         if (field == null) {
            throw new IllegalArgumentException();
         } else {
            switch (field) {
               case REQ:
                  return this.isSetReq();
               default:
                  throw new IllegalStateException();
            }
         }
      }

      public boolean equals(Object that) {
         return that instanceof GetTables_args ? this.equals((GetTables_args)that) : false;
      }

      public boolean equals(GetTables_args that) {
         if (that == null) {
            return false;
         } else if (this == that) {
            return true;
         } else {
            boolean this_present_req = this.isSetReq();
            boolean that_present_req = that.isSetReq();
            if (this_present_req || that_present_req) {
               if (!this_present_req || !that_present_req) {
                  return false;
               }

               if (!this.req.equals(that.req)) {
                  return false;
               }
            }

            return true;
         }
      }

      public int hashCode() {
         int hashCode = 1;
         hashCode = hashCode * 8191 + (this.isSetReq() ? 131071 : 524287);
         if (this.isSetReq()) {
            hashCode = hashCode * 8191 + this.req.hashCode();
         }

         return hashCode;
      }

      public int compareTo(GetTables_args other) {
         if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
         } else {
            int lastComparison = 0;
            lastComparison = Boolean.compare(this.isSetReq(), other.isSetReq());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetReq()) {
                  lastComparison = TBaseHelper.compareTo(this.req, other.req);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               return 0;
            }
         }
      }

      @Nullable
      public _Fields fieldForId(int fieldId) {
         return TCLIService.GetTables_args._Fields.findByThriftId(fieldId);
      }

      public void read(TProtocol iprot) throws TException {
         scheme(iprot).read(iprot, this);
      }

      public void write(TProtocol oprot) throws TException {
         scheme(oprot).write(oprot, this);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("GetTables_args(");
         boolean first = true;
         sb.append("req:");
         if (this.req == null) {
            sb.append("null");
         } else {
            sb.append(this.req);
         }

         first = false;
         sb.append(")");
         return sb.toString();
      }

      public void validate() throws TException {
         if (this.req != null) {
            this.req.validate();
         }

      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         try {
            this.write(new TCompactProtocol(new TIOStreamTransport(out)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         try {
            this.read(new TCompactProtocol(new TIOStreamTransport(in)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private static IScheme scheme(TProtocol proto) {
         return (StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
      }

      static {
         Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
         tmpMap.put(TCLIService.GetTables_args._Fields.REQ, new FieldMetaData("req", (byte)3, new StructMetaData((byte)12, TGetTablesReq.class)));
         metaDataMap = Collections.unmodifiableMap(tmpMap);
         FieldMetaData.addStructMetaDataMap(GetTables_args.class, metaDataMap);
      }

      public static enum _Fields implements TFieldIdEnum {
         REQ((short)1, "req");

         private static final Map byName = new HashMap();
         private final short _thriftId;
         private final String _fieldName;

         @Nullable
         public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
               case 1:
                  return REQ;
               default:
                  return null;
            }
         }

         public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) {
               throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            } else {
               return fields;
            }
         }

         @Nullable
         public static _Fields findByName(String name) {
            return (_Fields)byName.get(name);
         }

         private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
         }

         public short getThriftFieldId() {
            return this._thriftId;
         }

         public String getFieldName() {
            return this._fieldName;
         }

         static {
            for(_Fields field : EnumSet.allOf(_Fields.class)) {
               byName.put(field.getFieldName(), field);
            }

         }
      }

      private static class GetTables_argsStandardSchemeFactory implements SchemeFactory {
         private GetTables_argsStandardSchemeFactory() {
         }

         public GetTables_argsStandardScheme getScheme() {
            return new GetTables_argsStandardScheme();
         }
      }

      private static class GetTables_argsStandardScheme extends StandardScheme {
         private GetTables_argsStandardScheme() {
         }

         public void read(TProtocol iprot, GetTables_args struct) throws TException {
            iprot.readStructBegin();

            while(true) {
               TField schemeField = iprot.readFieldBegin();
               if (schemeField.type == 0) {
                  iprot.readStructEnd();
                  struct.validate();
                  return;
               }

               switch (schemeField.id) {
                  case 1:
                     if (schemeField.type == 12) {
                        struct.req = new TGetTablesReq();
                        struct.req.read(iprot);
                        struct.setReqIsSet(true);
                     } else {
                        TProtocolUtil.skip(iprot, schemeField.type);
                     }
                     break;
                  default:
                     TProtocolUtil.skip(iprot, schemeField.type);
               }

               iprot.readFieldEnd();
            }
         }

         public void write(TProtocol oprot, GetTables_args struct) throws TException {
            struct.validate();
            oprot.writeStructBegin(TCLIService.GetTables_args.STRUCT_DESC);
            if (struct.req != null) {
               oprot.writeFieldBegin(TCLIService.GetTables_args.REQ_FIELD_DESC);
               struct.req.write(oprot);
               oprot.writeFieldEnd();
            }

            oprot.writeFieldStop();
            oprot.writeStructEnd();
         }
      }

      private static class GetTables_argsTupleSchemeFactory implements SchemeFactory {
         private GetTables_argsTupleSchemeFactory() {
         }

         public GetTables_argsTupleScheme getScheme() {
            return new GetTables_argsTupleScheme();
         }
      }

      private static class GetTables_argsTupleScheme extends TupleScheme {
         private GetTables_argsTupleScheme() {
         }

         public void write(TProtocol prot, GetTables_args struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol)prot;
            BitSet optionals = new BitSet();
            if (struct.isSetReq()) {
               optionals.set(0);
            }

            oprot.writeBitSet(optionals, 1);
            if (struct.isSetReq()) {
               struct.req.write(oprot);
            }

         }

         public void read(TProtocol prot, GetTables_args struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol)prot;
            BitSet incoming = iprot.readBitSet(1);
            if (incoming.get(0)) {
               struct.req = new TGetTablesReq();
               struct.req.read(iprot);
               struct.setReqIsSet(true);
            }

         }
      }
   }

   @Public
   @Stable
   public static class GetTables_result implements TBase, Serializable, Cloneable, Comparable {
      private static final TStruct STRUCT_DESC = new TStruct("GetTables_result");
      private static final TField SUCCESS_FIELD_DESC = new TField("success", (byte)12, (short)0);
      private static final SchemeFactory STANDARD_SCHEME_FACTORY = new GetTables_resultStandardSchemeFactory();
      private static final SchemeFactory TUPLE_SCHEME_FACTORY = new GetTables_resultTupleSchemeFactory();
      @Nullable
      private TGetTablesResp success;
      public static final Map metaDataMap;

      public GetTables_result() {
      }

      public GetTables_result(TGetTablesResp success) {
         this();
         this.success = success;
      }

      public GetTables_result(GetTables_result other) {
         if (other.isSetSuccess()) {
            this.success = new TGetTablesResp(other.success);
         }

      }

      public GetTables_result deepCopy() {
         return new GetTables_result(this);
      }

      public void clear() {
         this.success = null;
      }

      @Nullable
      public TGetTablesResp getSuccess() {
         return this.success;
      }

      public void setSuccess(@Nullable TGetTablesResp success) {
         this.success = success;
      }

      public void unsetSuccess() {
         this.success = null;
      }

      public boolean isSetSuccess() {
         return this.success != null;
      }

      public void setSuccessIsSet(boolean value) {
         if (!value) {
            this.success = null;
         }

      }

      public void setFieldValue(_Fields field, @Nullable Object value) {
         switch (field) {
            case SUCCESS:
               if (value == null) {
                  this.unsetSuccess();
               } else {
                  this.setSuccess((TGetTablesResp)value);
               }
            default:
         }
      }

      @Nullable
      public Object getFieldValue(_Fields field) {
         switch (field) {
            case SUCCESS:
               return this.getSuccess();
            default:
               throw new IllegalStateException();
         }
      }

      public boolean isSet(_Fields field) {
         if (field == null) {
            throw new IllegalArgumentException();
         } else {
            switch (field) {
               case SUCCESS:
                  return this.isSetSuccess();
               default:
                  throw new IllegalStateException();
            }
         }
      }

      public boolean equals(Object that) {
         return that instanceof GetTables_result ? this.equals((GetTables_result)that) : false;
      }

      public boolean equals(GetTables_result that) {
         if (that == null) {
            return false;
         } else if (this == that) {
            return true;
         } else {
            boolean this_present_success = this.isSetSuccess();
            boolean that_present_success = that.isSetSuccess();
            if (this_present_success || that_present_success) {
               if (!this_present_success || !that_present_success) {
                  return false;
               }

               if (!this.success.equals(that.success)) {
                  return false;
               }
            }

            return true;
         }
      }

      public int hashCode() {
         int hashCode = 1;
         hashCode = hashCode * 8191 + (this.isSetSuccess() ? 131071 : 524287);
         if (this.isSetSuccess()) {
            hashCode = hashCode * 8191 + this.success.hashCode();
         }

         return hashCode;
      }

      public int compareTo(GetTables_result other) {
         if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
         } else {
            int lastComparison = 0;
            lastComparison = Boolean.compare(this.isSetSuccess(), other.isSetSuccess());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetSuccess()) {
                  lastComparison = TBaseHelper.compareTo(this.success, other.success);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               return 0;
            }
         }
      }

      @Nullable
      public _Fields fieldForId(int fieldId) {
         return TCLIService.GetTables_result._Fields.findByThriftId(fieldId);
      }

      public void read(TProtocol iprot) throws TException {
         scheme(iprot).read(iprot, this);
      }

      public void write(TProtocol oprot) throws TException {
         scheme(oprot).write(oprot, this);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("GetTables_result(");
         boolean first = true;
         sb.append("success:");
         if (this.success == null) {
            sb.append("null");
         } else {
            sb.append(this.success);
         }

         first = false;
         sb.append(")");
         return sb.toString();
      }

      public void validate() throws TException {
         if (this.success != null) {
            this.success.validate();
         }

      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         try {
            this.write(new TCompactProtocol(new TIOStreamTransport(out)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         try {
            this.read(new TCompactProtocol(new TIOStreamTransport(in)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private static IScheme scheme(TProtocol proto) {
         return (StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
      }

      static {
         Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
         tmpMap.put(TCLIService.GetTables_result._Fields.SUCCESS, new FieldMetaData("success", (byte)3, new StructMetaData((byte)12, TGetTablesResp.class)));
         metaDataMap = Collections.unmodifiableMap(tmpMap);
         FieldMetaData.addStructMetaDataMap(GetTables_result.class, metaDataMap);
      }

      public static enum _Fields implements TFieldIdEnum {
         SUCCESS((short)0, "success");

         private static final Map byName = new HashMap();
         private final short _thriftId;
         private final String _fieldName;

         @Nullable
         public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
               case 0:
                  return SUCCESS;
               default:
                  return null;
            }
         }

         public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) {
               throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            } else {
               return fields;
            }
         }

         @Nullable
         public static _Fields findByName(String name) {
            return (_Fields)byName.get(name);
         }

         private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
         }

         public short getThriftFieldId() {
            return this._thriftId;
         }

         public String getFieldName() {
            return this._fieldName;
         }

         static {
            for(_Fields field : EnumSet.allOf(_Fields.class)) {
               byName.put(field.getFieldName(), field);
            }

         }
      }

      private static class GetTables_resultStandardSchemeFactory implements SchemeFactory {
         private GetTables_resultStandardSchemeFactory() {
         }

         public GetTables_resultStandardScheme getScheme() {
            return new GetTables_resultStandardScheme();
         }
      }

      private static class GetTables_resultStandardScheme extends StandardScheme {
         private GetTables_resultStandardScheme() {
         }

         public void read(TProtocol iprot, GetTables_result struct) throws TException {
            iprot.readStructBegin();

            while(true) {
               TField schemeField = iprot.readFieldBegin();
               if (schemeField.type == 0) {
                  iprot.readStructEnd();
                  struct.validate();
                  return;
               }

               switch (schemeField.id) {
                  case 0:
                     if (schemeField.type == 12) {
                        struct.success = new TGetTablesResp();
                        struct.success.read(iprot);
                        struct.setSuccessIsSet(true);
                     } else {
                        TProtocolUtil.skip(iprot, schemeField.type);
                     }
                     break;
                  default:
                     TProtocolUtil.skip(iprot, schemeField.type);
               }

               iprot.readFieldEnd();
            }
         }

         public void write(TProtocol oprot, GetTables_result struct) throws TException {
            struct.validate();
            oprot.writeStructBegin(TCLIService.GetTables_result.STRUCT_DESC);
            if (struct.success != null) {
               oprot.writeFieldBegin(TCLIService.GetTables_result.SUCCESS_FIELD_DESC);
               struct.success.write(oprot);
               oprot.writeFieldEnd();
            }

            oprot.writeFieldStop();
            oprot.writeStructEnd();
         }
      }

      private static class GetTables_resultTupleSchemeFactory implements SchemeFactory {
         private GetTables_resultTupleSchemeFactory() {
         }

         public GetTables_resultTupleScheme getScheme() {
            return new GetTables_resultTupleScheme();
         }
      }

      private static class GetTables_resultTupleScheme extends TupleScheme {
         private GetTables_resultTupleScheme() {
         }

         public void write(TProtocol prot, GetTables_result struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol)prot;
            BitSet optionals = new BitSet();
            if (struct.isSetSuccess()) {
               optionals.set(0);
            }

            oprot.writeBitSet(optionals, 1);
            if (struct.isSetSuccess()) {
               struct.success.write(oprot);
            }

         }

         public void read(TProtocol prot, GetTables_result struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol)prot;
            BitSet incoming = iprot.readBitSet(1);
            if (incoming.get(0)) {
               struct.success = new TGetTablesResp();
               struct.success.read(iprot);
               struct.setSuccessIsSet(true);
            }

         }
      }
   }

   @Public
   @Stable
   public static class GetTableTypes_args implements TBase, Serializable, Cloneable, Comparable {
      private static final TStruct STRUCT_DESC = new TStruct("GetTableTypes_args");
      private static final TField REQ_FIELD_DESC = new TField("req", (byte)12, (short)1);
      private static final SchemeFactory STANDARD_SCHEME_FACTORY = new GetTableTypes_argsStandardSchemeFactory();
      private static final SchemeFactory TUPLE_SCHEME_FACTORY = new GetTableTypes_argsTupleSchemeFactory();
      @Nullable
      private TGetTableTypesReq req;
      public static final Map metaDataMap;

      public GetTableTypes_args() {
      }

      public GetTableTypes_args(TGetTableTypesReq req) {
         this();
         this.req = req;
      }

      public GetTableTypes_args(GetTableTypes_args other) {
         if (other.isSetReq()) {
            this.req = new TGetTableTypesReq(other.req);
         }

      }

      public GetTableTypes_args deepCopy() {
         return new GetTableTypes_args(this);
      }

      public void clear() {
         this.req = null;
      }

      @Nullable
      public TGetTableTypesReq getReq() {
         return this.req;
      }

      public void setReq(@Nullable TGetTableTypesReq req) {
         this.req = req;
      }

      public void unsetReq() {
         this.req = null;
      }

      public boolean isSetReq() {
         return this.req != null;
      }

      public void setReqIsSet(boolean value) {
         if (!value) {
            this.req = null;
         }

      }

      public void setFieldValue(_Fields field, @Nullable Object value) {
         switch (field) {
            case REQ:
               if (value == null) {
                  this.unsetReq();
               } else {
                  this.setReq((TGetTableTypesReq)value);
               }
            default:
         }
      }

      @Nullable
      public Object getFieldValue(_Fields field) {
         switch (field) {
            case REQ:
               return this.getReq();
            default:
               throw new IllegalStateException();
         }
      }

      public boolean isSet(_Fields field) {
         if (field == null) {
            throw new IllegalArgumentException();
         } else {
            switch (field) {
               case REQ:
                  return this.isSetReq();
               default:
                  throw new IllegalStateException();
            }
         }
      }

      public boolean equals(Object that) {
         return that instanceof GetTableTypes_args ? this.equals((GetTableTypes_args)that) : false;
      }

      public boolean equals(GetTableTypes_args that) {
         if (that == null) {
            return false;
         } else if (this == that) {
            return true;
         } else {
            boolean this_present_req = this.isSetReq();
            boolean that_present_req = that.isSetReq();
            if (this_present_req || that_present_req) {
               if (!this_present_req || !that_present_req) {
                  return false;
               }

               if (!this.req.equals(that.req)) {
                  return false;
               }
            }

            return true;
         }
      }

      public int hashCode() {
         int hashCode = 1;
         hashCode = hashCode * 8191 + (this.isSetReq() ? 131071 : 524287);
         if (this.isSetReq()) {
            hashCode = hashCode * 8191 + this.req.hashCode();
         }

         return hashCode;
      }

      public int compareTo(GetTableTypes_args other) {
         if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
         } else {
            int lastComparison = 0;
            lastComparison = Boolean.compare(this.isSetReq(), other.isSetReq());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetReq()) {
                  lastComparison = TBaseHelper.compareTo(this.req, other.req);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               return 0;
            }
         }
      }

      @Nullable
      public _Fields fieldForId(int fieldId) {
         return TCLIService.GetTableTypes_args._Fields.findByThriftId(fieldId);
      }

      public void read(TProtocol iprot) throws TException {
         scheme(iprot).read(iprot, this);
      }

      public void write(TProtocol oprot) throws TException {
         scheme(oprot).write(oprot, this);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("GetTableTypes_args(");
         boolean first = true;
         sb.append("req:");
         if (this.req == null) {
            sb.append("null");
         } else {
            sb.append(this.req);
         }

         first = false;
         sb.append(")");
         return sb.toString();
      }

      public void validate() throws TException {
         if (this.req != null) {
            this.req.validate();
         }

      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         try {
            this.write(new TCompactProtocol(new TIOStreamTransport(out)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         try {
            this.read(new TCompactProtocol(new TIOStreamTransport(in)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private static IScheme scheme(TProtocol proto) {
         return (StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
      }

      static {
         Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
         tmpMap.put(TCLIService.GetTableTypes_args._Fields.REQ, new FieldMetaData("req", (byte)3, new StructMetaData((byte)12, TGetTableTypesReq.class)));
         metaDataMap = Collections.unmodifiableMap(tmpMap);
         FieldMetaData.addStructMetaDataMap(GetTableTypes_args.class, metaDataMap);
      }

      public static enum _Fields implements TFieldIdEnum {
         REQ((short)1, "req");

         private static final Map byName = new HashMap();
         private final short _thriftId;
         private final String _fieldName;

         @Nullable
         public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
               case 1:
                  return REQ;
               default:
                  return null;
            }
         }

         public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) {
               throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            } else {
               return fields;
            }
         }

         @Nullable
         public static _Fields findByName(String name) {
            return (_Fields)byName.get(name);
         }

         private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
         }

         public short getThriftFieldId() {
            return this._thriftId;
         }

         public String getFieldName() {
            return this._fieldName;
         }

         static {
            for(_Fields field : EnumSet.allOf(_Fields.class)) {
               byName.put(field.getFieldName(), field);
            }

         }
      }

      private static class GetTableTypes_argsStandardSchemeFactory implements SchemeFactory {
         private GetTableTypes_argsStandardSchemeFactory() {
         }

         public GetTableTypes_argsStandardScheme getScheme() {
            return new GetTableTypes_argsStandardScheme();
         }
      }

      private static class GetTableTypes_argsStandardScheme extends StandardScheme {
         private GetTableTypes_argsStandardScheme() {
         }

         public void read(TProtocol iprot, GetTableTypes_args struct) throws TException {
            iprot.readStructBegin();

            while(true) {
               TField schemeField = iprot.readFieldBegin();
               if (schemeField.type == 0) {
                  iprot.readStructEnd();
                  struct.validate();
                  return;
               }

               switch (schemeField.id) {
                  case 1:
                     if (schemeField.type == 12) {
                        struct.req = new TGetTableTypesReq();
                        struct.req.read(iprot);
                        struct.setReqIsSet(true);
                     } else {
                        TProtocolUtil.skip(iprot, schemeField.type);
                     }
                     break;
                  default:
                     TProtocolUtil.skip(iprot, schemeField.type);
               }

               iprot.readFieldEnd();
            }
         }

         public void write(TProtocol oprot, GetTableTypes_args struct) throws TException {
            struct.validate();
            oprot.writeStructBegin(TCLIService.GetTableTypes_args.STRUCT_DESC);
            if (struct.req != null) {
               oprot.writeFieldBegin(TCLIService.GetTableTypes_args.REQ_FIELD_DESC);
               struct.req.write(oprot);
               oprot.writeFieldEnd();
            }

            oprot.writeFieldStop();
            oprot.writeStructEnd();
         }
      }

      private static class GetTableTypes_argsTupleSchemeFactory implements SchemeFactory {
         private GetTableTypes_argsTupleSchemeFactory() {
         }

         public GetTableTypes_argsTupleScheme getScheme() {
            return new GetTableTypes_argsTupleScheme();
         }
      }

      private static class GetTableTypes_argsTupleScheme extends TupleScheme {
         private GetTableTypes_argsTupleScheme() {
         }

         public void write(TProtocol prot, GetTableTypes_args struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol)prot;
            BitSet optionals = new BitSet();
            if (struct.isSetReq()) {
               optionals.set(0);
            }

            oprot.writeBitSet(optionals, 1);
            if (struct.isSetReq()) {
               struct.req.write(oprot);
            }

         }

         public void read(TProtocol prot, GetTableTypes_args struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol)prot;
            BitSet incoming = iprot.readBitSet(1);
            if (incoming.get(0)) {
               struct.req = new TGetTableTypesReq();
               struct.req.read(iprot);
               struct.setReqIsSet(true);
            }

         }
      }
   }

   @Public
   @Stable
   public static class GetTableTypes_result implements TBase, Serializable, Cloneable, Comparable {
      private static final TStruct STRUCT_DESC = new TStruct("GetTableTypes_result");
      private static final TField SUCCESS_FIELD_DESC = new TField("success", (byte)12, (short)0);
      private static final SchemeFactory STANDARD_SCHEME_FACTORY = new GetTableTypes_resultStandardSchemeFactory();
      private static final SchemeFactory TUPLE_SCHEME_FACTORY = new GetTableTypes_resultTupleSchemeFactory();
      @Nullable
      private TGetTableTypesResp success;
      public static final Map metaDataMap;

      public GetTableTypes_result() {
      }

      public GetTableTypes_result(TGetTableTypesResp success) {
         this();
         this.success = success;
      }

      public GetTableTypes_result(GetTableTypes_result other) {
         if (other.isSetSuccess()) {
            this.success = new TGetTableTypesResp(other.success);
         }

      }

      public GetTableTypes_result deepCopy() {
         return new GetTableTypes_result(this);
      }

      public void clear() {
         this.success = null;
      }

      @Nullable
      public TGetTableTypesResp getSuccess() {
         return this.success;
      }

      public void setSuccess(@Nullable TGetTableTypesResp success) {
         this.success = success;
      }

      public void unsetSuccess() {
         this.success = null;
      }

      public boolean isSetSuccess() {
         return this.success != null;
      }

      public void setSuccessIsSet(boolean value) {
         if (!value) {
            this.success = null;
         }

      }

      public void setFieldValue(_Fields field, @Nullable Object value) {
         switch (field) {
            case SUCCESS:
               if (value == null) {
                  this.unsetSuccess();
               } else {
                  this.setSuccess((TGetTableTypesResp)value);
               }
            default:
         }
      }

      @Nullable
      public Object getFieldValue(_Fields field) {
         switch (field) {
            case SUCCESS:
               return this.getSuccess();
            default:
               throw new IllegalStateException();
         }
      }

      public boolean isSet(_Fields field) {
         if (field == null) {
            throw new IllegalArgumentException();
         } else {
            switch (field) {
               case SUCCESS:
                  return this.isSetSuccess();
               default:
                  throw new IllegalStateException();
            }
         }
      }

      public boolean equals(Object that) {
         return that instanceof GetTableTypes_result ? this.equals((GetTableTypes_result)that) : false;
      }

      public boolean equals(GetTableTypes_result that) {
         if (that == null) {
            return false;
         } else if (this == that) {
            return true;
         } else {
            boolean this_present_success = this.isSetSuccess();
            boolean that_present_success = that.isSetSuccess();
            if (this_present_success || that_present_success) {
               if (!this_present_success || !that_present_success) {
                  return false;
               }

               if (!this.success.equals(that.success)) {
                  return false;
               }
            }

            return true;
         }
      }

      public int hashCode() {
         int hashCode = 1;
         hashCode = hashCode * 8191 + (this.isSetSuccess() ? 131071 : 524287);
         if (this.isSetSuccess()) {
            hashCode = hashCode * 8191 + this.success.hashCode();
         }

         return hashCode;
      }

      public int compareTo(GetTableTypes_result other) {
         if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
         } else {
            int lastComparison = 0;
            lastComparison = Boolean.compare(this.isSetSuccess(), other.isSetSuccess());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetSuccess()) {
                  lastComparison = TBaseHelper.compareTo(this.success, other.success);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               return 0;
            }
         }
      }

      @Nullable
      public _Fields fieldForId(int fieldId) {
         return TCLIService.GetTableTypes_result._Fields.findByThriftId(fieldId);
      }

      public void read(TProtocol iprot) throws TException {
         scheme(iprot).read(iprot, this);
      }

      public void write(TProtocol oprot) throws TException {
         scheme(oprot).write(oprot, this);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("GetTableTypes_result(");
         boolean first = true;
         sb.append("success:");
         if (this.success == null) {
            sb.append("null");
         } else {
            sb.append(this.success);
         }

         first = false;
         sb.append(")");
         return sb.toString();
      }

      public void validate() throws TException {
         if (this.success != null) {
            this.success.validate();
         }

      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         try {
            this.write(new TCompactProtocol(new TIOStreamTransport(out)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         try {
            this.read(new TCompactProtocol(new TIOStreamTransport(in)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private static IScheme scheme(TProtocol proto) {
         return (StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
      }

      static {
         Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
         tmpMap.put(TCLIService.GetTableTypes_result._Fields.SUCCESS, new FieldMetaData("success", (byte)3, new StructMetaData((byte)12, TGetTableTypesResp.class)));
         metaDataMap = Collections.unmodifiableMap(tmpMap);
         FieldMetaData.addStructMetaDataMap(GetTableTypes_result.class, metaDataMap);
      }

      public static enum _Fields implements TFieldIdEnum {
         SUCCESS((short)0, "success");

         private static final Map byName = new HashMap();
         private final short _thriftId;
         private final String _fieldName;

         @Nullable
         public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
               case 0:
                  return SUCCESS;
               default:
                  return null;
            }
         }

         public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) {
               throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            } else {
               return fields;
            }
         }

         @Nullable
         public static _Fields findByName(String name) {
            return (_Fields)byName.get(name);
         }

         private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
         }

         public short getThriftFieldId() {
            return this._thriftId;
         }

         public String getFieldName() {
            return this._fieldName;
         }

         static {
            for(_Fields field : EnumSet.allOf(_Fields.class)) {
               byName.put(field.getFieldName(), field);
            }

         }
      }

      private static class GetTableTypes_resultStandardSchemeFactory implements SchemeFactory {
         private GetTableTypes_resultStandardSchemeFactory() {
         }

         public GetTableTypes_resultStandardScheme getScheme() {
            return new GetTableTypes_resultStandardScheme();
         }
      }

      private static class GetTableTypes_resultStandardScheme extends StandardScheme {
         private GetTableTypes_resultStandardScheme() {
         }

         public void read(TProtocol iprot, GetTableTypes_result struct) throws TException {
            iprot.readStructBegin();

            while(true) {
               TField schemeField = iprot.readFieldBegin();
               if (schemeField.type == 0) {
                  iprot.readStructEnd();
                  struct.validate();
                  return;
               }

               switch (schemeField.id) {
                  case 0:
                     if (schemeField.type == 12) {
                        struct.success = new TGetTableTypesResp();
                        struct.success.read(iprot);
                        struct.setSuccessIsSet(true);
                     } else {
                        TProtocolUtil.skip(iprot, schemeField.type);
                     }
                     break;
                  default:
                     TProtocolUtil.skip(iprot, schemeField.type);
               }

               iprot.readFieldEnd();
            }
         }

         public void write(TProtocol oprot, GetTableTypes_result struct) throws TException {
            struct.validate();
            oprot.writeStructBegin(TCLIService.GetTableTypes_result.STRUCT_DESC);
            if (struct.success != null) {
               oprot.writeFieldBegin(TCLIService.GetTableTypes_result.SUCCESS_FIELD_DESC);
               struct.success.write(oprot);
               oprot.writeFieldEnd();
            }

            oprot.writeFieldStop();
            oprot.writeStructEnd();
         }
      }

      private static class GetTableTypes_resultTupleSchemeFactory implements SchemeFactory {
         private GetTableTypes_resultTupleSchemeFactory() {
         }

         public GetTableTypes_resultTupleScheme getScheme() {
            return new GetTableTypes_resultTupleScheme();
         }
      }

      private static class GetTableTypes_resultTupleScheme extends TupleScheme {
         private GetTableTypes_resultTupleScheme() {
         }

         public void write(TProtocol prot, GetTableTypes_result struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol)prot;
            BitSet optionals = new BitSet();
            if (struct.isSetSuccess()) {
               optionals.set(0);
            }

            oprot.writeBitSet(optionals, 1);
            if (struct.isSetSuccess()) {
               struct.success.write(oprot);
            }

         }

         public void read(TProtocol prot, GetTableTypes_result struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol)prot;
            BitSet incoming = iprot.readBitSet(1);
            if (incoming.get(0)) {
               struct.success = new TGetTableTypesResp();
               struct.success.read(iprot);
               struct.setSuccessIsSet(true);
            }

         }
      }
   }

   @Public
   @Stable
   public static class GetColumns_args implements TBase, Serializable, Cloneable, Comparable {
      private static final TStruct STRUCT_DESC = new TStruct("GetColumns_args");
      private static final TField REQ_FIELD_DESC = new TField("req", (byte)12, (short)1);
      private static final SchemeFactory STANDARD_SCHEME_FACTORY = new GetColumns_argsStandardSchemeFactory();
      private static final SchemeFactory TUPLE_SCHEME_FACTORY = new GetColumns_argsTupleSchemeFactory();
      @Nullable
      private TGetColumnsReq req;
      public static final Map metaDataMap;

      public GetColumns_args() {
      }

      public GetColumns_args(TGetColumnsReq req) {
         this();
         this.req = req;
      }

      public GetColumns_args(GetColumns_args other) {
         if (other.isSetReq()) {
            this.req = new TGetColumnsReq(other.req);
         }

      }

      public GetColumns_args deepCopy() {
         return new GetColumns_args(this);
      }

      public void clear() {
         this.req = null;
      }

      @Nullable
      public TGetColumnsReq getReq() {
         return this.req;
      }

      public void setReq(@Nullable TGetColumnsReq req) {
         this.req = req;
      }

      public void unsetReq() {
         this.req = null;
      }

      public boolean isSetReq() {
         return this.req != null;
      }

      public void setReqIsSet(boolean value) {
         if (!value) {
            this.req = null;
         }

      }

      public void setFieldValue(_Fields field, @Nullable Object value) {
         switch (field) {
            case REQ:
               if (value == null) {
                  this.unsetReq();
               } else {
                  this.setReq((TGetColumnsReq)value);
               }
            default:
         }
      }

      @Nullable
      public Object getFieldValue(_Fields field) {
         switch (field) {
            case REQ:
               return this.getReq();
            default:
               throw new IllegalStateException();
         }
      }

      public boolean isSet(_Fields field) {
         if (field == null) {
            throw new IllegalArgumentException();
         } else {
            switch (field) {
               case REQ:
                  return this.isSetReq();
               default:
                  throw new IllegalStateException();
            }
         }
      }

      public boolean equals(Object that) {
         return that instanceof GetColumns_args ? this.equals((GetColumns_args)that) : false;
      }

      public boolean equals(GetColumns_args that) {
         if (that == null) {
            return false;
         } else if (this == that) {
            return true;
         } else {
            boolean this_present_req = this.isSetReq();
            boolean that_present_req = that.isSetReq();
            if (this_present_req || that_present_req) {
               if (!this_present_req || !that_present_req) {
                  return false;
               }

               if (!this.req.equals(that.req)) {
                  return false;
               }
            }

            return true;
         }
      }

      public int hashCode() {
         int hashCode = 1;
         hashCode = hashCode * 8191 + (this.isSetReq() ? 131071 : 524287);
         if (this.isSetReq()) {
            hashCode = hashCode * 8191 + this.req.hashCode();
         }

         return hashCode;
      }

      public int compareTo(GetColumns_args other) {
         if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
         } else {
            int lastComparison = 0;
            lastComparison = Boolean.compare(this.isSetReq(), other.isSetReq());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetReq()) {
                  lastComparison = TBaseHelper.compareTo(this.req, other.req);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               return 0;
            }
         }
      }

      @Nullable
      public _Fields fieldForId(int fieldId) {
         return TCLIService.GetColumns_args._Fields.findByThriftId(fieldId);
      }

      public void read(TProtocol iprot) throws TException {
         scheme(iprot).read(iprot, this);
      }

      public void write(TProtocol oprot) throws TException {
         scheme(oprot).write(oprot, this);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("GetColumns_args(");
         boolean first = true;
         sb.append("req:");
         if (this.req == null) {
            sb.append("null");
         } else {
            sb.append(this.req);
         }

         first = false;
         sb.append(")");
         return sb.toString();
      }

      public void validate() throws TException {
         if (this.req != null) {
            this.req.validate();
         }

      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         try {
            this.write(new TCompactProtocol(new TIOStreamTransport(out)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         try {
            this.read(new TCompactProtocol(new TIOStreamTransport(in)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private static IScheme scheme(TProtocol proto) {
         return (StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
      }

      static {
         Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
         tmpMap.put(TCLIService.GetColumns_args._Fields.REQ, new FieldMetaData("req", (byte)3, new StructMetaData((byte)12, TGetColumnsReq.class)));
         metaDataMap = Collections.unmodifiableMap(tmpMap);
         FieldMetaData.addStructMetaDataMap(GetColumns_args.class, metaDataMap);
      }

      public static enum _Fields implements TFieldIdEnum {
         REQ((short)1, "req");

         private static final Map byName = new HashMap();
         private final short _thriftId;
         private final String _fieldName;

         @Nullable
         public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
               case 1:
                  return REQ;
               default:
                  return null;
            }
         }

         public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) {
               throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            } else {
               return fields;
            }
         }

         @Nullable
         public static _Fields findByName(String name) {
            return (_Fields)byName.get(name);
         }

         private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
         }

         public short getThriftFieldId() {
            return this._thriftId;
         }

         public String getFieldName() {
            return this._fieldName;
         }

         static {
            for(_Fields field : EnumSet.allOf(_Fields.class)) {
               byName.put(field.getFieldName(), field);
            }

         }
      }

      private static class GetColumns_argsStandardSchemeFactory implements SchemeFactory {
         private GetColumns_argsStandardSchemeFactory() {
         }

         public GetColumns_argsStandardScheme getScheme() {
            return new GetColumns_argsStandardScheme();
         }
      }

      private static class GetColumns_argsStandardScheme extends StandardScheme {
         private GetColumns_argsStandardScheme() {
         }

         public void read(TProtocol iprot, GetColumns_args struct) throws TException {
            iprot.readStructBegin();

            while(true) {
               TField schemeField = iprot.readFieldBegin();
               if (schemeField.type == 0) {
                  iprot.readStructEnd();
                  struct.validate();
                  return;
               }

               switch (schemeField.id) {
                  case 1:
                     if (schemeField.type == 12) {
                        struct.req = new TGetColumnsReq();
                        struct.req.read(iprot);
                        struct.setReqIsSet(true);
                     } else {
                        TProtocolUtil.skip(iprot, schemeField.type);
                     }
                     break;
                  default:
                     TProtocolUtil.skip(iprot, schemeField.type);
               }

               iprot.readFieldEnd();
            }
         }

         public void write(TProtocol oprot, GetColumns_args struct) throws TException {
            struct.validate();
            oprot.writeStructBegin(TCLIService.GetColumns_args.STRUCT_DESC);
            if (struct.req != null) {
               oprot.writeFieldBegin(TCLIService.GetColumns_args.REQ_FIELD_DESC);
               struct.req.write(oprot);
               oprot.writeFieldEnd();
            }

            oprot.writeFieldStop();
            oprot.writeStructEnd();
         }
      }

      private static class GetColumns_argsTupleSchemeFactory implements SchemeFactory {
         private GetColumns_argsTupleSchemeFactory() {
         }

         public GetColumns_argsTupleScheme getScheme() {
            return new GetColumns_argsTupleScheme();
         }
      }

      private static class GetColumns_argsTupleScheme extends TupleScheme {
         private GetColumns_argsTupleScheme() {
         }

         public void write(TProtocol prot, GetColumns_args struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol)prot;
            BitSet optionals = new BitSet();
            if (struct.isSetReq()) {
               optionals.set(0);
            }

            oprot.writeBitSet(optionals, 1);
            if (struct.isSetReq()) {
               struct.req.write(oprot);
            }

         }

         public void read(TProtocol prot, GetColumns_args struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol)prot;
            BitSet incoming = iprot.readBitSet(1);
            if (incoming.get(0)) {
               struct.req = new TGetColumnsReq();
               struct.req.read(iprot);
               struct.setReqIsSet(true);
            }

         }
      }
   }

   @Public
   @Stable
   public static class GetColumns_result implements TBase, Serializable, Cloneable, Comparable {
      private static final TStruct STRUCT_DESC = new TStruct("GetColumns_result");
      private static final TField SUCCESS_FIELD_DESC = new TField("success", (byte)12, (short)0);
      private static final SchemeFactory STANDARD_SCHEME_FACTORY = new GetColumns_resultStandardSchemeFactory();
      private static final SchemeFactory TUPLE_SCHEME_FACTORY = new GetColumns_resultTupleSchemeFactory();
      @Nullable
      private TGetColumnsResp success;
      public static final Map metaDataMap;

      public GetColumns_result() {
      }

      public GetColumns_result(TGetColumnsResp success) {
         this();
         this.success = success;
      }

      public GetColumns_result(GetColumns_result other) {
         if (other.isSetSuccess()) {
            this.success = new TGetColumnsResp(other.success);
         }

      }

      public GetColumns_result deepCopy() {
         return new GetColumns_result(this);
      }

      public void clear() {
         this.success = null;
      }

      @Nullable
      public TGetColumnsResp getSuccess() {
         return this.success;
      }

      public void setSuccess(@Nullable TGetColumnsResp success) {
         this.success = success;
      }

      public void unsetSuccess() {
         this.success = null;
      }

      public boolean isSetSuccess() {
         return this.success != null;
      }

      public void setSuccessIsSet(boolean value) {
         if (!value) {
            this.success = null;
         }

      }

      public void setFieldValue(_Fields field, @Nullable Object value) {
         switch (field) {
            case SUCCESS:
               if (value == null) {
                  this.unsetSuccess();
               } else {
                  this.setSuccess((TGetColumnsResp)value);
               }
            default:
         }
      }

      @Nullable
      public Object getFieldValue(_Fields field) {
         switch (field) {
            case SUCCESS:
               return this.getSuccess();
            default:
               throw new IllegalStateException();
         }
      }

      public boolean isSet(_Fields field) {
         if (field == null) {
            throw new IllegalArgumentException();
         } else {
            switch (field) {
               case SUCCESS:
                  return this.isSetSuccess();
               default:
                  throw new IllegalStateException();
            }
         }
      }

      public boolean equals(Object that) {
         return that instanceof GetColumns_result ? this.equals((GetColumns_result)that) : false;
      }

      public boolean equals(GetColumns_result that) {
         if (that == null) {
            return false;
         } else if (this == that) {
            return true;
         } else {
            boolean this_present_success = this.isSetSuccess();
            boolean that_present_success = that.isSetSuccess();
            if (this_present_success || that_present_success) {
               if (!this_present_success || !that_present_success) {
                  return false;
               }

               if (!this.success.equals(that.success)) {
                  return false;
               }
            }

            return true;
         }
      }

      public int hashCode() {
         int hashCode = 1;
         hashCode = hashCode * 8191 + (this.isSetSuccess() ? 131071 : 524287);
         if (this.isSetSuccess()) {
            hashCode = hashCode * 8191 + this.success.hashCode();
         }

         return hashCode;
      }

      public int compareTo(GetColumns_result other) {
         if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
         } else {
            int lastComparison = 0;
            lastComparison = Boolean.compare(this.isSetSuccess(), other.isSetSuccess());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetSuccess()) {
                  lastComparison = TBaseHelper.compareTo(this.success, other.success);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               return 0;
            }
         }
      }

      @Nullable
      public _Fields fieldForId(int fieldId) {
         return TCLIService.GetColumns_result._Fields.findByThriftId(fieldId);
      }

      public void read(TProtocol iprot) throws TException {
         scheme(iprot).read(iprot, this);
      }

      public void write(TProtocol oprot) throws TException {
         scheme(oprot).write(oprot, this);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("GetColumns_result(");
         boolean first = true;
         sb.append("success:");
         if (this.success == null) {
            sb.append("null");
         } else {
            sb.append(this.success);
         }

         first = false;
         sb.append(")");
         return sb.toString();
      }

      public void validate() throws TException {
         if (this.success != null) {
            this.success.validate();
         }

      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         try {
            this.write(new TCompactProtocol(new TIOStreamTransport(out)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         try {
            this.read(new TCompactProtocol(new TIOStreamTransport(in)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private static IScheme scheme(TProtocol proto) {
         return (StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
      }

      static {
         Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
         tmpMap.put(TCLIService.GetColumns_result._Fields.SUCCESS, new FieldMetaData("success", (byte)3, new StructMetaData((byte)12, TGetColumnsResp.class)));
         metaDataMap = Collections.unmodifiableMap(tmpMap);
         FieldMetaData.addStructMetaDataMap(GetColumns_result.class, metaDataMap);
      }

      public static enum _Fields implements TFieldIdEnum {
         SUCCESS((short)0, "success");

         private static final Map byName = new HashMap();
         private final short _thriftId;
         private final String _fieldName;

         @Nullable
         public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
               case 0:
                  return SUCCESS;
               default:
                  return null;
            }
         }

         public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) {
               throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            } else {
               return fields;
            }
         }

         @Nullable
         public static _Fields findByName(String name) {
            return (_Fields)byName.get(name);
         }

         private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
         }

         public short getThriftFieldId() {
            return this._thriftId;
         }

         public String getFieldName() {
            return this._fieldName;
         }

         static {
            for(_Fields field : EnumSet.allOf(_Fields.class)) {
               byName.put(field.getFieldName(), field);
            }

         }
      }

      private static class GetColumns_resultStandardSchemeFactory implements SchemeFactory {
         private GetColumns_resultStandardSchemeFactory() {
         }

         public GetColumns_resultStandardScheme getScheme() {
            return new GetColumns_resultStandardScheme();
         }
      }

      private static class GetColumns_resultStandardScheme extends StandardScheme {
         private GetColumns_resultStandardScheme() {
         }

         public void read(TProtocol iprot, GetColumns_result struct) throws TException {
            iprot.readStructBegin();

            while(true) {
               TField schemeField = iprot.readFieldBegin();
               if (schemeField.type == 0) {
                  iprot.readStructEnd();
                  struct.validate();
                  return;
               }

               switch (schemeField.id) {
                  case 0:
                     if (schemeField.type == 12) {
                        struct.success = new TGetColumnsResp();
                        struct.success.read(iprot);
                        struct.setSuccessIsSet(true);
                     } else {
                        TProtocolUtil.skip(iprot, schemeField.type);
                     }
                     break;
                  default:
                     TProtocolUtil.skip(iprot, schemeField.type);
               }

               iprot.readFieldEnd();
            }
         }

         public void write(TProtocol oprot, GetColumns_result struct) throws TException {
            struct.validate();
            oprot.writeStructBegin(TCLIService.GetColumns_result.STRUCT_DESC);
            if (struct.success != null) {
               oprot.writeFieldBegin(TCLIService.GetColumns_result.SUCCESS_FIELD_DESC);
               struct.success.write(oprot);
               oprot.writeFieldEnd();
            }

            oprot.writeFieldStop();
            oprot.writeStructEnd();
         }
      }

      private static class GetColumns_resultTupleSchemeFactory implements SchemeFactory {
         private GetColumns_resultTupleSchemeFactory() {
         }

         public GetColumns_resultTupleScheme getScheme() {
            return new GetColumns_resultTupleScheme();
         }
      }

      private static class GetColumns_resultTupleScheme extends TupleScheme {
         private GetColumns_resultTupleScheme() {
         }

         public void write(TProtocol prot, GetColumns_result struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol)prot;
            BitSet optionals = new BitSet();
            if (struct.isSetSuccess()) {
               optionals.set(0);
            }

            oprot.writeBitSet(optionals, 1);
            if (struct.isSetSuccess()) {
               struct.success.write(oprot);
            }

         }

         public void read(TProtocol prot, GetColumns_result struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol)prot;
            BitSet incoming = iprot.readBitSet(1);
            if (incoming.get(0)) {
               struct.success = new TGetColumnsResp();
               struct.success.read(iprot);
               struct.setSuccessIsSet(true);
            }

         }
      }
   }

   @Public
   @Stable
   public static class GetFunctions_args implements TBase, Serializable, Cloneable, Comparable {
      private static final TStruct STRUCT_DESC = new TStruct("GetFunctions_args");
      private static final TField REQ_FIELD_DESC = new TField("req", (byte)12, (short)1);
      private static final SchemeFactory STANDARD_SCHEME_FACTORY = new GetFunctions_argsStandardSchemeFactory();
      private static final SchemeFactory TUPLE_SCHEME_FACTORY = new GetFunctions_argsTupleSchemeFactory();
      @Nullable
      private TGetFunctionsReq req;
      public static final Map metaDataMap;

      public GetFunctions_args() {
      }

      public GetFunctions_args(TGetFunctionsReq req) {
         this();
         this.req = req;
      }

      public GetFunctions_args(GetFunctions_args other) {
         if (other.isSetReq()) {
            this.req = new TGetFunctionsReq(other.req);
         }

      }

      public GetFunctions_args deepCopy() {
         return new GetFunctions_args(this);
      }

      public void clear() {
         this.req = null;
      }

      @Nullable
      public TGetFunctionsReq getReq() {
         return this.req;
      }

      public void setReq(@Nullable TGetFunctionsReq req) {
         this.req = req;
      }

      public void unsetReq() {
         this.req = null;
      }

      public boolean isSetReq() {
         return this.req != null;
      }

      public void setReqIsSet(boolean value) {
         if (!value) {
            this.req = null;
         }

      }

      public void setFieldValue(_Fields field, @Nullable Object value) {
         switch (field) {
            case REQ:
               if (value == null) {
                  this.unsetReq();
               } else {
                  this.setReq((TGetFunctionsReq)value);
               }
            default:
         }
      }

      @Nullable
      public Object getFieldValue(_Fields field) {
         switch (field) {
            case REQ:
               return this.getReq();
            default:
               throw new IllegalStateException();
         }
      }

      public boolean isSet(_Fields field) {
         if (field == null) {
            throw new IllegalArgumentException();
         } else {
            switch (field) {
               case REQ:
                  return this.isSetReq();
               default:
                  throw new IllegalStateException();
            }
         }
      }

      public boolean equals(Object that) {
         return that instanceof GetFunctions_args ? this.equals((GetFunctions_args)that) : false;
      }

      public boolean equals(GetFunctions_args that) {
         if (that == null) {
            return false;
         } else if (this == that) {
            return true;
         } else {
            boolean this_present_req = this.isSetReq();
            boolean that_present_req = that.isSetReq();
            if (this_present_req || that_present_req) {
               if (!this_present_req || !that_present_req) {
                  return false;
               }

               if (!this.req.equals(that.req)) {
                  return false;
               }
            }

            return true;
         }
      }

      public int hashCode() {
         int hashCode = 1;
         hashCode = hashCode * 8191 + (this.isSetReq() ? 131071 : 524287);
         if (this.isSetReq()) {
            hashCode = hashCode * 8191 + this.req.hashCode();
         }

         return hashCode;
      }

      public int compareTo(GetFunctions_args other) {
         if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
         } else {
            int lastComparison = 0;
            lastComparison = Boolean.compare(this.isSetReq(), other.isSetReq());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetReq()) {
                  lastComparison = TBaseHelper.compareTo(this.req, other.req);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               return 0;
            }
         }
      }

      @Nullable
      public _Fields fieldForId(int fieldId) {
         return TCLIService.GetFunctions_args._Fields.findByThriftId(fieldId);
      }

      public void read(TProtocol iprot) throws TException {
         scheme(iprot).read(iprot, this);
      }

      public void write(TProtocol oprot) throws TException {
         scheme(oprot).write(oprot, this);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("GetFunctions_args(");
         boolean first = true;
         sb.append("req:");
         if (this.req == null) {
            sb.append("null");
         } else {
            sb.append(this.req);
         }

         first = false;
         sb.append(")");
         return sb.toString();
      }

      public void validate() throws TException {
         if (this.req != null) {
            this.req.validate();
         }

      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         try {
            this.write(new TCompactProtocol(new TIOStreamTransport(out)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         try {
            this.read(new TCompactProtocol(new TIOStreamTransport(in)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private static IScheme scheme(TProtocol proto) {
         return (StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
      }

      static {
         Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
         tmpMap.put(TCLIService.GetFunctions_args._Fields.REQ, new FieldMetaData("req", (byte)3, new StructMetaData((byte)12, TGetFunctionsReq.class)));
         metaDataMap = Collections.unmodifiableMap(tmpMap);
         FieldMetaData.addStructMetaDataMap(GetFunctions_args.class, metaDataMap);
      }

      public static enum _Fields implements TFieldIdEnum {
         REQ((short)1, "req");

         private static final Map byName = new HashMap();
         private final short _thriftId;
         private final String _fieldName;

         @Nullable
         public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
               case 1:
                  return REQ;
               default:
                  return null;
            }
         }

         public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) {
               throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            } else {
               return fields;
            }
         }

         @Nullable
         public static _Fields findByName(String name) {
            return (_Fields)byName.get(name);
         }

         private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
         }

         public short getThriftFieldId() {
            return this._thriftId;
         }

         public String getFieldName() {
            return this._fieldName;
         }

         static {
            for(_Fields field : EnumSet.allOf(_Fields.class)) {
               byName.put(field.getFieldName(), field);
            }

         }
      }

      private static class GetFunctions_argsStandardSchemeFactory implements SchemeFactory {
         private GetFunctions_argsStandardSchemeFactory() {
         }

         public GetFunctions_argsStandardScheme getScheme() {
            return new GetFunctions_argsStandardScheme();
         }
      }

      private static class GetFunctions_argsStandardScheme extends StandardScheme {
         private GetFunctions_argsStandardScheme() {
         }

         public void read(TProtocol iprot, GetFunctions_args struct) throws TException {
            iprot.readStructBegin();

            while(true) {
               TField schemeField = iprot.readFieldBegin();
               if (schemeField.type == 0) {
                  iprot.readStructEnd();
                  struct.validate();
                  return;
               }

               switch (schemeField.id) {
                  case 1:
                     if (schemeField.type == 12) {
                        struct.req = new TGetFunctionsReq();
                        struct.req.read(iprot);
                        struct.setReqIsSet(true);
                     } else {
                        TProtocolUtil.skip(iprot, schemeField.type);
                     }
                     break;
                  default:
                     TProtocolUtil.skip(iprot, schemeField.type);
               }

               iprot.readFieldEnd();
            }
         }

         public void write(TProtocol oprot, GetFunctions_args struct) throws TException {
            struct.validate();
            oprot.writeStructBegin(TCLIService.GetFunctions_args.STRUCT_DESC);
            if (struct.req != null) {
               oprot.writeFieldBegin(TCLIService.GetFunctions_args.REQ_FIELD_DESC);
               struct.req.write(oprot);
               oprot.writeFieldEnd();
            }

            oprot.writeFieldStop();
            oprot.writeStructEnd();
         }
      }

      private static class GetFunctions_argsTupleSchemeFactory implements SchemeFactory {
         private GetFunctions_argsTupleSchemeFactory() {
         }

         public GetFunctions_argsTupleScheme getScheme() {
            return new GetFunctions_argsTupleScheme();
         }
      }

      private static class GetFunctions_argsTupleScheme extends TupleScheme {
         private GetFunctions_argsTupleScheme() {
         }

         public void write(TProtocol prot, GetFunctions_args struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol)prot;
            BitSet optionals = new BitSet();
            if (struct.isSetReq()) {
               optionals.set(0);
            }

            oprot.writeBitSet(optionals, 1);
            if (struct.isSetReq()) {
               struct.req.write(oprot);
            }

         }

         public void read(TProtocol prot, GetFunctions_args struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol)prot;
            BitSet incoming = iprot.readBitSet(1);
            if (incoming.get(0)) {
               struct.req = new TGetFunctionsReq();
               struct.req.read(iprot);
               struct.setReqIsSet(true);
            }

         }
      }
   }

   @Public
   @Stable
   public static class GetFunctions_result implements TBase, Serializable, Cloneable, Comparable {
      private static final TStruct STRUCT_DESC = new TStruct("GetFunctions_result");
      private static final TField SUCCESS_FIELD_DESC = new TField("success", (byte)12, (short)0);
      private static final SchemeFactory STANDARD_SCHEME_FACTORY = new GetFunctions_resultStandardSchemeFactory();
      private static final SchemeFactory TUPLE_SCHEME_FACTORY = new GetFunctions_resultTupleSchemeFactory();
      @Nullable
      private TGetFunctionsResp success;
      public static final Map metaDataMap;

      public GetFunctions_result() {
      }

      public GetFunctions_result(TGetFunctionsResp success) {
         this();
         this.success = success;
      }

      public GetFunctions_result(GetFunctions_result other) {
         if (other.isSetSuccess()) {
            this.success = new TGetFunctionsResp(other.success);
         }

      }

      public GetFunctions_result deepCopy() {
         return new GetFunctions_result(this);
      }

      public void clear() {
         this.success = null;
      }

      @Nullable
      public TGetFunctionsResp getSuccess() {
         return this.success;
      }

      public void setSuccess(@Nullable TGetFunctionsResp success) {
         this.success = success;
      }

      public void unsetSuccess() {
         this.success = null;
      }

      public boolean isSetSuccess() {
         return this.success != null;
      }

      public void setSuccessIsSet(boolean value) {
         if (!value) {
            this.success = null;
         }

      }

      public void setFieldValue(_Fields field, @Nullable Object value) {
         switch (field) {
            case SUCCESS:
               if (value == null) {
                  this.unsetSuccess();
               } else {
                  this.setSuccess((TGetFunctionsResp)value);
               }
            default:
         }
      }

      @Nullable
      public Object getFieldValue(_Fields field) {
         switch (field) {
            case SUCCESS:
               return this.getSuccess();
            default:
               throw new IllegalStateException();
         }
      }

      public boolean isSet(_Fields field) {
         if (field == null) {
            throw new IllegalArgumentException();
         } else {
            switch (field) {
               case SUCCESS:
                  return this.isSetSuccess();
               default:
                  throw new IllegalStateException();
            }
         }
      }

      public boolean equals(Object that) {
         return that instanceof GetFunctions_result ? this.equals((GetFunctions_result)that) : false;
      }

      public boolean equals(GetFunctions_result that) {
         if (that == null) {
            return false;
         } else if (this == that) {
            return true;
         } else {
            boolean this_present_success = this.isSetSuccess();
            boolean that_present_success = that.isSetSuccess();
            if (this_present_success || that_present_success) {
               if (!this_present_success || !that_present_success) {
                  return false;
               }

               if (!this.success.equals(that.success)) {
                  return false;
               }
            }

            return true;
         }
      }

      public int hashCode() {
         int hashCode = 1;
         hashCode = hashCode * 8191 + (this.isSetSuccess() ? 131071 : 524287);
         if (this.isSetSuccess()) {
            hashCode = hashCode * 8191 + this.success.hashCode();
         }

         return hashCode;
      }

      public int compareTo(GetFunctions_result other) {
         if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
         } else {
            int lastComparison = 0;
            lastComparison = Boolean.compare(this.isSetSuccess(), other.isSetSuccess());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetSuccess()) {
                  lastComparison = TBaseHelper.compareTo(this.success, other.success);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               return 0;
            }
         }
      }

      @Nullable
      public _Fields fieldForId(int fieldId) {
         return TCLIService.GetFunctions_result._Fields.findByThriftId(fieldId);
      }

      public void read(TProtocol iprot) throws TException {
         scheme(iprot).read(iprot, this);
      }

      public void write(TProtocol oprot) throws TException {
         scheme(oprot).write(oprot, this);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("GetFunctions_result(");
         boolean first = true;
         sb.append("success:");
         if (this.success == null) {
            sb.append("null");
         } else {
            sb.append(this.success);
         }

         first = false;
         sb.append(")");
         return sb.toString();
      }

      public void validate() throws TException {
         if (this.success != null) {
            this.success.validate();
         }

      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         try {
            this.write(new TCompactProtocol(new TIOStreamTransport(out)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         try {
            this.read(new TCompactProtocol(new TIOStreamTransport(in)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private static IScheme scheme(TProtocol proto) {
         return (StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
      }

      static {
         Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
         tmpMap.put(TCLIService.GetFunctions_result._Fields.SUCCESS, new FieldMetaData("success", (byte)3, new StructMetaData((byte)12, TGetFunctionsResp.class)));
         metaDataMap = Collections.unmodifiableMap(tmpMap);
         FieldMetaData.addStructMetaDataMap(GetFunctions_result.class, metaDataMap);
      }

      public static enum _Fields implements TFieldIdEnum {
         SUCCESS((short)0, "success");

         private static final Map byName = new HashMap();
         private final short _thriftId;
         private final String _fieldName;

         @Nullable
         public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
               case 0:
                  return SUCCESS;
               default:
                  return null;
            }
         }

         public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) {
               throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            } else {
               return fields;
            }
         }

         @Nullable
         public static _Fields findByName(String name) {
            return (_Fields)byName.get(name);
         }

         private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
         }

         public short getThriftFieldId() {
            return this._thriftId;
         }

         public String getFieldName() {
            return this._fieldName;
         }

         static {
            for(_Fields field : EnumSet.allOf(_Fields.class)) {
               byName.put(field.getFieldName(), field);
            }

         }
      }

      private static class GetFunctions_resultStandardSchemeFactory implements SchemeFactory {
         private GetFunctions_resultStandardSchemeFactory() {
         }

         public GetFunctions_resultStandardScheme getScheme() {
            return new GetFunctions_resultStandardScheme();
         }
      }

      private static class GetFunctions_resultStandardScheme extends StandardScheme {
         private GetFunctions_resultStandardScheme() {
         }

         public void read(TProtocol iprot, GetFunctions_result struct) throws TException {
            iprot.readStructBegin();

            while(true) {
               TField schemeField = iprot.readFieldBegin();
               if (schemeField.type == 0) {
                  iprot.readStructEnd();
                  struct.validate();
                  return;
               }

               switch (schemeField.id) {
                  case 0:
                     if (schemeField.type == 12) {
                        struct.success = new TGetFunctionsResp();
                        struct.success.read(iprot);
                        struct.setSuccessIsSet(true);
                     } else {
                        TProtocolUtil.skip(iprot, schemeField.type);
                     }
                     break;
                  default:
                     TProtocolUtil.skip(iprot, schemeField.type);
               }

               iprot.readFieldEnd();
            }
         }

         public void write(TProtocol oprot, GetFunctions_result struct) throws TException {
            struct.validate();
            oprot.writeStructBegin(TCLIService.GetFunctions_result.STRUCT_DESC);
            if (struct.success != null) {
               oprot.writeFieldBegin(TCLIService.GetFunctions_result.SUCCESS_FIELD_DESC);
               struct.success.write(oprot);
               oprot.writeFieldEnd();
            }

            oprot.writeFieldStop();
            oprot.writeStructEnd();
         }
      }

      private static class GetFunctions_resultTupleSchemeFactory implements SchemeFactory {
         private GetFunctions_resultTupleSchemeFactory() {
         }

         public GetFunctions_resultTupleScheme getScheme() {
            return new GetFunctions_resultTupleScheme();
         }
      }

      private static class GetFunctions_resultTupleScheme extends TupleScheme {
         private GetFunctions_resultTupleScheme() {
         }

         public void write(TProtocol prot, GetFunctions_result struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol)prot;
            BitSet optionals = new BitSet();
            if (struct.isSetSuccess()) {
               optionals.set(0);
            }

            oprot.writeBitSet(optionals, 1);
            if (struct.isSetSuccess()) {
               struct.success.write(oprot);
            }

         }

         public void read(TProtocol prot, GetFunctions_result struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol)prot;
            BitSet incoming = iprot.readBitSet(1);
            if (incoming.get(0)) {
               struct.success = new TGetFunctionsResp();
               struct.success.read(iprot);
               struct.setSuccessIsSet(true);
            }

         }
      }
   }

   @Public
   @Stable
   public static class GetPrimaryKeys_args implements TBase, Serializable, Cloneable, Comparable {
      private static final TStruct STRUCT_DESC = new TStruct("GetPrimaryKeys_args");
      private static final TField REQ_FIELD_DESC = new TField("req", (byte)12, (short)1);
      private static final SchemeFactory STANDARD_SCHEME_FACTORY = new GetPrimaryKeys_argsStandardSchemeFactory();
      private static final SchemeFactory TUPLE_SCHEME_FACTORY = new GetPrimaryKeys_argsTupleSchemeFactory();
      @Nullable
      private TGetPrimaryKeysReq req;
      public static final Map metaDataMap;

      public GetPrimaryKeys_args() {
      }

      public GetPrimaryKeys_args(TGetPrimaryKeysReq req) {
         this();
         this.req = req;
      }

      public GetPrimaryKeys_args(GetPrimaryKeys_args other) {
         if (other.isSetReq()) {
            this.req = new TGetPrimaryKeysReq(other.req);
         }

      }

      public GetPrimaryKeys_args deepCopy() {
         return new GetPrimaryKeys_args(this);
      }

      public void clear() {
         this.req = null;
      }

      @Nullable
      public TGetPrimaryKeysReq getReq() {
         return this.req;
      }

      public void setReq(@Nullable TGetPrimaryKeysReq req) {
         this.req = req;
      }

      public void unsetReq() {
         this.req = null;
      }

      public boolean isSetReq() {
         return this.req != null;
      }

      public void setReqIsSet(boolean value) {
         if (!value) {
            this.req = null;
         }

      }

      public void setFieldValue(_Fields field, @Nullable Object value) {
         switch (field) {
            case REQ:
               if (value == null) {
                  this.unsetReq();
               } else {
                  this.setReq((TGetPrimaryKeysReq)value);
               }
            default:
         }
      }

      @Nullable
      public Object getFieldValue(_Fields field) {
         switch (field) {
            case REQ:
               return this.getReq();
            default:
               throw new IllegalStateException();
         }
      }

      public boolean isSet(_Fields field) {
         if (field == null) {
            throw new IllegalArgumentException();
         } else {
            switch (field) {
               case REQ:
                  return this.isSetReq();
               default:
                  throw new IllegalStateException();
            }
         }
      }

      public boolean equals(Object that) {
         return that instanceof GetPrimaryKeys_args ? this.equals((GetPrimaryKeys_args)that) : false;
      }

      public boolean equals(GetPrimaryKeys_args that) {
         if (that == null) {
            return false;
         } else if (this == that) {
            return true;
         } else {
            boolean this_present_req = this.isSetReq();
            boolean that_present_req = that.isSetReq();
            if (this_present_req || that_present_req) {
               if (!this_present_req || !that_present_req) {
                  return false;
               }

               if (!this.req.equals(that.req)) {
                  return false;
               }
            }

            return true;
         }
      }

      public int hashCode() {
         int hashCode = 1;
         hashCode = hashCode * 8191 + (this.isSetReq() ? 131071 : 524287);
         if (this.isSetReq()) {
            hashCode = hashCode * 8191 + this.req.hashCode();
         }

         return hashCode;
      }

      public int compareTo(GetPrimaryKeys_args other) {
         if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
         } else {
            int lastComparison = 0;
            lastComparison = Boolean.compare(this.isSetReq(), other.isSetReq());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetReq()) {
                  lastComparison = TBaseHelper.compareTo(this.req, other.req);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               return 0;
            }
         }
      }

      @Nullable
      public _Fields fieldForId(int fieldId) {
         return TCLIService.GetPrimaryKeys_args._Fields.findByThriftId(fieldId);
      }

      public void read(TProtocol iprot) throws TException {
         scheme(iprot).read(iprot, this);
      }

      public void write(TProtocol oprot) throws TException {
         scheme(oprot).write(oprot, this);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("GetPrimaryKeys_args(");
         boolean first = true;
         sb.append("req:");
         if (this.req == null) {
            sb.append("null");
         } else {
            sb.append(this.req);
         }

         first = false;
         sb.append(")");
         return sb.toString();
      }

      public void validate() throws TException {
         if (this.req != null) {
            this.req.validate();
         }

      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         try {
            this.write(new TCompactProtocol(new TIOStreamTransport(out)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         try {
            this.read(new TCompactProtocol(new TIOStreamTransport(in)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private static IScheme scheme(TProtocol proto) {
         return (StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
      }

      static {
         Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
         tmpMap.put(TCLIService.GetPrimaryKeys_args._Fields.REQ, new FieldMetaData("req", (byte)3, new StructMetaData((byte)12, TGetPrimaryKeysReq.class)));
         metaDataMap = Collections.unmodifiableMap(tmpMap);
         FieldMetaData.addStructMetaDataMap(GetPrimaryKeys_args.class, metaDataMap);
      }

      public static enum _Fields implements TFieldIdEnum {
         REQ((short)1, "req");

         private static final Map byName = new HashMap();
         private final short _thriftId;
         private final String _fieldName;

         @Nullable
         public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
               case 1:
                  return REQ;
               default:
                  return null;
            }
         }

         public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) {
               throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            } else {
               return fields;
            }
         }

         @Nullable
         public static _Fields findByName(String name) {
            return (_Fields)byName.get(name);
         }

         private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
         }

         public short getThriftFieldId() {
            return this._thriftId;
         }

         public String getFieldName() {
            return this._fieldName;
         }

         static {
            for(_Fields field : EnumSet.allOf(_Fields.class)) {
               byName.put(field.getFieldName(), field);
            }

         }
      }

      private static class GetPrimaryKeys_argsStandardSchemeFactory implements SchemeFactory {
         private GetPrimaryKeys_argsStandardSchemeFactory() {
         }

         public GetPrimaryKeys_argsStandardScheme getScheme() {
            return new GetPrimaryKeys_argsStandardScheme();
         }
      }

      private static class GetPrimaryKeys_argsStandardScheme extends StandardScheme {
         private GetPrimaryKeys_argsStandardScheme() {
         }

         public void read(TProtocol iprot, GetPrimaryKeys_args struct) throws TException {
            iprot.readStructBegin();

            while(true) {
               TField schemeField = iprot.readFieldBegin();
               if (schemeField.type == 0) {
                  iprot.readStructEnd();
                  struct.validate();
                  return;
               }

               switch (schemeField.id) {
                  case 1:
                     if (schemeField.type == 12) {
                        struct.req = new TGetPrimaryKeysReq();
                        struct.req.read(iprot);
                        struct.setReqIsSet(true);
                     } else {
                        TProtocolUtil.skip(iprot, schemeField.type);
                     }
                     break;
                  default:
                     TProtocolUtil.skip(iprot, schemeField.type);
               }

               iprot.readFieldEnd();
            }
         }

         public void write(TProtocol oprot, GetPrimaryKeys_args struct) throws TException {
            struct.validate();
            oprot.writeStructBegin(TCLIService.GetPrimaryKeys_args.STRUCT_DESC);
            if (struct.req != null) {
               oprot.writeFieldBegin(TCLIService.GetPrimaryKeys_args.REQ_FIELD_DESC);
               struct.req.write(oprot);
               oprot.writeFieldEnd();
            }

            oprot.writeFieldStop();
            oprot.writeStructEnd();
         }
      }

      private static class GetPrimaryKeys_argsTupleSchemeFactory implements SchemeFactory {
         private GetPrimaryKeys_argsTupleSchemeFactory() {
         }

         public GetPrimaryKeys_argsTupleScheme getScheme() {
            return new GetPrimaryKeys_argsTupleScheme();
         }
      }

      private static class GetPrimaryKeys_argsTupleScheme extends TupleScheme {
         private GetPrimaryKeys_argsTupleScheme() {
         }

         public void write(TProtocol prot, GetPrimaryKeys_args struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol)prot;
            BitSet optionals = new BitSet();
            if (struct.isSetReq()) {
               optionals.set(0);
            }

            oprot.writeBitSet(optionals, 1);
            if (struct.isSetReq()) {
               struct.req.write(oprot);
            }

         }

         public void read(TProtocol prot, GetPrimaryKeys_args struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol)prot;
            BitSet incoming = iprot.readBitSet(1);
            if (incoming.get(0)) {
               struct.req = new TGetPrimaryKeysReq();
               struct.req.read(iprot);
               struct.setReqIsSet(true);
            }

         }
      }
   }

   @Public
   @Stable
   public static class GetPrimaryKeys_result implements TBase, Serializable, Cloneable, Comparable {
      private static final TStruct STRUCT_DESC = new TStruct("GetPrimaryKeys_result");
      private static final TField SUCCESS_FIELD_DESC = new TField("success", (byte)12, (short)0);
      private static final SchemeFactory STANDARD_SCHEME_FACTORY = new GetPrimaryKeys_resultStandardSchemeFactory();
      private static final SchemeFactory TUPLE_SCHEME_FACTORY = new GetPrimaryKeys_resultTupleSchemeFactory();
      @Nullable
      private TGetPrimaryKeysResp success;
      public static final Map metaDataMap;

      public GetPrimaryKeys_result() {
      }

      public GetPrimaryKeys_result(TGetPrimaryKeysResp success) {
         this();
         this.success = success;
      }

      public GetPrimaryKeys_result(GetPrimaryKeys_result other) {
         if (other.isSetSuccess()) {
            this.success = new TGetPrimaryKeysResp(other.success);
         }

      }

      public GetPrimaryKeys_result deepCopy() {
         return new GetPrimaryKeys_result(this);
      }

      public void clear() {
         this.success = null;
      }

      @Nullable
      public TGetPrimaryKeysResp getSuccess() {
         return this.success;
      }

      public void setSuccess(@Nullable TGetPrimaryKeysResp success) {
         this.success = success;
      }

      public void unsetSuccess() {
         this.success = null;
      }

      public boolean isSetSuccess() {
         return this.success != null;
      }

      public void setSuccessIsSet(boolean value) {
         if (!value) {
            this.success = null;
         }

      }

      public void setFieldValue(_Fields field, @Nullable Object value) {
         switch (field) {
            case SUCCESS:
               if (value == null) {
                  this.unsetSuccess();
               } else {
                  this.setSuccess((TGetPrimaryKeysResp)value);
               }
            default:
         }
      }

      @Nullable
      public Object getFieldValue(_Fields field) {
         switch (field) {
            case SUCCESS:
               return this.getSuccess();
            default:
               throw new IllegalStateException();
         }
      }

      public boolean isSet(_Fields field) {
         if (field == null) {
            throw new IllegalArgumentException();
         } else {
            switch (field) {
               case SUCCESS:
                  return this.isSetSuccess();
               default:
                  throw new IllegalStateException();
            }
         }
      }

      public boolean equals(Object that) {
         return that instanceof GetPrimaryKeys_result ? this.equals((GetPrimaryKeys_result)that) : false;
      }

      public boolean equals(GetPrimaryKeys_result that) {
         if (that == null) {
            return false;
         } else if (this == that) {
            return true;
         } else {
            boolean this_present_success = this.isSetSuccess();
            boolean that_present_success = that.isSetSuccess();
            if (this_present_success || that_present_success) {
               if (!this_present_success || !that_present_success) {
                  return false;
               }

               if (!this.success.equals(that.success)) {
                  return false;
               }
            }

            return true;
         }
      }

      public int hashCode() {
         int hashCode = 1;
         hashCode = hashCode * 8191 + (this.isSetSuccess() ? 131071 : 524287);
         if (this.isSetSuccess()) {
            hashCode = hashCode * 8191 + this.success.hashCode();
         }

         return hashCode;
      }

      public int compareTo(GetPrimaryKeys_result other) {
         if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
         } else {
            int lastComparison = 0;
            lastComparison = Boolean.compare(this.isSetSuccess(), other.isSetSuccess());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetSuccess()) {
                  lastComparison = TBaseHelper.compareTo(this.success, other.success);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               return 0;
            }
         }
      }

      @Nullable
      public _Fields fieldForId(int fieldId) {
         return TCLIService.GetPrimaryKeys_result._Fields.findByThriftId(fieldId);
      }

      public void read(TProtocol iprot) throws TException {
         scheme(iprot).read(iprot, this);
      }

      public void write(TProtocol oprot) throws TException {
         scheme(oprot).write(oprot, this);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("GetPrimaryKeys_result(");
         boolean first = true;
         sb.append("success:");
         if (this.success == null) {
            sb.append("null");
         } else {
            sb.append(this.success);
         }

         first = false;
         sb.append(")");
         return sb.toString();
      }

      public void validate() throws TException {
         if (this.success != null) {
            this.success.validate();
         }

      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         try {
            this.write(new TCompactProtocol(new TIOStreamTransport(out)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         try {
            this.read(new TCompactProtocol(new TIOStreamTransport(in)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private static IScheme scheme(TProtocol proto) {
         return (StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
      }

      static {
         Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
         tmpMap.put(TCLIService.GetPrimaryKeys_result._Fields.SUCCESS, new FieldMetaData("success", (byte)3, new StructMetaData((byte)12, TGetPrimaryKeysResp.class)));
         metaDataMap = Collections.unmodifiableMap(tmpMap);
         FieldMetaData.addStructMetaDataMap(GetPrimaryKeys_result.class, metaDataMap);
      }

      public static enum _Fields implements TFieldIdEnum {
         SUCCESS((short)0, "success");

         private static final Map byName = new HashMap();
         private final short _thriftId;
         private final String _fieldName;

         @Nullable
         public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
               case 0:
                  return SUCCESS;
               default:
                  return null;
            }
         }

         public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) {
               throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            } else {
               return fields;
            }
         }

         @Nullable
         public static _Fields findByName(String name) {
            return (_Fields)byName.get(name);
         }

         private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
         }

         public short getThriftFieldId() {
            return this._thriftId;
         }

         public String getFieldName() {
            return this._fieldName;
         }

         static {
            for(_Fields field : EnumSet.allOf(_Fields.class)) {
               byName.put(field.getFieldName(), field);
            }

         }
      }

      private static class GetPrimaryKeys_resultStandardSchemeFactory implements SchemeFactory {
         private GetPrimaryKeys_resultStandardSchemeFactory() {
         }

         public GetPrimaryKeys_resultStandardScheme getScheme() {
            return new GetPrimaryKeys_resultStandardScheme();
         }
      }

      private static class GetPrimaryKeys_resultStandardScheme extends StandardScheme {
         private GetPrimaryKeys_resultStandardScheme() {
         }

         public void read(TProtocol iprot, GetPrimaryKeys_result struct) throws TException {
            iprot.readStructBegin();

            while(true) {
               TField schemeField = iprot.readFieldBegin();
               if (schemeField.type == 0) {
                  iprot.readStructEnd();
                  struct.validate();
                  return;
               }

               switch (schemeField.id) {
                  case 0:
                     if (schemeField.type == 12) {
                        struct.success = new TGetPrimaryKeysResp();
                        struct.success.read(iprot);
                        struct.setSuccessIsSet(true);
                     } else {
                        TProtocolUtil.skip(iprot, schemeField.type);
                     }
                     break;
                  default:
                     TProtocolUtil.skip(iprot, schemeField.type);
               }

               iprot.readFieldEnd();
            }
         }

         public void write(TProtocol oprot, GetPrimaryKeys_result struct) throws TException {
            struct.validate();
            oprot.writeStructBegin(TCLIService.GetPrimaryKeys_result.STRUCT_DESC);
            if (struct.success != null) {
               oprot.writeFieldBegin(TCLIService.GetPrimaryKeys_result.SUCCESS_FIELD_DESC);
               struct.success.write(oprot);
               oprot.writeFieldEnd();
            }

            oprot.writeFieldStop();
            oprot.writeStructEnd();
         }
      }

      private static class GetPrimaryKeys_resultTupleSchemeFactory implements SchemeFactory {
         private GetPrimaryKeys_resultTupleSchemeFactory() {
         }

         public GetPrimaryKeys_resultTupleScheme getScheme() {
            return new GetPrimaryKeys_resultTupleScheme();
         }
      }

      private static class GetPrimaryKeys_resultTupleScheme extends TupleScheme {
         private GetPrimaryKeys_resultTupleScheme() {
         }

         public void write(TProtocol prot, GetPrimaryKeys_result struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol)prot;
            BitSet optionals = new BitSet();
            if (struct.isSetSuccess()) {
               optionals.set(0);
            }

            oprot.writeBitSet(optionals, 1);
            if (struct.isSetSuccess()) {
               struct.success.write(oprot);
            }

         }

         public void read(TProtocol prot, GetPrimaryKeys_result struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol)prot;
            BitSet incoming = iprot.readBitSet(1);
            if (incoming.get(0)) {
               struct.success = new TGetPrimaryKeysResp();
               struct.success.read(iprot);
               struct.setSuccessIsSet(true);
            }

         }
      }
   }

   @Public
   @Stable
   public static class GetCrossReference_args implements TBase, Serializable, Cloneable, Comparable {
      private static final TStruct STRUCT_DESC = new TStruct("GetCrossReference_args");
      private static final TField REQ_FIELD_DESC = new TField("req", (byte)12, (short)1);
      private static final SchemeFactory STANDARD_SCHEME_FACTORY = new GetCrossReference_argsStandardSchemeFactory();
      private static final SchemeFactory TUPLE_SCHEME_FACTORY = new GetCrossReference_argsTupleSchemeFactory();
      @Nullable
      private TGetCrossReferenceReq req;
      public static final Map metaDataMap;

      public GetCrossReference_args() {
      }

      public GetCrossReference_args(TGetCrossReferenceReq req) {
         this();
         this.req = req;
      }

      public GetCrossReference_args(GetCrossReference_args other) {
         if (other.isSetReq()) {
            this.req = new TGetCrossReferenceReq(other.req);
         }

      }

      public GetCrossReference_args deepCopy() {
         return new GetCrossReference_args(this);
      }

      public void clear() {
         this.req = null;
      }

      @Nullable
      public TGetCrossReferenceReq getReq() {
         return this.req;
      }

      public void setReq(@Nullable TGetCrossReferenceReq req) {
         this.req = req;
      }

      public void unsetReq() {
         this.req = null;
      }

      public boolean isSetReq() {
         return this.req != null;
      }

      public void setReqIsSet(boolean value) {
         if (!value) {
            this.req = null;
         }

      }

      public void setFieldValue(_Fields field, @Nullable Object value) {
         switch (field) {
            case REQ:
               if (value == null) {
                  this.unsetReq();
               } else {
                  this.setReq((TGetCrossReferenceReq)value);
               }
            default:
         }
      }

      @Nullable
      public Object getFieldValue(_Fields field) {
         switch (field) {
            case REQ:
               return this.getReq();
            default:
               throw new IllegalStateException();
         }
      }

      public boolean isSet(_Fields field) {
         if (field == null) {
            throw new IllegalArgumentException();
         } else {
            switch (field) {
               case REQ:
                  return this.isSetReq();
               default:
                  throw new IllegalStateException();
            }
         }
      }

      public boolean equals(Object that) {
         return that instanceof GetCrossReference_args ? this.equals((GetCrossReference_args)that) : false;
      }

      public boolean equals(GetCrossReference_args that) {
         if (that == null) {
            return false;
         } else if (this == that) {
            return true;
         } else {
            boolean this_present_req = this.isSetReq();
            boolean that_present_req = that.isSetReq();
            if (this_present_req || that_present_req) {
               if (!this_present_req || !that_present_req) {
                  return false;
               }

               if (!this.req.equals(that.req)) {
                  return false;
               }
            }

            return true;
         }
      }

      public int hashCode() {
         int hashCode = 1;
         hashCode = hashCode * 8191 + (this.isSetReq() ? 131071 : 524287);
         if (this.isSetReq()) {
            hashCode = hashCode * 8191 + this.req.hashCode();
         }

         return hashCode;
      }

      public int compareTo(GetCrossReference_args other) {
         if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
         } else {
            int lastComparison = 0;
            lastComparison = Boolean.compare(this.isSetReq(), other.isSetReq());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetReq()) {
                  lastComparison = TBaseHelper.compareTo(this.req, other.req);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               return 0;
            }
         }
      }

      @Nullable
      public _Fields fieldForId(int fieldId) {
         return TCLIService.GetCrossReference_args._Fields.findByThriftId(fieldId);
      }

      public void read(TProtocol iprot) throws TException {
         scheme(iprot).read(iprot, this);
      }

      public void write(TProtocol oprot) throws TException {
         scheme(oprot).write(oprot, this);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("GetCrossReference_args(");
         boolean first = true;
         sb.append("req:");
         if (this.req == null) {
            sb.append("null");
         } else {
            sb.append(this.req);
         }

         first = false;
         sb.append(")");
         return sb.toString();
      }

      public void validate() throws TException {
         if (this.req != null) {
            this.req.validate();
         }

      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         try {
            this.write(new TCompactProtocol(new TIOStreamTransport(out)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         try {
            this.read(new TCompactProtocol(new TIOStreamTransport(in)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private static IScheme scheme(TProtocol proto) {
         return (StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
      }

      static {
         Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
         tmpMap.put(TCLIService.GetCrossReference_args._Fields.REQ, new FieldMetaData("req", (byte)3, new StructMetaData((byte)12, TGetCrossReferenceReq.class)));
         metaDataMap = Collections.unmodifiableMap(tmpMap);
         FieldMetaData.addStructMetaDataMap(GetCrossReference_args.class, metaDataMap);
      }

      public static enum _Fields implements TFieldIdEnum {
         REQ((short)1, "req");

         private static final Map byName = new HashMap();
         private final short _thriftId;
         private final String _fieldName;

         @Nullable
         public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
               case 1:
                  return REQ;
               default:
                  return null;
            }
         }

         public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) {
               throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            } else {
               return fields;
            }
         }

         @Nullable
         public static _Fields findByName(String name) {
            return (_Fields)byName.get(name);
         }

         private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
         }

         public short getThriftFieldId() {
            return this._thriftId;
         }

         public String getFieldName() {
            return this._fieldName;
         }

         static {
            for(_Fields field : EnumSet.allOf(_Fields.class)) {
               byName.put(field.getFieldName(), field);
            }

         }
      }

      private static class GetCrossReference_argsStandardSchemeFactory implements SchemeFactory {
         private GetCrossReference_argsStandardSchemeFactory() {
         }

         public GetCrossReference_argsStandardScheme getScheme() {
            return new GetCrossReference_argsStandardScheme();
         }
      }

      private static class GetCrossReference_argsStandardScheme extends StandardScheme {
         private GetCrossReference_argsStandardScheme() {
         }

         public void read(TProtocol iprot, GetCrossReference_args struct) throws TException {
            iprot.readStructBegin();

            while(true) {
               TField schemeField = iprot.readFieldBegin();
               if (schemeField.type == 0) {
                  iprot.readStructEnd();
                  struct.validate();
                  return;
               }

               switch (schemeField.id) {
                  case 1:
                     if (schemeField.type == 12) {
                        struct.req = new TGetCrossReferenceReq();
                        struct.req.read(iprot);
                        struct.setReqIsSet(true);
                     } else {
                        TProtocolUtil.skip(iprot, schemeField.type);
                     }
                     break;
                  default:
                     TProtocolUtil.skip(iprot, schemeField.type);
               }

               iprot.readFieldEnd();
            }
         }

         public void write(TProtocol oprot, GetCrossReference_args struct) throws TException {
            struct.validate();
            oprot.writeStructBegin(TCLIService.GetCrossReference_args.STRUCT_DESC);
            if (struct.req != null) {
               oprot.writeFieldBegin(TCLIService.GetCrossReference_args.REQ_FIELD_DESC);
               struct.req.write(oprot);
               oprot.writeFieldEnd();
            }

            oprot.writeFieldStop();
            oprot.writeStructEnd();
         }
      }

      private static class GetCrossReference_argsTupleSchemeFactory implements SchemeFactory {
         private GetCrossReference_argsTupleSchemeFactory() {
         }

         public GetCrossReference_argsTupleScheme getScheme() {
            return new GetCrossReference_argsTupleScheme();
         }
      }

      private static class GetCrossReference_argsTupleScheme extends TupleScheme {
         private GetCrossReference_argsTupleScheme() {
         }

         public void write(TProtocol prot, GetCrossReference_args struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol)prot;
            BitSet optionals = new BitSet();
            if (struct.isSetReq()) {
               optionals.set(0);
            }

            oprot.writeBitSet(optionals, 1);
            if (struct.isSetReq()) {
               struct.req.write(oprot);
            }

         }

         public void read(TProtocol prot, GetCrossReference_args struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol)prot;
            BitSet incoming = iprot.readBitSet(1);
            if (incoming.get(0)) {
               struct.req = new TGetCrossReferenceReq();
               struct.req.read(iprot);
               struct.setReqIsSet(true);
            }

         }
      }
   }

   @Public
   @Stable
   public static class GetCrossReference_result implements TBase, Serializable, Cloneable, Comparable {
      private static final TStruct STRUCT_DESC = new TStruct("GetCrossReference_result");
      private static final TField SUCCESS_FIELD_DESC = new TField("success", (byte)12, (short)0);
      private static final SchemeFactory STANDARD_SCHEME_FACTORY = new GetCrossReference_resultStandardSchemeFactory();
      private static final SchemeFactory TUPLE_SCHEME_FACTORY = new GetCrossReference_resultTupleSchemeFactory();
      @Nullable
      private TGetCrossReferenceResp success;
      public static final Map metaDataMap;

      public GetCrossReference_result() {
      }

      public GetCrossReference_result(TGetCrossReferenceResp success) {
         this();
         this.success = success;
      }

      public GetCrossReference_result(GetCrossReference_result other) {
         if (other.isSetSuccess()) {
            this.success = new TGetCrossReferenceResp(other.success);
         }

      }

      public GetCrossReference_result deepCopy() {
         return new GetCrossReference_result(this);
      }

      public void clear() {
         this.success = null;
      }

      @Nullable
      public TGetCrossReferenceResp getSuccess() {
         return this.success;
      }

      public void setSuccess(@Nullable TGetCrossReferenceResp success) {
         this.success = success;
      }

      public void unsetSuccess() {
         this.success = null;
      }

      public boolean isSetSuccess() {
         return this.success != null;
      }

      public void setSuccessIsSet(boolean value) {
         if (!value) {
            this.success = null;
         }

      }

      public void setFieldValue(_Fields field, @Nullable Object value) {
         switch (field) {
            case SUCCESS:
               if (value == null) {
                  this.unsetSuccess();
               } else {
                  this.setSuccess((TGetCrossReferenceResp)value);
               }
            default:
         }
      }

      @Nullable
      public Object getFieldValue(_Fields field) {
         switch (field) {
            case SUCCESS:
               return this.getSuccess();
            default:
               throw new IllegalStateException();
         }
      }

      public boolean isSet(_Fields field) {
         if (field == null) {
            throw new IllegalArgumentException();
         } else {
            switch (field) {
               case SUCCESS:
                  return this.isSetSuccess();
               default:
                  throw new IllegalStateException();
            }
         }
      }

      public boolean equals(Object that) {
         return that instanceof GetCrossReference_result ? this.equals((GetCrossReference_result)that) : false;
      }

      public boolean equals(GetCrossReference_result that) {
         if (that == null) {
            return false;
         } else if (this == that) {
            return true;
         } else {
            boolean this_present_success = this.isSetSuccess();
            boolean that_present_success = that.isSetSuccess();
            if (this_present_success || that_present_success) {
               if (!this_present_success || !that_present_success) {
                  return false;
               }

               if (!this.success.equals(that.success)) {
                  return false;
               }
            }

            return true;
         }
      }

      public int hashCode() {
         int hashCode = 1;
         hashCode = hashCode * 8191 + (this.isSetSuccess() ? 131071 : 524287);
         if (this.isSetSuccess()) {
            hashCode = hashCode * 8191 + this.success.hashCode();
         }

         return hashCode;
      }

      public int compareTo(GetCrossReference_result other) {
         if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
         } else {
            int lastComparison = 0;
            lastComparison = Boolean.compare(this.isSetSuccess(), other.isSetSuccess());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetSuccess()) {
                  lastComparison = TBaseHelper.compareTo(this.success, other.success);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               return 0;
            }
         }
      }

      @Nullable
      public _Fields fieldForId(int fieldId) {
         return TCLIService.GetCrossReference_result._Fields.findByThriftId(fieldId);
      }

      public void read(TProtocol iprot) throws TException {
         scheme(iprot).read(iprot, this);
      }

      public void write(TProtocol oprot) throws TException {
         scheme(oprot).write(oprot, this);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("GetCrossReference_result(");
         boolean first = true;
         sb.append("success:");
         if (this.success == null) {
            sb.append("null");
         } else {
            sb.append(this.success);
         }

         first = false;
         sb.append(")");
         return sb.toString();
      }

      public void validate() throws TException {
         if (this.success != null) {
            this.success.validate();
         }

      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         try {
            this.write(new TCompactProtocol(new TIOStreamTransport(out)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         try {
            this.read(new TCompactProtocol(new TIOStreamTransport(in)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private static IScheme scheme(TProtocol proto) {
         return (StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
      }

      static {
         Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
         tmpMap.put(TCLIService.GetCrossReference_result._Fields.SUCCESS, new FieldMetaData("success", (byte)3, new StructMetaData((byte)12, TGetCrossReferenceResp.class)));
         metaDataMap = Collections.unmodifiableMap(tmpMap);
         FieldMetaData.addStructMetaDataMap(GetCrossReference_result.class, metaDataMap);
      }

      public static enum _Fields implements TFieldIdEnum {
         SUCCESS((short)0, "success");

         private static final Map byName = new HashMap();
         private final short _thriftId;
         private final String _fieldName;

         @Nullable
         public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
               case 0:
                  return SUCCESS;
               default:
                  return null;
            }
         }

         public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) {
               throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            } else {
               return fields;
            }
         }

         @Nullable
         public static _Fields findByName(String name) {
            return (_Fields)byName.get(name);
         }

         private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
         }

         public short getThriftFieldId() {
            return this._thriftId;
         }

         public String getFieldName() {
            return this._fieldName;
         }

         static {
            for(_Fields field : EnumSet.allOf(_Fields.class)) {
               byName.put(field.getFieldName(), field);
            }

         }
      }

      private static class GetCrossReference_resultStandardSchemeFactory implements SchemeFactory {
         private GetCrossReference_resultStandardSchemeFactory() {
         }

         public GetCrossReference_resultStandardScheme getScheme() {
            return new GetCrossReference_resultStandardScheme();
         }
      }

      private static class GetCrossReference_resultStandardScheme extends StandardScheme {
         private GetCrossReference_resultStandardScheme() {
         }

         public void read(TProtocol iprot, GetCrossReference_result struct) throws TException {
            iprot.readStructBegin();

            while(true) {
               TField schemeField = iprot.readFieldBegin();
               if (schemeField.type == 0) {
                  iprot.readStructEnd();
                  struct.validate();
                  return;
               }

               switch (schemeField.id) {
                  case 0:
                     if (schemeField.type == 12) {
                        struct.success = new TGetCrossReferenceResp();
                        struct.success.read(iprot);
                        struct.setSuccessIsSet(true);
                     } else {
                        TProtocolUtil.skip(iprot, schemeField.type);
                     }
                     break;
                  default:
                     TProtocolUtil.skip(iprot, schemeField.type);
               }

               iprot.readFieldEnd();
            }
         }

         public void write(TProtocol oprot, GetCrossReference_result struct) throws TException {
            struct.validate();
            oprot.writeStructBegin(TCLIService.GetCrossReference_result.STRUCT_DESC);
            if (struct.success != null) {
               oprot.writeFieldBegin(TCLIService.GetCrossReference_result.SUCCESS_FIELD_DESC);
               struct.success.write(oprot);
               oprot.writeFieldEnd();
            }

            oprot.writeFieldStop();
            oprot.writeStructEnd();
         }
      }

      private static class GetCrossReference_resultTupleSchemeFactory implements SchemeFactory {
         private GetCrossReference_resultTupleSchemeFactory() {
         }

         public GetCrossReference_resultTupleScheme getScheme() {
            return new GetCrossReference_resultTupleScheme();
         }
      }

      private static class GetCrossReference_resultTupleScheme extends TupleScheme {
         private GetCrossReference_resultTupleScheme() {
         }

         public void write(TProtocol prot, GetCrossReference_result struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol)prot;
            BitSet optionals = new BitSet();
            if (struct.isSetSuccess()) {
               optionals.set(0);
            }

            oprot.writeBitSet(optionals, 1);
            if (struct.isSetSuccess()) {
               struct.success.write(oprot);
            }

         }

         public void read(TProtocol prot, GetCrossReference_result struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol)prot;
            BitSet incoming = iprot.readBitSet(1);
            if (incoming.get(0)) {
               struct.success = new TGetCrossReferenceResp();
               struct.success.read(iprot);
               struct.setSuccessIsSet(true);
            }

         }
      }
   }

   @Public
   @Stable
   public static class GetOperationStatus_args implements TBase, Serializable, Cloneable, Comparable {
      private static final TStruct STRUCT_DESC = new TStruct("GetOperationStatus_args");
      private static final TField REQ_FIELD_DESC = new TField("req", (byte)12, (short)1);
      private static final SchemeFactory STANDARD_SCHEME_FACTORY = new GetOperationStatus_argsStandardSchemeFactory();
      private static final SchemeFactory TUPLE_SCHEME_FACTORY = new GetOperationStatus_argsTupleSchemeFactory();
      @Nullable
      private TGetOperationStatusReq req;
      public static final Map metaDataMap;

      public GetOperationStatus_args() {
      }

      public GetOperationStatus_args(TGetOperationStatusReq req) {
         this();
         this.req = req;
      }

      public GetOperationStatus_args(GetOperationStatus_args other) {
         if (other.isSetReq()) {
            this.req = new TGetOperationStatusReq(other.req);
         }

      }

      public GetOperationStatus_args deepCopy() {
         return new GetOperationStatus_args(this);
      }

      public void clear() {
         this.req = null;
      }

      @Nullable
      public TGetOperationStatusReq getReq() {
         return this.req;
      }

      public void setReq(@Nullable TGetOperationStatusReq req) {
         this.req = req;
      }

      public void unsetReq() {
         this.req = null;
      }

      public boolean isSetReq() {
         return this.req != null;
      }

      public void setReqIsSet(boolean value) {
         if (!value) {
            this.req = null;
         }

      }

      public void setFieldValue(_Fields field, @Nullable Object value) {
         switch (field) {
            case REQ:
               if (value == null) {
                  this.unsetReq();
               } else {
                  this.setReq((TGetOperationStatusReq)value);
               }
            default:
         }
      }

      @Nullable
      public Object getFieldValue(_Fields field) {
         switch (field) {
            case REQ:
               return this.getReq();
            default:
               throw new IllegalStateException();
         }
      }

      public boolean isSet(_Fields field) {
         if (field == null) {
            throw new IllegalArgumentException();
         } else {
            switch (field) {
               case REQ:
                  return this.isSetReq();
               default:
                  throw new IllegalStateException();
            }
         }
      }

      public boolean equals(Object that) {
         return that instanceof GetOperationStatus_args ? this.equals((GetOperationStatus_args)that) : false;
      }

      public boolean equals(GetOperationStatus_args that) {
         if (that == null) {
            return false;
         } else if (this == that) {
            return true;
         } else {
            boolean this_present_req = this.isSetReq();
            boolean that_present_req = that.isSetReq();
            if (this_present_req || that_present_req) {
               if (!this_present_req || !that_present_req) {
                  return false;
               }

               if (!this.req.equals(that.req)) {
                  return false;
               }
            }

            return true;
         }
      }

      public int hashCode() {
         int hashCode = 1;
         hashCode = hashCode * 8191 + (this.isSetReq() ? 131071 : 524287);
         if (this.isSetReq()) {
            hashCode = hashCode * 8191 + this.req.hashCode();
         }

         return hashCode;
      }

      public int compareTo(GetOperationStatus_args other) {
         if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
         } else {
            int lastComparison = 0;
            lastComparison = Boolean.compare(this.isSetReq(), other.isSetReq());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetReq()) {
                  lastComparison = TBaseHelper.compareTo(this.req, other.req);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               return 0;
            }
         }
      }

      @Nullable
      public _Fields fieldForId(int fieldId) {
         return TCLIService.GetOperationStatus_args._Fields.findByThriftId(fieldId);
      }

      public void read(TProtocol iprot) throws TException {
         scheme(iprot).read(iprot, this);
      }

      public void write(TProtocol oprot) throws TException {
         scheme(oprot).write(oprot, this);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("GetOperationStatus_args(");
         boolean first = true;
         sb.append("req:");
         if (this.req == null) {
            sb.append("null");
         } else {
            sb.append(this.req);
         }

         first = false;
         sb.append(")");
         return sb.toString();
      }

      public void validate() throws TException {
         if (this.req != null) {
            this.req.validate();
         }

      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         try {
            this.write(new TCompactProtocol(new TIOStreamTransport(out)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         try {
            this.read(new TCompactProtocol(new TIOStreamTransport(in)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private static IScheme scheme(TProtocol proto) {
         return (StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
      }

      static {
         Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
         tmpMap.put(TCLIService.GetOperationStatus_args._Fields.REQ, new FieldMetaData("req", (byte)3, new StructMetaData((byte)12, TGetOperationStatusReq.class)));
         metaDataMap = Collections.unmodifiableMap(tmpMap);
         FieldMetaData.addStructMetaDataMap(GetOperationStatus_args.class, metaDataMap);
      }

      public static enum _Fields implements TFieldIdEnum {
         REQ((short)1, "req");

         private static final Map byName = new HashMap();
         private final short _thriftId;
         private final String _fieldName;

         @Nullable
         public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
               case 1:
                  return REQ;
               default:
                  return null;
            }
         }

         public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) {
               throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            } else {
               return fields;
            }
         }

         @Nullable
         public static _Fields findByName(String name) {
            return (_Fields)byName.get(name);
         }

         private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
         }

         public short getThriftFieldId() {
            return this._thriftId;
         }

         public String getFieldName() {
            return this._fieldName;
         }

         static {
            for(_Fields field : EnumSet.allOf(_Fields.class)) {
               byName.put(field.getFieldName(), field);
            }

         }
      }

      private static class GetOperationStatus_argsStandardSchemeFactory implements SchemeFactory {
         private GetOperationStatus_argsStandardSchemeFactory() {
         }

         public GetOperationStatus_argsStandardScheme getScheme() {
            return new GetOperationStatus_argsStandardScheme();
         }
      }

      private static class GetOperationStatus_argsStandardScheme extends StandardScheme {
         private GetOperationStatus_argsStandardScheme() {
         }

         public void read(TProtocol iprot, GetOperationStatus_args struct) throws TException {
            iprot.readStructBegin();

            while(true) {
               TField schemeField = iprot.readFieldBegin();
               if (schemeField.type == 0) {
                  iprot.readStructEnd();
                  struct.validate();
                  return;
               }

               switch (schemeField.id) {
                  case 1:
                     if (schemeField.type == 12) {
                        struct.req = new TGetOperationStatusReq();
                        struct.req.read(iprot);
                        struct.setReqIsSet(true);
                     } else {
                        TProtocolUtil.skip(iprot, schemeField.type);
                     }
                     break;
                  default:
                     TProtocolUtil.skip(iprot, schemeField.type);
               }

               iprot.readFieldEnd();
            }
         }

         public void write(TProtocol oprot, GetOperationStatus_args struct) throws TException {
            struct.validate();
            oprot.writeStructBegin(TCLIService.GetOperationStatus_args.STRUCT_DESC);
            if (struct.req != null) {
               oprot.writeFieldBegin(TCLIService.GetOperationStatus_args.REQ_FIELD_DESC);
               struct.req.write(oprot);
               oprot.writeFieldEnd();
            }

            oprot.writeFieldStop();
            oprot.writeStructEnd();
         }
      }

      private static class GetOperationStatus_argsTupleSchemeFactory implements SchemeFactory {
         private GetOperationStatus_argsTupleSchemeFactory() {
         }

         public GetOperationStatus_argsTupleScheme getScheme() {
            return new GetOperationStatus_argsTupleScheme();
         }
      }

      private static class GetOperationStatus_argsTupleScheme extends TupleScheme {
         private GetOperationStatus_argsTupleScheme() {
         }

         public void write(TProtocol prot, GetOperationStatus_args struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol)prot;
            BitSet optionals = new BitSet();
            if (struct.isSetReq()) {
               optionals.set(0);
            }

            oprot.writeBitSet(optionals, 1);
            if (struct.isSetReq()) {
               struct.req.write(oprot);
            }

         }

         public void read(TProtocol prot, GetOperationStatus_args struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol)prot;
            BitSet incoming = iprot.readBitSet(1);
            if (incoming.get(0)) {
               struct.req = new TGetOperationStatusReq();
               struct.req.read(iprot);
               struct.setReqIsSet(true);
            }

         }
      }
   }

   @Public
   @Stable
   public static class GetOperationStatus_result implements TBase, Serializable, Cloneable, Comparable {
      private static final TStruct STRUCT_DESC = new TStruct("GetOperationStatus_result");
      private static final TField SUCCESS_FIELD_DESC = new TField("success", (byte)12, (short)0);
      private static final SchemeFactory STANDARD_SCHEME_FACTORY = new GetOperationStatus_resultStandardSchemeFactory();
      private static final SchemeFactory TUPLE_SCHEME_FACTORY = new GetOperationStatus_resultTupleSchemeFactory();
      @Nullable
      private TGetOperationStatusResp success;
      public static final Map metaDataMap;

      public GetOperationStatus_result() {
      }

      public GetOperationStatus_result(TGetOperationStatusResp success) {
         this();
         this.success = success;
      }

      public GetOperationStatus_result(GetOperationStatus_result other) {
         if (other.isSetSuccess()) {
            this.success = new TGetOperationStatusResp(other.success);
         }

      }

      public GetOperationStatus_result deepCopy() {
         return new GetOperationStatus_result(this);
      }

      public void clear() {
         this.success = null;
      }

      @Nullable
      public TGetOperationStatusResp getSuccess() {
         return this.success;
      }

      public void setSuccess(@Nullable TGetOperationStatusResp success) {
         this.success = success;
      }

      public void unsetSuccess() {
         this.success = null;
      }

      public boolean isSetSuccess() {
         return this.success != null;
      }

      public void setSuccessIsSet(boolean value) {
         if (!value) {
            this.success = null;
         }

      }

      public void setFieldValue(_Fields field, @Nullable Object value) {
         switch (field) {
            case SUCCESS:
               if (value == null) {
                  this.unsetSuccess();
               } else {
                  this.setSuccess((TGetOperationStatusResp)value);
               }
            default:
         }
      }

      @Nullable
      public Object getFieldValue(_Fields field) {
         switch (field) {
            case SUCCESS:
               return this.getSuccess();
            default:
               throw new IllegalStateException();
         }
      }

      public boolean isSet(_Fields field) {
         if (field == null) {
            throw new IllegalArgumentException();
         } else {
            switch (field) {
               case SUCCESS:
                  return this.isSetSuccess();
               default:
                  throw new IllegalStateException();
            }
         }
      }

      public boolean equals(Object that) {
         return that instanceof GetOperationStatus_result ? this.equals((GetOperationStatus_result)that) : false;
      }

      public boolean equals(GetOperationStatus_result that) {
         if (that == null) {
            return false;
         } else if (this == that) {
            return true;
         } else {
            boolean this_present_success = this.isSetSuccess();
            boolean that_present_success = that.isSetSuccess();
            if (this_present_success || that_present_success) {
               if (!this_present_success || !that_present_success) {
                  return false;
               }

               if (!this.success.equals(that.success)) {
                  return false;
               }
            }

            return true;
         }
      }

      public int hashCode() {
         int hashCode = 1;
         hashCode = hashCode * 8191 + (this.isSetSuccess() ? 131071 : 524287);
         if (this.isSetSuccess()) {
            hashCode = hashCode * 8191 + this.success.hashCode();
         }

         return hashCode;
      }

      public int compareTo(GetOperationStatus_result other) {
         if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
         } else {
            int lastComparison = 0;
            lastComparison = Boolean.compare(this.isSetSuccess(), other.isSetSuccess());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetSuccess()) {
                  lastComparison = TBaseHelper.compareTo(this.success, other.success);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               return 0;
            }
         }
      }

      @Nullable
      public _Fields fieldForId(int fieldId) {
         return TCLIService.GetOperationStatus_result._Fields.findByThriftId(fieldId);
      }

      public void read(TProtocol iprot) throws TException {
         scheme(iprot).read(iprot, this);
      }

      public void write(TProtocol oprot) throws TException {
         scheme(oprot).write(oprot, this);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("GetOperationStatus_result(");
         boolean first = true;
         sb.append("success:");
         if (this.success == null) {
            sb.append("null");
         } else {
            sb.append(this.success);
         }

         first = false;
         sb.append(")");
         return sb.toString();
      }

      public void validate() throws TException {
         if (this.success != null) {
            this.success.validate();
         }

      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         try {
            this.write(new TCompactProtocol(new TIOStreamTransport(out)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         try {
            this.read(new TCompactProtocol(new TIOStreamTransport(in)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private static IScheme scheme(TProtocol proto) {
         return (StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
      }

      static {
         Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
         tmpMap.put(TCLIService.GetOperationStatus_result._Fields.SUCCESS, new FieldMetaData("success", (byte)3, new StructMetaData((byte)12, TGetOperationStatusResp.class)));
         metaDataMap = Collections.unmodifiableMap(tmpMap);
         FieldMetaData.addStructMetaDataMap(GetOperationStatus_result.class, metaDataMap);
      }

      public static enum _Fields implements TFieldIdEnum {
         SUCCESS((short)0, "success");

         private static final Map byName = new HashMap();
         private final short _thriftId;
         private final String _fieldName;

         @Nullable
         public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
               case 0:
                  return SUCCESS;
               default:
                  return null;
            }
         }

         public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) {
               throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            } else {
               return fields;
            }
         }

         @Nullable
         public static _Fields findByName(String name) {
            return (_Fields)byName.get(name);
         }

         private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
         }

         public short getThriftFieldId() {
            return this._thriftId;
         }

         public String getFieldName() {
            return this._fieldName;
         }

         static {
            for(_Fields field : EnumSet.allOf(_Fields.class)) {
               byName.put(field.getFieldName(), field);
            }

         }
      }

      private static class GetOperationStatus_resultStandardSchemeFactory implements SchemeFactory {
         private GetOperationStatus_resultStandardSchemeFactory() {
         }

         public GetOperationStatus_resultStandardScheme getScheme() {
            return new GetOperationStatus_resultStandardScheme();
         }
      }

      private static class GetOperationStatus_resultStandardScheme extends StandardScheme {
         private GetOperationStatus_resultStandardScheme() {
         }

         public void read(TProtocol iprot, GetOperationStatus_result struct) throws TException {
            iprot.readStructBegin();

            while(true) {
               TField schemeField = iprot.readFieldBegin();
               if (schemeField.type == 0) {
                  iprot.readStructEnd();
                  struct.validate();
                  return;
               }

               switch (schemeField.id) {
                  case 0:
                     if (schemeField.type == 12) {
                        struct.success = new TGetOperationStatusResp();
                        struct.success.read(iprot);
                        struct.setSuccessIsSet(true);
                     } else {
                        TProtocolUtil.skip(iprot, schemeField.type);
                     }
                     break;
                  default:
                     TProtocolUtil.skip(iprot, schemeField.type);
               }

               iprot.readFieldEnd();
            }
         }

         public void write(TProtocol oprot, GetOperationStatus_result struct) throws TException {
            struct.validate();
            oprot.writeStructBegin(TCLIService.GetOperationStatus_result.STRUCT_DESC);
            if (struct.success != null) {
               oprot.writeFieldBegin(TCLIService.GetOperationStatus_result.SUCCESS_FIELD_DESC);
               struct.success.write(oprot);
               oprot.writeFieldEnd();
            }

            oprot.writeFieldStop();
            oprot.writeStructEnd();
         }
      }

      private static class GetOperationStatus_resultTupleSchemeFactory implements SchemeFactory {
         private GetOperationStatus_resultTupleSchemeFactory() {
         }

         public GetOperationStatus_resultTupleScheme getScheme() {
            return new GetOperationStatus_resultTupleScheme();
         }
      }

      private static class GetOperationStatus_resultTupleScheme extends TupleScheme {
         private GetOperationStatus_resultTupleScheme() {
         }

         public void write(TProtocol prot, GetOperationStatus_result struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol)prot;
            BitSet optionals = new BitSet();
            if (struct.isSetSuccess()) {
               optionals.set(0);
            }

            oprot.writeBitSet(optionals, 1);
            if (struct.isSetSuccess()) {
               struct.success.write(oprot);
            }

         }

         public void read(TProtocol prot, GetOperationStatus_result struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol)prot;
            BitSet incoming = iprot.readBitSet(1);
            if (incoming.get(0)) {
               struct.success = new TGetOperationStatusResp();
               struct.success.read(iprot);
               struct.setSuccessIsSet(true);
            }

         }
      }
   }

   @Public
   @Stable
   public static class CancelOperation_args implements TBase, Serializable, Cloneable, Comparable {
      private static final TStruct STRUCT_DESC = new TStruct("CancelOperation_args");
      private static final TField REQ_FIELD_DESC = new TField("req", (byte)12, (short)1);
      private static final SchemeFactory STANDARD_SCHEME_FACTORY = new CancelOperation_argsStandardSchemeFactory();
      private static final SchemeFactory TUPLE_SCHEME_FACTORY = new CancelOperation_argsTupleSchemeFactory();
      @Nullable
      private TCancelOperationReq req;
      public static final Map metaDataMap;

      public CancelOperation_args() {
      }

      public CancelOperation_args(TCancelOperationReq req) {
         this();
         this.req = req;
      }

      public CancelOperation_args(CancelOperation_args other) {
         if (other.isSetReq()) {
            this.req = new TCancelOperationReq(other.req);
         }

      }

      public CancelOperation_args deepCopy() {
         return new CancelOperation_args(this);
      }

      public void clear() {
         this.req = null;
      }

      @Nullable
      public TCancelOperationReq getReq() {
         return this.req;
      }

      public void setReq(@Nullable TCancelOperationReq req) {
         this.req = req;
      }

      public void unsetReq() {
         this.req = null;
      }

      public boolean isSetReq() {
         return this.req != null;
      }

      public void setReqIsSet(boolean value) {
         if (!value) {
            this.req = null;
         }

      }

      public void setFieldValue(_Fields field, @Nullable Object value) {
         switch (field) {
            case REQ:
               if (value == null) {
                  this.unsetReq();
               } else {
                  this.setReq((TCancelOperationReq)value);
               }
            default:
         }
      }

      @Nullable
      public Object getFieldValue(_Fields field) {
         switch (field) {
            case REQ:
               return this.getReq();
            default:
               throw new IllegalStateException();
         }
      }

      public boolean isSet(_Fields field) {
         if (field == null) {
            throw new IllegalArgumentException();
         } else {
            switch (field) {
               case REQ:
                  return this.isSetReq();
               default:
                  throw new IllegalStateException();
            }
         }
      }

      public boolean equals(Object that) {
         return that instanceof CancelOperation_args ? this.equals((CancelOperation_args)that) : false;
      }

      public boolean equals(CancelOperation_args that) {
         if (that == null) {
            return false;
         } else if (this == that) {
            return true;
         } else {
            boolean this_present_req = this.isSetReq();
            boolean that_present_req = that.isSetReq();
            if (this_present_req || that_present_req) {
               if (!this_present_req || !that_present_req) {
                  return false;
               }

               if (!this.req.equals(that.req)) {
                  return false;
               }
            }

            return true;
         }
      }

      public int hashCode() {
         int hashCode = 1;
         hashCode = hashCode * 8191 + (this.isSetReq() ? 131071 : 524287);
         if (this.isSetReq()) {
            hashCode = hashCode * 8191 + this.req.hashCode();
         }

         return hashCode;
      }

      public int compareTo(CancelOperation_args other) {
         if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
         } else {
            int lastComparison = 0;
            lastComparison = Boolean.compare(this.isSetReq(), other.isSetReq());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetReq()) {
                  lastComparison = TBaseHelper.compareTo(this.req, other.req);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               return 0;
            }
         }
      }

      @Nullable
      public _Fields fieldForId(int fieldId) {
         return TCLIService.CancelOperation_args._Fields.findByThriftId(fieldId);
      }

      public void read(TProtocol iprot) throws TException {
         scheme(iprot).read(iprot, this);
      }

      public void write(TProtocol oprot) throws TException {
         scheme(oprot).write(oprot, this);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("CancelOperation_args(");
         boolean first = true;
         sb.append("req:");
         if (this.req == null) {
            sb.append("null");
         } else {
            sb.append(this.req);
         }

         first = false;
         sb.append(")");
         return sb.toString();
      }

      public void validate() throws TException {
         if (this.req != null) {
            this.req.validate();
         }

      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         try {
            this.write(new TCompactProtocol(new TIOStreamTransport(out)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         try {
            this.read(new TCompactProtocol(new TIOStreamTransport(in)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private static IScheme scheme(TProtocol proto) {
         return (StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
      }

      static {
         Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
         tmpMap.put(TCLIService.CancelOperation_args._Fields.REQ, new FieldMetaData("req", (byte)3, new StructMetaData((byte)12, TCancelOperationReq.class)));
         metaDataMap = Collections.unmodifiableMap(tmpMap);
         FieldMetaData.addStructMetaDataMap(CancelOperation_args.class, metaDataMap);
      }

      public static enum _Fields implements TFieldIdEnum {
         REQ((short)1, "req");

         private static final Map byName = new HashMap();
         private final short _thriftId;
         private final String _fieldName;

         @Nullable
         public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
               case 1:
                  return REQ;
               default:
                  return null;
            }
         }

         public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) {
               throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            } else {
               return fields;
            }
         }

         @Nullable
         public static _Fields findByName(String name) {
            return (_Fields)byName.get(name);
         }

         private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
         }

         public short getThriftFieldId() {
            return this._thriftId;
         }

         public String getFieldName() {
            return this._fieldName;
         }

         static {
            for(_Fields field : EnumSet.allOf(_Fields.class)) {
               byName.put(field.getFieldName(), field);
            }

         }
      }

      private static class CancelOperation_argsStandardSchemeFactory implements SchemeFactory {
         private CancelOperation_argsStandardSchemeFactory() {
         }

         public CancelOperation_argsStandardScheme getScheme() {
            return new CancelOperation_argsStandardScheme();
         }
      }

      private static class CancelOperation_argsStandardScheme extends StandardScheme {
         private CancelOperation_argsStandardScheme() {
         }

         public void read(TProtocol iprot, CancelOperation_args struct) throws TException {
            iprot.readStructBegin();

            while(true) {
               TField schemeField = iprot.readFieldBegin();
               if (schemeField.type == 0) {
                  iprot.readStructEnd();
                  struct.validate();
                  return;
               }

               switch (schemeField.id) {
                  case 1:
                     if (schemeField.type == 12) {
                        struct.req = new TCancelOperationReq();
                        struct.req.read(iprot);
                        struct.setReqIsSet(true);
                     } else {
                        TProtocolUtil.skip(iprot, schemeField.type);
                     }
                     break;
                  default:
                     TProtocolUtil.skip(iprot, schemeField.type);
               }

               iprot.readFieldEnd();
            }
         }

         public void write(TProtocol oprot, CancelOperation_args struct) throws TException {
            struct.validate();
            oprot.writeStructBegin(TCLIService.CancelOperation_args.STRUCT_DESC);
            if (struct.req != null) {
               oprot.writeFieldBegin(TCLIService.CancelOperation_args.REQ_FIELD_DESC);
               struct.req.write(oprot);
               oprot.writeFieldEnd();
            }

            oprot.writeFieldStop();
            oprot.writeStructEnd();
         }
      }

      private static class CancelOperation_argsTupleSchemeFactory implements SchemeFactory {
         private CancelOperation_argsTupleSchemeFactory() {
         }

         public CancelOperation_argsTupleScheme getScheme() {
            return new CancelOperation_argsTupleScheme();
         }
      }

      private static class CancelOperation_argsTupleScheme extends TupleScheme {
         private CancelOperation_argsTupleScheme() {
         }

         public void write(TProtocol prot, CancelOperation_args struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol)prot;
            BitSet optionals = new BitSet();
            if (struct.isSetReq()) {
               optionals.set(0);
            }

            oprot.writeBitSet(optionals, 1);
            if (struct.isSetReq()) {
               struct.req.write(oprot);
            }

         }

         public void read(TProtocol prot, CancelOperation_args struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol)prot;
            BitSet incoming = iprot.readBitSet(1);
            if (incoming.get(0)) {
               struct.req = new TCancelOperationReq();
               struct.req.read(iprot);
               struct.setReqIsSet(true);
            }

         }
      }
   }

   @Public
   @Stable
   public static class CancelOperation_result implements TBase, Serializable, Cloneable, Comparable {
      private static final TStruct STRUCT_DESC = new TStruct("CancelOperation_result");
      private static final TField SUCCESS_FIELD_DESC = new TField("success", (byte)12, (short)0);
      private static final SchemeFactory STANDARD_SCHEME_FACTORY = new CancelOperation_resultStandardSchemeFactory();
      private static final SchemeFactory TUPLE_SCHEME_FACTORY = new CancelOperation_resultTupleSchemeFactory();
      @Nullable
      private TCancelOperationResp success;
      public static final Map metaDataMap;

      public CancelOperation_result() {
      }

      public CancelOperation_result(TCancelOperationResp success) {
         this();
         this.success = success;
      }

      public CancelOperation_result(CancelOperation_result other) {
         if (other.isSetSuccess()) {
            this.success = new TCancelOperationResp(other.success);
         }

      }

      public CancelOperation_result deepCopy() {
         return new CancelOperation_result(this);
      }

      public void clear() {
         this.success = null;
      }

      @Nullable
      public TCancelOperationResp getSuccess() {
         return this.success;
      }

      public void setSuccess(@Nullable TCancelOperationResp success) {
         this.success = success;
      }

      public void unsetSuccess() {
         this.success = null;
      }

      public boolean isSetSuccess() {
         return this.success != null;
      }

      public void setSuccessIsSet(boolean value) {
         if (!value) {
            this.success = null;
         }

      }

      public void setFieldValue(_Fields field, @Nullable Object value) {
         switch (field) {
            case SUCCESS:
               if (value == null) {
                  this.unsetSuccess();
               } else {
                  this.setSuccess((TCancelOperationResp)value);
               }
            default:
         }
      }

      @Nullable
      public Object getFieldValue(_Fields field) {
         switch (field) {
            case SUCCESS:
               return this.getSuccess();
            default:
               throw new IllegalStateException();
         }
      }

      public boolean isSet(_Fields field) {
         if (field == null) {
            throw new IllegalArgumentException();
         } else {
            switch (field) {
               case SUCCESS:
                  return this.isSetSuccess();
               default:
                  throw new IllegalStateException();
            }
         }
      }

      public boolean equals(Object that) {
         return that instanceof CancelOperation_result ? this.equals((CancelOperation_result)that) : false;
      }

      public boolean equals(CancelOperation_result that) {
         if (that == null) {
            return false;
         } else if (this == that) {
            return true;
         } else {
            boolean this_present_success = this.isSetSuccess();
            boolean that_present_success = that.isSetSuccess();
            if (this_present_success || that_present_success) {
               if (!this_present_success || !that_present_success) {
                  return false;
               }

               if (!this.success.equals(that.success)) {
                  return false;
               }
            }

            return true;
         }
      }

      public int hashCode() {
         int hashCode = 1;
         hashCode = hashCode * 8191 + (this.isSetSuccess() ? 131071 : 524287);
         if (this.isSetSuccess()) {
            hashCode = hashCode * 8191 + this.success.hashCode();
         }

         return hashCode;
      }

      public int compareTo(CancelOperation_result other) {
         if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
         } else {
            int lastComparison = 0;
            lastComparison = Boolean.compare(this.isSetSuccess(), other.isSetSuccess());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetSuccess()) {
                  lastComparison = TBaseHelper.compareTo(this.success, other.success);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               return 0;
            }
         }
      }

      @Nullable
      public _Fields fieldForId(int fieldId) {
         return TCLIService.CancelOperation_result._Fields.findByThriftId(fieldId);
      }

      public void read(TProtocol iprot) throws TException {
         scheme(iprot).read(iprot, this);
      }

      public void write(TProtocol oprot) throws TException {
         scheme(oprot).write(oprot, this);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("CancelOperation_result(");
         boolean first = true;
         sb.append("success:");
         if (this.success == null) {
            sb.append("null");
         } else {
            sb.append(this.success);
         }

         first = false;
         sb.append(")");
         return sb.toString();
      }

      public void validate() throws TException {
         if (this.success != null) {
            this.success.validate();
         }

      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         try {
            this.write(new TCompactProtocol(new TIOStreamTransport(out)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         try {
            this.read(new TCompactProtocol(new TIOStreamTransport(in)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private static IScheme scheme(TProtocol proto) {
         return (StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
      }

      static {
         Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
         tmpMap.put(TCLIService.CancelOperation_result._Fields.SUCCESS, new FieldMetaData("success", (byte)3, new StructMetaData((byte)12, TCancelOperationResp.class)));
         metaDataMap = Collections.unmodifiableMap(tmpMap);
         FieldMetaData.addStructMetaDataMap(CancelOperation_result.class, metaDataMap);
      }

      public static enum _Fields implements TFieldIdEnum {
         SUCCESS((short)0, "success");

         private static final Map byName = new HashMap();
         private final short _thriftId;
         private final String _fieldName;

         @Nullable
         public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
               case 0:
                  return SUCCESS;
               default:
                  return null;
            }
         }

         public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) {
               throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            } else {
               return fields;
            }
         }

         @Nullable
         public static _Fields findByName(String name) {
            return (_Fields)byName.get(name);
         }

         private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
         }

         public short getThriftFieldId() {
            return this._thriftId;
         }

         public String getFieldName() {
            return this._fieldName;
         }

         static {
            for(_Fields field : EnumSet.allOf(_Fields.class)) {
               byName.put(field.getFieldName(), field);
            }

         }
      }

      private static class CancelOperation_resultStandardSchemeFactory implements SchemeFactory {
         private CancelOperation_resultStandardSchemeFactory() {
         }

         public CancelOperation_resultStandardScheme getScheme() {
            return new CancelOperation_resultStandardScheme();
         }
      }

      private static class CancelOperation_resultStandardScheme extends StandardScheme {
         private CancelOperation_resultStandardScheme() {
         }

         public void read(TProtocol iprot, CancelOperation_result struct) throws TException {
            iprot.readStructBegin();

            while(true) {
               TField schemeField = iprot.readFieldBegin();
               if (schemeField.type == 0) {
                  iprot.readStructEnd();
                  struct.validate();
                  return;
               }

               switch (schemeField.id) {
                  case 0:
                     if (schemeField.type == 12) {
                        struct.success = new TCancelOperationResp();
                        struct.success.read(iprot);
                        struct.setSuccessIsSet(true);
                     } else {
                        TProtocolUtil.skip(iprot, schemeField.type);
                     }
                     break;
                  default:
                     TProtocolUtil.skip(iprot, schemeField.type);
               }

               iprot.readFieldEnd();
            }
         }

         public void write(TProtocol oprot, CancelOperation_result struct) throws TException {
            struct.validate();
            oprot.writeStructBegin(TCLIService.CancelOperation_result.STRUCT_DESC);
            if (struct.success != null) {
               oprot.writeFieldBegin(TCLIService.CancelOperation_result.SUCCESS_FIELD_DESC);
               struct.success.write(oprot);
               oprot.writeFieldEnd();
            }

            oprot.writeFieldStop();
            oprot.writeStructEnd();
         }
      }

      private static class CancelOperation_resultTupleSchemeFactory implements SchemeFactory {
         private CancelOperation_resultTupleSchemeFactory() {
         }

         public CancelOperation_resultTupleScheme getScheme() {
            return new CancelOperation_resultTupleScheme();
         }
      }

      private static class CancelOperation_resultTupleScheme extends TupleScheme {
         private CancelOperation_resultTupleScheme() {
         }

         public void write(TProtocol prot, CancelOperation_result struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol)prot;
            BitSet optionals = new BitSet();
            if (struct.isSetSuccess()) {
               optionals.set(0);
            }

            oprot.writeBitSet(optionals, 1);
            if (struct.isSetSuccess()) {
               struct.success.write(oprot);
            }

         }

         public void read(TProtocol prot, CancelOperation_result struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol)prot;
            BitSet incoming = iprot.readBitSet(1);
            if (incoming.get(0)) {
               struct.success = new TCancelOperationResp();
               struct.success.read(iprot);
               struct.setSuccessIsSet(true);
            }

         }
      }
   }

   @Public
   @Stable
   public static class CloseOperation_args implements TBase, Serializable, Cloneable, Comparable {
      private static final TStruct STRUCT_DESC = new TStruct("CloseOperation_args");
      private static final TField REQ_FIELD_DESC = new TField("req", (byte)12, (short)1);
      private static final SchemeFactory STANDARD_SCHEME_FACTORY = new CloseOperation_argsStandardSchemeFactory();
      private static final SchemeFactory TUPLE_SCHEME_FACTORY = new CloseOperation_argsTupleSchemeFactory();
      @Nullable
      private TCloseOperationReq req;
      public static final Map metaDataMap;

      public CloseOperation_args() {
      }

      public CloseOperation_args(TCloseOperationReq req) {
         this();
         this.req = req;
      }

      public CloseOperation_args(CloseOperation_args other) {
         if (other.isSetReq()) {
            this.req = new TCloseOperationReq(other.req);
         }

      }

      public CloseOperation_args deepCopy() {
         return new CloseOperation_args(this);
      }

      public void clear() {
         this.req = null;
      }

      @Nullable
      public TCloseOperationReq getReq() {
         return this.req;
      }

      public void setReq(@Nullable TCloseOperationReq req) {
         this.req = req;
      }

      public void unsetReq() {
         this.req = null;
      }

      public boolean isSetReq() {
         return this.req != null;
      }

      public void setReqIsSet(boolean value) {
         if (!value) {
            this.req = null;
         }

      }

      public void setFieldValue(_Fields field, @Nullable Object value) {
         switch (field) {
            case REQ:
               if (value == null) {
                  this.unsetReq();
               } else {
                  this.setReq((TCloseOperationReq)value);
               }
            default:
         }
      }

      @Nullable
      public Object getFieldValue(_Fields field) {
         switch (field) {
            case REQ:
               return this.getReq();
            default:
               throw new IllegalStateException();
         }
      }

      public boolean isSet(_Fields field) {
         if (field == null) {
            throw new IllegalArgumentException();
         } else {
            switch (field) {
               case REQ:
                  return this.isSetReq();
               default:
                  throw new IllegalStateException();
            }
         }
      }

      public boolean equals(Object that) {
         return that instanceof CloseOperation_args ? this.equals((CloseOperation_args)that) : false;
      }

      public boolean equals(CloseOperation_args that) {
         if (that == null) {
            return false;
         } else if (this == that) {
            return true;
         } else {
            boolean this_present_req = this.isSetReq();
            boolean that_present_req = that.isSetReq();
            if (this_present_req || that_present_req) {
               if (!this_present_req || !that_present_req) {
                  return false;
               }

               if (!this.req.equals(that.req)) {
                  return false;
               }
            }

            return true;
         }
      }

      public int hashCode() {
         int hashCode = 1;
         hashCode = hashCode * 8191 + (this.isSetReq() ? 131071 : 524287);
         if (this.isSetReq()) {
            hashCode = hashCode * 8191 + this.req.hashCode();
         }

         return hashCode;
      }

      public int compareTo(CloseOperation_args other) {
         if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
         } else {
            int lastComparison = 0;
            lastComparison = Boolean.compare(this.isSetReq(), other.isSetReq());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetReq()) {
                  lastComparison = TBaseHelper.compareTo(this.req, other.req);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               return 0;
            }
         }
      }

      @Nullable
      public _Fields fieldForId(int fieldId) {
         return TCLIService.CloseOperation_args._Fields.findByThriftId(fieldId);
      }

      public void read(TProtocol iprot) throws TException {
         scheme(iprot).read(iprot, this);
      }

      public void write(TProtocol oprot) throws TException {
         scheme(oprot).write(oprot, this);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("CloseOperation_args(");
         boolean first = true;
         sb.append("req:");
         if (this.req == null) {
            sb.append("null");
         } else {
            sb.append(this.req);
         }

         first = false;
         sb.append(")");
         return sb.toString();
      }

      public void validate() throws TException {
         if (this.req != null) {
            this.req.validate();
         }

      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         try {
            this.write(new TCompactProtocol(new TIOStreamTransport(out)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         try {
            this.read(new TCompactProtocol(new TIOStreamTransport(in)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private static IScheme scheme(TProtocol proto) {
         return (StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
      }

      static {
         Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
         tmpMap.put(TCLIService.CloseOperation_args._Fields.REQ, new FieldMetaData("req", (byte)3, new StructMetaData((byte)12, TCloseOperationReq.class)));
         metaDataMap = Collections.unmodifiableMap(tmpMap);
         FieldMetaData.addStructMetaDataMap(CloseOperation_args.class, metaDataMap);
      }

      public static enum _Fields implements TFieldIdEnum {
         REQ((short)1, "req");

         private static final Map byName = new HashMap();
         private final short _thriftId;
         private final String _fieldName;

         @Nullable
         public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
               case 1:
                  return REQ;
               default:
                  return null;
            }
         }

         public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) {
               throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            } else {
               return fields;
            }
         }

         @Nullable
         public static _Fields findByName(String name) {
            return (_Fields)byName.get(name);
         }

         private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
         }

         public short getThriftFieldId() {
            return this._thriftId;
         }

         public String getFieldName() {
            return this._fieldName;
         }

         static {
            for(_Fields field : EnumSet.allOf(_Fields.class)) {
               byName.put(field.getFieldName(), field);
            }

         }
      }

      private static class CloseOperation_argsStandardSchemeFactory implements SchemeFactory {
         private CloseOperation_argsStandardSchemeFactory() {
         }

         public CloseOperation_argsStandardScheme getScheme() {
            return new CloseOperation_argsStandardScheme();
         }
      }

      private static class CloseOperation_argsStandardScheme extends StandardScheme {
         private CloseOperation_argsStandardScheme() {
         }

         public void read(TProtocol iprot, CloseOperation_args struct) throws TException {
            iprot.readStructBegin();

            while(true) {
               TField schemeField = iprot.readFieldBegin();
               if (schemeField.type == 0) {
                  iprot.readStructEnd();
                  struct.validate();
                  return;
               }

               switch (schemeField.id) {
                  case 1:
                     if (schemeField.type == 12) {
                        struct.req = new TCloseOperationReq();
                        struct.req.read(iprot);
                        struct.setReqIsSet(true);
                     } else {
                        TProtocolUtil.skip(iprot, schemeField.type);
                     }
                     break;
                  default:
                     TProtocolUtil.skip(iprot, schemeField.type);
               }

               iprot.readFieldEnd();
            }
         }

         public void write(TProtocol oprot, CloseOperation_args struct) throws TException {
            struct.validate();
            oprot.writeStructBegin(TCLIService.CloseOperation_args.STRUCT_DESC);
            if (struct.req != null) {
               oprot.writeFieldBegin(TCLIService.CloseOperation_args.REQ_FIELD_DESC);
               struct.req.write(oprot);
               oprot.writeFieldEnd();
            }

            oprot.writeFieldStop();
            oprot.writeStructEnd();
         }
      }

      private static class CloseOperation_argsTupleSchemeFactory implements SchemeFactory {
         private CloseOperation_argsTupleSchemeFactory() {
         }

         public CloseOperation_argsTupleScheme getScheme() {
            return new CloseOperation_argsTupleScheme();
         }
      }

      private static class CloseOperation_argsTupleScheme extends TupleScheme {
         private CloseOperation_argsTupleScheme() {
         }

         public void write(TProtocol prot, CloseOperation_args struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol)prot;
            BitSet optionals = new BitSet();
            if (struct.isSetReq()) {
               optionals.set(0);
            }

            oprot.writeBitSet(optionals, 1);
            if (struct.isSetReq()) {
               struct.req.write(oprot);
            }

         }

         public void read(TProtocol prot, CloseOperation_args struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol)prot;
            BitSet incoming = iprot.readBitSet(1);
            if (incoming.get(0)) {
               struct.req = new TCloseOperationReq();
               struct.req.read(iprot);
               struct.setReqIsSet(true);
            }

         }
      }
   }

   @Public
   @Stable
   public static class CloseOperation_result implements TBase, Serializable, Cloneable, Comparable {
      private static final TStruct STRUCT_DESC = new TStruct("CloseOperation_result");
      private static final TField SUCCESS_FIELD_DESC = new TField("success", (byte)12, (short)0);
      private static final SchemeFactory STANDARD_SCHEME_FACTORY = new CloseOperation_resultStandardSchemeFactory();
      private static final SchemeFactory TUPLE_SCHEME_FACTORY = new CloseOperation_resultTupleSchemeFactory();
      @Nullable
      private TCloseOperationResp success;
      public static final Map metaDataMap;

      public CloseOperation_result() {
      }

      public CloseOperation_result(TCloseOperationResp success) {
         this();
         this.success = success;
      }

      public CloseOperation_result(CloseOperation_result other) {
         if (other.isSetSuccess()) {
            this.success = new TCloseOperationResp(other.success);
         }

      }

      public CloseOperation_result deepCopy() {
         return new CloseOperation_result(this);
      }

      public void clear() {
         this.success = null;
      }

      @Nullable
      public TCloseOperationResp getSuccess() {
         return this.success;
      }

      public void setSuccess(@Nullable TCloseOperationResp success) {
         this.success = success;
      }

      public void unsetSuccess() {
         this.success = null;
      }

      public boolean isSetSuccess() {
         return this.success != null;
      }

      public void setSuccessIsSet(boolean value) {
         if (!value) {
            this.success = null;
         }

      }

      public void setFieldValue(_Fields field, @Nullable Object value) {
         switch (field) {
            case SUCCESS:
               if (value == null) {
                  this.unsetSuccess();
               } else {
                  this.setSuccess((TCloseOperationResp)value);
               }
            default:
         }
      }

      @Nullable
      public Object getFieldValue(_Fields field) {
         switch (field) {
            case SUCCESS:
               return this.getSuccess();
            default:
               throw new IllegalStateException();
         }
      }

      public boolean isSet(_Fields field) {
         if (field == null) {
            throw new IllegalArgumentException();
         } else {
            switch (field) {
               case SUCCESS:
                  return this.isSetSuccess();
               default:
                  throw new IllegalStateException();
            }
         }
      }

      public boolean equals(Object that) {
         return that instanceof CloseOperation_result ? this.equals((CloseOperation_result)that) : false;
      }

      public boolean equals(CloseOperation_result that) {
         if (that == null) {
            return false;
         } else if (this == that) {
            return true;
         } else {
            boolean this_present_success = this.isSetSuccess();
            boolean that_present_success = that.isSetSuccess();
            if (this_present_success || that_present_success) {
               if (!this_present_success || !that_present_success) {
                  return false;
               }

               if (!this.success.equals(that.success)) {
                  return false;
               }
            }

            return true;
         }
      }

      public int hashCode() {
         int hashCode = 1;
         hashCode = hashCode * 8191 + (this.isSetSuccess() ? 131071 : 524287);
         if (this.isSetSuccess()) {
            hashCode = hashCode * 8191 + this.success.hashCode();
         }

         return hashCode;
      }

      public int compareTo(CloseOperation_result other) {
         if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
         } else {
            int lastComparison = 0;
            lastComparison = Boolean.compare(this.isSetSuccess(), other.isSetSuccess());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetSuccess()) {
                  lastComparison = TBaseHelper.compareTo(this.success, other.success);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               return 0;
            }
         }
      }

      @Nullable
      public _Fields fieldForId(int fieldId) {
         return TCLIService.CloseOperation_result._Fields.findByThriftId(fieldId);
      }

      public void read(TProtocol iprot) throws TException {
         scheme(iprot).read(iprot, this);
      }

      public void write(TProtocol oprot) throws TException {
         scheme(oprot).write(oprot, this);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("CloseOperation_result(");
         boolean first = true;
         sb.append("success:");
         if (this.success == null) {
            sb.append("null");
         } else {
            sb.append(this.success);
         }

         first = false;
         sb.append(")");
         return sb.toString();
      }

      public void validate() throws TException {
         if (this.success != null) {
            this.success.validate();
         }

      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         try {
            this.write(new TCompactProtocol(new TIOStreamTransport(out)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         try {
            this.read(new TCompactProtocol(new TIOStreamTransport(in)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private static IScheme scheme(TProtocol proto) {
         return (StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
      }

      static {
         Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
         tmpMap.put(TCLIService.CloseOperation_result._Fields.SUCCESS, new FieldMetaData("success", (byte)3, new StructMetaData((byte)12, TCloseOperationResp.class)));
         metaDataMap = Collections.unmodifiableMap(tmpMap);
         FieldMetaData.addStructMetaDataMap(CloseOperation_result.class, metaDataMap);
      }

      public static enum _Fields implements TFieldIdEnum {
         SUCCESS((short)0, "success");

         private static final Map byName = new HashMap();
         private final short _thriftId;
         private final String _fieldName;

         @Nullable
         public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
               case 0:
                  return SUCCESS;
               default:
                  return null;
            }
         }

         public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) {
               throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            } else {
               return fields;
            }
         }

         @Nullable
         public static _Fields findByName(String name) {
            return (_Fields)byName.get(name);
         }

         private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
         }

         public short getThriftFieldId() {
            return this._thriftId;
         }

         public String getFieldName() {
            return this._fieldName;
         }

         static {
            for(_Fields field : EnumSet.allOf(_Fields.class)) {
               byName.put(field.getFieldName(), field);
            }

         }
      }

      private static class CloseOperation_resultStandardSchemeFactory implements SchemeFactory {
         private CloseOperation_resultStandardSchemeFactory() {
         }

         public CloseOperation_resultStandardScheme getScheme() {
            return new CloseOperation_resultStandardScheme();
         }
      }

      private static class CloseOperation_resultStandardScheme extends StandardScheme {
         private CloseOperation_resultStandardScheme() {
         }

         public void read(TProtocol iprot, CloseOperation_result struct) throws TException {
            iprot.readStructBegin();

            while(true) {
               TField schemeField = iprot.readFieldBegin();
               if (schemeField.type == 0) {
                  iprot.readStructEnd();
                  struct.validate();
                  return;
               }

               switch (schemeField.id) {
                  case 0:
                     if (schemeField.type == 12) {
                        struct.success = new TCloseOperationResp();
                        struct.success.read(iprot);
                        struct.setSuccessIsSet(true);
                     } else {
                        TProtocolUtil.skip(iprot, schemeField.type);
                     }
                     break;
                  default:
                     TProtocolUtil.skip(iprot, schemeField.type);
               }

               iprot.readFieldEnd();
            }
         }

         public void write(TProtocol oprot, CloseOperation_result struct) throws TException {
            struct.validate();
            oprot.writeStructBegin(TCLIService.CloseOperation_result.STRUCT_DESC);
            if (struct.success != null) {
               oprot.writeFieldBegin(TCLIService.CloseOperation_result.SUCCESS_FIELD_DESC);
               struct.success.write(oprot);
               oprot.writeFieldEnd();
            }

            oprot.writeFieldStop();
            oprot.writeStructEnd();
         }
      }

      private static class CloseOperation_resultTupleSchemeFactory implements SchemeFactory {
         private CloseOperation_resultTupleSchemeFactory() {
         }

         public CloseOperation_resultTupleScheme getScheme() {
            return new CloseOperation_resultTupleScheme();
         }
      }

      private static class CloseOperation_resultTupleScheme extends TupleScheme {
         private CloseOperation_resultTupleScheme() {
         }

         public void write(TProtocol prot, CloseOperation_result struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol)prot;
            BitSet optionals = new BitSet();
            if (struct.isSetSuccess()) {
               optionals.set(0);
            }

            oprot.writeBitSet(optionals, 1);
            if (struct.isSetSuccess()) {
               struct.success.write(oprot);
            }

         }

         public void read(TProtocol prot, CloseOperation_result struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol)prot;
            BitSet incoming = iprot.readBitSet(1);
            if (incoming.get(0)) {
               struct.success = new TCloseOperationResp();
               struct.success.read(iprot);
               struct.setSuccessIsSet(true);
            }

         }
      }
   }

   @Public
   @Stable
   public static class GetResultSetMetadata_args implements TBase, Serializable, Cloneable, Comparable {
      private static final TStruct STRUCT_DESC = new TStruct("GetResultSetMetadata_args");
      private static final TField REQ_FIELD_DESC = new TField("req", (byte)12, (short)1);
      private static final SchemeFactory STANDARD_SCHEME_FACTORY = new GetResultSetMetadata_argsStandardSchemeFactory();
      private static final SchemeFactory TUPLE_SCHEME_FACTORY = new GetResultSetMetadata_argsTupleSchemeFactory();
      @Nullable
      private TGetResultSetMetadataReq req;
      public static final Map metaDataMap;

      public GetResultSetMetadata_args() {
      }

      public GetResultSetMetadata_args(TGetResultSetMetadataReq req) {
         this();
         this.req = req;
      }

      public GetResultSetMetadata_args(GetResultSetMetadata_args other) {
         if (other.isSetReq()) {
            this.req = new TGetResultSetMetadataReq(other.req);
         }

      }

      public GetResultSetMetadata_args deepCopy() {
         return new GetResultSetMetadata_args(this);
      }

      public void clear() {
         this.req = null;
      }

      @Nullable
      public TGetResultSetMetadataReq getReq() {
         return this.req;
      }

      public void setReq(@Nullable TGetResultSetMetadataReq req) {
         this.req = req;
      }

      public void unsetReq() {
         this.req = null;
      }

      public boolean isSetReq() {
         return this.req != null;
      }

      public void setReqIsSet(boolean value) {
         if (!value) {
            this.req = null;
         }

      }

      public void setFieldValue(_Fields field, @Nullable Object value) {
         switch (field) {
            case REQ:
               if (value == null) {
                  this.unsetReq();
               } else {
                  this.setReq((TGetResultSetMetadataReq)value);
               }
            default:
         }
      }

      @Nullable
      public Object getFieldValue(_Fields field) {
         switch (field) {
            case REQ:
               return this.getReq();
            default:
               throw new IllegalStateException();
         }
      }

      public boolean isSet(_Fields field) {
         if (field == null) {
            throw new IllegalArgumentException();
         } else {
            switch (field) {
               case REQ:
                  return this.isSetReq();
               default:
                  throw new IllegalStateException();
            }
         }
      }

      public boolean equals(Object that) {
         return that instanceof GetResultSetMetadata_args ? this.equals((GetResultSetMetadata_args)that) : false;
      }

      public boolean equals(GetResultSetMetadata_args that) {
         if (that == null) {
            return false;
         } else if (this == that) {
            return true;
         } else {
            boolean this_present_req = this.isSetReq();
            boolean that_present_req = that.isSetReq();
            if (this_present_req || that_present_req) {
               if (!this_present_req || !that_present_req) {
                  return false;
               }

               if (!this.req.equals(that.req)) {
                  return false;
               }
            }

            return true;
         }
      }

      public int hashCode() {
         int hashCode = 1;
         hashCode = hashCode * 8191 + (this.isSetReq() ? 131071 : 524287);
         if (this.isSetReq()) {
            hashCode = hashCode * 8191 + this.req.hashCode();
         }

         return hashCode;
      }

      public int compareTo(GetResultSetMetadata_args other) {
         if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
         } else {
            int lastComparison = 0;
            lastComparison = Boolean.compare(this.isSetReq(), other.isSetReq());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetReq()) {
                  lastComparison = TBaseHelper.compareTo(this.req, other.req);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               return 0;
            }
         }
      }

      @Nullable
      public _Fields fieldForId(int fieldId) {
         return TCLIService.GetResultSetMetadata_args._Fields.findByThriftId(fieldId);
      }

      public void read(TProtocol iprot) throws TException {
         scheme(iprot).read(iprot, this);
      }

      public void write(TProtocol oprot) throws TException {
         scheme(oprot).write(oprot, this);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("GetResultSetMetadata_args(");
         boolean first = true;
         sb.append("req:");
         if (this.req == null) {
            sb.append("null");
         } else {
            sb.append(this.req);
         }

         first = false;
         sb.append(")");
         return sb.toString();
      }

      public void validate() throws TException {
         if (this.req != null) {
            this.req.validate();
         }

      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         try {
            this.write(new TCompactProtocol(new TIOStreamTransport(out)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         try {
            this.read(new TCompactProtocol(new TIOStreamTransport(in)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private static IScheme scheme(TProtocol proto) {
         return (StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
      }

      static {
         Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
         tmpMap.put(TCLIService.GetResultSetMetadata_args._Fields.REQ, new FieldMetaData("req", (byte)3, new StructMetaData((byte)12, TGetResultSetMetadataReq.class)));
         metaDataMap = Collections.unmodifiableMap(tmpMap);
         FieldMetaData.addStructMetaDataMap(GetResultSetMetadata_args.class, metaDataMap);
      }

      public static enum _Fields implements TFieldIdEnum {
         REQ((short)1, "req");

         private static final Map byName = new HashMap();
         private final short _thriftId;
         private final String _fieldName;

         @Nullable
         public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
               case 1:
                  return REQ;
               default:
                  return null;
            }
         }

         public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) {
               throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            } else {
               return fields;
            }
         }

         @Nullable
         public static _Fields findByName(String name) {
            return (_Fields)byName.get(name);
         }

         private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
         }

         public short getThriftFieldId() {
            return this._thriftId;
         }

         public String getFieldName() {
            return this._fieldName;
         }

         static {
            for(_Fields field : EnumSet.allOf(_Fields.class)) {
               byName.put(field.getFieldName(), field);
            }

         }
      }

      private static class GetResultSetMetadata_argsStandardSchemeFactory implements SchemeFactory {
         private GetResultSetMetadata_argsStandardSchemeFactory() {
         }

         public GetResultSetMetadata_argsStandardScheme getScheme() {
            return new GetResultSetMetadata_argsStandardScheme();
         }
      }

      private static class GetResultSetMetadata_argsStandardScheme extends StandardScheme {
         private GetResultSetMetadata_argsStandardScheme() {
         }

         public void read(TProtocol iprot, GetResultSetMetadata_args struct) throws TException {
            iprot.readStructBegin();

            while(true) {
               TField schemeField = iprot.readFieldBegin();
               if (schemeField.type == 0) {
                  iprot.readStructEnd();
                  struct.validate();
                  return;
               }

               switch (schemeField.id) {
                  case 1:
                     if (schemeField.type == 12) {
                        struct.req = new TGetResultSetMetadataReq();
                        struct.req.read(iprot);
                        struct.setReqIsSet(true);
                     } else {
                        TProtocolUtil.skip(iprot, schemeField.type);
                     }
                     break;
                  default:
                     TProtocolUtil.skip(iprot, schemeField.type);
               }

               iprot.readFieldEnd();
            }
         }

         public void write(TProtocol oprot, GetResultSetMetadata_args struct) throws TException {
            struct.validate();
            oprot.writeStructBegin(TCLIService.GetResultSetMetadata_args.STRUCT_DESC);
            if (struct.req != null) {
               oprot.writeFieldBegin(TCLIService.GetResultSetMetadata_args.REQ_FIELD_DESC);
               struct.req.write(oprot);
               oprot.writeFieldEnd();
            }

            oprot.writeFieldStop();
            oprot.writeStructEnd();
         }
      }

      private static class GetResultSetMetadata_argsTupleSchemeFactory implements SchemeFactory {
         private GetResultSetMetadata_argsTupleSchemeFactory() {
         }

         public GetResultSetMetadata_argsTupleScheme getScheme() {
            return new GetResultSetMetadata_argsTupleScheme();
         }
      }

      private static class GetResultSetMetadata_argsTupleScheme extends TupleScheme {
         private GetResultSetMetadata_argsTupleScheme() {
         }

         public void write(TProtocol prot, GetResultSetMetadata_args struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol)prot;
            BitSet optionals = new BitSet();
            if (struct.isSetReq()) {
               optionals.set(0);
            }

            oprot.writeBitSet(optionals, 1);
            if (struct.isSetReq()) {
               struct.req.write(oprot);
            }

         }

         public void read(TProtocol prot, GetResultSetMetadata_args struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol)prot;
            BitSet incoming = iprot.readBitSet(1);
            if (incoming.get(0)) {
               struct.req = new TGetResultSetMetadataReq();
               struct.req.read(iprot);
               struct.setReqIsSet(true);
            }

         }
      }
   }

   @Public
   @Stable
   public static class GetResultSetMetadata_result implements TBase, Serializable, Cloneable, Comparable {
      private static final TStruct STRUCT_DESC = new TStruct("GetResultSetMetadata_result");
      private static final TField SUCCESS_FIELD_DESC = new TField("success", (byte)12, (short)0);
      private static final SchemeFactory STANDARD_SCHEME_FACTORY = new GetResultSetMetadata_resultStandardSchemeFactory();
      private static final SchemeFactory TUPLE_SCHEME_FACTORY = new GetResultSetMetadata_resultTupleSchemeFactory();
      @Nullable
      private TGetResultSetMetadataResp success;
      public static final Map metaDataMap;

      public GetResultSetMetadata_result() {
      }

      public GetResultSetMetadata_result(TGetResultSetMetadataResp success) {
         this();
         this.success = success;
      }

      public GetResultSetMetadata_result(GetResultSetMetadata_result other) {
         if (other.isSetSuccess()) {
            this.success = new TGetResultSetMetadataResp(other.success);
         }

      }

      public GetResultSetMetadata_result deepCopy() {
         return new GetResultSetMetadata_result(this);
      }

      public void clear() {
         this.success = null;
      }

      @Nullable
      public TGetResultSetMetadataResp getSuccess() {
         return this.success;
      }

      public void setSuccess(@Nullable TGetResultSetMetadataResp success) {
         this.success = success;
      }

      public void unsetSuccess() {
         this.success = null;
      }

      public boolean isSetSuccess() {
         return this.success != null;
      }

      public void setSuccessIsSet(boolean value) {
         if (!value) {
            this.success = null;
         }

      }

      public void setFieldValue(_Fields field, @Nullable Object value) {
         switch (field) {
            case SUCCESS:
               if (value == null) {
                  this.unsetSuccess();
               } else {
                  this.setSuccess((TGetResultSetMetadataResp)value);
               }
            default:
         }
      }

      @Nullable
      public Object getFieldValue(_Fields field) {
         switch (field) {
            case SUCCESS:
               return this.getSuccess();
            default:
               throw new IllegalStateException();
         }
      }

      public boolean isSet(_Fields field) {
         if (field == null) {
            throw new IllegalArgumentException();
         } else {
            switch (field) {
               case SUCCESS:
                  return this.isSetSuccess();
               default:
                  throw new IllegalStateException();
            }
         }
      }

      public boolean equals(Object that) {
         return that instanceof GetResultSetMetadata_result ? this.equals((GetResultSetMetadata_result)that) : false;
      }

      public boolean equals(GetResultSetMetadata_result that) {
         if (that == null) {
            return false;
         } else if (this == that) {
            return true;
         } else {
            boolean this_present_success = this.isSetSuccess();
            boolean that_present_success = that.isSetSuccess();
            if (this_present_success || that_present_success) {
               if (!this_present_success || !that_present_success) {
                  return false;
               }

               if (!this.success.equals(that.success)) {
                  return false;
               }
            }

            return true;
         }
      }

      public int hashCode() {
         int hashCode = 1;
         hashCode = hashCode * 8191 + (this.isSetSuccess() ? 131071 : 524287);
         if (this.isSetSuccess()) {
            hashCode = hashCode * 8191 + this.success.hashCode();
         }

         return hashCode;
      }

      public int compareTo(GetResultSetMetadata_result other) {
         if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
         } else {
            int lastComparison = 0;
            lastComparison = Boolean.compare(this.isSetSuccess(), other.isSetSuccess());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetSuccess()) {
                  lastComparison = TBaseHelper.compareTo(this.success, other.success);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               return 0;
            }
         }
      }

      @Nullable
      public _Fields fieldForId(int fieldId) {
         return TCLIService.GetResultSetMetadata_result._Fields.findByThriftId(fieldId);
      }

      public void read(TProtocol iprot) throws TException {
         scheme(iprot).read(iprot, this);
      }

      public void write(TProtocol oprot) throws TException {
         scheme(oprot).write(oprot, this);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("GetResultSetMetadata_result(");
         boolean first = true;
         sb.append("success:");
         if (this.success == null) {
            sb.append("null");
         } else {
            sb.append(this.success);
         }

         first = false;
         sb.append(")");
         return sb.toString();
      }

      public void validate() throws TException {
         if (this.success != null) {
            this.success.validate();
         }

      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         try {
            this.write(new TCompactProtocol(new TIOStreamTransport(out)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         try {
            this.read(new TCompactProtocol(new TIOStreamTransport(in)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private static IScheme scheme(TProtocol proto) {
         return (StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
      }

      static {
         Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
         tmpMap.put(TCLIService.GetResultSetMetadata_result._Fields.SUCCESS, new FieldMetaData("success", (byte)3, new StructMetaData((byte)12, TGetResultSetMetadataResp.class)));
         metaDataMap = Collections.unmodifiableMap(tmpMap);
         FieldMetaData.addStructMetaDataMap(GetResultSetMetadata_result.class, metaDataMap);
      }

      public static enum _Fields implements TFieldIdEnum {
         SUCCESS((short)0, "success");

         private static final Map byName = new HashMap();
         private final short _thriftId;
         private final String _fieldName;

         @Nullable
         public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
               case 0:
                  return SUCCESS;
               default:
                  return null;
            }
         }

         public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) {
               throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            } else {
               return fields;
            }
         }

         @Nullable
         public static _Fields findByName(String name) {
            return (_Fields)byName.get(name);
         }

         private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
         }

         public short getThriftFieldId() {
            return this._thriftId;
         }

         public String getFieldName() {
            return this._fieldName;
         }

         static {
            for(_Fields field : EnumSet.allOf(_Fields.class)) {
               byName.put(field.getFieldName(), field);
            }

         }
      }

      private static class GetResultSetMetadata_resultStandardSchemeFactory implements SchemeFactory {
         private GetResultSetMetadata_resultStandardSchemeFactory() {
         }

         public GetResultSetMetadata_resultStandardScheme getScheme() {
            return new GetResultSetMetadata_resultStandardScheme();
         }
      }

      private static class GetResultSetMetadata_resultStandardScheme extends StandardScheme {
         private GetResultSetMetadata_resultStandardScheme() {
         }

         public void read(TProtocol iprot, GetResultSetMetadata_result struct) throws TException {
            iprot.readStructBegin();

            while(true) {
               TField schemeField = iprot.readFieldBegin();
               if (schemeField.type == 0) {
                  iprot.readStructEnd();
                  struct.validate();
                  return;
               }

               switch (schemeField.id) {
                  case 0:
                     if (schemeField.type == 12) {
                        struct.success = new TGetResultSetMetadataResp();
                        struct.success.read(iprot);
                        struct.setSuccessIsSet(true);
                     } else {
                        TProtocolUtil.skip(iprot, schemeField.type);
                     }
                     break;
                  default:
                     TProtocolUtil.skip(iprot, schemeField.type);
               }

               iprot.readFieldEnd();
            }
         }

         public void write(TProtocol oprot, GetResultSetMetadata_result struct) throws TException {
            struct.validate();
            oprot.writeStructBegin(TCLIService.GetResultSetMetadata_result.STRUCT_DESC);
            if (struct.success != null) {
               oprot.writeFieldBegin(TCLIService.GetResultSetMetadata_result.SUCCESS_FIELD_DESC);
               struct.success.write(oprot);
               oprot.writeFieldEnd();
            }

            oprot.writeFieldStop();
            oprot.writeStructEnd();
         }
      }

      private static class GetResultSetMetadata_resultTupleSchemeFactory implements SchemeFactory {
         private GetResultSetMetadata_resultTupleSchemeFactory() {
         }

         public GetResultSetMetadata_resultTupleScheme getScheme() {
            return new GetResultSetMetadata_resultTupleScheme();
         }
      }

      private static class GetResultSetMetadata_resultTupleScheme extends TupleScheme {
         private GetResultSetMetadata_resultTupleScheme() {
         }

         public void write(TProtocol prot, GetResultSetMetadata_result struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol)prot;
            BitSet optionals = new BitSet();
            if (struct.isSetSuccess()) {
               optionals.set(0);
            }

            oprot.writeBitSet(optionals, 1);
            if (struct.isSetSuccess()) {
               struct.success.write(oprot);
            }

         }

         public void read(TProtocol prot, GetResultSetMetadata_result struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol)prot;
            BitSet incoming = iprot.readBitSet(1);
            if (incoming.get(0)) {
               struct.success = new TGetResultSetMetadataResp();
               struct.success.read(iprot);
               struct.setSuccessIsSet(true);
            }

         }
      }
   }

   @Public
   @Stable
   public static class FetchResults_args implements TBase, Serializable, Cloneable, Comparable {
      private static final TStruct STRUCT_DESC = new TStruct("FetchResults_args");
      private static final TField REQ_FIELD_DESC = new TField("req", (byte)12, (short)1);
      private static final SchemeFactory STANDARD_SCHEME_FACTORY = new FetchResults_argsStandardSchemeFactory();
      private static final SchemeFactory TUPLE_SCHEME_FACTORY = new FetchResults_argsTupleSchemeFactory();
      @Nullable
      private TFetchResultsReq req;
      public static final Map metaDataMap;

      public FetchResults_args() {
      }

      public FetchResults_args(TFetchResultsReq req) {
         this();
         this.req = req;
      }

      public FetchResults_args(FetchResults_args other) {
         if (other.isSetReq()) {
            this.req = new TFetchResultsReq(other.req);
         }

      }

      public FetchResults_args deepCopy() {
         return new FetchResults_args(this);
      }

      public void clear() {
         this.req = null;
      }

      @Nullable
      public TFetchResultsReq getReq() {
         return this.req;
      }

      public void setReq(@Nullable TFetchResultsReq req) {
         this.req = req;
      }

      public void unsetReq() {
         this.req = null;
      }

      public boolean isSetReq() {
         return this.req != null;
      }

      public void setReqIsSet(boolean value) {
         if (!value) {
            this.req = null;
         }

      }

      public void setFieldValue(_Fields field, @Nullable Object value) {
         switch (field) {
            case REQ:
               if (value == null) {
                  this.unsetReq();
               } else {
                  this.setReq((TFetchResultsReq)value);
               }
            default:
         }
      }

      @Nullable
      public Object getFieldValue(_Fields field) {
         switch (field) {
            case REQ:
               return this.getReq();
            default:
               throw new IllegalStateException();
         }
      }

      public boolean isSet(_Fields field) {
         if (field == null) {
            throw new IllegalArgumentException();
         } else {
            switch (field) {
               case REQ:
                  return this.isSetReq();
               default:
                  throw new IllegalStateException();
            }
         }
      }

      public boolean equals(Object that) {
         return that instanceof FetchResults_args ? this.equals((FetchResults_args)that) : false;
      }

      public boolean equals(FetchResults_args that) {
         if (that == null) {
            return false;
         } else if (this == that) {
            return true;
         } else {
            boolean this_present_req = this.isSetReq();
            boolean that_present_req = that.isSetReq();
            if (this_present_req || that_present_req) {
               if (!this_present_req || !that_present_req) {
                  return false;
               }

               if (!this.req.equals(that.req)) {
                  return false;
               }
            }

            return true;
         }
      }

      public int hashCode() {
         int hashCode = 1;
         hashCode = hashCode * 8191 + (this.isSetReq() ? 131071 : 524287);
         if (this.isSetReq()) {
            hashCode = hashCode * 8191 + this.req.hashCode();
         }

         return hashCode;
      }

      public int compareTo(FetchResults_args other) {
         if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
         } else {
            int lastComparison = 0;
            lastComparison = Boolean.compare(this.isSetReq(), other.isSetReq());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetReq()) {
                  lastComparison = TBaseHelper.compareTo(this.req, other.req);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               return 0;
            }
         }
      }

      @Nullable
      public _Fields fieldForId(int fieldId) {
         return TCLIService.FetchResults_args._Fields.findByThriftId(fieldId);
      }

      public void read(TProtocol iprot) throws TException {
         scheme(iprot).read(iprot, this);
      }

      public void write(TProtocol oprot) throws TException {
         scheme(oprot).write(oprot, this);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("FetchResults_args(");
         boolean first = true;
         sb.append("req:");
         if (this.req == null) {
            sb.append("null");
         } else {
            sb.append(this.req);
         }

         first = false;
         sb.append(")");
         return sb.toString();
      }

      public void validate() throws TException {
         if (this.req != null) {
            this.req.validate();
         }

      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         try {
            this.write(new TCompactProtocol(new TIOStreamTransport(out)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         try {
            this.read(new TCompactProtocol(new TIOStreamTransport(in)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private static IScheme scheme(TProtocol proto) {
         return (StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
      }

      static {
         Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
         tmpMap.put(TCLIService.FetchResults_args._Fields.REQ, new FieldMetaData("req", (byte)3, new StructMetaData((byte)12, TFetchResultsReq.class)));
         metaDataMap = Collections.unmodifiableMap(tmpMap);
         FieldMetaData.addStructMetaDataMap(FetchResults_args.class, metaDataMap);
      }

      public static enum _Fields implements TFieldIdEnum {
         REQ((short)1, "req");

         private static final Map byName = new HashMap();
         private final short _thriftId;
         private final String _fieldName;

         @Nullable
         public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
               case 1:
                  return REQ;
               default:
                  return null;
            }
         }

         public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) {
               throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            } else {
               return fields;
            }
         }

         @Nullable
         public static _Fields findByName(String name) {
            return (_Fields)byName.get(name);
         }

         private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
         }

         public short getThriftFieldId() {
            return this._thriftId;
         }

         public String getFieldName() {
            return this._fieldName;
         }

         static {
            for(_Fields field : EnumSet.allOf(_Fields.class)) {
               byName.put(field.getFieldName(), field);
            }

         }
      }

      private static class FetchResults_argsStandardSchemeFactory implements SchemeFactory {
         private FetchResults_argsStandardSchemeFactory() {
         }

         public FetchResults_argsStandardScheme getScheme() {
            return new FetchResults_argsStandardScheme();
         }
      }

      private static class FetchResults_argsStandardScheme extends StandardScheme {
         private FetchResults_argsStandardScheme() {
         }

         public void read(TProtocol iprot, FetchResults_args struct) throws TException {
            iprot.readStructBegin();

            while(true) {
               TField schemeField = iprot.readFieldBegin();
               if (schemeField.type == 0) {
                  iprot.readStructEnd();
                  struct.validate();
                  return;
               }

               switch (schemeField.id) {
                  case 1:
                     if (schemeField.type == 12) {
                        struct.req = new TFetchResultsReq();
                        struct.req.read(iprot);
                        struct.setReqIsSet(true);
                     } else {
                        TProtocolUtil.skip(iprot, schemeField.type);
                     }
                     break;
                  default:
                     TProtocolUtil.skip(iprot, schemeField.type);
               }

               iprot.readFieldEnd();
            }
         }

         public void write(TProtocol oprot, FetchResults_args struct) throws TException {
            struct.validate();
            oprot.writeStructBegin(TCLIService.FetchResults_args.STRUCT_DESC);
            if (struct.req != null) {
               oprot.writeFieldBegin(TCLIService.FetchResults_args.REQ_FIELD_DESC);
               struct.req.write(oprot);
               oprot.writeFieldEnd();
            }

            oprot.writeFieldStop();
            oprot.writeStructEnd();
         }
      }

      private static class FetchResults_argsTupleSchemeFactory implements SchemeFactory {
         private FetchResults_argsTupleSchemeFactory() {
         }

         public FetchResults_argsTupleScheme getScheme() {
            return new FetchResults_argsTupleScheme();
         }
      }

      private static class FetchResults_argsTupleScheme extends TupleScheme {
         private FetchResults_argsTupleScheme() {
         }

         public void write(TProtocol prot, FetchResults_args struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol)prot;
            BitSet optionals = new BitSet();
            if (struct.isSetReq()) {
               optionals.set(0);
            }

            oprot.writeBitSet(optionals, 1);
            if (struct.isSetReq()) {
               struct.req.write(oprot);
            }

         }

         public void read(TProtocol prot, FetchResults_args struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol)prot;
            BitSet incoming = iprot.readBitSet(1);
            if (incoming.get(0)) {
               struct.req = new TFetchResultsReq();
               struct.req.read(iprot);
               struct.setReqIsSet(true);
            }

         }
      }
   }

   @Public
   @Stable
   public static class FetchResults_result implements TBase, Serializable, Cloneable, Comparable {
      private static final TStruct STRUCT_DESC = new TStruct("FetchResults_result");
      private static final TField SUCCESS_FIELD_DESC = new TField("success", (byte)12, (short)0);
      private static final SchemeFactory STANDARD_SCHEME_FACTORY = new FetchResults_resultStandardSchemeFactory();
      private static final SchemeFactory TUPLE_SCHEME_FACTORY = new FetchResults_resultTupleSchemeFactory();
      @Nullable
      private TFetchResultsResp success;
      public static final Map metaDataMap;

      public FetchResults_result() {
      }

      public FetchResults_result(TFetchResultsResp success) {
         this();
         this.success = success;
      }

      public FetchResults_result(FetchResults_result other) {
         if (other.isSetSuccess()) {
            this.success = new TFetchResultsResp(other.success);
         }

      }

      public FetchResults_result deepCopy() {
         return new FetchResults_result(this);
      }

      public void clear() {
         this.success = null;
      }

      @Nullable
      public TFetchResultsResp getSuccess() {
         return this.success;
      }

      public void setSuccess(@Nullable TFetchResultsResp success) {
         this.success = success;
      }

      public void unsetSuccess() {
         this.success = null;
      }

      public boolean isSetSuccess() {
         return this.success != null;
      }

      public void setSuccessIsSet(boolean value) {
         if (!value) {
            this.success = null;
         }

      }

      public void setFieldValue(_Fields field, @Nullable Object value) {
         switch (field) {
            case SUCCESS:
               if (value == null) {
                  this.unsetSuccess();
               } else {
                  this.setSuccess((TFetchResultsResp)value);
               }
            default:
         }
      }

      @Nullable
      public Object getFieldValue(_Fields field) {
         switch (field) {
            case SUCCESS:
               return this.getSuccess();
            default:
               throw new IllegalStateException();
         }
      }

      public boolean isSet(_Fields field) {
         if (field == null) {
            throw new IllegalArgumentException();
         } else {
            switch (field) {
               case SUCCESS:
                  return this.isSetSuccess();
               default:
                  throw new IllegalStateException();
            }
         }
      }

      public boolean equals(Object that) {
         return that instanceof FetchResults_result ? this.equals((FetchResults_result)that) : false;
      }

      public boolean equals(FetchResults_result that) {
         if (that == null) {
            return false;
         } else if (this == that) {
            return true;
         } else {
            boolean this_present_success = this.isSetSuccess();
            boolean that_present_success = that.isSetSuccess();
            if (this_present_success || that_present_success) {
               if (!this_present_success || !that_present_success) {
                  return false;
               }

               if (!this.success.equals(that.success)) {
                  return false;
               }
            }

            return true;
         }
      }

      public int hashCode() {
         int hashCode = 1;
         hashCode = hashCode * 8191 + (this.isSetSuccess() ? 131071 : 524287);
         if (this.isSetSuccess()) {
            hashCode = hashCode * 8191 + this.success.hashCode();
         }

         return hashCode;
      }

      public int compareTo(FetchResults_result other) {
         if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
         } else {
            int lastComparison = 0;
            lastComparison = Boolean.compare(this.isSetSuccess(), other.isSetSuccess());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetSuccess()) {
                  lastComparison = TBaseHelper.compareTo(this.success, other.success);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               return 0;
            }
         }
      }

      @Nullable
      public _Fields fieldForId(int fieldId) {
         return TCLIService.FetchResults_result._Fields.findByThriftId(fieldId);
      }

      public void read(TProtocol iprot) throws TException {
         scheme(iprot).read(iprot, this);
      }

      public void write(TProtocol oprot) throws TException {
         scheme(oprot).write(oprot, this);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("FetchResults_result(");
         boolean first = true;
         sb.append("success:");
         if (this.success == null) {
            sb.append("null");
         } else {
            sb.append(this.success);
         }

         first = false;
         sb.append(")");
         return sb.toString();
      }

      public void validate() throws TException {
         if (this.success != null) {
            this.success.validate();
         }

      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         try {
            this.write(new TCompactProtocol(new TIOStreamTransport(out)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         try {
            this.read(new TCompactProtocol(new TIOStreamTransport(in)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private static IScheme scheme(TProtocol proto) {
         return (StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
      }

      static {
         Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
         tmpMap.put(TCLIService.FetchResults_result._Fields.SUCCESS, new FieldMetaData("success", (byte)3, new StructMetaData((byte)12, TFetchResultsResp.class)));
         metaDataMap = Collections.unmodifiableMap(tmpMap);
         FieldMetaData.addStructMetaDataMap(FetchResults_result.class, metaDataMap);
      }

      public static enum _Fields implements TFieldIdEnum {
         SUCCESS((short)0, "success");

         private static final Map byName = new HashMap();
         private final short _thriftId;
         private final String _fieldName;

         @Nullable
         public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
               case 0:
                  return SUCCESS;
               default:
                  return null;
            }
         }

         public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) {
               throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            } else {
               return fields;
            }
         }

         @Nullable
         public static _Fields findByName(String name) {
            return (_Fields)byName.get(name);
         }

         private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
         }

         public short getThriftFieldId() {
            return this._thriftId;
         }

         public String getFieldName() {
            return this._fieldName;
         }

         static {
            for(_Fields field : EnumSet.allOf(_Fields.class)) {
               byName.put(field.getFieldName(), field);
            }

         }
      }

      private static class FetchResults_resultStandardSchemeFactory implements SchemeFactory {
         private FetchResults_resultStandardSchemeFactory() {
         }

         public FetchResults_resultStandardScheme getScheme() {
            return new FetchResults_resultStandardScheme();
         }
      }

      private static class FetchResults_resultStandardScheme extends StandardScheme {
         private FetchResults_resultStandardScheme() {
         }

         public void read(TProtocol iprot, FetchResults_result struct) throws TException {
            iprot.readStructBegin();

            while(true) {
               TField schemeField = iprot.readFieldBegin();
               if (schemeField.type == 0) {
                  iprot.readStructEnd();
                  struct.validate();
                  return;
               }

               switch (schemeField.id) {
                  case 0:
                     if (schemeField.type == 12) {
                        struct.success = new TFetchResultsResp();
                        struct.success.read(iprot);
                        struct.setSuccessIsSet(true);
                     } else {
                        TProtocolUtil.skip(iprot, schemeField.type);
                     }
                     break;
                  default:
                     TProtocolUtil.skip(iprot, schemeField.type);
               }

               iprot.readFieldEnd();
            }
         }

         public void write(TProtocol oprot, FetchResults_result struct) throws TException {
            struct.validate();
            oprot.writeStructBegin(TCLIService.FetchResults_result.STRUCT_DESC);
            if (struct.success != null) {
               oprot.writeFieldBegin(TCLIService.FetchResults_result.SUCCESS_FIELD_DESC);
               struct.success.write(oprot);
               oprot.writeFieldEnd();
            }

            oprot.writeFieldStop();
            oprot.writeStructEnd();
         }
      }

      private static class FetchResults_resultTupleSchemeFactory implements SchemeFactory {
         private FetchResults_resultTupleSchemeFactory() {
         }

         public FetchResults_resultTupleScheme getScheme() {
            return new FetchResults_resultTupleScheme();
         }
      }

      private static class FetchResults_resultTupleScheme extends TupleScheme {
         private FetchResults_resultTupleScheme() {
         }

         public void write(TProtocol prot, FetchResults_result struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol)prot;
            BitSet optionals = new BitSet();
            if (struct.isSetSuccess()) {
               optionals.set(0);
            }

            oprot.writeBitSet(optionals, 1);
            if (struct.isSetSuccess()) {
               struct.success.write(oprot);
            }

         }

         public void read(TProtocol prot, FetchResults_result struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol)prot;
            BitSet incoming = iprot.readBitSet(1);
            if (incoming.get(0)) {
               struct.success = new TFetchResultsResp();
               struct.success.read(iprot);
               struct.setSuccessIsSet(true);
            }

         }
      }
   }

   @Public
   @Stable
   public static class GetDelegationToken_args implements TBase, Serializable, Cloneable, Comparable {
      private static final TStruct STRUCT_DESC = new TStruct("GetDelegationToken_args");
      private static final TField REQ_FIELD_DESC = new TField("req", (byte)12, (short)1);
      private static final SchemeFactory STANDARD_SCHEME_FACTORY = new GetDelegationToken_argsStandardSchemeFactory();
      private static final SchemeFactory TUPLE_SCHEME_FACTORY = new GetDelegationToken_argsTupleSchemeFactory();
      @Nullable
      private TGetDelegationTokenReq req;
      public static final Map metaDataMap;

      public GetDelegationToken_args() {
      }

      public GetDelegationToken_args(TGetDelegationTokenReq req) {
         this();
         this.req = req;
      }

      public GetDelegationToken_args(GetDelegationToken_args other) {
         if (other.isSetReq()) {
            this.req = new TGetDelegationTokenReq(other.req);
         }

      }

      public GetDelegationToken_args deepCopy() {
         return new GetDelegationToken_args(this);
      }

      public void clear() {
         this.req = null;
      }

      @Nullable
      public TGetDelegationTokenReq getReq() {
         return this.req;
      }

      public void setReq(@Nullable TGetDelegationTokenReq req) {
         this.req = req;
      }

      public void unsetReq() {
         this.req = null;
      }

      public boolean isSetReq() {
         return this.req != null;
      }

      public void setReqIsSet(boolean value) {
         if (!value) {
            this.req = null;
         }

      }

      public void setFieldValue(_Fields field, @Nullable Object value) {
         switch (field) {
            case REQ:
               if (value == null) {
                  this.unsetReq();
               } else {
                  this.setReq((TGetDelegationTokenReq)value);
               }
            default:
         }
      }

      @Nullable
      public Object getFieldValue(_Fields field) {
         switch (field) {
            case REQ:
               return this.getReq();
            default:
               throw new IllegalStateException();
         }
      }

      public boolean isSet(_Fields field) {
         if (field == null) {
            throw new IllegalArgumentException();
         } else {
            switch (field) {
               case REQ:
                  return this.isSetReq();
               default:
                  throw new IllegalStateException();
            }
         }
      }

      public boolean equals(Object that) {
         return that instanceof GetDelegationToken_args ? this.equals((GetDelegationToken_args)that) : false;
      }

      public boolean equals(GetDelegationToken_args that) {
         if (that == null) {
            return false;
         } else if (this == that) {
            return true;
         } else {
            boolean this_present_req = this.isSetReq();
            boolean that_present_req = that.isSetReq();
            if (this_present_req || that_present_req) {
               if (!this_present_req || !that_present_req) {
                  return false;
               }

               if (!this.req.equals(that.req)) {
                  return false;
               }
            }

            return true;
         }
      }

      public int hashCode() {
         int hashCode = 1;
         hashCode = hashCode * 8191 + (this.isSetReq() ? 131071 : 524287);
         if (this.isSetReq()) {
            hashCode = hashCode * 8191 + this.req.hashCode();
         }

         return hashCode;
      }

      public int compareTo(GetDelegationToken_args other) {
         if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
         } else {
            int lastComparison = 0;
            lastComparison = Boolean.compare(this.isSetReq(), other.isSetReq());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetReq()) {
                  lastComparison = TBaseHelper.compareTo(this.req, other.req);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               return 0;
            }
         }
      }

      @Nullable
      public _Fields fieldForId(int fieldId) {
         return TCLIService.GetDelegationToken_args._Fields.findByThriftId(fieldId);
      }

      public void read(TProtocol iprot) throws TException {
         scheme(iprot).read(iprot, this);
      }

      public void write(TProtocol oprot) throws TException {
         scheme(oprot).write(oprot, this);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("GetDelegationToken_args(");
         boolean first = true;
         sb.append("req:");
         if (this.req == null) {
            sb.append("null");
         } else {
            sb.append(this.req);
         }

         first = false;
         sb.append(")");
         return sb.toString();
      }

      public void validate() throws TException {
         if (this.req != null) {
            this.req.validate();
         }

      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         try {
            this.write(new TCompactProtocol(new TIOStreamTransport(out)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         try {
            this.read(new TCompactProtocol(new TIOStreamTransport(in)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private static IScheme scheme(TProtocol proto) {
         return (StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
      }

      static {
         Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
         tmpMap.put(TCLIService.GetDelegationToken_args._Fields.REQ, new FieldMetaData("req", (byte)3, new StructMetaData((byte)12, TGetDelegationTokenReq.class)));
         metaDataMap = Collections.unmodifiableMap(tmpMap);
         FieldMetaData.addStructMetaDataMap(GetDelegationToken_args.class, metaDataMap);
      }

      public static enum _Fields implements TFieldIdEnum {
         REQ((short)1, "req");

         private static final Map byName = new HashMap();
         private final short _thriftId;
         private final String _fieldName;

         @Nullable
         public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
               case 1:
                  return REQ;
               default:
                  return null;
            }
         }

         public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) {
               throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            } else {
               return fields;
            }
         }

         @Nullable
         public static _Fields findByName(String name) {
            return (_Fields)byName.get(name);
         }

         private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
         }

         public short getThriftFieldId() {
            return this._thriftId;
         }

         public String getFieldName() {
            return this._fieldName;
         }

         static {
            for(_Fields field : EnumSet.allOf(_Fields.class)) {
               byName.put(field.getFieldName(), field);
            }

         }
      }

      private static class GetDelegationToken_argsStandardSchemeFactory implements SchemeFactory {
         private GetDelegationToken_argsStandardSchemeFactory() {
         }

         public GetDelegationToken_argsStandardScheme getScheme() {
            return new GetDelegationToken_argsStandardScheme();
         }
      }

      private static class GetDelegationToken_argsStandardScheme extends StandardScheme {
         private GetDelegationToken_argsStandardScheme() {
         }

         public void read(TProtocol iprot, GetDelegationToken_args struct) throws TException {
            iprot.readStructBegin();

            while(true) {
               TField schemeField = iprot.readFieldBegin();
               if (schemeField.type == 0) {
                  iprot.readStructEnd();
                  struct.validate();
                  return;
               }

               switch (schemeField.id) {
                  case 1:
                     if (schemeField.type == 12) {
                        struct.req = new TGetDelegationTokenReq();
                        struct.req.read(iprot);
                        struct.setReqIsSet(true);
                     } else {
                        TProtocolUtil.skip(iprot, schemeField.type);
                     }
                     break;
                  default:
                     TProtocolUtil.skip(iprot, schemeField.type);
               }

               iprot.readFieldEnd();
            }
         }

         public void write(TProtocol oprot, GetDelegationToken_args struct) throws TException {
            struct.validate();
            oprot.writeStructBegin(TCLIService.GetDelegationToken_args.STRUCT_DESC);
            if (struct.req != null) {
               oprot.writeFieldBegin(TCLIService.GetDelegationToken_args.REQ_FIELD_DESC);
               struct.req.write(oprot);
               oprot.writeFieldEnd();
            }

            oprot.writeFieldStop();
            oprot.writeStructEnd();
         }
      }

      private static class GetDelegationToken_argsTupleSchemeFactory implements SchemeFactory {
         private GetDelegationToken_argsTupleSchemeFactory() {
         }

         public GetDelegationToken_argsTupleScheme getScheme() {
            return new GetDelegationToken_argsTupleScheme();
         }
      }

      private static class GetDelegationToken_argsTupleScheme extends TupleScheme {
         private GetDelegationToken_argsTupleScheme() {
         }

         public void write(TProtocol prot, GetDelegationToken_args struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol)prot;
            BitSet optionals = new BitSet();
            if (struct.isSetReq()) {
               optionals.set(0);
            }

            oprot.writeBitSet(optionals, 1);
            if (struct.isSetReq()) {
               struct.req.write(oprot);
            }

         }

         public void read(TProtocol prot, GetDelegationToken_args struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol)prot;
            BitSet incoming = iprot.readBitSet(1);
            if (incoming.get(0)) {
               struct.req = new TGetDelegationTokenReq();
               struct.req.read(iprot);
               struct.setReqIsSet(true);
            }

         }
      }
   }

   @Public
   @Stable
   public static class GetDelegationToken_result implements TBase, Serializable, Cloneable, Comparable {
      private static final TStruct STRUCT_DESC = new TStruct("GetDelegationToken_result");
      private static final TField SUCCESS_FIELD_DESC = new TField("success", (byte)12, (short)0);
      private static final SchemeFactory STANDARD_SCHEME_FACTORY = new GetDelegationToken_resultStandardSchemeFactory();
      private static final SchemeFactory TUPLE_SCHEME_FACTORY = new GetDelegationToken_resultTupleSchemeFactory();
      @Nullable
      private TGetDelegationTokenResp success;
      public static final Map metaDataMap;

      public GetDelegationToken_result() {
      }

      public GetDelegationToken_result(TGetDelegationTokenResp success) {
         this();
         this.success = success;
      }

      public GetDelegationToken_result(GetDelegationToken_result other) {
         if (other.isSetSuccess()) {
            this.success = new TGetDelegationTokenResp(other.success);
         }

      }

      public GetDelegationToken_result deepCopy() {
         return new GetDelegationToken_result(this);
      }

      public void clear() {
         this.success = null;
      }

      @Nullable
      public TGetDelegationTokenResp getSuccess() {
         return this.success;
      }

      public void setSuccess(@Nullable TGetDelegationTokenResp success) {
         this.success = success;
      }

      public void unsetSuccess() {
         this.success = null;
      }

      public boolean isSetSuccess() {
         return this.success != null;
      }

      public void setSuccessIsSet(boolean value) {
         if (!value) {
            this.success = null;
         }

      }

      public void setFieldValue(_Fields field, @Nullable Object value) {
         switch (field) {
            case SUCCESS:
               if (value == null) {
                  this.unsetSuccess();
               } else {
                  this.setSuccess((TGetDelegationTokenResp)value);
               }
            default:
         }
      }

      @Nullable
      public Object getFieldValue(_Fields field) {
         switch (field) {
            case SUCCESS:
               return this.getSuccess();
            default:
               throw new IllegalStateException();
         }
      }

      public boolean isSet(_Fields field) {
         if (field == null) {
            throw new IllegalArgumentException();
         } else {
            switch (field) {
               case SUCCESS:
                  return this.isSetSuccess();
               default:
                  throw new IllegalStateException();
            }
         }
      }

      public boolean equals(Object that) {
         return that instanceof GetDelegationToken_result ? this.equals((GetDelegationToken_result)that) : false;
      }

      public boolean equals(GetDelegationToken_result that) {
         if (that == null) {
            return false;
         } else if (this == that) {
            return true;
         } else {
            boolean this_present_success = this.isSetSuccess();
            boolean that_present_success = that.isSetSuccess();
            if (this_present_success || that_present_success) {
               if (!this_present_success || !that_present_success) {
                  return false;
               }

               if (!this.success.equals(that.success)) {
                  return false;
               }
            }

            return true;
         }
      }

      public int hashCode() {
         int hashCode = 1;
         hashCode = hashCode * 8191 + (this.isSetSuccess() ? 131071 : 524287);
         if (this.isSetSuccess()) {
            hashCode = hashCode * 8191 + this.success.hashCode();
         }

         return hashCode;
      }

      public int compareTo(GetDelegationToken_result other) {
         if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
         } else {
            int lastComparison = 0;
            lastComparison = Boolean.compare(this.isSetSuccess(), other.isSetSuccess());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetSuccess()) {
                  lastComparison = TBaseHelper.compareTo(this.success, other.success);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               return 0;
            }
         }
      }

      @Nullable
      public _Fields fieldForId(int fieldId) {
         return TCLIService.GetDelegationToken_result._Fields.findByThriftId(fieldId);
      }

      public void read(TProtocol iprot) throws TException {
         scheme(iprot).read(iprot, this);
      }

      public void write(TProtocol oprot) throws TException {
         scheme(oprot).write(oprot, this);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("GetDelegationToken_result(");
         boolean first = true;
         sb.append("success:");
         if (this.success == null) {
            sb.append("null");
         } else {
            sb.append(this.success);
         }

         first = false;
         sb.append(")");
         return sb.toString();
      }

      public void validate() throws TException {
         if (this.success != null) {
            this.success.validate();
         }

      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         try {
            this.write(new TCompactProtocol(new TIOStreamTransport(out)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         try {
            this.read(new TCompactProtocol(new TIOStreamTransport(in)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private static IScheme scheme(TProtocol proto) {
         return (StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
      }

      static {
         Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
         tmpMap.put(TCLIService.GetDelegationToken_result._Fields.SUCCESS, new FieldMetaData("success", (byte)3, new StructMetaData((byte)12, TGetDelegationTokenResp.class)));
         metaDataMap = Collections.unmodifiableMap(tmpMap);
         FieldMetaData.addStructMetaDataMap(GetDelegationToken_result.class, metaDataMap);
      }

      public static enum _Fields implements TFieldIdEnum {
         SUCCESS((short)0, "success");

         private static final Map byName = new HashMap();
         private final short _thriftId;
         private final String _fieldName;

         @Nullable
         public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
               case 0:
                  return SUCCESS;
               default:
                  return null;
            }
         }

         public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) {
               throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            } else {
               return fields;
            }
         }

         @Nullable
         public static _Fields findByName(String name) {
            return (_Fields)byName.get(name);
         }

         private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
         }

         public short getThriftFieldId() {
            return this._thriftId;
         }

         public String getFieldName() {
            return this._fieldName;
         }

         static {
            for(_Fields field : EnumSet.allOf(_Fields.class)) {
               byName.put(field.getFieldName(), field);
            }

         }
      }

      private static class GetDelegationToken_resultStandardSchemeFactory implements SchemeFactory {
         private GetDelegationToken_resultStandardSchemeFactory() {
         }

         public GetDelegationToken_resultStandardScheme getScheme() {
            return new GetDelegationToken_resultStandardScheme();
         }
      }

      private static class GetDelegationToken_resultStandardScheme extends StandardScheme {
         private GetDelegationToken_resultStandardScheme() {
         }

         public void read(TProtocol iprot, GetDelegationToken_result struct) throws TException {
            iprot.readStructBegin();

            while(true) {
               TField schemeField = iprot.readFieldBegin();
               if (schemeField.type == 0) {
                  iprot.readStructEnd();
                  struct.validate();
                  return;
               }

               switch (schemeField.id) {
                  case 0:
                     if (schemeField.type == 12) {
                        struct.success = new TGetDelegationTokenResp();
                        struct.success.read(iprot);
                        struct.setSuccessIsSet(true);
                     } else {
                        TProtocolUtil.skip(iprot, schemeField.type);
                     }
                     break;
                  default:
                     TProtocolUtil.skip(iprot, schemeField.type);
               }

               iprot.readFieldEnd();
            }
         }

         public void write(TProtocol oprot, GetDelegationToken_result struct) throws TException {
            struct.validate();
            oprot.writeStructBegin(TCLIService.GetDelegationToken_result.STRUCT_DESC);
            if (struct.success != null) {
               oprot.writeFieldBegin(TCLIService.GetDelegationToken_result.SUCCESS_FIELD_DESC);
               struct.success.write(oprot);
               oprot.writeFieldEnd();
            }

            oprot.writeFieldStop();
            oprot.writeStructEnd();
         }
      }

      private static class GetDelegationToken_resultTupleSchemeFactory implements SchemeFactory {
         private GetDelegationToken_resultTupleSchemeFactory() {
         }

         public GetDelegationToken_resultTupleScheme getScheme() {
            return new GetDelegationToken_resultTupleScheme();
         }
      }

      private static class GetDelegationToken_resultTupleScheme extends TupleScheme {
         private GetDelegationToken_resultTupleScheme() {
         }

         public void write(TProtocol prot, GetDelegationToken_result struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol)prot;
            BitSet optionals = new BitSet();
            if (struct.isSetSuccess()) {
               optionals.set(0);
            }

            oprot.writeBitSet(optionals, 1);
            if (struct.isSetSuccess()) {
               struct.success.write(oprot);
            }

         }

         public void read(TProtocol prot, GetDelegationToken_result struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol)prot;
            BitSet incoming = iprot.readBitSet(1);
            if (incoming.get(0)) {
               struct.success = new TGetDelegationTokenResp();
               struct.success.read(iprot);
               struct.setSuccessIsSet(true);
            }

         }
      }
   }

   @Public
   @Stable
   public static class CancelDelegationToken_args implements TBase, Serializable, Cloneable, Comparable {
      private static final TStruct STRUCT_DESC = new TStruct("CancelDelegationToken_args");
      private static final TField REQ_FIELD_DESC = new TField("req", (byte)12, (short)1);
      private static final SchemeFactory STANDARD_SCHEME_FACTORY = new CancelDelegationToken_argsStandardSchemeFactory();
      private static final SchemeFactory TUPLE_SCHEME_FACTORY = new CancelDelegationToken_argsTupleSchemeFactory();
      @Nullable
      private TCancelDelegationTokenReq req;
      public static final Map metaDataMap;

      public CancelDelegationToken_args() {
      }

      public CancelDelegationToken_args(TCancelDelegationTokenReq req) {
         this();
         this.req = req;
      }

      public CancelDelegationToken_args(CancelDelegationToken_args other) {
         if (other.isSetReq()) {
            this.req = new TCancelDelegationTokenReq(other.req);
         }

      }

      public CancelDelegationToken_args deepCopy() {
         return new CancelDelegationToken_args(this);
      }

      public void clear() {
         this.req = null;
      }

      @Nullable
      public TCancelDelegationTokenReq getReq() {
         return this.req;
      }

      public void setReq(@Nullable TCancelDelegationTokenReq req) {
         this.req = req;
      }

      public void unsetReq() {
         this.req = null;
      }

      public boolean isSetReq() {
         return this.req != null;
      }

      public void setReqIsSet(boolean value) {
         if (!value) {
            this.req = null;
         }

      }

      public void setFieldValue(_Fields field, @Nullable Object value) {
         switch (field) {
            case REQ:
               if (value == null) {
                  this.unsetReq();
               } else {
                  this.setReq((TCancelDelegationTokenReq)value);
               }
            default:
         }
      }

      @Nullable
      public Object getFieldValue(_Fields field) {
         switch (field) {
            case REQ:
               return this.getReq();
            default:
               throw new IllegalStateException();
         }
      }

      public boolean isSet(_Fields field) {
         if (field == null) {
            throw new IllegalArgumentException();
         } else {
            switch (field) {
               case REQ:
                  return this.isSetReq();
               default:
                  throw new IllegalStateException();
            }
         }
      }

      public boolean equals(Object that) {
         return that instanceof CancelDelegationToken_args ? this.equals((CancelDelegationToken_args)that) : false;
      }

      public boolean equals(CancelDelegationToken_args that) {
         if (that == null) {
            return false;
         } else if (this == that) {
            return true;
         } else {
            boolean this_present_req = this.isSetReq();
            boolean that_present_req = that.isSetReq();
            if (this_present_req || that_present_req) {
               if (!this_present_req || !that_present_req) {
                  return false;
               }

               if (!this.req.equals(that.req)) {
                  return false;
               }
            }

            return true;
         }
      }

      public int hashCode() {
         int hashCode = 1;
         hashCode = hashCode * 8191 + (this.isSetReq() ? 131071 : 524287);
         if (this.isSetReq()) {
            hashCode = hashCode * 8191 + this.req.hashCode();
         }

         return hashCode;
      }

      public int compareTo(CancelDelegationToken_args other) {
         if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
         } else {
            int lastComparison = 0;
            lastComparison = Boolean.compare(this.isSetReq(), other.isSetReq());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetReq()) {
                  lastComparison = TBaseHelper.compareTo(this.req, other.req);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               return 0;
            }
         }
      }

      @Nullable
      public _Fields fieldForId(int fieldId) {
         return TCLIService.CancelDelegationToken_args._Fields.findByThriftId(fieldId);
      }

      public void read(TProtocol iprot) throws TException {
         scheme(iprot).read(iprot, this);
      }

      public void write(TProtocol oprot) throws TException {
         scheme(oprot).write(oprot, this);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("CancelDelegationToken_args(");
         boolean first = true;
         sb.append("req:");
         if (this.req == null) {
            sb.append("null");
         } else {
            sb.append(this.req);
         }

         first = false;
         sb.append(")");
         return sb.toString();
      }

      public void validate() throws TException {
         if (this.req != null) {
            this.req.validate();
         }

      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         try {
            this.write(new TCompactProtocol(new TIOStreamTransport(out)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         try {
            this.read(new TCompactProtocol(new TIOStreamTransport(in)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private static IScheme scheme(TProtocol proto) {
         return (StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
      }

      static {
         Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
         tmpMap.put(TCLIService.CancelDelegationToken_args._Fields.REQ, new FieldMetaData("req", (byte)3, new StructMetaData((byte)12, TCancelDelegationTokenReq.class)));
         metaDataMap = Collections.unmodifiableMap(tmpMap);
         FieldMetaData.addStructMetaDataMap(CancelDelegationToken_args.class, metaDataMap);
      }

      public static enum _Fields implements TFieldIdEnum {
         REQ((short)1, "req");

         private static final Map byName = new HashMap();
         private final short _thriftId;
         private final String _fieldName;

         @Nullable
         public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
               case 1:
                  return REQ;
               default:
                  return null;
            }
         }

         public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) {
               throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            } else {
               return fields;
            }
         }

         @Nullable
         public static _Fields findByName(String name) {
            return (_Fields)byName.get(name);
         }

         private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
         }

         public short getThriftFieldId() {
            return this._thriftId;
         }

         public String getFieldName() {
            return this._fieldName;
         }

         static {
            for(_Fields field : EnumSet.allOf(_Fields.class)) {
               byName.put(field.getFieldName(), field);
            }

         }
      }

      private static class CancelDelegationToken_argsStandardSchemeFactory implements SchemeFactory {
         private CancelDelegationToken_argsStandardSchemeFactory() {
         }

         public CancelDelegationToken_argsStandardScheme getScheme() {
            return new CancelDelegationToken_argsStandardScheme();
         }
      }

      private static class CancelDelegationToken_argsStandardScheme extends StandardScheme {
         private CancelDelegationToken_argsStandardScheme() {
         }

         public void read(TProtocol iprot, CancelDelegationToken_args struct) throws TException {
            iprot.readStructBegin();

            while(true) {
               TField schemeField = iprot.readFieldBegin();
               if (schemeField.type == 0) {
                  iprot.readStructEnd();
                  struct.validate();
                  return;
               }

               switch (schemeField.id) {
                  case 1:
                     if (schemeField.type == 12) {
                        struct.req = new TCancelDelegationTokenReq();
                        struct.req.read(iprot);
                        struct.setReqIsSet(true);
                     } else {
                        TProtocolUtil.skip(iprot, schemeField.type);
                     }
                     break;
                  default:
                     TProtocolUtil.skip(iprot, schemeField.type);
               }

               iprot.readFieldEnd();
            }
         }

         public void write(TProtocol oprot, CancelDelegationToken_args struct) throws TException {
            struct.validate();
            oprot.writeStructBegin(TCLIService.CancelDelegationToken_args.STRUCT_DESC);
            if (struct.req != null) {
               oprot.writeFieldBegin(TCLIService.CancelDelegationToken_args.REQ_FIELD_DESC);
               struct.req.write(oprot);
               oprot.writeFieldEnd();
            }

            oprot.writeFieldStop();
            oprot.writeStructEnd();
         }
      }

      private static class CancelDelegationToken_argsTupleSchemeFactory implements SchemeFactory {
         private CancelDelegationToken_argsTupleSchemeFactory() {
         }

         public CancelDelegationToken_argsTupleScheme getScheme() {
            return new CancelDelegationToken_argsTupleScheme();
         }
      }

      private static class CancelDelegationToken_argsTupleScheme extends TupleScheme {
         private CancelDelegationToken_argsTupleScheme() {
         }

         public void write(TProtocol prot, CancelDelegationToken_args struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol)prot;
            BitSet optionals = new BitSet();
            if (struct.isSetReq()) {
               optionals.set(0);
            }

            oprot.writeBitSet(optionals, 1);
            if (struct.isSetReq()) {
               struct.req.write(oprot);
            }

         }

         public void read(TProtocol prot, CancelDelegationToken_args struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol)prot;
            BitSet incoming = iprot.readBitSet(1);
            if (incoming.get(0)) {
               struct.req = new TCancelDelegationTokenReq();
               struct.req.read(iprot);
               struct.setReqIsSet(true);
            }

         }
      }
   }

   @Public
   @Stable
   public static class CancelDelegationToken_result implements TBase, Serializable, Cloneable, Comparable {
      private static final TStruct STRUCT_DESC = new TStruct("CancelDelegationToken_result");
      private static final TField SUCCESS_FIELD_DESC = new TField("success", (byte)12, (short)0);
      private static final SchemeFactory STANDARD_SCHEME_FACTORY = new CancelDelegationToken_resultStandardSchemeFactory();
      private static final SchemeFactory TUPLE_SCHEME_FACTORY = new CancelDelegationToken_resultTupleSchemeFactory();
      @Nullable
      private TCancelDelegationTokenResp success;
      public static final Map metaDataMap;

      public CancelDelegationToken_result() {
      }

      public CancelDelegationToken_result(TCancelDelegationTokenResp success) {
         this();
         this.success = success;
      }

      public CancelDelegationToken_result(CancelDelegationToken_result other) {
         if (other.isSetSuccess()) {
            this.success = new TCancelDelegationTokenResp(other.success);
         }

      }

      public CancelDelegationToken_result deepCopy() {
         return new CancelDelegationToken_result(this);
      }

      public void clear() {
         this.success = null;
      }

      @Nullable
      public TCancelDelegationTokenResp getSuccess() {
         return this.success;
      }

      public void setSuccess(@Nullable TCancelDelegationTokenResp success) {
         this.success = success;
      }

      public void unsetSuccess() {
         this.success = null;
      }

      public boolean isSetSuccess() {
         return this.success != null;
      }

      public void setSuccessIsSet(boolean value) {
         if (!value) {
            this.success = null;
         }

      }

      public void setFieldValue(_Fields field, @Nullable Object value) {
         switch (field) {
            case SUCCESS:
               if (value == null) {
                  this.unsetSuccess();
               } else {
                  this.setSuccess((TCancelDelegationTokenResp)value);
               }
            default:
         }
      }

      @Nullable
      public Object getFieldValue(_Fields field) {
         switch (field) {
            case SUCCESS:
               return this.getSuccess();
            default:
               throw new IllegalStateException();
         }
      }

      public boolean isSet(_Fields field) {
         if (field == null) {
            throw new IllegalArgumentException();
         } else {
            switch (field) {
               case SUCCESS:
                  return this.isSetSuccess();
               default:
                  throw new IllegalStateException();
            }
         }
      }

      public boolean equals(Object that) {
         return that instanceof CancelDelegationToken_result ? this.equals((CancelDelegationToken_result)that) : false;
      }

      public boolean equals(CancelDelegationToken_result that) {
         if (that == null) {
            return false;
         } else if (this == that) {
            return true;
         } else {
            boolean this_present_success = this.isSetSuccess();
            boolean that_present_success = that.isSetSuccess();
            if (this_present_success || that_present_success) {
               if (!this_present_success || !that_present_success) {
                  return false;
               }

               if (!this.success.equals(that.success)) {
                  return false;
               }
            }

            return true;
         }
      }

      public int hashCode() {
         int hashCode = 1;
         hashCode = hashCode * 8191 + (this.isSetSuccess() ? 131071 : 524287);
         if (this.isSetSuccess()) {
            hashCode = hashCode * 8191 + this.success.hashCode();
         }

         return hashCode;
      }

      public int compareTo(CancelDelegationToken_result other) {
         if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
         } else {
            int lastComparison = 0;
            lastComparison = Boolean.compare(this.isSetSuccess(), other.isSetSuccess());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetSuccess()) {
                  lastComparison = TBaseHelper.compareTo(this.success, other.success);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               return 0;
            }
         }
      }

      @Nullable
      public _Fields fieldForId(int fieldId) {
         return TCLIService.CancelDelegationToken_result._Fields.findByThriftId(fieldId);
      }

      public void read(TProtocol iprot) throws TException {
         scheme(iprot).read(iprot, this);
      }

      public void write(TProtocol oprot) throws TException {
         scheme(oprot).write(oprot, this);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("CancelDelegationToken_result(");
         boolean first = true;
         sb.append("success:");
         if (this.success == null) {
            sb.append("null");
         } else {
            sb.append(this.success);
         }

         first = false;
         sb.append(")");
         return sb.toString();
      }

      public void validate() throws TException {
         if (this.success != null) {
            this.success.validate();
         }

      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         try {
            this.write(new TCompactProtocol(new TIOStreamTransport(out)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         try {
            this.read(new TCompactProtocol(new TIOStreamTransport(in)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private static IScheme scheme(TProtocol proto) {
         return (StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
      }

      static {
         Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
         tmpMap.put(TCLIService.CancelDelegationToken_result._Fields.SUCCESS, new FieldMetaData("success", (byte)3, new StructMetaData((byte)12, TCancelDelegationTokenResp.class)));
         metaDataMap = Collections.unmodifiableMap(tmpMap);
         FieldMetaData.addStructMetaDataMap(CancelDelegationToken_result.class, metaDataMap);
      }

      public static enum _Fields implements TFieldIdEnum {
         SUCCESS((short)0, "success");

         private static final Map byName = new HashMap();
         private final short _thriftId;
         private final String _fieldName;

         @Nullable
         public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
               case 0:
                  return SUCCESS;
               default:
                  return null;
            }
         }

         public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) {
               throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            } else {
               return fields;
            }
         }

         @Nullable
         public static _Fields findByName(String name) {
            return (_Fields)byName.get(name);
         }

         private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
         }

         public short getThriftFieldId() {
            return this._thriftId;
         }

         public String getFieldName() {
            return this._fieldName;
         }

         static {
            for(_Fields field : EnumSet.allOf(_Fields.class)) {
               byName.put(field.getFieldName(), field);
            }

         }
      }

      private static class CancelDelegationToken_resultStandardSchemeFactory implements SchemeFactory {
         private CancelDelegationToken_resultStandardSchemeFactory() {
         }

         public CancelDelegationToken_resultStandardScheme getScheme() {
            return new CancelDelegationToken_resultStandardScheme();
         }
      }

      private static class CancelDelegationToken_resultStandardScheme extends StandardScheme {
         private CancelDelegationToken_resultStandardScheme() {
         }

         public void read(TProtocol iprot, CancelDelegationToken_result struct) throws TException {
            iprot.readStructBegin();

            while(true) {
               TField schemeField = iprot.readFieldBegin();
               if (schemeField.type == 0) {
                  iprot.readStructEnd();
                  struct.validate();
                  return;
               }

               switch (schemeField.id) {
                  case 0:
                     if (schemeField.type == 12) {
                        struct.success = new TCancelDelegationTokenResp();
                        struct.success.read(iprot);
                        struct.setSuccessIsSet(true);
                     } else {
                        TProtocolUtil.skip(iprot, schemeField.type);
                     }
                     break;
                  default:
                     TProtocolUtil.skip(iprot, schemeField.type);
               }

               iprot.readFieldEnd();
            }
         }

         public void write(TProtocol oprot, CancelDelegationToken_result struct) throws TException {
            struct.validate();
            oprot.writeStructBegin(TCLIService.CancelDelegationToken_result.STRUCT_DESC);
            if (struct.success != null) {
               oprot.writeFieldBegin(TCLIService.CancelDelegationToken_result.SUCCESS_FIELD_DESC);
               struct.success.write(oprot);
               oprot.writeFieldEnd();
            }

            oprot.writeFieldStop();
            oprot.writeStructEnd();
         }
      }

      private static class CancelDelegationToken_resultTupleSchemeFactory implements SchemeFactory {
         private CancelDelegationToken_resultTupleSchemeFactory() {
         }

         public CancelDelegationToken_resultTupleScheme getScheme() {
            return new CancelDelegationToken_resultTupleScheme();
         }
      }

      private static class CancelDelegationToken_resultTupleScheme extends TupleScheme {
         private CancelDelegationToken_resultTupleScheme() {
         }

         public void write(TProtocol prot, CancelDelegationToken_result struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol)prot;
            BitSet optionals = new BitSet();
            if (struct.isSetSuccess()) {
               optionals.set(0);
            }

            oprot.writeBitSet(optionals, 1);
            if (struct.isSetSuccess()) {
               struct.success.write(oprot);
            }

         }

         public void read(TProtocol prot, CancelDelegationToken_result struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol)prot;
            BitSet incoming = iprot.readBitSet(1);
            if (incoming.get(0)) {
               struct.success = new TCancelDelegationTokenResp();
               struct.success.read(iprot);
               struct.setSuccessIsSet(true);
            }

         }
      }
   }

   @Public
   @Stable
   public static class RenewDelegationToken_args implements TBase, Serializable, Cloneable, Comparable {
      private static final TStruct STRUCT_DESC = new TStruct("RenewDelegationToken_args");
      private static final TField REQ_FIELD_DESC = new TField("req", (byte)12, (short)1);
      private static final SchemeFactory STANDARD_SCHEME_FACTORY = new RenewDelegationToken_argsStandardSchemeFactory();
      private static final SchemeFactory TUPLE_SCHEME_FACTORY = new RenewDelegationToken_argsTupleSchemeFactory();
      @Nullable
      private TRenewDelegationTokenReq req;
      public static final Map metaDataMap;

      public RenewDelegationToken_args() {
      }

      public RenewDelegationToken_args(TRenewDelegationTokenReq req) {
         this();
         this.req = req;
      }

      public RenewDelegationToken_args(RenewDelegationToken_args other) {
         if (other.isSetReq()) {
            this.req = new TRenewDelegationTokenReq(other.req);
         }

      }

      public RenewDelegationToken_args deepCopy() {
         return new RenewDelegationToken_args(this);
      }

      public void clear() {
         this.req = null;
      }

      @Nullable
      public TRenewDelegationTokenReq getReq() {
         return this.req;
      }

      public void setReq(@Nullable TRenewDelegationTokenReq req) {
         this.req = req;
      }

      public void unsetReq() {
         this.req = null;
      }

      public boolean isSetReq() {
         return this.req != null;
      }

      public void setReqIsSet(boolean value) {
         if (!value) {
            this.req = null;
         }

      }

      public void setFieldValue(_Fields field, @Nullable Object value) {
         switch (field) {
            case REQ:
               if (value == null) {
                  this.unsetReq();
               } else {
                  this.setReq((TRenewDelegationTokenReq)value);
               }
            default:
         }
      }

      @Nullable
      public Object getFieldValue(_Fields field) {
         switch (field) {
            case REQ:
               return this.getReq();
            default:
               throw new IllegalStateException();
         }
      }

      public boolean isSet(_Fields field) {
         if (field == null) {
            throw new IllegalArgumentException();
         } else {
            switch (field) {
               case REQ:
                  return this.isSetReq();
               default:
                  throw new IllegalStateException();
            }
         }
      }

      public boolean equals(Object that) {
         return that instanceof RenewDelegationToken_args ? this.equals((RenewDelegationToken_args)that) : false;
      }

      public boolean equals(RenewDelegationToken_args that) {
         if (that == null) {
            return false;
         } else if (this == that) {
            return true;
         } else {
            boolean this_present_req = this.isSetReq();
            boolean that_present_req = that.isSetReq();
            if (this_present_req || that_present_req) {
               if (!this_present_req || !that_present_req) {
                  return false;
               }

               if (!this.req.equals(that.req)) {
                  return false;
               }
            }

            return true;
         }
      }

      public int hashCode() {
         int hashCode = 1;
         hashCode = hashCode * 8191 + (this.isSetReq() ? 131071 : 524287);
         if (this.isSetReq()) {
            hashCode = hashCode * 8191 + this.req.hashCode();
         }

         return hashCode;
      }

      public int compareTo(RenewDelegationToken_args other) {
         if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
         } else {
            int lastComparison = 0;
            lastComparison = Boolean.compare(this.isSetReq(), other.isSetReq());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetReq()) {
                  lastComparison = TBaseHelper.compareTo(this.req, other.req);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               return 0;
            }
         }
      }

      @Nullable
      public _Fields fieldForId(int fieldId) {
         return TCLIService.RenewDelegationToken_args._Fields.findByThriftId(fieldId);
      }

      public void read(TProtocol iprot) throws TException {
         scheme(iprot).read(iprot, this);
      }

      public void write(TProtocol oprot) throws TException {
         scheme(oprot).write(oprot, this);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("RenewDelegationToken_args(");
         boolean first = true;
         sb.append("req:");
         if (this.req == null) {
            sb.append("null");
         } else {
            sb.append(this.req);
         }

         first = false;
         sb.append(")");
         return sb.toString();
      }

      public void validate() throws TException {
         if (this.req != null) {
            this.req.validate();
         }

      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         try {
            this.write(new TCompactProtocol(new TIOStreamTransport(out)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         try {
            this.read(new TCompactProtocol(new TIOStreamTransport(in)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private static IScheme scheme(TProtocol proto) {
         return (StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
      }

      static {
         Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
         tmpMap.put(TCLIService.RenewDelegationToken_args._Fields.REQ, new FieldMetaData("req", (byte)3, new StructMetaData((byte)12, TRenewDelegationTokenReq.class)));
         metaDataMap = Collections.unmodifiableMap(tmpMap);
         FieldMetaData.addStructMetaDataMap(RenewDelegationToken_args.class, metaDataMap);
      }

      public static enum _Fields implements TFieldIdEnum {
         REQ((short)1, "req");

         private static final Map byName = new HashMap();
         private final short _thriftId;
         private final String _fieldName;

         @Nullable
         public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
               case 1:
                  return REQ;
               default:
                  return null;
            }
         }

         public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) {
               throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            } else {
               return fields;
            }
         }

         @Nullable
         public static _Fields findByName(String name) {
            return (_Fields)byName.get(name);
         }

         private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
         }

         public short getThriftFieldId() {
            return this._thriftId;
         }

         public String getFieldName() {
            return this._fieldName;
         }

         static {
            for(_Fields field : EnumSet.allOf(_Fields.class)) {
               byName.put(field.getFieldName(), field);
            }

         }
      }

      private static class RenewDelegationToken_argsStandardSchemeFactory implements SchemeFactory {
         private RenewDelegationToken_argsStandardSchemeFactory() {
         }

         public RenewDelegationToken_argsStandardScheme getScheme() {
            return new RenewDelegationToken_argsStandardScheme();
         }
      }

      private static class RenewDelegationToken_argsStandardScheme extends StandardScheme {
         private RenewDelegationToken_argsStandardScheme() {
         }

         public void read(TProtocol iprot, RenewDelegationToken_args struct) throws TException {
            iprot.readStructBegin();

            while(true) {
               TField schemeField = iprot.readFieldBegin();
               if (schemeField.type == 0) {
                  iprot.readStructEnd();
                  struct.validate();
                  return;
               }

               switch (schemeField.id) {
                  case 1:
                     if (schemeField.type == 12) {
                        struct.req = new TRenewDelegationTokenReq();
                        struct.req.read(iprot);
                        struct.setReqIsSet(true);
                     } else {
                        TProtocolUtil.skip(iprot, schemeField.type);
                     }
                     break;
                  default:
                     TProtocolUtil.skip(iprot, schemeField.type);
               }

               iprot.readFieldEnd();
            }
         }

         public void write(TProtocol oprot, RenewDelegationToken_args struct) throws TException {
            struct.validate();
            oprot.writeStructBegin(TCLIService.RenewDelegationToken_args.STRUCT_DESC);
            if (struct.req != null) {
               oprot.writeFieldBegin(TCLIService.RenewDelegationToken_args.REQ_FIELD_DESC);
               struct.req.write(oprot);
               oprot.writeFieldEnd();
            }

            oprot.writeFieldStop();
            oprot.writeStructEnd();
         }
      }

      private static class RenewDelegationToken_argsTupleSchemeFactory implements SchemeFactory {
         private RenewDelegationToken_argsTupleSchemeFactory() {
         }

         public RenewDelegationToken_argsTupleScheme getScheme() {
            return new RenewDelegationToken_argsTupleScheme();
         }
      }

      private static class RenewDelegationToken_argsTupleScheme extends TupleScheme {
         private RenewDelegationToken_argsTupleScheme() {
         }

         public void write(TProtocol prot, RenewDelegationToken_args struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol)prot;
            BitSet optionals = new BitSet();
            if (struct.isSetReq()) {
               optionals.set(0);
            }

            oprot.writeBitSet(optionals, 1);
            if (struct.isSetReq()) {
               struct.req.write(oprot);
            }

         }

         public void read(TProtocol prot, RenewDelegationToken_args struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol)prot;
            BitSet incoming = iprot.readBitSet(1);
            if (incoming.get(0)) {
               struct.req = new TRenewDelegationTokenReq();
               struct.req.read(iprot);
               struct.setReqIsSet(true);
            }

         }
      }
   }

   @Public
   @Stable
   public static class RenewDelegationToken_result implements TBase, Serializable, Cloneable, Comparable {
      private static final TStruct STRUCT_DESC = new TStruct("RenewDelegationToken_result");
      private static final TField SUCCESS_FIELD_DESC = new TField("success", (byte)12, (short)0);
      private static final SchemeFactory STANDARD_SCHEME_FACTORY = new RenewDelegationToken_resultStandardSchemeFactory();
      private static final SchemeFactory TUPLE_SCHEME_FACTORY = new RenewDelegationToken_resultTupleSchemeFactory();
      @Nullable
      private TRenewDelegationTokenResp success;
      public static final Map metaDataMap;

      public RenewDelegationToken_result() {
      }

      public RenewDelegationToken_result(TRenewDelegationTokenResp success) {
         this();
         this.success = success;
      }

      public RenewDelegationToken_result(RenewDelegationToken_result other) {
         if (other.isSetSuccess()) {
            this.success = new TRenewDelegationTokenResp(other.success);
         }

      }

      public RenewDelegationToken_result deepCopy() {
         return new RenewDelegationToken_result(this);
      }

      public void clear() {
         this.success = null;
      }

      @Nullable
      public TRenewDelegationTokenResp getSuccess() {
         return this.success;
      }

      public void setSuccess(@Nullable TRenewDelegationTokenResp success) {
         this.success = success;
      }

      public void unsetSuccess() {
         this.success = null;
      }

      public boolean isSetSuccess() {
         return this.success != null;
      }

      public void setSuccessIsSet(boolean value) {
         if (!value) {
            this.success = null;
         }

      }

      public void setFieldValue(_Fields field, @Nullable Object value) {
         switch (field) {
            case SUCCESS:
               if (value == null) {
                  this.unsetSuccess();
               } else {
                  this.setSuccess((TRenewDelegationTokenResp)value);
               }
            default:
         }
      }

      @Nullable
      public Object getFieldValue(_Fields field) {
         switch (field) {
            case SUCCESS:
               return this.getSuccess();
            default:
               throw new IllegalStateException();
         }
      }

      public boolean isSet(_Fields field) {
         if (field == null) {
            throw new IllegalArgumentException();
         } else {
            switch (field) {
               case SUCCESS:
                  return this.isSetSuccess();
               default:
                  throw new IllegalStateException();
            }
         }
      }

      public boolean equals(Object that) {
         return that instanceof RenewDelegationToken_result ? this.equals((RenewDelegationToken_result)that) : false;
      }

      public boolean equals(RenewDelegationToken_result that) {
         if (that == null) {
            return false;
         } else if (this == that) {
            return true;
         } else {
            boolean this_present_success = this.isSetSuccess();
            boolean that_present_success = that.isSetSuccess();
            if (this_present_success || that_present_success) {
               if (!this_present_success || !that_present_success) {
                  return false;
               }

               if (!this.success.equals(that.success)) {
                  return false;
               }
            }

            return true;
         }
      }

      public int hashCode() {
         int hashCode = 1;
         hashCode = hashCode * 8191 + (this.isSetSuccess() ? 131071 : 524287);
         if (this.isSetSuccess()) {
            hashCode = hashCode * 8191 + this.success.hashCode();
         }

         return hashCode;
      }

      public int compareTo(RenewDelegationToken_result other) {
         if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
         } else {
            int lastComparison = 0;
            lastComparison = Boolean.compare(this.isSetSuccess(), other.isSetSuccess());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetSuccess()) {
                  lastComparison = TBaseHelper.compareTo(this.success, other.success);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               return 0;
            }
         }
      }

      @Nullable
      public _Fields fieldForId(int fieldId) {
         return TCLIService.RenewDelegationToken_result._Fields.findByThriftId(fieldId);
      }

      public void read(TProtocol iprot) throws TException {
         scheme(iprot).read(iprot, this);
      }

      public void write(TProtocol oprot) throws TException {
         scheme(oprot).write(oprot, this);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("RenewDelegationToken_result(");
         boolean first = true;
         sb.append("success:");
         if (this.success == null) {
            sb.append("null");
         } else {
            sb.append(this.success);
         }

         first = false;
         sb.append(")");
         return sb.toString();
      }

      public void validate() throws TException {
         if (this.success != null) {
            this.success.validate();
         }

      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         try {
            this.write(new TCompactProtocol(new TIOStreamTransport(out)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         try {
            this.read(new TCompactProtocol(new TIOStreamTransport(in)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private static IScheme scheme(TProtocol proto) {
         return (StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
      }

      static {
         Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
         tmpMap.put(TCLIService.RenewDelegationToken_result._Fields.SUCCESS, new FieldMetaData("success", (byte)3, new StructMetaData((byte)12, TRenewDelegationTokenResp.class)));
         metaDataMap = Collections.unmodifiableMap(tmpMap);
         FieldMetaData.addStructMetaDataMap(RenewDelegationToken_result.class, metaDataMap);
      }

      public static enum _Fields implements TFieldIdEnum {
         SUCCESS((short)0, "success");

         private static final Map byName = new HashMap();
         private final short _thriftId;
         private final String _fieldName;

         @Nullable
         public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
               case 0:
                  return SUCCESS;
               default:
                  return null;
            }
         }

         public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) {
               throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            } else {
               return fields;
            }
         }

         @Nullable
         public static _Fields findByName(String name) {
            return (_Fields)byName.get(name);
         }

         private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
         }

         public short getThriftFieldId() {
            return this._thriftId;
         }

         public String getFieldName() {
            return this._fieldName;
         }

         static {
            for(_Fields field : EnumSet.allOf(_Fields.class)) {
               byName.put(field.getFieldName(), field);
            }

         }
      }

      private static class RenewDelegationToken_resultStandardSchemeFactory implements SchemeFactory {
         private RenewDelegationToken_resultStandardSchemeFactory() {
         }

         public RenewDelegationToken_resultStandardScheme getScheme() {
            return new RenewDelegationToken_resultStandardScheme();
         }
      }

      private static class RenewDelegationToken_resultStandardScheme extends StandardScheme {
         private RenewDelegationToken_resultStandardScheme() {
         }

         public void read(TProtocol iprot, RenewDelegationToken_result struct) throws TException {
            iprot.readStructBegin();

            while(true) {
               TField schemeField = iprot.readFieldBegin();
               if (schemeField.type == 0) {
                  iprot.readStructEnd();
                  struct.validate();
                  return;
               }

               switch (schemeField.id) {
                  case 0:
                     if (schemeField.type == 12) {
                        struct.success = new TRenewDelegationTokenResp();
                        struct.success.read(iprot);
                        struct.setSuccessIsSet(true);
                     } else {
                        TProtocolUtil.skip(iprot, schemeField.type);
                     }
                     break;
                  default:
                     TProtocolUtil.skip(iprot, schemeField.type);
               }

               iprot.readFieldEnd();
            }
         }

         public void write(TProtocol oprot, RenewDelegationToken_result struct) throws TException {
            struct.validate();
            oprot.writeStructBegin(TCLIService.RenewDelegationToken_result.STRUCT_DESC);
            if (struct.success != null) {
               oprot.writeFieldBegin(TCLIService.RenewDelegationToken_result.SUCCESS_FIELD_DESC);
               struct.success.write(oprot);
               oprot.writeFieldEnd();
            }

            oprot.writeFieldStop();
            oprot.writeStructEnd();
         }
      }

      private static class RenewDelegationToken_resultTupleSchemeFactory implements SchemeFactory {
         private RenewDelegationToken_resultTupleSchemeFactory() {
         }

         public RenewDelegationToken_resultTupleScheme getScheme() {
            return new RenewDelegationToken_resultTupleScheme();
         }
      }

      private static class RenewDelegationToken_resultTupleScheme extends TupleScheme {
         private RenewDelegationToken_resultTupleScheme() {
         }

         public void write(TProtocol prot, RenewDelegationToken_result struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol)prot;
            BitSet optionals = new BitSet();
            if (struct.isSetSuccess()) {
               optionals.set(0);
            }

            oprot.writeBitSet(optionals, 1);
            if (struct.isSetSuccess()) {
               struct.success.write(oprot);
            }

         }

         public void read(TProtocol prot, RenewDelegationToken_result struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol)prot;
            BitSet incoming = iprot.readBitSet(1);
            if (incoming.get(0)) {
               struct.success = new TRenewDelegationTokenResp();
               struct.success.read(iprot);
               struct.setSuccessIsSet(true);
            }

         }
      }
   }

   @Public
   @Stable
   public static class GetQueryId_args implements TBase, Serializable, Cloneable, Comparable {
      private static final TStruct STRUCT_DESC = new TStruct("GetQueryId_args");
      private static final TField REQ_FIELD_DESC = new TField("req", (byte)12, (short)1);
      private static final SchemeFactory STANDARD_SCHEME_FACTORY = new GetQueryId_argsStandardSchemeFactory();
      private static final SchemeFactory TUPLE_SCHEME_FACTORY = new GetQueryId_argsTupleSchemeFactory();
      @Nullable
      private TGetQueryIdReq req;
      public static final Map metaDataMap;

      public GetQueryId_args() {
      }

      public GetQueryId_args(TGetQueryIdReq req) {
         this();
         this.req = req;
      }

      public GetQueryId_args(GetQueryId_args other) {
         if (other.isSetReq()) {
            this.req = new TGetQueryIdReq(other.req);
         }

      }

      public GetQueryId_args deepCopy() {
         return new GetQueryId_args(this);
      }

      public void clear() {
         this.req = null;
      }

      @Nullable
      public TGetQueryIdReq getReq() {
         return this.req;
      }

      public void setReq(@Nullable TGetQueryIdReq req) {
         this.req = req;
      }

      public void unsetReq() {
         this.req = null;
      }

      public boolean isSetReq() {
         return this.req != null;
      }

      public void setReqIsSet(boolean value) {
         if (!value) {
            this.req = null;
         }

      }

      public void setFieldValue(_Fields field, @Nullable Object value) {
         switch (field) {
            case REQ:
               if (value == null) {
                  this.unsetReq();
               } else {
                  this.setReq((TGetQueryIdReq)value);
               }
            default:
         }
      }

      @Nullable
      public Object getFieldValue(_Fields field) {
         switch (field) {
            case REQ:
               return this.getReq();
            default:
               throw new IllegalStateException();
         }
      }

      public boolean isSet(_Fields field) {
         if (field == null) {
            throw new IllegalArgumentException();
         } else {
            switch (field) {
               case REQ:
                  return this.isSetReq();
               default:
                  throw new IllegalStateException();
            }
         }
      }

      public boolean equals(Object that) {
         return that instanceof GetQueryId_args ? this.equals((GetQueryId_args)that) : false;
      }

      public boolean equals(GetQueryId_args that) {
         if (that == null) {
            return false;
         } else if (this == that) {
            return true;
         } else {
            boolean this_present_req = this.isSetReq();
            boolean that_present_req = that.isSetReq();
            if (this_present_req || that_present_req) {
               if (!this_present_req || !that_present_req) {
                  return false;
               }

               if (!this.req.equals(that.req)) {
                  return false;
               }
            }

            return true;
         }
      }

      public int hashCode() {
         int hashCode = 1;
         hashCode = hashCode * 8191 + (this.isSetReq() ? 131071 : 524287);
         if (this.isSetReq()) {
            hashCode = hashCode * 8191 + this.req.hashCode();
         }

         return hashCode;
      }

      public int compareTo(GetQueryId_args other) {
         if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
         } else {
            int lastComparison = 0;
            lastComparison = Boolean.compare(this.isSetReq(), other.isSetReq());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetReq()) {
                  lastComparison = TBaseHelper.compareTo(this.req, other.req);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               return 0;
            }
         }
      }

      @Nullable
      public _Fields fieldForId(int fieldId) {
         return TCLIService.GetQueryId_args._Fields.findByThriftId(fieldId);
      }

      public void read(TProtocol iprot) throws TException {
         scheme(iprot).read(iprot, this);
      }

      public void write(TProtocol oprot) throws TException {
         scheme(oprot).write(oprot, this);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("GetQueryId_args(");
         boolean first = true;
         sb.append("req:");
         if (this.req == null) {
            sb.append("null");
         } else {
            sb.append(this.req);
         }

         first = false;
         sb.append(")");
         return sb.toString();
      }

      public void validate() throws TException {
         if (this.req != null) {
            this.req.validate();
         }

      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         try {
            this.write(new TCompactProtocol(new TIOStreamTransport(out)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         try {
            this.read(new TCompactProtocol(new TIOStreamTransport(in)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private static IScheme scheme(TProtocol proto) {
         return (StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
      }

      static {
         Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
         tmpMap.put(TCLIService.GetQueryId_args._Fields.REQ, new FieldMetaData("req", (byte)3, new StructMetaData((byte)12, TGetQueryIdReq.class)));
         metaDataMap = Collections.unmodifiableMap(tmpMap);
         FieldMetaData.addStructMetaDataMap(GetQueryId_args.class, metaDataMap);
      }

      public static enum _Fields implements TFieldIdEnum {
         REQ((short)1, "req");

         private static final Map byName = new HashMap();
         private final short _thriftId;
         private final String _fieldName;

         @Nullable
         public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
               case 1:
                  return REQ;
               default:
                  return null;
            }
         }

         public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) {
               throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            } else {
               return fields;
            }
         }

         @Nullable
         public static _Fields findByName(String name) {
            return (_Fields)byName.get(name);
         }

         private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
         }

         public short getThriftFieldId() {
            return this._thriftId;
         }

         public String getFieldName() {
            return this._fieldName;
         }

         static {
            for(_Fields field : EnumSet.allOf(_Fields.class)) {
               byName.put(field.getFieldName(), field);
            }

         }
      }

      private static class GetQueryId_argsStandardSchemeFactory implements SchemeFactory {
         private GetQueryId_argsStandardSchemeFactory() {
         }

         public GetQueryId_argsStandardScheme getScheme() {
            return new GetQueryId_argsStandardScheme();
         }
      }

      private static class GetQueryId_argsStandardScheme extends StandardScheme {
         private GetQueryId_argsStandardScheme() {
         }

         public void read(TProtocol iprot, GetQueryId_args struct) throws TException {
            iprot.readStructBegin();

            while(true) {
               TField schemeField = iprot.readFieldBegin();
               if (schemeField.type == 0) {
                  iprot.readStructEnd();
                  struct.validate();
                  return;
               }

               switch (schemeField.id) {
                  case 1:
                     if (schemeField.type == 12) {
                        struct.req = new TGetQueryIdReq();
                        struct.req.read(iprot);
                        struct.setReqIsSet(true);
                     } else {
                        TProtocolUtil.skip(iprot, schemeField.type);
                     }
                     break;
                  default:
                     TProtocolUtil.skip(iprot, schemeField.type);
               }

               iprot.readFieldEnd();
            }
         }

         public void write(TProtocol oprot, GetQueryId_args struct) throws TException {
            struct.validate();
            oprot.writeStructBegin(TCLIService.GetQueryId_args.STRUCT_DESC);
            if (struct.req != null) {
               oprot.writeFieldBegin(TCLIService.GetQueryId_args.REQ_FIELD_DESC);
               struct.req.write(oprot);
               oprot.writeFieldEnd();
            }

            oprot.writeFieldStop();
            oprot.writeStructEnd();
         }
      }

      private static class GetQueryId_argsTupleSchemeFactory implements SchemeFactory {
         private GetQueryId_argsTupleSchemeFactory() {
         }

         public GetQueryId_argsTupleScheme getScheme() {
            return new GetQueryId_argsTupleScheme();
         }
      }

      private static class GetQueryId_argsTupleScheme extends TupleScheme {
         private GetQueryId_argsTupleScheme() {
         }

         public void write(TProtocol prot, GetQueryId_args struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol)prot;
            BitSet optionals = new BitSet();
            if (struct.isSetReq()) {
               optionals.set(0);
            }

            oprot.writeBitSet(optionals, 1);
            if (struct.isSetReq()) {
               struct.req.write(oprot);
            }

         }

         public void read(TProtocol prot, GetQueryId_args struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol)prot;
            BitSet incoming = iprot.readBitSet(1);
            if (incoming.get(0)) {
               struct.req = new TGetQueryIdReq();
               struct.req.read(iprot);
               struct.setReqIsSet(true);
            }

         }
      }
   }

   @Public
   @Stable
   public static class GetQueryId_result implements TBase, Serializable, Cloneable, Comparable {
      private static final TStruct STRUCT_DESC = new TStruct("GetQueryId_result");
      private static final TField SUCCESS_FIELD_DESC = new TField("success", (byte)12, (short)0);
      private static final SchemeFactory STANDARD_SCHEME_FACTORY = new GetQueryId_resultStandardSchemeFactory();
      private static final SchemeFactory TUPLE_SCHEME_FACTORY = new GetQueryId_resultTupleSchemeFactory();
      @Nullable
      private TGetQueryIdResp success;
      public static final Map metaDataMap;

      public GetQueryId_result() {
      }

      public GetQueryId_result(TGetQueryIdResp success) {
         this();
         this.success = success;
      }

      public GetQueryId_result(GetQueryId_result other) {
         if (other.isSetSuccess()) {
            this.success = new TGetQueryIdResp(other.success);
         }

      }

      public GetQueryId_result deepCopy() {
         return new GetQueryId_result(this);
      }

      public void clear() {
         this.success = null;
      }

      @Nullable
      public TGetQueryIdResp getSuccess() {
         return this.success;
      }

      public void setSuccess(@Nullable TGetQueryIdResp success) {
         this.success = success;
      }

      public void unsetSuccess() {
         this.success = null;
      }

      public boolean isSetSuccess() {
         return this.success != null;
      }

      public void setSuccessIsSet(boolean value) {
         if (!value) {
            this.success = null;
         }

      }

      public void setFieldValue(_Fields field, @Nullable Object value) {
         switch (field) {
            case SUCCESS:
               if (value == null) {
                  this.unsetSuccess();
               } else {
                  this.setSuccess((TGetQueryIdResp)value);
               }
            default:
         }
      }

      @Nullable
      public Object getFieldValue(_Fields field) {
         switch (field) {
            case SUCCESS:
               return this.getSuccess();
            default:
               throw new IllegalStateException();
         }
      }

      public boolean isSet(_Fields field) {
         if (field == null) {
            throw new IllegalArgumentException();
         } else {
            switch (field) {
               case SUCCESS:
                  return this.isSetSuccess();
               default:
                  throw new IllegalStateException();
            }
         }
      }

      public boolean equals(Object that) {
         return that instanceof GetQueryId_result ? this.equals((GetQueryId_result)that) : false;
      }

      public boolean equals(GetQueryId_result that) {
         if (that == null) {
            return false;
         } else if (this == that) {
            return true;
         } else {
            boolean this_present_success = this.isSetSuccess();
            boolean that_present_success = that.isSetSuccess();
            if (this_present_success || that_present_success) {
               if (!this_present_success || !that_present_success) {
                  return false;
               }

               if (!this.success.equals(that.success)) {
                  return false;
               }
            }

            return true;
         }
      }

      public int hashCode() {
         int hashCode = 1;
         hashCode = hashCode * 8191 + (this.isSetSuccess() ? 131071 : 524287);
         if (this.isSetSuccess()) {
            hashCode = hashCode * 8191 + this.success.hashCode();
         }

         return hashCode;
      }

      public int compareTo(GetQueryId_result other) {
         if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
         } else {
            int lastComparison = 0;
            lastComparison = Boolean.compare(this.isSetSuccess(), other.isSetSuccess());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetSuccess()) {
                  lastComparison = TBaseHelper.compareTo(this.success, other.success);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               return 0;
            }
         }
      }

      @Nullable
      public _Fields fieldForId(int fieldId) {
         return TCLIService.GetQueryId_result._Fields.findByThriftId(fieldId);
      }

      public void read(TProtocol iprot) throws TException {
         scheme(iprot).read(iprot, this);
      }

      public void write(TProtocol oprot) throws TException {
         scheme(oprot).write(oprot, this);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("GetQueryId_result(");
         boolean first = true;
         sb.append("success:");
         if (this.success == null) {
            sb.append("null");
         } else {
            sb.append(this.success);
         }

         first = false;
         sb.append(")");
         return sb.toString();
      }

      public void validate() throws TException {
         if (this.success != null) {
            this.success.validate();
         }

      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         try {
            this.write(new TCompactProtocol(new TIOStreamTransport(out)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         try {
            this.read(new TCompactProtocol(new TIOStreamTransport(in)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private static IScheme scheme(TProtocol proto) {
         return (StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
      }

      static {
         Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
         tmpMap.put(TCLIService.GetQueryId_result._Fields.SUCCESS, new FieldMetaData("success", (byte)3, new StructMetaData((byte)12, TGetQueryIdResp.class)));
         metaDataMap = Collections.unmodifiableMap(tmpMap);
         FieldMetaData.addStructMetaDataMap(GetQueryId_result.class, metaDataMap);
      }

      public static enum _Fields implements TFieldIdEnum {
         SUCCESS((short)0, "success");

         private static final Map byName = new HashMap();
         private final short _thriftId;
         private final String _fieldName;

         @Nullable
         public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
               case 0:
                  return SUCCESS;
               default:
                  return null;
            }
         }

         public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) {
               throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            } else {
               return fields;
            }
         }

         @Nullable
         public static _Fields findByName(String name) {
            return (_Fields)byName.get(name);
         }

         private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
         }

         public short getThriftFieldId() {
            return this._thriftId;
         }

         public String getFieldName() {
            return this._fieldName;
         }

         static {
            for(_Fields field : EnumSet.allOf(_Fields.class)) {
               byName.put(field.getFieldName(), field);
            }

         }
      }

      private static class GetQueryId_resultStandardSchemeFactory implements SchemeFactory {
         private GetQueryId_resultStandardSchemeFactory() {
         }

         public GetQueryId_resultStandardScheme getScheme() {
            return new GetQueryId_resultStandardScheme();
         }
      }

      private static class GetQueryId_resultStandardScheme extends StandardScheme {
         private GetQueryId_resultStandardScheme() {
         }

         public void read(TProtocol iprot, GetQueryId_result struct) throws TException {
            iprot.readStructBegin();

            while(true) {
               TField schemeField = iprot.readFieldBegin();
               if (schemeField.type == 0) {
                  iprot.readStructEnd();
                  struct.validate();
                  return;
               }

               switch (schemeField.id) {
                  case 0:
                     if (schemeField.type == 12) {
                        struct.success = new TGetQueryIdResp();
                        struct.success.read(iprot);
                        struct.setSuccessIsSet(true);
                     } else {
                        TProtocolUtil.skip(iprot, schemeField.type);
                     }
                     break;
                  default:
                     TProtocolUtil.skip(iprot, schemeField.type);
               }

               iprot.readFieldEnd();
            }
         }

         public void write(TProtocol oprot, GetQueryId_result struct) throws TException {
            struct.validate();
            oprot.writeStructBegin(TCLIService.GetQueryId_result.STRUCT_DESC);
            if (struct.success != null) {
               oprot.writeFieldBegin(TCLIService.GetQueryId_result.SUCCESS_FIELD_DESC);
               struct.success.write(oprot);
               oprot.writeFieldEnd();
            }

            oprot.writeFieldStop();
            oprot.writeStructEnd();
         }
      }

      private static class GetQueryId_resultTupleSchemeFactory implements SchemeFactory {
         private GetQueryId_resultTupleSchemeFactory() {
         }

         public GetQueryId_resultTupleScheme getScheme() {
            return new GetQueryId_resultTupleScheme();
         }
      }

      private static class GetQueryId_resultTupleScheme extends TupleScheme {
         private GetQueryId_resultTupleScheme() {
         }

         public void write(TProtocol prot, GetQueryId_result struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol)prot;
            BitSet optionals = new BitSet();
            if (struct.isSetSuccess()) {
               optionals.set(0);
            }

            oprot.writeBitSet(optionals, 1);
            if (struct.isSetSuccess()) {
               struct.success.write(oprot);
            }

         }

         public void read(TProtocol prot, GetQueryId_result struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol)prot;
            BitSet incoming = iprot.readBitSet(1);
            if (incoming.get(0)) {
               struct.success = new TGetQueryIdResp();
               struct.success.read(iprot);
               struct.setSuccessIsSet(true);
            }

         }
      }
   }

   @Public
   @Stable
   public static class SetClientInfo_args implements TBase, Serializable, Cloneable, Comparable {
      private static final TStruct STRUCT_DESC = new TStruct("SetClientInfo_args");
      private static final TField REQ_FIELD_DESC = new TField("req", (byte)12, (short)1);
      private static final SchemeFactory STANDARD_SCHEME_FACTORY = new SetClientInfo_argsStandardSchemeFactory();
      private static final SchemeFactory TUPLE_SCHEME_FACTORY = new SetClientInfo_argsTupleSchemeFactory();
      @Nullable
      private TSetClientInfoReq req;
      public static final Map metaDataMap;

      public SetClientInfo_args() {
      }

      public SetClientInfo_args(TSetClientInfoReq req) {
         this();
         this.req = req;
      }

      public SetClientInfo_args(SetClientInfo_args other) {
         if (other.isSetReq()) {
            this.req = new TSetClientInfoReq(other.req);
         }

      }

      public SetClientInfo_args deepCopy() {
         return new SetClientInfo_args(this);
      }

      public void clear() {
         this.req = null;
      }

      @Nullable
      public TSetClientInfoReq getReq() {
         return this.req;
      }

      public void setReq(@Nullable TSetClientInfoReq req) {
         this.req = req;
      }

      public void unsetReq() {
         this.req = null;
      }

      public boolean isSetReq() {
         return this.req != null;
      }

      public void setReqIsSet(boolean value) {
         if (!value) {
            this.req = null;
         }

      }

      public void setFieldValue(_Fields field, @Nullable Object value) {
         switch (field) {
            case REQ:
               if (value == null) {
                  this.unsetReq();
               } else {
                  this.setReq((TSetClientInfoReq)value);
               }
            default:
         }
      }

      @Nullable
      public Object getFieldValue(_Fields field) {
         switch (field) {
            case REQ:
               return this.getReq();
            default:
               throw new IllegalStateException();
         }
      }

      public boolean isSet(_Fields field) {
         if (field == null) {
            throw new IllegalArgumentException();
         } else {
            switch (field) {
               case REQ:
                  return this.isSetReq();
               default:
                  throw new IllegalStateException();
            }
         }
      }

      public boolean equals(Object that) {
         return that instanceof SetClientInfo_args ? this.equals((SetClientInfo_args)that) : false;
      }

      public boolean equals(SetClientInfo_args that) {
         if (that == null) {
            return false;
         } else if (this == that) {
            return true;
         } else {
            boolean this_present_req = this.isSetReq();
            boolean that_present_req = that.isSetReq();
            if (this_present_req || that_present_req) {
               if (!this_present_req || !that_present_req) {
                  return false;
               }

               if (!this.req.equals(that.req)) {
                  return false;
               }
            }

            return true;
         }
      }

      public int hashCode() {
         int hashCode = 1;
         hashCode = hashCode * 8191 + (this.isSetReq() ? 131071 : 524287);
         if (this.isSetReq()) {
            hashCode = hashCode * 8191 + this.req.hashCode();
         }

         return hashCode;
      }

      public int compareTo(SetClientInfo_args other) {
         if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
         } else {
            int lastComparison = 0;
            lastComparison = Boolean.compare(this.isSetReq(), other.isSetReq());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetReq()) {
                  lastComparison = TBaseHelper.compareTo(this.req, other.req);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               return 0;
            }
         }
      }

      @Nullable
      public _Fields fieldForId(int fieldId) {
         return TCLIService.SetClientInfo_args._Fields.findByThriftId(fieldId);
      }

      public void read(TProtocol iprot) throws TException {
         scheme(iprot).read(iprot, this);
      }

      public void write(TProtocol oprot) throws TException {
         scheme(oprot).write(oprot, this);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("SetClientInfo_args(");
         boolean first = true;
         sb.append("req:");
         if (this.req == null) {
            sb.append("null");
         } else {
            sb.append(this.req);
         }

         first = false;
         sb.append(")");
         return sb.toString();
      }

      public void validate() throws TException {
         if (this.req != null) {
            this.req.validate();
         }

      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         try {
            this.write(new TCompactProtocol(new TIOStreamTransport(out)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         try {
            this.read(new TCompactProtocol(new TIOStreamTransport(in)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private static IScheme scheme(TProtocol proto) {
         return (StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
      }

      static {
         Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
         tmpMap.put(TCLIService.SetClientInfo_args._Fields.REQ, new FieldMetaData("req", (byte)3, new StructMetaData((byte)12, TSetClientInfoReq.class)));
         metaDataMap = Collections.unmodifiableMap(tmpMap);
         FieldMetaData.addStructMetaDataMap(SetClientInfo_args.class, metaDataMap);
      }

      public static enum _Fields implements TFieldIdEnum {
         REQ((short)1, "req");

         private static final Map byName = new HashMap();
         private final short _thriftId;
         private final String _fieldName;

         @Nullable
         public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
               case 1:
                  return REQ;
               default:
                  return null;
            }
         }

         public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) {
               throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            } else {
               return fields;
            }
         }

         @Nullable
         public static _Fields findByName(String name) {
            return (_Fields)byName.get(name);
         }

         private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
         }

         public short getThriftFieldId() {
            return this._thriftId;
         }

         public String getFieldName() {
            return this._fieldName;
         }

         static {
            for(_Fields field : EnumSet.allOf(_Fields.class)) {
               byName.put(field.getFieldName(), field);
            }

         }
      }

      private static class SetClientInfo_argsStandardSchemeFactory implements SchemeFactory {
         private SetClientInfo_argsStandardSchemeFactory() {
         }

         public SetClientInfo_argsStandardScheme getScheme() {
            return new SetClientInfo_argsStandardScheme();
         }
      }

      private static class SetClientInfo_argsStandardScheme extends StandardScheme {
         private SetClientInfo_argsStandardScheme() {
         }

         public void read(TProtocol iprot, SetClientInfo_args struct) throws TException {
            iprot.readStructBegin();

            while(true) {
               TField schemeField = iprot.readFieldBegin();
               if (schemeField.type == 0) {
                  iprot.readStructEnd();
                  struct.validate();
                  return;
               }

               switch (schemeField.id) {
                  case 1:
                     if (schemeField.type == 12) {
                        struct.req = new TSetClientInfoReq();
                        struct.req.read(iprot);
                        struct.setReqIsSet(true);
                     } else {
                        TProtocolUtil.skip(iprot, schemeField.type);
                     }
                     break;
                  default:
                     TProtocolUtil.skip(iprot, schemeField.type);
               }

               iprot.readFieldEnd();
            }
         }

         public void write(TProtocol oprot, SetClientInfo_args struct) throws TException {
            struct.validate();
            oprot.writeStructBegin(TCLIService.SetClientInfo_args.STRUCT_DESC);
            if (struct.req != null) {
               oprot.writeFieldBegin(TCLIService.SetClientInfo_args.REQ_FIELD_DESC);
               struct.req.write(oprot);
               oprot.writeFieldEnd();
            }

            oprot.writeFieldStop();
            oprot.writeStructEnd();
         }
      }

      private static class SetClientInfo_argsTupleSchemeFactory implements SchemeFactory {
         private SetClientInfo_argsTupleSchemeFactory() {
         }

         public SetClientInfo_argsTupleScheme getScheme() {
            return new SetClientInfo_argsTupleScheme();
         }
      }

      private static class SetClientInfo_argsTupleScheme extends TupleScheme {
         private SetClientInfo_argsTupleScheme() {
         }

         public void write(TProtocol prot, SetClientInfo_args struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol)prot;
            BitSet optionals = new BitSet();
            if (struct.isSetReq()) {
               optionals.set(0);
            }

            oprot.writeBitSet(optionals, 1);
            if (struct.isSetReq()) {
               struct.req.write(oprot);
            }

         }

         public void read(TProtocol prot, SetClientInfo_args struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol)prot;
            BitSet incoming = iprot.readBitSet(1);
            if (incoming.get(0)) {
               struct.req = new TSetClientInfoReq();
               struct.req.read(iprot);
               struct.setReqIsSet(true);
            }

         }
      }
   }

   @Public
   @Stable
   public static class SetClientInfo_result implements TBase, Serializable, Cloneable, Comparable {
      private static final TStruct STRUCT_DESC = new TStruct("SetClientInfo_result");
      private static final TField SUCCESS_FIELD_DESC = new TField("success", (byte)12, (short)0);
      private static final SchemeFactory STANDARD_SCHEME_FACTORY = new SetClientInfo_resultStandardSchemeFactory();
      private static final SchemeFactory TUPLE_SCHEME_FACTORY = new SetClientInfo_resultTupleSchemeFactory();
      @Nullable
      private TSetClientInfoResp success;
      public static final Map metaDataMap;

      public SetClientInfo_result() {
      }

      public SetClientInfo_result(TSetClientInfoResp success) {
         this();
         this.success = success;
      }

      public SetClientInfo_result(SetClientInfo_result other) {
         if (other.isSetSuccess()) {
            this.success = new TSetClientInfoResp(other.success);
         }

      }

      public SetClientInfo_result deepCopy() {
         return new SetClientInfo_result(this);
      }

      public void clear() {
         this.success = null;
      }

      @Nullable
      public TSetClientInfoResp getSuccess() {
         return this.success;
      }

      public void setSuccess(@Nullable TSetClientInfoResp success) {
         this.success = success;
      }

      public void unsetSuccess() {
         this.success = null;
      }

      public boolean isSetSuccess() {
         return this.success != null;
      }

      public void setSuccessIsSet(boolean value) {
         if (!value) {
            this.success = null;
         }

      }

      public void setFieldValue(_Fields field, @Nullable Object value) {
         switch (field) {
            case SUCCESS:
               if (value == null) {
                  this.unsetSuccess();
               } else {
                  this.setSuccess((TSetClientInfoResp)value);
               }
            default:
         }
      }

      @Nullable
      public Object getFieldValue(_Fields field) {
         switch (field) {
            case SUCCESS:
               return this.getSuccess();
            default:
               throw new IllegalStateException();
         }
      }

      public boolean isSet(_Fields field) {
         if (field == null) {
            throw new IllegalArgumentException();
         } else {
            switch (field) {
               case SUCCESS:
                  return this.isSetSuccess();
               default:
                  throw new IllegalStateException();
            }
         }
      }

      public boolean equals(Object that) {
         return that instanceof SetClientInfo_result ? this.equals((SetClientInfo_result)that) : false;
      }

      public boolean equals(SetClientInfo_result that) {
         if (that == null) {
            return false;
         } else if (this == that) {
            return true;
         } else {
            boolean this_present_success = this.isSetSuccess();
            boolean that_present_success = that.isSetSuccess();
            if (this_present_success || that_present_success) {
               if (!this_present_success || !that_present_success) {
                  return false;
               }

               if (!this.success.equals(that.success)) {
                  return false;
               }
            }

            return true;
         }
      }

      public int hashCode() {
         int hashCode = 1;
         hashCode = hashCode * 8191 + (this.isSetSuccess() ? 131071 : 524287);
         if (this.isSetSuccess()) {
            hashCode = hashCode * 8191 + this.success.hashCode();
         }

         return hashCode;
      }

      public int compareTo(SetClientInfo_result other) {
         if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
         } else {
            int lastComparison = 0;
            lastComparison = Boolean.compare(this.isSetSuccess(), other.isSetSuccess());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetSuccess()) {
                  lastComparison = TBaseHelper.compareTo(this.success, other.success);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               return 0;
            }
         }
      }

      @Nullable
      public _Fields fieldForId(int fieldId) {
         return TCLIService.SetClientInfo_result._Fields.findByThriftId(fieldId);
      }

      public void read(TProtocol iprot) throws TException {
         scheme(iprot).read(iprot, this);
      }

      public void write(TProtocol oprot) throws TException {
         scheme(oprot).write(oprot, this);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("SetClientInfo_result(");
         boolean first = true;
         sb.append("success:");
         if (this.success == null) {
            sb.append("null");
         } else {
            sb.append(this.success);
         }

         first = false;
         sb.append(")");
         return sb.toString();
      }

      public void validate() throws TException {
         if (this.success != null) {
            this.success.validate();
         }

      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         try {
            this.write(new TCompactProtocol(new TIOStreamTransport(out)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         try {
            this.read(new TCompactProtocol(new TIOStreamTransport(in)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private static IScheme scheme(TProtocol proto) {
         return (StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
      }

      static {
         Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
         tmpMap.put(TCLIService.SetClientInfo_result._Fields.SUCCESS, new FieldMetaData("success", (byte)3, new StructMetaData((byte)12, TSetClientInfoResp.class)));
         metaDataMap = Collections.unmodifiableMap(tmpMap);
         FieldMetaData.addStructMetaDataMap(SetClientInfo_result.class, metaDataMap);
      }

      public static enum _Fields implements TFieldIdEnum {
         SUCCESS((short)0, "success");

         private static final Map byName = new HashMap();
         private final short _thriftId;
         private final String _fieldName;

         @Nullable
         public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
               case 0:
                  return SUCCESS;
               default:
                  return null;
            }
         }

         public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) {
               throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            } else {
               return fields;
            }
         }

         @Nullable
         public static _Fields findByName(String name) {
            return (_Fields)byName.get(name);
         }

         private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
         }

         public short getThriftFieldId() {
            return this._thriftId;
         }

         public String getFieldName() {
            return this._fieldName;
         }

         static {
            for(_Fields field : EnumSet.allOf(_Fields.class)) {
               byName.put(field.getFieldName(), field);
            }

         }
      }

      private static class SetClientInfo_resultStandardSchemeFactory implements SchemeFactory {
         private SetClientInfo_resultStandardSchemeFactory() {
         }

         public SetClientInfo_resultStandardScheme getScheme() {
            return new SetClientInfo_resultStandardScheme();
         }
      }

      private static class SetClientInfo_resultStandardScheme extends StandardScheme {
         private SetClientInfo_resultStandardScheme() {
         }

         public void read(TProtocol iprot, SetClientInfo_result struct) throws TException {
            iprot.readStructBegin();

            while(true) {
               TField schemeField = iprot.readFieldBegin();
               if (schemeField.type == 0) {
                  iprot.readStructEnd();
                  struct.validate();
                  return;
               }

               switch (schemeField.id) {
                  case 0:
                     if (schemeField.type == 12) {
                        struct.success = new TSetClientInfoResp();
                        struct.success.read(iprot);
                        struct.setSuccessIsSet(true);
                     } else {
                        TProtocolUtil.skip(iprot, schemeField.type);
                     }
                     break;
                  default:
                     TProtocolUtil.skip(iprot, schemeField.type);
               }

               iprot.readFieldEnd();
            }
         }

         public void write(TProtocol oprot, SetClientInfo_result struct) throws TException {
            struct.validate();
            oprot.writeStructBegin(TCLIService.SetClientInfo_result.STRUCT_DESC);
            if (struct.success != null) {
               oprot.writeFieldBegin(TCLIService.SetClientInfo_result.SUCCESS_FIELD_DESC);
               struct.success.write(oprot);
               oprot.writeFieldEnd();
            }

            oprot.writeFieldStop();
            oprot.writeStructEnd();
         }
      }

      private static class SetClientInfo_resultTupleSchemeFactory implements SchemeFactory {
         private SetClientInfo_resultTupleSchemeFactory() {
         }

         public SetClientInfo_resultTupleScheme getScheme() {
            return new SetClientInfo_resultTupleScheme();
         }
      }

      private static class SetClientInfo_resultTupleScheme extends TupleScheme {
         private SetClientInfo_resultTupleScheme() {
         }

         public void write(TProtocol prot, SetClientInfo_result struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol)prot;
            BitSet optionals = new BitSet();
            if (struct.isSetSuccess()) {
               optionals.set(0);
            }

            oprot.writeBitSet(optionals, 1);
            if (struct.isSetSuccess()) {
               struct.success.write(oprot);
            }

         }

         public void read(TProtocol prot, SetClientInfo_result struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol)prot;
            BitSet incoming = iprot.readBitSet(1);
            if (incoming.get(0)) {
               struct.success = new TSetClientInfoResp();
               struct.success.read(iprot);
               struct.setSuccessIsSet(true);
            }

         }
      }
   }

   @Public
   @Stable
   public static class UploadData_args implements TBase, Serializable, Cloneable, Comparable {
      private static final TStruct STRUCT_DESC = new TStruct("UploadData_args");
      private static final TField REQ_FIELD_DESC = new TField("req", (byte)12, (short)1);
      private static final SchemeFactory STANDARD_SCHEME_FACTORY = new UploadData_argsStandardSchemeFactory();
      private static final SchemeFactory TUPLE_SCHEME_FACTORY = new UploadData_argsTupleSchemeFactory();
      @Nullable
      private TUploadDataReq req;
      public static final Map metaDataMap;

      public UploadData_args() {
      }

      public UploadData_args(TUploadDataReq req) {
         this();
         this.req = req;
      }

      public UploadData_args(UploadData_args other) {
         if (other.isSetReq()) {
            this.req = new TUploadDataReq(other.req);
         }

      }

      public UploadData_args deepCopy() {
         return new UploadData_args(this);
      }

      public void clear() {
         this.req = null;
      }

      @Nullable
      public TUploadDataReq getReq() {
         return this.req;
      }

      public void setReq(@Nullable TUploadDataReq req) {
         this.req = req;
      }

      public void unsetReq() {
         this.req = null;
      }

      public boolean isSetReq() {
         return this.req != null;
      }

      public void setReqIsSet(boolean value) {
         if (!value) {
            this.req = null;
         }

      }

      public void setFieldValue(_Fields field, @Nullable Object value) {
         switch (field) {
            case REQ:
               if (value == null) {
                  this.unsetReq();
               } else {
                  this.setReq((TUploadDataReq)value);
               }
            default:
         }
      }

      @Nullable
      public Object getFieldValue(_Fields field) {
         switch (field) {
            case REQ:
               return this.getReq();
            default:
               throw new IllegalStateException();
         }
      }

      public boolean isSet(_Fields field) {
         if (field == null) {
            throw new IllegalArgumentException();
         } else {
            switch (field) {
               case REQ:
                  return this.isSetReq();
               default:
                  throw new IllegalStateException();
            }
         }
      }

      public boolean equals(Object that) {
         return that instanceof UploadData_args ? this.equals((UploadData_args)that) : false;
      }

      public boolean equals(UploadData_args that) {
         if (that == null) {
            return false;
         } else if (this == that) {
            return true;
         } else {
            boolean this_present_req = this.isSetReq();
            boolean that_present_req = that.isSetReq();
            if (this_present_req || that_present_req) {
               if (!this_present_req || !that_present_req) {
                  return false;
               }

               if (!this.req.equals(that.req)) {
                  return false;
               }
            }

            return true;
         }
      }

      public int hashCode() {
         int hashCode = 1;
         hashCode = hashCode * 8191 + (this.isSetReq() ? 131071 : 524287);
         if (this.isSetReq()) {
            hashCode = hashCode * 8191 + this.req.hashCode();
         }

         return hashCode;
      }

      public int compareTo(UploadData_args other) {
         if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
         } else {
            int lastComparison = 0;
            lastComparison = Boolean.compare(this.isSetReq(), other.isSetReq());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetReq()) {
                  lastComparison = TBaseHelper.compareTo(this.req, other.req);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               return 0;
            }
         }
      }

      @Nullable
      public _Fields fieldForId(int fieldId) {
         return TCLIService.UploadData_args._Fields.findByThriftId(fieldId);
      }

      public void read(TProtocol iprot) throws TException {
         scheme(iprot).read(iprot, this);
      }

      public void write(TProtocol oprot) throws TException {
         scheme(oprot).write(oprot, this);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("UploadData_args(");
         boolean first = true;
         sb.append("req:");
         if (this.req == null) {
            sb.append("null");
         } else {
            sb.append(this.req);
         }

         first = false;
         sb.append(")");
         return sb.toString();
      }

      public void validate() throws TException {
         if (this.req != null) {
            this.req.validate();
         }

      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         try {
            this.write(new TCompactProtocol(new TIOStreamTransport(out)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         try {
            this.read(new TCompactProtocol(new TIOStreamTransport(in)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private static IScheme scheme(TProtocol proto) {
         return (StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
      }

      static {
         Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
         tmpMap.put(TCLIService.UploadData_args._Fields.REQ, new FieldMetaData("req", (byte)3, new StructMetaData((byte)12, TUploadDataReq.class)));
         metaDataMap = Collections.unmodifiableMap(tmpMap);
         FieldMetaData.addStructMetaDataMap(UploadData_args.class, metaDataMap);
      }

      public static enum _Fields implements TFieldIdEnum {
         REQ((short)1, "req");

         private static final Map byName = new HashMap();
         private final short _thriftId;
         private final String _fieldName;

         @Nullable
         public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
               case 1:
                  return REQ;
               default:
                  return null;
            }
         }

         public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) {
               throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            } else {
               return fields;
            }
         }

         @Nullable
         public static _Fields findByName(String name) {
            return (_Fields)byName.get(name);
         }

         private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
         }

         public short getThriftFieldId() {
            return this._thriftId;
         }

         public String getFieldName() {
            return this._fieldName;
         }

         static {
            for(_Fields field : EnumSet.allOf(_Fields.class)) {
               byName.put(field.getFieldName(), field);
            }

         }
      }

      private static class UploadData_argsStandardSchemeFactory implements SchemeFactory {
         private UploadData_argsStandardSchemeFactory() {
         }

         public UploadData_argsStandardScheme getScheme() {
            return new UploadData_argsStandardScheme();
         }
      }

      private static class UploadData_argsStandardScheme extends StandardScheme {
         private UploadData_argsStandardScheme() {
         }

         public void read(TProtocol iprot, UploadData_args struct) throws TException {
            iprot.readStructBegin();

            while(true) {
               TField schemeField = iprot.readFieldBegin();
               if (schemeField.type == 0) {
                  iprot.readStructEnd();
                  struct.validate();
                  return;
               }

               switch (schemeField.id) {
                  case 1:
                     if (schemeField.type == 12) {
                        struct.req = new TUploadDataReq();
                        struct.req.read(iprot);
                        struct.setReqIsSet(true);
                     } else {
                        TProtocolUtil.skip(iprot, schemeField.type);
                     }
                     break;
                  default:
                     TProtocolUtil.skip(iprot, schemeField.type);
               }

               iprot.readFieldEnd();
            }
         }

         public void write(TProtocol oprot, UploadData_args struct) throws TException {
            struct.validate();
            oprot.writeStructBegin(TCLIService.UploadData_args.STRUCT_DESC);
            if (struct.req != null) {
               oprot.writeFieldBegin(TCLIService.UploadData_args.REQ_FIELD_DESC);
               struct.req.write(oprot);
               oprot.writeFieldEnd();
            }

            oprot.writeFieldStop();
            oprot.writeStructEnd();
         }
      }

      private static class UploadData_argsTupleSchemeFactory implements SchemeFactory {
         private UploadData_argsTupleSchemeFactory() {
         }

         public UploadData_argsTupleScheme getScheme() {
            return new UploadData_argsTupleScheme();
         }
      }

      private static class UploadData_argsTupleScheme extends TupleScheme {
         private UploadData_argsTupleScheme() {
         }

         public void write(TProtocol prot, UploadData_args struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol)prot;
            BitSet optionals = new BitSet();
            if (struct.isSetReq()) {
               optionals.set(0);
            }

            oprot.writeBitSet(optionals, 1);
            if (struct.isSetReq()) {
               struct.req.write(oprot);
            }

         }

         public void read(TProtocol prot, UploadData_args struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol)prot;
            BitSet incoming = iprot.readBitSet(1);
            if (incoming.get(0)) {
               struct.req = new TUploadDataReq();
               struct.req.read(iprot);
               struct.setReqIsSet(true);
            }

         }
      }
   }

   @Public
   @Stable
   public static class UploadData_result implements TBase, Serializable, Cloneable, Comparable {
      private static final TStruct STRUCT_DESC = new TStruct("UploadData_result");
      private static final TField SUCCESS_FIELD_DESC = new TField("success", (byte)12, (short)0);
      private static final SchemeFactory STANDARD_SCHEME_FACTORY = new UploadData_resultStandardSchemeFactory();
      private static final SchemeFactory TUPLE_SCHEME_FACTORY = new UploadData_resultTupleSchemeFactory();
      @Nullable
      private TUploadDataResp success;
      public static final Map metaDataMap;

      public UploadData_result() {
      }

      public UploadData_result(TUploadDataResp success) {
         this();
         this.success = success;
      }

      public UploadData_result(UploadData_result other) {
         if (other.isSetSuccess()) {
            this.success = new TUploadDataResp(other.success);
         }

      }

      public UploadData_result deepCopy() {
         return new UploadData_result(this);
      }

      public void clear() {
         this.success = null;
      }

      @Nullable
      public TUploadDataResp getSuccess() {
         return this.success;
      }

      public void setSuccess(@Nullable TUploadDataResp success) {
         this.success = success;
      }

      public void unsetSuccess() {
         this.success = null;
      }

      public boolean isSetSuccess() {
         return this.success != null;
      }

      public void setSuccessIsSet(boolean value) {
         if (!value) {
            this.success = null;
         }

      }

      public void setFieldValue(_Fields field, @Nullable Object value) {
         switch (field) {
            case SUCCESS:
               if (value == null) {
                  this.unsetSuccess();
               } else {
                  this.setSuccess((TUploadDataResp)value);
               }
            default:
         }
      }

      @Nullable
      public Object getFieldValue(_Fields field) {
         switch (field) {
            case SUCCESS:
               return this.getSuccess();
            default:
               throw new IllegalStateException();
         }
      }

      public boolean isSet(_Fields field) {
         if (field == null) {
            throw new IllegalArgumentException();
         } else {
            switch (field) {
               case SUCCESS:
                  return this.isSetSuccess();
               default:
                  throw new IllegalStateException();
            }
         }
      }

      public boolean equals(Object that) {
         return that instanceof UploadData_result ? this.equals((UploadData_result)that) : false;
      }

      public boolean equals(UploadData_result that) {
         if (that == null) {
            return false;
         } else if (this == that) {
            return true;
         } else {
            boolean this_present_success = this.isSetSuccess();
            boolean that_present_success = that.isSetSuccess();
            if (this_present_success || that_present_success) {
               if (!this_present_success || !that_present_success) {
                  return false;
               }

               if (!this.success.equals(that.success)) {
                  return false;
               }
            }

            return true;
         }
      }

      public int hashCode() {
         int hashCode = 1;
         hashCode = hashCode * 8191 + (this.isSetSuccess() ? 131071 : 524287);
         if (this.isSetSuccess()) {
            hashCode = hashCode * 8191 + this.success.hashCode();
         }

         return hashCode;
      }

      public int compareTo(UploadData_result other) {
         if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
         } else {
            int lastComparison = 0;
            lastComparison = Boolean.compare(this.isSetSuccess(), other.isSetSuccess());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetSuccess()) {
                  lastComparison = TBaseHelper.compareTo(this.success, other.success);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               return 0;
            }
         }
      }

      @Nullable
      public _Fields fieldForId(int fieldId) {
         return TCLIService.UploadData_result._Fields.findByThriftId(fieldId);
      }

      public void read(TProtocol iprot) throws TException {
         scheme(iprot).read(iprot, this);
      }

      public void write(TProtocol oprot) throws TException {
         scheme(oprot).write(oprot, this);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("UploadData_result(");
         boolean first = true;
         sb.append("success:");
         if (this.success == null) {
            sb.append("null");
         } else {
            sb.append(this.success);
         }

         first = false;
         sb.append(")");
         return sb.toString();
      }

      public void validate() throws TException {
         if (this.success != null) {
            this.success.validate();
         }

      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         try {
            this.write(new TCompactProtocol(new TIOStreamTransport(out)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         try {
            this.read(new TCompactProtocol(new TIOStreamTransport(in)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private static IScheme scheme(TProtocol proto) {
         return (StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
      }

      static {
         Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
         tmpMap.put(TCLIService.UploadData_result._Fields.SUCCESS, new FieldMetaData("success", (byte)3, new StructMetaData((byte)12, TUploadDataResp.class)));
         metaDataMap = Collections.unmodifiableMap(tmpMap);
         FieldMetaData.addStructMetaDataMap(UploadData_result.class, metaDataMap);
      }

      public static enum _Fields implements TFieldIdEnum {
         SUCCESS((short)0, "success");

         private static final Map byName = new HashMap();
         private final short _thriftId;
         private final String _fieldName;

         @Nullable
         public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
               case 0:
                  return SUCCESS;
               default:
                  return null;
            }
         }

         public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) {
               throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            } else {
               return fields;
            }
         }

         @Nullable
         public static _Fields findByName(String name) {
            return (_Fields)byName.get(name);
         }

         private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
         }

         public short getThriftFieldId() {
            return this._thriftId;
         }

         public String getFieldName() {
            return this._fieldName;
         }

         static {
            for(_Fields field : EnumSet.allOf(_Fields.class)) {
               byName.put(field.getFieldName(), field);
            }

         }
      }

      private static class UploadData_resultStandardSchemeFactory implements SchemeFactory {
         private UploadData_resultStandardSchemeFactory() {
         }

         public UploadData_resultStandardScheme getScheme() {
            return new UploadData_resultStandardScheme();
         }
      }

      private static class UploadData_resultStandardScheme extends StandardScheme {
         private UploadData_resultStandardScheme() {
         }

         public void read(TProtocol iprot, UploadData_result struct) throws TException {
            iprot.readStructBegin();

            while(true) {
               TField schemeField = iprot.readFieldBegin();
               if (schemeField.type == 0) {
                  iprot.readStructEnd();
                  struct.validate();
                  return;
               }

               switch (schemeField.id) {
                  case 0:
                     if (schemeField.type == 12) {
                        struct.success = new TUploadDataResp();
                        struct.success.read(iprot);
                        struct.setSuccessIsSet(true);
                     } else {
                        TProtocolUtil.skip(iprot, schemeField.type);
                     }
                     break;
                  default:
                     TProtocolUtil.skip(iprot, schemeField.type);
               }

               iprot.readFieldEnd();
            }
         }

         public void write(TProtocol oprot, UploadData_result struct) throws TException {
            struct.validate();
            oprot.writeStructBegin(TCLIService.UploadData_result.STRUCT_DESC);
            if (struct.success != null) {
               oprot.writeFieldBegin(TCLIService.UploadData_result.SUCCESS_FIELD_DESC);
               struct.success.write(oprot);
               oprot.writeFieldEnd();
            }

            oprot.writeFieldStop();
            oprot.writeStructEnd();
         }
      }

      private static class UploadData_resultTupleSchemeFactory implements SchemeFactory {
         private UploadData_resultTupleSchemeFactory() {
         }

         public UploadData_resultTupleScheme getScheme() {
            return new UploadData_resultTupleScheme();
         }
      }

      private static class UploadData_resultTupleScheme extends TupleScheme {
         private UploadData_resultTupleScheme() {
         }

         public void write(TProtocol prot, UploadData_result struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol)prot;
            BitSet optionals = new BitSet();
            if (struct.isSetSuccess()) {
               optionals.set(0);
            }

            oprot.writeBitSet(optionals, 1);
            if (struct.isSetSuccess()) {
               struct.success.write(oprot);
            }

         }

         public void read(TProtocol prot, UploadData_result struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol)prot;
            BitSet incoming = iprot.readBitSet(1);
            if (incoming.get(0)) {
               struct.success = new TUploadDataResp();
               struct.success.read(iprot);
               struct.setSuccessIsSet(true);
            }

         }
      }
   }

   @Public
   @Stable
   public static class DownloadData_args implements TBase, Serializable, Cloneable, Comparable {
      private static final TStruct STRUCT_DESC = new TStruct("DownloadData_args");
      private static final TField REQ_FIELD_DESC = new TField("req", (byte)12, (short)1);
      private static final SchemeFactory STANDARD_SCHEME_FACTORY = new DownloadData_argsStandardSchemeFactory();
      private static final SchemeFactory TUPLE_SCHEME_FACTORY = new DownloadData_argsTupleSchemeFactory();
      @Nullable
      private TDownloadDataReq req;
      public static final Map metaDataMap;

      public DownloadData_args() {
      }

      public DownloadData_args(TDownloadDataReq req) {
         this();
         this.req = req;
      }

      public DownloadData_args(DownloadData_args other) {
         if (other.isSetReq()) {
            this.req = new TDownloadDataReq(other.req);
         }

      }

      public DownloadData_args deepCopy() {
         return new DownloadData_args(this);
      }

      public void clear() {
         this.req = null;
      }

      @Nullable
      public TDownloadDataReq getReq() {
         return this.req;
      }

      public void setReq(@Nullable TDownloadDataReq req) {
         this.req = req;
      }

      public void unsetReq() {
         this.req = null;
      }

      public boolean isSetReq() {
         return this.req != null;
      }

      public void setReqIsSet(boolean value) {
         if (!value) {
            this.req = null;
         }

      }

      public void setFieldValue(_Fields field, @Nullable Object value) {
         switch (field) {
            case REQ:
               if (value == null) {
                  this.unsetReq();
               } else {
                  this.setReq((TDownloadDataReq)value);
               }
            default:
         }
      }

      @Nullable
      public Object getFieldValue(_Fields field) {
         switch (field) {
            case REQ:
               return this.getReq();
            default:
               throw new IllegalStateException();
         }
      }

      public boolean isSet(_Fields field) {
         if (field == null) {
            throw new IllegalArgumentException();
         } else {
            switch (field) {
               case REQ:
                  return this.isSetReq();
               default:
                  throw new IllegalStateException();
            }
         }
      }

      public boolean equals(Object that) {
         return that instanceof DownloadData_args ? this.equals((DownloadData_args)that) : false;
      }

      public boolean equals(DownloadData_args that) {
         if (that == null) {
            return false;
         } else if (this == that) {
            return true;
         } else {
            boolean this_present_req = this.isSetReq();
            boolean that_present_req = that.isSetReq();
            if (this_present_req || that_present_req) {
               if (!this_present_req || !that_present_req) {
                  return false;
               }

               if (!this.req.equals(that.req)) {
                  return false;
               }
            }

            return true;
         }
      }

      public int hashCode() {
         int hashCode = 1;
         hashCode = hashCode * 8191 + (this.isSetReq() ? 131071 : 524287);
         if (this.isSetReq()) {
            hashCode = hashCode * 8191 + this.req.hashCode();
         }

         return hashCode;
      }

      public int compareTo(DownloadData_args other) {
         if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
         } else {
            int lastComparison = 0;
            lastComparison = Boolean.compare(this.isSetReq(), other.isSetReq());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetReq()) {
                  lastComparison = TBaseHelper.compareTo(this.req, other.req);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               return 0;
            }
         }
      }

      @Nullable
      public _Fields fieldForId(int fieldId) {
         return TCLIService.DownloadData_args._Fields.findByThriftId(fieldId);
      }

      public void read(TProtocol iprot) throws TException {
         scheme(iprot).read(iprot, this);
      }

      public void write(TProtocol oprot) throws TException {
         scheme(oprot).write(oprot, this);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("DownloadData_args(");
         boolean first = true;
         sb.append("req:");
         if (this.req == null) {
            sb.append("null");
         } else {
            sb.append(this.req);
         }

         first = false;
         sb.append(")");
         return sb.toString();
      }

      public void validate() throws TException {
         if (this.req != null) {
            this.req.validate();
         }

      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         try {
            this.write(new TCompactProtocol(new TIOStreamTransport(out)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         try {
            this.read(new TCompactProtocol(new TIOStreamTransport(in)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private static IScheme scheme(TProtocol proto) {
         return (StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
      }

      static {
         Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
         tmpMap.put(TCLIService.DownloadData_args._Fields.REQ, new FieldMetaData("req", (byte)3, new StructMetaData((byte)12, TDownloadDataReq.class)));
         metaDataMap = Collections.unmodifiableMap(tmpMap);
         FieldMetaData.addStructMetaDataMap(DownloadData_args.class, metaDataMap);
      }

      public static enum _Fields implements TFieldIdEnum {
         REQ((short)1, "req");

         private static final Map byName = new HashMap();
         private final short _thriftId;
         private final String _fieldName;

         @Nullable
         public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
               case 1:
                  return REQ;
               default:
                  return null;
            }
         }

         public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) {
               throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            } else {
               return fields;
            }
         }

         @Nullable
         public static _Fields findByName(String name) {
            return (_Fields)byName.get(name);
         }

         private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
         }

         public short getThriftFieldId() {
            return this._thriftId;
         }

         public String getFieldName() {
            return this._fieldName;
         }

         static {
            for(_Fields field : EnumSet.allOf(_Fields.class)) {
               byName.put(field.getFieldName(), field);
            }

         }
      }

      private static class DownloadData_argsStandardSchemeFactory implements SchemeFactory {
         private DownloadData_argsStandardSchemeFactory() {
         }

         public DownloadData_argsStandardScheme getScheme() {
            return new DownloadData_argsStandardScheme();
         }
      }

      private static class DownloadData_argsStandardScheme extends StandardScheme {
         private DownloadData_argsStandardScheme() {
         }

         public void read(TProtocol iprot, DownloadData_args struct) throws TException {
            iprot.readStructBegin();

            while(true) {
               TField schemeField = iprot.readFieldBegin();
               if (schemeField.type == 0) {
                  iprot.readStructEnd();
                  struct.validate();
                  return;
               }

               switch (schemeField.id) {
                  case 1:
                     if (schemeField.type == 12) {
                        struct.req = new TDownloadDataReq();
                        struct.req.read(iprot);
                        struct.setReqIsSet(true);
                     } else {
                        TProtocolUtil.skip(iprot, schemeField.type);
                     }
                     break;
                  default:
                     TProtocolUtil.skip(iprot, schemeField.type);
               }

               iprot.readFieldEnd();
            }
         }

         public void write(TProtocol oprot, DownloadData_args struct) throws TException {
            struct.validate();
            oprot.writeStructBegin(TCLIService.DownloadData_args.STRUCT_DESC);
            if (struct.req != null) {
               oprot.writeFieldBegin(TCLIService.DownloadData_args.REQ_FIELD_DESC);
               struct.req.write(oprot);
               oprot.writeFieldEnd();
            }

            oprot.writeFieldStop();
            oprot.writeStructEnd();
         }
      }

      private static class DownloadData_argsTupleSchemeFactory implements SchemeFactory {
         private DownloadData_argsTupleSchemeFactory() {
         }

         public DownloadData_argsTupleScheme getScheme() {
            return new DownloadData_argsTupleScheme();
         }
      }

      private static class DownloadData_argsTupleScheme extends TupleScheme {
         private DownloadData_argsTupleScheme() {
         }

         public void write(TProtocol prot, DownloadData_args struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol)prot;
            BitSet optionals = new BitSet();
            if (struct.isSetReq()) {
               optionals.set(0);
            }

            oprot.writeBitSet(optionals, 1);
            if (struct.isSetReq()) {
               struct.req.write(oprot);
            }

         }

         public void read(TProtocol prot, DownloadData_args struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol)prot;
            BitSet incoming = iprot.readBitSet(1);
            if (incoming.get(0)) {
               struct.req = new TDownloadDataReq();
               struct.req.read(iprot);
               struct.setReqIsSet(true);
            }

         }
      }
   }

   @Public
   @Stable
   public static class DownloadData_result implements TBase, Serializable, Cloneable, Comparable {
      private static final TStruct STRUCT_DESC = new TStruct("DownloadData_result");
      private static final TField SUCCESS_FIELD_DESC = new TField("success", (byte)12, (short)0);
      private static final SchemeFactory STANDARD_SCHEME_FACTORY = new DownloadData_resultStandardSchemeFactory();
      private static final SchemeFactory TUPLE_SCHEME_FACTORY = new DownloadData_resultTupleSchemeFactory();
      @Nullable
      private TDownloadDataResp success;
      public static final Map metaDataMap;

      public DownloadData_result() {
      }

      public DownloadData_result(TDownloadDataResp success) {
         this();
         this.success = success;
      }

      public DownloadData_result(DownloadData_result other) {
         if (other.isSetSuccess()) {
            this.success = new TDownloadDataResp(other.success);
         }

      }

      public DownloadData_result deepCopy() {
         return new DownloadData_result(this);
      }

      public void clear() {
         this.success = null;
      }

      @Nullable
      public TDownloadDataResp getSuccess() {
         return this.success;
      }

      public void setSuccess(@Nullable TDownloadDataResp success) {
         this.success = success;
      }

      public void unsetSuccess() {
         this.success = null;
      }

      public boolean isSetSuccess() {
         return this.success != null;
      }

      public void setSuccessIsSet(boolean value) {
         if (!value) {
            this.success = null;
         }

      }

      public void setFieldValue(_Fields field, @Nullable Object value) {
         switch (field) {
            case SUCCESS:
               if (value == null) {
                  this.unsetSuccess();
               } else {
                  this.setSuccess((TDownloadDataResp)value);
               }
            default:
         }
      }

      @Nullable
      public Object getFieldValue(_Fields field) {
         switch (field) {
            case SUCCESS:
               return this.getSuccess();
            default:
               throw new IllegalStateException();
         }
      }

      public boolean isSet(_Fields field) {
         if (field == null) {
            throw new IllegalArgumentException();
         } else {
            switch (field) {
               case SUCCESS:
                  return this.isSetSuccess();
               default:
                  throw new IllegalStateException();
            }
         }
      }

      public boolean equals(Object that) {
         return that instanceof DownloadData_result ? this.equals((DownloadData_result)that) : false;
      }

      public boolean equals(DownloadData_result that) {
         if (that == null) {
            return false;
         } else if (this == that) {
            return true;
         } else {
            boolean this_present_success = this.isSetSuccess();
            boolean that_present_success = that.isSetSuccess();
            if (this_present_success || that_present_success) {
               if (!this_present_success || !that_present_success) {
                  return false;
               }

               if (!this.success.equals(that.success)) {
                  return false;
               }
            }

            return true;
         }
      }

      public int hashCode() {
         int hashCode = 1;
         hashCode = hashCode * 8191 + (this.isSetSuccess() ? 131071 : 524287);
         if (this.isSetSuccess()) {
            hashCode = hashCode * 8191 + this.success.hashCode();
         }

         return hashCode;
      }

      public int compareTo(DownloadData_result other) {
         if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
         } else {
            int lastComparison = 0;
            lastComparison = Boolean.compare(this.isSetSuccess(), other.isSetSuccess());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetSuccess()) {
                  lastComparison = TBaseHelper.compareTo(this.success, other.success);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               return 0;
            }
         }
      }

      @Nullable
      public _Fields fieldForId(int fieldId) {
         return TCLIService.DownloadData_result._Fields.findByThriftId(fieldId);
      }

      public void read(TProtocol iprot) throws TException {
         scheme(iprot).read(iprot, this);
      }

      public void write(TProtocol oprot) throws TException {
         scheme(oprot).write(oprot, this);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("DownloadData_result(");
         boolean first = true;
         sb.append("success:");
         if (this.success == null) {
            sb.append("null");
         } else {
            sb.append(this.success);
         }

         first = false;
         sb.append(")");
         return sb.toString();
      }

      public void validate() throws TException {
         if (this.success != null) {
            this.success.validate();
         }

      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         try {
            this.write(new TCompactProtocol(new TIOStreamTransport(out)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         try {
            this.read(new TCompactProtocol(new TIOStreamTransport(in)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private static IScheme scheme(TProtocol proto) {
         return (StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
      }

      static {
         Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
         tmpMap.put(TCLIService.DownloadData_result._Fields.SUCCESS, new FieldMetaData("success", (byte)3, new StructMetaData((byte)12, TDownloadDataResp.class)));
         metaDataMap = Collections.unmodifiableMap(tmpMap);
         FieldMetaData.addStructMetaDataMap(DownloadData_result.class, metaDataMap);
      }

      public static enum _Fields implements TFieldIdEnum {
         SUCCESS((short)0, "success");

         private static final Map byName = new HashMap();
         private final short _thriftId;
         private final String _fieldName;

         @Nullable
         public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
               case 0:
                  return SUCCESS;
               default:
                  return null;
            }
         }

         public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) {
               throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            } else {
               return fields;
            }
         }

         @Nullable
         public static _Fields findByName(String name) {
            return (_Fields)byName.get(name);
         }

         private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
         }

         public short getThriftFieldId() {
            return this._thriftId;
         }

         public String getFieldName() {
            return this._fieldName;
         }

         static {
            for(_Fields field : EnumSet.allOf(_Fields.class)) {
               byName.put(field.getFieldName(), field);
            }

         }
      }

      private static class DownloadData_resultStandardSchemeFactory implements SchemeFactory {
         private DownloadData_resultStandardSchemeFactory() {
         }

         public DownloadData_resultStandardScheme getScheme() {
            return new DownloadData_resultStandardScheme();
         }
      }

      private static class DownloadData_resultStandardScheme extends StandardScheme {
         private DownloadData_resultStandardScheme() {
         }

         public void read(TProtocol iprot, DownloadData_result struct) throws TException {
            iprot.readStructBegin();

            while(true) {
               TField schemeField = iprot.readFieldBegin();
               if (schemeField.type == 0) {
                  iprot.readStructEnd();
                  struct.validate();
                  return;
               }

               switch (schemeField.id) {
                  case 0:
                     if (schemeField.type == 12) {
                        struct.success = new TDownloadDataResp();
                        struct.success.read(iprot);
                        struct.setSuccessIsSet(true);
                     } else {
                        TProtocolUtil.skip(iprot, schemeField.type);
                     }
                     break;
                  default:
                     TProtocolUtil.skip(iprot, schemeField.type);
               }

               iprot.readFieldEnd();
            }
         }

         public void write(TProtocol oprot, DownloadData_result struct) throws TException {
            struct.validate();
            oprot.writeStructBegin(TCLIService.DownloadData_result.STRUCT_DESC);
            if (struct.success != null) {
               oprot.writeFieldBegin(TCLIService.DownloadData_result.SUCCESS_FIELD_DESC);
               struct.success.write(oprot);
               oprot.writeFieldEnd();
            }

            oprot.writeFieldStop();
            oprot.writeStructEnd();
         }
      }

      private static class DownloadData_resultTupleSchemeFactory implements SchemeFactory {
         private DownloadData_resultTupleSchemeFactory() {
         }

         public DownloadData_resultTupleScheme getScheme() {
            return new DownloadData_resultTupleScheme();
         }
      }

      private static class DownloadData_resultTupleScheme extends TupleScheme {
         private DownloadData_resultTupleScheme() {
         }

         public void write(TProtocol prot, DownloadData_result struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol)prot;
            BitSet optionals = new BitSet();
            if (struct.isSetSuccess()) {
               optionals.set(0);
            }

            oprot.writeBitSet(optionals, 1);
            if (struct.isSetSuccess()) {
               struct.success.write(oprot);
            }

         }

         public void read(TProtocol prot, DownloadData_result struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol)prot;
            BitSet incoming = iprot.readBitSet(1);
            if (incoming.get(0)) {
               struct.success = new TDownloadDataResp();
               struct.success.read(iprot);
               struct.setSuccessIsSet(true);
            }

         }
      }
   }

   @Public
   @Stable
   public interface AsyncIface {
      void OpenSession(TOpenSessionReq var1, AsyncMethodCallback var2) throws TException;

      void CloseSession(TCloseSessionReq var1, AsyncMethodCallback var2) throws TException;

      void GetInfo(TGetInfoReq var1, AsyncMethodCallback var2) throws TException;

      void ExecuteStatement(TExecuteStatementReq var1, AsyncMethodCallback var2) throws TException;

      void GetTypeInfo(TGetTypeInfoReq var1, AsyncMethodCallback var2) throws TException;

      void GetCatalogs(TGetCatalogsReq var1, AsyncMethodCallback var2) throws TException;

      void GetSchemas(TGetSchemasReq var1, AsyncMethodCallback var2) throws TException;

      void GetTables(TGetTablesReq var1, AsyncMethodCallback var2) throws TException;

      void GetTableTypes(TGetTableTypesReq var1, AsyncMethodCallback var2) throws TException;

      void GetColumns(TGetColumnsReq var1, AsyncMethodCallback var2) throws TException;

      void GetFunctions(TGetFunctionsReq var1, AsyncMethodCallback var2) throws TException;

      void GetPrimaryKeys(TGetPrimaryKeysReq var1, AsyncMethodCallback var2) throws TException;

      void GetCrossReference(TGetCrossReferenceReq var1, AsyncMethodCallback var2) throws TException;

      void GetOperationStatus(TGetOperationStatusReq var1, AsyncMethodCallback var2) throws TException;

      void CancelOperation(TCancelOperationReq var1, AsyncMethodCallback var2) throws TException;

      void CloseOperation(TCloseOperationReq var1, AsyncMethodCallback var2) throws TException;

      void GetResultSetMetadata(TGetResultSetMetadataReq var1, AsyncMethodCallback var2) throws TException;

      void FetchResults(TFetchResultsReq var1, AsyncMethodCallback var2) throws TException;

      void GetDelegationToken(TGetDelegationTokenReq var1, AsyncMethodCallback var2) throws TException;

      void CancelDelegationToken(TCancelDelegationTokenReq var1, AsyncMethodCallback var2) throws TException;

      void RenewDelegationToken(TRenewDelegationTokenReq var1, AsyncMethodCallback var2) throws TException;

      void GetQueryId(TGetQueryIdReq var1, AsyncMethodCallback var2) throws TException;

      void SetClientInfo(TSetClientInfoReq var1, AsyncMethodCallback var2) throws TException;

      void UploadData(TUploadDataReq var1, AsyncMethodCallback var2) throws TException;

      void DownloadData(TDownloadDataReq var1, AsyncMethodCallback var2) throws TException;
   }

   @Public
   @Stable
   public interface Iface {
      TOpenSessionResp OpenSession(TOpenSessionReq var1) throws TException;

      TCloseSessionResp CloseSession(TCloseSessionReq var1) throws TException;

      TGetInfoResp GetInfo(TGetInfoReq var1) throws TException;

      TExecuteStatementResp ExecuteStatement(TExecuteStatementReq var1) throws TException;

      TGetTypeInfoResp GetTypeInfo(TGetTypeInfoReq var1) throws TException;

      TGetCatalogsResp GetCatalogs(TGetCatalogsReq var1) throws TException;

      TGetSchemasResp GetSchemas(TGetSchemasReq var1) throws TException;

      TGetTablesResp GetTables(TGetTablesReq var1) throws TException;

      TGetTableTypesResp GetTableTypes(TGetTableTypesReq var1) throws TException;

      TGetColumnsResp GetColumns(TGetColumnsReq var1) throws TException;

      TGetFunctionsResp GetFunctions(TGetFunctionsReq var1) throws TException;

      TGetPrimaryKeysResp GetPrimaryKeys(TGetPrimaryKeysReq var1) throws TException;

      TGetCrossReferenceResp GetCrossReference(TGetCrossReferenceReq var1) throws TException;

      TGetOperationStatusResp GetOperationStatus(TGetOperationStatusReq var1) throws TException;

      TCancelOperationResp CancelOperation(TCancelOperationReq var1) throws TException;

      TCloseOperationResp CloseOperation(TCloseOperationReq var1) throws TException;

      TGetResultSetMetadataResp GetResultSetMetadata(TGetResultSetMetadataReq var1) throws TException;

      TFetchResultsResp FetchResults(TFetchResultsReq var1) throws TException;

      TGetDelegationTokenResp GetDelegationToken(TGetDelegationTokenReq var1) throws TException;

      TCancelDelegationTokenResp CancelDelegationToken(TCancelDelegationTokenReq var1) throws TException;

      TRenewDelegationTokenResp RenewDelegationToken(TRenewDelegationTokenReq var1) throws TException;

      TGetQueryIdResp GetQueryId(TGetQueryIdReq var1) throws TException;

      TSetClientInfoResp SetClientInfo(TSetClientInfoReq var1) throws TException;

      TUploadDataResp UploadData(TUploadDataReq var1) throws TException;

      TDownloadDataResp DownloadData(TDownloadDataReq var1) throws TException;
   }
}
