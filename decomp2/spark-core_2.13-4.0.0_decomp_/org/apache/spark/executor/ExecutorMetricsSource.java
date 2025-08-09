package org.apache.spark.executor;

import com.codahale.metrics.Gauge;
import com.codahale.metrics.Metric;
import com.codahale.metrics.MetricRegistry;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.metrics.ExecutorMetricType$;
import org.apache.spark.metrics.MetricsSystem;
import org.apache.spark.metrics.source.Source;
import scala.MatchError;
import scala.collection.immutable.IndexedSeq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichInt.;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005U4Qa\u0004\t\u0001%aAQa\n\u0001\u0005\u0002%Bq\u0001\f\u0001C\u0002\u0013\u0005S\u0006\u0003\u00048\u0001\u0001\u0006IA\f\u0005\bq\u0001\u0011\r\u0011\"\u0011:\u0011\u0019\u0011\u0005\u0001)A\u0005u!91\t\u0001a\u0001\n\u0003!\u0005bB&\u0001\u0001\u0004%\t\u0001\u0014\u0005\u0007%\u0002\u0001\u000b\u0015B#\t\u000b]\u0003A\u0011\u0001-\u0007\tm\u0003A\u0001\u0018\u0005\tG*\u0011\t\u0011)A\u0005I\")qE\u0003C\u0001O\")1N\u0003C\u0001Y\")Q\u000e\u0001C\u0001]\n)R\t_3dkR|'/T3ue&\u001c7oU8ve\u000e,'BA\t\u0013\u0003!)\u00070Z2vi>\u0014(BA\n\u0015\u0003\u0015\u0019\b/\u0019:l\u0015\t)b#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002/\u0005\u0019qN]4\u0014\u0007\u0001Ir\u0004\u0005\u0002\u001b;5\t1DC\u0001\u001d\u0003\u0015\u00198-\u00197b\u0013\tq2D\u0001\u0004B]f\u0014VM\u001a\t\u0003A\u0015j\u0011!\t\u0006\u0003E\r\naa]8ve\u000e,'B\u0001\u0013\u0013\u0003\u001diW\r\u001e:jGNL!AJ\u0011\u0003\rM{WO]2f\u0003\u0019a\u0014N\\5u}\r\u0001A#\u0001\u0016\u0011\u0005-\u0002Q\"\u0001\t\u0002\u001d5,GO]5d%\u0016<\u0017n\u001d;ssV\ta\u0006\u0005\u00020k5\t\u0001G\u0003\u0002%c)\u0011!gM\u0001\tG>$\u0017\r[1mK*\tA'A\u0002d_6L!A\u000e\u0019\u0003\u001d5+GO]5d%\u0016<\u0017n\u001d;ss\u0006yQ.\u001a;sS\u000e\u0014VmZ5tiJL\b%\u0001\u0006t_V\u00148-\u001a(b[\u0016,\u0012A\u000f\t\u0003w\u0001k\u0011\u0001\u0010\u0006\u0003{y\nA\u0001\\1oO*\tq(\u0001\u0003kCZ\f\u0017BA!=\u0005\u0019\u0019FO]5oO\u0006Y1o\\;sG\u0016t\u0015-\\3!\u0003=iW\r\u001e:jGN\u001cf.\u00199tQ>$X#A#\u0011\u0007i1\u0005*\u0003\u0002H7\t)\u0011I\u001d:bsB\u0011!$S\u0005\u0003\u0015n\u0011A\u0001T8oO\u0006\u0019R.\u001a;sS\u000e\u001c8K\\1qg\"|Go\u0018\u0013fcR\u0011Q\n\u0015\t\u000359K!aT\u000e\u0003\tUs\u0017\u000e\u001e\u0005\b#\u001e\t\t\u00111\u0001F\u0003\rAH%M\u0001\u0011[\u0016$(/[2t':\f\u0007o\u001d5pi\u0002B#\u0001\u0003+\u0011\u0005i)\u0016B\u0001,\u001c\u0005!1x\u000e\\1uS2,\u0017!F;qI\u0006$X-T3ue&\u001c7o\u00158baNDw\u000e\u001e\u000b\u0003\u001bfCQAW\u0005A\u0002\u0015\u000ba\"\\3ue&\u001c7/\u00169eCR,7OA\nFq\u0016\u001cW\u000f^8s\u001b\u0016$(/[2HCV<WmE\u0002\u000b;\u0002\u0004\"a\u000f0\n\u0005}c$AB(cU\u0016\u001cG\u000fE\u00020C\"K!A\u0019\u0019\u0003\u000b\u001d\u000bWoZ3\u0002\u0007%$\u0007\u0010\u0005\u0002\u001bK&\u0011am\u0007\u0002\u0004\u0013:$HC\u00015k!\tI'\"D\u0001\u0001\u0011\u0015\u0019G\u00021\u0001e\u0003!9W\r\u001e,bYV,G#\u0001%\u0002\u0011I,w-[:uKJ$\"!T8\t\u000bAt\u0001\u0019A9\u0002\u001b5,GO]5dgNK8\u000f^3n!\t\u00118/D\u0001$\u0013\t!8EA\u0007NKR\u0014\u0018nY:TsN$X-\u001c"
)
public class ExecutorMetricsSource implements Source {
   private final MetricRegistry metricRegistry = new MetricRegistry();
   private final String sourceName = "ExecutorMetrics";
   private volatile long[] metricsSnapshot;

