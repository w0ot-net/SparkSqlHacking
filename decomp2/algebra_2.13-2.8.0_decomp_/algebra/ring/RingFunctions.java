package algebra.ring;

import java.lang.invoke.SerializedLambda;
import scala.math.BigInt;
import scala.reflect.ScalaSignature;
import scala.runtime.RichDouble.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005mba\u0002\u0004\b!\u0003\r\t\u0001\u0004\u0005\u0006]\u0001!\ta\f\u0005\u0006g\u0001!\t\u0001\u000e\u0005\u0006?\u0002!\t\u0001\u0019\u0005\u0006q\u0002!)!\u001f\u0005\b\u0003+\u0001AQAA\f\u00055\u0011\u0016N\\4Gk:\u001cG/[8og*\u0011\u0001\"C\u0001\u0005e&twMC\u0001\u000b\u0003\u001d\tGnZ3ce\u0006\u001c\u0001!\u0006\u0002\u000e5M!\u0001A\u0004\u000b,!\ty!#D\u0001\u0011\u0015\u0005\t\u0012!B:dC2\f\u0017BA\n\u0011\u0005\u0019\te.\u001f*fMB\u0019QC\u0006\r\u000e\u0003\u001dI!aF\u0004\u0003-\u0005#G-\u001b;jm\u0016<%o\\;q\rVt7\r^5p]N\u0004\"!\u0007\u000e\r\u0001\u0011)1\u0004\u0001b\u00019\t\t!+\u0006\u0002\u001eKE\u0011a$\t\t\u0003\u001f}I!\u0001\t\t\u0003\u000f9{G\u000f[5oOB\u0019QC\t\u0013\n\u0005\r:!\u0001\u0002*j]\u001e\u0004\"!G\u0013\u0005\u000b\u0019R\"\u0019A\u0014\u0003\u0003Q\u000b\"A\b\u0015\u0011\u0005=I\u0013B\u0001\u0016\u0011\u0005\r\te.\u001f\t\u0004+1B\u0012BA\u0017\b\u0005uiU\u000f\u001c;ja2L7-\u0019;jm\u0016luN\\8jI\u001a+hn\u0019;j_:\u001c\u0018A\u0002\u0013j]&$H\u0005F\u00011!\ty\u0011'\u0003\u00023!\t!QK\\5u\u0003\u001d1'o\\7J]R,\"!\u000e\u001d\u0015\u0005YRFCA\u001cX!\tI\u0002\bB\u0005:\u0005\u0001\u0006\t\u0011!b\u0001O\t\t\u0011\t\u000b\u00049wyBUJ\u0015\t\u0003\u001fqJ!!\u0010\t\u0003\u0017M\u0004XmY5bY&TX\rZ\u0019\u0006G}\u0002%)\u0011\b\u0003\u001f\u0001K!!\u0011\t\u0002\u0007%sG/\r\u0003%\u0007\u001e\u000bbB\u0001#H\u001b\u0005)%B\u0001$\f\u0003\u0019a$o\\8u}%\t\u0011#M\u0003$\u0013*c5J\u0004\u0002\u0010\u0015&\u00111\nE\u0001\u0005\u0019>tw-\r\u0003%\u0007\u001e\u000b\u0012'B\u0012O\u001fF\u0003fBA\bP\u0013\t\u0001\u0006#A\u0003GY>\fG/\r\u0003%\u0007\u001e\u000b\u0012'B\u0012T)Z+fBA\bU\u0013\t)\u0006#\u0001\u0004E_V\u0014G.Z\u0019\u0005I\r;\u0015\u0003C\u0003Y\u0005\u0001\u000f\u0011,\u0001\u0002fmB\u0019\u0011DG\u001c\t\u000bm\u0013\u0001\u0019\u0001/\u0002\u00039\u0004\"aD/\n\u0005y\u0003\"aA%oi\u0006QaM]8n\u0005&<\u0017J\u001c;\u0016\u0005\u0005$GC\u00012q)\t\u0019g\u000e\u0005\u0002\u001aI\u0012I\u0011h\u0001Q\u0001\u0002\u0003\u0015\ra\n\u0015\u0007In2\u0007N\u001b72\u000b\rz\u0004iZ!2\t\u0011\u001au)E\u0019\u0006G%S\u0015nS\u0019\u0005I\r;\u0015#M\u0003$\u001d>[\u0007+\r\u0003%\u0007\u001e\u000b\u0012'B\u0012T)6,\u0016\u0007\u0002\u0013D\u000fFAQ\u0001W\u0002A\u0004=\u00042!\u0007\u000ed\u0011\u0015Y6\u00011\u0001r!\t\u0011XO\u0004\u0002Dg&\u0011A\u000fE\u0001\ba\u0006\u001c7.Y4f\u0013\t1xO\u0001\u0004CS\u001eLe\u000e\u001e\u0006\u0003iB\t\u0011\u0003Z3gCVdGO\u0012:p[\nKw-\u00138u+\tQX\u0010F\u0002|\u0003'!2\u0001`A\b!\tIR\u0010B\u0005:\t\u0001\u0006\t\u0011!b\u0001O!JQpO@\u0002\u0004\u0005\u001d\u00111B\u0019\u0007G}\u0002\u0015\u0011A!2\t\u0011\u001au)E\u0019\u0007G%S\u0015QA&2\t\u0011\u001au)E\u0019\u0007G9{\u0015\u0011\u0002)2\t\u0011\u001au)E\u0019\u0007GM#\u0016QB+2\t\u0011\u001au)\u0005\u0005\u00071\u0012\u0001\u001d!!\u0005\u0011\u0007eQB\u0010C\u0003\\\t\u0001\u0007\u0011/A\teK\u001a\fW\u000f\u001c;Ge>lGi\\;cY\u0016,B!!\u0007\u0002 Q!\u00111DA\u0019)\u0019\ti\"!\t\u0002(A\u0019\u0011$a\b\u0005\u000be*!\u0019A\u0014\t\u000f\u0005\rR\u0001q\u0001\u0002&\u0005)!/\u001b8h\u0003B!QCIA\u000f\u0011\u001d\tI#\u0002a\u0002\u0003W\t1!\\4B!\u0015)\u0012QFA\u000f\u0013\r\tyc\u0002\u0002\u0014\u001bVdG/\u001b9mS\u000e\fG/\u001b<f\u000fJ|W\u000f\u001d\u0005\b\u0003g)\u0001\u0019AA\u001b\u0003\u0005\t\u0007cA\b\u00028%\u0019\u0011\u0011\b\t\u0003\r\u0011{WO\u00197f\u0001"
)
public interface RingFunctions extends AdditiveGroupFunctions, MultiplicativeMonoidFunctions {
   // $FF: synthetic method
   static Object fromInt$(final RingFunctions $this, final int n, final Ring ev) {
      return $this.fromInt(n, ev);
   }

