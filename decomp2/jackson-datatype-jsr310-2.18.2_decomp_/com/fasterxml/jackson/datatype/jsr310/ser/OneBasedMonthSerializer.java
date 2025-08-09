package com.fasterxml.jackson.datatype.jsr310.ser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.time.Month;

public class OneBasedMonthSerializer extends JsonSerializer {
   private final JsonSerializer _defaultSerializer;

   public OneBasedMonthSerializer(JsonSerializer defaultSerializer) {
      this._defaultSerializer = defaultSerializer;
   }

   public void serialize(Month value, JsonGenerator gen, SerializerProvider ctxt) throws IOException {
      if (ctxt.isEnabled(SerializationFeature.WRITE_ENUMS_USING_INDEX)) {
         gen.writeNumber(value.ordinal() + 1);
      } else {
         this._defaultSerializer.serialize(value, gen, ctxt);
      }
   }
}
