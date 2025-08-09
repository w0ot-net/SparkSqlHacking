package org.apache.spark.rdd;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Arrays;
import java.util.Map;
import org.apache.spark.errors.SparkCoreErrors$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.partial.MeanEvaluator;
import org.apache.spark.partial.PartialResult;
import org.apache.spark.partial.SumEvaluator;
import org.apache.spark.util.StatCounter;
import org.apache.spark.util.StatCounter$;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.IterableOnce;
import scala.collection.Iterator;
import scala.collection.SeqOps;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.Seq;
import scala.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;
import scala.runtime.java8.JFunction2;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Eb\u0001B\n\u0015\u0001uA\u0001B\u000e\u0001\u0003\u0002\u0003\u0006Ia\u000e\u0005\u0006}\u0001!\ta\u0010\u0005\u0006\u0005\u0002!\ta\u0011\u0005\u0006\t\u0002!\t!\u0012\u0005\u0006\u0019\u0002!\ta\u0011\u0005\u0006\u001b\u0002!\ta\u0011\u0005\u0006\u001d\u0002!\ta\u0011\u0005\u0006\u001f\u0002!\ta\u0011\u0005\u0006!\u0002!\ta\u0011\u0005\u0006#\u0002!\ta\u0011\u0005\u00067\u0002!\ta\u0011\u0005\u0006;\u0002!\tA\u0018\u0005\b_\u0002\t\n\u0011\"\u0001q\u0011\u0015Q\b\u0001\"\u0001|\u0011\u001dq\b!%A\u0005\u0002ADaa \u0001\u0005\u0002\u0005\u0005\u0001BB@\u0001\t\u0003\tY\u0002C\u0005\u0002,\u0001\t\n\u0011\"\u0001\u0002.\t\u0011Bi\\;cY\u0016\u0014F\t\u0012$v]\u000e$\u0018n\u001c8t\u0015\t)b#A\u0002sI\u0012T!a\u0006\r\u0002\u000bM\u0004\u0018M]6\u000b\u0005eQ\u0012AB1qC\u000eDWMC\u0001\u001c\u0003\ry'oZ\u0002\u0001'\u0011\u0001a\u0004\n\u0016\u0011\u0005}\u0011S\"\u0001\u0011\u000b\u0003\u0005\nQa]2bY\u0006L!a\t\u0011\u0003\r\u0005s\u0017PU3g!\t)\u0003&D\u0001'\u0015\t9c#\u0001\u0005j]R,'O\\1m\u0013\tIcEA\u0004M_\u001e<\u0017N\\4\u0011\u0005-\u001adB\u0001\u00172\u001d\ti\u0003'D\u0001/\u0015\tyC$\u0001\u0004=e>|GOP\u0005\u0002C%\u0011!\u0007I\u0001\ba\u0006\u001c7.Y4f\u0013\t!TG\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u00023A\u0005!1/\u001a7g!\rA\u0014hO\u0007\u0002)%\u0011!\b\u0006\u0002\u0004%\u0012#\u0005CA\u0010=\u0013\ti\u0004E\u0001\u0004E_V\u0014G.Z\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005\u0001\u000b\u0005C\u0001\u001d\u0001\u0011\u00151$\u00011\u00018\u0003\r\u0019X/\u001c\u000b\u0002w\u0005)1\u000f^1ugR\ta\t\u0005\u0002H\u00156\t\u0001J\u0003\u0002J-\u0005!Q\u000f^5m\u0013\tY\u0005JA\u0006Ti\u0006$8i\\;oi\u0016\u0014\u0018\u0001B7fC:\f\u0001B^1sS\u0006t7-Z\u0001\u0006gR$WM^\u0001\fg\u0006l\u0007\u000f\\3Ti\u0012,g/\u0001\btC6\u0004H.\u001a,be&\fgnY3\u0002\u0011A|\u0007o\u0015;eKZD3AC*Z!\t!v+D\u0001V\u0015\t1f#\u0001\u0006b]:|G/\u0019;j_:L!\u0001W+\u0003\u000bMKgnY3\"\u0003i\u000bQA\r\u00182]A\n1\u0002]8q-\u0006\u0014\u0018.\u00198dK\"\u001a1bU-\u0002\u00155,\u0017M\\!qaJ|\u0007\u0010F\u0002`Q6\u00042\u0001Y2f\u001b\u0005\t'B\u00012\u0017\u0003\u001d\u0001\u0018M\u001d;jC2L!\u0001Z1\u0003\u001bA\u000b'\u000f^5bYJ+7/\u001e7u!\t\u0001g-\u0003\u0002hC\ni!i\\;oI\u0016$Gi\\;cY\u0016DQ!\u001b\u0007A\u0002)\fq\u0001^5nK>,H\u000f\u0005\u0002 W&\u0011A\u000e\t\u0002\u0005\u0019>tw\rC\u0004o\u0019A\u0005\t\u0019A\u001e\u0002\u0015\r|gNZ5eK:\u001cW-\u0001\u000bnK\u0006t\u0017\t\u001d9s_b$C-\u001a4bk2$HEM\u000b\u0002c*\u00121H]\u0016\u0002gB\u0011A\u000f_\u0007\u0002k*\u0011ao^\u0001\nk:\u001c\u0007.Z2lK\u0012T!A\u0016\u0011\n\u0005e,(!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006I1/^7BaB\u0014x\u000e\u001f\u000b\u0004?rl\b\"B5\u000f\u0001\u0004Q\u0007b\u00028\u000f!\u0003\u0005\raO\u0001\u0014gVl\u0017\t\u001d9s_b$C-\u001a4bk2$HEM\u0001\nQ&\u001cHo\\4sC6$B!a\u0001\u0002\u0012A9q$!\u0002\u0002\n\u0005=\u0011bAA\u0004A\t1A+\u001e9mKJ\u0002BaHA\u0006w%\u0019\u0011Q\u0002\u0011\u0003\u000b\u0005\u0013(/Y=\u0011\t}\tYA\u001b\u0005\b\u0003'\u0001\u0002\u0019AA\u000b\u0003-\u0011WoY6fi\u000e{WO\u001c;\u0011\u0007}\t9\"C\u0002\u0002\u001a\u0001\u00121!\u00138u)\u0019\ty!!\b\u0002\"!9\u0011qD\tA\u0002\u0005%\u0011a\u00022vG.,Go\u001d\u0005\n\u0003G\t\u0002\u0013!a\u0001\u0003K\t1\"\u001a<f]\n+8m[3ugB\u0019q$a\n\n\u0007\u0005%\u0002EA\u0004C_>dW-\u00198\u0002'!L7\u000f^8he\u0006lG\u0005Z3gCVdG\u000f\n\u001a\u0016\u0005\u0005=\"fAA\u0013e\u0002"
)
public class DoubleRDDFunctions implements Logging, Serializable {
   private final RDD self;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public String logName() {
      return Logging.logName$(this);
   }

