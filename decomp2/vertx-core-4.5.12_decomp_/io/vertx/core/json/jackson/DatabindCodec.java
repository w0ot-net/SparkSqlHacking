package io.vertx.core.json.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.netty.buffer.ByteBufInputStream;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.DecodeException;
import io.vertx.core.json.EncodeException;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class DatabindCodec extends JacksonCodec {
   private static final ObjectMapper mapper;
   private static final ObjectMapper prettyMapper;

   private static void initialize(ObjectMapper om, boolean prettyPrint) {
      om.configure(Feature.ALLOW_COMMENTS, true);
      if (prettyPrint) {
         om.configure(SerializationFeature.INDENT_OUTPUT, true);
      }

      VertxModule module = new VertxModule();
      om.registerModule(module);
   }

   public static ObjectMapper mapper() {
      return mapper;
   }

   /** @deprecated */
   @Deprecated
   public static ObjectMapper prettyMapper() {
      return prettyMapper;
   }

   public Object fromValue(Object json, Class clazz) {
      T value = (T)mapper.convertValue(json, clazz);
      if (clazz == Object.class) {
         value = (T)adapt(value);
      }

      return value;
   }

   public Object fromValue(Object json, TypeReference type) {
      T value = (T)mapper.convertValue(json, type);
      if (type.getType() == Object.class) {
         value = (T)adapt(value);
      }

      return value;
   }

   public Object fromString(String str, Class clazz) throws DecodeException {
      return fromParser(createParser(str), clazz);
   }

   public Object fromString(String str, TypeReference typeRef) throws DecodeException {
      return fromParser(createParser(str), typeRef);
   }

   public Object fromBuffer(Buffer buf, Class clazz) throws DecodeException {
      return fromParser(createParser(buf), clazz);
   }

   public Object fromBuffer(Buffer buf, TypeReference typeRef) throws DecodeException {
      return fromParser(createParser(buf), typeRef);
   }

   public static JsonParser createParser(Buffer buf) {
      try {
         return mapper.getFactory().createParser(new ByteBufInputStream(buf.getByteBuf()));
      } catch (IOException e) {
         throw new DecodeException("Failed to decode:" + e.getMessage(), e);
      }
   }

   public static JsonParser createParser(String str) {
      try {
         return mapper.getFactory().createParser(str);
      } catch (IOException e) {
         throw new DecodeException("Failed to decode:" + e.getMessage(), e);
      }
   }

   public static Object fromParser(JsonParser parser, Class type) throws DecodeException {
      T value;
      JsonToken remaining;
      try {
         value = (T)mapper.readValue(parser, type);
         remaining = parser.nextToken();
      } catch (Exception e) {
         throw new DecodeException("Failed to decode:" + e.getMessage(), e);
      } finally {
         close(parser);
      }

      if (remaining != null) {
         throw new DecodeException("Unexpected trailing token");
      } else {
         if (type == Object.class) {
            value = (T)adapt(value);
         }

         return value;
      }
   }

   private static Object fromParser(JsonParser parser, TypeReference type) throws DecodeException {
      T value;
      try {
         value = (T)mapper.readValue(parser, type);
      } catch (Exception e) {
         throw new DecodeException("Failed to decode:" + e.getMessage(), e);
      } finally {
         close(parser);
      }

      if (type.getType() == Object.class) {
         value = (T)adapt(value);
      }

      return value;
   }

   public String toString(Object object, boolean pretty) throws EncodeException {
      try {
         String result;
         if (pretty) {
            result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
         } else {
            result = mapper.writeValueAsString(object);
         }

         return result;
      } catch (Exception e) {
         throw new EncodeException("Failed to encode as JSON: " + e.getMessage());
      }
   }

   public Buffer toBuffer(Object object, boolean pretty) throws EncodeException {
      try {
         byte[] result;
         if (pretty) {
            result = mapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(object);
         } else {
            result = mapper.writeValueAsBytes(object);
         }

         return Buffer.buffer(result);
      } catch (Exception e) {
         throw new EncodeException("Failed to encode as JSON: " + e.getMessage());
      }
   }

   private static Object adapt(Object o) {
      try {
         if (o instanceof List) {
            List list = (List)o;
            return new JsonArray(list);
         } else if (o instanceof Map) {
            Map<String, Object> map = (Map)o;
            return new JsonObject(map);
         } else {
            return o;
         }
      } catch (Exception e) {
         throw new DecodeException("Failed to decode: " + e.getMessage());
      }
   }

   static {
      mapper = new ObjectMapper(JacksonCodec.factory);
      prettyMapper = new ObjectMapper();
      initialize(mapper, false);
      initialize(prettyMapper, true);
   }
}
