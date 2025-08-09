package org.apache.spark.internal.config;

import java.lang.invoke.SerializedLambda;
import java.util.concurrent.TimeUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;

public final class Streaming$ {
   public static final Streaming$ MODULE$ = new Streaming$();
   private static final ConfigEntry STREAMING_DYN_ALLOCATION_ENABLED = (new ConfigBuilder("spark.streaming.dynamicAllocation.enabled")).version("3.0.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
   private static final ConfigEntry STREAMING_DYN_ALLOCATION_TESTING = (new ConfigBuilder("spark.streaming.dynamicAllocation.testing")).version("3.0.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
   private static final OptionalConfigEntry STREAMING_DYN_ALLOCATION_MIN_EXECUTORS = (new ConfigBuilder("spark.streaming.dynamicAllocation.minExecutors")).version("3.0.0").intConf().checkValue((JFunction1.mcZI.sp)(x$1) -> x$1 > 0, "The min executor number of streaming dynamic allocation must be positive.").createOptional();
   private static final ConfigEntry STREAMING_DYN_ALLOCATION_MAX_EXECUTORS = (new ConfigBuilder("spark.streaming.dynamicAllocation.maxExecutors")).version("3.0.0").intConf().checkValue((JFunction1.mcZI.sp)(x$2) -> x$2 > 0, "The max executor number of streaming dynamic allocation must be positive.").createWithDefault(BoxesRunTime.boxToInteger(Integer.MAX_VALUE));
   private static final ConfigEntry STREAMING_DYN_ALLOCATION_SCALING_INTERVAL;
   private static final ConfigEntry STREAMING_DYN_ALLOCATION_SCALING_UP_RATIO;
   private static final ConfigEntry STREAMING_DYN_ALLOCATION_SCALING_DOWN_RATIO;

   static {
      STREAMING_DYN_ALLOCATION_SCALING_INTERVAL = (new ConfigBuilder("spark.streaming.dynamicAllocation.scalingInterval")).version("3.0.0").timeConf(TimeUnit.SECONDS).checkValue((JFunction1.mcZJ.sp)(x$3) -> x$3 > 0L, "The scaling interval of streaming dynamic allocation must be positive.").createWithDefault(BoxesRunTime.boxToLong(60L));
      STREAMING_DYN_ALLOCATION_SCALING_UP_RATIO = (new ConfigBuilder("spark.streaming.dynamicAllocation.scalingUpRatio")).version("3.0.0").doubleConf().checkValue((JFunction1.mcZD.sp)(x$4) -> x$4 > (double)0, "The scaling up ratio of streaming dynamic allocation must be positive.").createWithDefault(BoxesRunTime.boxToDouble(0.9));
      STREAMING_DYN_ALLOCATION_SCALING_DOWN_RATIO = (new ConfigBuilder("spark.streaming.dynamicAllocation.scalingDownRatio")).version("3.0.0").doubleConf().checkValue((JFunction1.mcZD.sp)(x$5) -> x$5 > (double)0, "The scaling down ratio of streaming dynamic allocation must be positive.").createWithDefault(BoxesRunTime.boxToDouble(0.3));
   }

   public ConfigEntry STREAMING_DYN_ALLOCATION_ENABLED() {
      return STREAMING_DYN_ALLOCATION_ENABLED;
   }

   public ConfigEntry STREAMING_DYN_ALLOCATION_TESTING() {
      return STREAMING_DYN_ALLOCATION_TESTING;
   }

   public OptionalConfigEntry STREAMING_DYN_ALLOCATION_MIN_EXECUTORS() {
      return STREAMING_DYN_ALLOCATION_MIN_EXECUTORS;
   }

   public ConfigEntry STREAMING_DYN_ALLOCATION_MAX_EXECUTORS() {
      return STREAMING_DYN_ALLOCATION_MAX_EXECUTORS;
   }

   public ConfigEntry STREAMING_DYN_ALLOCATION_SCALING_INTERVAL() {
      return STREAMING_DYN_ALLOCATION_SCALING_INTERVAL;
   }

   public ConfigEntry STREAMING_DYN_ALLOCATION_SCALING_UP_RATIO() {
      return STREAMING_DYN_ALLOCATION_SCALING_UP_RATIO;
   }

   public ConfigEntry STREAMING_DYN_ALLOCATION_SCALING_DOWN_RATIO() {
      return STREAMING_DYN_ALLOCATION_SCALING_DOWN_RATIO;
   }

   private Streaming$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
