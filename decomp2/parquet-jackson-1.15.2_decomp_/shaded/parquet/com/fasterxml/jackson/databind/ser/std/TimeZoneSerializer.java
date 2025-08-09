package shaded.parquet.com.fasterxml.jackson.databind.ser.std;

import java.io.IOException;
import java.util.TimeZone;
import shaded.parquet.com.fasterxml.jackson.core.JsonGenerator;
import shaded.parquet.com.fasterxml.jackson.core.JsonToken;
import shaded.parquet.com.fasterxml.jackson.core.type.WritableTypeId;
import shaded.parquet.com.fasterxml.jackson.databind.SerializerProvider;
import shaded.parquet.com.fasterxml.jackson.databind.jsontype.TypeSerializer;

public class TimeZoneSerializer extends StdScalarSerializer {
   public TimeZoneSerializer() {
      super(TimeZone.class);
   }

   public void serialize(TimeZone value, JsonGenerator g, SerializerProvider provider) throws IOException {
      g.writeString(value.getID());
   }

   public void serializeWithType(TimeZone value, JsonGenerator g, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
      WritableTypeId typeIdDef = typeSer.writeTypePrefix(g, typeSer.typeId(value, (Class)TimeZone.class, (JsonToken)JsonToken.VALUE_STRING));
      this.serialize(value, g, provider);
      typeSer.writeTypeSuffix(g, typeIdDef);
   }
}
