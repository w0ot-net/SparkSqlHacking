package io.vertx.core.net;

import io.netty.handler.logging.ByteBufFormat;
import io.vertx.core.json.JsonObject;
import io.vertx.core.json.impl.JsonUtil;
import java.util.Base64;
import java.util.Map;

public class NetworkOptionsConverter {
   private static final Base64.Decoder BASE64_DECODER;
   private static final Base64.Encoder BASE64_ENCODER;

   static void fromJson(Iterable json, NetworkOptions obj) {
      for(Map.Entry member : json) {
         switch ((String)member.getKey()) {
            case "activityLogDataFormat":
               if (member.getValue() instanceof String) {
                  obj.setActivityLogDataFormat(ByteBufFormat.valueOf((String)member.getValue()));
               }
               break;
            case "logActivity":
               if (member.getValue() instanceof Boolean) {
                  obj.setLogActivity((Boolean)member.getValue());
               }
               break;
            case "receiveBufferSize":
               if (member.getValue() instanceof Number) {
                  obj.setReceiveBufferSize(((Number)member.getValue()).intValue());
               }
               break;
            case "reuseAddress":
               if (member.getValue() instanceof Boolean) {
                  obj.setReuseAddress((Boolean)member.getValue());
               }
               break;
            case "reusePort":
               if (member.getValue() instanceof Boolean) {
                  obj.setReusePort((Boolean)member.getValue());
               }
               break;
            case "sendBufferSize":
               if (member.getValue() instanceof Number) {
                  obj.setSendBufferSize(((Number)member.getValue()).intValue());
               }
               break;
            case "trafficClass":
               if (member.getValue() instanceof Number) {
                  obj.setTrafficClass(((Number)member.getValue()).intValue());
               }
         }
      }

   }

   static void toJson(NetworkOptions obj, JsonObject json) {
      toJson(obj, json.getMap());
   }

   static void toJson(NetworkOptions obj, Map json) {
      if (obj.getActivityLogDataFormat() != null) {
         json.put("activityLogDataFormat", obj.getActivityLogDataFormat().name());
      }

      json.put("logActivity", obj.getLogActivity());
      json.put("receiveBufferSize", obj.getReceiveBufferSize());
      json.put("reuseAddress", obj.isReuseAddress());
      json.put("reusePort", obj.isReusePort());
      json.put("sendBufferSize", obj.getSendBufferSize());
      json.put("trafficClass", obj.getTrafficClass());
   }

   static {
      BASE64_DECODER = JsonUtil.BASE64_DECODER;
      BASE64_ENCODER = JsonUtil.BASE64_ENCODER;
   }
}
