package org.apache.spark.mllib.stat.test;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.distribution.RealDistribution;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.rdd.RDD;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime.;
import scala.runtime.java8.JFunction1;

public final class KolmogorovSmirnovTest$ implements Logging {
   public static final KolmogorovSmirnovTest$ MODULE$ = new KolmogorovSmirnovTest$();
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

   public KolmogorovSmirnovTestResult testOneSample(final RDD data, final String distName, final double... params) {
      return this.testOneSample(data, distName, (Seq).MODULE$.wrapDoubleArray(params));
   }

   public KolmogorovSmirnovTestResult testOneSample(final RDD data, final Function1 cdf) {
      double n = (double)data.count();
      RDD qual$1 = data.sortBy((JFunction1.mcDD.sp)(x) -> x, data.sortBy$default$2(), data.sortBy$default$3(), scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$, scala.reflect.ClassTag..MODULE$.Double());
      Function1 x$1 = (part) -> {
         Iterator partDiffs = MODULE$.oneSampleDifferences(part, n, cdf);
         return MODULE$.searchOneSampleCandidates(partDiffs);
      };
      boolean x$2 = qual$1.mapPartitions$default$2();
      Tuple3[] localData = (Tuple3[])qual$1.mapPartitions(x$1, x$2, scala.reflect.ClassTag..MODULE$.apply(Tuple3.class)).collect();
      double ksStat = this.searchOneSampleStatistic(localData, n);
      return this.evalOneSampleP(ksStat, (long)n);
   }

   public KolmogorovSmirnovTestResult testOneSample(final RDD data, final RealDistribution distObj) {
      Function1 cdf = (x) -> distObj.cumulativeProbability(x);
      return this.testOneSample(data, cdf);
   }

   private Iterator oneSampleDifferences(final Iterator partData, final double n, final Function1 cdf) {
      return partData.zipWithIndex().map((x0$1) -> {
         if (x0$1 != null) {
            double v = x0$1._1$mcD$sp();
            int ix = x0$1._2$mcI$sp();
            double dp = (double)(ix + 1) / n;
            double dl = (double)ix / n;
            double cdfVal = cdf.apply$mcDD$sp(v);
            return new Tuple2.mcDD.sp(dl - cdfVal, dp - cdfVal);
         } else {
            throw new MatchError(x0$1);
         }
      });
   }

   private Iterator searchOneSampleCandidates(final Iterator partDiffs) {
      Tuple3[] var10000;
      label17: {
         label16: {
            Tuple3 initAcc = new Tuple3(BoxesRunTime.boxToDouble(Double.MAX_VALUE), BoxesRunTime.boxToDouble(-Double.MAX_VALUE), BoxesRunTime.boxToDouble((double)0.0F));
            Tuple3 pResults = (Tuple3)partDiffs.foldLeft(initAcc, (x0$1, x1$1) -> {
               Tuple2 var3 = new Tuple2(x0$1, x1$1);
               if (var3 != null) {
                  Tuple3 var4 = (Tuple3)var3._1();
                  Tuple2 var5 = (Tuple2)var3._2();
                  if (var4 != null) {
                     double pMin = BoxesRunTime.unboxToDouble(var4._1());
                     double pMax = BoxesRunTime.unboxToDouble(var4._2());
                     double pCt = BoxesRunTime.unboxToDouble(var4._3());
                     if (var5 != null) {
                        double dl = var5._1$mcD$sp();
                        double dp = var5._2$mcD$sp();
                        return new Tuple3(BoxesRunTime.boxToDouble(scala.math.package..MODULE$.min(pMin, dl)), BoxesRunTime.boxToDouble(scala.math.package..MODULE$.max(pMax, dp)), BoxesRunTime.boxToDouble(pCt + (double)1));
                     }
                  }
               }

               throw new MatchError(var3);
            });
            if (pResults == null) {
               if (initAcc == null) {
                  break label16;
               }
            } else if (pResults.equals(initAcc)) {
               break label16;
            }

            var10000 = (Tuple3[])((Object[])(new Tuple3[]{pResults}));
            break label17;
         }

         var10000 = (Tuple3[])scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.apply(Tuple3.class));
      }

      Tuple3[] results = var10000;
      return scala.collection.ArrayOps..MODULE$.iterator$extension(scala.Predef..MODULE$.refArrayOps((Object[])results));
   }

   private double searchOneSampleStatistic(final Tuple3[] localData, final double n) {
      Tuple2 initAcc = new Tuple2.mcDD.sp(-Double.MAX_VALUE, (double)0.0F);
      Tuple2 results = (Tuple2)scala.collection.ArrayOps..MODULE$.foldLeft$extension(scala.Predef..MODULE$.refArrayOps((Object[])localData), initAcc, (x0$1, x1$1) -> {
         Tuple2 var5 = new Tuple2(x0$1, x1$1);
         if (var5 != null) {
            Tuple2 var6 = (Tuple2)var5._1();
            Tuple3 var7 = (Tuple3)var5._2();
            if (var6 != null) {
               double prevMax = var6._1$mcD$sp();
               double prevCt = var6._2$mcD$sp();
               if (var7 != null) {
                  double minCand = BoxesRunTime.unboxToDouble(var7._1());
                  double maxCand = BoxesRunTime.unboxToDouble(var7._2());
                  double ct = BoxesRunTime.unboxToDouble(var7._3());
                  double adjConst = prevCt / n;
                  double dist1 = scala.math.package..MODULE$.abs(minCand + adjConst);
                  double dist2 = scala.math.package..MODULE$.abs(maxCand + adjConst);
                  double maxVal = BoxesRunTime.unboxToDouble(scala.Predef..MODULE$.wrapDoubleArray(new double[]{prevMax, dist1, dist2}).max(scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$));
                  return new Tuple2.mcDD.sp(maxVal, prevCt + ct);
               }
            }
         }

         throw new MatchError(var5);
      });
      return results._1$mcD$sp();
   }

   public KolmogorovSmirnovTestResult testOneSample(final RDD data, final String distName, final Seq params) {
      switch (distName == null ? 0 : distName.hashCode()) {
         case 3387324:
            if ("norm".equals(distName)) {
               NormalDistribution var10000;
               if (params.nonEmpty()) {
                  scala.Predef..MODULE$.require(params.length() == 2, () -> "Normal distribution requires mean and standard deviation as parameters");
                  var10000 = new NormalDistribution(BoxesRunTime.unboxToDouble(params.apply(0)), BoxesRunTime.unboxToDouble(params.apply(1)));
               } else {
                  this.logInfo((Function0)(() -> "No parameters specified for normal distribution,initialized to standard normal (i.e. N(0, 1))"));
                  var10000 = new NormalDistribution((double)0.0F, (double)1.0F);
               }

               NormalDistribution distObj = var10000;
               return this.testOneSample(data, (RealDistribution)distObj);
            }
         default:
            throw new UnsupportedOperationException(distName + " not yet supported through convenience method. Current options are:['norm'].");
      }
   }

   private KolmogorovSmirnovTestResult evalOneSampleP(final double ksStat, final long n) {
      double pval = (double)1 - (new org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest()).cdf(ksStat, (int)n);
      return new KolmogorovSmirnovTestResult(pval, ksStat, KolmogorovSmirnovTest.NullHypothesis$.MODULE$.OneSampleTwoSided().toString());
   }

   private KolmogorovSmirnovTest$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
