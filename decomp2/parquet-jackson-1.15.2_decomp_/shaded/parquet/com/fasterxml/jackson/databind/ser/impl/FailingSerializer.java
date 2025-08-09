package shaded.parquet.com.fasterxml.jackson.databind.ser.impl;

import java.io.IOException;
import shaded.parquet.com.fasterxml.jackson.core.JsonGenerator;
import shaded.parquet.com.fasterxml.jackson.databind.SerializerProvider;
import shaded.parquet.com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class FailingSerializer extends StdSerializer {
   protected final String _msg;

   public FailingSerializer(String msg) {
      super(Object.class);
      this._msg = msg;
   }

   public void serialize(Object value, JsonGenerator g, SerializerProvider ctxt) throws IOException {
      ctxt.reportMappingProblem(this._msg);
   }
}
