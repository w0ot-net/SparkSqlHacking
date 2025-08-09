package org.apache.spark;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.rdd.RDD;
import org.apache.spark.util.random.SamplingUtils$;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.mutable.ArrayBuffer;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ClassTag.;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class RangePartitioner$ implements Serializable {
   public static final RangePartitioner$ MODULE$ = new RangePartitioner$();

   public boolean $lessinit$greater$default$3() {
      return true;
   }

   public int $lessinit$greater$default$4() {
      return 20;
   }

   public Tuple2 sketch(final RDD rdd, final int sampleSizePerPartition, final ClassTag evidence$5) {
      int shift = rdd.id();
      Tuple3[] sketched = (Tuple3[])rdd.mapPartitionsWithIndex((idx, iter) -> $anonfun$sketch$1(shift, sampleSizePerPartition, evidence$5, BoxesRunTime.unboxToInt(idx), iter), rdd.mapPartitionsWithIndex$default$2(), .MODULE$.apply(Tuple3.class)).collect();
      long numItems = BoxesRunTime.unboxToLong(scala.Predef..MODULE$.wrapLongArray((long[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])sketched), (x$10) -> BoxesRunTime.boxToLong($anonfun$sketch$2(x$10)), .MODULE$.Long())).sum(scala.math.Numeric.LongIsIntegral..MODULE$));
      return new Tuple2(BoxesRunTime.boxToLong(numItems), sketched);
   }

   public Object determineBounds(final ArrayBuffer candidates, final int partitions, final Ordering evidence$6, final ClassTag evidence$7) {
      Ordering ordering = (Ordering)scala.Predef..MODULE$.implicitly(evidence$6);
      ArrayBuffer ordered = (ArrayBuffer)candidates.sortBy((x$11) -> x$11._1(), evidence$6);
      int numCandidates = ordered.size();
      double sumWeights = BoxesRunTime.unboxToDouble(((IterableOnceOps)ordered.map((x$12) -> BoxesRunTime.boxToDouble($anonfun$determineBounds$2(x$12)))).sum(scala.math.Numeric.DoubleIsFractional..MODULE$));
      double step = sumWeights / (double)partitions;
      double cumWeight = (double)0.0F;
      double target = step;
      ArrayBuffer bounds = scala.collection.mutable.ArrayBuffer..MODULE$.empty();
      int i = 0;
      int j = 0;

      for(Option previousBound = scala.Option..MODULE$.empty(); i < numCandidates && j < partitions - 1; ++i) {
         Tuple2 var22 = (Tuple2)ordered.apply(i);
         if (var22 == null) {
            throw new MatchError(var22);
         }

         Object key = var22._1();
         float weight = BoxesRunTime.unboxToFloat(var22._2());
         Tuple2 var21 = new Tuple2(key, BoxesRunTime.boxToFloat(weight));
         Object key = var21._1();
         float weight = BoxesRunTime.unboxToFloat(var21._2());
         cumWeight += (double)weight;
         if (cumWeight >= target && (previousBound.isEmpty() || ordering.gt(key, previousBound.get()))) {
            bounds.$plus$eq(key);
            target += step;
            ++j;
            previousBound = new Some(key);
         }
      }

      return bounds.toArray(evidence$7);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(RangePartitioner$.class);
   }

   // $FF: synthetic method
   public static final Iterator $anonfun$sketch$1(final int shift$1, final int sampleSizePerPartition$2, final ClassTag evidence$5$1, final int idx, final Iterator iter) {
      int seed = scala.util.hashing.package..MODULE$.byteswap32(idx ^ shift$1 << 16);
      Tuple2 var8 = SamplingUtils$.MODULE$.reservoirSampleAndCount(iter, sampleSizePerPartition$2, (long)seed, evidence$5$1);
      if (var8 != null) {
         Object sample = var8._1();
         long n = var8._2$mcJ$sp();
         Tuple2 var7 = new Tuple2(sample, BoxesRunTime.boxToLong(n));
         Object sample = var7._1();
         long n = var7._2$mcJ$sp();
         return scala.package..MODULE$.Iterator().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple3[]{new Tuple3(BoxesRunTime.boxToInteger(idx), BoxesRunTime.boxToLong(n), sample)})));
      } else {
         throw new MatchError(var8);
      }
   }

   // $FF: synthetic method
   public static final long $anonfun$sketch$2(final Tuple3 x$10) {
      return BoxesRunTime.unboxToLong(x$10._2());
   }

   // $FF: synthetic method
   public static final double $anonfun$determineBounds$2(final Tuple2 x$12) {
      return (double)BoxesRunTime.unboxToFloat(x$12._2());
   }

   private RangePartitioner$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
