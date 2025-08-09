package org.apache.hadoop.hive.serde2.binarysortable;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.common.type.HiveDecimal;
import org.apache.hadoop.hive.common.type.HiveDecimalV1;
import org.apache.hadoop.hive.common.type.HiveIntervalDayTime;
import org.apache.hadoop.hive.common.type.HiveIntervalYearMonth;
import org.apache.hadoop.hive.serde2.AbstractSerDe;
import org.apache.hadoop.hive.serde2.ByteStream;
import org.apache.hadoop.hive.serde2.SerDeException;
import org.apache.hadoop.hive.serde2.SerDeSpec;
import org.apache.hadoop.hive.serde2.SerDeStats;
import org.apache.hadoop.hive.serde2.io.ByteWritable;
import org.apache.hadoop.hive.serde2.io.DateWritable;
import org.apache.hadoop.hive.serde2.io.DoubleWritable;
import org.apache.hadoop.hive.serde2.io.HiveCharWritable;
import org.apache.hadoop.hive.serde2.io.HiveDecimalWritable;
import org.apache.hadoop.hive.serde2.io.HiveIntervalDayTimeWritable;
import org.apache.hadoop.hive.serde2.io.HiveIntervalYearMonthWritable;
import org.apache.hadoop.hive.serde2.io.HiveVarcharWritable;
import org.apache.hadoop.hive.serde2.io.ShortWritable;
import org.apache.hadoop.hive.serde2.io.TimestampWritable;
import org.apache.hadoop.hive.serde2.objectinspector.ListObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.MapObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StandardUnionObjectInspector;
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
import org.apache.hadoop.hive.serde2.typeinfo.BaseCharTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.ListTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.MapTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.PrimitiveTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.StructTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoFactory;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoUtils;
import org.apache.hadoop.hive.serde2.typeinfo.UnionTypeInfo;
import org.apache.hadoop.io.BooleanWritable;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SerDeSpec(
   schemaProps = {"columns", "columns.types", "serialization.sort.order", "serialization.sort.order.null"}
)
public class BinarySortableSerDe extends AbstractSerDe {
   public static final Logger LOG = LoggerFactory.getLogger(BinarySortableSerDe.class.getName());
   public static final byte ZERO = 0;
   public static final byte ONE = 1;
   List columnNames;
   List columnTypes;
   TypeInfo rowTypeInfo;
   StructObjectInspector rowObjectInspector;
   boolean[] columnSortOrderIsDesc;
   byte[] columnNullMarker;
   byte[] columnNotNullMarker;
   public static Charset decimalCharSet = Charset.forName("US-ASCII");
   ArrayList row;
   InputByteBuffer inputByteBuffer = new InputByteBuffer();
   BytesWritable serializeBytesWritable = new BytesWritable();
   ByteStream.Output output = new ByteStream.Output();

   public void initialize(Configuration conf, Properties tbl) throws SerDeException {
      String columnNameProperty = tbl.getProperty("columns");
      String columnTypeProperty = tbl.getProperty("columns.types");
      String columnNameDelimiter = tbl.containsKey("column.name.delimiter") ? tbl.getProperty("column.name.delimiter") : String.valueOf(',');
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
      this.rowObjectInspector = (StructObjectInspector)TypeInfoUtils.getStandardWritableObjectInspectorFromTypeInfo(this.rowTypeInfo);
      this.row = new ArrayList(this.columnNames.size());

      for(int i = 0; i < this.columnNames.size(); ++i) {
         this.row.add((Object)null);
      }

      String columnSortOrder = tbl.getProperty("serialization.sort.order");
      this.columnSortOrderIsDesc = new boolean[this.columnNames.size()];

      for(int i = 0; i < this.columnSortOrderIsDesc.length; ++i) {
         this.columnSortOrderIsDesc[i] = columnSortOrder != null && columnSortOrder.charAt(i) == '-';
      }

      String columnNullOrder = tbl.getProperty("serialization.sort.order.null");
      this.columnNullMarker = new byte[this.columnNames.size()];
      this.columnNotNullMarker = new byte[this.columnNames.size()];

      for(int i = 0; i < this.columnSortOrderIsDesc.length; ++i) {
         if (this.columnSortOrderIsDesc[i]) {
            if (columnNullOrder != null && columnNullOrder.charAt(i) == 'a') {
               this.columnNullMarker[i] = 1;
               this.columnNotNullMarker[i] = 0;
            } else {
               this.columnNullMarker[i] = 0;
               this.columnNotNullMarker[i] = 1;
            }
         } else if (columnNullOrder != null && columnNullOrder.charAt(i) == 'z') {
            this.columnNullMarker[i] = 1;
            this.columnNotNullMarker[i] = 0;
         } else {
            this.columnNullMarker[i] = 0;
            this.columnNotNullMarker[i] = 1;
         }
      }

   }

