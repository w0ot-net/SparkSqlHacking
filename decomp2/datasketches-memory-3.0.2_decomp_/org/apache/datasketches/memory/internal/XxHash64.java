package org.apache.datasketches.memory.internal;

public class XxHash64 {
   private static final long P1 = -7046029288634856825L;
   private static final long P2 = -4417276706812531889L;
   private static final long P3 = 1609587929392839161L;
   private static final long P4 = -8796714831421723037L;
   private static final long P5 = 2870177450012600261L;

   static long hash(Object unsafeObj, long cumOffsetBytes, long lengthBytes, long seed) {
      long remaining = lengthBytes;
      long hash;
      if (lengthBytes >= 32L) {
         long v1 = seed + -7046029288634856825L + -4417276706812531889L;
         long v2 = seed + -4417276706812531889L;
         long v3 = seed;
         long v4 = seed - -7046029288634856825L;

         do {
            v1 += UnsafeUtil.unsafe.getLong(unsafeObj, cumOffsetBytes) * -4417276706812531889L;
            v1 = Long.rotateLeft(v1, 31);
            v1 *= -7046029288634856825L;
            v2 += UnsafeUtil.unsafe.getLong(unsafeObj, cumOffsetBytes + 8L) * -4417276706812531889L;
            v2 = Long.rotateLeft(v2, 31);
            v2 *= -7046029288634856825L;
            v3 += UnsafeUtil.unsafe.getLong(unsafeObj, cumOffsetBytes + 16L) * -4417276706812531889L;
            v3 = Long.rotateLeft(v3, 31);
            v3 *= -7046029288634856825L;
            v4 += UnsafeUtil.unsafe.getLong(unsafeObj, cumOffsetBytes + 24L) * -4417276706812531889L;
            v4 = Long.rotateLeft(v4, 31);
            v4 *= -7046029288634856825L;
            cumOffsetBytes += 32L;
            remaining -= 32L;
         } while(remaining >= 32L);

         hash = Long.rotateLeft(v1, 1) + Long.rotateLeft(v2, 7) + Long.rotateLeft(v3, 12) + Long.rotateLeft(v4, 18);
         v1 *= -4417276706812531889L;
         v1 = Long.rotateLeft(v1, 31);
         v1 *= -7046029288634856825L;
         hash ^= v1;
         hash = hash * -7046029288634856825L + -8796714831421723037L;
         v2 *= -4417276706812531889L;
         v2 = Long.rotateLeft(v2, 31);
         v2 *= -7046029288634856825L;
         hash ^= v2;
         hash = hash * -7046029288634856825L + -8796714831421723037L;
         v3 *= -4417276706812531889L;
         v3 = Long.rotateLeft(v3, 31);
         v3 *= -7046029288634856825L;
         hash ^= v3;
         hash = hash * -7046029288634856825L + -8796714831421723037L;
         v4 *= -4417276706812531889L;
         v4 = Long.rotateLeft(v4, 31);
         v4 *= -7046029288634856825L;
         hash ^= v4;
         hash = hash * -7046029288634856825L + -8796714831421723037L;
      } else {
         hash = seed + 2870177450012600261L;
      }

      for(hash += lengthBytes; remaining >= 8L; remaining -= 8L) {
         long k1 = UnsafeUtil.unsafe.getLong(unsafeObj, cumOffsetBytes);
         k1 *= -4417276706812531889L;
         k1 = Long.rotateLeft(k1, 31);
         k1 *= -7046029288634856825L;
         hash ^= k1;
         hash = Long.rotateLeft(hash, 27) * -7046029288634856825L + -8796714831421723037L;
         cumOffsetBytes += 8L;
      }

      if (remaining >= 4L) {
         hash ^= ((long)UnsafeUtil.unsafe.getInt(unsafeObj, cumOffsetBytes) & 4294967295L) * -7046029288634856825L;
         hash = Long.rotateLeft(hash, 23) * -4417276706812531889L + 1609587929392839161L;
         cumOffsetBytes += 4L;
         remaining -= 4L;
      }

      while(remaining != 0L) {
         hash ^= ((long)UnsafeUtil.unsafe.getByte(unsafeObj, cumOffsetBytes) & 255L) * 2870177450012600261L;
         hash = Long.rotateLeft(hash, 11) * -7046029288634856825L;
         --remaining;
         ++cumOffsetBytes;
      }

      return finalize(hash);
   }

   public static long hash(long in, long seed) {
      long hash = seed + 2870177450012600261L;
      hash += 8L;
      long k1 = in * -4417276706812531889L;
      k1 = Long.rotateLeft(k1, 31);
      k1 *= -7046029288634856825L;
      hash ^= k1;
      hash = Long.rotateLeft(hash, 27) * -7046029288634856825L + -8796714831421723037L;
      return finalize(hash);
   }

   private static long finalize(long hash) {
      hash ^= hash >>> 33;
      hash *= -4417276706812531889L;
      hash ^= hash >>> 29;
      hash *= 1609587929392839161L;
      hash ^= hash >>> 32;
      return hash;
   }

   public static long hashBytes(byte[] arr, long offsetBytes, long lengthBytes, long seed) {
      return hash(arr, UnsafeUtil.ARRAY_BYTE_BASE_OFFSET + offsetBytes, lengthBytes, seed);
   }

   public static long hashShorts(short[] arr, long offsetShorts, long lengthShorts, long seed) {
      return hash(arr, UnsafeUtil.ARRAY_SHORT_BASE_OFFSET + (offsetShorts << 1), lengthShorts << 1, seed);
   }

   public static long hashChars(char[] arr, long offsetChars, long lengthChars, long seed) {
      return hash(arr, UnsafeUtil.ARRAY_CHAR_BASE_OFFSET + (offsetChars << 1), lengthChars << 1, seed);
   }

   public static long hashInts(int[] arr, long offsetInts, long lengthInts, long seed) {
      return hash(arr, UnsafeUtil.ARRAY_INT_BASE_OFFSET + (offsetInts << 2), lengthInts << 2, seed);
   }

   public static long hashLongs(long[] arr, long offsetLongs, long lengthLongs, long seed) {
      return hash(arr, UnsafeUtil.ARRAY_LONG_BASE_OFFSET + (offsetLongs << 3), lengthLongs << 3, seed);
   }

   public static long hashFloats(float[] arr, long offsetFloats, long lengthFloats, long seed) {
      return hash(arr, UnsafeUtil.ARRAY_FLOAT_BASE_OFFSET + (offsetFloats << 2), lengthFloats << 2, seed);
   }

   public static long hashDoubles(double[] arr, long offsetDoubles, long lengthDoubles, long seed) {
      return hash(arr, UnsafeUtil.ARRAY_DOUBLE_BASE_OFFSET + (offsetDoubles << 3), lengthDoubles << 3, seed);
   }

   public static long hashString(String str, long offsetChars, long lengthChars, long seed) {
      return hashChars(str.toCharArray(), offsetChars, lengthChars, seed);
   }
}
