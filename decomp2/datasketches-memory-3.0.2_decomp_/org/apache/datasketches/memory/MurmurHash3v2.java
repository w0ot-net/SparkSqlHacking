package org.apache.datasketches.memory;

import java.nio.charset.StandardCharsets;
import org.apache.datasketches.memory.internal.UnsafeUtil;

public final class MurmurHash3v2 {
   private static final long C1 = -8663945395140668459L;
   private static final long C2 = 5545529020109919103L;

   public static long[] hash(long[] in, long seed) {
      if (in != null && in.length != 0) {
         return hash(Memory.wrap(in), 0L, (long)(in.length << 3), seed, new long[2]);
      } else {
         throw new IllegalArgumentException("Input in is empty or null.");
      }
   }

   public static long[] hash(int[] in, long seed) {
      if (in != null && in.length != 0) {
         return hash(Memory.wrap(in), 0L, (long)(in.length << 2), seed, new long[2]);
      } else {
         throw new IllegalArgumentException("Input in is empty or null.");
      }
   }

   public static long[] hash(char[] in, long seed) {
      if (in != null && in.length != 0) {
         return hash(Memory.wrap(in), 0L, (long)(in.length << 1), seed, new long[2]);
      } else {
         throw new IllegalArgumentException("Input in is empty or null.");
      }
   }

   public static long[] hash(byte[] in, long seed) {
      if (in != null && in.length != 0) {
         return hash(Memory.wrap(in), 0L, (long)in.length, seed, new long[2]);
      } else {
         throw new IllegalArgumentException("Input in is empty or null.");
      }
   }

   public static long[] hash(long in, long seed, long[] hashOut) {
      long h1 = seed ^ mixK1(in);
      return finalMix128(h1, seed, 8L, hashOut);
   }

   public static long[] hash(double in, long seed, long[] hashOut) {
      double d = in == (double)0.0F ? (double)0.0F : in;
      long k1 = Double.doubleToLongBits(d);
      long h1 = seed ^ mixK1(k1);
      return finalMix128(h1, seed, 8L, hashOut);
   }

   public static long[] hash(String in, long seed, long[] hashOut) {
      if (in != null && in.length() != 0) {
         byte[] byteArr = in.getBytes(StandardCharsets.UTF_8);
         return hash(Memory.wrap(byteArr), 0L, (long)byteArr.length, seed, hashOut);
      } else {
         throw new IllegalArgumentException("Input in is empty or null.");
      }
   }

