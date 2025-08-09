package org.apache.hadoop.hive.serde2.objectinspector;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.JavaStringObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorConverter;
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
import org.apache.hadoop.hive.serde2.objectinspector.primitive.VoidObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.WritableStringObjectInspector;

public final class ObjectInspectorConverters {
   private static Converter getConverter(PrimitiveObjectInspector inputOI, PrimitiveObjectInspector outputOI) {
      switch (outputOI.getPrimitiveCategory()) {
         case BOOLEAN:
            return new PrimitiveObjectInspectorConverter.BooleanConverter(inputOI, (SettableBooleanObjectInspector)outputOI);
         case BYTE:
            return new PrimitiveObjectInspectorConverter.ByteConverter(inputOI, (SettableByteObjectInspector)outputOI);
         case SHORT:
            return new PrimitiveObjectInspectorConverter.ShortConverter(inputOI, (SettableShortObjectInspector)outputOI);
         case INT:
            return new PrimitiveObjectInspectorConverter.IntConverter(inputOI, (SettableIntObjectInspector)outputOI);
         case LONG:
            return new PrimitiveObjectInspectorConverter.LongConverter(inputOI, (SettableLongObjectInspector)outputOI);
         case FLOAT:
            return new PrimitiveObjectInspectorConverter.FloatConverter(inputOI, (SettableFloatObjectInspector)outputOI);
         case DOUBLE:
            return new PrimitiveObjectInspectorConverter.DoubleConverter(inputOI, (SettableDoubleObjectInspector)outputOI);
         case STRING:
            if (outputOI instanceof WritableStringObjectInspector) {
               return new PrimitiveObjectInspectorConverter.TextConverter(inputOI);
            } else if (outputOI instanceof JavaStringObjectInspector) {
               return new PrimitiveObjectInspectorConverter.StringConverter(inputOI);
            }
         case CHAR:
            return new PrimitiveObjectInspectorConverter.HiveCharConverter(inputOI, (SettableHiveCharObjectInspector)outputOI);
         case VARCHAR:
            return new PrimitiveObjectInspectorConverter.HiveVarcharConverter(inputOI, (SettableHiveVarcharObjectInspector)outputOI);
         case DATE:
            return new PrimitiveObjectInspectorConverter.DateConverter(inputOI, (SettableDateObjectInspector)outputOI);
         case TIMESTAMP:
            return new PrimitiveObjectInspectorConverter.TimestampConverter(inputOI, (SettableTimestampObjectInspector)outputOI);
         case INTERVAL_YEAR_MONTH:
            return new PrimitiveObjectInspectorConverter.HiveIntervalYearMonthConverter(inputOI, (SettableHiveIntervalYearMonthObjectInspector)outputOI);
         case INTERVAL_DAY_TIME:
            return new PrimitiveObjectInspectorConverter.HiveIntervalDayTimeConverter(inputOI, (SettableHiveIntervalDayTimeObjectInspector)outputOI);
         case BINARY:
            return new PrimitiveObjectInspectorConverter.BinaryConverter(inputOI, (SettableBinaryObjectInspector)outputOI);
         case DECIMAL:
            return new PrimitiveObjectInspectorConverter.HiveDecimalConverter(inputOI, (SettableHiveDecimalObjectInspector)outputOI);
         default:
            throw new RuntimeException("Hive internal error: conversion of " + inputOI.getTypeName() + " to " + outputOI.getTypeName() + " not supported yet.");
      }
   }

   public static Converter getConverter(ObjectInspector inputOI, ObjectInspector outputOI) {
      if (inputOI.equals(outputOI)) {
         return new IdentityConverter();
      } else {
         switch (outputOI.getCategory()) {
            case PRIMITIVE:
               return getConverter((PrimitiveObjectInspector)inputOI, (PrimitiveObjectInspector)outputOI);
            case STRUCT:
               return new StructConverter(inputOI, (SettableStructObjectInspector)outputOI);
            case LIST:
               return new ListConverter(inputOI, (SettableListObjectInspector)outputOI);
            case MAP:
               return new MapConverter(inputOI, (SettableMapObjectInspector)outputOI);
            case UNION:
               return new UnionConverter(inputOI, (SettableUnionObjectInspector)outputOI);
            default:
               throw new RuntimeException("Hive internal error: conversion of " + inputOI.getTypeName() + " to " + outputOI.getTypeName() + " not supported yet.");
         }
      }
   }

