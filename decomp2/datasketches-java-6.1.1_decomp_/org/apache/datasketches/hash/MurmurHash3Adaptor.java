package org.apache.datasketches.hash;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.common.SketchesStateException;
import org.apache.datasketches.common.Util;

public final class MurmurHash3Adaptor {
   private static final long BIT62 = 4611686018427387904L;
   private static final long MAX_LONG = Long.MAX_VALUE;
   private static final long INT_MASK = 2147483647L;
   private static final long PRIME = 9219741426499971445L;

   private MurmurHash3Adaptor() {
   }

   public static byte[] hashToBytes(long datum, long seed) {
      long[] data = new long[]{datum};
      return toByteArray(MurmurHash3.hash(data, seed));
   }

   public static byte[] hashToBytes(long[] data, long seed) {
      return data != null && data.length != 0 ? toByteArray(MurmurHash3.hash(data, seed)) : null;
   }

   public static byte[] hashToBytes(int[] data, long seed) {
      return data != null && data.length != 0 ? toByteArray(MurmurHash3.hash(data, seed)) : null;
   }

   public static byte[] hashToBytes(char[] data, long seed) {
      return data != null && data.length != 0 ? toByteArray(MurmurHash3.hash(data, seed)) : null;
   }

   public static byte[] hashToBytes(byte[] data, long seed) {
      return data != null && data.length != 0 ? toByteArray(MurmurHash3.hash(data, seed)) : null;
   }

   public static byte[] hashToBytes(double datum, long seed) {
      double d = datum == (double)0.0F ? (double)0.0F : datum;
      long[] data = new long[]{Double.doubleToLongBits(d)};
      return toByteArray(MurmurHash3.hash(data, seed));
   }

   public static byte[] hashToBytes(String datum, long seed) {
      if (datum != null && !datum.isEmpty()) {
         byte[] data = datum.getBytes(StandardCharsets.UTF_8);
         return toByteArray(MurmurHash3.hash(data, seed));
      } else {
         return null;
      }
   }

   public static long[] hashToLongs(long datum, long seed) {
      long[] data = new long[]{datum};
      return MurmurHash3.hash(data, seed);
   }

   public static long[] hashToLongs(long[] data, long seed) {
      return data != null && data.length != 0 ? MurmurHash3.hash(data, seed) : null;
   }

   public static long[] hashToLongs(int[] data, long seed) {
      return data != null && data.length != 0 ? MurmurHash3.hash(data, seed) : null;
   }

   public static long[] hashToLongs(char[] data, long seed) {
      return data != null && data.length != 0 ? MurmurHash3.hash(data, seed) : null;
   }

   public static long[] hashToLongs(byte[] data, long seed) {
      return data != null && data.length != 0 ? MurmurHash3.hash(data, seed) : null;
   }

   public static long[] hashToLongs(double datum, long seed) {
      double d = datum == (double)0.0F ? (double)0.0F : datum;
      long[] data = new long[]{Double.doubleToLongBits(d)};
      return MurmurHash3.hash(data, seed);
   }

   public static long[] hashToLongs(String datum, long seed) {
      if (datum != null && !datum.isEmpty()) {
         byte[] data = datum.getBytes(StandardCharsets.UTF_8);
         return MurmurHash3.hash(data, seed);
      } else {
         return null;
      }
   }

   public static int asInt(long[] data, int n) {
      if (data != null && data.length != 0) {
         return asInteger(data, n);
      } else {
         throw new SketchesArgumentException("Input is null or empty.");
      }
   }

   public static int asInt(int[] data, int n) {
      if (data != null && data.length != 0) {
         return asInteger(toLongArray(data), n);
      } else {
         throw new SketchesArgumentException("Input is null or empty.");
      }
   }

   public static int asInt(byte[] data, int n) {
      if (data != null && data.length != 0) {
         return asInteger(toLongArray(data), n);
      } else {
         throw new SketchesArgumentException("Input is null or empty.");
      }
   }

   public static int asInt(long datum, int n) {
      long[] data = new long[]{datum};
      return asInteger(data, n);
   }

