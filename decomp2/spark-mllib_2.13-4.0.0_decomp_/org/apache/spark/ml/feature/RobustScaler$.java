package org.apache.spark.ml.feature;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.util.DefaultParamsReadable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.rdd.RDD;
import org.apache.spark.rdd.RDD.;
import org.apache.spark.sql.catalyst.util.QuantileSummaries;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.java8.JFunction2;

public final class RobustScaler$ implements DefaultParamsReadable, Serializable {
   public static final RobustScaler$ MODULE$ = new RobustScaler$();

   static {
      MLReadable.$init$(MODULE$);
      DefaultParamsReadable.$init$(MODULE$);
   }

   public MLReader read() {
      return DefaultParamsReadable.read$(this);
   }

   public RDD computeSummaries(final RDD vectors, final int numFeatures, final double relativeError) {
      if (numFeatures <= 1000) {
         return .MODULE$.rddToPairRDDFunctions(vectors.mapPartitions((iter) -> {
            if (!iter.hasNext()) {
               return scala.package..MODULE$.Iterator().empty();
            } else {
               QuantileSummaries[] summaries = (QuantileSummaries[])scala.Array..MODULE$.fill(numFeatures, () -> new QuantileSummaries(org.apache.spark.sql.catalyst.util.QuantileSummaries..MODULE$.defaultCompressThreshold(), relativeError, org.apache.spark.sql.catalyst.util.QuantileSummaries..MODULE$.$lessinit$greater$default$3(), org.apache.spark.sql.catalyst.util.QuantileSummaries..MODULE$.$lessinit$greater$default$4(), org.apache.spark.sql.catalyst.util.QuantileSummaries..MODULE$.$lessinit$greater$default$5()), scala.reflect.ClassTag..MODULE$.apply(QuantileSummaries.class));

               while(iter.hasNext()) {
                  Vector vec = (Vector)iter.next();
                  vec.foreach((JFunction2.mcVID.sp)(i, v) -> {
                     if (!Double.isNaN(v)) {
                        summaries[i] = summaries[i].insert(v);
                     }
                  });
               }

               return scala.package..MODULE$.Iterator().tabulate(numFeatures, (i) -> $anonfun$computeSummaries$4(summaries, BoxesRunTime.unboxToInt(i)));
            }
         }, vectors.mapPartitions$default$2(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)), scala.reflect.ClassTag..MODULE$.Int(), scala.reflect.ClassTag..MODULE$.apply(QuantileSummaries.class), scala.math.Ordering.Int..MODULE$).reduceByKey((s1, s2) -> s1.merge(s2));
      } else {
         int scale = scala.math.package..MODULE$.max((int)scala.math.package..MODULE$.ceil(scala.math.package..MODULE$.sqrt((double)vectors.getNumPartitions())), 2);
         return .MODULE$.rddToPairRDDFunctions(.MODULE$.rddToPairRDDFunctions(vectors.mapPartitionsWithIndex((x0$1, x1$1) -> $anonfun$computeSummaries$6(scale, numFeatures, BoxesRunTime.unboxToInt(x0$1), x1$1), vectors.mapPartitionsWithIndex$default$2(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class), scala.reflect.ClassTag..MODULE$.Double(), scala.math.Ordering..MODULE$.Tuple2(scala.math.Ordering.Int..MODULE$, scala.math.Ordering.Int..MODULE$)).aggregateByKey(new QuantileSummaries(org.apache.spark.sql.catalyst.util.QuantileSummaries..MODULE$.defaultCompressThreshold(), relativeError, org.apache.spark.sql.catalyst.util.QuantileSummaries..MODULE$.$lessinit$greater$default$3(), org.apache.spark.sql.catalyst.util.QuantileSummaries..MODULE$.$lessinit$greater$default$4(), org.apache.spark.sql.catalyst.util.QuantileSummaries..MODULE$.$lessinit$greater$default$5()), (s, v) -> $anonfun$computeSummaries$10(s, BoxesRunTime.unboxToDouble(v)), (s1, s2) -> s1.compress().merge(s2.compress()), scala.reflect.ClassTag..MODULE$.apply(QuantileSummaries.class)).map((x0$2) -> {
            if (x0$2 != null) {
               Tuple2 var3 = (Tuple2)x0$2._1();
               QuantileSummaries s = (QuantileSummaries)x0$2._2();
               if (var3 != null) {
                  int i = var3._2$mcI$sp();
                  return new Tuple2(BoxesRunTime.boxToInteger(i), s);
               }
            }

            throw new MatchError(x0$2);
         }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)), scala.reflect.ClassTag..MODULE$.Int(), scala.reflect.ClassTag..MODULE$.apply(QuantileSummaries.class), scala.math.Ordering.Int..MODULE$).reduceByKey((s1, s2) -> s1.compress().merge(s2.compress()));
      }
   }

   public RobustScaler load(final String path) {
      return (RobustScaler)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(RobustScaler$.class);
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$computeSummaries$4(final QuantileSummaries[] summaries$1, final int i) {
      return new Tuple2(BoxesRunTime.boxToInteger(i), summaries$1[i].compress());
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$computeSummaries$8(final int p$1, final Vector vec$1, final int i) {
      return new Tuple2(new Tuple2.mcII.sp(p$1, i), BoxesRunTime.boxToDouble(vec$1.apply(i)));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$computeSummaries$9(final Tuple2 x$4) {
      return !Double.isNaN(x$4._2$mcD$sp());
   }

   // $FF: synthetic method
   public static final Iterator $anonfun$computeSummaries$6(final int scale$1, final int numFeatures$2, final int x0$1, final Iterator x1$1) {
      Tuple2 var5 = new Tuple2(BoxesRunTime.boxToInteger(x0$1), x1$1);
      if (var5 != null) {
         int pid = var5._1$mcI$sp();
         Iterator iter = (Iterator)var5._2();
         int p = pid % scale$1;
         return iter.flatMap((vec) -> scala.package..MODULE$.Iterator().tabulate(numFeatures$2, (i) -> $anonfun$computeSummaries$8(p, vec, BoxesRunTime.unboxToInt(i)))).filter((x$4) -> BoxesRunTime.boxToBoolean($anonfun$computeSummaries$9(x$4)));
      } else {
         throw new MatchError(var5);
      }
   }

   // $FF: synthetic method
   public static final QuantileSummaries $anonfun$computeSummaries$10(final QuantileSummaries s, final double v) {
      return s.insert(v);
   }

   private RobustScaler$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
