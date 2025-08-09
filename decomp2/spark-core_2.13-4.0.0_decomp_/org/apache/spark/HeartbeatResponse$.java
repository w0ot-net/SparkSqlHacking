package org.apache.spark;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class HeartbeatResponse$ extends AbstractFunction1 implements Serializable {
   public static final HeartbeatResponse$ MODULE$ = new HeartbeatResponse$();

   public final String toString() {
      return "HeartbeatResponse";
   }

   public HeartbeatResponse apply(final boolean reregisterBlockManager) {
      return new HeartbeatResponse(reregisterBlockManager);
   }

   public Option unapply(final HeartbeatResponse x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(BoxesRunTime.boxToBoolean(x$0.reregisterBlockManager())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(HeartbeatResponse$.class);
   }

   private HeartbeatResponse$() {
   }
}
