package io.vertx.core.json;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.spi.JsonFactory;
import io.vertx.core.spi.json.JsonCodec;

public class Json {
   public static final JsonCodec CODEC = load().codec();

   public static JsonFactory load() {
      return JsonFactory.load();
   }

   public static String encode(Object obj) throws EncodeException {
      return CODEC.toString(obj);
   }

   public static Buffer encodeToBuffer(Object obj) throws EncodeException {
      return CODEC.toBuffer(obj);
   }

   public static String encodePrettily(Object obj) throws EncodeException {
      return CODEC.toString(obj, true);
   }

   public static Object decodeValue(String str, Class clazz) throws DecodeException {
      return CODEC.fromString(str, clazz);
   }

   public static Object decodeValue(String str) throws DecodeException {
      return decodeValue(str, Object.class);
   }

   public static Object decodeValue(Buffer buf) throws DecodeException {
      return decodeValue(buf, Object.class);
   }

   public static Object decodeValue(Buffer buf, Class clazz) throws DecodeException {
      return CODEC.fromBuffer(buf, clazz);
   }
}
