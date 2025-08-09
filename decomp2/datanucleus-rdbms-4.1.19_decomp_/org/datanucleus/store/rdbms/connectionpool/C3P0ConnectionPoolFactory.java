package org.datanucleus.store.rdbms.connectionpool;

import com.mchange.v2.c3p0.DataSources;
import com.mchange.v2.c3p0.PooledDataSource;
import java.sql.SQLException;
import java.util.Properties;
import javax.sql.DataSource;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.store.StoreManager;
import org.datanucleus.util.ClassUtils;

public class C3P0ConnectionPoolFactory extends AbstractConnectionPoolFactory {
   public ConnectionPool createConnectionPool(StoreManager storeMgr) {
      String dbDriver = storeMgr.getConnectionDriverName();
      String dbURL = storeMgr.getConnectionURL();
      ClassLoaderResolver clr = storeMgr.getNucleusContext().getClassLoaderResolver((ClassLoader)null);
      this.loadDriver(dbDriver, clr);
      ClassUtils.assertClassForJarExistsInClasspath(clr, "com.mchange.v2.c3p0.DataSources", "c3p0.jar");

      try {
         Properties dbProps = getPropertiesForDriver(storeMgr);
         DataSource unpooled = DataSources.unpooledDataSource(dbURL, dbProps);
         Properties c3p0Props = new Properties();
         if (storeMgr.hasProperty("datanucleus.connectionPool.maxStatements")) {
            int size = storeMgr.getIntProperty("datanucleus.connectionPool.maxStatements");
            if (size >= 0) {
               c3p0Props.setProperty("maxStatementsPerConnection", "" + size);
               c3p0Props.setProperty("maxStatements", "" + size);
            }
         }

         if (storeMgr.hasProperty("datanucleus.connectionPool.maxPoolSize")) {
            int size = storeMgr.getIntProperty("datanucleus.connectionPool.maxPoolSize");
            if (size >= 0) {
               c3p0Props.setProperty("maxPoolSize", "" + size);
            }
         }

         if (storeMgr.hasProperty("datanucleus.connectionPool.minPoolSize")) {
            int size = storeMgr.getIntProperty("datanucleus.connectionPool.minPoolSize");
            if (size >= 0) {
               c3p0Props.setProperty("minPoolSize", "" + size);
            }
         }

         if (storeMgr.hasProperty("datanucleus.connectionPool.initialPoolSize")) {
            int size = storeMgr.getIntProperty("datanucleus.connectionPool.initialPoolSize");
            if (size >= 0) {
               c3p0Props.setProperty("initialPoolSize", "" + size);
            }
         }

         PooledDataSource ds = (PooledDataSource)DataSources.pooledDataSource(unpooled, c3p0Props);
         return new C3P0ConnectionPool(ds);
      } catch (SQLException sqle) {
         throw new DatastorePoolException("c3p0", dbDriver, dbURL, sqle);
      }
   }

   public class C3P0ConnectionPool implements ConnectionPool {
      final PooledDataSource dataSource;

      public C3P0ConnectionPool(PooledDataSource ds) {
         this.dataSource = ds;
      }

      public void close() {
         try {
            this.dataSource.close();
         } catch (SQLException var2) {
         }

      }

      public DataSource getDataSource() {
         return this.dataSource;
      }
   }
}
