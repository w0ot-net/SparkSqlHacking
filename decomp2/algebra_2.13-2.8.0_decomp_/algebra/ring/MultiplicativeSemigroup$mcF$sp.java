package algebra.ring;

import cats.kernel.Semigroup;
import cats.kernel.Semigroup.mcF.sp;
import scala.Option;
import scala.collection.IterableOnce;
import scala.collection.StringOps.;
import scala.runtime.BoxesRunTime;

public interface MultiplicativeSemigroup$mcF$sp extends MultiplicativeSemigroup {
   // $FF: synthetic method
   static Semigroup multiplicative$(final MultiplicativeSemigroup$mcF$sp $this) {
      return $this.multiplicative();
   }

   default Semigroup multiplicative() {
      return this.multiplicative$mcF$sp();
   }

   // $FF: synthetic method
   static Semigroup multiplicative$mcF$sp$(final MultiplicativeSemigroup$mcF$sp $this) {
      return $this.multiplicative$mcF$sp();
   }

   default Semigroup multiplicative$mcF$sp() {
      return new Semigroup.mcF.sp() {
         // $FF: synthetic field
         private final MultiplicativeSemigroup$mcF$sp $outer;

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

         public Option combineAllOption(final IterableOnce as) {
            return Semigroup.combineAllOption$(this, as);
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

         public float combine$mcF$sp(final float x, final float y) {
            return this.$outer.times$mcF$sp(x, y);
         }

         public {
            if (MultiplicativeSemigroup$mcF$sp.this == null) {
               throw null;
            } else {
               this.$outer = MultiplicativeSemigroup$mcF$sp.this;
               Semigroup.$init$(this);
            }
         }
      };
   }

   // $FF: synthetic method
   static float pow$(final MultiplicativeSemigroup$mcF$sp $this, final float a, final int n) {
      return $this.pow(a, n);
   }

   default float pow(final float a, final int n) {
      return this.pow$mcF$sp(a, n);
   }

   // $FF: synthetic method
   static float pow$mcF$sp$(final MultiplicativeSemigroup$mcF$sp $this, final float a, final int n) {
      return $this.pow$mcF$sp(a, n);
   }

   default float pow$mcF$sp(final float a, final int n) {
      if (n > 0) {
         return this.positivePow$mcF$sp(a, n);
      } else {
         throw new IllegalArgumentException(.MODULE$.format$extension(scala.Predef..MODULE$.augmentString("Illegal non-positive exponent to pow: %s"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(n)})));
      }
   }

   // $FF: synthetic method
   static float positivePow$(final MultiplicativeSemigroup$mcF$sp $this, final float a, final int n) {
      return $this.positivePow(a, n);
   }

   default float positivePow(final float a, final int n) {
      return this.positivePow$mcF$sp(a, n);
   }

   // $FF: synthetic method
   static float positivePow$mcF$sp$(final MultiplicativeSemigroup$mcF$sp $this, final float a, final int n) {
      return $this.positivePow$mcF$sp(a, n);
   }

   default float positivePow$mcF$sp(final float a, final int n) {
      return n == 1 ? a : this.loop$3(a, n - 1, a);
   }

   private float loop$3(final float b, final int k, final float extra) {
      while(k != 1) {
         float x = (k & 1) == 1 ? this.times$mcF$sp(b, extra) : extra;
         float var10000 = this.times$mcF$sp(b, b);
         int var10001 = k >>> 1;
         extra = x;
         k = var10001;
         b = var10000;
      }

      return this.times$mcF$sp(b, extra);
   }
}
