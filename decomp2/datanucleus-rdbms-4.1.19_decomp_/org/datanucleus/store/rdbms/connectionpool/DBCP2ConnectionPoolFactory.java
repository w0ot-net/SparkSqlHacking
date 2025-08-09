package org.datanucleus.store.rdbms.connectionpool;

import java.util.Properties;
import javax.management.ObjectName;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDataSource;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.store.StoreManager;
import org.datanucleus.util.ClassUtils;

public class DBCP2ConnectionPoolFactory extends AbstractConnectionPoolFactory {
   public ConnectionPool createConnectionPool(StoreManager storeMgr) {
      String dbDriver = storeMgr.getConnectionDriverName();
      String dbURL = storeMgr.getConnectionURL();
      ClassLoaderResolver clr = storeMgr.getNucleusContext().getClassLoaderResolver((ClassLoader)null);
      this.loadDriver(dbDriver, clr);
      ClassUtils.assertClassForJarExistsInClasspath(clr, "org.apache.commons.pool2.ObjectPool", "commons-pool-2.x.jar");
      ClassUtils.assertClassForJarExistsInClasspath(clr, "org.apache.commons.dbcp2.ConnectionFactory", "commons-dbcp-2.x.jar");
      PoolingDataSource ds = null;

      GenericObjectPool<PoolableConnection> connectionPool;
      try {
         Properties dbProps = getPropertiesForDriver(storeMgr);
         ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(dbURL, dbProps);
         PoolableConnectionFactory poolableCF = null;
         poolableCF = new PoolableConnectionFactory(connectionFactory, (ObjectName)null);
         String testSQL = null;
         if (storeMgr.hasProperty("datanucleus.connectionPool.testSQL")) {
            testSQL = storeMgr.getStringProperty("datanucleus.connectionPool.testSQL");
            poolableCF.setValidationQuery(testSQL);
         }

         connectionPool = new GenericObjectPool(poolableCF);
         poolableCF.setPool(connectionPool);
         if (testSQL != null) {
            connectionPool.setTestOnBorrow(true);
         }

         if (storeMgr.hasProperty("datanucleus.connectionPool.maxIdle")) {
            int value = storeMgr.getIntProperty("datanucleus.connectionPool.maxIdle");
            if (value > 0) {
               connectionPool.setMaxIdle(value);
            }
         }

         if (storeMgr.hasProperty("datanucleus.connectionPool.minIdle")) {
            int value = storeMgr.getIntProperty("datanucleus.connectionPool.minIdle");
            if (value > 0) {
               connectionPool.setMinIdle(value);
            }
         }

         if (storeMgr.hasProperty("datanucleus.connectionPool.maxActive")) {
            int value = storeMgr.getIntProperty("datanucleus.connectionPool.maxActive");
            if (value > 0) {
               connectionPool.setMaxTotal(value);
            }
         }

         if (storeMgr.hasProperty("datanucleus.connectionPool.maxWait")) {
            int value = storeMgr.getIntProperty("datanucleus.connectionPool.maxWait");
            if (value > 0) {
               connectionPool.setMaxWaitMillis((long)value);
            }
         }

         if (storeMgr.hasProperty("datanucleus.connectionPool.timeBetweenEvictionRunsMillis")) {
            int value = storeMgr.getIntProperty("datanucleus.connectionPool.timeBetweenEvictionRunsMillis");
            if (value > 0) {
               connectionPool.setTimeBetweenEvictionRunsMillis((long)value);
               int maxIdle = connectionPool.getMaxIdle();
               int numTestsPerEvictionRun = (int)Math.ceil((double)maxIdle / (double)4.0F);
               connectionPool.setNumTestsPerEvictionRun(numTestsPerEvictionRun);
            }
         }

         if (storeMgr.hasProperty("datanucleus.connectionPool.minEvictableIdleTimeMillis")) {
            int value = storeMgr.getIntProperty("datanucleus.connectionPool.minEvictableIdleTimeMillis");
            if (value > 0) {
               connectionPool.setMinEvictableIdleTimeMillis((long)value);
            }
         }

         ds = new PoolingDataSource(connectionPool);
      } catch (Exception e) {
         throw new DatastorePoolException("DBCP2", dbDriver, dbURL, e);
      }

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
