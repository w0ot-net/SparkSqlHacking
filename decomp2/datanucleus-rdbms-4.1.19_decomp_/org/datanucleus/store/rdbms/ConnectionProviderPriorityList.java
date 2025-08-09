package org.datanucleus.store.rdbms;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

public class ConnectionProviderPriorityList implements ConnectionProvider {
   private boolean failOnError;

   public void setFailOnError(boolean flag) {
      this.failOnError = flag;
   }

   public Connection getConnection(DataSource[] ds) throws SQLException {
      if (ds == null) {
         return null;
      } else {
         int i = 0;

         while(true) {
            if (i < ds.length) {
               try {
                  return ds[i].getConnection();
               } catch (SQLException e) {
                  if (!this.failOnError && i != ds.length - 1) {
                     ++i;
                     continue;
                  }

                  throw e;
               }
            }

            return null;
         }
      }
   }
}
