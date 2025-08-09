package org.apache.datasketches.hash;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Objects;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.common.Util;
import org.apache.datasketches.memory.Memory;

public final class MurmurHash3 implements Serializable {
   private static final long serialVersionUID = 0L;

   private MurmurHash3() {
   }

   public static long[] hash(long key, long seed) {
      HashState hashState = new HashState(seed, seed);
      return hashState.finalMix128(key, 0L, 8L);
   }

   public static long[] hash(long[] key, long seed) {
      return hash((long[])key, 0, key.length, seed);
   }

   public static long[] hash(long[] key, int offsetLongs, int lengthLongs, long seed) {
      Objects.requireNonNull(key);
      int arrLen = key.length;
      checkPositive((long)arrLen);
      Util.checkBounds((long)offsetLongs, (long)lengthLongs, (long)arrLen);
      HashState hashState = new HashState(seed, seed);
      int nblocks = lengthLongs >>> 1;

      for(int i = 0; i < nblocks; ++i) {
         long k1 = key[offsetLongs + (i << 1)];
         long k2 = key[offsetLongs + (i << 1) + 1];
         hashState.blockMix128(k1, k2);
      }

      int tail = nblocks << 1;
      int rem = lengthLongs - tail;
      long k1 = rem == 0 ? 0L : key[offsetLongs + tail];
      return hashState.finalMix128(k1, 0L, (long)(lengthLongs << 3));
   }

   public static long[] hash(int[] key, long seed) {
      return hash((int[])key, 0, key.length, seed);
   }

   public static long[] hash(int[] key, int offsetInts, int lengthInts, long seed) {
      Objects.requireNonNull(key);
      int arrLen = key.length;
      checkPositive((long)arrLen);
      Util.checkBounds((long)offsetInts, (long)lengthInts, (long)arrLen);
      HashState hashState = new HashState(seed, seed);
      int nblocks = lengthInts >>> 2;

      for(int i = 0; i < nblocks; ++i) {
         long k1 = getLong((int[])key, offsetInts + (i << 2), 2);
         long k2 = getLong((int[])key, offsetInts + (i << 2) + 2, 2);
         hashState.blockMix128(k1, k2);
      }

      int tail = nblocks << 2;
      int rem = lengthInts - tail;
      long k1;
      long k2;
      if (rem > 2) {
         k1 = getLong((int[])key, offsetInts + tail, 2);
         k2 = getLong(key, offsetInts + tail + 2, rem - 2);
      } else {
         k1 = rem == 0 ? 0L : getLong(key, offsetInts + tail, rem);
         k2 = 0L;
      }

      return hashState.finalMix128(k1, k2, (long)(lengthInts << 2));
   }

   public static long[] hash(char[] key, long seed) {
      return hash((char[])key, 0, key.length, seed);
   }

   public static long[] hash(char[] key, int offsetChars, int lengthChars, long seed) {
      Objects.requireNonNull(key);
      int arrLen = key.length;
      checkPositive((long)arrLen);
      Util.checkBounds((long)offsetChars, (long)lengthChars, (long)arrLen);
      HashState hashState = new HashState(seed, seed);
      int nblocks = lengthChars >>> 3;

      for(int i = 0; i < nblocks; ++i) {
         long k1 = getLong((char[])key, offsetChars + (i << 3), 4);
         long k2 = getLong((char[])key, offsetChars + (i << 3) + 4, 4);
         hashState.blockMix128(k1, k2);
      }

      int tail = nblocks << 3;
      int rem = lengthChars - tail;
      long k1;
      long k2;
      if (rem > 4) {
         k1 = getLong((char[])key, offsetChars + tail, 4);
         k2 = getLong(key, offsetChars + tail + 4, rem - 4);
      } else {
         k1 = rem == 0 ? 0L : getLong(key, offsetChars + tail, rem);
         k2 = 0L;
      }

      return hashState.finalMix128(k1, k2, (long)(lengthChars << 1));
   }

   public static long[] hash(byte[] key, long seed) {
      return hash((byte[])key, 0, key.length, seed);
   }

   public static long[] hash(byte[] key, int offsetBytes, int lengthBytes, long seed) {
      Objects.requireNonNull(key);
      int arrLen = key.length;
      checkPositive((long)arrLen);
      Util.checkBounds((long)offsetBytes, (long)lengthBytes, (long)arrLen);
      HashState hashState = new HashState(seed, seed);
      int nblocks = lengthBytes >>> 4;

      for(int i = 0; i < nblocks; ++i) {
         long k1 = getLong((byte[])key, offsetBytes + (i << 4), 8);
         long k2 = getLong((byte[])key, offsetBytes + (i << 4) + 8, 8);
         hashState.blockMix128(k1, k2);
      }

      int tail = nblocks << 4;
      int rem = lengthBytes - tail;
      long k1;
      long k2;
      if (rem > 8) {
         k1 = getLong((byte[])key, offsetBytes + tail, 8);
         k2 = getLong(key, offsetBytes + tail + 8, rem - 8);
      } else {
         k1 = rem == 0 ? 0L : getLong(key, offsetBytes + tail, rem);
         k2 = 0L;
      }

      return hashState.finalMix128(k1, k2, (long)lengthBytes);
   }

