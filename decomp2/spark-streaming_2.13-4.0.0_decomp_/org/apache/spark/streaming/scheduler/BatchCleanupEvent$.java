package org.apache.spark.streaming.scheduler;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.collection.immutable.Seq;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class BatchCleanupEvent$ extends AbstractFunction1 implements Serializable {
   public static final BatchCleanupEvent$ MODULE$ = new BatchCleanupEvent$();

   public final String toString() {
      return "BatchCleanupEvent";
   }

   public BatchCleanupEvent apply(final Seq times) {
      return new BatchCleanupEvent(times);
   }

   public Option unapply(final BatchCleanupEvent x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.times()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(BatchCleanupEvent$.class);
   }

   private BatchCleanupEvent$() {
   }
}
