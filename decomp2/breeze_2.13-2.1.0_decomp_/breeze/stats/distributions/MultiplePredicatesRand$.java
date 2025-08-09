package breeze.stats.distributions;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.ModuleSerializationProxy;

public final class MultiplePredicatesRand$ implements Serializable {
   public static final MultiplePredicatesRand$ MODULE$ = new MultiplePredicatesRand$();

   public final String toString() {
      return "MultiplePredicatesRand";
   }

   public MultiplePredicatesRand apply(final Rand rand, final Function1[] predicates) {
      return new MultiplePredicatesRand(rand, predicates);
   }

   public Option unapply(final MultiplePredicatesRand x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.rand(), x$0.predicates())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(MultiplePredicatesRand$.class);
   }

   public MultiplePredicatesRand apply$mDc$sp(final Rand rand, final Function1[] predicates) {
      return new MultiplePredicatesRand$mcD$sp(rand, predicates);
   }

   public MultiplePredicatesRand apply$mIc$sp(final Rand rand, final Function1[] predicates) {
      return new MultiplePredicatesRand$mcI$sp(rand, predicates);
   }

   public Option unapply$mDc$sp(final MultiplePredicatesRand x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.rand$mcD$sp(), x$0.predicates$mcD$sp())));
   }

   public Option unapply$mIc$sp(final MultiplePredicatesRand x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.rand$mcI$sp(), x$0.predicates$mcI$sp())));
   }

   private MultiplePredicatesRand$() {
   }
}
