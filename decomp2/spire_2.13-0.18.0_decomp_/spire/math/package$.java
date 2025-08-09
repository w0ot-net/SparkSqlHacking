package spire.math;

import algebra.ring.Field;
import algebra.ring.GCDRing;
import algebra.ring.Signed;
import cats.kernel.Eq;
import cats.kernel.Order;
import java.lang.invoke.SerializedLambda;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import scala.Function1;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.IterableOps;
import scala.collection.immutable.LazyList;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.math.BigDecimal;
import scala.math.BigInt;
import scala.math.ScalaNumericConversions;
import scala.math.BigDecimal.RoundingMode.;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;
import scala.runtime.java8.JFunction2;
import spire.implicits$;
import spire.algebra.IsReal;
import spire.algebra.NRoot;
import spire.algebra.Trig;
import spire.syntax.LiteralIntAdditiveSemigroupOps$;

public final class package$ {
   public static final package$ MODULE$ = new package$();
   private static final int radix = 1000000000;

   public final byte abs(final byte n) {
      return (byte)Math.abs(n);
   }

   public final short abs(final short n) {
      return (short)Math.abs(n);
   }

   public final int abs(final int n) {
      return Math.abs(n);
   }

   public final long abs(final long n) {
      return Math.abs(n);
   }

   public final float abs(final float n) {
      return Math.abs(n);
   }

   public final double abs(final double n) {
      return Math.abs(n);
   }

   public final Object abs(final Object a, final Signed ev) {
      return ev.abs(a);
   }

   public final float ceil(final float n) {
      return (float)Math.ceil((double)n);
   }

   public final double ceil(final double n) {
      return Math.ceil(n);
   }

   public final BigDecimal ceil(final BigDecimal n) {
      return n.setScale(0, .MODULE$.CEILING());
   }

   public final Object ceil(final Object a, final IsReal ev) {
      return ev.ceil(a);
   }

   public BigInt choose(final long n, final long k) {
      if (n >= 0L && k >= 0L) {
         if (k != 0L && k != n) {
            if (k > n) {
               return scala.package..MODULE$.BigInt().apply(0);
            } else if (n - k > k) {
               return this.choose(n, n - k);
            } else {
               return (n - k & 1L) == 1L ? this.loop$1(k + 1L, n - 1L, scala.package..MODULE$.BigInt().apply(n)).$div(this.fact(n - k)) : this.loop$1(k + 1L, n, scala.package..MODULE$.BigInt().apply(1)).$div(this.fact(n - k));
            }
         } else {
            return scala.package..MODULE$.BigInt().apply(1);
         }
      } else {
         throw new IllegalArgumentException((new StringBuilder(6)).append("n=").append(n).append(", k=").append(k).toString());
      }
   }

   public BigInt fact(final long n) {
      if (n < 0L) {
         throw new IllegalArgumentException(Long.toString(n));
      } else {
         return n == 0L ? scala.package..MODULE$.BigInt().apply(1) : ((n & 1L) == 1L ? this.loop$2(1L, n - 1L, scala.package..MODULE$.BigInt().apply(n)) : this.loop$2(2L, n - 1L, scala.package..MODULE$.BigInt().apply(n)));
      }
   }

   public BigInt fib(final long n) {
      if (n < 0L) {
         throw new IllegalArgumentException(Long.toString(n));
      } else {
         int i;
         for(i = 63; (n >>> i & 1L) == 0L && i >= 0; --i) {
         }

         return this.loop$3(scala.package..MODULE$.BigInt().apply(1), scala.package..MODULE$.BigInt().apply(0), i, n);
      }
   }

   public final float floor(final float n) {
      return (float)Math.floor((double)n);
   }

   public final double floor(final double n) {
      return Math.floor(n);
   }

   public final BigDecimal floor(final BigDecimal n) {
      return n.setScale(0, .MODULE$.FLOOR());
   }

   public final Object floor(final Object a, final IsReal ev) {
      return ev.floor(a);
   }

   public final float round(final float a) {
      return Math.abs(a) >= 1.6777216E7F ? a : (float)Math.round(a);
   }

   public final double round(final double a) {
      return Math.abs(a) >= (double)4.5035996E15F ? a : (double)Math.round(a);
   }

   public final BigDecimal round(final BigDecimal a) {
      return a.setScale(0, .MODULE$.HALF_UP());
   }

   public final Object round(final Object a, final IsReal ev) {
      return ev.round(a);
   }

   public final double exp(final double n) {
      return Math.exp(n);
   }

   public final BigDecimal exp(final int k, final int precision) {
      MathContext mc = new MathContext(precision + 1, RoundingMode.HALF_UP);
      int i = 2;
      BigInt num = scala.package..MODULE$.BigInt().apply(2);
      BigInt denom = scala.package..MODULE$.BigInt().apply(1);

      for(BigInt limit = scala.package..MODULE$.BigInt().apply(10).pow(precision); denom.$less(limit); ++i) {
         denom = denom.$times(scala.math.BigInt..MODULE$.int2bigInt(i));
         num = num.$times(scala.math.BigInt..MODULE$.int2bigInt(i)).$plus(scala.package..MODULE$.BigInt().apply(1));
      }

      BigDecimal sum = scala.package..MODULE$.BigDecimal().apply(num, mc).$div(scala.package..MODULE$.BigDecimal().apply(denom, mc));
      return sum.setScale(precision - sum.precision() + sum.scale(), .MODULE$.FLOOR()).pow(k);
   }

