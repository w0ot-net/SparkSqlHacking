package breeze.stats.distributions;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.ModuleSerializationProxy;

public final class SinglePredicateRand$ implements Serializable {
   public static final SinglePredicateRand$ MODULE$ = new SinglePredicateRand$();

   public final String toString() {
      return "SinglePredicateRand";
   }

   public SinglePredicateRand apply(final Rand rand, final Function1 pred) {
      return new SinglePredicateRand(rand, pred);
   }

   public Option unapply(final SinglePredicateRand x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.rand(), x$0.pred())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SinglePredicateRand$.class);
   }

   public SinglePredicateRand apply$mDc$sp(final Rand rand, final Function1 pred) {
      return new SinglePredicateRand$mcD$sp(rand, pred);
   }

   public SinglePredicateRand apply$mIc$sp(final Rand rand, final Function1 pred) {
      return new SinglePredicateRand$mcI$sp(rand, pred);
   }

   public Option unapply$mDc$sp(final SinglePredicateRand x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.rand$mcD$sp(), x$0.pred$mcD$sp())));
   }

   public Option unapply$mIc$sp(final SinglePredicateRand x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.rand$mcI$sp(), x$0.pred$mcI$sp())));
   }

   private SinglePredicateRand$() {
   }
}
