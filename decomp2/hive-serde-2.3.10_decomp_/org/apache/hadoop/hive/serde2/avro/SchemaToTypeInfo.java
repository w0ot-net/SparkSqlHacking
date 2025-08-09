package org.apache.hadoop.hive.serde2.avro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.avro.Schema;
import org.apache.avro.Schema.Type;
import org.apache.hadoop.hive.serde2.typeinfo.HiveDecimalUtils;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoFactory;

class SchemaToTypeInfo {
   private static final Map primitiveTypeToTypeInfo = initTypeMap();
   static InstanceCache typeInfoCache = new InstanceCache() {
      protected TypeInfo makeInstance(Schema s, Set seenSchemas) throws AvroSerdeException {
         return SchemaToTypeInfo.generateTypeInfoWorker(s, seenSchemas);
      }
   };

   private static Map initTypeMap() {
      Map<Schema.Type, TypeInfo> theMap = new Hashtable();
      theMap.put(Type.NULL, TypeInfoFactory.getPrimitiveTypeInfo("void"));
      theMap.put(Type.BOOLEAN, TypeInfoFactory.getPrimitiveTypeInfo("boolean"));
      theMap.put(Type.INT, TypeInfoFactory.getPrimitiveTypeInfo("int"));
      theMap.put(Type.LONG, TypeInfoFactory.getPrimitiveTypeInfo("bigint"));
      theMap.put(Type.FLOAT, TypeInfoFactory.getPrimitiveTypeInfo("float"));
      theMap.put(Type.DOUBLE, TypeInfoFactory.getPrimitiveTypeInfo("double"));
      theMap.put(Type.BYTES, TypeInfoFactory.getPrimitiveTypeInfo("binary"));
      theMap.put(Type.FIXED, TypeInfoFactory.getPrimitiveTypeInfo("binary"));
      theMap.put(Type.STRING, TypeInfoFactory.getPrimitiveTypeInfo("string"));
      return Collections.unmodifiableMap(theMap);
   }

   public static List generateColumnTypes(Schema schema) throws AvroSerdeException {
      return generateColumnTypes(schema, (Set)null);
   }

   public static List generateColumnTypes(Schema schema, Set seenSchemas) throws AvroSerdeException {
      List<Schema.Field> fields = schema.getFields();
      List<TypeInfo> types = new ArrayList(fields.size());

      for(Schema.Field field : fields) {
         types.add(generateTypeInfo(field.schema(), seenSchemas));
      }

      return types;
   }

   public static TypeInfo generateTypeInfo(Schema schema, Set seenSchemas) throws AvroSerdeException {
      Schema.Type type = schema.getType();
      if (type == Type.BYTES && "decimal".equalsIgnoreCase(schema.getProp("logicalType"))) {
         int precision = 0;
         int scale = 0;

         try {
            Object o = schema.getObjectProp("precision");
            if (o instanceof Integer) {
               precision = (Integer)o;
            }

            o = schema.getObjectProp("scale");
            if (o instanceof Integer) {
               scale = (Integer)o;
            }
         } catch (Exception ex) {
            throw new AvroSerdeException("Failed to obtain scale value from file schema: " + schema, ex);
         }

         try {
            HiveDecimalUtils.validateParameter(precision, scale);
         } catch (Exception ex) {
            throw new AvroSerdeException("Invalid precision or scale for decimal type", ex);
         }

         return TypeInfoFactory.getDecimalTypeInfo(precision, scale);
      } else if (type == Type.STRING && "char".equalsIgnoreCase(schema.getProp("logicalType"))) {
         int maxLength = 0;

         try {
            maxLength = AvroSerdeUtils.getIntFromSchema(schema, "maxLength");
         } catch (Exception ex) {
            throw new AvroSerdeException("Failed to obtain maxLength value from file schema: " + schema, ex);
         }

         return TypeInfoFactory.getCharTypeInfo(maxLength);
      } else if (type == Type.STRING && "varchar".equalsIgnoreCase(schema.getProp("logicalType"))) {
         int maxLength = 0;

         try {
            maxLength = AvroSerdeUtils.getIntFromSchema(schema, "maxLength");
         } catch (Exception ex) {
            throw new AvroSerdeException("Failed to obtain maxLength value from file schema: " + schema, ex);
         }

         return TypeInfoFactory.getVarcharTypeInfo(maxLength);
      } else if (type == Type.INT && "date".equals(schema.getProp("logicalType"))) {
         return TypeInfoFactory.dateTypeInfo;
      } else {
         return (TypeInfo)(type == Type.LONG && "timestamp-millis".equals(schema.getProp("logicalType")) ? TypeInfoFactory.timestampTypeInfo : (TypeInfo)typeInfoCache.retrieve(schema, seenSchemas));
      }
   }

