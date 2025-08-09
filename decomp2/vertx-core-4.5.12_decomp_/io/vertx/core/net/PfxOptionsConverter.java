package io.vertx.core.net;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;
import io.vertx.core.json.impl.JsonUtil;
import java.util.Base64;
import java.util.Map;

public class PfxOptionsConverter {
   private static final Base64.Decoder BASE64_DECODER;
   private static final Base64.Encoder BASE64_ENCODER;

   static void fromJson(Iterable json, PfxOptions obj) {
      for(Map.Entry member : json) {
         switch ((String)member.getKey()) {
            case "alias":
               if (member.getValue() instanceof String) {
                  obj.setAlias((String)member.getValue());
               }
               break;
            case "aliasPassword":
               if (member.getValue() instanceof String) {
                  obj.setAliasPassword((String)member.getValue());
               }
               break;
            case "password":
               if (member.getValue() instanceof String) {
                  obj.setPassword((String)member.getValue());
               }
               break;
            case "path":
               if (member.getValue() instanceof String) {
                  obj.setPath((String)member.getValue());
               }
               break;
            case "value":
               if (member.getValue() instanceof String) {
                  obj.setValue(Buffer.buffer(BASE64_DECODER.decode((String)member.getValue())));
               }
         }
      }

   }

   static void toJson(PfxOptions obj, JsonObject json) {
      toJson(obj, json.getMap());
   }

   static void toJson(PfxOptions obj, Map json) {
      if (obj.getAlias() != null) {
         json.put("alias", obj.getAlias());
      }

      if (obj.getAliasPassword() != null) {
         json.put("aliasPassword", obj.getAliasPassword());
      }

      if (obj.getPassword() != null) {
         json.put("password", obj.getPassword());
      }

      if (obj.getPath() != null) {
         json.put("path", obj.getPath());
      }

      if (obj.getValue() != null) {
         json.put("value", BASE64_ENCODER.encodeToString(obj.getValue().getBytes()));
      }

   }

   static {
      BASE64_DECODER = JsonUtil.BASE64_DECODER;
      BASE64_ENCODER = JsonUtil.BASE64_ENCODER;
   }
}
