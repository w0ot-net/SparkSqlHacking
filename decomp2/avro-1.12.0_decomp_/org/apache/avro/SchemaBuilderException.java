package org.apache.avro;

public class SchemaBuilderException extends AvroRuntimeException {
   public SchemaBuilderException(Throwable cause) {
      super(cause);
   }

   public SchemaBuilderException(String message) {
      super(message);
   }
}
