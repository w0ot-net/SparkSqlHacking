package org.apache.spark.streaming;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class Duration$ extends AbstractFunction1 implements Serializable {
   public static final Duration$ MODULE$ = new Duration$();

   public final String toString() {
      return "Duration";
   }

   public Duration apply(final long millis) {
      return new Duration(millis);
   }

   public Option unapply(final Duration x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(BoxesRunTime.boxToLong(x$0.millis$access$0())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Duration$.class);
   }

   private Duration$() {
   }
}
