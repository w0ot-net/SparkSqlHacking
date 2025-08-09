package org.apache.spark.streaming.scheduler;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class StreamingListenerReceiverError$ extends AbstractFunction1 implements Serializable {
   public static final StreamingListenerReceiverError$ MODULE$ = new StreamingListenerReceiverError$();

   public final String toString() {
      return "StreamingListenerReceiverError";
   }

   public StreamingListenerReceiverError apply(final ReceiverInfo receiverInfo) {
      return new StreamingListenerReceiverError(receiverInfo);
   }

   public Option unapply(final StreamingListenerReceiverError x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.receiverInfo()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(StreamingListenerReceiverError$.class);
   }

   private StreamingListenerReceiverError$() {
   }
}