   public static ObjectInspector getConvertedOI(ObjectInspector inputOI, ObjectInspector outputOI, Map oiSettableProperties) {
      return getConvertedOI(inputOI, outputOI, oiSettableProperties, true);
   }

   public static ObjectInspector getConvertedOI(ObjectInspector inputOI, ObjectInspector outputOI) {
      return getConvertedOI(inputOI, outputOI, (Map)null, true);
   }

   public static ObjectInspector getConvertedOI(ObjectInspector inputOI, ObjectInspector outputOI, Map oiSettableProperties, boolean equalsCheck) {
      if ((!equalsCheck || !inputOI.equals(outputOI)) && !ObjectInspectorUtils.hasAllFieldsSettable(outputOI, oiSettableProperties)) {
         switch (outputOI.getCategory()) {
            case PRIMITIVE:
               PrimitiveObjectInspector primOutputOI = (PrimitiveObjectInspector)outputOI;
               return PrimitiveObjectInspectorFactory.getPrimitiveWritableObjectInspector(primOutputOI.getTypeInfo());
            case STRUCT:
               StructObjectInspector structOutputOI = (StructObjectInspector)outputOI;
               List<? extends StructField> listFields = structOutputOI.getAllStructFieldRefs();
               List<String> structFieldNames = new ArrayList(listFields.size());
               List<ObjectInspector> structFieldObjectInspectors = new ArrayList(listFields.size());

               for(StructField listField : listFields) {
                  structFieldNames.add(listField.getFieldName());
                  structFieldObjectInspectors.add(getConvertedOI(listField.getFieldObjectInspector(), listField.getFieldObjectInspector(), oiSettableProperties, false));
               }

               return ObjectInspectorFactory.getStandardStructObjectInspector(structFieldNames, structFieldObjectInspectors);
            case LIST:
               ListObjectInspector listOutputOI = (ListObjectInspector)outputOI;
               return ObjectInspectorFactory.getStandardListObjectInspector(getConvertedOI(listOutputOI.getListElementObjectInspector(), listOutputOI.getListElementObjectInspector(), oiSettableProperties, false));
            case MAP:
               MapObjectInspector mapOutputOI = (MapObjectInspector)outputOI;
               return ObjectInspectorFactory.getStandardMapObjectInspector(getConvertedOI(mapOutputOI.getMapKeyObjectInspector(), mapOutputOI.getMapKeyObjectInspector(), oiSettableProperties, false), getConvertedOI(mapOutputOI.getMapValueObjectInspector(), mapOutputOI.getMapValueObjectInspector(), oiSettableProperties, false));
            case UNION:
               UnionObjectInspector unionOutputOI = (UnionObjectInspector)outputOI;
               List<ObjectInspector> unionListFields = unionOutputOI.getObjectInspectors();
               List<ObjectInspector> unionFieldObjectInspectors = new ArrayList(unionListFields.size());

               for(ObjectInspector listField : unionListFields) {
                  unionFieldObjectInspectors.add(getConvertedOI(listField, listField, oiSettableProperties, false));
               }

               return ObjectInspectorFactory.getStandardUnionObjectInspector(unionFieldObjectInspectors);
            default:
               throw new RuntimeException("Hive internal error: conversion of " + inputOI.getTypeName() + " to " + outputOI.getTypeName() + " not supported yet.");
         }
      } else {
         return outputOI;
      }
   }

   private ObjectInspectorConverters() {
   }

   public static class IdentityConverter implements Converter {
      public Object convert(Object input) {
         return input;
      }
   }

