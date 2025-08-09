package algebra.ring;

import cats.kernel.Semigroup;
import cats.kernel.Semigroup.mcD.sp;
import scala.Option;
import scala.collection.IterableOnce;
import scala.collection.StringOps.;
import scala.runtime.BoxesRunTime;

public interface AdditiveSemigroup$mcD$sp extends AdditiveSemigroup {
   // $FF: synthetic method
   static Semigroup additive$(final AdditiveSemigroup$mcD$sp $this) {
      return $this.additive();
   }

   default Semigroup additive() {
      return this.additive$mcD$sp();
   }

   // $FF: synthetic method
   static Semigroup additive$mcD$sp$(final AdditiveSemigroup$mcD$sp $this) {
      return $this.additive$mcD$sp();
   }

   default Semigroup additive$mcD$sp() {
      return new Semigroup.mcD.sp() {
         // $FF: synthetic field
         private final AdditiveSemigroup$mcD$sp $outer;

         public double combineN(final double a, final int n) {
            return sp.combineN$(this, a, n);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return sp.combineN$mcD$sp$(this, a, n);
         }

         public double repeatedCombineN(final double a, final int n) {
            return sp.repeatedCombineN$(this, a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return sp.repeatedCombineN$mcD$sp$(this, a, n);
         }

         public Semigroup reverse() {
            return sp.reverse$(this);
         }

         public Semigroup reverse$mcD$sp() {
            return sp.reverse$mcD$sp$(this);
         }

         public Semigroup intercalate(final double middle) {
            return sp.intercalate$(this, middle);
         }

         public Semigroup intercalate$mcD$sp(final double middle) {
            return sp.intercalate$mcD$sp$(this, middle);
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

         public float combineN$mcF$sp(final float a, final int n) {
            return Semigroup.combineN$mcF$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Semigroup.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Semigroup.combineN$mcJ$sp$(this, a, n);
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

         public Semigroup reverse$mcF$sp() {
            return Semigroup.reverse$mcF$sp$(this);
         }

         public Semigroup reverse$mcI$sp() {
            return Semigroup.reverse$mcI$sp$(this);
         }

         public Semigroup reverse$mcJ$sp() {
            return Semigroup.reverse$mcJ$sp$(this);
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

         public double combine(final double x, final double y) {
            return this.combine$mcD$sp(x, y);
         }

         public Option combineAllOption(final IterableOnce as) {
            return this.$outer.trySum(as);
         }

         public double combine$mcD$sp(final double x, final double y) {
            return this.$outer.plus$mcD$sp(x, y);
         }

         public {
            if (AdditiveSemigroup$mcD$sp.this == null) {
               throw null;
            } else {
               this.$outer = AdditiveSemigroup$mcD$sp.this;
               Semigroup.$init$(this);
            }
         }
      };
   }

   // $FF: synthetic method
   static double sumN$(final AdditiveSemigroup$mcD$sp $this, final double a, final int n) {
      return $this.sumN(a, n);
   }

   default double sumN(final double a, final int n) {
      return this.sumN$mcD$sp(a, n);
   }

   // $FF: synthetic method
   static double sumN$mcD$sp$(final AdditiveSemigroup$mcD$sp $this, final double a, final int n) {
      return $this.sumN$mcD$sp(a, n);
   }

   default double sumN$mcD$sp(final double a, final int n) {
      if (n > 0) {
         return this.positiveSumN$mcD$sp(a, n);
      } else {
         throw new IllegalArgumentException(.MODULE$.format$extension(scala.Predef..MODULE$.augmentString("Illegal non-positive exponent to sumN: %s"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(n)})));
      }
   }

   // $FF: synthetic method
   static double positiveSumN$(final AdditiveSemigroup$mcD$sp $this, final double a, final int n) {
      return $this.positiveSumN(a, n);
   }

   default double positiveSumN(final double a, final int n) {
      return this.positiveSumN$mcD$sp(a, n);
   }

   // $FF: synthetic method
   static double positiveSumN$mcD$sp$(final AdditiveSemigroup$mcD$sp $this, final double a, final int n) {
      return $this.positiveSumN$mcD$sp(a, n);
   }

   default double positiveSumN$mcD$sp(final double a, final int n) {
      return n == 1 ? a : this.loop$2(a, n - 1, a);
   }

   private double loop$2(final double b, final int k, final double extra) {
      while(k != 1) {
         double x = (k & 1) == 1 ? this.plus$mcD$sp(b, extra) : extra;
         double var10000 = this.plus$mcD$sp(b, b);
         int var10001 = k >>> 1;
         extra = x;
         k = var10001;
         b = var10000;
      }

      return this.plus$mcD$sp(b, extra);
   }
}
