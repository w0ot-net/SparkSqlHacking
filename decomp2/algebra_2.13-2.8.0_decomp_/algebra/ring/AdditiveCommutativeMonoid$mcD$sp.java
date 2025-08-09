package algebra.ring;

import cats.kernel.CommutativeMonoid;
import cats.kernel.CommutativeSemigroup;
import cats.kernel.Eq;
import cats.kernel.Monoid;
import cats.kernel.Semigroup;
import cats.kernel.CommutativeMonoid.mcD.sp;
import scala.Option;
import scala.collection.IterableOnce;

public interface AdditiveCommutativeMonoid$mcD$sp extends AdditiveCommutativeMonoid, AdditiveCommutativeSemigroup$mcD$sp, AdditiveMonoid$mcD$sp {
   // $FF: synthetic method
   static CommutativeMonoid additive$(final AdditiveCommutativeMonoid$mcD$sp $this) {
      return $this.additive();
   }

   default CommutativeMonoid additive() {
      return this.additive$mcD$sp();
   }

   // $FF: synthetic method
   static CommutativeMonoid additive$mcD$sp$(final AdditiveCommutativeMonoid$mcD$sp $this) {
      return $this.additive$mcD$sp();
   }

   default CommutativeMonoid additive$mcD$sp() {
      return new CommutativeMonoid.mcD.sp() {
         // $FF: synthetic field
         private final AdditiveCommutativeMonoid$mcD$sp $outer;

         public CommutativeMonoid reverse() {
            return sp.reverse$(this);
         }

         public CommutativeMonoid reverse$mcD$sp() {
            return sp.reverse$mcD$sp$(this);
         }

         public boolean isEmpty(final double a, final Eq ev) {
            return cats.kernel.Monoid.mcD.sp.isEmpty$(this, a, ev);
         }

         public boolean isEmpty$mcD$sp(final double a, final Eq ev) {
            return cats.kernel.Monoid.mcD.sp.isEmpty$mcD$sp$(this, a, ev);
         }

         public double combineN(final double a, final int n) {
            return cats.kernel.Monoid.mcD.sp.combineN$(this, a, n);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return cats.kernel.Monoid.mcD.sp.combineN$mcD$sp$(this, a, n);
         }

         public CommutativeSemigroup intercalate(final double middle) {
            return cats.kernel.CommutativeSemigroup.mcD.sp.intercalate$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcD$sp(final double middle) {
            return cats.kernel.CommutativeSemigroup.mcD.sp.intercalate$mcD$sp$(this, middle);
         }

         public double repeatedCombineN(final double a, final int n) {
            return cats.kernel.Semigroup.mcD.sp.repeatedCombineN$(this, a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return cats.kernel.Semigroup.mcD.sp.repeatedCombineN$mcD$sp$(this, a, n);
         }

         public CommutativeMonoid reverse$mcF$sp() {
            return CommutativeMonoid.reverse$mcF$sp$(this);
         }

         public CommutativeMonoid reverse$mcI$sp() {
            return CommutativeMonoid.reverse$mcI$sp$(this);
         }

         public CommutativeMonoid reverse$mcJ$sp() {
            return CommutativeMonoid.reverse$mcJ$sp$(this);
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

         public float empty$mcF$sp() {
            return Monoid.empty$mcF$sp$(this);
         }

         public int empty$mcI$sp() {
            return Monoid.empty$mcI$sp$(this);
         }

         public long empty$mcJ$sp() {
            return Monoid.empty$mcJ$sp$(this);
         }

         public boolean isEmpty$mcF$sp(final float a, final Eq ev) {
            return Monoid.isEmpty$mcF$sp$(this, a, ev);
         }

         public boolean isEmpty$mcI$sp(final int a, final Eq ev) {
            return Monoid.isEmpty$mcI$sp$(this, a, ev);
         }

         public boolean isEmpty$mcJ$sp(final long a, final Eq ev) {
            return Monoid.isEmpty$mcJ$sp$(this, a, ev);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Monoid.combineN$mcF$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Monoid.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Monoid.combineN$mcJ$sp$(this, a, n);
         }

         public float combineAll$mcF$sp(final IterableOnce as) {
            return Monoid.combineAll$mcF$sp$(this, as);
         }

         public int combineAll$mcI$sp(final IterableOnce as) {
            return Monoid.combineAll$mcI$sp$(this, as);
         }

         public long combineAll$mcJ$sp(final IterableOnce as) {
            return Monoid.combineAll$mcJ$sp$(this, as);
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

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Semigroup.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return Semigroup.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Semigroup.repeatedCombineN$mcJ$sp$(this, a, n);
         }

         public double empty() {
            return this.empty$mcD$sp();
         }

         public double combine(final double x, final double y) {
            return this.combine$mcD$sp(x, y);
         }

         public Option combineAllOption(final IterableOnce as) {
            return this.$outer.trySum(as);
         }

         public double combineAll(final IterableOnce as) {
            return this.combineAll$mcD$sp(as);
         }

         public double empty$mcD$sp() {
            return this.$outer.zero$mcD$sp();
         }

         public double combine$mcD$sp(final double x, final double y) {
            return this.$outer.plus$mcD$sp(x, y);
         }

         public double combineAll$mcD$sp(final IterableOnce as) {
            return this.$outer.sum$mcD$sp(as);
         }

         public {
            if (AdditiveCommutativeMonoid$mcD$sp.this == null) {
               throw null;
            } else {
               this.$outer = AdditiveCommutativeMonoid$mcD$sp.this;
               Semigroup.$init$(this);
               Monoid.$init$(this);
               CommutativeSemigroup.$init$(this);
               CommutativeMonoid.$init$(this);
            }
         }
      };
   }
}
