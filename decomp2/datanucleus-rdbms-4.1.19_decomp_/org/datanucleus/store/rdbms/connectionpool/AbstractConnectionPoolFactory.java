package org.datanucleus.store.rdbms.connectionpool;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.StringTokenizer;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.Configuration;
import org.datanucleus.store.StoreManager;

public abstract class AbstractConnectionPoolFactory implements ConnectionPoolFactory {
   protected void loadDriver(String dbDriver, ClassLoaderResolver clr) {
      try {
         clr.classForName(dbDriver).newInstance();
      } catch (Exception var6) {
         try {
            Class.forName(dbDriver).newInstance();
         } catch (Exception var5) {
            throw new DatastoreDriverNotFoundException(dbDriver);
         }
      }

   }

   public static Properties getPropertiesForDriver(StoreManager storeMgr) {
      Properties dbProps = new Properties();
      String dbUser = storeMgr.getConnectionUserName();
      if (dbUser == null) {
         dbUser = "";
      }

      dbProps.setProperty("user", dbUser);
      String dbPassword = storeMgr.getConnectionPassword();
      if (dbPassword == null) {
         dbPassword = "";
      }

      dbProps.setProperty("password", dbPassword);
      Configuration conf = storeMgr.getNucleusContext().getConfiguration();
      String drvPropsString = (String)conf.getProperty("datanucleus.connectionPool.driverProps");
      if (drvPropsString != null) {
         ByteArrayOutputStream baos = new ByteArrayOutputStream();
         PrintWriter pw = new PrintWriter(baos);
         StringTokenizer st = new StringTokenizer(drvPropsString, ",");

         while(st.hasMoreTokens()) {
            String prop = st.nextToken();
            pw.println(prop);
         }

         pw.flush();
         ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
         Properties drvProps = new Properties();

         try {
            drvProps.load(bais);
         } catch (IOException var12) {
         }

         dbProps.putAll(drvProps);
      }

      return dbProps;
   }
}
