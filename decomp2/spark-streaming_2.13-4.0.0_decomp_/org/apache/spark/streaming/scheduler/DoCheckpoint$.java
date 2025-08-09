package org.apache.spark.streaming.scheduler;

import java.io.Serializable;
import org.apache.spark.streaming.Time;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class DoCheckpoint$ extends AbstractFunction2 implements Serializable {
   public static final DoCheckpoint$ MODULE$ = new DoCheckpoint$();

   public final String toString() {
      return "DoCheckpoint";
   }

   public DoCheckpoint apply(final Time time, final boolean clearCheckpointDataLater) {
      return new DoCheckpoint(time, clearCheckpointDataLater);
   }

   public Option unapply(final DoCheckpoint x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.time(), BoxesRunTime.boxToBoolean(x$0.clearCheckpointDataLater()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(DoCheckpoint$.class);
   }

   private DoCheckpoint$() {
   }
}
