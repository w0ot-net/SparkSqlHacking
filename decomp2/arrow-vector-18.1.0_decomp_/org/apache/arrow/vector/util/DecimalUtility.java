package org.apache.arrow.vector.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.memory.util.MemoryUtil;

public class DecimalUtility {
   public static final byte[] zeroes = new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
   public static final byte[] minus_one = new byte[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
   private static final boolean LITTLE_ENDIAN;

   private DecimalUtility() {
   }

   public static BigDecimal getBigDecimalFromArrowBuf(ArrowBuf bytebuf, int index, int scale, int byteWidth) {
      byte[] value = new byte[byteWidth];
      long startIndex = (long)index * (long)byteWidth;
      bytebuf.getBytes(startIndex, value, 0, byteWidth);
      if (LITTLE_ENDIAN) {
         int stop = byteWidth / 2;

         for(int i = 0; i < stop; ++i) {
            byte temp = value[i];
            int j = byteWidth - 1 - i;
            value[i] = value[j];
            value[j] = temp;
         }
      }

      BigInteger unscaledValue = new BigInteger(value);
      return new BigDecimal(unscaledValue, scale);
   }

   public static BigDecimal getBigDecimalFromByteBuffer(ByteBuffer bytebuf, int scale, int byteWidth) {
      byte[] value = new byte[byteWidth];
      bytebuf.get(value);
      BigInteger unscaledValue = new BigInteger(value);
      return new BigDecimal(unscaledValue, scale);
   }

   public static byte[] getByteArrayFromArrowBuf(ArrowBuf bytebuf, int index, int byteWidth) {
      byte[] value = new byte[byteWidth];
      long startIndex = (long)index * (long)byteWidth;
      bytebuf.getBytes(startIndex, value, 0, byteWidth);
      return value;
   }

   public static boolean checkPrecisionAndScale(BigDecimal value, int vectorPrecision, int vectorScale) {
      if (value.scale() != vectorScale) {
         int var3 = value.scale();
         throw new UnsupportedOperationException("BigDecimal scale must equal that in the Arrow vector: " + var3 + " != " + vectorScale);
      } else if (value.precision() > vectorPrecision) {
         int var10002 = value.precision();
         throw new UnsupportedOperationException("BigDecimal precision cannot be greater than that in the Arrow vector: " + var10002 + " > " + vectorPrecision);
      } else {
         return true;
      }
   }

   public static boolean checkPrecisionAndScaleNoThrow(BigDecimal value, int vectorPrecision, int vectorScale) {
      return value.scale() == vectorScale && value.precision() < vectorPrecision;
   }

   public static boolean checkPrecisionAndScale(int decimalPrecision, int decimalScale, int vectorPrecision, int vectorScale) {
      if (decimalScale != vectorScale) {
         throw new UnsupportedOperationException("BigDecimal scale must equal that in the Arrow vector: " + decimalScale + " != " + vectorScale);
      } else if (decimalPrecision > vectorPrecision) {
         throw new UnsupportedOperationException("BigDecimal precision cannot be greater than that in the Arrow vector: " + decimalPrecision + " > " + vectorPrecision);
      } else {
         return true;
      }
   }

   public static void writeBigDecimalToArrowBuf(BigDecimal value, ArrowBuf bytebuf, int index, int byteWidth) {
      byte[] bytes = value.unscaledValue().toByteArray();
      writeByteArrayToArrowBufHelper(bytes, bytebuf, index, byteWidth);
   }

   public static void writeLongToArrowBuf(long value, ArrowBuf bytebuf, int index, int byteWidth) {
      if (byteWidth != 16 && byteWidth != 32) {
         throw new UnsupportedOperationException("DecimalUtility.writeLongToArrowBuf() currently supports 128-bit or 256-bit width data");
      } else {
         long addressOfValue = bytebuf.memoryAddress() + (long)index * (long)byteWidth;
         long padValue = Long.signum(value) == -1 ? -1L : 0L;
         if (LITTLE_ENDIAN) {
            MemoryUtil.putLong(addressOfValue, value);

            for(int i = 1; i <= (byteWidth - 8) / 8; ++i) {
               MemoryUtil.putLong(addressOfValue + (long)(8 * i), padValue);
            }
         } else {
            for(int i = 0; i < (byteWidth - 8) / 8; ++i) {
               MemoryUtil.putLong(addressOfValue + (long)(8 * i), padValue);
            }

            MemoryUtil.putLong(addressOfValue + (long)(8 * (byteWidth - 8) / 8), value);
         }

      }
   }

   public static void writeByteArrayToArrowBuf(byte[] bytes, ArrowBuf bytebuf, int index, int byteWidth) {
      writeByteArrayToArrowBufHelper(bytes, bytebuf, index, byteWidth);
   }

   private static void writeByteArrayToArrowBufHelper(byte[] bytes, ArrowBuf bytebuf, int index, int byteWidth) {
      long startIndex = (long)index * (long)byteWidth;
      if (bytes.length > byteWidth) {
         throw new UnsupportedOperationException("Decimal size greater than " + byteWidth + " bytes: " + bytes.length);
      } else {
         byte[] padBytes = bytes[0] < 0 ? minus_one : zeroes;
         if (LITTLE_ENDIAN) {
            byte[] bytesLE = new byte[bytes.length];

            for(int i = 0; i < bytes.length; ++i) {
               bytesLE[i] = bytes[bytes.length - 1 - i];
            }

            bytebuf.setBytes(startIndex, bytesLE, 0, (long)bytes.length);
            bytebuf.setBytes(startIndex + (long)bytes.length, padBytes, 0, (long)(byteWidth - bytes.length));
         } else {
            bytebuf.setBytes(startIndex + (long)byteWidth - (long)bytes.length, bytes, 0, (long)bytes.length);
            bytebuf.setBytes(startIndex, padBytes, 0, (long)(byteWidth - bytes.length));
         }

      }
   }

   static {
      LITTLE_ENDIAN = ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN;
   }
}
