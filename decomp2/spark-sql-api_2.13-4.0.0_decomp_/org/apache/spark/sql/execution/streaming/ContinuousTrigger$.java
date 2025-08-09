package org.apache.spark.sql.execution.streaming;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.concurrent.duration.Duration;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class ContinuousTrigger$ implements Serializable {
   public static final ContinuousTrigger$ MODULE$ = new ContinuousTrigger$();

   public ContinuousTrigger apply(final String interval) {
      return new ContinuousTrigger(Triggers$.MODULE$.convert(interval));
   }

   public ContinuousTrigger apply(final Duration interval) {
      return new ContinuousTrigger(Triggers$.MODULE$.convert(interval));
   }

   public ContinuousTrigger create(final String interval) {
      return this.apply(interval);
   }

   public ContinuousTrigger create(final long interval, final TimeUnit unit) {
      return new ContinuousTrigger(Triggers$.MODULE$.convert(interval, unit));
   }

   public ContinuousTrigger apply(final long intervalMs) {
      return new ContinuousTrigger(intervalMs);
   }

   public Option unapply(final ContinuousTrigger x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(BoxesRunTime.boxToLong(x$0.intervalMs())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ContinuousTrigger$.class);
   }

   private ContinuousTrigger$() {
   }
}
