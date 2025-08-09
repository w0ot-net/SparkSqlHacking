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
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;

public abstract class UpdatableVTITemplate implements PreparedStatement {
   protected UpdatableVTITemplate() {
   }

   public ResultSet executeQuery(String var1) throws SQLException {
      throw new SQLException("executeQuery");
   }

   public int executeUpdate(String var1) throws SQLException {
      throw new SQLException("executeUpdate");
   }

   public void close() throws SQLException {
      throw new SQLException("close");
   }

   public SQLWarning getWarnings() throws SQLException {
      throw new SQLException("getWarnings");
   }

   public void clearWarnings() throws SQLException {
      throw new SQLException("clearWarnings");
   }

   public int getMaxFieldSize() throws SQLException {
      throw new SQLException("getMaxFieldSize");
   }

   public void setMaxFieldSize(int var1) throws SQLException {
      throw new SQLException("setMaxFieldSize");
   }

   public int getMaxRows() throws SQLException {
      throw new SQLException("getMaxRows");
   }

   public void setMaxRows(int var1) throws SQLException {
      throw new SQLException("setMaxRows");
   }

   public void setEscapeProcessing(boolean var1) throws SQLException {
      throw new SQLException("setEscapeProcessing");
   }

   public int getQueryTimeout() throws SQLException {
      throw new SQLException("getQueryTimeout");
   }

   public void setQueryTimeout(int var1) throws SQLException {
      throw new SQLException("setQueryTimeout");
   }

   public void addBatch(String var1) throws SQLException {
      throw new SQLException("addBatch");
   }

   public void clearBatch() throws SQLException {
      throw new SQLException("clearBatch");
   }

   public int[] executeBatch() throws SQLException {
      throw new SQLException("executeBatch");
   }

   public void cancel() throws SQLException {
      throw new SQLException("cancel");
   }

   public void setCursorName(String var1) throws SQLException {
      throw new SQLException("setCursorName");
   }

   public boolean execute(String var1) throws SQLException {
      throw new SQLException("execute");
   }

   public ResultSet getResultSet() throws SQLException {
      throw new SQLException("getResultSet");
   }

   public int getUpdateCount() throws SQLException {
      throw new SQLException("getUpdateCount");
   }

   public boolean getMoreResults() throws SQLException {
      throw new SQLException("getMoreResults");
   }

   public int getResultSetConcurrency() throws SQLException {
      return 1008;
   }

   public ResultSet executeQuery() throws SQLException {
      throw new SQLException("executeQuery");
   }

   public int executeUpdate() throws SQLException {
      throw new SQLException("executeUpdate");
   }

   public void setNull(int var1, int var2) throws SQLException {
      throw new SQLException("setNull");
   }

   public void setNull(int var1, int var2, String var3) throws SQLException {
      throw new SQLException("setNull");
   }

   public void setBoolean(int var1, boolean var2) throws SQLException {
      throw new SQLException("setBoolean");
   }

   public void setByte(int var1, byte var2) throws SQLException {
      throw new SQLException("setByte");
   }

   public void setShort(int var1, short var2) throws SQLException {
      throw new SQLException("setShort");
   }

   public void setInt(int var1, int var2) throws SQLException {
      throw new SQLException("setInt");
   }

   public void setLong(int var1, long var2) throws SQLException {
      throw new SQLException("setLong");
   }

   public void setFloat(int var1, float var2) throws SQLException {
      throw new SQLException("setFloat");
   }

   public void setDouble(int var1, double var2) throws SQLException {
      throw new SQLException("setDouble");
   }

   public void setBigDecimal(int var1, BigDecimal var2) throws SQLException {
      throw new SQLException("setBigDecimal");
   }

   public void setString(int var1, String var2) throws SQLException {
      throw new SQLException("setString");
   }

   public void setBytes(int var1, byte[] var2) throws SQLException {
      throw new SQLException("setBytes");
   }

   public void setDate(int var1, Date var2) throws SQLException {
      throw new SQLException("setDate");
   }

   public void setTime(int var1, Time var2) throws SQLException {
      throw new SQLException("setTime");
   }

   public void setTimestamp(int var1, Timestamp var2) throws SQLException {
      throw new SQLException("setTimestamp");
   }

   public void setAsciiStream(int var1, InputStream var2, int var3) throws SQLException {
      throw new SQLException("setAsciiStream");
   }

   /** @deprecated */
   public void setUnicodeStream(int var1, InputStream var2, int var3) throws SQLException {
      throw new SQLException("setUnicodeStream");
   }

   public void setBinaryStream(int var1, InputStream var2, int var3) throws SQLException {
      throw new SQLException("setBinaryStream");
   }

   public void clearParameters() throws SQLException {
      throw new SQLException("clearParameters");
   }

   public void setObject(int var1, Object var2, int var3, int var4) throws SQLException {
      throw new SQLException("setObject");
   }

   public void setObject(int var1, Object var2, int var3) throws SQLException {
      throw new SQLException("setObject");
   }

   public void setObject(int var1, Object var2) throws SQLException {
      throw new SQLException("setObject");
   }

   public boolean execute() throws SQLException {
      throw new SQLException("execute");
   }

   public ResultSetMetaData getMetaData() throws SQLException {
      throw new SQLException("ResultSetMetaData");
   }

   public int getResultSetType() throws SQLException {
      throw new SQLException("getResultSetType");
   }

   public void setBlob(int var1, Blob var2) throws SQLException {
      throw new SQLException("setBlob");
   }

