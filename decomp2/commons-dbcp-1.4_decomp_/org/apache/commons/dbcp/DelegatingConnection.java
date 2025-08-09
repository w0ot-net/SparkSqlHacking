package org.apache.commons.dbcp;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class DelegatingConnection extends AbandonedTrace implements Connection {
   private static final Map EMPTY_FAILED_PROPERTIES = Collections.emptyMap();
   protected Connection _conn = null;
   protected boolean _closed = false;

   public DelegatingConnection(Connection c) {
      this._conn = c;
   }

   public DelegatingConnection(Connection c, AbandonedConfig config) {
      super(config);
      this._conn = c;
   }

   public String toString() {
      String s = null;
      Connection c = this.getInnermostDelegateInternal();
      if (c != null) {
         try {
            if (c.isClosed()) {
               s = "connection is closed";
            } else {
               DatabaseMetaData meta = c.getMetaData();
               if (meta != null) {
                  StringBuffer sb = new StringBuffer();
                  sb.append(meta.getURL());
                  sb.append(", UserName=");
                  sb.append(meta.getUserName());
                  sb.append(", ");
                  sb.append(meta.getDriverName());
                  s = sb.toString();
               }
            }
         } catch (SQLException var5) {
         }
      }

      if (s == null) {
         s = super.toString();
      }

      return s;
   }

   public Connection getDelegate() {
      return this.getDelegateInternal();
   }

   protected Connection getDelegateInternal() {
      return this._conn;
   }

   public boolean innermostDelegateEquals(Connection c) {
      Connection innerCon = this.getInnermostDelegateInternal();
      if (innerCon == null) {
         return c == null;
      } else {
         return innerCon.equals(c);
      }
   }

   public boolean equals(Object obj) {
      if (obj == null) {
         return false;
      } else if (obj == this) {
         return true;
      } else {
         Connection delegate = this.getInnermostDelegateInternal();
         if (delegate == null) {
            return false;
         } else if (obj instanceof DelegatingConnection) {
            DelegatingConnection c = (DelegatingConnection)obj;
            return c.innermostDelegateEquals(delegate);
         } else {
            return delegate.equals(obj);
         }
      }
   }

   public int hashCode() {
      Object obj = this.getInnermostDelegateInternal();
      return obj == null ? 0 : obj.hashCode();
   }

   public Connection getInnermostDelegate() {
      return this.getInnermostDelegateInternal();
   }

   protected final Connection getInnermostDelegateInternal() {
      Connection c = this._conn;

      while(c != null && c instanceof DelegatingConnection) {
         c = ((DelegatingConnection)c).getDelegateInternal();
         if (this == c) {
            return null;
         }
      }

      return c;
   }

   public void setDelegate(Connection c) {
      this._conn = c;
   }

   public void close() throws SQLException {
      this.passivate();
      this._conn.close();
   }

   protected void handleException(SQLException e) throws SQLException {
      throw e;
   }

   public Statement createStatement() throws SQLException {
      this.checkOpen();

      try {
         return new DelegatingStatement(this, this._conn.createStatement());
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
      this.checkOpen();

      try {
         return new DelegatingStatement(this, this._conn.createStatement(resultSetType, resultSetConcurrency));
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public PreparedStatement prepareStatement(String sql) throws SQLException {
      this.checkOpen();

      try {
         return new DelegatingPreparedStatement(this, this._conn.prepareStatement(sql));
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
      this.checkOpen();

      try {
         return new DelegatingPreparedStatement(this, this._conn.prepareStatement(sql, resultSetType, resultSetConcurrency));
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public CallableStatement prepareCall(String sql) throws SQLException {
      this.checkOpen();

      try {
         return new DelegatingCallableStatement(this, this._conn.prepareCall(sql));
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
      this.checkOpen();

      try {
         return new DelegatingCallableStatement(this, this._conn.prepareCall(sql, resultSetType, resultSetConcurrency));
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public void clearWarnings() throws SQLException {
      this.checkOpen();

      try {
         this._conn.clearWarnings();
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void commit() throws SQLException {
      this.checkOpen();

      try {
         this._conn.commit();
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public boolean getAutoCommit() throws SQLException {
      this.checkOpen();

      try {
         return this._conn.getAutoCommit();
      } catch (SQLException e) {
         this.handleException(e);
         return false;
      }
   }

   public String getCatalog() throws SQLException {
      this.checkOpen();

      try {
         return this._conn.getCatalog();
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public DatabaseMetaData getMetaData() throws SQLException {
      this.checkOpen();

      try {
         return new DelegatingDatabaseMetaData(this, this._conn.getMetaData());
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public int getTransactionIsolation() throws SQLException {
      this.checkOpen();

      try {
         return this._conn.getTransactionIsolation();
      } catch (SQLException e) {
         this.handleException(e);
         return -1;
      }
   }

   public Map getTypeMap() throws SQLException {
      this.checkOpen();

      try {
         return this._conn.getTypeMap();
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public SQLWarning getWarnings() throws SQLException {
      this.checkOpen();

      try {
         return this._conn.getWarnings();
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public boolean isReadOnly() throws SQLException {
      this.checkOpen();

      try {
         return this._conn.isReadOnly();
      } catch (SQLException e) {
         this.handleException(e);
         return false;
      }
   }

   public String nativeSQL(String sql) throws SQLException {
      this.checkOpen();

      try {
         return this._conn.nativeSQL(sql);
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public void rollback() throws SQLException {
      this.checkOpen();

      try {
         this._conn.rollback();
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void setAutoCommit(boolean autoCommit) throws SQLException {
      this.checkOpen();

      try {
         this._conn.setAutoCommit(autoCommit);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void setCatalog(String catalog) throws SQLException {
      this.checkOpen();

      try {
         this._conn.setCatalog(catalog);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void setReadOnly(boolean readOnly) throws SQLException {
      this.checkOpen();

      try {
         this._conn.setReadOnly(readOnly);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void setTransactionIsolation(int level) throws SQLException {
      this.checkOpen();

      try {
         this._conn.setTransactionIsolation(level);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void setTypeMap(Map map) throws SQLException {
      this.checkOpen();

      try {
         this._conn.setTypeMap(map);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public boolean isClosed() throws SQLException {
      return this._closed || this._conn.isClosed();
   }

   protected void checkOpen() throws SQLException {
      if (this._closed) {
         if (null != this._conn) {
            String label = "";

            try {
               label = this._conn.toString();
            } catch (Exception var3) {
            }

            throw new SQLException("Connection " + label + " is closed.");
         } else {
            throw new SQLException("Connection is null.");
         }
      }
   }

   protected void activate() {
      this._closed = false;
      this.setLastUsed();
      if (this._conn instanceof DelegatingConnection) {
         ((DelegatingConnection)this._conn).activate();
      }

   }

   protected void passivate() throws SQLException {
      try {
         List traces = this.getTrace();
         if (traces != null) {
            for(Object trace : traces) {
               if (trace instanceof Statement) {
                  ((Statement)trace).close();
               } else if (trace instanceof ResultSet) {
                  ((ResultSet)trace).close();
               }
            }

            this.clearTrace();
         }

         this.setLastUsed(0L);
         if (this._conn instanceof DelegatingConnection) {
            ((DelegatingConnection)this._conn).passivate();
         }
      } finally {
         this._closed = true;
      }

   }

   public int getHoldability() throws SQLException {
      this.checkOpen();

      try {
         return this._conn.getHoldability();
      } catch (SQLException e) {
         this.handleException(e);
         return 0;
      }
   }

   public void setHoldability(int holdability) throws SQLException {
      this.checkOpen();

      try {
         this._conn.setHoldability(holdability);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public Savepoint setSavepoint() throws SQLException {
      this.checkOpen();

      try {
         return this._conn.setSavepoint();
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public Savepoint setSavepoint(String name) throws SQLException {
      this.checkOpen();

      try {
         return this._conn.setSavepoint(name);
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public void rollback(Savepoint savepoint) throws SQLException {
      this.checkOpen();

      try {
         this._conn.rollback(savepoint);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void releaseSavepoint(Savepoint savepoint) throws SQLException {
      this.checkOpen();

      try {
         this._conn.releaseSavepoint(savepoint);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
      this.checkOpen();

      try {
         return new DelegatingStatement(this, this._conn.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability));
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
      this.checkOpen();

      try {
         return new DelegatingPreparedStatement(this, this._conn.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability));
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
      this.checkOpen();

      try {
         return new DelegatingCallableStatement(this, this._conn.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability));
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
      this.checkOpen();

      try {
         return new DelegatingPreparedStatement(this, this._conn.prepareStatement(sql, autoGeneratedKeys));
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
      this.checkOpen();

      try {
         return new DelegatingPreparedStatement(this, this._conn.prepareStatement(sql, columnIndexes));
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
      this.checkOpen();

      try {
         return new DelegatingPreparedStatement(this, this._conn.prepareStatement(sql, columnNames));
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public boolean isWrapperFor(Class iface) throws SQLException {
      return iface.isAssignableFrom(this.getClass()) || this._conn.isWrapperFor(iface);
   }

   public Object unwrap(Class iface) throws SQLException {
      if (iface.isAssignableFrom(this.getClass())) {
         return iface.cast(this);
      } else {
         return iface.isAssignableFrom(this._conn.getClass()) ? iface.cast(this._conn) : this._conn.unwrap(iface);
      }
   }

   public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
      this.checkOpen();

      try {
         return this._conn.createArrayOf(typeName, elements);
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public Blob createBlob() throws SQLException {
      this.checkOpen();

      try {
         return this._conn.createBlob();
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public Clob createClob() throws SQLException {
      this.checkOpen();

      try {
         return this._conn.createClob();
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public NClob createNClob() throws SQLException {
      this.checkOpen();

      try {
         return this._conn.createNClob();
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public SQLXML createSQLXML() throws SQLException {
      this.checkOpen();

      try {
         return this._conn.createSQLXML();
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
      this.checkOpen();

      try {
         return this._conn.createStruct(typeName, attributes);
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public boolean isValid(int timeout) throws SQLException {
      this.checkOpen();

      try {
         return this._conn.isValid(timeout);
      } catch (SQLException e) {
         this.handleException(e);
         return false;
      }
   }

   public void setClientInfo(String name, String value) throws SQLClientInfoException {
      try {
         this.checkOpen();
         this._conn.setClientInfo(name, value);
      } catch (SQLClientInfoException e) {
         throw e;
      } catch (SQLException e) {
         throw new SQLClientInfoException("Connection is closed.", EMPTY_FAILED_PROPERTIES, e);
      }
   }

   public void setClientInfo(Properties properties) throws SQLClientInfoException {
      try {
         this.checkOpen();
         this._conn.setClientInfo(properties);
      } catch (SQLClientInfoException e) {
         throw e;
      } catch (SQLException e) {
         throw new SQLClientInfoException("Connection is closed.", EMPTY_FAILED_PROPERTIES, e);
      }
   }

   public Properties getClientInfo() throws SQLException {
      this.checkOpen();

      try {
         return this._conn.getClientInfo();
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }

   public String getClientInfo(String name) throws SQLException {
      this.checkOpen();

      try {
         return this._conn.getClientInfo(name);
      } catch (SQLException e) {
         this.handleException(e);
         return null;
      }
   }
}
