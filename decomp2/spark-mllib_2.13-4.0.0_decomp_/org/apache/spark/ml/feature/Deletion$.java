package org.apache.spark.ml.feature;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class Deletion$ extends AbstractFunction1 implements Serializable {
   public static final Deletion$ MODULE$ = new Deletion$();

   public final String toString() {
      return "Deletion";
   }

   public Deletion apply(final Term term) {
      return new Deletion(term);
   }

   public Option unapply(final Deletion x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.term()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Deletion$.class);
   }

   private Deletion$() {
   }
}
