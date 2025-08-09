package org.apache.spark.streaming.scheduler;

import java.io.Serializable;
import org.apache.spark.SparkConf;
import org.apache.spark.streaming.StreamingConf$;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class RateController$ implements Serializable {
   public static final RateController$ MODULE$ = new RateController$();

   public boolean isBackPressureEnabled(final SparkConf conf) {
      return BoxesRunTime.unboxToBoolean(conf.get(StreamingConf$.MODULE$.BACKPRESSURE_ENABLED()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(RateController$.class);
   }

   private RateController$() {
   }
}
