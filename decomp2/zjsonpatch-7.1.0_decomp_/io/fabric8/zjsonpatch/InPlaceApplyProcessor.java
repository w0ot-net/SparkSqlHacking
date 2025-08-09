package io.fabric8.zjsonpatch;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.EnumSet;

class InPlaceApplyProcessor implements JsonPatchProcessor {
   private JsonNode target;
   private final EnumSet flags;

   InPlaceApplyProcessor(JsonNode target, EnumSet flags) {
      this.target = target;
      this.flags = flags;
   }

   public JsonNode result() {
      return this.target;
   }

   public void move(JsonPointer fromPath, JsonPointer toPath) throws JsonPointerEvaluationException {
      JsonNode valueNode = fromPath.evaluate(this.target);
      this.remove(fromPath);
      this.set(toPath, valueNode, Operation.MOVE);
   }

   public void copy(JsonPointer fromPath, JsonPointer toPath) throws JsonPointerEvaluationException {
      JsonNode valueNode = fromPath.evaluate(this.target);
      JsonNode valueToCopy = valueNode != null ? valueNode.deepCopy() : null;
      this.set(toPath, valueToCopy, Operation.COPY);
   }

   private static String show(JsonNode value) {
      if (value != null && !value.isNull()) {
         if (value.isArray()) {
            return "array";
         } else {
            return value.isObject() ? "object" : "value " + value.toString();
         }
      } else {
         return "null";
      }
   }

   public void test(JsonPointer path, JsonNode value) throws JsonPointerEvaluationException {
      JsonNode valueNode = path.evaluate(this.target);
      if (!valueNode.equals(value)) {
         throw new JsonPatchException("Expected " + show(value) + " but found " + show(valueNode), Operation.TEST, path);
      }
   }

   public void add(JsonPointer path, JsonNode value) throws JsonPointerEvaluationException {
      this.set(path, value, Operation.ADD);
   }

   public void replace(JsonPointer path, JsonNode value) throws JsonPointerEvaluationException {
      if (path.isRoot()) {
         this.target = value;
      } else {
         JsonNode parentNode = path.getParent().evaluate(this.target);
         JsonPointer.RefToken token = path.last();
         if (parentNode.isObject()) {
            if (!this.flags.contains(CompatibilityFlags.ALLOW_MISSING_TARGET_OBJECT_ON_REPLACE) && !parentNode.has(token.getField())) {
               throw new JsonPatchException("Missing field \"" + token.getField() + "\"", Operation.REPLACE, path.getParent());
            }

            ((ObjectNode)parentNode).replace(token.getField(), value);
         } else {
            if (!parentNode.isArray()) {
               throw new JsonPatchException("Can't reference past scalar value", Operation.REPLACE, path.getParent());
            }

            if (token.getIndex() >= parentNode.size()) {
               throw new JsonPatchException("Array index " + token.getIndex() + " out of bounds", Operation.REPLACE, path.getParent());
            }

            ((ArrayNode)parentNode).set(token.getIndex(), value);
         }

      }
   }

   public void remove(JsonPointer path) throws JsonPointerEvaluationException {
      if (path.isRoot()) {
         throw new JsonPatchException("Cannot remove document root", Operation.REMOVE, path);
      } else {
         JsonNode parentNode = path.getParent().evaluate(this.target);
         JsonPointer.RefToken token = path.last();
         if (parentNode.isObject()) {
            if (this.flags.contains(CompatibilityFlags.FORBID_REMOVE_MISSING_OBJECT) && !parentNode.has(token.getField())) {
               throw new JsonPatchException("Missing field " + token.getField(), Operation.REMOVE, path.getParent());
            }

            ((ObjectNode)parentNode).remove(token.getField());
         } else {
            if (!parentNode.isArray()) {
               throw new JsonPatchException("Cannot reference past scalar value", Operation.REMOVE, path.getParent());
            }

            if (!this.flags.contains(CompatibilityFlags.REMOVE_NONE_EXISTING_ARRAY_ELEMENT) && token.getIndex() >= parentNode.size()) {
               throw new JsonPatchException("Array index " + token.getIndex() + " out of bounds", Operation.REMOVE, path.getParent());
            }

            ((ArrayNode)parentNode).remove(token.getIndex());
         }

      }
   }

   private void set(JsonPointer path, JsonNode value, Operation forOp) throws JsonPointerEvaluationException {
      if (path.isRoot()) {
         this.target = value;
      } else {
         JsonNode parentNode = path.getParent().evaluate(this.target);
         if (!parentNode.isContainerNode()) {
            throw new JsonPatchException("Cannot reference past scalar value", forOp, path.getParent());
         }

         if (parentNode.isArray()) {
            this.addToArray(path, value, parentNode);
         } else {
            this.addToObject(path, parentNode, value);
         }
      }

   }

   private void addToObject(JsonPointer path, JsonNode node, JsonNode value) {
      ObjectNode target = (ObjectNode)node;
      String key = path.last().getField();
      target.set(key, value);
   }

   private void addToArray(JsonPointer path, JsonNode value, JsonNode parentNode) {
      ArrayNode target = (ArrayNode)parentNode;
      int idx = path.last().getIndex();
      if (idx == Integer.MIN_VALUE) {
         target.add(value);
      } else {
         if (idx > target.size()) {
            throw new JsonPatchException("Array index " + idx + " out of bounds", Operation.ADD, path.getParent());
         }

         target.insert(idx, value);
      }

   }
}
