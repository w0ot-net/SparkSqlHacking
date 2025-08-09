package org.apache.orc.protobuf;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

@CheckReturnValue
final class SchemaUtil {
   private static final Class GENERATED_MESSAGE_CLASS = getGeneratedMessageClass();
   private static final UnknownFieldSchema UNKNOWN_FIELD_SET_FULL_SCHEMA = getUnknownFieldSetSchema();
   private static final UnknownFieldSchema UNKNOWN_FIELD_SET_LITE_SCHEMA = new UnknownFieldSetLiteSchema();
   private static final int DEFAULT_LOOK_UP_START_NUMBER = 40;

   private SchemaUtil() {
   }

   public static void requireGeneratedMessage(Class messageType) {
      if (!GeneratedMessageLite.class.isAssignableFrom(messageType) && GENERATED_MESSAGE_CLASS != null && !GENERATED_MESSAGE_CLASS.isAssignableFrom(messageType)) {
         throw new IllegalArgumentException("Message classes must extend GeneratedMessageV3 or GeneratedMessageLite");
      }
   }

   public static void writeDouble(int fieldNumber, double value, Writer writer) throws IOException {
      if (Double.doubleToRawLongBits(value) != 0L) {
         writer.writeDouble(fieldNumber, value);
      }

   }

   public static void writeFloat(int fieldNumber, float value, Writer writer) throws IOException {
      if (Float.floatToRawIntBits(value) != 0) {
         writer.writeFloat(fieldNumber, value);
      }

   }

   public static void writeInt64(int fieldNumber, long value, Writer writer) throws IOException {
      if (value != 0L) {
         writer.writeInt64(fieldNumber, value);
      }

   }

   public static void writeUInt64(int fieldNumber, long value, Writer writer) throws IOException {
      if (value != 0L) {
         writer.writeUInt64(fieldNumber, value);
      }

   }

   public static void writeSInt64(int fieldNumber, long value, Writer writer) throws IOException {
      if (value != 0L) {
         writer.writeSInt64(fieldNumber, value);
      }

   }

   public static void writeFixed64(int fieldNumber, long value, Writer writer) throws IOException {
      if (value != 0L) {
         writer.writeFixed64(fieldNumber, value);
      }

   }

   public static void writeSFixed64(int fieldNumber, long value, Writer writer) throws IOException {
      if (value != 0L) {
         writer.writeSFixed64(fieldNumber, value);
      }

   }

   public static void writeInt32(int fieldNumber, int value, Writer writer) throws IOException {
      if (value != 0) {
         writer.writeInt32(fieldNumber, value);
      }

   }

   public static void writeUInt32(int fieldNumber, int value, Writer writer) throws IOException {
      if (value != 0) {
         writer.writeUInt32(fieldNumber, value);
      }

   }

   public static void writeSInt32(int fieldNumber, int value, Writer writer) throws IOException {
      if (value != 0) {
         writer.writeSInt32(fieldNumber, value);
      }

   }

   public static void writeFixed32(int fieldNumber, int value, Writer writer) throws IOException {
      if (value != 0) {
         writer.writeFixed32(fieldNumber, value);
      }

   }

   public static void writeSFixed32(int fieldNumber, int value, Writer writer) throws IOException {
      if (value != 0) {
         writer.writeSFixed32(fieldNumber, value);
      }

   }

   public static void writeEnum(int fieldNumber, int value, Writer writer) throws IOException {
      if (value != 0) {
         writer.writeEnum(fieldNumber, value);
      }

   }

   public static void writeBool(int fieldNumber, boolean value, Writer writer) throws IOException {
      if (value) {
         writer.writeBool(fieldNumber, true);
      }

   }

   public static void writeString(int fieldNumber, Object value, Writer writer) throws IOException {
      if (value instanceof String) {
         writeStringInternal(fieldNumber, (String)value, writer);
      } else {
         writeBytes(fieldNumber, (ByteString)value, writer);
      }

   }

   private static void writeStringInternal(int fieldNumber, String value, Writer writer) throws IOException {
      if (value != null && !value.isEmpty()) {
         writer.writeString(fieldNumber, value);
      }

   }

