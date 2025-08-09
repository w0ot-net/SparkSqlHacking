package org.apache.spark.streaming.scheduler;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.runtime.AbstractFunction3;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class DeregisterReceiver$ extends AbstractFunction3 implements Serializable {
   public static final DeregisterReceiver$ MODULE$ = new DeregisterReceiver$();

   public final String toString() {
      return "DeregisterReceiver";
   }

   public DeregisterReceiver apply(final int streamId, final String msg, final String error) {
      return new DeregisterReceiver(streamId, msg, error);
   }

   public Option unapply(final DeregisterReceiver x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(BoxesRunTime.boxToInteger(x$0.streamId()), x$0.msg(), x$0.error())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(DeregisterReceiver$.class);
   }

   private DeregisterReceiver$() {
   }
}
