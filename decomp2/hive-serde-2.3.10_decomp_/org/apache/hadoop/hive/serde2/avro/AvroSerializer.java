package org.apache.hadoop.hive.serde2.avro;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.avro.Schema;
import org.apache.avro.Schema.Type;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericEnumSymbol;
import org.apache.hadoop.hive.common.type.HiveChar;
import org.apache.hadoop.hive.common.type.HiveDecimal;
import org.apache.hadoop.hive.common.type.HiveVarchar;
import org.apache.hadoop.hive.serde2.io.DateWritable;
import org.apache.hadoop.hive.serde2.objectinspector.ListObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.MapObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StructField;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.UnionObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.DateObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.TimestampObjectInspector;
import org.apache.hadoop.hive.serde2.typeinfo.DecimalTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.ListTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.MapTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.StructTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.UnionTypeInfo;
import org.apache.hadoop.io.Writable;

class AvroSerializer {
   private static final Schema STRING_SCHEMA;
   AvroGenericRecordWritable cache = new AvroGenericRecordWritable();
   final InstanceCache enums = new InstanceCache() {
      protected InstanceCache makeInstance(final Schema schema, Set seenSchemas) {
         return new InstanceCache() {
            protected GenericEnumSymbol makeInstance(Object seed, Set seenSchemas) {
               return new GenericData.EnumSymbol(schema, seed.toString());
            }
         };
      }
   };

   public Writable serialize(Object o, ObjectInspector objectInspector, List columnNames, List columnTypes, Schema schema) throws AvroSerdeException {
      StructObjectInspector soi = (StructObjectInspector)objectInspector;
      GenericData.Record record = new GenericData.Record(schema);
      List<? extends StructField> outputFieldRefs = soi.getAllStructFieldRefs();
      if (outputFieldRefs.size() != columnNames.size()) {
         throw new AvroSerdeException("Number of input columns was different than output columns (in = " + columnNames.size() + " vs out = " + outputFieldRefs.size());
      } else {
         int size = schema.getFields().size();
         if (outputFieldRefs.size() != size) {
            throw new AvroSerdeException("Hive passed in a different number of fields than the schema expected: (Hive wanted " + outputFieldRefs.size() + ", Avro expected " + schema.getFields().size());
         } else {
            List<? extends StructField> allStructFieldRefs = soi.getAllStructFieldRefs();
            List<Object> structFieldsDataAsList = soi.getStructFieldsDataAsList(o);

            for(int i = 0; i < size; ++i) {
               Schema.Field field = (Schema.Field)schema.getFields().get(i);
               TypeInfo typeInfo = (TypeInfo)columnTypes.get(i);
               StructField structFieldRef = (StructField)allStructFieldRefs.get(i);
               Object structFieldData = structFieldsDataAsList.get(i);
               ObjectInspector fieldOI = structFieldRef.getFieldObjectInspector();
               Object val = this.serialize(typeInfo, fieldOI, structFieldData, field.schema());
               record.put(field.name(), val);
            }

            if (!GenericData.get().validate(schema, record)) {
               throw new SerializeToAvroException(schema, record);
            } else {
               this.cache.setRecord(record);
               return this.cache;
            }
         }
      }
   }

