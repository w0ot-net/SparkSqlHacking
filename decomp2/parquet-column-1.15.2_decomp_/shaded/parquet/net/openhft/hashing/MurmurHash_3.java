package shaded.parquet.net.openhft.hashing;

import java.nio.ByteOrder;
import javax.annotation.ParametersAreNonnullByDefault;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@ParametersAreNonnullByDefault
class MurmurHash_3 {
   private static final long C1 = -8663945395140668459L;
   private static final long C2 = 5545529020109919103L;

   private static long hash(long seed, @Nullable Object input, Access access, long offset, long length, @Nullable long[] result) {
      long h1 = seed;
      long h2 = seed;

      long remaining;
      for(remaining = length; remaining >= 16L; h2 = h2 * 5L + 944331445L) {
         long k1 = access.i64(input, offset);
         long k2 = access.i64(input, offset + 8L);
         offset += 16L;
         remaining -= 16L;
         long var19 = h1 ^ mixK1(k1);
         long var20 = Long.rotateLeft(var19, 27);
         long var21 = var20 + h2;
         h1 = var21 * 5L + 1390208809L;
         h2 ^= mixK2(k2);
         h2 = Long.rotateLeft(h2, 31);
         h2 += h1;
      }

      if (remaining > 0L) {
         long k1 = 0L;
         long k2 = 0L;
         switch ((int)remaining) {
            case 0:
               break;
            case 3:
               k1 ^= (long)access.u8(input, offset + 2L) << 16;
            case 2:
               k1 ^= (long)access.u8(input, offset + 1L) << 8;
            case 1:
               k1 ^= (long)access.u8(input, offset);
               break;
            case 7:
               k1 ^= (long)access.u8(input, offset + 6L) << 48;
            case 6:
               k1 ^= (long)access.u8(input, offset + 5L) << 40;
            case 5:
               k1 ^= (long)access.u8(input, offset + 4L) << 32;
            case 4:
               k1 ^= access.u32(input, offset);
               break;
            case 15:
               k2 ^= (long)access.u8(input, offset + 14L) << 48;
            case 14:
               k2 ^= (long)access.u8(input, offset + 13L) << 40;
            case 13:
               k2 ^= (long)access.u8(input, offset + 12L) << 32;
            case 12:
               k2 ^= (long)access.u8(input, offset + 11L) << 24;
            case 11:
               k2 ^= (long)access.u8(input, offset + 10L) << 16;
            case 10:
               k2 ^= (long)access.u8(input, offset + 9L) << 8;
            case 9:
               k2 ^= (long)access.u8(input, offset + 8L);
            case 8:
               k1 ^= access.i64(input, offset);
               break;
            default:
               throw new AssertionError("Should never get here.");
         }

         h1 ^= mixK1(k1);
         h2 ^= mixK2(k2);
      }

      return finalize(length, h1, h2, result);
   }

   private static long finalize(long length, long h1, long h2, @Nullable long[] result) {
      h1 ^= length;
      h2 ^= length;
      h1 += h2;
      h2 += h1;
      h1 = fmix64(h1);
      h2 = fmix64(h2);
      if (null != result) {
         h1 += h2;
         result[0] = h1;
         result[1] = h1 + h2;
         return h1;
      } else {
         return h1 + h2;
      }
   }

   private static long fmix64(long k) {
      k ^= k >>> 33;
      k *= -49064778989728563L;
      k ^= k >>> 33;
      k *= -4265267296055464877L;
      k ^= k >>> 33;
      return k;
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

   @NotNull
   static LongTupleHashFunction asLongTupleHashFunctionWithoutSeed() {
      return MurmurHash_3.AsLongTupleHashFunction.SEEDLESS_INSTANCE;
   }

   @NotNull
   static LongHashFunction asLongHashFunctionWithoutSeed() {
      return MurmurHash_3.AsLongTupleHashFunction.SEEDLESS_INSTANCE_LONG;
   }

   @NotNull
   static LongTupleHashFunction asLongTupleHashFunctionWithSeed(long seed) {
      return new AsLongTupleHashFunctionSeeded(seed);
   }

   @NotNull
   static LongHashFunction asLongHashFunctionWithSeed(long seed) {
      return (new AsLongTupleHashFunctionSeeded(seed)).asLongHashFunction();
   }

   private static class AsLongTupleHashFunction extends DualHashFunction {
      private static final long serialVersionUID = 0L;
      @NotNull
      private static final AsLongTupleHashFunction SEEDLESS_INSTANCE = new AsLongTupleHashFunction();
      @NotNull
      private static final LongHashFunction SEEDLESS_INSTANCE_LONG;

      private AsLongTupleHashFunction() {
      }

      private Object readResolve() {
         return SEEDLESS_INSTANCE;
      }

      public int bitsLength() {
         return 128;
      }

      @NotNull
      public long[] newResultArray() {
         return new long[2];
      }

      long seed() {
         return 0L;
      }

      protected long hashNativeLong(long nativeLong, long len, @Nullable long[] result) {
         long h1 = MurmurHash_3.mixK1(nativeLong);
         long h2 = 0L;
         return MurmurHash_3.finalize(len, h1, h2, result);
      }

      public long dualHashLong(long input, @Nullable long[] result) {
         return this.hashNativeLong(Primitives.nativeToLittleEndian(input), 8L, result);
      }

      public long dualHashInt(int input, @Nullable long[] result) {
         return this.hashNativeLong(Primitives.unsignedInt(Primitives.nativeToLittleEndian(input)), 4L, result);
      }

      public long dualHashShort(short input, @Nullable long[] result) {
         return this.hashNativeLong((long)Primitives.unsignedShort(Primitives.nativeToLittleEndian(input)), 2L, result);
      }

      public long dualHashChar(char input, @Nullable long[] result) {
         return this.hashNativeLong((long)Primitives.unsignedShort(Primitives.nativeToLittleEndian(input)), 2L, result);
      }

      public long dualHashByte(byte input, @Nullable long[] result) {
         return this.hashNativeLong((long)Primitives.unsignedByte(input), 1L, result);
      }

      public long dualHashVoid(@Nullable long[] result) {
         if (null != result) {
            result[0] = 0L;
            result[1] = 0L;
         }

         return 0L;
      }

      public long dualHash(@Nullable Object input, Access access, long off, long len, @Nullable long[] result) {
         long seed = this.seed();
         return MurmurHash_3.hash(seed, input, access.byteOrder(input, ByteOrder.LITTLE_ENDIAN), off, len, result);
      }

      static {
         SEEDLESS_INSTANCE_LONG = SEEDLESS_INSTANCE.asLongHashFunction();
      }
   }

   private static class AsLongTupleHashFunctionSeeded extends AsLongTupleHashFunction {
      private static final long serialVersionUID = 0L;
      private final long seed;
      @NotNull
      private final transient long[] voidHash;

      private AsLongTupleHashFunctionSeeded(long seed) {
         this.voidHash = this.newResultArray();
         this.seed = seed;
         MurmurHash_3.finalize(0L, seed, seed, this.voidHash);
      }

      long seed() {
         return this.seed;
      }

      protected long hashNativeLong(long nativeLong, long len, @Nullable long[] result) {
         long seed = this.seed;
         long h1 = seed ^ MurmurHash_3.mixK1(nativeLong);
         return MurmurHash_3.finalize(len, h1, seed, result);
      }

      public long dualHashVoid(@Nullable long[] result) {
         if (null != result) {
            result[0] = this.voidHash[0];
            result[1] = this.voidHash[1];
         }

         return this.voidHash[0];
      }
   }
}
