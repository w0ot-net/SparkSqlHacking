package org.apache.avro;

class ValidateCanBeRead implements SchemaValidationStrategy {
   public void validate(Schema toValidate, Schema existing) throws SchemaValidationException {
      ValidateMutualRead.canRead(toValidate, existing);
   }
}
