package org.apache.spark.streaming.scheduler;

import java.io.Serializable;
import org.apache.spark.streaming.Time;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class BatchAllocationEvent$ extends AbstractFunction2 implements Serializable {
   public static final BatchAllocationEvent$ MODULE$ = new BatchAllocationEvent$();

   public final String toString() {
      return "BatchAllocationEvent";
   }

   public BatchAllocationEvent apply(final Time time, final AllocatedBlocks allocatedBlocks) {
      return new BatchAllocationEvent(time, allocatedBlocks);
   }

   public Option unapply(final BatchAllocationEvent x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.time(), x$0.allocatedBlocks())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(BatchAllocationEvent$.class);
   }

   private BatchAllocationEvent$() {
   }
}
