package io.vertx.core.http;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;
import io.vertx.core.json.impl.JsonUtil;
import java.util.Base64;
import java.util.Map;

public class GoAwayConverter {
   private static final Base64.Decoder BASE64_DECODER;
   private static final Base64.Encoder BASE64_ENCODER;

   static void fromJson(Iterable json, GoAway obj) {
      for(Map.Entry member : json) {
         switch ((String)member.getKey()) {
            case "debugData":
               if (member.getValue() instanceof String) {
                  obj.setDebugData(Buffer.buffer(BASE64_DECODER.decode((String)member.getValue())));
               }
               break;
            case "errorCode":
               if (member.getValue() instanceof Number) {
                  obj.setErrorCode(((Number)member.getValue()).longValue());
               }
               break;
            case "lastStreamId":
               if (member.getValue() instanceof Number) {
                  obj.setLastStreamId(((Number)member.getValue()).intValue());
               }
         }
      }

   }

   static void toJson(GoAway obj, JsonObject json) {
      toJson(obj, json.getMap());
   }

   static void toJson(GoAway obj, Map json) {
      if (obj.getDebugData() != null) {
         json.put("debugData", BASE64_ENCODER.encodeToString(obj.getDebugData().getBytes()));
      }

      json.put("errorCode", obj.getErrorCode());
      json.put("lastStreamId", obj.getLastStreamId());
   }

   static {
      BASE64_DECODER = JsonUtil.BASE64_DECODER;
      BASE64_ENCODER = JsonUtil.BASE64_ENCODER;
   }
}
