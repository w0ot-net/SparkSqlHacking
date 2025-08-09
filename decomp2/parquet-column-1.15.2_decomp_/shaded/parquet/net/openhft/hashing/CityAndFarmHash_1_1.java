package shaded.parquet.net.openhft.hashing;

import java.nio.ByteOrder;

class CityAndFarmHash_1_1 {
   static final long K0 = -4348849565147123417L;
   private static final long K1 = -5435081209227447693L;
   private static final long K2 = -7286425919675154353L;
   private static final long K_MUL = -7070675565921424023L;

   private static long shiftMix(long val) {
      return val ^ val >>> 47;
   }

   private static long hashLen16(long u, long v) {
      return hashLen16(u, v, -7070675565921424023L);
   }

   private static long hashLen16(long u, long v, long mul) {
      long a = shiftMix((u ^ v) * mul);
      return shiftMix((v ^ a) * mul) * mul;
   }

   private static long mul(long len) {
      return -7286425919675154353L + (len << 1);
   }

   private static long hash1To3Bytes(int len, int firstByte, int midOrLastByte, int lastByte) {
      int y = firstByte + (midOrLastByte << 8);
      int z = len + (lastByte << 2);
      return shiftMix((long)y * -7286425919675154353L ^ (long)z * -4348849565147123417L) * -7286425919675154353L;
   }

   private static long hash4To7Bytes(long len, long first4Bytes, long last4Bytes) {
      long mul = mul(len);
      return hashLen16(len + (first4Bytes << 3), last4Bytes, mul);
   }

   private static long hash8To16Bytes(long len, long first8Bytes, long last8Bytes) {
      long mul = mul(len);
      long a = first8Bytes + -7286425919675154353L;
      long c = Long.rotateRight(last8Bytes, 37) * mul + a;
      long d = (Long.rotateRight(a, 25) + last8Bytes) * mul;
      return hashLen16(c, d, mul);
   }

   private static long hashLen0To16(Access access, Object in, long off, long len) {
      if (len >= 8L) {
         long a = access.i64(in, off);
         long b = access.i64(in, off + len - 8L);
         return hash8To16Bytes(len, a, b);
      } else if (len >= 4L) {
         long a = access.u32(in, off);
         long b = access.u32(in, off + len - 4L);
         return hash4To7Bytes(len, a, b);
      } else if (len > 0L) {
         int a = access.u8(in, off);
         int b = access.u8(in, off + (len >> 1));
         int c = access.u8(in, off + len - 1L);
         return hash1To3Bytes((int)len, a, b, c);
      } else {
         return -7286425919675154353L;
      }
   }

   private static long hashLen17To32(Access access, Object in, long off, long len) {
      long mul = mul(len);
      long a = access.i64(in, off) * -5435081209227447693L;
      long b = access.i64(in, off + 8L);
      long c = access.i64(in, off + len - 8L) * mul;
      long d = access.i64(in, off + len - 16L) * -7286425919675154353L;
      return hashLen16(Long.rotateRight(a + b, 43) + Long.rotateRight(c, 30) + d, a + Long.rotateRight(b + -7286425919675154353L, 18) + c, mul);
   }

   private static long cityHashLen33To64(Access access, Object in, long off, long len) {
      long mul = mul(len);
      long a = access.i64(in, off) * -7286425919675154353L;
      long b = access.i64(in, off + 8L);
      long c = access.i64(in, off + len - 24L);
      long d = access.i64(in, off + len - 32L);
      long e = access.i64(in, off + 16L) * -7286425919675154353L;
      long f = access.i64(in, off + 24L) * 9L;
      long g = access.i64(in, off + len - 8L);
      long h = access.i64(in, off + len - 16L) * mul;
      long u = Long.rotateRight(a + g, 43) + (Long.rotateRight(b, 30) + c) * 9L;
      long v = (a + g ^ d) + f + 1L;
      long w = Long.reverseBytes((u + v) * mul) + h;
      long x = Long.rotateRight(e + f, 42) + c;
      long y = (Long.reverseBytes((v + w) * mul) + g) * mul;
      long z = e + f + c;
      a = Long.reverseBytes((x + z) * mul + y) + b;
      b = shiftMix((z + a) * mul + d + h) * mul;
      return b + x;
   }

