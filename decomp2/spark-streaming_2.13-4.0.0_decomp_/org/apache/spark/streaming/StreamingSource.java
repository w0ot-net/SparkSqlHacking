package org.apache.spark.streaming;

import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.metrics.source.Source;
import org.apache.spark.streaming.ui.BatchUIData;
import org.apache.spark.streaming.ui.StreamingJobProgressListener;
import scala.Function1;
import scala.Option;
import scala.Option.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005Y4Qa\u0003\u0007\u0001\u0019QA\u0001b\t\u0001\u0003\u0002\u0003\u0006I!\n\u0005\u0006S\u0001!\tA\u000b\u0005\b[\u0001\u0011\r\u0011\"\u0011/\u0011\u0019A\u0004\u0001)A\u0005_!9\u0011\b\u0001b\u0001\n\u0003R\u0004B\u0002$\u0001A\u0003%1\bC\u0004H\u0001\t\u0007I\u0011\u0002%\t\r=\u0003\u0001\u0015!\u0003J\u0011\u0015\u0001\u0006\u0001\"\u0003R\u0011\u0015Q\u0007\u0001\"\u0003l\u0005=\u0019FO]3b[&twmU8ve\u000e,'BA\u0007\u000f\u0003%\u0019HO]3b[&twM\u0003\u0002\u0010!\u0005)1\u000f]1sW*\u0011\u0011CE\u0001\u0007CB\f7\r[3\u000b\u0003M\t1a\u001c:h'\r\u0001Qc\u0007\t\u0003-ei\u0011a\u0006\u0006\u00021\u0005)1oY1mC&\u0011!d\u0006\u0002\u0007\u0003:L(+\u001a4\u0011\u0005q\tS\"A\u000f\u000b\u0005yy\u0012AB:pkJ\u001cWM\u0003\u0002!\u001d\u00059Q.\u001a;sS\u000e\u001c\u0018B\u0001\u0012\u001e\u0005\u0019\u0019v.\u001e:dK\u0006\u00191o]2\u0004\u0001A\u0011aeJ\u0007\u0002\u0019%\u0011\u0001\u0006\u0004\u0002\u0011'R\u0014X-Y7j]\u001e\u001cuN\u001c;fqR\fa\u0001P5oSRtDCA\u0016-!\t1\u0003\u0001C\u0003$\u0005\u0001\u0007Q%\u0001\bnKR\u0014\u0018n\u0019*fO&\u001cHO]=\u0016\u0003=\u0002\"\u0001\r\u001c\u000e\u0003ER!\u0001\t\u001a\u000b\u0005M\"\u0014\u0001C2pI\u0006D\u0017\r\\3\u000b\u0003U\n1aY8n\u0013\t9\u0014G\u0001\bNKR\u0014\u0018n\u0019*fO&\u001cHO]=\u0002\u001f5,GO]5d%\u0016<\u0017n\u001d;ss\u0002\n!b]8ve\u000e,g*Y7f+\u0005Y\u0004C\u0001\u001fD\u001d\ti\u0014\t\u0005\u0002?/5\tqH\u0003\u0002AI\u00051AH]8pizJ!AQ\f\u0002\rA\u0013X\rZ3g\u0013\t!UI\u0001\u0004TiJLgn\u001a\u0006\u0003\u0005^\t1b]8ve\u000e,g*Y7fA\u0005\t2\u000f\u001e:fC6Lgn\u001a'jgR,g.\u001a:\u0016\u0003%\u0003\"AS'\u000e\u0003-S!\u0001\u0014\u0007\u0002\u0005UL\u0017B\u0001(L\u0005q\u0019FO]3b[&twMS8c!J|wM]3tg2K7\u000f^3oKJ\f!c\u001d;sK\u0006l\u0017N\\4MSN$XM\\3sA\u0005i!/Z4jgR,'oR1vO\u0016,\"AU0\u0015\tM3\u0006\f\u001b\t\u0003-QK!!V\f\u0003\tUs\u0017\u000e\u001e\u0005\u0006/&\u0001\raO\u0001\u0005]\u0006lW\rC\u0003Z\u0013\u0001\u0007!,A\u0001g!\u001112,S/\n\u0005q;\"!\u0003$v]\u000e$\u0018n\u001c82!\tqv\f\u0004\u0001\u0005\u000b\u0001L!\u0019A1\u0003\u0003Q\u000b\"AY3\u0011\u0005Y\u0019\u0017B\u00013\u0018\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"A\u00064\n\u0005\u001d<\"aA!os\")\u0011.\u0003a\u0001;\u0006aA-\u001a4bk2$h+\u00197vK\u00069\"/Z4jgR,'oR1vO\u0016<\u0016\u000e\u001e5PaRLwN\\\u000b\u0003YR$BaU7ok\")qK\u0003a\u0001w!)\u0011L\u0003a\u0001_B!acW%q!\r1\u0012o]\u0005\u0003e^\u0011aa\u00149uS>t\u0007C\u00010u\t\u0015\u0001'B1\u0001b\u0011\u0015I'\u00021\u0001t\u0001"
)
public class StreamingSource implements Source {
   private final MetricRegistry metricRegistry = new MetricRegistry();
   private final String sourceName;
   private final StreamingJobProgressListener org$apache$spark$streaming$StreamingSource$$streamingListener;

