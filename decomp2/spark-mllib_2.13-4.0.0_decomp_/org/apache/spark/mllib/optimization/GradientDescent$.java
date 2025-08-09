package org.apache.spark.mllib.optimization;

import breeze.linalg.DenseVector;
import breeze.linalg.NumericOps;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.LogEntry.;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors$;
import org.apache.spark.rdd.RDD;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function2;
import scala.MatchError;
import scala.None;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.IterableOnceOps;
import scala.collection.mutable.ArrayBuffer;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.ModuleSerializationProxy;

public final class GradientDescent$ implements Logging, Serializable {
   public static final GradientDescent$ MODULE$ = new GradientDescent$();
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

   public Tuple2 runMiniBatchSGD(final RDD data, final Gradient gradient, final Updater updater, final double stepSize, final int numIterations, final double regParam, final double miniBatchFraction, final Vector initialWeights, final double convergenceTol) {
      if (miniBatchFraction < (double)1.0F && convergenceTol > (double)0.0F) {
         this.logWarning((Function0)(() -> "Testing against a convergenceTol when using miniBatchFraction < 1.0 can be unstable because of the stochasticity in sampling."));
      }

      if ((double)numIterations * miniBatchFraction < (double)1.0F) {
         this.logWarning(.MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Not all examples will be used if numIterations * miniBatchFraction < 1.0: "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"numIterations=", " and "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_ITERATIONS..MODULE$, BoxesRunTime.boxToInteger(numIterations))})))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"miniBatchFraction=", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MINI_BATCH_FRACTION..MODULE$, BoxesRunTime.boxToDouble(miniBatchFraction))}))))));
      }

      ArrayBuffer stochasticLossHistory = new ArrayBuffer(numIterations + 1);
      Option previousWeights = scala.None..MODULE$;
      Option currentWeights = scala.None..MODULE$;
      long numExamples = data.count();
      if (numExamples == 0L) {
         this.logWarning((Function0)(() -> "GradientDescent.runMiniBatchSGD returning initial weights, no data found"));
         return new Tuple2(initialWeights, stochasticLossHistory.toArray(scala.reflect.ClassTag..MODULE$.Double()));
      } else {
         if ((double)numExamples * miniBatchFraction < (double)1) {
            this.logWarning((Function0)(() -> "The miniBatchFraction is too small"));
         }

         Vector weights = Vectors$.MODULE$.dense(initialWeights.toArray());
         int n = weights.size();
         double regVal = updater.compute(weights, Vectors$.MODULE$.zeros(weights.size()), (double)0.0F, 1, regParam)._2$mcD$sp();
         boolean converged = false;

         for(IntRef i = IntRef.create(1); !converged && i.elem <= numIterations + 1; ++i.elem) {
            Broadcast bcWeights = data.context().broadcast(weights, scala.reflect.ClassTag..MODULE$.apply(Vector.class));
            RDD qual$1 = data.sample(false, miniBatchFraction, (long)(42 + i.elem));
            Tuple3 x$1 = new Tuple3((Object)null, BoxesRunTime.boxToDouble((double)0.0F), BoxesRunTime.boxToLong(0L));
            Function2 x$2 = (c, v) -> {
               DenseVector vec = c._1() == null ? breeze.linalg.DenseVector..MODULE$.zeros$mDc$sp(n, scala.reflect.ClassTag..MODULE$.Double(), breeze.storage.Zero..MODULE$.DoubleZero()) : (DenseVector)c._1();
               double l = gradient.compute((Vector)v._2(), v._1$mcD$sp(), (Vector)bcWeights.value(), Vectors$.MODULE$.fromBreeze(vec));
               return new Tuple3(vec, BoxesRunTime.boxToDouble(BoxesRunTime.unboxToDouble(c._2()) + l), BoxesRunTime.boxToLong(BoxesRunTime.unboxToLong(c._3()) + 1L));
            };
            Function2 x$3 = (c1, c2) -> {
               DenseVector var10000;
               if (c1._1() == null) {
                  var10000 = (DenseVector)c2._1();
               } else if (c2._1() == null) {
                  var10000 = (DenseVector)c1._1();
               } else {
                  ((NumericOps)c1._1()).$plus$eq(c2._1(), breeze.linalg.operators.HasOps..MODULE$.impl_OpAdd_InPlace_DV_DV_Double());
                  var10000 = (DenseVector)c1._1();
               }

               DenseVector vec = var10000;
               return new Tuple3(vec, BoxesRunTime.boxToDouble(BoxesRunTime.unboxToDouble(c1._2()) + BoxesRunTime.unboxToDouble(c2._2())), BoxesRunTime.boxToLong(BoxesRunTime.unboxToLong(c1._3()) + BoxesRunTime.unboxToLong(c2._3())));
            };
            int x$4 = qual$1.treeAggregate$default$4(x$1);
            Tuple3 var28 = (Tuple3)qual$1.treeAggregate(x$1, x$2, x$3, x$4, scala.reflect.ClassTag..MODULE$.apply(Tuple3.class));
            if (var28 == null) {
               throw new MatchError(var28);
            }

            DenseVector gradientSum = (DenseVector)var28._1();
            double lossSum = BoxesRunTime.unboxToDouble(var28._2());
            long miniBatchSize = BoxesRunTime.unboxToLong(var28._3());
            Tuple3 var27 = new Tuple3(gradientSum, BoxesRunTime.boxToDouble(lossSum), BoxesRunTime.boxToLong(miniBatchSize));
            DenseVector gradientSum = (DenseVector)var27._1();
            double lossSum = BoxesRunTime.unboxToDouble(var27._2());
            long miniBatchSize = BoxesRunTime.unboxToLong(var27._3());
            bcWeights.destroy();
            if (miniBatchSize > 0L) {
               stochasticLossHistory.$plus$eq(BoxesRunTime.boxToDouble(lossSum / (double)miniBatchSize + regVal));
               if (i.elem != numIterations + 1) {
                  Tuple2 update = updater.compute(weights, Vectors$.MODULE$.fromBreeze((breeze.linalg.Vector)gradientSum.$div(BoxesRunTime.boxToDouble((double)miniBatchSize), breeze.linalg.operators.HasOps..MODULE$.impl_Op_DV_S_eq_DV_Double_OpDiv())), stepSize, i.elem, regParam);
                  weights = (Vector)update._1();
                  regVal = update._2$mcD$sp();
                  previousWeights = currentWeights;
                  currentWeights = new Some(weights);
                  None var45 = scala.None..MODULE$;
                  if (previousWeights == null) {
                     if (var45 == null) {
                        continue;
                     }
                  } else if (previousWeights.equals(var45)) {
                     continue;
                  }

                  None var46 = scala.None..MODULE$;
                  if (currentWeights == null) {
                     if (var46 == null) {
                        continue;
                     }
                  } else if (currentWeights.equals(var46)) {
                     continue;
                  }

                  converged = this.isConverged((Vector)previousWeights.get(), (Vector)currentWeights.get(), convergenceTol);
               }
            } else {
               this.logWarning(.MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Iteration "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(", "/", "). "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.INDEX..MODULE$, BoxesRunTime.boxToInteger(i.elem)), new MDC(org.apache.spark.internal.LogKeys.NUM_ITERATIONS..MODULE$, BoxesRunTime.boxToInteger(numIterations))})))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"The size of sampled batch is zero"})))).log(scala.collection.immutable.Nil..MODULE$))));
            }
         }

         this.logInfo(.MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"GradientDescent.runMiniBatchSGD finished. Last 10 stochastic losses "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.LOSSES..MODULE$, ((IterableOnceOps)stochasticLossHistory.takeRight(10)).mkString(", "))}))))));
         return new Tuple2(weights, stochasticLossHistory.toArray(scala.reflect.ClassTag..MODULE$.Double()));
      }
   }

   public Tuple2 runMiniBatchSGD(final RDD data, final Gradient gradient, final Updater updater, final double stepSize, final int numIterations, final double regParam, final double miniBatchFraction, final Vector initialWeights) {
      return this.runMiniBatchSGD(data, gradient, updater, stepSize, numIterations, regParam, miniBatchFraction, initialWeights, 0.001);
   }

   private boolean isConverged(final Vector previousWeights, final Vector currentWeights, final double convergenceTol) {
      DenseVector previousBDV = previousWeights.asBreeze().toDenseVector$mcD$sp(scala.reflect.ClassTag..MODULE$.Double());
      DenseVector currentBDV = currentWeights.asBreeze().toDenseVector$mcD$sp(scala.reflect.ClassTag..MODULE$.Double());
      double solutionVecDiff = BoxesRunTime.unboxToDouble(breeze.linalg.norm..MODULE$.apply(previousBDV.$minus(currentBDV, breeze.linalg.operators.HasOps..MODULE$.impl_OpSub_DV_DV_eq_DV_Double()), breeze.linalg.norm..MODULE$.normDoubleToNormalNorm(breeze.linalg.norm..MODULE$.canNorm(breeze.linalg.operators.HasOps..MODULE$.DV_canIterateValues(), breeze.linalg.norm..MODULE$.scalarNorm_Double()))));
      return solutionVecDiff < convergenceTol * Math.max(BoxesRunTime.unboxToDouble(breeze.linalg.norm..MODULE$.apply(currentBDV, breeze.linalg.norm..MODULE$.normDoubleToNormalNorm(breeze.linalg.norm..MODULE$.canNorm(breeze.linalg.operators.HasOps..MODULE$.DV_canIterateValues(), breeze.linalg.norm..MODULE$.scalarNorm_Double())))), (double)1.0F);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(GradientDescent$.class);
   }

   private GradientDescent$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
