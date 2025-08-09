package io.vertx.core.file;

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.impl.JsonUtil;
import java.util.Base64;
import java.util.Map;

public class CopyOptionsConverter {
   private static final Base64.Decoder BASE64_DECODER;
   private static final Base64.Encoder BASE64_ENCODER;

   static void fromJson(Iterable json, CopyOptions obj) {
      for(Map.Entry member : json) {
         switch ((String)member.getKey()) {
            case "atomicMove":
               if (member.getValue() instanceof Boolean) {
                  obj.setAtomicMove((Boolean)member.getValue());
               }
               break;
            case "copyAttributes":
               if (member.getValue() instanceof Boolean) {
                  obj.setCopyAttributes((Boolean)member.getValue());
               }
               break;
            case "nofollowLinks":
               if (member.getValue() instanceof Boolean) {
                  obj.setNofollowLinks((Boolean)member.getValue());
               }
               break;
            case "replaceExisting":
               if (member.getValue() instanceof Boolean) {
                  obj.setReplaceExisting((Boolean)member.getValue());
               }
         }
      }

   }

   static void toJson(CopyOptions obj, JsonObject json) {
      toJson(obj, json.getMap());
   }

   static void toJson(CopyOptions obj, Map json) {
      json.put("atomicMove", obj.isAtomicMove());
      json.put("copyAttributes", obj.isCopyAttributes());
      json.put("nofollowLinks", obj.isNofollowLinks());
      json.put("replaceExisting", obj.isReplaceExisting());
   }

   static {
      BASE64_DECODER = JsonUtil.BASE64_DECODER;
      BASE64_ENCODER = JsonUtil.BASE64_ENCODER;
   }
}
