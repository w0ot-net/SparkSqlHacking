package org.apache.hive.jdbc;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;

public class HiveCallableStatement implements CallableStatement {
   private final Connection connection;

   public HiveCallableStatement(Connection connection) {
      this.connection = connection;
   }

   public Array getArray(int i) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public Array getArray(String parameterName) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public BigDecimal getBigDecimal(int parameterIndex) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public BigDecimal getBigDecimal(String parameterName) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public BigDecimal getBigDecimal(int parameterIndex, int scale) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public Blob getBlob(int i) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public Blob getBlob(String parameterName) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean getBoolean(int parameterIndex) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean getBoolean(String parameterName) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public byte getByte(int parameterIndex) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public byte getByte(String parameterName) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public byte[] getBytes(int parameterIndex) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public byte[] getBytes(String parameterName) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public Reader getCharacterStream(int arg0) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public Reader getCharacterStream(String arg0) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public Clob getClob(int i) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public Clob getClob(String parameterName) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public Date getDate(int parameterIndex) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public Date getDate(String parameterName) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public Date getDate(int parameterIndex, Calendar cal) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public Date getDate(String parameterName, Calendar cal) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public double getDouble(int parameterIndex) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public double getDouble(String parameterName) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public float getFloat(int parameterIndex) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public float getFloat(String parameterName) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public int getInt(int parameterIndex) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public int getInt(String parameterName) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public long getLong(int parameterIndex) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public long getLong(String parameterName) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public Reader getNCharacterStream(int parameterIndex) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public Reader getNCharacterStream(String parameterName) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public NClob getNClob(int parameterIndex) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public NClob getNClob(String parameterName) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public String getNString(int parameterIndex) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public String getNString(String parameterName) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public Object getObject(int parameterIndex) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public Object getObject(String parameterName) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public Object getObject(int parameterIndex, Class type) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public Object getObject(String parameterName, Class type) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public Object getObject(int i, Map map) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public Object getObject(String parameterName, Map map) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public Ref getRef(int i) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public Ref getRef(String parameterName) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public RowId getRowId(int parameterIndex) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public RowId getRowId(String parameterName) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public SQLXML getSQLXML(int parameterIndex) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public SQLXML getSQLXML(String parameterName) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public short getShort(int parameterIndex) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public short getShort(String parameterName) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public String getString(int parameterIndex) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public String getString(String parameterName) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public Time getTime(int parameterIndex) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public Time getTime(String parameterName) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public Time getTime(int parameterIndex, Calendar cal) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public Time getTime(String parameterName, Calendar cal) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public Timestamp getTimestamp(int parameterIndex) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public Timestamp getTimestamp(String parameterName) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public Timestamp getTimestamp(int parameterIndex, Calendar cal) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public Timestamp getTimestamp(String parameterName, Calendar cal) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public URL getURL(int parameterIndex) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public URL getURL(String parameterName) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void registerOutParameter(int parameterIndex, int sqlType) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void registerOutParameter(String parameterName, int sqlType) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void registerOutParameter(int parameterIndex, int sqlType, int scale) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void registerOutParameter(int paramIndex, int sqlType, String typeName) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void registerOutParameter(String parameterName, int sqlType, int scale) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void registerOutParameter(String parameterName, int sqlType, String typeName) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setAsciiStream(String parameterName, InputStream x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setAsciiStream(String parameterName, InputStream x, int length) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setAsciiStream(String parameterName, InputStream x, long length) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setBigDecimal(String parameterName, BigDecimal x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setBinaryStream(String parameterName, InputStream x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setBinaryStream(String parameterName, InputStream x, int length) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setBinaryStream(String parameterName, InputStream x, long length) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setBlob(String parameterName, Blob x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setBlob(String parameterName, InputStream inputStream) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setBlob(String parameterName, InputStream inputStream, long length) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setBoolean(String parameterName, boolean x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setByte(String parameterName, byte x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setBytes(String parameterName, byte[] x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setCharacterStream(String parameterName, Reader reader) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setCharacterStream(String parameterName, Reader reader, int length) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setCharacterStream(String parameterName, Reader reader, long length) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setClob(String parameterName, Clob x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setClob(String parameterName, Reader reader) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setClob(String parameterName, Reader reader, long length) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setDate(String parameterName, Date x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setDate(String parameterName, Date x, Calendar cal) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setDouble(String parameterName, double x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setFloat(String parameterName, float x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setInt(String parameterName, int x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setLong(String parameterName, long x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setNCharacterStream(String parameterName, Reader value) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setNCharacterStream(String parameterName, Reader value, long length) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setNClob(String parameterName, NClob value) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setNClob(String parameterName, Reader reader) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setNClob(String parameterName, Reader reader, long length) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setNString(String parameterName, String value) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setNull(String parameterName, int sqlType) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setNull(String parameterName, int sqlType, String typeName) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setObject(String parameterName, Object x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setObject(String parameterName, Object x, int targetSqlType) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setObject(String parameterName, Object x, int targetSqlType, int scale) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setRowId(String parameterName, RowId x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setSQLXML(String parameterName, SQLXML xmlObject) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setShort(String parameterName, short x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setString(String parameterName, String x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setTime(String parameterName, Time x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setTime(String parameterName, Time x, Calendar cal) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setTimestamp(String parameterName, Timestamp x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setTimestamp(String parameterName, Timestamp x, Calendar cal) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setURL(String parameterName, URL val) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean wasNull() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void addBatch() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void clearParameters() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean execute() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public ResultSet executeQuery() throws SQLException {
      return (new HiveQueryResultSet.Builder(this)).build();
   }

   public int executeUpdate() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public ResultSetMetaData getMetaData() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public ParameterMetaData getParameterMetaData() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setArray(int i, Array x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setAsciiStream(int arg0, InputStream arg1) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setAsciiStream(int parameterIndex, InputStream x, int length) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setAsciiStream(int arg0, InputStream arg1, long arg2) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setBigDecimal(int parameterIndex, BigDecimal x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setBinaryStream(int parameterIndex, InputStream x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setBinaryStream(int parameterIndex, InputStream x, long length) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setBlob(int i, Blob x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setBlob(int parameterIndex, InputStream inputStream) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setBlob(int parameterIndex, InputStream inputStream, long length) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setBoolean(int parameterIndex, boolean x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setByte(int parameterIndex, byte x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setBytes(int parameterIndex, byte[] x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setCharacterStream(int parameterIndex, Reader reader) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setCharacterStream(int parameterIndex, Reader reader, int length) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setCharacterStream(int parameterIndex, Reader reader, long length) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setClob(int i, Clob x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setClob(int parameterIndex, Reader reader) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setClob(int parameterIndex, Reader reader, long length) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setDate(int parameterIndex, Date x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setDate(int parameterIndex, Date x, Calendar cal) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setDouble(int parameterIndex, double x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setFloat(int parameterIndex, float x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setInt(int parameterIndex, int x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setLong(int parameterIndex, long x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setNCharacterStream(int parameterIndex, Reader value) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setNCharacterStream(int parameterIndex, Reader value, long length) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setNClob(int parameterIndex, NClob value) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setNClob(int parameterIndex, Reader reader) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setNClob(int parameterIndex, Reader reader, long length) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setNString(int parameterIndex, String value) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setNull(int parameterIndex, int sqlType) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setNull(int paramIndex, int sqlType, String typeName) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setObject(int parameterIndex, Object x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setObject(int parameterIndex, Object x, int targetSqlType) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setObject(int parameterIndex, Object x, int targetSqlType, int scale) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setRef(int i, Ref x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setRowId(int parameterIndex, RowId x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setSQLXML(int parameterIndex, SQLXML xmlObject) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setShort(int parameterIndex, short x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setString(int parameterIndex, String x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setTime(int parameterIndex, Time x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setTime(int parameterIndex, Time x, Calendar cal) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setTimestamp(int parameterIndex, Timestamp x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setURL(int parameterIndex, URL x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setUnicodeStream(int parameterIndex, InputStream x, int length) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void addBatch(String sql) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void cancel() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void clearBatch() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void clearWarnings() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void close() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void closeOnCompletion() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean isCloseOnCompletion() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean execute(String sql) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean execute(String sql, int[] columnIndexes) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean execute(String sql, String[] columnNames) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public int[] executeBatch() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public ResultSet executeQuery(String sql) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public int executeUpdate(String sql) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public int executeUpdate(String sql, String[] columnNames) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public Connection getConnection() throws SQLException {
      return this.connection;
   }

   public int getFetchDirection() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public int getFetchSize() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public ResultSet getGeneratedKeys() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public int getMaxFieldSize() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public int getMaxRows() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean getMoreResults() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean getMoreResults(int current) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public int getQueryTimeout() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public ResultSet getResultSet() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public int getResultSetConcurrency() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public int getResultSetHoldability() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public int getResultSetType() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public int getUpdateCount() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public SQLWarning getWarnings() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean isClosed() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean isPoolable() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setCursorName(String name) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setEscapeProcessing(boolean enable) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setFetchDirection(int direction) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setFetchSize(int rows) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setMaxFieldSize(int max) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setMaxRows(int max) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setPoolable(boolean arg0) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setQueryTimeout(int seconds) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean isWrapperFor(Class iface) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public Object unwrap(Class iface) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }
}
