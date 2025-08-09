package io.vertx.ext.web.client;

import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.json.impl.JsonUtil;
import java.util.Base64;
import java.util.LinkedHashSet;
import java.util.Map;

public class CachingWebClientOptionsConverter {
   private static final Base64.Decoder BASE64_DECODER;
   private static final Base64.Encoder BASE64_ENCODER;

   static void fromJson(Iterable json, CachingWebClientOptions obj) {
      for(Map.Entry member : json) {
         switch ((String)member.getKey()) {
            case "cachedMethods":
               if (member.getValue() instanceof JsonArray) {
                  LinkedHashSet<HttpMethod> list = new LinkedHashSet();
                  ((Iterable)member.getValue()).forEach((item) -> {
                     if (item instanceof String) {
                        list.add(new HttpMethod((String)item));
                     }

                  });
                  obj.setCachedMethods(list);
               }
               break;
            case "cachedStatusCodes":
               if (member.getValue() instanceof JsonArray) {
                  LinkedHashSet<Integer> list = new LinkedHashSet();
                  ((Iterable)member.getValue()).forEach((item) -> {
                     if (item instanceof Number) {
                        list.add(((Number)item).intValue());
                     }

                  });
                  obj.setCachedStatusCodes(list);
               }
               break;
            case "enableVaryCaching":
               if (member.getValue() instanceof Boolean) {
                  obj.setEnableVaryCaching((Boolean)member.getValue());
               }
            case "varyCachingEnabled":
         }
      }

   }

   static void toJson(CachingWebClientOptions obj, JsonObject json) {
      toJson(obj, json.getMap());
   }

   static void toJson(CachingWebClientOptions obj, Map json) {
      if (obj.getCachedMethods() != null) {
         JsonArray array = new JsonArray();
         obj.getCachedMethods().forEach((item) -> array.add(item.toJson()));
         json.put("cachedMethods", array);
      }

      if (obj.getCachedStatusCodes() != null) {
         JsonArray array = new JsonArray();
         obj.getCachedStatusCodes().forEach((item) -> array.add(item));
         json.put("cachedStatusCodes", array);
      }

      json.put("varyCachingEnabled", obj.isVaryCachingEnabled());
   }

   static {
      BASE64_DECODER = JsonUtil.BASE64_DECODER;
      BASE64_ENCODER = JsonUtil.BASE64_ENCODER;
   }
}
