package algebra.ring;

import cats.kernel.Eq;
import cats.kernel.Group;
import cats.kernel.Monoid;
import cats.kernel.Semigroup;
import cats.kernel.Group.mcI.sp;
import scala.Option;
import scala.collection.IterableOnce;

public interface AdditiveGroup$mcI$sp extends AdditiveGroup, AdditiveMonoid$mcI$sp {
   // $FF: synthetic method
   static Group additive$(final AdditiveGroup$mcI$sp $this) {
      return $this.additive();
   }

   default Group additive() {
      return this.additive$mcI$sp();
   }

   // $FF: synthetic method
   static Group additive$mcI$sp$(final AdditiveGroup$mcI$sp $this) {
      return $this.additive$mcI$sp();
   }

   default Group additive$mcI$sp() {
      return new Group.mcI.sp() {
         // $FF: synthetic field
         private final AdditiveGroup$mcI$sp $outer;

         public int combineN(final int a, final int n) {
            return sp.combineN$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return sp.combineN$mcI$sp$(this, a, n);
         }

         public boolean isEmpty(final int a, final Eq ev) {
            return cats.kernel.Monoid.mcI.sp.isEmpty$(this, a, ev);
         }

         public boolean isEmpty$mcI$sp(final int a, final Eq ev) {
            return cats.kernel.Monoid.mcI.sp.isEmpty$mcI$sp$(this, a, ev);
         }

         public Monoid reverse() {
            return cats.kernel.Monoid.mcI.sp.reverse$(this);
         }

         public Monoid reverse$mcI$sp() {
            return cats.kernel.Monoid.mcI.sp.reverse$mcI$sp$(this);
         }

         public int repeatedCombineN(final int a, final int n) {
            return cats.kernel.Semigroup.mcI.sp.repeatedCombineN$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return cats.kernel.Semigroup.mcI.sp.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public Semigroup intercalate(final int middle) {
            return cats.kernel.Semigroup.mcI.sp.intercalate$(this, middle);
         }

         public Semigroup intercalate$mcI$sp(final int middle) {
            return cats.kernel.Semigroup.mcI.sp.intercalate$mcI$sp$(this, middle);
         }

         public double inverse$mcD$sp(final double a) {
            return Group.inverse$mcD$sp$(this, a);
         }

         public float inverse$mcF$sp(final float a) {
            return Group.inverse$mcF$sp$(this, a);
         }

         public long inverse$mcJ$sp(final long a) {
            return Group.inverse$mcJ$sp$(this, a);
         }

         public double remove$mcD$sp(final double a, final double b) {
            return Group.remove$mcD$sp$(this, a, b);
         }

         public float remove$mcF$sp(final float a, final float b) {
            return Group.remove$mcF$sp$(this, a, b);
         }

         public long remove$mcJ$sp(final long a, final long b) {
            return Group.remove$mcJ$sp$(this, a, b);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Group.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Group.combineN$mcF$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Group.combineN$mcJ$sp$(this, a, n);
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

         public double combineAll$mcD$sp(final IterableOnce as) {
            return Monoid.combineAll$mcD$sp$(this, as);
         }

         public float combineAll$mcF$sp(final IterableOnce as) {
            return Monoid.combineAll$mcF$sp$(this, as);
         }

         public long combineAll$mcJ$sp(final IterableOnce as) {
            return Monoid.combineAll$mcJ$sp$(this, as);
         }

         public Monoid reverse$mcD$sp() {
            return Monoid.reverse$mcD$sp$(this);
         }

         public Monoid reverse$mcF$sp() {
            return Monoid.reverse$mcF$sp$(this);
         }

         public Monoid reverse$mcJ$sp() {
            return Monoid.reverse$mcJ$sp$(this);
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

         public Semigroup intercalate$mcD$sp(final double middle) {
            return Semigroup.intercalate$mcD$sp$(this, middle);
         }

         public Semigroup intercalate$mcF$sp(final float middle) {
            return Semigroup.intercalate$mcF$sp$(this, middle);
         }

         public Semigroup intercalate$mcJ$sp(final long middle) {
            return Semigroup.intercalate$mcJ$sp$(this, middle);
         }

         public int empty() {
            return this.empty$mcI$sp();
         }

         public int combine(final int x, final int y) {
            return this.combine$mcI$sp(x, y);
         }

         public int remove(final int x, final int y) {
            return this.remove$mcI$sp(x, y);
         }

         public int inverse(final int x) {
            return this.inverse$mcI$sp(x);
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

         public int remove$mcI$sp(final int x, final int y) {
            return this.$outer.minus$mcI$sp(x, y);
         }

         public int inverse$mcI$sp(final int x) {
            return this.$outer.negate$mcI$sp(x);
         }

         public int combineAll$mcI$sp(final IterableOnce as) {
            return this.$outer.sum$mcI$sp(as);
         }

         public {
            if (AdditiveGroup$mcI$sp.this == null) {
               throw null;
            } else {
               this.$outer = AdditiveGroup$mcI$sp.this;
               Semigroup.$init$(this);
               Monoid.$init$(this);
               Group.$init$(this);
            }
         }
      };
   }

   // $FF: synthetic method
   static int minus$(final AdditiveGroup$mcI$sp $this, final int x, final int y) {
      return $this.minus(x, y);
   }

   default int minus(final int x, final int y) {
      return this.minus$mcI$sp(x, y);
   }

   // $FF: synthetic method
   static int minus$mcI$sp$(final AdditiveGroup$mcI$sp $this, final int x, final int y) {
      return $this.minus$mcI$sp(x, y);
   }

   default int minus$mcI$sp(final int x, final int y) {
      return this.plus$mcI$sp(x, this.negate$mcI$sp(y));
   }

   // $FF: synthetic method
   static int sumN$(final AdditiveGroup$mcI$sp $this, final int a, final int n) {
      return $this.sumN(a, n);
   }

   default int sumN(final int a, final int n) {
      return this.sumN$mcI$sp(a, n);
   }

   // $FF: synthetic method
   static int sumN$mcI$sp$(final AdditiveGroup$mcI$sp $this, final int a, final int n) {
      return $this.sumN$mcI$sp(a, n);
   }

   default int sumN$mcI$sp(final int a, final int n) {
      return n > 0 ? this.positiveSumN$mcI$sp(a, n) : (n == 0 ? this.zero$mcI$sp() : (n == Integer.MIN_VALUE ? this.positiveSumN$mcI$sp(this.negate$mcI$sp(this.plus$mcI$sp(a, a)), 1073741824) : this.positiveSumN$mcI$sp(this.negate$mcI$sp(a), -n)));
   }
}
