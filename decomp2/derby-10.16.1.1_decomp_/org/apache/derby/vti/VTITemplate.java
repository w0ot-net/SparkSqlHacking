package org.apache.derby.vti;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;

public abstract class VTITemplate implements ResultSet, AwareVTI {
   private VTIContext _vtiContext;

   public boolean isWrapperFor(Class var1) throws SQLException {
      throw this.notImplemented("isWrapperFor");
   }

   public Object unwrap(Class var1) throws SQLException {
      throw this.notImplemented("unwrap");
   }

   public ResultSetMetaData getMetaData() throws SQLException {
      throw this.notImplemented("getMetaData");
   }

   public String getString(String var1) throws SQLException {
      return this.getString(this.findColumn(var1));
   }

   public boolean getBoolean(String var1) throws SQLException {
      return this.getBoolean(this.findColumn(var1));
   }

   public byte getByte(String var1) throws SQLException {
      return this.getByte(this.findColumn(var1));
   }

   public short getShort(String var1) throws SQLException {
      return this.getShort(this.findColumn(var1));
   }

   public int getInt(String var1) throws SQLException {
      return this.getInt(this.findColumn(var1));
   }

   public long getLong(String var1) throws SQLException {
      return this.getLong(this.findColumn(var1));
   }

   public float getFloat(String var1) throws SQLException {
      return this.getFloat(this.findColumn(var1));
   }

   public double getDouble(String var1) throws SQLException {
      return this.getDouble(this.findColumn(var1));
   }

   /** @deprecated */
   @Deprecated
   public BigDecimal getBigDecimal(String var1, int var2) throws SQLException {
      return this.getBigDecimal(this.findColumn(var1), var2);
   }

   public byte[] getBytes(String var1) throws SQLException {
      return this.getBytes(this.findColumn(var1));
   }

   public Date getDate(String var1) throws SQLException {
      return this.getDate(this.findColumn(var1));
   }

   public Time getTime(String var1) throws SQLException {
      return this.getTime(this.findColumn(var1));
   }

   public Timestamp getTimestamp(String var1) throws SQLException {
      return this.getTimestamp(this.findColumn(var1));
   }

   public Object getObject(String var1) throws SQLException {
      return this.getObject(this.findColumn(var1));
   }

   public Object getObject(String var1, Class var2) throws SQLException {
      return this.getObject(this.findColumn(var1), var2);
   }

   public BigDecimal getBigDecimal(String var1) throws SQLException {
      return this.getBigDecimal(this.findColumn(var1));
   }

   public boolean wasNull() throws SQLException {
      throw this.notImplemented("wasNull");
   }

   public String getString(int var1) throws SQLException {
      throw this.notImplemented("getString");
   }

   public boolean getBoolean(int var1) throws SQLException {
      throw this.notImplemented("getBoolean");
   }

   public byte getByte(int var1) throws SQLException {
      throw this.notImplemented("getByte");
   }

   public short getShort(int var1) throws SQLException {
      throw this.notImplemented("getShort");
   }

   public int getInt(int var1) throws SQLException {
      throw this.notImplemented("getInt");
   }

   public long getLong(int var1) throws SQLException {
      throw this.notImplemented("getLong");
   }

   public float getFloat(int var1) throws SQLException {
      throw this.notImplemented("getFloat");
   }

   public double getDouble(int var1) throws SQLException {
      throw this.notImplemented("getDouble");
   }

   /** @deprecated */
   @Deprecated
   public BigDecimal getBigDecimal(int var1, int var2) throws SQLException {
      throw this.notImplemented("getBigDecimal");
   }

   public byte[] getBytes(int var1) throws SQLException {
      throw this.notImplemented("] getBytes");
   }

   public Date getDate(int var1) throws SQLException {
      throw this.notImplemented("sql.Date getDate");
   }

   public Time getTime(int var1) throws SQLException {
      throw this.notImplemented("sql.Time getTime");
   }

   public Timestamp getTimestamp(int var1) throws SQLException {
      throw this.notImplemented("sql.Timestamp getTimestamp");
   }

   public InputStream getAsciiStream(int var1) throws SQLException {
      throw this.notImplemented("io.InputStream getAsciiStream");
   }

