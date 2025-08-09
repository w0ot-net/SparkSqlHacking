package scala.math;

import scala.Function1;
import scala.Some;
import scala.runtime.ModuleSerializationProxy;

public class Ordering$Double$IeeeOrdering$ implements Ordering$Double$IeeeOrdering {
   public static final Ordering$Double$IeeeOrdering$ MODULE$ = new Ordering$Double$IeeeOrdering$();
   private static final long serialVersionUID = 5722631152457877238L;

   static {
      Ordering$Double$IeeeOrdering$ var10000 = MODULE$;
      var10000 = MODULE$;
      var10000 = MODULE$;
   }

   public int compare(final double x, final double y) {
      return Ordering$Double$IeeeOrdering.compare$(this, x, y);
   }

   public boolean lteq(final double x, final double y) {
      return Ordering$Double$IeeeOrdering.lteq$(this, x, y);
   }

   public boolean gteq(final double x, final double y) {
      return Ordering$Double$IeeeOrdering.gteq$(this, x, y);
   }

   public boolean lt(final double x, final double y) {
      return Ordering$Double$IeeeOrdering.lt$(this, x, y);
   }

   public boolean gt(final double x, final double y) {
      return Ordering$Double$IeeeOrdering.gt$(this, x, y);
   }

   public boolean equiv(final double x, final double y) {
      return Ordering$Double$IeeeOrdering.equiv$(this, x, y);
   }

   public double max(final double x, final double y) {
      return Ordering$Double$IeeeOrdering.max$(this, x, y);
   }

   public double min(final double x, final double y) {
      return Ordering$Double$IeeeOrdering.min$(this, x, y);
   }

   public Some tryCompare(final Object x, final Object y) {
      return Ordering.tryCompare$(this, x, y);
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

   private Object writeReplace() {
      return new ModuleSerializationProxy(Ordering$Double$IeeeOrdering$.class);
   }
}
