package cats.kernel;

import java.lang.invoke.SerializedLambda;
import scala.Option;
import scala.collection.IterableOnce;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction2;

public interface Monoid$mcJ$sp extends Monoid, Semigroup$mcJ$sp {
   default boolean isEmpty(final long a, final Eq ev) {
      return this.isEmpty$mcJ$sp(a, ev);
   }

   default boolean isEmpty$mcJ$sp(final long a, final Eq ev) {
      return ev.eqv$mcJ$sp(a, this.empty$mcJ$sp());
   }

   // $FF: synthetic method
   static long combineN$(final Monoid$mcJ$sp $this, final long a, final int n) {
      return $this.combineN(a, n);
   }

   default long combineN(final long a, final int n) {
      return this.combineN$mcJ$sp(a, n);
   }

   // $FF: synthetic method
   static long combineN$mcJ$sp$(final Monoid$mcJ$sp $this, final long a, final int n) {
      return $this.combineN$mcJ$sp(a, n);
   }

   default long combineN$mcJ$sp(final long a, final int n) {
      if (n < 0) {
         throw new IllegalArgumentException("Repeated combining for monoids must have n >= 0");
      } else {
         return n == 0 ? this.empty$mcJ$sp() : this.repeatedCombineN$mcJ$sp(a, n);
      }
   }

   default long combineAll(final IterableOnce as) {
      return this.combineAll$mcJ$sp(as);
   }

   default long combineAll$mcJ$sp(final IterableOnce as) {
      return BoxesRunTime.unboxToLong(as.iterator().foldLeft(BoxesRunTime.boxToLong(this.empty$mcJ$sp()), (JFunction2.mcJJJ.sp)(x, y) -> this.combine$mcJ$sp(x, y)));
   }

   // $FF: synthetic method
   static Monoid reverse$(final Monoid$mcJ$sp $this) {
      return $this.reverse();
   }

   default Monoid reverse() {
      return this.reverse$mcJ$sp();
   }

   // $FF: synthetic method
   static Monoid reverse$mcJ$sp$(final Monoid$mcJ$sp $this) {
      return $this.reverse$mcJ$sp();
   }

   default Monoid reverse$mcJ$sp() {
      return new Monoid$mcJ$sp() {
         // $FF: synthetic field
         private final Monoid$mcJ$sp $outer;

         public boolean isEmpty(final long a, final Eq ev) {
            return Monoid$mcJ$sp.super.isEmpty(a, ev);
         }

         public boolean isEmpty$mcJ$sp(final long a, final Eq ev) {
            return Monoid$mcJ$sp.super.isEmpty$mcJ$sp(a, ev);
         }

         public long combineAll(final IterableOnce as) {
            return Monoid$mcJ$sp.super.combineAll(as);
         }

         public long combineAll$mcJ$sp(final IterableOnce as) {
            return Monoid$mcJ$sp.super.combineAll$mcJ$sp(as);
         }

         public long repeatedCombineN(final long a, final int n) {
            return Semigroup$mcJ$sp.repeatedCombineN$(this, a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Semigroup$mcJ$sp.repeatedCombineN$mcJ$sp$(this, a, n);
         }

         public Semigroup intercalate(final long middle) {
            return Semigroup$mcJ$sp.intercalate$(this, middle);
         }

         public Semigroup intercalate$mcJ$sp(final long middle) {
            return Semigroup$mcJ$sp.intercalate$mcJ$sp$(this, middle);
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

         public long combine(final long a, final long b) {
            return this.combine$mcJ$sp(a, b);
         }

         public long combineN(final long a, final int n) {
            return this.combineN$mcJ$sp(a, n);
         }

         public Monoid reverse() {
            return this.reverse$mcJ$sp();
         }

         public long empty$mcJ$sp() {
            return this.$outer.empty$mcJ$sp();
         }

         public long combine$mcJ$sp(final long a, final long b) {
            return this.$outer.combine$mcJ$sp(b, a);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return this.$outer.combineN$mcJ$sp(a, n);
         }

         public Monoid reverse$mcJ$sp() {
            return this.$outer;
         }

         public {
            if (Monoid$mcJ$sp.this == null) {
               throw null;
            } else {
               this.$outer = Monoid$mcJ$sp.this;
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
