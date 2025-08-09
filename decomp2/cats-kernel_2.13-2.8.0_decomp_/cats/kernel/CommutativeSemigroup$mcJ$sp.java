package cats.kernel;

import scala.Option;
import scala.collection.IterableOnce;

public interface CommutativeSemigroup$mcJ$sp extends CommutativeSemigroup, Semigroup$mcJ$sp {
   default CommutativeSemigroup reverse() {
      return this.reverse$mcJ$sp();
   }

   default CommutativeSemigroup reverse$mcJ$sp() {
      return this;
   }

   default CommutativeSemigroup intercalate(final long middle) {
      return this.intercalate$mcJ$sp(middle);
   }

   default CommutativeSemigroup intercalate$mcJ$sp(final long middle) {
      return new CommutativeSemigroup$mcJ$sp(middle) {
         // $FF: synthetic field
         private final CommutativeSemigroup$mcJ$sp $outer;
         private final long middle$5;

         public CommutativeSemigroup reverse() {
            return CommutativeSemigroup$mcJ$sp.super.reverse();
         }

         public CommutativeSemigroup reverse$mcJ$sp() {
            return CommutativeSemigroup$mcJ$sp.super.reverse$mcJ$sp();
         }

         public CommutativeSemigroup intercalate(final long middle) {
            return CommutativeSemigroup$mcJ$sp.super.intercalate(middle);
         }

         public CommutativeSemigroup intercalate$mcJ$sp(final long middle) {
            return CommutativeSemigroup$mcJ$sp.super.intercalate$mcJ$sp(middle);
         }

         public long repeatedCombineN(final long a, final int n) {
            return Semigroup$mcJ$sp.repeatedCombineN$(this, a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Semigroup$mcJ$sp.repeatedCombineN$mcJ$sp$(this, a, n);
         }

         public CommutativeSemigroup reverse$mcD$sp() {
            return CommutativeSemigroup.reverse$mcD$sp$(this);
         }

         public CommutativeSemigroup reverse$mcF$sp() {
            return CommutativeSemigroup.reverse$mcF$sp$(this);
         }

         public CommutativeSemigroup reverse$mcI$sp() {
            return CommutativeSemigroup.reverse$mcI$sp$(this);
         }

         public CommutativeSemigroup intercalate$mcD$sp(final double middle) {
            return CommutativeSemigroup.intercalate$mcD$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcF$sp(final float middle) {
            return CommutativeSemigroup.intercalate$mcF$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcI$sp(final int middle) {
            return CommutativeSemigroup.intercalate$mcI$sp$(this, middle);
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

         public long combine(final long a, final long b) {
            return this.combine$mcJ$sp(a, b);
         }

         public long combineN(final long a, final int n) {
            return this.combineN$mcJ$sp(a, n);
         }

         public long combine$mcJ$sp(final long a, final long b) {
            return this.$outer.combine$mcJ$sp(a, this.$outer.combine$mcJ$sp(this.middle$5, b));
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return n <= 1 ? this.$outer.combineN$mcJ$sp(a, n) : this.$outer.combine$mcJ$sp(this.$outer.combineN$mcJ$sp(a, n), this.$outer.combineN$mcJ$sp(this.middle$5, n - 1));
         }

         public {
            if (CommutativeSemigroup$mcJ$sp.this == null) {
               throw null;
            } else {
               this.$outer = CommutativeSemigroup$mcJ$sp.this;
               this.middle$5 = middle$5;
               Semigroup.$init$(this);
               CommutativeSemigroup.$init$(this);
            }
         }
      };
   }
}
