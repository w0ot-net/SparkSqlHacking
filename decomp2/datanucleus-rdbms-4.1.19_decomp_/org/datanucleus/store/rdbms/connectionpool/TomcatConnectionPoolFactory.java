package org.datanucleus.store.rdbms.connectionpool;

import java.util.Properties;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.store.StoreManager;
import org.datanucleus.util.ClassUtils;

public class TomcatConnectionPoolFactory extends AbstractConnectionPoolFactory {
   public ConnectionPool createConnectionPool(StoreManager storeMgr) {
      String dbURL = storeMgr.getConnectionURL();
      String dbDriver = storeMgr.getConnectionDriverName();
      if (dbDriver == null) {
         dbDriver = "";
      }

      String dbUser = storeMgr.getConnectionUserName();
      if (dbUser == null) {
         dbUser = "";
      }

      String dbPassword = storeMgr.getConnectionPassword();
      if (dbPassword == null) {
         dbPassword = "";
      }

      ClassLoaderResolver clr = storeMgr.getNucleusContext().getClassLoaderResolver((ClassLoader)null);
      this.loadDriver(dbDriver, clr);
      ClassUtils.assertClassForJarExistsInClasspath(clr, "org.apache.tomcat.jdbc.pool.DataSource", "tomcat-jdbc.jar");
      PoolProperties config = new PoolProperties();
      config.setUrl(dbURL);
      config.setDriverClassName(dbDriver);
      config.setUsername(dbUser);
      config.setPassword(dbPassword);
      Properties dbProps = getPropertiesForDriver(storeMgr);
      config.setDbProperties(dbProps);
      if (storeMgr.hasProperty("datanucleus.connectionPool.abandonWhenPercentageFull")) {
         int value = storeMgr.getIntProperty("datanucleus.connectionPool.abandonWhenPercentageFull");
         if (value >= 0) {
            config.setAbandonWhenPercentageFull(value);
         }
      }

      if (storeMgr.hasProperty("datanucleus.connectionPool.initialPoolSize")) {
         int size = storeMgr.getIntProperty("datanucleus.connectionPool.initialPoolSize");
         if (size > 0) {
            config.setInitialSize(size);
         }
      }

      if (storeMgr.hasProperty("datanucleus.connectionPool.initSQL")) {
         String value = storeMgr.getStringProperty("datanucleus.connectionPool.initSQL");
         config.setInitSQL(value);
      }

      if (storeMgr.hasProperty("datanucleus.connectionPool.jdbcInterceptors")) {
         String value = storeMgr.getStringProperty("datanucleus.connectionPool.jdbcInterceptors");
         config.setJdbcInterceptors(value);
      }

      if (storeMgr.hasProperty("datanucleus.connectionPool.logAbandonded")) {
         String value = storeMgr.getStringProperty("datanucleus.connectionPool.logAbandonded");
         config.setLogAbandoned(Boolean.parseBoolean(value));
      }

      if (storeMgr.hasProperty("datanucleus.connectionPool.maxActive")) {
         int size = storeMgr.getIntProperty("datanucleus.connectionPool.maxActive");
         if (size > 0) {
            config.setMaxActive(size);
         }
      }

      if (storeMgr.hasProperty("datanucleus.connectionPool.maxAge")) {
         int value = storeMgr.getIntProperty("datanucleus.connectionPool.maxAge");
         if (value >= 0) {
            config.setMaxAge((long)value);
         }
      }

      if (storeMgr.hasProperty("datanucleus.connectionPool.maxIdle")) {
         int size = storeMgr.getIntProperty("datanucleus.connectionPool.maxIdle");
         if (size >= 0) {
            config.setMaxIdle(size);
         }
      }

      if (storeMgr.hasProperty("datanucleus.connectionPool.maxWait")) {
         int value = storeMgr.getIntProperty("datanucleus.connectionPool.maxWait");
         if (value > 0) {
            config.setMaxWait(value);
         }
      }

      if (storeMgr.hasProperty("datanucleus.connectionPool.minEvictableIdleTimeMillis")) {
         int value = storeMgr.getIntProperty("datanucleus.connectionPool.minEvictableIdleTimeMillis");
         if (value > 0) {
            config.setMinEvictableIdleTimeMillis(value);
         }
      }

      if (storeMgr.hasProperty("datanucleus.connectionPool.minIdle")) {
         int value = storeMgr.getIntProperty("datanucleus.connectionPool.minIdle");
         if (value >= 0) {
            config.setMinIdle(value);
         }
      }

      if (storeMgr.hasProperty("datanucleus.connectionPool.removeAbandonded")) {
         String value = storeMgr.getStringProperty("datanucleus.connectionPool.removeAbandonded");
         config.setRemoveAbandoned(Boolean.parseBoolean(value));
      }

      if (storeMgr.hasProperty("datanucleus.connectionPool.removeAbandondedTimeout")) {
         int value = storeMgr.getIntProperty("datanucleus.connectionPool.removeAbandondedTimeout");
         if (value > 0) {
            config.setRemoveAbandonedTimeout(value);
         }
      }

      if (storeMgr.hasProperty("datanucleus.connectionPool.suspectTimeout")) {
         int value = storeMgr.getIntProperty("datanucleus.connectionPool.suspectTimeout");
         if (value > 0) {
            config.setSuspectTimeout(value);
         }
      }

      if (storeMgr.hasProperty("datanucleus.connectionPool.testOnBorrow")) {
         String value = storeMgr.getStringProperty("datanucleus.connectionPool.testOnBorrow");
         config.setTestOnBorrow(Boolean.parseBoolean(value));
      }

      if (storeMgr.hasProperty("datanucleus.connectionPool.testOnConnect")) {
         String value = storeMgr.getStringProperty("datanucleus.connectionPool.testOnConnect");
         config.setTestOnConnect(Boolean.parseBoolean(value));
      }

      if (storeMgr.hasProperty("datanucleus.connectionPool.testOnReturn")) {
         String value = storeMgr.getStringProperty("datanucleus.connectionPool.testOnReturn");
         config.setTestOnReturn(Boolean.parseBoolean(value));
      }

      if (storeMgr.hasProperty("datanucleus.connectionPool.testWhileIdle")) {
         String value = storeMgr.getStringProperty("datanucleus.connectionPool.testWhileIdle");
         config.setTestWhileIdle(Boolean.parseBoolean(value));
      }

      if (storeMgr.hasProperty("datanucleus.connectionPool.timeBetweenEvictionRunsMillis")) {
         int value = storeMgr.getIntProperty("datanucleus.connectionPool.timeBetweenEvictionRunsMillis");
         if (value > 0) {
            config.setTimeBetweenEvictionRunsMillis(value);
         }
      }

      if (storeMgr.hasProperty("datanucleus.connectionPool.validationInterval")) {
         int value = storeMgr.getIntProperty("datanucleus.connectionPool.validationInterval");
         if (value >= 0) {
            config.setValidationInterval((long)value);
         }
      }

      if (storeMgr.hasProperty("datanucleus.connectionPool.validationQuery")) {
         String value = storeMgr.getStringProperty("datanucleus.connectionPool.validationQuery");
         config.setValidationQuery(value);
      }

      return new TomcatConnectionPool(new DataSource(config));
   }

   public class TomcatConnectionPool implements ConnectionPool {
      final DataSource dataSource;

      public TomcatConnectionPool(DataSource ds) {
         this.dataSource = ds;
      }

      public void close() {
         this.dataSource.close();
      }

      public javax.sql.DataSource getDataSource() {
         return this.dataSource;
      }
   }
}
