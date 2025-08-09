package shaded.parquet.net.openhft.hashing;

import java.nio.ByteOrder;

class XxHash {
   private static final long P1 = -7046029288634856825L;
   private static final long P2 = -4417276706812531889L;
   private static final long P3 = 1609587929392839161L;
   private static final long P4 = -8796714831421723037L;
   private static final long P5 = 2870177450012600261L;

   static long xxHash64(long seed, Object input, Access access, long off, long length) {
      long remaining = length;
      long hash;
      if (length >= 32L) {
         long v1 = seed + -7046029288634856825L + -4417276706812531889L;
         long v2 = seed + -4417276706812531889L;
         long v3 = seed;
         long v4 = seed - -7046029288634856825L;

         do {
            v1 += access.i64(input, off) * -4417276706812531889L;
            v1 = Long.rotateLeft(v1, 31);
            v1 *= -7046029288634856825L;
            v2 += access.i64(input, off + 8L) * -4417276706812531889L;
            v2 = Long.rotateLeft(v2, 31);
            v2 *= -7046029288634856825L;
            v3 += access.i64(input, off + 16L) * -4417276706812531889L;
            v3 = Long.rotateLeft(v3, 31);
            v3 *= -7046029288634856825L;
            v4 += access.i64(input, off + 24L) * -4417276706812531889L;
            v4 = Long.rotateLeft(v4, 31);
            v4 *= -7046029288634856825L;
            off += 32L;
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

      for(hash += length; remaining >= 8L; remaining -= 8L) {
         long k1 = access.i64(input, off);
         k1 *= -4417276706812531889L;
         k1 = Long.rotateLeft(k1, 31);
         k1 *= -7046029288634856825L;
         hash ^= k1;
         hash = Long.rotateLeft(hash, 27) * -7046029288634856825L + -8796714831421723037L;
         off += 8L;
      }

      if (remaining >= 4L) {
         hash ^= access.u32(input, off) * -7046029288634856825L;
         hash = Long.rotateLeft(hash, 23) * -4417276706812531889L + 1609587929392839161L;
         off += 4L;
         remaining -= 4L;
      }

      while(remaining != 0L) {
         hash ^= (long)access.u8(input, off) * 2870177450012600261L;
         hash = Long.rotateLeft(hash, 11) * -7046029288634856825L;
         --remaining;
         ++off;
      }

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

   static LongHashFunction asLongHashFunctionWithoutSeed() {
      return XxHash.AsLongHashFunction.SEEDLESS_INSTANCE;
   }

   static LongHashFunction asLongHashFunctionWithSeed(long seed) {
      return new AsLongHashFunctionSeeded(seed);
   }

   private static class AsLongHashFunction extends LongHashFunction {
      private static final long serialVersionUID = 0L;
      static final AsLongHashFunction SEEDLESS_INSTANCE = new AsLongHashFunction();
      private static final long VOID_HASH = XxHash.finalize(2870177450012600261L);

      private AsLongHashFunction() {
      }

      private Object readResolve() {
         return SEEDLESS_INSTANCE;
      }

      public long seed() {
         return 0L;
      }

      public long hashLong(long input) {
         input = Primitives.nativeToLittleEndian(input);
         long hash = this.seed() + 2870177450012600261L + 8L;
         input *= -4417276706812531889L;
         input = Long.rotateLeft(input, 31);
         input *= -7046029288634856825L;
         hash ^= input;
         hash = Long.rotateLeft(hash, 27) * -7046029288634856825L + -8796714831421723037L;
         return XxHash.finalize(hash);
      }

      public long hashInt(int input) {
         input = Primitives.nativeToLittleEndian(input);
         long hash = this.seed() + 2870177450012600261L + 4L;
         hash ^= Primitives.unsignedInt(input) * -7046029288634856825L;
         hash = Long.rotateLeft(hash, 23) * -4417276706812531889L + 1609587929392839161L;
         return XxHash.finalize(hash);
      }

      public long hashShort(short input) {
         input = Primitives.nativeToLittleEndian(input);
         long hash = this.seed() + 2870177450012600261L + 2L;
         hash ^= (long)Primitives.unsignedByte(input) * 2870177450012600261L;
         hash = Long.rotateLeft(hash, 11) * -7046029288634856825L;
         hash ^= (long)Primitives.unsignedByte(input >> 8) * 2870177450012600261L;
         hash = Long.rotateLeft(hash, 11) * -7046029288634856825L;
         return XxHash.finalize(hash);
      }

      public long hashChar(char input) {
         return this.hashShort((short)input);
      }

      public long hashByte(byte input) {
         long hash = this.seed() + 2870177450012600261L + 1L;
         hash ^= (long)Primitives.unsignedByte(input) * 2870177450012600261L;
         hash = Long.rotateLeft(hash, 11) * -7046029288634856825L;
         return XxHash.finalize(hash);
      }

      public long hashVoid() {
         return VOID_HASH;
      }

      public long hash(Object input, Access access, long off, long len) {
         long seed = this.seed();
         return XxHash.xxHash64(seed, input, access.byteOrder(input, ByteOrder.LITTLE_ENDIAN), off, len);
      }
   }

   private static class AsLongHashFunctionSeeded extends AsLongHashFunction {
      private static final long serialVersionUID = 0L;
      private final long seed;
      private final transient long voidHash;

      private AsLongHashFunctionSeeded(long seed) {
         this.seed = seed;
         this.voidHash = XxHash.finalize(seed + 2870177450012600261L);
      }

      public long seed() {
         return this.seed;
      }

      public long hashVoid() {
         return this.voidHash;
      }
   }
}
