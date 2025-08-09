package org.apache.spark.streaming.scheduler;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class StreamingListenerReceiverStopped$ extends AbstractFunction1 implements Serializable {
   public static final StreamingListenerReceiverStopped$ MODULE$ = new StreamingListenerReceiverStopped$();

   public final String toString() {
      return "StreamingListenerReceiverStopped";
   }

   public StreamingListenerReceiverStopped apply(final ReceiverInfo receiverInfo) {
      return new StreamingListenerReceiverStopped(receiverInfo);
   }

   public Option unapply(final StreamingListenerReceiverStopped x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.receiverInfo()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(StreamingListenerReceiverStopped$.class);
   }

   private StreamingListenerReceiverStopped$() {
   }
}
