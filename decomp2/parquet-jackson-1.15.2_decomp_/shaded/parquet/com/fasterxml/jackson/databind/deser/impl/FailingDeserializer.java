package shaded.parquet.com.fasterxml.jackson.databind.deser.impl;

import java.io.IOException;
import shaded.parquet.com.fasterxml.jackson.core.JsonParser;
import shaded.parquet.com.fasterxml.jackson.databind.DeserializationContext;
import shaded.parquet.com.fasterxml.jackson.databind.JsonDeserializer;
import shaded.parquet.com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class FailingDeserializer extends StdDeserializer {
   private static final long serialVersionUID = 1L;
   protected final String _message;

   public FailingDeserializer(String m) {
      this(Object.class, m);
   }

   public FailingDeserializer(Class rawType, String m) {
      super(rawType);
      this._message = m;
   }

   public Object deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
      ctxt.reportInputMismatch((JsonDeserializer)this, this._message);
      return null;
   }
}
