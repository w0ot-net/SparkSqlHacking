package org.apache.avro;

public interface SchemaValidator {
   void validate(Schema toValidate, Iterable existing) throws SchemaValidationException;
}
