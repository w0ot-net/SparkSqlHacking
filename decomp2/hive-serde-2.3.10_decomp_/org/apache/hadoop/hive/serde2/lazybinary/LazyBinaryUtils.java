package org.apache.hadoop.hive.serde2.lazybinary;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.hadoop.hive.serde2.ByteStream;
import org.apache.hadoop.hive.serde2.io.TimestampWritable;
import org.apache.hadoop.hive.serde2.lazybinary.objectinspector.LazyBinaryObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.typeinfo.ListTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.MapTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.PrimitiveTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.StructTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.UnionTypeInfo;
import org.apache.hadoop.io.WritableUtils;

public final class LazyBinaryUtils {
   public static final ThreadLocal threadLocalVInt = new ThreadLocal() {
      protected VInt initialValue() {
         return new VInt();
      }
   };
   public static final int VLONG_BYTES_LEN = 9;
   private static ThreadLocal vLongBytesThreadLocal = new ThreadLocal() {
      public byte[] initialValue() {
         return new byte[9];
      }
   };
   static ConcurrentHashMap cachedLazyBinaryObjectInspector = new ConcurrentHashMap();

   public static int byteArrayToInt(byte[] b, int offset) {
      int value = 0;

      for(int i = 0; i < 4; ++i) {
         int shift = (3 - i) * 8;
         value += (b[i + offset] & 255) << shift;
      }

      return value;
   }

   public static long byteArrayToLong(byte[] b, int offset) {
      long value = 0L;

      for(int i = 0; i < 8; ++i) {
         int shift = (7 - i) * 8;
         value += (long)(b[i + offset] & 255) << shift;
      }

      return value;
   }

   public static short byteArrayToShort(byte[] b, int offset) {
      short value = 0;
      value = (short)(value + ((b[offset] & 255) << 8));
      value = (short)(value + (b[offset + 1] & 255));
      return value;
   }

   public static void checkObjectByteInfo(ObjectInspector objectInspector, byte[] bytes, int offset, RecordInfo recordInfo, VInt vInt) {
      ObjectInspector.Category category = objectInspector.getCategory();
      switch (category) {
         case PRIMITIVE:
            PrimitiveObjectInspector.PrimitiveCategory primitiveCategory = ((PrimitiveObjectInspector)objectInspector).getPrimitiveCategory();
            switch (primitiveCategory) {
               case VOID:
                  recordInfo.elementOffset = 0;
                  recordInfo.elementSize = 0;
                  return;
               case BOOLEAN:
               case BYTE:
                  recordInfo.elementOffset = 0;
                  recordInfo.elementSize = 1;
                  return;
               case SHORT:
                  recordInfo.elementOffset = 0;
                  recordInfo.elementSize = 2;
                  return;
               case FLOAT:
                  recordInfo.elementOffset = 0;
                  recordInfo.elementSize = 4;
                  return;
               case DOUBLE:
                  recordInfo.elementOffset = 0;
                  recordInfo.elementSize = 8;
                  return;
               case INT:
                  recordInfo.elementOffset = 0;
                  recordInfo.elementSize = WritableUtils.decodeVIntSize(bytes[offset]);
                  return;
               case LONG:
                  recordInfo.elementOffset = 0;
                  recordInfo.elementSize = WritableUtils.decodeVIntSize(bytes[offset]);
                  return;
               case STRING:
                  readVInt(bytes, offset, vInt);
                  recordInfo.elementOffset = vInt.length;
                  recordInfo.elementSize = vInt.value;
                  return;
               case CHAR:
               case VARCHAR:
                  readVInt(bytes, offset, vInt);
                  recordInfo.elementOffset = vInt.length;
                  recordInfo.elementSize = vInt.value;
                  return;
               case BINARY:
                  readVInt(bytes, offset, vInt);
                  recordInfo.elementOffset = vInt.length;
                  recordInfo.elementSize = vInt.value;
                  return;
               case DATE:
                  recordInfo.elementOffset = 0;
                  recordInfo.elementSize = WritableUtils.decodeVIntSize(bytes[offset]);
                  return;
               case TIMESTAMP:
                  recordInfo.elementOffset = 0;
                  recordInfo.elementSize = TimestampWritable.getTotalLength(bytes, offset);
                  return;
               case INTERVAL_YEAR_MONTH:
                  recordInfo.elementOffset = 0;
                  recordInfo.elementSize = WritableUtils.decodeVIntSize(bytes[offset]);
                  return;
               case INTERVAL_DAY_TIME:
                  recordInfo.elementOffset = 0;
                  int secondsSize = WritableUtils.decodeVIntSize(bytes[offset]);
                  int nanosSize = WritableUtils.decodeVIntSize(bytes[offset + secondsSize]);
                  recordInfo.elementSize = secondsSize + nanosSize;
                  return;
               case DECIMAL:
                  readVInt(bytes, offset, vInt);
                  recordInfo.elementOffset = 0;
                  recordInfo.elementSize = vInt.length;
                  readVInt(bytes, offset + vInt.length, vInt);
                  recordInfo.elementSize += vInt.length + vInt.value;
                  return;
               default:
                  throw new RuntimeException("Unrecognized primitive type: " + primitiveCategory);
            }
         case LIST:
         case MAP:
         case STRUCT:
         case UNION:
            recordInfo.elementOffset = 4;
            recordInfo.elementSize = byteArrayToInt(bytes, offset);
            return;
         default:
            throw new RuntimeException("Unrecognized non-primitive type: " + category);
      }
   }

