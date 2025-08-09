package io.vertx.core.net;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.json.impl.JsonUtil;
import java.util.Base64;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class SSLOptionsConverter {
   private static final Base64.Decoder BASE64_DECODER;
   private static final Base64.Encoder BASE64_ENCODER;

   static void fromJson(Iterable json, SSLOptions obj) {
      for(Map.Entry member : json) {
         switch ((String)member.getKey()) {
            case "crlPaths":
               if (member.getValue() instanceof JsonArray) {
                  ((Iterable)member.getValue()).forEach((item) -> {
                     if (item instanceof String) {
                        obj.addCrlPath((String)item);
                     }

                  });
               }
               break;
            case "crlValues":
               if (member.getValue() instanceof JsonArray) {
                  ((Iterable)member.getValue()).forEach((item) -> {
                     if (item instanceof String) {
                        obj.addCrlValue(Buffer.buffer(BASE64_DECODER.decode((String)item)));
                     }

                  });
               }
               break;
            case "enabledCipherSuites":
               if (member.getValue() instanceof JsonArray) {
                  ((Iterable)member.getValue()).forEach((item) -> {
                     if (item instanceof String) {
                        obj.addEnabledCipherSuite((String)item);
                     }

                  });
               }
               break;
            case "enabledSecureTransportProtocols":
               if (member.getValue() instanceof JsonArray) {
                  LinkedHashSet<String> list = new LinkedHashSet();
                  ((Iterable)member.getValue()).forEach((item) -> {
                     if (item instanceof String) {
                        list.add((String)item);
                     }

                  });
                  obj.setEnabledSecureTransportProtocols(list);
               }
               break;
            case "sslHandshakeTimeout":
               if (member.getValue() instanceof Number) {
                  obj.setSslHandshakeTimeout(((Number)member.getValue()).longValue());
               }
               break;
            case "sslHandshakeTimeoutUnit":
               if (member.getValue() instanceof String) {
                  obj.setSslHandshakeTimeoutUnit(TimeUnit.valueOf((String)member.getValue()));
               }
               break;
            case "useAlpn":
               if (member.getValue() instanceof Boolean) {
                  obj.setUseAlpn((Boolean)member.getValue());
               }
         }
      }

   }

   static void toJson(SSLOptions obj, JsonObject json) {
      toJson(obj, json.getMap());
   }

   static void toJson(SSLOptions obj, Map json) {
      if (obj.getCrlPaths() != null) {
         JsonArray array = new JsonArray();
         obj.getCrlPaths().forEach((item) -> array.add(item));
         json.put("crlPaths", array);
      }

      if (obj.getCrlValues() != null) {
         JsonArray array = new JsonArray();
         obj.getCrlValues().forEach((item) -> array.add(BASE64_ENCODER.encodeToString(item.getBytes())));
         json.put("crlValues", array);
      }

      if (obj.getEnabledCipherSuites() != null) {
         JsonArray array = new JsonArray();
         obj.getEnabledCipherSuites().forEach((item) -> array.add(item));
         json.put("enabledCipherSuites", array);
      }

      if (obj.getEnabledSecureTransportProtocols() != null) {
         JsonArray array = new JsonArray();
         obj.getEnabledSecureTransportProtocols().forEach((item) -> array.add(item));
         json.put("enabledSecureTransportProtocols", array);
      }

      json.put("sslHandshakeTimeout", obj.getSslHandshakeTimeout());
      if (obj.getSslHandshakeTimeoutUnit() != null) {
         json.put("sslHandshakeTimeoutUnit", obj.getSslHandshakeTimeoutUnit().name());
      }

      json.put("useAlpn", obj.isUseAlpn());
   }

   static {
      BASE64_DECODER = JsonUtil.BASE64_DECODER;
      BASE64_ENCODER = JsonUtil.BASE64_ENCODER;
   }
}