   /** @deprecated */
   @Deprecated
   public InputStream getUnicodeStream(int var1) throws SQLException {
      throw this.notImplemented("io.InputStream getUnicodeStream");
   }

   public InputStream getBinaryStream(int var1) throws SQLException {
      throw this.notImplemented("io.InputStream getBinaryStream");
   }

   public InputStream getAsciiStream(String var1) throws SQLException {
      throw this.notImplemented("io.InputStream getAsciiStream");
   }

   /** @deprecated */
   @Deprecated
   public InputStream getUnicodeStream(String var1) throws SQLException {
      throw this.notImplemented("io.InputStream getUnicodeStream");
   }

   public InputStream getBinaryStream(String var1) throws SQLException {
      throw this.notImplemented("io.InputStream getBinaryStream");
   }

   public SQLWarning getWarnings() throws SQLException {
      return null;
   }

   public void clearWarnings() throws SQLException {
      throw this.notImplemented("clearWarnings");
   }

   public String getCursorName() throws SQLException {
      throw this.notImplemented("getCursorName");
   }

   public Object getObject(int var1) throws SQLException {
      throw this.notImplemented("getObject");
   }

   public Object getObject(int var1, Class var2) throws SQLException {
      throw this.notImplemented("getObject");
   }

   public int findColumn(String var1) throws SQLException {
      throw this.notImplemented("findColumn");
   }

   public Reader getCharacterStream(int var1) throws SQLException {
      throw this.notImplemented("io.Reader getCharacterStream");
   }

   public Reader getCharacterStream(String var1) throws SQLException {
      throw this.notImplemented("io.Reader getCharacterStream");
   }

   public BigDecimal getBigDecimal(int var1) throws SQLException {
      throw this.notImplemented("getBigDecimal");
   }

   public boolean isBeforeFirst() throws SQLException {
      throw this.notImplemented("isBeforeFirst");
   }

   public boolean isAfterLast() throws SQLException {
      throw this.notImplemented("isAfterLast");
   }

   public boolean isFirst() throws SQLException {
      throw this.notImplemented("isFirst");
   }

   public boolean isLast() throws SQLException {
      throw this.notImplemented("isLast");
   }

   public void beforeFirst() throws SQLException {
      throw this.notImplemented("beforeFirst");
   }

   public void afterLast() throws SQLException {
      throw this.notImplemented("afterLast");
   }

   public boolean first() throws SQLException {
      throw this.notImplemented("first");
   }

   public boolean last() throws SQLException {
      throw this.notImplemented("last");
   }

   public boolean isClosed() throws SQLException {
      throw this.notImplemented("isClosed");
   }

   public int getHoldability() throws SQLException {
      throw this.notImplemented("getHoldability");
   }

   public int getRow() throws SQLException {
      throw this.notImplemented("getRow");
   }

   public boolean absolute(int var1) throws SQLException {
      throw this.notImplemented("absolute");
   }

   public boolean relative(int var1) throws SQLException {
      throw this.notImplemented("relative");
   }

   public boolean previous() throws SQLException {
      throw this.notImplemented("previous");
   }

   public void setFetchDirection(int var1) throws SQLException {
      throw this.notImplemented("setFetchDirection");
   }

   public int getFetchDirection() throws SQLException {
      throw this.notImplemented("getFetchDirection");
   }

   public void setFetchSize(int var1) throws SQLException {
      throw this.notImplemented("setFetchSize");
   }

   public int getFetchSize() throws SQLException {
      throw this.notImplemented("getFetchSize");
   }

   public int getType() throws SQLException {
      throw this.notImplemented("getType");
   }

   public int getConcurrency() throws SQLException {
      throw this.notImplemented("getConcurrency");
   }

   public boolean rowUpdated() throws SQLException {
      throw this.notImplemented("rowUpdated");
   }

   public boolean rowInserted() throws SQLException {
      throw this.notImplemented("rowInserted");
   }

   public boolean rowDeleted() throws SQLException {
      throw this.notImplemented("rowDeleted");
   }

   public void updateNull(int var1) throws SQLException {
      throw this.notImplemented("updateNull");
   }

