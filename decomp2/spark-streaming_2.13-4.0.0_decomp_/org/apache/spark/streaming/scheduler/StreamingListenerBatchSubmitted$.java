package org.apache.spark.streaming.scheduler;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class StreamingListenerBatchSubmitted$ extends AbstractFunction1 implements Serializable {
   public static final StreamingListenerBatchSubmitted$ MODULE$ = new StreamingListenerBatchSubmitted$();

   public final String toString() {
      return "StreamingListenerBatchSubmitted";
   }

   public StreamingListenerBatchSubmitted apply(final BatchInfo batchInfo) {
      return new StreamingListenerBatchSubmitted(batchInfo);
   }

   public Option unapply(final StreamingListenerBatchSubmitted x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.batchInfo()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(StreamingListenerBatchSubmitted$.class);
   }

   private StreamingListenerBatchSubmitted$() {
   }
}
