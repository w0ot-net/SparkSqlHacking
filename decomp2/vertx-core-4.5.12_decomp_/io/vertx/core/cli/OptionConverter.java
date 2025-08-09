package io.vertx.core.cli;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.json.impl.JsonUtil;
import java.util.Base64;
import java.util.LinkedHashSet;
import java.util.Map;

public class OptionConverter {
   private static final Base64.Decoder BASE64_DECODER;
   private static final Base64.Encoder BASE64_ENCODER;

   static void fromJson(Iterable json, Option obj) {
      for(Map.Entry member : json) {
         switch ((String)member.getKey()) {
            case "argName":
               if (member.getValue() instanceof String) {
                  obj.setArgName((String)member.getValue());
               }
               break;
            case "choices":
               if (member.getValue() instanceof JsonArray) {
                  LinkedHashSet<String> list = new LinkedHashSet();
                  ((Iterable)member.getValue()).forEach((item) -> {
                     if (item instanceof String) {
                        list.add((String)item);
                     }

                  });
                  obj.setChoices(list);
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
            case "flag":
               if (member.getValue() instanceof Boolean) {
                  obj.setFlag((Boolean)member.getValue());
               }
               break;
            case "help":
               if (member.getValue() instanceof Boolean) {
                  obj.setHelp((Boolean)member.getValue());
               }
               break;
            case "hidden":
               if (member.getValue() instanceof Boolean) {
                  obj.setHidden((Boolean)member.getValue());
               }
               break;
            case "longName":
               if (member.getValue() instanceof String) {
                  obj.setLongName((String)member.getValue());
               }
               break;
            case "multiValued":
               if (member.getValue() instanceof Boolean) {
                  obj.setMultiValued((Boolean)member.getValue());
               }
            case "name":
            default:
               break;
            case "required":
               if (member.getValue() instanceof Boolean) {
                  obj.setRequired((Boolean)member.getValue());
               }
               break;
            case "shortName":
               if (member.getValue() instanceof String) {
                  obj.setShortName((String)member.getValue());
               }
               break;
            case "singleValued":
               if (member.getValue() instanceof Boolean) {
                  obj.setSingleValued((Boolean)member.getValue());
               }
         }
      }

   }

   static void toJson(Option obj, JsonObject json) {
      toJson(obj, json.getMap());
   }

   static void toJson(Option obj, Map json) {
      if (obj.getArgName() != null) {
         json.put("argName", obj.getArgName());
      }

      if (obj.getChoices() != null) {
         JsonArray array = new JsonArray();
         obj.getChoices().forEach((item) -> array.add(item));
         json.put("choices", array);
      }

      if (obj.getDefaultValue() != null) {
         json.put("defaultValue", obj.getDefaultValue());
      }

      if (obj.getDescription() != null) {
         json.put("description", obj.getDescription());
      }

      json.put("flag", obj.isFlag());
      json.put("help", obj.isHelp());
      json.put("hidden", obj.isHidden());
      if (obj.getLongName() != null) {
         json.put("longName", obj.getLongName());
      }

      json.put("multiValued", obj.isMultiValued());
      if (obj.getName() != null) {
         json.put("name", obj.getName());
      }

      json.put("required", obj.isRequired());
      if (obj.getShortName() != null) {
         json.put("shortName", obj.getShortName());
      }

      json.put("singleValued", obj.isSingleValued());
   }

   static {
      BASE64_DECODER = JsonUtil.BASE64_DECODER;
      BASE64_ENCODER = JsonUtil.BASE64_ENCODER;
   }
}
