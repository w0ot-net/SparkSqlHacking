package org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;
import org.apache.hadoop.hive.serde2.typeinfo.CharTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.DecimalTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.PrimitiveTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoFactory;
import org.apache.hadoop.hive.serde2.typeinfo.VarcharTypeInfo;
import org.apache.hadoop.io.Text;

public final class LazyPrimitiveObjectInspectorFactory {
   public static final LazyBooleanObjectInspector LAZY_BOOLEAN_OBJECT_INSPECTOR = new LazyBooleanObjectInspector();
   public static final LazyBooleanObjectInspector LAZY_EXT_BOOLEAN_OBJECT_INSPECTOR = new LazyBooleanObjectInspector();
   public static final LazyByteObjectInspector LAZY_BYTE_OBJECT_INSPECTOR;
   public static final LazyShortObjectInspector LAZY_SHORT_OBJECT_INSPECTOR;
   public static final LazyIntObjectInspector LAZY_INT_OBJECT_INSPECTOR;
   public static final LazyLongObjectInspector LAZY_LONG_OBJECT_INSPECTOR;
   public static final LazyFloatObjectInspector LAZY_FLOAT_OBJECT_INSPECTOR;
   public static final LazyDoubleObjectInspector LAZY_DOUBLE_OBJECT_INSPECTOR;
   public static final LazyVoidObjectInspector LAZY_VOID_OBJECT_INSPECTOR;
   public static final LazyDateObjectInspector LAZY_DATE_OBJECT_INSPECTOR;
   public static final LazyTimestampObjectInspector LAZY_TIMESTAMP_OBJECT_INSPECTOR;
   public static final LazyHiveIntervalYearMonthObjectInspector LAZY_INTERVAL_YEAR_MONTH_OBJECT_INSPECTOR;
   public static final LazyHiveIntervalDayTimeObjectInspector LAZY_INTERVAL_DAY_TIME_OBJECT_INSPECTOR;
   public static final LazyBinaryObjectInspector LAZY_BINARY_OBJECT_INSPECTOR;
   private static ConcurrentHashMap cachedLazyStringTypeOIs;
   private static ConcurrentHashMap cachedPrimitiveLazyObjectInspectors;

   private LazyPrimitiveObjectInspectorFactory() {
   }

   public static AbstractPrimitiveLazyObjectInspector getLazyObjectInspector(PrimitiveTypeInfo typeInfo, boolean escaped, byte escapeChar) {
      return getLazyObjectInspector(typeInfo, escaped, escapeChar, false);
   }

   public static AbstractPrimitiveLazyObjectInspector getLazyObjectInspector(PrimitiveTypeInfo typeInfo, boolean escaped, byte escapeChar, boolean extBoolean) {
      LazyObjectInspectorParameters lazyParams = new LazyObjectInspectorParametersImpl(escaped, escapeChar, extBoolean, (List)null, (byte[])null, (Text)null);
      return getLazyObjectInspector(typeInfo, lazyParams);
   }

   public static AbstractPrimitiveLazyObjectInspector getLazyObjectInspector(PrimitiveTypeInfo typeInfo, LazyObjectInspectorParameters lazyParams) {
      PrimitiveObjectInspector.PrimitiveCategory primitiveCategory = typeInfo.getPrimitiveCategory();
      switch (primitiveCategory) {
         case STRING:
            return getLazyStringObjectInspector(lazyParams.isEscaped(), lazyParams.getEscapeChar());
         case CHAR:
            return getLazyHiveCharObjectInspector((CharTypeInfo)typeInfo, lazyParams.isEscaped(), lazyParams.getEscapeChar());
         case VARCHAR:
            return getLazyHiveVarcharObjectInspector((VarcharTypeInfo)typeInfo, lazyParams.isEscaped(), lazyParams.getEscapeChar());
         case BOOLEAN:
            return getLazyBooleanObjectInspector(lazyParams.isExtendedBooleanLiteral());
         case TIMESTAMP:
            return getLazyTimestampObjectInspector(lazyParams.getTimestampFormats());
         default:
            return getLazyObjectInspector(typeInfo);
      }
   }

