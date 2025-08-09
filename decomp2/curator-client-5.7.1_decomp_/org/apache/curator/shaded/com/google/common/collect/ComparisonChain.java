package org.apache.curator.shaded.com.google.common.collect;

import java.util.Comparator;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;
import org.apache.curator.shaded.com.google.common.primitives.Booleans;
import org.apache.curator.shaded.com.google.common.primitives.Ints;
import org.apache.curator.shaded.com.google.common.primitives.Longs;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public abstract class ComparisonChain {
   private static final ComparisonChain ACTIVE = new ComparisonChain() {
      public ComparisonChain compare(Comparable left, Comparable right) {
         return this.classify(left.compareTo(right));
      }

      public ComparisonChain compare(@ParametricNullness Object left, @ParametricNullness Object right, Comparator comparator) {
         return this.classify(comparator.compare(left, right));
      }

      public ComparisonChain compare(int left, int right) {
         return this.classify(Ints.compare(left, right));
      }

      public ComparisonChain compare(long left, long right) {
         return this.classify(Longs.compare(left, right));
      }

      public ComparisonChain compare(float left, float right) {
         return this.classify(Float.compare(left, right));
      }

      public ComparisonChain compare(double left, double right) {
         return this.classify(Double.compare(left, right));
      }

      public ComparisonChain compareTrueFirst(boolean left, boolean right) {
         return this.classify(Booleans.compare(right, left));
      }

      public ComparisonChain compareFalseFirst(boolean left, boolean right) {
         return this.classify(Booleans.compare(left, right));
      }

      ComparisonChain classify(int result) {
         return result < 0 ? ComparisonChain.LESS : (result > 0 ? ComparisonChain.GREATER : ComparisonChain.ACTIVE);
      }

      public int result() {
         return 0;
      }
   };
   private static final ComparisonChain LESS = new InactiveComparisonChain(-1);
   private static final ComparisonChain GREATER = new InactiveComparisonChain(1);

   private ComparisonChain() {
   }

   public static ComparisonChain start() {
      return ACTIVE;
   }

   public abstract ComparisonChain compare(Comparable left, Comparable right);

   public abstract ComparisonChain compare(@ParametricNullness Object left, @ParametricNullness Object right, Comparator comparator);

   public abstract ComparisonChain compare(int left, int right);

   public abstract ComparisonChain compare(long left, long right);

   public abstract ComparisonChain compare(float left, float right);

   public abstract ComparisonChain compare(double left, double right);

   /** @deprecated */
   @Deprecated
   public final ComparisonChain compare(Boolean left, Boolean right) {
      return this.compareFalseFirst(left, right);
   }

   public abstract ComparisonChain compareTrueFirst(boolean left, boolean right);

   public abstract ComparisonChain compareFalseFirst(boolean left, boolean right);

   public abstract int result();

   private static final class InactiveComparisonChain extends ComparisonChain {
      final int result;

      InactiveComparisonChain(int result) {
         this.result = result;
      }

      public ComparisonChain compare(Comparable left, Comparable right) {
         return this;
      }

      public ComparisonChain compare(@ParametricNullness Object left, @ParametricNullness Object right, Comparator comparator) {
         return this;
      }

      public ComparisonChain compare(int left, int right) {
         return this;
      }

      public ComparisonChain compare(long left, long right) {
         return this;
      }

      public ComparisonChain compare(float left, float right) {
         return this;
      }

      public ComparisonChain compare(double left, double right) {
         return this;
      }

      public ComparisonChain compareTrueFirst(boolean left, boolean right) {
         return this;
      }

      public ComparisonChain compareFalseFirst(boolean left, boolean right) {
         return this;
      }

      public int result() {
         return this.result;
      }
   }
}
