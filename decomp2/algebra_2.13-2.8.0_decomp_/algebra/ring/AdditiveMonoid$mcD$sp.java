package algebra.ring;

import cats.kernel.Eq;
import cats.kernel.Monoid;
import cats.kernel.Semigroup;
import cats.kernel.Monoid.mcD.sp;
import java.lang.invoke.SerializedLambda;
import scala.Option;
import scala.collection.IterableOnce;
import scala.collection.StringOps.;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction2;

public interface AdditiveMonoid$mcD$sp extends AdditiveMonoid, AdditiveSemigroup$mcD$sp {
   // $FF: synthetic method
   static Monoid additive$(final AdditiveMonoid$mcD$sp $this) {
      return $this.additive();
   }

   default Monoid additive() {
      return this.additive$mcD$sp();
   }

   // $FF: synthetic method
   static Monoid additive$mcD$sp$(final AdditiveMonoid$mcD$sp $this) {
      return $this.additive$mcD$sp();
   }

   default Monoid additive$mcD$sp() {
      return new Monoid.mcD.sp() {
         // $FF: synthetic field
         private final AdditiveMonoid$mcD$sp $outer;

         public boolean isEmpty(final double a, final Eq ev) {
            return sp.isEmpty$(this, a, ev);
         }

         public boolean isEmpty$mcD$sp(final double a, final Eq ev) {
            return sp.isEmpty$mcD$sp$(this, a, ev);
         }

         public double combineN(final double a, final int n) {
            return sp.combineN$(this, a, n);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return sp.combineN$mcD$sp$(this, a, n);
         }

         public Monoid reverse() {
            return sp.reverse$(this);
         }

         public Monoid reverse$mcD$sp() {
            return sp.reverse$mcD$sp$(this);
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
            if (AdditiveMonoid$mcD$sp.this == null) {
               throw null;
            } else {
               this.$outer = AdditiveMonoid$mcD$sp.this;
               Semigroup.$init$(this);
               Monoid.$init$(this);
            }
         }
      };
   }

   // $FF: synthetic method
   static boolean isZero$(final AdditiveMonoid$mcD$sp $this, final double a, final Eq ev) {
      return $this.isZero(a, ev);
   }

   default boolean isZero(final double a, final Eq ev) {
      return this.isZero$mcD$sp(a, ev);
   }

   // $FF: synthetic method
   static boolean isZero$mcD$sp$(final AdditiveMonoid$mcD$sp $this, final double a, final Eq ev) {
      return $this.isZero$mcD$sp(a, ev);
   }

   default boolean isZero$mcD$sp(final double a, final Eq ev) {
      return ev.eqv$mcD$sp(a, this.zero$mcD$sp());
   }

   // $FF: synthetic method
   static double sumN$(final AdditiveMonoid$mcD$sp $this, final double a, final int n) {
      return $this.sumN(a, n);
   }

   default double sumN(final double a, final int n) {
      return this.sumN$mcD$sp(a, n);
   }

   // $FF: synthetic method
   static double sumN$mcD$sp$(final AdditiveMonoid$mcD$sp $this, final double a, final int n) {
      return $this.sumN$mcD$sp(a, n);
   }

   default double sumN$mcD$sp(final double a, final int n) {
      double var10000;
      if (n > 0) {
         var10000 = this.positiveSumN$mcD$sp(a, n);
      } else {
         if (n != 0) {
            throw new IllegalArgumentException(.MODULE$.format$extension(scala.Predef..MODULE$.augmentString("Illegal negative exponent to sumN: %s"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(n)})));
         }

         var10000 = this.zero$mcD$sp();
      }

      return var10000;
   }

   // $FF: synthetic method
   static double sum$(final AdditiveMonoid$mcD$sp $this, final IterableOnce as) {
      return $this.sum(as);
   }

   default double sum(final IterableOnce as) {
      return this.sum$mcD$sp(as);
   }

   // $FF: synthetic method
   static double sum$mcD$sp$(final AdditiveMonoid$mcD$sp $this, final IterableOnce as) {
      return $this.sum$mcD$sp(as);
   }

   default double sum$mcD$sp(final IterableOnce as) {
      return BoxesRunTime.unboxToDouble(scala.collection.IterableOnceExtensionMethods..MODULE$.foldLeft$extension(scala.collection.IterableOnce..MODULE$.iterableOnceExtensionMethods(as), BoxesRunTime.boxToDouble(this.zero$mcD$sp()), (JFunction2.mcDDD.sp)(x, y) -> this.plus$mcD$sp(x, y)));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
