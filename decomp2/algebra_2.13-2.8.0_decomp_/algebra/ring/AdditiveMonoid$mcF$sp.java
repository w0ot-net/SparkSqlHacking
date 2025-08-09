package algebra.ring;

import cats.kernel.Eq;
import cats.kernel.Monoid;
import cats.kernel.Semigroup;
import cats.kernel.Monoid.mcF.sp;
import java.lang.invoke.SerializedLambda;
import scala.Option;
import scala.collection.IterableOnce;
import scala.collection.StringOps.;
import scala.runtime.BoxesRunTime;

public interface AdditiveMonoid$mcF$sp extends AdditiveMonoid, AdditiveSemigroup$mcF$sp {
   // $FF: synthetic method
   static Monoid additive$(final AdditiveMonoid$mcF$sp $this) {
      return $this.additive();
   }

   default Monoid additive() {
      return this.additive$mcF$sp();
   }

   // $FF: synthetic method
   static Monoid additive$mcF$sp$(final AdditiveMonoid$mcF$sp $this) {
      return $this.additive$mcF$sp();
   }

   default Monoid additive$mcF$sp() {
      return new Monoid.mcF.sp() {
         // $FF: synthetic field
         private final AdditiveMonoid$mcF$sp $outer;

         public boolean isEmpty(final float a, final Eq ev) {
            return sp.isEmpty$(this, a, ev);
         }

         public boolean isEmpty$mcF$sp(final float a, final Eq ev) {
            return sp.isEmpty$mcF$sp$(this, a, ev);
         }

         public float combineN(final float a, final int n) {
            return sp.combineN$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return sp.combineN$mcF$sp$(this, a, n);
         }

         public Monoid reverse() {
            return sp.reverse$(this);
         }

         public Monoid reverse$mcF$sp() {
            return sp.reverse$mcF$sp$(this);
         }

         public float repeatedCombineN(final float a, final int n) {
            return cats.kernel.Semigroup.mcF.sp.repeatedCombineN$(this, a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return cats.kernel.Semigroup.mcF.sp.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public Semigroup intercalate(final float middle) {
            return cats.kernel.Semigroup.mcF.sp.intercalate$(this, middle);
         }

         public Semigroup intercalate$mcF$sp(final float middle) {
            return cats.kernel.Semigroup.mcF.sp.intercalate$mcF$sp$(this, middle);
         }

         public double empty$mcD$sp() {
            return Monoid.empty$mcD$sp$(this);
         }

         public int empty$mcI$sp() {
            return Monoid.empty$mcI$sp$(this);
         }

         public long empty$mcJ$sp() {
            return Monoid.empty$mcJ$sp$(this);
         }

         public boolean isEmpty$mcD$sp(final double a, final Eq ev) {
            return Monoid.isEmpty$mcD$sp$(this, a, ev);
         }

         public boolean isEmpty$mcI$sp(final int a, final Eq ev) {
            return Monoid.isEmpty$mcI$sp$(this, a, ev);
         }

         public boolean isEmpty$mcJ$sp(final long a, final Eq ev) {
            return Monoid.isEmpty$mcJ$sp$(this, a, ev);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Monoid.combineN$mcD$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Monoid.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Monoid.combineN$mcJ$sp$(this, a, n);
         }

         public double combineAll$mcD$sp(final IterableOnce as) {
            return Monoid.combineAll$mcD$sp$(this, as);
         }

         public int combineAll$mcI$sp(final IterableOnce as) {
            return Monoid.combineAll$mcI$sp$(this, as);
         }

         public long combineAll$mcJ$sp(final IterableOnce as) {
            return Monoid.combineAll$mcJ$sp$(this, as);
         }

         public Monoid reverse$mcD$sp() {
            return Monoid.reverse$mcD$sp$(this);
         }

         public Monoid reverse$mcI$sp() {
            return Monoid.reverse$mcI$sp$(this);
         }

         public Monoid reverse$mcJ$sp() {
            return Monoid.reverse$mcJ$sp$(this);
         }

         public double combine$mcD$sp(final double x, final double y) {
            return Semigroup.combine$mcD$sp$(this, x, y);
         }

         public int combine$mcI$sp(final int x, final int y) {
            return Semigroup.combine$mcI$sp$(this, x, y);
         }

         public long combine$mcJ$sp(final long x, final long y) {
            return Semigroup.combine$mcJ$sp$(this, x, y);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Semigroup.repeatedCombineN$mcD$sp$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return Semigroup.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Semigroup.repeatedCombineN$mcJ$sp$(this, a, n);
         }

         public Semigroup intercalate$mcD$sp(final double middle) {
            return Semigroup.intercalate$mcD$sp$(this, middle);
         }

         public Semigroup intercalate$mcI$sp(final int middle) {
            return Semigroup.intercalate$mcI$sp$(this, middle);
         }

         public Semigroup intercalate$mcJ$sp(final long middle) {
            return Semigroup.intercalate$mcJ$sp$(this, middle);
         }

         public float empty() {
            return this.empty$mcF$sp();
         }

         public float combine(final float x, final float y) {
            return this.combine$mcF$sp(x, y);
         }

         public Option combineAllOption(final IterableOnce as) {
            return this.$outer.trySum(as);
         }

         public float combineAll(final IterableOnce as) {
            return this.combineAll$mcF$sp(as);
         }

         public float empty$mcF$sp() {
            return this.$outer.zero$mcF$sp();
         }

         public float combine$mcF$sp(final float x, final float y) {
            return this.$outer.plus$mcF$sp(x, y);
         }

         public float combineAll$mcF$sp(final IterableOnce as) {
            return this.$outer.sum$mcF$sp(as);
         }

         public {
            if (AdditiveMonoid$mcF$sp.this == null) {
               throw null;
            } else {
               this.$outer = AdditiveMonoid$mcF$sp.this;
               Semigroup.$init$(this);
               Monoid.$init$(this);
            }
         }
      };
   }

   // $FF: synthetic method
   static boolean isZero$(final AdditiveMonoid$mcF$sp $this, final float a, final Eq ev) {
      return $this.isZero(a, ev);
   }

   default boolean isZero(final float a, final Eq ev) {
      return this.isZero$mcF$sp(a, ev);
   }

   // $FF: synthetic method
   static boolean isZero$mcF$sp$(final AdditiveMonoid$mcF$sp $this, final float a, final Eq ev) {
      return $this.isZero$mcF$sp(a, ev);
   }

   default boolean isZero$mcF$sp(final float a, final Eq ev) {
      return ev.eqv$mcF$sp(a, this.zero$mcF$sp());
   }

   // $FF: synthetic method
   static float sumN$(final AdditiveMonoid$mcF$sp $this, final float a, final int n) {
      return $this.sumN(a, n);
   }

   default float sumN(final float a, final int n) {
      return this.sumN$mcF$sp(a, n);
   }

   // $FF: synthetic method
   static float sumN$mcF$sp$(final AdditiveMonoid$mcF$sp $this, final float a, final int n) {
      return $this.sumN$mcF$sp(a, n);
   }

   default float sumN$mcF$sp(final float a, final int n) {
      float var10000;
      if (n > 0) {
         var10000 = this.positiveSumN$mcF$sp(a, n);
      } else {
         if (n != 0) {
            throw new IllegalArgumentException(.MODULE$.format$extension(scala.Predef..MODULE$.augmentString("Illegal negative exponent to sumN: %s"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(n)})));
         }

         var10000 = this.zero$mcF$sp();
      }

      return var10000;
   }

   // $FF: synthetic method
   static float sum$(final AdditiveMonoid$mcF$sp $this, final IterableOnce as) {
      return $this.sum(as);
   }

   default float sum(final IterableOnce as) {
      return this.sum$mcF$sp(as);
   }

   // $FF: synthetic method
   static float sum$mcF$sp$(final AdditiveMonoid$mcF$sp $this, final IterableOnce as) {
      return $this.sum$mcF$sp(as);
   }

   default float sum$mcF$sp(final IterableOnce as) {
      return BoxesRunTime.unboxToFloat(scala.collection.IterableOnceExtensionMethods..MODULE$.foldLeft$extension(scala.collection.IterableOnce..MODULE$.iterableOnceExtensionMethods(as), BoxesRunTime.boxToFloat(this.zero$mcF$sp()), (x, y) -> BoxesRunTime.boxToFloat($anonfun$sum$3(this, BoxesRunTime.unboxToFloat(x), BoxesRunTime.unboxToFloat(y)))));
   }

   // $FF: synthetic method
   static float $anonfun$sum$3(final AdditiveMonoid$mcF$sp $this, final float x, final float y) {
      return $this.plus$mcF$sp(x, y);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
