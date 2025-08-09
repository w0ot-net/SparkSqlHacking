package org.apache.hive.service.cli.operation;

import java.util.List;
import org.apache.hadoop.hive.metastore.IMetaStoreClient;
import org.apache.hadoop.hive.ql.security.authorization.plugin.HiveOperationType;
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

public class GetSchemasOperation extends MetadataOperation {
   private final String catalogName;
   private final String schemaName;
   private static final TableSchema RESULT_SET_SCHEMA = (new TableSchema()).addStringColumn("TABLE_SCHEM", "Schema name.").addStringColumn("TABLE_CATALOG", "Catalog name.");
   protected RowSet rowSet;

   protected GetSchemasOperation(HiveSession parentSession, String catalogName, String schemaName) {
      super(parentSession, OperationType.GET_SCHEMAS);
      this.catalogName = catalogName;
      this.schemaName = schemaName;
      this.rowSet = RowSetFactory.create(RESULT_SET_SCHEMA, this.getProtocolVersion(), false);
   }

   public void runInternal() throws HiveSQLException {
      this.setState(OperationState.RUNNING);
      if (this.isAuthV2Enabled()) {
         String cmdStr = "catalog : " + this.catalogName + ", schemaPattern : " + this.schemaName;
         this.authorizeMetaGets(HiveOperationType.GET_SCHEMAS, (List)null, cmdStr);
      }

      try {
         IMetaStoreClient metastoreClient = this.getParentSession().getMetaStoreClient();
         String schemaPattern = this.convertSchemaPattern(this.schemaName);

         for(String dbName : metastoreClient.getDatabases(schemaPattern)) {
            this.rowSet.addRow(new Object[]{dbName, ""});
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
}
