package io.vertx.core.json.impl;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.shareddata.Shareable;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public final class JsonUtil {
   public static final Base64.Encoder BASE64_ENCODER;
   public static final Base64.Decoder BASE64_DECODER;
   public static final Function DEFAULT_CLONER;

   public static Object wrapJsonValue(Object val) {
      if (val == null) {
         return null;
      } else {
         if (val instanceof Map) {
            val = new JsonObject((Map)val);
         } else if (val instanceof List) {
            val = new JsonArray((List)val);
         } else if (val instanceof Instant) {
            val = DateTimeFormatter.ISO_INSTANT.format((Instant)val);
         } else if (val instanceof byte[]) {
            val = BASE64_ENCODER.encodeToString((byte[])val);
         } else if (val instanceof Buffer) {
            val = BASE64_ENCODER.encodeToString(((Buffer)val).getBytes());
         } else if (val instanceof Enum) {
            val = ((Enum)val).name();
         }

         return val;
      }
   }

   public static Object deepCopy(Object val, Function copier) {
      if (val != null && !(val instanceof Number) && !(val instanceof Boolean) && !(val instanceof String) && !(val instanceof Character)) {
         if (val instanceof CharSequence) {
            val = val.toString();
         } else if (val instanceof Shareable) {
            val = ((Shareable)val).copy();
         } else if (val instanceof Map) {
            val = (new JsonObject((Map)val)).copy(copier);
         } else if (val instanceof List) {
            val = (new JsonArray((List)val)).copy(copier);
         } else if (!(val instanceof byte[]) && !(val instanceof Instant) && !(val instanceof Enum)) {
            val = copier.apply(val);
         }
      }

      return val;
   }

   public static Stream asStream(Iterator sourceIterator) {
      Iterable<T> iterable = () -> sourceIterator;
      return StreamSupport.stream(iterable.spliterator(), false);
   }

   static {
      if ("legacy".equalsIgnoreCase(System.getProperty("vertx.json.base64"))) {
         BASE64_ENCODER = Base64.getEncoder();
         BASE64_DECODER = Base64.getDecoder();
      } else {
         BASE64_ENCODER = Base64.getUrlEncoder().withoutPadding();
         BASE64_DECODER = Base64.getUrlDecoder();
      }

      DEFAULT_CLONER = (o) -> {
         throw new IllegalStateException("Illegal type in Json: " + o.getClass());
      };
   }
}
