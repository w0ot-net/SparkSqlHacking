package org.apache.spark.ml.feature;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.collection.immutable.Seq;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class Terms$ extends AbstractFunction1 implements Serializable {
   public static final Terms$ MODULE$ = new Terms$();

   public final String toString() {
      return "Terms";
   }

   public Terms apply(final Seq terms) {
      return new Terms(terms);
   }

   public Option unapply(final Terms x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.terms()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Terms$.class);
   }

   private Terms$() {
   }
}
