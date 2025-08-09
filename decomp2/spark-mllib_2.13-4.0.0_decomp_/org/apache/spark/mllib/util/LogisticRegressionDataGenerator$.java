package org.apache.spark.mllib.util;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkContext;
import org.apache.spark.mllib.linalg.Vectors$;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.apache.spark.rdd.RDD;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichInt.;
import scala.runtime.java8.JFunction0;
import scala.util.Random;

public final class LogisticRegressionDataGenerator$ {
   public static final LogisticRegressionDataGenerator$ MODULE$ = new LogisticRegressionDataGenerator$();

   public RDD generateLogisticRDD(final SparkContext sc, final int nexamples, final int nfeatures, final double eps, final int nparts, final double probOne) {
      RDD data = sc.parallelize(.MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), nexamples), nparts, scala.reflect.ClassTag..MODULE$.Int()).map((idx) -> $anonfun$generateLogisticRDD$1(nfeatures, eps, BoxesRunTime.unboxToInt(idx)), scala.reflect.ClassTag..MODULE$.apply(LabeledPoint.class));
      return data;
   }

   public int generateLogisticRDD$default$5() {
      return 2;
   }

   public double generateLogisticRDD$default$6() {
      return (double)0.5F;
   }

   public void main(final String[] args) {
      if (args.length != 5) {
         scala.Predef..MODULE$.println("Usage: LogisticRegressionGenerator <master> <output_dir> <num_examples> <num_features> <num_partitions>");
         System.exit(1);
      }

      String sparkMaster = args[0];
      String outputPath = args[1];
      int nexamples = args.length > 2 ? scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(args[2])) : 1000;
      int nfeatures = args.length > 3 ? scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(args[3])) : 2;
      int parts = args.length > 4 ? scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(args[4])) : 2;
      int eps = 3;
      SparkContext sc = new SparkContext(sparkMaster, "LogisticRegressionDataGenerator");
      RDD data = this.generateLogisticRDD(sc, nexamples, nfeatures, (double)eps, parts, this.generateLogisticRDD$default$6());
      data.saveAsTextFile(outputPath);
      sc.stop();
   }

   // $FF: synthetic method
   public static final LabeledPoint $anonfun$generateLogisticRDD$1(final int nfeatures$1, final double eps$1, final int idx) {
      Random rnd = new Random(42 + idx);
      double y = idx % 2 == 0 ? (double)0.0F : (double)1.0F;
      double[] x = (double[])scala.Array..MODULE$.fill(nfeatures$1, (JFunction0.mcD.sp)() -> rnd.nextGaussian() + y * eps$1, scala.reflect.ClassTag..MODULE$.Double());
      return new LabeledPoint(y, Vectors$.MODULE$.dense(x));
   }

   private LogisticRegressionDataGenerator$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
