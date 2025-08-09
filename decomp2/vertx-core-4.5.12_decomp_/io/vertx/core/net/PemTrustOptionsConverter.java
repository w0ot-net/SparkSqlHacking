package io.vertx.core.net;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.json.impl.JsonUtil;
import java.util.Base64;
import java.util.Map;

public class PemTrustOptionsConverter {
   private static final Base64.Decoder BASE64_DECODER;
   private static final Base64.Encoder BASE64_ENCODER;

   static void fromJson(Iterable json, PemTrustOptions obj) {
      for(Map.Entry member : json) {
         switch ((String)member.getKey()) {
            case "certPaths":
               if (member.getValue() instanceof JsonArray) {
                  ((Iterable)member.getValue()).forEach((item) -> {
                     if (item instanceof String) {
                        obj.addCertPath((String)item);
                     }

                  });
               }
               break;
            case "certValues":
               if (member.getValue() instanceof JsonArray) {
                  ((Iterable)member.getValue()).forEach((item) -> {
                     if (item instanceof String) {
                        obj.addCertValue(Buffer.buffer(BASE64_DECODER.decode((String)item)));
                     }

                  });
               }
         }
      }

   }

   static void toJson(PemTrustOptions obj, JsonObject json) {
      toJson(obj, json.getMap());
   }

   static void toJson(PemTrustOptions obj, Map json) {
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

   }

   static {
      BASE64_DECODER = JsonUtil.BASE64_DECODER;
      BASE64_ENCODER = JsonUtil.BASE64_ENCODER;
   }
}
