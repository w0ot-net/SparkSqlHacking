package org.apache.hive.service.cli.operation;

import java.util.List;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.apache.hadoop.hive.metastore.TableType;
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

public class GetTableTypesOperation extends MetadataOperation {
   protected static TableSchema RESULT_SET_SCHEMA = (new TableSchema()).addStringColumn("TABLE_TYPE", "Table type name.");
   protected final RowSet rowSet;
   private final TableTypeMapping tableTypeMapping;

   protected GetTableTypesOperation(HiveSession parentSession) {
      super(parentSession, OperationType.GET_TABLE_TYPES);
      String tableMappingStr = this.getParentSession().getHiveConf().getVar(ConfVars.HIVE_SERVER2_TABLE_TYPE_MAPPING);
      this.tableTypeMapping = TableTypeMappingFactory.getTableTypeMapping(tableMappingStr);
      this.rowSet = RowSetFactory.create(RESULT_SET_SCHEMA, this.getProtocolVersion(), false);
   }

   public void runInternal() throws HiveSQLException {
      this.setState(OperationState.RUNNING);
      if (this.isAuthV2Enabled()) {
         this.authorizeMetaGets(HiveOperationType.GET_TABLETYPES, (List)null);
      }

      try {
         for(TableType type : TableType.values()) {
            this.rowSet.addRow(new String[]{this.tableTypeMapping.mapToClientType(type.toString())});
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
