package org.apache.spark.deploy;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.config.ConfigEntry;
import org.apache.spark.internal.config.Streaming$;
import org.apache.spark.internal.config.package$;
import org.apache.spark.util.Clock;
import org.apache.spark.util.SystemClock;
import scala.Option;
import scala.math.package.;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

public final class ExecutorFailureTracker$ {
   public static final ExecutorFailureTracker$ MODULE$ = new ExecutorFailureTracker$();

   public Clock $lessinit$greater$default$2() {
      return new SystemClock();
   }

   public int maxNumExecutorFailures(final SparkConf sparkConf) {
      return BoxesRunTime.unboxToInt(((Option)sparkConf.get((ConfigEntry)package$.MODULE$.MAX_EXECUTOR_FAILURES())).getOrElse((JFunction0.mcI.sp)() -> defaultMaxNumExecutorFailures$1(sparkConf)));
   }

   private static final int defaultMaxNumExecutorFailures$1(final SparkConf sparkConf$1) {
      int effectiveNumExecutors = org.apache.spark.util.Utils$.MODULE$.isStreamingDynamicAllocationEnabled(sparkConf$1) ? BoxesRunTime.unboxToInt(sparkConf$1.get(Streaming$.MODULE$.STREAMING_DYN_ALLOCATION_MAX_EXECUTORS())) : (org.apache.spark.util.Utils$.MODULE$.isDynamicAllocationEnabled(sparkConf$1) ? BoxesRunTime.unboxToInt(sparkConf$1.get(package$.MODULE$.DYN_ALLOCATION_MAX_EXECUTORS())) : BoxesRunTime.unboxToInt(((Option)sparkConf$1.get((ConfigEntry)package$.MODULE$.EXECUTOR_INSTANCES())).getOrElse((JFunction0.mcI.sp)() -> 0)));
      return .MODULE$.max(3, effectiveNumExecutors > 1073741823 ? Integer.MAX_VALUE : 2 * effectiveNumExecutors);
   }

   private ExecutorFailureTracker$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
