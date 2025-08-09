package org.apache.spark;

import java.io.Serializable;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.runtime.ModuleSerializationProxy;

public final class Aggregator$ implements Serializable {
   public static final Aggregator$ MODULE$ = new Aggregator$();

   public final String toString() {
      return "Aggregator";
   }

   public Aggregator apply(final Function1 createCombiner, final Function2 mergeValue, final Function2 mergeCombiners) {
      return new Aggregator(createCombiner, mergeValue, mergeCombiners);
   }

   public Option unapply(final Aggregator x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(x$0.createCombiner(), x$0.mergeValue(), x$0.mergeCombiners())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Aggregator$.class);
   }

   private Aggregator$() {
   }
}
