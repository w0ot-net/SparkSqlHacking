package org.apache.spark.streaming.scheduler;

import java.io.Serializable;
import org.apache.spark.streaming.receiver.Receiver;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class RestartReceiver$ extends AbstractFunction1 implements Serializable {
   public static final RestartReceiver$ MODULE$ = new RestartReceiver$();

   public final String toString() {
      return "RestartReceiver";
   }

   public RestartReceiver apply(final Receiver receiver) {
      return new RestartReceiver(receiver);
   }

   public Option unapply(final RestartReceiver x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.receiver()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(RestartReceiver$.class);
   }

   private RestartReceiver$() {
   }
}
