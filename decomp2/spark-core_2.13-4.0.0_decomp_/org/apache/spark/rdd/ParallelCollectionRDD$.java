package org.apache.spark.rdd;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.immutable.NumericRange;
import scala.collection.immutable.Range;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArrayBuffer;
import scala.reflect.ClassTag;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.ObjectRef;
import scala.runtime.ScalaRunTime.;

public final class ParallelCollectionRDD$ implements Serializable {
   public static final ParallelCollectionRDD$ MODULE$ = new ParallelCollectionRDD$();

   public Seq slice(final Seq seq, final int numSlices, final ClassTag evidence$3) {
      if (numSlices < 1) {
         throw new IllegalArgumentException("Positive number of partitions required");
      } else if (seq instanceof Range) {
         Range var6 = (Range)seq;
         return positions$1((long)var6.length(), numSlices).zipWithIndex().map((x0$1) -> {
            if (x0$1 != null) {
               Tuple2 var5 = (Tuple2)x0$1._1();
               int index = x0$1._2$mcI$sp();
               if (var5 != null) {
                  int start = var5._1$mcI$sp();
                  int end = var5._2$mcI$sp();
                  if (var6.isInclusive() && index == numSlices - 1) {
                     return new Range.Inclusive(var6.start() + start * var6.step(), var6.end(), var6.step());
                  }

                  return new Range.Inclusive(var6.start() + start * var6.step(), var6.start() + (end - 1) * var6.step(), var6.step());
               }
            }

            throw new MatchError(x0$1);
         }).toSeq();
      } else if (seq instanceof NumericRange) {
         NumericRange var7 = (NumericRange)seq;
         ArrayBuffer slices = new ArrayBuffer(numSlices);
         ObjectRef r = ObjectRef.create(var7);
         positions$1((long)var7.length(), numSlices).withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$slice$3(check$ifrefutable$1))).foreach((x$2) -> {
            $anonfun$slice$4(slices, r, x$2);
            return BoxedUnit.UNIT;
         });
         return slices.toSeq();
      } else {
         Object array = seq.toArray(evidence$3);
         return positions$1((long).MODULE$.array_length(array), numSlices).map((x0$2) -> {
            if (x0$2 != null) {
               int start = x0$2._1$mcI$sp();
               int end = x0$2._2$mcI$sp();
               return org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(scala.collection.ArrayOps..MODULE$.slice$extension(scala.Predef..MODULE$.genericArrayOps(array), start, end)).toImmutableArraySeq();
            } else {
               throw new MatchError(x0$2);
            }
         }).toSeq();
      }
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ParallelCollectionRDD$.class);
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$slice$1(final long length$1, final int numSlices$1, final int i) {
      int start = (int)((long)i * length$1 / (long)numSlices$1);
      int end = (int)((long)(i + 1) * length$1 / (long)numSlices$1);
      return new Tuple2.mcII.sp(start, end);
   }

   private static final Iterator positions$1(final long length, final int numSlices) {
      return scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), numSlices).iterator().map((i) -> $anonfun$slice$1(length, numSlices, BoxesRunTime.unboxToInt(i)));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$slice$3(final Tuple2 check$ifrefutable$1) {
      return check$ifrefutable$1 != null;
   }

   // $FF: synthetic method
   public static final void $anonfun$slice$4(final ArrayBuffer slices$2, final ObjectRef r$1, final Tuple2 x$2) {
      if (x$2 != null) {
         int start = x$2._1$mcI$sp();
         int end = x$2._2$mcI$sp();
         int sliceSize = end - start;
         slices$2.$plus$eq(((NumericRange)r$1.elem).take(sliceSize));
         r$1.elem = ((NumericRange)r$1.elem).drop(sliceSize);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x$2);
      }
   }

   private ParallelCollectionRDD$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