   public Class getSerializedClass() {
      return BytesWritable.class;
   }

   public ObjectInspector getObjectInspector() throws SerDeException {
      return this.rowObjectInspector;
   }

   public Object deserialize(Writable blob) throws SerDeException {
      BytesWritable data = (BytesWritable)blob;
      this.inputByteBuffer.reset(data.getBytes(), 0, data.getLength());

      try {
         for(int i = 0; i < this.columnNames.size(); ++i) {
            this.row.set(i, deserialize(this.inputByteBuffer, (TypeInfo)this.columnTypes.get(i), this.columnSortOrderIsDesc[i], this.columnNullMarker[i], this.columnNotNullMarker[i], this.row.get(i)));
         }
      } catch (IOException e) {
         throw new SerDeException(e);
      }

      return this.row;
   }

   static Object deserialize(InputByteBuffer buffer, TypeInfo type, boolean invert, byte nullMarker, byte notNullMarker, Object reuse) throws IOException {
      byte isNull = buffer.read(invert);
      if (isNull == nullMarker) {
         return null;
      } else {
         assert isNull == notNullMarker;

         switch (type.getCategory()) {
            case PRIMITIVE:
               PrimitiveTypeInfo ptype = (PrimitiveTypeInfo)type;
               switch (ptype.getPrimitiveCategory()) {
                  case VOID:
                     return null;
                  case BOOLEAN:
                     BooleanWritable r = reuse == null ? new BooleanWritable() : (BooleanWritable)reuse;
                     byte b = buffer.read(invert);

                     assert b == 1 || b == 2;

                     r.set(b == 2);
                     return r;
                  case BYTE:
                     ByteWritable r = reuse == null ? new ByteWritable() : (ByteWritable)reuse;
                     r.set((byte)(buffer.read(invert) ^ 128));
                     return r;
                  case SHORT:
                     ShortWritable r = reuse == null ? new ShortWritable() : (ShortWritable)reuse;
                     int v = buffer.read(invert) ^ 128;
                     v = (v << 8) + (buffer.read(invert) & 255);
                     r.set((short)v);
                     return r;
                  case INT:
                     IntWritable r = reuse == null ? new IntWritable() : (IntWritable)reuse;
                     r.set(deserializeInt(buffer, invert));
                     return r;
                  case LONG:
                     LongWritable r = reuse == null ? new LongWritable() : (LongWritable)reuse;
                     r.set(deserializeLong(buffer, invert));
                     return r;
                  case FLOAT:
                     FloatWritable r = reuse == null ? new FloatWritable() : (FloatWritable)reuse;
                     int v = 0;

                     for(int i = 0; i < 4; ++i) {
                        v = (v << 8) + (buffer.read(invert) & 255);
                     }

                     if ((v & Integer.MIN_VALUE) == 0) {
                        v = ~v;
                     } else {
                        v ^= Integer.MIN_VALUE;
                     }

                     r.set(Float.intBitsToFloat(v));
                     return r;
                  case DOUBLE:
                     DoubleWritable r = reuse == null ? new DoubleWritable() : (DoubleWritable)reuse;
                     long v = 0L;

                     for(int i = 0; i < 8; ++i) {
                        v = (v << 8) + (long)(buffer.read(invert) & 255);
                     }

                     if ((v & Long.MIN_VALUE) == 0L) {
                        v = ~v;
                     } else {
                        v ^= Long.MIN_VALUE;
                     }

                     r.set(Double.longBitsToDouble(v));
                     return r;
                  case STRING:
                     Text r = reuse == null ? new Text() : (Text)reuse;
                     return deserializeText(buffer, invert, r);
                  case CHAR:
                     HiveCharWritable r = reuse == null ? new HiveCharWritable() : (HiveCharWritable)reuse;
                     deserializeText(buffer, invert, r.getTextValue());
                     r.enforceMaxLength(getCharacterMaxLength(type));
                     return r;
                  case VARCHAR:
                     HiveVarcharWritable r = reuse == null ? new HiveVarcharWritable() : (HiveVarcharWritable)reuse;
                     deserializeText(buffer, invert, r.getTextValue());
                     r.enforceMaxLength(getCharacterMaxLength(type));
                     return r;
                  case BINARY:
                     BytesWritable bw = new BytesWritable();
                     int start = buffer.tell();
                     int length = 0;

                     while(true) {
                        byte b = buffer.read(invert);
                        if (b == 0) {
                           if (length == buffer.tell() - start) {
                              bw.set(buffer.getData(), start, length);
                           } else {
                              bw.set(buffer.getData(), start, length);
                              buffer.seek(start);
                              byte[] rdata = bw.getBytes();

                              for(int i = 0; i < length; ++i) {
                                 byte b = buffer.read(invert);
                                 if (b == 1) {
                                    b = (byte)(buffer.read(invert) - 1);
                                 }

                                 rdata[i] = b;
                              }

                              byte b = buffer.read(invert);

                              assert b == 0;
                           }

                           return bw;
                        }

                        if (b == 1) {
                           buffer.read(invert);
                        }

                        ++length;
                     }
                  case DATE:
                     DateWritable d = reuse == null ? new DateWritable() : (DateWritable)reuse;
                     d.set(deserializeInt(buffer, invert));
                     return d;
                  case TIMESTAMP:
                     TimestampWritable t = reuse == null ? new TimestampWritable() : (TimestampWritable)reuse;
                     byte[] bytes = new byte[11];

                     for(int i = 0; i < bytes.length; ++i) {
                        bytes[i] = buffer.read(invert);
                     }

                     t.setBinarySortable(bytes, 0);
                     return t;
                  case INTERVAL_YEAR_MONTH:
                     HiveIntervalYearMonthWritable i = reuse == null ? new HiveIntervalYearMonthWritable() : (HiveIntervalYearMonthWritable)reuse;
                     i.set(deserializeInt(buffer, invert));
                     return i;
                  case INTERVAL_DAY_TIME:
                     HiveIntervalDayTimeWritable i = reuse == null ? new HiveIntervalDayTimeWritable() : (HiveIntervalDayTimeWritable)reuse;
                     long totalSecs = deserializeLong(buffer, invert);
                     int nanos = deserializeInt(buffer, invert);
                     i.set(totalSecs, nanos);
                     return i;
                  case DECIMAL:
                     HiveDecimalWritable bdw = reuse == null ? new HiveDecimalWritable() : (HiveDecimalWritable)reuse;
                     int b = buffer.read(invert) - 1;

                     assert b == 1 || b == -1 || b == 0;

                     boolean positive = b != -1;
                     int factor = buffer.read(invert) ^ 128;

                     for(int i = 0; i < 3; ++i) {
                        factor = (factor << 8) + (buffer.read(invert) & 255);
                     }

                     if (!positive) {
                        factor = -factor;
                     }

                     int start = buffer.tell();
                     int length = 0;

                     while(true) {
                        b = buffer.read(positive ? invert : !invert);

                        assert b != 1;

                        if (b == 0) {
                           byte[] decimalBuffer = new byte[length];
                           buffer.seek(start);

                           for(int i = 0; i < length; ++i) {
                              decimalBuffer[i] = buffer.read(positive ? invert : !invert);
                           }

                           buffer.read(positive ? invert : !invert);
                           String digits = new String(decimalBuffer, 0, length, decimalCharSet);
                           BigInteger bi = new BigInteger(digits);
                           HiveDecimal bd = HiveDecimal.create(bi).scaleByPowerOfTen(factor - length);
                           if (!positive) {
                              bd = bd.negate();
                           }

                           bdw.set(bd);
                           return bdw;
                        }

                        ++length;
                     }
                  default:
                     throw new RuntimeException("Unrecognized type: " + ptype.getPrimitiveCategory());
               }
            case LIST:
               ListTypeInfo ltype = (ListTypeInfo)type;
               TypeInfo etype = ltype.getListElementTypeInfo();
               ArrayList<Object> r = reuse == null ? new ArrayList() : (ArrayList)reuse;
               int size = 0;

               while(true) {
                  int more = buffer.read(invert);
                  if (more == 0) {
                     while(r.size() > size) {
                        r.remove(r.size() - 1);
                     }

                     return r;
                  }

                  assert more == 1;

                  if (size == r.size()) {
                     r.add((Object)null);
                  }

                  r.set(size, deserialize(buffer, etype, invert, nullMarker, notNullMarker, r.get(size)));
                  ++size;
               }
            case MAP:
               MapTypeInfo mtype = (MapTypeInfo)type;
               TypeInfo ktype = mtype.getMapKeyTypeInfo();
               TypeInfo vtype = mtype.getMapValueTypeInfo();
               Map<Object, Object> r;
               if (reuse == null) {
                  r = new HashMap();
               } else {
                  r = (HashMap)reuse;
                  r.clear();
               }

               while(true) {
                  int more = buffer.read(invert);
                  if (more == 0) {
                     return r;
                  }

                  assert more == 1;

                  Object k = deserialize(buffer, ktype, invert, nullMarker, notNullMarker, (Object)null);
                  Object v = deserialize(buffer, vtype, invert, nullMarker, notNullMarker, (Object)null);
                  r.put(k, v);
               }
            case STRUCT:
               StructTypeInfo stype = (StructTypeInfo)type;
               List<TypeInfo> fieldTypes = stype.getAllStructFieldTypeInfos();
               int size = fieldTypes.size();
               ArrayList<Object> r = reuse == null ? new ArrayList(size) : (ArrayList)reuse;

               assert r.size() <= size;

               while(r.size() < size) {
                  r.add((Object)null);
               }

               for(int eid = 0; eid < size; ++eid) {
                  r.set(eid, deserialize(buffer, (TypeInfo)fieldTypes.get(eid), invert, nullMarker, notNullMarker, r.get(eid)));
               }

               return r;
            case UNION:
               UnionTypeInfo utype = (UnionTypeInfo)type;
               StandardUnionObjectInspector.StandardUnion r = reuse == null ? new StandardUnionObjectInspector.StandardUnion() : (StandardUnionObjectInspector.StandardUnion)reuse;
               byte tag = buffer.read(invert);
               r.setTag(tag);
               r.setObject(deserialize(buffer, (TypeInfo)utype.getAllUnionObjectTypeInfos().get(tag), invert, nullMarker, notNullMarker, (Object)null));
               return r;
            default:
               throw new RuntimeException("Unrecognized type: " + type.getCategory());
         }
      }
   }

