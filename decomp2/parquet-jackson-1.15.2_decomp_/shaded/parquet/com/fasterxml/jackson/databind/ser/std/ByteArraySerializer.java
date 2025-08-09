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
import shaded.parquet.com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import shaded.parquet.com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;
import shaded.parquet.com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import shaded.parquet.com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import shaded.parquet.com.fasterxml.jackson.databind.node.ObjectNode;

@JacksonStdImpl
public class ByteArraySerializer extends StdSerializer {
   private static final long serialVersionUID = 1L;

   public ByteArraySerializer() {
      super(byte[].class);
   }

   public boolean isEmpty(SerializerProvider prov, byte[] value) {
      return value.length == 0;
   }

   public void serialize(byte[] value, JsonGenerator g, SerializerProvider provider) throws IOException {
      g.writeBinary(provider.getConfig().getBase64Variant(), value, 0, value.length);
   }

   public void serializeWithType(byte[] value, JsonGenerator g, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
      WritableTypeId typeIdDef = typeSer.writeTypePrefix(g, typeSer.typeId(value, JsonToken.VALUE_EMBEDDED_OBJECT));
      g.writeBinary(provider.getConfig().getBase64Variant(), value, 0, value.length);
      typeSer.writeTypeSuffix(g, typeIdDef);
   }

   /** @deprecated */
   @Deprecated
   public JsonNode getSchema(SerializerProvider provider, Type typeHint) {
      ObjectNode o = this.createSchemaNode("array", true);
      ObjectNode itemSchema = this.createSchemaNode("byte");
      return o.set("items", itemSchema);
   }

   public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
      JsonArrayFormatVisitor v2 = visitor.expectArrayFormat(typeHint);
      if (v2 != null) {
         v2.itemsFormat(JsonFormatTypes.INTEGER);
      }

   }
}
