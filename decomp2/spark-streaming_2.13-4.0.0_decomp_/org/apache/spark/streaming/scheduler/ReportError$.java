package org.apache.spark.streaming.scheduler;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.runtime.AbstractFunction3;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class ReportError$ extends AbstractFunction3 implements Serializable {
   public static final ReportError$ MODULE$ = new ReportError$();

   public final String toString() {
      return "ReportError";
   }

   public ReportError apply(final int streamId, final String message, final String error) {
      return new ReportError(streamId, message, error);
   }

   public Option unapply(final ReportError x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(BoxesRunTime.boxToInteger(x$0.streamId()), x$0.message(), x$0.error())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ReportError$.class);
   }

   private ReportError$() {
   }
}
