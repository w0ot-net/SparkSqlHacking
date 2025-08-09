package algebra.ring;

import cats.kernel.Eq;
import cats.kernel.Monoid;
import cats.kernel.Semigroup;
import cats.kernel.Monoid.mcI.sp;
import java.lang.invoke.SerializedLambda;
import scala.Option;
import scala.collection.IterableOnce;
import scala.collection.StringOps.;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction2;

public interface AdditiveMonoid$mcI$sp extends AdditiveMonoid, AdditiveSemigroup$mcI$sp {
   // $FF: synthetic method
   static Monoid additive$(final AdditiveMonoid$mcI$sp $this) {
      return $this.additive();
   }

   default Monoid additive() {
      return this.additive$mcI$sp();
   }

   // $FF: synthetic method
   static Monoid additive$mcI$sp$(final AdditiveMonoid$mcI$sp $this) {
      return $this.additive$mcI$sp();
   }

   default Monoid additive$mcI$sp() {
      return new Monoid.mcI.sp() {
         // $FF: synthetic field
         private final AdditiveMonoid$mcI$sp $outer;

         public boolean isEmpty(final int a, final Eq ev) {
            return sp.isEmpty$(this, a, ev);
         }

         public boolean isEmpty$mcI$sp(final int a, final Eq ev) {
            return sp.isEmpty$mcI$sp$(this, a, ev);
         }

         public int combineN(final int a, final int n) {
            return sp.combineN$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return sp.combineN$mcI$sp$(this, a, n);
         }

         public Monoid reverse() {
            return sp.reverse$(this);
         }

         public Monoid reverse$mcI$sp() {
            return sp.reverse$mcI$sp$(this);
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
            if (AdditiveMonoid$mcI$sp.this == null) {
               throw null;
            } else {
               this.$outer = AdditiveMonoid$mcI$sp.this;
               Semigroup.$init$(this);
               Monoid.$init$(this);
            }
         }
      };
   }

   // $FF: synthetic method
   static boolean isZero$(final AdditiveMonoid$mcI$sp $this, final int a, final Eq ev) {
      return $this.isZero(a, ev);
   }

   default boolean isZero(final int a, final Eq ev) {
      return this.isZero$mcI$sp(a, ev);
   }

   // $FF: synthetic method
   static boolean isZero$mcI$sp$(final AdditiveMonoid$mcI$sp $this, final int a, final Eq ev) {
      return $this.isZero$mcI$sp(a, ev);
   }

   default boolean isZero$mcI$sp(final int a, final Eq ev) {
      return ev.eqv$mcI$sp(a, this.zero$mcI$sp());
   }

   // $FF: synthetic method
   static int sumN$(final AdditiveMonoid$mcI$sp $this, final int a, final int n) {
      return $this.sumN(a, n);
   }

   default int sumN(final int a, final int n) {
      return this.sumN$mcI$sp(a, n);
   }

   // $FF: synthetic method
   static int sumN$mcI$sp$(final AdditiveMonoid$mcI$sp $this, final int a, final int n) {
      return $this.sumN$mcI$sp(a, n);
   }

   default int sumN$mcI$sp(final int a, final int n) {
      int var10000;
      if (n > 0) {
         var10000 = this.positiveSumN$mcI$sp(a, n);
      } else {
         if (n != 0) {
            throw new IllegalArgumentException(.MODULE$.format$extension(scala.Predef..MODULE$.augmentString("Illegal negative exponent to sumN: %s"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(n)})));
         }

         var10000 = this.zero$mcI$sp();
      }

      return var10000;
   }

   // $FF: synthetic method
   static int sum$(final AdditiveMonoid$mcI$sp $this, final IterableOnce as) {
      return $this.sum(as);
   }

   default int sum(final IterableOnce as) {
      return this.sum$mcI$sp(as);
   }

   // $FF: synthetic method
   static int sum$mcI$sp$(final AdditiveMonoid$mcI$sp $this, final IterableOnce as) {
      return $this.sum$mcI$sp(as);
   }

   default int sum$mcI$sp(final IterableOnce as) {
      return BoxesRunTime.unboxToInt(scala.collection.IterableOnceExtensionMethods..MODULE$.foldLeft$extension(scala.collection.IterableOnce..MODULE$.iterableOnceExtensionMethods(as), BoxesRunTime.boxToInteger(this.zero$mcI$sp()), (JFunction2.mcIII.sp)(x, y) -> this.plus$mcI$sp(x, y)));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
