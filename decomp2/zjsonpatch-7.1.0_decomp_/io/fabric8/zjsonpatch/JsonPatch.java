package io.fabric8.zjsonpatch;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.NullNode;
import java.util.EnumSet;

public class JsonPatch {
   private static final String OP = "op";
   public static final String VALUE = "value";
   private static final String PATH = "path";
   public static final String FROM = "from";

   private JsonPatch() {
   }

   private static JsonNode getPatchStringAttr(JsonNode jsonNode, String attr) {
      JsonNode child = getPatchAttr(jsonNode, attr);
      if (!child.isTextual()) {
         throw new JsonPatchException("Invalid JSON Patch payload (non-text '" + attr + "' field)");
      } else {
         return child;
      }
   }

   private static JsonNode getPatchAttr(JsonNode jsonNode, String attr) {
      JsonNode child = jsonNode.get(attr);
      if (child == null) {
         throw new JsonPatchException("Invalid JSON Patch payload (missing '" + attr + "' field)");
      } else {
         return child;
      }
   }

   private static JsonNode getPatchAttrWithDefault(JsonNode jsonNode, String attr, JsonNode defaultValue) {
      JsonNode child = jsonNode.get(attr);
      return child == null ? defaultValue : child;
   }

   private static void process(JsonNode patch, JsonPatchProcessor processor, EnumSet flags) {
      if (!patch.isArray()) {
         throw new JsonPatchException("Invalid JSON Patch payload (not an array)");
      } else {
         for(JsonNode jsonNode : patch) {
            if (!jsonNode.isObject()) {
               throw new JsonPatchException("Invalid JSON Patch payload (not an object)");
            }

            Operation operation = Operation.fromRfcName(getPatchStringAttr(jsonNode, "op").textValue());
            JsonPointer path = JsonPointer.parse(getPatchStringAttr(jsonNode, "path").textValue());

            try {
               switch (operation) {
                  case REMOVE:
                     processor.remove(path);
                     break;
                  case ADD:
                     JsonNode value;
                     if (!flags.contains(CompatibilityFlags.MISSING_VALUES_AS_NULLS)) {
                        value = getPatchAttr(jsonNode, "value");
                     } else {
                        value = getPatchAttrWithDefault(jsonNode, "value", NullNode.getInstance());
                     }

                     processor.add(path, value.deepCopy());
                     break;
                  case REPLACE:
                     JsonNode value;
                     if (!flags.contains(CompatibilityFlags.MISSING_VALUES_AS_NULLS)) {
                        value = getPatchAttr(jsonNode, "value");
                     } else {
                        value = getPatchAttrWithDefault(jsonNode, "value", NullNode.getInstance());
                     }

                     processor.replace(path, value.deepCopy());
                     break;
                  case MOVE:
                     JsonPointer fromPath = JsonPointer.parse(getPatchStringAttr(jsonNode, "from").textValue());
                     processor.move(fromPath, path);
                     break;
                  case COPY:
                     JsonPointer fromPath = JsonPointer.parse(getPatchStringAttr(jsonNode, "from").textValue());
                     processor.copy(fromPath, path);
                     break;
                  case TEST:
                     JsonNode value;
                     if (!flags.contains(CompatibilityFlags.MISSING_VALUES_AS_NULLS)) {
                        value = getPatchAttr(jsonNode, "value");
                     } else {
                        value = getPatchAttrWithDefault(jsonNode, "value", NullNode.getInstance());
                     }

                     processor.test(path, value.deepCopy());
               }
            } catch (JsonPointerEvaluationException e) {
               throw new JsonPatchException(e.getMessage(), operation, e.getPath());
            }
         }

      }
   }

   public static JsonNode apply(JsonNode patch, JsonNode source, EnumSet flags) {
      CopyingApplyProcessor processor = new CopyingApplyProcessor(source, flags);
      process(patch, processor, flags);
      return processor.result();
   }

   public static JsonNode apply(JsonNode patch, JsonNode source) {
      return apply(patch, source, CompatibilityFlags.defaults());
   }
}
