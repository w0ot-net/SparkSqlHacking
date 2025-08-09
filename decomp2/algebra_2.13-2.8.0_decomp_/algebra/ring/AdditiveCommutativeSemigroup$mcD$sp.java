package algebra.ring;

import cats.kernel.CommutativeSemigroup;
import cats.kernel.Semigroup;
import cats.kernel.CommutativeSemigroup.mcD.sp;
import scala.Option;
import scala.collection.IterableOnce;

public interface AdditiveCommutativeSemigroup$mcD$sp extends AdditiveCommutativeSemigroup, AdditiveSemigroup$mcD$sp {
   // $FF: synthetic method
   static CommutativeSemigroup additive$(final AdditiveCommutativeSemigroup$mcD$sp $this) {
      return $this.additive();
   }

   default CommutativeSemigroup additive() {
      return this.additive$mcD$sp();
   }

   // $FF: synthetic method
   static CommutativeSemigroup additive$mcD$sp$(final AdditiveCommutativeSemigroup$mcD$sp $this) {
      return $this.additive$mcD$sp();
   }

   default CommutativeSemigroup additive$mcD$sp() {
      return new CommutativeSemigroup.mcD.sp() {
         // $FF: synthetic field
         private final AdditiveCommutativeSemigroup$mcD$sp $outer;

         public CommutativeSemigroup reverse() {
            return sp.reverse$(this);
         }

         public CommutativeSemigroup reverse$mcD$sp() {
            return sp.reverse$mcD$sp$(this);
         }

         public CommutativeSemigroup intercalate(final double middle) {
            return sp.intercalate$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcD$sp(final double middle) {
            return sp.intercalate$mcD$sp$(this, middle);
         }

         public double combineN(final double a, final int n) {
            return cats.kernel.Semigroup.mcD.sp.combineN$(this, a, n);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return cats.kernel.Semigroup.mcD.sp.combineN$mcD$sp$(this, a, n);
         }

         public double repeatedCombineN(final double a, final int n) {
            return cats.kernel.Semigroup.mcD.sp.repeatedCombineN$(this, a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return cats.kernel.Semigroup.mcD.sp.repeatedCombineN$mcD$sp$(this, a, n);
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

         public double combine(final double x, final double y) {
            return this.combine$mcD$sp(x, y);
         }

         public Option combineAllOption(final IterableOnce as) {
            return this.$outer.trySum(as);
         }

         public double combine$mcD$sp(final double x, final double y) {
            return this.$outer.plus$mcD$sp(x, y);
         }

         public {
            if (AdditiveCommutativeSemigroup$mcD$sp.this == null) {
               throw null;
            } else {
               this.$outer = AdditiveCommutativeSemigroup$mcD$sp.this;
               Semigroup.$init$(this);
               CommutativeSemigroup.$init$(this);
            }
         }
      };
   }
}
