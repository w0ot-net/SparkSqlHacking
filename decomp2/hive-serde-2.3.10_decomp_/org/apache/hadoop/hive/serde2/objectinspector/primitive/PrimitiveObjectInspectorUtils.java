package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.charset.CharacterCodingException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import org.apache.hadoop.hive.common.type.HiveChar;
import org.apache.hadoop.hive.common.type.HiveDecimal;
import org.apache.hadoop.hive.common.type.HiveIntervalDayTime;
import org.apache.hadoop.hive.common.type.HiveIntervalYearMonth;
import org.apache.hadoop.hive.common.type.HiveVarchar;
import org.apache.hadoop.hive.ql.util.TimestampUtils;
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
import org.apache.hadoop.hive.serde2.lazy.LazyInteger;
import org.apache.hadoop.hive.serde2.lazy.LazyLong;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;
import org.apache.hadoop.io.BooleanWritable;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class PrimitiveObjectInspectorUtils {
   private static final Logger LOG = LoggerFactory.getLogger(PrimitiveObjectInspectorUtils.class);
   static final Map primitiveCategoryToTypeEntry = new HashMap();
   static final Map primitiveJavaTypeToTypeEntry = new HashMap();
   static final Map primitiveJavaClassToTypeEntry = new HashMap();
   static final Map primitiveWritableClassToTypeEntry = new HashMap();
   private static final Map typeNameToTypeEntry = new HashMap();
   public static final PrimitiveTypeEntry binaryTypeEntry;
   public static final PrimitiveTypeEntry stringTypeEntry;
   public static final PrimitiveTypeEntry booleanTypeEntry;
   public static final PrimitiveTypeEntry intTypeEntry;
   public static final PrimitiveTypeEntry longTypeEntry;
   public static final PrimitiveTypeEntry floatTypeEntry;
   public static final PrimitiveTypeEntry voidTypeEntry;
   public static final PrimitiveTypeEntry doubleTypeEntry;
   public static final PrimitiveTypeEntry byteTypeEntry;
   public static final PrimitiveTypeEntry shortTypeEntry;
   public static final PrimitiveTypeEntry dateTypeEntry;
   public static final PrimitiveTypeEntry timestampTypeEntry;
   public static final PrimitiveTypeEntry intervalYearMonthTypeEntry;
   public static final PrimitiveTypeEntry intervalDayTimeTypeEntry;
   public static final PrimitiveTypeEntry decimalTypeEntry;
   public static final PrimitiveTypeEntry varcharTypeEntry;
   public static final PrimitiveTypeEntry charTypeEntry;
   public static final PrimitiveTypeEntry unknownTypeEntry;

   static void addParameterizedType(PrimitiveTypeEntry t) {
      typeNameToTypeEntry.put(t.toString(), t);
   }

   static void registerType(PrimitiveTypeEntry t) {
      if (t.primitiveCategory != PrimitiveObjectInspector.PrimitiveCategory.UNKNOWN) {
         primitiveCategoryToTypeEntry.put(t.primitiveCategory, t);
      }

      if (t.primitiveJavaType != null) {
         primitiveJavaTypeToTypeEntry.put(t.primitiveJavaType, t);
      }

      if (t.primitiveJavaClass != null) {
         primitiveJavaClassToTypeEntry.put(t.primitiveJavaClass, t);
      }

      if (t.primitiveWritableClass != null) {
         primitiveWritableClassToTypeEntry.put(t.primitiveWritableClass, t);
      }

      if (t.typeName != null) {
         typeNameToTypeEntry.put(t.typeName, t);
      }

   }

   public static Class primitiveJavaTypeToClass(Class clazz) {
      PrimitiveTypeEntry t = (PrimitiveTypeEntry)primitiveJavaTypeToTypeEntry.get(clazz);
      return t == null ? clazz : t.primitiveJavaClass;
   }

   public static boolean isPrimitiveJava(Class clazz) {
      return primitiveJavaTypeToTypeEntry.get(clazz) != null || primitiveJavaClassToTypeEntry.get(clazz) != null;
   }

   public static boolean isPrimitiveJavaType(Class clazz) {
      return primitiveJavaTypeToTypeEntry.get(clazz) != null;
   }

   public static boolean isPrimitiveJavaClass(Class clazz) {
      return primitiveJavaClassToTypeEntry.get(clazz) != null;
   }

   public static boolean isPrimitiveWritableClass(Class clazz) {
      return primitiveWritableClassToTypeEntry.get(clazz) != null;
   }

   public static String getTypeNameFromPrimitiveJava(Class clazz) {
      PrimitiveTypeEntry t = (PrimitiveTypeEntry)primitiveJavaTypeToTypeEntry.get(clazz);
      if (t == null) {
         t = (PrimitiveTypeEntry)primitiveJavaClassToTypeEntry.get(clazz);
      }

      return t == null ? null : t.typeName;
   }

   public static String getTypeNameFromPrimitiveWritable(Class clazz) {
      PrimitiveTypeEntry t = (PrimitiveTypeEntry)primitiveWritableClassToTypeEntry.get(clazz);
      return t == null ? null : t.typeName;
   }

   public static PrimitiveTypeEntry getTypeEntryFromPrimitiveCategory(PrimitiveObjectInspector.PrimitiveCategory category) {
      return (PrimitiveTypeEntry)primitiveCategoryToTypeEntry.get(category);
   }

   public static PrimitiveTypeEntry getTypeEntryFromPrimitiveJava(Class clazz) {
      PrimitiveTypeEntry t = (PrimitiveTypeEntry)primitiveJavaTypeToTypeEntry.get(clazz);
      if (t == null) {
         t = (PrimitiveTypeEntry)primitiveJavaClassToTypeEntry.get(clazz);
      }

      return t;
   }

   public static PrimitiveTypeEntry getTypeEntryFromPrimitiveJavaType(Class clazz) {
      return (PrimitiveTypeEntry)primitiveJavaTypeToTypeEntry.get(clazz);
   }

   public static PrimitiveTypeEntry getTypeEntryFromPrimitiveJavaClass(Class clazz) {
      return (PrimitiveTypeEntry)primitiveJavaClassToTypeEntry.get(clazz);
   }

   public static PrimitiveTypeEntry getTypeEntryFromPrimitiveWritableClass(Class clazz) {
      return (PrimitiveTypeEntry)primitiveWritableClassToTypeEntry.get(clazz);
   }

   public static PrimitiveTypeEntry getTypeEntryFromTypeName(String typeName) {
      return (PrimitiveTypeEntry)typeNameToTypeEntry.get(typeName);
   }

   public static boolean comparePrimitiveObjects(Object o1, PrimitiveObjectInspector oi1, Object o2, PrimitiveObjectInspector oi2) {
      if (o1 != null && o2 != null) {
         if (oi1.getPrimitiveCategory() != oi2.getPrimitiveCategory()) {
            return false;
         } else {
            switch (oi1.getPrimitiveCategory()) {
               case BOOLEAN:
                  return ((BooleanObjectInspector)oi1).get(o1) == ((BooleanObjectInspector)oi2).get(o2);
               case BYTE:
                  return ((ByteObjectInspector)oi1).get(o1) == ((ByteObjectInspector)oi2).get(o2);
               case SHORT:
                  return ((ShortObjectInspector)oi1).get(o1) == ((ShortObjectInspector)oi2).get(o2);
               case INT:
                  return ((IntObjectInspector)oi1).get(o1) == ((IntObjectInspector)oi2).get(o2);
               case LONG:
                  return ((LongObjectInspector)oi1).get(o1) == ((LongObjectInspector)oi2).get(o2);
               case FLOAT:
                  return ((FloatObjectInspector)oi1).get(o1) == ((FloatObjectInspector)oi2).get(o2);
               case DOUBLE:
                  return ((DoubleObjectInspector)oi1).get(o1) == ((DoubleObjectInspector)oi2).get(o2);
               case STRING:
                  Writable t1 = ((StringObjectInspector)oi1).getPrimitiveWritableObject(o1);
                  Writable t2 = ((StringObjectInspector)oi2).getPrimitiveWritableObject(o2);
                  return t1.equals(t2);
               case CHAR:
                  return ((HiveCharObjectInspector)oi1).getPrimitiveWritableObject(o1).equals(((HiveCharObjectInspector)oi2).getPrimitiveWritableObject(o2));
               case VARCHAR:
                  return ((HiveVarcharObjectInspector)oi1).getPrimitiveWritableObject(o1).equals(((HiveVarcharObjectInspector)oi2).getPrimitiveWritableObject(o2));
               case DATE:
                  return ((DateObjectInspector)oi1).getPrimitiveWritableObject(o1).equals(((DateObjectInspector)oi2).getPrimitiveWritableObject(o2));
               case TIMESTAMP:
                  return ((TimestampObjectInspector)oi1).getPrimitiveWritableObject(o1).equals(((TimestampObjectInspector)oi2).getPrimitiveWritableObject(o2));
               case INTERVAL_YEAR_MONTH:
                  return ((HiveIntervalYearMonthObjectInspector)oi1).getPrimitiveWritableObject(o1).equals(((HiveIntervalYearMonthObjectInspector)oi2).getPrimitiveWritableObject(o2));
               case INTERVAL_DAY_TIME:
                  return ((HiveIntervalDayTimeObjectInspector)oi1).getPrimitiveWritableObject(o1).equals(((HiveIntervalDayTimeObjectInspector)oi2).getPrimitiveWritableObject(o2));
               case BINARY:
                  return ((BinaryObjectInspector)oi1).getPrimitiveWritableObject(o1).equals(((BinaryObjectInspector)oi2).getPrimitiveWritableObject(o2));
               case DECIMAL:
                  return ((HiveDecimalObjectInspector)oi1).getPrimitiveJavaObject(o1).compareTo(((HiveDecimalObjectInspector)oi2).getPrimitiveJavaObject(o2)) == 0;
               default:
                  return false;
            }
         }
      } else {
         return false;
      }
   }

   public static double convertPrimitiveToDouble(Object o, PrimitiveObjectInspector oi) {
      switch (oi.getPrimitiveCategory()) {
         case BOOLEAN:
            return ((BooleanObjectInspector)oi).get(o) ? (double)1.0F : (double)0.0F;
         case BYTE:
            return (double)((ByteObjectInspector)oi).get(o);
         case SHORT:
            return (double)((ShortObjectInspector)oi).get(o);
         case INT:
            return (double)((IntObjectInspector)oi).get(o);
         case LONG:
            return (double)((LongObjectInspector)oi).get(o);
         case FLOAT:
            return (double)((FloatObjectInspector)oi).get(o);
         case DOUBLE:
            return ((DoubleObjectInspector)oi).get(o);
         case STRING:
            return Double.valueOf(((StringObjectInspector)oi).getPrimitiveJavaObject(o));
         case CHAR:
         case VARCHAR:
         case DATE:
         case INTERVAL_YEAR_MONTH:
         case INTERVAL_DAY_TIME:
         case BINARY:
         default:
            throw new NumberFormatException();
         case TIMESTAMP:
            return ((TimestampObjectInspector)oi).getPrimitiveWritableObject(o).getDouble();
         case DECIMAL:
            return ((HiveDecimalObjectInspector)oi).getPrimitiveJavaObject(o).doubleValue();
      }
   }

   public static boolean comparePrimitiveObjectsWithConversion(Object o1, PrimitiveObjectInspector oi1, Object o2, PrimitiveObjectInspector oi2) {
      if (o1 != null && o2 != null) {
         if (oi1.getPrimitiveCategory() == oi2.getPrimitiveCategory()) {
            return comparePrimitiveObjects(o1, oi1, o2, oi2);
         } else {
            try {
               return convertPrimitiveToDouble(o1, oi1) == convertPrimitiveToDouble(o2, oi2);
            } catch (NumberFormatException var5) {
               return false;
            }
         }
      } else {
         return false;
      }
   }

   public static boolean getBoolean(Object o, PrimitiveObjectInspector oi) {
      boolean result = false;
      switch (oi.getPrimitiveCategory()) {
         case BOOLEAN:
            result = ((BooleanObjectInspector)oi).get(o);
            break;
         case BYTE:
            result = ((ByteObjectInspector)oi).get(o) != 0;
            break;
         case SHORT:
            result = ((ShortObjectInspector)oi).get(o) != 0;
            break;
         case INT:
            result = ((IntObjectInspector)oi).get(o) != 0;
            break;
         case LONG:
            result = (int)((LongObjectInspector)oi).get(o) != 0;
            break;
         case FLOAT:
            result = (int)((FloatObjectInspector)oi).get(o) != 0;
            break;
         case DOUBLE:
            result = (int)((DoubleObjectInspector)oi).get(o) != 0;
            break;
         case STRING:
            StringObjectInspector soi = (StringObjectInspector)oi;
            if (soi.preferWritable()) {
               Text t = soi.getPrimitiveWritableObject(o);
               result = t.getLength() != 0;
            } else {
               String s = soi.getPrimitiveJavaObject(o);
               result = s.length() != 0;
            }
            break;
         case CHAR:
         case VARCHAR:
         case DATE:
         case INTERVAL_YEAR_MONTH:
         case INTERVAL_DAY_TIME:
         case BINARY:
         default:
            throw new RuntimeException("Hive 2 Internal error: unsupported conversion from type: " + oi.getTypeName());
         case TIMESTAMP:
            result = ((TimestampObjectInspector)oi).getPrimitiveWritableObject(o).getSeconds() != 0L;
            break;
         case DECIMAL:
            result = HiveDecimal.ZERO.compareTo(((HiveDecimalObjectInspector)oi).getPrimitiveJavaObject(o)) != 0;
            break;
         case VOID:
            result = false;
      }

      return result;
   }

   public static byte getByte(Object o, PrimitiveObjectInspector oi) {
      switch (oi.getPrimitiveCategory()) {
         case DECIMAL:
            HiveDecimal dec = ((HiveDecimalObjectInspector)oi).getPrimitiveJavaObject(o);
            if (!dec.isByte()) {
               throw new NumberFormatException();
            }

            byte result = dec.byteValue();
            return result;
         default:
            return (byte)getInt(o, oi);
      }
   }

   public static short getShort(Object o, PrimitiveObjectInspector oi) {
      switch (oi.getPrimitiveCategory()) {
         case DECIMAL:
            HiveDecimal dec = ((HiveDecimalObjectInspector)oi).getPrimitiveJavaObject(o);
            if (!dec.isShort()) {
               throw new NumberFormatException();
            }

            short result = dec.shortValue();
            return result;
         default:
            return (short)getInt(o, oi);
      }
   }

   public static int getInt(Object o, PrimitiveObjectInspector oi) {
      int result = 0;
      switch (oi.getPrimitiveCategory()) {
         case BOOLEAN:
            result = ((BooleanObjectInspector)oi).get(o) ? 1 : 0;
            break;
         case BYTE:
            result = ((ByteObjectInspector)oi).get(o);
            break;
         case SHORT:
            result = ((ShortObjectInspector)oi).get(o);
            break;
         case INT:
            result = ((IntObjectInspector)oi).get(o);
            break;
         case LONG:
            result = (int)((LongObjectInspector)oi).get(o);
            break;
         case FLOAT:
            result = (int)((FloatObjectInspector)oi).get(o);
            break;
         case DOUBLE:
            result = (int)((DoubleObjectInspector)oi).get(o);
            break;
         case STRING:
            StringObjectInspector soi = (StringObjectInspector)oi;
            if (soi.preferWritable()) {
               Text t = soi.getPrimitiveWritableObject(o);
               result = LazyInteger.parseInt(t.getBytes(), 0, t.getLength());
            } else {
               String s = soi.getPrimitiveJavaObject(o);
               result = Integer.parseInt(s);
            }
            break;
         case CHAR:
         case VARCHAR:
            result = Integer.parseInt(getString(o, oi));
            break;
         case DATE:
         case INTERVAL_YEAR_MONTH:
         case INTERVAL_DAY_TIME:
         case BINARY:
         default:
            throw new RuntimeException("Hive 2 Internal error: unsupported conversion from type: " + oi.getTypeName());
         case TIMESTAMP:
            result = (int)((TimestampObjectInspector)oi).getPrimitiveWritableObject(o).getSeconds();
            break;
         case DECIMAL:
            HiveDecimal dec = ((HiveDecimalObjectInspector)oi).getPrimitiveJavaObject(o);
            if (!dec.isInt()) {
               throw new NumberFormatException();
            }

            result = dec.intValue();
            break;
         case VOID:
            result = 0;
      }

      return result;
   }

   public static long getLong(Object o, PrimitiveObjectInspector oi) {
      long result = 0L;
      switch (oi.getPrimitiveCategory()) {
         case BOOLEAN:
            result = (long)(((BooleanObjectInspector)oi).get(o) ? 1 : 0);
            break;
         case BYTE:
            result = (long)((ByteObjectInspector)oi).get(o);
            break;
         case SHORT:
            result = (long)((ShortObjectInspector)oi).get(o);
            break;
         case INT:
            result = (long)((IntObjectInspector)oi).get(o);
            break;
         case LONG:
            result = ((LongObjectInspector)oi).get(o);
            break;
         case FLOAT:
            result = (long)((FloatObjectInspector)oi).get(o);
            break;
         case DOUBLE:
            result = (long)((DoubleObjectInspector)oi).get(o);
            break;
         case STRING:
            StringObjectInspector soi = (StringObjectInspector)oi;
            if (soi.preferWritable()) {
               Text t = soi.getPrimitiveWritableObject(o);
               result = LazyLong.parseLong(t.getBytes(), 0, t.getLength());
            } else {
               String s = soi.getPrimitiveJavaObject(o);
               result = Long.parseLong(s);
            }
            break;
         case CHAR:
         case VARCHAR:
            result = Long.parseLong(getString(o, oi));
            break;
         case DATE:
         case INTERVAL_YEAR_MONTH:
         case INTERVAL_DAY_TIME:
         case BINARY:
         default:
            throw new RuntimeException("Hive 2 Internal error: unsupported conversion from type: " + oi.getTypeName());
         case TIMESTAMP:
            result = ((TimestampObjectInspector)oi).getPrimitiveWritableObject(o).getSeconds();
            break;
         case DECIMAL:
            HiveDecimal dec = ((HiveDecimalObjectInspector)oi).getPrimitiveJavaObject(o);
            if (!dec.isLong()) {
               throw new NumberFormatException();
            }

            result = dec.longValue();
            break;
         case VOID:
            result = 0L;
      }

      return result;
   }

   public static double getDouble(Object o, PrimitiveObjectInspector oi) {
      double result = (double)0.0F;
      switch (oi.getPrimitiveCategory()) {
         case BOOLEAN:
            result = (double)(((BooleanObjectInspector)oi).get(o) ? 1 : 0);
            break;
         case BYTE:
            result = (double)((ByteObjectInspector)oi).get(o);
            break;
         case SHORT:
            result = (double)((ShortObjectInspector)oi).get(o);
            break;
         case INT:
            result = (double)((IntObjectInspector)oi).get(o);
            break;
         case LONG:
            result = (double)((LongObjectInspector)oi).get(o);
            break;
         case FLOAT:
            result = (double)((FloatObjectInspector)oi).get(o);
            break;
         case DOUBLE:
            result = ((DoubleObjectInspector)oi).get(o);
            break;
         case STRING:
            StringObjectInspector soi = (StringObjectInspector)oi;
            String s = soi.getPrimitiveJavaObject(o);
            result = Double.parseDouble(s);
            break;
         case CHAR:
         case VARCHAR:
            result = Double.parseDouble(getString(o, oi));
            break;
         case DATE:
         case INTERVAL_YEAR_MONTH:
         case INTERVAL_DAY_TIME:
         case BINARY:
         default:
            throw new RuntimeException("Hive 2 Internal error: unsupported conversion from type: " + oi.getTypeName());
         case TIMESTAMP:
            result = ((TimestampObjectInspector)oi).getPrimitiveWritableObject(o).getDouble();
            break;
         case DECIMAL:
            result = ((HiveDecimalObjectInspector)oi).getPrimitiveJavaObject(o).doubleValue();
            break;
         case VOID:
            result = (double)0.0F;
      }

      return result;
   }

   public static float getFloat(Object o, PrimitiveObjectInspector oi) {
      return (float)getDouble(o, oi);
   }

   public static String getString(Object o, PrimitiveObjectInspector oi) {
      if (o == null) {
         return null;
      } else {
         String result = null;
         switch (oi.getPrimitiveCategory()) {
            case BOOLEAN:
               result = String.valueOf(((BooleanObjectInspector)oi).get(o));
               break;
            case BYTE:
               result = String.valueOf(((ByteObjectInspector)oi).get(o));
               break;
            case SHORT:
               result = String.valueOf(((ShortObjectInspector)oi).get(o));
               break;
            case INT:
               result = String.valueOf(((IntObjectInspector)oi).get(o));
               break;
            case LONG:
               result = String.valueOf(((LongObjectInspector)oi).get(o));
               break;
            case FLOAT:
               result = String.valueOf(((FloatObjectInspector)oi).get(o));
               break;
            case DOUBLE:
               result = String.valueOf(((DoubleObjectInspector)oi).get(o));
               break;
            case STRING:
               StringObjectInspector soi = (StringObjectInspector)oi;
               result = soi.getPrimitiveJavaObject(o);
               break;
            case CHAR:
               result = ((HiveCharObjectInspector)oi).getPrimitiveJavaObject(o).getStrippedValue();
               break;
            case VARCHAR:
               HiveVarcharObjectInspector hcoi = (HiveVarcharObjectInspector)oi;
               result = hcoi.getPrimitiveJavaObject(o).toString();
               break;
            case DATE:
               result = ((DateObjectInspector)oi).getPrimitiveWritableObject(o).toString();
               break;
            case TIMESTAMP:
               result = ((TimestampObjectInspector)oi).getPrimitiveWritableObject(o).toString();
               break;
            case INTERVAL_YEAR_MONTH:
               result = ((HiveIntervalYearMonthObjectInspector)oi).getPrimitiveWritableObject(o).toString();
               break;
            case INTERVAL_DAY_TIME:
               result = ((HiveIntervalDayTimeObjectInspector)oi).getPrimitiveWritableObject(o).toString();
               break;
            case BINARY:
               try {
                  byte[] bytes = ((BinaryObjectInspector)oi).getPrimitiveWritableObject(o).getBytes();
                  int byteLen = ((BinaryObjectInspector)oi).getPrimitiveWritableObject(o).getLength();
                  result = Text.decode(bytes, 0, byteLen);
               } catch (CharacterCodingException var5) {
                  result = null;
               }
               break;
            case DECIMAL:
               result = ((HiveDecimalObjectInspector)oi).getPrimitiveJavaObject(o).toString();
               break;
            case VOID:
               result = null;
               break;
            default:
               throw new RuntimeException("Hive 2 Internal error: unknown type: " + oi.getTypeName());
         }

         return result;
      }
   }

   public static HiveChar getHiveChar(Object o, PrimitiveObjectInspector oi) {
      if (o == null) {
         return null;
      } else {
         HiveChar result = null;
         switch (oi.getPrimitiveCategory()) {
            case CHAR:
               result = ((HiveCharObjectInspector)oi).getPrimitiveJavaObject(o);
               break;
            default:
               result = new HiveChar();
               result.setValue(getString(o, oi));
         }

         return result;
      }
   }

   public static HiveVarchar getHiveVarchar(Object o, PrimitiveObjectInspector oi) {
      if (o == null) {
         return null;
      } else {
         HiveVarchar result = null;
         switch (oi.getPrimitiveCategory()) {
            case VARCHAR:
               result = ((HiveVarcharObjectInspector)oi).getPrimitiveJavaObject(o);
               break;
            default:
               result = new HiveVarchar();
               result.setValue(getString(o, oi));
         }

         return result;
      }
   }

   public static BytesWritable getBinaryFromText(Text text) {
      BytesWritable bw = new BytesWritable();
      bw.set(text.getBytes(), 0, text.getLength());
      return bw;
   }

   public static BytesWritable getBinary(Object o, PrimitiveObjectInspector oi) {
      if (null == o) {
         return null;
      } else {
         switch (oi.getPrimitiveCategory()) {
            case STRING:
               Text text = ((StringObjectInspector)oi).getPrimitiveWritableObject(o);
               return getBinaryFromText(text);
            case CHAR:
               return getBinaryFromText(((HiveCharObjectInspector)oi).getPrimitiveWritableObject(o).getPaddedValue());
            case VARCHAR:
               return getBinaryFromText(((HiveVarcharObjectInspector)oi).getPrimitiveWritableObject(o).getTextValue());
            case DATE:
            case TIMESTAMP:
            case INTERVAL_YEAR_MONTH:
            case INTERVAL_DAY_TIME:
            case DECIMAL:
            default:
               throw new RuntimeException("Cannot convert to Binary from: " + oi.getTypeName());
            case BINARY:
               return ((BinaryObjectInspector)oi).getPrimitiveWritableObject(o);
            case VOID:
               return null;
         }
      }
   }

   public static HiveDecimal getHiveDecimal(Object o, PrimitiveObjectInspector oi) {
      if (o == null) {
         return null;
      } else {
         HiveDecimal result = null;
         switch (oi.getPrimitiveCategory()) {
            case BOOLEAN:
               result = ((BooleanObjectInspector)oi).get(o) ? HiveDecimal.ONE : HiveDecimal.ZERO;
               break;
            case BYTE:
               result = HiveDecimal.create(((ByteObjectInspector)oi).get(o));
               break;
            case SHORT:
               result = HiveDecimal.create(((ShortObjectInspector)oi).get(o));
               break;
            case INT:
               result = HiveDecimal.create(((IntObjectInspector)oi).get(o));
               break;
            case LONG:
               result = HiveDecimal.create(((LongObjectInspector)oi).get(o));
               break;
            case FLOAT:
               Float f = ((FloatObjectInspector)oi).get(o);
               result = HiveDecimal.create(f.toString());
               break;
            case DOUBLE:
               Double d = ((DoubleObjectInspector)oi).get(o);
               result = HiveDecimal.create(d.toString());
               break;
            case STRING:
               result = HiveDecimal.create(((StringObjectInspector)oi).getPrimitiveJavaObject(o));
               break;
            case CHAR:
            case VARCHAR:
               result = HiveDecimal.create(getString(o, oi));
               break;
            case DATE:
            case INTERVAL_YEAR_MONTH:
            case INTERVAL_DAY_TIME:
            case BINARY:
            default:
               throw new RuntimeException("Hive 2 Internal error: unsupported conversion from type: " + oi.getTypeName());
            case TIMESTAMP:
               Double ts = ((TimestampObjectInspector)oi).getPrimitiveWritableObject(o).getDouble();
               result = HiveDecimal.create(ts.toString());
               break;
            case DECIMAL:
               result = ((HiveDecimalObjectInspector)oi).getPrimitiveJavaObject(o);
               break;
            case VOID:
               result = null;
         }

         return result;
      }
   }

   public static Date getDate(Object o, PrimitiveObjectInspector oi) {
      if (o == null) {
         return null;
      } else {
         Date result = null;
         switch (oi.getPrimitiveCategory()) {
            case STRING:
               StringObjectInspector soi = (StringObjectInspector)oi;
               String s = soi.getPrimitiveJavaObject(o).trim();

               try {
                  result = Date.valueOf(s);
               } catch (IllegalArgumentException var9) {
                  Timestamp ts = getTimestampFromString(s);
                  if (ts != null) {
                     result = new Date(ts.getTime());
                  } else {
                     result = null;
                  }
               }
               break;
            case CHAR:
            case VARCHAR:
               String val = getString(o, oi).trim();

               try {
                  result = Date.valueOf(val);
               } catch (IllegalArgumentException var8) {
                  Timestamp ts = getTimestampFromString(val);
                  if (ts != null) {
                     result = new Date(ts.getTime());
                  } else {
                     result = null;
                  }
               }
               break;
            case DATE:
               result = ((DateObjectInspector)oi).getPrimitiveWritableObject(o).get();
               break;
            case TIMESTAMP:
               result = DateWritable.timeToDate(((TimestampObjectInspector)oi).getPrimitiveWritableObject(o).getSeconds());
               break;
            case INTERVAL_YEAR_MONTH:
            case INTERVAL_DAY_TIME:
            case BINARY:
            case DECIMAL:
            default:
               throw new RuntimeException("Cannot convert to Date from: " + oi.getTypeName());
            case VOID:
               result = null;
         }

         return result;
      }
   }

   public static Timestamp getTimestamp(Object o, PrimitiveObjectInspector oi) {
      return getTimestamp(o, oi, false);
   }

   public static Timestamp getTimestamp(Object o, PrimitiveObjectInspector inputOI, boolean intToTimestampInSeconds) {
      if (o == null) {
         return null;
      } else {
         Timestamp result = null;
         long longValue = 0L;
         switch (inputOI.getPrimitiveCategory()) {
            case BOOLEAN:
               longValue = ((BooleanObjectInspector)inputOI).get(o) ? 1L : 0L;
               result = TimestampWritable.longToTimestamp(longValue, intToTimestampInSeconds);
               break;
            case BYTE:
               longValue = (long)((ByteObjectInspector)inputOI).get(o);
               result = TimestampWritable.longToTimestamp(longValue, intToTimestampInSeconds);
               break;
            case SHORT:
               longValue = (long)((ShortObjectInspector)inputOI).get(o);
               result = TimestampWritable.longToTimestamp(longValue, intToTimestampInSeconds);
               break;
            case INT:
               longValue = (long)((IntObjectInspector)inputOI).get(o);
               result = TimestampWritable.longToTimestamp(longValue, intToTimestampInSeconds);
               break;
            case LONG:
               longValue = ((LongObjectInspector)inputOI).get(o);
               result = TimestampWritable.longToTimestamp(longValue, intToTimestampInSeconds);
               break;
            case FLOAT:
               result = TimestampUtils.doubleToTimestamp((double)((FloatObjectInspector)inputOI).get(o));
               break;
            case DOUBLE:
               result = TimestampUtils.doubleToTimestamp(((DoubleObjectInspector)inputOI).get(o));
               break;
            case STRING:
               StringObjectInspector soi = (StringObjectInspector)inputOI;
               String s = soi.getPrimitiveJavaObject(o);
               result = getTimestampFromString(s);
               break;
            case CHAR:
            case VARCHAR:
               result = getTimestampFromString(getString(o, inputOI));
               break;
            case DATE:
               result = new Timestamp(((DateObjectInspector)inputOI).getPrimitiveWritableObject(o).get().getTime());
               break;
            case TIMESTAMP:
               result = ((TimestampObjectInspector)inputOI).getPrimitiveWritableObject(o).getTimestamp();
               break;
            case INTERVAL_YEAR_MONTH:
            case INTERVAL_DAY_TIME:
            case BINARY:
            default:
               throw new RuntimeException("Hive 2 Internal error: unknown type: " + inputOI.getTypeName());
            case DECIMAL:
               result = TimestampUtils.decimalToTimestamp(((HiveDecimalObjectInspector)inputOI).getPrimitiveJavaObject(o));
               break;
            case VOID:
               result = null;
         }

         return result;
      }
   }

   static Timestamp getTimestampFromString(String s) {
      s = s.trim();
      int periodIdx = s.indexOf(".");
      if (periodIdx != -1 && s.length() - periodIdx > 9) {
         s = s.substring(0, periodIdx + 10);
      }

      if (s.indexOf(32) < 0) {
         s = s.concat(" 00:00:00");
      }

      Timestamp result;
      try {
         result = Timestamp.valueOf(s);
      } catch (IllegalArgumentException var4) {
         result = null;
      }

      return result;
   }

   public static HiveIntervalYearMonth getHiveIntervalYearMonth(Object o, PrimitiveObjectInspector oi) {
      if (o == null) {
         return null;
      } else {
         HiveIntervalYearMonth result = null;
         switch (oi.getPrimitiveCategory()) {
            case STRING:
            case CHAR:
            case VARCHAR:
               try {
                  String val = getString(o, oi).trim();
                  result = HiveIntervalYearMonth.valueOf(val);
               } catch (IllegalArgumentException var4) {
                  result = null;
               }
               break;
            case DATE:
            case TIMESTAMP:
            case INTERVAL_DAY_TIME:
            case BINARY:
            case DECIMAL:
            default:
               throw new RuntimeException("Cannot convert to IntervalYearMonth from: " + oi.getTypeName());
            case INTERVAL_YEAR_MONTH:
               result = ((HiveIntervalYearMonthObjectInspector)oi).getPrimitiveJavaObject(o);
               break;
            case VOID:
               result = null;
         }

         return result;
      }
   }

   public static HiveIntervalDayTime getHiveIntervalDayTime(Object o, PrimitiveObjectInspector oi) {
      if (o == null) {
         return null;
      } else {
         HiveIntervalDayTime result = null;
         switch (oi.getPrimitiveCategory()) {
            case STRING:
            case CHAR:
            case VARCHAR:
               try {
                  String val = getString(o, oi).trim();
                  result = HiveIntervalDayTime.valueOf(val);
               } catch (IllegalArgumentException var4) {
                  result = null;
               }
               break;
            case DATE:
            case TIMESTAMP:
            case INTERVAL_YEAR_MONTH:
            case BINARY:
            case DECIMAL:
            default:
               throw new RuntimeException("Cannot convert to IntervalDayTime from: " + oi.getTypeName());
            case INTERVAL_DAY_TIME:
               result = ((HiveIntervalDayTimeObjectInspector)oi).getPrimitiveJavaObject(o);
               break;
            case VOID:
               result = null;
         }

         return result;
      }
   }

   public static Class getJavaPrimitiveClassFromObjectInspector(ObjectInspector oi) {
      if (oi.getCategory() != ObjectInspector.Category.PRIMITIVE) {
         return null;
      } else {
         PrimitiveObjectInspector poi = (PrimitiveObjectInspector)oi;
         PrimitiveTypeEntry t = getTypeEntryFromPrimitiveCategory(poi.getPrimitiveCategory());
         return t == null ? null : t.primitiveJavaClass;
      }
   }

   public static PrimitiveGrouping getPrimitiveGrouping(PrimitiveObjectInspector.PrimitiveCategory primitiveCategory) {
      switch (primitiveCategory) {
         case BOOLEAN:
            return PrimitiveObjectInspectorUtils.PrimitiveGrouping.BOOLEAN_GROUP;
         case BYTE:
         case SHORT:
         case INT:
         case LONG:
         case FLOAT:
         case DOUBLE:
         case DECIMAL:
            return PrimitiveObjectInspectorUtils.PrimitiveGrouping.NUMERIC_GROUP;
         case STRING:
         case CHAR:
         case VARCHAR:
            return PrimitiveObjectInspectorUtils.PrimitiveGrouping.STRING_GROUP;
         case DATE:
         case TIMESTAMP:
            return PrimitiveObjectInspectorUtils.PrimitiveGrouping.DATE_GROUP;
         case INTERVAL_YEAR_MONTH:
         case INTERVAL_DAY_TIME:
            return PrimitiveObjectInspectorUtils.PrimitiveGrouping.INTERVAL_GROUP;
         case BINARY:
            return PrimitiveObjectInspectorUtils.PrimitiveGrouping.BINARY_GROUP;
         case VOID:
            return PrimitiveObjectInspectorUtils.PrimitiveGrouping.VOID_GROUP;
         default:
            return PrimitiveObjectInspectorUtils.PrimitiveGrouping.UNKNOWN_GROUP;
      }
   }

   private PrimitiveObjectInspectorUtils() {
   }

   static {
      binaryTypeEntry = new PrimitiveTypeEntry(PrimitiveObjectInspector.PrimitiveCategory.BINARY, "binary", byte[].class, byte[].class, BytesWritable.class);
      stringTypeEntry = new PrimitiveTypeEntry(PrimitiveObjectInspector.PrimitiveCategory.STRING, "string", (Class)null, String.class, Text.class);
      booleanTypeEntry = new PrimitiveTypeEntry(PrimitiveObjectInspector.PrimitiveCategory.BOOLEAN, "boolean", Boolean.TYPE, Boolean.class, BooleanWritable.class);
      intTypeEntry = new PrimitiveTypeEntry(PrimitiveObjectInspector.PrimitiveCategory.INT, "int", Integer.TYPE, Integer.class, IntWritable.class);
      longTypeEntry = new PrimitiveTypeEntry(PrimitiveObjectInspector.PrimitiveCategory.LONG, "bigint", Long.TYPE, Long.class, LongWritable.class);
      floatTypeEntry = new PrimitiveTypeEntry(PrimitiveObjectInspector.PrimitiveCategory.FLOAT, "float", Float.TYPE, Float.class, FloatWritable.class);
      voidTypeEntry = new PrimitiveTypeEntry(PrimitiveObjectInspector.PrimitiveCategory.VOID, "void", Void.TYPE, Void.class, NullWritable.class);
      doubleTypeEntry = new PrimitiveTypeEntry(PrimitiveObjectInspector.PrimitiveCategory.DOUBLE, "double", Double.TYPE, Double.class, DoubleWritable.class);
      byteTypeEntry = new PrimitiveTypeEntry(PrimitiveObjectInspector.PrimitiveCategory.BYTE, "tinyint", Byte.TYPE, Byte.class, ByteWritable.class);
      shortTypeEntry = new PrimitiveTypeEntry(PrimitiveObjectInspector.PrimitiveCategory.SHORT, "smallint", Short.TYPE, Short.class, ShortWritable.class);
      dateTypeEntry = new PrimitiveTypeEntry(PrimitiveObjectInspector.PrimitiveCategory.DATE, "date", (Class)null, Date.class, DateWritable.class);
      timestampTypeEntry = new PrimitiveTypeEntry(PrimitiveObjectInspector.PrimitiveCategory.TIMESTAMP, "timestamp", (Class)null, Timestamp.class, TimestampWritable.class);
      intervalYearMonthTypeEntry = new PrimitiveTypeEntry(PrimitiveObjectInspector.PrimitiveCategory.INTERVAL_YEAR_MONTH, "interval_year_month", (Class)null, HiveIntervalYearMonth.class, HiveIntervalYearMonthWritable.class);
      intervalDayTimeTypeEntry = new PrimitiveTypeEntry(PrimitiveObjectInspector.PrimitiveCategory.INTERVAL_DAY_TIME, "interval_day_time", (Class)null, HiveIntervalDayTime.class, HiveIntervalDayTimeWritable.class);
      decimalTypeEntry = new PrimitiveTypeEntry(PrimitiveObjectInspector.PrimitiveCategory.DECIMAL, "decimal", (Class)null, HiveDecimal.class, HiveDecimalWritable.class);
      varcharTypeEntry = new PrimitiveTypeEntry(PrimitiveObjectInspector.PrimitiveCategory.VARCHAR, "varchar", (Class)null, HiveVarchar.class, HiveVarcharWritable.class);
      charTypeEntry = new PrimitiveTypeEntry(PrimitiveObjectInspector.PrimitiveCategory.CHAR, "char", (Class)null, HiveChar.class, HiveCharWritable.class);
      unknownTypeEntry = new PrimitiveTypeEntry(PrimitiveObjectInspector.PrimitiveCategory.UNKNOWN, "unknown", (Class)null, Object.class, (Class)null);
      registerType(binaryTypeEntry);
      registerType(stringTypeEntry);
      registerType(charTypeEntry);
      registerType(varcharTypeEntry);
      registerType(booleanTypeEntry);
      registerType(intTypeEntry);
      registerType(longTypeEntry);
      registerType(floatTypeEntry);
      registerType(voidTypeEntry);
      registerType(doubleTypeEntry);
      registerType(byteTypeEntry);
      registerType(shortTypeEntry);
      registerType(dateTypeEntry);
      registerType(timestampTypeEntry);
      registerType(intervalYearMonthTypeEntry);
      registerType(intervalDayTimeTypeEntry);
      registerType(decimalTypeEntry);
      registerType(unknownTypeEntry);
   }

   public static class PrimitiveTypeEntry implements Writable, Cloneable {
      public PrimitiveObjectInspector.PrimitiveCategory primitiveCategory;
      public Class primitiveJavaType;
      public Class primitiveJavaClass;
      public Class primitiveWritableClass;
      public String typeName;

      protected PrimitiveTypeEntry() {
      }

      PrimitiveTypeEntry(PrimitiveObjectInspector.PrimitiveCategory primitiveCategory, String typeName, Class primitiveType, Class javaClass, Class hiveClass) {
         this.primitiveCategory = primitiveCategory;
         this.primitiveJavaType = primitiveType;
         this.primitiveJavaClass = javaClass;
         this.primitiveWritableClass = hiveClass;
         this.typeName = typeName;
      }

      public void readFields(DataInput in) throws IOException {
         this.primitiveCategory = (PrimitiveObjectInspector.PrimitiveCategory)WritableUtils.readEnum(in, PrimitiveObjectInspector.PrimitiveCategory.class);
         this.typeName = WritableUtils.readString(in);

         try {
            this.primitiveJavaType = Class.forName(WritableUtils.readString(in));
            this.primitiveJavaClass = Class.forName(WritableUtils.readString(in));
            this.primitiveWritableClass = Class.forName(WritableUtils.readString(in));
         } catch (ClassNotFoundException e) {
            throw new IOException(e);
         }
      }

      public void write(DataOutput out) throws IOException {
         WritableUtils.writeEnum(out, this.primitiveCategory);
         WritableUtils.writeString(out, this.typeName);
         WritableUtils.writeString(out, this.primitiveJavaType.getName());
         WritableUtils.writeString(out, this.primitiveJavaClass.getName());
         WritableUtils.writeString(out, this.primitiveWritableClass.getName());
      }

      public Object clone() {
         PrimitiveTypeEntry result = new PrimitiveTypeEntry(this.primitiveCategory, this.typeName, this.primitiveJavaType, this.primitiveJavaClass, this.primitiveWritableClass);
         return result;
      }

      public String toString() {
         return this.typeName;
      }
   }

   public static enum PrimitiveGrouping {
      NUMERIC_GROUP,
      STRING_GROUP,
      BOOLEAN_GROUP,
      DATE_GROUP,
      INTERVAL_GROUP,
      BINARY_GROUP,
      VOID_GROUP,
      UNKNOWN_GROUP;
   }
}
