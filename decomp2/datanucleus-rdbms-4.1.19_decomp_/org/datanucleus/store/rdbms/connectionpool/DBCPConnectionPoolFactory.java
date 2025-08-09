package org.datanucleus.store.rdbms.connectionpool;

import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.KeyedObjectPoolFactory;
import org.apache.commons.pool.KeyedPoolableObjectFactory;
import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.PoolableObjectFactory;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.commons.pool.impl.StackKeyedObjectPoolFactory;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.store.StoreManager;
import org.datanucleus.util.ClassUtils;

public class DBCPConnectionPoolFactory extends AbstractConnectionPoolFactory {
   public ConnectionPool createConnectionPool(StoreManager storeMgr) {
      String dbDriver = storeMgr.getConnectionDriverName();
      String dbURL = storeMgr.getConnectionURL();
      ClassLoaderResolver clr = storeMgr.getNucleusContext().getClassLoaderResolver((ClassLoader)null);
      this.loadDriver(dbDriver, clr);
      ClassUtils.assertClassForJarExistsInClasspath(clr, "org.apache.commons.pool.ObjectPool", "commons-pool.jar");
      ClassUtils.assertClassForJarExistsInClasspath(clr, "org.apache.commons.dbcp.ConnectionFactory", "commons-dbcp.jar");
      ObjectPool connectionPool = new GenericObjectPool((PoolableObjectFactory)null);
      if (storeMgr.hasProperty("datanucleus.connectionPool.maxIdle")) {
         int value = storeMgr.getIntProperty("datanucleus.connectionPool.maxIdle");
         if (value > 0) {
            ((GenericObjectPool)connectionPool).setMaxIdle(value);
         }
      }

      if (storeMgr.hasProperty("datanucleus.connectionPool.minIdle")) {
         int value = storeMgr.getIntProperty("datanucleus.connectionPool.minIdle");
         if (value > 0) {
            ((GenericObjectPool)connectionPool).setMinIdle(value);
         }
      }

      if (storeMgr.hasProperty("datanucleus.connectionPool.maxActive")) {
         int value = storeMgr.getIntProperty("datanucleus.connectionPool.maxActive");
         if (value > 0) {
            ((GenericObjectPool)connectionPool).setMaxActive(value);
         }
      }

      if (storeMgr.hasProperty("datanucleus.connectionPool.maxWait")) {
         int value = storeMgr.getIntProperty("datanucleus.connectionPool.maxWait");
         if (value > 0) {
            ((GenericObjectPool)connectionPool).setMaxWait((long)value);
         }
      }

      if (storeMgr.hasProperty("datanucleus.connectionPool.timeBetweenEvictionRunsMillis")) {
         int value = storeMgr.getIntProperty("datanucleus.connectionPool.timeBetweenEvictionRunsMillis");
         if (value > 0) {
            ((GenericObjectPool)connectionPool).setTimeBetweenEvictionRunsMillis((long)value);
            int maxIdle = ((GenericObjectPool)connectionPool).getMaxIdle();
            int numTestsPerEvictionRun = (int)Math.ceil((double)maxIdle / (double)4.0F);
            ((GenericObjectPool)connectionPool).setNumTestsPerEvictionRun(numTestsPerEvictionRun);
         }
      }

      if (storeMgr.hasProperty("datanucleus.connectionPool.minEvictableIdleTimeMillis")) {
         int value = storeMgr.getIntProperty("datanucleus.connectionPool.minEvictableIdleTimeMillis");
         if (value > 0) {
            ((GenericObjectPool)connectionPool).setMinEvictableIdleTimeMillis((long)value);
         }
      }

      Properties dbProps = getPropertiesForDriver(storeMgr);
      ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(dbURL, dbProps);
      KeyedObjectPoolFactory kpf = null;
      if (storeMgr.hasProperty("datanucleus.connectionPool.maxStatements")) {
         int value = storeMgr.getIntProperty("datanucleus.connectionPool.maxStatements");
         if (value > 0) {
            kpf = new StackKeyedObjectPoolFactory((KeyedPoolableObjectFactory)null, value);
         }
      }

      try {
         String testSQL = null;
         if (storeMgr.hasProperty("datanucleus.connectionPool.testSQL")) {
            testSQL = storeMgr.getStringProperty("datanucleus.connectionPool.testSQL");
         }

         new PoolableConnectionFactory(connectionFactory, connectionPool, kpf, testSQL, false, false);
         if (testSQL != null) {
            ((GenericObjectPool)connectionPool).setTestOnBorrow(true);
         }
      } catch (Exception e) {
         throw new DatastorePoolException("DBCP", dbDriver, dbURL, e);
      }

      PoolingDataSource ds = new PoolingDataSource(connectionPool);
      return new DBCPConnectionPool(ds, connectionPool);
   }

   public class DBCPConnectionPool implements ConnectionPool {
      final PoolingDataSource dataSource;
      final ObjectPool pool;

      public DBCPConnectionPool(PoolingDataSource ds, ObjectPool pool) {
         this.dataSource = ds;
         this.pool = pool;
      }

      public void close() {
         try {
            this.pool.close();
         } catch (Exception var2) {
         }

      }

      public DataSource getDataSource() {
         return this.dataSource;
      }
   }
}
