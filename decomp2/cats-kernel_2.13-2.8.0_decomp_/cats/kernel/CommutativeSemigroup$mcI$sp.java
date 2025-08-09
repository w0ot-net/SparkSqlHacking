package cats.kernel;

import scala.Option;
import scala.collection.IterableOnce;

public interface CommutativeSemigroup$mcI$sp extends CommutativeSemigroup, Semigroup$mcI$sp {
   default CommutativeSemigroup reverse() {
      return this.reverse$mcI$sp();
   }

   default CommutativeSemigroup reverse$mcI$sp() {
      return this;
   }

   default CommutativeSemigroup intercalate(final int middle) {
      return this.intercalate$mcI$sp(middle);
   }

   default CommutativeSemigroup intercalate$mcI$sp(final int middle) {
      return new CommutativeSemigroup$mcI$sp(middle) {
         // $FF: synthetic field
         private final CommutativeSemigroup$mcI$sp $outer;
         private final int middle$4;

         public CommutativeSemigroup reverse() {
            return CommutativeSemigroup$mcI$sp.super.reverse();
         }

         public CommutativeSemigroup reverse$mcI$sp() {
            return CommutativeSemigroup$mcI$sp.super.reverse$mcI$sp();
         }

         public CommutativeSemigroup intercalate(final int middle) {
            return CommutativeSemigroup$mcI$sp.super.intercalate(middle);
         }

         public CommutativeSemigroup intercalate$mcI$sp(final int middle) {
            return CommutativeSemigroup$mcI$sp.super.intercalate$mcI$sp(middle);
         }

         public int repeatedCombineN(final int a, final int n) {
            return Semigroup$mcI$sp.repeatedCombineN$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return Semigroup$mcI$sp.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public CommutativeSemigroup reverse$mcD$sp() {
            return CommutativeSemigroup.reverse$mcD$sp$(this);
         }

         public CommutativeSemigroup reverse$mcF$sp() {
            return CommutativeSemigroup.reverse$mcF$sp$(this);
         }

         public CommutativeSemigroup reverse$mcJ$sp() {
            return CommutativeSemigroup.reverse$mcJ$sp$(this);
         }

         public CommutativeSemigroup intercalate$mcD$sp(final double middle) {
            return CommutativeSemigroup.intercalate$mcD$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcF$sp(final float middle) {
            return CommutativeSemigroup.intercalate$mcF$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcJ$sp(final long middle) {
            return CommutativeSemigroup.intercalate$mcJ$sp$(this, middle);
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

         public int combine(final int a, final int b) {
            return this.combine$mcI$sp(a, b);
         }

         public int combineN(final int a, final int n) {
            return this.combineN$mcI$sp(a, n);
         }

         public int combine$mcI$sp(final int a, final int b) {
            return this.$outer.combine$mcI$sp(a, this.$outer.combine$mcI$sp(this.middle$4, b));
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return n <= 1 ? this.$outer.combineN$mcI$sp(a, n) : this.$outer.combine$mcI$sp(this.$outer.combineN$mcI$sp(a, n), this.$outer.combineN$mcI$sp(this.middle$4, n - 1));
         }

         public {
            if (CommutativeSemigroup$mcI$sp.this == null) {
               throw null;
            } else {
               this.$outer = CommutativeSemigroup$mcI$sp.this;
               this.middle$4 = middle$4;
               Semigroup.$init$(this);
               CommutativeSemigroup.$init$(this);
            }
         }
      };
   }
}
