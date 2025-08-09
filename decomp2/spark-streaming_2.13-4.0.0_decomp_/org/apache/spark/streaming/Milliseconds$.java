package org.apache.spark.streaming;

public final class Milliseconds$ {
   public static final Milliseconds$ MODULE$ = new Milliseconds$();

   public Duration apply(final long milliseconds) {
      return new Duration(milliseconds);
   }

   private Milliseconds$() {
   }
}
