package org.apache.spark.types.variant;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.UUID;
import org.apache.spark.QueryContext;
import org.apache.spark.SparkRuntimeException;
import scala.collection.immutable.Map;
import scala.collection.immutable.Map.;

public class VariantUtil {
   public static final int BASIC_TYPE_BITS = 2;
   public static final int BASIC_TYPE_MASK = 3;
   public static final int TYPE_INFO_MASK = 63;
   public static final int MAX_SHORT_STR_SIZE = 63;
   public static final int PRIMITIVE = 0;
   public static final int SHORT_STR = 1;
   public static final int OBJECT = 2;
   public static final int ARRAY = 3;
   public static final int NULL = 0;
   public static final int TRUE = 1;
   public static final int FALSE = 2;
   public static final int INT1 = 3;
   public static final int INT2 = 4;
   public static final int INT4 = 5;
   public static final int INT8 = 6;
   public static final int DOUBLE = 7;
   public static final int DECIMAL4 = 8;
   public static final int DECIMAL8 = 9;
   public static final int DECIMAL16 = 10;
   public static final int DATE = 11;
   public static final int TIMESTAMP = 12;
   public static final int TIMESTAMP_NTZ = 13;
   public static final int FLOAT = 14;
   public static final int BINARY = 15;
   public static final int LONG_STR = 16;
   public static final int UUID = 20;
   public static final byte VERSION = 1;
   public static final byte VERSION_MASK = 15;
   public static final int U8_MAX = 255;
   public static final int U16_MAX = 65535;
   public static final int U24_MAX = 16777215;
   public static final int U24_SIZE = 3;
   public static final int U32_SIZE = 4;
   public static final int SIZE_LIMIT = 134217728;
   public static final int MAX_DECIMAL4_PRECISION = 9;
   public static final int MAX_DECIMAL8_PRECISION = 18;
   public static final int MAX_DECIMAL16_PRECISION = 38;

   public static void writeLong(byte[] bytes, int pos, long value, int numBytes) {
      for(int i = 0; i < numBytes; ++i) {
         bytes[pos + i] = (byte)((int)(value >>> 8 * i & 255L));
      }

   }

   public static byte primitiveHeader(int type) {
      return (byte)(type << 2 | 0);
   }

   public static byte shortStrHeader(int size) {
      return (byte)(size << 2 | 1);
   }

   public static byte objectHeader(boolean largeSize, int idSize, int offsetSize) {
      return (byte)((largeSize ? 1 : 0) << 6 | idSize - 1 << 4 | offsetSize - 1 << 2 | 2);
   }

   public static byte arrayHeader(boolean largeSize, int offsetSize) {
      return (byte)((largeSize ? 1 : 0) << 4 | offsetSize - 1 << 2 | 3);
   }

   static SparkRuntimeException malformedVariant() {
      return new SparkRuntimeException("MALFORMED_VARIANT", .MODULE$.empty(), (Throwable)null, new QueryContext[0], "");
   }

   static SparkRuntimeException unknownPrimitiveTypeInVariant(int id) {
      return new SparkRuntimeException("UNKNOWN_PRIMITIVE_TYPE_IN_VARIANT", new Map.Map1("id", Integer.toString(id)), (Throwable)null, new QueryContext[0], "");
   }

   static SparkRuntimeException variantConstructorSizeLimit() {
      return new SparkRuntimeException("VARIANT_CONSTRUCTOR_SIZE_LIMIT", .MODULE$.empty(), (Throwable)null, new QueryContext[0], "");
   }

   static void checkIndex(int pos, int length) {
      if (pos < 0 || pos >= length) {
         throw malformedVariant();
      }
   }

   static long readLong(byte[] bytes, int pos, int numBytes) {
      checkIndex(pos, bytes.length);
      checkIndex(pos + numBytes - 1, bytes.length);
      long result = 0L;

      for(int i = 0; i < numBytes - 1; ++i) {
         long unsignedByteValue = (long)(bytes[pos + i] & 255);
         result |= unsignedByteValue << 8 * i;
      }

      long signedByteValue = (long)bytes[pos + numBytes - 1];
      result |= signedByteValue << 8 * (numBytes - 1);
      return result;
   }