   public static long[] hash(Memory mem, long offsetBytes, long lengthBytes, long seed, long[] hashOut) {
      if (mem != null && mem.getCapacity() != 0L) {
         Object uObj = ((WritableMemory)mem).getArray();
         if (uObj == null) {
            throw new IllegalArgumentException("The backing resource of input mem is not on-heap.");
         } else {
            long cumOff = mem.getCumulativeOffset() + offsetBytes;
            long h1 = seed;
            long h2 = seed;

            long rem;
            for(rem = lengthBytes; rem >= 16L; h2 = h2 * 5L + 944331445L) {
               long k1 = UnsafeUtil.unsafe.getLong(uObj, cumOff);
               long k2 = UnsafeUtil.unsafe.getLong(uObj, cumOff + 8L);
               cumOff += 16L;
               rem -= 16L;
               long var21 = h1 ^ mixK1(k1);
               long var22 = Long.rotateLeft(var21, 27);
               long var23 = var22 + h2;
               h1 = var23 * 5L + 1390208809L;
               h2 ^= mixK2(k2);
               h2 = Long.rotateLeft(h2, 31);
               h2 += h1;
            }

            if (rem > 0L) {
               long k1 = 0L;
               long k2 = 0L;
               switch ((int)rem) {
                  case 1:
                     k1 ^= (long)UnsafeUtil.unsafe.getByte(uObj, cumOff) & 255L;
                     break;
                  case 3:
                     k1 ^= ((long)UnsafeUtil.unsafe.getByte(uObj, cumOff + 2L) & 255L) << 16;
                  case 2:
                     k1 ^= (long)UnsafeUtil.unsafe.getShort(uObj, cumOff) & 65535L;
                     break;
                  case 5:
                     k1 ^= ((long)UnsafeUtil.unsafe.getByte(uObj, cumOff + 4L) & 255L) << 32;
                  case 4:
                     k1 ^= (long)UnsafeUtil.unsafe.getInt(uObj, cumOff) & 4294967295L;
                     break;
                  case 7:
                     k1 ^= ((long)UnsafeUtil.unsafe.getByte(uObj, cumOff + 6L) & 255L) << 48;
                  case 6:
                     k1 ^= ((long)UnsafeUtil.unsafe.getShort(uObj, cumOff + 4L) & 65535L) << 32;
                     k1 ^= (long)UnsafeUtil.unsafe.getInt(uObj, cumOff) & 4294967295L;
                     break;
                  case 9:
                     k2 ^= (long)UnsafeUtil.unsafe.getByte(uObj, cumOff + 8L) & 255L;
                  case 8:
                     k1 = UnsafeUtil.unsafe.getLong(uObj, cumOff);
                     break;
                  case 11:
                     k2 ^= ((long)UnsafeUtil.unsafe.getByte(uObj, cumOff + 10L) & 255L) << 16;
                  case 10:
                     k2 ^= (long)UnsafeUtil.unsafe.getShort(uObj, cumOff + 8L) & 65535L;
                     k1 = UnsafeUtil.unsafe.getLong(uObj, cumOff);
                     break;
                  case 13:
                     k2 ^= ((long)UnsafeUtil.unsafe.getByte(uObj, cumOff + 12L) & 255L) << 32;
                  case 12:
                     k2 ^= (long)UnsafeUtil.unsafe.getInt(uObj, cumOff + 8L) & 4294967295L;
                     k1 = UnsafeUtil.unsafe.getLong(uObj, cumOff);
                     break;
                  case 15:
                     k2 ^= ((long)UnsafeUtil.unsafe.getByte(uObj, cumOff + 14L) & 255L) << 48;
                  case 14:
                     k2 ^= ((long)UnsafeUtil.unsafe.getShort(uObj, cumOff + 12L) & 65535L) << 32;
                     k2 ^= (long)UnsafeUtil.unsafe.getInt(uObj, cumOff + 8L) & 4294967295L;
                     k1 = UnsafeUtil.unsafe.getLong(uObj, cumOff);
               }

               h1 ^= mixK1(k1);
               h2 ^= mixK2(k2);
            }

            return finalMix128(h1, h2, lengthBytes, hashOut);
         }
      } else {
         throw new IllegalArgumentException("Input mem is empty or null.");
      }
   }

   private static long mixK1(long k1) {
      k1 *= -8663945395140668459L;
      k1 = Long.rotateLeft(k1, 31);
      k1 *= 5545529020109919103L;
      return k1;
   }

   private static long mixK2(long k2) {
      k2 *= 5545529020109919103L;
      k2 = Long.rotateLeft(k2, 33);
      k2 *= -8663945395140668459L;
      return k2;
   }

   private static long finalMix64(long h) {
      h ^= h >>> 33;
      h *= -49064778989728563L;
      h ^= h >>> 33;
      h *= -4265267296055464877L;
      h ^= h >>> 33;
      return h;
   }

   private static long[] finalMix128(long h1, long h2, long lengthBytes, long[] hashOut) {
      h1 ^= lengthBytes;
      h2 ^= lengthBytes;
      h1 += h2;
      h2 += h1;
      h1 = finalMix64(h1);
      h2 = finalMix64(h2);
      h1 += h2;
      h2 += h1;
      hashOut[0] = h1;
      hashOut[1] = h2;
      return hashOut;
   }
}