   static long cityHash64(Access access, Object in, long off, long len) {
      if (len <= 32L) {
         return len <= 16L ? hashLen0To16(access, in, off, len) : hashLen17To32(access, in, off, len);
      } else if (len <= 64L) {
         return cityHashLen33To64(access, in, off, len);
      } else {
         long x = access.i64(in, off + len - 40L);
         long y = access.i64(in, off + len - 16L) + access.i64(in, off + len - 56L);
         long z = hashLen16(access.i64(in, off + len - 48L) + len, access.i64(in, off + len - 24L));
         long w4 = access.i64(in, off + len - 64L);
         long x4 = access.i64(in, off + len - 64L + 8L);
         long y4 = access.i64(in, off + len - 64L + 16L);
         long z4 = access.i64(in, off + len - 64L + 24L);
         long a3 = len + w4;
         long b3 = Long.rotateRight(z + a3 + z4, 21);
         long c3 = a3;
         a3 += x4 + y4;
         b3 += Long.rotateRight(a3, 44);
         long vFirst = a3 + z4;
         long vSecond = b3 + c3;
         long a2 = y + -5435081209227447693L;
         long w3 = access.i64(in, off + len - 32L);
         long x3 = access.i64(in, off + len - 32L + 8L);
         long y3 = access.i64(in, off + len - 32L + 16L);
         long z3 = access.i64(in, off + len - 32L + 24L);
         a2 += w3;
         long b2 = Long.rotateRight(x + a2 + z3, 21);
         long c2 = a2;
         a2 += x3 + y3;
         b2 += Long.rotateRight(a2, 44);
         long wFirst = a2 + z3;
         long wSecond = b2 + c2;
         x = x * -5435081209227447693L + access.i64(in, off);
         len = len - 1L & -64L;

         long tmp;
         do {
            x = Long.rotateRight(x + y + vFirst + access.i64(in, off + 8L), 37) * -5435081209227447693L;
            long var82 = Long.rotateRight(y + vSecond + access.i64(in, off + 48L), 42) * -5435081209227447693L;
            x ^= wSecond;
            y = var82 + vFirst + access.i64(in, off + 40L);
            z = Long.rotateRight(z + wFirst, 33) * -5435081209227447693L;
            long a1 = vSecond * -5435081209227447693L;
            long b1 = x + wFirst;
            long w2 = access.i64(in, off);
            long x2 = access.i64(in, off + 8L);
            long y2 = access.i64(in, off + 16L);
            long z2 = access.i64(in, off + 24L);
            a1 += w2;
            b1 = Long.rotateRight(b1 + a1 + z2, 21);
            long c1 = a1;
            a1 += x2 + y2;
            b1 += Long.rotateRight(a1, 44);
            vFirst = a1 + z2;
            vSecond = b1 + c1;
            long a = z + wSecond;
            long b = y + access.i64(in, off + 16L);
            long w1 = access.i64(in, off + 32L);
            long x1 = access.i64(in, off + 32L + 8L);
            long y1 = access.i64(in, off + 32L + 16L);
            long z1 = access.i64(in, off + 32L + 24L);
            a += w1;
            b = Long.rotateRight(b + a + z1, 21);
            long c = a;
            a += x1 + y1;
            b += Long.rotateRight(a, 44);
            wFirst = a + z1;
            wSecond = b + c;
            tmp = x;
            x = z;
            z = tmp;
            len -= 64L;
            off += 64L;
         } while(len != 0L);

         return hashLen16(hashLen16(vFirst, wFirst) + shiftMix(y) * -5435081209227447693L + tmp, hashLen16(vSecond, wSecond) + x);
      }
   }

   static LongHashFunction asLongHashFunctionWithoutSeed() {
      return CityAndFarmHash_1_1.AsLongHashFunction.SEEDLESS_INSTANCE;
   }

   static LongHashFunction asLongHashFunctionWithSeed(long seed) {
      return new AsLongHashFunctionSeeded(-7286425919675154353L, seed);
   }

