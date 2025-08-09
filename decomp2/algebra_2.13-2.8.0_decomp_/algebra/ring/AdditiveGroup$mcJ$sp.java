package algebra.ring;

import cats.kernel.Eq;
import cats.kernel.Group;
import cats.kernel.Monoid;
import cats.kernel.Semigroup;
import cats.kernel.Group.mcJ.sp;
import scala.Option;
import scala.collection.IterableOnce;

public interface AdditiveGroup$mcJ$sp extends AdditiveGroup, AdditiveMonoid$mcJ$sp {
   // $FF: synthetic method
   static Group additive$(final AdditiveGroup$mcJ$sp $this) {
      return $this.additive();
   }

   default Group additive() {
      return this.additive$mcJ$sp();
   }

   // $FF: synthetic method
   static Group additive$mcJ$sp$(final AdditiveGroup$mcJ$sp $this) {
      return $this.additive$mcJ$sp();
   }

   default Group additive$mcJ$sp() {
      return new Group.mcJ.sp() {
         // $FF: synthetic field
         private final AdditiveGroup$mcJ$sp $outer;

         public long combineN(final long a, final int n) {
            return sp.combineN$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return sp.combineN$mcJ$sp$(this, a, n);
         }

         public boolean isEmpty(final long a, final Eq ev) {
            return cats.kernel.Monoid.mcJ.sp.isEmpty$(this, a, ev);
         }

         public boolean isEmpty$mcJ$sp(final long a, final Eq ev) {
            return cats.kernel.Monoid.mcJ.sp.isEmpty$mcJ$sp$(this, a, ev);
         }

         public Monoid reverse() {
            return cats.kernel.Monoid.mcJ.sp.reverse$(this);
         }

         public Monoid reverse$mcJ$sp() {
            return cats.kernel.Monoid.mcJ.sp.reverse$mcJ$sp$(this);
         }

         public long repeatedCombineN(final long a, final int n) {
            return cats.kernel.Semigroup.mcJ.sp.repeatedCombineN$(this, a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return cats.kernel.Semigroup.mcJ.sp.repeatedCombineN$mcJ$sp$(this, a, n);
         }

         public Semigroup intercalate(final long middle) {
            return cats.kernel.Semigroup.mcJ.sp.intercalate$(this, middle);
         }

         public Semigroup intercalate$mcJ$sp(final long middle) {
            return cats.kernel.Semigroup.mcJ.sp.intercalate$mcJ$sp$(this, middle);
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

         public Monoid reverse$mcD$sp() {
            return Monoid.reverse$mcD$sp$(this);
         }

         public Monoid reverse$mcF$sp() {
            return Monoid.reverse$mcF$sp$(this);
         }

         public Monoid reverse$mcI$sp() {
            return Monoid.reverse$mcI$sp$(this);
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

         public Semigroup intercalate$mcD$sp(final double middle) {
            return Semigroup.intercalate$mcD$sp$(this, middle);
         }

         public Semigroup intercalate$mcF$sp(final float middle) {
            return Semigroup.intercalate$mcF$sp$(this, middle);
         }

         public Semigroup intercalate$mcI$sp(final int middle) {
            return Semigroup.intercalate$mcI$sp$(this, middle);
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
            if (AdditiveGroup$mcJ$sp.this == null) {
               throw null;
            } else {
               this.$outer = AdditiveGroup$mcJ$sp.this;
               Semigroup.$init$(this);
               Monoid.$init$(this);
               Group.$init$(this);
            }
         }
      };
   }

   // $FF: synthetic method
   static long minus$(final AdditiveGroup$mcJ$sp $this, final long x, final long y) {
      return $this.minus(x, y);
   }

   default long minus(final long x, final long y) {
      return this.minus$mcJ$sp(x, y);
   }

   // $FF: synthetic method
   static long minus$mcJ$sp$(final AdditiveGroup$mcJ$sp $this, final long x, final long y) {
      return $this.minus$mcJ$sp(x, y);
   }

   default long minus$mcJ$sp(final long x, final long y) {
      return this.plus$mcJ$sp(x, this.negate$mcJ$sp(y));
   }

   // $FF: synthetic method
   static long sumN$(final AdditiveGroup$mcJ$sp $this, final long a, final int n) {
      return $this.sumN(a, n);
   }

   default long sumN(final long a, final int n) {
      return this.sumN$mcJ$sp(a, n);
   }

   // $FF: synthetic method
   static long sumN$mcJ$sp$(final AdditiveGroup$mcJ$sp $this, final long a, final int n) {
      return $this.sumN$mcJ$sp(a, n);
   }

   default long sumN$mcJ$sp(final long a, final int n) {
      return n > 0 ? this.positiveSumN$mcJ$sp(a, n) : (n == 0 ? this.zero$mcJ$sp() : (n == Integer.MIN_VALUE ? this.positiveSumN$mcJ$sp(this.negate$mcJ$sp(this.plus$mcJ$sp(a, a)), 1073741824) : this.positiveSumN$mcJ$sp(this.negate$mcJ$sp(a), -n)));
   }
}
