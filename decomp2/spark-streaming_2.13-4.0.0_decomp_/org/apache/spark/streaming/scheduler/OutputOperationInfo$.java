package org.apache.spark.streaming.scheduler;

import java.io.Serializable;
import org.apache.spark.streaming.Time;
import scala.Option;
import scala.Some;
import scala.Tuple7;
import scala.None.;
import scala.runtime.AbstractFunction7;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class OutputOperationInfo$ extends AbstractFunction7 implements Serializable {
   public static final OutputOperationInfo$ MODULE$ = new OutputOperationInfo$();

   public final String toString() {
      return "OutputOperationInfo";
   }

   public OutputOperationInfo apply(final Time batchTime, final int id, final String name, final String description, final Option startTime, final Option endTime, final Option failureReason) {
      return new OutputOperationInfo(batchTime, id, name, description, startTime, endTime, failureReason);
   }

   public Option unapply(final OutputOperationInfo x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple7(x$0.batchTime(), BoxesRunTime.boxToInteger(x$0.id()), x$0.name(), x$0.description(), x$0.startTime(), x$0.endTime(), x$0.failureReason())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(OutputOperationInfo$.class);
   }

   private OutputOperationInfo$() {
   }
}
