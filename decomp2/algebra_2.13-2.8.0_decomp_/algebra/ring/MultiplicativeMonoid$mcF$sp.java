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

public interface MultiplicativeMonoid$mcF$sp extends MultiplicativeMonoid, MultiplicativeSemigroup$mcF$sp {
   // $FF: synthetic method
   static Monoid multiplicative$(final MultiplicativeMonoid$mcF$sp $this) {
      return $this.multiplicative();
   }

   default Monoid multiplicative() {
      return this.multiplicative$mcF$sp();
   }

   // $FF: synthetic method
   static Monoid multiplicative$mcF$sp$(final MultiplicativeMonoid$mcF$sp $this) {
      return $this.multiplicative$mcF$sp();
   }

   default Monoid multiplicative$mcF$sp() {
      return new Monoid.mcF.sp() {
         // $FF: synthetic field
         private final MultiplicativeMonoid$mcF$sp $outer;

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

         public float combineAll(final IterableOnce as) {
            return sp.combineAll$(this, as);
         }

         public float combineAll$mcF$sp(final IterableOnce as) {
            return sp.combineAll$mcF$sp$(this, as);
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

         public Option combineAllOption(final IterableOnce as) {
            return Monoid.combineAllOption$(this, as);
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

         public float empty$mcF$sp() {
            return this.$outer.one$mcF$sp();
         }

         public float combine$mcF$sp(final float x, final float y) {
            return this.$outer.times$mcF$sp(x, y);
         }

         public {
            if (MultiplicativeMonoid$mcF$sp.this == null) {
               throw null;
            } else {
               this.$outer = MultiplicativeMonoid$mcF$sp.this;
               Semigroup.$init$(this);
               Monoid.$init$(this);
            }
         }
      };
   }

   // $FF: synthetic method
   static boolean isOne$(final MultiplicativeMonoid$mcF$sp $this, final float a, final Eq ev) {
      return $this.isOne(a, ev);
   }

   default boolean isOne(final float a, final Eq ev) {
      return this.isOne$mcF$sp(a, ev);
   }

   // $FF: synthetic method
   static boolean isOne$mcF$sp$(final MultiplicativeMonoid$mcF$sp $this, final float a, final Eq ev) {
      return $this.isOne$mcF$sp(a, ev);
   }

   default boolean isOne$mcF$sp(final float a, final Eq ev) {
      return ev.eqv$mcF$sp(a, this.one$mcF$sp());
   }

   // $FF: synthetic method
   static float pow$(final MultiplicativeMonoid$mcF$sp $this, final float a, final int n) {
      return $this.pow(a, n);
   }

   default float pow(final float a, final int n) {
      return this.pow$mcF$sp(a, n);
   }

   // $FF: synthetic method
   static float pow$mcF$sp$(final MultiplicativeMonoid$mcF$sp $this, final float a, final int n) {
      return $this.pow$mcF$sp(a, n);
   }

   default float pow$mcF$sp(final float a, final int n) {
      float var10000;
      if (n > 0) {
         var10000 = this.positivePow$mcF$sp(a, n);
      } else {
         if (n != 0) {
            throw new IllegalArgumentException(.MODULE$.format$extension(scala.Predef..MODULE$.augmentString("Illegal negative exponent to pow: %s"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(n)})));
         }

         var10000 = this.one$mcF$sp();
      }

      return var10000;
   }

   // $FF: synthetic method
   static float product$(final MultiplicativeMonoid$mcF$sp $this, final IterableOnce as) {
      return $this.product(as);
   }

   default float product(final IterableOnce as) {
      return this.product$mcF$sp(as);
   }

   // $FF: synthetic method
   static float product$mcF$sp$(final MultiplicativeMonoid$mcF$sp $this, final IterableOnce as) {
      return $this.product$mcF$sp(as);
   }

   default float product$mcF$sp(final IterableOnce as) {
      return BoxesRunTime.unboxToFloat(scala.collection.IterableOnceExtensionMethods..MODULE$.foldLeft$extension(scala.collection.IterableOnce..MODULE$.iterableOnceExtensionMethods(as), BoxesRunTime.boxToFloat(this.one$mcF$sp()), (x, y) -> BoxesRunTime.boxToFloat($anonfun$product$3(this, BoxesRunTime.unboxToFloat(x), BoxesRunTime.unboxToFloat(y)))));
   }

   // $FF: synthetic method
   static float $anonfun$product$3(final MultiplicativeMonoid$mcF$sp $this, final float x, final float y) {
      return $this.times$mcF$sp(x, y);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
