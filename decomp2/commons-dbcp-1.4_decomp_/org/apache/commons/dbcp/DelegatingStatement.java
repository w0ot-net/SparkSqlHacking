package org.apache.commons.dbcp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.List;

public class DelegatingStatement extends AbandonedTrace implements Statement {
   protected Statement _stmt = null;
   protected DelegatingConnection _conn = null;
   protected boolean _closed = false;

   public DelegatingStatement(DelegatingConnection c, Statement s) {
      super((AbandonedTrace)c);
      this._stmt = s;
      this._conn = c;
   }

   public Statement getDelegate() {
      return this._stmt;
   }

   public boolean equals(Object obj) {
      Statement delegate = this.getInnermostDelegate();
      if (delegate == null) {
         return false;
      } else if (obj instanceof DelegatingStatement) {
         DelegatingStatement s = (DelegatingStatement)obj;
         return delegate.equals(s.getInnermostDelegate());
      } else {
         return delegate.equals(obj);
      }
   }

   public int hashCode() {
      Object obj = this.getInnermostDelegate();
      return obj == null ? 0 : obj.hashCode();
   }

   public Statement getInnermostDelegate() {
      Statement s = this._stmt;

      while(s != null && s instanceof DelegatingStatement) {
         s = ((DelegatingStatement)s).getDelegate();
         if (this == s) {
            return null;
         }
      }

      return s;
   }

   public void setDelegate(Statement s) {
      this._stmt = s;
   }

   protected void checkOpen() throws SQLException {
      if (this.isClosed()) {
         throw new SQLException(this.getClass().getName() + " with address: \"" + this.toString() + "\" is closed.");
      }
   }

   public void close() throws SQLException {
      try {
         if (this._conn != null) {
            this._conn.removeTrace(this);
            this._conn = null;
         }

         List resultSets = this.getTrace();
         if (resultSets != null) {
            ResultSet[] set = (ResultSet[])resultSets.toArray(new ResultSet[resultSets.size()]);

            for(int i = 0; i < set.length; ++i) {
               set[i].close();
            }

            this.clearTrace();
         }

         this._stmt.close();
      } catch (SQLException e) {
         this.handleException(e);
      } finally {
         this._closed = true;
      }

   }

   protected void handleException(SQLException e) throws SQLException {
      if (this._conn != null) {
         this._conn.handleException(e);
      } else {
         throw e;
      }
   }

   protected void activate() throws SQLException {
      if (this._stmt instanceof DelegatingStatement) {
         ((DelegatingStatement)this._stmt).activate();
      }

   }

   protected void passivate() throws SQLException {
      if (this._stmt instanceof DelegatingStatement) {
         ((DelegatingStatement)this._stmt).passivate();
      }

   }

   public Connection getConnection() throws SQLException {
      this.checkOpen();
      return this._conn;
   }

   public ResultSet executeQuery(String sql) throws SQLException {
      this.checkOpen();

      try {
         return DelegatingResultSet.wrapResultSet((Statement)this, this._stmt.executeQuery(sql));
      } catch (SQLException e) {
         this.handleException(e);
         throw new AssertionError();
      }
   }

   public ResultSet getResultSet() throws SQLException {
      this.checkOpen();

      try {
         return DelegatingResultSet.wrapResultSet((Statement)this, this._stmt.getResultSet());
      } catch (SQLException e) {
         this.handleException(e);
         throw new AssertionError();
      }
   }

   public int executeUpdate(String sql) throws SQLException {
      this.checkOpen();

      try {
         return this._stmt.executeUpdate(sql);
      } catch (SQLException e) {
         this.handleException(e);
         return 0;
      }
   }

   public int getMaxFieldSize() throws SQLException {
      this.checkOpen();

      try {
         return this._stmt.getMaxFieldSize();
      } catch (SQLException e) {
         this.handleException(e);
         return 0;
      }
   }

