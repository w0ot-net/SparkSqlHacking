package shaded.parquet.net.openhft.hashing;

import java.nio.ByteOrder;

class MetroHash {
   private static final long k0 = 3603962101L;
   private static final long k1 = 2729050939L;
   private static final long k2 = 1654206401L;
   private static final long k3 = 817650473L;

   static long metroHash64(long seed, Object input, Access access, long off, long length) {
      long remaining = length;
      long h = (seed + 1654206401L) * 3603962101L;
      if (length >= 32L) {
         long v0 = h;
         long v1 = h;
         long v2 = h;
         long v3 = h;

         do {
            long v0 = v0 + access.i64(input, off) * 3603962101L;
            v0 = Long.rotateRight(v0, 29) + v2;
            long v1 = v1 + access.i64(input, off + 8L) * 2729050939L;
            v1 = Long.rotateRight(v1, 29) + v3;
            v2 += access.i64(input, off + 16L) * 1654206401L;
            v2 = Long.rotateRight(v2, 29) + v0;
            v3 += access.i64(input, off + 24L) * 817650473L;
            v3 = Long.rotateRight(v3, 29) + v1;
            off += 32L;
            remaining -= 32L;
         } while(remaining >= 32L);

         v2 ^= Long.rotateRight((v0 + v3) * 3603962101L + v1, 37) * 2729050939L;
         v3 ^= Long.rotateRight((v1 + v2) * 2729050939L + v0, 37) * 3603962101L;
         v0 ^= Long.rotateRight((v0 + v2) * 3603962101L + v3, 37) * 2729050939L;
         v1 ^= Long.rotateRight((v1 + v3) * 2729050939L + v2, 37) * 3603962101L;
         h += v0 ^ v1;
      }

      if (remaining >= 16L) {
         long v0 = h + access.i64(input, off) * 1654206401L;
         v0 = Long.rotateRight(v0, 29) * 817650473L;
         long v1 = h + access.i64(input, off + 8L) * 1654206401L;
         v1 = Long.rotateRight(v1, 29) * 817650473L;
         v0 ^= Long.rotateRight(v0 * 3603962101L, 21) + v1;
         v1 ^= Long.rotateRight(v1 * 817650473L, 21) + v0;
         h += v1;
         off += 16L;
         remaining -= 16L;
      }

      if (remaining >= 8L) {
         h += access.i64(input, off) * 817650473L;
         h ^= Long.rotateRight(h, 55) * 2729050939L;
         off += 8L;
         remaining -= 8L;
      }

      if (remaining >= 4L) {
         h += access.u32(input, off) * 817650473L;
         h ^= Long.rotateRight(h, 26) * 2729050939L;
         off += 4L;
         remaining -= 4L;
      }

      if (remaining >= 2L) {
         h += (long)access.u16(input, off) * 817650473L;
         h ^= Long.rotateRight(h, 48) * 2729050939L;
         off += 2L;
         remaining -= 2L;
      }

      if (remaining >= 1L) {
         h += (long)access.u8(input, off) * 817650473L;
         h ^= Long.rotateRight(h, 37) * 2729050939L;
      }

      return finalize(h);
   }

   private static long finalize(long h) {
      h ^= Long.rotateRight(h, 28);
      h *= 3603962101L;
      h ^= Long.rotateRight(h, 29);
      return h;
   }

   static LongHashFunction asLongHashFunctionWithoutSeed() {
      return MetroHash.AsLongHashFunction.SEEDLESS_INSTANCE;
   }

   static LongHashFunction asLongHashFunctionWithSeed(long seed) {
      return new AsLongHashFunctionSeeded(seed);
   }

   private static class AsLongHashFunction extends LongHashFunction {
      private static final long serialVersionUID = 0L;
      private static final AsLongHashFunction SEEDLESS_INSTANCE = new AsLongHashFunction();
      private static final long VOID_HASH = MetroHash.finalize(5961697176435608501L);

      private AsLongHashFunction() {
      }

      private Object readResolve() {
         return SEEDLESS_INSTANCE;
      }

      protected long seed() {
         return 0L;
      }

      public long hashLong(long input) {
         input = Primitives.nativeToLittleEndian(input);
         long h = (this.seed() + 1654206401L) * 3603962101L;
         h += input * 817650473L;
         h ^= Long.rotateRight(h, 55) * 2729050939L;
         return MetroHash.finalize(h);
      }

      public long hashInt(int input) {
         input = Primitives.nativeToLittleEndian(input);
         long h = (this.seed() + 1654206401L) * 3603962101L;
         h += Primitives.unsignedInt(input) * 817650473L;
         h ^= Long.rotateRight(h, 26) * 2729050939L;
         return MetroHash.finalize(h);
      }

      public long hashShort(short input) {
         input = Primitives.nativeToLittleEndian(input);
         long h = (this.seed() + 1654206401L) * 3603962101L;
         h += (long)Primitives.unsignedShort(input) * 817650473L;
         h ^= Long.rotateRight(h, 48) * 2729050939L;
         return MetroHash.finalize(h);
      }

      public long hashChar(char input) {
         return this.hashShort((short)input);
      }

      public long hashByte(byte input) {
         long h = (this.seed() + 1654206401L) * 3603962101L;
         h += (long)Primitives.unsignedByte(input) * 817650473L;
         h ^= Long.rotateRight(h, 37) * 2729050939L;
         return MetroHash.finalize(h);
      }

      public long hashVoid() {
         return VOID_HASH;
      }

      public long hash(Object input, Access access, long off, long len) {
         long seed = this.seed();
         return MetroHash.metroHash64(seed, input, access.byteOrder(input, ByteOrder.LITTLE_ENDIAN), off, len);
      }
   }

   private static class AsLongHashFunctionSeeded extends AsLongHashFunction {
      private static final long serialVersionUID = 0L;
      private final long seed;
      private final transient long voidHash;

      AsLongHashFunctionSeeded(long seed) {
         this.seed = seed;
         this.voidHash = MetroHash.finalize((seed + 1654206401L) * 3603962101L);
      }

      public long hashVoid() {
         return this.voidHash;
      }

      protected long seed() {
         return this.seed;
      }
   }
}
