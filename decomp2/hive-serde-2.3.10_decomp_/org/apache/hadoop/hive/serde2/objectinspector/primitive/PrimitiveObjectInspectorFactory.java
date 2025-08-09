package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import java.util.EnumMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
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
import org.apache.hadoop.hive.serde2.objectinspector.ConstantObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;
import org.apache.hadoop.hive.serde2.typeinfo.CharTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.DecimalTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.PrimitiveTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoFactory;
import org.apache.hadoop.hive.serde2.typeinfo.VarcharTypeInfo;
import org.apache.hadoop.io.BooleanWritable;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;

public final class PrimitiveObjectInspectorFactory {
   public static final WritableBooleanObjectInspector writableBooleanObjectInspector = new WritableBooleanObjectInspector();
   public static final WritableByteObjectInspector writableByteObjectInspector = new WritableByteObjectInspector();
   public static final WritableShortObjectInspector writableShortObjectInspector = new WritableShortObjectInspector();
   public static final WritableIntObjectInspector writableIntObjectInspector = new WritableIntObjectInspector();
   public static final WritableLongObjectInspector writableLongObjectInspector = new WritableLongObjectInspector();
   public static final WritableFloatObjectInspector writableFloatObjectInspector = new WritableFloatObjectInspector();
   public static final WritableDoubleObjectInspector writableDoubleObjectInspector = new WritableDoubleObjectInspector();
   public static final WritableStringObjectInspector writableStringObjectInspector = new WritableStringObjectInspector();
   public static final WritableHiveCharObjectInspector writableHiveCharObjectInspector;
   public static final WritableHiveVarcharObjectInspector writableHiveVarcharObjectInspector;
   public static final WritableVoidObjectInspector writableVoidObjectInspector;
   public static final WritableDateObjectInspector writableDateObjectInspector;
   public static final WritableTimestampObjectInspector writableTimestampObjectInspector;
   public static final WritableHiveIntervalYearMonthObjectInspector writableHiveIntervalYearMonthObjectInspector;
   public static final WritableHiveIntervalDayTimeObjectInspector writableHiveIntervalDayTimeObjectInspector;
   public static final WritableBinaryObjectInspector writableBinaryObjectInspector;
   public static final WritableHiveDecimalObjectInspector writableHiveDecimalObjectInspector;
   private static ConcurrentHashMap cachedPrimitiveWritableInspectorCache;
   private static Map primitiveCategoryToWritableOI;
   public static final JavaBooleanObjectInspector javaBooleanObjectInspector;
   public static final JavaByteObjectInspector javaByteObjectInspector;
   public static final JavaShortObjectInspector javaShortObjectInspector;
   public static final JavaIntObjectInspector javaIntObjectInspector;
   public static final JavaLongObjectInspector javaLongObjectInspector;
   public static final JavaFloatObjectInspector javaFloatObjectInspector;
   public static final JavaDoubleObjectInspector javaDoubleObjectInspector;
   public static final JavaStringObjectInspector javaStringObjectInspector;
   public static final JavaHiveCharObjectInspector javaHiveCharObjectInspector;
   public static final JavaHiveVarcharObjectInspector javaHiveVarcharObjectInspector;
   public static final JavaVoidObjectInspector javaVoidObjectInspector;
   public static final JavaDateObjectInspector javaDateObjectInspector;
   public static final JavaTimestampObjectInspector javaTimestampObjectInspector;
   public static final JavaHiveIntervalYearMonthObjectInspector javaHiveIntervalYearMonthObjectInspector;
   public static final JavaHiveIntervalDayTimeObjectInspector javaHiveIntervalDayTimeObjectInspector;
   public static final JavaBinaryObjectInspector javaByteArrayObjectInspector;
   public static final JavaHiveDecimalObjectInspector javaHiveDecimalObjectInspector;
   private static ConcurrentHashMap cachedPrimitiveJavaInspectorCache;
   private static Map primitiveCategoryToJavaOI;

