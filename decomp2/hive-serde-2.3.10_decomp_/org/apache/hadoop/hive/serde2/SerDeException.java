package org.apache.hadoop.hive.serde2;

public class SerDeException extends Exception {
   private static final long serialVersionUID = 1L;

   public SerDeException() {
   }

   public SerDeException(String message) {
      super(message);
   }

   public SerDeException(Throwable cause) {
      super(cause);
   }

   public SerDeException(String message, Throwable cause) {
      super(message, cause);
   }
}
