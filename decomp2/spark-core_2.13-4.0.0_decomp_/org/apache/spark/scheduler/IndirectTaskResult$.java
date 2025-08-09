package org.apache.spark.scheduler;

import java.io.Serializable;
import org.apache.spark.storage.BlockId;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class IndirectTaskResult$ implements Serializable {
   public static final IndirectTaskResult$ MODULE$ = new IndirectTaskResult$();

   public final String toString() {
      return "IndirectTaskResult";
   }

   public IndirectTaskResult apply(final BlockId blockId, final long size) {
      return new IndirectTaskResult(blockId, size);
   }

   public Option unapply(final IndirectTaskResult x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.blockId(), BoxesRunTime.boxToLong(x$0.size()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(IndirectTaskResult$.class);
   }

   private IndirectTaskResult$() {
   }
}
