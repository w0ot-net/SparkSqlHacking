package org.apache.spark.streaming.scheduler;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class BlockAdditionEvent$ extends AbstractFunction1 implements Serializable {
   public static final BlockAdditionEvent$ MODULE$ = new BlockAdditionEvent$();

   public final String toString() {
      return "BlockAdditionEvent";
   }

   public BlockAdditionEvent apply(final ReceivedBlockInfo receivedBlockInfo) {
      return new BlockAdditionEvent(receivedBlockInfo);
   }

   public Option unapply(final BlockAdditionEvent x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.receivedBlockInfo()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(BlockAdditionEvent$.class);
   }

   private BlockAdditionEvent$() {
   }
}