   public void updateBoolean(int var1, boolean var2) throws SQLException {
      throw this.notImplemented("updateBoolean");
   }

   public void updateByte(int var1, byte var2) throws SQLException {
      throw this.notImplemented("updateByte");
   }

   public void updateBytes(int var1, byte[] var2) throws SQLException {
      throw this.notImplemented("updateBytes");
   }

   public void updateShort(int var1, short var2) throws SQLException {
      throw this.notImplemented("updateShort");
   }

   public void updateInt(int var1, int var2) throws SQLException {
      throw this.notImplemented("updateInt");
   }

   public void updateLong(int var1, long var2) throws SQLException {
      throw this.notImplemented("updateLong");
   }

   public void updateFloat(int var1, float var2) throws SQLException {
      throw this.notImplemented("updateFloat");
   }

   public void updateDouble(int var1, double var2) throws SQLException {
      throw this.notImplemented("updateDouble");
   }

   public void updateBigDecimal(int var1, BigDecimal var2) throws SQLException {
      throw this.notImplemented("updateBigDecimal");
   }

   public void updateString(int var1, String var2) throws SQLException {
      throw this.notImplemented("updateString");
   }

   public void updateDate(int var1, Date var2) throws SQLException {
      throw this.notImplemented("updateDate");
   }

   public void updateTime(int var1, Time var2) throws SQLException {
      throw this.notImplemented("updateTime");
   }

   public void updateTimestamp(int var1, Timestamp var2) throws SQLException {
      throw this.notImplemented("updateTimestamp");
   }

   public void updateAsciiStream(int var1, InputStream var2) throws SQLException {
      throw this.notImplemented("updateAsciiStream");
   }

   public void updateAsciiStream(int var1, InputStream var2, int var3) throws SQLException {
      throw this.notImplemented("updateAsciiStream");
   }

   public void updateAsciiStream(int var1, InputStream var2, long var3) throws SQLException {
      throw this.notImplemented("updateAsciiStream");
   }

   public void updateBinaryStream(int var1, InputStream var2) throws SQLException {
      throw this.notImplemented("updateBinaryStream");
   }

   public void updateBinaryStream(int var1, InputStream var2, int var3) throws SQLException {
      throw this.notImplemented("updateBinaryStream");
   }

   public void updateBinaryStream(int var1, InputStream var2, long var3) throws SQLException {
      throw this.notImplemented("updateBinaryStream");
   }

   public void updateCharacterStream(int var1, Reader var2) throws SQLException {
      throw this.notImplemented("updateCharacterStream");
   }

   public void updateCharacterStream(int var1, Reader var2, long var3) throws SQLException {
      throw this.notImplemented("updateCharacterStream");
   }

   public void updateCharacterStream(int var1, Reader var2, int var3) throws SQLException {
      throw this.notImplemented("updateCharacterStream");
   }

   public void updateObject(int var1, Object var2, int var3) throws SQLException {
      throw this.notImplemented("updateObject");
   }

   public void updateObject(int var1, Object var2) throws SQLException {
      throw this.notImplemented("updateObject");
   }

   public void updateNull(String var1) throws SQLException {
      throw this.notImplemented("updateNull");
   }

   public void updateBoolean(String var1, boolean var2) throws SQLException {
      throw this.notImplemented("updateBoolean");
   }

   public void updateByte(String var1, byte var2) throws SQLException {
      throw this.notImplemented("updateByte");
   }

   public void updateShort(String var1, short var2) throws SQLException {
      throw this.notImplemented("updateShort");
   }

   public void updateInt(String var1, int var2) throws SQLException {
      throw this.notImplemented("updateInt");
   }

   public void updateLong(String var1, long var2) throws SQLException {
      throw this.notImplemented("updateLong");
   }

   public void updateFloat(String var1, float var2) throws SQLException {
      throw this.notImplemented("updateFloat");
   }

   public void updateDouble(String var1, double var2) throws SQLException {
      throw this.notImplemented("updateDouble");
   }

   public void updateBigDecimal(String var1, BigDecimal var2) throws SQLException {
      throw this.notImplemented("updateBigDecimal");
   }

