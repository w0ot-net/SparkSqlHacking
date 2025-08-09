package org.apache.spark.types.variant;

import java.util.HashMap;
import java.util.Map;

public class VariantSchema {
   public final int typedIdx;
   public final int variantIdx;
   public final int topLevelMetadataIdx;
   public final int numFields;
   public final ScalarType scalarSchema;
   public final ObjectField[] objectSchema;
   public final Map objectSchemaMap;
   public final VariantSchema arraySchema;

   public VariantSchema(int typedIdx, int variantIdx, int topLevelMetadataIdx, int numFields, ScalarType scalarSchema, ObjectField[] objectSchema, VariantSchema arraySchema) {
      this.typedIdx = typedIdx;
      this.numFields = numFields;
      this.variantIdx = variantIdx;
      this.topLevelMetadataIdx = topLevelMetadataIdx;
      this.scalarSchema = scalarSchema;
      this.objectSchema = objectSchema;
      if (objectSchema != null) {
         this.objectSchemaMap = new HashMap();

         for(int i = 0; i < objectSchema.length; ++i) {
            this.objectSchemaMap.put(objectSchema[i].fieldName, i);
         }
      } else {
         this.objectSchemaMap = null;
      }

      this.arraySchema = arraySchema;
   }

   public boolean isUnshredded() {
      return this.topLevelMetadataIdx >= 0 && this.variantIdx >= 0 && this.typedIdx < 0;
   }

   public String toString() {
      int var10000 = this.typedIdx;
      return "VariantSchema{typedIdx=" + var10000 + ", variantIdx=" + this.variantIdx + ", topLevelMetadataIdx=" + this.topLevelMetadataIdx + ", numFields=" + this.numFields + ", scalarSchema=" + String.valueOf(this.scalarSchema) + ", objectSchema=" + String.valueOf(this.objectSchema) + ", arraySchema=" + String.valueOf(this.arraySchema) + "}";
   }

   public static final class ObjectField {
      public final String fieldName;
      public final VariantSchema schema;

      public ObjectField(String fieldName, VariantSchema schema) {
         this.fieldName = fieldName;
         this.schema = schema;
      }

      public String toString() {
         String var10000 = this.fieldName;
         return "ObjectField{fieldName=" + var10000 + ", schema=" + String.valueOf(this.schema) + "}";
      }
   }

   public abstract static class ScalarType {
   }

   public static final class StringType extends ScalarType {
   }

   public static enum IntegralSize {
      BYTE,
      SHORT,
      INT,
      LONG;

      // $FF: synthetic method
      private static IntegralSize[] $values() {
         return new IntegralSize[]{BYTE, SHORT, INT, LONG};
      }
   }

   public static final class IntegralType extends ScalarType {
      public final IntegralSize size;

      public IntegralType(IntegralSize size) {
         this.size = size;
      }
   }

   public static final class FloatType extends ScalarType {
   }

   public static final class DoubleType extends ScalarType {
   }

   public static final class BooleanType extends ScalarType {
   }

   public static final class BinaryType extends ScalarType {
   }

   public static final class DecimalType extends ScalarType {
      public final int precision;
      public final int scale;

      public DecimalType(int precision, int scale) {
         this.precision = precision;
         this.scale = scale;
      }
   }

   public static final class DateType extends ScalarType {
   }

   public static final class TimestampType extends ScalarType {
   }

   public static final class TimestampNTZType extends ScalarType {
   }

   public static final class UuidType extends ScalarType {
   }
}
