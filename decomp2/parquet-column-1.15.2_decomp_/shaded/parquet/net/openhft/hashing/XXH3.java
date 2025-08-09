package shaded.parquet.net.openhft.hashing;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

class XXH3 {
   private static final Access unsafeLE;
   private static final byte[] XXH3_kSecret;
   private static final long XXH_PRIME32_1 = 2654435761L;
   private static final long XXH_PRIME32_2 = 2246822519L;
   private static final long XXH_PRIME32_3 = 3266489917L;
   private static final long XXH_PRIME64_1 = -7046029288634856825L;
   private static final long XXH_PRIME64_2 = -4417276706812531889L;
   private static final long XXH_PRIME64_3 = 1609587929392839161L;
   private static final long XXH_PRIME64_4 = -8796714831421723037L;
   private static final long XXH_PRIME64_5 = 2870177450012600261L;
   private static final long nbStripesPerBlock = 16L;
   private static final long block_len = 1024L;

   private static long XXH64_avalanche(long h64) {
      h64 ^= h64 >>> 33;
      h64 *= -4417276706812531889L;
      h64 ^= h64 >>> 29;
      h64 *= 1609587929392839161L;
      return h64 ^ h64 >>> 32;
   }

   private static long XXH3_avalanche(long h64) {
      h64 ^= h64 >>> 37;
      h64 *= 1609587791953885689L;
      return h64 ^ h64 >>> 32;
   }

   private static long XXH3_rrmxmx(long h64, long length) {
      h64 ^= Long.rotateLeft(h64, 49) ^ Long.rotateLeft(h64, 24);
      h64 *= -6939452855193903323L;
      h64 ^= (h64 >>> 35) + length;
      h64 *= -6939452855193903323L;
      return h64 ^ h64 >>> 28;
   }

   private static long XXH3_mix16B(long seed, Object input, Access access, long offIn, long offSec) {
      long input_lo = access.i64(input, offIn);
      long input_hi = access.i64(input, offIn + 8L);
      return Maths.unsignedLongMulXorFold(input_lo ^ unsafeLE.i64(XXH3_kSecret, offSec) + seed, input_hi ^ unsafeLE.i64(XXH3_kSecret, offSec + 8L) - seed);
   }

   private static long XXH128_mix32B_once(long seed, long offSec, long acc, long input0, long input1, long input2, long input3) {
      acc += Maths.unsignedLongMulXorFold(input0 ^ unsafeLE.i64(XXH3_kSecret, offSec) + seed, input1 ^ unsafeLE.i64(XXH3_kSecret, offSec + 8L) - seed);
      return acc ^ input2 + input3;
   }

   private static long XXH3_mix2Accs(long acc_lh, long acc_rh, byte[] secret, long offSec) {
      return Maths.unsignedLongMulXorFold(acc_lh ^ unsafeLE.i64(secret, offSec), acc_rh ^ unsafeLE.i64(secret, offSec + 8L));
   }

