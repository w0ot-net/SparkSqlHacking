package org.apache.avro;

public final class ValidateAll implements SchemaValidator {
   private final SchemaValidationStrategy strategy;

   public ValidateAll(SchemaValidationStrategy strategy) {
      this.strategy = strategy;
   }

   public void validate(Schema toValidate, Iterable schemasInOrder) throws SchemaValidationException {
      for(Schema existing : schemasInOrder) {
         this.strategy.validate(toValidate, existing);
      }

   }
}