   public MetricRegistry metricRegistry() {
      return this.metricRegistry;
   }

   public String sourceName() {
      return this.sourceName;
   }

   public StreamingJobProgressListener org$apache$spark$streaming$StreamingSource$$streamingListener() {
      return this.org$apache$spark$streaming$StreamingSource$$streamingListener;
   }

   private void registerGauge(final String name, final Function1 f, final Object defaultValue) {
      this.registerGaugeWithOption(name, (l) -> .MODULE$.apply(f.apply(this.org$apache$spark$streaming$StreamingSource$$streamingListener())), defaultValue);
   }

   private void registerGaugeWithOption(final String name, final Function1 f, final Object defaultValue) {
      this.metricRegistry().register(MetricRegistry.name("streaming", new String[]{name}), new Gauge(f, defaultValue) {
         // $FF: synthetic field
         private final StreamingSource $outer;
         private final Function1 f$2;
         private final Object defaultValue$1;

         public Object getValue() {
            return ((Option)this.f$2.apply(this.$outer.org$apache$spark$streaming$StreamingSource$$streamingListener())).getOrElse(() -> this.defaultValue$1);
         }

         public {
            if (StreamingSource.this == null) {
               throw null;
            } else {
               this.$outer = StreamingSource.this;
               this.f$2 = f$2;
               this.defaultValue$1 = defaultValue$1;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      });
   }

   // $FF: synthetic method
   public static final int $anonfun$new$1(final StreamingJobProgressListener x$1) {
      return x$1.numReceivers();
   }

   // $FF: synthetic method
   public static final long $anonfun$new$2(final StreamingJobProgressListener x$2) {
      return x$2.numTotalCompletedBatches();
   }

   // $FF: synthetic method
   public static final long $anonfun$new$3(final StreamingJobProgressListener x$3) {
      return x$3.numTotalReceivedRecords();
   }

   // $FF: synthetic method
   public static final long $anonfun$new$4(final StreamingJobProgressListener x$4) {
      return x$4.numTotalProcessedRecords();
   }

   // $FF: synthetic method
   public static final long $anonfun$new$5(final StreamingJobProgressListener x$5) {
      return x$5.numUnprocessedBatches();
   }

   // $FF: synthetic method
   public static final int $anonfun$new$6(final StreamingJobProgressListener x$6) {
      return x$6.waitingBatches().size();
   }

   // $FF: synthetic method
   public static final int $anonfun$new$7(final StreamingJobProgressListener x$7) {
      return x$7.runningBatches().size();
   }

   // $FF: synthetic method
   public static final int $anonfun$new$8(final StreamingJobProgressListener x$8) {
      return x$8.retainedCompletedBatches().size();
   }

   // $FF: synthetic method
   public static final long $anonfun$new$10(final BatchUIData x$10) {
      return x$10.submissionTime();
   }

   // $FF: synthetic method
   public static final long $anonfun$new$22(final BatchUIData x$22) {
      return x$22.submissionTime();
   }

   // $FF: synthetic method
   public static final long $anonfun$new$27(final StreamingJobProgressListener x$27) {
      return BoxesRunTime.unboxToLong(x$27.lastReceivedBatchRecords().values().sum(scala.math.Numeric.LongIsIntegral..MODULE$));
   }

   public StreamingSource(final StreamingContext ssc) {
      this.sourceName = scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("%s.StreamingMetrics"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{ssc.sparkContext().appName()}));
      this.org$apache$spark$streaming$StreamingSource$$streamingListener = ssc.progressListener();
      this.registerGauge("receivers", (x$1) -> BoxesRunTime.boxToInteger($anonfun$new$1(x$1)), BoxesRunTime.boxToInteger(0));
      this.registerGauge("totalCompletedBatches", (x$2) -> BoxesRunTime.boxToLong($anonfun$new$2(x$2)), BoxesRunTime.boxToLong(0L));
      this.registerGauge("totalReceivedRecords", (x$3) -> BoxesRunTime.boxToLong($anonfun$new$3(x$3)), BoxesRunTime.boxToLong(0L));
      this.registerGauge("totalProcessedRecords", (x$4) -> BoxesRunTime.boxToLong($anonfun$new$4(x$4)), BoxesRunTime.boxToLong(0L));
      this.registerGauge("unprocessedBatches", (x$5) -> BoxesRunTime.boxToLong($anonfun$new$5(x$5)), BoxesRunTime.boxToLong(0L));
      this.registerGauge("waitingBatches", (x$6) -> BoxesRunTime.boxToInteger($anonfun$new$6(x$6)), BoxesRunTime.boxToLong(0L));
      this.registerGauge("runningBatches", (x$7) -> BoxesRunTime.boxToInteger($anonfun$new$7(x$7)), BoxesRunTime.boxToLong(0L));
      this.registerGauge("retainedCompletedBatches", (x$8) -> BoxesRunTime.boxToInteger($anonfun$new$8(x$8)), BoxesRunTime.boxToLong(0L));
      this.registerGaugeWithOption("lastCompletedBatch_submissionTime", (x$9) -> x$9.lastCompletedBatch().map((x$10) -> BoxesRunTime.boxToLong($anonfun$new$10(x$10))), BoxesRunTime.boxToLong(-1L));
      this.registerGaugeWithOption("lastCompletedBatch_processingStartTime", (x$11) -> x$11.lastCompletedBatch().flatMap((x$12) -> x$12.processingStartTime()), BoxesRunTime.boxToLong(-1L));
      this.registerGaugeWithOption("lastCompletedBatch_processingEndTime", (x$13) -> x$13.lastCompletedBatch().flatMap((x$14) -> x$14.processingEndTime()), BoxesRunTime.boxToLong(-1L));
      this.registerGaugeWithOption("lastCompletedBatch_processingDelay", (x$15) -> x$15.lastCompletedBatch().flatMap((x$16) -> x$16.processingDelay()), BoxesRunTime.boxToLong(-1L));
      this.registerGaugeWithOption("lastCompletedBatch_schedulingDelay", (x$17) -> x$17.lastCompletedBatch().flatMap((x$18) -> x$18.schedulingDelay()), BoxesRunTime.boxToLong(-1L));
      this.registerGaugeWithOption("lastCompletedBatch_totalDelay", (x$19) -> x$19.lastCompletedBatch().flatMap((x$20) -> x$20.totalDelay()), BoxesRunTime.boxToLong(-1L));
      this.registerGaugeWithOption("lastReceivedBatch_submissionTime", (x$21) -> x$21.lastReceivedBatch().map((x$22) -> BoxesRunTime.boxToLong($anonfun$new$22(x$22))), BoxesRunTime.boxToLong(-1L));
      this.registerGaugeWithOption("lastReceivedBatch_processingStartTime", (x$23) -> x$23.lastReceivedBatch().flatMap((x$24) -> x$24.processingStartTime()), BoxesRunTime.boxToLong(-1L));
      this.registerGaugeWithOption("lastReceivedBatch_processingEndTime", (x$25) -> x$25.lastReceivedBatch().flatMap((x$26) -> x$26.processingEndTime()), BoxesRunTime.boxToLong(-1L));
      this.registerGauge("lastReceivedBatch_records", (x$27) -> BoxesRunTime.boxToLong($anonfun$new$27(x$27)), BoxesRunTime.boxToLong(0L));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
