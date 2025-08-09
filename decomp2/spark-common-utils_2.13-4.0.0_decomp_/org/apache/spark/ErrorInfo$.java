package org.apache.spark;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.collection.immutable.Seq;
import scala.runtime.AbstractFunction3;
import scala.runtime.ModuleSerializationProxy;

public final class ErrorInfo$ extends AbstractFunction3 implements Serializable {
   public static final ErrorInfo$ MODULE$ = new ErrorInfo$();

   public final String toString() {
      return "ErrorInfo";
   }

   public ErrorInfo apply(final Seq message, final Option subClass, final Option sqlState) {
      return new ErrorInfo(message, subClass, sqlState);
   }

   public Option unapply(final ErrorInfo x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(x$0.message(), x$0.subClass(), x$0.sqlState())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ErrorInfo$.class);
   }

   private ErrorInfo$() {
   }
}
