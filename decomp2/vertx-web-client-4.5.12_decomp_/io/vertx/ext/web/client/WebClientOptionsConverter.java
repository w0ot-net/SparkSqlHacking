package io.vertx.ext.web.client;

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.impl.JsonUtil;
import io.vertx.uritemplate.ExpandOptions;
import java.util.Base64;
import java.util.Map;

public class WebClientOptionsConverter {
   private static final Base64.Decoder BASE64_DECODER;
   private static final Base64.Encoder BASE64_ENCODER;

   static void fromJson(Iterable json, WebClientOptions obj) {
      for(Map.Entry member : json) {
         switch ((String)member.getKey()) {
            case "followRedirects":
               if (member.getValue() instanceof Boolean) {
                  obj.setFollowRedirects((Boolean)member.getValue());
               }
               break;
            case "templateExpandOptions":
               if (member.getValue() instanceof JsonObject) {
                  obj.setTemplateExpandOptions(new ExpandOptions((JsonObject)member.getValue()));
               }
               break;
            case "userAgent":
               if (member.getValue() instanceof String) {
                  obj.setUserAgent((String)member.getValue());
               }
               break;
            case "userAgentEnabled":
               if (member.getValue() instanceof Boolean) {
                  obj.setUserAgentEnabled((Boolean)member.getValue());
               }
         }
      }

   }

   static void toJson(WebClientOptions obj, JsonObject json) {
      toJson(obj, json.getMap());
   }

   static void toJson(WebClientOptions obj, Map json) {
      json.put("followRedirects", obj.isFollowRedirects());
      if (obj.getTemplateExpandOptions() != null) {
         json.put("templateExpandOptions", obj.getTemplateExpandOptions().toJson());
      }

      if (obj.getUserAgent() != null) {
         json.put("userAgent", obj.getUserAgent());
      }

      json.put("userAgentEnabled", obj.isUserAgentEnabled());
   }

   static {
      BASE64_DECODER = JsonUtil.BASE64_DECODER;
      BASE64_ENCODER = JsonUtil.BASE64_ENCODER;
   }
}
