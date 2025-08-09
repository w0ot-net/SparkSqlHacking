package shaded.parquet.com.fasterxml.jackson.databind.ser.std;

import java.io.IOException;
import shaded.parquet.com.fasterxml.jackson.core.JsonGenerator;
import shaded.parquet.com.fasterxml.jackson.databind.SerializerProvider;

/** @deprecated */
@Deprecated
public class StdKeySerializer extends StdSerializer {
   public StdKeySerializer() {
      super(Object.class);
   }

   public void serialize(Object value, JsonGenerator g, SerializerProvider provider) throws IOException {
      g.writeFieldName(value.toString());
   }
}
