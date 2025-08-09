package org.apache.hive.jdbc;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import org.apache.hadoop.hive.common.type.HiveIntervalDayTime;
import org.apache.hadoop.hive.common.type.HiveIntervalYearMonth;
import org.apache.hadoop.hive.serde2.thrift.Type;
import org.apache.hive.service.cli.TableSchema;

public abstract class HiveBaseResultSet implements ResultSet {
   protected Statement statement = null;
   protected SQLWarning warningChain = null;
   protected boolean wasNull = false;
   protected Object[] row;
   protected List columnNames;
   protected List normalizedColumnNames;
   protected List columnTypes;
   protected List columnAttributes;
   private TableSchema schema;

   public boolean absolute(int row) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void afterLast() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void beforeFirst() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void cancelRowUpdates() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void deleteRow() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public int findColumn(String columnName) throws SQLException {
      int columnIndex = 0;
      boolean findColumn = false;

      for(String normalizedColumnName : this.normalizedColumnNames) {
         ++columnIndex;
         String[] names = normalizedColumnName.split("\\.");
         String name = names[names.length - 1];
         if (name.equalsIgnoreCase(columnName) || normalizedColumnName.equalsIgnoreCase(columnName)) {
            findColumn = true;
            break;
         }
      }

      if (!findColumn) {
         throw new SQLException("Could not find " + columnName + " in " + this.normalizedColumnNames);
      } else {
         return columnIndex;
      }
   }

   public boolean first() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public Array getArray(int i) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public Array getArray(String colName) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public InputStream getAsciiStream(int columnIndex) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public InputStream getAsciiStream(String columnName) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public BigDecimal getBigDecimal(int columnIndex) throws SQLException {
      Object val = this.getObject(columnIndex);
      if (val != null && !(val instanceof BigDecimal)) {
         throw new SQLException("Illegal conversion");
      } else {
         return (BigDecimal)val;
      }
   }

   public BigDecimal getBigDecimal(String columnName) throws SQLException {
      return this.getBigDecimal(this.findColumn(columnName));
   }

   public BigDecimal getBigDecimal(int columnIndex, int scale) throws SQLException {
      MathContext mc = new MathContext(scale);
      return this.getBigDecimal(columnIndex).round(mc);
   }

   public BigDecimal getBigDecimal(String columnName, int scale) throws SQLException {
      return this.getBigDecimal(this.findColumn(columnName), scale);
   }

   public InputStream getBinaryStream(int columnIndex) throws SQLException {
      Object obj = this.getObject(columnIndex);
      if (obj == null) {
         return null;
      } else if (obj instanceof InputStream) {
         return (InputStream)obj;
      } else if (obj instanceof byte[]) {
         byte[] byteArray = (byte[])obj;
         InputStream is = new ByteArrayInputStream(byteArray);
         return is;
      } else if (obj instanceof String) {
         String str = (String)obj;
         InputStream is = null;

         try {
            is = new ByteArrayInputStream(str.getBytes("UTF-8"));
            return is;
         } catch (UnsupportedEncodingException var6) {
            throw new SQLException("Illegal conversion to binary stream from column " + columnIndex + " - Unsupported encoding exception");
         }
      } else {
         throw new SQLException("Illegal conversion to binary stream from column " + columnIndex);
      }
   }

   public InputStream getBinaryStream(String columnName) throws SQLException {
      return this.getBinaryStream(this.findColumn(columnName));
   }

   public Blob getBlob(int i) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public Blob getBlob(String colName) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean getBoolean(int columnIndex) throws SQLException {
      Object obj = this.getObject(columnIndex);
      if (Boolean.class.isInstance(obj)) {
         return (Boolean)obj;
      } else if (obj == null) {
         return false;
      } else if (Number.class.isInstance(obj)) {
         return ((Number)obj).intValue() != 0;
      } else if (String.class.isInstance(obj)) {
         return !((String)obj).equals("0");
      } else {
         throw new SQLException("Cannot convert column " + columnIndex + " to boolean");
      }
   }

   public boolean getBoolean(String columnName) throws SQLException {
      return this.getBoolean(this.findColumn(columnName));
   }

   public byte getByte(int columnIndex) throws SQLException {
      Object obj = this.getObject(columnIndex);
      if (Number.class.isInstance(obj)) {
         return ((Number)obj).byteValue();
      } else if (obj == null) {
         return 0;
      } else {
         throw new SQLException("Cannot convert column " + columnIndex + " to byte");
      }
   }

   public byte getByte(String columnName) throws SQLException {
      return this.getByte(this.findColumn(columnName));
   }

