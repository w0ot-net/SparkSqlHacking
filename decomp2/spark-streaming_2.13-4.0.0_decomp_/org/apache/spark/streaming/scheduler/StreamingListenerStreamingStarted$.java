package org.apache.spark.streaming.scheduler;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class StreamingListenerStreamingStarted$ extends AbstractFunction1 implements Serializable {
   public static final StreamingListenerStreamingStarted$ MODULE$ = new StreamingListenerStreamingStarted$();

   public final String toString() {
      return "StreamingListenerStreamingStarted";
   }

   public StreamingListenerStreamingStarted apply(final long time) {
      return new StreamingListenerStreamingStarted(time);
   }

   public Option unapply(final StreamingListenerStreamingStarted x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(BoxesRunTime.boxToLong(x$0.time())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(StreamingListenerStreamingStarted$.class);
   }

   private StreamingListenerStreamingStarted$() {
   }
}
