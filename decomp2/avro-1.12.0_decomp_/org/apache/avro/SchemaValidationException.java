package org.apache.avro;

public class SchemaValidationException extends Exception {
   public SchemaValidationException(Schema reader, Schema writer) {
      super(getMessage(reader, writer));
   }

   public SchemaValidationException(Schema reader, Schema writer, Throwable cause) {
      super(getMessage(reader, writer), cause);
   }

   private static String getMessage(Schema reader, Schema writer) {
      String var10000 = writer.toString(true);
      return "Unable to read schema: \n" + var10000 + "\nusing schema:\n" + reader.toString(true);
   }
}
