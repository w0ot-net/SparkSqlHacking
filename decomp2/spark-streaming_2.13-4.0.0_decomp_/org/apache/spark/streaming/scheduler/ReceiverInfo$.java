package org.apache.spark.streaming.scheduler;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple8;
import scala.None.;
import scala.runtime.AbstractFunction8;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class ReceiverInfo$ extends AbstractFunction8 implements Serializable {
   public static final ReceiverInfo$ MODULE$ = new ReceiverInfo$();

   public String $lessinit$greater$default$6() {
      return "";
   }

   public String $lessinit$greater$default$7() {
      return "";
   }

   public long $lessinit$greater$default$8() {
      return -1L;
   }

   public final String toString() {
      return "ReceiverInfo";
   }

   public ReceiverInfo apply(final int streamId, final String name, final boolean active, final String location, final String executorId, final String lastErrorMessage, final String lastError, final long lastErrorTime) {
      return new ReceiverInfo(streamId, name, active, location, executorId, lastErrorMessage, lastError, lastErrorTime);
   }

   public String apply$default$6() {
      return "";
   }

   public String apply$default$7() {
      return "";
   }

   public long apply$default$8() {
      return -1L;
   }

   public Option unapply(final ReceiverInfo x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple8(BoxesRunTime.boxToInteger(x$0.streamId()), x$0.name(), BoxesRunTime.boxToBoolean(x$0.active()), x$0.location(), x$0.executorId(), x$0.lastErrorMessage(), x$0.lastError(), BoxesRunTime.boxToLong(x$0.lastErrorTime()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ReceiverInfo$.class);
   }

   private ReceiverInfo$() {
   }
}
