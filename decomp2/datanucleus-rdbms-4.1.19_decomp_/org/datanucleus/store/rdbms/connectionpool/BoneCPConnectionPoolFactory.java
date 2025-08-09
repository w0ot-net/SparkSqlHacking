package org.datanucleus.store.rdbms.connectionpool;

import com.jolbox.bonecp.BoneCPConfig;
import com.jolbox.bonecp.BoneCPDataSource;
import java.util.Properties;
import javax.sql.DataSource;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.store.StoreManager;
import org.datanucleus.util.ClassUtils;

public class BoneCPConnectionPoolFactory extends AbstractConnectionPoolFactory {
   public ConnectionPool createConnectionPool(StoreManager storeMgr) {
      String dbDriver = storeMgr.getConnectionDriverName();
      String dbURL = storeMgr.getConnectionURL();
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
      ClassUtils.assertClassForJarExistsInClasspath(clr, "com.jolbox.bonecp.BoneCPDataSource", "bonecp.jar");
      BoneCPConfig config = new BoneCPConfig();
      config.setUsername(dbUser);
      config.setPassword(dbPassword);
      Properties dbProps = getPropertiesForDriver(storeMgr);
      config.setDriverProperties(dbProps);
      BoneCPDataSource ds = new BoneCPDataSource(config);
      if (storeMgr.hasProperty("datanucleus.connectionPool.maxStatements")) {
         int size = storeMgr.getIntProperty("datanucleus.connectionPool.maxStatements");
         if (size >= 0) {
            ds.setStatementsCacheSize(size);
         }
      }

      if (storeMgr.hasProperty("datanucleus.connectionPool.maxPoolSize")) {
         int size = storeMgr.getIntProperty("datanucleus.connectionPool.maxPoolSize");
         if (size >= 0) {
            ds.setMaxConnectionsPerPartition(size);
         }
      }

      if (storeMgr.hasProperty("datanucleus.connectionPool.minPoolSize")) {
         int size = storeMgr.getIntProperty("datanucleus.connectionPool.minPoolSize");
         if (size >= 0) {
            ds.setMinConnectionsPerPartition(size);
         }
      }

      if (storeMgr.hasProperty("datanucleus.connectionPool.maxIdle")) {
         int value = storeMgr.getIntProperty("datanucleus.connectionPool.maxIdle");
         if (value > 0) {
            ds.setIdleMaxAgeInMinutes((long)value);
         }
      }

      ds.setJdbcUrl(dbURL);
      ds.setUsername(dbUser);
      ds.setPassword(dbPassword);
      return new BoneCPConnectionPool(ds);
   }

   public class BoneCPConnectionPool implements ConnectionPool {
      final BoneCPDataSource dataSource;

      public BoneCPConnectionPool(BoneCPDataSource ds) {
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
