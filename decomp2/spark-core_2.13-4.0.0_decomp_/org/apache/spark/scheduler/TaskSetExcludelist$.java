package org.apache.spark.scheduler;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.config.ConfigEntry;
import scala.Option;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

public final class TaskSetExcludelist$ {
   public static final TaskSetExcludelist$ MODULE$ = new TaskSetExcludelist$();

   public boolean $lessinit$greater$default$6() {
      return false;
   }

   public boolean isExcludeOnFailureEnabled(final SparkConf conf) {
      return BoxesRunTime.unboxToBoolean(((Option)conf.get((ConfigEntry)org.apache.spark.internal.config.package$.MODULE$.EXCLUDE_ON_FAILURE_ENABLED_TASK_AND_STAGE())).orElse(() -> (Option)conf.get((ConfigEntry)org.apache.spark.internal.config.package$.MODULE$.EXCLUDE_ON_FAILURE_ENABLED())).getOrElse((JFunction0.mcZ.sp)() -> false));
   }

   private TaskSetExcludelist$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
