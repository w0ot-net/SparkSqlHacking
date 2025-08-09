package org.apache.spark.streaming.scheduler;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.collection.immutable.Seq;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class StartAllReceivers$ extends AbstractFunction1 implements Serializable {
   public static final StartAllReceivers$ MODULE$ = new StartAllReceivers$();

   public final String toString() {
      return "StartAllReceivers";
   }

   public StartAllReceivers apply(final Seq receiver) {
      return new StartAllReceivers(receiver);
   }

   public Option unapply(final StartAllReceivers x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.receiver()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(StartAllReceivers$.class);
   }

   private StartAllReceivers$() {
   }
}
