package shaded.parquet.com.fasterxml.jackson.databind.ser.impl;

import java.io.IOException;
import shaded.parquet.com.fasterxml.jackson.core.JsonGenerator;
import shaded.parquet.com.fasterxml.jackson.databind.JavaType;
import shaded.parquet.com.fasterxml.jackson.databind.SerializerProvider;
import shaded.parquet.com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class UnsupportedTypeSerializer extends StdSerializer {
   private static final long serialVersionUID = 1L;
   protected final JavaType _type;
   protected final String _message;

   public UnsupportedTypeSerializer(JavaType t, String msg) {
      super(Object.class);
      this._type = t;
      this._message = msg;
   }

   public void serialize(Object value, JsonGenerator g, SerializerProvider ctxt) throws IOException {
      ctxt.reportBadDefinition(this._type, this._message);
   }
}
