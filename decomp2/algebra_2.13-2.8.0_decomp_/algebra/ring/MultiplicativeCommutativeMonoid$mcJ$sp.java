package algebra.ring;

import cats.kernel.CommutativeMonoid;
import cats.kernel.CommutativeSemigroup;
import cats.kernel.Eq;
import cats.kernel.Monoid;
import cats.kernel.Semigroup;
import cats.kernel.CommutativeMonoid.mcJ.sp;
import scala.Option;
import scala.collection.IterableOnce;

public interface MultiplicativeCommutativeMonoid$mcJ$sp extends MultiplicativeCommutativeMonoid, MultiplicativeCommutativeSemigroup$mcJ$sp, MultiplicativeMonoid$mcJ$sp {
   // $FF: synthetic method
   static CommutativeMonoid multiplicative$(final MultiplicativeCommutativeMonoid$mcJ$sp $this) {
      return $this.multiplicative();
   }

   default CommutativeMonoid multiplicative() {
      return this.multiplicative$mcJ$sp();
   }

   // $FF: synthetic method
   static CommutativeMonoid multiplicative$mcJ$sp$(final MultiplicativeCommutativeMonoid$mcJ$sp $this) {
      return $this.multiplicative$mcJ$sp();
   }

   default CommutativeMonoid multiplicative$mcJ$sp() {
      return new CommutativeMonoid.mcJ.sp() {
         // $FF: synthetic field
         private final MultiplicativeCommutativeMonoid$mcJ$sp $outer;

         public CommutativeMonoid reverse() {
            return sp.reverse$(this);
         }

         public CommutativeMonoid reverse$mcJ$sp() {
            return sp.reverse$mcJ$sp$(this);
         }

         public boolean isEmpty(final long a, final Eq ev) {
            return cats.kernel.Monoid.mcJ.sp.isEmpty$(this, a, ev);
         }

         public boolean isEmpty$mcJ$sp(final long a, final Eq ev) {
            return cats.kernel.Monoid.mcJ.sp.isEmpty$mcJ$sp$(this, a, ev);
         }

         public long combineN(final long a, final int n) {
            return cats.kernel.Monoid.mcJ.sp.combineN$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return cats.kernel.Monoid.mcJ.sp.combineN$mcJ$sp$(this, a, n);
         }

         public long combineAll(final IterableOnce as) {
            return cats.kernel.Monoid.mcJ.sp.combineAll$(this, as);
         }

         public long combineAll$mcJ$sp(final IterableOnce as) {
            return cats.kernel.Monoid.mcJ.sp.combineAll$mcJ$sp$(this, as);
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

         public double combineN$mcD$sp(final double a, final int n) {
            return Monoid.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Monoid.combineN$mcF$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Monoid.combineN$mcI$sp$(this, a, n);
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

         public Option combineAllOption(final IterableOnce as) {
            return Monoid.combineAllOption$(this, as);
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

         public long empty$mcJ$sp() {
            return this.$outer.one$mcJ$sp();
         }

         public long combine$mcJ$sp(final long x, final long y) {
            return this.$outer.times$mcJ$sp(x, y);
         }

         public {
            if (MultiplicativeCommutativeMonoid$mcJ$sp.this == null) {
               throw null;
            } else {
               this.$outer = MultiplicativeCommutativeMonoid$mcJ$sp.this;
               Semigroup.$init$(this);
               Monoid.$init$(this);
               CommutativeSemigroup.$init$(this);
               CommutativeMonoid.$init$(this);
            }
         }
      };
   }
}