   public static AbstractPrimitiveLazyObjectInspector getLazyObjectInspector(PrimitiveTypeInfo typeInfo) {
      AbstractPrimitiveLazyObjectInspector<?> poi = (AbstractPrimitiveLazyObjectInspector)cachedPrimitiveLazyObjectInspectors.get(typeInfo);
      if (poi != null) {
         return poi;
      } else {
         switch (typeInfo.getPrimitiveCategory()) {
            case CHAR:
               poi = new LazyHiveCharObjectInspector((CharTypeInfo)typeInfo);
               break;
            case VARCHAR:
               poi = new LazyHiveVarcharObjectInspector((VarcharTypeInfo)typeInfo);
               break;
            case BOOLEAN:
            case TIMESTAMP:
            default:
               throw new RuntimeException("Primitve type " + typeInfo.getPrimitiveCategory() + " should not take parameters");
            case DECIMAL:
               poi = new LazyHiveDecimalObjectInspector((DecimalTypeInfo)typeInfo);
         }

         AbstractPrimitiveLazyObjectInspector<?> prev = (AbstractPrimitiveLazyObjectInspector)cachedPrimitiveLazyObjectInspectors.putIfAbsent(typeInfo, poi);
         if (prev != null) {
            poi = prev;
         }

         return poi;
      }
   }

   public static LazyStringObjectInspector getLazyStringObjectInspector(boolean escaped, byte escapeChar) {
      ArrayList<Object> signature = new ArrayList();
      signature.add(TypeInfoFactory.stringTypeInfo);
      signature.add(escaped);
      signature.add(escapeChar);
      LazyStringObjectInspector result = (LazyStringObjectInspector)cachedLazyStringTypeOIs.get(signature);
      if (result == null) {
         result = new LazyStringObjectInspector(escaped, escapeChar);
         AbstractPrimitiveLazyObjectInspector<?> prev = (AbstractPrimitiveLazyObjectInspector)cachedLazyStringTypeOIs.putIfAbsent(signature, result);
         if (prev != null) {
            result = (LazyStringObjectInspector)prev;
         }
      }

      return result;
   }

   public static LazyHiveCharObjectInspector getLazyHiveCharObjectInspector(CharTypeInfo typeInfo, boolean escaped, byte escapeChar) {
      ArrayList<Object> signature = new ArrayList();
      signature.add(typeInfo);
      signature.add(escaped);
      signature.add(escapeChar);
      LazyHiveCharObjectInspector result = (LazyHiveCharObjectInspector)cachedLazyStringTypeOIs.get(signature);
      if (result == null) {
         result = new LazyHiveCharObjectInspector(typeInfo, escaped, escapeChar);
         AbstractPrimitiveLazyObjectInspector<?> prev = (AbstractPrimitiveLazyObjectInspector)cachedLazyStringTypeOIs.putIfAbsent(signature, result);
         if (prev != null) {
            result = (LazyHiveCharObjectInspector)prev;
         }
      }

      return result;
   }

   public static LazyHiveVarcharObjectInspector getLazyHiveVarcharObjectInspector(VarcharTypeInfo typeInfo, boolean escaped, byte escapeChar) {
      ArrayList<Object> signature = new ArrayList();
      signature.add(typeInfo);
      signature.add(escaped);
      signature.add(escapeChar);
      LazyHiveVarcharObjectInspector result = (LazyHiveVarcharObjectInspector)cachedLazyStringTypeOIs.get(signature);
      if (result == null) {
         result = new LazyHiveVarcharObjectInspector(typeInfo, escaped, escapeChar);
         AbstractPrimitiveLazyObjectInspector<?> prev = (AbstractPrimitiveLazyObjectInspector)cachedLazyStringTypeOIs.putIfAbsent(signature, result);
         if (prev != null) {
            result = (LazyHiveVarcharObjectInspector)prev;
         }
      }

      return result;
   }

   public static LazyTimestampObjectInspector getLazyTimestampObjectInspector(List tsFormats) {
      if (tsFormats == null) {
         return (LazyTimestampObjectInspector)getLazyObjectInspector(TypeInfoFactory.timestampTypeInfo);
      } else {
         ArrayList<Object> signature = new ArrayList();
         signature.add(TypeInfoFactory.timestampTypeInfo);
         signature.add(tsFormats);
         LazyTimestampObjectInspector result = (LazyTimestampObjectInspector)cachedLazyStringTypeOIs.get(signature);
         if (result == null) {
            result = new LazyTimestampObjectInspector(tsFormats);
            AbstractPrimitiveLazyObjectInspector<?> prev = (AbstractPrimitiveLazyObjectInspector)cachedLazyStringTypeOIs.putIfAbsent(signature, result);
            if (prev != null) {
               result = (LazyTimestampObjectInspector)prev;
            }
         }

         return result;
      }
   }

