package org.apache.hive.common.util;

public class HashCodeUtil {
   public static int calculateIntHashCode(int key) {
      key = ~key + (key << 15);
      key ^= key >>> 12;
      key += key << 2;
      key ^= key >>> 4;
      key *= 2057;
      key ^= key >>> 16;
      return key;
   }

   public static int calculateLongHashCode(long key) {
      key = ~key + (key << 21);
      key ^= key >>> 24;
      key = key + (key << 3) + (key << 8);
      key ^= key >>> 14;
      key = key + (key << 2) + (key << 4);
      key ^= key >>> 28;
      key += key << 31;
      return (int)key;
   }

   public static void calculateLongArrayHashCodes(long[] longs, int[] hashCodes, int count) {
      for(int v = 0; v < count; ++v) {
         long key = longs[v];
         key = ~key + (key << 21);
         key ^= key >>> 24;
         key = key + (key << 3) + (key << 8);
         key ^= key >>> 14;
         key = key + (key << 2) + (key << 4);
         key ^= key >>> 28;
         key += key << 31;
         hashCodes[v] = (int)key;
      }

   }

   public static int calculateBytesHashCode(byte[] keyBytes, int keyStart, int keyLength) {
      return murmurHash(keyBytes, keyStart, keyLength);
   }

   public static void calculateBytesArrayHashCodes(byte[][] bytesArrays, int[] starts, int[] lengths, int[] valueSelected, int[] hashCodes, int count) {
      for(int i = 0; i < count; ++i) {
         int batchIndex = valueSelected[i];
         hashCodes[i] = murmurHash(bytesArrays[batchIndex], starts[batchIndex], lengths[batchIndex]);
      }

   }

   public static int murmurHash(byte[] data, int offset, int length) {
      int m = 1540483477;
      int r = 24;
      int h = length;
      int len_4 = length >> 2;

      for(int i = 0; i < len_4; ++i) {
         int i_4 = offset + (i << 2);
         int k = data[i_4 + 3];
         k <<= 8;
         k |= data[i_4 + 2] & 255;
         k <<= 8;
         k |= data[i_4 + 1] & 255;
         k <<= 8;
         k |= data[i_4 + 0] & 255;
         k *= m;
         k ^= k >>> r;
         k *= m;
         h *= m;
         h ^= k;
      }

      int len_m = len_4 << 2;
      int left = length - len_m;
      if (left != 0) {
         length += offset;
         if (left >= 3) {
            h ^= data[length - 3] << 16;
         }

         if (left >= 2) {
            h ^= data[length - 2] << 8;
         }

         if (left >= 1) {
            h ^= data[length - 1];
         }

         h *= m;
      }

      h ^= h >>> 13;
      h *= m;
      h ^= h >>> 15;
      return h;
   }
}
