package io.vertx.core.net;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.json.impl.JsonUtil;
import java.util.Base64;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TCPSSLOptionsConverter {
   private static final Base64.Decoder BASE64_DECODER;
   private static final Base64.Encoder BASE64_ENCODER;

   static void fromJson(Iterable json, TCPSSLOptions obj) {
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
            case "idleTimeout":
               if (member.getValue() instanceof Number) {
                  obj.setIdleTimeout(((Number)member.getValue()).intValue());
               }
               break;
            case "idleTimeoutUnit":
               if (member.getValue() instanceof String) {
                  obj.setIdleTimeoutUnit(TimeUnit.valueOf((String)member.getValue()));
               }
               break;
            case "jdkSslEngineOptions":
               if (member.getValue() instanceof JsonObject) {
                  obj.setJdkSslEngineOptions(new JdkSSLEngineOptions((JsonObject)member.getValue()));
               }
               break;
            case "keyStoreOptions":
               if (member.getValue() instanceof JsonObject) {
                  obj.setKeyStoreOptions(new JksOptions((JsonObject)member.getValue()));
               }
               break;
            case "openSslEngineOptions":
               if (member.getValue() instanceof JsonObject) {
                  obj.setOpenSslEngineOptions(new OpenSSLEngineOptions((JsonObject)member.getValue()));
               }
               break;
            case "pemKeyCertOptions":
               if (member.getValue() instanceof JsonObject) {
                  obj.setPemKeyCertOptions(new PemKeyCertOptions((JsonObject)member.getValue()));
               }
               break;
            case "pemTrustOptions":
               if (member.getValue() instanceof JsonObject) {
                  obj.setPemTrustOptions(new PemTrustOptions((JsonObject)member.getValue()));
               }
               break;
            case "pfxKeyCertOptions":
               if (member.getValue() instanceof JsonObject) {
                  obj.setPfxKeyCertOptions(new PfxOptions((JsonObject)member.getValue()));
               }
               break;
            case "pfxTrustOptions":
               if (member.getValue() instanceof JsonObject) {
                  obj.setPfxTrustOptions(new PfxOptions((JsonObject)member.getValue()));
               }
               break;
            case "readIdleTimeout":
               if (member.getValue() instanceof Number) {
                  obj.setReadIdleTimeout(((Number)member.getValue()).intValue());
               }
               break;
            case "soLinger":
               if (member.getValue() instanceof Number) {
                  obj.setSoLinger(((Number)member.getValue()).intValue());
               }
               break;
            case "ssl":
               if (member.getValue() instanceof Boolean) {
                  obj.setSsl((Boolean)member.getValue());
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
            case "tcpCork":
               if (member.getValue() instanceof Boolean) {
                  obj.setTcpCork((Boolean)member.getValue());
               }
               break;
            case "tcpFastOpen":
               if (member.getValue() instanceof Boolean) {
                  obj.setTcpFastOpen((Boolean)member.getValue());
               }
               break;
            case "tcpKeepAlive":
               if (member.getValue() instanceof Boolean) {
                  obj.setTcpKeepAlive((Boolean)member.getValue());
               }
               break;
            case "tcpKeepAliveCount":
               if (member.getValue() instanceof Number) {
                  obj.setTcpKeepAliveCount(((Number)member.getValue()).intValue());
               }
               break;
            case "tcpKeepAliveIdleSeconds":
               if (member.getValue() instanceof Number) {
                  obj.setTcpKeepAliveIdleSeconds(((Number)member.getValue()).intValue());
               }
               break;
            case "tcpKeepAliveIntervalSeconds":
               if (member.getValue() instanceof Number) {
                  obj.setTcpKeepAliveIntervalSeconds(((Number)member.getValue()).intValue());
               }
               break;
            case "tcpNoDelay":
               if (member.getValue() instanceof Boolean) {
                  obj.setTcpNoDelay((Boolean)member.getValue());
               }
               break;
            case "tcpQuickAck":
               if (member.getValue() instanceof Boolean) {
                  obj.setTcpQuickAck((Boolean)member.getValue());
               }
               break;
            case "tcpUserTimeout":
               if (member.getValue() instanceof Number) {
                  obj.setTcpUserTimeout(((Number)member.getValue()).intValue());
               }
               break;
            case "trustStoreOptions":
               if (member.getValue() instanceof JsonObject) {
                  obj.setTrustStoreOptions(new JksOptions((JsonObject)member.getValue()));
               }
               break;
            case "useAlpn":
               if (member.getValue() instanceof Boolean) {
                  obj.setUseAlpn((Boolean)member.getValue());
               }
               break;
            case "writeIdleTimeout":
               if (member.getValue() instanceof Number) {
                  obj.setWriteIdleTimeout(((Number)member.getValue()).intValue());
               }
         }
      }

   }

   static void toJson(TCPSSLOptions obj, JsonObject json) {
      toJson(obj, json.getMap());
   }

   static void toJson(TCPSSLOptions obj, Map json) {
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

      json.put("idleTimeout", obj.getIdleTimeout());
      if (obj.getIdleTimeoutUnit() != null) {
         json.put("idleTimeoutUnit", obj.getIdleTimeoutUnit().name());
      }

      if (obj.getJdkSslEngineOptions() != null) {
         json.put("jdkSslEngineOptions", obj.getJdkSslEngineOptions().toJson());
      }

      if (obj.getKeyStoreOptions() != null) {
         json.put("keyStoreOptions", obj.getKeyStoreOptions().toJson());
      }

      if (obj.getOpenSslEngineOptions() != null) {
         json.put("openSslEngineOptions", obj.getOpenSslEngineOptions().toJson());
      }

      if (obj.getPemKeyCertOptions() != null) {
         json.put("pemKeyCertOptions", obj.getPemKeyCertOptions().toJson());
      }

      if (obj.getPemTrustOptions() != null) {
         json.put("pemTrustOptions", obj.getPemTrustOptions().toJson());
      }

      if (obj.getPfxKeyCertOptions() != null) {
         json.put("pfxKeyCertOptions", obj.getPfxKeyCertOptions().toJson());
      }

      if (obj.getPfxTrustOptions() != null) {
         json.put("pfxTrustOptions", obj.getPfxTrustOptions().toJson());
      }

      json.put("readIdleTimeout", obj.getReadIdleTimeout());
      json.put("soLinger", obj.getSoLinger());
      json.put("ssl", obj.isSsl());
      json.put("sslHandshakeTimeout", obj.getSslHandshakeTimeout());
      if (obj.getSslHandshakeTimeoutUnit() != null) {
         json.put("sslHandshakeTimeoutUnit", obj.getSslHandshakeTimeoutUnit().name());
      }

      json.put("tcpCork", obj.isTcpCork());
      json.put("tcpFastOpen", obj.isTcpFastOpen());
      json.put("tcpKeepAlive", obj.isTcpKeepAlive());
      json.put("tcpKeepAliveCount", obj.getTcpKeepAliveCount());
      json.put("tcpKeepAliveIdleSeconds", obj.getTcpKeepAliveIdleSeconds());
      json.put("tcpKeepAliveIntervalSeconds", obj.getTcpKeepAliveIntervalSeconds());
      json.put("tcpNoDelay", obj.isTcpNoDelay());
      json.put("tcpQuickAck", obj.isTcpQuickAck());
      json.put("tcpUserTimeout", obj.getTcpUserTimeout());
      if (obj.getTrustStoreOptions() != null) {
         json.put("trustStoreOptions", obj.getTrustStoreOptions().toJson());
      }

      json.put("useAlpn", obj.isUseAlpn());
      json.put("writeIdleTimeout", obj.getWriteIdleTimeout());
   }

   static {
      BASE64_DECODER = JsonUtil.BASE64_DECODER;
      BASE64_ENCODER = JsonUtil.BASE64_ENCODER;
   }
}
