package org.apache.spark.streaming.scheduler;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class StreamingListenerBatchCompleted$ extends AbstractFunction1 implements Serializable {
   public static final StreamingListenerBatchCompleted$ MODULE$ = new StreamingListenerBatchCompleted$();

   public final String toString() {
      return "StreamingListenerBatchCompleted";
   }

   public StreamingListenerBatchCompleted apply(final BatchInfo batchInfo) {
      return new StreamingListenerBatchCompleted(batchInfo);
   }

   public Option unapply(final StreamingListenerBatchCompleted x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.batchInfo()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(StreamingListenerBatchCompleted$.class);
   }

   private StreamingListenerBatchCompleted$() {
   }
}