   public final BigDecimal exp(final BigDecimal k) {
      if (k.signum() == 0) {
         return scala.package..MODULE$.BigDecimal().apply(1);
      } else if (k.signum() == -1) {
         return scala.package..MODULE$.BigDecimal().apply(1).$div(this.exp(k.unary_$minus()));
      } else {
         BigDecimal whole = k.setScale(0, .MODULE$.FLOOR());
         if (whole.signum() > 1) {
            BigDecimal part = this.exp(scala.package..MODULE$.BigDecimal().apply(1).$plus(k.$minus(whole).$div(whole)));
            return this.power$1(scala.package..MODULE$.BigDecimal().apply(1), part, whole.toBigInt());
         } else {
            BigDecimal r = this.doit$1(k.mc().getPrecision() + 3, 1000, k);
            return new BigDecimal(r.bigDecimal(), k.mc());
         }
      }
   }

   public final Object exp(final Object a, final Trig t) {
      return t.exp(a);
   }

   public final double log(final double n) {
      return Math.log(n);
   }

   public final double log(final double n, final int base) {
      return Math.log(n) / Math.log((double)base);
   }

   public final BigDecimal log(final BigDecimal n) {
      int scale = n.mc().getPrecision();
      if (n.signum() < 1) {
         throw new IllegalArgumentException("argument <= 0");
      } else {
         Tuple2 var5 = this.rescale$1(n, 0);
         if (var5 != null) {
            BigDecimal x = (BigDecimal)var5._1();
            int i = var5._2$mcI$sp();
            Tuple2 var2 = new Tuple2(x, BoxesRunTime.boxToInteger(i));
            BigDecimal x = (BigDecimal)var2._1();
            int i = var2._2$mcI$sp();
            return this.ln$1(x, scale).$times(scala.package..MODULE$.BigDecimal().apply(2).pow(i)).setScale(scale, .MODULE$.HALF_UP());
         } else {
            throw new MatchError(var5);
         }
      }
   }

   public BigDecimal log(final BigDecimal n, final int base) {
      return this.log(n).$div(this.log(scala.package..MODULE$.BigDecimal().apply(base)));
   }

   public final Object log(final Object a, final Trig t) {
      return t.log(a);
   }

   public final Object log(final Object a, final int base, final Field f, final Trig t) {
      return f.div(t.log(a), t.log(f.fromInt(base)));
   }

   public final BigDecimal pow(final BigDecimal base, final BigDecimal exponent) {
      return exponent.abs().$less$eq(scala.math.BigDecimal..MODULE$.int2bigDecimal(99999999)) && exponent.isWhole() ? base.pow(exponent.toInt()) : this.exp(this.log(base).$times(exponent));
   }

   public final BigInt pow(final BigInt base, final BigInt ex) {
      BigInt var10000;
      if (ex.signum() < 0) {
         if (base.signum() == 0) {
            throw new ArithmeticException("zero can't be raised to negative power");
         }

         var10000 = BoxesRunTime.equalsNumObject(base, BoxesRunTime.boxToInteger(1)) ? base : (BoxesRunTime.equalsNumObject(base, BoxesRunTime.boxToInteger(-1)) ? (ex.testBit(0) ? scala.package..MODULE$.BigInt().apply(1) : base) : scala.package..MODULE$.BigInt().apply(0));
      } else {
         var10000 = ex.isValidInt() ? base.pow(ex.toInt()) : this.bigIntPow$1(scala.package..MODULE$.BigInt().apply(1), base, ex);
      }

      return var10000;
   }

   public final long pow(final long base, final long exponent) {
      long var10000;
      if (exponent < 0L) {
         if (base == 0L) {
            throw new ArithmeticException("zero can't be raised to negative power");
         }

         var10000 = base == 1L ? 1L : (base == -1L ? ((exponent & 1L) == 0L ? -1L : 1L) : 0L);
      } else {
         var10000 = this.longPow$1(1L, base, exponent);
      }

      return var10000;
   }

   public final double pow(final double base, final double exponent) {
      return Math.pow(base, exponent);
   }

   public final long gcd(final long _x, final long _y) {
      if (_x == 0L) {
         return Math.abs(_y);
      } else if (_x == 1L) {
         return 1L;
      } else if (_y == 0L) {
         return Math.abs(_x);
      } else if (_y == 1L) {
         return 1L;
      } else {
         int xz = Long.numberOfTrailingZeros(_x);
         long x = Math.abs(_x >> xz);
         int yz = Long.numberOfTrailingZeros(_y);
         long y = Math.abs(_y >> yz);

         while(x != y) {
            if (x > y) {
               x -= y;
               x >>= Long.numberOfTrailingZeros(x);
            } else {
               y -= x;
               y >>= Long.numberOfTrailingZeros(y);
            }
         }

         return xz < yz ? x << xz : x << yz;
      }
   }

   public final BigInt gcd(final BigInt a, final BigInt b) {
      return a.gcd(b);
   }

   public final Object gcd(final Object x, final Object y, final Eq evidence$1, final GCDRing ev) {
      return ev.gcd(x, y, evidence$1);
   }

   public final Object gcd(final Seq xs, final Eq evidence$2, final GCDRing ev) {
      return xs.reduceLeft((a, b) -> ev.gcd(a, b, evidence$2));
   }

   public final Object gcd(final Object x, final Object y, final Object z, final Seq rest, final Eq evidence$3, final GCDRing ev) {
      return rest.isEmpty() ? ev.gcd(ev.gcd(x, y, evidence$3), z, evidence$3) : ev.gcd(ev.gcd(ev.gcd(x, y, evidence$3), z, evidence$3), this.gcd(rest, evidence$3, ev), evidence$3);
   }

   public final long lcm(final long x, final long y) {
      return x != 0L && y != 0L ? x / this.gcd(x, y) * y : 0L;
   }

   public final BigInt lcm(final BigInt a, final BigInt b) {
      return a.signum() != 0 && b.signum() != 0 ? a.$div(a.gcd(b)).$times(b) : scala.math.BigInt..MODULE$.int2bigInt(0);
   }

   public final Object lcm(final Object x, final Object y, final Eq evidence$4, final GCDRing ev) {
      return ev.lcm(x, y, evidence$4);
   }