   public static void writeBytes(int fieldNumber, ByteString value, Writer writer) throws IOException {
      if (value != null && !value.isEmpty()) {
         writer.writeBytes(fieldNumber, value);
      }

   }

   public static void writeMessage(int fieldNumber, Object value, Writer writer) throws IOException {
      if (value != null) {
         writer.writeMessage(fieldNumber, value);
      }

   }

   public static void writeDoubleList(int fieldNumber, List value, Writer writer, boolean packed) throws IOException {
      if (value != null && !value.isEmpty()) {
         writer.writeDoubleList(fieldNumber, value, packed);
      }

   }

   public static void writeFloatList(int fieldNumber, List value, Writer writer, boolean packed) throws IOException {
      if (value != null && !value.isEmpty()) {
         writer.writeFloatList(fieldNumber, value, packed);
      }

   }

   public static void writeInt64List(int fieldNumber, List value, Writer writer, boolean packed) throws IOException {
      if (value != null && !value.isEmpty()) {
         writer.writeInt64List(fieldNumber, value, packed);
      }

   }

   public static void writeUInt64List(int fieldNumber, List value, Writer writer, boolean packed) throws IOException {
      if (value != null && !value.isEmpty()) {
         writer.writeUInt64List(fieldNumber, value, packed);
      }

   }

   public static void writeSInt64List(int fieldNumber, List value, Writer writer, boolean packed) throws IOException {
      if (value != null && !value.isEmpty()) {
         writer.writeSInt64List(fieldNumber, value, packed);
      }

   }

   public static void writeFixed64List(int fieldNumber, List value, Writer writer, boolean packed) throws IOException {
      if (value != null && !value.isEmpty()) {
         writer.writeFixed64List(fieldNumber, value, packed);
      }

   }

   public static void writeSFixed64List(int fieldNumber, List value, Writer writer, boolean packed) throws IOException {
      if (value != null && !value.isEmpty()) {
         writer.writeSFixed64List(fieldNumber, value, packed);
      }

   }

   public static void writeInt32List(int fieldNumber, List value, Writer writer, boolean packed) throws IOException {
      if (value != null && !value.isEmpty()) {
         writer.writeInt32List(fieldNumber, value, packed);
      }

   }

   public static void writeUInt32List(int fieldNumber, List value, Writer writer, boolean packed) throws IOException {
      if (value != null && !value.isEmpty()) {
         writer.writeUInt32List(fieldNumber, value, packed);
      }

   }

   public static void writeSInt32List(int fieldNumber, List value, Writer writer, boolean packed) throws IOException {
      if (value != null && !value.isEmpty()) {
         writer.writeSInt32List(fieldNumber, value, packed);
      }

   }

   public static void writeFixed32List(int fieldNumber, List value, Writer writer, boolean packed) throws IOException {
      if (value != null && !value.isEmpty()) {
         writer.writeFixed32List(fieldNumber, value, packed);
      }

   }

   public static void writeSFixed32List(int fieldNumber, List value, Writer writer, boolean packed) throws IOException {
      if (value != null && !value.isEmpty()) {
         writer.writeSFixed32List(fieldNumber, value, packed);
      }

   }

   public static void writeEnumList(int fieldNumber, List value, Writer writer, boolean packed) throws IOException {
      if (value != null && !value.isEmpty()) {
         writer.writeEnumList(fieldNumber, value, packed);
      }

   }

   public static void writeBoolList(int fieldNumber, List value, Writer writer, boolean packed) throws IOException {
      if (value != null && !value.isEmpty()) {
         writer.writeBoolList(fieldNumber, value, packed);
      }

   }

   public static void writeStringList(int fieldNumber, List value, Writer writer) throws IOException {
      if (value != null && !value.isEmpty()) {
         writer.writeStringList(fieldNumber, value);
      }

   }

   public static void writeBytesList(int fieldNumber, List value, Writer writer) throws IOException {
      if (value != null && !value.isEmpty()) {
         writer.writeBytesList(fieldNumber, value);
      }

   }

   public static void writeMessageList(int fieldNumber, List value, Writer writer) throws IOException {
      if (value != null && !value.isEmpty()) {
         writer.writeMessageList(fieldNumber, value);
      }

   }