   private static long XXH3_64bits_internal(long seed, byte[] secret, Object input, Access access, long off, long length) {
      if (length <= 16L) {
         if (length > 8L) {
            long bitflip1 = (unsafeLE.i64(XXH3_kSecret, 24L + UnsafeAccess.BYTE_BASE) ^ unsafeLE.i64(XXH3_kSecret, 32L + UnsafeAccess.BYTE_BASE)) + seed;
            long bitflip2 = (unsafeLE.i64(XXH3_kSecret, 40L + UnsafeAccess.BYTE_BASE) ^ unsafeLE.i64(XXH3_kSecret, 48L + UnsafeAccess.BYTE_BASE)) - seed;
            long input_lo = access.i64(input, off) ^ bitflip1;
            long input_hi = access.i64(input, off + length - 8L) ^ bitflip2;
            long acc = length + Long.reverseBytes(input_lo) + input_hi + Maths.unsignedLongMulXorFold(input_lo, input_hi);
            return XXH3_avalanche(acc);
         } else if (length >= 4L) {
            long s = seed ^ Long.reverseBytes(seed & 4294967295L);
            long input1 = (long)access.i32(input, off);
            long input2 = access.u32(input, off + length - 4L);
            long bitflip = (unsafeLE.i64(XXH3_kSecret, 8L + UnsafeAccess.BYTE_BASE) ^ unsafeLE.i64(XXH3_kSecret, 16L + UnsafeAccess.BYTE_BASE)) - s;
            long keyed = input2 + (input1 << 32) ^ bitflip;
            return XXH3_rrmxmx(keyed, length);
         } else if (length != 0L) {
            int c1 = access.u8(input, off + 0L);
            int c2 = access.i8(input, off + (length >> 1));
            int c3 = access.u8(input, off + length - 1L);
            long combined = Primitives.unsignedInt(c1 << 16 | c2 << 24 | c3 | (int)length << 8);
            long bitflip = Primitives.unsignedInt(unsafeLE.i32(XXH3_kSecret, UnsafeAccess.BYTE_BASE) ^ unsafeLE.i32(XXH3_kSecret, 4L + UnsafeAccess.BYTE_BASE)) + seed;
            return XXH64_avalanche(combined ^ bitflip);
         } else {
            return XXH64_avalanche(seed ^ unsafeLE.i64(XXH3_kSecret, 56L + UnsafeAccess.BYTE_BASE) ^ unsafeLE.i64(XXH3_kSecret, 64L + UnsafeAccess.BYTE_BASE));
         }
      } else if (length <= 128L) {
         long acc = length * -7046029288634856825L;
         if (length > 32L) {
            if (length > 64L) {
               if (length > 96L) {
                  acc += XXH3_mix16B(seed, input, access, off + 48L, UnsafeAccess.BYTE_BASE + 96L);
                  acc += XXH3_mix16B(seed, input, access, off + length - 64L, UnsafeAccess.BYTE_BASE + 112L);
               }

               acc += XXH3_mix16B(seed, input, access, off + 32L, UnsafeAccess.BYTE_BASE + 64L);
               acc += XXH3_mix16B(seed, input, access, off + length - 48L, UnsafeAccess.BYTE_BASE + 80L);
            }

            acc += XXH3_mix16B(seed, input, access, off + 16L, UnsafeAccess.BYTE_BASE + 32L);
            acc += XXH3_mix16B(seed, input, access, off + length - 32L, UnsafeAccess.BYTE_BASE + 48L);
         }

         acc += XXH3_mix16B(seed, input, access, off, UnsafeAccess.BYTE_BASE);
         acc += XXH3_mix16B(seed, input, access, off + length - 16L, UnsafeAccess.BYTE_BASE + 16L);
         return XXH3_avalanche(acc);
      } else if (length <= 240L) {
         long acc = length * -7046029288634856825L;
         int nbRounds = (int)length / 16;

         int i;
         for(i = 0; i < 8; ++i) {
            acc += XXH3_mix16B(seed, input, access, off + (long)(16 * i), UnsafeAccess.BYTE_BASE + (long)(16 * i));
         }

         for(acc = XXH3_avalanche(acc); i < nbRounds; ++i) {
            acc += XXH3_mix16B(seed, input, access, off + (long)(16 * i), UnsafeAccess.BYTE_BASE + (long)(16 * (i - 8)) + 3L);
         }

         acc += XXH3_mix16B(seed, input, access, off + length - 16L, UnsafeAccess.BYTE_BASE + 136L - 17L);
         return XXH3_avalanche(acc);
      } else {
         long acc_0 = 3266489917L;
         long acc_1 = -7046029288634856825L;
         long acc_2 = -4417276706812531889L;
         long acc_3 = 1609587929392839161L;
         long acc_4 = -8796714831421723037L;
         long acc_5 = 2246822519L;
         long acc_6 = 2870177450012600261L;
         long acc_7 = 2654435761L;
         long nb_blocks = (length - 1L) / 1024L;

         for(long n = 0L; n < nb_blocks; ++n) {
            long offBlock = off + n * 1024L;

            for(long s = 0L; s < 16L; ++s) {
               long offStripe = offBlock + s * 64L;
               long offSec = s * 8L;
               long data_val_0 = access.i64(input, offStripe + 0L);
               long data_val_1 = access.i64(input, offStripe + 8L);
               long data_key_0 = data_val_0 ^ unsafeLE.i64(secret, UnsafeAccess.BYTE_BASE + offSec + 0L);
               long data_key_1 = data_val_1 ^ unsafeLE.i64(secret, UnsafeAccess.BYTE_BASE + offSec + 8L);
               acc_0 += data_val_1 + (4294967295L & data_key_0) * (data_key_0 >>> 32);
               acc_1 += data_val_0 + (4294967295L & data_key_1) * (data_key_1 >>> 32);
               data_val_0 = access.i64(input, offStripe + 16L);
               data_val_1 = access.i64(input, offStripe + 24L);
               data_key_0 = data_val_0 ^ unsafeLE.i64(secret, UnsafeAccess.BYTE_BASE + offSec + 16L);
               data_key_1 = data_val_1 ^ unsafeLE.i64(secret, UnsafeAccess.BYTE_BASE + offSec + 24L);
               acc_2 += data_val_1 + (4294967295L & data_key_0) * (data_key_0 >>> 32);
               acc_3 += data_val_0 + (4294967295L & data_key_1) * (data_key_1 >>> 32);
               data_val_0 = access.i64(input, offStripe + 32L);
               data_val_1 = access.i64(input, offStripe + 40L);
               data_key_0 = data_val_0 ^ unsafeLE.i64(secret, UnsafeAccess.BYTE_BASE + offSec + 32L);
               data_key_1 = data_val_1 ^ unsafeLE.i64(secret, UnsafeAccess.BYTE_BASE + offSec + 40L);
               acc_4 += data_val_1 + (4294967295L & data_key_0) * (data_key_0 >>> 32);
               acc_5 += data_val_0 + (4294967295L & data_key_1) * (data_key_1 >>> 32);
               data_val_0 = access.i64(input, offStripe + 48L);
               data_val_1 = access.i64(input, offStripe + 56L);
               data_key_0 = data_val_0 ^ unsafeLE.i64(secret, UnsafeAccess.BYTE_BASE + offSec + 48L);
               data_key_1 = data_val_1 ^ unsafeLE.i64(secret, UnsafeAccess.BYTE_BASE + offSec + 56L);
               acc_6 += data_val_1 + (4294967295L & data_key_0) * (data_key_0 >>> 32);
               acc_7 += data_val_0 + (4294967295L & data_key_1) * (data_key_1 >>> 32);
            }

            long offSec = UnsafeAccess.BYTE_BASE + 192L - 64L;
            acc_0 = (acc_0 ^ acc_0 >>> 47 ^ unsafeLE.i64(secret, offSec + 0L)) * 2654435761L;
            acc_1 = (acc_1 ^ acc_1 >>> 47 ^ unsafeLE.i64(secret, offSec + 8L)) * 2654435761L;
            acc_2 = (acc_2 ^ acc_2 >>> 47 ^ unsafeLE.i64(secret, offSec + 16L)) * 2654435761L;
            acc_3 = (acc_3 ^ acc_3 >>> 47 ^ unsafeLE.i64(secret, offSec + 24L)) * 2654435761L;
            acc_4 = (acc_4 ^ acc_4 >>> 47 ^ unsafeLE.i64(secret, offSec + 32L)) * 2654435761L;
            acc_5 = (acc_5 ^ acc_5 >>> 47 ^ unsafeLE.i64(secret, offSec + 40L)) * 2654435761L;
            acc_6 = (acc_6 ^ acc_6 >>> 47 ^ unsafeLE.i64(secret, offSec + 48L)) * 2654435761L;
            acc_7 = (acc_7 ^ acc_7 >>> 47 ^ unsafeLE.i64(secret, offSec + 56L)) * 2654435761L;
         }

         long nbStripes = (length - 1L - 1024L * nb_blocks) / 64L;
         long offBlock = off + 1024L * nb_blocks;

         for(long s = 0L; s < nbStripes; ++s) {
            long offStripe = offBlock + s * 64L;
            long offSec = s * 8L;
            long data_val_0 = access.i64(input, offStripe + 0L);
            long data_val_1 = access.i64(input, offStripe + 8L);
            long data_key_0 = data_val_0 ^ unsafeLE.i64(secret, UnsafeAccess.BYTE_BASE + offSec + 0L);
            long data_key_1 = data_val_1 ^ unsafeLE.i64(secret, UnsafeAccess.BYTE_BASE + offSec + 8L);
            acc_0 += data_val_1 + (4294967295L & data_key_0) * (data_key_0 >>> 32);
            acc_1 += data_val_0 + (4294967295L & data_key_1) * (data_key_1 >>> 32);
            data_val_0 = access.i64(input, offStripe + 16L);
            data_val_1 = access.i64(input, offStripe + 24L);
            data_key_0 = data_val_0 ^ unsafeLE.i64(secret, UnsafeAccess.BYTE_BASE + offSec + 16L);
            data_key_1 = data_val_1 ^ unsafeLE.i64(secret, UnsafeAccess.BYTE_BASE + offSec + 24L);
            acc_2 += data_val_1 + (4294967295L & data_key_0) * (data_key_0 >>> 32);
            acc_3 += data_val_0 + (4294967295L & data_key_1) * (data_key_1 >>> 32);
            data_val_0 = access.i64(input, offStripe + 32L);
            data_val_1 = access.i64(input, offStripe + 40L);
            data_key_0 = data_val_0 ^ unsafeLE.i64(secret, UnsafeAccess.BYTE_BASE + offSec + 32L);
            data_key_1 = data_val_1 ^ unsafeLE.i64(secret, UnsafeAccess.BYTE_BASE + offSec + 40L);
            acc_4 += data_val_1 + (4294967295L & data_key_0) * (data_key_0 >>> 32);
            acc_5 += data_val_0 + (4294967295L & data_key_1) * (data_key_1 >>> 32);
            data_val_0 = access.i64(input, offStripe + 48L);
            data_val_1 = access.i64(input, offStripe + 56L);
            data_key_0 = data_val_0 ^ unsafeLE.i64(secret, UnsafeAccess.BYTE_BASE + offSec + 48L);
            data_key_1 = data_val_1 ^ unsafeLE.i64(secret, UnsafeAccess.BYTE_BASE + offSec + 56L);
            acc_6 += data_val_1 + (4294967295L & data_key_0) * (data_key_0 >>> 32);
            acc_7 += data_val_0 + (4294967295L & data_key_1) * (data_key_1 >>> 32);
         }

         long offStripe = off + length - 64L;
         long offSec = 121L;
         long data_val_0 = access.i64(input, offStripe + 0L);
         long data_val_1 = access.i64(input, offStripe + 8L);
         long data_key_0 = data_val_0 ^ unsafeLE.i64(secret, UnsafeAccess.BYTE_BASE + 121L + 0L);
         long data_key_1 = data_val_1 ^ unsafeLE.i64(secret, UnsafeAccess.BYTE_BASE + 121L + 8L);
         acc_0 += data_val_1 + (4294967295L & data_key_0) * (data_key_0 >>> 32);
         acc_1 += data_val_0 + (4294967295L & data_key_1) * (data_key_1 >>> 32);
         data_val_0 = access.i64(input, offStripe + 16L);
         data_val_1 = access.i64(input, offStripe + 24L);
         data_key_0 = data_val_0 ^ unsafeLE.i64(secret, UnsafeAccess.BYTE_BASE + 121L + 16L);
         data_key_1 = data_val_1 ^ unsafeLE.i64(secret, UnsafeAccess.BYTE_BASE + 121L + 24L);
         acc_2 += data_val_1 + (4294967295L & data_key_0) * (data_key_0 >>> 32);
         acc_3 += data_val_0 + (4294967295L & data_key_1) * (data_key_1 >>> 32);
         data_val_0 = access.i64(input, offStripe + 32L);
         data_val_1 = access.i64(input, offStripe + 40L);
         data_key_0 = data_val_0 ^ unsafeLE.i64(secret, UnsafeAccess.BYTE_BASE + 121L + 32L);
         data_key_1 = data_val_1 ^ unsafeLE.i64(secret, UnsafeAccess.BYTE_BASE + 121L + 40L);
         acc_4 += data_val_1 + (4294967295L & data_key_0) * (data_key_0 >>> 32);
         acc_5 += data_val_0 + (4294967295L & data_key_1) * (data_key_1 >>> 32);
         data_val_0 = access.i64(input, offStripe + 48L);
         data_val_1 = access.i64(input, offStripe + 56L);
         data_key_0 = data_val_0 ^ unsafeLE.i64(secret, UnsafeAccess.BYTE_BASE + 121L + 48L);
         data_key_1 = data_val_1 ^ unsafeLE.i64(secret, UnsafeAccess.BYTE_BASE + 121L + 56L);
         acc_6 += data_val_1 + (4294967295L & data_key_0) * (data_key_0 >>> 32);
         acc_7 += data_val_0 + (4294967295L & data_key_1) * (data_key_1 >>> 32);
         data_val_0 = length * -7046029288634856825L + XXH3_mix2Accs(acc_0, acc_1, secret, UnsafeAccess.BYTE_BASE + 11L) + XXH3_mix2Accs(acc_2, acc_3, secret, UnsafeAccess.BYTE_BASE + 11L + 16L) + XXH3_mix2Accs(acc_4, acc_5, secret, UnsafeAccess.BYTE_BASE + 11L + 32L) + XXH3_mix2Accs(acc_6, acc_7, secret, UnsafeAccess.BYTE_BASE + 11L + 48L);
         return XXH3_avalanche(data_val_0);
      }
   }

