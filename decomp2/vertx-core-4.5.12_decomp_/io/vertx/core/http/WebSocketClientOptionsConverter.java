package io.vertx.core.http;

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.impl.JsonUtil;
import java.util.Base64;
import java.util.Map;

public class WebSocketClientOptionsConverter {
   private static final Base64.Decoder BASE64_DECODER;
   private static final Base64.Encoder BASE64_ENCODER;

   static void fromJson(Iterable json, WebSocketClientOptions obj) {
      for(Map.Entry member : json) {
         switch ((String)member.getKey()) {
            case "closingTimeout":
               if (member.getValue() instanceof Number) {
                  obj.setClosingTimeout(((Number)member.getValue()).intValue());
               }
               break;
            case "compressionAllowClientNoContext":
               if (member.getValue() instanceof Boolean) {
                  obj.setCompressionAllowClientNoContext((Boolean)member.getValue());
               }
               break;
            case "compressionLevel":
               if (member.getValue() instanceof Number) {
                  obj.setCompressionLevel(((Number)member.getValue()).intValue());
               }
               break;
            case "compressionRequestServerNoContext":
               if (member.getValue() instanceof Boolean) {
                  obj.setCompressionRequestServerNoContext((Boolean)member.getValue());
               }
               break;
            case "defaultHost":
               if (member.getValue() instanceof String) {
                  obj.setDefaultHost((String)member.getValue());
               }
               break;
            case "defaultPort":
               if (member.getValue() instanceof Number) {
                  obj.setDefaultPort(((Number)member.getValue()).intValue());
               }
               break;
            case "maxConnections":
               if (member.getValue() instanceof Number) {
                  obj.setMaxConnections(((Number)member.getValue()).intValue());
               }
               break;
            case "maxFrameSize":
               if (member.getValue() instanceof Number) {
                  obj.setMaxFrameSize(((Number)member.getValue()).intValue());
               }
               break;
            case "maxMessageSize":
               if (member.getValue() instanceof Number) {
                  obj.setMaxMessageSize(((Number)member.getValue()).intValue());
               }
               break;
            case "name":
               if (member.getValue() instanceof String) {
                  obj.setName((String)member.getValue());
               }
               break;
            case "sendUnmaskedFrames":
               if (member.getValue() instanceof Boolean) {
                  obj.setSendUnmaskedFrames((Boolean)member.getValue());
               }
               break;
            case "shared":
               if (member.getValue() instanceof Boolean) {
                  obj.setShared((Boolean)member.getValue());
               }
               break;
            case "tryUsePerFrameCompression":
               if (member.getValue() instanceof Boolean) {
                  obj.setTryUsePerFrameCompression((Boolean)member.getValue());
               }
               break;
            case "tryUsePerMessageCompression":
               if (member.getValue() instanceof Boolean) {
                  obj.setTryUsePerMessageCompression((Boolean)member.getValue());
               }
               break;
            case "verifyHost":
               if (member.getValue() instanceof Boolean) {
                  obj.setVerifyHost((Boolean)member.getValue());
               }
         }
      }

   }

   static void toJson(WebSocketClientOptions obj, JsonObject json) {
      toJson(obj, json.getMap());
   }

   static void toJson(WebSocketClientOptions obj, Map json) {
      json.put("closingTimeout", obj.getClosingTimeout());
      json.put("compressionAllowClientNoContext", obj.getCompressionAllowClientNoContext());
      json.put("compressionLevel", obj.getCompressionLevel());
      json.put("compressionRequestServerNoContext", obj.getCompressionRequestServerNoContext());
      if (obj.getDefaultHost() != null) {
         json.put("defaultHost", obj.getDefaultHost());
      }

      json.put("defaultPort", obj.getDefaultPort());
      json.put("maxConnections", obj.getMaxConnections());
      json.put("maxFrameSize", obj.getMaxFrameSize());
      json.put("maxMessageSize", obj.getMaxMessageSize());
      if (obj.getName() != null) {
         json.put("name", obj.getName());
      }

      json.put("sendUnmaskedFrames", obj.isSendUnmaskedFrames());
      json.put("shared", obj.isShared());
      json.put("tryUsePerFrameCompression", obj.getTryUsePerFrameCompression());
      json.put("tryUsePerMessageCompression", obj.getTryUsePerMessageCompression());
      json.put("verifyHost", obj.isVerifyHost());
   }

   static {
      BASE64_DECODER = JsonUtil.BASE64_DECODER;
      BASE64_ENCODER = JsonUtil.BASE64_ENCODER;
   }
}