   public static void writeMessageList(int fieldNumber, List value, Writer writer, Schema schema) throws IOException {
      if (value != null && !value.isEmpty()) {
         writer.writeMessageList(fieldNumber, value, schema);
      }

   }

   public static void writeLazyFieldList(int fieldNumber, List value, Writer writer) throws IOException {
      if (value != null && !value.isEmpty()) {
         for(Object item : value) {
            ((LazyFieldLite)item).writeTo(writer, fieldNumber);
         }
      }

   }

   public static void writeGroupList(int fieldNumber, List value, Writer writer) throws IOException {
      if (value != null && !value.isEmpty()) {
         writer.writeGroupList(fieldNumber, value);
      }

   }

   public static void writeGroupList(int fieldNumber, List value, Writer writer, Schema schema) throws IOException {
      if (value != null && !value.isEmpty()) {
         writer.writeGroupList(fieldNumber, value, schema);
      }

   }

   static int computeSizeInt64ListNoTag(List list) {
      int length = list.size();
      if (length == 0) {
         return 0;
      } else {
         int size = 0;
         if (list instanceof LongArrayList) {
            LongArrayList primitiveList = (LongArrayList)list;

            for(int i = 0; i < length; ++i) {
               size += CodedOutputStream.computeInt64SizeNoTag(primitiveList.getLong(i));
            }
         } else {
            for(int i = 0; i < length; ++i) {
               size += CodedOutputStream.computeInt64SizeNoTag((Long)list.get(i));
            }
         }

         return size;
      }
   }

   static int computeSizeInt64List(int fieldNumber, List list, boolean packed) {
      int length = list.size();
      if (length == 0) {
         return 0;
      } else {
         int size = computeSizeInt64ListNoTag(list);
         return packed ? CodedOutputStream.computeTagSize(fieldNumber) + CodedOutputStream.computeLengthDelimitedFieldSize(size) : size + list.size() * CodedOutputStream.computeTagSize(fieldNumber);
      }
   }

   static int computeSizeUInt64ListNoTag(List list) {
      int length = list.size();
      if (length == 0) {
         return 0;
      } else {
         int size = 0;
         if (list instanceof LongArrayList) {
            LongArrayList primitiveList = (LongArrayList)list;

            for(int i = 0; i < length; ++i) {
               size += CodedOutputStream.computeUInt64SizeNoTag(primitiveList.getLong(i));
            }
         } else {
            for(int i = 0; i < length; ++i) {
               size += CodedOutputStream.computeUInt64SizeNoTag((Long)list.get(i));
            }
         }

         return size;
      }
   }

   static int computeSizeUInt64List(int fieldNumber, List list, boolean packed) {
      int length = list.size();
      if (length == 0) {
         return 0;
      } else {
         int size = computeSizeUInt64ListNoTag(list);
         return packed ? CodedOutputStream.computeTagSize(fieldNumber) + CodedOutputStream.computeLengthDelimitedFieldSize(size) : size + length * CodedOutputStream.computeTagSize(fieldNumber);
      }
   }

   static int computeSizeSInt64ListNoTag(List list) {
      int length = list.size();
      if (length == 0) {
         return 0;
      } else {
         int size = 0;
         if (list instanceof LongArrayList) {
            LongArrayList primitiveList = (LongArrayList)list;

            for(int i = 0; i < length; ++i) {
               size += CodedOutputStream.computeSInt64SizeNoTag(primitiveList.getLong(i));
            }
         } else {
            for(int i = 0; i < length; ++i) {
               size += CodedOutputStream.computeSInt64SizeNoTag((Long)list.get(i));
            }
         }

         return size;
      }
   }

   static int computeSizeSInt64List(int fieldNumber, List list, boolean packed) {
      int length = list.size();
      if (length == 0) {
         return 0;
      } else {
         int size = computeSizeSInt64ListNoTag(list);
         return packed ? CodedOutputStream.computeTagSize(fieldNumber) + CodedOutputStream.computeLengthDelimitedFieldSize(size) : size + length * CodedOutputStream.computeTagSize(fieldNumber);
      }
   }

