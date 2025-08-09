package org.apache.spark.metrics.source;

import org.apache.spark.SparkContext;
import scala.collection.immutable.Map;

public final class LongAccumulatorSource$ {
   public static final LongAccumulatorSource$ MODULE$ = new LongAccumulatorSource$();

   public void register(final SparkContext sc, final Map accumulators) {
      LongAccumulatorSource source = new LongAccumulatorSource();
      source.register(accumulators);
      sc.env().metricsSystem().registerSource(source);
   }

   private LongAccumulatorSource$() {
   }
}
