package org.apache.hadoop.hive.metastore;

public class HiveMetaException extends Exception {
   public HiveMetaException() {
   }

   public HiveMetaException(String message) {
      super(message);
   }

   public HiveMetaException(Throwable cause) {
      super(cause);
   }

   public HiveMetaException(String message, Throwable cause) {
      super(message, cause);
   }
}