   static int computeSizeEnumListNoTag(List list) {
      int length = list.size();
      if (length == 0) {
         return 0;
      } else {
         int size = 0;
         if (list instanceof IntArrayList) {
            IntArrayList primitiveList = (IntArrayList)list;

            for(int i = 0; i < length; ++i) {
               size += CodedOutputStream.computeEnumSizeNoTag(primitiveList.getInt(i));
            }
         } else {
            for(int i = 0; i < length; ++i) {
               size += CodedOutputStream.computeEnumSizeNoTag((Integer)list.get(i));
            }
         }

         return size;
      }
   }

   static int computeSizeEnumList(int fieldNumber, List list, boolean packed) {
      int length = list.size();
      if (length == 0) {
         return 0;
      } else {
         int size = computeSizeEnumListNoTag(list);
         return packed ? CodedOutputStream.computeTagSize(fieldNumber) + CodedOutputStream.computeLengthDelimitedFieldSize(size) : size + length * CodedOutputStream.computeTagSize(fieldNumber);
      }
   }

   static int computeSizeInt32ListNoTag(List list) {
      int length = list.size();
      if (length == 0) {
         return 0;
      } else {
         int size = 0;
         if (list instanceof IntArrayList) {
            IntArrayList primitiveList = (IntArrayList)list;

            for(int i = 0; i < length; ++i) {
               size += CodedOutputStream.computeInt32SizeNoTag(primitiveList.getInt(i));
            }
         } else {
            for(int i = 0; i < length; ++i) {
               size += CodedOutputStream.computeInt32SizeNoTag((Integer)list.get(i));
            }
         }

         return size;
      }
   }

   static int computeSizeInt32List(int fieldNumber, List list, boolean packed) {
      int length = list.size();
      if (length == 0) {
         return 0;
      } else {
         int size = computeSizeInt32ListNoTag(list);
         return packed ? CodedOutputStream.computeTagSize(fieldNumber) + CodedOutputStream.computeLengthDelimitedFieldSize(size) : size + length * CodedOutputStream.computeTagSize(fieldNumber);
      }
   }

   static int computeSizeUInt32ListNoTag(List list) {
      int length = list.size();
      if (length == 0) {
         return 0;
      } else {
         int size = 0;
         if (list instanceof IntArrayList) {
            IntArrayList primitiveList = (IntArrayList)list;

            for(int i = 0; i < length; ++i) {
               size += CodedOutputStream.computeUInt32SizeNoTag(primitiveList.getInt(i));
            }
         } else {
            for(int i = 0; i < length; ++i) {
               size += CodedOutputStream.computeUInt32SizeNoTag((Integer)list.get(i));
            }
         }

         return size;
      }
   }

   static int computeSizeUInt32List(int fieldNumber, List list, boolean packed) {
      int length = list.size();
      if (length == 0) {
         return 0;
      } else {
         int size = computeSizeUInt32ListNoTag(list);
         return packed ? CodedOutputStream.computeTagSize(fieldNumber) + CodedOutputStream.computeLengthDelimitedFieldSize(size) : size + length * CodedOutputStream.computeTagSize(fieldNumber);
      }
   }

   static int computeSizeSInt32ListNoTag(List list) {
      int length = list.size();
      if (length == 0) {
         return 0;
      } else {
         int size = 0;
         if (list instanceof IntArrayList) {
            IntArrayList primitiveList = (IntArrayList)list;

            for(int i = 0; i < length; ++i) {
               size += CodedOutputStream.computeSInt32SizeNoTag(primitiveList.getInt(i));
            }
         } else {
            for(int i = 0; i < length; ++i) {
               size += CodedOutputStream.computeSInt32SizeNoTag((Integer)list.get(i));
            }
         }

         return size;
      }
   }

   static int computeSizeSInt32List(int fieldNumber, List list, boolean packed) {
      int length = list.size();
      if (length == 0) {
         return 0;
      } else {
         int size = computeSizeSInt32ListNoTag(list);
         return packed ? CodedOutputStream.computeTagSize(fieldNumber) + CodedOutputStream.computeLengthDelimitedFieldSize(size) : size + length * CodedOutputStream.computeTagSize(fieldNumber);
      }
   }

   static int computeSizeFixed32ListNoTag(List list) {
      return list.size() * 4;
   }