   private static long XXH3_128bits_internal(long seed, byte[] secret, Object input, Access access, long off, long length, long[] result) {
      if (length <= 16L) {
         if (length > 8L) {
            long bitflipl = (unsafeLE.i64(XXH3_kSecret, 32L + UnsafeAccess.BYTE_BASE) ^ unsafeLE.i64(XXH3_kSecret, 40L + UnsafeAccess.BYTE_BASE)) - seed;
            long bitfliph = (unsafeLE.i64(XXH3_kSecret, 48L + UnsafeAccess.BYTE_BASE) ^ unsafeLE.i64(XXH3_kSecret, 56L + UnsafeAccess.BYTE_BASE)) + seed;
            long input_hi = access.i64(input, off + length - 8L);
            long input_lo = access.i64(input, off) ^ input_hi ^ bitflipl;
            long m128_lo = input_lo * -7046029288634856825L;
            long m128_hi = Maths.unsignedLongMulHigh(input_lo, -7046029288634856825L);
            m128_lo += length - 1L << 54;
            input_hi ^= bitfliph;
            m128_hi += input_hi + Primitives.unsignedInt((int)input_hi) * 2246822518L;
            m128_lo ^= Long.reverseBytes(m128_hi);
            long low = XXH3_avalanche(m128_lo * -4417276706812531889L);
            if (null != result) {
               result[0] = low;
               result[1] = XXH3_avalanche(Maths.unsignedLongMulHigh(m128_lo, -4417276706812531889L) + m128_hi * -4417276706812531889L);
            }

            return low;
         } else if (length >= 4L) {
            long s = seed ^ Long.reverseBytes(seed & 4294967295L);
            long input_lo = access.u32(input, off);
            long input_hi = (long)access.i32(input, off + length - 4L);
            long bitflip = (unsafeLE.i64(XXH3_kSecret, 16L + UnsafeAccess.BYTE_BASE) ^ unsafeLE.i64(XXH3_kSecret, 24L + UnsafeAccess.BYTE_BASE)) + s;
            long keyed = input_lo + (input_hi << 32) ^ bitflip;
            long pl = -7046029288634856825L + (length << 2);
            long m128_lo = keyed * pl;
            long m128_hi = Maths.unsignedLongMulHigh(keyed, pl);
            m128_hi += m128_lo << 1;
            m128_lo ^= m128_hi >>> 3;
            m128_lo ^= m128_lo >>> 35;
            m128_lo *= -6939452855193903323L;
            m128_lo ^= m128_lo >>> 28;
            if (null != result) {
               result[0] = m128_lo;
               result[1] = XXH3_avalanche(m128_hi);
            }

            return m128_lo;
         } else if (length != 0L) {
            int c1 = access.u8(input, off + 0L);
            int c2 = access.i8(input, off + (length >> 1));
            int c3 = access.u8(input, off + length - 1L);
            int combinedl = c1 << 16 | c2 << 24 | c3 | (int)length << 8;
            int combinedh = Integer.rotateLeft(Integer.reverseBytes(combinedl), 13);
            long bitflipl = Primitives.unsignedInt(unsafeLE.i32(XXH3_kSecret, UnsafeAccess.BYTE_BASE) ^ unsafeLE.i32(XXH3_kSecret, UnsafeAccess.BYTE_BASE + 4L)) + seed;
            long bitfliph = Primitives.unsignedInt(unsafeLE.i32(XXH3_kSecret, UnsafeAccess.BYTE_BASE + 8L) ^ unsafeLE.i32(XXH3_kSecret, UnsafeAccess.BYTE_BASE + 12L)) - seed;
            long low = XXH64_avalanche(Primitives.unsignedInt(combinedl) ^ bitflipl);
            if (null != result) {
               result[0] = low;
               result[1] = XXH64_avalanche(Primitives.unsignedInt(combinedh) ^ bitfliph);
            }

            return low;
         } else {
            long low = XXH64_avalanche(seed ^ unsafeLE.i64(XXH3_kSecret, UnsafeAccess.BYTE_BASE + 64L) ^ unsafeLE.i64(XXH3_kSecret, UnsafeAccess.BYTE_BASE + 72L));
            if (null != result) {
               result[0] = low;
               result[1] = XXH64_avalanche(seed ^ unsafeLE.i64(XXH3_kSecret, UnsafeAccess.BYTE_BASE + 80L) ^ unsafeLE.i64(XXH3_kSecret, UnsafeAccess.BYTE_BASE + 88L));
            }

            return low;
         }
      } else if (length <= 128L) {
         long acc0 = length * -7046029288634856825L;
         long acc1 = 0L;
         if (length > 32L) {
            if (length > 64L) {
               if (length > 96L) {
                  long input0 = access.i64(input, off + 48L);
                  long input1 = access.i64(input, off + 48L + 8L);
                  long input2 = access.i64(input, off + length - 64L);
                  long input3 = access.i64(input, off + length - 64L + 8L);
                  acc0 = XXH128_mix32B_once(seed, UnsafeAccess.BYTE_BASE + 96L, acc0, input0, input1, input2, input3);
                  acc1 = XXH128_mix32B_once(seed, UnsafeAccess.BYTE_BASE + 96L + 16L, acc1, input2, input3, input0, input1);
               }

               long input0 = access.i64(input, off + 32L);
               long input1 = access.i64(input, off + 32L + 8L);
               long input2 = access.i64(input, off + length - 48L);
               long input3 = access.i64(input, off + length - 48L + 8L);
               acc0 = XXH128_mix32B_once(seed, UnsafeAccess.BYTE_BASE + 64L, acc0, input0, input1, input2, input3);
               acc1 = XXH128_mix32B_once(seed, UnsafeAccess.BYTE_BASE + 64L + 16L, acc1, input2, input3, input0, input1);
            }

            long input0 = access.i64(input, off + 16L);
            long input1 = access.i64(input, off + 16L + 8L);
            long input2 = access.i64(input, off + length - 32L);
            long input3 = access.i64(input, off + length - 32L + 8L);
            acc0 = XXH128_mix32B_once(seed, UnsafeAccess.BYTE_BASE + 32L, acc0, input0, input1, input2, input3);
            acc1 = XXH128_mix32B_once(seed, UnsafeAccess.BYTE_BASE + 32L + 16L, acc1, input2, input3, input0, input1);
         }

         long input0 = access.i64(input, off + 0L);
         long input1 = access.i64(input, off + 0L + 8L);
         long input2 = access.i64(input, off + length - 16L);
         long input3 = access.i64(input, off + length - 16L + 8L);
         acc0 = XXH128_mix32B_once(seed, UnsafeAccess.BYTE_BASE, acc0, input0, input1, input2, input3);
         acc1 = XXH128_mix32B_once(seed, UnsafeAccess.BYTE_BASE + 16L, acc1, input2, input3, input0, input1);
         long low = XXH3_avalanche(acc0 + acc1);
         if (null != result) {
            result[0] = low;
            result[1] = -XXH3_avalanche(acc0 * -7046029288634856825L + acc1 * -8796714831421723037L + (length - seed) * -4417276706812531889L);
         }

         return low;
      } else if (length <= 240L) {
         int nbRounds = (int)length / 32;
         long acc0 = length * -7046029288634856825L;
         long acc1 = 0L;

         int i;
         for(i = 0; i < 4; ++i) {
            long input0 = access.i64(input, off + (long)(32 * i));
            long input1 = access.i64(input, off + (long)(32 * i) + 8L);
            long input2 = access.i64(input, off + (long)(32 * i) + 16L);
            long input3 = access.i64(input, off + (long)(32 * i) + 24L);
            acc0 = XXH128_mix32B_once(seed, UnsafeAccess.BYTE_BASE + (long)(32 * i), acc0, input0, input1, input2, input3);
            acc1 = XXH128_mix32B_once(seed, UnsafeAccess.BYTE_BASE + (long)(32 * i) + 16L, acc1, input2, input3, input0, input1);
         }

         acc0 = XXH3_avalanche(acc0);

         for(acc1 = XXH3_avalanche(acc1); i < nbRounds; ++i) {
            long input0 = access.i64(input, off + (long)(32 * i));
            long input1 = access.i64(input, off + (long)(32 * i) + 8L);
            long input2 = access.i64(input, off + (long)(32 * i) + 16L);
            long input3 = access.i64(input, off + (long)(32 * i) + 24L);
            acc0 = XXH128_mix32B_once(seed, UnsafeAccess.BYTE_BASE + 3L + (long)(32 * (i - 4)), acc0, input0, input1, input2, input3);
            acc1 = XXH128_mix32B_once(seed, UnsafeAccess.BYTE_BASE + 3L + (long)(32 * (i - 4)) + 16L, acc1, input2, input3, input0, input1);
         }

         long input0 = access.i64(input, off + length - 16L);
         long input1 = access.i64(input, off + length - 16L + 8L);
         long input2 = access.i64(input, off + length - 32L);
         long input3 = access.i64(input, off + length - 32L + 8L);
         acc0 = XXH128_mix32B_once(-seed, UnsafeAccess.BYTE_BASE + 136L - 17L - 16L, acc0, input0, input1, input2, input3);
         acc1 = XXH128_mix32B_once(-seed, UnsafeAccess.BYTE_BASE + 136L - 17L, acc1, input2, input3, input0, input1);
         long low = XXH3_avalanche(acc0 + acc1);
         if (null != result) {
            result[0] = low;
            result[1] = -XXH3_avalanche(acc0 * -7046029288634856825L + acc1 * -8796714831421723037L + (length - seed) * -4417276706812531889L);
         }

         return low;
      } else {
         long acc_0 = 3266489917L;
         long acc_1 = -7046029288634856825L;
         long acc_2 = -4417276706812531889L;
         long acc_3 = 1609587929392839161L;
         long acc_4 = -8796714831421723037L;
         long acc_5 = 2246822519L;
         long acc_6 = 2870177450012600261L;
         long acc_7 = 2654435761L;
         long nb_blocks = (length - 1L) / 1024L;

         for(long n = 0L; n < nb_blocks; ++n) {
            long offBlock = off + n * 1024L;

            for(long s = 0L; s < 16L; ++s) {
               long offStripe = offBlock + s * 64L;
               long offSec = s * 8L;
               long data_val_0 = access.i64(input, offStripe + 0L);
               long data_val_1 = access.i64(input, offStripe + 8L);
               long data_key_0 = data_val_0 ^ unsafeLE.i64(secret, UnsafeAccess.BYTE_BASE + offSec + 0L);
               long data_key_1 = data_val_1 ^ unsafeLE.i64(secret, UnsafeAccess.BYTE_BASE + offSec + 8L);
               acc_0 += data_val_1 + (4294967295L & data_key_0) * (data_key_0 >>> 32);
               acc_1 += data_val_0 + (4294967295L & data_key_1) * (data_key_1 >>> 32);
               data_val_0 = access.i64(input, offStripe + 16L);
               data_val_1 = access.i64(input, offStripe + 24L);
               data_key_0 = data_val_0 ^ unsafeLE.i64(secret, UnsafeAccess.BYTE_BASE + offSec + 16L);
               data_key_1 = data_val_1 ^ unsafeLE.i64(secret, UnsafeAccess.BYTE_BASE + offSec + 24L);
               acc_2 += data_val_1 + (4294967295L & data_key_0) * (data_key_0 >>> 32);
               acc_3 += data_val_0 + (4294967295L & data_key_1) * (data_key_1 >>> 32);
               data_val_0 = access.i64(input, offStripe + 32L);
               data_val_1 = access.i64(input, offStripe + 40L);
               data_key_0 = data_val_0 ^ unsafeLE.i64(secret, UnsafeAccess.BYTE_BASE + offSec + 32L);
               data_key_1 = data_val_1 ^ unsafeLE.i64(secret, UnsafeAccess.BYTE_BASE + offSec + 40L);
               acc_4 += data_val_1 + (4294967295L & data_key_0) * (data_key_0 >>> 32);
               acc_5 += data_val_0 + (4294967295L & data_key_1) * (data_key_1 >>> 32);
               data_val_0 = access.i64(input, offStripe + 48L);
               data_val_1 = access.i64(input, offStripe + 56L);
               data_key_0 = data_val_0 ^ unsafeLE.i64(secret, UnsafeAccess.BYTE_BASE + offSec + 48L);
               data_key_1 = data_val_1 ^ unsafeLE.i64(secret, UnsafeAccess.BYTE_BASE + offSec + 56L);
               acc_6 += data_val_1 + (4294967295L & data_key_0) * (data_key_0 >>> 32);
               acc_7 += data_val_0 + (4294967295L & data_key_1) * (data_key_1 >>> 32);
            }

            long offSec = UnsafeAccess.BYTE_BASE + 192L - 64L;
            acc_0 = (acc_0 ^ acc_0 >>> 47 ^ unsafeLE.i64(secret, offSec + 0L)) * 2654435761L;
            acc_1 = (acc_1 ^ acc_1 >>> 47 ^ unsafeLE.i64(secret, offSec + 8L)) * 2654435761L;
            acc_2 = (acc_2 ^ acc_2 >>> 47 ^ unsafeLE.i64(secret, offSec + 16L)) * 2654435761L;
            acc_3 = (acc_3 ^ acc_3 >>> 47 ^ unsafeLE.i64(secret, offSec + 24L)) * 2654435761L;
            acc_4 = (acc_4 ^ acc_4 >>> 47 ^ unsafeLE.i64(secret, offSec + 32L)) * 2654435761L;
            acc_5 = (acc_5 ^ acc_5 >>> 47 ^ unsafeLE.i64(secret, offSec + 40L)) * 2654435761L;
            acc_6 = (acc_6 ^ acc_6 >>> 47 ^ unsafeLE.i64(secret, offSec + 48L)) * 2654435761L;
            acc_7 = (acc_7 ^ acc_7 >>> 47 ^ unsafeLE.i64(secret, offSec + 56L)) * 2654435761L;
         }

         long nbStripes = (length - 1L - 1024L * nb_blocks) / 64L;
         long offBlock = off + 1024L * nb_blocks;

         for(long s = 0L; s < nbStripes; ++s) {
            long offStripe = offBlock + s * 64L;
            long offSec = s * 8L;
            long data_val_0 = access.i64(input, offStripe + 0L);
            long data_val_1 = access.i64(input, offStripe + 8L);
            long data_key_0 = data_val_0 ^ unsafeLE.i64(secret, UnsafeAccess.BYTE_BASE + offSec + 0L);
            long data_key_1 = data_val_1 ^ unsafeLE.i64(secret, UnsafeAccess.BYTE_BASE + offSec + 8L);
            acc_0 += data_val_1 + (4294967295L & data_key_0) * (data_key_0 >>> 32);
            acc_1 += data_val_0 + (4294967295L & data_key_1) * (data_key_1 >>> 32);
            data_val_0 = access.i64(input, offStripe + 16L);
            data_val_1 = access.i64(input, offStripe + 24L);
            data_key_0 = data_val_0 ^ unsafeLE.i64(secret, UnsafeAccess.BYTE_BASE + offSec + 16L);
            data_key_1 = data_val_1 ^ unsafeLE.i64(secret, UnsafeAccess.BYTE_BASE + offSec + 24L);
            acc_2 += data_val_1 + (4294967295L & data_key_0) * (data_key_0 >>> 32);
            acc_3 += data_val_0 + (4294967295L & data_key_1) * (data_key_1 >>> 32);
            data_val_0 = access.i64(input, offStripe + 32L);
            data_val_1 = access.i64(input, offStripe + 40L);
            data_key_0 = data_val_0 ^ unsafeLE.i64(secret, UnsafeAccess.BYTE_BASE + offSec + 32L);
            data_key_1 = data_val_1 ^ unsafeLE.i64(secret, UnsafeAccess.BYTE_BASE + offSec + 40L);
            acc_4 += data_val_1 + (4294967295L & data_key_0) * (data_key_0 >>> 32);
            acc_5 += data_val_0 + (4294967295L & data_key_1) * (data_key_1 >>> 32);
            data_val_0 = access.i64(input, offStripe + 48L);
            data_val_1 = access.i64(input, offStripe + 56L);
            data_key_0 = data_val_0 ^ unsafeLE.i64(secret, UnsafeAccess.BYTE_BASE + offSec + 48L);
            data_key_1 = data_val_1 ^ unsafeLE.i64(secret, UnsafeAccess.BYTE_BASE + offSec + 56L);
            acc_6 += data_val_1 + (4294967295L & data_key_0) * (data_key_0 >>> 32);
            acc_7 += data_val_0 + (4294967295L & data_key_1) * (data_key_1 >>> 32);
         }

         long offStripe = off + length - 64L;
         long offSec = 121L;
         long data_val_0 = access.i64(input, offStripe + 0L);
         long data_val_1 = access.i64(input, offStripe + 8L);
         long data_key_0 = data_val_0 ^ unsafeLE.i64(secret, UnsafeAccess.BYTE_BASE + 121L + 0L);
         long data_key_1 = data_val_1 ^ unsafeLE.i64(secret, UnsafeAccess.BYTE_BASE + 121L + 8L);
         acc_0 += data_val_1 + (4294967295L & data_key_0) * (data_key_0 >>> 32);
         acc_1 += data_val_0 + (4294967295L & data_key_1) * (data_key_1 >>> 32);
         data_val_0 = access.i64(input, offStripe + 16L);
         data_val_1 = access.i64(input, offStripe + 24L);
         data_key_0 = data_val_0 ^ unsafeLE.i64(secret, UnsafeAccess.BYTE_BASE + 121L + 16L);
         data_key_1 = data_val_1 ^ unsafeLE.i64(secret, UnsafeAccess.BYTE_BASE + 121L + 24L);
         acc_2 += data_val_1 + (4294967295L & data_key_0) * (data_key_0 >>> 32);
         acc_3 += data_val_0 + (4294967295L & data_key_1) * (data_key_1 >>> 32);
         data_val_0 = access.i64(input, offStripe + 32L);
         data_val_1 = access.i64(input, offStripe + 40L);
         data_key_0 = data_val_0 ^ unsafeLE.i64(secret, UnsafeAccess.BYTE_BASE + 121L + 32L);
         data_key_1 = data_val_1 ^ unsafeLE.i64(secret, UnsafeAccess.BYTE_BASE + 121L + 40L);
         acc_4 += data_val_1 + (4294967295L & data_key_0) * (data_key_0 >>> 32);
         acc_5 += data_val_0 + (4294967295L & data_key_1) * (data_key_1 >>> 32);
         data_val_0 = access.i64(input, offStripe + 48L);
         data_val_1 = access.i64(input, offStripe + 56L);
         data_key_0 = data_val_0 ^ unsafeLE.i64(secret, UnsafeAccess.BYTE_BASE + 121L + 48L);
         data_key_1 = data_val_1 ^ unsafeLE.i64(secret, UnsafeAccess.BYTE_BASE + 121L + 56L);
         acc_6 += data_val_1 + (4294967295L & data_key_0) * (data_key_0 >>> 32);
         acc_7 += data_val_0 + (4294967295L & data_key_1) * (data_key_1 >>> 32);
         data_val_0 = XXH3_avalanche(length * -7046029288634856825L + XXH3_mix2Accs(acc_0, acc_1, secret, UnsafeAccess.BYTE_BASE + 11L) + XXH3_mix2Accs(acc_2, acc_3, secret, UnsafeAccess.BYTE_BASE + 11L + 16L) + XXH3_mix2Accs(acc_4, acc_5, secret, UnsafeAccess.BYTE_BASE + 11L + 32L) + XXH3_mix2Accs(acc_6, acc_7, secret, UnsafeAccess.BYTE_BASE + 11L + 48L));
         if (null != result) {
            result[0] = data_val_0;
            result[1] = XXH3_avalanche(~(length * -4417276706812531889L) + XXH3_mix2Accs(acc_0, acc_1, secret, UnsafeAccess.BYTE_BASE + 192L - 64L - 11L) + XXH3_mix2Accs(acc_2, acc_3, secret, UnsafeAccess.BYTE_BASE + 192L - 64L - 11L + 16L) + XXH3_mix2Accs(acc_4, acc_5, secret, UnsafeAccess.BYTE_BASE + 192L - 64L - 11L + 32L) + XXH3_mix2Accs(acc_6, acc_7, secret, UnsafeAccess.BYTE_BASE + 192L - 64L - 11L + 48L));
         }

         return data_val_0;
      }
   }

