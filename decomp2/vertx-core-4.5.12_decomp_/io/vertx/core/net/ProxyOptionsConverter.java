package io.vertx.core.net;

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.impl.JsonUtil;
import java.util.Base64;
import java.util.Map;

public class ProxyOptionsConverter {
   private static final Base64.Decoder BASE64_DECODER;
   private static final Base64.Encoder BASE64_ENCODER;

   static void fromJson(Iterable json, ProxyOptions obj) {
      for(Map.Entry member : json) {
         switch ((String)member.getKey()) {
            case "host":
               if (member.getValue() instanceof String) {
                  obj.setHost((String)member.getValue());
               }
               break;
            case "password":
               if (member.getValue() instanceof String) {
                  obj.setPassword((String)member.getValue());
               }
               break;
            case "port":
               if (member.getValue() instanceof Number) {
                  obj.setPort(((Number)member.getValue()).intValue());
               }
               break;
            case "type":
               if (member.getValue() instanceof String) {
                  obj.setType(ProxyType.valueOf((String)member.getValue()));
               }
               break;
            case "username":
               if (member.getValue() instanceof String) {
                  obj.setUsername((String)member.getValue());
               }
         }
      }

   }

   static void toJson(ProxyOptions obj, JsonObject json) {
      toJson(obj, json.getMap());
   }

   static void toJson(ProxyOptions obj, Map json) {
      if (obj.getHost() != null) {
         json.put("host", obj.getHost());
      }

      if (obj.getPassword() != null) {
         json.put("password", obj.getPassword());
      }

      json.put("port", obj.getPort());
      if (obj.getType() != null) {
         json.put("type", obj.getType().name());
      }

      if (obj.getUsername() != null) {
         json.put("username", obj.getUsername());
      }

   }

   static {
      BASE64_DECODER = JsonUtil.BASE64_DECODER;
      BASE64_ENCODER = JsonUtil.BASE64_ENCODER;
   }
}
