package scala.math;

import java.io.Serializable;
import java.util.Comparator;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.Some;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class Ordering$ implements LowPriorityOrderingImplicits, Serializable {
   public static final Ordering$ MODULE$ = new Ordering$();

   static {
      Ordering$ var10000 = MODULE$;
   }

   public Ordering ordered(final Function1 asComparable) {
      return LowPriorityOrderingImplicits.ordered$(this, asComparable);
   }

   public Ordering comparatorToOrdering(final Comparator cmp) {
      return LowPriorityOrderingImplicits.comparatorToOrdering$(this, cmp);
   }

   private final int reverseSeed() {
      return 41;
   }

   private final int optionSeed() {
      return 43;
   }

   private final int iterableSeed() {
      return 47;
   }

   public Ordering apply(final Ordering ord) {
      return ord;
   }

   public Ordering fromLessThan(final Function2 cmp) {
      return new Ordering(cmp) {
         private final Function2 cmp$2;

         public Some tryCompare(final Object x, final Object y) {
            return Ordering.tryCompare$(this, x, y);
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
            if (BoxesRunTime.unboxToBoolean(this.cmp$2.apply(x, y))) {
               return -1;
            } else {
               return BoxesRunTime.unboxToBoolean(this.cmp$2.apply(y, x)) ? 1 : 0;
            }
         }

         public boolean lt(final Object x, final Object y) {
            return BoxesRunTime.unboxToBoolean(this.cmp$2.apply(x, y));
         }

         public boolean gt(final Object x, final Object y) {
            return BoxesRunTime.unboxToBoolean(this.cmp$2.apply(y, x));
         }

         public boolean gteq(final Object x, final Object y) {
            return !BoxesRunTime.unboxToBoolean(this.cmp$2.apply(x, y));
         }

         public boolean lteq(final Object x, final Object y) {
            return !BoxesRunTime.unboxToBoolean(this.cmp$2.apply(y, x));
         }

         public {
            this.cmp$2 = cmp$2;
         }
      };
   }

   public Ordering by(final Function1 f, final Ordering ord) {
      return new Ordering(ord, f) {
         private final Ordering ord$2;
         private final Function1 f$3;

         public Some tryCompare(final Object x, final Object y) {
            return Ordering.tryCompare$(this, x, y);
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
            return this.ord$2.compare(this.f$3.apply(x), this.f$3.apply(y));
         }

         public boolean lt(final Object x, final Object y) {
            return this.ord$2.lt(this.f$3.apply(x), this.f$3.apply(y));
         }

         public boolean gt(final Object x, final Object y) {
            return this.ord$2.gt(this.f$3.apply(x), this.f$3.apply(y));
         }

         public boolean gteq(final Object x, final Object y) {
            return this.ord$2.gteq(this.f$3.apply(x), this.f$3.apply(y));
         }

         public boolean lteq(final Object x, final Object y) {
            return this.ord$2.lteq(this.f$3.apply(x), this.f$3.apply(y));
         }

         public {
            this.ord$2 = ord$2;
            this.f$3 = f$3;
         }
      };
   }

   public Ordering Option(final Ordering ord) {
      class O$1 implements Ordering.OptionOrdering {
         private static final long serialVersionUID = 6958068162830323876L;
         private final Ordering optionOrdering;

         public int compare(final Option x, final Option y) {
            return Ordering.OptionOrdering.compare$(this, x, y);
         }

         public boolean equals(final Object obj) {
            return Ordering.OptionOrdering.equals$(this, obj);
         }

         public int hashCode() {
            return Ordering.OptionOrdering.hashCode$(this);
         }

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

         public Ordering optionOrdering() {
            return this.optionOrdering;
         }

         public O$1(final Ordering ord$3) {
            this.optionOrdering = ord$3;
         }
      }

      return new O$1(ord);
   }

   /** @deprecated */
   public Ordering Iterable(final Ordering ord) {
      return new Ordering.IterableOrdering(ord);
   }

   public Ordering Tuple2(final Ordering ord1, final Ordering ord2) {
      return new Ordering.Tuple2Ordering(ord1, ord2);
   }

   public Ordering Tuple3(final Ordering ord1, final Ordering ord2, final Ordering ord3) {
      return new Ordering.Tuple3Ordering(ord1, ord2, ord3);
   }

   public Ordering Tuple4(final Ordering ord1, final Ordering ord2, final Ordering ord3, final Ordering ord4) {
      return new Ordering.Tuple4Ordering(ord1, ord2, ord3, ord4);
   }

   public Ordering Tuple5(final Ordering ord1, final Ordering ord2, final Ordering ord3, final Ordering ord4, final Ordering ord5) {
      return new Ordering.Tuple5Ordering(ord1, ord2, ord3, ord4, ord5);
   }

   public Ordering Tuple6(final Ordering ord1, final Ordering ord2, final Ordering ord3, final Ordering ord4, final Ordering ord5, final Ordering ord6) {
      return new Ordering.Tuple6Ordering(ord1, ord2, ord3, ord4, ord5, ord6);
   }

   public Ordering Tuple7(final Ordering ord1, final Ordering ord2, final Ordering ord3, final Ordering ord4, final Ordering ord5, final Ordering ord6, final Ordering ord7) {
      return new Ordering.Tuple7Ordering(ord1, ord2, ord3, ord4, ord5, ord6, ord7);
   }

   public Ordering Tuple8(final Ordering ord1, final Ordering ord2, final Ordering ord3, final Ordering ord4, final Ordering ord5, final Ordering ord6, final Ordering ord7, final Ordering ord8) {
      return new Ordering.Tuple8Ordering(ord1, ord2, ord3, ord4, ord5, ord6, ord7, ord8);
   }

   public Ordering Tuple9(final Ordering ord1, final Ordering ord2, final Ordering ord3, final Ordering ord4, final Ordering ord5, final Ordering ord6, final Ordering ord7, final Ordering ord8, final Ordering ord9) {
      return new Ordering.Tuple9Ordering(ord1, ord2, ord3, ord4, ord5, ord6, ord7, ord8, ord9);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Ordering$.class);
   }

   private Ordering$() {
   }
}
