package cats.kernel;

import java.lang.invoke.SerializedLambda;
import scala.Option;
import scala.collection.IterableOnce;
import scala.runtime.BoxesRunTime;

public interface Monoid$mcF$sp extends Monoid, Semigroup$mcF$sp {
   default boolean isEmpty(final float a, final Eq ev) {
      return this.isEmpty$mcF$sp(a, ev);
   }

   default boolean isEmpty$mcF$sp(final float a, final Eq ev) {
      return ev.eqv$mcF$sp(a, this.empty$mcF$sp());
   }

   // $FF: synthetic method
   static float combineN$(final Monoid$mcF$sp $this, final float a, final int n) {
      return $this.combineN(a, n);
   }

   default float combineN(final float a, final int n) {
      return this.combineN$mcF$sp(a, n);
   }

   // $FF: synthetic method
   static float combineN$mcF$sp$(final Monoid$mcF$sp $this, final float a, final int n) {
      return $this.combineN$mcF$sp(a, n);
   }

   default float combineN$mcF$sp(final float a, final int n) {
      if (n < 0) {
         throw new IllegalArgumentException("Repeated combining for monoids must have n >= 0");
      } else {
         return n == 0 ? this.empty$mcF$sp() : this.repeatedCombineN$mcF$sp(a, n);
      }
   }

   default float combineAll(final IterableOnce as) {
      return this.combineAll$mcF$sp(as);
   }

   default float combineAll$mcF$sp(final IterableOnce as) {
      return BoxesRunTime.unboxToFloat(as.iterator().foldLeft(BoxesRunTime.boxToFloat(this.empty$mcF$sp()), (x, y) -> BoxesRunTime.boxToFloat($anonfun$combineAll$3(this, BoxesRunTime.unboxToFloat(x), BoxesRunTime.unboxToFloat(y)))));
   }

   // $FF: synthetic method
   static Monoid reverse$(final Monoid$mcF$sp $this) {
      return $this.reverse();
   }

   default Monoid reverse() {
      return this.reverse$mcF$sp();
   }

   // $FF: synthetic method
   static Monoid reverse$mcF$sp$(final Monoid$mcF$sp $this) {
      return $this.reverse$mcF$sp();
   }

   default Monoid reverse$mcF$sp() {
      return new Monoid$mcF$sp() {
         // $FF: synthetic field
         private final Monoid$mcF$sp $outer;

         public boolean isEmpty(final float a, final Eq ev) {
            return Monoid$mcF$sp.super.isEmpty(a, ev);
         }

         public boolean isEmpty$mcF$sp(final float a, final Eq ev) {
            return Monoid$mcF$sp.super.isEmpty$mcF$sp(a, ev);
         }

         public float combineAll(final IterableOnce as) {
            return Monoid$mcF$sp.super.combineAll(as);
         }

         public float combineAll$mcF$sp(final IterableOnce as) {
            return Monoid$mcF$sp.super.combineAll$mcF$sp(as);
         }

         public float repeatedCombineN(final float a, final int n) {
            return Semigroup$mcF$sp.repeatedCombineN$(this, a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Semigroup$mcF$sp.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public Semigroup intercalate(final float middle) {
            return Semigroup$mcF$sp.intercalate$(this, middle);
         }

         public Semigroup intercalate$mcF$sp(final float middle) {
            return Semigroup$mcF$sp.intercalate$mcF$sp$(this, middle);
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

         public float combine(final float a, final float b) {
            return this.combine$mcF$sp(a, b);
         }

         public float combineN(final float a, final int n) {
            return this.combineN$mcF$sp(a, n);
         }

         public Monoid reverse() {
            return this.reverse$mcF$sp();
         }

         public float empty$mcF$sp() {
            return this.$outer.empty$mcF$sp();
         }

         public float combine$mcF$sp(final float a, final float b) {
            return this.$outer.combine$mcF$sp(b, a);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return this.$outer.combineN$mcF$sp(a, n);
         }

         public Monoid reverse$mcF$sp() {
            return this.$outer;
         }

         public {
            if (Monoid$mcF$sp.this == null) {
               throw null;
            } else {
               this.$outer = Monoid$mcF$sp.this;
               Semigroup.$init$(this);
               Monoid.$init$(this);
            }
         }
      };
   }

   // $FF: synthetic method
   static float $anonfun$combineAll$3(final Monoid$mcF$sp $this, final float x, final float y) {
      return $this.combine$mcF$sp(x, y);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
