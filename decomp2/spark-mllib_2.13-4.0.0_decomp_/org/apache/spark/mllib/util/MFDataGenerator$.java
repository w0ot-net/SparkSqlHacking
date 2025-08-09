package org.apache.spark.mllib.util;

import java.lang.invoke.SerializedLambda;
import java.util.Random;
import org.apache.spark.SparkContext;
import org.apache.spark.mllib.linalg.BLAS$;
import org.apache.spark.mllib.linalg.DenseMatrix;
import org.apache.spark.mllib.linalg.DenseMatrix$;
import org.apache.spark.mllib.linalg.Matrix;
import org.apache.spark.rdd.RDD;
import scala.Tuple3;
import scala.Predef.;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.List;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction2;

public final class MFDataGenerator$ {
   public static final MFDataGenerator$ MODULE$ = new MFDataGenerator$();

   public void main(final String[] args) {
      if (args.length < 2) {
         .MODULE$.println("Usage: MFDataGenerator <master> <outputDir> [m] [n] [rank] [trainSampFact] [noise] [sigma] [test] [testSampFact]");
         System.exit(1);
      }

      String sparkMaster = args[0];
      String outputPath = args[1];
      int m = args.length > 2 ? scala.collection.StringOps..MODULE$.toInt$extension(.MODULE$.augmentString(args[2])) : 100;
      int n = args.length > 3 ? scala.collection.StringOps..MODULE$.toInt$extension(.MODULE$.augmentString(args[3])) : 100;
      int rank = args.length > 4 ? scala.collection.StringOps..MODULE$.toInt$extension(.MODULE$.augmentString(args[4])) : 10;
      double trainSampFact = args.length > 5 ? scala.collection.StringOps..MODULE$.toDouble$extension(.MODULE$.augmentString(args[5])) : (double)1.0F;
      boolean noise = args.length > 6 ? scala.collection.StringOps..MODULE$.toBoolean$extension(.MODULE$.augmentString(args[6])) : false;
      double sigma = args.length > 7 ? scala.collection.StringOps..MODULE$.toDouble$extension(.MODULE$.augmentString(args[7])) : 0.1;
      boolean test = args.length > 8 ? scala.collection.StringOps..MODULE$.toBoolean$extension(.MODULE$.augmentString(args[8])) : false;
      double testSampFact = args.length > 9 ? scala.collection.StringOps..MODULE$.toDouble$extension(.MODULE$.augmentString(args[9])) : 0.1;
      SparkContext sc = new SparkContext(sparkMaster, "MFDataGenerator");
      Random random = new Random(42L);
      DenseMatrix A = DenseMatrix$.MODULE$.randn(m, rank, random);
      DenseMatrix B = DenseMatrix$.MODULE$.randn(rank, n, random);
      double z = (double)1 / scala.math.package..MODULE$.sqrt((double)rank);
      DenseMatrix fullData = DenseMatrix$.MODULE$.zeros(m, n);
      BLAS$.MODULE$.gemm(z, (Matrix)A, B, (double)1.0F, fullData);
      int df = rank * (m + n - rank);
      int sampSize = (int)scala.math.package..MODULE$.min(scala.math.package..MODULE$.round(trainSampFact * (double)df), scala.math.package..MODULE$.round(0.99 * (double)m * (double)n));
      scala.util.Random rand = new scala.util.Random();
      int mn = m * n;
      List shuffled = (List)rand.shuffle(scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), mn).toList(), scala.collection.BuildFrom..MODULE$.buildFromIterableOps());
      List omega = shuffled.slice(0, sampSize);
      int[] ordered = (int[])((IterableOnceOps)omega.sortWith((JFunction2.mcZII.sp)(x$1, x$2) -> x$1 < x$2)).toArray(scala.reflect.ClassTag..MODULE$.Int());
      RDD trainData = sc.parallelize(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(ordered).toImmutableArraySeq(), sc.parallelize$default$2(), scala.reflect.ClassTag..MODULE$.Int()).map((x) -> $anonfun$main$2(m, fullData, BoxesRunTime.unboxToInt(x)), scala.reflect.ClassTag..MODULE$.apply(Tuple3.class));
      if (noise) {
         trainData.map((x) -> new Tuple3(x._1(), x._2(), BoxesRunTime.boxToDouble(BoxesRunTime.unboxToDouble(x._3()) + rand.nextGaussian() * sigma)), scala.reflect.ClassTag..MODULE$.apply(Tuple3.class));
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      trainData.map((x) -> {
         Object var10000 = x._1();
         return var10000 + "," + x._2() + "," + x._3();
      }, scala.reflect.ClassTag..MODULE$.apply(String.class)).saveAsTextFile(outputPath);
      if (test) {
         int testSampSize = scala.math.package..MODULE$.min((int)scala.math.package..MODULE$.round((double)sampSize * testSampFact), mn - sampSize);
         List testOmega = shuffled.slice(sampSize, sampSize + testSampSize);
         int[] testOrdered = (int[])((IterableOnceOps)testOmega.sortWith((JFunction2.mcZII.sp)(x$3, x$4) -> x$3 < x$4)).toArray(scala.reflect.ClassTag..MODULE$.Int());
         RDD testData = sc.parallelize(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(testOrdered).toImmutableArraySeq(), sc.parallelize$default$2(), scala.reflect.ClassTag..MODULE$.Int()).map((x) -> $anonfun$main$6(m, fullData, BoxesRunTime.unboxToInt(x)), scala.reflect.ClassTag..MODULE$.apply(Tuple3.class));
         testData.map((x) -> {
            Object var10000 = x._1();
            return var10000 + "," + x._2() + "," + x._3();
         }, scala.reflect.ClassTag..MODULE$.apply(String.class)).saveAsTextFile(outputPath);
      }

      sc.stop();
   }

   // $FF: synthetic method
   public static final Tuple3 $anonfun$main$2(final int m$1, final DenseMatrix fullData$1, final int x) {
      return new Tuple3(BoxesRunTime.boxToInteger(x % m$1), BoxesRunTime.boxToInteger(x / m$1), BoxesRunTime.boxToDouble(fullData$1.values()[x]));
   }

   // $FF: synthetic method
   public static final Tuple3 $anonfun$main$6(final int m$1, final DenseMatrix fullData$1, final int x) {
      return new Tuple3(BoxesRunTime.boxToInteger(x % m$1), BoxesRunTime.boxToInteger(x / m$1), BoxesRunTime.boxToDouble(fullData$1.values()[x]));
   }

   private MFDataGenerator$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
