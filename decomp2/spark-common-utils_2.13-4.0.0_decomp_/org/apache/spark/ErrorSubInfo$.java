package org.apache.spark;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.collection.immutable.Seq;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class ErrorSubInfo$ extends AbstractFunction1 implements Serializable {
   public static final ErrorSubInfo$ MODULE$ = new ErrorSubInfo$();

   public final String toString() {
      return "ErrorSubInfo";
   }

   public ErrorSubInfo apply(final Seq message) {
      return new ErrorSubInfo(message);
   }

   public Option unapply(final ErrorSubInfo x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.message()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ErrorSubInfo$.class);
   }

   private ErrorSubInfo$() {
   }
}
