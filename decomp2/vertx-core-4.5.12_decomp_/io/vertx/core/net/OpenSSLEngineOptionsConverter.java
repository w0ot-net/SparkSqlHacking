package io.vertx.core.net;

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.impl.JsonUtil;
import java.util.Base64;
import java.util.Map;

public class OpenSSLEngineOptionsConverter {
   private static final Base64.Decoder BASE64_DECODER;
   private static final Base64.Encoder BASE64_ENCODER;

   static void fromJson(Iterable json, OpenSSLEngineOptions obj) {
      for(Map.Entry member : json) {
         switch ((String)member.getKey()) {
            case "sessionCacheEnabled":
               if (member.getValue() instanceof Boolean) {
                  obj.setSessionCacheEnabled((Boolean)member.getValue());
               }
               break;
            case "useWorkerThread":
               if (member.getValue() instanceof Boolean) {
                  obj.setUseWorkerThread((Boolean)member.getValue());
               }
         }
      }

   }

   static void toJson(OpenSSLEngineOptions obj, JsonObject json) {
      toJson(obj, json.getMap());
   }

   static void toJson(OpenSSLEngineOptions obj, Map json) {
      json.put("sessionCacheEnabled", obj.isSessionCacheEnabled());
      json.put("useWorkerThread", obj.getUseWorkerThread());
   }

   static {
      BASE64_DECODER = JsonUtil.BASE64_DECODER;
      BASE64_ENCODER = JsonUtil.BASE64_ENCODER;
   }
}
