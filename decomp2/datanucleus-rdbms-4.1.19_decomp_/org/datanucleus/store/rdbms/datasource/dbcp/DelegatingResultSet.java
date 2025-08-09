package org.datanucleus.store.rdbms.datasource.dbcp;

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
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;

public class DelegatingResultSet extends AbandonedTrace implements ResultSet {
   private ResultSet _res;
   private Statement _stmt;
   private Connection _conn;

   public DelegatingResultSet(Statement stmt, ResultSet res) {
      super((AbandonedTrace)stmt);
      this._stmt = stmt;
      this._res = res;
   }

   public DelegatingResultSet(Connection conn, ResultSet res) {
      super((AbandonedTrace)conn);
      this._conn = conn;
      this._res = res;
   }

   public static ResultSet wrapResultSet(Statement stmt, ResultSet rset) {
      return null == rset ? null : new DelegatingResultSet(stmt, rset);
   }

   public static ResultSet wrapResultSet(Connection conn, ResultSet rset) {
      return null == rset ? null : new DelegatingResultSet(conn, rset);
   }

   public ResultSet getDelegate() {
      return this._res;
   }

   public boolean equals(Object obj) {
      ResultSet delegate = this.getInnermostDelegate();
      if (delegate == null) {
         return false;
      } else if (obj instanceof DelegatingResultSet) {
         DelegatingResultSet s = (DelegatingResultSet)obj;
         return delegate.equals(s.getInnermostDelegate());
      } else {
         return delegate.equals(obj);
      }
   }

   public int hashCode() {
      Object obj = this.getInnermostDelegate();
      return obj == null ? 0 : obj.hashCode();
   }

   public ResultSet getInnermostDelegate() {
      ResultSet r = this._res;

      while(r != null && r instanceof DelegatingResultSet) {
         r = ((DelegatingResultSet)r).getDelegate();
         if (this == r) {
            return null;
         }
      }

      return r;
   }

   public Statement getStatement() throws SQLException {
      return this._stmt;
   }

