package org.apache.spark.util.random;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.rdd.RDD;
import org.apache.spark.rdd.RDD$;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.MapOps;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.HashMap;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ClassTag.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Null;
import scala.runtime.ObjectRef;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

public final class StratifiedSamplingUtils$ implements Logging {
   public static final StratifiedSamplingUtils$ MODULE$ = new StratifiedSamplingUtils$();
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
   }

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
      return org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      org$apache$spark$internal$Logging$$log_ = x$1;
   }

   public scala.collection.mutable.Map getAcceptanceResults(final RDD rdd, final boolean withReplacement, final scala.collection.Map fractions, final Option counts, final long seed) {
      Function2 combOp = this.getCombOp();
      RDD mappedPartitionRDD = rdd.mapPartitionsWithIndex((x0$1, x1$1) -> $anonfun$getAcceptanceResults$1(seed, withReplacement, fractions, counts, BoxesRunTime.unboxToInt(x0$1), x1$1), rdd.mapPartitionsWithIndex$default$2(), .MODULE$.apply(scala.collection.mutable.Map.class));
      return (scala.collection.mutable.Map)mappedPartitionRDD.reduce(combOp);
   }

   public Function2 getSeqOp(final boolean withReplacement, final scala.collection.Map fractions, final StratifiedSamplingUtils.RandomDataGenerator rng, final Option counts) {
      double delta = 5.0E-5;
      return (result, item) -> {
         Object key = item._1();
         double fraction = BoxesRunTime.unboxToDouble(fractions.apply(key));
         if (!result.contains(key)) {
            result.$plus$eq(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(key), new AcceptanceResult(AcceptanceResult$.MODULE$.$lessinit$greater$default$1(), AcceptanceResult$.MODULE$.$lessinit$greater$default$2())));
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

         AcceptanceResult acceptResult = (AcceptanceResult)result.apply(key);
         if (withReplacement) {
            if (acceptResult.areBoundsEmpty()) {
               long n = BoxesRunTime.unboxToLong(((MapOps)counts.get()).apply(key));
               long sampleSize = (long)scala.math.package..MODULE$.ceil((double)n * fraction);
               double lmbd1 = PoissonBounds$.MODULE$.getLowerBound((double)sampleSize);
               double lmbd2 = PoissonBounds$.MODULE$.getUpperBound((double)sampleSize);
               acceptResult.acceptBound_$eq(lmbd1 / (double)n);
               acceptResult.waitListBound_$eq((lmbd2 - lmbd1) / (double)n);
            }

            double acceptBound = acceptResult.acceptBound();
            long copiesAccepted = acceptBound == (double)0.0F ? 0L : (long)rng.nextPoisson(acceptBound);
            if (copiesAccepted > 0L) {
               acceptResult.numAccepted_$eq(acceptResult.numAccepted() + copiesAccepted);
            }

            int copiesWaitlisted = rng.nextPoisson(acceptResult.waitListBound());
            if (copiesWaitlisted > 0) {
               acceptResult.waitList().$plus$plus$eq(scala.collection.mutable.ArrayBuffer..MODULE$.fill(copiesWaitlisted, (JFunction0.mcD.sp)() -> rng.nextUniform()));
            } else {
               BoxedUnit var27 = BoxedUnit.UNIT;
            }
         } else {
            acceptResult.acceptBound_$eq(BinomialBounds$.MODULE$.getLowerBound(delta, acceptResult.numItems(), fraction));
            acceptResult.waitListBound_$eq(BinomialBounds$.MODULE$.getUpperBound(delta, acceptResult.numItems(), fraction));
            double x = rng.nextUniform();
            if (x < acceptResult.acceptBound()) {
               acceptResult.numAccepted_$eq(acceptResult.numAccepted() + 1L);
               BoxedUnit var28 = BoxedUnit.UNIT;
            } else if (x < acceptResult.waitListBound()) {
               acceptResult.waitList().$plus$eq(BoxesRunTime.boxToDouble(x));
            } else {
               BoxedUnit var29 = BoxedUnit.UNIT;
            }
         }

         acceptResult.numItems_$eq(acceptResult.numItems() + 1L);
         return result;
      };
   }

   public Function2 getCombOp() {
      return (result1, result2) -> {
         result1.keySet().union(result2.keySet()).foreach((key) -> {
            Option entry1 = result1.get(key);
            if (result2.contains(key)) {
               ((AcceptanceResult)result2.apply(key)).merge(entry1);
               return BoxedUnit.UNIT;
            } else {
               return entry1.isDefined() ? result2.$plus$eq(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(key), entry1.get())) : BoxedUnit.UNIT;
            }
         });
         return result2;
      };
   }

   public scala.collection.Map computeThresholdByKey(final scala.collection.Map finalResult, final scala.collection.Map fractions) {
      HashMap thresholdByKey = new HashMap();
      finalResult.withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$computeThresholdByKey$1(check$ifrefutable$1))).foreach((x$1) -> {
         if (x$1 != null) {
            Object key = x$1._1();
            AcceptanceResult acceptResult = (AcceptanceResult)x$1._2();
            long sampleSize = (long)scala.math.package..MODULE$.ceil((double)acceptResult.numItems() * BoxesRunTime.unboxToDouble(fractions.apply(key)));
            if (acceptResult.numAccepted() > sampleSize) {
               MODULE$.logWarning((Function0)(() -> "Pre-accepted too many"));
               return (HashMap)thresholdByKey.$plus$eq(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(key), BoxesRunTime.boxToDouble(acceptResult.acceptBound())));
            } else {
               int numWaitListAccepted = (int)(sampleSize - acceptResult.numAccepted());
               if (numWaitListAccepted >= acceptResult.waitList().size()) {
                  MODULE$.logWarning((Function0)(() -> "WaitList too short"));
                  return (HashMap)thresholdByKey.$plus$eq(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(key), BoxesRunTime.boxToDouble(acceptResult.waitListBound())));
               } else {
                  return (HashMap)thresholdByKey.$plus$eq(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(key), ((ArrayBuffer)acceptResult.waitList().sorted(scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$)).apply(numWaitListAccepted)));
               }
            }
         } else {
            throw new MatchError(x$1);
         }
      });
      return thresholdByKey;
   }

   public Function2 getBernoulliSamplingFunction(final RDD rdd, final scala.collection.Map fractions, final boolean exact, final long seed) {
      ObjectRef samplingRateByKey = ObjectRef.create(fractions);
      if (exact) {
         scala.collection.mutable.Map finalResult = this.getAcceptanceResults(rdd, false, fractions, scala.None..MODULE$, seed);
         samplingRateByKey.elem = this.computeThresholdByKey(finalResult, fractions);
      }

      return (idx, iter) -> $anonfun$getBernoulliSamplingFunction$1(seed, samplingRateByKey, BoxesRunTime.unboxToInt(idx), iter);
   }

   public Function2 getPoissonSamplingFunction(final RDD rdd, final scala.collection.Map fractions, final boolean exact, final long seed, final ClassTag evidence$1, final ClassTag evidence$2) {
      if (exact) {
         Null x$4 = RDD$.MODULE$.rddToPairRDDFunctions$default$4(rdd);
         Some counts = new Some(RDD$.MODULE$.rddToPairRDDFunctions(rdd, evidence$1, evidence$2, (Ordering)null).countByKey());
         scala.collection.mutable.Map finalResult = this.getAcceptanceResults(rdd, true, fractions, counts, seed);
         scala.collection.Map thresholdByKey = this.computeThresholdByKey(finalResult, fractions);
         return (idx, iter) -> $anonfun$getPoissonSamplingFunction$1(seed, finalResult, thresholdByKey, BoxesRunTime.unboxToInt(idx), iter);
      } else {
         return (idx, iter) -> $anonfun$getPoissonSamplingFunction$5(seed, fractions, BoxesRunTime.unboxToInt(idx), iter);
      }
   }

   // $FF: synthetic method
   public static final Iterator $anonfun$getAcceptanceResults$1(final long seed$1, final boolean withReplacement$1, final scala.collection.Map fractions$1, final Option counts$1, final int x0$1, final Iterator x1$1) {
      Tuple2 var8 = new Tuple2(BoxesRunTime.boxToInteger(x0$1), x1$1);
      if (var8 != null) {
         int partition = var8._1$mcI$sp();
         Iterator iter = (Iterator)var8._2();
         scala.collection.mutable.Map zeroU = new HashMap();
         StratifiedSamplingUtils.RandomDataGenerator rng = new StratifiedSamplingUtils.RandomDataGenerator();
         rng.reSeed(seed$1 + (long)partition);
         Function2 seqOp = MODULE$.getSeqOp(withReplacement$1, fractions$1, rng, counts$1);
         return scala.package..MODULE$.Iterator().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new scala.collection.mutable.Map[]{(scala.collection.mutable.Map)iter.foldLeft(zeroU, seqOp)})));
      } else {
         throw new MatchError(var8);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$computeThresholdByKey$1(final Tuple2 check$ifrefutable$1) {
      return check$ifrefutable$1 != null;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getBernoulliSamplingFunction$2(final StratifiedSamplingUtils.RandomDataGenerator rng$2, final ObjectRef samplingRateByKey$1, final Tuple2 t) {
      return rng$2.nextUniform() < BoxesRunTime.unboxToDouble(((scala.collection.Map)samplingRateByKey$1.elem).apply(t._1()));
   }

   // $FF: synthetic method
   public static final Iterator $anonfun$getBernoulliSamplingFunction$1(final long seed$2, final ObjectRef samplingRateByKey$1, final int idx, final Iterator iter) {
      StratifiedSamplingUtils.RandomDataGenerator rng = new StratifiedSamplingUtils.RandomDataGenerator();
      rng.reSeed(seed$2 + (long)idx);
      return iter.filter((t) -> BoxesRunTime.boxToBoolean($anonfun$getBernoulliSamplingFunction$2(rng, samplingRateByKey$1, t)));
   }

   // $FF: synthetic method
   public static final Iterator $anonfun$getPoissonSamplingFunction$1(final long seed$3, final scala.collection.mutable.Map finalResult$1, final scala.collection.Map thresholdByKey$2, final int idx, final Iterator iter) {
      StratifiedSamplingUtils.RandomDataGenerator rng = new StratifiedSamplingUtils.RandomDataGenerator();
      rng.reSeed(seed$3 + (long)idx);
      return iter.flatMap((item) -> {
         Object key = item._1();
         double acceptBound = ((AcceptanceResult)finalResult$1.apply(key)).acceptBound();
         long copiesAccepted = acceptBound == (double)0 ? 0L : (long)rng.nextPoisson(acceptBound);
         int copiesWaitlisted = rng.nextPoisson(((AcceptanceResult)finalResult$1.apply(key)).waitListBound());
         long copiesInSample = copiesAccepted + (long)scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), copiesWaitlisted).count((JFunction1.mcZI.sp)(i) -> rng.nextUniform() < BoxesRunTime.unboxToDouble(thresholdByKey$2.apply(key)));
         return copiesInSample > 0L ? scala.package..MODULE$.Iterator().fill((int)copiesInSample, () -> item) : scala.package..MODULE$.Iterator().empty();
      });
   }

   // $FF: synthetic method
   public static final Iterator $anonfun$getPoissonSamplingFunction$5(final long seed$3, final scala.collection.Map fractions$4, final int idx, final Iterator iter) {
      StratifiedSamplingUtils.RandomDataGenerator rng = new StratifiedSamplingUtils.RandomDataGenerator();
      rng.reSeed(seed$3 + (long)idx);
      return iter.flatMap((item) -> {
         int count = rng.nextPoisson(BoxesRunTime.unboxToDouble(fractions$4.apply(item._1())));
         return count == 0 ? scala.package..MODULE$.Iterator().empty() : scala.package..MODULE$.Iterator().fill(count, () -> item);
      });
   }

   private StratifiedSamplingUtils$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
