package cats.kernel;

import java.lang.invoke.SerializedLambda;
import scala.Option;
import scala.collection.IterableOnce;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction2;

public interface Monoid$mcI$sp extends Monoid, Semigroup$mcI$sp {
   default boolean isEmpty(final int a, final Eq ev) {
      return this.isEmpty$mcI$sp(a, ev);
   }

   default boolean isEmpty$mcI$sp(final int a, final Eq ev) {
      return ev.eqv$mcI$sp(a, this.empty$mcI$sp());
   }

   // $FF: synthetic method
   static int combineN$(final Monoid$mcI$sp $this, final int a, final int n) {
      return $this.combineN(a, n);
   }

   default int combineN(final int a, final int n) {
      return this.combineN$mcI$sp(a, n);
   }

   // $FF: synthetic method
   static int combineN$mcI$sp$(final Monoid$mcI$sp $this, final int a, final int n) {
      return $this.combineN$mcI$sp(a, n);
   }

   default int combineN$mcI$sp(final int a, final int n) {
      if (n < 0) {
         throw new IllegalArgumentException("Repeated combining for monoids must have n >= 0");
      } else {
         return n == 0 ? this.empty$mcI$sp() : this.repeatedCombineN$mcI$sp(a, n);
      }
   }

   default int combineAll(final IterableOnce as) {
      return this.combineAll$mcI$sp(as);
   }

   default int combineAll$mcI$sp(final IterableOnce as) {
      return BoxesRunTime.unboxToInt(as.iterator().foldLeft(BoxesRunTime.boxToInteger(this.empty$mcI$sp()), (JFunction2.mcIII.sp)(x, y) -> this.combine$mcI$sp(x, y)));
   }

   // $FF: synthetic method
   static Monoid reverse$(final Monoid$mcI$sp $this) {
      return $this.reverse();
   }

   default Monoid reverse() {
      return this.reverse$mcI$sp();
   }

   // $FF: synthetic method
   static Monoid reverse$mcI$sp$(final Monoid$mcI$sp $this) {
      return $this.reverse$mcI$sp();
   }

   default Monoid reverse$mcI$sp() {
      return new Monoid$mcI$sp() {
         // $FF: synthetic field
         private final Monoid$mcI$sp $outer;

         public boolean isEmpty(final int a, final Eq ev) {
            return Monoid$mcI$sp.super.isEmpty(a, ev);
         }

         public boolean isEmpty$mcI$sp(final int a, final Eq ev) {
            return Monoid$mcI$sp.super.isEmpty$mcI$sp(a, ev);
         }

         public int combineAll(final IterableOnce as) {
            return Monoid$mcI$sp.super.combineAll(as);
         }

         public int combineAll$mcI$sp(final IterableOnce as) {
            return Monoid$mcI$sp.super.combineAll$mcI$sp(as);
         }

         public int repeatedCombineN(final int a, final int n) {
            return Semigroup$mcI$sp.repeatedCombineN$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return Semigroup$mcI$sp.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public Semigroup intercalate(final int middle) {
            return Semigroup$mcI$sp.intercalate$(this, middle);
         }

         public Semigroup intercalate$mcI$sp(final int middle) {
            return Semigroup$mcI$sp.intercalate$mcI$sp$(this, middle);
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

         public int combine(final int a, final int b) {
            return this.combine$mcI$sp(a, b);
         }

         public int combineN(final int a, final int n) {
            return this.combineN$mcI$sp(a, n);
         }

         public Monoid reverse() {
            return this.reverse$mcI$sp();
         }

         public int empty$mcI$sp() {
            return this.$outer.empty$mcI$sp();
         }

         public int combine$mcI$sp(final int a, final int b) {
            return this.$outer.combine$mcI$sp(b, a);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return this.$outer.combineN$mcI$sp(a, n);
         }

         public Monoid reverse$mcI$sp() {
            return this.$outer;
         }

         public {
            if (Monoid$mcI$sp.this == null) {
               throw null;
            } else {
               this.$outer = Monoid$mcI$sp.this;
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
