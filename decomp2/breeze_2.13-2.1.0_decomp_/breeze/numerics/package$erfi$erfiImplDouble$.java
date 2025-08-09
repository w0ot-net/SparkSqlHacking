package breeze.numerics;

import breeze.generic.UFunc;
import breeze.generic.UFunc$UImpl$mcDD$sp;
import scala.math.package.;
import scala.runtime.ModuleSerializationProxy;

public class package$erfi$erfiImplDouble$ implements UFunc$UImpl$mcDD$sp {
   public static final package$erfi$erfiImplDouble$ MODULE$ = new package$erfi$erfiImplDouble$();

   public float apply$mcDF$sp(final double v) {
      return UFunc.UImpl.apply$mcDF$sp$(this, v);
   }

   public int apply$mcDI$sp(final double v) {
      return UFunc.UImpl.apply$mcDI$sp$(this, v);
   }

   public double apply$mcFD$sp(final float v) {
      return UFunc.UImpl.apply$mcFD$sp$(this, v);
   }

   public float apply$mcFF$sp(final float v) {
      return UFunc.UImpl.apply$mcFF$sp$(this, v);
   }

   public int apply$mcFI$sp(final float v) {
      return UFunc.UImpl.apply$mcFI$sp$(this, v);
   }

   public double apply$mcID$sp(final int v) {
      return UFunc.UImpl.apply$mcID$sp$(this, v);
   }

   public float apply$mcIF$sp(final int v) {
      return UFunc.UImpl.apply$mcIF$sp$(this, v);
   }

   public int apply$mcII$sp(final int v) {
      return UFunc.UImpl.apply$mcII$sp$(this, v);
   }

   public double apply(final double x) {
      return this.apply$mcDD$sp(x);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(package$erfi$erfiImplDouble$.class);
   }

   public double apply$mcDD$sp(final double x) {
      double var10000;
      if (x < (double)0) {
         var10000 = -this.apply$mcDD$sp(-x);
      } else {
         double y = x;
         double x2 = x * x;
         double xx = x;
         double f = (double)1.0F;

         double del;
         for(int n = 0; n < 100; y += del) {
            ++n;
            f /= (double)n;
            xx *= x2;
            del = f * xx / (double)(2 * n + 1);
            if (del < 1.0E-8) {
               n = 101;
            }
         }

         y = y * (double)2 / .MODULE$.sqrt(Math.PI);
         var10000 = y;
      }

      return var10000;
   }
}
