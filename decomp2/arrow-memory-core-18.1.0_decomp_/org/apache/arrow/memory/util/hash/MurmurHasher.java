package org.apache.arrow.memory.util.hash;

import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.memory.util.MemoryUtil;
import org.checkerframework.checker.nullness.qual.Nullable;

public class MurmurHasher implements ArrowBufHasher {
   private final int seed;

   public MurmurHasher() {
      this(0);
   }

   public MurmurHasher(int seed) {
      this.seed = seed;
   }

   public int hashCode(long address, long length) {
      return hashCode(address, length, this.seed);
   }

   public int hashCode(ArrowBuf buf, long offset, long length) {
      buf.checkBytes(offset, offset + length);
      return this.hashCode(buf.memoryAddress() + offset, length);
   }

   public static int hashCode(ArrowBuf buf, long offset, long length, int seed) {
      buf.checkBytes(offset, offset + length);
      return hashCode(buf.memoryAddress() + offset, length, seed);
   }

   public static int hashCode(long address, long length, int seed) {
      int index = 0;

      int hash;
      for(hash = seed; (long)(index + 4) <= length; index += 4) {
         int intValue = MemoryUtil.getInt(address + (long)index);
         hash = combineHashCode(hash, intValue);
      }

      if ((long)index < length) {
         int intValue = 0;

         for(long i = length - 1L; i >= (long)index; --i) {
            intValue <<= 8;
            intValue |= MemoryUtil.getByte(address + i) & 255;
            ++index;
         }

         hash = combineHashCode(hash, intValue);
      }

      return finalizeHashCode(hash, length);
   }

   public static int combineHashCode(int currentHashCode, int intValue) {
      int c1 = -862048943;
      int c2 = 461845907;
      int r1 = 15;
      int r2 = 13;
      int m = 5;
      int n = -430675100;
      int k = intValue * c1;
      k = rotateLeft(k, r1);
      k *= c2;
      int hash = currentHashCode ^ k;
      hash = rotateLeft(hash, r2);
      hash = hash * m + n;
      return hash;
   }

   public static int finalizeHashCode(int hashCode, long length) {
      hashCode ^= (int)length;
      hashCode ^= hashCode >>> 16;
      hashCode *= -2048144789;
      hashCode ^= hashCode >>> 13;
      hashCode *= -1028477387;
      hashCode ^= hashCode >>> 16;
      return hashCode;
   }

   private static int rotateLeft(int value, int count) {
      return value << count | value >>> 32 - count;
   }

   public boolean equals(@Nullable Object o) {
      if (this == o) {
         return true;
      } else if (!(o instanceof MurmurHasher)) {
         return false;
      } else {
         MurmurHasher that = (MurmurHasher)o;
         return this.seed == that.seed;
      }
   }

   public int hashCode() {
      return this.seed;
   }
}