   public MetricRegistry metricRegistry() {
      return this.metricRegistry;
   }

   public String sourceName() {
      return this.sourceName;
   }

   public long[] metricsSnapshot() {
      return this.metricsSnapshot;
   }

   public void metricsSnapshot_$eq(final long[] x$1) {
      this.metricsSnapshot = x$1;
   }

   public void updateMetricsSnapshot(final long[] metricsUpdates) {
      this.metricsSnapshot_$eq(metricsUpdates);
   }

   public void register(final MetricsSystem metricsSystem) {
      IndexedSeq gauges = .MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), ExecutorMetricType$.MODULE$.numMetrics()).map((idx) -> $anonfun$register$1(this, BoxesRunTime.unboxToInt(idx)));
      ExecutorMetricType$.MODULE$.metricToOffset().foreach((x0$1) -> {
         if (x0$1 != null) {
            String name = (String)x0$1._1();
            int idx = x0$1._2$mcI$sp();
            return (ExecutorMetricGauge)this.metricRegistry().register(MetricRegistry.name(name, new String[0]), (Metric)gauges.apply(idx));
         } else {
            throw new MatchError(x0$1);
         }
      });
      metricsSystem.registerSource(this);
   }

   // $FF: synthetic method
   public static final ExecutorMetricGauge $anonfun$register$1(final ExecutorMetricsSource $this, final int idx) {
      return $this.new ExecutorMetricGauge(idx);
   }

   public ExecutorMetricsSource() {
      this.metricsSnapshot = (long[])scala.Array..MODULE$.fill(ExecutorMetricType$.MODULE$.numMetrics(), (JFunction0.mcJ.sp)() -> 0L, scala.reflect.ClassTag..MODULE$.Long());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   private class ExecutorMetricGauge implements Gauge {
      private final int idx;
      // $FF: synthetic field
      public final ExecutorMetricsSource $outer;

      public long getValue() {
         return this.org$apache$spark$executor$ExecutorMetricsSource$ExecutorMetricGauge$$$outer().metricsSnapshot()[this.idx];
      }

      // $FF: synthetic method
      public ExecutorMetricsSource org$apache$spark$executor$ExecutorMetricsSource$ExecutorMetricGauge$$$outer() {
         return this.$outer;
      }

      public ExecutorMetricGauge(final int idx) {
         this.idx = idx;
         if (ExecutorMetricsSource.this == null) {
            throw null;
         } else {
            this.$outer = ExecutorMetricsSource.this;
            super();
         }
      }
   }
}
