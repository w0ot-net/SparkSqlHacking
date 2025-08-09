package breeze.optimize;

import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Tuple2;
import scala.Tuple3;
import scala.Tuple5;
import scala.collection.immutable.Seq;
import scala.math.package.;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;

public final class RootFinding$ {
   public static final RootFinding$ MODULE$ = new RootFinding$();
   private static double eps;
   private static int defaultMaxIter;
   private static volatile byte bitmap$0;

   private double eps$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(bitmap$0 & 1) == 0) {
            eps = .MODULE$.ulp((double)1.0F);
            bitmap$0 = (byte)(bitmap$0 | 1);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return eps;
   }

   public double eps() {
      return (byte)(bitmap$0 & 1) == 0 ? this.eps$lzycompute() : eps;
   }

   private int defaultMaxIter$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(bitmap$0 & 2) == 0) {
            defaultMaxIter = 1000;
            bitmap$0 = (byte)(bitmap$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return defaultMaxIter;
   }

   public int defaultMaxIter() {
      return (byte)(bitmap$0 & 2) == 0 ? this.defaultMaxIter$lzycompute() : defaultMaxIter;
   }

   public double find(final Function1 fn, final double x0, final Option x1) {
      double xx1 = BoxesRunTime.unboxToDouble(x1.orElse(() -> findSecondEstimate$1(x0, fn)).getOrElse(() -> {
         throw new RuntimeException("Automatic search of a second bracketing value failed");
      }));
      return this.brent(fn, x0, xx1);
   }

   public Option find$default$3() {
      return scala.None..MODULE$;
   }

   public double bisection(final Function1 fn, final double a, final double b) {
      double fa = fn.apply$mcDD$sp(a);
      double fb = fn.apply$mcDD$sp(b);
      scala.Predef..MODULE$.require(scala.runtime.RichDouble..MODULE$.signum$extension(scala.Predef..MODULE$.doubleWrapper(fa)) != scala.runtime.RichDouble..MODULE$.signum$extension(scala.Predef..MODULE$.doubleWrapper(fb)), () -> "The root is not bracketed by the given interval");
      return this.bis$1(a, b, fn, fa);
   }

   public double newtonRaphson(final Function1 fn, final Function1 fd, final double x0, final int maxIter) {
      return this.nr$1(x0, 0, fn, maxIter, fd);
   }

   public int newtonRaphson$default$4() {
      return this.defaultMaxIter();
   }

   public double secant(final Function1 fn, final double x0, final double x1, final int maxIter) {
      return this.se$1(x0, x1, 0, fn, maxIter);
   }

   public int secant$default$4() {
      return this.defaultMaxIter();
   }

   public double brent(final Function1 fn, final double a, final double b) {
      Tuple2.mcDD.sp var8 = new Tuple2.mcDD.sp(fn.apply$mcDD$sp(a), fn.apply$mcDD$sp(b));
      if (var8 != null) {
         double fa = ((Tuple2)var8)._1$mcD$sp();
         double fb = ((Tuple2)var8)._2$mcD$sp();
         Tuple2.mcDD.sp var6 = new Tuple2.mcDD.sp(fa, fb);
         double fa = ((Tuple2)var6)._1$mcD$sp();
         double fb = ((Tuple2)var6)._2$mcD$sp();
         scala.Predef..MODULE$.require(scala.runtime.RichDouble..MODULE$.signum$extension(scala.Predef..MODULE$.doubleWrapper(fa)) != scala.runtime.RichDouble..MODULE$.signum$extension(scala.Predef..MODULE$.doubleWrapper(fb)), () -> "The root is not bracketed by the given interval");
         return this.brentAux$1(a, b, a, b - a, b - a, fa, fb, fa, fn);
      } else {
         throw new MatchError(var8);
      }
   }

   private static final Option findSecondEstimate$1(final double x0, final Function1 fn$1) {
      Seq search = (Seq)scala.package..MODULE$.Seq().apply(scala.runtime.ScalaRunTime..MODULE$.wrapDoubleArray(new double[]{-0.01, 0.025, -0.05, 0.1, (double)-0.25F, (double)0.5F, (double)-1.0F, (double)2.5F, (double)-5.0F, (double)10.0F, (double)-50.0F, (double)100.0F, (double)-500.0F, (double)1000.0F}));
      return search.view().map((JFunction1.mcDD.sp)(s) -> x0 + x0 * s).find((JFunction1.mcZD.sp)(b) -> fn$1.apply$mcDD$sp(x0) * fn$1.apply$mcDD$sp(b) <= (double)0);
   }

   private final double bis$1(final double a, final double b, final Function1 fn$2, final double fa$1) {
      while(true) {
         double m = (a + b) / (double)2;
         double fm = fn$2.apply$mcDD$sp(m);
         if (scala.runtime.RichDouble..MODULE$.abs$extension(scala.Predef..MODULE$.doubleWrapper(fm)) < (double)2 * this.eps()) {
            return m;
         }

         if (scala.runtime.RichDouble..MODULE$.signum$extension(scala.Predef..MODULE$.doubleWrapper(fm)) != scala.runtime.RichDouble..MODULE$.signum$extension(scala.Predef..MODULE$.doubleWrapper(fa$1))) {
            b = m;
            a = a;
         } else {
            b = b;
            a = m;
         }
      }
   }

   private final double nr$1(final double x, final int iter, final Function1 fn$3, final int maxIter$1, final Function1 fd$1) {
      while(!(scala.runtime.RichDouble..MODULE$.abs$extension(scala.Predef..MODULE$.doubleWrapper(fn$3.apply$mcDD$sp(x))) < (double)2 * this.eps()) && iter != maxIter$1) {
         double var10000 = x - fn$3.apply$mcDD$sp(x) / fd$1.apply$mcDD$sp(x);
         ++iter;
         x = var10000;
      }

      return x;
   }

   private final double se$1(final double x0, final double x1, final int iter, final Function1 fn$4, final int maxIter$2) {
      while(!(scala.runtime.RichDouble..MODULE$.abs$extension(scala.Predef..MODULE$.doubleWrapper(fn$4.apply$mcDD$sp(x1))) < (double)2 * this.eps()) && iter != maxIter$2) {
         double fx1 = fn$4.apply$mcDD$sp(x1);
         double var10000 = x1;
         double var10001 = x1 - (x1 - x0) / (fx1 - fn$4.apply$mcDD$sp(x0)) * fx1;
         ++iter;
         x1 = var10001;
         x0 = var10000;
      }

      return x1;
   }

   private final double brentAux$1(final double aa, final double bb, final double cc, final double dd, final double ee, final double ffa, final double ffb, final double ffc, final Function1 fn$5) {
      while(true) {
         Tuple5 var25 = new Tuple5(BoxesRunTime.boxToDouble(aa), BoxesRunTime.boxToDouble(bb), BoxesRunTime.boxToDouble(cc), BoxesRunTime.boxToDouble(dd), BoxesRunTime.boxToDouble(ee));
         if (var25 == null) {
            throw new MatchError(var25);
         }

         double a = BoxesRunTime.unboxToDouble(var25._1());
         double b = BoxesRunTime.unboxToDouble(var25._2());
         double c = BoxesRunTime.unboxToDouble(var25._3());
         double d = BoxesRunTime.unboxToDouble(var25._4());
         double e = BoxesRunTime.unboxToDouble(var25._5());
         Tuple5 var23 = new Tuple5(BoxesRunTime.boxToDouble(a), BoxesRunTime.boxToDouble(b), BoxesRunTime.boxToDouble(c), BoxesRunTime.boxToDouble(d), BoxesRunTime.boxToDouble(e));
         double a = BoxesRunTime.unboxToDouble(var23._1());
         double b = BoxesRunTime.unboxToDouble(var23._2());
         double c = BoxesRunTime.unboxToDouble(var23._3());
         double d = BoxesRunTime.unboxToDouble(var23._4());
         double e = BoxesRunTime.unboxToDouble(var23._5());
         Tuple3 var47 = new Tuple3(BoxesRunTime.boxToDouble(ffa), BoxesRunTime.boxToDouble(ffb), BoxesRunTime.boxToDouble(ffc));
         if (var47 != null) {
            double fa = BoxesRunTime.unboxToDouble(var47._1());
            double fb = BoxesRunTime.unboxToDouble(var47._2());
            double fc = BoxesRunTime.unboxToDouble(var47._3());
            Tuple3 var22 = new Tuple3(BoxesRunTime.boxToDouble(fa), BoxesRunTime.boxToDouble(fb), BoxesRunTime.boxToDouble(fc));
            double fa = BoxesRunTime.unboxToDouble(var22._1());
            double fb = BoxesRunTime.unboxToDouble(var22._2());
            double fc = BoxesRunTime.unboxToDouble(var22._3());
            if (fb == (double)0) {
               return b;
            }

            if (scala.runtime.RichDouble..MODULE$.signum$extension(scala.Predef..MODULE$.doubleWrapper(fc)) == scala.runtime.RichDouble..MODULE$.signum$extension(scala.Predef..MODULE$.doubleWrapper(fb))) {
               c = a;
               fc = fa;
               d = b - a;
               e = d;
            }

            if (scala.runtime.RichDouble..MODULE$.abs$extension(scala.Predef..MODULE$.doubleWrapper(fc)) < scala.runtime.RichDouble..MODULE$.abs$extension(scala.Predef..MODULE$.doubleWrapper(fb))) {
               a = b;
               b = c;
               c = a;
               fa = fb;
               fb = fc;
               fc = fa;
            }

            double m = (double)0.5F * (c - b);
            double tol = (double)2 * this.eps() * .MODULE$.max(scala.runtime.RichDouble..MODULE$.abs$extension(scala.Predef..MODULE$.doubleWrapper(b)), (double)1.0F);
            if (!(scala.runtime.RichDouble..MODULE$.abs$extension(scala.Predef..MODULE$.doubleWrapper(m)) <= tol) && fb != (double)0) {
               if (!(scala.runtime.RichDouble..MODULE$.abs$extension(scala.Predef..MODULE$.doubleWrapper(e)) < tol) && !(scala.runtime.RichDouble..MODULE$.abs$extension(scala.Predef..MODULE$.doubleWrapper(fa)) <= scala.runtime.RichDouble..MODULE$.abs$extension(scala.Predef..MODULE$.doubleWrapper(fb)))) {
                  double s = fb / fa;
                  Tuple2.mcDD.sp var10000;
                  if (a == c) {
                     var10000 = new Tuple2.mcDD.sp((double)2 * m * s, (double)1 - s);
                  } else {
                     double q0 = fa / fc;
                     double r = fb / fc;
                     var10000 = new Tuple2.mcDD.sp(s * ((double)2 * m * q0 * (q0 - r) - (b - a) * (r - (double)1)), (q0 - (double)1) * (r - (double)1) * (s - (double)1));
                  }

                  Tuple2.mcDD.sp var67 = var10000;
                  if (var67 == null) {
                     throw new MatchError(var67);
                  }

                  double p1 = ((Tuple2)var67)._1$mcD$sp();
                  double q1 = ((Tuple2)var67)._2$mcD$sp();
                  Tuple2.mcDD.sp var21 = new Tuple2.mcDD.sp(p1, q1);
                  double p1 = ((Tuple2)var21)._1$mcD$sp();
                  double q1 = ((Tuple2)var21)._2$mcD$sp();
                  Tuple2.mcDD.sp var81 = p1 > (double)0 ? new Tuple2.mcDD.sp(-q1, p1) : new Tuple2.mcDD.sp(q1, -p1);
                  if (var81 == null) {
                     throw new MatchError(var81);
                  }

                  double q = ((Tuple2)var81)._1$mcD$sp();
                  double p = ((Tuple2)var81)._2$mcD$sp();
                  Tuple2.mcDD.sp var20 = new Tuple2.mcDD.sp(q, p);
                  double q = ((Tuple2)var20)._1$mcD$sp();
                  double p = ((Tuple2)var20)._2$mcD$sp();
                  Tuple2.mcDD.sp var91 = (double)2 * p < (double)3 * m * q - scala.runtime.RichDouble..MODULE$.abs$extension(scala.Predef..MODULE$.doubleWrapper(tol * q)) && p < scala.runtime.RichDouble..MODULE$.abs$extension(scala.Predef..MODULE$.doubleWrapper((double)0.5F * e * q)) ? new Tuple2.mcDD.sp(p / q, d) : new Tuple2.mcDD.sp(m, m);
                  if (var91 == null) {
                     throw new MatchError(var91);
                  }

                  double td = ((Tuple2)var91)._1$mcD$sp();
                  double te = ((Tuple2)var91)._2$mcD$sp();
                  Tuple2.mcDD.sp var19 = new Tuple2.mcDD.sp(td, te);
                  double td = ((Tuple2)var19)._1$mcD$sp();
                  double te = ((Tuple2)var19)._2$mcD$sp();
                  d = td;
                  e = te;
               } else {
                  e = m;
                  d = m;
               }

               a = b;
               fa = fb;
               b += scala.runtime.RichDouble..MODULE$.abs$extension(scala.Predef..MODULE$.doubleWrapper(d)) > tol ? d : (m > (double)0 ? tol : -tol);
               fb = fn$5.apply$mcDD$sp(b);
               ffc = fc;
               ffb = fb;
               ffa = fa;
               ee = e;
               dd = d;
               cc = c;
               bb = b;
               aa = a;
               continue;
            }

            return b;
         }

         throw new MatchError(var47);
      }
   }

   private RootFinding$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
