package breeze.util;

import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Some;
import scala.collection.IterableOnce;
import scala.math.Ordering;
import scala.math.PartialOrdering;

public final class TopK$ {
   public static final TopK$ MODULE$ = new TopK$();

   public TopK apply(final int k, final IterableOnce items, final Ordering ord) {
      TopK topk = new TopK(k, ord);
      items.iterator().foreach((x$1) -> topk.$plus$eq(x$1));
      return topk;
   }

   public TopK apply(final int k, final IterableOnce items, final Function1 scoreFn, final Ordering uord) {
      Ordering ord = new Ordering(uord, scoreFn) {
         private final Ordering uord$1;
         private final Function1 scoreFn$1;

         public Some tryCompare(final Object x, final Object y) {
            return Ordering.tryCompare$(this, x, y);
         }

         public boolean lteq(final Object x, final Object y) {
            return Ordering.lteq$(this, x, y);
         }

         public boolean gteq(final Object x, final Object y) {
            return Ordering.gteq$(this, x, y);
         }

         public boolean lt(final Object x, final Object y) {
            return Ordering.lt$(this, x, y);
         }

         public boolean gt(final Object x, final Object y) {
            return Ordering.gt$(this, x, y);
         }

         public boolean equiv(final Object x, final Object y) {
            return Ordering.equiv$(this, x, y);
         }

         public Object max(final Object x, final Object y) {
            return Ordering.max$(this, x, y);
         }

         public Object min(final Object x, final Object y) {
            return Ordering.min$(this, x, y);
         }

         public Ordering reverse() {
            return Ordering.reverse$(this);
         }

         public boolean isReverseOf(final Ordering other) {
            return Ordering.isReverseOf$(this, other);
         }

         public Ordering on(final Function1 f) {
            return Ordering.on$(this, f);
         }

         public Ordering orElse(final Ordering other) {
            return Ordering.orElse$(this, other);
         }

         public Ordering orElseBy(final Function1 f, final Ordering ord) {
            return Ordering.orElseBy$(this, f, ord);
         }

         public Ordering.OrderingOps mkOrderingOps(final Object lhs) {
            return Ordering.mkOrderingOps$(this, lhs);
         }

         public int compare(final Object x, final Object y) {
            return this.uord$1.compare(this.scoreFn$1.apply(x), this.scoreFn$1.apply(y));
         }

         public {
            this.uord$1 = uord$1;
            this.scoreFn$1 = scoreFn$1;
            PartialOrdering.$init$(this);
            Ordering.$init$(this);
         }
      };
      return this.apply(k, items, ord);
   }

   private TopK$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
