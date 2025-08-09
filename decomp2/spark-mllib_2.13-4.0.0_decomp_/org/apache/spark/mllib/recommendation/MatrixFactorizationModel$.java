package org.apache.spark.mllib.recommendation;

import java.io.IOException;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkContext;
import org.apache.spark.mllib.linalg.BLAS$;
import org.apache.spark.mllib.rdd.MLPairRDDFunctions$;
import org.apache.spark.mllib.util.Loader;
import org.apache.spark.mllib.util.Loader$;
import org.apache.spark.rdd.RDD;
import org.sparkproject.guava.collect.Ordering;
import scala.Function1;
import scala.MatchError;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.reflect.ClassTag.;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.ObjectRef;

public final class MatrixFactorizationModel$ implements Loader, Serializable {
   public static final MatrixFactorizationModel$ MODULE$ = new MatrixFactorizationModel$();

   public Tuple2[] org$apache$spark$mllib$recommendation$MatrixFactorizationModel$$recommend(final double[] recommendToFeatures, final RDD recommendableFeatures, final int num) {
      RDD scored = recommendableFeatures.map((x0$1) -> {
         if (x0$1 != null) {
            int id = x0$1._1$mcI$sp();
            double[] features = (double[])x0$1._2();
            return new Tuple2.mcID.sp(id, BLAS$.MODULE$.nativeBLAS().ddot(features.length, recommendToFeatures, 1, features, 1));
         } else {
            throw new MatchError(x0$1);
         }
      }, .MODULE$.apply(Tuple2.class));
      return (Tuple2[])scored.top(num, scala.package..MODULE$.Ordering().by((x$3) -> BoxesRunTime.boxToDouble($anonfun$recommend$2(x$3)), scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$));
   }

   public RDD org$apache$spark$mllib$recommendation$MatrixFactorizationModel$$recommendForAll(final int rank, final RDD srcFeatures, final RDD dstFeatures, final int num) {
      RDD srcBlocks = this.blockify(srcFeatures, this.blockify$default$2());
      RDD dstBlocks = this.blockify(dstFeatures, this.blockify$default$2());
      RDD qual$1 = srcBlocks.cartesian(dstBlocks, .MODULE$.apply(Tuple2.class));
      Function1 x$1 = (iter) -> {
         ObjectRef scores = ObjectRef.create((Object)null);
         ObjectRef idxOrd = ObjectRef.create((Object)null);
         return iter.flatMap((x0$1) -> {
            if (x0$1 != null) {
               Tuple2 var7 = (Tuple2)x0$1._1();
               Tuple2 var8 = (Tuple2)x0$1._2();
               if (var7 != null) {
                  int[] srcIds = (int[])var7._1();
                  double[] srcMat = (double[])var7._2();
                  if (var8 != null) {
                     int[] dstIds = (int[])var8._1();
                     double[] dstMat = (double[])var8._2();
                     scala.Predef..MODULE$.require(srcMat.length == srcIds.length * rank);
                     scala.Predef..MODULE$.require(dstMat.length == dstIds.length * rank);
                     int m = srcIds.length;
                     int n = dstIds.length;
                     if ((double[])scores.elem == null || ((double[])scores.elem).length < n) {
                        scores.elem = (double[])scala.Array..MODULE$.ofDim(n, .MODULE$.Double());
                        idxOrd.elem = new Ordering(scores) {
                           private final ObjectRef scores$1;

                           public int compare(final int left, final int right) {
                              return scala.package..MODULE$.Ordering().apply(scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$).compare(BoxesRunTime.boxToDouble(((double[])this.scores$1.elem)[left]), BoxesRunTime.boxToDouble(((double[])this.scores$1.elem)[right]));
                           }

                           public {
                              this.scores$1 = scores$1;
                           }
                        };
                     }

                     return scala.package..MODULE$.Iterator().range(0, m).flatMap((i) -> $anonfun$recommendForAll$3(rank, n, dstMat, srcMat, scores, srcIds, idxOrd, num, dstIds, BoxesRunTime.unboxToInt(i)));
                  }
               }
            }

            throw new MatchError(x0$1);
         });
      };
      boolean x$2 = qual$1.mapPartitions$default$2();
      RDD ratings = qual$1.mapPartitions(x$1, x$2, .MODULE$.apply(Tuple2.class));
      return MLPairRDDFunctions$.MODULE$.fromPairRDD(ratings, .MODULE$.Int(), .MODULE$.apply(Tuple2.class)).topByKey(num, scala.package..MODULE$.Ordering().by((x$4) -> BoxesRunTime.boxToDouble($anonfun$recommendForAll$5(x$4)), scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$));
   }

