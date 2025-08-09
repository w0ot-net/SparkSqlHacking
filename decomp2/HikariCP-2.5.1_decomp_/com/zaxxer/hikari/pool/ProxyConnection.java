package com.zaxxer.hikari.pool;

import com.zaxxer.hikari.util.ClockSource;
import com.zaxxer.hikari.util.FastList;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Wrapper;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ProxyConnection implements Connection {
   static final int DIRTY_BIT_READONLY = 1;
   static final int DIRTY_BIT_AUTOCOMMIT = 2;
   static final int DIRTY_BIT_ISOLATION = 4;
   static final int DIRTY_BIT_CATALOG = 8;
   static final int DIRTY_BIT_NETTIMEOUT = 16;
   private static final Logger LOGGER = LoggerFactory.getLogger(ProxyConnection.class);
   private static final Set ERROR_STATES;
   private static final Set ERROR_CODES;
   private static final ClockSource clockSource;
   protected Connection delegate;
   private final PoolEntry poolEntry;
   private final ProxyLeakTask leakTask;
   private final FastList openStatements;
   private int dirtyBits;
   private long lastAccess;
   private boolean isCommitStateDirty;
   private boolean isReadOnly;
   private boolean isAutoCommit;
   private int networkTimeout;
   private int transactionIsolation;
   private String dbcatalog;

   protected ProxyConnection(PoolEntry poolEntry, Connection connection, FastList openStatements, ProxyLeakTask leakTask, long now, boolean isReadOnly, boolean isAutoCommit) {
      this.poolEntry = poolEntry;
      this.delegate = connection;
      this.openStatements = openStatements;
      this.leakTask = leakTask;
      this.lastAccess = now;
      this.isReadOnly = isReadOnly;
      this.isAutoCommit = isAutoCommit;
   }

   public final String toString() {
      return (new StringBuilder(64)).append(this.getClass().getSimpleName()).append('@').append(System.identityHashCode(this)).append(" wrapping ").append(this.delegate).toString();
   }

   final boolean getAutoCommitState() {
      return this.isAutoCommit;
   }

   final String getCatalogState() {
      return this.dbcatalog;
   }

   final int getTransactionIsolationState() {
      return this.transactionIsolation;
   }

   final boolean getReadOnlyState() {
      return this.isReadOnly;
   }

   final int getNetworkTimeoutState() {
      return this.networkTimeout;
   }

   final PoolEntry getPoolEntry() {
      return this.poolEntry;
   }

   final SQLException checkException(SQLException sqle) {
      SQLException nse = sqle;

      for(int depth = 0; this.delegate != ProxyConnection.ClosedConnection.CLOSED_CONNECTION && nse != null && depth < 10; ++depth) {
         String sqlState = nse.getSQLState();
         if ((sqlState == null || !sqlState.startsWith("08")) && !ERROR_STATES.contains(sqlState) && !ERROR_CODES.contains(nse.getErrorCode())) {
            nse = nse.getNextException();
         } else {
            LOGGER.warn("{} - Connection {} marked as broken because of SQLSTATE({}), ErrorCode({})", new Object[]{this.poolEntry.getPoolName(), this.delegate, sqlState, nse.getErrorCode(), nse});
            this.leakTask.cancel();
            this.poolEntry.evict("(connection is broken)");
            this.delegate = ProxyConnection.ClosedConnection.CLOSED_CONNECTION;
         }
      }

      return sqle;
   }

   final synchronized void untrackStatement(Statement statement) {
      this.openStatements.remove(statement);
   }

   final void markCommitStateDirty() {
      if (this.isAutoCommit) {
         this.lastAccess = clockSource.currentTime();
      } else {
         this.isCommitStateDirty = true;
      }

   }

   void cancelLeakTask() {
      this.leakTask.cancel();
   }

   private final synchronized Statement trackStatement(Statement statement) {
      this.openStatements.add(statement);
      return statement;
   }

   private final void closeStatements() {
      int size = this.openStatements.size();
      if (size > 0) {
         for(int i = 0; i < size && this.delegate != ProxyConnection.ClosedConnection.CLOSED_CONNECTION; ++i) {
            try {
               Statement statement = (Statement)this.openStatements.get(i);
               Object var4 = null;
               if (statement != null) {
                  if (var4 != null) {
                     try {
                        statement.close();
                     } catch (Throwable var8) {
                        ((Throwable)var4).addSuppressed(var8);
                     }
                  } else {
                     statement.close();
                  }
               }
            } catch (SQLException e) {
               this.checkException(e);
            }
         }

         synchronized(this) {
            this.openStatements.clear();
         }
      }

   }

   public final void close() throws SQLException {
      this.closeStatements();
      if (this.delegate != ProxyConnection.ClosedConnection.CLOSED_CONNECTION) {
         this.leakTask.cancel();

         try {
            if (this.isCommitStateDirty && !this.isAutoCommit) {
               this.delegate.rollback();
               this.lastAccess = clockSource.currentTime();
               LOGGER.debug("{} - Executed rollback on connection {} due to dirty commit state on close().", this.poolEntry.getPoolName(), this.delegate);
            }

            if (this.dirtyBits != 0) {
               this.poolEntry.resetConnectionState(this, this.dirtyBits);
               this.lastAccess = clockSource.currentTime();
            }

            this.delegate.clearWarnings();
         } catch (SQLException e) {
            if (!this.poolEntry.isMarkedEvicted()) {
               throw this.checkException(e);
            }
         } finally {
            this.delegate = ProxyConnection.ClosedConnection.CLOSED_CONNECTION;
            this.poolEntry.recycle(this.lastAccess);
         }
      }

   }

   public boolean isClosed() throws SQLException {
      return this.delegate == ProxyConnection.ClosedConnection.CLOSED_CONNECTION;
   }

   public Statement createStatement() throws SQLException {
      return ProxyFactory.getProxyStatement(this, this.trackStatement(this.delegate.createStatement()));
   }

   public Statement createStatement(int resultSetType, int concurrency) throws SQLException {
      return ProxyFactory.getProxyStatement(this, this.trackStatement(this.delegate.createStatement(resultSetType, concurrency)));
   }

   public Statement createStatement(int resultSetType, int concurrency, int holdability) throws SQLException {
      return ProxyFactory.getProxyStatement(this, this.trackStatement(this.delegate.createStatement(resultSetType, concurrency, holdability)));
   }

   public CallableStatement prepareCall(String sql) throws SQLException {
      return ProxyFactory.getProxyCallableStatement(this, (CallableStatement)this.trackStatement(this.delegate.prepareCall(sql)));
   }

   public CallableStatement prepareCall(String sql, int resultSetType, int concurrency) throws SQLException {
      return ProxyFactory.getProxyCallableStatement(this, (CallableStatement)this.trackStatement(this.delegate.prepareCall(sql, resultSetType, concurrency)));
   }

   public CallableStatement prepareCall(String sql, int resultSetType, int concurrency, int holdability) throws SQLException {
      return ProxyFactory.getProxyCallableStatement(this, (CallableStatement)this.trackStatement(this.delegate.prepareCall(sql, resultSetType, concurrency, holdability)));
   }

   public PreparedStatement prepareStatement(String sql) throws SQLException {
      return ProxyFactory.getProxyPreparedStatement(this, (PreparedStatement)this.trackStatement(this.delegate.prepareStatement(sql)));
   }

   public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
      return ProxyFactory.getProxyPreparedStatement(this, (PreparedStatement)this.trackStatement(this.delegate.prepareStatement(sql, autoGeneratedKeys)));
   }

   public PreparedStatement prepareStatement(String sql, int resultSetType, int concurrency) throws SQLException {
      return ProxyFactory.getProxyPreparedStatement(this, (PreparedStatement)this.trackStatement(this.delegate.prepareStatement(sql, resultSetType, concurrency)));
   }

   public PreparedStatement prepareStatement(String sql, int resultSetType, int concurrency, int holdability) throws SQLException {
      return ProxyFactory.getProxyPreparedStatement(this, (PreparedStatement)this.trackStatement(this.delegate.prepareStatement(sql, resultSetType, concurrency, holdability)));
   }

   public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
      return ProxyFactory.getProxyPreparedStatement(this, (PreparedStatement)this.trackStatement(this.delegate.prepareStatement(sql, columnIndexes)));
   }

   public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
      return ProxyFactory.getProxyPreparedStatement(this, (PreparedStatement)this.trackStatement(this.delegate.prepareStatement(sql, columnNames)));
   }

   public void commit() throws SQLException {
      this.delegate.commit();
      this.isCommitStateDirty = false;
      this.lastAccess = clockSource.currentTime();
   }

   public void rollback() throws SQLException {
      this.delegate.rollback();
      this.isCommitStateDirty = false;
      this.lastAccess = clockSource.currentTime();
   }

   public void rollback(Savepoint savepoint) throws SQLException {
      this.delegate.rollback(savepoint);
      this.isCommitStateDirty = false;
      this.lastAccess = clockSource.currentTime();
   }

   public void setAutoCommit(boolean autoCommit) throws SQLException {
      this.delegate.setAutoCommit(autoCommit);
      this.isAutoCommit = autoCommit;
      this.dirtyBits |= 2;
   }

   public void setReadOnly(boolean readOnly) throws SQLException {
      this.delegate.setReadOnly(readOnly);
      this.isReadOnly = readOnly;
      this.isCommitStateDirty = false;
      this.dirtyBits |= 1;
   }

   public void setTransactionIsolation(int level) throws SQLException {
      this.delegate.setTransactionIsolation(level);
      this.transactionIsolation = level;
      this.dirtyBits |= 4;
   }

   public void setCatalog(String catalog) throws SQLException {
      this.delegate.setCatalog(catalog);
      this.dbcatalog = catalog;
      this.dirtyBits |= 8;
   }

   public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
      this.delegate.setNetworkTimeout(executor, milliseconds);
      this.networkTimeout = milliseconds;
      this.dirtyBits |= 16;
   }

   public final boolean isWrapperFor(Class iface) throws SQLException {
      return iface.isInstance(this.delegate) || this.delegate instanceof Wrapper && this.delegate.isWrapperFor(iface);
   }

   public final Object unwrap(Class iface) throws SQLException {
      if (iface.isInstance(this.delegate)) {
         return this.delegate;
      } else if (this.delegate instanceof Wrapper) {
         return this.delegate.unwrap(iface);
      } else {
         throw new SQLException("Wrapped connection is not an instance of " + iface);
      }
   }

   static {
      clockSource = ClockSource.INSTANCE;
      ERROR_STATES = new HashSet();
      ERROR_STATES.add("57P01");
      ERROR_STATES.add("57P02");
      ERROR_STATES.add("57P03");
      ERROR_STATES.add("01002");
      ERROR_STATES.add("JZ0C0");
      ERROR_STATES.add("JZ0C1");
      ERROR_CODES = new HashSet();
      ERROR_CODES.add(500150);
      ERROR_CODES.add(2399);
   }

   private static final class ClosedConnection {
      static final Connection CLOSED_CONNECTION = getClosedConnection();

      private static Connection getClosedConnection() {
         InvocationHandler handler = new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
               String methodName = method.getName();
               if ("abort".equals(methodName)) {
                  return Void.TYPE;
               } else if ("isValid".equals(methodName)) {
                  return Boolean.FALSE;
               } else if ("toString".equals(methodName)) {
                  return ClosedConnection.class.getCanonicalName();
               } else {
                  throw new SQLException("Connection is closed");
               }
            }
         };
         return (Connection)Proxy.newProxyInstance(Connection.class.getClassLoader(), new Class[]{Connection.class}, handler);
      }
   }
}
