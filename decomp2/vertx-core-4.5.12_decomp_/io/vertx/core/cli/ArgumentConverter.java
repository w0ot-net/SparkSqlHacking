package io.vertx.core.cli;

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.impl.JsonUtil;
import java.util.Base64;
import java.util.Map;

public class ArgumentConverter {
   private static final Base64.Decoder BASE64_DECODER;
   private static final Base64.Encoder BASE64_ENCODER;

   static void fromJson(Iterable json, Argument obj) {
      for(Map.Entry member : json) {
         switch ((String)member.getKey()) {
            case "argName":
               if (member.getValue() instanceof String) {
                  obj.setArgName((String)member.getValue());
               }
               break;
            case "defaultValue":
               if (member.getValue() instanceof String) {
                  obj.setDefaultValue((String)member.getValue());
               }
               break;
            case "description":
               if (member.getValue() instanceof String) {
                  obj.setDescription((String)member.getValue());
               }
               break;
            case "hidden":
               if (member.getValue() instanceof Boolean) {
                  obj.setHidden((Boolean)member.getValue());
               }
               break;
            case "index":
               if (member.getValue() instanceof Number) {
                  obj.setIndex(((Number)member.getValue()).intValue());
               }
               break;
            case "multiValued":
               if (member.getValue() instanceof Boolean) {
                  obj.setMultiValued((Boolean)member.getValue());
               }
               break;
            case "required":
               if (member.getValue() instanceof Boolean) {
                  obj.setRequired((Boolean)member.getValue());
               }
         }
      }

   }

   static void toJson(Argument obj, JsonObject json) {
      toJson(obj, json.getMap());
   }

   static void toJson(Argument obj, Map json) {
      if (obj.getArgName() != null) {
         json.put("argName", obj.getArgName());
      }

      if (obj.getDefaultValue() != null) {
         json.put("defaultValue", obj.getDefaultValue());
      }

      if (obj.getDescription() != null) {
         json.put("description", obj.getDescription());
      }

      json.put("hidden", obj.isHidden());
      json.put("index", obj.getIndex());
      json.put("multiValued", obj.isMultiValued());
      json.put("required", obj.isRequired());
   }

   static {
      BASE64_DECODER = JsonUtil.BASE64_DECODER;
      BASE64_ENCODER = JsonUtil.BASE64_ENCODER;
   }
}
