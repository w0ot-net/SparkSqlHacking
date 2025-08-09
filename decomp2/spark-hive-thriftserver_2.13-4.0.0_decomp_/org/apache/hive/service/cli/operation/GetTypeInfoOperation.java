package org.apache.hive.service.cli.operation;

import java.util.List;
import org.apache.hadoop.hive.ql.security.authorization.plugin.HiveOperationType;
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

public class GetTypeInfoOperation extends MetadataOperation {
   private static final TableSchema RESULT_SET_SCHEMA;
   protected final RowSet rowSet;

   protected GetTypeInfoOperation(HiveSession parentSession) {
      super(parentSession, OperationType.GET_TYPE_INFO);
      this.rowSet = RowSetFactory.create(RESULT_SET_SCHEMA, this.getProtocolVersion(), false);
   }

   public void runInternal() throws HiveSQLException {
      this.setState(OperationState.RUNNING);
      if (this.isAuthV2Enabled()) {
         this.authorizeMetaGets(HiveOperationType.GET_TYPEINFO, (List)null);
      }

      try {
         for(Type type : Type.values()) {
            Object[] rowData = new Object[]{type.getName(), type.toJavaSQLType(), type.getMaxPrecision(), type.getLiteralPrefix(), type.getLiteralSuffix(), type.getCreateParams(), type.getNullable(), type.isCaseSensitive(), type.getSearchable(), type.isUnsignedAttribute(), type.isFixedPrecScale(), type.isAutoIncrement(), type.getLocalizedName(), type.getMinimumScale(), type.getMaximumScale(), null, null, type.getNumPrecRadix()};
            this.rowSet.addRow(rowData);
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
      RESULT_SET_SCHEMA = (new TableSchema()).addPrimitiveColumn("TYPE_NAME", Type.STRING_TYPE, "Type name").addPrimitiveColumn("DATA_TYPE", Type.INT_TYPE, "SQL data type from java.sql.Types").addPrimitiveColumn("PRECISION", Type.INT_TYPE, "Maximum precision").addPrimitiveColumn("LITERAL_PREFIX", Type.STRING_TYPE, "Prefix used to quote a literal (may be null)").addPrimitiveColumn("LITERAL_SUFFIX", Type.STRING_TYPE, "Suffix used to quote a literal (may be null)").addPrimitiveColumn("CREATE_PARAMS", Type.STRING_TYPE, "Parameters used in creating the type (may be null)").addPrimitiveColumn("NULLABLE", Type.SMALLINT_TYPE, "Can you use NULL for this type").addPrimitiveColumn("CASE_SENSITIVE", Type.BOOLEAN_TYPE, "Is it case sensitive").addPrimitiveColumn("SEARCHABLE", Type.SMALLINT_TYPE, "Can you use \"WHERE\" based on this type").addPrimitiveColumn("UNSIGNED_ATTRIBUTE", Type.BOOLEAN_TYPE, "Is it unsigned").addPrimitiveColumn("FIXED_PREC_SCALE", Type.BOOLEAN_TYPE, "Can it be a money value").addPrimitiveColumn("AUTO_INCREMENT", Type.BOOLEAN_TYPE, "Can it be used for an auto-increment value").addPrimitiveColumn("LOCAL_TYPE_NAME", Type.STRING_TYPE, "Localized version of type name (may be null)").addPrimitiveColumn("MINIMUM_SCALE", Type.SMALLINT_TYPE, "Minimum scale supported").addPrimitiveColumn("MAXIMUM_SCALE", Type.SMALLINT_TYPE, "Maximum scale supported").addPrimitiveColumn("SQL_DATA_TYPE", Type.INT_TYPE, "Unused").addPrimitiveColumn("SQL_DATETIME_SUB", Type.INT_TYPE, "Unused").addPrimitiveColumn("NUM_PREC_RADIX", Type.INT_TYPE, "Usually 2 or 10");
   }
}
