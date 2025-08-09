package org.apache.spark.metrics;

import java.util.concurrent.TimeUnit;
import org.apache.spark.SparkConf;
import scala.collection.StringOps.;
import scala.util.matching.Regex;

public final class MetricsSystem$ {
   public static final MetricsSystem$ MODULE$ = new MetricsSystem$();
   private static final Regex SINK_REGEX;
   private static final Regex SOURCE_REGEX;
   private static final TimeUnit MINIMAL_POLL_UNIT;
   private static final int MINIMAL_POLL_PERIOD;

   static {
      SINK_REGEX = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString("^sink\\.(.+)\\.(.+)"));
      SOURCE_REGEX = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString("^source\\.(.+)\\.(.+)"));
      MINIMAL_POLL_UNIT = TimeUnit.SECONDS;
      MINIMAL_POLL_PERIOD = 1;
   }

   public Regex SINK_REGEX() {
      return SINK_REGEX;
   }

   public Regex SOURCE_REGEX() {
      return SOURCE_REGEX;
   }

   public void checkMinimalPollingPeriod(final TimeUnit pollUnit, final int pollPeriod) {
      long period = MINIMAL_POLL_UNIT.convert((long)pollPeriod, pollUnit);
      if (period < (long)MINIMAL_POLL_PERIOD) {
         throw new IllegalArgumentException("Polling period " + pollPeriod + " " + pollUnit + " below than minimal polling period ");
      }
   }

   public MetricsSystem createMetricsSystem(final String instance, final SparkConf conf) {
      return new MetricsSystem(instance, conf);
   }

   private MetricsSystem$() {
   }
}
