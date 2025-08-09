package org.apache.logging.log4j.core.jackson;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import org.apache.logging.log4j.util.ReadOnlyStringMap;
import org.apache.logging.log4j.util.TriConsumer;

public class ContextDataSerializer extends StdSerializer {
   private static final long serialVersionUID = 1L;
   private static final TriConsumer WRITE_STRING_FIELD_INTO = (key, value, jsonGenerator) -> {
      try {
         if (value == null) {
            jsonGenerator.writeNullField(key);
         } else {
            jsonGenerator.writeStringField(key, String.valueOf(value));
         }

      } catch (Exception ex) {
         throw new IllegalStateException("Problem with key " + key, ex);
      }
   };

   protected ContextDataSerializer() {
      super(ReadOnlyStringMap.class);
   }

   public void serialize(final ReadOnlyStringMap contextData, final JsonGenerator jgen, final SerializerProvider provider) throws IOException, JsonGenerationException {
      jgen.writeStartObject();
      contextData.forEach(WRITE_STRING_FIELD_INTO, jgen);
      jgen.writeEndObject();
   }
}
