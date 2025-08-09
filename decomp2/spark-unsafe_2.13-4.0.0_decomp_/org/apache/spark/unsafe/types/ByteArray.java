package org.apache.spark.unsafe.types;

import java.nio.ByteOrder;
import java.util.Arrays;
import org.apache.spark.unsafe.Platform;
import org.sparkproject.guava.primitives.Ints;

public final class ByteArray {
   public static final byte[] EMPTY_BYTE = new byte[0];
   private static final boolean IS_LITTLE_ENDIAN;

   public static void writeToMemory(byte[] src, Object target, long targetOffset) {
      Platform.copyMemory(src, (long)Platform.BYTE_ARRAY_OFFSET, target, targetOffset, (long)src.length);
   }

   public static long getPrefix(byte[] bytes) {
      return bytes == null ? 0L : getPrefix(bytes, (long)Platform.BYTE_ARRAY_OFFSET, bytes.length);
   }

   static long getPrefix(Object base, long offset, int numBytes) {
      long p;
      long mask;
      if (numBytes >= 8) {
         p = Platform.getLong(base, offset);
         mask = 0L;
      } else if (numBytes > 4) {
         p = Platform.getLong(base, offset);
         mask = (1L << (8 - numBytes) * 8) - 1L;
      } else if (numBytes > 0) {
         long pRaw = (long)Platform.getInt(base, offset);
         p = IS_LITTLE_ENDIAN ? pRaw : pRaw << 32;
         mask = (1L << (8 - numBytes) * 8) - 1L;
      } else {
         p = 0L;
         mask = 0L;
      }

      return (IS_LITTLE_ENDIAN ? Long.reverseBytes(p) : p) & ~mask;
   }

   public static int compareBinary(byte[] leftBase, byte[] rightBase) {
      return compareBinary(leftBase, (long)Platform.BYTE_ARRAY_OFFSET, leftBase.length, rightBase, (long)Platform.BYTE_ARRAY_OFFSET, rightBase.length);
   }

   static int compareBinary(Object leftBase, long leftOffset, int leftNumBytes, Object rightBase, long rightOffset, int rightNumBytes) {
      int len = Math.min(leftNumBytes, rightNumBytes);
      int wordMax = len / 8 * 8;

      for(int i = 0; i < wordMax; i += 8) {
         long left = Platform.getLong(leftBase, leftOffset + (long)i);
         long right = Platform.getLong(rightBase, rightOffset + (long)i);
         if (left != right) {
            if (IS_LITTLE_ENDIAN) {
               return Long.compareUnsigned(Long.reverseBytes(left), Long.reverseBytes(right));
            }

            return Long.compareUnsigned(left, right);
         }
      }

      for(int i = wordMax; i < len; ++i) {
         int res = (Platform.getByte(leftBase, leftOffset + (long)i) & 255) - (Platform.getByte(rightBase, rightOffset + (long)i) & 255);
         if (res != 0) {
            return res;
         }
      }

      return leftNumBytes - rightNumBytes;
   }

   public static byte[] subStringSQL(byte[] bytes, int pos, int len) {
      if (pos > bytes.length) {
         return EMPTY_BYTE;
      } else {
         int start = 0;
         if (pos > 0) {
            start = pos - 1;
         } else if (pos < 0) {
            start = bytes.length + pos;
         }

         int end;
         if (bytes.length - start < len) {
            end = bytes.length;
         } else {
            end = start + len;
         }

         start = Math.max(start, 0);
         return start >= end ? EMPTY_BYTE : Arrays.copyOfRange(bytes, start, end);
      }
   }

   public static byte[] concat(byte[]... inputs) {
      return concatWS(EMPTY_BYTE, inputs);
   }

   public static byte[] concatWS(byte[] delimiter, byte[]... inputs) {
      if (delimiter == null) {
         return null;
      } else {
         long totalLength = 0L;

         for(byte[] input : inputs) {
            if (input == null) {
               return null;
            }

            totalLength += (long)(input.length + delimiter.length);
         }

         if (totalLength > 0L) {
            totalLength -= (long)delimiter.length;
         }

         byte[] result = new byte[Ints.checkedCast(totalLength)];
         int offset = 0;

         for(int i = 0; i < inputs.length; ++i) {
            byte[] input = inputs[i];
            int len = input.length;
            Platform.copyMemory(input, (long)Platform.BYTE_ARRAY_OFFSET, result, (long)(Platform.BYTE_ARRAY_OFFSET + offset), (long)len);
            offset += len;
            if (delimiter.length > 0 && i < inputs.length - 1) {
               Platform.copyMemory(delimiter, (long)Platform.BYTE_ARRAY_OFFSET, result, (long)(Platform.BYTE_ARRAY_OFFSET + offset), (long)delimiter.length);
               offset += delimiter.length;
            }
         }

         return result;
      }
   }

   private static byte[] padWithEmptyPattern(byte[] bytes, int len) {
      len = Math.min(bytes.length, len);
      byte[] result = new byte[len];
      Platform.copyMemory(bytes, (long)Platform.BYTE_ARRAY_OFFSET, result, (long)Platform.BYTE_ARRAY_OFFSET, (long)len);
      return result;
   }

   private static void fillWithPattern(byte[] result, int firstPos, int beyondPos, byte[] pad) {
      for(int pos = firstPos; pos < beyondPos; pos += pad.length) {
         int jMax = Math.min(pad.length, beyondPos - pos);

         for(int j = 0; j < jMax; ++j) {
            result[pos + j] = pad[j];
         }
      }

   }

   public static byte[] lpad(byte[] bytes, int len, byte[] pad) {
      if (bytes != null && pad != null) {
         if (len == 0) {
            return EMPTY_BYTE;
         } else if (pad.length == 0) {
            return padWithEmptyPattern(bytes, len);
         } else {
            byte[] result = new byte[len];
            int minLen = Math.min(len, bytes.length);
            Platform.copyMemory(bytes, (long)Platform.BYTE_ARRAY_OFFSET, result, (long)(Platform.BYTE_ARRAY_OFFSET + len - minLen), (long)minLen);
            if (bytes.length < len) {
               fillWithPattern(result, 0, len - bytes.length, pad);
            }

            return result;
         }
      } else {
         return null;
      }
   }

   public static byte[] rpad(byte[] bytes, int len, byte[] pad) {
      if (bytes != null && pad != null) {
         if (len == 0) {
            return EMPTY_BYTE;
         } else if (pad.length == 0) {
            return padWithEmptyPattern(bytes, len);
         } else {
            byte[] result = new byte[len];
            Platform.copyMemory(bytes, (long)Platform.BYTE_ARRAY_OFFSET, result, (long)Platform.BYTE_ARRAY_OFFSET, (long)Math.min(len, bytes.length));
            if (bytes.length < len) {
               fillWithPattern(result, bytes.length, len, pad);
            }

            return result;
         }
      } else {
         return null;
      }
   }

   static {
      IS_LITTLE_ENDIAN = ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN;
   }
}