   public Tuple2 equotmod(final byte a, final byte b) {
      int qt = a / b;
      int rt = a % b;
      return rt >= 0 ? new Tuple2(BoxesRunTime.boxToByte((byte)qt), BoxesRunTime.boxToByte((byte)rt)) : (b > 0 ? new Tuple2(BoxesRunTime.boxToByte((byte)(qt - 1)), BoxesRunTime.boxToByte((byte)(rt + b))) : new Tuple2(BoxesRunTime.boxToByte((byte)(qt + 1)), BoxesRunTime.boxToByte((byte)(rt - b))));
   }

   public Tuple2 equotmod(final short a, final short b) {
      int qt = a / b;
      int rt = a % b;
      return rt >= 0 ? new Tuple2(BoxesRunTime.boxToShort((short)qt), BoxesRunTime.boxToShort((short)rt)) : (b > 0 ? new Tuple2(BoxesRunTime.boxToShort((short)(qt - 1)), BoxesRunTime.boxToShort((short)(rt + b))) : new Tuple2(BoxesRunTime.boxToShort((short)(qt + 1)), BoxesRunTime.boxToShort((short)(rt - b))));
   }

   public Tuple2 equotmod(final int a, final int b) {
      int qt = a / b;
      int rt = a % b;
      return rt >= 0 ? new Tuple2.mcII.sp(qt, rt) : (b > 0 ? new Tuple2.mcII.sp(qt - 1, rt + b) : new Tuple2.mcII.sp(qt + 1, rt - b));
   }

   public Tuple2 equotmod(final long a, final long b) {
      long qt = a / b;
      long rt = a % b;
      return rt >= 0L ? new Tuple2.mcJJ.sp(qt, rt) : (b > 0L ? new Tuple2.mcJJ.sp(qt - 1L, rt + b) : new Tuple2.mcJJ.sp(qt + 1L, rt - b));
   }

   public Tuple2 equotmod(final BigInt a, final BigInt b) {
      Tuple2 var5 = a.$div$percent(b);
      if (var5 != null) {
         BigInt qt = (BigInt)var5._1();
         BigInt rt = (BigInt)var5._2();
         Tuple2 var3 = new Tuple2(qt, rt);
         BigInt qt = (BigInt)var3._1();
         BigInt rt = (BigInt)var3._2();
         return rt.signum() >= 0 ? new Tuple2(qt, rt) : (b.signum() > 0 ? new Tuple2(qt.$minus(scala.math.BigInt..MODULE$.int2bigInt(1)), rt.$plus(b)) : new Tuple2(qt.$plus(scala.math.BigInt..MODULE$.int2bigInt(1)), rt.$minus(b)));
      } else {
         throw new MatchError(var5);
      }
   }

   public Tuple2 equotmod(final BigInteger a, final BigInteger b) {
      BigInteger[] arr = a.divideAndRemainder(b);
      BigInteger qt = arr[0];
      BigInteger rt = arr[1];
      return rt.signum() >= 0 ? new Tuple2(qt, rt) : (b.signum() > 0 ? new Tuple2(qt.subtract(BigInteger.ONE), rt.add(b)) : new Tuple2(qt.add(BigInteger.ONE), rt.subtract(b)));
   }

   public byte equot(final byte a, final byte b) {
      int qt = a / b;
      int rt = a % b;
      return rt >= 0 ? (byte)qt : (b > 0 ? (byte)(qt - 1) : (byte)(qt + 1));
   }

   public short equot(final short a, final short b) {
      int qt = a / b;
      int rt = a % b;
      return rt >= 0 ? (short)qt : (b > 0 ? (short)(qt - 1) : (short)(qt + 1));
   }

   public int equot(final int a, final int b) {
      int qt = a / b;
      int rt = a % b;
      return rt >= 0 ? qt : (b > 0 ? qt - 1 : qt + 1);
   }

   public long equot(final long a, final long b) {
      long qt = a / b;
      long rt = a % b;
      return rt >= 0L ? qt : (b > 0L ? qt - 1L : qt + 1L);
   }

   public BigInt equot(final BigInt a, final BigInt b) {
      Tuple2 var5 = a.$div$percent(b);
      if (var5 != null) {
         BigInt qt = (BigInt)var5._1();
         BigInt rt = (BigInt)var5._2();
         Tuple2 var3 = new Tuple2(qt, rt);
         BigInt qt = (BigInt)var3._1();
         BigInt rt = (BigInt)var3._2();
         return rt.signum() >= 0 ? qt : (b.signum() > 0 ? qt.$minus(scala.math.BigInt..MODULE$.int2bigInt(1)) : qt.$plus(scala.math.BigInt..MODULE$.int2bigInt(1)));
      } else {
         throw new MatchError(var5);
      }
   }

   public BigInteger equot(final BigInteger a, final BigInteger b) {
      BigInteger[] arr = a.divideAndRemainder(b);
      BigInteger qt = arr[0];
      BigInteger rt = arr[1];
      return rt.signum() >= 0 ? qt : (b.signum() > 0 ? qt.subtract(BigInteger.ONE) : qt.add(BigInteger.ONE));
   }

   public byte emod(final byte a, final byte b) {
      int rt = a % b;
      return rt >= 0 ? (byte)rt : (b > 0 ? (byte)(rt + b) : (byte)(rt - b));
   }

   public short emod(final short a, final short b) {
      int rt = a % b;
      return rt >= 0 ? (short)rt : (b > 0 ? (short)(rt + b) : (short)(rt - b));
   }

   public int emod(final int a, final int b) {
      int rt = a % b;
      return rt >= 0 ? rt : (b > 0 ? rt + b : rt - b);
   }

   public long emod(final long a, final long b) {
      long rt = a % b;
      return rt >= 0L ? rt : (b > 0L ? rt + b : rt - b);
   }

