package cats.kernel;

import scala.Option;
import scala.collection.IterableOnce;

public interface Semigroup$mcD$sp extends Semigroup {
   default double combineN(final double a, final int n) {
      return this.combineN$mcD$sp(a, n);
   }

   default double combineN$mcD$sp(final double a, final int n) {
      if (n <= 0) {
         throw new IllegalArgumentException("Repeated combining for semigroups must have n > 0");
      } else {
         return this.repeatedCombineN$mcD$sp(a, n);
      }
   }

   default double repeatedCombineN(final double a, final int n) {
      return this.repeatedCombineN$mcD$sp(a, n);
   }

   default double repeatedCombineN$mcD$sp(final double a, final int n) {
      return n == 1 ? a : this.loop$2(a, n - 1, a);
   }

   default Semigroup reverse() {
      return this.reverse$mcD$sp();
   }

   default Semigroup reverse$mcD$sp() {
      return new Semigroup$mcD$sp() {
         // $FF: synthetic field
         private final Semigroup$mcD$sp $outer;

         public double repeatedCombineN(final double a, final int n) {
            return Semigroup$mcD$sp.super.repeatedCombineN(a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Semigroup$mcD$sp.super.repeatedCombineN$mcD$sp(a, n);
         }

         public Semigroup intercalate(final double middle) {
            return Semigroup$mcD$sp.super.intercalate(middle);
         }

         public Semigroup intercalate$mcD$sp(final double middle) {
            return Semigroup$mcD$sp.super.intercalate$mcD$sp(middle);
         }

         public float combine$mcF$sp(final float x, final float y) {
            return Semigroup.combine$mcF$sp$(this, x, y);
         }

         public int combine$mcI$sp(final int x, final int y) {
            return Semigroup.combine$mcI$sp$(this, x, y);
         }

         public long combine$mcJ$sp(final long x, final long y) {
            return Semigroup.combine$mcJ$sp$(this, x, y);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Semigroup.combineN$mcF$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Semigroup.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Semigroup.combineN$mcJ$sp$(this, a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Semigroup.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return Semigroup.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Semigroup.repeatedCombineN$mcJ$sp$(this, a, n);
         }

         public Option combineAllOption(final IterableOnce as) {
            return Semigroup.combineAllOption$(this, as);
         }

         public Semigroup reverse$mcF$sp() {
            return Semigroup.reverse$mcF$sp$(this);
         }

         public Semigroup reverse$mcI$sp() {
            return Semigroup.reverse$mcI$sp$(this);
         }

         public Semigroup reverse$mcJ$sp() {
            return Semigroup.reverse$mcJ$sp$(this);
         }

         public Semigroup intercalate$mcF$sp(final float middle) {
            return Semigroup.intercalate$mcF$sp$(this, middle);
         }

         public Semigroup intercalate$mcI$sp(final int middle) {
            return Semigroup.intercalate$mcI$sp$(this, middle);
         }

         public Semigroup intercalate$mcJ$sp(final long middle) {
            return Semigroup.intercalate$mcJ$sp$(this, middle);
         }

         public double combine(final double a, final double b) {
            return this.combine$mcD$sp(a, b);
         }

         public double combineN(final double a, final int n) {
            return this.combineN$mcD$sp(a, n);
         }

         public Semigroup reverse() {
            return this.reverse$mcD$sp();
         }

         public double combine$mcD$sp(final double a, final double b) {
            return this.$outer.combine$mcD$sp(b, a);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return this.$outer.combineN$mcD$sp(a, n);
         }

         public Semigroup reverse$mcD$sp() {
            return this.$outer;
         }

         public {
            if (Semigroup$mcD$sp.this == null) {
               throw null;
            } else {
               this.$outer = Semigroup$mcD$sp.this;
               Semigroup.$init$(this);
            }
         }
      };
   }

   default Semigroup intercalate(final double middle) {
      return this.intercalate$mcD$sp(middle);
   }

   default Semigroup intercalate$mcD$sp(final double middle) {
      return new Semigroup$mcD$sp(middle) {
         // $FF: synthetic field
         private final Semigroup$mcD$sp $outer;
         private final double middle$2;

         public double combineN(final double a, final int n) {
            return Semigroup$mcD$sp.super.combineN(a, n);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Semigroup$mcD$sp.super.combineN$mcD$sp(a, n);
         }

         public double repeatedCombineN(final double a, final int n) {
            return Semigroup$mcD$sp.super.repeatedCombineN(a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Semigroup$mcD$sp.super.repeatedCombineN$mcD$sp(a, n);
         }

         public Semigroup reverse() {
            return Semigroup$mcD$sp.super.reverse();
         }

         public Semigroup reverse$mcD$sp() {
            return Semigroup$mcD$sp.super.reverse$mcD$sp();
         }

         public Semigroup intercalate(final double middle) {
            return Semigroup$mcD$sp.super.intercalate(middle);
         }

         public Semigroup intercalate$mcD$sp(final double middle) {
            return Semigroup$mcD$sp.super.intercalate$mcD$sp(middle);
         }

         public float combine$mcF$sp(final float x, final float y) {
            return Semigroup.combine$mcF$sp$(this, x, y);
         }

         public int combine$mcI$sp(final int x, final int y) {
            return Semigroup.combine$mcI$sp$(this, x, y);
         }

         public long combine$mcJ$sp(final long x, final long y) {
            return Semigroup.combine$mcJ$sp$(this, x, y);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Semigroup.combineN$mcF$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Semigroup.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Semigroup.combineN$mcJ$sp$(this, a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Semigroup.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return Semigroup.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Semigroup.repeatedCombineN$mcJ$sp$(this, a, n);
         }

         public Option combineAllOption(final IterableOnce as) {
            return Semigroup.combineAllOption$(this, as);
         }

         public Semigroup reverse$mcF$sp() {
            return Semigroup.reverse$mcF$sp$(this);
         }

         public Semigroup reverse$mcI$sp() {
            return Semigroup.reverse$mcI$sp$(this);
         }

         public Semigroup reverse$mcJ$sp() {
            return Semigroup.reverse$mcJ$sp$(this);
         }

         public Semigroup intercalate$mcF$sp(final float middle) {
            return Semigroup.intercalate$mcF$sp$(this, middle);
         }

         public Semigroup intercalate$mcI$sp(final int middle) {
            return Semigroup.intercalate$mcI$sp$(this, middle);
         }

         public Semigroup intercalate$mcJ$sp(final long middle) {
            return Semigroup.intercalate$mcJ$sp$(this, middle);
         }

         public double combine(final double a, final double b) {
            return this.combine$mcD$sp(a, b);
         }

         public double combine$mcD$sp(final double a, final double b) {
            return this.$outer.combine$mcD$sp(a, this.$outer.combine$mcD$sp(this.middle$2, b));
         }

         public {
            if (Semigroup$mcD$sp.this == null) {
               throw null;
            } else {
               this.$outer = Semigroup$mcD$sp.this;
               this.middle$2 = middle$2;
               Semigroup.$init$(this);
            }
         }
      };
   }

   private double loop$2(final double b, final int k, final double extra) {
      while(k != 1) {
         double x = (k & 1) == 1 ? this.combine$mcD$sp(b, extra) : extra;
         double var10000 = this.combine$mcD$sp(b, b);
         int var10001 = k >>> 1;
         extra = x;
         k = var10001;
         b = var10000;
      }

      return this.combine$mcD$sp(b, extra);
   }
}
