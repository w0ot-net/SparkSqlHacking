package org.apache.derby.impl.store.access.conglomerate;

import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.store.raw.Transaction;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.DataValueFactory;
import org.apache.derby.iapi.types.SQLLongint;
import org.apache.derby.shared.common.error.StandardException;

public final class TemplateRow {
   private TemplateRow() {
   }

   private static DataValueDescriptor[] allocate_objects(Transaction var0, int var1, FormatableBitSet var2, int[] var3, int[] var4) throws StandardException {
      DataValueDescriptor[] var5 = new DataValueDescriptor[var1];
      int var6 = var2 == null ? var3.length : var2.size();
      DataValueFactory var7 = var0.getDataValueFactory();

      for(int var8 = 0; var8 < var6; ++var8) {
         if (var2 == null || var2.get(var8)) {
            var5[var8] = var7.getNull(var3[var8], var4[var8]);
         }
      }

      return var5;
   }

   public static DataValueDescriptor[] newU8Row(int var0) {
      DataValueDescriptor[] var1 = new DataValueDescriptor[var0];

      for(int var2 = 0; var2 < var1.length; ++var2) {
         var1[var2] = new SQLLongint(Long.MIN_VALUE);
      }

      return var1;
   }

   public static DataValueDescriptor[] newRow(DataValueDescriptor[] var0) throws StandardException {
      DataValueDescriptor[] var1 = new DataValueDescriptor[var0.length];

      for(int var2 = var0.length; var2-- > 0; var1[var2] = var0[var2].getNewNull()) {
      }

      return var1;
   }

   public static DataValueDescriptor[] newRow(Transaction var0, FormatableBitSet var1, int[] var2, int[] var3) throws StandardException {
      return allocate_objects(var0, var2.length, var1, var2, var3);
   }

   public static DataValueDescriptor[] newBranchRow(Transaction var0, int[] var1, int[] var2, DataValueDescriptor var3) throws StandardException {
      DataValueDescriptor[] var4 = allocate_objects(var0, var1.length + 1, (FormatableBitSet)null, var1, var2);
      var4[var1.length] = var3;
      return var4;
   }

   public static boolean checkColumnTypes(DataValueFactory var0, int[] var1, int[] var2, DataValueDescriptor[] var3) throws StandardException {
      boolean var4 = true;
      int var5 = var3.length;
      if (var1.length != var3.length) {
         var4 = false;
      }

      return var4;
   }
}
