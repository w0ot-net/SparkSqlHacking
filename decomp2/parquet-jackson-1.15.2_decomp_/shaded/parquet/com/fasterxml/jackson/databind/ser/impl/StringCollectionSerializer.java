package shaded.parquet.com.fasterxml.jackson.databind.ser.impl;

import java.io.IOException;
import java.util.Collection;
import shaded.parquet.com.fasterxml.jackson.core.JsonGenerator;
import shaded.parquet.com.fasterxml.jackson.core.JsonToken;
import shaded.parquet.com.fasterxml.jackson.core.type.WritableTypeId;
import shaded.parquet.com.fasterxml.jackson.databind.BeanProperty;
import shaded.parquet.com.fasterxml.jackson.databind.JsonMappingException;
import shaded.parquet.com.fasterxml.jackson.databind.JsonNode;
import shaded.parquet.com.fasterxml.jackson.databind.JsonSerializer;
import shaded.parquet.com.fasterxml.jackson.databind.SerializationFeature;
import shaded.parquet.com.fasterxml.jackson.databind.SerializerProvider;
import shaded.parquet.com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import shaded.parquet.com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import shaded.parquet.com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;
import shaded.parquet.com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import shaded.parquet.com.fasterxml.jackson.databind.ser.std.StaticListSerializerBase;

@JacksonStdImpl
public class StringCollectionSerializer extends StaticListSerializerBase {
   public static final StringCollectionSerializer instance = new StringCollectionSerializer();

   protected StringCollectionSerializer() {
      super(Collection.class);
   }

   protected StringCollectionSerializer(StringCollectionSerializer src, Boolean unwrapSingle) {
      super(src, unwrapSingle);
   }

   public JsonSerializer _withResolved(BeanProperty prop, Boolean unwrapSingle) {
      return new StringCollectionSerializer(this, unwrapSingle);
   }

   protected JsonNode contentSchema() {
      return this.createSchemaNode("string", true);
   }

   protected void acceptContentVisitor(JsonArrayFormatVisitor visitor) throws JsonMappingException {
      visitor.itemsFormat(JsonFormatTypes.STRING);
   }

   public void serialize(Collection value, JsonGenerator g, SerializerProvider provider) throws IOException {
      int len = value.size();
      if (len != 1 || (this._unwrapSingle != null || !provider.isEnabled(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED)) && this._unwrapSingle != Boolean.TRUE) {
         g.writeStartArray(value, len);
         this.serializeContents(value, g, provider);
         g.writeEndArray();
      } else {
         this.serializeContents(value, g, provider);
      }
   }

   public void serializeWithType(Collection value, JsonGenerator g, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
      WritableTypeId typeIdDef = typeSer.writeTypePrefix(g, typeSer.typeId(value, JsonToken.START_ARRAY));
      g.assignCurrentValue(value);
      this.serializeContents(value, g, provider);
      typeSer.writeTypeSuffix(g, typeIdDef);
   }

   private final void serializeContents(Collection value, JsonGenerator g, SerializerProvider provider) throws IOException {
      int i = 0;

      try {
         for(String str : value) {
            if (str == null) {
               provider.defaultSerializeNull(g);
            } else {
               g.writeString(str);
            }

            ++i;
         }
      } catch (Exception e) {
         this.wrapAndThrow(provider, e, value, i);
      }

   }
}
