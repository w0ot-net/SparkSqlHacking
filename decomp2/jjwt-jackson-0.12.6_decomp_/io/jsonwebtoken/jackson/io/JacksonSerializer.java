package io.jsonwebtoken.jackson.io;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.module.SimpleModule;
import io.jsonwebtoken.io.AbstractSerializer;
import io.jsonwebtoken.lang.Assert;
import java.io.OutputStream;

public class JacksonSerializer extends AbstractSerializer {
   static final String MODULE_ID = "jjwt-jackson";
   static final Module MODULE;
   static final ObjectMapper DEFAULT_OBJECT_MAPPER;
   protected final ObjectMapper objectMapper;

   static ObjectMapper newObjectMapper() {
      return (new ObjectMapper()).registerModule(MODULE).configure(Feature.STRICT_DUPLICATE_DETECTION, true).configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
   }

   public JacksonSerializer() {
      this(DEFAULT_OBJECT_MAPPER);
   }

   public JacksonSerializer(ObjectMapper objectMapper) {
      Assert.notNull(objectMapper, "ObjectMapper cannot be null.");
      this.objectMapper = objectMapper.registerModule(MODULE);
   }

   protected void doSerialize(Object t, OutputStream out) throws Exception {
      Assert.notNull(out, "OutputStream cannot be null.");
      ObjectWriter writer = this.objectMapper.writer().without(com.fasterxml.jackson.core.JsonGenerator.Feature.AUTO_CLOSE_TARGET);
      writer.writeValue(out, t);
   }

   static {
      SimpleModule module = new SimpleModule("jjwt-jackson");
      module.addSerializer(JacksonSupplierSerializer.INSTANCE);
      MODULE = module;
      DEFAULT_OBJECT_MAPPER = newObjectMapper();
   }
}
