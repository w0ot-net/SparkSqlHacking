package spire.math;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.Tuple2;
import scala.collection.SeqFactory;
import scala.collection.SeqOps;
import scala.collection.immutable.LazyList;
import scala.collection.immutable.Stream;
import scala.math.BigDecimal;
import scala.math.BigInt;
import scala.package.;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.java8.JFunction1;

public final class Real$ implements RealInstances, Serializable {
   public static final Real$ MODULE$ = new Real$();
   private static Real pi;
   private static Real e;
   private static Real phi;
   private static Real piBy2;
   private static Real piBy4;
   private static Real log2;
   private static Real sqrt1By2;
   private static final Real zero;
   private static final Real one;
   private static final Real two;
   private static final Real four;
   private static Fractional algebra;
   private static NumberTag RealTag;
   private static volatile byte bitmap$0;

   static {
      RealInstances.$init$(MODULE$);
      zero = new Real.Exact(Rational$.MODULE$.zero());
      one = new Real.Exact(Rational$.MODULE$.one());
      two = new Real.Exact(Rational$.MODULE$.apply(2));
      four = new Real.Exact(Rational$.MODULE$.apply(4));
   }

   public final Fractional algebra() {
      return algebra;
   }

   public final NumberTag RealTag() {
      return RealTag;
   }

   public final void spire$math$RealInstances$_setter_$algebra_$eq(final Fractional x$1) {
      algebra = x$1;
   }

   public final void spire$math$RealInstances$_setter_$RealTag_$eq(final NumberTag x$1) {
      RealTag = x$1;
   }

   public Real zero() {
      return zero;
   }

   public Real one() {
      return one;
   }

   public Real two() {
      return two;
   }

   public Real four() {
      return four;
   }

   public Real apply(final Function1 f) {
      return new Real.Inexact(f);
   }

   public Real apply(final int n) {
      return new Real.Exact(Rational$.MODULE$.apply(n));
   }

   public Real apply(final long n) {
      return new Real.Exact(Rational$.MODULE$.apply(n));
   }

   public Real apply(final BigInt n) {
      return new Real.Exact(Rational$.MODULE$.apply(n));
   }

   public Real apply(final SafeLong n) {
      return new Real.Exact(Rational$.MODULE$.apply(n));
   }

   public Real apply(final Rational n) {
      return new Real.Exact(n);
   }

   public Real apply(final double n) {
      return new Real.Exact(Rational$.MODULE$.apply(n));
   }

   public Real apply(final BigDecimal n) {
      return new Real.Exact(Rational$.MODULE$.apply(n));
   }

   public Real apply(final String s) {
      return new Real.Exact(Rational$.MODULE$.apply(s));
   }

