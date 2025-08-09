package org.apache.spark.mllib.clustering;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.mllib.linalg.BLAS$;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors$;
import org.slf4j.Logger;
import scala.Function0;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.ArrayOps.;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;
import scala.util.Random;

public final class LocalKMeans$ implements Logging {
   public static final LocalKMeans$ MODULE$ = new LocalKMeans$();
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

   public VectorWithNorm[] kMeansPlusPlus(final int seed, final VectorWithNorm[] points, final double[] weights, final int k, final int maxIterations) {
      Random rand = new Random(seed);
      int dimensions = points[0].vector().size();
      VectorWithNorm[] centers = new VectorWithNorm[k];
      centers[0] = ((VectorWithNorm)this.pickWeighted(rand, points, weights)).toDense();
      double[] costArray = (double[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(points), (x$1) -> BoxesRunTime.boxToDouble($anonfun$kMeansPlusPlus$1(centers, x$1)), scala.reflect.ClassTag..MODULE$.Double());
      scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(1), k).foreach$mVc$sp((JFunction1.mcVI.sp)(ix) -> {
         double sum = BoxesRunTime.unboxToDouble(scala.Predef..MODULE$.wrapDoubleArray((double[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[]).MODULE$.zip$extension(scala.Predef..MODULE$.doubleArrayOps(costArray), scala.Predef..MODULE$.wrapDoubleArray(weights))), (p) -> BoxesRunTime.boxToDouble($anonfun$kMeansPlusPlus$3(p)), scala.reflect.ClassTag..MODULE$.Double())).sum(scala.math.Numeric.DoubleIsFractional..MODULE$));
         double r = rand.nextDouble() * sum;
         double cumulativeScore = (double)0.0F;

         int j;
         for(j = 0; j < points.length && cumulativeScore < r; ++j) {
            cumulativeScore += weights[j] * costArray[j];
         }

         if (j == 0) {
            MODULE$.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"kMeansPlusPlus initialization ran out of distinct points for centers."})))).log(scala.collection.immutable.Nil..MODULE$).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" Using duplicate point for center k = ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.POINT_OF_CENTER..MODULE$, BoxesRunTime.boxToInteger(ix))}))))));
            centers[ix] = points[0].toDense();
         } else {
            centers[ix] = points[j - 1].toDense();
         }

         .MODULE$.indices$extension(scala.Predef..MODULE$.refArrayOps(points)).foreach$mVc$sp((JFunction1.mcVI.sp)(p) -> costArray[p] = scala.math.package..MODULE$.min(EuclideanDistanceMeasure$.MODULE$.fastSquaredDistance(points[p], centers[ix]), costArray[p]));
      });
      EuclideanDistanceMeasure distanceMeasureInstance = new EuclideanDistanceMeasure();
      int[] oldClosest = (int[])scala.Array..MODULE$.fill(points.length, (JFunction0.mcI.sp)() -> -1, scala.reflect.ClassTag..MODULE$.Int());
      IntRef iteration = IntRef.create(0);

      for(boolean moved = true; moved && iteration.elem < maxIterations; ++iteration.elem) {
         moved = false;
         double[] counts = (double[])scala.Array..MODULE$.ofDim(k, scala.reflect.ClassTag..MODULE$.Double());
         Vector[] sums = (Vector[])scala.Array..MODULE$.fill(k, () -> Vectors$.MODULE$.zeros(dimensions), scala.reflect.ClassTag..MODULE$.apply(Vector.class));

         for(int i = 0; i < points.length; ++i) {
            VectorWithNorm p = points[i];
            int index = distanceMeasureInstance.findClosest(centers, p)._1$mcI$sp();
            BLAS$.MODULE$.axpy(weights[i], p.vector(), sums[index]);
            counts[index] += weights[i];
            if (index != oldClosest[i]) {
               moved = true;
               oldClosest[i] = index;
            }
         }

         for(int j = 0; j < k; ++j) {
            if (counts[j] == (double)0.0F) {
               centers[j] = points[rand.nextInt(points.length)].toDense();
            } else {
               BLAS$.MODULE$.scal((double)1.0F / counts[j], sums[j]);
               centers[j] = new VectorWithNorm(sums[j]);
            }
         }
      }

      if (iteration.elem == maxIterations) {
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Local KMeans++ reached the max number of "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"iterations: ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_ITERATIONS..MODULE$, BoxesRunTime.boxToInteger(maxIterations))}))))));
      } else {
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Local KMeans++ converged in ", " iterations."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_ITERATIONS..MODULE$, BoxesRunTime.boxToInteger(iteration.elem))})))));
      }

      return centers;
   }

   private Object pickWeighted(final Random rand, final Object data, final double[] weights) {
      double r = rand.nextDouble() * BoxesRunTime.unboxToDouble(scala.Predef..MODULE$.wrapDoubleArray(weights).sum(scala.math.Numeric.DoubleIsFractional..MODULE$));
      int i = 0;

      for(double curWeight = (double)0.0F; i < scala.runtime.ScalaRunTime..MODULE$.array_length(data) && curWeight < r; ++i) {
         curWeight += weights[i];
      }

      return scala.runtime.ScalaRunTime..MODULE$.array_apply(data, i - 1);
   }

   // $FF: synthetic method
   public static final double $anonfun$kMeansPlusPlus$1(final VectorWithNorm[] centers$1, final VectorWithNorm x$1) {
      return EuclideanDistanceMeasure$.MODULE$.fastSquaredDistance(x$1, centers$1[0]);
   }

   // $FF: synthetic method
   public static final double $anonfun$kMeansPlusPlus$3(final Tuple2 p) {
      return p._1$mcD$sp() * p._2$mcD$sp();
   }

   private LocalKMeans$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
