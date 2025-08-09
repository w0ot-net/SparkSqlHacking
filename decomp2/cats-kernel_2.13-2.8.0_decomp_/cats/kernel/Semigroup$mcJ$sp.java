package cats.kernel;

import scala.Option;
import scala.collection.IterableOnce;

public interface Semigroup$mcJ$sp extends Semigroup {
   default long combineN(final long a, final int n) {
      return this.combineN$mcJ$sp(a, n);
   }

   default long combineN$mcJ$sp(final long a, final int n) {
      if (n <= 0) {
         throw new IllegalArgumentException("Repeated combining for semigroups must have n > 0");
      } else {
         return this.repeatedCombineN$mcJ$sp(a, n);
      }
   }

   default long repeatedCombineN(final long a, final int n) {
      return this.repeatedCombineN$mcJ$sp(a, n);
   }

   default long repeatedCombineN$mcJ$sp(final long a, final int n) {
      return n == 1 ? a : this.loop$5(a, n - 1, a);
   }

   default Semigroup reverse() {
      return this.reverse$mcJ$sp();
   }

   default Semigroup reverse$mcJ$sp() {
      return new Semigroup$mcJ$sp() {
         // $FF: synthetic field
         private final Semigroup$mcJ$sp $outer;

         public long repeatedCombineN(final long a, final int n) {
            return Semigroup$mcJ$sp.super.repeatedCombineN(a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Semigroup$mcJ$sp.super.repeatedCombineN$mcJ$sp(a, n);
         }

         public Semigroup intercalate(final long middle) {
            return Semigroup$mcJ$sp.super.intercalate(middle);
         }

         public Semigroup intercalate$mcJ$sp(final long middle) {
            return Semigroup$mcJ$sp.super.intercalate$mcJ$sp(middle);
         }

         public double combine$mcD$sp(final double x, final double y) {
            return Semigroup.combine$mcD$sp$(this, x, y);
         }

         public float combine$mcF$sp(final float x, final float y) {
            return Semigroup.combine$mcF$sp$(this, x, y);
         }

         public int combine$mcI$sp(final int x, final int y) {
            return Semigroup.combine$mcI$sp$(this, x, y);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Semigroup.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Semigroup.combineN$mcF$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Semigroup.combineN$mcI$sp$(this, a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Semigroup.repeatedCombineN$mcD$sp$(this, a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Semigroup.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return Semigroup.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public Option combineAllOption(final IterableOnce as) {
            return Semigroup.combineAllOption$(this, as);
         }

         public Semigroup reverse$mcD$sp() {
            return Semigroup.reverse$mcD$sp$(this);
         }

         public Semigroup reverse$mcF$sp() {
            return Semigroup.reverse$mcF$sp$(this);
         }

         public Semigroup reverse$mcI$sp() {
            return Semigroup.reverse$mcI$sp$(this);
         }

         public Semigroup intercalate$mcD$sp(final double middle) {
            return Semigroup.intercalate$mcD$sp$(this, middle);
         }

         public Semigroup intercalate$mcF$sp(final float middle) {
            return Semigroup.intercalate$mcF$sp$(this, middle);
         }

         public Semigroup intercalate$mcI$sp(final int middle) {
            return Semigroup.intercalate$mcI$sp$(this, middle);
         }

         public long combine(final long a, final long b) {
            return this.combine$mcJ$sp(a, b);
         }

         public long combineN(final long a, final int n) {
            return this.combineN$mcJ$sp(a, n);
         }

         public Semigroup reverse() {
            return this.reverse$mcJ$sp();
         }

         public long combine$mcJ$sp(final long a, final long b) {
            return this.$outer.combine$mcJ$sp(b, a);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return this.$outer.combineN$mcJ$sp(a, n);
         }

         public Semigroup reverse$mcJ$sp() {
            return this.$outer;
         }

         public {
            if (Semigroup$mcJ$sp.this == null) {
               throw null;
            } else {
               this.$outer = Semigroup$mcJ$sp.this;
               Semigroup.$init$(this);
            }
         }
      };
   }

   default Semigroup intercalate(final long middle) {
      return this.intercalate$mcJ$sp(middle);
   }

   default Semigroup intercalate$mcJ$sp(final long middle) {
      return new Semigroup$mcJ$sp(middle) {
         // $FF: synthetic field
         private final Semigroup$mcJ$sp $outer;
         private final long middle$5;

         public long combineN(final long a, final int n) {
            return Semigroup$mcJ$sp.super.combineN(a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Semigroup$mcJ$sp.super.combineN$mcJ$sp(a, n);
         }

         public long repeatedCombineN(final long a, final int n) {
            return Semigroup$mcJ$sp.super.repeatedCombineN(a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Semigroup$mcJ$sp.super.repeatedCombineN$mcJ$sp(a, n);
         }

         public Semigroup reverse() {
            return Semigroup$mcJ$sp.super.reverse();
         }

         public Semigroup reverse$mcJ$sp() {
            return Semigroup$mcJ$sp.super.reverse$mcJ$sp();
         }

         public Semigroup intercalate(final long middle) {
            return Semigroup$mcJ$sp.super.intercalate(middle);
         }

         public Semigroup intercalate$mcJ$sp(final long middle) {
            return Semigroup$mcJ$sp.super.intercalate$mcJ$sp(middle);
         }

         public double combine$mcD$sp(final double x, final double y) {
            return Semigroup.combine$mcD$sp$(this, x, y);
         }

         public float combine$mcF$sp(final float x, final float y) {
            return Semigroup.combine$mcF$sp$(this, x, y);
         }

         public int combine$mcI$sp(final int x, final int y) {
            return Semigroup.combine$mcI$sp$(this, x, y);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Semigroup.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Semigroup.combineN$mcF$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Semigroup.combineN$mcI$sp$(this, a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Semigroup.repeatedCombineN$mcD$sp$(this, a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Semigroup.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return Semigroup.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public Option combineAllOption(final IterableOnce as) {
            return Semigroup.combineAllOption$(this, as);
         }

         public Semigroup reverse$mcD$sp() {
            return Semigroup.reverse$mcD$sp$(this);
         }

         public Semigroup reverse$mcF$sp() {
            return Semigroup.reverse$mcF$sp$(this);
         }

         public Semigroup reverse$mcI$sp() {
            return Semigroup.reverse$mcI$sp$(this);
         }

         public Semigroup intercalate$mcD$sp(final double middle) {
            return Semigroup.intercalate$mcD$sp$(this, middle);
         }

         public Semigroup intercalate$mcF$sp(final float middle) {
            return Semigroup.intercalate$mcF$sp$(this, middle);
         }

         public Semigroup intercalate$mcI$sp(final int middle) {
            return Semigroup.intercalate$mcI$sp$(this, middle);
         }

         public long combine(final long a, final long b) {
            return this.combine$mcJ$sp(a, b);
         }

         public long combine$mcJ$sp(final long a, final long b) {
            return this.$outer.combine$mcJ$sp(a, this.$outer.combine$mcJ$sp(this.middle$5, b));
         }

         public {
            if (Semigroup$mcJ$sp.this == null) {
               throw null;
            } else {
               this.$outer = Semigroup$mcJ$sp.this;
               this.middle$5 = middle$5;
               Semigroup.$init$(this);
            }
         }
      };
   }

   private long loop$5(final long b, final int k, final long extra) {
      while(k != 1) {
         long x = (k & 1) == 1 ? this.combine$mcJ$sp(b, extra) : extra;
         long var10000 = this.combine$mcJ$sp(b, b);
         int var10001 = k >>> 1;
         extra = x;
         k = var10001;
         b = var10000;
      }

      return this.combine$mcJ$sp(b, extra);
   }
}
