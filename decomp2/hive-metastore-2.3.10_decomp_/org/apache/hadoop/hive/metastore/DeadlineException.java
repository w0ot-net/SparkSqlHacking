package org.apache.hadoop.hive.metastore;

public class DeadlineException extends Exception {
   public DeadlineException(String message) {
      super(message);
   }
}
