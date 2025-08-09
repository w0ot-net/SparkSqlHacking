package org.apache.spark.streaming.scheduler;

import java.io.Serializable;
import scala.Enumeration;
import scala.Option;
import scala.Some;
import scala.Tuple7;
import scala.None.;
import scala.runtime.AbstractFunction7;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class ReceiverTrackingInfo$ extends AbstractFunction7 implements Serializable {
   public static final ReceiverTrackingInfo$ MODULE$ = new ReceiverTrackingInfo$();

   public Option $lessinit$greater$default$5() {
      return .MODULE$;
   }

   public Option $lessinit$greater$default$6() {
      return .MODULE$;
   }

   public Option $lessinit$greater$default$7() {
      return .MODULE$;
   }

   public final String toString() {
      return "ReceiverTrackingInfo";
   }

   public ReceiverTrackingInfo apply(final int receiverId, final Enumeration.Value state, final Option scheduledLocations, final Option runningExecutor, final Option name, final Option endpoint, final Option errorInfo) {
      return new ReceiverTrackingInfo(receiverId, state, scheduledLocations, runningExecutor, name, endpoint, errorInfo);
   }

   public Option apply$default$5() {
      return .MODULE$;
   }

   public Option apply$default$6() {
      return .MODULE$;
   }

   public Option apply$default$7() {
      return .MODULE$;
   }

   public Option unapply(final ReceiverTrackingInfo x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple7(BoxesRunTime.boxToInteger(x$0.receiverId()), x$0.state(), x$0.scheduledLocations(), x$0.runningExecutor(), x$0.name(), x$0.endpoint(), x$0.errorInfo())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ReceiverTrackingInfo$.class);
   }

   private ReceiverTrackingInfo$() {
   }
}
