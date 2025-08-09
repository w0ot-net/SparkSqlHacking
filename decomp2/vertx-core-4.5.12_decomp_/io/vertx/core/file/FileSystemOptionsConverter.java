package io.vertx.core.file;

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.impl.JsonUtil;
import java.util.Base64;
import java.util.Map;

public class FileSystemOptionsConverter {
   private static final Base64.Decoder BASE64_DECODER;
   private static final Base64.Encoder BASE64_ENCODER;

   static void fromJson(Iterable json, FileSystemOptions obj) {
      for(Map.Entry member : json) {
         switch ((String)member.getKey()) {
            case "classPathResolvingEnabled":
               if (member.getValue() instanceof Boolean) {
                  obj.setClassPathResolvingEnabled((Boolean)member.getValue());
               }
               break;
            case "fileCacheDir":
               if (member.getValue() instanceof String) {
                  obj.setFileCacheDir((String)member.getValue());
               }
               break;
            case "fileCachingEnabled":
               if (member.getValue() instanceof Boolean) {
                  obj.setFileCachingEnabled((Boolean)member.getValue());
               }
         }
      }

   }

   static void toJson(FileSystemOptions obj, JsonObject json) {
      toJson(obj, json.getMap());
   }

   static void toJson(FileSystemOptions obj, Map json) {
      json.put("classPathResolvingEnabled", obj.isClassPathResolvingEnabled());
      if (obj.getFileCacheDir() != null) {
         json.put("fileCacheDir", obj.getFileCacheDir());
      }

      json.put("fileCachingEnabled", obj.isFileCachingEnabled());
   }

   static {
      BASE64_DECODER = JsonUtil.BASE64_DECODER;
      BASE64_ENCODER = JsonUtil.BASE64_ENCODER;
   }
}
