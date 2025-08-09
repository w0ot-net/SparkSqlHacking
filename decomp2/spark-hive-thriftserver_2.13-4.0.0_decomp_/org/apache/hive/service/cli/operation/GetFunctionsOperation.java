package org.apache.hive.service.cli.operation;

import java.util.List;
import org.apache.hadoop.hive.metastore.IMetaStoreClient;
import org.apache.hadoop.hive.ql.exec.FunctionInfo;
import org.apache.hadoop.hive.ql.exec.FunctionRegistry;
import org.apache.hadoop.hive.ql.security.authorization.plugin.HiveOperationType;
import org.apache.hadoop.hive.ql.security.authorization.plugin.HivePrivilegeObject;
import org.apache.hadoop.hive.ql.security.authorization.plugin.HivePrivilegeObjectUtils;
import org.apache.hadoop.hive.serde2.thrift.Type;
import org.apache.hive.service.cli.CLIServiceUtils;
import org.apache.hive.service.cli.FetchOrientation;
import org.apache.hive.service.cli.HiveSQLException;
import org.apache.hive.service.cli.OperationState;
import org.apache.hive.service.cli.OperationType;
import org.apache.hive.service.cli.RowSet;
import org.apache.hive.service.cli.RowSetFactory;
import org.apache.hive.service.cli.TableSchema;
import org.apache.hive.service.cli.session.HiveSession;
import org.apache.hive.service.rpc.thrift.TRowSet;
import org.apache.hive.service.rpc.thrift.TTableSchema;
import org.apache.thrift.TException;

public class GetFunctionsOperation extends MetadataOperation {
   private static final TableSchema RESULT_SET_SCHEMA;
   private final String catalogName;
   private final String schemaName;
   private final String functionName;
   protected final RowSet rowSet;

   public GetFunctionsOperation(HiveSession parentSession, String catalogName, String schemaName, String functionName) {
      super(parentSession, OperationType.GET_FUNCTIONS);
      this.catalogName = catalogName;
      this.schemaName = schemaName;
      this.functionName = functionName;
      this.rowSet = RowSetFactory.create(RESULT_SET_SCHEMA, this.getProtocolVersion(), false);
   }

   public void runInternal() throws HiveSQLException {
      this.setState(OperationState.RUNNING);
      if (this.isAuthV2Enabled()) {
         IMetaStoreClient metastoreClient = this.getParentSession().getMetaStoreClient();
         String schemaPattern = this.convertSchemaPattern(this.schemaName);

         List<String> matchingDbs;
         try {
            matchingDbs = metastoreClient.getDatabases(schemaPattern);
         } catch (TException e) {
            this.setState(OperationState.ERROR);
            throw new HiveSQLException(e);
         }

         List<HivePrivilegeObject> privObjs = HivePrivilegeObjectUtils.getHivePrivDbObjects(matchingDbs);
         String cmdStr = "catalog : " + this.catalogName + ", schemaPattern : " + this.schemaName;
         this.authorizeMetaGets(HiveOperationType.GET_FUNCTIONS, privObjs, cmdStr);
      }

      try {
         if ((null == this.catalogName || "".equals(this.catalogName)) && (null == this.schemaName || "".equals(this.schemaName))) {
            for(String functionName : FunctionRegistry.getFunctionNames(CLIServiceUtils.patternToRegex(this.functionName))) {
               FunctionInfo functionInfo = FunctionRegistry.getFunctionInfo(functionName);
               Object[] rowData = new Object[]{null, null, functionInfo.getDisplayName(), "", functionInfo.isGenericUDTF() ? 2 : 1, functionInfo.getClass().getCanonicalName()};
               this.rowSet.addRow(rowData);
            }
         }

         this.setState(OperationState.FINISHED);
      } catch (Exception e) {
         this.setState(OperationState.ERROR);
         throw new HiveSQLException(e);
      }
   }

   public TTableSchema getResultSetSchema() throws HiveSQLException {
      this.assertState(OperationState.FINISHED);
      return RESULT_SET_SCHEMA.toTTableSchema();
   }

   public TRowSet getNextRowSet(FetchOrientation orientation, long maxRows) throws HiveSQLException {
      this.assertState(OperationState.FINISHED);
      this.validateDefaultFetchOrientation(orientation);
      if (orientation.equals(FetchOrientation.FETCH_FIRST)) {
         this.rowSet.setStartOffset(0L);
      }

      return this.rowSet.extractSubset((int)maxRows).toTRowSet();
   }

   static {
      RESULT_SET_SCHEMA = (new TableSchema()).addPrimitiveColumn("FUNCTION_CAT", Type.STRING_TYPE, "Function catalog (may be null)").addPrimitiveColumn("FUNCTION_SCHEM", Type.STRING_TYPE, "Function schema (may be null)").addPrimitiveColumn("FUNCTION_NAME", Type.STRING_TYPE, "Function name. This is the name used to invoke the function").addPrimitiveColumn("REMARKS", Type.STRING_TYPE, "Explanatory comment on the function").addPrimitiveColumn("FUNCTION_TYPE", Type.INT_TYPE, "Kind of function.").addPrimitiveColumn("SPECIFIC_NAME", Type.STRING_TYPE, "The name which uniquely identifies this function within its schema");
   }
}
