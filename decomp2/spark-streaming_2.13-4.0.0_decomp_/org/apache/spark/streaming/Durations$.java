package org.apache.spark.streaming;

public final class Durations$ {
   public static final Durations$ MODULE$ = new Durations$();

   public Duration milliseconds(final long milliseconds) {
      return Milliseconds$.MODULE$.apply(milliseconds);
   }

   public Duration seconds(final long seconds) {
      return Seconds$.MODULE$.apply(seconds);
   }

   public Duration minutes(final long minutes) {
      return Minutes$.MODULE$.apply(minutes);
   }

   private Durations$() {
   }
}
