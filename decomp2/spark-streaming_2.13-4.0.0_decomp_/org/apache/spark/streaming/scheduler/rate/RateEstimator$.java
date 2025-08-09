package org.apache.spark.streaming.scheduler.rate;

import java.io.Serializable;
import org.apache.spark.SparkConf;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.StreamingConf$;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class RateEstimator$ implements Serializable {
   public static final RateEstimator$ MODULE$ = new RateEstimator$();

   public RateEstimator create(final SparkConf conf, final Duration batchInterval) {
      String var4 = (String)conf.get(StreamingConf$.MODULE$.BACKPRESSURE_RATE_ESTIMATOR());
      switch (var4 == null ? 0 : var4.hashCode()) {
         case 110987:
            if ("pid".equals(var4)) {
               double proportional = BoxesRunTime.unboxToDouble(conf.get(StreamingConf$.MODULE$.BACKPRESSURE_PID_PROPORTIONAL()));
               double integral = BoxesRunTime.unboxToDouble(conf.get(StreamingConf$.MODULE$.BACKPRESSURE_PID_INTEGRAL()));
               double derived = BoxesRunTime.unboxToDouble(conf.get(StreamingConf$.MODULE$.BACKPRESSURE_PID_DERIVED()));
               double minRate = BoxesRunTime.unboxToDouble(conf.get(StreamingConf$.MODULE$.BACKPRESSURE_PID_MIN_RATE()));
               return new PIDRateEstimator(batchInterval.milliseconds(), proportional, integral, derived, minRate);
            }
         default:
            throw new IllegalArgumentException("Unknown rate estimator: " + var4);
      }
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(RateEstimator$.class);
   }

   private RateEstimator$() {
   }
}
