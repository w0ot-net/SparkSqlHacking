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

public interface MultiplicativeMonoid$mcJ$sp extends MultiplicativeMonoid, MultiplicativeSemigroup$mcJ$sp {
   // $FF: synthetic method
   static Monoid multiplicative$(final MultiplicativeMonoid$mcJ$sp $this) {
      return $this.multiplicative();
   }

   default Monoid multiplicative() {
      return this.multiplicative$mcJ$sp();
   }

   // $FF: synthetic method
   static Monoid multiplicative$mcJ$sp$(final MultiplicativeMonoid$mcJ$sp $this) {
      return $this.multiplicative$mcJ$sp();
   }

   default Monoid multiplicative$mcJ$sp() {
      return new Monoid.mcJ.sp() {
         // $FF: synthetic field
         private final MultiplicativeMonoid$mcJ$sp $outer;

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

         public long combineAll(final IterableOnce as) {
            return sp.combineAll$(this, as);
         }

         public long combineAll$mcJ$sp(final IterableOnce as) {
            return sp.combineAll$mcJ$sp$(this, as);
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

         public Option combineAllOption(final IterableOnce as) {
            return Monoid.combineAllOption$(this, as);
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

         public long empty$mcJ$sp() {
            return this.$outer.one$mcJ$sp();
         }

         public long combine$mcJ$sp(final long x, final long y) {
            return this.$outer.times$mcJ$sp(x, y);
         }

         public {
            if (MultiplicativeMonoid$mcJ$sp.this == null) {
               throw null;
            } else {
               this.$outer = MultiplicativeMonoid$mcJ$sp.this;
               Semigroup.$init$(this);
               Monoid.$init$(this);
            }
         }
      };
   }

   // $FF: synthetic method
   static boolean isOne$(final MultiplicativeMonoid$mcJ$sp $this, final long a, final Eq ev) {
      return $this.isOne(a, ev);
   }

   default boolean isOne(final long a, final Eq ev) {
      return this.isOne$mcJ$sp(a, ev);
   }

   // $FF: synthetic method
   static boolean isOne$mcJ$sp$(final MultiplicativeMonoid$mcJ$sp $this, final long a, final Eq ev) {
      return $this.isOne$mcJ$sp(a, ev);
   }

   default boolean isOne$mcJ$sp(final long a, final Eq ev) {
      return ev.eqv$mcJ$sp(a, this.one$mcJ$sp());
   }

   // $FF: synthetic method
   static long pow$(final MultiplicativeMonoid$mcJ$sp $this, final long a, final int n) {
      return $this.pow(a, n);
   }

   default long pow(final long a, final int n) {
      return this.pow$mcJ$sp(a, n);
   }

   // $FF: synthetic method
   static long pow$mcJ$sp$(final MultiplicativeMonoid$mcJ$sp $this, final long a, final int n) {
      return $this.pow$mcJ$sp(a, n);
   }

   default long pow$mcJ$sp(final long a, final int n) {
      long var10000;
      if (n > 0) {
         var10000 = this.positivePow$mcJ$sp(a, n);
      } else {
         if (n != 0) {
            throw new IllegalArgumentException(.MODULE$.format$extension(scala.Predef..MODULE$.augmentString("Illegal negative exponent to pow: %s"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(n)})));
         }

         var10000 = this.one$mcJ$sp();
      }

      return var10000;
   }

   // $FF: synthetic method
   static long product$(final MultiplicativeMonoid$mcJ$sp $this, final IterableOnce as) {
      return $this.product(as);
   }

   default long product(final IterableOnce as) {
      return this.product$mcJ$sp(as);
   }

   // $FF: synthetic method
   static long product$mcJ$sp$(final MultiplicativeMonoid$mcJ$sp $this, final IterableOnce as) {
      return $this.product$mcJ$sp(as);
   }

   default long product$mcJ$sp(final IterableOnce as) {
      return BoxesRunTime.unboxToLong(scala.collection.IterableOnceExtensionMethods..MODULE$.foldLeft$extension(scala.collection.IterableOnce..MODULE$.iterableOnceExtensionMethods(as), BoxesRunTime.boxToLong(this.one$mcJ$sp()), (JFunction2.mcJJJ.sp)(x, y) -> this.times$mcJ$sp(x, y)));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
