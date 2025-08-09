package io.vertx.core.http;

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.impl.JsonUtil;
import java.util.Base64;
import java.util.Map;

public class PoolOptionsConverter {
   private static final Base64.Decoder BASE64_DECODER;
   private static final Base64.Encoder BASE64_ENCODER;

   static void fromJson(Iterable json, PoolOptions obj) {
      for(Map.Entry member : json) {
         switch ((String)member.getKey()) {
            case "cleanerPeriod":
               if (member.getValue() instanceof Number) {
                  obj.setCleanerPeriod(((Number)member.getValue()).intValue());
               }
               break;
            case "eventLoopSize":
               if (member.getValue() instanceof Number) {
                  obj.setEventLoopSize(((Number)member.getValue()).intValue());
               }
               break;
            case "http1MaxSize":
               if (member.getValue() instanceof Number) {
                  obj.setHttp1MaxSize(((Number)member.getValue()).intValue());
               }
               break;
            case "http2MaxSize":
               if (member.getValue() instanceof Number) {
                  obj.setHttp2MaxSize(((Number)member.getValue()).intValue());
               }
               break;
            case "maxWaitQueueSize":
               if (member.getValue() instanceof Number) {
                  obj.setMaxWaitQueueSize(((Number)member.getValue()).intValue());
               }
         }
      }

   }

   static void toJson(PoolOptions obj, JsonObject json) {
      toJson(obj, json.getMap());
   }

   static void toJson(PoolOptions obj, Map json) {
      json.put("cleanerPeriod", obj.getCleanerPeriod());
      json.put("eventLoopSize", obj.getEventLoopSize());
      json.put("http1MaxSize", obj.getHttp1MaxSize());
      json.put("http2MaxSize", obj.getHttp2MaxSize());
      json.put("maxWaitQueueSize", obj.getMaxWaitQueueSize());
   }

   static {
      BASE64_DECODER = JsonUtil.BASE64_DECODER;
      BASE64_ENCODER = JsonUtil.BASE64_ENCODER;
   }
}
