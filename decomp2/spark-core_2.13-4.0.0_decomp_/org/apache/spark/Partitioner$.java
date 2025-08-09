package org.apache.spark;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.rdd.RDD;
import scala.Option;
import scala.Some;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.;
import scala.collection.immutable.Seq;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class Partitioner$ implements Serializable {
   public static final Partitioner$ MODULE$ = new Partitioner$();

   public Partitioner defaultPartitioner(final RDD rdd, final Seq others) {
      Seq rdds = (Seq)(new .colon.colon(rdd, scala.collection.immutable.Nil..MODULE$)).$plus$plus(others);
      Seq hasPartitioner = (Seq)rdds.filter((x$1) -> BoxesRunTime.boxToBoolean($anonfun$defaultPartitioner$1(x$1)));
      Option hasMaxPartitioner = (Option)(hasPartitioner.nonEmpty() ? new Some(hasPartitioner.maxBy((x$3) -> BoxesRunTime.boxToInteger($anonfun$defaultPartitioner$3(x$3)), scala.math.Ordering.Int..MODULE$)) : scala.None..MODULE$);
      int defaultNumPartitions = rdd.context().conf().contains(org.apache.spark.internal.config.package$.MODULE$.DEFAULT_PARALLELISM().key()) ? rdd.context().defaultParallelism() : BoxesRunTime.unboxToInt(((IterableOnceOps)rdds.map((x$4) -> BoxesRunTime.boxToInteger($anonfun$defaultPartitioner$4(x$4)))).max(scala.math.Ordering.Int..MODULE$));
      return (Partitioner)(!hasMaxPartitioner.nonEmpty() || !this.isEligiblePartitioner((RDD)hasMaxPartitioner.get(), rdds) && defaultNumPartitions > ((RDD)hasMaxPartitioner.get()).getNumPartitions() ? new HashPartitioner(defaultNumPartitions) : (Partitioner)((RDD)hasMaxPartitioner.get()).partitioner().get());
   }

   private boolean isEligiblePartitioner(final RDD hasMaxPartitioner, final Seq rdds) {
      int maxPartitions = BoxesRunTime.unboxToInt(((IterableOnceOps)rdds.map((x$5) -> BoxesRunTime.boxToInteger($anonfun$isEligiblePartitioner$1(x$5)))).max(scala.math.Ordering.Int..MODULE$));
      return scala.math.package..MODULE$.log10((double)maxPartitions) - scala.math.package..MODULE$.log10((double)hasMaxPartitioner.getNumPartitions()) < (double)1;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Partitioner$.class);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$defaultPartitioner$2(final Partitioner x$2) {
      return x$2.numPartitions() > 0;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$defaultPartitioner$1(final RDD x$1) {
      return x$1.partitioner().exists((x$2) -> BoxesRunTime.boxToBoolean($anonfun$defaultPartitioner$2(x$2)));
   }

   // $FF: synthetic method
   public static final int $anonfun$defaultPartitioner$3(final RDD x$3) {
      return x$3.partitions().length;
   }

   // $FF: synthetic method
   public static final int $anonfun$defaultPartitioner$4(final RDD x$4) {
      return x$4.partitions().length;
   }

   // $FF: synthetic method
   public static final int $anonfun$isEligiblePartitioner$1(final RDD x$5) {
      return x$5.partitions().length;
   }

   private Partitioner$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
