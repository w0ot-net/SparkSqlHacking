package org.apache.spark.streaming.scheduler;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class ErrorReported$ extends AbstractFunction2 implements Serializable {
   public static final ErrorReported$ MODULE$ = new ErrorReported$();

   public final String toString() {
      return "ErrorReported";
   }

   public ErrorReported apply(final String msg, final Throwable e) {
      return new ErrorReported(msg, e);
   }

   public Option unapply(final ErrorReported x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.msg(), x$0.e())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ErrorReported$.class);
   }

   private ErrorReported$() {
   }
}
