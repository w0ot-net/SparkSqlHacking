package org.apache.spark.streaming.scheduler;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class UpdateReceiverRateLimit$ extends AbstractFunction2 implements Serializable {
   public static final UpdateReceiverRateLimit$ MODULE$ = new UpdateReceiverRateLimit$();

   public final String toString() {
      return "UpdateReceiverRateLimit";
   }

   public UpdateReceiverRateLimit apply(final int streamUID, final long newRate) {
      return new UpdateReceiverRateLimit(streamUID, newRate);
   }

   public Option unapply(final UpdateReceiverRateLimit x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2.mcIJ.sp(x$0.streamUID(), x$0.newRate())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(UpdateReceiverRateLimit$.class);
   }

   private UpdateReceiverRateLimit$() {
   }
}