   private static int deserializeInt(InputByteBuffer buffer, boolean invert) throws IOException {
      int v = buffer.read(invert) ^ 128;

      for(int i = 0; i < 3; ++i) {
         v = (v << 8) + (buffer.read(invert) & 255);
      }

      return v;
   }

   private static long deserializeLong(InputByteBuffer buffer, boolean invert) throws IOException {
      long v = (long)(buffer.read(invert) ^ 128);

      for(int i = 0; i < 7; ++i) {
         v = (v << 8) + (long)(buffer.read(invert) & 255);
      }

      return v;
   }

   static int getCharacterMaxLength(TypeInfo type) {
      return ((BaseCharTypeInfo)type).getLength();
   }

   public static Text deserializeText(InputByteBuffer buffer, boolean invert, Text r) throws IOException {
      int start = buffer.tell();
      int length = 0;

      while(true) {
         byte b = buffer.read(invert);
         if (b == 0) {
            if (length == buffer.tell() - start) {
               r.set(buffer.getData(), start, length);
            } else {
               r.set(buffer.getData(), start, length);
               buffer.seek(start);
               byte[] rdata = r.getBytes();

               for(int i = 0; i < length; ++i) {
                  byte b = buffer.read(invert);
                  if (b == 1) {
                     b = (byte)(buffer.read(invert) - 1);
                  }

                  rdata[i] = b;
               }

               byte b = buffer.read(invert);

               assert b == 0;
            }

            return r;
         }

         if (b == 1) {
            buffer.read(invert);
         }

         ++length;
      }
   }

