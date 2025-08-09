package org.apache.hive.service.cli.operation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.apache.hadoop.hive.metastore.api.FieldSchema;
import org.apache.hadoop.hive.metastore.api.Schema;
import org.apache.hadoop.hive.ql.session.OperationLog;
import org.apache.hive.service.AbstractService;
import org.apache.hive.service.cli.FetchOrientation;
import org.apache.hive.service.cli.HiveSQLException;
import org.apache.hive.service.cli.OperationHandle;
import org.apache.hive.service.cli.OperationState;
import org.apache.hive.service.cli.OperationStatus;
import org.apache.hive.service.cli.RowSet;
import org.apache.hive.service.cli.RowSetFactory;
import org.apache.hive.service.cli.TableSchema;
import org.apache.hive.service.cli.session.HiveSession;
import org.apache.hive.service.rpc.thrift.TRowSet;
import org.apache.hive.service.rpc.thrift.TTableSchema;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Logger;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.SparkLogger;
import org.apache.spark.internal.SparkLoggerFactory;
import org.apache.spark.internal.LogKeys.OPERATION_HANDLE.;

public class OperationManager extends AbstractService {
   private static final SparkLogger LOG = SparkLoggerFactory.getLogger(OperationManager.class);
   private final Map handleToOperation = new HashMap();

   public OperationManager() {
      super(OperationManager.class.getSimpleName());
   }

   public synchronized void init(HiveConf hiveConf) {
      if (hiveConf.getBoolVar(ConfVars.HIVE_SERVER2_LOGGING_OPERATION_ENABLED)) {
         this.initOperationLogCapture(hiveConf.getVar(ConfVars.HIVE_SERVER2_LOGGING_OPERATION_LEVEL));
      } else {
         LOG.debug("Operation level logging is turned off");
      }

      super.init(hiveConf);
   }

   public synchronized void start() {
      super.start();
   }

   public synchronized void stop() {
      super.stop();
   }

   private void initOperationLogCapture(String loggingMode) {
      Appender ap = LogDivertAppender.create(this, OperationLog.getLoggingLevel(loggingMode));
      ((Logger)LogManager.getRootLogger()).addAppender(ap);
      ap.start();
   }

   public ExecuteStatementOperation newExecuteStatementOperation(HiveSession parentSession, String statement, Map confOverlay, boolean runAsync, long queryTimeout) throws HiveSQLException {
      throw new UnsupportedOperationException();
   }

   public GetTypeInfoOperation newGetTypeInfoOperation(HiveSession parentSession) {
      GetTypeInfoOperation operation = new GetTypeInfoOperation(parentSession);
      this.addOperation(operation);
      return operation;
   }

   public GetCatalogsOperation newGetCatalogsOperation(HiveSession parentSession) {
      GetCatalogsOperation operation = new GetCatalogsOperation(parentSession);
      this.addOperation(operation);
      return operation;
   }

   public GetSchemasOperation newGetSchemasOperation(HiveSession parentSession, String catalogName, String schemaName) {
      GetSchemasOperation operation = new GetSchemasOperation(parentSession, catalogName, schemaName);
      this.addOperation(operation);
      return operation;
   }

   public MetadataOperation newGetTablesOperation(HiveSession parentSession, String catalogName, String schemaName, String tableName, List tableTypes) {
      MetadataOperation operation = new GetTablesOperation(parentSession, catalogName, schemaName, tableName, tableTypes);
      this.addOperation(operation);
      return operation;
   }

   public GetTableTypesOperation newGetTableTypesOperation(HiveSession parentSession) {
      GetTableTypesOperation operation = new GetTableTypesOperation(parentSession);
      this.addOperation(operation);
      return operation;
   }

   public GetColumnsOperation newGetColumnsOperation(HiveSession parentSession, String catalogName, String schemaName, String tableName, String columnName) {
      GetColumnsOperation operation = new GetColumnsOperation(parentSession, catalogName, schemaName, tableName, columnName);
      this.addOperation(operation);
      return operation;
   }

   public GetFunctionsOperation newGetFunctionsOperation(HiveSession parentSession, String catalogName, String schemaName, String functionName) {
      GetFunctionsOperation operation = new GetFunctionsOperation(parentSession, catalogName, schemaName, functionName);
      this.addOperation(operation);
      return operation;
   }

   public GetPrimaryKeysOperation newGetPrimaryKeysOperation(HiveSession parentSession, String catalogName, String schemaName, String tableName) {
      GetPrimaryKeysOperation operation = new GetPrimaryKeysOperation(parentSession, catalogName, schemaName, tableName);
      this.addOperation(operation);
      return operation;
   }

   public GetCrossReferenceOperation newGetCrossReferenceOperation(HiveSession session, String primaryCatalog, String primarySchema, String primaryTable, String foreignCatalog, String foreignSchema, String foreignTable) {
      GetCrossReferenceOperation operation = new GetCrossReferenceOperation(session, primaryCatalog, primarySchema, primaryTable, foreignCatalog, foreignSchema, foreignTable);
      this.addOperation(operation);
      return operation;
   }

