package org.apache.hadoop.hive.serde2.lazybinary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.common.type.HiveDecimal;
import org.apache.hadoop.hive.serde2.AbstractSerDe;
import org.apache.hadoop.hive.serde2.ByteStream;
import org.apache.hadoop.hive.serde2.SerDeException;
import org.apache.hadoop.hive.serde2.SerDeSpec;
import org.apache.hadoop.hive.serde2.SerDeStats;
import org.apache.hadoop.hive.serde2.io.DateWritable;
import org.apache.hadoop.hive.serde2.io.HiveDecimalWritable;
import org.apache.hadoop.hive.serde2.io.HiveIntervalDayTimeWritable;
import org.apache.hadoop.hive.serde2.io.HiveIntervalYearMonthWritable;
import org.apache.hadoop.hive.serde2.io.TimestampWritable;
import org.apache.hadoop.hive.serde2.lazy.ByteArrayRef;
import org.apache.hadoop.hive.serde2.objectinspector.ListObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.MapObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StructField;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.UnionObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.BinaryObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.BooleanObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.ByteObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.DateObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.DoubleObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.FloatObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.HiveCharObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.HiveDecimalObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.HiveIntervalDayTimeObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.HiveIntervalYearMonthObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.HiveVarcharObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.IntObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.LongObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.ShortObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.StringObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.TimestampObjectInspector;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoFactory;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoUtils;
import org.apache.hadoop.io.BinaryComparable;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SerDeSpec(
   schemaProps = {"columns", "columns.types"}
)
public class LazyBinarySerDe extends AbstractSerDe {
   public static final Logger LOG = LoggerFactory.getLogger(LazyBinarySerDe.class.getName());
   List columnNames;
   List columnTypes;
   TypeInfo rowTypeInfo;
   ObjectInspector cachedObjectInspector;
   LazyBinaryStruct cachedLazyBinaryStruct;
   private int serializedSize;
   private SerDeStats stats;
   private boolean lastOperationSerialize;
   private boolean lastOperationDeserialize;
   ByteArrayRef byteArrayRef;
   BytesWritable serializeBytesWritable = new BytesWritable();
   ByteStream.Output serializeByteStream = new ByteStream.Output();
   BooleanRef nullMapKey = new BooleanRef(false);

   public LazyBinarySerDe() throws SerDeException {
   }

   public void initialize(Configuration conf, Properties tbl) throws SerDeException {
      String columnNameProperty = tbl.getProperty("columns");
      String columnNameDelimiter = tbl.containsKey("column.name.delimiter") ? tbl.getProperty("column.name.delimiter") : String.valueOf(',');
      String columnTypeProperty = tbl.getProperty("columns.types");
      if (columnNameProperty.length() == 0) {
         this.columnNames = new ArrayList();
      } else {
         this.columnNames = Arrays.asList(columnNameProperty.split(columnNameDelimiter));
      }

      if (columnTypeProperty.length() == 0) {
         this.columnTypes = new ArrayList();
      } else {
         this.columnTypes = TypeInfoUtils.getTypeInfosFromTypeString(columnTypeProperty);
      }

      assert this.columnNames.size() == this.columnTypes.size();

      this.rowTypeInfo = TypeInfoFactory.getStructTypeInfo(this.columnNames, this.columnTypes);
      this.cachedObjectInspector = LazyBinaryUtils.getLazyBinaryObjectInspectorFromTypeInfo(this.rowTypeInfo);
      this.cachedLazyBinaryStruct = (LazyBinaryStruct)LazyBinaryFactory.createLazyBinaryObject(this.cachedObjectInspector);
      LOG.debug("LazyBinarySerDe initialized with: columnNames=" + this.columnNames + " columnTypes=" + this.columnTypes);
      this.serializedSize = 0;
      this.stats = new SerDeStats();
      this.lastOperationSerialize = false;
      this.lastOperationDeserialize = false;
   }

   public ObjectInspector getObjectInspector() throws SerDeException {
      return this.cachedObjectInspector;
   }

