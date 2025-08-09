package io.jsonwebtoken.impl.lang;

import io.jsonwebtoken.impl.security.Randoms;
import io.jsonwebtoken.lang.Arrays;
import io.jsonwebtoken.lang.Assert;

public final class Bytes {
   public static final byte[] EMPTY = new byte[0];
   private static final int LONG_BYTE_LENGTH = 8;
   private static final int INT_BYTE_LENGTH = 4;
   public static final String LONG_REQD_MSG = "Long byte arrays must be 8 bytes in length.";
   public static final String INT_REQD_MSG = "Integer byte arrays must be 4 bytes in length.";

   private Bytes() {
   }

   public static byte[] nullSafe(byte[] bytes) {
      return bytes != null ? bytes : EMPTY;
   }

   public static byte[] randomBits(int numBits) {
      return random(numBits / 8);
   }

   public static byte[] random(int numBytes) {
      if (numBytes <= 0) {
         throw new IllegalArgumentException("numBytes argument must be >= 0");
      } else {
         byte[] bytes = new byte[numBytes];
         Randoms.secureRandom().nextBytes(bytes);
         return bytes;
      }
   }

   public static byte[] toBytes(int i) {
      return new byte[]{(byte)(i >>> 24), (byte)(i >>> 16), (byte)(i >>> 8), (byte)i};
   }

   public static byte[] toBytes(long l) {
      return new byte[]{(byte)((int)(l >>> 56)), (byte)((int)(l >>> 48)), (byte)((int)(l >>> 40)), (byte)((int)(l >>> 32)), (byte)((int)(l >>> 24)), (byte)((int)(l >>> 16)), (byte)((int)(l >>> 8)), (byte)((int)l)};
   }

   public static long toLong(byte[] bytes) {
      Assert.isTrue(Arrays.length(bytes) == 8, "Long byte arrays must be 8 bytes in length.");
      return ((long)bytes[0] & 255L) << 56 | ((long)bytes[1] & 255L) << 48 | ((long)bytes[2] & 255L) << 40 | ((long)bytes[3] & 255L) << 32 | ((long)bytes[4] & 255L) << 24 | ((long)bytes[5] & 255L) << 16 | ((long)bytes[6] & 255L) << 8 | (long)bytes[7] & 255L;
   }

   public static int toInt(byte[] bytes) {
      Assert.isTrue(Arrays.length(bytes) == 4, "Integer byte arrays must be 4 bytes in length.");
      return (bytes[0] & 255) << 24 | (bytes[1] & 255) << 16 | (bytes[2] & 255) << 8 | bytes[3] & 255;
   }

   public static int indexOf(byte[] source, byte[] target) {
      return indexOf(source, target, 0);
   }

   public static int indexOf(byte[] source, byte[] target, int fromIndex) {
      return indexOf(source, 0, length(source), target, 0, length(target), fromIndex);
   }

   static int indexOf(byte[] source, int srcOffset, int srcLen, byte[] target, int targetOffset, int targetLen, int fromIndex) {
      if (fromIndex >= srcLen) {
         return targetLen == 0 ? srcLen : -1;
      } else {
         if (fromIndex < 0) {
            fromIndex = 0;
         }

         if (targetLen == 0) {
            return fromIndex;
         } else {
            byte first = target[targetOffset];
            int max = srcOffset + (srcLen - targetLen);

            for(int i = srcOffset + fromIndex; i <= max; ++i) {
               if (source[i] != first) {
                  do {
                     ++i;
                  } while(i <= max && source[i] != first);
               }

               if (i <= max) {
                  int j = i + 1;
                  int end = j + targetLen - 1;

                  for(int k = targetOffset + 1; j < end && source[j] == target[k]; ++k) {
                     ++j;
                  }

                  if (j == end) {
                     return i - srcOffset;
                  }
               }
            }

            return -1;
         }
      }
   }

   public static boolean startsWith(byte[] src, byte[] prefix) {
      return startsWith(src, prefix, 0);
   }

   public static boolean startsWith(byte[] src, byte[] prefix, int offset) {
      int to = offset;
      int po = 0;
      int pc = length(prefix);
      if (offset >= 0 && offset <= length(src) - pc) {
         do {
            --pc;
            if (pc < 0) {
               return true;
            }
         } while(src[to++] == prefix[po++]);

         return false;
      } else {
         return false;
      }
   }

   public static boolean endsWith(byte[] src, byte[] suffix) {
      return startsWith(src, suffix, length(src) - length(suffix));
   }

   public static byte[] concat(byte[]... arrays) {
      int len = 0;
      int numArrays = Arrays.length(arrays);

      for(int i = 0; i < numArrays; ++i) {
         len += length(arrays[i]);
      }

      byte[] output = new byte[len];
      int position = 0;
      if (len > 0) {
         for(byte[] array : arrays) {
            int alen = length(array);
            if (alen > 0) {
               System.arraycopy(array, 0, output, position, alen);
               position += alen;
            }
         }
      }

      return output;
   }

   public static void clear(byte[] bytes) {
      if (!isEmpty(bytes)) {
         java.util.Arrays.fill(bytes, (byte)0);
      }
   }

   public static boolean isEmpty(byte[] bytes) {
      return length(bytes) == 0;
   }

   public static int length(byte[] bytes) {
      return bytes == null ? 0 : bytes.length;
   }

   public static long bitLength(byte[] bytes) {
      return (long)length(bytes) * 8L;
   }

   public static int length(int bitLength) {
      if (bitLength < 0) {
         throw new IllegalArgumentException("bitLength argument must be >= 0");
      } else {
         return (bitLength + 7) / 8;
      }
   }

   public static String bitsMsg(long bitLength) {
      return bitLength + " bits (" + bitLength / 8L + " bytes)";
   }

   public static String bytesMsg(int byteArrayLength) {
      return bitsMsg((long)byteArrayLength * 8L);
   }

   public static void increment(byte[] a) {
      for(int i = a.length - 1; i >= 0 && ++a[i] == 0; --i) {
      }

   }

   public static byte[] prepad(byte[] bytes, int length) {
      Assert.notNull(bytes, "byte array cannot be null.");
      Assert.gt(length, 0, "length must be positive (> 0).");
      if (bytes.length < length) {
         byte[] padded = new byte[length];
         System.arraycopy(bytes, 0, padded, length - bytes.length, bytes.length);
         bytes = padded;
      }

      return bytes;
   }
}