   private Real pi$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(bitmap$0 & 1) == 0) {
            pi = this.apply(16).$times(this.atan(this.apply(Rational$.MODULE$.apply(1L, 5L)))).$minus(this.four().$times(this.atan(this.apply(Rational$.MODULE$.apply(1L, 239L)))));
            bitmap$0 = (byte)(bitmap$0 | 1);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return pi;
   }

   public Real pi() {
      return (byte)(bitmap$0 & 1) == 0 ? this.pi$lzycompute() : pi;
   }

   private Real e$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(bitmap$0 & 2) == 0) {
            e = this.exp(this.one());
            bitmap$0 = (byte)(bitmap$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return e;
   }

   public Real e() {
      return (byte)(bitmap$0 & 2) == 0 ? this.e$lzycompute() : e;
   }

   private Real phi$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(bitmap$0 & 4) == 0) {
            phi = this.one().$plus(this.apply(5).sqrt()).$div(this.two());
            bitmap$0 = (byte)(bitmap$0 | 4);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return phi;
   }

   public Real phi() {
      return (byte)(bitmap$0 & 4) == 0 ? this.phi$lzycompute() : phi;
   }

   public Real log(final Real x) {
      SafeLong t = x.apply(2);
      int n = this.sizeInBase(t, 2) - 3;
      if (t.$less(SafeLong$.MODULE$.apply(0))) {
         throw new ArithmeticException("log of negative number");
      } else {
         return t.$less(SafeLong$.MODULE$.apply(4)) ? this.log(x.reciprocal()).unary_$minus() : (t.$less(SafeLong$.MODULE$.apply(8)) ? this.logDr(x) : this.logDr(this.div2n(x, n)).$plus(this.apply(n).$times(this.log2())));
      }
   }

   public Real exp(final Real x) {
      Real u = x.$div(this.log2());
      SafeLong n = u.apply(0);
      Real s = x.$minus(this.apply(n).$times(this.log2()));
      if (!n.isValidInt()) {
         throw new ArithmeticException("invalid power in exp");
      } else {
         return n.$less(SafeLong$.MODULE$.apply(0)) ? this.div2n(this.expDr(s), -n.toInt()) : (n.$greater(SafeLong$.MODULE$.apply(0)) ? this.mul2n(this.expDr(s), n.toInt()) : this.expDr(s));
      }
   }

   public Real sin(final Real x) {
      Real z = x.$div(this.piBy4());
      SafeLong s = this.roundUp(Rational$.MODULE$.apply(z.apply(2), SafeLong$.MODULE$.apply(4)));
      Real y = x.$minus(this.piBy4().$times(this.apply(s)));
      int m = s.$percent(8L).toInt();
      int n = m < 0 ? m + 8 : m;
      Real var10000;
      switch (n) {
         case 0:
            var10000 = this.sinDr(y);
            break;
         case 1:
            var10000 = this.sqrt1By2().$times(this.cosDr(y).$plus(this.sinDr(y)));
            break;
         case 2:
            var10000 = this.cosDr(y);
            break;
         case 3:
            var10000 = this.sqrt1By2().$times(this.cosDr(y).$minus(this.sinDr(y)));
            break;
         case 4:
            var10000 = this.sinDr(y).unary_$minus();
            break;
         case 5:
            var10000 = this.sqrt1By2().unary_$minus().$times(this.cosDr(y).$plus(this.sinDr(y)));
            break;
         case 6:
            var10000 = this.cosDr(y).unary_$minus();
            break;
         case 7:
            var10000 = this.sqrt1By2().unary_$minus().$times(this.cosDr(y).$minus(this.sinDr(y)));
            break;
         default:
            throw new MatchError(BoxesRunTime.boxToInteger(n));
      }

      return var10000;
   }

   public Real cos(final Real x) {
      Real z = x.$div(this.piBy4());
      SafeLong s = this.roundUp(Rational$.MODULE$.apply(z.apply(2), SafeLong$.MODULE$.apply(4)));
      Real y = x.$minus(this.piBy4().$times(this.apply(s)));
      int m = s.$percent(8L).toInt();
      int n = m < 0 ? m + 8 : m;
      Real var10000;
      switch (n) {
         case 0:
            var10000 = this.cosDr(y);
            break;
         case 1:
            var10000 = this.sqrt1By2().$times(this.cosDr(y).$minus(this.sinDr(y)));
            break;
         case 2:
            var10000 = this.sinDr(y).unary_$minus();
            break;
         case 3:
            var10000 = this.sqrt1By2().unary_$minus().$times(this.cosDr(y).$plus(this.sinDr(y)));
            break;
         case 4:
            var10000 = this.cosDr(y).unary_$minus();
            break;
         case 5:
            var10000 = this.sqrt1By2().unary_$minus().$times(this.cosDr(y).$minus(this.sinDr(y)));
            break;
         case 6:
            var10000 = this.sinDr(y);
            break;
         case 7:
            var10000 = this.sqrt1By2().$times(this.cosDr(y).$plus(this.sinDr(y)));
            break;
         default:
            throw new MatchError(BoxesRunTime.boxToInteger(n));
      }

      return var10000;
   }

   public Real tan(final Real x) {
      return this.sin(x).$div(this.cos(x));
   }

   public Real atan(final Real x) {
      SafeLong t = x.apply(2);
      Real xp1 = x.$plus(this.one());
      Real xm1 = x.$minus(this.one());
      return t.$less(SafeLong$.MODULE$.apply(-5)) ? this.atanDr(x.reciprocal().unary_$minus()).$minus(this.piBy2()) : (BoxesRunTime.equalsNumObject(t, BoxesRunTime.boxToInteger(-4)) ? this.piBy4().unary_$minus().$minus(this.atanDr(xp1.$div(xm1))) : (t.$less(SafeLong$.MODULE$.apply(4)) ? this.atanDr(x) : (BoxesRunTime.equalsNumObject(t, BoxesRunTime.boxToInteger(4)) ? this.piBy4().$plus(this.atanDr(xm1.$div(xp1))) : this.piBy2().$minus(this.atanDr(x.reciprocal())))));
   }

   public Real atan2(final Real y, final Real x) {
      return this.apply((Function1)((p) -> $anonfun$atan2$1(x, y, BoxesRunTime.unboxToInt(p))));
   }

   public Real asin(final Real x) {
      SafeLong x0 = x.apply(0);
      Real s = this.one().$minus(x.$times(x)).sqrt();
      int var5 = x0.signum();
      Real var2;
      if (var5 > 0) {
         var2 = this.pi().$div(this.two()).$minus(this.atan(s.$div(x)));
      } else if (0 == var5) {
         var2 = this.atan(x.$div(s));
      } else {
         var2 = this.pi().unary_$minus().$div(this.two()).$minus(this.atan(s.$div(x)));
      }

      return var2;
   }

   public Real acos(final Real x) {
      return this.pi().$div(this.two()).$minus(this.asin(x));
   }

   public Real sinh(final Real x) {
      Real y = this.exp(x);
      return y.$minus(y.reciprocal()).$div(this.two());
   }

   public Real cosh(final Real x) {
      Real y = this.exp(x);
      return y.$plus(y.reciprocal()).$div(this.two());
   }

   public Real tanh(final Real x) {
      Real y = this.exp(x);
      Real y2 = y.reciprocal();
      return y.$minus(y2).$div(y.$plus(y2));
   }

   public Real asinh(final Real x) {
      return this.log(x.$plus(x.$times(x).$plus(this.one()).sqrt()));
   }

   public Real acosh(final Real x) {
      return this.log(x.$plus(x.$times(x).$minus(this.one()).sqrt()));
   }

   public Real atanh(final Real x) {
      return this.log(this.one().$plus(x).$div(this.one().$minus(x))).$div(this.two());
   }

   public int digits() {
      return 40;
   }

   public int bits() {
      return this.digitsToBits(this.digits());
   }

   public int digitsToBits(final int n) {
      return (int)package$.MODULE$.ceil((double)n * (package$.MODULE$.log((double)10.0F) / package$.MODULE$.log((double)2.0F))) + 4;
   }

   public int sizeInBase(final SafeLong n, final int base) {
      return this.loop$2(n.abs(), 0, base);
   }

   public SafeLong roundUp(final Rational r) {
      return SafeLong$.MODULE$.apply(r.round().toBigInt());
   }

   public Real div2n(final Real x, final int n) {
      return this.apply((Function1)((p) -> $anonfun$div2n$1(n, x, BoxesRunTime.unboxToInt(p))));
   }

   public Real mul2n(final Real x, final int n) {
      return this.apply((Function1)((p) -> $anonfun$mul2n$1(x, n, BoxesRunTime.unboxToInt(p))));
   }

   private Real piBy2$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(bitmap$0 & 8) == 0) {
            piBy2 = this.div2n(this.pi(), 1);
            bitmap$0 = (byte)(bitmap$0 | 8);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return piBy2;
   }

   public Real piBy2() {
      return (byte)(bitmap$0 & 8) == 0 ? this.piBy2$lzycompute() : piBy2;
   }

   private Real piBy4$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(bitmap$0 & 16) == 0) {
            piBy4 = this.div2n(this.pi(), 2);
            bitmap$0 = (byte)(bitmap$0 | 16);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return piBy4;
   }

   public Real piBy4() {
      return (byte)(bitmap$0 & 16) == 0 ? this.piBy4$lzycompute() : piBy4;
   }

   private Real log2$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(bitmap$0 & 32) == 0) {
            log2 = this.div2n(this.logDrx(this.two().reciprocal()), 1);
            bitmap$0 = (byte)(bitmap$0 | 32);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return log2;
   }

   public Real log2() {
      return (byte)(bitmap$0 & 32) == 0 ? this.log2$lzycompute() : log2;
   }

   private Real sqrt1By2$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(bitmap$0 & 64) == 0) {
            sqrt1By2 = this.two().reciprocal().sqrt();
            bitmap$0 = (byte)(bitmap$0 | 64);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return sqrt1By2;
   }

   public Real sqrt1By2() {
      return (byte)(bitmap$0 & 64) == 0 ? this.sqrt1By2$lzycompute() : sqrt1By2;
   }

   public SafeLong accumulate(final SafeLong total, final LazyList xs, final LazyList cs) {
      SafeLong var5;
      while(true) {
         Tuple2 var6 = new Tuple2(xs, cs);
         if (var6 != null) {
            LazyList var7 = (LazyList)var6._2();
            if (var7 != null) {
               SeqOps var8 = .MODULE$.Seq().unapplySeq(var7);
               if (!scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.isEmpty$extension(var8) && new SeqFactory.UnapplySeqWrapper(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var8)) != null && scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var8), 0) == 0) {
                  var5 = total;
                  break;
               }
            }
         }

         if (var6 != null) {
            LazyList var9 = (LazyList)var6._1();
            if (var9 != null) {
               SeqOps var10 = .MODULE$.Seq().unapplySeq(var9);
               if (!scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.isEmpty$extension(var10) && new SeqFactory.UnapplySeqWrapper(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var10)) != null && scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var10), 0) == 0) {
                  throw scala.sys.package..MODULE$.error("nooooo");
               }
            }
         }

         if (var6 == null) {
            throw new MatchError(var6);
         }

         LazyList var11 = (LazyList)var6._1();
         LazyList var12 = (LazyList)var6._2();
         if (var11 == null) {
            throw new MatchError(var6);
         }

         Option var13 = scala.collection.immutable.LazyList..hash.colon.colon..MODULE$.unapply(var11);
         if (var13.isEmpty()) {
            throw new MatchError(var6);
         }

         SafeLong x = (SafeLong)((Tuple2)var13.get())._1();
         LazyList xs = (LazyList)((Tuple2)var13.get())._2();
         if (var12 == null) {
            throw new MatchError(var6);
         }

         Option var16 = scala.collection.immutable.LazyList..hash.colon.colon..MODULE$.unapply(var12);
         if (var16.isEmpty()) {
            throw new MatchError(var6);
         }

         Rational c = (Rational)((Tuple2)var16.get())._1();
         LazyList cs = (LazyList)((Tuple2)var16.get())._2();
         SafeLong t = this.roundUp(c.$times(Rational$.MODULE$.apply(x)));
         if (BoxesRunTime.equalsNumObject(t, BoxesRunTime.boxToInteger(0))) {
            var5 = total;
            break;
         }

         SafeLong var10000 = total.$plus(t);
         cs = cs;
         xs = xs;
         total = var10000;
      }

      return var5;
   }

   /** @deprecated */
   public SafeLong accumulate(final SafeLong total, final Stream xs, final Stream cs) {
      SafeLong var5;
      while(true) {
         Tuple2 var6 = new Tuple2(xs, cs);
         if (var6 != null) {
            Stream var7 = (Stream)var6._2();
            if (scala.collection.immutable.Stream.Empty..MODULE$.equals(var7)) {
               var5 = total;
               break;
            }
         }

         if (var6 != null) {
            Stream var8 = (Stream)var6._1();
            if (scala.collection.immutable.Stream.Empty..MODULE$.equals(var8)) {
               throw scala.sys.package..MODULE$.error("nooooo");
            }
         }

         if (var6 == null) {
            throw new MatchError(var6);
         }

         Stream var9 = (Stream)var6._1();
         Stream var10 = (Stream)var6._2();
         if (var9 == null) {
            throw new MatchError(var6);
         }

         Option var11 = scala.package..hash.colon.colon..MODULE$.unapply(var9);
         if (var11.isEmpty()) {
            throw new MatchError(var6);
         }

         SafeLong x = (SafeLong)((Tuple2)var11.get())._1();
         Stream xs = (Stream)((Tuple2)var11.get())._2();
         if (var10 == null) {
            throw new MatchError(var6);
         }

         Option var14 = scala.package..hash.colon.colon..MODULE$.unapply(var10);
         if (var14.isEmpty()) {
            throw new MatchError(var6);
         }

         Rational c = (Rational)((Tuple2)var14.get())._1();
         Stream cs = (Stream)((Tuple2)var14.get())._2();
         SafeLong t = this.roundUp(c.$times(Rational$.MODULE$.apply(x)));
         if (BoxesRunTime.equalsNumObject(t, BoxesRunTime.boxToInteger(0))) {
            var5 = total;
            break;
         }

         SafeLong var10000 = total.$plus(t);
         cs = cs;
         xs = xs;
         total = var10000;
      }

      return var5;
   }

   public Real powerSeries(final LazyList ps, final Function1 terms, final Real x) {
      return this.apply((Function1)((p) -> $anonfun$powerSeries$1(terms, x, ps, BoxesRunTime.unboxToInt(p))));
   }

   public LazyList accSeq(final Function2 f) {
      return loop$3(Rational$.MODULE$.one(), SafeLong$.MODULE$.one(), f);
   }

   public Real expDr(final Real x) {
      return this.powerSeries(this.accSeq((r, n) -> r.$div(Rational$.MODULE$.apply(n))), (JFunction1.mcII.sp)(n) -> n, x);
   }

   public Real logDr(final Real x) {
      Real y = x.$minus(this.one()).$div(x);
      return y.$times(this.logDrx(y));
   }

   public Real logDrx(final Real x) {
      return this.powerSeries(scala.collection.immutable.LazyList..MODULE$.from(1).map((n) -> $anonfun$logDrx$1(BoxesRunTime.unboxToInt(n))), (JFunction1.mcII.sp)(x$2) -> x$2 + 1, x);
   }

   public Real sinDr(final Real x) {
      return x.$times(this.powerSeries(this.accSeq((r, n) -> r.unary_$minus().$times(Rational$.MODULE$.apply(SafeLong$.MODULE$.apply(1), SafeLong$.MODULE$.apply(2).$times(n).$times(SafeLong$.MODULE$.apply(2).$times(n).$plus(1L))))), (JFunction1.mcII.sp)(n) -> n, x.$times(x)));
   }

   public Real cosDr(final Real x) {
      return this.powerSeries(this.accSeq((r, n) -> r.unary_$minus().$times(Rational$.MODULE$.apply(SafeLong$.MODULE$.apply(1), SafeLong$.MODULE$.apply(2).$times(n).$times(SafeLong$.MODULE$.apply(2).$times(n).$minus(1L))))), (JFunction1.mcII.sp)(n) -> n, x.$times(x));
   }

   public Real atanDr(final Real x) {
      Real y = x.$times(x).$plus(this.apply(1));
      return x.$div(y).$times(this.atanDrx(x.$times(x).$div(y)));
   }

   public Real atanDrx(final Real x) {
      return this.powerSeries(this.accSeq((r, n) -> r.$times(Rational$.MODULE$.apply(SafeLong$.MODULE$.apply(2).$times(n), SafeLong$.MODULE$.apply(2).$times(n).$plus(1L)))), (JFunction1.mcII.sp)(x$3) -> x$3 * 2, x);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Real$.class);
   }

   // $FF: synthetic method
   public static final SafeLong $anonfun$atan2$1(final Real x$4, final Real y$8, final int p) {
      int pp = p;
      int sx = x$4.apply(p).signum();

      int sy;
      for(sy = y$8.apply(p).signum(); sx == 0 && sy == 0; ++pp) {
         sx = x$4.apply(pp).signum();
         sy = y$8.apply(pp).signum();
      }

      SafeLong var10000;
      if (sx > 0) {
         var10000 = MODULE$.atan(y$8.$div(x$4)).apply(p);
      } else if (sy >= 0 && sx < 0) {
         var10000 = MODULE$.atan(y$8.$div(x$4)).$plus(MODULE$.pi()).apply(p);
      } else if (sy < 0 && sx < 0) {
         var10000 = MODULE$.atan(y$8.$div(x$4)).$minus(MODULE$.pi()).apply(p);
      } else if (sy > 0) {
         var10000 = MODULE$.pi().$div(MODULE$.two()).apply(p);
      } else {
         if (sy >= 0) {
            throw new IllegalArgumentException("atan2(0, 0) is undefined");
         }

         var10000 = MODULE$.pi().unary_$minus().$div(MODULE$.two()).apply(p);
      }

      return var10000;
   }

   private final int loop$2(final SafeLong n, final int acc, final int base$1) {
      while(!n.$less$eq(SafeLong$.MODULE$.apply(1))) {
         SafeLong var10000 = n.$div((long)base$1);
         ++acc;
         n = var10000;
      }

      return acc + 1;
   }

   // $FF: synthetic method
   public static final SafeLong $anonfun$div2n$1(final int n$1, final Real x$5, final int p) {
      return p >= n$1 ? x$5.apply(p - n$1) : MODULE$.roundUp(Rational$.MODULE$.apply(x$5.apply(p), SafeLong$.MODULE$.two().pow(n$1)));
   }

   // $FF: synthetic method
   public static final SafeLong $anonfun$mul2n$1(final Real x$6, final int n$2, final int p) {
      return x$6.apply(p + n$2);
   }

   private static final SafeLong g$1(final SafeLong yn, final SafeLong xr$1, final SafeLong xn$1) {
      return MODULE$.roundUp(Rational$.MODULE$.apply(yn.$times(xr$1), xn$1));
   }

   // $FF: synthetic method
   public static final SafeLong $anonfun$powerSeries$1(final Function1 terms$1, final Real x$7, final LazyList ps$1, final int p) {
      int t = terms$1.apply$mcII$sp(p);
      int l2t = 2 * MODULE$.sizeInBase(SafeLong$.MODULE$.apply(t).$plus(1L), 2) + 6;
      int p2 = p + l2t;
      SafeLong xr = x$7.apply(p2);
      SafeLong xn = SafeLong$.MODULE$.two().pow(p2);
      if (BoxesRunTime.equalsNumObject(xn, BoxesRunTime.boxToInteger(0))) {
         throw scala.sys.package..MODULE$.error("oh no");
      } else {
         SafeLong num = MODULE$.accumulate(SafeLong$.MODULE$.zero(), scala.collection.immutable.LazyList..MODULE$.iterate(() -> xn, (yn) -> g$1(yn, xr, xn)), ps$1.take(t));
         SafeLong denom = SafeLong$.MODULE$.two().pow(l2t);
         return MODULE$.roundUp(Rational$.MODULE$.apply(num, denom));
      }
   }

   private static final LazyList loop$3(final Rational r, final SafeLong n, final Function2 f$1) {
      return scala.collection.immutable.LazyList.Deferrer..MODULE$.$hash$colon$colon$extension(scala.collection.immutable.LazyList..MODULE$.toDeferrer(() -> loop$3((Rational)f$1.apply(r, n), n.$plus(1L), f$1)), () -> r);
   }

   // $FF: synthetic method
   public static final Rational $anonfun$logDrx$1(final int n) {
      return Rational$.MODULE$.apply(1L, (long)n);
   }

   private Real$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
