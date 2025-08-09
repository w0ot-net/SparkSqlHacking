package org.apache.spark;

import java.io.Serializable;
import org.apache.spark.storage.BlockManagerId;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.Tuple4;
import scala.None.;
import scala.collection.mutable.Map;
import scala.runtime.AbstractFunction4;
import scala.runtime.ModuleSerializationProxy;

public final class Heartbeat$ extends AbstractFunction4 implements Serializable {
   public static final Heartbeat$ MODULE$ = new Heartbeat$();

   public final String toString() {
      return "Heartbeat";
   }

   public Heartbeat apply(final String executorId, final Tuple2[] accumUpdates, final BlockManagerId blockManagerId, final Map executorUpdates) {
      return new Heartbeat(executorId, accumUpdates, blockManagerId, executorUpdates);
   }

   public Option unapply(final Heartbeat x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple4(x$0.executorId(), x$0.accumUpdates(), x$0.blockManagerId(), x$0.executorUpdates())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Heartbeat$.class);
   }

   private Heartbeat$() {
   }
}