   public void updateString(String var1, String var2) throws SQLException {
      throw this.notImplemented("updateString");
   }

   public void updateBytes(String var1, byte[] var2) throws SQLException {
      throw this.notImplemented("updateBytes");
   }

   public void updateDate(String var1, Date var2) throws SQLException {
      throw this.notImplemented("updateDate");
   }

   public void updateTime(String var1, Time var2) throws SQLException {
      throw this.notImplemented("updateTime");
   }

   public void updateTimestamp(String var1, Timestamp var2) throws SQLException {
      throw this.notImplemented("updateTimestamp");
   }

   public void updateAsciiStream(String var1, InputStream var2) throws SQLException {
      throw this.notImplemented("updateAsciiStream");
   }

   public void updateAsciiStream(String var1, InputStream var2, int var3) throws SQLException {
      throw this.notImplemented("updateAsciiStream");
   }

   public void updateAsciiStream(String var1, InputStream var2, long var3) throws SQLException {
      throw this.notImplemented("updateAsciiStream");
   }

   public void updateBinaryStream(String var1, InputStream var2) throws SQLException {
      throw this.notImplemented("updateBinaryStream");
   }

   public void updateBinaryStream(String var1, InputStream var2, int var3) throws SQLException {
      throw this.notImplemented("updateBinaryStream");
   }

   public void updateBinaryStream(String var1, InputStream var2, long var3) throws SQLException {
      throw this.notImplemented("updateBinaryStream");
   }

   public void updateCharacterStream(String var1, Reader var2) throws SQLException {
      throw this.notImplemented("updateCharacterStream");
   }

   public void updateCharacterStream(String var1, Reader var2, long var3) throws SQLException {
      throw this.notImplemented("updateCharacterStream");
   }

   public void updateCharacterStream(String var1, Reader var2, int var3) throws SQLException {
      throw this.notImplemented("updateCharacterStream");
   }

   public void updateObject(String var1, Object var2, int var3) throws SQLException {
      throw this.notImplemented("updateObject");
   }

   public void updateObject(String var1, Object var2) throws SQLException {
      throw this.notImplemented("updateObject");
   }

   public void insertRow() throws SQLException {
      throw this.notImplemented("insertRow");
   }

   public void updateRow() throws SQLException {
      throw this.notImplemented("updateRow");
   }

   public void deleteRow() throws SQLException {
      throw this.notImplemented("deleteRow");
   }

   public void refreshRow() throws SQLException {
      throw this.notImplemented("refreshRow");
   }

   public void cancelRowUpdates() throws SQLException {
      throw this.notImplemented("cancelRowUpdates");
   }

   public void moveToInsertRow() throws SQLException {
      throw this.notImplemented("moveToInsertRow");
   }

   public void moveToCurrentRow() throws SQLException {
      throw this.notImplemented("moveToCurrentRow");
   }

   public Statement getStatement() throws SQLException {
      throw this.notImplemented("getStatement");
   }

   public Date getDate(int var1, Calendar var2) throws SQLException {
      throw this.notImplemented("sql.Date getDate");
   }

   public Date getDate(String var1, Calendar var2) throws SQLException {
      throw this.notImplemented("sql.Date getDate");
   }

   public Time getTime(int var1, Calendar var2) throws SQLException {
      throw this.notImplemented("sql.Time getTime");
   }

   public Time getTime(String var1, Calendar var2) throws SQLException {
      throw this.notImplemented("sql.Time getTime");
   }

   public Timestamp getTimestamp(int var1, Calendar var2) throws SQLException {
      throw this.notImplemented("sql.Timestamp getTimestamp");
   }

   public Timestamp getTimestamp(String var1, Calendar var2) throws SQLException {
      throw this.notImplemented("sql.Timestamp getTimestamp");
   }

   public URL getURL(int var1) throws SQLException {
      throw this.notImplemented("getURL");
   }

   public URL getURL(String var1) throws SQLException {
      throw this.notImplemented("getURL");
   }

   public Object getObject(int var1, Map var2) throws SQLException {
      throw this.notImplemented("getObject");
   }

   public Ref getRef(int var1) throws SQLException {
      throw this.notImplemented("getRef");
   }

