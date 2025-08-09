package org.apache.hadoop.hive.serde2.avro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.avro.JsonProperties;
import org.apache.avro.Schema;
import org.apache.avro.Schema.Type;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;
import org.apache.hadoop.hive.serde2.typeinfo.CharTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.DecimalTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.ListTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.MapTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.PrimitiveTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.StructTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.UnionTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.VarcharTypeInfo;

public class TypeInfoToSchema {
   private long recordCounter = 0L;

   public Schema convert(List columnNames, List columnTypes, List columnComments, String namespace, String name, String doc) {
      List<Schema.Field> fields = new ArrayList();

      for(int i = 0; i < columnNames.size(); ++i) {
         String comment = columnComments.size() > i ? (String)columnComments.get(i) : null;
         Schema.Field avroField = this.createAvroField((String)columnNames.get(i), (TypeInfo)columnTypes.get(i), comment);
         fields.addAll(this.getFields(avroField));
      }

      if (name == null || name.isEmpty()) {
         name = "baseRecord";
      }

      Schema avroSchema = Schema.createRecord(name, doc, namespace, false);
      avroSchema.setFields(fields);
      return avroSchema;
   }

   private Schema.Field createAvroField(String name, TypeInfo typeInfo, String comment) {
      return new Schema.Field(name, this.createAvroSchema(typeInfo), comment, JsonProperties.NULL_VALUE);
   }

   private Schema createAvroSchema(TypeInfo typeInfo) {
      Schema schema = null;
      switch (typeInfo.getCategory()) {
         case PRIMITIVE:
            schema = this.createAvroPrimitive(typeInfo);
            break;
         case LIST:
            schema = this.createAvroArray(typeInfo);
            break;
         case MAP:
            schema = this.createAvroMap(typeInfo);
            break;
         case STRUCT:
            schema = this.createAvroRecord(typeInfo);
            break;
         case UNION:
            schema = this.createAvroUnion(typeInfo);
      }

      return this.wrapInUnionWithNull(schema);
   }

   private Schema createAvroPrimitive(TypeInfo typeInfo) {
      PrimitiveTypeInfo primitiveTypeInfo = (PrimitiveTypeInfo)typeInfo;
      Schema schema;
      switch (primitiveTypeInfo.getPrimitiveCategory()) {
         case STRING:
            schema = Schema.create(Type.STRING);
            break;
         case CHAR:
            schema = AvroSerdeUtils.getSchemaFor("{\"type\":\"string\",\"logicalType\":\"char\",\"maxLength\":" + ((CharTypeInfo)typeInfo).getLength() + "}");
            break;
         case VARCHAR:
            schema = AvroSerdeUtils.getSchemaFor("{\"type\":\"string\",\"logicalType\":\"varchar\",\"maxLength\":" + ((VarcharTypeInfo)typeInfo).getLength() + "}");
            break;
         case BINARY:
            schema = Schema.create(Type.BYTES);
            break;
         case BYTE:
            schema = Schema.create(Type.INT);
            break;
         case SHORT:
            schema = Schema.create(Type.INT);
            break;
         case INT:
            schema = Schema.create(Type.INT);
            break;
         case LONG:
            schema = Schema.create(Type.LONG);
            break;
         case FLOAT:
            schema = Schema.create(Type.FLOAT);
            break;
         case DOUBLE:
            schema = Schema.create(Type.DOUBLE);
            break;
         case BOOLEAN:
            schema = Schema.create(Type.BOOLEAN);
            break;
         case DECIMAL:
            DecimalTypeInfo decimalTypeInfo = (DecimalTypeInfo)typeInfo;
            String precision = String.valueOf(decimalTypeInfo.precision());
            String scale = String.valueOf(decimalTypeInfo.scale());
            schema = AvroSerdeUtils.getSchemaFor("{\"type\":\"bytes\",\"logicalType\":\"decimal\",\"precision\":" + precision + ",\"scale\":" + scale + "}");
            break;
         case DATE:
            schema = AvroSerdeUtils.getSchemaFor("{\"type\":\"int\",\"logicalType\":\"date\"}");
            break;
         case TIMESTAMP:
            schema = AvroSerdeUtils.getSchemaFor("{\"type\":\"long\",\"logicalType\":\"timestamp-millis\"}");
            break;
         case VOID:
            schema = Schema.create(Type.NULL);
            break;
         default:
            throw new UnsupportedOperationException(typeInfo + " is not supported.");
      }

      return schema;
   }

