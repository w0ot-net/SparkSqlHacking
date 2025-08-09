package io.vertx.core.http;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.json.impl.JsonUtil;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Map;

public class WebSocketConnectOptionsConverter {
   private static final Base64.Decoder BASE64_DECODER;
   private static final Base64.Encoder BASE64_ENCODER;

   static void fromJson(Iterable json, WebSocketConnectOptions obj) {
      for(Map.Entry member : json) {
         switch ((String)member.getKey()) {
            case "allowOriginHeader":
               if (member.getValue() instanceof Boolean) {
                  obj.setAllowOriginHeader((Boolean)member.getValue());
               }
               break;
            case "registerWriteHandlers":
               if (member.getValue() instanceof Boolean) {
                  obj.setRegisterWriteHandlers((Boolean)member.getValue());
               }
               break;
            case "subProtocols":
               if (member.getValue() instanceof JsonArray) {
                  ArrayList<String> list = new ArrayList();
                  ((Iterable)member.getValue()).forEach((item) -> {
                     if (item instanceof String) {
                        list.add((String)item);
                     }

                  });
                  obj.setSubProtocols(list);
               }
               break;
            case "version":
               if (member.getValue() instanceof String) {
                  obj.setVersion(WebsocketVersion.valueOf((String)member.getValue()));
               }
         }
      }

   }

   static void toJson(WebSocketConnectOptions obj, JsonObject json) {
      toJson(obj, json.getMap());
   }

   static void toJson(WebSocketConnectOptions obj, Map json) {
      json.put("allowOriginHeader", obj.getAllowOriginHeader());
      json.put("registerWriteHandlers", obj.isRegisterWriteHandlers());
      if (obj.getSubProtocols() != null) {
         JsonArray array = new JsonArray();
         obj.getSubProtocols().forEach((item) -> array.add(item));
         json.put("subProtocols", array);
      }

      if (obj.getVersion() != null) {
         json.put("version", obj.getVersion().name());
      }

   }

   static {
      BASE64_DECODER = JsonUtil.BASE64_DECODER;
      BASE64_ENCODER = JsonUtil.BASE64_ENCODER;
   }
}