   default Object fromInt(final int n, final Ring ev) {
      return ev.fromInt(n);
   }

   // $FF: synthetic method
   static Object fromBigInt$(final RingFunctions $this, final BigInt n, final Ring ev) {
      return $this.fromBigInt(n, ev);
   }

   default Object fromBigInt(final BigInt n, final Ring ev) {
      return ev.fromBigInt(n);
   }

   // $FF: synthetic method
   static Object defaultFromBigInt$(final RingFunctions $this, final BigInt n, final Ring ev) {
      return $this.defaultFromBigInt(n, ev);
   }

   default Object defaultFromBigInt(final BigInt n, final Ring ev) {
      Object var10000;
      if (n.isValidInt()) {
         var10000 = ev.fromInt(n.toInt());
      } else {
         Object d = ev.fromInt(1073741824);
         long mask = 1073741823L;
         Object absValue = this.loop$1(this.one(ev), n.abs(), this.zero(ev), ev, mask, d);
         var10000 = n.signum() < 0 ? ev.negate(absValue) : absValue;
      }

      return var10000;
   }

   // $FF: synthetic method
   static Object defaultFromDouble$(final RingFunctions $this, final double a, final Ring ringA, final MultiplicativeGroup mgA) {
      return $this.defaultFromDouble(a, ringA, mgA);
   }