   public Class getSerializedClass() {
      return BytesWritable.class;
   }

   public Object deserialize(Writable field) throws SerDeException {
      if (this.byteArrayRef == null) {
         this.byteArrayRef = new ByteArrayRef();
      }

      BinaryComparable b = (BinaryComparable)field;
      if (b.getLength() == 0) {
         return null;
      } else {
         this.byteArrayRef.setData(b.getBytes());
         this.cachedLazyBinaryStruct.init(this.byteArrayRef, 0, b.getLength());
         this.lastOperationSerialize = false;
         this.lastOperationDeserialize = true;
         return this.cachedLazyBinaryStruct;
      }
   }

   public Writable serialize(Object obj, ObjectInspector objInspector) throws SerDeException {
      if (objInspector.getCategory() != ObjectInspector.Category.STRUCT) {
         throw new SerDeException(this.getClass().toString() + " can only serialize struct types, but we got: " + objInspector.getTypeName());
      } else {
         this.serializeByteStream.reset();
         serializeStruct(this.serializeByteStream, (Object)obj, (StructObjectInspector)((StructObjectInspector)objInspector), this.nullMapKey);
         this.serializeBytesWritable.set(this.serializeByteStream.getData(), 0, this.serializeByteStream.getLength());
         this.serializedSize = this.serializeByteStream.getLength();
         this.lastOperationSerialize = true;
         this.lastOperationDeserialize = false;
         return this.serializeBytesWritable;
      }
   }

   private static void serializeStruct(ByteStream.RandomAccessOutput byteStream, Object obj, StructObjectInspector soi, BooleanRef warnedOnceNullMapKey) throws SerDeException {
      if (null != obj) {
         List<? extends StructField> fields = soi.getAllStructFieldRefs();
         int size = fields.size();
         Object[] fieldData = new Object[size];
         List<ObjectInspector> fieldOis = new ArrayList(size);

         for(int i = 0; i < size; ++i) {
            StructField field = (StructField)fields.get(i);
            fieldData[i] = soi.getStructFieldData(obj, field);
            fieldOis.add(field.getFieldObjectInspector());
         }

         serializeStruct(byteStream, fieldData, fieldOis, warnedOnceNullMapKey);
      }
   }

   public static void serializeStruct(ByteStream.RandomAccessOutput byteStream, Object[] fieldData, List fieldOis) throws SerDeException {
      serializeStruct(byteStream, (Object[])fieldData, (List)fieldOis, (BooleanRef)null);
   }

   private static void serializeStruct(ByteStream.RandomAccessOutput byteStream, Object[] fieldData, List fieldOis, BooleanRef warnedOnceNullMapKey) throws SerDeException {
      int lasti = 0;
      byte nullByte = 0;
      int size = fieldData.length;

      for(int i = 0; i < size; ++i) {
         if (null != fieldData[i]) {
            nullByte = (byte)(nullByte | 1 << i % 8);
         }

         if (7 == i % 8 || i == size - 1) {
            byteStream.write(nullByte);

            for(int j = lasti; j <= i; ++j) {
               serialize(byteStream, fieldData[j], (ObjectInspector)fieldOis.get(j), false, warnedOnceNullMapKey);
            }

            lasti = i + 1;
            nullByte = 0;
         }
      }

   }

   private static void serializeUnion(ByteStream.RandomAccessOutput byteStream, Object obj, UnionObjectInspector uoi, BooleanRef warnedOnceNullMapKey) throws SerDeException {
      byte tag = uoi.getTag(obj);
      byteStream.write(tag);
      serialize(byteStream, uoi.getField(obj), (ObjectInspector)uoi.getObjectInspectors().get(tag), false, warnedOnceNullMapKey);
   }

   private static void serializeText(ByteStream.RandomAccessOutput byteStream, Text t, boolean skipLengthPrefix) {
      int length = t.getLength();
      if (!skipLengthPrefix) {
         LazyBinaryUtils.writeVInt(byteStream, length);
      }

      byte[] data = t.getBytes();
      byteStream.write(data, 0, length);
   }

