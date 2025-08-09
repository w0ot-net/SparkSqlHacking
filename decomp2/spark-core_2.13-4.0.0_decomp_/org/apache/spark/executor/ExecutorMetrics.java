package org.apache.spark.executor;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.concurrent.atomic.AtomicLongArray;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.memory.MemoryManager;
import org.apache.spark.metrics.ExecutorMetricType$;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;
import scala.runtime.BooleanRef;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichInt.;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u0005-a\u0001B\b\u0011\u0001eAa\u0001\f\u0001\u0005\u0002Ii\u0003b\u0002\u0019\u0001\u0005\u0004%I!\r\u0005\u0007q\u0001\u0001\u000b\u0011\u0002\u001a\t\u000be\u0002A\u0011\u0001\u001e\t\u000b\u0015\u0003A\u0011\u0001$\t\r1\u0002A\u0011\u0001\nK\u0011\u0019a\u0003\u0001\"\u0001\u0013\u0019\"1A\u0006\u0001C\u0001%iCa\u0001\u0019\u0001\u0005\u0002I\twA\u00026\u0011\u0011\u0003\u00112N\u0002\u0004\u0010!!\u0005!\u0003\u001c\u0005\u0006Y-!\tA\u001d\u0005\u0006g.!\t\u0001\u001e\u0005\b{.\t\t\u0011\"\u0003\u007f\u0005=)\u00050Z2vi>\u0014X*\u001a;sS\u000e\u001c(BA\t\u0013\u0003!)\u00070Z2vi>\u0014(BA\n\u0015\u0003\u0015\u0019\b/\u0019:l\u0015\t)b#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002/\u0005\u0019qN]4\u0004\u0001M\u0019\u0001A\u0007\u0011\u0011\u0005mqR\"\u0001\u000f\u000b\u0003u\tQa]2bY\u0006L!a\b\u000f\u0003\r\u0005s\u0017PU3g!\t\t\u0013F\u0004\u0002#O9\u00111EJ\u0007\u0002I)\u0011Q\u0005G\u0001\u0007yI|w\u000e\u001e \n\u0003uI!\u0001\u000b\u000f\u0002\u000fA\f7m[1hK&\u0011!f\u000b\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003Qq\ta\u0001P5oSRtD#\u0001\u0018\u0011\u0005=\u0002Q\"\u0001\t\u0002\u000f5,GO]5dgV\t!\u0007E\u0002\u001cgUJ!\u0001\u000e\u000f\u0003\u000b\u0005\u0013(/Y=\u0011\u0005m1\u0014BA\u001c\u001d\u0005\u0011auN\\4\u0002\u00115,GO]5dg\u0002\nabZ3u\u001b\u0016$(/[2WC2,X\r\u0006\u00026w!)A\b\u0002a\u0001{\u0005QQ.\u001a;sS\u000et\u0015-\\3\u0011\u0005y\u0012eBA A!\t\u0019C$\u0003\u0002B9\u00051\u0001K]3eK\u001aL!a\u0011#\u0003\rM#(/\u001b8h\u0015\t\tE$A\u0003jgN+G\u000fF\u0001H!\tY\u0002*\u0003\u0002J9\t9!i\\8mK\u0006tGC\u0001\u0018L\u0011\u0015\u0001d\u00011\u00013)\tqS\nC\u00031\u000f\u0001\u0007a\n\u0005\u0002P16\t\u0001K\u0003\u0002R%\u00061\u0011\r^8nS\u000eT!a\u0015+\u0002\u0015\r|gnY;se\u0016tGO\u0003\u0002V-\u0006!Q\u000f^5m\u0015\u00059\u0016\u0001\u00026bm\u0006L!!\u0017)\u0003\u001f\u0005#x.\\5d\u0019>tw-\u0011:sCf$\"AL.\t\u000bqC\u0001\u0019A/\u0002\u001f\u0015DXmY;u_JlU\r\u001e:jGN\u0004BA\u00100>k%\u0011q\f\u0012\u0002\u0004\u001b\u0006\u0004\u0018AG2p[B\f'/Z!oIV\u0003H-\u0019;f!\u0016\f7NV1mk\u0016\u001cHCA$c\u0011\u0015a\u0016\u00021\u0001/Q\t\u0001A\r\u0005\u0002fQ6\taM\u0003\u0002h%\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005%4'\u0001\u0004#fm\u0016dw\u000e]3s\u0003BL\u0017aD#yK\u000e,Ho\u001c:NKR\u0014\u0018nY:\u0011\u0005=Z1cA\u0006\u001b[B\u0011a.]\u0007\u0002_*\u0011\u0001OV\u0001\u0003S>L!AK8\u0015\u0003-\f\u0011cZ3u\u0007V\u0014(/\u001a8u\u001b\u0016$(/[2t)\t\u0011T\u000fC\u0003w\u001b\u0001\u0007q/A\u0007nK6|'/_'b]\u0006<WM\u001d\t\u0003qnl\u0011!\u001f\u0006\u0003uJ\ta!\\3n_JL\u0018B\u0001?z\u00055iU-\\8ss6\u000bg.Y4fe\u0006aqO]5uKJ+\u0007\u000f\\1dKR\tq\u0010\u0005\u0003\u0002\u0002\u0005\u001dQBAA\u0002\u0015\r\t)AV\u0001\u0005Y\u0006tw-\u0003\u0003\u0002\n\u0005\r!AB(cU\u0016\u001cG\u000f"
)
public class ExecutorMetrics implements Serializable {
   private final long[] metrics;

