package org.apache.spark.streaming.scheduler;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class StreamingListenerBatchStarted$ extends AbstractFunction1 implements Serializable {
   public static final StreamingListenerBatchStarted$ MODULE$ = new StreamingListenerBatchStarted$();

   public final String toString() {
      return "StreamingListenerBatchStarted";
   }

   public StreamingListenerBatchStarted apply(final BatchInfo batchInfo) {
      return new StreamingListenerBatchStarted(batchInfo);
   }

   public Option unapply(final StreamingListenerBatchStarted x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.batchInfo()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(StreamingListenerBatchStarted$.class);
   }

   private StreamingListenerBatchStarted$() {
   }
}