   private static void XXH3_initCustomSecret(byte[] customSecret, long seed64) {
      int nbRounds = 12;
      ByteBuffer bb = ByteBuffer.wrap(customSecret).order(ByteOrder.LITTLE_ENDIAN);

      for(int i = 0; i < 12; ++i) {
         long lo = unsafeLE.i64(XXH3_kSecret, UnsafeAccess.BYTE_BASE + (long)(16 * i)) + seed64;
         long hi = unsafeLE.i64(XXH3_kSecret, UnsafeAccess.BYTE_BASE + (long)(16 * i) + 8L) - seed64;
         bb.putLong(16 * i + 0, lo);
         bb.putLong(16 * i + 8, hi);
      }

   }

   static LongHashFunction asLongHashFunctionWithoutSeed() {
      return XXH3.AsLongHashFunction.SEEDLESS_INSTANCE;
   }

   static LongHashFunction asLongHashFunctionWithSeed(long seed) {
      return (LongHashFunction)(0L == seed ? XXH3.AsLongHashFunction.SEEDLESS_INSTANCE : new AsLongHashFunctionSeeded(seed));
   }

   static LongTupleHashFunction asLongTupleHashFunctionWithoutSeed() {
      return XXH3.AsLongTupleHashFunction.SEEDLESS_INSTANCE;
   }

