package org.apache.avro;

class ValidateCanRead implements SchemaValidationStrategy {
   public void validate(Schema toValidate, Schema existing) throws SchemaValidationException {
      ValidateMutualRead.canRead(existing, toValidate);
   }
}