   private static void writeDateToByteStream(ByteStream.RandomAccessOutput byteStream, DateWritable date) {
      LazyBinaryUtils.writeVInt(byteStream, date.getDays());
   }

   public static void setFromBigIntegerBytesAndScale(byte[] bytes, int offset, int length, HiveDecimalWritable dec) {
      LazyBinaryUtils.VInt vInt = new LazyBinaryUtils.VInt();
      LazyBinaryUtils.readVInt(bytes, offset, vInt);
      int scale = vInt.value;
      offset += vInt.length;
      LazyBinaryUtils.readVInt(bytes, offset, vInt);
      offset += vInt.length;
      dec.setFromBigIntegerBytesAndScale(bytes, offset, vInt.value, scale);
   }

   public static void writeToByteStream(ByteStream.RandomAccessOutput byteStream, HiveDecimalWritable decWritable) {
      LazyBinaryUtils.writeVInt(byteStream, decWritable.scale());
      int byteLength = decWritable.bigIntegerBytesInternalScratch();
      LazyBinaryUtils.writeVInt(byteStream, byteLength);
      byteStream.write(decWritable.bigIntegerBytesInternalScratchBuffer(), 0, byteLength);
   }

   public static void writeToByteStream(ByteStream.RandomAccessOutput byteStream, HiveDecimal dec, long[] scratchLongs, byte[] scratchBytes) {
      LazyBinaryUtils.writeVInt(byteStream, dec.scale());
      int byteLength = dec.bigIntegerBytes(scratchLongs, scratchBytes);
      if (byteLength == 0) {
         throw new RuntimeException("Decimal to binary conversion failed");
      } else {
         LazyBinaryUtils.writeVInt(byteStream, byteLength);
         byteStream.write(scratchBytes, 0, byteLength);
      }
   }

   public static void writeToByteStream(ByteStream.RandomAccessOutput byteStream, HiveDecimalWritable decWritable, long[] scratchLongs, byte[] scratchBytes) {
      LazyBinaryUtils.writeVInt(byteStream, decWritable.scale());
      int byteLength = decWritable.bigIntegerBytes(scratchLongs, scratchBytes);
      LazyBinaryUtils.writeVInt(byteStream, byteLength);
      byteStream.write(scratchBytes, 0, byteLength);
   }