   private RDD blockify(final RDD features, final int blockSize) {
      return features.mapPartitions((iter) -> iter.grouped(blockSize).map((block) -> new Tuple2(((IterableOnceOps)block.map((x$5) -> BoxesRunTime.boxToInteger($anonfun$blockify$3(x$5)))).toArray(.MODULE$.Int()), ((IterableOnceOps)block.flatMap((x$6) -> scala.Predef..MODULE$.wrapDoubleArray((double[])x$6._2()))).toArray(.MODULE$.Double()))), features.mapPartitions$default$2(), .MODULE$.apply(Tuple2.class));
   }

   private int blockify$default$2() {
      return 4096;
   }

   public MatrixFactorizationModel load(final SparkContext sc, final String path) {
      Tuple3 var6 = Loader$.MODULE$.loadMetadata(sc, path);
      if (var6 == null) {
         throw new MatchError(var6);
      } else {
         String loadedClassName = (String)var6._1();
         String formatVersion = (String)var6._2();
         Tuple2 var5 = new Tuple2(loadedClassName, formatVersion);
         String loadedClassName = (String)var5._1();
         String formatVersion = (String)var5._2();
         String classNameV1_0 = MatrixFactorizationModel.SaveLoadV1_0$.MODULE$.thisClassName();
         Tuple2 var12 = new Tuple2(loadedClassName, formatVersion);
         if (var12 != null) {
            String className = (String)var12._1();
            String var14 = (String)var12._2();
            if ("1.0".equals(var14)) {
               if (className == null) {
                  if (classNameV1_0 == null) {
                     return MatrixFactorizationModel.SaveLoadV1_0$.MODULE$.load(sc, path);
                  }
               } else if (className.equals(classNameV1_0)) {
                  return MatrixFactorizationModel.SaveLoadV1_0$.MODULE$.load(sc, path);
               }
            }
         }

         throw new IOException("MatrixFactorizationModel.load did not recognize model with(class: " + loadedClassName + ", version: " + formatVersion + "). Supported:\n  (" + classNameV1_0 + ", 1.0)");
      }
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(MatrixFactorizationModel$.class);
   }

   // $FF: synthetic method
   public static final double $anonfun$recommend$2(final Tuple2 x$3) {
      return x$3._2$mcD$sp();
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$recommendForAll$4(final int srcId$1, final int[] dstIds$1, final ObjectRef scores$1, final int j) {
      return new Tuple2(BoxesRunTime.boxToInteger(srcId$1), new Tuple2.mcID.sp(dstIds$1[j], ((double[])scores$1.elem)[j]));
   }

   // $FF: synthetic method
   public static final Iterator $anonfun$recommendForAll$3(final int rank$1, final int n$1, final double[] dstMat$1, final double[] srcMat$1, final ObjectRef scores$1, final int[] srcIds$1, final ObjectRef idxOrd$1, final int num$1, final int[] dstIds$1, final int i) {
      BLAS$.MODULE$.javaBLAS().dgemv("T", rank$1, n$1, (double)1.0F, dstMat$1, 0, rank$1, srcMat$1, i * rank$1, 1, (double)0.0F, (double[])scores$1.elem, 0, 1);
      int srcId = srcIds$1[i];
      return scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(((Ordering)idxOrd$1.elem).greatestOf(scala.jdk.CollectionConverters..MODULE$.IteratorHasAsJava(scala.package..MODULE$.Iterator().range(0, n$1)).asJava(), num$1)).asScala().iterator().map((j) -> $anonfun$recommendForAll$4(srcId, dstIds$1, scores$1, BoxesRunTime.unboxToInt(j)));
   }

   // $FF: synthetic method
   public static final double $anonfun$recommendForAll$5(final Tuple2 x$4) {
      return x$4._2$mcD$sp();
   }

   // $FF: synthetic method
   public static final int $anonfun$blockify$3(final Tuple2 x$5) {
      return x$5._1$mcI$sp();
   }

   private MatrixFactorizationModel$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
