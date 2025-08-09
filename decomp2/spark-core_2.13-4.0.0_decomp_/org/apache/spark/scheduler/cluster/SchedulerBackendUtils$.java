package org.apache.spark.scheduler.cluster;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.config.ConfigEntry;
import org.apache.spark.internal.config.package$;
import org.apache.spark.util.Utils$;
import scala.Option;
import scala.Predef.;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

public final class SchedulerBackendUtils$ {
   public static final SchedulerBackendUtils$ MODULE$ = new SchedulerBackendUtils$();
   private static final int DEFAULT_NUMBER_EXECUTORS = 2;

   public int DEFAULT_NUMBER_EXECUTORS() {
      return DEFAULT_NUMBER_EXECUTORS;
   }

   public int getInitialTargetExecutorNumber(final SparkConf conf, final int numExecutors) {
      if (!Utils$.MODULE$.isDynamicAllocationEnabled(conf)) {
         return BoxesRunTime.unboxToInt(((Option)conf.get((ConfigEntry)package$.MODULE$.EXECUTOR_INSTANCES())).getOrElse((JFunction0.mcI.sp)() -> numExecutors));
      } else {
         int minNumExecutors = BoxesRunTime.unboxToInt(conf.get(package$.MODULE$.DYN_ALLOCATION_MIN_EXECUTORS()));
         int initialNumExecutors = Utils$.MODULE$.getDynamicAllocationInitialExecutors(conf);
         int maxNumExecutors = BoxesRunTime.unboxToInt(conf.get(package$.MODULE$.DYN_ALLOCATION_MAX_EXECUTORS()));
         .MODULE$.require(initialNumExecutors >= minNumExecutors && initialNumExecutors <= maxNumExecutors, () -> "initial executor number " + initialNumExecutors + " must between min executor number " + minNumExecutors + " and max executor number " + maxNumExecutors);
         return initialNumExecutors;
      }
   }

   public int getInitialTargetExecutorNumber$default$2() {
      return this.DEFAULT_NUMBER_EXECUTORS();
   }

   private SchedulerBackendUtils$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
