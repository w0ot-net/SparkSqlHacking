package org.apache.spark.streaming.ui;

import java.io.Serializable;
import org.apache.spark.streaming.scheduler.OutputOperationInfo;
import scala.Option;
import scala.Some;
import scala.Tuple6;
import scala.None.;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class OutputOperationUIData$ implements Serializable {
   public static final OutputOperationUIData$ MODULE$ = new OutputOperationUIData$();

   public OutputOperationUIData apply(final OutputOperationInfo outputOperationInfo) {
      return new OutputOperationUIData(outputOperationInfo.id(), outputOperationInfo.name(), outputOperationInfo.description(), outputOperationInfo.startTime(), outputOperationInfo.endTime(), outputOperationInfo.failureReason());
   }

   public OutputOperationUIData apply(final int id, final String name, final String description, final Option startTime, final Option endTime, final Option failureReason) {
      return new OutputOperationUIData(id, name, description, startTime, endTime, failureReason);
   }

   public Option unapply(final OutputOperationUIData x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple6(BoxesRunTime.boxToInteger(x$0.id()), x$0.name(), x$0.description(), x$0.startTime(), x$0.endTime(), x$0.failureReason())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(OutputOperationUIData$.class);
   }

   private OutputOperationUIData$() {
   }
}
