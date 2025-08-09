package org.apache.spark.sql.types;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class MaxLength$ extends AbstractFunction1 implements Serializable {
   public static final MaxLength$ MODULE$ = new MaxLength$();

   public final String toString() {
      return "MaxLength";
   }

   public MaxLength apply(final int length) {
      return new MaxLength(length);
   }

   public Option unapply(final MaxLength x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(BoxesRunTime.boxToInteger(x$0.length())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(MaxLength$.class);
   }

   private MaxLength$() {
   }
}