   static int readUnsigned(byte[] bytes, int pos, int numBytes) {
      checkIndex(pos, bytes.length);
      checkIndex(pos + numBytes - 1, bytes.length);
      int result = 0;

      for(int i = 0; i < numBytes; ++i) {
         int unsignedByteValue = bytes[pos + i] & 255;
         result |= unsignedByteValue << 8 * i;
      }

      if (result < 0) {
         throw malformedVariant();
      } else {
         return result;
      }
   }

   public static int getTypeInfo(byte[] value, int pos) {
      checkIndex(pos, value.length);
      return value[pos] >> 2 & 63;
   }

   public static Type getType(byte[] value, int pos) {
      checkIndex(pos, value.length);
      int basicType = value[pos] & 3;
      int typeInfo = value[pos] >> 2 & 63;
      switch (basicType) {
         case 1:
            return VariantUtil.Type.STRING;
         case 2:
            return VariantUtil.Type.OBJECT;
         case 3:
            return VariantUtil.Type.ARRAY;
         default:
            switch (typeInfo) {
               case 0:
                  return VariantUtil.Type.NULL;
               case 1:
               case 2:
                  return VariantUtil.Type.BOOLEAN;
               case 3:
               case 4:
               case 5:
               case 6:
                  return VariantUtil.Type.LONG;
               case 7:
                  return VariantUtil.Type.DOUBLE;
               case 8:
               case 9:
               case 10:
                  return VariantUtil.Type.DECIMAL;
               case 11:
                  return VariantUtil.Type.DATE;
               case 12:
                  return VariantUtil.Type.TIMESTAMP;
               case 13:
                  return VariantUtil.Type.TIMESTAMP_NTZ;
               case 14:
                  return VariantUtil.Type.FLOAT;
               case 15:
                  return VariantUtil.Type.BINARY;
               case 16:
                  return VariantUtil.Type.STRING;
               case 17:
               case 18:
               case 19:
               default:
                  throw unknownPrimitiveTypeInVariant(typeInfo);
               case 20:
                  return VariantUtil.Type.UUID;
            }
      }
   }

   public static int valueSize(byte[] value, int pos) {
      checkIndex(pos, value.length);
      int basicType = value[pos] & 3;
      int typeInfo = value[pos] >> 2 & 63;
      switch (basicType) {
         case 1:
            return 1 + typeInfo;
         case 2:
            return (Integer)handleObject(value, pos, (size, idSize, offsetSize, idStart, offsetStart, dataStart) -> dataStart - pos + readUnsigned(value, offsetStart + size * offsetSize, offsetSize));
         case 3:
            return (Integer)handleArray(value, pos, (size, offsetSize, offsetStart, dataStart) -> dataStart - pos + readUnsigned(value, offsetStart + size * offsetSize, offsetSize));
         default:
            switch (typeInfo) {
               case 0:
               case 1:
               case 2:
                  return 1;
               case 3:
                  return 2;
               case 4:
                  return 3;
               case 5:
               case 11:
               case 14:
                  return 5;
               case 6:
               case 7:
               case 12:
               case 13:
                  return 9;
               case 8:
                  return 6;
               case 9:
                  return 10;
               case 10:
                  return 18;
               case 15:
               case 16:
                  return 5 + readUnsigned(value, pos + 1, 4);
               case 17:
               case 18:
               case 19:
               default:
                  throw unknownPrimitiveTypeInVariant(typeInfo);
               case 20:
                  return 17;
            }
      }
   }

   static IllegalStateException unexpectedType(Type type) {
      return new IllegalStateException("Expect type to be " + String.valueOf(type));
   }

   public static boolean getBoolean(byte[] value, int pos) {
      checkIndex(pos, value.length);
      int basicType = value[pos] & 3;
      int typeInfo = value[pos] >> 2 & 63;
      if (basicType == 0 && (typeInfo == 1 || typeInfo == 2)) {
         return typeInfo == 1;
      } else {
         throw unexpectedType(VariantUtil.Type.BOOLEAN);
      }
   }