   public static void serialize(ByteStream.RandomAccessOutput byteStream, Object obj, ObjectInspector objInspector, boolean skipLengthPrefix, BooleanRef warnedOnceNullMapKey) throws SerDeException {
      if (null != obj) {
         switch (objInspector.getCategory()) {
            case PRIMITIVE:
               PrimitiveObjectInspector poi = (PrimitiveObjectInspector)objInspector;
               switch (poi.getPrimitiveCategory()) {
                  case VOID:
                     return;
                  case BOOLEAN:
                     boolean v = ((BooleanObjectInspector)poi).get(obj);
                     byteStream.write((byte)(v ? 1 : 0));
                     return;
                  case BYTE:
                     ByteObjectInspector boi = (ByteObjectInspector)poi;
                     byte v = boi.get(obj);
                     byteStream.write(v);
                     return;
                  case SHORT:
                     ShortObjectInspector spoi = (ShortObjectInspector)poi;
                     short v = spoi.get(obj);
                     byteStream.write((byte)(v >> 8));
                     byteStream.write((byte)v);
                     return;
                  case INT:
                     IntObjectInspector ioi = (IntObjectInspector)poi;
                     int v = ioi.get(obj);
                     LazyBinaryUtils.writeVInt(byteStream, v);
                     return;
                  case LONG:
                     LongObjectInspector loi = (LongObjectInspector)poi;
                     long v = loi.get(obj);
                     LazyBinaryUtils.writeVLong(byteStream, v);
                     return;
                  case FLOAT:
                     FloatObjectInspector foi = (FloatObjectInspector)poi;
                     int v = Float.floatToIntBits(foi.get(obj));
                     byteStream.write((byte)(v >> 24));
                     byteStream.write((byte)(v >> 16));
                     byteStream.write((byte)(v >> 8));
                     byteStream.write((byte)v);
                     return;
                  case DOUBLE:
                     DoubleObjectInspector doi = (DoubleObjectInspector)poi;
                     LazyBinaryUtils.writeDouble(byteStream, doi.get(obj));
                     return;
                  case STRING:
                     StringObjectInspector soi = (StringObjectInspector)poi;
                     Text t = soi.getPrimitiveWritableObject(obj);
                     serializeText(byteStream, t, skipLengthPrefix);
                     return;
                  case CHAR:
                     HiveCharObjectInspector hcoi = (HiveCharObjectInspector)poi;
                     Text t = hcoi.getPrimitiveWritableObject(obj).getTextValue();
                     serializeText(byteStream, t, skipLengthPrefix);
                     return;
                  case VARCHAR:
                     HiveVarcharObjectInspector hcoi = (HiveVarcharObjectInspector)poi;
                     Text t = hcoi.getPrimitiveWritableObject(obj).getTextValue();
                     serializeText(byteStream, t, skipLengthPrefix);
                     return;
                  case BINARY:
                     BinaryObjectInspector baoi = (BinaryObjectInspector)poi;
                     BytesWritable bw = baoi.getPrimitiveWritableObject(obj);
                     int length = bw.getLength();
                     if (!skipLengthPrefix) {
                        LazyBinaryUtils.writeVInt(byteStream, length);
                     } else if (length == 0) {
                        throw new RuntimeException("LazyBinaryColumnarSerde cannot serialize a non-null zero length binary field. Consider using either LazyBinarySerde or ColumnarSerde.");
                     }

                     byteStream.write(bw.getBytes(), 0, length);
                     return;
                  case DATE:
                     DateWritable d = ((DateObjectInspector)poi).getPrimitiveWritableObject(obj);
                     writeDateToByteStream(byteStream, d);
                     return;
                  case TIMESTAMP:
                     TimestampObjectInspector toi = (TimestampObjectInspector)poi;
                     TimestampWritable t = toi.getPrimitiveWritableObject(obj);
                     t.writeToByteStream(byteStream);
                     return;
                  case INTERVAL_YEAR_MONTH:
                     HiveIntervalYearMonthWritable intervalYearMonth = ((HiveIntervalYearMonthObjectInspector)poi).getPrimitiveWritableObject(obj);
                     intervalYearMonth.writeToByteStream(byteStream);
                     return;
                  case INTERVAL_DAY_TIME:
                     HiveIntervalDayTimeWritable intervalDayTime = ((HiveIntervalDayTimeObjectInspector)poi).getPrimitiveWritableObject(obj);
                     intervalDayTime.writeToByteStream(byteStream);
                     return;
                  case DECIMAL:
                     HiveDecimalObjectInspector bdoi = (HiveDecimalObjectInspector)poi;
                     HiveDecimalWritable t = bdoi.getPrimitiveWritableObject(obj);
                     if (t == null) {
                        return;
                     }

                     writeToByteStream(byteStream, t);
                     return;
                  default:
                     throw new RuntimeException("Unrecognized type: " + poi.getPrimitiveCategory());
               }
            case LIST:
               ListObjectInspector loi = (ListObjectInspector)objInspector;
               ObjectInspector eoi = loi.getListElementObjectInspector();
               int byteSizeStart = 0;
               int listStart = 0;
               if (!skipLengthPrefix) {
                  byteSizeStart = byteStream.getLength();
                  byteStream.reserve(4);
                  listStart = byteStream.getLength();
               }

               int size = loi.getListLength(obj);
               LazyBinaryUtils.writeVInt(byteStream, size);
               byte nullByte = 0;
               int eid = 0;

               for(; eid < size; ++eid) {
                  if (null != loi.getListElement(obj, eid)) {
                     nullByte = (byte)(nullByte | 1 << eid % 8);
                  }

                  if (7 == eid % 8 || eid == size - 1) {
                     byteStream.write(nullByte);
                     nullByte = 0;
                  }
               }

               for(int eid = 0; eid < size; ++eid) {
                  serialize(byteStream, loi.getListElement(obj, eid), eoi, false, warnedOnceNullMapKey);
               }

               if (!skipLengthPrefix) {
                  eid = byteStream.getLength();
                  int listSize = eid - listStart;
                  writeSizeAtOffset(byteStream, byteSizeStart, listSize);
               }

               return;
            case MAP:
               MapObjectInspector moi = (MapObjectInspector)objInspector;
               ObjectInspector koi = moi.getMapKeyObjectInspector();
               ObjectInspector voi = moi.getMapValueObjectInspector();
               Map<?, ?> map = moi.getMap(obj);
               int byteSizeStart = 0;
               int mapStart = 0;
               if (!skipLengthPrefix) {
                  byteSizeStart = byteStream.getLength();
                  byteStream.reserve(4);
                  mapStart = byteStream.getLength();
               }

               int size = map.size();
               LazyBinaryUtils.writeVInt(byteStream, size);
               int b = 0;
               byte nullByte = 0;

               for(Map.Entry entry : map.entrySet()) {
                  if (null != entry.getKey()) {
                     nullByte = (byte)(nullByte | 1 << b % 8);
                  } else if (warnedOnceNullMapKey != null) {
                     if (!warnedOnceNullMapKey.value) {
                        LOG.warn("Null map key encountered! Ignoring similar problems.");
                     }

                     warnedOnceNullMapKey.value = true;
                  }

                  ++b;
                  if (null != entry.getValue()) {
                     nullByte = (byte)(nullByte | 1 << b % 8);
                  }

                  ++b;
                  if (0 == b % 8 || b == size * 2) {
                     byteStream.write(nullByte);
                     nullByte = 0;
                  }
               }

               for(Map.Entry entry : map.entrySet()) {
                  serialize(byteStream, entry.getKey(), koi, false, warnedOnceNullMapKey);
                  serialize(byteStream, entry.getValue(), voi, false, warnedOnceNullMapKey);
               }

               if (!skipLengthPrefix) {
                  int mapEnd = byteStream.getLength();
                  int mapSize = mapEnd - mapStart;
                  writeSizeAtOffset(byteStream, byteSizeStart, mapSize);
               }

               return;
            case STRUCT:
            case UNION:
               int byteSizeStart = 0;
               int typeStart = 0;
               if (!skipLengthPrefix) {
                  byteSizeStart = byteStream.getLength();
                  byteStream.reserve(4);
                  typeStart = byteStream.getLength();
               }

               if (ObjectInspector.Category.STRUCT.equals(objInspector.getCategory())) {
                  serializeStruct(byteStream, obj, (StructObjectInspector)objInspector, warnedOnceNullMapKey);
               } else {
                  serializeUnion(byteStream, obj, (UnionObjectInspector)objInspector, warnedOnceNullMapKey);
               }

               if (!skipLengthPrefix) {
                  int typeEnd = byteStream.getLength();
                  int typeSize = typeEnd - typeStart;
                  writeSizeAtOffset(byteStream, byteSizeStart, typeSize);
               }

               return;
            default:
               throw new RuntimeException("Unrecognized type: " + objInspector.getCategory());
         }
      }
   }

   private static void writeSizeAtOffset(ByteStream.RandomAccessOutput byteStream, int byteSizeStart, int size) {
      byteStream.writeInt((long)byteSizeStart, size);
   }

   public SerDeStats getSerDeStats() {
      assert this.lastOperationSerialize != this.lastOperationDeserialize;

      if (this.lastOperationSerialize) {
         this.stats.setRawDataSize((long)this.serializedSize);
      } else {
         this.stats.setRawDataSize(this.cachedLazyBinaryStruct.getRawDataSerializedSize());
      }

      return this.stats;
   }

   public static class StringWrapper {
      public byte[] bytes;
      public int start;
      public int length;

      public void set(byte[] bytes, int start, int length) {
         this.bytes = bytes;
         this.start = start;
         this.length = length;
      }
   }

   public static class BooleanRef {
      public boolean value;

      public BooleanRef(boolean v) {
         this.value = v;
      }
   }
}
