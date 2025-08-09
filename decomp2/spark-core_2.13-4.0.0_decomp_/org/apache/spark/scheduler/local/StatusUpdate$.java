package org.apache.spark.scheduler.local;

import java.io.Serializable;
import java.nio.ByteBuffer;
import scala.Enumeration;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.runtime.AbstractFunction3;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class StatusUpdate$ extends AbstractFunction3 implements Serializable {
   public static final StatusUpdate$ MODULE$ = new StatusUpdate$();

   public final String toString() {
      return "StatusUpdate";
   }

   public StatusUpdate apply(final long taskId, final Enumeration.Value state, final ByteBuffer serializedData) {
      return new StatusUpdate(taskId, state, serializedData);
   }

   public Option unapply(final StatusUpdate x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(BoxesRunTime.boxToLong(x$0.taskId()), x$0.state(), x$0.serializedData())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(StatusUpdate$.class);
   }

   private StatusUpdate$() {
   }
}