   public byte[] getBytes(int columnIndex) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public byte[] getBytes(String columnName) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public Reader getCharacterStream(int columnIndex) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public Reader getCharacterStream(String columnName) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public Clob getClob(int i) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public Clob getClob(String colName) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public int getConcurrency() throws SQLException {
      return 1007;
   }

   public String getCursorName() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public Date getDate(int columnIndex) throws SQLException {
      Object obj = this.getObject(columnIndex);
      if (obj == null) {
         return null;
      } else if (obj instanceof Date) {
         return (Date)obj;
      } else {
         try {
            if (obj instanceof String) {
               return Date.valueOf((String)obj);
            }
         } catch (Exception e) {
            throw new SQLException("Cannot convert column " + columnIndex + " to date: " + e.toString(), e);
         }

         throw new SQLException("Cannot convert column " + columnIndex + " to date: Illegal conversion");
      }
   }

   public Date getDate(String columnName) throws SQLException {
      return this.getDate(this.findColumn(columnName));
   }

   public Date getDate(int columnIndex, Calendar cal) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public Date getDate(String columnName, Calendar cal) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public double getDouble(int columnIndex) throws SQLException {
      try {
         Object obj = this.getObject(columnIndex);
         if (Number.class.isInstance(obj)) {
            return ((Number)obj).doubleValue();
         } else if (obj == null) {
            return (double)0.0F;
         } else if (String.class.isInstance(obj)) {
            return Double.parseDouble((String)obj);
         } else {
            throw new Exception("Illegal conversion");
         }
      } catch (Exception e) {
         throw new SQLException("Cannot convert column " + columnIndex + " to double: " + e.toString(), e);
      }
   }

   public double getDouble(String columnName) throws SQLException {
      return this.getDouble(this.findColumn(columnName));
   }

   public int getFetchDirection() throws SQLException {
      return 1000;
   }

   public int getFetchSize() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public float getFloat(int columnIndex) throws SQLException {
      try {
         Object obj = this.getObject(columnIndex);
         if (Number.class.isInstance(obj)) {
            return ((Number)obj).floatValue();
         } else if (obj == null) {
            return 0.0F;
         } else if (String.class.isInstance(obj)) {
            return Float.parseFloat((String)obj);
         } else {
            throw new Exception("Illegal conversion");
         }
      } catch (Exception e) {
         throw new SQLException("Cannot convert column " + columnIndex + " to float: " + e.toString(), e);
      }
   }

   public float getFloat(String columnName) throws SQLException {
      return this.getFloat(this.findColumn(columnName));
   }

   public int getHoldability() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public int getInt(int columnIndex) throws SQLException {
      try {
         Object obj = this.getObject(columnIndex);
         if (Number.class.isInstance(obj)) {
            return ((Number)obj).intValue();
         } else if (obj == null) {
            return 0;
         } else if (String.class.isInstance(obj)) {
            return Integer.parseInt((String)obj);
         } else {
            throw new Exception("Illegal conversion");
         }
      } catch (Exception e) {
         throw new SQLException("Cannot convert column " + columnIndex + " to integer" + e.toString(), e);
      }
   }

   public int getInt(String columnName) throws SQLException {
      return this.getInt(this.findColumn(columnName));
   }

   public long getLong(int columnIndex) throws SQLException {
      try {
         Object obj = this.getObject(columnIndex);
         if (Number.class.isInstance(obj)) {
            return ((Number)obj).longValue();
         } else if (obj == null) {
            return 0L;
         } else if (String.class.isInstance(obj)) {
            return Long.parseLong((String)obj);
         } else {
            throw new Exception("Illegal conversion");
         }
      } catch (Exception e) {
         throw new SQLException("Cannot convert column " + columnIndex + " to long: " + e.toString(), e);
      }
   }

   public long getLong(String columnName) throws SQLException {
      return this.getLong(this.findColumn(columnName));
   }

   public ResultSetMetaData getMetaData() throws SQLException {
      return new HiveResultSetMetaData(this.columnNames, this.columnTypes, this.columnAttributes);
   }

