package breeze.stats.distributions;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.ModuleSerializationProxy;

public final class FlatMappedRand$ implements Serializable {
   public static final FlatMappedRand$ MODULE$ = new FlatMappedRand$();

   public final String toString() {
      return "FlatMappedRand";
   }

   public FlatMappedRand apply(final Rand rand, final Function1 func) {
      return new FlatMappedRand(rand, func);
   }

   public Option unapply(final FlatMappedRand x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.rand(), x$0.func())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(FlatMappedRand$.class);
   }

   public FlatMappedRand apply$mDDc$sp(final Rand rand, final Function1 func) {
      return new FlatMappedRand$mcDD$sp(rand, func);
   }

   public FlatMappedRand apply$mDIc$sp(final Rand rand, final Function1 func) {
      return new FlatMappedRand$mcDI$sp(rand, func);
   }

   public FlatMappedRand apply$mIDc$sp(final Rand rand, final Function1 func) {
      return new FlatMappedRand$mcID$sp(rand, func);
   }

   public FlatMappedRand apply$mIIc$sp(final Rand rand, final Function1 func) {
      return new FlatMappedRand$mcII$sp(rand, func);
   }

   public Option unapply$mDDc$sp(final FlatMappedRand x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.rand$mcD$sp(), x$0.func$mcDD$sp())));
   }

   public Option unapply$mDIc$sp(final FlatMappedRand x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.rand$mcD$sp(), x$0.func$mcDI$sp())));
   }

   public Option unapply$mIDc$sp(final FlatMappedRand x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.rand$mcI$sp(), x$0.func$mcID$sp())));
   }

   public Option unapply$mIIc$sp(final FlatMappedRand x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.rand$mcI$sp(), x$0.func$mcII$sp())));
   }

   private FlatMappedRand$() {
   }
}
