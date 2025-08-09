package io.fabric8.zjsonpatch;

import com.fasterxml.jackson.databind.JsonNode;

public class JsonPointerEvaluationException extends Exception {
   private final JsonPointer path;
   private final JsonNode target;

   public JsonPointerEvaluationException(String message, JsonPointer path, JsonNode target) {
      super(message);
      this.path = path;
      this.target = target;
   }

   public JsonPointer getPath() {
      return this.path;
   }

   public JsonNode getTarget() {
      return this.target;
   }
}
