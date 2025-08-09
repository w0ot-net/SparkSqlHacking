package algebra.ring;

import cats.kernel.Semigroup;
import cats.kernel.Semigroup.mcI.sp;
import scala.Option;
import scala.collection.IterableOnce;
import scala.collection.StringOps.;
import scala.runtime.BoxesRunTime;

public interface AdditiveSemigroup$mcI$sp extends AdditiveSemigroup {
   // $FF: synthetic method
   static Semigroup additive$(final AdditiveSemigroup$mcI$sp $this) {
      return $this.additive();
   }

   default Semigroup additive() {
      return this.additive$mcI$sp();
   }

   // $FF: synthetic method
   static Semigroup additive$mcI$sp$(final AdditiveSemigroup$mcI$sp $this) {
      return $this.additive$mcI$sp();
   }

   default Semigroup additive$mcI$sp() {
      return new Semigroup.mcI.sp() {
         // $FF: synthetic field
         private final AdditiveSemigroup$mcI$sp $outer;

         public int combineN(final int a, final int n) {
            return sp.combineN$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return sp.combineN$mcI$sp$(this, a, n);
         }

         public int repeatedCombineN(final int a, final int n) {
            return sp.repeatedCombineN$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return sp.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public Semigroup reverse() {
            return sp.reverse$(this);
         }

         public Semigroup reverse$mcI$sp() {
            return sp.reverse$mcI$sp$(this);
         }

         public Semigroup intercalate(final int middle) {
            return sp.intercalate$(this, middle);
         }

         public Semigroup intercalate$mcI$sp(final int middle) {
            return sp.intercalate$mcI$sp$(this, middle);
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

         public double combineN$mcD$sp(final double a, final int n) {
            return Semigroup.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Semigroup.combineN$mcF$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Semigroup.combineN$mcJ$sp$(this, a, n);
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

         public Semigroup reverse$mcD$sp() {
            return Semigroup.reverse$mcD$sp$(this);
         }

         public Semigroup reverse$mcF$sp() {
            return Semigroup.reverse$mcF$sp$(this);
         }

         public Semigroup reverse$mcJ$sp() {
            return Semigroup.reverse$mcJ$sp$(this);
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

         public int combine(final int x, final int y) {
            return this.combine$mcI$sp(x, y);
         }

         public Option combineAllOption(final IterableOnce as) {
            return this.$outer.trySum(as);
         }

         public int combine$mcI$sp(final int x, final int y) {
            return this.$outer.plus$mcI$sp(x, y);
         }

         public {
            if (AdditiveSemigroup$mcI$sp.this == null) {
               throw null;
            } else {
               this.$outer = AdditiveSemigroup$mcI$sp.this;
               Semigroup.$init$(this);
            }
         }
      };
   }

   // $FF: synthetic method
   static int sumN$(final AdditiveSemigroup$mcI$sp $this, final int a, final int n) {
      return $this.sumN(a, n);
   }

   default int sumN(final int a, final int n) {
      return this.sumN$mcI$sp(a, n);
   }

   // $FF: synthetic method
   static int sumN$mcI$sp$(final AdditiveSemigroup$mcI$sp $this, final int a, final int n) {
      return $this.sumN$mcI$sp(a, n);
   }

   default int sumN$mcI$sp(final int a, final int n) {
      if (n > 0) {
         return this.positiveSumN$mcI$sp(a, n);
      } else {
         throw new IllegalArgumentException(.MODULE$.format$extension(scala.Predef..MODULE$.augmentString("Illegal non-positive exponent to sumN: %s"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(n)})));
      }
   }

   // $FF: synthetic method
   static int positiveSumN$(final AdditiveSemigroup$mcI$sp $this, final int a, final int n) {
      return $this.positiveSumN(a, n);
   }

   default int positiveSumN(final int a, final int n) {
      return this.positiveSumN$mcI$sp(a, n);
   }

   // $FF: synthetic method
   static int positiveSumN$mcI$sp$(final AdditiveSemigroup$mcI$sp $this, final int a, final int n) {
      return $this.positiveSumN$mcI$sp(a, n);
   }

   default int positiveSumN$mcI$sp(final int a, final int n) {
      return n == 1 ? a : this.loop$4(a, n - 1, a);
   }

   private int loop$4(final int b, final int k, final int extra) {
      while(k != 1) {
         int x = (k & 1) == 1 ? this.plus$mcI$sp(b, extra) : extra;
         int var10000 = this.plus$mcI$sp(b, b);
         int var10001 = k >>> 1;
         extra = x;
         k = var10001;
         b = var10000;
      }

      return this.plus$mcI$sp(b, extra);
   }
}
