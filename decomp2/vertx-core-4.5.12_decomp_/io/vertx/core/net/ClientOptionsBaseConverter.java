package io.vertx.core.net;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.json.impl.JsonUtil;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Map;

public class ClientOptionsBaseConverter {
   private static final Base64.Decoder BASE64_DECODER;
   private static final Base64.Encoder BASE64_ENCODER;

   static void fromJson(Iterable json, ClientOptionsBase obj) {
      for(Map.Entry member : json) {
         switch ((String)member.getKey()) {
            case "connectTimeout":
               if (member.getValue() instanceof Number) {
                  obj.setConnectTimeout(((Number)member.getValue()).intValue());
               }
               break;
            case "localAddress":
               if (member.getValue() instanceof String) {
                  obj.setLocalAddress((String)member.getValue());
               }
               break;
            case "metricsName":
               if (member.getValue() instanceof String) {
                  obj.setMetricsName((String)member.getValue());
               }
               break;
            case "nonProxyHosts":
               if (member.getValue() instanceof JsonArray) {
                  ArrayList<String> list = new ArrayList();
                  ((Iterable)member.getValue()).forEach((item) -> {
                     if (item instanceof String) {
                        list.add((String)item);
                     }

                  });
                  obj.setNonProxyHosts(list);
               }
               break;
            case "proxyOptions":
               if (member.getValue() instanceof JsonObject) {
                  obj.setProxyOptions(new ProxyOptions((JsonObject)member.getValue()));
               }
               break;
            case "trustAll":
               if (member.getValue() instanceof Boolean) {
                  obj.setTrustAll((Boolean)member.getValue());
               }
         }
      }

   }

   static void toJson(ClientOptionsBase obj, JsonObject json) {
      toJson(obj, json.getMap());
   }

   static void toJson(ClientOptionsBase obj, Map json) {
      json.put("connectTimeout", obj.getConnectTimeout());
      if (obj.getLocalAddress() != null) {
         json.put("localAddress", obj.getLocalAddress());
      }

      if (obj.getMetricsName() != null) {
         json.put("metricsName", obj.getMetricsName());
      }

      if (obj.getNonProxyHosts() != null) {
         JsonArray array = new JsonArray();
         obj.getNonProxyHosts().forEach((item) -> array.add(item));
         json.put("nonProxyHosts", array);
      }

      if (obj.getProxyOptions() != null) {
         json.put("proxyOptions", obj.getProxyOptions().toJson());
      }

      json.put("trustAll", obj.isTrustAll());
   }

   static {
      BASE64_DECODER = JsonUtil.BASE64_DECODER;
      BASE64_ENCODER = JsonUtil.BASE64_ENCODER;
   }
}
