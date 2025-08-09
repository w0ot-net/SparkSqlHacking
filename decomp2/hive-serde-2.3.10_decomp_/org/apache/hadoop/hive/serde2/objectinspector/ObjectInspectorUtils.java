package org.apache.hadoop.hive.serde2.objectinspector;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.hadoop.hive.serde2.SerDeException;
import org.apache.hadoop.hive.serde2.io.DateWritable;
import org.apache.hadoop.hive.serde2.io.DoubleWritable;
import org.apache.hadoop.hive.serde2.io.HiveCharWritable;
import org.apache.hadoop.hive.serde2.io.HiveDecimalWritable;
import org.apache.hadoop.hive.serde2.io.HiveIntervalDayTimeWritable;
import org.apache.hadoop.hive.serde2.io.HiveIntervalYearMonthWritable;
import org.apache.hadoop.hive.serde2.io.HiveVarcharWritable;
import org.apache.hadoop.hive.serde2.io.TimestampWritable;
import org.apache.hadoop.hive.serde2.lazy.LazyDouble;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.AbstractPrimitiveWritableObjectInspector;
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
import org.apache.hadoop.hive.serde2.objectinspector.primitive.JavaStringObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.LongObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.SettableBinaryObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.SettableBooleanObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.SettableByteObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.SettableDateObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.SettableDoubleObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.SettableFloatObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.SettableHiveCharObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.SettableHiveDecimalObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.SettableHiveIntervalDayTimeObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.SettableHiveIntervalYearMonthObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.SettableHiveVarcharObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.SettableIntObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.SettableLongObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.SettableShortObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.SettableTimestampObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.ShortObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.StringObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.TimestampObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.WritableStringObjectInspector;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoUtils;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ObjectInspectorUtils {
   private static final Logger LOG = LoggerFactory.getLogger(ObjectInspectorUtils.class.getName());

   public static int writableArrayHashCode(Object[] keys) {
      if (keys == null) {
         return 0;
      } else {
         int hashcode = 1;

         for(Object element : keys) {
            hashcode = 31 * hashcode;
            if (element != null) {
               if (element instanceof LazyDouble) {
                  long v = Double.doubleToLongBits(((DoubleWritable)((LazyDouble)element).getWritableObject()).get());
                  hashcode += (int)(v ^ v >>> 32);
               } else if (element instanceof org.apache.hadoop.io.DoubleWritable) {
                  long v = Double.doubleToLongBits(((org.apache.hadoop.io.DoubleWritable)element).get());
                  hashcode += (int)(v ^ v >>> 32);
               } else {
                  hashcode += element.hashCode();
               }
            }
         }

         return hashcode;
      }
   }

   public static ObjectInspector getWritableObjectInspector(ObjectInspector oi) {
      if (oi.getCategory() == ObjectInspector.Category.PRIMITIVE) {
         PrimitiveObjectInspector poi = (PrimitiveObjectInspector)oi;
         if (!(poi instanceof AbstractPrimitiveWritableObjectInspector)) {
            return PrimitiveObjectInspectorFactory.getPrimitiveWritableObjectInspector(poi.getTypeInfo());
         }
      }

      return oi;
   }

   public static ObjectInspector[] getStandardObjectInspector(ObjectInspector[] ois, ObjectInspectorCopyOption objectInspectorOption) {
      if (ois == null) {
         return null;
      } else {
         ObjectInspector[] result = new ObjectInspector[ois.length];

         for(int i = 0; i < ois.length; ++i) {
            result[i] = getStandardObjectInspector(ois[i], objectInspectorOption);
         }

         return result;
      }
   }

   public static ObjectInspector getStandardObjectInspector(ObjectInspector oi) {
      return getStandardObjectInspector(oi, ObjectInspectorUtils.ObjectInspectorCopyOption.DEFAULT);
   }

   public static ObjectInspector getStandardObjectInspector(ObjectInspector oi, ObjectInspectorCopyOption objectInspectorOption) {
      ObjectInspector result = null;
      switch (oi.getCategory()) {
         case PRIMITIVE:
            PrimitiveObjectInspector poi = (PrimitiveObjectInspector)oi;
            switch (objectInspectorOption) {
               case DEFAULT:
                  if (poi.preferWritable()) {
                     result = PrimitiveObjectInspectorFactory.getPrimitiveWritableObjectInspector(poi.getTypeInfo());
                  } else {
                     result = PrimitiveObjectInspectorFactory.getPrimitiveJavaObjectInspector(poi.getTypeInfo());
                  }

                  return result;
               case JAVA:
                  result = PrimitiveObjectInspectorFactory.getPrimitiveJavaObjectInspector(poi.getTypeInfo());
                  return result;
               case WRITABLE:
                  result = PrimitiveObjectInspectorFactory.getPrimitiveWritableObjectInspector(poi.getTypeInfo());
                  return result;
               default:
                  return result;
            }
         case LIST:
            ListObjectInspector loi = (ListObjectInspector)oi;
            result = ObjectInspectorFactory.getStandardListObjectInspector(getStandardObjectInspector(loi.getListElementObjectInspector(), objectInspectorOption));
            break;
         case MAP:
            MapObjectInspector moi = (MapObjectInspector)oi;
            result = ObjectInspectorFactory.getStandardMapObjectInspector(getStandardObjectInspector(moi.getMapKeyObjectInspector(), objectInspectorOption), getStandardObjectInspector(moi.getMapValueObjectInspector(), objectInspectorOption));
            break;
         case STRUCT:
            StructObjectInspector soi = (StructObjectInspector)oi;
            List<? extends StructField> fields = soi.getAllStructFieldRefs();
            List<String> fieldNames = new ArrayList(fields.size());
            List<ObjectInspector> fieldObjectInspectors = new ArrayList(fields.size());

            for(StructField f : fields) {
               fieldNames.add(f.getFieldName());
               fieldObjectInspectors.add(getStandardObjectInspector(f.getFieldObjectInspector(), objectInspectorOption));
            }

            result = ObjectInspectorFactory.getStandardStructObjectInspector(fieldNames, fieldObjectInspectors);
            break;
         case UNION:
            UnionObjectInspector uoi = (UnionObjectInspector)oi;
            List<ObjectInspector> ois = new ArrayList();

            for(ObjectInspector eoi : uoi.getObjectInspectors()) {
               ois.add(getStandardObjectInspector(eoi, objectInspectorOption));
            }

            result = ObjectInspectorFactory.getStandardUnionObjectInspector(ois);
            break;
         default:
            throw new RuntimeException("Unknown ObjectInspector category!");
      }

      return result;
   }

   public static void partialCopyToStandardObject(List result, Object row, int startCol, int numCols, StructObjectInspector soi, ObjectInspectorCopyOption objectInspectorOption) {
      List<? extends StructField> fields = soi.getAllStructFieldRefs();
      int i = 0;
      int j = 0;

      for(StructField f : fields) {
         if (i++ >= startCol) {
            result.add(copyToStandardObject(soi.getStructFieldData(row, f), f.getFieldObjectInspector(), objectInspectorOption));
            ++j;
            if (j == numCols) {
               break;
            }
         }
      }

   }

   public static void copyToStandardObject(List result, Object row, StructObjectInspector soi, ObjectInspectorCopyOption objectInspectorOption) {
      for(StructField f : soi.getAllStructFieldRefs()) {
         result.add(copyToStandardObject(soi.getStructFieldData(row, f), f.getFieldObjectInspector(), objectInspectorOption));
      }

   }

   public static Object[] copyToStandardObject(Object[] o, ObjectInspector[] oi, ObjectInspectorCopyOption objectInspectorOption) {
      if (o == null) {
         return null;
      } else {
         assert o.length == oi.length;

         Object[] result = new Object[o.length];

         for(int i = 0; i < o.length; ++i) {
            result[i] = copyToStandardObject(o[i], oi[i], objectInspectorOption);
         }

         return result;
      }
   }

   public static Object copyToStandardObject(Object o, ObjectInspector oi) {
      return copyToStandardObject(o, oi, ObjectInspectorUtils.ObjectInspectorCopyOption.DEFAULT);
   }

   public static Object copyToStandardJavaObject(Object o, ObjectInspector oi) {
      return copyToStandardObject(o, oi, ObjectInspectorUtils.ObjectInspectorCopyOption.JAVA);
   }

   public static int getStructSize(ObjectInspector oi) throws SerDeException {
      if (oi.getCategory() != ObjectInspector.Category.STRUCT) {
         throw new SerDeException("Unexpected category " + oi.getCategory());
      } else {
         return ((StructObjectInspector)oi).getAllStructFieldRefs().size();
      }
   }

   public static void copyStructToArray(Object o, ObjectInspector oi, ObjectInspectorCopyOption objectInspectorOption, Object[] dest, int offset) throws SerDeException {
      if (o != null) {
         if (oi.getCategory() != ObjectInspector.Category.STRUCT) {
            throw new SerDeException("Unexpected category " + oi.getCategory());
         } else {
            StructObjectInspector soi = (StructObjectInspector)oi;
            List<? extends StructField> fields = soi.getAllStructFieldRefs();

            for(int i = 0; i < fields.size(); ++i) {
               StructField f = (StructField)fields.get(i);
               dest[offset + i] = copyToStandardObject(soi.getStructFieldData(o, f), f.getFieldObjectInspector(), objectInspectorOption);
            }

         }
      }
   }

   public static Object copyToStandardObject(Object o, ObjectInspector oi, ObjectInspectorCopyOption objectInspectorOption) {
      if (o == null) {
         return null;
      } else {
         Object result = null;
         switch (oi.getCategory()) {
            case PRIMITIVE:
               PrimitiveObjectInspector loi = (PrimitiveObjectInspector)oi;
               if (objectInspectorOption == ObjectInspectorUtils.ObjectInspectorCopyOption.DEFAULT) {
                  objectInspectorOption = loi.preferWritable() ? ObjectInspectorUtils.ObjectInspectorCopyOption.WRITABLE : ObjectInspectorUtils.ObjectInspectorCopyOption.JAVA;
               }

               switch (objectInspectorOption) {
                  case JAVA:
                     result = loi.getPrimitiveJavaObject(o);
                     if (loi.getPrimitiveCategory() == PrimitiveObjectInspector.PrimitiveCategory.TIMESTAMP) {
                        result = PrimitiveObjectInspectorFactory.javaTimestampObjectInspector.copyObject(result);
                     }

                     return result;
                  case WRITABLE:
                     result = loi.getPrimitiveWritableObject(loi.copyObject(o));
                     return result;
                  default:
                     return result;
               }
            case LIST:
               ListObjectInspector loi = (ListObjectInspector)oi;
               int length = loi.getListLength(o);
               ArrayList<Object> list = new ArrayList(length);

               for(int i = 0; i < length; ++i) {
                  list.add(copyToStandardObject(loi.getListElement(o, i), loi.getListElementObjectInspector(), objectInspectorOption));
               }

               result = list;
               break;
            case MAP:
               MapObjectInspector moi = (MapObjectInspector)oi;
               HashMap<Object, Object> map = new HashMap();
               Map<? extends Object, ? extends Object> omap = moi.getMap(o);

               for(Map.Entry entry : omap.entrySet()) {
                  map.put(copyToStandardObject(entry.getKey(), moi.getMapKeyObjectInspector(), objectInspectorOption), copyToStandardObject(entry.getValue(), moi.getMapValueObjectInspector(), objectInspectorOption));
               }

               result = map;
               break;
            case STRUCT:
               StructObjectInspector soi = (StructObjectInspector)oi;
               List<? extends StructField> fields = soi.getAllStructFieldRefs();
               ArrayList<Object> struct = new ArrayList(fields.size());

               for(StructField f : fields) {
                  struct.add(copyToStandardObject(soi.getStructFieldData(o, f), f.getFieldObjectInspector(), objectInspectorOption));
               }

               result = struct;
               break;
            case UNION:
               UnionObjectInspector uoi = (UnionObjectInspector)oi;
               List<ObjectInspector> objectInspectors = uoi.getObjectInspectors();
               Object object = copyToStandardObject(uoi.getField(o), (ObjectInspector)objectInspectors.get(uoi.getTag(o)), objectInspectorOption);
               result = object;
               break;
            default:
               throw new RuntimeException("Unknown ObjectInspector category!");
         }

         return result;
      }
   }

   public static String getStandardStructTypeName(StructObjectInspector soi) {
      StringBuilder sb = new StringBuilder();
      sb.append("struct<");
      List<? extends StructField> fields = soi.getAllStructFieldRefs();

      for(int i = 0; i < fields.size(); ++i) {
         if (i > 0) {
            sb.append(",");
         }

         sb.append(((StructField)fields.get(i)).getFieldName());
         sb.append(":");
         sb.append(((StructField)fields.get(i)).getFieldObjectInspector().getTypeName());
      }

      sb.append(">");
      return sb.toString();
   }

   public static String getStandardUnionTypeName(UnionObjectInspector uoi) {
      StringBuilder sb = new StringBuilder();
      sb.append("uniontype<");
      List<ObjectInspector> ois = uoi.getObjectInspectors();

      for(int i = 0; i < ois.size(); ++i) {
         if (i > 0) {
            sb.append(",");
         }

         sb.append(((ObjectInspector)ois.get(i)).getTypeName());
      }

      sb.append(">");
      return sb.toString();
   }

   public static StructField getStandardStructFieldRef(String fieldName, List fields) {
      fieldName = fieldName.toLowerCase();

      for(int i = 0; i < fields.size(); ++i) {
         if (((StructField)fields.get(i)).getFieldName().equals(fieldName)) {
            return (StructField)fields.get(i);
         }
      }

      try {
         int i = Integer.parseInt(fieldName);
         if (i >= 0 && i < fields.size()) {
            return (StructField)fields.get(i);
         }
      } catch (NumberFormatException var3) {
      }

      throw new RuntimeException("cannot find field " + fieldName + " from " + fields);
   }

   public static Field[] getDeclaredNonStaticFields(Class c) {
      Field[] f = c.getDeclaredFields();
      ArrayList<Field> af = new ArrayList();

      for(int i = 0; i < f.length; ++i) {
         if (!Modifier.isStatic(f[i].getModifiers())) {
            af.add(f[i]);
         }
      }

      Field[] r = new Field[af.size()];

      for(int i = 0; i < af.size(); ++i) {
         r[i] = (Field)af.get(i);
      }

      return r;
   }

   public static String getObjectInspectorName(ObjectInspector oi) {
      switch (oi.getCategory()) {
         case PRIMITIVE:
            return oi.getClass().getSimpleName();
         case LIST:
            ListObjectInspector loi = (ListObjectInspector)oi;
            return oi.getClass().getSimpleName() + "<" + getObjectInspectorName(loi.getListElementObjectInspector()) + ">";
         case MAP:
            MapObjectInspector moi = (MapObjectInspector)oi;
            return oi.getClass().getSimpleName() + "<" + getObjectInspectorName(moi.getMapKeyObjectInspector()) + "," + getObjectInspectorName(moi.getMapValueObjectInspector()) + ">";
         case STRUCT:
            StringBuilder result = new StringBuilder();
            result.append(oi.getClass().getSimpleName() + "<");
            StructObjectInspector soi = (StructObjectInspector)oi;
            List<? extends StructField> fields = soi.getAllStructFieldRefs();

            for(int i = 0; i < fields.size(); ++i) {
               result.append(((StructField)fields.get(i)).getFieldName());
               result.append(":");
               result.append(getObjectInspectorName(((StructField)fields.get(i)).getFieldObjectInspector()));
               if (i == fields.size() - 1) {
                  result.append(">");
               } else {
                  result.append(",");
               }
            }

            return result.toString();
         case UNION:
            StringBuilder result = new StringBuilder();
            result.append(oi.getClass().getSimpleName() + "<");
            UnionObjectInspector uoi = (UnionObjectInspector)oi;
            List<ObjectInspector> ois = uoi.getObjectInspectors();

            for(int i = 0; i < ois.size(); ++i) {
               if (i > 0) {
                  result.append(",");
               }

               result.append(getObjectInspectorName((ObjectInspector)ois.get(i)));
            }

            result.append(">");
            return result.toString();
         default:
            throw new RuntimeException("Unknown ObjectInspector category!");
      }
   }

   public static int getBucketNumber(Object[] bucketFields, ObjectInspector[] bucketFieldInspectors, int totalBuckets) {
      return getBucketNumber(getBucketHashCode(bucketFields, bucketFieldInspectors), totalBuckets);
   }

   public static int getBucketNumber(int hashCode, int numberOfBuckets) {
      if (numberOfBuckets <= 0) {
         throw new IllegalArgumentException("Number of Buckets must be > 0");
      } else {
         return (hashCode & Integer.MAX_VALUE) % numberOfBuckets;
      }
   }

   public static int getBucketHashCode(Object[] bucketFields, ObjectInspector[] bucketFieldInspectors) {
      int hashCode = 0;

      for(int i = 0; i < bucketFields.length; ++i) {
         int fieldHash = hashCode(bucketFields[i], bucketFieldInspectors[i]);
         hashCode = 31 * hashCode + fieldHash;
      }

      return hashCode;
   }

   public static int hashCode(Object o, ObjectInspector objIns) {
      if (o == null) {
         return 0;
      } else {
         switch (objIns.getCategory()) {
            case PRIMITIVE:
               PrimitiveObjectInspector poi = (PrimitiveObjectInspector)objIns;
               switch (poi.getPrimitiveCategory()) {
                  case VOID:
                     return 0;
                  case BOOLEAN:
                     return ((BooleanObjectInspector)poi).get(o) ? 1 : 0;
                  case BYTE:
                     return ((ByteObjectInspector)poi).get(o);
                  case SHORT:
                     return ((ShortObjectInspector)poi).get(o);
                  case INT:
                     return ((IntObjectInspector)poi).get(o);
                  case LONG:
                     long a = ((LongObjectInspector)poi).get(o);
                     return (int)(a >>> 32 ^ a);
                  case FLOAT:
                     return Float.floatToIntBits(((FloatObjectInspector)poi).get(o));
                  case DOUBLE:
                     long a = Double.doubleToLongBits(((DoubleObjectInspector)poi).get(o));
                     return (int)(a >>> 32 ^ a);
                  case STRING:
                     Text t = ((StringObjectInspector)poi).getPrimitiveWritableObject(o);
                     int r = 0;

                     for(int i = 0; i < t.getLength(); ++i) {
                        r = r * 31 + t.getBytes()[i];
                     }

                     return r;
                  case CHAR:
                     return ((HiveCharObjectInspector)poi).getPrimitiveWritableObject(o).hashCode();
                  case VARCHAR:
                     return ((HiveVarcharObjectInspector)poi).getPrimitiveWritableObject(o).hashCode();
                  case BINARY:
                     return ((BinaryObjectInspector)poi).getPrimitiveWritableObject(o).hashCode();
                  case DATE:
                     return ((DateObjectInspector)poi).getPrimitiveWritableObject(o).hashCode();
                  case TIMESTAMP:
                     TimestampWritable t = ((TimestampObjectInspector)poi).getPrimitiveWritableObject(o);
                     return t.hashCode();
                  case INTERVAL_YEAR_MONTH:
                     HiveIntervalYearMonthWritable intervalYearMonth = ((HiveIntervalYearMonthObjectInspector)poi).getPrimitiveWritableObject(o);
                     return intervalYearMonth.hashCode();
                  case INTERVAL_DAY_TIME:
                     HiveIntervalDayTimeWritable intervalDayTime = ((HiveIntervalDayTimeObjectInspector)poi).getPrimitiveWritableObject(o);
                     return intervalDayTime.hashCode();
                  case DECIMAL:
                     return ((HiveDecimalObjectInspector)poi).getPrimitiveWritableObject(o).hashCode();
                  default:
                     throw new RuntimeException("Unknown type: " + poi.getPrimitiveCategory());
               }
            case LIST:
               int r = 0;
               ListObjectInspector listOI = (ListObjectInspector)objIns;
               ObjectInspector elemOI = listOI.getListElementObjectInspector();

               for(int ii = 0; ii < listOI.getListLength(o); ++ii) {
                  r = 31 * r + hashCode(listOI.getListElement(o, ii), elemOI);
               }

               return r;
            case MAP:
               int r = 0;
               MapObjectInspector mapOI = (MapObjectInspector)objIns;
               ObjectInspector keyOI = mapOI.getMapKeyObjectInspector();
               ObjectInspector valueOI = mapOI.getMapValueObjectInspector();
               Map<?, ?> map = mapOI.getMap(o);

               for(Map.Entry entry : map.entrySet()) {
                  r += hashCode(entry.getKey(), keyOI) ^ hashCode(entry.getValue(), valueOI);
               }

               return r;
            case STRUCT:
               int r = 0;
               StructObjectInspector structOI = (StructObjectInspector)objIns;

               for(StructField field : structOI.getAllStructFieldRefs()) {
                  r = 31 * r + hashCode(structOI.getStructFieldData(o, field), field.getFieldObjectInspector());
               }

               return r;
            case UNION:
               UnionObjectInspector uOI = (UnionObjectInspector)objIns;
               byte tag = uOI.getTag(o);
               return hashCode(uOI.getField(o), (ObjectInspector)uOI.getObjectInspectors().get(tag));
            default:
               throw new RuntimeException("Unknown type: " + objIns.getTypeName());
         }
      }
   }

   public static int compare(Object[] o1, ObjectInspector[] oi1, Object[] o2, ObjectInspector[] oi2) {
      assert o1.length == oi1.length;

      assert o2.length == oi2.length;

      assert o1.length == o2.length;

      for(int i = 0; i < o1.length; ++i) {
         int r = compare(o1[i], oi1[i], o2[i], oi2[i]);
         if (r != 0) {
            return r;
         }
      }

      return 0;
   }

   public static boolean compareSupported(ObjectInspector oi) {
      switch (oi.getCategory()) {
         case PRIMITIVE:
            return true;
         case LIST:
            ListObjectInspector loi = (ListObjectInspector)oi;
            return compareSupported(loi.getListElementObjectInspector());
         case MAP:
            return false;
         case STRUCT:
            StructObjectInspector soi = (StructObjectInspector)oi;
            List<? extends StructField> fields = soi.getAllStructFieldRefs();

            for(int f = 0; f < fields.size(); ++f) {
               if (!compareSupported(((StructField)fields.get(f)).getFieldObjectInspector())) {
                  return false;
               }
            }

            return true;
         case UNION:
            UnionObjectInspector uoi = (UnionObjectInspector)oi;

            for(ObjectInspector eoi : uoi.getObjectInspectors()) {
               if (!compareSupported(eoi)) {
                  return false;
               }
            }

            return true;
         default:
            return false;
      }
   }

   public static int compare(Object o1, ObjectInspector oi1, Object o2, ObjectInspector oi2) {
      return compare(o1, oi1, o2, oi2, new FullMapEqualComparer());
   }

   public static int compare(Object o1, ObjectInspector oi1, Object o2, ObjectInspector oi2, MapEqualComparer mapEqualComparer) {
      return compare(o1, oi1, o2, oi2, mapEqualComparer, ObjectInspectorUtils.NullValueOption.MINVALUE);
   }

   public static int compare(Object o1, ObjectInspector oi1, Object o2, ObjectInspector oi2, MapEqualComparer mapEqualComparer, NullValueOption nullValueOpt) {
      if (oi1.getCategory() != oi2.getCategory()) {
         return oi1.getCategory().compareTo(oi2.getCategory());
      } else {
         int nullCmpRtn = -1;
         switch (nullValueOpt) {
            case MAXVALUE:
               nullCmpRtn = 1;
               break;
            case MINVALUE:
               nullCmpRtn = -1;
         }

         if (o1 == null) {
            return o2 == null ? 0 : nullCmpRtn;
         } else if (o2 == null) {
            return -nullCmpRtn;
         } else {
            switch (oi1.getCategory()) {
               case PRIMITIVE:
                  PrimitiveObjectInspector poi1 = (PrimitiveObjectInspector)oi1;
                  PrimitiveObjectInspector poi2 = (PrimitiveObjectInspector)oi2;
                  if (poi1.getPrimitiveCategory() != poi2.getPrimitiveCategory()) {
                     return poi1.getPrimitiveCategory().compareTo(poi2.getPrimitiveCategory());
                  } else {
                     switch (poi1.getPrimitiveCategory()) {
                        case VOID:
                           return 0;
                        case BOOLEAN:
                           int v1 = ((BooleanObjectInspector)poi1).get(o1) ? 1 : 0;
                           int v2 = ((BooleanObjectInspector)poi2).get(o2) ? 1 : 0;
                           return v1 - v2;
                        case BYTE:
                           int v1 = ((ByteObjectInspector)poi1).get(o1);
                           int v2 = ((ByteObjectInspector)poi2).get(o2);
                           return v1 - v2;
                        case SHORT:
                           int v1 = ((ShortObjectInspector)poi1).get(o1);
                           int v2 = ((ShortObjectInspector)poi2).get(o2);
                           return v1 - v2;
                        case INT:
                           int v1 = ((IntObjectInspector)poi1).get(o1);
                           int v2 = ((IntObjectInspector)poi2).get(o2);
                           return v1 > v2 ? 1 : (v1 < v2 ? -1 : 0);
                        case LONG:
                           long v1 = ((LongObjectInspector)poi1).get(o1);
                           long v2 = ((LongObjectInspector)poi2).get(o2);
                           return v1 > v2 ? 1 : (v1 < v2 ? -1 : 0);
                        case FLOAT:
                           float v1 = ((FloatObjectInspector)poi1).get(o1);
                           float v2 = ((FloatObjectInspector)poi2).get(o2);
                           if (v1 == 0.0F && v2 == 0.0F) {
                              return 0;
                           }

                           return Float.compare(v1, v2);
                        case DOUBLE:
                           double v1 = ((DoubleObjectInspector)poi1).get(o1);
                           double v2 = ((DoubleObjectInspector)poi2).get(o2);
                           if (v1 == (double)0.0F && v2 == (double)0.0F) {
                              return 0;
                           }

                           return Double.compare(v1, v2);
                        case STRING:
                           if (!poi1.preferWritable() && !poi2.preferWritable()) {
                              String s1 = (String)poi1.getPrimitiveJavaObject(o1);
                              String s2 = (String)poi2.getPrimitiveJavaObject(o2);
                              return s1 == null ? (s2 == null ? 0 : -1) : (s2 == null ? 1 : s1.compareTo(s2));
                           }

                           Text t1 = (Text)poi1.getPrimitiveWritableObject(o1);
                           Text t2 = (Text)poi2.getPrimitiveWritableObject(o2);
                           return t1 == null ? (t2 == null ? 0 : -1) : (t2 == null ? 1 : t1.compareTo(t2));
                        case CHAR:
                           HiveCharWritable t1 = ((HiveCharObjectInspector)poi1).getPrimitiveWritableObject(o1);
                           HiveCharWritable t2 = ((HiveCharObjectInspector)poi2).getPrimitiveWritableObject(o2);
                           return t1.compareTo(t2);
                        case VARCHAR:
                           HiveVarcharWritable t1 = ((HiveVarcharObjectInspector)poi1).getPrimitiveWritableObject(o1);
                           HiveVarcharWritable t2 = ((HiveVarcharObjectInspector)poi2).getPrimitiveWritableObject(o2);
                           return t1.compareTo(t2);
                        case BINARY:
                           BytesWritable bw1 = ((BinaryObjectInspector)poi1).getPrimitiveWritableObject(o1);
                           BytesWritable bw2 = ((BinaryObjectInspector)poi2).getPrimitiveWritableObject(o2);
                           return bw1.compareTo(bw2);
                        case DATE:
                           DateWritable d1 = ((DateObjectInspector)poi1).getPrimitiveWritableObject(o1);
                           DateWritable d2 = ((DateObjectInspector)poi2).getPrimitiveWritableObject(o2);
                           return d1.compareTo(d2);
                        case TIMESTAMP:
                           TimestampWritable t1 = ((TimestampObjectInspector)poi1).getPrimitiveWritableObject(o1);
                           TimestampWritable t2 = ((TimestampObjectInspector)poi2).getPrimitiveWritableObject(o2);
                           return t1.compareTo(t2);
                        case INTERVAL_YEAR_MONTH:
                           HiveIntervalYearMonthWritable i1 = ((HiveIntervalYearMonthObjectInspector)poi1).getPrimitiveWritableObject(o1);
                           HiveIntervalYearMonthWritable i2 = ((HiveIntervalYearMonthObjectInspector)poi2).getPrimitiveWritableObject(o2);
                           return i1.compareTo(i2);
                        case INTERVAL_DAY_TIME:
                           HiveIntervalDayTimeWritable i1 = ((HiveIntervalDayTimeObjectInspector)poi1).getPrimitiveWritableObject(o1);
                           HiveIntervalDayTimeWritable i2 = ((HiveIntervalDayTimeObjectInspector)poi2).getPrimitiveWritableObject(o2);
                           return i1.compareTo(i2);
                        case DECIMAL:
                           HiveDecimalWritable t1 = ((HiveDecimalObjectInspector)poi1).getPrimitiveWritableObject(o1);
                           HiveDecimalWritable t2 = ((HiveDecimalObjectInspector)poi2).getPrimitiveWritableObject(o2);
                           return t1.compareTo(t2);
                        default:
                           throw new RuntimeException("Unknown type: " + poi1.getPrimitiveCategory());
                     }
                  }
               case LIST:
                  ListObjectInspector loi1 = (ListObjectInspector)oi1;
                  ListObjectInspector loi2 = (ListObjectInspector)oi2;
                  int minimum = Math.min(loi1.getListLength(o1), loi2.getListLength(o2));

                  for(int i = 0; i < minimum; ++i) {
                     int r = compare(loi1.getListElement(o1, i), loi1.getListElementObjectInspector(), loi2.getListElement(o2, i), loi2.getListElementObjectInspector(), mapEqualComparer, nullValueOpt);
                     if (r != 0) {
                        return r;
                     }
                  }

                  return loi1.getListLength(o1) - loi2.getListLength(o2);
               case MAP:
                  if (mapEqualComparer == null) {
                     throw new RuntimeException("Compare on map type not supported!");
                  }

                  return mapEqualComparer.compare(o1, (MapObjectInspector)oi1, o2, (MapObjectInspector)oi2);
               case STRUCT:
                  StructObjectInspector soi1 = (StructObjectInspector)oi1;
                  StructObjectInspector soi2 = (StructObjectInspector)oi2;
                  List<? extends StructField> fields1 = soi1.getAllStructFieldRefs();
                  List<? extends StructField> fields2 = soi2.getAllStructFieldRefs();
                  int minimum = Math.min(fields1.size(), fields2.size());

                  for(int i = 0; i < minimum; ++i) {
                     int r = compare(soi1.getStructFieldData(o1, (StructField)fields1.get(i)), ((StructField)fields1.get(i)).getFieldObjectInspector(), soi2.getStructFieldData(o2, (StructField)fields2.get(i)), ((StructField)fields2.get(i)).getFieldObjectInspector(), mapEqualComparer, nullValueOpt);
                     if (r != 0) {
                        return r;
                     }
                  }

                  return fields1.size() - fields2.size();
               case UNION:
                  UnionObjectInspector uoi1 = (UnionObjectInspector)oi1;
                  UnionObjectInspector uoi2 = (UnionObjectInspector)oi2;
                  byte tag1 = uoi1.getTag(o1);
                  byte tag2 = uoi2.getTag(o2);
                  if (tag1 != tag2) {
                     return tag1 - tag2;
                  }

                  return compare(uoi1.getField(o1), (ObjectInspector)uoi1.getObjectInspectors().get(tag1), uoi2.getField(o2), (ObjectInspector)uoi2.getObjectInspectors().get(tag2), mapEqualComparer, nullValueOpt);
               default:
                  throw new RuntimeException("Compare on unknown type: " + oi1.getCategory());
            }
         }
      }
   }

   public static String getFieldNames(StructObjectInspector soi) {
      List<? extends StructField> fields = soi.getAllStructFieldRefs();
      StringBuilder sb = new StringBuilder();

      for(int i = 0; i < fields.size(); ++i) {
         if (i > 0) {
            sb.append(",");
         }

         sb.append(((StructField)fields.get(i)).getFieldName());
      }

      return sb.toString();
   }

   public static String getFieldTypes(StructObjectInspector soi) {
      List<? extends StructField> fields = soi.getAllStructFieldRefs();
      StringBuilder sb = new StringBuilder();

      for(int i = 0; i < fields.size(); ++i) {
         if (i > 0) {
            sb.append(":");
         }

         sb.append(TypeInfoUtils.getTypeInfoFromObjectInspector(((StructField)fields.get(i)).getFieldObjectInspector()).getTypeName());
      }

      return sb.toString();
   }

   public static String getTypeNameFromJavaClass(Type t) {
      try {
         ObjectInspector oi = ObjectInspectorFactory.getReflectionObjectInspector(t, ObjectInspectorFactory.ObjectInspectorOptions.JAVA);
         return oi.getTypeName();
      } catch (Throwable e) {
         LOG.info(StringUtils.stringifyException(e));
         return "unknown";
      }
   }

   public static boolean compareTypes(ObjectInspector o1, ObjectInspector o2) {
      ObjectInspector.Category c1 = o1.getCategory();
      ObjectInspector.Category c2 = o2.getCategory();
      if (!c1.equals(c2)) {
         return false;
      } else if (c1.equals(ObjectInspector.Category.PRIMITIVE)) {
         return o1.getTypeName().equals(o2.getTypeName());
      } else if (c1.equals(ObjectInspector.Category.LIST)) {
         ObjectInspector child1 = ((ListObjectInspector)o1).getListElementObjectInspector();
         ObjectInspector child2 = ((ListObjectInspector)o2).getListElementObjectInspector();
         return compareTypes(child1, child2);
      } else if (c1.equals(ObjectInspector.Category.MAP)) {
         MapObjectInspector mapOI1 = (MapObjectInspector)o1;
         MapObjectInspector mapOI2 = (MapObjectInspector)o2;
         ObjectInspector childKey1 = mapOI1.getMapKeyObjectInspector();
         ObjectInspector childKey2 = mapOI2.getMapKeyObjectInspector();
         if (compareTypes(childKey1, childKey2)) {
            ObjectInspector childVal1 = mapOI1.getMapValueObjectInspector();
            ObjectInspector childVal2 = mapOI2.getMapValueObjectInspector();
            if (compareTypes(childVal1, childVal2)) {
               return true;
            }
         }

         return false;
      } else if (c1.equals(ObjectInspector.Category.STRUCT)) {
         StructObjectInspector structOI1 = (StructObjectInspector)o1;
         StructObjectInspector structOI2 = (StructObjectInspector)o2;
         List<? extends StructField> childFieldsList1 = structOI1.getAllStructFieldRefs();
         List<? extends StructField> childFieldsList2 = structOI2.getAllStructFieldRefs();
         if (childFieldsList1 == null && childFieldsList2 == null) {
            return true;
         } else if (childFieldsList1 != null && childFieldsList2 != null) {
            if (childFieldsList1.size() != childFieldsList2.size()) {
               return false;
            } else {
               Iterator<? extends StructField> it1 = childFieldsList1.iterator();
               Iterator<? extends StructField> it2 = childFieldsList2.iterator();

               while(it1.hasNext()) {
                  StructField field1 = (StructField)it1.next();
                  StructField field2 = (StructField)it2.next();
                  if (!compareTypes(field1.getFieldObjectInspector(), field2.getFieldObjectInspector())) {
                     return false;
                  }
               }

               return true;
            }
         } else {
            return false;
         }
      } else if (c1.equals(ObjectInspector.Category.UNION)) {
         UnionObjectInspector uoi1 = (UnionObjectInspector)o1;
         UnionObjectInspector uoi2 = (UnionObjectInspector)o2;
         List<ObjectInspector> ois1 = uoi1.getObjectInspectors();
         List<ObjectInspector> ois2 = uoi2.getObjectInspectors();
         if (ois1 == null && ois2 == null) {
            return true;
         } else if (ois1 != null && ois2 != null) {
            if (ois1.size() != ois2.size()) {
               return false;
            } else {
               Iterator<? extends ObjectInspector> it1 = ois1.iterator();
               Iterator<? extends ObjectInspector> it2 = ois2.iterator();

               while(it1.hasNext()) {
                  if (!compareTypes((ObjectInspector)it1.next(), (ObjectInspector)it2.next())) {
                     return false;
                  }
               }

               return true;
            }
         } else {
            return false;
         }
      } else {
         throw new RuntimeException("Unknown category encountered: " + c1);
      }
   }

   public static ConstantObjectInspector getConstantObjectInspector(ObjectInspector oi, Object value) {
      if (oi instanceof ConstantObjectInspector) {
         return (ConstantObjectInspector)oi;
      } else {
         ObjectInspector writableOI = getStandardObjectInspector(oi, ObjectInspectorUtils.ObjectInspectorCopyOption.WRITABLE);
         Object writableValue = value == null ? value : ObjectInspectorConverters.getConverter(oi, writableOI).convert(value);
         switch (writableOI.getCategory()) {
            case PRIMITIVE:
               PrimitiveObjectInspector poi = (PrimitiveObjectInspector)oi;
               return PrimitiveObjectInspectorFactory.getPrimitiveWritableConstantObjectInspector(poi.getTypeInfo(), writableValue);
            case LIST:
               ListObjectInspector loi = (ListObjectInspector)oi;
               return ObjectInspectorFactory.getStandardConstantListObjectInspector(getStandardObjectInspector(loi.getListElementObjectInspector(), ObjectInspectorUtils.ObjectInspectorCopyOption.WRITABLE), (List)writableValue);
            case MAP:
               MapObjectInspector moi = (MapObjectInspector)oi;
               return ObjectInspectorFactory.getStandardConstantMapObjectInspector(getStandardObjectInspector(moi.getMapKeyObjectInspector(), ObjectInspectorUtils.ObjectInspectorCopyOption.WRITABLE), getStandardObjectInspector(moi.getMapValueObjectInspector(), ObjectInspectorUtils.ObjectInspectorCopyOption.WRITABLE), (Map)writableValue);
            case STRUCT:
               StructObjectInspector soi = (StructObjectInspector)oi;
               List<? extends StructField> fields = soi.getAllStructFieldRefs();
               List<String> fieldNames = new ArrayList(fields.size());
               List<ObjectInspector> fieldObjectInspectors = new ArrayList(fields.size());

               for(StructField f : fields) {
                  fieldNames.add(f.getFieldName());
                  fieldObjectInspectors.add(getStandardObjectInspector(f.getFieldObjectInspector(), ObjectInspectorUtils.ObjectInspectorCopyOption.WRITABLE));
               }

               if (value != null && writableValue.getClass().isArray()) {
                  writableValue = Arrays.asList(writableValue);
               }

               return ObjectInspectorFactory.getStandardConstantStructObjectInspector(fieldNames, fieldObjectInspectors, (List)writableValue);
            default:
               throw new IllegalArgumentException(writableOI.getCategory() + " not yet supported for constant OI");
         }
      }
   }

   public static Object getWritableConstantValue(ObjectInspector oi) {
      return ((ConstantObjectInspector)oi).getWritableConstantValue();
   }

   public static boolean supportsConstantObjectInspector(ObjectInspector oi) {
      switch (oi.getCategory()) {
         case PRIMITIVE:
         case LIST:
         case MAP:
         case STRUCT:
            return true;
         default:
            return false;
      }
   }

   public static boolean isConstantObjectInspector(ObjectInspector oi) {
      return oi instanceof ConstantObjectInspector;
   }

   private static boolean setOISettablePropertiesMap(ObjectInspector oi, Map oiSettableProperties, boolean value) {
      if (oiSettableProperties != null) {
         oiSettableProperties.put(oi, value);
      }

      return value;
   }

   private static boolean isInstanceOfSettablePrimitiveOI(PrimitiveObjectInspector oi) {
      switch (oi.getPrimitiveCategory()) {
         case BOOLEAN:
            return oi instanceof SettableBooleanObjectInspector;
         case BYTE:
            return oi instanceof SettableByteObjectInspector;
         case SHORT:
            return oi instanceof SettableShortObjectInspector;
         case INT:
            return oi instanceof SettableIntObjectInspector;
         case LONG:
            return oi instanceof SettableLongObjectInspector;
         case FLOAT:
            return oi instanceof SettableFloatObjectInspector;
         case DOUBLE:
            return oi instanceof SettableDoubleObjectInspector;
         case STRING:
            return oi instanceof WritableStringObjectInspector || oi instanceof JavaStringObjectInspector;
         case CHAR:
            return oi instanceof SettableHiveCharObjectInspector;
         case VARCHAR:
            return oi instanceof SettableHiveVarcharObjectInspector;
         case BINARY:
            return oi instanceof SettableBinaryObjectInspector;
         case DATE:
            return oi instanceof SettableDateObjectInspector;
         case TIMESTAMP:
            return oi instanceof SettableTimestampObjectInspector;
         case INTERVAL_YEAR_MONTH:
            return oi instanceof SettableHiveIntervalYearMonthObjectInspector;
         case INTERVAL_DAY_TIME:
            return oi instanceof SettableHiveIntervalDayTimeObjectInspector;
         case DECIMAL:
            return oi instanceof SettableHiveDecimalObjectInspector;
         default:
            throw new RuntimeException("Hive internal error inside isAssignableFromSettablePrimitiveOI " + oi.getTypeName() + " not supported yet.");
      }
   }

   private static boolean isInstanceOfSettableOI(ObjectInspector oi) {
      switch (oi.getCategory()) {
         case PRIMITIVE:
            return isInstanceOfSettablePrimitiveOI((PrimitiveObjectInspector)oi);
         case LIST:
            return oi instanceof SettableListObjectInspector;
         case MAP:
            return oi instanceof SettableMapObjectInspector;
         case STRUCT:
            return oi instanceof SettableStructObjectInspector;
         case UNION:
            return oi instanceof SettableUnionObjectInspector;
         default:
            throw new RuntimeException("Hive internal error inside isAssignableFromSettableOI : " + oi.getTypeName() + " not supported yet.");
      }
   }

   public static Boolean hasAllFieldsSettable(ObjectInspector oi) {
      return hasAllFieldsSettable(oi, (Map)null);
   }

   public static boolean hasAllFieldsSettable(ObjectInspector oi, Map oiSettableProperties) {
      if (oiSettableProperties != null && oiSettableProperties.containsKey(oi)) {
         return (Boolean)oiSettableProperties.get(oi);
      } else if (!isInstanceOfSettableOI(oi)) {
         return setOISettablePropertiesMap(oi, oiSettableProperties, false);
      } else {
         Boolean returnValue = true;
         switch (oi.getCategory()) {
            case PRIMITIVE:
               break;
            case LIST:
               ListObjectInspector listOutputOI = (ListObjectInspector)oi;
               returnValue = hasAllFieldsSettable(listOutputOI.getListElementObjectInspector(), oiSettableProperties);
               break;
            case MAP:
               MapObjectInspector mapOutputOI = (MapObjectInspector)oi;
               returnValue = hasAllFieldsSettable(mapOutputOI.getMapKeyObjectInspector(), oiSettableProperties) && hasAllFieldsSettable(mapOutputOI.getMapValueObjectInspector(), oiSettableProperties);
               break;
            case STRUCT:
               StructObjectInspector structOutputOI = (StructObjectInspector)oi;

               for(StructField listField : structOutputOI.getAllStructFieldRefs()) {
                  if (!hasAllFieldsSettable(listField.getFieldObjectInspector(), oiSettableProperties)) {
                     returnValue = false;
                     return setOISettablePropertiesMap(oi, oiSettableProperties, returnValue);
                  }
               }
               break;
            case UNION:
               UnionObjectInspector unionOutputOI = (UnionObjectInspector)oi;

               for(ObjectInspector listField : unionOutputOI.getObjectInspectors()) {
                  if (!hasAllFieldsSettable(listField, oiSettableProperties)) {
                     returnValue = false;
                     return setOISettablePropertiesMap(oi, oiSettableProperties, returnValue);
                  }
               }
               break;
            default:
               throw new RuntimeException("Hive internal error inside hasAllFieldsSettable : " + oi.getTypeName() + " not supported yet.");
         }

         return setOISettablePropertiesMap(oi, oiSettableProperties, returnValue);
      }
   }

   private ObjectInspectorUtils() {
   }

   public static enum ObjectInspectorCopyOption {
      DEFAULT,
      JAVA,
      WRITABLE;
   }

   public static enum NullValueOption {
      MINVALUE,
      MAXVALUE;
   }

   public static class ObjectInspectorObject {
      private final Object[] objects;
      private final ObjectInspector[] oi;

      public ObjectInspectorObject(Object object, ObjectInspector oi) {
         this.objects = new Object[]{object};
         this.oi = new ObjectInspector[]{oi};
      }

      public ObjectInspectorObject(Object[] objects, ObjectInspector[] oi) {
         this.objects = objects;
         this.oi = oi;
      }

      public Object[] getValues() {
         return this.objects;
      }

      public boolean equals(Object obj) {
         if (this == obj) {
            return true;
         } else if (obj != null && obj.getClass() == this.getClass()) {
            ObjectInspectorObject comparedObject = (ObjectInspectorObject)obj;
            return ObjectInspectorUtils.compare(this.objects, this.oi, comparedObject.objects, comparedObject.oi) == 0;
         } else {
            return false;
         }
      }

      public int hashCode() {
         return ObjectInspectorUtils.getBucketHashCode(this.objects, this.oi);
      }
   }
}
