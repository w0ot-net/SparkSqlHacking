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

public interface MultiplicativeMonoid$mcI$sp extends MultiplicativeMonoid, MultiplicativeSemigroup$mcI$sp {
   // $FF: synthetic method
   static Monoid multiplicative$(final MultiplicativeMonoid$mcI$sp $this) {
      return $this.multiplicative();
   }

   default Monoid multiplicative() {
      return this.multiplicative$mcI$sp();
   }

   // $FF: synthetic method
   static Monoid multiplicative$mcI$sp$(final MultiplicativeMonoid$mcI$sp $this) {
      return $this.multiplicative$mcI$sp();
   }

   default Monoid multiplicative$mcI$sp() {
      return new Monoid.mcI.sp() {
         // $FF: synthetic field
         private final MultiplicativeMonoid$mcI$sp $outer;

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

         public int combineAll(final IterableOnce as) {
            return sp.combineAll$(this, as);
         }

         public int combineAll$mcI$sp(final IterableOnce as) {
            return sp.combineAll$mcI$sp$(this, as);
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

         public Option combineAllOption(final IterableOnce as) {
            return Monoid.combineAllOption$(this, as);
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

         public int empty$mcI$sp() {
            return this.$outer.one$mcI$sp();
         }

         public int combine$mcI$sp(final int x, final int y) {
            return this.$outer.times$mcI$sp(x, y);
         }

         public {
            if (MultiplicativeMonoid$mcI$sp.this == null) {
               throw null;
            } else {
               this.$outer = MultiplicativeMonoid$mcI$sp.this;
               Semigroup.$init$(this);
               Monoid.$init$(this);
            }
         }
      };
   }

   // $FF: synthetic method
   static boolean isOne$(final MultiplicativeMonoid$mcI$sp $this, final int a, final Eq ev) {
      return $this.isOne(a, ev);
   }

   default boolean isOne(final int a, final Eq ev) {
      return this.isOne$mcI$sp(a, ev);
   }

   // $FF: synthetic method
   static boolean isOne$mcI$sp$(final MultiplicativeMonoid$mcI$sp $this, final int a, final Eq ev) {
      return $this.isOne$mcI$sp(a, ev);
   }

   default boolean isOne$mcI$sp(final int a, final Eq ev) {
      return ev.eqv$mcI$sp(a, this.one$mcI$sp());
   }

   // $FF: synthetic method
   static int pow$(final MultiplicativeMonoid$mcI$sp $this, final int a, final int n) {
      return $this.pow(a, n);
   }

   default int pow(final int a, final int n) {
      return this.pow$mcI$sp(a, n);
   }

   // $FF: synthetic method
   static int pow$mcI$sp$(final MultiplicativeMonoid$mcI$sp $this, final int a, final int n) {
      return $this.pow$mcI$sp(a, n);
   }

   default int pow$mcI$sp(final int a, final int n) {
      int var10000;
      if (n > 0) {
         var10000 = this.positivePow$mcI$sp(a, n);
      } else {
         if (n != 0) {
            throw new IllegalArgumentException(.MODULE$.format$extension(scala.Predef..MODULE$.augmentString("Illegal negative exponent to pow: %s"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(n)})));
         }

         var10000 = this.one$mcI$sp();
      }

      return var10000;
   }

   // $FF: synthetic method
   static int product$(final MultiplicativeMonoid$mcI$sp $this, final IterableOnce as) {
      return $this.product(as);
   }

   default int product(final IterableOnce as) {
      return this.product$mcI$sp(as);
   }

   // $FF: synthetic method
   static int product$mcI$sp$(final MultiplicativeMonoid$mcI$sp $this, final IterableOnce as) {
      return $this.product$mcI$sp(as);
   }

   default int product$mcI$sp(final IterableOnce as) {
      return BoxesRunTime.unboxToInt(scala.collection.IterableOnceExtensionMethods..MODULE$.foldLeft$extension(scala.collection.IterableOnce..MODULE$.iterableOnceExtensionMethods(as), BoxesRunTime.boxToInteger(this.one$mcI$sp()), (JFunction2.mcIII.sp)(x, y) -> this.times$mcI$sp(x, y)));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
