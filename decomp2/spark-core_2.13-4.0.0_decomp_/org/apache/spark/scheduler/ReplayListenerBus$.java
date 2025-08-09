package org.apache.spark.scheduler;

import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.runtime.BoxesRunTime;

public final class ReplayListenerBus$ {
   public static final ReplayListenerBus$ MODULE$ = new ReplayListenerBus$();
   private static final Function1 SELECT_ALL_FILTER = (eventString) -> BoxesRunTime.boxToBoolean($anonfun$SELECT_ALL_FILTER$1(eventString));

   public Function1 SELECT_ALL_FILTER() {
      return SELECT_ALL_FILTER;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$SELECT_ALL_FILTER$1(final String eventString) {
      return true;
   }

   private ReplayListenerBus$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
