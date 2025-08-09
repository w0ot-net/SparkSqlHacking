package org.apache.spark.storage;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class BlockException$ extends AbstractFunction2 implements Serializable {
   public static final BlockException$ MODULE$ = new BlockException$();

   public final String toString() {
      return "BlockException";
   }

   public BlockException apply(final BlockId blockId, final String message) {
      return new BlockException(blockId, message);
   }

   public Option unapply(final BlockException x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.blockId(), x$0.message())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(BlockException$.class);
   }

   private BlockException$() {
   }
}