   public static AbstractPrimitiveWritableObjectInspector getPrimitiveWritableObjectInspector(PrimitiveObjectInspector.PrimitiveCategory primitiveCategory) {
      AbstractPrimitiveWritableObjectInspector result = (AbstractPrimitiveWritableObjectInspector)primitiveCategoryToWritableOI.get(primitiveCategory);
      if (result == null) {
         throw new RuntimeException("Internal error: Cannot find ObjectInspector  for " + primitiveCategory);
      } else {
         return result;
      }
   }

   public static AbstractPrimitiveWritableObjectInspector getPrimitiveWritableObjectInspector(PrimitiveTypeInfo typeInfo) {
      AbstractPrimitiveWritableObjectInspector result = (AbstractPrimitiveWritableObjectInspector)cachedPrimitiveWritableInspectorCache.get(typeInfo);
      if (result != null) {
         return result;
      } else {
         switch (typeInfo.getPrimitiveCategory()) {
            case CHAR:
               result = new WritableHiveCharObjectInspector((CharTypeInfo)typeInfo);
               break;
            case VARCHAR:
               result = new WritableHiveVarcharObjectInspector((VarcharTypeInfo)typeInfo);
               break;
            case DECIMAL:
               result = new WritableHiveDecimalObjectInspector((DecimalTypeInfo)typeInfo);
               break;
            default:
               throw new RuntimeException("Failed to create object inspector for " + typeInfo);
         }

         AbstractPrimitiveWritableObjectInspector prev = (AbstractPrimitiveWritableObjectInspector)cachedPrimitiveWritableInspectorCache.putIfAbsent(typeInfo, result);
         if (prev != null) {
            result = prev;
         }

         return result;
      }
   }

   public static ConstantObjectInspector getPrimitiveWritableConstantObjectInspector(PrimitiveTypeInfo typeInfo, Object value) {
      switch (typeInfo.getPrimitiveCategory()) {
         case CHAR:
            return new WritableConstantHiveCharObjectInspector((CharTypeInfo)typeInfo, (HiveCharWritable)value);
         case VARCHAR:
            return new WritableConstantHiveVarcharObjectInspector((VarcharTypeInfo)typeInfo, (HiveVarcharWritable)value);
         case DECIMAL:
            return new WritableConstantHiveDecimalObjectInspector((DecimalTypeInfo)typeInfo, (HiveDecimalWritable)value);
         case BOOLEAN:
            return new WritableConstantBooleanObjectInspector((BooleanWritable)value);
         case BYTE:
            return new WritableConstantByteObjectInspector((ByteWritable)value);
         case SHORT:
            return new WritableConstantShortObjectInspector((ShortWritable)value);
         case INT:
            return new WritableConstantIntObjectInspector((IntWritable)value);
         case LONG:
            return new WritableConstantLongObjectInspector((LongWritable)value);
         case FLOAT:
            return new WritableConstantFloatObjectInspector((FloatWritable)value);
         case DOUBLE:
            return new WritableConstantDoubleObjectInspector((DoubleWritable)value);
         case STRING:
            return new WritableConstantStringObjectInspector((Text)value);
         case DATE:
            return new WritableConstantDateObjectInspector((DateWritable)value);
         case TIMESTAMP:
            return new WritableConstantTimestampObjectInspector((TimestampWritable)value);
         case INTERVAL_YEAR_MONTH:
            return new WritableConstantHiveIntervalYearMonthObjectInspector((HiveIntervalYearMonthWritable)value);
         case INTERVAL_DAY_TIME:
            return new WritableConstantHiveIntervalDayTimeObjectInspector((HiveIntervalDayTimeWritable)value);
         case BINARY:
            return new WritableConstantBinaryObjectInspector((BytesWritable)value);
         case VOID:
            return new WritableVoidObjectInspector();
         default:
            throw new RuntimeException("Internal error: Cannot find ConstantObjectInspector for " + typeInfo);
      }
   }

