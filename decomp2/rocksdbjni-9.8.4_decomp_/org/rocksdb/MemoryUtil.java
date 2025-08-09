package org.rocksdb;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

public class MemoryUtil {
   public static Map getApproximateMemoryUsageByType(List var0, Set var1) {
      int var2 = var0 == null ? 0 : var0.size();
      int var3 = var1 == null ? 0 : var1.size();
      long[] var4 = new long[var2];
      long[] var5 = new long[var3];
      if (var2 > 0) {
         for(ListIterator var6 = var0.listIterator(); var6.hasNext(); var4[var6.nextIndex()] = ((RocksDB)var6.next()).nativeHandle_) {
         }
      }

      if (var3 > 0) {
         int var10 = 0;

         for(Cache var8 : var1) {
            var5[var10] = var8.nativeHandle_;
            ++var10;
         }
      }

      Map var11 = getApproximateMemoryUsageByType(var4, var5);
      HashMap var12 = new HashMap();

      for(Map.Entry var9 : var11.entrySet()) {
         var12.put(MemoryUsageType.getMemoryUsageType((Byte)var9.getKey()), var9.getValue());
      }

      return var12;
   }

   private static native Map getApproximateMemoryUsageByType(long[] var0, long[] var1);
}
