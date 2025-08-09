package spire.math;

import scala.collection.StringOps.;
import scala.runtime.BoxesRunTime;

public final class FloatComplex$ {
   public static final FloatComplex$ MODULE$ = new FloatComplex$();
   private static final long i = 4575657221408423936L;
   private static final long one = 1065353216L;
   private static final long zero = 0L;

   public final long apply(final float real, final float imag) {
      return FastComplex$.MODULE$.encode(real, imag);
   }

   public final long apply(final double real, final double imag) {
      return FastComplex$.MODULE$.encode((float)real, (float)imag);
   }

   public long polar(final float magnitude, final float angle) {
      return FastComplex$.MODULE$.polar(magnitude, angle);
   }

   public final long i() {
      return i;
   }

   public final long one() {
      return one;
   }

   public final long zero() {
      return zero;
   }

   public final String toString$extension(final long $this) {
      return .MODULE$.format$extension(scala.Predef..MODULE$.augmentString("(%s+%si)"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToFloat(this.real$extension($this)), BoxesRunTime.boxToFloat(this.imag$extension($this))}));
   }

   public final float real$extension(final long $this) {
      return FastComplex$.MODULE$.real($this);
   }

   public final float imag$extension(final long $this) {
      return FastComplex$.MODULE$.imag($this);
   }

   public final String repr$extension(final long $this) {
      return .MODULE$.format$extension(scala.Predef..MODULE$.augmentString("FloatComplex(%s, %s)"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToFloat(this.real$extension($this)), BoxesRunTime.boxToFloat(this.imag$extension($this))}));
   }

   public final float abs$extension(final long $this) {
      return FastComplex$.MODULE$.abs($this);
   }

   public final float angle$extension(final long $this) {
      return FastComplex$.MODULE$.angle($this);
   }

   public final long conjugate$extension(final long $this) {
      return FastComplex$.MODULE$.conjugate($this);
   }

   public final boolean isWhole$extension(final long $this) {
      return FastComplex$.MODULE$.isWhole($this);
   }

   public final int signum$extension(final long $this) {
      return FastComplex$.MODULE$.signum($this);
   }

   public final long complexSignum$extension(final long $this) {
      return FastComplex$.MODULE$.complexSignum($this);
   }

   public final long negate$extension(final long $this) {
      return FastComplex$.MODULE$.negate($this);
   }

   public final long $plus$extension(final long $this, final long b) {
      return FastComplex$.MODULE$.add($this, b);
   }

   public final long $minus$extension(final long $this, final long b) {
      return FastComplex$.MODULE$.subtract($this, b);
   }

   public final long $times$extension(final long $this, final long b) {
      return FastComplex$.MODULE$.multiply($this, b);
   }

   public final long $div$extension(final long $this, final long b) {
      return FastComplex$.MODULE$.divide($this, b);
   }

   public final long pow$extension(final long $this, final long b) {
      return FastComplex$.MODULE$.pow($this, b);
   }

   public final long $times$times$extension(final long $this, final long b) {
      return this.pow$extension($this, b);
   }

   public final long pow$extension(final long $this, final int b) {
      return FastComplex$.MODULE$.pow($this, FastComplex$.MODULE$.apply((float)b, 0.0F));
   }

   public final long $times$times$extension(final long $this, final int b) {
      return this.pow$extension($this, b);
   }

   public final int hashCode$extension(final long $this) {
      return Long.hashCode($this);
   }

   public final boolean equals$extension(final long $this, final Object x$1) {
      boolean var4;
      if (x$1 instanceof FloatComplex) {
         var4 = true;
      } else {
         var4 = false;
      }

      boolean var10000;
      if (var4) {
         long var6 = ((FloatComplex)x$1).u();
         if ($this == var6) {
            var10000 = true;
            return var10000;
         }
      }

      var10000 = false;
      return var10000;
   }

   private FloatComplex$() {
   }
}
