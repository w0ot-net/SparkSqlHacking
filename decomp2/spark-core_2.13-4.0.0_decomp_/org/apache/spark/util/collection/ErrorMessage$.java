package org.apache.spark.util.collection;

public final class ErrorMessage$ {
   public static final ErrorMessage$ MODULE$ = new ErrorMessage$();
   private static final String msg = "mutable operation is not supported";

   public final String msg() {
      return msg;
   }

   private ErrorMessage$() {
   }
}
