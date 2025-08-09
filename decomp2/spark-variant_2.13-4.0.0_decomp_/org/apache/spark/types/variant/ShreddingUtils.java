package org.apache.spark.types.variant;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;

public class ShreddingUtils {
   public static Variant rebuild(ShreddedRow row, VariantSchema schema) {
      if (schema.topLevelMetadataIdx >= 0 && !row.isNullAt(schema.topLevelMetadataIdx)) {
         byte[] metadata = row.getBinary(schema.topLevelMetadataIdx);
         if (schema.isUnshredded()) {
            if (row.isNullAt(schema.variantIdx)) {
               throw VariantUtil.malformedVariant();
            } else {
               return new Variant(row.getBinary(schema.variantIdx), metadata);
            }
         } else {
            VariantBuilder builder = new VariantBuilder(false);
            rebuild(row, metadata, schema, builder);
            return builder.result();
         }
      } else {
         throw VariantUtil.malformedVariant();
      }
   }

   public static void rebuild(ShreddedRow row, byte[] metadata, VariantSchema schema, VariantBuilder builder) {
      int typedIdx = schema.typedIdx;
      int variantIdx = schema.variantIdx;
      if (typedIdx >= 0 && !row.isNullAt(typedIdx)) {
         if (schema.scalarSchema != null) {
            VariantSchema.ScalarType scalar = schema.scalarSchema;
            if (scalar instanceof VariantSchema.StringType) {
               builder.appendString(row.getString(typedIdx));
            } else if (scalar instanceof VariantSchema.IntegralType) {
               VariantSchema.IntegralType it = (VariantSchema.IntegralType)scalar;
               long value = 0L;
               switch (it.size) {
                  case BYTE -> value = (long)row.getByte(typedIdx);
                  case SHORT -> value = (long)row.getShort(typedIdx);
                  case INT -> value = (long)row.getInt(typedIdx);
                  case LONG -> value = row.getLong(typedIdx);
               }

               builder.appendLong(value);
            } else if (scalar instanceof VariantSchema.FloatType) {
               builder.appendFloat(row.getFloat(typedIdx));
            } else if (scalar instanceof VariantSchema.DoubleType) {
               builder.appendDouble(row.getDouble(typedIdx));
            } else if (scalar instanceof VariantSchema.BooleanType) {
               builder.appendBoolean(row.getBoolean(typedIdx));
            } else if (scalar instanceof VariantSchema.BinaryType) {
               builder.appendBinary(row.getBinary(typedIdx));
            } else if (scalar instanceof VariantSchema.UuidType) {
               builder.appendUuid(row.getUuid(typedIdx));
            } else if (scalar instanceof VariantSchema.DecimalType) {
               VariantSchema.DecimalType dt = (VariantSchema.DecimalType)scalar;
               builder.appendDecimal(row.getDecimal(typedIdx, dt.precision, dt.scale));
            } else if (scalar instanceof VariantSchema.DateType) {
               builder.appendDate(row.getInt(typedIdx));
            } else if (scalar instanceof VariantSchema.TimestampType) {
               builder.appendTimestamp(row.getLong(typedIdx));
            } else {
               assert scalar instanceof VariantSchema.TimestampNTZType;

               builder.appendTimestampNtz(row.getLong(typedIdx));
            }
         } else if (schema.arraySchema != null) {
            VariantSchema elementSchema = schema.arraySchema;
            ShreddedRow array = row.getArray(typedIdx);
            int start = builder.getWritePos();
            ArrayList<Integer> offsets = new ArrayList(array.numElements());

            for(int i = 0; i < array.numElements(); ++i) {
               offsets.add(builder.getWritePos() - start);
               rebuild(array.getStruct(i, elementSchema.numFields), metadata, elementSchema, builder);
            }

            builder.finishWritingArray(start, offsets);
         } else {
            ShreddedRow object = row.getStruct(typedIdx, schema.objectSchema.length);
            ArrayList<VariantBuilder.FieldEntry> fields = new ArrayList();
            int start = builder.getWritePos();

            for(int fieldIdx = 0; fieldIdx < schema.objectSchema.length; ++fieldIdx) {
               if (object.isNullAt(fieldIdx)) {
                  throw VariantUtil.malformedVariant();
               }

               String fieldName = schema.objectSchema[fieldIdx].fieldName;
               VariantSchema fieldSchema = schema.objectSchema[fieldIdx].schema;
               ShreddedRow fieldValue = object.getStruct(fieldIdx, fieldSchema.numFields);
               if (fieldSchema.typedIdx >= 0 && !fieldValue.isNullAt(fieldSchema.typedIdx) || fieldSchema.variantIdx >= 0 && !fieldValue.isNullAt(fieldSchema.variantIdx)) {
                  int id = builder.addKey(fieldName);
                  fields.add(new VariantBuilder.FieldEntry(fieldName, id, builder.getWritePos() - start));
                  rebuild(fieldValue, metadata, fieldSchema, builder);
               }
            }

            if (variantIdx >= 0 && !row.isNullAt(variantIdx)) {
               Variant v = new Variant(row.getBinary(variantIdx), metadata);
               if (v.getType() != VariantUtil.Type.OBJECT) {
                  throw VariantUtil.malformedVariant();
               }

               for(int i = 0; i < v.objectSize(); ++i) {
                  Variant.ObjectField field = v.getFieldAtIndex(i);
                  if (schema.objectSchemaMap.containsKey(field.key)) {
                     throw VariantUtil.malformedVariant();
                  }

                  int id = builder.addKey(field.key);
                  fields.add(new VariantBuilder.FieldEntry(field.key, id, builder.getWritePos() - start));
                  builder.appendVariant(field.value);
               }
            }

            builder.finishWritingObject(start, fields);
         }
      } else {
         if (variantIdx < 0 || row.isNullAt(variantIdx)) {
            throw VariantUtil.malformedVariant();
         }

         builder.appendVariant(new Variant(row.getBinary(variantIdx), metadata));
      }

   }

   public interface ShreddedRow {
      boolean isNullAt(int var1);

      boolean getBoolean(int var1);

      byte getByte(int var1);

      short getShort(int var1);

      int getInt(int var1);

      long getLong(int var1);

      float getFloat(int var1);

      double getDouble(int var1);

      BigDecimal getDecimal(int var1, int var2, int var3);

      String getString(int var1);

      byte[] getBinary(int var1);

      UUID getUuid(int var1);

      ShreddedRow getStruct(int var1, int var2);

      ShreddedRow getArray(int var1);

      int numElements();
   }
}