   private Object serialize(TypeInfo typeInfo, ObjectInspector fieldOI, Object structFieldData, Schema schema) throws AvroSerdeException {
      if (null == structFieldData) {
         return null;
      } else {
         if (AvroSerdeUtils.isNullableType(schema)) {
            schema = AvroSerdeUtils.getOtherTypeFromNullableType(schema);
         }

         if (Type.ENUM.equals(schema.getType())) {
            assert fieldOI instanceof PrimitiveObjectInspector;

            return this.serializeEnum(typeInfo, (PrimitiveObjectInspector)fieldOI, structFieldData, schema);
         } else {
            switch (typeInfo.getCategory()) {
               case PRIMITIVE:
                  assert fieldOI instanceof PrimitiveObjectInspector;

                  return this.serializePrimitive(typeInfo, (PrimitiveObjectInspector)fieldOI, structFieldData, schema);
               case MAP:
                  assert fieldOI instanceof MapObjectInspector;

                  assert typeInfo instanceof MapTypeInfo;

                  return this.serializeMap((MapTypeInfo)typeInfo, (MapObjectInspector)fieldOI, structFieldData, schema);
               case LIST:
                  assert fieldOI instanceof ListObjectInspector;

                  assert typeInfo instanceof ListTypeInfo;

                  return this.serializeList((ListTypeInfo)typeInfo, (ListObjectInspector)fieldOI, structFieldData, schema);
               case UNION:
                  assert fieldOI instanceof UnionObjectInspector;

                  assert typeInfo instanceof UnionTypeInfo;

                  return this.serializeUnion((UnionTypeInfo)typeInfo, (UnionObjectInspector)fieldOI, structFieldData, schema);
               case STRUCT:
                  assert fieldOI instanceof StructObjectInspector;

                  assert typeInfo instanceof StructTypeInfo;

                  return this.serializeStruct((StructTypeInfo)typeInfo, (StructObjectInspector)fieldOI, structFieldData, schema);
               default:
                  throw new AvroSerdeException("Ran out of TypeInfo Categories: " + typeInfo.getCategory());
            }
         }
      }
   }

   private Object serializeEnum(TypeInfo typeInfo, PrimitiveObjectInspector fieldOI, Object structFieldData, Schema schema) throws AvroSerdeException {
      return ((InstanceCache)this.enums.retrieve(schema)).retrieve(this.serializePrimitive(typeInfo, fieldOI, structFieldData, schema));
   }

   private Object serializeStruct(StructTypeInfo typeInfo, StructObjectInspector ssoi, Object o, Schema schema) throws AvroSerdeException {
      int size = schema.getFields().size();
      List<? extends StructField> allStructFieldRefs = ssoi.getAllStructFieldRefs();
      List<Object> structFieldsDataAsList = ssoi.getStructFieldsDataAsList(o);
      GenericData.Record record = new GenericData.Record(schema);
      ArrayList<TypeInfo> allStructFieldTypeInfos = typeInfo.getAllStructFieldTypeInfos();

      for(int i = 0; i < size; ++i) {
         Schema.Field field = (Schema.Field)schema.getFields().get(i);
         TypeInfo colTypeInfo = (TypeInfo)allStructFieldTypeInfos.get(i);
         StructField structFieldRef = (StructField)allStructFieldRefs.get(i);
         Object structFieldData = structFieldsDataAsList.get(i);
         ObjectInspector fieldOI = structFieldRef.getFieldObjectInspector();
         Object val = this.serialize(colTypeInfo, fieldOI, structFieldData, field.schema());
         record.put(field.name(), val);
      }

      return record;
   }

   private Object serializePrimitive(TypeInfo typeInfo, PrimitiveObjectInspector fieldOI, Object structFieldData, Schema schema) throws AvroSerdeException {
      switch (fieldOI.getPrimitiveCategory()) {
         case BINARY:
            if (schema.getType() == Type.BYTES) {
               return AvroSerdeUtils.getBufferFromBytes((byte[])fieldOI.getPrimitiveJavaObject(structFieldData));
            } else {
               if (schema.getType() == Type.FIXED) {
                  GenericData.Fixed fixed = new GenericData.Fixed(schema, (byte[])fieldOI.getPrimitiveJavaObject(structFieldData));
                  return fixed;
               }

               throw new AvroSerdeException("Unexpected Avro schema for Binary TypeInfo: " + schema.getType());
            }
         case DECIMAL:
            HiveDecimal dec = (HiveDecimal)fieldOI.getPrimitiveJavaObject(structFieldData);
            return AvroSerdeUtils.getBufferFromDecimal(dec, ((DecimalTypeInfo)typeInfo).scale());
         case CHAR:
            HiveChar ch = (HiveChar)fieldOI.getPrimitiveJavaObject(structFieldData);
            return ch.getStrippedValue();
         case VARCHAR:
            HiveVarchar vc = (HiveVarchar)fieldOI.getPrimitiveJavaObject(structFieldData);
            return vc.getValue();
         case DATE:
            Date date = ((DateObjectInspector)fieldOI).getPrimitiveJavaObject(structFieldData);
            return DateWritable.dateToDays(date);
         case TIMESTAMP:
            Timestamp timestamp = ((TimestampObjectInspector)fieldOI).getPrimitiveJavaObject(structFieldData);
            return timestamp.getTime();
         case UNKNOWN:
            throw new AvroSerdeException("Received UNKNOWN primitive category.");
         case VOID:
            return null;
         default:
            return fieldOI.getPrimitiveJavaObject(structFieldData);
      }
   }

