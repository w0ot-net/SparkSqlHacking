package org.datanucleus.transaction;

public class TransactionUtils {
   public static String getNameForTransactionIsolationLevel(int isolation) {
      if (isolation == 0) {
         return "none";
      } else if (isolation == 2) {
         return "read-committed";
      } else if (isolation == 1) {
         return "read-uncommitted";
      } else if (isolation == 4) {
         return "repeatable-read";
      } else {
         return isolation == 8 ? "serializable" : "UNKNOWN";
      }
   }

   public static int getTransactionIsolationLevelForName(String isolationName) {
      if (isolationName.equalsIgnoreCase("none")) {
         return 0;
      } else if (isolationName.equalsIgnoreCase("read-committed")) {
         return 2;
      } else if (isolationName.equalsIgnoreCase("read-uncommitted")) {
         return 1;
      } else if (isolationName.equalsIgnoreCase("repeatable-read")) {
         return 4;
      } else {
         return isolationName.equalsIgnoreCase("serializable") ? 8 : -1;
      }
   }
}