   private static LazyBooleanObjectInspector getLazyBooleanObjectInspector(boolean extLiteral) {
      return extLiteral ? LAZY_EXT_BOOLEAN_OBJECT_INSPECTOR : LAZY_BOOLEAN_OBJECT_INSPECTOR;
   }

   static {
      LAZY_EXT_BOOLEAN_OBJECT_INSPECTOR.setExtendedLiteral(true);
      LAZY_BYTE_OBJECT_INSPECTOR = new LazyByteObjectInspector();
      LAZY_SHORT_OBJECT_INSPECTOR = new LazyShortObjectInspector();
      LAZY_INT_OBJECT_INSPECTOR = new LazyIntObjectInspector();
      LAZY_LONG_OBJECT_INSPECTOR = new LazyLongObjectInspector();
      LAZY_FLOAT_OBJECT_INSPECTOR = new LazyFloatObjectInspector();
      LAZY_DOUBLE_OBJECT_INSPECTOR = new LazyDoubleObjectInspector();
      LAZY_VOID_OBJECT_INSPECTOR = new LazyVoidObjectInspector();
      LAZY_DATE_OBJECT_INSPECTOR = new LazyDateObjectInspector();
      LAZY_TIMESTAMP_OBJECT_INSPECTOR = new LazyTimestampObjectInspector();
      LAZY_INTERVAL_YEAR_MONTH_OBJECT_INSPECTOR = new LazyHiveIntervalYearMonthObjectInspector();
      LAZY_INTERVAL_DAY_TIME_OBJECT_INSPECTOR = new LazyHiveIntervalDayTimeObjectInspector();
      LAZY_BINARY_OBJECT_INSPECTOR = new LazyBinaryObjectInspector();
      cachedLazyStringTypeOIs = new ConcurrentHashMap();
      cachedPrimitiveLazyObjectInspectors = new ConcurrentHashMap();
      cachedPrimitiveLazyObjectInspectors.put(TypeInfoFactory.getPrimitiveTypeInfo("boolean"), LAZY_BOOLEAN_OBJECT_INSPECTOR);
      cachedPrimitiveLazyObjectInspectors.put(TypeInfoFactory.getPrimitiveTypeInfo("tinyint"), LAZY_BYTE_OBJECT_INSPECTOR);
      cachedPrimitiveLazyObjectInspectors.put(TypeInfoFactory.getPrimitiveTypeInfo("smallint"), LAZY_SHORT_OBJECT_INSPECTOR);
      cachedPrimitiveLazyObjectInspectors.put(TypeInfoFactory.getPrimitiveTypeInfo("int"), LAZY_INT_OBJECT_INSPECTOR);
      cachedPrimitiveLazyObjectInspectors.put(TypeInfoFactory.getPrimitiveTypeInfo("float"), LAZY_FLOAT_OBJECT_INSPECTOR);
      cachedPrimitiveLazyObjectInspectors.put(TypeInfoFactory.getPrimitiveTypeInfo("double"), LAZY_DOUBLE_OBJECT_INSPECTOR);
      cachedPrimitiveLazyObjectInspectors.put(TypeInfoFactory.getPrimitiveTypeInfo("bigint"), LAZY_LONG_OBJECT_INSPECTOR);
      cachedPrimitiveLazyObjectInspectors.put(TypeInfoFactory.getPrimitiveTypeInfo("void"), LAZY_VOID_OBJECT_INSPECTOR);
      cachedPrimitiveLazyObjectInspectors.put(TypeInfoFactory.getPrimitiveTypeInfo("date"), LAZY_DATE_OBJECT_INSPECTOR);
      cachedPrimitiveLazyObjectInspectors.put(TypeInfoFactory.getPrimitiveTypeInfo("timestamp"), LAZY_TIMESTAMP_OBJECT_INSPECTOR);
      cachedPrimitiveLazyObjectInspectors.put(TypeInfoFactory.getPrimitiveTypeInfo("interval_year_month"), LAZY_INTERVAL_YEAR_MONTH_OBJECT_INSPECTOR);
      cachedPrimitiveLazyObjectInspectors.put(TypeInfoFactory.getPrimitiveTypeInfo("interval_day_time"), LAZY_INTERVAL_DAY_TIME_OBJECT_INSPECTOR);
      cachedPrimitiveLazyObjectInspectors.put(TypeInfoFactory.getPrimitiveTypeInfo("binary"), LAZY_BINARY_OBJECT_INSPECTOR);
   }
}
