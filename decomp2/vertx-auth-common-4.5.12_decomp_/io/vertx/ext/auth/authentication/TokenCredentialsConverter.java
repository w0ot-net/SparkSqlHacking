package io.vertx.ext.auth.authentication;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.json.impl.JsonUtil;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Map;

public class TokenCredentialsConverter {
   private static final Base64.Decoder BASE64_DECODER;
   private static final Base64.Encoder BASE64_ENCODER;

   static void fromJson(Iterable json, TokenCredentials obj) {
      for(Map.Entry member : json) {
         switch ((String)member.getKey()) {
            case "scopes":
               if (member.getValue() instanceof JsonArray) {
                  ArrayList<String> list = new ArrayList();
                  ((Iterable)member.getValue()).forEach((item) -> {
                     if (item instanceof String) {
                        list.add((String)item);
                     }

                  });
                  obj.setScopes(list);
               }
               break;
            case "token":
               if (member.getValue() instanceof String) {
                  obj.setToken((String)member.getValue());
               }
         }
      }

   }

   static void toJson(TokenCredentials obj, JsonObject json) {
      toJson(obj, json.getMap());
   }

   static void toJson(TokenCredentials obj, Map json) {
      if (obj.getScopes() != null) {
         JsonArray array = new JsonArray();
         obj.getScopes().forEach((item) -> array.add(item));
         json.put("scopes", array);
      }

      if (obj.getToken() != null) {
         json.put("token", obj.getToken());
      }

   }

   static {
      BASE64_DECODER = JsonUtil.BASE64_DECODER;
      BASE64_ENCODER = JsonUtil.BASE64_ENCODER;
   }
}
