package org.apache.logging.log4j.core.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import java.util.List;
import org.apache.logging.log4j.core.impl.ContextDataFactory;
import org.apache.logging.log4j.util.StringMap;

public class ContextDataAsEntryListDeserializer extends StdDeserializer {
   private static final long serialVersionUID = 1L;

   ContextDataAsEntryListDeserializer() {
      super(StringMap.class);
   }

   public StringMap deserialize(final JsonParser jp, final DeserializationContext ctxt) throws IOException {
      List<MapEntry> list = (List)jp.readValueAs(new TypeReference() {
      });
      StringMap contextData = ContextDataFactory.createContextData();

      for(MapEntry mapEntry : list) {
         contextData.putValue(mapEntry.getKey(), mapEntry.getValue());
      }

      return contextData;
   }
}
