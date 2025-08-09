package org.apache.spark.streaming.scheduler;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class StreamingListenerReceiverStarted$ extends AbstractFunction1 implements Serializable {
   public static final StreamingListenerReceiverStarted$ MODULE$ = new StreamingListenerReceiverStarted$();

   public final String toString() {
      return "StreamingListenerReceiverStarted";
   }

   public StreamingListenerReceiverStarted apply(final ReceiverInfo receiverInfo) {
      return new StreamingListenerReceiverStarted(receiverInfo);
   }

   public Option unapply(final StreamingListenerReceiverStarted x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.receiverInfo()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(StreamingListenerReceiverStarted$.class);
   }

   private StreamingListenerReceiverStarted$() {
   }
}