   private Schema createAvroUnion(TypeInfo typeInfo) {
      List<Schema> childSchemas = new ArrayList();

      for(TypeInfo childTypeInfo : ((UnionTypeInfo)typeInfo).getAllUnionObjectTypeInfos()) {
         Schema childSchema = this.createAvroSchema(childTypeInfo);
         if (childSchema.getType() == Type.UNION) {
            childSchemas.addAll(childSchema.getTypes());
         } else {
            childSchemas.add(childSchema);
         }
      }

      return Schema.createUnion(this.removeDuplicateNullSchemas(childSchemas));
   }

   private Schema createAvroRecord(TypeInfo typeInfo) {
      List<Schema.Field> childFields = new ArrayList();
      List<String> allStructFieldNames = ((StructTypeInfo)typeInfo).getAllStructFieldNames();
      List<TypeInfo> allStructFieldTypeInfos = ((StructTypeInfo)typeInfo).getAllStructFieldTypeInfos();
      if (allStructFieldNames.size() != allStructFieldTypeInfos.size()) {
         throw new IllegalArgumentException("Failed to generate avro schema from hive schema. name and column type differs. names = " + allStructFieldNames + ", types = " + allStructFieldTypeInfos);
      } else {
         for(int i = 0; i < allStructFieldNames.size(); ++i) {
            TypeInfo childTypeInfo = (TypeInfo)allStructFieldTypeInfos.get(i);
            Schema.Field grandChildSchemaField = this.createAvroField((String)allStructFieldNames.get(i), childTypeInfo, childTypeInfo.toString());
            List<Schema.Field> grandChildFields = this.getFields(grandChildSchemaField);
            childFields.addAll(grandChildFields);
         }

         Schema recordSchema = Schema.createRecord("record_" + this.recordCounter, typeInfo.toString(), (String)null, false);
         ++this.recordCounter;
         recordSchema.setFields(childFields);
         return recordSchema;
      }
   }

   private Schema createAvroMap(TypeInfo typeInfo) {
      TypeInfo keyTypeInfo = ((MapTypeInfo)typeInfo).getMapKeyTypeInfo();
      if (((PrimitiveTypeInfo)keyTypeInfo).getPrimitiveCategory() != PrimitiveObjectInspector.PrimitiveCategory.STRING) {
         throw new UnsupportedOperationException("Key of Map can only be a String");
      } else {
         TypeInfo valueTypeInfo = ((MapTypeInfo)typeInfo).getMapValueTypeInfo();
         Schema valueSchema = this.createAvroSchema(valueTypeInfo);
         return Schema.createMap(valueSchema);
      }
   }

   private Schema createAvroArray(TypeInfo typeInfo) {
      ListTypeInfo listTypeInfo = (ListTypeInfo)typeInfo;
      Schema listSchema = this.createAvroSchema(listTypeInfo.getListElementTypeInfo());
      return Schema.createArray(listSchema);
   }

   private List getFields(Schema.Field schemaField) {
      List<Schema.Field> fields = new ArrayList();
      JsonProperties.Null nullDefault = JsonProperties.NULL_VALUE;
      if (schemaField.schema().getType() == Type.RECORD) {
         for(Schema.Field field : schemaField.schema().getFields()) {
            fields.add(new Schema.Field(field.name(), field.schema(), field.doc(), nullDefault));
         }
      } else {
         fields.add(new Schema.Field(schemaField.name(), schemaField.schema(), schemaField.doc(), nullDefault));
      }

      return fields;
   }

   private Schema wrapInUnionWithNull(Schema schema) {
      Schema wrappedSchema = schema;
      switch (schema.getType()) {
         case NULL:
            break;
         case UNION:
            List<Schema> existingSchemas = this.removeDuplicateNullSchemas(schema.getTypes());
            wrappedSchema = Schema.createUnion(existingSchemas);
            break;
         default:
            wrappedSchema = Schema.createUnion(Arrays.asList(Schema.create(Type.NULL), schema));
      }

      return wrappedSchema;
   }

   private List removeDuplicateNullSchemas(List childSchemas) {
      List<Schema> prunedSchemas = new ArrayList();
      boolean isNullPresent = false;

      for(Schema schema : childSchemas) {
         if (schema.getType() == Type.NULL) {
            isNullPresent = true;
         } else {
            prunedSchemas.add(schema);
         }
      }

      if (isNullPresent) {
         prunedSchemas.add(0, Schema.create(Type.NULL));
      }

      return prunedSchemas;
   }
}
