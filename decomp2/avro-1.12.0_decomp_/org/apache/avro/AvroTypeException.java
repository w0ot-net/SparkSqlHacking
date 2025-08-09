package org.apache.avro;

public class AvroTypeException extends AvroRuntimeException {
   public AvroTypeException(String message) {
      super(message);
   }

   public AvroTypeException(String message, Throwable cause) {
      super(message, cause);
   }
}