   public void setMaxFieldSize(int max) throws SQLException {
      this.checkOpen();

      try {
         this._stmt.setMaxFieldSize(max);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public int getMaxRows() throws SQLException {
      this.checkOpen();

      try {
         return this._stmt.getMaxRows();
      } catch (SQLException e) {
         this.handleException(e);
         return 0;
      }
   }

   public void setMaxRows(int max) throws SQLException {
      this.checkOpen();

      try {
         this._stmt.setMaxRows(max);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void setEscapeProcessing(boolean enable) throws SQLException {
      this.checkOpen();

      try {
         this._stmt.setEscapeProcessing(enable);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public int getQueryTimeout() throws SQLException {
      this.checkOpen();

      try {
         return this._stmt.getQueryTimeout();
      } catch (SQLException e) {
         this.handleException(e);
         return 0;
      }
   }

   public void setQueryTimeout(int seconds) throws SQLException {
      this.checkOpen();

      try {
         this._stmt.setQueryTimeout(seconds);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void cancel() throws SQLException {
      this.checkOpen();

      try {
         this._stmt.cancel();
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public SQLWarning getWarnings() throws SQLException {
      this.checkOpen();

      try {
         return this._stmt.getWarnings();
      } catch (SQLException e) {
         this.handleException(e);
         throw new AssertionError();
      }
   }

   public void clearWarnings() throws SQLException {
      this.checkOpen();

      try {
         this._stmt.clearWarnings();
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void setCursorName(String name) throws SQLException {
      this.checkOpen();

      try {
         this._stmt.setCursorName(name);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public boolean execute(String sql) throws SQLException {
      this.checkOpen();

      try {
         return this._stmt.execute(sql);
      } catch (SQLException e) {
         this.handleException(e);
         return false;
      }
   }

   public int getUpdateCount() throws SQLException {
      this.checkOpen();

      try {
         return this._stmt.getUpdateCount();
      } catch (SQLException e) {
         this.handleException(e);
         return 0;
      }
   }

   public boolean getMoreResults() throws SQLException {
      this.checkOpen();

      try {
         return this._stmt.getMoreResults();
      } catch (SQLException e) {
         this.handleException(e);
         return false;
      }
   }

   public void setFetchDirection(int direction) throws SQLException {
      this.checkOpen();

      try {
         this._stmt.setFetchDirection(direction);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public int getFetchDirection() throws SQLException {
      this.checkOpen();

      try {
         return this._stmt.getFetchDirection();
      } catch (SQLException e) {
         this.handleException(e);
         return 0;
      }
   }

   public void setFetchSize(int rows) throws SQLException {
      this.checkOpen();

      try {
         this._stmt.setFetchSize(rows);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public int getFetchSize() throws SQLException {
      this.checkOpen();

      try {
         return this._stmt.getFetchSize();
      } catch (SQLException e) {
         this.handleException(e);
         return 0;
      }
   }

   public int getResultSetConcurrency() throws SQLException {
      this.checkOpen();

      try {
         return this._stmt.getResultSetConcurrency();
      } catch (SQLException e) {
         this.handleException(e);
         return 0;
      }
   }

   public int getResultSetType() throws SQLException {
      this.checkOpen();

      try {
         return this._stmt.getResultSetType();
      } catch (SQLException e) {
         this.handleException(e);
         return 0;
      }
   }

   public void addBatch(String sql) throws SQLException {
      this.checkOpen();

      try {
         this._stmt.addBatch(sql);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void clearBatch() throws SQLException {
      this.checkOpen();

      try {
         this._stmt.clearBatch();
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public int[] executeBatch() throws SQLException {
      this.checkOpen();

      try {
         return this._stmt.executeBatch();
      } catch (SQLException e) {
         this.handleException(e);
         throw new AssertionError();
      }
   }

   public String toString() {
      return this._stmt.toString();
   }

   public boolean getMoreResults(int current) throws SQLException {
      this.checkOpen();

      try {
         return this._stmt.getMoreResults(current);
      } catch (SQLException e) {
         this.handleException(e);
         return false;
      }
   }

   public ResultSet getGeneratedKeys() throws SQLException {
      this.checkOpen();

      try {
         return DelegatingResultSet.wrapResultSet((Statement)this, this._stmt.getGeneratedKeys());
      } catch (SQLException e) {
         this.handleException(e);
         throw new AssertionError();
      }
   }

   public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
      this.checkOpen();

      try {
         return this._stmt.executeUpdate(sql, autoGeneratedKeys);
      } catch (SQLException e) {
         this.handleException(e);
         return 0;
      }
   }

   public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
      this.checkOpen();

      try {
         return this._stmt.executeUpdate(sql, columnIndexes);
      } catch (SQLException e) {
         this.handleException(e);
         return 0;
      }
   }

   public int executeUpdate(String sql, String[] columnNames) throws SQLException {
      this.checkOpen();

      try {
         return this._stmt.executeUpdate(sql, columnNames);
      } catch (SQLException e) {
         this.handleException(e);
         return 0;
      }
   }

   public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
      this.checkOpen();

      try {
         return this._stmt.execute(sql, autoGeneratedKeys);
      } catch (SQLException e) {
         this.handleException(e);
         return false;
      }
   }

   public boolean execute(String sql, int[] columnIndexes) throws SQLException {
      this.checkOpen();

      try {
         return this._stmt.execute(sql, columnIndexes);
      } catch (SQLException e) {
         this.handleException(e);
         return false;
      }
   }

   public boolean execute(String sql, String[] columnNames) throws SQLException {
      this.checkOpen();

      try {
         return this._stmt.execute(sql, columnNames);
      } catch (SQLException e) {
         this.handleException(e);
         return false;
      }
   }

   public int getResultSetHoldability() throws SQLException {
      this.checkOpen();

      try {
         return this._stmt.getResultSetHoldability();
      } catch (SQLException e) {
         this.handleException(e);
         return 0;
      }
   }

   public boolean isClosed() throws SQLException {
      return this._closed;
   }

   public boolean isWrapperFor(Class iface) throws SQLException {
      return iface.isAssignableFrom(this.getClass()) || this._stmt.isWrapperFor(iface);
   }

   public Object unwrap(Class iface) throws SQLException {
      if (iface.isAssignableFrom(this.getClass())) {
         return iface.cast(this);
      } else {
         return iface.isAssignableFrom(this._stmt.getClass()) ? iface.cast(this._stmt) : this._stmt.unwrap(iface);
      }
   }

   public void setPoolable(boolean poolable) throws SQLException {
      this.checkOpen();

      try {
         this._stmt.setPoolable(poolable);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public boolean isPoolable() throws SQLException {
      this.checkOpen();

      try {
         return this._stmt.isPoolable();
      } catch (SQLException e) {
         this.handleException(e);
         return false;
      }
   }
}
