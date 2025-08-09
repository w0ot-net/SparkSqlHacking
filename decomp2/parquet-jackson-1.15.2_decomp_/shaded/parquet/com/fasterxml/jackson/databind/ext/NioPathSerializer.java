package shaded.parquet.com.fasterxml.jackson.databind.ext;

import java.io.IOException;
import java.nio.file.Path;
import shaded.parquet.com.fasterxml.jackson.core.JsonGenerator;
import shaded.parquet.com.fasterxml.jackson.core.JsonToken;
import shaded.parquet.com.fasterxml.jackson.core.type.WritableTypeId;
import shaded.parquet.com.fasterxml.jackson.databind.SerializerProvider;
import shaded.parquet.com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import shaded.parquet.com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;

public class NioPathSerializer extends StdScalarSerializer {
   private static final long serialVersionUID = 1L;

   public NioPathSerializer() {
      super(Path.class);
   }

   public void serialize(Path value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
      gen.writeString(value.toUri().toString());
   }

   public void serializeWithType(Path value, JsonGenerator g, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
      WritableTypeId typeIdDef = typeSer.writeTypePrefix(g, typeSer.typeId(value, (Class)Path.class, (JsonToken)JsonToken.VALUE_STRING));
      this.serialize(value, g, provider);
      typeSer.writeTypeSuffix(g, typeIdDef);
   }
}
