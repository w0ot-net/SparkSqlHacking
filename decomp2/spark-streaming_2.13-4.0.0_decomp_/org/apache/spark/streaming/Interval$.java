package org.apache.spark.streaming;

public final class Interval$ {
   public static final Interval$ MODULE$ = new Interval$();

   public Interval currentInterval(final Duration duration) {
      Time time = new Time(System.currentTimeMillis());
      Time intervalBegin = time.floor(duration);
      return new Interval(intervalBegin, intervalBegin.$plus(duration));
   }

   private Interval$() {
   }
}
