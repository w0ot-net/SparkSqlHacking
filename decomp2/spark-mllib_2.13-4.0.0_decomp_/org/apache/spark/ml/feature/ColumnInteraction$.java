package org.apache.spark.ml.feature;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.collection.immutable.Seq;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class ColumnInteraction$ extends AbstractFunction1 implements Serializable {
   public static final ColumnInteraction$ MODULE$ = new ColumnInteraction$();

   public final String toString() {
      return "ColumnInteraction";
   }

   public ColumnInteraction apply(final Seq terms) {
      return new ColumnInteraction(terms);
   }

   public Option unapply(final ColumnInteraction x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.terms()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ColumnInteraction$.class);
   }

   private ColumnInteraction$() {
   }
}
