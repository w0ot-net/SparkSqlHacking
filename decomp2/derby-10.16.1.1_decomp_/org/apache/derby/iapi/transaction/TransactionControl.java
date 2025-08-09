package org.apache.derby.iapi.transaction;

import java.util.ArrayList;
import java.util.Iterator;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.util.ArrayUtil;

public final class TransactionControl {
   public static final int UNSPECIFIED_ISOLATION_LEVEL = 0;
   public static final int READ_UNCOMMITTED_ISOLATION_LEVEL = 1;
   public static final int READ_COMMITTED_ISOLATION_LEVEL = 2;
   public static final int REPEATABLE_READ_ISOLATION_LEVEL = 3;
   public static final int SERIALIZABLE_ISOLATION_LEVEL = 4;
   private static final int[] CS_TO_JDBC_ISOLATION_LEVEL_MAP = new int[]{0, 1, 2, 4, 8};
   private static final String[][] CS_TO_SQL_ISOLATION_MAP = new String[][]{{"  "}, {"UR", "DIRTY READ", "READ UNCOMMITTED"}, {"CS", "CURSOR STABILITY", "READ COMMITTED"}, {"RS"}, {"RR", "REPEATABLE READ", "SERIALIZABLE"}};
   private final ArrayList listeners = new ArrayList();

   public static int jdbcIsolationLevel(int var0) {
      return CS_TO_JDBC_ISOLATION_LEVEL_MAP[var0];
   }

   public static String[] isolationTextNames(int var0) {
      return (String[])ArrayUtil.copy(CS_TO_SQL_ISOLATION_MAP[var0]);
   }

   public static int isolationMapCount() {
      return CS_TO_SQL_ISOLATION_MAP.length;
   }

   public void addListener(TransactionListener var1) {
      this.listeners.add(var1);
   }

   public void removeListener(TransactionListener var1) {
      this.listeners.remove(var1);
   }

   public void preCommitNotify() throws StandardException {
      if (!this.listeners.isEmpty()) {
         Iterator var1 = this.listeners.iterator();

         while(var1.hasNext()) {
            TransactionListener var2 = (TransactionListener)var1.next();

            try {
               if (var2.preCommit()) {
                  var1.remove();
               }
            } catch (StandardException var4) {
               if (var4.getSeverity() < 30000) {
                  throw StandardException.newException("40XT1", var4, new Object[0]);
               }

               throw var4;
            }
         }

      }
   }

   public void preRollbackNotify() throws StandardException {
      if (!this.listeners.isEmpty()) {
         Iterator var1 = this.listeners.iterator();

         while(var1.hasNext()) {
            TransactionListener var2 = (TransactionListener)var1.next();

            try {
               var2.preRollback();
               var1.remove();
            } catch (StandardException var4) {
               if (var4.getSeverity() < 30000) {
               }

               throw var4;
            }
         }

      }
   }
}