   private static TypeInfo generateTypeInfoWorker(Schema schema, Set seenSchemas) throws AvroSerdeException {
      if (AvroSerdeUtils.isNullableType(schema)) {
         return generateTypeInfo(AvroSerdeUtils.getOtherTypeFromNullableType(schema), seenSchemas);
      } else {
         Schema.Type type = schema.getType();
         if (primitiveTypeToTypeInfo.containsKey(type)) {
            return (TypeInfo)primitiveTypeToTypeInfo.get(type);
         } else {
            switch (type) {
               case RECORD:
                  return generateRecordTypeInfo(schema, seenSchemas);
               case MAP:
                  return generateMapTypeInfo(schema, seenSchemas);
               case ARRAY:
                  return generateArrayTypeInfo(schema, seenSchemas);
               case UNION:
                  return generateUnionTypeInfo(schema, seenSchemas);
               case ENUM:
                  return generateEnumTypeInfo(schema);
               default:
                  throw new AvroSerdeException("Do not yet support: " + schema);
            }
         }
      }
   }

   private static TypeInfo generateRecordTypeInfo(Schema schema, Set seenSchemas) throws AvroSerdeException {
      assert schema.getType().equals(Type.RECORD);

      if (seenSchemas == null) {
         seenSchemas = Collections.newSetFromMap(new IdentityHashMap());
      } else if (seenSchemas.contains(schema)) {
         throw new AvroSerdeException("Recursive schemas are not supported. Recursive schema was " + schema.getFullName());
      }

      seenSchemas.add(schema);
      List<Schema.Field> fields = schema.getFields();
      List<String> fieldNames = new ArrayList(fields.size());
      List<TypeInfo> typeInfos = new ArrayList(fields.size());

      for(int i = 0; i < fields.size(); ++i) {
         fieldNames.add(i, ((Schema.Field)fields.get(i)).name());
         typeInfos.add(i, generateTypeInfo(((Schema.Field)fields.get(i)).schema(), seenSchemas));
      }

      return TypeInfoFactory.getStructTypeInfo(fieldNames, typeInfos);
   }

   private static TypeInfo generateMapTypeInfo(Schema schema, Set seenSchemas) throws AvroSerdeException {
      assert schema.getType().equals(Type.MAP);

      Schema valueType = schema.getValueType();
      TypeInfo ti = generateTypeInfo(valueType, seenSchemas);
      return TypeInfoFactory.getMapTypeInfo(TypeInfoFactory.getPrimitiveTypeInfo("string"), ti);
   }

   private static TypeInfo generateArrayTypeInfo(Schema schema, Set seenSchemas) throws AvroSerdeException {
      assert schema.getType().equals(Type.ARRAY);

      Schema itemsType = schema.getElementType();
      TypeInfo itemsTypeInfo = generateTypeInfo(itemsType, seenSchemas);
      return TypeInfoFactory.getListTypeInfo(itemsTypeInfo);
   }

   private static TypeInfo generateUnionTypeInfo(Schema schema, Set seenSchemas) throws AvroSerdeException {
      assert schema.getType().equals(Type.UNION);

      List<Schema> types = schema.getTypes();
      List<TypeInfo> typeInfos = new ArrayList(types.size());

      for(Schema type : types) {
         typeInfos.add(generateTypeInfo(type, seenSchemas));
      }

      return TypeInfoFactory.getUnionTypeInfo(typeInfos);
   }

   private static TypeInfo generateEnumTypeInfo(Schema schema) {
      assert schema.getType().equals(Type.ENUM);

      return TypeInfoFactory.getPrimitiveTypeInfo("string");
   }
}