   static int computeSizeFixed32List(int fieldNumber, List list, boolean packed) {
      int length = list.size();
      if (length == 0) {
         return 0;
      } else if (packed) {
         int dataSize = length * 4;
         return CodedOutputStream.computeTagSize(fieldNumber) + CodedOutputStream.computeLengthDelimitedFieldSize(dataSize);
      } else {
         return length * CodedOutputStream.computeFixed32Size(fieldNumber, 0);
      }
   }

   static int computeSizeFixed64ListNoTag(List list) {
      return list.size() * 8;
   }

   static int computeSizeFixed64List(int fieldNumber, List list, boolean packed) {
      int length = list.size();
      if (length == 0) {
         return 0;
      } else if (packed) {
         int dataSize = length * 8;
         return CodedOutputStream.computeTagSize(fieldNumber) + CodedOutputStream.computeLengthDelimitedFieldSize(dataSize);
      } else {
         return length * CodedOutputStream.computeFixed64Size(fieldNumber, 0L);
      }
   }

   static int computeSizeBoolListNoTag(List list) {
      return list.size();
   }

   static int computeSizeBoolList(int fieldNumber, List list, boolean packed) {
      int length = list.size();
      if (length == 0) {
         return 0;
      } else {
         return packed ? CodedOutputStream.computeTagSize(fieldNumber) + CodedOutputStream.computeLengthDelimitedFieldSize(length) : length * CodedOutputStream.computeBoolSize(fieldNumber, true);
      }
   }

   static int computeSizeStringList(int fieldNumber, List list) {
      int length = list.size();
      if (length == 0) {
         return 0;
      } else {
         int size = length * CodedOutputStream.computeTagSize(fieldNumber);
         if (list instanceof LazyStringList) {
            LazyStringList lazyList = (LazyStringList)list;

            for(int i = 0; i < length; ++i) {
               Object value = lazyList.getRaw(i);
               if (value instanceof ByteString) {
                  size += CodedOutputStream.computeBytesSizeNoTag((ByteString)value);
               } else {
                  size += CodedOutputStream.computeStringSizeNoTag((String)value);
               }
            }
         } else {
            for(int i = 0; i < length; ++i) {
               Object value = list.get(i);
               if (value instanceof ByteString) {
                  size += CodedOutputStream.computeBytesSizeNoTag((ByteString)value);
               } else {
                  size += CodedOutputStream.computeStringSizeNoTag((String)value);
               }
            }
         }

         return size;
      }
   }

   static int computeSizeMessage(int fieldNumber, Object value, Schema schema) {
      return value instanceof LazyFieldLite ? CodedOutputStream.computeLazyFieldSize(fieldNumber, (LazyFieldLite)value) : CodedOutputStream.computeMessageSize(fieldNumber, (MessageLite)value, schema);
   }

   static int computeSizeMessageList(int fieldNumber, List list) {
      int length = list.size();
      if (length == 0) {
         return 0;
      } else {
         int size = length * CodedOutputStream.computeTagSize(fieldNumber);

         for(int i = 0; i < length; ++i) {
            Object value = list.get(i);
            if (value instanceof LazyFieldLite) {
               size += CodedOutputStream.computeLazyFieldSizeNoTag((LazyFieldLite)value);
            } else {
               size += CodedOutputStream.computeMessageSizeNoTag((MessageLite)value);
            }
         }

         return size;
      }
   }

   static int computeSizeMessageList(int fieldNumber, List list, Schema schema) {
      int length = list.size();
      if (length == 0) {
         return 0;
      } else {
         int size = length * CodedOutputStream.computeTagSize(fieldNumber);

         for(int i = 0; i < length; ++i) {
            Object value = list.get(i);
            if (value instanceof LazyFieldLite) {
               size += CodedOutputStream.computeLazyFieldSizeNoTag((LazyFieldLite)value);
            } else {
               size += CodedOutputStream.computeMessageSizeNoTag((MessageLite)value, schema);
            }
         }

         return size;
      }
   }

   static int computeSizeByteStringList(int fieldNumber, List list) {
      int length = list.size();
      if (length == 0) {
         return 0;
      } else {
         int size = length * CodedOutputStream.computeTagSize(fieldNumber);

         for(int i = 0; i < list.size(); ++i) {
            size += CodedOutputStream.computeBytesSizeNoTag((ByteString)list.get(i));
         }

         return size;
      }
   }

