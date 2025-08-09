package org.apache.spark.scheduler;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkEnv;
import org.apache.spark.SparkEnv$;
import org.apache.spark.storage.BlockManagerId;
import scala.Option.;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

public final class MapStatus$ {
   public static final MapStatus$ MODULE$ = new MapStatus$();
   private static int minPartitionsToUseHighlyCompressMapStatus;
   private static final double LOG_BASE = 1.1;
   private static volatile boolean bitmap$0;

   private int minPartitionsToUseHighlyCompressMapStatus$lzycompute() {
      synchronized(this){}

      try {
         if (!bitmap$0) {
            minPartitionsToUseHighlyCompressMapStatus = BoxesRunTime.unboxToInt(.MODULE$.apply(SparkEnv$.MODULE$.get()).map((x$1) -> BoxesRunTime.boxToInteger($anonfun$minPartitionsToUseHighlyCompressMapStatus$1(x$1))).getOrElse((JFunction0.mcI.sp)() -> BoxesRunTime.unboxToInt(org.apache.spark.internal.config.package$.MODULE$.SHUFFLE_MIN_NUM_PARTS_TO_HIGHLY_COMPRESS().defaultValue().get())));
            bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return minPartitionsToUseHighlyCompressMapStatus;
   }

   private int minPartitionsToUseHighlyCompressMapStatus() {
      return !bitmap$0 ? this.minPartitionsToUseHighlyCompressMapStatus$lzycompute() : minPartitionsToUseHighlyCompressMapStatus;
   }

   public MapStatus apply(final BlockManagerId loc, final long[] uncompressedSizes, final long mapTaskId) {
      return (MapStatus)(uncompressedSizes.length > this.minPartitionsToUseHighlyCompressMapStatus() ? HighlyCompressedMapStatus$.MODULE$.apply(loc, uncompressedSizes, mapTaskId) : new CompressedMapStatus(loc, uncompressedSizes, mapTaskId));
   }

   public byte compressSize(final long size) {
      if (size == 0L) {
         return 0;
      } else {
         return size <= 1L ? 1 : (byte)scala.math.package..MODULE$.min(255, (int)scala.math.package..MODULE$.ceil(scala.math.package..MODULE$.log((double)size) / scala.math.package..MODULE$.log(LOG_BASE)));
      }
   }

   public long decompressSize(final byte compressedSize) {
      return compressedSize == 0 ? 0L : (long)scala.math.package..MODULE$.pow(LOG_BASE, (double)(compressedSize & 255));
   }

   // $FF: synthetic method
   public static final int $anonfun$minPartitionsToUseHighlyCompressMapStatus$1(final SparkEnv x$1) {
      return BoxesRunTime.unboxToInt(x$1.conf().get(org.apache.spark.internal.config.package$.MODULE$.SHUFFLE_MIN_NUM_PARTS_TO_HIGHLY_COMPRESS()));
   }

   private MapStatus$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
