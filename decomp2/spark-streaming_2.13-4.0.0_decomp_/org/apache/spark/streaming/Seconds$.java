package org.apache.spark.streaming;

public final class Seconds$ {
   public static final Seconds$ MODULE$ = new Seconds$();

   public Duration apply(final long seconds) {
      return new Duration(seconds * 1000L);
   }

   private Seconds$() {
   }
}
