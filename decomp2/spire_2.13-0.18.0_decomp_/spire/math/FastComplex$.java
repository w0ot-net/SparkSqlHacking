package spire.math;

import scala.Tuple2;
import scala.collection.StringOps.;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichFloat;

public final class FastComplex$ {
   public static final FastComplex$ MODULE$ = new FastComplex$();
   private static final long i;
   private static final long one;
   private static final long zero;

   static {
      i = MODULE$.encode(0.0F, 1.0F);
      one = MODULE$.encode(1.0F, 0.0F);
      zero = MODULE$.encode(0.0F, 0.0F);
   }

   public final long apply(final float real, final float imag) {
      return this.encode(real, imag);
   }

   public final long apply(final double real, final double imag) {
      return this.encode((float)real, (float)imag);
   }

   public final int bits(final float n) {
      return Float.floatToIntBits(n);
   }

   public final float bits(final int n) {
      return Float.intBitsToFloat(n);
   }

   public final float real(final long d) {
      return this.bits((int)(d & -1L));
   }

   public final float imag(final long d) {
      return this.bits((int)(d >>> 32));
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

   public final long encode(final float real, final float imag) {
      return (long)this.bits(real) & 4294967295L | ((long)this.bits(imag) & 4294967295L) << 32;
   }

   public final long polar(final float magnitude, final float angle) {
      return this.encode(magnitude * (float)Math.cos((double)angle), magnitude * (float)Math.sin((double)angle));
   }

   public final Tuple2 decode(final long d) {
      return new Tuple2(BoxesRunTime.boxToFloat(this.real(d)), BoxesRunTime.boxToFloat(this.imag(d)));
   }

   public final String toRepr(final long d) {
      return .MODULE$.format$extension(scala.Predef..MODULE$.augmentString("FastComplex(%s -> %s)"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToLong(d), this.decode(d)}));
   }

   public final float abs(final long d) {
      float re = this.real(d);
      float im = this.imag(d);
      return (float)Math.sqrt((double)(re * re + im * im));
   }

   public final float angle(final long d) {
      return (float)Math.atan2((double)this.imag(d), (double)this.real(d));
   }

   public final long conjugate(final long d) {
      return this.encode(this.real(d), -this.imag(d));
   }

   public final boolean isWhole(final long d) {
      return this.real(d) % 1.0F == 0.0F && this.imag(d) % 1.0F == 0.0F;
   }

   public final int signum(final long d) {
      return (new RichFloat(scala.Predef..MODULE$.floatWrapper(this.real(d)))).compare(BoxesRunTime.boxToFloat(0.0F));
   }

   public final long complexSignum(final long d) {
      float m = this.abs(d);
      return m == 0.0F ? this.zero() : this.divide(d, this.encode(m, 0.0F));
   }

   public final long negate(final long a) {
      return this.encode(-this.real(a), -this.imag(a));
   }

   public final long add(final long a, final long b) {
      return this.encode(this.real(a) + this.real(b), this.imag(a) + this.imag(b));
   }

   public final long subtract(final long a, final long b) {
      return this.encode(this.real(a) - this.real(b), this.imag(a) - this.imag(b));
   }

   public final long multiply(final long a, final long b) {
      float re_a = this.real(a);
      float im_a = this.imag(a);
      float re_b = this.real(b);
      float im_b = this.imag(b);
      return this.encode(re_a * re_b - im_a * im_b, im_a * re_b + re_a * im_b);
   }

   public final long divide(final long a, final long b) {
      float re_a = this.real(a);
      float im_a = this.imag(a);
      float re_b = this.real(b);
      float im_b = this.imag(b);
      float abs_re_b = Math.abs(re_b);
      float abs_im_b = Math.abs(im_b);
      long var10000;
      if (abs_re_b >= abs_im_b) {
         if (abs_re_b == 0.0F) {
            throw new ArithmeticException("/0");
         }

         float ratio = im_b / re_b;
         float denom = re_b + im_b * ratio;
         var10000 = this.encode((re_a + im_a * ratio) / denom, (im_a - re_a * ratio) / denom);
      } else {
         if (abs_im_b == 0.0F) {
            throw new ArithmeticException("/0");
         }

         float ratio = re_b / im_b;
         float denom = re_b * ratio + im_b;
         var10000 = this.encode((re_a * ratio + im_a) / denom, (im_a * ratio - re_a) / denom);
      }

      return var10000;
   }

   public final long pow(final long a, final long b) {
      long var10000;
      if (b == this.zero()) {
         var10000 = this.encode(1.0F, 0.0F);
      } else if (a == this.zero()) {
         if (this.imag(b) != 0.0F || this.real(b) < 0.0F) {
            throw new Exception("raising 0 to negative/complex power");
         }

         var10000 = this.zero();
      } else if (this.imag(b) != 0.0F) {
         float im_b = this.imag(b);
         float re_b = this.real(b);
         float len = (float)(Math.pow((double)this.abs(a), (double)re_b) / package$.MODULE$.exp((double)(this.angle(a) * im_b)));
         float phase = (float)((double)(this.angle(a) * re_b) + package$.MODULE$.log((double)this.abs(a)) * (double)im_b);
         var10000 = this.polar(len, phase);
      } else {
         float len = (float)Math.pow((double)this.abs(a), (double)this.real(b));
         float phase = this.angle(a) * this.real(b);
         var10000 = this.polar(len, phase);
      }

      return var10000;
   }

   private FastComplex$() {
   }
}
