package org.datanucleus.store.rdbms;

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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import org.datanucleus.exceptions.NucleusUserException;

class ParamLoggingPreparedStatement implements PreparedStatement {
   private final PreparedStatement ps;
   private SubStatement currentStatement = null;
   private List subStatements = null;
   private boolean paramAngleBrackets = true;
   private static final String DN_UNPRINTABLE = "DN_UNPRINTABLE";

   public ParamLoggingPreparedStatement(PreparedStatement ps, String jdbcSql) {
      this.ps = ps;
      this.currentStatement = new SubStatement(jdbcSql);
   }

   public void setParamsInAngleBrackets(boolean flag) {
      this.paramAngleBrackets = flag;
   }

   public String getStatementWithParamsReplaced() {
      StringBuilder statementWithParams = new StringBuilder();
      if (this.subStatements == null) {
         return this.getStatementWithParamsReplacedForSubStatement(this.currentStatement);
      } else {
         statementWithParams.append("BATCH [");
         Iterator iter = this.subStatements.iterator();

         while(iter.hasNext()) {
            SubStatement stParams = (SubStatement)iter.next();
            String stmt = this.getStatementWithParamsReplacedForSubStatement(stParams);
            statementWithParams.append(stmt);
            if (iter.hasNext()) {
               statementWithParams.append("; ");
            }
         }

         statementWithParams.append("]");
         return statementWithParams.toString();
      }
   }

   private String getStatementWithParamsReplacedForSubStatement(SubStatement stParams) {
      StringBuilder statementWithParams = new StringBuilder();
      StringTokenizer tokenizer = new StringTokenizer(stParams.statementText, "?", true);
      int i = 1;

      while(tokenizer.hasMoreTokens()) {
         String token = tokenizer.nextToken();
         if (token.equals("?")) {
            Object paramValue = "DN_UNPRINTABLE";
            Integer paramPos = i++;
            if (stParams.parameters.containsKey(paramPos)) {
               paramValue = stParams.parameters.get(paramPos);
            }

            this.appendParamValue(statementWithParams, paramValue);
         } else {
            statementWithParams.append(token);
         }
      }

      if (i > 1) {
         return statementWithParams.toString();
      } else {
         return stParams.statementText;
      }
   }

   private void appendParamValue(StringBuilder statementWithParams, Object paramValue) {
      if (this.paramAngleBrackets) {
         if (paramValue instanceof String) {
            if (paramValue.equals("DN_UNPRINTABLE")) {
               statementWithParams.append("<UNPRINTABLE>");
            } else {
               statementWithParams.append("<'" + paramValue + "'>");
            }
         } else {
            statementWithParams.append("<" + paramValue + ">");
         }
      } else if (paramValue instanceof String) {
         if (paramValue.equals("DN_UNPRINTABLE")) {
            statementWithParams.append("<UNPRINTABLE'>");
         } else {
            statementWithParams.append("'" + paramValue + "'");
         }
      } else {
         statementWithParams.append("" + paramValue);
      }

   }

   private void setParameter(int i, Object p) {
      this.currentStatement.parameters.put(i, p);
   }

   public Object getParameter(int i) {
      return this.currentStatement.parameters.get(i);
   }

   public void addBatch() throws SQLException {
      SubStatement newSubStmt = new SubStatement(this.currentStatement.statementText);
      newSubStmt.parameters.putAll(this.currentStatement.parameters);
      if (this.subStatements == null) {
         this.subStatements = new ArrayList();
      }

      this.subStatements.add(newSubStmt);
      this.ps.addBatch();
   }

   public void addBatch(String sql) throws SQLException {
      SubStatement newSubStmt = new SubStatement(sql);
      newSubStmt.parameters.putAll(this.currentStatement.parameters);
      if (this.subStatements == null) {
         this.subStatements = new ArrayList();
      }

      this.subStatements.add(newSubStmt);
      this.ps.addBatch(sql);
   }

   public void cancel() throws SQLException {
      this.ps.cancel();
   }

   public void clearBatch() throws SQLException {
      if (this.subStatements != null) {
         this.subStatements.clear();
      }

      this.ps.clearBatch();
   }

   public void clearParameters() throws SQLException {
      this.currentStatement.parameters.clear();
      if (this.subStatements != null) {
         for(SubStatement subStmt : this.subStatements) {
            subStmt.parameters.clear();
         }
      }

      this.ps.clearParameters();
   }

