package org.apache.spark.scheduler;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkEnv;
import org.apache.spark.SparkEnv$;
import org.apache.spark.storage.BlockManagerId;
import org.apache.spark.util.Utils$;
import org.roaringbitmap.RoaringBitmap;
import scala.Option.;
import scala.collection.mutable.Map;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.java8.JFunction0;

public final class HighlyCompressedMapStatus$ implements Serializable {
   public static final HighlyCompressedMapStatus$ MODULE$ = new HighlyCompressedMapStatus$();

   public HighlyCompressedMapStatus apply(final BlockManagerId loc, final long[] uncompressedSizes, final long mapTaskId) {
      int i = 0;
      int numNonEmptyBlocks = 0;
      int numSmallBlocks = 0;
      long totalSmallBlockSize = 0L;
      RoaringBitmap emptyBlocks = new RoaringBitmap();
      int totalNumBlocks = uncompressedSizes.length;
      double accurateBlockSkewedFactor = BoxesRunTime.unboxToDouble(.MODULE$.apply(SparkEnv$.MODULE$.get()).map((x$3) -> BoxesRunTime.boxToDouble($anonfun$apply$1(x$3))).getOrElse((JFunction0.mcD.sp)() -> BoxesRunTime.unboxToDouble(org.apache.spark.internal.config.package$.MODULE$.SHUFFLE_ACCURATE_BLOCK_SKEWED_FACTOR().defaultValue().get())));
      long shuffleAccurateBlockThreshold = BoxesRunTime.unboxToLong(.MODULE$.apply(SparkEnv$.MODULE$.get()).map((x$4) -> BoxesRunTime.boxToLong($anonfun$apply$3(x$4))).getOrElse((JFunction0.mcJ.sp)() -> BoxesRunTime.unboxToLong(org.apache.spark.internal.config.package$.MODULE$.SHUFFLE_ACCURATE_BLOCK_THRESHOLD().defaultValue().get())));
      double var10000;
      if (accurateBlockSkewedFactor > (double)0) {
         long[] sortedSizes = (long[])scala.collection.ArrayOps..MODULE$.sorted$extension(scala.Predef..MODULE$.longArrayOps(uncompressedSizes), scala.math.Ordering.Long..MODULE$);
         long medianSize = Utils$.MODULE$.median(sortedSizes, true);
         int maxAccurateSkewedBlockNumber = Math.min(BoxesRunTime.unboxToInt(.MODULE$.apply(SparkEnv$.MODULE$.get()).map((x$5) -> BoxesRunTime.boxToInteger($anonfun$apply$5(x$5))).getOrElse((JFunction0.mcI.sp)() -> BoxesRunTime.unboxToInt(org.apache.spark.internal.config.package$.MODULE$.SHUFFLE_MAX_ACCURATE_SKEWED_BLOCK_NUMBER().defaultValue().get()))), totalNumBlocks);
         double skewSizeThreshold = Math.max((double)medianSize * accurateBlockSkewedFactor, (double)sortedSizes[totalNumBlocks - maxAccurateSkewedBlockNumber]);
         var10000 = Math.min((double)shuffleAccurateBlockThreshold, skewSizeThreshold);
      } else {
         var10000 = (double)shuffleAccurateBlockThreshold;
      }

      double threshold = var10000;

      Map hugeBlockSizes;
      for(hugeBlockSizes = (Map)scala.collection.mutable.Map..MODULE$.empty(); i < totalNumBlocks; ++i) {
         long size = uncompressedSizes[i];
         if (size > 0L) {
            ++numNonEmptyBlocks;
            if ((double)size < threshold) {
               totalSmallBlockSize += size;
               ++numSmallBlocks;
            } else {
               hugeBlockSizes.update(BoxesRunTime.boxToInteger(i), BoxesRunTime.boxToByte(MapStatus$.MODULE$.compressSize(uncompressedSizes[i])));
            }
         } else {
            emptyBlocks.add(i);
         }
      }

      long avgSize = numSmallBlocks > 0 ? totalSmallBlockSize / (long)numSmallBlocks : 0L;
      emptyBlocks.trim();
      emptyBlocks.runOptimize();
      return new HighlyCompressedMapStatus(loc, numNonEmptyBlocks, emptyBlocks, avgSize, hugeBlockSizes, mapTaskId);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(HighlyCompressedMapStatus$.class);
   }

   // $FF: synthetic method
   public static final double $anonfun$apply$1(final SparkEnv x$3) {
      return BoxesRunTime.unboxToDouble(x$3.conf().get(org.apache.spark.internal.config.package$.MODULE$.SHUFFLE_ACCURATE_BLOCK_SKEWED_FACTOR()));
   }

   // $FF: synthetic method
   public static final long $anonfun$apply$3(final SparkEnv x$4) {
      return BoxesRunTime.unboxToLong(x$4.conf().get(org.apache.spark.internal.config.package$.MODULE$.SHUFFLE_ACCURATE_BLOCK_THRESHOLD()));
   }

   // $FF: synthetic method
   public static final int $anonfun$apply$5(final SparkEnv x$5) {
      return BoxesRunTime.unboxToInt(x$5.conf().get(org.apache.spark.internal.config.package$.MODULE$.SHUFFLE_MAX_ACCURATE_SKEWED_BLOCK_NUMBER()));
   }

   private HighlyCompressedMapStatus$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
