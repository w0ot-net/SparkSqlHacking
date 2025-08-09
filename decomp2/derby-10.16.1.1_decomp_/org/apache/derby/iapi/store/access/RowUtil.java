package org.apache.derby.iapi.store.access;

import java.util.Hashtable;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.store.raw.FetchDescriptor;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.DataValueFactory;
import org.apache.derby.shared.common.error.StandardException;

public class RowUtil {
   public static final DataValueDescriptor[] EMPTY_ROW = new DataValueDescriptor[0];
   public static final FormatableBitSet EMPTY_ROW_BITSET = new FormatableBitSet(0);
   public static final FetchDescriptor EMPTY_ROW_FETCH_DESCRIPTOR = new FetchDescriptor(0);
   private static final FetchDescriptor[] ROWUTIL_FETCH_DESCRIPTOR_CONSTANTS;

   private RowUtil() {
   }

   public static DataValueDescriptor getColumn(DataValueDescriptor[] var0, FormatableBitSet var1, int var2) {
      if (var1 == null) {
         return var2 < var0.length ? var0[var2] : null;
      } else if (var1.getLength() > var2 && var1.isSet(var2)) {
         return var2 < var0.length ? var0[var2] : null;
      } else {
         return null;
      }
   }

   public static Object getColumn(Object[] var0, FormatableBitSet var1, int var2) {
      if (var1 == null) {
         return var2 < var0.length ? var0[var2] : null;
      } else if (var1.getLength() > var2 && var1.isSet(var2)) {
         return var2 < var0.length ? var0[var2] : null;
      } else {
         return null;
      }
   }

   public static FormatableBitSet getQualifierBitSet(Qualifier[][] var0) {
      FormatableBitSet var1 = new FormatableBitSet();
      if (var0 != null) {
         for(int var2 = 0; var2 < var0.length; ++var2) {
            for(int var3 = 0; var3 < var0[var2].length; ++var3) {
               int var4 = var0[var2][var3].getColumnId();
               var1.grow(var4 + 1);
               var1.set(var4);
            }
         }
      }

      return var1;
   }

   public static int getNumberOfColumns(int var0, FormatableBitSet var1) {
      int var2 = var1.getLength();
      if (var0 > 0 && var0 < var2) {
         var2 = var0;
      }

      int var3 = 0;

      for(int var4 = 0; var4 < var2; ++var4) {
         if (var1.isSet(var4)) {
            ++var3;
         }
      }

      return var3;
   }

   public static boolean isRowEmpty(DataValueDescriptor[] var0) {
      if (var0 == null) {
         return true;
      } else {
         return var0.length == 0;
      }
   }

   public static int columnOutOfRange(DataValueDescriptor[] var0, FormatableBitSet var1, int var2) {
      if (var1 == null) {
         return var0.length > var2 ? var2 : -1;
      } else {
         int var3 = var1.getLength();

         for(int var4 = var2; var4 < var3; ++var4) {
            if (var1.isSet(var4)) {
               return var4;
            }
         }

         return -1;
      }
   }

   public static int nextColumn(Object[] var0, FormatableBitSet var1, int var2) {
      if (var1 != null) {
         for(int var3 = var1.getLength(); var2 < var3; ++var2) {
            if (var1.isSet(var2)) {
               return var2;
            }
         }

         return -1;
      } else if (var0 == null) {
         return -1;
      } else {
         return var2 < var0.length ? var2 : -1;
      }
   }

   public static final FetchDescriptor getFetchDescriptorConstant(int var0) {
      return var0 < ROWUTIL_FETCH_DESCRIPTOR_CONSTANTS.length ? ROWUTIL_FETCH_DESCRIPTOR_CONSTANTS[var0] : new FetchDescriptor(var0, var0);
   }

   public static DataValueDescriptor[] newTemplate(DataValueFactory var0, FormatableBitSet var1, int[] var2, int[] var3) throws StandardException {
      int var4 = var2.length;
      DataValueDescriptor[] var5 = new DataValueDescriptor[var4];
      int var6 = var1 == null ? 0 : var1.getLength();

      for(int var7 = 0; var7 < var4; ++var7) {
         if (var1 == null || var6 > var7 && var1.isSet(var7)) {
            var5[var7] = var0.getNull(var2[var7], var3[var7]);
         }
      }

      return var5;
   }

   public static DataValueDescriptor[] newRowFromTemplate(DataValueDescriptor[] var0) throws StandardException {
      DataValueDescriptor[] var1 = new DataValueDescriptor[var0.length];
      int var2 = var0.length;

      while(var2-- > 0) {
         if (var0[var2] != null) {
            var1[var2] = var0[var2].getNewNull();
         }
      }

      return var1;
   }

   public static String toString(Object[] var0) {
      return null;
   }

   public static String toString(Hashtable var0) {
      return null;
   }

   public static final boolean qualifyRow(DataValueDescriptor[] var0, Qualifier[][] var1) throws StandardException {
      boolean var2 = true;

      for(int var3 = 0; var3 < var1[0].length; ++var3) {
         var2 = false;
         Qualifier var4 = var1[0][var3];
         DataValueDescriptor var5 = var0[var4.getColumnId()];
         var2 = var5.compare(var4.getOperator(), var4.getOrderable(), var4.getOrderedNulls(), var4.getUnknownRV());
         if (var4.negateCompareResult()) {
            var2 = !var2;
         }

         if (!var2) {
            return false;
         }
      }

      for(int var9 = 1; var9 < var1.length; ++var9) {
         var2 = false;

         for(int var10 = 0; var10 < var1[var9].length; ++var10) {
            Qualifier var11 = var1[var9][var10];
            int var6 = var11.getColumnId();
            DataValueDescriptor var7 = var0[var11.getColumnId()];
            var2 = var7.compare(var11.getOperator(), var11.getOrderable(), var11.getOrderedNulls(), var11.getUnknownRV());
            if (var11.negateCompareResult()) {
               var2 = !var2;
            }

            if (var2) {
               break;
            }
         }

         if (!var2) {
            break;
         }
      }

      return var2;
   }

   static {
      ROWUTIL_FETCH_DESCRIPTOR_CONSTANTS = new FetchDescriptor[]{EMPTY_ROW_FETCH_DESCRIPTOR, new FetchDescriptor(1, 1), new FetchDescriptor(2, 2), new FetchDescriptor(3, 3), new FetchDescriptor(4, 4), new FetchDescriptor(5, 5), new FetchDescriptor(6, 6), new FetchDescriptor(7, 7)};
   }
}