   public Writable serialize(Object obj, ObjectInspector objInspector) throws SerDeException {
      this.output.reset();
      StructObjectInspector soi = (StructObjectInspector)objInspector;
      List<? extends StructField> fields = soi.getAllStructFieldRefs();

      for(int i = 0; i < this.columnNames.size(); ++i) {
         serialize(this.output, soi.getStructFieldData(obj, (StructField)fields.get(i)), ((StructField)fields.get(i)).getFieldObjectInspector(), this.columnSortOrderIsDesc[i], this.columnNullMarker[i], this.columnNotNullMarker[i]);
      }

      this.serializeBytesWritable.set(this.output.getData(), 0, this.output.getLength());
      return this.serializeBytesWritable;
   }

   public static void writeByte(ByteStream.RandomAccessOutput buffer, byte b, boolean invert) {
      if (invert) {
         b = (byte)(255 ^ b);
      }

      buffer.write(b);
   }

   static void serialize(ByteStream.Output buffer, Object o, ObjectInspector oi, boolean invert, byte nullMarker, byte notNullMarker) throws SerDeException {
      if (o == null) {
         writeByte(buffer, nullMarker, invert);
      } else {
         writeByte(buffer, notNullMarker, invert);
         switch (oi.getCategory()) {
            case PRIMITIVE:
               PrimitiveObjectInspector poi = (PrimitiveObjectInspector)oi;
               switch (poi.getPrimitiveCategory()) {
                  case VOID:
                     return;
                  case BOOLEAN:
                     boolean v = ((BooleanObjectInspector)poi).get(o);
                     writeByte(buffer, (byte)(v ? 2 : 1), invert);
                     return;
                  case BYTE:
                     ByteObjectInspector boi = (ByteObjectInspector)poi;
                     byte v = boi.get(o);
                     writeByte(buffer, (byte)(v ^ 128), invert);
                     return;
                  case SHORT:
                     ShortObjectInspector spoi = (ShortObjectInspector)poi;
                     short v = spoi.get(o);
                     serializeShort(buffer, v, invert);
                     return;
                  case INT:
                     IntObjectInspector ioi = (IntObjectInspector)poi;
                     int v = ioi.get(o);
                     serializeInt(buffer, v, invert);
                     return;
                  case LONG:
                     LongObjectInspector loi = (LongObjectInspector)poi;
                     long v = loi.get(o);
                     serializeLong(buffer, v, invert);
                     return;
                  case FLOAT:
                     FloatObjectInspector foi = (FloatObjectInspector)poi;
                     serializeFloat(buffer, foi.get(o), invert);
                     return;
                  case DOUBLE:
                     DoubleObjectInspector doi = (DoubleObjectInspector)poi;
                     serializeDouble(buffer, doi.get(o), invert);
                     return;
                  case STRING:
                     StringObjectInspector soi = (StringObjectInspector)poi;
                     Text t = soi.getPrimitiveWritableObject(o);
                     serializeBytes(buffer, t.getBytes(), t.getLength(), invert);
                     return;
                  case CHAR:
                     HiveCharObjectInspector hcoi = (HiveCharObjectInspector)poi;
                     HiveCharWritable hc = hcoi.getPrimitiveWritableObject(o);
                     Text t = hc.getStrippedValue();
                     serializeBytes(buffer, t.getBytes(), t.getLength(), invert);
                     return;
                  case VARCHAR:
                     HiveVarcharObjectInspector hcoi = (HiveVarcharObjectInspector)poi;
                     HiveVarcharWritable hc = hcoi.getPrimitiveWritableObject(o);
                     Text t = hc.getTextValue();
                     serializeBytes(buffer, t.getBytes(), t.getLength(), invert);
                     return;
                  case BINARY:
                     BinaryObjectInspector baoi = (BinaryObjectInspector)poi;
                     BytesWritable ba = baoi.getPrimitiveWritableObject(o);
                     byte[] toSer = new byte[ba.getLength()];
                     System.arraycopy(ba.getBytes(), 0, toSer, 0, ba.getLength());
                     serializeBytes(buffer, toSer, ba.getLength(), invert);
                     return;
                  case DATE:
                     DateObjectInspector doi = (DateObjectInspector)poi;
                     int v = doi.getPrimitiveWritableObject(o).getDays();
                     serializeInt(buffer, v, invert);
                     return;
                  case TIMESTAMP:
                     TimestampObjectInspector toi = (TimestampObjectInspector)poi;
                     TimestampWritable t = toi.getPrimitiveWritableObject(o);
                     serializeTimestampWritable(buffer, t, invert);
                     return;
                  case INTERVAL_YEAR_MONTH:
                     HiveIntervalYearMonthObjectInspector ioi = (HiveIntervalYearMonthObjectInspector)poi;
                     HiveIntervalYearMonth intervalYearMonth = ioi.getPrimitiveJavaObject(o);
                     serializeHiveIntervalYearMonth(buffer, intervalYearMonth, invert);
                     return;
                  case INTERVAL_DAY_TIME:
                     HiveIntervalDayTimeObjectInspector ioi = (HiveIntervalDayTimeObjectInspector)poi;
                     HiveIntervalDayTime intervalDayTime = ioi.getPrimitiveJavaObject(o);
                     serializeHiveIntervalDayTime(buffer, intervalDayTime, invert);
                     return;
                  case DECIMAL:
                     HiveDecimalObjectInspector boi = (HiveDecimalObjectInspector)poi;
                     HiveDecimal dec = boi.getPrimitiveJavaObject(o);
                     serializeHiveDecimal(buffer, dec, invert);
                     return;
                  default:
                     throw new RuntimeException("Unrecognized type: " + poi.getPrimitiveCategory());
               }
            case LIST:
               ListObjectInspector loi = (ListObjectInspector)oi;
               ObjectInspector eoi = loi.getListElementObjectInspector();
               int size = loi.getListLength(o);

               for(int eid = 0; eid < size; ++eid) {
                  writeByte(buffer, (byte)1, invert);
                  serialize(buffer, loi.getListElement(o, eid), eoi, invert, nullMarker, notNullMarker);
               }

               writeByte(buffer, (byte)0, invert);
               return;
            case MAP:
               MapObjectInspector moi = (MapObjectInspector)oi;
               ObjectInspector koi = moi.getMapKeyObjectInspector();
               ObjectInspector voi = moi.getMapValueObjectInspector();
               Map<?, ?> map = moi.getMap(o);

               for(Map.Entry entry : map.entrySet()) {
                  writeByte(buffer, (byte)1, invert);
                  serialize(buffer, entry.getKey(), koi, invert, nullMarker, notNullMarker);
                  serialize(buffer, entry.getValue(), voi, invert, nullMarker, notNullMarker);
               }

               writeByte(buffer, (byte)0, invert);
               return;
            case STRUCT:
               StructObjectInspector soi = (StructObjectInspector)oi;
               List<? extends StructField> fields = soi.getAllStructFieldRefs();

               for(int i = 0; i < fields.size(); ++i) {
                  serialize(buffer, soi.getStructFieldData(o, (StructField)fields.get(i)), ((StructField)fields.get(i)).getFieldObjectInspector(), invert, nullMarker, notNullMarker);
               }

               return;
            case UNION:
               UnionObjectInspector uoi = (UnionObjectInspector)oi;
               byte tag = uoi.getTag(o);
               writeByte(buffer, tag, invert);
               serialize(buffer, uoi.getField(o), (ObjectInspector)uoi.getObjectInspectors().get(tag), invert, nullMarker, notNullMarker);
               return;
            default:
               throw new RuntimeException("Unrecognized type: " + oi.getCategory());
         }
      }
   }

