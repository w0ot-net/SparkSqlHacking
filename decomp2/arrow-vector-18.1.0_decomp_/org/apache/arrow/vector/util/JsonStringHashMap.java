package org.apache.arrow.vector.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.LinkedHashMap;

public class JsonStringHashMap extends LinkedHashMap {
   private static final ObjectMapper MAPPER = ObjectMapperFactory.newObjectMapper();

   public final String toString() {
      try {
         return MAPPER.writeValueAsString(this);
      } catch (JsonProcessingException e) {
         throw new IllegalStateException("Cannot serialize hash map to JSON string", e);
      }
   }
}