   static LongHashFunction asLongHashFunctionWithTwoSeeds(long seed0, long seed1) {
      return new AsLongHashFunctionSeeded(seed0, seed1);
   }

   private static long naHashLen33To64(Access access, Object in, long off, long len) {
      long mul = mul(len);
      long a = access.i64(in, off) * -7286425919675154353L;
      long b = access.i64(in, off + 8L);
      long c = access.i64(in, off + len - 8L) * mul;
      long d = access.i64(in, off + len - 16L) * -7286425919675154353L;
      long y = Long.rotateRight(a + b, 43) + Long.rotateRight(c, 30) + d;
      long z = hashLen16(y, a + Long.rotateRight(b + -7286425919675154353L, 18) + c, mul);
      long e = access.i64(in, off + 16L) * mul;
      long f = access.i64(in, off + 24L);
      long g = (y + access.i64(in, off + len - 32L)) * mul;
      long h = (z + access.i64(in, off + len - 24L)) * mul;
      return hashLen16(Long.rotateRight(e + f, 43) + Long.rotateRight(g, 30) + h, e + Long.rotateRight(f + a, 18) + g, mul);
   }

   static long naHash64(Access access, Object in, long off, long len) {
      long seed = 81L;
      if (len <= 32L) {
         return len <= 16L ? hashLen0To16(access, in, off, len) : hashLen17To32(access, in, off, len);
      } else if (len <= 64L) {
         return naHashLen33To64(access, in, off, len);
      } else {
         long x = 81L;
         long y = 2480279821605975764L;
         long z = shiftMix(y * -7286425919675154353L + 113L) * -7286425919675154353L;
         long v1 = 0L;
         long v2 = 0L;
         long w1 = 0L;
         long w2 = 0L;
         x = x * -7286425919675154353L + access.i64(in, off);
         long end = off + (len - 1L >> 6) * 64L;
         long last64 = end + (len - 1L & 63L) - 63L;

         long t;
         do {
            x = Long.rotateRight(x + y + v1 + access.i64(in, off + 8L), 37) * -5435081209227447693L;
            long var51 = Long.rotateRight(y + v2 + access.i64(in, off + 48L), 42) * -5435081209227447693L;
            x ^= w2;
            y = var51 + v1 + access.i64(in, off + 40L);
            z = Long.rotateRight(z + w1, 33) * -5435081209227447693L;
            long a = v2 * -5435081209227447693L;
            long b = x + w1;
            long z1 = access.i64(in, off + 24L);
            a += access.i64(in, off);
            b = Long.rotateRight(b + a + z1, 21);
            long c = a;
            a += access.i64(in, off + 8L);
            a += access.i64(in, off + 16L);
            b += Long.rotateRight(a, 44);
            v1 = a + z1;
            v2 = b + c;
            long a1 = z + w2;
            long b1 = y + access.i64(in, off + 16L);
            long z2 = access.i64(in, off + 32L + 24L);
            a1 += access.i64(in, off + 32L);
            b1 = Long.rotateRight(b1 + a1 + z2, 21);
            long c1 = a1;
            a1 += access.i64(in, off + 32L + 8L);
            a1 += access.i64(in, off + 32L + 16L);
            b1 += Long.rotateRight(a1, 44);
            w1 = a1 + z2;
            w2 = b1 + c1;
            t = z;
            z = x;
            x = t;
         } while((off += 64L) != end);

         long mul = -5435081209227447693L + ((z & 255L) << 1);
         w1 += len - 1L & 63L;
         v1 += w1;
         w1 += v1;
         x = Long.rotateRight(t + y + v1 + access.i64(in, last64 + 8L), 37) * mul;
         y = Long.rotateRight(y + v2 + access.i64(in, last64 + 48L), 42) * mul;
         x ^= w2 * 9L;
         y += v1 * 9L + access.i64(in, last64 + 40L);
         z = Long.rotateRight(z + w1, 33) * mul;
         long a = v2 * mul;
         long b = x + w1;
         long z1 = access.i64(in, last64 + 24L);
         a += access.i64(in, last64);
         b = Long.rotateRight(b + a + z1, 21);
         long c = a;
         a += access.i64(in, last64 + 8L);
         a += access.i64(in, last64 + 16L);
         b += Long.rotateRight(a, 44);
         v1 = a + z1;
         v2 = b + c;
         long a1 = z + w2;
         long b1 = y + access.i64(in, last64 + 16L);
         long z2 = access.i64(in, last64 + 32L + 24L);
         a1 += access.i64(in, last64 + 32L);
         b1 = Long.rotateRight(b1 + a1 + z2, 21);
         t = a1;
         a1 += access.i64(in, last64 + 32L + 8L);
         a1 += access.i64(in, last64 + 32L + 16L);
         b1 += Long.rotateRight(a1, 44);
         w1 = a1 + z2;
         w2 = b1 + t;
         return hashLen16(hashLen16(v1, w1, mul) + shiftMix(y) * -4348849565147123417L + x, hashLen16(v2, w2, mul) + z, mul);
      }
   }