   public void clearWarnings() throws SQLException {
      this.ps.clearWarnings();
   }

   public void close() throws SQLException {
      this.ps.close();
   }

   public boolean execute() throws SQLException {
      return this.ps.execute();
   }

   public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
      return this.ps.execute(sql, autoGeneratedKeys);
   }

   public boolean execute(String sql, int[] columnIndexes) throws SQLException {
      return this.ps.execute(sql, columnIndexes);
   }

   public boolean execute(String sql, String[] columnNames) throws SQLException {
      return this.ps.execute(sql, columnNames);
   }

   public boolean execute(String sql) throws SQLException {
      return this.ps.execute(sql);
   }

   public int[] executeBatch() throws SQLException {
      return this.ps.executeBatch();
   }

   public ResultSet executeQuery() throws SQLException {
      return this.ps.executeQuery();
   }

   public ResultSet executeQuery(String sql) throws SQLException {
      return this.ps.executeQuery(sql);
   }

   public int executeUpdate() throws SQLException {
      return this.ps.executeUpdate();
   }

   public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
      return this.ps.executeUpdate(sql, autoGeneratedKeys);
   }

   public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
      return this.ps.executeUpdate(sql, columnIndexes);
   }

   public int executeUpdate(String sql, String[] columnNames) throws SQLException {
      return this.ps.executeUpdate(sql, columnNames);
   }

   public int executeUpdate(String sql) throws SQLException {
      return this.ps.executeUpdate(sql);
   }

   public Connection getConnection() throws SQLException {
      return this.ps.getConnection();
   }

   public int getFetchDirection() throws SQLException {
      return this.ps.getFetchDirection();
   }

   public int getFetchSize() throws SQLException {
      return this.ps.getFetchSize();
   }

   public ResultSet getGeneratedKeys() throws SQLException {
      return this.ps.getGeneratedKeys();
   }

   public int getMaxFieldSize() throws SQLException {
      return this.ps.getMaxFieldSize();
   }

   public int getMaxRows() throws SQLException {
      return this.ps.getMaxRows();
   }

   public ResultSetMetaData getMetaData() throws SQLException {
      return this.ps.getMetaData();
   }

   public boolean getMoreResults() throws SQLException {
      return this.ps.getMoreResults();
   }

   public boolean getMoreResults(int current) throws SQLException {
      return this.ps.getMoreResults(current);
   }

   public ParameterMetaData getParameterMetaData() throws SQLException {
      return this.ps.getParameterMetaData();
   }

   public int getQueryTimeout() throws SQLException {
      return this.ps.getQueryTimeout();
   }

   public ResultSet getResultSet() throws SQLException {
      return this.ps.getResultSet();
   }

   public int getResultSetConcurrency() throws SQLException {
      return this.ps.getResultSetConcurrency();
   }

   public int getResultSetHoldability() throws SQLException {
      return this.ps.getResultSetHoldability();
   }

   public int getResultSetType() throws SQLException {
      return this.ps.getResultSetType();
   }

   public int getUpdateCount() throws SQLException {
      return this.ps.getUpdateCount();
   }

   public SQLWarning getWarnings() throws SQLException {
      return this.ps.getWarnings();
   }

   public void setArray(int i, Array x) throws SQLException {
      this.setParameter(i, x);
      this.ps.setArray(i, x);
   }

   public void setAsciiStream(int parameterIndex, InputStream x, int length) throws SQLException {
      this.ps.setAsciiStream(parameterIndex, x, length);
   }

   public void setBigDecimal(int parameterIndex, BigDecimal x) throws SQLException {
      this.setParameter(parameterIndex, x);
      this.ps.setBigDecimal(parameterIndex, x);
   }

   public void setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException {
      this.ps.setBinaryStream(parameterIndex, x, length);
   }

   public void setBlob(int i, Blob x) throws SQLException {
      this.ps.setBlob(i, x);
   }

   public void setBoolean(int parameterIndex, boolean x) throws SQLException {
      this.setParameter(parameterIndex, x);
      this.ps.setBoolean(parameterIndex, x);
   }

   public void setByte(int parameterIndex, byte x) throws SQLException {
      this.setParameter(parameterIndex, x);
      this.ps.setByte(parameterIndex, x);
   }

   public void setBytes(int parameterIndex, byte[] x) throws SQLException {
      this.ps.setBytes(parameterIndex, x);
   }

   public void setCharacterStream(int parameterIndex, Reader reader, int length) throws SQLException {
      this.ps.setCharacterStream(parameterIndex, reader, length);
   }

   public void setClob(int i, Clob x) throws SQLException {
      this.ps.setClob(i, x);
   }

   public void setCursorName(String name) throws SQLException {
      this.ps.setCursorName(name);
   }

   public void setDate(int parameterIndex, Date x, Calendar cal) throws SQLException {
      this.setParameter(parameterIndex, x);
      this.ps.setDate(parameterIndex, x, cal);
   }

   public void setDate(int parameterIndex, Date x) throws SQLException {
      this.setParameter(parameterIndex, x);
      this.ps.setDate(parameterIndex, x);
   }

   public void setDouble(int parameterIndex, double x) throws SQLException {
      this.setParameter(parameterIndex, x);
      this.ps.setDouble(parameterIndex, x);
   }

   public void setEscapeProcessing(boolean enable) throws SQLException {
      this.ps.setEscapeProcessing(enable);
   }

   public void setFetchDirection(int direction) throws SQLException {
      this.ps.setFetchDirection(direction);
   }

   public void setFetchSize(int rows) throws SQLException {
      this.ps.setFetchSize(rows);
   }

   public void setFloat(int parameterIndex, float x) throws SQLException {
      this.setParameter(parameterIndex, x);
      this.ps.setFloat(parameterIndex, x);
   }

   public void setInt(int parameterIndex, int x) throws SQLException {
      this.setParameter(parameterIndex, x);
      this.ps.setInt(parameterIndex, x);
   }

   public void setLong(int parameterIndex, long x) throws SQLException {
      this.setParameter(parameterIndex, x);
      this.ps.setLong(parameterIndex, x);
   }

   public void setMaxFieldSize(int max) throws SQLException {
      this.ps.setMaxFieldSize(max);
   }

   public void setMaxRows(int max) throws SQLException {
      this.ps.setMaxRows(max);
   }

   public void setNull(int paramIndex, int sqlType, String typeName) throws SQLException {
      this.setParameter(paramIndex, (Object)null);
      this.ps.setNull(paramIndex, sqlType, typeName);
   }

   public void setNull(int parameterIndex, int sqlType) throws SQLException {
      this.setParameter(parameterIndex, (Object)null);
      this.ps.setNull(parameterIndex, sqlType);
   }

   public void setObject(int parameterIndex, Object x, int targetSqlType, int scale) throws SQLException {
      this.setParameter(parameterIndex, x);
      this.ps.setObject(parameterIndex, x, targetSqlType, scale);
   }

   public void setObject(int parameterIndex, Object x, int targetSqlType) throws SQLException {
      this.setParameter(parameterIndex, x);
      this.ps.setObject(parameterIndex, x, targetSqlType);
   }

   public void setObject(int parameterIndex, Object x) throws SQLException {
      this.setParameter(parameterIndex, x);
      this.ps.setObject(parameterIndex, x);
   }

   public void setQueryTimeout(int seconds) throws SQLException {
      this.ps.setQueryTimeout(seconds);
   }

   public void setRef(int i, Ref x) throws SQLException {
      this.setParameter(i, x);
      this.ps.setRef(i, x);
   }

   public void setShort(int parameterIndex, short x) throws SQLException {
      this.setParameter(parameterIndex, x);
      this.ps.setShort(parameterIndex, x);
   }

   public void setString(int parameterIndex, String x) throws SQLException {
      this.setParameter(parameterIndex, x);
      this.ps.setString(parameterIndex, x);
   }

   public void setTime(int parameterIndex, Time x, Calendar cal) throws SQLException {
      this.setParameter(parameterIndex, x);
      this.ps.setTime(parameterIndex, x, cal);
   }

   public void setTime(int parameterIndex, Time x) throws SQLException {
      this.setParameter(parameterIndex, x);
      this.ps.setTime(parameterIndex, x);
   }

   public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal) throws SQLException {
      this.setParameter(parameterIndex, x);
      this.ps.setTimestamp(parameterIndex, x, cal);
   }

   public void setTimestamp(int parameterIndex, Timestamp x) throws SQLException {
      this.setParameter(parameterIndex, x);
      this.ps.setTimestamp(parameterIndex, x);
   }

   public void setUnicodeStream(int parameterIndex, InputStream x, int length) throws SQLException {
      throw new NucleusUserException("Not supported");
   }

   public void setURL(int parameterIndex, URL x) throws SQLException {
      this.setParameter(parameterIndex, x);
      this.ps.setURL(parameterIndex, x);
   }

   public void setAsciiStream(int parameterIndex, InputStream x, long length) throws SQLException {
      this.ps.setAsciiStream(parameterIndex, x, length);
   }

   public void setAsciiStream(int parameterIndex, InputStream x) throws SQLException {
      this.ps.setAsciiStream(parameterIndex, x);
   }

   public void setBinaryStream(int parameterIndex, InputStream x, long length) throws SQLException {
      this.ps.setBinaryStream(parameterIndex, x, length);
   }

   public void setBinaryStream(int parameterIndex, InputStream x) throws SQLException {
      this.ps.setBinaryStream(parameterIndex, x);
   }

   public void setBlob(int parameterIndex, InputStream inputStream, long length) throws SQLException {
      this.ps.setBlob(parameterIndex, inputStream, length);
   }

   public void setBlob(int parameterIndex, InputStream inputStream) throws SQLException {
      this.ps.setBlob(parameterIndex, inputStream);
   }

   public void setCharacterStream(int parameterIndex, Reader reader, long length) throws SQLException {
      this.ps.setCharacterStream(parameterIndex, reader, length);
   }

   public void setCharacterStream(int parameterIndex, Reader reader) throws SQLException {
      this.ps.setCharacterStream(parameterIndex, reader);
   }

   public void setClob(int parameterIndex, Reader reader, long length) throws SQLException {
      this.ps.setClob(parameterIndex, reader, length);
   }

   public void setClob(int parameterIndex, Reader reader) throws SQLException {
      this.ps.setClob(parameterIndex, reader);
   }

   public void setNCharacterStream(int parameterIndex, Reader value, long length) throws SQLException {
      this.ps.setNCharacterStream(parameterIndex, value, length);
   }

   public void setNCharacterStream(int parameterIndex, Reader value) throws SQLException {
      this.ps.setNCharacterStream(parameterIndex, value);
   }

   public void setNString(int parameterIndex, String value) throws SQLException {
      this.setParameter(parameterIndex, value);
      this.ps.setNString(parameterIndex, value);
   }

   public boolean isClosed() throws SQLException {
      return this.ps.isClosed();
   }

   public boolean isPoolable() throws SQLException {
      return this.ps.isPoolable();
   }

   public void setPoolable(boolean poolable) throws SQLException {
      this.ps.setPoolable(poolable);
   }

   public void setNClob(int parameterIndex, NClob value) throws SQLException {
      this.ps.setNClob(parameterIndex, value);
   }

   public void setNClob(int parameterIndex, Reader reader, long length) throws SQLException {
      this.ps.setNClob(parameterIndex, reader, length);
   }

   public void setNClob(int parameterIndex, Reader reader) throws SQLException {
      this.ps.setNClob(parameterIndex, reader);
   }

   public void setRowId(int parameterIndex, RowId x) throws SQLException {
      this.ps.setRowId(parameterIndex, x);
   }

   public void setSQLXML(int parameterIndex, SQLXML xmlObject) throws SQLException {
      this.ps.setSQLXML(parameterIndex, xmlObject);
   }

   public boolean isWrapperFor(Class iface) throws SQLException {
      return PreparedStatement.class.equals(iface);
   }

   public Object unwrap(Class iface) throws SQLException {
      if (!PreparedStatement.class.equals(iface)) {
         throw new SQLException("PreparedStatement of type [" + this.getClass().getName() + "] can only be unwrapped as [java.sql.PreparedStatement], not as [" + iface.getName() + "]");
      } else {
         return this;
      }
   }

   public void closeOnCompletion() throws SQLException {
      this.ps.closeOnCompletion();
   }

   public boolean isCloseOnCompletion() throws SQLException {
      return this.ps.isCloseOnCompletion();
   }

   static class SubStatement {
      public final Map parameters = new HashMap();
      public final String statementText;

      public SubStatement(String statementText) {
         this.statementText = statementText;
      }
   }
}
