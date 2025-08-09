package cats.kernel;

import scala.Option;
import scala.collection.IterableOnce;

public interface CommutativeSemigroup$mcD$sp extends CommutativeSemigroup, Semigroup$mcD$sp {
   default CommutativeSemigroup reverse() {
      return this.reverse$mcD$sp();
   }

   default CommutativeSemigroup reverse$mcD$sp() {
      return this;
   }

   default CommutativeSemigroup intercalate(final double middle) {
      return this.intercalate$mcD$sp(middle);
   }

   default CommutativeSemigroup intercalate$mcD$sp(final double middle) {
      return new CommutativeSemigroup$mcD$sp(middle) {
         // $FF: synthetic field
         private final CommutativeSemigroup$mcD$sp $outer;
         private final double middle$2;

         public CommutativeSemigroup reverse() {
            return CommutativeSemigroup$mcD$sp.super.reverse();
         }

         public CommutativeSemigroup reverse$mcD$sp() {
            return CommutativeSemigroup$mcD$sp.super.reverse$mcD$sp();
         }

         public CommutativeSemigroup intercalate(final double middle) {
            return CommutativeSemigroup$mcD$sp.super.intercalate(middle);
         }

         public CommutativeSemigroup intercalate$mcD$sp(final double middle) {
            return CommutativeSemigroup$mcD$sp.super.intercalate$mcD$sp(middle);
         }

         public double repeatedCombineN(final double a, final int n) {
            return Semigroup$mcD$sp.repeatedCombineN$(this, a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Semigroup$mcD$sp.repeatedCombineN$mcD$sp$(this, a, n);
         }

         public CommutativeSemigroup reverse$mcF$sp() {
            return CommutativeSemigroup.reverse$mcF$sp$(this);
         }

         public CommutativeSemigroup reverse$mcI$sp() {
            return CommutativeSemigroup.reverse$mcI$sp$(this);
         }

         public CommutativeSemigroup reverse$mcJ$sp() {
            return CommutativeSemigroup.reverse$mcJ$sp$(this);
         }

         public CommutativeSemigroup intercalate$mcF$sp(final float middle) {
            return CommutativeSemigroup.intercalate$mcF$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcI$sp(final int middle) {
            return CommutativeSemigroup.intercalate$mcI$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcJ$sp(final long middle) {
            return CommutativeSemigroup.intercalate$mcJ$sp$(this, middle);
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

         public double combine(final double a, final double b) {
            return this.combine$mcD$sp(a, b);
         }

         public double combineN(final double a, final int n) {
            return this.combineN$mcD$sp(a, n);
         }

         public double combine$mcD$sp(final double a, final double b) {
            return this.$outer.combine$mcD$sp(a, this.$outer.combine$mcD$sp(this.middle$2, b));
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return n <= 1 ? this.$outer.combineN$mcD$sp(a, n) : this.$outer.combine$mcD$sp(this.$outer.combineN$mcD$sp(a, n), this.$outer.combineN$mcD$sp(this.middle$2, n - 1));
         }

         public {
            if (CommutativeSemigroup$mcD$sp.this == null) {
               throw null;
            } else {
               this.$outer = CommutativeSemigroup$mcD$sp.this;
               this.middle$2 = middle$2;
               Semigroup.$init$(this);
               CommutativeSemigroup.$init$(this);
            }
         }
      };
   }
}
