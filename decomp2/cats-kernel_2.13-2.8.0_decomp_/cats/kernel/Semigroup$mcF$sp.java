package cats.kernel;

import scala.Option;
import scala.collection.IterableOnce;

public interface Semigroup$mcF$sp extends Semigroup {
   default float combineN(final float a, final int n) {
      return this.combineN$mcF$sp(a, n);
   }

   default float combineN$mcF$sp(final float a, final int n) {
      if (n <= 0) {
         throw new IllegalArgumentException("Repeated combining for semigroups must have n > 0");
      } else {
         return this.repeatedCombineN$mcF$sp(a, n);
      }
   }

   default float repeatedCombineN(final float a, final int n) {
      return this.repeatedCombineN$mcF$sp(a, n);
   }

   default float repeatedCombineN$mcF$sp(final float a, final int n) {
      return n == 1 ? a : this.loop$3(a, n - 1, a);
   }

   default Semigroup reverse() {
      return this.reverse$mcF$sp();
   }

   default Semigroup reverse$mcF$sp() {
      return new Semigroup$mcF$sp() {
         // $FF: synthetic field
         private final Semigroup$mcF$sp $outer;

         public float repeatedCombineN(final float a, final int n) {
            return Semigroup$mcF$sp.super.repeatedCombineN(a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Semigroup$mcF$sp.super.repeatedCombineN$mcF$sp(a, n);
         }

         public Semigroup intercalate(final float middle) {
            return Semigroup$mcF$sp.super.intercalate(middle);
         }

         public Semigroup intercalate$mcF$sp(final float middle) {
            return Semigroup$mcF$sp.super.intercalate$mcF$sp(middle);
         }

         public double combine$mcD$sp(final double x, final double y) {
            return Semigroup.combine$mcD$sp$(this, x, y);
         }

         public int combine$mcI$sp(final int x, final int y) {
            return Semigroup.combine$mcI$sp$(this, x, y);
         }

         public long combine$mcJ$sp(final long x, final long y) {
            return Semigroup.combine$mcJ$sp$(this, x, y);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Semigroup.combineN$mcD$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Semigroup.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Semigroup.combineN$mcJ$sp$(this, a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Semigroup.repeatedCombineN$mcD$sp$(this, a, n);
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

         public Semigroup reverse$mcD$sp() {
            return Semigroup.reverse$mcD$sp$(this);
         }

         public Semigroup reverse$mcI$sp() {
            return Semigroup.reverse$mcI$sp$(this);
         }

         public Semigroup reverse$mcJ$sp() {
            return Semigroup.reverse$mcJ$sp$(this);
         }

         public Semigroup intercalate$mcD$sp(final double middle) {
            return Semigroup.intercalate$mcD$sp$(this, middle);
         }

         public Semigroup intercalate$mcI$sp(final int middle) {
            return Semigroup.intercalate$mcI$sp$(this, middle);
         }

         public Semigroup intercalate$mcJ$sp(final long middle) {
            return Semigroup.intercalate$mcJ$sp$(this, middle);
         }

         public float combine(final float a, final float b) {
            return this.combine$mcF$sp(a, b);
         }

         public float combineN(final float a, final int n) {
            return this.combineN$mcF$sp(a, n);
         }

         public Semigroup reverse() {
            return this.reverse$mcF$sp();
         }

         public float combine$mcF$sp(final float a, final float b) {
            return this.$outer.combine$mcF$sp(b, a);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return this.$outer.combineN$mcF$sp(a, n);
         }

         public Semigroup reverse$mcF$sp() {
            return this.$outer;
         }

         public {
            if (Semigroup$mcF$sp.this == null) {
               throw null;
            } else {
               this.$outer = Semigroup$mcF$sp.this;
               Semigroup.$init$(this);
            }
         }
      };
   }

   default Semigroup intercalate(final float middle) {
      return this.intercalate$mcF$sp(middle);
   }

   default Semigroup intercalate$mcF$sp(final float middle) {
      return new Semigroup$mcF$sp(middle) {
         // $FF: synthetic field
         private final Semigroup$mcF$sp $outer;
         private final float middle$3;

         public float combineN(final float a, final int n) {
            return Semigroup$mcF$sp.super.combineN(a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Semigroup$mcF$sp.super.combineN$mcF$sp(a, n);
         }

         public float repeatedCombineN(final float a, final int n) {
            return Semigroup$mcF$sp.super.repeatedCombineN(a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Semigroup$mcF$sp.super.repeatedCombineN$mcF$sp(a, n);
         }

         public Semigroup reverse() {
            return Semigroup$mcF$sp.super.reverse();
         }

         public Semigroup reverse$mcF$sp() {
            return Semigroup$mcF$sp.super.reverse$mcF$sp();
         }

         public Semigroup intercalate(final float middle) {
            return Semigroup$mcF$sp.super.intercalate(middle);
         }

         public Semigroup intercalate$mcF$sp(final float middle) {
            return Semigroup$mcF$sp.super.intercalate$mcF$sp(middle);
         }

         public double combine$mcD$sp(final double x, final double y) {
            return Semigroup.combine$mcD$sp$(this, x, y);
         }

         public int combine$mcI$sp(final int x, final int y) {
            return Semigroup.combine$mcI$sp$(this, x, y);
         }

         public long combine$mcJ$sp(final long x, final long y) {
            return Semigroup.combine$mcJ$sp$(this, x, y);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Semigroup.combineN$mcD$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Semigroup.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Semigroup.combineN$mcJ$sp$(this, a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Semigroup.repeatedCombineN$mcD$sp$(this, a, n);
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

         public Semigroup reverse$mcD$sp() {
            return Semigroup.reverse$mcD$sp$(this);
         }

         public Semigroup reverse$mcI$sp() {
            return Semigroup.reverse$mcI$sp$(this);
         }

         public Semigroup reverse$mcJ$sp() {
            return Semigroup.reverse$mcJ$sp$(this);
         }

         public Semigroup intercalate$mcD$sp(final double middle) {
            return Semigroup.intercalate$mcD$sp$(this, middle);
         }

         public Semigroup intercalate$mcI$sp(final int middle) {
            return Semigroup.intercalate$mcI$sp$(this, middle);
         }

         public Semigroup intercalate$mcJ$sp(final long middle) {
            return Semigroup.intercalate$mcJ$sp$(this, middle);
         }

         public float combine(final float a, final float b) {
            return this.combine$mcF$sp(a, b);
         }

         public float combine$mcF$sp(final float a, final float b) {
            return this.$outer.combine$mcF$sp(a, this.$outer.combine$mcF$sp(this.middle$3, b));
         }

         public {
            if (Semigroup$mcF$sp.this == null) {
               throw null;
            } else {
               this.$outer = Semigroup$mcF$sp.this;
               this.middle$3 = middle$3;
               Semigroup.$init$(this);
            }
         }
      };
   }

   private float loop$3(final float b, final int k, final float extra) {
      while(k != 1) {
         float x = (k & 1) == 1 ? this.combine$mcF$sp(b, extra) : extra;
         float var10000 = this.combine$mcF$sp(b, b);
         int var10001 = k >>> 1;
         extra = x;
         k = var10001;
         b = var10000;
      }

      return this.combine$mcF$sp(b, extra);
   }
}
