package io.vertx.core.json.jackson;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.TSFBuilder;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.io.SegmentedStringWriter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.BufferRecycler;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import io.netty.buffer.ByteBufInputStream;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.DecodeException;
import io.vertx.core.json.EncodeException;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.json.impl.JsonUtil;
import io.vertx.core.spi.json.JsonCodec;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class JacksonCodec implements JsonCodec {
   static final boolean releaseToPool;
   static final JsonFactory factory;

   public Object fromString(String json, Class clazz) throws DecodeException {
      return fromParser(createParser(json), clazz);
   }

   public Object fromString(String str, TypeReference typeRef) throws DecodeException {
      return this.fromString(str, classTypeOf(typeRef));
   }

   public Object fromBuffer(Buffer json, Class clazz) throws DecodeException {
      return fromParser(createParser(json), clazz);
   }

   public Object fromBuffer(Buffer buf, TypeReference typeRef) throws DecodeException {
      return this.fromBuffer(buf, classTypeOf(typeRef));
   }

   public Object fromValue(Object json, Class toValueType) {
      throw new DecodeException("Mapping " + toValueType.getName() + "  is not available without Jackson Databind on the classpath");
   }

   public Object fromValue(Object json, TypeReference type) {
      throw new DecodeException("Mapping " + type.getType().getTypeName() + " is not available without Jackson Databind on the classpath");
   }

   public String toString(Object object, boolean pretty) throws EncodeException {
      BufferRecycler br = factory._getBufferRecycler();

      String var7;
      try {
         SegmentedStringWriter sw = new SegmentedStringWriter(br);
         Throwable var5 = null;

         try {
            JsonGenerator generator = createGenerator((Writer)sw, pretty);
            encodeJson(object, generator);
            generator.close();
            var7 = sw.getAndClear();
         } catch (Throwable var25) {
            var5 = var25;
            throw var25;
         } finally {
            if (sw != null) {
               if (var5 != null) {
                  try {
                     sw.close();
                  } catch (Throwable var24) {
                     var5.addSuppressed(var24);
                  }
               } else {
                  sw.close();
               }
            }

         }
      } catch (IOException e) {
         throw new EncodeException(e.getMessage(), e);
      } finally {
         if (releaseToPool) {
            releaseToPool(br);
         }

      }

      return var7;
   }

   public Buffer toBuffer(Object object, boolean pretty) throws EncodeException {
      BufferRecycler br = factory._getBufferRecycler();

      Buffer var8;
      try {
         ByteArrayBuilder bb = new ByteArrayBuilder(br);
         Throwable var5 = null;

         try {
            JsonGenerator generator = createGenerator((OutputStream)bb, pretty);
            encodeJson(object, generator);
            generator.close();
            byte[] result = bb.toByteArray();
            bb.release();
            var8 = Buffer.buffer(result);
         } catch (Throwable var26) {
            var5 = var26;
            throw var26;
         } finally {
            if (bb != null) {
               if (var5 != null) {
                  try {
                     bb.close();
                  } catch (Throwable var25) {
                     var5.addSuppressed(var25);
                  }
               } else {
                  bb.close();
               }
            }

         }
      } catch (IOException e) {
         throw new EncodeException(e.getMessage(), e);
      } finally {
         if (releaseToPool) {
            releaseToPool(br);
         }

      }

      return var8;
   }

   private static void releaseToPool(BufferRecycler br) {
      br.releaseToPool();
   }

   public static JsonParser createParser(String str) {
      try {
         return factory.createParser(str);
      } catch (IOException e) {
         throw new DecodeException("Failed to decode:" + e.getMessage(), e);
      }
   }

   public static JsonParser createParser(Buffer buf) {
      try {
         return factory.createParser(new ByteBufInputStream(buf.getByteBuf()));
      } catch (IOException e) {
         throw new DecodeException("Failed to decode:" + e.getMessage(), e);
      }
   }

   private static JsonGenerator createGenerator(Writer out, boolean pretty) {
      try {
         JsonGenerator generator = factory.createGenerator(out);
         if (pretty) {
            generator.useDefaultPrettyPrinter();
         }

         return generator;
      } catch (IOException e) {
         throw new DecodeException("Failed to decode:" + e.getMessage(), e);
      }
   }

   private static JsonGenerator createGenerator(OutputStream out, boolean pretty) {
      try {
         JsonGenerator generator = factory.createGenerator(out);
         if (pretty) {
            generator.useDefaultPrettyPrinter();
         }

         return generator;
      } catch (IOException e) {
         throw new DecodeException("Failed to decode:" + e.getMessage(), e);
      }
   }

   public Object fromString(String str) throws DecodeException {
      return fromParser(createParser(str), Object.class);
   }

   public Object fromBuffer(Buffer buf) throws DecodeException {
      return fromParser(createParser(buf), Object.class);
   }

   public static Object fromParser(JsonParser parser, Class type) throws DecodeException {
      Object res;
      JsonToken remaining;
      try {
         parser.nextToken();
         res = parseAny(parser);
         remaining = parser.nextToken();
      } catch (IOException e) {
         throw new DecodeException(e.getMessage(), e);
      } finally {
         close(parser);
      }

      if (remaining != null) {
         throw new DecodeException("Unexpected trailing token");
      } else {
         return cast(res, type);
      }
   }

   private static Object parseAny(JsonParser parser) throws IOException, DecodeException {
      switch (parser.getCurrentTokenId()) {
         case 1:
            return parseObject(parser);
         case 2:
         case 4:
         case 5:
         default:
            throw new DecodeException("Unexpected token");
         case 3:
            return parseArray(parser);
         case 6:
            return parser.getText();
         case 7:
         case 8:
            return parser.getNumberValue();
         case 9:
            return Boolean.TRUE;
         case 10:
            return Boolean.FALSE;
         case 11:
            return null;
      }
   }

   private static Map parseObject(JsonParser parser) throws IOException {
      String key1 = parser.nextFieldName();
      if (key1 == null) {
         return new LinkedHashMap(2);
      } else {
         parser.nextToken();
         Object value1 = parseAny(parser);
         String key2 = parser.nextFieldName();
         if (key2 == null) {
            LinkedHashMap<String, Object> obj = new LinkedHashMap(2);
            obj.put(key1, value1);
            return obj;
         } else {
            parser.nextToken();
            Object value2 = parseAny(parser);
            String key = parser.nextFieldName();
            if (key == null) {
               LinkedHashMap<String, Object> obj = new LinkedHashMap(2);
               obj.put(key1, value1);
               obj.put(key2, value2);
               return obj;
            } else {
               LinkedHashMap<String, Object> obj = new LinkedHashMap();
               obj.put(key1, value1);
               obj.put(key2, value2);

               do {
                  parser.nextToken();
                  Object value = parseAny(parser);
                  obj.put(key, value);
                  key = parser.nextFieldName();
               } while(key != null);

               return obj;
            }
         }
      }
   }

   private static List parseArray(JsonParser parser) throws IOException {
      List<Object> array = new ArrayList();

      while(true) {
         parser.nextToken();
         int tokenId = parser.getCurrentTokenId();
         if (tokenId == 5) {
            throw new UnsupportedOperationException();
         }

         if (tokenId == 4) {
            return array;
         }

         Object value = parseAny(parser);
         array.add(value);
      }
   }

   static void close(Closeable parser) {
      try {
         parser.close();
      } catch (IOException var2) {
      }

   }

   public static void encodeJson(Object json, JsonGenerator generator) throws EncodeException {
      try {
         if (json instanceof JsonObject) {
            json = ((JsonObject)json).getMap();
         } else if (json instanceof JsonArray) {
            json = ((JsonArray)json).getList();
         }

         if (json instanceof Map) {
            generator.writeStartObject();

            for(Map.Entry e : ((Map)json).entrySet()) {
               generator.writeFieldName((String)e.getKey());
               encodeJson(e.getValue(), generator);
            }

            generator.writeEndObject();
         } else if (json instanceof List) {
            generator.writeStartArray();

            for(Object item : (List)json) {
               encodeJson(item, generator);
            }

            generator.writeEndArray();
         } else if (json instanceof String) {
            generator.writeString((String)json);
         } else if (json instanceof Number) {
            if (json instanceof Short) {
               generator.writeNumber((Short)json);
            } else if (json instanceof Integer) {
               generator.writeNumber((Integer)json);
            } else if (json instanceof Long) {
               generator.writeNumber((Long)json);
            } else if (json instanceof Float) {
               generator.writeNumber((Float)json);
            } else if (json instanceof Double) {
               generator.writeNumber((Double)json);
            } else if (json instanceof Byte) {
               generator.writeNumber((short)(Byte)json);
            } else if (json instanceof BigInteger) {
               generator.writeNumber((BigInteger)json);
            } else if (json instanceof BigDecimal) {
               generator.writeNumber((BigDecimal)json);
            } else {
               generator.writeNumber(((Number)json).doubleValue());
            }
         } else if (json instanceof Boolean) {
            generator.writeBoolean((Boolean)json);
         } else if (json instanceof Instant) {
            generator.writeString(DateTimeFormatter.ISO_INSTANT.format((Instant)json));
         } else if (json instanceof byte[]) {
            generator.writeString(JsonUtil.BASE64_ENCODER.encodeToString((byte[])json));
         } else if (json instanceof Buffer) {
            generator.writeString(JsonUtil.BASE64_ENCODER.encodeToString(((Buffer)json).getBytes()));
         } else if (json instanceof Enum) {
            generator.writeString(((Enum)json).name());
         } else {
            if (json != null) {
               throw new EncodeException("Mapping " + json.getClass().getName() + "  is not available without Jackson Databind on the classpath");
            }

            generator.writeNull();
         }

      } catch (IOException e) {
         throw new EncodeException(e.getMessage(), e);
      }
   }

   private static Class classTypeOf(TypeReference typeRef) {
      Type type = typeRef.getType();
      if (type instanceof Class) {
         return (Class)type;
      } else if (type instanceof ParameterizedType) {
         return (Class)((ParameterizedType)type).getRawType();
      } else {
         throw new DecodeException();
      }
   }

   private static Object cast(Object o, Class clazz) {
      if (o instanceof Map) {
         if (!clazz.isAssignableFrom(Map.class)) {
            throw new DecodeException("Failed to decode");
         } else {
            if (clazz == Object.class) {
               o = new JsonObject((Map)o);
            }

            return clazz.cast(o);
         }
      } else if (o instanceof List) {
         if (!clazz.isAssignableFrom(List.class)) {
            throw new DecodeException("Failed to decode");
         } else {
            if (clazz == Object.class) {
               o = new JsonArray((List)o);
            }

            return clazz.cast(o);
         }
      } else if (o instanceof String) {
         String str = (String)o;
         if (clazz.isEnum()) {
            o = Enum.valueOf(clazz, str);
         } else if (clazz == byte[].class) {
            o = JsonUtil.BASE64_DECODER.decode(str);
         } else if (clazz == Buffer.class) {
            o = Buffer.buffer(JsonUtil.BASE64_DECODER.decode(str));
         } else if (clazz == Instant.class) {
            o = Instant.from(DateTimeFormatter.ISO_INSTANT.parse(str));
         } else if (!clazz.isAssignableFrom(String.class)) {
            throw new DecodeException("Failed to decode");
         }

         return clazz.cast(o);
      } else if (o instanceof Boolean) {
         if (!clazz.isAssignableFrom(Boolean.class)) {
            throw new DecodeException("Failed to decode");
         } else {
            return clazz.cast(o);
         }
      } else if (o == null) {
         return null;
      } else {
         Number number = (Number)o;
         if (clazz == Integer.class) {
            o = number.intValue();
         } else if (clazz == Long.class) {
            o = number.longValue();
         } else if (clazz == Float.class) {
            o = number.floatValue();
         } else if (clazz == Double.class) {
            o = number.doubleValue();
         } else if (clazz == Byte.class) {
            o = number.byteValue();
         } else if (clazz == Short.class) {
            o = number.shortValue();
         } else if (clazz != Object.class && !clazz.isAssignableFrom(Number.class)) {
            throw new DecodeException("Failed to decode");
         }

         return clazz.cast(o);
      }
   }

   public static Object decodeValue(String str, TypeReference type) throws DecodeException {
      return JacksonFactory.CODEC.fromString(str, type);
   }

   public static Object decodeValue(Buffer buf, TypeReference type) throws DecodeException {
      return JacksonFactory.CODEC.fromBuffer(buf, type);
   }

   static {
      boolean releaseToPoolValue = false;
      TSFBuilder<?, ?> builder = JsonFactory.builder();

      try {
         Method[] methods = builder.getClass().getMethods();

         for(Method method : methods) {
            if (method.getName().equals("recyclerPool")) {
               method.invoke(builder, JacksonCodec.JacksonPoolHolder.pool);
               releaseToPoolValue = true;
               break;
            }
         }
      } catch (Throwable var7) {
      }

      factory = builder.build();
      releaseToPool = releaseToPoolValue;
      factory.configure(Feature.ALLOW_COMMENTS, true);
   }

   private static final class JacksonPoolHolder {
      private static final Object pool = HybridJacksonPool.getInstance();
   }
}
