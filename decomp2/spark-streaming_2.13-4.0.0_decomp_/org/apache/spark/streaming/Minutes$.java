package org.apache.spark.streaming;

public final class Minutes$ {
   public static final Minutes$ MODULE$ = new Minutes$();

   public Duration apply(final long minutes) {
      return new Duration(minutes * 60000L);
   }

   private Minutes$() {
   }
}
