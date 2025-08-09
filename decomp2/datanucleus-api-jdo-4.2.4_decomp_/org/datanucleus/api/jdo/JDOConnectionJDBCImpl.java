package org.datanucleus.api.jdo;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;
import org.datanucleus.store.NucleusConnection;

public class JDOConnectionJDBCImpl extends JDOConnectionImpl implements Connection {
   private final Connection conn;
   private boolean isAvailable = true;

   public JDOConnectionJDBCImpl(NucleusConnection nconn) {
      super(nconn);
      this.conn = (Connection)nconn.getNativeConnection();
   }

   public boolean isAvailable() {
      return this.nucConn.isAvailable();
   }

   public int getHoldability() throws SQLException {
      this.assertAvailable();
      return this.conn.getHoldability();
   }

   public int getTransactionIsolation() throws SQLException {
      this.assertAvailable();
      return this.conn.getTransactionIsolation();
   }

   public void clearWarnings() throws SQLException {
      this.assertAvailable();
      this.conn.clearWarnings();
   }

   public void commit() throws SQLException {
      super.throwExceptionUnsupportedOperation("commit");
   }

   public void rollback() throws SQLException {
      super.throwExceptionUnsupportedOperation("rollback");
   }

   public boolean getAutoCommit() throws SQLException {
      this.assertAvailable();
      return this.conn.getAutoCommit();
   }

   public boolean isClosed() throws SQLException {
      return this.nucConn.isAvailable() ? this.conn.isClosed() : true;
   }

   public boolean isReadOnly() throws SQLException {
      this.assertAvailable();
      return this.conn.isReadOnly();
   }

   public void setHoldability(int holdability) throws SQLException {
      super.throwExceptionUnsupportedOperation("setHoldability");
   }

   public void setTransactionIsolation(int level) throws SQLException {
      super.throwExceptionUnsupportedOperation("setTransactionIsolation");
   }

   public void setAutoCommit(boolean autoCommit) throws SQLException {
      super.throwExceptionUnsupportedOperation("setAutoCommit");
   }

   public void setReadOnly(boolean readOnly) throws SQLException {
      super.throwExceptionUnsupportedOperation("setReadOnly");
   }

   public String getCatalog() throws SQLException {
      this.assertAvailable();
      return this.conn.getCatalog();
   }

   public void setCatalog(String catalog) throws SQLException {
      super.throwExceptionUnsupportedOperation("setCatalog");
   }

   public DatabaseMetaData getMetaData() throws SQLException {
      super.throwExceptionUnsupportedOperation("getMetaData");
      return null;
   }

   public SQLWarning getWarnings() throws SQLException {
      this.assertAvailable();
      return this.conn.getWarnings();
   }

   public Savepoint setSavepoint() throws SQLException {
      super.throwExceptionUnsupportedOperation("setSavepoint");
      return null;
   }

   public void releaseSavepoint(Savepoint pt) throws SQLException {
      super.throwExceptionUnsupportedOperation("releaseSavepoint");
   }

   public void rollback(Savepoint pt) throws SQLException {
      super.throwExceptionUnsupportedOperation("rollback");
   }

   public Statement createStatement() throws SQLException {
      this.assertAvailable();
      return this.conn.createStatement();
   }

   public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
      this.assertAvailable();
      return this.conn.createStatement(resultSetType, resultSetConcurrency);
   }

   public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
      this.assertAvailable();
      return this.conn.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
   }

   public Map getTypeMap() throws SQLException {
      this.assertAvailable();
      return this.conn.getTypeMap();
   }

   public void setTypeMap(Map map) throws SQLException {
      super.throwExceptionUnsupportedOperation("setTypeMap");
   }

   public String nativeSQL(String sql) throws SQLException {
      this.assertAvailable();
      return this.conn.nativeSQL(sql);
   }

   public CallableStatement prepareCall(String sql) throws SQLException {
      this.assertAvailable();
      return this.conn.prepareCall(sql);
   }

   public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
      this.assertAvailable();
      return this.conn.prepareCall(sql, resultSetType, resultSetConcurrency);
   }

   public CallableStatement prepareCall(String arg0, int arg1, int arg2, int arg3) throws SQLException {
      this.assertAvailable();
      return this.conn.prepareCall(arg0, arg1, arg2, arg3);
   }

   public PreparedStatement prepareStatement(String sql) throws SQLException {
      this.assertAvailable();
      return this.conn.prepareStatement(sql);
   }

   public PreparedStatement prepareStatement(String arg0, int arg1) throws SQLException {
      this.assertAvailable();
      return this.conn.prepareStatement(arg0, arg1);
   }

   public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
      this.assertAvailable();
      return this.conn.prepareStatement(sql, resultSetType, resultSetConcurrency);
   }

   public PreparedStatement prepareStatement(String arg0, int arg1, int arg2, int arg3) throws SQLException {
      this.assertAvailable();
      return this.conn.prepareStatement(arg0, arg1, arg2, arg3);
   }

   public PreparedStatement prepareStatement(String arg0, int[] arg1) throws SQLException {
      this.assertAvailable();
      return this.conn.prepareStatement(arg0, arg1);
   }

   public Savepoint setSavepoint(String arg0) throws SQLException {
      super.throwExceptionUnsupportedOperation("setSavepoint");
      return null;
   }

   public PreparedStatement prepareStatement(String arg0, String[] arg1) throws SQLException {
      this.assertAvailable();
      return this.conn.prepareStatement(arg0, arg1);
   }

   public void assertAvailable() {
      if (!this.isAvailable) {
         this.throwExceptionNotAvailable();
      }

   }

   public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
      return this.conn.createArrayOf(typeName, elements);
   }

   public Blob createBlob() throws SQLException {
      return this.conn.createBlob();
   }

   public Clob createClob() throws SQLException {
      return this.conn.createClob();
   }

   public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
      return this.conn.createStruct(typeName, attributes);
   }

   public Properties getClientInfo() throws SQLException {
      return this.conn.getClientInfo();
   }

   public String getClientInfo(String name) throws SQLException {
      return this.conn.getClientInfo(name);
   }

   public boolean isValid(int timeout) throws SQLException {
      return this.conn.isValid(timeout);
   }

   public void setClientInfo(Properties properties) throws SQLClientInfoException {
      try {
         this.conn.setClientInfo(properties);
      } catch (Exception var3) {
      }

   }

   public void setClientInfo(String name, String value) throws SQLClientInfoException {
      try {
         this.conn.setClientInfo(name, value);
      } catch (Exception var4) {
      }

   }

   public NClob createNClob() throws SQLException {
      return this.conn.createNClob();
   }

   public SQLXML createSQLXML() throws SQLException {
      return this.conn.createSQLXML();
   }

   public boolean isWrapperFor(Class iface) throws SQLException {
      return Connection.class.equals(iface);
   }

   public Object unwrap(Class iface) throws SQLException {
      if (!Connection.class.equals(iface)) {
         throw new SQLException("Connection of type [" + this.getClass().getName() + "] can only be unwrapped as [java.sql.Connection], not as [" + iface.getName() + "]");
      } else {
         return this;
      }
   }

   public void setSchema(String schema) throws SQLException {
      this.conn.setSchema(schema);
   }

   public String getSchema() throws SQLException {
      return this.conn.getSchema();
   }

   public void abort(Executor executor) throws SQLException {
      this.conn.abort(executor);
   }

   public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
      this.conn.setNetworkTimeout(executor, milliseconds);
   }

   public int getNetworkTimeout() throws SQLException {
      return this.conn.getNetworkTimeout();
   }
}
