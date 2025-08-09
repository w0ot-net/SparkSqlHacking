package org.apache.arrow.vector.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;

public class JsonStringArrayList extends ArrayList {
   private static final ObjectMapper MAPPER = ObjectMapperFactory.newObjectMapper();

   public JsonStringArrayList() {
   }

   public JsonStringArrayList(int size) {
      super(size);
   }

   public final String toString() {
      try {
         return MAPPER.writeValueAsString(this);
      } catch (JsonProcessingException e) {
         throw new IllegalStateException("Cannot serialize array list to JSON string", e);
      }
   }
}
