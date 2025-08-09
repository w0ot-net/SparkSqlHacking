package org.apache.hadoop.hive.common;

public class NoDynamicValuesException extends RuntimeException {
   private static final long serialVersionUID = 1L;

   public NoDynamicValuesException(String message) {
      super(message);
   }
}
