package org.apache.spark.status;

import com.codahale.metrics.Counter;
import com.codahale.metrics.MetricRegistry;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.config.Status$;
import scala.Option;
import scala.Option.;
import scala.runtime.BoxesRunTime;

public final class AppStatusSource$ {
   public static final AppStatusSource$ MODULE$ = new AppStatusSource$();

   public Counter getCounter(final String prefix, final String name, final MetricRegistry metricRegistry) {
      return metricRegistry.counter(MetricRegistry.name(prefix, new String[]{name}));
   }

   public Option createSource(final SparkConf conf) {
      return .MODULE$.apply(conf.get(Status$.MODULE$.METRICS_APP_STATUS_SOURCE_ENABLED())).filter((x) -> BoxesRunTime.boxToBoolean($anonfun$createSource$1(BoxesRunTime.unboxToBoolean(x)))).map((x$1) -> $anonfun$createSource$2(BoxesRunTime.unboxToBoolean(x$1)));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$createSource$1(final boolean x) {
      return BoxesRunTime.unboxToBoolean(scala.Predef..MODULE$.identity(BoxesRunTime.boxToBoolean(x)));
   }

   // $FF: synthetic method
   public static final AppStatusSource $anonfun$createSource$2(final boolean x$1) {
      return new AppStatusSource();
   }

   private AppStatusSource$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
