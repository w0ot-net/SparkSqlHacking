package shaded.parquet.com.fasterxml.jackson.databind.jsontype.impl;

import java.io.IOException;
import shaded.parquet.com.fasterxml.jackson.annotation.JsonTypeInfo;
import shaded.parquet.com.fasterxml.jackson.core.JsonGenerator;
import shaded.parquet.com.fasterxml.jackson.core.JsonToken;
import shaded.parquet.com.fasterxml.jackson.core.type.WritableTypeId;
import shaded.parquet.com.fasterxml.jackson.databind.BeanProperty;
import shaded.parquet.com.fasterxml.jackson.databind.jsontype.TypeIdResolver;

public class AsDeductionTypeSerializer extends TypeSerializerBase {
   private static final AsDeductionTypeSerializer INSTANCE = new AsDeductionTypeSerializer();

   protected AsDeductionTypeSerializer() {
      super((TypeIdResolver)null, (BeanProperty)null);
   }

   public static AsDeductionTypeSerializer instance() {
      return INSTANCE;
   }

   public AsDeductionTypeSerializer forProperty(BeanProperty prop) {
      return this;
   }

   public JsonTypeInfo.As getTypeInclusion() {
      return JsonTypeInfo.As.EXISTING_PROPERTY;
   }

   public WritableTypeId writeTypePrefix(JsonGenerator g, WritableTypeId idMetadata) throws IOException {
      if (idMetadata.valueShape.isStructStart()) {
         if (g.canWriteTypeId()) {
            idMetadata.wrapperWritten = false;
            if (idMetadata.valueShape == JsonToken.START_OBJECT) {
               g.writeStartObject(idMetadata.forValue);
            } else if (idMetadata.valueShape == JsonToken.START_ARRAY) {
               g.writeStartArray(idMetadata.forValue);
            }

            return idMetadata;
         } else {
            return g.writeTypePrefix(idMetadata);
         }
      } else {
         return null;
      }
   }

   public WritableTypeId writeTypeSuffix(JsonGenerator g, WritableTypeId idMetadata) throws IOException {
      return idMetadata == null ? null : g.writeTypeSuffix(idMetadata);
   }
}
