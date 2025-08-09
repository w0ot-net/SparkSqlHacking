package shaded.parquet.com.fasterxml.jackson.databind.deser.impl;

import java.io.IOException;
import shaded.parquet.com.fasterxml.jackson.core.JsonParser;
import shaded.parquet.com.fasterxml.jackson.databind.DeserializationContext;
import shaded.parquet.com.fasterxml.jackson.databind.JsonDeserializer;

public class ErrorThrowingDeserializer extends JsonDeserializer {
   private final Error _cause;

   public ErrorThrowingDeserializer(NoClassDefFoundError cause) {
      this._cause = cause;
   }

   public Object deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
      throw this._cause;
   }
}
