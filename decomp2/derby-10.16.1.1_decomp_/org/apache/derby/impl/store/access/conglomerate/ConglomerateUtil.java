package org.apache.derby.impl.store.access.conglomerate;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Properties;
import org.apache.derby.iapi.services.io.CompressedNumber;
import org.apache.derby.iapi.services.io.FormatIdUtil;
import org.apache.derby.iapi.store.raw.Page;
import org.apache.derby.iapi.types.DataValueDescriptor;

public final class ConglomerateUtil {
   public static Properties createRawStorePropertySet(Properties var0) {
      var0 = createUserRawStorePropertySet(var0);
      var0.put("derby.storage.reusableRecordId", "");
      return var0;
   }

   public static Properties createUserRawStorePropertySet(Properties var0) {
      if (var0 == null) {
         var0 = new Properties();
      }

      var0.put("derby.storage.pageSize", "");
      var0.put("derby.storage.minimumRecordSize", "");
      var0.put("derby.storage.pageReservedSpace", "");
      var0.put("derby.storage.initialPages", "");
      return var0;
   }

   public static int[] createFormatIds(DataValueDescriptor[] var0) {
      int[] var1 = new int[var0.length];

      for(int var2 = 0; var2 < var0.length; ++var2) {
         var1[var2] = var0[var2].getTypeFormatId();
      }

      return var1;
   }

   public static int[] readFormatIdArray(int var0, ObjectInput var1) throws IOException {
      int[] var2 = new int[var0];

      for(int var3 = 0; var3 < var0; ++var3) {
         var2[var3] = FormatIdUtil.readFormatIdInteger((DataInput)var1);
      }

      return var2;
   }

   public static void writeFormatIdArray(int[] var0, ObjectOutput var1) throws IOException {
      for(int var2 = 0; var2 < var0.length; ++var2) {
         FormatIdUtil.writeFormatIdInteger(var1, var0[var2]);
      }

   }

   public static int[] createCollationIds(int var0, int[] var1) {
      int[] var2 = new int[var0];
      if (var1 != null) {
         System.arraycopy(var1, 0, var2, 0, var1.length);
      } else {
         for(int var3 = 0; var3 < var2.length; ++var3) {
            var2[var3] = 0;
         }
      }

      return var2;
   }

   public static void writeCollationIdArray(int[] var0, ObjectOutput var1) throws IOException {
      int var2 = 0;

      for(int var3 = 0; var3 < var0.length; ++var3) {
         if (var0[var3] != 0) {
            ++var2;
         }
      }

      CompressedNumber.writeInt((DataOutput)var1, var2);

      for(int var4 = 0; var4 < var0.length; ++var4) {
         if (var0[var4] != 0) {
            CompressedNumber.writeInt((DataOutput)var1, var4);
            CompressedNumber.writeInt((DataOutput)var1, var0[var4]);
         }
      }

   }

   public static boolean readCollationIdArray(int[] var0, ObjectInput var1) throws IOException {
      int var2 = CompressedNumber.readInt((DataInput)var1);

      for(int var3 = 0; var3 < var2; ++var3) {
         int var4 = CompressedNumber.readInt((DataInput)var1);
         var0[var4] = CompressedNumber.readInt((DataInput)var1);
      }

      return var2 > 0;
   }

   public static String debugPage(Page var0, int var1, boolean var2, DataValueDescriptor[] var3) {
      return null;
   }
}
