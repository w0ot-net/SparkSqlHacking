package org.apache.derby.iapi.types;

import java.sql.Timestamp;

public abstract class DataTypeUtilities {
   public static Timestamp clone(Timestamp var0) {
      return var0 == null ? null : (Timestamp)var0.clone();
   }

   public static int getPrecision(DataTypeDescriptor var0) {
      int var1 = var0.getTypeId().getJDBCTypeId();
      switch (var1) {
         case -4:
         case -3:
         case -2:
         case -1:
         case 1:
         case 12:
         case 2004:
         case 2005:
         case 2009:
            return var0.getMaximumWidth();
         case 5:
            return 5;
         case 16:
            return 1;
         default:
            return var0.getPrecision();
      }
   }

   public static int getDigitPrecision(DataTypeDescriptor var0) {
      int var1 = var0.getTypeId().getJDBCTypeId();
      switch (var1) {
         case 6:
         case 8:
            return 15;
         case 7:
            return 7;
         default:
            return getPrecision(var0);
      }
   }

   public static boolean isCaseSensitive(DataTypeDescriptor var0) {
      int var1 = var0.getTypeId().getJDBCTypeId();
      return var1 == 1 || var1 == 12 || var1 == 2005 || var1 == -1 || var1 == 2009;
   }

   public static int isNullable(DataTypeDescriptor var0) {
      return var0.isNullable() ? 1 : 0;
   }

   public static boolean isSigned(DataTypeDescriptor var0) {
      int var1 = var0.getTypeId().getJDBCTypeId();
      return var1 == 4 || var1 == 6 || var1 == 3 || var1 == 5 || var1 == -5 || var1 == -6 || var1 == 2 || var1 == 7 || var1 == 8;
   }

   public static int getColumnDisplaySize(DataTypeDescriptor var0) {
      int var1 = var0.getTypeId().getJDBCTypeId();
      int var2 = var0.getMaximumWidth();
      return getColumnDisplaySize(var1, var2);
   }

   public static int getColumnDisplaySize(int var0, int var1) {
      int var2;
      switch (var0) {
         case -7:
         case 16:
            var2 = 5;
            break;
         case -6:
            var2 = 15;
            break;
         case -5:
            var2 = 20;
            break;
         case -4:
         case -3:
         case -2:
         case 2004:
            var2 = 2 * var1;
            if (var2 < 0) {
               var2 = Integer.MAX_VALUE;
            }
            break;
         case 4:
            var2 = 11;
            break;
         case 5:
            var2 = 6;
            break;
         case 6:
         case 7:
            var2 = 15;
            break;
         case 8:
            var2 = 24;
            break;
         case 91:
            var2 = 10;
            break;
         case 92:
            var2 = 8;
            break;
         case 93:
            var2 = 29;
            break;
         default:
            var2 = var1 > 0 ? var1 : 15;
      }

      return var2;
   }

   public static int computeMaxWidth(int var0, int var1) {
      return var1 == 0 ? var0 + 1 : (var1 == var0 ? var0 + 3 : var0 + 2);
   }
}
