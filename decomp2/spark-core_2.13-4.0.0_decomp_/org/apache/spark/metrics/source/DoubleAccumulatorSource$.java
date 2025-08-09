package org.apache.spark.metrics.source;

import org.apache.spark.SparkContext;
import scala.collection.immutable.Map;

public final class DoubleAccumulatorSource$ {
   public static final DoubleAccumulatorSource$ MODULE$ = new DoubleAccumulatorSource$();

   public void register(final SparkContext sc, final Map accumulators) {
      DoubleAccumulatorSource source = new DoubleAccumulatorSource();
      source.register(accumulators);
      sc.env().metricsSystem().registerSource(source);
   }

   private DoubleAccumulatorSource$() {
   }
}
