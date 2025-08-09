package org.apache.spark.ml.feature;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class ColumnRef$ extends AbstractFunction1 implements Serializable {
   public static final ColumnRef$ MODULE$ = new ColumnRef$();

   public final String toString() {
      return "ColumnRef";
   }

   public ColumnRef apply(final String value) {
      return new ColumnRef(value);
   }

   public Option unapply(final ColumnRef x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.value()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ColumnRef$.class);
   }

   private ColumnRef$() {
   }
}
