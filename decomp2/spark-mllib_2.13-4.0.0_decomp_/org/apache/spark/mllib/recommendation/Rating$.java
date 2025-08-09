package org.apache.spark.mllib.recommendation;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.runtime.AbstractFunction3;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class Rating$ extends AbstractFunction3 implements Serializable {
   public static final Rating$ MODULE$ = new Rating$();

   public final String toString() {
      return "Rating";
   }

   public Rating apply(final int user, final int product, final double rating) {
      return new Rating(user, product, rating);
   }

   public Option unapply(final Rating x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(BoxesRunTime.boxToInteger(x$0.user()), BoxesRunTime.boxToInteger(x$0.product()), BoxesRunTime.boxToDouble(x$0.rating()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Rating$.class);
   }

   private Rating$() {
   }
}
