package io.fabric8.zjsonpatch;

public class JsonPatchException extends RuntimeException {
   private final Operation operation;
   private final JsonPointer path;

   public JsonPatchException(String message) {
      this(message, (Operation)null, (JsonPointer)null);
   }

   public JsonPatchException(String message, Operation operation, JsonPointer path) {
      super(message);
      this.operation = operation;
      this.path = path;
   }

   public Operation getOperation() {
      return this.operation;
   }

   public JsonPointer getPath() {
      return this.path;
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      if (this.operation != null) {
         sb.append('[').append(this.operation).append(" Operation] ");
      }

      sb.append(this.getMessage());
      if (this.path != null) {
         sb.append(" at ").append(this.path.isRoot() ? "root" : this.path);
      }

      return sb.toString();
   }
}
