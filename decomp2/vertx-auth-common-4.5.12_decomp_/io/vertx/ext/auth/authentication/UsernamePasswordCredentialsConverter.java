package io.vertx.ext.auth.authentication;

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.impl.JsonUtil;
import java.util.Base64;
import java.util.Map;

public class UsernamePasswordCredentialsConverter {
   private static final Base64.Decoder BASE64_DECODER;
   private static final Base64.Encoder BASE64_ENCODER;

   static void fromJson(Iterable json, UsernamePasswordCredentials obj) {
      for(Map.Entry member : json) {
         switch ((String)member.getKey()) {
            case "password":
               if (member.getValue() instanceof String) {
                  obj.setPassword((String)member.getValue());
               }
               break;
            case "username":
               if (member.getValue() instanceof String) {
                  obj.setUsername((String)member.getValue());
               }
         }
      }

   }

   static void toJson(UsernamePasswordCredentials obj, JsonObject json) {
      toJson(obj, json.getMap());
   }

   static void toJson(UsernamePasswordCredentials obj, Map json) {
      if (obj.getPassword() != null) {
         json.put("password", obj.getPassword());
      }

      if (obj.getUsername() != null) {
         json.put("username", obj.getUsername());
      }

   }

   static {
      BASE64_DECODER = JsonUtil.BASE64_DECODER;
      BASE64_ENCODER = JsonUtil.BASE64_ENCODER;
   }
}
