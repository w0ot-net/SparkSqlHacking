package org.apache.spark.storage;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple5;
import scala.None.;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class BlockUpdatedInfo$ implements Serializable {
   public static final BlockUpdatedInfo$ MODULE$ = new BlockUpdatedInfo$();

   public BlockUpdatedInfo apply(final BlockManagerMessages.UpdateBlockInfo updateBlockInfo) {
      return new BlockUpdatedInfo(updateBlockInfo.blockManagerId(), updateBlockInfo.blockId(), updateBlockInfo.storageLevel(), updateBlockInfo.memSize(), updateBlockInfo.diskSize());
   }

   public BlockUpdatedInfo apply(final BlockManagerId blockManagerId, final BlockId blockId, final StorageLevel storageLevel, final long memSize, final long diskSize) {
      return new BlockUpdatedInfo(blockManagerId, blockId, storageLevel, memSize, diskSize);
   }

   public Option unapply(final BlockUpdatedInfo x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple5(x$0.blockManagerId(), x$0.blockId(), x$0.storageLevel(), BoxesRunTime.boxToLong(x$0.memSize()), BoxesRunTime.boxToLong(x$0.diskSize()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(BlockUpdatedInfo$.class);
   }

   private BlockUpdatedInfo$() {
   }
}
