package org.apache.avro.message;

import org.apache.avro.AvroRuntimeException;

public class MissingSchemaException extends AvroRuntimeException {
   public MissingSchemaException(String message) {
      super(message);
   }
}
