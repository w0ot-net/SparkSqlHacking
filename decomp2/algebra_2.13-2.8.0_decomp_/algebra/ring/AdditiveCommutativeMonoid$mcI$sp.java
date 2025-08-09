package algebra.ring;

import cats.kernel.CommutativeMonoid;
import cats.kernel.CommutativeSemigroup;
import cats.kernel.Eq;
import cats.kernel.Monoid;
import cats.kernel.Semigroup;
import cats.kernel.CommutativeMonoid.mcI.sp;
import scala.Option;
import scala.collection.IterableOnce;

public interface AdditiveCommutativeMonoid$mcI$sp extends AdditiveCommutativeMonoid, AdditiveCommutativeSemigroup$mcI$sp, AdditiveMonoid$mcI$sp {
   // $FF: synthetic method
   static CommutativeMonoid additive$(final AdditiveCommutativeMonoid$mcI$sp $this) {
      return $this.additive();
   }

   default CommutativeMonoid additive() {
      return this.additive$mcI$sp();
   }

   // $FF: synthetic method
   static CommutativeMonoid additive$mcI$sp$(final AdditiveCommutativeMonoid$mcI$sp $this) {
      return $this.additive$mcI$sp();
   }

   default CommutativeMonoid additive$mcI$sp() {
      return new CommutativeMonoid.mcI.sp() {
         // $FF: synthetic field
         private final AdditiveCommutativeMonoid$mcI$sp $outer;

         public CommutativeMonoid reverse() {
            return sp.reverse$(this);
         }

         public CommutativeMonoid reverse$mcI$sp() {
            return sp.reverse$mcI$sp$(this);
         }

         public boolean isEmpty(final int a, final Eq ev) {
            return cats.kernel.Monoid.mcI.sp.isEmpty$(this, a, ev);
         }

         public boolean isEmpty$mcI$sp(final int a, final Eq ev) {
            return cats.kernel.Monoid.mcI.sp.isEmpty$mcI$sp$(this, a, ev);
         }

         public int combineN(final int a, final int n) {
            return cats.kernel.Monoid.mcI.sp.combineN$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return cats.kernel.Monoid.mcI.sp.combineN$mcI$sp$(this, a, n);
         }

         public CommutativeSemigroup intercalate(final int middle) {
            return cats.kernel.CommutativeSemigroup.mcI.sp.intercalate$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcI$sp(final int middle) {
            return cats.kernel.CommutativeSemigroup.mcI.sp.intercalate$mcI$sp$(this, middle);
         }

         public int repeatedCombineN(final int a, final int n) {
            return cats.kernel.Semigroup.mcI.sp.repeatedCombineN$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return cats.kernel.Semigroup.mcI.sp.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public CommutativeMonoid reverse$mcD$sp() {
            return CommutativeMonoid.reverse$mcD$sp$(this);
         }

         public CommutativeMonoid reverse$mcF$sp() {
            return CommutativeMonoid.reverse$mcF$sp$(this);
         }

         public CommutativeMonoid reverse$mcJ$sp() {
            return CommutativeMonoid.reverse$mcJ$sp$(this);
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

         public double empty$mcD$sp() {
            return Monoid.empty$mcD$sp$(this);
         }

         public float empty$mcF$sp() {
            return Monoid.empty$mcF$sp$(this);
         }

         public long empty$mcJ$sp() {
            return Monoid.empty$mcJ$sp$(this);
         }

         public boolean isEmpty$mcD$sp(final double a, final Eq ev) {
            return Monoid.isEmpty$mcD$sp$(this, a, ev);
         }

         public boolean isEmpty$mcF$sp(final float a, final Eq ev) {
            return Monoid.isEmpty$mcF$sp$(this, a, ev);
         }

         public boolean isEmpty$mcJ$sp(final long a, final Eq ev) {
            return Monoid.isEmpty$mcJ$sp$(this, a, ev);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Monoid.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Monoid.combineN$mcF$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Monoid.combineN$mcJ$sp$(this, a, n);
         }

         public double combineAll$mcD$sp(final IterableOnce as) {
            return Monoid.combineAll$mcD$sp$(this, as);
         }

         public float combineAll$mcF$sp(final IterableOnce as) {
            return Monoid.combineAll$mcF$sp$(this, as);
         }

         public long combineAll$mcJ$sp(final IterableOnce as) {
            return Monoid.combineAll$mcJ$sp$(this, as);
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

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Semigroup.repeatedCombineN$mcD$sp$(this, a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Semigroup.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Semigroup.repeatedCombineN$mcJ$sp$(this, a, n);
         }

         public int empty() {
            return this.empty$mcI$sp();
         }

         public int combine(final int x, final int y) {
            return this.combine$mcI$sp(x, y);
         }

         public Option combineAllOption(final IterableOnce as) {
            return this.$outer.trySum(as);
         }

         public int combineAll(final IterableOnce as) {
            return this.combineAll$mcI$sp(as);
         }

         public int empty$mcI$sp() {
            return this.$outer.zero$mcI$sp();
         }

         public int combine$mcI$sp(final int x, final int y) {
            return this.$outer.plus$mcI$sp(x, y);
         }

         public int combineAll$mcI$sp(final IterableOnce as) {
            return this.$outer.sum$mcI$sp(as);
         }

         public {
            if (AdditiveCommutativeMonoid$mcI$sp.this == null) {
               throw null;
            } else {
               this.$outer = AdditiveCommutativeMonoid$mcI$sp.this;
               Semigroup.$init$(this);
               Monoid.$init$(this);
               CommutativeSemigroup.$init$(this);
               CommutativeMonoid.$init$(this);
            }
         }
      };
   }
}