   public static long getLong(byte[] value, int pos) {
      checkIndex(pos, value.length);
      int basicType = value[pos] & 3;
      int typeInfo = value[pos] >> 2 & 63;
      String exceptionMessage = "Expect type to be LONG/DATE/TIMESTAMP/TIMESTAMP_NTZ";
      if (basicType != 0) {
         throw new IllegalStateException(exceptionMessage);
      } else {
         switch (typeInfo) {
            case 3:
               return readLong(value, pos + 1, 1);
            case 4:
               return readLong(value, pos + 1, 2);
            case 5:
            case 11:
               return readLong(value, pos + 1, 4);
            case 6:
            case 12:
            case 13:
               return readLong(value, pos + 1, 8);
            case 7:
            case 8:
            case 9:
            case 10:
            default:
               throw new IllegalStateException(exceptionMessage);
         }
      }
   }

   public static double getDouble(byte[] value, int pos) {
      checkIndex(pos, value.length);
      int basicType = value[pos] & 3;
      int typeInfo = value[pos] >> 2 & 63;
      if (basicType == 0 && typeInfo == 7) {
         return Double.longBitsToDouble(readLong(value, pos + 1, 8));
      } else {
         throw unexpectedType(VariantUtil.Type.DOUBLE);
      }
   }

   private static void checkDecimal(BigDecimal d, int maxPrecision) {
      if (d.precision() > maxPrecision || d.scale() > maxPrecision) {
         throw malformedVariant();
      }
   }

   public static BigDecimal getDecimalWithOriginalScale(byte[] value, int pos) {
      checkIndex(pos, value.length);
      int basicType = value[pos] & 3;
      int typeInfo = value[pos] >> 2 & 63;
      if (basicType != 0) {
         throw unexpectedType(VariantUtil.Type.DECIMAL);
      } else {
         int scale = value[pos + 1] & 255;
         BigDecimal result;
         switch (typeInfo) {
            case 8:
               result = BigDecimal.valueOf(readLong(value, pos + 2, 4), scale);
               checkDecimal(result, 9);
               break;
            case 9:
               result = BigDecimal.valueOf(readLong(value, pos + 2, 8), scale);
               checkDecimal(result, 18);
               break;
            case 10:
               checkIndex(pos + 17, value.length);
               byte[] bytes = new byte[16];

               for(int i = 0; i < 16; ++i) {
                  bytes[i] = value[pos + 17 - i];
               }

               result = new BigDecimal(new BigInteger(bytes), scale);
               checkDecimal(result, 38);
               break;
            default:
               throw unexpectedType(VariantUtil.Type.DECIMAL);
         }

         return result;
      }
   }

   public static BigDecimal getDecimal(byte[] value, int pos) {
      return getDecimalWithOriginalScale(value, pos).stripTrailingZeros();
   }

   public static float getFloat(byte[] value, int pos) {
      checkIndex(pos, value.length);
      int basicType = value[pos] & 3;
      int typeInfo = value[pos] >> 2 & 63;
      if (basicType == 0 && typeInfo == 14) {
         return Float.intBitsToFloat((int)readLong(value, pos + 1, 4));
      } else {
         throw unexpectedType(VariantUtil.Type.FLOAT);
      }
   }

   public static byte[] getBinary(byte[] value, int pos) {
      checkIndex(pos, value.length);
      int basicType = value[pos] & 3;
      int typeInfo = value[pos] >> 2 & 63;
      if (basicType == 0 && typeInfo == 15) {
         int start = pos + 1 + 4;
         int length = readUnsigned(value, pos + 1, 4);
         checkIndex(start + length - 1, value.length);
         return Arrays.copyOfRange(value, start, start + length);
      } else {
         throw unexpectedType(VariantUtil.Type.BINARY);
      }
   }

   public static String getString(byte[] value, int pos) {
      checkIndex(pos, value.length);
      int basicType = value[pos] & 3;
      int typeInfo = value[pos] >> 2 & 63;
      if (basicType == 1 || basicType == 0 && typeInfo == 16) {
         int start;
         int length;
         if (basicType == 1) {
            start = pos + 1;
            length = typeInfo;
         } else {
            start = pos + 1 + 4;
            length = readUnsigned(value, pos + 1, 4);
         }

         checkIndex(start + length - 1, value.length);
         return new String(value, start, length);
      } else {
         throw unexpectedType(VariantUtil.Type.STRING);
      }
   }

