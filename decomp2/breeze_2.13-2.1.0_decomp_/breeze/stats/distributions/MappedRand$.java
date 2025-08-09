package breeze.stats.distributions;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.ModuleSerializationProxy;

public final class MappedRand$ implements Serializable {
   public static final MappedRand$ MODULE$ = new MappedRand$();

   public final String toString() {
      return "MappedRand";
   }

   public MappedRand apply(final Rand rand, final Function1 func) {
      return new MappedRand(rand, func);
   }

   public Option unapply(final MappedRand x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.rand(), x$0.func())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(MappedRand$.class);
   }

   public MappedRand apply$mDDc$sp(final Rand rand, final Function1 func) {
      return new MappedRand$mcDD$sp(rand, func);
   }

   public MappedRand apply$mDIc$sp(final Rand rand, final Function1 func) {
      return new MappedRand$mcDI$sp(rand, func);
   }

   public MappedRand apply$mIDc$sp(final Rand rand, final Function1 func) {
      return new MappedRand$mcID$sp(rand, func);
   }

   public MappedRand apply$mIIc$sp(final Rand rand, final Function1 func) {
      return new MappedRand$mcII$sp(rand, func);
   }

   public Option unapply$mDDc$sp(final MappedRand x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.rand$mcD$sp(), x$0.func$mcDD$sp())));
   }

   public Option unapply$mDIc$sp(final MappedRand x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.rand$mcD$sp(), x$0.func$mcDI$sp())));
   }

   public Option unapply$mIDc$sp(final MappedRand x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.rand$mcI$sp(), x$0.func$mcID$sp())));
   }

   public Option unapply$mIIc$sp(final MappedRand x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.rand$mcI$sp(), x$0.func$mcII$sp())));
   }

   private MappedRand$() {
   }
}