   public Logger log() {
      return Logging.log$(this);
   }

   public Logging.LogStringContext LogStringContext(final StringContext sc) {
      return Logging.LogStringContext$(this, sc);
   }

   public void withLogContext(final Map context, final Function0 body) {
      Logging.withLogContext$(this, context, body);
   }

   public void logInfo(final Function0 msg) {
      Logging.logInfo$(this, msg);
   }

   public void logInfo(final LogEntry entry) {
      Logging.logInfo$(this, entry);
   }

   public void logInfo(final LogEntry entry, final Throwable throwable) {
      Logging.logInfo$(this, entry, throwable);
   }

   public void logDebug(final Function0 msg) {
      Logging.logDebug$(this, msg);
   }

   public void logDebug(final LogEntry entry) {
      Logging.logDebug$(this, entry);
   }

   public void logDebug(final LogEntry entry, final Throwable throwable) {
      Logging.logDebug$(this, entry, throwable);
   }

   public void logTrace(final Function0 msg) {
      Logging.logTrace$(this, msg);
   }

   public void logTrace(final LogEntry entry) {
      Logging.logTrace$(this, entry);
   }

   public void logTrace(final LogEntry entry, final Throwable throwable) {
      Logging.logTrace$(this, entry, throwable);
   }

   public void logWarning(final Function0 msg) {
      Logging.logWarning$(this, msg);
   }