   public Blob getBlob(int var1) throws SQLException {
      throw this.notImplemented("getBlob");
   }

   public Clob getClob(int var1) throws SQLException {
      throw this.notImplemented("getClob");
   }

   public Array getArray(int var1) throws SQLException {
      throw this.notImplemented("getArray");
   }

   public Object getObject(String var1, Map var2) throws SQLException {
      throw this.notImplemented("getObject");
   }

   public Ref getRef(String var1) throws SQLException {
      throw this.notImplemented("getRef");
   }

   public Blob getBlob(String var1) throws SQLException {
      throw this.notImplemented("getBlob");
   }

   public Clob getClob(String var1) throws SQLException {
      throw this.notImplemented("getClob");
   }

   public Array getArray(String var1) throws SQLException {
      throw this.notImplemented("getArray");
   }

   public SQLXML getSQLXML(int var1) throws SQLException {
      throw this.notImplemented("getSQLXML");
   }

   public SQLXML getSQLXML(String var1) throws SQLException {
      throw this.notImplemented("getSQLXML");
   }

   public void updateRef(int var1, Ref var2) throws SQLException {
      throw this.notImplemented("updateRef");
   }

   public void updateRef(String var1, Ref var2) throws SQLException {
      throw this.notImplemented("updateRef");
   }

   public void updateBlob(int var1, Blob var2) throws SQLException {
      throw this.notImplemented("updateBlob");
   }

   public void updateBlob(String var1, Blob var2) throws SQLException {
      throw this.notImplemented("updateBlob");
   }

   public void updateBlob(int var1, InputStream var2) throws SQLException {
      throw this.notImplemented("updateBlob");
   }

   public void updateBlob(int var1, InputStream var2, long var3) throws SQLException {
      throw this.notImplemented("updateBlob");
   }

   public void updateBlob(String var1, InputStream var2) throws SQLException {
      throw this.notImplemented("updateBlob");
   }

   public void updateBlob(String var1, InputStream var2, long var3) throws SQLException {
      throw this.notImplemented("updateBlob");
   }

   public void updateClob(int var1, Clob var2) throws SQLException {
      throw this.notImplemented("updateClob");
   }

   public void updateClob(String var1, Clob var2) throws SQLException {
      throw this.notImplemented("updateClob");
   }

   public void updateClob(int var1, Reader var2) throws SQLException {
      throw this.notImplemented("updateClob");
   }

   public void updateClob(int var1, Reader var2, long var3) throws SQLException {
      throw this.notImplemented("updateClob");
   }

   public void updateClob(String var1, Reader var2) throws SQLException {
      throw this.notImplemented("updateClob");
   }

   public void updateClob(String var1, Reader var2, long var3) throws SQLException {
      throw this.notImplemented("updateClob");
   }

   public void updateArray(int var1, Array var2) throws SQLException {
      throw this.notImplemented("updateArray");
   }

   public void updateArray(String var1, Array var2) throws SQLException {
      throw this.notImplemented("updateArray");
   }

   public Reader getNCharacterStream(int var1) throws SQLException {
      throw this.notImplemented("getNCharacterStream");
   }

   public Reader getNCharacterStream(String var1) throws SQLException {
      throw this.notImplemented("getNCharacterStream");
   }

   public NClob getNClob(int var1) throws SQLException {
      throw this.notImplemented("getNClob");
   }

   public NClob getNClob(String var1) throws SQLException {
      throw this.notImplemented("getNClob");
   }

   public String getNString(int var1) throws SQLException {
      throw this.notImplemented("getNString");
   }

   public String getNString(String var1) throws SQLException {
      throw this.notImplemented("getNString");
   }

   public RowId getRowId(int var1) throws SQLException {
      throw this.notImplemented("getRowId");
   }

   public RowId getRowId(String var1) throws SQLException {
      throw this.notImplemented("getRowId");
   }

   public void updateNCharacterStream(int var1, Reader var2) throws SQLException {
      throw this.notImplemented("updateNCharacterStream");
   }

   public void updateNCharacterStream(int var1, Reader var2, long var3) throws SQLException {
      throw this.notImplemented("updateNCharacterStream");
   }

