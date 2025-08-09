package org.apache.hadoop.hive.serde2.typeinfo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorUtils;

public final class TypeInfoFactory {
   public static final PrimitiveTypeInfo voidTypeInfo = new PrimitiveTypeInfo("void");
   public static final PrimitiveTypeInfo booleanTypeInfo = new PrimitiveTypeInfo("boolean");
   public static final PrimitiveTypeInfo intTypeInfo = new PrimitiveTypeInfo("int");
   public static final PrimitiveTypeInfo longTypeInfo = new PrimitiveTypeInfo("bigint");
   public static final PrimitiveTypeInfo stringTypeInfo = new PrimitiveTypeInfo("string");
   public static final PrimitiveTypeInfo charTypeInfo = new CharTypeInfo(255);
   public static final PrimitiveTypeInfo varcharTypeInfo = new VarcharTypeInfo(65535);
   public static final PrimitiveTypeInfo floatTypeInfo = new PrimitiveTypeInfo("float");
   public static final PrimitiveTypeInfo doubleTypeInfo = new PrimitiveTypeInfo("double");
   public static final PrimitiveTypeInfo byteTypeInfo = new PrimitiveTypeInfo("tinyint");
   public static final PrimitiveTypeInfo shortTypeInfo = new PrimitiveTypeInfo("smallint");
   public static final PrimitiveTypeInfo dateTypeInfo = new PrimitiveTypeInfo("date");
   public static final PrimitiveTypeInfo timestampTypeInfo = new PrimitiveTypeInfo("timestamp");
   public static final PrimitiveTypeInfo intervalYearMonthTypeInfo = new PrimitiveTypeInfo("interval_year_month");
   public static final PrimitiveTypeInfo intervalDayTimeTypeInfo = new PrimitiveTypeInfo("interval_day_time");
   public static final PrimitiveTypeInfo binaryTypeInfo = new PrimitiveTypeInfo("binary");
   public static final DecimalTypeInfo decimalTypeInfo = new DecimalTypeInfo(38, 18);
   public static final PrimitiveTypeInfo unknownTypeInfo = new PrimitiveTypeInfo("unknown");
   private static ConcurrentHashMap cachedPrimitiveTypeInfo = new ConcurrentHashMap();
   static ConcurrentHashMap cachedStructTypeInfo;
   static ConcurrentHashMap cachedUnionTypeInfo;
   static ConcurrentHashMap cachedListTypeInfo;
   static ConcurrentHashMap cachedMapTypeInfo;

   private TypeInfoFactory() {
   }

   public static PrimitiveTypeInfo getPrimitiveTypeInfo(String typeName) {
      PrimitiveTypeInfo result = (PrimitiveTypeInfo)cachedPrimitiveTypeInfo.get(typeName);
      if (result != null) {
         return result;
      } else {
         result = createPrimitiveTypeInfo(typeName);
         if (result == null) {
            throw new RuntimeException("Error creating PrimitiveTypeInfo instance for " + typeName);
         } else {
            PrimitiveTypeInfo prev = (PrimitiveTypeInfo)cachedPrimitiveTypeInfo.putIfAbsent(typeName, result);
            if (prev != null) {
               result = prev;
            }

            return result;
         }
      }
   }

   private static PrimitiveTypeInfo createPrimitiveTypeInfo(String fullName) {
      String baseName = TypeInfoUtils.getBaseName(fullName);
      PrimitiveObjectInspectorUtils.PrimitiveTypeEntry typeEntry = PrimitiveObjectInspectorUtils.getTypeEntryFromTypeName(baseName);
      if (null == typeEntry) {
         throw new RuntimeException("Unknown type " + fullName);
      } else {
         TypeInfoUtils.PrimitiveParts parts = TypeInfoUtils.parsePrimitiveParts(fullName);
         if (parts.typeParams != null && parts.typeParams.length >= 1) {
            switch (typeEntry.primitiveCategory) {
               case CHAR:
                  if (parts.typeParams.length != 1) {
                     return null;
                  }

                  return new CharTypeInfo(Integer.valueOf(parts.typeParams[0]));
               case VARCHAR:
                  if (parts.typeParams.length != 1) {
                     return null;
                  }

                  return new VarcharTypeInfo(Integer.valueOf(parts.typeParams[0]));
               case DECIMAL:
                  if (parts.typeParams.length != 2) {
                     return null;
                  }

                  return new DecimalTypeInfo(Integer.valueOf(parts.typeParams[0]), Integer.valueOf(parts.typeParams[1]));
               default:
                  return null;
            }
         } else {
            return null;
         }
      }
   }

   public static CharTypeInfo getCharTypeInfo(int length) {
      String fullName = BaseCharTypeInfo.getQualifiedName("char", length);
      return (CharTypeInfo)getPrimitiveTypeInfo(fullName);
   }

   public static VarcharTypeInfo getVarcharTypeInfo(int length) {
      String fullName = BaseCharTypeInfo.getQualifiedName("varchar", length);
      return (VarcharTypeInfo)getPrimitiveTypeInfo(fullName);
   }