   public BigInt emod(final BigInt a, final BigInt b) {
      BigInt rt = a.$percent(b);
      return rt.signum() >= 0 ? rt : (b.$greater(scala.math.BigInt..MODULE$.int2bigInt(0)) ? rt.$plus(b) : rt.$minus(b));
   }

   public BigInteger emod(final BigInteger a, final BigInteger b) {
      BigInteger rt = a.remainder(b);
      return rt.signum() >= 0 ? rt : (b.signum() > 0 ? rt.add(b) : rt.subtract(b));
   }

   public final byte min(final byte x, final byte y) {
      return (byte)Math.min(x, y);
   }

   public final short min(final short x, final short y) {
      return (short)Math.min(x, y);
   }

   public final int min(final int x, final int y) {
      return Math.min(x, y);
   }

   public final long min(final long x, final long y) {
      return Math.min(x, y);
   }

   public final float min(final float x, final float y) {
      return Math.min(x, y);
   }

   public final double min(final double x, final double y) {
      return Math.min(x, y);
   }

   public final Object min(final Object x, final Object y, final Order ev) {
      return ev.min(x, y);
   }

   public final byte max(final byte x, final byte y) {
      return (byte)Math.max(x, y);
   }

   public final short max(final short x, final short y) {
      return (short)Math.max(x, y);
   }

   public final int max(final int x, final int y) {
      return Math.max(x, y);
   }

   public final long max(final long x, final long y) {
      return Math.max(x, y);
   }

   public final float max(final float x, final float y) {
      return Math.max(x, y);
   }

   public final double max(final double x, final double y) {
      return Math.max(x, y);
   }

   public final Object max(final Object x, final Object y, final Order ev) {
      return ev.max(x, y);
   }

   public final double signum(final double x) {
      return Math.signum(x);
   }

   public final float signum(final float x) {
      return Math.signum(x);
   }

   public final int signum(final Object a, final Signed ev) {
      return ev.signum(a);
   }

   public final double sqrt(final double x) {
      return Math.sqrt(x);
   }

   public final Object sqrt(final Object a, final NRoot ev) {
      return ev.sqrt(a);
   }

   public final double e() {
      return Math.E;
   }

   public final Object e(final Trig ev) {
      return ev.e();
   }

   public final double pi() {
      return Math.PI;
   }

   public final Object pi(final Trig ev) {
      return ev.pi();
   }

   public final Object sin(final Object a, final Trig ev) {
      return ev.sin(a);
   }

   public final Object cos(final Object a, final Trig ev) {
      return ev.cos(a);
   }

   public final Object tan(final Object a, final Trig ev) {
      return ev.tan(a);
   }

   public final Object asin(final Object a, final Trig ev) {
      return ev.asin(a);
   }

   public final Object acos(final Object a, final Trig ev) {
      return ev.acos(a);
   }

   public final Object atan(final Object a, final Trig ev) {
      return ev.atan(a);
   }

   public final Object atan2(final Object y, final Object x, final Trig ev) {
      return ev.atan2(y, x);
   }

   public final Object sinh(final Object x, final Trig ev) {
      return ev.sinh(x);
   }

   public final Object cosh(final Object x, final Trig ev) {
      return ev.cosh(x);
   }

   public final Object tanh(final Object x, final Trig ev) {
      return ev.tanh(x);
   }

   public final double cbrt(final double x) {
      return Math.cbrt(x);
   }

   public final double copySign(final double m, final double s) {
      return Math.copySign(m, s);
   }

   public final float copySign(final float m, final float s) {
      return Math.copySign(m, s);
   }

   public final double cosh(final double x) {
      return Math.cosh(x);
   }

   public final double expm1(final double x) {
      return Math.expm1(x);
   }

   public final int getExponent(final double x) {
      return Math.getExponent(x);
   }

   public final int getExponent(final float x) {
      return Math.getExponent(x);
   }

   public final double IEEEremainder(final double x, final double d) {
      return Math.IEEEremainder(x, d);
   }

   public final double log10(final double x) {
      return Math.log10(x);
   }

   public final double log1p(final double x) {
      return Math.log1p(x);
   }

   public final double nextAfter(final double x, final double y) {
      return Math.nextAfter(x, y);
   }

   public final float nextAfter(final float x, final float y) {
      return Math.nextAfter(x, (double)y);
   }

   public final double nextUp(final double x) {
      return Math.nextUp(x);
   }

   public final float nextUp(final float x) {
      return Math.nextUp(x);
   }

   public final double random() {
      return Math.random();
   }

   public final double rint(final double x) {
      return Math.rint(x);
   }

   public final double scalb(final double d, final int s) {
      return Math.scalb(d, s);
   }

   public final float scalb(final float d, final int s) {
      return Math.scalb(d, s);
   }

   public final double toDegrees(final double a) {
      return Math.toDegrees(a);
   }

   public final double toRadians(final double a) {
      return Math.toRadians(a);
   }

   public final double ulp(final double x) {
      return Math.ulp(x);
   }

   public final double ulp(final float x) {
      return (double)Math.ulp(x);
   }

   public final Object hypot(final Object x, final Object y, final Field f, final NRoot n, final Order o, final Signed s) {
      Object ax = abs$1(x, o, f);
      Object ay = abs$1(y, o, f);
      return BoxesRunTime.equals(x, f.zero()) ? ay : (BoxesRunTime.equals(y, f.zero()) ? ax : (o.gt(ax, ay) ? f.times(ax, n.sqrt(LiteralIntAdditiveSemigroupOps$.MODULE$.$plus$extension(implicits$.MODULE$.literalIntAdditiveSemigroupOps(1), f.pow(f.div(y, x), 2), f))) : f.times(ay, n.sqrt(LiteralIntAdditiveSemigroupOps$.MODULE$.$plus$extension(implicits$.MODULE$.literalIntAdditiveSemigroupOps(1), f.pow(f.div(x, y), 2), f)))));
   }

