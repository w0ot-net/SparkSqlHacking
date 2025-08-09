package org.apache.spark.mllib.util;

import java.lang.invoke.SerializedLambda;
import java.util.List;
import org.apache.spark.SparkContext;
import org.apache.spark.mllib.linalg.BLAS$;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors$;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.apache.spark.rdd.RDD;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.Seq;
import scala.jdk.CollectionConverters.;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;
import scala.util.Random;

public final class LinearDataGenerator$ {
   public static final LinearDataGenerator$ MODULE$ = new LinearDataGenerator$();

   public List generateLinearInputAsList(final double intercept, final double[] weights, final int nPoints, final int seed, final double eps) {
      return .MODULE$.SeqHasAsJava(this.generateLinearInput(intercept, weights, nPoints, seed, eps)).asJava();
   }

   public Seq generateLinearInput(final double intercept, final double[] weights, final int nPoints, final int seed, final double eps) {
      return this.generateLinearInput(intercept, weights, (double[])scala.Array..MODULE$.ofDim(weights.length, scala.reflect.ClassTag..MODULE$.Double()), (double[])scala.Array..MODULE$.fill(weights.length, (JFunction0.mcD.sp)() -> 0.3333333333333333, scala.reflect.ClassTag..MODULE$.Double()), nPoints, seed, eps);
   }

   public Seq generateLinearInput(final double intercept, final double[] weights, final double[] xMean, final double[] xVariance, final int nPoints, final int seed, final double eps) {
      return this.generateLinearInput(intercept, weights, xMean, xVariance, nPoints, seed, eps, (double)0.0F);
   }

   public Seq generateLinearInput(final double intercept, final double[] weights, final double[] xMean, final double[] xVariance, final int nPoints, final int seed, final double eps, final double sparsity) {
      scala.Predef..MODULE$.require((double)0.0F <= sparsity && sparsity <= (double)1.0F);
      Random rnd = new Random(seed);
      return sparsity == (double)0.0F ? scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), nPoints).map((x$1) -> $anonfun$generateLinearInput$2(weights, intercept, eps, rnd, xVariance, xMean, BoxesRunTime.unboxToInt(x$1))) : scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), nPoints).map((x$3) -> $anonfun$generateLinearInput$4(weights, rnd, sparsity, intercept, eps, xVariance, xMean, BoxesRunTime.unboxToInt(x$3)));
   }

   public double generateLinearInput$default$5() {
      return 0.1;
   }

   public RDD generateLinearRDD(final SparkContext sc, final int nexamples, final int nfeatures, final double eps, final int nparts, final double intercept) {
      Random random = new Random(42);
      double[] w = (double[])scala.Array..MODULE$.fill(nfeatures, (JFunction0.mcD.sp)() -> random.nextDouble() - (double)0.5F, scala.reflect.ClassTag..MODULE$.Double());
      RDD data = sc.parallelize(scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), nparts), nparts, scala.reflect.ClassTag..MODULE$.Int()).flatMap((p) -> $anonfun$generateLinearRDD$2(nexamples, nparts, intercept, w, eps, BoxesRunTime.unboxToInt(p)), scala.reflect.ClassTag..MODULE$.apply(LabeledPoint.class));
      return data;
   }

   public int generateLinearRDD$default$5() {
      return 2;
   }

   public double generateLinearRDD$default$6() {
      return (double)0.0F;
   }

   public void main(final String[] args) {
      if (args.length < 2) {
         scala.Predef..MODULE$.println("Usage: LinearDataGenerator <master> <output_dir> [num_examples] [num_features] [num_partitions]");
         System.exit(1);
      }

      String sparkMaster = args[0];
      String outputPath = args[1];
      int nexamples = args.length > 2 ? scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(args[2])) : 1000;
      int nfeatures = args.length > 3 ? scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(args[3])) : 100;
      int parts = args.length > 4 ? scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(args[4])) : 2;
      int eps = 10;
      SparkContext sc = new SparkContext(sparkMaster, "LinearDataGenerator");
      RDD data = this.generateLinearRDD(sc, nexamples, nfeatures, (double)eps, parts, this.generateLinearRDD$default$6());
      data.saveAsTextFile(outputPath);
      sc.stop();
   }

   private static final double rndElement$1(final int i, final Random rnd$1, final double[] xVariance$1, final double[] xMean$1) {
      return (rnd$1.nextDouble() - (double)0.5F) * scala.math.package..MODULE$.sqrt((double)12.0F * xVariance$1[i]) + xMean$1[i];
   }

   // $FF: synthetic method
   public static final LabeledPoint $anonfun$generateLinearInput$2(final double[] weights$1, final double intercept$1, final double eps$1, final Random rnd$1, final double[] xVariance$1, final double[] xMean$1, final int x$1) {
      Vector features = Vectors$.MODULE$.dense((double[])scala.collection.ArrayOps..MODULE$.indices$extension(scala.Predef..MODULE$.doubleArrayOps(weights$1)).map((JFunction1.mcDI.sp)(x$2) -> rndElement$1(x$2, rnd$1, xVariance$1, xMean$1)).toArray(scala.reflect.ClassTag..MODULE$.Double()));
      double label = BLAS$.MODULE$.dot(Vectors$.MODULE$.dense(weights$1), features) + intercept$1 + eps$1 * rnd$1.nextGaussian();
      return new LabeledPoint(label, features);
   }

   // $FF: synthetic method
   public static final LabeledPoint $anonfun$generateLinearInput$4(final double[] weights$1, final Random rnd$1, final double sparsity$1, final double intercept$1, final double eps$1, final double[] xVariance$1, final double[] xMean$1, final int x$3) {
      IndexedSeq indices = (IndexedSeq)scala.collection.ArrayOps..MODULE$.indices$extension(scala.Predef..MODULE$.doubleArrayOps(weights$1)).filter((JFunction1.mcZI.sp)(x$4) -> rnd$1.nextDouble() <= sparsity$1);
      IndexedSeq values = (IndexedSeq)indices.map((JFunction1.mcDI.sp)(x$5) -> rndElement$1(x$5, rnd$1, xVariance$1, xMean$1));
      Vector features = Vectors$.MODULE$.sparse(weights$1.length, (int[])indices.toArray(scala.reflect.ClassTag..MODULE$.Int()), (double[])values.toArray(scala.reflect.ClassTag..MODULE$.Double()));
      double label = BLAS$.MODULE$.dot(Vectors$.MODULE$.dense(weights$1), features) + intercept$1 + eps$1 * rnd$1.nextGaussian();
      return new LabeledPoint(label, features);
   }

   // $FF: synthetic method
   public static final Seq $anonfun$generateLinearRDD$2(final int nexamples$1, final int nparts$1, final double intercept$2, final double[] w$1, final double eps$2, final int p) {
      int seed = 42 + p;
      int examplesInPartition = nexamples$1 / nparts$1;
      return MODULE$.generateLinearInput(intercept$2, w$1, examplesInPartition, seed, eps$2);
   }

   private LinearDataGenerator$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
