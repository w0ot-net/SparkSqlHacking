package org.apache.derby.impl.sql.execute;

import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.execute.ExecIndexRow;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.shared.common.error.StandardException;

public class RowUtil {
   private static long rowCountBase = 0L;

   public static void setRowCountBase(long var0) {
   }

   public static long getRowCountBase() {
      return rowCountBase;
   }

   public static ExecRow getEmptyValueRow(int var0, LanguageConnectionContext var1) {
      return var1.getLanguageConnectionFactory().getExecutionFactory().getValueRow(var0);
   }

   public static ExecIndexRow getEmptyIndexRow(int var0, LanguageConnectionContext var1) {
      return var1.getLanguageConnectionFactory().getExecutionFactory().getIndexableRow(var0);
   }

   public static void copyCloneColumns(ExecRow var0, ExecRow var1, int var2) {
      for(int var3 = 1; var3 <= var2; ++var3) {
         var0.setColumn(var3, var1.cloneColumn(var3));
      }

   }

   public static void copyRefColumns(ExecRow var0, ExecRow var1) {
      DataValueDescriptor[] var2 = var1.getRowArray();
      DataValueDescriptor[] var3 = var0.getRowArray();
      System.arraycopy(var2, 0, var3, 0, var2.length);
   }

   public static void copyRefColumns(ExecRow var0, ExecRow var1, int var2) throws StandardException {
      copyRefColumns(var0, 0, var1, 0, var2);
   }

   public static void copyRefColumns(ExecRow var0, ExecRow var1, int var2, int var3) throws StandardException {
      copyRefColumns(var0, 0, var1, var2, var3);
   }

   public static void copyRefColumns(ExecRow var0, int var1, ExecRow var2, int var3, int var4) throws StandardException {
      for(int var5 = 1; var5 <= var4; ++var5) {
         var0.setColumn(var5 + var1, var2.getColumn(var5 + var3));
      }

   }

   public static void copyRefColumns(ExecRow var0, ExecRow var1, int[] var2) throws StandardException {
      if (var2 != null) {
         int var3 = var2.length;

         for(int var4 = 0; var4 < var3; ++var4) {
            var0.setColumn(var4 + 1, var1.getColumn(var2[var4]));
         }

      }
   }

   public static void copyRefColumns(ExecRow var0, ExecRow var1, FormatableBitSet var2) throws StandardException {
      if (var2 != null) {
         int var3 = var0.getRowArray().length;
         int var4 = 1;

         for(int var5 = 1; var4 <= var3; ++var4) {
            if (var2.get(var4)) {
               var0.setColumn(var4, var1.getColumn(var5));
               ++var5;
            }
         }

      }
   }

   public static void copyRefColumns(ExecRow var0) throws StandardException {
      for(int var1 = 1; var1 <= var0.nColumns(); ++var1) {
         var0.setColumn(var1, (DataValueDescriptor)null);
      }

   }

   public static String toString(ExecRow var0) {
      return "";
   }

   public static String toString(Object[] var0) {
      return "";
   }

   public static String toString(ExecRow var0, int var1, int var2) {
      return toString((Object[])var0.getRowArray(), var1, var2);
   }

   public static String toString(Object[] var0, int var1, int var2) {
      StringBuffer var3 = new StringBuffer();
      var3.append("(");

      for(int var4 = var1; var4 <= var2; ++var4) {
         if (var4 > 0) {
            var3.append(",");
         }

         var3.append(var0[var4]);
      }

      var3.append(")");
      return var3.toString();
   }

   public static String toString(ExecRow var0, int[] var1) {
      return toString((Object[])var0.getRowArray(), var1);
   }

   public static String toString(Object[] var0, int[] var1) {
      if (var1 == null) {
         return (String)null;
      } else {
         StringBuffer var2 = new StringBuffer();
         var2.append("(");

         for(int var3 = 0; var3 < var1.length; ++var3) {
            if (var3 > 0) {
               var2.append(",");
            }

            var2.append(var0[var1[var3] - 1]);
         }

         var2.append(")");
         return var2.toString();
      }
   }

   public static String intArrayToString(int[] var0) {
      StringBuffer var1 = new StringBuffer();
      var1.append("(");

      for(int var2 = 0; var2 < var0.length; ++var2) {
         if (var2 > 0) {
            var1.append(",");
         }

         var1.append(var0[var2]);
      }

      var1.append(")");
      return var1.toString();
   }

   public static boolean inAscendingOrder(int[] var0) {
      if (var0 != null) {
         int var1 = -1;

         for(int var2 = 0; var2 < var0.length; ++var2) {
            if (var1 > var0[var2]) {
               return false;
            }

            var1 = var0[var2];
         }
      }

      return true;
   }

   public static FormatableBitSet shift(FormatableBitSet var0, int var1) {
      FormatableBitSet var2 = null;
      if (var0 != null) {
         int var3 = var0.size();
         var2 = new FormatableBitSet(var3);

         for(int var4 = var1; var4 < var3; ++var4) {
            if (var0.get(var4)) {
               var2.set(var4 - var1);
            }
         }
      }

      return var2;
   }
}
