package org.apache.spark.sql.execution.streaming;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.concurrent.duration.Duration;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class ProcessingTimeTrigger$ implements Serializable {
   public static final ProcessingTimeTrigger$ MODULE$ = new ProcessingTimeTrigger$();

   public ProcessingTimeTrigger apply(final String interval) {
      return new ProcessingTimeTrigger(Triggers$.MODULE$.convert(interval));
   }

   public ProcessingTimeTrigger apply(final Duration interval) {
      return new ProcessingTimeTrigger(Triggers$.MODULE$.convert(interval));
   }

   public ProcessingTimeTrigger create(final String interval) {
      return this.apply(interval);
   }

   public ProcessingTimeTrigger create(final long interval, final TimeUnit unit) {
      return new ProcessingTimeTrigger(Triggers$.MODULE$.convert(interval, unit));
   }

   public ProcessingTimeTrigger apply(final long intervalMs) {
      return new ProcessingTimeTrigger(intervalMs);
   }

   public Option unapply(final ProcessingTimeTrigger x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(BoxesRunTime.boxToLong(x$0.intervalMs())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ProcessingTimeTrigger$.class);
   }

   private ProcessingTimeTrigger$() {
   }
}