   public static void readVLong(byte[] bytes, int offset, VLong vlong) {
      byte firstByte = bytes[offset];
      vlong.length = (byte)WritableUtils.decodeVIntSize(firstByte);
      if (vlong.length == 1) {
         vlong.value = (long)firstByte;
      } else {
         long i = 0L;

         for(int idx = 0; idx < vlong.length - 1; ++idx) {
            byte b = bytes[offset + 1 + idx];
            i <<= 8;
            i |= (long)(b & 255);
         }

         vlong.value = WritableUtils.isNegativeVInt(firstByte) ? ~i : i;
      }
   }

   public static void readVInt(byte[] bytes, int offset, VInt vInt) {
      byte firstByte = bytes[offset];
      vInt.length = (byte)WritableUtils.decodeVIntSize(firstByte);
      if (vInt.length == 1) {
         vInt.value = firstByte;
      } else {
         int i = 0;

         for(int idx = 0; idx < vInt.length - 1; ++idx) {
            byte b = bytes[offset + 1 + idx];
            i <<= 8;
            i |= b & 255;
         }

         vInt.value = WritableUtils.isNegativeVInt(firstByte) ? ~i : i;
      }
   }

   public static void writeVInt(ByteStream.RandomAccessOutput byteStream, int i) {
      writeVLong(byteStream, (long)i);
   }

   public static long readVLongFromByteArray(byte[] bytes, int offset) {
      byte firstByte = bytes[offset++];
      int len = WritableUtils.decodeVIntSize(firstByte);
      if (len == 1) {
         return (long)firstByte;
      } else {
         long i = 0L;

         for(int idx = 0; idx < len - 1; ++idx) {
            byte b = bytes[offset++];
            i <<= 8;
            i |= (long)(b & 255);
         }

         return WritableUtils.isNegativeVInt(firstByte) ? ~i : i;
      }
   }

   public static int writeVLongToByteArray(byte[] bytes, long l) {
      return writeVLongToByteArray(bytes, 0, l);
   }

   public static int writeVLongToByteArray(byte[] bytes, int offset, long l) {
      if (l >= -112L && l <= 127L) {
         bytes[offset] = (byte)((int)l);
         return 1;
      } else {
         int len = -112;
         if (l < 0L) {
            l = ~l;
            len = -120;
         }

         for(long tmp = l; tmp != 0L; --len) {
            tmp >>= 8;
         }

         bytes[offset] = (byte)len;
         len = len < -120 ? -(len + 120) : -(len + 112);

         for(int idx = len; idx != 0; --idx) {
            int shiftbits = (idx - 1) * 8;
            long mask = 255L << shiftbits;
            bytes[offset + 1 - (idx - len)] = (byte)((int)((l & mask) >> shiftbits));
         }

         return 1 + len;
      }
   }

   public static void writeVLong(ByteStream.RandomAccessOutput byteStream, long l) {
      byte[] vLongBytes = (byte[])vLongBytesThreadLocal.get();
      int len = writeVLongToByteArray(vLongBytes, l);
      byteStream.write(vLongBytes, 0, len);
   }

