package org.apache.commons.dbcp;

import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.Map;
import java.util.NoSuchElementException;
import javax.sql.DataSource;
import org.apache.commons.pool.ObjectPool;

public class PoolingDataSource implements DataSource {
   private boolean accessToUnderlyingConnectionAllowed;
   protected PrintWriter _logWriter;
   protected ObjectPool _pool;

   public PoolingDataSource() {
      this((ObjectPool)null);
   }

   public PoolingDataSource(ObjectPool pool) {
      this.accessToUnderlyingConnectionAllowed = false;
      this._logWriter = null;
      this._pool = null;
      this._pool = pool;
   }

   public void setPool(ObjectPool pool) throws IllegalStateException, NullPointerException {
      if (null != this._pool) {
         throw new IllegalStateException("Pool already set");
      } else if (null == pool) {
         throw new NullPointerException("Pool must not be null.");
      } else {
         this._pool = pool;
      }
   }

   public boolean isAccessToUnderlyingConnectionAllowed() {
      return this.accessToUnderlyingConnectionAllowed;
   }

   public void setAccessToUnderlyingConnectionAllowed(boolean allow) {
      this.accessToUnderlyingConnectionAllowed = allow;
   }

   public boolean isWrapperFor(Class iface) throws SQLException {
      return false;
   }

   public Object unwrap(Class iface) throws SQLException {
      throw new SQLException("PoolingDataSource is not a wrapper.");
   }

   public Connection getConnection() throws SQLException {
      try {
         Connection conn = (Connection)this._pool.borrowObject();
         if (conn != null) {
            conn = new PoolGuardConnectionWrapper(conn);
         }

         return conn;
      } catch (SQLException e) {
         throw e;
      } catch (NoSuchElementException e) {
         throw new SQLNestedException("Cannot get a connection, pool error " + e.getMessage(), e);
      } catch (RuntimeException e) {
         throw e;
      } catch (Exception e) {
         throw new SQLNestedException("Cannot get a connection, general error", e);
      }
   }

   public Connection getConnection(String uname, String passwd) throws SQLException {
      throw new UnsupportedOperationException();
   }

   public PrintWriter getLogWriter() {
      return this._logWriter;
   }

   public int getLoginTimeout() {
      throw new UnsupportedOperationException("Login timeout is not supported.");
   }

   public void setLoginTimeout(int seconds) {
      throw new UnsupportedOperationException("Login timeout is not supported.");
   }

   public void setLogWriter(PrintWriter out) {
      this._logWriter = out;
   }

   private class PoolGuardConnectionWrapper extends DelegatingConnection {
      private Connection delegate;

      PoolGuardConnectionWrapper(Connection delegate) {
         super(delegate);
         this.delegate = delegate;
      }

      protected void checkOpen() throws SQLException {
         if (this.delegate == null) {
            throw new SQLException("Connection is closed.");
         }
      }

      public void close() throws SQLException {
         if (this.delegate != null) {
            this.delegate.close();
            this.delegate = null;
            super.setDelegate((Connection)null);
         }

      }

      public boolean isClosed() throws SQLException {
         return this.delegate == null ? true : this.delegate.isClosed();
      }

      public void clearWarnings() throws SQLException {
         this.checkOpen();
         this.delegate.clearWarnings();
      }

      public void commit() throws SQLException {
         this.checkOpen();
         this.delegate.commit();
      }

      public Statement createStatement() throws SQLException {
         this.checkOpen();
         return new DelegatingStatement(this, this.delegate.createStatement());
      }

      public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
         this.checkOpen();
         return new DelegatingStatement(this, this.delegate.createStatement(resultSetType, resultSetConcurrency));
      }

      public boolean innermostDelegateEquals(Connection c) {
         Connection innerCon = super.getInnermostDelegate();
         if (innerCon == null) {
            return c == null;
         } else {
            return innerCon.equals(c);
         }
      }

      public boolean getAutoCommit() throws SQLException {
         this.checkOpen();
         return this.delegate.getAutoCommit();
      }

      public String getCatalog() throws SQLException {
         this.checkOpen();
         return this.delegate.getCatalog();
      }

      public DatabaseMetaData getMetaData() throws SQLException {
         this.checkOpen();
         return this.delegate.getMetaData();
      }

      public int getTransactionIsolation() throws SQLException {
         this.checkOpen();
         return this.delegate.getTransactionIsolation();
      }

      public Map getTypeMap() throws SQLException {
         this.checkOpen();
         return this.delegate.getTypeMap();
      }