   private static long naHash64WithSeeds(Access access, Object in, long off, long len, long seed0, long seed1) {
      return hashLen16(naHash64(access, in, off, len) - seed0, seed1);
   }

   private static long uoH(long x, long y, long mul, int r) {
      long a = (x ^ y) * mul;
      a = shiftMix(a);
      long b = (y ^ a) * mul;
      return Long.rotateRight(b, r) * mul;
   }

   static long uoHash64WithSeeds(Access access, Object in, long off, long len, long seed0, long seed1) {
      if (len <= 64L) {
         return naHash64WithSeeds(access, in, off, len, seed0, seed1);
      } else {
         long y = seed1 * -7286425919675154353L + 113L;
         long z = shiftMix(y * -7286425919675154353L) * -7286425919675154353L;
         long v0 = seed0;
         long v1 = seed1;
         long w0 = 0L;
         long w1 = 0L;
         long u = seed0 - z;
         long x = seed0 * -7286425919675154353L;
         long mul = -7286425919675154353L + (u & 130L);
         long end = off + (len - 1L >> 6) * 64L;
         long last64 = end + (len - 1L & 63L) - 63L;

         long var111;
         do {
            long a0 = access.i64(in, off);
            long a1 = access.i64(in, off + 8L);
            long a2 = access.i64(in, off + 16L);
            long a3 = access.i64(in, off + 24L);
            long a4 = access.i64(in, off + 32L);
            long a5 = access.i64(in, off + 40L);
            long a6 = access.i64(in, off + 48L);
            long a7 = access.i64(in, off + 56L);
            x += a0 + a1;
            long var56 = y + a2;
            z += a3;
            v0 += a4;
            long var74 = v1 + a5 + a1;
            w0 += a6;
            w1 += a7;
            x = Long.rotateRight(x, 26);
            x *= 9L;
            long var57 = Long.rotateRight(var56, 29);
            z *= mul;
            v0 = Long.rotateRight(v0, 33);
            long var75 = Long.rotateRight(var74, 30);
            w0 ^= x;
            w0 *= 9L;
            z = Long.rotateRight(z, 32);
            z += w1;
            w1 += z;
            z *= 9L;
            long t = u;
            u = var57;
            z += a0 + a6;
            v0 += a2;
            long var76 = var75 + a3;
            w0 += a4;
            w1 += a5 + a6;
            x += a1;
            long var58 = t + a7;
            y = var58 + v0;
            v0 += x - y;
            v1 = var76 + w0;
            w0 += v1;
            w1 += x - y;
            x += w1;
            w1 = Long.rotateRight(w1, 34);
            var111 = u;
            u = z;
            z = var111;
         } while((off += 64L) != end);

         u *= 9L;
         v1 = Long.rotateRight(v1, 28);
         v0 = Long.rotateRight(v0, 20);
         w0 += len - 1L & 63L;
         u += y;
         y += u;
         x = Long.rotateRight(y - x + v0 + access.i64(in, last64 + 8L), 37) * mul;
         y = Long.rotateRight(y ^ v1 ^ access.i64(in, last64 + 48L), 42) * mul;
         x ^= w1 * 9L;
         y += v0 + access.i64(in, last64 + 40L);
         z = Long.rotateRight(var111 + w0, 33) * mul;
         long a = v1 * mul;
         long b = x + w0;
         long z1 = access.i64(in, last64 + 24L);
         a += access.i64(in, last64);
         b = Long.rotateRight(b + a + z1, 21);
         long c = a;
         a += access.i64(in, last64 + 8L);
         a += access.i64(in, last64 + 16L);
         b += Long.rotateRight(a, 44);
         v0 = a + z1;
         v1 = b + c;
         long a1 = z + w1;
         long b1 = y + access.i64(in, last64 + 16L);
         long z2 = access.i64(in, last64 + 32L + 24L);
         a1 += access.i64(in, last64 + 32L);
         b1 = Long.rotateRight(b1 + a1 + z2, 21);
         long c1 = a1;
         a1 += access.i64(in, last64 + 32L + 8L);
         a1 += access.i64(in, last64 + 32L + 16L);
         b1 += Long.rotateRight(a1, 44);
         w0 = a1 + z2;
         w1 = b1 + c1;
         return uoH(hashLen16(v0 + x, w0 ^ y, mul) + z - u, uoH(v1 + y, w1 + z, -7286425919675154353L, 30) ^ x, -7286425919675154353L, 31);
      }
   }

