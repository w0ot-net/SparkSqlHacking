package algebra.ring;

import cats.kernel.Semigroup;
import cats.kernel.Semigroup.mcJ.sp;
import scala.Option;
import scala.collection.IterableOnce;
import scala.collection.StringOps.;
import scala.runtime.BoxesRunTime;

public interface MultiplicativeSemigroup$mcJ$sp extends MultiplicativeSemigroup {
   // $FF: synthetic method
   static Semigroup multiplicative$(final MultiplicativeSemigroup$mcJ$sp $this) {
      return $this.multiplicative();
   }

   default Semigroup multiplicative() {
      return this.multiplicative$mcJ$sp();
   }

   // $FF: synthetic method
   static Semigroup multiplicative$mcJ$sp$(final MultiplicativeSemigroup$mcJ$sp $this) {
      return $this.multiplicative$mcJ$sp();
   }

   default Semigroup multiplicative$mcJ$sp() {
      return new Semigroup.mcJ.sp() {
         // $FF: synthetic field
         private final MultiplicativeSemigroup$mcJ$sp $outer;

         public long combineN(final long a, final int n) {
            return sp.combineN$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return sp.combineN$mcJ$sp$(this, a, n);
         }

         public long repeatedCombineN(final long a, final int n) {
            return sp.repeatedCombineN$(this, a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return sp.repeatedCombineN$mcJ$sp$(this, a, n);
         }

         public Semigroup reverse() {
            return sp.reverse$(this);
         }

         public Semigroup reverse$mcJ$sp() {
            return sp.reverse$mcJ$sp$(this);
         }

         public Semigroup intercalate(final long middle) {
            return sp.intercalate$(this, middle);
         }

         public Semigroup intercalate$mcJ$sp(final long middle) {
            return sp.intercalate$mcJ$sp$(this, middle);
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

         public double combineN$mcD$sp(final double a, final int n) {
            return Semigroup.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Semigroup.combineN$mcF$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Semigroup.combineN$mcI$sp$(this, a, n);
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

         public Option combineAllOption(final IterableOnce as) {
            return Semigroup.combineAllOption$(this, as);
         }

         public Semigroup reverse$mcD$sp() {
            return Semigroup.reverse$mcD$sp$(this);
         }

         public Semigroup reverse$mcF$sp() {
            return Semigroup.reverse$mcF$sp$(this);
         }

         public Semigroup reverse$mcI$sp() {
            return Semigroup.reverse$mcI$sp$(this);
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

         public long combine(final long x, final long y) {
            return this.combine$mcJ$sp(x, y);
         }

         public long combine$mcJ$sp(final long x, final long y) {
            return this.$outer.times$mcJ$sp(x, y);
         }

         public {
            if (MultiplicativeSemigroup$mcJ$sp.this == null) {
               throw null;
            } else {
               this.$outer = MultiplicativeSemigroup$mcJ$sp.this;
               Semigroup.$init$(this);
            }
         }
      };
   }

   // $FF: synthetic method
   static long pow$(final MultiplicativeSemigroup$mcJ$sp $this, final long a, final int n) {
      return $this.pow(a, n);
   }

   default long pow(final long a, final int n) {
      return this.pow$mcJ$sp(a, n);
   }

   // $FF: synthetic method
   static long pow$mcJ$sp$(final MultiplicativeSemigroup$mcJ$sp $this, final long a, final int n) {
      return $this.pow$mcJ$sp(a, n);
   }

   default long pow$mcJ$sp(final long a, final int n) {
      if (n > 0) {
         return this.positivePow$mcJ$sp(a, n);
      } else {
         throw new IllegalArgumentException(.MODULE$.format$extension(scala.Predef..MODULE$.augmentString("Illegal non-positive exponent to pow: %s"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(n)})));
      }
   }

   // $FF: synthetic method
   static long positivePow$(final MultiplicativeSemigroup$mcJ$sp $this, final long a, final int n) {
      return $this.positivePow(a, n);
   }

   default long positivePow(final long a, final int n) {
      return this.positivePow$mcJ$sp(a, n);
   }

   // $FF: synthetic method
   static long positivePow$mcJ$sp$(final MultiplicativeSemigroup$mcJ$sp $this, final long a, final int n) {
      return $this.positivePow$mcJ$sp(a, n);
   }

   default long positivePow$mcJ$sp(final long a, final int n) {
      return n == 1 ? a : this.loop$5(a, n - 1, a);
   }

   private long loop$5(final long b, final int k, final long extra) {
      while(k != 1) {
         long x = (k & 1) == 1 ? this.times$mcJ$sp(b, extra) : extra;
         long var10000 = this.times$mcJ$sp(b, b);
         int var10001 = k >>> 1;
         extra = x;
         k = var10001;
         b = var10000;
      }

      return this.times$mcJ$sp(b, extra);
   }
}
