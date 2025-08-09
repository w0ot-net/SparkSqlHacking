package shaded.parquet.it.unimi.dsi.fastutil.bytes;

import java.io.Serializable;
import java.util.Comparator;

public final class ByteComparators {
   public static final ByteComparator NATURAL_COMPARATOR = new NaturalImplicitComparator();
   public static final ByteComparator OPPOSITE_COMPARATOR = new OppositeImplicitComparator();

   private ByteComparators() {
   }

   public static ByteComparator oppositeComparator(ByteComparator c) {
      return (ByteComparator)(c instanceof OppositeComparator ? ((OppositeComparator)c).comparator : new OppositeComparator(c));
   }

   public static ByteComparator asByteComparator(final Comparator c) {
      return c != null && !(c instanceof ByteComparator) ? new ByteComparator() {
         public int compare(byte x, byte y) {
            return c.compare(x, y);
         }

         public int compare(Byte x, Byte y) {
            return c.compare(x, y);
         }
      } : (ByteComparator)c;
   }

   protected static class NaturalImplicitComparator implements ByteComparator, Serializable {
      private static final long serialVersionUID = 1L;

      public final int compare(byte a, byte b) {
         return Byte.compare(a, b);
      }

      public ByteComparator reversed() {
         return ByteComparators.OPPOSITE_COMPARATOR;
      }

      private Object readResolve() {
         return ByteComparators.NATURAL_COMPARATOR;
      }
   }

   protected static class OppositeImplicitComparator implements ByteComparator, Serializable {
      private static final long serialVersionUID = 1L;

      public final int compare(byte a, byte b) {
         return -Byte.compare(a, b);
      }

      public ByteComparator reversed() {
         return ByteComparators.NATURAL_COMPARATOR;
      }

      private Object readResolve() {
         return ByteComparators.OPPOSITE_COMPARATOR;
      }
   }

   protected static class OppositeComparator implements ByteComparator, Serializable {
      private static final long serialVersionUID = 1L;
      final ByteComparator comparator;

      protected OppositeComparator(ByteComparator c) {
         this.comparator = c;
      }

      public final int compare(byte a, byte b) {
         return this.comparator.compare(b, a);
      }

      public final ByteComparator reversed() {
         return this.comparator;
      }
   }
}