   default Object defaultFromDouble(final double a, final Ring ringA, final MultiplicativeGroup mgA) {
      Object var10000;
      if (a == (double)0.0F) {
         var10000 = ringA.zero();
      } else if (.MODULE$.isValidInt$extension(scala.Predef..MODULE$.doubleWrapper(a))) {
         var10000 = ringA.fromInt((int)a);
      } else {
         scala.Predef..MODULE$.require(!Double.isInfinite(a) && !Double.isNaN(a), () -> "Double must be representable as a fraction.");
         long bits = Double.doubleToLongBits(a);
         int expBits = (int)(bits >> 52 & 2047L);
         long mBits = bits & 4503599627370495L;
         long m = expBits > 0 ? mBits | 4503599627370496L : mBits;
         int zeros = Long.numberOfTrailingZeros(m);
         long value = m >>> zeros;
         int exp = scala.math.package..MODULE$.max(1, expBits) - 1075 + zeros;
         Object high = ringA.times(ringA.fromInt((int)(value >>> 30)), ringA.fromInt(1073741824));
         Object low = ringA.fromInt((int)(value & 1073741823L));
         Object num = ringA.plus(high, low);
         Object unsigned = exp > 0 ? ringA.times(num, ringA.pow(ringA.fromInt(2), exp)) : (exp < 0 ? mgA.div(num, ringA.pow(ringA.fromInt(2), -exp)) : num);
         var10000 = a < (double)0 ? ringA.negate(unsigned) : unsigned;
      }

      return var10000;
   }

   // $FF: synthetic method
   static double fromInt$mDc$sp$(final RingFunctions $this, final int n, final Ring ev) {
      return $this.fromInt$mDc$sp(n, ev);
   }

   default double fromInt$mDc$sp(final int n, final Ring ev) {
      return ev.fromInt$mcD$sp(n);
   }

   // $FF: synthetic method
   static float fromInt$mFc$sp$(final RingFunctions $this, final int n, final Ring ev) {
      return $this.fromInt$mFc$sp(n, ev);
   }

   default float fromInt$mFc$sp(final int n, final Ring ev) {
      return ev.fromInt$mcF$sp(n);
   }

   // $FF: synthetic method
   static int fromInt$mIc$sp$(final RingFunctions $this, final int n, final Ring ev) {
      return $this.fromInt$mIc$sp(n, ev);
   }

   default int fromInt$mIc$sp(final int n, final Ring ev) {
      return ev.fromInt$mcI$sp(n);
   }

   // $FF: synthetic method
   static long fromInt$mJc$sp$(final RingFunctions $this, final int n, final Ring ev) {
      return $this.fromInt$mJc$sp(n, ev);
   }

   default long fromInt$mJc$sp(final int n, final Ring ev) {
      return ev.fromInt$mcJ$sp(n);
   }

   // $FF: synthetic method
   static double fromBigInt$mDc$sp$(final RingFunctions $this, final BigInt n, final Ring ev) {
      return $this.fromBigInt$mDc$sp(n, ev);
   }

   default double fromBigInt$mDc$sp(final BigInt n, final Ring ev) {
      return ev.fromBigInt$mcD$sp(n);
   }

   // $FF: synthetic method
   static float fromBigInt$mFc$sp$(final RingFunctions $this, final BigInt n, final Ring ev) {
      return $this.fromBigInt$mFc$sp(n, ev);
   }

