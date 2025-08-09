package algebra.ring;

import cats.kernel.Eq;
import cats.kernel.Monoid;
import cats.kernel.Semigroup;
import cats.kernel.Monoid.mcJ.sp;
import java.lang.invoke.SerializedLambda;
import scala.Option;
import scala.collection.IterableOnce;
import scala.collection.StringOps.;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction2;

public interface AdditiveMonoid$mcJ$sp extends AdditiveMonoid, AdditiveSemigroup$mcJ$sp {
   // $FF: synthetic method
   static Monoid additive$(final AdditiveMonoid$mcJ$sp $this) {
      return $this.additive();
   }

   default Monoid additive() {
      return this.additive$mcJ$sp();
   }

   // $FF: synthetic method
   static Monoid additive$mcJ$sp$(final AdditiveMonoid$mcJ$sp $this) {
      return $this.additive$mcJ$sp();
   }

   default Monoid additive$mcJ$sp() {
      return new Monoid.mcJ.sp() {
         // $FF: synthetic field
         private final AdditiveMonoid$mcJ$sp $outer;

         public boolean isEmpty(final long a, final Eq ev) {
            return sp.isEmpty$(this, a, ev);
         }

         public boolean isEmpty$mcJ$sp(final long a, final Eq ev) {
            return sp.isEmpty$mcJ$sp$(this, a, ev);
         }

         public long combineN(final long a, final int n) {
            return sp.combineN$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return sp.combineN$mcJ$sp$(this, a, n);
         }

         public Monoid reverse() {
            return sp.reverse$(this);
         }

         public Monoid reverse$mcJ$sp() {
            return sp.reverse$mcJ$sp$(this);
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

         public long combineAll$mcJ$sp(final IterableOnce as) {
            return this.$outer.sum$mcJ$sp(as);
         }

         public {
            if (AdditiveMonoid$mcJ$sp.this == null) {
               throw null;
            } else {
               this.$outer = AdditiveMonoid$mcJ$sp.this;
               Semigroup.$init$(this);
               Monoid.$init$(this);
            }
         }
      };
   }

   // $FF: synthetic method
   static boolean isZero$(final AdditiveMonoid$mcJ$sp $this, final long a, final Eq ev) {
      return $this.isZero(a, ev);
   }

   default boolean isZero(final long a, final Eq ev) {
      return this.isZero$mcJ$sp(a, ev);
   }

   // $FF: synthetic method
   static boolean isZero$mcJ$sp$(final AdditiveMonoid$mcJ$sp $this, final long a, final Eq ev) {
      return $this.isZero$mcJ$sp(a, ev);
   }

   default boolean isZero$mcJ$sp(final long a, final Eq ev) {
      return ev.eqv$mcJ$sp(a, this.zero$mcJ$sp());
   }

   // $FF: synthetic method
   static long sumN$(final AdditiveMonoid$mcJ$sp $this, final long a, final int n) {
      return $this.sumN(a, n);
   }

   default long sumN(final long a, final int n) {
      return this.sumN$mcJ$sp(a, n);
   }

   // $FF: synthetic method
   static long sumN$mcJ$sp$(final AdditiveMonoid$mcJ$sp $this, final long a, final int n) {
      return $this.sumN$mcJ$sp(a, n);
   }

   default long sumN$mcJ$sp(final long a, final int n) {
      long var10000;
      if (n > 0) {
         var10000 = this.positiveSumN$mcJ$sp(a, n);
      } else {
         if (n != 0) {
            throw new IllegalArgumentException(.MODULE$.format$extension(scala.Predef..MODULE$.augmentString("Illegal negative exponent to sumN: %s"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(n)})));
         }

         var10000 = this.zero$mcJ$sp();
      }

      return var10000;
   }

   // $FF: synthetic method
   static long sum$(final AdditiveMonoid$mcJ$sp $this, final IterableOnce as) {
      return $this.sum(as);
   }

   default long sum(final IterableOnce as) {
      return this.sum$mcJ$sp(as);
   }

   // $FF: synthetic method
   static long sum$mcJ$sp$(final AdditiveMonoid$mcJ$sp $this, final IterableOnce as) {
      return $this.sum$mcJ$sp(as);
   }

   default long sum$mcJ$sp(final IterableOnce as) {
      return BoxesRunTime.unboxToLong(scala.collection.IterableOnceExtensionMethods..MODULE$.foldLeft$extension(scala.collection.IterableOnce..MODULE$.iterableOnceExtensionMethods(as), BoxesRunTime.boxToLong(this.zero$mcJ$sp()), (JFunction2.mcJJJ.sp)(x, y) -> this.plus$mcJ$sp(x, y)));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
