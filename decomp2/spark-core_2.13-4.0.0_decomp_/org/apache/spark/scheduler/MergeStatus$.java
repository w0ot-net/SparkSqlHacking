package org.apache.spark.scheduler;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.network.shuffle.protocol.MergeStatuses;
import org.apache.spark.storage.BlockManagerId;
import org.apache.spark.storage.BlockManagerId$;
import org.roaringbitmap.RoaringBitmap;
import scala.MatchError;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.immutable.Seq;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class MergeStatus$ implements Serializable {
   public static final MergeStatus$ MODULE$ = new MergeStatus$();
   private static final int SHUFFLE_PUSH_DUMMY_NUM_REDUCES = 1;

   public int SHUFFLE_PUSH_DUMMY_NUM_REDUCES() {
      return SHUFFLE_PUSH_DUMMY_NUM_REDUCES;
   }

   public Seq convertMergeStatusesToMergeStatusArr(final MergeStatuses mergeStatuses, final BlockManagerId loc) {
      .MODULE$.assert(mergeStatuses.bitmaps.length == mergeStatuses.reduceIds.length && mergeStatuses.bitmaps.length == mergeStatuses.sizes.length);
      BlockManagerId mergerLoc = BlockManagerId$.MODULE$.apply(BlockManagerId$.MODULE$.SHUFFLE_MERGER_IDENTIFIER(), loc.host(), loc.port(), BlockManagerId$.MODULE$.apply$default$4());
      int shuffleMergeId = mergeStatuses.shuffleMergeId;
      return org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zipWithIndex$extension(.MODULE$.refArrayOps((Object[])mergeStatuses.bitmaps))), (x0$1) -> {
         if (x0$1 != null) {
            RoaringBitmap bitmap = (RoaringBitmap)x0$1._1();
            int index = x0$1._2$mcI$sp();
            MergeStatus mergeStatus = new MergeStatus(mergerLoc, shuffleMergeId, bitmap, mergeStatuses.sizes[index]);
            return new Tuple2(BoxesRunTime.boxToInteger(mergeStatuses.reduceIds[index]), mergeStatus);
         } else {
            throw new MatchError(x0$1);
         }
      }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class))).toImmutableArraySeq();
   }

   public MergeStatus apply(final BlockManagerId loc, final int shuffleMergeId, final RoaringBitmap bitmap, final long size) {
      return new MergeStatus(loc, shuffleMergeId, bitmap, size);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(MergeStatus$.class);
   }

   private MergeStatus$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
