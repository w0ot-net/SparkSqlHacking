package org.datanucleus.store.rdbms.adapter;

import java.sql.DatabaseMetaData;
import org.datanucleus.store.connection.ManagedConnection;
import org.datanucleus.store.rdbms.schema.JDBCTypeInfo;
import org.datanucleus.store.rdbms.schema.RDBMSTypesInfo;
import org.datanucleus.store.rdbms.schema.SQLTypeInfo;
import org.datanucleus.store.schema.StoreSchemaHandler;

public class PointbaseAdapter extends BaseDatastoreAdapter {
   public PointbaseAdapter(DatabaseMetaData metadata) {
      super(metadata);
      this.supportedOptions.remove("BooleanExpression");
      this.supportedOptions.remove("DeferredConstraints");
   }

   public void initialiseTypes(StoreSchemaHandler handler, ManagedConnection mconn) {
      super.initialiseTypes(handler, mconn);
      RDBMSTypesInfo typesInfo = (RDBMSTypesInfo)handler.getSchemaData(mconn.getConnection(), "types", (Object[])null);
      JDBCTypeInfo jdbcType = (JDBCTypeInfo)typesInfo.getChild("9");
      if (jdbcType != null && jdbcType.getNumberOfChildren() > 0) {
         SQLTypeInfo dfltTypeInfo = (SQLTypeInfo)jdbcType.getChild("DEFAULT");
         SQLTypeInfo sqlType = new SQLTypeInfo(dfltTypeInfo.getTypeName(), (short)-5, dfltTypeInfo.getPrecision(), dfltTypeInfo.getLiteralPrefix(), dfltTypeInfo.getLiteralSuffix(), dfltTypeInfo.getCreateParams(), dfltTypeInfo.getNullable(), dfltTypeInfo.isCaseSensitive(), dfltTypeInfo.getSearchable(), dfltTypeInfo.isUnsignedAttribute(), dfltTypeInfo.isFixedPrecScale(), dfltTypeInfo.isAutoIncrement(), dfltTypeInfo.getLocalTypeName(), dfltTypeInfo.getMinimumScale(), dfltTypeInfo.getMaximumScale(), dfltTypeInfo.getNumPrecRadix());
         this.addSQLTypeForJDBCType(handler, mconn, (short)-5, sqlType, true);
      }

      jdbcType = (JDBCTypeInfo)typesInfo.getChild("16");
      if (jdbcType != null) {
         SQLTypeInfo dfltTypeInfo = (SQLTypeInfo)jdbcType.getChild("DEFAULT");
         SQLTypeInfo sqlType = new SQLTypeInfo(dfltTypeInfo.getTypeName(), (short)16, dfltTypeInfo.getPrecision(), dfltTypeInfo.getLiteralPrefix(), dfltTypeInfo.getLiteralSuffix(), dfltTypeInfo.getCreateParams(), dfltTypeInfo.getNullable(), dfltTypeInfo.isCaseSensitive(), dfltTypeInfo.getSearchable(), dfltTypeInfo.isUnsignedAttribute(), dfltTypeInfo.isFixedPrecScale(), dfltTypeInfo.isAutoIncrement(), dfltTypeInfo.getLocalTypeName(), dfltTypeInfo.getMinimumScale(), dfltTypeInfo.getMaximumScale(), dfltTypeInfo.getNumPrecRadix());
         this.addSQLTypeForJDBCType(handler, mconn, (short)16, sqlType, true);
      }

   }

   public String getVendorID() {
      return "pointbase";
   }

   public int getUnlimitedLengthPrecisionValue(SQLTypeInfo typeInfo) {
      return typeInfo.getDataType() != 2004 && typeInfo.getDataType() != 2005 ? super.getUnlimitedLengthPrecisionValue(typeInfo) : Integer.MIN_VALUE;
   }
}
