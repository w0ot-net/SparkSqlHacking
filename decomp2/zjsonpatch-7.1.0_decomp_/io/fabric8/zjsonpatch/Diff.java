package io.fabric8.zjsonpatch;

import com.fasterxml.jackson.databind.JsonNode;

class Diff {
   private final Operation operation;
   private final JsonPointer path;
   private final JsonNode value;
   private JsonPointer toPath;
   private final JsonNode srcValue;

   Diff(Operation operation, JsonPointer path, JsonNode value) {
      this.operation = operation;
      this.path = path;
      this.value = value;
      this.srcValue = null;
   }

   Diff(Operation operation, JsonPointer fromPath, JsonPointer toPath) {
      this.operation = operation;
      this.path = fromPath;
      this.toPath = toPath;
      this.value = null;
      this.srcValue = null;
   }

   Diff(Operation operation, JsonPointer path, JsonNode srcValue, JsonNode value) {
      this.operation = operation;
      this.path = path;
      this.value = value;
      this.srcValue = srcValue;
   }

   public Operation getOperation() {
      return this.operation;
   }

   public JsonPointer getPath() {
      return this.path;
   }

   public JsonNode getValue() {
      return this.value;
   }

   public static Diff generateDiff(Operation replace, JsonPointer path, JsonNode target) {
      return new Diff(replace, path, target);
   }

   public static Diff generateDiff(Operation replace, JsonPointer path, JsonNode source, JsonNode target) {
      return new Diff(replace, path, source, target);
   }

   JsonPointer getToPath() {
      return this.toPath;
   }

   public JsonNode getSrcValue() {
      return this.srcValue;
   }
}