   public static AbstractPrimitiveJavaObjectInspector getPrimitiveJavaObjectInspector(PrimitiveObjectInspector.PrimitiveCategory primitiveCategory) {
      AbstractPrimitiveJavaObjectInspector result = (AbstractPrimitiveJavaObjectInspector)primitiveCategoryToJavaOI.get(primitiveCategory);
      if (result == null) {
         throw new RuntimeException("Internal error: Cannot find ObjectInspector  for " + primitiveCategory);
      } else {
         return result;
      }
   }

   public static AbstractPrimitiveJavaObjectInspector getPrimitiveJavaObjectInspector(PrimitiveTypeInfo typeInfo) {
      AbstractPrimitiveJavaObjectInspector result = (AbstractPrimitiveJavaObjectInspector)cachedPrimitiveJavaInspectorCache.get(typeInfo);
      if (result != null) {
         return result;
      } else {
         switch (typeInfo.getPrimitiveCategory()) {
            case CHAR:
               result = new JavaHiveCharObjectInspector((CharTypeInfo)typeInfo);
               break;
            case VARCHAR:
               result = new JavaHiveVarcharObjectInspector((VarcharTypeInfo)typeInfo);
               break;
            case DECIMAL:
               result = new JavaHiveDecimalObjectInspector((DecimalTypeInfo)typeInfo);
               break;
            default:
               throw new RuntimeException("Failed to create Java ObjectInspector for " + typeInfo);
         }

         AbstractPrimitiveJavaObjectInspector prev = (AbstractPrimitiveJavaObjectInspector)cachedPrimitiveJavaInspectorCache.putIfAbsent(typeInfo, result);
         if (prev != null) {
            result = prev;
         }

         return result;
      }
   }

   public static PrimitiveObjectInspector getPrimitiveObjectInspectorFromClass(Class c) {
      if (Writable.class.isAssignableFrom(c)) {
         PrimitiveObjectInspectorUtils.PrimitiveTypeEntry te = PrimitiveObjectInspectorUtils.getTypeEntryFromPrimitiveWritableClass(c);
         if (te == null) {
            throw new RuntimeException("Internal error: Cannot recognize " + c);
         } else {
            return getPrimitiveWritableObjectInspector(te.primitiveCategory);
         }
      } else {
         PrimitiveObjectInspectorUtils.PrimitiveTypeEntry te = PrimitiveObjectInspectorUtils.getTypeEntryFromPrimitiveJavaClass(c);
         if (te == null) {
            throw new RuntimeException("Internal error: Cannot recognize " + c);
         } else {
            return getPrimitiveJavaObjectInspector(te.primitiveCategory);
         }
      }
   }

   private PrimitiveObjectInspectorFactory() {
   }

