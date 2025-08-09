package org.apache.hadoop.hive.serde2.lazy;

import java.util.ArrayList;
import java.util.List;
import org.apache.hadoop.hive.serde2.SerDeException;
import org.apache.hadoop.hive.serde2.lazy.objectinspector.LazyListObjectInspector;
import org.apache.hadoop.hive.serde2.lazy.objectinspector.LazyMapObjectInspector;
import org.apache.hadoop.hive.serde2.lazy.objectinspector.LazyObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.lazy.objectinspector.LazySimpleStructObjectInspector;
import org.apache.hadoop.hive.serde2.lazy.objectinspector.LazyUnionObjectInspector;
import org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive.LazyBinaryObjectInspector;
import org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive.LazyBooleanObjectInspector;
import org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive.LazyByteObjectInspector;
import org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive.LazyDateObjectInspector;
import org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive.LazyDoubleObjectInspector;
import org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive.LazyFloatObjectInspector;
import org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive.LazyHiveCharObjectInspector;
import org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive.LazyHiveDecimalObjectInspector;
import org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive.LazyHiveIntervalDayTimeObjectInspector;
import org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive.LazyHiveIntervalYearMonthObjectInspector;
import org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive.LazyHiveVarcharObjectInspector;
import org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive.LazyIntObjectInspector;
import org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive.LazyLongObjectInspector;
import org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive.LazyObjectInspectorParameters;
import org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive.LazyObjectInspectorParametersImpl;
import org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive.LazyPrimitiveObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive.LazyShortObjectInspector;
import org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive.LazyStringObjectInspector;
import org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive.LazyTimestampObjectInspector;
import org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive.LazyVoidObjectInspector;
import org.apache.hadoop.hive.serde2.lazydio.LazyDioBinary;
import org.apache.hadoop.hive.serde2.lazydio.LazyDioBoolean;
import org.apache.hadoop.hive.serde2.lazydio.LazyDioByte;
import org.apache.hadoop.hive.serde2.lazydio.LazyDioDouble;
import org.apache.hadoop.hive.serde2.lazydio.LazyDioFloat;
import org.apache.hadoop.hive.serde2.lazydio.LazyDioInteger;
import org.apache.hadoop.hive.serde2.lazydio.LazyDioLong;
import org.apache.hadoop.hive.serde2.lazydio.LazyDioShort;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;
import org.apache.hadoop.hive.serde2.typeinfo.ListTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.MapTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.PrimitiveTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.StructTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.UnionTypeInfo;
import org.apache.hadoop.io.Text;

public final class LazyFactory {
   public static LazyPrimitive createLazyPrimitiveClass(PrimitiveObjectInspector poi, boolean typeBinary) {
      return typeBinary ? createLazyPrimitiveBinaryClass(poi) : createLazyPrimitiveClass(poi);
   }

   public static LazyPrimitive createLazyPrimitiveClass(PrimitiveObjectInspector oi) {
      PrimitiveObjectInspector.PrimitiveCategory p = oi.getPrimitiveCategory();
      switch (p) {
         case BOOLEAN:
            return new LazyBoolean((LazyBooleanObjectInspector)oi);
         case BYTE:
            return new LazyByte((LazyByteObjectInspector)oi);
         case SHORT:
            return new LazyShort((LazyShortObjectInspector)oi);
         case INT:
            return new LazyInteger((LazyIntObjectInspector)oi);
         case LONG:
            return new LazyLong((LazyLongObjectInspector)oi);
         case FLOAT:
            return new LazyFloat((LazyFloatObjectInspector)oi);
         case DOUBLE:
            return new LazyDouble((LazyDoubleObjectInspector)oi);
         case STRING:
            return new LazyString((LazyStringObjectInspector)oi);
         case CHAR:
            return new LazyHiveChar((LazyHiveCharObjectInspector)oi);
         case VARCHAR:
            return new LazyHiveVarchar((LazyHiveVarcharObjectInspector)oi);
         case DATE:
            return new LazyDate((LazyDateObjectInspector)oi);
         case TIMESTAMP:
            return new LazyTimestamp((LazyTimestampObjectInspector)oi);
         case INTERVAL_YEAR_MONTH:
            return new LazyHiveIntervalYearMonth((LazyHiveIntervalYearMonthObjectInspector)oi);
         case INTERVAL_DAY_TIME:
            return new LazyHiveIntervalDayTime((LazyHiveIntervalDayTimeObjectInspector)oi);
         case BINARY:
            return new LazyBinary((LazyBinaryObjectInspector)oi);
         case DECIMAL:
            return new LazyHiveDecimal((LazyHiveDecimalObjectInspector)oi);
         case VOID:
            return new LazyVoid((LazyVoidObjectInspector)oi);
         default:
            throw new RuntimeException("Internal error: no LazyObject for " + p);
      }
   }