   public static UUID getUuid(byte[] value, int pos) {
      checkIndex(pos, value.length);
      int basicType = value[pos] & 3;
      int typeInfo = value[pos] >> 2 & 63;
      if (basicType == 0 && typeInfo == 20) {
         int start = pos + 1;
         checkIndex(start + 15, value.length);
         ByteBuffer bb = ByteBuffer.wrap(value, start, 16).order(ByteOrder.BIG_ENDIAN);
         return new UUID(bb.getLong(), bb.getLong());
      } else {
         throw unexpectedType(VariantUtil.Type.UUID);
      }
   }

   public static Object handleObject(byte[] value, int pos, ObjectHandler handler) {
      checkIndex(pos, value.length);
      int basicType = value[pos] & 3;
      int typeInfo = value[pos] >> 2 & 63;
      if (basicType != 2) {
         throw unexpectedType(VariantUtil.Type.OBJECT);
      } else {
         boolean largeSize = (typeInfo >> 4 & 1) != 0;
         int sizeBytes = largeSize ? 4 : 1;
         int size = readUnsigned(value, pos + 1, sizeBytes);
         int idSize = (typeInfo >> 2 & 3) + 1;
         int offsetSize = (typeInfo & 3) + 1;
         int idStart = pos + 1 + sizeBytes;
         int offsetStart = idStart + size * idSize;
         int dataStart = offsetStart + (size + 1) * offsetSize;
         return handler.apply(size, idSize, offsetSize, idStart, offsetStart, dataStart);
      }
   }

   public static Object handleArray(byte[] value, int pos, ArrayHandler handler) {
      checkIndex(pos, value.length);
      int basicType = value[pos] & 3;
      int typeInfo = value[pos] >> 2 & 63;
      if (basicType != 3) {
         throw unexpectedType(VariantUtil.Type.ARRAY);
      } else {
         boolean largeSize = (typeInfo >> 2 & 1) != 0;
         int sizeBytes = largeSize ? 4 : 1;
         int size = readUnsigned(value, pos + 1, sizeBytes);
         int offsetSize = (typeInfo & 3) + 1;
         int offsetStart = pos + 1 + sizeBytes;
         int dataStart = offsetStart + (size + 1) * offsetSize;
         return handler.apply(size, offsetSize, offsetStart, dataStart);
      }
   }

   public static String getMetadataKey(byte[] metadata, int id) {
      checkIndex(0, metadata.length);
      int offsetSize = (metadata[0] >> 6 & 3) + 1;
      int dictSize = readUnsigned(metadata, 1, offsetSize);
      if (id >= dictSize) {
         throw malformedVariant();
      } else {
         int stringStart = 1 + (dictSize + 2) * offsetSize;
         int offset = readUnsigned(metadata, 1 + (id + 1) * offsetSize, offsetSize);
         int nextOffset = readUnsigned(metadata, 1 + (id + 2) * offsetSize, offsetSize);
         if (offset > nextOffset) {
            throw malformedVariant();
         } else {
            checkIndex(stringStart + nextOffset - 1, metadata.length);
            return new String(metadata, stringStart + offset, nextOffset - offset);
         }
      }
   }

   public static enum Type {
      OBJECT,
      ARRAY,
      NULL,
      BOOLEAN,
      LONG,
      STRING,
      DOUBLE,
      DECIMAL,
      DATE,
      TIMESTAMP,
      TIMESTAMP_NTZ,
      FLOAT,
      BINARY,
      UUID;

      // $FF: synthetic method
      private static Type[] $values() {
         return new Type[]{OBJECT, ARRAY, NULL, BOOLEAN, LONG, STRING, DOUBLE, DECIMAL, DATE, TIMESTAMP, TIMESTAMP_NTZ, FLOAT, BINARY, UUID};
      }
   }

   public interface ArrayHandler {
      Object apply(int var1, int var2, int var3, int var4);
   }

   public interface ObjectHandler {
      Object apply(int var1, int var2, int var3, int var4, int var5, int var6);
   }
}
