package org.apache.spark.unsafe.bitset;

import org.apache.spark.unsafe.Platform;

public final class BitSetMethods {
   private static final long WORD_SIZE = 8L;

   private BitSetMethods() {
   }

   public static void set(Object baseObject, long baseOffset, int index) {
      assert index >= 0 : "index (" + index + ") should >= 0";

      long mask = 1L << (index & 63);
      long wordOffset = baseOffset + (long)(index >> 6) * 8L;
      long word = Platform.getLong(baseObject, wordOffset);
      Platform.putLong(baseObject, wordOffset, word | mask);
   }

   public static void unset(Object baseObject, long baseOffset, int index) {
      assert index >= 0 : "index (" + index + ") should >= 0";

      long mask = 1L << (index & 63);
      long wordOffset = baseOffset + (long)(index >> 6) * 8L;
      long word = Platform.getLong(baseObject, wordOffset);
      Platform.putLong(baseObject, wordOffset, word & ~mask);
   }

   public static boolean isSet(Object baseObject, long baseOffset, int index) {
      assert index >= 0 : "index (" + index + ") should >= 0";

      long mask = 1L << (index & 63);
      long wordOffset = baseOffset + (long)(index >> 6) * 8L;
      long word = Platform.getLong(baseObject, wordOffset);
      return (word & mask) != 0L;
   }

   public static boolean anySet(Object baseObject, long baseOffset, long bitSetWidthInWords) {
      long addr = baseOffset;

      for(int i = 0; (long)i < bitSetWidthInWords; addr += 8L) {
         if (Platform.getLong(baseObject, addr) != 0L) {
            return true;
         }

         ++i;
      }

      return false;
   }

   public static int nextSetBit(Object baseObject, long baseOffset, int fromIndex, int bitsetSizeInWords) {
      int wi = fromIndex >> 6;
      if (wi >= bitsetSizeInWords) {
         return -1;
      } else {
         int subIndex = fromIndex & 63;
         long word = Platform.getLong(baseObject, baseOffset + (long)wi * 8L) >> subIndex;
         if (word != 0L) {
            return (wi << 6) + subIndex + Long.numberOfTrailingZeros(word);
         } else {
            ++wi;

            while(wi < bitsetSizeInWords) {
               word = Platform.getLong(baseObject, baseOffset + (long)wi * 8L);
               if (word != 0L) {
                  return (wi << 6) + Long.numberOfTrailingZeros(word);
               }

               ++wi;
            }

            return -1;
         }
      }
   }
}