   public static LazyPrimitive createLazyPrimitiveBinaryClass(PrimitiveObjectInspector poi) {
      PrimitiveObjectInspector.PrimitiveCategory pc = poi.getPrimitiveCategory();
      switch (pc) {
         case BOOLEAN:
            return new LazyDioBoolean((LazyBooleanObjectInspector)poi);
         case BYTE:
            return new LazyDioByte((LazyByteObjectInspector)poi);
         case SHORT:
            return new LazyDioShort((LazyShortObjectInspector)poi);
         case INT:
            return new LazyDioInteger((LazyIntObjectInspector)poi);
         case LONG:
            return new LazyDioLong((LazyLongObjectInspector)poi);
         case FLOAT:
            return new LazyDioFloat((LazyFloatObjectInspector)poi);
         case DOUBLE:
            return new LazyDioDouble((LazyDoubleObjectInspector)poi);
         case STRING:
         case CHAR:
         case VARCHAR:
         case DATE:
         case TIMESTAMP:
         case INTERVAL_YEAR_MONTH:
         case INTERVAL_DAY_TIME:
         default:
            throw new RuntimeException("Hive Internal Error: no LazyObject for " + poi);
         case BINARY:
            return new LazyDioBinary((LazyBinaryObjectInspector)poi);
      }
   }

   public static LazyObject createLazyObject(ObjectInspector oi) {
      ObjectInspector.Category c = oi.getCategory();
      switch (c) {
         case PRIMITIVE:
            return createLazyPrimitiveClass((PrimitiveObjectInspector)oi);
         case MAP:
            return new LazyMap((LazyMapObjectInspector)oi);
         case LIST:
            return new LazyArray((LazyListObjectInspector)oi);
         case STRUCT:
            return new LazyStruct((LazySimpleStructObjectInspector)oi);
         case UNION:
            return new LazyUnion((LazyUnionObjectInspector)oi);
         default:
            throw new RuntimeException("Hive LazySerDe Internal error.");
      }
   }

   public static LazyObject createLazyObject(ObjectInspector oi, boolean typeBinary) {
      return (LazyObject)(oi.getCategory() == ObjectInspector.Category.PRIMITIVE ? createLazyPrimitiveClass((PrimitiveObjectInspector)oi, typeBinary) : createLazyObject(oi));
   }

   /** @deprecated */
   @Deprecated
   public static ObjectInspector createLazyObjectInspector(TypeInfo typeInfo, byte[] separators, int separatorIndex, Text nullSequence, boolean escaped, byte escapeChar, ObjectInspectorFactory.ObjectInspectorOptions option) throws SerDeException {
      return createLazyObjectInspector(typeInfo, separators, separatorIndex, nullSequence, escaped, escapeChar, false, option);
   }

   /** @deprecated */
   @Deprecated
   public static ObjectInspector createLazyObjectInspector(TypeInfo typeInfo, byte[] separators, int separatorIndex, Text nullSequence, boolean escaped, byte escapeChar) throws SerDeException {
      return createLazyObjectInspector(typeInfo, separators, separatorIndex, nullSequence, escaped, escapeChar, false, ObjectInspectorFactory.ObjectInspectorOptions.JAVA);
   }

   /** @deprecated */
   @Deprecated
   public static ObjectInspector createLazyObjectInspector(TypeInfo typeInfo, byte[] separators, int separatorIndex, Text nullSequence, boolean escaped, byte escapeChar, boolean extendedBooleanLiteral) throws SerDeException {
      return createLazyObjectInspector(typeInfo, separators, separatorIndex, nullSequence, escaped, escapeChar, extendedBooleanLiteral, ObjectInspectorFactory.ObjectInspectorOptions.JAVA);
   }

   /** @deprecated */
   @Deprecated
   public static ObjectInspector createLazyObjectInspector(TypeInfo typeInfo, byte[] separators, int separatorIndex, Text nullSequence, boolean escaped, byte escapeChar, boolean extendedBooleanLiteral, ObjectInspectorFactory.ObjectInspectorOptions option) throws SerDeException {
      LazyObjectInspectorParametersImpl lazyParams = new LazyObjectInspectorParametersImpl(escaped, escapeChar, extendedBooleanLiteral, (List)null, separators, nullSequence);
      return createLazyObjectInspector(typeInfo, separatorIndex, lazyParams, option);
   }

