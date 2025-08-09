package org.apache.logging.log4j.core.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import org.apache.logging.log4j.core.impl.ContextDataFactory;
import org.apache.logging.log4j.util.StringMap;

public class ContextDataDeserializer extends StdDeserializer {
   private static final long serialVersionUID = 1L;

   ContextDataDeserializer() {
      super(StringMap.class);
   }

   public StringMap deserialize(final JsonParser jp, final DeserializationContext ctxt) throws IOException {
      StringMap contextData = ContextDataFactory.createContextData();

      while(jp.nextToken() != JsonToken.END_OBJECT) {
         String fieldName = jp.currentName();
         jp.nextToken();
         contextData.putValue(fieldName, jp.getText());
      }

      return contextData;
   }
}