   public void logWarning(final LogEntry entry) {
      Logging.logWarning$(this, entry);
   }

   public void logWarning(final LogEntry entry, final Throwable throwable) {
      Logging.logWarning$(this, entry, throwable);
   }

   public void logError(final Function0 msg) {
      Logging.logError$(this, msg);
   }

   public void logError(final LogEntry entry) {
      Logging.logError$(this, entry);
   }

   public void logError(final LogEntry entry, final Throwable throwable) {
      Logging.logError$(this, entry, throwable);
   }

   public void logInfo(final Function0 msg, final Throwable throwable) {
      Logging.logInfo$(this, msg, throwable);
   }

   public void logDebug(final Function0 msg, final Throwable throwable) {
      Logging.logDebug$(this, msg, throwable);
   }

   public void logTrace(final Function0 msg, final Throwable throwable) {
      Logging.logTrace$(this, msg, throwable);
   }

   public void logWarning(final Function0 msg, final Throwable throwable) {
      Logging.logWarning$(this, msg, throwable);
   }

   public void logError(final Function0 msg, final Throwable throwable) {
      Logging.logError$(this, msg, throwable);
   }

   public boolean isTraceEnabled() {
      return Logging.isTraceEnabled$(this);
   }

   public void initializeLogIfNecessary(final boolean isInterpreter) {
      Logging.initializeLogIfNecessary$(this, isInterpreter);
   }

   public boolean initializeLogIfNecessary(final boolean isInterpreter, final boolean silent) {
      return Logging.initializeLogIfNecessary$(this, isInterpreter, silent);
   }

   public boolean initializeLogIfNecessary$default$2() {
      return Logging.initializeLogIfNecessary$default$2$(this);
   }

