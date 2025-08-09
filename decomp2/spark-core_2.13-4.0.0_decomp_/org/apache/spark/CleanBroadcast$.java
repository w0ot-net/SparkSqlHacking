package org.apache.spark;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class CleanBroadcast$ extends AbstractFunction1 implements Serializable {
   public static final CleanBroadcast$ MODULE$ = new CleanBroadcast$();

   public final String toString() {
      return "CleanBroadcast";
   }

   public CleanBroadcast apply(final long broadcastId) {
      return new CleanBroadcast(broadcastId);
   }

   public Option unapply(final CleanBroadcast x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(BoxesRunTime.boxToLong(x$0.broadcastId())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(CleanBroadcast$.class);
   }

   private CleanBroadcast$() {
   }
}
