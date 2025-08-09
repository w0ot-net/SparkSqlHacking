package org.apache.commons.dbcp;

import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Set;
import org.apache.commons.jocl.JOCLContentHandler;
import org.apache.commons.pool.ObjectPool;
import org.xml.sax.SAXException;

public class PoolingDriver implements Driver {
   protected static final HashMap _pools;
   private static boolean accessToUnderlyingConnectionAllowed;
   protected static final String URL_PREFIX = "jdbc:apache:commons:dbcp:";
   protected static final int URL_PREFIX_LEN;
   protected static final int MAJOR_VERSION = 1;
   protected static final int MINOR_VERSION = 0;

   public static synchronized boolean isAccessToUnderlyingConnectionAllowed() {
      return accessToUnderlyingConnectionAllowed;
   }

   public static synchronized void setAccessToUnderlyingConnectionAllowed(boolean allow) {
      accessToUnderlyingConnectionAllowed = allow;
   }

   /** @deprecated */
   public synchronized ObjectPool getPool(String name) {
      try {
         return this.getConnectionPool(name);
      } catch (Exception e) {
         throw new DbcpException(e);
      }
   }

   public synchronized ObjectPool getConnectionPool(String name) throws SQLException {
      ObjectPool pool = (ObjectPool)_pools.get(name);
      if (null == pool) {
         InputStream in = this.getClass().getResourceAsStream(name + ".jocl");
         if (in == null) {
            in = Thread.currentThread().getContextClassLoader().getResourceAsStream(name + ".jocl");
         }

         if (null == in) {
            throw new SQLException("Configuration file not found");
         }

         JOCLContentHandler jocl = null;

         try {
            jocl = JOCLContentHandler.parse(in);
         } catch (SAXException e) {
            throw (SQLException)(new SQLException("Could not parse configuration file")).initCause(e);
         } catch (IOException e) {
            throw (SQLException)(new SQLException("Could not load configuration file")).initCause(e);
         }

         if (jocl.getType(0).equals(String.class)) {
            pool = this.getPool((String)jocl.getValue(0));
            if (null != pool) {
               this.registerPool(name, pool);
            }
         } else {
            pool = ((PoolableConnectionFactory)((PoolableConnectionFactory)jocl.getValue(0))).getPool();
            if (null != pool) {
               this.registerPool(name, pool);
            }
         }
      }

      return pool;
   }

   public synchronized void registerPool(String name, ObjectPool pool) {
      _pools.put(name, pool);
   }

   public synchronized void closePool(String name) throws SQLException {
      ObjectPool pool = (ObjectPool)_pools.get(name);
      if (pool != null) {
         _pools.remove(name);

         try {
            pool.close();
         } catch (Exception e) {
            throw (SQLException)(new SQLException("Error closing pool " + name)).initCause(e);
         }
      }

   }

   public synchronized String[] getPoolNames() {
      Set names = _pools.keySet();
      return (String[])names.toArray(new String[names.size()]);
   }

   public boolean acceptsURL(String url) throws SQLException {
      try {
         return url.startsWith("jdbc:apache:commons:dbcp:");
      } catch (NullPointerException var3) {
         return false;
      }
   }

   public Connection connect(String url, Properties info) throws SQLException {
      if (this.acceptsURL(url)) {
         ObjectPool pool = this.getConnectionPool(url.substring(URL_PREFIX_LEN));
         if (null == pool) {
            throw new SQLException("No pool found for " + url + ".");
         } else {
            try {
               Connection conn = (Connection)pool.borrowObject();
               if (conn != null) {
                  conn = new PoolGuardConnectionWrapper(pool, conn);
               }

               return conn;
            } catch (SQLException e) {
               throw e;
            } catch (NoSuchElementException e) {
               throw (SQLException)(new SQLException("Cannot get a connection, pool error: " + e.getMessage())).initCause(e);
            } catch (RuntimeException e) {
               throw e;
            } catch (Exception e) {
               throw (SQLException)(new SQLException("Cannot get a connection, general error: " + e.getMessage())).initCause(e);
            }
         }
      } else {
         return null;
      }
   }

   public void invalidateConnection(Connection conn) throws SQLException {
      if (conn instanceof PoolGuardConnectionWrapper) {
         PoolGuardConnectionWrapper pgconn = (PoolGuardConnectionWrapper)conn;
         ObjectPool pool = pgconn.pool;
         Connection delegate = pgconn.delegate;

         try {
            pool.invalidateObject(delegate);
         } catch (Exception var6) {
         }

         pgconn.delegate = null;
      } else {
         throw new SQLException("Invalid connection class");
      }
   }

   public int getMajorVersion() {
      return 1;
   }

   public int getMinorVersion() {
      return 0;
   }

   public boolean jdbcCompliant() {
      return true;
   }

   public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) {
      return new DriverPropertyInfo[0];
   }

   static {
      try {
         DriverManager.registerDriver(new PoolingDriver());
      } catch (Exception var1) {
      }

      _pools = new HashMap();
      accessToUnderlyingConnectionAllowed = false;
      URL_PREFIX_LEN = "jdbc:apache:commons:dbcp:".length();
   }

   private static class PoolGuardConnectionWrapper extends DelegatingConnection {
      private final ObjectPool pool;
      private Connection delegate;

      PoolGuardConnectionWrapper(ObjectPool pool, Connection delegate) {
         super(delegate);
         this.pool = pool;
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

      public boolean equals(Object obj) {
         return this.delegate == null ? false : this.delegate.equals(obj);
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
         return PoolingDriver.isAccessToUnderlyingConnectionAllowed() ? super.getDelegate() : null;
      }

      public Connection getInnermostDelegate() {
         return PoolingDriver.isAccessToUnderlyingConnectionAllowed() ? super.getInnermostDelegate() : null;
      }
   }
}
