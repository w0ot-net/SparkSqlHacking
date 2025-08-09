package io.vertx.core.http;

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.impl.JsonUtil;
import java.util.Base64;
import java.util.Map;

public class Http2SettingsConverter {
   private static final Base64.Decoder BASE64_DECODER;
   private static final Base64.Encoder BASE64_ENCODER;

   static void fromJson(Iterable json, Http2Settings obj) {
      for(Map.Entry member : json) {
         switch ((String)member.getKey()) {
            case "headerTableSize":
               if (member.getValue() instanceof Number) {
                  obj.setHeaderTableSize(((Number)member.getValue()).longValue());
               }
               break;
            case "initialWindowSize":
               if (member.getValue() instanceof Number) {
                  obj.setInitialWindowSize(((Number)member.getValue()).intValue());
               }
               break;
            case "maxConcurrentStreams":
               if (member.getValue() instanceof Number) {
                  obj.setMaxConcurrentStreams(((Number)member.getValue()).longValue());
               }
               break;
            case "maxFrameSize":
               if (member.getValue() instanceof Number) {
                  obj.setMaxFrameSize(((Number)member.getValue()).intValue());
               }
               break;
            case "maxHeaderListSize":
               if (member.getValue() instanceof Number) {
                  obj.setMaxHeaderListSize(((Number)member.getValue()).longValue());
               }
               break;
            case "pushEnabled":
               if (member.getValue() instanceof Boolean) {
                  obj.setPushEnabled((Boolean)member.getValue());
               }
         }
      }

   }

   static void toJson(Http2Settings obj, JsonObject json) {
      toJson(obj, json.getMap());
   }

   static void toJson(Http2Settings obj, Map json) {
      json.put("headerTableSize", obj.getHeaderTableSize());
      json.put("initialWindowSize", obj.getInitialWindowSize());
      json.put("maxConcurrentStreams", obj.getMaxConcurrentStreams());
      json.put("maxFrameSize", obj.getMaxFrameSize());
      json.put("maxHeaderListSize", obj.getMaxHeaderListSize());
      json.put("pushEnabled", obj.isPushEnabled());
   }

   static {
      BASE64_DECODER = JsonUtil.BASE64_DECODER;
      BASE64_ENCODER = JsonUtil.BASE64_ENCODER;
   }
}