   private int intSearch(final Function1 f) {
      int ceil = BoxesRunTime.unboxToInt(scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), 32).find((JFunction1.mcZI.sp)(i) -> !f.apply$mcZI$sp(1 << i)).getOrElse((JFunction0.mcI.sp)() -> 33));
      return ceil == 0 ? 0 : BoxesRunTime.unboxToInt(scala.runtime.RichInt..MODULE$.to$extension(scala.Predef..MODULE$.intWrapper(ceil - 1), 0).by(-1).foldLeft(BoxesRunTime.boxToInteger(0), (JFunction2.mcIII.sp)(x, i) -> {
         int y = x | 1 << i;
         return f.apply$mcZI$sp(y) ? y : x;
      }));
   }

   private LazyList decDiv(final BigInt x, final BigInt y, final int r) {
      BigInt expanded = x.$times(scala.math.BigInt..MODULE$.int2bigInt(r));
      BigInt quot = expanded.$div(y);
      BigInt rem = expanded.$minus(quot.$times(y));
      return BoxesRunTime.equalsNumObject(rem, BoxesRunTime.boxToInteger(0)) ? scala.collection.immutable.LazyList.Deferrer..MODULE$.$hash$colon$colon$extension(scala.collection.immutable.LazyList..MODULE$.toDeferrer(() -> scala.collection.immutable.LazyList..MODULE$.empty()), () -> quot) : scala.collection.immutable.LazyList.Deferrer..MODULE$.$hash$colon$colon$extension(scala.collection.immutable.LazyList..MODULE$.toDeferrer(() -> MODULE$.decDiv(rem, y, r)), () -> quot);
   }

   private List digitize(final BigInt x, final int r, final List prev) {
      while(!BoxesRunTime.equalsNumObject(x, BoxesRunTime.boxToInteger(0))) {
         BigInt var10000 = x.$div(scala.math.BigInt..MODULE$.int2bigInt(r));
         int var5 = x.$percent(scala.math.BigInt..MODULE$.int2bigInt(r)).toInt();
         prev = prev.$colon$colon(BoxesRunTime.boxToInteger(var5));
         r = r;
         x = var10000;
      }

      return prev;
   }

   private List digitize$default$3() {
      return scala.package..MODULE$.Nil();
   }

   private BigInt undigitize(final Seq digits, final int r) {
      return (BigInt)digits.foldLeft(scala.package..MODULE$.BigInt().apply(0), (x$4, x$5) -> $anonfun$undigitize$1(r, x$4, BoxesRunTime.unboxToInt(x$5)));
   }

   private int radix() {
      return radix;
   }

   public BigDecimal nroot(final BigDecimal a, final int k, final MathContext ctxt) {
      BigDecimal var10000;
      if (k == 0) {
         var10000 = scala.package..MODULE$.BigDecimal().apply(1);
      } else if (a.signum() < 0) {
         if (k % 2 == 0) {
            throw new ArithmeticException(scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("%d-root of negative number"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(k)})));
         }

         var10000 = this.nroot(a.unary_$minus(), k, ctxt).unary_$minus();
      } else {
         BigInt underlying = scala.package..MODULE$.BigInt().apply(a.bigDecimal().unscaledValue().toByteArray());
         BigInt scale = scala.package..MODULE$.BigInt().apply(10).pow(a.scale());
         List intPart = this.digitize(underlying.$div(scale), this.radix(), this.digitize$default$3());
         LazyList fracPart = this.decDiv(underlying.$percent(scale), scale, this.radix()).map((x$6) -> BoxesRunTime.boxToInteger($anonfun$nroot$1(x$6)));
         LazyList leader = intPart.size() % k == 0 ? scala.collection.immutable.LazyList..MODULE$.empty() : scala.collection.immutable.LazyList..MODULE$.fill(k - intPart.size() % k, (JFunction0.mcI.sp)() -> 0);
         LazyList digits = (LazyList)((IterableOps)((IterableOps)leader.$plus$plus(scala.collection.immutable.LazyList..MODULE$.from(intPart))).$plus$plus(fracPart)).$plus$plus(scala.collection.immutable.LazyList..MODULE$.continually((JFunction0.mcI.sp)() -> 0));
         BigInt radixPowK = scala.package..MODULE$.BigInt().apply(this.radix()).pow(k);
         int maxSize = (ctxt.getPrecision() + 8) / 9 + 2;
         Tuple2 var14 = this.findRoot$1(digits, scala.math.BigInt..MODULE$.int2bigInt(0), scala.math.BigInt..MODULE$.int2bigInt(0), 1, k, radixPowK, maxSize);
         if (var14 == null) {
            throw new MatchError(var14);
         }

         int size = var14._1$mcI$sp();
         BigInt unscaled = (BigInt)var14._2();
         Tuple2 var4 = new Tuple2(BoxesRunTime.boxToInteger(size), unscaled);
         int size = var4._1$mcI$sp();
         BigInt unscaled = (BigInt)var4._2();
         int newscale = (size - (intPart.size() + k - 1) / k) * 9;
         var10000 = scala.package..MODULE$.BigDecimal().apply(unscaled, newscale, ctxt);
      }

      return var10000;
   }

   public boolean anyIsZero(final Object n) {
      boolean var2;
      if (BoxesRunTime.equals(n, BoxesRunTime.boxToInteger(0))) {
         var2 = true;
      } else if (n instanceof ScalaNumericConversions) {
         ScalaNumericConversions var4 = (ScalaNumericConversions)n;
         var2 = var4.isValidInt() && var4.toInt() == 0;
      } else {
         var2 = false;
      }

      return var2;
   }

   public double anyToDouble(final Object n) {
      double var2;
      if (n instanceof Byte) {
         byte var5 = BoxesRunTime.unboxToByte(n);
         var2 = (double)var5;
      } else if (n instanceof Short) {
         short var6 = BoxesRunTime.unboxToShort(n);
         var2 = (double)var6;
      } else if (n instanceof Character) {
         char var7 = BoxesRunTime.unboxToChar(n);
         var2 = (double)var7;
      } else if (n instanceof Integer) {
         int var8 = BoxesRunTime.unboxToInt(n);
         var2 = (double)var8;
      } else if (n instanceof Long) {
         long var9 = BoxesRunTime.unboxToLong(n);
         var2 = (double)var9;
      } else if (n instanceof Float) {
         float var11 = BoxesRunTime.unboxToFloat(n);
         var2 = (double)var11;
      } else if (n instanceof Double) {
         double var12 = BoxesRunTime.unboxToDouble(n);
         var2 = var12;
      } else {
         if (!(n instanceof ScalaNumericConversions)) {
            throw new UnsupportedOperationException((new StringBuilder(21)).append(n).append(" is not a ScalaNumber").toString());
         }

         ScalaNumericConversions var14 = (ScalaNumericConversions)n;
         var2 = var14.toDouble();
      }

      return var2;
   }

   public long anyToLong(final Object n) {
      long var2;
      if (n instanceof Byte) {
         byte var5 = BoxesRunTime.unboxToByte(n);
         var2 = (long)var5;
      } else if (n instanceof Short) {
         short var6 = BoxesRunTime.unboxToShort(n);
         var2 = (long)var6;
      } else if (n instanceof Character) {
         char var7 = BoxesRunTime.unboxToChar(n);
         var2 = (long)var7;
      } else if (n instanceof Integer) {
         int var8 = BoxesRunTime.unboxToInt(n);
         var2 = (long)var8;
      } else if (n instanceof Long) {
         long var9 = BoxesRunTime.unboxToLong(n);
         var2 = var9;
      } else if (n instanceof Float) {
         float var11 = BoxesRunTime.unboxToFloat(n);
         var2 = (long)var11;
      } else if (n instanceof Double) {
         double var12 = BoxesRunTime.unboxToDouble(n);
         var2 = (long)var12;
      } else {
         if (!(n instanceof ScalaNumericConversions)) {
            throw new UnsupportedOperationException((new StringBuilder(21)).append(n).append(" is not a ScalaNumber").toString());
         }

         ScalaNumericConversions var14 = (ScalaNumericConversions)n;
         var2 = var14.toLong();
      }

      return var2;
   }

   public boolean anyIsWhole(final Object n) {
      boolean var2;
      if (n instanceof Byte) {
         var2 = true;
      } else if (n instanceof Short) {
         var2 = true;
      } else if (n instanceof Character) {
         var2 = true;
      } else if (n instanceof Integer) {
         var2 = true;
      } else if (n instanceof Long) {
         var2 = true;
      } else if (n instanceof Float) {
         float var4 = BoxesRunTime.unboxToFloat(n);
         var2 = scala.runtime.RichFloat..MODULE$.isWhole$extension(scala.Predef..MODULE$.floatWrapper(var4));
      } else if (n instanceof Double) {
         double var5 = BoxesRunTime.unboxToDouble(n);
         var2 = scala.runtime.RichDouble..MODULE$.isWhole$extension(scala.Predef..MODULE$.doubleWrapper(var5));
      } else {
         if (!(n instanceof ScalaNumericConversions)) {
            throw new UnsupportedOperationException((new StringBuilder(21)).append(n).append(" is not a ScalaNumber").toString());
         }

         ScalaNumericConversions var7 = (ScalaNumericConversions)n;
         var2 = var7.isWhole();
      }

      return var2;
   }

   public boolean anyIsValidInt(final Object n) {
      boolean var2;
      if (n instanceof Byte) {
         var2 = true;
      } else if (n instanceof Short) {
         var2 = true;
      } else if (n instanceof Character) {
         var2 = true;
      } else if (n instanceof Integer) {
         var2 = true;
      } else if (n instanceof Long) {
         long var4 = BoxesRunTime.unboxToLong(n);
         var2 = scala.runtime.RichLong..MODULE$.isValidInt$extension(scala.Predef..MODULE$.longWrapper(var4));
      } else if (n instanceof Float) {
         float var6 = BoxesRunTime.unboxToFloat(n);
         var2 = scala.runtime.RichFloat..MODULE$.isValidInt$extension(scala.Predef..MODULE$.floatWrapper(var6));
      } else if (n instanceof Double) {
         double var7 = BoxesRunTime.unboxToDouble(n);
         var2 = scala.runtime.RichDouble..MODULE$.isValidInt$extension(scala.Predef..MODULE$.doubleWrapper(var7));
      } else {
         if (!(n instanceof ScalaNumericConversions)) {
            throw new UnsupportedOperationException((new StringBuilder(21)).append(n).append(" is not a ScalaNumber").toString());
         }

         ScalaNumericConversions var9 = (ScalaNumericConversions)n;
         var2 = var9.isValidInt();
      }

      return var2;
   }

   public final double e$mDc$sp(final Trig ev) {
      return ev.e$mcD$sp();
   }

   public final float e$mFc$sp(final Trig ev) {
      return ev.e$mcF$sp();
   }

   public final double pi$mDc$sp(final Trig ev) {
      return ev.pi$mcD$sp();
   }

   public final float pi$mFc$sp(final Trig ev) {
      return ev.pi$mcF$sp();
   }

   public final double sin$mDc$sp(final double a, final Trig ev) {
      return ev.sin$mcD$sp(a);
   }

   public final float sin$mFc$sp(final float a, final Trig ev) {
      return ev.sin$mcF$sp(a);
   }

   public final double cos$mDc$sp(final double a, final Trig ev) {
      return ev.cos$mcD$sp(a);
   }

   public final float cos$mFc$sp(final float a, final Trig ev) {
      return ev.cos$mcF$sp(a);
   }

   public final double tan$mDc$sp(final double a, final Trig ev) {
      return ev.tan$mcD$sp(a);
   }

   public final float tan$mFc$sp(final float a, final Trig ev) {
      return ev.tan$mcF$sp(a);
   }

   public final double asin$mDc$sp(final double a, final Trig ev) {
      return ev.asin$mcD$sp(a);
   }

   public final float asin$mFc$sp(final float a, final Trig ev) {
      return ev.asin$mcF$sp(a);
   }

   public final double acos$mDc$sp(final double a, final Trig ev) {
      return ev.acos$mcD$sp(a);
   }

   public final float acos$mFc$sp(final float a, final Trig ev) {
      return ev.acos$mcF$sp(a);
   }

   public final double atan$mDc$sp(final double a, final Trig ev) {
      return ev.atan$mcD$sp(a);
   }

   public final float atan$mFc$sp(final float a, final Trig ev) {
      return ev.atan$mcF$sp(a);
   }

   public final double atan2$mDc$sp(final double y, final double x, final Trig ev) {
      return ev.atan2$mcD$sp(y, x);
   }

   public final float atan2$mFc$sp(final float y, final float x, final Trig ev) {
      return ev.atan2$mcF$sp(y, x);
   }

   public final double sinh$mDc$sp(final double x, final Trig ev) {
      return ev.sinh$mcD$sp(x);
   }

   public final float sinh$mFc$sp(final float x, final Trig ev) {
      return ev.sinh$mcF$sp(x);
   }

   public final double cosh$mDc$sp(final double x, final Trig ev) {
      return ev.cosh$mcD$sp(x);
   }

   public final float cosh$mFc$sp(final float x, final Trig ev) {
      return ev.cosh$mcF$sp(x);
   }

   public final double tanh$mDc$sp(final double x, final Trig ev) {
      return ev.tanh$mcD$sp(x);
   }

   public final float tanh$mFc$sp(final float x, final Trig ev) {
      return ev.tanh$mcF$sp(x);
   }

   public final double hypot$mDc$sp(final double x, final double y, final Field f, final NRoot n, final Order o, final Signed s) {
      double ax = abs$2(x, o, f);
      double ay = abs$2(y, o, f);
      return x == f.zero$mcD$sp() ? ay : (y == f.zero$mcD$sp() ? ax : (o.gt$mcD$sp(ax, ay) ? f.times$mcD$sp(ax, n.sqrt$mcD$sp(BoxesRunTime.unboxToDouble(LiteralIntAdditiveSemigroupOps$.MODULE$.$plus$extension(implicits$.MODULE$.literalIntAdditiveSemigroupOps(1), BoxesRunTime.boxToDouble(f.pow$mcD$sp(f.div$mcD$sp(y, x), 2)), f)))) : f.times$mcD$sp(ay, n.sqrt$mcD$sp(BoxesRunTime.unboxToDouble(LiteralIntAdditiveSemigroupOps$.MODULE$.$plus$extension(implicits$.MODULE$.literalIntAdditiveSemigroupOps(1), BoxesRunTime.boxToDouble(f.pow$mcD$sp(f.div$mcD$sp(x, y), 2)), f))))));
   }

   public final float hypot$mFc$sp(final float x, final float y, final Field f, final NRoot n, final Order o, final Signed s) {
      float ax = abs$3(x, o, f);
      float ay = abs$3(y, o, f);
      return x == f.zero$mcF$sp() ? ay : (y == f.zero$mcF$sp() ? ax : (o.gt$mcF$sp(ax, ay) ? f.times$mcF$sp(ax, n.sqrt$mcF$sp(BoxesRunTime.unboxToFloat(LiteralIntAdditiveSemigroupOps$.MODULE$.$plus$extension(implicits$.MODULE$.literalIntAdditiveSemigroupOps(1), BoxesRunTime.boxToFloat(f.pow$mcF$sp(f.div$mcF$sp(y, x), 2)), f)))) : f.times$mcF$sp(ay, n.sqrt$mcF$sp(BoxesRunTime.unboxToFloat(LiteralIntAdditiveSemigroupOps$.MODULE$.$plus$extension(implicits$.MODULE$.literalIntAdditiveSemigroupOps(1), BoxesRunTime.boxToFloat(f.pow$mcF$sp(f.div$mcF$sp(x, y), 2)), f))))));
   }

   private final BigInt loop$1(final long lo, final long hi, final BigInt prod) {
      while(lo <= hi) {
         long var10000 = lo + 1L;
         long var10001 = hi - 1L;
         prod = scala.package..MODULE$.BigInt().apply(lo).$times(scala.package..MODULE$.BigInt().apply(hi)).$times(prod);
         hi = var10001;
         lo = var10000;
      }

      return prod;
   }

   private final BigInt loop$2(final long lo, final long hi, final BigInt prod) {
      while(lo <= hi) {
         long var10000 = lo + 1L;
         long var10001 = hi - 1L;
         prod = scala.package..MODULE$.BigInt().apply(lo).$times(scala.package..MODULE$.BigInt().apply(hi)).$times(prod);
         hi = var10001;
         lo = var10000;
      }

      return prod;
   }

   private final BigInt loop$3(final BigInt a, final BigInt b, final int i, final long n$1) {
      while(true) {
         BigInt c = a.$plus(b);
         if (i < 0) {
            return b;
         }

         if ((n$1 >>> i & 1L) == 1L) {
            BigInt var8 = a.$plus(c).$times(b);
            BigInt var9 = b.$times(b).$plus(c.$times(c));
            --i;
            b = var9;
            a = var8;
         } else {
            BigInt var10000 = a.$times(a).$plus(b.$times(b));
            BigInt var10001 = a.$plus(c).$times(b);
            --i;
            b = var10001;
            a = var10000;
         }
      }
   }

   private final BigDecimal power$1(final BigDecimal result, final BigDecimal base, final BigInt exponent) {
      while(exponent.signum() != 0) {
         if (exponent.testBit(0)) {
            BigDecimal var10000 = result.$times(base);
            BigDecimal var4 = base.$times(base);
            exponent = exponent.$greater$greater(1);
            base = var4;
            result = var10000;
         } else {
            BigDecimal var10001 = base.$times(base);
            exponent = exponent.$greater$greater(1);
            base = var10001;
            result = result;
         }
      }

      return result;
   }

   private final BigDecimal doit$1(final int precision, final int leeway, final BigDecimal k$1) {
      while(true) {
         MathContext mc = new MathContext(precision, RoundingMode.HALF_UP);
         int i = 2;
         BigDecimal sum = scala.package..MODULE$.BigDecimal().apply(1, mc).$plus(k$1);
         BigDecimal factorial = scala.package..MODULE$.BigDecimal().apply(2, mc);
         BigDecimal kpow = k$1.$times(k$1);

         for(BigDecimal term = kpow.$div(factorial).setScale(precision, .MODULE$.HALF_UP()); term.signum() != 0 && i < leeway; term = kpow.$div(factorial).setScale(precision, .MODULE$.HALF_UP())) {
            ++i;
            sum = sum.$plus(term);
            factorial = factorial.$times(scala.math.BigDecimal..MODULE$.int2bigDecimal(i));
            kpow = kpow.$times(k$1);
         }

         if (i <= leeway) {
            return sum.setScale(k$1.mc().getPrecision() - sum.precision() + sum.scale(), .MODULE$.FLOOR());
         }

         int var10000 = precision + 3;
         leeway *= 1000;
         precision = var10000;
      }
   }

   private final BigDecimal loop$4(final BigDecimal x, final BigDecimal n$2, final BigDecimal limit$1) {
      while(true) {
         BigDecimal xp = this.exp(x);
         BigDecimal term = xp.$minus(n$2).$div(xp);
         if (!term.$greater(limit$1)) {
            return x.$minus(term);
         }

         x = x.$minus(term);
      }
   }

   private final BigDecimal ln$1(final BigDecimal n, final int scale$1) {
      int scale2 = scale$1 + 1;
      BigDecimal limit = scala.package..MODULE$.BigDecimal().apply(5).$times(scala.package..MODULE$.BigDecimal().apply(10).pow(-scale2));
      return this.loop$4(n.setScale(scale2, .MODULE$.HALF_UP()), n, limit).setScale(scale$1, .MODULE$.HALF_UP());
   }

   private final Tuple2 rescale$1(final BigDecimal x, final int n) {
      while(!x.$less(scala.math.BigDecimal..MODULE$.int2bigDecimal(64))) {
         BigDecimal var10000 = (BigDecimal)((NRoot)spire.std.package.bigDecimal$.MODULE$.BigDecimalAlgebra()).sqrt(x);
         ++n;
         x = var10000;
      }

      return new Tuple2(x, BoxesRunTime.boxToInteger(n));
   }

   private final BigInt bigIntPow$1(final BigInt t, final BigInt b, final BigInt e) {
      while(e.signum() != 0) {
         if (e.testBit(0)) {
            BigInt var10000 = t.$times(b);
            BigInt var4 = b.$times(b);
            e = e.$greater$greater(1);
            b = var4;
            t = var10000;
         } else {
            BigInt var10001 = b.$times(b);
            e = e.$greater$greater(1);
            b = var10001;
            t = t;
         }
      }

      return t;
   }

   private final long longPow$1(final long t, final long b, final long e) {
      while(e != 0L) {
         if ((e & 1L) == 1L) {
            long var10000 = t * b;
            long var7 = b * b;
            e >>= (int)1L;
            b = var7;
            t = var10000;
         } else {
            long var10001 = b * b;
            e >>= (int)1L;
            b = var10001;
            t = t;
         }
      }

      return t;
   }

   private static final Object abs$1(final Object n, final Order o$1, final Field f$1) {
      return o$1.lt(n, f$1.zero()) ? f$1.negate(n) : n;
   }

   // $FF: synthetic method
   public static final BigInt $anonfun$undigitize$1(final int r$2, final BigInt x$4, final int x$5) {
      return x$4.$times(scala.math.BigInt..MODULE$.int2bigInt(r$2)).$plus(scala.math.BigInt..MODULE$.int2bigInt(x$5));
   }

   // $FF: synthetic method
   public static final int $anonfun$nroot$1(final BigInt x$6) {
      return x$6.toInt();
   }

   private final Tuple2 findRoot$1(final LazyList digits, final BigInt y, final BigInt r, final int i, final int k$2, final BigInt radixPowK$1, final int maxSize$1) {
      while(true) {
         BigInt y_ = y.$times(scala.math.BigInt..MODULE$.int2bigInt(this.radix()));
         BigInt a = this.undigitize(digits.take(k$2), this.radix());
         BigInt target = radixPowK$1.$times(r).$plus(a).$plus(y_.pow(k$2));
         int b = this.intSearch((JFunction1.mcZI.sp)(bx) -> y_.$plus(scala.math.BigInt..MODULE$.int2bigInt(bx)).pow(k$2).$less$eq(target));
         BigInt ny = y_.$plus(scala.math.BigInt..MODULE$.int2bigInt(b));
         if (i == maxSize$1) {
            return new Tuple2(BoxesRunTime.boxToInteger(i), ny);
         }

         BigInt nr = target.$minus(ny.pow(k$2));
         LazyList var10000 = digits.drop(k$2);
         ++i;
         r = nr;
         y = ny;
         digits = var10000;
      }
   }

   private static final double abs$2(final double n, final Order o$2, final Field f$3) {
      return o$2.lt$mcD$sp(n, f$3.zero$mcD$sp()) ? f$3.negate$mcD$sp(n) : n;
   }

   private static final float abs$3(final float n, final Order o$3, final Field f$4) {
      return o$3.lt$mcF$sp(n, f$4.zero$mcF$sp()) ? f$4.negate$mcF$sp(n) : n;
   }

   private package$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