   public static void serializeBytes(ByteStream.Output buffer, byte[] data, int length, boolean invert) {
      for(int i = 0; i < length; ++i) {
         if (data[i] != 0 && data[i] != 1) {
            writeByte(buffer, data[i], invert);
         } else {
            writeByte(buffer, (byte)1, invert);
            writeByte(buffer, (byte)(data[i] + 1), invert);
         }
      }

      writeByte(buffer, (byte)0, invert);
   }

   public static void serializeBytes(ByteStream.Output buffer, byte[] data, int offset, int length, boolean invert) {
      for(int i = offset; i < offset + length; ++i) {
         if (data[i] != 0 && data[i] != 1) {
            writeByte(buffer, data[i], invert);
         } else {
            writeByte(buffer, (byte)1, invert);
            writeByte(buffer, (byte)(data[i] + 1), invert);
         }
      }

      writeByte(buffer, (byte)0, invert);
   }

   public static void serializeShort(ByteStream.Output buffer, short v, boolean invert) {
      writeByte(buffer, (byte)(v >> 8 ^ 128), invert);
      writeByte(buffer, (byte)v, invert);
   }

   public static void serializeInt(ByteStream.Output buffer, int v, boolean invert) {
      writeByte(buffer, (byte)(v >> 24 ^ 128), invert);
      writeByte(buffer, (byte)(v >> 16), invert);
      writeByte(buffer, (byte)(v >> 8), invert);
      writeByte(buffer, (byte)v, invert);
   }

