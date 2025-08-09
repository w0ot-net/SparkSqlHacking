package io.vertx.core.dns;

import io.netty.handler.logging.ByteBufFormat;
import io.vertx.core.json.JsonObject;
import io.vertx.core.json.impl.JsonUtil;
import java.util.Base64;
import java.util.Map;

public class DnsClientOptionsConverter {
   private static final Base64.Decoder BASE64_DECODER;
   private static final Base64.Encoder BASE64_ENCODER;

   static void fromJson(Iterable json, DnsClientOptions obj) {
      for(Map.Entry member : json) {
         switch ((String)member.getKey()) {
            case "activityLogFormat":
               if (member.getValue() instanceof String) {
                  obj.setActivityLogFormat(ByteBufFormat.valueOf((String)member.getValue()));
               }
               break;
            case "host":
               if (member.getValue() instanceof String) {
                  obj.setHost((String)member.getValue());
               }
               break;
            case "logActivity":
               if (member.getValue() instanceof Boolean) {
                  obj.setLogActivity((Boolean)member.getValue());
               }
               break;
            case "port":
               if (member.getValue() instanceof Number) {
                  obj.setPort(((Number)member.getValue()).intValue());
               }
               break;
            case "queryTimeout":
               if (member.getValue() instanceof Number) {
                  obj.setQueryTimeout(((Number)member.getValue()).longValue());
               }
               break;
            case "recursionDesired":
               if (member.getValue() instanceof Boolean) {
                  obj.setRecursionDesired((Boolean)member.getValue());
               }
         }
      }

   }

   static void toJson(DnsClientOptions obj, JsonObject json) {
      toJson(obj, json.getMap());
   }

   static void toJson(DnsClientOptions obj, Map json) {
      if (obj.getActivityLogFormat() != null) {
         json.put("activityLogFormat", obj.getActivityLogFormat().name());
      }

      if (obj.getHost() != null) {
         json.put("host", obj.getHost());
      }

      json.put("logActivity", obj.getLogActivity());
      json.put("port", obj.getPort());
      json.put("queryTimeout", obj.getQueryTimeout());
      json.put("recursionDesired", obj.isRecursionDesired());
   }

   static {
      BASE64_DECODER = JsonUtil.BASE64_DECODER;
      BASE64_ENCODER = JsonUtil.BASE64_ENCODER;
   }
}
