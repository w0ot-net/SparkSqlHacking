package io.vertx.core.net;

import io.vertx.core.http.ClientAuth;
import io.vertx.core.json.JsonObject;
import io.vertx.core.json.impl.JsonUtil;
import java.util.Base64;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class NetServerOptionsConverter {
   private static final Base64.Decoder BASE64_DECODER;
   private static final Base64.Encoder BASE64_ENCODER;

   static void fromJson(Iterable json, NetServerOptions obj) {
      for(Map.Entry member : json) {
         switch ((String)member.getKey()) {
            case "acceptBacklog":
               if (member.getValue() instanceof Number) {
                  obj.setAcceptBacklog(((Number)member.getValue()).intValue());
               }
               break;
            case "clientAuth":
               if (member.getValue() instanceof String) {
                  obj.setClientAuth(ClientAuth.valueOf((String)member.getValue()));
               }
               break;
            case "host":
               if (member.getValue() instanceof String) {
                  obj.setHost((String)member.getValue());
               }
               break;
            case "port":
               if (member.getValue() instanceof Number) {
                  obj.setPort(((Number)member.getValue()).intValue());
               }
               break;
            case "proxyProtocolTimeout":
               if (member.getValue() instanceof Number) {
                  obj.setProxyProtocolTimeout(((Number)member.getValue()).longValue());
               }
               break;
            case "proxyProtocolTimeoutUnit":
               if (member.getValue() instanceof String) {
                  obj.setProxyProtocolTimeoutUnit(TimeUnit.valueOf((String)member.getValue()));
               }
               break;
            case "registerWriteHandler":
               if (member.getValue() instanceof Boolean) {
                  obj.setRegisterWriteHandler((Boolean)member.getValue());
               }
               break;
            case "sni":
               if (member.getValue() instanceof Boolean) {
                  obj.setSni((Boolean)member.getValue());
               }
               break;
            case "trafficShapingOptions":
               if (member.getValue() instanceof JsonObject) {
                  obj.setTrafficShapingOptions(new TrafficShapingOptions((JsonObject)member.getValue()));
               }
               break;
            case "useProxyProtocol":
               if (member.getValue() instanceof Boolean) {
                  obj.setUseProxyProtocol((Boolean)member.getValue());
               }
         }
      }

   }

   static void toJson(NetServerOptions obj, JsonObject json) {
      toJson(obj, json.getMap());
   }

   static void toJson(NetServerOptions obj, Map json) {
      json.put("acceptBacklog", obj.getAcceptBacklog());
      if (obj.getClientAuth() != null) {
         json.put("clientAuth", obj.getClientAuth().name());
      }

      if (obj.getHost() != null) {
         json.put("host", obj.getHost());
      }

      json.put("port", obj.getPort());
      json.put("proxyProtocolTimeout", obj.getProxyProtocolTimeout());
      if (obj.getProxyProtocolTimeoutUnit() != null) {
         json.put("proxyProtocolTimeoutUnit", obj.getProxyProtocolTimeoutUnit().name());
      }

      json.put("registerWriteHandler", obj.isRegisterWriteHandler());
      json.put("sni", obj.isSni());
      if (obj.getTrafficShapingOptions() != null) {
         json.put("trafficShapingOptions", obj.getTrafficShapingOptions().toJson());
      }

      json.put("useProxyProtocol", obj.isUseProxyProtocol());
   }

   static {
      BASE64_DECODER = JsonUtil.BASE64_DECODER;
      BASE64_ENCODER = JsonUtil.BASE64_ENCODER;
   }
}