   public static DecimalTypeInfo getDecimalTypeInfo(int precision, int scale) {
      String fullName = DecimalTypeInfo.getQualifiedName(precision, scale);
      return (DecimalTypeInfo)getPrimitiveTypeInfo(fullName);
   }

   public static TypeInfo getPrimitiveTypeInfoFromPrimitiveWritable(Class clazz) {
      String typeName = PrimitiveObjectInspectorUtils.getTypeNameFromPrimitiveWritable(clazz);
      if (typeName == null) {
         throw new RuntimeException("Internal error: Cannot get typeName for " + clazz);
      } else {
         return getPrimitiveTypeInfo(typeName);
      }
   }

   public static TypeInfo getPrimitiveTypeInfoFromJavaPrimitive(Class clazz) {
      return getPrimitiveTypeInfo(PrimitiveObjectInspectorUtils.getTypeNameFromPrimitiveJava(clazz));
   }

   public static TypeInfo getStructTypeInfo(List names, List typeInfos) {
      ArrayList<List<?>> signature = new ArrayList(2);
      signature.add(names);
      signature.add(typeInfos);
      TypeInfo result = (TypeInfo)cachedStructTypeInfo.get(signature);
      if (result == null) {
         result = new StructTypeInfo(names, typeInfos);
         TypeInfo prev = (TypeInfo)cachedStructTypeInfo.putIfAbsent(signature, result);
         if (prev != null) {
            result = prev;
         }
      }

      return result;
   }

   public static TypeInfo getUnionTypeInfo(List typeInfos) {
      TypeInfo result = (TypeInfo)cachedUnionTypeInfo.get(typeInfos);
      if (result == null) {
         result = new UnionTypeInfo(typeInfos);
         TypeInfo prev = (TypeInfo)cachedUnionTypeInfo.putIfAbsent(typeInfos, result);
         if (prev != null) {
            result = prev;
         }
      }

      return result;
   }

   public static TypeInfo getListTypeInfo(TypeInfo elementTypeInfo) {
      TypeInfo result = (TypeInfo)cachedListTypeInfo.get(elementTypeInfo);
      if (result == null) {
         result = new ListTypeInfo(elementTypeInfo);
         TypeInfo prev = (TypeInfo)cachedListTypeInfo.putIfAbsent(elementTypeInfo, result);
         if (prev != null) {
            result = prev;
         }
      }

      return result;
   }

   public static TypeInfo getMapTypeInfo(TypeInfo keyTypeInfo, TypeInfo valueTypeInfo) {
      ArrayList<TypeInfo> signature = new ArrayList(2);
      signature.add(keyTypeInfo);
      signature.add(valueTypeInfo);
      TypeInfo result = (TypeInfo)cachedMapTypeInfo.get(signature);
      if (result == null) {
         result = new MapTypeInfo(keyTypeInfo, valueTypeInfo);
         TypeInfo prev = (TypeInfo)cachedMapTypeInfo.putIfAbsent(signature, result);
         if (prev != null) {
            result = prev;
         }
      }

      return result;
   }

   static {
      cachedPrimitiveTypeInfo.put("void", voidTypeInfo);
      cachedPrimitiveTypeInfo.put("boolean", booleanTypeInfo);
      cachedPrimitiveTypeInfo.put("int", intTypeInfo);
      cachedPrimitiveTypeInfo.put("bigint", longTypeInfo);
      cachedPrimitiveTypeInfo.put("string", stringTypeInfo);
      cachedPrimitiveTypeInfo.put(charTypeInfo.getQualifiedName(), charTypeInfo);
      cachedPrimitiveTypeInfo.put(varcharTypeInfo.getQualifiedName(), varcharTypeInfo);
      cachedPrimitiveTypeInfo.put("float", floatTypeInfo);
      cachedPrimitiveTypeInfo.put("double", doubleTypeInfo);
      cachedPrimitiveTypeInfo.put("tinyint", byteTypeInfo);
      cachedPrimitiveTypeInfo.put("smallint", shortTypeInfo);
      cachedPrimitiveTypeInfo.put("date", dateTypeInfo);
      cachedPrimitiveTypeInfo.put("timestamp", timestampTypeInfo);
      cachedPrimitiveTypeInfo.put("interval_year_month", intervalYearMonthTypeInfo);
      cachedPrimitiveTypeInfo.put("interval_day_time", intervalDayTimeTypeInfo);
      cachedPrimitiveTypeInfo.put("binary", binaryTypeInfo);
      cachedPrimitiveTypeInfo.put(decimalTypeInfo.getQualifiedName(), decimalTypeInfo);
      cachedPrimitiveTypeInfo.put("unknown", unknownTypeInfo);
      cachedStructTypeInfo = new ConcurrentHashMap();
      cachedUnionTypeInfo = new ConcurrentHashMap();
      cachedListTypeInfo = new ConcurrentHashMap();
      cachedMapTypeInfo = new ConcurrentHashMap();
   }
}