   static int computeSizeGroupList(int fieldNumber, List list) {
      int length = list.size();
      if (length == 0) {
         return 0;
      } else {
         int size = 0;

         for(int i = 0; i < length; ++i) {
            size += CodedOutputStream.computeGroupSize(fieldNumber, (MessageLite)list.get(i));
         }

         return size;
      }
   }

   static int computeSizeGroupList(int fieldNumber, List list, Schema schema) {
      int length = list.size();
      if (length == 0) {
         return 0;
      } else {
         int size = 0;

         for(int i = 0; i < length; ++i) {
            size += CodedOutputStream.computeGroupSize(fieldNumber, (MessageLite)list.get(i), schema);
         }

         return size;
      }
   }

   public static boolean shouldUseTableSwitch(FieldInfo[] fields) {
      if (fields.length == 0) {
         return false;
      } else {
         int lo = fields[0].getFieldNumber();
         int hi = fields[fields.length - 1].getFieldNumber();
         return shouldUseTableSwitch(lo, hi, fields.length);
      }
   }

   public static boolean shouldUseTableSwitch(int lo, int hi, int numFields) {
      if (hi < 40) {
         return true;
      } else {
         long tableSpaceCost = (long)hi - (long)lo + 1L;
         long tableTimeCost = 3L;
         long lookupSpaceCost = 3L + 2L * (long)numFields;
         long lookupTimeCost = 3L + (long)numFields;
         return tableSpaceCost + 3L * tableTimeCost <= lookupSpaceCost + 3L * lookupTimeCost;
      }
   }

   public static UnknownFieldSchema unknownFieldSetFullSchema() {
      return UNKNOWN_FIELD_SET_FULL_SCHEMA;
   }

   public static UnknownFieldSchema unknownFieldSetLiteSchema() {
      return UNKNOWN_FIELD_SET_LITE_SCHEMA;
   }

   private static UnknownFieldSchema getUnknownFieldSetSchema() {
      try {
         Class<?> clz = getUnknownFieldSetSchemaClass();
         return clz == null ? null : (UnknownFieldSchema)clz.getConstructor().newInstance();
      } catch (Throwable var1) {
         return null;
      }
   }

   private static Class getGeneratedMessageClass() {
      try {
         return Class.forName("org.apache.orc.protobuf.GeneratedMessageV3");
      } catch (Throwable var1) {
         return null;
      }
   }

   private static Class getUnknownFieldSetSchemaClass() {
      try {
         return Class.forName("org.apache.orc.protobuf.UnknownFieldSetSchema");
      } catch (Throwable var1) {
         return null;
      }
   }

   static Object getMapDefaultEntry(Class clazz, String name) {
      try {
         Class<?> holder = Class.forName(clazz.getName() + "$" + toCamelCase(name, true) + "DefaultEntryHolder");
         java.lang.reflect.Field[] fields = holder.getDeclaredFields();
         if (fields.length != 1) {
            throw new IllegalStateException("Unable to look up map field default entry holder class for " + name + " in " + clazz.getName());
         } else {
            return UnsafeUtil.getStaticObject(fields[0]);
         }
      } catch (Throwable t) {
         throw new RuntimeException(t);
      }
   }

   static String toCamelCase(String name, boolean capNext) {
      StringBuilder sb = new StringBuilder();

      for(int i = 0; i < name.length(); ++i) {
         char c = name.charAt(i);
         if ('a' <= c && c <= 'z') {
            if (capNext) {
               sb.append((char)(c + -32));
            } else {
               sb.append(c);
            }

            capNext = false;
         } else if ('A' <= c && c <= 'Z') {
            if (i == 0 && !capNext) {
               sb.append((char)(c - -32));
            } else {
               sb.append(c);
            }

            capNext = false;
         } else if ('0' <= c && c <= '9') {
            sb.append(c);
            capNext = true;
         } else {
            capNext = true;
         }
      }

      return sb.toString();
   }

   static boolean safeEquals(Object a, Object b) {
      return a == b || a != null && a.equals(b);
   }

