package scala.math;

import java.util.Comparator;
import scala.Function1;
import scala.Some;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000553q!\u0002\u0004\u0011\u0002\u0007\u00051\u0002C\u0003\u0011\u0001\u0011\u0005\u0011#\u0002\u0003\u0016\u0001\u00011\u0002\"\u0002\u001a\u0001\t\u0007\u0019\u0004\"B \u0001\t\u0007\u0001%\u0001\b'poB\u0013\u0018n\u001c:jif|%\u000fZ3sS:<\u0017*\u001c9mS\u000eLGo\u001d\u0006\u0003\u000f!\tA!\\1uQ*\t\u0011\"A\u0003tG\u0006d\u0017m\u0001\u0001\u0014\u0005\u0001a\u0001CA\u0007\u000f\u001b\u0005A\u0011BA\b\t\u0005\u0019\te.\u001f*fM\u00061A%\u001b8ji\u0012\"\u0012A\u0005\t\u0003\u001bMI!\u0001\u0006\u0005\u0003\tUs\u0017\u000e\u001e\u0002\r\u0003N\u001cu.\u001c9be\u0006\u0014G.Z\u000b\u0003/q\u0001B!\u0004\r\u001bK%\u0011\u0011\u0004\u0003\u0002\n\rVt7\r^5p]F\u0002\"a\u0007\u000f\r\u0001\u0011)QD\u0001b\u0001=\t\t\u0011)\u0005\u0002 EA\u0011Q\u0002I\u0005\u0003C!\u0011qAT8uQ&tw\r\u0005\u0002\u000eG%\u0011A\u0005\u0003\u0002\u0004\u0003:L\bG\u0001\u00140!\r9CFL\u0007\u0002Q)\u0011\u0011FK\u0001\u0005Y\u0006twMC\u0001,\u0003\u0011Q\u0017M^1\n\u00055B#AC\"p[B\f'/\u00192mKB\u00111d\f\u0003\na\t\t\t\u0011!A\u0003\u0002E\u00121a\u0018\u00133#\tQ\"%A\u0004pe\u0012,'/\u001a3\u0016\u0005QRDCA\u001b<!\r1t'O\u0007\u0002\r%\u0011\u0001H\u0002\u0002\t\u001fJ$WM]5oOB\u00111D\u000f\u0003\u0006;\r\u0011\rA\b\u0005\u0006y\r\u0001\u001d!P\u0001\rCN\u001cu.\u001c9be\u0006\u0014G.\u001a\t\u0004}\tIT\"\u0001\u0001\u0002)\r|W\u000e]1sCR|'\u000fV8Pe\u0012,'/\u001b8h+\t\tE\t\u0006\u0002C\u000bB\u0019agN\"\u0011\u0005m!E!B\u000f\u0005\u0005\u0004q\u0002\"\u0002$\u0005\u0001\b9\u0015aA2naB\u0019\u0001jS\"\u000e\u0003%S!A\u0013\u0016\u0002\tU$\u0018\u000e\\\u0005\u0003\u0019&\u0013!bQ8na\u0006\u0014\u0018\r^8s\u0001"
)
public interface LowPriorityOrderingImplicits {
   // $FF: synthetic method
   static Ordering ordered$(final LowPriorityOrderingImplicits $this, final Function1 asComparable) {
      return $this.ordered(asComparable);
   }

   default Ordering ordered(final Function1 asComparable) {
      return new Ordering(asComparable) {
         private final Function1 asComparable$1;

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
            return ((Comparable)this.asComparable$1.apply(x)).compareTo(y);
         }

         public {
            this.asComparable$1 = asComparable$1;
         }
      };
   }

   // $FF: synthetic method
   static Ordering comparatorToOrdering$(final LowPriorityOrderingImplicits $this, final Comparator cmp) {
      return $this.comparatorToOrdering(cmp);
   }

   default Ordering comparatorToOrdering(final Comparator cmp) {
      return new Ordering(cmp) {
         private final Comparator cmp$1;

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
            return this.cmp$1.compare(x, y);
         }

         public {
            this.cmp$1 = cmp$1;
         }
      };
   }

   static void $init$(final LowPriorityOrderingImplicits $this) {
   }
}
