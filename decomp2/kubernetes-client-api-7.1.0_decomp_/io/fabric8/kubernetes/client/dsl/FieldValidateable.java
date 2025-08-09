package io.fabric8.kubernetes.client.dsl;

public interface FieldValidateable {
   Object fieldValidation(Validation var1);

   public static enum Validation {
      WARN("Warn"),
      IGNORE("Ignore"),
      STRICT("Strict");

      String parameterValue;

      private Validation(String parameterValue) {
         this.parameterValue = parameterValue;
      }

      public String parameterValue() {
         return this.parameterValue;
      }
   }
}
