package io.vertx.core.metrics;

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.impl.JsonUtil;
import java.util.Base64;
import java.util.Map;

public class MetricsOptionsConverter {
   private static final Base64.Decoder BASE64_DECODER;
   private static final Base64.Encoder BASE64_ENCODER;

   static void fromJson(Iterable json, MetricsOptions obj) {
      for(Map.Entry member : json) {
         switch ((String)member.getKey()) {
            case "enabled":
               if (member.getValue() instanceof Boolean) {
                  obj.setEnabled((Boolean)member.getValue());
               }
         }
      }

   }

   static void toJson(MetricsOptions obj, JsonObject json) {
      toJson(obj, json.getMap());
   }

   static void toJson(MetricsOptions obj, Map json) {
      json.put("enabled", obj.isEnabled());
   }

   static {
      BASE64_DECODER = JsonUtil.BASE64_DECODER;
      BASE64_ENCODER = JsonUtil.BASE64_ENCODER;
   }
}
