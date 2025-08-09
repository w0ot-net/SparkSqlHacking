package breeze.numerics;

import breeze.generic.UFunc;
import breeze.generic.UFunc$UImpl2$mcDDD$sp;
import breeze.linalg.logDiff;
import breeze.linalg.logDiff$;
import scala.math.package.;
import scala.runtime.ModuleSerializationProxy;

public class package$lgamma$lgammaImplDoubleDouble$ implements UFunc$UImpl2$mcDDD$sp {
   public static final package$lgamma$lgammaImplDoubleDouble$ MODULE$ = new package$lgamma$lgammaImplDoubleDouble$();

   public float apply$mcDDF$sp(final double v, final double v2) {
      return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
   }

   public int apply$mcDDI$sp(final double v, final double v2) {
      return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
   }

   public double apply$mcDFD$sp(final double v, final float v2) {
      return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
   }

   public float apply$mcDFF$sp(final double v, final float v2) {
      return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
   }

   public int apply$mcDFI$sp(final double v, final float v2) {
      return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
   }

   public double apply$mcDID$sp(final double v, final int v2) {
      return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
   }

   public float apply$mcDIF$sp(final double v, final int v2) {
      return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
   }

   public int apply$mcDII$sp(final double v, final int v2) {
      return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
   }

   public double apply$mcFDD$sp(final float v, final double v2) {
      return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
   }

   public float apply$mcFDF$sp(final float v, final double v2) {
      return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
   }

   public int apply$mcFDI$sp(final float v, final double v2) {
      return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
   }

   public double apply$mcFFD$sp(final float v, final float v2) {
      return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
   }

   public float apply$mcFFF$sp(final float v, final float v2) {
      return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
   }

   public int apply$mcFFI$sp(final float v, final float v2) {
      return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
   }

   public double apply$mcFID$sp(final float v, final int v2) {
      return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
   }

   public float apply$mcFIF$sp(final float v, final int v2) {
      return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
   }

   public int apply$mcFII$sp(final float v, final int v2) {
      return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
   }

   public double apply$mcIDD$sp(final int v, final double v2) {
      return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
   }

   public float apply$mcIDF$sp(final int v, final double v2) {
      return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
   }

   public int apply$mcIDI$sp(final int v, final double v2) {
      return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
   }

   public double apply$mcIFD$sp(final int v, final float v2) {
      return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
   }

   public float apply$mcIFF$sp(final int v, final float v2) {
      return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
   }

   public int apply$mcIFI$sp(final int v, final float v2) {
      return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
   }

   public double apply$mcIID$sp(final int v, final int v2) {
      return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
   }

   public float apply$mcIIF$sp(final int v, final int v2) {
      return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
   }

   public int apply$mcIII$sp(final int v, final int v2) {
      return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
   }

   public double apply(final double a, final double x) {
      return this.apply$mcDDD$sp(a, x);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(package$lgamma$lgammaImplDoubleDouble$.class);
   }

   public double apply$mcDDD$sp(final double a, final double x) {
      if (!(x < (double)0.0F) && !(a <= (double)0.0F)) {
         double var10000;
         if (x == (double)0) {
            var10000 = (double)0.0F;
         } else if (x < a + (double)1.0F) {
            double ap = a;
            double del = (double)1.0F / a;
            double sum = (double)1.0F / a;
            int n = 0;

            double result;
            for(result = Double.NaN; n < 100; ++n) {
               ap += (double)1;
               del *= x / ap;
               sum += del;
               if (.MODULE$.abs(del) < .MODULE$.abs(sum) * 1.0E-7) {
                  result = -x + a * .MODULE$.log(x) + .MODULE$.log(sum);
                  n = 100;
               }
            }

            if (Double.isNaN(result)) {
               throw new ArithmeticException("Convergence failed");
            }

            var10000 = result;
         } else {
            double gln = package.lgamma$.MODULE$.apply$mDDc$sp(a, package$lgamma$lgammaImplDouble$.MODULE$);
            double b = x + (double)1.0F - a;
            double c = 9.999999999999999E29;
            double d = (double)1.0F / b;
            double h = d;
            int n = 0;

            while(n < 100) {
               ++n;
               double an = (double)(-n) * ((double)n - a);
               b += (double)2.0F;
               d = an * d + b;
               if (.MODULE$.abs(d) < 1.0E-30) {
                  d = 1.0E-30;
               }

               c = b + an / c;
               if (.MODULE$.abs(c) < 1.0E-30) {
                  c = 1.0E-30;
               }

               d = (double)1.0F / d;
               double del = d * c;
               h *= del;
               if (.MODULE$.abs(del - (double)1.0F) < 1.0E-7) {
                  n = 101;
               }
            }

            if (n == 100) {
               throw new ArithmeticException("Convergence failed");
            }

            var10000 = logDiff$.MODULE$.apply$mDDDc$sp(gln, -x + a * .MODULE$.log(x) + .MODULE$.log(h), logDiff.implDoubleDouble$.MODULE$);
         }

         return var10000;
      } else {
         throw new IllegalArgumentException();
      }
   }
}
