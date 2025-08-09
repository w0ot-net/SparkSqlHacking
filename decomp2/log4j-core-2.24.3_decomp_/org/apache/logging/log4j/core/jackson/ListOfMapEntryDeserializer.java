package org.apache.logging.log4j.core.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListOfMapEntryDeserializer extends StdDeserializer {
   private static final long serialVersionUID = 1L;

   ListOfMapEntryDeserializer() {
      super(Map.class);
   }

   public Map deserialize(final JsonParser jp, final DeserializationContext ctxt) throws IOException, JsonProcessingException {
      List<MapEntry> list = (List)jp.readValueAs(new TypeReference() {
      });
      HashMap<String, String> map = new HashMap(list.size());

      for(MapEntry mapEntry : list) {
         map.put(mapEntry.getKey(), mapEntry.getValue());
      }

      return map;
   }
}