   default float fromBigInt$mFc$sp(final BigInt n, final Ring ev) {
      return ev.fromBigInt$mcF$sp(n);
   }

   // $FF: synthetic method
   static int fromBigInt$mIc$sp$(final RingFunctions $this, final BigInt n, final Ring ev) {
      return $this.fromBigInt$mIc$sp(n, ev);
   }

   default int fromBigInt$mIc$sp(final BigInt n, final Ring ev) {
      return ev.fromBigInt$mcI$sp(n);
   }

   // $FF: synthetic method
   static long fromBigInt$mJc$sp$(final RingFunctions $this, final BigInt n, final Ring ev) {
      return $this.fromBigInt$mJc$sp(n, ev);
   }

   default long fromBigInt$mJc$sp(final BigInt n, final Ring ev) {
      return ev.fromBigInt$mcJ$sp(n);
   }

   // $FF: synthetic method
   static double defaultFromBigInt$mDc$sp$(final RingFunctions $this, final BigInt n, final Ring ev) {
      return $this.defaultFromBigInt$mDc$sp(n, ev);
   }

   default double defaultFromBigInt$mDc$sp(final BigInt n, final Ring ev) {
      double var10000;
      if (n.isValidInt()) {
         var10000 = ev.fromInt$mcD$sp(n.toInt());
      } else {
         double d = ev.fromInt$mcD$sp(1073741824);
         long mask = 1073741823L;
         double absValue = this.loop$2(this.one$mDc$sp(ev), n.abs(), this.zero$mDc$sp(ev), ev, mask, d);
         var10000 = n.signum() < 0 ? ev.negate$mcD$sp(absValue) : absValue;
      }

      return var10000;
   }

   // $FF: synthetic method
   static float defaultFromBigInt$mFc$sp$(final RingFunctions $this, final BigInt n, final Ring ev) {
      return $this.defaultFromBigInt$mFc$sp(n, ev);
   }

   default float defaultFromBigInt$mFc$sp(final BigInt n, final Ring ev) {
      float var10000;
      if (n.isValidInt()) {
         var10000 = ev.fromInt$mcF$sp(n.toInt());
      } else {
         float d = ev.fromInt$mcF$sp(1073741824);
         long mask = 1073741823L;
         float absValue = this.loop$3(this.one$mFc$sp(ev), n.abs(), this.zero$mFc$sp(ev), ev, mask, d);
         var10000 = n.signum() < 0 ? ev.negate$mcF$sp(absValue) : absValue;
      }

      return var10000;
   }

   // $FF: synthetic method
   static int defaultFromBigInt$mIc$sp$(final RingFunctions $this, final BigInt n, final Ring ev) {
      return $this.defaultFromBigInt$mIc$sp(n, ev);
   }

   default int defaultFromBigInt$mIc$sp(final BigInt n, final Ring ev) {
      int var10000;
      if (n.isValidInt()) {
         var10000 = ev.fromInt$mcI$sp(n.toInt());
      } else {
         int d = ev.fromInt$mcI$sp(1073741824);
         long mask = 1073741823L;
         int absValue = this.loop$4(this.one$mIc$sp(ev), n.abs(), this.zero$mIc$sp(ev), ev, mask, d);
         var10000 = n.signum() < 0 ? ev.negate$mcI$sp(absValue) : absValue;
      }

      return var10000;
   }

   // $FF: synthetic method
   static long defaultFromBigInt$mJc$sp$(final RingFunctions $this, final BigInt n, final Ring ev) {
      return $this.defaultFromBigInt$mJc$sp(n, ev);
   }

   default long defaultFromBigInt$mJc$sp(final BigInt n, final Ring ev) {
      long var10000;
      if (n.isValidInt()) {
         var10000 = ev.fromInt$mcJ$sp(n.toInt());
      } else {
         long d = ev.fromInt$mcJ$sp(1073741824);
         long mask = 1073741823L;
         long absValue = this.loop$5(this.one$mJc$sp(ev), n.abs(), this.zero$mJc$sp(ev), ev, mask, d);
         var10000 = n.signum() < 0 ? ev.negate$mcJ$sp(absValue) : absValue;
      }

      return var10000;
   }

