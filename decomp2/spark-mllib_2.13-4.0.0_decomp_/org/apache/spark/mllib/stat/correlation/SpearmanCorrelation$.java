package org.apache.spark.mllib.stat.correlation;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.mllib.linalg.Matrix;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors$;
import org.apache.spark.rdd.OrderedRDDFunctions;
import org.apache.spark.rdd.RDD;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.Iterable;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.mutable.ArrayBuffer;
import scala.reflect.ClassTag.;
import scala.runtime.BoxesRunTime;
import scala.runtime.DoubleRef;
import scala.runtime.IntRef;

public final class SpearmanCorrelation$ implements Correlation, Logging {
   public static final SpearmanCorrelation$ MODULE$ = new SpearmanCorrelation$();
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Correlation.$init$(MODULE$);
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

   public double computeCorrelationWithMatrixImpl(final RDD x, final RDD y) {
      return Correlation.computeCorrelationWithMatrixImpl$(this, x, y);
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      org$apache$spark$internal$Logging$$log_ = x$1;
   }

   public double computeCorrelation(final RDD x, final RDD y) {
      return this.computeCorrelationWithMatrixImpl(x, y);
   }

   public Matrix computeCorrelationMatrix(final RDD X) {
      RDD colBased = X.zipWithUniqueId().flatMap((x0$1) -> {
         if (x0$1 != null) {
            Vector vec = (Vector)x0$1._1();
            long uid = x0$1._2$mcJ$sp();
            return vec.iterator().map((t) -> new Tuple2(t, BoxesRunTime.boxToLong(uid)));
         } else {
            throw new MatchError(x0$1);
         }
      }, .MODULE$.apply(Tuple2.class));
      OrderedRDDFunctions qual$1 = org.apache.spark.rdd.RDD..MODULE$.rddToOrderedRDDFunctions(colBased, scala.math.Ordering..MODULE$.Tuple2(scala.math.Ordering.Int..MODULE$, scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$), .MODULE$.apply(Tuple2.class), .MODULE$.Long());
      boolean x$1 = qual$1.sortByKey$default$1();
      int x$2 = qual$1.sortByKey$default$2();
      RDD sorted = qual$1.sortByKey(x$1, x$2);
      RDD qual$2 = sorted.zipWithIndex();
      Function1 x$3 = (iter) -> {
         IntRef preCol = IntRef.create(-1);
         DoubleRef preVal = DoubleRef.create(Double.NaN);
         DoubleRef startRank = DoubleRef.create((double)-1.0F);
         ArrayBuffer cachedUids = scala.collection.mutable.ArrayBuffer..MODULE$.empty();
         Function0 flush = () -> {
            double averageRank = startRank.elem + (double)(cachedUids.size() - 1) / (double)2.0F;
            ArrayBuffer output = (ArrayBuffer)cachedUids.map((uid) -> $anonfun$computeCorrelationMatrix$5(preCol, averageRank, BoxesRunTime.unboxToLong(uid)));
            cachedUids.clear();
            return output;
         };
         return iter.flatMap((x0$2) -> {
            if (x0$2 != null) {
               Tuple2 var8 = (Tuple2)x0$2._1();
               long rank = x0$2._2$mcJ$sp();
               if (var8 != null) {
                  Tuple2 var11 = (Tuple2)var8._1();
                  long uid = var8._2$mcJ$sp();
                  if (var11 != null) {
                     int j = var11._1$mcI$sp();
                     double v = var11._2$mcD$sp();
                     if (j == preCol.elem && v == preVal.elem && cachedUids.size() < 10000000) {
                        cachedUids.$plus$eq(BoxesRunTime.boxToLong(uid));
                        return scala.package..MODULE$.Iterator().empty();
                     }

                     Iterable output = (Iterable)flush.apply();
                     preCol.elem = j;
                     preVal.elem = v;
                     startRank.elem = (double)rank;
                     cachedUids.$plus$eq(BoxesRunTime.boxToLong(uid));
                     return output;
                  }
               }
            }

            throw new MatchError(x0$2);
         }).$plus$plus(flush);
      };
      boolean x$4 = qual$2.mapPartitions$default$2();
      RDD globalRanks = qual$2.mapPartitions(x$3, x$4, .MODULE$.apply(Tuple2.class));
      RDD groupedRanks = org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(globalRanks, .MODULE$.Long(), .MODULE$.apply(Tuple2.class), scala.math.Ordering.Long..MODULE$).groupByKey().map((x0$3) -> {
         if (x0$3 != null) {
            Iterable iter = (Iterable)x0$3._2();
            return Vectors$.MODULE$.dense((double[])((IterableOnceOps)((IterableOps)iter.toSeq().sortBy((x$1) -> BoxesRunTime.boxToInteger($anonfun$computeCorrelationMatrix$8(x$1)), scala.math.Ordering.Int..MODULE$)).map((x$2) -> BoxesRunTime.boxToDouble($anonfun$computeCorrelationMatrix$9(x$2)))).toArray(.MODULE$.Double()));
         } else {
            throw new MatchError(x0$3);
         }
      }, .MODULE$.apply(Vector.class));
      return PearsonCorrelation$.MODULE$.computeCorrelationMatrix(groupedRanks);
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$computeCorrelationMatrix$5(final IntRef preCol$1, final double averageRank$1, final long uid) {
      return new Tuple2(BoxesRunTime.boxToLong(uid), new Tuple2.mcID.sp(preCol$1.elem, averageRank$1));
   }

   // $FF: synthetic method
   public static final int $anonfun$computeCorrelationMatrix$8(final Tuple2 x$1) {
      return x$1._1$mcI$sp();
   }

   // $FF: synthetic method
   public static final double $anonfun$computeCorrelationMatrix$9(final Tuple2 x$2) {
      return x$2._2$mcD$sp();
   }

   private SpearmanCorrelation$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
