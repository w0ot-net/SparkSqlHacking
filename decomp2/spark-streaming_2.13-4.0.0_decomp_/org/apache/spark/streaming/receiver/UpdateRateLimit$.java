package org.apache.spark.streaming.receiver;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class UpdateRateLimit$ extends AbstractFunction1 implements Serializable {
   public static final UpdateRateLimit$ MODULE$ = new UpdateRateLimit$();

   public final String toString() {
      return "UpdateRateLimit";
   }

   public UpdateRateLimit apply(final long elementsPerSecond) {
      return new UpdateRateLimit(elementsPerSecond);
   }

   public Option unapply(final UpdateRateLimit x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(BoxesRunTime.boxToLong(x$0.elementsPerSecond())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(UpdateRateLimit$.class);
   }

   private UpdateRateLimit$() {
   }
}
