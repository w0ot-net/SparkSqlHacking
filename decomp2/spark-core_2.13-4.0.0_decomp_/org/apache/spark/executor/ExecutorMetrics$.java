package org.apache.spark.executor;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.memory.MemoryManager;
import org.apache.spark.metrics.ExecutorMetricType;
import org.apache.spark.metrics.ExecutorMetricType$;
import scala.Product;
import scala.Array.;
import scala.runtime.BoxedUnit;
import scala.runtime.IntRef;
import scala.runtime.ModuleSerializationProxy;

public final class ExecutorMetrics$ implements Serializable {
   public static final ExecutorMetrics$ MODULE$ = new ExecutorMetrics$();

   public long[] getCurrentMetrics(final MemoryManager memoryManager) {
      long[] currentMetrics = new long[ExecutorMetricType$.MODULE$.numMetrics()];
      IntRef offset = IntRef.create(0);
      ExecutorMetricType$.MODULE$.metricGetters().foreach((metricType) -> {
         $anonfun$getCurrentMetrics$1(memoryManager, currentMetrics, offset, metricType);
         return BoxedUnit.UNIT;
      });
      return currentMetrics;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ExecutorMetrics$.class);
   }

   // $FF: synthetic method
   public static final void $anonfun$getCurrentMetrics$1(final MemoryManager memoryManager$1, final long[] currentMetrics$1, final IntRef offset$1, final Product metricType) {
      long[] metricValues = ((ExecutorMetricType)metricType).getMetricValues(memoryManager$1);
      .MODULE$.copy(metricValues, 0, currentMetrics$1, offset$1.elem, metricValues.length);
      offset$1.elem += metricValues.length;
   }

   private ExecutorMetrics$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
