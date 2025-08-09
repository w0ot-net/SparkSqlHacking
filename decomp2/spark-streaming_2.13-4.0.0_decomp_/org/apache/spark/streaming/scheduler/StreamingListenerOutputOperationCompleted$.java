package org.apache.spark.streaming.scheduler;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class StreamingListenerOutputOperationCompleted$ extends AbstractFunction1 implements Serializable {
   public static final StreamingListenerOutputOperationCompleted$ MODULE$ = new StreamingListenerOutputOperationCompleted$();

   public final String toString() {
      return "StreamingListenerOutputOperationCompleted";
   }

   public StreamingListenerOutputOperationCompleted apply(final OutputOperationInfo outputOperationInfo) {
      return new StreamingListenerOutputOperationCompleted(outputOperationInfo);
   }

   public Option unapply(final StreamingListenerOutputOperationCompleted x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.outputOperationInfo()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(StreamingListenerOutputOperationCompleted$.class);
   }

   private StreamingListenerOutputOperationCompleted$() {
   }
}
