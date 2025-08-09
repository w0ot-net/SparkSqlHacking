package algebra.ring;

import cats.kernel.CommutativeGroup;
import cats.kernel.CommutativeMonoid;
import cats.kernel.CommutativeSemigroup;
import cats.kernel.Eq;
import cats.kernel.Group;
import cats.kernel.Monoid;
import cats.kernel.Semigroup;
import cats.kernel.Group.mcJ.sp;
import scala.Option;
import scala.collection.IterableOnce;

public interface AdditiveCommutativeGroup$mcJ$sp extends AdditiveCommutativeGroup, AdditiveCommutativeMonoid$mcJ$sp, AdditiveGroup$mcJ$sp {
   // $FF: synthetic method
   static CommutativeGroup additive$(final AdditiveCommutativeGroup$mcJ$sp $this) {
      return $this.additive();
   }

   default CommutativeGroup additive() {
      return this.additive$mcJ$sp();
   }

   // $FF: synthetic method
   static CommutativeGroup additive$mcJ$sp$(final AdditiveCommutativeGroup$mcJ$sp $this) {
      return $this.additive$mcJ$sp();
   }

   default CommutativeGroup additive$mcJ$sp() {
      return new CommutativeGroup.mcJ.sp() {
         // $FF: synthetic field
         private final AdditiveCommutativeGroup$mcJ$sp $outer;

         public long combineN(final long a, final int n) {
            return sp.combineN$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return sp.combineN$mcJ$sp$(this, a, n);
         }

         public CommutativeMonoid reverse() {
            return cats.kernel.CommutativeMonoid.mcJ.sp.reverse$(this);
         }

         public CommutativeMonoid reverse$mcJ$sp() {
            return cats.kernel.CommutativeMonoid.mcJ.sp.reverse$mcJ$sp$(this);
         }

         public boolean isEmpty(final long a, final Eq ev) {
            return cats.kernel.Monoid.mcJ.sp.isEmpty$(this, a, ev);
         }

         public boolean isEmpty$mcJ$sp(final long a, final Eq ev) {
            return cats.kernel.Monoid.mcJ.sp.isEmpty$mcJ$sp$(this, a, ev);
         }

         public CommutativeSemigroup intercalate(final long middle) {
            return cats.kernel.CommutativeSemigroup.mcJ.sp.intercalate$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcJ$sp(final long middle) {
            return cats.kernel.CommutativeSemigroup.mcJ.sp.intercalate$mcJ$sp$(this, middle);
         }

         public long repeatedCombineN(final long a, final int n) {
            return cats.kernel.Semigroup.mcJ.sp.repeatedCombineN$(this, a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return cats.kernel.Semigroup.mcJ.sp.repeatedCombineN$mcJ$sp$(this, a, n);
         }

         public CommutativeMonoid reverse$mcD$sp() {
            return CommutativeMonoid.reverse$mcD$sp$(this);
         }

         public CommutativeMonoid reverse$mcF$sp() {
            return CommutativeMonoid.reverse$mcF$sp$(this);
         }

         public CommutativeMonoid reverse$mcI$sp() {
            return CommutativeMonoid.reverse$mcI$sp$(this);
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

         public double inverse$mcD$sp(final double a) {
            return Group.inverse$mcD$sp$(this, a);
         }

         public float inverse$mcF$sp(final float a) {
            return Group.inverse$mcF$sp$(this, a);
         }

         public int inverse$mcI$sp(final int a) {
            return Group.inverse$mcI$sp$(this, a);
         }

         public double remove$mcD$sp(final double a, final double b) {
            return Group.remove$mcD$sp$(this, a, b);
         }

         public float remove$mcF$sp(final float a, final float b) {
            return Group.remove$mcF$sp$(this, a, b);
         }

         public int remove$mcI$sp(final int a, final int b) {
            return Group.remove$mcI$sp$(this, a, b);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Group.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Group.combineN$mcF$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Group.combineN$mcI$sp$(this, a, n);
         }

         public double empty$mcD$sp() {
            return Monoid.empty$mcD$sp$(this);
         }

         public float empty$mcF$sp() {
            return Monoid.empty$mcF$sp$(this);
         }

         public int empty$mcI$sp() {
            return Monoid.empty$mcI$sp$(this);
         }

         public boolean isEmpty$mcD$sp(final double a, final Eq ev) {
            return Monoid.isEmpty$mcD$sp$(this, a, ev);
         }

         public boolean isEmpty$mcF$sp(final float a, final Eq ev) {
            return Monoid.isEmpty$mcF$sp$(this, a, ev);
         }

         public boolean isEmpty$mcI$sp(final int a, final Eq ev) {
            return Monoid.isEmpty$mcI$sp$(this, a, ev);
         }

         public double combineAll$mcD$sp(final IterableOnce as) {
            return Monoid.combineAll$mcD$sp$(this, as);
         }

         public float combineAll$mcF$sp(final IterableOnce as) {
            return Monoid.combineAll$mcF$sp$(this, as);
         }

         public int combineAll$mcI$sp(final IterableOnce as) {
            return Monoid.combineAll$mcI$sp$(this, as);
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

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Semigroup.repeatedCombineN$mcD$sp$(this, a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Semigroup.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return Semigroup.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public long empty() {
            return this.empty$mcJ$sp();
         }

         public long combine(final long x, final long y) {
            return this.combine$mcJ$sp(x, y);
         }

         public long remove(final long x, final long y) {
            return this.remove$mcJ$sp(x, y);
         }

         public long inverse(final long x) {
            return this.inverse$mcJ$sp(x);
         }

         public Option combineAllOption(final IterableOnce as) {
            return this.$outer.trySum(as);
         }

         public long combineAll(final IterableOnce as) {
            return this.combineAll$mcJ$sp(as);
         }

         public long empty$mcJ$sp() {
            return this.$outer.zero$mcJ$sp();
         }

         public long combine$mcJ$sp(final long x, final long y) {
            return this.$outer.plus$mcJ$sp(x, y);
         }

         public long remove$mcJ$sp(final long x, final long y) {
            return this.$outer.minus$mcJ$sp(x, y);
         }

         public long inverse$mcJ$sp(final long x) {
            return this.$outer.negate$mcJ$sp(x);
         }

         public long combineAll$mcJ$sp(final IterableOnce as) {
            return this.$outer.sum$mcJ$sp(as);
         }

         public {
            if (AdditiveCommutativeGroup$mcJ$sp.this == null) {
               throw null;
            } else {
               this.$outer = AdditiveCommutativeGroup$mcJ$sp.this;
               Semigroup.$init$(this);
               Monoid.$init$(this);
               Group.$init$(this);
               CommutativeSemigroup.$init$(this);
               CommutativeMonoid.$init$(this);
            }
         }
      };
   }
}