   private Object loop$1(final Object k, final BigInt x, final Object acc, final Ring ev$1, final long mask$1, final Object d$1) {
      while(!x.isValidInt()) {
         BigInt y = x.$greater$greater(30);
         Object r = ev$1.fromInt(x.$amp(scala.math.BigInt..MODULE$.long2bigInt(mask$1)).toInt());
         Object var10000 = ev$1.times(d$1, k);
         acc = ev$1.plus(ev$1.times(k, r), acc);
         x = y;
         k = var10000;
      }

      return ev$1.plus(ev$1.times(k, ev$1.fromInt(x.toInt())), acc);
   }

   private double loop$2(final double k, final BigInt x, final double acc, final Ring ev$2, final long mask$2, final double d$2) {
      while(!x.isValidInt()) {
         BigInt y = x.$greater$greater(30);
         double r = ev$2.fromInt$mcD$sp(x.$amp(scala.math.BigInt..MODULE$.long2bigInt(mask$2)).toInt());
         double var10000 = ev$2.times$mcD$sp(d$2, k);
         acc = ev$2.plus$mcD$sp(ev$2.times$mcD$sp(k, r), acc);
         x = y;
         k = var10000;
      }

      return ev$2.plus$mcD$sp(ev$2.times$mcD$sp(k, ev$2.fromInt$mcD$sp(x.toInt())), acc);
   }

   private float loop$3(final float k, final BigInt x, final float acc, final Ring ev$3, final long mask$3, final float d$3) {
      while(!x.isValidInt()) {
         BigInt y = x.$greater$greater(30);
         float r = ev$3.fromInt$mcF$sp(x.$amp(scala.math.BigInt..MODULE$.long2bigInt(mask$3)).toInt());
         float var10000 = ev$3.times$mcF$sp(d$3, k);
         acc = ev$3.plus$mcF$sp(ev$3.times$mcF$sp(k, r), acc);
         x = y;
         k = var10000;
      }

      return ev$3.plus$mcF$sp(ev$3.times$mcF$sp(k, ev$3.fromInt$mcF$sp(x.toInt())), acc);
   }

   private int loop$4(final int k, final BigInt x, final int acc, final Ring ev$4, final long mask$4, final int d$4) {
      while(!x.isValidInt()) {
         BigInt y = x.$greater$greater(30);
         int r = ev$4.fromInt$mcI$sp(x.$amp(scala.math.BigInt..MODULE$.long2bigInt(mask$4)).toInt());
         int var10000 = ev$4.times$mcI$sp(d$4, k);
         acc = ev$4.plus$mcI$sp(ev$4.times$mcI$sp(k, r), acc);
         x = y;
         k = var10000;
      }

      return ev$4.plus$mcI$sp(ev$4.times$mcI$sp(k, ev$4.fromInt$mcI$sp(x.toInt())), acc);
   }

   private long loop$5(final long k, final BigInt x, final long acc, final Ring ev$5, final long mask$5, final long d$5) {
      while(!x.isValidInt()) {
         BigInt y = x.$greater$greater(30);
         long r = ev$5.fromInt$mcJ$sp(x.$amp(scala.math.BigInt..MODULE$.long2bigInt(mask$5)).toInt());
         long var10000 = ev$5.times$mcJ$sp(d$5, k);
         acc = ev$5.plus$mcJ$sp(ev$5.times$mcJ$sp(k, r), acc);
         x = y;
         k = var10000;
      }

      return ev$5.plus$mcJ$sp(ev$5.times$mcJ$sp(k, ev$5.fromInt$mcJ$sp(x.toInt())), acc);
   }

   static void $init$(final RingFunctions $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
