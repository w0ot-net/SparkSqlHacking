package org.apache.spark.sql.types;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class FixedLength$ extends AbstractFunction1 implements Serializable {
   public static final FixedLength$ MODULE$ = new FixedLength$();

   public final String toString() {
      return "FixedLength";
   }

   public FixedLength apply(final int length) {
      return new FixedLength(length);
   }

   public Option unapply(final FixedLength x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(BoxesRunTime.boxToInteger(x$0.length())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(FixedLength$.class);
   }

   private FixedLength$() {
   }
}