   public static void serializeLong(ByteStream.Output buffer, long v, boolean invert) {
      writeByte(buffer, (byte)((int)(v >> 56 ^ 128L)), invert);
      writeByte(buffer, (byte)((int)(v >> 48)), invert);
      writeByte(buffer, (byte)((int)(v >> 40)), invert);
      writeByte(buffer, (byte)((int)(v >> 32)), invert);
      writeByte(buffer, (byte)((int)(v >> 24)), invert);
      writeByte(buffer, (byte)((int)(v >> 16)), invert);
      writeByte(buffer, (byte)((int)(v >> 8)), invert);
      writeByte(buffer, (byte)((int)v), invert);
   }

   public static void serializeFloat(ByteStream.Output buffer, float vf, boolean invert) {
      int v = Float.floatToIntBits(vf);
      if ((v & Integer.MIN_VALUE) != 0) {
         v = ~v;
      } else {
         v ^= Integer.MIN_VALUE;
      }

      writeByte(buffer, (byte)(v >> 24), invert);
      writeByte(buffer, (byte)(v >> 16), invert);
      writeByte(buffer, (byte)(v >> 8), invert);
      writeByte(buffer, (byte)v, invert);
   }

   public static void serializeDouble(ByteStream.Output buffer, double vd, boolean invert) {
      long v = Double.doubleToLongBits(vd);
      if ((v & Long.MIN_VALUE) != 0L) {
         v = ~v;
      } else {
         v ^= Long.MIN_VALUE;
      }

      writeByte(buffer, (byte)((int)(v >> 56)), invert);
      writeByte(buffer, (byte)((int)(v >> 48)), invert);
      writeByte(buffer, (byte)((int)(v >> 40)), invert);
      writeByte(buffer, (byte)((int)(v >> 32)), invert);
      writeByte(buffer, (byte)((int)(v >> 24)), invert);
      writeByte(buffer, (byte)((int)(v >> 16)), invert);
      writeByte(buffer, (byte)((int)(v >> 8)), invert);
      writeByte(buffer, (byte)((int)v), invert);
   }

