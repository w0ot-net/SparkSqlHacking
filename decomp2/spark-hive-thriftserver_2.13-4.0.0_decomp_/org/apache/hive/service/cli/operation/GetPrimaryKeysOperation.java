package org.apache.hive.service.cli.operation;

import java.util.List;
import org.apache.hadoop.hive.metastore.IMetaStoreClient;
import org.apache.hadoop.hive.metastore.api.PrimaryKeysRequest;
import org.apache.hadoop.hive.metastore.api.SQLPrimaryKey;
import org.apache.hadoop.hive.serde2.thrift.Type;
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

public class GetPrimaryKeysOperation extends MetadataOperation {
   private static final TableSchema RESULT_SET_SCHEMA;
   private final String catalogName;
   private final String schemaName;
   private final String tableName;
   private final RowSet rowSet;

   public GetPrimaryKeysOperation(HiveSession parentSession, String catalogName, String schemaName, String tableName) {
      super(parentSession, OperationType.GET_FUNCTIONS);
      this.catalogName = catalogName;
      this.schemaName = schemaName;
      this.tableName = tableName;
      this.rowSet = RowSetFactory.create(RESULT_SET_SCHEMA, this.getProtocolVersion(), false);
   }

   public void runInternal() throws HiveSQLException {
      this.setState(OperationState.RUNNING);

      try {
         IMetaStoreClient metastoreClient = this.getParentSession().getMetaStoreClient();
         PrimaryKeysRequest sqlReq = new PrimaryKeysRequest(this.schemaName, this.tableName);
         List<SQLPrimaryKey> pks = metastoreClient.getPrimaryKeys(sqlReq);
         if (pks != null) {
            for(SQLPrimaryKey pk : pks) {
               this.rowSet.addRow(new Object[]{this.catalogName, pk.getTable_db(), pk.getTable_name(), pk.getColumn_name(), pk.getKey_seq(), pk.getPk_name()});
            }

            this.setState(OperationState.FINISHED);
         }
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
      RESULT_SET_SCHEMA = (new TableSchema()).addPrimitiveColumn("TABLE_CAT", Type.STRING_TYPE, "Table catalog (may be null)").addPrimitiveColumn("TABLE_SCHEM", Type.STRING_TYPE, "Table schema (may be null)").addPrimitiveColumn("TABLE_NAME", Type.STRING_TYPE, "Table name").addPrimitiveColumn("COLUMN_NAME", Type.STRING_TYPE, "Column name").addPrimitiveColumn("KEQ_SEQ", Type.INT_TYPE, "Sequence number within primary key").addPrimitiveColumn("PK_NAME", Type.STRING_TYPE, "Primary key name (may be null)");
   }
}
