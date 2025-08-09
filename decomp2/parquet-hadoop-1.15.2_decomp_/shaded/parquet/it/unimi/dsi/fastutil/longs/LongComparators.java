package shaded.parquet.it.unimi.dsi.fastutil.longs;

import java.io.Serializable;
import java.util.Comparator;

public final class LongComparators {
   public static final LongComparator NATURAL_COMPARATOR = new NaturalImplicitComparator();
   public static final LongComparator OPPOSITE_COMPARATOR = new OppositeImplicitComparator();

   private LongComparators() {
   }

   public static LongComparator oppositeComparator(LongComparator c) {
      return (LongComparator)(c instanceof OppositeComparator ? ((OppositeComparator)c).comparator : new OppositeComparator(c));
   }

   public static LongComparator asLongComparator(final Comparator c) {
      return c != null && !(c instanceof LongComparator) ? new LongComparator() {
         public int compare(long x, long y) {
            return c.compare(x, y);
         }

         public int compare(Long x, Long y) {
            return c.compare(x, y);
         }
      } : (LongComparator)c;
   }

   protected static class NaturalImplicitComparator implements LongComparator, Serializable {
      private static final long serialVersionUID = 1L;

      public final int compare(long a, long b) {
         return Long.compare(a, b);
      }

      public LongComparator reversed() {
         return LongComparators.OPPOSITE_COMPARATOR;
      }

      private Object readResolve() {
         return LongComparators.NATURAL_COMPARATOR;
      }
   }

   protected static class OppositeImplicitComparator implements LongComparator, Serializable {
      private static final long serialVersionUID = 1L;

      public final int compare(long a, long b) {
         return -Long.compare(a, b);
      }

      public LongComparator reversed() {
         return LongComparators.NATURAL_COMPARATOR;
      }

      private Object readResolve() {
         return LongComparators.OPPOSITE_COMPARATOR;
      }
   }

   protected static class OppositeComparator implements LongComparator, Serializable {
      private static final long serialVersionUID = 1L;
      final LongComparator comparator;

      protected OppositeComparator(LongComparator c) {
         this.comparator = c;
      }

      public final int compare(long a, long b) {
         return this.comparator.compare(b, a);
      }

      public final LongComparator reversed() {
         return this.comparator;
      }
   }
}
