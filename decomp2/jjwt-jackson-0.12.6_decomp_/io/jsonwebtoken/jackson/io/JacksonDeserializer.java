package io.jsonwebtoken.jackson.io;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.UntypedObjectDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import io.jsonwebtoken.io.AbstractDeserializer;
import io.jsonwebtoken.lang.Assert;
import java.io.IOException;
import java.io.Reader;
import java.util.Collections;
import java.util.Map;

public class JacksonDeserializer extends AbstractDeserializer {
   private final Class returnType;
   private final ObjectMapper objectMapper;

   public JacksonDeserializer() {
      this(JacksonSerializer.DEFAULT_OBJECT_MAPPER);
   }

   public JacksonDeserializer(Map claimTypeMap) {
      this(JacksonSerializer.newObjectMapper(), claimTypeMap);
   }

   public JacksonDeserializer(ObjectMapper objectMapper) {
      this(objectMapper, Object.class);
   }

   private JacksonDeserializer(ObjectMapper objectMapper, Map claimTypeMap) {
      this(objectMapper);
      Assert.notNull(claimTypeMap, "Claim type map cannot be null.");
      SimpleModule module = new SimpleModule();
      module.addDeserializer(Object.class, new MappedTypeDeserializer(Collections.unmodifiableMap(claimTypeMap)));
      objectMapper.registerModule(module);
   }

   private JacksonDeserializer(ObjectMapper objectMapper, Class returnType) {
      Assert.notNull(objectMapper, "ObjectMapper cannot be null.");
      Assert.notNull(returnType, "Return type cannot be null.");
      this.objectMapper = objectMapper;
      this.returnType = returnType;
   }

   protected Object doDeserialize(Reader reader) throws Exception {
      return this.objectMapper.readValue(reader, this.returnType);
   }

   private static class MappedTypeDeserializer extends UntypedObjectDeserializer {
      private final Map claimTypeMap;

      private MappedTypeDeserializer(Map claimTypeMap) {
         super((JavaType)null, (JavaType)null);
         this.claimTypeMap = claimTypeMap;
      }

      public Object deserialize(JsonParser parser, DeserializationContext context) throws IOException {
         String name = parser.currentName();
         if (this.claimTypeMap != null && name != null && this.claimTypeMap.containsKey(name)) {
            Class<?> type = (Class)this.claimTypeMap.get(name);
            return parser.readValueAsTree().traverse(parser.getCodec()).readValueAs(type);
         } else {
            return super.deserialize(parser, context);
         }
      }
   }
}