   public Reader getNCharacterStream(int arg0) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public Reader getNCharacterStream(String arg0) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public NClob getNClob(int arg0) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public NClob getNClob(String columnLabel) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public String getNString(int columnIndex) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public String getNString(String columnLabel) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   private Object getColumnValue(int columnIndex) throws SQLException {
      if (this.row == null) {
         throw new SQLException("No row found.");
      } else if (this.row.length == 0) {
         throw new SQLException("RowSet does not contain any columns!");
      } else if (columnIndex > this.row.length) {
         throw new SQLException("Invalid columnIndex: " + columnIndex);
      } else {
         Type columnType = this.getSchema().getColumnDescriptorAt(columnIndex - 1).getType();

         try {
            Object evaluated = this.evaluate(columnType, this.row[columnIndex - 1]);
            this.wasNull = evaluated == null;
            return evaluated;
         } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Unrecognized column type:" + columnType, e);
         }
      }
   }

   private Object evaluate(Type type, Object value) {
      if (value == null) {
         return null;
      } else {
         switch (type) {
            case BINARY_TYPE:
               if (value instanceof String) {
                  return ((String)value).getBytes();
               }

               return value;
            case TIMESTAMP_TYPE:
               return Timestamp.valueOf((String)value);
            case DECIMAL_TYPE:
               return new BigDecimal((String)value);
            case DATE_TYPE:
               return Date.valueOf((String)value);
            case INTERVAL_YEAR_MONTH_TYPE:
               return HiveIntervalYearMonth.valueOf((String)value);
            case INTERVAL_DAY_TIME_TYPE:
               return HiveIntervalDayTime.valueOf((String)value);
            case ARRAY_TYPE:
            case MAP_TYPE:
            case STRUCT_TYPE:
               return value;
            default:
               return value;
         }
      }
   }

   public Object getObject(int columnIndex) throws SQLException {
      return this.getColumnValue(columnIndex);
   }

   public Object getObject(String columnName) throws SQLException {
      return this.getObject(this.findColumn(columnName));
   }

   public Object getObject(int i, Map map) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public Object getObject(String colName, Map map) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public Ref getRef(int i) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public Ref getRef(String colName) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public int getRow() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public RowId getRowId(int columnIndex) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public RowId getRowId(String columnLabel) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public SQLXML getSQLXML(int columnIndex) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public SQLXML getSQLXML(String columnLabel) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public short getShort(int columnIndex) throws SQLException {
      try {
         Object obj = this.getObject(columnIndex);
         if (Number.class.isInstance(obj)) {
            return ((Number)obj).shortValue();
         } else if (obj == null) {
            return 0;
         } else if (String.class.isInstance(obj)) {
            return Short.parseShort((String)obj);
         } else {
            throw new Exception("Illegal conversion");
         }
      } catch (Exception e) {
         throw new SQLException("Cannot convert column " + columnIndex + " to short: " + e.toString(), e);
      }
   }

   public short getShort(String columnName) throws SQLException {
      return this.getShort(this.findColumn(columnName));
   }

   public Statement getStatement() throws SQLException {
      return this.statement;
   }

   public String getString(int columnIndex) throws SQLException {
      Object value = this.getColumnValue(columnIndex);
      if (this.wasNull) {
         return null;
      } else {
         return value instanceof byte[] ? new String((byte[])value) : value.toString();
      }
   }

   public String getString(String columnName) throws SQLException {
      return this.getString(this.findColumn(columnName));
   }

   public Time getTime(int columnIndex) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public Time getTime(String columnName) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public Time getTime(int columnIndex, Calendar cal) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public Time getTime(String columnName, Calendar cal) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public Timestamp getTimestamp(int columnIndex) throws SQLException {
      Object obj = this.getObject(columnIndex);
      if (obj == null) {
         return null;
      } else if (obj instanceof Timestamp) {
         return (Timestamp)obj;
      } else if (obj instanceof String) {
         return Timestamp.valueOf((String)obj);
      } else {
         throw new SQLException("Illegal conversion");
      }
   }

   public Timestamp getTimestamp(String columnName) throws SQLException {
      return this.getTimestamp(this.findColumn(columnName));
   }

   public Timestamp getTimestamp(int columnIndex, Calendar cal) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public Timestamp getTimestamp(String columnName, Calendar cal) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public int getType() throws SQLException {
      return 1003;
   }

   public URL getURL(int columnIndex) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public URL getURL(String columnName) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public InputStream getUnicodeStream(int columnIndex) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public InputStream getUnicodeStream(String columnName) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void insertRow() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean isAfterLast() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean isBeforeFirst() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean isClosed() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean isFirst() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean isLast() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean last() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void moveToCurrentRow() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void moveToInsertRow() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean previous() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void refreshRow() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean relative(int rows) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean rowDeleted() throws SQLException {
      return false;
   }

   public boolean rowInserted() throws SQLException {
      return false;
   }

   public boolean rowUpdated() throws SQLException {
      return false;
   }

   public void setFetchDirection(int direction) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setFetchSize(int rows) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateArray(int columnIndex, Array x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateArray(String columnName, Array x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateAsciiStream(int columnIndex, InputStream x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateAsciiStream(String columnLabel, InputStream x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateAsciiStream(int columnIndex, InputStream x, int length) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateAsciiStream(String columnName, InputStream x, int length) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateAsciiStream(int columnIndex, InputStream x, long length) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateAsciiStream(String columnLabel, InputStream x, long length) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateBigDecimal(int columnIndex, BigDecimal x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateBigDecimal(String columnName, BigDecimal x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateBinaryStream(int columnIndex, InputStream x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateBinaryStream(String columnLabel, InputStream x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateBinaryStream(int columnIndex, InputStream x, int length) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateBinaryStream(String columnName, InputStream x, int length) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateBinaryStream(int columnIndex, InputStream x, long length) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateBinaryStream(String columnLabel, InputStream x, long length) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateBlob(int columnIndex, Blob x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateBlob(String columnName, Blob x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateBlob(int columnIndex, InputStream inputStream) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateBlob(String columnLabel, InputStream inputStream) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateBlob(int columnIndex, InputStream inputStream, long length) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateBlob(String columnLabel, InputStream inputStream, long length) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateBoolean(int columnIndex, boolean x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateBoolean(String columnName, boolean x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateByte(int columnIndex, byte x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateByte(String columnName, byte x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateBytes(int columnIndex, byte[] x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateBytes(String columnName, byte[] x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateCharacterStream(int columnIndex, Reader x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateCharacterStream(String columnLabel, Reader reader) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateCharacterStream(int columnIndex, Reader x, int length) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateCharacterStream(String columnName, Reader reader, int length) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateCharacterStream(int columnIndex, Reader x, long length) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateCharacterStream(String columnLabel, Reader reader, long length) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateClob(int columnIndex, Clob x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateClob(String columnName, Clob x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateClob(int columnIndex, Reader reader) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateClob(String columnLabel, Reader reader) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateClob(int columnIndex, Reader reader, long length) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateClob(String columnLabel, Reader reader, long length) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateDate(int columnIndex, Date x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateDate(String columnName, Date x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateDouble(int columnIndex, double x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateDouble(String columnName, double x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateFloat(int columnIndex, float x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateFloat(String columnName, float x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateInt(int columnIndex, int x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateInt(String columnName, int x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateLong(int columnIndex, long x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateLong(String columnName, long x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateNCharacterStream(int columnIndex, Reader x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateNCharacterStream(String columnLabel, Reader reader) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateNCharacterStream(int columnIndex, Reader x, long length) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateNCharacterStream(String columnLabel, Reader reader, long length) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateNClob(int columnIndex, NClob clob) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateNClob(String columnLabel, NClob clob) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateNClob(int columnIndex, Reader reader) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateNClob(String columnLabel, Reader reader) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateNClob(int columnIndex, Reader reader, long length) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateNClob(String columnLabel, Reader reader, long length) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateNString(int columnIndex, String string) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateNString(String columnLabel, String string) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateNull(int columnIndex) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateNull(String columnName) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateObject(int columnIndex, Object x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateObject(String columnName, Object x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateObject(int columnIndex, Object x, int scale) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateObject(String columnName, Object x, int scale) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateRef(int columnIndex, Ref x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateRef(String columnName, Ref x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateRow() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateRowId(int columnIndex, RowId x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateRowId(String columnLabel, RowId x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateSQLXML(int columnIndex, SQLXML xmlObject) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateSQLXML(String columnLabel, SQLXML xmlObject) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateShort(int columnIndex, short x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateShort(String columnName, short x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateString(int columnIndex, String x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateString(String columnName, String x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateTime(int columnIndex, Time x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateTime(String columnName, Time x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateTimestamp(int columnIndex, Timestamp x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void updateTimestamp(String columnName, Timestamp x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public SQLWarning getWarnings() throws SQLException {
      return this.warningChain;
   }

   public void clearWarnings() throws SQLException {
      this.warningChain = null;
   }

   public void close() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean wasNull() throws SQLException {
      return this.wasNull;
   }

   public boolean isWrapperFor(Class iface) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public Object unwrap(Class iface) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   protected void setSchema(TableSchema schema) {
      this.schema = schema;
   }

   protected TableSchema getSchema() {
      return this.schema;
   }
}
