package org.apache.spark.streaming.receiver;

import java.io.Serializable;
import org.apache.spark.storage.StreamBlockId;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class BlockManagerBasedStoreResult$ extends AbstractFunction2 implements Serializable {
   public static final BlockManagerBasedStoreResult$ MODULE$ = new BlockManagerBasedStoreResult$();

   public final String toString() {
      return "BlockManagerBasedStoreResult";
   }

   public BlockManagerBasedStoreResult apply(final StreamBlockId blockId, final Option numRecords) {
      return new BlockManagerBasedStoreResult(blockId, numRecords);
   }

   public Option unapply(final BlockManagerBasedStoreResult x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.blockId(), x$0.numRecords())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(BlockManagerBasedStoreResult$.class);
   }

   private BlockManagerBasedStoreResult$() {
   }
}
