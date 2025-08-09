package org.apache.spark.mllib.util;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkContext;
import org.apache.spark.rdd.RDD;
import scala.Array.;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;
import scala.util.Random;

public final class KMeansDataGenerator$ {
   public static final KMeansDataGenerator$ MODULE$ = new KMeansDataGenerator$();

   public RDD generateKMeansRDD(final SparkContext sc, final int numPoints, final int k, final int d, final double r, final int numPartitions) {
      Random rand = new Random(42);
      double[][] centers = (double[][]).MODULE$.fill(k, () -> (double[]).MODULE$.fill(d, (JFunction0.mcD.sp)() -> rand.nextGaussian() * r, scala.reflect.ClassTag..MODULE$.Double()), scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Double.TYPE)));
      return sc.parallelize(scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), numPoints), numPartitions, scala.reflect.ClassTag..MODULE$.Int()).map((idx) -> $anonfun$generateKMeansRDD$3(centers, k, d, BoxesRunTime.unboxToInt(idx)), scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Double.TYPE)));
   }

   public int generateKMeansRDD$default$6() {
      return 2;
   }

   public void main(final String[] args) {
      if (args.length < 6) {
         scala.Predef..MODULE$.println("Usage: KMeansGenerator <master> <output_dir> <num_points> <k> <d> <r> [<num_partitions>]");
         System.exit(1);
      }

      String sparkMaster = args[0];
      String outputPath = args[1];
      int numPoints = scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(args[2]));
      int k = scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(args[3]));
      int d = scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(args[4]));
      double r = scala.collection.StringOps..MODULE$.toDouble$extension(scala.Predef..MODULE$.augmentString(args[5]));
      int parts = args.length >= 7 ? scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(args[6])) : 2;
      SparkContext sc = new SparkContext(sparkMaster, "KMeansDataGenerator");
      RDD data = this.generateKMeansRDD(sc, numPoints, k, d, r, parts);
      data.map((x$1) -> scala.Predef..MODULE$.wrapDoubleArray(x$1).mkString(" "), scala.reflect.ClassTag..MODULE$.apply(String.class)).saveAsTextFile(outputPath);
      sc.stop();
      System.exit(0);
   }

   // $FF: synthetic method
   public static final double[] $anonfun$generateKMeansRDD$3(final double[][] centers$1, final int k$1, final int d$1, final int idx) {
      double[] center = centers$1[idx % k$1];
      Random rand2 = new Random(42 + idx);
      return (double[]).MODULE$.tabulate(d$1, (JFunction1.mcDI.sp)(i) -> center[i] + rand2.nextGaussian(), scala.reflect.ClassTag..MODULE$.Double());
   }

   private KMeansDataGenerator$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
