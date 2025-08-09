package spire.random;

import java.lang.invoke.SerializedLambda;
import scala.math.package.;
import scala.runtime.DoubleRef;
import scala.runtime.IntRef;
import scala.runtime.LongRef;
import scala.runtime.java8.JFunction1;

public final class Ziggurat$ {
   public static final Ziggurat$ MODULE$ = new Ziggurat$();
   private static final long[] kn = new long[128];
   private static final double[] wn = new double[128];
   private static final double[] fn = new double[128];
   private static final long[] ke = new long[256];
   private static final double[] we = new double[256];
   private static final double[] fe = new double[256];

   static {
      double m1 = (double)(float)Integer.MAX_VALUE;
      double m2 = (double)4.2949673E9F;
      DoubleRef dn = DoubleRef.create(3.442619855899);
      DoubleRef tn = DoubleRef.create(dn.elem);
      DoubleRef de = DoubleRef.create(7.697117470131487);
      DoubleRef te = DoubleRef.create(de.elem);
      double vn = 0.00991256303526217;
      double ve = 0.003949659822581572;
      double q = vn / .MODULE$.exp((double)-0.5F * dn.elem * dn.elem);
      MODULE$.kn()[0] = (long)(dn.elem / q * m1);
      MODULE$.kn()[1] = 0L;
      MODULE$.wn()[0] = q / m1;
      MODULE$.wn()[127] = dn.elem / m1;
      MODULE$.fn()[0] = (double)1.0F;
      MODULE$.fn()[127] = .MODULE$.exp((double)-0.5F * dn.elem * dn.elem);
      scala.runtime.RichInt..MODULE$.to$extension(scala.Predef..MODULE$.intWrapper(126), 1).by(-1).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> {
         dn.elem = .MODULE$.sqrt((double)-2 * .MODULE$.log(vn / dn.elem + .MODULE$.exp((double)-0.5F * dn.elem * dn.elem)));
         MODULE$.kn()[i + 1] = (long)(dn.elem / tn.elem * m1);
         tn.elem = dn.elem;
         MODULE$.fn()[i] = .MODULE$.exp((double)-0.5F * dn.elem * dn.elem);
         MODULE$.wn()[i] = dn.elem / m1;
      });
      q = ve / .MODULE$.exp(-de.elem);
      MODULE$.ke()[0] = (long)(de.elem / q * m2);
      MODULE$.ke()[1] = 0L;
      MODULE$.we()[0] = q / m2;
      MODULE$.we()[255] = de.elem / m2;
      MODULE$.fe()[0] = (double)1.0F;
      MODULE$.fe()[255] = .MODULE$.exp(-de.elem);
      scala.runtime.RichInt..MODULE$.to$extension(scala.Predef..MODULE$.intWrapper(254), 1).by(-1).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> {
         de.elem = -.MODULE$.log(ve / de.elem + .MODULE$.exp(-de.elem));
         MODULE$.ke()[i + 1] = (long)(de.elem / te.elem * m2);
         te.elem = de.elem;
         MODULE$.fe()[i] = .MODULE$.exp(-de.elem);
         MODULE$.we()[i] = de.elem / m2;
      });
   }

   private long[] kn() {
      return kn;
   }

   private double[] wn() {
      return wn;
   }

   private double[] fn() {
      return fn;
   }

   private long[] ke() {
      return ke;
   }

   private double[] we() {
      return we;
   }

   private double[] fe() {
      return fe;
   }

   public double rnor(final Generator g) {
      int hz = g.nextInt();
      int iz = hz & 127;
      return (long).MODULE$.abs(hz) < this.kn()[iz] ? (double)hz * this.wn()[iz] : this.nfix(g, hz, iz);
   }

   public double rexp(final Generator g) {
      long jz = (long)g.nextInt() & 4294967295L;
      int iz = (int)(jz & 255L);
      return jz < this.ke()[iz] ? (double)jz * this.we()[iz] : this.efix(g, jz, iz);
   }

   private double nfix(final Generator g, final int hza, final int iza) {
      double r = 3.442619855899;
      double r1 = (double)1 / r;
      DoubleRef x = DoubleRef.create((double)0.0F);
      DoubleRef y = DoubleRef.create((double)0.0F);
      IntRef hz = IntRef.create(hza);
      IntRef iz = IntRef.create(iza);
      return this.loop$1(x, hz, iz, g, r1, y, r);
   }

   private double efix(final Generator g, final long jza, final int iza) {
      LongRef jz = LongRef.create(jza);
      IntRef iz = IntRef.create(iza);
      return this.loop$2(iz, g, jz);
   }

   private final double loop$1(final DoubleRef x$1, final IntRef hz$1, final IntRef iz$1, final Generator g$1, final double r1$1, final DoubleRef y$1, final double r$1) {
      do {
         x$1.elem = (double)hz$1.elem * this.wn()[iz$1.elem];
         if (iz$1.elem == 0) {
            x$1.elem = -.MODULE$.log(g$1.nextDouble()) * r1$1;
            y$1.elem = -.MODULE$.log(g$1.nextDouble());
            if (y$1.elem + y$1.elem < x$1.elem * x$1.elem) {
               return hz$1.elem > 0 ? r$1 + x$1.elem : -r$1 - x$1.elem;
            }
         }

         if (this.fn()[iz$1.elem] + g$1.nextDouble() * (this.fn()[iz$1.elem - 1] - this.fn()[iz$1.elem]) < .MODULE$.exp((double)-0.5F * x$1.elem * x$1.elem)) {
            return x$1.elem;
         }

         hz$1.elem = g$1.nextInt();
         iz$1.elem = hz$1.elem & 127;
      } while((long).MODULE$.abs(hz$1.elem) >= this.kn()[iz$1.elem]);

      return (double)hz$1.elem * this.wn()[iz$1.elem];
   }

   private final double loop$2(final IntRef iz$2, final Generator g$2, final LongRef jz$1) {
      while(iz$2.elem != 0) {
         double x = (double)jz$1.elem * this.we()[iz$2.elem];
         if (this.fe()[iz$2.elem] + g$2.nextDouble() * (this.fe()[iz$2.elem - 1] - this.fe()[iz$2.elem]) < .MODULE$.exp(-x)) {
            return x;
         }

         jz$1.elem = (long)g$2.nextInt() & 4294967295L;
         iz$2.elem = (int)(jz$1.elem & 255L);
         if (jz$1.elem < this.ke()[iz$2.elem]) {
            return (double)jz$1.elem * this.we()[iz$2.elem];
         }
      }

      return 7.697117470131487 - .MODULE$.log(g$2.nextDouble());
   }

   private Ziggurat$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
