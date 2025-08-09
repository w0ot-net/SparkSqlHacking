package shaded.parquet.com.fasterxml.jackson.databind.ser.std;

import java.io.IOException;
import java.lang.reflect.Type;
import shaded.parquet.com.fasterxml.jackson.core.JsonGenerator;
import shaded.parquet.com.fasterxml.jackson.core.JsonToken;
import shaded.parquet.com.fasterxml.jackson.core.type.WritableTypeId;
import shaded.parquet.com.fasterxml.jackson.databind.JavaType;
import shaded.parquet.com.fasterxml.jackson.databind.JsonMappingException;
import shaded.parquet.com.fasterxml.jackson.databind.JsonNode;
import shaded.parquet.com.fasterxml.jackson.databind.SerializerProvider;
import shaded.parquet.com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import shaded.parquet.com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import shaded.parquet.com.fasterxml.jackson.databind.jsontype.TypeSerializer;

@JacksonStdImpl
public class ToEmptyObjectSerializer extends StdSerializer {
   protected ToEmptyObjectSerializer(Class raw) {
      super(raw, false);
   }

   public ToEmptyObjectSerializer(JavaType type) {
      super(type);
   }

   public void serialize(Object value, JsonGenerator gen, SerializerProvider ctxt) throws IOException {
      gen.writeStartObject(value, 0);
      gen.writeEndObject();
   }

   public void serializeWithType(Object value, JsonGenerator gen, SerializerProvider ctxt, TypeSerializer typeSer) throws IOException {
      WritableTypeId typeIdDef = typeSer.writeTypePrefix(gen, typeSer.typeId(value, JsonToken.START_OBJECT));
      typeSer.writeTypeSuffix(gen, typeIdDef);
   }

   public boolean isEmpty(SerializerProvider provider, Object value) {
      return true;
   }

   /** @deprecated */
   @Deprecated
   public JsonNode getSchema(SerializerProvider provider, Type typeHint) throws JsonMappingException {
      return null;
   }

   public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
      visitor.expectObjectFormat(typeHint);
   }
}