   static LongHashFunction naWithoutSeeds() {
      return CityAndFarmHash_1_1.Na.SEEDLESS_NA;
   }

   static LongHashFunction naWithSeed(long seed) {
      return new NaSeeded(-7286425919675154353L, seed);
   }

   static LongHashFunction naWithSeeds(long seed0, long seed1) {
      return new NaSeeded(seed0, seed1);
   }

   static LongHashFunction uoWithoutSeeds() {
      return CityAndFarmHash_1_1.Uo.SEEDLESS_UO;
   }

   static LongHashFunction uoWithSeed(long seed) {
      return new UoWithOneSeed(seed);
   }

   static LongHashFunction uoWithSeeds(long seed0, long seed1) {
      return new UoSeeded(seed0, seed1);
   }

   private static class AsLongHashFunction extends LongHashFunction {
      private static final long serialVersionUID = 0L;
      private static final AsLongHashFunction SEEDLESS_INSTANCE = new AsLongHashFunction();
      private static final int FIRST_SHORT_BYTE_SHIFT;
      private static final int FIRST_SHORT_BYTE_MASK;
      private static final int SECOND_SHORT_BYTE_SHIFT;
      private static final int SECOND_SHORT_BYTE_MASK;

      private AsLongHashFunction() {
      }

      private Object readResolve() {
         return SEEDLESS_INSTANCE;
      }

      public long hashLong(long input) {
         input = Primitives.nativeToLittleEndian(input);
         long hash = CityAndFarmHash_1_1.hash8To16Bytes(8L, input, input);
         return this.finalize(hash);
      }

      public long hashInt(int input) {
         input = Primitives.nativeToLittleEndian(input);
         long unsignedInt = Primitives.unsignedInt(input);
         long hash = CityAndFarmHash_1_1.hash4To7Bytes(4L, unsignedInt, unsignedInt);
         return this.finalize(hash);
      }

      public long hashShort(short input) {
         return this.hashChar((char)input);
      }

      public long hashChar(char input) {
         int firstByte = input >> FIRST_SHORT_BYTE_SHIFT & FIRST_SHORT_BYTE_MASK;
         int secondByte = input >> SECOND_SHORT_BYTE_SHIFT & SECOND_SHORT_BYTE_MASK;
         long hash = CityAndFarmHash_1_1.hash1To3Bytes(2, firstByte, secondByte, secondByte);
         return this.finalize(hash);
      }

      public long hashByte(byte input) {
         int unsignedByte = Primitives.unsignedByte(input);
         long hash = CityAndFarmHash_1_1.hash1To3Bytes(1, unsignedByte, unsignedByte, unsignedByte);
         return this.finalize(hash);
      }

      public long hashVoid() {
         return -7286425919675154353L;
      }

      public long hash(Object input, Access access, long off, long len) {
         long hash = CityAndFarmHash_1_1.cityHash64(access.byteOrder(input, ByteOrder.LITTLE_ENDIAN), input, off, len);
         return this.finalize(hash);
      }