   public static long[] getCurrentMetrics(final MemoryManager memoryManager) {
      return ExecutorMetrics$.MODULE$.getCurrentMetrics(memoryManager);
   }

   private long[] metrics() {
      return this.metrics;
   }

   public long getMetricValue(final String metricName) {
      return this.metrics()[BoxesRunTime.unboxToInt(ExecutorMetricType$.MODULE$.metricToOffset().apply(metricName))];
   }

   public boolean isSet() {
      return this.metrics()[0] > -1L;
   }

   public boolean compareAndUpdatePeakValues(final ExecutorMetrics executorMetrics) {
      BooleanRef updated = BooleanRef.create(false);
      .MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), ExecutorMetricType$.MODULE$.numMetrics()).foreach$mVc$sp((JFunction1.mcVI.sp)(idx) -> {
         if (executorMetrics.metrics()[idx] > this.metrics()[idx]) {
            updated.elem = true;
            this.metrics()[idx] = executorMetrics.metrics()[idx];
         }
      });
      return updated.elem;
   }

   // $FF: synthetic method
   public static final void $anonfun$new$1(final ExecutorMetrics $this, final AtomicLongArray metrics$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         int i = x0$1._2$mcI$sp();
         $this.metrics()[i] = metrics$1.get(i);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$new$2(final ExecutorMetrics $this, final Map executorMetrics$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         String name = (String)x0$1._1();
         int idx = x0$1._2$mcI$sp();
         $this.metrics()[idx] = BoxesRunTime.unboxToLong(executorMetrics$1.getOrElse(name, (JFunction0.mcJ.sp)() -> 0L));
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   public ExecutorMetrics() {
      this.metrics = new long[ExecutorMetricType$.MODULE$.numMetrics()];
      this.metrics()[0] = -1L;
   }

   public ExecutorMetrics(final long[] metrics) {
      this();
      scala.Array..MODULE$.copy(metrics, 0, this.metrics(), 0, Math.min(metrics.length, this.metrics().length));
   }

   public ExecutorMetrics(final AtomicLongArray metrics) {
      this();
      ExecutorMetricType$.MODULE$.metricToOffset().foreach((x0$1) -> {
         $anonfun$new$1(this, metrics, x0$1);
         return BoxedUnit.UNIT;
      });
   }

   public ExecutorMetrics(final Map executorMetrics) {
      this();
      ExecutorMetricType$.MODULE$.metricToOffset().foreach((x0$1) -> {
         $anonfun$new$2(this, executorMetrics, x0$1);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