   public void updateNCharacterStream(String var1, Reader var2) throws SQLException {
      throw this.notImplemented("updateNCharacterStream");
   }

   public void updateNCharacterStream(String var1, Reader var2, long var3) throws SQLException {
      throw this.notImplemented("updateNCharacterStream");
   }

   public void updateNClob(int var1, NClob var2) throws SQLException {
      throw this.notImplemented("updateNClob");
   }

   public void updateNClob(int var1, Reader var2) throws SQLException {
      throw this.notImplemented("updateNClob");
   }

   public void updateNClob(int var1, Reader var2, long var3) throws SQLException {
      throw this.notImplemented("updateNClob");
   }

   public void updateNClob(String var1, NClob var2) throws SQLException {
      throw this.notImplemented("updateNClob");
   }

   public void updateNClob(String var1, Reader var2) throws SQLException {
      throw this.notImplemented("updateNClob");
   }

   public void updateNClob(String var1, Reader var2, long var3) throws SQLException {
      throw this.notImplemented("updateNClob");
   }

   public void updateNString(int var1, String var2) throws SQLException {
      throw this.notImplemented("updateNString");
   }

   public void updateNString(String var1, String var2) throws SQLException {
      throw this.notImplemented("updateNString");
   }

   public void updateRowId(int var1, RowId var2) throws SQLException {
      throw this.notImplemented("updateRowId");
   }

   public void updateRowId(String var1, RowId var2) throws SQLException {
      throw this.notImplemented("updateRowId");
   }

   public void updateSQLXML(int var1, SQLXML var2) throws SQLException {
      throw this.notImplemented("updateSQLXML");
   }

   public void updateSQLXML(String var1, SQLXML var2) throws SQLException {
      throw this.notImplemented("updateSQLXML");
   }

   public VTIContext getContext() {
      return this._vtiContext;
   }

   public void setContext(VTIContext var1) {
      this._vtiContext = var1;
   }

   protected SQLException notImplemented(String var1) {
      return new SQLFeatureNotSupportedException("Unimplemented method: " + var1);
   }

   public ColumnDescriptor[] getReturnTableSignature(Connection var1) throws SQLException {
      ArrayList var2 = new ArrayList();
      VTIContext var3 = this.getContext();
      String var4 = var3.vtiSchema();
      String var5 = var3.vtiTable();
      ResultSet var6 = var1.getMetaData().getFunctionColumns((String)null, var4, var5, "%");

      try {
         while(var6.next()) {
            if (var6.getInt("COLUMN_TYPE") == 5) {
               ColumnDescriptor var7 = new ColumnDescriptor(var6.getString("COLUMN_NAME"), var6.getInt("DATA_TYPE"), var6.getInt("PRECISION"), var6.getInt("SCALE"), var6.getString("TYPE_NAME"), var6.getInt("ORDINAL_POSITION"));
               var2.add(var7);
            }
         }
      } finally {
         var6.close();
      }

      ColumnDescriptor[] var11 = new ColumnDescriptor[var2.size()];
      var2.toArray(var11);
      Arrays.sort(var11);
      return var11;
   }

   public static final class ColumnDescriptor implements Comparable {
      public final String columnName;
      public final int jdbcType;
      public final int precision;
      public final int scale;
      public final String typeName;
      public final int ordinalPosition;

      public ColumnDescriptor(String var1, int var2, int var3, int var4, String var5, int var6) {
         this.columnName = var1;
         this.jdbcType = var2;
         this.precision = var3;
         this.scale = var4;
         this.typeName = var5;
         this.ordinalPosition = var6;
      }

      public int compareTo(ColumnDescriptor var1) {
         return this.ordinalPosition - var1.ordinalPosition;
      }

      public boolean equals(Object var1) {
         if (var1 == null) {
            return false;
         } else if (!(var1 instanceof ColumnDescriptor)) {
            return false;
         } else {
            return this.compareTo((ColumnDescriptor)var1) == 0;
         }
      }

      public int hashCode() {
         return this.columnName.hashCode();
      }

      public String toString() {
         return this.columnName;
      }
   }
}
