package org.apache.derby.impl.jdbc;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import org.apache.derby.iapi.sql.ResultColumnDescriptor;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.DataTypeUtilities;
import org.apache.derby.impl.sql.GenericColumnDescriptor;
import org.apache.derby.shared.common.util.ArrayUtil;

public class EmbedResultSetMetaData implements ResultSetMetaData {
   private final ResultColumnDescriptor[] columnInfo;

   public EmbedResultSetMetaData(ResultColumnDescriptor[] var1) {
      this.columnInfo = (ResultColumnDescriptor[])ArrayUtil.copy(var1);
   }

   public final int getColumnCount() {
      return this.columnInfo.length;
   }

   public final boolean isAutoIncrement(int var1) throws SQLException {
      this.validColumnNumber(var1);
      ResultColumnDescriptor var2 = this.columnInfo[var1 - 1];
      return var2.isAutoincrement();
   }

   public final boolean isCaseSensitive(int var1) throws SQLException {
      return DataTypeUtilities.isCaseSensitive(this.getColumnTypeDescriptor(var1));
   }

   public final boolean isSearchable(int var1) throws SQLException {
      this.validColumnNumber(var1);
      return true;
   }

   public final boolean isCurrency(int var1) throws SQLException {
      return false;
   }

   public final int isNullable(int var1) throws SQLException {
      return DataTypeUtilities.isNullable(this.getColumnTypeDescriptor(var1));
   }

   public final boolean isSigned(int var1) throws SQLException {
      return DataTypeUtilities.isSigned(this.getColumnTypeDescriptor(var1));
   }

   public final int getColumnDisplaySize(int var1) throws SQLException {
      return DataTypeUtilities.getColumnDisplaySize(this.getColumnTypeDescriptor(var1));
   }

   public final String getColumnLabel(int var1) throws SQLException {
      ResultColumnDescriptor var2 = this.columnInfo[var1 - 1];
      String var3 = var2.getName();
      return var3 == null ? "Column" + Integer.toString(var1) : var3;
   }

   public final String getColumnName(int var1) throws SQLException {
      ResultColumnDescriptor var2 = this.columnInfo[var1 - 1];
      String var3 = var2.getName();
      return var3 == null ? "" : var3;
   }

   public final String getSchemaName(int var1) throws SQLException {
      ResultColumnDescriptor var2 = this.columnInfo[var1 - 1];
      String var3 = var2.getSourceSchemaName();
      return var3 == null ? "" : var3;
   }

   public final int getPrecision(int var1) throws SQLException {
      return DataTypeUtilities.getDigitPrecision(this.getColumnTypeDescriptor(var1));
   }

   public final int getScale(int var1) throws SQLException {
      DataTypeDescriptor var2 = this.getColumnTypeDescriptor(var1);
      return var2.getScale();
   }

   public final String getTableName(int var1) throws SQLException {
      ResultColumnDescriptor var2 = this.columnInfo[var1 - 1];
      String var3 = var2.getSourceTableName();
      return var3 == null ? "" : var3;
   }

   public final String getCatalogName(int var1) throws SQLException {
      this.validColumnNumber(var1);
      return "";
   }

   public final int getColumnType(int var1) throws SQLException {
      DataTypeDescriptor var2 = this.getColumnTypeDescriptor(var1);
      return var2.getTypeId().getJDBCTypeId();
   }

   public final String getColumnTypeName(int var1) throws SQLException {
      DataTypeDescriptor var2 = this.getColumnTypeDescriptor(var1);
      return var2.getTypeId().getSQLTypeName();
   }

   public final boolean isReadOnly(int var1) throws SQLException {
      this.validColumnNumber(var1);
      return false;
   }

   public final boolean isWritable(int var1) throws SQLException {
      this.validColumnNumber(var1);
      return this.columnInfo[var1 - 1].updatableByCursor();
   }

   public final boolean isDefinitelyWritable(int var1) throws SQLException {
      this.validColumnNumber(var1);
      return false;
   }

   private void validColumnNumber(int var1) throws SQLException {
      if (var1 < 1 || var1 > this.getColumnCount()) {
         throw Util.generateCsSQLException("S0022", var1);
      }
   }

   private DataTypeDescriptor getColumnTypeDescriptor(int var1) throws SQLException {
      this.validColumnNumber(var1);
      ResultColumnDescriptor var2 = this.columnInfo[var1 - 1];
      return var2.getType();
   }

   public final String getColumnClassName(int var1) throws SQLException {
      return this.getColumnTypeDescriptor(var1).getTypeId().getResultSetMetaDataTypeName();
   }

   public static ResultColumnDescriptor getResultColumnDescriptor(String var0, int var1, boolean var2) {
      return new GenericColumnDescriptor(var0, DataTypeDescriptor.getBuiltInDataTypeDescriptor(var1, var2));
   }

   public static ResultColumnDescriptor getResultColumnDescriptor(String var0, int var1, boolean var2, int var3) {
      return new GenericColumnDescriptor(var0, DataTypeDescriptor.getBuiltInDataTypeDescriptor(var1, var2, var3));
   }

   public static ResultColumnDescriptor getResultColumnDescriptor(String var0, DataTypeDescriptor var1) {
      return new GenericColumnDescriptor(var0, var1);
   }

   public final boolean isWrapperFor(Class var1) {
      return var1.isInstance(this);
   }

   public final Object unwrap(Class var1) throws SQLException {
      try {
         return var1.cast(this);
      } catch (ClassCastException var3) {
         throw Util.generateCsSQLException("XJ128.S", var1);
      }
   }
}
