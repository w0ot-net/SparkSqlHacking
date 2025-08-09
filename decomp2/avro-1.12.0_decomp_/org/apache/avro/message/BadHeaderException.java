package org.apache.avro.message;

import org.apache.avro.AvroRuntimeException;

public class BadHeaderException extends AvroRuntimeException {
   public BadHeaderException(String message) {
      super(message);
   }
}
