package org.apache.spark.storage;

import java.util.UUID;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.errors.SparkCoreErrors$;
import scala.Option;
import scala.collection.LinearSeqOps;
import scala.collection.StringOps.;
import scala.collection.immutable.List;
import scala.util.matching.Regex;

@DeveloperApi
public final class BlockId$ {
   public static final BlockId$ MODULE$ = new BlockId$();
   private static final Regex RDD;
   private static final Regex SHUFFLE;
   private static final Regex SHUFFLE_BATCH;
   private static final Regex SHUFFLE_DATA;
   private static final Regex SHUFFLE_INDEX;
   private static final Regex SHUFFLE_PUSH;
   private static final Regex SHUFFLE_MERGED;
   private static final Regex SHUFFLE_MERGED_DATA;
   private static final Regex SHUFFLE_MERGED_INDEX;
   private static final Regex SHUFFLE_MERGED_META;
   private static final Regex SHUFFLE_CHUNK;
   private static final Regex BROADCAST;
   private static final Regex TASKRESULT;
   private static final Regex STREAM;
   private static final Regex PYTHON_STREAM;
   private static final Regex TEMP_LOCAL;
   private static final Regex TEMP_SHUFFLE;
   private static final Regex TEST;

   static {
      RDD = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString("rdd_([0-9]+)_([0-9]+)"));
      SHUFFLE = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString("shuffle_([0-9]+)_([0-9]+)_([0-9]+)"));
      SHUFFLE_BATCH = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString("shuffle_([0-9]+)_([0-9]+)_([0-9]+)_([0-9]+)"));
      SHUFFLE_DATA = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString("shuffle_([0-9]+)_([0-9]+)_([0-9]+).data"));
      SHUFFLE_INDEX = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString("shuffle_([0-9]+)_([0-9]+)_([0-9]+).index"));
      SHUFFLE_PUSH = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString("shufflePush_([0-9]+)_([0-9]+)_([0-9]+)_([0-9]+)"));
      SHUFFLE_MERGED = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString("shuffleMerged_([0-9]+)_([0-9]+)_([0-9]+)"));
      SHUFFLE_MERGED_DATA = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString("shuffleMerged_([_A-Za-z0-9]*)_([0-9]+)_([0-9]+)_([0-9]+).data"));
      SHUFFLE_MERGED_INDEX = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString("shuffleMerged_([_A-Za-z0-9]*)_([0-9]+)_([0-9]+)_([0-9]+).index"));
      SHUFFLE_MERGED_META = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString("shuffleMerged_([_A-Za-z0-9]*)_([0-9]+)_([0-9]+)_([0-9]+).meta"));
      SHUFFLE_CHUNK = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString("shuffleChunk_([0-9]+)_([0-9]+)_([0-9]+)_([0-9]+)"));
      BROADCAST = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString("broadcast_([0-9]+)([_A-Za-z0-9]*)"));
      TASKRESULT = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString("taskresult_([0-9]+)"));
      STREAM = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString("input-([0-9]+)-([0-9]+)"));
      PYTHON_STREAM = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString("python-stream-([0-9]+)-([0-9]+)"));
      TEMP_LOCAL = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString("temp_local_([-A-Fa-f0-9]+)"));
      TEMP_SHUFFLE = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString("temp_shuffle_([-A-Fa-f0-9]+)"));
      TEST = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString("test_(.*)"));
   }

   public Regex RDD() {
      return RDD;
   }

   public Regex SHUFFLE() {
      return SHUFFLE;
   }

   public Regex SHUFFLE_BATCH() {
      return SHUFFLE_BATCH;
   }

   public Regex SHUFFLE_DATA() {
      return SHUFFLE_DATA;
   }

   public Regex SHUFFLE_INDEX() {
      return SHUFFLE_INDEX;
   }

   public Regex SHUFFLE_PUSH() {
      return SHUFFLE_PUSH;
   }

   public Regex SHUFFLE_MERGED() {
      return SHUFFLE_MERGED;
   }

   public Regex SHUFFLE_MERGED_DATA() {
      return SHUFFLE_MERGED_DATA;
   }

   public Regex SHUFFLE_MERGED_INDEX() {
      return SHUFFLE_MERGED_INDEX;
   }

   public Regex SHUFFLE_MERGED_META() {
      return SHUFFLE_MERGED_META;
   }

   public Regex SHUFFLE_CHUNK() {
      return SHUFFLE_CHUNK;
   }

   public Regex BROADCAST() {
      return BROADCAST;
   }

   public Regex TASKRESULT() {
      return TASKRESULT;
   }

   public Regex STREAM() {
      return STREAM;
   }

   public Regex PYTHON_STREAM() {
      return PYTHON_STREAM;
   }

   public Regex TEMP_LOCAL() {
      return TEMP_LOCAL;
   }

   public Regex TEMP_SHUFFLE() {
      return TEMP_SHUFFLE;
   }

   public Regex TEST() {
      return TEST;
   }

   public BlockId apply(final String name) {
      if (name != null) {
         Option var4 = this.RDD().unapplySeq(name);
         if (!var4.isEmpty() && var4.get() != null && ((List)var4.get()).lengthCompare(2) == 0) {
            String rddId = (String)((LinearSeqOps)var4.get()).apply(0);
            String splitIndex = (String)((LinearSeqOps)var4.get()).apply(1);
            return new RDDBlockId(.MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(rddId)), .MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(splitIndex)));
         }
      }

      if (name != null) {
         Option var7 = this.SHUFFLE().unapplySeq(name);
         if (!var7.isEmpty() && var7.get() != null && ((List)var7.get()).lengthCompare(3) == 0) {
            String shuffleId = (String)((LinearSeqOps)var7.get()).apply(0);
            String mapId = (String)((LinearSeqOps)var7.get()).apply(1);
            String reduceId = (String)((LinearSeqOps)var7.get()).apply(2);
            return new ShuffleBlockId(.MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(shuffleId)), .MODULE$.toLong$extension(scala.Predef..MODULE$.augmentString(mapId)), .MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(reduceId)));
         }
      }

      if (name != null) {
         Option var11 = this.SHUFFLE_BATCH().unapplySeq(name);
         if (!var11.isEmpty() && var11.get() != null && ((List)var11.get()).lengthCompare(4) == 0) {
            String shuffleId = (String)((LinearSeqOps)var11.get()).apply(0);
            String mapId = (String)((LinearSeqOps)var11.get()).apply(1);
            String startReduceId = (String)((LinearSeqOps)var11.get()).apply(2);
            String endReduceId = (String)((LinearSeqOps)var11.get()).apply(3);
            return new ShuffleBlockBatchId(.MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(shuffleId)), .MODULE$.toLong$extension(scala.Predef..MODULE$.augmentString(mapId)), .MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(startReduceId)), .MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(endReduceId)));
         }
      }

      if (name != null) {
         Option var16 = this.SHUFFLE_DATA().unapplySeq(name);
         if (!var16.isEmpty() && var16.get() != null && ((List)var16.get()).lengthCompare(3) == 0) {
            String shuffleId = (String)((LinearSeqOps)var16.get()).apply(0);
            String mapId = (String)((LinearSeqOps)var16.get()).apply(1);
            String reduceId = (String)((LinearSeqOps)var16.get()).apply(2);
            return new ShuffleDataBlockId(.MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(shuffleId)), .MODULE$.toLong$extension(scala.Predef..MODULE$.augmentString(mapId)), .MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(reduceId)));
         }
      }

      if (name != null) {
         Option var20 = this.SHUFFLE_INDEX().unapplySeq(name);
         if (!var20.isEmpty() && var20.get() != null && ((List)var20.get()).lengthCompare(3) == 0) {
            String shuffleId = (String)((LinearSeqOps)var20.get()).apply(0);
            String mapId = (String)((LinearSeqOps)var20.get()).apply(1);
            String reduceId = (String)((LinearSeqOps)var20.get()).apply(2);
            return new ShuffleIndexBlockId(.MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(shuffleId)), .MODULE$.toLong$extension(scala.Predef..MODULE$.augmentString(mapId)), .MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(reduceId)));
         }
      }

      if (name != null) {
         Option var24 = this.SHUFFLE_PUSH().unapplySeq(name);
         if (!var24.isEmpty() && var24.get() != null && ((List)var24.get()).lengthCompare(4) == 0) {
            String shuffleId = (String)((LinearSeqOps)var24.get()).apply(0);
            String shuffleMergeId = (String)((LinearSeqOps)var24.get()).apply(1);
            String mapIndex = (String)((LinearSeqOps)var24.get()).apply(2);
            String reduceId = (String)((LinearSeqOps)var24.get()).apply(3);
            return new ShufflePushBlockId(.MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(shuffleId)), .MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(shuffleMergeId)), .MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(mapIndex)), .MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(reduceId)));
         }
      }

      if (name != null) {
         Option var29 = this.SHUFFLE_MERGED().unapplySeq(name);
         if (!var29.isEmpty() && var29.get() != null && ((List)var29.get()).lengthCompare(3) == 0) {
            String shuffleId = (String)((LinearSeqOps)var29.get()).apply(0);
            String shuffleMergeId = (String)((LinearSeqOps)var29.get()).apply(1);
            String reduceId = (String)((LinearSeqOps)var29.get()).apply(2);
            return new ShuffleMergedBlockId(.MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(shuffleId)), .MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(shuffleMergeId)), .MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(reduceId)));
         }
      }

      if (name != null) {
         Option var33 = this.SHUFFLE_MERGED_DATA().unapplySeq(name);
         if (!var33.isEmpty() && var33.get() != null && ((List)var33.get()).lengthCompare(4) == 0) {
            String appId = (String)((LinearSeqOps)var33.get()).apply(0);
            String shuffleId = (String)((LinearSeqOps)var33.get()).apply(1);
            String shuffleMergeId = (String)((LinearSeqOps)var33.get()).apply(2);
            String reduceId = (String)((LinearSeqOps)var33.get()).apply(3);
            return new ShuffleMergedDataBlockId(appId, .MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(shuffleId)), .MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(shuffleMergeId)), .MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(reduceId)));
         }
      }

      if (name != null) {
         Option var38 = this.SHUFFLE_MERGED_INDEX().unapplySeq(name);
         if (!var38.isEmpty() && var38.get() != null && ((List)var38.get()).lengthCompare(4) == 0) {
            String appId = (String)((LinearSeqOps)var38.get()).apply(0);
            String shuffleId = (String)((LinearSeqOps)var38.get()).apply(1);
            String shuffleMergeId = (String)((LinearSeqOps)var38.get()).apply(2);
            String reduceId = (String)((LinearSeqOps)var38.get()).apply(3);
            return new ShuffleMergedIndexBlockId(appId, .MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(shuffleId)), .MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(shuffleMergeId)), .MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(reduceId)));
         }
      }

      if (name != null) {
         Option var43 = this.SHUFFLE_MERGED_META().unapplySeq(name);
         if (!var43.isEmpty() && var43.get() != null && ((List)var43.get()).lengthCompare(4) == 0) {
            String appId = (String)((LinearSeqOps)var43.get()).apply(0);
            String shuffleId = (String)((LinearSeqOps)var43.get()).apply(1);
            String shuffleMergeId = (String)((LinearSeqOps)var43.get()).apply(2);
            String reduceId = (String)((LinearSeqOps)var43.get()).apply(3);
            return new ShuffleMergedMetaBlockId(appId, .MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(shuffleId)), .MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(shuffleMergeId)), .MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(reduceId)));
         }
      }

      if (name != null) {
         Option var48 = this.SHUFFLE_CHUNK().unapplySeq(name);
         if (!var48.isEmpty() && var48.get() != null && ((List)var48.get()).lengthCompare(4) == 0) {
            String shuffleId = (String)((LinearSeqOps)var48.get()).apply(0);
            String shuffleMergeId = (String)((LinearSeqOps)var48.get()).apply(1);
            String reduceId = (String)((LinearSeqOps)var48.get()).apply(2);
            String chunkId = (String)((LinearSeqOps)var48.get()).apply(3);
            return new ShuffleBlockChunkId(.MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(shuffleId)), .MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(shuffleMergeId)), .MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(reduceId)), .MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(chunkId)));
         }
      }

      if (name != null) {
         Option var53 = this.BROADCAST().unapplySeq(name);
         if (!var53.isEmpty() && var53.get() != null && ((List)var53.get()).lengthCompare(2) == 0) {
            String broadcastId = (String)((LinearSeqOps)var53.get()).apply(0);
            String field = (String)((LinearSeqOps)var53.get()).apply(1);
            return new BroadcastBlockId(.MODULE$.toLong$extension(scala.Predef..MODULE$.augmentString(broadcastId)), .MODULE$.stripPrefix$extension(scala.Predef..MODULE$.augmentString(field), "_"));
         }
      }

      if (name != null) {
         Option var56 = this.TASKRESULT().unapplySeq(name);
         if (!var56.isEmpty() && var56.get() != null && ((List)var56.get()).lengthCompare(1) == 0) {
            String taskId = (String)((LinearSeqOps)var56.get()).apply(0);
            return new TaskResultBlockId(.MODULE$.toLong$extension(scala.Predef..MODULE$.augmentString(taskId)));
         }
      }

      if (name != null) {
         Option var58 = this.STREAM().unapplySeq(name);
         if (!var58.isEmpty() && var58.get() != null && ((List)var58.get()).lengthCompare(2) == 0) {
            String streamId = (String)((LinearSeqOps)var58.get()).apply(0);
            String uniqueId = (String)((LinearSeqOps)var58.get()).apply(1);
            return new StreamBlockId(.MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(streamId)), .MODULE$.toLong$extension(scala.Predef..MODULE$.augmentString(uniqueId)));
         }
      }

      if (name != null) {
         Option var61 = this.PYTHON_STREAM().unapplySeq(name);
         if (!var61.isEmpty() && var61.get() != null && ((List)var61.get()).lengthCompare(2) == 0) {
            String streamId = (String)((LinearSeqOps)var61.get()).apply(0);
            String uniqueId = (String)((LinearSeqOps)var61.get()).apply(1);
            return new PythonStreamBlockId(.MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(streamId)), .MODULE$.toLong$extension(scala.Predef..MODULE$.augmentString(uniqueId)));
         }
      }

      if (name != null) {
         Option var64 = this.TEMP_LOCAL().unapplySeq(name);
         if (!var64.isEmpty() && var64.get() != null && ((List)var64.get()).lengthCompare(1) == 0) {
            String uuid = (String)((LinearSeqOps)var64.get()).apply(0);
            return new TempLocalBlockId(UUID.fromString(uuid));
         }
      }

      if (name != null) {
         Option var66 = this.TEMP_SHUFFLE().unapplySeq(name);
         if (!var66.isEmpty() && var66.get() != null && ((List)var66.get()).lengthCompare(1) == 0) {
            String uuid = (String)((LinearSeqOps)var66.get()).apply(0);
            return new TempShuffleBlockId(UUID.fromString(uuid));
         }
      }

      if (name != null) {
         Option var68 = this.TEST().unapplySeq(name);
         if (!var68.isEmpty() && var68.get() != null && ((List)var68.get()).lengthCompare(1) == 0) {
            String value = (String)((LinearSeqOps)var68.get()).apply(0);
            return new TestBlockId(value);
         }
      }

      throw SparkCoreErrors$.MODULE$.unrecognizedBlockIdError(name);
   }

   private BlockId$() {
   }
}