   public Operation getOperation(OperationHandle operationHandle) throws HiveSQLException {
      Operation operation = this.getOperationInternal(operationHandle);
      if (operation == null) {
         throw new HiveSQLException("Invalid OperationHandle: " + String.valueOf(operationHandle));
      } else {
         return operation;
      }
   }

   private synchronized Operation getOperationInternal(OperationHandle operationHandle) {
      return (Operation)this.handleToOperation.get(operationHandle);
   }

   private synchronized Operation removeTimedOutOperation(OperationHandle operationHandle) {
      Operation operation = (Operation)this.handleToOperation.get(operationHandle);
      if (operation != null && operation.isTimedOut(System.currentTimeMillis())) {
         this.handleToOperation.remove(operationHandle);
         return operation;
      } else {
         return null;
      }
   }

   private synchronized void addOperation(Operation operation) {
      this.handleToOperation.put(operation.getHandle(), operation);
   }

   private synchronized Operation removeOperation(OperationHandle opHandle) {
      return (Operation)this.handleToOperation.remove(opHandle);
   }

   public OperationStatus getOperationStatus(OperationHandle opHandle) throws HiveSQLException {
      return this.getOperation(opHandle).getStatus();
   }

   public void cancelOperation(OperationHandle opHandle) throws HiveSQLException {
      Operation operation = this.getOperation(opHandle);
      OperationState opState = operation.getStatus().getState();
      if (opState != OperationState.CANCELED && opState != OperationState.TIMEDOUT && opState != OperationState.CLOSED && opState != OperationState.FINISHED && opState != OperationState.ERROR && opState != OperationState.UNKNOWN) {
         SparkLogger var4 = LOG;
         String var5 = String.valueOf(opHandle);
         var4.debug(var5 + ": Attempting to cancel from state - " + String.valueOf(opState));
         operation.cancel();
      } else {
         SparkLogger var10000 = LOG;
         String var10001 = String.valueOf(opHandle);
         var10000.debug(var10001 + ": Operation is already aborted in state - " + String.valueOf(opState));
      }

   }

   public void closeOperation(OperationHandle opHandle) throws HiveSQLException {
      Operation operation = this.removeOperation(opHandle);
      if (operation == null) {
         throw new HiveSQLException("Operation does not exist!");
      } else {
         operation.close();
      }
   }

   public TTableSchema getOperationResultSetSchema(OperationHandle opHandle) throws HiveSQLException {
      return this.getOperation(opHandle).getResultSetSchema();
   }

   public TRowSet getOperationNextRowSet(OperationHandle opHandle, FetchOrientation orientation, long maxRows) throws HiveSQLException {
      return this.getOperation(opHandle).getNextRowSet(orientation, maxRows);
   }

   public TRowSet getOperationLogRowSet(OperationHandle opHandle, FetchOrientation orientation, long maxRows) throws HiveSQLException {
      OperationLog operationLog = this.getOperation(opHandle).getOperationLog();
      if (operationLog == null) {
         throw new HiveSQLException("Couldn't find log associated with operation handle: " + String.valueOf(opHandle));
      } else {
         List<String> logs;
         try {
            logs = operationLog.readOperationLog(this.isFetchFirst(orientation), maxRows);
         } catch (SQLException e) {
            throw new HiveSQLException(e.getMessage(), e.getCause());
         }

         TableSchema tableSchema = new TableSchema(this.getLogSchema());
         RowSet rowSet = RowSetFactory.create(tableSchema, this.getOperation(opHandle).getProtocolVersion(), false);

         for(String log : logs) {
            rowSet.addRow(new String[]{log});
         }

         return rowSet.toTRowSet();
      }
   }

   private boolean isFetchFirst(FetchOrientation fetchOrientation) {
      return fetchOrientation.equals(FetchOrientation.FETCH_FIRST);
   }

   private Schema getLogSchema() {
      Schema schema = new Schema();
      FieldSchema fieldSchema = new FieldSchema();
      fieldSchema.setName("operation_log");
      fieldSchema.setType("string");
      schema.addToFieldSchemas(fieldSchema);
      return schema;
   }

   public OperationLog getOperationLogByThread() {
      return OperationLog.getCurrentOperationLog();
   }

   public List removeExpiredOperations(OperationHandle[] handles) {
      List<Operation> removed = new ArrayList();

      for(OperationHandle handle : handles) {
         Operation operation = this.removeTimedOutOperation(handle);
         if (operation != null) {
            LOG.warn("Operation {} is timed-out and will be closed", new MDC[]{MDC.of(.MODULE$, handle)});
            removed.add(operation);
         }
      }

      return removed;
   }
}
