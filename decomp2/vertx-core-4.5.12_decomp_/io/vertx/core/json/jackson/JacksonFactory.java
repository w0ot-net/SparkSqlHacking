package io.vertx.core.json.jackson;

import io.vertx.core.spi.JsonFactory;
import io.vertx.core.spi.json.JsonCodec;

public class JacksonFactory implements JsonFactory {
   public static final JacksonFactory INSTANCE = new JacksonFactory();
   public static final JacksonCodec CODEC;

   public JsonCodec codec() {
      return CODEC;
   }

   static {
      JacksonCodec codec;
      try {
         codec = new DatabindCodec();
      } catch (Throwable var2) {
         codec = new JacksonCodec();
      }

      CODEC = codec;
   }
}
