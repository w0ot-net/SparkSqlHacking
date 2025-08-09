package org.apache.spark.mllib.util;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkContext;
import org.apache.spark.mllib.linalg.Vectors$;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.apache.spark.rdd.RDD;
import scala.Predef.;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;
import scala.util.Random;

public final class SVMDataGenerator$ {
   public static final SVMDataGenerator$ MODULE$ = new SVMDataGenerator$();

   public void main(final String[] args) {
      if (args.length < 2) {
         .MODULE$.println("Usage: SVMGenerator <master> <output_dir> [num_examples] [num_features] [num_partitions]");
         System.exit(1);
      }

      String sparkMaster = args[0];
      String outputPath = args[1];
      int nexamples = args.length > 2 ? scala.collection.StringOps..MODULE$.toInt$extension(.MODULE$.augmentString(args[2])) : 1000;
      int nfeatures = args.length > 3 ? scala.collection.StringOps..MODULE$.toInt$extension(.MODULE$.augmentString(args[3])) : 2;
      int parts = args.length > 4 ? scala.collection.StringOps..MODULE$.toInt$extension(.MODULE$.augmentString(args[4])) : 2;
      SparkContext sc = new SparkContext(sparkMaster, "SVMGenerator");
      Random globalRnd = new Random(94720);
      double[] trueWeights = (double[])scala.Array..MODULE$.fill(nfeatures, (JFunction0.mcD.sp)() -> globalRnd.nextGaussian(), scala.reflect.ClassTag..MODULE$.Double());
      RDD data = sc.parallelize(scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), nexamples), parts, scala.reflect.ClassTag..MODULE$.Int()).map((idx) -> $anonfun$main$2(nfeatures, trueWeights, BoxesRunTime.unboxToInt(idx)), scala.reflect.ClassTag..MODULE$.apply(LabeledPoint.class));
      data.saveAsTextFile(outputPath);
      sc.stop();
   }

   // $FF: synthetic method
   public static final LabeledPoint $anonfun$main$2(final int nfeatures$1, final double[] trueWeights$1, final int idx) {
      Random rnd = new Random(42 + idx);
      double[] x = (double[])scala.Array..MODULE$.fill(nfeatures$1, (JFunction0.mcD.sp)() -> rnd.nextDouble() * (double)2.0F - (double)1.0F, scala.reflect.ClassTag..MODULE$.Double());
      double yD = org.apache.spark.ml.linalg.BLAS..MODULE$.nativeBLAS().ddot(trueWeights$1.length, x, 1, trueWeights$1, 1);
      double var10000 = rnd.nextGaussian() * 0.1;
      double y = yD < (double)0 ? (double)0.0F : (double)1.0F;
      return new LabeledPoint(y, Vectors$.MODULE$.dense(x));
   }

   private SVMDataGenerator$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
