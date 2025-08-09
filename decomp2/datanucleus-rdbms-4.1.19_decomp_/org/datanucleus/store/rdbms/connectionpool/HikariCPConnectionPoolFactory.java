package org.datanucleus.store.rdbms.connectionpool;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.store.StoreManager;
import org.datanucleus.util.ClassUtils;

public class HikariCPConnectionPoolFactory extends AbstractConnectionPoolFactory {
   public ConnectionPool createConnectionPool(StoreManager storeMgr) {
      ClassLoaderResolver clr = storeMgr.getNucleusContext().getClassLoaderResolver((ClassLoader)null);
      ClassUtils.assertClassForJarExistsInClasspath(clr, "com.zaxxer.hikari.HikariConfig", "hikaricp.jar");
      HikariConfig config = new HikariConfig();
      config.setJdbcUrl(storeMgr.getConnectionURL());
      String dbUser = storeMgr.getConnectionUserName();
      if (dbUser == null) {
         dbUser = "";
      }

      config.setUsername(dbUser);
      String dbPassword = storeMgr.getConnectionPassword();
      if (dbPassword == null) {
         dbPassword = "";
      }

      config.setPassword(dbPassword);
      if (storeMgr.getConnectionDriverName() != null) {
         String dbDriver = storeMgr.getConnectionDriverName();
         this.loadDriver(dbDriver, clr);
         config.setDriverClassName(dbDriver);
      }

      if (storeMgr.hasProperty("datanucleus.connectionPool.maxPoolSize")) {
         int size = storeMgr.getIntProperty("datanucleus.connectionPool.maxPoolSize");
         if (size >= 0) {
            config.setMaximumPoolSize(size);
         }
      }

      if (storeMgr.hasProperty("datanucleus.connectionPool.minIdle")) {
         int idle = storeMgr.getIntProperty("datanucleus.connectionPool.minIdle");
         if (idle >= 0) {
            config.setMinimumIdle(idle);
         }
      }

      if (storeMgr.hasProperty("datanucleus.connectionPool.maxIdle")) {
         long idle = (long)storeMgr.getIntProperty("datanucleus.connectionPool.maxIdle");
         if (idle >= 0L) {
            config.setIdleTimeout(idle);
         }
      }

      if (storeMgr.hasProperty("datanucleus.connectionPool.leakThreshold")) {
         long threshold = (long)storeMgr.getIntProperty("datanucleus.connectionPool.leakThreshold");
         if (threshold >= 0L) {
            config.setLeakDetectionThreshold(threshold);
         }
      }

      if (storeMgr.hasProperty("datanucleus.connectionPool.maxLifetime")) {
         long maxLifeTime = (long)storeMgr.getIntProperty("datanucleus.connectionPool.maxLifetime");
         if (maxLifeTime >= 0L) {
            config.setMaxLifetime(maxLifeTime);
         }
      }

      if (storeMgr.hasProperty("datanucleus.connectionPool.autoCommit")) {
         boolean autoCommit = storeMgr.getBooleanProperty("datanucleus.connectionPool.autoCommit");
         config.setAutoCommit(autoCommit);
      }

      if (storeMgr.hasProperty("datanucleus.connectionPool.connectionWaitTimeout")) {
         long connectionTimeout = (long)storeMgr.getIntProperty("datanucleus.connectionPool.connectionWaitTimeout");
         if (connectionTimeout >= 0L) {
            config.setConnectionTimeout(connectionTimeout);
         }
      }

      if (storeMgr.hasProperty("datanucleus.connectionPool.testSQL")) {
         String connectionTestQuery = storeMgr.getStringProperty("datanucleus.connectionPool.testSQL");
         config.setConnectionTestQuery(connectionTestQuery);
      }

      if (storeMgr.hasProperty("datanucleus.connectionPool.name")) {
         String poolName = storeMgr.getStringProperty("datanucleus.connectionPool.name");
         config.setPoolName(poolName);
      }

      if (storeMgr.hasProperty("datanucleus.connectionPool.transactionIsolation")) {
         String transactionIsolation = storeMgr.getStringProperty("datanucleus.connectionPool.transactionIsolation");
         config.setTransactionIsolation(transactionIsolation);
      }

      if (storeMgr.hasProperty("datanucleus.connectionPool.catalog")) {
         String catalog = storeMgr.getStringProperty("datanucleus.connectionPool.catalog");
         config.setCatalog(catalog);
      }

      if (storeMgr.hasProperty("datanucleus.connectionPool.readOnly")) {
         boolean readOnly = storeMgr.getBooleanProperty("datanucleus.connectionPool.readOnly");
         config.setReadOnly(readOnly);
      }

      if (storeMgr.hasProperty("datanucleus.connectionPool.allowPoolSuspension")) {
         boolean allowPoolSuspension = storeMgr.getBooleanProperty("datanucleus.connectionPool.allowPoolSuspension");
         config.setAllowPoolSuspension(allowPoolSuspension);
      }

      if (storeMgr.hasProperty("datanucleus.connectionPool.validationTimeout")) {
         long validationTimeout = (long)storeMgr.getIntProperty("datanucleus.connectionPool.validationTimeout");
         if (validationTimeout >= 0L) {
            config.setValidationTimeout(validationTimeout);
         }
      }

      HikariDataSource ds = new HikariDataSource(config);
      return new HikariCPConnectionPool(ds);
   }

   public class HikariCPConnectionPool implements ConnectionPool {
      final HikariDataSource dataSource;

      public HikariCPConnectionPool(HikariDataSource ds) {
         this.dataSource = ds;
      }

      public void close() {
         this.dataSource.close();
      }

      public DataSource getDataSource() {
         return this.dataSource;
      }
   }
}
