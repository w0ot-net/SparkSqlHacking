package org.datanucleus.store.rdbms.connectionpool;

import java.util.Properties;
import javax.sql.DataSource;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.store.StoreManager;
import org.datanucleus.util.ClassUtils;
import org.logicalcobwebs.proxool.ProxoolDataSource;
import org.logicalcobwebs.proxool.ProxoolException;
import org.logicalcobwebs.proxool.ProxoolFacade;

public class ProxoolConnectionPoolFactory extends AbstractConnectionPoolFactory {
   private static int poolNumber = 0;

   public ConnectionPool createConnectionPool(StoreManager storeMgr) {
      String dbDriver = storeMgr.getConnectionDriverName();
      String dbURL = storeMgr.getConnectionURL();
      ClassLoaderResolver clr = storeMgr.getNucleusContext().getClassLoaderResolver((ClassLoader)null);
      this.loadDriver(dbDriver, clr);
      ClassUtils.assertClassForJarExistsInClasspath(clr, "org.apache.commons.logging.Log", "commons-logging.jar");
      ClassUtils.assertClassForJarExistsInClasspath(clr, "org.logicalcobwebs.proxool.ProxoolDriver", "proxool.jar");
      String alias = "datanucleus" + poolNumber;
      String poolURL = null;

      try {
         Properties dbProps = getPropertiesForDriver(storeMgr);
         if (storeMgr.hasProperty("datanucleus.connectionPool.maxConnections")) {
            int value = storeMgr.getIntProperty("datanucleus.connectionPool.maxConnections");
            if (value > 0) {
               dbProps.put("proxool.maximum-connection-count", "" + value);
            } else {
               dbProps.put("proxool.maximum-connection-count", "10");
            }
         } else {
            dbProps.put("proxool.maximum-connection-count", "10");
         }

         if (storeMgr.hasProperty("datanucleus.connectionPool.testSQL")) {
            String value = storeMgr.getStringProperty("datanucleus.connectionPool.testSQL");
            dbProps.put("proxool.house-keeping-test-sql", value);
         } else {
            dbProps.put("proxool.house-keeping-test-sql", "SELECT 1");
         }

         poolURL = "proxool." + alias + ":" + dbDriver + ":" + dbURL;
         ++poolNumber;
         ProxoolFacade.registerConnectionPool(poolURL, dbProps);
      } catch (ProxoolException pe) {
         pe.printStackTrace();
         throw new DatastorePoolException("Proxool", dbDriver, dbURL, pe);
      }

      ProxoolDataSource ds = new ProxoolDataSource(alias);
      return new ProxoolConnectionPool(ds, poolURL);
   }

   public class ProxoolConnectionPool implements ConnectionPool {
      final String poolURL;
      final ProxoolDataSource dataSource;

      public ProxoolConnectionPool(ProxoolDataSource ds, String poolURL) {
         this.dataSource = ds;
         this.poolURL = poolURL;
      }

      public void close() {
         try {
            ProxoolFacade.removeConnectionPool(this.poolURL);
         } catch (ProxoolException var2) {
         }

      }

      public DataSource getDataSource() {
         return this.dataSource;
      }
   }
}