   public void close() throws SQLException {
      try {
         if (this._stmt != null) {
            ((AbandonedTrace)this._stmt).removeTrace(this);
            this._stmt = null;
         }

         if (this._conn != null) {
            ((AbandonedTrace)this._conn).removeTrace(this);
            this._conn = null;
         }

         this._res.close();
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   protected void handleException(SQLException e) throws SQLException {
      if (this._stmt != null && this._stmt instanceof DelegatingStatement) {
         ((DelegatingStatement)this._stmt).handleException(e);
      } else {
         if (this._conn == null || !(this._conn instanceof DelegatingConnection)) {
            throw e;
         }

         ((DelegatingConnection)this._conn).handleException(e);
      }

   }

   public boolean next() throws SQLException {
      try {
         return this._res.next();
      } catch (SQLException e) {
         this.handleException(e);
         return false;
      }
   }

   public boolean wasNull() throws SQLException {
      try {
         return this._res.wasNull();
      } catch (SQLException e) {
         this.handleException(e);
         return false;
      }
   }

   public String getString(int columnIndex) throws SQLException {
      try {
         return this._res.getString(columnIndex);
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public boolean getBoolean(int columnIndex) throws SQLException {
      try {
         return this._res.getBoolean(columnIndex);
      } catch (SQLException e) {
         this.handleException(e);
         return false;
      }
   }

   public byte getByte(int columnIndex) throws SQLException {
      try {
         return this._res.getByte(columnIndex);
      } catch (SQLException e) {
         this.handleException(e);
         return 0;
      }
   }

   public short getShort(int columnIndex) throws SQLException {
      try {
         return this._res.getShort(columnIndex);
      } catch (SQLException e) {
         this.handleException(e);
         return 0;
      }
   }

   public int getInt(int columnIndex) throws SQLException {
      try {
         return this._res.getInt(columnIndex);
      } catch (SQLException e) {
         this.handleException(e);
         return 0;
      }
   }

   public long getLong(int columnIndex) throws SQLException {
      try {
         return this._res.getLong(columnIndex);
      } catch (SQLException e) {
         this.handleException(e);
         return 0L;
      }
   }

   public float getFloat(int columnIndex) throws SQLException {
      try {
         return this._res.getFloat(columnIndex);
      } catch (SQLException e) {
         this.handleException(e);
         return 0.0F;
      }
   }

   public double getDouble(int columnIndex) throws SQLException {
      try {
         return this._res.getDouble(columnIndex);
      } catch (SQLException e) {
         this.handleException(e);
         return (double)0.0F;
      }
   }

   /** @deprecated */
   public BigDecimal getBigDecimal(int columnIndex, int scale) throws SQLException {
      try {
         return this._res.getBigDecimal(columnIndex);
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public byte[] getBytes(int columnIndex) throws SQLException {
      try {
         return this._res.getBytes(columnIndex);
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public Date getDate(int columnIndex) throws SQLException {
      try {
         return this._res.getDate(columnIndex);
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public Time getTime(int columnIndex) throws SQLException {
      try {
         return this._res.getTime(columnIndex);
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public Timestamp getTimestamp(int columnIndex) throws SQLException {
      try {
         return this._res.getTimestamp(columnIndex);
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public InputStream getAsciiStream(int columnIndex) throws SQLException {
      try {
         return this._res.getAsciiStream(columnIndex);
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   /** @deprecated */
   public InputStream getUnicodeStream(int columnIndex) throws SQLException {
      try {
         return this._res.getUnicodeStream(columnIndex);
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public InputStream getBinaryStream(int columnIndex) throws SQLException {
      try {
         return this._res.getBinaryStream(columnIndex);
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public String getString(String columnName) throws SQLException {
      try {
         return this._res.getString(columnName);
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public boolean getBoolean(String columnName) throws SQLException {
      try {
         return this._res.getBoolean(columnName);
      } catch (SQLException e) {
         this.handleException(e);
         return false;
      }
   }

   public byte getByte(String columnName) throws SQLException {
      try {
         return this._res.getByte(columnName);
      } catch (SQLException e) {
         this.handleException(e);
         return 0;
      }
   }

   public short getShort(String columnName) throws SQLException {
      try {
         return this._res.getShort(columnName);
      } catch (SQLException e) {
         this.handleException(e);
         return 0;
      }
   }

   public int getInt(String columnName) throws SQLException {
      try {
         return this._res.getInt(columnName);
      } catch (SQLException e) {
         this.handleException(e);
         return 0;
      }
   }

   public long getLong(String columnName) throws SQLException {
      try {
         return this._res.getLong(columnName);
      } catch (SQLException e) {
         this.handleException(e);
         return 0L;
      }
   }

   public float getFloat(String columnName) throws SQLException {
      try {
         return this._res.getFloat(columnName);
      } catch (SQLException e) {
         this.handleException(e);
         return 0.0F;
      }
   }

   public double getDouble(String columnName) throws SQLException {
      try {
         return this._res.getDouble(columnName);
      } catch (SQLException e) {
         this.handleException(e);
         return (double)0.0F;
      }
   }

   /** @deprecated */
   public BigDecimal getBigDecimal(String columnName, int scale) throws SQLException {
      try {
         return this._res.getBigDecimal(columnName);
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public byte[] getBytes(String columnName) throws SQLException {
      try {
         return this._res.getBytes(columnName);
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public Date getDate(String columnName) throws SQLException {
      try {
         return this._res.getDate(columnName);
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public Time getTime(String columnName) throws SQLException {
      try {
         return this._res.getTime(columnName);
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public Timestamp getTimestamp(String columnName) throws SQLException {
      try {
         return this._res.getTimestamp(columnName);
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public InputStream getAsciiStream(String columnName) throws SQLException {
      try {
         return this._res.getAsciiStream(columnName);
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   /** @deprecated */
   public InputStream getUnicodeStream(String columnName) throws SQLException {
      try {
         return this._res.getUnicodeStream(columnName);
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public InputStream getBinaryStream(String columnName) throws SQLException {
      try {
         return this._res.getBinaryStream(columnName);
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public SQLWarning getWarnings() throws SQLException {
      try {
         return this._res.getWarnings();
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public void clearWarnings() throws SQLException {
      try {
         this._res.clearWarnings();
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public String getCursorName() throws SQLException {
      try {
         return this._res.getCursorName();
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public ResultSetMetaData getMetaData() throws SQLException {
      try {
         return this._res.getMetaData();
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public Object getObject(int columnIndex) throws SQLException {
      try {
         return this._res.getObject(columnIndex);
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public Object getObject(String columnName) throws SQLException {
      try {
         return this._res.getObject(columnName);
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public int findColumn(String columnName) throws SQLException {
      try {
         return this._res.findColumn(columnName);
      } catch (SQLException e) {
         this.handleException(e);
         return 0;
      }
   }

   public Reader getCharacterStream(int columnIndex) throws SQLException {
      try {
         return this._res.getCharacterStream(columnIndex);
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public Reader getCharacterStream(String columnName) throws SQLException {
      try {
         return this._res.getCharacterStream(columnName);
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public BigDecimal getBigDecimal(int columnIndex) throws SQLException {
      try {
         return this._res.getBigDecimal(columnIndex);
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public BigDecimal getBigDecimal(String columnName) throws SQLException {
      try {
         return this._res.getBigDecimal(columnName);
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public boolean isBeforeFirst() throws SQLException {
      try {
         return this._res.isBeforeFirst();
      } catch (SQLException e) {
         this.handleException(e);
         return false;
      }
   }

   public boolean isAfterLast() throws SQLException {
      try {
         return this._res.isAfterLast();
      } catch (SQLException e) {
         this.handleException(e);
         return false;
      }
   }

   public boolean isFirst() throws SQLException {
      try {
         return this._res.isFirst();
      } catch (SQLException e) {
         this.handleException(e);
         return false;
      }
   }

   public boolean isLast() throws SQLException {
      try {
         return this._res.isLast();
      } catch (SQLException e) {
         this.handleException(e);
         return false;
      }
   }

   public void beforeFirst() throws SQLException {
      try {
         this._res.beforeFirst();
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void afterLast() throws SQLException {
      try {
         this._res.afterLast();
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public boolean first() throws SQLException {
      try {
         return this._res.first();
      } catch (SQLException e) {
         this.handleException(e);
         return false;
      }
   }

   public boolean last() throws SQLException {
      try {
         return this._res.last();
      } catch (SQLException e) {
         this.handleException(e);
         return false;
      }
   }

   public int getRow() throws SQLException {
      try {
         return this._res.getRow();
      } catch (SQLException e) {
         this.handleException(e);
         return 0;
      }
   }

   public boolean absolute(int row) throws SQLException {
      try {
         return this._res.absolute(row);
      } catch (SQLException e) {
         this.handleException(e);
         return false;
      }
   }

   public boolean relative(int rows) throws SQLException {
      try {
         return this._res.relative(rows);
      } catch (SQLException e) {
         this.handleException(e);
         return false;
      }
   }

   public boolean previous() throws SQLException {
      try {
         return this._res.previous();
      } catch (SQLException e) {
         this.handleException(e);
         return false;
      }
   }

   public void setFetchDirection(int direction) throws SQLException {
      try {
         this._res.setFetchDirection(direction);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public int getFetchDirection() throws SQLException {
      try {
         return this._res.getFetchDirection();
      } catch (SQLException e) {
         this.handleException(e);
         return 0;
      }
   }

   public void setFetchSize(int rows) throws SQLException {
      try {
         this._res.setFetchSize(rows);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public int getFetchSize() throws SQLException {
      try {
         return this._res.getFetchSize();
      } catch (SQLException e) {
         this.handleException(e);
         return 0;
      }
   }

   public int getType() throws SQLException {
      try {
         return this._res.getType();
      } catch (SQLException e) {
         this.handleException(e);
         return 0;
      }
   }

   public int getConcurrency() throws SQLException {
      try {
         return this._res.getConcurrency();
      } catch (SQLException e) {
         this.handleException(e);
         return 0;
      }
   }

   public boolean rowUpdated() throws SQLException {
      try {
         return this._res.rowUpdated();
      } catch (SQLException e) {
         this.handleException(e);
         return false;
      }
   }

   public boolean rowInserted() throws SQLException {
      try {
         return this._res.rowInserted();
      } catch (SQLException e) {
         this.handleException(e);
         return false;
      }
   }

   public boolean rowDeleted() throws SQLException {
      try {
         return this._res.rowDeleted();
      } catch (SQLException e) {
         this.handleException(e);
         return false;
      }
   }

   public void updateNull(int columnIndex) throws SQLException {
      try {
         this._res.updateNull(columnIndex);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateBoolean(int columnIndex, boolean x) throws SQLException {
      try {
         this._res.updateBoolean(columnIndex, x);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateByte(int columnIndex, byte x) throws SQLException {
      try {
         this._res.updateByte(columnIndex, x);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateShort(int columnIndex, short x) throws SQLException {
      try {
         this._res.updateShort(columnIndex, x);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateInt(int columnIndex, int x) throws SQLException {
      try {
         this._res.updateInt(columnIndex, x);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateLong(int columnIndex, long x) throws SQLException {
      try {
         this._res.updateLong(columnIndex, x);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateFloat(int columnIndex, float x) throws SQLException {
      try {
         this._res.updateFloat(columnIndex, x);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateDouble(int columnIndex, double x) throws SQLException {
      try {
         this._res.updateDouble(columnIndex, x);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateBigDecimal(int columnIndex, BigDecimal x) throws SQLException {
      try {
         this._res.updateBigDecimal(columnIndex, x);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateString(int columnIndex, String x) throws SQLException {
      try {
         this._res.updateString(columnIndex, x);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateBytes(int columnIndex, byte[] x) throws SQLException {
      try {
         this._res.updateBytes(columnIndex, x);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateDate(int columnIndex, Date x) throws SQLException {
      try {
         this._res.updateDate(columnIndex, x);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateTime(int columnIndex, Time x) throws SQLException {
      try {
         this._res.updateTime(columnIndex, x);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateTimestamp(int columnIndex, Timestamp x) throws SQLException {
      try {
         this._res.updateTimestamp(columnIndex, x);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateAsciiStream(int columnIndex, InputStream x, int length) throws SQLException {
      try {
         this._res.updateAsciiStream(columnIndex, x, length);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateBinaryStream(int columnIndex, InputStream x, int length) throws SQLException {
      try {
         this._res.updateBinaryStream(columnIndex, x, length);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateCharacterStream(int columnIndex, Reader x, int length) throws SQLException {
      try {
         this._res.updateCharacterStream(columnIndex, x, length);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateObject(int columnIndex, Object x, int scale) throws SQLException {
      try {
         this._res.updateObject(columnIndex, x);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateObject(int columnIndex, Object x) throws SQLException {
      try {
         this._res.updateObject(columnIndex, x);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateNull(String columnName) throws SQLException {
      try {
         this._res.updateNull(columnName);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateBoolean(String columnName, boolean x) throws SQLException {
      try {
         this._res.updateBoolean(columnName, x);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateByte(String columnName, byte x) throws SQLException {
      try {
         this._res.updateByte(columnName, x);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateShort(String columnName, short x) throws SQLException {
      try {
         this._res.updateShort(columnName, x);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateInt(String columnName, int x) throws SQLException {
      try {
         this._res.updateInt(columnName, x);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateLong(String columnName, long x) throws SQLException {
      try {
         this._res.updateLong(columnName, x);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateFloat(String columnName, float x) throws SQLException {
      try {
         this._res.updateFloat(columnName, x);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateDouble(String columnName, double x) throws SQLException {
      try {
         this._res.updateDouble(columnName, x);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateBigDecimal(String columnName, BigDecimal x) throws SQLException {
      try {
         this._res.updateBigDecimal(columnName, x);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateString(String columnName, String x) throws SQLException {
      try {
         this._res.updateString(columnName, x);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateBytes(String columnName, byte[] x) throws SQLException {
      try {
         this._res.updateBytes(columnName, x);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateDate(String columnName, Date x) throws SQLException {
      try {
         this._res.updateDate(columnName, x);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateTime(String columnName, Time x) throws SQLException {
      try {
         this._res.updateTime(columnName, x);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateTimestamp(String columnName, Timestamp x) throws SQLException {
      try {
         this._res.updateTimestamp(columnName, x);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateAsciiStream(String columnName, InputStream x, int length) throws SQLException {
      try {
         this._res.updateAsciiStream(columnName, x, length);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateBinaryStream(String columnName, InputStream x, int length) throws SQLException {
      try {
         this._res.updateBinaryStream(columnName, x, length);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateCharacterStream(String columnName, Reader reader, int length) throws SQLException {
      try {
         this._res.updateCharacterStream(columnName, reader, length);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateObject(String columnName, Object x, int scale) throws SQLException {
      try {
         this._res.updateObject(columnName, x);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateObject(String columnName, Object x) throws SQLException {
      try {
         this._res.updateObject(columnName, x);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void insertRow() throws SQLException {
      try {
         this._res.insertRow();
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateRow() throws SQLException {
      try {
         this._res.updateRow();
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void deleteRow() throws SQLException {
      try {
         this._res.deleteRow();
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void refreshRow() throws SQLException {
      try {
         this._res.refreshRow();
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void cancelRowUpdates() throws SQLException {
      try {
         this._res.cancelRowUpdates();
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void moveToInsertRow() throws SQLException {
      try {
         this._res.moveToInsertRow();
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void moveToCurrentRow() throws SQLException {
      try {
         this._res.moveToCurrentRow();
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public Object getObject(int i, Map map) throws SQLException {
      try {
         return this._res.getObject(i, map);
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public Ref getRef(int i) throws SQLException {
      try {
         return this._res.getRef(i);
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public Blob getBlob(int i) throws SQLException {
      try {
         return this._res.getBlob(i);
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public Clob getClob(int i) throws SQLException {
      try {
         return this._res.getClob(i);
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public Array getArray(int i) throws SQLException {
      try {
         return this._res.getArray(i);
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public Object getObject(String colName, Map map) throws SQLException {
      try {
         return this._res.getObject(colName, map);
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public Ref getRef(String colName) throws SQLException {
      try {
         return this._res.getRef(colName);
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public Blob getBlob(String colName) throws SQLException {
      try {
         return this._res.getBlob(colName);
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public Clob getClob(String colName) throws SQLException {
      try {
         return this._res.getClob(colName);
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public Array getArray(String colName) throws SQLException {
      try {
         return this._res.getArray(colName);
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public Date getDate(int columnIndex, Calendar cal) throws SQLException {
      try {
         return this._res.getDate(columnIndex, cal);
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public Date getDate(String columnName, Calendar cal) throws SQLException {
      try {
         return this._res.getDate(columnName, cal);
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public Time getTime(int columnIndex, Calendar cal) throws SQLException {
      try {
         return this._res.getTime(columnIndex, cal);
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public Time getTime(String columnName, Calendar cal) throws SQLException {
      try {
         return this._res.getTime(columnName, cal);
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public Timestamp getTimestamp(int columnIndex, Calendar cal) throws SQLException {
      try {
         return this._res.getTimestamp(columnIndex, cal);
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public Timestamp getTimestamp(String columnName, Calendar cal) throws SQLException {
      try {
         return this._res.getTimestamp(columnName, cal);
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public URL getURL(int columnIndex) throws SQLException {
      try {
         return this._res.getURL(columnIndex);
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public URL getURL(String columnName) throws SQLException {
      try {
         return this._res.getURL(columnName);
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public void updateRef(int columnIndex, Ref x) throws SQLException {
      try {
         this._res.updateRef(columnIndex, x);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateRef(String columnName, Ref x) throws SQLException {
      try {
         this._res.updateRef(columnName, x);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateBlob(int columnIndex, Blob x) throws SQLException {
      try {
         this._res.updateBlob(columnIndex, x);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateBlob(String columnName, Blob x) throws SQLException {
      try {
         this._res.updateBlob(columnName, x);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateClob(int columnIndex, Clob x) throws SQLException {
      try {
         this._res.updateClob(columnIndex, x);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateClob(String columnName, Clob x) throws SQLException {
      try {
         this._res.updateClob(columnName, x);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateArray(int columnIndex, Array x) throws SQLException {
      try {
         this._res.updateArray(columnIndex, x);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateArray(String columnName, Array x) throws SQLException {
      try {
         this._res.updateArray(columnName, x);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public boolean isWrapperFor(Class iface) throws SQLException {
      return iface.isAssignableFrom(this.getClass()) || this._res.isWrapperFor(iface);
   }

   public Object unwrap(Class iface) throws SQLException {
      if (iface.isAssignableFrom(this.getClass())) {
         return iface.cast(this);
      } else {
         return iface.isAssignableFrom(this._res.getClass()) ? iface.cast(this._res) : this._res.unwrap(iface);
      }
   }

   public RowId getRowId(int columnIndex) throws SQLException {
      try {
         return this._res.getRowId(columnIndex);
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public RowId getRowId(String columnLabel) throws SQLException {
      try {
         return this._res.getRowId(columnLabel);
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public void updateRowId(int columnIndex, RowId value) throws SQLException {
      try {
         this._res.updateRowId(columnIndex, value);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateRowId(String columnLabel, RowId value) throws SQLException {
      try {
         this._res.updateRowId(columnLabel, value);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public int getHoldability() throws SQLException {
      try {
         return this._res.getHoldability();
      } catch (SQLException e) {
         this.handleException(e);
         return 0;
      }
   }

   public boolean isClosed() throws SQLException {
      try {
         return this._res.isClosed();
      } catch (SQLException e) {
         this.handleException(e);
         return false;
      }
   }

   public void updateNString(int columnIndex, String value) throws SQLException {
      try {
         this._res.updateNString(columnIndex, value);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateNString(String columnLabel, String value) throws SQLException {
      try {
         this._res.updateNString(columnLabel, value);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateNClob(int columnIndex, NClob value) throws SQLException {
      try {
         this._res.updateNClob(columnIndex, value);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateNClob(String columnLabel, NClob value) throws SQLException {
      try {
         this._res.updateNClob(columnLabel, value);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public NClob getNClob(int columnIndex) throws SQLException {
      try {
         return this._res.getNClob(columnIndex);
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public NClob getNClob(String columnLabel) throws SQLException {
      try {
         return this._res.getNClob(columnLabel);
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public SQLXML getSQLXML(int columnIndex) throws SQLException {
      try {
         return this._res.getSQLXML(columnIndex);
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public SQLXML getSQLXML(String columnLabel) throws SQLException {
      try {
         return this._res.getSQLXML(columnLabel);
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public void updateSQLXML(int columnIndex, SQLXML value) throws SQLException {
      try {
         this._res.updateSQLXML(columnIndex, value);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateSQLXML(String columnLabel, SQLXML value) throws SQLException {
      try {
         this._res.updateSQLXML(columnLabel, value);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public String getNString(int columnIndex) throws SQLException {
      try {
         return this._res.getNString(columnIndex);
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public String getNString(String columnLabel) throws SQLException {
      try {
         return this._res.getNString(columnLabel);
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public Reader getNCharacterStream(int columnIndex) throws SQLException {
      try {
         return this._res.getNCharacterStream(columnIndex);
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public Reader getNCharacterStream(String columnLabel) throws SQLException {
      try {
         return this._res.getNCharacterStream(columnLabel);
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public void updateNCharacterStream(int columnIndex, Reader reader, long length) throws SQLException {
      try {
         this._res.updateNCharacterStream(columnIndex, reader, length);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateNCharacterStream(String columnLabel, Reader reader, long length) throws SQLException {
      try {
         this._res.updateNCharacterStream(columnLabel, reader, length);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateAsciiStream(int columnIndex, InputStream inputStream, long length) throws SQLException {
      try {
         this._res.updateAsciiStream(columnIndex, inputStream, length);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateBinaryStream(int columnIndex, InputStream inputStream, long length) throws SQLException {
      try {
         this._res.updateBinaryStream(columnIndex, inputStream, length);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateCharacterStream(int columnIndex, Reader reader, long length) throws SQLException {
      try {
         this._res.updateCharacterStream(columnIndex, reader, length);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateAsciiStream(String columnLabel, InputStream inputStream, long length) throws SQLException {
      try {
         this._res.updateAsciiStream(columnLabel, inputStream, length);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateBinaryStream(String columnLabel, InputStream inputStream, long length) throws SQLException {
      try {
         this._res.updateBinaryStream(columnLabel, inputStream, length);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateCharacterStream(String columnLabel, Reader reader, long length) throws SQLException {
      try {
         this._res.updateCharacterStream(columnLabel, reader, length);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateBlob(int columnIndex, InputStream inputStream, long length) throws SQLException {
      try {
         this._res.updateBlob(columnIndex, inputStream, length);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateBlob(String columnLabel, InputStream inputStream, long length) throws SQLException {
      try {
         this._res.updateBlob(columnLabel, inputStream, length);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateClob(int columnIndex, Reader reader, long length) throws SQLException {
      try {
         this._res.updateClob(columnIndex, reader, length);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateClob(String columnLabel, Reader reader, long length) throws SQLException {
      try {
         this._res.updateClob(columnLabel, reader, length);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateNClob(int columnIndex, Reader reader, long length) throws SQLException {
      try {
         this._res.updateNClob(columnIndex, reader, length);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateNClob(String columnLabel, Reader reader, long length) throws SQLException {
      try {
         this._res.updateNClob(columnLabel, reader, length);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateNCharacterStream(int columnIndex, Reader reader) throws SQLException {
      try {
         this._res.updateNCharacterStream(columnIndex, reader);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateNCharacterStream(String columnLabel, Reader reader) throws SQLException {
      try {
         this._res.updateNCharacterStream(columnLabel, reader);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateAsciiStream(int columnIndex, InputStream inputStream) throws SQLException {
      try {
         this._res.updateAsciiStream(columnIndex, inputStream);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateBinaryStream(int columnIndex, InputStream inputStream) throws SQLException {
      try {
         this._res.updateBinaryStream(columnIndex, inputStream);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateCharacterStream(int columnIndex, Reader reader) throws SQLException {
      try {
         this._res.updateCharacterStream(columnIndex, reader);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateAsciiStream(String columnLabel, InputStream inputStream) throws SQLException {
      try {
         this._res.updateAsciiStream(columnLabel, inputStream);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateBinaryStream(String columnLabel, InputStream inputStream) throws SQLException {
      try {
         this._res.updateBinaryStream(columnLabel, inputStream);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateCharacterStream(String columnLabel, Reader reader) throws SQLException {
      try {
         this._res.updateCharacterStream(columnLabel, reader);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateBlob(int columnIndex, InputStream inputStream) throws SQLException {
      try {
         this._res.updateBlob(columnIndex, inputStream);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateBlob(String columnLabel, InputStream inputStream) throws SQLException {
      try {
         this._res.updateBlob(columnLabel, inputStream);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateClob(int columnIndex, Reader reader) throws SQLException {
      try {
         this._res.updateClob(columnIndex, reader);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateClob(String columnLabel, Reader reader) throws SQLException {
      try {
         this._res.updateClob(columnLabel, reader);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateNClob(int columnIndex, Reader reader) throws SQLException {
      try {
         this._res.updateNClob(columnIndex, reader);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void updateNClob(String columnLabel, Reader reader) throws SQLException {
      try {
         this._res.updateNClob(columnLabel, reader);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public Object getObject(int columnIndex, Class type) throws SQLException {
      return null;
   }

   public Object getObject(String columnLabel, Class type) throws SQLException {
      return null;
   }
}