      public SQLWarning getWarnings() throws SQLException {
         this.checkOpen();
         return this.delegate.getWarnings();
      }

      public int hashCode() {
         return this.delegate == null ? 0 : this.delegate.hashCode();
      }

      public boolean equals(Object obj) {
         if (obj == null) {
            return false;
         } else if (obj == this) {
            return true;
         } else {
            Connection conn = super.getInnermostDelegate();
            if (conn == null) {
               return false;
            } else if (obj instanceof DelegatingConnection) {
               DelegatingConnection c = (DelegatingConnection)obj;
               return c.innermostDelegateEquals(conn);
            } else {
               return conn.equals(obj);
            }
         }
      }

      public boolean isReadOnly() throws SQLException {
         this.checkOpen();
         return this.delegate.isReadOnly();
      }

      public String nativeSQL(String sql) throws SQLException {
         this.checkOpen();
         return this.delegate.nativeSQL(sql);
      }

      public CallableStatement prepareCall(String sql) throws SQLException {
         this.checkOpen();
         return new DelegatingCallableStatement(this, this.delegate.prepareCall(sql));
      }

      public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
         this.checkOpen();
         return new DelegatingCallableStatement(this, this.delegate.prepareCall(sql, resultSetType, resultSetConcurrency));
      }

      public PreparedStatement prepareStatement(String sql) throws SQLException {
         this.checkOpen();
         return new DelegatingPreparedStatement(this, this.delegate.prepareStatement(sql));
      }

      public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
         this.checkOpen();
         return new DelegatingPreparedStatement(this, this.delegate.prepareStatement(sql, resultSetType, resultSetConcurrency));
      }

      public void rollback() throws SQLException {
         this.checkOpen();
         this.delegate.rollback();
      }

      public void setAutoCommit(boolean autoCommit) throws SQLException {
         this.checkOpen();
         this.delegate.setAutoCommit(autoCommit);
      }

      public void setCatalog(String catalog) throws SQLException {
         this.checkOpen();
         this.delegate.setCatalog(catalog);
      }

      public void setReadOnly(boolean readOnly) throws SQLException {
         this.checkOpen();
         this.delegate.setReadOnly(readOnly);
      }

      public void setTransactionIsolation(int level) throws SQLException {
         this.checkOpen();
         this.delegate.setTransactionIsolation(level);
      }

      public void setTypeMap(Map map) throws SQLException {
         this.checkOpen();
         this.delegate.setTypeMap(map);
      }

      public String toString() {
         return this.delegate == null ? "NULL" : this.delegate.toString();
      }

      public int getHoldability() throws SQLException {
         this.checkOpen();
         return this.delegate.getHoldability();
      }

      public void setHoldability(int holdability) throws SQLException {
         this.checkOpen();
         this.delegate.setHoldability(holdability);
      }

      public Savepoint setSavepoint() throws SQLException {
         this.checkOpen();
         return this.delegate.setSavepoint();
      }

      public Savepoint setSavepoint(String name) throws SQLException {
         this.checkOpen();
         return this.delegate.setSavepoint(name);
      }

      public void releaseSavepoint(Savepoint savepoint) throws SQLException {
         this.checkOpen();
         this.delegate.releaseSavepoint(savepoint);
      }

      public void rollback(Savepoint savepoint) throws SQLException {
         this.checkOpen();
         this.delegate.rollback(savepoint);
      }

      public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
         this.checkOpen();
         return new DelegatingStatement(this, this.delegate.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability));
      }

      public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
         this.checkOpen();
         return new DelegatingCallableStatement(this, this.delegate.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability));
      }

      public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
         this.checkOpen();
         return new DelegatingPreparedStatement(this, this.delegate.prepareStatement(sql, autoGeneratedKeys));
      }

      public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
         this.checkOpen();
         return new DelegatingPreparedStatement(this, this.delegate.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability));
      }

      public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
         this.checkOpen();
         return new DelegatingPreparedStatement(this, this.delegate.prepareStatement(sql, columnIndexes));
      }

      public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
         this.checkOpen();
         return new DelegatingPreparedStatement(this, this.delegate.prepareStatement(sql, columnNames));
      }

      public Connection getDelegate() {
         return PoolingDataSource.this.isAccessToUnderlyingConnectionAllowed() ? super.getDelegate() : null;
      }

      public Connection getInnermostDelegate() {
         return PoolingDataSource.this.isAccessToUnderlyingConnectionAllowed() ? super.getInnermostDelegate() : null;
      }
   }
}