   public static class ListConverter implements Converter {
      ListObjectInspector inputOI;
      SettableListObjectInspector outputOI;
      ObjectInspector inputElementOI;
      ObjectInspector outputElementOI;
      ArrayList elementConverters;
      Object output;

      public ListConverter(ObjectInspector inputOI, SettableListObjectInspector outputOI) {
         if (inputOI instanceof ListObjectInspector) {
            this.inputOI = (ListObjectInspector)inputOI;
            this.outputOI = outputOI;
            this.inputElementOI = this.inputOI.getListElementObjectInspector();
            this.outputElementOI = outputOI.getListElementObjectInspector();
            this.output = outputOI.create(0);
            this.elementConverters = new ArrayList();
         } else if (!(inputOI instanceof VoidObjectInspector)) {
            throw new RuntimeException("Hive internal error: conversion of " + inputOI.getTypeName() + " to " + outputOI.getTypeName() + "not supported yet.");
         }

      }

      public Object convert(Object input) {
         if (input == null) {
            return null;
         } else {
            int size = this.inputOI.getListLength(input);

            while(this.elementConverters.size() < size) {
               this.elementConverters.add(ObjectInspectorConverters.getConverter(this.inputElementOI, this.outputElementOI));
            }

            this.outputOI.resize(this.output, size);

            for(int index = 0; index < size; ++index) {
               Object inputElement = this.inputOI.getListElement(input, index);
               Object outputElement = ((Converter)this.elementConverters.get(index)).convert(inputElement);
               this.outputOI.set(this.output, index, outputElement);
            }

            return this.output;
         }
      }
   }

   public static class StructConverter implements Converter {
      StructObjectInspector inputOI;
      SettableStructObjectInspector outputOI;
      List inputFields;
      List outputFields;
      ArrayList fieldConverters;
      Object output;

      public StructConverter(ObjectInspector inputOI, SettableStructObjectInspector outputOI) {
         if (inputOI instanceof StructObjectInspector) {
            this.inputOI = (StructObjectInspector)inputOI;
            this.outputOI = outputOI;
            this.inputFields = this.inputOI.getAllStructFieldRefs();
            this.outputFields = outputOI.getAllStructFieldRefs();
            int minFields = Math.min(this.inputFields.size(), this.outputFields.size());
            this.fieldConverters = new ArrayList(minFields);

            for(int f = 0; f < minFields; ++f) {
               this.fieldConverters.add(ObjectInspectorConverters.getConverter(((StructField)this.inputFields.get(f)).getFieldObjectInspector(), ((StructField)this.outputFields.get(f)).getFieldObjectInspector()));
            }

            this.output = outputOI.create();
         } else if (!(inputOI instanceof VoidObjectInspector)) {
            throw new RuntimeException("Hive internal error: conversion of " + inputOI.getTypeName() + " to " + outputOI.getTypeName() + "not supported yet.");
         }

      }

      public Object convert(Object input) {
         if (input == null) {
            return null;
         } else {
            int minFields = Math.min(this.inputFields.size(), this.outputFields.size());

            for(int f = 0; f < minFields; ++f) {
               Object inputFieldValue = this.inputOI.getStructFieldData(input, (StructField)this.inputFields.get(f));
               Object outputFieldValue = ((Converter)this.fieldConverters.get(f)).convert(inputFieldValue);
               this.outputOI.setStructFieldData(this.output, (StructField)this.outputFields.get(f), outputFieldValue);
            }

            for(int f = minFields; f < this.outputFields.size(); ++f) {
               this.outputOI.setStructFieldData(this.output, (StructField)this.outputFields.get(f), (Object)null);
            }

            return this.output;
         }
      }
   }

   public static class UnionConverter implements Converter {
      UnionObjectInspector inputOI;
      SettableUnionObjectInspector outputOI;
      List inputTagsOIs;
      List outputTagsOIs;
      ArrayList fieldConverters;
      Object output;

