package org.apache.spark.streaming.scheduler;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.collection.immutable.Map;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class AllocatedBlocks$ extends AbstractFunction1 implements Serializable {
   public static final AllocatedBlocks$ MODULE$ = new AllocatedBlocks$();

   public final String toString() {
      return "AllocatedBlocks";
   }

   public AllocatedBlocks apply(final Map streamIdToAllocatedBlocks) {
      return new AllocatedBlocks(streamIdToAllocatedBlocks);
   }

   public Option unapply(final AllocatedBlocks x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.streamIdToAllocatedBlocks()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(AllocatedBlocks$.class);
   }

   private AllocatedBlocks$() {
   }
}