   public static ObjectInspector createLazyObjectInspector(TypeInfo typeInfo, int separatorIndex, LazyObjectInspectorParameters lazyParams, ObjectInspectorFactory.ObjectInspectorOptions option) throws SerDeException {
      ObjectInspector.Category c = typeInfo.getCategory();
      switch (c) {
         case PRIMITIVE:
            return LazyPrimitiveObjectInspectorFactory.getLazyObjectInspector((PrimitiveTypeInfo)typeInfo, lazyParams);
         case MAP:
            return LazyObjectInspectorFactory.getLazySimpleMapObjectInspector(createLazyObjectInspector(((MapTypeInfo)typeInfo).getMapKeyTypeInfo(), separatorIndex + 2, lazyParams, option), createLazyObjectInspector(((MapTypeInfo)typeInfo).getMapValueTypeInfo(), separatorIndex + 2, lazyParams, option), LazyUtils.getSeparator(lazyParams.getSeparators(), separatorIndex), LazyUtils.getSeparator(lazyParams.getSeparators(), separatorIndex + 1), lazyParams);
         case LIST:
            return LazyObjectInspectorFactory.getLazySimpleListObjectInspector(createLazyObjectInspector(((ListTypeInfo)typeInfo).getListElementTypeInfo(), separatorIndex + 1, lazyParams, option), LazyUtils.getSeparator(lazyParams.getSeparators(), separatorIndex), lazyParams);
         case STRUCT:
            StructTypeInfo structTypeInfo = (StructTypeInfo)typeInfo;
            List<String> fieldNames = structTypeInfo.getAllStructFieldNames();
            List<TypeInfo> fieldTypeInfos = structTypeInfo.getAllStructFieldTypeInfos();
            List<ObjectInspector> fieldObjectInspectors = new ArrayList(fieldTypeInfos.size());

            for(int i = 0; i < fieldTypeInfos.size(); ++i) {
               fieldObjectInspectors.add(createLazyObjectInspector((TypeInfo)fieldTypeInfos.get(i), separatorIndex + 1, lazyParams, option));
            }

            return LazyObjectInspectorFactory.getLazySimpleStructObjectInspector(fieldNames, fieldObjectInspectors, (List)null, LazyUtils.getSeparator(lazyParams.getSeparators(), separatorIndex), lazyParams, option);
         case UNION:
            UnionTypeInfo unionTypeInfo = (UnionTypeInfo)typeInfo;
            List<ObjectInspector> lazyOIs = new ArrayList();

            for(TypeInfo uti : unionTypeInfo.getAllUnionObjectTypeInfos()) {
               lazyOIs.add(createLazyObjectInspector(uti, separatorIndex + 1, lazyParams, option));
            }

            return LazyObjectInspectorFactory.getLazyUnionObjectInspector(lazyOIs, LazyUtils.getSeparator(lazyParams.getSeparators(), separatorIndex), lazyParams);
         default:
            throw new RuntimeException("Hive LazySerDe Internal error.");
      }
   }

   /** @deprecated */
   @Deprecated
   public static ObjectInspector createLazyStructInspector(List columnNames, List typeInfos, byte[] separators, Text nullSequence, boolean lastColumnTakesRest, boolean escaped, byte escapeChar) throws SerDeException {
      return createLazyStructInspector(columnNames, typeInfos, separators, nullSequence, lastColumnTakesRest, escaped, escapeChar, false);
   }

   /** @deprecated */
   @Deprecated
   public static ObjectInspector createLazyStructInspector(List columnNames, List typeInfos, byte[] separators, Text nullSequence, boolean lastColumnTakesRest, boolean escaped, byte escapeChar, boolean extendedBooleanLiteral) throws SerDeException {
      LazyObjectInspectorParametersImpl lazyParams = new LazyObjectInspectorParametersImpl(escaped, escapeChar, extendedBooleanLiteral, (List)null, separators, nullSequence, lastColumnTakesRest);
      return createLazyStructInspector(columnNames, typeInfos, lazyParams);
   }

   public static ObjectInspector createLazyStructInspector(List columnNames, List typeInfos, LazyObjectInspectorParameters lazyParams) throws SerDeException {
      ArrayList<ObjectInspector> columnObjectInspectors = new ArrayList(typeInfos.size());

      for(int i = 0; i < typeInfos.size(); ++i) {
         columnObjectInspectors.add(createLazyObjectInspector((TypeInfo)typeInfos.get(i), 1, lazyParams, ObjectInspectorFactory.ObjectInspectorOptions.JAVA));
      }

      return LazyObjectInspectorFactory.getLazySimpleStructObjectInspector(columnNames, columnObjectInspectors, (List)null, lazyParams.getSeparators()[0], lazyParams, ObjectInspectorFactory.ObjectInspectorOptions.JAVA);
   }

   /** @deprecated */
   @Deprecated
   public static ObjectInspector createColumnarStructInspector(List columnNames, List columnTypes, byte[] separators, Text nullSequence, boolean escaped, byte escapeChar) throws SerDeException {
      LazyObjectInspectorParametersImpl lazyParams = new LazyObjectInspectorParametersImpl(escaped, escapeChar, false, (List)null, separators, nullSequence);
      return createColumnarStructInspector(columnNames, columnTypes, lazyParams);
   }

   public static ObjectInspector createColumnarStructInspector(List columnNames, List columnTypes, LazyObjectInspectorParameters lazyParams) throws SerDeException {
      ArrayList<ObjectInspector> columnObjectInspectors = new ArrayList(columnTypes.size());

      for(int i = 0; i < columnTypes.size(); ++i) {
         columnObjectInspectors.add(createLazyObjectInspector((TypeInfo)columnTypes.get(i), 1, lazyParams, ObjectInspectorFactory.ObjectInspectorOptions.JAVA));
      }

      return ObjectInspectorFactory.getColumnarStructObjectInspector(columnNames, columnObjectInspectors);
   }

   private LazyFactory() {
   }
}
