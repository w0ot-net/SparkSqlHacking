package com.clearspring.analytics.hash;

public class MurmurHash {
   public static int hash(Object o) {
      if (o == null) {
         return 0;
      } else if (o instanceof Long) {
         return hashLong((Long)o);
      } else if (o instanceof Integer) {
         return hashLong((long)(Integer)o);
      } else if (o instanceof Double) {
         return hashLong(Double.doubleToRawLongBits((Double)o));
      } else if (o instanceof Float) {
         return hashLong((long)Float.floatToRawIntBits((Float)o));
      } else if (o instanceof String) {
         return hash(((String)o).getBytes());
      } else {
         return o instanceof byte[] ? hash((byte[])o) : hash((Object)o.toString());
      }
   }

   public static int hash(byte[] data) {
      return hash(data, data.length, -1);
   }

   public static int hash(byte[] data, int seed) {
      return hash(data, data.length, seed);
   }

   public static int hash(byte[] data, int length, int seed) {
      int m = 1540483477;
      int r = 24;
      int h = seed ^ length;
      int len_4 = length >> 2;

      for(int i = 0; i < len_4; ++i) {
         int i_4 = i << 2;
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

   public static int hashLong(long data) {
      int m = 1540483477;
      int r = 24;
      int h = 0;
      int k = (int)data * m;
      k ^= k >>> r;
      h ^= k * m;
      k = (int)(data >> 32) * m;
      k ^= k >>> r;
      h *= m;
      h ^= k * m;
      h ^= h >>> 13;
      h *= m;
      h ^= h >>> 15;
      return h;
   }

   public static long hash64(Object o) {
      if (o == null) {
         return 0L;
      } else if (o instanceof String) {
         byte[] bytes = ((String)o).getBytes();
         return hash64(bytes, bytes.length);
      } else if (o instanceof byte[]) {
         byte[] bytes = (byte[])o;
         return hash64(bytes, bytes.length);
      } else {
         return hash64(o.toString());
      }
   }

   public static long hash64(byte[] data, int length) {
      return hash64(data, length, -512093083);
   }

   public static long hash64(byte[] data, int length, int seed) {
      long m = -4132994306676758123L;
      int r = 47;
      long h = (long)seed & 4294967295L ^ (long)length * -4132994306676758123L;
      int length8 = length / 8;

      for(int i = 0; i < length8; ++i) {
         int i8 = i * 8;
         long k = ((long)data[i8 + 0] & 255L) + (((long)data[i8 + 1] & 255L) << 8) + (((long)data[i8 + 2] & 255L) << 16) + (((long)data[i8 + 3] & 255L) << 24) + (((long)data[i8 + 4] & 255L) << 32) + (((long)data[i8 + 5] & 255L) << 40) + (((long)data[i8 + 6] & 255L) << 48) + (((long)data[i8 + 7] & 255L) << 56);
         k *= -4132994306676758123L;
         k ^= k >>> 47;
         k *= -4132994306676758123L;
         h ^= k;
         h *= -4132994306676758123L;
      }

      switch (length % 8) {
         case 7:
            h ^= (long)(data[(length & -8) + 6] & 255) << 48;
         case 6:
            h ^= (long)(data[(length & -8) + 5] & 255) << 40;
         case 5:
            h ^= (long)(data[(length & -8) + 4] & 255) << 32;
         case 4:
            h ^= (long)(data[(length & -8) + 3] & 255) << 24;
         case 3:
            h ^= (long)(data[(length & -8) + 2] & 255) << 16;
         case 2:
            h ^= (long)(data[(length & -8) + 1] & 255) << 8;
         case 1:
            h ^= (long)(data[length & -8] & 255);
            h *= -4132994306676758123L;
         default:
            h ^= h >>> 47;
            h *= -4132994306676758123L;
            h ^= h >>> 47;
            return h;
      }
   }
}