   static LongHashFunction asLongTupleLowHashFunctionWithoutSeed() {
      return XXH3.AsLongTupleHashFunction.SEEDLESS_INSTANCE.asLongHashFunction();
   }

   static LongTupleHashFunction asLongTupleHashFunctionWithSeed(long seed) {
      return (LongTupleHashFunction)(0L == seed ? XXH3.AsLongTupleHashFunction.SEEDLESS_INSTANCE : new AsLongTupleHashFunctionSeeded(seed));
   }

   static LongHashFunction asLongTupleLowHashFunctionWithSeed(long seed) {
      return (new AsLongTupleHashFunctionSeeded(seed)).asLongHashFunction();
   }

   static {
      unsafeLE = UnsafeAccess.INSTANCE.byteOrder((Object)null, ByteOrder.LITTLE_ENDIAN);
      XXH3_kSecret = new byte[]{-72, -2, 108, 57, 35, -92, 75, -66, 124, 1, -127, 44, -9, 33, -83, 28, -34, -44, 109, -23, -125, -112, -105, -37, 114, 64, -92, -92, -73, -77, 103, 31, -53, 121, -26, 78, -52, -64, -27, 120, -126, 90, -48, 125, -52, -1, 114, 33, -72, 8, 70, 116, -9, 67, 36, -114, -32, 53, -112, -26, -127, 58, 38, 76, 60, 40, 82, -69, -111, -61, 0, -53, -120, -48, 101, -117, 27, 83, 46, -93, 113, 100, 72, -105, -94, 13, -7, 78, 56, 25, -17, 70, -87, -34, -84, -40, -88, -6, 118, 63, -29, -100, 52, 63, -7, -36, -69, -57, -57, 11, 79, 29, -118, 81, -32, 75, -51, -76, 89, 49, -56, -97, 126, -55, -39, 120, 115, 100, -22, -59, -84, -125, 52, -45, -21, -61, -59, -127, -96, -1, -6, 19, 99, -21, 23, 13, -35, 81, -73, -16, -38, 73, -45, 22, 85, 38, 41, -44, 104, -98, 43, 22, -66, 88, 125, 71, -95, -4, -113, -8, -72, -47, 122, -48, 49, -50, 69, -53, 58, -113, -107, 22, 4, 40, -81, -41, -5, -54, -69, 75, 64, 126};
   }

