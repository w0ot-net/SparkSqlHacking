package org.apache.hive.service.cli.operation;

import java.util.List;
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

public class GetCatalogsOperation extends MetadataOperation {
   private static final TableSchema RESULT_SET_SCHEMA = (new TableSchema()).addStringColumn("TABLE_CAT", "Catalog name. NULL if not applicable.");
   protected final RowSet rowSet;

   protected GetCatalogsOperation(HiveSession parentSession) {
      super(parentSession, OperationType.GET_CATALOGS);
      this.rowSet = RowSetFactory.create(RESULT_SET_SCHEMA, this.getProtocolVersion(), false);
   }

   public void runInternal() throws HiveSQLException {
      this.setState(OperationState.RUNNING);

      try {
         if (this.isAuthV2Enabled()) {
            this.authorizeMetaGets(HiveOperationType.GET_CATALOGS, (List)null);
         }

         this.setState(OperationState.FINISHED);
      } catch (HiveSQLException e) {
         this.setState(OperationState.ERROR);
         throw e;
      }
   }

   public TTableSchema getResultSetSchema() throws HiveSQLException {
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
