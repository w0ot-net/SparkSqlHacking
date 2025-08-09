package algebra.ring;

import cats.kernel.Eq;
import cats.kernel.Group;
import cats.kernel.Monoid;
import cats.kernel.Semigroup;
import cats.kernel.Group.mcD.sp;
import scala.Option;
import scala.collection.IterableOnce;

public interface MultiplicativeGroup$mcD$sp extends MultiplicativeGroup, MultiplicativeMonoid$mcD$sp {
   // $FF: synthetic method
   static Group multiplicative$(final MultiplicativeGroup$mcD$sp $this) {
      return $this.multiplicative();
   }

   default Group multiplicative() {
      return this.multiplicative$mcD$sp();
   }

   // $FF: synthetic method
   static Group multiplicative$mcD$sp$(final MultiplicativeGroup$mcD$sp $this) {
      return $this.multiplicative$mcD$sp();
   }

   default Group multiplicative$mcD$sp() {
      return new Group.mcD.sp() {
         // $FF: synthetic field
         private final MultiplicativeGroup$mcD$sp $outer;

         public double combineN(final double a, final int n) {
            return sp.combineN$(this, a, n);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return sp.combineN$mcD$sp$(this, a, n);
         }

         public boolean isEmpty(final double a, final Eq ev) {
            return cats.kernel.Monoid.mcD.sp.isEmpty$(this, a, ev);
         }

         public boolean isEmpty$mcD$sp(final double a, final Eq ev) {
            return cats.kernel.Monoid.mcD.sp.isEmpty$mcD$sp$(this, a, ev);
         }

         public double combineAll(final IterableOnce as) {
            return cats.kernel.Monoid.mcD.sp.combineAll$(this, as);
         }

         public double combineAll$mcD$sp(final IterableOnce as) {
            return cats.kernel.Monoid.mcD.sp.combineAll$mcD$sp$(this, as);
         }

         public Monoid reverse() {
            return cats.kernel.Monoid.mcD.sp.reverse$(this);
         }

         public Monoid reverse$mcD$sp() {
            return cats.kernel.Monoid.mcD.sp.reverse$mcD$sp$(this);
         }

         public double repeatedCombineN(final double a, final int n) {
            return cats.kernel.Semigroup.mcD.sp.repeatedCombineN$(this, a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return cats.kernel.Semigroup.mcD.sp.repeatedCombineN$mcD$sp$(this, a, n);
         }

         public Semigroup intercalate(final double middle) {
            return cats.kernel.Semigroup.mcD.sp.intercalate$(this, middle);
         }

         public Semigroup intercalate$mcD$sp(final double middle) {
            return cats.kernel.Semigroup.mcD.sp.intercalate$mcD$sp$(this, middle);
         }

         public float inverse$mcF$sp(final float a) {
            return Group.inverse$mcF$sp$(this, a);
         }

         public int inverse$mcI$sp(final int a) {
            return Group.inverse$mcI$sp$(this, a);
         }

         public long inverse$mcJ$sp(final long a) {
            return Group.inverse$mcJ$sp$(this, a);
         }

         public float remove$mcF$sp(final float a, final float b) {
            return Group.remove$mcF$sp$(this, a, b);
         }

         public int remove$mcI$sp(final int a, final int b) {
            return Group.remove$mcI$sp$(this, a, b);
         }

         public long remove$mcJ$sp(final long a, final long b) {
            return Group.remove$mcJ$sp$(this, a, b);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Group.combineN$mcF$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Group.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Group.combineN$mcJ$sp$(this, a, n);
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

         public float combineAll$mcF$sp(final IterableOnce as) {
            return Monoid.combineAll$mcF$sp$(this, as);
         }

         public int combineAll$mcI$sp(final IterableOnce as) {
            return Monoid.combineAll$mcI$sp$(this, as);
         }

         public long combineAll$mcJ$sp(final IterableOnce as) {
            return Monoid.combineAll$mcJ$sp$(this, as);
         }

         public Option combineAllOption(final IterableOnce as) {
            return Monoid.combineAllOption$(this, as);
         }

         public Monoid reverse$mcF$sp() {
            return Monoid.reverse$mcF$sp$(this);
         }

         public Monoid reverse$mcI$sp() {
            return Monoid.reverse$mcI$sp$(this);
         }

         public Monoid reverse$mcJ$sp() {
            return Monoid.reverse$mcJ$sp$(this);
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

         public Semigroup intercalate$mcF$sp(final float middle) {
            return Semigroup.intercalate$mcF$sp$(this, middle);
         }

         public Semigroup intercalate$mcI$sp(final int middle) {
            return Semigroup.intercalate$mcI$sp$(this, middle);
         }

         public Semigroup intercalate$mcJ$sp(final long middle) {
            return Semigroup.intercalate$mcJ$sp$(this, middle);
         }

         public double empty() {
            return this.empty$mcD$sp();
         }

         public double combine(final double x, final double y) {
            return this.combine$mcD$sp(x, y);
         }

         public double remove(final double x, final double y) {
            return this.remove$mcD$sp(x, y);
         }

         public double inverse(final double x) {
            return this.inverse$mcD$sp(x);
         }

         public double empty$mcD$sp() {
            return this.$outer.one$mcD$sp();
         }

         public double combine$mcD$sp(final double x, final double y) {
            return this.$outer.times$mcD$sp(x, y);
         }

         public double remove$mcD$sp(final double x, final double y) {
            return this.$outer.div$mcD$sp(x, y);
         }

         public double inverse$mcD$sp(final double x) {
            return this.$outer.reciprocal$mcD$sp(x);
         }

         public {
            if (MultiplicativeGroup$mcD$sp.this == null) {
               throw null;
            } else {
               this.$outer = MultiplicativeGroup$mcD$sp.this;
               Semigroup.$init$(this);
               Monoid.$init$(this);
               Group.$init$(this);
            }
         }
      };
   }

   // $FF: synthetic method
   static double reciprocal$(final MultiplicativeGroup$mcD$sp $this, final double x) {
      return $this.reciprocal(x);
   }

   default double reciprocal(final double x) {
      return this.reciprocal$mcD$sp(x);
   }

   // $FF: synthetic method
   static double reciprocal$mcD$sp$(final MultiplicativeGroup$mcD$sp $this, final double x) {
      return $this.reciprocal$mcD$sp(x);
   }

   default double reciprocal$mcD$sp(final double x) {
      return this.div$mcD$sp(this.one$mcD$sp(), x);
   }

   // $FF: synthetic method
   static double pow$(final MultiplicativeGroup$mcD$sp $this, final double a, final int n) {
      return $this.pow(a, n);
   }

   default double pow(final double a, final int n) {
      return this.pow$mcD$sp(a, n);
   }

   // $FF: synthetic method
   static double pow$mcD$sp$(final MultiplicativeGroup$mcD$sp $this, final double a, final int n) {
      return $this.pow$mcD$sp(a, n);
   }

   default double pow$mcD$sp(final double a, final int n) {
      return n > 0 ? this.positivePow$mcD$sp(a, n) : (n == 0 ? this.one$mcD$sp() : (n == Integer.MIN_VALUE ? this.positivePow$mcD$sp(this.reciprocal$mcD$sp(this.times$mcD$sp(a, a)), 1073741824) : this.positivePow$mcD$sp(this.reciprocal$mcD$sp(a), -n)));
   }
}
