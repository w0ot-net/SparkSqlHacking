package org.apache.spark.streaming.scheduler;

import java.io.Serializable;
import org.apache.spark.streaming.Time;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class ClearCheckpointData$ extends AbstractFunction1 implements Serializable {
   public static final ClearCheckpointData$ MODULE$ = new ClearCheckpointData$();

   public final String toString() {
      return "ClearCheckpointData";
   }

   public ClearCheckpointData apply(final Time time) {
      return new ClearCheckpointData(time);
   }

   public Option unapply(final ClearCheckpointData x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.time()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ClearCheckpointData$.class);
   }

   private ClearCheckpointData$() {
   }
}
