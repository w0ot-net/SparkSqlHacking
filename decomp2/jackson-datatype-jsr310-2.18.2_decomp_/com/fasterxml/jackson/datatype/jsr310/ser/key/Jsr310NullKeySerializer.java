package com.fasterxml.jackson.datatype.jsr310.ser.key;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

/** @deprecated */
@Deprecated
public class Jsr310NullKeySerializer extends JsonSerializer {
   public static final String NULL_KEY = "";

   public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
      if (value != null) {
         throw JsonMappingException.from(gen, "Jsr310NullKeySerializer is only for serializing null values.");
      } else {
         gen.writeFieldName("");
      }
   }
}