   static {
      writableHiveCharObjectInspector = new WritableHiveCharObjectInspector((CharTypeInfo)TypeInfoFactory.charTypeInfo);
      writableHiveVarcharObjectInspector = new WritableHiveVarcharObjectInspector((VarcharTypeInfo)TypeInfoFactory.varcharTypeInfo);
      writableVoidObjectInspector = new WritableVoidObjectInspector();
      writableDateObjectInspector = new WritableDateObjectInspector();
      writableTimestampObjectInspector = new WritableTimestampObjectInspector();
      writableHiveIntervalYearMonthObjectInspector = new WritableHiveIntervalYearMonthObjectInspector();
      writableHiveIntervalDayTimeObjectInspector = new WritableHiveIntervalDayTimeObjectInspector();
      writableBinaryObjectInspector = new WritableBinaryObjectInspector();
      writableHiveDecimalObjectInspector = new WritableHiveDecimalObjectInspector(TypeInfoFactory.decimalTypeInfo);
      cachedPrimitiveWritableInspectorCache = new ConcurrentHashMap();
      cachedPrimitiveWritableInspectorCache.put(TypeInfoFactory.getPrimitiveTypeInfo("boolean"), writableBooleanObjectInspector);
      cachedPrimitiveWritableInspectorCache.put(TypeInfoFactory.getPrimitiveTypeInfo("tinyint"), writableByteObjectInspector);
      cachedPrimitiveWritableInspectorCache.put(TypeInfoFactory.getPrimitiveTypeInfo("smallint"), writableShortObjectInspector);
      cachedPrimitiveWritableInspectorCache.put(TypeInfoFactory.getPrimitiveTypeInfo("int"), writableIntObjectInspector);
      cachedPrimitiveWritableInspectorCache.put(TypeInfoFactory.getPrimitiveTypeInfo("bigint"), writableLongObjectInspector);
      cachedPrimitiveWritableInspectorCache.put(TypeInfoFactory.getPrimitiveTypeInfo("float"), writableFloatObjectInspector);
      cachedPrimitiveWritableInspectorCache.put(TypeInfoFactory.getPrimitiveTypeInfo("double"), writableDoubleObjectInspector);
      cachedPrimitiveWritableInspectorCache.put(TypeInfoFactory.getPrimitiveTypeInfo("string"), writableStringObjectInspector);
      cachedPrimitiveWritableInspectorCache.put(TypeInfoFactory.charTypeInfo, writableHiveCharObjectInspector);
      cachedPrimitiveWritableInspectorCache.put(TypeInfoFactory.varcharTypeInfo, writableHiveVarcharObjectInspector);
      cachedPrimitiveWritableInspectorCache.put(TypeInfoFactory.getPrimitiveTypeInfo("void"), writableVoidObjectInspector);
      cachedPrimitiveWritableInspectorCache.put(TypeInfoFactory.getPrimitiveTypeInfo("date"), writableDateObjectInspector);
      cachedPrimitiveWritableInspectorCache.put(TypeInfoFactory.getPrimitiveTypeInfo("timestamp"), writableTimestampObjectInspector);
      cachedPrimitiveWritableInspectorCache.put(TypeInfoFactory.getPrimitiveTypeInfo("interval_year_month"), writableHiveIntervalYearMonthObjectInspector);
      cachedPrimitiveWritableInspectorCache.put(TypeInfoFactory.getPrimitiveTypeInfo("interval_day_time"), writableHiveIntervalDayTimeObjectInspector);
      cachedPrimitiveWritableInspectorCache.put(TypeInfoFactory.getPrimitiveTypeInfo("binary"), writableBinaryObjectInspector);
      cachedPrimitiveWritableInspectorCache.put(TypeInfoFactory.decimalTypeInfo, writableHiveDecimalObjectInspector);
      primitiveCategoryToWritableOI = new EnumMap(PrimitiveObjectInspector.PrimitiveCategory.class);
      primitiveCategoryToWritableOI.put(PrimitiveObjectInspector.PrimitiveCategory.BOOLEAN, writableBooleanObjectInspector);
      primitiveCategoryToWritableOI.put(PrimitiveObjectInspector.PrimitiveCategory.BYTE, writableByteObjectInspector);
      primitiveCategoryToWritableOI.put(PrimitiveObjectInspector.PrimitiveCategory.SHORT, writableShortObjectInspector);
      primitiveCategoryToWritableOI.put(PrimitiveObjectInspector.PrimitiveCategory.INT, writableIntObjectInspector);
      primitiveCategoryToWritableOI.put(PrimitiveObjectInspector.PrimitiveCategory.LONG, writableLongObjectInspector);
      primitiveCategoryToWritableOI.put(PrimitiveObjectInspector.PrimitiveCategory.FLOAT, writableFloatObjectInspector);
      primitiveCategoryToWritableOI.put(PrimitiveObjectInspector.PrimitiveCategory.DOUBLE, writableDoubleObjectInspector);
      primitiveCategoryToWritableOI.put(PrimitiveObjectInspector.PrimitiveCategory.STRING, writableStringObjectInspector);
      primitiveCategoryToWritableOI.put(PrimitiveObjectInspector.PrimitiveCategory.CHAR, writableHiveCharObjectInspector);
      primitiveCategoryToWritableOI.put(PrimitiveObjectInspector.PrimitiveCategory.VARCHAR, writableHiveVarcharObjectInspector);
      primitiveCategoryToWritableOI.put(PrimitiveObjectInspector.PrimitiveCategory.VOID, writableVoidObjectInspector);
      primitiveCategoryToWritableOI.put(PrimitiveObjectInspector.PrimitiveCategory.DATE, writableDateObjectInspector);
      primitiveCategoryToWritableOI.put(PrimitiveObjectInspector.PrimitiveCategory.TIMESTAMP, writableTimestampObjectInspector);
      primitiveCategoryToWritableOI.put(PrimitiveObjectInspector.PrimitiveCategory.INTERVAL_YEAR_MONTH, writableHiveIntervalYearMonthObjectInspector);
      primitiveCategoryToWritableOI.put(PrimitiveObjectInspector.PrimitiveCategory.INTERVAL_DAY_TIME, writableHiveIntervalDayTimeObjectInspector);
      primitiveCategoryToWritableOI.put(PrimitiveObjectInspector.PrimitiveCategory.BINARY, writableBinaryObjectInspector);
      primitiveCategoryToWritableOI.put(PrimitiveObjectInspector.PrimitiveCategory.DECIMAL, writableHiveDecimalObjectInspector);
      javaBooleanObjectInspector = new JavaBooleanObjectInspector();
      javaByteObjectInspector = new JavaByteObjectInspector();
      javaShortObjectInspector = new JavaShortObjectInspector();
      javaIntObjectInspector = new JavaIntObjectInspector();
      javaLongObjectInspector = new JavaLongObjectInspector();
      javaFloatObjectInspector = new JavaFloatObjectInspector();
      javaDoubleObjectInspector = new JavaDoubleObjectInspector();
      javaStringObjectInspector = new JavaStringObjectInspector();
      javaHiveCharObjectInspector = new JavaHiveCharObjectInspector((CharTypeInfo)TypeInfoFactory.charTypeInfo);
      javaHiveVarcharObjectInspector = new JavaHiveVarcharObjectInspector((VarcharTypeInfo)TypeInfoFactory.varcharTypeInfo);
      javaVoidObjectInspector = new JavaVoidObjectInspector();
      javaDateObjectInspector = new JavaDateObjectInspector();
      javaTimestampObjectInspector = new JavaTimestampObjectInspector();
      javaHiveIntervalYearMonthObjectInspector = new JavaHiveIntervalYearMonthObjectInspector();
      javaHiveIntervalDayTimeObjectInspector = new JavaHiveIntervalDayTimeObjectInspector();
      javaByteArrayObjectInspector = new JavaBinaryObjectInspector();
      javaHiveDecimalObjectInspector = new JavaHiveDecimalObjectInspector(TypeInfoFactory.decimalTypeInfo);
      cachedPrimitiveJavaInspectorCache = new ConcurrentHashMap();
      cachedPrimitiveJavaInspectorCache.put(TypeInfoFactory.getPrimitiveTypeInfo("boolean"), javaBooleanObjectInspector);
      cachedPrimitiveJavaInspectorCache.put(TypeInfoFactory.getPrimitiveTypeInfo("tinyint"), javaByteObjectInspector);
      cachedPrimitiveJavaInspectorCache.put(TypeInfoFactory.getPrimitiveTypeInfo("smallint"), javaShortObjectInspector);
      cachedPrimitiveJavaInspectorCache.put(TypeInfoFactory.getPrimitiveTypeInfo("int"), javaIntObjectInspector);
      cachedPrimitiveJavaInspectorCache.put(TypeInfoFactory.getPrimitiveTypeInfo("bigint"), javaLongObjectInspector);
      cachedPrimitiveJavaInspectorCache.put(TypeInfoFactory.getPrimitiveTypeInfo("float"), javaFloatObjectInspector);
      cachedPrimitiveJavaInspectorCache.put(TypeInfoFactory.getPrimitiveTypeInfo("double"), javaDoubleObjectInspector);
      cachedPrimitiveJavaInspectorCache.put(TypeInfoFactory.getPrimitiveTypeInfo("string"), javaStringObjectInspector);
      cachedPrimitiveJavaInspectorCache.put(TypeInfoFactory.charTypeInfo, javaHiveCharObjectInspector);
      cachedPrimitiveJavaInspectorCache.put(TypeInfoFactory.varcharTypeInfo, javaHiveVarcharObjectInspector);
      cachedPrimitiveJavaInspectorCache.put(TypeInfoFactory.getPrimitiveTypeInfo("void"), javaVoidObjectInspector);
      cachedPrimitiveJavaInspectorCache.put(TypeInfoFactory.getPrimitiveTypeInfo("date"), javaDateObjectInspector);
      cachedPrimitiveJavaInspectorCache.put(TypeInfoFactory.getPrimitiveTypeInfo("timestamp"), javaTimestampObjectInspector);
      cachedPrimitiveJavaInspectorCache.put(TypeInfoFactory.getPrimitiveTypeInfo("interval_year_month"), javaHiveIntervalYearMonthObjectInspector);
      cachedPrimitiveJavaInspectorCache.put(TypeInfoFactory.getPrimitiveTypeInfo("interval_day_time"), javaHiveIntervalDayTimeObjectInspector);
      cachedPrimitiveJavaInspectorCache.put(TypeInfoFactory.getPrimitiveTypeInfo("binary"), javaByteArrayObjectInspector);
      cachedPrimitiveJavaInspectorCache.put(TypeInfoFactory.decimalTypeInfo, javaHiveDecimalObjectInspector);
      primitiveCategoryToJavaOI = new EnumMap(PrimitiveObjectInspector.PrimitiveCategory.class);
      primitiveCategoryToJavaOI.put(PrimitiveObjectInspector.PrimitiveCategory.BOOLEAN, javaBooleanObjectInspector);
      primitiveCategoryToJavaOI.put(PrimitiveObjectInspector.PrimitiveCategory.BYTE, javaByteObjectInspector);
      primitiveCategoryToJavaOI.put(PrimitiveObjectInspector.PrimitiveCategory.SHORT, javaShortObjectInspector);
      primitiveCategoryToJavaOI.put(PrimitiveObjectInspector.PrimitiveCategory.INT, javaIntObjectInspector);
      primitiveCategoryToJavaOI.put(PrimitiveObjectInspector.PrimitiveCategory.LONG, javaLongObjectInspector);
      primitiveCategoryToJavaOI.put(PrimitiveObjectInspector.PrimitiveCategory.FLOAT, javaFloatObjectInspector);
      primitiveCategoryToJavaOI.put(PrimitiveObjectInspector.PrimitiveCategory.DOUBLE, javaDoubleObjectInspector);
      primitiveCategoryToJavaOI.put(PrimitiveObjectInspector.PrimitiveCategory.STRING, javaStringObjectInspector);
      primitiveCategoryToJavaOI.put(PrimitiveObjectInspector.PrimitiveCategory.CHAR, javaHiveCharObjectInspector);
      primitiveCategoryToJavaOI.put(PrimitiveObjectInspector.PrimitiveCategory.VARCHAR, javaHiveVarcharObjectInspector);
      primitiveCategoryToJavaOI.put(PrimitiveObjectInspector.PrimitiveCategory.VOID, javaVoidObjectInspector);
      primitiveCategoryToJavaOI.put(PrimitiveObjectInspector.PrimitiveCategory.DATE, javaDateObjectInspector);
      primitiveCategoryToJavaOI.put(PrimitiveObjectInspector.PrimitiveCategory.TIMESTAMP, javaTimestampObjectInspector);
      primitiveCategoryToJavaOI.put(PrimitiveObjectInspector.PrimitiveCategory.INTERVAL_YEAR_MONTH, javaHiveIntervalYearMonthObjectInspector);
      primitiveCategoryToJavaOI.put(PrimitiveObjectInspector.PrimitiveCategory.INTERVAL_DAY_TIME, javaHiveIntervalDayTimeObjectInspector);
      primitiveCategoryToJavaOI.put(PrimitiveObjectInspector.PrimitiveCategory.BINARY, javaByteArrayObjectInspector);
      primitiveCategoryToJavaOI.put(PrimitiveObjectInspector.PrimitiveCategory.DECIMAL, javaHiveDecimalObjectInspector);
   }
}