   public static void serializeTimestampWritable(ByteStream.Output buffer, TimestampWritable t, boolean invert) {
      byte[] data = t.getBinarySortable();

      for(int i = 0; i < data.length; ++i) {
         writeByte(buffer, data[i], invert);
      }

   }

   public static void serializeHiveIntervalYearMonth(ByteStream.Output buffer, HiveIntervalYearMonth intervalYearMonth, boolean invert) {
      int totalMonths = intervalYearMonth.getTotalMonths();
      serializeInt(buffer, totalMonths, invert);
   }

   public static void serializeHiveIntervalDayTime(ByteStream.Output buffer, HiveIntervalDayTime intervalDayTime, boolean invert) {
      long totalSecs = intervalDayTime.getTotalSeconds();
      int nanos = intervalDayTime.getNanos();
      serializeLong(buffer, totalSecs, invert);
      serializeInt(buffer, nanos, invert);
   }

   public static void serializeOldHiveDecimal(ByteStream.Output buffer, HiveDecimalV1 oldDec, boolean invert) {
      int sign = oldDec.compareTo(HiveDecimalV1.ZERO);
      oldDec = oldDec.abs();
      int factor = oldDec.bigDecimalValue().precision() - oldDec.bigDecimalValue().scale();
      factor = sign == 1 ? factor : -factor;
      oldDec.scaleByPowerOfTen(Math.abs(oldDec.scale()));
      String digits = oldDec.unscaledValue().toString();
      writeByte(buffer, (byte)(sign + 1), invert);
      writeByte(buffer, (byte)(factor >> 24 ^ 128), invert);
      writeByte(buffer, (byte)(factor >> 16), invert);
      writeByte(buffer, (byte)(factor >> 8), invert);
      writeByte(buffer, (byte)factor, invert);
      serializeBytes(buffer, digits.getBytes(decimalCharSet), digits.length(), sign == -1 ? !invert : invert);
   }

