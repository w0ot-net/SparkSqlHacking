package cats.kernel;

import scala.Option;
import scala.collection.IterableOnce;

public interface CommutativeSemigroup$mcF$sp extends CommutativeSemigroup, Semigroup$mcF$sp {
   default CommutativeSemigroup reverse() {
      return this.reverse$mcF$sp();
   }

   default CommutativeSemigroup reverse$mcF$sp() {
      return this;
   }

   default CommutativeSemigroup intercalate(final float middle) {
      return this.intercalate$mcF$sp(middle);
   }

   default CommutativeSemigroup intercalate$mcF$sp(final float middle) {
      return new CommutativeSemigroup$mcF$sp(middle) {
         // $FF: synthetic field
         private final CommutativeSemigroup$mcF$sp $outer;
         private final float middle$3;

         public CommutativeSemigroup reverse() {
            return CommutativeSemigroup$mcF$sp.super.reverse();
         }

         public CommutativeSemigroup reverse$mcF$sp() {
            return CommutativeSemigroup$mcF$sp.super.reverse$mcF$sp();
         }

         public CommutativeSemigroup intercalate(final float middle) {
            return CommutativeSemigroup$mcF$sp.super.intercalate(middle);
         }

         public CommutativeSemigroup intercalate$mcF$sp(final float middle) {
            return CommutativeSemigroup$mcF$sp.super.intercalate$mcF$sp(middle);
         }

         public float repeatedCombineN(final float a, final int n) {
            return Semigroup$mcF$sp.repeatedCombineN$(this, a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Semigroup$mcF$sp.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public CommutativeSemigroup reverse$mcD$sp() {
            return CommutativeSemigroup.reverse$mcD$sp$(this);
         }

         public CommutativeSemigroup reverse$mcI$sp() {
            return CommutativeSemigroup.reverse$mcI$sp$(this);
         }

         public CommutativeSemigroup reverse$mcJ$sp() {
            return CommutativeSemigroup.reverse$mcJ$sp$(this);
         }

         public CommutativeSemigroup intercalate$mcD$sp(final double middle) {
            return CommutativeSemigroup.intercalate$mcD$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcI$sp(final int middle) {
            return CommutativeSemigroup.intercalate$mcI$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcJ$sp(final long middle) {
            return CommutativeSemigroup.intercalate$mcJ$sp$(this, middle);
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

         public float combine(final float a, final float b) {
            return this.combine$mcF$sp(a, b);
         }

         public float combineN(final float a, final int n) {
            return this.combineN$mcF$sp(a, n);
         }

         public float combine$mcF$sp(final float a, final float b) {
            return this.$outer.combine$mcF$sp(a, this.$outer.combine$mcF$sp(this.middle$3, b));
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return n <= 1 ? this.$outer.combineN$mcF$sp(a, n) : this.$outer.combine$mcF$sp(this.$outer.combineN$mcF$sp(a, n), this.$outer.combineN$mcF$sp(this.middle$3, n - 1));
         }

         public {
            if (CommutativeSemigroup$mcF$sp.this == null) {
               throw null;
            } else {
               this.$outer = CommutativeSemigroup$mcF$sp.this;
               this.middle$3 = middle$3;
               Semigroup.$init$(this);
               CommutativeSemigroup.$init$(this);
            }
         }
      };
   }
}
