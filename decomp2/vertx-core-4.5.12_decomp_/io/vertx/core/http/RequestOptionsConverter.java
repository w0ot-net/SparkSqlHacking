package io.vertx.core.http;

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.impl.JsonUtil;
import io.vertx.core.net.ProxyOptions;
import java.util.Base64;
import java.util.Map;

public class RequestOptionsConverter {
   private static final Base64.Decoder BASE64_DECODER;
   private static final Base64.Encoder BASE64_ENCODER;

   static void fromJson(Iterable json, RequestOptions obj) {
      for(Map.Entry member : json) {
         switch ((String)member.getKey()) {
            case "absoluteURI":
               if (member.getValue() instanceof String) {
                  obj.setAbsoluteURI((String)member.getValue());
               }
               break;
            case "connectTimeout":
               if (member.getValue() instanceof Number) {
                  obj.setConnectTimeout(((Number)member.getValue()).longValue());
               }
               break;
            case "followRedirects":
               if (member.getValue() instanceof Boolean) {
                  obj.setFollowRedirects((Boolean)member.getValue());
               }
               break;
            case "host":
               if (member.getValue() instanceof String) {
                  obj.setHost((String)member.getValue());
               }
               break;
            case "idleTimeout":
               if (member.getValue() instanceof Number) {
                  obj.setIdleTimeout(((Number)member.getValue()).longValue());
               }
               break;
            case "port":
               if (member.getValue() instanceof Number) {
                  obj.setPort(((Number)member.getValue()).intValue());
               }
               break;
            case "proxyOptions":
               if (member.getValue() instanceof JsonObject) {
                  obj.setProxyOptions(new ProxyOptions((JsonObject)member.getValue()));
               }
               break;
            case "ssl":
               if (member.getValue() instanceof Boolean) {
                  obj.setSsl((Boolean)member.getValue());
               }
               break;
            case "timeout":
               if (member.getValue() instanceof Number) {
                  obj.setTimeout(((Number)member.getValue()).longValue());
               }
               break;
            case "traceOperation":
               if (member.getValue() instanceof String) {
                  obj.setTraceOperation((String)member.getValue());
               }
               break;
            case "uri":
               if (member.getValue() instanceof String) {
                  obj.setURI((String)member.getValue());
               }
         }
      }

   }

   static void toJson(RequestOptions obj, JsonObject json) {
      toJson(obj, json.getMap());
   }

   static void toJson(RequestOptions obj, Map json) {
      json.put("connectTimeout", obj.getConnectTimeout());
      if (obj.getFollowRedirects() != null) {
         json.put("followRedirects", obj.getFollowRedirects());
      }

      if (obj.getHost() != null) {
         json.put("host", obj.getHost());
      }

      json.put("idleTimeout", obj.getIdleTimeout());
      if (obj.getPort() != null) {
         json.put("port", obj.getPort());
      }

      if (obj.getProxyOptions() != null) {
         json.put("proxyOptions", obj.getProxyOptions().toJson());
      }

      if (obj.isSsl() != null) {
         json.put("ssl", obj.isSsl());
      }

      json.put("timeout", obj.getTimeout());
      if (obj.getTraceOperation() != null) {
         json.put("traceOperation", obj.getTraceOperation());
      }

      if (obj.getURI() != null) {
         json.put("uri", obj.getURI());
      }

   }

   static {
      BASE64_DECODER = JsonUtil.BASE64_DECODER;
      BASE64_ENCODER = JsonUtil.BASE64_ENCODER;
   }
}
