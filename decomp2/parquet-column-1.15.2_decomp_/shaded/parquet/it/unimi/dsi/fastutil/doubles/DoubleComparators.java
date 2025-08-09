package shaded.parquet.it.unimi.dsi.fastutil.doubles;

import java.io.Serializable;
import java.util.Comparator;

public final class DoubleComparators {
   public static final DoubleComparator NATURAL_COMPARATOR = new NaturalImplicitComparator();
   public static final DoubleComparator OPPOSITE_COMPARATOR = new OppositeImplicitComparator();

   private DoubleComparators() {
   }

   public static DoubleComparator oppositeComparator(DoubleComparator c) {
      return (DoubleComparator)(c instanceof OppositeComparator ? ((OppositeComparator)c).comparator : new OppositeComparator(c));
   }

   public static DoubleComparator asDoubleComparator(final Comparator c) {
      return c != null && !(c instanceof DoubleComparator) ? new DoubleComparator() {
         public int compare(double x, double y) {
            return c.compare(x, y);
         }

         public int compare(Double x, Double y) {
            return c.compare(x, y);
         }
      } : (DoubleComparator)c;
   }

   protected static class NaturalImplicitComparator implements DoubleComparator, Serializable {
      private static final long serialVersionUID = 1L;

      public final int compare(double a, double b) {
         return Double.compare(a, b);
      }

      public DoubleComparator reversed() {
         return DoubleComparators.OPPOSITE_COMPARATOR;
      }

      private Object readResolve() {
         return DoubleComparators.NATURAL_COMPARATOR;
      }
   }

   protected static class OppositeImplicitComparator implements DoubleComparator, Serializable {
      private static final long serialVersionUID = 1L;

      public final int compare(double a, double b) {
         return -Double.compare(a, b);
      }

      public DoubleComparator reversed() {
         return DoubleComparators.NATURAL_COMPARATOR;
      }

      private Object readResolve() {
         return DoubleComparators.OPPOSITE_COMPARATOR;
      }
   }

   protected static class OppositeComparator implements DoubleComparator, Serializable {
      private static final long serialVersionUID = 1L;
      final DoubleComparator comparator;

      protected OppositeComparator(DoubleComparator c) {
         this.comparator = c;
      }

      public final int compare(double a, double b) {
         return this.comparator.compare(b, a);
      }

      public final DoubleComparator reversed() {
         return this.comparator;
      }
   }
}
