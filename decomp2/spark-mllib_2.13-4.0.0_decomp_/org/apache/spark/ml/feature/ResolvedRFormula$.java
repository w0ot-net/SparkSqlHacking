package org.apache.spark.ml.feature;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.collection.immutable.Seq;
import scala.runtime.AbstractFunction3;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class ResolvedRFormula$ extends AbstractFunction3 implements Serializable {
   public static final ResolvedRFormula$ MODULE$ = new ResolvedRFormula$();

   public final String toString() {
      return "ResolvedRFormula";
   }

   public ResolvedRFormula apply(final String label, final Seq terms, final boolean hasIntercept) {
      return new ResolvedRFormula(label, terms, hasIntercept);
   }

   public Option unapply(final ResolvedRFormula x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(x$0.label(), x$0.terms(), BoxesRunTime.boxToBoolean(x$0.hasIntercept()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ResolvedRFormula$.class);
   }

   private ResolvedRFormula$() {
   }
}
