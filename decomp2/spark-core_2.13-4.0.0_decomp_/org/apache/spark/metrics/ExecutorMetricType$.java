package org.apache.spark.metrics;

import java.lang.invoke.SerializedLambda;
import scala.MatchError;
import scala.Product;
import scala.Tuple2;
import scala.collection.immutable.IndexedSeq;
import scala.collection.mutable.LinkedHashMap;
import scala.package.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;

public final class ExecutorMetricType$ {
   public static final ExecutorMetricType$ MODULE$ = new ExecutorMetricType$();
   private static final IndexedSeq metricGetters;
   // $FF: synthetic field
   private static final Tuple2 x$6;
   private static final LinkedHashMap metricToOffset;
   private static final int numMetrics;

   static {
      metricGetters = (IndexedSeq).MODULE$.IndexedSeq().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Product[]{JVMHeapMemory$.MODULE$, JVMOffHeapMemory$.MODULE$, OnHeapExecutionMemory$.MODULE$, OffHeapExecutionMemory$.MODULE$, OnHeapStorageMemory$.MODULE$, OffHeapStorageMemory$.MODULE$, OnHeapUnifiedMemory$.MODULE$, OffHeapUnifiedMemory$.MODULE$, DirectPoolMemory$.MODULE$, MappedPoolMemory$.MODULE$, ProcessTreeMetrics$.MODULE$, GarbageCollectionMetrics$.MODULE$})));
      IntRef numberOfMetrics = IntRef.create(0);
      LinkedHashMap definedMetricsAndOffset = scala.collection.mutable.LinkedHashMap..MODULE$.empty();
      MODULE$.metricGetters().foreach((m) -> {
         $anonfun$x$6$1(definedMetricsAndOffset, numberOfMetrics, m);
         return BoxedUnit.UNIT;
      });
      Tuple2 var1 = new Tuple2(definedMetricsAndOffset, BoxesRunTime.boxToInteger(numberOfMetrics.elem));
      if (var1 != null) {
         LinkedHashMap metricToOffset = (LinkedHashMap)var1._1();
         int numMetrics = var1._2$mcI$sp();
         x$6 = new Tuple2(metricToOffset, BoxesRunTime.boxToInteger(numMetrics));
         metricToOffset = (LinkedHashMap)x$6._1();
         numMetrics = x$6._2$mcI$sp();
      } else {
         throw new MatchError(var1);
      }
   }

   public IndexedSeq metricGetters() {
      return metricGetters;
   }

   public LinkedHashMap metricToOffset() {
      return metricToOffset;
   }

   public int numMetrics() {
      return numMetrics;
   }

   // $FF: synthetic method
   public static final LinkedHashMap $anonfun$x$6$2(final LinkedHashMap definedMetricsAndOffset$1, final Product m$1, final IntRef numberOfMetrics$1, final int idx) {
      return (LinkedHashMap)definedMetricsAndOffset$1.$plus$eq(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(((ExecutorMetricType)m$1).names().apply(idx)), BoxesRunTime.boxToInteger(idx + numberOfMetrics$1.elem)));
   }

   // $FF: synthetic method
   public static final void $anonfun$x$6$1(final LinkedHashMap definedMetricsAndOffset$1, final IntRef numberOfMetrics$1, final Product m) {
      ((ExecutorMetricType)m).names().indices().foreach((idx) -> $anonfun$x$6$2(definedMetricsAndOffset$1, m, numberOfMetrics$1, BoxesRunTime.unboxToInt(idx)));
      numberOfMetrics$1.elem += ((ExecutorMetricType)m).names().length();
   }

   private ExecutorMetricType$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