   private static class AsLongHashFunction extends LongHashFunction {
      private static final long serialVersionUID = 0L;
      private static final AsLongHashFunction SEEDLESS_INSTANCE = new AsLongHashFunction();

      private AsLongHashFunction() {
      }

      public long seed() {
         return 0L;
      }

      public long hashLong(long input) {
         input = Primitives.nativeToLittleEndian(input);
         long s = this.seed() ^ Long.reverseBytes(this.seed() & 4294967295L);
         long bitflip = (XXH3.unsafeLE.i64(XXH3.XXH3_kSecret, 8L + UnsafeAccess.BYTE_BASE) ^ XXH3.unsafeLE.i64(XXH3.XXH3_kSecret, 16L + UnsafeAccess.BYTE_BASE)) - s;
         long keyed = Long.rotateLeft(input, 32) ^ bitflip;
         return XXH3.XXH3_rrmxmx(keyed, 8L);
      }

      public long hashInt(int input) {
         input = Primitives.nativeToLittleEndian(input);
         long s = this.seed() ^ Long.reverseBytes(this.seed() & 4294967295L);
         long bitflip = (XXH3.unsafeLE.i64(XXH3.XXH3_kSecret, 8L + UnsafeAccess.BYTE_BASE) ^ XXH3.unsafeLE.i64(XXH3.XXH3_kSecret, 16L + UnsafeAccess.BYTE_BASE)) - s;
         long keyed = Primitives.unsignedInt(input) + ((long)input << 32) ^ bitflip;
         return XXH3.XXH3_rrmxmx(keyed, 4L);
      }

