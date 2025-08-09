package scala.math;

import scala.Function1;
import scala.Some;
import scala.runtime.ModuleSerializationProxy;

public class Ordering$Float$IeeeOrdering$ implements Ordering$Float$IeeeOrdering {
   public static final Ordering$Float$IeeeOrdering$ MODULE$ = new Ordering$Float$IeeeOrdering$();
   private static final long serialVersionUID = 2142189527751553605L;

   static {
      Ordering$Float$IeeeOrdering$ var10000 = MODULE$;
      var10000 = MODULE$;
      var10000 = MODULE$;
   }

   public int compare(final float x, final float y) {
      return Ordering$Float$IeeeOrdering.compare$(this, x, y);
   }

   public boolean lteq(final float x, final float y) {
      return Ordering$Float$IeeeOrdering.lteq$(this, x, y);
   }

   public boolean gteq(final float x, final float y) {
      return Ordering$Float$IeeeOrdering.gteq$(this, x, y);
   }

   public boolean lt(final float x, final float y) {
      return Ordering$Float$IeeeOrdering.lt$(this, x, y);
   }

   public boolean gt(final float x, final float y) {
      return Ordering$Float$IeeeOrdering.gt$(this, x, y);
   }

   public boolean equiv(final float x, final float y) {
      return Ordering$Float$IeeeOrdering.equiv$(this, x, y);
   }

   public float max(final float x, final float y) {
      return Ordering$Float$IeeeOrdering.max$(this, x, y);
   }

   public float min(final float x, final float y) {
      return Ordering$Float$IeeeOrdering.min$(this, x, y);
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
      return new ModuleSerializationProxy(Ordering$Float$IeeeOrdering$.class);
   }
}
