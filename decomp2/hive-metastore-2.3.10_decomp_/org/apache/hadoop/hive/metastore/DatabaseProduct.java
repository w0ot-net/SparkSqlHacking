package org.apache.hadoop.hive.metastore;

import java.sql.SQLException;
import java.sql.SQLTransactionRollbackException;

public enum DatabaseProduct {
   DERBY,
   MYSQL,
   POSTGRES,
   ORACLE,
   SQLSERVER,
   OTHER;

   public static DatabaseProduct determineDatabaseProduct(String productName) throws SQLException {
      if (productName == null) {
         return OTHER;
      } else {
         productName = productName.toLowerCase();
         if (productName.contains("derby")) {
            return DERBY;
         } else if (productName.contains("microsoft sql server")) {
            return SQLSERVER;
         } else if (productName.contains("mysql")) {
            return MYSQL;
         } else if (productName.contains("oracle")) {
            return ORACLE;
         } else {
            return productName.contains("postgresql") ? POSTGRES : OTHER;
         }
      }
   }

   public static boolean isDeadlock(DatabaseProduct dbProduct, SQLException e) {
      return e instanceof SQLTransactionRollbackException || (dbProduct == MYSQL || dbProduct == POSTGRES || dbProduct == SQLSERVER) && e.getSQLState().equals("40001") || dbProduct == POSTGRES && e.getSQLState().equals("40P01") || dbProduct == ORACLE && (e.getMessage().contains("deadlock detected") || e.getMessage().contains("can't serialize access for this transaction"));
   }

   public static boolean needsInBatching(DatabaseProduct dbType) {
      return dbType == ORACLE || dbType == SQLSERVER;
   }

   public static boolean hasJoinOperationOrderBug(DatabaseProduct dbType) {
      return dbType == DERBY || dbType == ORACLE;
   }
}
