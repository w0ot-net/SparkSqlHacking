package shaded.parquet.com.fasterxml.jackson.databind.ser.std;

import java.io.IOException;
import java.util.EnumSet;
import shaded.parquet.com.fasterxml.jackson.core.JsonGenerator;
import shaded.parquet.com.fasterxml.jackson.databind.BeanProperty;
import shaded.parquet.com.fasterxml.jackson.databind.JavaType;
import shaded.parquet.com.fasterxml.jackson.databind.JsonSerializer;
import shaded.parquet.com.fasterxml.jackson.databind.SerializationFeature;
import shaded.parquet.com.fasterxml.jackson.databind.SerializerProvider;
import shaded.parquet.com.fasterxml.jackson.databind.jsontype.TypeSerializer;

public class EnumSetSerializer extends AsArraySerializerBase {
   public EnumSetSerializer(JavaType elemType) {
      super(EnumSet.class, elemType, true, (TypeSerializer)null, (JsonSerializer)null);
   }

   public EnumSetSerializer(EnumSetSerializer src, BeanProperty property, TypeSerializer vts, JsonSerializer valueSerializer, Boolean unwrapSingle) {
      super(src, property, vts, valueSerializer, unwrapSingle);
   }

   public EnumSetSerializer _withValueTypeSerializer(TypeSerializer vts) {
      return this;
   }

   public EnumSetSerializer withResolved(BeanProperty property, TypeSerializer vts, JsonSerializer elementSerializer, Boolean unwrapSingle) {
      return new EnumSetSerializer(this, property, vts, elementSerializer, unwrapSingle);
   }

   public boolean isEmpty(SerializerProvider prov, EnumSet value) {
      return value.isEmpty();
   }

   public boolean hasSingleElement(EnumSet value) {
      return value.size() == 1;
   }

   public final void serialize(EnumSet value, JsonGenerator gen, SerializerProvider provider) throws IOException {
      int len = value.size();
      if (len != 1 || (this._unwrapSingle != null || !provider.isEnabled(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED)) && this._unwrapSingle != Boolean.TRUE) {
         gen.writeStartArray(value, len);
         this.serializeContents(value, gen, provider);
         gen.writeEndArray();
      } else {
         this.serializeContents(value, gen, provider);
      }
   }

   public void serializeContents(EnumSet value, JsonGenerator gen, SerializerProvider provider) throws IOException {
      JsonSerializer<Object> enumSer = this._elementSerializer;

      for(Enum en : value) {
         if (enumSer == null) {
            enumSer = provider.findContentValueSerializer(en.getDeclaringClass(), this._property);
         }

         enumSer.serialize(en, gen, provider);
      }

   }
}