      long finalize(long hash) {
         return hash;
      }

      static {
         FIRST_SHORT_BYTE_SHIFT = Primitives.NATIVE_LITTLE_ENDIAN ? 0 : 8;
         FIRST_SHORT_BYTE_MASK = Primitives.NATIVE_LITTLE_ENDIAN ? 255 : -1;
         SECOND_SHORT_BYTE_SHIFT = 8 - FIRST_SHORT_BYTE_SHIFT;
         SECOND_SHORT_BYTE_MASK = Primitives.NATIVE_LITTLE_ENDIAN ? -1 : 255;
      }
   }

   private static class AsLongHashFunctionSeeded extends AsLongHashFunction {
      private static final long serialVersionUID = 0L;
      final long seed0;
      final long seed1;
      private final transient long voidHash;

      private AsLongHashFunctionSeeded(long seed0, long seed1) {
         this.seed0 = seed0;
         this.seed1 = seed1;
         this.voidHash = this.finalize(-7286425919675154353L);
      }

      public long hashVoid() {
         return this.voidHash;
      }

      protected long finalize(long hash) {
         return CityAndFarmHash_1_1.hashLen16(hash - this.seed0, this.seed1);
      }
   }

   private static class Na extends AsLongHashFunction {
      private static final long serialVersionUID = 0L;
      private static final Na SEEDLESS_NA = new Na();

      private Na() {
      }

      private Object readResolve() {
         return SEEDLESS_NA;
      }

      public long hash(Object input, Access access, long off, long len) {
         long hash = CityAndFarmHash_1_1.naHash64(access.byteOrder(input, ByteOrder.LITTLE_ENDIAN), input, off, len);
         return this.finalize(hash);
      }
   }

   private static class NaSeeded extends Na {
      private static final long serialVersionUID = 0L;
      private final long seed0;
      private final long seed1;
      private final transient long voidHash;

      private NaSeeded(long seed0, long seed1) {
         this.seed0 = seed0;
         this.seed1 = seed1;
         this.voidHash = this.finalize(-7286425919675154353L);
      }

      public long hashVoid() {
         return this.voidHash;
      }

      protected long finalize(long hash) {
         return CityAndFarmHash_1_1.hashLen16(hash - this.seed0, this.seed1);
      }
   }

   private static final class Uo extends AsLongHashFunction {
      private static final long serialVersionUID = 0L;
      private static final Uo SEEDLESS_UO = new Uo();

      private Object readResolve() {
         return SEEDLESS_UO;
      }

      public long hash(Object input, Access access, long off, long len) {
         return len <= 64L ? CityAndFarmHash_1_1.naHash64(access.byteOrder(input, ByteOrder.LITTLE_ENDIAN), input, off, len) : CityAndFarmHash_1_1.uoHash64WithSeeds(access.byteOrder(input, ByteOrder.LITTLE_ENDIAN), input, off, len, 81L, 0L);
      }
   }

   private static final class UoWithOneSeed extends AsLongHashFunctionSeeded {
      private static final long serialVersionUID = 0L;

      private UoWithOneSeed(long seed) {
         super(-7286425919675154353L, seed, null);
      }

      public long hash(Object input, Access access, long off, long len) {
         return len <= 64L ? this.finalize(CityAndFarmHash_1_1.naHash64(access.byteOrder(input, ByteOrder.LITTLE_ENDIAN), input, off, len)) : CityAndFarmHash_1_1.uoHash64WithSeeds(access.byteOrder(input, ByteOrder.LITTLE_ENDIAN), input, off, len, 0L, this.seed1);
      }
   }

   private static class UoSeeded extends AsLongHashFunctionSeeded {
      private static final long serialVersionUID = 0L;

      private UoSeeded(long seed0, long seed1) {
         super(seed0, seed1, null);
      }

      public long hash(Object input, Access access, long off, long len) {
         return CityAndFarmHash_1_1.uoHash64WithSeeds(access.byteOrder(input, ByteOrder.LITTLE_ENDIAN), input, off, len, this.seed0, this.seed1);
      }
   }
}