   public static void serializeHiveDecimal(ByteStream.Output buffer, HiveDecimal dec, boolean invert) {
      byte[] scratchBuffer = new byte[79];
      serializeHiveDecimal(buffer, dec, invert, scratchBuffer);
   }

   public static void serializeHiveDecimal(ByteStream.Output buffer, HiveDecimal dec, boolean invert, byte[] scratchBuffer) {
      int signum = dec.signum();
      int factor;
      if (signum == 0) {
         factor = 1;
      } else {
         factor = dec.rawPrecision() - dec.scale();
      }

      factor = signum == 1 ? factor : -factor;
      int index = dec.toDigitsOnlyBytes(scratchBuffer);
      writeByte(buffer, (byte)(signum + 1), invert);
      writeByte(buffer, (byte)(factor >> 24 ^ 128), invert);
      writeByte(buffer, (byte)(factor >> 16), invert);
      writeByte(buffer, (byte)(factor >> 8), invert);
      writeByte(buffer, (byte)factor, invert);
      serializeBytes(buffer, scratchBuffer, index, scratchBuffer.length - index, signum == -1 ? !invert : invert);
   }

   public static void serializeHiveDecimal(ByteStream.Output buffer, HiveDecimalWritable decWritable, boolean invert, byte[] scratchBuffer) {
      int signum = decWritable.signum();
      int factor;
      if (signum == 0) {
         factor = 1;
      } else {
         factor = decWritable.rawPrecision() - decWritable.scale();
      }

      factor = signum == 1 ? factor : -factor;
      int index = decWritable.toDigitsOnlyBytes(scratchBuffer);
      writeByte(buffer, (byte)(signum + 1), invert);
      writeByte(buffer, (byte)(factor >> 24 ^ 128), invert);
      writeByte(buffer, (byte)(factor >> 16), invert);
      writeByte(buffer, (byte)(factor >> 8), invert);
      writeByte(buffer, (byte)factor, invert);
      serializeBytes(buffer, scratchBuffer, index, scratchBuffer.length - index, signum == -1 ? !invert : invert);
   }

   public SerDeStats getSerDeStats() {
      return null;
   }

   public static void serializeStruct(ByteStream.Output byteStream, Object[] fieldData, List fieldOis, boolean[] sortableSortOrders, byte[] nullMarkers, byte[] notNullMarkers) throws SerDeException {
      for(int i = 0; i < fieldData.length; ++i) {
         serialize(byteStream, fieldData[i], (ObjectInspector)fieldOis.get(i), sortableSortOrders[i], nullMarkers[i], notNullMarkers[i]);
      }

   }

   public boolean[] getSortOrders() {
      return this.columnSortOrderIsDesc;
   }

   public byte[] getNullMarkers() {
      return this.columnNullMarker;
   }

   public byte[] getNotNullMarkers() {
      return this.columnNotNullMarker;
   }
}
