package io.vertx.core.net;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.json.impl.JsonUtil;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Map;

public class PemKeyCertOptionsConverter {
   private static final Base64.Decoder BASE64_DECODER;
   private static final Base64.Encoder BASE64_ENCODER;

   static void fromJson(Iterable json, PemKeyCertOptions obj) {
      for(Map.Entry member : json) {
         switch ((String)member.getKey()) {
            case "certPath":
               if (member.getValue() instanceof String) {
                  obj.setCertPath((String)member.getValue());
               }
               break;
            case "certPaths":
               if (member.getValue() instanceof JsonArray) {
                  ArrayList<String> list = new ArrayList();
                  ((Iterable)member.getValue()).forEach((item) -> {
                     if (item instanceof String) {
                        list.add((String)item);
                     }

                  });
                  obj.setCertPaths(list);
               }
               break;
            case "certValue":
               if (member.getValue() instanceof String) {
                  obj.setCertValue(Buffer.buffer(BASE64_DECODER.decode((String)member.getValue())));
               }
               break;
            case "certValues":
               if (member.getValue() instanceof JsonArray) {
                  ArrayList<Buffer> list = new ArrayList();
                  ((Iterable)member.getValue()).forEach((item) -> {
                     if (item instanceof String) {
                        list.add(Buffer.buffer(BASE64_DECODER.decode((String)item)));
                     }

                  });
                  obj.setCertValues(list);
               }
               break;
            case "keyPath":
               if (member.getValue() instanceof String) {
                  obj.setKeyPath((String)member.getValue());
               }
               break;
            case "keyPaths":
               if (member.getValue() instanceof JsonArray) {
                  ArrayList<String> list = new ArrayList();
                  ((Iterable)member.getValue()).forEach((item) -> {
                     if (item instanceof String) {
                        list.add((String)item);
                     }

                  });
                  obj.setKeyPaths(list);
               }
               break;
            case "keyValue":
               if (member.getValue() instanceof String) {
                  obj.setKeyValue(Buffer.buffer(BASE64_DECODER.decode((String)member.getValue())));
               }
               break;
            case "keyValues":
               if (member.getValue() instanceof JsonArray) {
                  ArrayList<Buffer> list = new ArrayList();
                  ((Iterable)member.getValue()).forEach((item) -> {
                     if (item instanceof String) {
                        list.add(Buffer.buffer(BASE64_DECODER.decode((String)item)));
                     }

                  });
                  obj.setKeyValues(list);
               }
         }
      }

   }

   static void toJson(PemKeyCertOptions obj, JsonObject json) {
      toJson(obj, json.getMap());
   }

   static void toJson(PemKeyCertOptions obj, Map json) {
      if (obj.getCertPaths() != null) {
         JsonArray array = new JsonArray();
         obj.getCertPaths().forEach((item) -> array.add(item));
         json.put("certPaths", array);
      }

      if (obj.getCertValues() != null) {
         JsonArray array = new JsonArray();
         obj.getCertValues().forEach((item) -> array.add(BASE64_ENCODER.encodeToString(item.getBytes())));
         json.put("certValues", array);
      }

      if (obj.getKeyPaths() != null) {
         JsonArray array = new JsonArray();
         obj.getKeyPaths().forEach((item) -> array.add(item));
         json.put("keyPaths", array);
      }

      if (obj.getKeyValues() != null) {
         JsonArray array = new JsonArray();
         obj.getKeyValues().forEach((item) -> array.add(BASE64_ENCODER.encodeToString(item.getBytes())));
         json.put("keyValues", array);
      }

   }

   static {
      BASE64_DECODER = JsonUtil.BASE64_DECODER;
      BASE64_ENCODER = JsonUtil.BASE64_ENCODER;
   }
}
