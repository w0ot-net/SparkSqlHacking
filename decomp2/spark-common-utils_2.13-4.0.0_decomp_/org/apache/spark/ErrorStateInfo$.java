package org.apache.spark;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple4;
import scala.None.;
import scala.collection.immutable.List;
import scala.runtime.AbstractFunction4;
import scala.runtime.ModuleSerializationProxy;

public final class ErrorStateInfo$ extends AbstractFunction4 implements Serializable {
   public static final ErrorStateInfo$ MODULE$ = new ErrorStateInfo$();

   public final String toString() {
      return "ErrorStateInfo";
   }

   public ErrorStateInfo apply(final String description, final String origin, final String standard, final List usedBy) {
      return new ErrorStateInfo(description, origin, standard, usedBy);
   }

   public Option unapply(final ErrorStateInfo x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple4(x$0.description(), x$0.origin(), x$0.standard(), x$0.usedBy())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ErrorStateInfo$.class);
   }

   private ErrorStateInfo$() {
   }
}