   public static void writeDouble(ByteStream.RandomAccessOutput byteStream, double d) {
      long v = Double.doubleToLongBits(d);
      byteStream.write((byte)((int)(v >> 56)));
      byteStream.write((byte)((int)(v >> 48)));
      byteStream.write((byte)((int)(v >> 40)));
      byteStream.write((byte)((int)(v >> 32)));
      byteStream.write((byte)((int)(v >> 24)));
      byteStream.write((byte)((int)(v >> 16)));
      byteStream.write((byte)((int)(v >> 8)));
      byteStream.write((byte)((int)v));
   }

   public static ObjectInspector getLazyBinaryObjectInspectorFromTypeInfo(TypeInfo typeInfo) {
      ObjectInspector result = (ObjectInspector)cachedLazyBinaryObjectInspector.get(typeInfo);
      if (result == null) {
         switch (typeInfo.getCategory()) {
            case PRIMITIVE:
               result = PrimitiveObjectInspectorFactory.getPrimitiveWritableObjectInspector((PrimitiveTypeInfo)typeInfo);
               break;
            case LIST:
               ObjectInspector elementObjectInspector = getLazyBinaryObjectInspectorFromTypeInfo(((ListTypeInfo)typeInfo).getListElementTypeInfo());
               result = LazyBinaryObjectInspectorFactory.getLazyBinaryListObjectInspector(elementObjectInspector);
               break;
            case MAP:
               MapTypeInfo mapTypeInfo = (MapTypeInfo)typeInfo;
               ObjectInspector keyObjectInspector = getLazyBinaryObjectInspectorFromTypeInfo(mapTypeInfo.getMapKeyTypeInfo());
               ObjectInspector valueObjectInspector = getLazyBinaryObjectInspectorFromTypeInfo(mapTypeInfo.getMapValueTypeInfo());
               result = LazyBinaryObjectInspectorFactory.getLazyBinaryMapObjectInspector(keyObjectInspector, valueObjectInspector);
               break;
            case STRUCT:
               StructTypeInfo structTypeInfo = (StructTypeInfo)typeInfo;
               List<String> fieldNames = structTypeInfo.getAllStructFieldNames();
               List<TypeInfo> fieldTypeInfos = structTypeInfo.getAllStructFieldTypeInfos();
               List<ObjectInspector> fieldObjectInspectors = new ArrayList(fieldTypeInfos.size());

               for(int i = 0; i < fieldTypeInfos.size(); ++i) {
                  fieldObjectInspectors.add(getLazyBinaryObjectInspectorFromTypeInfo((TypeInfo)fieldTypeInfos.get(i)));
               }

               result = LazyBinaryObjectInspectorFactory.getLazyBinaryStructObjectInspector(fieldNames, fieldObjectInspectors);
               break;
            case UNION:
               UnionTypeInfo unionTypeInfo = (UnionTypeInfo)typeInfo;
               List<TypeInfo> fieldTypeInfos = unionTypeInfo.getAllUnionObjectTypeInfos();
               List<ObjectInspector> fieldObjectInspectors = new ArrayList(fieldTypeInfos.size());

               for(int i = 0; i < fieldTypeInfos.size(); ++i) {
                  fieldObjectInspectors.add(getLazyBinaryObjectInspectorFromTypeInfo((TypeInfo)fieldTypeInfos.get(i)));
               }

               result = LazyBinaryObjectInspectorFactory.getLazyBinaryUnionObjectInspector(fieldObjectInspectors);
               break;
            default:
               result = null;
         }

         ObjectInspector prev = (ObjectInspector)cachedLazyBinaryObjectInspector.putIfAbsent(typeInfo, result);
         if (prev != null) {
            result = prev;
         }
      }

      return result;
   }

   private LazyBinaryUtils() {
   }

   public static class RecordInfo {
      public byte elementOffset = 0;
      public int elementSize = 0;

      public String toString() {
         return "(" + this.elementOffset + ", " + this.elementSize + ")";
      }
   }

   public static class VLong {
      public long value = 0L;
      public byte length = 0;
   }

   public static class VInt {
      public int value = 0;
      public byte length = 0;
   }
}
