package org.apache.derby.vti;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;

public abstract class VTIMetaDataTemplate implements ResultSetMetaData {
   public boolean isWrapperFor(Class var1) throws SQLException {
      throw this.notImplemented("isWrapperFor");
   }

   public Object unwrap(Class var1) throws SQLException {
      throw this.notImplemented("unwrap");
   }

   public boolean isAutoIncrement(int var1) throws SQLException {
      throw new SQLException("isAutoIncrement");
   }

   public boolean isCaseSensitive(int var1) throws SQLException {
      throw new SQLException("isCaseSensitive");
   }

   public boolean isSearchable(int var1) throws SQLException {
      throw new SQLException("isSearchable");
   }

   public boolean isCurrency(int var1) throws SQLException {
      throw new SQLException("isCurrency");
   }

   public int isNullable(int var1) throws SQLException {
      throw new SQLException("isNullable");
   }

   public boolean isSigned(int var1) throws SQLException {
      throw new SQLException("isSigned");
   }

   public int getColumnDisplaySize(int var1) throws SQLException {
      throw new SQLException("getColumnDisplaySize");
   }

   public String getColumnLabel(int var1) throws SQLException {
      throw new SQLException("getColumnLabel");
   }

   public String getColumnName(int var1) throws SQLException {
      throw new SQLException("getColumnName");
   }

   public String getSchemaName(int var1) throws SQLException {
      throw new SQLException("getSchemaName");
   }

   public int getPrecision(int var1) throws SQLException {
      throw new SQLException("getPrecision");
   }

   public int getScale(int var1) throws SQLException {
      throw new SQLException("getScale");
   }

   public String getTableName(int var1) throws SQLException {
      throw new SQLException("getTableName");
   }

   public String getCatalogName(int var1) throws SQLException {
      throw new SQLException("getCatalogName");
   }

   public String getColumnTypeName(int var1) throws SQLException {
      throw new SQLException("getColumnTypeName");
   }

   public boolean isReadOnly(int var1) throws SQLException {
      return true;
   }

   public boolean isWritable(int var1) throws SQLException {
      return false;
   }

   public boolean isDefinitelyWritable(int var1) throws SQLException {
      return false;
   }

   public String getColumnClassName(int var1) throws SQLException {
      throw new SQLException("getColumnClassName");
   }

   protected SQLException notImplemented(String var1) {
      return new SQLFeatureNotSupportedException("Unimplemented method: " + var1);
   }
}
