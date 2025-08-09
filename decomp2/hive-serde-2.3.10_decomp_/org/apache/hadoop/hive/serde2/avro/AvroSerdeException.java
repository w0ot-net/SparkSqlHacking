package org.apache.hadoop.hive.serde2.avro;

import org.apache.hadoop.hive.serde2.SerDeException;

public class AvroSerdeException extends SerDeException {
   public AvroSerdeException() {
   }

   public AvroSerdeException(String message) {
      super(message);
   }

   public AvroSerdeException(Throwable cause) {
      super(cause);
   }

   public AvroSerdeException(String message, Throwable cause) {
      super(message, cause);
   }
}