   private Object serializeUnion(UnionTypeInfo typeInfo, UnionObjectInspector fieldOI, Object structFieldData, Schema schema) throws AvroSerdeException {
      byte tag = fieldOI.getTag(structFieldData);
      return this.serialize((TypeInfo)typeInfo.getAllUnionObjectTypeInfos().get(tag), (ObjectInspector)fieldOI.getObjectInspectors().get(tag), fieldOI.getField(structFieldData), (Schema)schema.getTypes().get(tag));
   }

   private Object serializeList(ListTypeInfo typeInfo, ListObjectInspector fieldOI, Object structFieldData, Schema schema) throws AvroSerdeException {
      List<?> list = fieldOI.getList(structFieldData);
      List<Object> deserialized = new GenericData.Array(list.size(), schema);
      TypeInfo listElementTypeInfo = typeInfo.getListElementTypeInfo();
      ObjectInspector listElementObjectInspector = fieldOI.getListElementObjectInspector();
      Schema elementType = schema.getElementType();

      for(int i = 0; i < list.size(); ++i) {
         deserialized.add(i, this.serialize(listElementTypeInfo, listElementObjectInspector, list.get(i), elementType));
      }

      return deserialized;
   }

   private Object serializeMap(MapTypeInfo typeInfo, MapObjectInspector fieldOI, Object structFieldData, Schema schema) throws AvroSerdeException {
      if (!this.mapHasStringKey(fieldOI.getMapKeyObjectInspector())) {
         throw new AvroSerdeException("Avro only supports maps with keys as Strings.  Current Map is: " + typeInfo.toString());
      } else {
         ObjectInspector mapKeyObjectInspector = fieldOI.getMapKeyObjectInspector();
         ObjectInspector mapValueObjectInspector = fieldOI.getMapValueObjectInspector();
         TypeInfo mapKeyTypeInfo = typeInfo.getMapKeyTypeInfo();
         TypeInfo mapValueTypeInfo = typeInfo.getMapValueTypeInfo();
         Map<?, ?> map = fieldOI.getMap(structFieldData);
         Schema valueType = schema.getValueType();
         Map<Object, Object> deserialized = new HashMap(fieldOI.getMapSize(structFieldData));

         for(Map.Entry entry : map.entrySet()) {
            deserialized.put(this.serialize(mapKeyTypeInfo, mapKeyObjectInspector, entry.getKey(), STRING_SCHEMA), this.serialize(mapValueTypeInfo, mapValueObjectInspector, entry.getValue(), valueType));
         }

         return deserialized;
      }
   }

   private boolean mapHasStringKey(ObjectInspector mapKeyObjectInspector) {
      return mapKeyObjectInspector instanceof PrimitiveObjectInspector && ((PrimitiveObjectInspector)mapKeyObjectInspector).getPrimitiveCategory().equals(PrimitiveObjectInspector.PrimitiveCategory.STRING);
   }

   static {
      STRING_SCHEMA = Schema.create(Type.STRING);
   }

   public static class SerializeToAvroException extends AvroSerdeException {
      private final Schema schema;
      private final GenericData.Record record;

      public SerializeToAvroException(Schema schema, GenericData.Record record) {
         this.schema = schema;
         this.record = record;
      }

      public String toString() {
         return "Avro could not validate record against schema (record = " + this.record + ") (schema = " + this.schema.toString(false) + ")";
      }
   }
}
