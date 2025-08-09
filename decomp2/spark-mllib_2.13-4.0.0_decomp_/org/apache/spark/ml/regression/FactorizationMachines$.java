package org.apache.spark.ml.regression;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.linalg.DenseMatrix;
import org.apache.spark.ml.linalg.DenseVector;
import org.apache.spark.ml.linalg.Matrix;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.mllib.optimization.SquaredL2Updater;
import org.apache.spark.mllib.optimization.Updater;
import scala.MatchError;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.ArrayOps.;
import scala.collection.immutable.Seq;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.DoubleRef;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.java8.JFunction1;
import scala.runtime.java8.JFunction2;

public final class FactorizationMachines$ implements Serializable {
   public static final FactorizationMachines$ MODULE$ = new FactorizationMachines$();
   private static final String GD = "gd";
   private static final String AdamW = "adamW";
   private static final String[] supportedSolvers;
   private static final String LogisticLoss;
   private static final String SquaredError;
   private static final String[] supportedRegressorLosses;
   private static final String[] supportedClassifierLosses;
   private static final String[] supportedLosses;

   static {
      supportedSolvers = (String[])((Object[])(new String[]{MODULE$.GD(), MODULE$.AdamW()}));
      LogisticLoss = "logisticLoss";
      SquaredError = "squaredError";
      supportedRegressorLosses = (String[])((Object[])(new String[]{MODULE$.SquaredError()}));
      supportedClassifierLosses = (String[])((Object[])(new String[]{MODULE$.LogisticLoss()}));
      supportedLosses = (String[]).MODULE$.$plus$plus$extension(scala.Predef..MODULE$.refArrayOps((Object[])MODULE$.supportedRegressorLosses()), MODULE$.supportedClassifierLosses(), scala.reflect.ClassTag..MODULE$.apply(String.class));
   }

   public String GD() {
      return GD;
   }

   public String AdamW() {
      return AdamW;
   }

   public String[] supportedSolvers() {
      return supportedSolvers;
   }

   public String LogisticLoss() {
      return LogisticLoss;
   }

   public String SquaredError() {
      return SquaredError;
   }

   public String[] supportedRegressorLosses() {
      return supportedRegressorLosses;
   }

   public String[] supportedClassifierLosses() {
      return supportedClassifierLosses;
   }

   public String[] supportedLosses() {
      return supportedLosses;
   }

   public Updater parseSolver(final String solver, final int coefficientsSize) {
      String var10000 = this.GD();
      if (var10000 == null) {
         if (solver == null) {
            return new SquaredL2Updater();
         }
      } else if (var10000.equals(solver)) {
         return new SquaredL2Updater();
      }

      var10000 = this.AdamW();
      if (var10000 == null) {
         if (solver == null) {
            return new AdamWUpdater(coefficientsSize);
         }
      } else if (var10000.equals(solver)) {
         return new AdamWUpdater(coefficientsSize);
      }

      throw new MatchError(solver);
   }

   public BaseFactorizationMachinesGradient parseLoss(final String lossFunc, final int factorSize, final boolean fitIntercept, final boolean fitLinear, final int numFeatures) {
      String var10000 = this.LogisticLoss();
      if (var10000 == null) {
         if (lossFunc == null) {
            return new LogisticFactorizationMachinesGradient(factorSize, fitIntercept, fitLinear, numFeatures);
         }
      } else if (var10000.equals(lossFunc)) {
         return new LogisticFactorizationMachinesGradient(factorSize, fitIntercept, fitLinear, numFeatures);
      }

      var10000 = this.SquaredError();
      if (var10000 == null) {
         if (lossFunc == null) {
            return new MSEFactorizationMachinesGradient(factorSize, fitIntercept, fitLinear, numFeatures);
         }
      } else if (var10000.equals(lossFunc)) {
         return new MSEFactorizationMachinesGradient(factorSize, fitIntercept, fitLinear, numFeatures);
      }

      throw new IllegalArgumentException("loss function type " + lossFunc + " is invalidation");
   }

   public Tuple3 splitCoefficients(final Vector coefficients, final int numFeatures, final int factorSize, final boolean fitIntercept, final boolean fitLinear) {
      int coefficientsSize = numFeatures * factorSize + (fitLinear ? numFeatures : 0) + (fitIntercept ? 1 : 0);
      scala.Predef..MODULE$.require(coefficientsSize == coefficients.size(), () -> "coefficients.size did not match the excepted size " + coefficientsSize);
      double intercept = fitIntercept ? coefficients.apply(coefficients.size() - 1) : (double)0.0F;
      Vector linear = (Vector)(fitLinear ? new DenseVector((double[]).MODULE$.slice$extension(scala.Predef..MODULE$.doubleArrayOps(coefficients.toArray()), numFeatures * factorSize, numFeatures * factorSize + numFeatures)) : org.apache.spark.ml.linalg.Vectors..MODULE$.sparse(numFeatures, (Seq)scala.package..MODULE$.Seq().empty()));
      DenseMatrix factors = new DenseMatrix(numFeatures, factorSize, (double[]).MODULE$.slice$extension(scala.Predef..MODULE$.doubleArrayOps(coefficients.toArray()), 0, numFeatures * factorSize), true);
      return new Tuple3(BoxesRunTime.boxToDouble(intercept), linear, factors);
   }

   public Vector combineCoefficients(final double intercept, final Vector linear, final Matrix factors, final boolean fitIntercept, final boolean fitLinear) {
      double[] coefficients = (double[]).MODULE$.$plus$plus$extension(scala.Predef..MODULE$.doubleArrayOps((double[]).MODULE$.$plus$plus$extension(scala.Predef..MODULE$.doubleArrayOps(factors.toDense().values()), fitLinear ? linear.toArray() : scala.Array..MODULE$.emptyDoubleArray(), scala.reflect.ClassTag..MODULE$.Double())), fitIntercept ? new double[]{intercept} : scala.Array..MODULE$.emptyDoubleArray(), scala.reflect.ClassTag..MODULE$.Double());
      return new DenseVector(coefficients);
   }

   public double getRawPrediction(final Vector features, final double intercept, final Vector linear, final Matrix factors) {
      DoubleRef rawPrediction = DoubleRef.create(intercept + features.dot(linear));
      scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), factors.numCols()).foreach$mVc$sp((JFunction1.mcVI.sp)(f) -> {
         DoubleRef sumSquare = DoubleRef.create((double)0.0F);
         DoubleRef sum = DoubleRef.create((double)0.0F);
         features.foreachNonZero((JFunction2.mcVID.sp)(x0$1, x1$1) -> {
            Tuple2.mcID.sp var8 = new Tuple2.mcID.sp(x0$1, x1$1);
            if (var8 != null) {
               int index = ((Tuple2)var8)._1$mcI$sp();
               double value = ((Tuple2)var8)._2$mcD$sp();
               double vx = factors.apply(index, f) * value;
               sumSquare.elem += vx * vx;
               sum.elem += vx;
               BoxedUnit var10000 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(var8);
            }
         });
         rawPrediction.elem += (double)0.5F * (sum.elem * sum.elem - sumSquare.elem);
      });
      return rawPrediction.elem;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(FactorizationMachines$.class);
   }

   private FactorizationMachines$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
