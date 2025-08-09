package org.apache.spark.metrics.sink;

public final class StatsdMetricType$ {
   public static final StatsdMetricType$ MODULE$ = new StatsdMetricType$();
   private static final String COUNTER = "c";
   private static final String GAUGE = "g";
   private static final String TIMER = "ms";
   private static final String Set = "s";

   public String COUNTER() {
      return COUNTER;
   }

   public String GAUGE() {
      return GAUGE;
   }

   public String TIMER() {
      return TIMER;
   }

   public String Set() {
      return Set;
   }

   private StatsdMetricType$() {
   }
}
