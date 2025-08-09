package org.apache.hive.service.cli.operation;

import java.util.List;
import org.apache.hadoop.hive.metastore.IMetaStoreClient;
import org.apache.hadoop.hive.metastore.api.ForeignKeysRequest;
import org.apache.hadoop.hive.metastore.api.SQLForeignKey;
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

public class GetCrossReferenceOperation extends MetadataOperation {
   private static final TableSchema RESULT_SET_SCHEMA;
   private final String parentCatalogName;
   private final String parentSchemaName;
   private final String parentTableName;
   private final String foreignCatalogName;
   private final String foreignSchemaName;
   private final String foreignTableName;
   private final RowSet rowSet;

   public GetCrossReferenceOperation(HiveSession parentSession, String parentCatalogName, String parentSchemaName, String parentTableName, String foreignCatalog, String foreignSchema, String foreignTable) {
      super(parentSession, OperationType.GET_FUNCTIONS);
      this.parentCatalogName = parentCatalogName;
      this.parentSchemaName = parentSchemaName;
      this.parentTableName = parentTableName;
      this.foreignCatalogName = foreignCatalog;
      this.foreignSchemaName = foreignSchema;
      this.foreignTableName = foreignTable;
      this.rowSet = RowSetFactory.create(RESULT_SET_SCHEMA, this.getProtocolVersion(), false);
   }

   public void runInternal() throws HiveSQLException {
      this.setState(OperationState.RUNNING);

      try {
         IMetaStoreClient metastoreClient = this.getParentSession().getMetaStoreClient();
         ForeignKeysRequest fkReq = new ForeignKeysRequest(this.parentSchemaName, this.parentTableName, this.foreignSchemaName, this.foreignTableName);
         List<SQLForeignKey> fks = metastoreClient.getForeignKeys(fkReq);
         if (fks != null) {
            for(SQLForeignKey fk : fks) {
               this.rowSet.addRow(new Object[]{this.parentCatalogName, fk.getPktable_db(), fk.getPktable_name(), fk.getPkcolumn_name(), this.foreignCatalogName, fk.getFktable_db(), fk.getFktable_name(), fk.getFkcolumn_name(), fk.getKey_seq(), fk.getUpdate_rule(), fk.getDelete_rule(), fk.getFk_name(), fk.getPk_name(), 0});
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
      RESULT_SET_SCHEMA = (new TableSchema()).addPrimitiveColumn("PKTABLE_CAT", Type.STRING_TYPE, "Parent key table catalog (may be null)").addPrimitiveColumn("PKTABLE_SCHEM", Type.STRING_TYPE, "Parent key table schema (may be null)").addPrimitiveColumn("PKTABLE_NAME", Type.STRING_TYPE, "Parent Key table name").addPrimitiveColumn("PKCOLUMN_NAME", Type.STRING_TYPE, "Parent Key column name").addPrimitiveColumn("FKTABLE_CAT", Type.STRING_TYPE, "Foreign key table catalog (may be null)").addPrimitiveColumn("FKTABLE_SCHEM", Type.STRING_TYPE, "Foreign key table schema (may be null)").addPrimitiveColumn("FKTABLE_NAME", Type.STRING_TYPE, "Foreign Key table name").addPrimitiveColumn("FKCOLUMN_NAME", Type.STRING_TYPE, "Foreign Key column name").addPrimitiveColumn("KEQ_SEQ", Type.INT_TYPE, "Sequence number within primary key").addPrimitiveColumn("UPDATE_RULE", Type.INT_TYPE, "What happens to foreign key when parent key is updated").addPrimitiveColumn("DELETE_RULE", Type.INT_TYPE, "What happens to foreign key when parent key is deleted").addPrimitiveColumn("FK_NAME", Type.STRING_TYPE, "Foreign key name (may be null)").addPrimitiveColumn("PK_NAME", Type.STRING_TYPE, "Primary key name (may be null)").addPrimitiveColumn("DEFERRABILITY", Type.INT_TYPE, "Can the evaluation of foreign key constraints be deferred until commit");
   }
}
