package org.apache.spark.storage;

import java.lang.invoke.SerializedLambda;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.spark.network.util.NettyUtils;
import org.apache.spark.util.Clock;
import org.apache.spark.util.SystemClock;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.Seq;
import scala.collection.mutable.ArrayBuffer;
import scala.math.Numeric.LongIsIntegral.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

public final class ShuffleBlockFetcherIterator$ {
   public static final ShuffleBlockFetcherIterator$ MODULE$ = new ShuffleBlockFetcherIterator$();
   private static final AtomicBoolean isNettyOOMOnShuffle = new AtomicBoolean(false);

   public Clock $lessinit$greater$default$18() {
      return new SystemClock();
   }

   public AtomicBoolean isNettyOOMOnShuffle() {
      return isNettyOOMOnShuffle;
   }

   public void resetNettyOOMFlagIfPossible(final long freeMemoryLowerBound) {
      if (this.isNettyOOMOnShuffle().get() && NettyUtils.freeDirectMemory() >= freeMemoryLowerBound) {
         this.isNettyOOMOnShuffle().compareAndSet(true, false);
      }
   }

   public Seq mergeContinuousShuffleBlockIdsIfNeeded(final Seq blocks, final boolean doBatchFetch) {
      Object var11;
      if (doBatchFetch) {
         ArrayBuffer curBlocks = new ArrayBuffer();
         ArrayBuffer mergedBlockInfo = new ArrayBuffer();
         Iterator iter = blocks.iterator();

         while(iter.hasNext()) {
            ShuffleBlockFetcherIterator.FetchBlockInfo info = (ShuffleBlockFetcherIterator.FetchBlockInfo)iter.next();
            if (info.blockId() instanceof ShuffleBlockBatchId) {
               mergedBlockInfo.$plus$eq(info);
            } else if (curBlocks.isEmpty()) {
               curBlocks.$plus$eq(info);
            } else {
               ShuffleBlockId curBlockId = (ShuffleBlockId)info.blockId();
               long currentMapId = ((ShuffleBlockId)((ShuffleBlockFetcherIterator.FetchBlockInfo)curBlocks.head()).blockId()).mapId();
               if (curBlockId.mapId() != currentMapId) {
                  mergedBlockInfo.$plus$eq(mergeFetchBlockInfo$1(curBlocks, mergedBlockInfo));
                  curBlocks.clear();
               }

               curBlocks.$plus$eq(info);
            }
         }

         if (curBlocks.nonEmpty()) {
            mergedBlockInfo.$plus$eq(mergeFetchBlockInfo$1(curBlocks, mergedBlockInfo));
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

         var11 = mergedBlockInfo;
      } else {
         var11 = blocks;
      }

      Seq result = (Seq)var11;
      return result;
   }

   private static final boolean shouldMergeIntoPreviousBatchBlockId$1(final ArrayBuffer mergedBlockInfo$1, final ShuffleBlockId startBlockId$1) {
      return ((ShuffleBlockBatchId)((ShuffleBlockFetcherIterator.FetchBlockInfo)mergedBlockInfo$1.last()).blockId()).mapId() == startBlockId$1.mapId();
   }

   // $FF: synthetic method
   public static final long $anonfun$mergeContinuousShuffleBlockIdsIfNeeded$1(final ShuffleBlockFetcherIterator.FetchBlockInfo x$25) {
      return x$25.size();
   }

   // $FF: synthetic method
   public static final long $anonfun$mergeContinuousShuffleBlockIdsIfNeeded$2(final ShuffleBlockFetcherIterator.FetchBlockInfo x$26) {
      return x$26.size();
   }

   private static final ShuffleBlockFetcherIterator.FetchBlockInfo mergeFetchBlockInfo$1(final ArrayBuffer toBeMerged, final ArrayBuffer mergedBlockInfo$1) {
      ShuffleBlockId startBlockId = (ShuffleBlockId)((ShuffleBlockFetcherIterator.FetchBlockInfo)toBeMerged.head()).blockId();
      Tuple2.mcIJ.sp var10000;
      if (mergedBlockInfo$1.nonEmpty() && shouldMergeIntoPreviousBatchBlockId$1(mergedBlockInfo$1, startBlockId)) {
         ShuffleBlockFetcherIterator.FetchBlockInfo removed = (ShuffleBlockFetcherIterator.FetchBlockInfo)mergedBlockInfo$1.remove(mergedBlockInfo$1.length() - 1);
         var10000 = new Tuple2.mcIJ.sp(((ShuffleBlockBatchId)removed.blockId()).startReduceId(), removed.size() + BoxesRunTime.unboxToLong(((IterableOnceOps)toBeMerged.map((x$25) -> BoxesRunTime.boxToLong($anonfun$mergeContinuousShuffleBlockIdsIfNeeded$1(x$25)))).sum(.MODULE$)));
      } else {
         var10000 = new Tuple2.mcIJ.sp(startBlockId.reduceId(), BoxesRunTime.unboxToLong(((IterableOnceOps)toBeMerged.map((x$26) -> BoxesRunTime.boxToLong($anonfun$mergeContinuousShuffleBlockIdsIfNeeded$2(x$26)))).sum(.MODULE$)));
      }

      Tuple2.mcIJ.sp var5 = var10000;
      if (var5 != null) {
         int startReduceId = ((Tuple2)var5)._1$mcI$sp();
         long size = ((Tuple2)var5)._2$mcJ$sp();
         Tuple2.mcIJ.sp var4 = new Tuple2.mcIJ.sp(startReduceId, size);
         int startReduceId = ((Tuple2)var4)._1$mcI$sp();
         long size = ((Tuple2)var4)._2$mcJ$sp();
         return new ShuffleBlockFetcherIterator.FetchBlockInfo(new ShuffleBlockBatchId(startBlockId.shuffleId(), startBlockId.mapId(), startReduceId, ((ShuffleBlockId)((ShuffleBlockFetcherIterator.FetchBlockInfo)toBeMerged.last()).blockId()).reduceId() + 1), size, ((ShuffleBlockFetcherIterator.FetchBlockInfo)toBeMerged.head()).mapIndex());
      } else {
         throw new MatchError(var5);
      }
   }

   private ShuffleBlockFetcherIterator$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
