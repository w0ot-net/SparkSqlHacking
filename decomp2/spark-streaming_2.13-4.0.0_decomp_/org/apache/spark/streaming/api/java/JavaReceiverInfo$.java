package org.apache.spark.streaming.api.java;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple8;
import scala.None.;
import scala.runtime.AbstractFunction8;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class JavaReceiverInfo$ extends AbstractFunction8 implements Serializable {
   public static final JavaReceiverInfo$ MODULE$ = new JavaReceiverInfo$();

   public final String toString() {
      return "JavaReceiverInfo";
   }

   public JavaReceiverInfo apply(final int streamId, final String name, final boolean active, final String location, final String executorId, final String lastErrorMessage, final String lastError, final long lastErrorTime) {
      return new JavaReceiverInfo(streamId, name, active, location, executorId, lastErrorMessage, lastError, lastErrorTime);
   }

   public Option unapply(final JavaReceiverInfo x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple8(BoxesRunTime.boxToInteger(x$0.streamId()), x$0.name(), BoxesRunTime.boxToBoolean(x$0.active()), x$0.location(), x$0.executorId(), x$0.lastErrorMessage(), x$0.lastError(), BoxesRunTime.boxToLong(x$0.lastErrorTime()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(JavaReceiverInfo$.class);
   }

   private JavaReceiverInfo$() {
   }
}
