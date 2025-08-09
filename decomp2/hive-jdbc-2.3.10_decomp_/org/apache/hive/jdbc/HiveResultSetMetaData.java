package org.apache.hive.jdbc;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.List;
import org.apache.hadoop.hive.serde2.thrift.Type;

public class HiveResultSetMetaData implements ResultSetMetaData {
   private final List columnNames;
   private final List columnTypes;
   private final List columnAttributes;

   public HiveResultSetMetaData(List columnNames, List columnTypes, List columnAttributes) {
      this.columnNames = columnNames;
      this.columnTypes = columnTypes;
      this.columnAttributes = columnAttributes;
   }

   public String getCatalogName(int column) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   private Type getHiveType(int column) throws SQLException {
      return JdbcColumn.typeStringToHiveType((String)this.columnTypes.get(this.toZeroIndex(column)));
   }

   public String getColumnClassName(int column) throws SQLException {
      return JdbcColumn.columnClassName(this.getHiveType(column), (JdbcColumnAttributes)this.columnAttributes.get(this.toZeroIndex(column)));
   }

   public int getColumnCount() throws SQLException {
      return this.columnNames.size();
   }

   public int getColumnDisplaySize(int column) throws SQLException {
      return JdbcColumn.columnDisplaySize(this.getHiveType(column), (JdbcColumnAttributes)this.columnAttributes.get(this.toZeroIndex(column)));
   }

   public String getColumnLabel(int column) throws SQLException {
      return (String)this.columnNames.get(this.toZeroIndex(column));
   }

   public String getColumnName(int column) throws SQLException {
      return (String)this.columnNames.get(this.toZeroIndex(column));
   }

   public int getColumnType(int column) throws SQLException {
      String type = (String)this.columnTypes.get(this.toZeroIndex(column));
      return JdbcColumn.hiveTypeToSqlType(type);
   }

   public String getColumnTypeName(int column) throws SQLException {
      return JdbcColumn.getColumnTypeName((String)this.columnTypes.get(this.toZeroIndex(column)));
   }

   public int getPrecision(int column) throws SQLException {
      return JdbcColumn.columnPrecision(this.getHiveType(column), (JdbcColumnAttributes)this.columnAttributes.get(this.toZeroIndex(column)));
   }

   public int getScale(int column) throws SQLException {
      return JdbcColumn.columnScale(this.getHiveType(column), (JdbcColumnAttributes)this.columnAttributes.get(this.toZeroIndex(column)));
   }

   public String getSchemaName(int column) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public String getTableName(int column) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean isAutoIncrement(int column) throws SQLException {
      return false;
   }

   public boolean isCaseSensitive(int column) throws SQLException {
      String type = (String)this.columnTypes.get(this.toZeroIndex(column));
      return "string".equalsIgnoreCase(type);
   }

   public boolean isCurrency(int column) throws SQLException {
      return false;
   }

   public boolean isDefinitelyWritable(int column) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public int isNullable(int column) throws SQLException {
      return 1;
   }

   public boolean isReadOnly(int column) throws SQLException {
      return true;
   }

   public boolean isSearchable(int column) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean isSigned(int column) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean isWritable(int column) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean isWrapperFor(Class iface) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public Object unwrap(Class iface) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   protected int toZeroIndex(int column) throws SQLException {
      if (this.columnTypes == null) {
         throw new SQLException("Could not determine column type name for ResultSet");
      } else if (column >= 1 && column <= this.columnTypes.size()) {
         return column - 1;
      } else {
         throw new SQLException("Invalid column value: " + column);
      }
   }
}
