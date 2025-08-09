package scala.math;

import scala.Function1;
import scala.Some;
import scala.runtime.ModuleSerializationProxy;

public class Ordering$Float$TotalOrdering$ implements Ordering$Float$TotalOrdering {
   public static final Ordering$Float$TotalOrdering$ MODULE$ = new Ordering$Float$TotalOrdering$();
   private static final long serialVersionUID = 2951539161283192433L;

   static {
      Ordering$Float$TotalOrdering$ var10000 = MODULE$;
      var10000 = MODULE$;
      var10000 = MODULE$;
   }

   public int compare(final float x, final float y) {
      return Ordering$Float$TotalOrdering.compare$(this, x, y);
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

   private Object writeReplace() {
      return new ModuleSerializationProxy(Ordering$Float$TotalOrdering$.class);
   }
}
