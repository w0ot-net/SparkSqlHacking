package org.datanucleus.store.rdbms.datasource.dbcp.datasources;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Hashtable;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.Name;
import javax.naming.NamingException;
import javax.naming.Reference;
import javax.naming.StringRefAddr;
import javax.sql.ConnectionPoolDataSource;
import org.datanucleus.store.rdbms.datasource.dbcp.SQLNestedException;
import org.datanucleus.store.rdbms.datasource.dbcp.pool.KeyedObjectPool;
import org.datanucleus.store.rdbms.datasource.dbcp.pool.KeyedPoolableObjectFactory;
import org.datanucleus.store.rdbms.datasource.dbcp.pool.impl.GenericKeyedObjectPool;

public class SharedPoolDataSource extends InstanceKeyDataSource {
   private static final long serialVersionUID = -8132305535403690372L;
   private int maxActive = 8;
   private int maxIdle = 8;
   private int maxWait = (int)Math.min(2147483647L, -1L);
   private transient KeyedObjectPool pool = null;
   private transient KeyedCPDSConnectionFactory factory = null;

   public void close() throws Exception {
      if (this.pool != null) {
         this.pool.close();
      }

      InstanceKeyObjectFactory.removeInstance(this.instanceKey);
   }

   public int getMaxActive() {
      return this.maxActive;
   }

   public void setMaxActive(int maxActive) {
      this.assertInitializationAllowed();
      this.maxActive = maxActive;
   }

   public int getMaxIdle() {
      return this.maxIdle;
   }

   public void setMaxIdle(int maxIdle) {
      this.assertInitializationAllowed();
      this.maxIdle = maxIdle;
   }

   public int getMaxWait() {
      return this.maxWait;
   }

   public void setMaxWait(int maxWait) {
      this.assertInitializationAllowed();
      this.maxWait = maxWait;
   }

   public int getNumActive() {
      return this.pool == null ? 0 : this.pool.getNumActive();
   }

   public int getNumIdle() {
      return this.pool == null ? 0 : this.pool.getNumIdle();
   }

   protected PooledConnectionAndInfo getPooledConnectionAndInfo(String username, String password) throws SQLException {
      synchronized(this) {
         if (this.pool == null) {
            try {
               this.registerPool(username, password);
            } catch (NamingException e) {
               throw new SQLNestedException("RegisterPool failed", e);
            }
         }
      }

      PooledConnectionAndInfo info = null;
      UserPassKey key = new UserPassKey(username, password);

      try {
         info = (PooledConnectionAndInfo)this.pool.borrowObject(key);
         return info;
      } catch (Exception e) {
         throw new SQLNestedException("Could not retrieve connection info from pool", e);
      }
   }

   protected PooledConnectionManager getConnectionManager(UserPassKey upkey) {
      return this.factory;
   }

   public Reference getReference() throws NamingException {
      Reference ref = new Reference(this.getClass().getName(), SharedPoolDataSourceFactory.class.getName(), (String)null);
      ref.add(new StringRefAddr("instanceKey", this.instanceKey));
      return ref;
   }

   private void registerPool(String username, String password) throws NamingException, SQLException {
      ConnectionPoolDataSource cpds = this.testCPDS(username, password);
      GenericKeyedObjectPool tmpPool = new GenericKeyedObjectPool((KeyedPoolableObjectFactory)null);
      tmpPool.setMaxActive(this.getMaxActive());
      tmpPool.setMaxIdle(this.getMaxIdle());
      tmpPool.setMaxWait((long)this.getMaxWait());
      tmpPool.setWhenExhaustedAction(this.whenExhaustedAction(this.maxActive, this.maxWait));
      tmpPool.setTestOnBorrow(this.getTestOnBorrow());
      tmpPool.setTestOnReturn(this.getTestOnReturn());
      tmpPool.setTimeBetweenEvictionRunsMillis((long)this.getTimeBetweenEvictionRunsMillis());
      tmpPool.setNumTestsPerEvictionRun(this.getNumTestsPerEvictionRun());
      tmpPool.setMinEvictableIdleTimeMillis((long)this.getMinEvictableIdleTimeMillis());
      tmpPool.setTestWhileIdle(this.getTestWhileIdle());
      this.pool = tmpPool;
      this.factory = new KeyedCPDSConnectionFactory(cpds, this.pool, this.getValidationQuery(), this.isRollbackAfterValidation());
   }

   protected void setupDefaults(Connection con, String username) throws SQLException {
      boolean defaultAutoCommit = this.isDefaultAutoCommit();
      if (con.getAutoCommit() != defaultAutoCommit) {
         con.setAutoCommit(defaultAutoCommit);
      }

      int defaultTransactionIsolation = this.getDefaultTransactionIsolation();
      if (defaultTransactionIsolation != -1) {
         con.setTransactionIsolation(defaultTransactionIsolation);
      }

      boolean defaultReadOnly = this.isDefaultReadOnly();
      if (con.isReadOnly() != defaultReadOnly) {
         con.setReadOnly(defaultReadOnly);
      }

   }

   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
      try {
         in.defaultReadObject();
         SharedPoolDataSource oldDS = (SharedPoolDataSource)(new SharedPoolDataSourceFactory()).getObjectInstance(this.getReference(), (Name)null, (Context)null, (Hashtable)null);
         this.pool = oldDS.pool;
      } catch (NamingException e) {
         throw new IOException("NamingException: " + e);
      }
   }

   public Logger getParentLogger() throws SQLFeatureNotSupportedException {
      throw new SQLFeatureNotSupportedException("Not supported");
   }
}
