package org.datanucleus.store.rdbms.datasource.dbcp.cpdsadapter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Vector;
import javax.sql.ConnectionEvent;
import javax.sql.ConnectionEventListener;
import javax.sql.PooledConnection;
import javax.sql.StatementEventListener;
import org.datanucleus.store.rdbms.datasource.dbcp.DelegatingConnection;
import org.datanucleus.store.rdbms.datasource.dbcp.DelegatingPreparedStatement;
import org.datanucleus.store.rdbms.datasource.dbcp.SQLNestedException;
import org.datanucleus.store.rdbms.datasource.dbcp.pool.KeyedObjectPool;
import org.datanucleus.store.rdbms.datasource.dbcp.pool.KeyedPoolableObjectFactory;

class PooledConnectionImpl implements PooledConnection, KeyedPoolableObjectFactory {
   private static final String CLOSED = "Attempted to use PooledConnection after closed() was called.";
   private Connection connection = null;
   private final DelegatingConnection delegatingConnection;
   private Connection logicalConnection = null;
   private final Vector eventListeners;
   private final Vector statementEventListeners = new Vector();
   boolean isClosed;
   protected KeyedObjectPool pstmtPool = null;
   private boolean accessToUnderlyingConnectionAllowed = false;

   PooledConnectionImpl(Connection connection, KeyedObjectPool pool) {
      this.connection = connection;
      if (connection instanceof DelegatingConnection) {
         this.delegatingConnection = (DelegatingConnection)connection;
      } else {
         this.delegatingConnection = new DelegatingConnection(connection);
      }

      this.eventListeners = new Vector();
      this.isClosed = false;
      if (pool != null) {
         this.pstmtPool = pool;
         this.pstmtPool.setFactory(this);
      }

   }

   public void addConnectionEventListener(ConnectionEventListener listener) {
      if (!this.eventListeners.contains(listener)) {
         this.eventListeners.add(listener);
      }

   }

   public void addStatementEventListener(StatementEventListener listener) {
      if (!this.statementEventListeners.contains(listener)) {
         this.statementEventListeners.add(listener);
      }

   }

   public void close() throws SQLException {
      this.assertOpen();
      this.isClosed = true;

      try {
         if (this.pstmtPool != null) {
            try {
               this.pstmtPool.close();
            } finally {
               this.pstmtPool = null;
            }
         }
      } catch (RuntimeException e) {
         throw e;
      } catch (Exception e) {
         throw new SQLNestedException("Cannot close connection (return to pool failed)", e);
      } finally {
         try {
            this.connection.close();
         } finally {
            this.connection = null;
         }
      }

   }

   private void assertOpen() throws SQLException {
      if (this.isClosed) {
         throw new SQLException("Attempted to use PooledConnection after closed() was called.");
      }
   }

   public Connection getConnection() throws SQLException {
      this.assertOpen();
      if (this.logicalConnection != null && !this.logicalConnection.isClosed()) {
         throw new SQLException("PooledConnection was reused, withoutits previous Connection being closed.");
      } else {
         this.logicalConnection = new ConnectionImpl(this, this.connection, this.isAccessToUnderlyingConnectionAllowed());
         return this.logicalConnection;
      }
   }

   public void removeConnectionEventListener(ConnectionEventListener listener) {
      this.eventListeners.remove(listener);
   }

   public void removeStatementEventListener(StatementEventListener listener) {
      this.statementEventListeners.remove(listener);
   }

   protected void finalize() throws Throwable {
      try {
         this.connection.close();
      } catch (Exception var2) {
      }

      if (this.logicalConnection != null && !this.logicalConnection.isClosed()) {
         throw new SQLException("PooledConnection was gc'ed, withoutits last Connection being closed.");
      }
   }

   void notifyListeners() {
      ConnectionEvent event = new ConnectionEvent(this);
      Object[] listeners = this.eventListeners.toArray();

      for(int i = 0; i < listeners.length; ++i) {
         ((ConnectionEventListener)listeners[i]).connectionClosed(event);
      }

   }

   PreparedStatement prepareStatement(String sql) throws SQLException {
      if (this.pstmtPool == null) {
         return this.connection.prepareStatement(sql);
      } else {
         try {
            return (PreparedStatement)this.pstmtPool.borrowObject(this.createKey(sql));
         } catch (RuntimeException e) {
            throw e;
         } catch (Exception e) {
            throw new SQLNestedException("Borrow prepareStatement from pool failed", e);
         }
      }
   }

   PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
      if (this.pstmtPool == null) {
         return this.connection.prepareStatement(sql, resultSetType, resultSetConcurrency);
      } else {
         try {
            return (PreparedStatement)this.pstmtPool.borrowObject(this.createKey(sql, resultSetType, resultSetConcurrency));
         } catch (RuntimeException e) {
            throw e;
         } catch (Exception e) {
            throw new SQLNestedException("Borrow prepareStatement from pool failed", e);
         }
      }
   }

   PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
      if (this.pstmtPool == null) {
         return this.connection.prepareStatement(sql, autoGeneratedKeys);
      } else {
         try {
            return (PreparedStatement)this.pstmtPool.borrowObject(this.createKey(sql, autoGeneratedKeys));
         } catch (RuntimeException e) {
            throw e;
         } catch (Exception e) {
            throw new SQLNestedException("Borrow prepareStatement from pool failed", e);
         }
      }
   }

   PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
      if (this.pstmtPool == null) {
         return this.connection.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
      } else {
         try {
            return (PreparedStatement)this.pstmtPool.borrowObject(this.createKey(sql, resultSetType, resultSetConcurrency, resultSetHoldability));
         } catch (RuntimeException e) {
            throw e;
         } catch (Exception e) {
            throw new SQLNestedException("Borrow prepareStatement from pool failed", e);
         }
      }
   }

   PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
      if (this.pstmtPool == null) {
         return this.connection.prepareStatement(sql, columnIndexes);
      } else {
         try {
            return (PreparedStatement)this.pstmtPool.borrowObject(this.createKey(sql, columnIndexes));
         } catch (RuntimeException e) {
            throw e;
         } catch (Exception e) {
            throw new SQLNestedException("Borrow prepareStatement from pool failed", e);
         }
      }
   }

   PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
      if (this.pstmtPool == null) {
         return this.connection.prepareStatement(sql, columnNames);
      } else {
         try {
            return (PreparedStatement)this.pstmtPool.borrowObject(this.createKey(sql, columnNames));
         } catch (RuntimeException e) {
            throw e;
         } catch (Exception e) {
            throw new SQLNestedException("Borrow prepareStatement from pool failed", e);
         }
      }
   }

   protected Object createKey(String sql, int autoGeneratedKeys) {
      return new PStmtKey(this.normalizeSQL(sql), autoGeneratedKeys);
   }

   protected Object createKey(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) {
      return new PStmtKey(this.normalizeSQL(sql), resultSetType, resultSetConcurrency, resultSetHoldability);
   }

   protected Object createKey(String sql, int[] columnIndexes) {
      return new PStmtKey(this.normalizeSQL(sql), columnIndexes);
   }

   protected Object createKey(String sql, String[] columnNames) {
      return new PStmtKey(this.normalizeSQL(sql), columnNames);
   }

   protected Object createKey(String sql, int resultSetType, int resultSetConcurrency) {
      return new PStmtKey(this.normalizeSQL(sql), resultSetType, resultSetConcurrency);
   }

   protected Object createKey(String sql) {
      return new PStmtKey(this.normalizeSQL(sql));
   }

   protected String normalizeSQL(String sql) {
      return sql.trim();
   }

   public Object makeObject(Object obj) throws Exception {
      if (null != obj && obj instanceof PStmtKey) {
         PStmtKey key = (PStmtKey)obj;
         if (null == key._resultSetType && null == key._resultSetConcurrency) {
            return null == key._autoGeneratedKeys ? new PoolablePreparedStatementStub(this.connection.prepareStatement(key._sql), key, this.pstmtPool, this.delegatingConnection) : new PoolablePreparedStatementStub(this.connection.prepareStatement(key._sql, key._autoGeneratedKeys), key, this.pstmtPool, this.delegatingConnection);
         } else {
            return new PoolablePreparedStatementStub(this.connection.prepareStatement(key._sql, key._resultSetType, key._resultSetConcurrency), key, this.pstmtPool, this.delegatingConnection);
         }
      } else {
         throw new IllegalArgumentException();
      }
   }

   public void destroyObject(Object key, Object obj) throws Exception {
      if (obj instanceof DelegatingPreparedStatement) {
         ((DelegatingPreparedStatement)obj).getInnermostDelegate().close();
      } else {
         ((PreparedStatement)obj).close();
      }

   }

   public boolean validateObject(Object key, Object obj) {
      return true;
   }

   public void activateObject(Object key, Object obj) throws Exception {
      ((PoolablePreparedStatementStub)obj).activate();
   }

   public void passivateObject(Object key, Object obj) throws Exception {
      ((PreparedStatement)obj).clearParameters();
      ((PoolablePreparedStatementStub)obj).passivate();
   }

   public synchronized boolean isAccessToUnderlyingConnectionAllowed() {
      return this.accessToUnderlyingConnectionAllowed;
   }

   public synchronized void setAccessToUnderlyingConnectionAllowed(boolean allow) {
      this.accessToUnderlyingConnectionAllowed = allow;
   }

   static class PStmtKey {
      protected String _sql = null;
      protected Integer _resultSetType = null;
      protected Integer _resultSetConcurrency = null;
      protected Integer _autoGeneratedKeys = null;
      protected Integer _resultSetHoldability = null;
      protected int[] _columnIndexes = null;
      protected String[] _columnNames = null;

      PStmtKey(String sql) {
         this._sql = sql;
      }

      PStmtKey(String sql, int resultSetType, int resultSetConcurrency) {
         this._sql = sql;
         this._resultSetType = new Integer(resultSetType);
         this._resultSetConcurrency = new Integer(resultSetConcurrency);
      }

      PStmtKey(String sql, int autoGeneratedKeys) {
         this._sql = sql;
         this._autoGeneratedKeys = new Integer(autoGeneratedKeys);
      }

      PStmtKey(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) {
         this._sql = sql;
         this._resultSetType = new Integer(resultSetType);
         this._resultSetConcurrency = new Integer(resultSetConcurrency);
         this._resultSetHoldability = new Integer(resultSetHoldability);
      }

      PStmtKey(String sql, int[] columnIndexes) {
         this._sql = sql;
         this._columnIndexes = columnIndexes;
      }

      PStmtKey(String sql, String[] columnNames) {
         this._sql = sql;
         this._columnNames = columnNames;
      }

      public boolean equals(Object that) {
         try {
            PStmtKey key = (PStmtKey)that;
            return (null == this._sql && null == key._sql || this._sql.equals(key._sql)) && (null == this._resultSetType && null == key._resultSetType || this._resultSetType.equals(key._resultSetType)) && (null == this._resultSetConcurrency && null == key._resultSetConcurrency || this._resultSetConcurrency.equals(key._resultSetConcurrency)) && (null == this._autoGeneratedKeys && null == key._autoGeneratedKeys || this._autoGeneratedKeys.equals(key._autoGeneratedKeys)) && (null == this._resultSetHoldability && null == key._resultSetHoldability || this._resultSetHoldability.equals(key._resultSetHoldability)) && (null == this._columnIndexes && null == key._columnIndexes || Arrays.equals(this._columnIndexes, key._columnIndexes)) && (null == this._columnNames && null == key._columnNames || Arrays.equals(this._columnNames, key._columnNames));
         } catch (ClassCastException var3) {
            return false;
         } catch (NullPointerException var4) {
            return false;
         }
      }

      public int hashCode() {
         return null == this._sql ? 0 : this._sql.hashCode();
      }

      public String toString() {
         StringBuffer buf = new StringBuffer();
         buf.append("PStmtKey: sql=");
         buf.append(this._sql);
         buf.append(", resultSetType=");
         buf.append(this._resultSetType);
         buf.append(", resultSetConcurrency=");
         buf.append(this._resultSetConcurrency);
         buf.append(", autoGeneratedKeys=");
         buf.append(this._autoGeneratedKeys);
         buf.append(", resultSetHoldability=");
         buf.append(this._resultSetHoldability);
         buf.append(", columnIndexes=");
         this.arrayToString(buf, this._columnIndexes);
         buf.append(", columnNames=");
         this.arrayToString(buf, this._columnNames);
         return buf.toString();
      }

      private void arrayToString(StringBuffer sb, int[] array) {
         if (array == null) {
            sb.append("null");
         } else {
            sb.append('[');

            for(int i = 0; i < array.length; ++i) {
               if (i > 0) {
                  sb.append(',');
               }

               sb.append(array[i]);
            }

            sb.append(']');
         }
      }

      private void arrayToString(StringBuffer sb, String[] array) {
         if (array == null) {
            sb.append("null");
         } else {
            sb.append('[');

            for(int i = 0; i < array.length; ++i) {
               if (i > 0) {
                  sb.append(',');
               }

               sb.append(array[i]);
            }

            sb.append(']');
         }
      }
   }
}
