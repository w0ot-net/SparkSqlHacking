package io.vertx.core.tracing;

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.impl.JsonUtil;
import java.util.Base64;
import java.util.Map;

public class TracingOptionsConverter {
   private static final Base64.Decoder BASE64_DECODER;
   private static final Base64.Encoder BASE64_ENCODER;

   static void fromJson(Iterable json, TracingOptions obj) {
      for(Map.Entry member : json) {
         ((String)member.getKey()).getClass();
      }

   }

   static void toJson(TracingOptions obj, JsonObject json) {
      toJson(obj, json.getMap());
   }

   static void toJson(TracingOptions obj, Map json) {
   }

   static {
      BASE64_DECODER = JsonUtil.BASE64_DECODER;
      BASE64_ENCODER = JsonUtil.BASE64_ENCODER;
   }
}
