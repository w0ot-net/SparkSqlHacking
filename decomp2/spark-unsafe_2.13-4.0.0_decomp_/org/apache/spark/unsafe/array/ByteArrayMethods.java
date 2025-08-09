package org.apache.spark.unsafe.array;

import org.apache.spark.unsafe.Platform;

public class ByteArrayMethods {
   public static final int MAX_ROUNDED_ARRAY_LENGTH = 2147483632;
   private static final boolean unaligned = Platform.unaligned();

   private ByteArrayMethods() {
   }

   public static long nextPowerOf2(long num) {
      long highBit = Long.highestOneBit(num);
      return highBit == num ? num : highBit << 1;
   }

   public static int roundNumberOfBytesToNearestWord(int numBytes) {
      return (int)roundNumberOfBytesToNearestWord((long)numBytes);
   }

   public static long roundNumberOfBytesToNearestWord(long numBytes) {
      long remainder = numBytes & 7L;
      return numBytes + (8L - remainder & 7L);
   }

   public static boolean arrayEquals(Object leftBase, long leftOffset, Object rightBase, long rightOffset, long length) {
      int i = 0;
      if (!unaligned && leftOffset % 8L == rightOffset % 8L) {
         while((leftOffset + (long)i) % 8L != 0L && (long)i < length) {
            if (Platform.getByte(leftBase, leftOffset + (long)i) != Platform.getByte(rightBase, rightOffset + (long)i)) {
               return false;
            }

            ++i;
         }
      }

      if (unaligned || (leftOffset + (long)i) % 8L == 0L && (rightOffset + (long)i) % 8L == 0L) {
         while((long)i <= length - 8L) {
            if (Platform.getLong(leftBase, leftOffset + (long)i) != Platform.getLong(rightBase, rightOffset + (long)i)) {
               return false;
            }

            i += 8;
         }
      }

      while((long)i < length) {
         if (Platform.getByte(leftBase, leftOffset + (long)i) != Platform.getByte(rightBase, rightOffset + (long)i)) {
            return false;
         }

         ++i;
      }

      return true;
   }

   public static boolean contains(byte[] arr, byte[] sub) {
      if (sub.length == 0) {
         return true;
      } else {
         byte first = sub[0];

         for(int i = 0; i <= arr.length - sub.length; ++i) {
            if (arr[i] == first && matchAt(arr, sub, i)) {
               return true;
            }
         }

         return false;
      }
   }

   public static boolean startsWith(byte[] array, byte[] target) {
      return target.length > array.length ? false : arrayEquals(array, (long)Platform.BYTE_ARRAY_OFFSET, target, (long)Platform.BYTE_ARRAY_OFFSET, (long)target.length);
   }

   public static boolean endsWith(byte[] array, byte[] target) {
      return target.length > array.length ? false : arrayEquals(array, (long)(Platform.BYTE_ARRAY_OFFSET + array.length - target.length), target, (long)Platform.BYTE_ARRAY_OFFSET, (long)target.length);
   }

   public static boolean matchAt(byte[] arr, byte[] sub, int pos) {
      return sub.length + pos <= arr.length && pos >= 0 ? arrayEquals(arr, (long)(Platform.BYTE_ARRAY_OFFSET + pos), sub, (long)Platform.BYTE_ARRAY_OFFSET, (long)sub.length) : false;
   }
}