   public static long[] hash(ByteBuffer buf, long seed) {
      Objects.requireNonNull(buf);
      int pos = buf.position();
      int rem = buf.remaining();
      checkPositive((long)rem);
      Memory mem = Memory.wrap(buf, ByteOrder.LITTLE_ENDIAN).region((long)pos, (long)rem);
      return hash(mem, seed);
   }

   public static long[] hash(Memory mem, long seed) {
      Objects.requireNonNull(mem);
      long lengthBytes = mem.getCapacity();
      checkPositive(lengthBytes);
      Memory memLE = mem.getTypeByteOrder() == ByteOrder.LITTLE_ENDIAN ? mem : mem.region(0L, lengthBytes, ByteOrder.LITTLE_ENDIAN);
      HashState hashState = new HashState(seed, seed);
      long nblocks = lengthBytes >>> 4;

      for(long i = 0L; i < nblocks; ++i) {
         long k1 = memLE.getLong(i << 4);
         long k2 = memLE.getLong((i << 4) + 8L);
         hashState.blockMix128(k1, k2);
      }

      long tail = nblocks << 4;
      int rem = (int)(lengthBytes - tail);
      long k1;
      long k2;
      if (rem > 8) {
         k1 = memLE.getLong(tail);
         k2 = getLong(memLE, tail + 8L, rem - 8);
      } else {
         k1 = rem == 0 ? 0L : getLong(memLE, tail, rem);
         k2 = 0L;
      }

      return hashState.finalMix128(k1, k2, lengthBytes);
   }

   private static long getLong(int[] intArr, int index, int rem) {
      long out = 0L;

      int v;
      for(int i = rem; i-- > 0; out ^= ((long)v & 4294967295L) << i * 32) {
         v = intArr[index + i];
      }

      return out;
   }

   private static long getLong(char[] charArr, int index, int rem) {
      long out = 0L;

      char c;
      for(int i = rem; i-- > 0; out ^= ((long)c & 65535L) << i * 16) {
         c = charArr[index + i];
      }

      return out;
   }

   private static long getLong(byte[] bArr, int index, int rem) {
      long out = 0L;

      byte b;
      for(int i = rem; i-- > 0; out ^= ((long)b & 255L) << i * 8) {
         b = bArr[index + i];
      }

      return out;
   }

   private static long getLong(Memory mem, long offsetBytes, int rem) {
      long out = 0L;
      if (rem == 8) {
         return mem.getLong(offsetBytes);
      } else {
         byte b;
         for(int i = rem; i-- > 0; out ^= ((long)b & 255L) << (i << 3)) {
            b = mem.getByte(offsetBytes + (long)i);
         }

         return out;
      }
   }

   private static void checkPositive(long size) {
      if (size <= 0L) {
         throw new SketchesArgumentException("Array size must not be negative or zero: " + size);
      }
   }

   private static final class HashState {
      private static final long C1 = -8663945395140668459L;
      private static final long C2 = 5545529020109919103L;
      private long h1;
      private long h2;

      HashState(long h1, long h2) {
         this.h1 = h1;
         this.h2 = h2;
      }

      void blockMix128(long k1, long k2) {
         this.h1 ^= mixK1(k1);
         this.h1 = Long.rotateLeft(this.h1, 27);
         this.h1 += this.h2;
         this.h1 = this.h1 * 5L + 1390208809L;
         this.h2 ^= mixK2(k2);
         this.h2 = Long.rotateLeft(this.h2, 31);
         this.h2 += this.h1;
         this.h2 = this.h2 * 5L + 944331445L;
      }

      long[] finalMix128(long k1, long k2, long inputLengthBytes) {
         this.h1 ^= mixK1(k1);
         this.h2 ^= mixK2(k2);
         this.h1 ^= inputLengthBytes;
         this.h2 ^= inputLengthBytes;
         this.h1 += this.h2;
         this.h2 += this.h1;
         this.h1 = finalMix64(this.h1);
         this.h2 = finalMix64(this.h2);
         this.h1 += this.h2;
         this.h2 += this.h1;
         return new long[]{this.h1, this.h2};
      }

      private static long finalMix64(long h) {
         h ^= h >>> 33;
         h *= -49064778989728563L;
         h ^= h >>> 33;
         h *= -4265267296055464877L;
         h ^= h >>> 33;
         return h;
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
   }
}
