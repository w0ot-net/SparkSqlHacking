package org.apache.spark.streaming.scheduler;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class StreamingListenerOutputOperationStarted$ extends AbstractFunction1 implements Serializable {
   public static final StreamingListenerOutputOperationStarted$ MODULE$ = new StreamingListenerOutputOperationStarted$();

   public final String toString() {
      return "StreamingListenerOutputOperationStarted";
   }

   public StreamingListenerOutputOperationStarted apply(final OutputOperationInfo outputOperationInfo) {
      return new StreamingListenerOutputOperationStarted(outputOperationInfo);
   }

   public Option unapply(final StreamingListenerOutputOperationStarted x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.outputOperationInfo()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(StreamingListenerOutputOperationStarted$.class);
   }

   private StreamingListenerOutputOperationStarted$() {
   }
}