      public long hashShort(short input) {
         input = Primitives.nativeToLittleEndian(input);
         int c1 = Primitives.unsignedByte((byte)input);
         int c2 = Primitives.unsignedShort(input) >>> 8;
         long combined = Primitives.unsignedInt(c1 << 16 | c2 << 24 | c2 | 512);
         long bitflip = (XXH3.unsafeLE.u32(XXH3.XXH3_kSecret, UnsafeAccess.BYTE_BASE) ^ XXH3.unsafeLE.u32(XXH3.XXH3_kSecret, 4L + UnsafeAccess.BYTE_BASE)) + this.seed();
         return XXH3.XXH64_avalanche(combined ^ bitflip);
      }

      public long hashChar(char input) {
         return this.hashShort((short)input);
      }

      public long hashByte(byte input) {
         int c1 = Primitives.unsignedByte(input);
         long combined = Primitives.unsignedInt(c1 << 16 | c1 << 24 | c1 | 256);
         long bitflip = (XXH3.unsafeLE.u32(XXH3.XXH3_kSecret, UnsafeAccess.BYTE_BASE) ^ XXH3.unsafeLE.u32(XXH3.XXH3_kSecret, 4L + UnsafeAccess.BYTE_BASE)) + this.seed();
         return XXH3.XXH64_avalanche(combined ^ bitflip);
      }

      public long hashVoid() {
         return XXH3.XXH64_avalanche(this.seed() ^ XXH3.unsafeLE.i64(XXH3.XXH3_kSecret, 56L + UnsafeAccess.BYTE_BASE) ^ XXH3.unsafeLE.i64(XXH3.XXH3_kSecret, 64L + UnsafeAccess.BYTE_BASE));
      }

      public long hash(Object input, Access access, long off, long len) {
         return XXH3.XXH3_64bits_internal(0L, XXH3.XXH3_kSecret, input, access.byteOrder(input, ByteOrder.LITTLE_ENDIAN), off, len);
      }
   }

   private static class AsLongHashFunctionSeeded extends AsLongHashFunction {
      private static final long serialVersionUID = 0L;
      private final long seed;
      private final byte[] secret;

      private AsLongHashFunctionSeeded(long seed) {
         this.secret = new byte[192];
         this.seed = seed;
         XXH3.XXH3_initCustomSecret(this.secret, seed);
      }

      public long seed() {
         return this.seed;
      }

      public long hash(Object input, Access access, long off, long len) {
         return XXH3.XXH3_64bits_internal(this.seed, this.secret, input, access.byteOrder(input, ByteOrder.LITTLE_ENDIAN), off, len);
      }
   }

   private static class AsLongTupleHashFunction extends DualHashFunction {
      private static final long serialVersionUID = 0L;
      private static final AsLongTupleHashFunction SEEDLESS_INSTANCE = new AsLongTupleHashFunction();

      private AsLongTupleHashFunction() {
      }

      public long seed() {
         return 0L;
      }

      public int bitsLength() {
         return 128;
      }

      public long[] newResultArray() {
         return new long[2];
      }

