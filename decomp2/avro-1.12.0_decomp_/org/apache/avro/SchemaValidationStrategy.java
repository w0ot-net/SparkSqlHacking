package org.apache.avro;

public interface SchemaValidationStrategy {
   void validate(Schema toValidate, Schema existing) throws SchemaValidationException;
}
