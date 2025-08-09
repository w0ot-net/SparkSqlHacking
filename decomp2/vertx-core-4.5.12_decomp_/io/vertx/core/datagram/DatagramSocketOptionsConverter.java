package io.vertx.core.datagram;

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.impl.JsonUtil;
import java.util.Base64;
import java.util.Map;

public class DatagramSocketOptionsConverter {
   private static final Base64.Decoder BASE64_DECODER;
   private static final Base64.Encoder BASE64_ENCODER;

   static void fromJson(Iterable json, DatagramSocketOptions obj) {
      for(Map.Entry member : json) {
         switch ((String)member.getKey()) {
            case "broadcast":
               if (member.getValue() instanceof Boolean) {
                  obj.setBroadcast((Boolean)member.getValue());
               }
               break;
            case "ipV6":
               if (member.getValue() instanceof Boolean) {
                  obj.setIpV6((Boolean)member.getValue());
               }
               break;
            case "loopbackModeDisabled":
               if (member.getValue() instanceof Boolean) {
                  obj.setLoopbackModeDisabled((Boolean)member.getValue());
               }
               break;
            case "multicastNetworkInterface":
               if (member.getValue() instanceof String) {
                  obj.setMulticastNetworkInterface((String)member.getValue());
               }
               break;
            case "multicastTimeToLive":
               if (member.getValue() instanceof Number) {
                  obj.setMulticastTimeToLive(((Number)member.getValue()).intValue());
               }
         }
      }

   }

   static void toJson(DatagramSocketOptions obj, JsonObject json) {
      toJson(obj, json.getMap());
   }

   static void toJson(DatagramSocketOptions obj, Map json) {
      json.put("broadcast", obj.isBroadcast());
      json.put("ipV6", obj.isIpV6());
      json.put("loopbackModeDisabled", obj.isLoopbackModeDisabled());
      if (obj.getMulticastNetworkInterface() != null) {
         json.put("multicastNetworkInterface", obj.getMulticastNetworkInterface());
      }

      json.put("multicastTimeToLive", obj.getMulticastTimeToLive());
   }

   static {
      BASE64_DECODER = JsonUtil.BASE64_DECODER;
      BASE64_ENCODER = JsonUtil.BASE64_ENCODER;
   }
}