      public long dualHashLong(long input, long[] result) {
         input = Primitives.nativeToLittleEndian(input);
         long s = this.seed() ^ Long.reverseBytes(this.seed() & 4294967295L);
         long bitflip = (XXH3.unsafeLE.i64(XXH3.XXH3_kSecret, 16L + UnsafeAccess.BYTE_BASE) ^ XXH3.unsafeLE.i64(XXH3.XXH3_kSecret, 24L + UnsafeAccess.BYTE_BASE)) + s;
         long keyed = input ^ bitflip;
         long pl = -7046029288634856793L;
         long m128_lo = keyed * -7046029288634856793L;
         long m128_hi = Maths.unsignedLongMulHigh(keyed, -7046029288634856793L);
         m128_hi += m128_lo << 1;
         m128_lo ^= m128_hi >>> 3;
         m128_lo ^= m128_lo >>> 35;
         m128_lo *= -6939452855193903323L;
         m128_lo ^= m128_lo >>> 28;
         if (null != result) {
            result[0] = m128_lo;
            result[1] = XXH3.XXH3_avalanche(m128_hi);
         }

         return m128_lo;
      }

      public long dualHashInt(int input, long[] result) {
         long inputU = Primitives.unsignedInt(Primitives.nativeToLittleEndian(input));
         long s = this.seed() ^ Long.reverseBytes(this.seed() & 4294967295L);
         long bitflip = (XXH3.unsafeLE.i64(XXH3.XXH3_kSecret, 16L + UnsafeAccess.BYTE_BASE) ^ XXH3.unsafeLE.i64(XXH3.XXH3_kSecret, 24L + UnsafeAccess.BYTE_BASE)) + s;
         long keyed = inputU + (inputU << 32) ^ bitflip;
         long pl = -7046029288634856809L;
         long m128_lo = keyed * -7046029288634856809L;
         long m128_hi = Maths.unsignedLongMulHigh(keyed, -7046029288634856809L);
         m128_hi += m128_lo << 1;
         m128_lo ^= m128_hi >>> 3;
         m128_lo ^= m128_lo >>> 35;
         m128_lo *= -6939452855193903323L;
         m128_lo ^= m128_lo >>> 28;
         if (null != result) {
            result[0] = m128_lo;
            result[1] = XXH3.XXH3_avalanche(m128_hi);
         }

         return m128_lo;
      }

      public long dualHashShort(short input, long[] result) {
         input = Primitives.nativeToLittleEndian(input);
         int c1 = Primitives.unsignedByte((byte)input);
         int c2 = Primitives.unsignedShort(input) >>> 8;
         int combinedl = c1 << 16 | c2 << 24 | c2 | 512;
         int combinedh = Integer.rotateLeft(Integer.reverseBytes(combinedl), 13);
         long bitflipl = Primitives.unsignedInt(XXH3.unsafeLE.i32(XXH3.XXH3_kSecret, UnsafeAccess.BYTE_BASE) ^ XXH3.unsafeLE.i32(XXH3.XXH3_kSecret, UnsafeAccess.BYTE_BASE + 4L)) + this.seed();
         long bitfliph = Primitives.unsignedInt(XXH3.unsafeLE.i32(XXH3.XXH3_kSecret, UnsafeAccess.BYTE_BASE + 8L) ^ XXH3.unsafeLE.i32(XXH3.XXH3_kSecret, UnsafeAccess.BYTE_BASE + 12L)) - this.seed();
         long low = XXH3.XXH64_avalanche(Primitives.unsignedInt(combinedl) ^ bitflipl);
         if (null != result) {
            result[0] = low;
            result[1] = XXH3.XXH64_avalanche(Primitives.unsignedInt(combinedh) ^ bitfliph);
         }

         return low;
      }

      public long dualHashChar(char input, long[] result) {
         return this.dualHashShort((short)input, result);
      }

      public long dualHashByte(byte input, long[] result) {
         int c1 = Primitives.unsignedByte(input);
         int combinedl = c1 << 16 | input << 24 | c1 | 256;
         int combinedh = Integer.rotateLeft(Integer.reverseBytes(combinedl), 13);
         long bitflipl = Primitives.unsignedInt(XXH3.unsafeLE.i32(XXH3.XXH3_kSecret, UnsafeAccess.BYTE_BASE) ^ XXH3.unsafeLE.i32(XXH3.XXH3_kSecret, UnsafeAccess.BYTE_BASE + 4L)) + this.seed();
         long bitfliph = Primitives.unsignedInt(XXH3.unsafeLE.i32(XXH3.XXH3_kSecret, UnsafeAccess.BYTE_BASE + 8L) ^ XXH3.unsafeLE.i32(XXH3.XXH3_kSecret, UnsafeAccess.BYTE_BASE + 12L)) - this.seed();
         long low = XXH3.XXH64_avalanche(Primitives.unsignedInt(combinedl) ^ bitflipl);
         if (null != result) {
            result[0] = low;
            result[1] = XXH3.XXH64_avalanche(Primitives.unsignedInt(combinedh) ^ bitfliph);
         }

         return low;
      }

      public long dualHashVoid(long[] result) {
         long low = XXH3.XXH64_avalanche(this.seed() ^ XXH3.unsafeLE.i64(XXH3.XXH3_kSecret, UnsafeAccess.BYTE_BASE + 64L) ^ XXH3.unsafeLE.i64(XXH3.XXH3_kSecret, UnsafeAccess.BYTE_BASE + 72L));
         if (null != result) {
            result[0] = low;
            result[1] = XXH3.XXH64_avalanche(this.seed() ^ XXH3.unsafeLE.i64(XXH3.XXH3_kSecret, UnsafeAccess.BYTE_BASE + 80L) ^ XXH3.unsafeLE.i64(XXH3.XXH3_kSecret, UnsafeAccess.BYTE_BASE + 88L));
         }

         return low;
      }

      public long dualHash(Object input, Access access, long off, long len, long[] result) {
         return XXH3.XXH3_128bits_internal(0L, XXH3.XXH3_kSecret, input, access.byteOrder(input, ByteOrder.LITTLE_ENDIAN), off, len, result);
      }
   }

   private static class AsLongTupleHashFunctionSeeded extends AsLongTupleHashFunction {
      private static final long serialVersionUID = 0L;
      private final long seed;
      private final byte[] secret;

      private AsLongTupleHashFunctionSeeded(long seed) {
         this.secret = new byte[192];
         this.seed = seed;
         XXH3.XXH3_initCustomSecret(this.secret, seed);
      }

      public long seed() {
         return this.seed;
      }

      public long dualHash(Object input, Access access, long off, long len, long[] result) {
         return XXH3.XXH3_128bits_internal(this.seed, this.secret, input, access.byteOrder(input, ByteOrder.LITTLE_ENDIAN), off, len, result);
      }
   }
}
