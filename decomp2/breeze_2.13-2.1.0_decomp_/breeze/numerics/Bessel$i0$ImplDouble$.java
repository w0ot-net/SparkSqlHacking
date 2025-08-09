package breeze.numerics;

import breeze.generic.UFunc;
import breeze.generic.UFunc$UImpl$mcDD$sp;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.RichDouble.;

public class Bessel$i0$ImplDouble$ implements UFunc$UImpl$mcDD$sp {
   public static final Bessel$i0$ImplDouble$ MODULE$ = new Bessel$i0$ImplDouble$();

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
      return new ModuleSerializationProxy(Bessel$i0$ImplDouble$.class);
   }

   public double apply$mcDD$sp(final double x) {
      double ax = .MODULE$.abs$extension(scala.Predef..MODULE$.doubleWrapper(x));
      double var10000;
      if (ax < (double)15.0F) {
         double y = x * x;
         var10000 = package$.MODULE$.polyval(Bessel$.MODULE$.breeze$numerics$Bessel$$i0p(), y) / package$.MODULE$.polyval(Bessel$.MODULE$.breeze$numerics$Bessel$$i0q(), (double)225.0F - y);
      } else {
         double z = (double)1.0F - (double)15.0F / ax;
         var10000 = package.exp$.MODULE$.apply$mDDc$sp(ax, package$exp$expDoubleImpl$.MODULE$) * package$.MODULE$.polyval(Bessel$.MODULE$.breeze$numerics$Bessel$$i0pp(), z) / (package$.MODULE$.polyval(Bessel$.MODULE$.breeze$numerics$Bessel$$i0qq(), z) * package.sqrt$.MODULE$.apply$mDDc$sp(ax, package$sqrt$sqrtDoubleImpl$.MODULE$));
      }

      return var10000;
   }
}