   public static int asInt(double datum, int n) {
      double d = datum == (double)0.0F ? (double)0.0F : datum;
      long[] data = new long[]{Double.doubleToLongBits(d)};
      return asInteger(data, n);
   }

   public static int asInt(String datum, int n) {
      if (datum != null && !datum.isEmpty()) {
         byte[] data = datum.getBytes(StandardCharsets.UTF_8);
         return asInteger(toLongArray(data), n);
      } else {
         throw new SketchesArgumentException("Input is null or empty.");
      }
   }

   private static int asInteger(long[] data, int n) {
      int cnt = 0;
      long seed = 0L;
      if (n < 2) {
         throw new SketchesArgumentException("Given value of n must be &gt; 1.");
      } else if (n > 1073741824) {
         while(true) {
            ++cnt;
            if (cnt >= 10000) {
               throw new SketchesStateException("Internal Error: Failed to find integer &lt; n within 10000 iterations.");
            }

            long[] h = MurmurHash3.hash(data, seed);
            int t = (int)(h[0] & 2147483647L);
            if (t < n) {
               return t;
            }

            t = (int)(h[0] >>> 33);
            if (t < n) {
               return t;
            }

            t = (int)(h[1] & 2147483647L);
            if (t < n) {
               return t;
            }

            t = (int)(h[1] >>> 33);
            if (t < n) {
               return t;
            }

            seed += 9219741426499971445L;
         }
      } else {
         long mask = (long)(Util.ceilingPowerOf2(n) - 1);

         while(true) {
            ++cnt;
            if (cnt >= 10000) {
               throw new SketchesStateException("Internal Error: Failed to find integer &lt; n within 10000 iterations.");
            }

            long[] h = MurmurHash3.hash(data, seed);
            int t = (int)(h[0] & mask);
            if (t < n) {
               return t;
            }

            t = (int)(h[0] >>> 33 & mask);
            if (t < n) {
               return t;
            }

            t = (int)(h[1] & mask);
            if (t < n) {
               return t;
            }

            t = (int)(h[1] >>> 33 & mask);
            if (t < n) {
               return t;
            }

            seed += 9219741426499971445L;
         }
      }
   }

   public static double asDouble(long[] hash) {
      return (double)(hash[0] >>> 12) * (double)2.220446E-16F;
   }

   public static int modulo(long h0, long h1, int divisor) {
      long d = (long)divisor;
      long modH0 = h0 < 0L ? addRule(mulRule(4611686018427387904L, 2L, d), h0 & Long.MAX_VALUE, d) : h0 % d;
      long modH1 = h1 < 0L ? addRule(mulRule(4611686018427387904L, 2L, d), h1 & Long.MAX_VALUE, d) : h1 % d;
      long modTop = mulRule(mulRule(4611686018427387904L, 4L, d), modH1, d);
      return (int)addRule(modTop, modH0, d);
   }

   public static int modulo(long[] hash, int divisor) {
      return modulo(hash[0], hash[1], divisor);
   }

   private static long addRule(long a, long b, long d) {
      return (a % d + b % d) % d;
   }

   private static long mulRule(long a, long b, long d) {
      return a % d * (b % d) % d;
   }

   private static byte[] toByteArray(long[] hash) {
      byte[] bArr = new byte[16];
      ByteBuffer bb = ByteBuffer.wrap(bArr);
      bb.putLong(hash[0]);
      bb.putLong(hash[1]);
      return bArr;
   }

   private static long[] toLongArray(byte[] data) {
      int dataLen = data.length;
      int longLen = (dataLen + 7) / 8;
      long[] longArr = new long[longLen];

      for(int bi = 0; bi < dataLen; ++bi) {
         int li = bi / 8;
         longArr[li] |= (long)data[bi] << bi * 8 % 64;
      }

      return longArr;
   }

   private static long[] toLongArray(int[] data) {
      int dataLen = data.length;
      int longLen = (dataLen + 1) / 2;
      long[] longArr = new long[longLen];

      for(int ii = 0; ii < dataLen; ++ii) {
         int li = ii / 2;
         longArr[li] |= (long)data[ii] << ii * 32 % 64;
      }

      return longArr;
   }
}
