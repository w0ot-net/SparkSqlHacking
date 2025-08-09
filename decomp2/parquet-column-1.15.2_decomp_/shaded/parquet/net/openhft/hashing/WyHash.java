package shaded.parquet.net.openhft.hashing;

import java.nio.ByteOrder;

class WyHash {
   public static final long _wyp0 = -6884282663029611473L;
   public static final long _wyp1 = -1800455987208640293L;
   public static final long _wyp2 = -8161530843051276573L;
   public static final long _wyp3 = 6384245875588680899L;
   public static final long _wyp4 = 2129725606500045391L;

   private static long _wymum(long lhs, long rhs) {
      return Maths.unsignedLongMulXorFold(lhs, rhs);
   }

   private static long _wyr3(Access access, Object in, long index, long k) {
      return (long)access.u8(in, index) << 16 | (long)access.u8(in, index + (k >>> 1)) << 8 | (long)access.u8(in, index + k - 1L);
   }

   private static long u64Rorate32(Access access, Object in, long index) {
      return access.u32(in, index) << 32 | access.u32(in, index + 4L);
   }

   static long wyHash64(long seed, Object input, Access access, long off, long length) {
      if (length <= 0L) {
         return 0L;
      } else if (length < 4L) {
         return _wymum(_wymum(_wyr3(access, input, off, length) ^ seed ^ -6884282663029611473L, seed ^ -1800455987208640293L) ^ seed, length ^ 2129725606500045391L);
      } else if (length <= 8L) {
         return _wymum(_wymum(access.u32(input, off) ^ seed ^ -6884282663029611473L, access.u32(input, off + length - 4L) ^ seed ^ -1800455987208640293L) ^ seed, length ^ 2129725606500045391L);
      } else if (length <= 16L) {
         return _wymum(_wymum(u64Rorate32(access, input, off) ^ seed ^ -6884282663029611473L, u64Rorate32(access, input, off + length - 8L) ^ seed ^ -1800455987208640293L) ^ seed, length ^ 2129725606500045391L);
      } else if (length <= 24L) {
         return _wymum(_wymum(u64Rorate32(access, input, off) ^ seed ^ -6884282663029611473L, u64Rorate32(access, input, off + 8L) ^ seed ^ -1800455987208640293L) ^ _wymum(u64Rorate32(access, input, off + length - 8L) ^ seed ^ -8161530843051276573L, seed ^ 6384245875588680899L), length ^ 2129725606500045391L);
      } else if (length <= 32L) {
         return _wymum(_wymum(u64Rorate32(access, input, off) ^ seed ^ -6884282663029611473L, u64Rorate32(access, input, off + 8L) ^ seed ^ -1800455987208640293L) ^ _wymum(u64Rorate32(access, input, off + 16L) ^ seed ^ -8161530843051276573L, u64Rorate32(access, input, off + length - 8L) ^ seed ^ 6384245875588680899L), length ^ 2129725606500045391L);
      } else {
         long see1 = seed;
         long i = length;

         long p;
         for(p = off; i > 256L; p += 256L) {
            seed = _wymum(access.i64(input, p) ^ seed ^ -6884282663029611473L, access.i64(input, p + 8L) ^ seed ^ -1800455987208640293L) ^ _wymum(access.i64(input, p + 16L) ^ seed ^ -8161530843051276573L, access.i64(input, p + 24L) ^ seed ^ 6384245875588680899L);
            see1 = _wymum(access.i64(input, p + 32L) ^ see1 ^ -1800455987208640293L, access.i64(input, p + 40L) ^ see1 ^ -8161530843051276573L) ^ _wymum(access.i64(input, p + 48L) ^ see1 ^ 6384245875588680899L, access.i64(input, p + 56L) ^ see1 ^ -6884282663029611473L);
            seed = _wymum(access.i64(input, p + 64L) ^ seed ^ -6884282663029611473L, access.i64(input, p + 72L) ^ seed ^ -1800455987208640293L) ^ _wymum(access.i64(input, p + 80L) ^ seed ^ -8161530843051276573L, access.i64(input, p + 88L) ^ seed ^ 6384245875588680899L);
            see1 = _wymum(access.i64(input, p + 96L) ^ see1 ^ -1800455987208640293L, access.i64(input, p + 104L) ^ see1 ^ -8161530843051276573L) ^ _wymum(access.i64(input, p + 112L) ^ see1 ^ 6384245875588680899L, access.i64(input, p + 120L) ^ see1 ^ -6884282663029611473L);
            seed = _wymum(access.i64(input, p + 128L) ^ seed ^ -6884282663029611473L, access.i64(input, p + 136L) ^ seed ^ -1800455987208640293L) ^ _wymum(access.i64(input, p + 144L) ^ seed ^ -8161530843051276573L, access.i64(input, p + 152L) ^ seed ^ 6384245875588680899L);
            see1 = _wymum(access.i64(input, p + 160L) ^ see1 ^ -1800455987208640293L, access.i64(input, p + 168L) ^ see1 ^ -8161530843051276573L) ^ _wymum(access.i64(input, p + 176L) ^ see1 ^ 6384245875588680899L, access.i64(input, p + 184L) ^ see1 ^ -6884282663029611473L);
            seed = _wymum(access.i64(input, p + 192L) ^ seed ^ -6884282663029611473L, access.i64(input, p + 200L) ^ seed ^ -1800455987208640293L) ^ _wymum(access.i64(input, p + 208L) ^ seed ^ -8161530843051276573L, access.i64(input, p + 216L) ^ seed ^ 6384245875588680899L);
            see1 = _wymum(access.i64(input, p + 224L) ^ see1 ^ -1800455987208640293L, access.i64(input, p + 232L) ^ see1 ^ -8161530843051276573L) ^ _wymum(access.i64(input, p + 240L) ^ see1 ^ 6384245875588680899L, access.i64(input, p + 248L) ^ see1 ^ -6884282663029611473L);
            i -= 256L;
         }

         while(i > 32L) {
            seed = _wymum(access.i64(input, p) ^ seed ^ -6884282663029611473L, access.i64(input, p + 8L) ^ seed ^ -1800455987208640293L);
            see1 = _wymum(access.i64(input, p + 16L) ^ see1 ^ -8161530843051276573L, access.i64(input, p + 24L) ^ see1 ^ 6384245875588680899L);
            i -= 32L;
            p += 32L;
         }

         if (i < 4L) {
            seed = _wymum(_wyr3(access, input, p, i) ^ seed ^ -6884282663029611473L, seed ^ -1800455987208640293L);
         } else if (i <= 8L) {
            seed = _wymum(access.u32(input, p) ^ seed ^ -6884282663029611473L, access.u32(input, p + i - 4L) ^ seed ^ -1800455987208640293L);
         } else if (i <= 16L) {
            seed = _wymum(u64Rorate32(access, input, p) ^ seed ^ -6884282663029611473L, u64Rorate32(access, input, p + i - 8L) ^ seed ^ -1800455987208640293L);
         } else if (i <= 24L) {
            seed = _wymum(u64Rorate32(access, input, p) ^ seed ^ -6884282663029611473L, u64Rorate32(access, input, p + 8L) ^ seed ^ -1800455987208640293L);
            see1 = _wymum(u64Rorate32(access, input, p + i - 8L) ^ see1 ^ -8161530843051276573L, see1 ^ 6384245875588680899L);
         } else {
            seed = _wymum(u64Rorate32(access, input, p) ^ seed ^ -6884282663029611473L, u64Rorate32(access, input, p + 8L) ^ seed ^ -1800455987208640293L);
            see1 = _wymum(u64Rorate32(access, input, p + 16L) ^ see1 ^ -8161530843051276573L, u64Rorate32(access, input, p + i - 8L) ^ see1 ^ 6384245875588680899L);
         }

         return _wymum(seed ^ see1, length ^ 2129725606500045391L);
      }
   }

