package org.apache.spark.status.protobuf;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.executor.ExecutorMetrics;
import org.apache.spark.metrics.ExecutorMetricType$;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.reflect.ClassTag.;
import scala.runtime.BoxesRunTime;

public final class ExecutorMetricsSerializer$ {
   public static final ExecutorMetricsSerializer$ MODULE$ = new ExecutorMetricsSerializer$();

   public StoreTypes.ExecutorMetrics serialize(final ExecutorMetrics e) {
      StoreTypes.ExecutorMetrics.Builder builder = StoreTypes.ExecutorMetrics.newBuilder();
      ExecutorMetricType$.MODULE$.metricToOffset().foreach((x0$1) -> {
         if (x0$1 != null) {
            String metric = (String)x0$1._1();
            return builder.putMetrics(metric, e.getMetricValue(metric));
         } else {
            throw new MatchError(x0$1);
         }
      });
      return builder.build();
   }

   public ExecutorMetrics deserialize(final StoreTypes.ExecutorMetrics binary) {
      long[] array = (long[])((IterableOnceOps)ExecutorMetricType$.MODULE$.metricToOffset().map((x0$1) -> BoxesRunTime.boxToLong($anonfun$deserialize$1(binary, x0$1)))).toArray(.MODULE$.Long());
      return new ExecutorMetrics(array);
   }

   // $FF: synthetic method
   public static final long $anonfun$deserialize$1(final StoreTypes.ExecutorMetrics binary$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         String name = (String)x0$1._1();
         return binary$1.getMetricsOrDefault(name, 0L);
      } else {
         throw new MatchError(x0$1);
      }
   }

   private ExecutorMetricsSerializer$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
