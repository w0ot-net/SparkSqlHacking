package io.vertx.core.net;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.json.impl.JsonUtil;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Map;

public class NetClientOptionsConverter {
   private static final Base64.Decoder BASE64_DECODER;
   private static final Base64.Encoder BASE64_ENCODER;

   static void fromJson(Iterable json, NetClientOptions obj) {
      for(Map.Entry member : json) {
         switch ((String)member.getKey()) {
            case "applicationLayerProtocols":
               if (member.getValue() instanceof JsonArray) {
                  ArrayList<String> list = new ArrayList();
                  ((Iterable)member.getValue()).forEach((item) -> {
                     if (item instanceof String) {
                        list.add((String)item);
                     }

                  });
                  obj.setApplicationLayerProtocols(list);
               }
               break;
            case "hostnameVerificationAlgorithm":
               if (member.getValue() instanceof String) {
                  obj.setHostnameVerificationAlgorithm((String)member.getValue());
               }
               break;
            case "reconnectAttempts":
               if (member.getValue() instanceof Number) {
                  obj.setReconnectAttempts(((Number)member.getValue()).intValue());
               }
               break;
            case "reconnectInterval":
               if (member.getValue() instanceof Number) {
                  obj.setReconnectInterval(((Number)member.getValue()).longValue());
               }
               break;
            case "registerWriteHandler":
               if (member.getValue() instanceof Boolean) {
                  obj.setRegisterWriteHandler((Boolean)member.getValue());
               }
         }
      }

   }

   static void toJson(NetClientOptions obj, JsonObject json) {
      toJson(obj, json.getMap());
   }

   static void toJson(NetClientOptions obj, Map json) {
      if (obj.getApplicationLayerProtocols() != null) {
         JsonArray array = new JsonArray();
         obj.getApplicationLayerProtocols().forEach((item) -> array.add(item));
         json.put("applicationLayerProtocols", array);
      }

      if (obj.getHostnameVerificationAlgorithm() != null) {
         json.put("hostnameVerificationAlgorithm", obj.getHostnameVerificationAlgorithm());
      }

      json.put("reconnectAttempts", obj.getReconnectAttempts());
      json.put("reconnectInterval", obj.getReconnectInterval());
      json.put("registerWriteHandler", obj.isRegisterWriteHandler());
   }

   static {
      BASE64_DECODER = JsonUtil.BASE64_DECODER;
      BASE64_ENCODER = JsonUtil.BASE64_ENCODER;
   }
}
