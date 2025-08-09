package cats.kernel;

import java.lang.invoke.SerializedLambda;
import scala.Option;
import scala.collection.IterableOnce;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction2;

public interface Monoid$mcD$sp extends Monoid, Semigroup$mcD$sp {
   default boolean isEmpty(final double a, final Eq ev) {
      return this.isEmpty$mcD$sp(a, ev);
   }

   default boolean isEmpty$mcD$sp(final double a, final Eq ev) {
      return ev.eqv$mcD$sp(a, this.empty$mcD$sp());
   }

   // $FF: synthetic method
   static double combineN$(final Monoid$mcD$sp $this, final double a, final int n) {
      return $this.combineN(a, n);
   }

   default double combineN(final double a, final int n) {
      return this.combineN$mcD$sp(a, n);
   }

   // $FF: synthetic method
   static double combineN$mcD$sp$(final Monoid$mcD$sp $this, final double a, final int n) {
      return $this.combineN$mcD$sp(a, n);
   }

   default double combineN$mcD$sp(final double a, final int n) {
      if (n < 0) {
         throw new IllegalArgumentException("Repeated combining for monoids must have n >= 0");
      } else {
         return n == 0 ? this.empty$mcD$sp() : this.repeatedCombineN$mcD$sp(a, n);
      }
   }

   default double combineAll(final IterableOnce as) {
      return this.combineAll$mcD$sp(as);
   }

   default double combineAll$mcD$sp(final IterableOnce as) {
      return BoxesRunTime.unboxToDouble(as.iterator().foldLeft(BoxesRunTime.boxToDouble(this.empty$mcD$sp()), (JFunction2.mcDDD.sp)(x, y) -> this.combine$mcD$sp(x, y)));
   }

   // $FF: synthetic method
   static Monoid reverse$(final Monoid$mcD$sp $this) {
      return $this.reverse();
   }

   default Monoid reverse() {
      return this.reverse$mcD$sp();
   }

   // $FF: synthetic method
   static Monoid reverse$mcD$sp$(final Monoid$mcD$sp $this) {
      return $this.reverse$mcD$sp();
   }

   default Monoid reverse$mcD$sp() {
      return new Monoid$mcD$sp() {
         // $FF: synthetic field
         private final Monoid$mcD$sp $outer;

         public boolean isEmpty(final double a, final Eq ev) {
            return Monoid$mcD$sp.super.isEmpty(a, ev);
         }

         public boolean isEmpty$mcD$sp(final double a, final Eq ev) {
            return Monoid$mcD$sp.super.isEmpty$mcD$sp(a, ev);
         }

         public double combineAll(final IterableOnce as) {
            return Monoid$mcD$sp.super.combineAll(as);
         }

         public double combineAll$mcD$sp(final IterableOnce as) {
            return Monoid$mcD$sp.super.combineAll$mcD$sp(as);
         }

         public double repeatedCombineN(final double a, final int n) {
            return Semigroup$mcD$sp.repeatedCombineN$(this, a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Semigroup$mcD$sp.repeatedCombineN$mcD$sp$(this, a, n);
         }

         public Semigroup intercalate(final double middle) {
            return Semigroup$mcD$sp.intercalate$(this, middle);
         }

         public Semigroup intercalate$mcD$sp(final double middle) {
            return Semigroup$mcD$sp.intercalate$mcD$sp$(this, middle);
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

         public double combine(final double a, final double b) {
            return this.combine$mcD$sp(a, b);
         }

         public double combineN(final double a, final int n) {
            return this.combineN$mcD$sp(a, n);
         }

         public Monoid reverse() {
            return this.reverse$mcD$sp();
         }

         public double empty$mcD$sp() {
            return this.$outer.empty$mcD$sp();
         }

         public double combine$mcD$sp(final double a, final double b) {
            return this.$outer.combine$mcD$sp(b, a);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return this.$outer.combineN$mcD$sp(a, n);
         }

         public Monoid reverse$mcD$sp() {
            return this.$outer;
         }

         public {
            if (Monoid$mcD$sp.this == null) {
               throw null;
            } else {
               this.$outer = Monoid$mcD$sp.this;
               Semigroup.$init$(this);
               Monoid.$init$(this);
            }
         }
      };
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