      public UnionConverter(ObjectInspector inputOI, SettableUnionObjectInspector outputOI) {
         if (inputOI instanceof UnionObjectInspector) {
            this.inputOI = (UnionObjectInspector)inputOI;
            this.outputOI = outputOI;
            this.inputTagsOIs = this.inputOI.getObjectInspectors();
            this.outputTagsOIs = outputOI.getObjectInspectors();
            int minFields = Math.min(this.inputTagsOIs.size(), this.outputTagsOIs.size());
            this.fieldConverters = new ArrayList(minFields);

            for(int f = 0; f < minFields; ++f) {
               this.fieldConverters.add(ObjectInspectorConverters.getConverter((ObjectInspector)this.inputTagsOIs.get(f), (ObjectInspector)this.outputTagsOIs.get(f)));
            }

            this.output = outputOI.create();
         } else if (!(inputOI instanceof VoidObjectInspector)) {
            throw new RuntimeException("Hive internal error: conversion of " + inputOI.getTypeName() + " to " + outputOI.getTypeName() + "not supported yet.");
         }

      }

      public Object convert(Object input) {
         if (input == null) {
            return null;
         } else {
            Object inputFieldValue = this.inputOI.getField(input);
            Object inputFieldTag = this.inputOI.getTag(input);
            Object outputFieldValue = null;
            int inputFieldTagIndex = ((Byte)inputFieldTag).intValue();
            if (inputFieldTagIndex >= 0 && inputFieldTagIndex < this.fieldConverters.size()) {
               outputFieldValue = ((Converter)this.fieldConverters.get(inputFieldTagIndex)).convert(inputFieldValue);
            }

            this.outputOI.addField(this.output, outputFieldValue);
            return this.output;
         }
      }
   }

   public static class MapConverter implements Converter {
      MapObjectInspector inputOI;
      SettableMapObjectInspector outputOI;
      ObjectInspector inputKeyOI;
      ObjectInspector outputKeyOI;
      ObjectInspector inputValueOI;
      ObjectInspector outputValueOI;
      ArrayList keyConverters;
      ArrayList valueConverters;
      Object output;

      public MapConverter(ObjectInspector inputOI, SettableMapObjectInspector outputOI) {
         if (inputOI instanceof MapObjectInspector) {
            this.inputOI = (MapObjectInspector)inputOI;
            this.outputOI = outputOI;
            this.inputKeyOI = this.inputOI.getMapKeyObjectInspector();
            this.outputKeyOI = outputOI.getMapKeyObjectInspector();
            this.inputValueOI = this.inputOI.getMapValueObjectInspector();
            this.outputValueOI = outputOI.getMapValueObjectInspector();
            this.keyConverters = new ArrayList();
            this.valueConverters = new ArrayList();
            this.output = outputOI.create();
         } else if (!(inputOI instanceof VoidObjectInspector)) {
            throw new RuntimeException("Hive internal error: conversion of " + inputOI.getTypeName() + " to " + outputOI.getTypeName() + "not supported yet.");
         }

      }

      public Object convert(Object input) {
         if (input == null) {
            return null;
         } else {
            Map<?, ?> map = this.inputOI.getMap(input);
            int size = map.size();

            while(this.keyConverters.size() < size) {
               this.keyConverters.add(ObjectInspectorConverters.getConverter(this.inputKeyOI, this.outputKeyOI));
               this.valueConverters.add(ObjectInspectorConverters.getConverter(this.inputValueOI, this.outputValueOI));
            }

            this.outputOI.clear(this.output);
            int entryID = 0;

            for(Map.Entry entry : map.entrySet()) {
               Object inputKey = entry.getKey();
               Object inputValue = entry.getValue();
               Object outputKey = ((Converter)this.keyConverters.get(entryID)).convert(inputKey);
               Object outputValue = ((Converter)this.valueConverters.get(entryID)).convert(inputValue);
               ++entryID;
               this.outputOI.put(this.output, outputKey, outputValue);
            }

            return this.output;
         }
      }
   }

   public interface Converter {
      Object convert(Object var1);
   }
}
