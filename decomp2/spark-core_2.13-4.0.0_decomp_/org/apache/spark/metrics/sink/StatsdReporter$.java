package org.apache.spark.metrics.sink;

import com.codahale.metrics.MetricFilter;
import java.util.concurrent.TimeUnit;

public final class StatsdReporter$ {
   public static final StatsdReporter$ MODULE$ = new StatsdReporter$();

   public String $lessinit$greater$default$2() {
      return "127.0.0.1";
   }

   public int $lessinit$greater$default$3() {
      return 8125;
   }

   public String $lessinit$greater$default$4() {
      return "";
   }

   public MetricFilter $lessinit$greater$default$5() {
      return MetricFilter.ALL;
   }

   public TimeUnit $lessinit$greater$default$6() {
      return TimeUnit.SECONDS;
   }

   public TimeUnit $lessinit$greater$default$7() {
      return TimeUnit.MILLISECONDS;
   }

   private StatsdReporter$() {
   }
}
