package org.apache.spark.util.sketch;

public class IncompatibleMergeException extends Exception {
   public IncompatibleMergeException(String message) {
      super(message);
   }
}
