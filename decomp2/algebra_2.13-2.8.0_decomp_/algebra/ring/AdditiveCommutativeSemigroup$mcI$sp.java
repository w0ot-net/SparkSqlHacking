package algebra.ring;

import cats.kernel.CommutativeSemigroup;
import cats.kernel.Semigroup;
import cats.kernel.CommutativeSemigroup.mcI.sp;
import scala.Option;
import scala.collection.IterableOnce;

public interface AdditiveCommutativeSemigroup$mcI$sp extends AdditiveCommutativeSemigroup, AdditiveSemigroup$mcI$sp {
   // $FF: synthetic method
   static CommutativeSemigroup additive$(final AdditiveCommutativeSemigroup$mcI$sp $this) {
      return $this.additive();
   }

   default CommutativeSemigroup additive() {
      return this.additive$mcI$sp();
   }

   // $FF: synthetic method
   static CommutativeSemigroup additive$mcI$sp$(final AdditiveCommutativeSemigroup$mcI$sp $this) {
      return $this.additive$mcI$sp();
   }

   default CommutativeSemigroup additive$mcI$sp() {
      return new CommutativeSemigroup.mcI.sp() {
         // $FF: synthetic field
         private final AdditiveCommutativeSemigroup$mcI$sp $outer;

         public CommutativeSemigroup reverse() {
            return sp.reverse$(this);
         }

         public CommutativeSemigroup reverse$mcI$sp() {
            return sp.reverse$mcI$sp$(this);
         }

         public CommutativeSemigroup intercalate(final int middle) {
            return sp.intercalate$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcI$sp(final int middle) {
            return sp.intercalate$mcI$sp$(this, middle);
         }

         public int combineN(final int a, final int n) {
            return cats.kernel.Semigroup.mcI.sp.combineN$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return cats.kernel.Semigroup.mcI.sp.combineN$mcI$sp$(this, a, n);
         }

         public int repeatedCombineN(final int a, final int n) {
            return cats.kernel.Semigroup.mcI.sp.repeatedCombineN$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return cats.kernel.Semigroup.mcI.sp.repeatedCombineN$mcI$sp$(this, a, n);
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

         public int combine(final int x, final int y) {
            return this.combine$mcI$sp(x, y);
         }

         public Option combineAllOption(final IterableOnce as) {
            return this.$outer.trySum(as);
         }

         public int combine$mcI$sp(final int x, final int y) {
            return this.$outer.plus$mcI$sp(x, y);
         }

         public {
            if (AdditiveCommutativeSemigroup$mcI$sp.this == null) {
               throw null;
            } else {
               this.$outer = AdditiveCommutativeSemigroup$mcI$sp.this;
               Semigroup.$init$(this);
               CommutativeSemigroup.$init$(this);
            }
         }
      };
   }
}
