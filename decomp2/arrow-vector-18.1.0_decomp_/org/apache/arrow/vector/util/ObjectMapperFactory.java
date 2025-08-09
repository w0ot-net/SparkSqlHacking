package org.apache.arrow.vector.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public final class ObjectMapperFactory {
   private ObjectMapperFactory() {
   }

   public static ObjectMapper newObjectMapper() {
      return ((JsonMapper.Builder)JsonMapper.builder().addModule(new JavaTimeModule())).build();
   }
}
