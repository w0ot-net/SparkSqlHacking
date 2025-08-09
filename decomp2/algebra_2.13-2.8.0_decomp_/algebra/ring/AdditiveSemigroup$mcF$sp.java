package algebra.ring;

import cats.kernel.Semigroup;
import cats.kernel.Semigroup.mcF.sp;
import scala.Option;
import scala.collection.IterableOnce;
import scala.collection.StringOps.;
import scala.runtime.BoxesRunTime;

public interface AdditiveSemigroup$mcF$sp extends AdditiveSemigroup {
   // $FF: synthetic method
   static Semigroup additive$(final AdditiveSemigroup$mcF$sp $this) {
      return $this.additive();
   }

   default Semigroup additive() {
      return this.additive$mcF$sp();
   }

   // $FF: synthetic method
   static Semigroup additive$mcF$sp$(final AdditiveSemigroup$mcF$sp $this) {
      return $this.additive$mcF$sp();
   }

   default Semigroup additive$mcF$sp() {
      return new Semigroup.mcF.sp() {
         // $FF: synthetic field
         private final AdditiveSemigroup$mcF$sp $outer;

         public float combineN(final float a, final int n) {
            return sp.combineN$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return sp.combineN$mcF$sp$(this, a, n);
         }

         public float repeatedCombineN(final float a, final int n) {
            return sp.repeatedCombineN$(this, a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return sp.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public Semigroup reverse() {
            return sp.reverse$(this);
         }

         public Semigroup reverse$mcF$sp() {
            return sp.reverse$mcF$sp$(this);
         }

         public Semigroup intercalate(final float middle) {
            return sp.intercalate$(this, middle);
         }

         public Semigroup intercalate$mcF$sp(final float middle) {
            return sp.intercalate$mcF$sp$(this, middle);
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

         public double combineN$mcD$sp(final double a, final int n) {
            return Semigroup.combineN$mcD$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Semigroup.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Semigroup.combineN$mcJ$sp$(this, a, n);
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

         public Semigroup reverse$mcD$sp() {
            return Semigroup.reverse$mcD$sp$(this);
         }

         public Semigroup reverse$mcI$sp() {
            return Semigroup.reverse$mcI$sp$(this);
         }

         public Semigroup reverse$mcJ$sp() {
            return Semigroup.reverse$mcJ$sp$(this);
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

         public float combine(final float x, final float y) {
            return this.combine$mcF$sp(x, y);
         }

         public Option combineAllOption(final IterableOnce as) {
            return this.$outer.trySum(as);
         }

         public float combine$mcF$sp(final float x, final float y) {
            return this.$outer.plus$mcF$sp(x, y);
         }

         public {
            if (AdditiveSemigroup$mcF$sp.this == null) {
               throw null;
            } else {
               this.$outer = AdditiveSemigroup$mcF$sp.this;
               Semigroup.$init$(this);
            }
         }
      };
   }

   // $FF: synthetic method
   static float sumN$(final AdditiveSemigroup$mcF$sp $this, final float a, final int n) {
      return $this.sumN(a, n);
   }

   default float sumN(final float a, final int n) {
      return this.sumN$mcF$sp(a, n);
   }

   // $FF: synthetic method
   static float sumN$mcF$sp$(final AdditiveSemigroup$mcF$sp $this, final float a, final int n) {
      return $this.sumN$mcF$sp(a, n);
   }

   default float sumN$mcF$sp(final float a, final int n) {
      if (n > 0) {
         return this.positiveSumN$mcF$sp(a, n);
      } else {
         throw new IllegalArgumentException(.MODULE$.format$extension(scala.Predef..MODULE$.augmentString("Illegal non-positive exponent to sumN: %s"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(n)})));
      }
   }

   // $FF: synthetic method
   static float positiveSumN$(final AdditiveSemigroup$mcF$sp $this, final float a, final int n) {
      return $this.positiveSumN(a, n);
   }

   default float positiveSumN(final float a, final int n) {
      return this.positiveSumN$mcF$sp(a, n);
   }

   // $FF: synthetic method
   static float positiveSumN$mcF$sp$(final AdditiveSemigroup$mcF$sp $this, final float a, final int n) {
      return $this.positiveSumN$mcF$sp(a, n);
   }

   default float positiveSumN$mcF$sp(final float a, final int n) {
      return n == 1 ? a : this.loop$3(a, n - 1, a);
   }

   private float loop$3(final float b, final int k, final float extra) {
      while(k != 1) {
         float x = (k & 1) == 1 ? this.plus$mcF$sp(b, extra) : extra;
         float var10000 = this.plus$mcF$sp(b, b);
         int var10001 = k >>> 1;
         extra = x;
         k = var10001;
         b = var10000;
      }

      return this.plus$mcF$sp(b, extra);
   }
}
