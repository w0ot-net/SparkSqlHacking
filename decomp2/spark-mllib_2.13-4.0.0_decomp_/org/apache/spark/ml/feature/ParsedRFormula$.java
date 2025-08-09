package org.apache.spark.ml.feature;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.collection.immutable.Seq;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class ParsedRFormula$ extends AbstractFunction2 implements Serializable {
   public static final ParsedRFormula$ MODULE$ = new ParsedRFormula$();

   public final String toString() {
      return "ParsedRFormula";
   }

   public ParsedRFormula apply(final ColumnRef label, final Seq terms) {
      return new ParsedRFormula(label, terms);
   }

   public Option unapply(final ParsedRFormula x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.label(), x$0.terms())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ParsedRFormula$.class);
   }

   private ParsedRFormula$() {
   }
}
