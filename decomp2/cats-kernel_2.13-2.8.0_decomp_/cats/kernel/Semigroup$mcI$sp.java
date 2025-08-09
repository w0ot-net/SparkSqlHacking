package cats.kernel;

import scala.Option;
import scala.collection.IterableOnce;

public interface Semigroup$mcI$sp extends Semigroup {
   default int combineN(final int a, final int n) {
      return this.combineN$mcI$sp(a, n);
   }

   default int combineN$mcI$sp(final int a, final int n) {
      if (n <= 0) {
         throw new IllegalArgumentException("Repeated combining for semigroups must have n > 0");
      } else {
         return this.repeatedCombineN$mcI$sp(a, n);
      }
   }

   default int repeatedCombineN(final int a, final int n) {
      return this.repeatedCombineN$mcI$sp(a, n);
   }

   default int repeatedCombineN$mcI$sp(final int a, final int n) {
      return n == 1 ? a : this.loop$4(a, n - 1, a);
   }

   default Semigroup reverse() {
      return this.reverse$mcI$sp();
   }

   default Semigroup reverse$mcI$sp() {
      return new Semigroup$mcI$sp() {
         // $FF: synthetic field
         private final Semigroup$mcI$sp $outer;

         public int repeatedCombineN(final int a, final int n) {
            return Semigroup$mcI$sp.super.repeatedCombineN(a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return Semigroup$mcI$sp.super.repeatedCombineN$mcI$sp(a, n);
         }

         public Semigroup intercalate(final int middle) {
            return Semigroup$mcI$sp.super.intercalate(middle);
         }

         public Semigroup intercalate$mcI$sp(final int middle) {
            return Semigroup$mcI$sp.super.intercalate$mcI$sp(middle);
         }

         public double combine$mcD$sp(final double x, final double y) {
            return Semigroup.combine$mcD$sp$(this, x, y);
         }

         public float combine$mcF$sp(final float x, final float y) {
            return Semigroup.combine$mcF$sp$(this, x, y);
         }

         public long combine$mcJ$sp(final long x, final long y) {
            return Semigroup.combine$mcJ$sp$(this, x, y);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Semigroup.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Semigroup.combineN$mcF$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Semigroup.combineN$mcJ$sp$(this, a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Semigroup.repeatedCombineN$mcD$sp$(this, a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Semigroup.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Semigroup.repeatedCombineN$mcJ$sp$(this, a, n);
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

         public Semigroup reverse$mcJ$sp() {
            return Semigroup.reverse$mcJ$sp$(this);
         }

         public Semigroup intercalate$mcD$sp(final double middle) {
            return Semigroup.intercalate$mcD$sp$(this, middle);
         }

         public Semigroup intercalate$mcF$sp(final float middle) {
            return Semigroup.intercalate$mcF$sp$(this, middle);
         }

         public Semigroup intercalate$mcJ$sp(final long middle) {
            return Semigroup.intercalate$mcJ$sp$(this, middle);
         }

         public int combine(final int a, final int b) {
            return this.combine$mcI$sp(a, b);
         }

         public int combineN(final int a, final int n) {
            return this.combineN$mcI$sp(a, n);
         }

         public Semigroup reverse() {
            return this.reverse$mcI$sp();
         }

         public int combine$mcI$sp(final int a, final int b) {
            return this.$outer.combine$mcI$sp(b, a);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return this.$outer.combineN$mcI$sp(a, n);
         }

         public Semigroup reverse$mcI$sp() {
            return this.$outer;
         }

         public {
            if (Semigroup$mcI$sp.this == null) {
               throw null;
            } else {
               this.$outer = Semigroup$mcI$sp.this;
               Semigroup.$init$(this);
            }
         }
      };
   }

   default Semigroup intercalate(final int middle) {
      return this.intercalate$mcI$sp(middle);
   }

   default Semigroup intercalate$mcI$sp(final int middle) {
      return new Semigroup$mcI$sp(middle) {
         // $FF: synthetic field
         private final Semigroup$mcI$sp $outer;
         private final int middle$4;

         public int combineN(final int a, final int n) {
            return Semigroup$mcI$sp.super.combineN(a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Semigroup$mcI$sp.super.combineN$mcI$sp(a, n);
         }

         public int repeatedCombineN(final int a, final int n) {
            return Semigroup$mcI$sp.super.repeatedCombineN(a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return Semigroup$mcI$sp.super.repeatedCombineN$mcI$sp(a, n);
         }

         public Semigroup reverse() {
            return Semigroup$mcI$sp.super.reverse();
         }

         public Semigroup reverse$mcI$sp() {
            return Semigroup$mcI$sp.super.reverse$mcI$sp();
         }

         public Semigroup intercalate(final int middle) {
            return Semigroup$mcI$sp.super.intercalate(middle);
         }

         public Semigroup intercalate$mcI$sp(final int middle) {
            return Semigroup$mcI$sp.super.intercalate$mcI$sp(middle);
         }

         public double combine$mcD$sp(final double x, final double y) {
            return Semigroup.combine$mcD$sp$(this, x, y);
         }

         public float combine$mcF$sp(final float x, final float y) {
            return Semigroup.combine$mcF$sp$(this, x, y);
         }

         public long combine$mcJ$sp(final long x, final long y) {
            return Semigroup.combine$mcJ$sp$(this, x, y);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Semigroup.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Semigroup.combineN$mcF$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Semigroup.combineN$mcJ$sp$(this, a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Semigroup.repeatedCombineN$mcD$sp$(this, a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Semigroup.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Semigroup.repeatedCombineN$mcJ$sp$(this, a, n);
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

         public Semigroup reverse$mcJ$sp() {
            return Semigroup.reverse$mcJ$sp$(this);
         }

         public Semigroup intercalate$mcD$sp(final double middle) {
            return Semigroup.intercalate$mcD$sp$(this, middle);
         }

         public Semigroup intercalate$mcF$sp(final float middle) {
            return Semigroup.intercalate$mcF$sp$(this, middle);
         }

         public Semigroup intercalate$mcJ$sp(final long middle) {
            return Semigroup.intercalate$mcJ$sp$(this, middle);
         }

         public int combine(final int a, final int b) {
            return this.combine$mcI$sp(a, b);
         }

         public int combine$mcI$sp(final int a, final int b) {
            return this.$outer.combine$mcI$sp(a, this.$outer.combine$mcI$sp(this.middle$4, b));
         }

         public {
            if (Semigroup$mcI$sp.this == null) {
               throw null;
            } else {
               this.$outer = Semigroup$mcI$sp.this;
               this.middle$4 = middle$4;
               Semigroup.$init$(this);
            }
         }
      };
   }

   private int loop$4(final int b, final int k, final int extra) {
      while(k != 1) {
         int x = (k & 1) == 1 ? this.combine$mcI$sp(b, extra) : extra;
         int var10000 = this.combine$mcI$sp(b, b);
         int var10001 = k >>> 1;
         extra = x;
         k = var10001;
         b = var10000;
      }

      return this.combine$mcI$sp(b, extra);
   }
}