   public void setFetchDirection(int var1) throws SQLException {
      throw new SQLException("setFetchDirection");
   }

   public void setFetchSize(int var1) throws SQLException {
      throw new SQLException("setFetchSize");
   }

   public void addBatch() throws SQLException {
      throw new SQLException("addBatch");
   }

   public void setCharacterStream(int var1, Reader var2, int var3) throws SQLException {
      throw new SQLException("setCharacterStream");
   }

   public Connection getConnection() throws SQLException {
      throw new SQLException("getConnection");
   }

   public int getFetchDirection() throws SQLException {
      throw new SQLException("getFetchDirection");
   }

   public void setTime(int var1, Time var2, Calendar var3) throws SQLException {
      throw new SQLException("setTime");
   }

   public void setTimestamp(int var1, Timestamp var2, Calendar var3) throws SQLException {
      throw new SQLException("setTimestamp");
   }

   public int getFetchSize() throws SQLException {
      throw new SQLException("getFetchSize");
   }

   public void setRef(int var1, Ref var2) throws SQLException {
      throw new SQLException("setRef");
   }

   public void setDate(int var1, Date var2, Calendar var3) throws SQLException {
      throw new SQLException("setDate");
   }

   public void setClob(int var1, Clob var2) throws SQLException {
      throw new SQLException("setClob");
   }

   public void setArray(int var1, Array var2) throws SQLException {
      throw new SQLException("setArray");
   }

   public void setURL(int var1, URL var2) throws SQLException {
      throw new SQLException("setURL");
   }

   public boolean getMoreResults(int var1) throws SQLException {
      throw new SQLException("getMoreResults");
   }

   public ResultSet getGeneratedKeys() throws SQLException {
      throw new SQLException("getGeneratedKeys");
   }

   public int executeUpdate(String var1, int var2) throws SQLException {
      throw new SQLException("executeUpdate");
   }

   public int executeUpdate(String var1, int[] var2) throws SQLException {
      throw new SQLException("executeUpdate");
   }

   public int executeUpdate(String var1, String[] var2) throws SQLException {
      throw new SQLException("executeUpdate");
   }

   public boolean execute(String var1, int var2) throws SQLException {
      throw new SQLException("execute");
   }

   public boolean execute(String var1, int[] var2) throws SQLException {
      throw new SQLException("execute");
   }

   public boolean execute(String var1, String[] var2) throws SQLException {
      throw new SQLException("execute");
   }

   public int getResultSetHoldability() throws SQLException {
      throw new SQLException("getResultSetHoldability");
   }

   public ParameterMetaData getParameterMetaData() throws SQLException {
      throw new SQLException("getParameterMetaData");
   }

   public boolean isWrapperFor(Class var1) throws SQLException {
      throw new SQLException("isWrapperFor");
   }

   public Object unwrap(Class var1) throws SQLException {
      throw new SQLException("unwrap");
   }

   public boolean isClosed() throws SQLException {
      throw new SQLException("isClosed");
   }

   public boolean isPoolable() throws SQLException {
      throw new SQLException("isPoolable");
   }

   public void setPoolable(boolean var1) throws SQLException {
      throw new SQLException("setPoolable");
   }

   public void setAsciiStream(int var1, InputStream var2) throws SQLException {
      throw new SQLException("setAsciiStream");
   }

   public void setAsciiStream(int var1, InputStream var2, long var3) throws SQLException {
      throw new SQLException("setAsciiStream");
   }

   public void setBinaryStream(int var1, InputStream var2) throws SQLException {
      throw new SQLException("setBinaryStream");
   }

   public void setBinaryStream(int var1, InputStream var2, long var3) throws SQLException {
      throw new SQLException("setBinaryStream");
   }

   public void setBlob(int var1, InputStream var2) throws SQLException {
      throw new SQLException("setBlob");
   }

   public void setBlob(int var1, InputStream var2, long var3) throws SQLException {
      throw new SQLException("setBlob");
   }

   public void setClob(int var1, Reader var2) throws SQLException {
      throw new SQLException("setClob");
   }

   public void setClob(int var1, Reader var2, long var3) throws SQLException {
      throw new SQLException("setClob");
   }

   public void setCharacterStream(int var1, Reader var2) throws SQLException {
      throw new SQLException("setCharacterStream");
   }

   public void setCharacterStream(int var1, Reader var2, long var3) throws SQLException {
      throw new SQLException("setCharacterStream");
   }

   public void setNCharacterStream(int var1, Reader var2) throws SQLException {
      throw new SQLException("setNCharacterStream");
   }

   public void setNCharacterStream(int var1, Reader var2, long var3) throws SQLException {
      throw new SQLException("setNCharacterStream");
   }

   public void setNClob(int var1, NClob var2) throws SQLException {
      throw new SQLException("setNClob");
   }

   public void setNClob(int var1, Reader var2) throws SQLException {
      throw new SQLException("setNClob");
   }

   public void setNClob(int var1, Reader var2, long var3) throws SQLException {
      throw new SQLException("setNClob");
   }

   public void setNString(int var1, String var2) throws SQLException {
      throw new SQLException("setNString");
   }

   public void setRowId(int var1, RowId var2) throws SQLException {
      throw new SQLException("setRowId");
   }

   public void setSQLXML(int var1, SQLXML var2) throws SQLException {
      throw new SQLException("setSQLXML");
   }

   public boolean isCloseOnCompletion() throws SQLException {
      throw new SQLException("isCloseOnCompletion");
   }

   public void closeOnCompletion() throws SQLException {
      throw new SQLException("closeOnCompletion");
   }
}
