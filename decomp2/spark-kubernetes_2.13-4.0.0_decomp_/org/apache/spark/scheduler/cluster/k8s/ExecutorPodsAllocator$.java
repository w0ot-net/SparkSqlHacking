package org.apache.spark.scheduler.cluster.k8s;

import java.lang.invoke.SerializedLambda;
import scala.Tuple2;
import scala.collection.IterableOnce;
import scala.collection.IterableOps;
import scala.collection.immutable.Seq;
import scala.runtime.BoxesRunTime;

public final class ExecutorPodsAllocator$ {
   public static final ExecutorPodsAllocator$ MODULE$ = new ExecutorPodsAllocator$();

   public Seq splitSlots(final Seq consumers, final int slots) {
      int d = slots / consumers.size();
      int r = slots % consumers.size();
      return (Seq)((IterableOps)((IterableOps)consumers.take(r)).map((x$13) -> new Tuple2(x$13, BoxesRunTime.boxToInteger(d + 1)))).$plus$plus((IterableOnce)((IterableOps)consumers.takeRight(consumers.size() - r)).map((x$14) -> new Tuple2(x$14, BoxesRunTime.boxToInteger(d))));
   }

   private ExecutorPodsAllocator$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
