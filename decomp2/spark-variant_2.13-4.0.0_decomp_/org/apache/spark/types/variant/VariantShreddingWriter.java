package org.apache.spark.types.variant;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class VariantShreddingWriter {
   public static ShreddedResult castShredded(Variant v, VariantSchema schema, ShreddedResultBuilder builder) {
      VariantUtil.Type variantType = v.getType();
      ShreddedResult result = builder.createEmpty(schema);
      if (schema.topLevelMetadataIdx >= 0) {
         result.addMetadata(v.getMetadata());
      }

      if (schema.arraySchema != null && variantType == VariantUtil.Type.ARRAY) {
         VariantSchema elementSchema = schema.arraySchema;
         int size = v.arraySize();
         ShreddedResult[] array = new ShreddedResult[size];

         for(int i = 0; i < size; ++i) {
            ShreddedResult shreddedArray = castShredded(v.getElementAtIndex(i), elementSchema, builder);
            array[i] = shreddedArray;
         }

         result.addArray(array);
      } else if (schema.objectSchema != null && variantType == VariantUtil.Type.OBJECT) {
         VariantSchema.ObjectField[] objectSchema = schema.objectSchema;
         ShreddedResult[] shreddedValues = new ShreddedResult[objectSchema.length];
         VariantBuilder variantBuilder = new VariantBuilder(false);
         ArrayList<VariantBuilder.FieldEntry> fieldEntries = new ArrayList();
         int numFieldsMatched = 0;
         int start = variantBuilder.getWritePos();

         for(int i = 0; i < v.objectSize(); ++i) {
            Variant.ObjectField field = v.getFieldAtIndex(i);
            Integer fieldIdx = (Integer)schema.objectSchemaMap.get(field.key);
            if (fieldIdx != null) {
               ShreddedResult shreddedField = castShredded(field.value, objectSchema[fieldIdx].schema, builder);
               shreddedValues[fieldIdx] = shreddedField;
               ++numFieldsMatched;
            } else {
               int id = v.getDictionaryIdAtIndex(i);
               fieldEntries.add(new VariantBuilder.FieldEntry(field.key, id, variantBuilder.getWritePos() - start));
               variantBuilder.shallowAppendVariant(field.value);
            }
         }

         if (numFieldsMatched < objectSchema.length) {
            for(int i = 0; i < objectSchema.length; ++i) {
               if (shreddedValues[i] == null) {
                  VariantSchema.ObjectField fieldSchema = objectSchema[i];
                  ShreddedResult emptyChild = builder.createEmpty(fieldSchema.schema);
                  shreddedValues[i] = emptyChild;
                  ++numFieldsMatched;
               }
            }
         }

         if (numFieldsMatched != objectSchema.length) {
            throw VariantUtil.malformedVariant();
         }

         result.addObject(shreddedValues);
         if (variantBuilder.getWritePos() != start) {
            variantBuilder.finishWritingObject(start, fieldEntries);
            result.addVariantValue(variantBuilder.valueWithoutMetadata());
         }
      } else if (schema.scalarSchema != null) {
         VariantSchema.ScalarType scalarType = schema.scalarSchema;
         Object typedValue = tryTypedShred(v, variantType, scalarType, builder);
         if (typedValue != null) {
            result.addScalar(typedValue);
         } else {
            result.addVariantValue(v.getValue());
         }
      } else {
         result.addVariantValue(v.getValue());
      }

      return result;
   }

   private static Object tryTypedShred(Variant v, VariantUtil.Type variantType, VariantSchema.ScalarType targetType, ShreddedResultBuilder builder) {
      switch (variantType) {
         case LONG:
            if (targetType instanceof VariantSchema.IntegralType) {
               VariantSchema.IntegralType integralType = (VariantSchema.IntegralType)targetType;
               VariantSchema.IntegralSize size = integralType.size;
               long value = v.getLong();
               switch (size) {
                  case BYTE:
                     if (value == (long)((byte)((int)value))) {
                        return (byte)((int)value);
                     }

                     return null;
                  case SHORT:
                     if (value == (long)((short)((int)value))) {
                        return (short)((int)value);
                     }

                     return null;
                  case INT:
                     if (value == (long)((int)value)) {
                        return (int)value;
                     }

                     return null;
                  case LONG:
                     return value;
               }
            } else {
               if (targetType instanceof VariantSchema.DecimalType) {
                  VariantSchema.DecimalType decimalType = (VariantSchema.DecimalType)targetType;
                  if (builder.allowNumericScaleChanges()) {
                     long value = v.getLong();
                     BigDecimal decimalValue = BigDecimal.valueOf(value);
                     BigDecimal scaledValue = decimalValue.setScale(decimalType.scale);

                     assert decimalValue.compareTo(scaledValue) == 0;

                     if (scaledValue.precision() <= decimalType.precision) {
                        return scaledValue;
                     }
                  }
               }
               break;
            }
         case DECIMAL:
            if (targetType instanceof VariantSchema.DecimalType decimalType) {
               BigDecimal value = VariantUtil.getDecimalWithOriginalScale(v.value, v.pos);
               if (value.precision() <= decimalType.precision && value.scale() == decimalType.scale) {
                  return value;
               }

               if (builder.allowNumericScaleChanges()) {
                  BigDecimal scaledValue = value.setScale(decimalType.scale, RoundingMode.FLOOR);
                  if (scaledValue.compareTo(value) == 0 && scaledValue.precision() <= decimalType.precision) {
                     return scaledValue;
                  }
               }
            } else if (targetType instanceof VariantSchema.IntegralType integralType) {
               if (builder.allowNumericScaleChanges()) {
                  BigDecimal value = v.getDecimal();
                  VariantSchema.IntegralSize size = integralType.size;
                  switch (size) {
                     case BYTE:
                        if (value.compareTo(BigDecimal.valueOf((long)value.byteValue())) == 0) {
                           return value.byteValue();
                        }

                        return null;
                     case SHORT:
                        if (value.compareTo(BigDecimal.valueOf((long)value.shortValue())) == 0) {
                           return value.shortValue();
                        }

                        return null;
                     case INT:
                        if (value.compareTo(BigDecimal.valueOf((long)value.intValue())) == 0) {
                           return value.intValue();
                        }

                        return null;
                     case LONG:
                        if (value.compareTo(BigDecimal.valueOf(value.longValue())) == 0) {
                           return value.longValue();
                        }
                  }
               }
            }
            break;
         case BOOLEAN:
            if (targetType instanceof VariantSchema.BooleanType) {
               return v.getBoolean();
            }
            break;
         case STRING:
            if (targetType instanceof VariantSchema.StringType) {
               return v.getString();
            }
            break;
         case DOUBLE:
            if (targetType instanceof VariantSchema.DoubleType) {
               return v.getDouble();
            }
            break;
         case DATE:
            if (targetType instanceof VariantSchema.DateType) {
               return (int)v.getLong();
            }
            break;
         case TIMESTAMP:
            if (targetType instanceof VariantSchema.TimestampType) {
               return v.getLong();
            }
            break;
         case TIMESTAMP_NTZ:
            if (targetType instanceof VariantSchema.TimestampNTZType) {
               return v.getLong();
            }
            break;
         case FLOAT:
            if (targetType instanceof VariantSchema.FloatType) {
               return v.getFloat();
            }
            break;
         case BINARY:
            if (targetType instanceof VariantSchema.BinaryType) {
               return v.getBinary();
            }
            break;
         case UUID:
            if (targetType instanceof VariantSchema.UuidType) {
               return v.getUuid();
            }
      }

      return null;
   }

   private static void addVariantValueVariant(Variant variantResult, VariantSchema schema, ShreddedResult result) {
      result.addVariantValue(variantResult.getValue());
   }

   public interface ShreddedResult {
      void addArray(ShreddedResult[] var1);

      void addObject(ShreddedResult[] var1);

      void addVariantValue(byte[] var1);

      void addScalar(Object var1);

      void addMetadata(byte[] var1);
   }

   public interface ShreddedResultBuilder {
      ShreddedResult createEmpty(VariantSchema var1);

      boolean allowNumericScaleChanges();
   }
}