   static void mergeMap(MapFieldSchema mapFieldSchema, Object message, Object o, long offset) {
      Object merged = mapFieldSchema.mergeFrom(UnsafeUtil.getObject(message, offset), UnsafeUtil.getObject(o, offset));
      UnsafeUtil.putObject(message, offset, merged);
   }

   static void mergeExtensions(ExtensionSchema schema, Object message, Object other) {
      FieldSet<FT> otherExtensions = schema.getExtensions(other);
      if (!otherExtensions.isEmpty()) {
         FieldSet<FT> messageExtensions = schema.getMutableExtensions(message);
         messageExtensions.mergeFrom(otherExtensions);
      }

   }

   static void mergeUnknownFields(UnknownFieldSchema schema, Object message, Object other) {
      UT messageUnknowns = (UT)schema.getFromMessage(message);
      UT otherUnknowns = (UT)schema.getFromMessage(other);
      UT merged = (UT)schema.merge(messageUnknowns, otherUnknowns);
      schema.setToMessage(message, merged);
   }

   @CanIgnoreReturnValue
   static Object filterUnknownEnumList(Object containerMessage, int number, List enumList, Internal.EnumLiteMap enumMap, Object unknownFields, UnknownFieldSchema unknownFieldSchema) {
      if (enumMap == null) {
         return unknownFields;
      } else {
         if (enumList instanceof RandomAccess) {
            int writePos = 0;
            int size = enumList.size();

            for(int readPos = 0; readPos < size; ++readPos) {
               int enumValue = (Integer)enumList.get(readPos);
               if (enumMap.findValueByNumber(enumValue) != null) {
                  if (readPos != writePos) {
                     enumList.set(writePos, enumValue);
                  }

                  ++writePos;
               } else {
                  unknownFields = (UB)storeUnknownEnum(containerMessage, number, enumValue, unknownFields, unknownFieldSchema);
               }
            }

            if (writePos != size) {
               enumList.subList(writePos, size).clear();
            }
         } else {
            Iterator<Integer> it = enumList.iterator();

            while(it.hasNext()) {
               int enumValue = (Integer)it.next();
               if (enumMap.findValueByNumber(enumValue) == null) {
                  unknownFields = (UB)storeUnknownEnum(containerMessage, number, enumValue, unknownFields, unknownFieldSchema);
                  it.remove();
               }
            }
         }

         return unknownFields;
      }
   }

   @CanIgnoreReturnValue
   static Object filterUnknownEnumList(Object containerMessage, int number, List enumList, Internal.EnumVerifier enumVerifier, Object unknownFields, UnknownFieldSchema unknownFieldSchema) {
      if (enumVerifier == null) {
         return unknownFields;
      } else {
         if (enumList instanceof RandomAccess) {
            int writePos = 0;
            int size = enumList.size();

            for(int readPos = 0; readPos < size; ++readPos) {
               int enumValue = (Integer)enumList.get(readPos);
               if (enumVerifier.isInRange(enumValue)) {
                  if (readPos != writePos) {
                     enumList.set(writePos, enumValue);
                  }

                  ++writePos;
               } else {
                  unknownFields = (UB)storeUnknownEnum(containerMessage, number, enumValue, unknownFields, unknownFieldSchema);
               }
            }

            if (writePos != size) {
               enumList.subList(writePos, size).clear();
            }
         } else {
            Iterator<Integer> it = enumList.iterator();

            while(it.hasNext()) {
               int enumValue = (Integer)it.next();
               if (!enumVerifier.isInRange(enumValue)) {
                  unknownFields = (UB)storeUnknownEnum(containerMessage, number, enumValue, unknownFields, unknownFieldSchema);
                  it.remove();
               }
            }
         }

         return unknownFields;
      }
   }

   @CanIgnoreReturnValue
   static Object storeUnknownEnum(Object containerMessage, int number, int enumValue, Object unknownFields, UnknownFieldSchema unknownFieldSchema) {
      if (unknownFields == null) {
         unknownFields = (UB)unknownFieldSchema.getBuilderFromMessage(containerMessage);
      }

      unknownFieldSchema.addVarint(unknownFields, number, (long)enumValue);
      return unknownFields;
   }
}