   public void initializeForcefully(final boolean isInterpreter, final boolean silent) {
      Logging.initializeForcefully$(this, isInterpreter, silent);
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   public double sum() {
      return BoxesRunTime.unboxToDouble(this.self.withScope((JFunction0.mcD.sp)() -> BoxesRunTime.unboxToDouble(this.self.fold(BoxesRunTime.boxToDouble((double)0.0F), (JFunction2.mcDDD.sp)(x$1, x$2) -> x$1 + x$2))));
   }

   public StatCounter stats() {
      return (StatCounter)this.self.withScope(() -> (StatCounter)this.self.mapPartitions((nums) -> .MODULE$.Iterator().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new StatCounter[]{StatCounter$.MODULE$.apply((IterableOnce)nums)})), this.self.mapPartitions$default$2(), scala.reflect.ClassTag..MODULE$.apply(StatCounter.class)).reduce((a, b) -> a.merge(b)));
   }

   public double mean() {
      return BoxesRunTime.unboxToDouble(this.self.withScope((JFunction0.mcD.sp)() -> this.stats().mean()));
   }

   public double variance() {
      return BoxesRunTime.unboxToDouble(this.self.withScope((JFunction0.mcD.sp)() -> this.stats().variance()));
   }

   public double stdev() {
      return BoxesRunTime.unboxToDouble(this.self.withScope((JFunction0.mcD.sp)() -> this.stats().stdev()));
   }

   public double sampleStdev() {
      return BoxesRunTime.unboxToDouble(this.self.withScope((JFunction0.mcD.sp)() -> this.stats().sampleStdev()));
   }

   public double sampleVariance() {
      return BoxesRunTime.unboxToDouble(this.self.withScope((JFunction0.mcD.sp)() -> this.stats().sampleVariance()));
   }

   public double popStdev() {
      return BoxesRunTime.unboxToDouble(this.self.withScope((JFunction0.mcD.sp)() -> this.stats().popStdev()));
   }

   public double popVariance() {
      return BoxesRunTime.unboxToDouble(this.self.withScope((JFunction0.mcD.sp)() -> this.stats().popVariance()));
   }

   public PartialResult meanApprox(final long timeout, final double confidence) {
      return (PartialResult)this.self.withScope(() -> {
         Function2 processPartition = (ctx, ns) -> StatCounter$.MODULE$.apply((IterableOnce)ns);
         MeanEvaluator evaluator = new MeanEvaluator(this.self.partitions().length, confidence);
         return this.self.context().runApproximateJob(this.self, processPartition, evaluator, timeout);
      });
   }

   public double meanApprox$default$2() {
      return 0.95;
   }

   public PartialResult sumApprox(final long timeout, final double confidence) {
      return (PartialResult)this.self.withScope(() -> {
         Function2 processPartition = (ctx, ns) -> StatCounter$.MODULE$.apply((IterableOnce)ns);
         SumEvaluator evaluator = new SumEvaluator(this.self.partitions().length, confidence);
         return this.self.context().runApproximateJob(this.self, processPartition, evaluator, timeout);
      });
   }

   public double sumApprox$default$2() {
      return 0.95;
   }

   public Tuple2 histogram(final int bucketCount) {
      return (Tuple2)this.self.withScope(() -> {
         Tuple2 var4 = (Tuple2)this.self.mapPartitions((items) -> .MODULE$.Iterator().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{(Tuple2)items.foldRight(new Tuple2.mcDD.sp(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY), (e, x) -> $anonfun$histogram$4(BoxesRunTime.unboxToDouble(e), x))}))), this.self.mapPartitions$default$2(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)).reduce((maxmin1, maxmin2) -> new Tuple2.mcDD.sp(scala.runtime.RichDouble..MODULE$.max$extension(scala.Predef..MODULE$.doubleWrapper(maxmin1._1$mcD$sp()), maxmin2._1$mcD$sp()), scala.runtime.RichDouble..MODULE$.min$extension(scala.Predef..MODULE$.doubleWrapper(maxmin1._2$mcD$sp()), maxmin2._2$mcD$sp())));
         if (var4 != null) {
            double max = var4._1$mcD$sp();
            double min = var4._2$mcD$sp();
            if (true && true) {
               Tuple2.mcDD.sp var3 = new Tuple2.mcDD.sp(max, min);
               double max = ((Tuple2)var3)._1$mcD$sp();
               double min = ((Tuple2)var3)._2$mcD$sp();
               if (!Double.isNaN(min) && !Double.isNaN(max) && !scala.runtime.RichDouble..MODULE$.isInfinity$extension(scala.Predef..MODULE$.doubleWrapper(max)) && !scala.runtime.RichDouble..MODULE$.isInfinity$extension(scala.Predef..MODULE$.doubleWrapper(min))) {
                  Seq range = (Seq)(min != max ? customRange$1(min, max, bucketCount) : (Seq).MODULE$.List().apply(scala.runtime.ScalaRunTime..MODULE$.wrapDoubleArray(new double[]{min, min})));
                  double[] buckets = (double[])range.toArray(scala.reflect.ClassTag..MODULE$.Double());
                  return new Tuple2(buckets, this.histogram(buckets, true));
               }

               throw SparkCoreErrors$.MODULE$.histogramOnEmptyRDDOrContainingInfinityOrNaNError();
            }
         }

         throw new MatchError(var4);
      });
   }

   public long[] histogram(final double[] buckets, final boolean evenBuckets) {
      return (long[])this.self.withScope(() -> {
         if (buckets.length < 2) {
            throw new IllegalArgumentException("buckets array must have at least two elements");
         } else {
            Function1 var10000;
            if (evenBuckets) {
               double var4 = BoxesRunTime.unboxToDouble(scala.collection.ArrayOps..MODULE$.head$extension(scala.Predef..MODULE$.doubleArrayOps(buckets)));
               double var6 = BoxesRunTime.unboxToDouble(scala.collection.ArrayOps..MODULE$.last$extension(scala.Predef..MODULE$.doubleArrayOps(buckets)));
               int var8 = buckets.length - 1;
               var10000 = (e) -> $anonfun$histogram$8(var4, var6, var8, BoxesRunTime.unboxToDouble(e));
            } else {
               var10000 = (e) -> $anonfun$histogram$9(buckets, BoxesRunTime.unboxToDouble(e));
            }

            Function1 bucketFunction = var10000;
            return this.self.partitions().length == 0 ? new long[buckets.length - 1] : (long[])this.self.mapPartitions((iter) -> histogramPartition$1(bucketFunction, iter, buckets), this.self.mapPartitions$default$2(), scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Long.TYPE))).reduce((a1, a2) -> mergeCounters$1(a1, a2));
         }
      });
   }

   public boolean histogram$default$2() {
      return false;
   }

   private static final IndexedSeq customRange$1(final double min, final double max, final int steps) {
      double span = max - min;
      return (IndexedSeq)((SeqOps)scala.collection.immutable.Range.Int..MODULE$.apply(0, steps, 1).map((JFunction1.mcDI.sp)(s) -> min + (double)s * span / (double)steps)).$colon$plus(BoxesRunTime.boxToDouble(max));
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$histogram$4(final double e, final Tuple2 x) {
      return new Tuple2.mcDD.sp(scala.runtime.RichDouble..MODULE$.max$extension(scala.Predef..MODULE$.doubleWrapper(x._1$mcD$sp()), e), scala.runtime.RichDouble..MODULE$.min$extension(scala.Predef..MODULE$.doubleWrapper(x._2$mcD$sp()), e));
   }

   private static final Iterator histogramPartition$1(final Function1 bucketFunction, final Iterator iter, final double[] buckets$1) {
      long[] counters = new long[buckets$1.length - 1];

      while(iter.hasNext()) {
         Option var5 = (Option)bucketFunction.apply(iter.next());
         if (var5 instanceof Some var6) {
            int x = BoxesRunTime.unboxToInt(var6.value());
            if (true) {
               int var10002 = counters[x]++;
               BoxedUnit var9 = BoxedUnit.UNIT;
               continue;
            }
         }

         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      return .MODULE$.Iterator().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new long[][]{counters})));
   }

   private static final long[] mergeCounters$1(final long[] a1, final long[] a2) {
      scala.collection.ArrayOps..MODULE$.indices$extension(scala.Predef..MODULE$.longArrayOps(a1)).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> a1[i] += a2[i]);
      return a1;
   }

   private static final Option basicBucketFunction$1(final double e, final double[] buckets$1) {
      int location = Arrays.binarySearch(buckets$1, e);
      if (location < 0) {
         int insertionPoint = -location - 1;
         return (Option)(insertionPoint > 0 && insertionPoint < buckets$1.length ? new Some(BoxesRunTime.boxToInteger(insertionPoint - 1)) : scala.None..MODULE$);
      } else {
         return location < buckets$1.length - 1 ? new Some(BoxesRunTime.boxToInteger(location)) : new Some(BoxesRunTime.boxToInteger(location - 1));
      }
   }

   private static final Option fastBucketFunction$1(final double min, final double max, final int count, final double e) {
      if (!Double.isNaN(e) && !(e < min) && !(e > max)) {
         int bucketNumber = (int)((e - min) / (max - min) * (double)count);
         return new Some(BoxesRunTime.boxToInteger(scala.math.package..MODULE$.min(bucketNumber, count - 1)));
      } else {
         return scala.None..MODULE$;
      }
   }

   // $FF: synthetic method
   public static final Option $anonfun$histogram$8(final double eta$0$1$1, final double eta$1$1$1, final int eta$2$1$1, final double e) {
      return fastBucketFunction$1(eta$0$1$1, eta$1$1$1, eta$2$1$1, e);
   }

   // $FF: synthetic method
   public static final Option $anonfun$histogram$9(final double[] buckets$1, final double e) {
      return basicBucketFunction$1(e, buckets$1);
   }

   public DoubleRDDFunctions(final RDD self) {
      this.self = self;
      Logging.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
