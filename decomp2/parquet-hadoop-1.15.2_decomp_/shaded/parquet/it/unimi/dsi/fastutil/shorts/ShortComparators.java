package shaded.parquet.it.unimi.dsi.fastutil.shorts;

import java.io.Serializable;
import java.util.Comparator;

public final class ShortComparators {
   public static final ShortComparator NATURAL_COMPARATOR = new NaturalImplicitComparator();
   public static final ShortComparator OPPOSITE_COMPARATOR = new OppositeImplicitComparator();

   private ShortComparators() {
   }

   public static ShortComparator oppositeComparator(ShortComparator c) {
      return (ShortComparator)(c instanceof OppositeComparator ? ((OppositeComparator)c).comparator : new OppositeComparator(c));
   }

   public static ShortComparator asShortComparator(final Comparator c) {
      return c != null && !(c instanceof ShortComparator) ? new ShortComparator() {
         public int compare(short x, short y) {
            return c.compare(x, y);
         }

         public int compare(Short x, Short y) {
            return c.compare(x, y);
         }
      } : (ShortComparator)c;
   }

   protected static class NaturalImplicitComparator implements ShortComparator, Serializable {
      private static final long serialVersionUID = 1L;

      public final int compare(short a, short b) {
         return Short.compare(a, b);
      }

      public ShortComparator reversed() {
         return ShortComparators.OPPOSITE_COMPARATOR;
      }

      private Object readResolve() {
         return ShortComparators.NATURAL_COMPARATOR;
      }
   }

   protected static class OppositeImplicitComparator implements ShortComparator, Serializable {
      private static final long serialVersionUID = 1L;

      public final int compare(short a, short b) {
         return -Short.compare(a, b);
      }

      public ShortComparator reversed() {
         return ShortComparators.NATURAL_COMPARATOR;
      }

      private Object readResolve() {
         return ShortComparators.OPPOSITE_COMPARATOR;
      }
   }

   protected static class OppositeComparator implements ShortComparator, Serializable {
      private static final long serialVersionUID = 1L;
      final ShortComparator comparator;

      protected OppositeComparator(ShortComparator c) {
         this.comparator = c;
      }

      public final int compare(short a, short b) {
         return this.comparator.compare(b, a);
      }

      public final ShortComparator reversed() {
         return this.comparator;
      }
   }
}