   static LongHashFunction asLongHashFunctionWithoutSeed() {
      return WyHash.AsLongHashFunction.SEEDLESS_INSTANCE;
   }

   static LongHashFunction asLongHashFunctionWithSeed(long seed) {
      return new AsLongHashFunctionSeeded(seed);
   }

   private static class AsLongHashFunction extends LongHashFunction {
      private static final long serialVersionUID = 0L;
      static final AsLongHashFunction SEEDLESS_INSTANCE = new AsLongHashFunction();

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
         long hi = input & 4294967295L;
         long lo = input >>> 32 & 4294967295L;
         return WyHash._wymum(WyHash._wymum(hi ^ this.seed() ^ -6884282663029611473L, lo ^ this.seed() ^ -1800455987208640293L) ^ this.seed(), 2129725606500045383L);
      }

      public long hashInt(int input) {
         input = Primitives.nativeToLittleEndian(input);
         long longInput = (long)input & 4294967295L;
         return WyHash._wymum(WyHash._wymum(longInput ^ this.seed() ^ -6884282663029611473L, longInput ^ this.seed() ^ -1800455987208640293L) ^ this.seed(), 2129725606500045387L);
      }

      public long hashShort(short input) {
         input = Primitives.nativeToLittleEndian(input);
         long hi = (long)(input >>> 8) & 255L;
         long wyr3 = hi | hi << 8 | ((long)input & 255L) << 16;
         return WyHash._wymum(WyHash._wymum(wyr3 ^ this.seed() ^ -6884282663029611473L, this.seed() ^ -1800455987208640293L) ^ this.seed(), 2129725606500045389L);
      }

      public long hashChar(char input) {
         return this.hashShort((short)input);
      }

      public long hashByte(byte input) {
         long hi = (long)input & 255L;
         long wyr3 = hi | hi << 8 | hi << 16;
         return WyHash._wymum(WyHash._wymum(wyr3 ^ this.seed() ^ -6884282663029611473L, this.seed() ^ -1800455987208640293L) ^ this.seed(), 2129725606500045390L);
      }

      public long hashVoid() {
         return 0L;
      }

      public long hash(Object input, Access access, long off, long len) {
         long seed = this.seed();
         return WyHash.wyHash64(seed, input, access.byteOrder(input, ByteOrder.LITTLE_ENDIAN), off, len);
      }
   }

   private static class AsLongHashFunctionSeeded extends AsLongHashFunction {
      private static final long serialVersionUID = 0L;
      private final long seed;

      private AsLongHashFunctionSeeded(long seed) {
         this.seed = seed;
      }

      public long seed() {
         return this.seed;
      }
   }
}
