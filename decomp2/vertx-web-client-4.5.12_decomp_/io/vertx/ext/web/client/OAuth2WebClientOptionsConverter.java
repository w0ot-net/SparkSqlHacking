package io.vertx.ext.web.client;

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.impl.JsonUtil;
import java.util.Base64;
import java.util.Map;

public class OAuth2WebClientOptionsConverter {
   private static final Base64.Decoder BASE64_DECODER;
   private static final Base64.Encoder BASE64_ENCODER;

   static void fromJson(Iterable json, OAuth2WebClientOptions obj) {
      for(Map.Entry member : json) {
         switch ((String)member.getKey()) {
            case "leeway":
               if (member.getValue() instanceof Number) {
                  obj.setLeeway(((Number)member.getValue()).intValue());
               }
               break;
            case "renewTokenOnForbidden":
               if (member.getValue() instanceof Boolean) {
                  obj.setRenewTokenOnForbidden((Boolean)member.getValue());
               }
         }
      }

   }

   static void toJson(OAuth2WebClientOptions obj, JsonObject json) {
      toJson(obj, json.getMap());
   }

   static void toJson(OAuth2WebClientOptions obj, Map json) {
      json.put("leeway", obj.getLeeway());
      json.put("renewTokenOnForbidden", obj.isRenewTokenOnForbidden());
   }

   static {
      BASE64_DECODER = JsonUtil.BASE64_DECODER;
      BASE64_ENCODER = JsonUtil.BASE64_ENCODER;
   }
}
